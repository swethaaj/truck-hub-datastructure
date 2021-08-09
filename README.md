# Take Home Engineering Challenge

Commercial Software Engineering is a very practical team at Microsoft and this extends to the way that we work with you to find out if this team is a great fit for you. We want you to come away with a great understanding of the work that we actually do day to day and what it is like to work with us.

So instead of coding at a whiteboard with someone watching over your shoulder under high pressure, which is not a thing we often do, we instead discuss code that you have written previously when we meet.

## Guidelines

-   This is meant to be an assignment that you spend approximately three hours of dedicated, focused work. Do not feel like you need to overengineer the solution with dozens of hours to impress us. Be biased toward quality over quantity, simplicity over complexity.

-   Think of this like an open source project. Create a repo on Github, use git for source control, and use README.md to document what you built for the newcomer to your project.

-   Our team builds, alongside our customers and partners, systems engineered to run in production. Given this, please organize, design, test, deploy, and document your solution as if you were going to put into production. We completely understand this might mean you can't do as much in the time budget. Be biased for production-ready over features.

-   Think out loud in your submission's documentation. Document tradeoffs, the rationale behind your technical choices, or things you would do or do differently if you were able to spend more time on the project or do it again.

-   Our team meets our customers where they are in terms of software engineering platforms, frameworks, tools, and languages. This means you have wide latitude to make choices that express the best solution to the problem given your knowledge and favorite tools. Make sure to document how to get started with your solution in terms of setup.

## The Problem

Our San Francisco team loves to eat. They are also a team that loves variety, so they also like to discover new places to eat.

In fact, we have a particular affection for food trucks. One of the great things about Food Trucks in San Francisco is that the city releases a list of them as open data.

Your assignment is to make it possible for us to find a food truck no matter where our work takes us in the city.

Feel free to tackle this problem in a way that demonstrates your expertise of an area -- or takes you out of your comfort zone.

## Technical Requirements

### Interface

You can write a simple REST service that returns a set of food trucks (our team is fluent in JSON).

### Expected Data Size

Design the solution assuming that the dataset includes data from many cities with millions of records.

### Data Schema

San Francisco's food truck open dataset is [located here](https://data.sfgov.org/Economy-and-Community/Mobile-Food-Facility-Permit/rqzj-sfat/data) and there is an endpoint with a [CSV dump of the latest data here](https://data.sfgov.org/api/views/rqzj-sfat/rows.csv). We've included a [copy of this data](./Mobile_Food_Facility_Permit.csv) in this repo as well.

### Programming Language 

You are welcome to use any language frameworks or libraries you like. 

### Data Storage

You don’t need to use a database to store food truck data. Instead, your REST Service should use data structures to implement an in-memory data store.

### Service Requirements

Your REST service should make it possible to:

- Add a new food truck.
- Retrieve a food truck based on the `locationid` field.
- Get all food trucks for a given `block`.

### Testing

You are welcome to use your unit testing framework of choice to validate the in-memory data store and service functionality.

Good luck! Please send a link to your solution on Github back to us at least 12 hours before your interview so we can review it before we speak.


## 			**************** IMPLEMENTATION DETAILS: *******************

The above problem is solved using TRUCKHUB Application. 

-	TRUCKHUB is an Application implemented using Java in SpringBoot Framework.
-	The EndPoint URL to access the Application when application is up: `http://localhost:9090/`.


-	Application uses Spring Boot as its light weight and comes with its own MVC controller which is used to deploy the application.
-	Data Structures(HashMap and HashSet) are used to store the data in memory.
-	Spring Batch process is used to load the data in chunks, as data can be large.
-	All the above are added as part of Maven dependencies in pom.xml file.



-	Rest Services are created for each feature.
-	Test cases are written in Junits to test the API.
-	Integration testing is performed using local host and Postman to test the REST services.



# Input to Application:
	
-	The Application takes in the input CSV file (`Mobile_Food_Facility_Permit.csv`) 
	and loads the data to the in memory data structures which is then used later for search services.
	
# Configuration Details:

	APPLICATION.PROPERTIES
	Below are the configurable parameters
	
-	(chunkSize =  100)
	Since this CSV file can be huge, the Load() is a Spring Batch process which loads the data in chunks of 100 records.


-	(spring.batch.job.enabled=true)
	The Batch Process Job is ENABLED by Default to Load the data from CSV file  when the Application starts.
	
	
-	Port(9090), Input File Location are all configurable parameters

-	input=file:C:/Users/Swetha/eclipse-workspace/take-home-engineering-challenge-boot/src/main/resources/Mobile_Food_Facility_Permit.csv
	This needs to be changed accordingly to refer to your absulute Path


# REST SERVICES:

-	load()			: API to load the data from CSV file to Cache.

-	getByID()		: API to get the truckInfo by Location ID.

-	getByBlock()	: API to get the truckInfo by Block.

-	addbyTruckInfo(): API to Insert the records to the Cache.

-	getByLocationIdAndTruckType()	:Enhancement which can be done later.

- 
# Endpoint Details:
	
-	`http://localhost:9090`  To Access Application


-	`http://localhost:9090/truckhub/truckInfo/load`  Load Service to load the data from CSV file and stores it in internal memory datastructures.


-	`http://localhost:9090/truckhub/truckInfo/locationId/{id}`   Service to get the Truck Info by Location ID


-	`http://localhost:9090/truckhub/truckInfo/block/{block}`   Service to get the Truck Info by Block


# Steps to Run the Application:

-	Starting point of the Application is `TruckHubSpringBootApp.java` 
	Run -> RunAs -> JavaApplication will bring up the
	application on the local port (9090) which is configured in the application.properties.
	
	
-	As Part of the  above step, App loads the data from CSV file  through a Batch process.


# Details about the APP implementation:

	# Package: src.main.java
	
-	Starting point to the SpringBoot App is through @SpringBootApplication(TruckHubSpringBootApp.java).This java file has a main method which will be called as starting 	         point.


-	SpringBatchConfig.java is a configuration file used for Bean creation for the Batch process.
	It  Creates the JOB, FileItemReader , and the LineMapper Beans required for the Spring Batch Process. These beans are later used by JobLauncher in the controller file to 	launch the Batch Process Job.
	

-	TruckHubCache.java is Cache java class which exposes the public Api getMapByLocationID() and getMapByBlock() 
    and the Processor.java is used to process any data changes needed before loading it.
	
	Data is stored in cache in 2 maps as below 
	
	Map<Integer, TruckInfo> mapByLocationID;  which stores the one to one relation from locationID to truckInfo entity.
	
	Map<String, Set<Integer>> mapByBlock;  which stores one to Many relationship from Block to LocationId.
	
	LocationID is considered to be the primary key of the truckInfo object.
	
	Data is stored this ways so its easy during retrieval process.
	HasetSets are used as insertion and lookup up times are constant vs ArrayList which could be linear.

	
-	TruckHubController.java is the Controller class which host the REST APIs. The beans are AutoWired to the controller.
	
	For the getByLOcationID service, this controller queries the data from the mapByLocationID and retrieves the data.
	
	For the getByBlock service, the controller queries the data from mapByBlock and gets all the locationIds for that block and iterats obver this locationID to get the 	  	  truckInfo from mapByLocationID Map.

-	TruckInfo.java	is the Entity File for the each Truck.


	Package: src.test.java
	
-	All the JUint test files are in the respective test files.
	TruckHubControllerTests is the main file.


	Package: src.main.resources
	
	
-	application.properties  : File used to configuration details


-	application-test.properties	: File used for Junits.


-	.csv files		: Input files used.

	

# Test cases using local host or postman:

	Once the Application is loaded:
-	http://localhost:9090/
	
	
# POSTMAN:
	
# Service:load()
	
-	INPUT: http://localhost:9090/truckhub/truckInfo/load   Not needed to run explicitly as this service is Enabled by default by application

# Service:getByID()
	
-	Input:	GET
		http://localhost:9090/truckhub/truckInfo/locationId/1524388

	
-	Output:
		{"locationid":"1524388","Applicant":"Flavors of Africa","FacilityType":"Truck","LocationDescription":"MISSION ST: SHAW ALY to ANTHONY ST (543 - 586)","Address":"555 MISSION ST","blocklot":"3721120","block":"3721","lot":"120","permit":"21MFF-00068","Status":"APPROVED","FoodItems":"Meat and vegi rice bowls: meat and vegi salad bowls: meat and vegi wraps: drinks and juices.","Location":"(37.78844615690132, -122.3986412420388)","ZipCodes":null,"location":"(37.78844615690132, -122.3986412420388)","address":"555 MISSION ST","status":"APPROVED","foodItems":"Meat and vegi rice bowls: meat and vegi salad bowls: meat and vegi wraps: drinks and juices.","facilityType":"Truck","applicant":"Flavors of Africa","zipCodes":null,"locationDescription":"MISSION ST: SHAW ALY to ANTHONY ST (543 - 586)"}


# Service: getByBlock()

-	Input:	GET
		http://localhost:9090/truckhub/truckInfo/block/3721

-	Output:
		[{"locationid":"1524388","Applicant":"Flavors of Africa","FacilityType":"Truck","LocationDescription":"MISSION ST: SHAW ALY to ANTHONY ST (543 - 586)","Address":"555 MISSION ST","blocklot":"3721120","block":"3721","lot":"120","permit":"21MFF-00068","Status":"APPROVED","FoodItems":"Meat and vegi rice bowls: meat and vegi salad bowls: meat and vegi wraps: drinks and juices.","Location":"(37.78844615690132, -122.3986412420388)","ZipCodes":null,"location":"(37.78844615690132, -122.3986412420388)","address":"555 MISSION ST","status":"APPROVED","foodItems":"Meat and vegi rice bowls: meat and vegi salad bowls: meat and vegi wraps: drinks and juices.","facilityType":"Truck","applicant":"Flavors of Africa","zipCodes":null,"locationDescription":"MISSION ST: SHAW ALY to ANTHONY ST (543 - 586)"},{"locationid":"1408986","Applicant":"MOMO INNOVATION LLC","FacilityType":"Truck","LocationDescription":"MISSION ST: SHAW ALY to ANTHONY ST (543 - 586)","Address":"555 MISSION ST","blocklot":"3721120","block":"3721","lot":"120","permit":"19MFF-00131","Status":"APPROVED","FoodItems":"Noodles","Location":"(37.78844615690132, -122.3986412420388)","ZipCodes":null,"location":"(37.78844615690132, -122.3986412420388)","address":"555 MISSION ST","status":"APPROVED","foodItems":"Noodles","facilityType":"Truck","applicant":"MOMO INNOVATION LLC","zipCodes":null,"locationDescription":"MISSION ST: SHAW ALY to ANTHONY ST (543 - 586)"},{"locationid":"1534033","Applicant":"Off the Grid Services, LLC","FacilityType":"","LocationDescription":"MINNA ST: SHAW ALY to 02ND ST (44 - 99)","Address":"65 MINNA ST","blocklot":"3721124","block":"3721","lot":"124","permit":"21MFF-00073","Status":"REQUESTED","FoodItems":"N/A","Location":"(37.7879095795372, -122.39836076940526)","ZipCodes":null,"location":"(37.7879095795372, -122.39836076940526)","address":"65 MINNA ST","status":"REQUESTED","foodItems":"N/A","facilityType":"","applicant":"Off the Grid Services, LLC","zipCodes":null,"locationDescription":"MINNA ST: SHAW ALY to 02ND ST (44 - 99)"},{"locationid":"1509790","Applicant":"Plaza Garibaldy","FacilityType":"Truck","LocationDescription":"HOWARD ST: 01ST ST to MALDEN ALY (500 - 589)","Address":"540 HOWARD ST","blocklot":"3721015","block":"3721","lot":"15","permit":"21MFF-00013","Status":"APPROVED","FoodItems":"Tacos: burritos: quesadillas","Location":"(37.78795495968584, -122.39723654373122)","ZipCodes":null,"location":"(37.78795495968584, -122.39723654373122)","address":"540 HOWARD ST","status":"APPROVED","foodItems":"Tacos: burritos: quesadillas","facilityType":"Truck","applicant":"Plaza Garibaldy","zipCodes":null,"locationDescription":"HOWARD ST: 01ST ST to MALDEN ALY (500 - 589)"},{"locationid":"1535610","Applicant":"Wu Wei LLC dba MoBowl","FacilityType":"Truck","LocationDescription":"HOWARD ST: 01ST ST to MALDEN ALY (500 - 589)","Address":"500 HOWARD ST","blocklot":"3721011","block":"3721","lot":"11","permit":"21MFF-00076","Status":"APPROVED","FoodItems":"Various types of meat: veggie: and seafood bowls.","Location":"(0.0, 0.0)","ZipCodes":"","location":"(0.0, 0.0)","address":"500 HOWARD ST","status":"APPROVED","foodItems":"Various types of meat: veggie: and seafood bowls.","facilityType":"Truck","applicant":"Wu Wei LLC dba MoBowl","zipCodes":"","locationDescription":"HOWARD ST: 01ST ST to MALDEN ALY (500 - 589)"},{"locationid":"1367290","Applicant":"Star Taco","FacilityType":"","LocationDescription":"HOWARD ST: MALDEN ALY to 02ND ST (574 - 599)","Address":"580 HOWARD ST","blocklot":"3721092","block":"3721","lot":"92","permit":"19MFF-00128","Status":"REQUESTED","FoodItems":"Mexican Food","Location":"(37.7873042488646, -122.39803725191237)","ZipCodes":null,"location":"(37.7873042488646, -122.39803725191237)","address":"580 HOWARD ST","status":"REQUESTED","foodItems":"Mexican Food","facilityType":"","applicant":"Star Taco","zipCodes":null,"locationDescription":"HOWARD ST: MALDEN ALY to 02ND ST (574 - 599)"},{"locationid":"1447794","Applicant":"Street Meet","FacilityType":"Truck","LocationDescription":"HOWARD ST: 01ST ST to MALDEN ALY (500 - 589)","Address":"564 HOWARD ST","blocklot":"3721019","block":"3721","lot":"19","permit":"20MFF-00007","Status":"REQUESTED","FoodItems":"Tortas: Burritos: Tacos: Churros: Nachos: Asada Fries","Location":"(37.787539893467496, -122.39772670915164)","ZipCodes":null,"location":"(37.787539893467496, -122.39772670915164)","address":"564 HOWARD ST","status":"REQUESTED","foodItems":"Tortas: Burritos: Tacos: Churros: Nachos: Asada Fries","facilityType":"Truck","applicant":"Street Meet","zipCodes":null,"locationDescription":"HOWARD ST: 01ST ST to MALDEN ALY (500 - 589)"}]


# Service: addbyTruckInfo()

-	INPUT: POST
		http://localhost:9090/truckhub/truckInfo/add
		RequestBody - JSON

		{
    		"locationid": "222",
    		"Applicant": "Flavors of Africa",
    		"FacilityType": "Truck",
    		"LocationDescription": "MISSION ST: SHAW ALY to ANTHONY ST (543 - 586)",
    		"Address": "560 MISSION ST",
    		"blocklot": "3708095",
    		"block": "3708",
    		"lot": "95",
    		"permit": "21MFF-00068",
    		"Status": "APPROVED",
    		"FoodItems": "Meat and vegi rice bowls: meat and vegi salad bowls: meat and vegi wraps: drinks and juices.",
   		 "Location": "(37.78886471534304, -122.39935935136297)",
   		 "ZipCodes": null,
   		 "location": "(37.78886471534304, -122.39935935136297)",
   		 "address": "560 MISSION ST",
   		 "status": "APPROVED",
   		 "facilityType": "Truck",
   		 "foodItems": "Meat and vegi rice bowls: meat and vegi salad bowls: meat and vegi wraps: drinks and juices.",
   		 "applicant": "Flavors of Africa",
  		  "zipCodes": null,
  		  "locationDescription": "MISSION ST: SHAW ALY to ANTHONY ST (543 - 586)"
		}


-	OUTPUT:
		222
		
		
		
		
# Enhancements/Changes that could be done:
	

-	Python script or some other way to initially clean the CSV file automatically.Get the clarifications  on the exact columns data the user might need and use that as part 	of Application


-	Add more test cases which could be integration test cases, which takes in CSV file as input and runs the services, no need to use post man in that case.


- 	Add the security features such as tructkeyStore, AAF( Authentication Implementation) for the Clients to use this.
	

-	Deploying it in containers(Kubernetes/docker image) for security purposes.


-	Have a data base for the storing the records especially the add operation which does add it to the Map memory but will be lost when the application goes down.
	
	

# Things I would have liked to clarify if I had gotten a chance
	
- 	 Get more specific details about the columns that clients would like to see as part of response.
	


	
	

	
	


	






















