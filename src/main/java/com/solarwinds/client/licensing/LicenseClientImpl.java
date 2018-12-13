package com.solarwinds.client.licensing;

import com.solarwinds.client.AbstractRestClient;
import com.solarwinds.model.DpaResponse;
import com.solarwinds.model.licensing.LicenseAllocation;
import com.solarwinds.model.licensing.LicenseInfo;
import com.solarwinds.model.licensing.LicenseSummary;
import com.solarwinds.util.Constants;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LicenseClientImpl extends AbstractRestClient implements LicenseClient {

    @Override
    public List<LicenseSummary> getInstalledLicenses() {
        System.out.println();
        System.out.println("===> getInstalledLicenses()");

        // http://localhost:8123/iwc/api/databases/licenses/installed
        String url = Constants.API_URL + "/databases/licenses/installed";

        // TODO Get httpGet(url) method to work correctly
        // ResponseEntity<DpaResponse<List<LicenseSummary>>> response = httpGet(url);

        HttpEntity<String> request = new HttpEntity<>(getHttpHeadersJson());

        ResponseEntity<DpaResponse<List<LicenseSummary>>> response =
                restTemplate.exchange(url, HttpMethod.GET, request, new ParameterizedTypeReference<DpaResponse<List<LicenseSummary>>>() {});

        DpaResponse<List<LicenseSummary>> dpaResponse = response.getBody();

        return dpaResponse.getDataObject();
    }

    @Override
    public LicenseInfo getLicenseInfo(final int databaseId) {
        System.out.println();
        System.out.println("===> getLicenseInfo(" + databaseId + ")");

        // http://localhost:8123/iwc/api/databases/{databaseId}/licenses
        String url = Constants.API_URL + "/databases/"+databaseId+"/licenses";

        // TODO Get httpGet(url) method to work correctly
        // ResponseEntity<DpaResponse<LicenseInfo>> response = httpGet(url);

        HttpEntity<String> request = new HttpEntity<>(getHttpHeadersJson());

        ResponseEntity<DpaResponse<LicenseInfo>> response =
                restTemplate.exchange(url, HttpMethod.GET, request, new ParameterizedTypeReference<DpaResponse<LicenseInfo>>() {});

        DpaResponse<LicenseInfo> dpaResponse = response.getBody();

        return dpaResponse.getDataObject();
    }

    @Override
    public LicenseInfo updateLicenseAllocation(final int databaseId, final LicenseAllocation licenseAllocation) {
        System.out.println();
        System.out.println("===> updateLicenseAllocation(" + databaseId + ", [" + licenseAllocation.getPerformanceLicenseProduct() + ", " + licenseAllocation.getVmLicenseProduct() + "])");

        // http://localhost:8123/iwc/api/databases/{databaseId}/licenses
        String url = Constants.API_URL + "/databases/"+databaseId+"/licenses";

        HttpEntity<LicenseAllocation> request = new HttpEntity<>(licenseAllocation, getHttpHeadersJson());

        ResponseEntity<DpaResponse<LicenseInfo>> response =
                restTemplate.exchange(url, HttpMethod.PUT, request, new ParameterizedTypeReference<DpaResponse<LicenseInfo>>() {});

        DpaResponse<LicenseInfo> dpaResponse = response.getBody();

        return dpaResponse.getDataObject();
    }

}
