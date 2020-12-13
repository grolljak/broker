# Device configuration and Message Routing

![Concept diagram](https://https://github.com/grolljak/broker/blob/master/concept_broker.pdf?raw=true)

This project is a simple Spring Boot application that simulates the core logic of a message
broker. The Message Center allows the registration of multiple Message Devices, which can
send and receive messages containing plain text. Message Devices can also be assigned (and
unassigned) to Device Groups. Messages can be sent as point-to-point messages for a specific
device, as multicast messages for a specific group, or as broadcast messages for all devices.

## Logging
A list of alert keywords can be entered into the system. Any message that contains one or more
keyword in its plain-text content is logged together with the identified keywords and all recipients.

### How to run the application

Application runs under 'mssql' profile, requiring a docker image. There is a docker-compose file located in the 
project setup folder for that purpose.
                
```
cd setup
docker-compose up
```
Note: You can use Hibernate ddl-auto property to create a schema for you once your db is running.

When the database is set up, you can run the application under 'mssql' profile. It is running on the default port 8080.

### How to use the application
Application provides REST API to allow basic interaction with the application. In order to easily use the API, 
there is exported postman collection that contains all available endpoints.

Another way how to operate the application is through IntegrationTest.java, located in the de/eurofunk/broker package.