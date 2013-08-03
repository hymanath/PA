<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title> Party Analyst - Suggestive Model</title>
 <script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
 <script type="text/javascript" src="js/jqueryDataTable/jquery.dataTables.js"></script>
 <!--<script type="text/javascript" src="js/jqueryDataTable/jquery.dataTables.min.js"></script>-->
  <!--<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>-->
<link rel="stylesheet" href="/resources/demos/style.css" />
<!--<link rel="stylesheet" href="styles/jqueryDataTable/css/datatable.css" />-->
<link rel="stylesheet" href="styles/jQ_datatables/css/demo_page.css" />
<link rel="stylesheet" href="styles/jQ_datatables/css/demo_table.css" />
<link rel="stylesheet" type="text/css" href="styles/userProfile/userProfilePage.css">
 <script type="text/javascript" src="http://www.google.com/jsapi"></script>
 <script type="text/javascript" src="js/googleAnalytics/googleChartsColourPicker.js"></script>

<!-- YUI Dependency files (Start) -->
	<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/button-min.js"></script> 	
	<script src="js/yahoo/resize-min.js"></script> 
	<script src="js/yahoo/layout-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
	<script type="text/javascript" src="js/json/json-min.js"></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/tabview-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/calendar-min.js"></script>
	<!-- Skin CSS files resize.css must load before layout.css --> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css"> 
 	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css">
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/calendar.css"> 
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">    
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css"> 
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">	

	<!-- YUI Dependency files (End) -->


<script type="text/javaScript" >
google.load("visualization", "1", {packages:["corechart"]});
</script>
<style type="text/css">	
	select {
    background-color: #FFFFFF;
    border: 1px solid #CCCCCC;
    width: 180px;
	}
	select, textarea, input[type="text"], input[type="password"], input[type="datetime"], input[type="datetime-local"], input[type="date"], input[type="month"], input[type="time"], input[type="week"], input[type="number"], input[type="email"], input[type="url"], input[type="search"], input[type="tel"], input[type="color"], .uneditable-input{
		border-radius: 4px 4px 4px 4px;
		color: #000000;
		display: inline-block;
		font-size: 13px;
		line-height: 18px;
		padding: 4px;
	}
	input, button, select {
		font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
		 border: 1px solid lightBlue;
	}
	.tdWidth1,#tdWidth{
	width: 150px;
	}

	
	#requiredValue{
	color:red;
	font-size:large;
	}	
	/*#mainDiv{
	font-family: serif verdana sans-serif;
	border: 1px lightBlue solid ;
	width: 920px; 
	margin-left: 15px;
	margin-bottom: 15px;
	background-color:white;
	font-weight:bold;
	font-size:15px;
	border-radius: 5px 5px 5px 5px;
	}	

	#tableRowS,#tableRowC,#tableRowM,#tableRowP,#tableRowB{
	margin-left: -10px;
	}

	#titleHeading{
	font-family: verdana sans-serif serif;
	margin-top: 30px;
	height: 25px;
	width: 920px; 
	margin-left: 15px;
	color:white;
	background:none repeat scroll 0 0 #06ABEA;
	margin-top: 30px; 
	margin-bottom: 5px;
	font-weight:bold;
	font-size:18px;
	border-radius: 5px 5px 5px 5px;
	}
	#errorMsgDiv{
	color: red; 
	float: left; 
	font-weight:normal;
	font-size:15px;
	width: 730px;
	height: 25px;
	padding-top: 5px;
	}*/
/*#partyPerformanceInnerDiv table th,#weakPollingPercentageDiv table th,#strongPollingPercentageDiv table th{
    background-color: #CDE6FC;
    color: #333333;
    font-size: 13px;
    font-weight: bold;
    padding: 10px;
    text-align: left;
}
#partyPerformanceInnerDiv table td,#weakPollingPercentageDiv table td,#strongPollingPercentageDiv table td{
    color: #676A67;
    font: small-caption;
    padding: 8px 8px 8px 10px;
}*/
/* #partyPerformanceMainDiv{float: none;
    margin-left: auto;
    margin-right: auto;
    width: 960px;}
#partyPerformanceBtnDiv{float: none;
    margin-left: auto;
    margin-right: auto;
    width: 200px;} */



#suggestiveMainDiv{float: none;
    margin-left: auto;
    margin-right: auto;
    width: 990px;margin-top:20px;margin-bottom:20px;}
th {
    background: none repeat scroll 0 0 #D9EDF7;
    color: #454545;
}

.headingCls{ color: #005580;
    font-size: 15px;
    margin-bottom: 10px;}

.fromDiv,.toDiv{float:left;margin:10px;}
.inputDiv{float:right;margin-left:10px;}
#errorMsg{color:red;}
.table thead th{text-align:center;}
.ageGroupTable .table th, .table td{text-align:center;}

#tableageGroupTableId1,#tableageGroupTableId2,#tableageGroupTableId3{border:1px solid #ccc;}
#ageGroupTableId1,#ageGroupTableId2,#ageGroupTableId3,#ageGroupBoothTableId1,ageGroupBoothTableId2,ageGroupBoothTableId3{clear:both;}
.table-bordered th, .table-bordered td{font-family:verdana;}
.table th, .table td{font-family:verdana;}
.table thead th{vertical-align:middle;}
tr.even td.sorting_1{background-color:#ffffff;}

#titleageGroupBoothTableId1,#titleageGroupTableId1,#titleageGroupBoothTableId2,#titleageGroupTableId2,#titleageGroupBoothTableId3,#titleageGroupTableId3{clear:both;padding:15px;background:#9CE7FC;border:1px solid #cccccc;display:none;margin-top:80px;color:#000000;}

#panchayatWisePollingPercentageDiv{padding-top: 9px; padding-bottom: 21px;}

html{overflow-x: hidden;}

</style>


<script type="text/javascript" >
$(document).ready(function(){
getConstituencyList();
});

function getConstituencyList(){

var jsObj= 
	{	
		task:"getConstituencies"		
	};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/getConstituenciesByPartyNYearAction.action?"+param;
	callAjax(param,jsObj,url);

}
/*
function getMandals(){
	var value =  $("#listConstituencyNames option:selected").val();
	var list = document.getElementById("listMandalNames");
	var partyElmts = document.getElementById('partySelectEl')
	var electionyrElmt1 = document.getElementById('electionYearSelectEl1')
	var electionyrElmt2 = document.getElementById('electionYearSelectEl2')
	$("#listConstituencyNames").css("border","1px solid lightBlue");
	removeSelectElements(partyElmts);
	removeSelectElements(electionyrElmt1);
	removeSelectElements(electionyrElmt2);
	removeSelectElements(list);
	addDefaultSelectValues(partyElmts);
	addDefaultSelectValues(electionyrElmt2);
	addDefaultSelectValues(electionyrElmt1);
	addDefaultSelectValues(list);
	$('#errorMsgDiv').html('');
		if(value == 0){
		$("#listConstituencyNames").css("border","1px solid IndianRed");
		$('#errorMsgDiv').html('Please Select Constituency');
		return;
		}
		var jsObj=
			{
					
					selected:value,
					selectElmt:"mandalField",
					str   : "all",
					task:"getMandalList"
			};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "voterAnalysisAjaxAction.action?"+rparam;						
		callAjax(rparam,jsObj,url)
	} */
function getPartyDetails(mandalId){
	
	var list = document.getElementById("partySelectEl");
	var electionyrElmt1 = document.getElementById('electionYearSelectEl1');
	var electionyrElmt2 = document.getElementById('electionYearSelectEl2');
	$("#listMandalNames").css("border","1px solid lightBlue");
	removeSelectElements(electionyrElmt1);
	removeSelectElements(electionyrElmt2);
	removeSelectElements(document.getElementById("candidateCastes"));
	removeSelectElements(list);
	
	addDefaultSelectValues(electionyrElmt2);
	addDefaultSelectValues(electionyrElmt1);
	addDefaultSelectValues(list);
	$('#errorMsgDiv').html('');
	if(mandalId == 0){
		$("#listMandalNames").css("border","1px solid IndianRed");
		$('#errorMsgDiv').html('Please Select Mandal');
		return;
	}
	var jsObj = 
	{
	mandalId : mandalId.slice(1),
	task:"getPartyDetails"
	}	
	var param = "task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getPartyForSuggestiveAction.action?"+param;
	callAjax(param,jsObj,url);

}

	function getElectionYears(id){
	$('#errorMsgDiv').html('');
	var electionyrElmt1 = document.getElementById('electionYearSelectEl1');
	var electionyrElmt2 = document.getElementById('electionYearSelectEl2');
	$("#partySelectEl").css("border","1px solid lightBlue");
	removeSelectElements(electionyrElmt1);
	removeSelectElements(electionyrElmt2);
	addDefaultSelectValues(electionyrElmt2);
	addDefaultSelectValues(electionyrElmt1);
		var constituencyId = $('#listConstituencyNames').val();
		if(id == 0){
		$("#partySelectEl").css("border","1px solid IndianRed");
		$('#errorMsgDiv').html('Please Select Party Name');
		return;
		}

		var jsObj=
			{
				electionScopeId:2,
				partyId:id,
				constituencyId:constituencyId,
				task:"getElectionYears"						
			};
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/getElectionsYearsForParties.action?"+rparam;						
			callAjax(rparam,jsObj,url);
	}
function validateYear1(yearId){
	$('#errorMsgDiv').html('');
		$("#electionYearSelectEl1").css("border","1px solid lightBlue");
	if(yearId == 0){
		$("#electionYearSelectEl1").css("border","1px solid IndianRed");
		$('#errorMsgDiv').html('Please Select Election Years');
		return;
	}
	
}

function validateYear2(yearId){
	$('#errorMsgDiv').html('');
		$("#electionYearSelectEl2").css("border","1px solid lightBlue");
	if(yearId == 0){
		$("#electionYearSelectEl2").css("border","1px solid IndianRed");
		$('#errorMsgDiv').html('Please Select Election Years');
		return;
	}	
}
function getConstituencyType()
	{		
		var constituencyId = $("#listConstituencyNames option:selected").val();

		var jsObj=
				{
					constituencyId : constituencyId,
					publicationId  : 8,
					task           : "getConstituencyType"
				}
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/getReportLevelDetails.action?"+rparam;	

		callAjax(rparam,jsObj,url);
}
function getLeadersList(){
//var mandalId = $('#listMandalNames option:selected').val();
var constituencyId = $('#listConstituencyNames option:selected').val();
var jsObj= 
	{	
		//mandalId       : mandalId.slice(1),
		constituencyId : constituencyId,
		task           : "getLeadersList"		
	};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/getLeadersDataAction.action?"+param;
	callAjax(param,jsObj,url);

}

function getLeadersListInRuralUrbans(){
var constituencyId = $('#listConstituencyNames option:selected').val();
var jsObj= 
	{	
		constituencyId : constituencyId,
		task           : "getLeadersListInRuralUrbans"		
	};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/getLeadersListInRuralUrbansAction.action?"+param;
	callAjax(param,jsObj,url);
}

function getCandidateCastes(constituencyIds){
	var constituencyId = $('#listConstituencyNames').val();
	var jsObj ={
		constituencyId : constituencyId,
		task : "getUserAssignedVoterCastes"
		};
	var rparam="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url="getUserAssignedVoterCastesAction.action?"+rparam;
	callAjax(rparam,jsObj,url);
}
function callAjax(param,jsObj,url){
	var myResults;					
		var callback = {			
				success : function( o ) {
					try 
					{												
						if(o.responseText)
							myResults = YAHOO.lang.JSON.parse(o.responseText);
						
						if(jsObj.task == "getElectionYears")
						{
							populateElectionYearDropdown(myResults);
						}
						else if(jsObj.task == "getPartyDetails")
						{
							populatePartiesDropdown(myResults);
						}								
						else if(jsObj.task == "getConstituencies")
						{	
							populateConstituencyListDropdown(myResults);
						}
						else if(jsObj.task == "getMandalList")
						{
							buildMandalList(myResults);
						}
						else if(jsObj.task == "getPartyPerformanceReport")
						{
						    $("#ajaxImg").css("display","none");
					        showPartyPerformanceReport(myResults,jsObj);
							showStrongAndWeakPollingPercentage(myResults,jsObj);
							buildAddedVotersDetails(myResults);
							showPartyPerformancePieChart(myResults,jsObj);
							//showSuggestedLocations(myResults,jsObj);
						}
						else if(jsObj.task == "getLeadersList")
						{
							buildLeadersTable(myResults);
						}
						else if(jsObj.task == "getDeletedVotersInfo")
						{
							buildDeletedVotersInfo(myResults);
						}
						else if(jsObj.task == "getAgeGroupWiseReport"){
							$('#ajaxLoaderImg').css('display','none');
							buildAgeGroupWiseTable(myResults,jsObj);
						}
						else if(jsObj.task== "getUserAssignedVoterCastes"){
							buildUserAssignedVotersCastes(myResults);
						}
						else if(jsObj.task== "getConstituencyType"){
							//console.log(myResults[0].name);
							if(myResults[0].name == "RURAL-URBAN")
								getLeadersListInRuralUrbans();
						}
						else if (jsObj.task== "getLeadersListInRuralUrbans"){
							buildLeadersTableForNONUrbanAreas(myResults);					
						}
					}catch (e){
					//alert("Invalid JSON result" + e);   
					}  
				},
			       scope : this,
			       failure : function( o ) {
			        			//alert( "Failed to load result" + o.status + " " + o.statusText);
			        }
			    };
		YAHOO.util.Connect.asyncRequest('GET', url, callback);
	}	
function buildUserAssignedVotersCastes(results){
	var candidateCastesEl = document.getElementById("candidateCastes");
	removeSelectElements(candidateCastesEl);
	
	for(var i in results)
	{
		var opElmt=document.createElement('option');
		opElmt.value=results[i].id;
		opElmt.text=results[i].name;
		addOptions(candidateCastesEl,opElmt);	
	}	
}
function populatePartiesDropdown(results)
{
	var partySelectEl = document.getElementById("partySelectEl");
	removeSelectElements(partySelectEl);
	var opElmt=document.createElement('option');
		opElmt.value='0';
		opElmt.text='Select Party';
		addOptions(partySelectEl,opElmt);
	for(var i in results)
	{
		var opElmt=document.createElement('option');
		opElmt.value=results[i].id;
		opElmt.text=results[i].name;
		addOptions(partySelectEl,opElmt);	
	}	
}

function populateElectionYearDropdown(results)
{
	var electionYearsEl1 = document.getElementById("electionYearSelectEl1");
	var electionYearsEl2 = document.getElementById("electionYearSelectEl2");

		removeSelectElements(electionYearsEl1);
		removeSelectElements(electionYearsEl2);
	var opElmt1=document.createElement('option');
	var opElmt2=document.createElement('option');
		opElmt1.value='0';
		opElmt2.value='0';
		opElmt1.text='Select Party';
		opElmt2.text='Select Party';
		addOptions(electionYearsEl1,opElmt1);
		addOptions(electionYearsEl2,opElmt2);
	if(results!=null)
		for(var i in results)
		{	
			var opElmt1=document.createElement('option');
			var opElmt2=document.createElement('option');
			opElmt1.value=results[i].id;
			opElmt1.text=results[i].name;
			opElmt2.value=results[i].id;
			opElmt2.text=results[i].name;
			addOptions(electionYearsEl1,opElmt1);
			addOptions(electionYearsEl2,opElmt2);
		}
}
function buildMandalList(results){

var list = document.getElementById("listMandalNames");
	removeSelectElements(list); 
	if(results!=null)
		for(var i in results)
		{
			var opElmt=document.createElement('option');
			opElmt.value=results[i].id;
			opElmt.text=results[i].name;
			addOptions(list,opElmt);			
		}	
}
function populateConstituencyListDropdown(results){

	var list = document.getElementById("listConstituencyNames");
	removeSelectElements(list);
	if(results!=null)
		for(var i in results)
		{
			var opElmt=document.createElement('option');
			opElmt.value=results[i].id;
			opElmt.text=results[i].name;
			addOptions(list,opElmt);			
		}	
}

function addOptions(list,opElmt){
	try
		{
		list.add(opElmt,null); // standards compliant
		}
	catch(ex)
		{
		list.add(opElmt); // IE only
		}
}

function removeSelectElements(elmt)
{
	if(!elmt)
		return;

	var len=elmt.length;			
	for(i=len-1;i>=0;i--)
	{
		elmt.remove(i);
	}	

}

function addDefaultSelectValues(elmt){
	var opElmt=document.createElement('option');
		opElmt.value='0';
		opElmt.text='Select ';	
	addOptions(elmt,opElmt);
}

function buildLeadersTable(results)
{
	if(results != null && results.length > 0)
	{
		var constituencyName = $('#listConstituencyNames option:selected').text().toUpperCase();
		var str = "";
		str+='<div class="widget blue">';
		str+='<div style="margin-top: 0px; clear: both; display: block; padding-bottom:1px;" class="widget-block">';
		str+='<h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;color: black;" class="">'+constituencyName+' CONSTITUENCY BOOTH LEVEL CASTE DETAILS </h4>';
		//str+='<h4  style="border-radius: 4px 4px 4px 4px; margin-top: 10px; padding-bottom: 10px; margin-bottom: 10px; padding-top: 10px; color: white; background-color: rgb(6, 171, 234); height: 22px;"></h4>';
		str += '<table class="table table-hover table-bordered" style="font-size: 12px; font-family: verdana; color: black; font-weight: lighter; margin-top: 15px;">';
		str += '<tr>';
		str += '<th>Mandal</th>';
		str += '<th>Panchayat</th>';
		str += '<th>Total Voters</th>';
		str += '<th>Major Castes</th>';
		str += '<th>Booth</th>';
		str += '<th>Total Voters</th>';
		str += '<th>Major Castes</th>';
		str += '</tr>';
		for(var i in results)
		{
			str += '<tr>';
			var rowLength = results[i].boothLevelLeadersList.length;
			str += '<td rowspan='+rowLength+'>'+results[i].mandalName+'</td>'; 
			str += '<td rowspan='+rowLength+'>'+results[i].panchayatName+'</td>'; 
			str += '<td rowspan='+rowLength+'>'+results[i].panchayatTotalVoters+'</td>'; 
			str += '<td rowspan='+rowLength+' >';
			for(var j in results[i].panchayatLevelLeadersList)
			{
				str += ''+results[i].panchayatLevelLeadersList[j].casteName +'('+results[i].panchayatLevelLeadersList[j].casteVotersPerc+')  '; 
			}
			str += '</td>';
			
			for(var k in results[i].boothLevelLeadersList)
			{
			
				if(k > 0)
				{
					str += '<tr>';
				}
				str += '<td>'+results[i].boothLevelLeadersList[k].boothName+'</td>'; 
				str += '<td>'+results[i].boothLevelLeadersList[k].boothTotalVoters+'</td>';
				str += '<td>';
				for(var m in results[i].boothLevelLeadersList[k].boothLevelLeadersList)
				{
					str += ''+results[i].boothLevelLeadersList[k].boothLevelLeadersList[m].casteName+'('+results[i].boothLevelLeadersList[k].boothLevelLeadersList[m].casteVotersPerc+')  '; 
				}
				str += '</td>';
				if(k > 0)
				{
					str += '</tr>';
				}
			}
			str += '</tr>';
		}
		str += '</table>';
		str += '</div>';
		str += '</div>';
		$('#leadersTable').html(str);
	}
	
}

function buildLeadersTableForNONUrbanAreas(results){

if(results != null && results.length > 0)
	{
		var constituencyName = $('#listConstituencyNames option:selected').text().toUpperCase();
		var str = "";
		str+='<div class="widget blue">';
		str+='<div style="margin-top: 0px; clear: both; display: block; padding-bottom:1px;" class="widget-block">';
		str+='<h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;color: black;" class="">'+constituencyName+' MUNCIPALITY BOOTH LEVEL CASTE DETAILS </h4>';
		//str+='<h4  style="border-radius: 4px 4px 4px 4px; margin-top: 10px; padding-bottom: 10px; margin-bottom: 10px; padding-top: 10px; color: white; background-color: rgb(6, 171, 234); height: 22px;"></h4>';
		str += '<table class="table table-hover table-bordered" style="font-size: 12px; font-family: verdana; color: black; font-weight: lighter; margin-top: 15px;">';
		str += '<tr>';
		str += '<th>Mandal</th>';
		str += '<th>Total Voters</th>';
		str += '<th>Major Castes</th>';
		str += '<th>Booth</th>';
		str += '<th>Total Voters</th>';
		str += '<th>Major Castes</th>';
		str += '</tr>';
		for(var i in results)
		{
			str += '<tr>';
			var rowLength = results[i].boothLevelLeadersList.length;
			str += '<td rowspan='+rowLength+'>'+results[i].mandalName+' Muncipality </td>'; 
			str += '<td rowspan='+rowLength+'>'+results[i].boothTotalVoters+'</td>'; 
			str += '<td rowspan='+rowLength+' >';
			for(var j in results[i].panchayatLevelLeadersList)
			{
				str += ''+results[i].panchayatLevelLeadersList[j].casteName +'('+results[i].panchayatLevelLeadersList[j].casteVotersPerc+')  '; 
			}
			str += '</td>';
			
			for(var k in results[i].boothLevelLeadersList)
			{
			
				if(k > 0)
				{
					str += '<tr>';
				}
				str += '<td>'+results[i].boothLevelLeadersList[k].boothName+'</td>'; 
				str += '<td>'+results[i].boothLevelLeadersList[k].boothTotalVoters+'</td>';
				str += '<td>';
				for(var m in results[i].boothLevelLeadersList[k].boothLevelLeadersList)
				{
					str += ''+results[i].boothLevelLeadersList[k].boothLevelLeadersList[m].casteName+'('+results[i].boothLevelLeadersList[k].boothLevelLeadersList[m].casteVotersPerc+')  '; 
				}
				str += '</td>';
				if(k > 0)
				{
					str += '</tr>';
				}
			}
			str += '</tr>';
		}
		str += '</table>';
		str += '</div>';
		str += '</div>';
		$('#leadersTable1').html(str);
	}


}
function showSuggestedLocations(myResults,jsObj){
 var str ='';
 if(myResults != null && myResults.length > 0 && myResults[0].suggestedLocations != null && myResults[0].suggestedLocations.length > 0){
	str+='<div class="widget blue">';
	str+='<div style="margin-top: 0px; clear: both; display: block; padding-bottom:1px;" class="widget-block">';
	str+='<h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;color: black;" class="">Order OF Priority to Target Geographically </h4>';
    //str+='<h4  style="border-radius: 4px 4px 4px 4px; margin-top: 10px; padding-bottom: 10px; margin-bottom: 10px; padding-top: 10px; color: white; background-color: rgb(6, 171, 234); height: 22px;">Order OF Priority to Target Geographically </h4>';
	str+='<table  class="table table-bordered table-striped table-hover" style="font-size: 12px; font-family: verdana; color: black; font-weight: lighter; margin-top: 15px;">';
	str+='  <tr>';
	str+='    <th>Panchayat Name</th>';
	str+='  </tr>';
	 for(var i in myResults[0].suggestedLocations){
	   str+='<tr>';
	   str+='  <td>'+myResults[0].suggestedLocations[i].name+'</td>';
	   str+='</tr>';
	 }
	str+='</table>';
	str+= '</div>';
	str+= '</div>';
 }
 $("#suggestedLocationsDiv").html(str);
}

</script>
</head>
<body>
<div id="suggestiveMainDiv" align="center">
  <!--<div id="titleHeading" align="center"> SUGGESTIVE MODEL </div>-->
  <div class="widget blue">
  <div style="margin-top: 0px; clear: both; display: block; padding-bottom:1px; height: 450px;" class="widget-block">
  <h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;color: black;" class="">SUGGESTIVE MODEL</h4>
   <div id="mainDiv" align="center" style="margin-left: 100px;">
     <div id="errorMsgDiv" >&nbsp;</div><br><br>
     <div style="width: 500px; float: left;margin-bottom: 5px;">
		<table>
			<tr id="tableRowS">
				<td id="tdWidth">
					Constituency Name :<font id="requiredValue" class="requiredFont">*</font> 
				</td>
				<td>
					<select id="listConstituencyNames" onchange="getPartyDetails(this.options[this.selectedIndex].value),getCandidateCastes(this.options[this.selectedIndex].value);">
					<option value="0"> Select Constituency </option>
					</select>
				</td>		
		<!--	
			<td id="tdWidth">
					Mandal Name :<font id="requiredValue" class="requiredFont">*</font> 
				</td>
				<td>
					<select id="listMandalNames" onchange="getPartyDetails(this.options[this.selectedIndex].value);">
					<option value="0"> Select Mandal </option>
					</select>
				</td>	
		-->
			</tr>
	</table>		
</div>
<br><br>
<div style="width: 500px; float: left;margin-bottom: 5px;">
	<table>
		<tr id="tableRowS">
			<td id="tdWidth">
				Party Name:<font id="requiredValue" class="requiredFont">*</font> 
			</td>
			<td>
				<select id="partySelectEl" onchange="getElectionYears(this.options[this.selectedIndex].value)">
				<option value="0"> Select Party </option>
				</select>
			</td>
			</tr>
</table>
</div>
<br><br>
<div style=" margin-bottom: 5px;float: left; margin-left: 82px;">
<table>
		<tr>
			<td id="tdWidth">
				Election Year :<font id="requiredValue" class="requiredFont">*</font> 
			</td>		
			<td>
				<select id="electionYearSelectEl1" onchange="validateYear1(this.options[this.selectedIndex].value)">
				<option value="0"> Select Year </option>
				</select>
			</td>
			<td Style="padding-left: 15px;">
				<select id="electionYearSelectEl2" onchange="validateYear2(this.options[this.selectedIndex].value)">
				<option value="0"> Select Year </option>
				</select>
			</td>
		</tr>
	</table>
</div>

<div style=" margin-bottom: 5px;float: left; margin-left: 82px;">
	<table>
		<tr>
			<td id="tdWidth">
				Caste Names :<font id="requiredValue" class="requiredFont">*</font> 
			</td>		
			<td>
				<select id="candidateCastes" >
				<option value="0"> Select Caste </option>
				</select>
			</td>			
		</tr>
	</table>
</div>

<div style="margin-left:80px;margin-top:70px;">
<div id="ageGroupWiseId">
	<div style="clear:both;" class="fromToDivTemplateClass fromToDivClass" id="fromToDivId0">
		<div class="pull-left" style="margin:10px;font-weight:bold;">Young Voters -</div><div class="fromDiv">
			From
			<div class="inputDiv"><input type="text" id="fromTxt" class="fromInput" style="width:80px;" value="18"/></div>
		</div>
		<div class="toDiv">
			To
			<div class="inputDiv"><input type="text" id="toTxt" class="toInput" style="width:80px;" value="23"/></div>
		</div>
		<div class="closeImgDiv pull-left" style="margin-top:12px;"><img src="images/close.png" height="25px" width="25px" style="display:none;"/></div>
	</div>
	<div style="clear:both;" class="fromToDivTemplateClass fromToDivClass" id="fromToDivId0">
		<div class="pull-left" style="margin:10px;font-weight:bold;">Old Voters     -</div><div class="fromDiv" style="margin-left:30px;">
			From
			<div class="inputDiv"><input type="text" id="fromTxt" class="fromInput" style="width:80px;" value="60"/></div>
		</div>
		<div class="toDiv">
			To
			<div class="inputDiv"><input type="text" id="toTxt" class="toInput" style="width:80px;" value="120"/></div>
		</div>
		<div class="closeImgDiv pull-left" style="margin-top:12px;"><img src="images/close.png" height="25px" width="25px" style="display:none;"/></div>
	</div>
</div>
</div>
<div style="clear:both;">
	<span id="errorMsg"></span>
</div>


<div id="partyPerformanceBtnDiv" style="margin-bottom: 4px;float: left; width: 980px;">
<input type="button" id="getPartyPer" value="Submit" class="btn btn-success" style="margin-bottom: 10px; margin-top: 10px;" onclick="getLeadersList(),getAgeGroupWiseResults(),getConstituencyType();"/>

<img src="images/icons/search.gif" id="ajaxImg" style="display:none;"/>
<img src="images/icons/loading.gif" id="ajaxLoaderImg" height="25px" width="25px;" style="display:none;"/>
</div>

</div>
</div></div>
<div>
<div id="leadersTable"></div>
<div id="leadersTable1"></div>
<div id="suggestedLocationsDiv"></div>
<div id="partyPerformanceMainDiv">
   <div id="partyPerformanceInnerDiv"></div>
</div>
<div id="strongPollingPerDiv" class="row-fluid">
    <div id="strongPollingPercentageDiv" class="span6"></div>
</div>
<div id="weakPollingPerDiv" class="row-fluid">
<div id="weakPollingPercentageDiv" class="span6"></div>
</div>
<div id="addedVotesDib" class="row-fluid">
<div id="addedVoterDetailsDiv" class="span6"></div>
</div>
<div class="widget green" id="panchayatWisePollingPercMainDiv" style="display:none;">
  <div id="panchayatWisePollingPercHeadingDiv"></div>
  <div id="panchayatWisePollingPercentageDiv" class="row-fluid"></div>
</div>
<!--<div id="deletedVotersInfo">

</div>-->

 
</div>

<!--<div style="clear:both;margin-top:10px;">
	<span id="addMoreBtn" class="btn btn-info" >Add More</span>
	<span id="getAgeGroupWiseResults" class="btn btn-info">Get Results</span>
	<span id="ajaxLoaderImg" style="display:none;"><img src="images/icons/loading.gif" height="30px" width="30px;"/></span>
</div>-->

<div style="background:#fff;">
	<div><div id="titleageGroupTableId1" ></div><div id="ageGroupTableId1"  style="margin:20px;"></div></div>
	<div><div id="titleageGroupBoothTableId1"></div><div id="ageGroupBoothTableId1" style="margin-top:10px;"></div></div>
	<div><div id="titleageGroupTableId2"></div><div id="ageGroupTableId2" style="margin-top:10px;"></div></div>
	<div><div id="titleageGroupBoothTableId2"></div><div id="ageGroupBoothTableId2" style="margin-top:10px;"></div></div>
	<div><div id="titleageGroupTableId3"></div><div id="ageGroupTableId3" style="margin-top:10px;"></div></div>
	<div><div id="titleageGroupBoothTableId3"></div><div id="ageGroupBoothTableId3" style="margin-top:10px;"></div></div>
</div>

<script>
$(document).ready(function(){

$("#getPartyPer").click(function(){
	  var constituencyId = $('#listConstituencyNames option:selected').val();
	  var mandalId = $('#listMandalNames option:selected').val();
	  var partyId = $('#partySelectEl option:selected').val();
	  var eleId1 = $('#electionYearSelectEl1 option:selected').val();
	  var eleId2 = $('#electionYearSelectEl2 option:selected').val();
	  var eleIds = new Array();
	  eleIds.push(eleId1);
	  eleIds.push(eleId2);
    $("#ajaxImg").css("display","inline-block");

	var jsObj = {
	        constituencyId:constituencyId,
			electionId:eleIds,
			partyId:partyId,
			locationId:2844,
			locationType:"mandal",
			tempVar:"",
			task:"getPartyPerformanceReport"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "<%=request.getContextPath()%>/getPartyPerformanceAction.action?"+rparam;
		callAjax(rparam,jsObj,url);
		
  });

});

 function showPartyPerformanceReport(result,jsObj)
{
	$("#partyPerformanceInnerDiv").html('');
  if(result == null || result.length == 0)
	{
     $("#partyPerformanceInnerDiv").html('No Data Found');
	 return;
	}
	var partyName = $('#partySelectEl option:selected').text();
	var str = '';
	str+='<div class="widget green">';
	str+='<div style="margin-top: 0px; clear: both; display: block; padding-bottom:1px;" class="widget-block">';
	str+='<h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;color: black;" class="">PANCHAYAT WISE '+partyName+' PARTY PERFORMANCE REPORT</h4>';
	//str +='<h4 style="border-radius: 4px 4px 4px 4px; margin-top: 10px; padding-bottom: 10px; margin-bottom: 10px; padding-top: 10px; color: white; background-color: rgb(6, 171, 234); height: 22px;">PANCHAYAT WISE INC PARTY PERFORMANCE REPORT</h4>';
	str +='<table class="table table-bordered table-striped table-hover" style="font-size: 12px; font-family: verdana; color: black; font-weight: lighter; margin-top: 15px;">';
    str +='<tr>';
	str +='<th style="background: none repeat scroll 0 0 #D9EDF7;color: #454545;">Type</th>';
	for(var i in result)
	 str +='<th style="background: none repeat scroll 0 0 #D9EDF7;color: #454545;">'+result[i].name+'</th>';
	str +='</tr>';
    var length = result[0].partyPositionVOList.length;
	for(var j=0;j<length;j++){
		str +='<tr>';
		str +='<td>'+result[0].partyPositionVOList[j].name+'</td>';
		for(var i in result)
		{
		  str +='<td>'
		  for(var k in result[i].partyPositionVOList[j].partyPositionVOList)
		  {
			var tempVar = result[i].partyPositionVOList[j].partyPositionVOList.length-1;
			str +=''+result[i].partyPositionVOList[j].partyPositionVOList[k].name+'';
		    
			if(k != tempVar)
			 str +=', ';
		  }
		  
		  str +='</td>'
		}
		str +='</tr>';
    }
	str +='</table>';
	str += '</div>';
	str += '</div>';
	$("#partyPerformanceInnerDiv").html(str);

}

/* function showStrongAndWeakPollingPercentage(result,jsObj)
{
  if(result == null || result.length == 0)
  {
     $("#strongPollingPercentageDiv").html('');
	 $("#weakPollingPercentageDiv").html('');
	 return;
  }
  var StrongPollingPerList = result[0].strongPollingPercentVOList;
  var weakPollingPerList = result[0].weakPollingPercentVOList;
  var locationtype = "";
  alert(StrongPollingPerList);
  alert(weakPollingPerList);
  if(jsObj.locationType == "mandal")
   locationtype = "Panchayat";

  else if(jsObj.locationType == "panchayat")
   locationtype = "Booth";


  var str = '';
  var temp = '';
  str +='<h4 class="headingCls">Low Voting % in Strong '+locationtype+'s</h4>'; 
  temp +='<h4 class="headingCls">High Voting % in Weak '+locationtype+'s</h4>'; 

  if(StrongPollingPerList == null || StrongPollingPerList.length == 0)
  {
	$("#strongPollingPercentageDiv").css('display','none');
	$("#strongPollingPercentageDiv").html(''); 
  }
  if(weakPollingPerList == null || weakPollingPerList.length == 0)
  {
    $("#weakPollingPercentageDiv").css('display','none');
	$("#weakPollingPercentageDiv").html(''); 
  }
  
  //Strong
  if(StrongPollingPerList != null && StrongPollingPerList.length > 0)
  {
	
    str+='<table class="table table-bordered table-striped table-hover">';
    str += '<tr>';
    str +='<th>'+locationtype+' Name</th>';
    str +='<th>Party %</th>';
    str += '<th>Polling %</th>';
    str +='</tr>';
    for(var j in weakPollingPerList)
    {
	 str += '<tr>';
	 str += '<td>'+weakPollingPerList[j].name+'</td>';
	 str += '<td>'+weakPollingPerList[j].partyPercentage+'</td>';
	 str += '<td>'+weakPollingPerList[j].pollingPercentage+'</td>';
	 str += '</tr>';
    }

    str+='</table>';
	$("#strongPollingPercentageDiv").html(str); 
   }


  //weak
 if(weakPollingPerList != null && weakPollingPerList.length > 0)
 {
   
   temp+='<table class="table table-bordered table-striped table-hover">';
   temp += '<tr>';
   temp +='<th>'+locationtype+' Name</th>';
   temp +='<th>Party %</th>';
   temp += '<th>Polling %</th>';
   temp +='</tr>';
   for(var j in weakPollingPerList)
   {
	temp += '<tr>';
	temp += '<td>'+weakPollingPerList[j].name+'</td>';
	temp += '<td>'+weakPollingPerList[j].partyPercentage+'</td>';
	temp += '<td>'+weakPollingPerList[j].pollingPercentage+'</td>';
	temp += '</tr>';
   }

   temp+='</table>';
   $("#weakPollingPercentageDiv").html(temp); 
 }

} */
 
function showStrongAndWeakPollingPercentage(result,jsObj)
{
  if(result == null || result.length == 0)
  {
     $("#strongPollingPercentageDiv").html('');
	 $("#weakPollingPercentageDiv").html('');
	 return;
  }
  var StrongPollingPerList = result[0].strongPollingPercentVOList;
 
	var str = '';
  
	str += '<table >';
	var z = 0;
  for(var i in StrongPollingPerList)
  {
	if(z%2 == 0)
	{
		str += '<tr>';
	}
	str+='<td valign="top" style="padding-left:10px;"><div class="widget blue">';
	str+='<div style="margin-top: 0px; clear: both; display: block; padding-bottom:1px;" class="widget-block">';
	str+='<h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;width:450px;color: black;" class="">'+StrongPollingPerList[i].name+ ' AND POLLING % IS LESS</h4>';
	str += '<table class="table table-bordered table-striped table-hover" style="font-size: 12px; font-family: verdana; color: black; font-weight: lighter; margin-top: 15px;">';
	str += '<tr>';
	str += '<th style="background: none repeat scroll 0 0 #D9EDF7;color: #454545;">PANCHAYAT</th>';
	str += '<th style="background: none repeat scroll 0 0 #D9EDF7;color: #454545;">POLLING PERCENTAGE</th>';
	str += '</tr>';
	
	for(var j in StrongPollingPerList[i].partyPositionVOList)
	{
		str += '<tr>';
		str += '<td>'+StrongPollingPerList[i].partyPositionVOList[j].name+'</td>';
		str += '<td>'+StrongPollingPerList[i].partyPositionVOList[j].pollingPercentage+'</td>';
		str += '</tr>';
	}
	
	
	str += '</table>';
	str += '</div>';
	str += '</div></td>';
	if((z-1)%2 == 0)
	{
		str += '</tr>';
	}
	z++;
  }
  if(z%2 == 0)
  {
	str += '</tr>';
  }
  str += '</table>'
  $("#strongPollingPercentageDiv").html(str);
  var weakPollingPerList = result[0].weakPollingPercentVOList;
  var wstr = '';
  wstr += '<table >';
  var v = 0;
  for(var m in weakPollingPerList)
  {
	if(v%2 == 0)
	{
		wstr += '<tr>';
	}
	wstr+='<td valign="top" style="padding-left:10px;"><div class="widget blue">';
	wstr+='<div style="margin-top: 0px; clear: both; display: block; padding-bottom:1px;" class="widget-block">';
	wstr+='<h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;width:450px;color: black;" class="">'+weakPollingPerList[m].name+ ' AND POLLING % IS MORE </h4>';
	//wstr += '<h4 style="border-radius: 4px 4px 4px 4px; margin-top: 10px; padding-bottom: 10px; margin-bottom: 10px; padding-top: 10px; color: white; background-color: rgb(6, 171, 234); height: 22px;">'+weakPollingPerList[m].name+ ' AND POLLING % IS MORE </h4>';
	wstr += '<table class="table table-bordered table-striped table-hover" style="font-size: 12px; font-family: verdana; color: black; font-weight: lighter; margin-top: 15px;">';
	wstr += '<tr>';
	wstr += '<th style="background: none repeat scroll 0 0 #D9EDF7;color: #454545;">PANCHAYAT</th>';
	wstr += '<th style="background: none repeat scroll 0 0 #D9EDF7;color: #454545;">POLLING PERCENTAGE</th>';
	wstr += '</tr>';
	
	for(var n in weakPollingPerList[m].partyPositionVOList)
	{
		wstr += '<tr>';
		wstr += '<td>'+weakPollingPerList[m].partyPositionVOList[n].name+'</td>';
		wstr += '<td>'+weakPollingPerList[m].partyPositionVOList[n].pollingPercentage+'</td>';
		wstr += '</tr>';
	}
	
	
	wstr += '</table>';
	wstr += '</div>';
	wstr += '</div><td>';
	if((v-1)%2 == 0)
	{
		wstr += '</tr>';
	}
	v++;
  }
  if(v%2 == 0)
  {
	wstr += '</tr>';
  }
  wstr += '</table>'
  $("#weakPollingPercentageDiv").html(wstr);
}
function buildAddedVotersDetails(result)
{
	var z = 0;
	var str = "";
	var myResult = new Array();
	var addedVoterDetails = result[0].addedVoterDetails;
	for(var i in addedVoterDetails)
	{
		if(addedVoterDetails[i].addedVotersPresent == true)
		{
			myResult.push(addedVoterDetails[i]);
		}
	}
		if(myResult != null && myResult.length > 0)
		{
			str += '<table >';
			for(var j in myResult)
			{
				if(z%2 == 0)
				{
					str += '<tr>';
				}
				str+='<td valign="top" style="padding-left:10px;"><div class="widget blue">';
				str+='<div style="margin-top: 0px; clear: both; display: block; padding-bottom:1px;" class="widget-block">';
				str+='<h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;width:450px;color: black;" class="">'+myResult[j].name+ ' AND ADDED VOTERS ARE MORE</h4>';
				str += '<table class="table table-bordered table-striped table-hover" style="font-size: 12px; font-family: verdana; color: black; font-weight: lighter; margin-top: 15px;">';
				str += '<tr>';
				str += '<th style="background: none repeat scroll 0 0 #D9EDF7;color: #454545;">PANCHAYAT</th>';
				str += '<th style="background: none repeat scroll 0 0 #D9EDF7;color: #454545;">ADDED VOTERS</th>';
				str += '<tr>';
				for(var m in myResult[j].partyPositionVOList)
				{
					if(myResult[j].partyPositionVOList[m].addedVotersCount != null && myResult[j].partyPositionVOList[m].addedVotersCount > 0)
					{
							str += '<tr>';
							str += '<td>'+myResult[j].partyPositionVOList[m].name+'</td>';
							str += '<td>'+myResult[j].partyPositionVOList[m].addedVotersCount+'</td>';
							str += '</tr>'; 
					}
				}
				str += '</table>';
				str += '</div>';
				str += '</div><td>';
				if((z-1)%2 == 0)
				{
					str += '<tr>';
				}
				z++;
			}
			if(z%2 == 0)
			{ 
				str += '</tr>';
			}
			str += '</table>';
		}
	$('#addedVoterDetailsDiv').html(str);
}
function getDeletedVotersInfo()
{
var panchayats = [];
panchayats.push(1392,1393,1394,1395);
var jsObj = {
			panchayats:panchayats,
			task:"getDeletedVotersInfo"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "getDeletedVotersInfoByPanchayatIdsAction.action?"+rparam;
		callAjax(rparam,jsObj,url);
}

function buildDeletedVotersInfo(results)
{
var divEle = document.getElementById("deletedVotersInfo");
if(results != null)
var str='';
str+='<h4 style="margin-left:10px;">Deleted Voters</h4>';
str+='<table class="table table-bordered table-striped table-hover">';
str+='<tr>';
str+='<th style="background: none repeat scroll 0 0 #D9EDF7;color: #454545;"> PanchayatName</th>';
str+='<th style="background: none repeat scroll 0 0 #D9EDF7;color: #454545;"> Deleted Voters</th>';
str+='</tr>';
for(var i in results)
{
str+='<tr>';
str+='<td>'+results[i].panchayatName+'</td>';
str+='<td>'+results[i].totalVoters+'</td>';
str+='</tr>';
}
str+='</table>';
divEle.innerHTML = str;
}

function showPartyPerformancePieChart(result,jsObj)
{
  
  $('#panchayatWisePollingPercentageDiv').html('');
  $('#panchayatWisePollingPercHeadingDiv').html('');
  if(result == null || result.length == 0)
   return;
  $("#panchayatWisePollingPercMainDiv").css("display","block");
  var partyName = $('#partySelectEl option:selected').text();

  $('#panchayatWisePollingPercHeadingDiv').html('<h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;color: black;" class="">PANCHAYAT WISE '+partyName+' PARTY PERFORMANCE REPORT</h4>');

  for(var i in result)
  {
   var divEle = '<div id="partyPerformance'+i+'" class="span6"></div>'; 
    $("#panchayatWisePollingPercentageDiv").append(divEle);
     
   
   var results = result[i].partyPositionVOList;
   var data = new google.visualization.DataTable();
        data.addColumn('string', 'name');
        data.addColumn('number', 'value');
      
		data.addRows(results.length);

		for(var j = 0 ; j< results.length ; j++){		
		var name = results[j].name;
		var val = parseFloat(results[j].rangePercentage);
		  data.setValue(j,0,name);
		  data.setValue(j,1,val);
		}
        // Set chart options
		var title = ''+result[i].name+' PANCHAYAT WISE '+partyName+' PARTY PERFORMANCE'; 
        var options = {'title':title,
                       'width':450,
                       'height':300};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('partyPerformance'+i));
        chart.draw(data, options);
	       
  }
       
}

//getDeletedVotersInfo();

var count=0;
	$('#addMoreBtn').click(function(){
		
		if($('.fromToDivClass').length==2){
			$('#addMoreBtn').css('display','none');
		}
		var template=$('.fromToDivTemplateClass');
		var templateClone=template.clone();
		
		count=count+1;
		templateClone.removeClass('fromToDivTemplateClass');
		templateClone.attr('id','fromToDivId'+count);
		
		
		templateClone.find('.fromDiv').html('From <div class="inputDiv"><input type="text" id="fromTxt" class="fromInput"/></div>');
		templateClone.find('.toDiv').html('To <div class="inputDiv"><input type="text" id="toTxt" class="toInput"/></div>');
		templateClone.find('.closeImgDiv').html('<img src="images/close.png" height="25px" width="25px"/>');
		
		templateClone.appendTo('#ageGroupWiseId');
		
	});
	
	$('.closeImgDiv').live('click',function(){
		$(this).closest('.fromToDivClass').remove();
		if($('.fromToDivClass').length<=2){
			$('#addMoreBtn').css('display','inline-block');
		}
	});
	
	
	var valuesArr;
	<!--$('#getAgeGroupWiseResults').click(function(){
		function getAgeGroupWiseResults(){
		var k=validateAndPush();
		
		if(k!=0){
			if($('#errorMsg').html()==""){
				$('#errorMsg').html('Invalid Input..Please Give valid Input');
			}
			$('#errorMsg').css('display','inline-block');
			return;
		}
		$('#ajaxLoaderImg').css('display','inline-block');
		var agesArr=[];
		
		if(valuesArr.length==1){
			var val1=valuesArr[0]['from'];
			var val2=valuesArr[0]['to'];
			agesArr.push(val1);
			agesArr.push(val2);
		}
		if(valuesArr.length>1){
			var val1=valuesArr[0]['from'];
			var val2=valuesArr[0]['to'];
			var val3=valuesArr[1]['from'];
			var val4=valuesArr[1]['to'];
			agesArr.push(val1);
			agesArr.push(val2);
			agesArr.push(val3);
			agesArr.push(val4);
		}
		if(valuesArr.length>2){
			var val5=valuesArr[2]['from'];
			var val6=valuesArr[2]['to'];
			agesArr.push(val5);
			agesArr.push(val6);
		}
		
		var constituencyId = $('#listConstituencyNames option:selected').val();
		if(constituencyId==0){
		$('#errorMsg').html('Please Select the Constituency');
		}
		
		var jsObj = {
			
	        constituencyId:constituencyId,
			electionId:0,
			partyId:0,
			locationId:0,
			locationType:"panchayat",
			tempVar:"all",
			task:"getAgeGroupWiseReport",
			agesList:agesArr
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "<%=request.getContextPath()%>/getAgeGroupWiseReportAction.action?"+rparam;
		callAjax(rparam,jsObj,url);
	}	
  
		
		
		
	
	function validateAndPush(){
	var errorFree=0;
	valuesArr=[];
	$( ".fromToDivClass" ).each(function (i) {	
			
			var valuesList={
                 from:$(this).find('.fromInput').val(),
			     to:$(this).find('.toInput').val()
             };
			 var from=valuesList['from'];
			 var to=valuesList['to'];
			 
			 
			 var constituencyId = $('#listConstituencyNames option:selected').val();
				if(constituencyId==0){
					$('#errorMsg').html('Please Select the Constituency');
					errorFree=1;
					return;
				}
			 
			 if($.isNumeric(from) && $.isNumeric(to)){
				if(!(parseInt(from)>17 && parseInt(from)<=140) || !(parseInt(to)>17 && parseInt(to)<=140)){
					$('#errorMsg').html('Age should be between 18 & 140');
					errorFree=1;
					return;
				}
			 	if(parseInt(from) >= parseInt(to)){
					$('#errorMsg').html('Invalid Input..From Age > To Age');
					errorFree=1;
					return;
				}else{
					valuesArr.push(valuesList);
					$('#errorMsg').html('');
				}
			 }else{
					if(constituencyId==0){
						$('#errorMsg').html('Please Select the Constituency');
						errorFree=1;
						return;
					}
					else{
						$('#errorMsg').html('Invalid Input..Please Enter only Numerics');
						errorFree=1;
						return;
					}
			 }
		});
		return errorFree;
	}
	
	
		 $(".fromToDivClass input").live('blur',function(){
			var value=$(this).val();
			
			var numStatus=$.isNumeric(value);
			
			if(numStatus==false){
				$('#errorMsg').html('Invalid Input..Please Enter only Numerics');
				return;
			}
			else{
				$('#errorMsg').html('');
			}
			
			if(!(parseInt(value)>17 && parseInt(value)<140)){
				$('#errorMsg').html('Age should be between 18 & 140');
				return;
			}
		});
	
	function buildAgeGroupWiseTable(myResults,jsObj){
		$('#ageGroupTableId1').html('');
		$('#ageGroupTableId2').html('');
		$('#ageGroupTableId3').html('');
		$('#ageGroupBoothTableId1').html('');
		$('#ageGroupBoothTableId2').html('');
		$('#ageGroupBoothTableId3').html('');
		buildHeadBodyForTable(myResults,jsObj);
		
	}
	
	function buildHeadBodyForTable(myResults,jsObj){
		var tablesCount=myResults.length;
		
		if(tablesCount==1){
			createTable(myResults[0],'ageGroupTableId1');
			
			$.each(myResults[0].municipalitesBoothsMap, function(key, value){
				createBoothsTable(value,'ageGroupBoothTableId1',key);
			}); 
			
		}
		else if(tablesCount==2){
			for(var i=0;i<tablesCount;i++){
				var num=i+1;
				createTable(myResults[i],'ageGroupTableId'+num);
				
				$.each(myResults[i].municipalitesBoothsMap, function(key, value){
					createBoothsTable(value,'ageGroupBoothTableId'+num,key);
				});
			}
			
						
		}
		else{
			for(var i=0;i<tablesCount;i++){
				var num=i+1;
				createTable(myResults[i],'ageGroupTableId'+num);
				
				$.each(myResults[i].municipalitesBoothsMap, function(key, value){
					createBoothsTable(value,'ageGroupBoothTableId'+num,key);
				});
			}
		}
	}
	
	
	function createTable(result,tableId){
	$('#title'+tableId).css('display','block');
	$('#title'+tableId).html('<h4>Panchayat Wise Voters Analysis of Age Range - '+result.ageRange+'</h4>');
		var str='';
		<!--str+='<div style="width:800px" align="center"><h4>Panchayat Wise Voters Analysis of Age Range - +'+result.ageRange+'</h4></div>'-->
		str+='<table class="table table-bordered table-striped table-hover" style="font-family:verdana,font-size:12px;" id="table'+tableId+'"><thead><tr><th rowspan=2>Panchayat</th><th rowspan=2>Total Voters In Panchayat</th><th colspan=4>'+result.ageRange+'</th><th rowspan=2>Top Castes</th></tr>';
		str+='<tr><th>Total Voters</th><th>Male Voters</th><th>Female Voters</th>	<th>Percentage</th></tr></thead>';
		str+='<tbody>';
		var res=result.panchayatList;
		for(var i in result.panchayatList){
		str+='<tr>';
		str+='<td>'+result.panchayatList[i].panchayatName+'</td>';
		str+='<td>'+result.panchayatList[i].totalPanchayatVoters+'</td>';
		str+='<td>'+result.panchayatList[i].totalVoters+'</td>';
		str+='<td>'+result.panchayatList[i].maleVoters+'</td>';
		str+='<td>'+result.panchayatList[i].femaleVoters+'</td>';
		str+='<td>'+result.panchayatList[i].percentage+'</td>';
		str+='<td>';
		var topCastesLength=result.panchayatList[i].topCastes.length;
		for(var j in result.panchayatList[i].topCastes){
		str+=result.panchayatList[i].topCastes[j].castName+"("+result.panchayatList[i].topCastes[j].castCount+")";
			 if(topCastesLength>j){
				str+=", ";
			}
		}
		str+='</td>';
		str+='</tr>';
		}
		str+='</tbody></table>';
		
		$('#'+tableId).html(str);
		$('#table'+tableId).dataTable({
			"iDisplayLength": 15,
			"aLengthMenu": [[15, 30, -1], [15, 30, "All"]]
		});
	}
	
	function createBoothsTable(result,tableId,mncplName){
	$('#title'+tableId).css('display','block');
	$('#title'+tableId).html('<h4>'+mncplName+'- Booth Wise Voters Analysis of Age Range - '+result[0].ageRange+'</h4>');
		var str='';
		str+='<table class="table table-bordered table-striped table-hover" style="font-family:verdana,font-size:12px;" id="table'+tableId+'"><thead><tr><th rowspan=2>Booths</th><th rowspan=2>Total Voters In Booth</th><th colspan=4>'+result[0].ageRange+'</th><th rowspan=2>Top Castes</th></tr>';
		str+='<tr><th>Total Voters</th><th>Male Voters</th><th>Female Voters</th>	<th>Percentage</th></tr></thead>';
		str+='<tbody>';
		for(var i in result){
		var vstr="";
		vstr+='<tr>';
		vstr+='<td>'+result[i].panchayatName+'</td>';
		vstr+='<td>'+result[i].totalPanchayatVoters+'</td>';
		vstr+='<td>'+result[i].totalVoters+'</td>';
		vstr+='<td>'+result[i].maleVoters+'</td>';
		vstr+='<td>'+result[i].femaleVoters+'</td>';
		vstr+='<td>'+result[i].percentage+'</td>';
		vstr+='<td>';
		var topCastesLength=result[i].topCastes.length;
		for(var j in result[i].topCastes){
		vstr+=result[i].topCastes[j].castName+"("+result[i].topCastes[j].castCount+")";
			 if(topCastesLength>j){
				vstr+=", ";
			}
		}
		vstr+='</td>';
		vstr+='</tr>';
		
		str+=vstr;
		}
		str+='</tbody></table>';
		
		$('#'+tableId).html(str);
		$('#table'+tableId).dataTable({
			"iDisplayLength": 15,
			"aLengthMenu": [[15, 30, -1], [15, 30, "All"]]
		});
	}
	
	 
		 
</script>
</body>
</html>
