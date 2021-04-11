package fi.jussi.harjoitustyo;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.jussi.harjoitustyo.domain.Beverage;
import fi.jussi.harjoitustyo.domain.BeverageRepository;
import fi.jussi.harjoitustyo.domain.Beveragetype;
import fi.jussi.harjoitustyo.domain.BeveragetypeRepository;
import fi.jussi.harjoitustyo.domain.Maker;
import fi.jussi.harjoitustyo.domain.MakerRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BeverageRepositoryTest {

    @Autowired
	BeverageRepository juomatrepo;
	@Autowired
	BeveragetypeRepository tyypitrepo;
	@Autowired
	MakerRepository makerrepo;

	@Test
	public void findByNameIgnoreCaseShouldReturnListOfBeverages() {
		List<Beverage> beverages = juomatrepo.findByNameIgnoreCase("Omppuviini");
		assertThat(beverages).hasSize(1);
		assertThat(beverages.get(0).getStyle()).isEqualTo("Hedelmäviini");
	}

    @Test
	public void findByStyleIgnoreCaseShouldReturnListOfBeverages() {
		List<Beverage> beverages = juomatrepo.findByStyleIgnoreCase("Pale ale");
		assertThat(beverages).hasSize(2);
	}
	
	@Test 
	public void deleteBeverage() {
		List<Beverage> beverages = juomatrepo.findByNameIgnoreCase("Omppuviini");
		juomatrepo.deleteById(beverages.get(0).getId());
		beverages = juomatrepo.findByNameIgnoreCase("Omppuviini");
		assertThat(beverages).hasSize(0);
	}

	@Test
	public void insertNewBeverage() {
        tyypitrepo.save(new Beveragetype("Olut"));
        var olut = tyypitrepo.findByName("Olut").get(0);
        makerrepo.save(new Maker("Jussi"));
        var jussi = makerrepo.findByName("Jussi").get(0);
        Beverage beverage = new Beverage("Testi-Olut", jussi, "Stout", "Tuhti Stoutti", 3.5, 5.5, olut);
		juomatrepo.save(beverage);
		assertThat(beverage.getId()).isNotNull();
	}

}