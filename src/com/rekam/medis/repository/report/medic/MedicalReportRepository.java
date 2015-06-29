package com.rekam.medis.repository.report.medic;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rekam.medis.domain.report.medic.MedicalReport;
import com.rekam.medis.repository.AbstractRepository;

@Repository("medicalReportRepository")
public class MedicalReportRepository extends AbstractRepository<MedicalReport>{
	
	public long countAll(){
		
		return (Long) entityManager.createQuery("SELECT COUNT(m) FROM MedicalReport m ORDER BY m.id DESC")
        		.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
    public List<MedicalReport> getAll(int firstResult, int maxResult) {
        
        return entityManager.createQuery("SELECT m FROM MedicalReport m ORDER BY m.id DESC")
        		.setFirstResult(firstResult)
        		.setMaxResults(maxResult)
        		.getResultList();
    }
	
	@SuppressWarnings("unchecked")
    public MedicalReport get(long id) {
        
        List<MedicalReport> medicalReports = entityManager.createQuery("SELECT m "
        		+ "FROM MedicalReport m WHERE m.id = :id ORDER BY m.id DESC")
        		.setParameter("id", id)
        		.setMaxResults(1)
        		.getResultList();
        
        if (!medicalReports.isEmpty() && medicalReports.size()>0) {
            
            return medicalReports.get(0);
        }
        
        return null;
    }
	
}
