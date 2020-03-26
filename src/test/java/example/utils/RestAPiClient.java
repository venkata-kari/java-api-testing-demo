package example.utils;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class RestAPiClient {
        ClientResponse response;
        private Response clientResponse;

        public ClientResponse getClientResponse(final String serviceEndPoint) {
            final Client client = new Client();
            final WebResource resource = client.resource(serviceEndPoint);

            response = resource
                    .type("application/json")
                    .get(ClientResponse.class);

            return response;
        }

        public ClientResponse postClientResponse(final String serviceEndPoint, final String body) {
            final Client client = new Client();
            final WebResource resource = client.resource(serviceEndPoint);

            response = resource
                    .type("application/json")
                    .post(ClientResponse.class, body);

            return response;
        }

        public ClientResponse putClientResponse(final String serviceEndPoint, final String body) {
            final Client client = new Client();
            WebResource resource = client.resource(serviceEndPoint);
            response = resource
                    .type("application/json")
                    .put(ClientResponse.class, body);
            return response;
        }

        public ClientResponse deleteClientResponse(String serviceEndPoint) {
            final Client client = new Client();
            WebResource resource = client.resource(serviceEndPoint);
            response = resource
                    .type("application/json")
                    .delete(ClientResponse.class);
            return response;
        }

        public Response getAPIClientResponse(final String serviceEndPoint) {
            clientResponse = given().contentType(JSON).get(serviceEndPoint);
            return clientResponse;
        }

        public ClientResponse optionsClientResponse(final String serviceEndPoint){
            final Client client = new Client();
            WebResource resource = client.resource(serviceEndPoint);
            return resource.options(ClientResponse.class);
        }

        public Response getClientCookieResponse(final String serviceEndPoint, final String tenant, final String acsCookie) {
            String cookieHeaderValue = String.format("acs_%s=%s", tenant, acsCookie);
            clientResponse = given()
                    .contentType(JSON)
                    .header("cookie", cookieHeaderValue)
                    .get(serviceEndPoint);
            return clientResponse;
        }

        public ClientResponse getClientWithCookieResponse(final String serviceEndPoint, final String tenant, final String acsCookie) {
            final Client client = new Client();
            WebResource resource = client.resource(serviceEndPoint);
            if (acsCookie == null) {
                response = resource
                        .get(ClientResponse.class);
            }else{
                String cookieHeaderValue = String.format("acs_%s=%s", tenant, acsCookie);
                response = resource
                        .header("cookie", cookieHeaderValue)
                        .get(ClientResponse.class);
            }
            return response;
        }

        public ClientResponse putClientWithCookieResponse(final String serviceEndPoint, final String tenant, final String acsCookie, final String body) {
            final Client client = new Client();
            WebResource resource = client.resource(serviceEndPoint);
            if (acsCookie == null) {
                response = resource
                        .type("application/json")
                        .put(ClientResponse.class, body);
            }else{
                String cookieHeaderValue = String.format("acs_%s=%s", tenant, acsCookie);
                response = resource
                        .type("application/json")
                        .header("cookie", cookieHeaderValue)
                        .put(ClientResponse.class, body);
            }
            return response;
        }

        public JSONObject getClientJsonResponse() throws JSONException {
            return new JSONObject(clientResponse.body().asString());
        }

    }

