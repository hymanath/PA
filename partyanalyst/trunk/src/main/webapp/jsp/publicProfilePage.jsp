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
.imageClass{width:300px;}
.profilerDetails{width:300px;}
.profileUserName{ font-size: 17px;font-weight: bold;}
.fontStyle{font-size:14px;}
.publicProfileInnerDiv{background:#fafafa}
.problemShowDiv{ background: #FFFFFF;border: 1px solid #CDCDCD;margin-top: 15px;padding:10px;margin-bottom:10px;}
.problemTitle{color: -moz-menuhover;font-size: 16px;font-weight: bold;}
.friendsImgs{border: 1px solid #CDCDCD;margin-top: 10px; margin-right: 6px; background:  #00FFFF;}
.imgLi{clear: both;
    display: inline-block;;
    
    width: 85px;}
	.TemplateCollection{display:none;}
	
	.placeholderCenterDiv{
	background: none repeat scroll 0 0 #FFFFFF;
    border: 1px solid #CDCDCD;
    margin-bottom: 10px;
    margin-top: 15px;
    padding: 10px;
	display:inline-block;
	}
	
	.templatePersons{width:29%;height:70px;}
</style>
</head>
<body>
  <div class="container m-top15">
	<div class="row-fluid">
		<div class="publicProfileInnerDiv">
			<div class="span8">
				<div class="imageClass">
				<c:if test="${loginUserProfilePic == '' || loginUserProfilePic == null}">
					<img width="90" height="100" src="pictures/profiles/human.jpg" style="border:1px solid #ADADAD;" id="userProfileImg">
				</c:if>
				<c:if test="${loginUserProfilePic !='' && loginUserProfilePic != null}">
				 <img width="140" height="100" src="pictures/profiles/${loginUserProfilePic}" style="border:1px solid #ADADAD;" id="userProfileImg">
				</c:if>
				</div>
				<div class="profilerDetails">
					<span class="profileUserName">${profileUserName}</span><br>
					<span class="fontStyle"><a href="constituencyPageAction.action?districtId=${dataTransferVO.districtId}&constituencyId=${dataTransferVO.constituencyId}">${dataTransferVO.constituencyName}</a></span>&nbsp;&nbsp;&nbsp;
					<span class="fontStyle"><a href="districtPageAction.action?districtId=${dataTransferVO.districtId}&districtName=${dataTransferVO.districtName}">${dataTransferVO.districtName}</a></span>&nbsp;&nbsp;&nbsp;
					<span class="fontStyle"><a href="statePageAction.action?stateId=${dataTransferVO.stateId}">${dataTransferVO.stateName}</a></span>
					<!--<a class='btn btn-success btn-mini pull-right' href="/PartyAnalyst/userProfile.action">My Profile</a>-->
				</div>
				
				<div class="placeholderCenterDiv" style="display:none;"></div>
				
				<div id="problemsDiv">
					
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

				</div>
			</div>
			<div class="span4">
			<div class="friendsDiv widget blue">
			<h2>
				<span><i class="icon-fire" id="icon_leftsec"></i></span>
					Friends 
					<span style="font-size: 11px;margin-left: 120px;text-transform: none;cursor:pointer;" class="friendsInPP">See All</span>
					</h2>
					<div>
					<ul>
				<c:forEach var="friendsDetails" items="${registrationVOList}" begin="0" end="5">

					<li class="imgLi">
						<a href="userProfile.action?profileId=${friendsDetails.registrationID}"></a>
						<c:if test="${friendsDetails.userProfilePic != null && friendsDetails.userProfilePic !=''}">
									<img height="50" width="55" src="/pictures/profiles/${friendsDetails.userProfilePic}" style="clear:both;display:block;" class="thumbnail"/>
									<!--<img height="50" width="55" src="/PartyAnalyst/images/icons/indexPage/human.jpg" /> -->
								</c:if>
						<c:if test="${friendsDetails.userProfilePic == null || friendsDetails.userProfilePic == ''}">
								<img height="50" width="55" src="/images/icons/indexPage/human.jpg" style="clear:both;display:block;" class="thumbnail"/>
						</c:if>
						
						<span>${friendsDetails.firstName} ${friendsDetails.lastName}</span>
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
	<div class="frndImg thumbnail span3" ></div>
	<span class="frndName span12"></span>
</div>
</div>
<script>

var profileId = '${profileId}';

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
</script>
</body>
</html>