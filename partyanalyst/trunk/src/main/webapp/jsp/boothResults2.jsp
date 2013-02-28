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

<script type='text/javascript' src="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.0.1/bootstrap.min.js"></script>

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
   font-size:13px;
   font-family:verdana;
}
.yui-skin-sam th.yui-dt-asc, .yui-skin-sam th.yui-dt-desc 
{
	background:none;
}

.yui-skin-sam thead .yui-dt-sortable {

	background-color:#C4DEFF;
	color:#3F546F;
}

.yui-skin-sam .yui-dt-liner {
	padding:4px 8px;
}

#boothResultsTableId th{
	background-color:#C4DEFF;
	text-align:center;
	font-weight:bold;
	color:#1031B6;
	height:30px;
}

#boothResultsTableId td{

	/*color:#180206;
	font-weight:bold;
	width:140px;
	border: 1px solid #b0bec7;
	padding: 0 0 0 10px;*/

color:#5B5B5B;
width: 660px;
height: 25px;
font-family: tahoma, arial, helvetica, verdana, sans-serif;
font-size: 13px;
font-weight: bold;
text-decoration: none;
background: transparent;
border: 1px solid #b0bec7;
padding: 0 0 0 10px;
}
#boothResultsTableId{width:95%;}
#boothResultsTableId tr:nth-child(even){background:#F8FBFF;}
#boothResultsTableId tr:nth-child(odd){background:#F8FBFF;}


#titleDiv
{
	color: #ffffff;
    font-size: 13px;
    font-weight: bold;
    margin-top: 20px;
	margin-left:auto;
	margin-right:auto;
	background:#06ABEA;
	padding:5px;
	border-radius:5px;
	padding-left: 30px;
    width: 470px;
}

}
.resultTableDiv{
	width :100%;
	margin-bottom:5px;
}
#mainDiv
{
	margin-left:auto;
	margin-right:auto;
	width:980px;
}

table#searchresultsTable {
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
	margin-top:10px;
}
table#searchresultsTable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}
table#searchresultsTable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #5B5B5B;
	background-color: #ffffff;
}

#boothResultsMarkup table{border: 1px solid #C4DEFF;margin-right: 15px;
	font-family:arial;
	margin-bottom:20px;
	
}

</style>
</head>
<body>
<div id="mainDiv">
<div id="titleDiv"><s:property value="boothResult.constituencyName" /> Constituency Booth Results for <s:property value="boothResult.partyName" /> in  <s:property value="boothResult.electionYear" /> <s:property value="boothResult.electionType" /></div>
<div id="boothResultsDiv">

<div class="resultTableDiv">
<div class="boothResultHeadingDiv" style="margin-eft:15px;">Candidate Details : </div>
<table class="searchresultsTable" id="searchresultsTable" style="width: auto; float: left;">
	<tr>
		<th style="background-color:#C4DEFF;">Candidate Name</th>
		<td style="background-color:#F8FBFF;"><s:property value="boothResult.candidateName" /> [ Rank - <s:property value="boothResult.rank" /> ]<br></td>

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
	
	<s:if test="boothResult.rank != 1">
	<tr id='wonInfo'>
		
		<th style="background-color:#C4DEFF;">Winning Candidate</th>
		<td style="background-color:#F8FBFF;"><s:property value="boothResult.wonCandidate[0][1]" /> - <s:property value="boothResult.wonCandidate[0][2]" /></td>

		<th style="background-color:#C4DEFF;">Margin Votes</th>
		<td style="background-color:#F8FBFF;"><s:property value="boothResult.marginVotes" /></td>
		
	</tr>
	</s:if>
</table>
</div>
<BR><BR><BR><BR><BR><BR>

<div style="float:left;width:auto;">
<table>
	<tr>
		<td>
			<div class="boothResultHeadingDiv" style="margin-left:70px;">Polling Percentage vs Party Votes Percentage</div><br>
			<table id="boothResultsTableId">
				<tr> 	 	
			       <th>Polling % Range</th>
				   <th>Total No of Booths</th>
				   <th>Party Votes %</th>
				 </tr>
			<s:iterator value="boothResult.perWiseboothResults" var="boothresult">
				 <tr>
			       <td><s:property value="location"/></td>
				   <td id='partyvotesEarnedId'><a><s:property value="votesEarned"/></a></td>
				   <td><s:property value="percentage"/></td>
				 </tr>
			</s:iterator>
			</table>
			<!--<div >
			<display:table id="boothResultsTableId"
				 name="${boothResult.perWiseboothResults}"
				defaultorder="ascending" defaultsort="4"
				style="width:auto;margin-left:1px;border:1px solid #C4DEFF;">

			<display:column style="text-align:center;" title="Polling % Range"
					property="location" />
			<display:column style="text-align:center;" title="Total No of Booths"
					property="votesEarned" href="javascript:openWin('#');" paramId="votesEarned" paramProperty="votesEarned">
				
			</display:column>
			<display:column style="text-align:center;" title="Party Votes %"
					property="percentage" />
			</display:table>
			</div>-->
		</td>
		<td>
			<div class="boothResultHeadingDiv" style="margin-left:80px;">Party Votes Percentage vs Polling Percentage</div><br>
			<table id="boothResultsTableId">
				<tr> 	 	
			       <th>Polling % Range</th>
				   <th>Total No of Booths</th>
				   <th>Polling %</th>
				 </tr>
			<s:iterator value="boothResult.partyPerWiseboothResults" var="boothresult">
				 <tr>
			       <td><s:property value="location"/></td>
				   <td id='pollingPercentId'><a><s:property value="votesEarned"/></a></td>
				   <td><s:property value="percentage"/></td>
				 </tr>
			</s:iterator>
			</table>
			<!--<div class="yui-skin-sam">
			<display:table id="boothResultsTableId"
				 name="${boothResult.partyPerWiseboothResults}"
				defaultorder="ascending" defaultsort="4"
				style="width:auto;margin-left:10px;border:1px solid #C4DEFF;">

			<display:column style="text-align:center;" title="Party Votes % Range"
					property="location" />
			<display:column style="text-align:center;" title="Total No of Booths"
					property="votesEarned" href="javascript:openWin('#')" paramId="votesEarned" paramProperty="votesEarned">
			</display:column>
			<display:column style="text-align:center;" title="Polling %"
					property="percentage" />
			</display:table>
			</div>-->
		</td>
	</tr>
</table>
</div>

<div class="boothResultHeadingDiv" style="margin-left:15px;;margin-bottom:15px;clear:both;padding-top:20px;">Booth Wise Performance : </div>
<div id="boothResultsMarkup" class="yui-skin-sam">
<display:table id="boothResultsTable"
	 name="${boothResult.boothResults}"
	defaultorder="ascending" defaultsort="4"
	style="width:auto;margin-right:20px;">
	<display:column style="text-align: center;" title="Booth No"
		property="partNo"></display:column>
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
	<display:column style="text-align: center;" title="boothId"
		property="boothId" />
</display:table>

</div>
</div>
<div class="modal hide fade" id="myModal" style="width:750px;">
  <div class="modal-header">
    <a class="close" data-dismiss="modal">X</a>
		<h4>Booths</h4>
  </div>
  <div class="modal-body">
		<p style="font-size:16px;font-weight:bold;"></p>
  </div>
 </div>
</div>
<script language="javascript">
initializeResultsTable();
var contextPath = "<%=request.getContextPath()%>"; 

//Iterating key and value of Party votes% START
	var managersArrayForChm = new Array();
	var finalMapForChm = new Object();
	<c:forEach var="entry" items="${boothResult.boothsMap}">
		managersArrayForChm = new Array();
			<c:forEach items="${entry.value}" var="keyval">
				//console.log('${keyval.id}'+'${keyval.location}'+'${keyval.partno}'+'${keyval.villageCovered}');
				var boothDetail = {
					partNo: '${keyval.partno}',
					location: '${keyval.location}',
					villages: '${keyval.villageCovered}',
					boothId: '${keyval.id}'
				};
				managersArrayForChm.push(boothDetail);//Pushing the values into array
			</c:forEach>
		finalMapForChm["${entry.key}"] = managersArrayForChm;//mapping the pushed values to key
	</c:forEach>
//Iterating key and value of Party votes% END

//Iterating key and value of Polling votes% START
	var managersArrayForChm_polling = new Array();
	var finalMapForChm_polling = new Object();
	<c:forEach var="entry" items="${boothResult.boothsMap1}">
		managersArrayForChm_polling = new Array();
			<c:forEach items="${entry.value}" var="keyval">
				var boothDetail_polling = {
					partNo: '${keyval.partno}',
					location: '${keyval.location}',
					villages: '${keyval.villageCovered}',
					boothId: '${keyval.id}'
				};
				managersArrayForChm_polling.push(boothDetail_polling);//Pushing the values into array
			</c:forEach>
		finalMapForChm_polling["${entry.key}"] = managersArrayForChm_polling;//mapping the pushed values to key
	</c:forEach>
//Iterating key and value of Polling votes% END


$('#partyvotesEarnedId').live('click',function(){
  var arr=finalMapForChm[$(this).prev().text()];
  openModal(arr);
});

$('#pollingPercentId').live('click',function(){
  var arr1=finalMapForChm_polling[$(this).prev().text()];
  openModal(arr1);
});

function openModal(arr){
    $('#myModal').find('p').html('');
	var str='<div id="boothsList"><table class="table table-hover table-bordered"><thead style="background:#ECE9D8;"><tr><th style="width:72px;">Booth No</th><th>Location</th><th>Villages Covered</th></tr></thead><tbody>';
	for (var i = 0; i < arr.length; i++) {
		 str+='<tr><td onclick="boothDetails('+arr[i].boothId+')" title="Click to Know '+arr[i].partNo+' Booth\'s No of voters, Mandal and Villages Covered and Recent Election Results "><span class="btn" style="margin:2px;width:24px;">'+arr[i].partNo+'</span></td><td>'+arr[i].location+'</td><td>'+arr[i].villages+'</td>';
    }
	str+='</tbody></table></div>';
	$('#myModal').find('p').append('<span style="color:#4F2817;">'+str+'</span>');
	$('#myModal').modal('show');
		
}





</script>

</body>

</html>