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

	function initializeResultsTable() {
		var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
				.get("dataSortingTable"));
		resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
		resultsDataSource.responseSchema = {
			fields : [ {
				key : "constituencyName"
			}, {
				key : "INC"
			}, {   
				key : "TDP"
			}, {
				key : "TRS"
			}, {
				key : "CPI"
			}, {
				key : "CPM"
			}, {
				key : "AIMIM"
			}, {
				key : "BJP"
			}, {
				key : "PRP"
			}]
		};
	
		var resultsColumnDefs = [ {
			key : "constituencyName",
			label : "Constituency Name",
			sortable : true
		}, {
			key : "INC",
			label : "INC",
			sortable : true
		}, {
			key : "TDP",
			label : "TDP",
			sortable : true	
		}, {
			key : "TRS",
			label : "TRS",
			sortable : true	
		}, {
			key : "CPI",
			label : "CPI"	
		}, {
			key : "CPM",
			label : "CPM"	
		}, {
			key : "AIMIM",
			label : "AIMIM"	
		}, {
			key : "BJP",
			label : "BJP"	
		}, {
			key : "PRP",
			label : "PRP"	
		}];
		
		var paginatorConfig = {
	    paginator : new YAHOO.widget.Paginator({
	        rowsPerPage: 10
	    })
		};
		
		var myDataTable = new YAHOO.widget.DataTable("dataTableDiv",resultsColumnDefs, resultsDataSource,paginatorConfig);  
	}


	function getStates(selectedElmt)
	{
		var jsObj=
		{		
				electionType :selectedElmt,		
				task:"getStatesAjaxAction"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/getStatesAjaxAction.action?"+rparam;						
		callAjax(rparam,jsObj,url);
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
					<table id="partyStrenghtsTable" width="400px;">						
						<tr>
							<th align="left">Election Type</td>
							<td align="left">
								<select id="electionTypeSelect" name="electionType" class = "selectWidth" onchange="getStates(this.options[this.selectedIndex].value)">
									<option value="0">Select Election Type</option>									
									<option value="Assembly">Assembly</option>
									<option value="Parliament">Parliament</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="left" id="selectStateId">Select State</th>
							<td align="left">						
								<div id="showStates"></div>
							</td>
						</tr>
						<tr>
							<th align="left">Select Frequency of Years</th>
							<td align="left">
							
								<select id="electionYears" name="electionYears" class = "selectWidth">
									<option value="0">Select</option>
									<option value="7">7</option>	
									<option value="6">6</option>	
									<option value="5">5</option>
									<option value="4">4</option>
									<option value="3">3</option>
									<option value="2">2</option>
									<option value="1">1</option>									
								</select>								
							</td>
						</tr>
						<tr>
							<th align="left">Select Party</th>
							<td align="left">														
								<s:select theme="simple" name="party" id="partyList" cssClass = "selectWidth" list="partyList" headerKey="-1" headerValue="Select Party" listKey="id" listValue="name" />
							</td>
						</tr>
						<tr>
							<td> <input type="submit" value="Submit"></td>
						</tr>
					</table>
				</s:form>				
			</div>	
	<c:if test="${errorCode == '0'}">		
			<div id="data_body" class="yui-skin-sam">	
					<div id="dataTableDiv">
						<table  id="dataSortingTable" border="1">	
						<tr>
							<th> Constituency Name</th>		
							<c:forEach var="partyName" varStatus="stat" items="${partyListWithOutAll}">											
								<th> ${partyName.name} </th>
							</c:forEach>
						</tr>					
								<c:forEach var="details" varStatus="stat" items="${electionInfo.requiredConstituenciesInfo.partiesStrengthsInfoVO}">	
									<tr>
										<td>
											${details.constituencyName}
										</td>
										<c:forEach var="partyResults" varStatus="stat" items="${details.partyResults}">
											<td>
												<c:if test="${partyResults.count == '0'}">
													0
												</c:if>	
												<c:if test="${partyResults.count != '0'}">
													 ${partyResults.count}
												</c:if>	
											</td>	
										</c:forEach>
									</tr>
								</c:forEach>
						</table>	
					</div>	
			</div>
</c:if>	
		</div>
</body>
</html>