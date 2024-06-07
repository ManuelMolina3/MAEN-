package com.triana.salesianos.dam.Maen.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Purchase {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    @Column(columnDefinition = "uuid")
    private UUID idPurchase;

    @ManyToOne
    private UsuarioMaen user;

    private LocalDate date;

    private double total;

    @ToString.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SalesLine> salesLines = new ArrayList<>();

    public void addSalesLine(SalesLine salesLine) {
        salesLines.add(salesLine);
        salesLine.setPurchase(this);
    }

    public void removeSalesLine(SalesLine salesLine) {
        salesLines.remove(salesLine);
        salesLine.setPurchase(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return Objects.equals(idPurchase, purchase.idPurchase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPurchase);
    }
}
