# REST Assured API Automation Framework

## Overview

This project is an API automation framework built using:

* Java
* REST Assured
* Cucumber (BDD)
* Maven
* TestNG
* Cucumber HTML Reports

The framework is designed to automate API testing using Behavior-Driven Development (BDD) principles, making test scenarios easy to read, maintain, and execute.

---

## Project Structure

```text
project-root
│
├── src
│   ├── test
│   │   ├── java
│   │   │   ├── runners
│   │   │   ├── stepDefinitions
│   │   │   ├── feature
|   |   |   ├── api
│   │   │   └── utils
│   │   │
│   │   └── resources
│   │       └── config
│
├── target
│   └── cucumber-reports
│
├── pom.xml
└── README.md
```

---

## Prerequisites

Before running the automation framework, install the following software:

### 1. Java

Install Java JDK 11 or above.

Verify installation:

```bash
java -version
javac -version
```

Expected output:

```text
java version "11.x.x"
javac 11.x.x
```

### 2. Maven

Install Maven and verify installation:

```bash
mvn -version
```

Expected output:

```text
Apache Maven 3.x.x
Java version: 11.x.x
```

### 3. IDE

Recommended IDEs:

* IntelliJ IDEA
* Eclipse
* VS Code

---

## Setup

### Clone Repository

```bash
git clone <repository-url>
```

Navigate to project directory:

```bash
cd <repository-name>
```

### Install Dependencies

```bash
mvn clean install
```

This command downloads all required project dependencies defined in `pom.xml`.

---

## Feature File Example

```gherkin
Feature: User API Validation

  Scenario: Verify user details
    Given User sends GET request to user endpoint
    Then Response status code should be 200
    And User name should be returned successfully
```

---

## Step Definition Example

```java
@Given("User sends GET request to user endpoint")
public void userSendsGetRequest() {
    response = RestAssured
            .given()
            .when()
            .get("/users/1");
}
```

---

## Running Tests

### Run Entire Test Suite

```bash
mvn test
```

### Run Specific Runner

```bash
mvn test -Dtest=TestRunner
```

Example:

```bash
mvn test -Dtest=RegressionRunner
```

### Run Tests by Tag

Smoke Tests:

```bash
mvn test -Dcucumber.filter.tags="@smoke"
```

Regression Tests:

```bash
mvn test -Dcucumber.filter.tags="@regression"
```

Multiple Tags:

```bash
mvn test -Dcucumber.filter.tags="@smoke and not @wip"
```

---

## Test Execution Flow

1. Maven starts test execution.
2. Runner class loads feature files.
3. Cucumber maps steps to Step Definitions.
4. REST Assured sends API requests.
5. Assertions validate API responses.
6. Reports are generated after execution.

---

## Report Generation

After execution, reports are generated automatically.

Report locations:

```text
target/cucumber-reports.html
```

## Opening the Report

### Windows

```bash
start target/cucumber-report.html
```

### MacOS

```bash
open target/cucumber-report.html
```

### Linux

```bash
xdg-open target/cucumber-report.html
```

Alternatively:

1. Navigate to the report location.
2. Double-click the HTML file.
3. Open it in any browser.

---

## Adding New Test Scenarios

### Step 1

Create or update a Feature file:

```text
src/test/java/feature
```

### Step 2

Add corresponding Step Definitions:

```text
src/test/java/stepDefinition
```

### Step 3

Implement REST Assured request and validation logic.

### Step 4

Execute tests:

```bash
mvn test
```

---

## Framework Maintenance

### Update Dependencies

Review and update dependency versions in:

```text
pom.xml
```

Then run:

```bash
mvn clean install
```

### Refactor Common Methods

Place reusable logic into:

```text
utilities
helpers
base classes
```

Examples:

* Authentication
* Request specifications
* Response validation
* Environment configuration

### Test Data Management

Store reusable test data under:

```text
src/test/resources/testdata
```

Avoid hardcoding values in Step Definitions.

### Environment Configuration

Keep environment-specific values in configuration files:

```text
src/test/resources/config
```

Examples:

* Base URL
* Authentication credentials
* API keys
* Environment variables

---

## Troubleshooting

### Maven Cannot Find pom.xml

Ensure you are inside the project root directory:

```bash
cd <project-root>
```

Verify:

```bash
ls
```

or

```cmd
dir
```

You should see:

```text
pom.xml
```

### Dependency Issues

Clean and rebuild:

```bash
mvn clean install -U
```

---

## Best Practices

* Use meaningful scenario names.
* Keep feature files business-readable.
* Reuse Step Definitions whenever possible.
* Centralize API request specifications.
* Avoid hardcoded test data.
* Use tags for test categorization.
* Regularly update dependencies.

---
