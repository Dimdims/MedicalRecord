package com.rekam.medis.form.user;

import org.hibernate.validator.constraints.NotEmpty;

import com.rekam.medis.domain.user.Role;

public class RoleForm {

	private int id;
	
	@NotEmpty
    private String authority;

	@NotEmpty
	private String name;
	
    public RoleForm(){
    	
    }
    
    public RoleForm(Role role){
    	
    	this.id = role.getId();
    	
    	this.authority = role.getAuthority();
    	
    	this.name = role.getName();
    }
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
}