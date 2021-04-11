package fi.jussi.harjoitustyo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.jussi.harjoitustyo.domain.AppUser;
import fi.jussi.harjoitustyo.domain.AppUserRepository;
import fi.jussi.harjoitustyo.domain.Beverage;
import fi.jussi.harjoitustyo.domain.BeverageRepository;
import fi.jussi.harjoitustyo.domain.Beveragetype;
import fi.jussi.harjoitustyo.domain.BeveragetypeRepository;
import fi.jussi.harjoitustyo.domain.Maker;
import fi.jussi.harjoitustyo.domain.MakerRepository;

@SpringBootApplication
public class HarjoitustyoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarjoitustyoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BeverageRepository juomatrepo, BeveragetypeRepository tyypitrepo, MakerRepository makerrepo, AppUserRepository userrepo) {
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

			// Tekijöitä (maker)
			makerrepo.save(new Maker("-"));
			makerrepo.save(new Maker("Jussi"));
			makerrepo.save(new Maker("Jami"));
			var jussi = makerrepo.findByName("Jussi").get(0);
			var jami = makerrepo.findByName("Jami").get(0);

			// Testijuomat
			Beverage olut1 = new Beverage(
				"Galaxy HomeAPA", jussi, "Pale ale", "Mukava hedelmän ja katkeron tasapaino", 3.5, 5.5, olut);
			Beverage olut2 = new Beverage(
				"Jamin Sour", jami, "Sour ale", "Tyypillinen sour, mutta mikään maku ei tule happamuuden lisäksi hyvin esille", 2.5, 5.1, olut);
			Beverage olut3 = new Beverage(
				"Kaatisale", jami, "Pale ale", "Käytettyy 25% vehnää, joka tuo tietynlaista maltaista makeutta paremmin olueen", 3.0, 4.4, olut);
			Beverage viini1 = new Beverage(
				"Omppuviini", jussi, "Hedelmäviini", "Melko rapsakka ja kuiva, voisi kaivata jälkimakeutusta lisää", 2.5, 14.5, viini);
			Beverage siideri1 = new Beverage(
				"Omenasiideri",  jussi, "Omenasiideri", "Perinteinen kuiva englantilaistyylinen siideri", 3, 5.8, siideri);
			juomatrepo.save(olut1);
			juomatrepo.save(olut2);
			juomatrepo.save(olut3);
			juomatrepo.save(viini1);
			juomatrepo.save(siideri1);

			// Luo käyttäjät: admin/admin user/user
			AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			AppUser user2 = new AppUser("admin", "$2a$10$x7NK/ZZp9YeCSibCh.R3zeykNzEgPqSA4xzj.n2e4X.xeffNXF4HW", "ADMIN");
			userrepo.save(user1);
			userrepo.save(user2);
			// $2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C admin
		};
	}

}
