<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="s" uri="/struts-tags" %>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Party Influence Page</title>

</head>
<body>

	<h3><u>New Party Influence Report</u></h3>
	<s:form action = "partyInfluenceAction" theme="simple">		
	<table>
	<tr>
		<td> <s:label for="electionTypeSelect" value="Election Type"/> </td>
		<td><s:select id="electionTypeSelect" label="Election Type" list="electionTypes" listKey="id" listValue="name"></s:select></td>
	</tr>
	<tr>
	<td> <s:label for="stateSelect" value="State"/> </td>
		<td><s:select id="stateSelect" label="State" list="states" listKey="id" listValue="name"></s:select></td>
	</tr>
	<tr>
		<td> <s:label for="electionYearSelect" value="Election Year"/> </td>
		<td><s:select id="electionYearSelect" label="Election Year" list="electionYears"></s:select></td>
	</tr>
	<tr>
		<td> <s:label for="newPartySelect" value="New Party"/> </td>
		<td><s:select id="newPartySelect" label="New Party" list="newParty" listKey="id" listValue="name"></s:select></td>
	</tr>
	<tr>
		<td> <s:label for="partyNameSelect" value="Party Name"/> </td>
		<td><s:radio  id="partyNameSelect" label="Party Name" list="partyNames" listKey="id" listValue="name" ></s:radio>	</td>
	</tr>	
	<tr>
		<td colspan="2" align="center"><s:submit value="View Party Influence Report"/></td>
	</tr>
	</table>	
	
	</s:form>
</body>
</html>