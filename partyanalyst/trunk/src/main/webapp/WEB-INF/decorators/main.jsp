<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/connection/connection-min.js&2.8.2r1/build/container/container-min.js&2.8.2r1/build/datasource/datasource-min.js&2.8.2r1/build/element/element-min.js&2.8.2r1/build/paginator/paginator-min.js&2.8.2r1/build/datatable/datatable-min.js&2.8.2r1/build/json/json-min.js&2.8.2r1/build/menu/menu-min.js"></script> 

<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript" src="js/partyPerformance.js" ></script>
<script type="text/javascript" src="js/partyPerformanceReport.js" ></script>
<script type="text/javascript" src="js/homePage/homePage.js"> </script>
<script type="text/javascript" src="js/googleAnalytics/googleAnalytics.js" ></script>

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

<link href="styles/pa.css" rel="stylesheet" type="text/css" />
<link href="styles/styles.css" rel="stylesheet" type="text/css" />
<link href="styles/indexPage/indexPage.css" rel="stylesheet" type="text/css" />
<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/faviIcon.jpg">
	
	<script type="text/javascript">
	 
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
	            { text: "Elections Comparisons Report", url: "electionComparisonAction.action" },
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
		    { text: "Problem Management Report", url: "problemManagementReportAction.action" },
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
						/*{ text: "Karnataka", url: "statePageAction.action?stateId=12" },*/
						{ text: "Kerala", url: "statePageAction.action?stateId=13" },
						{ text: "Puducherry", url: "statePageAction.action?stateId=35" },
						{ text: "Tamil Nadu", url: "statePageAction.action?stateId=24" },
						{ text: "West Bengal", url: "statePageAction.action?stateId=28" }
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
				            { text: "Elections Comparisons Report", url: "electionComparisonAction.action" },
				            { text: "Party Results Report", url: "partyResultsCriteriaAction.action" },
				            { text: "Party Strengths/Weakness", url: "partyStrengthAction.action" }       	                       
				        ]    
					},
					{ 
						id: "staticData",  
						itemdata: [ 
								{ text: "Andhra Pradesh", url: "statePageAction.action?stateId=1" },
								{ text: "Assam", url: "statePageAction.action?stateId=3" },
								/*{ text: "Karnataka", url: "statePageAction.action?stateId=12" },*/
								{ text: "Kerala", url: "statePageAction.action?stateId=13" },
								{ text: "Puducherry", url: "statePageAction.action?stateId=35" },
								{ text: "Tamil Nadu", url: "statePageAction.action?stateId=24" },
								{ text: "West Bengal", url: "statePageAction.action?stateId=28" }
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
	
	</script>
	 <decorator:head/>
</head>

<body>
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
							<td id="searchBox">
								
							</td>
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
	
	<script type="text/javascript">
		//buildLogoImage();
	</script>
</body>
</html>