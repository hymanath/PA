<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Panchayat Raj eMeetings</title>
<link href="Assests/less/bootstrap.less" rel="stylesheet" type="text/less">
<link href="Assests/css/custom.less" rel="stylesheet" type="text/less"/>
<link href="Assests/Plugins/DateTime/bootstrap-datetimepicker-build.less" type="text/less" rel="stylesheet"/>
<link href="Assests/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Scroller/jquery.mCustomScrollbar.css" type="text/less" rel="stylesheet"/>
<script src="Assests/Plugins/Less/less.js"></script>
<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/DataTable/exportButtons.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<script src="https://use.fontawesome.com/e94c241642.js"></script>
</head>
<body>
<header>
	<nav>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-1 col-xs-3 pad_left0">
					<img src="Assests/images/aplogo.png" class="logo"/>
				</div>
				<div class="col-sm-10 m_top10 col-xs-9">
					<h4 class="text-capital">Panchayat Raj</h4>
					<p>e-Meetings - DashBoard</p>
				</div>
				<div class="col-sm-1 col-xs-12">
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
		 <div class="row m_top20">
			<div class="col-sm-12">
				<div class="white_block_emeeting">
					<div class="row">
						<div class="col-sm-12">
							<div class="media">
								<div class="media-left">
									<img src="Assests/images/Group 663.png" />
								</div>
								<div class="media-body">
									<h3 class="m_top10">Panchayati Raj - e Meetings</h3>
								</div>
							</div>
						</div>
					</div>
					<div class="row m_top10">
						<div class="col-sm-12">
							<div class="yellow-bdr-div">
								<div class="row">
									<div class="col-sm-8">
											<div class="custom-div">
												<div class="row">
													<div class="col-sm-8">
														<div class="custom-div">
															<div class="row">
																<div class="col-sm-9">
																	<div class="media">
																		<div class="media-left">
																			<img src="Assests/images/total-panchayats.png" style="width:45px; height:45px;"/>
																		</div>
																		<div class="media-body">
																			<h5 class="m_top10 font_weight">Total Panchayats</h5>
																		</div>
																	</div>
																</div>
																<div class="col-sm-3 text-right">
																	<h4 class="font_weight m_top10">13,500</h4>
																</div>
															</div>
														</div>
														<div class="row m_top10">
																<div class="col-sm-6">
																	<div class="custom-div-bg">
																		<div class="row">
																			<div class="col-sm-8">
																				<div class="media">
																					<div class="media-left" style="vertical-align:middle;">
																						<i class="fa fa-check-circle-o green_color fa-2x"></i>
																					</div>
																					<div class="media-body">
																						<h5 class="font_weight">e Meetings Panchayaties</h5>
																					</div>
																				</div>
																			</div>
																			<div class="col-sm-4  right-align"><h4 class="font_weight">1,700</h4></div>
																		</div>
																	</div>
																</div>

																<div class="col-sm-6">
																	<div class="custom-div-bg">
																		<div class="row">
																			<div class="col-sm-8">
																				<div class="media">
																					<div class="media-left" style="vertical-align:middle;">
																						<i class="fa fa-times-circle-o fa-2x red_color"></i>
																					</div>
																					<div class="media-body">
																						<h5 class="font_weight">e Meetings Panchayaties</h5>
																					</div>
																				</div>
																			</div>
																			<div class="col-sm-4 pad_left5"><h4 class="font_weight">11,800</h4></div> 
																		</div>
																	</div>
																</div>
														</div>
													</div>
													<div class="col-sm-4">
														<div class="custom-div">
															<div class="row">
																<div class="col-sm-7">
																	<div class="media">
																		<div class="media-left">
																			<img src="Assests/images/icon-meetngs.png" style="width:45px; height:45px;"/>
																		</div>
																		<div class="media-body">
																			<h5 class="m_top10 font_weight">Total Meetings</h5>
																		</div>
																	</div>
																</div>
																<div class="col-sm-5">
																	<h4 class="font_weight m_top10">13,500</h4>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col-sm-6 m_top10">
																<div class="custom-div-bg">
																	<h5 class="font_weight text-center">Conducted</h5>
																	<h5 class="font_weight"><span class="float-lt"><i class="fa fa-check-circle-o green_color fa-2x"></i></span><span class="m_left text_pos m_top5">2,000</span></h5>
																</div>
															</div>
															<div class="col-sm-6">
																<div class="custom-div-bg m_top10">
																	<h5 class="font_weight text-center">Re Scheduled</h5>
																	<h5 class="font_weight"><span class="float-lt"><i class="fa fa-pause-circle-o yellow_color fa-2x"></i></span><span class="m_left text_pos m_top5">2,000</span></h5>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
									</div>
									<div class="col-sm-4">
										<div class="custom-div">
											<div class="row">
											<div class="col-sm-12">
												<div class="media">
													<div class="media-left">
														<img src="Assests/images/icon-agenda.png" style="width:45px; height:45px;"/>
													</div>
													<div class="media-body">
														<h5 class="m_top10 font_weight">Total Agenda Points</h5>
														<h4 class="m_top5 font_weight">10,10,300</h4>
													</div>
												</div>
											</div>
											</div>
											<div class="row">
												<div class="col-sm-12">
													<div class="custom-div-bg m_top20">
														<div class="row">
															<div class="col-sm-6">
																<div class="border_left_width_green">
																	<h6 class="font_weight good_color margin_left_8">Approved</h6>
																	<h4 class="good_color m_top10 margin_left_8 font_weight">10,00,000</h4>
																</div>
															</div>
															<div class="col-sm-6">
																<div class="border_left_width_bad">
																	<h6 class="font_weight bad_color margin_left_8">Not-Approved </h6>
																	<h4 class="bad_color m_top10 margin_left_8 font_weight">10,300</h4>
																</div>
															</div>
														</div>    
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<h4 class="m_top10">Panchayaties vs Meetings</h4>
						</div>
					</div>
					<div class="row m_top10">
						<div class="col-sm-12">
							<div class="yellow-bdr-div">
								<div class="row">
										<div class="col-sm-4">
											<div class = "panel panel-default border_radius_5 m_bottom_0">
												<div class = "panel-heading">
													<div class="media">
														<div class="media-left">
															<i class="fa fa-check-circle green_color fa-2x"></i>
														</div>
														<div class="media-body">
															<h5 class="font_weight text_pos">Panchayaties - 1500</h5>
														</div>
													</div>
												</div>
												<div class="panel-body">
													<div class="row">
														<div class="text-center">
															<h5 class="font_weight">Conducted eMeetings - 2000</h5>
														</div>
													</div>
													<hr style="margin-top:10px;margin-bottom:10px;">
													<div class="row">
														<div class="col-sm-4 m_top10">
															<div class="border_left_blue">
																<h6 class="font_weight margin_left_8 text_color_e">General</h6>
																<h4 class="good_color m_top10 margin_left_8">1500</h4>
															</div>
														</div>
														<div class="col-sm-4 m_top10">
															<div class="border_left_width_green ">
																<h6 class="font_weight margin_left_8 text_color_e">Requested</h6>
																<h4 class="good_color m_top10 margin_left_8">400</h4>
															</div>
														</div>
														<div class="col-sm-4 m_top10">
															<div class="border_left_width_bad ">
																<h6 class="font_weight margin_left_8 text_color_e">Emergency</h6>
																<h4 class="bad_color m_top10 margin_left_8">100</h4>
															</div>
														</div>
													</div>
											   </div>
											</div>
										</div>
										<div class="col-sm-4">
											<div class = "panel panel-default border_radius_5 m_bottom_0">
												<div class = "panel-heading">
													<div class="media">
														<div class="media-left">
															<img src="Assests/images/150-panchayats.png" style="height:28px; width:28px;"/>
														</div>
														<div class="media-body">
															<h5 class="font_weight text_pos">Panchayaties - 150</h5>
														</div>
													</div>
												</div>
												<div class="panel-body">
													<div class="row">
														<div class="col-sm-6" style="margin-top: 43px;">
															<h4 class="font_weight" style="position: relative;top: -20px;">Re Scheduled eMeetings</h4>
														</div>
														<div class="col-sm-6" style="margin-top: 43px;">
															<h4 class="font_weight f_24" style="position: relative;top: -20px;">1000</h4>
														</div>
													</div>
											   </div>
											</div>
										</div>
										<div class="col-sm-4">
											<div class = "panel panel-default border_radius_5 m_bottom_0">
												<div class = "panel-heading">
													<div class="media">
														<div class="media-left">
															<i class="fa fa-times-circle-o red_color fa-2x"></i>
														</div>
														<div class="media-body">
															<h5 class="font_weight text_pos">Panchayaties - 500</h5>
														</div>
													</div>
												</div>
												<div class="panel-body">
													<div class="row">
														<div class="text-center">
															<h5 class="font_weight">Not Conducted eMeetings - 1000</h5>
														</div>
													</div>
													<hr style="margin-top:10px;margin-bottom:10px;">
													<div class="row">
														<div class="col-sm-4 m_top10">
															<div class="border_left_yellow">
																<h6 class="font_weight margin_left_8 text_color_e">Beyond&nbsp;30&nbsp;days</h6>
																<h4 class="good_color m_top10 margin_left_8">1500</h4>
															</div>
														</div>
														<div class="col-sm-4 m_top10">
															<div class="border_left_pink ">
																<h6 class="font_weight margin_left_8 text_color_e">Beyond&nbsp;60&nbsp;days</h6>
																<h4 class="good_color m_top10 margin_left_8">400</h4>
															</div>
														</div>
														<div class="col-sm-4 m_top10">
															<div class="border_left_teak ">
																<h6 class="font_weight margin_left_8 text_color_e">Beyond&nbsp;90&nbsp;days</h6>
																<h4 class="bad_color m_top10 margin_left_8">100</h4>
															</div>
														</div>
													</div>
											   </div>
											</div>
										</div>
									</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</main>

<script src="Assests/js/jquery-3.2.1.js" type="text/javascript"></script>
<script src="Assests/js/bootstrap.js" type="text/javascript"></script>
<script src="Assests/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="Assests/Plugins/Scroller/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="Assests/Plugins/Scroller/jquery.mousewheel.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="Assests/Plugins/DateTime/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/exportButtons.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/jsZip.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/pdf.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/v5font.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/htmlButtons.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/dataTables.fixedColumns.min.js" type="text/javascript"></script>
</body>
</html>