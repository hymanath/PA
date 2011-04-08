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
		var tabOneContent = '';
		tabOneContent+='<div id="div1">';
		tabOneContent+='<div id="dTTableDiv1" align="center" class="yui-skin-sam"></div>';
		tabOneContent+='</div>';
		var tabTwoContent = '';
		tabTwoContent+='<div id="partyVoters">';
		tabTwoContent+='<div id="dTTableDiv2" align="center" class="yui-skin-sam"></div>';
		tabTwoContent+='</div>';
		var tabThreeContent = '';
		tabThreeContent+='<div id="div3">';
		tabThreeContent+='<div id="dTTableDiv3" align="center" class="yui-skin-sam"></div>';
		tabThreeContent+='</div>';
		var tabFourContent = '';
		tabFourContent+='<div id="div4">';
		tabFourContent+='<div id="dTTableDiv4" align="center" class="yui-skin-sam"></div>';
		tabFourContent+='</div>';
		
	
		myTabs.addTab( new YAHOO.widget.Tab({
		    label: 'Hamlet wise Voters',
		    content: tabOneContent,
		    active: true
		}));
		 
		myTabs.addTab( new YAHOO.widget.Tab({
		    label: 'Elections Voters in Revenue Villages',
		    content: tabTwoContent
		}));
		 
		myTabs.addTab( new YAHOO.widget.Tab({
		    label: 'Cast wise Voters in Revenue Villages',
		    content: tabThreeContent
		}));
		 
		myTabs.addTab( new YAHOO.widget.Tab({
		    label: 'Age wise Voters in Revenue Villages',
		    content: tabFourContent
		}));
		
		myTabs.appendTo('revenueVillagePageTab');
				
	}

	function electionsInRevenueVillageDatatable()
	{
		var votersElmt = document.getElementById("partyVoters");
		var content='';
		content+='<table class="ConstituencyElectionsTable" >';
		content+='<c:set var="headerData1" value="${partyElectionVotersHeaderDataVO}"/>';
		content+='<tr>';
		content+='<th><c:out value="Party"/></th>';
		content+='<c:forEach var="header1" items="${headerData1.header}" varStatus="status">';
		content+='<th><c:out value="${header1}"/></th>';
		content+='</c:forEach>';
		content+='</tr>';
		content+='<c:forEach var="data1" items="${headerData1.data}" varStatus="status">';
		content+='<tr>';
		content+='<td><c:out value="${data1.partyName}"/></td>';
		content+='<c:forEach var="partyElectionVoter" items="${data1.partyElectionVotersList1}">';
		content+='<td><c:out value="${partyElectionVoter}"/></td>';
		content+='</c:forEach>';
		content+='</tr>';		
		content+='</c:forEach>';
		content+='</table>';

		votersElmt.innerHTML=content;
	}
	function buildRevenueVillageBoothVoters()
	{
		var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom.get("revenueVillageTable"));
		resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
		resultsDataSource.responseSchema = { fields : [{ key : "hamletName" }, { key : "boothPartNos" }, { key : "totalVoters",parser:"number" }] };
		var resultsColumnDefs = [ { key : "hamletName", label : "Hamlet Name", sortable : true }, 
								  { key : "boothPartNos", label : "Booth Part Nos", sortable : true }, 
								  { key : "totalVoters", parser:"number", label : "Total Voters", sortable : true } ];
		var myConfigs = { paginator : new YAHOO.widget.Paginator({ rowsPerPage: 20}) };
		var myDataTable = new YAHOO.widget.DataTable("dTTableDiv1",resultsColumnDefs, resultsDataSource,myConfigs);  
	}

	function buildCastVotersDataTable()
	{
		var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
			.get("revenueVillageCastVotersTable"));
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
		var myDataTable = new YAHOO.widget.DataTable("dTTableDiv3",resultsColumnDefs, resultsDataSource,myConfigs);  

	}

	function buildAgeWiseVotersDataTable()
	{
		var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
			.get("revenueVillageGenderAgeVotersTable"));
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
		var myDataTable = new YAHOO.widget.DataTable("dTTableDiv4",resultsColumnDefs, resultsDataSource,myConfigs);  

	}
window.history.forward(1);
	</script>
</head>
<body>
<div id="revenueVillageInfo">
		<div><h4><u>Revenue Village <c:out value="${revenueVillageName}"/></u></h4></div>
	<div id="revenueVillagePageTab" class="yui-skin-sam"></div>
	<div id="partyVotersDiv"  class="yui-skin-sam" style="display: none;">
		<div id="mandalPartyVotersDivHead"><h4><u>Revenue Village Party Election Voters</u></h4></div>
		<table class="ConstituencyElectionsTable" >	
		<c:set var="headerData1" value="${partyElectionVotersHeaderDataVO}"/>
		<tr>
			<th><c:out value="Party"/></th>
			<c:forEach var="header1" items="${headerData1.header}" varStatus="status">
				<th><c:out value="${header1}"/></th>
			</c:forEach>
		</tr>
		<c:forEach var="data1" items="${headerData1.data}" varStatus="status">
			<tr>
				<td><c:out value="${data1.partyName}"/></td>
				<c:forEach var="partyElectionVoter" items="${data1.partyElectionVotersList1}">
					<td><c:out value="${partyElectionVoter}"/></td>
				</c:forEach>			
			</tr>		
		</c:forEach>		
		</table>	
	</div>
	
	<!--div id="revenueVillageDiv"-->
	<div id="revenueVillageDiv"  class="yui-skin-sam" style="display: none;">
		<div id="revenueVillageDivHead"><h4><u>Revenue Village Details.</u></h4></div>
		<div id="revenueVillageDivBody" class="yui-skin-sam">
			<display:table id="revenueVillageTable" class="searchresultsTable"
			 name="${hamletsListWithBoothsAndVotersVO.hamletsListWithBoothsAndVoters}"
			defaultorder="ascending" defaultsort="1"
			style="width:auto;margin-right:20px;">
				<display:column style="text-align: left;" title="Hamlet Name"
					property="hamletNameURL" sortable="true" />
				<display:column style="text-align: left;" title="Booth Part Nos"
					property="boothPartNos" sortable="true" />
				<display:column style="text-align: left;" title="Total Voters"
					property="totalVoters" sortable="true" />
			</display:table>
		</div>
	</div>
	
	
	<div id="revenueVillageCastVotersDiv"  class="yui-skin-sam" style="display: none;">
		<div id="revenueVillageCastVotersDivHead"><h4><u>Cast wise Voters</u></h4></div>
		<div id="revenueVillageCastVotersDivBody" class="yui-skin-sam">
			<display:table id="revenueVillageCastVotersTable" class="searchresultsTable"
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
	
	
	<div id="revenueVillageGenderAgeVotersDiv" class="yui-skin-sam" style="display: none;">
		<div id="revenueVillageGenderAgeVotersDivHead"><h4><u>Age wise Voters</u></h4></div>
		<div id="revenueVillageGenderAgeVotersDivBody" class="yui-skin-sam">
			<display:table class="searchresultsTable" name="${genderAgeWiseVoters.voterAgeRangeVOList}" 
			id="revenueVillageGenderAgeVotersTable" style="width:auto;margin-right:20px;">
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
	buildRevenueVillageBoothVoters();
	electionsInRevenueVillageDatatable();
	buildCastVotersDataTable();
	buildAgeWiseVotersDataTable();
</script>
</body>
</html>