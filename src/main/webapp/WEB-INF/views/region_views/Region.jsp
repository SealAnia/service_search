<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import = "util.AllWordsToUpperCase" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>REGIONS</title>
</head>
<body>

	<div>
	
		<table>
			<tr>
				<th>ID</th>
				<th>Region name</th>
			</tr>
			<c:forEach items = "${regions}" var = "region">
			<tr>
				<td>${region.regionId}</td>
				<td>${AllWordsToUpperCase.allWordsToUpperCase(region.name)}</td>
			</tr>
			</c:forEach>
		</table>
		
	</div>

</body>
</html>