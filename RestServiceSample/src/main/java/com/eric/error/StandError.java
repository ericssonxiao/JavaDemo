package com.eric.error;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * type — A URI identifier that categorizes the error
 * title — A brief, human-readable message about the error
 * status — The HTTP response code (optional)
 * detail — A human-readable explanation of the error
 * instance — A URI that identifies the specific occurrence of the error
 */
public class StandError implements Serializable {

    private static final long serialVersionUID = -114530226840992392L;

    private String type;
    private String title;
    private int status;
    private String detail;
    private String instance;

    public StandError(int status) {
        this.status = status;
    }

    public StandError(String type, String title, int status, String detail, String instance) {
        this.type = type;
        this.title = title;
        this.status = status;
        this.detail = detail;
        this.instance = instance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof StandError)) return false;

        StandError that = (StandError) o;

        return new EqualsBuilder()
                .append(getStatus(), that.getStatus())
                .append(getType(), that.getType())
                .append(getTitle(), that.getTitle())
                .append(getDetail(), that.getDetail())
                .append(getInstance(), that.getInstance())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getType())
                .append(getTitle())
                .append(getStatus())
                .append(getDetail())
                .append(getInstance())
                .toHashCode();
    }

    @Override
    public String toString() {
        return "StandError{" +
                "type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", status=" + status +
                ", detail='" + detail + '\'' +
                ", instance='" + instance + '\'' +
                '}';
    }
}
