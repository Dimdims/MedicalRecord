package com.rekam.medis.form.registration;

import org.hibernate.validator.constraints.NotEmpty;

import com.rekam.medis.domain.registration.Registration;


/**
 * 
 * Maps Receptionist registration
 *
 */
public class RegistrationReceptionistForm {
    
	private long id;
	
	@NotEmpty
    private String symptom;
	
	private long patientId;
	
	private long receptionistId;
	
	private long doctorId;
	
    public RegistrationReceptionistForm(){
    	
    }
    
    public RegistrationReceptionistForm(Registration registration){
    	
    	this.id = registration.getId();
    	
    	if(registration.getSymptom()!=null)
    		this.symptom = registration.getSymptom().getText();
    	
    	if(registration.getPatient()!=null)
    		this.patientId = registration.getPatient().getId();
    	
    	if(registration.getReceptionist()!=null)
    		this.receptionistId = registration.getReceptionist().getId();
    	
    	if(registration.getDoctor()!=null)
    		this.doctorId = registration.getDoctor().getId();
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSymptom() {
		return symptom;
	}

	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	public long getReceptionistId() {
		return receptionistId;
	}

	public void setReceptionistId(long receptionistId) {
		this.receptionistId = receptionistId;
	}

	public long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}
    
}
