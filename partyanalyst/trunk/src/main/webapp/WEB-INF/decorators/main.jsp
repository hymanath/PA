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

	
<!-- Combo-handled YUI CSS files: --> 
<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/combo?2.8.2r1/build/assets/skins/sam/skin.css"> 
<!-- Combo-handled YUI JS files: --> 
<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/connection/connection-min.js&2.8.2r1/build/datasource/datasource-min.js&2.8.2r1/build/autocomplete/autocomplete-min.js&2.8.2r1/build/element/element-min.js&2.8.2r1/build/container/container-min.js&2.8.2r1/build/menu/menu-min.js&2.8.2r1/build/button/button-min.js&2.8.2r1/build/paginator/paginator-min.js&2.8.2r1/build/datatable/datatable-min.js&2.8.2r1/build/json/json-min.js&2.8.2r1/build/tabview/tabview-min.js"></script> 


<script type="text/javascript" src="http://www.google.com/jsapi"></script>
<script type="text/javascript" src="js/googleAnalytics/googleAnalytics.js" ></script>


<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript" src="js/partyPerformance.js" ></script>
<script type="text/javascript" src="js/partyPerformanceReport.js" ></script>
<script type="text/javascript" src="js/homePage/homePage.js"> </script>
<script type="text/javascript" src="js/googleAnalytics/googleAnalytics.js" ></script>
<script type="text/javascript" src="js/cncSearch.js"> </script>
<script type="text/javascript" src="js/commonUtilityScript/regionSelect.js"></script>
<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
<script type="text/javascript" src="js/LocationHierarchy/locationHierarchy.js"></script>   
<link  rel="stylesheet" type="text/css" href="styles/landingPage/landingPage.css"/>
<link href="styles/pa.css" rel="stylesheet" type="text/css" />
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

<link href="styles/styles.css" rel="stylesheet" type="text/css" />
<link href="styles/indexPage/indexPage.css" rel="stylesheet" type="text/css" />
<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/faviIcon.jpg">

<script type="text/javascript">
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
						{ text: "Gujarat", url: "statePageAction.action?stateId=7" },
						{ text: "Uttaranchal", url: "statePageAction.action?stateId=26"}
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
								{ text: "Gujarat", url: "statePageAction.action?stateId=7" },
								{ text: "Uttaranchal", url: "statePageAction.action?stateId=26"}
						]     
					}, 
					{ 
						id: "search", 
						itemdata: [
						] 
					}  					 
				]; 
	</c:if>

	function buildLogoImage()
	{
		var elmt = document.getElementById("pa_Logo");

		if(!elmt)
			return;

		var str = '';	
		
		
		if(navigator.appName=="Microsoft Internet Explorer")
			str += '<img width="171" height="84" src="images/icons/homePage_new/logo.gif">';
		else
			str += '<img width="171" height="84" src="images/icons/homePage_new/logo.png">';

		elmt.innerHTML = str;
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
	
function intialize()
{
	$('#floatingDiv_absolute_main').addFloating(  
	 {  
		 targetRight: 10,  
		 targetTop: 10,  
		 snap: true  
	 });  
}

function openAddNewProblemWindow()
{	
	var browser_addNewProblem = window.open("<s:url action="addNewProblemAction.action"/>","addNewProblem","scrollbars=yes,height=600,width=600,left=200,top=200");
	
	browser_addNewProblem.focus();
}
	
</script>
 <decorator:head/>

</head>
<body>

<div id="homePageContainer" style="overflow:visible;">
<div id="floatingDiv_relative_main">
			<div id="floatingDiv_absolute_main">				
				<a href="javascript:{}" onclick="buildAccordion()"><img   width="25" height="100" style="border:0px none;padding:0px;margin:0px" src="images/icons/QuickView.png"/></a>
				<div id="floatingDiv_absolute_close" >
					<img width="13" height="14" id="floatingDiv_absolute_close_Img" onclick="javascript:{$('#floatingDiv_relative_main').hide();}" src="images/icons/homePage_new/feedback_close.jpeg">
				</div>
			</div>
		</div>
    <div id="contactWindowDiv"><div id="contactWindowDiv_window_inner"></div></div>
	<div id="indexheader" class="indexLayoutContainer" style="overflow:visible;background-image:url('images/icons/homePage_new/headerBG_betanew.jpg');height:125px;">
		<table  width="100%" id="headerTable">
			<tr>
				<td style="vertical-align:top;width:540px;">
					<div id="pa_Logo" style="padding-left: 10px; padding-top: 10px;"></div>
				</td>
				<td style="vertical-align:top;">
					<table width="100%" style="width:100%">
						<tr>
							<th id="loginarea" style="color:#FFFFFF">
								 <c:if test="${sessionScope.loginStatus != null && sessionScope.loginStatus == 'out'  && sessionScope.UserType == 'FreeUser'}">        		
									<c:out value="Welcome, ${sessionScope.UserName} | "/>
									<a class="loginStatusAnc" style="color:#FFFFFF" href="<c:out value="${pageContext.request.contextPath}" />/logOut.jsp">Logout</a>
								</c:if>		
								<c:if test="${sessionScope.loginStatus != null && sessionScope.loginStatus == 'out' && sessionScope.UserType == 'PartyAnalyst'}">        		
									<c:out value="Welcome, ${sessionScope.UserName} | "/>
									<a class="loginStatusAnc" style="color:#FFFFFF" href="<c:out value="${pageContext.request.contextPath}" />/logOut.jsp">Logout</a>
									<c:if test="${sessionScope.USER.isAdmin == 'true'}"> | 
										<a class="loginStatusAnc" style="color:#FFFFFF" href="<c:out value="${pageContext.request.contextPath}/adminUpload.action" />" >Admin</a>
									</c:if>	         		
								</c:if>			
								<c:if test="${sessionScope.loginStatus == null || sessionScope.loginStatus == 'in'}">
									<a class="loginStatusAnc" style="color:#FFFFFF" href="<c:out value="${pageContext.request.contextPath}" />/loginInputAction.action?url=${pageContext.request.servletPath}?&${pageContext.request.queryString}" >Login</a>  | 
									<a class="loginStatusAnc" style="color:#FFFFFF" href="<c:out value="${pageContext.request.contextPath}/freeUserRegistration.action" />" >Register</a>
								</c:if>		
							</th>
						</tr>
						<tr>
						<td id="searchBox"></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>		
	</div>	
	
	<div id="indexNavContainer"  class="indexLayoutContainer yui-skin-sam">
		<div id="navigationHead" class="yuimenubar yuimenubarnav"> 
				<div class="bd"> 
					<ul class="first-of-type"> 
						<li class="yuimenubaritem"> 
							<a class="yuimenubaritemlabel" href="<c:out value="${pageContext.request.contextPath}" />/homePage.action" >
								<img id="indexHomeImg" width="28" height="25" src="images/icons/indexPage/pa_home.png" title="home"/>								
								HOME
							</a> 
						</li>
						<c:if test="${sessionScope.loginStatus == 'out' && sessionScope.UserType == 'PartyAnalyst'}">  
							<li class="yuimenubaritem"> 
								<a class="yuimenubaritemlabel" href="javascript:{}">ELECTION ANALYSIS</a> 
							</li> 
							<li class="yuimenubaritem"> 
								<a class="yuimenubaritemlabel" href="cadreManagementAction.action">CADRE</a> 
							</li> 
							<li class="yuimenubaritem"> 
								<a class="yuimenubaritemlabel" href="initailConstituencyManagementAction.action">CONSTITUENCY MANAGEMENT</a> 
							</li> 
						</c:if>
						
						<c:if test="${sessionScope.loginStatus == 'out' && sessionScope.UserType == 'PartyAnalyst'}">  
							<li class="yuimenubaritem"> 
								<a class="yuimenubaritemlabel" href="javascript:{}">POLITICIAN ANALYSIS</a> 
							</li>
						</c:if>
						
						<c:if test="${sessionScope.UserType != 'PartyAnalyst'}">
						<li class="yuimenubaritem"> 
								<a class="yuimenubaritemlabel" href="javascript:{}">ELECTION ANALYSIS</a> 
							</li>
						</c:if>
						
						
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
	<div id="indexContent" class="indexLayoutContainer">		
		<div id="contenttable">
			<center>
				<decorator:body/>
			</center>
		</div>
	</div>
	<div id="index_footer" class="indexLayoutContainer">
		<div id="cpyRight_info" style="padding:10px 0px 10px 0px;">
			<table width="100%">
				<tr>
					<td valign="top" style="width:50px"><img width="60" height="40" src="images/icons/indexPage/importantNote.png"></img> </td>
					<td valign="top">
						<div id="copyRightTextDiv"> The Election Information displayed in this website are based on the data provided by the Election Commmission Of India.
						Further suggestions and corrections please contact us at <font color="#b76823"><b>info@itgrids.com</b></font></div> </td>
				</tr>
			</table>
			
		</div>
		<div id="index_inner_footer">
		<table width="100%" id="copyrightLinksTable">
			<tr>
				<td align="left"> &copy; Copyright 2011. All rights reserved | IT GRIDS (India) Pvt. Ltd.</td>
				<td align="right" ><a href="footerLinksAction.action?linkFrom=aboutUs" style="text-decoration:none" ><font color="#ffffff">About Us </font></a>
				| <a  href="javascript:{}"onclick ="contactLinkInHomePage()" style="text-decoration:none"> <font color="#ffffff"> Contact Us</font> </a>
				| <a href="footerLinksAction.action?linkFrom=termsOfUse" style="text-decoration:none"><font color="#ffffff">Terms Of Use</font></a>
				| <a href="footerLinksAction.action?linkFrom=privacy" style="text-decoration:none"><font color="#ffffff">Privacy Policy</font></a></td>
			</tr>
		</table>
		</div>
	</div>
	
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
								<img style="cursor:pointer" width="70" height="25" src="images/icons/homePage_new/b3.jpg" onclick="navigateToStatePage()"></img>
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
								<img style="cursor:pointer" width="70" height="25" src="images/icons/homePage_new/b3.jpg" onclick="navigateToDistrictPage()"></img>
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
								<img style="cursor:pointer" width="70" height="25" src="images/icons/homePage_new/b3.jpg" onclick="navigateToConstituencyPage()"></img>
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
								<img style="cursor:pointer" width="70" height="25" src="images/icons/homePage_new/b3.jpg" onclick="navigateToLocalBodyPage()"></img>
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

									<td widyh="35%"><img style="cursor:pointer" width="70" height="25" src="images/icons/homePage_new/b3.jpg" onclick="viewElectionResults()"></img></td>
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
