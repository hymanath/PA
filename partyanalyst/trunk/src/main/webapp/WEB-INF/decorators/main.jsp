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

	<script type="text/javascript" src="js/main.js"></script>


	<script src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script>
	<script src="js/yahoo/yui-js-2.8/build/container/container_core-min.js"></script>
	<script src="js/yahoo/yui-js-2.8/build/menu/menu-min.js"></script>	

	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/fonts/fonts-min.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/menu/assets/skins/sam/menu.css">

	<script type="text/javascript" src="js/partyPerformance.js" ></script>
	<script type="text/javascript" src="js/partyPerformanceReport.js" ></script>	
	
	<link href="styles/pa.css" rel="stylesheet" type="text/css" />
	<link href="styles/styles.css" rel="stylesheet" type="text/css" />
	<link href="styles/indexPage/indexPage.css" rel="stylesheet" type="text/css" />
	

	

	
	
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
	    } 
	 
	}); 
	 
	oMenuBar.render();  
	 
	}); 

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
				{ text: "Maharashtra", url: "statePageAction.action?stateId=15" }
	        ]     
	    }
	     
	     
	]; 
	
	</script>
	 <decorator:head/>
</head>

<body>
	<div id="indexheader" class="indexLayoutContainer" style="overflow:visible">
		<table  width="100%" id="headerTable">
			<tr>
				<td style="vertical-align:top;width:580px;">
					<div id="pa_Logo"><img src="images/icons/homePage/pa_logo.jpg" style="padding-left: 10px; padding-top: 5px;"></img></div>
				</td>
				<td style="vertical-align:top;">
					<table width="100%" style="width:100%">
						<tr>
							<td id="loginarea">
								<c:if test="${sessionScope.loginStatus == 'out'}">        		
									<c:out value="Welcome, ${sessionScope.UserName} | "/>
									<a class="loginStatusAnc" href="<c:out value="${pageContext.request.contextPath}" />/logOut.jsp">LogOut</a> | 
									<a class="loginStatusAnc" href="<c:out value="${pageContext.request.contextPath}" />/index.action" >Home</a> | 
									<a class="loginStatusAnc" href="<c:out value="${pageContext.request.contextPath}/adminUpload.action" />" >Admin</a>         		
								</c:if>		
								<c:if test="${sessionScope.loginStatus == null || sessionScope.loginStatus == 'in'}">
									<a class="loginStatusAnc" href="<c:out value="${pageContext.request.contextPath}" />/index.action" >Home</a> |
									<a class="loginStatusAnc" href="<c:out value="${pageContext.request.contextPath}" />/loginForm.jsp" >Login</a> | 
									<a class="loginStatusAnc" href="<c:out value="${pageContext.request.contextPath}" />/userRegPageAction.action" >Register</a> | 
									<a class="loginStatusAnc" href="<c:out value="${pageContext.request.contextPath}/adminUpload.action" />" >Admin</a>
								</c:if>		
							</td>
						</tr>
						<tr>
							<td id="searchBox">
								<jsp:include page="../../jsp/cncSearch.jsp"/>
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
						<li class="yuimenubaritem" style="background:none;cursor:pointer;"> 
							<a class="yuimenubaritemlabel" href="<c:out value="${pageContext.request.contextPath}" />/index.action" >
								<img id="indexHomeImg" src="images/icons/indexPage/pa_home.png" title="home"/>								
								HOME
							</a> 
						</li> 
						<li class="yuimenubaritem"> 
							<a class="yuimenubaritemlabel" href="javascript:{}">PARTY ANALYSIS</a> 
						</li> 
						<li class="yuimenubaritem"> 
							<a class="yuimenubaritemlabel" href="cadreManagementAction.action">CADRE</a> 
						</li> 
						<li class="yuimenubaritem"> 
							<a class="yuimenubaritemlabel" href="constituencyManagementAction.action">CONSTITUENCEY MANAGEMENT</a> 
						</li> 
						<li class="yuimenubaritem"> 
							<a class="yuimenubaritemlabel" href="javascript:{}">POLITICIAN ANALYSIS</a> 
						</li> 
						<li class="yuimenubaritem"> 
							<a class="yuimenubaritemlabel" href="statePageAction.action?stateId=1">STATIC DATA</a> 
						</li> 
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
		<div id="index_inner_footer">
		<table width="100%" id="copyrightLinksTable">
			<tr>
				<td align="left"> © Copyright 2010. All rights reserved | IT GRIDS (India) Pvt. Ltd.</td>
				<td align="right"> About Us | Contact Us | API | Terms Of Use | Privacy Policy </td>
			</tr>
		</table>
		</div>
	</div>

</body>
</html>
