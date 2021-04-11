package fi.jussi.harjoitustyo.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BeverageRepository extends CrudRepository <Beverage, Long> {

    // Hakuja (ignoring case)
    List<Beverage> findByNameIgnoreCase(String name);
    List<Beverage> findByStyleIgnoreCase(String style);


    // Luodaan tietokantakysely(query). Etsii juomaa nimen, tekijän, tyypin, tyylin, alkoholin tai pisteiden perusteella.
    @Query(value=
    "SELECT *"
    + " FROM Beverage"
    + " WHERE makerid =(SELECT makerid FROM Maker WHERE name iLIKE %:keyword%)"
    + " OR typeid =(SELECT typeid FROM Beveragetype WHERE name iLIKE %:keyword%)"
    + " OR name iLIKE %:keyword%"
    + " OR points iLIKE %:keyword%"
    + " OR abv iLIKE %:keyword%"
    + " OR style iLIKE %:keyword%", nativeQuery = true)
    List<Beverage> findByKeyword (@Param("keyword") String keyword);
    //Etsii juomaa käyttäjän hakusanan(keyword)(url-parametri) perusteella. Hyödyntää yllä ollevaa querya.
    
  
}
