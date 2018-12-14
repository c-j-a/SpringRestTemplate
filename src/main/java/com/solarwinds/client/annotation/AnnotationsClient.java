package com.solarwinds.client.annotation;

import com.solarwinds.model.annotation.Annotation;

import java.time.OffsetDateTime;
import java.util.List;

public interface AnnotationsClient {

    /**
     * Gets a List of Annotation objects for the requested database.
     *
     * @param databaseId The database id to get annotations for.
     * @param startDate The starting date to query.
     * @param endDate The ending date to query.
     * @return A List of Annotations within the provided date range.
     */
    List<Annotation> getAnnotations(int databaseId, OffsetDateTime startDate, OffsetDateTime endDate);

    /**
     * Create an annotation for the provided database.
     *
     * @param databaseId The database id to create the annotations for.
     * @param annotation The annotation details.
     * @return The newly created annotation.
     */
    Annotation createAnnotation(int databaseId, Annotation annotation);

}
