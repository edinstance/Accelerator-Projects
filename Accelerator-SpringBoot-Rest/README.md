# Accelerator-SpringBoot-Rest

A fully functioning Springboot RestAPI accelerator

## Running

You can either run the application locally or using Docker:

### Locally

To run locally you can either build a jar file using maven
and then run that, or you can run the application with maven.

#### Maven

```
mvn -f ./Application/pom.xml clean test spring-boot:run
```

#### Jar

```
mvn -f ./Application/pom.xml clean test package
```

### Docker

You can either run the application using the standalone image or
by using docker compose:

#### Docker Image

First move into the Application Directory

```
cd Application
```

Then build the image

```
docker build -t accelerator-springboot .
```

Finally, run it

```
docker run accelerator-springboot
```

#### Docker Compose

Run

```
docker-compose -f ./Application/docker-compose.yml up --build
```

## Tests

There are two test suites for this application,
first the Unit tests and then the Integration tests.
The Unit tests are part of the maven test lifecycle
but the integration tests must be run manually.

### Unit Tests

The Unit tests can be run by using these commands: 

```
mvn -f ./Application clean test
```

### Integration Tests

To run the Integration tests you must first be running the
application either locally or in a container. Then to run
the tests use these commands and change the port and url
depending on your deployment

First:

```
mvn -am compile
```

Then 

```
mvn test
```

### Dependency Checks

Dependency checks can be run using ```mvn verify```
it generally takes around 20 minutes to run and outputs ```dependency-check-report.html``` in the target folder

## Actuator

The Spring Actuator can be used to help monitor and manage the application. The config for the actuator is contained
within the [application.yml](application.yml) file. It shows examples of how to change the endpoint the actuator is
under and how to exclude features. The current enabled endpoints are the actuator and health endpoints, which are at
/details and /details/health

## API documentation

You can access the Swagger API documentations using the following link. To run the Swagger UI you must first be running
the
application either locally or in a container.

http://localhost:8080/accelerator-api-docs