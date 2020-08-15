# garage

Step1: build the project : docker build -t garage-api-image .
Step2: Run the project : docker run --name garage-api-container -d -p 8080:8080 garage-api-image
- docker pull mongo
- docker run -d -p 27017-27019:27017-27019 --name mongodb mongo
