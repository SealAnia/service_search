<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="spring_form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>UPDATE CATEGORY INFO</title>
</head>
<body>

	<div>
		<c:url value="/categories/by_id/${category.categoryId}" var="category"/>
	
		<spring_form:form action="${category}" 
		modelAttribute = "newCategory">
	
		<spring_form:hidden path="categoryId" value = "${newCategory.categoryId}"/>
	
		<spring_form:label path="name">NAME:</spring_form:label>
    	<spring_form:input type="text" path="name" placeholder = "${oldName}"/><br>
    	
    	<label for="description">DESCRIPTION:</label>
    	<textarea rows="10" cols="10" id = "description" name = "description">${oldDescription}
    	</textarea><br>
    	
    	 <label for = "isAvailableOnline">IS AVAILABLE ONLINE</label>
    	 	<input list="options" id="isAvailableOnline" name="isAvailableOnline">
    	 	<datalist id = "options">
    		<option value = "YES"/>
    		<option value = "NO"/>
    	</datalist>
    	<input type="submit" value="Submit">
    	
		</spring_form:form>
	</div>

</body>
</html>