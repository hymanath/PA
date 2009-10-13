<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>

 <%@page import="com.itgrids.partyanalyst.dto.StatePageVO" %>
 <%@page import="com.itgrids.partyanalyst.dto.PartyResultsVO" %>
 <%@page import="com.itgrids.partyanalyst.dto.StateElectionResultsVO" %>
 <%@page import="java.util.ArrayList" %>
 <%@page import="java.util.List" %>

<HTML>
 <HEAD>
  <TITLE> State Page - <c:out value="${statePage.stateName}" /></TITLE>
  <META NAME="Generator" CONTENT="EditPlus">
  <META NAME="Author" CONTENT="">
  <META NAME="Keywords" CONTENT="Andhra Pradesh State, Election">
  <META NAME="Description" CONTENT="">
  <script type="text/javascript">
	function displayResults(value,id)
	{
		
		var elmt=document.getElementById(value);
		var spanElmt=document.getElementById(id);
	
		if(elmt.style.display=="none")
		{
			elmt.style.display="";			
			spanElmt.innerHTML="(-)";
		}
		else
		{
			elmt.style.display="none";
			spanElmt.innerHTML="(+)";
		}
	}
  </script>
  <style type="text/css">
table.stateResultsTable{
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}
table.stateResultsTable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}
table.stateResultsTable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}

</style>
 </HEAD>

 <BODY>
<div id="stateOuterDiv">
		<div style="float: right; width: 500px; text-align: left; font-family: Trebuchet MS; font-weight: bold; color: Black;" id="stateInfoDiv">
			 <h1> <c:out value="${statePage.stateName}" /> State Details</h1>
 <h5>
 <table border="0" cellpadding="0" cellspacing="0">
 <tr>
	 <td align="left"><c:out value="  State Capital " /> : </td>
	 <td  align="left"><c:out value="${statePage.adminCapital}" /></td>
	 	<td  align="left"><c:out value="  State Language " /> : </td>
    <td  align="left"><c:out value="${statePage.stateLanguage}" /></td>
 </tr>
 <tr>     
</tr>
<tr>     <td  align="left">      <c:out value="  State Song  " /> : </td>
    <td align="left"> <c:out value="${statePage.stateSong}" /></td>
	<td colspan="2"></td>
</tr>
</table>
</h5> 
<table><tr><td align="Center"><h3>Census Info</h3></td></tr></table>
<table border="1" width="70%" class="stateResultsTable">
<tr><th>Type</th>
<th align="center">Total Population</th>
<th align="center">Male Population</th>
<th align="center">Female Population</th></tr>
<c:forEach var="census" items="${censusVO}">
<tr><td align="left"><c:out value="${census.tru }" /></td>
<td align="center"><c:out value="${census.totalPopulation }" /></td>
<td align="center"><c:out value="${census.malePopulation }" /></td>
<td align="center"><c:out value="${census.femalePopulation }" /></td></tr>
</c:forEach></table>
<table border="0" width="70%"><tr><td align="center"><br><font size="3"><b><c:out value="${statePage.stateName}" />  Previous Elections Results:</b></font></td></tr></table>


<c:forEach var="state" items="${stateElectionResults}">
<table border="0" width="40%" align="center">
	<tr>
		<td align="left">
			<h4><c:out value="${state.electionType}" /></h4>
		</td>
		<td align="right">
			<h5><c:out value="${state.electionYear}" /><span id="span${state.electionId}" style="color:blue;cursor:pointer;" onclick="displayResults(${state.electionId},this.id)" style="cursor:pointer;"> (+) </span> </h5> 
		</td>
		<td align="left"></td>
	</tr>
</table>
<table class="stateResultsTable" border="1" width="60%" cellpadding="3" cellspacing="5" id="${state.electionId}" style="display:none;">
	<tr>
		<th align="center">Party</th>
		<th align="right">Total seats Won</th>
	</tr>
	<c:forEach var="stateResults" items="${state.partyResultsVO}">
	<tr>
		<td align="left"><c:out value="${stateResults.partyName}" /></td>
		<td align="center"><c:out value="${stateResults.totalSeatsWon}" /></td>
	</tr>
	</c:forEach>
</table>
</c:forEach>
</div>
</div>


 </BODY>
</HTML>


    

