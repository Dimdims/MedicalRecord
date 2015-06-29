package com.rekam.medis.service.implementation.registration;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rekam.medis.component.ConfigurationHolder;
import com.rekam.medis.domain.registration.Registration;
import com.rekam.medis.domain.report.laboratorium.LaboratoriumReport;
import com.rekam.medis.domain.report.medic.MedicalReport;
import com.rekam.medis.domain.symptom.Symptom;
import com.rekam.medis.domain.treatment.prescription.Prescription;
import com.rekam.medis.domain.user.User;
import com.rekam.medis.form.registration.RegistrationDoctorForm;
import com.rekam.medis.form.registration.RegistrationLaboratoryTechnicianForm;
import com.rekam.medis.form.registration.RegistrationReceptionistForm;
import com.rekam.medis.form.registration.RegistrationSpecialistForm;
import com.rekam.medis.repository.registration.RegistrationRepository;
import com.rekam.medis.repository.report.laboratorium.LaboratoriumReportRepository;
import com.rekam.medis.repository.report.medic.MedicalReportRepository;
import com.rekam.medis.repository.symptom.SymptomRepository;
import com.rekam.medis.repository.treatment.prescription.PrescriptionRepository;
import com.rekam.medis.repository.user.UserRepository;
import com.rekam.medis.service.registration.RegistrationService;
import com.rekam.medis.util.FileUtils;

/**
 * provides services for registration.
 *
 */
@Service("registrationService")
@Transactional(readOnly = true)
public class RegistrationServiceImpl implements RegistrationService {
	
    @Autowired
    private RegistrationRepository registrationRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private SymptomRepository symptomRepository;
    
    @Autowired
    private MedicalReportRepository medicalReportRepository;
    
    @Autowired
    private LaboratoriumReportRepository laboratoriumReportRepository;
    
    @Autowired
    private PrescriptionRepository prescriptionRepository;
    
	@Override
	public Registration get(long id) throws Exception {
		
		return registrationRepository.get(id);
	}

	@Override
	public long countAll() throws Exception {
		
		return registrationRepository.countAll();
	}

	@Override
	public List<Registration> getAll(int page, int maxResult) throws Exception {
		
		return registrationRepository.getAll(page*maxResult, maxResult);
	}

	@Override
	@Transactional(readOnly = false)
	public Registration create(RegistrationReceptionistForm form) throws Exception {
		
		Registration registration = new Registration();
		
		registration.setCreatedDate(new Date());
		
		Symptom symptom = new Symptom();
		
		symptom.setText(form.getSymptom());
		
		symptom = symptomRepository.persist(symptom);
		
		registration.setSymptom(symptom);
		
		User patient = userRepository.get(form.getPatientId());
		
		registration.setPatient(patient);
		
		User receptionist = userRepository.get(form.getReceptionistId());
		
		registration.setReceptionist(receptionist);
		
		User doctor = userRepository.get(form.getDoctorId());
		
		registration.setDoctor(doctor);
		
		return registrationRepository.persist(registration);
	}

	@Override
	@Transactional(readOnly = false)
	public Registration edit(RegistrationReceptionistForm form) throws Exception {
		
		Registration registration = registrationRepository.get(form.getId());
		
		Symptom symptom = null;
		
		if(registration.getSymptom()!=null){
			
			symptom = registration.getSymptom();
			
			symptom.setText(form.getSymptom());
			
			symptom = symptomRepository.merge(symptom);
			
		}else{
			
			symptom = new Symptom();
			
			symptom.setText(form.getSymptom());
			
			symptom = symptomRepository.persist(symptom);
			
		}
		
		registration.setSymptom(symptom);
		
		User patient = userRepository.get(form.getPatientId());
		
		registration.setPatient(patient);
		
		User receptionist = userRepository.get(form.getReceptionistId());
		
		registration.setReceptionist(receptionist);
		
		User doctor = userRepository.get(form.getDoctorId());
		
		registration.setDoctor(doctor);
		
		return registrationRepository.merge(registration);
	}

	@Override
	public List<Registration> getAllByDoctor(User doctor, int page,
			int maxResult) throws Exception {
		
		return registrationRepository.getAllByDoctor(doctor, page*maxResult, maxResult);
	}

	@Override
	public long countAllByDoctor(User doctor) throws Exception {
		
		return registrationRepository.countAllByDoctor(doctor);
	}

	@Override
	public List<Registration> getAllByLaboratoryTechnician(
			User laboratoryTechnician, int page, int maxResult)
			throws Exception {
		
		return registrationRepository.getAllByLaboratoryTechnician(
				laboratoryTechnician, page*maxResult, maxResult);
	}

	@Override
	public long countAllByLaboratoryTechnician(User laboratoryTechnician)
			throws Exception {
		
		return registrationRepository.countAllByLaboratoryTechnician(laboratoryTechnician);
	}

	@Override
	public List<Registration> getAllByPatient(User patient, int page, int maxResult)
			throws Exception {
		
		return registrationRepository.getAllByPatient(patient, page*maxResult, maxResult);
	}

	@Override
	public long countAllByPatient(User patient) throws Exception {
		
		return registrationRepository.countAllByPatient(patient);
	}

	@Override
	public List<Registration> getAllBySpecialist(User specialist, int page,
			int maxResult) throws Exception {
		
		return registrationRepository.getAllBySpecialist(specialist, page*maxResult, maxResult);
	}

	@Override
	public long countAllBySpecialist(User specialist) throws Exception {
		
		return registrationRepository.countAllBySpecialist(specialist);
	}

	@Override
	@Transactional(readOnly = false)
	public Registration edit(RegistrationDoctorForm form) throws Exception {
		
		Registration registration = registrationRepository.get(form.getId());
		
		MedicalReport medicalReport = null;
		
		if(registration.getDoctorMedicalReport()!=null){
			
			medicalReport = registration.getDoctorMedicalReport();
			
			medicalReport.setText(form.getMedicalReport());
			
			medicalReport = medicalReportRepository.merge(medicalReport);
			
		}else{
			
			medicalReport = new MedicalReport();
			
			medicalReport.setText(form.getMedicalReport());
			
			medicalReport = medicalReportRepository.persist(medicalReport);
			
		}
		
		registration.setDoctorMedicalReport(medicalReport);
		
		Prescription prescription = null;
		
		if(registration.getDoctorPrescription()!=null){
			
			prescription = registration.getDoctorPrescription();
			
			prescription.setText(form.getPrescription());
			
			prescription = prescriptionRepository.merge(prescription);
			
		}else{
			
			prescription = new Prescription();
			
			prescription.setText(form.getPrescription());
			
			prescription = prescriptionRepository.persist(prescription);
			
		}
		
		registration.setDoctorPrescription(prescription);
		
		User specialist = userRepository.get(form.getSpecialistId());
		
		registration.setSpecialist(specialist);
		
		return registrationRepository.merge(registration);
	}
	
	@Override
	@Transactional(readOnly = false)
	public Registration edit(RegistrationSpecialistForm form) throws Exception {
		
		Registration registration = registrationRepository.get(form.getId());
		
		MedicalReport medicalReport = null;
		
		if(registration.getSpecialistMedicalReport()!=null){
			
			medicalReport = registration.getSpecialistMedicalReport();
			
			medicalReport.setText(form.getMedicalReport());
			
			medicalReport = medicalReportRepository.merge(medicalReport);
			
		}else{
			
			medicalReport = new MedicalReport();
			
			medicalReport.setText(form.getMedicalReport());
			
			medicalReport = medicalReportRepository.persist(medicalReport);
			
		}
		
		registration.setSpecialistMedicalReport(medicalReport);
		
		Prescription prescription = null;
		
		if(registration.getSpecialistPrescription()!=null){
			
			prescription = registration.getSpecialistPrescription();
			
			prescription.setText(form.getPrescription());
			
			prescription = prescriptionRepository.merge(prescription);
			
		}else{
			
			prescription = new Prescription();
			
			prescription.setText(form.getPrescription());
			
			prescription = prescriptionRepository.persist(prescription);
			
		}
		
		registration.setSpecialistPrescription(prescription);
		
		User laboratoryTechnician = userRepository.get(form.getLaboratoryTechnicianId());
		
		registration.setLaboratoryTechnician(laboratoryTechnician);
		
		return registrationRepository.merge(registration);
	}
	
	@Override
	@Transactional(readOnly = false)
	public Registration edit(RegistrationLaboratoryTechnicianForm form) throws Exception {
		
		Registration registration = registrationRepository.get(form.getId());
		
		LaboratoriumReport laboratoriumReport = null;
		
		if(registration.getLaboratoriumReport()!=null){
			
			laboratoriumReport = registration.getLaboratoriumReport();
			
			laboratoriumReport.setText(form.getLaboratoriumReport());
			
			if(form.getData() != null && form.getData().getSize()>0 &&
	        		!StringUtils.isEmpty(form.getData().getOriginalFilename())) {
	        	
				String[] extensions = ConfigurationHolder.get("laboratorium.file.supported.extension").split(";");
				
	        	String fileExtension = FileUtils.getFileNameExtension(form.getData().getOriginalFilename().toLowerCase()).toLowerCase();
	        	
	        	String filename = UUID.randomUUID()+"."+fileExtension;
	        	
	        	if(ArrayUtils.contains(extensions, fileExtension)){
	        		laboratoriumReport.setFileUrl(filename);
		        	
		    		FileUtils.commonsMultiPartFileTransfer(form.getData(), ConfigurationHolder.get("laboratorium.file.path"),filename);
	        	}
	    	}
			
			laboratoriumReport = laboratoriumReportRepository.merge(laboratoriumReport);
			
		}else{
			
			laboratoriumReport = new LaboratoriumReport();
			
			laboratoriumReport.setText(form.getLaboratoriumReport());
			
			if(form.getData() != null && form.getData().getSize()>0 &&
	        		!StringUtils.isEmpty(form.getData().getOriginalFilename())) {
	        	
				String[] extensions = ConfigurationHolder.get("laboratorium.file.supported.extension").split(";");
				
	        	String fileExtension = FileUtils.getFileNameExtension(form.getData().getOriginalFilename().toLowerCase()).toLowerCase();
	        	
	        	String filename = UUID.randomUUID()+"."+fileExtension;
	        	
	        	if(ArrayUtils.contains(extensions, fileExtension)){
	        		laboratoriumReport.setFileUrl(filename);
		        	
		    		FileUtils.commonsMultiPartFileTransfer(form.getData(), ConfigurationHolder.get("laboratorium.file.path"),filename);
	        	}
	    		
	    	}
			
			laboratoriumReport = laboratoriumReportRepository.persist(laboratoriumReport);
			
		}
		
		registration.setLaboratoriumReport(laboratoriumReport);
		
		return registrationRepository.merge(registration);
	}

}
