
## **Features**

1. Java 11
2. Gradle Build Tool 8.0.2
3. Selenium Webdriver
4. TestNG
5. Rest Assured
6. Allure-reports
7. Cross Browser Testing - Google Chrome, Firefox, Microsoft Edge

## **Setup project on machine to work**

* Install IntelliJ IDEA
* Install Java
* Install JDK & JRE
* Download the project from git and unzip it or take a git clone
* Import the project in IntelliJ

## **Running Api tests**

Browse through the api-tests module and run the test suite from IntelliJ

To run the test suite from command line, use:

`gradle :api-tests:test`

Reports are generated in build folder of `api-tests` module

## **Running UI tests**

Browse through the ui-tests module and run the test suite from IntelliJ.

Default browser value is set to _chrome_. Change the browser value accordingly to run the tests on specific browser.
Accepted values for browser parameter are: `chrome, firefox, msedge`

To run the tests in headless mode, set the `headless` value to `true` in config.properties

To run the test suite from command line, use:

`gradle :ui-tests:test -Pbrowser=firefox`

Reports are generated in build folder of `ui-tests` module

