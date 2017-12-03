package com.maciejak.myplaces_server.repositories;

import com.maciejak.myplaces_server.entity.PlacePhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlacePhotoRepository extends JpaRepository<PlacePhoto, Long> {
}
