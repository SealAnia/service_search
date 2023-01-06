<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="spring_form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ADD DESCRIPTION OF SERVICE</title>
</head>
<body>

	<c:url value = "/goods" var = "portfolio"/>
	
	<spring_form:form action = "${portfolio}" method="post" modelAttribute="portfolio">
	
    <form method="POST" enctype="multipart/form-data"
        action="/goods">
        File to upload: <input type="file" name="image"><br /> IMAGE: <input
            type="text" name="description"><br /> <br /> <input type="submit"
            value="Upload"> Press here to upload the file!
    </form>
    
    <input type="submit" value="Submit">
    
	</spring_form:form>

</body>
</html>