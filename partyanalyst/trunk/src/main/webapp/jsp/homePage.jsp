
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">

<title>Party Analyst - An Election Analysis &amp; Political Management Platform</title>

<meta http-equiv="Content-Language" content="en">

<meta name="description" content="Party Analyst is a complete Indian Election Analysis Platform that also offers, Constituency, Cadre Management to Indian Political Parties and Politicians.">

<meta name="keywords" content="Indian Elections, Election Analysis, Indian Democracy, Andhra Pradesh Politics, Indian Political Parties, Indian Politicians, Indian Leaders, Congress, BJP, TDP, TRS, Indian Election Commission, Know Analyze, Act, MLA Elections, MP Elections, Cross Voting, District Election Results, MPTC Elections, ZPTC Elections, Constituency Management, Cadre Management, Party Performance, Election Comparison, Municipal Elections, Corporation Elections">

<meta name="copyright" content="IT Grids (India) Pvt. Ltd.">

<meta name="author" content="Ashok Dakavaram">

<meta name="email" content="info@itgrids.com">

<meta name="Charset" content="ISO-8859-1">

<meta name="Distribution" content="Global">

<meta name="Rating" content="General">

<meta name="ROBOTS" content="INDEX,FOLLOW">

<meta name="Revisit-after" content="1 Day">

<meta name="expires" content="Never">


<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/faviIcon.jpg">


<!-- Combo-handled YUI CSS files: --> 
<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/combo?2.8.2r1/build/assets/skins/sam/skin.css"> 
<!-- Combo-handled YUI JS files: --> 
<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/connection/connection-min.js&2.8.2r1/build/datasource/datasource-min.js&2.8.2r1/build/autocomplete/autocomplete-min.js&2.8.2r1/build/element/element-min.js&2.8.2r1/build/container/container-min.js&2.8.2r1/build/menu/menu-min.js&2.8.2r1/build/button/button-min.js&2.8.2r1/build/paginator/paginator-min.js&2.8.2r1/build/datatable/datatable-min.js&2.8.2r1/build/json/json-min.js&2.8.2r1/build/tabview/tabview-min.js"></script> 


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

<link  rel="stylesheet" type="text/css" href="styles/homePage/jquerySlider.css"/>

<!-- JQuery files (End) -->

<link  rel="stylesheet" type="text/css" href="styles/landingPage/landingPage.css"/>

<script type="text/javascript">
var uname = '${sessionScope.USER.userName}';
var emailId='${sessionScope.USER.email}';
var changedUserName='${sessionScope.changedUserName}';
function callAJAX(jsObj,url){
	var results;	
	var callback = {			
	    success : function( o ) {
			try {							
				"",					
					results = YAHOO.lang.JSON.parse(o.responseText);		
					if(jsObj.task == "changeAnanymousUserNameToEmail")
					{
						showDetails(results);
					}
					if(jsObj.task == "checkAnanymousUserNameAvailability")
				{ 
                        showDetailsForAvailability(results);

				}
			}catch (e) {   		
			   	alert("Invalid JSON result" + e);   
			}  
	    },
	    scope : this,
	    failure : function( o ) {
	     			//alert( "Failed to load result" + o.status + " " + o.statusText);//
	              }
	    };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
	}


google.load("elements", "1", {packages : ["newsshow"]});

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
var errotMsg = '<%=errorMsg%>';
localBodyString = '<%=localBody%>';
var loginStatus = '${loginStatus}';

//News Answers
var new1="Politician Message";
var new2="Election Message";
//

	<c:forEach var="state" items="${statesList}" >
		var obj={
					id:"${state.id}",
					name:"${state.name}"				
				};
		statesInCountryObj.push(obj);	
	</c:forEach>

	YAHOO.util.Event.onContentReady("navigationHead", function () { 	 
	    
	    var oMenuBar = new YAHOO.widget.MenuBar("navigationHead", {  
	                                                autosubmenudisplay: true,  
	                                                hidedelay: 100,  
	                                                lazyload: true }); 
	
		 
		oMenuBar.subscribe("beforeRender", function () { 
	 
	    if (this.getRoot() == this) { 	 
			this.getItem(0).cfg.setProperty("submenu", aSubmenuData[0]); 
			this.getItem(1).cfg.setProperty("submenu", aSubmenuData[1]); 
			this.getItem(2).cfg.setProperty("submenu", aSubmenuData[2]); 
			this.getItem(3).cfg.setProperty("submenu", aSubmenuData[3]); 
			this.getItem(4).cfg.setProperty("submenu", aSubmenuData[4]); 
			this.getItem(5).cfg.setProperty("submenu", aSubmenuData[5]);
			this.getItem(6).cfg.setProperty("submenu", aSubmenuData[6]);
	    } 
	 
	}); 
	oMenuBar.render();  
	 
	}); 
	<c:if test="${sessionScope.loginStatus == 'out' && sessionScope.UserType == 'PartyAnalyst'}"> 	
		var aSubmenuData = [ 
		{
			 id: "home", 
	        itemdata: [ 				
	   	          
	
	        ] 
		},	 
	    { 
	        id: "partyanalysis",  
	        itemdata: [  
	            { text: "Party Performance Report", url: "partyPerformanceMain.action" }, 	            
	            { text: "Elections Comparison Report", url: "electionComparisonAction.action" },
	            { text: "Party Results Report", url: "partyResultsCriteriaAction.action" },	 
	            { text: "Election Results Analysis Report", url:"electionResultsAnalysisAction.action"},
	            { text: "Telangana Bye-Elections 2010", url: "biElectionAction.action" },
				{ text: "Elections Vs Census", url: "censusReportAction.action" },
				{ text: "Party Strengths/Weakness", url: "partyStrengthAction.action" }                             
	        ] 
	    }, 
	    { 
	        id: "cadreManagement", 
	        itemdata: [ 				
	   	          
	
	        ] 
	    },  
		{ 
	       id: "constituencyManagement",
	        itemdata: [
		    { text: "Problem Management",
			    submenu : {
				     id  : "ProblemManagement",
				itemdata : [
					{text: "Add New Problem", onclick: { fn: openAddNewProblemWindow }, keylistener: { ctrl: true, keys: 80 }},
					{text: "All Problems", url: "constituencyManagementAction.action?cmTask=PROBLEMS_MANAGEMENT"},
					{text: "Problem Search & Report", url: "problemManagementReportAction.action"},
				]
				}
			},
		    { text: "User Groups", url: "userGroupAction.action" },
		    { text: "Constituency Election Report", url: "constituencyElectionReportAction.action" }
	        ] 
	    },   
	    { 
	        id: "politicianAnalysis",  
	        itemdata: [ 
		    { text: "Mandal Voting Report", url: "mandalPageSDetailAction.action" },
		    { text: "Cross Voting Report", url: "crossVotingReportInputAction.action" },				
	            { text: "Constituency Booth Results Report", url: "partyBoothResultAction.action" }						
	        ]  
	    },	 
	    { 
	        id: "staticData",  
	        itemdata: [ 
	            { text: "Andhra Pradesh", url: "statePageAction.action?stateId=1" },
				{ text: "Assam", url: "statePageAction.action?stateId=3" },
	            { text: "Karnataka", url: "statePageAction.action?stateId=12" },
				{ text: "Kerala", url: "statePageAction.action?stateId=13" },
				{ text: "Puducherry", url: "statePageAction.action?stateId=35" },
				{ text: "Tamil Nadu", url: "statePageAction.action?stateId=24" },
	            { text: "West Bengal", url: "statePageAction.action?stateId=28" },
				{ text: "Gujarat", url: "statePageAction.action?stateId=7" }

	        ]     
	    }, 
	    { 
	        id: "search", 
	        itemdata: [
	        ] 
	    }  
	     
	     
		]; 
	</c:if>
	<c:if test="${(sessionScope.loginStatus == 'out' && sessionScope.UserType == 'FreeUser') || (sessionScope.loginStatus == null || sessionScope.loginStatus == 'in')}">
		var aSubmenuData = [ 
					{
						 id: "home", 
						itemdata: [ 				
							  
	
						] 
					},
					{ 
						id: "partyanalysis",  
				        itemdata: [	            
				            { text: "Elections Comparison Report", url: "electionComparisonAction.action" },
				            { text: "Party Results Report", url: "partyResultsCriteriaAction.action" },
							{ text: "Party Strengths/Weakness", url: "partyStrengthAction.action" }  	                       
				        ]    
					},
					{ 
						id: "staticData",  
						itemdata: [ 
						{ text: "Andhra Pradesh", url: "statePageAction.action?stateId=1" },
						{ text: "Assam", url: "statePageAction.action?stateId=3" },
						{ text: "Karnataka", url: "statePageAction.action?stateId=12" },
						{ text: "Kerala", url: "statePageAction.action?stateId=13" },
						{ text: "Puducherry", url: "statePageAction.action?stateId=35" },
						{ text: "Tamil Nadu", url: "statePageAction.action?stateId=24" },
						{ text: "West Bengal", url: "statePageAction.action?stateId=28" },
						{ text: "Gujarat", url: "statePageAction.action?stateId=7" }
						]     
					}, 
					{ 
						id: "search", 
						itemdata: [
						] 
					}  					 
				]; 
	</c:if>
	
function openAddNewProblemWindow()
{	
	var browser_addNewProblem = window.open("<s:url action="addNewProblemAction.action"/>","addNewProblem","scrollbars=yes,height=600,width=600,left=200,top=200");
	
	browser_addNewProblem.focus();
}
</script>
</head>
<body>
    <div id="username_change_window" style="background-color: #C7CFD2;">
	<div id="username_change_window_inner"></div></div>
	<div id="loginPopupDivMain" class="yui-skin-sam"><div id="loginPopupDiv"></div></div>
	<div class="yui-skin-sam"><div id="electionResultsPopupDiv_inner"></div></div>
	<div id="knowMore_window"><div id="knowMore_window_inner"></div></div>
	<div id="feedback_window"><div id="feedback_window_inner"></div></div>
	<div id="assembly_2011_window"><div id="assembly_2011_window_inner"></div></div>
	<div id="quickRequest_window"><div id="quickRequest_window_inner"></div></div>
	<div id="contactWindowDiv"><div id="contactWindowDiv_window_inner"></div></div>
	<div id="homePageContainer" style="overflow:visible;">
		<div id="floatingDiv_relative_main">
			<div id="floatingDiv_absolute_main">				
				<a href="javascript:{}" onclick="showFeedBackFormPanel()"><img width="25" height="100" style="border:0px none;" src="images/icons/homePage_new/feedback_new.jpg"/></a>
				<div id="floatingDiv_absolute_close" >
					<img width="13" height="14" id="floatingDiv_absolute_close_Img" onclick="javascript:{$('#floatingDiv_relative_main').hide();}" src="images/icons/homePage_new/feedback_close.jpeg">
				</div>
			</div>
		</div>
		<div id="indexheader" class="indexLayoutContainer" style="overflow:visible;background-image:url('images/icons/homePage_new/headerBG_betanew.jpg');height:125px;">
            <table  width="100%" id="headerTable">
                <tr>
                    <td style="vertical-align:top;width:190px;">
                        <div id="pa_Logo" style="padding-left: 10px; padding-top: 10px;">
                        	
                        </div>
                    </td>
                    
                  <td style="padding-left:10px;"></td>
					  <td style="padding-left:125px;padding-top:5px;"></td>
				<td style="vertical-align:top;">
                        <table width="100%" style="width:100%">                           
                            <tr>
                                <th id="searchBox" style="color:#FFFFFF">
                                
                                    <c:if test="${sessionScope.loginStatus == 'out' && sessionScope.UserType == 'FreeUser'}">
                                        <c:out value="Welcome, ${sessionScope.UserName} | "/>
										<a class="loginStatusAnc" style="color:#FFFFFF" href="<c:out value="${pageContext.request.contextPath}" />/logOut.jsp">Logout</a> 
								
									</c:if>
									<c:if test="${sessionScope.loginStatus == 'out' && sessionScope.UserType == 'PartyAnalyst'}">        		
										<c:out value="Welcome, ${sessionScope.UserName} | "/>
										<a class="loginStatusAnc" style="color:#FFFFFF" href="<c:out value="${pageContext.request.contextPath}" />/logOut.jsp">Logout</a>
										<c:if test="${sessionScope.USER.isAdmin == 'true'}"> | 
											<a class="loginStatusAnc" style="color:#FFFFFF" href="<c:out value="${pageContext.request.contextPath}/adminUpload.action" />" >Admin</a>
										</c:if>	         		
									</c:if>		
									<c:if test="${sessionScope.loginStatus == null || sessionScope.loginStatus == 'in'}">
										<a class="loginStatusAnc" style="color:#FFFFFF" href="<c:out value="${pageContext.request.contextPath}" />/loginInputAction.action" >Login</a> |
										<a class="loginStatusAnc" style="color:#FFFFFF" href="<c:out value="${pageContext.request.contextPath}/freeUserRegistration.action" />" >Register</a>
										<!-- | 
										<a class="loginStatusAnc" style="color:#FFFFFF" href="<c:out value="${pageContext.request.contextPath}/adminUpload.action" />" >Admin</a> -->
									</c:if>		
                                </th>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
			<table><tr>
			<td style="padding-left:10px;"></td>
					<td style="padding-left:450px;padding-top:150px;padding-right:231px"></td>
					<td style="padding-left:87px;padding-top:-9px"><a title="Facebook" href="http://www.facebook.com/share.php?u=http%3A%2F%2Fpartyanalyst.com%2Fhomepage.action&amp;t=to%20know%20%20Analyse%20Act%20for%20Politics" target="_blank" rel="nofollow"><img class="sociable-hovers" style="padding-right:px;padding-top:3px;height:20px;background: url('images/icons/homePage/facebook.JPG') no-repeat scroll -343px -1px transparent;"align="right" alt="Facebook" title="share this on Facebook" src="images/icons/homePage/facebook.JPG"></a>
					</td>
					
					<td style="padding-left:10px;padding-top:5px;">
				<a href="http://twitter.com/share" class="twitter-share-button" data-url="http://www.partyanalyst.com" data-count="none"></a><script type="text/javascript" src="http://platform.twitter.com/widgets.js"></script></td>
				
				
				<td style="padding-left:10px;"><a title="LinkedIn" href="http://www.linkedin.com/shareArticle?mini=true&url=http%3A//partyanalyst.com&title=PartyAnalyst&#45An Election%20Analysis%20Political%20Management%20Platform%20&source=Know%20Analyse%20Act
" target="_blank"><img class="sociable-hovers" style="padding-right:px;padding-top:3px;height:20px;background: url('images/icons/homePage/LinkedIn.JPG') no-repeat scroll -343px -1px transparent;"align="right" alt="LinkedIn"  title="share this on LinkedIn" src="images/icons/homePage/Linkedin.JPG"></a>
					</td>
			</tr></table>
        </div>	
		
		<!-- Note Data Div (Start)
		<div id="noteDataDiv">
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td width="5%">
						<div id="noteDataDiv_head"><span id="noteDataDiv_head_span">Note</span></div>
					</td>
					<td width="95%">
						<div id="noteDataDiv_body">
							<marquee onmouseover="this.stop()" onmouseout="this.start()" scrollamount="4">
								This product is not ready to use presently and it will be very soon.The users registered presently may lose their login credentials. 
							</marquee>
						</div>
					</td>
				</tr>
			</table>
		</div>		
		 Note Data Div (End)-->

        <div id="indexNavContainer"  class="indexLayoutContainer yui-skin-sam">
			<div id="navigationHead" class="yuimenubar yuimenubarnav"> 
				<div class="bd"> 
					<ul class="first-of-type"> 
						<li class="yuimenubaritem"> 
							<a class="yuimenubaritemlabel" href="<c:out value="${pageContext.request.contextPath}" />/homePage.action" >
								<!--<img id="indexHomeImg" width="28" height="25" src="images/icons/indexPage/pa_home.png" title="home"/>-->
								HOME
							</a> 
						</li>
						<c:if test="${sessionScope.loginStatus == 'out' && sessionScope.UserType == 'PartyAnalyst'}">  
							<li class="yuimenubaritem"> 
								<a class="yuimenubaritemlabel" href="javascript:{}">PARTY ANALYSIS</a> 
							</li> 
							<li class="yuimenubaritem"> 
								<a class="yuimenubaritemlabel" href="cadreManagementAction.action">CADRE</a> 
							</li> 
							<li class="yuimenubaritem"> 
								<a class="yuimenubaritemlabel" href="initailConstituencyManagementAction.action">CONSTITUENCY MANAGEMENT</a> 
							</li> 
						</c:if>
						
						<li class="yuimenubaritem"> 
							<a class="yuimenubaritemlabel" href="javascript:{}">ELECTION ANALYSIS</a> 
						</li>
						<li class="yuimenubaritem"> 
							<a class="yuimenubaritemlabel" href="statePageAction.action?stateId=1">STATES</a> 
						</li>
						<li class="yuimenubaritem"> 
							<a class="yuimenubaritemlabel" href="searchPartyAnalystAction.action">SEARCH</a> 
						</li> 
						
						<c:if test="${sessionScope.loginStatus == 'out'}">  
							<li class="yuimenubaritem"> 
								<c:if test="${sessionScope.UserType == 'PartyAnalyst'}"> 
									<a class="yuimenubaritemlabel" href="<c:out value="${pageContext.request.contextPath}" />/index.action" >
										DASH BOARD
									</a> 
								</c:if>
								<c:if test="${sessionScope.UserType == 'FreeUser'}"> 
									<a class="yuimenubaritemlabel" href="<c:out value="${pageContext.request.contextPath}" />/connectPeopleAction.action" >												
										DASH BOARD
									</a>  
								</c:if>	
							</li> 
						</c:if>
					</ul> 
				</div> 
			</div> 
		</div>
		
       <!-- <div id="homePage_header">
        	<img height="180" width="960" src="images/icons/homePage_new/homePage_header.jpg"/>
        </div> -->
        <div id="homePageContent_main" style="height:auto;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<td width="70%" valign="top">
					<div id="homePage_Image_Header_main" style="height:417px;overflow:hidden;">
						<div id="homePage_Image_Header" height="430px">
							<div style="padding:325px 30px 10px 0;text-align:right;">
								<img style="cursor:pointer" onclick="openKnowMoreWindow()" width="140" height="30" src="images/icons/homePage_new/learn_more.jpeg">
							</div>
						</div>					
					</div>
				</td>
				<td width="30%" valign="top">
					
					<div id="accordion">
						<h3><a href="#">View Your State</a></h3>
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
										<td><s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state_s" id="stateList_s" list="statesList" listKey="id" listValue="name" onchange="setStateValue()"/></td>									
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
										<td width="200px"><s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state" id="stateList_d" list="statesList" listKey="id" listValue="name" onchange="getDistrictsComboBoxForAState(this.options[this.selectedIndex].value,'districtList_d')"></s:select></td>
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
						<h3><a href="#">View Your Constituency</a></h3>
						<div style="padding:0px;">
							<div class="widgetsBody" style="background-color:#FFFFFF;color:#49443E;">
								<div id="alertMessage" style="color:red;font-weight:bold;"></div>
								<table>
									<tr>
										<td colspan="4">Select Constituency Type</td>
									</tr>	
									<tr>
										<td colspan="2"><input type="radio" checked="checked" name="a_radio" id="a_radio" onclick="hideUnhideSelectBox(this.id, 'constituency')"/><%=assembly%></td>
										<td><input type="radio" name="a_radio" id="p_radio" onclick="hideUnhideSelectBox(this.id,'constituency')"/><%=parliament%></td>
									</tr>
								</table>
								<table id="stateTable" style="display:none;">
									<tr>
										<td style="color:#004078"><%=stateSelect%></td>
									</tr>
									<tr>
										<td><s:select cssClass="selectBoxWidth" theme="simple" label="Select Your State" name="state" id="stateList_c" list="statesList" listKey="id" listValue="name" onchange="getAllConstituenciesInStateByType(2,this.options[this.selectedIndex].value,'constituency')"/>
										</td>
										<td><div id="constituency_ImgSpan" style="display:none;"><img src="images/icons/search.gif" /></div></td>	
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
									
						<h3><a href="#">View Your Locality</a></h3>
						<div style="padding:0px;">
							<div class="widgetsBody" style="background-color:#FFFFFF;color:#49443E;">
								<table>								
									<tr>
										<td style="color:#004078"><%=stateSelect%></td>
									</tr>
									<tr>
										<td><s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state_s" id="stateList_l" list="statesListForLocalBodyElection" listKey="id" listValue="name" onchange="getLocalBodiesForState(this.options[this.selectedIndex].value)"/></td>									
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
					 <h3><a href="#">View Election Results</a></h3>
						<div style="padding:0px;">
							<div class="widgetsBody" style="background-color:#FFFFFF;color:#49443E;">
							  <table>
									<td width="65%"><div id="electionDetailsErrorMsgDiv" style="display:none;"><font color="red"><b>*Select All Inputs</b></font></div></td>
									<tr>
										<td style="height:40px;color:#004078"><%=stateSelect%></td>
									</tr>
									<tr>
										<td><s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state_s" id="stateLists" list="statesList" listKey="id" listValue="name" onchange="getElectionTypeValue((this.options[this.selectedIndex].value))"/></td><td><div id="stateLists_ImgSpan" style="display:none;"><img src="images/icons/search.gif" /></div></td>			
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
								<img width="70" height="25" src="images/icons/homePage_new/b3.jpg" onclick="viewElectionResults()"></img>
							</div>
						</div>

					</div>
				</td>
			  </tr>
			</table>


			
			<!-- =================
			New Layout Start				
			====================-->

			<div id="homePageContent1"> 
				<table width="100%">
					<tr>
						<td width="75%" valign="top">
							<div class="homePageContentWidget_main">
								<div class="homePageContentWidget_head">
									<table cellpadding="0" cellspacing="0">
										<tr>
											<td width="20px" valign="top">
												<img width="20" height="45" src="images/icons/homePage_new/homeWidgetImageHeader_left.jpg"/>
											</td>	
											<td width="85px" valign="top">
												<div class="homePageContentWidget_head_center_div" style="width:86px;">
													<span class="homePageContentWidget_head_center_span">What's in</span>
												</div>
											</td>	
											<td width="20px" valign="top">
												<img width="20" height="45" src="images/icons/homePage_new/homeWidgetImageHeader_right.jpg"/>
											</td>
									<!---		<td width="100%">
											    <marquee scrollDelay="180"><font class="scrollDataStyle"><u>News</u> :<font><a href="javascript:{}" class="scrollDataStyle" onclick="openMediaOpenionsWindow()">Party Analyst Officially Launched - READ the AMAZING reviews from National & International Media.</a>
												<font class="scrollDataStyle">
												<u>New Feature </u>: <font> Now you can view your Leaders Education details, Assets, Liabilities and more details in your Constituency page.</font>
											   </marquee>
											</td>   -->
											
											<td width="100%">
											    <marquee scrollDelay="180">
											    <font class="scrollDataStyle"/><u>News</u> :<font><a href="javascript:{}" class="scrollDataStyle" onclick="openAssembly2011Window()">Pulivendula Bi Election 2011 Results</a> , <a href="javascript:{}" class="scrollDataStyle" onclick="openKadapa2011Window()">Kadapa Bi Election 2011 Results</a> , <a href="javascript:{}" class="scrollDataStyle" onclick="openTN2011Window()">Tamil Nadu Assembly Election 2011 Results</a> , <a href="javascript:{}" class="scrollDataStyle" onclick="openWB2011Window()">West Bengal Assembly Election 2011 Results</a> , <a href="javascript:{}" class="scrollDataStyle" onclick="openKerala2011Window()">Kerala Assembly Election 2011 Results</a> , <a href="javascript:{}" class="scrollDataStyle" onclick="openAssam2011Window()">Assam Assembly Election 2011 Results</a> , <a href="javascript:{}" class="scrollDataStyle" onclick="openPD2011Window()">Puducherry Assembly Election 2011 Results</a>
												</font>
											   </marquee>
											</td>
										</tr>
										
									</table>
								</div>
								<div class="homePageContentWidget_body" style="height:400px">
									<table width="100%">
										<tr>
											<td width="33%">
												<div class="homePageContentWidget_body_news_main">
													<div class="homePageContentWidget_body_news_head">
														<table cellspacing="0" cellpadding="0" border="0" width="100%">
														<!--<tr>
														<td width="3px">
															<img src="images/icons/electionResultsAnalysisReport/first.png">
														</td>-->
														<td align="left">
															<img width="200" height="70" style="border:none;" src="images/icons/homePage_new/freeuser.jpg"/>
								
															<!-- <div class="homePageContentWidget_body_news_head_div">
																<span class="homePageContentWidget_body_news_head_span">Free Users</span>
															</div> -->
														</td>
														<!--<td width="3px">
															<img src="images/icons/electionResultsAnalysisReport/second.png">
														</td>-->
														</tr>
														</table>														
													</div>
													<div class="homePageContentWidget_body_news_body">
														All free users can access the website with following features:
														<ul class="homePageContentWidget_body_news_list">
															<li>View comments posted by registered users</li>
															<li>View all election results</li>
															<li>View your Political Leader&#39;s profile</li>
															<li>View all your constituency problems</li>
															<li>The features access to free users are restricted</li>
															<li>Please register to access the advanced features and benefit from it</li>
								<div>
								<center><a href="/PartyAnalyst/freeUserRegistration.action"><img width="153" height="38" style="border: 0px none;" src="images/icons/homePage_new/banner_register_now2.png"></img></a> </center>
							   </div>
														</ul>
													</div>
													<div class="homePageContentWidget_body_news_footer">
														
													</div>
												</div>
											</td>
											<td width="33%">
												<div class="homePageContentWidget_body_news_main">
													<div class="homePageContentWidget_body_news_head">
														<table cellspacing="0" cellpadding="0" border="0" width="100%">
														<tr>
														<!--<td width="3px">
															<img src="images/icons/electionResultsAnalysisReport/first.png">
														</td>-->
														<td align="left">
															<img width="200" height="70" style="border:none;" src="images/icons/homePage_new/registeruser.jpg"/>
															<!--<div class="homePageContentWidget_body_news_head_div">
																<span class="homePageContentWidget_body_news_head_span">Registered Users</span>
															</div>-->
														</td>
														<!--<td width="3px">
															<img src="images/icons/electionResultsAnalysisReport/second.png">
														</td>-->
														</tr>
														</table>														
													</div>
													<div class="homePageContentWidget_body_news_body">
														All users must REGISTER to access the website, and after logging with user name / password, they can:
														<ul class="homePageContentWidget_body_news_list">
															<li>Post a problem of their constituency</li>
															<li>View all posted comments</li>
															<li>View replies to their comments</li>
															<li>Connect, Interact and communicate with the locality people and leaders</li>
															<li>View additional election analysis report</li>
														</ul>
							   <div>
								<center><a href="/PartyAnalyst/freeUserRegistration.action"><img width="153" height="38" style="border: 0px none; margin-top:10px;" src="images/icons/homePage_new/banner_register_now2.png"></img> </a><center>
							   </div>
													</div>
							
													<div class="homePageContentWidget_body_news_footer">
														
													</div>
												</div>
											</td>
											<td width="33%">
												<div class="homePageContentWidget_body_news_main" style="border:0px;">
													<div class="homePageContentWidget_body_news_head">
														<table cellspacing="0" cellpadding="0" border="0" width="100%">
														<tr>
														<!--<td width="3px">
															<img src="images/icons/electionResultsAnalysisReport/first.png">
														</td>-->
														<td align="left">
															<img width="200" height="70" style="border:none;" src="images/icons/homePage_new/commercialuser.jpg"/>
															<!--<div class="homePageContentWidget_body_news_head_div">
																<span class="homePageContentWidget_body_news_head_span">Commercial Users</span>
															</div>-->
														</td>
														<!--<td width="3px">
															<img src="images/icons/electionResultsAnalysisReport/second.png">
														</td>-->
														</tr>
														</table>														
													</div>
													<div class="homePageContentWidget_body_news_body">
														The commercial users basically are the Parties, Political Leaders, and Press & Media Houses with:
														<ul class="homePageContentWidget_body_news_list">
															<li>Election Analysis based on demographics, geographic</li>
															<li>Different tools to enhance communication channels with Cadre and Influencing Groups/People</li>
															<li>Problem Management tools to manage people problems effectively</li>
														</ul>	
														<div style="text-align: right;">
															<a href="viewFeaturesAction.action"><img height="21" width="94" src="images/icons/homePage_new/viewfeatures.jpg"></img></a>
														</div>
															<!--<li>Lot more functionalities and Excellent Customer Support</li>-->
														For a FREE DEMO and TEST LOGIN -->
															<div style="text-align: right;">
															<a href="javascript:{}" id="contactLink" onclick="contactLinkInHomePage()"><img height="21" width="94" src="images/icons/homePage_new/contactus.jpg"></img></a>
														</div>
														
														
													</div>
													<div class="homePageContentWidget_body_news_footer">
														
													</div>
												</div>
											</td>
										</tr>
									</table>
								</div>
								<div class="homePageContentWidget_footer">

								</div>
							</div>
						</td>
						<td width="25%" valign="top">
							<div class="homePageContentWidget_main">
								<div class="homePageContentWidget_head">
									<table width="92%" cellpadding="0" cellspacing="0">
										<tr>
											<td width="20px" valign="top">
												<img width="20" height="45" src="images/icons/homePage_new/homeWidgetImageHeader_left.jpg"/>
											</td>	
											<td valign="top">
												<div class="homePageContentWidget_head_center_div">
													<span class="homePageContentWidget_head_center_span">Join PartyAnalyst</span>
												</div>
											</td>	
											<td width="20px" valign="top">
												<img width="20" height="45" src="images/icons/homePage_new/homeWidgetImageHeader_right.jpg"/>
											</td>
										</tr>
									</table>
								</div>
								<div id="homePageContentWidget_body_questions" class="homePageContentWidget_body" style="height:400px;">
								<iframe scrolling="no" frameborder="0" allowtransparency="true" style="border: medium none; overflow: hidden; width: 210px; height: 408px;" src="http://www.facebook.com/plugins/likebox.php?href=http%3A%2F%2Fwww.facebook.com%2Fapps%2Fapplication.php%3Fid%3D158042927544491&amp;width=210&amp;colorscheme=light&amp;connections=12&amp;stream=false&amp;header=false&amp;height=408"></iframe>
								</div>
								<div class="homePageContentWidget_footer">

								</div>
							</div>
						</td>
					</tr>
				</table>
			</div>

			<div id="homePageContent2">
				<table width="100%">
					<tr>
						<td width="75%" valign="top">
							<div class="homePageContentWidget_main">
								<div class="homePageContentWidget_head">
									<table width="97%" cellpadding="0" cellspacing="0">										
										<tr>
											<td width="20px" valign="top">
												<img width="20" height="45" src="images/icons/homePage_new/homeWidgetImageHeader_left.jpg"/>
											</td>	
											<td valign="top">
												<div style="width:160px;"class="homePageContentWidget_head_center_div">
													<span class="homePageContentWidget_head_center_span">Current Elections</span>
												</div>
											</td>	
											<td width="20px" valign="top">
												<img width="20" height="45" src="images/icons/homePage_new/homeWidgetImageHeader_right.jpg"/>
											</td>
											<td width="100%">
											    <marquee scrollDelay="180"><font class="scrollDataStyle"><a href="exit_Polls_2011_And_Previous_Election_Reults_For_Tamilnadu_West_Bengal_Assam_Kerala_Pudducherry_Kadapa_2011_Election_Results_Pulivendula_2011_Election_Results.action" class="scrollDataStyle">Exit Polls 2011 And Previous Election Results For Tamilnadu, West Bengal, Assam, Kerala, Pudducherry And Kadapa, Pulivendla 2011 Election Results.</a>
												</font>
											   </marquee>
											</td>
										</tr>
									</table>
								</div>
								<div class="homePageContentWidget_body">
									<table width="100%">
										<tr>
											<td style="padding:10px;">
												<a href="statePageAction.action?stateId=24">
													<img width="180" height="125" style="border:none;" src="images/icons/homePage_new/TN_elections_img.jpg"/>
												</a>
											</td>
											<td style="padding:10px;">
												<a href="statePageAction.action?stateId=13">
													<img width="180" height="125" style="border:none;" src="images/icons/homePage_new/KL_elections_img.jpg"/>
												</a>
											</td>
											<td style="padding:10px;">
												<a href="statePageAction.action?stateId=3">
												<img width="180" height="125" src="images/icons/homePage_new/AS_elections_img.jpg"/>
												</a>
											</td>
										</tr>
										<tr>
											<td style="padding:10px;">
												<a href="statePageAction.action?stateId=35">
												<img width="180" height="125" src="images/icons/homePage_new/PD_elections_img.jpg"/>
												</a>
											</td>
											<td style="padding:10px;">
												<a href="statePageAction.action?stateId=28">
												<img width="180" height="125" src="images/icons/homePage_new/WB_elections_img.jpg"/>
												</a>
											</td>
											<td style="padding:10px;">
												
												<img width="180" height="125" src="images/icons/homePage_new/PA_KAA_img.jpg"/>
												
											</td>
										</tr>
									</table>								</div>
								<div class="homePageContentWidget_footer">
									
								</div>
							</div>
						</td>
						<td width="25%" valign="top">
							<div class="homePageContentWidget_main">
								<div class="homePageContentWidget_head">
									<table width="75%" cellpadding="0" cellspacing="0">
										<tr>
											<td width="20px" valign="top">
												<img width="20" height="45" src="images/icons/homePage_new/homeWidgetImageHeader_left.jpg"/>
											</td>	
											<td valign="top">
												<div class="homePageContentWidget_head_center_div">
													<span class="homePageContentWidget_head_center_span">Quick Search</span>
												</div>
											</td>	
											<td width="20px" valign="top">
												<img width="20" height="45" src="images/icons/homePage_new/homeWidgetImageHeader_right.jpg"/>
											</td>
										</tr>
									</table>
								</div>
								<div class="homePageContentWidget_body">
									<jsp:include page="../jsp/cncSearch.jsp"/>
								</div>
								<div class="homePageContentWidget_footer">

								</div>
							</div>
						</td>
					</tr>
				</table>
			</div>
			
			<div id="homePageContent3">
				<table width="100%">
					<tr>
						<td width="75%" valign="top">
							<div class="homePageContentWidget_main">
								<div class="homePageContentWidget_head">
														<table width="42%" cellpadding="0" cellspacing="0">
										<tr>
											<td width="20px" valign="top">
												<img width="20" height="45" src="images/icons/homePage_new/homeWidgetImageHeader_left.jpg"/>
											</td>	
											<td valign="top">
												<div class="homePageContentWidget_head_center_div">
													<span class="homePageContentWidget_head_center_span">Sneak Peak @ Party Analyst</span>
												</div>
											</td>	
											<td width="20px" valign="top">
												<img width="20" height="45" src="images/icons/homePage_new/homeWidgetImageHeader_right.jpg"/>
											</td>
										</tr>
									</table>
								</div>
								<div class="homePageContentWidget_body">

									<div class="homePageContentWidget_readMore">
										
									</div>
									<div id="homePage_Chart_Header_main" style="overflow:hidden;">
										<div id="homePage_Chart_Header">
									   
										</div>
									</div>
							</div>
								<div class="homePageContentWidget_footer">
								</div>
							</div>
						</td>
						<td width="25%" valign="top">
							<div class="homePageContentWidget_main">
								<div class="homePageContentWidget_head">
									<table width="75%" cellpadding="0" cellspacing="0">
										<tr>
											<td width="20px" valign="top">
												<img width="20" height="45" src="images/icons/homePage_new/homeWidgetImageHeader_left.jpg"/>
											</td>	
											<td valign="top">
												<div class="homePageContentWidget_head_center_div">
													<span class="homePageContentWidget_head_center_span">Quick Request</span>
													

												</div>
											</td>	
											<td width="20px" valign="top">
												<img width="20" height="45" src="images/icons/homePage_new/homeWidgetImageHeader_right.jpg"/>
											</td>
										</tr>
									</table>
								</div>
								<div class="homePageContentWidget_body">
									<table id="quickRequestTable">
										<tr>
											<td colspan="2" align="right"><font color="red">*</font><span>Fields are Mandatory</span></td>
										</tr>
										<tr>
										  <td style="font-weight:bold">
											<font color="red"> * </font>Name:
										  </td>
										  <td>
											<input class="quickRequestTextbox" type="text" name="name" id="quickRequestNameTextbox">
										  </td>
										</tr>
										<tr>
										  <td style="font-weight:bold">
											<font color="red"> * </font>Email:
										  </td>
										  <td>
											<input type="text" class="quickRequestTextbox" name="email" id="quickRequestEmailTextbox">
										  </td>
										</tr>
										<tr>
										  <td style="font-weight:bold">
											<font color="red"> * </font>Mobile:
										  </td>
										  <td>
											<input type="text" class="quickRequestTextbox" name="mobileNO" id="quickRequestMobileTextbox">
										  </td>
										</tr>
										<tr>
										  <td style="font-weight:bold" colspan="2">
											<font color="red"> * </font> Requirement:
										  </td>
										</tr>
										<tr>
										  <td colspan="2">
											<textarea name="requirement" rows="3" cols="21" id="quickRequestReqTextbox" ></textarea>
										   </td>
										</tr>
										<tr>
										  <td colspan="2" align="right"><div id="feedback"><input type="submit" value="Send Request" class="quickRequestTextbox" style="margin-top=25px;" onclick="submitDialogBox()"></div></td>
										</tr>									
								 </table>
								</div>
								<div class="homePageContentWidget_footer">
 								</div>
							</div>
						</td>
					</tr>
				</table>
			</div>
			<div id="cpyRight_info" style="padding:10px 0px 10px 0px;">
				<table width="100%">
					<tr>
						<td valign="top" style="width:50px"><img width="60" height="40" src="images/icons/indexPage/importantNote.png"></img> </td>
						<td valign="top">
							<div id="copyRightTextDiv"> The information displayed in this website are based on the data collected from the Election Commmission Of India.
							Further suggestions and corrections please contact us at <font color="#b76823"><b>info@itgrids.com</b></font></div> </td>
					</tr>
				</table>				
			</div>
			<div id="homePage_new_footer">
				<table width="100%">
					<tr>
						<td width="15%" valign="top">
							<div class="homePage_new_footer_links_head">About Us</div>
							<ul class="homePage_new_footer_links">
								<li><a href="footerLinksAction.action?linkFrom=aboutUs#whoWeAre">Who we are</a></li>
								<li><a href="footerLinksAction.action?linkFrom=aboutUs#whatWeDo">What we do</a></li>
								<li><a href="footerLinksAction.action?linkFrom=aboutUs#coreCompetency">Core Competency</a></li>
								<li><a href="javascript:{}" >Customer Support</a></li>
								<li><a href="javascript:{}" id="contactLink" onclick="contactLinkInHomePage()">Contact</a></li>
								<li><a href="javascript:{}">Sitemap</a></li>
							</ul>
						</td>
						<td width="18%" valign="top">
							<div class="homePage_new_footer_links_head">Connect</div>
							<ul class="homePage_new_footer_links">
							<c:if test="${loginStatus == 'false'}">
								<li><a href="freeUserRegistration.action">Register</a></li>
								<li><a href="loginInputAction.action">Login</a></li>
							</c:if>
								<li><a href="viewFeaturesAction.action">Explore</a></li>
								<li><a href="javascript:{}" onClick="contactLinkInHomePage()">Ask for DEMO/TEST Login</a></li>
								<li><a href="javascript:{}" onClick="showFeedBackFormPanel()">Feedback</a></li>
								<li><a href="javascript:{}">Articles</a></li>
								<li><a href="javascript:{}">Blogs</a></li>
							</ul>
						</td>
						<td width="13%" valign="top">
							<div class="homePage_new_footer_links_head">Policy</div>
							<ul class="homePage_new_footer_links">
								<li><a href="footerLinksAction.action?linkFrom=termsOfUse#termsOfUse">Terms of use</a></li>
								<li><a href="footerLinksAction.action?linkFrom=privacy#privacyPolicy">Privacy</a></li>
								<li><a href="footerLinksAction.action?linkFrom=disclaimer#disclaimer">Disclaimer</a></li>
							</ul>
						</td>
						<td width="13%" valign="top">
							<div class="homePage_new_footer_links_head">We are SOCIAL</div>
							<ul class="homePage_new_footer_links">
								<li><a href="http://www.facebook.com/PartyAnalyst" target="_blank">Facebook</a></li>
								<li><a href="http://twitter.com/#!/party_analyst" target="_blank">Twitter</a></li>
								<li><a href="http://www.linkedin.com/company/it-grids-ltd" target="_blank">LinkedIN</a></li>
								<li><a href="http://www.youtube.com/partyanalyst" target="_blank">YouTube</a></li>

							</ul>
						</td>

						<td width="15%" valign="top">
							<div class="homePage_new_footer_links_head">News & Events</div>
							<ul class="homePage_new_footer_links">
								<li><a href="javascript:{}" onclick="openMediaOpenionsWindow()">Party Analyst Launch</a></li>
							<li><a href="footerLinksAction.action?linkFrom=guestArticelsSubmission" target="_blank">Publish Your Article</a></li>
							<li><a href="newsZoneAction.action" target="_blank">News Zone</a></li>
							
							
							</ul>
						</td>

						<td width="25%" valign="top" align="right">
							<div class="homePage_new_footer_links_head"> &copy; Copyright 2011. All rights reserved </div>
							<div class="homePage_new_footer_links_head">
								<a href="http://www.itgrids.com" target="_blank">IT GRIDS (India) Pvt. Ltd.</a></div>
						</td>
					</tr>
				</table>
			</div>
						        
	</div>
	
<script type="text/javascript">

	initializeHomePage();
	getElectionTypeValue(1);
	
	<c:if test="${loginStatus == 'true' && sessionScope.UserType == 'FreeUser'}">
	showUserNameChangePanel(uname);
	</c:if>

	//getElectionYears("Assembly");
	</script>
	
</body>
</html>
