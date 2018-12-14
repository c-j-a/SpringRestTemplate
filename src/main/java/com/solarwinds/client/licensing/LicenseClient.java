package com.solarwinds.client.licensing;

import com.solarwinds.model.licensing.LicenseAllocation;
import com.solarwinds.model.licensing.LicenseInfo;
import com.solarwinds.model.licensing.LicenseSummary;

import java.util.List;

public interface LicenseClient {

    /**
     * Gets information about all the installed licenses.
     *
     * @return A List of LicenseSummary Objects
     */
    List<LicenseSummary> getInstalledLicenses();

    /**
     * Gets License Information for the requested database.
     *
     * @param databaseId The database id to get information for.
     * @return LicenseInfo containing the new license state for the database.
     */
    LicenseInfo getLicenseInfo(int databaseId);

    /**
     * Updates the License Allocations for the requested database.
     *
     * @param databaseId The database id to get information for.
     * @param licenseAllocation The LicenseAllocation containing the information about what to license and/or unlicense.
     * @return LicenseInfo containing the new license state for the database.
     */
    LicenseInfo updateLicenseAllocation(int databaseId, LicenseAllocation licenseAllocation);

}
