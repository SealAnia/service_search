<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>NO SUCH COMPANY</title>
</head>
<body>

	NO COMPANY WITH ${companyId} FOUND! 
	
<script>
setTimeout(function(){
	window.location.href = '/companies/all';
}, 5 * 1000);
</script>

</body>
</html>