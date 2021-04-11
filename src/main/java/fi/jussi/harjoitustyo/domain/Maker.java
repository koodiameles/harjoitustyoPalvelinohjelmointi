package fi.jussi.harjoitustyo.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Maker {

    //OMINAISUUDET
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long makerid;
    private String name;                 //  tekijän nimi

	@JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maker")
    private List<Beverage> beverages;    // lista tämän tekijän tekemille juomille


    //KONSTRUKTORIT
    public Maker(String name) {
        super();
		this.name = name;
	}
    public Maker() {
        super();
	}

    //SETIT JA GETIT
	public Long getMakerid() {
		return makerid;
	}

	public void setMakerid(Long makerid) {
		this.makerid = makerid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Beverage> getBeverages() {
		return beverages;
	}

	public void setBeverages(List<Beverage> beverages) {
		this.beverages = beverages;
	}

	
	@Override
	public String toString() {
		return "Beveragetype [name=" + name + ", makerid=" + makerid + "]";
	}

}
