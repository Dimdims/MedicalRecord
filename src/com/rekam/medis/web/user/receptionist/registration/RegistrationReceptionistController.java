package com.rekam.medis.web.user.receptionist.registration;

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
import com.rekam.medis.domain.user.Role;
import com.rekam.medis.domain.user.User;
import com.rekam.medis.form.registration.RegistrationReceptionistForm;
import com.rekam.medis.form.user.UserForm;
import com.rekam.medis.service.registration.RegistrationService;
import com.rekam.medis.service.user.RoleService;
import com.rekam.medis.service.user.UserService;
import com.rekam.medis.util.PaginationUtils;
import com.rekam.medis.validator.registration.RegistrationReceptionistFormValidator;

/**
 * 
 * Controller for receptionist registration
 *
 */

@Controller
@RequestMapping(value={"user/receptionist/registration"})
public class RegistrationReceptionistController {

	private int maxPageResult = Integer.parseInt(ConfigurationHolder.get("max.number.per.page"));
	
	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private RegistrationReceptionistFormValidator registrationReceptionistFormValidator;
	
	@RequestMapping(value={"","/"})
	public ModelAndView list(HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(required=false, defaultValue="0") Integer page) throws Exception{
		
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User user = userService.getByUsername(userName);
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		int recordCount =  (int) registrationService.countAll();
		
		List<Registration> registrations = registrationService.getAll(page, maxPageResult);
		
		params.put("user", user);
		
		PaginationUtils.generatePage(params, page ,recordCount, maxPageResult);		
		
		params.put("recordCount", recordCount);
		
		params.put("registrations", registrations);
		
		return new ModelAndView("user/receptionist/registration/list",params);
	}
	
	@RequestMapping(value = {"/create"}, method = {RequestMethod.GET})
    public ModelAndView create(HttpServletRequest request) throws Exception {
        
    	Map<String, Object> params = new HashMap<String, Object>();
    	
    	List<Role> roleDoctor = new ArrayList<Role>();
		roleDoctor.add(roleService.getByAuthority("ROLE_DOCTOR"));
    	
		List<Role> rolePatient = new ArrayList<Role>();
		rolePatient.add(roleService.getByAuthority("ROLE_PATIENT"));
		
		List<User> doctors = userService.getAllByRoleList(roleDoctor, 0, 10000);
		
		List<User> patients = userService.getAllByRoleList(rolePatient, 0, 10000);
		
		params.put("doctors", doctors);
		
		params.put("patients", patients);
		
    	params.put("form", new RegistrationReceptionistForm());
    	
        return new ModelAndView("user/receptionist/registration/form", params);
    }
	
	@RequestMapping(value = {"/edit/{id}"}, method = {RequestMethod.GET})
    public ModelAndView edit(HttpServletRequest request, @PathVariable("id") long id) throws Exception {
        
		Map<String, Object> params = new HashMap<String, Object>();
		
		List<Role> roleDoctor = new ArrayList<Role>();
		roleDoctor.add(roleService.getByAuthority("ROLE_DOCTOR"));
    	
		List<Role> rolePatient = new ArrayList<Role>();
		rolePatient.add(roleService.getByAuthority("ROLE_PATIENT"));
		
		List<User> doctors = userService.getAllByRoleList(roleDoctor, 0, 10000);
		
		List<User> patients = userService.getAllByRoleList(rolePatient, 0, 10000);
		
		params.put("doctors", doctors);
		
		params.put("patients", patients);
		
		Registration registration = registrationService.get(id);
		
		if(registration!=null){
			params.put("form", new RegistrationReceptionistForm(registration));
		}
    	
        return new ModelAndView("user/receptionist/registration/form", params);
    }
	
    @RequestMapping(value = {"/save"}, method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView save(@ModelAttribute("form") @Valid RegistrationReceptionistForm form, BindingResult result, HttpServletRequest request) throws Exception {
        
    	String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User user = userService.getByUsername(userName);
		
    	Registration registration = new Registration();
    	
    	String message = "";
    	
    	registrationReceptionistFormValidator.validate(form, result);
    	
        if (result.hasErrors()) {
        	
            FlashMap.put("BindingResult", result);
            
            return new ModelAndView("redirect:/user/receptionist/registration/create");
        }
        
        form.setReceptionistId(user.getId());
        
        if (form.getId()<=0) {
            
        	registration = registrationService.create(form);
            
        	message = "Registration for {0} successfully created";
        	
        } else {
        	registration = registrationService.edit(form);
        	
        	message = "Registration for {0} successfully edited";
        }
        
        List<String> listInString = new ArrayList<String>();
		
		listInString.add(registration.getPatient().getUsername());
		
        FlashMap.setSuccessMessage(MessageHolder.get("registration.created.edited", listInString.toArray(), message, LocaleContextHolder.getLocale()));
        
        return new ModelAndView("redirect:/user/receptionist/registration/");
    }
}
