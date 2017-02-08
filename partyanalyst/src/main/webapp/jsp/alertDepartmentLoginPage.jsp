<!DOCTYPE html>
<html lang="en">
  <head>
  
	<title>Login</title>
	<link rel="stylesheet"  href="alertDepartment/css/bootstrap.css" >
	<script src="alertDepartment/js/bootstrap.js"></script>
	
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login - Community News Portal</title>
	<link rel="SHORTCUT ICON" type="image/x-icon" href="alertDepartment/images/favicon.png">
    <!-- Bootstrap -->
   <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="css/style.css" rel="stylesheet">
 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

		<style>
		 .requiredFields{color:#FF0020;font-weight:bold;}
		</style>
		<style>
			
			#errorDiv{color:red;}
			.loginAnimBg{margin:auto; background:url('alertDepartment/images/bars-moving-image.gif') repeat 0 0 ;	}
			.border-radius-0{border-radius:0px !important;}
			.login-panel{box-shadow: 0 2px 2px rgba(0, 0, 0, 0.3);}
			.m-top5p{margin-top:5%;}
			.m-top10p{margin-top:10%;}
			.m-top15p{margin-top:15%;}
			.m_top10{margin-top:10px;}
			.m_top20{margin-top:20px;}
			.listdots li{padding-left:20px; }
			.listdots li:nth-child(odd) {background: transparent url('alertDepartment/images/blue_dot.png') no-repeat 0 0 ;}
			.listdots li:nth-child(even) {background: transparent url('alertDepartment/images/green_dot.png') no-repeat 0 0 ;}
			
			.eventsheader,.footerCls{
				display:none;
			}

		</style>
</head>

 <body class="loginAnimBg">
	<div class="container">
		<div class="row m-top5p">
			<div class=" col-md-4">
				<img src="alertDepartment/images/paper_boy_big_image.png" />
			</div>
			
			<div class=" col-md-5 m-top10p">
				<img src="alertDepartment/images/text_paperboy_title.png" />
					<ul class="list-unstyled listdots m_top10">
						<li>Access and Read all major news publications</li>
						<li>Tap, share, Highlight, and also make article annotations</li>
						<li>Analyse the articles</li>
						<li>Dashboard view for detailed analysis</li>
					</ul>
			</div>
			
			<div class=" col-md-3 m-top5p">
				<div class="login-panel panel panel-default m-top10p">
				
					<div class="panel-body" >
						<div id="errorDiv" style="height:42px;"></div>
						<!--div id="userNameErr" class="requiredFields" style="height:42px;"></div>-->
						<img src="alertDepartment/images/paper_boy_login_logo.png" class="img-responsive" style="margin-bottom:10px;" />
						<form role="form" action="loginAction.action" onSubmit="return userLogin();" method="post">
							  <div class="form-group">
								<!---<label for="exampleInputEmail1">Email address</label>--->
								<input type="email" class="form-control border-radius-0 input-lg" name="userName" id="userName" placeholder="Enter Email Id">
							 </div>
							  <div class="form-group">
								 <!--- <!---<label for="exampleInputPassword1">Password</label>--->
								<input type="password" class="form-control border-radius-0 input-lg" name="password" id="password" placeholder="Password">
							 </div>	
							 <input id="signin" class="btn btn-success btn-lg btn-block border-radius-0" type="submit" value="Sign In"/>
							<!-- <a href="javascript:{userLogin()}" class="btn btn-success btn-lg btn-block border-radius-0"> Sign in </a> -->
						</form>
					</div>
					<!--<div class="panel-footer">
					</div>-->
				</div>		
			</div>		
		</div>	
		
	</div>


   
<script type="text/javascript">
	
</script>
</body>

</html>
