package com.harrisonbrock.javajdp.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Nation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String country;
    private Long gdp;

    public Nation() {
    }

    public Nation(String country, Long gdp) {
        this.country = country;
        this.gdp = gdp;
    }
}
