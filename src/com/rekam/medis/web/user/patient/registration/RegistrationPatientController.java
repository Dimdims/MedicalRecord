package com.rekam.medis.web.user.patient.registration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rekam.medis.component.ConfigurationHolder;
import com.rekam.medis.domain.registration.Registration;
import com.rekam.medis.domain.user.User;
import com.rekam.medis.service.registration.RegistrationService;
import com.rekam.medis.service.user.UserService;
import com.rekam.medis.util.PaginationUtils;

/**
 * 
 * Controller for patient registration
 *
 */

@Controller
@RequestMapping(value={"user/patient/registration"})
public class RegistrationPatientController {

	private int maxPageResult = Integer.parseInt(ConfigurationHolder.get("max.number.per.page"));
	
	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private UserService userService;
	
	@RequestMapping(value={"","/"})
	public ModelAndView list(HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(required=false, defaultValue="0") Integer page) throws Exception{
		
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User user = userService.getByUsername(userName);
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		int recordCount =  (int) registrationService.countAllByPatient(user);
		
		List<Registration> registrations = registrationService.getAllByPatient(user, page, maxPageResult);
		
		params.put("user", user);
		
		PaginationUtils.generatePage(params, page ,recordCount, maxPageResult);		
		
		params.put("recordCount", recordCount);
		
		params.put("registrations", registrations);
		
		return new ModelAndView("user/patient/registration/list",params);
	}
	
}
