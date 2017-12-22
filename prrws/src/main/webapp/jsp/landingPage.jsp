<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>MINISTER DASHBOARD</title>
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
<header>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-1 col-xs-3">
				<img src="Assests/img/aplogo.png" class="logoCls" alt="logo">
			</div>
			<div class="col-sm-9 col-xs-6">
				<h5 style="margin-bottom: 0px;margin-top: 10px;color: #EC2027"><b>MINISTER <span style="font-size: 10px;">DASHBOARD</span></b></h5>
				<p style="font-size: 10px;color : #22A67E"><b>Information Technology, Panchayath Raj & <br/>
				Rural Development</b></p>
			</div>
			<div class="col-sm-2 col-xs-3">
			<!--<a class="btn btn-primary btn-large btn-block btnSearch m_top20 validateLoginCls" style="display:inline-block; width:95px;" style="cursor:pointer;" >Login</a>-->
			</div>
		</div>
	</div>
</header>
<section>
	<div class="landingmenu-block">
		<div class="container">
			<div class="row" style="color:white;">
				<div class="col-sm-2 text-center">
					<div class="block" landing-link="favourite" id="favourite">
						<div style="height:70px;width:100%;margin:auto;">
							<img src="Assests/img/Path 233.png" alt="FAVOURITE" style="display:block;margin:auto">
						</div>
						<h5>FAVOURITES</h5>
					</div>
				</div>
				<div class="col-sm-2 text-center">
					<div class="block" landing-link="fms" id="fmsMenu" attr_blockId="2">
						<div style="height:70px;width:100%;margin:auto;">
							<img src="Assests/img/Group 2353.png" alt="Fund Management" style="display:block;margin:auto">
						</div>
						<h5>FUND MANAGMENT SYSTEM</h5>
					</div>
				</div>
				<div class="col-sm-2 text-center">
					<div class="block" landing-link="panchayat" id="panchayatRajMenu" attr_blockId="3">
						<div style="height:70px;width:100%;margin:auto;">
							<img src="Assests/img/Group 2354.png" alt="Panchayat Raj" style="display:block;margin:auto">
						</div>
						<h5>PANCHAYAT RAJ</h5>
					</div>
				</div>
				<div class="col-sm-2 text-center">
					<div class="block" landing-link="rd" id="rwsMenu" attr_blockId="4">
						<div style="height:70px;width:100%;margin:auto;">
							<img src="Assests/img/Path 232.png" alt="Rural Development" style="display:block;margin:auto">
						</div>
						<h5>RURAL DEVELOPMENT</h5>
					</div>
				</div>
				<div class="col-sm-2 text-center">
					<div class="block" landing-link="rws" id="ruralDevelopmentMenu" attr_blockId="5">
						<div style="height:70px;width:100%;margin:auto;">
							<img src="Assests/img/Group 2355.png" alt="Rural Water Supply" style="display:block;margin:auto">
						</div>
						<h5>RURAL WATER SUPPLY</h5>
					</div>
				</div>
				<div class="col-sm-2 text-center">
					<div class="block" landing-link="itec" id="itecMenu" attr_blockId="6">
						<div style="height:70px;width:100%;margin:auto;">
							<img src="Assests/img/Group 2356.png" alt="ITEC" style="display:block;margin:auto">
						</div>
						<h5>IT E & C</h5>
					</div>
				</div>				
			</div>
		</div>
	</div>
</section>
<section style="padding:20px 0px;margin-bottom: 45px;" id="showMainBlock">
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-6 col-sm-offset-3 text-center">
				<img src="Assests/img/Group 2329.png" alt="image">
				<h4 style="color: #EC2027"><b>NARA LOKESH</b></h4>
				<p style="font-size: 12px;">Minister for Information Technology,<br/> Panchayath Raj and Rural Development,<br/>(Government of Andhra Pradesh)</p>
			</div>
		</div>
	</div>
</section>
<section style="margin-bottom: 100px" id="favouriteListMenu" landing-block="favourite" class="showhideCls">
	<div class="container">
		<div class="row">
			<div id="favouriteComponentDivId"></div>
		</div>
	</div>
</section>
<div id="blockWiseComponentDivId2"></div>
<div id="blockWiseComponentDivId3"></div>
<div id="blockWiseComponentDivId4"></div>
<div id="blockWiseComponentDivId5"></div>
<div id="blockWiseComponentDivId6"></div>
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
<div class="modal fade" id="blockModalMessageDivId" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document" >
    <div class="modal-content modal-custom">
      <div class="modal-body">
       <h4 id="blockOperationStatusHeadingId" style="text-align: center; color: green; font-weight: bold;"></h4>
	   <div id="processingImage"></div>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<input type="hidden" id="hiddenFieldId">
<div class="modal fade" id="validateModalId" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
	<button class="close" type="button" data-dismiss="modal" style="border-left-width: 0px; width: 52px; padding-top: 3px;">×</button>
		<h1 class = "text-center m_top10 " ><font size ="4">LOGIN DETAILS</font></h1>
        </button>
     <div class="modal-body">
		 <div class="row m_top20">
			  <div class="col-md-6 col-md-offset-2 ">
				  <input type="text" class="form-control " value="" placeholder="UserName" id="loginNmeIds" >
					<span id="statusUserId" ></span>		
			  </div>
		  </div>
		  <div class="row m_top20">
			  <div class="col-md-6 col-md-offset-2 margin-top: 10px;">
				<input type="password" class="form-control " value="" placeholder=" Passord" id="loginPassIds" >
				<span id="statusMessagePwdId"></span>	
			 </div>
		 </div>
		  <div class="row m_top20">
			   <div class="col-md-6 col-md-offset-2 " >
						<a class="btn btn-primary btn-large btn-block btnSearch m_top20" style="display:inline-block;width: 179px;" style="cursor:pointer; margin-left: -4px; padding-left: 9px; width: 267px;" onClick="userLoginPopUpDetails();" >SIGN IN</a>
						<img src="Assests/images/spinner.gif" style="width:40px;height:40px;margin-left:10px;margin-top:10px;display:none; " id="spinnerImg"/>
				</div>
		</div>
    </div>
      <p id="statusMessageId"></p>
</div>	
</div>  
</div>  	
<script src="Assests/js/jquery-1.11.3.js" type="text/javascript"></script>
<!--<script src="Assests/MaterialKit/js/jquery-3.2.1.js" type="text/javascript"></script>
<script src="Assests/MaterialKit/js/bootstrap.min.js" type="text/javascript"></script>
<script src="Assests/MaterialKit/js/material.min.js"></script>-->
<script src="Assests/Plugins/dragAndDrop/Sortable.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="Assests/js/bootstrap.js" type="text/javascript"></script>
<script type="text/javascript" src="Assests/fundManagament/landingPage.js"></script>
</body>
</html>