<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>

 <%@page import="com.itgrids.partyanalyst.dto.StatePageVO" %>
 <%@page import="com.itgrids.partyanalyst.dto.PartyResultsVO" %>
 <%@page import="com.itgrids.partyanalyst.dto.StateElectionsVO" %>
 <%@page import="java.util.ArrayList" %>
 <%@page import="java.util.List" %>

<HTML>
 <HEAD>
  <TITLE> State Page - <c:out value="${statePage.stateName}" /></TITLE>
  <META NAME="Generator" CONTENT="EditPlus">
  <META NAME="Author" CONTENT="">
  <META NAME="Keywords" CONTENT="Andhra Pradesh State, Election">
  <META NAME="Description" CONTENT="">
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
	background-color: #CFDCE4;
}
table.stateResultsTable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}
    <!-- Dependencies --> 
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<!-- OPTIONAL: JSON (enables JSON validation) --> 
	<script type="text/javascript" src="js/json/json-min.js"></script> 
  	<!-- Dependencies --> 
   	<script type="text/javascript" src="js/yahoo/yahoo-min.js" ></script>
 	<script type="text/javascript">	
  </style>
  <script type="text/javascript">
  function callAjax(param){
 		var myResults;
 		var url = "<%=request.getContextPath()%>/stateElectionResultsAjax.action?"+param;
 		var callback = {			
 		               success : function( o ) {
							try {
								myResults = YAHOO.lang.JSON.parse(o.responseText);	
								
								insertData(param,myResults.stateElectionResults.partyResultsVO);								
							}catch (e) {   
							   	alert("Invalid JSON result" + e);   
							}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('GET', url, callback);
 	}
 	
  
function doAjax(param){

	var tableElmt=document.getElementById("table"+param);
	var spanElmt=document.getElementById("span"+param);
	var searchElmt=document.getElementById("search"+param);
    searchElmt.style.display="block"
	if(tableElmt.style.display=="block")
	{
		tableElmt.style.display="none";
		searchElmt.style.display="none";
		spanElmt.innerHTML=" (view results)";
		return;
	}
 	callAjax("electionId="+param);
 }

function insertData(param,results)
{

	var index=param.indexOf("=");
	var subStr=param.substr(index+1)
	var tableElmt=document.getElementById("table"+subStr);
	var spanElmt=document.getElementById("span"+subStr);	
	var searchElmt=document.getElementById("search"+subStr);
	
	tableElmt.style.display="block";
	var str='';
	str+='<tr>';
	str+='	<th align="center"> Party Name </th>';
	str+='	<th align="center"> Seats Won </th>';
	str+='</tr>';
	
	for(var item in results)
	{		
		str+='<tr>';
		str+='<td colspan="2">'+results[item].partyName+'</th>';
		str+='<td align="center">'+results[item].totalSeatsWon+'</td>';
		str+='</tr>';
	}
	
    
	tableElmt.innerHTML=str;	

	spanElmt.innerHTML=" (close)";
	searchElmt.style.display="none";
}
  </script>
 </HEAD>

 <BODY>
<div id="stateOuterDiv">
		<div style="float: right; width: 600px; text-align: left; font-family: Trebuchet MS; font-weight: bold; color: Black;" id="stateInfoDiv">
			 <h1> <c:out value="${statePage.stateName}" /> State Details</h1>
 <h5>
 <table border="0" cellpadding="0" cellspacing="0">
 <tr>
	 <td align="left"><c:out value="  State Capital " /> : </td>
	 <td  align="left"><c:out value="${statePage.adminCapital}" /></td>
	 <td></td>
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
<table border="0" width="70%"><tr></tr><tr><td align="left"><br><font size="3"><b><c:out value="${statePage.stateName}" />  Previous Elections Results:</b></font></td></tr></table>


<c:forEach var="state" items="${stateElections}">
<table border="0" >
     <tr></tr>
	<tr>
		<td align="center">
		<ul type="square" style="margin:0"><li><c:out value="${state.electionType}" /> Election In
		</td>
		<td align="center">
			<c:out value="${state.year}" /><span id="span${state.electionId}" style="color:blue;cursor:pointer;" onclick="doAjax(${state.electionId});"style="cursor:pointer;"> <c:out value="(view results)" /></span> 
		</li></ul></td>
		<td id="search${state.electionId}" align="left" style="display:none;"><img src="<%=request.getContextPath()%>/images/icons/arrows.gif" /></img></td>
		
	</tr>
</table>
<table style="display:none;" id="table${state.electionId}" class="stateResultsTable">
</table>


</c:forEach>

</div>
</div>

 </BODY>
</HTML>


    

