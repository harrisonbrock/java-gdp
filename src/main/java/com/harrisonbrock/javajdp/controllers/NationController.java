package com.harrisonbrock.javajdp.controllers;

import com.harrisonbrock.javajdp.domain.Nation;
import com.harrisonbrock.javajdp.services.NationService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/gdp")
    public List<Nation> loadNations(@RequestBody List<Nation> nations) {
        return nationService.addAllNations(nations);
    }
}
