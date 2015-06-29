package com.rekam.medis.service.implementation.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rekam.medis.domain.user.Role;
import com.rekam.medis.form.user.RoleForm;
import com.rekam.medis.repository.user.RoleRepository;
import com.rekam.medis.service.user.RoleService;

@Service("roleService")
@Transactional(readOnly=true)
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role get(int id) throws Exception {
		
		return roleRepository.get(id);
	}
	
	@Override
	public List<Role> getAll(int page, int maxResult) throws Exception {
		
		return roleRepository.getAll(page*maxResult, maxResult);
	}
	
	@Override
	public long countAll() throws Exception {
		
		return roleRepository.countAll();
	}

	@Override
	public Role getByAuthority(String authority) throws Exception {
		
		return roleRepository.getByAuthority(authority);
	}

	@Transactional(readOnly=false)
	@Override
	public Role create(RoleForm form) throws Exception {
		
		Role role = new Role();
		
		role.setAuthority(form.getAuthority());
		
		role.setName(form.getName());
		
		return roleRepository.persist(role);
	}
	
	@Transactional(readOnly=false)
	@Override
	public Role edit(RoleForm form) throws Exception {
		
		Role role = roleRepository.get(form.getId());
		
		if(role != null){
			
			role.setAuthority(role.getAuthority());
			
			role.setName(form.getName());
		}
		
		return roleRepository.merge(role);
	}

}
