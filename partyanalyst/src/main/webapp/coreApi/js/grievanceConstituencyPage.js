var grivanceColorObj={"APPROVED":"#2DCC70","COMPLETED":"#449C43","IN PROGRESS":"#FFB84F","NOT ELIGIBLE":"#C0392B","NOT POSSIBLE":"#EF8379","NOT VERIFIED":"#31708F"}
var insuranceColorObj={"Waiting For Documents":"#2DCC70","Documents Submitted In Party":"#449C43","Forwarded to Insurance":"#FFB84F","Closed at Insurance":"#8F43AF","Closed at Party":"#9B88B3","Approved - Compensated":"#2BCD72","Closed Letters":"#32708F","Account Rejected":"#65CBCC"}
var spinner = '<div class="row"><div class="col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var globalFromDate = moment().subtract(3,'year').format("DD/MM/YYYY");
var globalToDate = moment().format("DD/MM/YYYY");

setTimeout(function(){
	onloadCalls();
},1500)

function onloadCalls(){
	getGrivenceDetails();
	getConstituencyWiseInsurance(); //Insurance
	getLevelWiseGrievanceCounts(); 
	getLocationWiseNtrTrustDetails();
}

function highcharts(id,type,data,plotOptions,title,tooltip,legend){
	'use strict';
	$('#'+id).highcharts({
		colors:['#FF9900','#8D4553','#CCCCCC','#F25C81','#0D9615'],
		chart: type,
		title: title,
		tooltip:tooltip,
		subtitle: {
			text: null
		},
		plotOptions: plotOptions,
		legend:legend,
		series: data
	});
}

function getGrivenceDetails(){
		$("#grievanceOverViewId").html(spinner);
	var jsObj={
			"fromDate" 			:globalFromDate,
			"toDate"			:globalToDate,
			"locationTypeId" 	:locationLevelId,
			"locationValuesArr" :userAccessLevelValuesArray,
			"year": 3
		}
	$.ajax({
		type : "POST",
		url : "getGrivenceDetailsAction.action",
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			return buildGrivenceDetails(result);
		}
	});
	
	function buildGrivenceDetails(result){
		var str='';
		str+='<div class="row">';
			str+='<div class="col-sm-2">';
				str+='<p class="m_top10 text-capital text-center" id="totalGriDetailedCount"></p>';
				str+='<div id="grievancePieChartId" style="height:150px;"></div>';
			str+='</div>';
			str+='<div class="col-sm-2 m_top40">';
				//str+='<ul class="list-inline">';
					for(var i in result){
						if(result[i].grivenceType == "Grivence"){
							for(var j in result[i].subList){
								str+='<h5 class="f-12" style="color:'+grivanceColorObj[result[i].subList[j].name.trim()]+';line-height:25px;">'+result[i].subList[j].name+' <span class="pull-right">'+result[i].subList[j].count+'</span></h5>';
							}
						}
					}
				//str+='</ul>';
			str+='</div>';
			str+='<div class="col-sm-8">';
				for(var i in result){
					if(result[i].grivenceType != "Grivence"){
						str+='<div class="col-sm-4">';
						str+='<div class="block">';
						str+='<p class="m_top10 text-capital">'+result[i].grivenceType+'</p>';
						for(var j in result[i].subList){
								str+='<table class="table tableborderCss m_top10">';
									str+='<tr>';
										str+='<td class="">';
											if(result[i].subList[j].name.length > 0){
												str+='<p class="f-12">'+result[i].subList[j].name+'</p>';
											}else{
												
												str+='<p class="f-12">Others</p>';
											}
											
										str+='</td>';
										str+='<td class="text-right">';
											str+='<span>'+result[i].subList[j].count+'</span>';
										str+='</td>';
									str+='</tr>';
								str+='</table>';
						}
						str+='</div>';
						str+='</div>';
						
					}
					
				}
			str+='</div>';
			
		str+='<div>';
		$("#grievanceOverViewId").html(str);
		if(result !=null && result.length>0){
			var mainArr=[];
			var totalGrievanceCount = 0;
			for(var i in result){
				if(result[i].grivenceType == "Grivence"){
					for(var j in result[i].subList){
						var colorsId = grivanceColorObj[result[i].subList[j].name.trim()];
						var obj = {
							name: result[i].subList[j].name,
							y:result[i].subList[j].count,
							color:colorsId
						}
						mainArr.push(obj);
						totalGrievanceCount = totalGrievanceCount + result[i].subList[j].count;
					}
					$("#totalGriDetailedCount").html('<b>Total Count - '+totalGrievanceCount+'</b>');
				}
					
				
				var id = 'grievancePieChartId';
				var type = {
					type: 'pie',
					backgroundColor:'transparent',
					options3d: {
						enabled: true,
						alpha: 25
					}
				};
				var title = {
					text: ''
				};
				var tooltip = {
					useHTML: true,
					backgroundColor: '#FCFFC5', 
					formatter: function() {
						var cnt = this.point.count;
						return "<b style='color:"+this.point.color+"'>"+this.point.name+" -<br/>("+Highcharts.numberFormat(this.percentage,1)+"%)</b>";
					}  
				}; 
				var plotOptions ={
					pie: {
						innerSize: 80,
						depth: 60,
						dataLabels: {
							enabled: false,
							formatter: function() {
								return (this.y) + ' %';
							},
							distance: -10,
							color:'#333'
						},
						showInLegend: legend
					},
				};
				var legend = {
					enabled: false,
					x:80,
					y:50,
					layout: 'vertical',
					align: 'right',
					verticalAlign: 'top',
					useHTML: true,
					
					labelFormatter: function() {
						return '<div><span style="color:'+this.color+'">'+this.name + '-' + this.y + '</span></div>';
					}
				};
				var data = [{
					name: '',
					data: mainArr
				}];
				highcharts(id,type,data,plotOptions,title,tooltip,legend);
			}
		}
	}
}


function getConstituencyWiseInsurance(){
	$("#insuranceOverViewId").html(spinner);
	
	var jsObj={
			"fromDate" 			:globalFromDate,
			"toDate"			:globalToDate,
			"locationTypeId" 	:locationLevelId,
			"locationValuesArr" :userAccessLevelValuesArray,
			"year": 3
		}
	$.ajax({
		type : "POST",
		url : "getConstituencyWiseInsuranceWiseIssueTypeCountsAction.action",
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			return buildLevelWiseGrievanceCountss(result)
		}
	});
	
	function buildLevelWiseGrievanceCountss(result){
		var str='';
		
		str+='<div class="row">';
			str+='<div class="col-sm-6">';
				str+='<p class="m_top10 text-capital text-center" id="totalInsuranceCount"></p>';
				str+='<div id="insuranceGraphId" style="height:180px;"></div>';
			str+='</div>';
			str+='<div class="col-sm-6">';
				for(var i in result){
					if(result[i].grivenceType == "Insurance"){
						for(var j in result[i].subList){
							str+='<h5 class="f-12" style="color:'+insuranceColorObj[result[i].subList[j].name.trim()]+';line-height:25px;">'+result[i].subList[j].name+' <span class="pull-right">'+result[i].subList[j].count+'</span></h5>';
						}
					}
				}
			str+='</div>';
			str+='</div>';
			str+='<div class="row">';
			for(var i in result){
				if(result[i].grivenceType != "Insurance"){
					str+='<div class="col-sm-6">';
					str+='<div class="block">';
					str+='<p class="m_top10 text-capital">'+result[i].grivenceType+'</p>';
					for(var j in result[i].subList){
							str+='<table class="table tableborderCss m_top10">';
								str+='<tr>';
									str+='<td class="">';
										if(result[i].subList[j].name.length > 0){
											str+='<p class="f-12">'+result[i].subList[j].name+'</p>';
										}else{
											
											str+='<p class="f-12">Others</p>';
										}
										
									str+='</td>';
									str+='<td class="text-right">';
										str+='<span>'+result[i].subList[j].count+'</span>';
									str+='</td>';
								str+='</tr>';
							str+='</table>';
					}
					str+='</div>';
					str+='</div>';
					
				}
				
			}
		str+='</div>';	
		
		
		$("#insuranceOverViewId").html(str);
		if(result !=null && result.length>0){
			var mainArr=[];
			var totalInsuranceCount = 0;
			for(var i in result){
				if(result[i].grivenceType == "Insurance"){
					for(var j in result[i].subList){
						var colorsId = insuranceColorObj[result[i].subList[j].name.trim()];
						var obj = {
							name: result[i].subList[j].name,
							y:result[i].subList[j].count,
							color:colorsId
						}
						mainArr.push(obj);
						totalInsuranceCount = totalInsuranceCount + result[i].subList[j].count;
					}
					$("#totalInsuranceCount").html('<b>Total Count - '+totalInsuranceCount+'</b>');
				}
					
				
				var id = 'insuranceGraphId';
				var type = {
					type: 'pie',
					backgroundColor:'transparent',
					options3d: {
						enabled: true,
						alpha: 25
					}
				};
				var title = {
					text: ''
				};
				var tooltip = {
					useHTML: true,
					backgroundColor: '#FCFFC5', 
					formatter: function() {
						var cnt = this.point.count;
						return "<b style='color:"+this.point.color+"'>"+this.point.name+" -<br/>("+Highcharts.numberFormat(this.percentage,1)+"%)</b>";
					}  
				}; 
				var plotOptions ={
					pie: {
						innerSize: 90,
						depth: 60,
						dataLabels: {
							enabled: false,
							formatter: function() {
								return (this.y) + ' %';
							},
							distance: -10,
							color:'#333'
						},
						showInLegend: legend
					},
				};
				var legend = {
					enabled: false,
					x:80,
					y:50,
					layout: 'vertical',
					align: 'right',
					verticalAlign: 'top',
					useHTML: true,
					
					labelFormatter: function() {
						return '<div><span style="color:'+this.color+'">'+this.name + '-' + this.y + '</span></div>';
					}
				};
				var data = [{
					name: '',
					data: mainArr
				}];
				highcharts(id,type,data,plotOptions,title,tooltip,legend);
			}
		}
	}
	
}
function getLevelWiseGrievanceCounts(){
	$("#mandalWiseGrievanceId").html(spinner);
	
	var jsObj={
			"fromDate" 			:globalFromDate,
			"toDate"			:globalToDate,
			"locationTypeId" 	:locationLevelId,
			"locationValuesArr" :userAccessLevelValuesArray,
			"year": 3
		}
	$.ajax({
		type : "POST",
		url : "getLevelWiseGrievanceCountsAction.action",
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			return buildLevelWiseGrievanceCounts(result);
		}
	});
	
	function buildLevelWiseGrievanceCounts(result){
		var str='';
		var totalCount=0;
		str+='<div class="table-responsive">';
			str+='<table class="table tablePadding" id="mandalWiseGrievanceDataTable">';
				str+='<thead>';
					str+='<tr>';
						str+='<th>Mandal Name</th>';
						str+='<th>Total</th>';
						str+='<th>Party</th>';
						str+='<th>Govt</th>';
						str+='<th>Welfare</th>';
						str+='<th>Death</th>';
						str+='<th>Hospitalization</th>';
						str+='<th>Fee Concession</th>';
						str+='<th>Seat</th>';
					str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
					for(var i in result){
						totalCount = result[i].partyCount+result[i].govtCount+result[i].welfareCount+result[i].deathCount+result[i].hosptalCount+
						result[i].feeConsCount+result[i].seatCount
						str+='<tr>';
							str+='<td>'+result[i].name+'</td>';
							if(totalCount !=null && totalCount>0){
								str+='<td>'+totalCount+'</td>';
							}else{
								str+='<td> - </td>';
							}
							if(result[i].partyCount !=null && result[i].partyCount>0){
								str+='<td>'+result[i].partyCount+'</td>';
							}else{
								str+='<td> - </td>';
							}
							if(result[i].govtCount !=null && result[i].govtCount>0){
								str+='<td>'+result[i].govtCount+'</td>';
							}else{
								str+='<td> - </td>';
							}
							if(result[i].welfareCount !=null && result[i].welfareCount>0){
								str+='<td>'+result[i].welfareCount+'</td>';
							}else{
								str+='<td> - </td>';
							}
							if(result[i].deathCount !=null && result[i].deathCount>0){
								str+='<td>'+result[i].deathCount+'</td>';
							}else{
								str+='<td> - </td>';
							}
							if(result[i].hosptalCount !=null && result[i].hosptalCount>0){
								str+='<td>'+result[i].hosptalCount+'</td>';
							}else{
								str+='<td> - </td>';
							}
							if(result[i].feeConsCount !=null && result[i].feeConsCount>0){
								str+='<td>'+result[i].feeConsCount+'</td>';
							}else{
								str+='<td> - </td>';
							}
							if(result[i].seatCount !=null && result[i].seatCount>0){
								str+='<td>'+result[i].seatCount+'</td>';
							}else{
								str+='<td> - </td>';
							}
						str+='</tr>';
					}
					
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
		
		$("#mandalWiseGrievanceId").html(str);
		$("#mandalWiseGrievanceDataTable").dataTable({
			"iDisplayLength": 10,
			"aaSorting": [],
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
			//"dom": 'lfBrtip',
			"dom": "<'row'<'col-sm-4'l><'col-sm-6'f><'col-sm-2'B>>" +
			"<'row'<'col-sm-12'tr>>" +
			"<'row m_top30'<'col-sm-5'i><'col-sm-7'p>>",
			buttons: [
				{
					extend:    'csvHtml5',
					text:      '<i class="fa fa-file-text-o tooltipCls"></i>',
					titleAttr: 'CSV',
					title:	   "MandalWiseLevel",
					filename:  "MandalWiseLevel"+moment().format("DD/MMMM/YYYY  HH:MM"),
				},
				{
					extend:    'pdfHtml5',
					text:      '<i class="fa fa-file-pdf-o tooltipCls"></i>',
					titleAttr: 'PDF',
					title:	   "MandalWiseLevel",
					filename:  'MandalWiseLevel'+moment().format("DD/MMMM/YYYY  HH:MM"),
					orientation: "landscape",
					pageSize:'A4',
					customize: function (doc) {
						doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
					}
				}
			]
		});
	}
	
}
function getLocationWiseNtrTrustDetails(){
	$("#ntrTrustOverViewId").html(spinner);
	
	var jsObj={
			"fromDate" 			:globalFromDate,
			"toDate"			:globalToDate,
			"locationTypeId" 	:locationLevelId,
			"locationValuesArr" :userAccessLevelValuesArray,
			"year": 3
		}
	$.ajax({
		type : "POST",
		url : "getLocationWiseGrivenceTrustIssueTypesCountsAction.action",
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			return locationWiseNtrTrustDetails(result);
		}
	});
	
	function locationWiseNtrTrustDetails(result){
		var str='';
		
		str+='<div class="row">';
			str+='<div class="col-sm-6">';
				str+='<p class="m_top10 text-capital text-center" id="totalNtrTrustCount"></p>';
				str+='<div id="ntrTrustGraphId" style="height:180px;"></div>';
			str+='</div>';
			str+='<div class="col-sm-6">';
				for(var i in result){
					if(result[i].grivenceType == "NTR Trust"){
						for(var j in result[i].subList){
							str+='<h5 class="f-12" style="color:'+grivanceColorObj[result[i].subList[j].name.trim()]+';line-height:25px;">'+result[i].subList[j].name+' <span class="pull-right">'+result[i].subList[j].count+'</span></h5>';
						}
					}
				}
			str+='</div>';
			str+='</div>';
			str+='<div class="row">';
			for(var i in result){
				if(result[i].grivenceType != "NTR Trust"){
					str+='<div class="col-sm-6">';
					str+='<div class="block">';
					str+='<p class="m_top10 text-capital">'+result[i].grivenceType+'</p>';
					for(var j in result[i].subList){
							str+='<table class="table tableborderCss m_top10">';
								str+='<tr>';
									str+='<td class="">';
										if(result[i].subList[j].name.length > 0){
											str+='<p class="f-12">'+result[i].subList[j].name+'</p>';
										}else{
											
											str+='<p class="f-12">Others</p>';
										}
										
									str+='</td>';
									str+='<td class="text-right">';
										str+='<span>'+result[i].subList[j].count+'</span>';
									str+='</td>';
								str+='</tr>';
							str+='</table>';
					}
					str+='</div>';
					str+='</div>';
					
				}
				
			}
		str+='</div>';	
		
		
		$("#ntrTrustOverViewId").html(str);
		if(result !=null && result.length>0){
			var mainArr=[];
			var totalNtrTrustCount = 0;
			for(var i in result){
				if(result[i].grivenceType == "NTR Trust"){
					for(var j in result[i].subList){
						var colorsId = grivanceColorObj[result[i].subList[j].name.trim()];
						var obj = {
							name: result[i].subList[j].name,
							y:result[i].subList[j].count,
							color:colorsId
						}
						mainArr.push(obj);
						totalNtrTrustCount = totalNtrTrustCount + result[i].subList[j].count;
					}
					$("#totalNtrTrustCount").html('<b>Total Count - '+totalNtrTrustCount+'</b>');
				}
					
				
				var id = 'ntrTrustGraphId';
				var type = {
					type: 'pie',
					backgroundColor:'transparent',
					options3d: {
						enabled: true,
						alpha: 25
					}
				};
				var title = {
					text: ''
				};
				var tooltip = {
					useHTML: true,
					backgroundColor: '#FCFFC5', 
					formatter: function() {
						var cnt = this.point.count;
						return "<b style='color:"+this.point.color+"'>"+this.point.name+" -<br/>("+Highcharts.numberFormat(this.percentage,1)+"%)</b>";
					}  
				}; 
				var plotOptions ={
					pie: {
						innerSize: 90,
						depth: 60,
						dataLabels: {
							enabled: false,
							formatter: function() {
								return (this.y) + ' %';
							},
							distance: -10,
							color:'#333'
						},
						showInLegend: legend
					},
				};
				var legend = {
					enabled: false,
					x:80,
					y:50,
					layout: 'vertical',
					align: 'right',
					verticalAlign: 'top',
					useHTML: true,
					
					labelFormatter: function() {
						return '<div><span style="color:'+this.color+'">'+this.name + '-' + this.y + '</span></div>';
					}
				};
				var data = [{
					name: '',
					data: mainArr
				}];
				highcharts(id,type,data,plotOptions,title,tooltip,legend);
			}
		}
	}
}