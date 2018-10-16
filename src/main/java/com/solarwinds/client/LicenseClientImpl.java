package com.solarwinds.client;

import com.solarwinds.util.Constants;
import com.solarwinds.model.AuthToken;
import com.solarwinds.model.DpaResponse;
import com.solarwinds.model.LicenseAllocation;
import com.solarwinds.model.LicenseInfo;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LicenseClientImpl extends AbstractRestClient implements LicenseClient {

    @Override
    public LicenseInfo getLicenseInfo(final AuthToken authToken, final int databaseId) {

        System.out.println();
        System.out.println("===== getLicenseInfo =====");

        // http://localhost:8123/iwc/api/databases/{databaseId}/licenses
        String url = Constants.API_URL + "/databases/"+databaseId+"/licenses";
        System.out.println("License URL: " + url);

        HttpEntity<String> request = new HttpEntity<>(new HttpHeaders());

        ResponseEntity<DpaResponse<LicenseInfo>> response =
                restTemplate.exchange(url, HttpMethod.GET, request, new ParameterizedTypeReference<DpaResponse<LicenseInfo>>() {});

        DpaResponse<LicenseInfo> dpaResponse = response.getBody();
        LicenseInfo licenseInfo = dpaResponse.getDataObject();

        System.out.println("Status: " + response.getStatusCode().toString());
        System.out.println("LicenseInfo: " + licenseInfo);

        // Dump JSON
        ResponseEntity<Object> response2 = restTemplate.exchange(url, HttpMethod.GET, request, new ParameterizedTypeReference<Object>() {});
        System.out.println(response2.getBody().toString());

        return licenseInfo;
    }

    @Override
    public LicenseInfo updateLicenseAllocation(final AuthToken authToken,
                                               final int databaseId,
                                               final LicenseAllocation licenseAllocation) {

        System.out.println();
        System.out.println("===== updateLicenseAllocation =====");

        // http://localhost:8123/iwc/api/databases/{databaseId}/licenses
        String url = Constants.API_URL + "/databases/"+databaseId+"/licenses";
        System.out.println("License URL: " + url);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<LicenseAllocation> request = new HttpEntity<>(licenseAllocation, headers);

        ResponseEntity<DpaResponse<LicenseInfo>> response =
                restTemplate.exchange(url, HttpMethod.PUT, request, new ParameterizedTypeReference<DpaResponse<LicenseInfo>>() {});

        DpaResponse<LicenseInfo> dpaResponse = response.getBody();
        LicenseInfo licenseInfo = dpaResponse.getDataObject();
        System.out.println("Status: " + response.getStatusCode().toString());
        System.out.println("LicenseInfo: " + licenseInfo);

        return licenseInfo;
    }

}
