var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var globalColor = {"A":"#F7B519","B":"#00AF50","C":"#FF6600","D":"#FF0000"};
var globalGradientsColorArr = {"A":"padding: 3px;background: -moz-linear-gradient(left, #F7B419 0%, #ffffff 100%);background: -webkit-linear-gradient(left, #F7B419 0%, #ffffff 100%);padding-top: 10px; padding-bottom: 10px;","B":"padding: 3px;background: -moz-linear-gradient(left, #00AF50 0%, #ffffff 100%);background: -webkit-linear-gradient(left, #00AF50 0%, #ffffff 100%);padding-top: 10px; padding-bottom: 10px;","C":"padding: 3px;background: -moz-linear-gradient(left, #FF6600 0%, #ffffff 100%);background: -webkit-linear-gradient(left, #FF6600 0%, #ffffff 100%);padding-top: 10px; padding-bottom: 10px;","D":"padding: 3px;background: -moz-linear-gradient(left, #FF0000 0%, #ffffff 100%);background: -webkit-linear-gradient(left, #FF0000 0%, #ffffff 100%);padding-top: 10px; padding-bottom: 10px;"};	
var levelWiseSBArr = ['state','district','constituency','mandal'];
var globalFromDateForLevel = "01-10-2017";
var globalToDateForLevel = moment().format("DD-MM-YYYY");

onloadCalls();
onloadIntiliazilation();
function onloadCalls(){
	$(".tooltipCls").tooltip();
	$("#dailyCategoryWiseAnalysisHeadinId").html(globalFromDateForLevel+" to "+globalToDateForLevel);
	getSwachhBharatMissionOverviewDtls(); // first block And Second Block
	getIHHLCategoryWiseAnalysisBySelectedDate();
	getIHHLAchivementProgressDtls("week");
	levelWiseSBData("IHHL");
}
function onloadIntiliazilation(){
	$("header").on("click",".menu-cls",function(e){
		e.stopPropagation();
		$(".menu-data-cls").toggle();
	});
	$(document).on("click",function(){
		$(".menu-data-cls").hide();
	});
	
	$("#dateRangePickerAUM").daterangepicker({
		opens: 'left',
		startDate: globalFromDateForLevel,
        endDate: globalToDateForLevel,
		minDate: globalFromDateForLevel,
		locale: {
		  format: 'DD-MM-YYYY'
		},
		ranges: {
		  // 'Last 10 Days': [moment().subtract(10, 'days'), moment()],
          // 'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		  // 'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		   //'Last 3 Months': [moment().subtract(3, 'month'), moment()],
		  // 'Last 6 Months': [moment().subtract(6, 'month'), moment()],
		  // 'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
           //'This Month': [moment().startOf('month'), moment()],//.endOf('month')
          // 'This Year': [moment().startOf('Year'), moment()],
		  // 'Overall' : [moment().subtract(30, 'years').startOf('year'), moment()],
        }
	});
	$('#dateRangePickerAUM').on('apply.daterangepicker', function(ev, picker) {
			globalFromDateForLevel = picker.startDate.format('DD-MM-YYYY');
			globalToDateForLevel = picker.endDate.format('DD-MM-YYYY');
			$(".commpnliCls").removeClass("active");
			$(".weeklicls").addClass("active");
			onloadCalls();
	});	
	$("#singleDateRangePicker").daterangepicker({
		opens: 'left',
		startDate: globalFromDateForLevel,
		endDate: globalToDateForLevel,
		minDate: globalFromDateForLevel,
		locale: {
		  format: 'DD-MM-YYYY'
		}
	});
	$('#singleDateRangePicker').on('apply.daterangepicker', function(ev, picker) {
		
		globalFromDateForLevel = picker.startDate.format('DD-MM-YYYY')
		globalToDateForLevel = picker.endDate.format('DD-MM-YYYY')
		$(".defaultActiveClsDay").addClass("active");
		
		$(".timePeriodCommonCls").removeClass("active");
		$(".locationLevelWeekCls").addClass("active");
		for(var i in levelWiseSBArr){
			getSwachhBharatMissionLocationWiseDetails(levelWiseSBArr[i],"daily",'day')
		}
	});
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
	$("#overAllIHHLPerformanceId").html(spinner);
	$("#statusWiseIHHLPerformanceId").html(spinner);
	$("#categoryWiseDataId").html(spinner);
	var json = {
		fromDate:globalFromDateForLevel,
		toDate:globalToDateForLevel,
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
		if(result !=null){
			return buildSwachhBharatMissionOverviewDtls(result);
		} else {
			$("#overAllIHHLPerformanceId").html("NO DATA AVAILABLE");
			$("#statusWiseIHHLPerformanceId").html("NO DATA AVAILABLE");
			$("#categoryWiseDataId").html("NO DATA AVAILABLE");
		}
	});	
	
	function buildSwachhBharatMissionOverviewDtls(result){
		
		var str='';
		if(result !=null && result.subList !=null && result.subList.length>0){
			for(var i in result.subList){
				str+='<div class="col-sm-3 m_top10">';
					str+='<div class="panel panel-default">';
						str+='<div class="panel-heading" style="padding: 3px;background-color:#fff;">';
							 str+='<div class="row" style="color:'+globalGradientsColorArr[result.subList[i].name.trim()]+'>';
								str+='<div class="col-sm-2 m_top10">';
									str+='<span class="categoryRondedCss" style="background-color:'+globalColor[result.subList[i].name.trim()]+';">'+result.subList[i].name+'</span>';
								str+='</div>';
								/*str+='<div class="col-sm-10">';
									str+='<p class="text-right" style="font-size: 16px;color:'+globalColor[result.subList[i].name.trim()]+'">'+result.subList[i].range+'</p>';
									str+='<h5 class="panel-title text-right" style="color:'+globalColor[result.subList[i].name.trim()]+'">Achivement</h5>';
								str+='</div>';*/
							str+='</div>'; 
							
						str+='</div>';
						str+='<div class="panel-body">';
							str+='<ul class="list-inline borderleft">';
							    if (result.subList[i].districtCount != null && result.subList[i].districtCount > 0) {
									str+='<li style="cursor:pointer;" attr_report_type="status" attr_category_type="'+result.subList[i].name.trim()+'" attr_location_type="district" class="categoryCls"><h3><span style="color:'+globalColor[result.subList[i].name.trim()]+'">D</span> - '+result.subList[i].districtCount+'</h3></li>';
								} else {
									str+='<li><h3><span style="color:'+globalColor[result.subList[i].name.trim()]+'">D</span> - '+result.subList[i].districtCount+'</h3></li>';
								}
								if (result.subList[i].constituencyCount != null && result.subList[i].constituencyCount > 0) {
									str+='<li style="cursor:pointer;" attr_report_type="status" attr_category_type="'+result.subList[i].name.trim()+'" attr_location_type="constituency" class="categoryCls"><h3><span style="color:'+globalColor[result.subList[i].name.trim()]+'">C</span> - '+result.subList[i].constituencyCount+'</h3></li>';
								} else {
									str+='<li><h3><span style="color:'+globalColor[result.subList[i].name.trim()]+'">C</span> - '+result.subList[i].constituencyCount+'</h3></li>';
								}
								if (result.subList[i].mandalCount != null && result.subList[i].mandalCount > 0) {
									str+='<li style="cursor:pointer;" attr_report_type="status" attr_category_type="'+result.subList[i].name.trim()+'" attr_location_type="mandal" class="categoryCls"><h3><span style="color:'+globalColor[result.subList[i].name.trim()]+'">M</span> - '+result.subList[i].mandalCount+'</h3></li>';
								} else {
									str+='<li><h3><span style="color:'+globalColor[result.subList[i].name.trim()]+'">M</span> - '+result.subList[i].mandalCount+'</h3></li>';
								}
								
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
		var notSanctionCount=0;
		var notStartedCount=0;
		var sanctionedCount=0;
		totalCount =result.target;
		notSanctionCount=result.target-result.sanctioned;
		notStartedCount=result.sanctioned-(result.inProgress+result.completed);
		sanctionedCount=result.sanctioned;
		targetCount = result.target;
		achivementCount = result.completed;
		dataArr.push(result.target);
		dataArr.push(notSanctionCount)
		dataArr.push(result.sanctioned)
		dataArr.push(notStartedCount);
		dataArr.push(result.inProgress);
		dataArr.push(result.completed)
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
			text: 'Total Swatch Bharat - IHHL(up to 31 march)',
			align: 'left',
			style: {
				 color: '#000',
				 font: 'bold 12px "Lato", sans-serif'
			  }
		};
		var tooltip = {
			useHTML: true,
			backgroundColor: '#FCFFC5', 
			formatter: function() {
				   var pcnt = (this.y / totalCount) * 100;
					if (this.point.name == "TARGET") {
						str="";
					} else {
						str='-'+Highcharts.numberFormat(pcnt)+'%';
					}
				return "<b style='color:"+this.point.color+"'>"+this.point.name+" -<br/>"+this.y+""+str+"</b>";
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
					  var pcnt = (this.y / totalCount) * 100;
						if (this.name == "TARGET") {
							str="";
						} else {
							str='-'+Highcharts.numberFormat(pcnt)+'%';
						}
						return '<div><span style="color:'+this.color+'">'+this.name + '- <b>' + this.y +'</b>'+str+'</span></div>';
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
				  name: 'ACHIEVEMENT',
				  y: achivementCount,
				  color:"#13B9AC"
				}
			]
		}];
		highcharts(id,type,data,plotOptions,title,tooltip,legend);
		
		$("#statusWiseIHHLPerformanceId").highcharts({
			colors:["#FC615E","#3B876E","#FFBA00","#00D2BC","#686CC6"],
			chart: {
				type: 'column'
			},
			title: {
				text: 'Swatch Bharat - IHHL Status(<b>up to 31 march)',
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
				categories: ['TARGET','NOT SANCTIONED','SANCTIONED','NOT STARTED','IN PROGRESS','COMPLETED'],
				//categories: ['SANCTIONED','TARGET','NOT GROUNDED','IN PROGRESS','COMPLETED','EXCESSBEN'],
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
					var pcnt='';
					if(this.x =="NOT STARTED" || this.x =="IN PROGRESS" || this.x =="COMPLETED"){
					 pcnt = (this.y /sanctionedCount) * 100;
					}else if(this.x == "SANCTIONED" || this.x == "NOT SANCTIONED"){
						pcnt = (this.y / totalCount) * 100;
					}
						var str="";
						if (this.x == "TARGET") {
							str="";
						} else {
							str='-'+Highcharts.numberFormat(pcnt)+'%';
						}
					return '<b>' + this.x + '</b><br/> '+this.series.name+" - " +this.y+''+str+"";
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
						var pcnt ="";
						if(this.x =="NOT STARTED" || this.x =="IN PROGRESS" || this.x =="COMPLETED"){
							pcnt = (this.y /sanctionedCount) * 100;
						}else if(this.x == "SANCTIONED" || this.x == "NOT SANCTIONED"){
							pcnt = (this.y / totalCount) * 100;
						}
						var str="";
						if (this.x == "TARGET") {
							str="";
						} else {
							str='<br>('+Highcharts.numberFormat(pcnt)+'%)';
						}
						return '<span>'+this.y+''+str+'</span>';
					}
				}
			}]
		});
	}
}
function getIHHLAchivementProgressDtls(displayType){
	$("#IHHLAchivementProgress").html(spinner)
		var json = {
					fromDate:globalFromDateForLevel,
					toDate:globalToDateForLevel,
					location:"state",
					locationId:"-1",
					subLocation:"state",
					reportType:"daily",
					displayType:displayType
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
			if(result !=null && result.length>0){
				buildIHHLAchivementProgressDtls(result,displayType);
			}else{
				$("#IHHLAchivementProgress").html("NO DATA AVAILABLE.");
			}
		});

		function buildIHHLAchivementProgressDtls(result,displayType){
			var datesArr=[];
			var targetArr=[];
			var completedArr=[];
			for(var i in result){
				var heading="";
				if (displayType=="week") {
					heading = formDateInRequiredFormat(result[i].fromDate)+" TO "+formDateInRequiredFormat(result[i].toDate);
				} else {
					heading = result[i].range;
				}
				datesArr.push(heading);
				targetArr.push(result[i].target)
				completedArr.push(result[i].completed)
			}
			$('#IHHLAchivementProgress').highcharts({
				chart: {
					type: 'area'
				},
				title: {
					text: ''
				},
				subtitle: {
					text: ''
				},
				xAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories:datesArr
				},
				yAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,	
					title: {
						text: ''
					},
				},
				tooltip: {
					useHTML: true,
					formatter: function() {
						return '<b>'+this.x+' <br/> '+this.series.name+' : '+this.y+'</b>'
					}, 
					// shared: true
					//pointFormat: '{series.name} <b>{point.y:,.0f}</b><br/>warheads in {point.x}',
					
				},
				plotOptions: {
					area: {
						marker: {
							enabled: false,
							symbol: 'circle',
							radius: 2,
							states: {
								hover: {
									enabled: true
								}
							}
						}
					}
				},
				series: [{
					name: 'TARGET',
					data: targetArr,
					color:"#FC615E"
				}, {
					name: 'COMPLETED',
					data: completedArr,
					color:"#13B9AC" 
				}]
			});
		}
}
function getSwachhBharatMissionLocationWiseDetails(subLocation,reportType,displayType){
	$("#IHHL"+subLocation).html(spinner);
		var json = {
			fromDate:globalFromDateForLevel,
			toDate:globalToDateForLevel,
			location:"state",
			locationId:"-1",
			subLocation:subLocation, 
			reportType:reportType,
			displayType:displayType
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
			$("#IHHL"+subLocation).html('');
			if(result !=null && result.length>0){
				return buildSwachhBharatMissionLocationWiseDetails(result,reportType,subLocation,displayType);
			} else {
				$("#IHHL"+subLocation).html("NO DATA AVAILABLE.");
			}
		});
	
	function buildSwachhBharatMissionLocationWiseDetails(result,reportType,subLocation,displayType){
		
		var str='';
		
		str+='<div class="table-responsive">';
		if(reportType == "status"){
			str+='<table class="table table-bordered table-condensed" id="dataTable'+subLocation+'" style="width:100%">';
		}else if(reportType == "daily"){
			str+='<table class="table table-bordered table-condensed" id="dataTableDaily'+subLocation+'" style="width:100%">';
		}
			
				str+='<thead>';
					if(reportType == "status"){
						str+='<tr class="text-capital  text-center">';	
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
							str+='<th class="sacnotsacClass">NOT SANCTIONED</th>';
							str+='<th class="sacnotsacClass">%</th>';
							str+='<th class="sacnotsacClass">SANCTIONED</th>';
							str+='<th class="sacnotsacClass">%</th>';
							//str+='<th>Grounded</th>';	
						//	str+='<th>Not Grounded</th>';
							str+='<th class="notstartinprogresscomClass">NOT STARTED</th>';
							str+='<th class="notstartinprogresscomClass">%</th>';							
							str+='<th class="notstartinprogresscomClass">In Progress</th>';	
							str+='<th class="notstartinprogresscomClass">%</th>';
							str+='<th class="notstartinprogresscomClass">Completed</th>';	
							str+='<th class="notstartinprogresscomClass">%</th>';
							str+='<th>Achievement %</th>';
						str+='</tr>';
						}else if(reportType == "daily"){
								str+='<tr class="text-capital">';
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
								str+='<th rowspan="2" style="background-color:#FC615E;color:#fff">Target</th>';	
								str+='<th rowspan="2" style="background-color:#13B9AC;color:#fff">achievement</th>';	
								str+='<th rowspan="2" style="background-color:#13B9AC;color:#fff">%</th>';	
								if(result[0].subList !=null && result[0].subList.length>0){
									for(var i in result[0].subList){
										var heading="";
										if (displayType=="week") {
											heading = formDateInRequiredFormat(result[0].subList[i].fromDate)+"&nbsp;<b style='color:green;'>to</b>&nbsp;"+formDateInRequiredFormat(result[0].subList[i].toDate);
										} else {
											heading = result[0].subList[i].range;
										}
										str+='<th colspan="3" class="text-center">'+heading+'</th>';	
									}
								}
							str+='</tr>';
							str+='<tr class="text-capital">';
								if(result[0].subList !=null && result[0].subList.length>0){
									for(var i in result[0].subList){
										str+='<th>Target</th>';	
										str+='<th>achievement</th>';	
										str+='<th>%</th>';	
									}
								}
							str+='</tr>';	
						}
				str+='</thead>';
				str+='<tbody>';
					for(var i in result){
							str+='<tr class="text-capital">';	
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
										str+='<td class="text-center">'+result[i].target+'</td>';
									}else{
										str+='<td class="text-center"> - </td>';
									}	
									if(result[i].notSanctioned !=null && result[i].notSanctioned>0){
										str+='<td class="text-center">'+result[i].notSanctioned+'</td>';
									}else{
										str+='<td class="text-center"> - </td>';
									}	
									if(result[i].notSanctionPercentage !=null && result[i].notSanctionPercentage>0){
										str+='<td class="text-center">'+result[i].notSanctionPercentage+'</td>';
									}else{
										str+='<td class="text-center"> - </td>';
									}	
									if(result[i].sanctioned !=null && result[i].sanctioned>0){
										str+='<td class="text-center">'+result[i].sanctioned+'</td>';
									}else{
										str+='<td class="text-center"> - </td>';
									}	
									if(result[i].sanctionpercentage !=null && result[i].sanctionpercentage>0){
										str+='<td class="text-center">'+result[i].sanctionpercentage+'</td>';
									}else{
										str+='<td class="text-center"> - </td>';
									}	
							/*		if(result[i].grounded !=null && result[i].grounded>0){
										str+='<td class="text-center">'+result[i].grounded+'</td>';
									}else{
										str+='<td class="text-center"> - </td>';
									}
									if(result[i].noTGrounded !=null && result[i].noTGrounded>0){
										str+='<td class="text-center">'+result[i].noTGrounded+'</td>';
									}else{
										str+='<td class="text-center"> - </td>';
									}	
									*/
									if(result[i].notStarted !=null && result[i].notStarted>0){
										str+='<td class="text-center">'+result[i].notStarted+'</td>';
									}else{
										str+='<td class="text-center"> - </td>';
									}
							
									if(result[i].notStaredPercentage !=null && result[i].notStaredPercentage>0){
										str+='<td class="text-center">'+result[i].notStaredPercentage+'</td>';
									}else{
										str+='<td class="text-center"> - </td>';
									}
									if(result[i].inProgress !=null && result[i].inProgress>0){
										str+='<td class="text-center">'+result[i].inProgress+'</td>';
									}else{
										str+='<td class="text-center"> - </td>';
									}
									if(result[i].inProgressPerc !=null && result[i].inProgressPerc>0){
										str+='<td class="text-center">'+result[i].inProgressPerc+'</td>';
									}else{
										str+='<td class="text-center"> - </td>';
									}
									if(result[i].completed !=null && result[i].completed>0){
										str+='<td class="text-center">'+result[i].completed+'</td>';
									}else{
										str+='<td class="text-center"> - </td>';
									}
									if(result[i].completedPerce !=null && result[i].completedPerce>0){
										str+='<td class="text-center">'+result[i].completedPerce+'</td>';
									}else{
										str+='<td class="text-center"> - </td>';
									}
									if(result[i].percentage !=null && parseFloat(result[i].percentage)>=0){
										if(parseFloat(result[i].percentage) >= 100){
											str+='<td style="background-color:#f7b519;color:#fff">'+result[i].percentage+'</td>';
										}else if(parseFloat(result[i].percentage) >= 90 && parseFloat(result[i].percentage) < 100){
											str+='<td style="background-color:#00AF50;color:#fff">'+result[i].percentage+'</td>';
										}else if(parseFloat(result[i].percentage) >= 60 && parseFloat(result[i].percentage) < 90){
											str+='<td style="background-color:#ff6600;color:#fff">'+result[i].percentage+'</td>';
										}else{
											str+='<td style="background-color:#FF0000;color:#fff">'+result[i].percentage+'</td>';
										}
									}else{
										str+='<td class="text-center"> - </td>';
									}
									
									
									/* if(result[i].percentage !=null && parseFloat(result[i].percentage)>=0){
										if(parseFloat(result[i].percentage)>=80){// && parseFloat(result[i].percentage) <=100
											str+='<td class="text-center" style="background-color:#009587;color:#fff">'+result[i].percentage+'</td>';
										}else if(parseFloat(result[i].percentage) >=60 && parseFloat(result[i].percentage)<80){
											str+='<td class="text-center" style="background-color:#99B95F;color:#fff">'+result[i].percentage+'</td>';
										}else if(parseFloat(result[i].percentage) >=40 && parseFloat(result[i].percentage)<60){
											str+='<td class="text-center" style="background-color:#E67401;color:#fff">'+result[i].percentage+'</td>';
										}else if(parseFloat(result[i].percentage) >=0 && parseFloat(result[i].percentage)<40){
											str+='<td class="text-center" style="background-color:#FD403A;color:#fff">'+result[i].percentage+'</td>';
										}else{
											str+='<td class="text-center">'+result[i].percentage+' </td>';
										}
									}else{
										str+='<td class="text-center"> - </td>';
									} */
								}else if(reportType == "daily"){
									if(result[i].target !=null && result[i].target>0){
										str+='<td class="text-center">'+result[i].target+'</td>';
									}else{
										str+='<td class="text-center"> - </td>';
									}
									
									if(result[i].completed !=null && result[i].completed>0){
										
										if(parseFloat(result[i].percentage) >= 100){
											str+='<td style="background-color:#f7b519;color:#fff">'+result[i].percentage+'</td>';
										}else if(parseFloat(result[i].percentage) >= 90 && parseFloat(result[i].percentage) < 100){
											str+='<td style="background-color:#00AF50;color:#fff">'+result[i].percentage+'</td>';
										}else if(parseFloat(result[i].percentage) >= 60 && parseFloat(result[i].percentage) < 90){
											str+='<td style="background-color:#ff6600;color:#fff">'+result[i].percentage+'</td>';
										}else{
											str+='<td style="background-color:#FF0000;color:#fff">'+result[i].percentage+'</td>';
										}
										
										/* if(parseFloat(result[i].percentage) >=80){//&& parseFloat(result[i].percentage)<=100
											str+='<td class="text-center" style="background-color:#009587;color:#fff">'+result[i].completed+'</td>';
										}else if(parseFloat(result[i].percentage) >=60 && parseFloat(result[i].percentage)<80){
											str+='<td class="text-center" style="background-color:#99B95F;color:#fff">'+result[i].completed+'</td>';
										}else if(parseFloat(result[i].percentage) >=40 && parseFloat(result[i].percentage)<60){
											str+='<td class="text-center" style="background-color:#E67401;color:#fff">'+result[i].completed+'</td>';
										}else if(parseFloat(result[i].percentage) >=0 && parseFloat(result[i].percentage)<40){
											str+='<td class="text-center" style="background-color:#FD403A;color:#fff">'+result[i].completed+'</td>';
										}else{
											str+='<td class="text-center">'+result[i].completed+'</td>';
										} */
									}else{
										str+='<td class="text-center"> - </td>';
									}
									
									if(result[i].percentage !=null && parseFloat(result[i].percentage)>=0){
										if(parseFloat(result[i].percentage) >= 100){
											str+='<td style="background-color:#f7b519;color:#fff">'+result[i].percentage+'</td>';
										}else if(parseFloat(result[i].percentage) >= 90 && parseFloat(result[i].percentage) < 100){
											str+='<td style="background-color:#00AF50;color:#fff">'+result[i].percentage+'</td>';
										}else if(parseFloat(result[i].percentage) >= 60 && parseFloat(result[i].percentage) < 90){
											str+='<td style="background-color:#ff6600;color:#fff">'+result[i].percentage+'</td>';
										}else{
											str+='<td style="background-color:#FF0000;color:#fff">'+result[i].percentage+'</td>';
										}
										
										/* if(parseFloat(result[i].percentage)>=80){//&& parseFloat(result[i].percentage) <=100
											str+='<td class="text-center" style="background-color:#009587;color:#fff">'+result[i].percentage+'</td>';
										}else if(parseFloat(result[i].percentage) >=60 && parseFloat(result[i].percentage)<80){
											str+='<td class="text-center" style="background-color:#99B95F;color:#fff">'+result[i].percentage+'</td>';
										}else if(parseFloat(result[i].percentage) >=40 && parseFloat(result[i].percentage)<60){
											str+='<td class="text-center" style="background-color:#E67401;color:#fff">'+result[i].percentage+'</td>';
										}else if(parseFloat(result[i].percentage) >=0 && parseFloat(result[i].percentage)<40){
											str+='<td class="text-center" style="background-color:#FD403A;color:#fff">'+result[i].percentage+'</td>';
										}else{
											str+='<td class="text-center">'+result[i].percentage+' </td>';
										} */
									}else{
										str+='<td class="text-center"> - </td>';
									}
									
									
									
									for(var j in result[i].subList){
										if(result[i].subList[j].target !=null && result[i].subList[j].target>0){
											str+='<td class="text-center">'+result[i].subList[j].target+'</td>';
										}else{
											str+='<td class="text-center"> - </td>';
										}
										if(result[i].subList[j].completed !=null && result[i].subList[j].completed>0){
											str+='<td class="text-center">'+result[i].subList[j].completed+'</td>';
										}else{
											str+='<td class="text-center"> - </td>';
										}
										if(result[i].subList[j].percentage !=null && result[i].subList[j].percentage>0){
											str+='<td class="text-center">'+result[i].subList[j].percentage+'</td>';
										}else{
											str+='<td class="text-center"> - </td>';
										}
									}
									
								}
							
							str+='</tr>';	
					}
					
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
		
		$("#IHHL"+subLocation).html(str);
		if (subLocation =="district") {
			if(reportType == "status"){
			$("#dataTable"+subLocation).dataTable({
				"iDisplayLength": 15,
				"aaSorting": [],
				"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
			});
		   }else if(reportType == "daily"){
			$("#dataTableDaily"+subLocation).dataTable({
				"iDisplayLength": 15,
				"aaSorting": [],
				"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
				
			});
		  }
		} else {
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
			getSwachhBharatMissionLocationWiseDetails(levelWiseSBArr[i],"daily",'week');
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
		if(displayType =='custom'){
			displayType='week';
		}
	});
	
	if(reportType == "status"){
		$(".calendar_active_IHHL_cls").hide();
		$(".ihhlAchivementProgressCls").show();
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
	var levelType = $(this).closest("ul").attr("attr_level_type");
	
	var displayType ='';
	if($(this).hasClass("active")){
		displayType = $(this).attr("attr_val");
	}
	if (displayType == "view") {
		return;
	}
	
	if(levelType == "table"){
		if(displayType !="custom"){
			
			for(var i in levelWiseSBArr){
				getSwachhBharatMissionLocationWiseDetails(levelWiseSBArr[i],"daily",displayType);
			}
		}
	}else{
		getIHHLAchivementProgressDtls(displayType);
	}	
});
$(document).on("click",".categoryCls",function() {
		var categoryType = $(this).attr("attr_category_type");
		var locationType = $(this).attr("attr_location_type");
		var reportType = $(this).attr("attr_report_type");
		$("#categoryWiseAnalysisModalDivId").modal('show');
		$("#modalHeadingId").html(locationType.toUpperCase()+" DETAILS");
		$("#categoryWiseAnalysisTableDivId").html(spinner);
	    getLocationDetailsBasedOnCategory(categoryType,locationType,reportType);
});

function getLocationDetailsBasedOnCategory(categoryType,locationType,reportType){
	var json = {
		fromDate:globalFromDateForLevel,
		toDate:globalToDateForLevel,
		location:"state",
		locationId:"-1",
		subLocation:locationType, 
		displayType:categoryType,
		reportType:reportType,
	}
	$.ajax({                
		type:'POST',    
		url: 'getLocationDetailsBasedOnCategory',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		buildTableDetailsData(result,locationType,reportType);
	});	
}

 function buildTableDetailsData(result,subLocation,reportType){
	 if(result !=null && result.length>0){
		     var str='';
		     str+='<div class="table-responsive">';
		     str+='<table class="table table-bordered" id="locationWiseDataTblId">';
			 str+='<thead>';
				str+='<tr class="text-capital">';
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
						if (reportType != null && reportType=="status") {
							str+='<th>Target</th>';	
							str+='<th class="sacnotsacClass">NOT SANCTIONED</th>';	
							str+='<th class="sacnotsacClass">%</th>';
							str+='<th class="sacnotsacClass">SANCTIONED</th>';	
							str+='<th class="sacnotsacClass">%</th>';
							str+='<th class="notstartinprogresscomClass">NOT STARTED</th>';	
							str+='<th  class="notstartinprogresscomClass">%</th>';	
							//str+='<th>Grounded</th>';	
							//str+='<th>Not Grounded</th>';	
							str+='<th  class="notstartinprogresscomClass">In Progress</th>';
							str+='<th  class="notstartinprogresscomClass">%</th>';							
							str+='<th  class="notstartinprogresscomClass">Completed</th>';	
							str+='<th  class="notstartinprogresscomClass">%</th>';
							str+='<th>Achievement %</th>';
						} else {
							str+='<th>Target</th>';	
							str+='<th>Completed</th>';	
							str+='<th>Achievement %</th>';
						}
						
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
				for(var i in result){
					   str+='<tr class="text-capital">';	
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
						if (reportType != null && reportType=="status") {
							if(result[i].target !=null && result[i].target>0){
								str+='<td class="text-center">'+result[i].target+'</td>';
							}else{
								str+='<td class="text-center"> - </td>';
							}	
							if(result[i].notSanctioned !=null && result[i].notSanctioned>0){
								str+='<td class="text-center">'+result[i].notSanctioned+'</td>';
							}else{
								str+='<td class="text-center"> - </td>';
							}	
							if(result[i].notSanctionPercentage !=null && result[i].notSanctionPercentage>0){
								str+='<td class="text-center">'+result[i].notSanctionPercentage+'</td>';
							}else{
								str+='<td class="text-center"> - </td>';
							}	
							if(result[i].sanctioned !=null && result[i].sanctioned>0){
								str+='<td class="text-center">'+result[i].sanctioned+'</td>';
							}else{
								str+='<td class="text-center"> - </td>';
							}	
							if(result[i].sanctionpercentage !=null && result[i].sanctionpercentage>0){
								str+='<td class="text-center">'+result[i].sanctionpercentage+'</td>';
							}else{
								str+='<td class="text-center"> - </td>';
							}	
					/*		if(result[i].grounded !=null && result[i].grounded>0){
								str+='<td class="text-center">'+result[i].grounded+'</td>';
							}else{
								//str+='<td class="text-center"> - </td>';
							}
							if(result[i].noTGrounded !=null && result[i].noTGrounded>0){
								str+='<td class="text-center">'+result[i].noTGrounded+'</td>';
							}else{
								str+='<td class="text-center"> - </td>';
							}	*/
							if(result[i].notStarted !=null && result[i].notStarted>0){
								str+='<td class="text-center">'+result[i].notStarted+'</td>';
							}else{
								str+='<td class="text-center"> - </td>';
							}
							if(result[i].notStaredPercentage !=null && result[i].notStaredPercentage>0){
								str+='<td class="text-center">'+result[i].notStaredPercentage+'</td>';
							}else{
								str+='<td class="text-center"> - </td>';
							}
							if(result[i].inProgress !=null && result[i].inProgress>0){
								str+='<td class="text-center">'+result[i].inProgress+'</td>';
							}else{
								str+='<td class="text-center"> - </td>';
							}
							if(result[i].inProgressPerc !=null && result[i].inProgressPerc>0){
								str+='<td class="text-center">'+result[i].inProgressPerc+'</td>';
							}else{
								str+='<td class="text-center"> - </td>';
							}
							if(result[i].completed !=null && result[i].completed>0){
								str+='<td class="text-center">'+result[i].completed+'</td>';
							}else{
								str+='<td class="text-center"> - </td>';
							}
							if(result[i].completedPerce !=null && result[i].completedPerce>0){
								str+='<td class="text-center">'+result[i].completedPerce+'</td>';
							}else{
								str+='<td class="text-center"> - </td>';
							}
							if(result[i].percentage !=null && parseFloat(result[i].percentage)>=0){
								if(parseFloat(result[i].percentage) >= 100){
									str+='<td style="background-color:#f7b519;color:#fff">'+result[i].percentage+'</td>';
								}else if(parseFloat(result[i].percentage) >= 90 && parseFloat(result[i].percentage) < 100){
									str+='<td style="background-color:#00AF50;color:#fff">'+result[i].percentage+'</td>';
								}else if(parseFloat(result[i].percentage) >= 60 && parseFloat(result[i].percentage) < 90){
									str+='<td style="background-color:#ff6600;color:#fff">'+result[i].percentage+'</td>';
								}else{
									str+='<td style="background-color:#FF0000;color:#fff">'+result[i].percentage+'</td>';
								}
								
								/* if(parseFloat(result[i].percentage)>=80 ){//&& parseFloat(result[i].percentage) <=100
									str+='<td class="text-center" style="background-color:#009587;color:#fff">'+result[i].percentage+'</td>';
								}else if(parseFloat(result[i].percentage) >=60 && parseFloat(result[i].percentage)<80){
									str+='<td class="text-center" style="background-color:#99B95F;color:#fff">'+result[i].percentage+'</td>';
								}else if(parseFloat(result[i].percentage) >=40 && parseFloat(result[i].percentage)<60){
									str+='<td class="text-center" style="background-color:#E67401;color:#fff">'+result[i].percentage+'</td>';
								}else if(parseFloat(result[i].percentage) >=0 && parseFloat(result[i].percentage)<40){
									str+='<td class="text-center" style="background-color:#FD403A;color:#fff">'+result[i].percentage+'</td>';
								}else {
									str+='<td class="text-center">'+result[i].percentage+' </td>';
								} */
							}else{
								str+='<td class="text-center"> - </td>';
							}
								str+='</tr>';
						} else {
							if(result[i].target !=null && result[i].target>0){
								str+='<td class="text-center">'+result[i].target+'</td>';
							}else{
								str+='<td class="text-center"> - </td>';
							}	
							if(result[i].completed !=null && result[i].completed>0){
								str+='<td class="text-center">'+result[i].completed+'</td>';
							}else{
								str+='<td class="text-center"> - </td>';
							}
							if(result[i].percentage !=null && parseFloat(result[i].percentage)>=0){
								if(parseFloat(result[i].percentage) >= 100){
									str+='<td style="background-color:#f7b519;color:#fff">'+result[i].percentage+'</td>';
								}else if(parseFloat(result[i].percentage) >= 90 && parseFloat(result[i].percentage) < 100){
									str+='<td style="background-color:#00AF50;color:#fff">'+result[i].percentage+'</td>';
								}else if(parseFloat(result[i].percentage) >= 60 && parseFloat(result[i].percentage) < 90){
									str+='<td style="background-color:#ff6600;color:#fff">'+result[i].percentage+'</td>';
								}else{
									str+='<td style="background-color:#FF0000;color:#fff">'+result[i].percentage+'</td>';
								}
								
								/* if(parseFloat(result[i].percentage)>=80 ){//&& parseFloat(result[i].percentage) <=100
									str+='<td class="text-center" style="background-color:#009587;color:#fff">'+result[i].percentage+'</td>';
								}else if(parseFloat(result[i].percentage) >=60 && parseFloat(result[i].percentage)<80){
									str+='<td class="text-center" style="background-color:#99B95F;color:#fff">'+result[i].percentage+'</td>';
								}else if(parseFloat(result[i].percentage) >=40 && parseFloat(result[i].percentage)<60){
									str+='<td class="text-center" style="background-color:#E67401;color:#fff">'+result[i].percentage+'</td>';
								}else if(parseFloat(result[i].percentage) >=0 && parseFloat(result[i].percentage)<40){
									str+='<td class="text-center" style="background-color:#FD403A;color:#fff">'+result[i].percentage+'</td>';
								}else {
									str+='<td class="text-center">'+result[i].percentage+' </td>';
								} */
							}else{
								str+='<td class="text-center"> - </td>';
							}
								str+='</tr>';
						}
							
						
					}
					str+='</tbody>';
					str+='</table>';
					str+='</div>';
					$("#categoryWiseAnalysisTableDivId").html(str);
					$("#locationWiseDataTblId").dataTable({
						"iDisplayLength": 10,
						"aaSorting": [],
						"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
					}); 
		}else{
			$("#categoryWiseAnalysisTableDivId").html("NO DATA AVAILABLE.");
		}
	}
  
  function getIHHLCategoryWiseAnalysisBySelectedDate(){
	  $("#selectedDatecategoryWiseDataId").html(spinner);
		var json = {
			fromDate:globalFromDateForLevel,
			toDate:globalToDateForLevel,
			location:"state",
			locationId:"-1",
			displayType:"day",
			reportType:"daily",
			subLocation:"state"
		}
		$.ajax({                
			type:'POST',    
			url: 'getIHHLCategoryWiseAnalysisBySelectedDate',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			if (result != null && result.length > 0) {
				buildCateggoryWiseAnalysisBasedOnSelectedDate(result);
			} else {
				$("#selectedDatecategoryWiseDataId").html("NO DATA AVAILABLE.");
			}
		});	
		
	function buildCateggoryWiseAnalysisBasedOnSelectedDate(result) {
		 var str='';
		if(result !=null && result.length>0){
			for(var i in result){
				str+='<div class="col-sm-3 m_top10">';
					str+='<div class="panel panel-default">';
						str+='<div class="panel-heading" style="padding: 3px;background-color:#fff;">';
							 str+='<div class="row" style="color:'+globalGradientsColorArr[result[i].name.trim()]+'>';
								str+='<div class="col-sm-2 m_top10">';
									str+='<span class="categoryRondedCss" style="background-color:'+globalColor[result[i].name.trim()]+';">'+result[i].name+'</span>';
								str+='</div>';
								/*str+='<div class="col-sm-10">';
									str+='<p class="text-right" style="font-size: 16px;color:'+globalColor[result[i].name.trim()]+'">'+result[i].range+'</p>';
									str+='<h5 class="panel-title text-right" style="color:'+globalColor[result[i].name.trim()]+'">Achivement</h5>';
								str+='</div>';*/
							str+='</div>'; 
						str+='</div>';
						str+='<div class="panel-body">';
							str+='<ul class="list-inline borderleft">';
							if (result[i].districtCount != null && result[i].districtCount > 0) {
								str+='<li style="cursor:pointer;" attr_report_type="daily" attr_category_type="'+result[i].name.trim()+'" attr_location_type="district" class="categoryCls"><h3><span style="color:'+globalColor[result[i].name.trim()]+'">D</span> - '+result[i].districtCount+'</h3></li>';
							} else {
								str+='<li><h3><span style="color:'+globalColor[result[i].name.trim()]+'">D</span> - '+result[i].districtCount+'</h3></li>';
							}
							if (result[i].constituencyCount != null && result[i].constituencyCount > 0) {
								str+='<li attr_report_type="daily" style="cursor:pointer;" attr_category_type="'+result[i].name.trim()+'" attr_location_type="constituency" class="categoryCls"><h3><span style="color:'+globalColor[result[i].name.trim()]+'">C</span> - '+result[i].constituencyCount+'</h3></li>';
							} else {
								str+='<li><h3><span style="color:'+globalColor[result[i].name.trim()]+'">C</span> - '+result[i].constituencyCount+'</h3></li>';
							}
							if (result[i].mandalCount != null && result[i].mandalCount > 0) {
								str+='<li attr_report_type="daily" style="cursor:pointer;" attr_category_type="'+result[i].name.trim()+'" attr_location_type="mandal" class="categoryCls"><h3><span style="color:'+globalColor[result[i].name.trim()]+'">M</span> - '+result[i].mandalCount+'</h3></li>';
							} else {
								str+='<li><h3><span style="color:'+globalColor[result[i].name.trim()]+'">M</span> - '+result[i].mandalCount+'</h3></li>';
							}
							str+='<ul>';
						str+='</div>';	
					str+='</div>';
				str+='</div>';
			}
		}
		$("#selectedDatecategoryWiseDataId").html(str);
	}
}
function formDateInRequiredFormat(date) {
	var dateArr = date.split("-");
	return dateArr[2]+"-"+dateArr[1]+"-"+dateArr[0];;
}