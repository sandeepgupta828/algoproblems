package sample.api.buster;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import sample.api.buster.exception.BusterException;
import sample.api.buster.model.BusterResponse;
import sample.api.buster.model.KeyResponse;

import java.util.Collections;
import java.util.Map;

@Service
public class BusterService {
    private static final String BUSTER_TRANSACTION_URL = "http://34.102.239.194/v1/transaction";
    private static final String BUSTER_API_KEY_URL = "http://34.102.239.194/v1/api_key";

    private RestTemplate restTemplate;

    public BusterService() {
        // ideally should be injected as a dependency
        this.restTemplate = new RestTemplate(getClientHttpRequestFactory());
    }

    private SimpleClientHttpRequestFactory getClientHttpRequestFactory()
    {
        SimpleClientHttpRequestFactory clientHttpRequestFactory
                = new SimpleClientHttpRequestFactory();
        //Connect timeout
        clientHttpRequestFactory.setConnectTimeout(1_000);

        //Read timeout
        clientHttpRequestFactory.setReadTimeout(10_000);
        return clientHttpRequestFactory;
    }

    public KeyResponse createKey(Map<String, String> createKeyData)  throws BusterException {
        HttpHeaders headers = getHttpHeaders();
        HttpEntity entity = new HttpEntity(createKeyData, headers);
        ResponseEntity<KeyResponse> response = null;
        try {
            response = this.restTemplate.exchange(BUSTER_API_KEY_URL, HttpMethod.POST, entity, KeyResponse.class, 1);
        } catch (RestClientException ex) {
            throw new BusterException("Error calling buster for api key: ", ex);
        }
        return handleResponse(response);
    }

    public BusterResponse createTransaction(String apiKey, Map<String, String> createTransactionData) throws BusterException {
        HttpHeaders headers = getHttpHeaders();
        // set custom header
        headers.set("X-API-KEY", apiKey);

        HttpEntity entity = new HttpEntity(createTransactionData, headers);
        ResponseEntity<BusterResponse> response = null;
        try {
            response = this.restTemplate.exchange(BUSTER_TRANSACTION_URL, HttpMethod.POST, entity, BusterResponse.class, 1);
        } catch (RestClientException ex) {
            throw new BusterException("Error calling buster for transaction: ", ex);
        }
        return handleResponse(response);
    }

    private HttpHeaders getHttpHeaders() {
        // create common headers
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private <T> T handleResponse(ResponseEntity<T> response) throws BusterException {
        if (response == null) {
            throw new BusterException("Error calling buster: null response");
        }
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new BusterException("Error calling buster: "+ response.toString(), response.getStatusCode().value());
        }
    }
}
