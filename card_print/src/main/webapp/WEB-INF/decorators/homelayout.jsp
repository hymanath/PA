<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title class="getHeaderName"></title><!-- Give Title Name IN CORRESPONDING JSP PAGES  -->
<link href="dist/img/AP_logo.png" rel="icon" type="image/x-icon" />
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto:400" rel="stylesheet" type="text/css">

<decorator:head/>
 </head>
<body>

  <!--  header -->
<header>
	<div class="headerpart1">
        <div class="container">
            <div class="row">
                <div class="col-md-2 col-md-offset-5 col-xs-2 col-xs-offset-5">
                    <!-- <img src="dist/img/AP_logo.png" class="img-responsive img-login" alt="LOGO"> -->
                </div>
                <div class="col-md-12 col-xs-12 col-sm-12 text-center">
                    <h3 class="m_0  text-bold header_text_color">PRINTING APPLICATION</h3>
                    <!--<h3 class="m_0 header_text_color">FOR UNORGANIZED WORKERS</h3> -->
                    <!--<h4 class="m_0 header_white">INSURANCE REGISTRATION</h4> -->
					
					<div class="dropdown pull-right">
					  <button id="dLabel" class="" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						Menu
						<span class="caret"></span>
					  </button>
					  <ul class="dropdown-menu" aria-labelledby="dLabel" style="padding: 10px;">
						<li><a tabindex="-1" href="logoutAction.action">Sign Out</a></li>
					  </ul>
					</div>

					
                </div>
            </div>
			
        </div>
    </div>
    
</header>

<decorator:body/>

<script>

</script>
</body>
</html>

