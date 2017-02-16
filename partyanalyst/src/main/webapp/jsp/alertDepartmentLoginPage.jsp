<html>
<head>
<title>TDP Party's Election Analysis &amp; Management Platform</title>
<link type="text/css" rel="stylesheet" href="css/loginpagestyle.css" media="screen" />
<script type="text/javascript" src="js/md5.js"></script>
<script type="text/javascript" src="js/loginpopup1.js"> </script>
<script src="https://code.jquery.com/jquery-1.8.3.js"></script>
	<script src="https://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
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
	<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/TDP.gif">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css"> 
 	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css">
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/calendar.css">      

	<!-- YUI Dependency files (End) -->
	<style>
	.signin{line-height:20px; border-radius:4px; border:1px solid #a2c359;padding:13px 120px; color:#525b3d; text-shadow: 1px 1px rgba(255, 255, 255, 0.6);text-decoration:none; font-weight:bold; background: #c4e480; font-size:16px; text-shadow:0px,1px,0px, #fff;
background: url(data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiA/Pgo8c3ZnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgd2lkdGg9IjEwMCUiIGhlaWdodD0iMTAwJSIgdmlld0JveD0iMCAwIDEgMSIgcHJlc2VydmVBc3BlY3RSYXRpbz0ibm9uZSI+CiAgPGxpbmVhckdyYWRpZW50IGlkPSJncmFkLXVjZ2ctZ2VuZXJhdGVkIiBncmFkaWVudFVuaXRzPSJ1c2VyU3BhY2VPblVzZSIgeDE9IjAlIiB5MT0iMCUiIHgyPSIwJSIgeTI9IjEwMCUiPgogICAgPHN0b3Agb2Zmc2V0PSIwJSIgc3RvcC1jb2xvcj0iI2M0ZTQ4MCIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjEwMCUiIHN0b3AtY29sb3I9IiNhY2M4NmYiIHN0b3Atb3BhY2l0eT0iMSIvPgogIDwvbGluZWFyR3JhZGllbnQ+CiAgPHJlY3QgeD0iMCIgeT0iMCIgd2lkdGg9IjEiIGhlaWdodD0iMSIgZmlsbD0idXJsKCNncmFkLXVjZ2ctZ2VuZXJhdGVkKSIgLz4KPC9zdmc+);
background: -moz-linear-gradient(top, #c4e480 0%, #acc86f 100%);
background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#c4e480), color-stop(100%,#acc86f));
background: -webkit-linear-gradient(top, #c4e480 0%,#acc86f 100%);
background: -o-linear-gradient(top, #c4e480 0%,#acc86f 100%);
background: -ms-linear-gradient(top, #c4e480 0%,#acc86f 100%);
background: linear-gradient(to bottom, #c4e480 0%,#acc86f 100%);
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#c4e480', endColorstr='#acc86f',GradientType=0 );}
.signin:hover{background: #c4e480;
background: url(data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiA/Pgo8c3ZnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgd2lkdGg9IjEwMCUiIGhlaWdodD0iMTAwJSIgdmlld0JveD0iMCAwIDEgMSIgcHJlc2VydmVBc3BlY3RSYXRpbz0ibm9uZSI+CiAgPGxpbmVhckdyYWRpZW50IGlkPSJncmFkLXVjZ2ctZ2VuZXJhdGVkIiBncmFkaWVudFVuaXRzPSJ1c2VyU3BhY2VPblVzZSIgeDE9IjAlIiB5MT0iMTAwJSIgeDI9IjEwMCUiIHkyPSIwJSI+CiAgICA8c3RvcCBvZmZzZXQ9IjAlIiBzdG9wLWNvbG9yPSIjYzRlNDgwIiBzdG9wLW9wYWNpdHk9IjEiLz4KICAgIDxzdG9wIG9mZnNldD0iMTAwJSIgc3RvcC1jb2xvcj0iI2FjYzg2ZiIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgPC9saW5lYXJHcmFkaWVudD4KICA8cmVjdCB4PSIwIiB5PSIwIiB3aWR0aD0iMSIgaGVpZ2h0PSIxIiBmaWxsPSJ1cmwoI2dyYWQtdWNnZy1nZW5lcmF0ZWQpIiAvPgo8L3N2Zz4=);
background: -moz-linear-gradient(45deg, #c4e480 0%, #acc86f 100%);
background: -webkit-gradient(linear, left bottom, right top, color-stop(0%,#c4e480), color-stop(100%,#acc86f));
background: -webkit-linear-gradient(45deg, #c4e480 0%,#acc86f 100%);
background: -o-linear-gradient(45deg, #c4e480 0%,#acc86f 100%);
background: -ms-linear-gradient(45deg, #c4e480 0%,#acc86f 100%);
background: linear-gradient(45deg, #c4e480 0%,#acc86f 100%);
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#c4e480', endColorstr='#acc86f',GradientType=1 );
}

	</style>
</head>
<body>
	
	<!---Logo Div---->
	<div id="logowrapper">
		<img src="images/tdp-login/login-logo.png">		
	</div><!---Logo Div END---->
	
	<!---Login Div---->
	<div id="wrapper">
		<div id="wrappertop"></div>

		<div id="wrappermiddle">
<div id="forgot_password_window1" style="background-color: #f5f5f5;">
<div id="forgot_password_window_inner1" style="font-size:0.8em"></div>
</div>
			<h2>Login</h2>
<form name="loginForm" method="POST" onsubmit="javascript: ajaxCallForLoginPopup(); return false;">
			<div id="username_input">

				<div id="username_inputleft"></div>

				<div id="username_inputmiddle">
			
					<input type="text" id="userName1" placeholder="User Name" class="url">
					<img id="url_user" src="./images/tdp-login/mailicon.png" >
				
				</div>

				<div id="username_inputright"></div>

			</div>

			<div id="password_input">

				<div id="password_inputleft"></div>

				<div id="password_inputmiddle">
				
					<input type="password" id="passWord_Id1" placeholder="Password" class="url">
					<img id="url_password" src="./images/tdp-login/passicon.png" alt="">
				
				</div>

				<div id="password_inputright"></div>

			</div>

			<div id="submit"> 
				<!--<a class="signin" id="submit1" onclick="ajaxCallForLoginPopup();" >SIGN IN</a>-->
				<input type="submit" class="signin" id="submit1" value="SIGN IN">SIGN IN</input>
				
			</div>


			<div id="links_left">
			<div id="ajaxcallimage"  class = "span3" style="display:none;font-weight:bold;color: #0174DF;font-size:small;width: 345px; height: 17px;">
<font  style="font-size:small;">Sending Your Request. Please wait...</font>
<img src="images/icons/loading.gif" style="padding-left:10px;" width="18" height="11"/>
</div>
<div id ="LoginErrorMessageDiv" style="color:red; margin-left: 2px;"></div>
			<!--<a onclick="showForgotPasswordPanelForPopup();" href="javascript:{}">Forgot your Password?</a>-->

			</div>

			<!-----<div id="links_right"><a href="#">Not a Member Yet?</a></div>---->

		</div>
</form>
		<div id="wrapperbottom"></div>
		
		<div id="powered">
		<p>&copy; 2014 Telugu Desam Party</p>
		</div>
	</div><!---Login Div---->
<script>
/*$("#submit").live("click",function(){
	$("#wrappermiddle").css("height","257px");
});*/
var userip = "";
</script>
<script type="text/javascript" src="https://l2.io/ip.js?var=userip"></script>
</body>
</html>