package com.maciejak.myplaces_server.repositories;

import com.maciejak.myplaces_server.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    Place findById(Long placeId);

    @Query("select p from Place p where p.deletedAt is null")
    List<Place> findAllActive();

    @Query("select p from Place p where p.deletedAt is not null")
    List<Place> findAllArchived();
}
