package fi.jussi.harjoitustyo.domain;

import org.springframework.data.repository.CrudRepository;

public interface BeverageRepository extends CrudRepository<Beverage, Long> {
    
}
