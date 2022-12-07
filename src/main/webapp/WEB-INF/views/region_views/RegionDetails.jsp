<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="util.AllWordsToUpperCase" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>REGION DETAILS</title>
</head>
<body>

	${region.toString()} <br>
	
	<c:forEach items = "${towns}" var = "town">
		${AllWordsToUpperCase.allWordsToUpperCase(town.name)}<br>
	</c:forEach>

</body>
</html>