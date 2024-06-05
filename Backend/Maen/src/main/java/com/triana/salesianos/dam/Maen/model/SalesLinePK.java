package com.triana.salesianos.dam.Maen.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class SalesLinePK implements Serializable {
    private Purchase purchase;
    private UUID idSalesLine;

    private SalesLinePK(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesLinePK that = (SalesLinePK) o;
        return Objects.equals(purchase, that.purchase) && Objects.equals(idSalesLine, that.idSalesLine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchase, idSalesLine);
    }

}
