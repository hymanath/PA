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
	<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/faviIcon.jpg">
	<!--Bootstrap styles file-->
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link rel="stylesheet" href="css/style.css">
	

	<!--Script file
	<script type="text/javascript" src="js/jquery.js"></script>-->
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
    
	<% if(request.getRequestURL().indexOf("partyanalyst.com") != -1){

%>

<script type="text/javascript" src="js/googleAnalytics/googleAnalytics.js"></script>

<% }

%>
	<%-- <script>
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
	</script> --%>
	<style>
	body{color:#5B5B5B;}
	
.background
{
background:#ffffff;
margin:-1px 0px 0px 0px ;
}

.header2 {
     position: absolute;
    top: -126px;
	left:-2%;
}
.gradlightblack{
	margin-top:10px;
}

.header-right-sec{width:750px;}
.lr-sec{padding:14px 5px 5px;}
#menu ul.menu li{z-index: 999;}
</style>
<decorator:head/>
</head>
<body>

<table style="border-collapse: collapse;" width="100%">
<tr>
<td>

  <div class="container-fluid headerBg" style="padding-left: 0px; padding-right: 0px;">
		<!---Header----->
		<div class="container">	
		<div class="row">
			<!----Logo----->
			<div class="span4">
				<img src="images/logo.png" alt="Telugudhesham party logo" />		
			</div>
			<!-----Nav main div---->
			<div class="span8">
				<div class="row-fluid">
					<!----Member Area Div---->
					<div class="span12">
						<ul class="nav nav-pills pull-right memberArea">
						  <li><a href="#">Login</a></li>
						  <li class="selected"><a href="#">Register</a></li>
						</ul>
					</div>
					<!------Menu div-------->
					<div class="span12">
						<ul class="nav nav-pills navMenu pull-right">
						  <li class=""><a href="#">Home</a></li>
						  <li class=""><a href="#">About us</a></li>
						  <li class=""><a href="#">News</a></li>
						  <li class=""><a href="#">Videos</a></li>
						  <li class=""><a href="#">Contact us</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		</div>
		</div>

</td>
</tr>

<tr>
<td class="background" border="0">
<div style="width:960px;position:relative;margin:0 auto;">
<center>

<div class="header2">
<!--Facebook like icon-->
	<div style="float:left;margin-left:80px;margin-top:15px;" class="fb-like" data-href="http://www.facebook.com/PartyAnalyst" data-send="false" data-layout="button_count" data-width="0" data-show-faces="false">
	</div>
	<!--Facebook and twitter follow us
		<div class="follow-us" style="margin-left:180px;">
            <ul>
              <li><a href="http://twitter.com/#!/partyanalyst" target="_blank"><img src="./images/new_homepage/twitter.gif" alt="" height="30px" width="30px"/></a></li>
              <li><a title="Facebook" href="http://www.facebook.com/PartyAnalyst" target="_blank"><img src="./images/new_homepage/facebook.gif" alt="" height="30px" width="30px"/></a></li>
            </ul>
            <h5 style="color:#08AAEC;">follow us</h5> 
		</div>-->
</div>

</center>
</div>
</td>
</tr>
<!--BODY -->
<tr>
	<td class="background" border="0">
 		<div id="contenttable" class="background">
			<decorator:body/>
		</div>
	</td>
</tr>
<tr>
<td>
	<!-----Footer---->
	  <footer>
        <p class="text-center">&copy; Telugudesham Party 2013</p>
      </footer>
  
  
</div>
</td></tr>
</table>
<!--FOOTER SECTION END-->

</body>
</html>