package com.solarwinds.client.registration;

import com.solarwinds.client.AbstractRestClient;
import com.solarwinds.model.DpaResponse;
import com.solarwinds.model.registration.RegisterDatabase;
import com.solarwinds.model.registration.RegistrationResponse;
import com.solarwinds.model.registration.UnregisterDatabase;
import com.solarwinds.util.Constants;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RegistrationClientImpl extends AbstractRestClient implements RegistrationClient {

    @Override
    public RegistrationResponse registerMonitor(RegisterDatabase registerDatabase) {

        System.out.println();
        System.out.println("===> registerMonitor(" + registerDatabase + ")");

        // http://localhost:8123/iwc/api/databases/register-monitor
        String url = Constants.API_URL + "/databases/register-monitor";

        HttpEntity<RegisterDatabase> request = new HttpEntity<>(registerDatabase, getHttpHeadersJson());

        ResponseEntity<DpaResponse<RegistrationResponse>> response =
                restTemplate.exchange(url, HttpMethod.POST, request, new ParameterizedTypeReference<DpaResponse<RegistrationResponse>>() {});

        DpaResponse<RegistrationResponse> dpaResponse = response.getBody();

        return dpaResponse.getDataObject();

    }

    @Override
    public RegistrationResponse unRegisterMonitor(UnregisterDatabase unregisterDatabase) {

        System.out.println();
        System.out.println("===> unRegisterMonitor(" + unregisterDatabase + ")");

        // http://localhost:8123/iwc/api/databases/unregister-monitor
        String url = Constants.API_URL + "/databases/unregister-monitor";

        HttpEntity<UnregisterDatabase> request = new HttpEntity<>(unregisterDatabase, getHttpHeadersJson());

        ResponseEntity<DpaResponse<RegistrationResponse>> response =
                restTemplate.exchange(url, HttpMethod.POST, request, new ParameterizedTypeReference<DpaResponse<RegistrationResponse>>() {});

        DpaResponse<RegistrationResponse> dpaResponse = response.getBody();

        return dpaResponse.getDataObject();

    }

}
