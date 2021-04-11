package fi.jussi.harjoitustyo.web;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fi.jussi.harjoitustyo.domain.Beverage;
import fi.jussi.harjoitustyo.domain.BeverageRepository;
//import fi.jussi.harjoitustyo.domain.BeveragetypeRepository;
//import fi.jussi.harjoitustyo.domain.MakerRepository;


@RestController
public class RestfulController {

    @Autowired
	private BeverageRepository juomatrepo;
	/*@Autowired
	private BeveragetypeRepository tyypitrepo;
	@Autowired
	private MakerRepository makerrepo;
	*/
    
    
    // RESTful service to get all beverages
    @RequestMapping(value="/juomat", method = RequestMethod.GET)
    public  @ResponseBody List<Beverage> beveragelistRest() {	
        return (List<Beverage>) juomatrepo.findAll();
    }    

    // RESTful service to get beverage by name(hakusana)
    @RequestMapping(value="/search2", method = RequestMethod.GET)
    public @ResponseBody List<Beverage> findbeverageRest(@Param("name") String name) {	
		//return (List<Beverage>) juomatrepo.findAll();
    	return juomatrepo.findByNameIgnoreCase(name);
    } 

    // RESTful service to get beverage by keyword(hakusana)
	@RequestMapping(value={"/search3"}, method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public @ResponseBody List<Beverage> getsearch (@Param("keyword") String keyword, Model model) {
		System.out.println("GET search - Käyttäjän haku");
		System.out.println("Hakusana: " +  keyword);

		List<Beverage> beverages = juomatrepo.findByKeyword(keyword);
		model.addAttribute("keyword", keyword);
		model.addAttribute("beverages", beverages);

		return juomatrepo.findByKeyword(keyword);	}
}

