<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="spring_form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ADD A NEW COMPANY</title>
</head>
<body>

	${message}

	<c:url value = "/companies" var = "company"/>
	
	<spring_form:form action = "${company}" method="post" modelAttribute="company">
	
	<spring_form:label path="name">NAME:</spring_form:label>
    <spring_form:input type="text" path="name"/><br>
    
    <spring_form:label path="registrationNumber">REGISTRATION NUMBER:</spring_form:label>
    <spring_form:input type="number" path="registrationNumber"/><br>
    
    <input type="submit" value="Submit">
    
	</spring_form:form>

</body>
</html>