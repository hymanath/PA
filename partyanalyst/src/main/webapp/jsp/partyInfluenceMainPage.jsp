<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="s" uri="/struts-tags" %>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Party Influence Page</title>
<style type="text/css">
table.CandidateElectionResultsTable{
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}
table.CandidateElectionResultsTable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #6380BA;
	color:#FFFFFF;
}
table.CandidateElectionResultsTable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}

</style>
</head>
<body>

	<h3><u>New Party Influence Report</u></h3>
	<s:form action = "partyInfluenceAction" theme="simple">		
	<table class="CandidateElectionResultsTable" width="300px" border="1">
	<tr>
	  <th colspan="2"><s:label value="NewPartyInfluence Input"/> 
	  </th>
	 </tr>
	<tr>
		<th><s:label for="electionTypeSelect" value="Election Type"/> </th>
		<td><s:select id="electionTypeSelect" name="electionType" label="Election Type" list="electionTypes" listKey="id" listValue="name"></s:select></td>
	</tr>
	<tr>
	    <th> <s:label for="stateSelect" value="State"/> </th>
		<td><s:select id="stateSelect" name="stateName" label="State" list="states" listKey="id" listValue="name"></s:select></td>
	</tr>
	<tr>
		<th> <s:label for="electionYearSelect" value="Election Year"/> </th>
		<td><s:select  name="electionYear" id="electionYearSelect" label="Election Year" list="electionYears"></s:select></td>
	</tr>
	<tr>
		<th> <s:label for="newPartySelect" value="New Party"/> </th>
		<td><s:select name="newParty" id="newPartySelect" label="New Party" list="newParty" listKey="id" listValue="name"></s:select></td>
	</tr>
	<tr>
		<th> <s:label for="partyNameSelect" value="Party Name"/> </th>
		<td><s:radio  name="partyName" id="partyNameSelect" label="Party Name" list="partyNames" listKey="id" listValue="name" ></s:radio>	</td>
	</tr>	
	<tr>
	    <th><s:label for="alliancePartySelect" value="Alliance Parties"/> </th>
	    <td>
			<s:checkbox id="alliances" name="alliances" value="hasAllianceParties"></s:checkbox> Include in the Report
		</td>
	</tr>
	<tr>
		<th colspan="2" align="center"><s:submit value="View Party Influence Report"/></th>
	</tr>
	</table>	
	
	</s:form>
</body>
</html>