/* getSwachhBharatMissionStatusOverviewDtls();//secondBlock
	getIHHLCategoryWiseAnalysis();//third block
	getIHHLAchivementProgressDtls();//fourth block */
var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var globalColor = {"A":"#009587","B":"#99B95F","C":"#E67401","D":"#FD403A"};	
var levelWiseSBArr = ['state','district','constituency','mandal'];
var globalFromDateForLevel = moment().subtract(10,'days').format("DD-MM-YYYY");
var globalToDateForLevel = moment().format("DD-MM-YYYY");
onloadCalls();
function onloadCalls(){
	getSwachhBharatMissionOverviewDtls(); // first block And Second Block
	levelWiseSBData("IHHL")
}
function highcharts(id,type,data,plotOptions,title,tooltip,legend){
	'use strict';
	$('#'+id).highcharts({
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
function highcharts1(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip,colors,title)
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
function getSwachhBharatMissionOverviewDtls(){
	$("#categoryWiseDataId").html(spinner);
	$("#overAllIHHLPerformanceId").html(spinner);
	$("#statusWiseIHHLPerformanceId").html(spinner);
	var json = {
		fromDate:"",
		toDate:"",
		location:"state",
		locationId:"-1",
		subLocation:"state"
	}
	$.ajax({                
		type:'POST',    
		url: 'getSwachhBharatMissionOverviewDtls',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		$("#categoryWiseDataId").html('');
		$("#overAllIHHLPerformanceId").html('');
		$("#statusWiseIHHLPerformanceId").html('');
		if(result !=null){
			return buildSwachhBharatMissionOverviewDtls(result);
		}
	});	
	
	function buildSwachhBharatMissionOverviewDtls(result){
		
		var str='';
		if(result !=null && result.subList !=null && result.subList.length>0){
			for(var i in result.subList){
				str+='<div class="col-sm-3 m_top10">';
					str+='<div class="panel panel-default">';
						str+='<div class="panel-heading" style="padding: 3px;background-color:#fff;">';
							str+='<div class="row">';
								str+='<div class="col-sm-2 m_top10">';
									str+='<span class="categoryRondedCss" style="background-color:'+globalColor[result.subList[i].name.trim()]+';">'+result.subList[i].name+'</span>';
								str+='</div>';
								str+='<div class="col-sm-10">';
									str+='<p class="text-right" style="font-size: 16px;color:'+globalColor[result.subList[i].name.trim()]+'">'+result.subList[i].range+' %</p>';
									str+='<h5 class="panel-title text-right" style="color:'+globalColor[result.subList[i].name.trim()]+'">Achivement</h5>';
								str+='</div>';
							str+='</div>';
							
						str+='</div>';
						str+='<div class="panel-body">';
							str+='<ul class="list-inline borderleft">';
								str+='<li><h3><span style="color:'+globalColor[result.subList[i].name.trim()]+'">D</span> - '+result.subList[i].districtCount+'</h3></li>';
								str+='<li><h3 ><span style="color:'+globalColor[result.subList[i].name.trim()]+'">C</span> - '+result.subList[i].constituencyCount+'</h3></li>';
								str+='<li><h3><span style="color:'+globalColor[result.subList[i].name.trim()]+'">M</span> - '+result.subList[i].mandalCount+'</h3></li>';
							str+='<ul>';
						str+='</div>';	
					str+='</div>';
				str+='</div>';
			}
			
		}
		
		
		$("#categoryWiseDataId").html(str);
		
		var dataArr=[];
		var targetCount =0;
		var achivementCount=0;
		var totalCount=0;
		var targetCount1=0;
		var groundedCount=0;
		var notGroundedCount=0;
		var inProgressCount=0;
		var completedCount=0;
		
		totalCount =result.target+result.grounded+result.noTGrounded+result.inProgress+result.completed
		
		
		
		targetCount = result.target;
		achivementCount = result.completed;
		dataArr.push(result.target)
		dataArr.push(result.grounded)
		dataArr.push(result.noTGrounded)
		dataArr.push(result.inProgress)
		dataArr.push(result.completed)
					
		/* targetCount1 = result.target
		groundedCount = result.grounded
		notGroundedCount = result.noTGrounded
		inProgressCount = result.inProgress
		completedCount = result.completed */
		
		var id = "overAllIHHLPerformanceId";
		var type = {
			type: 'pie',
			backgroundColor:'transparent',
			options3d: {
				enabled: true,
				alpha: 25
			}
		};
		var title = {
			text: 'Total Swatch Bharat - IHHL',
			style: {
				 color: '#000',
				 font: 'bold 13px "Lato", sans-serif'
			  }
		};
		var tooltip = {
			useHTML: true,
			backgroundColor: '#FCFFC5', 
			formatter: function() {
				return "<b style='color:"+this.point.color+"'>"+this.point.name+" <br/>"+this.y+"</b>";//- ("+(Highcharts.numberFormat(this.percentage,1))+" %)
			}  
		}; 
		var plotOptions ={
			pie: {
				innerSize: 100,
				depth: 70,
				dataLabels: {
					enabled: false,
					formatter: function() {
						return (Highcharts.numberFormat(this.percentage,1)) + ' %';
					},
					distance: -20,
					color:'#333'
				},
				showInLegend: true
			},
		};
		var legend = {
			enabled: true,
			layout: 'vertical',
			align: 'center',
			verticalAlign: 'bottom',
			useHTML: true,
			
			labelFormatter: function() {
				return '<div><span style="color:'+this.color+'">'+this.name + '- <b>' + this.y +'</b></span></div>';//' - '+(Highcharts.numberFormat(this.percentage,1)) + ' %'+
			}
		};
		var data = [{
			name: '',
			data: [
				{
				  name: 'TARGET',
				  y: targetCount,
				  color:"#FC615E"
				},
				{
				  name: 'COMPLETED',
				  y: achivementCount,
				  color:"#13B9AC"
				}
			]
		}];
		highcharts(id,type,data,plotOptions,title,tooltip,legend);
		
		$("#statusWiseIHHLPerformanceId").highcharts({
			colors:["#FC615E","#FFBA00","#3B876E","#00D2BC","#686CC6"],
			chart: {
				type: 'column'
			},
			title: {
				text: 'Swatch Bharat - IHHL Status',
				style: {
				 color: '#000',
				 font: 'bold 13px "Lato", sans-serif'
			  }
			},
			subtitle: {
				text: ''
			},
			xAxis: {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,	
				type: 'category',
				categories: ['TARGET','GROUNDED','NOT GROUNDED','IN PROGRESS','COMPLETED'],
				
			},
			yAxis: {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				title: {
					text: null
				}
			},
			legend: {
				enabled: false
			},
			tooltip: {
				useHTML:true,	
				formatter: function () {
					var pcnt = (this.y / totalCount) * 100;
					return '<b>' + this.x + '</b><br/> '+this.series.name+" - " +this.y;//"-"+((Highcharts.numberFormat(pcnt)))+'%'
				}
			},
			plotOptions: { 
				column: {
					pointWidth: 30,
					gridLineWidth: 25,
					colorByPoint: true
				}
			},
			series: [{
				name: 'Count',
				data: dataArr,
				dataLabels: {
					enabled: true,
					color: '#000',
					align: 'center',
					formatter: function() {
						var pcnt = (this.y / totalCount) * 100;
						return '<span>'+this.y+'</span>';//'<br>('+Highcharts.numberFormat(pcnt)+'%)
					}
				}
			}]
		});
	}
}

function getSwachhBharatMissionStatusOverviewDtls(){
	var json = {
		fromDate:"",
		toDate:"",
		year:""
	}
	$.ajax({                
		type:'POST',    
		url: 'getSwachhBharatMissionStatusOverviewDtls',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		console.log(result);
	});	
}
function getIHHLCategoryWiseAnalysis(){
		var json = {
			fromDate:"",
			toDate:"",
			year:""
		}
		$.ajax({                
			type:'POST',    
			url: 'getIHHLCategoryWiseAnalysis',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			console.log(result);
		});	
}
function getIHHLAchivementProgressDtls(){
		var json = {
					fromDate:"02-10-2017",
					toDate:"07-10-2017",
					location:"state",
					locationId:"-1",
					subLocation:"state",
					reportType:"daily",//daily
					displayType:"month"//day/week/month
				}
		$.ajax({                
			type:'POST',    
			url: 'getIHHLAchivementProgressDtls',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			console.log(result);
		});	
}
function getSwachhBharatMissionLocationWiseDetails(subLocation,reportType,displayType){
	$("#IHHL"+subLocation).html(spinner);
		var json = {
			fromDate:globalFromDateForLevel,
			toDate:globalToDateForLevel,
			location:"state",
			locationId:"-1",
			subLocation:subLocation, // district/constituency/manal
			reportType:reportType,//daily
			displayType:displayType//day/week/month
		}
		$.ajax({                
			type:'POST',    
			url: 'getSwachhBharatMissionLocationWiseDetails',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			if(result !=null && result.length>0){
				return buildSwachhBharatMissionLocationWiseDetails(result,reportType,subLocation);
			}
		});
	
	function buildSwachhBharatMissionLocationWiseDetails(result,reportType,subLocation){
		
		var str='';
		
		str+='<div class="table-responsive">';
		if(reportType == "status"){
			str+='<table class="table table-bordered table-condensed" id="dataTable'+subLocation+'">';
		}else if(reportType == "daily"){
			str+='<table class="table table-bordered table-condensed" id="dataTableDaily'+subLocation+'">';
		}
			
				str+='<thead>';
					if(reportType == "status"){
						str+='<tr>';	
							if(subLocation =="state"){
								str+='<th>State</th>';
							}else if(subLocation =="district"){
								str+='<th>District</th>';
							}else if(subLocation =="constituency"){
								str+='<th>District</th>';
								str+='<th>Constituency</th>';
							}else if(subLocation =="mandal"){
								str+='<th>District</th>';
								str+='<th>Constituency</th>';
								str+='<th>Mandal</th>';
							}
							str+='<th>Target</th>';	
							str+='<th>Grounded</th>';	
							str+='<th>Not Grounded</th>';	
							str+='<th>In Progress</th>';	
							str+='<th>Completed</th>';	
							str+='<th>Achivement %</th>';
						str+='</tr>';
						}else if(reportType == "daily"){
								str+='<tr>';
								if(subLocation =="state"){
									str+='<th rowspan="2">State</th>';
								}else if(subLocation =="district"){
									str+='<th rowspan="2">District</th>';
								}else if(subLocation =="constituency"){
									str+='<th rowspan="2">District</th>';
									str+='<th rowspan="2">Constituency</th>';
								}else if(subLocation =="mandal"){
									str+='<th rowspan="2">District</th>';
									str+='<th rowspan="2">Constituency</th>';
									str+='<th rowspan="2">Mandal</th>';
								}
								str+='<th rowspan="2">Target</th>';	
								str+='<th rowspan="2">Achivement</th>';	
								str+='<th rowspan="2">%</th>';	
								if(result[0].subList !=null && result[0].subList.length>0){
									for(var i in result[0].subList){
										str+='<th colspan="3">'+result[0].subList[i].range+'</th>';	
									}
								}
							str+='</tr>';
							str+='<tr>';
								if(result[0].subList !=null && result[0].subList.length>0){
									for(var i in result[0].subList){
										str+='<th>Target</th>';	
										str+='<th>Achivement</th>';	
										str+='<th>%</th>';	
									}
								}
							str+='</tr>';	
						}
				str+='</thead>';
				str+='<tbody>';
					for(var i in result){
						str+='<tr>';
							if(subLocation =="state"){
								str+='<td>'+result[i].stateName+'</td>';
								}else if(subLocation =="district"){
									str+='<td>'+result[i].districtName+'</td>';
								}else if(subLocation =="constituency"){
									str+='<td>'+result[i].districtName+'</td>';
									str+='<td>'+result[i].constName+'</td>';
								}else if(subLocation =="mandal"){
									str+='<td>'+result[i].districtName+'</td>';
									str+='<td>'+result[i].constName+'</td>';
									str+='<td>'+result[i].mandalName+'</td>';
									
								}
								if(reportType == "status"){
									if(result[i].target !=null && result[i].target>0){
										str+='<td>'+result[i].target+'</td>';
									}else{
										str+='<td> - </td>';
									}	
									if(result[i].grounded !=null && result[i].grounded>0){
										str+='<td>'+result[i].grounded+'</td>';
									}else{
										str+='<td> - </td>';
									}
									if(result[i].noTGrounded !=null && result[i].noTGrounded>0){
										str+='<td>'+result[i].noTGrounded+'</td>';
									}else{
										str+='<td> - </td>';
									}	
									if(result[i].inProgress !=null && result[i].inProgress>0){
										str+='<td>'+result[i].inProgress+'</td>';
									}else{
										str+='<td> - </td>';
									}
									if(result[i].completed !=null && result[i].completed>0){
										str+='<td>'+result[i].completed+'</td>';
									}else{
										str+='<td> - </td>';
									}
									if(result[i].percentage !=null && result[i].percentage>0){
										if(result[i].percentage < 100){
											str+='<td style="background-color:#FFE296">'+result[i].percentage+' %</td>';
										}else{
											str+='<td style="background-color:#C7F0C5;">'+result[i].percentage+' %</td>';
										}
									}else{
										str+='<td> - </td>';
									}
								}else if(reportType == "daily"){
									str+='<td>'+result[i].target+'</td>';
									str+='<td>'+result[i].completed+'</td>';
									str+='<td>'+result[i].percentage+'</td>';
									
									for(var j in result[i].subList){
										str+='<td>'+result[i].subList[j].target+'</td>';
										str+='<td>'+result[i].subList[j].completed+'</td>';
										str+='<td>'+result[i].subList[j].percentage+'</td>';
									}
								}
							
								
						str+='</tr>';
					}
					
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
		
		$("#IHHL"+subLocation).html(str);
		if(reportType == "status"){
			$("#dataTable"+subLocation).dataTable({
				"iDisplayLength": 10,
				"aaSorting": [],
				"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
			});
		}else if(reportType == "daily"){
			$("#dataTableDaily"+subLocation).dataTable({
				"iDisplayLength": 10,
				"aaSorting": [],
				"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
			});
		}
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
									collapse+='<h4 class="panel-title text-capital">'+levelWiseSBArr[i]+' level overview</h4>';
								else
									collapse+='<h4 class="panel-title text-capital">'+levelWiseSBArr[i]+' level overview</h4>';
									
								collapse+='</a>';
							collapse+='</div>';
							if(i == 0)
							{
								collapse+='<div id="collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'">';
							}else{
								collapse+='<div id="collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'">';
							}
							
								collapse+='<div class="panel-body">';
									collapse+='<ul class="switch-btn-New" role="tabStatusWise" attr_level_type="'+levelWiseSBArr[i]+'">';
										collapse+='<li class="active ActiveStateCls" attr_type="daily">Daily</li>';
										collapse+='<li attr_type="status">STATUS</li>';
									collapse+='</ul>';
									collapse+='<div id="'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'"></div>';
								collapse+='</div>';
							collapse+='</div>';
						collapse+='</div>';
					collapse+='</div>';
				}
			collapse+='</div>';
			collapse+='</div>';
			collapse+='</section>';
		$("#levelWiseDetailsBlockId").html(collapse);
		setTimeout(function(){ 
		for(var i in levelWiseSBArr){
			getSwachhBharatMissionLocationWiseDetails(levelWiseSBArr[i],"daily",'day')
		}	
	
	}, 1500);
}
$(document).on("click","[role='tabStatusWise'] li",function(){
	$(this).closest("ul").find("li").removeClass("active");
	$(this).addClass("active");
	
	var reportType='';
	var levelType = $(this).closest("ul").attr("attr_level_type");
	if($(this).hasClass("active")){
		reportType = $(this).attr("attr_type");
	}
	
	var displayType='';
	$(".calendar_active_IHHL_cls li").each(function(i, obj){
		 if($(this).hasClass("active")){
			  displayType = $(this).attr("attr_val");
		 }
		
	});
	
	if(reportType == "status"){
		$(".calendar_active_IHHL_cls").hide();
	}else{
		$(".calendar_active_IHHL_cls").show();
	}
	if(levelType == "state"){
		getSwachhBharatMissionLocationWiseDetails("state",reportType,displayType);
	}else if(levelType == "district"){
		getSwachhBharatMissionLocationWiseDetails("district",reportType,displayType);
	}else if(levelType == "constituency"){
		getSwachhBharatMissionLocationWiseDetails("constituency",reportType,displayType);
	}else if(levelType == "mandal"){
		getSwachhBharatMissionLocationWiseDetails("mandal",reportType,displayType);
	}
});
$(document).on("click",".calendar_active_IHHL_cls li",function(){
	$(this).closest("ul").find("li").removeClass("active");
	$(this).addClass("active");
	
	var displayType ='';
	if($(this).hasClass("active")){
		displayType = $(this).attr("attr_val");
	}
	if(displayType !="custom"){
		for(var i in levelWiseSBArr){
			getSwachhBharatMissionLocationWiseDetails(levelWiseSBArr[i],"daily",displayType);
		}
	}
	
});
$("#singleDateRangePicker").daterangepicker({
	opens: 'left',
	startDate: globalFromDateForLevel,
	endDate: globalToDateForLevel,
	locale: {
	  format: 'DD-MM-YYYY'
	}
});
$('#singleDateRangePicker').on('apply.daterangepicker', function(ev, picker) {
	
	globalFromDate = picker.startDate.format('DD-MM-YYYY')
	globalToDate = picker.endDate.format('DD-MM-YYYY')
	$(".defaultActiveClsDay").addClass("active");
	
	for(var i in levelWiseSBArr){
		getSwachhBharatMissionLocationWiseDetails(levelWiseSBArr[i],"daily",'day')
	}
});