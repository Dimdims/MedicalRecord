package com.rekam.medis.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.rekam.medis.component.ConfigurationHolder;


/**
 * loads the objects server base URL, authenticated user and tracking code of Google Analytics, <br>
 * and passes them to the view.<br>
 * It passes also binding result (e.g. error and flash messages), shopping cart object and product form. 
 */
public class DefaultFilter extends HandlerInterceptorAdapter {
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	
    	request.setAttribute("baseURL", ConfigurationHolder.get("server.baseURL"));
    	
    	request.setAttribute("imageBaseURL", ConfigurationHolder.get("image.baseURL"));
    	
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    	
    	if (request.getAttribute("BindingResult") != null) {
            
            BindingResult result = (BindingResult) request.getAttribute("BindingResult");
            modelAndView.getModelMap().putAll(result.getModel());
        }
    }
}
