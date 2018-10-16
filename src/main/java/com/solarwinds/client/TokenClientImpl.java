package com.solarwinds.client;

import com.solarwinds.util.Constants;
import com.solarwinds.model.AuthToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TokenClientImpl implements TokenClient {

    private static final String ACCESS_TOKEN_URL = Constants.BASE_URL + "/security/oauth/token?grant_type=refresh_token&refresh_token=";

    @Autowired
    RestTemplate restTemplate;

    public AuthToken createAccessToken() {
        System.out.println();
        System.out.println("===== createAccessToken =====");

        String url = ACCESS_TOKEN_URL + Constants.REFRESH_TOKEN;
        System.out.println("License URL: " + url);

        // POST
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<AuthToken> response =
                restTemplate.exchange(url, HttpMethod.POST, request, new ParameterizedTypeReference<AuthToken>() {});
        AuthToken authToken = response.getBody();
        System.out.println("Status: " + response.getStatusCode().toString());
        System.out.println("AuthToken: " + authToken);

        // Dump JSON
        //ResponseEntity<Object> response2 = restTemplate.exchange(url, HttpMethod.POST, request, Object.class);
        //System.out.println(response2.getBody().toString());

        return authToken;
    }
}

// Example not using ParameterizedTypeReference

//        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.POST, request, Object.class);
//        AuthToken authToken = null;
//        LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>)response.getBody();
//        if(map != null){
//            // DEBUG
//            System.out.println("DpaResponse: ");
//            Set<Map.Entry<String, Object>> entries = map.entrySet();
//            for (Map.Entry<String, Object> entry : entries) {
//                System.out.println("  " + entry.getKey() + ": " + entry.getValue());
//            }
//            // DEBUG
//            authToken = new AuthToken();
//            authToken.setAccessToken((String)map.get("access_token"));
//            authToken.setTokenType((String)map.get("token_type"));
//            authToken.setExpiresIn((int)map.get("expires_in"));
//            authToken.setJti((String)map.get("jti"));
//        }
