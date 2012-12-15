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
<script type="text/javascript" src="js/opinionpoll/opinionpoll.js"> </script>
<script type="text/javascript" src="js/customPaginator/customPaginator.js"></script>

<script type='text/javascript' src="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.0.1/bootstrap.min.js"></script>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
	
<!-- rating -->
		<!-- <link href="styles/rating/jquery.rating.css" rel="stylesheet" type="text/css">
		<link href="styles/newhome_inner_styles.css" rel="stylesheet" type="text/css" />
		<script src="js/rating/jquery.MetaData.js" type="text/javascript"></script>
		<script type="text/javascript" src="js/raty/js/jquery.raty.min.js"></script>
		<script src="js/slides.min.jquery.js"></script>-->
<!-- rating -->
    <!-- syntax highlighter -->
    <link href="styles/rating/shCore.css" rel="stylesheet" type="text/css">
    <link href="styles/rating/shCoreDefault.css" rel="stylesheet" type="text/css">
	
  <link type="text/css" href="styles/userProfile/userProfilePage.css" rel="stylesheet" />
<style>
.profile-left .widget-block{margin-top:0px;margin-bottom:0px;padding-bottom:0px;padding-top:0px;border:none;}
.profile-left .widget-block h4{border:none;background:#e5e5e5;display:none;}
/* 14-12 -12 */
.unreadfont{font-weight:bold;}
</style>
</head>
<body>
	<div class="container m-top15">
		<div class="row-fluid">
			
		<!--------left div------->
			<div class="span3">

				<div class="widget blue profile-left">
			        <div class="row">
					<div class="span5 profileimg pull-left">
						<c:if test="${loginUserProfilePic == '' || loginUserProfilePic == null}">
							<img src="pictures/profiles/human.jpg" id="userProfileImg" class="thumbnail">
						</c:if>
						<c:if test="${loginUserProfilePic !='' && loginUserProfilePic != null}">
						<img src="pictures/profiles/${loginUserProfilePic}" class="thumbnail" id="userProfileImg">
						</c:if>
					</div>
						<div id="profileUserName" class="span7 pull-right"><h6>${loginUserName}</h6><div class="btn-group">
							<button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
								<li><a href="freeUserRegistration.action">Edit Profile</a></li>
								<li><a href="javascript:{}" class="changePwdLink">Change Password</a></li>
								<li><a href="javascript:{}" class="editPictureLink">Edit Picture</a></li>
							</ul>
						</div></div>
						
												
					</div>	
					    
						
				
				<div class="widget-block">
					<h4>
						<span><i class="icon icon_left" id="icon_leftsec"></i></span>
						User Menu
					</h4>
				
								
					<ul class="nav nav-list bs-docs-sidenav nav-stacked">
					<li class="active"><a href="javascript:{}" class="whatsnew"><i class="icon-fire"></i><i class="icon-chevron-right"></i> Whats's New?</a></li>
					<li><a href="javascript:{}" class="messagesLink"><i class="icon-envelope"></i><i class="icon-chevron-right"></i> Messages</a></li>
					<li><a href="javascript:{}" id="friendsLink"><i class="icon-comment"></i><i class="icon-chevron-right"></i> Friends</a></li>
					<li><a href="javascript:{}" id="requestLink"><i class="icon-retweet"></i><i class="icon-chevron-right"></i> Requests</a></li>
					</ul>
				
				</div>
				
				<div class="widget-block">
					<h4>
						<span><i class="userMenu" id="icon_leftsec"></i></span>
						Applications
					</h4>
					
					
					<ul class="nav nav-list bs-docs-sidenav nav-stacked">
					<li><a href="javascript:{}" class="ImportantDates"><i class="icon-calendar"></i><i class="icon-chevron-right"></i> Important Dates</a></li>
					<li><a  href="javascript:{}" class="problemsLink"><i class="icon-exclamation-sign"></i><i class="icon-chevron-right"></i> Problems</a><input type="hidden" value="Total" class="problemTypeVariable"/></li>
					</ul>
				</div>
				
				
				<div class="widget-block">
					<h4>
						<span><i class="userMenu" id="icon_leftsec"></i></span>
						Interests
					</h4>
					<ul class="nav nav-list bs-docs-sidenav nav-stacked">
					<li ><a href="javascript:{}" class="subscriptionsLink"><i class="icon-list"></i><i class="icon-chevron-right"></i> Subscriptions</a></li>
					<li><a  href="javascript:{}" class="assessPoliticianLink"><i class="icon-edit"></i><i class="icon-chevron-right"></i> Asses Politician</a>
					<input type="hidden" value="Total" class="politicalReasTypeVar" /></li>
					<li><a href="javascript:{}" id="specialPageLink"><i class="icon-heart"></i><i class="icon-chevron-right"></i> Favorite Links</a></li>
					</ul>
				</div>
				
				</div>
			</div>
		<!--------left div End ------->

		<!--------Center div------>
			<div class="span6">
				<div class="widget green" id="MyProfileActions">
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
				<!-- <h4>People In Your Location</h4>
				<div>
				<p>From ${dataTransferVO.districtName} District - <a href="javascript:{}" onclick="getAllConnectedUsersByFilterView('DISTRICT')">${dataTransferVO.districtUsersCount}</a></p>
				<p>From ${dataTransferVO.constituencyName} Constituency - ${dataTransferVO.constituencyUsersCount}</p>
				</div>-->
					<h4>
						<span><i class="connectPeopleHeading" id="icon_leftsec"></i><span>People You May Know</span>
					</h4>
					
					<div style="margin-top: 14px;">

					 <c:if test="${not empty dataTransferVO.peopleYouMayKnow}">

					<c:forEach var="connectedPeoples" items="${dataTransferVO.peopleYouMayKnow}" begin="0" end="2">

						<div class="connectPeopleDiv" style="margin-top: 21px;width:100%;">
							<div style="width:30%;float:left;">
							<a href="publicProfile.action?profileId=${connectedPeoples.id}">
							 <c:if test="${connectedPeoples.image != null && connectedPeoples.image !=''}">
								<img height="50" width="55" src="/PartyAnalyst/pictures/profiles/${connectedPeoples.image}" />
							</c:if>
							<c:if test="${connectedPeoples.image == null || connectedPeoples.image == ''}">
								<img height="50" width="55" src="/PartyAnalyst/images/icons/indexPage/human.jpg" />
							</c:if>
							</a></div>
							<div style="width:70%;float:left;">
								<div style="margin-bottom:5px;margin-top:5px;"><a href="publicProfile.action?profileId=${connectedPeoples.id}">${connectedPeoples.candidateName}</a></div>
								<div>${connectedPeoples.constituencyName}</div>
							</div>
						
						
						</div>
						<div style="width:100%; clear:both;margin-left:20px;">
							<span  style="margin-right: 25px; margin-left:25px;">
								<a href="javascript:{}" class="connectLink">Connect</a>
							</span>
							<span>
								<a href="javascript:{}" onclick="showMailPopup('${connectedPeoples.id}',' ${connectedPeoples.candidateName}','Message')">Send Message</a>
							</span>
							<input type="hidden" value="${connectedPeoples.id}" class="userId" />
							<input type="hidden" value="${connectedPeoples.candidateName}" class="userName" />
							<input type="hidden" value="${connectedPeoples.constituencyName}" class="constituencyName" />

						</div>
						</c:forEach>
						</c:if>

						<c:if test="${empty dataTransferVO.peopleYouMayKnow}">
							<div>
							Right now there are no friend suggestion for you.	We will get back with more suggesstions as soon as possible..
							</div>
						</c:if>
				</div>
						
				<p class="p4"><a class="btn btn-mini btn-small btn-info districtPeopleLink"href="javascript:{}">See All</a></p>

				</div>
			<!-- People You May Know Div End -->

				<!-- special Pages-->
				<div class="widget red hottopics">
					<h2>
					<span><i class="icon-thumbs-up" id="icon_leftsec"></i></span>
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
<div class="TemplateCollection">

	<div class="templateDiv templateholder templatePersons">
			<span class="connectedPersonName" style="color:#3B5998;"></span>
			<div class="imgClass"></div>
			<div class="prinfo">
				<span class="messageCls" style="display:none"></span>
				<span class="constituencyName"></span>
				<span class="districtName"></span>
				<span class="stateName"></span>
				<span class="sendMsg"></span>
				<span class="connectCls"> </span>
				<span class="blockPersonBtn" style="display:none;"></span>
			</div>
	</div>
	<div class="templateDivMsg templateholder templateMessage" >
			<div class="white-gloss readMsg popover-title" style="padding:2px;height:30px;">
				<div class="imgClass"></div>
				<span class="messageFrom"></span>
				<span class="dateAndTimeReceived"></span>
				
			</div>
			<input type="hidden" class="msgid">
			<div class="msgbox popover-content" style="display:none;">
				<div class="message" ></div>
				<div class="msgBtns" >
					<span class="reply"></span>
					
					<!--<span class="msgDelete icon-remove"></span>-->
				</div>
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
				<p>
					<span class="problemTitle"></span>
					
				</p>
				<p class="problemDescription"></p>
				<span class="problemFromDate"></span>
			</div>
			<span class="likeCls"></span>
			<span class="commentCls"></span>
			<span class="shareCls"></span>

			<div><span class="problemRating"></span></div>
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
		
	$(document).ready(function(){
	
		$(".profile-left .bs-docs-sidenav a").click(function(){
		
			$(".bs-docs-sidenav li").removeClass("active");
			$(this).closest("li").addClass("active");
		
		});
	
	});	
</script>
</body>
</html>