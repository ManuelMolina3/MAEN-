package com.triana.salesianos.dam.Maen.service;

import com.triana.salesianos.dam.Maen.dto.purchase.AddPurchaseDTO;
import com.triana.salesianos.dam.Maen.dto.purchase.GetPurchaseDTO;
import com.triana.salesianos.dam.Maen.dto.purchase.GetPurchaseDetailsDTO;
import com.triana.salesianos.dam.Maen.exception.NotFoundException;
import com.triana.salesianos.dam.Maen.exception.product.ProductNotFoundException;
import com.triana.salesianos.dam.Maen.model.Product;
import com.triana.salesianos.dam.Maen.model.Purchase;
import com.triana.salesianos.dam.Maen.model.SalesLine;
import com.triana.salesianos.dam.Maen.model.UsuarioMaen;
import com.triana.salesianos.dam.Maen.repository.ProductRepository;
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
    private final ProductRepository productRepository;


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
    public List<Purchase> getAllPurchase(){
        return repository.findAll();
    }
    public GetPurchaseDetailsDTO getPurchaseDetailsById(UUID idPurchase){
        Optional<Purchase> purchase= repository.findById(idPurchase);
        if (purchase.isPresent())
            return GetPurchaseDetailsDTO.of(purchase.get(), purchase.get().getSalesLines());

        throw new NotFoundException("Purchase Details");
    }

    public Purchase addProductToPurchase(String idProduct, UsuarioMaen u, LocalDate date){
        Optional<Product> product = productRepository.findProductById(idProduct);
        Optional<Purchase> purchase = repository.getPurchaseByDate(date, u.getId());

        if(product.isEmpty()){
            throw new ProductNotFoundException();
        }
        if (purchase.isEmpty()) {
            SalesLine newSalesLine = SalesLine.builder()
                    .price(product.get().getPrice())
                    .amount(1)
                    .product(product.get())
                    .subtotal(product.get().getPrice())
                    .build();
            Purchase newPurchase = Purchase.builder()
                    .idUserMaen(u.getId())
                    .date(date)
                    .total(getTotalPriceOfPurchase(purchase.get().getIdPurchase()))
                    .build();
            newPurchase.addSalesLine(newSalesLine);
            return repository.save(newPurchase);
        }
        Optional<SalesLine> lnExist = repository.findSalesLineByProduct(product.get().getId(), purchase.get().getIdPurchase());
        if(lnExist.isEmpty()){
            SalesLine newSalesLine = SalesLine.builder()
                    .price(product.get().getPrice())
                    .amount(1)
                    .product(product.get())
                    .subtotal(product.get().getPrice())
                    .build();
        }
        lnExist.get().setAmount(lnExist.get().getAmount()+1);
        purchase.get().removeSalesLine(lnExist.get());
        purchase.get().addSalesLine(lnExist.get());
        return repository.save(purchase.get());

    }
    public double getTotalPriceOfPurchase(UUID idPurchase){
        return 0.00;
    }

}
