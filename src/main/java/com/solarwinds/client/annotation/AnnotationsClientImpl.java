package com.solarwinds.client.annotation;

import com.solarwinds.client.AbstractRestClient;
import com.solarwinds.model.DpaResponse;
import com.solarwinds.model.annotation.Annotation;
import com.solarwinds.util.Constants;

import java.time.OffsetDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

@Service
public class AnnotationsClientImpl extends AbstractRestClient implements AnnotationsClient {

    @Override
    public List<Annotation> getAnnotations(int databaseId, OffsetDateTime startDate, OffsetDateTime endDate) {
        System.out.println();
        System.out.println("===> getAnnotations(" + databaseId + ")");

        // http://localhost:8123/iwc/api/databases/{databaseId}/annotations?startTime={start}&endTime={end}
        String url = Constants.API_URL + "/databases/"+databaseId+"/annotations";

        Map<String, Object> queryParamMap = new LinkedHashMap<>(2);
        queryParamMap.put("startTime", dateTimeToString(startDate));
        queryParamMap.put("endTime", dateTimeToString(endDate));

        ParameterizedTypeReference<DpaResponse<List<Annotation>>> ptr = new ParameterizedTypeReference<>() {};
        DpaResponse<List<Annotation>> dpaResponse = httpGet(url, queryParamMap, ptr);

        return dpaResponse.getDataObject();
    }

    @Override
    public Annotation createAnnotation(int databaseId, Annotation annotation) {
        System.out.println();
        System.out.println("===> createAnnotation(" + databaseId + ", " + annotation + ")");

        // http://localhost:8123/iwc/api/databases/{databaseId}/annotations
        String url = Constants.API_URL + "/databases/"+databaseId+"/annotations";

        ParameterizedTypeReference<DpaResponse<Annotation>> ptr = new ParameterizedTypeReference<>() {};
        DpaResponse<Annotation> dpaResponse = httpPost(url, annotation, ptr);

        return dpaResponse.getDataObject();
    }

}
