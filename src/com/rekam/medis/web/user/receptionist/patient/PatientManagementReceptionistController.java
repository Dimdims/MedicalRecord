package com.rekam.medis.web.user.receptionist.patient;

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
import com.rekam.medis.domain.user.Role;
import com.rekam.medis.domain.user.User;
import com.rekam.medis.form.user.PatientForm;
import com.rekam.medis.form.user.UserForm;
import com.rekam.medis.service.user.RoleService;
import com.rekam.medis.service.user.UserService;
import com.rekam.medis.util.PaginationUtils;
import com.rekam.medis.validator.user.PatientFormValidator;

/**
 * 
 * Controller for receptionist to manage patients
 *
 */

@Controller
@RequestMapping(value={"user/receptionist/patientManagement"})
public class PatientManagementReceptionistController {

	private int maxPageResult = Integer.parseInt(ConfigurationHolder.get("max.number.per.page"));
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PatientFormValidator patientFormValidator;
	
	@RequestMapping(value={"","/"})
	public ModelAndView list(HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(required=false, defaultValue="0") Integer page) throws Exception{
		
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User user = userService.getByUsername(userName);
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		List<Role> rolePatient = new ArrayList<Role>();
		rolePatient.add(roleService.getByAuthority("ROLE_PATIENT"));
		
		List<User> patients = userService.getAllByRoleList(rolePatient, 0, 10000);
		
		int recordCount =  (int) userService.countAllByRoleList(rolePatient);
		
		PaginationUtils.generatePage(params, page ,recordCount, maxPageResult);		
		
		params.put("recordCount", recordCount);
		
		params.put("patients", patients);
		
		return new ModelAndView("user/receptionist/patientManagement/list",params);
	}
	
	@RequestMapping(value = {"/create"}, method = {RequestMethod.GET})
    public ModelAndView create(HttpServletRequest request) throws Exception {
        
    	Map<String, Object> params = new HashMap<String, Object>();
    	
    	params.put("form", new PatientForm());
    	
        return new ModelAndView("user/receptionist/patientManagement/form", params);
    }
	
	@RequestMapping(value = {"/edit/{id}"}, method = {RequestMethod.GET})
    public ModelAndView edit(HttpServletRequest request, @PathVariable("id") long id) throws Exception {
        
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("form", new PatientForm());
		
		User user = userService.get(id);
		
		if(user!=null){
			params.put("form", new PatientForm(user));
		}
    	
        return new ModelAndView("user/receptionist/patientManagement/form", params);
    }
	
    @RequestMapping(value = {"/save"}, method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView save(@ModelAttribute("form") @Valid PatientForm form, BindingResult result, HttpServletRequest request) throws Exception {
        
    	User user = new User();
    	
    	String message = "";
    	
    	patientFormValidator.validate(form, result);
    	
        if (result.hasErrors()) {
        	
            FlashMap.put("BindingResult", result);
            
            return new ModelAndView("redirect:/user/receptionist/patientManagement/create");
        }
        
        if (form.getId()<=0) {
            
        	user = userService.create(form);
            
        	message = "Patient {0} successfully created";
        	
        } else {
        	user = userService.edit(form);
        	
        	message = "Patient {0} successfully edited";
        }
        
        List<String> listInString = new ArrayList<String>();
		
		listInString.add(user.getUsername());
		
        FlashMap.setSuccessMessage(MessageHolder.get("patient.created.edited", listInString.toArray(), message, LocaleContextHolder.getLocale()));
        
        return new ModelAndView("redirect:/user/receptionist/patientManagement/");
    }
}
