package fi.jussi.harjoitustyo.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;


public interface BeveragetypeRepository extends CrudRepository<Beveragetype, Long> {
    
    List<Beveragetype> findByName(String name);
}
