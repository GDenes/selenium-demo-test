# Selenium First Task

This repository contains tests for the Sauce Demo application, and Google search. 

## Tech stack:

* Java
* Selenium
* JUnit

## System variables

System variables are used to control the behavior of the tests.  
All of them are optional, with default values.

| Variable name | Values                | Default value              | Description                                         |
|---------------|-----------------------|----------------------------|-----------------------------------------------------|
| -Dbrowser     | Chrome, Edge, Firefox | Chrome                     | The browser where the tests will run.               |
| -Dheadless    | true, false           | false                      | If the browser should run in headless mode.         |
| -Dremote      | true, false           | false                      | If the tests should run in remote mode.             |
| -Durl         | *valid url*           | http://192.168.50.69:4444/ | Remote url, if the tests should run in remote mode. |

# Running tests locally from CLI

```sh

# Run all tests
mvn clean test

# Run a single test class
mvn clean test -Dtest=CartTests

# Run a single test method
mvn clean test -Dtest=CartTests#testRandomProductsInCart

```

## Selecting the browser

```sh
mvn clean test -Dbrowser=firefox
```

## Set headless mode

```sh
mvn clean test -Dheadless=true
```
# Running tests remotely

1. Prerequisites
    * Java 11 or higher installed
    * Browser(s) installed
    * Browser driver(s)
    * Download the Selenium server jar file from the [latest release](https://github.com/SeleniumHQ/selenium/releases/latest)
2. Start the Grid
```sh
java -jar selenium-server-<version>.jar standalone
```
3. Point your WebDriver tests to http://localhost:4444

## Use default URL

```sh
mvn clean test -Dremote=true
```

## Use specific URL

```sh
mvn clean test -Dremote=true -Durl=http://localhost:4444/
```

## Generate Allure report

Allure Maven plugin can be used to generate a test report.  
To only create a report in target/allure-report. Currently, not really useful as it needs a server to be displayed correctly.

```sh
mvn allure:report
```

You can also serve a generated temp report in a local Jetty server instance.  
It will open in the default browser after it was generated.

```sh
mvn allure:serve
```
