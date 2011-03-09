<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
	<title>PartyAnalyst - Know - Analyse - Act</title>
	<META NAME="keywords" CONTENT="Party Analyst,Party,Analyst,Political Analysis,Indian Politics,Lok Sabha,Rajya Sabha,Andhra Elections,About AndhraPradesh,AP POlitics,MlA's,MP's,MPTC's,ZPTC's,Booth Info,Cross Voting,Booth Level Report," />
	<META NAME="description" CONTENT="Party Analyst is a new way to find information about Indian Politics & Serves as a platform between the public and politicians." />
	<META HTTP-EQUIV="Content-Language" CONTENT="EN">
	<META NAME="robots" CONTENT="FOLLOW,INDEX,NOARCHIVE">
	<META name="distribution" content="Global">
	<META name="rating" content="General">
	<META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE">
	<META NAME="GOOGLEBOT" CONTENT="NOARCHIVE">
	<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
	
	<!--<link href="styles/pa.css" rel="stylesheet" type="text/css" />-->
	
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
	<link href="styles/landingPage/landingPage.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/landingPage/landingPage.js" ></script>
	<script type="text/javascript" src="js/googleAnalytics/googleAnalytics.js" ></script>
	<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/faviIcon.jpg">
</head>

<body>
	<div id="loginPopupDivMain" class="yui-skin-sam"><div id="loginPopupDiv"></div></div>
	<div id="mainContainer">
			<div id="navMenu_main" class="pageContentDivs">
				<div id="navMenu_links">
					<ul id="topLinksNav">
						<li><a href="homePage.action" class="navLinksAnc">Home</a></li>
						<li><a href="viewFeaturesAction.action" class="navLinksAnc">Features</a></li>
						<li><a href="javascript:{}" class="navLinksAnc">Pricing</a></li>						
						<li><a href="javascript:{}" class="navLinksAnc">Resources</a></li>
						<li><a href="javascript:{}" class="navLinksAnc">Support</a></li>
						<li><a href="javascript:{}" class="navLinksAnc">Who We Are</a></li>
						<li><a href="http://partyanalyst.wordpress.com" target="_blank" class="navLinksAnc">Our Blog</a></li>
						<li>|</li>
						<li><a href="javascript:{}" onclick="buildLoginPopup()" class="navLinksAnc">
						Login </a></li>
					</ul>
				</div>
				<div id="pa_logo" class="pa_logo_class">
					<img src="images/icons/homePage/pa_logo.jpg"></img>
				</div>
			</div>			
			<div id="analyzeButtonBar_main" class="pageContentDivs">
				<table width="100%" id="analyzeButtonTable">
					<tr>
						<td width="250px"><a href="javascript:{}" onclick="buildLoginPopup()"><img style="border:none;margin-top:2px" src="images/icons/homePage/analyze.png"/></a></td>
						
						<td width="250px"><a href="viewFeaturesAction.action"><img style="border:none;margin-top:2px" src="images/icons/homePage/features.png"/></a></td>
						<td><div></div></td>
					</tr>
				</table>			
			</div>

			<div class="contentSeperatorDiv"></div>
			<div id="featuresList_main" class="pageContentDivs">
				<table width="100%">
					<tr>
						<td width="40%" style="vertical-align: top;">
							<div id="knowAnalyzeActDiv_image">
								<img src="images/icons/homePage/know_analyze_act.png"/>
							</div>
							<div id="knowAnalyzeActDiv_description" class="descriptionDiv" style="margin-top: 25px;">
								<p>
									It is to serve as a platform between the public and their leaders that we bought out our first-of-a-kind service.
									We offer you a range of services to help you get a real understanding of your constituency and the issues it is facing.
									Now you have all the information you have at your fingertips to help understand, to omprove party success and serve your people better.
								</p>
							</div>
							<div id="knowAnalyzeActDiv_footer" style="margin-top: 25px;">
								<p>To know how our three-step method helps you, <font style="font-weight:bold;font-size:12px">Know.Analyse.Act </font></p>
							</div>
						</td>
						<td width="60%" style="vertical-align: top;">
							<table id="featuresList" width="100%">
								<tr>
									<td width="50%" style="vertical-align: top;">
										<div id="groupCommunication_main"  style="height: 120px;">
											<div id="groupCommunication_head" class="homePageHeaders">
												<span style="float:left;"><img src="images/icons/homePage/group_communication.png"/></span>
												<span>Create & Send Group Communication</span>
											</div>
											<br/>
											<div id="groupCommunication_body" class="descriptionDiv">
												<font style="color:#4b74c6">Improve Your Communication </font>between members and workers by constantly keeping in touch with them.
											</div>
										</div>
									</td>
									<td width="50%" style="vertical-align: top;">
										<div id="cadreManagementdiv_main"  style="height: 120px;">
											<div id="cadreManagementdiv_head" class="homePageHeaders">
												<span style="float:left;"><img src="images/icons/homePage/cadre_management.png"/></span>
												<span>Manage Lists & Cadre Communication</span>
											</div>
											<br/>
											<div id="cadreManagementdiv_body" class="descriptionDiv">
												<font style="color:#4b74c6">We help manage party </font>workers and the people involved. Easily create target segments using cadre management.
											</div>
										</div>
									</td>
								</tr>
								<tr>
									<td width="50%" style="vertical-align: top;">
										<div id="powerfulAnalytics_main"  style="height: 120px;">
											<div id="powerfulAnalytics_head" class="homePageHeaders">
												<span style="float:left;"><img src="images/icons/homePage/analytics.png"/></span>
												<span>Powerful Analytics</span>
											</div>
											<br/>
											<div id="powerfulAnalytics_body" class="descriptionDiv" style="padding-top:17px;">
												<font style="color:#4b74c6">Actionable reports that go beyond open and clicks.</font>Tracks your performance over a period of time.
											</div>
										</div>
									</td>
									<td width="50%" style="vertical-align: top;">
										<div id="detailsTools_main"  style="height: 120px;">
											<div id="detailsTools_head" class="homePageHeaders">
												<span style="float:left;"><img src="images/icons/homePage/detail_tools.png"/></span>
												<span>Extensive & Detailed Tools</span>
											</div>
											<br/>
											<div id="detailsTools_body" class="descriptionDiv">
												<font style="color:#4b74c6">Do analysis, cadre management, track information ,</font> or any other feature.It's easy as a click away.
											</div>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>
			
			<div class="contentSeperatorDiv"></div>
			<div id="resources_main" class="pageContentDivs">
				<table>
					<tr>
						<td width="50%" style="vertical-align: top;">
							<div id="currentNewsDiv">
								<div id="currentNewsDiv_head" class="news_resources_head">
									Current News :
								</div>
								<div id="currentNewsDiv_body">
									<div class="newsData_main">
									<div id="prices_main_head" class="newsDataHead">Telangana Bye Elections - 2010</div>
									<div id="prices_main_body" style="color:#73787E;">
										Crucial elections for major parties like INC, TDP and TRS in Andhra Pradesh.
										What is each parties strengths in these constituencies? What are the factors influence these elections like non participating parties (PRP, BJP, Loksatta etc) ...?
									</div>
									<div id="prices_main_footer" class="newsDataFooter"> >> More	</div>
									</div>

									<div class="newsData_main">
									<div id="fuel_main_head" class="newsDataHead">Govt to talk to allies on fuel price hike:pranab</div>
									<div id="fuel_main_body" style="color:#73787E;">
										Finance minister Pranab Mukherjee on Wednesday briefed the Congress MP's in the backdrop of demands for a rollback in the fuel prices
										and explained why reduction in the excise duty on petroleum products was difficult.
									</div>
									<div id="fuel_main_footer" class="newsDataFooter"> >> More</div>
									</div>
								</div>								
							</div>
						</td>
						<td width="25%" style="vertical-align: top;">							
							<div id="otherNews_main">
								<div id="otherNewsDiv_head" class="news_resources_head">
									Other News :
								</div>
								<ul id="other_news_list">
									<li><div>Political Leaders arrest warns political parties, leaders about election code.</div></li>
									<li><div>PRP making arrangements for Local Body Elections.</div></li>
									<li><div>Parties alligation on fuel price hike influence the Govt.</div></li>
									<li><div>Sport Stars in India</div></li>
								</ul>
							</div>
						</td>
						<td width="25%" style="vertical-align: top;"> 
							<div id="popularResourcesDiv_main">
								<div id="popularResourcesDiv_head" class="news_resources_head"> Popular Resources : </div>
								<div id="popularResourcesDiv_list">
									<ul id="popularResources_list">
										<li>
											<div style="margin-bottom:5px;">Konw how political analysis plays a huge role in performance</div>
											<div style="color:#73787E;font-weight:normal;">Telling you how statistics will give you the power to fine tune, implement, rollback and improve actions of organization.</div>
										</li>
										<li><div>Group motivation practices</div></li>
										<li><div>Best public speaks of all times.A comprehensive survey.</div></li>
										<li><div>Public speaking tips and tricks.</div></li>
									</ul>
								</div>
							</div>
						</td>
					</tr>
				</table>
			</div>		
			<div id="homePagefooter" class="pageContentDivs">
				<table width="100%" id="copyrightLinksTable">
					<tr>
						<td align="left"> Â© Copyright 2010. All rights reserved | IT GRIDS (India) Pvt. Ltd.</td>
						<td align="right"> About Us | Contact Us | API | Terms Of Use | Privacy Policy </td>
					</tr>
				</table>
			</div>
	</div>			
</body>
</html>