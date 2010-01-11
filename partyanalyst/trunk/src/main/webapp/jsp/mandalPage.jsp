<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<style type="text/css">



#VillageTable th
{
	background-color:#afafaf;
}

#boothResultsDiv {
	text-align: left;
	margin-left: 50px;
	font-size: 12px;
	margin-right:10px;
	
}
#villageCensusDivHead,#mandalCensusDiv
{
	color:#0D3A5C;
}
#villageCensusDivBody
{
	border:2px solid #A5CCFF;
}
.yui-skin-sam th.yui-dt-asc, .yui-skin-sam th.yui-dt-desc 
{
	background-image:none;
}

.yui-skin-sam thead .yui-dt-sortable {

	background-color:#C4DEFF;
	color:#3F546F;
	text-decoration:none;
}

.searchresultsTable td {
	background-color:#F8FBFF;
}
.searchresultsTable th {
	background-color:#C4DEFF;
}
.yui-skin-sam .yui-dt-liner {
	padding:4px 8px;
}
.ConstituencyElectionsTable th
{
	text-align:left;
	background-color:#C4DEFF;
}
.ConstituencyElectionsTable td
{
	text-align:right;
}

</style>



<script type="text/javascript">
	
	function buildCensusDataTable()
	{
		var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
			.get("villageCensusTable"));
	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
	resultsDataSource.responseSchema = {
		fields : [ {
			key : "townshipName"
		}, {
			key : "totalPersons",parser:"number"
		}, {
			key : "totalSCPersons",parser:"number"
		}, {
			key : "totalSTPersons",parser:"number"
		}, {
			key : "totalLiteratePersons",parser:"number"
		}, {
			key : "totalIlliteratePersons",parser:"number"
		} , {
			key : "totalWorkingPersons",parser:"number"
		} ]
	};

	var resultsColumnDefs = [ {
		key : "townshipName",		
		label : "Village Name",
		sortable : true
	}, {
		key : "totalPersons",
		parser:"number",
		label : "Total Populations",
		sortable : true
	}, {
		key : "totalSCPersons",
		parser:"number",
		label : "SC Populations",
		sortable : true
	}, {
		key : "totalSTPersons",
		parser:"number",
		label : "ST Populations",
		sortable : true
	}, {
		key : "totalLiteratePersons",
		parser:"number",
		label : "Literate Populations",
		sortable : true
	}, {
		key : "totalIlliteratePersons",
		parser:"number",
		label : "Illiterate Populations",
		sortable : true
	}, {
		key : "totalWorkingPersons",
		parser:"number",
		label : "Working Populations",
		sortable : true
	} ];

	var myDataTable = new YAHOO.widget.DataTable("villageCensusDivBody",resultsColumnDefs, resultsDataSource,{});  

	}
</script>
</head>
<body> 

<h3><u><c:out value="${mandalInfoVO.mandalName}"/> Tehsil / Mandal Details</u></h3>

<div id="boothResultsDiv">
	<div id="mandalCensusDiv">
		<div id="mandalCensusDivHead"><h4><u>Mandal Details..</u></h4></div>
		<div id="mandalCensusDivBody">
		<table class="ConstituencyElectionsTable" >		
				<tr>
					<th></th>
					<th>Population</th>
					<th>SC Population</th>
					<th>ST Population</th>
					<th>Literate Populations</th>
					<th>Illiterate Population</th>
					<th>Working Population</th>
					
				</tr>
				<tr>
					<th>Male</th>
					<td><c:out value="${mandalInfoVO.totalMalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalSCMalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalSTMalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalLiterateMalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalIlliterateMalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalWorkingMalePersons}"/></td>
				</tr>
				<tr>
					<th>Female</th>
					<td><c:out value="${mandalInfoVO.totalFemalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalSCFemalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalSTFemalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalLiterateFemalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalIlliterateFemalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalWorkingFemalePersons}"/></td>
				</tr>
				<tr>
					<th>Total</th>
					<td><c:out value="${mandalInfoVO.totalPersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalSCPersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalSTPersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalLiteratePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalIlliteratePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalWorkingPersons}"/></td>
				</tr>
				
			</table>
		</div>
	</div>
	<div id="villageCensusDiv">
		<div id="villageCensusDivHead"><h4><u>Villages Details..</u></h4></div>
		<div id="villageCensusDivBody" class="yui-skin-sam">
			<display:table id="villageCensusTable" class="searchresultsTable"
			 name="${villageDetailsVO.villageCensusList}"
			defaultorder="ascending" defaultsort="2"
			style="width:auto;margin-right:20px;">
				<display:column style="text-align: left;" title="Village Name"
					property="townshipName" sortable="true" />
				<display:column style="text-align: left;" title="Total Populations"
					property="totalPersons" sortable="true" />
				<display:column style="text-align: left;" title="SC Population"
					property="totalSCPersons" sortable="true" />
				<display:column style="text-align: center;" title="ST Population"
					property="totalSTPersons" sortable="true" />
				<display:column style="text-align: center;" title="Literate Population"
					property="totalLiteratePersons" sortable="true" />
				<display:column style="text-align: center;" title="Illiterate Population"
					property="totalIlliteratePersons"  sortable="true"/>
				<display:column style="text-align: center;" title="Working Population"
					property="totalWorkingPersons" sortable="true" />
			</display:table>
		</div>
	</div>		
</div>
<script type="text/javascript">
	buildCensusDataTable();
</script>
</body>
</html>