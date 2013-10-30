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

	var fieldsArray = new Array();
	var dataArray = new Array();

	
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

	var myConfigs = { 
		    paginator : new YAHOO.widget.Paginator({ 
	        rowsPerPage    : 15 
		    }) 
		};
	
	var myDataTable = new YAHOO.widget.DataTable("dTTableDiv3",resultsColumnDefs, resultsDataSource,myConfigs);  

	}

	function buildBoothDataForRevenueVillagesTable(){
		var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
				.get("boothInfo"));
		resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
		resultsDataSource.responseSchema = {
			fields : [ {
				key : "locationName"
			}, {
				key : "population",parser:"number"
			}, {
				key : "booths"
			}, {
				key : "subLocations"
			}, {
				key : "hamletsOfTownship"
			} ]
		};

		var resultsColumnDefs = [ {
			key : "locationName",		
			label : "Location Name",
			sortable : true
		}, {
			key : "population",
			parser:"number",
			label : "Total Population",
			sortable : true
		}, {
			key : "booths",
			parser:"number",
			label : "Booths",
			sortable : true
		}, {
			key : "subLocations",
			parser:"number",
			label : "Sub Locations",
			sortable : true
		}, {
			key : "hamletsOfTownship",
			parser:"number",
			label : "Hamlets Of Township",
			sortable : true
		}];

		var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 15 
			    }) 
			};
		
		var myDataTable = new YAHOO.widget.DataTable("dTTableDiv2",resultsColumnDefs, resultsDataSource,myConfigs);  		
	}

	function buildTabNavigator(){
		var myTabs = new YAHOO.widget.TabView();
		var tabOneContent = '';
		tabOneContent+='<div id="div1">';
		tabOneContent+='<div id="dTTableDiv1" align="center" class="yui-skin-sam"></div>';
		tabOneContent+='</div>';
		var tabTwoContent = '';
		tabTwoContent+='<div id="div2" class="yui-skin-sam">';
		tabTwoContent+='<div id="dTTableDiv2" align="center" class="yui-skin-sam"></div>';
		tabTwoContent+='</div>';
		var tabThreeContent = '';
		tabThreeContent+='<div id="div3" class="yui-skin-sam">';
		tabThreeContent+='<div id="dTTableDiv3" align="center" class="yui-skin-sam"></div>';
		tabThreeContent+='</div>'; 
		var tabFourContent = '';
		tabFourContent+='<div id="partyVoters"  align="center" class="yui-skin-sam">';		
		tabFourContent+='</div>';
		var tabFiveContent = '';
		tabFiveContent+='<div id="div5">';
		tabFiveContent+='<div id="dTTableDiv5" align="center" class="yui-skin-sam"></div>';
		tabFiveContent+='</div>';
		var tabSixContent = '';
		tabSixContent+='<div id="div6">';
		tabSixContent+='<div id="dTTableDiv6" align="center" class="yui-skin-sam"></div>';
		tabSixContent+='</div>';

		myTabs.addTab( new YAHOO.widget.Tab({
		    label: 'Mandal Local Leaders',
		    content: tabOneContent,
		    active: true
		}));
		 
		myTabs.addTab( new YAHOO.widget.Tab({
		    label: 'Booths in Revenue Villages',
		    content: tabTwoContent
		}));
		 
		myTabs.addTab( new YAHOO.widget.Tab({
		    label: 'Census Info in Revenue Villages',
		    content: tabThreeContent
		}));

		myTabs.addTab( new YAHOO.widget.Tab({
		    label: 'Elections In Mandal',
		    content: tabFourContent
		}));
			
		myTabs.addTab( new YAHOO.widget.Tab({
		    label: 'Cast wise Voters',
		    content: tabFiveContent
		}));
		
			
		myTabs.addTab( new YAHOO.widget.Tab({
		    label: 'Age wise Voters',
		    content: tabSixContent
		}));
		
		myTabs.appendTo('mandalPageTab');
				
	}

	function electionsInMandalDatatable()
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

	function buildLocalLeadersTable(){

		var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
				.get("localLeaders"));
		resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
		resultsDataSource.responseSchema = {
			fields : [ {
				key : "personName"
			}, {
				key : "designation"
			}, {
				key : "party"
			}, {
				key : "localArea"
			}, {
				key : "year"
			} ]
		};

		var resultsColumnDefs = [ {
			key : "personName",		
			label : "Person Name",
			sortable : true
		}, {
			key : "designation",
			label : "Designation",
			sortable : true
		}, {
			key : "party",
			label : "Party",
			sortable : true
		}, {
			key : "localArea",
			label : "Local Area",
			sortable : true
		}, {
			key : "year",
			label : "Year",
			sortable : true
		}];

		var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 15 
			    }) 
			};
		
		var myDataTable = new YAHOO.widget.DataTable("dTTableDiv1",resultsColumnDefs, resultsDataSource, myConfigs);
	}

	function buildCastVotersDataTable()
	{
		var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
			.get("mandalCastVotersTable"));
	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
	resultsDataSource.responseSchema = {
		fields : [ {
			key : "casteName"
		}, {
			key : "totalVoters",parser:"number"
		}, {
			key : "voterPercentage"
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
		label : "Percentage(%)", 
		formatter:YAHOO.widget.DataTable.formatFloat
	} ];
	var myConfigs = {
		paginator : new YAHOO.widget.Paginator({
			rowsPerPage: 20
		})
	};
	var myDataTable = new YAHOO.widget.DataTable("dTTableDiv5",resultsColumnDefs, resultsDataSource,myConfigs);  

	}

	function buildAgeWiseVotersDataTable()
	{
		var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
			.get("mandalGenderAgeVotersTable"));
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
		}, {
			key : "percentage"
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
	}, {
		key : "percentage",
		label : "Percentage(%)"
	}];
	var myConfigs = {
		paginator : new YAHOO.widget.Paginator({
			rowsPerPage: 20
		})
	};
	var myDataTable = new YAHOO.widget.DataTable("dTTableDiv6",resultsColumnDefs, resultsDataSource,myConfigs);  

	}

	</script>
</head>
<body> 

<h3><u><c:out value="${mandalInfoVO.mandalName}"/> Tehsil / Mandal Details</u></h3>

<div id="boothResultsDiv">
	<div id="mandalCensusDiv">
		<div id="mandalCensusDivHead"><h4><u>Mandal Details..</u></h4></div>
		<div id="mandalCensusDivBody" align="center" class="yui-skin-sam">
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
	<br/>
	
	<div id="mandalPageTab" class="yui-skin-sam"></div>
	<div id="villageCensusDiv" style="display: none;">
		<div id="villageCensusDivHead"><h4><u>Villages Details..</u></h4></div>
		<div id="villageCensusDivBody" class="yui-skin-sam">
			<display:table id="villageCensusTable" class="searchresultsTable"
			 name="${villageDetailsVO.villageCensusList}"
			defaultorder="ascending" defaultsort="2"
			style="width:auto;margin-right:20px;">
				<display:column style="text-align: left;" title="Village Name" 
					property="townshipNameURL" sortable="true" />
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
		
	<div id="Local Leaders" class="yui-skin-sam" style="display: none;">		
		<div id="mandalLocalLeadersHead"><h4><u>Local Leaders In Mandal</u></h4></div>
		<table class="ConstituencyElectionsTable" id ="localLeaders">	
			<c:forEach var="data1" items="${influencingPeopleInMandal}" varStatus="status">
			<tr>
				<td><c:out value="${data1.personName}"/></td>
				<td><c:out value="${data1.designation}"/></td>
				<td><c:out value="${data1.party}"/></td>
				<td><c:out value="${data1.localArea}"/></td>
				<td><c:out value="${data1.year}"/><br/></td>
			</tr>
			</c:forEach>
		</table>	
	</div>
	<div id="Township Wise Booth Details" class="yui-skin-sam" style="display: none;">		
		<div id="townshipWiseBoothDetails"><h4><u>Township Wise Booth Details</u></h4></div>
		<table class="ConstituencyElectionsTable" id ="boothInfo">	
			<c:forEach var="data1" items="${townshipWiseBoothDataInMandal}" varStatus="status">
				<c:url value="revenueVillageReport.action" var="displayRVURL">
							<c:param name="revenueVillageID"   value="${data1.locationId}" />
							<c:param name="revenueVillageName"   value="${data1.locationName}" />
						</c:url>				
						
			<tr>
				<td>
					<a href='<c:out value="${displayRVURL}" />'> 
							<c:out value="${data1.locationName}"/>
					</a>
				</td>
				<td><c:out value="${data1.population}"/></td>
				<td>
					<c:forEach var="booth" items="${data1.booths}">
						<c:out value="${booth}"/><br/>
					</c:forEach>
				</td>
				<td>
					<c:forEach var="hamlet" items="${data1.subLocations}">
						<c:out value="${hamlet}"/><br/>
					</c:forEach>
				</td>
				<td>
					<c:forEach var="hamlet" items="${data1.hamletsOfTownship}">
						<c:out value="${hamlet}"/><br/>
					</c:forEach>
				</td>
			</tr>
			</c:forEach>
		</table>	
	</div>
	
	<div id="mandalCastVotersDiv"  class="yui-skin-sam" style="display: none;">
		<div id="mandalCastVotersDivHead"><h4><u>Cast wise Voters</u></h4></div>
		<div id="mandalCastVotersDivBody" class="yui-skin-sam">
			<display:table id="mandalCastVotersTable" class="searchresultsTable"
			  name="${castWiseElectionVoters.casteVoters}"
			defaultorder="ascending" defaultsort="2"
			style="width:auto;margin-right:20px;">
				<display:column style="text-align: left;" title="Cast"
					property="casteName" sortable="true" />
				<display:column style="text-align: left;" title="Total Voters"
					property="totalVoters" sortable="true" />
				<display:column style="text-align: right;" title="Percentage"
					property="voterPercentage" sortable="true" />
			</display:table>
		</div>
	</div>

	<div id="mandalGenderAgeVotersDiv" class="yui-skin-sam" style="display: none;">
		<div id="mandalGenderAgeVotersDivHead"><h4><u>Age wise Voters</u></h4></div>
		<div id="mandalGenderAgeVotersDivBody" class="yui-skin-sam">
			<display:table class="searchresultsTable" name="${genderAgeWiseVoters.voterAgeRangeVOList}" 
			id="mandalGenderAgeVotersTable" style="width:auto;margin-right:20px;">
				<display:column style="text-align: left;" title="Age Range"
					property="ageRange" sortable="true" />
				<display:column style="text-align: left;" title="Male"
					property="maleVoters" sortable="true" />
				<display:column style="text-align: left;" title="Female"
					property="femaleVoters" sortable="true" />
				<display:column style="text-align: left;" title="Total"
					property="totalVoters" sortable="true" />
				<display:column style="text-align: left;" title="Percentage"
					 property="percentage" />
			</display:table>
		</div>
	</div>

</div>
<script type="text/javascript">
	buildTabNavigator();
</script>
<script type="text/javascript">
	buildCensusDataTable();
	buildCastVotersDataTable();
	buildAgeWiseVotersDataTable();
</script>
<script type="text/javascript">
	buildLocalLeadersTable();
	fieldsArray.push('Party');
	<c:forEach var="header1" items="${headerData1.header}" varStatus="status">
			fieldsArray.push('${header1}');
	</c:forEach>

	<c:forEach var="data1" items="${headerData1.data}" varStatus="status">	
			dataArray.push('${data1.partyName}');			
		<c:forEach var="partyElectionVoter" items="${data1.partyElectionVotersList1}">
			dataArray.push('${partyElectionVoter}');				
		</c:forEach>
	</c:forEach>
	electionsInMandalDatatable();
</script>

<script type="text/javascript">
	buildBoothDataForRevenueVillagesTable();
</script>


</body>
</html>