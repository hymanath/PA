<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Solid Waste Management Dashboard</title>
<link href="Assests/css/bootstrap.css" rel="stylesheet" type="text/css"/>
<link href="Assests/css/custom.less" rel="stylesheet" type="text/less"/>
<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Scroller/jquery.mCustomScrollbar.css" type="text/less" rel="stylesheet"/>
<link href="Assests/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<link href="https://cdn.datatables.net/buttons/1.3.1/css/buttons.dataTables.min.css" type="text/css" rel="stylesheet"/>
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
					<h4 class="text-capital">Panchayati Raj, RD & RWS</h4>
					<p>SOLID WASTE MANAGEMENT - AP</p>
				</div>
				<div class="col-sm-2 col-xs-12 col-sm-offset-3">
					<img src="Assests/images/NREGS-LOGO.png" class="m_top5"/>
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
												<div class="menu-block" style="background-color:#1e92b2">
													<a href="swachhBharatMissionIHHL">
														<h3>IHHL</h3>
														<p>Swatch Bharat Mission</p>
													</a>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="col-sm-12 m_top10">
									<div class="menu-heading-block">
										<h4>PANCHAYATI RAJ</h4>
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
													<a href="#">
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
														<p>Light Monitoring Dashboard</p>
													</a>
												</div>
											</div>
											<div class="col-sm-6 m_top10">
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
												<div class="menu-block" style="background-color:#ff1c5e">
													<a href="RuralDevelopmentDashboard">
														<h3>RD</h3>
														<p>Rural Development Dashboard</p>
													</a>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="col-sm-12 m_top10">
									<div class="menu-block" style="background-color:#de4524 ">
										<a href="itcDashboard">
											<h3>IT E & C</h3>
											<p>Dashboard</p>
										</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</nav>	
</header>
<main>
	<div class="container">
		<section>
			<div class="row">
				<div class="col-sm-12">
					<div class="row white_block_swm">
						<div id="swmInfraustructure">
							<div class="col-sm-6">
									<div class="row" style="border: 1px solid lightgrey;padding:15px;margin:2px">
									<h4 style="padding:10px"><img src="Assests/icons/swm/Group 2186.png" style=""><span>SWM-INFRASTRUCTURE OVERVIEW</span></h4>
									<div class="col-sm-12 m_top_bottom p_top_bottom" style="border:1px solid lightgrey">
										<div class="row">
											<div class="col-sm-8">
												<h5><b>RFID TAGGED HOUSES</b></h5>
												<div class="media">
													<div class="media-left">
														<img src="Assests/icons/swm/Group 2188.png">
													</div>
													<div class="media-body">
														<h5><b>RFID TAGGED HOUSES</b></h5>
														<p>Solid Waste / Garbage</p>
													</div>
												</div>
											</div>
											<div class="col-sm-4">
												<div class="well" style="margin-top:10px">
													<h4 class="text-center"><b id="rfidTaggedHouses"></b></h4>
												</div>
											</div>
										</div>
										
									</div>
									<div class="col-sm-12 p_top_bottom m_top_bottom " style="border:1px solid lightgrey">
										<div class="row">
											<div class="col-sm-8">
												<h5><b>REGISTRED FARMERS</b></h5>
												<div class="media">
													<div class="media-left">
														<img src="Assests/icons/swm/Group 2193.png">
													</div>
													<div class="media-body">
														<h5><b>FARMERS</b></h5>
														<p>Cattle Dung</p>
													</div>
												</div>
											</div>
											<div class="col-sm-4">
												<div class="well" style="margin-top:10px">
													<h4 class="text-center"><b id="registeredFarmers"></b></h4>
												</div>
											</div>
										</div>
									</div>
									<div class="col-sm-12 p_top_bottom  m_top_bottom" style="border:1px solid lightgrey">
										<div class="row">
											<div class="col-sm-8">
												<h5><b>GREEN AMBASSADOR's <br/><span style="font-size:10px">MAN POWER</span></b></h5>
												<table class="table" id="swgInfTable">
													<tr style="padding:10px">
														<td><img src="Assests/icons/swm/Group 2190.png"></td>
														<td>MGNREGS</td>
														<td id="mgnrgsId"></td>
													</tr>
													<tr style="padding:10px">
														<td><img src="Assests/icons/swm/Group 2191.png"></td>
														<td>PR</td>
														<td id="prId"	></td>
													</tr>
													<tr style="padding:10px">
														<td><img src="Assests/icons/swm/Group 2195.png"></td>
														<td>PUBLIC</td>
														<td id="publicId"></td>
													</tr>
												</table>
											</div>
											<div class="col-sm-4">
												<div class="well text-center" style="margin-top:10px">
													<h5>TOTAL MAN POWER</h5>
													<h4><b id="totalManPower"></b></h4>
												</div>
											</div>
										</div>
									</div>
									<div class="col-sm-12 p_top_bottom m_top_bottom" style="border:1px solid lightgrey">
										<div class="row">
											<div class="col-sm-8">
												<h5><b>REGISTERED VEHICLES</b></h5>
												<table class="table">
													<tr>
														<td><img src="Assests/icons/swm/tractor-front.png"></td>
														<td><img src="Assests/icons/swm/auto-ricksaw.png"></td>
														<td><img src="Assests/icons/swm/tricycle.png"></td>
														<td><img src="Assests/icons/swm/Group 2194.png"></td>
													</tr>
													<tr>
														<td id="tractorId"></td>
														<td id="autoId"></td>
														<td id="trycycleId"></td>
														<td id="evehicleId"></td>
													</tr>
													
												</table>
											</div>
											<div class="col-sm-4">
												<div class="well text-center" style="margin-top:10px">
													<h5>TOTAL VEHICLES</h5>
													<h4><b id="totalRegVehicles"></b></h4>
												</div>
											</div>
										</div>
									</div>
									<div class="col-sm-12 p_top_bottom m_top_bottom" style="border:1px solid lightgrey">
										<div class="row">
											<div class="col-sm-4">
												<div class="well text-center" style="margin-top:10px">
													<h4>GP</h4>
													<h4><b id="gpId"></b></h4>
												</div>
											</div>
											<div class="col-sm-4 text-center" style="padding:20px">
												<img src="Assests/icons/swm/Group 2238.png">
											</div>
											<div class="col-sm-4 pull-right">
												<div class="well text-center" style="margin-top:10px">
													<h5>BLOCKS</h5>
													<h4><b id="blocksId"></b></h4>
												</div>
											</div>
											<div class="col-sm-12" style="">
												<h4 style="padding:15px;color:#fff;background-color:#00A85A">RFID TRACKING<i class="glyphicon glyphicon-ok pull-right" style="color:#00A85A;background-color:#fff;padding:5px;border-radius:14px;margin-top:-5px;margin-left:5px"></i><span id="rfidTracking" class="pull-right">%</span></h4>
											</div>
										</div>
										</div>
									</div>
							</div>
							<div class="col-sm-6">
								<div  class="row" style="border: 1px solid lightgrey;padding:15px">
								<h4 style="padding:10px"><img src="Assests/icons/swm/Group 2187.png"><span>SWM-VERMI COMPOST (TROUGH-PIT)</span></h4>
								<div class="col-sm-12 p_top_bottom m_top_bottom" style="border:1px solid lightgrey">
									<h5><b>TOTAL SWM COLLECTION<br/><span style="font-size:10px">TON'S</span></b></h5>
									<div class="col-sm-4">
										<div class="media">
											<div class="media-left">
												<img src="Assests/icons/swm/Group 2188.png">
											</div>
											<div class="media-body">
												<h5><b>HOUSES</b></h5>
												<P>Solid Waste / Garbage</P>
												<h4 style="m_top10"><b id="solidWasteId"></b></h4>
												<span style="font-size:10px">TON's</span>
											</div>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="media">
											<div class="media-left">
												<img src="Assests/icons/swm/Group 2193.png">
											</div>
											<div class="media-body">
												<h5><b>FARMERS</b></h5>
												<P>Cattle Dung</P>
												<h4 style="m_top10"><b id="farmerCattleDung"></b></h4>
												<span style="font-size:10px">TON's</span>
											</div>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="well text-center">
											<h4><b id="totSwmId"></b></h4>
											<p>TON'S</p>
										</div>
									</div>
								</div>
								<h5>STAGE-I</h5>
								<div class="col-sm-12  m_top_bottom" style="border:1px solid lightgrey">
									<div class="row">
										<div class="col-sm-8">
											<p class="m_top20"><img src="Assests/icons/swm/Group 2338.png"><span style="padding-left:20px">NADAP - PITS</span></p>
										</div>
										<div class="col-sm-4">
											<div class="well m_top5 text-center">
												<h4><b id="stageOneId"></b></h4>
												<p>TON'S</p>
											</div>
										</div>
										</div>
								</div>
								<h5>STAGE-II</h5>
								<div class="col-sm-12  m_top_bottom" style="border:1px solid lightgrey">
									<div class="row">
										<div class="col-sm-8">
											<p class="m_top20"><img src="Assests/icons/swm/Group 2337.png"><span style="padding-left:20px">VERMI - PITS</span></p>
										</div>
										<div class="col-sm-4">
											<div class="well m_top5 text-center">
												<h4><b id="stageTwoId"></b></h4>
												<p>TON'S</p>
											</div>
										</div>
										</div>
								</div>
								<h5>STAGE-III</h5>
								<div class="col-sm-12  m_top_bottom" style="border:1px solid lightgrey">
									<div class="row">
										<div class="col-sm-8">
											<p class="m_top20"><img src="Assests/icons/swm/Group 2336.png"><span style="padding-left:20px">VERMI STOCK</span></p>
										</div>
										<div class="col-sm-4">
											<div class="well m_top5 text-center">
												<h4><b id="stageThreeId"></b></h4>
												<p>TON'S</p>
											</div>
										</div>
										</div>
								</div>
								<h5>PACKAGE STOCK DETAILS</h5>
								<div class="col-sm-12  m_top_bottom" style="border:1px solid lightgrey">
									<div class="row">
											<table class="table">
												<tr>
													<th>1 KG<br/><span style="font-size:10px">BAGS</span></th>
													<th>5 KG<br/><span style="font-size:10px">BAGS</span></th>
													<th>10 KG<br/><span style="font-size:10px">BAGS</span></th>
													<th>25 KG <br/><span style="font-size:10px">BAGS</span></th>
													<th>50 KG <br/><span style="font-size:10px">BAGS</span></th>
												</tr>
												<tr>
													<td id="onekgCount"></td>
													<td id="fivekgCount"></td>
													<td id="tenkgcount"></td>
													<td id="twentyfivekgCount"></td>
													<td id="fiftykgCount"></td>
												</tr>
											</table>
									</div>
								</div>
							</div>
							
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-12 m_top10">
					<div id="levelWiseOverviewId"></div>
				</div>
			</div>
			
			<div class="modal fade" id="swmModal" role="dialog">
    <div class="modal-dialog modal-lg">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
			<h4 id="onclickDistName" style="display:inline-block"></h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          
        </div>
        <div class="modal-body">
			<div class="row">
				<div id="swmModalContent"></div>
			</div>
          
        </div>
       
      </div>
      
    </div>
  </div>
		</section>
	</div>
</main>
<script src="Assests/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="Assests/js/bootstrap.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="Assests/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/exportButtons.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/jsZip.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/pdf.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/v5font.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/htmlButtons.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/dataTables.fixedColumns.min.js" type="text/javascript"></script>
<script src="Assests/solidWasteManagement/solidWasteManagement.js" type="text/javascript"></script>
</body>
</html>