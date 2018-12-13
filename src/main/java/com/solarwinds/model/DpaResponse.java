package com.solarwinds.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class represents the json that DPA returns.
 * The Data object will hold the real return type of the data.
 * <code>
 *   ResponseEntity<DpaResponse<LicenseInfo>> response =
 *       restTemplate.exchange(url, HttpMethod.PUT, request, new ParameterizedTypeReference<DpaResponse<LicenseInfo>>() {});
 *
 *   DpaResponse<LicenseInfo> dpaResponse = response.getBody();
 *   LicenseInfo licenseInfo = dpaResponse.getDataObject();
 * </code>
 *
 *    "data": {
 *      "serverName": "string",
 *      "performanceLicenseProduct": "DPACAT1",
 *      "vmLicenseProduct": "DPAVM",
 *      "overLicensed": true
 *    },
 *    "messages": [
 *      {
 *        "code": 0,
 *        "helpUrl": "string",
 *        "params": {
 *          "additionalProp1": {
 *            "type": "NUMBER",
 *            "value": {}
 *          },
 *          "additionalProp2": {
 *            "type": "NUMBER",
 *            "value": {}
 *          },
 *          "additionalProp3": {
 *            "type": "NUMBER",
 *            "value": {}
 *          }
 *        },
 *        "reason": "string",
 *        "severity": "INFO"
 *      }
 *    ],
 *    "requestId": "string"
 *  }
 *
 * @param <T> The type of the Data Object to return
 */
public class DpaResponse<T> {

    private T data;
    private String requestId;
    private List<DpaMessage> messages = new ArrayList<>();

    public void setData(T data) {
        this.data = data;
    }

    public T getDataObject() {
        return data;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public List<DpaMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<Map<String, Object>> messages) {
        for (Map map : messages) {
            DpaMessage message = new DpaMessage();
            message.setCode((int)map.get("code"));
            message.setHelpUrl((String)map.get("helpUrl"));
            message.setReason((String)map.get("reason"));
            message.setSeverity((String)map.get("severity"));
            message.setParams((Map<String, Map<Object, Object>>) map.get("params"));
            this.messages.add(message);
        }
    }

}
