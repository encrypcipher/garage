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

 ## End points:
   Base URL: http://localhost:8080/search/api/v1 <br>
   Authentication: Basic<br>
   user name: user <br>
   password: password <br>
   
     API Operations:
  
  |No| Operation | Endpoint | Method |
|----|---|---|---|
|1| get warehouses with cars   |/warehouse | GET |
|2| get traffic results | /traffic | GET |
|3|  add car | /cars |POST |
|4|  update car | /cars |PUT |
|5|  delete car | /cars/{id} |DELETE |

## 1. get warehouses with cars
- URI: /warehouse
- Method: GET
<br>
Request Body : None

Response :
```
 [{
  	"_id": "1",
  	"name": "Warehouse A",
  	"location": {
  		"lat": "47.13111",
  		"long": "-61.54801"
  	},
  	"cars": {
  		"location": "West wing",
  		"vehicles": [{
  			"_id": 1,
  			"make": "Volkswagen",
  			"model": "Jetta III",
  			"year_model": 1995,
  			"price": 12947.52,
  			"licensed": true,
  			"date_added": "2018-09-18"
  		}]
  	}
  }]
```
## 2. get traffic results
- URI: /traffic
- Method: GET
<br>
Request Body

  |Attributes|Type|Validation | Required |
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
- URI: /cars/{id} eg: /cars/{id}
- Method: DELETE<br>

Response : none
