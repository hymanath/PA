<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>MGNREGS IHHL</title>
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
					<h4 class="text-capital">Panchayat Raj & RD & RWS</h4>
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
	<section>
		<div class="container-fluid">
			<div class="row m_top10">
				<div class="col-sm-12">
					<div class="panel panel-black panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">MGNREGS - IHHL</h4>
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-sm-4">
									<div class="chart" id="swatchBharatDivId"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-12 m_top20">
					<div id="sbDataDivId">
						<div id="overViewBlockId"></div>
						<div id="levelWiseSwatchBharatId"></div>
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
<!--<script src="Assests/ruralWaterSupply/ruralWaterSupplyDashBoard.js" type="text/javascript"></script>-->
</body>
</html>

<script>
var spinner = '<div class="row"><div class="col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var levelWiseSBArr = ['state','district','constituencies','mandal','panchayat'];
var globalStatusObj={"QA":"#494949","PC":"#FC5049","FC":"#14BAAD","Ground":"#14BAAD","Surface":"#FC5049","SAFE":"#14BAAD","UN-SAFE":"#FC5049",
		"SINGAL VILLAGE":"#14BAAD","MULTI VILLAGE":"#FC5049","physicalTestCount":"#14BAAD","bacterialTestCount":"#FC5049",
		"Completely Satisfied":"#0FBE08","Not Satisfied":"#FF0909","Partially Satisfied":"#FFBA00","SATISFIED":"#0FBE08","PARTIALLY SATISFIED":"#FFBA00","NOT SATISFIED":"#FF0909","TARGET":"#FC5049","COMPLETED":"#14BAAD"}
getIHHLOverviewData("abstract");
overviewData("IHHL");
levelWiseSBData("IHHL");
$("header").on("click",".menu-cls",function(e){
	e.stopPropagation();
	$(".menu-data-cls").toggle();
});
$(document).on("click",function(){
	$(".menu-data-cls").hide();
});
function highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip,colors,title)
{
	'use strict';
	$('#'+id).highcharts({
		colors: colors,
		chart: type,
		title: title,
		subtitle: {
			text: null
		},
		xAxis: xAxis,
		yAxis: yAxis,
		tooltip: tooltip,
		plotOptions: plotOptions,
		legend: legend,
		series: data
	});
}
function getIHHLOverviewData(type){
	if(type == "abstract"){
		$("#swatchBharatDivId").html(spinner);
	}else{
		$("#swatchBharatOvervwIHHL").html(spinner);
	}
	
	var json = {
			fromMonth:"201704",
			toMonth:"201707",
			location:"state",
			locationId:"01"
			}
	
	$.ajax({                
		type:'POST',    
		url: 'getIHHLOverviewData',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null){
			if(type == "abstract"){
				buildIHHLOverviewDataAbstractOverView(result);
			}else{
				
				builOverViewBlock(result,"IHHL");
			}
			
		}else{
			if(type == "abstract"){
				$("#swatchBharatDivId").html("NO DATA AVAILABLE");
			}else{
				$("#swatchBharatOvervwIHHL").html("NO DATA AVAILABLE");
			}
			
		}
	});
}

function buildIHHLOverviewDataAbstractOverView(result){
	if(result.subList1 !=null && result.subList1.length>0){
			var dataArr=[];
			var totalCount=0;
			var ComPerc=0;
			for(var i in result.subList1){
				dataArr.push(result.subList1[i].target)
				dataArr.push(result.subList1[i].completed)
				totalCount =result.subList1[i].target+result.subList1[i].completed;
				ComPerc =result.subList1[i].completed*100/result.subList1[i].target;
			}
		var colors = ['#FC5049','#14BAAD']
		var id = 'swatchBharatDivId';
		var type = {
			type: 'column',
			backgroundColor:'transparent'
		};
		var title = { text: ''
			/* text: 'Habitation',
			align:'left',
			 style: {
				 color: '#000',
				 font: 'bold 16px "Lato", sans-serif'
			  } */
		};
	
		var legend = {
			enabled: false
		};
		var yAxis = {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			title: {
				text: null
			},
		};
		var xAxis = {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			categories: ['TARGET','COMPLETED'],
			labels: {
				useHTML:true,
				formatter: function() {
					return '<p><span class="roundClr" style="background-color:'+globalStatusObj[this.value]+'"></span>&nbsp;&nbsp;&nbsp;'+this.value+'</p>';
					
				},
				
			}
			
		};
		
		var plotOptions ={ column: {
				colorByPoint: true
			}};
		var tooltip = {
			useHTML:true,
			formatter: function () {
					if(this.x != "TARGET"){
						var pcnt = ComPerc;
						return '<b>' + this.x + '</b><br/>' +
						this.y+"-"+((Highcharts.numberFormat(pcnt)))+'%';
					}else{
						return '<b>' + this.x + '</b><br/>' +
						this.y+'';
					}
				}
		};

		var data = [{
			name: '',
			data: dataArr,

			dataLabels: {
				enabled: true,
				color: '#000',
				align: 'center',
				formatter: function() {
					if(this.x != "TARGET"){
						var pcnt = ComPerc;
						return '<span>'+this.y+'<br>('+Highcharts.numberFormat(pcnt)+'%)</span>';
					}else{
						return '<span>'+this.y+'';
					}
				}
			}
		}];
		highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip,colors,title);
	}
}
	
$(document).on("click",".overViewDtlsSwatchBharatCls",function(){
	//$("#sbModalDivId").modal('show');
	var str='';
	str+='<div id="overViewBlockId"></div>';
	str+='<div id="levelWiseSwatchBharatId"></div>';
	
	$("#sbDataDivId").html(str);
	//$("#sbmIhhlBlockId").html(str);
	
	overviewData("IHHL");
	levelWiseSBData("IHHL");
	/* $('html,body').animate({
		scrollTop: $("#overViewBlockId").offset().top},
	'slow'); */
});
	
function overviewData(divId){
	var collapse = '';
	collapse+='<section>';
		collapse+='<div class="row">';
			collapse+='<div class="col-sm-12">';
				collapse+='<div class="panel-group" id="accordion'+divId.replace(/\s+/g, '')+'" role="tablist" aria-multiselectable="true">';
					collapse+='<div class="panel panel-default panel-black">';
						collapse+='<div class="panel-heading" role="tab" id="heading'+divId+'">';
							collapse+='<a role="button" class="panelCollapseIcon" data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+'" href="#collapse'+divId.replace(/\s+/g, '')+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+'">';
							collapse+='<h4 class="panel-title text-capital">MGNREGS '+divId+' overview</h4>';
							collapse+='</a>';
						collapse+='</div>';
						collapse+='<div id="collapse'+divId.replace(/\s+/g, '')+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+divId.replace(/\s+/g, '')+'">';
							collapse+='<div class="panel-body">';
								collapse+='<div id="swatchBharatOvervw'+divId.replace(/\s+/g, '')+'"></div>';
							collapse+='</div>';
						collapse+='</div>';
					collapse+='</div>';
				collapse+='</div>';
			collapse+='</div>';
		collapse+='</div>';
	collapse+='</section>';
	$("#overViewBlockId").html(collapse);
	
	getIHHLOverviewData("overview")
	
}
	
function builOverViewBlock(result,divId){
	var $windowWidth = $(window).width();
	if(result.subList2 !=null && result.subList2.length>0){
		var str1="";
		str1+='<div class="table-responsive">';
			str1+='<table class="table table-bordered m_top10" >';
				str1+='<tbody>';
				str1+='<tr>';
				for(var i in result.subList2){
						str1+='<td>';
							str1+='<div class="col-sm-12">';
							if(result.subList2[i].type == "District"){
								str1+='<h4 ><strong>Total Districts : '+result.subList2[i].total+'</strong></h4>';
							}else if(result.subList2[i].type == "Constituencies"){
								str1+='<h4 ><strong>Total Constituencies : '+result.subList2[i].total+'</strong></h4>';
							}else if(result.subList2[i].type == "Mandal"){
								str1+='<h4 ><strong>Total Mandal : '+result.subList2[i].total+'</strong></h4>';
							}else if(result.subList2[i].type == "Panchayat"){
								str1+='<h4 ><strong>Total Panchayat : '+result.subList2[i].total+'</strong></h4>';
							}
								
							str1+='</div>';
							str1+='<div class="col-sm-12 m_top10">';
								str1+='<div class="media">';
									str1+='<p class="media-left"><img src="Assests/icons/Red.png"></p>';
									if(result.subList2[i].red != null && result.subList2[i].red > 0){
										if(result.subList2[i].type == "District"){
											str1+='<p class="media-body" >Districts in Red : '+result.subList2[i].red+'</p>';
										}else if(result.subList2[i].type == "Constituencies"){
											str1+='<p class="media-body" >Constituencies in Red : '+result.subList2[i].red+'</p>';
										}else if(result.subList2[i].type == "Mandal"){
											str1+='<p class="media-body" >Mandal in Red : '+result.subList2[i].red+'</p>';
										}else if(result.subList2[i].type == "Panchayat"){
											str1+='<p class="media-body" >Panchayat in Red : '+result.subList2[i].red+'</p>';
										}
										
									}else{
										if(result.subList2[i].type == "District"){
											str1+='<p>Districts in Red : - </p>';
										}else if(result.subList2[i].type == "Constituencies"){
											str1+='<p>Constituencies in Red : - </p>';
										}else if(result.subList2[i].type == "Mandal"){
											str1+='<p>Mandal in Red : - </p>';
										}else if(result.subList2[i].type == "Panchayat"){
											str1+='<p>Panchayat in Red : - </p>';
										}
										
									}
								str1+='</div>';
								str1+='<div class="media">';
									str1+='<p class="media-left"><img src="Assests/icons/Orange.png"></p>';
									if(result.subList2[i].orange != null && result.subList2[i].orange > 0){
										if(result.subList2[i].type == "District"){
											str1+='<p class="media-body"  >Districts in Orange : '+result.subList2[i].orange+'</p>';
										}else if(result.subList2[i].type == "Constituencies"){
											str1+='<p class="media-body"  >Constituencies in Orange : '+result.subList2[i].orange+'</p>';
										}else if(result.subList2[i].type == "Mandal"){
											str1+='<p class="media-body" >Mandal in Orange : '+result.subList2[i].orange+'</p>';
										}else if(result.subList2[i].type == "Panchayat"){
											str1+='<p class="media-body" >Panchayat in Orange : '+result.subList2[i].orange+'</p>';
										}
									}else{
										if(result.subList2[i].type == "District"){
											str1+='<p>Districts in Red : - </p>';
										}else if(result.subList2[i].type == "Constituencies"){
											str1+='<p>Constituencies in Red : - </p>';
										}else if(result.subList2[i].type == "Mandal"){
											str1+='<p>Mandal in Red : - </p>';
										}else if(result.subList2[i].type == "Panchayat"){
											str1+='<p>Panchayat in Red : - </p>';
										}
									}
								str1+='</div>';
								str1+='<div class="media">';
									str1+='<p class="media-left"><img src="Assests/icons/Green.png"></p>';
									if(result.subList2[i].green != null && result.subList2[i].green > 0){
										if(result.subList2[i].type == "District"){
											str1+='<p class="media-body" >Districts in Green : '+result.subList2[i].green+'</p>';
										}else if(result.subList2[i].type == "Constituencies"){
											str1+='<p class="media-body" >Constituencies in Green : '+result.subList2[i].green+'</p>';
										}else if(result.subList2[i].type == "Mandal"){
											str1+='<p class="media-body" >Mandal in Green : '+result.subList2[i].green+'</p>';
										}else if(result.subList2[i].type == "Panchayat"){
											str1+='<p class="media-body" >Panchayat in Green : '+result.subList2[i].green+'</p>';
										}
									}else{
										if(result.subList2[i].type == "District"){
											str1+='<p>Districts in Red : - </p>';
										}else if(result.subList2[i].type == "Constituencies"){
											str1+='<p>Constituencies in Red : - </p>';
										}else if(result.subList2[i].type == "Mandal"){
											str1+='<p>Mandal in Red : - </p>';
										}else if(result.subList2[i].type == "Panchayat"){
											str1+='<p>Panchayat in Red : - </p>';
										}
									}
								str1+='</div>';
							str1+='</div>';
						str1+='</td>';
				}
				str1+='</tr>';
				str1+='</tbody>';
			str1+='</table>';
		str1+='</div>';	
		$("#swatchBharatOvervw"+divId.replace(/\s+/g, '')).html(str1);	
		
	}
}
function levelWiseSBData(divId)
{
	var collapse='';
		collapse+='<section>';
			collapse+='<div class="row">';
			collapse+='<div class="col-sm-12">';
				for(var i in levelWiseSBArr)
				{
					collapse+='<div class="panel-group" id="accordion'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" role="tablist" aria-multiselectable="true">';
						collapse+='<div class="panel panel-default panel-black">';
							collapse+='<div class="panel-heading" role="tab" id="heading'+divId+''+levelWiseSBArr[i]+'">';
								if(i == 0)
								{
									collapse+='<a role="button" class="panelCollapseIcon '+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'"  data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" href="#collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'">';
								}else{
									collapse+='<a role="button" class="panelCollapseIcon collapsed '+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'"  data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" href="#collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'">';
								}
								if(levelWiseSBArr[i] == "state" || levelWiseSBArr[i] == "district" || levelWiseSBArr[i] == "constituency")
									collapse+='<h4 class="panel-title text-capital">'+levelWiseSBArr[i]+' level overview - (SBM- '+divId+')</h4>';
								else
									collapse+='<h4 class="panel-title text-capital">'+levelWiseSBArr[i]+' level overview - (SBM- '+divId+')</h4>';
									
								collapse+='</a>';
							collapse+='</div>';
							if(i == 0)
							{
								collapse+='<div id="collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'">';
							}else{
								collapse+='<div id="collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'">';
							}
							
								collapse+='<div class="panel-body">';
									collapse+='<div id="'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'"></div>';
								collapse+='</div>';
							collapse+='</div>';
						collapse+='</div>';
					collapse+='</div>';
				}
			collapse+='</div>';
			collapse+='</div>';
			collapse+='</section>';
	
	if(divId == "IHHL"){
		$("#levelWiseSwatchBharatId").html(collapse);
	}else if(divId == "Payments"){
		$("#levelWiseSwatchBharatPaymentsId").html(collapse);
	}	
	
	
	setTimeout(function(){ 
		for(var i in levelWiseSBArr){
			if(levelWiseSBArr[i] == "state"){
				if(divId == "IHHL"){
					getIHHLlocationLvlWiseData("state")
				}else if(divId == "Payments"){
					getSBPaymentsLvlWiseData("state")
				}
				
			}else if(levelWiseSBArr[i] == "district"){
				if(divId == "IHHL"){
					getIHHLlocationLvlWiseData("district")
				}else if(divId == "Payments"){
					getSBPaymentsLvlWiseData("district")
				}
				
			}else if(levelWiseSBArr[i] == "constituencies"){
				if(divId == "IHHL"){
					getIHHLlocationLvlWiseData("constituencies")
				}else if(divId == "Payments"){
					getSBPaymentsLvlWiseData("constituency")
				}
				
			}
		}	
	
	}, 1500);
	
}
$(document).on("click",".IHHLmandal",function(){
		getIHHLlocationLvlWiseData("mandal")
});
$(document).on("click",".IHHLpanchayat",function(){
	getIHHLlocationLvlWiseData("panchayat")
});	
$(document).on("click",".Paymentsmandal",function(){
		getSBPaymentsLvlWiseData("mandal")
});
$(document).on("click",".Paymentspanchayat",function(){
	getSBPaymentsLvlWiseData("panchayat")
});	
function getIHHLlocationLvlWiseData(locationType){
	$("#IHHL"+locationType).html(spinner);
	
	var json = {
		fromMonth:"201704",
		toMonth:"201707",
		location:"state",
		locationId:"01",
		subLocation :locationType
			
	}
	$.ajax({                
		type:'POST',    
		url: 'getIHHLlocationLvlWiseData',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildIHHLlocationLvlWiseData(result,locationType);
		}
	});
}
	
function buildIHHLlocationLvlWiseData(ajaxresp,locationType){
	if(ajaxresp != null && ajaxresp.length > 0){
		var str = '';
		str+='<div class="table-responsive">';
			str+='<table class="table table-bordered dataTable'+locationType+'">';
				str+='<thead class="text-capital">';
					if(locationType == "state"){
						str+='<th class="text-capital">'+locationType+'</th>';
					}
					else if(locationType == "district"){
						str+='<th class="text-capital">district</th>';
					}
					else if(locationType == "constituencies"){
						str+='<th class="text-capital">district</th>';
						str+='<th class="text-capital">constituency</th>';
					}
					else if(locationType == "mandal"){
						str+='<th class="text-capital">district</th>';
						str+='<th class="text-capital">constituency</th>';
						str+='<th class="text-capital">mandal</th>';
					}
					else if(locationType == "panchayat"){
						str+='<th class="text-capital">district</th>';
						str+='<th class="text-capital">constituency</th>';
						str+='<th class="text-capital">mandal</th>';
						str+='<th class="text-capital">panchayat</th>';
					}
					str+='<th class="text-capital">TARGET</th>';
					str+='<th class="text-capital">Grounded</th>';
					str+='<th class="text-capital">Not-Grounded</th>';
					//str+='<th class="text-capital">In Progress</th>';
					str+='<th class="text-capital">Completed</th>';
					str+='<th class="text-capital">Achivement Percentage</th>';
					
				str+='</thead>';
				str+='<tbody>';
					for(var i in ajaxresp){
						str+='<tr>';
							if(locationType == "state"){
								str+='<td class="text-capital">'+locationType+'</td>';
							}
							else if(locationType == "district"){
								str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
							}
							else if(locationType == "constituencies"){
								str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
								str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
							}
							else if(locationType == "mandal"){
								str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
								str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
								str+='<td class="text-capital">'+ajaxresp[i].mandal+'</td>';
							}
							else if(locationType == "panchayat"){
								str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
								str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
								str+='<td class="text-capital">'+ajaxresp[i].mandal+'</td>';
								str+='<td class="text-capital">'+ajaxresp[i].panchayt+'</td>';
							}
							str+='<td class="text-capital">'+ajaxresp[i].target+'</td>';										
							str+='<td class="text-capital">'+ajaxresp[i].grounded+'</td>';										
							str+='<td class="text-capital">'+ajaxresp[i].noTGrounded+'</td>';										
							//str+='<td class="text-capital">'+ajaxresp[i].completed+'</td>';										
							str+='<td class="text-capital">'+ajaxresp[i].completed+'</td>';	
							
							if(ajaxresp[i].percentage >= 100){
								str+='<td style="background-color:#f7b519;color:#fff">'+ajaxresp[i].percentage+'</td>';
							}else if(ajaxresp[i].percentage >= 90 && ajaxresp[i].percentage < 100){
								str+='<td style="background-color:#00AF50;color:#fff">'+ajaxresp[i].percentage+'</td>';
							}else if(ajaxresp[i].percentage >= 60 && ajaxresp[i].percentage < 90){
								str+='<td style="background-color:#ff6600;color:#fff">'+ajaxresp[i].percentage+'</td>';
							}else{
								str+='<td style="background-color:#FF0000;color:#fff">'+ajaxresp[i].percentage+'</td>';
							}
							/* 
							if(ajaxresp[i].percentage >=80){
								str+='<td class="text-capital" style="background-color:#00AF50">'+ajaxresp[i].percentage+'</td>';
							}else if(ajaxresp[i].percentage >= 50 && ajaxresp[i].percentage < 80){
									str+='<td style="background-color:#FFBA00">'+ajaxresp[i].percentage+'</td>';
							}else if(ajaxresp[i].percentage <50){
								str+='<td class="text-capital" style="background-color:#FF0000">'+ajaxresp[i].percentage+'</td>';
							} */
						str+='</tr>';
					}
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
		$("#IHHL"+locationType).html(str);
		
		if(locationType !="state" || locationType !="district"){
			$(".dataTable"+locationType).dataTable({
				"iDisplayLength": 20,
				"aaSorting": [],
				"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
				"dom": "<'row'<'col-sm-4'l><'col-sm-7'f><'col-sm-1'B>>" +
					"<'row'<'col-sm-12'tr>>" +
					"<'row'<'col-sm-5'i><'col-sm-7'p>>",
				buttons: [
					{
						extend:    'csvHtml5',
						text:      '<i class="fa fa-file-text-o"></i>',
						titleAttr: 'CSV',
						title:	   locationType,
						filename:  locationType+''+moment().format("DD/MMMM/YYYY  HH:MM"),
					},
					{
						extend:    'pdfHtml5',
						text:      '<i class="fa fa-file-pdf-o"></i>',
						titleAttr: 'PDF',
						title:	   locationType,
						filename:  locationType+''+moment().format("DD/MMMM/YYYY  HH:MM"),
						orientation: "landscape",
						pageSize:'A3',
						customize: function (doc) {
							doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
						}
					}
				]
			});
		}
	}
}
</script>