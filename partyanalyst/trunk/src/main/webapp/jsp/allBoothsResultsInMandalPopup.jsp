<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<TITLE>All Booths Results</TITLE>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></script>
		
	<!-- YUI Skin Sam -->

	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/paginator.css">
	
	<!-- YUI Dependency files (End) -->
<STYLE>
.yui-skin-sam .yui-dt td {
	border-color:-moz-use-text-color #CBCBCB -moz-use-text-color -moz-use-text-color;
	border-style:none solid none none;
	border-width:medium 1px medium medium;
	font-size:12px;
	margin:0;
	padding:0;
	text-align:left;
}
.pageHead
{
	background-image:url("images/icons/electionResultsReport/heading.png");
	border:0 solid #AEE2FF;
	color:#000000;
	font-family:MS Sans-serif;
	font-size:17px;
	font-weight:bold;
	height:25px;
	margin-bottom:15px;
	margin-top:15px;
	padding:10px;
	text-align:center;	
}	
</STYLE>
</HEAD>
<BODY class="yui-skin-sam">
	<center>
	<table cellspacing="0" cellpadding="0" border="0">
		<tr>
			<td valign="top"><img border="none" style="margin-top: 15px;" src="images/icons/electionResultsReport/elections_logo1.png"></td><td valign="top">
			<div class="pageHead">All Booths Results In ${mandalName}  Mandal</div>
			</td><td valign="top"><img border="none" style="margin-top: 15px;" src="images/icons/electionResultsReport/elections_logo2.png">
			</td>
		</tr>
	</table>
	</center>
	<DIV id="infoText" style="color:#707070;"><B>Opp Party</B>=Opposition Party, <B>Opp Party VE</B>= Oppposition Party Votes Earned, <B>Opp Party %</B>=  Oppposition Party Votes Percentage</DIV>
	<DIV id="dataTableDiv"></DIV> 
</BODY>
<SCRIPT type="text/javascript">
var allBoothsResultsArr = new Array();

	<c:forEach var="resultsList" items="${allBoothsResultsForAPartyInAMandal.boothResults}">

	var resultsObj = {
				partNo: '${resultsList.partNo}',
				location: '${resultsList.location}',
				villagesCovered: '${resultsList.villagesCovered}',
				mandal: '${resultsList.mandal}',
				votesEarned: '${resultsList.votesEarned}',
				percentage: '${resultsList.percentage}',
				oppParty: '${resultsList.oppParty}',
				oppPartyVotesEarned: '${resultsList.oppPartyVotesEarned}',
				oppPartyPercentage: '${resultsList.oppPartyPercentage}'					
			};
		allBoothsResultsArr.push(resultsObj);
		
	</c:forEach>

	function buildBoothResultsDt()
	{
		var resultsDataSource = new YAHOO.util.DataSource(allBoothsResultsArr);
		
		   	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
		   	resultsDataSource.responseSchema = {
		   		fields : [ {
		   			key : "partNo",parser:"number"
		   		}, {
		   			key : "location"
		   		}, {
		   			key : "villagesCovered"
		   		}, {
		   			key : "votesEarned",parser:"number"
		   		}, {
		   			key : "percentage",parser:"number"
		   		}, {
		   			key : "oppParty"
		   		}, {
		   			key : "oppPartyVotesEarned"
		   		}, {
		   			key : "oppPartyPercentage"
		   		}]
		   	};
	   	
			var resultsColumnDefs = [ {
				key : "partNo",
				parser:"number",
				label : "Booth No",
				sortable : true
			}, {
				key : "location",
				label : "Location",
				sortable : true
			}, {
				key : "villagesCovered",
				label : "Villages Covered",
				sortable : true
			}, {
				key : "votesEarned",
				label : "Votes Earned",
				sortable : true
			}, {
				key : "percentage",
				label : "Votes %",
				sortable : true
			}, {
				key : "oppParty",
				label : "Opp Party"
				
			}, {
				key : "oppPartyVotesEarned",
				label : "Opp Party VE ",
				sortable : true
				
			}, {
				key : "oppPartyPercentage",
				label : "Opp Party %",
				sortable : true			
			} ];
			if(allBoothsResultsArr.length > 20)
			{
				var myConfigs = {
			    paginator : new YAHOO.widget.Paginator({
			    	rowsPerPage    : 20,
					template: "{PageLinks} Show {RowsPerPageDropdown} Rows Per Page",
					rowsPerPageOptions: [20,40,60,80], 
				    pageLinks: 20	
			    	})
				};
			}


		var myDataTable = new YAHOO.widget.DataTable("dataTableDiv",resultsColumnDefs, resultsDataSource,myConfigs);  
	}
	buildBoothResultsDt();
window.history.forward(1);
</SCRIPT>
</HTML>