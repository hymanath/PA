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

<script type="text/javascript" src="http://www.google.com/jsapi"></script> 
<script type="text/javascript" src="js/customPaginator/customPaginator.js"></script>
<script type="text/javascript" src="js/googleAnalytics/googleAnalytics.js"></script>
	
<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />

<title> Complete Details </title>



<style type="text/css">

	#headingDiv 
	{
	   	color: #247CD4;
	    font-size: 19px;
	    font-weight: bold;
	    padding-bottom: 29px;
	    padding-top: 22px;
	    text-align: center;
	}
	
	.yui-skin-sam .yui-dt th, .yui-skin-sam .yui-dt th a 
	{
	    color: #000000;
	    font-size: 13px;
	    font-weight: bold;
	    text-decoration: none;
	    vertical-align: bottom;
	}
	
	.yui-skin-sam .yui-dt table 
	{
	    border-collapse: separate;
	    border-spacing: 0;
	    font-family: arial;
	    font-size: 13px;
	}

	.yui-skin-sam .yui-pg-container 
	{
	    display: block;
	    font-size: 13px;
	    margin: 4px 0;
	    text-align: center;
	    white-space: nowrap;
	}
</style>

<script type="text/javascript">

var type = '${type}';

	function initializeResultsTable() {	
		var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
				.get("mainDataTable"));
		resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
		resultsDataSource.responseSchema = {
			fields : [  {
				key : "candidateName"
			},{
				key : "constituencyName"
			},{
				key : "electionYear"
			}, {
				key : "votesEarned"
			},{
				key : "moreDetails"
			}]
		};
	
		var resultsColumnDefs = [  {
			key : "candidateName",
			label : "Candidate Name",
			sortable : true
		},{
			key : "constituencyName",
			label : "Constituency Name",
			sortable : true
		},{
			key : "electionYear",
			label : "Election Year",
			sortable : true
		},{
			key : "votesEarned",
			label : "Votes Earned",
			sortable : true
		},{
			key : "moreDetails",
			label : "More Details",
			sortable : true
		} ];
	
		var paginatorConfig = {
		    paginator : new YAHOO.widget.Paginator({
		        rowsPerPage: 10
		    })
		};				
		var myDataTable = new YAHOO.widget.DataTable("mainDataDiv",resultsColumnDefs,resultsDataSource,paginatorConfig);
	}

	function initializeResultsTableForAllParties()
	{
		var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
				.get("mainDataTable"));
		resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
		resultsDataSource.responseSchema = {
			fields : [  {
				key : "candidateName"
			},{
				key : "constituencyName"
			},{
				key : "partyName"
			},{
				key : "electionYear"
			}, {
				key : "votesEarned"
			},{
				key : "moreDetails"
			}]
		};
	
		var resultsColumnDefs = [  {
			key : "candidateName",
			label : "Candidate Name",
			sortable : true
		},{
			key : "constituencyName",
			label : "Constituency Name",
			sortable : true
		},{
			key : "partyName",
			label : "Party Name",
			sortable : true
		},{
			key : "electionYear",
			label : "Election Year",
			sortable : true
		},{
			key : "votesEarned",
			label : "Votes Earned",
			sortable : true
		},{
			key : "moreDetails",
			label : "More Details",
			sortable : true
		} ];
	
		var paginatorConfig = {
		    paginator : new YAHOO.widget.Paginator({
		        rowsPerPage: 10
		    })
		};				
		var myDataTable = new YAHOO.widget.DataTable("mainDataDiv",resultsColumnDefs,resultsDataSource,paginatorConfig);

	}

	
	function getConstituencyElecResultsWindow(constiId,elecType,elecYear)
	{
		
	   //var browser1 = window.open("<s:url action="constituencyElectionResultsAction.action"/>?constituencyId="+constiId+"&electionType="+elecType+"&electionYear="+elecYear,"constituencyElectionResults","scrollbars=yes,height=600,width=750,left=200,top=200");
	   var browser1 = window.open("constituencyElectionResultsAction.action?constituencyId="+constiId+"&electionType="+elecType+"&electionYear="+elecYear,"constituencyElectionResults","scrollbars=yes,height=600,width=750,left=200,top=200");
	   browser1.focus();
	}

</script>


</head>
<body>
		<div id="mainDiv">
		 <c:if test="${type==null || type=='WINNER'}">
			<div id="headingDiv"> Complete Details of ${partyName} Party</div>
		</c:if>
		<c:if test="${type!=null && type!='WINNER'}">
				<div id="headingDiv"> Complete Details of All Partys</div>
		</c:if>
			<div id="bodyDiv" class="yui-skin-sam" >
				<div id="mainDataDiv">
					<table id="mainDataTable">
					 <c:if test="${type==null || type=='WINNER'}">
						<c:forEach var="requiredConstituencyDetails" varStatus="stat" items="${requiredConstituencyDetails}">		
							<tr>
								<td>
									${requiredConstituencyDetails.candidateName}
								</td>
								<td>
									${requiredConstituencyDetails.constituencyName}
								</td>						
								<td>
									${requiredConstituencyDetails.electionYear}
								</td>
								<td>
									${requiredConstituencyDetails.votesEarned}
								</td>
								<td>
									<a href="javascript:{}" onclick="getConstituencyElecResultsWindow('${requiredConstituencyDetails.constituencyId}','${electionType}','${requiredConstituencyDetails.electionYear}')">view results</a>
								</td>
							</tr>
						</c:forEach>
					   </c:if>
					   <c:if test="${type!=null && type!='WINNER'}">
						<c:forEach var="requiredConstituencyDetails" varStatus="stat" items="${requiredConstituencyDetails}">	
							<tr>
								<td>
									${requiredConstituencyDetails.candidateName}
								</td>
								<td>
									${requiredConstituencyDetails.constituencyName}
								</td>	
								<td>
									<img src="<%=request.getContextPath()%>/images/party_flags/${requiredConstituencyDetails.partyName}" height="30" width="40"/>
								</td>							
								<td>
									${requiredConstituencyDetails.electionYear}
								</td>
								<td>
									${requiredConstituencyDetails.votesEarned}
								</td>
								<td>
									<a href="javascript:{}" onclick="getConstituencyElecResultsWindow('${requiredConstituencyDetails.constituencyId}','${electionType}','${requiredConstituencyDetails.electionYear}')">view results</a>
								</td>
							</tr>
						</c:forEach>
					   </c:if>
					</table>
				</div>
			</div>
		</div>
		
<script type="text/javascript">
	<c:if test="${type==null || type=='WINNER'}">
		initializeResultsTable();
	 </c:if>
	<c:if test="${type!=null && type!='WINNER'}">
		 initializeResultsTableForAllParties();
	</c:if>
</script>

</body>
</html>