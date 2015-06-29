package com.rekam.medis.web.authentication;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value={"/authentication"})
public class AuthenticationController {

	@RequestMapping(value={"/login","/login/"})
	public ModelAndView login(
			@RequestParam(value="lockoutCounter", defaultValue="0", required=false) int lockoutCounter,
			@RequestParam(value="lockout", defaultValue="false", required=false) boolean lockout,
			@RequestParam(value="blocked", defaultValue="false", required=false) boolean isBlocked,
			@RequestParam(value="maintenance", defaultValue="false", required=false) boolean maintenance,
			HttpServletRequest request) throws Exception{
		
		Map<String,Object> params = new HashMap<String, Object>();
		
		return new ModelAndView("authentication/login",params);
	}
	
	@RequestMapping(value={"/denied","/denied/"})
	public ModelAndView denied(HttpServletRequest request) throws Exception{
		
		Map<String,Object> params = new HashMap<String, Object>();
		
		return new ModelAndView("authentication/denied",params);
	}
	
}
