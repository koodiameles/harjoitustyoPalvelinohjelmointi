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
public class Beveragetype {

    //OMINAISUUDET
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long typeid;
    private String name;                 // tyypin/kategorian nimi

	@JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "beveragetype")
    private List<Beverage> beverages;    // lista tämän kategorian juomille


    //KONSTRUKTORIT
    public Beveragetype(String name) {
        super();
		this.name = name;
	}
    public Beveragetype() {
        super();
	}

    //SETIT JA GETIT
	public Long getTypeid() {
		return typeid;
	}

	public void setTypeid(Long typeid) {
		this.typeid = typeid;
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
		return "Beveragetype [name=" + name + ", typeid=" + typeid + "]";
	}

}
