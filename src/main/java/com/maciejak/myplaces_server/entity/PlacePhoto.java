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

}
