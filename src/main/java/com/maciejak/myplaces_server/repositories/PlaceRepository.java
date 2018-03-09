package com.maciejak.myplaces_server.repositories;

import com.maciejak.myplaces_server.entity.Place;
import com.maciejak.myplaces_server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    @Query("select p from Place p where p.deletedAt is null and p.user.id = ?1")
    List<Place> findAllActive(Long userId);

    @Query("select p from Place p where p.deletedAt is not null and p.user.id = ?1")
    List<Place> findAllArchived(Long userId);

    List<Place> findAllByUser(User user);
}
