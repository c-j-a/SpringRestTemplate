package com.solarwinds.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractRestClient {

    @Autowired
    RestTemplate restTemplate;

}
