package com.solarwinds.model.licensing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LicenseAllocation {

    private String performanceLicenseProduct;
    private String vmLicenseProduct;

    public String getPerformanceLicenseProduct() {
        return performanceLicenseProduct;
    }

    public void setPerformanceLicenseProduct(String dbLicenseProduct) {
        performanceLicenseProduct = dbLicenseProduct;
    }

    public String getVmLicenseProduct() {
        return vmLicenseProduct;
    }

    public void setVmLicenseProduct(String vmLicenseProduct) {
        this.vmLicenseProduct = vmLicenseProduct;
    }

}
