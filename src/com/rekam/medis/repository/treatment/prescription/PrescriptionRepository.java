package com.rekam.medis.repository.treatment.prescription;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rekam.medis.domain.treatment.prescription.Prescription;
import com.rekam.medis.repository.AbstractRepository;

@Repository("prescriptionRepository")
public class PrescriptionRepository extends AbstractRepository<Prescription>{
	
	public long countAll(){
		
		return (Long) entityManager.createQuery("SELECT COUNT(p) FROM Prescription p ORDER BY p.id DESC")
        		.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
    public List<Prescription> getAll(int firstResult, int maxResult) {
        
        return entityManager.createQuery("SELECT p FROM Prescription p ORDER BY p.id DESC")
        		.setFirstResult(firstResult)
        		.setMaxResults(maxResult)
        		.getResultList();
    }
	
	@SuppressWarnings("unchecked")
    public Prescription get(long id) {
        
        List<Prescription> prescriptions = entityManager.createQuery("SELECT p FROM Prescription p WHERE p.id = :id ORDER BY p.id DESC")
        		.setParameter("id", id)
        		.setMaxResults(1)
        		.getResultList();
        
        if (!prescriptions.isEmpty() && prescriptions.size()>0) {
            
            return prescriptions.get(0);
        }
        
        return null;
    }
	
}
