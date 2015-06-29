package com.rekam.medis.validator.registration;

import java.util.UUID;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.rekam.medis.component.ConfigurationHolder;
import com.rekam.medis.form.registration.RegistrationLaboratoryTechnicianForm;
import com.rekam.medis.service.registration.RegistrationService;
import com.rekam.medis.util.FileUtils;

/**
 * 
 * Validation check for lab tech registration
 *
 */
@Component
public class RegistrationLaboratoryTechnicianFormValidator implements Validator{

	@Autowired
	private RegistrationService registrationService;
	
	@Override
	public boolean supports(Class clazz) {

		return RegistrationLaboratoryTechnicianFormValidator.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		RegistrationLaboratoryTechnicianForm form = (RegistrationLaboratoryTechnicianForm) target;
		
		if(form.getData() != null && form.getData().getSize()>0 &&
        		!StringUtils.isEmpty(form.getData().getOriginalFilename())){
			
			String[] extensions = ConfigurationHolder.get("laboratorium.file.supported.extension").split(";");
			
			String fileExtension = FileUtils.getFileNameExtension(form.getData().getOriginalFilename().toLowerCase()).toLowerCase();
			
			if(!ArrayUtils.contains(extensions, fileExtension)){
				
				errors.rejectValue("data",
						"data.file.must.be.image", null, "Data file must be an image with extension jpg, jpeg, gif, png, or bmp");
	    	}
			
		}
    	
	}
}
