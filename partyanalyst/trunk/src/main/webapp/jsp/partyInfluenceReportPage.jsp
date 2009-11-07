<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="s" uri="/struts-tags" %>
 <%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Party Influence Report</title>
</head>
<body>
		<h3>Party Influence Report</h3>
		<div id="influenceReportMain">
			<table>
			<tr>
				<td>District Name :</td>
				<td><s:property value="districtResults.districtName"/></td>
			</tr>
			<tr>
				<td>State Name :</td>
				<td><s:property value="districtResults.stateName"/></td>
			</tr>
			<tr>
				<td>Votes Percentage:</td>
				<td><s:property value="districtResults.districtVotesPercentageDiff"/>%</td>
			</tr>
			</table>
			
			<c:forEach var="result" items="${districtResults.constituencyElectionsDetailedResults}">
				<c:out value="hi"></c:out>
			
			</c:forEach>
		</div>
</body>
</html>