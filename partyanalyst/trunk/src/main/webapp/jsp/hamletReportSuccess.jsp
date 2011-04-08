<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<html>
<head>
<!--CSS file (default YUI Sam Skin) --> 
	<link type="text/css" rel="stylesheet" href="http://yui.yahooapis.com/2.8.0r4/build/datatable/assets/skins/sam/datatable.css"> 	 
	<!-- Dependencies --> 
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/element/element-min.js"></script> 
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/datasource/datasource-min.js"></script> 
	 
	<!-- OPTIONAL: JSON Utility (for DataSource) --> 
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/json/json-min.js"></script> 
	 
	<!-- OPTIONAL: Connection Manager (enables XHR for DataSource) --> 
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/connection/connection-min.js"></script> 
	 
	<!-- OPTIONAL: Get Utility (enables dynamic script nodes for DataSource) --> 
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/get/get-min.js"></script> 
	 
	<!-- OPTIONAL: Drag Drop (enables resizeable or reorderable columns) --> 
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/dragdrop/dragdrop-min.js"></script> 
	 
	<!-- OPTIONAL: Calendar (enables calendar editors) --> 
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/calendar/calendar-min.js"></script> 
 
	<!-- Source files --> 
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/datatable/datatable-min.js"></script> 
	<!--  dependencies for tab view -->
	<!-- Sam Skin CSS for TabView -->
<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.8.0r4/build/tabview/assets/skins/sam/tabview.css">
 
<!-- JavaScript Dependencies for Tabview: -->
<script src="http://yui.yahooapis.com/2.8.0r4/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script src="http://yui.yahooapis.com/2.8.0r4/build/element/element-min.js"></script>
 
<!-- OPTIONAL: Connection (required for dynamic loading of data) -->
<script src="http://yui.yahooapis.com/2.8.0r4/build/connection/connection-min.js"></script>
 
<!-- Source file for TabView -->
<script src="http://yui.yahooapis.com/2.8.0r4/build/tabview/tabview-min.js"></script>
<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>	
<script type="text/javascript">

	function buildTabNavigator(){
		var myTabs = new YAHOO.widget.TabView();
		
		var tab1content = '';
		tab1content+='<div id="partyVoters">';
		tab1content+='<div id="dTTableDiv1" align="center" class="yui-skin-sam"></div>';
		tab1content+='</div>';
		
		var tab2content = '';
		tab2content+='<div id="partyVoters">';
		tab2content+='<div id="dTTableDiv2" align="center" class="yui-skin-sam"></div>';
		tab2content+='</div>';
		
		
		myTabs.addTab( new YAHOO.widget.Tab({
		    label: 'Cast wise Voters in Hamlet',
		    content: tab1content,
			active: true
		}));
		 
		myTabs.addTab( new YAHOO.widget.Tab({
		    label: 'Age wise Voters in Hamlet',
		    content: tab2content
		}));
		
		myTabs.appendTo('hamletPageTab');
				
	}

	function buildCastVotersDataTable()
	{
		var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
			.get("hamletCastVotersTable"));
		resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
		resultsDataSource.responseSchema = {
			fields : [ {
				key : "casteName"
			}, {
				key : "totalVoters",parser:"number"
			}, {
				key : "voterPercentage", parser:YAHOO.util.DataSourceBase.parseNumber
			} ]
		};
	
		var resultsColumnDefs = [ {
			key : "casteName",		
			label : "Cast",
			sortable : true
		}, {
			key : "totalVoters",
			parser:"number",
			label : "Total Voters",
			sortable : true
		}, {
			key : "voterPercentage",
			label : "Percentage",formatter:YAHOO.widget.DataTable.formatFloat,
			sortable : true
		} ];
		var myConfigs = {
			paginator : new YAHOO.widget.Paginator({
				rowsPerPage: 20
			})
		};
		var myDataTable = new YAHOO.widget.DataTable("dTTableDiv1",resultsColumnDefs, resultsDataSource,myConfigs);  

	}

	function buildAgeWiseVotersDataTable()
	{
		var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
			.get("hamletGenderAgeVotersTable"));
		resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
		resultsDataSource.responseSchema = {
			fields : [ {
				key : "ageRange"
			},{
				key : "maleVoters",parser:"number"
			},{
				key : "femaleVoters",parser:"number"
			}, {
				key : "totalVoters",parser:"number"
			} ]
		};
	
		var resultsColumnDefs = [ {
			key : "ageRange",		
			label : "Age Range",
			sortable : true
		},{
			key : "maleVoters",	
			parser:"number",	
			label : "Male",
			sortable : true
		},{
			key : "femaleVoters",
			parser:"number",		
			label : "Female",
			sortable : true
		}, {
			key : "totalVoters",
			parser:"number",
			label : "Total",
			sortable : true
		}];
		var myConfigs = {
			paginator : new YAHOO.widget.Paginator({
				rowsPerPage: 20
			})
		};
		var myDataTable = new YAHOO.widget.DataTable("dTTableDiv2",resultsColumnDefs, resultsDataSource,myConfigs);  

	}
window.history.forward(1);
	</script>

</head>
<body>
<div><h4>Hamlet ( <c:out value="${hamletName} "/>)</h4></div>
<div id="hamletInfo">
	<div id="hamletPageTab" class="yui-skin-sam"></div>

	<div id="hamletCastVotersDiv"  class="yui-skin-sam" style="display: none;">
		<div id="hamletCastVotersDivHead"><h4><u>Cast wise Voters</u></h4></div>
		<div id="hamletCastVotersDivBody" class="yui-skin-sam">
			<display:table id="hamletCastVotersTable" class="searchresultsTable"
			  name="${castWiseElectionVoters.casteVoters}"
			defaultorder="ascending" defaultsort="2"
			style="width:auto;margin-right:20px;">
				<display:column style="text-align: left;" title="Cast"
					property="casteName" sortable="true" />
				<display:column style="text-align: left;" title="Total Voters"
					property="totalVoters" sortable="true" />
				<display:column style="text-align: left;" title="Percentage"
					property="voterPercentage" sortable="true" />
			</display:table>
		</div>
	</div>

	<div id="hamletGenderAgeVotersDiv" class="yui-skin-sam" style="display: none;">
		<div id="hamletGenderAgeVotersDivHead"><h4><u>Age wise Voters</u></h4></div>
		<div id="hamletGenderAgeVotersDivBody" class="yui-skin-sam">
			<display:table class="searchresultsTable" name="${genderAgeWiseVoters.voterAgeRangeVOList}" 
			id="hamletGenderAgeVotersTable" style="width:auto;margin-right:20px;">
				<display:column style="text-align: left;" title="Age Range"
					property="ageRange" sortable="true" />
				<display:column style="text-align: left;" title="Male"
					property="maleVoters" sortable="true" />
				<display:column style="text-align: left;" title="Female"
					property="femaleVoters" sortable="true" />
				<display:column style="text-align: left;" title="Total"
					property="totalVoters" sortable="true" />
			</display:table>
		</div>
	</div>

</div>
<script type="text/javascript">
	buildTabNavigator();
	buildCastVotersDataTable();
	buildAgeWiseVotersDataTable()
</script>
</body>
</html>