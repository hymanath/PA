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
<style type="text/css">
.navbarHeader {
   background: #555555 none repeat scroll 0 0;
    height: 60px !important;
    margin-bottom: 0;
}
.navbarHeader .navbar-brand, .navbarHeader .navbar-brand:active, .navbarHeader .navbar-brand:hover, .navbarHeader .navbar-brand:focus {
    color: rgb(255, 204, 45);
    font-family: "Oswald",sans-serif;
    padding: 5px 0;
}
.navbarHeader .navbar-brand img {
    display: inline-block;
    height: 50px;
    width: 210px;
}
</style>
<decorator:head/>
</head>
<body>
<header>
	<nav class="navbar navbar-default navbarHeader">
	  <div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
		  <a class="navbar-brand" href="#"><img src="newCoreDashBoard/img/APLOGO.jpg" class="img-responsive"/></a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse " id="bs-example-navbar-collapse-1">
          <ul class="nav navbar-nav navbar-right">
          	<li class="dropdown profileDropDown">
              <a class="dropdown-toggle" style="color:#fff;" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> ${sessionScope.officerName}<span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="govtLogoutAction.action">LOGOUT</a>  </li>
              </ul>
            </li>
          </ul>
        </div><!-- /.navbar-collapse -->
	  </div><!-- /.container-fluid -->
	</nav>
</header>
<section>
	<decorator:body/>
</section>
<footer>
	<div class="row-fluid">
		<div class="col-md-12 col-sm-12 col-xs-12 text-center">
			All &copy; Government Of Andhra Pradesh
		<div>
	<div>	
</footer>
</body>
</html>