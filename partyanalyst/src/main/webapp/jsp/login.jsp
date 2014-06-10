<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/TDP.gif">

	<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
	<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
	<link type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" rel="stylesheet" />

	<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet">
	<script type="text/javascript" src="js/md5.js"></script>
	<script type="text/javascript" src="js/loginpopup1.js"> </script>
 <!-- YUI Dependency files (Start) -->
	<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/button-min.js"></script> 	
	<script src="js/yahoo/resize-min.js"></script> 
	<script src="js/yahoo/layout-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
	<script type="text/javascript" src="js/json/json-min.js"></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/tabview-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	<!-- Skin CSS files resize.css must load before layout.css --> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css"> 
 	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css">
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/calendar.css">      

	<!-- YUI Dependency files (End) -->


<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.js"></script>
<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.css" />

<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.filter.css" />
<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.filter.js"></script>

   <style>
 
	/*body{color:#333333!important;}
		
		.mahanadubg{background:#fff url(images/mahaNadu/Mahanadu_bg.gif) repeat-x;}
		.m_top10{margin-top:10px;}
		.m_top15{margin-top:15px;}
		.m_top20{margin-top:20px;}
		.containerBorder{border: 1px solid rgb(204, 204, 204); padding:10px;}
		.MahanaduHeading{height:42px; width:100%; border-bottom:1px solid rgba(0,0,0,.5);border-radius:6px 6px 0px 0px; text-transform:uppercase;line-height:45px;
			background: #ffe933;
			background: url(data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiA/Pgo8c3ZnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgd2lkdGg9IjEwMCUiIGhlaWdodD0iMTAwJSIgdmlld0JveD0iMCAwIDEgMSIgcHJlc2VydmVBc3BlY3RSYXRpbz0ibm9uZSI+CiAgPGxpbmVhckdyYWRpZW50IGlkPSJncmFkLXVjZ2ctZ2VuZXJhdGVkIiBncmFkaWVudFVuaXRzPSJ1c2VyU3BhY2VPblVzZSIgeDE9IjAlIiB5MT0iMCUiIHgyPSIwJSIgeTI9IjEwMCUiPgogICAgPHN0b3Agb2Zmc2V0PSIwJSIgc3RvcC1jb2xvcj0iI2ZmZTkzMyIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjEwMCUiIHN0b3AtY29sb3I9IiNmZmNmMzEiIHN0b3Atb3BhY2l0eT0iMSIvPgogIDwvbGluZWFyR3JhZGllbnQ+CiAgPHJlY3QgeD0iMCIgeT0iMCIgd2lkdGg9IjEiIGhlaWdodD0iMSIgZmlsbD0idXJsKCNncmFkLXVjZ2ctZ2VuZXJhdGVkKSIgLz4KPC9zdmc+);
			background: -moz-linear-gradient(top,  #ffe933 0%, #ffcf31 100%);
			background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#ffe933), color-stop(100%,#ffcf31));
			background: -webkit-linear-gradient(top,  #ffe933 0%,#ffcf31 100%);
			background: -o-linear-gradient(top,  #ffe933 0%,#ffcf31 100%);
			background: -ms-linear-gradient(top,  #ffe933 0%,#ffcf31 100%);
			background: linear-gradient(to bottom,  #ffe933 0%,#ffcf31 100%);
			filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ffe933', endColorstr='#ffcf31',GradientType=0 );
		}
	
#wrapper{
	width: 470px;
	margin: 10px auto 30px;
	position: relative;
}*/
	</style>
</head>
<body>
 <div class="container" style="margin-top:100px;margin-bottom:100px;">	
	    <!----Login Div--------->
<div id="loginDiv" style="margin-left:auto;margin-right:auto;width:420px;">
 <div id="login_window_inner1"> 
 </div>

</div>


<!----Login Div END--------->


	</div>
	<script>
	function buildLoginDiv()
	{
var str='';
str+='<div id="forgot_password_window1" style="background-color: #f5f5f5;">';
str+='<div id="forgot_password_window_inner1" style="font-size:0.8em"></div>';
str+='</div>';

str+='<div class="span3 well" style="border: 5px solid #FEC440; width: 360px;align:center">';
str+='<form name="loginForm" method="POST" onsubmit="javascript: ajaxCallForLoginPopup(); return false;">';
str+='<div id ="LoginErrorMessageDiv" style="color:red; margin-left: 55px;">';
str+='</div>';

str+='<div class="input-prepend" style="margin-left: 55px;">';
str+='<span class="add-on"><i class="icon-envelope"></i></span><input type="text"  class="span2" class="input-small" placeholder="Email" name="userName"  id="userName1" style="width: 200px; position: static;"/>';
str+='</div>';
str+='<div  class="input-prepend" style="margin-left: 55px;">';
str+='<span class="add-on"><i class="icon-lock"></i></span><input type="password"  class="span2" class="input-small" placeholder="Password" name="password" style="width: 200px;position: static;" id="passWord_Id1"/>';
str+='</div>';
str+='<div class = "span3">';
str+='<a href="javascript:{}" onclick="showForgotPasswordPanelForPopup()" style="color: rgb(1, 116, 223); font-size: small; margin-left: 50px;">Forgot Password</a>';
str+='<input id="signin" class="submitButton btn btn btn-primary" type="submit" style="margin-left: 180px; margin-top: -20px; height: 25px; width: 85px; padding-top: 2px;" value="Sign In"/>';
str+='</div>';
str+='<div id="ajaxcallimage"  class = "span3" style="display:none;font-weight:bold;color: #0174DF;font-size:small;width: 345px; height: 17px;">';
str+='<font  style="font-size:small;">Sending Your Request. Please wait...</font>';
str+='<img src="images/icons/loading.gif" style="padding-left:10px;" width="18" height="11"/>';
str+='</div>';
str+='</form><br><br>';
str+='</div>';
//$("#loginDiv").html(str);
$("#login_window_inner1").html(str);

}
	</script>
	<script>
	buildLoginDiv();
	</script>
</body>

</html>