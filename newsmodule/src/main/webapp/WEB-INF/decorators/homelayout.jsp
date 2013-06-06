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
	<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet">
	<link rel="stylesheet" href="css/style.css">
	<script type="text/javascript" src="js/loginpopup.js"> </script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.24.custom.min.js"> </script>
	<!--Script file
	<script type="text/javascript" src="js/jquery.js"></script>-->
	<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.core.css"/>
	<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.theme.css"/>
	<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.accordion.css"/>
	<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
	<link  rel="stylesheet" type="text/css" href="styles/landingPage/landingPage.css"/>
	
	<!--YUI SCRIPT-->
	<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/connection/connection-min.js&2.8.2r1/build/datasource/datasource-min.js&2.8.2r1/build/autocomplete/autocomplete-min.js&2.8.2r1/build/element/element-min.js&2.8.2r1/build/container/container-min.js&2.8.2r1/build/menu/menu-min.js&2.8.2r1/build/button/button-min.js&2.8.2r1/build/paginator/paginator-min.js&2.8.2r1/build/datatable/datatable-min.js&2.8.2r1/build/json/json-min.js&2.8.2r1/build/tabview/tabview-min.js"></script>
    
	
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
				<img src="images/Logo.png" alt="Telugudhesham party logo" />		
			</div>
			<!-----Nav main div---->
			<div class="span8">
				<div class="row-fluid">
					<!----Member Area Div---->
					<div class="span12">
						<ul class="nav nav-pills pull-right memberArea">
						 <c:if test="${sessionScope.USER == null}">
						  <li><a href="javascript:{}" onClick="openDialogForLoginWindow()">Login</a></li>
						  <li class="selected"><a href="#">Register</a></li>
						 </c:if>
						 <c:if test="${sessionScope.USER != null}">
						  <li style="padding-top: 6px;">${sessionScope.USER.name}</li>
						   <c:if test="${sessionScope.USER.userType == 'Admin'}">
						    <li><a href="#">Admin</a></li>
						   </c:if>
						  <li class="selected"><a href="logoutAction.action">Logout</a></li>
						 </c:if>
						</ul>
					</div>
					<!------Menu div-------->
					<div class="span12">
						<ul class="nav nav-pills navMenu pull-right">
						  <li class=""><a href="#">Home</a></li>
						  <li class=""><a href="#">About us</a></li>
						  <li class=""><a href="#">News</a></li>
						  <li class=""><a href="#">Videos</a></li>
						   <c:if test="${sessionScope.USER != null}">
						    <c:if test="${sessionScope.USER.userType != '' || sessionScope.USER.userType == 'subuser'}">
						      <li class=""><a href="partyManagementAction.action">Manage Party Profile</a></li>
						    </c:if>
						   </c:if>
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
<div id="login_window">
	<div id="login_window_inner"></div>
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
<script type="text/javascript">
var notLogged = '${notLogged}';
 if(notLogged == 'true')
 {
	 redirectTo = true;
	 openDialogForLoginWindow();
  }
</script>
</body>
</html>

