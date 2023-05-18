# Fetch TakeHome Application

# Description
This is a Java Spring Boot application designed to process receipts. The main entities are Receipt and Item. 
A Receipt includes details such as retailer, purchase date, purchase time, items, and total. 
An Item includes a short description and a price.

The application offers the following endpoints:

POST /receipts/process: Submit a receipt for processing and returns the ID assigned to the receipt.
GET /receipts/{id}/points: Returns the points associated with a given receipt id.

# Setup & Installation
You must have docker in your computer, here the link to download https://www.docker.com/products/docker-desktop/
You must have java installed https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html
Ubuntu/Linux


To install Java 17, you can follow these steps according to your operating system:

## Ubuntu/Linux:
Open Terminal.

First, update the package index:

sudo apt update
Next, install the Java Development Kit (JDK). As of my knowledge cutoff in September 2021, OpenJDK 17 is the latest version. If it's available, you can install it with:

sudo apt install openjdk-17-jdk
Verify the installation with the following command:

java -version

## Windows
go to the website above and download java 17, To make sure Java is installed correctly, open Command Prompt and type:
java -version

You need to manually change the JAVA_HOME environment variable to point to the installation directory of the desired Java version. After changing JAVA_HOME, you should also update your system's PATH variable to include the bin directory of the new JAVA_HOME.

## MAC
download from the website above, Open the downloaded .dmg file and follow the installation steps.


# Running the Application
run the command below 
docker run -d -p 8080:8080 fetch-take-home-container fetch-take-home

And you should be able to run the application in your computer, just use post or get http request to your local port
e.g localhost:8080/receipts/process and POST json payload you should get something like {"id": "7fb1377b-b223-49d9-a31a-5a02701dd310"}
or localhost:8080/receipts/{id you get from post}/points should give you something like {"points": 32}

