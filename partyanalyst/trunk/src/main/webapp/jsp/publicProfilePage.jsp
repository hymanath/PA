<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile Page</title>

<script type="text/javascript" src="js/publicProfile/publicProfilePage.js"> </script>
<script type='text/javascript' src="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.0.1/bootstrap.min.js"></script>
<script type="text/javascript" src="js/customPaginator/customPaginator.js"></script>
<script type="text/javascript" src="js/opinionpoll/opinionpoll.js"> </script>

<link type="text/css" href="styles/publicProfile/publicProfilePage.css" rel="stylesheet" />
<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
<link type="text/css" href="styles/userProfile/userProfilePage.css" rel="stylesheet" />
<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet" />
<style>
#userProfileImg{
cursor: auto; 
width: 80px;
height: 80px;
}
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

#contenttable > .container > .row-fluid > .span8 {margin-left:10px;}
#contenttable > .container > .row-fluid > .span4 {margin-left:10px;width:245px;}
.container {
    width: 960px;
}


/* profile Style */


.profileUserName{ font-size: 17px;font-weight: bold;}
.fontStyle{font-size:14px;}
.publicProfileInnerDiv{background:#fafafa}
.problemShowDiv{ background: #FFFFFF;border: 1px solid #CDCDCD;margin-top: 15px;padding:10px;margin-bottom:10px;}
.problemTitle{color: -moz-menuhover;font-size: 16px;font-weight: bold;}
.friendsImgs{border: 1px solid #CDCDCD;margin-top: 10px; margin-right: 6px; background:  #00FFFF;}
.imgLi{clear: both;
     display: inline-block;
    
    width: 80px; vertical-align: top;margin-bottom: 20px;}
	.TemplateCollection{display:none;}
	
	 .placeholderCenterDiv{
	background: none repeat scroll 0 0 #FFFFFF;
    border: 1px solid #CDCDCD;
    margin-bottom: 10px;
    margin-top: 15px;
    padding: 5px;
	display:inline-block;
	margin-left: 0;
    margin-right: 0;
	}
	
	.templatePersons{width:;height:70px;}
	 .opacityFilter-50{opacity: 0.65;}
	 .politicalReasonsTemplate{display: table;}

	 .requestsDivCls span {
    clear: none;
    color: #999999;
   
    float: left;
    font-size: 12px;
    margin: 0 2px;
	  display: table;
}

.requestsDivCls {
    clear: both;
    color: #999999;
    display: inline-block;
    float: right;
    font-size: 12px;
    width: auto;
}
.subscriptionsImgTitleDescDiv {width:100%;background:#fafafa;display:inline-block;padding:5px;margin:5px;}

/*.frndName{ padding: 18px;
    width: auto;
   margin-top: -50px;
   display:block;
   position:relative;
}
.frndImg {
 width: auto;
 padding: 2px;
}
.friendListTemplate1{
 display: -moz-inline-box;
}*/
.friendListTemplate1 {
    display: -moz-grid-line;
}

.frndName{ 
    width: auto;
   margin-top: 0px;
   display:block;
   position:relative;
}
.frndImg {
 width: auto;
}

</style>
</head>
<body>
  <div class="container m-top15">
	<div class="row">
		<div class="publicProfileInnerDiv">
			<div class="span8">
			
				<div class="span2" style="width: 90px;">
				<c:if test="${loginUserProfilePic == '' || loginUserProfilePic == null}">
					<img class="thumbnail"  src="pictures/profiles/human.jpg"  id="userProfileImg">
				</c:if>
				<c:if test="${loginUserProfilePic !='' && loginUserProfilePic != null}">
				 <img class="thumbnail"  src="pictures/profiles/${loginUserProfilePic}"  id="userProfileImg">
				</c:if>
				</div>
				<div class="span7" style="width: 550px;">
					<span class="profileUserName row"  style="text-transform: capitalize; font-family: sans-serif;">${profileUserName}<br></span>
					<span class="fontStyle"><a href="constituencyPageAction.action?districtId=${dataTransferVO.districtId}&constituencyId=${dataTransferVO.constituencyId}">${dataTransferVO.constituencyName}</a>&nbsp;&nbsp;&nbsp;
					<a href="districtPageAction.action?districtId=${dataTransferVO.districtId}&districtName=${dataTransferVO.districtName}">${dataTransferVO.districtName}</a>&nbsp;&nbsp;&nbsp;
					<a href="statePageAction.action?stateId=${dataTransferVO.stateId}">${dataTransferVO.stateName}</a></span>

					<c:if test='${logInStatus == false}'>
						<span class="pull-right">
							<a href="javascript:{}" onclick="openDialogForLoginWindow()"><i class="icon-plus-sign opacityFilter-50 fontStyle"></i> Connect </a>
							<a href="javascript:{}" onclick="openDialogForLoginWindow()"><i class="icon-envelope opacityFilter-50 fontStyle"></i> Send Message</a>
						</span>
					</c:if>


					<c:if test='${logInStatus == true && userType == "OtherUser"}'>
						<span class="pull-right">
							
							<c:if test="${connectStatus == 'NOT CONNECTED'}">
								<span class="connectStatusSpan"><a href="javascript:{}" class="connectLinkInPP"><i class="icon-plus-sign opacityFilter-50 fontStyle"></i> Connect </a></span>
							</c:if>
							<c:if test="${connectStatus == 'PENDING'}">
								<span class="connectStatusSpan"><a class="btn-mini" href="javascript:{}" rel="tooltip" title="Pending" style="font-size:15px"><i class="icon-adjust opacityFilter-50"></i> Request Pending</a></span>

							</c:if>
							<c:if test="${connectStatus == 'Respond to Friend'}">
								<div class="icon-groups requestsDivCls">
									<span class="sendMsg">
										<a rel="tooltip" class="acceptButton btn btn-mini acceptRequestLink" data-original-title="Confirm"><i class="icon-thumbs-up opacityFilter-50"></i></a>
									</span>
									<span class="connectCls">
										<a rel="tooltip" class="rejectButton btn btn-mini rejectRequestLink" data-original-title="Reject"><i class="icon-thumbs-down opacityFilter-50"></i></a>
									</span>
									<span style="display: block;" class="blockPersonBtn">
										<a title="Block This Person" rel="tooltip" class="rejectButton btn btn-mini blockButtonLink"><i class="icon-ban-circle opacityFilter-50"></i></a>
									</span>
							</div>
							</c:if>

							<a href="javascript:{}" class="sendMessageLinkInPP"><i class="icon-envelope opacityFilter-50 fontStyle"></i> Send Message</a>
						</span>
					
					</c:if>
					
					
					<!--<a class='btn btn-success btn-mini pull-right' href="userProfile.action">My Profile</a>-->
				</div>
			
				
				
				<div class="span8 placeholderCenterDiv" >
				
				</div>
				
				<div class="row-fluid">
				
					<!--<div id="problemsDiv" class="">
					
					<c:if test="${not empty problems.problemBeanVOList}">

						<c:forEach var="problems" items="${problemList}">
							<div class="problemShowDiv">
								${problems.postDate}
							<c:forEach var="problemData" items="${problems.problemBeanVOList}">
								 <div class="problemDetails">
								<p class="problemTitle"><a href="completeProblemDetailsAction.action?problemId=${problemData.problemId}">${problemData.problem}</a></p>
								<p class="problemDescription">${problemData.description}</p>
								<p><span>Existing From: </span>${problemData.existingFrom}</p>
								
							 </div>
							</c:forEach>
							</div>
				 
						</c:forEach>
					</c:if>

					<c:if test="${empty problems.problemBeanVOList}">

					<c:forEach var="problems" items="${problemBeanVOList}">
						<div class="problemShowDiv">
						${problems.postDate}
						<c:forEach var="problemData" items="${problems.problemBeanVOList}">
						 <div class="problemDetails">
							<p class="problemTitle"><a href="completeProblemDetailsAction.action?problemId=${problemData.problemId}">${problemData.problem}</a></p>
							<p class="problemDescription">${problemData.description}</p>
							<p><span>Existing From: </span>${problemData.existingFrom}</p>
						 </div>
						</c:forEach>
						</div>
				 
					  </c:forEach>
					</c:if>

					</div>-->
				</div>
			</div>
			<div class="span4 ">
			<div class="friendsDiv widget blue ">
			<h2>
				<span><i class="icon-fire" id="icon_leftsec"></i></span>
					Friends 
					<c:if test="${ !empty registrationVOList }">
					<span style="font-size: 11px;margin-left: 120px;text-transform: none;cursor:pointer;" class="friendsInPP">See All</span>
					 </c:if>
					</h2>
					<div class=""style="margin-left: -20px;">
					
					<ul>
					<c:if test="${ empty registrationVOList }">
                 <li style="color:#0088CC">There are No Friends for <br><b style="text-transform: capitalize;text-align: justify;">${profileUserName}</b></li>
                    </c:if>
				<c:forEach var="friendsDetails" items="${registrationVOList}" begin="0" end="5">

					<li class="imgLi">
	
						<a href="userProfile.action?profileId=${friendsDetails.registrationID}">
								
						<c:if test="${friendsDetails.userProfilePic != null && friendsDetails.userProfilePic !=''}">
									<!--<img height="50" width="55" src="/pictures/profiles/${friendsDetails.userProfilePic}" style="clear:both;display:block;" class="thumbnail"/>-->
									<img height="50" width="55" src="images/icons/indexPage/human.jpg"> </img>
								</c:if>
						</a>
													
						<c:if test="${friendsDetails.userProfilePic == null || friendsDetails.userProfilePic == ''}">
								<img height="50" width="55" src="/images/icons/indexPage/human.jpg" style="clear:both;display:block;" class="thumbnail"/>
						</c:if>								
						<span><a href="userProfile.action?profileId=${friendsDetails.registrationID}"> <b style="font-size: 13px; text-transform: capitalize;">${friendsDetails.firstName}&nbsp; ${friendsDetails.lastName}</b></a></span>
					</li>
					
				</c:forEach>
			
				</ul>
				</div>
				
			</div>
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
			<!-- People You May Know Div End -->

				<!-- special Pages-->
				<div class="widget red hottopics">
					<h2>
					<span><i class="icon-fire" id="icon_leftsec"></i></span>
					You Might Like</h2>
				
					<c:forEach var="specialPages" items="${specialPageVOList}">
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

						 <a href="specialPageInfoAction.action" class="btn btn-primary" >View More Special Pages</a>
						<!-- <a href="javascript:{}" class="btn btn-primary" id="specialPageLink">View More Special Pages</a> -->	
				</div>
				<!-- end special Pages -->
				
			
			</div>
	
	</div>

	</div>
	
  </div>
	
<div class="TemplateCollection">

	<div class="friendListTemplate templateholder templatePersons">

	<div class="friendListTemplate1">
	
		<div class="frndImg thumbnail pull-left"></div>
		<h6  class="frndName span2 pull-right" ></h6>
</div>
	</div>
	
	<div class="problemTemplate templateholder row-fluid" >

		<div class="problemsShowDIV span12 widget-simple">
			<div class="problemImg"></div>
			<span class="postedPersonName"></span>
			<div class="span10" style="background:#f1f1f1;padding:7px;">
				<div class="ProblemImgTitleDescDiv">
    					 <span class="subscriptionsDescDiv">
    						  <span class="activity-title">
								<p><span class="problemTitle"/></p>
							  </span>
    						  <span>
								<span class="problemDescription"/>
    						  </span>
    					  </span>
				</div>
				<span class="pull-right date-span">
					<h6><span class="problemFromDate pull-right"></span></h6>
					<h6><span class="location pull-left" style="margin-bottom:20px;color:#273241;"></span></h6>
					<h6><span class="problemReportedDate pull-right"></span></h6>
					
				</span>
				<!-- <div id='prblmCommentShare' >
					<span class="commentCls pull-right btn btn-mini"></span>
					<span class="shareCls pull-left btn btn-mini"></span>
				</div> -->

			</div>
		</div>
	</div>
	



<div class="politicalReasonsTemplate templateholder subscriptionsMain" style="margin:5px 0px;width:92%;">
		<div class="politicalReasonsInnerDiv row-fluid widget-simple">
			<div class="postedBy"></div>
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
	

<div id="connectPeoplePopup" style="display:none;">
	<div id="allConnectedUsersDisplay_main"></div>
	</div>

</div>
<script>

var profileId = '${profileId}';
var profileUserName = '${profileUserName}';

districtId = '${dataTransferVO.districtId}';
districtName = '${dataTransferVO.districtName}';
constituencyId = '${dataTransferVO.constituencyId}';
constituencyName = '${dataTransferVO.constituencyName}';
var loginUserId = '${loginUserId}';

loginUserName = '${loginUserName}';	
loginUserProfilePic = '${loginUserProfilePic}';	
connetLocationId = constituencyId;
userType = '${UserType}';

buildPolls();

 <c:forEach var="friendsDtls" items="${registrationVOList}" >
		var obj = {
					 id:'${friendsDtls.registrationID}',
					 profilePic:'${friendsDtls.userProfilePic}',
					 firstName:'${friendsDtls.firstName}',
					 lastName:'${friendsDtls.lastName}',
					};
				friendsInPP.push(obj);
  </c:forEach>

  executeOnload();

</script>
</body>
</html>