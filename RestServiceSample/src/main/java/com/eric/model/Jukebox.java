package com.eric.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;


/**
 *
 */
//@Entity
//@Table(name = "jukebox")
public class Jukebox implements Serializable {

    private static final long serialVersionUID = -854530226840992392L;

    //    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id")
    private String id;

    //    @Column(name = "model")
    private String model;

    //    @Column(name = "components")
    private Collection<HashMap<String, String>> components;

    public Jukebox() {
    }

    public Jukebox(String id, String model) {
        this.id = id;
        this.model = model;
    }

    public Jukebox(String id, String model, Collection<HashMap<String, String>> components) {
        this.id = id;
        this.model = model;
        this.components = components;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Collection<HashMap<String, String>> getComponents() {
        return components;
    }

    public void setComponents(Collection<HashMap<String, String>> components) {
        this.components = components;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Jukebox)) return false;

        Jukebox jukebox = (Jukebox) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(getId(), jukebox.getId())
                .append(getModel(), jukebox.getModel())
                .append(getComponents(), jukebox.getComponents())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
                .append(getId())
                .append(getModel())
                .append(getComponents())
                .toHashCode();
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", model='" + model + '\'' +
                ", components=" + components +
                '}';
    }
}
