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
<link href="dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="dist/registration/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/css/animate.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto:400" rel="stylesheet" type="text/css">
<link href="dist/dropdown/style.css" rel="stylesheet" type="text/css">
<link href="dist/registration/Menu/component.css" rel="stylesheet" type="text/css">
<link href="dist/registration/Menu/normalize.css" rel="stylesheet" type="text/css">
<link href="dist/registration/Menu/icons.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="dist/registration/Menu/modernizr.custom.js"></script>

<decorator:head/>
 </head>
<body>

  <!--  header -->
<header>
	<div class="headerpart1">
        <div class="container">
            <div class="row">
                <div class="col-md-2 col-md-offset-5 col-xs-2 col-xs-offset-5">
                    <img src="dist/img/AP_logo.png" class="img-responsive img-login" alt="LOGO">
                </div>
                <div class="col-md-12 col-xs-12 col-sm-12 text-center">
                    <h3 class="m_0  text-bold header_text_color">ANDHRA PRADESH STATE SOCIAL SECURITY BOARD</h3>
                    <h3 class="m_0 header_text_color">FOR UNORGANIZED WORKERS</h3>
                    <h4 class="m_0 header_white">INSURANCE REGISTRATION</h4>
                </div>
            </div>
        </div>
    </div>
    <div class="headerpart2">
    	<div class="container">
			<img src="dist/img/AP_logo.png" class="img-responsive scroll-logo col-md-offset-1" alt="LOGO" style="margin-top:-20px;height:65px;width:60px;position:absolute;display:none" >
        	<div class="row">
            	<div class="col-md-2 col-md-offset-5 col-sm-2 col-sm-offset-5 col-xs-2 col-xs-offset-5">
	            	<img src="dist/img/borderimage.png" class="img-responsive img-login border-image" alt="border">
                </div>
                <div class="col-md-10 col-md-offset-1  text-center col-xs-12 col-sm-10 col-sm-offset-1">
                    <h4 class="m_0  text-bold color">
						<span style="margin-left:14px" class="getHeaderName"></span><!-- Give HEADER Name IN CORRESPONDING JSP PAGES -->
						<div style="margin-right:7px;" class="pull-right">
							<i class="glyphicon glyphicon-align-justify" id="trigger"></i>
						 </div>
						 
					</h4>
					
                </div>
				<div class="col-md-1 col-md-offset-10">
				
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

