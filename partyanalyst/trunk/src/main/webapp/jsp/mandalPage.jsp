<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tehsil Details</title>
<style type="text/css">
.ConstituencyElectionsTable th
{
	text-align:left;
}
.ConstituencyElectionsTable td
{
	text-align:right;
}

#VillageTable th
{
	background-color:#afafaf;
}

#boothResultsDiv {
	text-align: left;
	margin-left: 50px;
	font-size: 12px;
}
.yui-skin-sam th.yui-dt-asc, .yui-skin-sam th.yui-dt-desc 
{
	background:none;
}

.yui-skin-sam thead .yui-dt-sortable {

	background-color:#C4DEFF;
	color:#3F546F;
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
</style>
<!-- Combo-handled YUI CSS files: -->
<link rel="stylesheet" type="text/css"
	href="http://yui.yahooapis.com/combo?2.8.0r4/build/datatable/assets/skins/sam/datatable.css">
<!-- Combo-handled YUI JS files: -->
<script type="text/javascript"
	src="http://yui.yahooapis.com/combo?2.8.0r4/build/yahoo-dom-event/yahoo-dom-event.js&2.8.0r4/build/element/element-min.js&2.8.0r4/build/datasource/datasource-min.js&2.8.0r4/build/datatable/datatable-min.js"></script>

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
					<th>Male</th>
					<th>Female</th>
					<th>Total</th>
				</tr>
				<tr>
					<th>Population</th>
					<td><c:out value="${mandalInfoVO.totalMalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalFemalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalPersons}"/></td>
				</tr>
				<tr>
					<th>SC Population</th>
					<td><c:out value="${mandalInfoVO.totalSCMalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalSCFemalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalSCPersons}"/></td>
				</tr>
				<tr>
					<th>ST Population</th>
					<td><c:out value="${mandalInfoVO.totalSTMalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalSTFemalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalSTPersons}"/></td>
				</tr>
					
				<tr>
					<th>Literate Populations</th>
					<td><c:out value="${mandalInfoVO.totalLiterateMalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalLiterateFemalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalLiteratePersons}"/></td>
				</tr>
				<tr>
					<th>Illiterate Population</th>
					<td><c:out value="${mandalInfoVO.totalIlliterateMalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalIlliterateFemalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalIlliteratePersons}"/></td>
				</tr>
				<tr>
					<th>Working Population</th>
					<td><c:out value="${mandalInfoVO.totalWorkingMalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalWorkingFemalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalWorkingPersons}"/></td>
				</tr>	
			</table>
		</div>
	</div>
	<div id="villageCensusDiv">
		<div id="villageCensusDivHead"><h4><u>Villages Details..</u></h4></div>
		<div id="villageCensusDivBody">
			<display:table class="searchresultsTable"
			 name="${villageDetailsVO.villageCensusList}"
			defaultorder="ascending" defaultsort="2"
			style="width:auto;margin-right:20px;">
				<display:column style="text-align: left;" title="Village Name"
					property="townshipName" />
				<display:column style="text-align: left;" title="Total Populations"
					property="totalPersons" />
				<display:column style="text-align: left;" title="SC Population"
					property="totalSCPersons" />
				<display:column style="text-align: center;" title="ST Population"
					property="totalSTPersons" />
				<display:column style="text-align: center;" title="Literate Population"
					property="totalLiteratePersons" />
				<display:column style="text-align: center;" title="Illiterate Population"
					property="totalIlliteratePersons" />
				<display:column style="text-align: center;" title="Working Population"
					property="totalWorkingPersons" />
			</display:table>
		</div>
	</div>		
</div>
</body>
</html>