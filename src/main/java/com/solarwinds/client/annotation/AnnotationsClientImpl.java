package com.solarwinds.client.annotation;

import com.solarwinds.client.AbstractRestClient;
import com.solarwinds.model.DpaResponse;
import com.solarwinds.model.annotation.Annotation;
import com.solarwinds.util.Constants;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class AnnotationsClientImpl extends AbstractRestClient implements AnnotationsClient {

    @Override
    public List<Annotation> getAnnotations(int databaseId, OffsetDateTime startDate, OffsetDateTime endDate) {
        System.out.println();
        System.out.println("===> getAnnotations(" + databaseId + ")");

        // http://localhost:8123/iwc/api/databases/{databaseId}/annotations?startTime={start}&endTime={end}
        String url = Constants.API_URL + "/databases/"+databaseId+"/annotations";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("startTime", dateTimeToString(startDate))
                .queryParam("endTime", dateTimeToString(endDate));
        url = builder.toUriString();

        HttpEntity<String> request = new HttpEntity<>(getHttpHeadersJson());

        ResponseEntity<DpaResponse<List<Annotation>>> response =
                restTemplate.exchange(url, HttpMethod.GET, request, new ParameterizedTypeReference<DpaResponse<List<Annotation>>>() {});

        if (response.hasBody()) {
            DpaResponse<List<Annotation>> dpaResponse = response.getBody();
            return dpaResponse.getDataObject();
        }

        return Collections.emptyList();
    }

    @Override
    public Annotation createAnnotation(int databaseId, Annotation annotation) {
        System.out.println();
        System.out.println("===> createAnnotation(" + databaseId + ", " + annotation + ")");

        // http://localhost:8123/iwc/api/databases/{databaseId}/annotations
        String url = Constants.API_URL + "/databases/"+databaseId+"/annotations";

        HttpEntity<Annotation> request = new HttpEntity<>(annotation, getHttpHeadersJson());

        ResponseEntity<DpaResponse<Annotation>> response =
                restTemplate.exchange(url, HttpMethod.POST, request, new ParameterizedTypeReference<DpaResponse<Annotation>>() {});

        if (response.hasBody()) {
            DpaResponse<Annotation> dpaResponse = response.getBody();
            return dpaResponse.getDataObject();
        }

        return null;
    }

}
