<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Election results in all revenue villages of ${tehsilName } Mandal </title>
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

	<!-- YUI Dependency files (End) -->

	<script type="text/javascript" src="js/districtPage/districtPage.js"></script>	
	<link rel="stylesheet" type="text/css" href="styles/districtPage/districtPage.css">
	
	<script type="text/javascript">
	
		function buildVotesPolledDataTable() {
			var resultsDataSourceForTehsil = new YAHOO.util.DataSource(YAHOO.util.Dom
					.get("votesPolledTable"));
			resultsDataSourceForTehsil.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
			resultsDataSourceForTehsil.responseSchema = {
				fields : [  {
					key : "townshipName"
				},{
					key : "percentageOfValidVotes"
				}]
			};
	
			var resultsColumnDefsForTehsil = [  {
				key : "townshipName",
				label : "Township Name",
				sortable : true
			},{
				key : "percentageOfValidVotes",
				label : "Votes %",
				sortable : true,
				parser:"float",
			}];	
			
			var myDataTableForTehsil = new YAHOO.widget.DataTable("votesPolledDtTableDiv",resultsColumnDefsForTehsil, resultsDataSourceForTehsil);			
		}
	</script>
</head>
<body>
	<div style="color:#24455E;font-size:17px;font-weight:bold;margin-bottom:10px;margin-top:23px;text-decoration:underline;">All Parties Trends In All Elections Of ${tehsilName } Mandal</div>
	<div>
	<c:if test="${! empty mandalVO}">
		<s:form action="mandalRevenueVillagesElecViewAction" name="MandalRevenueVillagesElecViewAction" method="GET" enctype="multipart/form-data">
			<table>
				<tr><td><s:checkboxlist list="mandalVO.partiesInMandal" listKey="id" listValue="name" name="parties" label="Parties:"/></td></tr>
				<tr><td><s:checkboxlist list="mandalVO.electionsInMandal" listKey="id" listValue="name" name="elections" label="Elections:"/></td></tr>
				<tr><td><input type="hidden" name="tehsilId" value="${tehsilId}"/></td><td><input type="hidden" name="tehsilName" value="${tehsilName}"/></td><td align="center"><s:checkbox theme="simple" id="allianceCheck" name="includeAlliance" value="hasAllianceParties"></s:checkbox><b> Include Aliance Parties</b></td></tr>
				<tr><td colspan="2" align="center"><s:submit label="View Chart" /></td></tr>
			</table>
		</s:form>
	</c:if>
	</div>
	<div>
		<c:if test="${! empty chartPath}">
			<img src="charts/${chartPath}">
		</c:if>
	</div>
	
	
	
	<div id="votesPollingInMandalDIV" class="yui-skin-sam" style="margin-top:40px;">
		<table id="votesPollingInMandal" width="90%">
			<c:forEach var="votesPollingInMandal" items="${townshipBoothDetailsVO}">
				<tr>
					<td width="50%" style="vertical-align:top;">
						<div id="votesPolledDtTableDiv">
						<table id="votesPolledTable">	
							<c:forEach var="votesPolling" items="${votesPollingInMandal.townshipVotingTrends}">
								<tr>
									<td>
										${votesPolling.townshipName}
									</td>
									<td>
										${votesPolling.percentageOfValidVotes}
									</td>	
								</tr>
																									
							</c:forEach>	
							</table>	
						</div>
					</td>
					<td width="50%" style="vertical-align:top;" align="center">						
						<c:forEach var="votesPollingInMandal" items="${townshipBoothDetailsVO}">										
								<table>	
									<tr>	
										<td>
											<img src="charts/${votesPollingInMandal.chartName}">
										</td>	
									</tr>	
								</table>							
						</c:forEach>	
					</td>
				</tr>																				
			</c:forEach>
		</table>
	</div>
	
	<div id="votesPollingInMandalDIV">
	<table>
		
		</tr>
	</table>
	</div>
	
	<script type="text/javascript">
	buildVotesPolledDataTable();
	</script>
</body>
</html>