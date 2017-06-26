package enhanced.potluch;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import enhanced.potluch.Chefs;
import enhanced.potluch.UserRepository;

@Controller // This class is a controller.
public class MainPotluckController {
	@Autowired // Calls the bean UserRepository.
private UserRepository repository;
	
	@GetMapping("/signup")
	public String addChef(Model chefModel) {
		chefModel.addAttribute("chefObject", new Chefs());
		return "signupForm";
	}
	
	@PostMapping("/signup")
	public String chefsAdded(@Valid Chefs chefs, BindingResult resultThatBinds, Model dubiousModel){
		
		if (resultThatBinds.hasErrors()){
			return "signupForm";
		}
		
		repository.save(chefs);
		dubiousModel.addAttribute("chefObject", chefs);
		return "chefslist";
	}

	@GetMapping("/search")
	public String chefSearch(Model searchModel){
		searchModel.addAttribute("searchObject", new Chefs());
		return "search";
	}
	/*
	@PostMapping("/search")
	public String SearchedFor(@ModelAttribute ("chefs")Chefs){
		// should return search results
		repository.findAll();
		return "result";
	}*/
	@GetMapping("/all")
	public @ResponseBody Iterable<Chefs> fetchAllChefs(){
		// should return JSON or XML with chefs
		return repository.findAll();
	}
	
	@GetMapping("/delete")
	public String deleteChef(Model deleteModel){
		deleteModel.addAttribute("deleteObject", new Chefs());
		return "delete";
	}
	
	@PostMapping("/delete")
	public String chefDeleted(Chefs chefs, Model lostModel){
		//should delete selected record
		repository.delete(chefs);
		lostModel.addAttribute("deleteObject", chefs);
		return "delete";
	}
}
