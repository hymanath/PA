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
	
	.yui-skin-sam yui-dt
	{
		font-family: verdana,arial,sans-serif;
		font-size:11px;
		color:#333333;
		border-width: 1px;
		border-color: #666666;
		border-collapse: collapse;
	}
</style>

<script type="text/javascript">

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
							initializeResultsTable(results);				
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
			sortable : true
		}, {
			key : "AIMIM",
			label : "AIMIM",
			sortable : true
		}, {
			key : "BJP",
			label : "BJP",
			sortable : true	
		}, {
			key : "CPI",
			label : "CPI",
			sortable : true	
		}, {
			key : "CPM",
			label : "CPM",
			sortable : true	
		}, {
			key : "INC",
			label : "INC",
			sortable : true	
		}, {
			key : "PRP",
			label : "PRP",
			sortable : true	
		}, {
			key : "TDP",
			label : "TDP",
			sortable : true	
		}, {
			key : "TRS",
			label : "TRS",
			sortable : true	
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

	
	function buildElectionYears(results)
	{
		var selectStateDIV = document.getElementById("selectElectionsDIV");
		var str='';
		str+='Select Election Years';
		selectStateDIV.innerHTML = str;
		
		 		
		var showElections = document.getElementById("showElections");
		var populateElections='';
		populateElections+='<select id="states" style="width:154px;">';
		for(var i in results)
		{
			populateElections+='<option value="'+results[i].id+'">'+results[i].name+'</option>';
		}
		populateElections+='</select>';
		showElections.innerHTML = populateElections;

		var selectPartyDIV = document.getElementById("selectPartyDIV");
		selectPartyDIV.style.display = 'block';
	}
	
	function buildStates(results)
	{	
		
		var showStates = document.getElementById("showStates");
		var populateStates='';
		populateStates+='<select id="tehsilParties" style="width:80px;" onchange="partyWiseCandidateDetails(this.options[this.selectedIndex].value)">';
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
		str+='<div id="dataTableMainDiv" class="yui-skin-sam">';
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
	
	function populateDefaultDetails()
	{
		var jsObj=
		{	
				stateId : '1',
				electionType : 'Assembly',	
				electionYears : '5',
				party : 0,
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
								<select id="electionTypeSelect" name="electionType" class = "selectWidth" onchange="getStates(this.options[this.selectedIndex].value)">																
									<option value="Assembly">Assembly</option>									
								</select>
							</td>
						</tr>
						<tr>
							<th align="left" id="selectStateId">Select State</th>
							<td align="left">
								<select id="stateSelect" name="stateType" class = "selectWidth">																
										<option value="Andhra Pradesh">Andhra Pradesh</option>									
								</select>
							</td>
						</tr>
						<tr>
							<th align="left">Select Frequency of Years</th>
							<td align="left">
								<select id="electionTypeSelect" name="electionType" class = "selectWidth" onchange="getStates(this.options[this.selectedIndex].value)">																
									<option value="5">5</option>									
								</select>								
							</td>
						</tr>
						<tr>
							<th align="left">Select Party</th>
							<td align="left">														
								<select id="electionTypeSelect" name="electionType" class = "selectWidth" onchange="getStates(this.options[this.selectedIndex].value)">																
									<option value="0">All Party</option>									
								</select>
							</td>
						</tr>
						<tr>
							<td> <input type="submit" value="Submit"></td>
						</tr>
					</table>
				</s:form>				
			</div>	
			<div id="dataTableBuild">
			</div>
		</div>
		
<script type="text/javascript">
	populateDefaultDetails();
</script>

</body>
</html>