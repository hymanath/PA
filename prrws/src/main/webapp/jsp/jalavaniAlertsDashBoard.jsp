<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Jalavani Minister Dashboard</title>
<link href="Assests/css/bootstrap.css" rel="stylesheet" type="text/css"/>
<link href="Assests/css/custom.less" rel="stylesheet" type="text/less"/>
<link href="https://cdnjs.cloudflare.com/ajax/libs/highcharts/6.0.6/css/highcharts.css" rel="stylesheet" type="text/css"/>
<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Scroller/jquery.mCustomScrollbar.css" type="text/less" rel="stylesheet"/>
<link href="Assests/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<link href="https://cdn.datatables.net/buttons/1.3.1/css/buttons.dataTables.min.css" type="text/css" rel="stylesheet">
<script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>
<script src="https://use.fontawesome.com/07d3416f74.js"></script>
<script src="Assests/Plugins/Less/less.js"></script>
<link rel="stylesheet" type="text/css" href="Assests/SimplePagination/simplePagination.css"/>
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
					<p>Rural Water Supply</p>
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
<section class="m_top20">
	<div class="container-fluid">
		<div class="row">
			<div class="p_lr20">
				<div class="col-sm-12 m_top_bottom pad_border white-block">
				
				   
					<div class="col-sm-12">
						<div class="panel panel-default m_top_bottom">
							<h2 style="padding:15px 0px 0px 30px;">Jalavani</h2>
							<div class="p_lr20"> 
								<div class="panel-body m_top_bottom">
									<div class="row">
										<div class="col-sm-3 white-block">
											<nav class="white-block border_Block">
												<div id="JalavaniDashBoardOverviewTabsId"></div>
												<select class="form-control" role="tabListMobile">
													<option tab_id="all_Sources_Id" value="0">ALL SOURCES</option>
													<option tab_id="call_center_Id" value="1">CALL CENTER</option>
													<option tab_id="print_media_Id" value="2">PRINT MEDIA</option>
													<option tab_id="electronic_media_Id" value="3">ELECTRONIC MEDIA</option> 
													<option tab_id="social_media_Id" value="4">SOCIAL MEDIA</option> 
												</select>
											</nav>	
										</div>
										<div class="col-sm-9" style="border:2px solid #E3E4E4; border-left:none; background-color:#F1F1F1;">
											<div class="tab-content">
												<div role="tabpanel" class="tab-pane active " id="all_Sources_Id">
													<div class="row pad_5 white-block">
														<div id="AllSourcesId"></div>
													</div>
													<div class="m_top10"></div>
													<div class="row">
														<div id="areasplineChartId" style="height:300px;"></div>
														<div class="m_top10">
															<div id="stackedColumnChartId" style="height:300px;"></div>
														</div>
														<div class="col-sm-12">
															<div class="row" id="FeedbackId">
															</div>
														</div>	
													</div>
												</div>
												
												<div role="tabpanel" class="tab-pane " id="call_center_Id">
													<div class="row pad_10" style="background-color:#F1F1F1;">
														<div class="col-sm-3">
															<div class="row">
																<div class="pad_5">
																	<div class="pad_10 brdR_3 box_shad white-block" style="border: 1px solid #447BB3;">
																		<div class="media m_top10">
																			<div class="media-left">
																			  <img src="Assests/images/call_center_calls_icon.PNG" class="media-object" style="width:50px">
																			</div>
																			<div class="media-body">
																				<h5 class="media-heading" style="color:#F6A323;"><b>CALL&nbsp;CENTER&nbsp;CALLS</b></h5>
																				<h3>1451</h3>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
														</div>
														<div class="col-sm-3">
															<div class="row">
																<div class="pad_5">
																	<div class="pad_10 brdR_3 box_shad" style="border: 1px solid #3C3B3B; background-color:#F6A323;">
																		<div class="media m_top10">
																			<div class="media-left">
																			  <i class="far fa-bell fa-4x"></i>
																			</div>
																			<div class="media-body">
																				<h5 class="media-heading"><b>CALL&nbsp;CENTER&nbsp;ALERTS</b></h5>
																				<h3>1299</h3>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>
													<div class="row" style="background-color:#F1F1F1; padding-left:4px;">
														<div id="areasplineChartId2" style="height:300px;"></div>
														<div class="col-sm-7">
															<div class="row m_top10" style="padding-right:5px">
																<div id="stackedColumnChartId2" style="height:300px;"></div>
															</div>
															<div class="row" id="FeedbackId0">
															</div>
														</div>
														<div class="col-sm-5">
															<div class="row m_top10" style="padding-left:5px">
																<div id="StackColumnChartId" style="height:300px;"></div>
															</div>
															<div class="row">
																<div class="pad3">
																	<div class="col-sm-6">
																		<div class="row">
																			<div class="white-block padding_15">
																				<h5>LAST 30 DAYS</h5>
																				<h4 style="color:#4BB647;" class="m_top10">1.5%<span style="margin-left:15px;"><i class="fas fa-arrow-up fa-lg" style="color:#4BB647;"></i></span></h4>
																			</div>
																		</div>
																	</div>
																	<div class="col-sm-6">
																		<div class="row">
																			<div class="white-block padding_15">
																				<h5>LAST 60 DAYS</h5>
																				<h4 style="color:#4BB647;" class="m_top10">2.5%<span style="margin-left:15px;"><i class="fas fa-arrow-up fa-lg" style="color:#4BB647;"></i></span></h4>
																			</div>
																		</div>
																	</div>
																</div>
															</div>	
														</div>
													</div>
												</div>
												<div role="tabpanel" class="tab-pane showStatus" id="print_media_Id" style="background-color:#F1F1F1;">
													<div class="row m_top5" id="PrintMediaId">
														
														<div class="col-sm-3">
															<div class="brdR_3 padding_15 m_top_bottom_5 white-block box_shad" style="border: 1px solid #5E5E5E;">
																<div class="media">
																	<div class="media-left media-top">
																		<img src="Assests/images/print_media_icon.PNG" class="media-object" style="width:49px">
																	</div>
																	<div class="media-body">
																		<h5 class="media-heading">POSITIVE&nbsp;NEWS</h5>
																		<h3><b>320</b></h3>
																	</div>
																</div>
															</div>
														</div>
														<div class="col-sm-3">
															<div class="brdR_3 padding_15 m_top_bottom_5 white-block box_shad" style="border: 1px solid #41B549;">
																<div class="media">
																	<div class="media">
																		<div class="media-left media-top">
																			<i class="far fa-smile fa-4x" style="color:#41B549;"></i>
																		</div>
																		<div class="media-body">
																			<h5 class="media-heading" style="color:#41B549;">PRINT&nbsp;MEDIA&nbsp;NEWS</h5>
																			<h3><b>530</b></h3>
																		</div>
																	</div>
																</div>
															</div>
														</div>
														<div class="col-sm-3">
															<div class="brdR_3 padding_15 m_top_bottom_5 white-block box_shad" style="border: 1px solid #EE4E5B;">
																<div class="media">
																	<div class="media-left media-top">
																		<i class="far fa-frown fa-4x" style="color:#EE4E5B;"></i>
																	</div>
																	<div class="media-body">
																		<h5 class="media-heading" style="color:#EE4E5B;">NEGATIVE&nbsp;NEWS</h5>
																		<h3><b>210</b></h3>
																	</div>
																</div>
															</div>
														</div>
														<div class="col-sm-3">
															<div class="brdR_3 padding_15 m_top_bottom_5 box_shad" style="background-color:#F6A323;">
																<div class="media">
																	<div class="media-left media-top">
																		<i class="far fa-bell fa-4x"></i>
																	</div>
																	<div class="media-body">
																		<h5 class="media-heading">PRINT&nbsp;MEDIA&nbsp;ALERTS</h5>
																		<h3><b>203</b></h3>
																	</div>
																</div>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="pad_5">
															<h5 class="font_weight m_top5">IMPACT LEVEL</h5>
															<div class="col-sm-2 m_top_bottom padding_15 border_white marl" style="background-color:#F3F3F4;">
																<h5><b>STATE</h5><h5> LEVEL</b></h5>
																<div class="col-sm-6">
																	<h3 class="m_top20">20</h3>
																</div>
																<div class="col-sm-6 m_top10">
																	<div class="row">
																		<div class="pad_2 custom-border white-block">
																			<h5><i class="far fa-smile fa-lg" style="color:#67BD47;"></i><b>12</b></h5>
																		</div>	
																	</div>	
																	<div class="row">
																		<div class="pad_2 custom-border white-block">
																			<h5> <i class="far fa-frown fa-lg" style="color:#EE4E5B;"></i><b>8</b></h5>
																		</div>	
																	</div>	
																</div>
															</div>
														
															<div class="col-sm-2 col-half m_top_bottom padding_15 border_white" style="background-color:#F2F2F2;">
																<h5><b>DISTRICT</h5><h5> LEVEL</b></h5>
																<div class="col-sm-6">
																	<h3 class="m_top20">20</h3>
																</div>
																<div class="col-sm-6 m_top10">
																	<div class="row">
																		<div class="pad_2 custom-border white-block">
																			<h5><i class="far fa-smile fa-lg" style="color:#67BD47;"></i><b>12</b></h5>
																		</div>	
																	</div>	
																	<div class="row">
																		<div class="pad_2 custom-border white-block">
																			<h5> <i class="far fa-frown fa-lg" style="color:#EE4E5B;"></i><b>8</b></h5>
																		</div>	
																	</div>
																</div>
															</div>
															<div class="col-sm-2 col-half m_top_bottom border_white padding_15 over" style="background-color:#EDEDED;">
																<h5><b>CONSTITUENCY</h5><h5> LEVEL</b></h5>
																<div class="col-sm-6">
																	<h3 class="m_top20">50</h3>
																</div>
																<div class="col-sm-6 m_top10">
																	<div class="row">
																		<div class="pad_2 custom-border white-block">
																			<h5><i class="far fa-smile fa-lg" style="color:#67BD47;"></i><b>12</b></h5>
																		</div>	
																	</div>	
																	<div class="row">
																		<div class="pad_2 custom-border white-block">
																			<h5> <i class="far fa-frown fa-lg" style="color:#EE4E5B;"></i><b>8</b></h5>
																		</div>	
																	</div>
																</div>
															</div>
															<div class="col-sm-2 col-half m_top_bottom padding_15 border_white" style="background-color:#E6E6E6;">
																<h5><b>MANDAL</h5><h5> LEVEL</b></h5>
																<div class="col-sm-6">
																	<h3 class="m_top20">20</h3>
																</div>
																<div class="col-sm-6 m_top10">
																	<div class="row">
																		<div class="pad_2 custom-border white-block">
																			<h5><i class="far fa-smile fa-lg" style="color:#67BD47;"></i><b>12</b></h5>
																		</div>	
																	</div>	
																	<div class="row">
																		<div class="pad_2 custom-border white-block">
																			<h5> <i class="far fa-frown fa-lg" style="color:#EE4E5B;"></i><b>8</b></h5>
																		</div>	
																	</div>
																</div>
															</div>
															<div class="col-sm-2 col-half m_top_bottom padding_15 border_white" style="background-color:#E0E0E0;">
																<h5><b>VILLAGE</h5><h5> LEVEL</b></h5>
																<div class="col-sm-6">
																	<h3 class="m_top20">20</h3>
																</div>
																<div class="col-sm-6 m_top10">
																	<div class="row">
																		<div class="pad_2 custom-border white-block">
																			<h5><i class="far fa-smile fa-lg" style="color:#67BD47;"></i><b>12</b></h5>
																		</div>	
																	</div>	
																	<div class="row">
																		<div class="pad_2 custom-border white-block">
																			<h5> <i class="far fa-frown fa-lg" style="color:#EE4E5B;"></i><b>8</b></h5>
																		</div>	
																	</div>
																</div>
															</div>
														</div>
													</div>
													<div class="row m_top10">
														<div id="areasplineChartId3" style="height:300px;"></div>
														<div class="m_top10">
															<div id="stackedColumnChartId3" style="height:300px;"></div>
														</div>
														<div id="FeedbackId1"></div>
													</div> 	
												</div>
												<div role="tabpanel" class="tab-pane white-block showStatus" id="electronic_media_Id">Settings Tab.</div>
												<div role="tabpanel" class="tab-pane white-block showStatus" id="social_media_Id">Settings .</div>
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
		<div style="padding:0px 20px 0px 20px;">
			<div class="row m_top10">
				<div class="col-sm-12 m_top10 white-block">
					<h4 class="m_top10">FILTERS</h4>
					<div class="col-sm-12 m_top_bottom20">
						<div class="col-sm-3">
							<label>SOURCE TYPE</label>
							<select class="form-control">
								<option>All</option>
								<option>NEWS</option>
								<option>ALERTS</option>
							</select>
						</div>
						<div class="col-sm-3">
							<label>ALERT SOURCE</label>
							<select class="form-control">
								<option>All</option>
								<option>CALL CENTER</option>
								<option>PRINT MEDIA</option>
							</select>
						</div>
						<div class="col-sm-3">
							<label>VIEW DATA</label>
							<select class="form-control">
								<option>All</option>
								<option>ALERTS</option>
								<option>STATUS</option>
							</select>	
						</div>
						<div class="col-sm-2" style="padding-top:25px;">
							<button type="button" class="btn btn-success form-control" style="background-color:#fff; color:#4BB647;">GET DETAILS</button>
						</div>
					</div>
				</div>
			</div>	
		
		
			<div class="row m_top10">
				<div class="panel-group" id="#accordionDeptSLA">
					<div class="panel panel-default panel-black">
						<div class="panel-heading" role="tab" id="headingDeptSLA">
							<a role="button" class="panelCollapseIcon collapsed"  data-toggle="collapse" data-parent="#accordion" href="#collapseDeptSLA">
								<h4 class="panel-title">DISTRICT WISE OVERVIEW</h4>
							</a>
						</div>
						<div id="collapseDeptSLA" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingDeptSLA">
							<div class="panel-body">
								<div class="table-responsive m_top_bottom">
									<table class="table table-bordered table_customStl">
										<thead>
											<tr>
												<th>Location</th>
												<th>TOTAL ALERTS</th>
												<th>Notified</th>
												<th>Proposal</th>
												<th>Action In Progess</th>
												<th>Completed & Closed</th>
												<th>Others</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>Srikakulam</td>
												<td>182</td>
												<td>77<small class="NoClr">22%</small></td>
												<td>22<small class="NoClr">22%</small></td>
												<td>44<small class="NoClr">22%</small></td>
												<td>22<small class="NoClr">22%</small></td>
												<td>44<small class="NoClr">22%</small></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="panel-group" id="#accordionDeptCLA">
					<div class="panel panel-default panel-black">
						<div class="panel-heading" role="tab" id="headingDepCLA">
							<a role="button" class="panelCollapseIcon collapsed"  data-toggle="collapse" data-parent="#accordion" href="#collapseDeptCLA">
								<h4 class="panel-title">CONSTITUENCY WISE OVERVIEW</h4>
							</a>
						</div>
						<div id="collapseDeptCLA" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingDeptCLA">
							<div class="panel-body">
								
							</div>
						</div>
					</div>
				</div>
				<div class="panel-group" id="#accordionDeptMLA">
					<div class="panel panel-default panel-black">
						<div class="panel-heading" role="tab" id="headingDeptMLA">
							<a role="button" class="panelCollapseIcon collapsed"  data-toggle="collapse" data-parent="#accordion" href="#collapseDeptMLA">
								<h4 class="panel-title">MANDAL WISE OVERVIEW</h4>
							</a>
						</div>
						<div id="collapseDeptMLA" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingDeptMLA">
							<div class="panel-body">
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


		<div class="row m_top20">
			<div class="col-sm-12">
				<div id="collapseId"></div>
				<button class="btn btn-primary" id="showModalId">View Popup</button>
			</div>
		</div>
		
		<div class="modal fade" id="jalavaniMinisterPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document" style="width:95%;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myModalLabel">TOTAL</h4>
					</div>
					<div class="modal-body modal-insurance">
						<div id="filter" style="display:none;"></div>
						<div id="jalavaniMinisterPopupBody" class="padding_15" style="background-color:#F1F1F1;"></div>
					
					</div>
				</div>
			</div>
		</div>
		
	</div>
</section>
<script src="Assests/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="Assests/js/bootstrap.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="Assests/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/exportButtons.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/jsZip.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/pdf.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/v5font.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/htmlButtons.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/dataTables.fixedColumns.min.js" type="text/javascript"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/highcharts/6.0.6/highcharts.js"></script>	
<script type="text/javascript" src="Assests/js/locationHierarchy.js"></script>
<script src="Assests/js/jalavaniAlertsDashBoard.js" type="text/javascript"></script>
	
</body>
</html>
	