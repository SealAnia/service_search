<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="spring_form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ADD A NEW CATEGORY</title>
</head>
<body>

	<c:url value="/categories" var="category"/>
	
	<spring_form:form action="${category}" method="post" modelAttribute="category">
	
	<spring_form:label path="name">NAME:</spring_form:label>
    <spring_form:input type="text" path="name"/><br>
    
    <spring_form:label path="description">DESCRIPTION:</spring_form:label>
    <spring_form:input type="text" path="description"/><br>
    
    <label for = "isAvailableOnline">IS AVAILABLE ONLINE</label>
    <input list="options" id="isAvailableOnline" name="isAvailableOnline">
    <datalist id = "options">
    	<option value = "YES"/>
    	<option value = "NO"/>
    </datalist>
    
    <input type="submit" value="Submit">
    
	</spring_form:form>

</body>
</html>