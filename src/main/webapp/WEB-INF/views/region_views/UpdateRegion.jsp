<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="spring_form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>UPDATE A REGION</title>
</head>
<body>

	<c:url value="/regions/by_id/${region.regionId}" var="region"/>
	
	<spring_form:form action="${region}" 
	modelAttribute = "newRegion">
	
	<spring_form:hidden path="regionId" value = "${newRegion.regionId}"/>
	
	<spring_form:label path="name">NAME:</spring_form:label>
    <spring_form:input type="text" path="name" placeholder = "${oldName}"/><br>
    <input type="submit" value="Submit">
    
	</spring_form:form>
	
</body>
</html>