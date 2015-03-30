<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>

<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><decorator:title default="Committee"/></title>
	<!-- Bootstrap -->
    <link href="dist/css/bootstrap.min.css" rel="stylesheet"/>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	
	<link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
	<style>
		/*body{background: #F4D330 url("background_fixed.jpg"); background-size: cover;}
		.m_top20{margin-top:20px;}
		.m_top0{margin-top:0px;}
		.m_top10{margin-top:10px;}*/
		body,table,h1,h2,h3,h4,h5,h6,p,a {font-family: 'Roboto', sans-serif !important; }
	</style>
	<script>
	jQuery.noConflict();
	</script>
	<header style="align:center;background-color:#ef4036; display:flex;border-bottom:4px solid #13a751;">
			<!----LOGO---->
		 	<div class="col-md-6 col-md-offset-3 col-xs-6 col-xs-offset-3 col-sm-6 col-sm-offset-3 text-center">
				<img src="images/Committees_2014_logo.png" class="m_top10" title="Committee Logo" alt="committee" />
			</div>
			<!----/LOGO End---->
			
			<!----MENU---->
			
			<div class="col-md-3  col-xs-3 col-sm-3">    
				<div class="" style="color:white;margin-top: 20px;"><b> Welcome ${sessionScope.UserName} </b></div>
                    <a href="#" class="dropdown-toggle btn btn-default btn-xs m_top10" data-toggle="dropdown" aria-expanded="false" style="margin-top: 20px;">
                    Menu <img src="images/menu_icon.png" />
                    </a>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="drop6" style="    background-color: rgb(239, 64, 54);top: 91px;">
                   <c:if test="${fn:contains(sessionScope.USER.entitlements, 'TDP_COMMITTEE_STATE_DISTRICT_ACCESS' ) || fn:contains(sessionScope.USER.entitlements, 'CADRE_COMMITTEE_MANAGEMENT' )}">
                       <li><a tabindex="-1" href="committeeManagementAction.action">Home</a></li>
					   <li><a tabindex="-1" href="cadreCommitteeSummaryAction.action">Summary Report</a></li>
                   </c:if>
                    <c:if test="${!fn:contains(sessionScope.USER.entitlements, 'TDP_COMMITTEE_STATE_DISTRICT_ACCESS' )}">
                       <li><a tabindex="-1" href="cadreCommitteeAction.action">Home</a></li>
				       
				       <li><a tabindex="-1" href="cadreCommitteeRequestAction.action">Request For Positions Increase</a></li>
				       <li><a tabindex="-1" href="constituencyCommitteeSummaryAction.action">Advanced DashBoard</a></li>
                    </c:if>
				  <li role="presentation" class="divider" style="background-color: rgba(229, 229, 229,0.6);"></li>
				  <li><a tabindex="-1" href="newlogoutAction.action">Sign Out</a></li>
                    </ul>                 
            </div>
			<!----/MENU End---->
	</header>
	<decorator:head/>
	</head>
	
	<body>

	<decorator:body/>
	
	<footer>
	<hr>
	<div class="row-fluid">
		<div class="col-md-12 col-sm-12 col-xs-12 text-center">
			&copy; TDP-2015 Committees Management 
		<div>
	<div>	
	</footer>


	
	
</body>
</html>
