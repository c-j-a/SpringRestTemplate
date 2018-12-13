package com.solarwinds.model.registration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class RegistrationResponse {

    int databaseId;
    String result;

    public int getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(int databaseId) {
        this.databaseId = databaseId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [" +
                "databaseId=" + databaseId +
                ", result='" + result + '\'' +
                ']';
    }

}
