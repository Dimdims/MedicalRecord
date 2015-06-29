package com.rekam.medis.domain.symptom;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="symptom")
public class Symptom implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @Basic(optional=false)
    @Column(name="id")
    @GeneratedValue
	private long id;
	
	@Column(name="text", columnDefinition="TEXT")
	private String text;

	public Symptom(){
		
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
	
}
