package com.rekam.medis.service.user;


import java.util.List;

import com.rekam.medis.domain.user.Role;
import com.rekam.medis.form.user.RoleForm;

public interface RoleService {

	Role get(int id) throws Exception;

	List<Role> getAll(int page, int maxResult) throws Exception;

	long countAll() throws Exception;

	Role getByAuthority(String authority) throws Exception;

	Role create(RoleForm form) throws Exception;

	Role edit(RoleForm form) throws Exception;

}
