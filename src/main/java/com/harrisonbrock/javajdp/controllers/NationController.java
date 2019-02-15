package com.harrisonbrock.javajdp.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.harrisonbrock.javajdp.domain.Nation;
import com.harrisonbrock.javajdp.services.NationService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NationController {
    private final NationService nationService;
    private final RabbitTemplate rabbitTemplate;

    public NationController(NationService nationService, RabbitTemplate rabbitTemplate) {
        this.nationService = nationService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/names")
    public List<Nation> getAllNationsSortedByName(){
        return nationService.getAllSortedByName();
    }

    @GetMapping("/economy")
    public List<Nation> getAllNationSoredByGDP() {
        return nationService.getAllSortedByGDP();
    }

    @GetMapping("/total")
    public ObjectNode getTotal() {
        return nationService.getTotal();
    }

    @GetMapping("/gdp/{name}")
    public Nation getNationByName(@PathVariable String name) {
        Nation nation = nationService.findNationByName(name);
        nationService.sendToQueue(rabbitTemplate, name);
        return nation;
    }

    @PostMapping("/gdp")
    public List<Nation> loadNations(@RequestBody List<Nation> nations) {
        return nationService.addAllNations(nations);
    }
}
