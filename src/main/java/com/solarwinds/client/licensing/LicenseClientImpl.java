package com.solarwinds.client.licensing;

import com.solarwinds.client.AbstractRestClient;
import com.solarwinds.model.DpaResponse;
import com.solarwinds.model.licensing.LicenseAllocation;
import com.solarwinds.model.licensing.LicenseInfo;
import com.solarwinds.model.licensing.LicenseSummary;
import com.solarwinds.util.Constants;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

@Service
public class LicenseClientImpl extends AbstractRestClient implements LicenseClient {

    @Override
    public List<LicenseSummary> getInstalledLicenses() {
        System.out.println();
        System.out.println("===> getInstalledLicenses()");

        // http://localhost:8123/iwc/api/databases/licenses/installed
        String url = Constants.API_URL + "/databases/licenses/installed";

        ParameterizedTypeReference<DpaResponse<List<LicenseSummary>>> ptr = new ParameterizedTypeReference<>() {};
        DpaResponse<List<LicenseSummary>> dpaResponse = httpGet(url, ptr);

        return dpaResponse.getDataObject();
    }

    @Override
    public LicenseInfo getLicenseInfo(final int databaseId) {
        System.out.println();
        System.out.println("===> getLicenseInfo(" + databaseId + ")");

        // http://localhost:8123/iwc/api/databases/{databaseId}/licenses
        String url = Constants.API_URL + "/databases/"+databaseId+"/licenses";

        ParameterizedTypeReference<DpaResponse<LicenseInfo>> ptr = new ParameterizedTypeReference<>() {};
        DpaResponse<LicenseInfo> dpaResponse = httpGet(url, ptr);

        return dpaResponse.getDataObject();
    }

    @Override
    public LicenseInfo updateLicenseAllocation(final int databaseId, final LicenseAllocation licenseAllocation) {
        System.out.println();
        System.out.println("===> updateLicenseAllocation(" + databaseId + ", [" + licenseAllocation.getPerformanceLicenseProduct() + ", " + licenseAllocation.getVmLicenseProduct() + "])");

        // http://localhost:8123/iwc/api/databases/{databaseId}/licenses
        String url = Constants.API_URL + "/databases/"+databaseId+"/licenses";

        ParameterizedTypeReference<DpaResponse<LicenseInfo>> ptr = new ParameterizedTypeReference<>() {};
        DpaResponse<LicenseInfo> dpaResponse = httpPut(url, licenseAllocation, ptr);

        return dpaResponse.getDataObject();
    }

}
