package com.solarwinds.client.monitor;

import com.solarwinds.model.monitor.MonitorCommand;
import com.solarwinds.model.monitor.MonitorPassword;
import com.solarwinds.model.monitor.MonitoredInstance;

import java.util.List;

public interface MonitorClient {

    enum MonitorState {
        RUNNING("Monitor Running"),
        STOPPED("Monitor Stopped"),
        START_PENDING("Monitor Start Pending"),
        STOP("Stop Pending"),
        DISABLED("Monitor Disabled"),
        BLACKED_OUT("Monitor in Blackout Period"),
        START_NO_LICENSE("Monitor Start No License"),
        STOP_NO_LICENSE("Monitor Stop No License");

        private String state;
        MonitorState(String state) {
            this.state = state;
        }
        public String getState() {
            return this.state;
        }
    }

    /**
     * Gets information about all the database instances.
     *
     * @return A List of MonitoredInstance
     */
    List<MonitoredInstance> getMonitorInformation();

    /**
     * Gets information about the requested database instance.
     *
     * @return A MonitoredInstance
     */
    MonitoredInstance getMonitorInformation(int databaseId);

    /**
     * Start or Stop all monitoring database instances
     *
     * @param command The MonitorCommand (START or STOP)
     * @return The result String, usually SUCCESS
     */
    String setMonitorStatus(MonitorCommand command);

    /**
     * Start or Stop monitoring on the provided database instances
     *
     * @param databaseId The Id of the monitored instance to stop or start
     * @param command The MonitorCommand (START or STOP)@param command
     * @return The result String, usually SUCCESS
     */
    String setMonitorStatus(int databaseId, MonitorCommand command);

    /**
     * Update the monitored database password for the specified database id.
     *
     * @param databaseId The Id of the monitored instance to stop or start
     * @param newPassword The MonitorPassword with the new password
     * @return The result String, usually SUCCESS
     */
    String updateMonitorPassword(int databaseId, MonitorPassword newPassword);

}
