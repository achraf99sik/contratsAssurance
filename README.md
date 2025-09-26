# Contrats Assurance

## Project Description
Contrats Assurance is a Java application designed to manage insurance contracts, clients, advisors, and claims. It provides a structured way to handle various aspects of an insurance business, from client information to claim processing.

## Features
-   **Client Management**: Add, view, update, and delete client information.
-   **Advisor Management**: Manage details of insurance advisors.
-   **Contract Management**: Create, view, and modify insurance contracts, including different types of contracts.
-   **Claim Management**: Record and track insurance claims (sinistres) with various types.
-   **Database Integration**: Persistent storage of all data using PostgreSQL.

## Technologies Used
-   **Java**: The core programming language for the application.
-   **PostgreSQL**: Relational database for data storage.
-   **JDBC**: Java Database Connectivity for interacting with the PostgreSQL database.

## Project Structure
The project is organized into several key directories:

-   `src/`: Contains all the source code for the application.
    -   `controllers/`: Handles user input and interactions, acting as intermediaries between views and services.
    -   `dao/`: Data Access Objects, responsible for abstracting and encapsulating all access to the data source.
        -   `Implamentation/`: Concrete implementations of DAO interfaces.
        -   `Interfaces/`: Interfaces defining the contracts for DAO implementations.
    -   `database/`: Contains classes related to database connection and configuration.
    -   `enums/`: Defines enumeration types used throughout the application (e.g., `TypeContrat`, `TypeSinistre`).
    -   `models/`: Defines the data structures (Java classes) representing the entities in the application (e.g., `Client`, `Contrat`, `Sinistre`).
    -   `services/`: Business logic layer, orchestrating operations and applying business rules.
    -   `views/`: User interface components for displaying information and receiving input.
    -   `Main.java`: The entry point of the application.
-   `lib/`: Contains external libraries, such as the PostgreSQL JDBC driver.
-   `out/`: Compiled Java classes.
-   `artifacts/`: Contains the deployable JAR file (`contratsAssurance.jar`).
-   `run.sh`: A shell script to compile and run the application.

## Setup and Installation

### Prerequisites
-   Java Development Kit (JDK) 8 or higher
-   PostgreSQL database server

### Database Setup
1.  **Install PostgreSQL**: If you don't have PostgreSQL installed, download and install it from [https://www.postgresql.org/download/](https://www.postgresql.org/download/).
2.  **Create a Database**: Create a new database for the application (e.g., `contratsassurance_db`).
3.  **Configure Database Credentials**:
    The application connects to the database using credentials defined in `src/database/JdbcConfig.java`. You might need to adjust the database URL, username, and password in this file to match your PostgreSQL setup.

    ```java
    // Example from JdbcConfig.java (adjust as needed)
    private static final String URL = "jdbc:postgresql://localhost:5432/contratsassurance_db";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";
    ```
    (Note: This is a placeholder; you'll need to locate the actual `JdbcConfig.java` and make changes there.)

4.  **Create Tables**:
    ```sql
    CREATE DATABASE assurance;
    \c assurance

    CREATE TABLE conseillers (
        id UUID PRIMARY KEY,
        nom VARCHAR(100) NOT NULL,
        prenom VARCHAR(100) NOT NULL,
        email VARCHAR(150) UNIQUE NOT NULL
    );

    CREATE TABLE clients (
        id UUID PRIMARY KEY,
        nom VARCHAR(100) NOT NULL,
        prenom VARCHAR(100) NOT NULL,
        email VARCHAR(150) UNIQUE NOT NULL,
        conseiller_id UUID NOT NULL,
        FOREIGN KEY (conseiller_id) REFERENCES conseillers(id) ON DELETE CASCADE
    );

    CREATE TYPE type_contrat_enum AS ENUM ('AUTOMOBILE', 'IMMOBILIER', 'MALADIE');
    CREATE TABLE contrats (
        id UUID PRIMARY KEY,
        client_id UUID NOT NULL,
        type_contrat type_contrat_enum NOT NULL,
        date_debut DATE NOT NULL,
        date_fin DATE NOT NULL,
        FOREIGN KEY (client_id) REFERENCES clients(id) ON DELETE CASCADE
    );

    CREATE TYPE type_sinistre_enum AS ENUM ('ACCIDENT_DE_VOITURE', 'ACCIDENT_DE_MAISON', 'MALADIE');
    CREATE TABLE sinistres (
        id UUID PRIMARY KEY,
        contrat_id UUID NOT NULL,
        type_sinistre type_sinistre_enum NOT NULL,
        description TEXT,
        cout DOUBLE PRECISION NOT NULL,
        date_time TIMESTAMP NOT NULL,
        FOREIGN KEY (contrat_id) REFERENCES contrats(id) ON DELETE CASCADE
    );
    ```

### Running the Application

1.  **Clone the Repository**:
    ```bash
    git clone https://github.com/your-username/contratsAssurance.git
    cd contratsAssurance
    ```
    (Note: Replace `https://github.com/your-username/contratsAssurance.git` with the actual repository URL if available, or omit if cloning from local.)

2.  **Compile and Run**:
    Use the provided `run.sh` script to compile and execute the application:
    ```bash
    ./run.sh
    ```
    Alternatively, you can manually compile and run:
    ```bash
    javac -d out $(find src -name "*.java")
    java -cp "lib/*;out" Main
    ```

## Usage
Upon running the application, you will be presented with a main menu. Follow the on-screen prompts to navigate through different functionalities like managing clients, contracts, advisors, and claims.

## License
This project is licensed under the MIT License. See the `LICENSE` file for details (if available).