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
	
</style>

<script type="text/javascript">

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
				key : "partyName"
			},{
				key : "electionYear"
			}, {
				key : "votesEarned"
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
		} ];
	
		
		var myDataTable = new YAHOO.widget.DataTable("mainDataDiv",resultsColumnDefs,resultsDataSource);  
		
	}
</script>


</head>
<body>
		<div id="mainDiv">
			<div id="headingDiv"> Complete Details </div>
			<div id="bodyDiv" class="yui-skin-sam" >
				<div id="mainDataDiv">
					<table id="mainDataTable">
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
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
		
<script type="text/javascript">
	initializeResultsTable();
</script>

</body>
</html>