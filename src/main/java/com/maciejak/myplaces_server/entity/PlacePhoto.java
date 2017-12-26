package com.maciejak.myplaces_server.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PlacePhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String image;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
