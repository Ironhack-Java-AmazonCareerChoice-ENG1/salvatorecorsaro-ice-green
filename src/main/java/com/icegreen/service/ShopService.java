package com.icegreen.service;

import com.icegreen.dto.UpdateShopDto;
import com.icegreen.expeptions.IceCreamException;
import com.icegreen.model.Review;
import com.icegreen.model.Shop;
import com.icegreen.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;

    public List<Shop> findAll(Optional<String> location) {
        if (location.isPresent()) {
            return shopRepository.findAllByLocation(location.get());
        }
        return shopRepository.findAll();
    }

    public Shop findById(Long id) {
        return shopRepository.findById(id).orElseThrow();
    }

    @Transactional
    public Shop save(Shop shop) {
        if (shop.getDescription().equals("error")) {
            throw new IceCreamException("Oh oh there is an Error");
        }
        return shopRepository.save(shop);
    }

    @Transactional
    public Shop addReview(Long id, Review review) {
        Shop shop = shopRepository.findById(id).orElseThrow();
        shop.addReview(review);
        return shopRepository.save(shop);
    }

    @Transactional
    public Shop update(Long idOfTheShop, UpdateShopDto shop) {
        var x = shopRepository.findById(idOfTheShop);
        if (x.isPresent()) {
            var entity = x.get();
            entity.setName(shop.getName());
            entity.setLocation(shop.getLocation());
            entity.setDescription(shop.getDescription());
            return shopRepository.save(entity);
        } else {
            throw new RuntimeException("Shop not found");
        }

    }

    @Transactional
    public Shop updateName(Long id, String name) {
        var x = shopRepository.findById(id);
        if (x.isPresent()) {
            var entity = x.get();
            entity.setName(name);
            return shopRepository.save(entity);
        } else {
            throw new RuntimeException("Shop not found");
        }
    }

    public void delete(Long id) {
        shopRepository.deleteById(id);
    }
}
