package com.solarwinds.config;

import com.solarwinds.util.Constants;
import com.solarwinds.model.AuthToken;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

public class DpaClientRequestInterceptor implements ClientHttpRequestInterceptor {

    private AuthToken authToken = null;

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        // Add the OAuth token to the headers
        if (authToken != null) {
            HttpHeaders headers = request.getHeaders();
            headers.set("Authorization", "Bearer " + authToken.getAccessToken());
        }

        logRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        logResponse(response);

        return response;
    }

    private void logRequest(HttpRequest request, byte[] body) throws IOException {
        if (isDebugEnabled()) {
            log("=========================== request begin =============================================");
            log("URI         : " + request.getURI());
            log("Method      : " + request.getMethod());
            log("Headers     : " + request.getHeaders());
            log("Request body: " + new String(body, "UTF-8"));
            log("========================== request end ================================================");
        }
    }

    private void logResponse(ClientHttpResponse response) throws IOException {
        if (isDebugEnabled()) {
            log("========================== response begin =============================================");
            log("Status code  : " + response.getStatusCode());
            log("Status text  : " + response.getStatusText());
            log("Headers      : " + response.getHeaders());
            log("Response body: " + StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
            log("======================= response end ==================================================");
        }
    }

    private boolean isDebugEnabled() {
        return Constants.DEBUG;
    }

    private void log(String message) {
        System.out.println(message);
    }

}
