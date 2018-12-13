package com.solarwinds.client.token;

import com.solarwinds.client.AbstractRestClient;
import com.solarwinds.model.token.AuthToken;
import com.solarwinds.util.Constants;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TokenClientImpl extends AbstractRestClient implements TokenClient {

    public AuthToken createAccessToken() {
        String url = Constants.API_URL + "/security/oauth/token?grant_type=refresh_token&refresh_token=" + Constants.REFRESH_TOKEN;
        System.out.println();
        System.out.println("===> createAccessToken()");

        HttpEntity<String> request = new HttpEntity<>(getHttpHeadersJson());

        ResponseEntity<AuthToken> response =
                restTemplate.exchange(url, HttpMethod.POST, request, new ParameterizedTypeReference<AuthToken>() {});

        return response.getBody();
    }

}
