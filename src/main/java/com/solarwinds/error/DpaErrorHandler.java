package com.solarwinds.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solarwinds.model.DpaMessage;
import com.solarwinds.model.DpaParamater;
import com.solarwinds.model.DpaResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClientResponseException;

@Service
public class DpaErrorHandler implements ResponseErrorHandler {

    private ObjectMapper objectMapper;

    public void setObjectMapper(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        HttpStatus status = response.getStatusCode();
        HttpStatus.Series series = status.series();
        return (HttpStatus.Series.CLIENT_ERROR.equals(series) ||
                HttpStatus.Series.SERVER_ERROR.equals(series));
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

// Print the response JSON
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(response.getBody()))) {
//            System.out.println(br.lines().collect(Collectors.joining(System.lineSeparator())));
//        }

        System.out.println("Status Code: " + response.getStatusCode() + "  [" + response.getStatusCode().series() + "]");
        System.out.println("Status Text: " + response.getStatusText() );
        DpaResponse dpaResponse = objectMapper.readValue(response.getBody(), DpaResponse.class);
        StringBuilder sb = new StringBuilder();

        int statusCode = 0;
        List<DpaMessage> messages = dpaResponse.getMessages();
        for (int i = 0; i < messages.size(); i++) {
            DpaMessage message = messages.get(i);
            if (i == 0) {
                statusCode = message.getCode();
            }
            System.out.println(message.getSeverity() + ": " + message.getReason() + " [code: " + message.getCode() + "]");
            sb.append(message.getSeverity()).append(": ").append(message.getReason())
              .append(" [code: ").append(message.getCode()).append("]");
            Map<String, DpaParamater> params = message.getParams();
            if (params != null) {
                Set<Map.Entry<String, DpaParamater>> entries = params.entrySet();
                for (Map.Entry<String, DpaParamater> entry : entries) {
                    DpaParamater parameter = entry.getValue();
                    System.out.println("Parameter: " + entry.getKey() + " [type: " + parameter.getType() + ", value: " + parameter.getValue() + "]");
                }
            }
            if (i < (messages.size() - 1)) {
                sb.append(System.lineSeparator());
            }
        }
        throw new RestClientResponseException(sb.toString(), statusCode, null, null, null, null);
    }

}
