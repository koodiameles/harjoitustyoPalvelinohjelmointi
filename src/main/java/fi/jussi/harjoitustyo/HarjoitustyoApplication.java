package fi.jussi.harjoitustyo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.jussi.harjoitustyo.domain.Beverage;
import fi.jussi.harjoitustyo.domain.BeverageRepository;
import fi.jussi.harjoitustyo.domain.Beveragetype;
import fi.jussi.harjoitustyo.domain.BeveragetypeRepository;

@SpringBootApplication
public class HarjoitustyoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarjoitustyoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BeverageRepository juomatrepo, BeveragetypeRepository tyypitrepo) {
		return (args) -> {

			//Tallenna testidataa tietokantaan//

			//Juoman tyyppi/kategoria
			tyypitrepo.save(new Beveragetype("Viini"));
			tyypitrepo.save(new Beveragetype("Olut"));
			tyypitrepo.save(new Beveragetype("Siideri"));
			tyypitrepo.save(new Beveragetype("Muu"));
			var olut = tyypitrepo.findByName("Olut").get(0);
			var viini = tyypitrepo.findByName("Viini").get(0);
			var siideri = tyypitrepo.findByName("Siideri").get(0);
			var muu = tyypitrepo.findByName("Muu").get(0);

			//3 kappaletta testijuomaa
			Beverage olut1 = new Beverage(
				"Galaxy HomeAPA", "Jussi", "Pale ale", "Mukava hedelmän ja katkeron tasapaino", 3.5, 5.5, olut);
			Beverage olut2 = new Beverage(
				"Jamin Sour", "Jami", "Sour ale", "Tyypillinen sour, mutta mikään maku ei tule happamuuden lisäksi hyvin esille", 2.5, 5.1, olut);
			Beverage viini1 = new Beverage(
				"Omppuviini", "Jussi", "Hedelmäviini", "Melko rapsakka ja kuiva, voisi kaivata jälkimakeutusta lisää", 2.5, 14.5, viini);
			Beverage siideri1 = new Beverage(
				"Omenasiideri",  "Jussi", "Omenasiideri", "Perinteinen kuiva englantilaistyylinen siideri", 3, 5.8, siideri);
			juomatrepo.save(olut1);
			juomatrepo.save(olut2);
			juomatrepo.save(viini1);
			juomatrepo.save(siideri1);

		};
	}

}
