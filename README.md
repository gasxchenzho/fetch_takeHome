# Fetch TakeHome Application

# Description
This is a Java Spring Boot application designed to process receipts. The main entities are Receipt and Item. 
A Receipt includes details such as retailer, purchase date, purchase time, items, and total. 
An Item includes a short description and a price.

The application offers the following endpoints:

POST /receipts/process: Submit a receipt for processing and returns the ID assigned to the receipt.
GET /receipts/{id}/points: Returns the points associated with a given receipt id.

# Setup & Installation
You must have docker in your computer

# Running the Application
run the command below 
docker run -d -p 8080:8080 fetch-take-home-container fetch-take-home

And you should be able to run the application in your computer, just use post or get http request to your local port
e.g localhost:8080/receipts/process and POST json payload you should get something like {"id": "7fb1377b-b223-49d9-a31a-5a02701dd310"}
or localhost:8080/receipts/{id you get from post}/points should give you something like {"points": 32}

