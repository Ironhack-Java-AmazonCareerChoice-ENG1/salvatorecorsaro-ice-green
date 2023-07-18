package com.icegreen.service;

import com.icegreen.model.Review;
import com.icegreen.model.Shop;
import com.icegreen.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;

    public List<Shop> findAll() {
        return shopRepository.findAll();
    }

    public Shop findById(Long id) {
        return shopRepository.findById(id).orElseThrow();
    }

    @Transactional
    public Shop save(Shop shop) {
        return shopRepository.save(shop);
    }

    @Transactional
    public Shop addReview(Long id, Review review) {
        Shop shop = shopRepository.findById(id).orElseThrow();
        shop.addReview(review);
        return shopRepository.save(shop);
    }
}
