package com.rekam.medis.service.registration;

import java.util.List;

import com.rekam.medis.domain.registration.Registration;
import com.rekam.medis.domain.user.User;
import com.rekam.medis.form.registration.RegistrationDoctorForm;
import com.rekam.medis.form.registration.RegistrationLaboratoryTechnicianForm;
import com.rekam.medis.form.registration.RegistrationReceptionistForm;
import com.rekam.medis.form.registration.RegistrationSpecialistForm;

/**
 * 
 * Inteface for registration service
 *
 */
public interface RegistrationService {
    
	Registration get(long id) throws Exception;
	
	long countAll() throws Exception;
	
	List<Registration> getAll(int page, int maxResult) throws Exception;
   
	Registration create(RegistrationReceptionistForm form) throws Exception;
	   
	Registration edit(RegistrationReceptionistForm form) throws Exception;
	
	List<Registration> getAllByDoctor(User doctor, int page, int maxResult) throws Exception;
	
	public long countAllByDoctor(User doctor) throws Exception;

	List<Registration> getAllBySpecialist(User specialist, int page, int maxResult) throws Exception;
	
	public long countAllBySpecialist(User specialist) throws Exception;
	
    public List<Registration> getAllByLaboratoryTechnician(User laboratoryTechnician,
    		int page, int maxResult) throws Exception;
	
	public long countAllByLaboratoryTechnician(User laboratoryTechnician) throws Exception;

    public List<Registration> getAllByPatient(User patient, 
    		int page, int maxResult) throws Exception;
	
	public long countAllByPatient(User patient) throws Exception;
	
	Registration edit(RegistrationDoctorForm form) throws Exception;
   
	Registration edit(RegistrationSpecialistForm form) throws Exception;
	
	Registration edit(RegistrationLaboratoryTechnicianForm form) throws Exception;
}
