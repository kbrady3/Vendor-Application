package dmacc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dmacc.beans.Vendor;
import dmacc.repository.VendorRepository;

@Controller
public class WebController {
	@Autowired
	VendorRepository repo;
	
	@GetMapping({ "/", "viewAllVendors" })
	public String viewAllVendors(Model model) {
		if(repo.findAll().isEmpty()) {
			return addNewVendor(model);
		}
		model.addAttribute("vendors", repo.findAll());
		return "results";
	}
	
	@GetMapping("/addVendor")
	public String addNewVendor(Model model) {
		Vendor v = new Vendor();
	 	model.addAttribute("newVendor", v);
	 	return "input";
	}
	
	@PostMapping("/addVendor")
	public String addNewVendor(@ModelAttribute Vendor v, Model model) {
		repo.save(v);
		return viewAllVendors(model);
	}
	
	@GetMapping("/edit/{id}")
	public String showUpdateVendor(@PathVariable("id") int id, Model model) {
		Vendor v = repo.findById((long) id).orElse(null);
		model.addAttribute("newVendor", v);
		return "input";
	}
	
	@PostMapping("/update/{id}")
	public String reviseVendor(Vendor v, Model model) {
		repo.save(v);
		return viewAllVendors(model);
	}
	
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") int id, Model model) {
		Vendor v = repo.findById((long) id).orElse(null);
		repo.delete(v);
		return viewAllVendors(model);
	}
}
