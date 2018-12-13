package com.solarwinds.model.monitor;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.OffsetDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MonitoredInstance {

    private int dbId;
    private String name;
    private String ip;
    private String port;
    private String jdbcUrlProperties;
    private String connectionProperties;
    private String databaseType;
    private String databaseVersion;
    private String databaseEdition;
    private String monitoringUser;
    private String defaultDbLicenseCategory;
    private String assignedDbLicenseCategory;
    private String assignedVmLicenseCategory;
    private String monitorState;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private OffsetDateTime oldestMonitoringDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private OffsetDateTime latestMonitoringDate;
    private boolean isLinkedToVirtualMachine;
    private boolean isRds;
    private boolean isPdb;
    private boolean isRac;
    private boolean isEBusiness;
    private String agListenerName;
    private String agClusterName;
    private String agName;
    private String racInfo;

    public int getDbId() {
        return dbId;
    }

    public void setDbId(int dbId) {
        this.dbId = dbId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
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

    public String getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }

    public String getDatabaseVersion() {
        return databaseVersion;
    }

    public void setDatabaseVersion(String databaseVersion) {
        this.databaseVersion = databaseVersion;
    }

    public String getDatabaseEdition() {
        return databaseEdition;
    }

    public void setDatabaseEdition(String databaseEdition) {
        this.databaseEdition = databaseEdition;
    }

    public String getMonitoringUser() {
        return monitoringUser;
    }

    public void setMonitoringUser(String monitoringUser) {
        this.monitoringUser = monitoringUser;
    }

    public String getDefaultDbLicenseCategory() {
        return defaultDbLicenseCategory;
    }

    public void setDefaultDbLicenseCategory(String defaultDbLicenseCategory) {
        this.defaultDbLicenseCategory = defaultDbLicenseCategory;
    }

    public String getAssignedDbLicenseCategory() {
        return assignedDbLicenseCategory;
    }

    public void setAssignedDbLicenseCategory(String assignedDbLicenseCategory) {
        this.assignedDbLicenseCategory = assignedDbLicenseCategory;
    }

    public String getAssignedVmLicenseCategory() {
        return assignedVmLicenseCategory;
    }

    public void setAssignedVmLicenseCategory(String assignedVmLicenseCategory) {
        this.assignedVmLicenseCategory = assignedVmLicenseCategory;
    }

    public String getMonitorState() {
        return monitorState;
    }

    public void setMonitorState(String monitorState) {
        this.monitorState = monitorState;
    }

    public OffsetDateTime getOldestMonitoringDate() {
        return oldestMonitoringDate;
    }

    public void setOldestMonitoringDate(OffsetDateTime oldestMonitoringDate) {
        this.oldestMonitoringDate = oldestMonitoringDate;
    }

    public OffsetDateTime getLatestMonitoringDate() {
        return latestMonitoringDate;
    }

    public void setLatestMonitoringDate(OffsetDateTime latestMonitoringDate) {
        this.latestMonitoringDate = latestMonitoringDate;
    }

    public boolean isLinkedToVirtualMachine() {
        return isLinkedToVirtualMachine;
    }

    public void setLinedToVirtualMachine(boolean isLinkedToVirtualMachine) {
        this.isLinkedToVirtualMachine = isLinkedToVirtualMachine;
    }

    public boolean isRds() {
        return isRds;
    }

    public void setRds(boolean rds) {
        isRds = rds;
    }

    public boolean isPdb() {
        return isPdb;
    }

    public void setPdb(boolean pdb) {
        isPdb = pdb;
    }

    public boolean isRac() {
        return isRac;
    }

    public void setRac(boolean rac) {
        isRac = rac;
    }

    public boolean isEBusiness() {
        return isEBusiness;
    }

    public void setEBusiness(boolean EBusiness) {
        isEBusiness = EBusiness;
    }

    public String getAgListenerName() {
        return agListenerName;
    }

    public void setAgListenerName(String agListenerName) {
        this.agListenerName = agListenerName;
    }

    public String getAgClusterName() {
        return agClusterName;
    }

    public void setAgClusterName(String agClusterName) {
        this.agClusterName = agClusterName;
    }

    public String getAgName() {
        return agName;
    }

    public void setAgName(String agName) {
        this.agName = agName;
    }

    public String getRacInfo() {
        return racInfo;
    }

    public void setRacInfo(String racInfo) {
        this.racInfo = racInfo;
    }

    public boolean isStarted() {
        return "Monitor Running".equals(monitorState);
    }

    public boolean isStopped() {
        return "Monitor Stopped".equals(monitorState);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [" +
                "dbId=" + dbId +
                ", name='" + name + '\'' +
                ", ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", jdbcUrlProperties='" + jdbcUrlProperties + '\'' +
                ", connectionProperties='" + connectionProperties + '\'' +
                ", databaseType='" + databaseType + '\'' +
                ", databaseVersion='" + databaseVersion + '\'' +
                ", databaseEdition='" + databaseEdition + '\'' +
                ", monitoringUser='" + monitoringUser + '\'' +
                ", defaultDbLicenseCategory='" + defaultDbLicenseCategory + '\'' +
                ", assignedDbLicenseCategory='" + assignedDbLicenseCategory + '\'' +
                ", assignedVmLicenseCategory='" + assignedVmLicenseCategory + '\'' +
                ", monitorState='" + monitorState + '\'' +
                ", oldestMonitoringDate=" + oldestMonitoringDate +
                ", latestMonitoringDate=" + latestMonitoringDate +
                ", isLinkedToVirtualMachine=" + isLinkedToVirtualMachine +
                ", isRds=" + isRds +
                ", isPdb=" + isPdb +
                ", isRac=" + isRac +
                ", isEBusiness=" + isEBusiness +
                ", agListenerName='" + agListenerName + '\'' +
                ", agClusterName='" + agClusterName + '\'' +
                ", agName='" + agName + '\'' +
                ", racInfo='" + racInfo + '\'' +
                ']';
    }

}
