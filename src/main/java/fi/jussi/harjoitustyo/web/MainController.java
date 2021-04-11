package fi.jussi.harjoitustyo.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fi.jussi.harjoitustyo.domain.Beverage;
import fi.jussi.harjoitustyo.domain.BeverageRepository;
import fi.jussi.harjoitustyo.domain.Beveragetype;
import fi.jussi.harjoitustyo.domain.BeveragetypeRepository;
import fi.jussi.harjoitustyo.domain.Maker;
import fi.jussi.harjoitustyo.domain.MakerRepository;

@Controller
public class MainController {

	@Autowired
	private BeverageRepository juomatrepo;
	@Autowired
	private BeveragetypeRepository tyypitrepo;
	@Autowired
	private MakerRepository makerrepo;
    

	// GET INDEX - SHOW MAIN PAGE
	@GetMapping(value={"/index", "/", "/mainpage"})
	//@PreAuthorize("hasAuthority('ADMIN')")
	public String getindex (Model model) {
		System.out.println("GET index - Siirry Main Pagelle");
		model.addAttribute("beverages", juomatrepo.findAll());
		return "index";
	}

	// SEARCH BY KEYWORD - MAIN PAGE WITH FILTERED RESULTS
	@GetMapping(value={"/search"})
	//@PreAuthorize("hasAuthority('ADMIN')")
	public String getsearch (Model model, String name, @RequestParam ("keyword")String keyword ) {
		System.out.println("GET search - Käyttäjän haku");

		if(keyword != null) { 
			model.addAttribute("beverages", juomatrepo.findByKeyword(keyword));
		} else { // jos hakusana tyhjä, palauta kaikki juomat
			model.addAttribute("beverages", juomatrepo.findAll());
		}
		return "index";
	}

	// SEARCH BY NAME [NOT USED]
	@GetMapping(value={"/searchbyname"})
	@PreAuthorize("hasAuthority('ADMIN')")
	public String getsearchbyname (@PathVariable("name") String name, Model model) {
		System.out.println("GET search - Käyttäjän haku");
		System.out.println("Hakusana: " +  name);
		List<Beverage> beverages = juomatrepo.findByNameIgnoreCase(name);
		model.addAttribute("name", name);
		model.addAttribute("beverages", beverages);
		return "index";
	}


	// EDIT BEVERAGE (Vain Admin)
	@GetMapping("/edit/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editBeverage (@PathVariable("id") Long beverageId, Model model) {			
		System.out.println("GET ID" + beverageId + " - Edit beverage ");
		model.addAttribute("beverage", juomatrepo.findById(beverageId));
		model.addAttribute("beveragetypes", tyypitrepo.findAll());
		model.addAttribute("makers", makerrepo.findAll());
		return "editbeverage";
	}

	// DELETE BEVERAGE (Vain Admin)
	@GetMapping("/delete/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteBeverage (@PathVariable("id") Long beverageId, Model model) {					
		System.out.println("Delete beverage " + beverageId);
		juomatrepo.deleteById(beverageId);
		return "redirect:/index";
	}

	// DELETE BEVERAGE ADMINPAGELLA (Vain Admin)
	@GetMapping("/delete2/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteBeverage2 (@PathVariable("id") Long beverageId, Model model) {					
		System.out.println("Delete beverage " + beverageId);
		juomatrepo.deleteById(beverageId);
		return "redirect:/addbeverage";
	}
	
	// DELETE BEVERAGETYPE (Vain Admin)
	@GetMapping("/deletetype/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteBeveragetype (@PathVariable("id") Long beveragetypeId, Model model) {					
		System.out.println("Delete beveragetype " + beveragetypeId);
		tyypitrepo.deleteById(beveragetypeId);
		return "redirect:/addbeverage";
	}

	// DELETE MAKER (Vain Admin)
	@GetMapping("/deletemaker/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteMaker (@PathVariable("id") Long makerId, Model model) {					
		System.out.println("Delete maker " + makerId);
		makerrepo.deleteById(makerId);
		return "redirect:/addbeverage";
	}
 
	//ADD BEVERAGE, MAKER, TYPE (Vain Admin)
	@GetMapping("/addbeverage")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String addBeverage (Model model, Beverage beverage) {					
		System.out.println("Add beverage ");
		model.addAttribute("beverage", new Beverage());
		model.addAttribute("beveragetype", new Beveragetype());
		model.addAttribute("maker", new Maker());
		model.addAttribute("beveragetypes", tyypitrepo.findAll());
		model.addAttribute("makers", makerrepo.findAll());
		model.addAttribute("beverages", juomatrepo.findAll());
		return "addbeverage";
	}

	//SAVE BEVERAGE (Vain Admin)
	@PostMapping("/save")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String saveBeverage (@Valid Beverage beverage, BindingResult bindingResult) {	 // validoi Beveragen arvot		
		if (bindingResult.hasErrors()) {	// jos virheitä
			System.out.println("Virheellisiä arvoja juoman tallennuksessa");
			return "errorpage";
		}
		System.out.println("Save beverage ");
		juomatrepo.save(beverage);
		return "redirect:addbeverage";
	}

	//SAVEEDIT BEVERAGE (Vain Admin)
	@PostMapping("/saveedit")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String saveEditBeverage (@Valid Beverage beverage, BindingResult bindingResult) {	 // validoi Beveragen arvot		
		if (bindingResult.hasErrors()) {	// jos virheitä
			System.out.println("Virheellisiä arvoja juoman tallennuksessa");
			return "errorpage";
		}
		System.out.println("Save beverage ");
		juomatrepo.save(beverage);
		return "redirect:index";
	}

	//SAVE BEVERAGETYPE (Vain Admin)
	@PostMapping("/savetype")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String saveBeveragetype (Beveragetype beveragetype) {					
		System.out.println("Save beverageTYPE ");
		tyypitrepo.save(beveragetype);
		return "redirect:addbeverage";
	}
	//SAVE MAKER (Vain Admin)
	@PostMapping("/savemaker")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String saveBeveragetype (Maker maker) {					
		System.out.println("Save MAKER ");
		makerrepo.save(maker);
		return "redirect:addbeverage";
	}

}