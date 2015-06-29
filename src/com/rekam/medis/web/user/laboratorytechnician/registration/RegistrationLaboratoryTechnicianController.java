package com.rekam.medis.web.user.laboratorytechnician.registration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.flash.FlashMap;
import org.springframework.web.servlet.ModelAndView;

import com.rekam.medis.component.ConfigurationHolder;
import com.rekam.medis.component.MessageHolder;
import com.rekam.medis.domain.registration.Registration;
import com.rekam.medis.domain.user.User;
import com.rekam.medis.form.registration.RegistrationLaboratoryTechnicianForm;
import com.rekam.medis.service.registration.RegistrationService;
import com.rekam.medis.service.user.RoleService;
import com.rekam.medis.service.user.UserService;
import com.rekam.medis.util.PaginationUtils;
import com.rekam.medis.validator.registration.RegistrationLaboratoryTechnicianFormValidator;

/**
 * 
 * Controller for lab tech registration
 *
 */

@Controller
@RequestMapping(value={"user/laboratoryTechnician/registration"})
public class RegistrationLaboratoryTechnicianController {

	private int maxPageResult = Integer.parseInt(ConfigurationHolder.get("max.number.per.page"));
	
	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private RegistrationLaboratoryTechnicianFormValidator registrationLaboratoryTechnicianFormValidator;
	
	@RequestMapping(value={"","/"})
	public ModelAndView list(HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(required=false, defaultValue="0") Integer page) throws Exception{
		
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User user = userService.getByUsername(userName);
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		int recordCount =  (int) registrationService.countAllByLaboratoryTechnician(user);
		
		List<Registration> registrations = registrationService.getAllByLaboratoryTechnician(user, page, maxPageResult);
		
		params.put("user", user);
		
		PaginationUtils.generatePage(params, page ,recordCount, maxPageResult);		
		
		params.put("recordCount", recordCount);
		
		params.put("registrations", registrations);
		
		return new ModelAndView("user/laboratoryTechnician/registration/list",params);
	}
	
	@RequestMapping(value = {"/edit/{id}"}, method = {RequestMethod.GET})
    public ModelAndView edit(HttpServletRequest request, @PathVariable("id") long id) throws Exception {
        
		Map<String, Object> params = new HashMap<String, Object>();
		
		Registration registration = registrationService.get(id);
		
		if(registration!=null){
			params.put("form", new RegistrationLaboratoryTechnicianForm(registration));
		}
    	
        return new ModelAndView("user/laboratoryTechnician/registration/form", params);
    }
	
    @RequestMapping(value = {"/save"}, method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView save(@ModelAttribute("form") @Valid RegistrationLaboratoryTechnicianForm form, BindingResult result, HttpServletRequest request) throws Exception {
        
    	String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User user = userService.getByUsername(userName);
		
    	Registration registration = new Registration();
    	
    	String message = "";
    	
    	registrationLaboratoryTechnicianFormValidator.validate(form, result);
    	
        if (result.hasErrors()) {
        	
            FlashMap.put("BindingResult", result);
            
            return new ModelAndView("redirect:/user/laboratoryTechnician/registration/edit/"+form.getId());
        }
        
    	registration = registrationService.edit(form);
        	
    	message = "Registration for {0} successfully edited";
        
        List<String> listInString = new ArrayList<String>();
		
		listInString.add(registration.getPatient().getUsername());
		
        FlashMap.setSuccessMessage(MessageHolder.get("registration.created.edited", listInString.toArray(), message, LocaleContextHolder.getLocale()));
        
        return new ModelAndView("redirect:/user/laboratoryTechnician/registration/");
    }
    
    @RequestMapping(value = {"/medicalHistory/{id}"}, method = {RequestMethod.GET})
	public ModelAndView history(HttpServletResponse response,
			HttpServletRequest request,
			@PathVariable("id") long id,
			@RequestParam(required=false, defaultValue="0") Integer page) throws Exception{
		
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User user = userService.getByUsername(userName);
		
		User patient = userService.get(id);
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		if(patient!=null){
			
			int recordCount =  (int) registrationService.countAllByPatient(patient);
			
			List<Registration> registrations = registrationService.getAllByPatient(patient, page, maxPageResult);
			
			params.put("user", user);
			
			params.put("patient", patient);
			
			PaginationUtils.generatePage(params, page ,recordCount, maxPageResult);		
			
			params.put("recordCount", recordCount);
			
			params.put("registrations", registrations);
			
			return new ModelAndView("user/laboratoryTechnician/registration/medicalHistory",params);
		}
		
		return null;
		
	}
}
