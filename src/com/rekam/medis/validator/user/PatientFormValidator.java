package com.rekam.medis.validator.user;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.rekam.medis.domain.user.User;
import com.rekam.medis.form.user.PatientForm;
import com.rekam.medis.service.user.UserService;

/**
 * 
 * Validation check for patient registration
 *
 */
@Component
public class PatientFormValidator implements Validator{

	@Autowired
	private UserService userService;
	
	@Override
	public boolean supports(Class clazz) {

		return PatientFormValidator.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		PatientForm form = (PatientForm) target;
		
		User userById = new User();
		
		if(form.getId()>0){
			try {
				userById = userService.get(form.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(StringUtils.isEmpty(form.getUsername())){
			
			errors.rejectValue("username",
					"input.required", null, "Input diperlukan.");
			
		}
		
		if(StringUtils.isEmpty(form.getPassword()) && form.getId()<=0){
			
			errors.rejectValue("password",
					"input.required", null, "Input diperlukan.");
			
		}
		
		if(!StringUtils.isEmpty(form.getUsername())){
			
			User userByUsername = new User();
			
			try {
				userByUsername = userService.getByUsername(form.getUsername());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(userByUsername!= null && userByUsername.getId()>0){
				if(userById.getId()<=0 || (userById.getId()>0 && 
						userById.getId()!=userByUsername.getId())
						){
					errors.rejectValue("username",
							"duplicate.entry", new Object[]{userByUsername.getUsername()}, "Input {0} telah ada.");
				}
				
			}
			
		}
		
	}
}
