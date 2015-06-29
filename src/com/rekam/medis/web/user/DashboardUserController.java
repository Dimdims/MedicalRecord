package com.rekam.medis.web.user;

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
 * Controller for the home page of all actors / roles
 *
 */

@Controller
@RequestMapping(value={"user"})
public class DashboardUserController {

	private int maxPageResult = Integer.parseInt(ConfigurationHolder.get("max.number.per.page"));
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value={"","/"})
	public ModelAndView index(HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(required=false, defaultValue="0") Integer page) throws Exception{
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User user = userService.getByUsername(userName);
		
		params.put("user", user);
		
		return new ModelAndView("user/dashboardUser/index",params);
	}
	
}
