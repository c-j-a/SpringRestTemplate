package com.solarwinds.model.monitor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MonitorCommand {

    public static final String START = "START";
    public static final String STOP  = "STOP";

    private String command;

    private MonitorCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static MonitorCommand createStart() {
        return new MonitorCommand(START);
    }

    public static MonitorCommand createStop() {
        return new MonitorCommand(STOP);
    }

}
