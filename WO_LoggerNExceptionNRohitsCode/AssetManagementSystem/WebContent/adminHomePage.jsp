<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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

<h1 id="mgr">Admin home page</h1><br><br>



 <a href="showAssets.do"><button type="submit" id="nwo">View Asset List</button></a>
 <br/>
 <a href="showPendingRequests.do"><button type="submit" id="nwo">Show Request</button></a>
 <br/>
 <a href=showReport.do><button type="submit" id="nwo">Generate Reports</button></a>

</div>
</center>
</body>
</html>