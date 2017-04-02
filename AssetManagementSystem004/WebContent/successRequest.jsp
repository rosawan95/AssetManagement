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
<div class="container">
	<center>
		Raised request for employee with Employee Id: ${sessionScope.request.mgrId} and the Request Id is: ${requestId}
	</center> 
	<center>
	<a href="managerHomePage.jsp"><button type="submit"  id="nwi">Home</button></a><!--  -->
	<br><br>
	<a href="createRequestData.do"><button type="submit" id="nwi">Request Asset</button></a>
	<br><br>
	<a href="showAllRequests.do"><button type="submit" id="nwi">Check Request status</button></a>
	</center>
</div>
</body>
</html>