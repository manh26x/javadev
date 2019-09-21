package com.mike.springbootregistrationform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mike.springbootregistrationform.dao.AppUserDAO;
import com.mike.springbootregistrationform.dao.CountryDAO;
import com.mike.springbootregistrationform.formbean.AppUserForm;
import com.mike.springbootregistrationform.model.AppUser;
import com.mike.springbootregistrationform.model.Country;
import com.mike.springbootregistrationform.validator.AppUserValidator;

@Controller
public class MainController {

	@Autowired
	private AppUserDAO appUserDAO;
	
	@Autowired 
	private CountryDAO countryDAO;
	
	@Autowired
	private AppUserValidator appUserValidator;
	
	// Set a form  validator
	@InitBinder
	protected void initBinder(WebDataBinder dataBinder) {
		// form target
		Object target = dataBinder.getTarget();
		
		if(target == null) {
			return;
		}
		
		System.out.println("Target = " + target);
		
		if(target.getClass() == AppUserForm.class) {
			dataBinder.setValidator(appUserValidator);
		}
		//...
	}
	
	@RequestMapping("/")
	public String viewHome(Model model) {
		return "welcomePage";
	}
	
	@RequestMapping("/members")
	public String viewMembers(Model model) {
		List<AppUser> list = appUserDAO.getAppUsers();
		model.addAttribute("members", list);
		return "membersPage";
	}
	
	@RequestMapping("/registerSuccessfull")
	public String viewRegisterSuccessfull(Model model) {
		return "registerSuccessfullPage";
	}
	
	// show register page
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String viewRegister(Model model) {
		
		AppUserForm form = new AppUserForm();
		List<Country> countries = countryDAO.getCountries();		
		
		model.addAttribute("appUserForm", form);
		model.addAttribute("countries", countries);
		return "registerPage";
	}
	
	// this method is called to  save register information
	// @Validated: To guarantee  this form 
	// was validate befor this method is called
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String saveRegister(Model model, //
			@ModelAttribute("appUserForm") @Validated AppUserForm appUserForm, // 
			BindingResult result, //
			final RedirectAttributes redirectAttributes) {
		
		// Validate result
		if(result.hasErrors()) {
			List<Country> countries = countryDAO.getCountries();
			model.addAttribute("countries", countries);
			return "registerPage";
		}
		
		AppUser newUser = null;
		try {
			newUser = appUserDAO.createAppUser(appUserForm);
		} catch(Exception e) {
			List<Country> countries = countryDAO.getCountries();
			model.addAttribute("countries", countries);
			model.addAttribute("errorMessage", "Error: " + e.getMessage());
			return "registerPage";
		}
		redirectAttributes.addFlashAttribute("flashUser", newUser);
		return "redirect:/registerSuccessfull";
	}
	
}
