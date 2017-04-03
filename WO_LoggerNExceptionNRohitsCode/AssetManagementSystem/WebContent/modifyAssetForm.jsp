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

<center>
<div class="container">
			<h1 id="mgr">Modify Asset Form</h1>
	<form name="modAsset" method="post" action="adminUpdateConfirm.do?id=${asset.assetId }">
		<table>
			<tr>
				<td>Asset ID:</td>
				<td><input type="text" name="asstid" value="${asset.assetId }" id="astm" readonly="readonly" /></td>
			</tr>
			<tr>
				<td>Asset Name:</td>
				<td><input type="text" name="asstnm" value="${asset.assetName }" id="astm" readonly="readonly" required></td>
			</tr>
			<tr>
				<td>Asset Description:</td>
				<td><input type="text" name="asstdes" value="${asset.assetDesc }" id="astm" readonly="readonly" required /></td>
			</tr>
			<tr>
				<td>Quantity:</td>
				<td><input type="number" name="quantity" value="${asset.quantity }"  id="astm" readonly="readonly" /></td>
			</tr>
			<tr>
				<td>Add Quantity:</td>
				<td><input type="number" min="0" required name="addedquantity" id="astm" /></td>
			</tr>
			</table>
			
				<input type="submit" value="Confirm Update" id="nwo" />
			

			
				<td><input type="reset" value="Reset Details" id="nwo" /></td>
			

		
	</form>
	</div>
	</center>

</body>
</html>
