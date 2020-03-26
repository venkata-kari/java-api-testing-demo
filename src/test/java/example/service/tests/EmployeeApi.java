package example.service.tests;

import com.sun.jersey.api.client.ClientResponse;
import example.service.client.EmployeeClientApi;
import example.service.domain.EmployeePostRecord;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class EmployeeApi {

    private EmployeeClientApi employeeClientApi;
    private String employeeId;
    private EmployeePostRecord employeePostRecord;

    @Before
    public void setUp() {
        employeeClientApi = new EmployeeClientApi();
        employeePostRecord = EmployeePostRecord.builder()
                .name("John Doe")
                .age("30")
                .salary("86000")
                .build();
    }

    @Test
    public void testCreateEmployeeRecord() throws JSONException {
        ClientResponse employeePostRecordResponse = employeeClientApi.postEmployeeRecord(employeePostRecord);

        Assert.assertEquals(200, employeePostRecordResponse.getStatus());
        JSONObject responseBody = new JSONObject(employeePostRecordResponse.getEntity(String.class));
        employeeId = responseBody.getJSONObject("data").getString("id");
    }

    @Test
    public void testGetEmployeeDetailsById() throws JSONException {
        ClientResponse employeeDetails = employeeClientApi.getEmployeeRecord(employeeId);
        JSONObject responseBody = new JSONObject(employeeDetails.getEntity(String.class));

        JSONObject employeeResponseDetails = responseBody.getJSONObject("data");
        assertSame("Unknown employee ID", employeeId, employeeResponseDetails.getString("id"));
        assertSame("unknown employee name", employeePostRecord.getName(), employeeResponseDetails.getString("employee_name"));
        assertSame("unknown employee salary", employeePostRecord.getSalary(), employeeResponseDetails.getString("employee_salary"));
        assertSame("unknown employee age", employeePostRecord.getAge(), employeeResponseDetails.getString("employee_age"));
    }

    @Test
    public void testEmployeeRecordDeleteById() throws JSONException {
        ClientResponse deleteResponse = employeeClientApi.deleteEmployeeRecord(employeeId);
        JSONObject responseBody = new JSONObject(deleteResponse.getEntity(String.class));

        assertSame("successfully! deleted Records", responseBody.getString("message"));
        assertSame("success", responseBody.getString("status"));
    }

    @Test
    public void testRequestToGetUnknownEmployeeRecordFails() throws JSONException {
        ClientResponse employeeRecordResponse = employeeClientApi.getEmployeeRecord(employeeId);
        JSONObject response = new JSONObject(employeeRecordResponse.getEntity(String.class));

        assertEquals(401, employeeRecordResponse.getStatus());
        assertSame("Record does not found.", response.getString("message"));
        assertSame("failed", response.getString("status"));
    }

    @Test
    public void BadRequestFromDummyApi() {
        ClientResponse employeeRecordResponse = employeeClientApi.getClientResponse("$adkfhadfg");
        assertEquals(404, employeeRecordResponse.getStatus());
    }
}
