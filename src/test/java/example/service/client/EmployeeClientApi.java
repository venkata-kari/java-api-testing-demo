package example.service.client;

import com.sun.jersey.api.client.ClientResponse;
import example.service.domain.EmployeePostRecord;
import example.utils.RestAPiClient;
import gherkin.deps.com.google.gson.JsonObject;
import org.json.JSONException;
import org.json.JSONObject;

public class EmployeeClientApi extends RestAPiClient {
    public ClientResponse postEmployeeRecord(EmployeePostRecord postBody) {
        String endPoint = "http://dummy.restapiexample.com/api/v1/create";
        return postClientResponse(endPoint, new JSONObject(postBody).toString());
    }

    public ClientResponse getEmployeeRecord(final String employeeId) {
        String endPoint = String.format("http://dummy.restapiexample.com/api/v1/employee/%s", employeeId);
        return getClientResponse(endPoint);
    }

    public ClientResponse deleteEmployeeRecord(final String employeeId) {
        String endPoint = String.format("http://dummy.restapiexample.com/api/v1/employee/%s", employeeId);
        return deleteClientResponse(endPoint);
    }
}
