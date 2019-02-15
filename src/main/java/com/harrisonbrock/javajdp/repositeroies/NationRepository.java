package com.harrisonbrock.javajdp.repositeroies;

import com.harrisonbrock.javajdp.domain.Nation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NationRepository extends JpaRepository<Nation, Long> {
    Nation findByCountry(String country);
}
