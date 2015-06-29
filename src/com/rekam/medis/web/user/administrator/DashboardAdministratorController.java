package com.rekam.medis.web.user.administrator;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rekam.medis.component.ConfigurationHolder;

/**
 * 
 * Controller for admin home page
 *
 */

@Controller
@RequestMapping(value={"user/administrator"})
public class DashboardAdministratorController {

	private int maxPageResult = Integer.parseInt(ConfigurationHolder.get("max.number.per.page"));
	
	@RequestMapping(value={"","/"})
	public ModelAndView index(HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(required=false, defaultValue="0") Integer page) throws Exception{
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		return new ModelAndView("user/administrator/dashboardAdministrator/index",params);
	}
	
}
