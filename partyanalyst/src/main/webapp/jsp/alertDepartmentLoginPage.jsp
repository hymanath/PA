<html>
<head>
<title>Government Login</title>
<link rel="icon" type="image/png"  href="alertDepartment/img/GOVT.png">
<link type="text/css" rel="stylesheet" href="alertDepartment/css/bootstrap.min.css" media="screen" />
<link type="text/css" rel="stylesheet" href="alertDepartment/css/custom.css" media="screen" />
<script type="text/javascript" src="alertDepartment/js/alertDepartmentLoginPage.js"></script>
<script type="text/javascript" src="js/md5.js"></script>
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
	
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
	
</head>
<body>
<form name="loginForm" method="POST" onsubmit="javascript: ajaxCallForLoginPopup(); return false;">
	<div class="container" style="margin-top:50px;">
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-primary">
					<div class="panel-body" style="padding:40px;">
						<label>Username</label>
						<input type="text" class="form-control" id="userName1" placeholder="User Name" class="url"/>
						<label>Password</label>
						<input type="password" class="form-control" id="passWord_Id1" placeholder="Password" class="url"/>
						<input type="submit" class="signin btn btn-primary" id="submit1" value="SIGN IN" style="margin-top:20px;"/>
					</div>
					<div id="ajaxcallimage"  style="display:none;">
						<font  style="font-size:small;">Sending Your Request. Please wait...</font>
						<img src="images/icons/loading.gif" width="18" height="11"/>
					</div>
					<div id ="LoginErrorMessageDiv" style="color:red; margin-left: 2px;"></div>
				</div>
			</div>
		</div>
	</div>
</form>
<script>
var userip = "";
</script>
<script type="text/javascript" src="https://l2.io/ip.js?var=userip"></script>
</body>
</html>