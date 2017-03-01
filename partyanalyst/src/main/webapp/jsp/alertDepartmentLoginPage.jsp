<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Government Login</title>
<link rel="icon" type="image/png"  href="alertDepartment/img/Fevicon.png">
<link type="text/css" rel="stylesheet" href="alertDepartment/css/bootstrap.min.css" media="screen" />
<link type="text/css" rel="stylesheet" href="alertDepartment/css/login.css" media="screen" />
<link href="alertDepartment/css/animate.css" rel="stylesheet" type="text/css">
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
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css"> 
 	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css">
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/calendar.css">      

<!-- YUI Dependency files (End) -->
<style type="text/css">
header
{
	display:none;
}
</style>
</head>
<body>
<div class="header">
	<div class="container">
	<h3 class="text-left"><img src="alertDepartment/img/logo.png" alt="logo"/><span>ALERT MANAGEMENT SYSTEM</span></h3>
	</div>
</div>
<section class="main-infograph">
  <article class="container main-infograph-block m_top30">
    <div class="col-md-4 col-sm-6 col-md-offset-1 col-xs-12 main-graph-circles-block">
      <div class="cc main-graph-circles scroll-animate ">
        <i class="circle"></i>
        <i class="circle"></i>
        <i class="circle"></i>
        <i class="circle"></i>
        <span class="center-circle ico-Wela"><img src="alertDepartment/img/Fevicon.png" style=""/></span>
      </div>
    </div>
	<form name="loginForm" method="POST" onsubmit="javascript: ajaxCallForLoginPopup(); return false;">
		<div class="col-md-4 col-md-offset-2 col-sm-4 col-xs-12 main-infograph-info">
			<h3 class="main-infograph-title">LOGIN</h3>
			<div class="loginFormBox">
				<div class="input-group">
					<span class="input-group-addon">
						<i class="glyphicon glyphicon-user"></i>
					</span>
					<input type="text" class="form-control" id="userName1" placeholder="User Name" class="url"/>
				</div>
				<div class="input-group">
					<span class="input-group-addon">
						<i class="glyphicon glyphicon-lock"></i>
					</span>
					<input type="password" class="form-control" id="passWord_Id1" placeholder="Password" class="url"/>
				</div>
			
			</div>
			<input type="submit" class="signin btn btn-success btnWhite" id="submit1" value="LOGIN" style="margin-top:20px;"/>
			<div id="ajaxcallimage"  style="display:none;margin-top:10px">
				<font  style="font-size:small;">Sending Your Request. Please wait...</font>
				<img src="images/icons/loading.gif" width="18" height="11"/>
			</div>
			<div id ="LoginErrorMessageDiv" style="color:red; margin-left: 2px;"></div>
		</div>
	</form>
  </article>
  <div class="container">
  <div class="col-md-12 col-xs-12 col-sm-12 borderLines">
	  <li class="borderLine"></li>
	  <li class="borderLine"></li>
	  <li class="borderLine"></li>
	  <li class="borderLine"></li>
	  <li class="borderLine"></li>
  </div>
  </div>
</section>
<script>
$("#userName1").focus();
var userip = "";
setTimeout(function(){
	$(".cc").removeClass("scroll-animate");
},100);
setTimeout(function(){
	$(".cc").addClass("scroll-animation");
	$(".borderLine").addClass("borderLineAnimate");
},150);
</script>
<script type="text/javascript" src="https://l2.io/ip.js?var=userip"></script>
</body>
</html>