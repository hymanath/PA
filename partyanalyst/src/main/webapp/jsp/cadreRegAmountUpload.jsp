<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<title>Cadre Registration Report</title>

<script>
$(function() {
$( "#date" ).datepicker();
});

</script>
</head>

<body>
	<div id="contentDivId" align="center" style="margin-top:50px;margin-bottom:100px;">
	<s:form name="cadreRegAmountFileUpload" action="cadreRegAmountFileUpload.action" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<td><label>Select Date : </label></td>
			<td><input type="text" id="date" name="date"></input></td>
		</tr>
		<tr>
			<td><label>Select File : </label></td>
			<td><input type="file" id="file" name="file"></input></td>
		</tr>
		<tr align="left">
			<td></td>
			<td align="centre"><input type="submit" value=" Submit "></td>
		</tr>
	</table>
	</s:form>

	<table>
		<tr><td></td><td><b>1. Xls should be in Ms-Excel 97-2007 foemat with extention .xls .</b></td></tr>
		<tr><td></td><td><b>2. First row should contains column headers .</b></td></tr>
		<tr><td></td><td><b>3. Data should e in bellow format .</b></td></tr>
			<tr align="left"><td></td><td>&nbsp;&nbsp;&nbsp;I. Column - Branch name</td></tr>
			<tr align="left"><td></td><td>&nbsp;&nbsp;&nbsp;II. Column - Amount</td></tr>
			<tr align="left"><td></td><td>&nbsp;&nbsp;&nbsp;III. Column - Username</td></tr>
		<tr><td></td><td><b>4. Data should be in First work sheet .</b></td></tr>
	</table>
	</div>
	
</body>
</html>
