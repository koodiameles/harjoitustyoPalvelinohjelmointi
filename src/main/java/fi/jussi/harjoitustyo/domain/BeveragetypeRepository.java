package fi.jussi.harjoitustyo.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface BeveragetypeRepository extends JpaRepository<Beveragetype, Long> {
    
    List<Beveragetype> findByName(String name);
}
