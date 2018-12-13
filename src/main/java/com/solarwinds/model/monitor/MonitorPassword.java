package com.solarwinds.model.monitor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MonitorPassword {

    private String password;

    public MonitorPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

}