<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>
<!-- YUI Dependency files (Start) -->
	<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/element-min.js"></script>  	
	<script src="js/yahoo/layout-min.js"></script>  
	<script type="text/javascript" src="js/json/json-min.js"></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script>  
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script>  
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	<!-- Skin CSS files resize.css must load before layout.css -->   
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
	<!-- YUI Dependency files (End) -->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${constituencyName} CONSTITUENCY Local Body Elections Results</title>
<style type="text/css">
.table-bordered caption + thead tr:first-child th, .table-bordered caption + tbody tr:first-child th, .table-bordered caption + tbody tr:first-child td, .table-bordered colgroup + thead tr:first-child th, .table-bordered colgroup + tbody tr:first-child th, .table-bordered colgroup + tbody tr:first-child td, .table-bordered thead:first-child tr:first-child th, .table-bordered tbody:first-child tr:first-child th, .table-bordered tbody:first-child tr:first-child td 
{
    border-top: 0 none;
}
.table caption + thead tr:first-child th, .table caption + thead tr:first-child td, .table colgroup + thead tr:first-child th, .table colgroup + thead tr:first-child td, .table thead:first-child tr:first-child th, .table thead:first-child tr:first-child td 
{
    border-top: 0 none;
}
.table thead.info th, .impFamilesMainDiv th 
{
    background: none repeat scroll 0 0 #D9EDF7;
	font-size: 15px;
    color: #454545;
}
.table thead th 
{
    vertical-align: bottom;
}
.table-bordered th, .table-bordered td
 {
    border-left: 1px solid #DDDDDD;
}
.table th 
{
    font-weight: bold;
}
.table th, .table td 
{
    border-top: 1px solid #DDDDDD;
	font-size: 16px;
    line-height: 20px;
    padding: 8px;
    text-align: center;
    vertical-align: top;
}
.table-bordered 
{
    border-collapse: separate;
}
table {
    border-collapse: collapse;
    border-spacing: 0;
}
.electionResults
{
	clear: both;
    margin: 0 -20px;
    padding: 10px 10px 10px 20px;
    text-align: start;
    font-size: 16px;
    font-weight: bolder;
    font-family: serif;
	color: highlight;
	margin-left:16px;
}
#corpDetails,#muncipalDetails
{
    clear: both;
    color: highlight;
    font-family: serif;
    font-size: 16px;
    margin: -25px -20px 0 16px;
    padding: 10px 10px 10px 20px;
    text-align: start;
}
.electionResultsTable
{
	margin-left: 76px;
	margin-bottom: 14px;
}
.showResultButton 
{
    margin-bottom: -17px;
    margin-left: 290px;
    margin-right: 68px;
    position: relative;
}
.localBodyElectionYearSelect
{
	margin-left: 291px;
    margin-top: -21px;
}
.errorMsgDiv
{
	display:none;
	float: left;
    margin-left: 74px;
}
#zptcMainDiv,#mptcMainDiv,#muncipalMainDiv,#corpMainDiv,#ghmcMainDiv
{	
	display:none;
	border: 1px solid #A1A1A1;
    border-radius: 10px 10px 10px 10px;
    margin-bottom: 17px;
    margin-left: 32px;
	margin-bottom: 15px;
}
#headingDiv
{
	color: black;
    font-family: Tahoma;
    font-size: 17px;
    margin-bottom: 16px;
    margin-left: 91px;
}
#mptcTableDiv, #zptcTableDiv, #muncipalTableDiv, #corpTableDiv
{
    background-color: #F5F6F6;
    color: #000000;
    font-family: Helvetica;
    font-size: 13px;
    margin-left: 39px;
    padding: 10px;
    text-align: center;
    width: 546px;
}
.yui-skin-sam .yui-dt-liner 
{
    margin: 0;
    padding: 4px 10px;
    width: 116px;
}
.yui-skin-sam thead .yui-dt-sortable {
    color: inherit;
    cursor: inherit;
}
.yui-skin-sam .yui-dt-liner 
{
    color: highlight;
    margin: 0;
    padding: 7px 10px;
    text-align: justify;
    width: 116px;
}
.muncipalOrCorpButton
{
	margin-left: 429px;
}
#muncipalButtonDiv,#corpButtonDiv
{
	margin-bottom: 10px;
}
#ghmcMainDiv
{
	border: 1px solid #A1A1A1;
    margin-left: 40px;
    width: 980px;
	display:none;
    border-radius: 10px 10px 10px 10px;
    margin-bottom: 17px;
	margin-bottom: 15px;
	
}
#ghmcTableDiv
{
    background-color: #F5F6F6;
    color: #000000;
    font-family: Helvetica;
    font-size: 13px;
    margin-left: 39px;
    padding: 10px;
    text-align: center;
    width: 896px;
}
.yui-skin-sam .yui-pg-container {
    display: block;
    margin: 6px 0 6px 278px;
    white-space: nowrap;
	
}
.yui-skin-sam .yui-dt-paginator {
    display: block;
    margin: 6px 0;
    white-space: nowrap;
	margin-left: auto;
}

</style>
<script type="text/javascript">
var constituencyId = '${constituencyId}';
var constituencyName = '${constituencyName}';
$(document).ready(function(){
getLocalBodyElectionType();
});
/*
This Method is Used To Sead a Ajax Call For Retriving MPTC LocalBody Election Details
*/
function getMptcPartyDetails(){
	var mptcElectionYear = $("#mptcElectionYear :selected").text();
	var jsObj = {
			constituencyType:'Assembly',
			constituencyId:constituencyId,
			mptcElectionYear:mptcElectionYear,
			task:"getMptcElectionResults"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "localBodyElectionResultsAjaxAction.action?"+rparam;
	callAjax(jsObj, url);
}
/*
This Method is Used To Sead a Ajax Call For Retriving ZPTC LocalBody Election Details
*/
function getZptcPartyDetails(){
	var zptcElectionYear = $('#zptcElectionYear :selected').text();
	var jsObj = {
			constituencyType:'Assembly',
			constituencyId:constituencyId,
			zptcElectionYear:zptcElectionYear,
			task:"getZptcElectionResults"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "localBodyElectionResultsAjaxAction.action?"+rparam;
	callAjax(jsObj, url);
}
/*
This Method is Used To Sead a Ajax Call For Retriving Muncipality LocalBody Election Details
*/
function getMuncipalityPartyDetails(){
	var localBodyElectionId = $('#muncipalElectionYear :selected').val();
	var jsObj = {
			constituencyId:constituencyId,
			localBodyElectionId:localBodyElectionId,
			task:"getMuncipalityElectionResults"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "localBodyElectionResultsAjaxAction.action?"+rparam;
	callAjax(jsObj, url);
}
/*
This Method is Used To Sead a Ajax Call For Retriving Corpration LocalBody Election Details
*/
function getCorpPartyDetails(){
	var localBodyElectionId = $('#corpElectionYear :selected').val();
	var jsObj = {
			constituencyId:constituencyId,
			localBodyElectionId:localBodyElectionId,
			task:"getCorpElectionResults"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "localBodyElectionResultsAjaxAction.action?"+rparam;
	callAjax(jsObj, url);
}
/*
This Method is Used To Sead a Ajax Call For Retriving GHMC LocalBody Election Details
*/
function getGhmcPartyDetails()
{
	var localBodyElectionId = $('#ghmcElectionYear :selected').val();
	var jsObj = {
			stateId : 1,
			localBodyElectionId:localBodyElectionId,
			constituencyId:constituencyId,
			task:"greaterElectionsInfo"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getGreaterConstiResults.action?"+rparam;
	callAjax(jsObj, url);
}
/*
	This Method is Used To Check Weather the Local Body Election are Present or Not In Particular Constituency
*/
function checkForLocalBodyElection()
{
	var jsObj = {
			constituencyId:constituencyId,
			task:"checkForLocalBodyElection"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "localBodyElectionResultsAjaxAction.action?"+rparam;
	callAjax(jsObj, url);
}
/*
	This Method is used to get the Local Body Election Area type Ex:-(RURAL,URBAn,RURAL-URBAN)
*/
function getLocalBodyElectionType()
{
	var jsObj = {
			constituencyId:constituencyId,
			task:"getLocalBodyElectionType"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "localBodyElectionResultsAjaxAction.action?"+rparam;
	callAjax(jsObj, url);
}

/*
	This Method Is Used To Build a Table For All MPTC Details 
*/
function buildDataTableForMptc(myResults)
{	
$('#mptcMainDiv').show();
$('#mptcSeats').show();
	if(myResults != null && myResults.length > 0)
	{
		var totalSeats = myResults[0].totalSeats;
		var mptcColumnDefs =
							[ 
							 {key:"partyName",label:"Party",sortable:true},
							 {key:"participatedSeats",label:"Participated Seats",sortable:true},
							 {key:"seatsWonByParty",label:"Won",sortable:true},
							 {key:"percentageOfVotesWonByParty",label:"Voting%",sortable:true}
							]; 

		 
			var myDataSource = new YAHOO.util.DataSource(myResults);
			myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
			myDataSource.responseschema = {
							 fields : ["partyName","participatedSeats","seatsWonByParty","percentageOfVotesWonByParty"]
						};
		
		 var familesDataSource = new YAHOO.widget.DataTable("mptcTableDiv", mptcColumnDefs,myDataSource);
		 $('#totalmptcSeats').html(totalSeats);	 
	}
	else
	{
		$('#mptcTableDiv').html('<b>No Date Available</b>');
		$('#mptcSeats').hide();
	}
}
/*
	This Method Is Used To Build a Table For All ZPTC Details 
*/
function buildDataTableForZptc(myResults)
{	
	$('#zptcMainDiv').show();
	$('#zptcSeats').show();
	if(myResults != null && myResults.length > 0)
	{
		var totalSeats = myResults[0].totalSeats;
		var zptcColumnDefs =
							[ 
							 {key:"partyName",label:"Party",sortable:true},
							 {key:"participatedSeats",label:"Participated Seats",sortable:true},
							 {key:"seatsWonByParty",label:"Won",sortable:true},
							 {key:"percentageOfVotesWonByParty",label:"Voting%",sortable:true}
							]; 
					
			var myDataSource = new YAHOO.util.DataSource(myResults);
			myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
			myDataSource.responseschema = {
							 fields : ["partyName","participatedSeats","seatsWonByParty","percentageOfVotesWonByParty"]
						};
		
		 var familesDataSource = new YAHOO.widget.DataTable("zptcTableDiv", zptcColumnDefs,myDataSource);
		 $('#totalZptcSeats').html(totalSeats);
		 
	}
	else
	{
		$('#zptcTableDiv').html('<b>No Date Available</b>');
		$('#zptcSeats').hide();
	}
}
/*
	This Method Is Used To Build a Table For All Muncipalites Details 
*/
function buildDataTableForMuncipal(myResults)
{	$('#muncipalMainDiv').show();
	$('#muncipalDetails').show();
	if(myResults.muncipalityDetails != null)
	{
			var muncipalName = myResults.muncipalityDetails[0].muncipalityName;
		    var totWards = myResults.muncipalityDetails[0].totalWards;
			var totalVoters = myResults.muncipalityDetails[0].totalVoters;
			var totalPolledVotes = myResults.muncipalityDetails[0].totalPolledVotes;
		for(var i in myResults.muncipalityDetails)
		{
			if(myResults.muncipalityDetails[i].muncipalityVO != null && myResults.muncipalityDetails[i].muncipalityVO.length > 0)
			{
				var muncipalColumnDefs =
								[ 
								 {key:"partyName",label:"Party",sortable:true},
								 {key:"participatedSeats",label:"Participated Seats",sortable:true},
								 {key:"seatsWonByParty",label:"Won",sortable:true},
								 {key:"percentageOfVotesWonByParty",label:"Voting%",sortable:true}
								]; 
						
				var myDataSource = new YAHOO.util.DataSource(myResults.muncipalityDetails[i].muncipalityVO);
				myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
				myDataSource.responseschema = {
								 fields : ["partyName","participatedSeats","seatsWonByParty","percentageOfVotesWonByParty"]
							};
			
			 var familesDataSource = new YAHOO.widget.DataTable("muncipalTableDiv", muncipalColumnDefs,myDataSource);
			}
		}
		var divEle = document.getElementById('muncipalButtonDiv');
		var str='';
		str+='<input type="button" class="muncipalOrCorpButton" value="SHOW RESULTS" onClick="redirectMuncipalityCandidateLink(\'MUNCIPALITY\','+myResults.muncipalityDetails[i].electionTypeId+','+myResults.muncipalityDetails[i].muncipalityId+','+myResults.muncipalityDetails[i].latestMuncipalElectionYear+',\''+myResults.muncipalityDetails[i].muncipalityName+'\')";></input>';
		divEle.innerHTML = str;
		$('#muncipalName').html(muncipalName);
		$('#muncipalTotWards').html(totWards);
		$('#muncipalTotVoters').html(totalVoters);
		$('#muncipalPolledVotes').html(totalPolledVotes);
	}
	else
	{
		$('#muncipalTableDiv').html('<b>No Date Available</b>');
	}
}
/*
	This Method Is Used To Build a Table For All Corpration Details 
*/
function buildDataTableForCorp(myResults)
{	$('#corpMainDiv').show();
	$('#corpDetails').show();
	if(myResults.corprationDetails != null)
	{
		for(var i in myResults.corprationDetails)
		{
			var corpname = myResults.corprationDetails[0].muncipalityName;
		    var totWards = myResults.corprationDetails[0].totalWards;
			var totalVoters = myResults.corprationDetails[0].totalVoters;
			var totalPolledVotes = myResults.corprationDetails[0].totalPolledVotes;
			if(myResults.corprationDetails[i].muncipalityVO != null && myResults.corprationDetails[i].muncipalityVO.length > 0)
			{
				var corpColumnDefs =
								[ 
								 {key:"partyName",label:"Party",sortable:true},
								 {key:"participatedSeats",label:"Participated Seats",sortable:true},
								 {key:"seatsWonByParty",label:"Won",sortable:true},
								 {key:"percentageOfVotesWonByParty",label:"Voting%",sortable:true}
								]; 
						
				var myDataSource = new YAHOO.util.DataSource(myResults.corprationDetails[i].muncipalityVO);
				myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
				myDataSource.responseschema = {
								 fields : ["partyName","participatedSeats","seatsWonByParty","percentageOfVotesWonByParty"]
							};
			
			 var familesDataSource = new YAHOO.widget.DataTable("corpTableDiv", corpColumnDefs,myDataSource);
			}
		}
		var divEle = document.getElementById('corpButtonDiv');
		var str='';
		str+='<input type="button" class="muncipalOrCorpButton" value="SHOW RESULTS" onClick="redirectMuncipalityCandidateLink(\'Corporation\','+myResults.corprationDetails[i].electionTypeId+','+myResults.corprationDetails[i].muncipalityId+','+myResults.corprationDetails[i].latestMuncipalElectionYear+',\''+myResults.corprationDetails[i].muncipalityName+'\')";></input>';
		divEle.innerHTML = str;
		$('#corpName').html(corpname);
		$('#corpTotWards').html(totWards);
		$('#corpTotVoters').html(totalVoters);
		$('#corpPolledVotes').html(totalPolledVotes);
	}
	else
	{
		$('#corpTableDiv').html('<b>No Date Available</b>');
	}
}
/*
	This Method Is Used To Build a Table For All GHMC Details 
*/
function buildDataTableForGhmc(myResults)
{	
	$('#ghmcMainDiv').show();
	
	if(myResults.localElectionsInfo != null)
	{
		for(var i in myResults.localElectionsInfo)
		{
			if(myResults.localElectionsInfo[i].wardwiseResultsForParty != null && myResults.localElectionsInfo[i].wardwiseResultsForParty.length > 0)
			{
				var ghmcColumnDefs =
								[ 
								 {key:"constituencyName",label:"Ward",sortable:true},
								 {key:"partyName",label:"Party",sortable:true},
								 {key:"candidateName",label:"Candidate",sortable:true},
								 {key:"votesEarned",label:"Votes Gained",sortable:true},
								 {key:"votesPercentage",label:"Voting%",sortable:true},
								 {key:"rank",label:"Rank",sortable:true,width:50},
								 {key:"totalVotes",label:"Total Voters",sortable:true}
								]; 
				var myConfigsForghmc = {
				paginator : new YAHOO.widget.Paginator({
					rowsPerPage: 15 
				})
				};		
				var myDataSource = new YAHOO.util.DataSource(myResults.localElectionsInfo[i].wardwiseResultsForParty);
				myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
				myDataSource.responseschema = {
								 fields : ["constituencyName","partyName","candidateName","votesEarned","votesPercentage","rank","totalVotes"]
							};
			}
		}
		var divEle = document.getElementById('ghmcButtonDiv');
		var str='';
		str+='<input type="button" class="muncipalOrCorpButton" value="SHOW RESULTS" onClick="redirectMuncipalityCandidateLink(\''+myResults.localElectionsInfo[i].electionType+'\','+myResults.localElectionsInfo[i].electionTypeId+','+myResults.localElectionsInfo[i].id+','+myResults.localElectionsInfo[i].electionYear+',\''+myResults.localElectionsInfo[i].name+'\')";></input>';
		divEle.innerHTML = str;
		 var familesDataSource = new YAHOO.widget.DataTable("ghmcTableDiv", ghmcColumnDefs,myDataSource,myConfigsForghmc);
		$('#mptcMainDiv').hide();
		$('#zptcMainDiv').hide(); 
	}
	else
	{
		$('#ghmcTableDiv').html('<b>No Date Available</b>');
	}
}
/*
	This Method is used For Ajax Calls Handling
*/
function callAjax(jsObj,url)
	{	
	
		
 		var callback = {			
 		               success : function( o ) {
							try {
								myResults = YAHOO.lang.JSON.parse(o.responseText);
								
							if(jsObj.task=="getMptcElectionResults")
							{
								buildDataTableForMptc(myResults);
							}	
							else if(jsObj.task=="getZptcElectionResults")
							{
								buildDataTableForZptc(myResults);
							}	
 							else if(jsObj.task=="getMuncipalityElectionResults")
							{
								buildDataTableForMuncipal(myResults);
							}
							else if(jsObj.task=="getCorpElectionResults")
							{
								buildDataTableForCorp(myResults);
							}	
							else if(jsObj.task=="greaterElectionsInfo")
							{
								buildDataTableForGhmc(myResults);
							}	
							else if(jsObj.task=="getLocalBodyElectionType")
							{
								checkingForAreaType(myResults);
							}							
							}catch (e) {   
							   	//alert("Invalid JSON result" + e);   
							}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                		//	alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('GET', url, callback);
	}
/*
	This Method is Used To Show Muncipalites Or Corpration in a Details 
*/
function redirectMuncipalityCandidateLink(muncipalityElectionType,muncipalityElectionId,muncipalityId,latestMuncipalElectionYear,name)
{	
	
		var browser3 = window.open("muncipalElectionReportAction.action?muncipalityId="+muncipalityId+"&muncipalityElectionType="+muncipalityElectionType+"&name="+name+"&muncipalityElectionId="+muncipalityElectionId+"&electionYear="+latestMuncipalElectionYear,"browser3","scrollbars=yes,height=670,width=1170,left=200,top=200");
		browser3.focus();
}
/*
	This Method is Used To Show ZPTC Election Details in a Details 
*/
function redirectZptcCandidateLink()
{	
		var zptcElectionYear = $("#zptcElectionYear :selected").text();
		var zptcElectionType = 'ZPTC';	
		var constituencyTYPE = 'Assembly';	
	    var browser1 = window.open("constituencyPageCandidateDetailsAjaxAction.action?constId="+constituencyId+"&eleType="+zptcElectionType+"&eleYear="+zptcElectionYear+"&constTYPE="+constituencyTYPE,"browser1","scrollbars=yes,height=630,width=1020,left=200,top=200");
		browser1.focus();
}
/*
	This Method is Used To Show MPTC Election Details in a Details 
*/
function redirectMptcCandidateLink()
{	
		var mptcElectionYear = $("#mptcElectionYear :selected").text();	
		var mptcElectionType = 'MPTC';
		var constituencyTYPE = 'Assembly';
		var browser2 = window.open("constituencyPageCandidateDetailsAjaxAction.action?constId="+constituencyId+"&eleType="+mptcElectionType+"&eleYear="+mptcElectionYear+"&constTYPE="+constituencyTYPE,"browser2","scrollbars=yes,height=630,width=1020,left=200,top=200");
		browser2.focus();
}
/*
	Thia Method is Used To Check The Condition For Displaying the details Local Body Election Details Based On Area Type
*/
function checkingForAreaType(myResults)
{
	if(myResults == 'RURAL')
	{
		<s:if test="zptcElection != null && zptcElection.size() > 0">
		getZptcPartyDetails();
		</s:if>
		<s:if test="mptcElection != null && mptcElection.size() > 0">
		getMptcPartyDetails();
		</s:if>
	}
	else if(myResults == 'URBAN')
	{
		<s:if test="muncipalElection != null && muncipalElection.size() > 0">
		getMuncipalityPartyDetails();
		</s:if> 
		<s:if test="corpElection != null && corpElection.size() > 0">
		getCorpPartyDetails();
		</s:if> 
		<s:if test="greaterElections != null && greaterElections.size() > 0">
		getGhmcPartyDetails();
		</s:if>
	}
	else if(myResults == 'RURAL-URBAN')
	{
		<s:if test="zptcElection != null && zptcElection.size() > 0">
		getZptcPartyDetails();
		</s:if>
		<s:if test="mptcElection != null && mptcElection.size() > 0">
		getMptcPartyDetails();
		</s:if>
		<s:if test="muncipalElection != null && muncipalElection.size() > 0">
		getMuncipalityPartyDetails();
		</s:if> 
		<s:if test="corpElection != null && corpElection.size() > 0">
		getCorpPartyDetails();
		</s:if> 
		<s:if test="ghmcElection != null && ghmcElection.size() > 0">
		getGhmcPartyDetails();
		</s:if>
	}
}
</script>
</head>

<body>

<div id="headingDiv" align="center">${constituencyName} CONSTITUENCY LOCAL BODY ELECTION RESULTS</div>
<div id="mainDiv" style="margin-left: -25px;">

<div id="zptcMainDiv" class="bordeDiv">
<div id="zptcDiv" style="margin-left: 42px;"> 
<s:if test="zptcElection != null && zptcElection.size() > 0">
<h4 id="zptcHeading" class ="electionResults">ZPTC ELECTION RESULTS :
<div id="zptcElectionYear" class="localBodyElectionYearSelect">Select Year : <s:select theme="simple" cssClass="selectBoxWidth" label="Select Year" name="zptcElectionYear" id="zptcElectionYear" list="zptcElection" listKey="id" listValue="name" onChange="getZptcPartyDetails();"/></div>
</br>
<div style="width:1000px" id="zptcSeats" style="display:none;color: brown;">Total Seats : <span><font id="totalZptcSeats" style="dispaly:none;"></font>
<input type="button" class="showResultButton" value="SHOW RESULTS" onClick="redirectZptcCandidateLink();"></input>
</div>
</s:if></h4>
</div>
<div id="zptcTableDiv" class="electionResultsTable yui-skin-sam yui-dt-sortable yui-dt"></div>
<div class="errorMsgDiv"></div>
</div>

<div id="mptcMainDiv" class="bordeDiv">
<div id="mptcDiv" style="margin-left: 42px;"> 
<s:if test="mptcElection != null && mptcElection.size() > 0">
<h4 id="mptcHeading" class ="electionResults" >MPTC ELECTION RESULTS :
<div id="mptcYearSelectId" class="localBodyElectionYearSelect">Select Year : <s:select theme="simple" cssClass="selectBoxWidth" label="Select Year" name="mptcElectionYear" id="mptcElectionYear" list="mptcElection" listKey="id" listValue="name" onChange="getMptcPartyDetails();"/></div>
</br>
<div style="width:1000px" id="mptcSeats" style="display:none;color: brown;">Total Seats : <font id="totalmptcSeats" style="dispaly:none;"></font>
<input type="button" class="showResultButton" value="SHOW RESULTS" onClick="redirectMptcCandidateLink();"></input>
</div>
</s:if></h4>
</div>
<div id="mptcTableDiv" class="electionResultsTable yui-skin-sam yui-dt-sortable yui-dt"></div>
<div class="errorMsgDiv"></div>
</div>

<div id="muncipalMainDiv" class="bordeDiv">
<div id="muncipalDiv" style="margin-left: 42px;"> 
<s:if test="muncipalElection != null && muncipalElection.size() > 0">
<h4 id="muncipalHeading" class ="electionResults">MUNCIPALITY ELECTION RESULTS :
<div id="muncipalYearSelectId" class="localBodyElectionYearSelect">Select Year : <s:select theme="simple" cssClass="selectBoxWidth" label="Select Year" name="muncipalElectionYear" id="muncipalElectionYear" list="muncipalElection" listKey="id" listValue="name" onChange="getMuncipalityPartyDetails();"/></div>
</s:if></h4>
</br>
<div id="muncipalDetails" style="display:none;">
<span id="muncipalityName"><b>Muncipality Name :</b> </span><font id="muncipalName" style="dispaly:none;"></font>
<span id="muncipalTotalWards" style="float: right;
    margin-right: 148px;"><b>Total Wards :</b><font id="muncipalTotWards" style="dispaly:none;"></font></span>
</br>
<span id="muncipalTotalVoters"><b>Total Voters : </b></span><font id="muncipalTotVoters" style="dispaly:none;"></font>
<span id="muncipalTotalPolledVoters" style="float: right;
    margin-right: 144px;"><b>Total Polled Votes : </b><font id="muncipalPolledVotes" style="dispaly:none;"></font>
</span>
</div>
<div id="muncipalButtonDiv"></div>
</div>
<div id="muncipalTableDiv" class="electionResultsTable yui-skin-sam yui-dt-sortable yui-dt"></div>
<div class="errorMsgDiv"></div>
</div>

<div id="corpMainDiv" class="bordeDiv">
<div id="corpDiv" style="margin-left: 42px;">
<s:if test="corpElection != null && corpElection.size() > 0">
<h4 id="corpHeading" class ="electionResults" >CORPORATION ELECTION RESULTS :
<div id="corpYearSelectId" class="localBodyElectionYearSelect">Select Year : <s:select theme="simple" cssClass="selectBoxWidth" label="Select Year" name="corpElectionYear" id="corpElectionYear" list="corpElection" listKey="id" listValue="name" onChange="getCorpPartyDetails();"/></div>
</s:if></h4>
</br>
<div id="corpDetails" style="display:none;">
<span id="corporationName"><b>Corporation Name :</b> </span><font id="corpName" style="dispaly:none;"></font>
<span id="corpTotalWards" style="float: right;
    margin-right: 148px;"><b>Total Wards :</b><font id="corpTotWards" style="dispaly:none;"></font></span>
</br>
<span id="corpTotalVoters"><b>Total Voters : </b></span><font id="corpTotVoters" style="dispaly:none;"></font>
<span id="corpTotalPolledVoters" style="float: right;
    margin-right: 144px;"><b>Total Polled Votes : </b><font id="corpPolledVotes" style="dispaly:none;"></font>
</span>
</div>
<div id="corpButtonDiv"></div>
</div>
<div id="corpTableDiv" class="electionResultsTable yui-skin-sam yui-dt-sortable yui-dt"></div>
<div class="errorMsgDiv"></div>
</div>

<div id="ghmcMainDiv" class="bordeDiv">
<div id="ghmcDiv" style="margin-left: 42px;">
<s:if test="greaterElections != null && greaterElections.size() > 0">
<h4 id="ghmcHeading" class ="electionResults" style=" width:300px" >GHMC ELECTION RESULTS :</h4> 
<div id="ghmcYearDiv" align="center" style="margin-top: -30px; margin-bottom: 17px; margin-left: 90px;">Select Year : <s:select theme="simple" cssClass="selectBoxWidth" label="Select Year" name="ghmcElectionYear" id="ghmcElectionYear" list="greaterElections" listKey="id" listValue="name"/>
</s:if>
<div id="ghmcButtonDiv" style="margin-left:-113px;margin-top:-22px;"></div>
</div>
</div>
<div id="ghmcTableDiv" class="electionResultsTable yui-skin-sam yui-dt-sortable yui-dt">
<div class="errorMsgDiv"></div>
</div>
</div>

</div>
</body>
</html>

