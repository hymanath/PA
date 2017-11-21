<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Constituency Wise WorkStatus</title>
<link href="Assests/css/bootstrap.css" rel="stylesheet" type="text/css"/>
<link href="Assests/css/custom.less" rel="stylesheet" type="text/less"/>
<link href="Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Chosen/chosen.css" type="text/less" rel="stylesheet"/>
<link href="Assests/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
<script src="Assests/Plugins/Less/less.js"></script>
<script src="https://use.fontawesome.com/07d3416f74.js"></script>
<link href="Assests/css/print.css" rel="stylesheet" type="text/css"/>
<style>
body .mainDivHeaderCls h1, h2, h3, h4, h5, h6, p, ul, .form-group, .table{
	font-family:'Ubuntu', sans-serif !important;
}
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
					<h4 class="text-capital">Panchayati Raj & RD & RWS</h4>
					<p>Rural Water Supply - AP</p>
				</div>
				<div class="col-sm-1 col-xs-12 col-sm-offset-5">
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
											<div class="col-sm-12 m_top10">
												<div class="menu-block" style="background-color:#56A3C5">
													<a href="ruralWaterSupplyDashBoard">
														<h3>RWS</h3>
														<p>Rural Water Supply</p>
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
											<!-- <div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#2C546C">
													<a href="#">
														<h3>ENC</h3>
														<p>Engineering Dept</p>
													</a>
												</div>
											</div>-->
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
	<section class="navbar-section">
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-2 border_right">
					<div class="form-horizontal">
						<div class="form-group form-group-sm">
							<label class="col-sm-4 control-label" for="formGroupInputLarge">Financial Year: </label>
							<div class="col-sm-8">
								<select id="financialYearId" class="chosenSelect" multiple></select>
							</div>
						</div>
					</div>
				
				</div>
				<div class="col-sm-2 border_right">
					<div class="form-group">
							<select id="districtSelId" class="form-control chosenSelect">
								<option value="0"> SELECT DISTRICT</option>
							</select>
						</div>
				</div>
				<div class="col-sm-2 border_right">
					<div class="form-group">
							<select id="constituencySelId" class="form-control chosenSelect">
								<option value="0"> SELECT CONSTITUENCY</option>
							</select>
						</div>
				</div>
				<div class="col-sm-2 border_right">
					<div class="form-group">
							<select id="DepartmentsId" class="form-control chosenSelect">
								<option value="0" selected="">ALL DEPARTMENTS</option>
								<option value="1">ENC</option>
								<option value="3">MGNREGS</option>
								<option value="2">RWS</option>
								
							</select>
						</div>
				</div>
				<div class="col-sm-1">
					<button class="btn btn-md btn-success submitCls">Submit</button>
				</div>
				<div class="col-sm-2 border_right">
					<div class="input-group">
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-calendar"></i>
						</span>
						<input type="text" class="form-control" id="dateRangePickerAUM"/>
					</div>
				</div>
				
		   </div>
		   
		</div>
	</section>
</header>
	<div class="container m_top20">
		<div class="row">
			<div class="col-sm-11">
				<label class="radio-inline pull-right">
					<input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="withHeader" class="withAndOutHeaderCls">With Header
				</label>
				<label class="radio-inline pull-right" style="margin-right: 20px;">
					<input type="radio" name="inlineRadioOptions" id="inlineRadio2" value="withOutHeader" checked class="withAndOutHeaderCls">With Out Header
				</label>
			</div>
			<div class="col-sm-1">
				<button class="btn btn-md btn-success printViewCls pull-right" attr_divId="printableArea">Print</button>
			</div>
		</div>
		<div class="row">
			<p class="text-center waitingMsgCls">Please Wait Page is Loading...</p>
		</div>
	</div>
	
	<div class="mainDivHeaderCls printcontent" id="printableArea">
		<div class="container">
			<div class="white_block" style="box-shadow:none;padding:20px;">
					<div class="headingCssCls">
						<div class="row">
							<div class="col-sm-4 mobile-view">
								<h2 class="redColor">Nara Lokesh</h2>
								<p>
									<span><strong>Minister for Information Technology</strong></span><br/>
									<span><strong>Panchayathi Raj and Rural Development</strong></span><br/>
									<span class="govt_color"><strong>Government of Andhra Pradesh</strong></span>
								</p>
							</div>
							<div class="col-sm-4 text-center">
								<img src="Assests/images/aplogo.png"  alt="" class="imgWidthHeight"/>
							</div>
							<div class="col-sm-4 text-right mobile-view pull-right">
								<p>
									<span>4th Block, 1st Floor, Room No:214,</span><br/>
									<span>A.P Secretariat Office, Velagapudi,<br/></span>
									<span>Amaravathi, Andhra Pradesh</span>
								</p>
							</div>
						</div>
						<div class="row">
							<div class="to-date">
								<div class="row">
									<div class="col-sm-6">
										<div id="addressDivId"></div>
									</div>
									<div class="col-sm-6 text-right">
										<p>
											<!--<span><strong>Date: 17th November, 2017</strong></span><br/>-->
										</p>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="dear-sir">
							<div class="row">
								<div class="col-md-12 col-sm-12 col-xs-12">
									<p>Dear Sir,</p>
									<p>This is a Letter from <span id="headingId"></span></p>
								</div>
							</div>
						</div>
					</div>
					<!--<div class="go-works m_top10">
						<h4  id="mainHeadingId"></h4>
					</div>-->
					<div id="overAllDeparmentsDivId"></div>
					<div id="rwsDetailsDivId" class="pagebreak"></div>
					<div id="encDetailsDivId" class="pagebreak"></div>
					<div id="mgnrewsDetailsDivId" class="pagebreak"></div>
					<div class="footer">
						<p>
							<span>4th Block, 1st Floor, Room No:214, A.P Secretariat Office, Velagapudi</span><br/>
							<span>Amaravathi, Andhra Pradesh</span>
						</p>
					</div>	
			</div>
		</div>
		
	</div>
<script src="Assests/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="Assests/js/bootstrap.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="Assests/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="Assests/constituencyWorks/constituencyWiseWorkStatus.js" type="text/javascript" ></script>
<script>

</script>
</body>
</html>