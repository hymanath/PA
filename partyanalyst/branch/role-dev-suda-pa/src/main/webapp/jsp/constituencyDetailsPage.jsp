<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		 <h6><U><s:property value="name" />  Details </U></h6>
<table width="800px" border="1">
	<tbody><tr>
		<th>Name</th>
		<th>Election Type</th>
		<th>District</th>
		<th>State</th>
	</tr>
	<tr>
	<td width="200px"><s:property value="name" /></td>
	<td width="200px"><s:property value="electionType" /></td>
	<td width="200px"><s:property value="districtName" /></td>
	<td width="200px"><s:property value="stateName" /></td>
	</tr>
	</tbody></table>
</body>
</html>