package com.icegreen.repository;

import com.icegreen.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Long> {

    List<Shop> findAllByLocation(String location);
}
