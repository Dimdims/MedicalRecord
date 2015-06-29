package com.rekam.medis.component;

import java.util.Locale;

import org.springframework.context.ApplicationContext;

/**
 * Reads the java property file config.properties <br>
 * and retrieves its property values
 *
 */
public final class MessageHolder {

	private static ApplicationContext context = ApplicationContextProvider.getApplicationContext();
	
    public static String get(String key, Object[] args, String defaultValue, Locale locale){
    	
    	return context.getMessage(key,args, defaultValue, locale);
    }
    
}
