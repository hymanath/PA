<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>NTR SUJALA</title>
<link href="Assests/less/bootstrap.less" rel="stylesheet" type="text/less">
<link href="Assests/css/custom.less" rel="stylesheet" type="text/less"/>
<link href="Assests/Plugins/DateTime/bootstrap-datetimepicker-build.less" type="text/less" rel="stylesheet"/>
<link href="Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/DataTable/exportButtons.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Scroller/jquery.mCustomScrollbar.css" type="text/less" rel="stylesheet"/>
<script src="Assests/Plugins/Less/less.js"></script>
<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<script src="https://use.fontawesome.com/e94c241642.js"></script>
<style type="text/css">

</style>
</head>
<body>
 <header>
	<nav>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-1 col-xs-3 pad_left0">
					<img src="Assests/images/aplogo.png" class="logo"/>
				</div>
				<div class="col-sm-4 m_top10 col-xs-9">
					<h4 class="text-capital">Panchayat Raj, RD & RWS</h4>
					<p>NTR SUJALA DASHBOARD</p>
				</div>
				
				<div class="col-sm-3 col-xs-12 pull-right">
					<i class="glyphicon glyphicon-th menu-cls pull-right"></i>
					<div class="menu-data-cls">
						<div class="arrow_box_top">
							<div class="row">
								<!--<div class="col-sm-12">
									<div class="menu-block" style="background-color:#FFBA00">
										<a href="newfundManagementDashboard">
											<h3>FMS</h3>
											<p>Fund Management System</p>
										</a>
									</div>
								</div>  -->
								<div class="col-sm-12">
									<div class="menu-heading-block">
										<h4 class="text-capital">Rural Water Supply</h4>
										<div class="row">
											<div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#56A3C5">
													<a href="ruralWaterSupplyDashBoard">
														<h3>RWS</h3>
														<p>Rural Water Supply</p>
													</a>
												</div>
											</div>
											<div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#8A2BE2">
													<a href="newsArticles?deptId=2171">
														<h3>RWS News</h3>
														<p>Rural&nbsp;Water&nbsp;Supply&nbsp;News</p>
													</a>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#1e92b2">
													<a href="swachhBharatMissionIHHL">
														<h3>IHHL</h3>
														<p>Swatch Bharat Mission</p>
													</a>
												</div>
											</div>
											<div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#1c94ef">
													<a href="waterTanksClorinationDashBoard">
														<h3>WTC</h3>
														<p>Water Tank chlorination</p>
													</a>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="col-sm-12 m_top10">
									<div class="menu-heading-block">
										<h4>PANCHAYAT RAJ</h4>
										<div class="row">
											<div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#0F685C">
													<a href="prisDashBoard">
														<h3>PRIS</h3>
													</a>
												</div>
											</div>
											<div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#31B8B7">
													<a href="drainDashBoard">
														<h3>DRAINS</h3>
													</a>
												</div>
											</div>
											<div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#2C546C">
													<a href="EncDevelopmentDashboard">
														<h3>ENC</h3>
														<p>Engineering Dept</p>
													</a>
												</div>
											</div>
											<div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#512507">
													<a href="getdailySpikeReport">
														<h3>SA</h3>
														<p>Spike Analysis</p>
													</a>
												</div>
											</div>
											<div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#888420">
													<a href="getlightsMonitoringDashboard">
														<h3>LED</h3>
														<p>Light Monitoring</p>
													</a>
												</div>
											</div>
											<!-- <div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#ff5e1c">
													<a href="prExpenditureDashboard">
														<h3>PR EXP</h3>
														<p>Panchayat Raj <br/>Expenditure</p>
													</a>
												</div>
											</div>
											<div class="col-sm-12 m_top10">
												<div class="menu-block" style="background-color:#1c94ef">
													<a href="waterTanksClorinationDashBoard">
														<h3>WTC</h3>
														<p>Water Tank Clorination</p>
													</a>
												</div>
											</div>-->
											<div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#ff5e1c">
													<a href="solidWasteManagementDashboard">
														<h3>SWM</h3>
                           								 <p>Solid Waste Management</p>
													</a>
												</div>
											</div>
											<div class="col-sm-12 m_top10">
												<div class="menu-block" style="background-color:#008000">
													<a href="newsArticles?deptId=1699">
														<h3>PR News</h3>
                           								 <p>Panchayat Raj News</p>
													</a>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="col-sm-12 m_top10">
									<div class="menu-heading-block">
										<h4>RURAL DEVELOPMENT</h4>
										<div class="row">
											<div class="col-sm-12 m_top10">
												<div class="menu-block" style="background-color:#88186B">
													<a href="MGNREGSDashboard">
														<h3>MGNREGS</h3>
														<p>Mahatma Gandhi Rural employement guarantee scheme</p>
													</a>
												</div>
											</div>
											<div class="col-sm-12 m_top10">
											<div class="row">
											<div class="col-sm-6">
												<div class="menu-block" style="background-color:#ff1c5e">
													<a href="RuralDevelopmentDashboard">
														<h3>RD</h3>
														<p>Rural&nbsp;Development&nbsp;Dashboard</p>
													</a>
												</div>
											</div>
											<div class="col-sm-6">
												<div class="menu-block" style="background-color:#8B0000">
													<a href="newsArticles?deptId=2170">
														<h3>RD News</h3>
														<p>Rural Development News</p>
													</a>
												</div>
											</div>
											</div>
											</div>
										</div>
									</div>
								</div>
								<!--<div class="col-sm-12 m_top10">
									<div class="menu-block" style="background-color:#de4524 ">
										<a href="itcDashboard">
											<h3>IT E & C</h3>
											<p>Dashboard</p>
										</a>
									</div>
								</div>-->
								<!-- <div class="col-sm-12 m_top10">
									<div class="menu-block" style="background-color:#989820">
										<a href="newsArticles">
											<h3>News Articles</h3>
											<p>Dashboard</p>
										</a>
									</div>
								</div>-->
							</div>
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</nav>
	
</header>
<main>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12">
				<div class="panel panel-default">
					 <div class="panel-heading" style="padding: 15px;background-color: #CFCFCF;">
						<h3 class="font_weight">Overview</h3>
					</div>
					<div class="panel-body">
						<div id="overViewDetails"></div>
					</div>
				</div>
				
			</div>
		</div>
		<!--<div class="pad_white_bg" style="border: 1px solid #ccc;border-radius: 0px !important;">
			<div class="row">
				<div class="col-sm-7">
					<div class="row">
						<div class="col-sm-3">
							<label>District</label>
							<select class="form-control chosen-select" id="districtId">
								<option value="0">Select District</option>
							</select>
						</div>
						<div class="col-sm-3">
							<label>Mother Plant</label>
							<select class="form-control chosen-select" id="motherPlantId">
								<option value="0">Select Mother Plant</option>
							</select>
						</div>
					</div>
					<div id="mapDetailsDivId"></div>
				</div>
				<div class="col-sm-5">
					<div id="mpWiseDetailsDivId"></div>
				</div>
			</div>
		</div>-->
		<div class="row m_top20">
			<div class="col-sm-12">
				<div class="panel panel-default">
					 <div class="panel-heading" style="display:block;">
						<h3 class="panel-title font_weight">District Wise Mother Plant Details</h3>
					</div>
					<div class="panel-body" style="">
						<div id="districtWiseMpDetailsId"></div>
					</div>
				</div>
				
			</div>
		</div>	
	</div>
	
	<div class="modal fade" id="mother_plants" role="dialog">
		<div class="modal-dialog modal-lg" style="width:95%; margin:auto;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h3 class="modal-title"><b>Mother Plants Overview</b></h3>
				</div>
				<div class="modal-body" >	
					<div class="row">
						<div class="col-sm-12">
							<div id="motherPlantsOverviewId"></div>
						</div>
						
					</div>
				</div>
				<div class="modal-footer" style="background-color:#E1E1E1;">
				  <button type="button" class="btn btn-default Close_st" data-dismiss="modal">CLOSE</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="mother_plants_Details" role="dialog">
		<div class="modal-dialog modal-lg" style="width:95%; margin:auto;">
			<div class="modal-content">
				<div class="modal-header linerGradientCss" style="padding: 10px;">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div class="row">
						<div class="col-sm-3">
							<div class="media m_top0">
							  <div class="media-left">
								<a href="#">
								 <img class="media-object" src="Assests/images/icon-motherPlanet.png" alt="..." style="width: 40px;">
								</a>
							  </div>
							  <div class="media-body">
								<h3 class="media-heading font_weight" style="font-size: 20px;" id="mpDetailsHeadingId"></h3>
								<h5 class="m_top5">Mother Plant</h5>
							  </div>
							</div>
						</div>
						<div class="col-sm-1">
							<h5 class="motherPlantLowLevelDtsCls"><i style="color: #ccc;font-size: 25px;cursor:pointer;" class="glyphicon glyphicon-resize-full" title="Click Here to get Last 30 Days MotherPlant Details"></i></h5>
						</div>
						<div class="col-sm-7">
							<div class="pull-right" style="margin-right:20px;">
								<h5 class="font_weight" style="color:#493333"><i class="fa fa-phone-square"></i><span id="mpDetailsHeadingMobileId"></h5>
							</div>
							
						</div>
					</div>
					
				</div>
				<div class="modal-body" >	
					<div id="motherPlantsDetailsId"></div>
				</div>
				<div class="modal-footer" style="background-color:#E1E1E1;">
				  <button type="button" class="btn btn-default Close_st" data-dismiss="modal">CLOSE</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="mother_plants_Details_low_level" role="dialog">
		<div class="modal-dialog modal-lg" style="width:95%; margin:auto;">
			<div class="modal-content">
				<div class="modal-header linerGradientCss" style="padding: 10px;">
					<button type="button" class="close closeSecondModal" data-dismiss="modal">&times;</button>
					<div class="row">
						<div class="col-sm-7">
							<div class="media m_top0">
							  <div class="media-left">
								<a href="#">
								 <img class="media-object" src="Assests/images/icon-motherPlanet.png" alt="..." style="width: 40px;">
								</a>
							  </div>
							  <div class="media-body">
								<h3 class="media-heading font_weight" style="font-size: 20px;" id="mp30DaysHeadingId"></h3>
								<h5 class="m_top5">Mother Plant</h5>
							  </div>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="m_top10" style="margin-right:20px;">
								<h3 style="font-size: 20px;" id="">Last <span style="padding:5px;border:1px solid #ccc;border-radius:5px;">30Days</span> Wise Overview</h3>
							</div>
							
						</div>
					</div>
					
				</div>
				<div class="modal-body" >	
					<div id="motherPlantsLowLevelDetailsId"></div>
				</div>
				<div class="modal-footer" style="background-color:#E1E1E1;">
				  <button type="button" class="btn btn-default Close_st closeSecondModal" data-dismiss="modal">CLOSE</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="mother_plants_Details_water_tank" role="dialog">
		<div class="modal-dialog modal-lg" style="width:95%; margin:auto;">
			<div class="modal-content">
				<div class="modal-header linerGradientCss" style="padding: 10px;">
					<button type="button" class="close closeSecondModal" data-dismiss="modal">&times;</button>
					<div class="row">
						<div class="col-sm-3">
							<div class="media m_top0">
							  <div class="media-left">
								<a href="#">
								 <img class="media-object" src="Assests/images/icon-rdu.png" alt="..." style="width: 40px;">
								</a>
							  </div>
							  <div class="media-body">
								<h3 class="media-heading font_weight" style="font-size: 20px;" id="rduHeadingId"></h3>
								<h5 class="m_top5">RDU</h5>
							  </div>
							</div>
						</div>
						<div class="col-sm-4">
								<div class="border_left_width_water_tank">
									<h5 class="m_left_5 f_12 font_weight">Water Tank Capacity</h5>
									<h4 class="m_top5  m_left_5" id="rduheadingCapacityId"></h4>
								</div>
						</div>
						<div class="col-sm-4">
							<div class="m_top10" style="margin-right:20px;">
								<h3 style="font-size: 20px;" id="">Last <span style="padding:5px;border:1px solid #ccc;border-radius:5px;">30Days</span> Wise Overview</h3>
							</div>
							
						</div>
					</div>
					
				</div>
				<div class="modal-body" >	
					<div id="motherPlantsRDUDetailsId"></div>
				</div>
				<div class="modal-footer" style="background-color:#E1E1E1;">
				  <button type="button" class="btn btn-default Close_st closeSecondModal" data-dismiss="modal">CLOSE</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="rdus_modal" role="dialog">
		<div class="modal-dialog modal-lg" style="width:95%; margin:auto;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h3 class="modal-title"><b>RDU's Overview</b></h3>
				</div>
				<div class="modal-body" >	
					<div class="row">
						<div class="col-sm-12">
							<div id="rdusOverviewId"></div>
						</div>
						
					</div>
				</div>
				<div class="modal-footer" style="background-color:#E1E1E1;">
				  <button type="button" class="btn btn-default Close_st" data-dismiss="modal">CLOSE</button>
				</div>
			</div>
		</div>
	</div>
</main>		
			
<script src="Assests/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="Assests/js/bootstrap.js" type="text/javascript"></script>
<script src="Assests/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="Assests/Plugins/Scroller/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="Assests/Plugins/Scroller/jquery.mousewheel.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/exportButtons.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/jsZip.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/pdf.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/v5font.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/htmlButtons.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/dataTables.fixedColumns.min.js" type="text/javascript"></script>
<script type="text/javascript" src="http://maps.google.com/maps/api/js?key=AIzaSyD_ELXOA5iHgPThVcenSQjMwkev64EcZbE"></script>
<script src="Assests/NTRSujala/ntrSujalaDashboard.js" type="text/javascript"></script>
<script>

</script>
</body>
</html>