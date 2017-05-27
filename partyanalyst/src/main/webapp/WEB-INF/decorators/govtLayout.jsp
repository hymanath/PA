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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><decorator:title default="Government"/></title>
<link rel="icon" type="image/png"  href="alertDepartment/img/Fevicon.png">
<link rel="stylesheet" href="alertDepartment/alertexpandcollapse/jquery.multilevelpushmenu_red.css">
<link rel="stylesheet" href="alertDepartment/alertexpandcollapse/fullexpandcollapse.css">
<link rel="stylesheet" href="alertDepartment/alertexpandcollapse/MenuResponsive.css">
<link rel="stylesheet" href="dist/css/font-awesome.css">

<style type="text/css">
#menu_multilevelpushmenu 
	{	
     
      background-color: #555555;
      box-shadow: 0 0 10px #000;
	  
    }
	.line_heightDiv
	{
		line-height:45px !important;
	}
	.textdecoration span::hover{
		text-decoration:none;
		
	}

	.navbar-nav li {
		font-size: 15px;
	}
.text_capital{text-transform:uppercase;}
.overlay
{
	background-color: rgba(0,0,0,0.1);
	position: absolute;
	top: 0px;
	left: 0px;
	right: 0px;
	bottom: 0px;
}
.details-box
{
	background-color: #38465D;
	color:#fff;
	padding:10px;
	border-bottom: 8px solid #F67E40;
	font-size:22px !important
}
.special-font
{
	font-family:Gabriola;
}
#trigger
{
	background-color:#DD8E65;
	padding:3px 5px ;
	font-size:14px;
	color:#fff;
	cursor:pointer;
}
</style>
<script>
var globalValue='';
var Value = '${sessionScope.designationAndLocation}';

var resultValue = Value.split("-");
var resultDesigValue = resultValue[0]
globalValue = resultValue[resultValue.length-1];
</script>
<decorator:head/>
</head>
<body>


<header>
	<nav class="navbarHeader">
		<div class="container">
			<div class="row">
				<div class="col-sm-6">
					<img src="alertDepartment/img/logo.png" class="img-responsive pull-left"/>
				</div>
				<div class="col-sm-6">
					<img src="alertDepartment/img/headerImage.png" class="img-responsive pull-right"/>
				</div>
			</div>
		</div>
		<div class="details-box">
			<div class="container">
				<div class="col-sm-6">
					<h4 class="special-font" style="font-size:22px;text-transform:uppercase;" id="stateAndDistName"></h4>
				</div>
				<div class="col-sm-6 text-right">
					<h4 class="special-font">
						<i class="glyphicon glyphicon-user" style="font-size:14px"></i><span style="font-size:22px;text-transform:uppercase;" id="designationName"> </span> &nbsp;&nbsp;&nbsp;
						<a id="trigger" aria-expanded="false" data-toggle="dropdown" class="fullcollapse textdecoration">
							<i class="fa fa-reorder" style="font-size:14px"> </i> MENU 
						</a>
					</h4>
				</div>
			</div>
		</div>
	</nav>
</header><!-- Top Nav Bar End -->
	
<div class="container">
	<div class="" id="menu" style="z-index:9999">
		<nav class="">
			<h2><i class="fa fa-reorder line_heightDiv" style="color:#fff;font-size:23px;"></i>&nbsp;</h2>
			<ul>
			<c:if test="${fn:contains(sessionScope.USER.entitlements, 'GOVT_DEPARTMENT_ADMIN_ENTITLEMENT_NEW')}">
					<li>
						<a href="alertManagementAction.action"><span class="fa fa-dashboard ico-white"></span><span>&nbsp;&nbsp;Home</span></a>
					</li>
			</c:if>
			<c:if test="${fn:contains(sessionScope.USER.entitlements, 'GOVT_DEPARTMENT_ENTITLEMENT_NEW')}">
					<li>
						<a href="alertUserManagementAction.action"><span class="fa fa-dashboard ico-white"></span><span>&nbsp;&nbsp;Home</span></a>
					</li>
					<li>
						<a href="officerWiseAlertReportAction.action"><span class="fa fa-dashboard ico-white"></span><span>&nbsp;&nbsp;Officer Location Report</span></a>
					</li>
					<li>
						<a href="locationWiseGrivenceReportAction.action"><span class="fa fa-dashboard ico-white"></span><span>&nbsp;&nbsp;Location Wise Report</span></a>
					</li>
			</c:if>
			<c:if test="${fn:contains(sessionScope.USER.entitlements, 'GOVT_DEPARTMENT_DISTRICT_OFFICER_ENTITLEMENT_NEW')}">
					<li>
						<a href="alertDistManagement.action"><span class="fa fa-dashboard ico-white"></span><span>&nbsp;&nbsp;Home</span></a>
					</li>
					<li>
						<a href="officerWiseAlertReportAction.action"><span class="fa fa-dashboard ico-white"></span><span>&nbsp;&nbsp;Officer Location Report</span></a>
					</li>
			</c:if>
			<c:if test="${fn:contains(sessionScope.USER.entitlements, 'GOVT_DEPARTMENT_DISTRICT_OFFICE_ENTITLEMENT_NEW')}">
					<li>
						<a href="alertDistOfficeManagement.action"><span class="fa fa-dashboard ico-white"></span><span>&nbsp;&nbsp;Home</span></a>
					</li>
					<li>
						<a href="officerWiseAlertReportAction.action"><span class="fa fa-dashboard ico-white"></span><span>&nbsp;&nbsp;Officer Location Report</span></a>
					</li>
			</c:if>
			<c:if test="${fn:contains(sessionScope.USER.entitlements, 'GOVT_DEPARTMENT_DISTRICT_OFFICER_ENTITLEMENT_NEW')}">
				<li>
					<a href="createMeekosamGrievanceAction.action"><span class="fa fa-dashboard ico-white"></span><span>&nbsp;&nbsp;New Grievance</span></a>
				</li>
			</c:if>
				<li>
					<a href="govtLogoutAction.action"><span class="fa fa-sign-out ico-white"></span><span>&nbsp;&nbsp;LOGOUT (${sessionScope.officerName})</span></a>
				</li> 
				<li>
					<img style="padding:55px 40px;border-bottom:0px" src="alertDepartment/img/Menu_logo.png" alt="logo" class="img-responsive"/>
				</li>
			</ul>
		</nav>
	</div>
</div>
<section>
	<decorator:body/>
</section>
<footer class="footer">
	<div class="container">
		<div class="col-sm-8" style="margin-top: 20px;">
			<p class="text-right" style="font-size: 18px;font-family:Gabriola">All &copy; Government Of Andhra Pradesh</p>
		</div>
		<div class="col-sm-4 text-right">
			<p style="margin-bottom:0px;font-family:Gabriola"><a target="_new" href="http://www.itgrids.com"><img src="alertDepartment/img/itgridsLogo.png" style="height:30px;width:70px"/></a></p>
			<p><small style="font-size: 85%;font-family:Gabriola">Powered By ITGRIDS INDIA PVT.LTD</small></p>
		</div>
	</div>
</footer>
<script type="text/javascript" src="alertDepartment/alertexpandcollapse/jquery.multilevelpushmenu.js"></script>
<script type="text/javascript" src="alertDepartment/alertexpandcollapse/fullexpandcollapse.js"></script>
<script>
$("#stateAndDistName").html(globalValue);
$("#designationName").html(resultDesigValue);
if(Value != "")
{
	$(".footer").css("background-color","#4e5d76")
}
</script>

</body>
</html>