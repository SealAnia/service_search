<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>NO SUCH TOWN</title>
</head>
<body>

	NO TOWN WITH ID ${townId} FOUND! 
	
<script>
setTimeout(function(){
	window.location.href = '/towns/all';
}, 5 * 1000);
</script>

</body>
</html>