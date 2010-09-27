<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><c:out value="${localBodyElectionResults.localBodyRegion}" /> Local Body Elections</title>

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

<script type="text/javascript" src="http://www.google.com/jsapi"></script>
<script type="text/javascript" src="js/localBodyElection/localBodyElection.js"></script>


<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jQuery/jquery.liquidcarousel.js"></script>

<script type="text/javascript" src="js/homePage/jquery.sudoSlider.min.js"></script>


<link  rel="stylesheet" type="text/css" href="styles/jQuery/liquidcarousel.css"/>
<link  rel="stylesheet" type="text/css" href="styles/homePage/jquerySlider.css"/>

<link  rel="stylesheet" type="text/css" href="styles/homePage/homePage.css"/>
<link rel="stylesheet" type="text/css" href="styles/districtPage/districtPage.css">
<link rel="stylesheet" type="text/css" href="styles/localBodyElection/localBodyElection.css">

<script type="text/javascript">

	google.load("elements", "1", {packages : ["newsshow"]});
	
	localBodyElectionObj.stateId = '${localBodyElectionResults.stateId}';
	localBodyElectionObj.stateName = '${localBodyElectionResults.state}';
	localBodyElectionObj.localBodyElectionTypeId = '${localBodyElectionResults.localBodyElectionTypeId}';
	localBodyElectionObj.localBodyElectionTypeName = '${localBodyElectionResults.localBodyElectionType}';
	localBodyElectionObj.localBodyId = '${localBodyElectionResults.localBodyId}';
	localBodyElectionObj.localBodyName = '${localBodyElectionResults.localBodyRegion}';
	localBodyElectionObj.localBodyElectionId = '${localBodyElectionResults.localBodyElectionId}';
	localBodyElectionObj.tehsilId = '${localBodyElectionResults.tehsilId}';
	localBodyElectionObj.tehsilName = '${localBodyElectionResults.tehsil}';
	localBodyElectionObj.districtId = '${localBodyElectionResults.districtId}';
	localBodyElectionObj.districtName = '${localBodyElectionResults.district}';

	
</script>
</head>
<body>
	<div id="localBodyElectiondiv_main">
		<table width="100%">
			<tr>
				<td width="65%" valign="top">
					<div>
						 <div class="productFeatureHeader">
							<table width="100%" border="0" cellpadding="0" cellspacing="0" style="width:100%;">
								<tr>
									<td width="30px"><img src="images/icons/districtPage/header_left.gif"/></td>
									<td><div class="districtPageRoundedHeaders_center" style="padding:8px;width:554px;"><span>${localBodyElectionResults.localBodyRegion}  ${localBodyElectionResults.localBodyElectionType} Details  </span></div></td>
									<td><img src="images/icons/districtPage/header_right.gif"/></td>
								</tr>
							</table>
						</div>
						<div id="districtNewsDiv_outer" style="overflow:hidden;width:603px;height:135px;">
							<div id="districtNewsDiv" style="padding:10px;">
								<table id="localBodyDetailsTable" class="datatableClass" border="1">
									<tr>
										<th>${localBodyElectionResults.localBodyElectionType} Name</th>										
										<td>${localBodyElectionResults.localBodyRegion}</td>
										<th>Total Wards</th>										
										<td>${localBodyElectionResults.totalWards}</td>
									</tr>
									<tr>
										<th>District</th>										
										<td>${localBodyElectionResults.district}</td>
										<th>State</th>										
										<td>${localBodyElectionResults.state}</td>
									</tr>
									<tr>										
										<th>Total Votes</th>										
										<td>${localBodyElectionResults.totalVotes}</td>
										<th>Valid Votes</th>										
										<td>${localBodyElectionResults.totValidVotes}</td>
									</tr>									
								</table>
							</div>
						</div>	
					</div>
				</td>
				<td width="35%" valign="top">
					<div class="productFeatureMain">							
						<div class="productFeatureHeader">
							<table  border="0" cellpadding="0" cellspacing="0" style="width:100%;">
								<tr>
									<td width="10px"><img src="images/icons/districtPage/header_left.gif"/></td>
									<td width="125px"><div class="districtPageRoundedHeaders_center" style="padding:8px;width:261px;"><span>${localBodyElectionResults.localBodyRegion} ${localBodyElectionResults.localBodyElectionType} News </span></div></td>
									<td><img src="images/icons/districtPage/header_right.gif"/></td>
								</tr>
							</table>
						</div>
						<div id="local_body_news" class="productFeatureBody" style="overflow:hidden;width:300px;height:125px;">
							
						</div>	
					</div>
				</td>
			</tr>			
			</table>

			<table>
			<tr>
				<td width="70%" valign="top">
					<div class="yui-skin-sam">
						 <div class="productFeatureHeader">
							<table width="100%" border="0" cellpadding="0" cellspacing="0" style="width:100%;">
								<tr>
									<td width="30px"><img src="images/icons/districtPage/header_left.gif"/></td>
									<td><div class="districtPageRoundedHeaders_center" style="padding:8px;width:611px;"><span>${localBodyElectionResults.localBodyRegion}  ${localBodyElectionResults.localBodyElectionType} Election Results in  ${localBodyElectionResults.localBodyElectionYear}</span></div></td>
									<td><img  src="images/icons/districtPage/header_right.gif"/></td>
								</tr>
							</table>
						</div>
						<div id="localBodyElectionResults_body" class="productFeatureBody">
							<div id="localBodyElectionResults_body_chart">
								<img width="650px" height="280px" src="charts/${localBodyElectionResults.highLevelChart}"></img>
							</div>
							<div id="localBodyElectionResults_body_heading"> *V - Votes, *P - Participated</div>
							<div id="localBodyElectionResults_body_dataTable">
							<display:table id="localBodyElectionResultsTable" name="${localBodyElectionResults.muncipalityVO}"
								defaultorder="ascending" defaultsort="4"
								style="width:100%;margin-right:20px;">
								<display:column style="text-align: center;" title="Party Name"
									property="partyName" />
								<display:column style="text-align: left;" title="Participated Seats"
									property="participatedSeats" />
								<display:column style="text-align: center;" title="Won Seats"
									property="partyWonSeats" />
								<display:column style="text-align: center;" title="Second Position"
									property="partySecndPos" />
								<display:column style="text-align: center;" title="Third Position"
									property="partyThirdPos" />
								<display:column style="text-align: center;" title="Nth Position"
									property="partyNthPos" />
								<display:column style="text-align: left;" title="Votes Gained"
									property="votesGained" />
								<display:column style="text-align: left;" title="Participated Votes %"
									property="partiPartiVotesPercent" />
								<display:column style="text-align: left;" title="Total Votes %"
									property="totConstiVotesPercent" />
							</display:table>
							</div>
						</div>
					</div>
				</td>	
				<td width="30%" rowspan="2" valign="top">
					<div style="width:270px;height:583px;background-color:#DEE1E2;"></div>
				</td>
			</tr>
			<tr>
				<td>
					<div id="wardsElectionResults_main">
						<div id="wardsElectionResults_head">
							<div class="productFeatureHeader">
										<table width="100%" border="0" cellpadding="0" cellspacing="0" style="width:100%;">
											<tr>
												<td width="30px"><img src="images/icons/districtPage/header_left.gif"/></td>
												<td><div class="districtPageRoundedHeaders_center" style="padding:8px;width:611px;"><span>${localBodyElectionResults.localBodyRegion}  Ward Wise ${localBodyElectionResults.localBodyElectionType} Election Results in  ${localBodyElectionResults.localBodyElectionYear}</span></div></td>
												<td><img  src="images/icons/districtPage/header_right.gif"/></td>
											</tr>
										</table>
									</div>
						</div>
						<div id="wardsElectionResults_body" class="productFeatureBody">
							<div id="wardsElectionResults_body_radioSelectDiv" style="padding:5px;font-weight:bold;">
								<!--Select Results Criteria :
								<input type="radio" name="wardWiseElectionRadio" value="all" checked="checked">All</input>
								<input type="radio" name="wardWiseElectionRadio" value="partyWise">Party Wise Results</input>	-->
							</div>
							<div id="wardsElectionResults_body_results">
								<img src="images/icons/barloader.gif"></img>
							</div>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</div>

	<script type="text/javascript">
		
		initializeLocalBodiesElectionPage();
	</script>
</body>
</html>