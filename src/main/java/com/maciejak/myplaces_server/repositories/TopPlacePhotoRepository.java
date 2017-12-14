package com.maciejak.myplaces_server.repositories;


import com.maciejak.myplaces_server.entity.TopPlacePhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopPlacePhotoRepository extends JpaRepository<TopPlacePhoto, Long> {
}
