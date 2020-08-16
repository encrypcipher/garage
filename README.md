# Frank's garage
- Architecture <br>
![garage](https://user-images.githubusercontent.com/22782834/90341211-f3e7dc00-dffd-11ea-9a6c-63952fa61247.png)

# API Documentation
## Technology stack
  - Java 11
  - Spring Boot 2+
  - Basic Authentication
  - Maven Build
  - Environment: Embedded Tomcat
  - Deploymnet: Docker container
  
## Instructions to run
With Docker: <br>
   - Need to have git installed. Clone the project: git clone https://github.com/vishnuvuyyur1/garage.git
   - Need to have docker installed. <br>
   Run Database<br>
   - Step1: pull the image - docker pull mongo
   - Step2: Run the DB - docker run -d -p 27017-27019:27017-27019 --name mongodb mongo <br>
   Run API <br>
   - Command prompt: From insider the project folder garage-api
   - Step1: build the project : docker build -t garaga-api-image .
   - Step1: run the api: docker run --name garage-api-container -d -p 8080:8080 garage-api-image
   - Base URL: http://localhost:8080/garage/api/v1
   
## Approach:
- A microservice with its own database with a well defined set of functinatlity. And runs in a containerized environment.
- A reactive approach down to the databse to enable the API respond in a non-blocking and event based to improve performance.
- Used spring web flux to reactively interact with 3rd party apis, using this library enables our client to perform HTTP requests and providing asynchronous behaviour i.e the rest call need not wait till response comes back. Instead when there is a response, a notification will be provided.
- Reactive Mongo DB NoSQL database enabling a reactive interaction with DB, a nosql Db is document orientend enables storing of data together in documents, boosts horizonal scaling.
