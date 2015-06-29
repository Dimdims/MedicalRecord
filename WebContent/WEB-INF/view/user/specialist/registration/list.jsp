<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <title>Registration Management</title>
</head>
<body>

     <h1>Registration Management</h1>
  	<c:if test="${not empty message}">
		<c:if test="${fn:toUpperCase(message.type) == 'ERROR'}"><c:set var="messageClass" value="error"/></c:if>
		<c:if test="${fn:toUpperCase(message.type) == 'SUCCESS'}"><c:set var="messageClass" value="success"/></c:if>
		<c:if test="${fn:toUpperCase(message.type) == 'INFO'}"><c:set var="messageClass" value="information"/></c:if>
		<c:if test="${fn:toUpperCase(message.type) == 'WARNING'}"><c:set var="messageClass" value="attention"/></c:if>
		
		<div class="${messageClass} png_bg">
			<div>
	            ${message.text}
	    	</div>
     	</div> <!--Attention Message ENDS -->
	</c:if>
	<table  border="1" bordercolor="#dfdfdf" class="static_table resizable">
	 <thead>
		 <tr>
		 <th>No.</th>
		 <th>Patient (Medical History)</th>
		 <th>Symptom</th>
		 <th>Medical Report Doctor</th>
		 <th>Specialist</th>
		 <th>Medical Report Specialist</th>
		 <th>Prescription Doctor</th>
		 <th>Prescription Specialist</th>
		 <th>Laboratory Tech</th>
		 <th>Date</th>
		 <th>Action</th>
		 </tr>
	 </thead>
	 <tbody>
	 <c:forEach items="${registrations}" var="registration" varStatus="registrationsStatus">
	<tr>
	<td>${registrationsStatus.count}</td>
	<td>
		<c:if test="${not empty registration.patient}">
			${registration.patient.username } (click <a href="${baseURL}/user/specialist/registration/medicalHistory/${registration.patient.id}">here</a> for his medical histories)
		</c:if>
	
	</td>
	<td>
		<c:if test="${not empty registration.symptom}">
			${registration.symptom.text}
		</c:if>
	</td>
	<td>
		<c:if test="${not empty registration.doctorMedicalReport}">
			${registration.doctorMedicalReport.text}
		</c:if>
	</td>
	<td>
		<c:if test="${not empty registration.specialist}">
			${registration.specialist.username}
		</c:if>
	</td>
	<td>
		<c:if test="${not empty registration.specialistMedicalReport}">
			${registration.specialistMedicalReport.text}
		</c:if>
	</td>
		<td>
		<c:if test="${not empty registration.doctorPrescription}">
			${registration.doctorPrescription.text}
		</c:if>
	</td>
	<td>
		<c:if test="${not empty registration.specialistPrescription}">
			${registration.specialistPrescription.text}
		</c:if>
	</td>
	<td>
		<c:if test="${not empty registration.laboratoryTechnician}">
			${registration.laboratoryTechnician.username}
		</c:if>
	</td>
	<td><fmt:formatDate pattern="dd MMMM yyyy" type="date" value="${registration.createdDate}" /></td>
	<td><a href="${baseURL}/user/specialist/registration/edit/${registration.id}">edit</a></td>
	</tr>
	</c:forEach>
	 </tbody>
	</table>
	<c:import url="_pagination.jsp"></c:import>

<script type="text/javascript">

$(document).on("click", "#create_item", function(event) {
	event.preventDefault();
	var ref = $(this).data("href");
	window.location.replace(ref);
});
		
		
</script>	

</body>
</html>
