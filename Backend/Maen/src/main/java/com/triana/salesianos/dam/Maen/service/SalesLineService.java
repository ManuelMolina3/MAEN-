package com.triana.salesianos.dam.Maen.service;

import com.triana.salesianos.dam.Maen.repository.SalesLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalesLineService {

    private final SalesLineRepository repository;


}
