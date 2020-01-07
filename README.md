# Secure TodoList

Todo list haven't got existing vulnerability

Cloud IP (site adress) 

http://46.101.193.234:8087

H2 - Database Adress

http://46.101.193.234:8087/h2-console

H2 console a girişten sonra

Saves setting : Generic H2(embedded) 

JDBC url : jdbc:h2:mem:testdb

Username :sa

password boş olcak

# FINAL PROJECT UPDATE !
-OPA control mechanism is added
-Admin page is added


## Getting Started


### How to Run

#### Build Spring Boot Project with Maven
To be able to run your Spring Boot app you will need to first build it. To build and package a Spring Boot app into a single executable Jar file with a Maven, use the below command. You will need to run it from the project folder which contains the pom.xml file.
  ```
  maven package
  ```
  or you can also use
  ```
  mvn install
  ```
  
#### Run Spring Boot app with java -jar command
To run your Spring Boot app from a command line in a Terminal window you can you the java -jar command. This is provided your Spring Boot app was packaged as an executable jar file.  

 ```
 java -jar target/mywebserviceapp-0.0.1-SNAPSHOT.jar
 ```

#### Run Spring Boot app using Maven
You can also use Maven plugin to run your Spring Boot app. Use the below example to run your Spring Boot app with Maven plugin
  
  ```
  mvn spring-boot:run
  ```
  
  for windows
  ```
  ./mvnw spring-boot:run
  ```
