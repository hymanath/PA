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
	<script type="text/javascript" src="js/yahoo/element-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/datasource-min.js" ></script>
	<script type="text/javascript" src="js/json/json-min.js"></script>   
   	<script type="text/javascript" src="js/yahoo/yahoo-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/get-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/calendar-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/datatable-min.js" ></script>

	<link href="styles/yuiStyles/datatable.css" rel="stylesheet" type="text/css" />

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
								//buildDataTable(param,myResults.stateElectionResults.partyResultsVO);
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


function buildDataTable(param,results)
{
	var index=param.indexOf("=");
	var subStr=param.substr(index+1)
	var tableDivElmt=document.getElementById("table"+subStr);
	var spanElmt=document.getElementById("span"+subStr);	
	var searchElmt=document.getElementById("search"+subStr);

	tableDivElmt.style.display="block";

	exData = { 
	    partys: [ 
	         
	    ] 
	}

	for (var i in results)
	{
		exData.partys[i]=
			{
				name:results[i].partyName,
				seats:results[i].totalSeatsWon	
			}
	}

	console.log(exData.partys);

	YAHOO.example.Basic = function(){
	 var myColumnDefs = [ 
	            {key:"Party Name", sortable:true, resizeable:true}, 
	            {key:"Seats Won", sortable:true,resizeable:true}
	        ]; 
	
	console.log(myColumnDefs);

	var myDataSource = new YAHOO.util.DataSource(exData.partys); 
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	myDataSource.responseSchema = { 
	     fields: ["Party Name","Seats Won"] 
	}; 
	
	var myDataTable = new YAHOO.widget.DataTable("table"+subStr,myColumnDefs, myDataSource, {caption:"DataTable Caption"}); 
	return 
		{ 
	      oDS: myDataSource
	      
	    }; 
	}(); 

	spanElmt.innerHTML=" (close)";
	searchElmt.style.display="none";
}
function insertData(param,results)
{

	var index=param.indexOf("=");
	var subStr=param.substr(index+1)
	var tableDivElmt=document.getElementById("table"+subStr);
	var spanElmt=document.getElementById("span"+subStr);	
	var searchElmt=document.getElementById("search"+subStr);
		
	tableDivElmt.style.display="block";
	
	var str='';
	str+='<table style="margin-left:20px;margin-top:10px;" class="stateResultsTable">';
	str+='<tr>';
	str+='	<th align="center"> Party Name </th>';
	str+='	<th align="center"> Seats Won </th>';
	str+='</tr>';
	
	for(var item in results)
	{		
		str+='<tr>';
		str+='<td>'+results[item].partyName+'</th>';
		str+='<td align="center">'+results[item].totalSeatsWon+'</td>';
		str+='</tr>';
	}
	str+='</table>';
	
	tableDivElmt.innerHTML=str;	
	
	spanElmt.innerHTML=" (close)";
	searchElmt.style.display="none";
}
  </script>
 </HEAD>

 <BODY>
 <h2><u style="color:#1C4B7A;"><c:out value="${statePage.stateName}" /> State Details</u></h2>
<div id="stateOuterDiv" style="text-align:left;margin-left:50px;">
	

 <table border="0" cellpadding="0" cellspacing="0">
 <tr>
	 <td align="left" style="color:#1C4B7A;"><c:out value="State Capital "/> </td>
	 <td  align="left" style="color:#18325A;font-weight:bold;"> : <c:out value="${statePage.adminCapital}" /></td>
 </tr>
 <tr>
	<td  align="left" style="color:#1C4B7A;"><c:out value="State Language"/> </td>
    <td  align="left" style="color:#18325A;font-weight:bold;"> : <c:out value="${statePage.stateLanguage}" /></td>
 </tr>
 <tr>
	<td  align="left" style="color:#1C4B7A;"><c:out value="State Song"/></td>
    <td align="left" style="color:#18325A;font-weight:bold;"> : <c:out value="${statePage.stateSong}" /></td>	
</tr>
</table>

<h3><u style="color:#1C4B7A;">Census Info</u></h3>

<table border="1" width="70%" class="stateResultsTable">
	<tr>
		<th>Type</th>
		<th align="center">Total Population</th>
		<th align="center">Male Population</th>
		<th align="center">Female Population</th>
	</tr>
	<c:forEach var="census" items="${censusVO}">
	<tr>
		<td align="left" style="font-weight:bold;"><c:out value="${census.tru }" /></td>
		<td align="center"><c:out value="${census.totalPopulation }" /></td>
		<td align="center"><c:out value="${census.malePopulation }" /></td>
		<td align="center"><c:out value="${census.femalePopulation }" /></td>
	</tr>
	</c:forEach>
</table>
<H3><u  style="color:#1C4B7A;"><c:out value="${statePage.stateName}" />  Previous Elections Results:</u></H3>

<c:forEach var="state" items="${stateElections}">
<table border="0" >     
	<tr>
		<td><img height="10" width="10" src="<%=request.getContextPath()%>/images/icons/arrow.png"/> </td>
		<td align="center">
			<c:out value="${state.electionType}" /> Election In	<c:out value="${state.year}" /><span id="span${state.electionId}" style="color:#4F6177;cursor:pointer;" onclick="doAjax(${state.electionId});"style="cursor:pointer;"> <c:out value="(view results)" /></span> 
		</td>
		<td id="search${state.electionId}" align="left" style="display:none;"><img src="<%=request.getContextPath()%>/images/icons/arrows.gif" /></img></td>		
	</tr>
</table>
<div style="display:none;" id="table${state.electionId}">

</div>
<br/><br/>
</c:forEach>
</div>

 </BODY>
</HTML>


    

