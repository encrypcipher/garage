# Frank's garage
- Architecture <br>
![garage](https://user-images.githubusercontent.com/22782834/90341211-f3e7dc00-dffd-11ea-9a6c-63952fa61247.png)

# API Documentation
## Instructions to run


Step1: build the project : docker build -t garage-api-image .
Step2: Run the project : docker run --name garage-api-container -d -p 8080:8080 garage-api-image
- docker pull mongo
- docker run -d -p 27017-27019:27017-27019 --name mongodb mongo
