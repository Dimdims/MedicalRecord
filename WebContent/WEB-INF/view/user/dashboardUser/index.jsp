<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dashboard</title>
</head>

<body>
	<h1>Dashboard for Authenticated user - Your username: ${user.username }</h1>
	
	<sec:authorize access="hasAnyRole('ROLE_ADMINISTRATOR')">
     	<h2>For Administrator, click <a href="${baseURL}/user/administrator/">here.</a></h2>
     </sec:authorize>
	<sec:authorize access="hasAnyRole('ROLE_PATIENT')">
     	<h2>For Patient, click <a href="${baseURL}/user/patient/">here.</a></h2>
     </sec:authorize>
     <sec:authorize access="hasAnyRole('ROLE_RECEPTIONIST')">
     	<h2>For Receptionist, click <a href="${baseURL}/user/receptionist/">here.</a></h2>
     </sec:authorize>
     <sec:authorize access="hasAnyRole('ROLE_DOCTOR')">
     	<h2>For Doctor, click <a href="${baseURL}/user/doctor/">here.</a></h2>
     </sec:authorize>
     <sec:authorize access="hasAnyRole('ROLE_SPECIALIST')">
     	<h2>For Specialist, click <a href="${baseURL}/user/specialist/">here.</a></h2>
     </sec:authorize>
     <sec:authorize access="hasAnyRole('ROLE_LABORATORYTECHNICIAN')">
     	<h2>For Laboratory Technician, click <a href="${baseURL}/user/laboratoryTechnician/">here.</a></h2>
     </sec:authorize>
     
     <h2><a href="${baseURL}/j_spring_security_logout">Logout</a></h2>
     
</body>
</html>