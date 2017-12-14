package com.maciejak.myplaces_server.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class TopPlace {

    @Id
    private
    Long id;

    @Column
    private Integer rank;

    @Column
    private
    String name;

    @Column(length = 10000)
    private
    String description;

    @Column
    private
    String mainPhoto;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "topPlace")
    private
    List<TopPlacePhoto> photos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMainPhoto() {
        return mainPhoto;
    }

    public void setMainPhoto(String mainPhoto) {
        this.mainPhoto = mainPhoto;
    }

    public List<TopPlacePhoto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<TopPlacePhoto> photos) {
        this.photos = photos;
    }
}
