package com.maciejak.myplaces_server.repositories;

import com.maciejak.myplaces_server.entity.PlacePhoto;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlacePhotoRepository extends JpaRepository<PlacePhoto, Long> {
}
