# parking_lot

💁🏻 Design a parking lot 🎯

<h2>Specifications - </h2>

👉🏼 System that can hold cars of different colors and different registration numbers. <br>
👉🏼 Every car has been issued a ticket for a spot and the spot has been assigned based on the nearest to the entry point. <br>
👉🏼 Registration numbers of all cars of a particular Color. <br>
👉🏼 Ticket number in which a car with a given registration number is placed. <br>
👉🏼 Ticket numbers of all tickets where a car of a particular color is placed. <br>

<br>

<h2>🟢 Enum </h2>
   🔹 <u>VehicleType </u><br>
      <ul>🔺Car</ul>


<br>



<h2>🟢 Interface -</h2>
   🔹 <u>Parking</u><br>
      <ul>🔺parkVehicle()</ul>
      <ul>🔺removeVehicle()</ul>

   🔹 <u>VehicleParkingStategy</u>
      <ul>🔺getAvailableSpot()</ul>


<br>



<h2>🟢 Class - </h2>
   🔹 <u>Vehicle </u><br>
      <ul>🔺String registrationNo</ul>
      <ul>🔺VehicleType vehicleType</ul>
      <ul>🔺String color</ul>
      <ul>🔺Ticket ticket</ul>
      <ul>🔺Getters / setters / constructors</ul>

   🔹 <u>Ticket </u><br>
      <ul>🔺String ticketId</ul>
      <ul>🔺String entryTime</ul>
      <ul>🔺ParkingSpot parkingSpot</ul>
      <ul>🔺Getters / setters / constructors</ul>

   🔹 <u>ParkingSpot </u><br>
      <ul>🔺Int parkingSpotId</ul>
      <ul>🔺Boolean isEmpty</ul>
      <ul>🔺Vehicle vehicle</ul>
      <ul>🔺parkVehicle()</ul>
      <ul>🔺removeVehicle()</ul>
      <ul>🔺Getters / setters / constructors</ul>

   🔹 <u>ParkingFullException</u><br>
      <ul>🔺InvalidVehicleNumberException</ul>

   🔹 <u>NearestParkingStrategy </u><br>
      <ul>🔺getAvailableSpot()</ul>

   🔹 <u>ParkingSpotManager </u><br>
      <ul>🔺Int capacity</ul>
      <ul>🔺List<ParkingSpot> parkingSpotList</ul>
      <ul>🔺HashMap<String,List<Vehicle>> colorToVehicleMapping</ul>
      <ul>🔺HashMap<String,Vehicle> registrationNoToVehicleMapping</ul>
      <ul>🔺VehicleParkingStrategy vehicleParkingStrategy</ul>
      <ul>🔺initializeParkingLot()</ul>
      <ul>🔺addParkingSpace()</ul>
      <ul>🔺removeParkingSpace()</ul>
      <ul>🔺parkVehicle()</ul>
      <ul>🔺removeVehicle()</ul>
      <ul>🔺getRegistrationNoMappedToColor</ul>
      <ul>🔺getTicketIdMappedToRegistrationNo</ul>
      <ul>🔺getTicketIdMappedToColor</ul>
      <ul>🔺Getters / setters / constructors</ul>


<br>

<p>📝 Designed a system that can hold cars of different colors and registration numbers. The system will issue a parking ticket to each car and assign a parking spot based on proximity to the entry point. Additionally, the system will support various queries such as retrieving registration numbers of cars of a particular color, finding the ticket number for a given registration number, and retrieving ticket numbers for cars of a specific color.</p>

<br>

<h2>🚩Parking Lot Design - </h2>
<p>📝 The parking lot consists of a fixed number of parking spots, each identified by a unique spot number.
Spots may vary in size to accommodate different types of vehicles (e.g., compact, standard, or oversized).
The parking lot will have an entry point for cars to enter and an exit point for cars to exit.
</p>

<br>

<h2>🚩Ticket Generation - </h2>
<p>📝 When a car enters the parking lot, a unique ticket is generated for that car.
Each ticket should include the ticket number and the timestamp when it was issued.</p>

<br>

<h2>🚩Car Registration and Allocation - </h2>
<p>📝 When a car enters the parking lot, the system records its details, including color and registration number.
The system assigns the car to the nearest available parking spot based on an algorithm that calculates proximity to the entry point.
</p>

<br>

<h3>🚩The query engine provides the following functionality:</h3>
<ul>🔶 <b>Registration Numbers by Color</b> - Users can query the system to retrieve the registration numbers of all cars of a particular color.</ul>
<ul>🔶 <b>Ticket Number by Registration Number</b> -  Users can find the ticket number associated with a given registration number.</ul>
<ul>🔶 <b>Ticket Numbers by Color</b> -  Users can retrieve the ticket numbers of all tickets where a car of a particular color is parked.</ul>

<br>


<h3>Input/Output Format 👇</h3>

<h5>Multiple lines with each containing a command</h5>

<h7>Possible commands 👇</h7>
<ul>⭐ Create_parking_lot <parking_lot_capacity></ul>
<ul>⭐ park_vehicle <registration_no> <color></ul>
<ul>⭐ unpark_vehicle <registration_no></ul>
<ul>⭐ display regNo_mapped_to_color <color></ul>
<ul>⭐ display ticketId_mapped_to_regNo <registration_no></ul>
<ul>⭐ display ticketIds_mapped_to_color <color></ul>
<ul>⭐ exit</ul>

Stop taking input when you encounter the word exit

<h3>Output Format 👇 </h3>

<b>📍 Create_parking_lot </b><br>
<ul>Parking Spot created with capacity of <parking_lot_capacity></ul>

<b>📍 park_vehicle </b><br>
	<ul>Car having registration No. <registration_no>, color <color> and ticker Id <ticketId> is parked on <entry_time> at Parking Spot <parking_spot_id></ul>

<b>📍 unpark_vehicle </b><br>
<ul>The Car having registration No. <registration_no> and color <color> is removed from parking spot <parking_spot_id></ul>
  
<b>📍 display regNo_mapped_to_color </b><br>
<ul>The Registration No of Car which belongs to the color <color> are - </ul>
<ul><ul><registration_no1></ul></ul>
<ul><ul><registration_no2></ul></ul>


The Above will be printed for each Registration No


<b>📍 display ticketId_mapped_to_regNo <registration_no></b>
<ul>The Ticket Id of Car which has a Registration No <registration_no> is <ticketId></ul>


<b>📍 display ticketIds_mapped_to_color <color></b><br>
	<ul>The Ticket Ids of Car which has the color <color> are -</ul>
	<ul><ul><ticketId1></ul></ul>
 <ul><ul><ticketId2></ul></ul>

   The Above will be printed for each Ticket Id

<br>

<h3>Sample Input 👇</h3>

<h4>create_parking_lot 10</h4>


park_vehicle 657 RED
park_vehicle 897 Yellow
park_vehicle 432 blue
park_vehicle 123 greEn
park_vehicle 876 reD
park_vehicle 908 yelLoW
park_vehicle 543 bLuE
park_vehicle 231 gReeN
park_vehicle 765 rEd
park_vehicle 549 yEllOw

<br>

<h4>unpark_vehicle 657</h4>

<br>

display regNo_mapped_to_color gReen
display ticketId_mapped_to_regNo 765
display ticketIds_mapped_to_color rEd

<br>

<h4>exit</h4>


<h3>Sample Output</h3>

<br>

Parking Spot created with capacity of 10

<br>

Car having registration No. 657, color red and ticker Id 0_657 is parked on 2023/09/22 19:04:37 at Parking Spot 0 <br>
Car having registration No. 897, color yellow and ticker Id 1_897 is parked on 2023/09/22 19:04:40 at Parking Spot 1 <br>
Car having registration No. 432, color blue and ticker Id 2_432 is parked on 2023/09/22 19:04:44 at Parking Spot 2 <br>
Car having registration No. 123, color green and ticker Id 3_123 is parked on 2023/09/22 19:04:48 at Parking Spot 3 <br>
Car having registration No. 876, color red and ticker Id 4_876 is parked on 2023/09/22 19:04:52 at Parking Spot 4 <br>
Car having registration No. 908, color yellow and ticker Id 5_908 is parked on 2023/09/22 19:04:58 at Parking Spot 5 <br>
Car having registration No. 543, color blue and ticker Id 6_543 is parked on 2023/09/22 19:05:02 at Parking Spot 6 <br>
Car having registration No. 231, color green and ticker Id 7_231 is parked on 2023/09/22 19:05:06 at Parking Spot 7 <br>
Car having registration No. 765, color red and ticker Id 8_765 is parked on 2023/09/22 19:05:10 at Parking Spot 8 <br>
Car having registration No. 549, color yellow and ticker Id 9_549 is parked on 2023/09/22 19:05:23 at Parking Spot 9 <br>

<br>

The Car having registration No. 657 and color red is removed from parking spot 0

<br>

The Registration No of Car which belongs to the color green are -
123 
231

The Ticket Id of Car which has a Registration No 765 is 8_765

The Ticket Ids of Car which has the color red are -
4_876 
8_765
