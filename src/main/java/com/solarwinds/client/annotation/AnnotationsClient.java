package com.solarwinds.client.annotation;

import com.solarwinds.model.annotation.Annotation;

import java.time.OffsetDateTime;
import java.util.List;

public interface AnnotationsClient {

    List<Annotation> getAnnotations(int databaseId, OffsetDateTime startDate, OffsetDateTime endDate);

    Annotation createAnnotation(int databaseId, Annotation licenseAllocation);

}
