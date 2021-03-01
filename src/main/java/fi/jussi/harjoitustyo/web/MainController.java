package fi.jussi.harjoitustyo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fi.jussi.harjoitustyo.domain.Beverage;
import fi.jussi.harjoitustyo.domain.BeverageRepository;
import fi.jussi.harjoitustyo.domain.Beveragetype;
import fi.jussi.harjoitustyo.domain.BeveragetypeRepository;

@Controller
public class MainController {

	@Autowired
	private BeverageRepository juomatrepo;
	@Autowired
	private BeveragetypeRepository tyypitrepo;
    
	// GET INDEX/MAIN PAGE
	@GetMapping(value={"/index", "/", "/mainpage"})
	public String getindex (Model model) {
		System.out.println("GET index - Siirry main pagelle");
		model.addAttribute("beverages", juomatrepo.findAll());
		return "index";
	}

	// GET ADMIN INDEX / (ADMIN NÄKYMÄ)
	@GetMapping(value={"/adminindex"})
	public String getadminindex (Model model) {
		System.out.println("GET adminindex - Siirry admin näkymään");
		model.addAttribute("beverages", juomatrepo.findAll());
		return "adminindex";
	}

	// EDIT BEVERAGE (Vain Admin)
	@GetMapping("/edit/{id}")
	public String editBeverage (@PathVariable("id") Long beverageId, Model model) {			
		System.out.println("GET ID" + beverageId + " - Edit beverage ");
		model.addAttribute("beverage", juomatrepo.findById(beverageId));
		model.addAttribute("beveragetypes", tyypitrepo.findAll());
		return "editbeverage";
	}

	// DELETE BEVERAGE (Vain Admin)
	@GetMapping("/delete/{id}")
	public String deleteBook (@PathVariable("id") Long beverageId, Model model) {					
		System.out.println("Delete beverage " + beverageId);
		juomatrepo.deleteById(beverageId);
		return "redirect:/adminindex";
	}
 
	//ADD BEVERAGE (Vain Admin)
	@GetMapping("/addbeverage")
	public String addBeverage (Model model) {					
		System.out.println("Add beverage ");
		model.addAttribute("beverage", new Beverage());
		model.addAttribute("beveragetypes", tyypitrepo.findAll());
		return "addbeverage";
	}

	//SAVE BEVERAGE (Vain Admin)
	@PostMapping("/save")
	public String saveBook (Beverage beverage) {					
		System.out.println("Save beverage ");
		juomatrepo.save(beverage);
		return "redirect:adminindex";
	}

}