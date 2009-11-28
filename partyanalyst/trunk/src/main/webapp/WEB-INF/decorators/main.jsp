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
	<link href="styles/pa.css" rel="stylesheet" type="text/css" />
	<link href="styles/styles.css" rel="stylesheet" type="text/css" />

	

	<script type="text/javascript" src="js/main.js"></script>
	<!-- Dependencies --> 
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<!-- OPTIONAL: JSON (enables JSON validation) --> 
	<script type="text/javascript" src="js/json/json-min.js"></script> 
  	<!-- Dependencies --> 
   	<script type="text/javascript" src="js/yahoo/yahoo-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/container_core-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/menu-min.js" ></script>

	<script type="text/javascript" src="js/partyPerformance.js" ></script>
	<script type="text/javascript" src="js/partyPerformanceReport.js" ></script>	

	<link href="styles/yuiStyles/fonts-min.css" rel="stylesheet" type="text/css" />
	<link href="styles/yuiStyles/menu.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
		.yui-skin-sam .yuimenubar
		{
			border:medium none;
		}
		.yui-skin-sam .yuimenubaritemlabel
		{
			border:medium none;
		}
		.yui-skin-sam .yuimenubarnav .yuimenubaritem 
		{
			border:medium none;
		}
		.yui-skin-sam .yuimenu .bd
		{
			background-color:#2A2F35;
		}
		.yui-skin-sam .yuimenuitemlabel-selected 
		{
			background-color:#FFFFFF;
		}
	</style>
	
	<script type="text/javascript">
	 
	YAHOO.util.Event.onContentReady("navigationHead", function () { 	 
	    
	    var oMenuBar = new YAHOO.widget.MenuBar("navigationHead", {  
	                                                autosubmenudisplay: true,  
	                                                hidedelay: 100,  
	                                                lazyload: true }); 

		 
		oMenuBar.subscribe("beforeRender", function () { 
	 
	    if (this.getRoot() == this) { 
	 
			//this.getItem(0).cfg.setProperty("submenu", aSubmenuData[0]); 
	        this.getItem(1).cfg.setProperty("submenu", aSubmenuData[1]); 
	        this.getItem(2).cfg.setProperty("submenu", aSubmenuData[2]); 
			//this.getItem(3).cfg.setProperty("submenu", aSubmenuData[3]); 
			//this.getItem(4).cfg.setProperty("submenu", aSubmenuData[4]); 
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
	        id: "states",  
	        itemdata: [ 
	            { text: "Andhra Pradesh", url: "statePageAction.action?stateId=1" },
				{ text: "Maharashtra", url: "statePageAction.action?stateId=15" }
	        ]     
	    }, 
	     
	    { 
	        id: "reports",  
	        itemdata: [ 
	            { text: "Party Performance Report", url: "partyPerformanceMain.action" }, 	            
	            { text: "Elections Comparisons Report", url: "electionComparisonAction.action" },
	            { text: "Party Results Report", url: "partyResultsCriteriaAction.action" },	 
	            { text: "Party Booth Results Report", url: "partyBoothResultAction.action" },  
	            { text: "Add Rebels", url: "addRebelsAction.action" },                       
				{ text: "Party Influence Report", url: "partyInfluenceMainAction.action" },
				{ text: "Cross Voting Report", url: "crossVotingReportInputAction.action" },
				{ text: "Mandal Voting Report", url: "mandalPageSDetailAction.action" }
	        ]  
	    }, 
	     
	    { 
	        id: "cadreManagement", 
	        itemdata: [ 
	            
	        ] 
	    },  
		{ 
	        id: "politicalPlanner", 
	        itemdata: [ 
	            
	        ] 
	    }    
	]; 
	
	</script>
	 <decorator:head/>
</head>

<body>
<div id="maincontainer">
	<div id="header">
		<div id="logo"></div>
		<div id="searchDiv" style="position:absolute;color: white;margin-left:415px;margin-top:25px;font-size: 11px; font-family: Arial;display:inline;width:360px;" >
			<jsp:include page="../../jsp/cncSearch.jsp" />
		</div>
		<div id="loginarea">
			<c:if test="${sessionScope.loginStatus == 'out'}">        		
        		<c:out value="Welcome, ${sessionScope.UserName}|"/><a href="<c:out value="${pageContext.request.contextPath}" />/logOut.jsp">LogOut</a>         		
           	</c:if>		
        	<c:if test="${sessionScope.loginStatus == null || sessionScope.loginStatus == 'in'}">
        		<a href="<c:out value="${pageContext.request.contextPath}" />/loginForm.jsp" >Login</a> | <a href="<c:out value="${pageContext.request.contextPath}" />/userRegPageAction.action" >Register</a> 
        	</c:if>		
		</div>
		<div id="navcontainer" class="yui-skin-sam">
			<div id="navigationHead" class="yuimenubar yuimenubarnav"> 
				<div class="bd"> 
					<ul class="first-of-type"> 
						<li class="yuimenubaritem first-of-type"> 
							<a class="yuimenubaritemlabel" href="${pageContext.request.contextPath}">HOME</a> 
						</li> 
						<li class="yuimenubaritem"> 
							<a class="yuimenubaritemlabel" href="javascript:{}">STATES</a> 
						</li> 
						<li class="yuimenubaritem"> 
							<a class="yuimenubaritemlabel" href="javascript:{}">REPORTS</a> 
						</li> 
						<li class="yuimenubaritem"> 
							<a class="yuimenubaritemlabel" href="cadreReportAction.action">CADRE MANAGEMENT</a> 
						</li> 
						<li class="yuimenubaritem"> 
							<a class="yuimenubaritemlabel" href="javascript:{}">POLITICAL PLANNER</a> 
						</li> 
					</ul> 
				</div> 
			</div> 
		</div>
		</div>
		<div id="content">
			<div id="contentimage"><img height="120" width="960" src="<%=request.getContextPath()%>/images/site/homeimage.jpg"/></div>
			<div id="contenttable">
				<center>
					<decorator:body/>
				</center>
			</div>
		</div>
		<div id="footer">
			<div class="footertext">All information © Party Analyst. Hyderabad, India</div>
		</div>
	</div>
</body>
</html>
