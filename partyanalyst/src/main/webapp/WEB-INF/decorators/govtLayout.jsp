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
</style>
<script>
var globalValue='';
var Value = '${sessionScope.designationAndLocation}';
var resultValue = Value.split("-");
if(resultValue[resultValue.length-1] == "AP"){
	globalValue = resultValue[resultValue.length-1]+" "+"State";
}else{
	globalValue = resultValue[resultValue.length-1]+" "+"District";
}

</script>
<decorator:head/>
</head>
<body>


<header >
	  <div class="container-fluid navbarHeader">
		<div class="navbar-header ">
		  <a class="navbar-brand" href="#"><img src="alertDepartment/img/Alert_Management_Logo_Header.png" class="img-responsive"/>
		  <h4 style="margin-left:20px !important;display:inline-block;font-family:Gabriola;color:#fff;font-size: 24px;" id="stateAndDistName"></h4> </a>
		</div>
		<nav role="navigation" class="collapse navbar-collapse bs-navbar-collapse ">		 
		  <ul class="nav navbar-nav navbar-right" >
			<li style="margin-right:30px;margin-top: 13px;font-family:Gabriola;font-size:18px" class="">
				<div class="text_capital" style="cursor:default;color: #fff;">
					<div style="display: inline-block; position: relative; top: -10px; font-size: 20px;">
						<i class="glyphicon glyphicon-user"></i>
					</div>
					<div style="display: inline-block;">
						<p>&nbsp;&nbsp;&nbsp;${sessionScope.officerName}</p>
						<p style="margin-left: 7px ! important; line-height: 10px ! important;font-size:15px">${sessionScope.designationAndLocation}</p>
					</div>
				</div>
				<!--<div class="text_capital" style="cursor:default;color: #fff;">
					<div>
						<i class="glyphicon glyphicon-user"></i>
					</div>
					<div>
						<p>&nbsp;&nbsp;&nbsp;${sessionScope.officerName}</p>
						<p style="margin-left: 27px;">${sessionScope.designationAndLocation}</p>
					</div>
				</div>-->
			</li>
			<div class="pull-right" style="margin-top: 12px;">
				
				<a id="trigger" aria-expanded="false" data-toggle="dropdown" class="fullcollapse textdecoration">
					<span class="fa fa-reorder" style="font-size: 24px; color: #fff;cursor:pointer;"> </span>
				</a>
			</div>
			
		  </ul>
		 
		</nav>
	  </div>
	</header><!-- Top Nav Bar End -->
	
<div class="container">
	<div class="" id="menu" style="z-index:9999">
		<nav class="">
			<h2><i class="fa fa-reorder line_heightDiv" style="color:#fff;font-size:23px;"></i>&nbsp;</h2>
			<ul>
				<c:if test="${fn:contains(sessionScope.USER.entitlements, 'AP_GOVT_LOCATION_WISE_GRIEVANCE_REPORT' )}">
				<li>
					<a href="locationWiseGrivenceReportAction.action"><span class="fa fa-dashboard ico-white"></span><span>&nbsp;&nbsp;Location Wise Report</span></a>
				 </li>
				 </c:if>
				 <li>
					<a href="govtLogoutAction.action"><span class="fa fa-sign-out ico-white"></span><span>&nbsp;&nbsp;LOGOUT</span></a>
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
			<p class="m_top25 text-right" style="font-size: 18px;font-family:Gabriola">All &copy; Government Of Andhra Pradesh</p>
		</div>
		<div class="col-sm-4 text-right">
			<p style="margin-bottom:0px;font-family:Gabriola;font-family:Gabriola">Powered By</p>
			<p style="margin-bottom:0px;font-family:Gabriola"><a target="_new" href="http://www.itgrids.com"><img src="alertDepartment/img/log_itgrids.png" style="height:30px;width:70px"/></a></p>
			<p><small style="font-size: 85%;font-family:Gabriola">ITGRIDS INDIA PVT.LTD</small></p>
		</div>
	</div>
</footer>
<script type="text/javascript" src="alertDepartment/alertexpandcollapse/jquery.multilevelpushmenu.js"></script>
<script type="text/javascript" src="alertDepartment/alertexpandcollapse/fullexpandcollapse.js"></script>
<script>
$("#stateAndDistName").html(globalValue);
if(Value != "")
{
	$(".footer").css("background-color","#4e5d76")
}
</script>

</body>
</html>