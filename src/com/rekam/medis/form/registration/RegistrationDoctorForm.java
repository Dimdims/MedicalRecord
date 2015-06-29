package com.rekam.medis.form.registration;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotEmpty;

import com.rekam.medis.domain.registration.Registration;


/**
 * 
 * Maps Doctor registration
 *
 */
public class RegistrationDoctorForm {
    
	private long id;
	
	@NotEmpty
    private String medicalReport;
	
	private String prescription;
	
	private long specialistId;
	
    public RegistrationDoctorForm(){
    	
    }
    
    public RegistrationDoctorForm(Registration registration){
    	this.id = registration.getId();
    	
    	if(registration.getDoctorMedicalReport()!=null)
    		this.medicalReport = registration.getDoctorMedicalReport().getText();
    	
    	if(registration.getSpecialist()!=null)
    		this.specialistId = registration.getSpecialist().getId();
    	
    	if(registration.getDoctorPrescription()!=null)
    		this.prescription = registration.getDoctorPrescription().getText();
    	
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

	public long getSpecialistId() {
		return specialistId;
	}

	public void setSpecialistId(long specialistId) {
		this.specialistId = specialistId;
	}

	public String getPrescription() {
		return prescription;
	}

	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}
	
}
