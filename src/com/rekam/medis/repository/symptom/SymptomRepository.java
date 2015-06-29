package com.rekam.medis.repository.symptom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rekam.medis.domain.symptom.Symptom;
import com.rekam.medis.repository.AbstractRepository;

@Repository("symptomRepository")
public class SymptomRepository extends AbstractRepository<Symptom>{
	
	public long countAll(){
		
		return (Long) entityManager.createQuery("SELECT COUNT(s) FROM Symptom s ORDER BY s.id DESC")
        		.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
    public List<Symptom> getAll(int firstResult, int maxResult) {
        
        return entityManager.createQuery("SELECT s FROM Symptom s ORDER BY s.id DESC")
        		.setFirstResult(firstResult)
        		.setMaxResults(maxResult)
        		.getResultList();
    }
	
	@SuppressWarnings("unchecked")
    public Symptom get(long id) {
        
        List<Symptom> symptoms = entityManager.createQuery("SELECT s FROM Symptom s WHERE s.id = :id ORDER BY s.id DESC")
        		.setParameter("id", id)
        		.setMaxResults(1)
        		.getResultList();
        
        if (!symptoms.isEmpty() && symptoms.size()>0) {
            
            return symptoms.get(0);
        }
        
        return null;
    }
	
}
