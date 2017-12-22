<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>PITITIONS CELL</title>
<!--<link href="Assests/MaterialKit/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="Assests/MaterialKit/css/material-kit.css" rel="stylesheet"/>
<link href="Assests/MaterialKit/css/landingPage.css" rel="stylesheet"/>-->
<link href="Assests/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="Assests/css/style.css" rel="stylesheet" type="text/less">
<link href="Assests/css/custom.less" rel="stylesheet" type="text/less"/>
<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
<script src="Assests/Plugins/Less/less.js"></script>
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<script src="https://use.fontawesome.com/e94c241642.js"></script>
<style>
	.landing-menu li:hover{
		cursor:pointer;
	}
	.block
	{
		background-color:#383838;
		padding:15px;
		height:130px;
		cursor:pointer;
	}
	.block.active
	{
		background-color:#FF9100
	}
	.menu-top-selection .arrow_box_top
	{
		left:-13px;
		right:none;
	}
	.menu-top-selection .arrow_box_top::before, .menu-top-selection .arrow_box_top::after
	{
		right:none;
		left:20px;
	}
	
</style>
</head>
<body>
<header style="box-shadow:none;">
	<img src="Assests/images/petitionStripGrid.png" class="" alt="logo" style="height:165px;width:100%">
	<div class="col-sm-12">
			<button class="btn btn-success btn-sm pull-right menu-login" style="border-radius:0px;background-color:#AA8440;font-weight:bold;position:relative;top:-70px;">Login</button>
			<div class="menu-data-cls" style="top:-10px;">
				<div class="arrow_box_top">
					<div class="row">
						
							<div class="paddingLogin">
								<div class="col-sm-12">
									<label class="login-field-icon fui-user" for="login-name">USERNAME
										<input type="text" class="form-control m_top5" value="" placeholder="User Name" id="loginName"/>
										<span id="statusErrUserId"></span>	
									</label>
								</div>
								<div class="col-sm-12">
									<label class="login-field-icon fui-lock" for="login-pass">PASSWORD
										<input type="password" class="form-control m_top5" value="" placeholder="Password" id="loginPass" >
										<span id="statusErrPwdId"></span>	
									</label>
								</div>
								<div class="col-sm-12">
									<a class="btn btn-primary btnSearch m_top5" style="display:inline-block" onClick="userLogin();" id="signinId" style="cursor:pointer;">SIGN IN</a>
									<img src="Assests/images/spinner.gif" style="width:40px;height:40px;margin-left:10px;margin-top:10px;display:none;" id="spinnerImg"/>
									<p id="successMessage"></p>	
								</div>
							</div>
						
					</div>
				</div>
			</div>
	</div>
			
</header> 
<section style="padding:20px 0px;margin-bottom: 45px;" id="showMainBlock">
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-6 col-sm-offset-3 text-center">
				<img src="Assests/img/Group 2329.png" alt="image">
				<h3 style="color: #EC2027" style="margin-top:20px;"><b>NARA LOKESH</b></h3>
				<p style="font-size: 16px;">Minister for Information Technology,<br/> Panchayathi Raj and Rural Development,<br/>(Government of Andhra Pradesh)</p>
			</div>
		</div>
	</div>
</section>
<footer class="footerCls">
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12 footer-block">
				<p>Copyright © 2017, All Rights Reserved by Govt of A.P. India</p>
				<p class="pull-right">Designed & Developed by<img src="Assests/img/logo (1).png"></p>
			</div>
		</div>
	</div>
</footer>	
<script src="Assests/js/jquery-1.11.3.js" type="text/javascript"></script>
<script type="text/javascript">

$(document).click(function() {
	$(".menu-data-cls").hide();
});

$(document).on("click",".login-field-icon",function(e){
	e.stopPropagation();
	//$(".menu-data-cls").toggle();
});
$("header").on("click",".menu-login",function(e){
	e.stopPropagation();
	$(".menu-data-cls").toggle();
});

function userLogin(){
	$("#statusErrUserId").html(' ');
	$("#statusErrPwdId").html(' ');
	$("#successMessage").html(' ');
		
	var userName = $("#loginName").val();
	var password = $("#loginPass").val();

	var errorStr = '';
	var errorPwdStr='';
	if(userName == 0 || userName == '' || userName == null || userName.trim().length == 0){
		errorStr += "<p style='color:red'>Username is required</p>";
	}
	if(password == 0 || password == ''  || password == null  || password.trim().length == 0 ){
		errorPwdStr += "<p style='color:red'>Password is required</p>";
	}
	if(errorStr.length >0)
	{
		$('#statusErrUserId').html(errorStr);
		return ;
	}else{
		$('#statusErrUserId').html('');
	}
	if(errorPwdStr.length >0)
	{
		$('#statusErrPwdId').html(errorPwdStr);
		return ;
	}else{
		$('#statusErrPwdId').html('');
	}
	$("#spinnerImg").show();
	 var json = {
	  username:userName,
	  passwordHashText:password
	} 
	/* var json = {
		userName:"itgrids",
		password:"itgrids@123"
	} */
	$.ajax({                
	  type:'POST',    
	  url: 'userLogin',  
	  dataType: 'json',
	data : JSON.stringify(json),
	beforeSend :   function(xhr){
		xhr.setRequestHeader("Accept", "application/json");
		xhr.setRequestHeader("Content-Type", "application/json");
	}  
	}).done(function(result){
		if(result !=  null){
		  if(result.responceCode == 0){
			$("#successMessage").html("<span style='color:red'>Invalid UserName and Password,Please Try Again..<span>");
			$("#spinnerImg").hide();
		  }else if(result.responceCode == 1){
			$("#successMessage").html("<span style='color:green'>Login Successfull,Page is Refreshing,Please Wait...<span>");
			$(location).attr('href', result.url);
			$("#spinnerImg").hide();
		  }
		}
	});
}
$(document).bind('keypress', function(event) {
	 var keyCode = (event.keyCode ? event.keyCode : event.which); 
    if(keyCode == 13){
       $('#signinId').trigger('click');
     }  
}); 
</script>
</body>
</html>