<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Card Print Login</title>
<link href="dist/img/AP_logo.png" rel="icon" type="image/x-icon" />
<!--<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/Icomoon/style.css" rel="stylesheet" type="text/css">-->
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/css/style.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<script src="js/sha512.js"></script>
<style type="text/css">
body
{
	background:#ffcc2d url(dist/img/TDP-BG.png) repeat scroll 0 0;
	
	
}
.img-login{
	display: block;  margin-left: auto;margin-right: auto;margin-top:10px
}
</style>
</head>

<body>
<header>
	<div class="headerpart1">
        <div class="container">
            <div class="row">
                    <!-- <img src="dist/img/AP_logo.png" class="img-responsive img-login" alt="LOGO">-->
                <div class="col-md-12 col-xs-12 text-center col-sm-12 m_top10">
                     <h3 class="m_0  text-bold header_text_color">TDP MEMBERSHIP CARD PRINTING</h3>
                    <!--<h3 class="m_0 header_text_color">FOR UNORGANIZED WORKERS</h3>-->
                    <!--<h4 class="m_0 header_white">INSURANCE REGISTRATION</h4>-->
                </div>
            </div>
        </div>
    </div>
</header>
<section>
        <div class="container">
            <div class="row">
                
               <!-- <div class="col-md-12 col-xs-12 col-sm-12" style="padding:15px">
                	<h4 class="m_0 text-bold text-center">WELCOME USER</h4>
                    <h4 class="m_0 text-center">PLEASE ENTER USERNAME AND PASSWORD</h4>
                </div> -->
                <div class="col-md-6 col-md-offset-3 col-xs-12 col-sm-8 col-sm-offset-2" style="margin-top:100px">
                	<div class="login-box" style="height:300px">
                    	<div class="row">
                        	<div class="col-md-8 col-md-offset-2 col-xs-12 col-sm-8 col-sm-offset-2">
                                <label class="m_top20">USER NAME  <span id="userNameError" style="color:red"></span></label>
                                <div class="input-group input-g">
                                	<span class="input-group-addon ">
	                                    <img src="dist/img/username.png" alt="user">
                                    </span>
                                	<input type="text" class="form-control" id="uname" placeholder="Username">
                                </div>
                                <label class="m_top20">PASSWORD  <span id="passwordError" style="color:red"></span></label>
                                <div class="input-group input-g">
                                	<span class="input-group-addon ">
	                                    <img src="dist/img/password.png" alt="user">
                                    </span>
                                	<input type="password" class="form-control" id="password" placeholder="******">
                                </div>
	                        </div>
							
                            <div class="col-md-8 col-md-offset-2 col-xs-12 col-sm-8 col-sm-offset-2 m_top20">
							<div id="successDiv"></div>
                            	<button onclick="checkUserLogin()" class="btn btn-success btn-upload btn-block m_top10" style="background:#449D44">
                                	<img src="dist/img/login.png">LOGIN
									<img src="images/ajax-loader.gif" style="padding-left:10px;display:none;" width="25" height="15" id="processingImg"/></button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</section>
<!--
 <footer>
 	<div class="container">
    	<div class="row">
        	<div class="col-md-12">
            	<p class="text-center header_white m_top10">All &copy; 2015</p>
            	<p class="text-center header_white m_top10"><a href="http://www.itgrids.com/" style="color:#ffffff;">Powered by IT Grids(India) Pvt Ltd.</a></p>
            </div>
        </div>
    </div>
 </footer>  	
-->
<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<!--<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>-->

<script type="text/javascript">
var uniqueKey = '${sessionScope.uniqueKey}';
$('#uname').keypress(function (e) {
	 var key = e.which;
	 if(key == 13)  // the enter key code
	  {
		 checkUserLogin();
	    return false;  
	  }
	}); 
$('#password').keypress(function (e) {
	 var key = e.which;
	 if(key == 13)  // the enter key code
	  {
		 checkUserLogin();
	    return false;  
	  }
	});
function checkUserLogin()
{
	$("#userNameError").html("");
	$("#passwordError").html("");
	$("#successDiv").html("");
	var str ='';
	var flag = true;
	var uname = $.trim($("#uname").val());
	var pwd = $.trim($("#password").val());
	if(uname.length == 0)
	{
		$("#userNameError").html("Username Required");
		flag = false;
	}
	if(pwd.length == 0)
	{
		$("#passwordError").html("Password Required");
		flag = false;
	}
	if(!flag)
	{
		return false;
	}
	var shaPwd = hex_sha512(''+pwd+'');
	var shaEle = shaPwd + uniqueKey;
	var shaHash = hex_sha512(''+shaEle+'');
	var shauser = hex_sha512(''+uname+'');
	$("#processingImg").show();
	var jObj = {
		username : shauser,
		password : shaHash,
		key   : uniqueKey,
		task:"checkUser"
	}
		$.ajax({
	          type:'POST',
	          url: 'validateUserLogin.action',
	          dataType: 'json',
	          data: {task:JSON.stringify(jObj)},

	          success: function(result){ 
				  $("#processingImg").hide();
				 if(result.resultCode == 1)
				  {
					  $("#userNameError").html("Invalid Credentials");
				  }
				  else if(result.resultCode == 0)
				  {
					$("#successDiv").html("<b>Login Successful, Please Wait...</b>").css("color","green");
					window.location.href = "cardPrintUpdationAction.action" ;
					/* if(result.redirectUrl != '')
					   window.location.href = result.redirectUrl ;
				   else
					   window.location.href = "insuranceDashBoardAction.action" ; */
					
				  }
			  }
		});
}

</script>
</body>
</html>
