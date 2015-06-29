<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    
    <title>Login</title>
    <meta name="description" content="">
    <meta name="author" content="">
    
</head>
<body>
<form class="form-signin form-horizontal themed" id="target" action="${baseURL}/j_spring_security_check" method="post">
	<c:if test="${empty error and not empty message}">
		<div class="control-group ${message.type}">
			<label>${message.text}</label>
		</div>
       </c:if>
       <c:if test="${not empty lockoutCounter && 
       				not empty maxLockoutCounter &&
       				lockoutCounter>0}">
       	<div class="control-group error">
			<label><spring:message code="login.attempts" text="Login attempts" />: ${lockoutCounter}/${maxLockoutCounter}</label>
		</div>
	</c:if>
	<div class="control-group">
		<input type="text" class="input-block-level" name="j_username" autofocus placeholder="<spring:message code="username" 
		text="username / email" />" value="">
	</div>
	<div class="control-group">
		<input type="password" class="input-block-level" name="j_password" placeholder="<spring:message code="password" 
		text="password" />" value="">
	</div>
	<div class="control-group no-border">
		<div style="text-align: right;">
			<input class="btn btn-primary" type="submit" value="<spring:message code="sign.in" 
		text="Sign in" />" id="login-btn">
		</div>
	</div>
</form>
						
</body>
</html>

