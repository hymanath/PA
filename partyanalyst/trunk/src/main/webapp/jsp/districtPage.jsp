<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>District Page</title>


<!-- YUI files dependencies (start) -->

<!--CSS files (default YUI Sam Skin) -->
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
 
<!--JS files Dependencies -->
<script src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/json/json-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/get/get-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/dragdrop/dragdrop-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></script>

<!-- YUI files dependencies (end) -->

<style type="text/css">
		.detailsDiv
		{
			margin-top:10px;
			margin-bottom:10px;
			margin-right:30px;
			margin-left:30px;
			text-align:left;
		}
		.detailsHead
		{
			font-weight:bold;
			color:#1C4B7A;
			text-decoration:underline;
			font-size:15px;
			padding:5px 5px 5px 0px;
			margin-bottom: 5px;
		}
		.detailsBody
		{
			padding:5px;
			background-color:#F1F5F7;
		}				
		#districtAncSpan
		{
			padding:10px;
			font-size:12px;
		}
		.districtAnc
		{
			color:#1C4B7A;
		}	
		.yui-skin-sam th.yui-dt-asc, .yui-skin-sam th.yui-dt-desc 
		{
			background:none;
		}

		.yui-skin-sam thead {

			background-color:#C4DEFF;
			color:#3F546F;
		}
		.candidateDetailsStyle{
			background-color:#EFF3F7;
			border-color:#96B4D3;
			border-style:solid;
			border-width:1px 1px 1px 1px;
			margin-bottom:5px;margin-left:0;
			margin-top:0;
			padding:4px;
			color:#247CD4;
			font-size:13px;
			font-weight:bold;
		}
		.yui-skin-sam .yui-dt th, .yui-skin-sam .yui-dt th a {
			font-weight:bold;
			text-decoration:none;
			vertical-align:bottom;
		}
		.counterSize{
			color:red;
			font-size:14px;
		}
	</style>
	

<script type="text/javascript">

var tehsilDetails={
			zptcArray:[],
			mptcArray:[],
			partyArray:[],
			partyMptcArray:[]
		};
var districtId = ${districtId};
var myDataTableForParty,myDataTableForMptcParty,zptcElectionYear,mptcElectionYear,mptcElectionTypeId=3,zptcElectionTypeId=4,mptcElectionType="MPTC",zptcElectionType="ZPTC";
var totalZptcs = 0,totalMptcs = 0;
	function initializeResultsTable() {

	var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
			.get("mlaDataSortingTable"));
	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
	resultsDataSource.responseSchema = {
		fields : [ {
			key : "constituencyName",formatter:YAHOO.widget.DataTable.formatLink
		}, {
			key : "candidateName",formatter:YAHOO.widget.DataTable.formatLink
		}, {
			key : "partyFlag"
		}, {
			key : "parliamentConstituencyName"
		}, {
			key : ""
		}]
	};

	var resultsColumnDefs = [ {
		key : "constituencyName",
		label : "Constituency Name",
		sortable : true
	}, {
		key : "candidateName",
		label : "Candidate Name",
		sortable : true
	}, {
		key : "parliamentConstituencyName",
		label : "Parliament Constituency",
		sortable : true	
	}, {
		key : "partyFlag",
		label : "Party Flag",
		sortable : true	
	}, {
		key : "",
		label : "Complete Results"	
	}];

	var myConfigs = {
    paginator : new YAHOO.widget.Paginator({
        rowsPerPage: 10
    })
};
	var myDataTable = new YAHOO.widget.DataTable("mlaInfoDivBody",resultsColumnDefs, resultsDataSource,myConfigs);  
}


function getConstituencyElecResultsWindow(constiId,elecType,elecYear)
{
   var browser1 = window.open("<s:url action="constituencyElectionResultsAction.action"/>?constituencyId="+constiId+"&electionType="+elecType+"&electionYear="+elecYear,"browser1","scrollbars=yes,height=600,width=750,left=200,top=200");
   browser1.focus();
}

	function initializeResultsTableForMp() {

		var resultsDataSourceForTehsil = new YAHOO.util.DataSource(YAHOO.util.Dom
				.get("mpsDataSortingTable"));
		resultsDataSourceForTehsil.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
		resultsDataSourceForTehsil.responseSchema = {
			fields : [  {
				key : "constituencyName"
			},{
				key : "candidateName"
			}, {
				key : "partyFlag"
			}, {
				key : ""
			}]
		};

		var resultsColumnDefsForTehsil = [  {
			key : "constituencyName",
			label : "Constituency Name",
			sortable : true
		},{
			key : "candidateName",
			label : "Candidate Name",
			sortable : true
		}, {
			key : "partyFlag",
			label : "Party Flag",
			sortable : true	
		} , {
			key : "",
			label : "Complete Results"
		} ];

		
		var myDataTableForTehsil = new YAHOO.widget.DataTable("mpsInfoDivBody",resultsColumnDefsForTehsil, resultsDataSourceForTehsil);  
		
	}
	
	function initializeResultsTableForParty(){
		var resultsDataSourceForTehsil = new YAHOO.util.DataSource(tehsilDetails.partyArray);
		resultsDataSourceForTehsil.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
		resultsDataSourceForTehsil.responseSchema = {
			fields : [ {
				key : "partyName"
			}, {
				key : "participatedSeats"
			}, {
				key : "seatsWonByParty"
			}, {
				key : "percentageOfVotesWonByParty"
			}]
		};
	
		var resultsColumnDefsForTehsil = [ {
			key : "partyName",
			label : "Party Name",
			sortable : true
		}, {
			key : "participatedSeats",
			label : "Participated Seats",
			sortable : true
		}, {
			key : "seatsWonByParty",
			label : "Seats Won",
			sortable : true
		}, {
			key : "percentageOfVotesWonByParty",
			label : "Percentage Of Votes Won",
			sortable : true
		} ];

		var myConfigsForTehsil = {
	    paginator : new YAHOO.widget.Paginator({
	        rowsPerPage: 10 
	    })
	};
				
		myDataTableForParty = new YAHOO.widget.DataTable("partyDetails",resultsColumnDefsForTehsil, resultsDataSourceForTehsil,myConfigsForTehsil);

		 var ajaxImgElmt = document.getElementById("zptcAjaxLoadDiv");
		 ajaxImgElmt.style.display = "none"; 
		 
		return {
			oDS:resultsDataSourceForTehsil, 
			oDT:myDataTableForParty			
		};  		
	}
	function initializeMptcResultsTableForParty(){
		
		var resultsDataSourceForTehsil = new YAHOO.util.DataSource(tehsilDetails.partyMptcArray);
		resultsDataSourceForTehsil.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
		resultsDataSourceForTehsil.responseSchema = {
			fields : [ {
				key : "partyName"
			}, {
				key : "participatedSeats"
			}, {
				key : "seatsWonByParty"
			}, {
				key : "percentageOfVotesWonByParty"
			}]
		};
	
		var resultsColumnDefsForTehsil = [ {
			key : "partyName",
			label : "Party Name",
			sortable : true
		}, {
			key : "participatedSeats",
			label : "Participated Seats",
			sortable : true
		}, {
			key : "seatsWonByParty",
			label : "Seats Won",
			sortable : true
		}, {
			key : "percentageOfVotesWonByParty",
			label : "Percentage Of Votes Won",
			sortable : true
		} ];

		var myConfigsForTehsil = {
	    paginator : new YAHOO.widget.Paginator({
	        rowsPerPage: 10 
	    })
	};
				
		myDataTableForMptcParty = new YAHOO.widget.DataTable("mptcPartyDetails",resultsColumnDefsForTehsil, resultsDataSourceForTehsil,myConfigsForTehsil);

		var ajaxImgElmt = document.getElementById("mptcAjaxLoadDiv");
		 ajaxImgElmt.style.display = "none"; 
		 
		return {
			oDS:resultsDataSourceForTehsil, 
			oDT:myDataTableForMptcParty			
		}; 		
	}
	
	function callAjax(param,jsObj,url){
	var results;	
	var callback = {			
	    success : function( o ) {
			try {												
					results = YAHOO.lang.JSON.parse(o.responseText);		
					if(jsObj.task == "getAllElectionYears")
					{
						if(results!= null &&  results.length>0){
							showAllYearsForZptc(results);
						}
					}	
					if(jsObj.task == "getAllMptcElectionYears")
					{
						if(results!= null &&  results.length>0){
							showAllYearsForMptc(results);
						}
					}
					if(jsObj.task == "getPartyDetails") 
					{
						if(results!= null &&  results.length>0){
							showAllPartyDetails(results);
						}
					}
					if(jsObj.task == "getMptcPartyDetails") 
					{
						if(results!= null &&  results.length>0){
							showAllMptcPartyDetails(results);
						}
					}	
					if(jsObj.task == "getAllMptcParties") 
					{
						if(results!= null &&  results.length>0){
							showAllMptcParties(results);
						}
					}
					if(jsObj.task == "getAllZptcParties") 
					{
						if(results!= null &&  results.length>0){
							showAllZptcParties(results);
						}
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

	function showAllMptcParties(results){
		assignToPartyDataArray = new Array();
		for(var i in results)
		{		
			var problemObj=		
			 {		
					partyName:results[i].partyName,
					participatedSeats:results[i].participatedSeats,
					seatsWonByParty:results[i].seatsWonByParty,
					percentageOfVotesWonByParty:results[i].percentageOfVotesWonByParty				
			 };
			
			assignToPartyDataArray.push(problemObj);
			tehsilDetails.partyMptcArray=assignToPartyDataArray;	
		}
	
		var emptyArr = new Array();
	    if(results.length == 0)
		{	
	    	tehsilDetails.partyMptcArray = emptyArr;				
		}
	    initializeMptcResultsTableForParty(); 
	}
	
	function showAllZptcParties(results){
		assignToPartyDataArray = new Array();
		for(var i in results)
		{		
			var problemObj=		
			 {		
					partyName:results[i].partyName,
					participatedSeats:results[i].participatedSeats,
					seatsWonByParty:results[i].seatsWonByParty,
					percentageOfVotesWonByParty:results[i].percentageOfVotesWonByParty				
			 };
			
			assignToPartyDataArray.push(problemObj);
			tehsilDetails.partyArray=assignToPartyDataArray;	
		}
	
		var emptyArr = new Array();
	    if(results.length == 0)
		{	tehsilDetails.partyArray = emptyArr;				
		}
	    initializeResultsTableForParty();
	}
	function redirectCandidateLink()
	{
		 var browser1 = window.open("<s:url action="districtPageCandidateDetailsAjaxAction.action"/>?disId="+districtId+"&eleType="+zptcElectionType+"&eleYear="+zptcElectionYear,"browser1","scrollbars=yes,height=630,width=970,left=200,top=200");
		 browser1.focus();
	}
	function redirectMptcCandidateLink()
	{
		 var browser2 = window.open("<s:url action="districtPageCandidateDetailsAjaxAction.action"/>?disId="+districtId+"&eleType="+mptcElectionType+"&eleYear="+mptcElectionYear,"browser2","scrollbars=yes,height=630,width=970,left=200,top=200");
		 browser2.focus();
	}
	function showAllPartyDetails(results)
	{
		var candLink = document.getElementById("candidateLink");
		var linkRef = '<a href="javascript:{}" onclick="redirectCandidateLink()"  style="text-decoration:none;" class="candidateDetailsStyle">Show Candidate Details</a>';
		candLink.innerHTML = linkRef;
		assignToPartyDataArray = new Array();
		totalZptcs = 0;
		for(var i in results)
		{		
			var problemObj=
			 {		
					partyName:results[i].partyName,
					participatedSeats:results[i].participatedSeats,
					seatsWonByParty:results[i].seatsWonByParty,
					percentageOfVotesWonByParty:results[i].percentageOfVotesWonByParty				
			 };
			totalZptcs = totalZptcs + results[i].seatsWonByParty;
			assignToPartyDataArray.push(problemObj);
			tehsilDetails.partyArray=assignToPartyDataArray;	
		}
	
		var totalZptcCountDiv = document.getElementById("totalZptcCountResultDiv");
		var totalStr='';
		totalStr += '<b class="counterSize">'+totalZptcs+'</b>';
		totalZptcCountDiv.innerHTML = totalStr;	
		
		var emptyArr = new Array();
	    if(results.length == 0)
		{	
	    	tehsilDetails.partyArray = emptyArr;				
		}
	    initializeResultsTableForParty();  
	}

	function showAllMptcPartyDetails(results)
	{
		var candLink = document.getElementById("mptcCandidateLink");
		var linkRef = '<a href="javascript:{}" onclick="redirectMptcCandidateLink()" style="text-decoration:none;" class="candidateDetailsStyle" >Show Candidate Details</a>';
		candLink.innerHTML = linkRef;
		assignToPartyDataArray = new Array();
		totalMptcs = 0;
		for(var i in results)
		{		
			var problemObj=		
			 {		
					partyName:results[i].partyName,
					participatedSeats:results[i].participatedSeats,
					seatsWonByParty:results[i].seatsWonByParty,
					percentageOfVotesWonByParty:results[i].percentageOfVotesWonByParty				
			 };
			totalMptcs = totalMptcs + results[i].seatsWonByParty;
			assignToPartyDataArray.push(problemObj);
			tehsilDetails.partyMptcArray=assignToPartyDataArray;	
		}
	  	
		var totalMptcCountDiv = document.getElementById("totalMptcCountResultDiv");
		var totalStr='';
		totalStr += '<b class="counterSize">'+totalMptcs+'</b>';
		totalMptcCountDiv.innerHTML = totalStr;	
			 
		var emptyArr = new Array();
	    if(results.length == 0)
		{	
	    	tehsilDetails.partyMptcArray = emptyArr;				
		}
	    initializeMptcResultsTableForParty();  
	}
	
	function showAllYearsForZptc(results)
	{
		var selectedElmt = document.getElementById("zptcElectionYears");
		getPartyDetails(results[0].id);
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

	function showAllYearsForMptc(results)
	{
		var selectedElmt = document.getElementById("mptcElectionYears");
		getMptcPartyDetails(results[0].id);
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
	
	function getAllYearsForTeshil()
	{
		var jsObj=
		{		
				eleType:zptcElectionTypeId,
				task:"getAllElectionYears"						
		};
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/districtPageTehsilElectionYearsAjaxAction.action?"+rparam;						
		callAjax(rparam,jsObj,url);
	}
	
	function getAllMptcYearsForTeshil()
	{
		var jsObj=
		{		
				eleType:mptcElectionTypeId,
				task:"getAllMptcElectionYears"						
		};
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/districtPageMptcElectionYearsAjaxAction.action?"+rparam;						
		callAjax(rparam,jsObj,url);
	}
	function getPartyDetails(id)
	{	
		var ajaxImgElmt = document.getElementById("zptcAjaxLoadDiv");
		ajaxImgElmt.style.display = "block";
		zptcElectionYear = id;
		var jsObj=
		{		
				districtId:districtId,
				electionType:zptcElectionType,
				electionYear:id,
				task:"getPartyDetails"						
		};
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/districtPagePartyDetailsAjaxAction.action?"+rparam;						
		callAjax(rparam,jsObj,url);
	}
	function getMptcPartyDetails(id)
	{
		var ajaxImgElmt = document.getElementById("mptcAjaxLoadDiv");
		ajaxImgElmt.style.display = "block";
		mptcElectionYear = id;		
		var jsObj=
		{		
				districtId:districtId,
				electionType:mptcElectionType,
				electionYear:id,
				task:"getMptcPartyDetails"						
		};
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/districtPageMptcPartyDetailsAjaxAction.action?"+rparam;						
		callAjax(rparam,jsObj,url);
	}
	function getAllZptcParties(){
		
		var jsObj=
		{		
				electionTypeId:zptcElectionTypeId,
				districtId:districtId,
				electionType:zptcElectionType,
				task:"getAllZptcParties"						
		};
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/districtPagePartyDetailsAjaxAction.action?"+rparam;						
		callAjax(rparam,jsObj,url);
	}
	
	function getAllMptcParties(){
		var jsObj=
		{		
				electionTypeId:mptcElectionTypeId,
				districtId:districtId,
				electionType:mptcElectionType,
				task:"getAllMptcParties"						
		};
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/districtPageMptcPartyDetailsAjaxAction.action?"+rparam;					
		callAjax(rparam,jsObj,url);
	}
	
	function getAllZptcYears()
	{	 	 
		 var imgElmt = document.getElementById("zptcInfoDivBody");
		 var electionDetails="";
		 electionDetails+="Select a Election Year :";
		 electionDetails+='<select id="zptcElectionYears" class="selectWidth" list="result" theme="simple" onchange="getPartyDetails(this.value)"/>';
		 imgElmt.innerHTML = electionDetails;
		 
        var spanElmt=document.getElementById("zptcDetails");	
 
		getAllYearsForTeshil();		 
	}
	function getAllMptcYears()
	{	 
		 var imgElmt = document.getElementById("mptcInfoDivBody");
		 var electionDetails="";
		 electionDetails+="Select a Election Year :";
		 electionDetails+='<select id="mptcElectionYears" class="selectWidth" list="result" theme="simple" onchange="getMptcPartyDetails(this.value)"/>';
		 imgElmt.innerHTML = electionDetails;
		 
        var spanElmt=document.getElementById("mptcDetails");  
		getAllMptcYearsForTeshil();				 
	}
</script>
 
</head>
<body>
<div class="detailsHead">
		Welcome to <c:out value="${districtName}"></c:out> District Page <br/><br/>
</div>
<div id="districtInfoDiv" class="detailsDiv">
	<table>
		<tr>
			<td id="districtInfoDivHead" class="detailsHead"> MLA Constituencies for ${constituenciesStatusVO.delimitationYear}</td>
		<td> : <b  class="counterSize"> ${constituenciesStatusVO.totalConstituenciesAfterDelimitation} </b></td>
		</tr>
	</table>
		<div id="districtInfoDivBody" class="detailsBody">

		<table><tr>
		<c:forEach var="result" varStatus="stat" items="${constituenciesStatusVO.existConstituencies}">			
				<td>
				<span id="districtAncSpan">
					<img height="10" width="10" src="<%=request.getContextPath()%>/images/icons/arrow.png"/>
					<a href="constituencyPageAction.action?districtId=${districtId}&constituencyId=${result.id}" class="districtAnc" style="text-decoration:none;" onmouseover="javascript:{this.style.textDecoration='underline';}" onmouseout="javascript:{this.style.textDecoration='none';}">${result.name}
					</a>
				</span>
			</td>	
			<c:if test="${stat.count % 4==0}">
				</tr><tr><td colspan="4"> </td></tr><tr>
			</c:if>			
		</c:forEach>			
		
		</tr><tr><td colspan="4"></td></tr><tr>
		<c:forEach var="result" varStatus="stat" items="${constituenciesStatusVO.newConstituencies}">
			<td>
				<span id="districtAncSpan">
					<img height="10" width="10" src="<%=request.getContextPath()%>/images/icons/arrow.png"/>
					<a href="constituencyPageAction.action?districtId=${districtId}&constituencyId=${result.id}" class="districtAnc" style="text-decoration:none;" onmouseover="javascript:{this.style.textDecoration='underline';}" onmouseout="javascript:{this.style.textDecoration='none';}">* ${result.name}
					</a>
				</span>
			</td>	
			<c:if test="${stat.count % 4==0}">
				</tr><tr><td colspan="4"> </td></tr><tr>
			</c:if>			
		</c:forEach>			
		</tr>
		</table>		
	</div>
	&nbsp &nbsp	* indicates New Constituencies after Delimitation
</div>



<div id="districtInfoDiv" class="detailsDiv">
	<table>
		<tr>
			<td id="districtInfoDivHead" class="detailsHead"> Dissolved Constituencies in Delimitation ${constituenciesStatusVO.delimitationYear}</td>
			<td> :<b  class="counterSize"> ${constituenciesStatusVO.totalDeletedConstituencies}</b> </td>
		</tr>
	</table>

	<div id="districtInfoDivBody" class="detailsBody">
		<table><tr>
		<c:forEach var="result" varStatus="stat" items="${constituenciesStatusVO.deletedConstituencies}">			
				<td>
				<span id="districtAncSpan">
					<img height="10" width="10" src="<%=request.getContextPath()%>/images/icons/arrow.png"/>
					<a href="constituencyPageAction.action?districtId=${districtId}&constituencyId=${result.id}" class="districtAnc" style="text-decoration:none;" onmouseover="javascript:{this.style.textDecoration='underline';}" onmouseout="javascript:{this.style.textDecoration='none';}">${result.name}
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

<div id="tehsilInfoDiv" class="detailsDiv">
<div id="tehsilInfoDivHead" class="detailsHead">
		Member of Parliament (MP) in the District
	</div>
	<div class="yui-skin-sam">
	<div id="mpsInfoDivBody">
		<table id="mpsDataSortingTable">			
			<c:forEach var="mpsDetails" varStatus="stat" items="${parliamentCandidateDetailsVo.candidateDetails}">			
				<tr>
					<td>
					<span id="districtAncSpan">
									<a href="constituencyPageAction.action?districtId=${districtId}&constituencyId=${mpsDetails.constituencyId}" class="districtAnc" style="text-decoration:none;" onmouseover="javascript:{this.style.textDecoration='underline';}" onmouseout="javascript:{this.style.textDecoration='none';}">${mpsDetails.constituencyName}
							</a>
						</span>
					</td>
					<td>
						<span id="districtAncSpan">
								<a href="candidateElectionResultsAction.action?candidateId=${mpsDetails.candidateId}" class="districtAnc" style="text-decoration:none;" onmouseover="javascript:{this.style.textDecoration='underline';}" onmouseout="javascript:{this.style.textDecoration='none';}">${mpsDetails.candidateName}
								</a>
						</span>
					</td>
					<td>	
						<img src="<%=request.getContextPath()%>/images/party_flags/${mpsDetails.partyFlag}" height="30" width="40"/>
					</td>
					<td> <a href="javascript:{}" onclick="getConstituencyElecResultsWindow('${mpsDetails.constituencyId}','Parliament','2009')">view results</a>
					</td>
				</tr>  
			</c:forEach>
		</table>		
	</div>
	</div>
</div>

<div id="districtInfoDiv" class="detailsDiv">
<div id="districtInfoDivHead" class="detailsHead">
		Member of Legislative Assembly (MLA) in the District
	</div>
	<div class="yui-skin-sam">
	<div id="mlaInfoDivBody">
		<table  id="mlaDataSortingTable">
			
			<c:forEach var="candidate" varStatus="stat" items="${constituenciesStatusVO.constituencyWinnerInfoVO}">			
				<tr>
					<td>
						<span id="districtAncSpan">
							<a href="constituencyPageAction.action?districtId=${districtId}&constituencyId=${candidate.constituencyId}" class="districtAnc" style="text-decoration:none;" onmouseover="javascript:{this.style.textDecoration='underline';}" onmouseout="javascript:{this.style.textDecoration='none';}">${candidate.constituencyName}
							</a>
						</span>
					</td>
					<td>
					<span id="districtAncSpan">
							<a href="candidateElectionResultsAction.action?candidateId=${candidate.candidateId}" class="districtAnc" style="text-decoration:none;" onmouseover="javascript:{this.style.textDecoration='underline';}" onmouseout="javascript:{this.style.textDecoration='none';}">${candidate.candidateName}
							</a>
						</span>
					 &nbsp </td>
					<td><img src="<%=request.getContextPath()%>/images/party_flags/${candidate.partyFlag}" height="30" width="40"/></td>


					<td>
						<span id="districtAncSpan">
							<a href="constituencyPageAction.action?districtId=${districtId}&constituencyId=${candidate.parliamentConstituencyId}" class="districtAnc" style="text-decoration:none;" onmouseover="javascript:{this.style.textDecoration='underline';}" onmouseout="javascript:{this.style.textDecoration='none';}">${candidate.parliamentConstituencyName}
							</a>
						</span>
					</td>
					<td>
	                <a href="javascript:{}" onclick="getConstituencyElecResultsWindow('${candidate.constituencyId}','${constituenciesStatusVO.electionType}','${constituenciesStatusVO.electionYear}')">view results</a>
				</td>
				</tr>  
			</c:forEach>
		</table>		
	</div>
	</div>
</div>



<div id="mandalInfoDiv" style="margin-left:0.7cm; text-align:left;">
	<table>
		<tr>
			<td><div id="zptcInfoDivHead" class="detailsHead"> Total Number of ZPTC's : </div></td><b><td><div id="totalZptcCountResultDiv"></div></b></td>			
		</tr>		
	</table>
	
			<div class="yui-skin-sam">
			<table><tr></tr>
					<td><div id="zptcInfoDivBody"></div></td>
					<td><div id="zptcAjaxLoadDiv" style="display:none;padding-top:20px;">
						<img id="ajaxImg" height="13" width="10" src="<%=request.getContextPath()%>/images/icons/search.gif"/>			
					</div></td><td><div id="candidateLink"></div></td></tr>
					</table>
					<table cellpadding="5px">
					<tr><td><div id="partyDetails"></div></td>
					</tr>
					</table>
			</div>	
						
	<table>
		<tr>
			<td><div id="mptcInfoDivHead" class="detailsHead"> Total Number of MPTC's : </div></td><td><b><td><div id="totalMptcCountResultDiv"></div></b></td>	
		</tr>
	</table>
	
			<div class="yui-skin-sam">
			<table><tr></tr>
					<td><div id="mptcInfoDivBody"></div></td>
					<td><div id="mptcAjaxLoadDiv" style="display:none;padding-top:20px;">
						<img id="ajaxImg" height="13" width="10" src="<%=request.getContextPath()%>/images/icons/search.gif"/>			
					</div></td><td><div id="mptcCandidateLink"></div></td></tr>
					</table>
					<table cellpadding="5px">
					<tr><td><div id="mptcPartyDetails"></div></td>
					</tr>
					</table>
			</div>
</div>	

<div id="mandalInfoDiv" class="detailsDiv">
	<div id="mandalInfoDivHead" class="detailsHead">
		Mandal in <c:out value="${districtName}"/> District 
	</div>

	<div id="mandalInfoDivBody" class="detailsBody">
		<table><tr>
		<c:forEach var="mandalsBeforeDelimitationConstituency" varStatus="stat" items="${mandals}">				
			<td>
				<span id="mandalAncSpan">
					<img height="10" width="10" src="<%=request.getContextPath()%>/images/icons/arrow.png"/>
					<a href="mandalPageElectionInfoAction.action?MANDAL_ID=${mandalsBeforeDelimitationConstituency.id}&MANDAL_NAME=${mandalsBeforeDelimitationConstituency.name}" class="districtAnc" style="text-decoration:none;" onmouseover="javascript:{this.style.textDecoration='underline';}" onmouseout="javascript:{this.style.textDecoration='none';}">${mandalsBeforeDelimitationConstituency.name}
					</a>&nbsp; 
				</span>
			</td>	
			<c:if test="${stat.count % 4==0}">
				</tr><tr><td colspan="4"> </td></tr><tr>
			</c:if>			
		</c:forEach>			
		</tr>  </table>		
	</div>	
</div>
</div>
</div>
<script language="javascript">
initializeResultsTableForMp();
initializeResultsTable();
getAllMptcParties();
getAllZptcParties();
getAllZptcYears();
getAllMptcYears();
</script>
</body>
</html>