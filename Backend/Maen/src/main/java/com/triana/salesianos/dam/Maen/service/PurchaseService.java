package com.triana.salesianos.dam.Maen.service;

import com.triana.salesianos.dam.Maen.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository repository;


}
