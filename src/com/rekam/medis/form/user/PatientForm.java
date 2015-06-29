package com.rekam.medis.form.user;

import java.util.ArrayList;
import java.util.List;

import com.rekam.medis.domain.user.Role;
import com.rekam.medis.domain.user.User;


/**
 * 
 * Maps Patient registration
 *
 */
public class PatientForm {
    
	private long id;
	
    private String username;
    
    private String password;
    
    public PatientForm(){
    	
    }
    
    public PatientForm(User user){
    	this.id = user.getId();
    	
    	this.username = user.getUsername();
    	
    }
    
    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
