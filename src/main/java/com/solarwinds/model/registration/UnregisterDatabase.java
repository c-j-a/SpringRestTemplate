package com.solarwinds.model.registration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class UnregisterDatabase {

    private Integer databaseId;
    private Boolean removeMonitoringUser;
    private Boolean removeDatabaseObjects;
    private String sysadminUser;
    private String sysadminPassword;

    public Integer getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(Integer databaseId) {
        this.databaseId = databaseId;
    }

    public Boolean getRemoveMonitoringUser() {
        return removeMonitoringUser;
    }

    public void setRemoveMonitoringUser(Boolean removeMonitoringUser) {
        this.removeMonitoringUser = removeMonitoringUser;
    }

    public Boolean getRemoveDatabaseObjects() {
        return removeDatabaseObjects;
    }

    public void setRemoveDatabaseObjects(Boolean removeDatabaseObjects) {
        this.removeDatabaseObjects = removeDatabaseObjects;
    }

    public String getSysadminUser() {
        return sysadminUser;
    }

    public void setSysAdminUser(String sysadminUser) {
        this.sysadminUser = sysadminUser;
    }

    public String getSysadminPassword() {
        return sysadminPassword;
    }

    public void setSysAdminPassword(String sysadminPassword) {
        this.sysadminPassword = sysadminPassword;
    }

}