package com.triana.salesianos.dam.Maen.service;

import com.triana.salesianos.dam.Maen.dto.purchase.GetPurchaseDTO;
import com.triana.salesianos.dam.Maen.model.Purchase;
import com.triana.salesianos.dam.Maen.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository repository;


    public List<GetPurchaseDTO> obtenerVentasDeUnMes(int year, int month, UUID idUserMaen) {
        LocalDate from = LocalDate.of(year, month, 1);
        LocalDate to = from.plusMonths(1).minusDays(1);
        Optional<List<Purchase>> purchases = repository.getPurchasebyMonth(from, to, idUserMaen);
        if(purchases.isEmpty()){
            return ;
        }else{
            return ;
        }

    }

}
