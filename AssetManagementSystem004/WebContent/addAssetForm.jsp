<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="design.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>

</head>
<body>

		<%@ include file="new.html" %>
<div align="right"> Welcome  ${userName }&nbsp;&nbsp;&nbsp;   <u> <%@ include file="logout.jsp" %><br> </u></div>

	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<center>
		<div class="container">
			<h1 id="mgr">Add New Asset Form</h1>
	<form name="addAsset" method="post" action="addNewAsset.do">
		<table>
			<tr>
				<td>Asset Name:</td>
				<td><input type="text" name="astname" id="astm" required /></td>
			</tr>
			<tr>
				<td>Asset Description</td>
				<td><input type="text" name="astdesc" id="astm"  required></td>
			</tr>
			<tr>
				<td>Quantity</td>
				<td><input type="number" min="1"  name="aqty" id="astm"  required /></td>
			</tr>
			</table>
		
				<input type="submit" value="ADD Asset" id="nwo"/>
				<input type="reset" value="Reset" id="nwo1"/>
			

		
	</form>
	</div>
	
 <a href="adminHomePage.jsp"><button type="submit" id="nwi">Home</button></a>
</center>
</body>
</html>