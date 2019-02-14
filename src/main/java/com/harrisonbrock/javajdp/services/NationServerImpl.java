package com.harrisonbrock.javajdp.services;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.harrisonbrock.javajdp.domain.Nation;
import com.harrisonbrock.javajdp.repositeroies.NationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class NationServerImpl  implements NationService{

    private final NationRepository repository;

    public NationServerImpl(NationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Nation> getAllSortedByName() {
        return repository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Nation::getCountry))
                .collect(Collectors.toList());
    }

    @Override
    public List<Nation> getAllSortedByGDP() {
        return repository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Nation::getDpg).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public ObjectNode getTotal() {
        return null;
    }
}
