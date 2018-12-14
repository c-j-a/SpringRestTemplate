package com.solarwinds.client;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public abstract class AbstractRestClient {

    @Autowired
    protected RestTemplate restTemplate;


    /**
     * Helper to get a HttpHeaders object with a ContentType of MediaType.APPLICATION_JSON
     *
     * @return The HttpHeaders object
     */
    protected HttpHeaders getHttpHeadersJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    /**
     * Helper to convert OffsetDateTime to ISO Strings
     *
     * @param dateTime The date to convert
     * @return The String value of the OffsetDateTime
     */
    protected String dateTimeToString(OffsetDateTime dateTime) {
        return DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(dateTime);
    }

    /**
     * A helper method to do HTTP GETs
     *
     * @param url The URL to post to
     * @param queryParams The Query Parameters to add
     * @param returnType The ParameterizedTypeReference
     * @param <T> The referenced type
     * @return The return value
     */
    protected <T> T httpGet(String url, Map<String, Object> queryParams, ParameterizedTypeReference<T> returnType) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        // Add the parameters to URL
        Set<Map.Entry<String, Object>> entries = queryParams.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            builder.queryParam(entry.getKey(), entry.getValue());
        }

        return httpGet(builder.toUriString(), returnType);
    }

    /**
     * A helper method to do HTTP GETs
     *
     * @param url The URL to post to
     * @param returnType The ParameterizedTypeReference
     * @param <T> The referenced type
     * @return The return value
     */
    protected <T> T httpGet(String url, ParameterizedTypeReference<T> returnType) {
        HttpEntity<String> request = new HttpEntity<>(getHttpHeadersJson());
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, request, returnType);

        return response.getBody();
    }

    /**
     * A helper method to do HTTP PUTs
     *
     * @param url The URL to post to
     * @param object     The Object to PUT
     * @param returnType The ParameterizedTypeReference
     * @param <T> The referenced type
     * @return The return value
     */
    protected <T> T httpPut(String url, Object object, ParameterizedTypeReference<T> returnType) {
        HttpEntity<T> request = new HttpEntity<>((T) object, getHttpHeadersJson());
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.PUT, request, returnType);

        return response.getBody();
    }

    /**
     * A helper method to do HTTP POSTs
     *
     * @param url The URL to post to
     * @param object The Object to PUT
     * @param returnType The ParameterizedTypeReference
     * @param <T> The referenced type
     * @return The return value
     */
    protected <T> T httpPost(String url, Object object, ParameterizedTypeReference<T> returnType) {
        HttpEntity<T> request = new HttpEntity<>((T) object, getHttpHeadersJson());
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.POST, request, returnType);

        return response.getBody();
    }

}
