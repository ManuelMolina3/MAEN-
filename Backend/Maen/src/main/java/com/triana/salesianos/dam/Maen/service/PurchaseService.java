package com.triana.salesianos.dam.Maen.service;

import com.triana.salesianos.dam.Maen.dto.purchase.GetPurchaseDTO;
import com.triana.salesianos.dam.Maen.dto.purchase.GetPurchaseDetailsDTO;
import com.triana.salesianos.dam.Maen.exception.NotFoundException;
import com.triana.salesianos.dam.Maen.model.Purchase;
import com.triana.salesianos.dam.Maen.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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
        List<Purchase> purchases = repository.getPurchasebyMonth(from, to, idUserMaen);
        if(purchases.isEmpty()){
            throw new NotFoundException("Purchase");
        }else{
            List<GetPurchaseDTO> p = new ArrayList<>();
            for (int i = 0; i<purchases.size(); i++){
                GetPurchaseDTO g = GetPurchaseDTO.of(purchases.get(i));
                p.add(g);

            }
        return p;

        }

    }
    public GetPurchaseDetailsDTO getPurchaseDetailsById(UUID idPurchase){
        Optional<Purchase> purchase= repository.findById(idPurchase);
        if (purchase.isPresent())
            return GetPurchaseDetailsDTO.of(purchase.get(), purchase.get().getSalesLineList());

        throw new NotFoundException("Purchase Details");
    }

}
