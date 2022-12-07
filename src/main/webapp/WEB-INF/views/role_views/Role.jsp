<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>USERS BY ROLE</title>
</head>
<body>

	<div>
		<table>
			<tr>
				<th>Role ID</th>
				<th>Role Name</th>
			</tr>
			<c:forEach items = "${roles}" var = "role">
			<tr>
				<td>${role.roleId}</td>
				<td>${role.name.toLowerCase().substring(5)}</td>
			</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>