package com.harrisonbrock.javajdp.services;


import com.fasterxml.jackson.databind.node.ObjectNode;
import com.harrisonbrock.javajdp.domain.Nation;

import java.util.List;

public interface NationService {
    List<Nation> getAllSortedByName();
    List<Nation> getAllSortedByGDP();
    List<Nation> addAllNations(List<Nation> nations);
    ObjectNode getTotal();
}
