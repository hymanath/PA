<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	<link type="text/css" rel="stylesheet" href="http://yui.yahooapis.com/2.8.0r4/build/datatable/assets/skins/sam/datatable.css"> 
	<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css"> 
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
	<script src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
<script type="text/javascript">
	
	function buildRevenueVillageBoothVoters()
	{
		var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
			.get("revenueVillageTable"));
		resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
		resultsDataSource.responseSchema = {
										     fields : [{ key : "hamletName" }, { key : "boothPartNos" }, { key : "totalVoters",parser:"number" }]
											};
		var resultsColumnDefs = [ {
			key : "hamletName",		
			label : "Hamlet Name",
			sortable : true
		}, {
			key : "boothPartNos",
			label : "Booth Part Nos",
			sortable : true
		}, {
			key : "totalVoters",
			parser:"number",
			label : "Total Voters",
			sortable : true
		} ];
		var myConfigs = {
			paginator : new YAHOO.widget.Paginator({ rowsPerPage: 10})
		};
		var myDataTable = new YAHOO.widget.DataTable("revenueVillageDivBody",resultsColumnDefs, resultsDataSource,myConfigs);  
	}


	</script>
</head>
<body>
	 

	<!--div id="revenueVillageDiv"-->
	<div id="revenueVillageDiv">
		<div id="revenueVillageDivHead"><h4><u>Revenue Village Details.</u></h4></div>
		<div id="revenueVillageDivBody" class="yui-skin-sam">
			<display:table id="revenueVillageTable" class="searchresultsTable"
			 name="${hamletsListWithBoothsAndVotersVO.hamletsListWithBoothsAndVoters}"
			defaultorder="ascending" defaultsort="1"
			style="width:auto;margin-right:20px;">
				<display:column style="text-align: left;" title="Hamlet Name"
					property="hamletName" sortable="true" />
				<display:column style="text-align: left;" title="Booth Part Nos"
					property="boothPartNos" sortable="true" />
				<display:column style="text-align: left;" title="Total Voters"
					property="totalVoters" sortable="true" />
			</display:table>
		</div>
	</div>
	
<script type="text/javascript">
	buildRevenueVillageBoothVoters();
</script>
</body>
</html>