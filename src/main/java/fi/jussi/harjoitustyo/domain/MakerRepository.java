package fi.jussi.harjoitustyo.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface MakerRepository extends JpaRepository<Maker, Long> {
    
    List<Maker> findByName(String name);
}
