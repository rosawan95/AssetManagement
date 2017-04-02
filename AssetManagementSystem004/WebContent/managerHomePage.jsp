<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="design.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<body>
		<%@ include file="new.html" %>


<div align="right"> Welcome  ${userName }&nbsp;&nbsp;&nbsp;   <u> <%@ include file="logout.jsp" %><br> </u></div>
<br/>
<br/>
<br/>
<br/>
<br/>
<center>
<div class="container">

<h1 id="mgr">Manager home page</h1><br><br>

<div align="center" > ${requestStatus } </div>
<!-- <a href="requestForm.jsp"> Request Asset</a> -->
<a href="createRequestData.do"><button type="submit">Request Asset</button></a>
<br><br>
<a href="showAllRequests.do"><button type="submit">Check Request status</button></a>

</div>
</center>

</body>
</html>


