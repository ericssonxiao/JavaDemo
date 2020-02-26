package com.eric.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.Collection;


/**
 *
 */
//@Entity
//@Table(name = "setting")
public class Setting implements Serializable {

    private static final long serialVersionUID = -123530226345992392L;

    //    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @Column(name = "id")
    private String id;

    //    @Column(name = "requires")
    private Collection<String> requires;

    public Setting() {
    }

    public Setting(String id) {
        this.id = id;
    }

    public Setting(String id, Collection<String> requires) {
        this.id = id;
        this.requires = requires;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Collection<String> getRequires() {
        return requires;
    }

    public void setRequires(Collection<String> requires) {
        this.requires = requires;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Setting)) return false;

        Setting setting = (Setting) o;

        return new EqualsBuilder()
                .append(getId(), setting.getId())
                .append(getRequires(), setting.getRequires())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getRequires())
                .toHashCode();
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", requires=" + requires +
                '}';
    }
}
