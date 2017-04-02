<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="design.css" rel="stylesheet" type="text/css">
		<title>Login</title>
	
	
	
	</head>
	<body>
			<%@ include file="new.html" %>


																																			
			<div align="center" style="color:red " >
			
			${errorMsg }
			
			<%-- <%
			String msg=(String)request.getAttribute("errorMsg");
			out.write(msg);
			%>	 --%>	
			</div>																																					
			<form action="authenticate.do" method="post" align="center"> 
			<!--   Username : <input type="text" name="userName"><br> -->
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
	<div class="container">
	<center>
    <label><b>Username</b></label>
    <input type="text" id="usn" placeholder="Enter Username" name="userName" required>
    <br/>
			 <!--  Password : <input type="password" name="password"><br> -->
			 <label><b>Password</b></label>
    <input type="password" id="pwd" placeholder="Enter Password" name="password" required>
			<!--    <input type="submit" value="Login">&nbsp;&nbsp;&nbsp;<input type="reset" value="Clear"> -->
			<br/>
			<button type="submit">Login</button>
			
			<br/>
			
   
    </center>
  </div>
			
			</form>
		
	</body>
</html>