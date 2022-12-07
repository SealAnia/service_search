<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CATEGORY DETAILS</title>
</head>
<body>

	${category.toString()}<br>
	DESCRIPTION: 
	${category.comment}<br>
	${isAvailableOnline}

</body>
</html>