package com.rekam.medis.domain.registration;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.rekam.medis.domain.report.laboratorium.LaboratoriumReport;
import com.rekam.medis.domain.report.medic.MedicalReport;
import com.rekam.medis.domain.symptom.Symptom;
import com.rekam.medis.domain.treatment.prescription.Prescription;
import com.rekam.medis.domain.user.User;

@Entity
@Table(name="registration")
public class Registration implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @Basic(optional=false)
    @Column(name="id")
    @GeneratedValue
	private long id;
	
	@Basic(optional=false)
    @Column(name="created_date")
    @Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@OneToOne(optional=true)
    @JoinColumn(name="laboratorium_report")
    private LaboratoriumReport laboratoriumReport;
	
	@OneToOne(optional=true)
    @JoinColumn(name="doctor_medical_report")
    private MedicalReport doctorMedicalReport;
	
	@OneToOne(optional=true)
    @JoinColumn(name="specialist_medical_report")
    private MedicalReport specialistMedicalReport;
	
	@OneToOne(optional=true)
    @JoinColumn(name="doctor_prescription")
    private Prescription doctorPrescription;
	
	@OneToOne(optional=true)
    @JoinColumn(name="specialist_prescription")
    private Prescription specialistPrescription;
	
	@OneToOne(optional=true)
    @JoinColumn(name="symptom")
    private Symptom symptom;
	
	@ManyToOne
    @JoinColumn(name="patient_id")
	private User patient;
	
	@ManyToOne
    @JoinColumn(name="receptionist_id")
	private User receptionist;
	
	@ManyToOne
    @JoinColumn(name="doctor_id")
	private User doctor;
	
	@ManyToOne
    @JoinColumn(name="specialist_id")
	private User specialist;
	
	@ManyToOne
    @JoinColumn(name="laboratory_technician_id")
	private User laboratoryTechnician;
	
	public Registration(){
		
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getPatient() {
		return patient;
	}

	public void setPatient(User patient) {
		this.patient = patient;
	}

	public LaboratoriumReport getLaboratoriumReport() {
		return laboratoriumReport;
	}

	public void setLaboratoriumReport(LaboratoriumReport laboratoriumReport) {
		this.laboratoriumReport = laboratoriumReport;
	}

	public MedicalReport getDoctorMedicalReport() {
		return doctorMedicalReport;
	}

	public void setDoctorMedicalReport(MedicalReport doctorMedicalReport) {
		this.doctorMedicalReport = doctorMedicalReport;
	}

	public MedicalReport getSpecialistMedicalReport() {
		return specialistMedicalReport;
	}

	public void setSpecialistMedicalReport(MedicalReport specialistMedicalReport) {
		this.specialistMedicalReport = specialistMedicalReport;
	}

	public Prescription getDoctorPrescription() {
		return doctorPrescription;
	}

	public void setDoctorPrescription(Prescription doctorPrescription) {
		this.doctorPrescription = doctorPrescription;
	}

	public Prescription getSpecialistPrescription() {
		return specialistPrescription;
	}

	public void setSpecialistPrescription(Prescription specialistPrescription) {
		this.specialistPrescription = specialistPrescription;
	}

	public Symptom getSymptom() {
		return symptom;
	}

	public void setSymptom(Symptom symptom) {
		this.symptom = symptom;
	}

	public User getReceptionist() {
		return receptionist;
	}

	public void setReceptionist(User receptionist) {
		this.receptionist = receptionist;
	}

	public User getDoctor() {
		return doctor;
	}

	public void setDoctor(User doctor) {
		this.doctor = doctor;
	}

	public User getLaboratoryTechnician() {
		return laboratoryTechnician;
	}

	public void setLaboratoryTechnician(User laboratoryTechnician) {
		this.laboratoryTechnician = laboratoryTechnician;
	}

	public User getSpecialist() {
		return specialist;
	}

	public void setSpecialist(User specialist) {
		this.specialist = specialist;
	}
	
}
