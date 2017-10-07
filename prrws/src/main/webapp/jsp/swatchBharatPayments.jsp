<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Rural Water Supply Dashboard</title>
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
									<div class="menu-block" style="background-color:#56A3C5">
										<a href="ruralWaterSupplyDashBoard">
											<h3>RWS</h3>
											<p>Rural Water Supply</p>
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
														<p>Panchayat Raj Expenditure</p>
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
				<div class="col-sm-6">
					<div class="white-block">
						<h5 style="padding:5px;text-align:center;"><span class="chartTitleAlign overViewDtlsSwatchBharatPaymentCls" style="cursor:pointer;">Swatch Bharat - PAYMENTS</span></h5>
						<div class="chart" id="swatchBharatPaymentsDivId"></div>
					</div>
				</div>
			</div>
		</div>
	</section>
</main>

<div class="modal fade" id="sbPaymentModalDivId" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document" style="width: 95%;">
    <div class="modal-content modal-custom">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true" style="color:#fff">&times;</span></button>
        <h4 class="modal-title" id="">Swatch Bharat Payments</h4>
      </div>
      <div class="modal-body">
        <div id="sbPaymentDataDivId"></div>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

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
function getSBPaymentsAbstract(){
		
	$("#swatchBharatPaymentsDivId").html(spinner);
	var json = {
		//fromDate:"201704",
		//toDate:"201708",
		location:"state",
		locationId:"01",
		subLocation :"state"
			
	}
	$.ajax({                
		type:'POST',    
		url: 'getSBPaymentsAbstract',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null){
			buildSBPaymentsAbstract(result);
		}
	});
}
		
function buildSBPaymentsAbstract(result){
	
	var dataArr=[];
	
	dataArr.push({"y":result.ttlAmt,"extra":result.totalAmount});
	dataArr.push({"y":result.paidAmt,"extra":result.paidAmount});
	dataArr.push({"y":result.pndgAmt,"extra":result.pendingAmount});
	var colors = ['#14BBAE'];
	var id = 'swatchBharatPaymentsDivId';
		var type = {
			type: 'bar',
			backgroundColor:'transparent'
		};
		var legend = {
			enabled: false
		};
		
		var title = {
			text: '',
			align:'left',
			 style: {
				 color: '#777',
				 font: 'bold 8px "Lato", sans-serif'
			  } 
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
			categories: ["TOTAL","PAID","PENDING"]
			
		};
		var plotOptions ={ bar: {
				colorByPoint: true
			}};
		var tooltip = {
			useHTML:true,
			formatter: function () {
				//var pcnt = (this.y / totalCount) * 100;
				return '<b>' + this.x + ' Amount</b><br/>' +
					this.point.extra;
			}
		};

		var data = [{
			name: '',
			data: dataArr,

			dataLabels: {
				enabled: true,
				color: '#000',
				align: 'canter',
				formatter: function() {
						//var pcnt = (this.y / totalCount) * 100;
						//return '<span>'+this.y+'<br>('+Highcharts.numberFormat(pcnt)+'%)</span>';
						return '<span>'+this.point.extra+'</span>';
				} 
			}
		}];
	highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip,colors,title);
}
$(document).on("click",".overViewDtlsSwatchBharatPaymentCls",function(){
	$("#sbPaymentModalDivId").modal('show');
	var str='';
		str+='<div id="levelWiseSwatchBharatPaymentsId"></div>';
	$("#sbPaymentDataDivId").html(str);
	
	levelWiseSBData("Payments")
	//$('html,body').animate({
	//	scrollTop: $("#overViewBlockId").offset().top},
	//'slow');
});
	
function getSBPaymentsLvlWiseData(locationType){
	$("#Payments"+locationType).html(spinner);
	
	var json = {
		//fromDate:"201704",
		//toDate:"201708",
		location:"state",
		locationId:"01",
		subLocation :locationType
			
	}
	$.ajax({                
		type:'POST',    
		url: 'getSBPaymentsLevelsWiseData',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildSBPaymentsLvlWiseData(result,locationType);
		}
	});
}
	
	
function buildSBPaymentsLvlWiseData(ajaxresp,locationType){
	if(ajaxresp != null && ajaxresp.length > 0){
		var str = '';
		str+='<div class="table-responsive">';
			str+='<table class="table table-bordered dataTablePayments'+locationType+'">';
				str+='<thead class="text-capital">';
					if(locationType == "state"){
						str+='<th class="text-capital">'+locationType+'</th>';
					}
					else if(locationType == "district"){
						str+='<th class="text-capital">district</th>';
					}
					else if(locationType == "constituency"){
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
					str+='<th class="text-capital">TOTAL FTO</th>';
					str+='<th class="text-capital">Total Amount</th>';
					str+='<th class="text-capital">Paid Fto</th>';
					str+='<th class="text-capital">Paid Amount</th>';
					str+='<th class="text-capital">Pending Fto</th>';
					str+='<th class="text-capital">Pending AMount</th>';
					
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
							else if(locationType == "constituency"){
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
							str+='<td class="text-capital">'+ajaxresp[i].totalFTO+'</td>';										
							str+='<td class="text-capital">'+ajaxresp[i].totalAmount+'</td>';										
							str+='<td class="text-capital">'+ajaxresp[i].paidFTO+'</td>';										
							str+='<td class="text-capital">'+ajaxresp[i].paidAmount+'</td>';										
							str+='<td class="text-capital">'+ajaxresp[i].pendingFTO+'</td>';	
							str+='<td class="text-capital">'+ajaxresp[i].pendingAmount+'</td>';	
							
						str+='</tr>';
					}
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
		if(locationType == "constituency"){
			$("#Paymentsconstituencies").html(str);
		}else{
			$("#Payments"+locationType).html(str);
		}
		if(locationType !="state" || locationType !="district"){
			$(".dataTablePayments"+locationType).dataTable({
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
