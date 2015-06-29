package com.rekam.medis.repository.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rekam.medis.domain.user.Role;
import com.rekam.medis.repository.AbstractRepository;

@Repository("roleRepository")
public class RoleRepository extends AbstractRepository<Role>{
	
	@SuppressWarnings("unchecked")
    public List<Role> getAll(int firstResult, int maxResult) {
        
        return entityManager.createNamedQuery("Role.findAll").
        		setFirstResult(firstResult).setMaxResults(maxResult).getResultList();
    }
	
	public long countAll() {
        
        return (Long) entityManager.createNamedQuery("Role.countAll").getSingleResult();
    }

    public Role get(int id) {
        
        return entityManager.find(Role.class, id);
    }
    
    @SuppressWarnings("unchecked")
    public Role getByAuthority(String authority){
    	
        List<Role> roles = entityManager.createNamedQuery("Role.findByAuthority").setParameter("authority", authority).getResultList();
        
        if (!roles.isEmpty()) {
            
            return roles.get(0);
        }
        
        return null;
    }

}
