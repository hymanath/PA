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
<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/combo?2.8.2r1/build/assets/skins/sam/skin.css"> 
<!-- Combo-handled YUI JS files: --> 
<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/connection/connection-min.js&2.8.2r1/build/datasource/datasource-min.js&2.8.2r1/build/autocomplete/autocomplete-min.js&2.8.2r1/build/element/element-min.js&2.8.2r1/build/container/container-min.js&2.8.2r1/build/menu/menu-min.js&2.8.2r1/build/button/button-min.js&2.8.2r1/build/paginator/paginator-min.js&2.8.2r1/build/datatable/datatable-min.js&2.8.2r1/build/json/json-min.js&2.8.2r1/build/tabview/tabview-min.js"></script> 

<link type="text/css" href="styles/menu.css" rel="stylesheet" />
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/menu.js"></script>

<script type="text/javascript" src="http://www.google.com/jsapi"></script>
<script type="text/javascript" src="js/googleAnalytics/googleAnalytics.js" ></script>

<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
<script type="text/javascript" src="js/commonUtilityScript/regionSelect.js"></script>
<script type="text/javascript" src="js/landingPage/landingPage.js" ></script>
<script type="text/javascript" src="js/homePage/homePage.js"> </script>
<script type="text/javascript" src="js/statePage/statePage.js"> </script>
<script type="text/javascript" src="js/cncSearch.js"> </script>

 <script type="text/javascript" src="js/LocationHierarchy/locationHierarchy.js"></script>   
<!-- JQuery files (Start) -->
<script type="text/javascript" src="js/jQuery/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
<script type="text/javascript" src="js/jQuery/floating-1.5.js"></script>
<script type="text/javascript" src="js/homePage/jquery.sudoSlider.min.js"></script>

<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.core.css"/>
<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.theme.css"/>
<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.accordion.css"/>
<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
<!-- JQuery files (End) -->


<link  rel="stylesheet" type="text/css" href="styles/landingPage/landingPage.css"/>
<link href="styles/home_style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="js/jcarousellite_1.0.1.pack.js"></script>
<script type="text/javascript" src="js/captify.tiny.js"></script>
<script type="text/javascript" src="js/hslider.js"></script>
<script type="text/javascript" src="js/ddaccordion.js"></script>
<script type="text/javascript" src="js/ddcontent.js"></script>
<script type="text/javascript" src="js/jquery.easing.1.2.js"></script>
<script type="text/javascript" src="js/jquery.anythingslider.js"></script>
<script type="text/javascript" src="js/bannerslider.js"></script>

<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/faviIcon.jpg">

<style>
.background
{
background:url("./images/new_homepage/js-banner-bg1.jpg");
margin:-1px 0px 0px 0px ;
}
</style>
<decorator:head/>
</head>
<body>

<div id="quickRequest_window"><div id="quickRequest_window_inner"></div></div>
	
<div id="floatingDiv_relative_main">
			<div id="floatingDiv_absolute_main">
<c:choose>

       <c:when test="${!empty feedback && feedback == 'true'}">
			
			<a href="javascript:{}" onclick="showFeedBackFormPanel()"><img width="25" height="100" style="border:0px none;" src="images/icons/homePage_new/feedback_new.jpg"/></a>
				
      </c:when>		
      <c:otherwise>
	  
      		<a href="javascript:{}" onclick="buildAccordion()"><img width="25" height="100" style="border:0px none;" src="images/icons/QuickView.png"/></a>
				
      </c:otherwise>

  	</c:choose>
	<div id="floatingDiv_absolute_close" >
					<img width="13" height="14" id="floatingDiv_absolute_close_Img" onclick="javascript:{$('#floatingDiv_relative_main').hide();}" src="images/icons/homePage_new/feedback_close.jpeg">
				</div>
</div>
		</div>

<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) {return;}
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_US/all.js#xfbml=1";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));
var feedback="<%=request.getRequestURI()%>";

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


function intialize()
{
	
	
	$('#floatingDiv_absolute_main').addFloating(  
	 {  
		 targetRight: 10,  
		 targetTop: 10,  
		 snap: true  
	 });  
}
function buildAccordion()
{
	var accordionElmtId = document.getElementById("accordion");
	
	if($("#accordion").parents(".ui-dialog").is(":visible"))
		return;
	accordionElmtId.style.display = 'block';

	getStatesInQuickView('siteSearch','stateList_s');
	getElectionTypeValue(1);
	getDistrictsComboBoxForAStateInQuickView(1, 'districtList_d');
	hideUnhideSelectBoxInQuickView('a_radio', 'constituency');
	getAllStatesHavingLocalBody("stateList_l");
	getLocalBodiesForState(1);

	$( "#accordion" ).accordion();
		$(function() {
		$( "#accordion" ).dialog({
			autoOpen: true,
			show: "blind",
			width: 350,
			minHeight:460,
			hide: "explode"
		});
	
	});
 }	
</script>

<!--HEADER SECTION START-->

<table style="border-collapse: collapse;" width="100%"><tr><td>
<div id="header-mainsec">
  <div class="mainwrapper">
    <div class="header">
      <div class="logo"><a href="<c:out value="${pageContext.request.contextPath}" />/homePage.action"><img src="./images/new_homepage/logo.png" alt=""/></a></div>
      <div class="header-right-sec">
        <div class="lr-sec">

		 <!--<c:if test="${sessionScope.loginStatus == 'out' && sessionScope.UserType == 'FreeUser'}">
                                        <c:out value="Welcome, ${sessionScope.UserName} |"/>
										<a style="float:right" href="<c:out value="${pageContext.request.contextPath}" />/logOut.jsp">Logout</a> 
								
									</c:if>
									<c:if test="${sessionScope.loginStatus == 'out' && sessionScope.UserType == 'PartyAnalyst'}">        		
										<c:out value="Welcome, ${sessionScope.UserName} | "/>
										<a style="float:right" href="<c:out value="${pageContext.request.contextPath}" />/logOut.jsp">Logout</a>
										<c:if test="${sessionScope.USER.isAdmin == 'true'}">
											<a style="float:right" href="<c:out value="${pageContext.request.contextPath}/adminUpload.action" />" >Admin</a>
										</c:if>	         		
									</c:if>		
									<c:if test="${sessionScope.loginStatus == null || sessionScope.loginStatus == 'in'}">
										<a href="<c:out value="${pageContext.request.contextPath}/loginInputAction.action"/>">Login</a> 
										
										<a href="<c:out value="${pageContext.request.contextPath}/freeUserRegistration.action" />">Register</a>
										
									</c:if>		-->

									<c:if test="${sessionScope.loginStatus == 'out' && sessionScope.UserType == 'FreeUser'}">
                                        <c:out value="Welcome, ${sessionScope.UserName} |"/></c:if>
									<c:if test="${sessionScope.loginStatus == 'out' && sessionScope.UserType == 'PartyAnalyst'}">        		
										<c:out value="Welcome, ${sessionScope.UserName} |"/></c:if>
										
									<c:if test="${sessionScope.USER.isAdmin == 'true'}">
											<a href="<c:out value="${pageContext.request.contextPath}/adminUpload.action" />" >Admin</a>
											|
										</c:if>
									<c:if test="${sessionScope.loginStatus == null || sessionScope.loginStatus == 'in'}">
										<a href="<c:out value="${pageContext.request.contextPath}/loginInputAction.action"/>">Login</a> 
										<span>|</span>
										<a href="<c:out value="${pageContext.request.contextPath}/freeUserRegistration.action" />">Register</a>
										
									</c:if>
									
									<c:if test="${sessionScope.loginStatus == 'out' && sessionScope.UserType == 'FreeUser'}">
                                       	<a style="float:right" href="<c:out value="${pageContext.request.contextPath}" />/logOut.jsp">Logout</a>
										</c:if>
								<c:if test="${sessionScope.loginStatus == 'out' && sessionScope.UserType == 'PartyAnalyst'}">        
								<a href="<c:out value="${pageContext.request.contextPath}" />/logOut.jsp">Logout</a>
								</c:if>	
		</div>
        
        
        <!--MENU SECTION START-->
        
        
<div id="menu">
    <ul class="menu">
        <li><a href="homePage.action" ><span>HOME</span></a>           
        <c:if test="${(sessionScope.loginStatus == 'out' && sessionScope.UserType == 'FreeUser') || (sessionScope.loginStatus == null || sessionScope.loginStatus == 'in')}">
        <li><a href="#" ><span>ELECTION ANALYSIS</span></a>
            <div style="z-index:1;text-align:left;"><ul>
                <li><a href="electionComparisonAction.action"><span >Elections Comparison Report</span></a>
                 
                </li>
                <li><a href="partyResultsCriteriaAction.action"><span>Party Results Report</span></a>
                    
                </li>
                <li><a href="partyStrengthAction.action"><span>Party Strenths/Weakness</span></a></li>
                            </ul></div>
        </li>
        </c:if>
		<c:if test="${sessionScope.loginStatus == 'out' && sessionScope.UserType == 'PartyAnalyst'}"> 
         <li><a href="#" class="parent"><span>ANALYSIS</span></a>
            <div  style="z-index:1;text-align:left;"><ul>
                <li><a href="#" class="parent"><span>Election Analysis</span></a>
                    <div  style="z-index:1;text-align:left;"><ul>
                        <li><a href="partyPerformanceMain.action"><span>Party Performance Report</span></a></li>
                        <li><a href="electionComparisonAction.action"><span>Elections Comparison Report</span></a></li>
                        <li><a href="partyResultsCriteriaAction.action"><span>Party Results Report</span></a></li>
						<li><a href="electionResultsAnalysisAction.action"><span>Election Results Analysis Report</span></a></li>
						<li><a href="biElectionAction.action"><span>Telengana Bye-Elections 2010</span></a></li>
						<li><a href="censusReportAction.action"><span>Elections vs Census</span></a></li>
						<li><a href="partyStrengthAction.action"><span>Party Strenths/Weakness</span></a></li>
					 </ul></div>
                </li>
                <li><a href="#" class="parent"><span>Politician Analysis</span></a>
                    <div  style="z-index:1;text-align:left;"><ul>
                        <li><a href="mandalPageSDetailAction.action"><span>Mandal Voting Report</span></a></li>
                        <li><a href="crossVotingReportInputAction.action"><span>Cross Voting Report</span></a></li>
						 <li><a href="partyBoothResultAction.action"><span>Constituency Booth Results Report</span></a></li>
                    </ul></div>
                </li>
             </ul></div>
         <li><a href="initailConstituencyManagementAction.action" class="parent"><span>CONSTITUENCY</span></a>
            <div  style="z-index:1;text-align:left;"><ul>
                <li><a href="#" class="parent"><span>Problem Management</span></a>
                    <div><ul>
                        <li><a onclick="openAddNewProblemWindow()" href="javascript:{}"><span>Add New Problem</span></a></li>
                        <li><a href="constituencyManagementAction.action?cmTask=PROBLEMS_MANAGEMENT"><span>All Problems</span></a></li>
                        <li><a href="problemManagementReportAction.action"><span>Problem Search And Report</span></a></li>
						
					 </ul></div>
                </li>
          <li><a href="userGroupAction.action"><span>User Groups</span></a></li>
        <li><a href="callCenterAction.action"><span>Call Center</span></a></li>
	
			 </ul></div>
			  <li><a href="cadreManagementAction.action"><span>CADRE</span></a>
			 </c:if>
        <li><a href="statePageAction.action?stateId=1"><span>STATES</span></a>
		 <div  style="z-index:8;text-align:left;" ><ul>
                <li><a href="statePageAction.action?stateId=1"><span>Andhra Pradesh</span></a>
                 
                </li>
                <li><a href="statePageAction.action?stateId=3"><span>Assam</span></a>
                    
                </li>
                <li><a href="statePageAction.action?stateId=12"><span>Karnataka</span></a></li>
				<li><a href="statePageAction.action?stateId=13"><span>Kerala</span></a></li>
				<li><a href="statePageAction.action?stateId=35"><span>Puducherry</span></a></li>
				<li><a href="statePageAction.action?stateId=24"><span>Tamil Nadu</span></a></li>
				<li><a href="statePageAction.action?stateId=7"><span>Gujarath</span></a></li>
				<li><a href="statePageAction.action?stateId=28"><span>West Bengal</span></a></li>
				<li><a href="statePageAction.action?stateId=26"><span>Uttaranchal</span></a></li>
				<li><a href="statePageAction.action?stateId=21"><span>Punjab</span></a></li>
                <li><a href="statePageAction.action?stateId=9"><span>Himachal Pradesh</span></a></li>
                            </ul></div>
		
		</li>
        <li class="last"><a href="searchPartyAnalystAction.action"><span>SEARCH</span></a></li>
		<c:if test="${sessionScope.loginStatus == 'out'}">  
							<li> 
								<c:if test="${sessionScope.UserType == 'PartyAnalyst'}"> 
									<a href="<c:out value="${pageContext.request.contextPath}" />/index.action" >
										<span>DASH BOARD</span>
									</a> 
								</c:if>
								<c:if test="${sessionScope.UserType == 'FreeUser'}"> 
									<a href="<c:out value="${pageContext.request.contextPath}" />/connectPeopleAction.action" >												
										<span>DASH BOARD</span>
									</a>  
								</c:if>	
							</li> 
						</c:if>	
    </ul>
</div>
        
        <!--MENU SECTION END--> 
        
      </div>
    </div>
  </div>
  
</div>


<!--HEADER SECTION END--> 
</td></tr>
<div>
<tr>
<td class="background" border="0"><div style="float:left; padding: 14px 22px 9px 121px;" class="fb-like" data-href="http://www.facebook.com/PartyAnalyst" data-send="false" data-layout="button_count" data-width="450" data-show-faces="false"></div>
<div class="follow-us">
            <ul>
              <li><a href="http://twitter.com/#!/party_analyst" target="_blank"><img src="./images/new_homepage/twitter.gif" alt=""/></a></li>
              <li><a title="Facebook" href="http://www.facebook.com/PartyAnalyst" target="_blank"><img src="./images/new_homepage/facebook.gif" alt=""/></a></li>
            </ul>
            <span class="fright">follow us on</span> </div></td>
</tr>
</div>
 <tr><td class="background" border="0">
 	
	<div id="contenttable" class="background">
			<decorator:body/>
	</div>
		
</td></tr>
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
      <li><a href="javascript:{}">Customer Support</a><span>|</span></li>
      <li><a href="javascript:{}" id="contactLink" onclick="contactLinkInHomePage()">Contact</a><span>|</span></li>
      <li><a href="javascript:{}">Sitemap</a></li>
    </ul>
    <ul>
      <li class="f-title">Connect</li>
      <li><a href="freeUserRegistration.action">Register</a><span>|</span></li>
      <li><a href="loginInputAction.action">Login</a><span>|</span></li>
      <li><a href="viewFeaturesAction.action">Explore</a><span>|</span></li>
      <li><a href="javascript:{}" onClick="contactLinkInHomePage()">Ask for DEMO/TEST</a><span>|</span></li>
      <li><a href="javascript:{}" onClick="showFeedBackFormPanel()">Feedback</a><span>|</span></li>
      <li><a href="javascript:{}">Articles</a><span>|</span></li>
      <li><a href="http://www.blog.partyanalyst.com/" target="">Blogs</a></li>
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
        <textarea name="requirement" rows="3" cols="21" id="quickRequestReqTextbox"  style="height:78px;width:297px;border:1px solid #7f7f7f;background-color:#7f7f7f" >Comment</textarea>
      </li>
      <li style="width:307px;text-align:left;">
       <input name="mobileNO" id="quickRequestMobileTextbox" type="text" class="text-fields" value="Mobile No"  onblur="if(this.value=='')this.value=this.defaultValue;" onfocus="if(this.value==this.defaultValue)this.value='';"/>
        <input name="" type="submit" value="" class="submit" onclick="submitDialogBox()"/>
      </li>
    </ul>
  </div>
  
  <!--FOOTER LEFT SECTION (FOOTER MENU) END-->
  
  <div class="clear"></div>
  
  <!--COPYRIGHT SECTION START-->
  
  <div class="copy-rights"> &copy; Copyright 2011. All rights reserved  IT GRIDS (India) Pvt. Ltd. </div>
  
  <!--COPYRIGHT SECTION END--> 
  
</div>

<!--FOOTER SECTION END-->

</td></tr>
</table>

<div id="accordion" title="Quick View" style="display:none;">
          	<h3 ><a href="#">View Your State</a></h3>
				<div style="padding:0px;">
					<div class="widgetsBody" style="background-color:#FFFFFF;color:#49443E;">
								<table>
									<tr>
										<td style="text-align:justify;padding-bottom:30px;line-height:25px;"> Select your state to view its Assembly, Parliament, Local Bodies election results. </td>
									</tr>
									<tr>
										<td style="height:40px;color:#004078"><%=stateSelect%></td>
									</tr>
									<tr>
										<td><s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state_s" id="stateList_s" list="{}" listKey="id" listValue="name" /></td>									
									</tr>								
								</table>
							</div>
							<div class="widgetsFooter" style="background-color:#FFFFFF;height:37px;">
								<img width="70" height="25" src="images/icons/homePage_new/b3.jpg" onclick="navigateToStatePage()"></img>
							</div>
						</div>
						<h3><a href="#">View Your District</a></h3>
						<div style="padding:0px;">
							<div class="widgetsBody" style="background-color:#FFFFFF;color:#49443E;">
								<div id="alertMessage_district" style="color:red;font-weight:bold;"></div>
								<table>
									<tr>
										<td style="text-align:justify;padding-bottom:5px;line-height:25px;"> Select your district to view its election results in district level. </td>
									</tr>
									<tr>
										<td style="color:#004078"><%=stateSelect%></td>
									</tr>
									<tr>
										<td style="width:200px;"><s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state" id="stateList2" list="{}" listKey="id" listValue="name" onchange="getDistrictsComboBoxForAStateInQuickView(this.options[this.selectedIndex].value,'districtList_d')"></s:select></td>
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
						<h3 ><a href="#">View Your Constituency</a></h3>
						<div style="padding:0px;">
							<div class="widgetsBody" style="background-color:#FFFFFF;color:#49443E;">
								<div id="alertMessage" style="color:red;font-weight:bold;"></div>
								<table>
									<tr>
										<td colspan="4">Select Constituency Type</td>
									</tr>	
									<tr>
										<td colspan="2"><input type="radio" checked="checked" name="a_radio" id="a_radio" onclick="hideUnhideSelectBoxInQuickView(this.id, 'constituency')"/><%=assembly%></td>
										<td><input type="radio" name="a_radio" id="p_radio" onclick="hideUnhideSelectBoxInQuickView(this.id,'constituency')"/><%=parliament%></td>
									</tr>
								</table>
								<table id="stateTable" style="display:none;">
									<tr>
										<td style="color:#004078"><%=stateSelect%></td>
									</tr>
									<tr>
										<td><s:select cssClass="selectBoxWidth" theme="simple" label="Select Your State" name="state" id="stateList_c" list="{}" listKey="id" listValue="name" onchange="getAllConstituenciesInStateByTypeInQuickView(2,this.options[this.selectedIndex].value,'constituency')"></s:select></td>
										<td><span id="constituency_ImgSpan" style="display:none;"><img src="images/icons/search.gif" /></span>
										</td>
									</tr>
								</table>
									
								
								<table id="constTable" style="display:none;">
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

					<h3 ><a href="#">View Your Locality</a></h3>
						<div style="padding:0px;">
							<div class="widgetsBody" style="background-color:#FFFFFF;color:#49443E;">
								<table>								
									<tr>
										<td style="color:#004078"><%=stateSelect%></td>
									</tr>
									<tr>
										<td><s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state_s" id="stateList_l" list="{}" listKey="id" listValue="name" onchange="getLocalBodiesForState(this.options[this.selectedIndex].value)"/></td>									
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

						<h3 ><a href="#">View Election Results</a></h3>
						<div style="padding:0px;">
							<div class="widgetsBody" style="background-color:#FFFFFF;color:#49443E;">
								<table>
									
									<tr>
										<td style="height:40px;color:#004078"><%=stateSelect%></td>
									</tr>
									<tr>
										<td><s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state_s" id="stateLists" list="{}" listKey="id" listValue="name" onchange="getElectionTypeValue((this.options[this.selectedIndex].value))"/></td>			<td><div id="stateLists_ImgSpan" style="display:none;"><img src="images/icons/search.gif" /></div></td>							
									</tr>		
									<tr><td style="height:40px;color:#004078"><%=electionTypeInHome%></td>
									</tr>
									<tr><td><select id="electionLists" class="selectBoxWidth" onchange="getElectionYearsInHomePage((this.options[this.selectedIndex].text))" ></select></td></tr>
									<tr>
									<td style="height:40px;color:#004078"><%=electionYearInHome%></td></tr>
									<tr><td><select id="electionYears" class="selectBoxWidth"></select></td></tr>
								</table>
							</div>
							<div class="widgetsFooter" style="background-color:#FFFFFF;height:37px;">
								<table width="90%"><tr>
									<td widyh="65%"><div id="electionDetailsErrorMsgDiv" style="display:none;"><font color="red"><b>*Select All Inputs</b></font></div></td>

									<td widyh="35%"><img width="70" height="25" src="images/icons/homePage_new/b3.jpg" onclick="viewElectionResults()"></img></td>
								</tr></table>
							</div>
						</div>
						
						</div>
				</div>

                
<script type="text/javascript">
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

function getDistrictsComboBoxForAStateInQuickView(value,elmtId)
{	
	
	showBusyImgWithId(elmtId);	
	
	clearOptionsListForSelectElmtId(elmtId);
	//createSelectOptionsForSelectConstituencyElmtId("districtSelectBox");
	//clearOptionsListForSelectElmtId("constituencySelectBox");
	//createSelectOptionsForSelectConstituencyElmtId("constituencySelectBox");
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
function getAllStatesHavingLocalBody(element){

	var jsObj={
		task: "statesListForLocalBodyElection",
		elmtId: element
	}
	var rparam = "task="+YAHOO.	lang.JSON.stringify(jsObj);
	var url="getAllStatesHavingLocalBody.action?"+rparam;
	callQuickViewAjax(jsObj,url);
}
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
							alert("Invalid JSON result" + e);   
						}  
				   },
				   scope : this,
				   failure : function( o ) {
								//alert( "Failed to load result" + o.status + " " + o.statusText);
							 }
				   };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

	
	intialize();

</script>

</body>
</html>
