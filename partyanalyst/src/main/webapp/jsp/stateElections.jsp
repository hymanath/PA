<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Constituency Details</title>
<style type="text/css">
table.ConstituencyElectionsTable{
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}
table.ConstituencyElectionsTable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}
table.ConstituencyElectionsTable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}

</style>
	
</head>
<body >
<div id="ResultsDiv" style="padding:5px;">
    <h2>State Election</h2>
	
	<table class="ConstituencyElectionsTable" border="1">
	<tr>
		
		<th>Party Name</th>
		<th>Total Seats Won</th>
		

	</tr>
	<c:forEach var="partyResults" items="${stateElectionResults}" >		
	<tr>
	   <c:forEach var="Results" items="${partyResults.partyResultsVO}" >
		<td><c:out value="${Results.partyName}"/></td>
		<td><c:out value="${Results.totalSeatsWon}"/></td>
		</c:forEach>
	</tr>
	
	</c:forEach>
</table><br>
</div>

</body>
</html>