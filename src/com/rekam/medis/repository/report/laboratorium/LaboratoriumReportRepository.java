package com.rekam.medis.repository.report.laboratorium;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rekam.medis.domain.report.laboratorium.LaboratoriumReport;
import com.rekam.medis.repository.AbstractRepository;

@Repository("laboratoriumReportRepository")
public class LaboratoriumReportRepository extends AbstractRepository<LaboratoriumReport>{
	
	public long countAll(){
		
		return (Long) entityManager.createQuery("SELECT COUNT(l) FROM LaboratoriumReport l ORDER BY l.id DESC")
        		.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
    public List<LaboratoriumReport> getAll(int firstResult, int maxResult) {
        
        return entityManager.createQuery("SELECT l FROM LaboratoriumReport l ORDER BY l.id DESC")
        		.setFirstResult(firstResult)
        		.setMaxResults(maxResult)
        		.getResultList();
    }
	
	@SuppressWarnings("unchecked")
    public LaboratoriumReport get(long id) {
        
        List<LaboratoriumReport> saboratoriumReports = entityManager.createQuery("SELECT l "
        		+ "FROM LaboratoriumReport l WHERE l.id = :id ORDER BY l.id DESC")
        		.setParameter("id", id)
        		.setMaxResults(1)
        		.getResultList();
        
        if (!saboratoriumReports.isEmpty() && saboratoriumReports.size()>0) {
            
            return saboratoriumReports.get(0);
        }
        
        return null;
    }
	
}
