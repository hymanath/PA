<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.ResourceBundle;" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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

<!-- JQuery files (End) -->
<script type="text/javascript" src="js/customPaginator/customPaginator.js"></script>
<script type="text/javascript" src="js/connectPeople/connectPeople.js"></script>
<script type="text/javascript" src="js/connectPeople/connectPeopleContent.js"></script>

<link rel="stylesheet" type="text/css" href="styles/statePage/statePage.css">	
<link rel="stylesheet" type="text/css" href="styles/constituencyPage/constituencyPage.css">	
<link rel="stylesheet" type="text/css" href="styles/districtPage/districtPage.css">	
<link rel="stylesheet" type="text/css" href="styles/connectPeople/connectPeople.css">

</head>
<body>
	<div id="connectPeoplePopup_outer" class="yui-skin-sam">
		<div id="connectPeoplePopup" style="display:none;"><div id="allConnectedUsersDisplay_main"><img src="images/icons/barloader.gif"/></div></div>
	</div>
	<div id="jQueryPopup"> <div id="reasonsDataTable_outer" class="yui-skin-sam"> <div id="reasonsDataTable"></div> </div></div>
	<div id="connectPeopleMessagePopup_main" class="yui-skin-sam"><div id="connectPeopleMessagePopup"></div></div>
	<div id="connectPeoplePage_main">
		<table width="100%">
			<tr>
				<td width="70%" valign="top">
					
					<div id="constituencyLeftContentOuter" class="rounded"> 						
						<div class="corner topLeft"></div>
						<div class="corner topRight"></div>
						<div class="corner bottomLeft"></div>
						<div class="corner bottomRight"></div>
						
							<div id="connectPeople_messages_center" class="yui-skin-sam">
											 
							</div>
					</div>

					
				</td>
				<td width="30%" valign="top">
					<div id="constituencyRightContentOuter" class="rounded"> 						
						<div class="corner topLeft"></div>
						<div class="corner topRight"></div>
						<div class="corner bottomLeft"></div>
						<div class="corner bottomRight"></div>
							<div id="connectPeople_editProfile_center" class="yui-skin-sam">
							</div>
							<div id="connectPeople_quickAccess_center" class="yui-skin-sam" style="margin-top:10px;">									 
							</div>	
							<div id="connectPeople_count_center" class="yui-skin-sam" style="margin-top:10px;">											
							</div>	
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2" valign="top">
					<div id="constituencyBottomContentOuter" class="rounded"> 						
						<div class="corner topLeft"></div>
						<div class="corner topRight"></div>
						<div class="corner bottomLeft"></div>
						<div class="corner bottomRight"></div>							
							<table width="100%">
								<tr>
									<td width="70%" valign="top">
										<div id="connectPeople_connect_center" class="yui-skin-sam">
											 
										</div>
									</td>
									<td width="30%" valign="top">
										<div id="connectPeople_PeopleMayKnow_center" class="yui-skin-sam">
											 
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
							status:'${unKnownPeople.status}',
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