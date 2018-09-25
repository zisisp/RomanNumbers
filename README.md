[![Build Status](https://travis-ci.org/zisisp/RentalCars.svg?branch=master)](https://travis-ci.org/zisisp/RomanNumber)

This is a Java Spring Boot service that translates Roman numbers to Arabic (modern numbers) numbers and vice versa
## How to run

with maven:
```
mvn spring-boot:run
```

or run 
```
mvn clean package
```
and then 
```
java -jar target/RomanNumbers-1.0.jar
```


## Rest services
The REST services are:

[http://localhost:8080/parse?roman=V](http://localhost:8080/parse?roman=V)

[http://localhost:8080/generate?number=5](http://localhost:8080/generate?number=5)
