FROM openjdk:8-jdk-alpine
ADD target/todolist.jar todolist.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","todolist.jar"]
