<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile Page</title>

<script type="text/javascript" src="js/homePage/newHomePage.js"> </script>
<script type="text/javascript" src="js/userProfile/userProfilePage.js"> </script>

<link type="text/css" href="styles/userProfile/userProfilePage.css" rel="stylesheet" />
<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />

</head>
<body>
<div id="profilePageMainDiv" style="">

 <div id="profilePageInnerDiv">	

	<div class="container m-top15">
		<div class="row-fluid">
			
		<!--------left div------->
			<div class="span3">

				<div class="widget blue votersguide p-m-bottom0px">
			        <div style="background: #F5F5F5; text-align: center; padding: 10px 0px;">         
						<img width="90" height="100" src="pictures/profiles/human.jpg" style="border:1px solid #ADADAD;" id="userProfileImg">
						<div id="profileUserName">${loginUserName}</div>
						<div>${dataTransferVO.constituencyName}</div>
						<div>${dataTransferVO.districtName}</div>
						<div style="margin-top: 10px;">${dataTransferVO.stateName}</div>
					</div>			
				</div>
				<div class="widget-block">
					<p class="userMenu"><span>User Menu</span></p>
					<p class="p1"><span>Whats's New?</span></p>
					<p class="p1"><a href="javascript:{}" id="messagesLink">Messages</a></p>
					<p class="p1"><a href="javascript:{}" id="friendsLink">Friends</a></span></p>
					<p class="p1"><a href="javascript:{}" id="requestLink">Requests</a></p>
				</div>
				
				<div class="widget-block">
					<p class="applicationCls"><span>Applications</span></p>
					<p class="p2"><span>Important Dates</span></p>
					<p class="p2"><span><a href="javascript:{}" id="problemsLink">Problems</a></span></p>
					
				</div>
				
				<div class="widget-block">
					<p class="interestsCls"><span>Interests</span></p>
					<p class="p3"><a href="javascript:{}">Subscriptions</a></p>
					<p class="p3"><a href="javascript:{}">Asses Politician</a></p>
					<p class="p3"><a href="javascript:{}">Special Pages</a></p>
					
				</div>
				
			</div>
		<!--------left div End ------->

		<!--------Center div------>
			<div class="span6">
				<div class="widget green">
				<div id="headerDiv"></div>
					<div class="placeholderCenterDiv"></div>	  
				</div>

			</div>
		<!--------Center div------>


		<!--------Right div------>
			<div class="span3">
		  <!-- Profile Statistics Div Start -->
			<div class="widget-block">
					<p class="userMenu" style="text-align:right;"><span>Your Profile Statistics</span></p>
					<div class="profileStatisticsDiv">
					<ul>
						<li>Friends: </li>
						<li>Photos: </li>
						<li>Videos: </li>
						<li>Subscriptions: </li>
						<li>Connected Pages: </li>
						<li>Politiciaa Assess: </li>
						<li>Problems Posted: </li>
						<li>Messages: </li>
						
					</ul>
					</div>
				</div>
			<!-- Profile Statistics Div End -->
			
			<!-- People You May Know Div start -->
				<div class="widget-block">
					<p class="connectPeopleHeading"><span>People You May Know</span></p>
					<div class="connectPeopleDiv" >
					<img src=""></img>
					<span>New MemberName</span>
					<p><span>connect</span><span>Send Message</span></p>
					</div>
					<div class="connectPeopleDiv">
					<img src=""></img>
					<span>New MemberName</span>
					<p><span>connect</span><span>Send Message</span></p>
					</div>
					<div class="connectPeopleDiv">
					<img src=""></img>
					<span>New MemberName</span>
					<p><span>connect</span><span>Send Message</span></p>
					</div>
				</div>
			<!-- People You May Know Div End -->

			<!-- opinionPollDiv Start -->
				<div class="widget-block">
				
				<div id="pollsWidgetBody"></div>
				
				</div>
			<!-- opinionPollDiv End -->

				<!-- special Pages-->
				
				<div class="widget-block specialPagesDiv">
				<p class="specialPageHeading">You Might Like
				<br><span style="margin-left: 151px; font-size: 12px;"><a href="specialPageInfoAction.action">See All</a></span></p>
				
				<c:forEach var="specialPages" items="${specialPageVOList}">
					<div class="specialPageCls">
							<h5>${specialPages.title}</h5>
								
								<img src="${specialPages.eventImagePath}" width="100px"height="85px" style="margin-bottom:12px;"/>
								
									${specialPages.description}
						
						<div>		
						
						<a href="specialPageAction.action?specialPageId=${specialPages.specialPageId}" class="btn-info btn-small pull-right" title="${specialPages.title}">View Now</a>
						</div>
					</div>
						</c:forEach>
						
				
				</div>

				<!-- end special Pages -->

			</div>
		<!--------Right div Close------>
			
		</div>
	</div>
 </div>
</div>
<!-- <div id="connectPeoplePopup"></div>-->
<div id="connectPeoplePopup" style="display:none;"><div id="allConnectedUsersDisplay_main"><img src="images/icons/barloader.gif"/></div></div>
	</div>

<!-- Templates -->

	<div class="templateDiv templateholder">
						
		<p class="connectedPersonName"></p>
		<p class="imgClass"></p>
		<span class="constituencyName"></span>
		<span class="districtName"></span>
		<span class="stateName"></span>
		<div><span class="sendMsg"></span></div>
	</div>
<!-- Templated END -->


	<!-- Dialog Box -->
	<div class="dialogTemplate">
		<div>
		<span>Message</span>
		 <textarea rows="4" cols="50">
		 </textarea>
		 <input type="button" value="send" />
		</div>
	</div>
	<!-- End -->
	


<script>
buildPolls();
</script>
</body>
</html>