package com.solarwinds.model.licensing;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LicenseInfo {

    private String serverName;
    private String performanceProduct;
    private String vmProduct;
    private boolean overLicensed;

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getPerformanceLicenseProduct() {
        return performanceProduct;
    }

    public void setPerformanceLicenseProduct(String performanceProduct) {
        this.performanceProduct = performanceProduct;
    }

    public String getVmLicenseProduct() {
        return vmProduct;
    }

    public void setVmLicenseProduct(String vmProduct) {
        this.vmProduct = vmProduct;
    }

    public boolean isOverLicensed() {
        return overLicensed;
    }

    public void setOverLicensed(boolean overLicensed) {
        this.overLicensed = overLicensed;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [" +
                "serverName='" + serverName + '\'' +
                ", performanceProduct='" + performanceProduct + '\'' +
                ", vmProduct='" + vmProduct + '\'' +
                ", overLicensed=" + overLicensed +
                ']';
    }
}
