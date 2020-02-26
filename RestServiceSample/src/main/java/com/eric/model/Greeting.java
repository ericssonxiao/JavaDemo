package com.eric.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * Greeting
 * this is demo test.
 */
public class Greeting implements Serializable {

    private static final long serialVersionUID = -854530226345992392L;

    private long id;
    private String content;

    public Greeting() {
    }

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Greeting)) return false;

        Greeting greeting = (Greeting) o;

        return new EqualsBuilder()
                .append(getId(), greeting.getId())
                .append(getContent(), greeting.getContent())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getContent())
                .toHashCode();
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
