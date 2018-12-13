package com.solarwinds.model.licensing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class LicenseSummary {

    private String licenseProduct;
    private String licenseCategory;
    private int licensesAvailable;
    private int licensesConsumed;

    public String getLicenseProduct() {
        return licenseProduct;
    }

    public void setLicenseProduct(String licenseProduct) {
        this.licenseProduct = licenseProduct;
    }

    public String getLicenseCategory() {
        return licenseCategory;
    }

    public void setLicenseCategory(String licenseCategory) {
        this.licenseCategory = licenseCategory;
    }

    public int getLicensesAvailable() {
        return licensesAvailable;
    }

    public void setLicensesAvailable(int licensesAvailable) {
        this.licensesAvailable = licensesAvailable;
    }

    public int getLicensesConsumed() {
        return licensesConsumed;
    }

    public void setLicensesConsumed(int licensesConsumed) {
        this.licensesConsumed = licensesConsumed;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [" +
                "licenseProduct='" + licenseProduct + '\'' +
                ", licenseCategory='" + licenseCategory + '\'' +
                ", licensesAvailable=" + licensesAvailable +
                ", licensesConsumed=" + licensesConsumed +
                ']';
    }

}
