package com.mike.springbootregistrationform.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mike.springbootregistrationform.dao.AppUserDAO;
import com.mike.springbootregistrationform.formbean.AppUserForm;
import com.mike.springbootregistrationform.model.AppUser;

@Component
public class AppUserValidator implements Validator {

	// common-validator library
	private EmailValidator emailValidator = EmailValidator.getInstance();
	
	@Autowired
	private AppUserDAO appUserDAO;
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz == AppUserForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		AppUserForm appUserForm = (AppUserForm) target;
		
		// kiem tra cac field cua AppUserForm
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty.appUserForm.userName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.appUserForm.firstName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.appUserForm.lastName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.appUserForm.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.appUserForm.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.appUserForm.confirmPassword");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "NotEmpty.appUserForm.gender");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "countryCode", "NotEmpty.appUserForm.countryCode");
		
		if(!this.emailValidator.isValid(appUserForm.getEmail())) {
			// email invalid 
			errors.rejectValue("email", "Pattern.appUserForm.email");
		} else if(appUserForm.getUserId() == null) {
			AppUser dbUser = appUserDAO.findAppUserByEmail(appUserForm.getEmail());
			if(dbUser != null) {
				// Email is used
				errors.rejectValue("email", "Duplicate.appUserForm.email");
			}
		}
		
		if(!errors.hasFieldErrors("userName")) {
			AppUser dbUser = appUserDAO.findAppUserByUserName(appUserForm.getUserName());
			if(dbUser != null) {
				// userName is used
				errors.rejectValue("userName", "Duplicate.appUserForm.userName");
			}
		}
		
		if(!errors.hasErrors()) {
			if(!appUserForm.getConfirmPassword().equals(appUserForm.getPassword())) {
				errors.rejectValue("confirmPassowrd", "Match.appUserForm.confirmPassowrd");
			}
		}
	}

}
