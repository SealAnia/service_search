<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CATEGORIES OF SERVICE</title>
</head>
<body>

	<div>
		<table>
			<tr>
				<th>Category ID</th>
				<th>Name</th>
			</tr>
			<c:forEach items = "${categories}" var = "category">
			<tr>
				<td>${category.categoryId}</td>
				<td><a href = "/categories/by_id/${category.categoryId}">${category.name}</a></td>
			</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>