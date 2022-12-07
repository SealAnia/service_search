<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LIST OF COMPANIES</title>
</head>
<body>

	<div>
		
		<table>
			<tr>
				<th>ID</th>
				<th>Company name</th>
				<th>Registration number</th>
			</tr>
			<c:forEach items = "${companies}" var = "company">
			<tr>
				<td>${company.companyId}</td>
				<td>${company.name}</td>
				<td>${company.registrationNumber}</td>
			</tr>
			</c:forEach>
		</table>
		
	</div>

</body>
</html>