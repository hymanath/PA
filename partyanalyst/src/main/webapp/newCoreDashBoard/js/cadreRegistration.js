
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

	function cadreRegistrationBasicCall(){
		showCadreRegistreredCount();
		getEnumeratorsInfo();
	}

	
	function showCadreRegistreredCount(){
		
		$("#totalTodayCadreRegistrationBlockDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		
		
		$.ajax({
			type : 'GET',
			url : 'showCadreRegistreredCountAction.action',
			dataType : 'json',
			data : {}
		}).done(function(result){
            if(result != null){
				buildTotalTodayRegistrations(result);
			}else{
				$("#totalTodayCadreRegistrationBlockDivId").html('NO DATA AVAILABLE');
			}	
		});	
           
	}
	
	function buildTotalTodayRegistrations(result){
		
	 var str= '';
	 // Total today Registrations block
	  str+='<div class="row m_top10">';
			str+='<div class="col-md-6 col-xs-12 col-sm-12">';
				str+='<div class="bg_ED pad_15">';
					str+='<div class="row">';
						str+='<div class="col-md-5 col-xs-12 col-sm-12 pad_right0">';
							str+='<p style="font-size: 12px">Total Target</p> <h4 class="cadreCount">75000</span></h4>';
						str+='</div>';
						var totalAchievedPercantage = '0';
						if(result.totalCount != null && result.totalCount > 0){
			               totalAchievedPercantage =  ((result.totalCount*100)/(75000)).toFixed(1) ;
		                }
						str+='<div class="col-md-7 col-xs-12 col-sm-12 pad_left0 m_top10">';
							str+='<h5 class="text-success">Achieved %<span class="cadreCount1 pull-right">'+totalAchievedPercantage+'</span></h5>';
						str+='</div>';
						
					str+='</div>';
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
				str+='</div>';
				str+='<div id="totalOverAllRegistrationGraph" class="chartLiD" style="height:120px" ></div>';
			str+='</div>';
			
			str+='<div class="col-md-6 col-xs-12 col-sm-12">';
				str+='<div class="bg_ED pad_15">';
					str+='<div class="row">';
						str+='<div class="col-md-5 col-xs-12 col-sm-12 pad_right0">';
							str+='<p style="font-size:12px;">Today Target</p> <h4 class="cadreCount">9000</span></h4>';
						str+='</div>';
						var todayAchievedPercantage = '0';
						if(result.todayTotalCount !=null && result.todayTotalCount > 0){
			               todayAchievedPercantage =  ((result.todayTotalCount*100)/(9000)).toFixed(1) ;
		                }
						str+='<div class="col-md-7 col-xs-12 col-sm-12 pad_left0 m_top10">';
							str+='<h5 class="text-success">Achieved %<span class="cadreCount1 pull-right">'+todayAchievedPercantage+'</span></h5>';
						str+='</div>';
					str+='</div>';
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
				str+='</div>';
				str+='<div id="todayOverAllRegistrationGraph" class="chartLiD" style="height:120px" ></div>';
			str+='</div>';
		str+='</div>';
		
		$("#totalTodayCadreRegistrationBlockDivId").html(str);
		$('.cadreCount').each(function () {
				$(this).prop('Counter',0).animate({
					Counter: $(this).text()
				}, {
					duration: 1500,
					easing: 'swing',
					step: function (now) {
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
		
		$.ajax({
			type : 'GET',
			url : 'getEnumeratorsInfoAction.action',
			dataType : 'json',
			data : {}
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
								str1+='<h5>Started<br> Contituencies</h5>';
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
							str1+='<h3 class="EnumCadreCount cadreCount">'+emptyCheck(result.todaySubmittedCount)+'</h3>';
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
					str+='<th rowspan="2">Booth Name</th>';
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
		$(".showTabUserWiseDetails").show();
		getCadreRegistrationCountByConstituency(constituencyId,fromDate.trim(),toDate.trim());
});  
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
			str+='<table class="table table-bordered table-condensed" id="tabUserWiseReportDataTableId"> ';
				str+='<thead> ';
					str+='<tr>';
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
						str+='<tr> ';
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
	
	getCadreRecentTime();
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
  }, 60 * 5000);
  
  setInterval(function() {
    getCadreRecentTime();
  }, 60 * 1000);
function setLastUpdatedTime(lastUpdatedTime){
	$("#lastUpdatedTimeCadreId").html(" Last Updated : "+lastUpdatedTime+"");
}