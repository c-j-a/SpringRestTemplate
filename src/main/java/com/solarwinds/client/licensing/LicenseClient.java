package com.solarwinds.client.licensing;

import com.solarwinds.model.licensing.LicenseAllocation;
import com.solarwinds.model.licensing.LicenseInfo;
import com.solarwinds.model.licensing.LicenseSummary;

import java.util.List;

public interface LicenseClient {

    List<LicenseSummary> getInstalledLicenses();

    LicenseInfo getLicenseInfo(int databaseId);

    LicenseInfo updateLicenseAllocation(int databaseId, LicenseAllocation licenseAllocation);

}
