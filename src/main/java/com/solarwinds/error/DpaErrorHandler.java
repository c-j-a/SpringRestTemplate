package com.solarwinds.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solarwinds.model.DpaResponse;
import com.solarwinds.model.ResponseMessage;

import java.io.IOException;
import java.util.List;

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
        List<ResponseMessage> responseMessages = dpaResponse.getMessages();
        for (int i = 0; i < responseMessages.size(); i++) {
            ResponseMessage responseMessage = responseMessages.get(i);
            if (i == 0) {
                statusCode = responseMessage.getCode();
            }
            System.out.println(responseMessage.getSeverity() + ": " + responseMessage.getReason() + " [" + responseMessage.getCode() + "]");
            sb.append(responseMessage.getSeverity()).append(": ").append(responseMessage.getReason())
              .append(" [code: ").append(responseMessage.getCode()).append("]");
            if (i < responseMessages.size() -1) {
                sb.append(System.lineSeparator());
            }
        }
        throw new RestClientResponseException(sb.toString(), statusCode, null, null, null, null);
    }

}
