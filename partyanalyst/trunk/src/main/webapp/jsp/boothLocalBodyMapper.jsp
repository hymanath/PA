<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Map Booth To Local Election Body</title>
</head>
<body>
		<table>
			<tr>
				<td colspan="2" align="left"><div id="errorMessageDiv"></div></td>
				
			</tr>
			<tr>
				<td align = "right">State:</td>
				<td><s:select id="stateId" theme="simple" cssClass="selectWidth" list="result" listKey="id" listValue="name" onchange="getDistrictsList(this.value)" /></td>
			</tr>
			<tr>
				<td align = "right">District:</td>
				<td><s:select id="stateId" theme="simple" cssClass="selectWidth" list="result" listKey="id" listValue="name" onchange="getDistrictsList(this.value)" /></td>
			</tr>
			<tr>
				<td align = "right">Local Election Body:</td>
				<td><s:select id="stateId" theme="simple" cssClass="selectWidth" list="result" listKey="id" listValue="name" onchange="getDistrictsList(this.value)" /></td>
			</tr>
			<tr>
				<td align = "right">Assembly:</td>
				<td><s:select id="stateId" theme="simple" cssClass="selectWidth" list="result" listKey="id" listValue="name" onchange="getDistrictsList(this.value)" /></td>
			</tr>
			<tr>
				<td align = "right">Year:</td>
				<td><s:select id="stateId" theme="simple" cssClass="selectWidth" list="result" listKey="id" listValue="name" onchange="getDistrictsList(this.value)" /></td>
			</tr>
		</table>
</body>
</html>