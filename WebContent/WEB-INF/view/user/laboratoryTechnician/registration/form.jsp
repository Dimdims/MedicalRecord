<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <title>Add / Edit User</title>
</head>
<body>

  <div class="non-shortable-content">
     <h1>Edit Registration</h1>
  </div>
  
	<div class="shortable-content">
	      <form:form action="${baseURL}/user/laboratoryTechnician/registration/save" modelAttribute="form" method="post" enctype="multipart/form-data">
			    <div class="box-content">
			      	
	      			<form:hidden path="id"/>
	
               		  <div class="form-row">
				       <label for="normal-field"><strong>Patient</strong></label>
		              	<div class="form-right-col">
		              		<div class="form-row">
		              			${patient.username}
							</div>
		              	</div>
		              	<span class="help-inline">${errorMessage}</span>
				      </div>
				      
				      <div class="form-row">
				       <label for="normal-field"><strong>Symptom</strong></label>
		              	<div class="form-right-col">
		              		${symptom}
		              	</div>
		              	<span class="help-inline">${errorMessage}</span>
				      </div>
				      
				      
				      <div class="form-row">
				       <label for="normal-field"><strong>Laboratorium Report</strong></label>
				       <c:set var="hasError"><form:errors path="laboratoriumReport"/></c:set>
		              	<c:if test="${not empty hasError}">
		              		<c:set var="errorMessage"><form:errors path="laboratoriumReport"/></c:set>
		              		<c:set var="errorMessageType" value="error" />
		              	</c:if>
		              	<div class="form-right-col">
		              		<form:textarea path="laboratoriumReport" class="_100F" placeholder="Laboratorium Report" title="Please enter Laboratorium Report"/>
		              	</div>
		              	<span class="help-inline">${errorMessage}</span>
				      </div>
				      <c:set var="errorMessage" value=""/>
               		  <c:set var="errorMessageType" value="" />
               		  
               		  <div class="form-row">
				       <label for="normal-field"><strong>Laboratorium File</strong></label>
				       <c:set var="hasError"><form:errors path="data"/></c:set>
		              	<c:if test="${not empty hasError}">
		              		<c:set var="errorMessage"><form:errors path="data"/></c:set>
		              		<c:set var="errorMessageType" value="error" />
		              	</c:if>
		              	<div class="form-right-col">
		              		<form:input path="data" type="file" /><br>
		              		<c:if test="${not empty form.fileUrl}">
								<img src="${baseURL}/laboratorium-folder/${form.fileUrl}"/>
							</c:if>
		              	</div>
		              	<span class="help-inline">${errorMessage}</span>
				      </div>
				      <c:set var="errorMessage" value=""/>
               		  <c:set var="errorMessageType" value="" />
		      	</div>
			      <div class="form-row">
<!-- 			       <input type="submit" class="grey" value="Cancel" /> -->
			       <input type="submit" value="Submit" class="form_button float_r" />
			      </div>
		      
	    </form:form>  
	    </div>
	  </div>  <!--GENRAL FORMS ENDS HERE-->
  
  
  
  </div><!--SHORTABLECONTENT-ENDS-->
  
<script type="text/javascript">

$(document).on("click", "#create_item", function(event) {
	event.preventDefault();
	var ref = $(this).data("href");
	window.location.replace(ref);
});
		
		
</script>	

</body>
</html>
