<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
  <meta charset="UTF-8">
  <title>Representation Request Entry Login</title>
	<link href="Assests/css/bootstrap.css" rel="stylesheet" type="text/css">
	<link href="Assests/css/style.css" rel="stylesheet" type="text/css"/>
	<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
</head>

<body>
<main>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-6 col-md-offset-3 col-sm-6 col-sm-offset-3">
				<div class="login-block">
					<div class="row">
						<div class="col-sm-7">
						<div class="col-sm-12">
							<h3>Login</h3>
						</div>
							<div class="login-block-inner">
								<!--<h2>Login</h2>-->
								<label class="login-field-icon fui-user" for="login-name" style="margin-top:30px">USERNAME
									<input type="text" class="form-control" value="" placeholder="" id="login-name">
									<span id="statusMessage"></span>	
								</label>
								<label class="login-field-icon fui-lock m_top20" for="login-pass">PASSWORD
									<input type="password" class="form-control" value="" placeholder="" id="login-pass" onkeypress="searchKeyPress(event);">
									<span id="statusMessagePwd"></span>	
								</label>
								<div class="row" style="margin-left: 0px;">
								<a class="btn btn-primary btn-large btn-block btnSearch m_top20" style="display:inline-block" onClick="userLogin();" id="signinId" style="cursor:pointer;">SIGN IN</a>
								<img src="Assests/images/spinner.gif" style="width:40px;height:40px;margin-left:10px;margin-top:10px;display:none;" id="spinnerImg"/>
								</div>
							</div>	
							<p id="successMessage"></p>	
						</div>
					</div>
					<div class="row">
						<div class="col-sm-11">
							<h5 style="text-decoration:underline;color:#279ffc" class="pull-right"><a href="MGNREGSDashboard" target="_blank">Go To DashBoard</a></h5>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</main>
<footer>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-6 col-md-offset-3 col-sm-6 col-sm-offset-3">
				<div class="row">
					<div class="col-sm-6 col-sm-offset-6">
						<img src="Assests/images/itg_logo.png" class="pull-right itgrids-logo" width="120" height="78" alt=""/>
					</div>					
				</div>
			</div>
		</div>
		
	</div>
</footer>
<script type = "text/javascript" src = "Assests/js/jquery-1.11.3.js"></script>
<script src="Assests/js/login.js" type="text/javascript"></script>
<script>

function userLogin(){
	$("#statusMessage").html("");
	$("#statusMessagePwd").html("");
	$("#successMessage").html("");
	var userName = $("#login-name").val();
	var password = $("#login-pass").val();
	var errorStr = '';
	var errorPwdStr='';
	if(userName == 0){
		errorStr += "<p style='color:red'>Username is required</p>";
	}
	if(password == 0){
		errorPwdStr += "<p style='color:red'>Password is required</p>";
	}
	if(errorStr.length >0)
	{
		$('#statusMessage').html(errorStr);
		return ;
	}
	if(errorPwdStr.length >0)
	{
		$('#statusMessagePwd').html(errorPwdStr);
		return ;
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
</script>
</body>
</html>