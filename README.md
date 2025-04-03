
# Hexagonal Architecture Price API

A demo web service using hexagonal architecture for an e-commerce company.

The service exposes a REST endpoint `/price` to check the best price of a product based on the given date.

## Endpoints

### [GET] /price

This endpoint allows you to retrieve the best price for a product based on the provided application date, product ID, and brand ID.

#### Query Parameters:
- `applicationDate`: The date you want to check the price for (in `yyyy-MM-dd-HH.mm.ss` format).
- `brandId`: The ID of the brand you want to check.
- `productId`: The ID of the product you want to check.

#### Example Request:
```
GET /price?applicationDate=2020-06-14-16:00:00&productId=35455&brandId=1
```

#### Example Response:
```json
{
  "productId": 35455,
  "brandId": 1,
  "priceList": 1,
  "startDate": "2020-06-14-00.00.00",
  "endDate": "2020-12-31-23.59.59",
  "price": 35.5
}
```

## Project Setup

### Build the Project

To build the project, run the following command in the project's root directory:

```bash
mvn clean install
```

### Run the Project

#### As a JAR file

After building the project, navigate to the `target` directory and run the following command:

```bash
java -jar price-0.0.1-SNAPSHOT.jar
```

Once the application is running, you can access the service at the following URL:

```
http://localhost:8080
```

#### Healthcheck

To check the status of the service, visit:

```
http://localhost:8080/actuator/health
```

This will be useful for:
- Service status monitoring
- Error recovery
- Dependent services running

### Browse the Database

You can access the H2 database console in your web browser at:

```
http://localhost:8080/h2-console
```

Use the connection data provided in the `application.properties` file to access the database.

### Browse the API Documentation

You can explore the API documentation via Swagger UI at:

```
http://localhost:8080/swagger-ui.html
```

## Technologies Used

- **Java 17**
- **Spring Boot 2.7.x**
- **H2 Database (in-memory)**
- **JUnit 5 for unit testing**
- **Mockito for mocking dependencies**
- **Maven for project management**

## Folder Structure

The project follows a hexagonal architecture structure, with the following directories:

- **`domain/`**: Contains the core business logic, including domain models and interfaces.
- **`exception/`**: Defines custom exceptions used throughout the application.
- **`model/`**: Contains the data transfer objects (DTOs) used in the service layer.
- **`port/`**: Contains the input and output ports (interfaces) for interacting with the service.
- **`service/`**: Contains the service implementation that orchestrates the domain logic.
- **`infrastructure/`**: Contains infrastructure-related code such as database and external service adapters.
- **`persistence/`**: Contains the repository layer, which interacts with the database.
- **`adapter/`**: Contains adapters that connect the domain to external systems (e.g., databases, APIs).
- **`entity/`**: Contains JPA entities used to map database tables.
- **`mapper/`**: Maps domain models to DTOs and vice versa.
- **`repository/`**: Contains the repository interfaces for database access.
- **`controller/`**: Contains REST controllers that expose the API endpoints.
