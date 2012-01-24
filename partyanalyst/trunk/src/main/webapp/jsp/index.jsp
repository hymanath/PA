<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

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
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/history/history.js">
	</script> 
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

	<script type="text/javascript" src="js/yahoo/yui-gallery/gallery-accordion-min.js">
	</script>
<script type="text/javascript" src="js/connectPeople/connectPeople.js"></script>
<script type="text/javascript" src="js/connectPeople/connectPeopleContent.js"></script>
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

	<!-- JQuery files (Start) -->
<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery-ui-1.8.5.custom.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.core.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.blind.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.explode.min.js"></script>

<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />

<!-- JQuery files (End) -->

	<link href="styles/styles.css" rel="stylesheet" type="text/css" />

	<script type="text/javascript" src="js/cadreManagement/cadre.js" ></script>
	<script type="text/javascript" src="js/indexPage/indexPage.js" ></script>
	<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js" ></script>
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
 <style>
	.yui-skin-sam thead{
		background: #dddddd;
	}
	/*#dashBoardCenterlayout_body table{

		border: 1px solid #dddddd;
	}*/
	 .yui-skin-sam .yui-dt-liner{
	 	margin: 0;
		padding: 4px 10px;
		font: 12px "Trebuchet MS",Arial,Helvetica,sans-serif;
	}
	/* .yui-skin-sam table{
		font-size:12px;
		border: 1px solid #dddddd;
		
	}*/
	a{
		font-size:12px;
		color:green;
	}
	.yui-skin-sam .yui-pg-container{
  		text-align: center;

	}
	.newsImage {
   		height:53px;
   		width:29px;
	 }
	.container {
    	-moz-box-shadow: 0 0 1px rgba(0, 0, 0, 0.25), 0 1px 5px 3px rgba(0, 0, 0, 0.05), 0 5px 4px -3px rgba(0, 0, 0, 0.06);
    	background-color: #FFFFFF;
    	margin: 9px auto 40px;
    	max-width: 800px;
    	padding: 10px;
	}
	#newsHeading{
		color: #FFFFFF;
    	font-weight: bold;
    	height:25px;
		padding:5px;
		background:#0099ff;
		width:50%;
		text-align:center;
  	}
	.pa_reports_head {
    background-color: #3D3D3D;
	background-image:url();
}
.dashboardBar{
width:960px;
background:#3d3d3d;
height:30px;
border-radius:5px 5px 0px 0px;
}
.dashboardHeader{
color:#d3d3d3;float:left;padding:7px 0px 0px 10px;font-weight:bold;font-family:Arial;
}
.dashboardTimeStamp{
color:#fff;float:right;padding:8px 0px 0px 5px;font-family:arial;font-size:11px;margin-right:10px;
}
#todayDate{
color:#fff;float:right;padding:8px 0px 0px 5px;font-family:arial;font-size:11px;margin-right:10px;
}
.edit {
    background-color: #335291;
    color: #FFFFFF;
}

.profileAnc{
display:block;
padding:5px;
background:#a3a3a3;
color:#3d3d3d;
border:1px ridge #fff;
width:150px;
}
.profileAnc:hover{
background:#3d3d3d;
color:#d3d3d3;
text-decoration:none;
}
#noticeBoard_new span{display:block;margin:3px;}
#humanImgDiv a span{display:inline-block;height:25px;width:30px;background:#fff;border:1px solid #3d3d3d;margin-right:10px;}
#humanImgDiv a span img{width:25px;height:25px;}
.dashBoardtabsDiv {
    -moz-border-radius: 5px 5px 5px 5px;
	border-radius: 5px 5px 5px 5px;
    background: none repeat scroll 0 0 #F9F9DA;
    padding: 7px;
	border:1px solid #cdcdcd;
	border-bottom:3px solid #9D5CB2;
}

.dashBoardtabsDivSelected
	{
	background :#F5E4FA; color: #9D5CB2;border-bottom:1px solid #F5E4FA;
	}
.dashBoardtabsDiv a {
    color: #000000;
    cursor: pointer;
    font: 14px trebuchet MS;
    padding: 16px;
	padding-bottom:6px;
    text-decoration: none;
}
   </style>
</head>
	<body onload="menuSlider.init('menu','slide')">
	<div id="username_change_window" style="background-color: #C7CFD2;">
	<div id="username_change_window_inner"></div></div>
	<div id="dashBoardCenterlayout_header" style="margin: 0px 21px;"><center><div style="width:960px;">		
    	
				
                 <div class="dashboardBar">
<span class="dashboardHeader">DASHBOARD</span>		
<span class="dashboardTimeStamp" id="todayDate"></span>		
</div>				
	</div>

		<div id="dashboard_main">

			<div id="dashboard_layout_main">						
			</div>
			<div id="cadreManagementMainDiv" class="yui-skin-sam"></div>
		<div class="yui-skin-sam"><div id="myDialog"></div></div> 
	</div>
	
	<div id="uploadPic_window"><div id="uploadPic_window_inner"></div></div>
			
			<div id="dashBoardLeftlayoutDiv" style="background:#e2e2e2;text-align:center;">
				<div id="humanImgDiv" style="height:auto;padding-bottom:20px;margin-left:auto;margin-right:auto;display:block;text-align:left;">
				<img src="images/icons/indexPage/human_DashBoard.png" style="margin-top: 28px;" />
					<a href="javascript:{}" class="profileAnc" onclick="uploadUserPic()"><span><img src="images/icons/total_index_icons.png" /></span>Upload Photo</a>
							<a href="javascript:{}" class="profileAnc"><span><img src="images/icons/view.png" /></span>View Profile</a>
							
							<c:if test="${hasSubUserEntitlement}"><a href="javascript:{}" onclick="openSubUserRegPopup()" class="profileAnc">
							<span><img src="images/icons/cadreReport/addCadre.png"/></span>Add Sub User</a></c:if>
							<c:if test="${hasSubUserEntitlement == false}"><a href="javascript:{}" onclick="showAlertMsg('noticeBoard_new')" class="profileAnc"><span><img src="images/icons/cadreReport/addCadre.png"/></span>Add Sub User</a></c:if>
							<a class="profileAnc" style="text-decoration:none" href="<c:out value="${pageContext.request.contextPath}" />/connectPeopleAction.action" ><span><img src="images/icons/constituencyPage/groups.png" /></span>DashBoard</a>
							<c:if test="${hasCallCenterEntitlment}">
							<a href="callCenterAction.action" class="profileAnc" ><span><img src="images/icons/callCenter.png" /></span>Call Center</a>
							</c:if>
							<c:if test="${hasCallCenterEntitlment == false}">
							<a onclick="showAlertMsg('noticeBoard_new')" class="profileAnc" onclick=""><span><img src="images/icons/callCenter.png" /></span>Call Center</a>
							</c:if>
							<c:if test="${hasProfileManagement}">
							<a href="profileManagePageAction.action" class="profileAnc"><span><img src="images/icons/profile.png" /></span>Manage Profile</a>
							</c:if>	
							<c:if test="${hasProfileManagement == false}">
							<a onclick="showAlertMsg('noticeBoard_new')" class="profileAnc"><span><img src="images/icons/profile.png" /></span>Manage Profile</a>
							</c:if>	
						<c:if test="${hasNewsMonitoring == true}">
						  <a href="javascript:{};" onclick="openShowNews();" class="profileAnc"><span><img src="images/graph.png" /></span>News Analyse</a>
					   </c:if>
					   <a class="profileAnc" href="userGroupAction.action">
					   <span><img src="images/icons/indexPage/group_icon.png"/></span>User Groups</a>
				</div>		
				<div id="noticeBoard_new" style="width:95%;border:1px solid #3d3d3d;margin-right:auto;margin-left:auto;margin-top:15px;background:#fff;">
				<h4 style="width:100%;height:25px;color:#fff;background:#3d3d3d;">Announcements</h4>
					<span><img src="images/icons/homePage_new/widgetHeaderIcon.jpg" alt="listIcon"/>&nbsp;<a href="javascript:{}" onclick = "openNewAnnouncementPopup()">Add New Announcement</a></span><span>
					    <img src="images/icons/homePage_new/widgetHeaderIcon.jpg" alt="listIcon"/>&nbsp;<a href="javascript:{}" onclick = "openEditAnnouncement()">View All Announcements</a></span>
						
				</div>
			<div id="dashBoardCenterlayoutDiv">
			<%
			/*<div id="dashBoardCenterlayout_header">		
					<table width="100%" style="width:100%;" cellspacing="0" cellpadding="0" border="0">
					<tr>
					<td style="width:3px;"><img src="images/icons/electionResultsAnalysisReport/first.png"/></td>
					<td class="centerSwasthicImage" style="vertical-align: middle;">	
						<table width="100%" style="width:100%;">
							<tr>
								<td align="left"><font class="welcomeUserFont">Welcome </font> <font class="welcomeUserFont" style="color:#4B74C6">User </font></td>
								<td align="right">
									<!--<img src="images/icons/indexPage/clock.png" height="25px"/>
									<div id="todayDate"></div>
								</td>
							</tr>
						</table>						

					</td>
					<td style="width:3px;"><img src="images/icons/electionResultsAnalysisReport/second.png"/></td>					
					</table>
				</div>*/
				%>
		
<div class="dashBoardtabsDiv">
<c:if test="${hasNewsMonitoring == true}">
	<a onclick="tabsDiv('dashBoardNews_Main',this.id)" id="newsTabId">News</a>
	</c:if>
	<a onclick="tabsDiv('impDatesDiv_main',this.id)" id="impDateId">Important Dates</a>
	<a onclick="tabsDiv('impEventsDiv_main',this.id)" id="impEventsId">Events</a>
	<a onclick="tabsDiv('cadresDiv_main',this.id)" id="cadresInfoId">Cadres Info</a>
</div>
			<div id="dashBoardCenterlayout_body_up" class="yui-skin-sam">
				<table width="100%" style="height:100%">
						<tr>
							<td style="vertical-align:top;width:70%;">
								<table width="100%" style="padding:10px;">
									<tr>
										<td>
											<div id="impEventsDiv_main" style="display:none">
												<div id="impEventsDiv_head">
													<table><tr>
														<td><img src="images/icons/indexPage/cal.png"/></span></td>
														<td style="vertical-align:center;"><span class="dashBoardCenterContentHeader">Today's Event</span></td>
													</tr></table>
												</div>
												<div id="impEventsDiv_body">
												<span class="dashBoardCenterContentBody" style="color:#4B74C6">You have ${eventCount} event(s) scheduled today</span>
													<ul class="dashBoardContentList">
													<c:forEach var="impEvents" items="${cadreManagementVO.userEvents}" >
														<li><c:out value="${impEvents.eventDisplayTitle}" /></li>	
													</c:forEach>										
													</ul>
												</div>

												<div id="impEventsDiv_footer" style="text-align:right">
													<span class="dashBoardLinks">
														<a href="cadreManagementAction.action#yui-gen3" class="indexPageAnc">View All</a>
													</span>
													<span class="dashBoardLinks">
														<a href="javascript:{}" class="indexPageAnc" onclick="buildNewEventPopup()">Create</a>
													</span>
												</div>
											</div>
										</td>
									</tr>
									
									<tr>
										<td>
											<div id="impDatesDiv_main" style="display:none">
												<div id="impDatesDiv_head">
													<table><tr>
														<td><img src="images/icons/indexPage/cal.png"/></span></td>
														<td style="vertical-align:center;"><span class="dashBoardCenterContentHeader">Imp Dates</span></td>
													</tr></table>
												</div>
												<div id="impDatesDiv_body">
													<span class="dashBoardCenterContentBody" style="color:#4B74C6">You have ${impDateCount} Imp date(s) scheduled today</span>
													<ul class="dashBoardContentList">
														<c:forEach var="impDates" items="${cadreManagementVO.userImpDates}" >
														<li><c:out value="${impDates.title}" /></li>	
													</c:forEach>													
													</ul>
												</div>
												<div id="impDatesDiv_footer" style="text-align:right">
													<span class="dashBoardLinks">
														<a href="cadreManagementAction.action#cadreImpDatesDiv" class="indexPageAnc">View All</a>
													</span>
													<span class="dashBoardLinks">
														<a href="javascript:{}" onclick="buildNewImpDatePopup()" class="indexPageAnc">Create</a>
													</span>
												</div>
											</div>
										</td>
									</tr>	
									<tr>
										<td>
											<div id="cadresDiv_main" style="display:none">
												<div id="cadresDiv_head">
													<table><tr>
														<td><img src="images/icons/indexPage/group_icon.png"/></span></td>
														<td style="vertical-align:center;"><span class="dashBoardCenterContentHeader">Cadres Info</span></td>
													</tr></table>
												</div>
												<div id="cadresDiv_body" style="margin-bottom: -14px;">
													<span class="dashBoardCenterContentBody" style="color:#4B74C6"></span>
													<ul class="dashBoardContentList">
													<c:forEach var="cadreLevels" items="${cadreManagementVO.cadresByCadreLevel}" >
														<li>${cadreLevels.key} Level Cadres - ${cadreLevels.value} </li>
													</c:forEach>													
													</ul>
												</div> 
												<div id="cadresDiv_footer" style="text-align:right">
													<span class="dashBoardLinks">
														<a href="cadreManagementAction.action#regionLevelCadreDivHead" class="indexPageAnc">View All</a>
													</span>
													<span class="dashBoardLinks">
														<!--<span><img src="images/icons/indexPage/sms_cell.png"/></span>
														<span>
															<a href="javascript:{}" id="sendSMSAnc" onclick="buildSMSPopup();" class="indexPageAnc">Send SMS</a>				
														</span>	-->													
													</span>
												</div>
											</div>
										</td>
									</tr>
									<tr><td>
									<div id="dashBoardNews_Main"  style="display:none">
									<c:if test="${hasNewsMonitoring == true}">
		
	      <div id="newsHeading" align="top">
			<span>News Overview</span></div>
		
		
		<div id="showNewsCount" style="margin-top:25px;display:none;"></div>
		<table><tr><td><div id="newsCount" style="padding-left:5px;padding-bottom:5px;padding-top:15px;float:left;width:100%"></div></td></tr></table>
		
		<div id="dashBoardCenterlayout_body" class="yui-skin-sam"></div>
		

</c:if>

</div>
</td></tr>
								</table>
							</td>
							
							<td style="vertical-align:top;">

								<div id="staticDataView_main" class="yui-skin-sam">
									<div id="staticDataView_head"></div>
									<div id="staticDataView_body">
										
										<!--<ul id="dashboardRightLayoutList">
											<li>View Your State</li>
											<li>View Your District</li>
											<li>View Your Constituency</li>
											<li>View Your Mandal</li>											
										</ul>
									</div>
									<div id="staticDataView_footer"></div>
								</div>-->

							</td>
						</tr>
					</table>
					
				</td>	
				</tr>
				</table>
					
		<!--
			</div>
		<div id="dashboard_reportsNav_main" style="margin-left:10px;"><center><div style="width:970px;">
				<table id="dashboard_main_table" style="width:960px;" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td style="vertical-align:top;width:250px;">
							<div id="dashboard_leftNav">								
								<ul id="pa_reportsList_main">
									<li id="partyAnalysisListItem">										
										<div id="partyAnalysis_div" class="reportGroupClass reportGroupClassSelected" onclick="showReportsInCarousel('partyAnalysis')">Party Analysis</div>
									</li>
									<li id="politicianAnalysisListItem">
										<div id="politicianAnalysis_div" class="reportGroupClass" onclick="showReportsInCarousel('politicianAnalysis')">Politician Analysis</div>
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
													<div style="margin-top:0px"><img src="images/icons/indexPage/partyanalysis/report1.png"/></div>
													<div style="height: 138px; font-size: 12px;">
														Party Performance Report gives a detailed election results analysis for a party on its performance in an election.
														This report mainly focus on complete party election results of won/lost details in different positions, which include first,second,third upto Nth position dtails and election results in those positions.
													</div>
													<div style="float:right;padding-top:4px;"><a href="partyPerformanceMain.action" class="viewReportAnc">View</a></div>
												</div>											
											</div>
										</li>
										<li>
											<div class="reports_carousel_div_class">
												<div class="pa_reports_head">Election Comparison Report</div>
												<div class="pa_reports_body">
													<div style="margin-top:0px"><img src="images/icons/indexPage/partyanalysis/report2.png"/></div>
													<div style="height: 138px; font-size: 12px;">
													Elections Comparison Report gives a glance of compared election results for a party participated any two elections in a detailed view.This report mainly provides a overview  for a user to know wheather the party improved/lost its performance in selected present year when compared to selected previous year.
													</div>
													<div style="float:right;padding-top:4px;"><a href="electionComparisonAction.action" class="viewReportAnc">View</a></div>
												</div>											
											</div> 
										</li>
										<li>
											<div class="reports_carousel_div_class">
												<div class="pa_reports_head">Party Results Report</div>
												<div class="pa_reports_body">
													 <div style="margin-top:0px"><img src="images/icons/indexPage/partyanalysis/report3.png"/></div>
													<div style="height: 138px; font-size: 12px;">
													 Party Results Report gives overall picture for a party in different types of elections like assembly/parliament/zptc/mptc/municipality in different party participated years in a single glance.The results can be classified and viewed in three different views like statewise or districtwise or constituencywise.
													</div>
													 <div style="float:right;padding-top:4px;"><a href="partyResultsCriteriaAction.action" class="viewReportAnc">View</a></div>
												</div>											
											</div> 
										</li>
										<li> 
											<div class="reports_carousel_div_class">
												<div class="pa_reports_head"> Party Influence Report</div>
												<div class="pa_reports_body"> 
													<div style="margin-top:0px"><img src="images/icons/indexPage/partyanalysis/report4.png"/></div>
													<div style="height: 138px; font-size: 12px;">
													Party Influence Report compares the election results among one party and newly establish party.The resuilts are compared among all the districts wise and the mandals wise.
													</div>
													<div style="float:right;padding-top:4px;"><a href="javascript:{}" class="viewReportAnc">View</a></div>
												</div>									
											</div> 
										</li>
									</ul>
								</div> 
							</div>
						</td>
					</tr>
				</table></center>
				</div>
			</div>-->
		</div>

<div id="newsPopUpDiv">
	<div id="newsInnerContetDiv"></div>
</div>
		
		<script type="text/javascript">
		
		$(document).ready(function() {
    $("#tabs").tabs();
		});
var uname = '${sessionScope.USER.userName}';
var emailId='${sessionScope.USER.email}';
function showDetailsForAvailability(myresults){
var email = document.getElementById("emailField").value;
var result = document.getElementById("feedback_window_errorMsg");
	var str='';
	if(myresults==121){
		str = '<font color="#000000">Sending Your Request.Please wait</font>';
		str += '<img src="images/icons/partypositions.gif" style="padding-left:10px;" width="18" height="11">'

		var jsObj=
		{		
 				userName:email,
				task:"changeRegisteredUserNameToEmail"						
		};	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/changeRegisteredUserNameToEmailAction.action?"+rparam;						
		callAjax(jsObj,url);
		}	
	else{
		str+='<div style="color:red"> Username is not available</div>';	
	}
	result.innerHTML = str;

}

<c:if test="${hasNewsMonitoring == true}">
	//buildNews();
	getNews("byTodayDate","getCount","Public","","","","","","");
	tabsDiv('dashBoardNews_Main','newsTabId');
	//getNews("byTodayDate","getNews","Public","","","","","","");
</c:if>

function tabsDiv(divElmt,selectedTabId){
	
	if(divElmt)
		document.getElementById(divElmt).style.display ='block';

	$("#"+selectedTabId).css("color", "#9D5CB2"); $("#"+selectedTabId).addClass("dashBoardtabsDivSelected");
	var newsHeadingElmt = document.getElementById("dashBoardNews_Main");
	var cadresDivmainElmt = document.getElementById("cadresDiv_main");
	var impEventsDivMainElmt = document.getElementById("impEventsDiv_main");
	var impDatesDivMainElmt = document.getElementById("impDatesDiv_main");
	
	if(divElmt !='cadresDiv_main'){
		
	if(cadresDivmainElmt.style.display == 'block'){
		$("#cadresInfoId").css("color", "#000000").removeClass("dashBoardtabsDivSelected");
		cadresDivmainElmt.style.display ='none';
	 }
	}
	 if(divElmt != 'impEventsDiv_main'){
		
		if(impEventsDivMainElmt.style.display =='block'){
			 $("#impEventsId").css("color", "#000000").removeClass("dashBoardtabsDivSelected");
			impEventsDivMainElmt.style.display ='none';
		 }
		}
		if(divElmt != 'impDatesDiv_main'){
			 
		 if(impDatesDivMainElmt.style.display =='block'){
		$("#impDateId").css("color", "#000000").removeClass("dashBoardtabsDivSelected");
			 impDatesDivMainElmt.style.display ='none';
		 }
		}
		if(divElmt != 'dashBoardNews_Main'){
			
		 if(newsHeadingElmt.style.display =='block'){
			 $("#newsTabId").css("color", "#000000").removeClass("dashBoardtabsDivSelected");
			 newsHeadingElmt.style.display ='none';
		 }
		}

}
showTabs();
function showDetails(myresults)
{
	
	var result = document.getElementById("feedback_window_errorMsg");
	 str='';
	
	if(myresults == null){
		str+='<div style="color:red"> Your request not submitted. Please try again.</div>';
	}
	
	else{
		$("#username_change_window").dialog("destroy");
		afterUserNameChanges();
		return;
	}
	result.innerHTML = str;
	
}

function afterUserNameChanges(){
var email = document.getElementById("emailField").value;
 $("#username_change_window").dialog({
			resizable:false,
			width: 600,
			minHeight:150,
			show:'slide',
			modal:true
		});	
		$(".ui-dialog-titlebar").hide();

		var elmt = document.getElementById("username_change_window_inner");

		var str = '';
		str += '<div id="feedback_window_head">Do You Want To Change Your UserName As Your Email</div>';
		str += '<div id="feedback_window_body"';
		str +='style="font-weight:bold;color:green;text-align:center;">';
		str += 'Your New UserName Is :'+email;
		str += '</div>';
		str += '<div id="feedback_window_footer" class="yui-skin-sam">';
		str += '	<table width="100%">';
		str += '	<tr><td>';
		str += '	<input id="OkButton" type="button" width="50px" align="center"' ;
		str += '   value="OK"></input></td>';
		str += '	</tr>';
		str += '	</table>';	
		str += '</div>';
		elmt.innerHTML = str;

		var oPushButton2 = new YAHOO.widget.Button("OkButton");

		oPushButton2.on("click",function(){
			$("#username_change_window").dialog("destroy");
		});
}



function changeUserName(){

var email = document.getElementById("emailField").value;
	var resultDIVEle = document.getElementById("feedback_window_errorMsg");
	resultDIVEle.innerHTML = "";
	var reg = /^([A-Za-z0-9_\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

	if(email.length == 0)
		resultDIVEle.innerHTML = "<font color='red'><b>Please Enter EmailId.</b></font>";
	
	else if(reg.test(email) == false)
		resultDIVEle.innerHTML = "<font color='red'><b>Please provide correct Email Address.</b></font>";
   
	else if(emailId == '' || emailId == null){
       document.getElementById("feedback_window_errorMsg").innerHTML = " ";
       var jsObj=
		{		
 				userName:email,
				task:"checkRegisteredUserNameAvailability"						
		};	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "checkFreashRegisteredUserNameAvailabilityAction.action?"+rparam;				
		callAjax(jsObj,url);

	}
		else if(reg.test(email) == true)
	    {
		
		document.getElementById("feedback_window_errorMsg").innerHTML = " ";
 		var jsObj=
		{		
 				userName:email,
				task:"checkRegisteredUserNameAvailability"						
		};	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "checkRegisteredUserNameAvailabilityAction.action?"+rparam;						
		callAjax(jsObj,url);

	}
	
	
	}
function showUserNameChangePanel(uname){

	var alertIndicator = '${changedUserName}';
	
	if(alertIndicator == null || alertIndicator == 'false')
		return;

var reg = /^([A-Za-z0-9_\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
if(reg.test(uname) == false)
	{

$("#username_change_window").dialog({
			resizable:false,
			width: 600,
			minHeight:225,
			show:'slide',
			modal:true
		});	
		$(".ui-dialog-titlebar").hide();

		var elmt = document.getElementById("username_change_window_inner");

		var str = '';
		str += '<div id="feedback_window_head">Do You Want To Change Your UserName As Your Email</div>';
		 if(emailId == ''|| emailId== null)
		str += '<div id="feedback_window_body">';
		 else
        str += '<div id="feedback_window_body" style="padding-bottom:48px">';
		str += '	<div id="feedBackNote_div">';
		str += '		<table>';
		str += '		<tr>';
	 if(emailId == ''|| emailId== null){

		str += '		<td><img src="images/icons/infoicon.png"></td>';
		str += '		<td>Fields marked with (<font color="red">*</font>) are mandatory</td>';
		str += '		</tr>';
		str += '		</table>';
		str += '	</div>';
		str += '	<div id="feedBackForm_div">';
		str += '		<table id="feedbackTable" width="100%">';
		str += '		<tr>';
       	str += '		<th><font color="red">*</font>Enter Your EmailId </th>';
		str += '		<td>';
		str += '			<input type="text" id="emailField" size="25"/>';
		str += '		</td>';
		str += '		</tr>';
        }
		else{
        str += '<input type="hidden" value='+emailId+' id="emailField" size="25"/>'; 
		str += '		<tr>';
       	str += '		<th></th>';
        str += '		<td>';
        str += '		</td>';
		str += '		</tr>';
	    }
		str += '		</table>';
		str += '	</div>';
		str += '</div>';
		str += '<div id="feedback_window_footer" class="yui-skin-sam">';
		str += '	<table width="100%">';
		str += '	<tr>';
		str += '	<td width="65%" align="left"><div id="feedback_window_errorMsg"></div></td>';
		str += '	<td width="35%" align="right">';
		str += '		<input id="postButton" type="button" value="Yes"></input>';
		str += '		<input style="width:52px; text-align:center;" id="cancelButton" type="button" value="No"></input>';
		str += '	</td>';
		str += '	</tr>';
		str += '	</table>';	
		str += '</div>';
		elmt.innerHTML = str;

		var oPushButton1 = new YAHOO.widget.Button("postButton");  
		var oPushButton2 = new YAHOO.widget.Button("cancelButton");

		oPushButton1.on("click",function(){
			changeUserName();
		});

		oPushButton2.on("click",function(){
			$("#username_change_window").dialog("destroy");
		});
	}
}
		
		var labelResources = { <%		
				ResourceBundle rb = ResourceBundle.getBundle("global_ErrorMessages");
				String regionLevel = rb.getString("regionLevel");
				String typeMessage = rb.getString("typeMessage");
				String smsSuccess  = rb.getString("smsSuccess");
				String remainingSMS  = rb.getString("remainingSMS");
				String smsQuotaClosed = rb.getString("smsQuotaClosed");
					
				%> }
				
		var smsHidden = 1;
		<c:forEach var="mlaConstituencies"  items="${mlaConstituenciesList}" >
			var ob={
						id:'${mlaConstituencies.id}',
						name:'${mlaConstituencies.name}'
					};
			indexPageMainObj.mlaConstituencies.push(ob);	
		</c:forEach>

		<c:forEach var="mpConstituencies"  items="${mpConstituenciesList}" >
			var ob={
						id:'${mpConstituencies.id}',
						name:'${mpConstituencies.name}'
					};
			indexPageMainObj.mpContituencies.push(ob);	
		</c:forEach>
		// for integrating cadre sms					
		function callAjax(jsObj,url)
		{		
			
	 		var callback = {			
	 		               success : function( o ) {
								try {
						var myResults = YAHOO.lang.JSON.parse(o.responseText);	
						 
						if(jsObj.task == "getUserLocation")
							fillDataOptions(myResults,jsObj);	
						if(jsObj.task == "fillSelectElements")
							fillSelectElement(myResults,jsObj);
						if(jsObj.task == "sendSMS")
							displaySuccessMessage(myResults,jsObj);
						if(jsObj.task=="CADRE_LEVEL")
							fillDataForCadreLevel(myResults,jsObj);
					 if(jsObj.task=="checkRegisteredUserNameAvailability")		
					 showDetailsForAvailability(myResults);	
					  if(jsObj.task=="changeRegisteredUserNameToEmail")		
					 showDetails(myResults);	
					 
						
						
						}catch (e) {   
						  	alert("Invalid JSON result" + e);   
						   }  
	 		               },
	 		               scope : this,
	 		               failure : function( o ) {
	 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
	 		                         }
	 		               };

	 		YAHOO.util.Connect.asyncRequest('GET', url, callback);
		}
		function getUserLocationData(val,type)
		{			
			if(type == 'event' || type == 'Editevent' )
			{
				var str="cadreLevelDivId_"+type;
				
				cadreAnim = new YAHOO.util.Anim(str, {
					height: {
						to: 150 
					} 
				}, 1, YAHOO.util.Easing.easeOut);

				cadreAnim.animate();
				var ancElmt = document.getElementById('cadreLevelDivId_'+type+'_anc');
				if(ancElmt)
				ancElmt.style.display = 'block';
			}
			

			var eventCadreDivHeadElmt = document.getElementById(type+"CadreDivHead");
			var eventCadreDivBodyElmt = document.getElementById(type+"CadreDivBody");

			if(eventCadreDivHeadElmt && eventCadreDivBodyElmt)
			{
				eventCadreDivHeadElmt.innerHTML="";
				eventCadreDivBodyElmt.innerHTML="";
			}
			
			var jsObj={
						value:val,
						taskType:type,
						task:"getUserLocation"
					  };
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/cadreSMSLocationWiseData.action?";
			callAjax(jsObj,url);
		}
		function fillDataOptions(results,jsObj)
		{			
			//Setting values for region type..
			var regTask = jsObj.taskType;
			if(jsObj.taskType == 'sms')
			{
				var regionTypeElmtLabel = document.getElementById("region_type_Label");
				var regionTypeElmtData = document.getElementById("region_type_Data");

				var regionTypeSelectElmtLabel = document.getElementById("region_select_Label");
				var regionTypeSelectElmtData = document.getElementById("region_select_Data");

				var smsCadreNameIncludeLabel = document.getElementById("sms_cadre_name_include_label");
				var smsCadreNameIncludeValue = document.getElementById("sms_cadre_name_include_value");

				if(smsCadreNameIncludeLabel)
					smsCadreNameIncludeLabel.innerHTML="Include Cadre Name";
				
				var smsCadreIncludeStr='<input type="radio" id="include_cadre_name" name="include_cadre_name" value="YES" /> Yes';
				   smsCadreIncludeStr+='<input type="radio" id="no_cadre_name" name="include_cadre_name" value="NO" checked="checked" /> No    ';
				   smsCadreIncludeStr+='.       with cadre names performance degrades';
				if(smsCadreNameIncludeValue)
					smsCadreNameIncludeValue.innerHTML=smsCadreIncludeStr;

				//------
				
				
				var smsUserNameIncludeLabel = document.getElementById("sms_user_name_include_label");
				var smsUserNameIncludeValue = document.getElementById("sms_user_name_include_value");

				if(smsUserNameIncludeLabel)
					smsUserNameIncludeLabel.innerHTML="Include User Name";
				
				var smsUserIncludeStr='<input type="radio" id="include_user_name" name="include_user_name" value="YES" onclick="enableTextBox();" /> Yes';
				smsUserIncludeStr+='<input type="radio" id="no_user_name" name="include_user_name" value="NO" checked="checked" onclick="disableTextBox();" /> No    ';
				smsUserIncludeStr+='.                    <input type="text" id ="user_name" name="user_name" value="${sessionScope.UserName}" disabled/>';
				if(smsUserNameIncludeValue)
					smsUserNameIncludeValue.innerHTML=smsUserIncludeStr;

			}
			else if(jsObj.taskType == 'event' || jsObj.taskType == 'action' || jsObj.taskType == 'Editevent')
			{			
				var regionTypeElmtLabel = document.getElementById(regTask+"_region_type_Label");
				var regionTypeElmtData = document.getElementById(regTask+"_region_type_Data");

				var regionTypeSelectElmtLabel = document.getElementById(regTask+"_region_select_Label");
				var regionTypeSelectElmtData = document.getElementById(regTask+"_region_select_Data");

			}

			var str='';
			for(var i in results.regions)
			{
				str+='<input type="radio" name="region_type_radio" value="'+results.regions[i].name+'" onclick="displayRegionsSelect(this.value,\''+regTask+'\')" /> '+results.regions[i].name+'';
			}		

			if(regionTypeElmtLabel)
				regionTypeElmtLabel.innerHTML="Select Level";
			if(regionTypeElmtData)
				regionTypeElmtData.innerHTML=str;
			
			//---------
			//Setting values for select box..
			
			if(regionTypeSelectElmtLabel)
				regionTypeSelectElmtLabel.innerHTML="Select Location";

			var regionStr='';
			
			regionStr+='<select id="'+regTask+'_StateSelect" class="selectBox" onchange="getNextRegions(this.id,\'STATE\',\''+regTask+'\')" disabled="true">';
			if(results.states != "")
			{
				for(var state in results.states)
				{
					regionStr+='<option value="'+results.states[state].id+'">'+results.states[state].name+'</option>';
				}
			}
			else
			{
				regionStr+='<option value="0"> Select State</option>';
			}
			regionStr+='</select>';	

			
			regionStr+='<select id="'+regTask+'_DistrictSelect" class="selectBox" onchange="getNextRegions(this.id,\'DISTRICT\',\''+regTask+'\')" disabled="true">';
			if(results.districts != "")
			{
				for(var district in results.districts)
				{
					regionStr+='<option value="'+results.districts[district].id+'">'+results.districts[district].name+'</option>';
				}
			}
			else
			{
				regionStr+='<option value="0"> Select District</option>';
			}
			regionStr+='</select>';
			
			
			regionStr+='<select id="'+regTask+'_ConstituencySelect" class="selectBox" onchange="getNextRegions(this.id,\'CONSTITUENCY\',\''+regTask+'\')" disabled="true">';
			if(results.constituencies != "")
			{
				for(var consti in results.constituencies)
				{
					regionStr+='<option value="'+results.constituencies[consti].id+'">'+results.constituencies[consti].name+'</option>';
				}
			}
			else
			{
				regionStr+='<option value="0"> Select Constituency</option>';
			}
			regionStr+='</select>';
		
		
		
			regionStr+='<select id="'+regTask+'_MandalSelect" class="selectBox" onchange="getNextRegions(this.id,\'MANDAL\',\''+regTask+'\')" disabled="true">';
			if(results.mandals != "")
			{
				for(var mandal in results.mandals)
				{
					regionStr+='<option value="'+results.mandals[mandal].id+'">'+results.mandals[mandal].name+'</option>';
				}
			}
			else
			{	
				regionStr+='<option value="0"> Select Mandal</option>';
			}
			regionStr+='</select>';

			
			regionStr+='<select id="'+regTask+'_VillageSelect" class="selectBox" disabled="true">';
			if(results.villages != "")
			{
				for(var village in results.villages)
				{
					regionStr+='<option value="'+results.villages[village].id+'">'+results.villages[village].name+'</option>';
				}
			}
			else
			{	
				regionStr+='<option value="0"> Select Village</option>';
			}
			regionStr+='</select>';
		
			if(regionTypeSelectElmtData)
				regionTypeSelectElmtData.innerHTML=regionStr;

			if(jsObj.taskType == 'event' || jsObj.taskType == 'action' || jsObj.taskType == 'Editevent')
			{
				var submitStr='<input type="button" onclick="getCadresForEvent(\''+regTask+'\')" value="Get Cadres"/>';
				var submitDiv = document.getElementById(regTask+"_region_submit");
				submitDiv.innerHTML = submitStr;
				return;
			}
			//---------------
			// Displaying Text Area
			var smsTextElmtLabel = document.getElementById("sms_text_Label");
			var smsTextElmtData = document.getElementById("sms_text_Data");
			
			if(smsTextElmtLabel)
				smsTextElmtLabel.innerHTML="SMS Text";

			var smsStr='';
			smsStr+='<div><textarea rows="5" cols="50" id="smsTextArea" onkeyup="limitText(\'smsTextArea\',\'maxcount\',200)" ></textarea></div> ';
			smsStr+='<div id="limitDiv">';
			smsStr+='<table style="width:100%;"><tr>';
			smsStr+='<td style="width:50%;"><div id="remainChars"><span id="maxcount">200 </span> <span>chars remaining..</span></div></td>';
			smsStr+='<td style="width:50%;"><div>Should not exceed 200 chars</div></td>';
			smsStr+='</tr></table>';
			smsStr+='</div>';	
			
			if(smsTextElmtData)
				smsTextElmtData.innerHTML=smsStr;

			//--------------------------
			//Displaying button

			var buttonDiv = document.getElementById("button_div");
			var bstr='';
			bstr+='<input type="button" value="Send SMS" onclick="sendSMS()"/>';
			if(buttonDiv)
			{
				buttonDiv.innerHTML=bstr;
			}

		
		}
		function displayRegionsSelect(val,regTask)
		{
			var stateSelectElmt = document.getElementById(regTask+"_StateSelect");
			var districtSelectElmt = document.getElementById(regTask+"_DistrictSelect");
			var constituencySelectElmt = document.getElementById(regTask+"_ConstituencySelect");
			var mandalSelectElmt = document.getElementById(regTask+"_MandalSelect");
			var villageSelectElmt = document.getElementById(regTask+"_VillageSelect");
			
			stateSelectElmt.disabled=true;
			districtSelectElmt.disabled=true;	
			constituencySelectElmt.disabled=true;	
			mandalSelectElmt.disabled=true;	
			villageSelectElmt.disabled=true;	

			if(val == "District")
			{
				stateSelectElmt.disabled=false;
				districtSelectElmt.disabled=false;			
			}
			if(val == "Constituency")
			{
				stateSelectElmt.disabled=false;
				districtSelectElmt.disabled=false;	
				constituencySelectElmt.disabled=false;	
			}
			if(val == "Mandal")
			{
				stateSelectElmt.disabled=false;
				districtSelectElmt.disabled=false;
				constituencySelectElmt.disabled=false;	
				mandalSelectElmt.disabled=false;	
			}
			if(val == "Village")
			{
				stateSelectElmt.disabled=false;
				districtSelectElmt.disabled=false;	
				constituencySelectElmt.disabled=false;	
				mandalSelectElmt.disabled=false;	
				villageSelectElmt.disabled=false;	
			}		
		}
		function limitText(limitField, limitCount, limitNum)
		{		
			var limitFieldElmt = document.getElementById(limitField);
			var limitCountElmt = document.getElementById(limitCount);

			if (limitFieldElmt.value.length > limitNum) 
			{
				limitFieldElmt.value = limitFieldElmt.value.substring(0, limitNum);			
			}
			else
			{			
				limitCountElmt.innerHTML = limitNum - limitFieldElmt.value.length+"";
			}
		}
		function sendSMS()
		{
			var val= null;
			var region_type_AlertEl = document.getElementById("region_type_Alert");
			var sms_text_AlertEl = document.getElementById("sms_text_Alert");
			if(region_type_AlertEl)
				region_type_AlertEl.innerHTML = '';
			if(sms_text_AlertEl)
				sms_text_AlertEl.innerHTML = '';
			for( i = 0; i < document.smsForm.region_type_radio.length; i++ )
			{
				if( document.smsForm.region_type_radio[i].checked == true )
					val = document.smsForm.region_type_radio[i].value;
			}
			if(val == null)
			{	
				if(region_type_AlertEl)
				region_type_AlertEl.innerHTML = 'Please Select Region Level!'
				return;
			}	
			var valSelect = document.getElementById("sms_"+val+"Select");
			var textAreaElmt = document.getElementById("smsTextArea");
			
			var include_cadre ="NO";
			var include_user ="NO";
			//var user_name ='Thx ${sessionScope.UserName}';
			var elements = document.getElementsByTagName('input'); 
			for(var i=0;i<elements.length;i++)
			{
				if(elements[i].type=="radio" && elements[i].name=="include_user_name" && elements[i].checked==true)
					include_user = elements[i].value;
				else if(elements[i].type=="radio" && elements[i].name=="include_cadre_name" && elements[i].checked==true)
					include_cadre = elements[i].value;
			}
			

			textAreaElmtValue = textAreaElmt.value
			
			if(textAreaElmtValue == '')
			{
				sms_text_AlertEl.innerHTML = 'Please Type A Message!';
				return;
			}	
			if(include_user=='YES'){
				if(document.getElementById('user_name')!=null && document.getElementById('user_name').value!='' )
					textAreaElmtValue = textAreaElmtValue + ' Thx ' + document.smsForm.user_name.value;
			}
			var valSelectValue = valSelect.options[valSelect.selectedIndex].value;
			val=val.toUpperCase();
			
			var jsObj={
						SMS_LEVEL_TYPE: val,
						SMS_LEVEL_VALUE: valSelectValue,
						SMS_MESSAGE: textAreaElmtValue,
						SMS_INCLUDE_CADRE_NAME: include_cadre,
						task:"sendSMS"
					  };
			
			
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/sendCadreSMS.action?"+rparam+"&smsHidden="+smsHidden;
			callAjax(jsObj,url);
			
		}
		
		function smsRenewalMessage()
		{
			var elmt = document.getElementById('smsErrorPopupDiv');
			var divChild = document.createElement('div');
			divChild.setAttribute('id','smsErrorDiv');
			
			var str = '';
			str	+= '<div id="smsErrorMain" style="padding:10px;">';
			str	+= '	<table id="loginDetailsTable" width="100%">';
			str	+= '		<tr>';
			str	+= '			<th colspan="3" align="left">';
			str	+= '				Your SMS Credentials are expired ';
			str	+= '			</th>';		
			str	+= '		</tr>';
			str	+= '		<tr>';
			str	+= '			<td colspan="3">Please contact us @  </td>';
			str	+= '		</tr>';
			str	+= '		<tr>';
			str	+= '			<th align="left">Phone </th><td>: </td><td> +91-40-40124153</td>';
			str	+= '		</tr>';
			str	+= '		<tr>';
			str	+= '			<th align="left">Mail </th><td>: </td><td> license@itgrids.com</td>';
			str	+= '		</tr>';
			str	+= '	</table>';	
			str	+= '</div>';
			divChild.innerHTML=str;		
			
			elmt.appendChild(divChild);	
			if(popupPanel)
				popupPanel.destroy();
			popupPanel = new YAHOO.widget.Dialog("smsErrorDiv",
					{ 
						 height:'150px',
						 width:'250px',
			             fixedcenter : true, 
			             visible : true,
			             constraintoviewport : true, 
			    		 iframe :true,
			    		 modal :true,
			    		 hideaftersubmit:true,
			    		 close:true,
						 draggable:true
		             } ); 
			popupPanel.render();
		}
		//cadre levelwise
		function getUsersCadreLevelData(value,type)
	{		
		var str="cadreLevelDivId_"+type;
		
		cadreAnim = new YAHOO.util.Anim(str, {
			height: {
				to: 150 
			} 
		}, 1, YAHOO.util.Easing.easeOut);

		cadreAnim.animate();

		var ancElmt = document.getElementById('cadreLevelDivId_'+type+'_anc');
		if(ancElmt)
			ancElmt.style.display = 'block';

		var eventCadreDivHeadElmt = document.getElementById(type+"CadreDivHead");
		var eventCadreDivBodyElmt = document.getElementById(type+"CadreDivBody");

		if(eventCadreDivHeadElmt && eventCadreDivBodyElmt)
		{
			eventCadreDivHeadElmt.innerHTML="";
			eventCadreDivBodyElmt.innerHTML="";
		}

		var jsObj={
				taskType:type,
				task:"CADRE_LEVEL"
			  };
		var url = "<%=request.getContextPath()%>/usersCadreLevelData.action";	
		callAjax(jsObj,url);
	}
		function fillDataForCadreLevel(results,jsObj)
		{
			var successDivElmt=	 document.getElementById("successDiv");
			successDivElmt.innerHTML="";
			
			var actionVal = jsObj.taskType;

			if(jsObj.taskType == "sms")
			{
				var regionTypeElmtLabel = document.getElementById("region_type_Label");
				var regionTypeElmtData = document.getElementById("region_type_Data");
				var regionTypeSelectElmtLabel = document.getElementById("region_select_Label");
				var regionTypeSelectElmtData = document.getElementById("region_select_Data");
			}
			else if(jsObj.taskType == "event" || jsObj.taskType == "action")
			{			
				var regionTypeElmtLabel = document.getElementById(actionVal+"_region_type_Label");
				var regionTypeElmtData = document.getElementById(actionVal+"_region_type_Data");
				var regionTypeSelectElmtLabel = document.getElementById(actionVal+"_region_select_Label");
				var regionTypeSelectElmtData = document.getElementById(actionVal+"_region_select_Data");
			}
			regionTypeElmtLabel.innerHTML="Select Cadre Level";

			var str='';
			for(var i in results)
			{
				str+='<input type="radio" name="region_type_radio" value="'+results[i].id+'"> '+results[i].name+' ';
			}
			regionTypeElmtData.innerHTML=str;
			//--------------


			if(regionTypeSelectElmtLabel && regionTypeSelectElmtData)
			{
				regionTypeSelectElmtLabel.innerHTML="";
				regionTypeSelectElmtData.innerHTML="";
			}

			if(jsObj.taskType == "event" || jsObj.taskType == "action")
			{
				var submitStr='<input type="button" onclick="getCadresLevelForEvent(\''+jsObj.taskType+'\')" value="Get Cadres"/>';
				var submitDiv = document.getElementById(actionVal+"_region_submit");
				submitDiv.innerHTML = submitStr;
				return;
			}
			//-------------
			
			var smsCadreNameIncludeLabel = document.getElementById("sms_cadre_name_include_label");
			var smsCadreNameIncludeValue = document.getElementById("sms_cadre_name_include_value");

			if(smsCadreNameIncludeLabel)
				smsCadreNameIncludeLabel.innerHTML="Include Cadre Name";
			
			var smsCadreIncludeStr='<input type="radio" id="include_cadre_name" name="include_cadre_name" value="YES" /> Yes';
			   smsCadreIncludeStr+='<input type="radio" id="no_cadre_name" name="include_cadre_name" value="NO" checked="checked" /> No    ';
			   smsCadreIncludeStr+='.       with cadre names performance degrades';
			if(smsCadreNameIncludeValue)
				smsCadreNameIncludeValue.innerHTML=smsCadreIncludeStr;

			//------
			
			
			var smsUserNameIncludeLabel = document.getElementById("sms_user_name_include_label");
			var smsUserNameIncludeValue = document.getElementById("sms_user_name_include_value");

			if(smsUserNameIncludeLabel)
				smsUserNameIncludeLabel.innerHTML="Include User Name";
			
			var smsUserIncludeStr='<input type="radio" id="include_user_name" name="include_user_name" value="YES" onclick="enableTextBox();" /> Yes';
			smsUserIncludeStr+='<input type="radio" id="no_user_name" name="include_user_name" value="NO" checked="checked" onclick="disableTextBox();" /> No    ';
			smsUserIncludeStr+='. <input type="text" id ="user_name" value="${sessionScope.UserName}" name="user_name" disabled/>';
			if(smsUserNameIncludeValue)
				smsUserNameIncludeValue.innerHTML=smsUserIncludeStr;

			//-------------------------------------
			var smsTextElmtLabel = document.getElementById("sms_text_Label");
			var smsTextElmtData = document.getElementById("sms_text_Data");
			
			if(smsTextElmtLabel)
				smsTextElmtLabel.innerHTML="SMS Text";
			
			var smsStr='';
			smsStr+='<div><textarea rows="5" cols="50" id="smsTextArea" onkeyup="limitText(\'smsTextArea\',\'maxcount\',200)" ></textarea></div> ';
			smsStr+='<div id="limitDiv">';
			smsStr+='<table style="width:100%;"><tr>';
			smsStr+='<td style="width:50%;"><div id="remainChars"><span id="maxcount">200 </span> <span>chars remaining..</span></div></td>';
			smsStr+='<td style="width:50%;"><div>Should not exceed 200 chars</div></td>';
			smsStr+='</tr></table>';
			smsStr+='</div>';	
				
			
			if(smsTextElmtData)
				smsTextElmtData.innerHTML=smsStr;


			var buttonDiv =  document.getElementById("button_div");
			var bstr='';
			bstr+='<input type="button" value="Send SMS" onclick="sendSMSCadreLevel()"/>';
			
			if(buttonDiv)
				buttonDiv.innerHTML=bstr;
			
		}
		function sendSMSCadreLevel(){
			var val = null;
			var region_type_AlertEl = document.getElementById("region_type_Alert");
			var sms_text_AlertEl = document.getElementById("sms_text_Alert");
			if(region_type_AlertEl)
				region_type_AlertEl.innerHTML = '';
			if(sms_text_AlertEl)
				sms_text_AlertEl.innerHTML = '';
			for( i = 0; i < document.smsForm.region_type_radio.length; i++ )
			{
				if( document.smsForm.region_type_radio[i].checked == true ){
					val = document.smsForm.region_type_radio[i].value;
					
				}
			}
			if(val == null)
			{
				region_type_AlertEl.innerHTML = 'Please Select Region Level!';
				return;
			}
			var textAreaElmt = document.getElementById("smsTextArea");

			textAreaElmtValue = textAreaElmt.value
			if(textAreaElmtValue == '')
			{
				sms_text_AlertEl.innerHTML = 'Please Type A Message!';
				return;
			}
			val=val.toUpperCase();

			//---
			var include_cadre ="NO";
			var include_user ="NO";

			var elements = document.getElementsByTagName('input'); 
			for(var i=0;i<elements.length;i++)
			{
				if(elements[i].type=="radio" && elements[i].name=="include_user_name" && elements[i].checked==true)
					include_user = elements[i].value;
				else if(elements[i].type=="radio" && elements[i].name=="include_cadre_name" && elements[i].checked==true)
					include_cadre = elements[i].value;
			}
			
			if(include_user=='YES'){
				if(document.getElementById('user_name')!=null && document.getElementById('user_name').value!='' )
					textAreaElmtValue = textAreaElmtValue + ' Thx ' + document.smsForm.user_name.value;
			}
			//---
			
			var jsObj={
						SMS_LEVEL_TYPE:'CADRE_LEVEL',
						SMS_LEVEL_VALUE: val,
						SMS_MESSAGE:textAreaElmtValue,
						SMS_INCLUDE_CADRE_NAME:include_cadre,
						task:"sendSMS"
					  };
			
			
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/sendCadreSMS.action?"+rparam;
			callAjax(jsObj,url);
		}
		function enableTextBox(){
			var textBoxElmt = document.getElementById("user_name");
			textBoxElmt.disabled =false;
		}
		function getNextRegions(id,val,regTask)
		{
			var selectElmt = document.getElementById(id);
			var selectValue = selectElmt.options[selectElmt.selectedIndex].value;
			
			if(selectValue=="0")
				return;

			var jsObj={
						value:selectValue,
						type:val,
						taskType:regTask,
						task:"fillSelectElements"
					  };
			var url = "<%=request.getContextPath()%>/regionsByCadreScope.action?REGION="+val+"&REGION_ID="+selectValue;
			callAjax(jsObj,url);

		}
		function fillSelectElement(results,jsObj)
		{			
			if(jsObj.type == "cadreDetails")
			{
				if(jsObj.reportLevel=="state")
				{
					var elmt = document.getElementById("cadreLevelDistrict");
				}
				else if(jsObj.reportLevel=="district")
				{
					var elmt=document.getElementById("cadreLevelMandal");
				}
				else if(jsObj.reportLevel=="constituency")
				{
					var elmt=document.getElementById("cadreLevelConstituency");
				}
				else if(jsObj.reportLevel=="mandal")
				{
					var elmt=document.getElementById("cadreLevelVillage");
				}
				else if(jsObj.reportLevel=="Constituencies")
				{
					var elmt=document.getElementById("mandalField");
				}
			}
			else if(jsObj.type == "cadreLevel")
				var elmt = document.getElementById("cadreLevelState");
			else if(jsObj.type == "STATE")
				var elmt = document.getElementById(jsObj.taskType+"_DistrictSelect");
			else if(jsObj.type == "DISTRICT	")
				var elmt = document.getElementById(jsObj.taskType+"_ConstituencySelect");
			else if(jsObj.type == "CONSTITUENCY")
				var elmt = document.getElementById(jsObj.taskType+"_MandalSelect");
			else if(jsObj.type == "MANDAL")
				var elmt = document.getElementById(jsObj.taskType+"_VillageSelect");
			else if(jsObj.type == "cadreLevel")
				var elmt=document.getElementById(jsObj.taskType+"_cadreLevelState");
			
			var len=elmt.length;			
			for(i=len-1;i>=0;i--)		
				elmt.remove(i);
									
			for (var l in results)
			{
				var y=document.createElement('option');
				y.value=results[l].id;
				y.text=results[l].name;
				
				try
				{
					elmt.add(y,null); // standards compliant
				}
				catch(ex)
				{
					elmt.add(y); // IE only
				}
			}
			
		}
		function displaySuccessMessage(results,jsObj)
		{
			var divElmt = document.getElementById("successDiv");
			var region_type_AlertEl = document.getElementById("region_type_Alert");
			var sms_text_AlertEl = document.getElementById("sms_text_Alert");
			var smsTextAreaEl = document.getElementById("smsTextArea"); 
			var maxcountEl = document.getElementById("maxcount");
			maxcountEl.innerHTML = '200';
			if(region_type_AlertEl)
				region_type_AlertEl.innerHTML = '';
			if(sms_text_AlertEl)
				sms_text_AlertEl.innerHTML = '';
			smsTextAreaEl.value='';
			var str='';
			if(results.status==0){
				str+=" SMS sent successfully to "+results.totalSmsSent+" cadres";
				if(results.remainingSmsCount!=0){
					str+="<br/>";
					str+=" You can send "+results.remainingSmsCount+"more SMS";
				}else{
					str+="<br/>";
					str+=" You cannot any more SMS ";
					smsRenewalMessage();
				}
				
			}else{
				smsRenewalMessage();
			}
			
			divElmt.innerHTML=str;
		}
		initializeIndexPage();
		<c:if test="${sessionScope.UserType == 'PartyAnalyst'}">
	showUserNameChangePanel(uname);
	</c:if>
var size='<s:property value="fileVOList.size"/>';
function showNewsPopUp(count,title,fileId,scope,description,pathOfFile,fileDate){

	
	$("#newsPopUpDiv").dialog({ stack: false,
								height: 'auto',
								width: 950,
								top:150,
								closeOnEscape: true,
								position:[30,30],
								show: "blind",
								hide: "explode",
								modal: true,
								maxWidth : 950,
								title:'<font color="Navy">'+title+'</font>',
								overlay: { opacity: 0.5, background: 'black'}
			});
	
	var newsInnerContetDivElmt = document.getElementById("newsInnerContetDiv");
	var str='';
	str+='<div id="content">';
	str+='<table width="100%">';
	str+='<tr>';
	str+='<td>'+scope+'</td>';
	str+='<td>'+fileDate+'</td>';
	str+='</tr><tr>';
	str+='<td width="10px">';
	if(count>1){
	str+='<a href="javascript:{getPreviousNews('+fileId+','+count+');}"><img class="newsImage" src="images/icons/jQuery/previous.png"></a>';
	}
	str+='</td>';
	str+='<td><div class="container"><img style="max-width:780px;max-length:800px;" src="'+pathOfFile+'">';
	str+='</div></td>';
	if(count!=size)
	str+='<td width="20px"><a href="javascript:{getNextNews('+fileId+','+count+');}"><img class="newsImage" src="images/icons/jQuery/next.png"></a>';
	str+='</td>';
	str+='</tr><tr>';
	str+='<td>'+description+'</td>';
	str+='</tr>';
	str+='</table>';
	str+='</div>';
	newsInnerContetDivElmt.innerHTML = str;
}	

//var fileList = '${fileVOList}';
//var hasNewsMonitoringentitlement ='${hasNewsMonitoring}' ; 
//alert(hasNewsMonitoringentitlement);

function getPreviousNews(fileId){

var newsInnerContetDivElmt = document.getElementById("newsInnerContetDiv");
var str='';
var ui_dialog_title_newsPopUpDivElmt = document.getElementById("ui-dialog-title-newsPopUpDiv");
	
	str+='<s:iterator value="fileVOList" status="stat">';
		var fileListId = '<s:property value="fileId"/>';
if(fileListId == fileId){
			
	
	count='<s:property value="#stat.index-1"/>';
	str+='<div id="content">';
	str+='<table width="100%">';
	str+='<tr>';
	alert(count);
	str+='<td><s:property value="fileVOList[#stat.index-1].scope"/></td>';
	str+='<td><s:property value="fileVOList[#stat.index-1].fileDate"/></td>';
	str+='</tr><tr>';
	if(count>0){
	str+='<td width="10px"><a href="javascript:{getNextNews(<s:property value="fileVOList[#stat.index-1].fileId"/>);}"><img class="newsImage" src="images/icons/jQuery/previous.png"></a>';
	str+='</td>';
	}
	str+='<td><div class="container"><img style="max-width:780px;max-length:800px;" src="<s:property value="fileVOList[#stat.index-1].pathOfFile"/>">';
	str+='</div></td>';
	if(count <=size){
	str+='<td width="20px"><a href="javascript:{getNextNews(<s:property value="fileVOList[#stat.index+1].fileId"/>);}"><img class="newsImage" src="images/icons/jQuery/next.png"></a>';
	str+='</td>';
	}
	str+='</tr><tr>';
	str+='<td>';
	
	str+='</tr>';
	str+='</table>';
	str+='</div>';
	ui_dialog_title_newsPopUpDivElmt.innerHTML =' <font color="Navy"><s:property value="fileVOList[#stat.index-1].title"/></font>';
	newsInnerContetDivElmt.innerHTML = str;
		return;	
	
	}
	
str+='</s:iterator>';



}
function getNextNews(fileId,count){


var newsInnerContetDivElmt = document.getElementById("newsInnerContetDiv");
var str='';
	var ui_dialog_title_newsPopUpDivElmt = document.getElementById("ui-dialog-title-newsPopUpDiv");
	str+='<s:iterator value="fileVOList" status="stat">';
		var fileListId = '<s:property value="fileId"/>';
 if( fileListId == fileId){
	count='<s:property value="#stat.index+1"/>';
	
	str+='<div id="content">';
	str+='<table width="100%">';
	str+='<tr>';
	str+='<td width="10px">Scope:</td><td><s:property value="fileVOList[#stat.index+1].scope"/></td>';
	str+='<td width="10px"><s:property value="fileVOList[#stat.index+1].fileDate"/></td>';
	str+='</tr><tr>';
	if(count>=1){
	str+='<td width="10px"><a href="javascript:{getNextNews(<s:property value="fileVOList[#stat.index-1].fileId"/>);}"><img class="newsImage" src="images/icons/jQuery/previous.png"></a>';
	str+='</td>';
	}
	str+='<td width="50px"><div class="container"><img style="max-width:780px;max-length:800px;" src="<s:property value="fileVOList[#stat.index+1].pathOfFile"/>">';
	str+='</div></td>';
	if(count >=size){
		
	str+='<td width="0px"><a href="javascript:{getNextNews(<s:property value="fileVOList[#stat.index+1].fileId"/>);}"><img class="newsImage" src="images/icons/jQuery/next.png"></a>';
	str+='</td>';
	}
	str+='</tr><tr>';
	str+='<td>';
	
	str+='</tr>';
	str+='</table>';
	str+='</div>';
	ui_dialog_title_newsPopUpDivElmt.innerHTML = '<font color="Navy"><s:property value="fileVOList[#stat.index+1].title"/></font>';
	newsInnerContetDivElmt.innerHTML = str;
		return;	
	
	}
	
str+='</s:iterator>';

	
}
function showAlertMsg(divElmt){

$("#"+divElmt).dialog({ stack: false,
							    height: 'auto',
								width: 500,
								position:'center',								
								modal: true,
								title:'<font color="#000000" style="font-size:11px">ALERT</font>',
								overlay: { opacity: 0.5, background: 'black'},
								
								});
		
		var accessDivElmt = document.getElementById(divElmt);
		var str='';

	str+='<img src="images/icons/smiley_sad.png" alt="sorry" style="display:inline;"/>&nbsp;&nbsp;';
	str+='<h3 style="font: bold 13px Trebuchet MS,Arial,Helvetica,sans-serif;color:#ff0000;display:inline;position:relative;top:-10px;">'; 
	str+='Sorry, You Don\'t have Access Privileges To View Detailed Report. Please Contact Us For Access Privileges.</h3>';
	str+='<span style="font: bold 13px Trebuchet MS,Arial,Helvetica,sans-serif;text-align:center;color:#000;display:block;">';
	str+='Phone No:+91 40 40124153 / +91 096766 96760<br />';
	str+='Email: <a href="mailTo:info@partyanalyst.com">info@itgrids.com</a></span>';
	
	accessDivElmt.innerHTML=str;
}

function hideShowNewsCount(){

var textmsg=$("#togglebutton").val();
var settext;

if(textmsg=="Show Summary")
settext="Hide Summary";
else
settext="Show Summary";

$("#togglebutton").val(settext);
  $("#showNewsCount").slideToggle("slow");
  
}
function showajaxImg(){
  document.getElementById("ajaxImg").style.display="block";
}
function showTabs(){
if(document.getElementById("dashBoardNews_Main").style.display == 'none')
	tabsDiv('impDatesDiv_main','impDateId');
}
</script>
</body>
</html>