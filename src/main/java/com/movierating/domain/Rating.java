package com.movierating.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.*;


@Entity
@Table(name = "rating")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Rating {

    @Id
    @GeneratedValue()
    private long id;

    @Column(nullable = false)
    private String name;

    public Rating() {
    }
    
    public Rating(String name) {
        this.name = name;
    }

    public long getId() {
        return this.id;
    }

    // for tests ONLY
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Rating {" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

