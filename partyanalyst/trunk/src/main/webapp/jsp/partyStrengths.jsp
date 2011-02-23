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

<title>Party Strengths</title>
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
	

</style>

<script type="text/javascript">

	var electionType;
	var selectedStateElmts;
	
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
						buildDefaultDetails(results);
						if(results.partyName=="All Parties"){
							initializeResultsTable(results);
						}else{
							initializeResultsTable2(results);
						}	
			
						buildDefaultDetailsForNewConstituencies(results);
						if(results.partyName=="All Parties"){
							initializeResultsTableForNewConstituencies(results);
						}else{
							initializeResultsTable2ForNewConstituencies(results);
						}	

						buildDefaultDetailsForRemianingConstituencies(results);
						if(results.partyName=="All Parties"){
							initializeResultsTableForRemianingConstituencies(results);
						}else{
							initializeResultsTable2ForRemianingConstituencies(results);
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
			   // 	alert("Invalid JSON result" + e);   
			}  
	    },
	    scope : this,
	    failure : function( o ) {
	     		//	alert( "Failed to load result" + o.status + " " + o.statusText);
	              }
	    };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
	}

	function initializeResultsTable(results) {
		var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
				.get("dataTableId"));
		resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
		resultsDataSource.responseSchema = {
			fields : [ {
				key : "constituencyName"
			}, {
				key : "AIMIM"
			}, {   
				key : "BJP"
			}, {
				key : "CPI"
			}, {
				key : "CPM"
			}, {
				key : "INC"
			}, {
				key : "PRP"
			}, {
				key : "TDP"
			}, {
				key : "TRS"
			}]
		};
	
		var resultsColumnDefs = [ {
			key : "constituencyName",
			label : "Constituency Name",
			sortable : true,
			 resizeable:true
		}, {
			key : "AIMIM",
			label : "AIMIM",
			sortable : true,
			 resizeable:true
		}, {
			key : "BJP",
			label : "BJP",
			sortable : true,
			 resizeable:true	
		}, {
			key : "CPI",
			label : "CPI",
			sortable : true,
			 resizeable:true
		}, {
			key : "CPM",
			label : "CPM",
			sortable : true,
			 resizeable:true	
		}, {
			key : "INC",
			label : "INC",
			sortable : true,
			 resizeable:true	
		}, {
			key : "PRP",
			label : "PRP",
			sortable : true,
			 resizeable:true	
		}, {
			key : "TDP",
			label : "TDP",
			sortable : true,
			 resizeable:true	
		}, {
			key : "TRS",
			label : "TRS",
			sortable : true,
			 resizeable:true	
		}];
		
		var paginatorConfig = {
	    paginator : new YAHOO.widget.Paginator({
	        rowsPerPage: 10
	    })
		};
		
		var myDataTable = new YAHOO.widget.DataTable("dataTableMainDiv",resultsColumnDefs, resultsDataSource,paginatorConfig);  
	}

	function initializeResultsTable2(results) {
		var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
				.get("dataTableId"));
		resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
		resultsDataSource.responseSchema = {
			fields : [ {
				key : "constituencyName"
			}, {
				key : "Won Seats"
			}]
		};
	
		var resultsColumnDefs = [ {
			key : "constituencyName",
			label : "Constituency Name",
			sortable : true,
			 resizeable:true
		}, {
			key : "Won Seats",
			label : "Won Seats",
			sortable : true,
			 resizeable:true
		}];
		
		var paginatorConfig = {
	    paginator : new YAHOO.widget.Paginator({
	        rowsPerPage: 10
	    })
		};
		
		var myDataTable = new YAHOO.widget.DataTable("dataTableMainDiv",resultsColumnDefs, resultsDataSource,paginatorConfig);  
	}


	function initializeResultsTableForRemianingConstituencies(results) {
		var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
				.get("dataTableId_remainingConstituencies"));
		resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
		resultsDataSource.responseSchema = {
			fields : [ {
				key : "constituencyName"
			}, {
				key : "AIMIM"
			}, {   
				key : "BJP"
			}, {
				key : "CPI"
			}, {
				key : "CPM"
			}, {
				key : "INC"
			}, {
				key : "PRP"
			}, {
				key : "TDP"
			}, {
				key : "TRS"
			}]
		};
	
		var resultsColumnDefs = [ {
			key : "constituencyName",
			label : "Constituency Name",
			sortable : true,
			 resizeable:true
		}, {
			key : "AIMIM",
			label : "AIMIM",
			sortable : true,
			 resizeable:true
		}, {
			key : "BJP",
			label : "BJP",
			sortable : true,
			 resizeable:true	
		}, {
			key : "CPI",
			label : "CPI",
			sortable : true,
			 resizeable:true
		}, {
			key : "CPM",
			label : "CPM",
			sortable : true,
			 resizeable:true	
		}, {
			key : "INC",
			label : "INC",
			sortable : true,
			 resizeable:true	
		}, {
			key : "PRP",
			label : "PRP",
			sortable : true,
			 resizeable:true	
		}, {
			key : "TDP",
			label : "TDP",
			sortable : true,
			 resizeable:true	
		}, {
			key : "TRS",
			label : "TRS",
			sortable : true,
			 resizeable:true	
		}];
		
		var paginatorConfig = {
	    paginator : new YAHOO.widget.Paginator({
	        rowsPerPage: 10
	    })
		};
		
		var myDataTable = new YAHOO.widget.DataTable("dataTableMainDiv_remainingConstituencies",resultsColumnDefs, resultsDataSource,paginatorConfig);  
	}

	function initializeResultsTable2ForRemianingConstituencies(results) {
		var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
				.get("dataTableId_remainingConstituencies"));
		resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
		resultsDataSource.responseSchema = {
			fields : [ {
				key : "constituencyName"
			}, {
				key : "Won Seats"
			}]
		};
	
		var resultsColumnDefs = [ {
			key : "constituencyName",
			label : "Constituency Name",
			sortable : true,
			 resizeable:true
		}, {
			key : "Won Seats",
			label : "Won Seats",
			sortable : true,
			 resizeable:true
		}];
		
		var paginatorConfig = {
	    paginator : new YAHOO.widget.Paginator({
	        rowsPerPage: 10
	    })
		};
		
		var myDataTable = new YAHOO.widget.DataTable("dataTableMainDiv_remainingConstituencies",resultsColumnDefs, resultsDataSource,paginatorConfig);  
	}

	function initializeResultsTableForNewConstituencies(results) {
		var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
				.get("dataTableId_latestConstituencies"));
		resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
		resultsDataSource.responseSchema = {
			fields : [ {
				key : "constituencyName"
			}, {
				key : "AIMIM"
			}, {   
				key : "BJP"
			}, {
				key : "CPI"
			}, {
				key : "CPM"
			}, {
				key : "INC"
			}, {
				key : "PRP"
			}, {
				key : "TDP"
			}, {
				key : "TRS"
			}]
		};
	
		var resultsColumnDefs = [ {
			key : "constituencyName",
			label : "Constituency Name",
			sortable : true,
			 resizeable:true
		}, {
			key : "AIMIM",
			label : "AIMIM",
			sortable : true,
			 resizeable:true
		}, {
			key : "BJP",
			label : "BJP",
			sortable : true,
			 resizeable:true	
		}, {
			key : "CPI",
			label : "CPI",
			sortable : true,
			 resizeable:true
		}, {
			key : "CPM",
			label : "CPM",
			sortable : true,
			 resizeable:true	
		}, {
			key : "INC",
			label : "INC",
			sortable : true,
			 resizeable:true	
		}, {
			key : "PRP",
			label : "PRP",
			sortable : true,
			 resizeable:true	
		}, {
			key : "TDP",
			label : "TDP",
			sortable : true,
			 resizeable:true	
		}, {
			key : "TRS",
			label : "TRS",
			sortable : true,
			 resizeable:true	
		}];
		
		var paginatorConfig = {
	    paginator : new YAHOO.widget.Paginator({
	        rowsPerPage: 10
	    })
		};
		
		var myDataTable = new YAHOO.widget.DataTable("dataTableMainDiv_latestConstituencies",resultsColumnDefs, resultsDataSource,paginatorConfig);  
	}

	function initializeResultsTable2ForNewConstituencies(results) {
		var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
				.get("dataTableId"));
		resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
		resultsDataSource.responseSchema = {
			fields : [ {
				key : "constituencyName"
			}, {
				key : "Won Seats"
			}]
		};
	
		var resultsColumnDefs = [ {
			key : "constituencyName",
			label : "Constituency Name",
			sortable : true,
			 resizeable:true
		}, {
			key : "Won Seats",
			label : "Won Seats",
			sortable : true,
			 resizeable:true
		}];
		
		var paginatorConfig = {
	    paginator : new YAHOO.widget.Paginator({
	        rowsPerPage: 10
	    })
		};
		
		var myDataTable = new YAHOO.widget.DataTable("dataTableMainDiv",resultsColumnDefs, resultsDataSource,paginatorConfig);  
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
	function buildAllPartiesData(results)
	{		
		var allPartiesData = document.getElementById("partySelectTd");
		
		var populateAllPartiesData='';
		populateAllPartiesData+='<select id="partySelect" style="width:130px;">';
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
		populateElections+='<select id="electionYearsSelect" style="width:130px;" onchange="getParties()">';
		for(var i in results)
		{
			populateElections+='<option value="'+results[i].id+'">'+results[i].name+'</option>';
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
		str+='Required Constituencies Details';
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
	}

	function buildDefaultDetailsForNewConstituencies(results)
	{
		var dataTable = document.getElementById("dataTableBuildForNewConstituencies");

		var data = results.latestConstituenciesInfo.partiesStrengthsInfoVO;

		var partiesData = results.latestConstituenciesInfo;

		var str='';
		str+='New Constituencies Details';
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
	}

	function buildDefaultDetailsForRemianingConstituencies(results)
	{
		var dataTable = document.getElementById("dataTableBuildForRemainingConstituencies");
		
		var data = results.remainingConstituenciesInfo.partiesStrengthsInfoVO;

		var partiesData = results.remainingConstituenciesInfo;

		var str='';
		str+='Remaining Constituencies Details';
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
</script>


</head>
<body>
		<div id="main_div">
			<div id="header_div" style="margin-top: 24px;">
				<table>
					<tr>
						<th colspan="2">
							<span style="margin: 0px; text-align: center;">Party Strengths</span>
						</th>
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
					<table id="partyStrenghtsTable" width="367px;">						
						<tr>
							<th align="left">Election Type</td>
							<td align="left">
								<select id="electionTypeSelect" name="electionType"  style="width:130px;"  onchange="getStates(this.options[this.selectedIndex].value)">
									<option value="Assembly">Assembly</option>		
									<option value="Parliament">Parliament</option>									
								</select>
							</td>
						</tr>
						<tr>
							<th align="left" id="selectStateId">Select State</th>
							<td align="left" id="stateTd">
								<select id="stateSelect" name="state" class = "selectWidth">																
										<option value="1">Andhra Pradesh</option>									
								</select>
							</td>
						</tr>
						<tr>
							<th align="left">No of Previous Election Years</th>
							<td align="left" id="electionTypeTd">
								<select id="electionYearsSelect" name="electionYears" class = "selectWidth">																
									<option value="7">7</option>									
								</select>								
							</td>
						</tr>
						<tr>
							<th align="left">Select Party</th>
							<td align="left" id="partySelectTd">												
								<select id="partySelect" name="party" class = "selectWidth">																
									<option value="0">All Party</option>									
								</select>
							</td>
						</tr>
						<tr>
							<td> 
								<input id="viewReportButton" type="button" onClick="return validateAndForwardToAction(this)"  value="View Report"/>
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
			
			<div id="dataTableBuild" class="yui-skin-sam"></div>
			<div id="dataTableBuildForNewConstituencies" class="yui-skin-sam"></div>
			<div id="dataTableBuildForRemainingConstituencies" class="yui-skin-sam"></div>
			
		</div>
		
<script type="text/javascript">
	populateDefaultDetails();
</script>

</body>
</html>