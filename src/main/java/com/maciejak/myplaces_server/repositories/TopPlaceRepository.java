package com.maciejak.myplaces_server.repositories;

import com.maciejak.myplaces_server.entity.TopPlace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopPlaceRepository extends JpaRepository<TopPlace, Long> {
    public List<TopPlace> findAllByOrderByRankAsc();
}
