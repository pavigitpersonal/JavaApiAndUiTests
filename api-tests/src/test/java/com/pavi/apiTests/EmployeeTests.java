package com.pavi.apiTests;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static org.hamcrest.Matchers.equalTo;

import com.pavi.config.ConfigReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EmployeeTests extends BaseTest{
    private String baseUrl;
    private static final String GET_EMPLOYEES_ENDPOINT = "/api/v1/employees";
    private static final String CREATE_EMPLOYEE_ENDPOINT = "/api/v1/create";
    private static final String UPDATE_EMPLOYEE_ENDPOINT = "/api/v1/update/{id}";
    private static final String DELETE_EMPLOYEE_ENDPOINT = "/api/v1/delete/{id}";

    private int employee1Id;
    private int employee2Id;
    private final int employee1Salary = 40000;
    private final int employee2Salary = 50000;

    @BeforeClass
    public void setUp() {
        ConfigReader.readConfig();
        RestAssured.baseURI = ConfigReader.getBaseUrl();
    }

    @Test(priority = 1)
    public void testGetEmployeesCountAndNames() {
        System.out.println("Url = " + RestAssured.baseURI + GET_EMPLOYEES_ENDPOINT);

        // Get count of employees
        String response = given()
                .when()
                .get(GET_EMPLOYEES_ENDPOINT)
                .then()
                .extract()
                .asString();

        JsonPath jsonPath = new JsonPath(response);

        int count = jsonPath.getInt("data.size()");
        System.out.println("Number of employees: " + count);

        // Print names of employees
        for (int i = 0; i < count; i++) {
            String name = jsonPath.getString("data[" + i + "].employee_name");
            System.out.println("Employee " + (i+1) + " name: " + name);
        }
    }

    @Test(priority = 2)
    public void testCreateEmployee() {
        System.out.println("Url = " + RestAssured.baseURI + CREATE_EMPLOYEE_ENDPOINT);
        // Create employee 1
        String employee1Data = "{\"name\":\"Pavi 1\"," +
                "\"salary\":" + employee1Salary + "," +
                "\"age\":\"30\"," +
                "\"profile_image\": \"\"}";

        String response = given()
                .body(employee1Data)
                .when()
                .post(CREATE_EMPLOYEE_ENDPOINT)
                .then()
                .statusCode(200)
                .body("message", equalTo("Successfully! Record has been added."))
                .extract()
                .asString();

        System.out.println(response);

        JsonPath jsonPath = new JsonPath(response);
        employee1Id = jsonPath.getInt("data.id");
        System.out.println("Employee 1 created with ID: " + employee1Id);

        // Create employee 2
        String employee2Data = "{\"name\":\"Pavi 2\"," +
                "\"salary\":" + employee2Salary + "," +
                "\"age\":\"35\"," +
                "\"profile_image\": \"\"}";

        response = given()
                .body(employee2Data)
                .when()
                .post(CREATE_EMPLOYEE_ENDPOINT)
                .then()
                .statusCode(200)
                .body("message", equalTo("Successfully! Record has been added."))
                .extract()
                .asString();

        jsonPath = new JsonPath(response);
        employee2Id = jsonPath.getInt("data.id");
        System.out.println("Employee 2 created with ID: " + employee2Id);
    }

    @Test(priority = 3)
    public void testUpdateEmployee() {
        // Update employee 1 salary
        given()
                .pathParam("id", employee1Id)
                .param("salary", employee1Salary + 10000)
                .when()
                .put(UPDATE_EMPLOYEE_ENDPOINT)
                .then()
                .statusCode(200)
                .body("message", equalTo("Successfully! Record has been updated."));

        // Update employee 2 salary
        given()
                .pathParam("id", employee2Id)
                .param("salary", employee2Salary + 10000)
                .when()
                .put(UPDATE_EMPLOYEE_ENDPOINT)
                .then()
                .statusCode(200)
                .body("message", equalTo("Successfully! Record has been updated."));

        System.out.println("Updated salaries for employees with IDs: " + employee1Id + ", " + employee2Id);
    }

    @Test(priority = 4)
    public void testDeleteEmployee() {
        // Delete employee 1
        given()
                .pathParam("id", employee1Id)
                .when()
                .delete(DELETE_EMPLOYEE_ENDPOINT)
                .then()
                .statusCode(200)
                .body("message", equalTo("Successfully! Record has been deleted."));

        System.out.println("Deleted employee with ID: " + employee1Id);
    }
}
