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
var selectedRadioElmt;
var selectedStateElmt;
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
						showStatesInSelectOption(myResults);
					}	
					if(jsObj.task == "getDistricts")
					{
						showDistrictsInSelectOption(myResults);
					}
					if(jsObj.task == "getConstituencies")
					{
						showParlimentConstituenciesInSelectOption(myResults);
					}
					if(jsObj.task == "getConstituenciesForDistrict" || jsObj.task == "getAssemblyConstituencysForAState")
					{
						showConstituenciesInSelectOption(myResults);
					}				
					if(jsObj.task == "getCandidateDetails")
					{
						showCandidateDetails(myResults);
					}		
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

function showStatesInSelectOption(results) 
{
	var selectedElmt = document.getElementById("statesPopulationDiv");

	var districtId = document.getElementById("districtPopulationDiv");
	if(districtId)
		districtId.style.display = 'none';

	var constituenciesPopulationId = document.getElementById("constituenciesPopulationDiv");
	if(constituenciesPopulationId)
		constituenciesPopulationId.style.display = 'none';

	var candidateInfoDiv = document.getElementById("candidateInfoDiv");
	if(candidateInfoDiv)
		candidateInfoDiv.style.display = 'none';
	
	var data = results.getAllStates;
	var str='';
	if(selectedRadioElmt=="assembly"){
		str+='<select id="statesInIndiaId" style="width:130px;" onChange="getDistricts(this.options[this.selectedIndex].value,\''+selectedRadioElmt+'\')">';
	}else{
		str+='<select id="statesInIndiaId" style="width:130px;" onChange="getConstituency(this.options[this.selectedIndex].value,\''+selectedRadioElmt+'\')">';
	}
	
	for(var i in data)
	{
		str+='<option value="'+data[i].id+'">'+data[i].name+'</option>';
	}
	str+='</select>';
	selectedElmt.innerHTML = str;
	
}

function showDistrictsInSelectOption(results)
{	
	if(results.length>1){	

		var districtId = document.getElementById("districtPopulationDiv");
		if(districtId)
			districtId.style.display = 'block';

	/*	var constituenciesPopulationId = document.getElementById("constituenciesPopulationDiv");
		if(constituenciesPopulationId)
			constituenciesPopulationId.style.display = 'block';

		var candidateInfoDiv = document.getElementById("candidateInfoDiv");
		if(candidateInfoDiv)
			candidateInfoDiv.style.display = 'block';
*/
		
		var selectedElmt = document.getElementById("districtPopulationDiv");
		
		var str='';
		str+='<select id="districtForAStateId" style="width:130px;" onChange="getConstituencysForADistrict(this.options[this.selectedIndex].value,\''+selectedRadioElmt+'\')">';
		for(var i in results)
		{
			str+='<option value="'+results[i].id+'">'+results[i].name+'</option>';
		}
		str+='</select>';
		selectedElmt.innerHTML = str;
		
		var messageDiv = document.getElementById("messageDiv");
		if(messageDiv)
			messageDiv.style.display = 'none';
		
	}else{		
		var districtId = document.getElementById("districtPopulationDiv");
		if(districtId)
			districtId.style.display = 'none';

		var constituenciesPopulationId = document.getElementById("constituenciesPopulationDiv");
		if(constituenciesPopulationId)
			constituenciesPopulationId.style.display = 'none';

		var candidateInfoDiv = document.getElementById("candidateInfoDiv");
		if(candidateInfoDiv)
			candidateInfoDiv.style.display = 'none';

		var messageDiv = document.getElementById("messageDiv");
		if(messageDiv)
			messageDiv.style.display = 'block';
		
		var messageDiv = document.getElementById("messageDiv");
		var messageDiv1='';
		messageDiv1+='<b style="color:red;font-weight:bold;"> There were no districts mapped to this state<b>';
		messageDiv.innerHTML = messageDiv1;
		
	}
}

function showParlimentConstituenciesInSelectOption(results)
{

	var constituenciesPopulationDiv = document.getElementById("constituenciesPopulationDiv");
	if(constituenciesPopulationDiv)
		constituenciesPopulationDiv.style.display = 'block';
	
	var districtId = document.getElementById("districtPopulationDiv");
	if(districtId)
		districtId.style.display = 'none';
	
	var candidateInfoDiv = document.getElementById("candidateInfoDiv");
	if(candidateInfoDiv)
		candidateInfoDiv.style.display = 'none';
	
	var selectedElmt = document.getElementById("constituenciesPopulationDiv");
	var data = results.latestConstituencies;
	var str=''; 		
	str+='<select id="latestConstituenciesIndiaId" style="width:130px;"  onChange="getDetails(this.options[this.selectedIndex].value)">';
	for(var i in data)
	{
		str+='<option value="'+data[i].id+'">'+data[i].name+'</option>';
	}
	str+='</select>';
	selectedElmt.innerHTML = str;
}

function showConstituenciesInSelectOption(results)
{
	var constituenciesPopulationId = document.getElementById("constituenciesPopulationDiv");
	if(constituenciesPopulationId)
		constituenciesPopulationId.style.display = 'block';

	var selectedElmt = document.getElementById("constituenciesPopulationDiv");
	var data = results.latestConstituencies;
	var str=''; 		
	str+='<select id="latestConstituenciesIndiaId" style="width:130px;"  onChange="getDetails(this.options[this.selectedIndex].value)">';
	for(var i in data)
	{
		str+='<option value="'+data[i].id+'">'+data[i].name+'</option>';
	}
	str+='</select>';
	selectedElmt.innerHTML = str;
}

function showCandidateDetails(results)
{

	var candidateInfoDiv = document.getElementById("candidateInfoDiv");
	if(candidateInfoDiv)
		candidateInfoDiv.style.display = 'block';
	
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
function getConstituency(id,eleType)
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

function getAssemblyConstituencysForAState(id)
{	
		var jsObj=
			{
					locationId:id,
					task:"getAssemblyConstituencysForAState",
					taskType:type						
			};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/constituencyElectionReportAjaxAction.action?"+rparam;						
			callAjax(rparam,jsObj,url);
}

function getConstituencysForADistrict(id)
{	
		var jsObj=
			{
					locationId:id,
					task:"getConstituenciesForDistrict",
					taskType:type						
			};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/constituencyElectionReportAjaxAction.action?"+rparam;						
			callAjax(rparam,jsObj,url);
}

function getDistricts(id)
{	
	selectedStateElmt = id;
	var jsObj=
			{
					locationId:id,
					task:"getDistricts"				
			};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/getDistrictsAndConstituenciesAjaxAction.action?"+rparam;						
			callAjax(rparam,jsObj,url);
}
function getStatesForCountry(selectedButton)
{
	selectedRadioElmt = selectedButton.value;
	var jsObj=
	{
			task:"getStates"						
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
window.history.forward(1);

</script>
</head>

<body>

<div class="problemHeader" style="margin-top:60px;">Constituency Election Report</div>
	
 <div class="yui-skin-sam" style="margin-left:0px;text-align:center;">	
	<table>
		<tr>
			<td>
				<table align="left" style="margin-left:1.5cm">
					<tr>
						<td><input  type="radio" id="assemblyElection" name="electionType" value="parliament" onClick="getStatesForCountry(this)"/>Parliament</td>		
						<td><input  type="radio" id="parliamentElection" name="electionType" value="assembly" onClick="getStatesForCountry(this)"/>Assembly</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table align="left" style="margin-left:1.5cm">
					<tr>
						<td><div id="statesPopulationDiv"></div></td>
						<td><div id="districtPopulationDiv"></div></td>		
						<td><div id="constituenciesPopulationDiv"></div></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
					<div id="messageDiv"></div>
			</td>
		</tr>
	</table>
	
	
	<div id="constituenciesDiv"></div>
	<div id="buildId" align="left" style="margin-left:1.5cm"></div>
	<div id="candidateInfoDiv" style="margin-left:70px;margin-top:40px;"></div>
</div>
</body>
</html>