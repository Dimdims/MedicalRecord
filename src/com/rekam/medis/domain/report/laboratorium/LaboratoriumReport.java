package com.rekam.medis.domain.report.laboratorium;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="laboratorium_report")
public class LaboratoriumReport implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @Basic(optional=false)
    @Column(name="id")
    @GeneratedValue
	private long id;
	
	@Column(name="text", columnDefinition="TEXT")
	private String text;

	@Column(name="file_url")
	private String fileUrl;
	
	public LaboratoriumReport(){
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	
}
