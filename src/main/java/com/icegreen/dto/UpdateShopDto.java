package com.icegreen.dto;

import lombok.Data;

@Data
public class UpdateShopDto {
    private Long id;
    private String name;
    private String location;
    private String description;
}
