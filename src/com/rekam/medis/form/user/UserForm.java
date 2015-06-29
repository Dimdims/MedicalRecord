package com.rekam.medis.form.user;

import java.util.ArrayList;
import java.util.List;

import com.rekam.medis.domain.user.Role;
import com.rekam.medis.domain.user.User;


/**
 * 
 * Maps user variables in user registration
 *
 */
public class UserForm {
    
	private long id;
	
    private String username;
    
    private String password;
    
    private List<Integer> roles = new ArrayList<Integer>();
    
    public UserForm(){
    	
    }
    
    public UserForm(User user){
    	this.id = user.getId();
    	
    	this.username = user.getUsername();
    	
    	if(user.getRoles()!=null && user.getRoles().size()>0){
        	for(Role role : user.getRoles()){
        		roles.add(role.getId());
        	}
        }
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

	public List<Integer> getRoles() {
		return roles;
	}

	public void setRoles(List<Integer> roles) {
		this.roles = roles;
	}

}
