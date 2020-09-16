package com.movierating.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.*;


@Entity
@Table(name = "movie")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Movie {

    @Id
    @GeneratedValue()
    private long id;

    @Column(nullable = false)
    private String name;

    public Movie() {
    }
    
    public Movie(String name) {
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
        return "Movie {" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
