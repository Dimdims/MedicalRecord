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
     <h1>Add / Edit User</h1>
     <a href="${baseURL}/user/administrator/userManagement/create">Create</a>
  </div>
  
	<div class="shortable-content">
	      <form:form action="${baseURL}/user/administrator/userManagement/save" modelAttribute="form" method="post">
			    <div class="box-content">
			      	
	      			<form:hidden path="id"/>
			      		
               		  <div class="form-row">
				       <label for="normal-field"><strong>Username</strong></label>
				       <c:set var="hasError"><form:errors path="username"/></c:set>
		              	<c:if test="${not empty hasError}">
		              		<c:set var="errorMessage"><form:errors path="username"/></c:set>
		              		<c:set var="errorMessageType" value="error" />
		              	</c:if>
		              	<div class="form-right-col">
		              		<form:input path="username" class="_100F" placeholder="Username" title="Please enter Username"/>
		              	</div>
		              	<span class="help-inline">${errorMessage}</span>
				      </div>
				      <c:set var="errorMessage" value=""/>
               		  <c:set var="errorMessageType" value="" />
               		  
               		  
               		  <div class="form-row">
				       <label for="normal-field"><strong>Password</strong></label>
				       <c:set var="hasError"><form:errors path="password"/></c:set>
		              	<c:if test="${not empty hasError}">
		              		<c:set var="errorMessage"><form:errors path="password"/></c:set>
		              		<c:set var="errorMessageType" value="error" />
		              	</c:if>
		              	<div class="form-right-col">
		              		<form:password path="password" class="_100F" placeholder="Password" title="Please enter Password"/>
		              	</div>
		              	<span class="help-inline">${errorMessage}</span>
				      </div>
				      <c:set var="errorMessage" value=""/>
               		  <c:set var="errorMessageType" value="" />
               		  
               		  
               		  <div class="form-row">
				       <label for="normal-field"><strong>Roles</strong></label>
				       <c:set var="hasError"><form:errors path="roles"/></c:set>
		              	<c:if test="${not empty hasError}">
		              		<c:set var="errorMessage"><form:errors path="roles"/></c:set>
		              		<c:set var="errorMessageType" value="error" />
		              	</c:if>
		              	
		              	<div class="form-right-col">
		              		<div class="form-row">
				              	<c:forEach items="${roles}" var="role" varStatus="status">
				                 	<form:checkbox class="styled" path="roles" value="${roles[status.index].id}" />
				                 	${roles[status.index].authority}
								</c:forEach>
							</div>
							<form:errors path="roles" cssClass="error"/>
		              	</div>
		              	<span class="help-inline">${errorMessage}</span>
				      </div>
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
