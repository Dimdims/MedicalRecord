package com.rekam.medis.web;

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
 * Controller for the home page
 *
 */

@Controller
@RequestMapping(value={""})
public class HomeController {

	private int maxPageResult = Integer.parseInt(ConfigurationHolder.get("max.number.per.page"));
	
	@RequestMapping(value={"","/"})
	public ModelAndView home(HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(required=false, defaultValue="0") Integer page) throws Exception{
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		return new ModelAndView("home/index",params);
	}
	
}
