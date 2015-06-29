package com.rekam.medis.repository.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.rekam.medis.domain.user.Role;
import com.rekam.medis.domain.user.User;
import com.rekam.medis.repository.AbstractRepository;

/**
 * 
 * connect User with DB using JPA Hibernate
 *
 */

@Repository("userRepository")
public class UserRepository extends AbstractRepository<User> {
    
	public long countAll(){
		
		return (Long) entityManager.createQuery("SELECT COUNT(u) FROM User u ORDER BY u.username")
        		.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
    public List<User> getAll(int firstResult, int maxResult) {
        
        return entityManager.createQuery("SELECT u FROM User u ORDER BY u.username")
        		.setFirstResult(firstResult)
        		.setMaxResults(maxResult)
        		.getResultList();
    }
	
	@SuppressWarnings("unchecked")
    public User get(long id) {
        
        List<User> users = entityManager.createQuery("SELECT u FROM User u WHERE u.id = :id ORDER BY u.username")
        		.setParameter("id", id)
        		.setMaxResults(1)
        		.getResultList();
        
        if (!users.isEmpty() && users.size()>0) {
            
            return users.get(0);
        }
        
        return null;
    }
	
	@SuppressWarnings("unchecked")
    public User getByUsername(String username) {
        
        List<User> users = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username ORDER BY u.username")
        		.setParameter("username", username)
        		.setMaxResults(1)
        		.getResultList();
        
        if (!users.isEmpty() && users.size()>0) {
            
            return users.get(0);
        }
        
        return null;
    }
	
	@SuppressWarnings("unchecked")
    public List<User> getAllByRoleList(List<Role> roles, int firstResult, int maxResult) {
        
		if(roles!=null){
	        return entityManager.createQuery("SELECT u FROM User u JOIN u.roles r WHERE r IN :roles ORDER BY u.username")
	        		.setParameter("roles", roles)
	        		.setFirstResult(firstResult)
	        		.setMaxResults(maxResult)
	        		.getResultList();
		}
		
		return new ArrayList<User>();
    }
	
    public long countAllByRoleList(List<Role> roles) {
        
		if(roles!=null){
	        return (long) entityManager.createQuery("SELECT COUNT(u) FROM User u JOIN u.roles r WHERE r IN :roles ORDER BY u.username")
	        		.setParameter("roles", roles)
	        		.getSingleResult();
		}
		
		return 0;
    }
}
