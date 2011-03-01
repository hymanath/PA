<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.ResourceBundle;" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Combo-handled YUI CSS files: --> 
<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/combo?2.8.2r1/build/assets/skins/sam/skin.css"> 
<!-- Combo-handled YUI JS files: --> 
<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/animation/animation-min.js&2.8.2r1/build/element/element-min.js&2.8.2r1/build/button/button-min.js&2.8.2r1/build/carousel/carousel-min.js&2.8.2r1/build/layout/layout-min.js"></script>  

<!-- JQuery files (Start) -->
<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery-ui-1.8.5.custom.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.core.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.blind.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.explode.min.js"></script>

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

<script type="text/javascript" src="http://www.google.com/jsapi"></script> 
<script type="text/javascript" src="js/customPaginator/customPaginator.js"></script>
<script type="text/javascript" src="js/googleAnalytics/googleAnalytics.js"></script>
	
<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />
<link rel="stylesheet" type="text/css" href="styles/districtPage/districtPage.css">

<title>Party Strengths and Weakness</title>
<style type="text/css">
	table.CandidateElectionResultsTable{
		font-family: verdana,arial,sans-serif;
		font-size:11px;
		color:#333333;
		border-width: 1px;
		border-color: #666666;
		border-collapse: collapse;
	}
	
	#partyStrenghtsTable td
	{
	 	 padding-bottom: 13px;
	}
	
	#remaining_const_body, #new_const_body, #required_const_body ,#overView_const_body {
	    border-bottom: 1px solid #E0E0D6;
	    border-left: 1px solid #E0E0D6;
	    border-right: 1px solid #E0E0D6;
	  
	    overflow: auto;
	}
	
	#headingDiv
	{
		color: #247CD4;
	    font-size: 19px;
	    font-weight: bold;
	    text-align: center;
	    padding-bottom: 29px;
    	padding-top: 22px;
	}
	.tdStyle
	{
		font-weight:bold;
		color:#808080;
		font-size:12px;
	}
</style>

<script type="text/javascript">

	var electionType='Assembly';
	var selectedStateElmts=1;
	
	function callAjax(jsObj,url){
	var results;	
	var callback = {			
	    success : function( o ) {
			try {							
				"",					
					results = YAHOO.lang.JSON.parse(o.responseText);		
					if(jsObj.task == "getStatesAjaxAction")
					{					
						buildStates(results);					
					}
					if(jsObj.task == "getDefaultDetails")
					{	
						
						var main_container_div_elmt = document.getElementById("main_container_div");						
						main_container_div_elmt.style.display = 'block';

						var elmt = document.getElementById("busyImage");
						elmt.style.display = 'none';
						
						if(results.requiredConstituenciesInfo.partiesStrengthsInfoVO ==null){
							showRequiredConstituenciesErrorMessage();
						}else{
							buildDefaultDetails(results);
							buildOverViewDataTable(results); 
							if(results.partyName=="All Parties"){
								initializeResultsTable(results,"dataTableId","dataTableMainDiv","others");
								initializeTable(results);
							}else{
								initializeResultsTable2(results,"dataTableId","dataTableMainDiv");
								initializeTable(results);
							}
						}
							
						if(results.latestConstituenciesInfo.partiesStrengthsInfoVO==null){
							hideLatestConstituenciesDiv();
						}else{
							buildDefaultDetailsForNewConstituencies(results);
							if(results.partyName=="All Parties"){
								initializeResultsTable(results,"dataTableId_latestConstituencies","dataTableMainDiv_latestConstituencies","without_others");
							}else{
								initializeResultsTable2(results,"dataTableId_latestConstituencies","dataTableMainDiv_latestConstituencies");
							}
						}
						
						if(results.remainingConstituenciesInfo.partiesStrengthsInfoVO==null){
							hideRemainingConstituenciesDiv();
						}else{
							buildDefaultDetailsForRemianingConstituencies(results);
							if(results.partyName=="All Parties"){
								initializeResultsTable(results,"dataTableId_remainingConstituencies","dataTableMainDiv_remainingConstituencies","without_others");
							}else{
								initializeResultsTable2(results,"dataTableId_remainingConstituencies","dataTableMainDiv_remainingConstituencies");
							}	
						}																		
					}
					if(jsObj.task == "getAllElectionsAjaxAction")
					{					
						buildElectionYears(results);			
					}
					if(jsObj.task == "getAllPartiesData")
					{										
						buildAllPartiesData(results);			
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

	function buildOverViewDataTable(results)
	{

		var headerElmt = document.getElementById("messageDiv");
		var tempStr ='';
		tempStr+='Winning Position of different Parties in the last '+results.selectedYearsCount+' election years';
		headerElmt.innerHTML = tempStr;

		
		var elmt = document.getElementById("overViewDiv");
		var str='';
		var partiesDetails = results.allPartiesDetails.partiesDetailsVO;
		str += '<table id="overView_Table">';
		for(var k=0;k<partiesDetails.length;k++){
			str += '	<tr>';	
				str += '		<td>'+partiesDetails[k].partyName+'</td>';
				for(var j=0;j<partiesDetails[k].partyDetails.length;j++){
					str += '	<td>'+partiesDetails[k].partyDetails[j].name+'</td>';
				}
			str += '	</tr>';
		}
		
		str += '</table>';
		
		elmt.innerHTML =  str;
		
	} 

	
	function initializeTable(results){

		var partiesArray = new Array();
		var columnDataArray = new Array();
		
		var resultsObj = {
			key: "partyName"							
		};
		partiesArray.push(resultsObj);
		
		var columnObj ={
			key : "partyName",
			label : "Party Name",
			sortable : true,
			resizeable:true
		};
		columnDataArray.push(columnObj);
		var j=0;
		for(var i=0;i<results.selectedYearsCount;i++){
			j = i+1;
			var resultsObj = {
					key: j+""							
			};
			partiesArray.push(resultsObj);

			var allColumnObj ={
				key : j+"",
				label : j+"",
				sortable : true,
				resizeable:true
			};
			columnDataArray.push(allColumnObj);
		}
			
		var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
				.get("overView_Table"));
		resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;

		resultsDataSource.responseSchema = {
			fields : partiesArray
		};
	
		var resultsColumnDefs = columnDataArray;
		
		var paginatorConfig = {
	    paginator : new YAHOO.widget.Paginator({
	        rowsPerPage: 10
	    })
		};
				
		var myDataTable = new YAHOO.widget.DataTable("overViewDiv",resultsColumnDefs, resultsDataSource,paginatorConfig);  
	}	
	
	function showRequiredConstituenciesErrorMessage(){

		var elmt = document.getElementById("dataTableBuild");
		elmt.innerHTML = "";
		
		var str='';
		str+='<b style="color:red;"> No Constituencies Were present for matching the Criteria </b>';
		elmt.innerHTML = str;
	}

	function hideLatestConstituenciesDiv(){
		var elmt = document.getElementById("dataTableBuildForNewConstituencies");
		var str='';	
		elmt.innerHTML = str;

		var elmt2 = document.getElementById("new_const_main");
		elmt2.innerHTML = str;
	}

	function hideRemainingConstituenciesDiv(){
		var elmt = document.getElementById("dataTableBuildForRemainingConstituencies");
		var str='';	
		elmt.innerHTML = str;

		var elmt2 = document.getElementById("remaining_const_main");
		elmt2.innerHTML = str;
	}
	
	function initializeResultsTable(results,tableId,divId,type) {

		var partiesArray = new Array();
		var columnDataArray = new Array();
		var allParties = results.allPartiesDetails.allPartiesData;

		var resultsObj = {
			key: "constituencyName"							
		};
		partiesArray.push(resultsObj);
		
		var columnObj ={
			key : "constituencyName",
			label : "Constituency Name",
			sortable : true,
			resizeable:true
		};
		columnDataArray.push(columnObj);
		
		for(var i=0;i<allParties.length;i++){
			var resultsObj = {
					key: allParties[i].name							
			};
			partiesArray.push(resultsObj);

			var allColumnObj ={
				key : allParties[i].name,
				label : allParties[i].name,
				sortable : true,
				resizeable:true
			};
			columnDataArray.push(allColumnObj);
		}

		if(type=="others"){
			var resultsWithOthers = {
				key: "others"							
			};
			partiesArray.push(resultsWithOthers);
			
			var columnObjWithOthers ={
				key : "others",
				label : "Others",
				sortable : true,
				resizeable:true
			};
			columnDataArray.push(columnObjWithOthers);
		}
		
		var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
				.get(tableId));
		resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;

		resultsDataSource.responseSchema = {
			fields : partiesArray
		};
	
		var resultsColumnDefs = columnDataArray;
		
		var paginatorConfig = {
	    paginator : new YAHOO.widget.Paginator({
	        rowsPerPage: 10
	    })
		};
		
		var myDataTable = new YAHOO.widget.DataTable(divId,resultsColumnDefs, resultsDataSource,paginatorConfig);  
	}

	
	
	function initializeResultsTable2(results,tableId,divId) {
		var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
				.get(tableId));
		resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
		resultsDataSource.responseSchema = {
			fields : [ {
				key : "constituencyName"
			}, {
				key : "count"
			}]
		};
	
		var resultsColumnDefs = [ {
			key : "constituencyName",
			label : "Constituency Name",
			sortable : true,
			 resizeable:true
		}, {
			key : "count",
			label : "Won Seats",
			sortable : true,
			 resizeable:true
		}];
		
		var paginatorConfig = {
	    paginator : new YAHOO.widget.Paginator({
	        rowsPerPage: 10
	    })
		};
		
		var myDataTable = new YAHOO.widget.DataTable(divId,resultsColumnDefs, resultsDataSource,paginatorConfig);  
	}

	
	function getStates(selectedElmt)
	{
		electionType = selectedElmt;
		var jsObj=
		{		
				electionType :selectedElmt,		
				task:"getStatesAjaxAction"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getStatesAjaxAction.action?"+rparam;						
		callAjax(jsObj,url);
	}

	function getFrequencyOfYears(selectedState)
	{		
		selectedStateElmts = selectedState;
		var jsObj=
		{		
				stateId : selectedState,
				electionType :electionType,		
				task:"getAllElectionsAjaxAction"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/getAllElectionsAjaxAction.action?"+rparam;						
		callAjax(jsObj,url);
	}

	function getDefaultFrequencyOfYears()
	{	
		var jsObj=
		{		
				stateId : 1,
				electionType : 'Assembly',		
				task:"getAllElectionsAjaxAction"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/getAllElectionsAjaxAction.action?"+rparam;						
		callAjax(jsObj,url);
	}
	
	function getParties()
	{
		var jsObj=
		{		
				stateId : selectedStateElmts,
				electionType :electionType,		
				task:"getAllPartiesData"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/getAllPartiesMatchingCriteria.action?"+rparam;						
		callAjax(jsObj,url);
	}

	function getDefaultParties()
	{
		var jsObj=
		{		
				stateId : 1,
				electionType :'Assembly',		
				task:"getAllPartiesData"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/getAllPartiesMatchingCriteria.action?"+rparam;						
		callAjax(jsObj,url);
	}
	
	function buildAllPartiesData(results)
	{		
		var allPartiesData = document.getElementById("partySelectTd");
		
		var populateAllPartiesData='';
		populateAllPartiesData+='<select id="partySelect" style="width:130px;" onClick="return validateAndForwardToAction(this)">';
		populateAllPartiesData+='<option value="0">All Parties</option>';
		for(var i in results)
		{
			populateAllPartiesData+='<option value="'+results[i].id+'">'+results[i].name+'</option>';
		}
		populateAllPartiesData+='</select>';
		allPartiesData.innerHTML = populateAllPartiesData;
	}
	
	
	function buildElectionYears(results)
	{		 		
		var showElections = document.getElementById("electionTypeTd");
		
		var populateElections='';
		populateElections+='<select id="electionYearsSelect" style="width:50px;" onchange="getParties()">';
		
		for(var i in results)  
		{
			if(results[i].id==7){
				populateElections+='<option value="'+results[i].id+'" selected="selected">'+results[i].name+'</option>';
			}else{
				populateElections+='<option value="'+results[i].id+'">'+results[i].name+'</option>';
			}
			
		}
		populateElections+='</select>';
		showElections.innerHTML = populateElections;
	}

	
	
	function buildStates(results)
	{			
		var showStates = document.getElementById("stateTd");
		var populateStates='';
		populateStates+='<select id="stateSelect" style="width:130px;" onchange="getFrequencyOfYears(this.options[this.selectedIndex].value)">';
		for(var i in results)
		{
			populateStates+='<option value="'+results[i].id+'">'+results[i].name+'</option>';
		}
		populateStates+='</select>';
		showStates.innerHTML = populateStates;
	}

	function buildDefaultDetails(results)
	{
		var dataTable = document.getElementById("dataTableBuild");

		var data = results.requiredConstituenciesInfo.partiesStrengthsInfoVO;

		var partiesData = results.requiredConstituenciesInfo;

		var str='';
		//str+='<b style="color:green;font-weight:bold;font-size:12px;"> Required Constituencies Details </b>';
		str+='<div id="dataTableMainDiv">';
		str+='	<table id="dataTableId">';
		
		for(var i =0;i<data.length;i++)
		{			
			str+='	<tr>';
			str+='		<td>'+data[i].constituencyName+'</td>';
			for(var j =0;j<data[i].partyResults.length;j++)
			{
				str+='	<td>'+data[i].partyResults[j].count+'</td>';
			}
			str+='	</tr>'
		}
		str+='	</table>';
		str+='</div>';
		
		dataTable.innerHTML = str;

		var count = document.getElementById("requiredConstituenciesCount");
		var countElmt = '';
		countElmt+='<span><a style="color:green;font-weight:bold;font-size:12px;" href="javascript:{}" title="click here to hide and show the table" onclick="hideOrShow(\'required_const_body\')"> Constituencies Present in the last '+ results.selectedYearsCount +' election years</a></span>';
		countElmt+='<b style="font-weight:bold;color:red;font-size:12px;"> : '+results.requiredConstituenciesInfo.totalNumberOfConstituencies+'</b>';
		count.innerHTML = countElmt;
	}

	function buildDefaultDetailsForNewConstituencies(results)
	{
		var dataTable = document.getElementById("dataTableBuildForNewConstituencies");

		var data = results.latestConstituenciesInfo.partiesStrengthsInfoVO;

		var partiesData = results.latestConstituenciesInfo;

		var str='';
		//str+='<b style="color:green;font-weight:bold;font-size:12px;"> NewConstituencies Details </b>';
		str+='<div id="dataTableMainDiv_latestConstituencies">';
		str+='	<table id="dataTableId_latestConstituencies">';
		
		for(var i =0;i<data.length;i++)
		{			
			str+='	<tr>';
			str+='		<td>'+data[i].constituencyName+'</td>';
			for(var j =0;j<data[i].partyResults.length;j++)
			{
				str+='	<td>'+data[i].partyResults[j].count+'</td>';
			}
			str+='	</tr>'
		}
		str+='	</table>';
		str+='</div>';
		
		dataTable.innerHTML = str;

		var count = document.getElementById("newConstituenciesCount");
		var countElmt = '';
		countElmt+='<span><a style="color:green;font-weight:bold;font-size:12px;" href="javascript:{}" title="click here to hide and show the table" onclick="hideOrShow(\'new_const_body\')" > New Constituencies for 2009 election</a></span>';
		countElmt+='<b style="font-weight:bold;color:red;font-size:12px;"> : '+results.latestConstituenciesInfo.totalNumberOfConstituencies+'</b>';
		count.innerHTML = countElmt;
	}

	function buildDefaultDetailsForRemianingConstituencies(results)
	{
		var dataTable = document.getElementById("dataTableBuildForRemainingConstituencies");
		
		var data = results.remainingConstituenciesInfo.partiesStrengthsInfoVO;

		var partiesData = results.remainingConstituenciesInfo;

		var str='';
		//str+='<b style="color:green;font-weight:bold;font-size:12px;"> Remaining Constituencies Details </b>';
		str+='<div id="dataTableMainDiv_remainingConstituencies">';
		str+='	<table id="dataTableId_remainingConstituencies">';
		
		for(var i =0;i<data.length;i++)
		{			
			str+='	<tr>';
			str+='		<td>'+data[i].constituencyName+'</td>';
			for(var j =0;j<data[i].partyResults.length;j++)
			{
				str+='	<td>'+data[i].partyResults[j].count+'</td>';
			}
			str+='	</tr>'
		}
		str+='	</table>';
		str+='</div>';
		
		dataTable.innerHTML = str;

		var count = document.getElementById("delimitationConstituenciesCount");
		var countElmt = '';
		countElmt+='<span><a style="color:green;font-weight:bold;font-size:12px;" title="click here to hide and show the table" href="javascript:{}" onclick="hideOrShow(\'remaining_const_body\')"> Delimitation Constituencies Details</a>  </span> ';
		countElmt+='<b style="font-weight:bold;color:red;font-size:12px;"> : '+results.remainingConstituenciesInfo.totalNumberOfConstituencies+'</b>';
		count.innerHTML = countElmt;
	}
	
	function populateDefaultDetails()
	{
		var jsObj=
		{	
				stateId : '1',
				electionType : 'Assembly',	
				electionYears : '7',
				party : 0,
				task:"getDefaultDetails"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "populateDefaultDetailsAjaxAction.action?"+rparam;						
		callAjax(jsObj,url);
	}

	function validateAndForwardToAction(elmt)
	{		
		var electionTypeSelect = document.getElementById("electionTypeSelect").value;
		var stateSelect = document.getElementById("stateSelect").value;
		var electionYearsSelect = document.getElementById("electionYearsSelect").value;
		var partySelect = document.getElementById("partySelect").value;
				
		getElectionDetailsForSelectedCriteria(electionTypeSelect,stateSelect,electionYearsSelect,partySelect);	
	}

	function getElectionDetailsForSelectedCriteria(electionTypeSelect,stateSelect,electionYearsSelect,partySelect)
	{
		var elmt = document.getElementById("busyImage");
		elmt.style.display = 'block';		
		var jsObj=
		{	
				stateId : stateSelect,
				electionType : electionTypeSelect,	
				electionYears : electionYearsSelect,
				party : partySelect,				
				task:"getDefaultDetails"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "populateDefaultDetailsAjaxAction.action?"+rparam;						
		callAjax(jsObj,url);
	}

	function hideOrShow(divId)
	{
		var elmet = document.getElementById(divId);

		if(elmet.style.display == 'none')
			elmet.style.display = 'block';
		else
			elmet.style.display = 'none';
	}
	
</script>


</head>
<body>
		<div id="main_div">
			<div id="header_div" style="margin-top: 24px;">
				<table>
					<tr>
						<td colspan="2" id="headingDiv">
							<span style="margin: 0px; text-align: center;">Party Strengths and Weakness</span>
						</td>
					</tr>
				</table>
				<table class="partyStrengthsTable">
					<tr>
						<td colspan="2">
							<div style="color: red;font-weight:bold;" id="errorMessageDiv">
								<s:actionerror />
								<s:fielderror />
								<s:actionmessage/>						
							</div>
						</td>
					</tr>
				</table>
				<s:form name="partyStrengthResultsAction" action="partyStrengthResultsAction" method="POST">
					<table id="partyStrenghtsTable" width="97%;">						
						<tr>
							<td align="left" class="tdStyle">Election Type</td>
							<td align="left">
								<select id="electionTypeSelect" name="electionType"  style="width:130px;"  onchange="getStates(this.options[this.selectedIndex].value)">
									<option value="Assembly">Assembly</option>		
									<option value="Parliament">Parliament</option>									
								</select>
							</td>
						
							<td align="left" id="selectStateId" class="tdStyle">Select State</td>
							<td align="left" id="stateTd">
								<select id="stateSelect" name="state" class = "selectWidth">																
										<option value="1">Andhra Pradesh</option>									
								</select>
							</td>
						
							<td align="left" class="tdStyle">No.of Previous Election Years</td>
							<td align="left" id="electionTypeTd">
								<select id="electionYearsSelect" name="electionYears" class = "selectWidth">																
									<option value="7">7</option>									
								</select>								
							</td>
						
							<td align="left" class="tdStyle">Select Party</td>
							<td align="left" id="partySelectTd">												
								<select id="partySelect" name="party" class = "selectWidth">																
									<option value="0">All Party</option>									
								</select>
							</td>
						</tr>
						
						<tr>
							<td colspan="8">
								<hr/>
							</td>
						</tr>
						
					</table>
				</s:form>				
			</div>	
			<table>
				<tr>
		 			<td align="left">
		 				<div id="errorMessage"></div>
		 			</td>
		 		</tr> 
			</table>
			<div id="busyImage">
				<img id="ajaxImg" height="13" width="100" src="<%=request.getContextPath()%>/images/icons/goldAjaxLoad.gif"/>
			</div>
			
			<div id="main_overViewDiv" style="margin-left:60px;" align="left">
				<table>
					<tr>
						<td class="yui-skin-sam">
							<div id="overView_const_head">
								<table border="0" cellpadding="0" cellspacing="0" style="width:101%;">
									<tr>
										<td width="30px"><img  width="30" height="36" src="images/icons/districtPage/header_left.gif"/></td>
										<td><div class="districtPageRoundedHeaders_center" id="messageDiv"></div></td>
										<td><img width="5" height="36" src="images/icons/districtPage/header_right.gif"/></td>
									</tr>
								</table>
							</div>
							<div id="overView_const_body">
									<div id="newConstAncSpan" class="mandalNamesDiv">		
									<table>
										<tr>
											<td>
												<div id="overViewDiv" class="yui-skin-sam" align="left"></div>
											</td> 
										</tr>
									</table>
								</div>						
							</div>							
						</td>
						
					</tr>
				</table>				
			</div>
			
			
			<div id="main_container_div" style="display:none;margin-left:50px;" align="left">
			 <table>
			 	<tr>
			 		<td>
			 			<div id="required_const_main">	
							<div id="required_const_head">
								<table border="0" cellpadding="0" cellspacing="0" style="width:101%;">
									<tr>
										<td width="30px"><img  width="30" height="36" src="images/icons/districtPage/header_left.gif"/></td>
										<td><div class="districtPageRoundedHeaders_center"> <div id="requiredConstituenciesCount"></div></div></td>
										<td><img width="5" height="36" src="images/icons/districtPage/header_right.gif"/></td>
									</tr>
								</table>
							</div>
							<div id="required_const_body">
									<div id="newConstAncSpan" class="mandalNamesDiv">		
									<table>
										<tr>
											<td>
												<div id="dataTableBuild" class="yui-skin-sam" align="left"></div>
											</td> 
										</tr>
									</table>
								</div>						
							</div>
						</div>
					</td>
				</tr>
				
				<tr>
			 		<td>
			 			<div id="new_const_main">		
							<div id="new_const_head">
								<table border="0" cellpadding="0" cellspacing="0" style="width:101%;">
									<tr>
										<td width="30px"><img  width="30" height="36" src="images/icons/districtPage/header_left.gif"/></td>
										<td><div class="districtPageRoundedHeaders_center"> <div id="newConstituenciesCount"></div></div></td>
										<td><img width="5" height="36" src="images/icons/districtPage/header_right.gif"/></td>
									</tr>
								</table>
							</div>
							<div id="new_const_body">
									<div id="newConstAncSpan" class="mandalNamesDiv">		
									<table>
										<tr>
											<td>
												<div id="dataTableBuildForNewConstituencies" class="yui-skin-sam" align="left"></div>
											</td> 
										</tr>
									</table>
								</div>						
							</div>
						</div>
					</td>
				</tr>
				
				<tr>
			 		<td>
			 			<div id="remaining_const_main">	
							<div id="remaining_const_head">
								<table border="0" cellpadding="0" cellspacing="0" style="width:101%;">
									<tr>
										<td width="30px"><img  width="30" height="36" src="images/icons/districtPage/header_left.gif"/></td>
										<td><div class="districtPageRoundedHeaders_center"><div id="delimitationConstituenciesCount"></div></div></td>
										<td><img width="5" height="36" src="images/icons/districtPage/header_right.gif"/></td>
									</tr>
								</table>
							</div>
							<div id="remaining_const_body">
									<div id="newConstAncSpan" class="mandalNamesDiv">		
									<table>
										<tr>
											<td>
												<div id="dataTableBuildForRemainingConstituencies" class="yui-skin-sam" align="left"></div>
											</td> 
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
		
<script type="text/javascript">
	populateDefaultDetails();
	getStates('Assembly');
	getDefaultFrequencyOfYears();
	getDefaultParties();
</script>

</body>
</html>