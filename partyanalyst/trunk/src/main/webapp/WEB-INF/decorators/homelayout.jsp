<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>


<html xmlns="http://www.w3.org/1999/xhtml">


<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
	<title><decorator:title default="Party Analyst"/></title>
	<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/faviIcon.jpg">
	<!--Bootstrap styles file-->
	<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet">
	<link type="text/css" href="styles/menu.css" rel="stylesheet" />
	<link href="styles/newhome_styles.css" rel="stylesheet" type="text/css" />
	

	<!--Script file
	<script type="text/javascript" src="js/jquery.js"></script>-->
	<script type="text/javascript" src="js/loginpopup.js"> </script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
    <script type="text/javascript" src="js/menu.js"></script>
	<script type="text/javascript" src="js/ddaccordion.js"></script>
	<script type="text/javascript" 
		src="js/commonUtilityScript/commonUtilityScript.js"></script>
	<script type="text/javascript" src="js/commonUtilityScript/regionSelect.js"></script>
	<script type="text/javascript" src="js/landingPage/landingPage.js" ></script>
	<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.24.custom.min.js"> </script>
	<% if(request.getRequestURL().indexOf("partyanalyst.com") != -1){

%>

<script type="text/javascript" src="js/googleAnalytics/googleAnalytics.js"></script>

<% }

%>
	
	<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.core.css"/>
	<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.theme.css"/>
	<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.accordion.css"/>
	<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
	<link  rel="stylesheet" type="text/css" href="styles/landingPage/landingPage.css"/>
	
	<!--YUI SCRIPT-->
	<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/connection/connection-min.js&2.8.2r1/build/datasource/datasource-min.js&2.8.2r1/build/autocomplete/autocomplete-min.js&2.8.2r1/build/element/element-min.js&2.8.2r1/build/container/container-min.js&2.8.2r1/build/menu/menu-min.js&2.8.2r1/build/button/button-min.js&2.8.2r1/build/paginator/paginator-min.js&2.8.2r1/build/datatable/datatable-min.js&2.8.2r1/build/json/json-min.js&2.8.2r1/build/tabview/tabview-min.js"></script>
	
	
	<script>
		var Localization = { <%
		
		ResourceBundle rb = ResourceBundle.getBundle("common_Lables");
		String stateSelect = rb.getString("stateSelect");
		String distSelect = rb.getString("distSelect");
		String constSelect = rb.getString("constSelect");
		String assembly = rb.getString("assembly");
		String parliament = rb.getString("parliament");
		String localBody = rb.getString("localBodies");
		String electionTypeInHome = rb.getString("electionTypeInHome");
		String electionYearInHome = rb.getString("electionYearInHome");
		
		ResourceBundle resb = ResourceBundle.getBundle("global_ErrorMessages");
		String errorMsg = resb.getString("constTypeAlert");
%> }
	</script>
	<style>
	body{color:#5B5B5B;}
	
.background
{
background:#ffffff;
margin:-1px 0px 0px 0px ;
}

.header2 {
     position: absolute;
    top: -126px;
	left:-2%;
}

: #follow-icon{width:69px;height:24px;}
#follow-icon a.f-icon{margin-left:7px;background: url("images/new_homepage/f.png") repeat scroll 0px 0px transparent; display: block;float: left;height: 24px;transition: all 0s ease-in-out 0s;width: 24px;}
#follow-icon a.f-icon:hover{background: url("images/new_homepage/f.png") repeat scroll 0px -24px transparent;}

#follow-icon a.t-icon{background: url("images/new_homepage/t.png") repeat scroll 0px 0px transparent; display: block;float: left;height: 24px;transition: all 0s ease-in-out 0s;width: 24px;margin-left:6px;}
#follow-icon a.t-icon:hover{background: url("images/new_homepage/t.png") repeat scroll 0px -24px transparent;}

.gradlightblack{
	margin-top:10px;
}

.header-right-sec{width:750px;}
.follow-us-top{width:72px;margin:0px;display:inline-block;}
.follow-us-top h5{margin:0px;display:inline-block;color:#80D1F1;font-size: 12px;}
.lr-sec{padding:14px 5px 5px;}
</style>
<decorator:head/>
</head>
<body>

<div id="feedback_window"><div id="feedback_window_inner"></div></div>
<div id="quickRequest_window"><div id="quickRequest_window_inner"></div></div>
<div id="contactWindowDiv"><div id="contactWindowDiv_window_inner"></div></div>

<div id="floatingDiv_relative_main">
	<div id="floatingDiv_absolute_main" style="position:fixed;top:25px;right:0px; z-index: 999;">
		<c:choose>
			<c:when test="${!empty feedback && feedback == 'true'}">
				<a href="javascript:{}" onclick="showFeedBackFormPanel()"><img width="25" height="100" style="border:0px none;" src="images/icons/homePage_new/feedback_new.jpg"/></a>
			</c:when>	
			<c:otherwise>
				<a href="javascript:{}" onclick="buildAccordion()"><img width="25" height="100" style="border:0px none;" src="images/icons/QuickView.png"/></a>
			</c:otherwise>			
		</c:choose>
		<div id="floatingDiv_absolute_close" >
			<img width="13" height="14" id="floatingDiv_absolute_close_Img" onclick="javascript:{$('#floatingDiv_relative_main').hide();}" src="images/icons/homePage_new/feedback_close.jpeg" alt="feedback Image">
		</div>
			
	</div>
</div>

<table style="border-collapse: collapse;" width="100%">
<tr>
<td>
<div id="header-mainsec">
  <div class="mainwrapper">
  <div class="row-fluid">
    <div class="header">
      <div class="logo" ><a href="<c:out value="${pageContext.request.contextPath}" />/homePage.action"><img src="./images/new_homepage/logo.png" alt="" style="margin-top:0px;"/></a>
	  </div>
      <div class="header-right-sec">
		<div class="pull-right follow-us-top">
		
				<h5>FOLLOW US</h5>
		<div id="follow-icon" >
				<a class="f-icon" title="Follow us on Facebook" href="http://www.facebook.com/PartyAnalyst" target="_blank" alt="Facebook Image"></a>
				<a class="t-icon" title="Follow us on twitter" href="http://twitter.com/#!/partyanalyst" target="_blank" alt="Twitter Image"></a>
			</div>
		</div>
        <div class="lr-sec" >

			<c:if test="${sessionScope.loginStatus == 'out' && (sessionScope.hasFreeUserRole == true && sessionScope.hasPartyAnalystUserRole != true)}">
				<c:out value="Welcome, ${sessionScope.UserName} |"/>
			</c:if>
			
			<c:if test="${sessionScope.loginStatus == 'out' && sessionScope.hasPartyAnalystUserRole == true}">        		
				<c:out value="Welcome, ${sessionScope.UserName} |"/>
			</c:if>
			
			<c:if test="${sessionScope.USER.isAdmin == 'true'}">
				<a href="<c:out value="${pageContext.request.contextPath}/adminUpload.action" />" >Admin</a>|
			</c:if>
			
			<c:if test="${sessionScope.loginStatus == null || sessionScope.loginStatus == 'in'}">
				<!--<a href="<c:out value="${pageContext.request.contextPath}/loginInputAction.action"/>">Login</a> -->
				<a href="javascript:{}" onClick="openDialogForLoginWindow()" id="login">Login</a>
				<span>|</span>
				
				<a href="<c:out value="${pageContext.request.contextPath}/freeUserRegistration.action" />">Register</a>
			</c:if>
									
			<c:if test="${sessionScope.loginStatus == 'out' && (sessionScope.hasFreeUserRole == true && sessionScope.hasPartyAnalystUserRole != true)}">
                <a href="logoutAction.action">Logout</a>
			</c:if>
			
			<c:if test="${sessionScope.loginStatus == 'out' && sessionScope.hasPartyAnalystUserRole == true}">        
				<a href="logoutAction.action">Logout</a>
			</c:if>	
			<!-- For LoginPoppWindow-->
  			<!-- <a href="javascript:{}" onClick="openDialogForLoginWindow()"> New Login</a>-->
   			<div id="login_window">
				<div id="login_window_inner"></div>
   			</div>
   			<!-- End of LoginPoppWindow-->
		</div>
		<% if(request.getRequestURL().indexOf("partyanalyst.com") != -1){

%>
		<!-- live2support.com tracking codes starts --><div id="l2s_trk" style="z-index:99;float: right; position: fixed; right: 10px;bottom:4px;width:130px;"><a href="http://live2support.com" style="font-size:1px;">Live Support Software</a></div><script type="text/javascript"><!--
 var l2s_pht=escape(location.protocol); if(l2s_pht.indexOf("http")==-1) l2s_pht='http:'; var dept="0"; function l2s_load() { document.write('<scr'+'ipt type="text/javaScr'+'ipt" src="'+unescape(l2s_pht)+'//sa.live2support.com/js/lsjs1.php?stid=22424"  defer=true>'+'</scr'+'ipt>');  }
l2s_load();  document.getElementById('l2s_trk').style.visibility='hidden'; //--></script><!-- live2support.com tracking codes closed -->
<% }

%>
<div id="menu">
    <ul class="menu">
        <li class="active"><a href="homePage.action"><span><i class="icon-home icon-white"></i></span></a>
	<c:if test="${sessionScope.loginStatus == null || 	!sessionScope.hasPartyAnalystUserRole}">
        <li><a href="#" ><span>ANALYSIS</span></a>
            <div style="z-index:1;text-align:left;">
				<ul>
					<li><a href="electionComparisonAction.action"><span>Elections Comparison Report</span></a></li>
					<li><a href="partyResultsCriteriaAction.action"><span>Party Results Report</span></a></li>
                </ul>
			</div>
        </li>
	</c:if>
    
	<c:if test="${sessionScope.loginStatus == 'out' && sessionScope.hasPartyAnalystUserRole == true}"> 
         <li>
			<a href="#" class="parent"><span>ANALYSIS</span></a>
            <div  style="z-index:1;text-align:left;">
			<ul>
                <li><a href="#" class="parent">
					<span>Election Analysis</span></a>
						<div  style="z-index:1;text-align:left;">
							<ul>
								<li><a href="partyPerformanceMain.action"><span>Party Performance Report</span></a></li>
								<li><a href="electionComparisonAction.action"><span>Elections Comparison Report</span></a></li>
								<li><a href="partyResultsCriteriaAction.action"><span>Party Results Report</span></a></li>
								<li><a href="electionResultsAnalysisAction.action"><span>Election Results Analysis Report</span></a></li>
								<li><a href="biElectionAction.action"><span>Telengana Bye-Elections 2010</span></a></li>
								<li><a href="censusReportAction.action"><span>Elections vs Census</span></a></li>
								<li><a href="partyStrengthAction.action"><span>Party Strenths/Weakness</span></a></li>
							</ul>
						</div>
                </li>
                <li>
					<a href="#" class="parent"><span>Politician Analysis</span></a>
						<div  style="z-index:1;text-align:left;">
							<ul>
								<li><a href="mandalPageSDetailAction.action"><span>Mandal Voting Report</span></a></li>
								<li><a href="crossVotingReportInputAction.action"><span>Cross Voting Report</span></a></li>
								<li><a href="partyBoothResultAction.action"><span>Constituency Booth Results Report</span></a></li>
							</ul>
						</div>
                </li>
				
				<li><a href="#" class="parent"><span>Live Results Analysis</span></a>
					<div  style="z-index:1;text-align:left;">
						<ul>
							<li><a href="electionLiveResultsAnalysisAction.action"><span>Live & Previous Results Comparison</span></a></li>
							<li><a href="ministerAnalysisAction.action"><span>Ministers & Special Candidates Analysis</span></a></li>
							<li><a href="electionResultsUpdateAction.action"><span>Update Live Election Results</span></a></li>
							<li><a href="assignKeyCandidateAction.action"><span>Assign Special Candidates</span></a></li>
							<li><a href="districtWisePartyPerformanceAction.action"><span>District Wise Party Performance</span></a></li>
						</ul>
					</div>
				</li>
				
            </ul>
		</div>
        
		<li><a href="initailConstituencyManagementAction.action" class="parent"><span>CONSTITUENCY</span></a>
            <div  style="z-index:1;text-align:left;">
				<ul>
					<li><a href="#" class="parent"><span>Problem Management</span></a>
						<div>
							<ul>
								<li><a onclick="openAddNewProblemWindow()" href="javascript:{}"><span>Add New Problem</span></a></li>
								<li><a href="constituencyManagementAction.action?cmTask=PROBLEMS_MANAGEMENT"><span>All Problems</span></a></li>
								<li><a href="completeProblemDetailsSearchAction.action"><span>Problem Search And Report</span></a></li>
							</ul>
						</div>
					</li>
					<li><a href="userGroupAction.action"><span>User Groups</span></a></li>
					<li><a href="callCenterAction.action"><span>Call Center</span></a></li>
					<li><a href="votersAnalysisNewAction.action"><span>Constituency Analysis</span></a></li>
					<li><a href="votersSearchAction.action"><span>Voters Search & Report</span></a></li>
				</ul>
			</div>
		</li>

		<li><a href="statePageAction.action?stateId=1"><span>STATES</span></a>
			<div  style="z-index:8;text-align:left;" >
			<ul>
				<li><a href="#" class="parent"><span>South India</span></a>
					<div style="z-index:1;text-align:left;">
						<ul>
							<li><a href="statePageAction.action?stateId=1"><span>Andhra Pradesh</span></a></li>
							<li><a href="statePageAction.action?stateId=6"><span>Goa</span>
							</a></li>
							<li><a href="statePageAction.action?stateId=12"><span>Karnataka</span></a></li>
							<li><a href="statePageAction.action?stateId=13"><span>Kerala 	</span></a></li>
							<li><a href="statePageAction.action?stateId=24"><span>Tamil Nadu</span></a></li>
						</ul>
					</div>
				</li>
				<li><a href="#" class="parent"><span>North India</span></a>
					<div style="z-index:1;text-align:left;">
						<ul>
							<li><a href="statePageAction.action?stateId=3"><span>Assam</span>
								</a></li>
							<li><a href="statePageAction.action?stateId=7"><span>Gujarat</span>
								</a></li>
							<li><a href="statePageAction.action?stateId=9"><span>Himachal Pradesh</span></a></li>
							<li><a href="statePageAction.action?stateId=16"><span>Manipur</span>
							</a></li>
							<li><a href="statePageAction.action?stateId=21"><span>Punjab</span>
							</a></li>
							<li><a href="statePageAction.action?stateId=27"><span>Uttar Pradesh</span></a></li>
							<li><a href="statePageAction.action?stateId=26"><span>Uttaranchal</span>
							</a></li>
							<li><a href="statePageAction.action?stateId=28"><span>West Bengal</span>
							</a></li>
							<li><a href="statePageAction.action?stateId=17"><span>Meghalaya</span>
							</a></li>
							<li><a href="statePageAction.action?stateId=19"><span>Nagaland</span>
							</a></li>
							<li><a href="statePageAction.action?stateId=25"><span>Tripura</span>
							</a></li>
						</ul>
					</div>
				</li>
				<li><a href="#" class="parent"><span>Union Territories</span></a>
					<div style="z-index:1;text-align:left;">
						<ul>
							<li><a href="statePageAction.action?stateId=35"><span>Puducherry</span>
							</a></li>
						</ul>
					</div>
				</li>
			</ul>
			</div>
		</li>
		<li><a id="cadreId" href="cadreManagementAction.action"><span id="cadreSpanId">CADRE</span></a></li> 
	</c:if>

	<c:if test="${sessionScope.loginStatus == null ||sessionScope.loginStatus == 'in' || (sessionScope.hasFreeUserRole == true && sessionScope.hasPartyAnalystUserRole != true)}">
       <li><a href="statePageAction.action?stateId=1"><span>STATES</span></a>
			<div  style="z-index:8;text-align:left;" >
				<ul>
					<li><a href="#" class="parent"><span>South India</span></a>
					<div style="z-index:1;text-align:left;">
						<ul>
							<li><a href="statePageAction.action?stateId=1"><span>Andhra Pradesh</span>
							</a></li>
							<li><a href="statePageAction.action?stateId=6"><span>Goa</span>
							</a></li>
							<li><a href="statePageAction.action?stateId=12"><span>Karnataka</span>
							</a></li>
							<li><a href="statePageAction.action?stateId=13"><span>Kerala</span>
							</a></li>
							<li><a href="statePageAction.action?stateId=24"><span>Tamil Nadu</span>
							</a></li>
						</ul>
					</div>
					</li>
		 
					<li><a href="#" class="parent"><span>North India</span></a>
						<div style="z-index:1;text-align:left;">
							<ul>
								<li><a href="statePageAction.action?stateId=3"><span>Assam</span>
								</a></li>
								<li><a href="statePageAction.action?stateId=7"><span>Gujarat</span>
								</a></li>
								<li><a href="statePageAction.action?stateId=9"><span>Himachal Pradesh</span></a></li>
								<li><a href="statePageAction.action?stateId=16"><span>Manipur</span>
								</a></li>
								<li><a href="statePageAction.action?stateId=21"><span>Punjab</span>
								</a></li>
								<li><a href="statePageAction.action?stateId=27"><span>Uttar Pradesh</span></a></li>
								<li><a href="statePageAction.action?stateId=26"><span>Uttaranchal</span>
								</a></li>
								<li><a href="statePageAction.action?stateId=28"><span>West Bengal</span>
								</a></li>
							</ul>
						</div>
					</li>
	

					<li><a href="#" class="parent"><span>Union Territories</span></a>
						<div style="z-index:1;text-align:left;">
							<ul>
								<li><a href="statePageAction.action?stateId=35"><span>Puducherry</span>
								</a></li>
							</ul>
						</div>
					</li>
				</ul>
			</div>
		</li>

	</c:if>

		
	   
		<li><a href="#" ><span>SERVICES</span></a>
            <div style="z-index:1;text-align:left;">
				<ul>
                <li><a href="newsMonitoringService.action"><span>News Monitoring Services</span></a></li>
                <li><a href="VotersPulse.action"><span>Voters Pulse</span></a></li>
				<li><a href="constituencyProfileReport.action"><span>Constituency Profile Reports</span></a></li>
				<li><a href="electionAnalysisAndManagementTool.action"><span>Election Analysis And Management Tool</span></a></li>				
				</ul>
			</div>
        </li>	

		<c:if test="${sessionScope.loginStatus == 'out'}">  
			<li> 
				<c:choose>
				<c:when test="${sessionScope.hasPartyAnalystUserRole == true}"> 
				<a href="<c:out value="${pageContext.request.contextPath}" />/index.action" >
				<span>DASHBOARD</span>
				</a> 
				</c:when>
				
				<c:otherwise> 
					<a href="<c:out value="${pageContext.request.contextPath}" />/userProfile.action" >												
					<span>DASHBOARD</span>
					</a>  
				</c:otherwise>	
				
				</c:choose>
			</li> 
		</c:if>	
					
			<li>
				<a class="searchClass" id="searchId" href="searchInPartyAnalyst.action">
				<span id="searchSpanId">SEARCH</span></a>
			</li>
    </ul>
	
</div>
<!--MENU END-->
  
</div><!--HEADER RIGHT SEC END-->
</div><!--HEADER END-->
</div>
</div><!--MAIN WRAPPER END-->
</div><!--HEADER MAIN SECTION END-->
</td>
</tr>

<tr>
<td class="background" border="0">
<div style="width:960px;position:relative;margin:0 auto;">
<center>

<div class="header2">
<!--Facebook like icon-->
	<div style="float:left;margin-left:80px;margin-top:15px;" class="fb-like" data-href="http://www.facebook.com/PartyAnalyst" data-send="false" data-layout="button_count" data-width="0" data-show-faces="false">
	</div>
	<!--Facebook and twitter follow us
		<div class="follow-us" style="margin-left:180px;">
            <ul>
              <li><a href="http://twitter.com/#!/partyanalyst" target="_blank"><img src="./images/new_homepage/twitter.gif" alt="" height="30px" width="30px"/></a></li>
              <li><a title="Facebook" href="http://www.facebook.com/PartyAnalyst" target="_blank"><img src="./images/new_homepage/facebook.gif" alt="" height="30px" width="30px"/></a></li>
            </ul>
            <h5 style="color:#08AAEC;">follow us</h5> 
		</div>-->
</div>

</center>
</div>
</td>
</tr>
<!--BODY -->
<tr>
	<td class="background" border="0">
 		<div id="contenttable" class="background">
			<decorator:body/>
		</div>
	</td>
</tr>
<tr><td style="background:#5d5d5d;">
<!--FOOTER SECTION START-->
<div class="mainwrapper" > 
  <!--FOOTER LEFT SECTION (FOOTER MENU) START-->
	<div class="footer-left-sec">
		<ul>
			<li class="f-title">About Us</li>
			<li><a href="footerLinksAction.action?linkFrom=aboutUs#whoWeAre">Who we are</a><span>|</span></li>
			<li><a href="footerLinksAction.action?linkFrom=aboutUs#whatWeDo">What we do</a><span>|</span></li>
			<li><a href="footerLinksAction.action?linkFrom=aboutUs#coreCompetency">Core Competency</a><span>|</span></li>
			<li><a href="javascript:{}" onClick="contactForSupportLinkInHomePage()">Customer Support</a><span>|</span></li>
			<li><a href="javascript:{}" id="contactLink" onclick="contactLinkInHomePage()">Contact</a><span>|</span></li>
			<li><a href="sitemapAction.action?linkFrom=aboutUs#sitemap">Sitemap</a></li>
		</ul>
		
		<ul>
			<li class="f-title">Connect</li>
			<li><a href="freeUserRegistration.action">Register</a><span>|</span></li>
			<li><a href="loginInputAction.action">Login</a><span>|</span></li>
			<li><a href="viewFeaturesAction.action">Explore</a><span>|</span></li>
			<li><a href="javascript:{}" onClick="contactForDemoLinkInHomePage()">Ask for DEMO/TEST</a><span>|</span></li>
			<li><a href="javascript:{}" onClick="showFeedBackFormPanel()">Feedback</a><span>|</span></li>
			<li><a href="javascript:{}" onClick="showArticles()">Articles</a><span>|</span></li>
			<li><a href="javascript:{}" onClick="showBlogs()">Blogs</a></li>
		</ul>
		
		<ul>
			<li class="f-title">Policy</li>
			<li><a href="footerLinksAction.action?linkFrom=termsOfUse#termsOfUse">Terms of use</a><span>|</span></li>
			<li><a href="footerLinksAction.action?linkFrom=privacy#privacyPolicy">Privacy</a><span>|</span></li>
			<li><a href="footerLinksAction.action?linkFrom=disclaimer#disclaimer">Disclaimer</a></li>
		</ul>
		
		<ul>
			<li class="f-title">News &amp; Events</li>
			<li><a href="javascript:{}" onclick="openMediaOpenionsWindow()">Party Analyst Launch</a><span>|</span></li>
			<li><a href="footerLinksAction.action?linkFrom=guestArticelsSubmission" target="_blank">Publish Your Article</a><span>|</span></li>
			<li><a href="newsZoneAction.action" target="_blank">News Zone</a></li>
		</ul>
  </div>
  <!--FOOTER LEFT SECTION (FOOTER MENU) END--> 
  
  
  <!--FOOTER RIGHT SECTION (QUICK REQUEST FORM) START-->
  <div class="footer-right-sec">
    <h3 class="form-title">Quick Request</h3>
		<ul>
			<li>
				<input name="name" id="quickRequestNameTextbox" type="text" class="text-fields" value="Name" onblur="if(this.value=='')this.value=this.defaultValue;" onfocus="if(this.value==this.defaultValue)this.value='';"/>
			</li>
			
			<li>
				<input name="email" id="quickRequestEmailTextbox" type="text" class="text-fields" value="Email" onblur="if(this.value=='')this.value=this.defaultValue;" onfocus="if(this.value==this.defaultValue)this.value='';"/>
			</li>
			
			<li style="width:307px;">
				<textarea name="requirement" rows="3" cols="21" id="quickRequestReqTextbox" Value="Comment" style="height:78px;width:287px;border:1px solid #7f7f7f;background-color:#aaa;"  onblur="if(this.value=='')this.value=this.defaultValue;" onfocus="if(this.value==this.defaultValue)this.value='';" >Comment</textarea>
			</li>
			
			<li style="width:307px;text-align:left;">
				<input name="mobileNO" id="quickRequestMobileTextbox" type="text" class="text-fields" value="Mobile No"  onblur="if(this.value=='')this.value=this.defaultValue;" onfocus="if(this.value==this.defaultValue)this.value='';"/>
				<button href="#" onclick="submitDialogBox()" class="btn btn-danger">Submit</button>
			</li>
		</ul>
  </div>
  <!--FOOTER LEFT SECTION (FOOTER MENU) END-->
  
  <div class="clear"></div>
  <!--COPYRIGHT SECTION START-->
	<%
		java.util.Calendar now = java.util.Calendar.getInstance();
		int year =  now.get(java.util.Calendar.YEAR);
	%>

	<div class="copy-rights"> &copy; Copyright <%=year %>. All rights reserved  IT GRIDS (India) Pvt. Ltd. </div>
  <!--COPYRIGHT SECTION END--> 
</div>
</td></tr>
</table>
<!--FOOTER SECTION END-->

<div id="accordion" title="Quick View" style="display:none;">
          	<h3 ><a href="#">View Your State</a></h3>
				<div style="padding:0px;">
					<div class="widgetsBody" style="background-color:#FFFFFF;color:#49443E;">
								<table style="font-size:12px;">
									<tr>
										<td style="text-align:justify;padding-bottom:30px;line-height:25px;"> Select your state to view its Assembly, Parliament, Local Bodies election results. </td>
									</tr>
									<tr>
		<td style="height:40px;color:#004078;font-size=12px;">
	<%=stateSelect%></td>
									</tr>
									<tr>
									<td style="font-size:12px;"><s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state_s" id="stateList_s" list="{}" listKey="id" listValue="name" /></td>									
									</tr>								
								</table>
							</div>
							<div class="widgetsFooter" style="background-color:#FFFFFF;height:37px;">
								<img width="70" height="25" src="images/icons/homePage_new/b3.jpg" onclick="navigateToStatePage()"></img>
							</div>
						</div>
						<h3><a href="#" style="font-size: 13px;
    font-family: verdana,arial,san-serif;">View Your District</a></h3>
						<div style="padding:0px;">
							<div class="widgetsBody" style="background-color:#FFFFFF;color:#49443E;">
								<div id="alertMessage_district" style="color:red;font-weight:bold;"></div>
								<table style="font-size:12px;">
									<tr>
										<td style="text-align:justify;padding-bottom:5px;line-height:25px;"> Select your district to view its election results in district level. </td>
									</tr>
									<tr>
										<td style="color:#004078"><%=stateSelect%></td>
									</tr>
									<tr>
								<td style="width:200px;font-size=12px;">
								<s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state" id="stateList2" list="{}" listKey="id" listValue="name" onchange="getDistrictsComboBoxForAStateInQuickView(this.options[this.selectedIndex].value,'districtList_d')"></s:select></td>
										<td><span id="districtList_d_ImgSpan" style="display:none;"><img src="images/icons/search.gif" /></span>
										</td>
									</tr>
									<tr>
										<td style="color:#004078"><%=distSelect%></td>
									</tr>
									<tr>
										<td><s:select theme="simple" cssClass="selectBoxWidth" label="Select Your District" name="district" id="districtList_d" list="{}" listKey="id" listValue="name" headerKey = "0" headerValue="Select District"/></td>
									</tr>
								</table>
							</div>
							<div class="widgetsFooter" style="background-color:#FFFFFF;height:37px;">
								<img width="70" height="25" src="images/icons/homePage_new/b3.jpg" onclick="navigateToDistrictPage()"></img>
							</div>
						</div>
						<h3 ><a href="#" style="font-size: 13px;
    font-family: verdana,arial,san-serif;">View Your Constituency</a></h3>
						<div style="padding:0px;">
							<div class="widgetsBody" style="background-color:#FFFFFF;color:#49443E;">
								<div id="alertMessage" style="color:red;font-weight:bold;"></div>
								<table style="font-size:12px;">
									<tr>
										<td colspan="4" style="font-size:12px;">Select Constituency Type</td>
									</tr>	
									<tr>
										<td colspan="2"><input type="radio" checked="checked" name="a_radio" id="a_radio" onclick="hideUnhideSelectBoxInQuickView(this.id, 'constituency')"/><%=assembly%></td>
										<td><input type="radio" name="a_radio" id="p_radio" onclick="hideUnhideSelectBoxInQuickView(this.id,'constituency')"/><%=parliament%></td>
									</tr>
								</table>
								<table id="stateTable" style="display:none;font-size:12px;">
									<tr>
										<td style="color:#004078"><%=stateSelect%></td>
									</tr>
									<tr>
										<td style="font-size:12px;"><s:select cssClass="selectBoxWidth" theme="simple" label="Select Your State" name="state" id="stateList_c" list="{}" listKey="id" listValue="name" onchange="getAllConstituenciesInStateByTypeInQuickView(2,this.options[this.selectedIndex].value,'constituency')"></s:select></td>
										<td><span id="constituency_ImgSpan" style="display:none;"><img src="images/icons/search.gif" /></span>
										</td>
									</tr>
								</table>
									
								
								<table id="constTable" style="display:none;font-size:12px;">
									<tr>
										<td style="color:#004078"><%=constSelect%></td>
									</tr>
									<tr>
										<td><s:select theme="simple" cssClass="selectBoxWidth" label="Select Your Constituency" name="constituency" id="constituency" list="{}" listKey="id" listValue="name" headerKey = "0" headerValue="Select Constituency"/></td>
									</tr>
								</table>										
							</div>
							<div class="widgetsFooter" style="background-color:#FFFFFF;height:37px;">
								<img width="70" height="25" src="images/icons/homePage_new/b3.jpg" onclick="navigateToConstituencyPage()"></img>
							</div>
						</div>

					<h3 ><a href="#" style="font-size: 13px;
    font-family: verdana,arial,san-serif;">View Your Locality</a></h3>
						<div style="padding:0px;">
							<div class="widgetsBody" style="background-color:#FFFFFF;color:#49443E;">
								<table style="font-size:12px;">								
									<tr>
										<td style="color:#004078"><%=stateSelect%></td>
									</tr>
									<tr>
										<td style="font-size:12px;"><s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state_s" id="stateList_l" list="{}" listKey="id" listValue="name" onchange="getLocalBodiesForState(this.options[this.selectedIndex].value)"/></td>									
									</tr>
									<tr>
										<td style="color:#004078"><div id="localBodiesRadioDiv_label"><%=localBody%></div></td>
									</tr>
									<tr>
										<td><div id="localBodiesRadioDiv_data"></div></td>									
									</tr>
									<tr>
										<td style="color:#004078"><div id="localBodiesSelectDiv_label"></div></td>
									</tr>
									<tr>
										<td><div id="localBodiesSelectDiv_data"></div></td>									
									</tr>
									<tr>
										<td><div id="localBodies_errorDiv"></div></td>									
									</tr>
								</table>
							</div>
							<div class="widgetsFooter" style="background-color:#FFFFFF;height:37px;">
								<img width="70" height="25" src="images/icons/homePage_new/b3.jpg" onclick="navigateToLocalBodyPage()"></img>
							</div>
						</div>

						<h3 ><a href="#" style="font-size: 13px;
    font-family: verdana,arial,san-serif;">View Election Results</a></h3>
						<div style="padding:0px;">
							<div class="widgetsBody" style="background-color:#FFFFFF;color:#49443E;">
								<table style="font-size:12px;">
								
		<tr>
		<td><select id="electionTypeId" name="electionType"  cssClass="textFieldStyle" cssStyle="width: 145px;margin-left:0px;" style="margin-left:12px;padding: 1px;width: 200px;"
		onchange="checkElectionType(this.options[this.selectedIndex].value)"/>
		<option value="0">Select Type</option>
		<option value="2">Assembly</option>
		<option value="1">Parliament</option>
		</select>	</td></tr>
						
			

		<tr><td>
		<select id="states" cssClass="textFieldStyle" cssStyle="width: 145px;margin-left:0px;" style="margin-left:12px;padding: 1px;
		width: 200px;"
		onchange="getElectionYearsInHomePage('Assembly')">
			</select></td></tr>

		<tr><td><select id="electionYears" cssClass="textFieldStyle" cssStyle="width: 145px;margin-left:0px;" style="margin-left:12px;padding: 1px;
			width: 200px;"></select></td></tr></table>
							</div>
							<div class="widgetsFooter" style="background-color:#FFFFFF;height:37px;">
								<table width="90%"><tr>
									<td width="65%"><div id="electionDetailsErrorMsgDiv" style="display:none;"><font color="red"><b>*Select All Inputs</b></font></div></td>

									<td width="35%"><img width="70" height="25" src="images/icons/homePage_new/b3.jpg" onclick="viewElectionResults()"></img></td>
								</tr></table>
							</div>
						</div>
						
						</div>
				

<script>
function checkElectionType(electionTypeId)
{

var electionType = document.getElementById('electionTypeId').value;

if(electionType == 1)
	{
getStates();
document.getElementById('states').style.display="none";
getElectionYearsInHomePage('Parliament');
	}

if(electionType == 2)
	{
	document.getElementById('states').style.display="block";

getStatesForNewLayOut();

	}
}
function getStatesForNewLayOut()
{

	var electionType = document.getElementById('electionTypeId').value;

	var jsObj=
		{						
				
				electionType:electionType,
				task:"getStates"
		}

		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getStatesForHomepage.action?"+rparam;						
	callHomePageAjax(jsObj,url);

}

function getElectionYearsInHomePage(electionType)
{

	var stateEle = document.getElementById("states");
	if(electionType == 'Assembly')
	var stateId = stateEle.options[stateEle.selectedIndex].value;
	if(electionType == 'Parliament')
		stateId = 1;
	document.getElementById("electionYears").length = 0;
	
	if(electionType == null || electionType == 'Select Type' || stateId == 0)
		return;
	
	var jObj = {
			stateId : stateId,
		electionType: electionType,
				task: 'getElectionYearsForAState'
				};

	var rparam = "task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "electionYearsForstateAndElectionTypeAction.action?"+rparam;
	callHomePageAjax(jObj,url);
}



function checkElectionType(electionTypeId)
{

var electionType = document.getElementById('electionTypeId').value;
if(electionType == 1)
	{
	getStatesForNewLayOut();
	document.getElementById('states').style.display="none";
	getElectionYearsInHomePage('Parliament');
	}

if(electionType == 2)
	{
	document.getElementById('states').style.display="block";

getStatesForNewLayOut();

	}
}
function getStatesForNewLayOut()
{

	var electionType = document.getElementById('electionTypeId').value;

	var jsObj=
		{						
				
				electionType:electionType,
				task:"getStates"
		}

		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getStatesForHomepage.action?"+rparam;
	callHomePageAjax(jsObj,url);

}

function callQuickViewAjax(jsObj, url){


	var callback = {			
				   success : function( o ) {
						try
						{
							
							myResults = YAHOO.lang.JSON.parse(o.responseText);	
							
							if(jsObj.taskType == "getRegions")
							{
								clearOptionsListForSelectElmtId(jsObj.elmtId);
								createOptionsForSelectElmtId(jsObj.elmtId,myResults);
								hideBusyImgWithId(jsObj.elmtId)
							}
							else if(jsObj.task == "getConstituencies")
							{
								clearOptionsListForSelectElmtId(jsObj.elmtId);
								createOptionsForSelectElmtId(jsObj.elmtId,myResults);
								hideBusyImgWithId(jsObj.elmtId);
							}else if(jsObj.task == "getAllParliamentConstituencies")
							{
								clearOptionsListForSelectElmtId(jsObj.elmtId);
								createOptionsForSelectElmtId(jsObj.elmtId,myResults);
							}
							else if(jsObj.task == "statesListForLocalBodyElection"){
								clearOptionsListForSelectElmtId(jsObj.elmtId);
								createOptionsForSelectElmtId(jsObj.elmtId,myResults);	
							}
							else if(jsObj.task == "siteSearch"){
								clearOptionsListForSelectElmtId(jsObj.elmtId);
								createOptionsForSelectElmtId(jsObj.elmtId,myResults);	
								clearOptionsListForSelectElmtId("stateList_c");
								createOptionsForSelectElmtId("stateList_c",myResults);	
								clearOptionsListForSelectElmtId("stateLists");
								createOptionsForSelectElmtId("stateLists",myResults);	
								clearOptionsListForSelectElmtId("stateList2");
								createOptionsForSelectElmtId("stateList2",myResults);
								
							}
							else{
							clearOptionsListForSelectElmtId(jsObj.elmtId);
							
							createOptionsForSelectElmtId(jsObj.elmtId,myResults);
							}

						}
						catch(e)
						{   
							//alert("Invalid JSON result" + e);   
						}  
				   },
				   scope : this,
				   failure : function( o ) {
								//alert( "Failed to load result" + o.status + " " + o.statusText);
							 }
				   };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
}
function getDistrictsComboBoxForAStateInQuickView(value,elmtId)
{	
	
	showBusyImgWithId(elmtId);	
	
	clearOptionsListForSelectElmtId(elmtId);
	
	var jsObj=
		{				
				stateId:value,
				elmtId:elmtId,
				task:"findDistrictsForAState",
				taskType:"getRegions"				
		}
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getDistrictsForAStateAjaxAction.action?"+rparam;						
	callQuickViewAjax(jsObj,url);
}

function buildAccordion()
{
	var accordionElmtId = document.getElementById("accordion");
	
	if($("#accordion").parents(".ui-dialog").is(":visible"))
		return;
	accordionElmtId.style.display = 'block';

	getStatesInQuickView('siteSearch','stateList_s');
	
	getDistrictsComboBoxForAStateInQuickView(1, 'districtList_d');
	hideUnhideSelectBoxInQuickView('a_radio', 'constituency');
	getAllStatesHavingLocalBody("stateList_l");
	getLocalBodiesForState(1);

	$("#accordion").accordion();
		$(function() {
		$( "#accordion").dialog({
			autoOpen: true,
			show: "blind",
			width: 350,
			minHeight:460,
			hide: "explode"
		});
	
	});
 }
 
 
 <!--Accordian Script functions-->
 function getStatesInQuickView( task,selId)
{		
	
	var jsObj=
		{				
			electionType :'Assembly',
			elmtId:selId,
			task:task	
		}
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getStatesAjaxAction.action?"+rparam;						
	callQuickViewAjax(jsObj,url);
}

function hideUnhideSelectBoxInQuickView(radioElement, selectElement)
{
	
	var stateId;
	var tableEl = document.getElementById("constTable");
	var stateTableEl = document.getElementById("stateTable");
	var stateSelectEl = document.getElementById("stateList_c");
	if(stateSelectEl.selectedIndex == -1){
	stateId = 1;
	}
	else{
	 stateId = stateSelectEl.options[stateSelectEl.selectedIndex].value;
		}
	var alertEl = document.getElementById("alertMessage");
	alertEl.innerHTML = '';
	if(radioElement == 'a_radio')
	{
		
		if(stateTableEl.style.display == 'none')
		{
			stateTableEl.style.display = 'block';
		}
		if(tableEl.style.display == 'none')
		{
			tableEl.style.display = 'block';
		}
		/*election type 2 for mla const*/
		getAllConstituenciesInStateByTypeInQuickView(2,stateId,selectElement);
	} else if(radioElement == 'p_radio')
	{
		 /*election type 1 for mla const*/
		
		if(stateTableEl.style.display == 'block')
		{
			stateTableEl.style.display = 'none';
		}
		if(tableEl.style.display == 'none')
		{
			tableEl.style.display = 'block';
		}
		getAllParliamentConstInCountryInQuickView(selectElement);
	}
	 
}

function getAllConstituenciesInStateByTypeInQuickView(electionType, stateId, element)
{
	showBusyImgWithId(element);
	clearOptionsListForSelectElmtId(element);
	var jsObj=
	{				
			electionTypeId: electionType,
			stateId: stateId,
			task: "getConstituencies",
			elmtId: element 	
	}

var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
var url = "getAllConstituenciesInState.action?"+rparam;						
callQuickViewAjax(jsObj,url);
}

function getAllStatesHavingLocalBody(element){

	var jsObj={
		task: "statesListForLocalBodyElection",
		elmtId: element
	}
	var rparam = "task="+YAHOO.	lang.JSON.stringify(jsObj);
	var url="getAllStatesHavingLocalBody.action?"+rparam;
	callQuickViewAjax(jsObj,url);
}

function getLocalBodiesForState(stateId)
{
	var jsObj=
	{
			stateId:stateId,
			task:"getLocalBodiesForState"					
	}; 

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getLocalBodiesTypesInAState.action?"+rparam;						
	homePageAjaxCall(rparam,jsObj,url);
}


function homePageAjaxCall(param,jsObj,url){
	var myResults;
		
		var callback = {			
		               success : function( o ) 
					  {
						try {			
								if(o.responseText)
								myResults = YAHOO.lang.JSON.parse(o.responseText);								

								if(jsObj.task == "getRecentElectionsInState")
								{									
									showResults(myResults);
								} 
								else if(jsObj.task == "getAllPolls")
								{			
									
								    
									if(myResults.description==null){
										
										//showVotesObtainedForOptions(myResults.questionsOptionsVO);
										if(myResults.questionsOptionsVO != null)
										displayCurrentPollResult(myResults.questionsOptionsVO.questionId);

									}else{										
										//buildNewPoll(myResults);
										buildNewPoll1(myResults);
									}
								}
								else if(jsObj.task == "saveSelectedPoll")
								{	
									
									showVotesObtainedForOptions(myResults);
								} 
								else if(jsObj.task == "getLocalBodiesForState")
								{
									buildLocalBodiesForAState(jsObj,myResults);
								}
								else if(jsObj.task == "getLocalBodiesSelectElmtForState")
								{
									buildLocalBodiesSelectElmt(jsObj,myResults)
								}
								
								
						}
						catch (e)
							{   
							  	//alert("Invalid JSON result" + e);   
							}	  
			              },
			               scope : this,
			               failure : function( o ) {

			            	  			// alert( "Failed to load result" + o.status + " " + o.statusText);
			                         }
			               };

			YAHOO.util.Connect.asyncRequest('GET', url, callback);
	}
	
	function getAllParliamentConstInCountryInQuickView(element)
{
	
	var jsObj=
	{				
			task: "getAllParliamentConstituencies",
			elmtId: element 	
	}

var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
var url = "getAllParliamentConstInCountry.action?"+rparam;						
callQuickViewAjax(jsObj,url);
}
 
 
 
 
 
 <!--footer functions-->
function contactForSupportLinkInHomePage(){

			jQuery(document).ready( function(){       
				//	jQuery("#contactLink").click( showDialog );

						//$myWindow = jQuery('#contactWindowDiv');

						//instantiate the dialog
						$("#contactWindowDiv").dialog({ stack: false,
							    height: 220,
								width: 800,
								modal: true,
								position: [170,150],
								title:'Contact Us www.partyanalyst.com',
								overlay: { opacity: 0.5, background: 'black'}
								});
						

			});	
			//function to show dialog   
			var showDialog = function() {
				$("#contactWindowDiv").show(); 
				//open the dialog
				$("#contactWindowDiv").dialog("open");
				}

			//function to close dialog, probably called by a button in the dialog
			var closeDialog = function() {
				$("#contactWindowDiv").dialog("close");
			}
			
 var elmt = document.getElementById("contactWindowDiv_window_inner");

  var str ='';

str+='<table width="100%">';
str+='<tr>';
str+='<td><img src="images/icons/homePage_new/logo.png" height="100px" width="170px"></td>';
str+='<td>';
str+='<table style="margin-left:17px">';
str+='<tr>';
str+='<td><B>IT Grids (India) Pvt. Ltd.</B><br></td></tr>';
str+='<tr><td>Hyderabad.<br></td></tr>';
str+='<tr><td>Phone: +91 40 4012 4153<br></td></tr>';
str+='<tr><td>Mobile: +91 96766 96760<br></td></tr>';
str+='<tr><td>Email: customer.services@partyanalyst.com<br></td></tr>';
str+='</table>';
str+='</td>';
str+='<td><img src="images/icons/homePage_new/itgrids_logo.gif" height="130px" width="200px"></td>';				
str+='</tr>';
str+='</table>';


elmt.innerHTML = str;
}

function contactForDemoLinkInHomePage(){

			jQuery(document).ready( function(){       
				//	jQuery("#contactLink").click( showDialog );

						//$myWindow = jQuery('#contactWindowDiv');

						//instantiate the dialog
						$("#contactWindowDiv").dialog({ stack: false,
							    height: 220,
								width: 700,
								modal: true,
								position: [170,150],
								title:'Contact Us www.partyanalyst.com',
								overlay: { opacity: 0.5, background: 'black'}
								});
						

			});	
			//function to show dialog   
			var showDialog = function() {
				$("#contactWindowDiv").show(); 
				//open the dialog
				$("#contactWindowDiv").dialog("open");
				}

			//function to close dialog, probably called by a button in the dialog
			var closeDialog = function() {
				$("#contactWindowDiv").dialog("close");
			}
			
 var elmt = document.getElementById("contactWindowDiv_window_inner");

  var str ='';

str+='<table width="100%">';
str+='<tr>';
str+='<td><img src="images/icons/homePage_new/logo.png" height="100px" width="170px"></td>';
str+='<td>';
str+='<table style="margin-left:17px">';
str+='<tr>';
str+='<td><B>IT Grids (India) Pvt. Ltd.</B><br></td></tr>';
str+='<tr><td>Hyderabad.<br></td></tr>';
str+='<tr><td>Phone: +91 40 4012 4153<br></td></tr>';
str+='<tr><td>Mobile: +91 96766 96760<br></td></tr>';
str+='<tr><td>Email: sales@partyanalyst.com<br></td></tr>';
str+='</table>';
str+='</td>';
str+='<td><img src="images/icons/homePage_new/itgrids_logo.gif" height="130px" width="200px"></td>';				
str+='</tr>';
str+='</table>';


elmt.innerHTML = str;
}


function supportLinkInHomePage(){

			jQuery(document).ready( function(){       
				//	jQuery("#contactLink").click( showDialog );

						//$myWindow = jQuery('#contactWindowDiv');

						//instantiate the dialog
						$("#supportWindowDiv").dialog({ height: 220,
								width: 630,
								modal: true,
								position: 'center',
								title:'Support @ IT Grids',
								overlay: { opacity: 0.5, background: 'black'}
								});
						

			});	
			//function to show dialog   
			var showDialog = function() {
				$("#supportWindowDiv").show(); 
				//open the dialog
				$("#supportWindowDiv").dialog("open");
				}

			//function to close dialog, probably called by a button in the dialog
			var closeDialog = function() {
				$("#supportWindowDiv").dialog("close");
			}
			
 var elmt = document.getElementById("supportWindowDiv_window_inner");

  var str ='';

str+='<table width="100%">';
str+='<tr>';
str+='<td><img src="images/icons/homePage_new/logo.png" height="100px" width="170px"></td>';
str+='<td>';
str+='<table style="margin-left:17px">';
str+='<tr>';
str+='<td><B>IT Grids (India) Pvt. Ltd.</B><br></td></tr>';
str+='<tr><td>Hyderabad.<br></td></tr>';
str+='<tr><td>Mobile: +91 96766 96760<br></td></tr>';
str+='<tr><td>Email: info@partyanalyst.com or a.dakavaram@itgrids.com<br></td></tr>';
str+='</table>';
str+='</td>';
str+='<td><img src="images/icons/homePage_new/itgrids_logo.gif" height="130px" width="200px"></td>';				
str+='</tr>';
str+='</table>';


elmt.innerHTML = str;
}




function navigateToStatePage()
{
	$('#processingDivForState').show();
	var stateSelectEl = document.getElementById("stateList_s");
	var stateSelectElVal = stateSelectEl.options[stateSelectEl.selectedIndex].value
	window.location="statePageAction.action?stateId="+stateSelectElVal; 
}
function navigateToDistrictPage()
{	
	$('#processingDivForDistrict').show();
	var distSelectEl = document.getElementById("districtList_d");
	var alertEl = document.getElementById("alertMessage_district");
	var distSelectElVal = distSelectEl.options[distSelectEl.selectedIndex].value;
	var distSelectElText =  distSelectEl.options[distSelectEl.selectedIndex].text;
	if(distSelectElVal == 0)
	{
		alertEl.innerHTML = 'Please Select Your District';
		$('#processingDivForDistrict').hide();
		return;
	}
	else
		alertEl.innerHTML = '';
	window.location="districtPageAction.action?districtId="+distSelectElVal+"&districtName="+distSelectElText;
	
}
function navigateToConstituencyPage()
{		
	$('#processingDivForConstituency').show();
	var constSelectEl = document.getElementById("constituency");
	var alertEl = document.getElementById("alertMessage");
	var constSelectElVal = constSelectEl.options[constSelectEl.selectedIndex].value;
	alertEl.innerHTML = '';
	if(constSelectElVal == 0)
	{
		alertEl.innerHTML = 'Please Select Your Constituency';
		$('#processingDivForConstituency').hide();
		return;
	}
	window.location = "constituencyPageAction.action?constituencyId="+constSelectElVal;
	
}


function navigateToLocalBodyPage()
{
	var errorElmt = document.getElementById("localBodies_errorDiv");
	var stateElmt = document.getElementById("stateList_l");		
	var localBodySelectElmt = document.getElementById("localBodySelectElmt");
	var radioElmts = document.getElementsByName("localBodyRadio");
	var localBodyElectionId;

	if(!stateElmt || !localBodySelectElmt || !radioElmts || radioElmts.length == 0)
		return;
	$('#processingDivForLocality').show();
	var stateId = stateElmt.options[stateElmt.selectedIndex].value;
	var localBodySelectElmtValue = localBodySelectElmt.options[localBodySelectElmt.selectedIndex].value;
	for(var i=0; i<radioElmts.length; i++)
	{
		if(radioElmts[i].checked == true)
			localBodyElectionId = radioElmts[i].value;
	}
	
	if(localBodySelectElmtValue == 0 && errorElmt)
	{
		errorElmt.innerHTML = '<font color="red" style="font-size:12px;"> Please Select Location.. </font>';
		$('#processingDivForLocality').hide();
		return;
	}
	else
		errorElmt.innerHTML = '';

	window.location="localBodyElectionAction.action?stateId="+stateId+"&localBodyElectionTypeId="+localBodyElectionId+"&localBodyId="+localBodySelectElmtValue;
}

function getSelectElmtForLocalBody(localBodyId)
{
	var statelocalEl = document.getElementById("stateList_l");
	var stateSelectlocalElVal = statelocalEl.options[statelocalEl.selectedIndex].value;
	
	var jsObj =
		{
			stateId: stateSelectlocalElVal,
			electionTypeId:localBodyId,
			task: "getLocalBodiesSelectElmtForState"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);	
	var url = "getLocalBodiesInAState.action?"+rparam; 
	homePageAjaxCall(rparam,jsObj,url);	
}

function buildLocalBodiesSelectElmt(jsObj,results)
{
	var labelElmt = document.getElementById("localBodiesSelectDiv_label");
	var dataElmt = document.getElementById("localBodiesSelectDiv_data");

	if(!labelElmt || !dataElmt)
		return;

	var labelStr = '';
	labelStr += 'Select Location';
	labelElmt.innerHTML = labelStr;
	
	var dataStr = '';
	dataStr += '<select id="localBodySelectElmt"></select>';

	dataElmt.innerHTML = dataStr;

	clearOptionsListForSelectElmtId("localBodySelectElmt");
	createOptionsForSelectElmtIdWithSelectOption("localBodySelectElmt",results);
}

function buildLocalBodiesForAState(jsObj,results)
{
	var localBodyString = '';
	var elmtLabel = document.getElementById("localBodiesRadioDiv_label");
	var elmtData = document.getElementById("localBodiesRadioDiv_data");

	var selectElmtLabel = document.getElementById("localBodiesSelectDiv_label");
	var selectElmtdata = document.getElementById("localBodiesSelectDiv_data");


	if(!elmtLabel || !elmtData || !selectElmtLabel || !selectElmtdata)
		return;
	

	selectElmtLabel.innerHTML = '';
	selectElmtdata.innerHTML = '';

	if(results.length == 0)
		elmtLabel.innerHTML = '';
	else
		elmtLabel.innerHTML = localBodyString;

	var str = '';
	for(var i=0; i<results.length; i++)
	{
		str += '<label class="radio">';
		str += '<input type="radio" name="localBodyRadio" onclick="getSelectElmtForLocalBody(this.value)" value="'+results[i].id+'"> '+results[i].name+' </input><br>';
		str +='</label>'
		/*if(i == 1)
			str += '<br/>';*/
	}

	elmtData.innerHTML = str;
}


function callHomePageAjax(jsObj,url)
{			
	
	var callback = {			
				   success : function( o ) {
						try
						{
							myResults = YAHOO.lang.JSON.parse(o.responseText);	
							

							if(jsObj.task == 'getElectionYearsForAState')
							{
								buildElectionYearsSelect(myResults);
							}
							
							else if(jsObj.task == "getStates")
							{
								buildElectionTypes(myResults);
							}

						}
						catch(e)
						{   
							//alert("Invalid JSON result" + e);   
						}  
				   },
				   scope : this,
				   failure : function( o ) {
								//alert( "Failed to load result" + o.status + " " + o.statusText);
							 }
				   };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
}
function buildElectionYearsSelect(myResult)
{
	if(myResult == null || myResult.length == 0)
		return;

	var electionYearsElmt = document.getElementById("electionYears");
	electionYearsElmt.options.length=0;

	for(var i in myResult)
	{
		var option = document.createElement('option');
		option.value = myResult[i].id;
		option.text = myResult[i].name;
		try
		{
			electionYearsElmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			electionYearsElmt.add(option); // IE only
		}
	}	

}
function buildElectionTypes(myResult)
{	
	
	if(myResult == null || myResult.length == 0)
		return;
	
	var electionTypeElmt = document.getElementById("states");
	electionTypeElmt.options.length=0;

	var option = document.createElement('option');
	option.value = "0";
	option.text = "Select State";
	try
	{
		electionTypeElmt.add(option,null); // standards compliant
	}
	catch(ex)
	{
		electionTypeElmt.add(option); // IE only
	}
	
	for(var i in myResult)
	{
		var option = document.createElement('option');
		option.value = myResult[i].id;
		option.text = myResult[i].name;
		try
		{
			electionTypeElmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			electionTypeElmt.add(option); // IE only
		}
	}	

	hideBusyImgWithId("states");

}

function showBusyImgWithId(elmtId)
{		
		
		var spanElmt = document.getElementById(elmtId+"_ImgSpan");
		if(spanElmt)
			spanElmt.style.display = 'block';
}
function hideBusyImgWithId(elmtId)
{
	
	var spanElmt = document.getElementById(elmtId+"_ImgSpan");
	if(spanElmt)
		spanElmt.style.display = "none";
}

function viewElectionResults()
{
   var errorMsgDivEle = document.getElementById("electionDetailsErrorMsgDiv");
   $('#processingDiv1').show();
  try
	{
	  var stateSelectEle = document.getElementById("states");
	  var electionYearEle = document.getElementById("electionYears");
	  var electionTypeEle = document.getElementById("electionTypeId");
	  

	  var stateId = stateSelectEle.options[stateSelectEle.selectedIndex].value;
	  var stateName = stateSelectEle.options[stateSelectEle.selectedIndex].text;
	  var electionTypeId = electionTypeEle.options[electionTypeEle.selectedIndex].value;
	  var electionType = electionTypeEle.options[electionTypeEle.selectedIndex].text;
	  var electionId = electionYearEle.options[electionYearEle.selectedIndex].value;
	  var electionYear = electionYearEle.options[electionYearEle.selectedIndex].text;
	if(electionType == 'Parliament')
		stateId = 1;
		if(stateId == 0 || electionTypeId == 0 || electionId == 0)
		{
			errorMsgDivEle.style.display = 'block';
			 $('#processingDiv1').hide();
			return;
		}
		
		errorMsgDivEle.style.display = 'none';
		 
		document.location = "electionDetailsReportAction.action?electionId="+electionId+"&stateID="+stateId+"&stateName="+stateName+"&electionType="+electionType+"&electionTypeId="+electionTypeId+"&year="+electionYear;
	}
	catch(err)
	{
		errorMsgDivEle.style.display = 'block';
		 $('#processingDiv1').hide();
		return;
	}
}

function openAddNewProblemWindow()
{	
	var browser_addNewProblem = window.open("<s:url action="addNewProblemAction.action"/>","addNewProblem","scrollbars=yes,height=600,width=600,left=200,top=200");
	
	browser_addNewProblem.focus();
}
<!--Facebook like code Start-->
(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) {return;}
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_US/all.js#xfbml=1";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));
<!--Facebook like code End-->


function showArticles(){

			jQuery(document).ready( function(){       
				//	jQuery("#contactLink").click( showDialog );

						//$myWindow = jQuery('#contactWindowDiv');

						//instantiate the dialog
						$("#contactWindowDiv").dialog({ stack: false,
							    height: 230,
								width: 800,
								modal: true,
								position: [170,150],
								title:'Articles',
								overlay: { opacity: 0.5, background: 'black'}
								});
						

			});	
			//function to show dialog   
			var showDialog = function() {
				$("#contactWindowDiv").show(); 
				//open the dialog
				$("#contactWindowDiv").dialog("open");
				}

			//function to close dialog, probably called by a button in the dialog
			var closeDialog = function() {
				$("#contactWindowDiv").dialog("close");
			}
			
 var elmt = document.getElementById("contactWindowDiv_window_inner");

  var str ='';

str+='<table width="100%">';
str+='<tr>';
str+='<td><img src="images/icons/homePage_new/UnderConstruction.jpg" height="100px" width="170px"></td>';
str+='<td>';
str+='<table style="margin-left:17px">';
str+='<tr>';
str+='<td><h6 style="color:red;">SORRY PAGE UNDER CONSTRUCTION</h6><br></td></tr>';
str+='<td><B>IT Grids (India) Pvt. Ltd.</B><br></td></tr>';
str+='<tr><td>Enquires: customer.servies@partyanalyst.com<br></td></tr>';
str+='<tr><td>Demo: sales@partyanalyst.com<br></td></tr>';
str+='</table>';
str+='</td>';
str+='</tr>';
str+='</table>';


elmt.innerHTML = str;
}

function showBlogs(){

			jQuery(document).ready( function(){       
				//	jQuery("#contactLink").click( showDialog );

						//$myWindow = jQuery('#contactWindowDiv');

						//instantiate the dialog
						$("#contactWindowDiv").dialog({ stack: false,
							    height: 230,
								width: 800,
								modal: true,
								position: [170,150],
								title:'Blogs',
								overlay: { opacity: 0.5, background: 'black'}
								});
						

			});	
			//function to show dialog   
			var showDialog = function() {
				$("#contactWindowDiv").show(); 
				//open the dialog
				$("#contactWindowDiv").dialog("open");
				}

			//function to close dialog, probably called by a button in the dialog
			var closeDialog = function() {
				$("#contactWindowDiv").dialog("close");
			}
			
 var elmt = document.getElementById("contactWindowDiv_window_inner");

  var str ='';

str+='<table width="100%">';
str+='<tr>';
str+='<td><img src="images/icons/homePage_new/UnderConstruction.jpg" height="100px" width="170px"></td>';
str+='<td>';
str+='<table style="margin-left:17px">';
str+='<tr>';
str+='<td><h6 style="color:red;">SORRY PAGE UNDER CONSTRUCTION</h6><br></td></tr>';
str+='<td><B>IT Grids (India) Pvt. Ltd.</B><br></td></tr>';
str+='<tr><td>Enquires: customer.servies@partyanalyst.com<br></td></tr>';
str+='<tr><td>Demo: sales@partyanalyst.com<br></td></tr>';
str+='</table>';
str+='</td>';
str+='</tr>';
str+='</table>';


elmt.innerHTML = str;
}
</script>
</body>
</html>