package com.solarwinds.client;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

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

    protected String dateTimeToString(OffsetDateTime dateTime) {
        return DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(dateTime);
    }

}
