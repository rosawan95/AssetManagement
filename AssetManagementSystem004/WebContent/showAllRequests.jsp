<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ include file="new.html" %>
<div align="right"> Welcome  ${userName }&nbsp;&nbsp;&nbsp;   <u> <%@ include file="logout.jsp" %><br> </u></div>
<table border="1" align="center">
<tr>
	  <th>Requestid</th>
	  <th>Empno</th>
	  <th>Assetid</th>
	  <th>Requestdate</th>
	  <th>Requestfordays</th>
	  <th>Status</th>
  </tr>
  
  
  <fo:forEach var="request" items="${data}">
  <tr>
	<td>${request.requestId} </td>
	<td>${request.empNo}</td>
	<td>${request.assetId}</td>
	<td>${request.requestDate}</td>
	<td>${request.requestForDays}</td>
	<td>${request.status}</td>
  </tr>
  </fo:forEach>

</table>
<center>
	<a href="managerHomePage.jsp"><button type="submit" >Home</button></a><!-- id="nwi" -->
	<br><br>
	<a href="createRequestData.do"><button type="submit">Request Asset</button></a>
	<br><br>
	</center>

</body>
</html>