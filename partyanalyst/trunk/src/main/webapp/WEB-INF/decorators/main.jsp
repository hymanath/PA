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
	
	<!-- YUI Dependency files (Start) -->

<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/treeview/treeview-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dragdrop/dragdrop-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/history/history.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/container/container-min.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yuiloader/yuiloader-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/button/button-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/resize/resize-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/layout/layout-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/carousel/carousel-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/container/container_core-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/menu/menu-min.js"></script>	



<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>

<script type="text/javascript" src="js/yahoo/yui-gallery/gallery-accordion-min.js"></script>

<!-- YUI Skin Sam -->

<link rel="stylesheet" type="text/css" href="styles/yuiStyles/yui-gallery-styles/gallery-accordion.css">	
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/treeview/assets/skins/sam/treeview.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/resize.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/layout.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/carousel/assets/skins/sam/carousel.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/fonts/fonts-min.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/menu/assets/skins/sam/menu.css">

<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/gallery-2010.03.02-18/build/gallery-accordion/assets/skins/sam/gallery-accordion.css">
 <!--YUI Dependency files (End) -->

<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript" src="js/partyPerformance.js" ></script>
<script type="text/javascript" src="js/partyPerformanceReport.js" ></script>
<script type="text/javascript" src="js/googleAnalytics/googleAnalytics.js" ></script>

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
	            { text: "Party Influence Report", url: "partyInfluenceMainAction.action" },
	            { text: "Election Results Analysis Report", url:"electionResultsAnalysisAction.action"}	                       
	        ] 
	    }, 
	    { 
	        id: "cadreManagement", 
	        itemdata: [ 				
	   	          

	        ] 
	    },  
		{ 
	        id: "constituenceyManagement",
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
	            { text: "Constituencey Booth Results Report", url: "partyBoothResultAction.action" }						
	        ]  
	    },	 
	    { 
	        id: "staticData",  
	        itemdata: [ 
	            { text: "Andhra Pradesh", url: "statePageAction.action?stateId=1" },
	            { text: "Tamil Nadu", url: "statePageAction.action?stateId=24" },
	            { text: "Karnataka", url: "statePageAction.action?stateId=12" },
				{ text: "Telangana Bye-Elections 2010", url: "biElectionAction.action" }
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
				            { text: "Party Results Report", url: "partyResultsCriteriaAction.action" }	                       
				        ]    
					},
					{ 
						id: "staticData",  
						itemdata: [ 
							{ text: "Andhra Pradesh", url: "statePageAction.action?stateId=1" },
							{ text: "Tamil Nadu", url: "statePageAction.action?stateId=24" },
							{ text: "Karnataka", url: "statePageAction.action?stateId=12" },
							{ text: "Telangana Bye-Elections 2010", url: "biElectionAction.action" }
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
			str += '<img src="images/icons/homePage_new/logo.gif">';
		else
			str += '<img src="images/icons/homePage_new/logo.png">';

		elmt.innerHTML = str;
	}
	
	</script>
	 <decorator:head/>
</head>

<body>
	<div id="indexheader" class="indexLayoutContainer" style="overflow:visible;background-image:url('images/icons/homePage_new/headerBG.jpg');height:125px;">
		<table  width="100%" id="headerTable">
			<tr>
				<td style="vertical-align:top;width:540px;">
					<div id="pa_Logo" style="padding-left: 10px; padding-top: 10px;"></div>
				</td>
				<td style="vertical-align:top;">
					<table width="100%" style="width:100%">
						<tr>
							<th id="loginarea">
								<c:if test="${! empty sessionScope.USER.registrationID}">        		
									<c:out value="Welcome, ${sessionScope.UserName} | "/>
									<a class="loginStatusAnc" style="color:#163447" href="<c:out value="${pageContext.request.contextPath}" />/logOut.jsp">LogOut</a> | 
									<c:if test="${sessionScope.UserType == 'PartyAnalyst'}"> 
										<a class="loginStatusAnc" style="color:#163447" href="<c:out value="${pageContext.request.contextPath}" />/index.action" >Home</a> | 
									</c:if>	
									<c:if test="${sessionScope.UserType == 'FreeUser'}"> 
										<a class="loginStatusAnc" style="color:#163447" href="<c:out value="${pageContext.request.contextPath}" />/homePage.action" >Home</a> | 
									</c:if>	
									<a class="loginStatusAnc" style="color:#163447" href="<c:out value="${pageContext.request.contextPath}/adminUpload.action" />" >Admin</a>         		
								</c:if>		
								<c:if test="${sessionScope.loginStatus == null || sessionScope.loginStatus == 'in'}">
									<a class="loginStatusAnc" style="color:#163447" href="<c:out value="${pageContext.request.contextPath}" />/index.action" >Home</a> |
									<a class="loginStatusAnc" style="color:#163447" href="<c:out value="${pageContext.request.contextPath}" />/loginInputAction.action?url=${pageContext.request.servletPath}?&${pageContext.request.queryString}" >Login</a> | 
									<a class="loginStatusAnc" style="color:#163447" href="<c:out value="${pageContext.request.contextPath}/adminUpload.action" />" >Admin</a>
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
						<c:if test="${sessionScope.loginStatus == 'out'}">  
						<li class="yuimenubaritem" style="background:none;cursor:pointer;"> 
							<c:if test="${sessionScope.UserType == 'PartyAnalyst'}"> 
								<a class="yuimenubaritemlabel" href="<c:out value="${pageContext.request.contextPath}" />/index.action" >
								<img id="indexHomeImg" src="images/icons/indexPage/pa_home.png" title="home"/>								
								HOME
							</a> 
							</c:if>	
							<c:if test="${sessionScope.UserType == 'FreeUser'}"> 
								<a class="yuimenubaritemlabel" href="<c:out value="${pageContext.request.contextPath}" />/homePage.action" >
									<img id="indexHomeImg" src="images/icons/indexPage/pa_home.png" title="home"/>								
									HOME
								</a>  
							</c:if>	
							
						</li> 
						</c:if>
						<c:if test="${sessionScope.loginStatus == null || sessionScope.loginStatus == 'in'}">
							<li class="yuimenubaritem" style="background:none;cursor:pointer;"> 
							<a class="yuimenubaritemlabel" href="<c:out value="${pageContext.request.contextPath}" />/homePage.action" >
								<img id="indexHomeImg" src="images/icons/indexPage/pa_home.png" title="home"/>								
								HOME
							</a> 
						</li> 
						</c:if>

						<c:if test="${sessionScope.loginStatus == 'out' && sessionScope.UserType == 'PartyAnalyst'}">  
						<li class="yuimenubaritem"> 
							<a class="yuimenubaritemlabel" href="javascript:{}">PARTY ANALYSIS</a> 
						</li> 
						</c:if>
						<c:if test="${sessionScope.loginStatus == 'out' && sessionScope.UserType == 'PartyAnalyst'}">
						<li class="yuimenubaritem"> 
							<a class="yuimenubaritemlabel" href="cadreManagementAction.action">CADRE</a> 
						</li> 
						</c:if>
						<c:if test="${sessionScope.loginStatus == 'out' && sessionScope.UserType == 'PartyAnalyst'}">
						<li class="yuimenubaritem"> 
							<a class="yuimenubaritemlabel" href="initailConstituencyManagementAction.action">CONSTITUENCEY MANAGEMENT</a> 
						</li> 
						</c:if>
						
						<li class="yuimenubaritem"> 
							<a class="yuimenubaritemlabel" href="javascript:{}">POLITICIAN ANALYSIS</a> 
						</li> 
						<li class="yuimenubaritem"> 
							<a class="yuimenubaritemlabel" href="statePageAction.action?stateId=1">STATES</a> 
						</li>
						<li class="yuimenubaritem"> 
							<a class="yuimenubaritemlabel" href="searchPartyAnalystAction.action">SEARCH</a> 
						</li> 
						<c:if test="${sessionScope.loginStatus == 'out'}">  
							<li class="yuimenubaritem" style="background:none;cursor:pointer;"> 
								<c:if test="${sessionScope.UserType == 'FreeUser'}"> 
									<a class="yuimenubaritemlabel" href="<c:out value="${pageContext.request.contextPath}" />/connectPeopleAction.action" >												
										USER PROFILE
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
					<td valign="top" style="width:50px"><img src="images/icons/indexPage/importantNote.png"></img> </td>
					<td valign="top">
						<div id="copyRightTextDiv"> The information displayed in this website are based on the data provided by the Election Commmission Of India.
						Further suggestions and corrections please contact us at <font color="#b76823"><b>info@itgrids.com</b></font></div> </td>
				</tr>
			</table>
			
		</div>
		<div id="index_inner_footer">
		<table width="100%" id="copyrightLinksTable">
			<tr>
				<td align="left"> Â© Copyright 2010. All rights reserved | IT GRIDS (India) Pvt. Ltd.</td>
				<td align="right"> About Us | Contact Us | API | Terms Of Use | Privacy Policy </td>
			</tr>
		</table>
		</div>
	</div>
	
	<script type="text/javascript">
		buildLogoImage();
	</script>
</body>
</html>
