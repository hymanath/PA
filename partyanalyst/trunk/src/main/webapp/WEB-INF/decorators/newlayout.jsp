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
<!--  BootStrap Stating and pasted 'assert' folder in webapp folder-->
<link href="styles/assets/css/bootstrap.css" rel="stylesheet">
<!--  BootStrap Ending -->
<!--  BootStrap Stating -->
 <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<!--  BootStrap Ending -->
	<link rel="apple-touch-icon-precomposed" sizes="144x144" href="styles/assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="styles/assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="styles/assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="styles/assets/ico/apple-touch-icon-57-precomposed.png">

<link type="text/css" href="styles/menu.css" rel="stylesheet" />
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/menu.js"></script>

<script type="text/javascript" src="http://www.google.com/jsapi"></script>

<script type="text/javascript" src="js/googleAnalytics/googleChartsColourPicker.js"></script>

<% if(request.getRequestURL().indexOf("partyanalyst.com") != -1){
%>
<script type="text/javascript" src="js/googleAnalytics/googleAnalytics.js"></script>
<% }
%>

<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
<script type="text/javascript" src="js/commonUtilityScript/regionSelect.js"></script>
<script type="text/javascript" src="js/landingPage/landingPage.js" ></script>
<script type="text/javascript" src="js/homePage/homePage.js"> </script>
<script type="text/javascript" src="js/statePage/statePage.js"> </script>
<script type="text/javascript" src="js/cncSearch.js"> </script>
<script type="text/javascript" src="js/loginpopup.js"> </script>
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

.header2 {
    float: none;
    padding: 0px 7px 0 0;
    width: 992px;
}
#l2s_trk img{max-width:100%;width:130px;}
</style>
<decorator:head/>
</head>
<body>
<div id="feedback_window"><div id="feedback_window_inner"></div></div>
<div id="quickRequest_window"><div id="quickRequest_window_inner"></div></div>
<div id="contactWindowDiv"><div id="contactWindowDiv_window_inner"></div></div>
	
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
	getDistrictsComboBoxForAStateInQuickView(1, 'districtList_d');
	hideUnhideSelectBoxInQuickView('a_radio', 'constituency');
	getAllStatesHavingLocalBody("stateList_l");
	getLocalBodiesForState(1);
	$("#accordion").accordion();
		//$(function() {
		$( "#accordion").dialog({
			autoOpen: true,
			show: "blind",
			width: 350,
			minHeight:460,
			hide: "explode"
		});
	
	//});
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

									<c:if test="${sessionScope.loginStatus == 'out' && (sessionScope.hasFreeUserRole == true && sessionScope.hasPartyAnalystUserRole != true)}">
                                        <c:out value="Welcome, ${sessionScope.UserName} |"/></c:if>
									<c:if test="${sessionScope.loginStatus == 'out' && sessionScope.hasPartyAnalystUserRole == true}">        		
										<c:out value="Welcome, ${sessionScope.UserName} |"/></c:if>
										
									<c:if test="${sessionScope.USER.isAdmin == 'true'}">
											<a href="<c:out value="${pageContext.request.contextPath}/adminUpload.action" />" >Admin</a>
											|
										</c:if>
									<c:if test="${sessionScope.loginStatus == null || sessionScope.loginStatus == 'in'}">
									<!--	<a href="<c:out value="${pageContext.request.contextPath}/loginInputAction.action"/>">Login</a> -->
									<a href="javascript:{}" onClick="openDialogForLoginWindow()">Login</a>
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
   								
   								<!--<a href="javascript:{}" onClick="openDialogForLoginWindow()"> new Login</a>-->
  								<div id="login_window">
									<div id="login_window_inner"></div>
  								</div>
   	 							<!-- End of LoginPoppWindow-->
		</div>
<% if(request.getRequestURL().indexOf("partyanalyst.com") != -1){
%>
        <!-- live2support.com tracking codes starts --><div id="l2s_trk" style="z-index:99;float: right; position: fixed; right: 10px;bottom:4px;"><a href="http://live2support.com" style="font-size:1px;">Live Support Software</a></div><script type="text/javascript"><!--
 var l2s_pht=escape(location.protocol); if(l2s_pht.indexOf("http")==-1) l2s_pht='http:'; var dept="0"; function l2s_load() { document.write('<scr'+'ipt type="text/javaScr'+'ipt" src="'+unescape(l2s_pht)+'//sa.live2support.com/js/lsjs1.php?stid=22424"  defer=true>'+'</scr'+'ipt>');  }
l2s_load();  document.getElementById('l2s_trk').style.visibility='hidden'; //--></script><!-- live2support.com tracking codes closed -->
  <% }
%>      
        <!--MENU SECTION START-->
        
        
<div id="menu">
    <ul class="menu">
        <li><a href="homePage.action"><span><i class="icon-home icon-white"></i></span></a>
<c:if test="${sessionScope.loginStatus == null || !sessionScope.hasPartyAnalystUserRole}">
          <li><a href="#" ><span>ANALYSIS</span></a>
            <div style="z-index:1;text-align:left;"><ul>
                <li><a href="electionComparisonAction.action"><span>Elections Comparison Report</span></a>
                 
                </li>
                <li><a href="partyResultsCriteriaAction.action"><span>Party Results Report</span></a>
                 
				</ul></div>
        </li>
		</c:if>
       	<c:if test="${sessionScope.loginStatus == 'out' && sessionScope.hasPartyAnalystUserRole == true}"> 
         <li><a id="analysisId" href="#" class="parent"><span id="analysisSpanId">ANALYSIS</span></a>
            <div  style="z-index:1;text-align:left;"><ul>
                <li><a href="#" class="parent"><span>Election Analysis</span></a>
                    <div  style="z-index:1;text-align:left;"><ul>
                        <li><a href="partyPerformanceMain.action"><span>Party Performance Report</span></a></li>
                        <li><a href="electionComparisonAction.action"><span>Elections Comparison Report</span></a></li>
                        <li><a href="partyResultsCriteriaAction.action"><span>Party Results Report</span></a></li>
						<li><a href="electionResultsAnalysisAction.action"><span>Election Results Analysis Report</span></a></li>
						<li><a href="biElectionAction.action"><span>Telengana Bye-Elections 2010</span></a></li>
						<li><a href="censusReportAction.action"><span>Elections vs Demographics</span></a></li>
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
				<li><a href="#" class="parent"><span>Live Results Analysis</span></a>
					<div  style="z-index:1;text-align:left;"><ul>
						<li><a href="electionLiveResultsAnalysisAction.action"><span>Live & Previous Results Comparison</span></a></li>
						<li><a href="ministerAnalysisAction.action"><span>Ministers & Special Candidates Analysis</span></a></li>
						<li><a href="electionResultsUpdateAction.action"><span>Update Live Election Results</span></a></li>
						<li><a href="assignKeyCandidateAction.action"><span>Assign Special Candidates</span></a></li>
						<li><a href="districtWisePartyPerformanceAction.action"><span>District Wise Party Performance</span>
						</a></li>
					</ul></div>
				</li>
             </ul></div>
         <li><a id="constituencyId" href="initailConstituencyManagementAction.action" class="parent"><span id="constituencySpanId">CONSTITUENCY</span></a>
            <div  style="z-index:1;text-align:left;"><ul>
                <li><a href="#" class="parent"><span>Problem Management</span></a>
                    <div><ul>
                        <li><a onclick="openAddNewProblemWindow()" href="javascript:{}"><span>Add New Problem</span></a></li>
                        <li><a href="constituencyManagementAction.action?cmTask=PROBLEMS_MANAGEMENT"><span>All Problems</span></a></li>
                        <li><a href="completeProblemDetailsSearchAction.action"><span>Problem Search And Report</span></a></li>
						
					 </ul></div>
                </li>
          <li><a href="userGroupAction.action"><span>User Groups</span></a></li>
        <li><a href="callCenterAction.action"><span>Call Center</span></a></li>
		<li><a href="votersAnalysisNewAction.action"><span>Constituency Analysis</span></a></li>
		<li><a href="votersSearchAction.action"><span>Voters Search & Report</span></a></li>
	
	 </ul></div>

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
	<li><a id="cadreId" href="cadreManagementAction.action"><span id="cadreSpanId">CADRE</span></a>
	</li> 
	</c:if>

<c:if test="${sessionScope.loginStatus == null ||sessionScope.loginStatus == 'in' || (sessionScope.hasFreeUserRole == true && sessionScope.hasPartyAnalystUserRole != true)}">
       <li><a  href="statePageAction.action?stateId=1">
		<span>STATES</span></a>
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
            <div style="z-index:1;text-align:left;"><ul>
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
							<a id="dashBoardId" href="<c:out value="${pageContext.request.contextPath}" />/userProfile.action" >
										<span id="dashBoardSpanId" >DASHBOARD</span>
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

        
        <!--MENU SECTION END--> 
        
      </div>
    </div>
  </div>
  
</div>

<!--HEADER SECTION END--> 
</td></tr>
<div>
<tr>
<td class="background" border="0">
<div style="width:100%;float:left;">
<center>
<div class="header2">
<div style="float:left;margin-left:80px;margin-top:15px;" class="fb-like" data-href="http://www.facebook.com/PartyAnalyst" data-send="false" data-layout="button_count" data-width="0" data-show-faces="false"></div>
<div class="follow-us">
            <ul>
              <li><a href="http://twitter.com/#!/partyanalyst" target="_blank"><img src="./images/new_homepage/twitter.gif" alt=""/></a></li>
              <li><a title="Facebook" href="http://www.facebook.com/PartyAnalyst" target="_blank"><img src="./images/new_homepage/facebook.gif" alt=""/></a></li>
            </ul>
            <span class="fright">follow us on</span> </div>
</div>
</center>
</div>
</td>
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
        <textarea name="requirement" rows="3" cols="21" id="quickRequestReqTextbox" Value="Comment" style="height:78px;width:287px;border:1px solid #7f7f7f;background-color:#7f7f7f"  onblur="if(this.value=='')this.value=this.defaultValue;" onfocus="if(this.value==this.defaultValue)this.value='';" >Comment</textarea>
      </li>
      <li style="width:307px;text-align:left;">
       <input name="mobileNO" id="quickRequestMobileTextbox" type="text" class="text-fields" value="Mobile No"  onblur="if(this.value=='')this.value=this.defaultValue;" onfocus="if(this.value==this.defaultValue)this.value='';"/>
        <!--<button name="submitbtn" class="btn btn-danger span1 pull-right" value="Submit" onclick="submitDialogBox()"/>-->
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

<!--FOOTER SECTION END-->

</td></tr>
</table>

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
									
									<!--<tr>
										<td style="color:#004078"><%=stateSelect%></td>
									</tr>
									<tr>
										<td style="font-size:12px;"><s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state_s" id="stateLists" list="{}" listKey="id" listValue="name" onchange="getElectionTypeValue((this.options[this.selectedIndex].value))"/></td>			<td><div id="stateLists_ImgSpan" style="display:none;"><img src="images/icons/search.gif" /></div></td>							
									</tr>		
									<tr><td style="color:#004078"><%=electionTypeInHome%></td>
									</tr>
									<tr><td><select id="electionLists" class="selectBoxWidth" onchange="getElectionYearsInHomePage((this.options[this.selectedIndex].text))" ></select></td></tr>
									<tr>
									<td style="color:#004078"><%=electionYearInHome%></td></tr>
									<tr><td><select id="electionYears" class="selectBoxWidth"></select></td></tr>-->

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

function openAddNewProblemWindow()
{	
	var browser_addNewProblem = window.open("<s:url action="addNewProblemAction.action"/>","addNewProblem","scrollbars=yes,height=600,width=600,left=200,top=200");
	
	browser_addNewProblem.focus();
}

function shareInFacebook(urlStr)
{	
	var url = encodeURL(urlStr);
	var shareInFacebook_window = window.open("http://www.facebook.com/sharer/sharer.php?app_id=309437425817038&sdk=joey&u="+url+"&display=popup&src=sp","Share In Facebbok","scrollbars=no,height=400,width=650,left=0,top=0");
	shareInFacebook_window.focus();
}

function encodeURL(urlStr)
{
	var url = $.trim(urlStr);
	if(url.length > 0)
	{
		url = url.replace('&','%26');
		return url;
	}
	else
		return;
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
	intialize();

</script>

</body>
</html>
