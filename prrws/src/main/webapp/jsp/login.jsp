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
								<label class="login-field-icon fui-user m_top20" for="login-name">USERNAME</label><br/>
								<input type="text" class="form-control" value="" placeholder="" id="loginNameId">	
								<label class="login-field-icon fui-lock m_top20" for="login-pass">PASSWORD</label><br/>
								<input type="password" class="form-control" value="" placeholder="" id="passwordId" onkeypress="searchKeyPress(event);">
								<p id="statusMessage"></p>
								<a class="btn btn-primary btn-large btn-block btnSearch m_top20" style="display:inline-block" onClick="userLogin();" id="signinId" style="cursor:pointer;">SIGN IN</a>
								<img src="Assests/images/spinner.gif" style="width:40px;height:40px;margin-left:10px;margin-top:10px;display:none;" id="spinnerImg"/>
							</div>	
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
</body>
</html>