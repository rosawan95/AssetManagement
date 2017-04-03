<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fo"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="design.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<body>

	<%@ include file="new.html"%>
	<div align="right"> Welcome  ${userName }&nbsp;&nbsp;&nbsp;   <u> <%@ include file="logout.jsp" %><br> </u></div>
	
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<center>
		<div class="container">
			<h1 id="mgr">Add Request Form</h1>
			<br> <br>

			<form action="addRequest.do" method="post">
		<input type="hidden" name="mgrId" value="${sessionScope.mgrId}" />
		<table>
			<tr>
				<td>Employee Name:</td>
				<td><select name="empName" required>
						<fo:forEach var="emps" items="${sessionScope.availableEmps}">
							<option value="${emps}">${emps}</option>
						</fo:forEach>
				</select></td>
			</tr>
			<tr>
				<td>Asset Name:</td>
				<td><select name="assetName" required>
						<fo:forEach var="assets" items="${sessionScope.availAssets}">
							<option value="${assets}">${assets}</option>
						</fo:forEach>
				</select></td>
				<!-- <td><input type="text" name="assetName" required
					pattern="^[a-zA-Z]{3,15}"></td> -->
			</tr>
			<tr>
				<td>Asset Required Date:</td>
				<td><input type="date" name="reqDate"  required>${errorMsg}</td>
			</tr>
			<tr>
				<td>No of Days:</td>
				<td><input type="number" min ="1"  name="reqDays" required></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit Request"></td>
				<td><input type="reset" value="Clear"></td>
			</tr>
			</form>
		</div>
		
	</center>
	<center>
	
	 </center>
</table></form>
 <a href="managerHomePage.jsp"><button type="submit" id="nwi">Home</button></body></a>
</html>