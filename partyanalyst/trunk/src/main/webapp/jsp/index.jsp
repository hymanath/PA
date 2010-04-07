<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>

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
	

	<link href="styles/styles.css" rel="stylesheet" type="text/css" />

	<script type="text/javascript" src="js/indexPage/indexPage.js" ></script>
	
	</head>
	<body>
		<div id="dashboard_main">

			<div id="dashboard_layout_main">						
			</div>
			
			<div id="dashBoardLeftlayoutDiv">
			
			</div>
			<div id="dashBoardCenterlayoutDiv">
				<div id="dashBoardCenterlayout_header">		
					<table width="100%" style="width:100%;" cellspacing="0" cellpadding="0" border="0">
					<tr>
					<td style="width:35px;"><img src="images/icons/indexPage/swasthic_left.png"/></td>
					<td class="centerSwasthicImage" style="vertical-align: middle;">	
						<table width="100%" style="width:100%;">
							<tr>
								<td align="left"><font class="welcomeUserFont">Welcome </font> <font class="welcomeUserFont" style="color:#4B74C6">User </font></td>
								<td align="right"><font id="todayDateFont">  Today's Date - 06-April-2010,Tuesday</font></td>
							</tr>
						</table>						

					</td>
					<td style="width:35px;"><img src="images/icons/indexPage/swasthic_right.png"/></td>					
					</table>
				</div>
				<div id="dashBoardCenterlayout_body">
					<table width="100%" style="width:100%">
						<tr>
							<td style="vertical-align:top">
								<table>
									<!-- Imp Event Details-->
									<tr>
										<td><img src="images/icons/indexPage/cal.png"/></span></td>
										<td style="vertical-align:center;"><span class="dashBoardCenterContentHeader">Today's Event</span></td>
									</tr>
									<tr>
										<td colspan="2"><span class="dashBoardCenterContentBody" style="color:#4B74C6">You have 2 event(s) scheduled today</span></td>
									</tr>
									<tr>
										<td><span class="dashBoardCenterContentBody"></span></td>
										<td><span class="dashBoardCenterContentBody">Meeting with cadres at 11.00 AM at party office </span></td>
									</tr>
									<tr>
										<td><span class="dashBoardCenterContentBody"></span></td>
										<td><span class="dashBoardCenterContentBody">Meeting with party president at 4.00PM </span></td>
									</tr>
									
									<!-- Imp Dates Details-->
									<tr>
										<td><img src="images/icons/indexPage/cal.png"/></span></td>
										<td style="vertical-align:center;"><span class="dashBoardCenterContentHeader">Imp Date's</span></td>
									</tr>
									<tr>
										<td colspan="2"><span class="dashBoardCenterContentBody" style="color:#4B74C6">You have 1 Imp date(s) scheduled today</span></td>
									</tr>
									<tr>
										<td><span class="dashBoardCenterContentBody"></span></td>
										<td><span class="dashBoardCenterContentBody">Party President Birthday </span></td>
									</tr>									
								</table>
							</td>
							<td style="vertical-align:top">
								<!--<div class="contentSeparatorDiv"> </div>-->
							</td>
							<td style="vertical-align:top;border-left:1px solid #cdcdcd;">
								<ul id="dashboardRightLayoutList">
									<li>View Your Constituency</li>
									<li>View Your Mandal</li>
									<li>View Your District</li>
								</ul>
							</td>

						</tr>
					</table>
				</div>
			</div>

			<div id="dashboard_reportsNav_main">
				<table id="dashboard_main_table" width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td style="vertical-align:top;width:250px;">
							<div id="dashboard_leftNav" style="margin-top: 35px;">								
								<ul id="pa_reportsList_main">
									<li id="partyAnalysisListItem">
										<div class="reportGroupClass">Party Analysis</div>
									</li>
									<li id="politicianAnalysisListItem">
										<div class="reportGroupClass">Politician Analysis</div>
									</li>
								</ul>
							</div>
						</td>
						<td style="vertical-align:top;">
							<div id="dashboard_centerContent">
								<div id="pa_reports_carousel" class="yui-skin-sam" style="width:100%">
									<ul>
										<li> 
											<div class="reports_carousel_div_class">
												<div class="pa_reports_head">Party Performance Report</div>
												<div class="pa_reports_body">													
													Party Performance Report gives a detailed election results analysis for a party on its performance in an election.
													This report mainly focus on complete party election results of won/lost details in different positions, which include first,second,third upto Nth position dtails and election results in those positions.
													
													<div style="margin-top:10px"><img src="images/icons/indexPage/partyanalysis/report1.png"/></div>
													<div style="float:right;"><a href="javascript:{}" class="viewReportAnc">View</a></div>
												</div>											
											</div>
										</li>
										<li>
											<div class="reports_carousel_div_class">
												<div class="pa_reports_head">Election Comparison Report</div>
												<div class="pa_reports_body">
													Elections Comparison Report gives a glance of compared election results for a party participated any two elections in a detailed view.This report mainly provides a overview  for a user to know wheather the party improved/lost its performance in selected present year when compared to selected previous year.

													<div style="margin-top:10px"><img src="images/icons/indexPage/partyanalysis/report2.png"/></div>

													<div style="float:right;"><a href="javascript:{}" class="viewReportAnc">View</a></div>
												</div>											
											</div> 
										</li>
										<li>
											<div class="reports_carousel_div_class">
												<div class="pa_reports_head">Party Results Report</div>
												<div class="pa_reports_body">
													 Party Results Report gives overall picture for a party in different types of elections like assembly/parliament/zptc/mptc/municipality in different party participated years in a single glance.The results can be classified and viewed in three different views like statewise or districtwise or constituencywise.

													 <div style="margin-top:10px"><img src="images/icons/indexPage/partyanalysis/report3.png"/></div>

													 <div style="float:right;"><a href="javascript:{}" class="viewReportAnc">View</a></div>
												</div>											
											</div> 
										</li>
										<li> 
											<div class="reports_carousel_div_class">
												<div class="pa_reports_head"> Party Influence Report</div>
												<div class="pa_reports_body"> 
													Party Influence Report compares the election results among one party and newly establish party.The resuilts are compared among all the districts wise and the mandals wise.

													<div style="margin-top:10px"><img src="images/icons/indexPage/partyanalysis/report4.png"/></div>

													<div style="float:right;"><a href="javascript:{}" class="viewReportAnc">View</a></div>
												</div>									
											</div> 
										</li>
									</ul>
								</div> 
							</div>
						</td>
					</tr>
				</table>
			</div>
		</div>
		
		<script type="text/javascript">
				initializeIndexPage();
		</script>
			
</body>