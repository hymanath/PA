<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@page import="com.itgrids.partyanalyst.excel.booth.PartyBoothPerformanceVO"%>
<%@page import="com.itgrids.partyanalyst.excel.booth.BoothResultVO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="js/partyBoothResults/boothResults.js"></script>

<!-- YUI files dependencies (start) -->

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

<!-- YUI files dependencies (end) -->

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Booth Results</title>
<style type="text/css">
#boothResultsDiv {
    text-align: left;
	margin-left: 50px;
	font-size: 12px;
	
}

.boothResultHeadingDiv
{
   margin-top: 20px;
   color:#23318B;
   text-decoration: underline;
   font-weight: bold;
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

#boothResultsTableId th{
	background-color:#C4DEFF;
	text-align:center;
	font-weight:bold;
	color:#1031B6;
}

#boothResultsTableId td{
	background-color:#F8FBFF;
	color:#180206;
	font-weight:bold;
	width:140px;
}

#titleDiv
{
	color: #23318B;
    font-size: 15px;
    font-weight: bold;
    margin-top: 20px;
    text-decoration: underline;

}
.resultTableDiv{
	width :100%;
	margin-bottom:5px;
}

</style>
</head>
<body>
<div id="titleDiv"><s:property value="boothResult.constituencyName" /> Constituency Booth Results for <s:property value="boothResult.partyName" /> in  <s:property value="boothResult.electionYear" /> <s:property value="boothResult.electionType" /></div>
<div id="boothResultsDiv">

<div class="resultTableDiv">
<div class="boothResultHeadingDiv">Candidate Details : </div>
<table class="searchresultsTable" style="width: auto; float: left;">
	<tr>
		<th style="background-color:#C4DEFF;">Candidate Name</th>
		<td style="background-color:#F8FBFF;"><s:property value="boothResult.candidateName" /><br></td>

		<th style="background-color:#C4DEFF;">Total Votes</th>
		<td style="background-color:#F8FBFF;"><s:property value="boothResult.totalVotes"/></td>
	</tr>

	<tr>
	<th style="background-color:#C4DEFF;">Total Valid Votes</th>
		<td style="background-color:#F8FBFF;"><s:property value="boothResult.totalValidVotes" /></td>	
		
		<th style="background-color:#C4DEFF;">Voting Percentage</th>
		<td style="background-color:#F8FBFF;"><s:property value="boothResult.votingPercentage"/></td>		
	
	</tr>
	<tr>
		
		<th style="background-color:#C4DEFF;">Total Votes Gained</th>
		<td style="background-color:#F8FBFF;"><s:property value="boothResult.votesGained" /></td>

		<th style="background-color:#C4DEFF;">Total Votes Gained Percentage</th>
		<td style="background-color:#F8FBFF;"><s:property value="boothResult.percentage" /></td>
		
	</tr>
</table>
</div>
<BR><BR><BR><BR><BR><BR>

<div>
<table>
	<tr>
		<td>
			<div class="boothResultHeadingDiv" style="margin-left:70px;">Polling Percentage vs Party Votes Percentage</div><br>
			<div class="yui-skin-sam">
			<display:table id="boothResultsTableId"
				 name="${boothResult.perWiseboothResults}"
				defaultorder="ascending" defaultsort="4"
				style="width:auto;margin-left:1px;border:1px;">

			<display:column style="text-align:center;" title="Polling % Range"
					property="location" />
			<display:column style="text-align:center;" title="Total No of Booths"
					property="votesEarned"/>
			<display:column style="text-align:center;" title="Party Votes %"
					property="percentage" />
			</display:table>
			</div>
		</td>
		<td>
			<div class="boothResultHeadingDiv" style="margin-left:80px;">Party Votes Percentage vs Polling Percentage</div><br>
			<div class="yui-skin-sam">
			<display:table id="boothResultsTableId"
				 name="${boothResult.partyPerWiseboothResults}"
				defaultorder="ascending" defaultsort="4"
				style="width:auto;margin-left:10px;border:1px;">

			<display:column style="text-align:center;" title="Party Votes % Range"
					property="location" />
			<display:column style="text-align:center;" title="Total No of Booths"
					property="votesEarned"/>
			<display:column style="text-align:center;" title="Polling %"
					property="percentage" />
			</display:table>
			</div>
		</td>
	</tr>
</table>
</div>
<div class="boothResultHeadingDiv">Booth Wise Performance : </div>
<div id="boothResultsMarkup" class="yui-skin-sam">
<display:table id="boothResultsTable"
	 name="${boothResult.boothResults}"
	defaultorder="ascending" defaultsort="4"
	style="width:auto;margin-right:20px;">
	<display:column style="text-align: center;" title="Booth No"
		property="partNo" />
	<display:column style="text-align: left;" title="Location"
		property="location" />
	<display:column style="text-align: left;" title="Villages Covered"
		property="villagesCovered" />
	<display:column style="text-align: center;" title="Mandal"
		property="mandal" />
	<display:column style="text-align: center;" title="Votes Earned"
		property="votesEarned" />
	<display:column style="text-align: center;" title="Polled Votes"
		property="totalVoters" />
	<display:column style="text-align: center;" title="Polling Percentage"
		property="pollingPercentage" />
	<display:column style="text-align: center;" title="Percentage"
		property="percentage" />
</display:table>
</div>
</div>
<script language="javascript">
initializeResultsTable();
</script>

</body>

</html>