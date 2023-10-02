# RealNaut Rick & Morty 

## Table of Contents

- [Introduction](#introduction)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
    - [Clone the Repository](#clone-the-repository)
    - [Build and Run](#build-and-run)
- [Usage](#usage)

## Introduction

This project is an exercise focused on developing a REST/JSON API endpoint. The endpoint is designed to search for a specific Rick and Morty character by their exact name and return a list of episode names in which that character appears, regardless of their status or other attributes. Additionally, it retrieves the air date of the character's first appearance in the series.

The goal of this exercise is to demonstrate the implementation of a key feature in a RESTful API, showcasing how to retrieve and present relevant information from an external source based on user input. This project serves as a practical example for developers looking to understand how to create such endpoints and interact with external APIs effectively.

Feel free to explore the project, contribute, or use it as a reference for your own API development endeavors.

**Note:** This project leverages the GraphQL services provided by the official Rick and Morty API to fetch character and episode data. GraphQL offers a flexible and efficient way to request only the specific data needed for our API, making it a powerful tool for interacting with external APIs.


## Prerequisites

Ensure you have installed the following software:

- Java Development Kit (JDK) v17
- Gradle
- Git

## Getting Started

The following steps must be done once previous requisites have been achieved: 

### Clone the Repository

```bash
git clone https://github.com/yourusername/your-project.git
cd your-project
```

### Build the project

```bash
./gradlew build
```

### Run the Unit Tests

```bash
./gradlew test
```

### Run the application
```bash
./gradlew bootRun
```

## Usage
To use this service, you can make a GET request to the following endpoint:
- http://localhost:8080/search-character-appearance?name=Rick+Sanchez
### Parameters

- `name` (Required): The exact name of the character you want to search for. Be sure to URL encode the character's name if it contains spaces or special characters.

**Example Request:**

```http
GET http://localhost:8080/search-character-appearance?name=Rick+Sanchez
```

### Swagger
Swagger specification can be found in the following URL once the service is running:

```http
http://localhost:8080/swagger-ui/index.html
```