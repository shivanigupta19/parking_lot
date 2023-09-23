import org.example.enums.VehicleType;
import org.example.errors.ParkingLotErrors;
import org.example.exception.impl.InvalidVehicleNumberException;
import org.example.exception.impl.ParkingFullException;
import org.example.parkingStrategy.VehicleParkingStrategy;
import org.example.parkingStrategy.impl.NearestParkingStrategy;
import org.example.pojo.Vehicle;
import org.example.services.ParkingSpotManager;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ParkingLotTest {
    ParkingSpotManager parkingSpotManager;
    @Before
    public void setUp() {
        parkingSpotManager = new ParkingSpotManager(5);
        parkingSpotManager.initializeParkingLot();

        VehicleParkingStrategy vehicleParkingStrategy = new NearestParkingStrategy();
        parkingSpotManager.setVehicleParkingStrategy(vehicleParkingStrategy);
    }

    @Test
    public void createParkingLotTest() {
        assertEquals(parkingSpotManager.getParkingSpotList().size(), 5);
    }

    @Test
    public void checkIfNotCreatedParkingLotTest() {
        assertNotEquals(parkingSpotManager.getParkingSpotList().size(), 15);
    }

    @Test
    public void parkVehicleTest() {
        try{
            Vehicle vehicle = new Vehicle("234", "rEd", VehicleType.Car);
            parkingSpotManager.parkVehicle(vehicle);
            String ticketId = "0_234";

            assertEquals(parkingSpotManager.getRegistrationNoToVehicleMapping().size(), 1);
            assertEquals(parkingSpotManager.getColorToVehiclesMapping().size(), 1);

            assertEquals(parkingSpotManager.getRegistrationNoToVehicleMapping().get("234"), vehicle );
            assertEquals(parkingSpotManager.getColorToVehiclesMapping().get("red").get(0), vehicle );
            assertNotNull(vehicle.getTicket());
            assertEquals(vehicle.getTicket().getTicketId(), ticketId);
        } catch (ParkingFullException parkingFullException) {
            assertNull(parkingFullException);
        }
    }

    @Test
    public void removeVehicletest() {
        try {
            parkVehicleTest();
            parkingSpotManager.removeVehicle(parkingSpotManager.getParkingSpotList().get(0).getVehicle().getRegistrationNumber());

            assertEquals(parkingSpotManager.getRegistrationNoToVehicleMapping().size(), 0);
            assertEquals(parkingSpotManager.getColorToVehiclesMapping().size(), 0);

            assertEquals(parkingSpotManager.getRegistrationNoToVehicleMapping().get("234"), null );
            assertEquals(parkingSpotManager.getColorToVehiclesMapping().get("red"), null );
        } catch (InvalidVehicleNumberException invalidVehicleNumberException) {
            assertNull(invalidVehicleNumberException);
        }
    }

    @Test
    public void parkingFullExceptionTest() {
        try{
            String[] registrationNo = {
                    "765",
                    "876",
                    "890",
                    "654",
                    "432",
                    "891"
            };

            for(int i = 0; i < 6; i++) {
                Vehicle vehicle = new Vehicle(registrationNo[i], "red", VehicleType.Car);
                parkingSpotManager.parkVehicle(vehicle);
            }

        } catch (ParkingFullException parkingFullException) {
            assertNotNull(parkingFullException);
            assertEquals(parkingFullException.getMessage(), ParkingLotErrors.PARKING_FULL_ERROR);
        }
    }
}
