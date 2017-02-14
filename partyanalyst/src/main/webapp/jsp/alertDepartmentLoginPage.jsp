<!DOCTYPE html>
<html lang="en">
  <head>
  
	<title>Login</title>
	<link rel="stylesheet"  href="alertDepartment/css/bootstrap.css" >
	<script src="alertDepartment/js/bootstrap.js"></script>
	
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<!--<link rel="SHORTCUT ICON" type="image/x-icon" href="alertDepartment/images/favicon.png">-->
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


</head>

 <body class="loginAnimBg">
	<div class="container">
		<div class="row m-top5p">
			<div class=" col-md-3 m-top5p  pull-right">
				<div class="login-panel panel panel-default m-top10p">
				
					<div class="panel-body" >
						<div id="errorDiv" style="height:42px;"></div>

						<form role="form" action="loginAction.action" onSubmit="return userLogin();" method="post">
							  <div class="form-group">
								<input type="text" class="form-control border-radius-0 input-lg" name="userName" id="userName" placeholder="User Name">
							 </div>
							  <div class="form-group">
								<input type="password" class="form-control border-radius-0 input-lg" name="password" id="password" placeholder="Password">
							 </div>	
							 <input id="signin" class="btn btn-success btn-lg btn-block border-radius-0" type="submit" value="Sign In"/>
						</form>
					</div>
				</div>		
			</div>		
		</div>	
		
	</div>


   
<script type="text/javascript">
</script>
</body>

</html>
