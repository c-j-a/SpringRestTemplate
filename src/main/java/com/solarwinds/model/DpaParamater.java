package com.solarwinds.model;

public class DpaParamater {

    private String type;
    private Object value;

    DpaParamater(String type, Object value) {
        this.type = type;
        this.value = value;
    }

    public Object getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

}
