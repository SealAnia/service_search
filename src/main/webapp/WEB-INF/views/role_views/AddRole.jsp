<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="spring_form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ADD A NEW ROLE</title>
</head>
<body>
	
	<c:url value="/roles" var="role"/>
	
	<spring_form:form action="${role}" method="post" modelAttribute="role">
	
	<spring_form:label path="name">NAME:</spring_form:label>
    <spring_form:input type="text" path="name"/><br>
    
    <input type="submit" value="Submit">
    
	</spring_form:form>

</body>
</html>