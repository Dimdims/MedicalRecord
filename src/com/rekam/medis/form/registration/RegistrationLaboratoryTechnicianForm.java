package com.rekam.medis.form.registration;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.rekam.medis.domain.registration.Registration;


/**
 * 
 * Maps Lab registration
 *
 */
public class RegistrationLaboratoryTechnicianForm {
    
	private long id;
	
	@NotEmpty
    private String laboratoriumReport;
	
	private String fileUrl;
	
	private CommonsMultipartFile data;
	
    public RegistrationLaboratoryTechnicianForm(){
    	
    }
    
    public RegistrationLaboratoryTechnicianForm(Registration registration){
    	this.id = registration.getId();
    	
    	if(registration.getLaboratoriumReport()!=null)
    		this.laboratoriumReport = registration.getLaboratoriumReport().getText();
    	if(registration.getLaboratoriumReport()!=null)
    		this.fileUrl = registration.getLaboratoriumReport().getFileUrl();
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLaboratoriumReport() {
		return laboratoriumReport;
	}

	public void setLaboratoriumReport(String laboratoriumReport) {
		this.laboratoriumReport = laboratoriumReport;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public CommonsMultipartFile getData() {
		return data;
	}

	public void setData(CommonsMultipartFile data) {
		this.data = data;
	}

}
