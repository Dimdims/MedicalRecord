package com.rekam.medis.conf;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.rekam.medis.domain.user.Role;
import com.rekam.medis.domain.user.User;
import com.rekam.medis.form.user.RoleForm;
import com.rekam.medis.form.user.UserForm;
import com.rekam.medis.service.user.RoleService;
import com.rekam.medis.service.user.UserService;

/**
 * creates underlying instances every time the application starts<br>
 * and destroys some instances (if necessary) if the application is terminated.
 *
 */
@Component
public class BootStrap {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	/**
	 * executed every time the application starts
	 * @throws Exception
	 */
	@Transactional(readOnly=false)
	@PostConstruct
	public void init() throws Exception{
		
		//Setup ROLES
		setupRoles();
		
		//Setup administrator
		setupAdministrator();
		
	}
	
	/**
	 * executed every time the application is terminated
	 * @throws Exception
	 */
	@PreDestroy
	public void destroy() throws Exception{
		
	}
	
	private void setupRoles() throws Exception{
		
		String [][] rolesNames = {
				{ "ROLE_ADMINISTRATOR", "Administrator"},
				{ "ROLE_PATIENT", "Pasien"},
				{ "ROLE_RECEPTIONIST", "Receptionist"},
				{ "ROLE_DOCTOR", "Dokter"},
				{ "ROLE_SPECIALIST", "Spesialis"},
				{ "ROLE_LABORATORYTECHNICIAN", "Lab Tech"},
		} ;

		for(int i=0;i<rolesNames.length;i++){
			
			if(roleService.getByAuthority(rolesNames[i][0]) == null) {
				
				RoleForm roleForm = new RoleForm();
				
				roleForm.setAuthority(rolesNames[i][0]);
				
				roleForm.setName(rolesNames[i][1]);
				
				roleService.create(roleForm);
				
			}
		}
	}

	private void setupAdministrator() throws Exception{
		
		User administrator = userService.getByUsername("administrator");
		
		if(administrator == null){
			
			UserForm form = new UserForm();
			
			form.setUsername("administrator");
			
			form.setPassword("administrator");
			
			List<Integer> roles = new ArrayList<Integer>();
			
			Role administratorRole = roleService.getByAuthority("ROLE_ADMINISTRATOR");
			
			roles.add(administratorRole.getId());
			
			form.setRoles(roles);
			
			administrator = userService.create(form);
		}
		
	}
	
}
