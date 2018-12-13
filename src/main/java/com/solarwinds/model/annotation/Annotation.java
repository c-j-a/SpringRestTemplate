package com.solarwinds.model.annotation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.OffsetDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Annotation {

    private int id;
    private String title;
    private String description;
    private String createdBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private OffsetDateTime time;
    private String type;


    /**
     * The annotation id.
     * id is read-only, meaning it is assigned by DPA, it can't be assign through the API.
     *
     * @return The annotation id.
     */
    public int getId() {
        return id;
    }

    /**
     * The annotation id.
     * id is read-only, meaning it is assigned by DPA, it can't be assign through the API.
     *
     * @param id The annotation id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the title of the Annotation.
     *
     * @return The title of the Annotation.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the Annotation.
     *
     * @param title The title of the Annotation.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the description of the annotation.
     *
     * @return The description of the annotation.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the annotation.
     *
     * @param description The description of the annotation.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the user that created the annotation.
     *
     * @return The user that created the annotation.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the user that created the annotation.
     *
     * @param createdBy The user that created the annotation.
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets the time of the annotation occurred.
     *
     * @return The time of the annotation.
     */
    public OffsetDateTime getTime() {
        return time;
    }

    /**
     * Sets the time of the annotation occurred.
     * @param time
     */
    public void setTime(OffsetDateTime time) {
        this.time = time;
    }

    /**
     * Gets the type of Annotation.
     * <ul>
     *     <li>CUSTOM - Custom User Created</li>
     *     <li>PDB - PDB move</li>
     *     <li>IDLE - Idle Blocker</li>
     *     <li>AG_FAILOVER - AG Failover</li>
     * </ul>
     * Type is read-only, meaning it is assigned by DPA, it can't be assign through the API.
     *
     * @return the annotation type.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of Annotation.
     * <ul>
     *     <li>CUSTOM - Custom User Created</li>
     *     <li>PDB - PDB move</li>
     *     <li>IDLE - Idle Blocker</li>
     *     <li>AG_FAILOVER - AG Failover</li>
     * </ul>
     * Type is read-only, meaning it is assigned by DPA, it can't be assign through the API.
     *
     * @return the annotation type.
     */
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", time=" + time +
                ", type='" + type + '\'' +
                ']';
    }

}
