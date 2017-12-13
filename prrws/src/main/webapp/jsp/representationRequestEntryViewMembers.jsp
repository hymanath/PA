<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>EDIT DATA ENTRY FORM</title>
<link href="Assests/css/bootstrap.css" rel="stylesheet" type="text/css"/>
<link href="Assests/css/custom.less" rel="stylesheet" type="text/less"/>
<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Scroller/jquery.mCustomScrollbar.css" type="text/less" rel="stylesheet"/>
<link href="Assests/Plugins/Chosen/chosen.css" type="text/less" rel="stylesheet"/>
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
										<div class="row">
  											<div class="col-sm-12 m_top10">
												<div class="menu-block" style="background-color:#ff5e1c">
													<a href="solidWasteManagementDashboard">
														<h3>SWM</h3>
                           								 <p>Solid Waste Management</p>
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
	<section>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-12">
					<div class="panel-group">
						<div class="panel panel-default panel-lightGreen">
							<div class="panel-heading">
								<h4 class="panel-title f_22" style="font-weight:normal !important;">SEARCH REPRESENTEE DETAILS <a class="btn btn-success btn-xs  btn-xs pull-right" target="_blank" href="representationRequestEntry"> ADD NEW PETITION </a></h4>
							</div>
								<div class="panel-body">								
									<div class="row m_top20">
									<!--	<div class="col-sm-9">	
											<label class="radio-inline ">
												<input  class="searchCls m_top0" type="radio" name="optradio" value="designation" checked> <span title="Refferer / Representee Designation wise"> REF./REPR. DESIGNATION WISE</span>
											</label>											
											<label class="radio-inline">
												<input  class="searchCls m_top0" type="radio" name="optradio" value="department" >DEPARTMENT WISE
											</label>
											<label class="radio-inline ">
												<input class="searchCls m_top0" type="radio" name="optradio" value="refLocation" >REFFERRE LOCATION WISE
											</label>
											<label class="radio-inline ">
												<input class="searchCls m_top0"  type="radio" name="optradio" value="workLocation" >WORK LOCATION WISE
											</label>
											<label class="radio-inline ">
												<input class="searchCls m_top0"  type="radio" name="optradio" value="advanceBtnId" >ADVANCED SEARCH 
											</label>
										</div>  -->
										<div class="col-sm-3" id="locationDivlId">	
										<label> SEARCH TYPE</label>
											<select class="form-control chosen-select" id="locationSelId" >
												<option value="all">Select Location</option>
												<option value="work"> WORK lOCATION WISE</option>
												<option value="representee"> REPRESENTEE lOCATION WISE</option>
												<option value="referral"> REFERREL lOCATION WISE</option>
												<option value="referrelDesignation"> REFERREL DESIGNATION WISE</option>
												<option value="representeeDesignation"> REPRESENTEE DESIGNATION WISE</option>
												<option value="department"> DEPARTMENT WISE</option>
												<option value="name"> NAME</option>
												<option value="mobile"> MOBILE NUMBER</option>
												<option value="email"> EMAIL ID</option>
												<option value="endorsmentNO"> endorsment NUMBER</option>
												
											</select>
											<div class="error_colorCls" id="locationErrDivlId"></div>
										</div>
										<div class="col-sm-3 col-sm-offset-6">	
											<div class="input-group inline-block">
												<span class="input-group-addon">
													<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
												</span>
												<input type="text"  class="form-control" id="dateRangePicker"/>
											</div>
										</div>
									</div>
									<div class="m_top20" id="normalSearchDivId"  >
										<div class="row m_top20">
											<div class="col-sm-3" id="designationDiv" style="display:none;">
												<label> DESIGNATION </label>
												<select class="form-control chosen-select" id="designationsId">
													<option value="0">Select Designation</option>
												</select>
												<div class="error_colorCls" id="designationErrDiv"></div>
											</div>
											<div class="col-sm-3" id="departMentsDiv" style="display:none;">
											<label> DEPARTMENT</label>
												<select class="form-control chosen-select" id="departmentId">
													<option value="0">Select Department</option>
												</select>
												<div class="error_colorCls" id="departMentsErrDiv"></div>
											</div>	
											<div class="col-sm-3" id="nameDivid" style="display:none;">
											<label> NAME</label>
												<input type="text" class="form-control" style="text-align:center;height:40px" id="nameId" placeholder="Please enter Name">
												<div class="error_colorCls" id="nameErrDivId"></div>
											</div>	
											<div class="col-sm-3" id="mobileDivid" style="display:none;">
											<label>MOBILE NUMBER</label>
												<input type="text" class="form-control" style="text-align:center;height:40px" id="mobileId" placeholder="Please enter mobile number">
												<div class="error_colorCls" id="mobileErrDivId"></div>
											</div>	
											<div class="col-sm-3" id="emailDivid" style="display:none;">
											<label> EMAIL ID</label>
												<input type="text" class="form-control" style="text-align:center;height:40px" id="emailId" placeholder="Please enter email">
												<div class="error_colorCls" id="emailErrDivId"></div>
											</div>	
											<div class="col-sm-3" id="endorsmentNoDivid" style="display:none;">
											<label> ENDORSMENT NUMBER</label>
												<input type="text" class="form-control" style="text-align:center;height:40px" id="endorsmentNoId" placeholder="Please enter endorsment number">
												<div class="error_colorCls" id="endorsmentNoErrDivId"></div>
											</div>	
										</div>	
										<div class="row m_top20">
											<div class="col-sm-3" id="districtCandDiv">
												<label>DISTRICT</label>
												<select class="form-control chosen-select" id="districtCandId">
													<option value="0">Select District</option>
												</select>
												<div class="error_colorCls" id="districtCandErrDiv"></div>
											</div>
											<div class="col-sm-3" id="constituencyCanDiv">
											<label>CONSTITUENCY</label>
												<select class="form-control chosen-select" id="constituencyCanId">
													<option value="0">Select Constituency</option>
												</select>
												<div class="error_colorCls" id="constituencyCanErrDiv"></div>
											</div>
											<div class="col-sm-3" id="mandalCanDiv">
											<label>MANDAL</label>
												<select class="form-control chosen-select" id="mandalCanId">
													<option value="0">Select Mandal</option>
												</select>
												<div class="error_colorCls" id="mandalCanErrDiv"></div>
											</div>
										</div>
							    	</div>
									<div class="row m_top20" id="advancedSearchDivId" style="display:none;">
										<div class="col-sm-6">	
											<div style="border:1px solid #ddd;padding:10px;box-shadow: 0 0 2px 2px rgba(0, 0, 0, 0.2);">
												<h4 class="font_weight f_18">ADVANCED SEARCH WISE : </h4>
												<div class="m_top10">
													<label class="radio-inline m_top5">
														<input type="radio"  class="advancedSrchCls"  name="optradio1" value="name" checked> NAME
													</label>
													<label class="radio-inline m_top5">
														<input type="radio" class="advancedSrchCls"  name="optradio1" value="mobileNo"> MOBILE NO
													</label>
													<label class="radio-inline m_top5">
														<input type="radio" class="advancedSrchCls"  name="optradio1" value="refCode"> ENDORSMENT ID
													</label>
													<label class="radio-inline m_top5">
														<input type="radio" class="advancedSrchCls"  name="optradio1" value="emailId"> EMAIL-ID
													</label>
												</div>
												<div class="row m_top10">
													<div class="col-sm-12">
														<input type="text" class="form-control" style="text-align:center;height:55px" id="advancedSearchVal">
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="row m_top10">
										<div class="col-sm-12">						
											<button type ="button" class="btn btn-lg btn-success" id="advanceSearchId" style="cursor: pointer; font-weight: bold; border-radius: 0px;">SEARCH</button>
										</div> 
									</div>
									<div class="row m_top10">
										<div class="col-sm-12">
											<div id="representationRequestEntryTable"></div>
										</div>
									</div>
								</div>	
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</main>



<script src="Assests/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="Assests/js/bootstrap.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="Assests/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="Assests/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="Assests/Plugins/Scroller/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="Assests/Plugins/Scroller/jquery.mousewheel.js" type="text/javascript"></script>
<script src="Assests/SimplePagination/simplePagination3.js" type="text/javascript"></script>
<!--<script src="Assests/ruralWaterSupply/custom.js" type="text/javascript"></script>-->
<script src="https://cdn.datatables.net/buttons/1.3.1/js/dataTables.buttons.min.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js" type="text/javascript"></script>
<script src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.27/build/pdfmake.min.js" type="text/javascript"></script>
<script src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.27/build/vfs_fonts.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/buttons/1.3.1/js/buttons.html5.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/buttons/1.3.1/js/buttons.print.min.js" type="text/javascript"></script>
<script src="Assests/representationRequest/representationRequestEntryViewMembers.js" type="text/javascript"></script>
</body>
</html>