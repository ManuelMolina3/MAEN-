package com.triana.salesianos.dam.Maen.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesLine {

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
    private UUID idSalesLine;

    @Id
    @ManyToOne
    private Purchase purchase;

    private int amount;

    private double price;

    private double subtotal;

    @ManyToOne
    private Product product;

    public void setId(SalesLinePK pk){
        this.purchase = pk.getPurchase();
        this.idSalesLine = pk.getIdSalesLine();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesLine that = (SalesLine) o;
        return Objects.equals(idSalesLine, that.idSalesLine) && Objects.equals(purchase, that.purchase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSalesLine, purchase);
    }
}
