
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Connect People</title>

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
<!--<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>-->
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

<!-- JQuery files (End) -->

<script type="text/javascript" src="http://www.google.com/jsapi"></script>

<script type="text/javascript" src="js/customPaginator/customPaginator.js"></script>
<script type="text/javascript" src="js/connectPeople/connectPeople.js"></script>
<script type="text/javascript" src="js/connectPeople/connectPeopleContent.js"></script>
<script type="text/javascript" src="js/homePage/homePage.js"> </script>

<link rel="stylesheet" type="text/css" href="styles/statePage/statePage.css">	
<link rel="stylesheet" type="text/css" href="styles/constituencyPage/constituencyPage.css">	
<link rel="stylesheet" type="text/css" href="styles/districtPage/districtPage.css">	
<link  rel="stylesheet" type="text/css" href="styles/landingPage/landingPage.css"/>
<link rel="stylesheet" type="text/css" href="styles/connectPeople/connectPeople.css">

<script type="text/javascript">
	google.load("elements", "1", {packages : ["newsshow"]});

	


	
</script>
<STYLE type="text/css" >

.yui-skin-sam .yui-navset .yui-nav .selected a, .yui-skin-sam .yui-navset .yui-nav .selected a em {
    background-image:url("images/icons/homePage_new/accordianHeader_selected.jpg");
   }
   #connectPeople_messages_center .yui-navset .yui-nav .selected a {
    background: url("../../js/yahoo/yui-js-2.8/build/assets/skins/sam/sprite.png") repeat-x scroll 0 0 #D8D8D8;
    color: #FFFFFF;
    font-weight: bold;
}
</STYLE>
</head>
<body>
      <div id="password_change_window" style="background-color: #C7CFD2;">
	  <div id="password_change_window_inner"></div></div>
	<div id="connectPeoplePopup_outer" class="yui-skin-sam">
		<div id="connectPeoplePopup" style="display:none;"><div id="allConnectedUsersDisplay_main"><img src="images/icons/barloader.gif"/></div></div>
	</div>
	<div id="jQueryPopup"> <div id="reasonsDataTable_outer" class="yui-skin-sam"> <div id="reasonsDataTable"></div> </div></div>
	<div id="connectPeopleMessagePopup_main" class="yui-skin-sam"><div id="connectPeopleMessagePopup"></div></div>
	<div id="uploadPic_window"><div id="uploadPic_window_inner"></div></div>
	<div id="connectPeoplePage_main">
		<table width="100%">
			<tr>
				<td width="70%" valign="top">
					
					<div id="constituencyLeftContentOuter" class="rounded" style="width:650px;"> 						
						<div class="corner topLeft"></div>
						<div class="corner topRight"></div>
						<div class="corner bottomLeft"></div>
						<div class="corner bottomRight"></div>
							
							<div id="connectPeople_profile_center" class="yui-skin-sam">
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
											<div class="connectPeople_profile_center_linksDiv">
												<table>
													<tr>
														<td><img width="10" width="10" src="images/icons/homePage_new/widgetHeaderIcon.jpeg"></td>
														<td><a href="javascript:{}" onclick="openAddReasonWindow('analyze')">Add Political Reasons</a></td>
													</tr>
												</table>												
											</div>
											<div class="connectPeople_profile_center_linksDiv">
												<table>
													<tr>
														<td><img width="10" width="10" src="images/icons/homePage_new/widgetHeaderIcon.jpeg"></td>
														<td><a href="javascript:{}" onclick="openAddReasonWindow('viewResults')">View Political Reasons</a></td>
													</tr>
												</table>												
											</div>
											<div class="connectPeople_profile_center_linksDiv">
												<table>
													<tr>
														<td><img width="10" width="10" src="images/icons/homePage_new/widgetHeaderIcon.jpeg"></td>
														<td><a href="javascript:{}" onclick="openAddNewProblemWindowForDashBoard()">Post Problem</a></td>
													</tr>
												</table>												
											</div>
										</td>
								</c:if>
									</tr>
									
								</table>
							</div>
							<div id="connectPeople_messages_center" class="yui-skin-sam">
											 
							</div>

							<!--<div id="connectPeople_connect_center" class="yui-skin-sam">
											 
							</div>-->
					</div>

					
				</td>
				<td width="30%" valign="top">
					<div id="constituencyRightContentOuter" class="rounded" style="width:270px;"> 						
						<div class="corner topLeft"></div>
						<div class="corner topRight"></div>
						<div class="corner bottomLeft"></div>
						<div class="corner bottomRight"></div>
							<!-- Accordian Start-->
							<div id="connectedPeopleAccordian">
								<h3><a href="#">View Your Location Details</a></h3>
								<div style="padding:0px;height:250px;">
									<div id="connectPeople_quickAccess_center" class="yui-skin-sam" style="margin-top:10px;">									 
									</div>	
								</div>
								<h3><a href="#">People In Your Location</a></h3>
								<div style="padding:0px;height:250px;">
									<div id="connectPeople_count_center" class="yui-skin-sam" style="margin-top:10px;">											
									</div>	
								</div>
								<h3><a href="#">People You May Know</a></h3>
								<div style="padding:0px;height:250px;">
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
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
										  <tr>
											<td width="1%"><img width="45" height="40" src="images/icons/homePage_new/poll_header_left.jpg"/></td>
											<td width="98%">
												<div class="electionTrendzHeaderBackground_center">
													<span class="headerLabelSpan headerLabelSpan1" style="color:#C66E17;top:13px;">
														Opinion Poll
													</span>
												</div>
											</td>
											<td width="1%"><img width="25" height="40" src="images/icons/homePage_new/poll_header_right.jpg"/></td>
										  </tr>
										</table>	
									 </div>
									<div id="pollsWidgetBody" class="yui-skin-sam" style="height:293px;">
									</div>
									<div id="pollsWidgetFooter">
										
									</div>
								</div>
							<!-- Opinion Poll End-->
							<!--<div id="connectPeople_editProfile_center" class="yui-skin-sam">
							</div>-->
							
							
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2" valign="top">
					<div id="constituencyBottomContentOuter" class="rounded" style="width:940px;"> 						
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

		initializeConnectPeople();
	</script>
</body>
</html>