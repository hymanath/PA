<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>District Page</title>


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

	<script type="text/javascript" src="js/districtPage/districtPage.js"></script>	
	<link rel="stylesheet" type="text/css" href="styles/districtPage/districtPage.css">

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
var selectedZptcYear,selectedMptcYear;

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
			label : "Votes %",
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
			label : "Votes %",
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
				"",					
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
						}else{
							hideZptcDiv();
						}
					}
					if(jsObj.task == "getMptcPartyDetails") 
					{
						if(results!= null &&  results.length>0){
							showAllMptcPartyDetails(results);
						}else{
							hideMptcDiv();
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
					if(jsObj.task == "getAllElectionsInDistrict")
					{										
						showAllElectionsInDistrict(results);
					}else
					if(jsObj.task == "getElectionTypesAndYears")
					{										
						buildElectionTypesAndYears(results);
					}else
					if(jsObj.task == "getPartiesPositions")
					{										
						buildElectionTypesAndYearsGraph(results);
					}if(jsObj.task == "getAllElectionScopes")
					{										
						buildElectionTypesSelect(results);
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

	function getAllElections(elecId, type){
		var jsObj=
		{		
				districtId:districtId,
				districtName:"${districtName}",
				electionTypeId:elecId,
				electionType:type,
				task:"getAllElectionsInDistrict"						
		};
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/getAllElectionsInDistrictAction.action?"+rparam;					
		callAjax(rparam,jsObj,url);
	}
	
	function buildElectionTypesSelect(result){

		var selectLabel = document.getElementById("graphElectionTypeLabel");
		var selectData = document.getElementById("graphElectionTypeSelect");

		var labelStr = '';
		labelStr += 'Election Type';

		if(selectLabel)
			selectLabel.innerHTML = labelStr;

		var dataStr = '';
		dataStr += '<select onchange="getAllElections(this.options[this.selectedIndex].value, this.options[this.selectedIndex].text)">';		
		for(var i in result)
		{
			dataStr += '<option value="'+result[i].id+'"> '+result[i].name+' </option>';
		}
		dataStr += '</select>';		
		
		if(selectData)
			selectData.innerHTML = 	dataStr;	
		
		getAllElections(result[0].id, result[0].name);
	}

	function buildElectionTypesAndYearsGraph(results)
	{
		var elmt = document.getElementById("partyPositionsDiv");
		if(!elmt)
			return;

		var str = '';
		str+='<img	src="charts/'+results.pasitionsChart+'"/>';
		elmt.innerHTML=str;
	}
	function buildElectionTypesAndYears(results)
	{
		var elmt = document.getElementById("electionHirarchiDiv");
		if(!elmt)
			return;
		
		var str = '';
		str +='<span style="font-weight:bold;margin-right:10px;">Select Election</span>';
		str += '<select id="electionTypesSelect" onchange = "getPartiesPositions(this.options[this.selectedIndex].value,this.options[this.selectedIndex].text)">';
		for(var i in results)
		{
			var localArr = results[i];
			if(localArr.length == 3)
				str += '<option value="'+localArr[0]+'">'+localArr[1]+'-'+localArr[2]+'</option>';
			
		}
		str += '</select>';

		elmt.innerHTML = str;
	
		var elmt = document.getElementById("electionTypesSelect");
		if(elmt)
		{
			var id = elmt.options[elmt.selectedIndex].value;
			var name = elmt.options[elmt.selectedIndex].text;
			getPartiesPositions(id,name);
		}
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
		 var browser1 = window.open("<s:url action="districtPageCandidateDetailsAjaxAction.action"/>?disId="+districtId+"&eleType="+zptcElectionType+"&eleYear="+zptcElectionYear,"browser1","scrollbars=yes,height=630,width=1020,left=200,top=200");
		 browser1.focus();
	}
	function redirectMptcCandidateLink()
	{
		 var browser2 = window.open("<s:url action="districtPageCandidateDetailsAjaxAction.action"/>?disId="+districtId+"&eleType="+mptcElectionType+"&eleYear="+mptcElectionYear,"browser2","scrollbars=yes,height=630,width=1020,left=200,top=200");
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
		selectedZptcYear = id;
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
		selectedMptcYear = id;
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
	function hideZptcDiv(){
	     var zptcElmt = document.getElementById("zptcAjaxLoadDiv");
		 var zptcDiv="";
		 zptcElmt.innerHTML = zptcDiv;
		 
		 var candLink = document.getElementById("candidateLink");
		 var candidateLink="";
		 candLink.innerHTML = candidateLink;
		 
		 var zptcDetailsElmt = document.getElementById("partyDetails");
		 var zptcDetailsDiv="";
		 zptcDetailsDiv+="<br/>";
		 zptcDetailsDiv+="<b>"+selectedZptcYear+" ZPTC data is not available.</b>";
		 zptcDetailsElmt.innerHTML = zptcDetailsDiv;
		 
		 var totalZptcCountResultDiv = document.getElementById("totalZptcCountResultDiv");
		 var totalZptcCountResult="";
		 totalZptcCountResultDiv.innerHTML = totalZptcCountResult;		 		

		 		 
	}
	
	function hideMptcDiv(){	 
		 var mptcElmt = document.getElementById("mptcAjaxLoadDiv");
		 var mptcDiv="";
		 mptcElmt.innerHTML = mptcDiv;
		 
		 var candLink = document.getElementById("mptcCandidateLink");
		 var candidateLink="";
		 candLink.innerHTML = candidateLink;
		 
		 var mptcElmt = document.getElementById("mptcPartyDetails");
		 var mptcDiv="";
		 mptcDiv+="<br/>";
		 mptcDiv+="<b>"+selectedMptcYear+" MPTC data is not available.</b>";
		 mptcElmt.innerHTML = mptcDiv;
		 
		 var totalMptcCountResultDiv = document.getElementById("totalMptcCountResultDiv");
		 var totalMptcCountResult="";
		 totalMptcCountResultDiv.innerHTML = totalMptcCountResult;		 
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

	

	function showAllElectionsInDistrict(results){
				
		var allElecDiv = document.getElementById("allElectionResultsInDT_body");
		var allElecDivImg = document.getElementById("barloaderImage");

		if(allElecDivImg)
			allElecDivImg.style.display = 'none';
		
		var graphDivStr = '';				
		if(results.chartPath == null)
			graphDivStr += '<b>Sorry, Data Not Available</b>'
		else
			graphDivStr += '<img src="charts/'+results.chartPath+'" />';
		
		 allElecDiv.innerHTML = graphDivStr;	 
		
	}

	function showAllElectionsInDistrictHead(){
		
		var allElecDiv = document.getElementById("allElectionResultsInDT_head");
		var graphDivStr = '';
		graphDivStr += '<table>';
		graphDivStr += '<tr>';
		graphDivStr += '<td><div style="margin-left:20px;"><input type="button" onclick="showAlliancePartiesWindow()" value="Know About Alliance Parties"></div></td>';
		graphDivStr += '<td><div id="graphElectionTypeLabel"></div></td>';
		graphDivStr += '<td><div id="graphElectionTypeSelect"></div></td>';
		graphDivStr += '<td><img id="barloaderImage" src="images/icons/barloader.gif"/></td>';
		graphDivStr += '</tr>';
		graphDivStr += '</table>';	
		 allElecDiv.innerHTML = graphDivStr;
		 
		 getAllElectionScopes();
	}
	
	function getElectionTypesAndYears(){
		var jsObj=
		{		
				districtId:districtId,
				task:"getElectionTypesAndYears"						
		};
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/getAllElectionsSelectInDistrictAction.action?"+rparam;					
		callAjax(rparam,jsObj,url);
	}
	
	function getPartiesPositions(id,value){
		
		var jsObj=
		{		electionId:id,
				electionTypeYear:value,
				districtId:districtId,
				districtName:"${districtName}",
				task:"getPartiesPositions"						
		};
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/getAllPartiesPositionsInDistrictAction.action?"+rparam;					
		callAjax(rparam,jsObj,url);
	}

	function showAlliancePartiesWindow(){
		var brow1 = window.open("<s:url action="alliancePartiesPageAction"/>?districtId=${districtId}&districtName=${districtName}","brow1","width=1050,height=600,menubar=no,status=no,location=no,toolbar=no,scrollbars=yes");
		brow1.focus();
	}

	function buildGraphsCarousel(divId,arr)
	{
		var elmt = document.getElementById(divId);
		if(!elmt && arr.length == 0)
			return;

		var contentStr = '';
		contentStr+='<ul>';
		for(var i in arr)
		{				
			contentStr+='<LI style="width:880px;height:300px;"><IMG src="charts/'+arr[i]+'"></IMG></LI>';		
		}
		contentStr+='</ul>';

		elmt.innerHTML = contentStr;

		graphImagesCarousel = new YAHOO.widget.Carousel(divId,
				{
					carouselEl: "UL",
					isCircular: true,
					isVertical: false,
					numVisible: 1,
					animation: { speed: 1.0 },
					autoPlayInterval: 2000
				});

		graphImagesCarousel.render(); 
		graphImagesCarousel.show();
	}

	function getAllElectionScopes(){
		var jsObj=
		{	
				task:"getAllElectionScopes"						
		};
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/getAllElectionScopesSelectInDistrictAction.action?"+rparam;					
		callAjax(rparam,jsObj,url);
	}
	
</script>
 
</head>
<body>

<div id="districtPageMainDiv">	

	<!--District Page Layout-->
	<div id="districtPageLayout_main"></div>	

	<!--District Page Right Layout-->
	<div id="districtPageLayout_right">		

		<div id="newConstDiv_main">
			<div id="newConstDiv_head">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" style="width:100%;">
					<tr>
						<td width="10%"><img src="images/icons/districtPage/header_left.gif"/></td>
						<td><div class="districtPageRoundedHeaders_center" style="padding:4px;"><span>New Constituencies in Delimitation ${constituenciesStatusVO.delimitationYear}</span></div></td>
						<td><img src="images/icons/districtPage/header_right.gif"/></td>
					</tr>
				</table>
			</div>
			<div id="newConstDiv_body">				
				<c:forEach var="result" varStatus="stat" items="${constituenciesStatusVO.newConstituencies}">
					<div id="newConstAncSpan" class="mandalNamesDiv">		
					<table>
						<tr>
						<td> <img src="images/icons/districtPage/listIcon.png"/></td>
						<td><span class="mandalNameSpan">
							<a href="constituencyPageAction.action?districtId=${districtId}&constituencyId=${result.id}" class="districtAnc" style="text-decoration:none;" onmouseover="javascript:{this.style.textDecoration='underline';}" onmouseout="javascript:{this.style.textDecoration='none';}"> ${result.name}
							</a>
						</td>
						</tr>
					</table>
				</div>	
				</c:forEach>						
			</div>
		</div>


		
		<div id="delimitMandalsDiv_main">
			<div id="delimitMandalsDiv_head">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" style="width:100%;">
					<tr>
						<td width="10%"><img src="images/icons/districtPage/header_left.gif"/></td>
						<td><div class="districtPageRoundedHeaders_center" style="padding:4px;"><span>Dissolved Constituencies in Delimitation ${constituenciesStatusVO.delimitationYear}</span></div></td>
						<td><img src="images/icons/districtPage/header_right.gif"/></td>
					</tr>
				</table>
			</div>
			<div id="delimitMandalsDiv_body">
				<c:forEach var="result" varStatus="stat" items="${constituenciesStatusVO.deletedConstituencies}">			
				<div id="mandalAncSpan" class="mandalNamesDiv">							
					<table>
						<tr>
						<td> <img src="images/icons/districtPage/listIcon.png"/></td>
						<td><span class="mandalNameSpan">
							<a href="constituencyPageAction.action?districtId=${districtId}&constituencyId=${result.id}" class="districtAnc" style="text-decoration:none;" onmouseover="javascript:{this.style.textDecoration='underline';}" onmouseout="javascript:{this.style.textDecoration='none';}">${result.name}
							</a>
						</td>
						</tr>
					</table>
				</div>			
				</c:forEach>			
			</div>
		</div>
		

		<div id="mandalsDiv_main">
			<div id="mandalsDiv_head">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" style="width:100%;">
					<tr>
						<td width="10%"><img src="images/icons/districtPage/header_left.gif"/></td>
						<td><div class="districtPageRoundedHeaders_center"><span>Mandals</span></div></td>
						<td><img src="images/icons/districtPage/header_right.gif"/></td>
					</tr>
				</table>
			</div>
			<div id="mandalsDiv_body">
				<c:forEach var="mandalsBeforeDelimitationConstituency" varStatus="stat" items="${mandals}">						
						<div id="mandalAncSpan" class="mandalNamesDiv">							
							<table>
								<tr>
								<td> <img src="images/icons/districtPage/listIcon.png"/></td>
								<td><span class="mandalNameSpan">
									<a href="mandalPageElectionInfoAction.action?MANDAL_ID=${mandalsBeforeDelimitationConstituency.id}&MANDAL_NAME=${mandalsBeforeDelimitationConstituency.name}" class="districtAnc">${mandalsBeforeDelimitationConstituency.name}
									</a></span>
								</td>
								</tr>
							</table>
						</div>					
				</c:forEach>	
			</div>			
		</div>

	</div>

	<!--District Page Center Layout-->
	<div id="districtPageLayout_center">
		<div id="districtMap">
			<div id="districtMap_head">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" style="width:100%;">
					<tr>
						<td width="30px"><img src="images/icons/districtPage/header_left.gif"/></td>
						<td><div class="districtPageRoundedHeaders_center" style="width:658px"><span> ${districtName} District Details</span></div></td>
						<td><img src="images/icons/districtPage/header_right.gif"/></td>
					</tr>
				</table>
			</div>
			<div id="districtMap_body">
				<object width="550" height="430">
					<param name="movie" value="images/icons/districtPage/districtMaps/nellore.swf">
					 <param name="wmode" value="transparent"> 
					<embed wmode="transparent" src="images/icons/districtPage/districtMaps/nellore.swf" width="550" height="430">
					</embed>
				</object>
			</div>
		</div>
		<div id="partiesPerformanceGraphDistrict">
			<div id="alliancePartiesCarousel" class="yui-skin-sam">
				<ul>
				<li>
					<div id="allElectionResultsInDT"  class="allianceListDiv">
						<div id="allElectionResultsInDT_head"></div>
						<div id="allElectionResultsInDT_body"></div>
					</div>
				</li>
				<li>
					<div id="positionsGraphDiv" class="allianceListDiv">
						<div id="electionHirarchiDiv" ></div>
						<div id="partyPositionsDiv"></div>
					</div>
				</li>
				</ul>
			</div>
		</div>
	</div>
	
	<!--District Page MP, MLA Div-->
	<div id="mpMla_main">
		<div id="mp_main_div">
			<div id="mp_head">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" style="width:100%;">
					<tr>
						<td width="30px"><img src="images/icons/districtPage/header_left.gif"/></td>
						<td>	
							<div id="mpInfoDivHead" class="districtPageRoundedHeaders_center" style="width:793px;">
								<span>Member of Parliament (MP) in the District</span>
							</div>
						</td>
						<td><img src="images/icons/districtPage/header_right.gif"/></td>
					</tr>
				</table>
			</div>
			<div id="mp_body" class="yui-skin-sam">
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
		<div id="mla_main_div">
			<div id="mla_head">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" style="width:100%;">
					<tr>
						<td width="30px"><img src="images/icons/districtPage/header_left.gif"/></td>
						<td>	
							<div id="mlaInfoDivHead" class="districtPageRoundedHeaders_center" style="width:793px;">
								<span>Member of Legislative Assembly (MLA) in the District</span>
							</div>
						</td>
						<td><img src="images/icons/districtPage/header_right.gif"/></td>
					</tr>
				</table>
			</div>
			<div id="mla_body" class="yui-skin-sam">
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
	</div>

	<!--District Page MPTC, ZPTC Div-->
	<div id="mptc_zptc_div_main">
		<table>
			<tr>
				<td>
					<div id="zptc_main">
						<div id="zptc_head">
							<table width="100%" border="0" cellpadding="0" cellspacing="0" style="width:100%;">
								<tr>
									<td width="30px"><img src="images/icons/districtPage/header_left.gif"/></td>
									<td>	
										<div id="zptcInfoDivHead" class="districtPageRoundedHeaders_center" style="width:400px;padding:9px;height:18px;">
											<span>Total Number of ZPTC's : </span>
											<span id="totalZptcCountResultDiv"></span>
										</div>
									</td>
									<td><img src="images/icons/districtPage/header_right.gif"/></td>
								</tr>
							</table>
						</div>
						<div id="zptc_body">
							<div id="zptcDiv" class="yui-skin-sam">
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
						</div>
					</div>
				</td>
				<td>
					<div id="mptc_main">
						<div id="mptc_head">
							<table width="100%" border="0" cellpadding="0" cellspacing="0" style="width:100%;">
								<tr>
									<td width="30px"><img src="images/icons/districtPage/header_left.gif"/></td>
									<td>	
										<div id="mptcInfoDivHead" class="districtPageRoundedHeaders_center" style="width:400px;padding:9px;height:18px;">
											<span>Total Number of MPTC's : </span>
											<span id="totalMptcCountResultDiv"></span>
										</div>
									</td>
									<td><img src="images/icons/districtPage/header_right.gif"/></td>
								</tr>
							</table>
						</div>
						<div id="mptc_body">
							<div id="mptcDiv" class="yui-skin-sam">
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
					</div>		
				</td>
			</tr>
		</table>
	</div>	

</div>

<script language="javascript">

initializeDistrictPage();

initializeResultsTableForMp();
initializeResultsTable();
showAllElectionsInDistrictHead();
getAllMptcParties();
getAllZptcParties();
getAllZptcYears();
getElectionTypesAndYears();
getAllMptcYears();


var allianceCarousel = new YAHOO.widget.Carousel("alliancePartiesCarousel",
			{
				carouselEl: "UL",
				isCircular: true,
				isVertical: false,
				numVisible: 1,
				animation: { speed: 1.0 },
				autoPlayInterval: 2000
			});

	allianceCarousel.render(); 
	allianceCarousel.show();
 
</script>
</body>
</html>