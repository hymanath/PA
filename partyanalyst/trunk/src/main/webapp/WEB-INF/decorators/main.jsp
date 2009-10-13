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
	<script type="text/javascript" src="js/partyPerformance.js" ></script>
	<script type="text/javascript" src="js/partyPerformanceReport.js" ></script>
	 <decorator:head/>
</head>

<body>
<div id="maincontainer">
	<div id="header">
		<div id="logo"></div>
		<div id="searchDiv" style="position: absolute; color: white;padding-left: 0px; margin-top: 10px;font-size: 11px; font-family: Arial;display:inline;" >
			<jsp:include page="../../jsp/cncSearch.jsp" />
		</div>
		<div id="loginarea">
			<c:if test="${sessionScope.loginStatus == 'out'}">        		
        		<c:out value="Welcome, ${sessionScope.UserName}|"/><a href="<c:out value="${pageContext.request.contextPath}" />/logOut.jsp">LogOut</a>         		
           	</c:if>		
        	<c:if test="${sessionScope.loginStatus == null || sessionScope.loginStatus == 'in'}">
        		<a href="<c:out value="${pageContext.request.contextPath}" />/loginForm.jsp" >Login</a> | <a href="<c:out value="${pageContext.request.contextPath}" />/registration.jsp" >Register</a></td> 
        	</c:if>		
		</div>
		<div id="navcontainer">
			<ul id="navlist">
				<li><a id="ppAnc" href="#">POLITICAL PLANNER</a></li>
				<li><a id="cmAnc"href="#">CADRE MANAGEMENT</a></li>
				<li>
					<a id="reportsAnc" href="javascript:{}" onmouseover="displayAnchorDiv(this.id)" onmouseout="hideAnchorDiv(this.id)">REPORTS</a>
					<div id="reportsAncDiv" onmouseover="displayAnchorDiv('reportsAnc')" onmouseout="hideAnchorDiv('reportsAnc')" style="padding: 5px; background-color:#2A2F35; display: none;position:absolute;">
						<div style="padding: 5px;cursor:pointer;width:100%;"><a style="padding:0px;" href="partyPerformanceMain.action">Party Performance Report</a></div>
						<div style="padding: 5px;cursor:pointer;width:100%;"><a style="padding:0px;" href="partyResultsCriteriaAction.action">Party Results Report</a></div>
					</div>	
				</li>
				<li id="active">
					<a id="stateAnc" href="javascript:{}" onmouseover="displayAnchorDiv(this.id)" onmouseout="hideAnchorDiv(this.id)">STATES</a>
					<div id="stateAncDiv" onmouseover="displayAnchorDiv('stateAnc')" onmouseout="hideAnchorDiv('stateAnc')" style="padding: 5px; background-color:#2A2F35; display: none;position:absolute;">
						<div style="padding: 5px;cursor:pointer;width:100%;"><a style="padding:0px;" href="statePageAction.action?stateId=1">Andhra Pradesh</a></div>
					</div>
				</li>
				<li><a id="homeAnc" href="${pageContext.request.contextPath}">HOME</a></li>
			</ul>
		</div>
		</div>
		<div id="content">
			<div id="contentimage"></div>
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