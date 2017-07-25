<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Rural Development Dashboard</title>
<link href="Assests/less/bootstrap.less" rel="stylesheet" type="text/less">
<link href="Assests/css/custom.less" rel="stylesheet" type="text/less"/>
<link href="Assests/Plugins/DateTime/bootstrap-datetimepicker-build.less" type="text/less" rel="stylesheet"/>
<link href="Assests/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Scroller/jquery.mCustomScrollbar.css" type="text/less" rel="stylesheet"/>
<script src="Assests/Plugins/Less/less.js"></script>
<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
</head>
<header>
	<nav>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-1 col-xs-3 pad_left0">
					<img src="Assests/images/aplogo.png" class="logo"/>
				</div>
				<div class="col-sm-4 m_top10 col-xs-9">
					<h4 class="text-capital">Panchayati Raj, RD & RWS</h4>
					<p>Rural Development - AP</p>
				</div>
				<div class="col-sm-2 col-xs-12 col-sm-offset-3">
					<img src="Assests/images/NREGS-LOGO.png" class="m_top5"/>
				</div>
				<div class="col-sm-1 col-xs-12">
					<i class="glyphicon glyphicon-th menu-cls pull-right"></i>
					<div class="menu-data-cls">
						<div class="arrow_box_top">
							<div class="row">
								<div class="col-sm-12">
									<div class="menu-block" style="background-color:#FFBA00">
										<a href="newfundManagementDashboard">
											<h3>FMS</h3>
											<p>Fund Management System</p>
										</a>
									</div>
								</div>
								<div class="col-sm-12">
									<div class="menu-block" style="background-color:#56A3C5">
										<a href="ruralWaterSupplyDashBoard">
											<h3>RWS</h3>
											<p>RURAL WATER SUPPLY</p>
										</a>
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
											<div class="col-sm-12 m_top10">
												<div class="menu-block" style="background-color:#2C546C">
													<a href="#">
														<h3>ENC</h3>
														<p>ENGINEERING DEPARTMENT</p>
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
												<div class="menu-block" style="background-color:#88186B">
													<a href="RuralDevelopmentDashboard">
														<h3>RD</h3>
														<p>Rural Development</p>
													</a>
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
	</nav>
	<section class="navbar-section">
		<div class="container-fluid">
			<div class="row">
				<!--<div class="col-sm-2 border_right m_top5">
					<h4 class="arrowIconChanged"><i class="glyphicon glyphicon-menu-hamburger" style="font-size:13px;"></i>&nbsp;&nbsp;<span id="selectedName" style="text-transform: uppercase;cursor:pointer;" attr_levelid="2" attr_id="-1" title="Location Level">Andhra Pradesh </span></h4>
					<div class="multi-level-selection-menu arrow_box_top"></div>
				</div>-->
				<div class="col-sm-3 border_right">
					<div class="row">
						<div class="col-sm-4 m_top5">
							<label>FROM DATE</label>
						</div>
						<div class="col-sm-8">
							<div class="input-group inline-block">
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
								</span>
								<input type="text" class="form-control" id="dateRangePickerMGNF"/>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-3 border_right">
					<div class="row">
						<div class="col-sm-4 m_top5">
							<label>TO DATE</label>
						</div>
						<div class="col-sm-8">
							<div class="input-group inline-block">
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
								</span>
								<input type="text" class="form-control" id="dateRangePickerMGNT"/>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-4 m_top5 pad_right0">
					<ul class="list-inline pull-left">
						<li><span class="color-label color-label-success"></span> 80% and above</li>
						<li><span class="color-label color-label-default"></span> 50% - 79%</li>
						<li><span class="color-label color-label-danger"></span> 0% - 49%&nbsp;&nbsp;&nbsp;</li>
						<!--<li><button type="button" class="btn btn-primary btn-xs" title="Webservice Details" id="getWebserviceDetailsId">WS Details</button> </li>-->
					</ul>
					<!--<div class="menu-top-selection">
						<i class="glyphicon glyphicon-cog menu-top-selection-icon"></i>
						<div class="arrow_box_top">
							<div class="row">
								<div id="navTabsMenuSelectionId"></div>
							</div>
						</div>
					</div>-->
				</div>
			</div>
		</div>
	</section>
</header>
<main>
	<section>
		<div class="container">
			<div class="row">
				<div id="overViewAbstract"></div>
				<div class="col-sm-12 m_top20">
					<div class="panel panel-default panel-black">
						<div class="panel-heading">
							<h4 class="panel-title">NTR Jalavani Overview</h4>
						</div>
						<div class="panel-body">
							<div id="overViewDataId"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
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
<script>
var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
$("#dateRangePickerMGNF").val('2017-04-01');
$("#dateRangePickerMGNT").val(moment().format("YYYY-MM")+'-30');
var glStartDate = '2017-04-01'//moment().startOf('year').format("YYYY-MM")+'-1';
var glEndDate = moment().format("YYYY-MM")+'-30';
onLoadCalls();
$("header").on("click",".menu-cls",function(e){
	e.stopPropagation();
	$(".menu-data-cls").toggle();
});
$(document).on("click",function(){
	$(".menu-data-cls").hide();
});
$("#dateRangePickerMGNF").datetimepicker({
	format: 'YYYY-MM',
	viewMode:'months'
});
$("#dateRangePickerMGNT").datetimepicker({
	format: 'YYYY-MM',
	viewMode:'months'
});
$('#dateRangePickerMGNF').on('dp.change', function(e){ 
	glStartDate = e.date.format("YYYY-MM")+"-31";
	onLoadCalls()
});
$('#dateRangePickerMGNT').on('dp.change', function(e){ 
	glEndDate = e.date.format("YYYY-MM")+"-31";
	onLoadCalls()
});

function onLoadCalls()
{
	getNtrJalaSiriAbstract();
	getNtrJalaSiriOverview();
}
function getNtrJalaSiriAbstract()
{
	$("#overViewAbstract").html(spinner);
	var json = {
		year : "2017",
		fromDate : glStartDate,
		toDate : glEndDate,
		locationType: "state"
	}
	$.ajax({
		url: 'getNtrJalaSiriAbstract',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
		  xhr.setRequestHeader("Accept", "application/json");
		  xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			var str='';
			str+='<div class="col-sm-4">';
				str+='<div class="text-center">';
					str+='<div class="panel-black-white panel-block-white-high text-center">';
						str+='<h4 class="panel-block-white-title text-capitalize text-center">NTR Jala Siri</h4>';
						str+='<small class="text-center">Achieved</small>';
						str+='<h1 class="text-center">'+ajaxresp.percentage+'<small>%</small><small><i class="fa fa-long-arrow-up"></i></small></h1>';
						str+='<div class="row">';
							str+='<div class="col-sm-6 text-center">';
								str+='<label>Target</label>';
								str+='<h4>'+ajaxresp.target+'</h4>';
							str+='</div>';
							str+='<div class="col-sm-6 text-center">';
								str+='<label>Completed</label>';
								str+='<h4>'+ajaxresp.completed+'</h4>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
			$("#overViewAbstract").html(str);
		}
	});
}

function getNtrJalaSiriOverview()
{
	$("#overViewDataId").html(spinner);
	var json = {
		year : "2017",
		fromDate : glStartDate,
		toDate : glEndDate
	}
	$.ajax({
		url: 'getNtrJalaSiriOverview',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
		  xhr.setRequestHeader("Accept", "application/json");
		  xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			console.log(ajaxresp);
			var str1='';
			str1+='<div class="table-responsive">';
				str1+='<table class="table table-bordered" >';
					str1+='<tbody>';
						str1+='<tr>';
							str1+='<td>';
								str1+='<p>Average Per District : '+ajaxresp.averagePerDistrict+'</p>';
							str1+='</td>';
							str1+='<td>';
								str1+='<p>Total Avg NTR Jalavani in District : '+ajaxresp.totalAvgFarmsInDistrict+'</p>';
							str1+='</td>';
						str1+='</tr>';
						str1+='<tr>';
							str1+='<td>';
								str1+='<p>Average Per Constituency : '+ajaxresp.averagePerConstituency+'</p>';
							str1+='</td>';
							str1+='<td>';
								str1+='<p>Total Avg NTR Jalavani in Constituency : '+ajaxresp.totalAvgFarmsInConstituency+'</p>';
							str1+='</td>';
						str1+='</tr>';
						str1+='<tr>';
							str1+='<td>';
								str1+='<p>Average Per Mandal : '+ajaxresp.averagePerMandal+'</p>';
							str1+='</td>';
							str1+='<td>';
								str1+='<p>Total Avg NTR Jalavani in Mandal: '+ajaxresp.totalAvgFarmsInMandal+'</p>';
							str1+='</td>';
						str1+='</tr>';
						str1+='<tr>';
							str1+='<td>';
								str1+='<p>Total Budget</p>';
								str1+='<h4>'+ajaxresp.totalBudget+'</h4>';
							str1+='</td>';
							str1+='<td>';
								str1+='<p>Total NTR Jalavani</p>';
								str1+='<h4>'+ajaxresp.totalBudget+'</h4>';
							str1+='</td>';
						str1+='</tr>';
					str1+='</tbody>';
				str1+='</table>';
			str1+='</div>';
			str1+='<div class="table-responsive">';
				
			str1+='</div>';
			str1+='<div class="table-responsive">';
				str1+='<table class="table table-bordered m_top10" >';
					str1+='<tbody>';
						str1+='<tr>';
							str1+='<td>';
								str1+='<div class="col-sm-9">';
									str1+='<div class="media">';
										str1+='<h4  attr_type="total">Total Districts : '+ajaxresp.totalDistricts+'</h4>';
									str1+='</div>';
									str1+='<div class="media">';
										str1+='<p class="media-left"><img src="Assests/icons/Red.png"></p>';
										if(ajaxresp.districtsInRed != null && ajaxresp.districtsInRed > 0)
											str1+='<p class="media-body detailsCls">Districts in Red : '+ajaxresp.districtsInRed+'</p>';
										else
											str1+='<p class="media-body">Districts in Red : '+ajaxresp.districtsInRed+'</p>';
									str1+='</div>';
									str1+='<div class="media">';
										str1+='<p class="media-left"><img src="Assests/icons/Orange.png"></p>';
										if(ajaxresp.districtsInOrange != null && ajaxresp.districtsInOrange > 0)
											str1+='<p class="media-body detailsCls">Districts in Orange : '+ajaxresp.districtsInOrange+'</p>';
										else
											str1+='<p class="media-body"  >Districts in Orange : '+ajaxresp.districtsInOrange+'</p>';
									str1+='</div>';
									str1+='<div class="media">';
										str1+='<p class="media-left"><img src="Assests/icons/Green.png"></p>';
										if(ajaxresp.districtsInGreen != null && ajaxresp.districtsInGreen > 0)
											str1+='<p class="media-body detailsCls"    >Districts in Green : '+ajaxresp.districtsInGreen+'</p>';
										else
											str1+='<p class="media-body"  >Districts in Green : '+ajaxresp.districtsInGreen+'</p>';
									str1+='</div>';
								str1+='</div>';
							str1+='</td>';
							str1+='<td>';
								str1+='<div class="col-sm-9">';
									str1+='<div class="media">';
										str1+='<h4  attr_type="total">Total Constituencies : '+ajaxresp.totalConstituencies+'</h4>';
									str1+='</div>';
									str1+='<div class="media">';
									str1+='<p class="media-left"><img src="Assests/icons/Red.png"></p>';
									if(ajaxresp.constituenciesInRed != null && ajaxresp.constituenciesInRed > 0)
										str1+='<p class="media-body detailsCls">Constituencies in Red : '+ajaxresp.constituenciesInRed+'</p>';
									else
										str1+='<p class="media-body"  >Constituencies in Red : '+ajaxresp.constituenciesInRed+'</p>';
										str1+='</div>';
									str1+='<div class="media">';
									str1+='<p class="media-left"><img src="Assests/icons/Orange.png"></p>';
									if(ajaxresp.constituenciesInOrange != null && ajaxresp.constituenciesInOrange > 0)
										str1+='<p class="media-body detailsCls">Constituencies in Orange : '+ajaxresp.constituenciesInOrange+'</p>';
									else
										str1+='<p class="media-body"  >Constituencies in Orange : '+ajaxresp.constituenciesInOrange+'</p>';
										str1+='</div>';
									str1+='<div class="media">';
										str1+='<p class="media-left"><img src="Assests/icons/Green.png"></p>';
										if(ajaxresp.constituenciesInGreen != null && ajaxresp.constituenciesInGreen > 0)
											str1+='<p class="media-body detailsCls">Constituencies in Green : '+ajaxresp.constituenciesInGreen+'</p>';
										else
											str1+='<p class="media-body">Constituencies in Green : '+ajaxresp.constituenciesInGreen+'</p>';
									str1+='</div>';
								str1+='</div>';	
							str1+='</td>';
							str1+='<td>';
								str1+='<div class="col-sm-9">';
									str1+='<div class="media">';
										str1+='<h4  attr_type="total">Total Constituencies : '+ajaxresp.totalConstituencies+'</h4>';
									str1+='</div>';
									str1+='<div class="media">';
									str1+='<p class="media-left"><img src="Assests/icons/Red.png"></p>';
									if(ajaxresp.mandalsInRed != null && ajaxresp.mandalsInRed > 0)
										str1+='<p class="media-body detailsCls">Constituencies in Red : '+ajaxresp.mandalsInRed+'</p>';
									else
										str1+='<p class="media-body"  >Constituencies in Red : '+ajaxresp.mandalsInRed+'</p>';
										str1+='</div>';
									str1+='<div class="media">';
									str1+='<p class="media-left"><img src="Assests/icons/Orange.png"></p>';
									if(ajaxresp.mandalsInOrange != null && ajaxresp.mandalsInOrange > 0)
										str1+='<p class="media-body detailsCls">Constituencies in Orange : '+ajaxresp.mandalsInOrange+'</p>';
									else
										str1+='<p class="media-body"  >Constituencies in Orange : '+ajaxresp.mandalsInOrange+'</p>';
										str1+='</div>';
									str1+='<div class="media">';
										str1+='<p class="media-left"><img src="Assests/icons/Green.png"></p>';
										if(ajaxresp.mandalsInGreen != null && ajaxresp.mandalsInGreen > 0)
											str1+='<p class="media-body detailsCls">Constituencies in Green : '+ajaxresp.mandalsInGreen+'</p>';
										else
											str1+='<p class="media-body">Constituencies in Green : '+ajaxresp.mandalsInGreen+'</p>';
									str1+='</div>';
								str1+='</div>';	
							str1+='</td>';
							
						str1+='</tr>';
					str1+='</tbody>';
				str1+='</table>';
			str1+='</div>';
			$("#overViewDataId").html(str1);
		}
	});
}
getNtrJalaSiriLvlWiseData();
function getNtrJalaSiriLvlWiseData()
{
	
	var json = {
		year : "2017",
		fromDate : glStartDate,
		toDate : glEndDate,
		locationType : "state"
	}
	$.ajax({
		url: 'getNtrJalaSiriLvlWiseData',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
		  xhr.setRequestHeader("Accept", "application/json");
		  xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			
		}
	});
}

</script>
</body>
</html>