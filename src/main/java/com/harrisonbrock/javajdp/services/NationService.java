package com.harrisonbrock.javajdp.services;


import com.fasterxml.jackson.databind.node.ObjectNode;
import com.harrisonbrock.javajdp.domain.Nation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.List;

public interface NationService {
    List<Nation> getAllSortedByName();
    List<Nation> getAllSortedByGDP();
    List<Nation> addAllNations(List<Nation> nations);
    Nation findNationByName(String name);
    ObjectNode getTotal();
    void sendToQueue(RabbitTemplate rabbitTemplate, String country);
}
