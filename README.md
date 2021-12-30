# weather-checker-service
 Project to check weather based on different parameters for authorized users

<hr>

## About this project

- Allows users to register in the application providing username, password and email 
- Registered users can use their credentials to retrieve the the weather using the endpoint created by passing various parameters based on the need


Tech Stack:
-----------

- Java 11
- Springboot 2.6.2
- H2 DB
- Apache Maven 3.6.3
- EHCache
- Git 

<hr>

## Architecture
Application is developed as a single service both for user registration and weather retrival end points. 
User information is persisted in H2 in-memory DB. Weather retrival service talks to a thirparty api to retrive the weather information. 
EHCache is used to cache the data for 10 minutes.   

<hr>

## How to run this project locally

- Use STS or Command Line
	- import the project
	- run the command  `mvn clean install` 
	- run the command  `mvn spring-boot:run` 
	- service will be up in the port 8080


All the endpoints can be tested using the postman collection attached as no UI is implemented due to time constraints. You can change the parameter values to test different exception scenarios

Please ignore data quality in postman collection. 
   
#How to test
-------------------------------------
All the operations have to be tested using the postman collection attached as no UI is implemented due to time constraints.
Type information is fetched from a utility method.

Note: please ignore Order entity which is not being used
<hr>
  
  
  
  
#Current Implementation 
  -----------------------------------
  
Below are the main tasks covered in the code committed so far,

- REST end point implemented to register user 
- REST end point to retrive weather data
- Cache implementation using EHCache
- Validation and error handling 
- Versioning
- Unit Test Cases - partial
- Swagger - partial due to some library version dependency errors 
- Unit Test Cases - partial

<hr>
   
#Endpoints Implemented
  --------------------
- Register : http://localhost:8080/users/v1/register
  request body:
  {
    "name": "myname",
    "email": "myname@123.com",
    "password": "pwd"
  }

- Weather retrival 
	By City and Country - http://localhost:8080/api/v1/weather?type=by_city&city=London&country=uk
	By coordinates      - http://localhost:8080/api/v1/weather?type=by_geo&lat=35&lon=139
	
type parameter is used to distinguish the type of the query. valid values are by_city and by_geo
	

 <hr>
   

#Pending implementations / Roadmap
  -----------------------
  - Response has to be formatted correctly for weather api endpoint. Currently the result is as is received from third party endpoint.
  - Test cases for all the operations with all possible positive and negative cases. done only for user registration
  - Swagger implementation
  - Enhance Validation and exception handling for all the possible scenarios
  - Enhance Logging
  - Population of valid test data
  - password encryption
  - Log tracing
  - Monitoring capabilities
 <hr>
 