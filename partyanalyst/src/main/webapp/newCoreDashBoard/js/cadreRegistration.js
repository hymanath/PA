
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

getOverAllRegistrationgraph();
function getOverAllRegistrationgraph(){
	
	$(function () {
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
					headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
					pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.1f}%</b>'
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
								return Highcharts.numberFormat(this.y,1) + '%';
							}
						}
					  
					}
				}
			},
			series: [{
				name: 'Renewal',
				data: [50]
			}, {
				name: 'New',
				data: [20]
			}]
		});
	});
	
	$(function () {
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
								return Highcharts.numberFormat(this.y,1) + '%';
							}
						}
					  
					}
				}
			},
			series: [{
				name: 'John',
				data: [50]
			}, {
				name: 'Jane',
				data: [20]
			}]
		});
	});
}
getConstituencyDetailedReport();
function getConstituencyDetailedReport(){
	
	var str='';
	
	str+='<div class="row">';
    str+='<div class="col-md-12 col-xs-12 col-sm-12">';
		str+='<h4 class="text-center text-capital">Kuppam Constitency Detailed Report</h4>';
		str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
		str+='<label class="radio-inline">';
		  str+='<input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1" style="margin-top: 0px;" checked><h5>Booth Wise</h5>';
		str+='</label>';
		str+='<label class="radio-inline">';
		  str+='<input type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2" style="margin-top: 0px;"><h5>Panchayat Wise</h5>';
		str+='</label>';
		str+='<label class="radio-inline">';
		  str+='<input type="radio" name="inlineRadioOptions" id="inlineRadio3" value="option3" style="margin-top: 0px;"><h5>Mandal Wise</h5>';
		str+='</label>';
		str+='</div>';
		str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
		str+='<table class="table table-bordered table-condensed"> ';
			str+='<thead> ';
				str+='<tr>';
					str+='<th>Mandal</th>';
					str+='<th>Panchayat</th>';
					str+='<th>Booth</th>';
					str+='<th>Total Voters</th>';
					str+='<th>2014 Cadre Count</th>';
					str+='<th>2016 Cadre Count</th>';
				str+='</tr>'; 
			str+='</thead>'; 
			str+='<tbody>';
			str+='<tr> ';
				str+='<td>Santhipuram</td> ';
				str+='<td>Gudipalli</td>';
				str+='<td>210</td>';
				str+='<td>2000</td>';
				str+='<td>2000</td>';
				str+='<td>2000</td>';
			str+='</tr>';
			str+='<tr> ';
				str+='<td>Santhipuram</td> ';
				str+='<td>Gudipalli</td>';
				str+='<td>210</td>';
				str+='<td>2000</td>';
				str+='<td>2000</td>';
				str+='<td>2000</td>';
			str+='</tr>';
			str+='<tr> ';
				str+='<td>Santhipuram</td> ';
				str+='<td>Gudipalli</td>';
				str+='<td>210</td>';
				str+='<td>2000</td>';
				str+='<td>2000</td>';
				str+='<td>2000</td>';
			str+='</tr>';
		str+='</tbody>'; 
	str+='</table>';
		str+='</div>';
	str+='</div>';
	str+='</div>';
								
	$("#constituenctDetailedReport").html(str);
}
	
	function getTabUserWiseReport(){
		
		var str='';
		str+='<table class="table table-bordered table-condensed"> ';
			str+='<thead> ';
				str+='<tr>';
					str+='<th>Tab UserId</th>';
					str+='<th>Tab UserInfoId</th>';
					str+='<th>Name</th>';
					str+='<th>Image</th>';
					str+='<th>MobileNo</th>';
					str+='<th>No.Of Samples</th>';
					str+='<th>First Record Time</th>';
					str+='<th>Second Record Time</th>';
				str+='</tr>'; 
			str+='</thead>'; 
			str+='<tbody>';
			str+='<tr> ';
				str+='<td>1000</td> ';
				str+='<td>2560</td>';
				str+='<td>Registration</td>';
				str+='<td>9999999999</td>';
				str+='<td>2000</td>';
				str+='<td class="noOfSamplesDetailsPopUpView" style="cursor:pointer;">2000</td>';
				str+='<td>2000</td>';
				str+='<td>2000</td>';
			str+='</tr>';
			str+='<tr> ';
				str+='<td>1000</td> ';
				str+='<td>2560</td>';
				str+='<td>Registration</td>';
				str+='<td>9999999999</td>';
				str+='<td>2000</td>';
				str+='<td>2000</td>';
				str+='<td>2000</td>';
				str+='<td>2000</td>';
			str+='</tr>';
			str+='<tr> ';
				str+='<td>1000</td> ';
				str+='<td>2560</td>';
				str+='<td>Registration</td>';
				str+='<td>9999999999</td>';
				str+='<td>2000</td>';
				str+='<td>2000</td>';
				str+='<td>2000</td>';
				str+='<td>2000</td>';
			str+='</tr>';
		str+='</tbody>'; 
	str+='</table>';
	
	
		$("#tabUserWiseReportDiv").html(str);
	}
	
	$(document).on("click",".tabUserWiseDetails",function(){
		$(".showTabUserWiseDetails").show();
		getTabUserWiseReport();
	});
	$(document).on("click",".noOfSamplesDetailsPopUpView",function(){
		$("#noOfSamplesModal").modal("show");
		
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
			str+='<tr> ';
				str+='<td>day1</td> ';
				str+='<td>2560</td>';
				str+='<td>2000</td>';
				str+='<td>2000</td>';
			str+='</tr>';
			str+='<tr> ';
				str+='<td>day1</td> ';
				str+='<td>2560</td>';
				str+='<td>2000</td>';
				str+='<td>2000</td>';
			str+='</tr>';
			str+='<tr> ';
				str+='<td>day1</td> ';
				str+='<td>2560</td>';
				str+='<td>2000</td>';
				str+='<td>2000</td>';
			str+='</tr>';
		str+='</tbody>'; 
	str+='</table>';
	
	
		$("#noOfSamplesDetailsDiv").html(str);
		
	});