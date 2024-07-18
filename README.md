# Coding Challenge

## My Comments:
The coding challenge was a relatively simple but fun springboot usecase. I found it fun because although I've been working with relational DBs for 5 years(Mostly Oracle), I had never worked with MongoDB or Mongo Repositories before, so it was both a way to flex what I do know with regards to 
springboot and java in general, but also to learn at least a little bit of a new DB technology. I treated this as I would an agile story with the instructions included as acceptance criteria. I also included a postman collection for easy testing.

## Problem 1
For problem 1, I used a recursive solution that counts up all reports in the list, and feeds back into the function any reports that have a list of direct reports themselves, continuing to recurse until a node with all direct reports not having direct reports is found. 
One issue I faced is that direct reports were represented by EmployeeID only. This meant that the ID would need to be fed into employeeRepository in order to get the list of reports. My solution to this was to create a function that calculated the number of reports in the serviceImpl class. 
I would have preferred to write it as a member function of the ReportingStructure class, but due to  either my own inexperience with MongoDB, or a technical limitation, I could not find a way to make employeeRepository work from the member class. 
If there is a way to so, I would be curious to learn it. The endpoint created is employee/{id}/reporting. This can be tested using either REPORTINGSTRUCTURE request inside of the postman collection.

## Problem 2
For problem 2, there was a bit more thought that I had to put into the design, largely because compensation needed to both persist, but also contain an Employee object as part of it's data. As a result the solution I went with was to stand up a second mongo repository, with the key-object pairing
being done on the employee ID. This way, if there was ever a need to compare it back to the original employee table, it could be done using ID as a common key. Additionally, I created a compensationRequest type for ease of data entry on the postman side, and ease of processing on the java side. 
I was unsure as to whether "effectiveDate" needed to be a parameter taken in or an "at the instant of request" value. I set it as a parameter because I figured payroll probably isn't updated at the exact time someone makes a REST request. 
The endpoint serving this problem is employee/{id}/compensation, This can be tested using CREATE COMP and VIEW COMP requests in the postman collection.




## What's Provided
A simple [Spring Boot](https://projects.spring.io/spring-boot/) web application has been created and bootstrapped 
with data. The application contains information about all employees at a company. On application start-up, an in-memory 
Mongo database is bootstrapped with a serialized snapshot of the database. While the application runs, the data may be
accessed and mutated in the database without impacting the snapshot.

### How to Run
The application may be executed by running `gradlew bootRun`.

### How to Use
The following endpoints are available to use:
```
* CREATE
    * HTTP Method: POST 
    * URL: localhost:8080/employee
    * PAYLOAD: Employee
    * RESPONSE: Employee
* READ
    * HTTP Method: GET 
    * URL: localhost:8080/employee/{id}
    * RESPONSE: Employee
* UPDATE
    * HTTP Method: PUT 
    * URL: localhost:8080/employee/{id}
    * PAYLOAD: Employee
    * RESPONSE: Employee
```
The Employee has a JSON schema of:
```json
{
  "type":"Employee",
  "properties": {
    "employeeId": {
      "type": "string"
    },
    "firstName": {
      "type": "string"
    },
    "lastName": {
          "type": "string"
    },
    "position": {
          "type": "string"
    },
    "department": {
          "type": "string"
    },
    "directReports": {
      "type": "array",
      "items" : "string"
    }
  }
}
```
For all endpoints that require an "id" in the URL, this is the "employeeId" field.

## What to Implement
Clone or download the repository, do not fork it.

### Task 1
Create a new type, ReportingStructure, that has two properties: employee and numberOfReports.

For the field "numberOfReports", this should equal the total number of reports under a given employee. The number of 
reports is determined to be the number of directReports for an employee and all of their distinct reports. For example, 
given the following employee structure:
```
                    John Lennon
                /               \
         Paul McCartney         Ringo Starr
                               /        \
                          Pete Best     George Harrison
```
The numberOfReports for employee John Lennon (employeeId: 16a596ae-edd3-4847-99fe-c4518e82c86f) would be equal to 4. 

This new type should have a new REST endpoint created for it. This new endpoint should accept an employeeId and return 
the fully filled out ReportingStructure for the specified employeeId. The values should be computed on the fly and will 
not be persisted.

### Task 2
Create a new type, Compensation. A Compensation has the following fields: employee, salary, and effectiveDate. Create 
two new Compensation REST endpoints. One to create and one to read by employeeId. These should persist and query the 
Compensation from the persistence layer.

## Delivery
Please upload your results to a publicly accessible Git repo. Free ones are provided by Github and Bitbucket.
