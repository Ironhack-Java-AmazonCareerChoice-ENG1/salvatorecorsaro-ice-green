package com.icegreen.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    //    @Email
    @Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$",
        message = "Email should be valid")
    private String username;
    private String comment;
    @Min(value = 1, message = "Rating should be between 1 and 5")
    @Max(value = 5, message = "Rating should be between 1 and 5")
    private int rating;
    @CreationTimestamp
    private ZonedDateTime createdAt;

    @ManyToOne
    @JsonIgnore
    private Shop shop;
}
