package com.rekam.medis.form.registration;

import org.hibernate.validator.constraints.NotEmpty;

import com.rekam.medis.domain.registration.Registration;


/**
 * 
 * Maps Specialist in registration
 *
 */
public class RegistrationSpecialistForm {
    
	private long id;
	
	@NotEmpty
    private String medicalReport;
	
	private long laboratoryTechnicianId;
	
	private String prescription;
	
    public RegistrationSpecialistForm(){
    	
    }
    
    public RegistrationSpecialistForm(Registration registration){
    	this.id = registration.getId();
    	
    	if(registration.getSpecialistMedicalReport()!=null)
    		this.medicalReport = registration.getSpecialistMedicalReport().getText();
    	
    	if(registration.getLaboratoryTechnician()!=null)
    		this.laboratoryTechnicianId = registration.getLaboratoryTechnician().getId();
    	
    	if(registration.getSpecialistPrescription()!=null)
    		this.prescription = registration.getSpecialistPrescription().getText();
    	
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMedicalReport() {
		return medicalReport;
	}

	public void setMedicalReport(String medicalReport) {
		this.medicalReport = medicalReport;
	}

	public long getLaboratoryTechnicianId() {
		return laboratoryTechnicianId;
	}

	public void setLaboratoryTechnicianId(long laboratoryTechnicianId) {
		this.laboratoryTechnicianId = laboratoryTechnicianId;
	}

	public String getPrescription() {
		return prescription;
	}

	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}
	
}
