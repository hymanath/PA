<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile Page</title>

<script type="text/javascript" src="js/userProfile/userProfilePage.js"> </script>
<script type='text/javascript' src="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.0.1/bootstrap.min.js"></script>
<script type="text/javascript" src="js/customPaginator/customPaginator.js"></script>


<link type="text/css" href="styles/userProfile/userProfilePage.css" rel="stylesheet" />
<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />


<style>
	.widget .widget-block{
		padding-bottom: 20px;
		border-bottom: 1px solid #EFEFEF;
		margin: 5px -20px;
	}
	p {
		color: #333333;
		font-size: 13px;	
		line-height: 18px;
	}
	.widget-simple{

    background: none repeat scroll 0 0 #FFFFFF;
    padding: 20px;
	/*border-top: 5px solid #000;*/
	box-shadow:0 0 4px rgba(0, 0, 0, 0.3);
	margin:10px 0px 20px;
	position:relative;
}
.widget{

    background: none repeat scroll 0 0 #fafafa;
    padding:0px 20px 20px;
	border-top: 5px solid #000;
	box-shadow:0 0 1px rgba(0, 0, 0, 0.3);
	margin:10px 0px 20px;
	position:relative;
}
.p-top20{padding-top:20px;}
.p-2px{padding:2px !important;}
.red:before {
    background: #E64946;
    content: " ";
    width: 45px;
    height: 5px;
    position: absolute;
    top: -5px;
    left: 0px;
}
.red h2{color:#000;}

.green:before {
    background:#BDDA8D;
    content: " ";
    width: 45px;
    height: 5px;
    position: absolute;
    top: -5px;
    left: 0px;
}
.green h2{color:#000; }
/*.green .widget-block h5{margin:2px 2px -20px -10px;padding:5px; background:#53A753 !important; 
   color: #fff !important;text-shadow:1px 1px #000;} */
.yellow:before {
    background: #FDE498;
    content: " ";
    width: 45px;
    height: 5px;
    position: absolute;
    top: -5px;
    left: 0px;
}
.yellow h2{   color: #000;}

.blue:before {
    background:#548bd4;
    content: " ";
    width: 45px;
    height: 5px;
    position: absolute;
    top: -5px;
    left: 0px;
}
.blue h2{color: #000;}

.gray:before {
    background:#aaa;
    content: " ";
    width: 45px;
    height: 5px;
    position: absolute;
    top: -5px;
    left: 0px;
}
.gray h2{ color: #000;}

.whitegloss{background: rgb(238,238,238); /* Old browsers */
background: -moz-linear-gradient(top,  rgba(238,238,238,1) 0%, rgba(255,255,255,1) 49%, rgba(255,255,255,1) 100%); /* FF3.6+ */
background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(238,238,238,1)), color-stop(49%,rgba(255,255,255,1)), color-stop(100%,rgba(255,255,255,1))); /* Chrome,Safari4+ */
background: -webkit-linear-gradient(top,  rgba(238,238,238,1) 0%,rgba(255,255,255,1) 49%,rgba(255,255,255,1) 100%); /* Chrome10+,Safari5.1+ */
background: -o-linear-gradient(top,  rgba(238,238,238,1) 0%,rgba(255,255,255,1) 49%,rgba(255,255,255,1) 100%); /* Opera 11.10+ */
background: -ms-linear-gradient(top,  rgba(238,238,238,1) 0%,rgba(255,255,255,1) 49%,rgba(255,255,255,1) 100%); /* IE10+ */
background: linear-gradient(to bottom,  rgba(238,238,238,1) 0%,rgba(255,255,255,1) 49%,rgba(255,255,255,1) 100%); /* W3C */
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#eeeeee', endColorstr='#ffffff',GradientType=0 ); /* IE6-9 */


}

.background{  background-attachment: fixed;
    background-color: #E5E5E5;
    background-image: url("chrome://browser/skin/newtab/noise.png"), linear-gradient(rgba(255, 255, 255, 0.5), rgba(255, 255, 255, 0.2));/*background:#fff;*/} 

.widget h4,h2 {

    font-size: 15px;
    font-weight: bold;
    line-height: 20px;
    text-transform: uppercase;
	text-rendering: optimizelegibility;
	/*font-family:'Abel',sans-serif,Helvetica;*/
	font-family:sans-serif,Helvetica;
	margin:0px -20px;
	padding:5px 20px;
	border-bottom:1px solid #c0c0c0;
}
.widget .b-top1{
border-top:1px solid #efefef;
}

.widget .widget-block h5{
    color: #010101;
    font-family: Cambria,'Abel',sans-serif,Helvetica;
    font-weight: normal;   
    font-size: 18px;
  
    line-height: 18px;
 

	
}

.widget .widget-block{margin:5px -20px;
border-bottom:1px solid #efefef;
padding:5px 20px;
}
.quicklinks .widget-block .btn {display:block;clear:both;}
.m-left5{margin-left:5px;}
.m-top15{margin-top:15px;}
.clear-both{clear:both;}
.widget .pager .btn{display:inline;clear:none;}

#contenttable > .container > .row-fluid > .span6 {margin-left:10px;}
#contenttable > .container > .row-fluid > .span4 {margin-left:10px;width:245px;}
.container {
    width: 960px;
}
#pollsWidgetBody {
    border:none;
   overflow: visible;
    padding:0px;
}

.opinionpoll .breadcrumb {
    display: inline-block;
    margin: 0 -20px;
}

.opinionpoll .question{padding:5px;border-bottom:1px dashed #ccc;font:13px Arial;}
.opinionpoll .answer{border-bottom:1px dashed #ccc;padding:0px ;margin-bottom:5px;margin-left:19px;}
.opinionpoll .votebtn{margin:0px auto;display:block;width:75px;}

.form-horizontal label{  display:block; font:12px Arial;
    margin-bottom:5px;padding-top:5px;cursor:hand;cursor:pointer;}
.form-horizontal .radio
	{
	font-size: 13px;
    font-weight: normal;
    margin-top:-1px;
	margin-right:8px;
	}
	
	#headerDiv{
		font-size:16px;
		background: none repeat scroll 0 0 #ECEFF5;
		border-bottom: 1px solid #D8DFEA;
		margin: 3px;
		padding: 14px 26px;
		color:black;
		font-weight:bold;
	}
	.templatePersons span{
		clear:both;
		width:100%;
		display:inline-block;
	}
	.templatePersons{
		border: 1px solid #CFCFCF;
		float: left;
		margin: 1px;
		padding: 5px;
		width: 200px;
		height:110px;
	}
	.templatePersons .imgClass{width:50%;float:left;}
	.templatePersons .prinfo{width:50%;float:left;color:#000000;}
	 body{font-size:13px;}
	.templatePersons .connectedPersonName{font-size:14px;}
	.templateMessage {border:1px solid #CFCFCF;margin:1px;padding:4px;}
	.templateMessage .reply a{text-decoration:none;color:white}
	.templateMessage .msgDelete a{text-decoration:none;color:white}
	.templateMessage .imgClass{float:left;width:20%;}
	.templateMessage .messageFrom{float:left;width:40%;color:#3B5998;}
	.templateMessage .dateAndTimeReceived{float:right;width:40%;color:gray;}
	.templateMessage .message{color:#000;margin-left:50px;padding:10px;}
	.templateMessage .msgBtns{width:50%;margin-left:auto;margin-right:auto;}
	
	/* subscription Styles Start */
	.specialPagSubscrDiv{margin-top: 10px;} 
	.specialPagSubscrDiv .btnClass{margin-top: 35px;}
	.subscriptionType{font-size: 15px;font-weight: bold;margin-right: 133px;}
	.unSubscriptionBtn{margin-left: 25px;}
    .subscriptionInnerDiv{clear:both; margin-bottom: 10px;margin-top: 10px;display: table;}

	
	.problemheadClass{margin-bottom: 20px;margin-top: 10px;font-family: Verdana,Arial,sans-serif;}
	.problemsShowDiv,.politicalReasonsDiv{border: 1px solid #D3D3D3;border-radius: 5px;padding: 10px; padding-top: 2px;margin-top: 10px;}
	.problemTitle,.headingCls{color: -moz-menuhover;font-size: 16px;font-weight: bold;}
	.likeCls{margin-right: 55px; margin-left: 62px;}
	.commentCls{margin-right: 62px;}
	.problemFromDate{margin-left: 220px;color: #273241;}
	.problemReportedDate{float: right;color: #273241;}
	.problemsShowDIV p{font-size: 12px; text-align: justify;color: #273241;}
	.problemsShowDIV .problemTitle{font-size: 13px;}
	/* subscription Styles End */
	/* upload image */

	#uploadPic_window_head {
    background-color: #9AB0C3;
    background-image: url("../images/icons/homePage_new/accordianHeader_selected.jpeg");
    color: #FFFFFF;
    font-weight: bold;
    padding: 5px;
}
.uploadPic_string_table td {
    color: #69645B;
    font-size: 10px;
    padding: 5px;
    text-align: left;
}

#profileUserName{ margin-bottom: 5px;margin-top: 10px;}
.btn-group{margin-left: 8px;}

#sendMessageButtonId{margin-left: 265px;margin-top: 20px;padding: 3px;}
#connectMessageText,#connectUserMsg{background: none repeat scroll 0 0 transparent !important;}
.messageLabel{margin-bottom: 9px; margin-top: 5px;font-family: verdana;}
#ErrorMsgDivId{clear: both;margin-top: 5px;font-family: helvetica;}
#connectPeopleLink {
    background-color: #2D5773;
    border: medium none;
    color: #FFFFFF;
    cursor: pointer;
    font-weight: bold;
    margin: 0 10px;
    padding: 5px;
	float: right; margin: -11px 38px 16px 0px;
}
#connectUserMsg{margin-left: 12px;}


.changePwdDiv{ font-family: arial;margin-bottom: 12px;}
#password_window{ margin-bottom: 15px;}
#currentPwdId{margin-left: 22px;}
#confirmPwdId{margin-left: 20px;}
#newPwdId{ margin-left: 44px;}
#changePWDButton,#cancelButtonID{padding:3px;}
#password_window_errorMsg{  margin-bottom: 8px;}
#changePWDButton{margin-left: 165px;margin-right: 26px;}
#uploadPic_window_footer{margin-top: 10px;}
#uploadPic_string_table{ margin-top: 6px;}
.fontStyle{color: #000000;font-family: verdana;font-size: 12px;}
.l1{color: maroon;font-family: verdana;padding: 4px;}
</style>
</head>
<body>
	<div class="container m-top15">
		<div class="row-fluid">
			
		<!--------left div------->
			<div class="span3">

				<div class="widget blue votersguide p-m-bottom0px">
			        <div style="text-align: center; padding: 10px 0px;">
					
						<c:if test="${loginUserProfilePic == '' || loginUserProfilePic == null}">
							<img width="90" height="100" src="pictures/profiles/human.jpg" style="border:1px solid #ADADAD;" id="userProfileImg">
						</c:if>
						<c:if test="${loginUserProfilePic !='' && loginUserProfilePic != null}">
						<img width="90" height="100" src="pictures/profiles/${loginUserProfilePic}" style="border:1px solid #ADADAD;" id="userProfileImg">
						</c:if>

						<div id="profileUserName">${loginUserName}<div class="btn-group">
							<button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
								<li><a href="freeUserRegistration.action">Edit Profile</a></li>
								<li><a href="javascript:{}" class="changePwdLink">Change Password</a></li>
								<li><a href="javascript:{}" class="editPictureLink">Edit Picture</a></li>
							</ul>
						</div></div>
						
						<div style="margin-bottom: 4px; font-size: 13px; font-family: verdana;"><a href="constituencyPageAction.action?districtId=${dataTransferVO.districtId}&constituencyId=${dataTransferVO.constituencyId}">${dataTransferVO.constituencyName}</a></div>
						<div style="margin-bottom: 4px; font-size: 13px; font-family: verdana;"><a href="districtPageAction.action?districtId=${dataTransferVO.districtId}&districtName=${dataTransferVO.districtName}">${dataTransferVO.districtName}</a></div>
						<div style="font-size: 13px; font-family: verdana;"><a href="statePageAction.action?stateId=${dataTransferVO.stateId}">${dataTransferVO.stateName}</a></div>
					</div>	
					    
						
				</div>
				<div class="widget blue">
					<h4>
						<span><i class="userMenu" id="icon_leftsec"></i></span>
						User Menu
					</h4>
					<p class="p1"><span>Whats's New?</span></p>
					<p class="p1"><a href="javascript:{}" class="messagesLink">Messages</a></p>
					<p class="p1"><a href="javascript:{}" id="friendsLink">Friends</a></span></p>
					<p class="p1"><a href="javascript:{}" id="requestLink">Requests</a></p>
				</div>
				
				
				<div class="widget yellow">
					<h4>
						<span><i class="userMenu" id="icon_leftsec"></i></span>
						Applications
					</h4>
					<p class="p2"><span>Important Dates</span></p>
					<p class="p2"><span><a href="javascript:{}" class="problemsLink">Problems</a><input type="hidden" value="Total" class="problemTypeVariable"/></span></p>
				</div>
				
				
				<div class="widget blue">
					<h4>
						<span><i class="userMenu" id="icon_leftsec"></i></span>
						Interests
					</h4>
					<p class="p3"><a href="javascript:{}" class="subscriptionsLink">Subscriptions</a></p>
					<p class="p3"><a href="javascript:{}" class="assessPoliticianLink">Asses Politician</a></p>
					<p class="p3"><a href="javascript:{}" id="specialPageLink">Special Pages</a></p>
				</div>
				
				
			</div>
		<!--------left div End ------->

		<!--------Center div------>
			<div class="span6">
				<div class="widget green">
				<div id="headerDiv"></div>
					<div class="placeholderCenterDiv"></div>
						<div id="subscriptionsDiv">
							<div id="userSpecialPageSubscriptionsDiv" class="subscriptionInnerDiv"></div>
							<div id="userSpecialPageUnSubscriptionsDiv" class="subscriptionInnerDiv"></div>

							<div id="userCandidateSubscriptionsDiv" class="subscriptionInnerDiv"></div>
							<div id="userPartySubscriptionsDiv" class="subscriptionInnerDiv"></div>
							<div id="userConstituencySubscriptionsDiv" class="subscriptionInnerDiv"></div>
							
					</div>
				</div>

			</div>
		<!--------Center div------>


		<!--------Right div------>
			<div class="span4">
		  <!-- Profile Statistics Div Start -->
				<div class="widget red prStatistics" style="display:none;">
					<p class="userMenu" style="text-align:right;"><span>Your Profile Statistics</span></p>
					<div class="profileStatisticsDiv">
					<ul>
						<li>Friends:<a href="javascript:{}" class="friendsLink"></a> </li>
						<li>Subscriptions:</li>
						<li>Connected Pages: </li>
						<li>Politician Assess: </li>
						<li>Problems Posted: </li>
						<li>Messages: <a href="javascript:{}" class="messagesLink">${dataTransferVO.totalMsgCount}</a></li>
						
					</ul>
					</div>
				

				</div>
			<!-- Profile Statistics Div End -->
			
			

				<div class="widget blue">
					<h4>
						<span><i class="connectPeopleHeading" id="icon_leftsec"></i><span>People You May Know</span>
						<p>
						<span>
							<a class="btn btn-mini btn-info"href="javascript:{}" id="districtPeopleLink">See All</a>
						</span>
					</p>
					</h4>
					
					<div style="margin-top: 14px;">
					<c:forEach var="connectedPeoples" items="${dataTransferVO.peopleYouMayKnow}" begin="0" end="2">
						<div class="connectPeopleDiv" style="margin-top: 21px;width:100%;">
							<div style="width:30%;float:left;"><img height="50" width="55" src="/PartyAnalyst/images/icons/indexPage/human.jpg" /></div>
							<div style="width:70%;float:left;">
								<div style="margin-bottom:5px;margin-top:5px;">${connectedPeoples.candidateName}</div>
								<div>${connectedPeoples.constituencyName}</div>
							</div>
						
							<input type="hidden" value="${connectedPeoples.id}" class="userId" />
							<input type="hidden" value="${connectedPeoples.candidateName}" class="userName" />
							<input type="hidden" value="${connectedPeoples.constituencyName}" class="constituencyName" />
						
						</div>
						<div style="width:100%; clear:both;margin-left:20px;">
							<span  style="margin-right: 25px; margin-left:25px;">
								<a href="javascript:{}" class="connectLink">Connect</a>
							</span>
							<span>
								<a href="javascript:{}" onclick="showMailPopup('${connectedPeoples.id}',' ${connectedPeoples.candidateName}','Message')">Send Message</a>
							</span>
						</div>
						</c:forEach>
				</div>

				</div>
			<!-- People You May Know Div End -->

				<!-- special Pages-->
				<div class="widget red hottopics">
					<h2>
					<span><i class="icon-fire" id="icon_leftsec"></i></span>
					You Might Like</h2>
				
					<c:forEach var="specialPages" items="${specialPageVOList}" begin="0" end="1">
					<div class="media media_hr widget-block">
						
						<div class="media-body">
							<h5>${specialPages.title}</h5>
								
								<img src="${specialPages.eventImagePath}" width="200px"height="85px" style="margin-bottom:12px; " alt="${specialPages.title} Image"/>
								<p class="hottopics_desc">
									${specialPages.description}
								</p>
						</div>
						<a href="specialPageAction.action?specialPageId=${specialPages.specialPageId}" class="btn-info btn-small pull-right" title="${specialPages.title}">View Now</a>
					</div>
						</c:forEach>
						<a href="specialPageInfoAction.action" class="btn btn-primary" >View More Special Pages</a>						
				</div>
				<!-- end special Pages -->
				
				<!-- opinionPollDiv Start -->
				<div class="widget blue" style="padding-bottom:0px;">
					<h2><span><i class="icon-signal "id="icon_leftsec"></i><span>
					Opinion Poll</h2>
						<div class="widget-block">
							<div id="pollsWidgetHeader"></div>
                            <div><div id="alreadyVotedMsg"></div></div>
							<div id="pollsWidgetBody"></div>
							<div id="pollsWidgetFooter"></div>
						</div>
				
				</div>
				<!-- opinionPollDiv End -->
		
			</div>
		<!--------Right div Close------>
			
		</div>
	</div>
 </div>
</div>

<div id="connectPeoplePopup" style="display:none;">
	<div id="allConnectedUsersDisplay_main"></div>
	</div>


<!-- Templates -->

	<div class="templateDiv templateholder templatePersons">
			<span class="connectedPersonName" style="color:#3B5998;"></span>
			<div class="imgClass"></div>
			<div class="prinfo">
				<span class="constituencyName"></span>
				<span class="districtName"></span>
				<span class="stateName"></span>
				<span class="sendMsg"></span>
				<span class="connectCls"> </span>
			</div>
	</div>
	<div class="templateDivMsg templateholder templateMessage" >
			<div class="breadcrumb white-gloss" style="padding:10px;">
				<div class="imgClass"></div>
				<span class="messageFrom"></span>
				<span class="dateAndTimeReceived"></span>
			</div>
			
			<div class="message"></div>
			<div class="msgBtns" >
				<span class="reply btn-mini btn-info"></span>
				<span class="msgDelete btn-mini btn-info"></span>
			</div>
	</div>
	

	<div class="specialPagSubscrTemplDiv templateholder specialPagSubscrDiv templatePersons">
		<div class="imgClass"></div>
		<div class="prinfo">
			<span class="titleCls"></span>
			<span class="btnClass"></span>
			<span class="hiddenVar"></span>
		</div>
		
	</div>

	<div class="problemTemplateDiv templateholder problemsShowDiv">
		<div class="problemsShowDIV">
			<div class="">
				<span class="problemImg"></span>
				<span class="postedPersonName"></span>
			</div>
			<div class="problemheadClass">
				<span class="problemReportedDate"></span>
				<p class="problemTitle"></p>
				<p class="problemDescription"></p>
				<span class="problemFromDate"></span>
			</div>
			<span class="likeCls"></span>
			<span class="commentCls"></span>
			<span class="shareCls"></span>
		</div>
	</div>
	
		
	<div class="politicalReasonsTemplate templateholder politicalReasonsDiv">
		<div class="politicalReasonsInnerDiv">
			<p class="headingCls"></p>
			<div class="politicalReasons">
				<p class="politicalReaCls"></p>
				<p class="politicalDescCls"></p>
				<p class="polReaPostedDate"></p>
				<p class="polReaPostedPerName"></p>

			</div>
		</div>
	</div>


<!-- Templated END -->

	

<script>



districtId = '${dataTransferVO.districtId}';
districtName = '${dataTransferVO.districtName}';
constituencyId = '${dataTransferVO.constituencyId}';
constituencyName = '${dataTransferVO.constituencyName}';
var loginUserId = '${loginUserId}';

loginUserName = '${loginUserName}';	
loginUserProfilePic = '${loginUserProfilePic}';	
connetLocationId = constituencyId;
userType = '${UserType}';

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
		buildPolls();
</script>
</body>
</html>