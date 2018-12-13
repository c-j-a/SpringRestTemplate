package com.solarwinds.model.registration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterDatabase {

    private boolean amazonRds;
    private String databaseType;
    private boolean isAmazonRds;
    private String serverName;
    private String serviceNameOrSID;
    private Integer port;
    private boolean sysadminIsWindowsAuth;
    private String sysAdminUser;
    private String sysAdminPassword;
    private String monitoringUser;
    private String monitoringUserPassword;
    private boolean monitoringUserIsNew;
    private String monitoringUserTableSpace;
    private String monitoringUserTempTableSpace;
    private String sysPassword;
    private boolean sysBypasss;
    private boolean oracleEBusinessEnabled;
    private String repositoryTableSpace;
    private String database;
    private String jdbcUrlProperties;
    private String connectionProperties;
    private String displayName;

    public boolean getAmazonRds() {
        return amazonRds;
    }

    public void setAmazonRds(boolean amazonRds) {
        this.amazonRds = amazonRds;
    }

    public String getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }

    public boolean getIsAmazonRds() {
        return isAmazonRds;
    }

    public void setIsAmazonRds(boolean isAmazonRds) {
        this.isAmazonRds = isAmazonRds;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getServiceNameOrSID() {
        return serviceNameOrSID;
    }

    public void setServiceNameOrSID(String serviceNameOrSID) {
        this.serviceNameOrSID = serviceNameOrSID;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public boolean getSysadminIsWindowsAuth() {
        return sysadminIsWindowsAuth;
    }

    public void setSysadminIsWindowsAuth(boolean sysadminIsWindowsAuth) {
        this.sysadminIsWindowsAuth = sysadminIsWindowsAuth;
    }

    public String getSysAdminUser() {
        return sysAdminUser;
    }

    public void setSysAdminUser(String sysAdminUser) {
        this.sysAdminUser = sysAdminUser;
    }

    public String getSysAdminPassword() {
        return sysAdminPassword;
    }

    public void setSysAdminPassword(String sysAdminPassword) {
        this.sysAdminPassword = sysAdminPassword;
    }

    public String getMonitoringUser() {
        return monitoringUser;
    }

    public void setMonitoringUser(String monitoringUser) {
        this.monitoringUser = monitoringUser;
    }

    public String getMonitoringUserPassword() {
        return monitoringUserPassword;
    }

    public void setMonitoringUserPassword(String monitoringUserPassword) {
        this.monitoringUserPassword = monitoringUserPassword;
    }

    public boolean getMonitoringUserIsNew() {
        return monitoringUserIsNew;
    }

    public void setMonitoringUserIsNew(boolean monitoringUserIsNew) {
        this.monitoringUserIsNew = monitoringUserIsNew;
    }

    public String getMonitoringUserTableSpace() {
        return monitoringUserTableSpace;
    }

    public void setMonitoringUserTableSpace(String monitoringUserTableSpace) {
        this.monitoringUserTableSpace = monitoringUserTableSpace;
    }

    public String getMonitoringUserTempTableSpace() {
        return monitoringUserTempTableSpace;
    }

    public void setMonitoringUserTempTableSpace(String monitoringUserTempTableSpace) {
        this.monitoringUserTempTableSpace = monitoringUserTempTableSpace;
    }

    public String getSysPassword() {
        return sysPassword;
    }

    public void setSysPassword(String sysPassword) {
        this.sysPassword = sysPassword;
    }

    public boolean getSysBypasss() {
        return sysBypasss;
    }

    public void setSysBypasss(boolean sysBypasss) {
        this.sysBypasss = sysBypasss;
    }

    public boolean getOracleEBusinessEnabled() {
        return oracleEBusinessEnabled;
    }

    public void setOracleEBusinessEnabled(boolean oracleEBusinessEnabled) {
        this.oracleEBusinessEnabled = oracleEBusinessEnabled;
    }

    public String getRepositoryTableSpace() {
        return repositoryTableSpace;
    }

    public void setRepositoryTableSpace(String repositoryTableSpace) {
        this.repositoryTableSpace = repositoryTableSpace;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getJdbcUrlProperties() {
        return jdbcUrlProperties;
    }

    public void setJdbcUrlProperties(String jdbcUrlProperties) {
        this.jdbcUrlProperties = jdbcUrlProperties;
    }

    public String getConnectionProperties() {
        return connectionProperties;
    }

    public void setConnectionProperties(String connectionProperties) {
        this.connectionProperties = connectionProperties;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

}

