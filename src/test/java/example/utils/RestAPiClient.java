package example.utils;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import io.restassured.response.Response;

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

}

