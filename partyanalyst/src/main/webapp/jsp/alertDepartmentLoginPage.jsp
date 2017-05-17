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
<link href="newCoreDashBoard/Plugins/Slick/slick.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/Slick/slick-theme.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="alertDepartment/js/alertDepartmentLoginPage.js"></script>
<script type="text/javascript" src="js/md5.js"></script>
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Slick/slick.js" type="text/javascript"></script>
	
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
.list-inline li
{
	margin:0px 5px;
}
</style>
</head>
<body>
	<nav>
		<div class="container">
			<div class="row">
				<div class="col-sm-6">
					<img src="alertDepartment/img/logo.png" class="img-responsive pull-left"/>
				</div>
				<div class="col-sm-6">
					<img src="alertDepartment/img/headerImage.png" class="img-responsive pull-right"/>
				</div>
			</div>
		</div>
	</nav>
	<section class="login-box">
		<div class="overlay"></div>
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<h1 class="text-center text-capitalize special-font m_top10">welcome to alert management system</h1>
					<h3 class="special-font text-center m_top10">Alert Management System An Government Of Andhra Pradesh Initiative to expand the  e governance to resolve the public Grievances with transparancy and Smart Approach           </h3>
				</div>
				<div class="col-sm-12">
					<form name="loginForm" method="POST" onsubmit="javascript: ajaxCallForLoginPopup(); return false;">
					<div class="row m_top10">
						<div class="col-sm-4 ">
							<label class="text-capital">username</label>
							<input type="text" class="form-control" id="userName1" placeholder="User Name" class="url"/>
						</div>
						<div class="col-sm-4">
							<label class="text-capital">password</label>
							<input type="password" class="form-control" id="passWord_Id1" placeholder="Password" class="url"/>
						</div>
						<div class="col-sm-4">
							<button class="btn btn-warning text-capital m_top25 btn-block">sign in</button>
						</div>
						<div id="ajaxcallimage"  style="display:none;margin-top:10px">
							<font  style="font-size:small;">Sending Your Request. Please wait...</font>
							<img src="images/icons/loading.gif" width="18" height="11"/>
						</div>
						<div id ="LoginErrorMessageDiv" style="color:red; margin-left: 2px;"></div>
					</div>
					</form>
				</div>
			</div>
		</div>
	</section>
	<section class="departments-box">
		<h4 class="text-capital color_blue text-center">departments that are included in alert management system</h4>
		<div class="container">
			<div class="row">
				<div class="col-sm-12 m_top10">
					<div id="departments"></div>
				</div>
			</div>
		</div>
		<img src="alertDepartment/img/alert logo.png" alt="alert Logo"  class="alert-logo"/>
	</section>
	
	<section class="work">
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<h4 class="text-center text-capital color_blue">how it works</h4>
				</div>
				<div class="row">
					<div class="col-sm-4 box">
						<img src="alertDepartment/img/1.png" class="media-icon"/>
						<img src="alertDepartment/img/2.png" class="media-icon1"/>
						<img src="alertDepartment/img/3.png" class="media-icon2"/>
						<img src="alertDepartment/img/4.png" class="media-icon3"/>
						<img src="alertDepartment/img/5.png" class="media-icon4"/>
					</div>
						<img src="alertDepartment/img/arrow1.png" class="arrow-left1"/>
					<div class="col-sm-4 box">
						<img src="alertDepartment/img/arrow2.png" class="arrows-top"/>
						<img src="alertDepartment/img/sms_mobile.png" class="sms-icon"/>
						<img src="alertDepartment/img/6.png" class="animted-profile"/>
					</div>
						<img src="alertDepartment/img/arrow1.png" class="arrow-left"/>
					<div class="col-sm-4 box">
						<img src="alertDepartment/img/arrow2.png" class="arrows-top1"/>
						<img src="alertDepartment/img/sms_mobile.png" class="sms-icon1"/>
						<img src="alertDepartment/img/7.png" class="completed-icon"/>
						<img src="alertDepartment/img/8.png" class="people-1"/>
						<img src="alertDepartment/img/9.png" class="people-2"/>
						<img src="alertDepartment/img/10.png" class="people-3"/>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-4">
						<p class="text-capitalize">alert creates from different sources like print media,electronic media,call center,collector peshi,facebook,twitter,etc..</p>
					</div>
					<div class="col-sm-4">
						<p class="text-capitalize">identify grievance department and assign to (create SMS alert) specific location level department officer</p>
					</div>
					<div class="col-sm-4">
						<p class="text-capitalize">different department grievance resolved people happy</p>
					</div>
				</div>
			</div>
		</div>
	</section>
<script>
$("#userName1").focus();
var userip = "";

getGovtDepartmentDetails();
function getGovtDepartmentDetails()
{
	$("#govtDepartmentsId").html('');
	$.ajax({
	  type:'GET',
	  url: 'getAllDeptsAction.action',
	  data: {}
	}).done(function(result){
		var str='';
		str+='<ul class="list-inline">';
			if(result != null && result.length > 0)
			{
				for(var i in result)
				{
					if(result[i].name.length > 25)
					{
						str+='<li><img style="display:inline-block" src="alertDepartment/img/'+result[i].id+'.jpg" alt="'+result[i].name+'" onerror="setDefaultImage(this);"/>'+result[i].name.substring(0,25)+'..</li>';
					}else{
						str+='<li><img style="display:inline-block" src="alertDepartment/img/'+result[i].id+'.jpg" alt="'+result[i].name+'" onerror="setDefaultImage(this);"/>'+result[i].name+'</li>';
					}
					
				}
			}
		str+='</ul>';
		$("#departments").html(str);
		$('.list-inline').slick({
			slide: 'li',
			slidesToShow: 4,
			slidesToScroll: 4,
			infinite: false,
			swipe:false,
			touchMove:true,
			autoplay: false,
			autoplaySpeed: 4000,
			variableWidth: true
		});
	});
}
function setDefaultImage(img)
{
	img.onerror = "";
	img.src = "alertDepartment/img/noimg-icon.jpg";
	return true;
}
</script>
<script type="text/javascript" src="https://l2.io/ip.js?var=userip"></script>
</body>
</html>