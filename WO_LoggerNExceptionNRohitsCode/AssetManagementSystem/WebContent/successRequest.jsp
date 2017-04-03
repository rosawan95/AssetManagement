<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="new.html"%>
	<div align="right">
		Welcome ${userName }&nbsp;&nbsp;&nbsp; <a href="logout.do">logout</a>
	</div>
	<br>

	<center>
		Raised request for employee with Employee Id: ${sessionScope.request.mgrId} and the Request Id is: ${requestId}
	</center> 
	<center>
	<a href="managerHomePage.jsp"><button type="submit" id="nwi">Home</button></a>
	</center>
</body>
</html>