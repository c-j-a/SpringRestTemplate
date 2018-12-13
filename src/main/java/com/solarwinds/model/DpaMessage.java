package com.solarwinds.model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class DpaMessage {

    private int code;
    private String helpUrl;
    private String reason;
    private String severity;
    private Map<String, DpaParamater> params;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getHelpUrl() {
        return helpUrl;
    }

    public void setHelpUrl(String helpUrl) {
        this.helpUrl = helpUrl;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public Map<String, DpaParamater> getParams() {
        return params;
    }

    public void setParams(Map<String, Map<Object, Object>> params) {
        if (params != null) {
            Map<String, DpaParamater> newParams = new LinkedHashMap<>();
            Set<Map.Entry<String, Map<Object, Object>>> mapSet = params.entrySet();
            for (Map.Entry<String, Map<Object, Object>> mapEntry : mapSet) {
                Map<Object, Object> paramMap = mapEntry.getValue();
                Object type = paramMap.get("type");
                Object value = paramMap.get("value");
                if (type != null && value != null) {
                    String key = mapEntry.getKey();
                    newParams.put(key, new DpaParamater(type.toString(), value));
                }
            }
            this.params = newParams;
        }
    }

}
