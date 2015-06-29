package com.rekam.medis.validator.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.rekam.medis.service.registration.RegistrationService;

/**
 * 
 * Validation check for receptionist registration
 *
 */
@Component
public class RegistrationReceptionistFormValidator implements Validator{

	@Autowired
	private RegistrationService registrationService;
	
	@Override
	public boolean supports(Class clazz) {

		return RegistrationReceptionistFormValidator.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		
	}
}
