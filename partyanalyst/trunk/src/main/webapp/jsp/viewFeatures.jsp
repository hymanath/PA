<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Party Analyst - Features</title>


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

<!-- JQuery files (Start) -->
<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery-ui-1.8.5.custom.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.core.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.blind.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.explode.min.js"></script>

<script type="text/javascript" src="js/jQuery/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
<script type="text/javascript" src="js/jQuery/floating-1.5.js"></script>
<script type="text/javascript" src="js/homePage/jquery.sudoSlider.min.js"></script>


<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />

<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery.ui.accordion.js"></script>

<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.core.css"/>
<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.theme.css"/>
<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.accordion.css"/>
<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>

<link  rel="stylesheet" type="text/css" href="styles/homePage/jquerySlider.css"/>

<!-- JQuery files (End) -->

<link  rel="stylesheet" type="text/css" href="styles/landingPage/landingPage.css"/>
<link href="styles/styles.css" rel="stylesheet" type="text/css" />

<link href="styles/viewFeatures/viewFeatures.css" rel="stylesheet" type="text/css" />
<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/faviIcon.jpg">
<script type="text/javascript" src="js/landingPage/landingPage.js" ></script>
<script type="text/javascript" src="js/homePage/homePage.js" ></script>
<script type="text/javascript">

  var loginStatus = '${loginStatus}';
</script>
</head>
<body>
   <div id="jQueryPopup"><div id="jQueryPopup_content"></div></div>
   <div id="loginPopupDivMain" class="yui-skin-sam"><div id="loginPopupDiv"></div></div>
   <div id="contactWindowDiv"><div id="contactWindowDiv_window_inner"></div></div>
   <div id="supportWindowDiv"><div id="supportWindowDiv_window_inner"></div></div>
   <div id="feedback_window"><div id="feedback_window_inner"></div></div>
   <div id="viewFeatures_main">
		<div id="navMenu_main" class="pageContentDivs">
			<div id="navMenu_links">
				<ul id="topLinksNav">					
					<li><a href="homePage.action" class="navLinksAnc">Home</a></li>
					<li><a href="javascript:{}" onclick="showDetailsPopup('Pricing')" class="navLinksAnc">Pricing</a></li>						
					<li><a href="javascript:{}" class="navLinksAnc">Resources</a></li>
					<li><a href="javascript:{}" class="navLinksAnc" id="supportLink" onclick="supportLinkInHomePage()">Support</a></li>
					<li><a href="footerLinksAction.action?linkFrom=aboutUs#whoWeAre" class="navLinksAnc">Who We Are</a></li>
					<li><a href="javascript:{}" class="navLinksAnc">Our Blog</a></li>
					<li>|</li>
					<li><a href="loginInputAction.action" class="navLinksAnc">
					Login </a></li>
				</ul>
			</div>
			<div id="pa_logo" class="pa_logo_class">
				<img src="images/icons/homePage/pa_logo.jpg"></img>
			</div>
		</div>
		<table width="100%">
			<tr>
				<td colspan="2" align="center">
					<table>
						<td width="250"><img height="70px" width="200" src="images/icons/viewFeatures/know_icon.jpg"></img></td>
						<td width="250"><img height="70px" width="200" src="images/icons/viewFeatures/analyse_icon.jpg"></img></td>
						<td><img height="70px" width="200" src="images/icons/viewFeatures/act_icon.jpg"></img></td>
					</table>
				</td>
			</tr>
			<!--<tr>
				<td width="75%" valign="top">
					<div id="featuresFlashDiv_main"></div>
				</td>
				<td width="25%" valign="top">
					<div id="know_analyse_act_icons_div">
						<table>
							<tr><td><img src="images/icons/viewFeatures/know_icon.jpg"></img></td></tr>
							<tr><td><img src="images/icons/viewFeatures/analyse_icon.jpg"></img></td></tr>
							<tr><td><img src="images/icons/viewFeatures/act_icon.jpg"></img></td></tr>
						</table>
					</div>
				</td>
			</tr>-->			
			<tr>
				<td width="75%" valign="top">

				<div id="viewFeaturesWidgets_main">
				<div id="viewFeatures_head">PartyAnalyst Product OverView</div>
				<div><center><table>
					    <tr><td>
					<iframe width="200" height="200" src="http://www.youtube.com/embed/3k9vFj0Ca54" frameborder="0" allowfullscreen></iframe>
					
					</td><td></td><td></td><td></td><td>
					<iframe width="200" height="200" src="http://www.youtube.com/embed/mMTRWXNVXCw" frameborder="0" allowfullscreen></iframe>	
					</td><td></td><td></td><td></td>
					<td><iframe width="200" height="200" src="http://www.youtube.com/embed/Dgz_evKCbBg" frameborder="0" allowfullscreen></iframe></td>
					</tr></table></center>
					</div>					

					<div id="viewFeatures_head" style="padding-top:30px"> View Features </div>
						<div id="viewFeatures_body">
							<table width="100%">
								<tr>
									<td width="33%">
										<div id="politicalAnalysis">
											<div id="politicalAnalysis_head">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
												  <tr>                                    
													<td width="1%"><img src="images/icons/viewFeatures/feature1.jpg"/></td>
													<td width="98%">
														<div class="viewFeatureHeaderBackground_center">
															<span class="featureHeaderLabelSpan" style="top:13px;left:20px;">
																Party Analysis  
															</span>
														</div>
													</td>
													<td width="1%"><img src="images/icons/viewFeatures/viewFeature_header_right.jpg"/></td>
												  </tr>
												</table>
											</div>
											<div id="politicalAnalysis_body" class="viewFeature_body_content">
												<!--
													******************************											
													Content for party analysis 
													******************************
												-->

												Different Tools like Party Performance Report, Election Comparison, Party Results, Party Influence  for parties to analyse their performance from state level to mandal level.
											</div>
											<div id="politicalAnalysis_footer" class="viewFeature_footer_content">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
												  <tr>                                    
													<td width="1%"><img src="images/icons/viewFeatures/viewFeature_footer_left.jpg"/></td>
													<td width="98%">
														<div class="viewFeatureFooterBackground_center">
															<a href="javascript:{}"><img style="border:none;" src="images/icons/viewFeatures/readMore.jpg"/></a>
														</div>
													</td>
													<td width="1%"><img src="images/icons/viewFeatures/viewFeature_footer_right.jpg"/></td>
												  </tr>
												</table>
											</div>
										</div>
									</td>
									<td width="33%">
										<div id="politicianAnalysis_main">
											<div id="politicianAnalysis_head">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
												  <tr>                                    
													<td width="1%"><img src="images/icons/viewFeatures/feature6.jpg"/></td>
													<td width="98%">
														<div class="viewFeatureHeaderBackground_center">
															<span class="featureHeaderLabelSpan" style="top:13px;left:20px;">
																Politician Analysis  
															</span>
														</div>
													</td>
													<td width="1%"><img src="images/icons/viewFeatures/viewFeature_header_right.jpg"/></td>
												  </tr>
												</table>
											</div>
											<div id="politician_body" class="viewFeature_body_content">
												<!--
													******************************											
													Content for politician analysis 
													******************************
												-->
													Different Tools like Mandal Voting Report, Cross Voting, Constituency Booth Results for politician to analyse their performance from state level to hamlet level.
											</div>
											<div id="politician_footer" class="viewFeature_footer_content">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
												  <tr>                                    
													<td width="1%"><img src="images/icons/viewFeatures/viewFeature_footer_left.jpg"/></td>
													<td width="98%">
														<div class="viewFeatureFooterBackground_center">
															<a href="javascript:{}"><img style="border:none;" src="images/icons/viewFeatures/readMore.jpg"/></a>
														</div>
													</td>
													<td width="1%"><img src="images/icons/viewFeatures/viewFeature_footer_right.jpg"/></td>
												  </tr>
												</table>
											</div>
										</div>
									</td>
									<td width="33%">
										<div id="constituencyMgmt">
											<div id="constituency_head">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
												  <tr>                                    
													<td width="1%"><img src="images/icons/viewFeatures/feature3.jpg"/></td>
													<td width="98%">
														<div class="viewFeatureHeaderBackground_center">
															<span class="featureHeaderLabelSpan" style="top:13px;left:20px;">
																Constituency Management  
															</span>
														</div>
													</td>
													<td width="1%"><img src="images/icons/viewFeatures/viewFeature_header_right.jpg"/></td>
												  </tr>
												</table>
											</div>
											<div id="constituency_body" class="viewFeature_body_content">
												Consituency management feature allows user to keep track their constituency problems, know political changes and impact in their constituency, view influencing people, mandal level leaders and voters details by hamlet level. 
											</div>
											<div id="constituency_footer" class="viewFeature_footer_content">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
												  <tr>                                    
													<td width="1%"><img src="images/icons/viewFeatures/viewFeature_footer_left.jpg"/></td>
													<td width="98%">
														<div class="viewFeatureFooterBackground_center">
															<a href="javascript:{}"><img style="border:none;" src="images/icons/viewFeatures/readMore.jpg"/></a>
														</div>
													</td>
													<td width="1%"><img src="images/icons/viewFeatures/viewFeature_footer_right.jpg"/></td>
												  </tr>
												</table>
											</div>
										</div>
									</td>
								</tr>
								<tr>
									<td width="33%">
										<div id="cadreMgmt">
											<div id="cadreMgmt_head">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
												  <tr>                                    
													<td width="1%"><img src="images/icons/viewFeatures/feature2.jpg"/></td>
													<td width="98%">
														<div class="viewFeatureHeaderBackground_center">
															<span class="featureHeaderLabelSpan" style="top:13px;left:20px;">
																Cadre Management  
															</span>
														</div>
													</td>
													<td width="1%"><img src="images/icons/viewFeatures/viewFeature_header_right.jpg"/></td>
												  </tr>
												</table>
											</div>
											<div id="cadreMgmt_body" class="viewFeature_body_content">
												Cadre management feature is mainly used to manage their cadre efficiently.The user can add & manage cadre, communicate with cadre, plan and organize events, maintain associate groups.
											</div>
											<div id="cadreMgmt_footer" class="viewFeature_footer_content">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
												  <tr>                                    
													<td width="1%"><img src="images/icons/viewFeatures/viewFeature_footer_left.jpg"/></td>
													<td width="98%">
														<div class="viewFeatureFooterBackground_center">
															<a href="javascript:{}"><img style="border:none;" src="images/icons/viewFeatures/readMore.jpg"/></a>
														</div>
													</td>
													<td width="1%"><img src="images/icons/viewFeatures/viewFeature_footer_right.jpg"/></td>
												  </tr>
												</table>
											</div>
										</div>
									</td>
									<td width="33%">
										<div id="problemMgmt">
											<div id="problemMgmt_head">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
												  <tr>                                    
													<td width="1%"><img src="images/icons/viewFeatures/feature5.jpg"/></td>
													<td width="98%">
														<div class="viewFeatureHeaderBackground_center">
															<span class="featureHeaderLabelSpan" style="top:13px;left:20px;">
																Problem Management  
															</span>
														</div>
													</td>
													<td width="1%"><img src="images/icons/viewFeatures/viewFeature_header_right.jpg"/></td>
												  </tr>
												</table>
											</div>
											<div id="problemMgmt_body" class="viewFeature_body_content">
												Problem management feature is mainly used to keep track of problems in different levels.The user can track problems by location, Status,deparment.
											</div>
											<div id="problemMgmt_footer" class="viewFeature_footer_content">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
												  <tr>                                    
													<td width="1%"><img src="images/icons/viewFeatures/viewFeature_footer_left.jpg"/></td>
													<td width="98%">
														<div class="viewFeatureFooterBackground_center">
															<a href="javascript:{}"><img style="border:none;" src="images/icons/viewFeatures/readMore.jpg"/></a>
														</div>
													</td>
													<td width="1%"><img src="images/icons/viewFeatures/viewFeature_footer_right.jpg"/></td>
												  </tr>
												</table>
											</div>
										</div>
									</td>
									<td width="33%">
										<div id="userGroups">
											<div id="userGroups_head">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
												  <tr>                                    
													<td width="1%"><img src="images/icons/viewFeatures/feature4.jpg"/></td>
													<td width="98%">
														<div class="viewFeatureHeaderBackground_center">
															<span class="featureHeaderLabelSpan" style="top:13px;left:20px;">
																User Groups  
															</span>
														</div>
													</td>
													<td width="1%"><img src="images/icons/viewFeatures/viewFeature_header_right.jpg"/></td>
												  </tr>
												</table>
											</div>
											<div id="userGroups_body" class="viewFeature_body_content">
												User Groups is a consistent interaction for creating, managing groups and it helps the users for maintaining relationship with people
											</div>
											<div id="userGroups_footer" class="viewFeature_footer_content">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
												  <tr>                                    
													<td width="1%"><img src="images/icons/viewFeatures/viewFeature_footer_left.jpg"/></td>
													<td width="98%">
														<div class="viewFeatureFooterBackground_center">
															<a href="javascript:{}"><img style="border:none;" src="images/icons/viewFeatures/readMore.jpg"/></a>
														</div>
													</td>
													<td width="1%"><img src="images/icons/viewFeatures/viewFeature_footer_right.jpg"/></td>
												  </tr>
												</table>
											</div>
										</div>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</td>
				
				<td width="25%" valign="top" style="padding-top:37px;">
					<div id="topFeaturesListDiv_main">
						<div id="topFeaturesListDiv_head">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
							  <tr>                                    
								<td width="1%"><img src="images/icons/viewFeatures/topFeatures_header_left.jpg"/></td>
								<td width="98%">
									<div class="featureHeaderBackground_center">
										<span class="featureHeaderLabelSpan">
											Top Features  
										</span>
									</div>
								</td>
								<td width="1%"><img src="images/icons/viewFeatures/topFeatures_header_right.jpg"/></td>
							  </tr>
							</table>
						</div>
						<div id="topFeaturesListDiv_body">
							<div>
							<ul id="topFeaturesList">
								<li><a href="javascript:{}">Party Performance Report</a></li>
								<li><a href="javascript:{}">Election Comparison Report</a></li>
								<li><a href="javascript:{}">Party Results Report</a></li>
								<li><a href="javascript:{}">Election Results Analysis Report</a></li>
								<li><a href="javascript:{}">Problem Management Report</a></li>
								<li><a href="javascript:{}">User Groups</a></li>
								<li><a href="javascript:{}">Constituency Election Report</a></li>
								<li><a href="javascript:{}">Call Center</a></li>
								<li><a href="javascript:{}">Cross Voting Report</a></li>
								<li><a href="javascript:{}">Constituency Booth Results Report</a></li>
							</ul>
							</div>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<!--<div id="viewFeatures_QuickLinks_main">
						<table width="100%">
							<tr>
								<td valign="top" width="15%">
									<div id="viewFeatures_QuickLinks_1">
										<div class="viewFeatures_QuickLinks_head">Company</div>
										<div class="viewFeatures_QuickLinks_body">
											<ul class="quickLinks_list">
												<li><a href="javascript:{}">About</a></li>
												<li><a href="javascript:{}" id="contactLink" onclick="contactLinkInHomePage()">Contact Us</a></li>
												<li><a href="javascript:{}">Our Blog</a></li>
											</ul>
										</div>
									</div>
								</td>
								<td valign="top" width="15%">
									<div id="viewFeatures_QuickLinks_2">
										<div class="viewFeatures_QuickLinks_head">Why Us</div>
										<div class="viewFeatures_QuickLinks_body">
											<ul class="quickLinks_list">
												<li><a href="javascript:{}">Our Client</a></li>
												<li><a href="javascript:{}">Technology</a></li>
												<li><a href="javascript:{}">Market co.</a></li>
											</ul>
										</div>
									</div>
								</td>
								<td valign="top" width="15%">
									<div id="viewFeatures_QuickLinks_3">
										<div class="viewFeatures_QuickLinks_head">Quick Links</div>
										<div class="viewFeatures_QuickLinks_body">
											<ul class="quickLinks_list">
												<li><a href="javascript:{}">Pricing</a></li>
												<li><a href="javascript:{}">Resources</a></li>
											</ul>
										</div>
									</div>
								</td>
								<td valign="top" width="15%">
									<div id="viewFeatures_QuickLinks_4">
										<div class="viewFeatures_QuickLinks_head">Support</div>
										<div class="viewFeatures_QuickLinks_body">
											<ul class="quickLinks_list">
												<li><a href="userFeedbackAction.action">Feed Back</a></li>
												<li><a href="javascript:{}">Support Us</a></li>
											</ul>
										</div>
									</div>
								</td>
								<td valign="top" width="15%">
									<div id="viewFeatures_QuickLinks_5">
										<div class="viewFeatures_QuickLinks_head">Login</div>
										<div class="viewFeatures_QuickLinks_body">
											<ul class="quickLinks_list">
												<li><a href="javascript:{}">Client Login</a></li>
												<li><a href="javascript:{}">Admin Login</a></li>
											</ul>
										</div>
									</div>
								</td>
								<td valign="top" width="25%">
									<div id="viewFeatures_QuickLinks_6">
										<div class="viewFeatures_QuickLinks_head">News Letter Subscription</div>
										<div class="viewFeatures_QuickLinks_body" style="padding-left:27px;padding-right:10px;">
											<div><input type="text" value="Enter your email" onfocus="javascript:{if(this.value=='Enter your email'){this.value = '';}}" onblur="javascript:{if(this.value==''){this.value = 'Enter your email';}}" size="33"></div>
											<div style="text-align:right;padding-top:10px;"><input type="button" value="Submit"></input></div>
										</div>
									</div>
								</td>
								
							</tr>
						</table>
					</div>-->

					
				
		
		<div id="homePage_new_footer">
					<table width="100%">
						<tr>
							<td width="20%" valign="top">
								<div class="homePage_new_footer_links_head">About Us</div>
								<ul class="homePage_new_footer_links">
									<li><a href="footerLinksAction.action?linkFrom=aboutUs#whoWeAre">Who we are</a></li>
									<li><a href="footerLinksAction.action?linkFrom=aboutUs#whatWeDo">What we do</a></li>
									<li><a href="footerLinksAction.action?linkFrom=aboutUs#coreCompetency">Core Competency</a></li>
									<li><a href="javascript:{}" id="supportLink" onclick="supportLinkInHomePage()">Customer Support</a></li>
									<li><a href="javascript:{}" id="contactLink" onclick="contactLinkInHomePage()">Contact</a></li>
									<li><a href="javascript:{}">Sitemap</a></li>
								</ul>
							</td>
							<td width="20%" valign="top">
								<div class="homePage_new_footer_links_head">Connect</div>
								<ul class="homePage_new_footer_links">
									<li><a href="freeUserRegistration.action">Register</a></li>
									<li><a href="loginInputAction.action">Login</a></li>
									<li><a href="javascript:{}" onclick="contactLinkInHomePage()">Ask for DEMO/TEST Login</a></li>
									<li><a href="javascript:{}" onClick="showFeedBackFormPanel()">Feedback</a></li>
									<li><a href="javascript:{}">Articles</a></li>
									<li><a href="javascript:{}">Blogs</a></li>
								</ul>
							</td>
							<td width="20%" valign="top">
								<div class="homePage_new_footer_links_head">Policy</div>
								<ul class="homePage_new_footer_links">
									<li><a href="footerLinksAction.action?linkFrom=termsOfUse">Terms of use</a></li>
									<li><a href="footerLinksAction.action?linkFrom=privacy">Privacy</a></li>
									<li><a href="footerLinksAction.action?linkFrom=disclaimer">Disclaimer</a></li>
								</ul>
							</td>
							<td width="10%" valign="top">
								<div class="homePage_new_footer_links_head">We are SOCIAL</div>
								<ul class="homePage_new_footer_links">
									<li><a href="http://www.facebook.com/PartyAnalyst" target="_blank">Facebook</a></li>
									<li><a href="http://twitter.com/#!/party_analyst">Twitter</a></li>
									<li><a href="http://www.linkedin.com/company/it-grids-ltd">LinkedIN</a></li>
																	<li><a href="http://www.youtube.com/partyanalyst" target="_blank">YouTube</a></li>

								</ul>
							</td>
							<td width="30%" valign="top" align="right">
								<div class="homePage_new_footer_links_head"> &copy; Copyright 2011. All rights reserved </div>
								<div class="homePage_new_footer_links_head">
									<a href="http://www.itgrids.com" target="_blank">IT GRIDS (India) Pvt. Ltd.</a></div>
							</td>
						</tr>
					</table>
			      </div>
				  </td>
			</tr>
		</table>
   </div>
</body>
</html>