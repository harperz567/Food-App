# Food Delivery Microservices Application

A full-stack food delivery application built with microservices architecture, featuring Angular frontend and Spring Boot backend services.

## 🍕 Project Overview

This application provides a complete food delivery experience with restaurant listings, food catalog browsing, order management, and payment processing. The system is built using modern microservices architecture for scalability and maintainability.

## 🏗️ Architecture

### Frontend
- **Framework**: Angular 19.2
- **Features**: Restaurant listings, Food catalog, Order management, Payment processing
- **Styling**: Component-based CSS
- **Routing**: Angular Router with lazy loading modules

### Backend Microservices
- **Framework**: Spring Boot 3.3.7
- **Java Version**: 21
- **Service Registry**: Netflix Eureka
- **Database**: MySQL + MongoDB (hybrid approach)
- **Architecture Pattern**: Microservices with Spring Cloud

## 📋 Services Overview

| Service | Port | Description |
|---------|------|-------------|
| **eureka** | 8761 | Service discovery and registration |
| **restaurant-listing** | 8080 | Manages restaurant information |
| **food-catalog** | 8081 | Handles food items and menus |
| **order** | 8082 | Order processing and management |
| **payment** | 8083 | Payment processing service |
| **userinfo** | 8084 | User information management |
| **food-app (frontend)** | 4200 | Angular frontend application |

## 🛠️ Technology Stack

### Frontend
- Angular 19.2
- TypeScript 5.7
- RxJS 7.8
- Angular SSR (Server-Side Rendering)
- Express.js (for SSR)

### Backend
- Spring Boot 3.3.7
- Spring Cloud 2023.0.4
- Spring Data JPA + Spring Data MongoDB
- Netflix Eureka
- MySQL Database (restaurant, food-catalog, payment, userinfo services)
- MongoDB (order service)
- MapStruct 1.5.5 (for object mapping)
- Lombok (for reducing boilerplate code)

## 🚀 Getting Started

### Prerequisites
- Node.js 18+ and npm
- Java 21
- MySQL 8.0+
- MongoDB 4.4+
- Maven 3.6+

### Database Setup

**MySQL Setup** (for restaurant-listing, food-catalog, payment, userinfo services):
```sql
-- Create databases
CREATE DATABASE restaurant_db;
CREATE DATABASE food_catalog_db; 
CREATE DATABASE payment_db;
CREATE DATABASE userinfo_db;

-- Create user (optional)
CREATE USER 'food_app_user'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON restaurant_db.* TO 'food_app_user'@'localhost';
GRANT ALL PRIVILEGES ON food_catalog_db.* TO 'food_app_user'@'localhost';
GRANT ALL PRIVILEGES ON payment_db.* TO 'food_app_user'@'localhost';
GRANT ALL PRIVILEGES ON userinfo_db.* TO 'food_app_user'@'localhost';
FLUSH PRIVILEGES;
```

**MongoDB Setup** (for order service):
```bash
# Start MongoDB service
sudo systemctl start mongod

# Create database (will be created automatically when first document is inserted)
# Database name: order_db
```

### Running the Application

**1. Start Eureka Server (Service Discovery)**
```bash
cd eureka
mvn spring-boot:run
```
Access Eureka Dashboard: http://localhost:8761

**2. Start Backend Services**

Start services in this order (wait for each to register with Eureka):

```bash
# Restaurant Service
cd restaurant-listing
mvn spring-boot:run

# Food Catalog Service  
cd food-catalog
mvn spring-boot:run

# User Info Service
cd userinfo
mvn spring-boot:run

# Order Service (uses MongoDB)
cd order  
mvn spring-boot:run

# Payment Service
cd payment
mvn spring-boot:run
```

**3. Start Frontend Application**
```bash
cd food-app
npm install
npm start
```

Access the application: http://localhost:4200

## 📁 Project Structure

```
food-delivery-app/
├── food-app/                    # Angular Frontend
│   ├── src/app/
│   │   ├── restaurant-listing/  # Restaurant browsing
│   │   ├── food-catalog/        # Food items catalog
│   │   ├── order-summary/       # Order management
│   │   ├── payment/             # Payment processing
│   │   └── shared/              # Shared models and utilities
│   └── public/                  # Static assets
├── eureka/                      # Service Discovery Server
├── restaurant-listing/          # Restaurant Management Service (MySQL)
├── food-catalog/               # Food Catalog Service (MySQL)
├── order/                      # Order Management Service (MongoDB)
├── payment/                    # Payment Processing Service (MySQL)
└── userinfo/                   # User Information Service (MySQL)
```

## 🔧 Configuration

Each service can be configured through `application.yml` files with different profiles:
- `application-local.yml` - Local development
- `application-dev.yml` - Development environment

### Service Ports
- Eureka Server: 8761
- Restaurant Listing: 8080
- Food Catalog: 8081
- Order Service: 8082
- Payment Service: 8083
- User Info Service: 8084
- Frontend: 4200

## 🐳 Docker Support

Most services include `Dockerfile` for containerization. To build and run with Docker:

```bash
# Build individual service
cd [service-name]
docker build -t food-app-[service-name] .

# Run with Docker Compose (if docker-compose.yml exists)
docker-compose up
```

## 🧪 Testing

**Backend Services:**
```bash
cd [service-name]
mvn test
```

**Frontend:**
```bash
cd food-app
npm test
```

## 📚 API Documentation

The application exposes RESTful APIs for each service:

- **Restaurant Service**: `http://localhost:8080/api/restaurants`
- **Food Catalog Service**: `http://localhost:8081/api/food-catalog`
- **Order Service**: `http://localhost:8082/api/orders`
- **Payment Service**: `http://localhost:8083/api/payments`
- **User Service**: `http://localhost:8084/api/users`

## 🔍 Service Discovery

All services are registered with Eureka server. You can monitor service health and status at:
`http://localhost:8761`

## 🏗️ Architecture Highlights

- **Microservices Architecture**: Each service is independently deployable
- **Service Discovery**: Automatic service registration and discovery with Eureka
- **Database Per Service**: Each service has its own database (MySQL/MongoDB)
- **Angular Modules**: Feature-based modular frontend architecture
- **RESTful APIs**: Clean API design following REST principles
- **Docker Ready**: Containerization support for easy deployment

## 📝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🤝 Contact

For any questions or suggestions, please feel free to reach out!