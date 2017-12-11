package com.maciejak.myplaces_server.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column
    String username;

    @Column
    String email;

    @Column
    String password;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
    List<Place> places;

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public List<Place> getActivePlaces() {
        return places.stream().filter(place -> place.getDeletedAt() == null).collect(Collectors.toList());
    }

    public List<Place> getArchivePlaces() {
        return places.stream().filter(place -> place.getDeletedAt() != null).collect(Collectors.toList());
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }
}
