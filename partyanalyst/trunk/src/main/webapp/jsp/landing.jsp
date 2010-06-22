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
	
</head>

<body>
	<div id="loginPopupDivMain" class="yui-skin-sam"><div id="loginPopupDiv"></div></div>
	<div id="mainContainer">
			<div id="navMenu_main" class="pageContentDivs">
				<div id="navMenu_links">
					<ul id="topLinksNav">
						<li><a href="javascript:{}" class="navLinksAnc">Features</a></li>
						<li><a href="javascript:{}" class="navLinksAnc">Pricing</a></li>						
						<li><a href="javascript:{}" class="navLinksAnc">Resources</a></li>
						<li><a href="javascript:{}" class="navLinksAnc">Support</a></li>
						<li><a href="javascript:{}" class="navLinksAnc">Who We Are</a></li>
						<li><a href="javascript:{}" class="navLinksAnc">Our Blog</a></li>
						<li>|</li>
						<li><a href="javascript:{}" class="navLinksAnc"> Login </a></li>
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
						
						<td width="250px"><a href="javascript:{}"><img style="border:none;margin-top:2px" src="images/icons/homePage/features.png"/></a></td>
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
									Now you have all the information you have at your fingertips to help understand, to improve party success and serve your people better.
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
									<div id="prices_main_head" class="newsDataHead">Oppn alleges scam in sugar imports,Pawar refuses charge</div>
									<div id="prices_main_body" style="color:#73787E">
										An relenting opposition today kept the pressure on the government in the Rajya Sabha on the spiraling prices alleging 
										scam in sugar imports, a charge rejected by food and agricultural minister Sharad Pawar.
									</div>
									<div id="prices_main_footer" class="newsDataFooter"> >> More	</div>
									</div>

									<div class="newsData_main">
									<div id="fuel_main_head" class="newsDataHead">Govt to talk to allies on fuel price hke:pranab </div>
									<div id="fuel_main_body" style="color:#73787E">
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
									<li><div>In pics : Vineet Jain's Holi party 2010</div></li>
									<li><div>Filmfare Awards : 3 Idiots shines.</div></li>
									<li><div>Pranab meets Congress MP's explains fuel price hike</div></li>
									<li><div>Chandrayaan finds ice on moon</div></li>
								</ul>
							</div>
						</td>
						<td width="25%" style="vertical-align: top;"> 
							<div id="popularResourcesDiv_main">
								<div id="popularResourcesDiv_head" class="news_resources_head"> Popular Resources : </div>
								<div id="popularResourcesDiv_list">
									<ul id="popularResources_list">
										<li>
											<div style="margin-bottom:5px;">Know how political analysis plays a huge role in performance</div>
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
						<td align="left"> © Copyright 2010. All rights reserved | IT GRIDS (India) Pvt. Ltd.</td>
						<td align="right"> About Us | Contact Us | API | Terms Of Use | Privacy Policy </td>
					</tr>
				</table>
			</div>
	</div>			
</body>
</html>