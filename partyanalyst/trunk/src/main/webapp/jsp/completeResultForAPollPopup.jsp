<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Complete Result</title>
<!-- YUI Dependency files (Start) -->

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

	/*body
	{
		background-color:#EAEDEF;
		direction:ltr;
		font-family:"lucida grande",tahoma,verdana,arial,sans-serif;
		font-size:11px;
		margin:0;
		padding:0;
	}*/
	
	.heading
	{
		font-weight:bold;
		font-family:verdana;
		color:#003399;
		padding-top:4px;
		padding-left:10px;
		padding-right:10px;
		padding-top:4px;
		padding-bottom:4px;
	}
	#pollsTable
	{
		margin-left:40px;
		margin-top:20px;
	}
	.cellStyle
	{
		padding-left:10px;
		padding-right:10px;
		padding-top:4px;
		padding-bottom:4px;
		font-weight:normal;
	}
	#contenttable table{background:#ffffff;margin-right:auto;margin-left:auto;padding-bottom:10px;} 
		
</style>
  
<script type="text/javascript">

google.load("visualization", "1", {packages:["corechart"]});
</script>
<script type="text/javascript"> 



var constituencies = [];

var pollStatus = [];


function opinionPollChart()
{
	
var arrData = pollStatus;
var data = new google.visualization.DataTable();
data.addColumn('string','option');
data.addColumn('number','votesObtained');
		
data.addRows(arrData.length);

for(var j=0; j<arrData.length; j++)
		{
			
			data.setValue(j,0,arrData[j].option);
			data.setValue(j,1,arrData[j].votesObtained);
			
		}
			var chart = new google.visualization.PieChart(document.getElementById('chartDiv'));
	
chart.draw(data,{width: 400, height: 280,legend:'right', 
legendTextStyle:{fontSize:12},title:'Opinion Polls To Different Parties',titleTextStyle:{fontName:'verdana',fontSize:9}});



}

function initializeResultsTable() {
	var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
			.get("pollsDataTable"));
	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
	
	resultsDataSource.responseSchema = {
		fields : [ { 		
			key : "option"
		}, {
			key : "votesObtained"
		}, {
			key : "percentage"
		}]
	};
	
	var resultsColumnDefs = [ {
		key : "option",
		label : "Option",
		sortable : true
	}, {
		key : "votesObtained",
		label : "Votes",
		sortable : true
	}, {
		key : "percentage",
		label : "Percentage",
		sortable : true	
	}];

	
	var myDataTable = new YAHOO.widget.DataTable("pollsDivBody",resultsColumnDefs, resultsDataSource);  
	
}

</script>
</head>
<body>
	<table width="100%">
		<tr>
			<td valign="top" align="left">
				<table>
					<tr>
						<td style="color:#0C67AC;font-weight:bold;padding-top:40px;">
							Q) ${questionsOptionsVO.question}
						</td>						
					</tr>					
				</table>
				
							<table style="margin-top:10px;" >
								<tr>
									<td style="font-weight:bold;padding-bottom:20px;">
										 Total Votes Polled: ${questionsOptionsVO.totalVotesObtainedForPoll}
										 
										<div id="polls_body" class="yui-skin-sam">
											<div id="pollsDivBody">				
													<table   id="pollsDataTable">	
														 <c:forEach var="polls" varStatus="stat" items="${questionsOptionsVO.options}">
															 <tr>
									      						<td class="cellStyle">
									      		 	  				${polls.option}
									      						</td>
									      						<td class="cellStyle">
									      		 	  				${polls.votesObtained}
									      						</td>
									      						<td class="cellStyle">
									      		 	  				${polls.percentage} 
									      						</td>
									      					</tr>
														 </c:forEach>
													</table>		
											</div>
										</div>
									</td>
								</tr>
							</table>
			</td>	
			<td align="right">
				<table style="margin-left:-58px;margin-top:37px;">
						<tr>
							<td>
								<div id="chartDiv" style="width: 400px; height: 500px;"></div>				
							</td>
						</tr>
				</table>
			</td>		
		</tr>
</table>	
<script language="javascript">

<c:forEach var="status" varStatus="stat" items="${questionsOptionsVO.options}">
			var obj =	{
							option:'${status.option}',
							votesObtained:${status.votesObtained}
						};
			pollStatus.push(obj);
		</c:forEach>
initializeResultsTable();
opinionPollChart();

</script>
</body>
</html>