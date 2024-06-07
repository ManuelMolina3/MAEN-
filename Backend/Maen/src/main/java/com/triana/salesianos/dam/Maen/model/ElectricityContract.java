package com.triana.salesianos.dam.Maen.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ElectricityContract {

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    @Column(columnDefinition = "uuid")
    private UUID id;

    private double priceEnergy;

    private int discountEnergy;

    private double pricePower;

    private double priceEquipment;

    private double taxes;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private ElectricityCompany company;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<UsuarioMaen> users;
}
