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
- Get Response from mock api data which has cars that are grouped under warehouses and aggregate the results of all warehouses by parallel processing to one custom CAR POJO.
- By utilizing the parallel processing of data using streams the performance is optimized.
- Reactive Mongo DB NoSQL database enabling a reactive interaction with DB, a nosql Db is document orientend enables storing of data together in documents.

 ## End points:
   3rd Party API : https://api.jsonbin.io/b/5ebe673947a2266b1478d892 <br>
   API Base URL: http://localhost:8080/search/api/v1 <br>
   Authentication: Basic<br>
   user name: user <br>
   password: password <br>
   
     API Operations:
  
  |No| Operation | Endpoint | Method |Phase|
|----|---|---|---|---|
|1| get warehouses  cars   |/warehouses | GET |phase 1|
|2| get traffic results | /traffic | GET |phase 2|
|3|  add car | /cars |POST |phase 3|
|4|  update car | /cars |PUT |phase 3|
|5|  delete car | /cars/{id} |DELETE |phase 3|

## 1. get warehouses cars
- URI: /warehouses
- Method: GET
<br>
Request Body : None

Response :
```
 [{
    "id":1,
    "year": 2002,
    "model": "swift",
    "make": "suzuki",
    "price": 1299.99
}]
  
```
## 2. get traffic results
- URI: /traffic
- Method: GET
<br>
Request Body

  |Attributes|Type|Allowed values | Required |
|----|---|---|---|
|status|string | 200, 4XX, 5XX, TOTAL| yes|
|trafficCountType|ENUM | MAX,MIN,AVERAGE| yes |

Response : count: Int

## 3. add car
- URI: /cars
- Method: Post
<br>
Request Body

  |Attributes|Type|Validation | Required |
|----|---|---|---|
|id|number | min 1 | yes|
|year|number | min 1500 | yes |
|make|string |any string|yes| 
|model|string| any string |yes |
|price|decimal number | min 1 |yes |
```
{
    "id":1,
    "year": 2002,
    "model": "swift",
    "make": "suzuki",
    "price": 1299.99
}
```
Response 

```
{
    "id":1,
    "year": 2002,
    "model": "swift",
    "make": "suzuki",
    "price": 1299.99
}
```

## 4. update car
- URI: /cars
- Method: Put
<br>
Request Body

  |Attributes|Type|Validation | Required |
|----|---|---|---|
|id|number | min 1 | yes|
|year|number | min 1500 | yes |
|make|string |any string|yes| 
|model|string| any string |yes |
|price|decimal number | min 1 |yes |
```
{
    "id":1,
    "year": 2002,
    "model": "swift",
    "make": "suzuki",
    "price": 4299.99
}
```
Response 

```
{
    "id":1,
    "year": 2002,
    "model": "swift",
    "make": "suzuki",
    "price": 4299.99
}
```
## 5. delete car
- URI: /cars/{id} eg: /cars/1
- Method: DELETE<br>

Response : none

# APP Documentation
## Technology stack
  - Angular 9
  - css
  - Bootstarp 4
  - Type Script
  - npm Build
  - Environment: Browsers (google chrome, firefox etc)
  - Deploymnet: Docker container
  
  ## Instructions to run
With Docker: <br>
   - Need to have git installed. Clone the project: git clone https://github.com/vishnuvuyyur1/garage.git
   - Need to have docker installed. <br>
   Run APP <br>
   - Command prompt: From insider the project folder garage-app
   - Step1: build the project : docker build -t garage-app-image .
   - Step1: run the api: docker run --name garage-app-container -d -p 4200:80 garage-app-image
   - Base URL: http://localhost:4200

## Approach:
- A single page application and pure component based approach.  And runs in a containerized environment.
- A service layer to interact with the API to fetch the results.
- Phase 1: Display of all cars: car-diplay components displays the cars from franks garage sorted baesd on date added
- Phase 2: show details of a car: car-details compnent displays the details of a particular car
- phase 3: shpping cart: shopping-car component is display the cars added by user to checkout 
- Includes navigation : when a user clicks on view details of a particular car we navigate to anoter route to display the results, and user go back to cars diplay. The reason for this mechanish is to fetch fresh load of cars every time a user navigates to cars display.
- Additional Futures: Search, sort, pagination, control to dispay no of cars 10,20,30 etc 

Results:<br>
- Phase 1<br>
![image](https://user-images.githubusercontent.com/22782834/90391992-5b4d6c80-e08e-11ea-88bd-0d6cf5f68bc5.png)
- phase 2
![image](https://user-images.githubusercontent.com/22782834/90392255-d020a680-e08e-11ea-92b1-26dd3dc1cfae.png)
- phase 3
![image](https://user-images.githubusercontent.com/22782834/90392322-edee0b80-e08e-11ea-9a72-9067dba930fb.png)
