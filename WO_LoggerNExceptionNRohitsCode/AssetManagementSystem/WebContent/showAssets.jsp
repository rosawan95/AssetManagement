<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fo"%>

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
<br/>

	<table border="1" align="center" id="fon">
		<tr>
			<th>ASSET ID</th>
			<th>ASSET NAME</th>
			<th>ASSET DESCRIPTION</th>
			<th>QUANTITY</th>
			<th>STATUS</th>
			<th>ADD / MODIFY</th>
			<th>DELETE</th>
		</tr>
		<fo:forEach var="asset" items="${asset}">

			<tr>
				<td>${asset.assetId}</td>
				<td>${asset.assetName}</td>
				<td>${asset.assetDesc}</td>
				<td>${asset.quantity}</td>
				<td>${asset.status}</td>
				<td><a href="adminModify.do?id=${asset.assetId }">ADD/MODIFY</a></td>
				<td><a href="delete.do?id=${asset.assetId }">DELETE</a></td>
			</tr>

		</fo:forEach>
	</table>
	<br/>
	<br/>
	<center>
	 <a href="addAssetForm.jsp"><button type="submit" id="nwi">Add New Asset</button></a>
<br><br>
 <a href="adminHomePage.jsp"><button type="submit" id="nwi">Go Back To Home Page!</button></a>
</center>

</body>
</html>

