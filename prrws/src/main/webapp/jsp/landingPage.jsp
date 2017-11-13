<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>MINISTER DASHBOARD</title>
<!--<link href="Assests/MaterialKit/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="Assests/MaterialKit/css/material-kit.css" rel="stylesheet"/>
<link href="Assests/MaterialKit/css/landingPage.css" rel="stylesheet"/>-->
<link href="Assests/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="Assests/css/custom.less" rel="stylesheet" type="text/less">
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
			<div class="col-sm-1">
				<img src="Assests/img/aplogo.png" class="logoCls" alt="logo">
			</div>
			<div class="col-sm-11">
				<h5 style="margin-bottom: 0px;margin-top: 10px;color: #EC2027"><b>MINISTER <span style="font-size: 10px;">DASHBOARD</span></b></h5>
				<p style="font-size: 10px;color : #22A67E"><b>Information Technology, Panchayathi Raj & <br/>
				Rural Development</b></p>
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
						<h5>FAVOURITE</h5>
					</div>
				</div>
				<div class="col-sm-2 text-center">
					<div class="block" landing-link="fms" id="fmsMenu">
						<div style="height:70px;width:100%;margin:auto;">
							<img src="Assests/img/Group 2353.png" alt="Fund Management" style="display:block;margin:auto">
						</div>
						<h5>FUND MANAGMENT SYSTEM</h5>
					</div>
				</div>
				<div class="col-sm-2 text-center">
					<div class="block" landing-link="panchayat" id="panchayatRajMenu">
						<div style="height:70px;width:100%;margin:auto;">
							<img src="Assests/img/Group 2354.png" alt="Panchayat Raj" style="display:block;margin:auto">
						</div>
						<h5>PANCHAYATI RAJ</h5>
					</div>
				</div>
				<div class="col-sm-2 text-center">
					<div class="block" landing-link="rd" id="rwsMenu">
						<div style="height:70px;width:100%;margin:auto;">
							<img src="Assests/img/Path 232.png" alt="Rural Development" style="display:block;margin:auto">
						</div>
						<h5>RURAL DEVELOPMENT</h5>
					</div>
				</div>
				<div class="col-sm-2 text-center">
					<div class="block" landing-link="rws" id="ruralDevelopmentMenu">
						<div style="height:70px;width:100%;margin:auto;">
							<img src="Assests/img/Group 2355.png" alt="Rural Water Supply" style="display:block;margin:auto">
						</div>
						<h5>RURAL WATER SUPPLY</h5>
					</div>
				</div>
				<div class="col-sm-2 text-center">
					<div class="block" landing-link="itec" id="itecMenu">
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
				<p style="font-size: 12px;">Minister for Information Technology,<br/> Panchayathi Raj and Rural Development,<br/>(Government of Andhra Pradesh)</p>
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
<section style="margin-bottom: 100px; display:none" landing-block="fms" id="fundmanagementBlock" class="showhideCls">
	<div class="container">
		<div class="row">
			<div class="col-sm-4">
				<div class="whiteBlock">
					<img src="Assests/img/Group 2352.png" >
					<h4 style="display: inline-block;">FUND MANAGMENT SYSTEM</h4>
					<div class=" " style="text-align: right">
						<h2 class="fundMngmntSstmOverAchvmntAllCls" style="margin-top: 0px"><span></span></h2>
						<p class="">TOTAL FUNDS</p>
					</div>
					<div class="block-footer">
						<i class="fa fa-star starcolorChange FUNDMANAGMENTSYSTEMColor" title="click to add as favourite component."  attr_url="newfundManagementSystemDashBoard" attr_full_block_name="FUND MANAGMENT SYSTEM" attr_color_name="gray" attr_block_name="FUNDMANAGMENTSYSTEM" aria-hidden="true"></i>
						<!--<span style="font-size: 9px;color:#8286FF">PANCHAYATI RAJ,RURAL DEVELOPMENT,RURAL WATER SUPPLY</span>-->
						<a class="pull-right" href="newfundManagementSystemDashBoard" target="_blank" style="font-size: 12px;"><i class="fa fa-external-link-square" aria-hidden="true"></i>Get More Details...</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<section style="margin-bottom: 100px;display:none" landing-block="panchayat" id="panchayatirajBlock" class="showhideCls">
	<div class="container">
		<div class="row">
			<div class="col-sm-4">
				<div class="whiteBlock">
					<img src="Assests/img/Group 2344.png" >
					<h5 style="display: inline-block;">PRIS</h5>
					<div class=" " style="text-align: right">
						<h2 class="prisOverAchvmntAllCls" style="margin-top: 0px"></h2>
						<p class="">ACHIEVEMENT</p>
					</div>
					<div class="block-footer" style="border-top: 1px solid lightgrey;padding-top: 5px">
						<i class="fa fa-star starcolorChange PRISColor" title="click to add as favourite component." attr_url="prisDashBoard" attr_full_block_name="PRIS" attr_color_name="gray" attr_block_name="PRIS" aria-hidden="true"></i>
						<a class="pull-right" href="prisDashBoard" target="_blank" style="font-size: 12px;"><i class="fa fa-external-link-square" aria-hidden="true"></i>Get More Info...</a>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="whiteBlock">
					<img src="Assests/img/Group 2345.png" >
					<h5 style="display: inline-block;">DRAINS</h5>
					<div class=" " style="text-align: right">
						<h2 class="drainsOverAchvmntAllCls" style="margin-top: 0px"></h2>
						<p class="">ACHIEVEMENT</p>
					</div>
					<div class="block-footer">
						<i class="fa fa-star starcolorChange DRAINSColor" title="click to add as favourite component." attr_url="drainDashBoard" attr_full_block_name="DRAINS" attr_color_name="gray" attr_block_name="DRAINS" aria-hidden="true"></i>
						<a class="pull-right" href="drainDashBoard"  target="_blank" style="font-size: 12px;"><i class="fa fa-external-link-square" aria-hidden="true"></i>Get More Details...</a>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="whiteBlock">
					<img src="Assests/img/Group 2346.png" >
					<h5 style="display: inline-block;">ENGINEERING DEPARTMENT</h5>
					<div class=" " style="text-align: right">
						<h2 class="encOverAchvmntAllCls" style="margin-top: 0px">0%</h2>
						<p class="">ACHIEVEMENT</p>
					</div>
					<div class="block-footer">
						<i class="fa fa-star starcolorChange ENGINEERINGDEPARTMENTColor"  title="click to add as favourite component." attr_url="" attr_full_block_name="ENGINEERING DEPARTMENT" attr_color_name="gray" attr_block_name="ENGINEERINGDEPARTMENT" aria-hidden="true"></i>
						<a class="pull-right"  style="font-size: 12px;"><i class="fa fa-external-link-square" aria-hidden="true"></i>Get More Details...</a>
					</div>
				</div>
			</div>
			<!--<div class="col-sm-4">
				<div class="whiteBlock">
					<img src="Assests/img/Group 2343.png" >
					<h5 style="display: inline-block;">PANACHAYATI RAJ EXPENDITURE</h5>
					<div class=" " style="text-align: right">
						<h2 class="preOverAchvmntAllCls" style="margin-top: 0px"></h2>
						<p class="">GROSS-AMOUNT</p>
					</div>
					<div class="block-footer">
						<i class="fa fa-star starcolorChange PANACHAYATIRAJEXPENDITUREColor" title="click to add as favourite component." attr_url="prExpenditureDashboard" attr_url="prExpenditureDashboard" attr_full_block_name="PANACHAYATI RAJ EXPENDITURE" attr_color_name="gray" attr_block_name="PANACHAYATIRAJEXPENDITURE" aria-hidden="true"></i>
						<a class="pull-right" href="prExpenditureDashboard" target="_blank" style="font-size: 12px;"><i class="fa fa-external-link-square" aria-hidden="true"></i>Get More Details...</a>
					</div>
				</div>
			</div>-->
			<div class="col-sm-4">
				<div class="whiteBlock">
					<img src="Assests/img/Group 2348.png" >
					<h5 style="display: inline-block;">LED MONITORING</h5>
					<div class=" " style="text-align: right">
						<h2 class="ledOverAchvmntAllCls" style="margin-top: 0px"></h2>
						<p class="">TOTAL LIGHTS</p>
					</div>
					<div class="block-footer">
						<i class="fa fa-star starcolorChange LEDMONITORINGColor" title="click to add as favourite component." attr_url="getlightsMonitoringDashboard" attr_color_name="gray" attr_full_block_name="LED MONITORING" attr_block_name="LEDMONITORING" aria-hidden="true"></i>
						<a class="pull-right" target="_blank" href="getlightsMonitoringDashboard" style="font-size: 12px;"><i class="fa fa-external-link-square" aria-hidden="true"></i>Get More Details...</a>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="whiteBlock">
					<img src="Assests/img/Group 2347.png" >
					<h5 style="display: inline-block;">SPIKE ANALYSIS</h5>
					<div class=" " style="text-align: right">
						<h2 class="spikeOverAchvmntAllCls" style="margin-top: 0px"></h2>
						<p class="">TOTAL CASES</p>
					</div>
					<div class="block-footer">
						<i class="fa fa-star starcolorChange SPIKEANALYSISColor" title="click to add as favourite component." attr_url="getdailySpikeReport" attr_full_block_name="SPIKE ANALYSIS" attr_color_name="gray" attr_block_name="SPIKEANALYSIS" aria-hidden="true"></i>
						<a class="pull-right"  href="getdailySpikeReport" target="_blank" style="font-size: 12px;"><i class="fa fa-external-link-square" aria-hidden="true"></i>Get More Details...</a>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="whiteBlock">
					<img src="Assests/img/waterTank.png">
					<h5 style="display: inline-block;">WATER TANK CHLORINATION</h5>
					<div class=" " style="text-align: right">
						<h2 class="waterTankChlorinationAllCls" style="margin-top: 0px"></h2>
						<p class="">CHLORINATED</p>
					</div>
					<div class="block-footer">
						<i class="fa fa-star starcolorChange WATERTANKCHLORINATIONColor" title="click to add as favourite component." attr_url="waterTanksClorinationDashBoard" attr_full_block_name="WATER TANK CLORINATION" attr_color_name="gray" attr_block_name="WATERTANKCLORINATION" aria-hidden="true"></i>
						<a class="pull-right"  href="waterTanksClorinationDashBoard" target="_blank" style="font-size: 12px;"><i class="fa fa-external-link-square" aria-hidden="true"></i>Get More Details...</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<section style="margin-bottom: 100px;display:none" landing-block="rd" id="ruralWaterSupplyBlock" class="showhideCls">
	<div class="container">
		<div class="row">
			<div class="col-sm-4">
				<div class="whiteBlock">
					<img src="Assests/img/Group 2357.png" >
					<h5 style="display: inline-block;">MGNREGS</h5>
					<div class=" " style="text-align: right">
						<h2 class="mgnregsOverAchvmntAllCls" style="margin-top: 0px">0%</h2>
						<p class="">ACHIEVEMENT</p>
					</div>
					<div class="block-footer" style="display:table;width:100%;">
						<div class="menu-top-selection" style="float:left">
							<i class="fa fa-star menu-top-selection-icon"></i>
							<div class="arrow_box_top">
								<div class="row">
									<div id="navTabsMenuSelectionId"></div>
								</div>
							</div>
						</div>
						<a class="pull-right" href="MGNREGSDashboard" target="_blank" style="font-size: 12px;"><i class="fa fa-external-link-square" aria-hidden="true"></i>Get More Details...</a>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="whiteBlock">
					<img src="Assests/img/Group 2349.png" >
					<h5 style="display: inline-block;">NTR JALASIRI</h5>
					<div class=" " style="text-align: right">
						<h2 class="NtrJalasiriAllCls" style="margin-top: 0px">0%</h2>
						<p class="">ACHIEVEMENT</p>
					</div>
					<div class="block-footer">
						<i class="fa fa-star starcolorChange NtrJalasiriColor" title="click to add as favourite component." attr_url="RuralDevelopmentDashboard" attr_full_block_name="Ntr Jalasiri" attr_color_name="gray" attr_block_name="Ntr Jalasiri" aria-hidden="true"></i>
						<a class="pull-right" href="RuralDevelopmentDashboard?component=Ntr Jalasiri" target="_blank" style="font-size: 12px;"><i class="fa fa-external-link-square" aria-hidden="true"></i>Get More Details...</a>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="whiteBlock">
					<img src="Assests/img/Group 2350.png" >
					<h5 style="display: inline-block;">WATER BUDGET</h5>
					<div class=" " style="text-align: right">
						<h2 class="WaterBudgetAllCls" style="margin-top: 0px">0%</h2>
						<p class="">ACHIEVEMENT</p>
					</div>
					<div class="block-footer">
						<i class="fa fa-star starcolorChange WaterBudgetColor" title="click to add as favourite component." attr_url="WaterBudget" attr_full_block_name="WaterBudget" attr_color_name="gray" attr_block_name="WaterBudget" aria-hidden="true"></i>
						<a class="pull-right" href="RuralDevelopmentDashboard?component=WaterBudget" target="_blank" style="font-size: 12px;"><i class="fa fa-external-link-square" aria-hidden="true"></i>Get More Details...</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<section style="margin-bottom: 100px;display:none" landing-block="rws" id="ruralDevelopmentBlock" class="showhideCls">
	<div class="container">
		<div class="row">
			<div class="col-sm-4">
				<div class="whiteBlock">
					<img src="Assests/img/Group 2350.png" >
					<h5 style="display: inline-block;">RURAL WATER SUPPLY</h5>
					<div class=" " style="text-align: right">
						<h2 class="rwsOverAchvmntAllCls" style="margin-top: 0px"></h2>
						<p class="">ACHIEVEMENT</p>
					</div>
					<div class="block-footer">
						<i class="fa fa-star starcolorChange RURALWATERSUPPLYColor" title="click to add as favourite component." attr_url="ruralWaterSupplyDashBoard" attr_full_block_name="RURAL WATER SUPPLY" attr_color_name="gray" attr_block_name="RURALWATERSUPPLY" aria-hidden="true"></i>
						<a class="pull-right" href="ruralWaterSupplyDashBoard"  target="_blank" style="font-size: 12px;"><i class="fa fa-external-link-square" aria-hidden="true"></i>Get More Details...</a>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="whiteBlock">
					<img src="Assests/img/Group 2362.png" >
					<h5 style="display: inline-block;">SWATCH BHARATH IHHL</h5>
					<div class=" " style="text-align: right">
						<h2 class="swatchBharathIHHL" style="margin-top: 0px"></h2>
						<p class="">COMPLETED</p>
					</div>
					<div class="block-footer">
						<i class="fa fa-star starcolorChange SWATCHBHARATHIHHLColor" title="click to add as favourite component." attr_url="swachhBharatMissionIHHL" attr_full_block_name="SWATCH BHARATH IHHL" attr_color_name="gray" attr_block_name="SWATCHBHARATHIHHL" aria-hidden="true"></i>
						<a class="pull-right" href="swachhBharatMissionIHHL"  target="_blank" style="font-size: 12px;"><i class="fa fa-external-link-square" aria-hidden="true"></i>Get More Details...</a>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="whiteBlock">
					<img src="Assests/img/Group 2362.png" >
					<h5 style="display: inline-block;">MGNREGS IHHL</h5>
					<div class=" " style="text-align: right">
						<h2 class="MGNREGSIHHLAllCls" style="margin-top: 0px"></h2>
						<p class="">COMPLETED</p>
					</div>
					<div class="block-footer">
						<i class="fa fa-star starcolorChange MGNREGSIHHLColor" title="click to add as favourite component." attr_url="swatchBharatIHHLInfo" attr_full_block_name="MGNREGS IHHL" attr_color_name="gray" attr_block_name="MGNREGSIHHL" aria-hidden="true"></i>
						<a class="pull-right" href="swatchBharatIHHLInfo"  target="_blank" style="font-size: 12px;"><i class="fa fa-external-link-square" aria-hidden="true"></i>Get More Details...</a>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="whiteBlock">
					<img src="Assests/img/Group 2352.png" >
					<h5 style="display: inline-block;">SWATCH BHARATH PAYMENTS</h5>
					<div class=" " style="text-align: right">
						<h2 class="swatchBharathPayments" style="margin-top: 0px"></h2>
						<p class="">PENDING</p>
					</div>
					<div class="block-footer">
						<i class="fa fa-star starcolorChange SWATCHBHARATHPAYMENTSColor" title="click to add as favourite component." attr_url="swatchBharatIHHLInfo" attr_full_block_name="SWATCH BHARATH PAYMENTS" attr_color_name="gray" attr_block_name="SWATCHBHARATHPAYMENTS" aria-hidden="true"></i>
						<a class="pull-right" href="swatchBharatPaymentsInfo"  target="_blank" style="font-size: 12px;"><i class="fa fa-external-link-square" aria-hidden="true"></i>Get More Details...</a>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="whiteBlock">
					<img src="Assests/img/Jalavani.png" >
					<h5 style="display: inline-block;">JALAVANI</h5>
					<div class=" " style="text-align: right">
						<h2 class="JalavaniAllCls" style="margin-top: 0px"></h2>
						<p class="">NOTIFIED & IN PROGRESS</p>
					</div>
					<div class="block-footer">
						<i class="fa fa-star starcolorChange JALAVANIColor" title="click to add as favourite component." attr_url="jalavaniDashBoard" attr_full_block_name="JALAVANI" attr_color_name="gray" attr_block_name="JALAVANI" aria-hidden="true"></i>
						<a class="pull-right" href="jalavaniDashBoard"  target="_blank" style="font-size: 12px;"><i class="fa fa-external-link-square" aria-hidden="true"></i>Get More Details...</a>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="whiteBlock">
					<img src="Assests/img/assets.png" >
					<h5 style="display: inline-block;">ASSETS</h5>
					<div class=" " style="text-align: right">
						<h2 class="assetsAllCls" style="margin-top: 0px"></h2>
						<p class="">TOTAL</p>
					</div>
					<div class="block-footer">
						<i class="fa fa-star starcolorChange ASSETSColor" title="click to add as favourite component." attr_url="assetsDashBoard" attr_full_block_name="ASSETS" attr_color_name="gray" attr_block_name="ASSETS" aria-hidden="true"></i>
						<a class="pull-right" href="assetsDashBoard"  target="_blank" style="font-size: 12px;"><i class="fa fa-external-link-square" aria-hidden="true"></i>Get More Details...</a>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="whiteBlock">
					<img src="Assests/img/kpi.png" >
					<h5 style="display: inline-block;">KEY PERFORMANCE</h5>
					<div class=" " style="text-align: right">
						<h2 class="keyPerforamanceAllCls" style="margin-top: 0px"></h2>
						<p class="">TOTAL</p>
					</div>
					<div class="block-footer">
						<i class="fa fa-star starcolorChange KEYPERFORMANCEColor" title="click to add as favourite component." attr_url="keyPerfomanceDashBoard" attr_full_block_name="KEY PERFORMANCE" attr_color_name="gray" attr_block_name="KEY PERFORMANCE" aria-hidden="true"></i>
						<a class="pull-right" href="keyPerfomanceDashBoard"  target="_blank" style="font-size: 12px;"><i class="fa fa-external-link-square" aria-hidden="true"></i>Get More Details...</a>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="whiteBlock">
					<img src="Assests/img/water source.png" >
					<h5 style="display: inline-block;">WATER SOURCE</h5>
					<div class=" " style="text-align: right">
						<h2 class="waterSourceAllCls" style="margin-top: 0px"></h2>
						<p class="">TOTAL</p>
					</div>
					<div class="block-footer">
						<i class="fa fa-star starcolorChange WATERSOURCEColor" title="click to add as favourite component." attr_url="waterSourcesDashBoard" attr_full_block_name="WATER SOURCE" attr_color_name="gray" attr_block_name="WATER SOURCE" aria-hidden="true"></i>
						<a class="pull-right" href="waterSourcesDashBoard"  target="_blank" style="font-size: 12px;"><i class="fa fa-external-link-square" aria-hidden="true"></i>Get More Details...</a>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="whiteBlock">
					<img src="Assests/img/works.png" >
					<h5 style="display: inline-block;">WORKS</h5>
					<div class=" " style="text-align: right">
						<h2 class="worksAllCls" style="margin-top: 0px"></h2>
						<p class="">TOTAL</p>
					</div>
					<div class="block-footer">
						<i class="fa fa-star starcolorChange WORKSColor" title="click to add as favourite component." attr_url="worksDashBoard" attr_full_block_name="WORKS" attr_color_name="gray" attr_block_name="WORKS" aria-hidden="true"></i>
						<a class="pull-right" href="worksDashBoard"  target="_blank" style="font-size: 12px;"><i class="fa fa-external-link-square" aria-hidden="true"></i>Get More Details...</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<section style="margin-bottom: 100px;display:none" landing-block="itec" id="iteBlock" class="showhideCls">
	<div class="container">
		<div class="row">
			<!--<div class="col-sm-4">
				<div class="whiteBlock">
					<img src="Assests/img/Group 2351.png" >
					<h5 style="display: inline-block;">IT E & C</h5>
					<div class=" " style="text-align: right">
						<h2 class="itecOverAchvmntAllCls" style="margin-top: 0px"></h2>
						<p class="">TOTAL TRANSACTIONS</p>
					</div>
					<div class="block-footer">
						<i class="fa fa-star ITECColor starcolorChange" title="click to add as favourite component."  attr_url="itcDashboard" attr_full_block_name="IT E & C" attr_color_name="gray" aria-hidden="true" attr_block_name="ITEC"></i>
						<span style="font-size: 9px;color:#8286FF">INFORMATION TECHNOLOGY , ELECTRONICS & FIN TECH</span>
						<a class="pull-right" href="itcDashboard" target="_blank" style="font-size: 12px;"><i class="fa fa-external-link-square" aria-hidden="true"></i>Get More Details...</a>
					</div>
				</div>
			</div>-->
			<div class="col-sm-4">
				<div class="whiteBlock">
					<img src="Assests/img/promotions.png" >
					<h5 style="display: inline-block;">PROMOTIONS</h5>
					<div class=" " style="text-align: right">
						<h2 class="itecPromotionsAllCls" style="margin-top: 0px"></h2>
						<p class="">Committed Investment</p>
					</div>
					<div class="block-footer">
						<i class="fa fa-star PROMOTIONSColor starcolorChange" title="click to add as favourite component."  attr_url="itcDashboard" attr_full_block_name="PROMOTIONS" attr_color_name="gray" aria-hidden="true" attr_block_name="PROMOTIONS"></i>
						<a class="pull-right" href="itcDashboard?component=promotions" target="_blank" style="font-size: 12px;"><i class="fa fa-external-link-square" aria-hidden="true"></i>Get More Details...</a>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="whiteBlock">
					<img src="Assests/img/eOffice.png" >
					<h5 style="display: inline-block;">E OFFICE</h5>
					<div class=" " style="text-align: right">
						<h2 class="itecEOfficeAllCls" style="margin-top: 0px"></h2>
						<p class="">Total Pendency</p>
					</div>
					<div class="block-footer">
						<i class="fa fa-star EOFFICEColor starcolorChange" title="click to add as favourite component."  attr_url="itcDashboard" attr_full_block_name="E OFFICE" attr_color_name="gray" aria-hidden="true" attr_block_name="EOFFICE"></i>
						<a class="pull-right" href="itcDashboard?component=eOffice" target="_blank" style="font-size: 12px;"><i class="fa fa-external-link-square" aria-hidden="true"></i>Get More Details...</a>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="whiteBlock">
					<img src="Assests/img/meeSevaSla.png" >
					<h5 style="display: inline-block;">MEESEVA - SLA</h5>
					<div class=" " style="text-align: right">
						<h2 class="itecMeeSevaSlaAllCls" style="margin-top: 0px"></h2>
						<p class="">Beyond SLA</p>
					</div>
					<div class="block-footer">
						<i class="fa fa-star MEESEVA-SLAColor starcolorChange" title="click to add as favourite component."  attr_url="itcDashboard" attr_full_block_name="MEESEVA - SLA" attr_color_name="gray" aria-hidden="true" attr_block_name="MEESEVA-SLA"></i>
						<a class="pull-right" href="itcDashboard?component=meesevaSla" target="_blank" style="font-size: 12px;"><i class="fa fa-external-link-square" aria-hidden="true"></i>Get More Details...</a>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="whiteBlock">
					<img src="Assests/img/APInnovationSoc.png" >
					<h5 style="display: inline-block;">AP INNOVATION SOCIETY</h5>
					<div class=" " style="text-align: right">
						<h2 class="itecApInnovationAllCls" style="margin-top: 0px"></h2>
						<p class="">Startups</p>
					</div>
					<div class="block-footer">
						<i class="fa fa-star APINNOVATIONSOCIETYColor starcolorChange" title="click to add as favourite component."  attr_url="itcDashboard" attr_full_block_name="AP INNOVATION SOCIETY" attr_color_name="gray" aria-hidden="true" attr_block_name="APINNOVATIONSOCIETY"></i>
						<a class="pull-right" href="itcDashboard?component=apInnovationSociety" target="_blank" style="font-size: 12px;"><i class="fa fa-external-link-square" aria-hidden="true"></i>Get More Details...</a>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="whiteBlock">
					<img src="Assests/img/MeeSevaKPI.png" >
					<h5 style="display: inline-block;">MEESEVA & KPI</h5>
					<div class=" " style="text-align: right">
						<h2 style="margin-top: 0px">3499</h2>
						<p class="">etaal - KPI</p>
					</div>
					<div class="block-footer">
						<i class="fa fa-star MEESEVA&KPIColor starcolorChange" title="click to add as favourite component."  attr_url="itcDashboard" attr_full_block_name="MEESEVA & KPI" attr_color_name="gray" aria-hidden="true" attr_block_name="MEESEVA&KPI"></i>
						<a class="pull-right" href="itcDashboard?component=meesevaKpi" target="_blank" style="font-size: 12px;"><i class="fa fa-external-link-square" aria-hidden="true"></i>Get More Details...</a>
					</div>
				</div>
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