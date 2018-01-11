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
    private String placePhotoUrl;

    @Column
    private String placePhotoPath;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlacePhotoUrl() {
        return placePhotoUrl;
    }

    public void setPlacePhotoUrl(String placePhotoUrl) {
        this.placePhotoUrl = placePhotoUrl;
    }

    public String getPlacePhotoPath() {
        return placePhotoPath;
    }

    public void setPlacePhotoPath(String placePhotoPath) {
        this.placePhotoPath = placePhotoPath;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
