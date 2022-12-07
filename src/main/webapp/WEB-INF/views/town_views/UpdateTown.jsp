<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="spring_form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>UPDATE A ROLE</title>
</head>
<body>

	<c:url value="/towns/by_id/${town.townId}" var="town"/>
	
	<spring_form:form action="${town}" 
	modelAttribute = "newTown">
	
	<spring_form:hidden path="townId" value = "${newTown.townId}"/>
	
	<spring_form:label path="name">NAME:</spring_form:label>
    <spring_form:input type="text" path="name" placeholder = "${oldName}"/><br>
    
    <label for = "regionId">REGION</label>
    <datalist id = "regionlist">
    	<c:forEach items = "${regions}" var = "region">
    		<option value = "${regionregionId}"> - ${region.name}
    	</c:forEach>
    </datalist>
    <input type = "number" id = "regionId" name = "regionId" placeholder = "${oldRegion}" list = "regionlist"><br>
    
    <input type="submit" value="Submit">
    
	</spring_form:form>

</body>
</html>