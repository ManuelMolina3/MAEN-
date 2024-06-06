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
public class ElectricityBill {

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    @Column(columnDefinition = "uuid")
    private UUID id;

    private Type type;

    private double power;

    private double pricePower;

    private double energy;

    private double priceEnergy;

    private double discount;

    private double equipment;

    private double taxes;

    private double total;

    @OneToOne
    private UsuarioMaen owner;


    @ManyToOne
    @JoinColumn(name = "company_id")
    private ElectricityCompany company;
}
