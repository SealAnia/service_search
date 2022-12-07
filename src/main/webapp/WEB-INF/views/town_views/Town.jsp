<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="util.AllWordsToUpperCase" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ALL TOWNS</title>
</head>
<body>

	<div>
		<table>
			<tr>
				<th>Town ID</th>
				<th>Town Name</th>
				<th>Region</th>
			</tr>
			<c:forEach items = "${towns}" var = "town">
			<tr>
				<td>${town.townId}</td>
				<td>${AllWordsToUpperCase.allWordsToUpperCase(town.name)}</td>
				<td>${AllWordsToUpperCase.allWordsToUpperCase(town.region.name)}</td>
			</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>