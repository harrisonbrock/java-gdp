package com.harrisonbrock.javajdp.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.harrisonbrock.javajdp.JavaJdpApplication;
import com.harrisonbrock.javajdp.domain.Nation;
import com.harrisonbrock.javajdp.log.NationLogMessage;
import com.harrisonbrock.javajdp.repositeroies.NationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class NationServerImpl implements NationService {

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
        log.info("Before creating the list");
        List<Nation> nations = repository.findAll()
                .stream()
                .sorted(Comparator.comparing(Nation::getGdp).reversed())
                .collect(Collectors.toList());
        log.info("Nations List Size: " + nations.size());
        return nations;
    }

    @Override
    public List<Nation> addAllNations(List<Nation> nations) {
        return repository.saveAll(nations);
    }

    @Override
    public Nation findNationByName(String name) {
        return repository.findByCountry(name);
    }

    @Override
    public ObjectNode getTotal() {
        List<Nation> nations = repository.findAll();
        long total = 0;
        for (Nation nation : nations) {
            total = total + nation.getGdp();
        }
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode totalGdp = mapper.createObjectNode();
        totalGdp.put("id", 0);
        totalGdp.put("nation", "gdp");
        totalGdp.put("totalGDP", total);
        return totalGdp;
    }

    @Override
    public void sendToQueue(RabbitTemplate rabbitTemplate, String country) {
        log.info("Sending message");
        NationLogMessage message = new NationLogMessage("Logged User Looked Up Country: " + country);
        rabbitTemplate.convertAndSend(JavaJdpApplication.QUEUE_NAME, message.getText());
    }
}
