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
.m_top15
{
	margin-top:15px;
}
</style>
</head>
<body>
<div class="container">
	<form name="loginForm" method="POST" onsubmit="javascript: ajaxCallForLoginPopup(); return false;">
	<div class="row">
		<div class="m_top20 col-xs-12 col-md-6 col-md-offset-3">
			<img class="displayed m_top20 m_bottom20 img-responsive" src="alertDepartment/img/AP_Govt_Logo.png" alt="Andhra Pradesh Goverment Logo"/>
			<div class="m_top20 panel panel-default" style="border:1px solid #ffc019; box-shadow:0 0 30px 10px rgba(0, 0, 0, 0.2)">
			<div class="m_top20panel-body">
				<img class="displayed m_top20 m_bottom20 img-responsive" src="alertDepartment/img/Alert_Management_Logo.png" alt="Andhra Pradesh Goverment Logo">
				<img class="displayed m_top10 image-responsive" src="alertDepartment/img/Officer Login.png" alt="Andhra Pradesh Goverment Logo">
				
				<div class="loginFormBox" style="padding: 10px 80px;">
					<div class="input-group">
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-user"></i>
						</span>
						<input type="text" class="form-control m_top10" id="userName1" placeholder="User Name" class="url"/>
					</div>
					<div class="input-group m_top20 m_bottom20">
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-lock"></i>
						</span>
						<input type="password" class="form-control m_top10" id="passWord_Id1" placeholder="Password" class="url"/>
					</div>			
				</div>
				
				
			</div>
			<div id="ajaxcallimage"  style="display:none;margin-top:10px">
				<font  style="font-size:small;">Sending Your Request. Please wait...</font>
				<img src="images/icons/loading.gif" width="18" height="11"/>
			</div>
			<div id ="LoginErrorMessageDiv" style="color:red; margin-left: 2px;"></div>
			<div class="panel-footer" style="padding: 0px;">			
				<!---<input class="btn btn-primary btn-block btn-lg" value="LOGIN" type="submit"></div>-->
				<input type="submit" class="btn btn-primary btn-block btn-lg signin" id="submit1" value="LOGIN"/>
				
			</div>
		</div>
	</div>
	</form>
</div>
<!--<footer class="footer">
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-8">
				<p class="m_top25 text-right">All &copy; Government Of Andhra Pradesh</p>
			</div>
			<div class="col-sm-4 text-right">
				<p style="margin-bottom:0px">Powered By</p>
				<p style="margin-bottom:0px"><a target="_new" href="http://www.itgrids.com"><img src="alertDepartment/img/log_itgrids.png" style="height:30px;width:70px"/></a></p>
				<p><small style="font-size: 75%;">ITGRIDS INDIA PVT.LTD</small></p>
			</div>
			<div class="col-sm-12 text-right">
				
			</div>
		</div>
	</div>
</footer>-->
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
if($(window).height() > 600)
{
	setTimeout(function(){
		$("footer").addClass("stickyFooter");
	},1000)
}
</script>
<script type="text/javascript" src="https://l2.io/ip.js?var=userip"></script>
</body>
</html>