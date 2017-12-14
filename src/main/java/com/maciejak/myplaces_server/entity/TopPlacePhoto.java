package com.maciejak.myplaces_server.entity;

import javax.persistence.*;

@Entity
public class TopPlacePhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String photo;

    @ManyToOne
    @JoinColumn(name = "top_place_id")
    private
    TopPlace topPlace;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public TopPlace getTopPlace() {
        return topPlace;
    }

    public void setTopPlace(TopPlace topPlace) {
        this.topPlace = topPlace;
    }
}
