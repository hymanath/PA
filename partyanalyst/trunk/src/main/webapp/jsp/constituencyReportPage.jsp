<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Constituency Election Report</title>



<!-- YUI Dependency files (Start) -->

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
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/resize/resize-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/layout/layout-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/carousel/carousel-min.js"></script>



<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>

<script type="text/javascript" src="js/yahoo/yui-gallery/gallery-accordion-min.js"></script>

<!-- YUI Skin Sam -->

<link rel="stylesheet" type="text/css" href="styles/yuiStyles/yui-gallery-styles/gallery-accordion.css">	
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/treeview/assets/skins/sam/treeview.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/resize.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/layout.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/carousel/assets/skins/sam/carousel.css">

<!-- YUI Dependency files (End) -->

<style type="text/css">

		.yui-skin-sam .yui-panel 
		{
			background:#FFFFFF none repeat scroll 0 0;
			border-color:#808080;
			border-style:solid;
			border-width:1px 0;
			left:0;
			position:relative;
			top:0;
			z-index:1;
		}
		.problemHeader
		{
			font-size:20px;
			color:salmon;
			text-align:center;
			color:salmon;
			font-size:20px;
		}
		.selectWidth
		{
			width:160px;
		}
</style>

<script type="text/javascript">
var type="";
var candidateDetails={
		candidateDetailsArray:[]
	};

function initializeResultsTable() {

var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
		.get(candidateDetails.candidateDetailsArray));
resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
resultsDataSource.responseSchema = {
	fields : [  {
		key : "candidateName"
	},{
		key : "constituencyName"
	}, {
		key : "votesEarned"
	}, {
		key : "result"
	}, {
		key : "partyName"
	}, {
		key : "electionYear"
	} ]
};

var resultsColumnDefs = [ {
	key : "candidateName",
	label : "Candidate Name",
	sortable : true
}, {
	key : "constituencyName",
	label : "Constituency Name",
	sortable : true
}, {
	key : "votesEarned",
	label : "Votes Earned",
	sortable : true
}, {
	key : "result",
	label : "Result",
	sortable : true	
}, {
	key : "partyName",
	label : "Party Name",
	sortable : true	
}, {
	key : "electionYear",
	label : "Election Year",
	sortable : true	
} ];
var myDataTable = new YAHOO.widget.DataTable("candidateInfoDiv",resultsColumnDefs, resultsDataSource);  
}

function callAjax(param,jsObj,url){
	var myResults;	
	var  callback = {			
	    success : function( o ) {
			try {												
					myResults = YAHOO.lang.JSON.parse(o.responseText);		
					if(jsObj.task == "getStates")
					{
						showStatesInSelectOption(myResults)
					}	
					if(jsObj.task == "getDistricts")
					{
						showDistrictsInSelectOption(myResults)
					}
					if(jsObj.task == "getConstituencies")
					{
						showConstituenciesInSelectOption(myResults)
					}					
					if(jsObj.task == "getCandidateDetails")
					{
						showCandidateDetails(myResults)
					}		
			}catch (e) {   		
			   	alert("Invalid JSON result" + e);   
			}  
	    },
	    scope : this,
	    failure : function( o ) {
	     			//alert( "Failed to load result" + o.status + " " + o.statusText);
	              }
	    };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
}
function showStatesInSelectOption(results) 
{
	var selectedElmt = document.getElementById("stateId");
	for(var i in results.getAllStates)
	{			
		var opElmt=document.createElement('option');
		opElmt.value=results.getAllStates[i].id;
		opElmt.text=results.getAllStates[i].name;
	
	try
	{
		selectedElmt.add(opElmt,null); // standards compliant
	}
	catch(ex)
	{
		selectedElmt.add(opElmt); // IE only
	}			
	}
}

function showDistrictsInSelectOption(results)
{
	var selectedElmt = document.getElementById("districtField");
	for(var i in results)
	{			
		var opElmt=document.createElement('option');
		opElmt.value=results[i].id;
		opElmt.text=results[i].name;
	
	try
	{
		selectedElmt.add(opElmt,null); // standards compliant
	}
	catch(ex)
	{
		selectedElmt.add(opElmt); // IE only
	}			
	}
}

function showConstituenciesInSelectOption(results)
{
	var selectedElmt = document.getElementById("constituencyField");
	for(var i in results.latestConstituencies)
	{			
		var opElmt=document.createElement('option');
		opElmt.value=results.latestConstituencies[i].id;
		opElmt.text=results.latestConstituencies[i].name;
	
	try
	{
		selectedElmt.add(opElmt,null); // standards compliant
	}
	catch(ex)
	{
		selectedElmt.add(opElmt); // IE only
	}			
	}
}

function showCandidateDetails(results)
{
	assignToCandidateArray = new Array();

	for(var i in results.candidateDetails)
	{
		var problemObj= {
				candidateName:results.candidateDetails[i].candidateName,
				constituencyName:results.candidateDetails[i].constituencyName,
				votesEarned:results.candidateDetails[i].votesEarned,
				result:results.candidateDetails[i].result,						
				partyName:results.candidateDetails[i].partyName,
				electionYear:results.candidateDetails[i].electionYear					
			};
		
		assignToCandidateArray.push(problemObj);
		candidateDetails.candidateDetailsArray=assignToCandidateArray;	
	}
	var emptyArr = new Array();
    if(results.length == 0)
	{	
    	problemDetails.problemHistoryArray = emptyArr;				
	}
    initializeResultsTable();
}
function getConstituency(id)
{	
		var jsObj=
			{
					locationId:id,
					task:"getConstituencies",
					taskType:type						
			};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/constituencyElectionReportAjaxAction.action?"+rparam;						
			callAjax(rparam,jsObj,url);
}

function getDistricts(id)
{	
	var jsObj=
			{
					locationId:id,
					task:"getDistricts"				
			};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/getDistrictsAndConstituenciesAjaxAction.action?"+rparam;						
			callAjax(rparam,jsObj,url);
}
function getStatesForCountry()
{
	var jsObj=
	{
			task:"getStates",
			taskType:type						
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/constituencyElectionReportAjaxAction.action?"+rparam;						
	callAjax(rparam,jsObj,url);
}

function getDetails(id)
{
	var jsObj=
	{
			locationId:id,
			task:"getCandidateDetails",
			taskType:type						
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/constituencyElectionReportAjaxAction.action?"+rparam;						
	callAjax(rparam,jsObj,url);
}

function getParliamentConstituencies()
{
	
	getStatesForCountry();
	var dataTable;
	dataTable = document.getElementById("constituenciesDiv");
	dataTable.innerHTML = "";
	var assembly;
	assembly = document.getElementById("buildId");
	var hr='';
	hr+='<br/><br/>';
	hr+='<table><tr>';
	hr+='<td>';
	hr+='<select id="stateId" class="selectWidth" list="result" theme="simple" listKey="id" listValue="name" onchange="getConstituency(this.value)"/>';
	hr+='</select>';
	hr+='</td>';	
	hr+='<td>';
	hr+='<select id="constituencyField" class="selectWidth" name="constituency"  onchange="getDetails(this.options[this.selectedIndex].value)"/>';
	hr+='</select>';
	hr+='</td>';
	hr+='</tr></table>';
	hr+='<br/><br/>';
	assembly.innerHTML = hr;
}
function getAssemblyConstituencies()
{
	
	getStatesForCountry();
	var dataTable;
	dataTable = document.getElementById("constituenciesDiv");
	dataTable.innerHTML = "";
	var assembly;
	assembly = document.getElementById("buildId");
	var hr='';
	hr+='<br/><br/>';
	hr+='<table><tr>';
	hr+='<td>';
	hr+='<select id="stateId" class="selectWidth" list="result" theme="simple" listKey="id" listValue="name" onchange="getDistricts(this.value)"/>';
	hr+='</select>';
	hr+='</td>';	
	hr+='<td>';
	hr+='<select class="selectWidth" id="districtField" list="result.getDistricts" theme="simple" listKey="id" listValue="name" onchange="getDetails(this.value)"/>';
	hr+='</select>';
	hr+='</td>';
	hr+='</tr></table>';
	hr+='<br/><br/>';
	assembly.innerHTML = hr;
}
function getParliament()
{
	type = "";
	type = "parliament";
	var constituenciesDiv;
	constituenciesDiv = document.getElementById("candidateInfoDiv");
	constituenciesDiv.innerHTML = "";
	var hr='';
	constituenciesDiv.innerHTML = hr;
	getParliamentConstituencies();
} 
function getAssembly()
{
	type = "";
	type = "assembly";
	var constituenciesDiv;
	constituenciesDiv = document.getElementById("candidateInfoDiv");
	constituenciesDiv.innerHTML = "";
	var hr='';
	constituenciesDiv.innerHTML = hr;
	getAssemblyConstituencies();
} 
</script>
</head>

<body>
<div class="yui-skin-sam">
	<div class="problemHeader">Constituency Election Report</div>
	<br/><br/>
	<table align="left" style="margin-left:1.5cm">
		<tr>
			<td><input  type="radio" id="assemblyElection" name="electionType" value="parliament" onClick="getParliament()"/>Parliament</td>		
			<td><input  type="radio" id="parliamentElection" name="electionType" value="assembly" onClick="getAssembly()"/>Assembly</td>
		</tr>
	</table>
	<br/>
	<div id="constituenciesDiv"></div>
	<div id="buildId" align="left" style="margin-left:1.5cm"></div>
	<div id="candidateInfoDiv"></div>
</div>
</body>
</html>