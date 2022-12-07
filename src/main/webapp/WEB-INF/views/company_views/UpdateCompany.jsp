<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="spring_form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>UPDATE A COMPANY</title>
</head>
<body>

	<c:url value="/companies/by_id/${company.companyId}" var="company"/>
	
	<spring_form:form action="${company}" 
	modelAttribute = "newCompany">
	
	<spring_form:hidden path="companyId" value = "${newCompany.companyId}"/>
	
	<spring_form:label path="name">NAME:</spring_form:label>
    <spring_form:input type="text" path="name" placeholder = "${oldName}"/><br>
    
    <spring_form:label path="registrationNumber">REGISTRATION NUMBER:</spring_form:label>
    <spring_form:input type="number" path="registrationNumber" placeholder = "${oldRegistrationNumber}"/><br>
    
    <input type="submit" value="Submit">
    
	</spring_form:form>

</body>
</html>