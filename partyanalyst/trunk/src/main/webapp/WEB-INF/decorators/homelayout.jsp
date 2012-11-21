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
	<link href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet">
	<link type="text/css" href="styles/menu.css" rel="stylesheet" />
	<link href="styles/newhome_styles.css" rel="stylesheet" type="text/css" />
		

	<!--Script file
	<script type="text/javascript" src="js/jquery.js"></script>-->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
    <script type="text/javascript" src="js/menu.js"></script>
	<script type="text/javascript" src="js/ddaccordion.js"></script>
	<script type="text/javascript" 
		src="js/commonUtilityScript/commonUtilityScript.js"></script>
	<script type="text/javascript" src="js/commonUtilityScript/regionSelect.js"></script>
	<script type="text/javascript" src="js/landingPage/landingPage.js" ></script>
	<script type="text/javascript" src="js/homePage/newhomePage.js"> </script>
	<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.24.custom.min.js"> </script>
	
	<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.core.css"/>
	<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.theme.css"/>
	<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.accordion.css"/>
	<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
	<!--YUI SCRIPT-->
	<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/connection/connection-min.js&2.8.2r1/build/datasource/datasource-min.js&2.8.2r1/build/autocomplete/autocomplete-min.js&2.8.2r1/build/element/element-min.js&2.8.2r1/build/container/container-min.js&2.8.2r1/build/menu/menu-min.js&2.8.2r1/build/button/button-min.js&2.8.2r1/build/paginator/paginator-min.js&2.8.2r1/build/datatable/datatable-min.js&2.8.2r1/build/json/json-min.js&2.8.2r1/build/tabview/tabview-min.js"></script>
	
	<style>
.background
{
background:#ffffff;
margin:-1px 0px 0px 0px ;
}

.header2 {
   left: 1%;
    position: absolute;
    top: -6px;
}
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
		</c:choose>
		<div id="floatingDiv_absolute_close" >
			<img width="13" height="14" id="floatingDiv_absolute_close_Img" onclick="javascript:{$('#floatingDiv_relative_main').hide();}" src="images/icons/homePage_new/feedback_close.jpeg" style="border: 5px solid red;
			margin-left:0;margin-top: 1px;width: 15px;">
		</div>
			
	</div>
</div>

<table style="border-collapse: collapse;" width="100%">
<tr>
<td>
<div id="header-mainsec">
  <div class="mainwrapper">
  <div class="row-fluid">
    <div class="header span10">
	<div class="row-fluid">
      <div class="logo span4 pull-left"><a href="<c:out value="${pageContext.request.contextPath}" />/homePage.action"><img src="./images/new_homepage/logo.png" alt=""/></a>
	  </div>
      <div class="header-right-sec span8 pull-right">
        <div class="lr-sec">
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
				<a href="<c:out value="${pageContext.request.contextPath}/loginInputAction.action"/>">Login</a> 
				<span>|</span>
				<a href="<c:out value="${pageContext.request.contextPath}/freeUserRegistration.action" />">Register</a>
			</c:if>
									
			<c:if test="${sessionScope.loginStatus == 'out' && (sessionScope.hasFreeUserRole == true && sessionScope.hasPartyAnalystUserRole != true)}">
                <a href="logoutAction.action">Logout</a>
			</c:if>
			
			<c:if test="${sessionScope.loginStatus == 'out' && sessionScope.hasPartyAnalystUserRole == true}">        
				<a href="logoutAction.action">Logout</a>
			</c:if>	
		</div>
<div id="menu">
    <ul class="menu">
        <li><a href="homePage.action"><span><i class="icon-home icon-white"></i></span></a>
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
								<li><a href="problemManagementReportAction.action"><span>Problem Search And Report</span></a></li>
							</ul>
						</div>
					</li>
					<li><a href="userGroupAction.action"><span>User Groups</span></a></li>
					<li><a href="callCenterAction.action"><span>Call Center</span></a></li>
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
		<li><a href="cadreManagementAction.action"><span>CADRE</span></a></li> 
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
					<a href="<c:out value="${pageContext.request.contextPath}" />/connectPeopleAction.action" >												
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
</div>
</div><!--HEADER END-->
</div>
</div><!--MAIN WRAPPER END-->
</div><!--HEADER MAIN SECTION END-->
</td>
</tr>

<tr>
<td class="background" border="0">
<div style="width:100%;float:left;">
<center>

<div class="header2">
<!--Facebook like icon-->
	<div style="float:left;margin-left:80px;margin-top:15px;" class="fb-like" data-href="http://www.facebook.com/PartyAnalyst" data-send="false" data-layout="button_count" data-width="0" data-show-faces="false">
	</div>
	<!--Facebook and twitter follow us-->
		<div class="follow-us">
            <ul>
              <li><a href="http://twitter.com/#!/party_analyst" target="_blank"><img src="./images/new_homepage/twitter.gif" alt="" height="30px" width="30px"/></a></li>
              <li><a title="Facebook" href="http://www.facebook.com/PartyAnalyst" target="_blank"><img src="./images/new_homepage/facebook.gif" alt="" height="30px" width="30px"/></a></li>
            </ul>
            <span class="fright" style="color:#fff;">follow us</span> 
		</div>
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
<tr><td>
<!--FOOTER SECTION START-->
<div class="mainwrapper"> 
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
			<li><a href="javascript:{}">Articles</a><span>|</span></li>
			<li><a href="javascript:{}" target="">Blogs</a></li>
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
				<textarea name="requirement" rows="3" cols="21" id="quickRequestReqTextbox" Value="Comment" style="height:78px;width:287px;border:1px solid #7f7f7f;background-color:#7f7f7f"  onblur="if(this.value=='')this.value=this.defaultValue;" onfocus="if(this.value==this.defaultValue)this.value='';" >Comment</textarea>
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
 
 
<!--Facebook like code Start-->
(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) {return;}
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_US/all.js#xfbml=1";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));
<!--Facebook like code End-->
</script>
</body>
</html>