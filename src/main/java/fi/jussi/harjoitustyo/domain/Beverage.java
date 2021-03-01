package fi.jussi.harjoitustyo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Beverage {

    //OMINAISUUDET
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;			// id
    private String name;        // juoman nimi
    private String maker;       // juoman valmistaja
    private String style;       // juoman tarkempi tyyli, esim. lager, porter, punaviini, mansikkaviini
    private String notes;       // juoman sanallista kuvailua ja arvostelua
    private double points;      // juoman pisteet "tähdet" 1-5
    private double abv;         // alcohol by volume eli 'prossat' %

    @ManyToOne
    @JoinColumn(name = "typeid")
    private Beveragetype beveragetype;  // juoman tyyppi esim. olut, viini


    //KONSTRUKTORIT

    public Beverage(String name, String maker, String style, String notes, double points, double abv, Beveragetype beveragetype) {
		this.name = name;
		this.maker = maker;
		this.style = style;
		this.notes = notes;
		this.points = points;
		this.abv = abv;
		this.beveragetype = beveragetype;
	}

    public Beverage(String name) {
		super();
        this.name = name;
	}

    public Beverage() {
		super();
	}


    //SETIT JA GETIT
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public double getPoints() {
		return points;
	}

	public void setPoints(double points) {
		this.points = points;
	}

	public double getAbv() {
		return abv;
	}

	public void setAbv(double abv) {
		this.abv = abv;
	}

	public Beveragetype getBeveragetype() {
		return beveragetype;
	}

	public void setBeveragetype(Beveragetype beveragetype) {
		this.beveragetype = beveragetype;
	}

	@Override
	public String toString() {
		return "Beverage [abv=" + abv + ", id=" + id + ", maker=" + maker + ", name=" + name + ", notes=" + notes
				+ ", points=" + points + ", style=" + style + ", Beveragetype=" + beveragetype + "]";
	}

	
    
}
