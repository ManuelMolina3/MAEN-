package com.triana.salesianos.dam.Maen.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    @Column(columnDefinition = "uuid")
    private UUID id;

    private String name;

    private String image;

    private String brand;

    private double price;

    private double priceKg;

    private int taxes;

    private Category category;

    @ManyToOne
    @JoinColumn(name = "supermarket_id")
    private SuperMarket superMarket;
}
