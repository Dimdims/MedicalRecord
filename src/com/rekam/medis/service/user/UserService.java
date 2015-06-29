package com.rekam.medis.service.user;

import java.util.List;

import com.rekam.medis.domain.user.Role;
import com.rekam.medis.domain.user.User;
import com.rekam.medis.form.user.PatientForm;
import com.rekam.medis.form.user.UserForm;

/**
 * 
 * Inteface for user service
 *
 */
public interface UserService {
    
	User get(long id) throws Exception;
	
	long countAll() throws Exception;
	
	List<User> getAll(int page, int maxResult) throws Exception;
   
	User create(UserForm form) throws Exception;
	   
	User edit(UserForm form) throws Exception;
	
	User getByUsername(String username) throws Exception;
   
	List<User> getAllByRoleList(List<Role> roles, int page, int maxResult) throws Exception;
	
	long countAllByRoleList(List<Role> roles) throws Exception;
	
	User create(PatientForm form) throws Exception;
	   
	User edit(PatientForm form) throws Exception;
	
}
