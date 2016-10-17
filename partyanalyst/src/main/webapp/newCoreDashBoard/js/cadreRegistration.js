
$(document).ready(function() {
    initialiseDatePickerForCadreRegistration();
});

function initialiseDatePickerForCadreRegistration(){
		$("#dateRangeIdForCadre").daterangepicker({
			opens: 'right',
			startDate: moment(),
			endDate: moment(),
			locale: {
			  format: 'DD-MM-YYYY'
			},
		})
}

$(document).on("click",".cadreExpand",function(){
	$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
	$(".cadreBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	$(".cadreBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
	setTimeout(function(){
		$(".moreCadreBlock,.moreBlocksCadreIcon").toggle();
		//getSpokesPersonWiseDebate("top");
	},800);
	getUserTypeWiseTotalCadreRegistrationCount();
	//getRegistrationCountDtls("booth","overall"); 
	$("#constituencySeletBoxId").val(0);
	if( !$(this).find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		setTimeout(function(){
			$(".moreCadreBlock,.moreBlocksCadreIcon,.showTabUserWiseDetails").hide();
		},1000);		
	}else{
		
		//getSpokesPersonWiseDebate("top");
	}
	if( $(".iconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".iconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".committeesHiddenBlock,.moreBlocks,.moreBlocks1,.moreBlocksDetailAndComp,.moreBlocksIcon,.moreBlocksDistrictlevel").hide();
		$(".committeesBlock,.basicCommitteesBlock,.userTypeCommitteesBlock,.committeesBlock1").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".dateRangePickerCls").toggleClass("hide");
		$(".moreBlocksIcon").removeClass("unExpandBlock");
	}else if( $(".trainingIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".trainingIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".trainingsHiddenBlock,.moreTrainingBlocks,.moreTrainingBlocksIcon").hide();
		$(".moreTrainingBlocksIcon").removeClass("unExpandTrainingBlock");
		$(".trainingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}else if( $(".meetingsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".meetingsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".meetingsHiddenBlock,.moreMeetingsBlocksIcon").hide();
		$(".meetingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".dateRangePickerClsForMeetings").toggleClass("hide");
		$(".moreMeetingsBlocks1").hide();
		$(".moreMeetingsBlocksDetailed").hide();
		$(".moreMeetingsBlocksComparision").hide();
	}else if( $(".newsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".newsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".newsHiddenBlock,.morenewsBlocksIcon,.newsHiddenMoreBlock").hide();
		$(".newsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".dateRangePickerClsForNews").toggleClass("hide");
	}else if( $(".eventsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".eventsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".eventsHiddenBlock,.moreEventsBlocks,.comparisonBlockEvents,.detailedBlockEvents").hide();
		$(".eventsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".dateRangePickerClsForEvents").toggleClass("hide");
	}else if( $(".debatesIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".debatesIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".debatesMoreHiddenBlock,.debatesHiddenBlock,.dateRangePickerClsForDebates").hide();
		$(".moreDebatesBlocksIcon").removeClass("unExpandDebatesBlock");
		$(".debatesBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}else if( $(".attendaceIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".attendaceIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".attendanceBlockMore,.moreAttBlocks,.moreAttBlocksIcon").hide();
		$(".dateRangePickerClsForAttendance").toggleClass('hide');
		$(".attendanceBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}
});

$(document).on("click",".moreBlocksCadreIcon",function(){
	$(".moreBlocksCadre").toggle();
	var filterType="All";
	getCadreDetailsBasedOnUserType(filterType);
	var accessLevelId=0;
	var accessLevelValues=[];
	getTsDistrictWiseTsDetails(accessLevelId,accessLevelValues,filterType);
	getApConstituencyCadreRegistrationDetails(accessLevelId,accessLevelValues,"Y","Y","Y");
	getTsConstituencyCadreRegistrationDetails(accessLevelId,accessLevelValues,"Y","Y","Y");
})
$('#apRegistration').highcharts({

    chart: {
      type: 'column'
    },

    title: {
      text: null
    },

    xAxis: {
      categories: ['Srikakulam', 'Vizianagaram', 'Vishakapatnam', 'East Godavari', 'West Godavari']
    },

    yAxis: {
      allowDecimals: false,
      min: 0,
      title: {
        text: null
      }
    },

    tooltip: {
      formatter: function () {
        return '<b>' + this.x + '</b><br/>' +
          this.series.name + ': ' + this.y + '<br/>' +
          'Total: ' + this.point.stackTotal;
      }
    },

    plotOptions: {
      column: {
        stacking: 'normal'
      }
    },

    series: [{
      name: '2016 Renewal',
      data: [1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9],
      stack: 'Registration',
      color:'#F76800'
    }, {
      name: '2016 New',
      data: [9,8,7,6,5,4,0,2,1,0,9,8,7,6,5,4,3,2,1],
      stack: 'Registration',
      color:'#31AA74'
    }, {
      name: '2014',
      data: [8,8,8,8,8,8,12,8,8,8,8,8,8,15,8,8,8,8,8],
      stack: 'Old',
      color:'#FCCD00'
    }]
  });
$('#apRegistrationCons').highcharts({
    chart: {
      type: 'bar'
    },

    title: {
      text: null
    },

    xAxis: {
      categories: ['Srikakulam', 'Vizianagaram', 'Vishakapatnam', 'East Godavari', 'West Godavari']
    },

    yAxis: {
      allowDecimals: false,
      min: 0,
      title: {
        text: null
      }
    },

    tooltip: {
      formatter: function () {
        return '<b>' + this.x + '</b><br/>' +
          this.series.name + ': ' + this.y + '<br/>' +
          'Total: ' + this.point.stackTotal;
      }
    },

    plotOptions: {
      column: {
        stacking: 'normal'
      }
    },

   series: [{
      name: '2016 Renewal',
      data: [1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9],
      stack: 'Registration',
      color:'#F76800'
    }, {
      name: '2016 New',
      data: [9,8,7,6,5,4,0,2,1,0,9,8,7,6,5,4,3,2,1],
      stack: 'Registration',
      color:'#31AA74'
    }, {
      name: '2014',
      data: [8,8,8,8,8,8,12,8,8,8,8,8,8,15,8,8,8,8,8],
      stack: 'Old',
      color:'#FCCD00'
    }]
  });

$('#genSec').highcharts({
	colors: ['#0066DC'],
	chart: {
		type: 'column'
	},
	title: {
		text: null
	},
	subtitle: {
		text: null
	},
	xAxis: {
		categories: ['B Jaya Nageshwar Reddy', 'G Buchaiah Chowdary', 'Nimmala Ramanaidu', 'Reddy Subramanyam', 'Varla Ramaiah'],
		title: {
			text: null
		}
	},
	yAxis: {
		min: 0,
		title: {
			text: null,
			align: 'high'
		},
		labels: {
			overflow: 'justify',
			enabled: false,
		}
	},
	tooltip: {
		valueSuffix: '%'
	},
	plotOptions: {
		bar: {
			dataLabels: {
				enabled: true
			}
		}
	},
	legend: {
		layout: 'vertical',
		align: 'right',
		verticalAlign: 'top',
		x: -40,
		y: 80,
		floating: true,
		borderWidth: 1,
		backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
		shadow: true
	},
	credits: {
		enabled: false
	},
	series: [{
		name: 'Year 1800',
		data: [107, 31, 635, 203, 2]
	}]
});

	function cadreRegistrationBasicCall(globalActivityMemberId){
		showCadreRegistreredCount(globalActivityMemberId);
		getEnumeratorsInfo();
		getCadreRecentTime();  
	}

	//swadhin
	function showCadreRegistreredCount(globalActivityMemberId){ 
		
		$("#totalTodayCadreRegistrationBlockDivAPId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		
		var startDate = '';
		var endDate = '';  
		var jsObj={  
			activityMemberId : globalActivityMemberId,      
			stateId : globalStateId,           
			startDate : '02/10/2016',        
			endDate : '17/10/2016'
		};
		$.ajax({
			type : 'GET',
			url : 'getTotalNewRenewalCadreStateWiseAction.action',
			dataType : 'json',  
			data : {task :JSON.stringify(jsObj)}  
		}).done(function(result){
            if(result != null){
				buildTotalTodayRegistrationsAP(result);
				//buildTotalTodayRegistrationsTS(result);
			}else{
				$("#totalTodayCadreRegistrationBlockDivAPId").html('NO DATA AVAILABLE');
			}	
		});	
           
	}
	
	function buildTotalTodayRegistrationsAP(result){  
		
	 var str= '';
	 // Total today Registrations block
	  str+='<div class="row m_top10">';
			str+='<div class="col-md-6 col-xs-12 col-sm-12">';
				str+='<div class="bg_ED pad_15">';
					str+='<div class="row m_top10">';
						str+='<div class="col-md-5 col-xs-12 col-sm-12 pad_right0">';
							str+='<h5 class="text-capital">total</h5>';
							str+='<h3 class="cadreCount">'+emptyCheck(result.totalCount)+'</h3>';
						str+='</div>';
						str+='<div class="col-md-7 col-xs-12 col-sm-12 pad_left0">';
							str+='<h4 class="f_16 text-success">Renewal  <span class="pull-right cadreCount f_14">'+emptyCheck(result.renewalCount)+'</span></h4>';
							str+='<h4 class="f_16" style="color:#F7A423">New  <span class="pull-right cadreCount f_14">'+emptyCheck(result.newCount)+'</span></h4>';
						str+='</div>';
					str+='</div>';
					str+='<div id="totalOverAllRegistrationGraph" class="chartLiD" style="height:120px" ></div>'; 
				str+='</div>';
				
			str+='</div>';
			
			str+='<div class="col-md-6 col-xs-12 col-sm-12">';
				str+='<div class="bg_ED pad_15">';
					
					str+='<div class="row m_top10">';
						str+='<div class="col-md-5 col-xs-12 col-sm-12 pad_right0">';
							str+='<h5 class="text-capital">today</h5>';
							str+='<h3 class="cadreCount">'+emptyCheck(result.todayTotalCount)+'</h3>';
						str+='</div>';
						str+='<div class="col-md-7 col-xs-12 col-sm-12 pad_left0">';
							str+='<h4 class="f_16 text-success">Renewal  <span class="pull-right cadreCount f_14">'+emptyCheck(result.todayRenewalCount)+'</span></h4>';
							str+='<h4 class="f_16" style="color:#F7A423">New  <span class="pull-right cadreCount f_14">'+emptyCheck(result.todayNewCount)+'</span></h4>';
						str+='</div>';

					str+='</div>';
					str+='<div id="todayOverAllRegistrationGraph" class="chartLiD" style="height:120px" ></div>';
				str+='</div>';
				
			str+='</div>';
		str+='</div>';
		
		$("#totalTodayCadreRegistrationBlockDivAPId").html(str);
		$('.cadreCount').each(function () {
				$(this).prop('Counter',0).animate({
					Counter: $(this).text()
				}, {
					duration: 1500,
					easing: 'swing',
					step: function (now) {
						if(now != null && now > 0)
						$(this).text(Math.ceil(now));
					}
				});
			});
		//total block graph.
			var totalRenewalCountArray = [];
			var totalNewCountArray =[];
			totalRenewalCountArray.push(emptyCheck(result.renewalCount));
			totalNewCountArray.push(emptyCheck(result.newCount));
			
			$('#totalOverAllRegistrationGraph').highcharts({
				colors: ['#53BF8B','#f7a423'],
				chart: {
					backgroundColor: '#EDEEF0',
					type: 'column'
				},
				title: {
					text: ''
				},
				xAxis: {
					min: 0,
					 gridLineWidth: 0,  
					 minorGridLineWidth: 0,
					categories: ['Andhra Pradesh']
				},
				yAxis: {
					min: 0,
				   gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: ''
					},
					stackLabels: {
						enabled: true,
						style: {
							fontWeight: 'bold',
							color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
						}
					}
				},
				legend: {
					enabled: false,
					align: 'right',
					x: -30,
					verticalAlign: 'top',
					y: 25,
					floating: true,
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
								Highcharts.numberFormat(this.percentage,1)+'%' +' - ' +
								(this.y);
						});

						return s;
					},
					shared: true
				},
				plotOptions: {
					column: {
						stacking: 'percent',
						dataLabels: {
							enabled: true,
							 formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return Highcharts.numberFormat(this.percentage,1) + '%';
								}
							} 
						  
						}
					}
				},
				series: [{
					name: 'Renewal',
					data: totalRenewalCountArray
				}, {
					name: 'New',
					data: totalNewCountArray
				}]
			});
		
		   //today block graph.
			var todayRenewalCountArray = [];
			var todayNewCountArray =[];
			todayRenewalCountArray.push(emptyCheck(result.todayRenewalCount));
			todayNewCountArray.push(emptyCheck(result.todayNewCount));
			$('#todayOverAllRegistrationGraph').highcharts({
				colors: ['#53BF8B','#f7a423'],
				chart: {
					backgroundColor: '#EDEEF0', 
					type: 'column'
				},
				title: {
					text: ''
				},
				xAxis: {
					min: 0,
					 gridLineWidth: 0,
					 minorGridLineWidth: 0,
					categories: ['Andhra Pradesh']
				},
				yAxis: {
					min: 0,
				   gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: ''
					},
					stackLabels: {
						enabled: true,
						style: {
							fontWeight: 'bold',
							color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
						}
					}
				},
				legend: {
					enabled: false,
					align: 'right',
					x: -30,
					verticalAlign: 'top',
					y: 25,
					floating: true,
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
								Highcharts.numberFormat(this.percentage,1)+'%' +' - ' +
								(this.y);
						});

						return s;
					},
					shared: true
				},
				plotOptions: {
					column: {
						stacking: 'percent',
						dataLabels: {
							enabled: true,
							  formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return Highcharts.numberFormat(this.percentage,1) + '%';
								}
							} 
						  
						}
					}
				},
				series: [{
					name: 'Renewal',
					data: todayRenewalCountArray
				}, {
					name: 'New',
					data: todayNewCountArray
				}]  
			});
	         
	}
	function buildTotalTodayRegistrationsTS(result){  
		
	 var str= '';
	 // Total today Registrations block
	  str+='<div class="row m_top10">';
			str+='<div class="col-md-6 col-xs-12 col-sm-12">';
				str+='<div class="bg_ED pad_15">';
					str+='<div class="row m_top10">';
						str+='<div class="col-md-5 col-xs-12 col-sm-12 pad_right0">';
							str+='<h5 class="text-capital">total</h5>';
							str+='<h3 class="cadreCount">'+emptyCheck(result.totalCount)+'</h3>';
						str+='</div>';
						str+='<div class="col-md-7 col-xs-12 col-sm-12 pad_left0">';
							str+='<h4 class="f_16 text-success">Renewal  <span class="pull-right cadreCount f_14">'+emptyCheck(result.renewalCount)+'</span></h4>';
							str+='<h4 class="f_16" style="color:#F7A423">New  <span class="pull-right cadreCount f_14">'+emptyCheck(result.newCount)+'</span></h4>';
						str+='</div>';
					str+='</div>';
					str+='<div id="totalOverAllRegistrationGraph1" class="chartLiD" style="height:120px" ></div>'; 
				str+='</div>';
				
			str+='</div>';
			
			str+='<div class="col-md-6 col-xs-12 col-sm-12">';
				str+='<div class="bg_ED pad_15">';
					
					str+='<div class="row m_top10">';
						str+='<div class="col-md-5 col-xs-12 col-sm-12 pad_right0">';
							str+='<h5 class="text-capital">today</h5>';
							str+='<h3 class="cadreCount">'+emptyCheck(result.todayTotalCount)+'</h3>';
						str+='</div>';
						str+='<div class="col-md-7 col-xs-12 col-sm-12 pad_left0">';
							str+='<h4 class="f_16 text-success">Renewal  <span class="pull-right cadreCount f_14">'+emptyCheck(result.todayRenewalCount)+'</span></h4>';
							str+='<h4 class="f_16" style="color:#F7A423">New  <span class="pull-right cadreCount f_14">'+emptyCheck(result.todayNewCount)+'</span></h4>';
						str+='</div>';

					str+='</div>';
					str+='<div id="todayOverAllRegistrationGraph" class="chartLiD" style="height:120px" ></div>';
				str+='</div>';
				
			str+='</div>';
		str+='</div>';
		
		$("#totalTodayCadreRegistrationBlockDivTSId").html(str);  
		$('.cadreCount').each(function () {
				$(this).prop('Counter',0).animate({
					Counter: $(this).text()
				}, {
					duration: 1500,
					easing: 'swing',
					step: function (now) {
						if(now != null && now > 0)
						$(this).text(Math.ceil(now));
					}
				});
			});
		//total block graph.
			var totalRenewalCountArray = [];
			var totalNewCountArray =[];
			totalRenewalCountArray.push(emptyCheck(result.renewalCount));
			totalNewCountArray.push(emptyCheck(result.newCount));
			
			$('#totalOverAllRegistrationGraph1').highcharts({  
				colors: ['#53BF8B','#f7a423'],
				chart: {
					backgroundColor: '#EDEEF0',
					type: 'column'
				},
				title: {
					text: ''
				},
				xAxis: {
					min: 0,
					 gridLineWidth: 0,
					 minorGridLineWidth: 0,
					categories: ['Andhra Pradesh']
				},
				yAxis: {
					min: 0,
				   gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: ''
					},
					stackLabels: {
						enabled: true,
						style: {
							fontWeight: 'bold',
							color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
						}
					}
				},
				legend: {
					enabled: false,
					align: 'right',
					x: -30,
					verticalAlign: 'top',
					y: 25,
					floating: true,
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
								Highcharts.numberFormat(this.percentage,1)+'%' +' - ' +
								(this.y);
						});

						return s;
					},
					shared: true
				},
				plotOptions: {
					column: {
						stacking: 'percent',
						dataLabels: {
							enabled: true,
							 formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return Highcharts.numberFormat(this.percentage,1) + '%';
								}
							} 
						  
						}
					}
				},
				series: [{
					name: 'Renewal',
					data: totalRenewalCountArray
				}, {
					name: 'New',
					data: totalNewCountArray
				}]
			});
		
		   //today block graph.
			var todayRenewalCountArray = [];
			var todayNewCountArray =[];
			todayRenewalCountArray.push(emptyCheck(result.todayRenewalCount));
			todayNewCountArray.push(emptyCheck(result.todayNewCount));
			$('#todayOverAllRegistrationGraph').highcharts({
				colors: ['#53BF8B','#f7a423'],
				chart: {
					backgroundColor: '#EDEEF0', 
					type: 'column'
				},
				title: {
					text: ''
				},
				xAxis: {
					min: 0,
					 gridLineWidth: 0,
					 minorGridLineWidth: 0,
					categories: ['Andhra Pradesh']
				},
				yAxis: {
					min: 0,
				   gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: ''
					},
					stackLabels: {
						enabled: true,
						style: {
							fontWeight: 'bold',
							color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
						}
					}
				},
				legend: {
					enabled: false,
					align: 'right',
					x: -30,
					verticalAlign: 'top',
					y: 25,
					floating: true,
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
								Highcharts.numberFormat(this.percentage,1)+'%' +' - ' +
								(this.y);
						});

						return s;
					},
					shared: true
				},
				plotOptions: {
					column: {
						stacking: 'percent',
						dataLabels: {
							enabled: true,
							  formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return Highcharts.numberFormat(this.percentage,1) + '%';
								}
							} 
						  
						}
					}
				},
				series: [{
					name: 'Renewal',
					data: todayRenewalCountArray
				}, {
					name: 'New',
					data: todayNewCountArray
				}]  
			});
	         
	}
	
	
	function emptyCheck(filedValue){
		var returnVal = ' - ';
		if( filedValue !=null && filedValue > 0){
			returnVal = filedValue;
		}
		return returnVal;
	}
	
	function getEnumeratorsInfo(){
		
		$("#enumeratorsInfoDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var cadreDataRetrieveType  = "intermediate";
		$.ajax({
			type : 'GET',
			url : 'getEnumeratorsInfoAction.action',
			dataType : 'json',
			data : { retrieveType : cadreDataRetrieveType }
		}).done(function(result){
            if(result != null){
				buildEnumeratorsInfo(result);
			}else{
				$("#enumeratorsInfoDivId").html('NO DATA AVAILABLE');
			}	
		});	
           
	}
	function buildEnumeratorsInfo(result){
		//Enumerators block
			var str1='';
			str1+='<div class="col-md-12 col-xs-12 col-sm-12 m_top20">';
				str1+='<div class="bg_ED pad_15">';
					str1+='<table class="table text-capital">';
						str1+='<tr>';
							str1+='<td>';
								str1+='<img src="newCoreDashBoard/img/AP.png" class="img-responsive" alt="Andhra Prasdesh" style="width:65px"/>';
							str1+='</td>';
							str1+='<td>';
								str1+='<h5>Total ';
								/* if(result.totalCount != null && result.totalCount > 0){
									str1+='- <span class="text-muted">'+result.+'%</span></h5>';
								} */
								str1+='<h3 class="EnumCadreCount cadreCount">'+emptyCheck(result.totalCount)+'</h3>';
							str1+='</td>';
							str1+='<td>';
								str1+='<h5>Today'; 
								if(result.todayPercenCount != null && result.todayPercenCount > 0){
									str1+='- <span class="text-muted">'+result.todayPercenCount+'%</span></h5>';
								}
								str1+='<h3 class="EnumCadreCount cadreCount">'+emptyCheck(result.todayTotalCount)+'</h3>';
							str1+='</td>';
							str1+='<td>';
								str1+='<h5 class="EnumCadreCount cadreCount">'+emptyCheck(result.totalStartConstituCount)+' ';
								if(result.totalStartConstituPer != null && result.totalStartConstituPer > 0){
									str1+='- <small class="text-muted">'+result.totalStartConstituPer+'%</small></h5>';
								}
								str1+='<h5>Started<br> Constituencies</h5>';
							str1+='</td>';
						str1+='</tr>';
					str1+='</table>';
					
					str1+='<hr style="border-color:#B0B4B7;margin-top:10px;margin-bottom:10px;"/>';
					str1+='<span style="position: relative; text-align: center; top: -20px; padding: 3px 8px; background-color: #edeef0; left: 35%;">Today Eumerators Info</span>';
					str1+='<div class="row" style="margin-top:-10px">';
						str1+='<div class="col-md-4 col-xs-12 col-sm-4 text-center">';
							str1+='<h3 class="EnumCadreCount cadreCount">'+emptyCheck(result.todayFieldMembersCount)+'</h3>';
							str1+='<h5 class="text-capital">today field members</h5>';
						str1+='</div>';
						str1+='<div class="col-md-4 col-xs-12 col-sm-4 text-center">';
							str1+='<h3 class="EnumCadreCount cadreCount">'+emptyCheck(result.inFieldCount)+'</h3>';
							str1+='<h5 class="text-capital">in field now</h5>';
						str1+='</div>';
						str1+='<div class="col-md-4 col-xs-12 col-sm-4 text-center">';
							//str1+='<h3 class="EnumCadreCount cadreCount">'+emptyCheck(result.todaySubmittedCount)+'</h3>';
							str1+='<h3 class="EnumCadreCount cadreCount">'+emptyCheck(result.todayTotalCount)+'</h3>';
							str1+='<h5 class="text-capital">today submitted data</h5>';
						str1+='</div>';
					str1+='</div>';
				str1+='</div>';
			str1+='</div>';
			$("#enumeratorsInfoDivId").html(str1);
			$('.EnumCadreCount').each(function () {
				$(this).prop('Counter',0).animate({
					Counter: $(this).text()
				}, {
					duration: 1500,
					easing: 'swing',
					step: function (now) {
						if(now != null && now > 0)
						$(this).text(Math.ceil(now));
					}
				});
			});
			
	}
	
	function getRegistrationCountDtls(location,scope){
		$("#kupamRegDtlsId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		//var location = "booth";
		var jsObj={  
			location : location,       
			constId : 282,
			scope : scope    
		};
		$.ajax({          
			type : 'GET',      
			url : 'getRegistrationCountDtlsAction.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
			$("#kupamRegDtlsId").html('');
			//console.log(result);
			buildRegistrationCountDtls(result,location,scope);       
		});
	}
	function buildRegistrationCountDtls(result,location,scope){
		var str = '';
		str+='<div class="table-responsive m_top20">';
          str+='<table class="table table-bordered" id="regCadreCountTableId">';
            str+='<thead class="text-capital text-center">';
              str+='<tr>';
                str+='<th rowspan="2">mandal</th>';
				if(location == "panchayat"){
					str+='<th rowspan="2">panchayat</th>';
				}
                if(location == "booth"){
					str+='<th rowspan="2">panchayat</th>';
					str+='<th rowspan="2">Booth No</th>'; 
				}
                str+='<th rowspan="2">total voters</th>';
                str+='<th rowspan="2">2014 Total Cadre</th>';
				if(scope == "today"){
					str+='<th colspan="6" class="text-capital text-center">2016 Cadre</th>';
				}else{
					str+='<th colspan="5" class="text-capital text-center">2016 Cadre</th>';
				}
                    
              str+='</tr>';
              str+='<tr>';
				str+='<th>renewal Cadre 2016</th>';
                str+='<th>renewal cadre percent(%)</th>';
                str+='<th>total Cadre 2016</th>';
				if(scope == "today"){
					str+='<th>total Cadre On Today</th>'; 
				}
                
                str+='<th>new cadre</th>';
                str+='<th>new cadre percent(%)</th>';
              str+='</tr>';
            str+='</thead>';
			for(var i in result.responseData){  
				str+='<tr>';
				str+='<td>'+result.responseData[i].mandalName+'</td> ';
				if(location == "panchayat"){
					str+='<td>'+result.responseData[i].panchayatName+'</td>';
				}
				if(location == "booth"){
					str+='<td>'+result.responseData[i].panchayatName+'</td>';
					str+='<td>'+result.responseData[i].boothName+'</td>'; 
				}
			   str+='<td>'+result.responseData[i].totalVoter+'</td>';  
			   str+='<td>'+result.responseData[i].cadreCount2014+'</td>';
			   str+='<td>'+result.responseData[i].renewalCount+'</td>';
			   if(result.responseData[i].cadreCount2014 > 0){
				   var precent = (result.responseData[i].renewalCount*(100/result.responseData[i].cadreCount2014)).toFixed(0);
				   str+='<td>'+precent+'</td>';
			   }else{
				   str+='<td>0</td>';
			   }
			   str+='<td>'+result.responseData[i].cadreCount2016OverAll+'</td>';
			   if(scope == "today"){
					str+='<td>'+result.responseData[i].cadreCount2016Today+'</td>';  
			   }			  
            
              
              str+='<td>'+result.responseData[i].newCount+'</td>'; 
			  if(result.responseData[i].cadreCount2016OverAll > 0){    
				  var precent = (result.responseData[i].newCount*(100/result.responseData[i].cadreCount2016OverAll)).toFixed(0);   
				  str+='<td>'+precent+'</td>';     
			  }else{
				  str+='<td>0</td>';  
			  }
              
			  str+='</tr>';
			}
          str+='</table>';
        str+='</div>';
		$("#kupamRegDtlsId").html(str);  
		$("#regCadreCountTableId").dataTable();   
	}
	
	
$(document).on('click','.locationRadioCls',function(){
	var selectionType=$("input:radio[name=selectionType]:checked").val();
	var scopeType=$("input:radio[name=scopeType]:checked").val();
	getRegistrationCountDtls(selectionType,scopeType);  
});
$(document).on('click','.scopeRadioCls',function(){
	var selectionType=$("input:radio[name=selectionType]:checked").val();
	var scopeType=$("input:radio[name=scopeType]:checked").val();
	getRegistrationCountDtls(selectionType,scopeType);    
});
$(document).on('click','#cadreModalDivid',function(){
	$("#cadreModal").modal('show');
	$(".tabModal").hide();
	$(".webModal").show();
	$("#myModalLabel1").html("KUPPAM CONSTITUENCY DETAILED REPORT");
	var location = $("input:radio[name=selectionType]:checked").val();
	var scope = $("input:radio[name=scopeType]:checked").val();
	getRegistrationCountDtls(location,scope);  
});  
$(document).on('click','#cadreModalTabDivid',function(){
	
	$("#tabUserWiseReportDiv").html(' ');
	$("#cadreModal").modal('show');
	$(".tabModal").show();
	$(".webModal").hide();
	$("#myModalLabel1").html("KUPPAM CONSTITUENCY TAB USER DETAILED REPORT");
	 var constituencyId = $("#constituencySeletBoxId").val();
		var dates = $("#dateRangeIdForCadre").val();
		 var fromDate;
		 var toDate;
		 if(dates != null ){
			 var datesArr = dates.split("-");
			 fromDate=datesArr[0]+"-"+datesArr[1]+"-"+datesArr[2];
			 toDate=datesArr[3]+"-"+datesArr[4]+"-"+datesArr[5];
		 }
		var d = new Date();
		var curr_date = d.getDate();
		var curr_month = d.getMonth()+1;
		var curr_year = d.getFullYear();
		var fromDates= new Date(convertRequiredDate(fromDate.trim()));
		var toDates= new Date(convertRequiredDate(toDate.trim()));
		var currentDate =curr_date+ "-"+curr_month+"-"+curr_year;
		var currentDates = currentDate;
		var currentDates = new Date(convertRequiredDate(currentDates.trim()));
	   if(currentDates.getTime() == fromDates.getTime() && currentDates.getTime() == toDates.getTime()){
		   $("#notReceiveRegistrationFieldStaffDivId").show();
		  getNotReceiveRegistrationPerson(constituencyId,currentDate);
	   }else{
			$("#notReceiveRegistrationFieldStaffDivId").html(' ');   
			$("#notReceiveRegistrationFieldStaffDivId").hide();   
	   }
	   
		$(".showTabUserWiseDetails").show();
		getCadreRegistrationCountByConstituency(constituencyId,fromDate.trim(),toDate.trim());
});  
 function convertRequiredDate(date){
	   var dateArr=date.split("-");
	   return dateArr[1]+"/"+dateArr[0]+"/"+dateArr[2];
   }
$(document).on('click','.closeModal',function(){
	$("#noOfSamplesModal").modal('hide');
	$("body").addClass('modal-open');
});

$(document).on("click",".applyBtn",function(){
		
		var constituencyId = $("#constituencySeletBoxId").val();
		var dates = $("#dateRangeIdForCadre").val();
		/* if(constituencyId == 0){
			$("#constituencyErrorId").html("Please Select Constituency.");
			return;
		}
		$("#constituencyErrorId").html(' '); */
		 var fromDate;
		 var toDate;
		 if(dates != null ){
			 var datesArr = dates.split("-");
			 fromDate=datesArr[0]+"-"+datesArr[1]+"-"+datesArr[2];
			 toDate=datesArr[3]+"-"+datesArr[4]+"-"+datesArr[5];
		 }
		 var d = new Date();
		var curr_date = d.getDate();
		var curr_month = d.getMonth()+1;
		var curr_year = d.getFullYear();
		var fromDates= new Date(convertRequiredDate(fromDate.trim()));
		var toDates= new Date(convertRequiredDate(toDate.trim()));
		var currentDate =curr_date+ "-"+curr_month+"-"+curr_year;
		var currentDates = currentDate;
		var currentDates = new Date(convertRequiredDate(currentDates.trim()));
	   if(currentDates.getTime() == fromDates.getTime() && currentDates.getTime() == toDates.getTime()){
		   $("#notReceiveRegistrationFieldStaffDivId").show();
		   getNotReceiveRegistrationPerson(constituencyId,currentDate);
	   }else{
			$("#notReceiveRegistrationFieldStaffDivId").html(' ');   
			$("#notReceiveRegistrationFieldStaffDivId").hide();   
	   }
		$(".showTabUserWiseDetails").show();
		getCadreRegistrationCountByConstituency(constituencyId,fromDate.trim(),toDate.trim());
	});
	
$(document).on("click",".tabUserWiseDetails",function(){
		
		var constituencyId = $("#constituencySeletBoxId").val();
		var dates = $("#dateRangeIdForCadre").val();
		if(constituencyId == 0){
			$("#constituencyErrorId").html("Please Select Constituency.");
			return;
		}
		$("#constituencyErrorId").html(' ');
		 var fromDate;
		 var toDate;
		 if(dates != null ){
			 var datesArr = dates.split("-");
			 fromDate=datesArr[0]+"-"+datesArr[1]+"-"+datesArr[2];
			 toDate=datesArr[3]+"-"+datesArr[4]+"-"+datesArr[5];
		 }
		var d = new Date();
		var curr_date = d.getDate();
		var curr_month = d.getMonth()+1;
		var curr_year = d.getFullYear();
		var fromDates= new Date(convertRequiredDate(fromDate.trim()));
		var toDates= new Date(convertRequiredDate(toDate.trim()));
		var currentDate =curr_date+ "-"+curr_month+"-"+curr_year;
		var currentDates = currentDate;
		var currentDates = new Date(convertRequiredDate(currentDates.trim()));
		if(currentDates.getTime() == fromDates.getTime() && currentDates.getTime() == toDates.getTime()){
			   $("#notReceiveRegistrationFieldStaffDivId").show();
			  getNotReceiveRegistrationPerson(constituencyId,currentDate);
		}else{
			$("#notReceiveRegistrationFieldStaffDivId").html(' ');   
			$("#notReceiveRegistrationFieldStaffDivId").hide();   
		 }
		   
		$(".showTabUserWiseDetails").show();
		getCadreRegistrationCountByConstituency(constituencyId,fromDate.trim(),toDate.trim());
	});
function getCadreRegistrationCountByConstituency(constituencyId,fromDate,toDate){
		 $("#tabUserWiseReportDiv").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jsObj={  
			constituencyId : constituencyId,      
			fromDate : fromDate,
			toDate : toDate
		};
		$.ajax({          
			type : 'GET',    
			url : 'getCadreRegistrationCountByConstituencyAction.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
			$("#tabUserWiseReportDiv").html('');
		  if(result != null && result.length > 0){
			  buildCadreRegistrationOverViewResult(result);
		  }else{
			  $("#tabUserWiseReportDiv").html("NO DATA AVAILABLE.");
		  }
		});
	}
	function buildCadreRegistrationOverViewResult(tabUserInfoList){
		
			var str='';
			str+='<h4>FIELD USER REGISTRATION DETAILS</h4>';
			str+='<table class="table table-bordered table-condensed m_top10" id="tabUserWiseReportDataTableId"> ';
				str+='<thead> ';
					str+='<tr>';
					    str+='<th>Survey UserId</th>';
						str+='<th>Field Staff Name </th>';
						str+='<th>Image</th>';
						str+='<th>MobileNo</th>';
						str+='<th>No.Of Samples</th>';
						str+='<th>First Record Time</th>';
						str+='<th>Last Record Time</th>';
					str+='</tr>'; 
				str+='</thead>'; 
				str+='<tbody>';
				for(var j in tabUserInfoList){
					if(tabUserInfoList[j].name == "prasad"){
						continue;   
					}
						str+='<tr> ';
						   if(tabUserInfoList[j].userName != null && tabUserInfoList[j].userName.length > 0){
							str+='<td>'+tabUserInfoList[j].userName+'</td>';   
						   }else{
							 str+='<td> - </td>';  
						   }
						   if(tabUserInfoList[j].sampleCount > 0){
							 	if(tabUserInfoList[j].name != null){
							str+='<td>'+tabUserInfoList[j].name+'</td>';
							}else{
								str+='<td> - </td>';	
							}  
						   }else{
							if(tabUserInfoList[j].name != null){
							str+='<td style="color:red;">'+tabUserInfoList[j].name+'</td>';
							}else{
								str+='<td> - </td>';	
							}  
						   }
						 
							str+='<td><img src="http://mytdp.in/tab_user_images/'+tabUserInfoList[j].imagePath+'" onerror="setDefaultImage(this);" style="width: 50px; height: 50px;"></img></td>';
							 if(tabUserInfoList[j].mobileNo != null && tabUserInfoList[j].mobileNo.length > 0){
							 str+='<td>'+tabUserInfoList[j].mobileNo+'</td> ';	 	 
							 }else{
							 str+='<td> - </td> ';	 
							  }
							 if(tabUserInfoList[j].sampleCount != null && tabUserInfoList[j].sampleCount> 0){
							 str+='<td><a style="cursor:pointer;" class="noOfSamplesDetailsPopUpView" attr_tab_user_info_id='+tabUserInfoList[j].id+'>'+tabUserInfoList[j].sampleCount+'</a></td> ';	 	 
							 }else{
							 str+='<td> - </td> ';	 
							  }
							 if(tabUserInfoList[j].firstRecordInsertedTime != null && tabUserInfoList[j].firstRecordInsertedTime.length> 0){
							 str+='<td>'+tabUserInfoList[j].firstRecordInsertedTime.substring(0,19)+'</td> ';	 	 
							 }else{
							 str+='<td> - </td> ';	 
							  }
							  if(tabUserInfoList[j].lastRecordInsertedTime != null && tabUserInfoList[j].lastRecordInsertedTime.length> 0){
							 str+='<td>'+tabUserInfoList[j].lastRecordInsertedTime.substring(0,19)+'</td> ';	 	 
							 }else{
							 str+='<td> - </td> ';	 
							  }
						str+='</tr>';
						  }
			str+='</tbody>'; 
		str+='</table>';
		
		$("#tabUserWiseReportDiv").html(str);
		$("#tabUserWiseReportDataTableId").dataTable();  
	}
	
	function setDefaultImage(img){
		img.onerror = "";
		img.src = "images/cadre_images/human.jpg";
		return true;
	}
	
	$(document).on("click",".noOfSamplesDetailsPopUpView",function(){
	   var constituencyId = $("#constituencySeletBoxId").val();
	    var dates = $("#dateRangeIdForCadre").val();
		 var fromDate;
		 var toDate;
		 if(dates != null ){
			 var datesArr = dates.split("-");
			 fromDate=datesArr[0]+"-"+datesArr[1]+"-"+datesArr[2];
			 toDate=datesArr[3]+"-"+datesArr[4]+"-"+datesArr[5];
		 }
		var tabUserInfoId = $(this).attr("attr_tab_user_info_id");
		//var surveyUserId = $(this).attr("attr_survey_user_info_id");
		$("#tabUserInfoDivId").html(' ');
		$("#tabUserInfoDetailsHeadingId").html("No.Of Samples Day Wise Tab User Details");
		$("#noOfSamplesModal").modal("show");
		getDaysByCadreRegistrationCount(constituencyId,fromDate.trim(),toDate.trim(),tabUserInfoId,0);
	});	

function getDaysByCadreRegistrationCount(constituencyId,fromDate,toDate,tabUserInfoId,surveyUserId){
		$("#noOfSamplesDetailsDiv").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jsObj={  
			constituencyId : constituencyId,      
			fromDate : fromDate,
			toDate : toDate,
			tabUserInfoId : tabUserInfoId,
			cadreSurveyUserId : surveyUserId
		};
		$.ajax({          
			type : 'GET',    
			url : 'getDaysByCadreRegistrationCountAction.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
			$("#noOfSamplesDetailsDiv").html(' ');
			 if(result != null && result.length > 0){
				 buildDaysByCadreRegistrationResult(result);
			 }else{
				$("#noOfSamplesDetailsDiv").html("NO DATA AVAILABLE"); 
			 }
		});
	}

	function buildDaysByCadreRegistrationResult(result){
			var str='';
			str+='<table class="table table-bordered table-condensed"> ';
				str+='<thead> ';
					str+='<tr>';
						str+='<th>Day</th>';
						str+='<th>No.Of Samples</th>';
						str+='<th>First Record Time</th>';
						str+='<th>Last Record Time</th>';
					str+='</tr>'; 
				str+='</thead>'; 
				str+='<tbody>';
				for(var i in result){
					str+='<tr> ';
						 if(result[i].days != null && result[i].days.length > 0){
								str+='<td>'+result[i].days+'</td> '; 
						 }else{
								str+='<td> - </td> ';
						 }
						if(result[i].sampleCount != null && result[i].sampleCount> 0){
						 str+='<td>'+result[i].sampleCount+'</td> ';	 	 
						 }else{
						 str+='<td> - </td> ';	 
						  }
						 if(result[i].firstRecordInsertedTime != null && result[i].firstRecordInsertedTime.length> 0){
						 str+='<td>'+result[i].firstRecordInsertedTime.substring(0,19)+'</td> ';	 	 
						 }else{
						 str+='<td> - </td> ';	 
						  }
						  if(result[i].lastRecordInsertedTime != null && result[i].lastRecordInsertedTime.length> 0){
						 str+='<td>'+result[i].lastRecordInsertedTime.substring(0,19)+'</td> ';	 	 
						 }else{
						 str+='<td> - </td> ';	 
						  }
				   str+='</tr>';
				
				}
			str+='</tbody>'; 
		str+='</table>';
		$("#noOfSamplesDetailsDiv").html(str);
	}
	
	
	function getCadreRecentTime(){
 	$.ajax({
		type : 'POST',
		url : 'getCadreLastUpdatedTimeAction.action',
		dataType : 'json',
		data : {task:JSON.stringify( )}
	}).done(function(result){
		if(result != null){
		setLastUpdatedTime(result)	
		}
	});
}
setInterval(function() {
    cadreRegistrationBasicCall();
  }, 60 * 500000);
  

function setLastUpdatedTime(lastUpdatedTime){
	$("#lastUpdatedTimeCadreId").html(" Last Updated : "+lastUpdatedTime+"");
}

function getNotReceiveRegistrationPerson(constituencyId,currentDate){
	$("#notReceiveRegistrationFieldStaffDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jsObj={  
			constituencyId : constituencyId,      
			date : currentDate
		};
		$.ajax({          
			type : 'GET',    
			url : 'getNotReceiveRegistrationPersonAction.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
			$("#notReceiveRegistrationFieldStaffDivId").html(' ');
		  if(result != null && result.length > 0){
			  buildNotReceivedFieldStaffResult(result);
		  }else{
		  $("#notReceiveRegistrationFieldStaffDivId").html("NO DATA AVAILABLE.");  
		  }
		});
	}
	var htmlColorCodeArr=[];
	    htmlColorCodeArr.push("#FFA500");
	    htmlColorCodeArr.push("#FF8D00");
	    htmlColorCodeArr.push("#ff3232");
	    htmlColorCodeArr.push("#FF0000");
	function buildNotReceivedFieldStaffResult(result){
	var str='';
			str+='<h4>IDLE TAB USER DETAILS</h4>';
			str+='<table class="table table-bordered table-condensed m_top10">';
				str+='<tbody>';
				str+='<tr> ';
				for(var i in result){
					if(result[i].count != null && result[i].count>0){
				     str+='<td><h2><a style="cursor:pointer;" class="tabUserCountcls" attr_Ids='+result[i].idsList+'>'+result[i].count+'</a></h2>';
				     str+='<p style="color:'+htmlColorCodeArr[i]+'">'+result[i].name+'</p></td> ';
					}else{
					  str+='<td><h2>'+result[i].count+'</h2>';
			          str+='<p style="color:'+htmlColorCodeArr[i]+'">'+result[i].name+'</p></td> ';
					}
				}
			 str+='</tr>';
			str+='</tbody>'; 
		str+='</table>';
		$("#notReceiveRegistrationFieldStaffDivId").html(str);
	}
$(document).on("click",".tabUserCountcls",function(){
	var tabUserIdsString = $(this).attr("attr_Ids");
	var idsArr = tabUserIdsString.split(",");
	var tabUserIdStr;
	for(var i=0;i<idsArr.length;i++){
		if(i==0){
		tabUserIdStr = 	idsArr[i];
		}else{
		tabUserIdStr = tabUserIdStr+","+idsArr[i];	
		}
	}
	$("#noOfSamplesDetailsDiv").html(' ');
	$("#tabUserInfoDetailsHeadingId").html("IDLE TAB USER DETAILS.");
	$("#noOfSamplesModal").modal("show");
    getTabUserInfoDetails(tabUserIdStr);
});		
function getTabUserInfoDetails(tabUserIdStr){
	$("#tabUserInfoDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div   class="dot2"></div></div></div>');
		var jsObj={  
			tabUserInfoStrIds :tabUserIdStr      
		};
		$.ajax({          
			type : 'GET',    
			url : 'getTabUserInfoDetailsAction.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
		  if(result != null && result.length > 0){
			buildNotReceivedTabUserDetails(result);  
		  }else{
			$("#tabUserInfoDivId").html("NO DATA AVAILABLE.");  
		  }
		});
	}	
	function buildNotReceivedTabUserDetails(result){
		var str=''
		str+='<table class="table table-bordered table-condensed" id="fieldStaffDetailsDataTableId"> ';
		        str+='<thead>';
				 str+='<th>Name</th>'
				 str+='<th>MobileNo</th>'
				 str+='<th>Image</th>'
				str+='</thead>';
				str+='<tbody>';
				for(var i in result){
					str+='<tr> ';
					 if(result[i].name != null && result[i].name.length > 0){
							str+='<td>'+result[i].name+'</td> '; 
					 }else{
							str+='<td> - </td> ';
					 }
					 if(result[i].mobileNo != null && result[i].mobileNo.length > 0){
					 str+='<td>'+result[i].mobileNo+'</td> ';	 	 
					 }else{
					 str+='<td> - </td> ';	 
					  }
					 str+='<td><img src="http://mytdp.in/tab_user_images/'+result[i].imagePath+'" onerror="setDefaultImage(this);" style="width: 50px; height: 50px;"></img></td>';
				   str+='</tr>';
				}
			str+='</tbody>'; 
		str+='</table>';
		$("#tabUserInfoDivId").html(str);
		$("#fieldStaffDetailsDataTableId").dataTable();
	}
	
	
	/*  cadre registration  new core dashboard block      */
	
	var globalUserTypeWiseCadreRegistrationCountRslt;
	var globalUserTypeBySubLevelRslt;
	var globalTsDistrictRslt;
	var globalApConstituencyRslt;
	var globalTsConstituencyRslt;
	 function getUserTypeWiseTotalCadreRegistrationCount(){
		$("#userTypeWiseTop5PositiveAndNegitiveCadreDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jsObj ={ 
				 activityMemberId : globalActivityMemberId,
				 stateId : globalStateId,
				 userTypeId : globalUserTypeId,
				 fromDate : "2/10/2016",
				 todate : "17/10/2016"
			  }
		$.ajax({
			type : 'POST',
			url : 'getUserTypeWiseTotalCadreRegistrationCountAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		  $("#userTypeWiseTop5PositiveAndNegitiveCadreDivId").html(' ');
		  buildgUserTypeWiseCadreRegistrationTopPositiveRslt(result);
		  globalUserTypeWiseCadreRegistrationCountRslt = result;
		});
 }
 function buildgUserTypeWiseCadreRegistrationTopPositiveRslt(result){
		var str='';
		if(result != null && result.length > 0){
		  var str='';
		  for(var i in result){
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				 if(result[i][0].userTypeId==4 || result[i][0].userTypeId==11){
				  if(result[i][0].userTypeId==4){
				  if(result[i][0].totalCadreCountPer!=0){
					  str+='<h5 class="text-capital">'+result[i][0].userType+' / SECRETARY </h5>';      
				  }
				  }
				  if(result[i][0].userTypeId==11){
				   if(result[i][0].totalCadreCountPer!=0){
					 str+='<h5 class="text-capital">ORGANIZING SECRETARY /'+result[i][0].userType+'</h5>';      
				   }
			     }
			   }else{
				 if(result[i][0].totalCadreCountPer!=0){
					str+='<h5 class="text-capital">'+result[i][0].userType+'</h5>'; 
				 }
		      }
			  str+='<div id="genSecCadre'+i+'" style="width:100%;height:80px;"></div>';
			str+='</div>'
		  }
		}
		$("#userTypeWiseTop5PositiveAndNegitiveCadreDivId").html(str);
	   if(result != null && result.length > 0){
			for(var i in result){
				var candidateNameArray = [];
				var totalCadreCountPerArr = [];
				var countVar =0;
			  if(result[i] !=null && result[i].length>0){
					for(var j in result[i]){
						countVar =countVar+1;
						candidateNameArray.push(result[i][j].name);
						totalCadreCountPerArr.push(result[i][j].totalCadreCountPer);
						if (countVar === 5) {
							break;
						}
					}
				}
		if(result[i][0].totalCadreCountPer!=0){
				var getWidth = $("#genSecCadre"+i).parent().width()+'px';
				$("#genSecCadre"+i).width(getWidth);
		     $(function () {
			$('#genSecCadre'+i).highcharts({
				colors: ['#0066DC'],
				chart: {
					type: 'column'
				},
				title: {
					text: null
				},
				subtitle: {
					text: null
				},
				xAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: candidateNameArray,
					title: {
						text: null
					},
					labels: {
							formatter: function() {
								return this.value.toString().substring(0, 10)+'...';
							},
							
						}
				},
				yAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: null,
						align: 'high'
					},
					labels: {
						overflow: 'justify',
						enabled: false,
					}
				},
				tooltip: {
				headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
				pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}%</b>'
				},
				plotOptions: {
					column: {
						stacking: 'percent',
						dataLabels: {
							enabled: true,
							 formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return Highcharts.numberFormat(this.y,2) +"%";
								}
							}
						  
						}
					}
				},
				legend: {
					layout: 'vertical',
					align: 'right',
					verticalAlign: 'top',
					x: -40,
					y: 80,
					floating: true,
					borderWidth: 1,
					backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
					shadow: true
				},
				credits: {
					enabled: false
				},
				
				series: [{
					name: 'Cadre',
					data: totalCadreCountPerArr
				}]
			});
		});
		}else{
		$("#genSecCadre"+i).html("No Data Available");
		$("#genSecCadre"+i).css("height","35px");
		$("#genSecCadre"+i).hide();
		} 
	}
	}else{
    $("#userTypeWiseTop5PositiveAndNegitiveCadreDivId").html('NO DATA AVAILABLE.');
	}
	}
	
	$(document).on("click",".cadrePositiveNegativeCls",function(){
	var resultType=$(this).attr("attr_value");
	 if(resultType != null && resultType == "positive"){
	  buildgUserTypeWiseCadreRegistrationTopPositiveRslt(globalUserTypeWiseCadreRegistrationCountRslt); 
	 }else if(resultType == "negative"){
	  buildgUserTypeWiseCadreRegistrationTopNegitiveRslt(globalUserTypeWiseCadreRegistrationCountRslt);
	 }
});
	function buildgUserTypeWiseCadreRegistrationTopNegitiveRslt(result){
		var str='';
		if(result != null && result.length > 0){
			var str='';
			for(var i in result){
				str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				 if(result[i][0].userTypeId==4 || result[i][0].userTypeId==11){
				  if(result[i][0].userTypeId==4){
				   str+='<h5 class="text-capital">'+result[i][0].userType+' / SECRETARY </h5>';      
				  }
				  if(result[i][0].userTypeId==11){
				   str+='<h5 class="text-capital">ORGANIZING SECRETARY /'+result[i][0].userType+'</h5>';      
				  }
			   }else{
				str+='<h5 class="text-capital">'+result[i][0].userType+'</h5>'; 
			   }
				str+='<div id="genSecCadre'+i+'" style="width:100%;height:100px;"></div>';
				str+='</div>'
			}
		}
		$("#userTypeWiseTop5PositiveAndNegitiveCadreDivId").html(str);
	if(result != null && result.length > 0){
		for(var i in result){
				var candidateNameArray = [];
				var totalCadreCountPerArr = [];
				var countVar = 0;
				if(result[i] != null && result[i].length > 0){
					var length = result[i].length - 1;
					for(var j = length; j >= 0; j--){
						candidateNameArray.push(result[i][j].name);
						totalCadreCountPerArr.push(result[i][j].totalCadreCountPer);
						countVar =countVar+1;
						if (countVar === 5) {
							break;
						}
					}	
				}
			//if( result[i][j].totalCadreCountPer!=0){
			var getWidth = $("#genSecCadre"+i).parent().width()+'px';
				$("#genSecCadre"+i).width(getWidth);
				$(function () {
			   $('#genSecCadre'+i).highcharts({
				colors: ['#0066DC'],
				chart: {
					type: 'column'
				},
				title: {
					text: null
				},
				subtitle: {
					text: null
				},
				xAxis: {
					categories: candidateNameArray,
					title: {
						text: null
					}
				},
				yAxis: {
					min: 0,
					title: {
						text: null,
						align: 'high'
					},
					labels: {
						overflow: 'justify',
						enabled: false,
					}
				},
				tooltip: {
				headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
				pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}%</b>'
				},

				plotOptions: {
					column: {
						stacking: 'percent',
						dataLabels: {
							enabled: true,
							 formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return Highcharts.numberFormat(this.y,2) +"%";
								}
							}
						  
						}
					}
				},
				legend: {
					layout: 'vertical',
					align: 'right',
					verticalAlign: 'top',
					x: -40,
					y: 80,
					floating: true,
					borderWidth: 1,
					backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
					shadow: true
				},
				credits: {
					enabled: false
				},
				series: [{
					name: 'Cadre',
					data: totalCadreCountPerArr
				}]
			});
		});
		/* }else{
		$("#genSecCadre"+i).html("No Data Available");
		$("#genSecCadre"+i).css("height","35px");	
		} */
		}
	}else{
	 $("#userTypeWiseTop5PositiveAndNegitiveCadreDivId").html('NO DATA AVAILABLE.');
	}
	} 
	
	 function getCadreDetailsBasedOnUserType(filterType){
		 $("#userTypeWiseHighChartDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jsObj ={ 
				 activityMemberId : globalActivityMemberId,
				 stateId : globalStateId,
				 userTypeId : globalUserTypeId,
				 fromDate : "2/6/2016",
				 todate : "17/10/2016"
			  }
		$.ajax({
			type : 'POST',
			url : 'getCadreDetailsBasedOnUserTypeAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		       buildUserTypeWiseHighchartsRslt(result,"userTypeWiseHighChartDivId",filterType);
			   globalUserTypeBySubLevelRslt = result;
		  });
	}

 $(document).on("click",".districtFilterCls",function(){
     var filterType = $(this).attr("attr_filter_value");
     buildUserTypeWiseHighchartsRslt(globalUserTypeBySubLevelRslt,"userTypeWiseHighChartDivId",filterType);
	 buildUserTypeWiseHighchartsRslt(globalTsDistrictRslt,"tsDistrictWiseRegistrationDivId",filterType);
  });
   function getTsDistrictWiseTsDetails(accessLevelId,accessLevelValues,filterType){
	   $("#tsDistrictWiseRegistrationDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jsObj ={ 
				 locationType : "District",
				 stateId : 36,
				 fromDate : "2/10/2016",
				 todate : "17/10/2016",
				 accessLevelId:accessLevelId,
				 accessLevelValues:accessLevelValues
			  }
		$.ajax({
			type : 'POST',
			url : 'getLocationWiseCadreDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		   buildUserTypeWiseHighchartsRslt(result,"tsDistrictWiseRegistrationDivId",filterType);
           globalTsDistrictRslt	= result;	   
	     });
 }			
  function buildUserTypeWiseHighchartsRslt(result,divId,filterType){
	   $("#"+divId).html(' ');
	    var locationNameArr =[];
		var newCadreArr = [];
		var cadre2014ArrPer = [];
		var renewalArr=[];
	if(result != null && result.length > 0){
			for(var i in result){
				
			    if(filterType != null && filterType=="All"){
					
					locationNameArr.push(result[i].locationName);
					cadre2014ArrPer.push(result[i].total2014CadrePer);
					renewalArr.push(result[i].total2016RenewalCadrePer);
					newCadreArr.push(result[i].total2016NewCadrePer);
				
				}else if(filterType=="verygood"){
				
				 if(result[i].total2016CadrePer > 100){
					 	alert(true);
						alert(result[i].total2016CadrePer);
				  locationNameArr.push(result[i].locationName);
				  cadre2014ArrPer.push(result[i].total2014CadrePer);
				  renewalArr.push(result[i].total2016RenewalCadrePer);
				  newCadreArr.push(result[i].total2016NewCadrePer);
				  
				 }	
				 
				}else if(filterType=="good"){
				  
				  if(result[i].total2016CadrePer >= 90 && result[i].total2016CadrePer<=100){
				  
				   locationNameArr.push(result[i].locationName);
				   cadre2014ArrPer.push(result[i].total2014CadrePer);
			       renewalArr.push(result[i].total2016RenewalCadrePer);
				   newCadreArr.push(result[i].total2016NewCadrePer);
				   
				  }
				}else if(filterType=="ok"){
					
					  if(result[i].total2016CadrePer >= 80 && result[i].total2016CadrePer<=90){
						  
					   locationNameArr.push(result[i].locationName);
				       cadre2014ArrPer.push(result[i].total2014CadrePer);
			     	  renewalArr.push(result[i].total2016RenewalCadrePer);
				      newCadreArr.push(result[i].total2016NewCadrePer);
				
				      }
				}else if(filterType=="poor"){
					
				  if(result[i].total2016CadrePer >= 60 && result[i].total2016CadrePer <= 80){
					 locationNameArr.push(result[i].locationName);
					 cadre2014ArrPer.push(result[i].total2014CadrePer);
					 renewalArr.push(result[i].total2016RenewalCadrePer);
					 newCadreArr.push(result[i].total2016NewCadrePer);
				  }
				}else if(filterType == "verypoor"){
					
					if(result[i].total2016CadrePer <= 60){
						
					   locationNameArr.push(result[i].locationName);
					   cadre2014ArrPer.push(result[i].total2014CadrePer);
					   renewalArr.push(result[i].total2016RenewalCadrePer);
					   newCadreArr.push(result[i].total2016NewCadrePer);
					}
				}
			
			}
		var jsonDataArr=[];	
		 var colorArr=[];
		 if(renewalArr.length > 0){
		  jsonDataArr.push({name: '2016 Renewal Cadre',data: renewalArr,stack: '2016'});
          colorArr.push('#30AA74');		  
		  }
		  if(newCadreArr.length > 0){
		  jsonDataArr.push({name: '2016 New Cadre',data: newCadreArr,stack: '2016'});
          colorArr.push('#F36800');		  
		  }
		  if(cadre2014ArrPer.length > 0){
		  jsonDataArr.push({name: '2014 Cadre',data: cadre2014ArrPer,stack: '2014'});
          colorArr.push('#FFCA00');		  
		  }
		  
		  $("#"+divId).highcharts({
			 colors: ['#30AA74','#F36800','#FFCA00'],
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
			   categories: locationNameArr
			},

			yAxis: {
				allowDecimals: false,
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				title: {
					text: ' '
				}
			},

			tooltip: {
				valueSuffix:  '%' 
			},

			plotOptions: {
				column: {
					stacking: 'normal'
				}
			},

			series: jsonDataArr
		}); 
   }else{
	 $("#"+divId).html('NO DATA AVAILABLE.');	 
	}
  }
  $(document).on("click",".constituencyFilterCls",function(){
     var filterType = $(this).attr("attr_filter_value");
    // buildConstituecnyWiseCadreResult(globalApConstituencyRslt,"apConstituencyRegistrationReportDivId",filterType);
	// buildConstituecnyWiseCadreResult(globalTsConstituencyRslt,"tsConstituencyRegistrationReportDivId",filterType);
  });

  function getApConstituencyCadreRegistrationDetails(accessLevelId,accessLevelValues,renewal2016CheckboxIsChecked,new2016CheckboxIsChecked,cadre2014CheckboxIsChecked){
	  $("#apConstituencyRegistrationReportDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jsObj ={ 
				 locationType : "Constituency",
				 stateId : 1,
				 fromDate : "2/10/2016",
				 todate : "17/10/2016",
				 accessLevelId:accessLevelId, 
				 accessLevelValues:accessLevelValues
			  }
		$.ajax({
			type : 'POST',
			url : 'getLocationWiseCadreDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			  buildConstituecnyWiseCadreResult(result,"apConstituencyRegistrationReportDivId",renewal2016CheckboxIsChecked,new2016CheckboxIsChecked,cadre2014CheckboxIsChecked);
			  globalApConstituencyRslt=result;
	     });
 }
   function getTsConstituencyCadreRegistrationDetails(accessLevelId,accessLevelValues,renewal2016CheckboxIsChecked,new2016CheckboxIsChecked,cadre2014CheckboxIsChecked){
	   $("#tsConstituencyRegistrationReportDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jsObj ={ 
				 locationType : "Constituency",
				 stateId : 36,
				 fromDate : "2/10/2016",
				 todate : "17/10/2016",
				 accessLevelId:accessLevelId,
				 accessLevelValues:accessLevelValues
			  }
		$.ajax({
			type : 'POST',
			url : 'getLocationWiseCadreDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
	    	  buildConstituecnyWiseCadreResult(result,"tsConstituencyRegistrationReportDivId",renewal2016CheckboxIsChecked,new2016CheckboxIsChecked,cadre2014CheckboxIsChecked);
			  globalTsConstituencyRslt=result;
	     });
 }
  var veryGoodCntArr=[];
  var goodCntArr=[];
  var okCntArr=[];
  var poorCntArr=[];
  var veryPoorCntArr=[];
 function buildConstituecnyWiseCadreResult(result,divId,renewal2016CheckboxIsChecked,new2016CheckboxIsChecked,cadre2014CheckboxIsChecked){
	   $("#"+divId).html(' ');
    	var locationNameArr =[];
		var newCadreArr = [];
		var cadre2014ArrPer = [];
		var renewalArr=[];
		var jsonDataArr=[];
		
		var veryGoodCnt=0;
		var goodCnt=0;
		var okCnt=0;
		var poorCnt=0;
		var veryPoorCnt=0;
		if(result != null && result.length > 0){
				for(var i in result){
				locationNameArr.push(result[i].locationName);
				if((renewal2016CheckboxIsChecked=="Y" && new2016CheckboxIsChecked=="Y" && cadre2014CheckboxIsChecked=="Y")||(renewal2016CheckboxIsChecked=="2016Renewal" && new2016CheckboxIsChecked=="2016New" && cadre2014CheckboxIsChecked=="2014Cadre")){
					renewalArr.push(result[i].total2016RenewalCadrePer);
					newCadreArr.push(result[i].total2016NewCadrePer);
					cadre2014ArrPer.push(result[i].total2014CadrePer);
				}else if(renewal2016CheckboxIsChecked=="2016Renewal" && new2016CheckboxIsChecked=="2016New"){
					renewalArr.push(result[i].total2016RenewalCadrePer);
					newCadreArr.push(result[i].total2016NewCadrePer);
				}else if(renewal2016CheckboxIsChecked=="2016Renewal" && cadre2014CheckboxIsChecked=="2014Cadre"){
					renewalArr.push(result[i].total2016RenewalCadrePer);
					cadre2014ArrPer.push(result[i].total2014CadrePer);
				}else if(new2016CheckboxIsChecked=="2016New" && cadre2014CheckboxIsChecked=="2014Cadre"){
					newCadreArr.push(result[i].total2016NewCadrePer);
					cadre2014ArrPer.push(result[i].total2014CadrePer);
				}else if(renewal2016CheckboxIsChecked=="2016Renewal"){
					renewalArr.push(result[i].total2016RenewalCadrePer);
				}else if(new2016CheckboxIsChecked=="2016New"){
					 newCadreArr.push(result[i].total2016NewCadrePer);
				}else if(cadre2014CheckboxIsChecked=="2014Cadre"){
					 cadre2014ArrPer.push(result[i].total2014CadrePer);
				}
			}
		
			 var colorArr=[];
			 if(renewalArr.length > 0){
			  jsonDataArr.push({name: '2016 Renewal Cadre',data: renewalArr,stack: '2016'});
			  colorArr.push('#30AA74');		  
			  }
			  if(newCadreArr.length > 0){
			  jsonDataArr.push({name: '2016 New Cadre',data: newCadreArr,stack: '2016'});
			  colorArr.push('#F36800');		  
			  }
			  if(cadre2014ArrPer.length > 0){
			  jsonDataArr.push({name: '2014 Cadre',data: cadre2014ArrPer,stack: '2014'});
			  colorArr.push('#FFCA00');		  
			  }
			 /*Setting Dynamic height for highChart */
			 if(result!= null && result.length > 10){
			  var highChartDivHight = result.length*20;
			  $("#"+divId).height(highChartDivHight);	
			  }else{
			  $("#"+divId).height(260);		
			  }
				$(function () {
					$("#"+divId).highcharts({
						colors: colorArr,
						chart: {
							type: 'bar'
						},
						title: {
							text: null
						},
						xAxis: {
							min: 0,
							gridLineWidth: 0,
							minorGridLineWidth: 0,
							categories: locationNameArr
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
							reversed: true
						},
						plotOptions: {
							series: {
								stacking: 'normal'
							}
						},
						series:jsonDataArr 
					});
				});
			if(result.length > 10){
				$("#"+divId).mCustomScrollbar({setHeight: '440px'})		
			}	
		}else{
		 $("#"+divId).html("NO DATA AVAILABLE.");	
		}
 }
 
 
    getApTsDistrictList();
  function getApTsDistrictList(){
	 	$.ajax({
			type : 'POST',
			url : 'getApTsDistrictListAction.action',
			dataType : 'json',
			data : {task:JSON.stringify( )}
		}).done(function(result){
	       if(result != null ){
			 buildDistrictRslt(result);  
		   }
		});
 }
 function buildDistrictRslt(result){
	 var apDistrictList = result.subList1;
	 var tsDistrictList = result.subList2;
	 var str1='';
	 var str2='';
	 str1+='<ul class="cadreSelectD" id="apDistrictUlId">';
    if(apDistrictList != null && apDistrictList.length > 0){
		  for(var i in apDistrictList){
				 str1+='<li><label class="checkbox-inline"><input id="'+apDistrictList[i].locationId+'" type="checkbox" checked/>'+apDistrictList[i].locationName+'</label></li>';
		  }
	  }
	  str1+='</ul>';
	  
	  str2+='<ul class="cadreSelectD" id="tsDistrictULId">';
     if(tsDistrictList != null && tsDistrictList.length > 0){
		  for(var i in tsDistrictList){
			 	str2+='<li><label class="checkbox-inline"><input id="'+tsDistrictList[i].locationId+'" type="checkbox" checked/>'+tsDistrictList[i].locationName+'</label></li>';
		  }
	  }
	 str2+='<ul>';
	 $("#apDistrictId").html(str1);
	 $("#tsDistrictId").html(str2);
 }
 $(".closePopUpCls").click(function(){
	$(".cadreRDD").hide(); 
 });
 $(document).on("click",".selectAllApDistrict",function(){
   if($(this).is(":checked")){
	$("#apDistrictUlId li").each(function() {
	  $(this).find("input").prop("checked",true)
	});
   }else{
	 $("#apDistrictUlId li").each(function() {
	  $(this).find("input").prop("checked",false)
	});
   }	
});
$(document).on("click",".selectAllTsDistrict",function(){
   if($(this).is(":checked")){
	$("#tsDistrictULId li").each(function() {
	  $(this).find("input").prop("checked",true)
	});
   }else{
	 $("#tsDistrictULId li").each(function() {
	  $(this).find("input").prop("checked",false)
	});
   }	
});
 $(document).on("click","#settingsCadre",function(e){
	 $("#cadreRegSearchErrorId").html(' ');
	$(this).closest(".moreBlocksCadre").find(".cadreRDD").toggle();
	e.stopPropagation();
});
$(document).on("click","#settingsCadre",function(){
	$(this).closest(".cadreRDD").hide();
});

$(document).on("click","#getCadreRegistrationDetailsBtnId",function(){
	 
	  var renewal2016CheckboxIsChecked="N";
	  var new2016CheckboxIsChecked="N";
	  var cadre2014CheckboxIsChecked="N";
	  if($("#2016RenewalCheckBoxId").is(':checked')){
		  renewal2016CheckboxIsChecked=$("#2016RenewalCheckBoxId").attr("attr_cadre_search_type");
	  }
	  if($("#2016NewCheckBoxId").is(':checked')){
		  new2016CheckboxIsChecked=$("#2016NewCheckBoxId").attr("attr_cadre_search_type");
	  }
	  if($("#2014CadreCheckBoxId").is(':checked')){
		  cadre2014CheckboxIsChecked=$("#2014CadreCheckBoxId").attr("attr_cadre_search_type");
	  }
	  if(renewal2016CheckboxIsChecked=="N" && new2016CheckboxIsChecked=="N" && cadre2014CheckboxIsChecked=="N"){
		  $("#cadreRegSearchErrorId").html("Please Select Search Type");
		  return;
	  }
	 var apDistrictArr=[];
	 var tsDistrictArr=[];
	  $("#apDistrictUlId li").each(function() {
		  if($(this).find("input").is(":checked")){
			  apDistrictArr.push($(this).find("input").attr("id"));
		  }
	   });
	    $("#tsDistrictULId li").each(function() {
		  if($(this).find("input").is(":checked")){
			  tsDistrictArr.push($(this).find("input").attr("id"));
		  }
	   });
	   if(apDistrictArr.length == 0 && tsDistrictArr.length==0 ){
		 $("#cadreRegSearchErrorId").html("Please Select District.");
		   return;
	   }
	   $("#cadreRegSearchErrorId").html(' ');
	   if(apDistrictArr.length > 0){
		getApConstituencyCadreRegistrationDetails(3,apDistrictArr,renewal2016CheckboxIsChecked,new2016CheckboxIsChecked,cadre2014CheckboxIsChecked);  
	   }
	   if(tsDistrictArr.length > 0){
		  getTsConstituencyCadreRegistrationDetails(3,tsDistrictArr,renewal2016CheckboxIsChecked,new2016CheckboxIsChecked,cadre2014CheckboxIsChecked);
	   }
	   $(".cadreRDD").hide();
});
	 
	function getTotalNewRenewalCadreStateWise(globalActivityMemberId){
		var startDate = '';
		var endDate = '';  
		var jsObj={  
			activityMemberId : globalActivityMemberId,
			stateId : globalStateId,         
			startDate : '02/10/2016',      
			endDate : '17/10/2016'
		};
		$.ajax({          
			type : 'GET',    
			url : 'getTotalNewRenewalCadreStateWiseAction.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){        
			
			 if(result != null){
				console.log(result);  
			 }
		});
	}
	function getStateDtls(globalActivityMemberId){
		var startDate = '';    
		var endDate = '';  
		var jsObj={  
			activityMemberId : globalActivityMemberId,
			stateId : globalStateId,         
			startDate : '02/10/2016',      
			endDate : '17/10/2016'
		};
		$.ajax({          
			type : 'GET',       
			url : 'getStateDtls.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){        
			
			 if(result != null){
				console.log(result);  
			 }
		});
	}
	function getSourceOfRegistrationDtls(globalActivityMemberId){
		var startDate = '';    
		var endDate = '';    
		var jsObj={  
			activityMemberId : globalActivityMemberId,
			stateId : globalStateId,         
			startDate : '02/10/2016',      
			endDate : '17/10/2016'
		};
		$.ajax({          
			type : 'GET',       
			url : 'getSourceOfRegistrationDtls.action',    
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){        
			
			 if(result != null){
				console.log(result);  
			 }  
		});
	}
	 