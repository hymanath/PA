<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile Page</title>
<script type="text/javascript" src="js/userProfile/userProfilePage.js"> </script>
<script type="text/javascript" src="js/opinionpoll/opinionpoll.js"> </script>
<script type="text/javascript" src="js/customPaginator/customPaginator.js"></script>
<script type="text/javascript" src="js/userProfile/impDates.js"> </script>
<script type='text/javascript' src="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.0.1/bootstrap.min.js"></script>
<script src="js/jQuery/image-crop/js/jquery.Jcrop.js" type="text/javascript"></script>
<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet" />
<link rel="stylesheet" href="js/jQuery/image-crop/css/jquery.Jcrop.css" type="text/css" />
<script type='text/javascript' src="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.0.1/bootstrap.min.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
<!-- YUI Dependency files (Start) -->
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
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
<link rel="stylesheet" type="text/css" href="styles/cadreManagement/cadreManagement.css">

<script type="text/javascript">
var smsDialog, newEventDialog, newDateDialog,eventDateDialog,mainEventCalendar,dateCalendar,cadreDataTable,cadreAnim,jsonStr; 
var monthname = new Array("January", "February", "March", 
		"April", "May", "June", "July", "August", "September", 
		"October", "November", "December"); 
		
var requestPath = '<%=request.getContextPath()%>';
</script>
<style>
.profile-left .widget-block{margin: 0 -20px !important;;padding-bottom:0px;padding-top:0px;border:none;display:inline-block;width:100%;height:auto;}
.profile-left .widget-block h4{border:none;background:#e5e5e5;display:none;}
.left-section{width:175px  !important;}
.centerSpan6{width:515px !important;}
.problemsViewMoreLink{cursor:pointer;}
.connectPeopleDiv{display:inline-block;  border-bottom: 1px solid #EFEFEF;
    
    padding: 5px 20px;padding-left:5px;clear:both;width:200px; margin:0px -20px;text-transform:Capitalize;}
	
.ajaxImg{display:none;}
.opacityFilter-50{filter: alpha(opacity=65);
	opacity: 0.65;}
	.viewMoreDiv{margin-top: 12px;}
	.ajaxImg{margin-left: 35px;}
	.favouriteLinkDivClass{
/*border:1px solid #c3c3c3;*/
border-radius:2px;
height:50px;
}
.favouriteLinksHeading{
margin-bottom:10px;
margin-top:9px;
}
.removeLinkButton{
float:right;
margin:8px 9px 0px 0px;
}
.politicalReasAddLink{margin-left: 200px; margin-right: 26px; margin-bottom: 10px;}
#newImpDateDiv,#eventDateDetails{
	height: auto;
    min-height: 26.1667px;
    text-align: justify;
    width: auto;
}
.ui-widget{font:12px "Helvetica Neue",Helvetica,Arial,sans-serif}
.subscriptionsMain{
display:inline-block;
    height: auto;
    padding: 8px 20px;
	margin:5px -11px;
    width: 96%;
	
	background:#f2f2f2; 

	transition: all 0.4s ease-in-out 0s;
}

.subscriptionsImgTitleDescDiv {width:100%;background:#fafafa;display:inline-block;padding:5px;margin:5px;}
.subscriptionsHeaderTitleDiv{margin-left:-5px;}
.subscriptionsHeaderTitleDiv .span2{width:60px;}
.subscriptionsHeaderTitleDiv .date-span{font-style:italic;font-size:11px;text-transform:Capitalize;color:#999;text-align:right;}
 .activity-name{padding-left:5px;  color: #777777;}
 .activity-title h5{font-size:13px;}
 
 
 
 .ProblemImgTitleDescDiv{
	margin: 5px;
    padding: 5px;
	
 }
 
 #prblmCommentShare{
	margin-left:auto;margin-right:auto;width:168px;
 }
 
 #prblmCommentShare .commentCls a{text-decoration:none;}
 #prblmCommentShare .shareCls a{text-decoration:none;}
 
 .prblmsHeader .APSpan{width:25%;float:left;padding:15px;}
 
 .paginatorElmtClass a{
   display: inline-block;
 
  padding: 4px 12px;
margin:3px;

  font-size: 14px;
  line-height: 20px;
  color: #333333;
  text-align: center;
  text-shadow: 0 1px 1px rgba(255, 255, 255, 0.75);
  vertical-align: middle;
  cursor: pointer;
  background-color: #f5f5f5;
}

#profileUserName{text-transform: capitalize;}
.subscrStreamingMoreCls{display:none;}
</style>
</head>
<body>
	<div class="container m-top15">
		<div class="row-fluid">
			
		<!--------left div------->
			<div class="span3 left-section">

				<div class="widget blue profile-left">
			        <div class="profile-pic widget-block">
								<div class="profileimg span12 thumbnail">
									<c:if test="${loginUserProfilePic == '' || loginUserProfilePic == null}">
										<img src="pictures/profiles/human.jpg" id="userProfileImg" >
									</c:if>
									<c:if test="${loginUserProfilePic !='' && loginUserProfilePic != null}">
									<img src="pictures/profiles/${loginUserProfilePic}" id="userProfileImg">
									</c:if>
								</div>
									
						</div>	
						
						<!--<div class="widget-block">
							<button class="btn  dropdown-toggle" data-toggle="dropdown">
								<i class="icon-wrench"></i>Settings<span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
								<li><a href="freeUserRegistration.action">Edit Profile</a></li>
								<li><a href="javascript:{}" class="changePwdLink">Change Password</a></li>
								<li><a href="javascript:{}" class="editPictureLink">Edit Picture</a></li>
								<li><a href="javascript:{getUserSettingsDetails();}" class="editSettingsLink">Edit View Settings</a></li>
							</ul>
						</div>-->
										
					
					    <div class="widget-block" id="profileUserName">
						<h5>${loginUserName}</h5> </div>
				
				<div class="widget-block">
					<h4>
						<span><i class="icon icon_left" id="icon_leftsec"></i></span>
						User Menu
					</h4>
				
								
					<ul class="nav nav-list bs-docs-sidenav nav-stacked">
					<li><a href="javascript:{}" id="settings"><i class="icon-wrench"></i><i class="icon-chevron-right"></i>Settings</a></li>
					<li class="active"><a href="javascript:{}" onclick="getInitialUpdates();" id="latestUpdates" class="whatsnew"><i class="icon-fire"></i><i class="icon-chevron-right"></i> Whats's New?</a></li>
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
					<li><a href="javascript:{}" id="FavouriteLinks"><i class="icon-heart"></i><i class="icon-chevron-right"></i> Favourite Links</a></li>
					
					</ul>
				</div>
				
				</div>
			</div>
		<!--------left div End ------->

		<!--------Center div------>
			<div class="span6 centerSpan6">
				<div class="widget green" id="MyProfileActions">
				        <div id="subscriptionsStreamingMain">
				           <div id="subscriptionsStreamingData">

				           </div>
				           <div id="subscriptionsStreamingMoreDiv"><input type="button" value="More" id="subscriptionsStreamingMore" class="btn subscrStreamingMoreCls"/><img src="images/icons/ajaxImg.gif"  style="width:20px;padding-left:150px;display:none;" id="subscriptionsStreamingAjaxImg"></img></div>
				        </div>
				
				        <div id="fLinks"></div>
				        <div id="headerDiv" class="whitegloss"></div>
						<div class="placeholderCenterDiv"></div>
						
						<div id="subscriptionsDiv">
							<div id="userSpecialPageSubscriptionsDiv" class="subscriptionInnerDiv"></div>
							<div id="userSpecialPageUnSubscriptionsDiv" class="subscriptionInnerDiv"></div>

							<div id="userCandidateSubscriptionsDiv" class="subscriptionInnerDiv"></div>
							<div id="userPartySubscriptionsDiv" class="subscriptionInnerDiv"></div>
							<div id="userConstituencySubscriptionsDiv" class="subscriptionInnerDiv"></div>
						</div>
	<!--PRASAD-->
		<div id="impdatesDiv" style="display:none">
			<div id="cadreDatesYUICalDiv" class="yui-skin-sam"></div>
			<div id="cadreEventsDetailsDivMain">
				<span class="impInfoSpan"> <img height="10" width="10" src="<%=request.getContextPath()%>/images/icons/bluebox.png"/> - Only Important Dates </span>
			</div>	
			<div id="cadreEventsDatesMain">
				<table width="100%">
					<tr>
						<td width="50%">
							<div id="cadreImpDatesDiv">
								<div id="cadreImpDatesHeadDiv">
									<span style="float: left;">Important Dates</span>
									<span id="newEventSpan"><a href="javascript:{}" onclick="buildNewImpDatePopup()" style="color:#669900;">Create New Date</a></span>							
								</div>
							<div id="cadreImpDatesBodyDiv"> </div>
							</div>
						</td>
					
					</tr>
				</table>
			</div>
			<div id="cadreManagementMainDiv" class="yui-skin-sam">		
				<div id="myDialog" class="yui-skin-sam"> 			
				</div> 
			</div>
				
	<!--PRASAD-->
						</div>
						<div class="FavoriteLinksDiv">
							<div class="constituencyDivMain">
								<div style="background:#2D6987;" class="favouriteLinksHeading constituencyDivheading constituencyHeadingCls"></div>
								<div class="constituencyDivInnerFav"></div>
							</div>
							<div class="stateDivMain">
								<div style="background:#2D6987;" class="favouriteLinksHeading stateDivheading stateHeadingCls" ></div>
						        <div class="stateDivInnerFav"></div>
							</div>

							<div class="districtDivMain">
								<div style="background:#2D6987;" class="favouriteLinksHeading districtDivheading districtHeadingCls" ></div>
						        <div class="districtDivInnerFav"></div>
							</div>

							<div class="specialPageDivMain">
								<div style="background:#2D6987;" class="favouriteLinksHeading specialPageDivheading specialPageHeadingCls" ></div>
						        <div class="specialPageDivInnerFav"></div>
							</div>

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
						<span><i class="icon-info-sign" id="icon_leftsec"></i><span>People You May Know</span>
					</h4>
					
					<div class="peopleYouMayKnowMainDiv">
						<ul class="peopleYouMayKnowULClass"></ul>
					</div>
					
					<div class="peopleYouMayKnowInnerDiv">
					</div>
						
					 <!-- <c:if test="${not empty dataTransferVO.peopleYouMayKnow}">
					<c:forEach var="connectedPeoples" items="${dataTransferVO.peopleYouMayKnow}" begin="0" end="2">

						<li class="connectPeopleDiv">
							<div class="span3">
								<a href="userProfile.action?profileId=${connectedPeoples.id}" class="thumbnail">
								 <c:if test="${connectedPeoples.image != null && connectedPeoples.image !=''}">
									<img height="50" width="55" src="/pictures/profiles/${connectedPeoples.image}" />-->
									<!--<img height="50" width="55" src="/PartyAnalyst/images/icons/indexPage/human.jpg" /> -->
								<!-- </c:if>
								<c:if test="${connectedPeoples.image == null || connectedPeoples.image == ''}">
									<img height="50" width="55" src="/images/icons/indexPage/human.jpg" />
								</c:if>
								</a>
							</div>
							<div class="span9">
								<a href="userProfile.action?profileId=${connectedPeoples.id}"><h6>${connectedPeoples.candidateName}</h6></a>
								<i>${fn:toLowerCase(connectedPeoples.constituencyName)}
								</i>
									<div class="pull-right">
									<span>
										<a rel="tooltip" href="javascript:{}" class="connectLink" title="Connect"><i class="icon-plus-sign opacityFilter-50"></i></a>
									</span>
									<span>
																				
										<a rel="tooltip" href="javascript:{}" title="Send A Message" onclick="showMailPopup('${connectedPeoples.id}',' ${connectedPeoples.candidateName}','Message')"><i class="icon-envelope opacityFilter-50"></i></a>
									</span>
									<input type="hidden" value="${connectedPeoples.id}" class="userId" />
									<input type="hidden" value="${connectedPeoples.candidateName}" class="userName" />
									<input type="hidden" value="${connectedPeoples.constituencyName}" class="constituencyName" />

									</div>
							</div>
						
						
						</li>
						
						</c:forEach>
						</c:if>
						</ul>-->

						<!-- <c:if test="${empty dataTransferVO.peopleYouMayKnow}">
							<div class="peopleYouMayKnowInnerDiv">
							Right now there are no friend suggestion for you.	We will get back with more suggesstions as soon as possible..
							</div>
						</c:if>-->
				
						
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
								
								<a href="specialPageAction.action?specialPageId=${specialPages.specialPageId}"><img src="${specialPages.eventImagePath}" width="200px"height="85px" style="margin-bottom:12px; " alt="${specialPages.title} Image" /></a>
								<p class="hottopics_desc">
									${specialPages.description}
								</p>
						</div>
						<a href="specialPageAction.action?specialPageId=${specialPages.specialPageId}" class="btn-info btn-small pull-right" title="${specialPages.title}">View Now</a>
					</div>
						</c:forEach>
						<a id="specialPageLink" href="javascript:{}" class="btn btn-primary" >View More Special Pages</a>						
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

<div id="userSettingsDialog"></div>

<div id="connectPeoplePopup" style="display:none;">
	<div id="allConnectedUsersDisplay_main"></div>
	</div>


<!-- Templates -->
<div class="TemplateCollection">

	<div class="politicalReasonsTemplate templateholder subscriptionsMain widget-simple" style="margin:5px 0px;width:92%;">
		<div class="politicalReasonsInnerDiv">
			<div class="span2 postedBy">
    				
    		   </div>
			<div class="span10">
				<span class="headingCls span12">
					<h6><span class="headingCls"></span></h6>
				</span>
				
				<div class="subscriptionsImgTitleDescDiv">
    					  <span class="candidateImg span4"></span>
    					  
    					 <span class="subscriptionsDescDiv span8">
    						  <span class="activity-title">
								<p><span class="politicalReaCls"/></p>
							  </span>
    						  <span>
    						   <p><span class="politicalDescCls"/></p>
    						  </span>
    					  </span>
				</div>
				<span class="pull-right date-span">
					<h6><span class="polReaPostedDate"></span></h6>
				</span>
				
				<!--<span class="candidateImg"></span>
				<div class="politicalReasons">
					
					<p class="politicalReaCls"></p>
					<p class="politicalDescCls"></p>
					<!--
					<span class="postedBy"></span>
					<p class="polReaPostedDate span3"></p>
					<p class="polReaPostedPerName"></p>
				</div>-->
			</div>
		</div>
	</div>	
	
	


    
     <div class="subscriptionsMainTemplate subscriptionsMain">
    	   <div class="subscriptionsHeaderTitleDiv">
    		   <div class="span2 thumbnail">
    				<img src="images/new_homepage/logo.png" style="width:50px;height:50px;vertical-align:bottom;" class="img-rounded"/>
    		   </div>
    		   <div class="span10">
    				   <span class="span9 activity-name">
    						<h6><span class="subscriptionsHeaderTitle"/></h6>
    				   </span>
    					<span class="span3 pull-right date-span">
    						<span class="subscriptionsDateDiff"></span>
    					</span>
    	   
    	   
    				   <div class="subscriptionsImgTitleDescDiv">
    					  <span class="subscriptionsFileImgDiv span4">
    						 
    					  </span>
    					  
    					 <span class="subscriptionsDescDiv span8">
    						  <span class="activity-title">
    						 
    						  </span>
    						  <span>
    						   <p><span class="subscriptionsDescription"/></p>
    						  </span>
    					  </span>
    				   </div>
    	   </div>
       </div>
     </div>
    
	<div class="templateDiv templateholder templatePersons">
			
			<div class="imgClass span3 thumbnail"></div>
			<div class="prinfo span9">
				<h6 class="connectedPersonName"></h6>
				<span class="messageCls" style="display:none"></span>
				<span class="constituencyName"></span>
				<span class="districtName"></span>
				<span class="stateName"></span>
				<div class="icon-groups">
					<span class="sendMsg"></span>
					<span class="connectCls"></span>
					<span class="blockPersonBtn" style="display:none;"></span>
				</div>
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

	<!--<div class="problemTemplateDiv templateholder problemsShowDiv">
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
	</div>-->
	
	<div class="problemTemplateDiv templateholder" >

		<div class="problemsShowDIV span12 widget-simple">
			<div class="span2 problemImg"></div>
			<span class="postedPersonName"></span>
			<div class="span10" style="background:#f1f1f1;padding:7px;">
				<div class="ProblemImgTitleDescDiv">
    					 <span class="subscriptionsDescDiv span12">
    						  <span class="activity-title">
								<p><span class="problemTitle"/></p>
							  </span>
    						  <span>
    						   <p><span class="problemDescription"/></p>
    						  </span>
    					  </span>
				</div>
				<span class="pull-right date-span">
					<h6><span class="problemFromDate pull-right"></span></h6>
					<h6><span class="location pull-left" style="margin-bottom:20px;color:#273241;"></span></h6>
					<h6><span class="problemReportedDate pull-right"></span></h6>
					
				</span>
				<div id='prblmCommentShare' >
					<span class="commentCls pull-right btn btn-mini"></span>
					<span class="shareCls pull-left btn btn-mini"></span>
				</div>
			</div>
		</div>
	</div>	
	
		
	


<div class="favouriteLinkConstituencyClass favouriteLinkDivClass" >
	<span class="imageClass"></span>
	<span class="titleClass"></span>
	<span class="removeClass"></span>

</div>

<!-- <div class="favouriteLinkStateClass favouriteLinkDivClass" >
	<span class="imageClass"></span>
	<span class="titleClass"></span>
	<span class="removeClass"></span>

</div>

<div class="favouriteLinkDistrictClass favouriteLinkDivClass" >
	<span class="imageClass"></span>
	<span class="titleClass"></span>
	<span class="removeClass"></span>

</div>-->


	<div class="peopleYouMayKnowTemplate">
	  <ul>
		<li class="connectPeopleDiv connectPeopleTemplateDiv">
			<div class="span3 imageDIv"></div>
				<div class="span9">
					<h6 class="nameCls"></h6>
						<i class="constituencyNameCls"></i>
							<div class="pull-right">
								<span class="connectLinkCls"></span>
								<span class="sendMsgClass"></span>
								<!-- <span class="hiddenVarspan"></span>-->
								<span class="userIdhiddenVar"></span>
								<span class="usernamehiddenVar"></span>
								<span class="constituencyNamehiddenVar"></span>
							</div>
				</div>

		  </li>
		</ul>

	</div>

</div>


<!-- Templated END -->

	

<script type="text/javascript">

getInitialUpdates();

districtId = '${dataTransferVO.districtId}';
districtName = '${dataTransferVO.districtName}';
constituencyId = '${dataTransferVO.constituencyId}';
constituencyName = '${dataTransferVO.constituencyName}';
parliamentConstId =	'${dataTransferVO.parliamentConstId}';
parliamentConstName = '${dataTransferVO.parliamentConstName}';

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
var impDates = new Array();
		<c:forEach var="impDate" items="${cadreManagementVO.userImpDates}" >			
				var ob =
					{
						importantDateId:"${impDate.importantDateId}",
						title:"${impDate.title}",
						impDate:"${impDate.impDate}",
						importance:"${impDate.importance}",
						eventType:"${impDate.eventType}"
					};
					impDates.push(ob);
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
		
			$('body').tooltip({
  			 selector: '[rel=tooltip]'
			});
	
	});	
	
	
	
	
	


	function getUserSettingsDetails(){

	var jsObj=
			{				
				task:"getSettings"
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "updateUserSettingsDetailsAction.action?"+rparam;						
		callAjax(jsObj,url);

	var url = "getTotalSettingsOptionsOfAnUser.action";
	callAjaxForUserSettings(jsObj,url);
}




function buildSettingsDetails(results){

$('#userSettingsDialog').html('');

	var str='';

	str+='<div style="width:285px;">';

	//str+='<label>Profile Settings:</label><br>';

	if(results.selectedOptionId == 0 || results.selectedOptionId == 2)
	  str+='<label><input name="profile" style="margin:0px;" checked="true" type="radio" value="2"/><b>Private View</b></label>';
	else
	  str+='<label><input name="profile" style="margin:0px;" type="radio" value="2"/><b>Private View</b></label>';

    if(results.selectedOptionId == 1)
	  str+='<label><input name="profile" style="margin:0px;" checked="true" type="radio" value="1"/><b>Public View</b></label>';
	else
	  str+='<label><input name="profile" style="margin:0px;" type="radio" value="1"/><b>Public View</b></label>';
	
	if(results.selectedOptionId == 3)
	  str+='<label><input name="profile"  style="margin:0px;" checked="true"type="radio" value="3"/><b>Friends View</b></label>';
	else
	  str+='<label><input name="profile" style="margin:0px;" type="radio" value="3"/><b>Friends View</b></label>';

    str+='<div style="margin-top:10px;">';
	str+='<input  type="button"   class="btn btn-success" value="Update" onClick="updateSelectedOption();"/>';

	str+='<input  style="margin:10px;" type="button" class="btn btn-success" value="Cancel" onClick="closeDialog();"/>';
	str+='</div>';


	str+='</div>';
	str+='<div id="successMsg"></div>';

	$('#userSettingsDialog').html(str);

	$('#userSettingsDialog').dialog({
        title:'Update Profile View',
		hide:'explode'
	});

	
}

function updateSelectedOption(){

var selectedValue = $('input[name=profile]:radio:checked').val();

var jsObj=
			{					
				selectedValue:selectedValue,				
				task:"updateSettings"
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "updateUserSettingsDetailsAction.action?"+rparam;						
		callAjaxForUserSettings(jsObj,url);

}

function callAjaxForUserSettings(jsObj,url)
{

	 var myResults;

	 var callback = {			
	   success : function( o ) {
			try {												
				myResults = YAHOO.lang.JSON.parse(o.responseText);	

                     if(jsObj.task == "getSettings")								
						buildSettingsDetails(myResults);
					 else if(jsObj.task == "updateSettings"){

						 var cssObj = {    
					        'font-weight' : 'bold',
					        'color' : 'green'
				          }
					 		 $('#successMsg').text("Updated Successfully.").css(cssObj).show().delay(2000).fadeOut(400);

							 setTimeout(closeDialog,2000 );
					 }
										
											
			}catch (e) { 
				alert("Invalid JSON result" + e);   
			}  
	   },
	   scope : this,
	   failure : function( o ) {
					
		}
	 };

YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

function closeDialog(){

$('#userSettingsDialog').dialog('close');
}

getPeopleYouMayKnowDetails();
</script>
</body>
</html>