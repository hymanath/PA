<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>

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

  
 <script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/treeview/treeview-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dragdrop/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/history/history.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/container/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yuiloader/yuiloader-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/button/button-min.js"></script>
	<script src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></script>

	
	<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>

	<script type="text/javascript" src="js/yahoo/yui-gallery/gallery-accordion-min.js"></script>

	<!-- YUI Skin Sam -->

	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/yui-gallery-styles/gallery-accordion.css">	
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
	<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
	<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/treeview/assets/skins/sam/treeview.css">
	<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">
	<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">

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
		#districtInfoDiv
		{
			margin-top:10px;
			margin-bottom:10px;
			margin-right:30px;
		}
		#districtInfoDivHead
		{
			font-weight:bold;
			color:#1C4B7A;
			text-decoration:underline;
			font-size:15px;
			padding:5px 5px 5px 0px;
		}
		#districtInfoDivBody
		{
			padding:5px;
			background-color:#F1F5F7;
		}				
		#districtAncSpan
		{
			padding:10px;
		}
		.districtAnc
		{
			color:#1C4B7A;
		}	
		
		.yui-skin-sam .yui-dt table
		{
			border:medium none LemonChiffon;
			border-collapse:separate;
			color:DarkOliveGreen;
			font-family:verdana;
			font-size:inherit;
			font-weight:bold;
			margin:0;
			padding:0;
			text-align:center;
		}
		
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

	var myColumnDefs = [ 
	            {key:"Party Name", sortable:true, resizeable:true}, 
	            {key:"Seats Won", sortable:true,resizeable:true}
	        ]; 
	
	
	var myDataSource = new YAHOO.util.DataSource(exData.partys); 
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	myDataSource.responseSchema = { 
	     fields: ["Party Name","Seats Won"] 
	}; 
	
	var myDataTable = new YAHOO.widget.DataTable("table"+subStr,myColumnDefs, myDataSource); 
		
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
	str+='<table id="elecResultsTab">';
	for(var item in results)
	{		
		console.log(results[item].partyFlag);
		str+='<tr>';
		str+='<td><a href="partyPageAction.action?partyId='+results[item].partyId+'">'+results[item].partyName+'</a></td>';
		if(results[item].partyFlag)
			str+='<td><img src="<%=request.getContextPath()%>/images/party_flags/'+results[item].partyFlag+'" height="30" width="40"/></td>';
		else	
			str+='<td><img src="<%=request.getContextPath()%>/images/party_flags/no_Image.png" height="30" width="40"/></td>';
		str+='<td align="center">'+results[item].totalSeatsWon+'</td>';
		str+='</tr>';
	}
	str+='</table>';
	
	tableDivElmt.innerHTML=str;	

	spanElmt.innerHTML=" (close)";
	searchElmt.style.display="none";
	
	var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
			.get("elecResultsTab"));
	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
	resultsDataSource.responseSchema = {
		fields : [ {
			key : "partyName"
		},{
			key : "partyFlag"
		}, {
			key : "totalSeatsWon",parser:"number"
		}]
	};

	var resultsColumnDefs = [ {
		key : "partyName",
		label : "PARTY NAME",
		sortable : true
	},{
		key : "partyFlag",
		label : "PARTY Flag"
	}, {
		key : "totalSeatsWon",
		label : "SEATS WON",
		sortable : true
	}];

	
	var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 10
			    }) 
				};	

   	var myDataTable = new YAHOO.widget.DataTable(tableDivElmt,resultsColumnDefs, resultsDataSource,myConfigs);  

}
  </script>
 </HEAD>

 <BODY>
 <h2><u style="color:#1C4B7A;"><c:out value="${statePage.stateName}" /> State Details</u></h2>
<div id="stateOuterDiv" style="text-align:left;margin-left:50px;">
	

 <table border="0" cellpadding="0" cellspacing="0" class="stateDetailsTable">
 <tr>
	 <td align="left" style="color:#1C4B7A;"><c:out value="State Capital "/> </td>
	 <td  align="left" style="color:#18325A;font-weight:bold;"> : <c:out value="${statePage.adminCapital}" /></td>

	 <td  align="left" style="color:#1C4B7A;padding-left:20px;"><c:out value="Total Districts"/></td>
	 <td align="left" style="color:#18325A;font-weight:bold;"> : <c:out value="${districtNumber}" /></td>	
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

<div id="districtInfoDiv">
	<div id="districtInfoDivHead">
		District's Info
	</div>
	<div id="districtInfoDivBody">
		<table><tr>
		<c:forEach var="district" varStatus="stat" items="${districtData}">				
			<td>
				<span id="districtAncSpan">
					<img height="10" width="10" src="<%=request.getContextPath()%>/images/icons/arrow.png"/>
					<a href="districtPageAction.action?districtId=${district.id}&districtName=${district.name }" class="districtAnc" style="text-decoration:none;" onmouseover="javascript:{this.style.textDecoration='underline';}" onmouseout="javascript:{this.style.textDecoration='none';}">${district.name}
					</a>
				</span>
			</td>	
			<c:if test="${stat.count % 7==0}">
				</tr><tr><td colspan="7"> </td></tr><tr>
			</c:if>			
		</c:forEach>			
		</tr></table>		
	</div>
</div>

<h3><u style="color:#1C4B7A;">Census Info *</u></h3>

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
<c:if test="${stateElections != null }">
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
<div id="electionResultsDiv" class="yui-skin-sam">
<div style="display:none;" id="table${state.electionId}">
</div>
</div>
<br/><br/>
</c:forEach>
</c:if>

</div>

 </BODY>
</HTML>


    

