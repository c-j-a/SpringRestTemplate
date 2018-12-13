package com.solarwinds.client.monitor;

import com.solarwinds.client.AbstractRestClient;
import com.solarwinds.model.DpaResponse;
import com.solarwinds.model.monitor.MonitorCommand;
import com.solarwinds.model.monitor.MonitorPassword;
import com.solarwinds.model.monitor.MonitoredInstance;
import com.solarwinds.util.Constants;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MonitorClientImpl extends AbstractRestClient implements MonitorClient {

    @Override
    public MonitoredInstance getMonitorInformation(final int databaseId) {
        System.out.println();
        System.out.println("===> getMonitorInformation(" + databaseId + ")");

        // http://localhost:8123/iwc/api/databases/{databaseId}/monitor-information
        String url = Constants.API_URL + "/databases/"+databaseId+"/monitor-information";

        HttpEntity<String> request = new HttpEntity<>(getHttpHeadersJson());

        ResponseEntity<DpaResponse<MonitoredInstance>> response =
                restTemplate.exchange(url, HttpMethod.GET, request, new ParameterizedTypeReference<DpaResponse<MonitoredInstance>>() {});

        DpaResponse<MonitoredInstance> dpaResponse = response.getBody();

        return dpaResponse.getDataObject();
    }

    @Override
    public List<MonitoredInstance> getMonitorInformation() {
        System.out.println();
        System.out.println("===> getMonitorInformation()");

        // http://localhost:8123/iwc/api/databases/monitor-information
        String url = Constants.API_URL + "/databases/monitor-information";

        HttpEntity<String> request = new HttpEntity<>(getHttpHeadersJson());

        ResponseEntity<DpaResponse<List<MonitoredInstance>>> response =
                restTemplate.exchange(url, HttpMethod.GET, request, new ParameterizedTypeReference<DpaResponse<List<MonitoredInstance>>>() {});

        DpaResponse<List<MonitoredInstance>> dpaResponse = response.getBody();

        return dpaResponse.getDataObject();
    }

    @Override
    public String setMonitorStatus(final MonitorCommand command) {
        System.out.println();
        System.out.println("===> setMonitorStatus( " + command.getCommand() + ")");

        // http://localhost:8123/iwc/api/databases/monitor-status
        String url = Constants.API_URL + "/databases/monitor-status";

        HttpEntity<MonitorCommand> request = new HttpEntity<>(command, getHttpHeadersJson());

        ResponseEntity<DpaResponse<String>> response =
                restTemplate.exchange(url, HttpMethod.PUT, request, new ParameterizedTypeReference<DpaResponse<String>>() {});

        DpaResponse<String> dpaResponse = response.getBody();

        return dpaResponse.getDataObject();
    }

    @Override
    public String setMonitorStatus(final int databaseId, final MonitorCommand command) {
        System.out.println();
        System.out.println("===> setMonitorStatus(" + databaseId + ", " + command.getCommand() + ")");

        // http://localhost:8123/iwc/api/databases/{databaseId}/monitor-status
        String url = Constants.API_URL + "/databases/"+databaseId+"/monitor-status";

        HttpEntity<MonitorCommand> request = new HttpEntity<>(command, getHttpHeadersJson());

        ResponseEntity<DpaResponse<String>> response =
                restTemplate.exchange(url, HttpMethod.PUT, request, new ParameterizedTypeReference<DpaResponse<String>>() {});

        DpaResponse<String> dpaResponse = response.getBody();

        return dpaResponse.getDataObject();
    }

    @Override
    public String updateMonitorPassword(final int databaseId, final MonitorPassword newPassword) {
        // http://localhost:8123/iwc/api/databases/{databaseId}/update-password
        String url = Constants.API_URL + "/databases/"+databaseId+"/update-password";
        System.out.println();
        System.out.println("===> updateMonitorPassword(" + databaseId + ", " + newPassword.getPassword() + ")");

        HttpEntity<MonitorPassword> request = new HttpEntity<>(newPassword, getHttpHeadersJson());

        ResponseEntity<DpaResponse<String>> response =
                restTemplate.exchange(url, HttpMethod.PUT, request, new ParameterizedTypeReference<DpaResponse<String>>() {});

        DpaResponse<String> dpaResponse = response.getBody();

        return dpaResponse.getDataObject();
    }

}
