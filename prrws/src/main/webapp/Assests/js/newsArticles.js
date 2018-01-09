	var glStartDate = moment().subtract(20, 'years').startOf('year').format("DD-MM-YYYY");
	var glEndDate = moment().add(10, 'years').endOf('year').format("DD-MM-YYYY");
	var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
	
	$("header").on("click",".menu-cls",function(e){
		e.stopPropagation();
		$(".menu-data-cls").toggle();
	});
	$(document).on("click",function(){
		$(".menu-data-cls").hide();
	});
	
	$("#dateRangePickerAUM").daterangepicker({
		opens:'left',
		startDate: glStartDate,
		endDate: glEndDate,
		locale: {
			format: "DD-MM-YYYY",
		},
		ranges: {
		   'All':[moment().subtract(20, 'years').startOf('year').format("DD-MM-YYYY"), moment().add(10, 'years').endOf('year').format("DD-MM-YYYY")],
		   'Today' : [moment(), moment()],
		   'This Month': [moment().startOf('month'), moment()],
		   'This Year': [moment().startOf('Year'), moment()],
		   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		   'Last 3 Months': [moment().subtract(3, 'month'), moment()],
		   'Last 1 Year': [moment().subtract(1, 'Year'), moment()]
		  
		}
	});
	$('#dateRangePickerAUM').on('apply.daterangepicker', function(ev, picker) {
		startDate = picker.startDate.format("DD-MM-YYYY");
		endDate = picker.endDate.format("DD-MM-YYYY");
		onLoadCalls();
	});
	
	onloadCalls();
	
	function onloadCalls(){
		getPrintMediaCountsDetailsInfo();
		getDepartMentWiseAllNewsBulletinsAndPrograms();
		getDistrictWiseTotalOverViewInfo();
		getDepartmentWiSeBlockDetails();
	}
	
	 function getPrintMediaCountsDetailsInfo(){
		 $("#overAllPrintMediaDivId").html(spinner);
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getPrintMediaCountsDetailsInfo/"+glStartDate+"/"+glEndDate+"/"
			//url: "http://localhost:8085/CommunityNewsPortal/webservice/getPrintMediaCountsDetailsInfo/"+glStartDate+"/"+glEndDate+"/"
		}).then(function(result){
			if(result !=null && result.length>0){
				buildOverAllPrintMediaDetails(result,"PrintMedia","overAllPrintMediaDivId","overAll",0);
			}else{
				$("#overAllPrintMediaDivId").html("No Data Available");
			}
		});
	} 
	function getDepartMentWiseAllNewsBulletinsAndPrograms(){
		 $("#overAllElectronicMediaDivId").html(spinner);
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getDepartMentWiseAllNewsBulletinsAndPrograms/"+glStartDate+"/"+glEndDate+"/"
			//url: "http://localhost:8085/CommunityNewsPortal/webservice/getDepartMentWiseAllNewsBulletinsAndPrograms/"+glStartDate+"/"+glEndDate+"/All"
		}).then(function(result){
				if(result !=null){
					buildOverAllPrintMediaDetails(result,"ElectronicMedia","overAllElectronicMediaDivId","overAll",0);
				}else{
					$("#overAllElectronicMediaDivId").html("No Data Available");
				}
		});
	}
	function getDistrictWiseTotalOverViewInfo(){
		$("#overAllDistrictWiseDivId").html(spinner);
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getDistrictWiseTotalOverViewInfo/"+glStartDate+"/"+glEndDate+"/"
			//url: "http://localhost:8085/CommunityNewsPortal/webservice/getDistrictWiseTotalOverViewInfo/"+glStartDate+"/"+glEndDate+"/"
		}).then(function(result){
			if(result !=null){
					buildOverAllDistrictWiseDetails(result,"overAllDistrictWiseDivId");
			}else{
				$("#overAllDistrictWiseDivId").html("No Data Available");
			}
		});
	}
	function getDepartmentWiSeBlockDetails(){
		$("#departmentWiseDetailsDivId").html(spinner);
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getDepartmentWiSeBlockDetails/"+glStartDate+"/"+glEndDate+"/"
			//url: "http://localhost:8085/CommunityNewsPortal/webservice/getDepartmentWiSeBlockDetails/"+glStartDate+"/"+glEndDate+"/"
		}).then(function(result){
			if(result !=null && result.length>0){
				buildDepartmentWiSeBlockDetails(result);
			}else{
				$("#departmentWiseDetailsDivId").html("No Data Available");
			}
		});
	}
function highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip,colors,title){
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
function highchartsPieChart(id,type,data,plotOptions,title,tooltip,legend){
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
function buildOverAllPrintMediaDetails(result,typeval,divId,departmentType,departmentId){
	var str='';
	var categoryNamesArr=['Total','Positive','Negative'];
	var dataArr=[];
	var totalArr = [];
	var positivePercArr = [];
	var negativePercArr = [];
	var totalCount=0;
	var totalPosCount=0;
	var totalNegCount=0;
	var totalPosPerc=0;
	var totalNegperc=0;
	for(var i in result){
		totalCount = totalCount+result[i].count;
		totalPosCount = totalPosCount+result[i].positiveCountMain;
		totalNegCount = totalNegCount+result[i].negativCountMain;
		totalPosPerc = (totalPosCount/totalCount*100).toFixed(0);
		totalNegperc = (totalNegCount/totalCount*100).toFixed(0);
	}
	
	str+='<div class="row m_top10">';
		str+='<div class="col-sm-5 border_right_yash">';
			str+='<div id="overAll'+typeval+'GraphDivId'+departmentId+'" style="height:250px;"></div>';
		str+='</div>';
		str+='<div class="col-sm-7">';
			str+='<div class="row">';
				for(var i in result){
					if(i==0){
						str+='<div class="col-sm-6 border_right_yash">';
					}else{
						str+='<div class="col-sm-6">';
					}
					
						str+='<div id="overAll'+typeval+'MainDistEditionDivId'+i+''+departmentId+'" style="height:200px;"></div>';
						str+='<div class="row">';
							str+='<div class="col-sm-6">';
								str+='<h5 class="font_weight">Positive</h5>';
								str+='<h5 class="font_weight m_top5" style="font-size:12px;">'+result[i].positiveCountMain+'(<span style="color:#63C563">'+result[i].positivePerc.toFixed(0)+'%</span>)</h5>';
							str+='</div>';
							str+='<div class="col-sm-6">';
								str+='<h5 class="font_weight">Negative</h5>';
								str+='<h5 class="font_weight m_top5" style="font-size:12px;">'+result[i].negativCountMain+'(<span style="color:#EB2F2F">'+result[i].negativePerc.toFixed(0)+'%</span>)</h5>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				}
			str+='</div>';
		str+='</div>';
	str+='</div>';
	$("#"+divId).html(str);
	
	
	
	
	totalArr.push(parseInt(totalCount));
	positivePercArr.push(parseInt(totalPosCount));
	negativePercArr.push(parseInt(totalNegCount));
	dataArr.push(totalArr);
	dataArr.push(positivePercArr);
	dataArr.push(negativePercArr);
	
	var colors = ['#343B54','#63C563','#EB2F2F']
	var id = 'overAll'+typeval+'GraphDivId'+departmentId+'';
	var type = {
		type: 'column',
		backgroundColor:'transparent'
	};
	var title = {
		text: '',
		
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
		categories: categoryNamesArr
	};
	
	var plotOptions ={ column: {
			colorByPoint: true
		}};
	var tooltip = {
		useHTML:true,
		formatter: function () {
			return '<b>' + this.x + '</b><br/>' +
				this.y+'';
		}
	};

	var data = [{
		name: '',
		data: dataArr,

		dataLabels: {
			useHTML:true,
			enabled: true,
			color: '#000',
			align: 'center',
			formatter: function() {
					return '<span>'+this.y+'</span>';
			} 
		}
	}];
	highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip,colors,title);
	for(var i in result){
		
		var postiveCountPerc = result[i].positivePerc;
		var negativCountPerc = result[i].negativePerc;
		var totalCount=result[i].positiveCountMain+result[i].negativCountMain;
		var headingCountPrint='';
		if(departmentType == "overAll"){
			if(typeval == "PrintMedia"){
				if(result[i].organization == "Main"){
					headingCountPrint+='<h5><span>'+result[i].organization+' Edition</span><br><b>'+totalCount+' ('+totalPosPerc+'%)</b></h5>';
				}else if(result[i].organization == "District"){
					headingCountPrint+='<h5><span>'+result[i].organization+' Edition</span><br><b>'+totalCount+' ('+totalNegperc+'%)</b></h5>';
				}
			}else if(typeval == "ElectronicMedia"){
				if(result[i].organization == "News Bulletin"){
					headingCountPrint+='<h5><span>'+result[i].organization+'</span><br><b>'+totalCount+' ('+totalPosPerc+'%)</b></h5>';
				}else if(result[i].organization == "News Program"){
					headingCountPrint+='<h5><span>'+result[i].organization+'</span><br><b>'+totalCount+' ('+totalNegperc+'%)</b></h5>';
				}
			}
		}else{
			if(typeval == "PrintMediadepartment"+departmentId+""){
				if(result[i].organization == "Main"){
					headingCountPrint+='<h5><span>'+result[i].organization+' Edition</span><br><b>'+totalCount+' ('+totalPosPerc+'%)</b></h5>';
				}else if(result[i].organization == "District"){
					headingCountPrint+='<h5><span>'+result[i].organization+' Edition</span><br><b>'+totalCount+' ('+totalNegperc+'%)</b></h5>';
				}
			}else if(typeval == "ElectronicMediadepartment"+departmentId+""){
				if(result[i].organization == "News Bulletin"){
					headingCountPrint+='<h5><span>'+result[i].organization+'</span><br><b>'+totalCount+' ('+totalPosPerc+'%)</b></h5>';
				}else if(result[i].organization == "News Program"){
					headingCountPrint+='<h5><span>'+result[i].organization+'</span><br><b>'+totalCount+' ('+totalNegperc+'%)</b></h5>';
				}
			}
		}
		
		
		
		var id = 'overAll'+typeval+'MainDistEditionDivId'+i+departmentId;
		var type = {
			type: 'pie',
			backgroundColor:'transparent',
			options3d: {
				enabled: true,
				alpha: 25
			}
		};
		var title = {
			text: headingCountPrint,
			align:'center',
			 style: {
				 color: '#000',
				 font: '12px "Lato", sans-serif'
			  }
		};
		
		
		
		var tooltip = {
			useHTML: true,
			backgroundColor: '#FCFFC5', 
			formatter: function() {
				var cnt = this.point.count;
				return "<b style='color:"+this.point.color+"'>"+this.point.name+" -<br/>("+(this.y)+"%)</b>";
			}  
		}; 
		var plotOptions ={
			pie: {
				innerSize: 80,
				depth: 40,
				dataLabels: {
					enabled: false,
					formatter: function() {
						return (this.y) + ' %';
					},
					distance: -10,
					color:'#333'
				},
				showInLegend: false
			},
		};
		var legend={enabled: false};
		var data = [{
			name: '',
			data: [
				{
				  name: 'Positive',
				  y: postiveCountPerc,
				  color:"#63C563"
				},
				{
				  name: 'Negative',
				  y: negativCountPerc,
				  color:"#EB2F2F"
				}
			]
		}];
		highchartsPieChart(id,type,data,plotOptions,title,tooltip,legend);
	}			
	
	
}
function buildOverAllDistrictWiseDetails(result,divId){
	var districtWiseNegativeCountArray=[];
	var districtWisePositiveCountArray=[];
	var districtNamesArray=[];
	for(var i in result){
		districtNamesArray.push(result[i].organization)
		districtWiseNegativeCountArray.push({y:result[i].negativePerc,"extra":result[i].positiveCountMain})
		districtWisePositiveCountArray.push({y:result[i].positivePerc,"extra":result[i].negativCountMain})
	}
	
	$('#'+divId).highcharts({
		colors: ['#64C664','#D33E39'],
		chart: {
			type: 'column'
		},
		title: {
			text: ''
		},
		xAxis: {
			 min: 0,
				 gridLineWidth: 0,
				 minorGridLineWidth: 0,
				categories: districtNamesArray,
			labels: {
					rotation: -45,
					style: {
						fontSize: '12px',
						fontFamily: 'Verdana, sans-serif'
					}
				}
		},
		yAxis: {
			min: 0,
				   gridLineWidth: 0,
					minorGridLineWidth: 0,
			title: {
				text: ''
			},
			stackLabels: {
				enabled: false,
				style: {
					fontWeight: 'bold',
					color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
				}
			}
		},
		legend: {
			enabled: true,
			/* //align: 'right',
			x: -40,
			y: 30,
			verticalAlign: 'top',
			//y: -32,
			floating: true, */
			backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
			borderColor: '#CCC',
			borderWidth: 1,
			shadow: false
		},
		tooltip: {
			formatter: function () {
				var s = '<b>' + this.x + '</b>';

				$.each(this.points, function () {
					s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
						(this.y)+'%'+' - ' +
						(this.point.extra);
				});

				return s;
			},
			shared: true
		},
		
		plotOptions: {
			pointPadding: 0.2,
			borderWidth: 2,
			groupPadding: 0.2,
			column: {
				stacking: 'percent',
				dataLabels: {
					enabled: true,
					 formatter: function() {
						if (this.y === 0) {
							return null;
						} else {
							return (this.y) +'%';
						}
					}
				  
				}
			},
		},
		series: [{
			name: 'Positive',
			data: districtWisePositiveCountArray
		}, {
			name: 'Negative',
			data: districtWiseNegativeCountArray
		}]
	});
}
function buildDepartmentWiSeBlockDetails(result){
	
	var collapse='';
	collapse+='<div class="row m_top20">';
		collapse+='<div class="col-sm-12">';
			collapse+='<div class="white_block">';
	for(var i in result){
				collapse+='<div class="panel-group" id="accordion'+i+'" role="tablist" aria-multiselectable="true">';
					collapse+='<div class="panel panel-default panel-white">';
						collapse+='<div class="panel-heading" role="tab" id="heading'+i+'">';
							if(i == 0)
							{
								collapse+='<a role="button" class="panelCollapseIconChangePE"  data-toggle="collapse" data-parent="#accordion'+i+'" href="#collapse'+i+'" aria-expanded="true" aria-controls="collapse'+i+'" style="margin-top:0px !important;">';
							}else{
								collapse+='<a role="button" class="panelCollapseIconChangePE collapsed"  data-toggle="collapse" data-parent="#accordion'+i+'" href="#collapse'+i+'" aria-expanded="true" aria-controls="collapse'+i+'" style="margin-top:0px !important;">';
							}
							collapse+='<h4 class="panel-title text-capital">'+result[i].organization+'</h4>';
								
							collapse+='</a>';
						collapse+='</div>';
						if(i == 0)
						{
							collapse+='<div id="collapse'+i+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+i+'">';
						}else{
							collapse+='<div id="collapse'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+i+'">';
						}
						
							collapse+='<div class="panel-body">';
							for(var j in result[i].coreDashBoardVOList){
								collapse+='<div class="row">';
									collapse+='<div class="col-sm-6">';
										collapse+='<div class="pad_light_yash_bg border_yash">';
											collapse+='<div id="printMedia'+result[i].organizationId+'"></div>';
										collapse+='</div>';
									collapse+='</div>';
									collapse+='<div class="col-sm-6">';
										collapse+='<div class="pad_light_yash_bg border_yash">';
											collapse+='<div id="electronicMedia'+result[i].organizationId+'"></div>';
										collapse+='</div>';
									collapse+='</div>';
								collapse+='</div>';
								
								collapse+='<div class="row m_top20">';
									collapse+='<h4 class="font_weight">District wise Overview</h4>';
									collapse+='<div id="districtWise'+result[i].organizationId+'" class="m_top10" style="height:250px;"></div>';
								collapse+='</div>';
							}	
								
							collapse+='</div>';
						collapse+='</div>';
					collapse+='</div>';
				collapse+='</div>';
				
		}
			collapse+='</div>';
		collapse+='</div>';
	collapse+='</div>';
	$("#departmentWiseDetailsDivId").html(collapse);
	for(var i in result){
		for(var j in result[i].coreDashBoardVOList1){
			//DistrictCall
			buildOverAllDistrictWiseDetails(result[i].coreDashBoardVOList1[j].coreDashBoardVOList,'districtWise'+result[i].organizationId+'')
		}
		
		for(var j in result[i].coreDashBoardVOList){
			//printMediaCall
			buildOverAllPrintMediaDetails(result[i].coreDashBoardVOList[j].coreDashBoardVOList,'PrintMediadepartment'+result[i].organizationId+'','printMedia'+result[i].organizationId+'',"department",result[i].organizationId)
		}
		for(var j in result[i].coreDashBoardVOList3){
			//ElectronicCall
			buildOverAllPrintMediaDetails(result[i].coreDashBoardVOList3[j].coreDashBoardVOList,'ElectronicMediadepartment'+result[i].organizationId+'','electronicMedia'+result[i].organizationId+'',"departmentEle",result[i].organizationId)
			
		}
	}
	
	
}