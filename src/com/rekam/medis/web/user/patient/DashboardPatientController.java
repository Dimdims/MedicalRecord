package com.rekam.medis.web.user.patient;

import java.util.HashMap;
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
import com.rekam.medis.domain.user.User;
import com.rekam.medis.service.user.UserService;

/**
 * 
 * Controller for patient home page
 *
 */

@Controller
@RequestMapping(value={"user/patient"})
public class DashboardPatientController {

	@Autowired
	private UserService userService;
	
	private int maxPageResult = Integer.parseInt(ConfigurationHolder.get("max.number.per.page"));
	
	@RequestMapping(value={"","/"})
	public ModelAndView index(HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(required=false, defaultValue="0") Integer page) throws Exception{
		
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User user = userService.getByUsername(userName);
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		return new ModelAndView("user/patient/dashboardPatient/index",params);
	}
	
}
