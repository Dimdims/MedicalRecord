package com.rekam.medis.repository.registration;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rekam.medis.domain.registration.Registration;
import com.rekam.medis.domain.user.User;
import com.rekam.medis.repository.AbstractRepository;

/**
 * 
 * connect Registration with DB using JPA Hibernate
 *
 */

@Repository("registrationRepository")
public class RegistrationRepository extends AbstractRepository<Registration> {
    
	public long countAll(){
		
		return (Long) entityManager.createQuery("SELECT COUNT(r) FROM Registration r ORDER BY r.id DESC")
        		.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
    public List<Registration> getAll(int firstResult, int maxResult) {
        
        return entityManager.createQuery("SELECT r FROM Registration r ORDER BY r.id DESC")
        		.setFirstResult(firstResult)
        		.setMaxResults(maxResult)
        		.getResultList();
    }
	
	@SuppressWarnings("unchecked")
    public Registration get(long id) {
        
        List<Registration> registrations = entityManager.createQuery("SELECT r FROM Registration r WHERE r.id = :id ORDER BY r.id DESC")
        		.setParameter("id", id)
        		.setMaxResults(1)
        		.getResultList();
        
        if (!registrations.isEmpty() && registrations.size()>0) {
            
            return registrations.get(0);
        }
        
        return null;
    }
	
	@SuppressWarnings("unchecked")
    public List<Registration> getAllByDoctor(User doctor, int firstResult, int maxResult) {
        
		return entityManager.createQuery("SELECT r FROM Registration r WHERE "
        		+ "r.doctor = :doctor ORDER BY r.id DESC")
        		.setParameter("doctor", doctor)
        		.setFirstResult(firstResult)
        		.setMaxResults(maxResult)
        		.getResultList();
        
    }
	
	public long countAllByDoctor(User doctor){
		
		return (Long) entityManager.createQuery("SELECT COUNT(r) FROM Registration r WHERE "
        		+ "r.doctor = :doctor ORDER BY r.id DESC")
        		.setParameter("doctor", doctor)
        		.getSingleResult();
	}

	@SuppressWarnings("unchecked")
    public List<Registration> getAllBySpecialist(User specialist, int firstResult, int maxResult) {
        
		return entityManager.createQuery("SELECT r FROM Registration r WHERE "
        		+ "r.specialist = :specialist ORDER BY r.id DESC")
        		.setParameter("specialist", specialist)
        		.setFirstResult(firstResult)
        		.setMaxResults(maxResult)
        		.getResultList();
        
    }
	
	public long countAllBySpecialist(User specialist){
		
		return (Long) entityManager.createQuery("SELECT COUNT(r) FROM Registration r WHERE "
        		+ "r.specialist = :specialist ORDER BY r.id DESC")
        		.setParameter("specialist", specialist)
        		.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
    public List<Registration> getAllByLaboratoryTechnician(User laboratoryTechnician, int firstResult, int maxResult) {
        
		return entityManager.createQuery("SELECT r FROM Registration r WHERE "
        		+ "r.laboratoryTechnician = :laboratoryTechnician ORDER BY r.id DESC")
        		.setParameter("laboratoryTechnician", laboratoryTechnician)
        		.setFirstResult(firstResult)
        		.setMaxResults(maxResult)
        		.getResultList();
        
    }
	
	public long countAllByLaboratoryTechnician(User laboratoryTechnician){
		
		return (Long) entityManager.createQuery("SELECT COUNT(r) FROM Registration r WHERE "
        		+ "r.laboratoryTechnician = :laboratoryTechnician ORDER BY r.id DESC")
        		.setParameter("laboratoryTechnician", laboratoryTechnician)
        		.getSingleResult();
	}

	
	@SuppressWarnings("unchecked")
    public List<Registration> getAllByPatient(User patient, int firstResult, int maxResult) {
        
		return entityManager.createQuery("SELECT r FROM Registration r WHERE "
        		+ "r.patient = :patient ORDER BY r.id DESC")
        		.setParameter("patient", patient)
        		.setFirstResult(firstResult)
        		.setMaxResults(maxResult)
        		.getResultList();
        
    }
	
	public long countAllByPatient(User patient){
		
		return (Long) entityManager.createQuery("SELECT COUNT(r) FROM Registration r WHERE "
        		+ "r.patient = :patient ORDER BY r.id DESC")
        		.setParameter("patient", patient)
        		.getSingleResult();
	}
}
