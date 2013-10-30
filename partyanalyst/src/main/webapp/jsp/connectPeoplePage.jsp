<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>

<%@ page import="java.util.ResourceBundle;" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ConnectPeople</title>
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
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/tabview/tabview-min.js"></script>
<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>




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
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/tabview/assets/skins/sam/tabview.css">

<!-- YUI Dependency files (End) -->

<!-- JQuery files (Start) -->
<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery-ui-1.8.5.custom.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.core.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.blind.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.explode.min.js"></script>

<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />

<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery.ui.accordion.js"></script>

<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.core.css"/>
<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.theme.css"/>
<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.accordion.css"/>
<link rel="stylesheet" type="text/css" href="styles/connectPeople/ConnectStyle.css">

<!-- jquery for imagecrop --->
<script src="js/jQuery/image-crop/js/jquery.Jcrop.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/jQuery/image-crop/css/jquery.Jcrop.css" type="text/css" />
 

 <!-- -->



<!-- JQuery files (End) -->

<script type="text/javascript" src="http://www.google.com/jsapi"></script>

<script type="text/javascript" src="js/customPaginator/customPaginator.js"></script>
<script type="text/javascript" src="js/connectPeople/connectPeople.js"></script>
<script type="text/javascript" src="js/connectPeople/connectPeopleContent.js"></script>
<script type="text/javascript" src="js/homePage/homePage.js"> </script>
<link rel="stylesheet" type="text/css" href="styles/statePage/statePage.css">	
<link rel="stylesheet" type="text/css" href="styles/constituencyPage/constituencyPage.css">	
<link rel="stylesheet" type="text/css" href="styles/districtPage/districtPage.css">	
<link rel="stylesheet" type="text/css" href="styles/connectPeople/connectPeople.css">
<link rel="stylesheet" type="text/css" href="styles/FreeProfileStyle.css">
<link rel="stylesheet" type="text/css" href="styles/styles.css">


<script type="text/javascript">
google.load("visualization", "1", {packages:["piechart"]});
	google.load("elements", "1", {packages : ["newsshow"]});

var uname='${sessionScope.USER.userName}';	
var emailId = '${sessionScope.USER.email}';


	
</script>
<STYLE type="text/css" >
.yui-skin-sam .yui-navset .yui-nav .selected a, .yui-skin-sam .yui-navset .yui-nav .selected a em {
    background-image:url("images/icons/homePage_new/accordianHeader_selected.jpg");
   }
   #connectPeople_messages_center .yui-navset .yui-nav .selected a {
    background: url("../../js/yahoo/yui-js-2.8/build/assets/skins/sam/sprite.png") repeat-x scroll 0 0 #D8D8D8;
    color: #FFFFFF;
    font-weight: bold;

	#regionAccessTable th {font-size:12px;}
	.ctr-right ul li h5{padding:3px;margin:0px;}
	.ctr-right ul li p{padding:5px;margin:0px;border-top: 1px solid #d3d3d3;}

.paginatorElmtClass{
	padding-bottom: 10px;
    padding-left: 10px;
    padding-right: 10px;
    padding-top: 10px;
    text-align: right;
}

#pollQuestionDiv
{
	color:#0C67AC;
	font-weight:bold;
	margin-top:15px;
}

#pollOptionsDiv
{
	color:#73787E;
	font-weight:bold;
	margin-bottom:15px;
	margin-top:15px;
}

#viewPollResDiv
{
    color:#73787E;
	font-weight:bold;
	margin-bottom:15px;
	margin-top:15px;
}


.opinionpoll .question{padding:5px;border-bottom:1px dashed #ccc;font:13px Arial;}
.opinionpoll .answer{border-bottom:1px dashed #ccc;padding:0px ;margin-bottom:5px;margin-left:19px;}
.opinionpoll .votebtn{margin:0px auto;display:block;width:75px;}
.resultdisplay a{display:inline-block;text-decoration:none;}
.resultdisplay .previouslink{ float:left;}
.resultdisplay .nextlink{ float:right;}

.opinionpoll .answer .span2{margin-left:0px;}
.opinionpoll .answer span{margin-left:10px;}




</STYLE>
</head>

<body>



<div id="username_change_window" style="background-color: #C7CFD2;">
	<div id="username_change_window_inner"></div></div>
	

<div id="password_change_window" style="background-color: #C7CFD2;">
	  <div id="password_change_window_inner"></div></div>
	<div id="connectPeoplePopup_outer" class="yui-skin-sam">
		<div id="connectPeoplePopup" style="display:none;"><div id="allConnectedUsersDisplay_main"><img src="images/icons/barloader.gif"/></div></div>
	</div>
	<div id="jQueryPopup"> <div id="reasonsDataTable_outer" class="yui-skin-sam"> <div id="reasonsDataTable"></div> </div></div>
	<div id="connectPeopleMessagePopup_main" class="yui-skin-sam"><div id="connectPeopleMessagePopup"></div></div>
	<div id="uploadPic_window"><div id="uploadPic_window_inner"></div></div>
	<div id="connectPeoplePage_main" style="width:1000px;margin-left:auto;margin-right:auto;background:none;">
		<table width="100%">
			<tr>
				<td width="70%" valign="top">
					
					<div id="constituencyLeftContentOuter" style="width:670px;margin-left:auto;background:#ffffff;height:190px;clear:both;border:1px solid #d3d3d3;"> 						
						<div class="corner topLeft"></div>
						<div class="corner topRight"></div>
						<div class="corner bottomLeft"></div>
						<div class="corner bottomRight"></div>
							
							<div id="connectPeople_profile_center" class="yui-skin-sam">
							<!--<table>
									<tr>
									<td>
											<span style="font-size:12px;color:#9E7556;padding:2px;">Refer Friends From</span>
										</td>
										<td valign="top">
										   <a href="#" onclick="openBrowserForSocialNetworking('google')"><img src="images/google_16.png" alt="Gmail"  title="Gmail" border="0"></img></a>
										</td>
										<td valign="top">
										 <a href="#" onclick="openBrowserForSocialNetworking('yahoo')"><img src="images/yahoo_16.png" alt="YahooMail" title="YahooMail" border="0"></img></a>
										</td>
										</tr>
										</table>-->
								<table width="100%">
									<tr>
										<td width="20%" valign="top">
											<img id="userProfileImg" height="100" width="90" style="border:1px solid #ADADAD;" src="images/icons/indexPage/human.jpg">
										</td>
										<td width="55%" valign="top" align="left">
											<div id="profileUserName">${loginUserName}</div>
											<div id="connectPeople_editProfile_center" class="yui-skin-sam">
											</div>
										</td>
								<c:if test="${sessionScope.UserType != 'PartyAnalyst'}"> 
										<td width="25%" valign="top">
											<div class="connectPeople_profile_center_linksDiv" style="width:160px;">
												<table>
													<tr>
														<td><img width="10" width="10" src="images/icons/homePage_new/widgetHeaderIcon.jpeg"></td>
														<td><a href="javascript:{}" onclick="openAddReasonWindow('analyze')" style="color:#669900;">Add Political Reasons</a></td>
													</tr>
												</table>												
											</div>
											<div class="connectPeople_profile_center_linksDiv" style="width:160px;">
												<table>
													<tr>
														<td><img width="10" width="10" src="images/icons/homePage_new/widgetHeaderIcon.jpeg"></td>
														<td><a href="javascript:{}" onclick="openAddReasonWindow('viewResults')" style="color:#669900;">View Political Reasons</a></td>
													</tr>
												</table>												
											</div>
											<div class="connectPeople_profile_center_linksDiv" style="width:160px;">
												<table>
													<tr>
														<td><img width="10" width="10" src="images/icons/homePage_new/widgetHeaderIcon.jpeg"></td>
														<td><a href="javascript:{}" onclick="openAddNewProblemWindowForFreeUser('${sessionScope.USER.constituencyId}')" style="color:#669900;">Post Problem</a></td>
													</tr>
												</table>												
											</div>
										<div class="connectPeople_profile_center_linksDiv" style="width:160px;">
												<table>
													<tr>
														<td><img width="10" width="10" src="images/icons/homePage_new/widgetHeaderIcon.jpeg"></td>
														<td><a href="problemSearchAction.action" style="color:#669900;">View All Problems</a></td>
													</tr>
												</table>												
											</div>
										</td>
								</c:if>
									</tr>
									
								</table>
							</div>
							<div id="connectPeople_messages_center" class="yui-skin-sam" style="margin-top:20px;">
											 
							</div>

							<!--<div id="connectPeople_connect_center" class="yui-skin-sam">
											 
							</div>-->
					</div>

					
				</td>
				<td valign="top">
					<div id="constituencyRightContentOuter" class="rounded" style="width:287px;margin-right:auto;"> 						
						

 						
						<div class="corner topLeft"></div>
						<div class="corner topRight"></div>
						<div class="corner bottomLeft"></div>
						<div class="corner bottomRight"></div>
							<!-- Accordian Start-->
							<div id="connectedPeopleAccordian" style="width:101%;">
								<h3><a href="#" style="color:#000000;font-size:12px;">View Your Location Details</a></h3>
								<div style="padding:0px;height:250px;">
									<div id="connectPeople_quickAccess_center" class="yui-skin-sam" style="margin-top:10px;">									 
									</div>	
								</div>
								<h3><a href="#" style="color:#000000;font-size:12px;">People In Your Location</a></h3>
								<div style="padding:0px;height:250px;">
									<div id="connectPeople_count_center" class="yui-skin-sam" style="margin-top:10px;">											
									</div>	
								</div>
								<h3><a href="#" style="color:#000000;font-size:12px;">People You May Know</a></h3>
								<div style="padding:0px;height:250px;">
									<div id="connectPeople_PeopleMayKnow_center" class="yui-skin-sam">
											 
									</div>	
								</div>
							</div>
							<!-- Accordian End-->

						
										<!--<div class="ctr-right">
										<h5 style="background-color:#5e5e5e;color:#ffffff;padding: 2px;">About Me</h5>

								
									<ul>
									
									<li> 
										<h5>Contact Info</h5>
										<p style="border-top: 1px solid #d3d3d3;">${dataTransferVO.email}<br/>${dataTransferVO.mobileNo}</p>
									</li>
									
										<li> 
										 <h5>My Favourite Party(s)</h5>
							 <p style="border-top: 1px solid #d3d3d3;">Telugu Desam Party [TDP]</p>
							</li>
							<li> 
							 <h5 style="border-top: 1px solid #d3d3d3;">My Favourite Leader(s)</h5>
								<p> Chandra Babu Naidu <br/> Kishan Reddy </p>
								</li>
									</ul>
									</div>	
								
						








								<h5 style="background-color:#5e5e5e;color:#ffffff; padding: 2px;">Your Location Details</h5>
								<div style="padding:0px;">
									<div id="connectPeople_quickAccess_center" class="yui-skin-sam" style="margin-top:10px;height:auto;">									 
									</div>	
								</div>
								<h5 style="background-color:#5e5e5e;color:#ffffff; padding: 2px;">People In Your Location</h5>
								<div style="padding:0px;height:115px;">
							<div id="connectPeople_count_center" class="yui-skin-sam" style="margin-top:10px;">											
									</div>	
								</div>
								<h5 style="background-color:#5e5e5e;color:#ffffff; padding: 2px;">People You May Know</a></h5>
								<div style="padding:0px;">
									<div id="connectPeople_PeopleMayKnow_center" class="yui-skin-sam">
											 
									</div>	
								</div>
							</div>
							<!-- Accordian End-->
							
							<!-- CNC Search Start -->
							<!--<div id="adDataDiv_main" style="margin:10px 0px 10px 0px;">
								<div id="adDataMain_header">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
									  <tr>                                    
										<td width="8px"><img width="8" height="40" src="images/icons/homePage_new/white_header_image_left.jpeg"/></td>
										<td width="98%">
											<div class="productFeatureHeaderBackground_center">
												<span class="headerLabelSpan" style="position:relative;top:6px;">
													<table cellspacing="0" cellpadding="0">
														<tr>
															<td align="left">Quick Search </td>
															<td style="padding:2px 0px 0px 8px"><img  width="10" width="10" src="images/icons/homePage_new/widgetHeaderIcon.jpeg"></td>
														</tr>
													</table>
													 
												</span>
											</div>
										</td>
										<td width="8px"><img width="8" height="40" src="images/icons/homePage_new/white_header_image_right.jpeg"/></td>
									  </tr>
									</table>
								</div>
								<div id="adDataMain_body">
									<div class="adData_main">										
										<div class="adData_body">
											
										</div>																	
									</div>
								</div>
							</div>-->
							
							<!-- CNC Search End -->
							

							<!-- Opinion Poll Start-->
								<div id="pollsWidgetMain" style="margin:10px 0px 10px 0px;">
									<div id="pollsWidgetHeader">
										<!--<table width="100%" border="0" cellspacing="0" cellpadding="0">
										  <tr>
											<td width="1%"><img width="45" height="40" src="images/icons/homePage_new/poll_header_left.jpg"/></td>
											<td width="98%">
												<div class="electionTrendzHeaderBackground_center" style="width:217px;">
													<span class="headerLabelSpan headerLabelSpan1" style="color:#C66E17;top:13px;">
														Opinion Poll
													</span>
												</div>
											</td>
											<td width="1%"><img width="25" height="40" src="images/icons/homePage_new/poll_header_right.jpg"/></td>
										  </tr>
										</table>	-->
									 </div>
									<!--<div id="pollsWidgetBody" class="yui-skin-sam" style="height:auto;overflow:hidden;background:#ffffff;width:265px;">
									</div>-->
									<!--<div id="pollsWidgetBody" class="span4 well" style="background:#d4d4d4;">-->
									<!--<div id="pollsWidgetBody" class="span4 well" style="background:#FFFFFF;width:290px;margin-left:1px;">-->
                                      <div style="height:15px;padding-bottom:5px;">
                                     <div id="alreadyVotedDivId"></div>
									 </div>
									<div id="pollsWidgetBody"></div>
									<div id="pollsWidgetFooter">
										
									
								</div>
							<!-- Opinion Poll End-->
							<!--<div id="connectPeople_editProfile_center" class="yui-skin-sam">
							</div>-->
							
							
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2" valign="top">
					<div id="constituencyBottomContentOuter" class="rounded" style="width:940px;margin-left:18px;margin-top:14px;"> 						
						<div class="corner topLeft"></div>
						<div class="corner topRight"></div>
						<div class="corner bottomLeft"></div>
						<div class="corner bottomRight"></div>							
							<table width="100%" style="position:relative;">
								<tr>
									<td width="33%">
										<div class="productFeatureMain">							
											 <div class="productFeatureHeader">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
												  <tr>                                    
													<td width="8px"><img width="8" height="40" src="images/icons/homePage_new/white_header_image_left.jpeg"/></td>
													<td width="98%">
														<div class="productFeatureHeaderBackground_center1">
															<span class="headerLabelSpan">
																<table cellspacing="0" cellpadding="0">
																	<tr>
																		<td align="left">News About</td>
																		<td></td>
																	</tr>
																	<tr>
																		<td align="left">Leaders</td>
																		<td style="padding:2px 0px 0px 8px"><img width="10" width="10" src="images/icons/homePage_new/widgetHeaderIcon.jpeg"></td>
																	</tr>
																</table>
																 
															</span>
														</div>
													</td>
													<td width="8px"><img width="8" height="40" src="images/icons/homePage_new/white_header_image_right.jpeg"/></td>
												  </tr>
												</table>
											</div>							
											<div class="productFeatureBody" style="overflow:hidden;width:295px;height:250px;">
												<div id="leadersNews"></div>								
											</div>	
											<div class="productFeatureFooter">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
												  <tr>                                    
													<td width="12px"><img width="12" height="25" src="images/icons/homePage_new/white_footer_image_left.jpeg"/></td>
													<td width="98%">
														<div class="productFeatureFooterBackground_center">
															
														</div>
													</td>
													<td width="12px"><img width="12" height="25" src="images/icons/homePage_new/white_footer_image_right.jpeg"/></td>
												  </tr>
												</table>
											</div>
										</div>
									</td>
									<td width="33%">
										<div class="productFeatureMain">							
											 <div class="productFeatureHeader">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
												  <tr>                                    
													<td width="8px"><img width="8" height="40" src="images/icons/homePage_new/white_header_image_left.jpeg"/></td>
													<td width="98%">
														<div class="productFeatureHeaderBackground_center1">
															<span class="headerLabelSpan">
																<table cellspacing="0" cellpadding="0">
																	<tr>
																		<td align="left">News About</td>
																		<td></td>
																	</tr>
																	<tr>
																		<td align="left">Nation</td>
																		<td style="padding:2px 0px 0px 8px"><img width="10" width="10" src="images/icons/homePage_new/widgetHeaderIcon.jpeg"></td>
																	</tr>
																</table>
																 
															</span>
														</div>
													</td>
													<td width="8px"><img width="8" height="40" src="images/icons/homePage_new/white_header_image_right.jpeg"/></td>
												  </tr>
												</table>
											</div>
											<div class="productFeatureBody" style="overflow:hidden;width:295px;height:250px;">
												<div id="topStories"></div>								
											</div>	
											<div class="productFeatureFooter">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
												  <tr>                                    
													<td width="12px"><img width="12" height="25" src="images/icons/homePage_new/white_footer_image_left.jpeg"/></td>
													<td width="98%">
														<div class="productFeatureFooterBackground_center">
															
														</div>
													</td>
													<td width="12px"><img width="12" height="25" src="images/icons/homePage_new/white_footer_image_right.jpeg"/></td>
												  </tr>
												</table>
											</div>
										</div>
									</td>
									<td width="33%">
										<div class="productFeatureMain">							
											 <div class="productFeatureHeader">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
												  <tr>                                    
													<td width="8px"><img width="8" height="40" src="images/icons/homePage_new/white_header_image_left.jpeg"/></td>
													<td width="98%">
														<div class="productFeatureHeaderBackground_center1">
															<span class="headerLabelSpan">
																<table cellspacing="0" cellpadding="0">
																	<tr>
																		<td align="left">News About</td>
																		<td></td>
																	</tr>
																	<tr>
																		<td align="left">Parties</td>
																		<td style="padding:2px 0px 0px 8px"><img width="10" width="10" src="images/icons/homePage_new/widgetHeaderIcon.jpeg"></td>
																	</tr>
																</table>
																 
															</span>
														</div>
													</td>
													<td width="8px"><img width="8" height="40" src="images/icons/homePage_new/white_header_image_right.jpeg"/></td>
												  </tr>
												</table>
											</div>
											<div class="productFeatureBody" style="overflow:hidden;width:295px;height:250px;">
											<div id="partiesNews"></div>
											
												<!--<iframe frameborder="0" width="300" height="250" marginwidth="0" marginheight="0"
														src="http://www.google.com/uds/modules/elements/newsshow/iframe.html?q=INC%2C%20TDP%2C%20TRS%2C%20PRP%2C%20CPI%2C%20CPM%2C%20DMK%2CAIADMK&ned=in&rsz=small&hl=en&format=300x250">
												</iframe>-->
											</div>		
											<div class="productFeatureFooter">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
												  <tr>                                    
													<td width="12px"><img width="12" height="25" src="images/icons/homePage_new/white_footer_image_left.jpeg"/></td>
													<td width="98%">
														<div class="productFeatureFooterBackground_center">
															
														</div>
													</td>
													<td width="12px"><img width="12" height="25" src="images/icons/homePage_new/white_footer_image_right.jpeg"/></td>
												  </tr>
												</table>
											</div>
										</div>
									</td>
								</tr>
							</table>					
					</div>
				</td>
			</tr>
		</table>	
	
	</div>
<script type="text/javascript">
		

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
			height:130,
			show:'slide',
			modal:true
		});	
		$(".ui-dialog-titlebar").hide();
		$(".ui-widget-overlay").css("width","1000px");
		var elmt = document.getElementById("username_change_window_inner");

		var str = '';
			
		if(emailId == ''|| emailId== null)
		{
			str += '<div id="feedback_window_body">';
			str += '<div id="feedback_window_head">Enter Your Email</div>';
			str += '	<div id="feedBackForm_div">';
			str += '		<table id="feedbackTable" width="100%">';
			str += '		<tr>';
			str += '			<div id="ErrorMsgDivId"></div>';
		 	str += '		<th><font color="red">*</font>Enter Your EmailId </th>';
			str += '		<td><input type="text" id="emailField" size="25"/></td>';
			str += '		</tr>';
			str += '		</table>';
			str += '	<input type="checkbox" checked="true" id="changeUserNameCheck"/>&nbsp;Do you want to change Email as Username';
			str += '	</div>';
			str += '</div>';
			
			str += '<div id="feedback_window_footer" class="yui-skin-sam">';
			str += '	<table width="100%">';
			str += '	<tr>';
			str += '	<td width="65%" align="left"><div id="feedback_window_errorMsg"></div></td>';
			str += '	<td width="35%" align="right">';
			str += '		<input id="post" type="button" value="Yes"></input>';
			str += '		<input style="width:52px; text-align:center;" id="cancelButton" type="button" value="No"></input>';
			str += '	</td>';
			
			str += '	</tr>';
			str += '	</table>';	
			str += '</div>';
			elmt.innerHTML = str;

			$("#username_change_window").css("height","160px");
			
			var oPushButton1 = new YAHOO.widget.Button("post");  
			oPushButton1.on("click",function(){
				saveEmailAndSetAsUserName();
			});
			var oPushButton2 = new YAHOO.widget.Button("cancelButton");
	
			oPushButton2.on("click",function(){
				$("#username_change_window").dialog("destroy");
			});
	 
		}
		else
		{
			str += '<div id="feedback_window_head">Do You Want To Change Your UserName As Your Email</div>';
	        str += '<input type="hidden" value='+emailId+' id="emailField" size="25"/>'; 
			str += '<div id="feedback_window_footer" class="yui-skin-sam">';
			str += '	<table width="100%">';
			str += '	<tr>';
			str += '	<td width="65%" align="left"><div id="feedback_window_errorMsg"></div></td>';
			str += '	<td width="35%" align="right">';
			str += '		<input id="post" type="button" value="Yes"></input>';
			str += '		<input style="width:52px; text-align:center;" id="cancelButton" type="button" value="No"></input>';
			str += '	</td>';
			
			str += '	</tr>';
			str += '	</table>';	
			str += '</div>';
			elmt.innerHTML = str;

			$("#username_change_window").css("height","85px");

			var oPushButton1 = new YAHOO.widget.Button("post");  
			var oPushButton2 = new YAHOO.widget.Button("cancelButton");
	
			oPushButton1.on("click",function(){
				changeUserName();
			});
	
			oPushButton2.on("click",function(){
				$("#username_change_window").dialog("destroy");
			});
		}
	}
}

		

		function changeUserName(){
	
		var email = document.getElementById("emailField").value;
		var resultDIVEle = document.getElementById("feedback_window_errorMsg");
		resultDIVEle.innerHTML = "";
		var reg = /^([A-Za-z0-9_\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

	if(email.length == 0)
		resultDIVEle.innerHTML = "<font color='red'><b>Please Enter Email Id.</b></font>";
	
	else if(reg.test(email) == false)
		resultDIVEle.innerHTML = "<font color='red'><b>Plase provide correct Email Address.</b></font>";
    else if(emailId == ''|| emailId== null){
		document.getElementById("feedback_window_errorMsg").innerHTML = " ";
       var jsObj=
		{		
 				userName:email,
				task:"checkAnanymousUserNameAvailability"						
		};	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "checkAnanymousFreashUserNameAvailabilityAction.action?"+rparam;						
		callAjax(jsObj,url);

	}
	else if(reg.test(email) == true)
	{
        document.getElementById("feedback_window_errorMsg").innerHTML = " ";
 		var jsObj=
		{		
 				userName:email,
				task:"checkAnanymousUserNameAvailability"						
		};	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "checkAnanymousUserNameAvailabilityAction.action?"+rparam;						
		callAjax(jsObj,url);

	}
	
	
	}

	function saveEmailAndSetAsUserName()
{

	var email = document.getElementById('emailField').value;
	var errorDivEle = document.getElementById("ErrorMsgDivId");
	var setAsUserName = document.getElementById("changeUserNameCheck").checked;
	var eFlag = false;
	var str = '<font color="red">';
	var task = null;
	
	
	var emailExp = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
      if(email !='' && email!='your email'){
          
		  if(!email.match(emailExp)){

				document.getElementById("ErrorMsgDivId").innerHTML = '<font color="red">Please enter valid Email</font>';
				return;
		  }
	  }
	 else {
		document.getElementById("ErrorMsgDivId").innerHTML ='<font color="red">Please enter Email id</font>';  
		return;
	 }

	str += '</font>';

	errorDivEle.innerHTML = str;
	if(eFlag)
		return;

	if(setAsUserName)
		task = "saveUserEmailAndsetAsUserName";
	else
		task = "saveUserEmailAndSendPwd";

	var jsObj=
	{
			userName : uname,
			email : email,
			task : task

	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "saveUserEmailAction.action?"+rparam;						
	callAjax(jsObj,url);
}




		loginUserId = '${loginUserId}';
		loginUserName = '${loginUserName}';	
		loginUserProfilePic = '${loginUserProfilePic}';	

		userType = '${UserType}';		
		stateId = '${dataTransferVO.stateId}';
		stateName = '${dataTransferVO.stateName}';
		districtId = '${dataTransferVO.districtId}';
		districtName = '${dataTransferVO.districtName}';
		constituencyId = '${dataTransferVO.constituencyId}';
		constituencyName = '${dataTransferVO.constituencyName}';
		parliamentConstId =	'${dataTransferVO.parliamentConstId}';
		parliamentConstName = '${dataTransferVO.parliamentConstName}';

		districtConnectCount = '${dataTransferVO.districtUsersCount}';
		constituencyConnectCount = '${dataTransferVO.constituencyUsersCount}';

		connetLocationId = constituencyId;
		connectUserLoginId = loginUserId;

		connectedPeopleInfo.connectPeopleStatus.resultCode = '${dataTransferVO.resultStatusForConnectedPeople.resultCode}';	
		connectedPeopleInfo.connectPeopleStatus.exceptionMsg = '${dataTransferVO.resultStatusForConnectedPeople.exceptionMsg}';
		connectedPeopleInfo.connectPeopleStatus.isResultPartial = '${dataTransferVO.resultStatusForConnectedPeople.isResultPartial}';
		connectedPeopleInfo.connectPeopleStatus.exceptionClass = '${dataTransferVO.resultStatusForConnectedPeople.exceptionClass}';

		<c:forEach var="connect" varStatus="stat" items="${dataTransferVO.connectedPeople}">
			var obj =	{
							id:'${connect.id}',
							candidateName:'${connect.candidateName}',
							constituencyName:'${connect.constituencyName}',
							district:'${connect.district}',
							state:'${connect.state}',
							status:'${connect.status}'
						};
			connectedPeopleInfo.connectPeople.push(obj);
		</c:forEach>

		connectedPeopleInfo.connectPeopleStatus.resultCode = '${dataTransferVO.resultStatusForPeopleYouMayKnow.resultCode}';	
		connectedPeopleInfo.connectPeopleStatus.exceptionMsg = '${dataTransferVO.resultStatusForPeopleYouMayKnow.exceptionMsg}';
		connectedPeopleInfo.connectPeopleStatus.isResultPartial = '${dataTransferVO.resultStatusForPeopleYouMayKnow.isResultPartial}';
		connectedPeopleInfo.connectPeopleStatus.exceptionClass = '${dataTransferVO.resultStatusForPeopleYouMayKnow.exceptionClass}';

		<c:forEach var="unKnownPeople" varStatus="stat" items="${dataTransferVO.peopleYouMayKnow}">
			var obj =	{
							id:'${unKnownPeople.id}',
							candidateName:'${unKnownPeople.candidateName}',
							constituencyName:'${unKnownPeople.constituencyName}',
							district:'${unKnownPeople.district}',
							state:'${unKnownPeople.state}',
							status:'${unKnownPeople.status}'
						};
			peopleYouMayKnowInfo.peopleYouMayKnow.push(obj);
		</c:forEach>
		
		
		commentsInfo.connectPeopleStatus.resultCode = '${dataTransferVO.resultStatusForConnectedPeople.resultCode}';	
		commentsInfo.connectPeopleStatus.exceptionMsg = '${dataTransferVO.resultStatusForConnectedPeople.exceptionMsg}';
		commentsInfo.connectPeopleStatus.isResultPartial = '${dataTransferVO.resultStatusForConnectedPeople.isResultPartial}';
		commentsInfo.connectPeopleStatus.exceptionClass = '${dataTransferVO.resultStatusForConnectedPeople.exceptionClass}';
		commentsInfo.totalMsgCount = '${dataTransferVO.totalMsgCount}';
		commentsInfo.unreadMsgCount = '${dataTransferVO.unreadMsgCount}';
		<c:forEach var="connect" varStatus="stat" items="${dataTransferVO.comments}">
			var obj =	{
							id:'${connect.id}',
							candidateName:'${connect.candidateName}',
							constituencyName:'${connect.constituencyName}',
							district:'${connect.district}',
							state:'${connect.state}',
							status:'${connect.status}',
							postedDate:'${connect.postedDate}',
							data : '${connect.data}',
							msg: '${connect.message}',
							customMsgId:'${connect.costumMessageId}',
							recepientId:'${connect.recepientId}'
						};
			commentsInfo.comments.push(obj);
		</c:forEach>
		
		<c:forEach var="status" varStatus="stat" items="${messageTypes.messageTypes}">
			var obj =	{
							id:'${status.id}',
							name:'${status.name}'
						};
			connectStatus.push(obj);
		</c:forEach>
		
		<c:forEach var="constituency" varStatus="stat" items="${constituenciesStatusVO.constituencyWinnerInfoVO}">
			var obj =	{
							id:'${constituency.constituencyId}',
							name:'${constituency.constituencyName}'
						};
			constituencies.push(obj);
		</c:forEach>
			
	<c:if test="${sessionScope.hasFreeUserRole == true}">
		showUserNameChangePanel(uname);
		</c:if>
	
		initializeConnectPeople();
	</script>
</body>
</html>