package com.maciejak.myplaces_server.api.dto.response;

import java.util.List;

public class TopPlaceResponse {

    private Long id;
    private Integer rank;
    private String name;
    private String description;
    private String mainPhoto;
    private List<TopPlacePhotoResponse> photos;

    public TopPlaceResponse() {
    }

    public TopPlaceResponse(Long id,
                            Integer rank,
                            String name,
                            String description,
                            String mainPhoto,
                            List<TopPlacePhotoResponse> photos) {
        this.id = id;
        this.rank = rank;
        this.name = name;
        this.description = description;
        this.mainPhoto = mainPhoto;
        this.photos = photos;
    }

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

    public List<TopPlacePhotoResponse> getPhotos() {
        return photos;
    }

    public void setPhotos(List<TopPlacePhotoResponse> photos) {
        this.photos = photos;
    }
}
