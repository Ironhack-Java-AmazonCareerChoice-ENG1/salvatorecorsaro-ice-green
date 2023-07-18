package com.icegreen.controller;

import com.icegreen.model.Review;
import com.icegreen.model.Shop;
import com.icegreen.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("shops")
public class ShopController {

    private final ShopService shopService;

    @GetMapping
    public List<Shop> getAllShops(@RequestParam Optional<String> location) {
        return shopService.findAll(location);
    }

    @GetMapping("/{id}")
    public Shop getShopById(@PathVariable Long id) {
        return shopService.findById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Shop createShop(@RequestBody Shop shop){
        return shopService.save(shop);
    }

    @PostMapping("/{id}/reviews")
    public Shop addReview(@PathVariable Long id, @RequestBody Review review){
        return shopService.addReview(id, review);
    }
}
