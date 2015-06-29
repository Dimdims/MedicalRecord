package com.rekam.medis.service.implementation.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rekam.medis.domain.user.Role;
import com.rekam.medis.domain.user.User;
import com.rekam.medis.form.user.PatientForm;
import com.rekam.medis.form.user.UserForm;
import com.rekam.medis.repository.user.RoleRepository;
import com.rekam.medis.repository.user.UserRepository;
import com.rekam.medis.service.user.UserService;
import com.rekam.medis.util.HashUtils;

/**
 * provides services for user.
 *
 */
@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
	
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
	@Override
	public User get(long id) throws Exception {
		
		return userRepository.get(id);
	}

	@Override
	public long countAll() throws Exception {
		
		return userRepository.countAll();
	}

	@Override
	public List<User> getAll(int page, int maxResult) throws Exception {
		
		return userRepository.getAll(page*maxResult, maxResult);
	}

	@Override
	@Transactional(readOnly = false)
	public User create(UserForm form) throws Exception {
		
		//stores the user.
        User user = new User();
        
        user.setUsername(form.getUsername());
        
        user.setPassword(HashUtils.SHA256(form.getPassword()).toLowerCase());
        
        //Set Roles
        List<Role> roles = new ArrayList<Role>();
        
        if(form.getRoles() != null && form.getRoles().size()>0){
	        
        	for(int roleString : form.getRoles()){
	        	
        		roles.add(roleRepository.get(roleString));
        		
	        }
        	
        }else{
        	
        	Role userRole = roleRepository.getByAuthority("ROLE_USER");
        	
        	roles.add(userRole);
        	
        }
        
        user.setRoles(roles);
        
        return userRepository.persist(user);
        
	}

	@Override
	@Transactional(readOnly = false)
	public User edit(UserForm form) throws Exception {
		
		User user = get(form.getId());
        
        user.setUsername(form.getUsername());
        
        if(StringUtils.isNotEmpty(form.getPassword()))
        	user.setPassword(HashUtils.SHA256(form.getPassword()).toLowerCase());
        
      //Set Roles
        List<Role> roles = new ArrayList<Role>();
        
        if(form.getRoles() != null && form.getRoles().size()>0){
	        
        	for(int roleString : form.getRoles()){
	        	
        		roles.add(roleRepository.get(roleString));
        		
	        }
        	
        }else{
        	
        	Role userRole = roleRepository.getByAuthority("ROLE_PATIENT");
        	
        	roles.add(userRole);
        	
        }
        
        user.setRoles(roles);
        
        return userRepository.merge(user);
        
	}

	@Override
	public User getByUsername(String username) throws Exception {
		System.out.println("getByUsername: "+username);
		return userRepository.getByUsername(username);
	}

	@Override
	public List<User> getAllByRoleList(List<Role> roles, int page, int maxResult)
			throws Exception {
		
		return userRepository.getAllByRoleList(roles, page*maxResult, maxResult);
	}

	@Override
	public long countAllByRoleList(List<Role> roles) throws Exception {
		
		return userRepository.countAllByRoleList(roles);
	}

	@Override
	@Transactional(readOnly = false)
	public User create(PatientForm form) throws Exception {
		
		//stores the user.
        User user = new User();
        
        user.setUsername(form.getUsername());
        
        user.setPassword(HashUtils.SHA256(form.getPassword()).toLowerCase());
        
        //Set Roles
        List<Role> roles = new ArrayList<Role>();
        
    	Role userRole = roleRepository.getByAuthority("ROLE_PATIENT");
        	
    	roles.add(userRole);
        	
        user.setRoles(roles);
        
        return userRepository.persist(user);
	}

	@Override
	@Transactional(readOnly = false)
	public User edit(PatientForm form) throws Exception {
		
		User user = get(form.getId());
        
        user.setUsername(form.getUsername());
        
        if(StringUtils.isNotEmpty(form.getPassword()))
        	user.setPassword(HashUtils.SHA256(form.getPassword()).toLowerCase());
        
        return userRepository.merge(user);
	}
    
}
