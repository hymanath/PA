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
													<h3>MGNREGS</h3>
													<p>Mahatma Gandhi Rural employement guarantee scheme</p>
													<div class="row">
														<div class="col-sm-6 m_top10">
															<div class="menu-block" style="background-color:#FFBA00">
																<a href="MGNREGSDashboard">
																	<p>Dashboard</p>
																</a>
															</div>
														</div>
														<div class="col-sm-6 m_top10">
															<div class="menu-block" style="background-color:#56A3C5">
																<a href="NregaConsolidatedDashboard">
																	<p>Consolidated</p>
																</a>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="col-sm-12 m_top10">
												<div class="menu-block" style="background-color:#ff1c5e">
													<a href="RuralDevelopmentDashboard">
														<h3>IWMP</h3>
														<p>Integrated Watershed Management Programme</p>
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
				<div class="col-sm-12">
					<div class="panel panel-default panel-black">
						<div class="panel-heading">
							<h4 class="panel-title text-capital">NTR Jalasiri</h4>
						</div>
						<div class="panel-body">
							<div id="overViewAbstract"></div>
						</div>
					</div>
				</div>
				
				<div class="col-sm-12 m_top20">
					<div class="panel panel-default panel-black">
						<div class="panel-heading">
							<h4 class="panel-title text-capital">NTR Jalasiri Overview</h4>
						</div>
						<div class="panel-body">
							<div id="overViewDataId"></div>
						</div>
					</div>
				</div>
				<div class="col-sm-12 m_top20">
					<div id="projectData"></div>
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
	projectData('NTR Jala Siri',2,'');
}
function getNtrJalaSiriAbstract()
{
	$("#overViewAbstract").html(spinner);
	var json = {
		year : "2017",
		fromDate : glStartDate,
		toDate : glEndDate,
		locationType : "district",
		locationId : "-1"
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
					if(ajaxresp.percentage < 50)
					{
						str+='<div class="panel-black-white panel-block-white-low text-center active">';
					}else if(ajaxresp.percentage >= 50 && ajaxresp.percentage < 80)
					{
						str+='<div class="panel-black-white panel-block-white-medium text-center active">';
						
					}else if(ajaxresp.percentage >= 80)
					{
						str+='<div class="panel-black-white panel-block-white-high text-center active">';
					}
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
								str1+='<p>Total Avg NTR Jalasiri in District : '+ajaxresp.totalAvgFarmsInDistrict+'</p>';
							str1+='</td>';
						str1+='</tr>';
						str1+='<tr>';
							str1+='<td>';
								str1+='<p>Average Per Constituency : '+ajaxresp.averagePerConstituency+'</p>';
							str1+='</td>';
							str1+='<td>';
								str1+='<p>Total Avg NTR Jalasiri in Constituency : '+ajaxresp.totalAvgFarmsInConstituency+'</p>';
							str1+='</td>';
						str1+='</tr>';
						str1+='<tr>';
							str1+='<td>';
								str1+='<p>Average Per Mandal : '+ajaxresp.averagePerMandal+'</p>';
							str1+='</td>';
							str1+='<td>';
								str1+='<p>Total Avg NTR Jalasiri in Mandal: '+ajaxresp.totalAvgFarmsInMandal+'</p>';
							str1+='</td>';
						str1+='</tr>';
						str1+='<tr>';
							str1+='<td>';
								str1+='<p>Total Budget</p>';
								str1+='<h4>'+ajaxresp.totalBudget+'</h4>';
							str1+='</td>';
							str1+='<td>';
								str1+='<p>Total NTR Jalasiri</p>';
								str1+='<h4>'+ajaxresp.totalBudget1+'</h4>';
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
										str1+='<h4  attr_type="total">Total Mandals : '+ajaxresp.totalMandals+'</h4>';
									str1+='</div>';
									str1+='<div class="media">';
									str1+='<p class="media-left"><img src="Assests/icons/Red.png"></p>';
									if(ajaxresp.mandalsInRed != null && ajaxresp.mandalsInRed > 0)
										str1+='<p class="media-body detailsCls">Mandals in Red : '+ajaxresp.mandalsInRed+'</p>';
									else
										str1+='<p class="media-body"  >Mandals in Red : '+ajaxresp.mandalsInRed+'</p>';
										str1+='</div>';
									str1+='<div class="media">';
									str1+='<p class="media-left"><img src="Assests/icons/Orange.png"></p>';
									if(ajaxresp.mandalsInOrange != null && ajaxresp.mandalsInOrange > 0)
										str1+='<p class="media-body detailsCls">Mandals in Orange : '+ajaxresp.mandalsInOrange+'</p>';
									else
										str1+='<p class="media-body"  >Mandals in Orange : '+ajaxresp.mandalsInOrange+'</p>';
										str1+='</div>';
									str1+='<div class="media">';
										str1+='<p class="media-left"><img src="Assests/icons/Green.png"></p>';
										if(ajaxresp.mandalsInGreen != null && ajaxresp.mandalsInGreen > 0)
											str1+='<p class="media-body detailsCls">Mandals in Green : '+ajaxresp.mandalsInGreen+'</p>';
										else
											str1+='<p class="media-body">Mandals in Green : '+ajaxresp.mandalsInGreen+'</p>';
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

function projectData(divId,levelId,locationId)
{
	//alert(locationId);
	var collapse='';
	var dataArr = '';
	if(levelId == 2)
	{
		dataArr = ['state','district','constituency','mandal'];
	}
	collapse+='<section>';
		collapse+='<div class="row">';
			collapse+='<div class="col-sm-12">';
				for(var i in dataArr)
				{
					collapse+='<div class="panel-group" id="accordion'+divId.replace(/\s+/g, '')+''+dataArr[i]+'" role="tablist" aria-multiselectable="true">';
						collapse+='<div class="panel panel-default panel-black">';
							collapse+='<div class="panel-heading" role="tab" id="heading'+divId+''+dataArr[i]+'">';
								if(i == 0)
								{
									collapse+='<a role="button" class="panelCollapseIcon" overview-levelId="'+levelId+'" overview-locationId="'+locationId+'" overview-divId="'+divId+'" overview-level="'+dataArr[i]+'" data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+''+dataArr[i]+'" href="#collapse'+divId.replace(/\s+/g, '')+''+dataArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+''+dataArr[i]+'">';
								}else{
									collapse+='<a role="button" class="panelCollapseIcon collapsed" overview-levelId="'+levelId+'" overview-locationId="'+locationId+'" overview-divId="'+divId+'" overview-level="'+dataArr[i]+'" data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+''+dataArr[i]+'" href="#collapse'+divId.replace(/\s+/g, '')+''+dataArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+''+dataArr[i]+'">';
								}
									if(divId == 'FAperformance')
									{
										collapse+='<h4 class="panel-title text-capital">'+dataArr[i]+' level overview - FA Performance</h4>';
									}else{
										collapse+='<h4 class="panel-title text-capital">'+dataArr[i]+' level overview - '+divId+'</h4>';
									}
									
								collapse+='</a>';
							collapse+='</div>';
							if(i == 0)
							{
								collapse+='<div id="collapse'+divId.replace(/\s+/g, '')+''+dataArr[i]+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+divId.replace(/\s+/g, '')+''+dataArr[i]+'">';
							}else{
								collapse+='<div id="collapse'+divId.replace(/\s+/g, '')+''+dataArr[i]+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+divId.replace(/\s+/g, '')+''+dataArr[i]+'">';
							}
							
								collapse+='<div class="panel-body">';
								if(divId == 'Payments')
								{
									collapse+='';
									collapse+='<label class="radio-inline">';
										collapse+='<input name="'+divId.replace(/\s+/g, '')+''+dataArr[i]+'" overview-locationId="'+locationId+'" attr_levelId="'+levelId+'" attr_locationType="'+dataArr[i]+'" attr_name="Material"  type="radio" attr_radioBtn="'+divId.replace(/\s+/g, '')+''+dataArr[i]+'"/> Material';
									collapse+='</label>';
									collapse+='<label class="radio-inline">';
										collapse+='<input name="'+divId.replace(/\s+/g, '')+''+dataArr[i]+'" overview-locationId="'+locationId+'" attr_levelId="'+levelId+'" attr_locationType="'+dataArr[i]+'" attr_name="Wage" type="radio" checked attr_radioBtn="'+divId.replace(/\s+/g, '')+''+dataArr[i]+'"/> Wage';
									collapse+='</label>';
									collapse+='<label class="radio-inline">';
										collapse+='<input name="'+divId.replace(/\s+/g, '')+''+dataArr[i]+'" overview-locationId="'+locationId+'" attr_levelId="'+levelId+'" attr_locationType="'+dataArr[i]+'" attr_name="Total" type="radio" attr_radioBtn="'+divId.replace(/\s+/g, '')+''+dataArr[i]+'"/> Total';
									collapse+='</label>';
									collapse+='<label class="radio-inline">';
										collapse+='<input name="'+divId.replace(/\s+/g, '')+''+dataArr[i]+'" overview-locationId="'+locationId+'" attr_levelId="'+levelId+'" attr_locationType="'+dataArr[i]+'" attr_name="All" type="radio" attr_radioBtn="'+divId.replace(/\s+/g, '')+''+dataArr[i]+'"/> All';
									collapse+='</label>';
								}
									collapse+='<div id="'+divId.replace(/\s+/g, '')+''+dataArr[i]+'"></div>';
								collapse+='</div>';
							collapse+='</div>';
						collapse+='</div>';
					collapse+='</div>';
				}
			collapse+='</div>';
		collapse+='</div>';
	collapse+='</section>';
	$("#projectData").html(collapse);
	
	for(var i in dataArr)
	{
		var theadArr = [dataArr[i],'Targeted Bore Wells ','Number of Bore Wells Drilled Succesfully','No of LT applications Filled','Demand Notices Received from TRANSCO','Beneficiaries Contribution Received (Nos)','Amount Paid to TRANSCO (Nos)','No of Solar Pumpsets Installed','percentage'];
		if(dataArr[i] == "constituency")
			theadArr = ["district",dataArr[i],'Targeted Bore Wells ','Number of Bore Wells Drilled Succesfully','No of LT applications Filled','Demand Notices Received from TRANSCO','Beneficiaries Contribution Received (Nos)','Amount Paid to TRANSCO (Nos)','No of Solar Pumpsets Installed','percentage'];
		else if(dataArr[i] == "mandal")
			theadArr = ["district","constituency",dataArr[i],'Targeted Bore Wells ','Number of Bore Wells Drilled Succesfully','No of LT applications Filled','Demand Notices Received from TRANSCO','Beneficiaries Contribution Received (Nos)','Amount Paid to TRANSCO (Nos)','No of Solar Pumpsets Installed','percentage'];
		else if(dataArr[i] == "panchayat")
			theadArr = ["district","constituency","mandal",dataArr[i],'Targeted Bore Wells ','Number of Bore Wells Drilled Succesfully','No of LT applications Filled','Demand Notices Received from TRANSCO','Beneficiaries Contribution Received (Nos)','Amount Paid to TRANSCO (Nos)','No of Solar Pumpsets Installed','percentage'];
		
		divId = 'NTRJalaSiri'+dataArr[i];
		getNtrJalaSiriLvlWiseData(dataArr[i],divId,theadArr);		
	}
	
	

}
function getNtrJalaSiriLvlWiseData(locationType,divId,theadArr)
{
	$("#"+divId).html(spinner);	
	var json = {
		year : "2017",
		fromDate : glStartDate,
		toDate : glEndDate,
		locationType : locationType
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
			tableView(divId,theadArr,ajaxresp,locationType);
		}
	});
}
function tableView(blockId,theadArr,result,locationType)
{
	var tableView='';
	tableView+='<div class="table-responsive">';
		tableView+='<table class="table table-bordered dataTable'+blockId+'">';
			tableView+='<thead class="text-capital">';
				for(var i in theadArr)
				{
					tableView+='<th>'+theadArr[i]+'</th>';
				}
			tableView+='</thead>';
			tableView+='<tbody>';
				if(result != null && result.length > 0){
					for(var i in result){
						tableView+='<tr>';
							if(locationType == "state"){
								tableView+='<td class="text-capital">'+locationType+'</td>';
							}
							else if(locationType == "district"){
								tableView+='<td class="text-capital">'+result[i].district+'</td>';
							}
							else if(locationType == "constituency"){
								tableView+='<td class="text-capital">'+result[i].district+'</td>';
								tableView+='<td class="text-capital">'+result[i].constituency+'</td>';
							}
							else if(locationType == "mandal"){
								tableView+='<td class="text-capital">'+result[i].district+'</td>';
								tableView+='<td class="text-capital">'+result[i].constituency+'</td>';
								tableView+='<td class="text-capital">'+result[i].mandal+'</td>';
							}
							else if(locationType == "panchayat"){
								tableView+='<td class="text-capital">'+result[i].district+'</td>';
								tableView+='<td class="text-capital">'+result[i].constituency+'</td>';
								tableView+='<td class="text-capital">'+result[i].mandal+'</td>';
								tableView+='<td class="text-capital">'+result[i].panchayat+'</td>';
							}	
							
							tableView+='<td>'+result[i].target+'</td>';
							tableView+='<td>'+result[i].borewellsDrilled+'</td>';
							tableView+='<td>'+result[i].ltFiles+'</td>';
							tableView+='<td>'+result[i].sentToTransco+'</td>';
							tableView+='<td>'+result[i].beneficaryContribution+'</td>';
							tableView+='<td>'+result[i].amountPaidTransco+'</td>';
							tableView+='<td>'+result[i].borewellenergisation+'</td>';
							if(result[i].percentage < 50)
							{
								tableView+='<td style="background-color:#FF0000">'+result[i].percentage+'</td>';
							}else if(result[i].percentage >= 50 && result[i].percentage < 80)
							{
								tableView+='<td style="background-color:#FFBA00">'+result[i].percentage+'</td>';
							}else if(result[i].percentage >= 80)
							{
								tableView+='<td style="background-color:#00AF50">'+result[i].percentage+'</td>';
							}
						tableView+='</tr>';
					}
				}
			tableView+='</tbody>';
		tableView+='</table>';
	tableView+='</div>';
	
	$("#"+blockId).html(tableView);	
	if(locationType == 'constituency' || locationType == 'mandal' || locationType == 'panchayat')
	{
		$(".dataTable"+blockId).dataTable({
			"iDisplayLength": 15,
			"aaSorting": [],
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
		});
	}else if(locationType == 'district')
	{
		$(".dataTable"+blockId).dataTable({
			"iDisplayLength": 20,
			"aaSorting": [],
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
		});
	}
}
//getRDAbstractDataByType();
function getRDAbstractDataByType()
{
	var json = {
			year : "2017",
			fromDate : "2017-04-01",
			toDate : "2017-07-30",
			type : "WaterBudget",
			locationType: "state",
			locationId : "-1"
		}
	
	$.ajax({
		url: 'getRDAbstractDataByType',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			//buildNREGSAbstractDataByTypeNew(type,ajaxresp,blockName,locId,locType,levelId);
		}
	}); 
}
//getRDOverview();
function getRDOverview()
{
	
		var json = {
			year : "2017",
			fromDate : "2017-04-01",
	        toDate : "2017-07-30",
			divType : "SMC Trench",
			locationType : "constituency",
			locationId : "01120"
		}
	
	
	$.ajax({
		url: 'getRDOverview',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
		  xhr.setRequestHeader("Accept", "application/json");
		  xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			//buildNregasOverViewBlock(ajaxresp,projectDivId,menuLocationType,menuLocationId);
		}
	});
}
getRDLevelsWiseData();
function getRDLevelsWiseData()
{
	//$("#"+divIdd).html(spinner);
	var json = {
		year : "2017",
		fromDate : "2017-04-01",
		toDate : "2017-07-30",
		locationType: "constituency",
		divType : "SMC Trench",
		locationId : "10293",
		sublocaType : "constituency"
	}
	$.ajax({
		url: 'getRDLevelsWiseData',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			/* var str = '';
			if(ajaxresp != null && ajaxresp.length > 0){
				for(var i in ajaxresp){
					str+='<tr>';
						if(locationTypeNew == "state"){
							str+='<td class="text-capital">'+locationTypeNew+'</td>';
						}
						else if(locationTypeNew == "district"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
						}
						else if(locationTypeNew == "constituency"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
						}
						else if(locationTypeNew == "mandal"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].mandal+'</td>';
						}
						else if(locationTypeNew == "panchayat"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].mandal+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].panchayat+'</td>';
						}
						str+='<td>'+ajaxresp[i].target+'</td>';
						if((globalDivName == 'Mulbery' || globalDivName == 'Silk worms' || globalDivName == 'Cattle drinking water troughs' || globalDivName == 'Raising of Perinnial Fodders') && locationTypeNew == "state"){
							str+='<td>'+ajaxresp[i].sanctionedTarget+'</td>';
							//str+='<td>'+ajaxresp[i].sanctionedPerventage+'</td>';
						}
						if((globalDivName == 'Fish Ponds' || globalDivName == 'Fish Drying Platforms' || globalDivName == 'Anganwadi Buildings' || globalDivName == 'SMC Trench' || globalDivName == 'Imp to CD' || globalDivName == 'MPT_PT' || globalDivName == 'GC Works' || globalDivName == 'CD_CW') && (locationTypeNew == "state" || locationTypeNew == "district")){
							str+='<td>'+ajaxresp[i].sanctionedTarget+'</td>';
							//str+='<td>'+ajaxresp[i].sanctionedPerventage+'</td>';
						}
						
						str+='<td>'+ajaxresp[i].grounded+'</td>';
						str+='<td>'+ajaxresp[i].notGrounded+'</td>';
						str+='<td>'+ajaxresp[i].inProgress+'</td>';
						str+='<td>'+ajaxresp[i].completed+'</td>';
						if(ajaxresp[i].percentage < 50){
							str+='<td style="background-color:#FF0000">'+ajaxresp[i].percentage+'</td>';
						}else if(ajaxresp[i].percentage >= 50 && ajaxresp[i].percentage < 80){
							str+='<td style="background-color:#FFBA00">'+ajaxresp[i].percentage+'</td>';
						}else if(ajaxresp[i].percentage >= 80){
							str+='<td style="background-color:#00AF50">'+ajaxresp[i].percentage+'</td>';
						}
						
						if((globalDivName == 'Mulbery' || globalDivName == 'Silk worms' || globalDivName == 'Cattle drinking water troughs' || globalDivName == 'Raising of Perinnial Fodders') && locationTypeNew == "state"){
							if(ajaxresp[i].percSant < 50){
								str+='<td style="background-color:#FF0000">'+ajaxresp[i].percSant+'</td>';
							}else if(ajaxresp[i].percSant >= 50 && ajaxresp[i].percSant < 80){
								str+='<td style="background-color:#FFBA00">'+ajaxresp[i].percSant+'</td>';
							}else if(ajaxresp[i].percSant >= 80){
								str+='<td style="background-color:#00AF50">'+ajaxresp[i].percSant+'</td>';
							}
						}
						if((globalDivName == 'Fish Ponds' || globalDivName == 'Fish Drying Platforms' || globalDivName == 'Anganwadi Buildings' || globalDivName == 'SMC Trench' || globalDivName == 'Imp to CD' || globalDivName == 'MPT_PT' || globalDivName == 'GC Works' || globalDivName == 'CD_CW') && (locationTypeNew == "state" || locationTypeNew == "district")){
							if(ajaxresp[i].percSant < 50){
								str+='<td style="background-color:#FF0000">'+ajaxresp[i].percSant+'</td>';
							}else if(ajaxresp[i].percSant >= 50 && ajaxresp[i].percSant < 80){
								str+='<td style="background-color:#FFBA00">'+ajaxresp[i].percSant+'</td>';
							}else if(ajaxresp[i].percSant >= 80){
								str+='<td style="background-color:#00AF50">'+ajaxresp[i].percSant+'</td>';
							}
						}
					str+='</tr>';
				}
			}*/

		} 
	});
}
</script>
</body>
</html>