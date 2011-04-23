<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Polls</title>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/treeview/treeview-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dragdrop/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/history/history.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/container/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yuiloader/yuiloader-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/button/button-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/resize/resize-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/layout/layout-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/carousel/carousel-min.js"></script>



	<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>

	<script type="text/javascript" src="js/yahoo/yui-gallery/gallery-accordion-min.js"></script>

	<!-- YUI Skin Sam -->

	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/yui-gallery-styles/gallery-accordion.css">	
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/treeview/assets/skins/sam/treeview.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/resize.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/layout.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/carousel/assets/skins/sam/carousel.css">
	<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/faviIcon.jpg">

	<!-- YUI Dependency files (End) -->
	<script type="text/javascript" src="http://www.google.com/jsapi"></script>
<style type="text/css">
	
	#pollsDiv
	{
		text-align:left;		
		margin-top:40px;
		margin-left:40px;
		font-family:verdana;
		font-weight:bold;
		color:#0C67AC;
		text-align:left;
		font-size:13px;
	}
	#headingDiv
	{
		color:#247CD4;
		font-size:19px;
		font-weight:bold;
		text-align:center;
		padding-top:30px;
	}
	body
	{
		background-color:#EAEDEF;
		direction:ltr;
		font-family:"lucida grande",tahoma,verdana,arial,sans-serif;
		font-size:11px;
		margin:0;
		padding:0;
	}
	#pollsDivBody
	{
		padding:10px;
	}
	#pollsDivBody table
	{
		width:100%;
	}
</style>
<script type="text/javascript">
	function getCompletePollResult(questionId){  
		var browser1 = window.open("completeResultForAPollAction.action?questionId="+questionId,"completeResultForAPoll","scrollbars=yes,height=350,width=450,left=200,top=200");
		browser1.focus();
	}
</script>
<script type="text/javascript"> 

function initializeResultsTable() {
	var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
			.get("pollsDataTable"));
	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
	
	resultsDataSource.responseSchema = {
		fields : [ { 		
			key : "startDate",formatter:YAHOO.widget.DataTable.formatDate
		}, {
			key : "question"
		}, {
			key : "",formatter:YAHOO.widget.DataTable.formatLink
		}]
	};

	var resultsColumnDefs = [ {
		key : "startDate",
		label : "Poll Date",
		sortable : true
	}, {
		key : "question",
		label : "Question",
		sortable : true
	}, {
		key : "",
		label : "More Details..",
		sortable : true	
	}];

	
	var myDataTable = new YAHOO.widget.DataTable("pollsDivBody",resultsColumnDefs, resultsDataSource);  
	
}

</script>
</head>
<body>
		<div id="headingDiv">
			All Polls
		</div>
		<center style="margin-top:30px;">
		<div id="polls_body" class="yui-skin-sam">							
				<div id="pollsDivBody">
					<table id="pollsDataTable">						
				      <c:forEach var="polls" varStatus="stat" items="${allPolls}">
				      	<tr>
				      		<td style="padding-right:40px;">
				      		 	  ${polls.startDate}
				      		</td>
				      		<td style="padding-right:40px;">
				      		 	  ${polls.question}
				      		</td>
				      		<td>
				      			<a href="javascript:{}" onclick="getCompletePollResult(${polls.questionId})" style="text-align:right;margin-top:15px;text-decoration:underline;"> click here</a>
				      		</td>
				      	</tr>		
				      </c:forEach>
				     </table>
				</div>
		</div>
		</center>
<script language="javascript">
initializeResultsTable();
</script>

</body>
</html>