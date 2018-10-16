package com.solarwinds.client;

import com.solarwinds.model.AuthToken;
import com.solarwinds.model.LicenseAllocation;
import com.solarwinds.model.LicenseInfo;

public interface LicenseClient {

    LicenseInfo getLicenseInfo(AuthToken authToken, int databaseId);

    LicenseInfo updateLicenseAllocation(AuthToken authToken, int databaseId, LicenseAllocation licenseAllocation);

}
