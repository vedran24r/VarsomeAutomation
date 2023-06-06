# VarsomeAutomationProject

## Project Description
The VarsomeAutomationProject is an automation project that performs a series of tests on the Varsome website (https://varsome.com/). It aims to validate functionalities and verify expected results.

## Installation Instructions
To use the VarsomeAutomationProject, follow these steps:
1. Clone the project repository to your local machine.
2. Ensure you have the necessary dependencies installed.
3. Open the project in your preferred IDE.

## Dependencies
The following dependencies are required to run the VarsomeAutomationProject:

- **TestNG** (version 7.7.0): A testing framework for Java that provides a flexible and powerful platform for running automated tests.
- **Selenium** (version 4.6.0): A Java binding for Selenium WebDriver, which enables automated browser testing.
- **WebDriverManager** (version 5.3.1): A library that simplifies the management of WebDriver binaries, ensuring the correct version is downloaded and set up automatically.
- **Lombok**: A Java library that helps reduce boilerplate code by automatically generating getters, setters, constructors, and other common code patterns.

Make sure to include these dependencies in your project's build configuration or dependency management tool.

## Usage
1. Open the `TestConfig.xml` file.
2. Set the desired environment using the `<property>` tag. For example: 
    ```xml
    <property name="env" value="prod" />
    ```
3. Right click TestConfig.xml file and 'Run'.

## Test Steps and Expected Results:
1. Go to *https://varsome.com/*
2. Fill the search with the below query: *BRAF:V600E*
3. Change reference genome to *hg19*. `Verify that the reference genome is changed.`
4. Click the Search button.
5. Fill the values in the popup and click Search. `Verify that you are redirected to the correct result page.`
6. Click on *Germline Classification Card*. `Verify that the card appears.`
7. In the Sample Information component, `verify that the "Phenotype" inserted in the previous step is present.`
8. Scroll to the Automated criteria column and deactivate the PS3 rule. `Verify that the rule is deactivated.`
9. Scroll on the cards on top of the page and click on "Publications". `Verify that the Publications card appears.`
10. On the "ORDER BY" section, change the current value "relevance" to "publication date". `Verify that the order of the articles has changed.`

### Omitted features and enhancements due to project scope, local nature and time constraint:

- REST API library integration (restAssured/OkHttp3).
- Enums (although some argue they aren't suitable for automation purposes).
- Logger functionality.
- Test case tools (TestRail, Zephyr, Jira, Cucumber, csv, ...).
- Parallel execution and cross-browser testing.