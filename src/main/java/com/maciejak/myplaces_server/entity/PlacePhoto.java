package com.maciejak.myplaces_server.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PlacePhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column
    String image;

    @ManyToOne
    Place place;

}
