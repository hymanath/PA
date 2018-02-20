var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var globalDistrictLatLagArr=[];
var globalOverviewResult;

$("header").on("click",".menu-cls",function(e){
	e.stopPropagation();
	$(".menu-data-cls").toggle();
});
$(document).on("click",function(){
	$(".menu-data-cls").hide();
});
$("#dateRangePicker").daterangepicker({
	opens:'left',
	startDate: moment().subtract(1,"month"),
	endDate: moment(),
	locale: {
        format: "DD-MM-YYYY",
	},
	ranges: {
	   'Today': [moment(), moment()],
	   'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	   'Last 7 Days': [moment().subtract(6, 'days'), moment()],
	   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
	   'This Month': [moment().startOf('month'), moment().endOf('month')],
	   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
	}
});
$('#dateRangePicker').on('apply.daterangepicker', function(ev, picker) {
	startDate = picker.startDate.format("DD-MM-YYYY");
	endDate = picker.endDate.format("DD-MM-YYYY");
	onLoadCalls();
});
onLoadCalls();
function onLoadCalls(){
  
  
 responsiveTabs();
 getJalavaniDashBoardOverview();	
}
function responsiveTabs()
{
  var $windowWidth = $(window).width();
  if($windowWidth < 768)
  {
    $('[role="tabListMobile"]').show();
    $('[role="tablist"]').hide();
  }else{
    $('[role="tabListMobile"]').hide();
    $('[role="tablist"]').show();
  }
 
  $(document).on("change","[role='tabListMobile']",function(){
    var id = $('option:selected', this).attr('tab_id');
    $("#"+id).closest(".tab-content").find("[role='tabpanel']").removeClass("active");
    $("#"+id).addClass("active");
  });
  
}
  


$(window,document).on('resize', function(){
  responsiveTabs();
  
});

function getJalavaniDashBoardOverview(){
	var json={
		fromDateStr:"01-01-2017",
		toDateStr:"31-01-2018"
	}
	$.ajax({                
   type:'POST',    
   url: 'getJalavaniDashBoardOverview',
   dataType: 'json',
   data : JSON.stringify(json),
   beforeSend :   function(xhr){
     xhr.setRequestHeader("Accept", "application/json");
     xhr.setRequestHeader("Content-Type", "application/json");
   }
 }).done(function(result){
	 buildJalavaniDashBoardOverview(result);
	 buildJalavaniDashBoardAlertsOverview(result);
	 buildJalavaniAlertsOverview(result);
});
}
function buildJalavaniDashBoardAlertsOverview(result)
{
	var str='';
	var totalCnt=0;
	var tabsData=[{"id":"print_media_Id","name":"print_media_icon"},{"id":"electronic_media_Id","name":"electronic_media_icon"},{"id":"call_center_Id","name":"call_center_icon"}];
	str+='<ul class="nav nav-tabs tabs-left Active_State" role="tablist" >';
		str+='<li class="active border_Block">';
			str+='<a href="#all_Sources_Id" data-toggle="tab">';
				str+='<div class="row">';
					str+='<div class="col-sm-9">';
						str+='<div class="media">';
							str+='<div class="media-left">';
							  str+='<img src="Assests/images/icon_all.PNG" class="media-object" style="width:45px">';
							str+='</div>';
							str+='<div class="media-body" style="color:#393938;">';
								str+='<h5 class="media-heading"><b>ALL SOURCES</b></h5>';
								str+='<h3 id="allSourcesCnt"></h3>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
					str+='<div class="col-sm-3">';
						str+='<i class="fas fa-angle-right fa-3x ARI"></i>';
					str+='</div>';
				str+='</div>';		
			str+='</a>';
	str+='</li>';
	for(var i in result.subList1){
		str+='<li class="border_Block">';
			str+='<a href="#'+tabsData[i].id+'" data-toggle="tab">';
				str+='<div class="row">';
					str+='<div class="col-sm-9">';
						str+='<div class="media">';
							str+='<div class="media-left">';
							 str+=' <img src="Assests/images/'+tabsData[i].name+'.PNG" class="media-object" style="width:45px">';
							str+='</div>';
							str+='<div class="media-body" style="color:#393938;">';
								str+='<h5 class="media-heading"><b>'+result.subList1[i].name+'</b></h5>';
								 if(result.subList1[i].name=="Print Media")
								{
								str+='<h3>'+result.satisfiedCount+'</h3>';
								}
								else if(result.subList1[i].name=="Electronic Media") {
									str+='<h3>'+result.unSatisfiedCount+'</h3>';
								}
								else if(result.subList1[i].name=="Call Center") {
									str+='<h3>'+result.count+'</h3>';
								}								
							str+='</div>';
						str+='</div>';
					str+='</div>';
					str+='<div class="col-sm-3">';
						str+='<i class="fas fa-angle-right fa-3x ARI" style="display:none;"></i>';
					str+='</div>';
				str+='</div>';
			str+='</a>';
		str+='</li>';
		totalCnt=totalCnt+result.subList1[i].count;
	}
	str+='</ul>';
	$("#JalavaniDashBoardOverviewTabsId").html(str);
	$("#allSourcesCnt").html(totalCnt);
}	
function buildJalavaniAlertsOverview(result)
{
	var str='';
	var total=0;
	var colors=["575858","F26532","E952A0"];
	str+='<div class="col-sm-2">';
		str+='<div class="pad_10 m_top_bottom_5 box_shad over" style="border: 1px solid #4C9DD6;">';
			str+='<h6 style="color:#4C9DD6;"><b>TOTAL<p>ALERTS</p></b></h6>';
			str+='<h3 class="m_top10" id="alertsCount"></h3>';
		str+='</div>';	
	str+='</div>';
	for(var i in result.subList1){
		str+='<div class="col-sm-2">';
			str+='<div class="pad_10 m_top_bottom_5 box_shad over" style="border: 1px solid #'+colors[i]+';">';
				str+='<h6 style="color:#'+colors[i]+';"><b>'+result.subList1[i].name+'<p>ALERTS</p></b></h6>';
				str+='<h3 class="m_top10">'+result.subList1[i].alertCnt+'</h3>';
			str+='</div>';	
		str+='</div>';
		total=total+result.subList1[i].alertCnt;
	}
	$("#AllSourcesId").html(str);
	$("#alertsCount").html(total);
}
  

function buildJalavaniDashBoardOverview(result)
{
	$("#areasplineChartId").html(spinner);
  $("#stackedColumnChartId").html(spinner);
  var categories=[];
  var percentage=[];
  var status=[];
  var notifiedCnt=[];
  var proposolCnt=[];
  var actionInProgressCnt=[];
  var closed=[];
  var colors=[];
  for(var i in result.subList2)
  {
    categories.push(result.subList2[i].smType.toUpperCase());
    percentage.push(result.subList2[i].percentage);
    //percentage.push(result.subList2[i].percentage);
  }
  for(var i in result.list)
  {
    if(result.list[i].status=="Action In Progress" ||  result.list[i].status=="Notified" ||  result.list[i].status=="Proposal" || result.list[i].status=="Closed")
    {
    status.push(result.list[i].status);
    colors.push(result.list[i].color);
    }
    if(result.list[i].status=="Action In Progress")
    {
      actionInProgressCnt=[0,result.list[i].statusCount,0,0];
    }
    else if(result.list[i].status=="Notified")
    {
      notifiedCnt=[result.list[i].statusCount,0,0,0];
    }
    else if(result.list[i].status=="Proposal")
    {
      proposolCnt=[0,0,0,result.list[i].statusCount];
    }
    else if(result.list[i].status=="Closed")
    {
      closed=[0,0,result.list[i].statusCount,0];
    }
  	
		//percentage.push(result.subList2[i].percentage);
  }
	buildAreaSplineChart("areasplineChartId",categories,percentage);
	buildstackedColumnChart("stackedColumnChartId",status,notifiedCnt,proposolCnt,actionInProgressCnt,closed);
}	


/* buildAreaSplineChart("areasplineChartId");
buildAreaSplineChart("areasplineChartId2");
buildAreaSplineChart("areasplineChartId3");
 */
function buildAreaSplineChart(areasplineChartId,categories,percentage)
{
	$('#'+areasplineChartId).highcharts({
		colors:['#d9edc9'],
	  chart: {
			type: 'areaspline'
		},
		title: {
			text: 'ALERTS - MONTHLY OVERVIEW',
			align: 'left'
		},
		legend: {
			layout: 'vertical',
			align: 'left',
			verticalAlign: 'top',
			x: 150,
			y: 100,
			floating: true,
		enabled: false,
			borderWidth: 1,
			
		},
		xAxis: {
			categories: categories,
			plotBands: [{ // visualize the weekend
				
				color: 'rgba(68, 170, 213, .2)'
			}],
		labels: {
			style: {
				color: '#333',
				fontSize:'14px',
				fontWeight:'bold',
			}
		},
		plotLines: [{
			color: '#BCBCBC', // Color value
			//dashStyle: 'longdashdot', // Style of the plot line. Default to solid
			value: 4, // Value of where the line will appear
			width: 1.5 // Width of the line    
	  }]
		},
		yAxis: {
			title: {
				text: '',
			},
		min: 0, 
		max: 100,
		tickInterval: 10,
		},
		tooltip: {
			
			formatter: function () {
				return '<div style="box-shadow:0px 0px 1px rgba(0,0,0,4);color:#fff;>"<h6><b>'+this.x+'</b></h6><br><h4 style="color:#62CAA1;">'+this.y+'%</h4></div>';
			}
		},
		credits: {
			enabled: false
		},
		plotOptions: {
				areaspline: {
				fillOpacity: 0.5,
				lineColor: '#25CAA1',
			},
		series: {
				marker: {
					fillColor: '#FFFFFF',
					lineColor: '#25CAA1',
					lineWidth: 1,  // inherit from series
				}
			}
		},
		series: [{
			name: '',
			data: percentage
		}]
	});
}

/* buildstackedColumnChart("stackedColumnChartId");
buildstackedColumnChart("stackedColumnChartId2");
buildstackedColumnChart("stackedColumnChartId3");
 */	
function buildstackedColumnChart(stackedColumnChartId,status,notifiedCnt,proposolCnt,actionInProgressCnt,closed)
{
	$('#'+stackedColumnChartId).highcharts({
	  colors:['#DFDFDF','#CC3399','#FFBA00','#009964','#00D2BC'],
		chart: {
			type: 'column'
		},
		title: {
			text: '2175 - ALERTS STATUS',
			align: 'left'
		},
	  credits:{
		enabled:false,
	  },
		xAxis: {
			categories: status,
		labels: {
			style: {
				color: '#333',
				fontSize:'12px',
				fontWeight:'bold',
			 }
		},
		  plotBands: [{ // visualize the weekend
				from: 'Notified',
				to: 'Proposal',
		  color:'red',
				
			}],
		},
		yAxis: {
			min: 0,
		
		tickInterval: 1000,
			title: {
				text: ''
			},
		labels: {
		  enabled:true
		},
			stackLabels: {
				
		  labels: {
			  enabled:false
			},
		
				style: {
					fontWeight: 'bold',
					color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
				}
			}
		},
		legend: {
			align: 'right',
			x: -30,
			verticalAlign: 'top',
			y: 25,
		enabled:false,
			floating: true,
			backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
			borderColor: '#CCC',
			borderWidth: 1,
			shadow: false
		},
		tooltip: {
			formatter: function () {
				console.log(this.series.name);
				return '<div style="box-shadow:0px 0px 1px rgba(0,0,0,4);"><h4>'+this.x+'</h4><br>'+this.series.name+': '+this.y+'</div>';
			}
		},
		
		plotOptions: {
			series: {
					pointWidth: 50,
			  
				},
				column: {
					stacking: 'normal',
					
				},

			},
			series: [{
				name: 'Remained',
				data: [10500-notifiedCnt[0], 10500-actionInProgressCnt[1],10500-closed[2],10500-proposolCnt[3]],
				color:'#DFDFDF',
			  },
			  {
				name: 'Notified',
				data: notifiedCnt,
				color:'#CC3399',
				
			  },
			  {
				name: 'Action In Progress',
				data: actionInProgressCnt,
				color:'#009964',
			  },
			  {
				name: 'Completed & Closed',
				data: closed,
				color:'#00D2BC',
			  },
			  {
				name: 'Proposal',
				data: proposolCnt,
				color:'#FFBA00',
			  },
			  ],
	});
}

buildStackColumnChart("StackColumnChartId");

function buildStackColumnChart(StackColumnChartId)
{
	$('#'+StackColumnChartId).highcharts({
   
		chart: {
			type: 'column'
		},
		title: {
			text: 'Jalavani Call Center IVR feedback',
			align: 'left'
		},
	  credits:{
		enabled:false,
	  },
		xAxis: {
			categories: ['SATISFIED', 'AVG', 'NOT SATISFIED'],
		labels: {
			enabled:false
		},
		  plotBands: [{ // visualize the weekend
				from: 'Notified',
				to: 'Proposal',
		  color:'red',
				
			}],
		},
		yAxis: {
			min: 0,
		max: 250,
		tickInterval: 50,
			title: {
				text: ''
			},
		labels: {
		  enabled:true
		},
			stackLabels: {
				
		  labels: {
			  enabled:false
			},
		
				style: {
					fontWeight: 'bold',
					color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
				}
			}
		},
		legend: {
			align: 'center',
			
			verticalAlign: 'bottom',
			
			enabled:true,
		
			backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
			borderColor: '#CCC',
			borderWidth: 1,
			shadow: false
		},
		tooltip: {
			headerFormat: '<b>{point.x}</b><br/>',
			pointFormat: '{series.name}: {point.y}<br/>Total: {point.stackTotal}'
		},
		
		plotOptions: {
			series: {
					pointWidth: 50,
			  
				},
				column: {
					stacking: 'normal',
					
				},

			},
			series: [{
				name: 'Remaining',
				data: [50, 50,120],
				color:'#DFDFDF',
			  },
			  {
				name: 'SATISFIED',
				data: [233, 0,0],
				color:'#30B349',
				
			  },
			  {
				name: 'AVG',
				data: [0,247,0],
				color:'#FDBB11',
			  },
			  {
				name: 'NOT SATISFIED',
				data: [0,0,173],
				color:'#EE2125',
			  },
			 
			  
			  ],
	});
}	

buildFeedback("FeedbackId");

{
	
	for(var i=0;i<2;i++)
	{
		buildFeedback("FeedbackId"+i);
	}
}
	
function buildFeedback(FeedbackId)
{
	var daysCount=[30,60,90];
	var perCount=[12.5,1.5,0.2];

	var str='';
	for(var i=0; i<=2; i++){
		str+='<div class="col-sm-4">';
			str+='<div class="row">';
				str+='<div class="pad3">';
					str+='<div class="white-block padding_15">';
						str+='<h5>LAST '+daysCount[i]+' DAYS</h5>';
						if(i==0){
							str+='<h4 style="color:red;" class="m_top10">12.5%<span style="margin-left:15px;"><i class="fas fa-arrow-up fa-lg" style="color:red;"></i></span></h4>';
						}else{
							str+='<h4 style="color:#4BB647;" class="m_top10">12.5%<span style="margin-left:15px;"><i class="fas fa-arrow-down fa-lg" style="color:#4BB647;"></i></span></h4>';
						}
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	}
	
	$("#"+FeedbackId).html(str);
}



$(document).on("click","#showModalId",function()
  {
      var str='<div class="row">';
        str+='<div class="col-sm-12 m_top10" expand-main="false">';
          str+='<table style="background-color:#fff;" id="dataTable" class="table table-bordered ">';
            str+='<thead>';
			 str+='<tr>';
              str+='<th class="white-block"></th>';
              str+='<th class="white-block">Id</th>';
              str+='<th class="white-block">Title</th>';
              str+='<th class="white-block"><span class="channel-name">Source</span></th>';
              str+='<th class="white-block"><span class="location-name">Location</span></th>';
              str+='<th class="white-block"><span class="channel-name">Status</span></th>';
              str+='<th class="white-block"><span class="channel-name">Ofcr Name</span></th>';
              str+='<th class="white-block"><span class="channel-name">Ofcr Designation</span></th>';
              str+='<th class="white-block"></th>';
			 str+='</tr>'; 
            str+='</thead>';
            str+='<tbody>';
              str+='<tr>';
                str+='<td>';
                  str+='<i class="glyphicon glyphicon-cog text-danger" style="color:#EFA5B6;margin-right:3px;"></i>';
                str+='</td>';
                str+='<td>'; 
                  str+='53316';
                str+='</td>';
                str+='<td>';
                  str+='<span class="alert-title" data-toggle="tooltip" data-placement="top" title="Water not reaching">Water not reaching</span>';
                str+='</td>';
                str+='<td>';
                  str+='<span data-toggle="tooltip" class="channel-name" data-placement="top" title="Call Center">Call Center</span>';
                str+='</td>';
                str+='<td>';
                  str+='<span data-toggle="tooltip" class="location-name" data-placement="top" title="KASPAPENTAPADU">KASPAPENTAPADU</span>';
                str+='</td>';
                str+='<td class="text-center">';
                  str+='<span class="channel-name" data-toggle="tooltip" data-placement="top" title="Notified">Notified</span>';
                str+='</td>';
                str+='<td class="text-center">';
                  str+='<span class="channel-name" data-toggle="tooltip" data-placement="top" title="Assistant Executive Engineer">Assistant Executive Engineer</span>';
                str+='</td>';
                str+='<td class="text-center">';
                  str+='<span class="channel-name" data-toggle="tooltip" data-placement="top" title="Pentapadu">Pentapadu</span>';
                str+='</td>';
                str+='<td>';
                  str+='<span class="arrow-icon pull-right alertIdCls text-primary" attr_statusid="2" attr_alertid="53316"'; 
                  str+='expand-icon="block1"><i class="glyphicon glyphicon-menu-right"></i></span>';
                str+='</td>';
              str+='</tr>';
            str+='</tbody>';
          str+='</table>';
        str+='</div>';
		str+='<div id="rightSideExpandView"></div>';
      str+='</div>';    
      $("#jalavaniMinisterPopupBody").html(str);
	  $("#dataTable").dataTable();
	  rightSideExpandView();
      $("#jalavaniMinisterPopup").modal('show');
  });

$(document).on("click","[expand-icon]",function(){
        var expandBlockName = $(this).attr("expand-icon");
    var alertId = $(this).attr("attr_alertId");
    //var statusId = $(this).attr("attr_statusId");
    $("[expand-icon]").closest("li").removeClass("active");
    $("[expand-icon]").removeClass("text-primary");
    $(this).addClass("text-primary");
    $(this).closest("li").addClass("active");
    //rightSideExpandView(alertId);
    //glStr = '';
    setTimeout(function(){
      $("[expanded-block="+expandBlockName+"]").show().css("transition"," ease-in, width 0.7s ease-in-out");
    },750);
    setTimeout(function(){
      $("#alertManagementPopup").scrollTop(0);
    },780);
    if($("[expand-main]").attr("expand-main") === 'false')
    {  
      $("[expand-main]").attr("expand-main","true");
      $("[expanded-channel]").attr("expanded-channel","true");
      $("[expand-main]").addClass("col-sm-4").removeClass("col-sm-12").css("transition"," ease-in-out, width 0.7s ease-in-out");
      $("#demo").show();
    }
  });
  
$(document).on("click","[expanded-close]",function(){
    var expandBlockName = $(this).attr("expanded-close");
    if($("[expand-main]").attr("expand-main") === 'true')
    {
      $("[expand-main]").attr("expand-main","false");
    }else{
      $("[expand-main]").attr("expand-main","true");
    }
    $("[expanded-block="+expandBlockName+"]").hide();
    $("[expand-main]").removeClass("col-sm-4").addClass("col-sm-12").css("transition"," ease-in-out, width 0.7s ease-in-out");
  });
 
function rightSideExpandView()
  {
    var str='';
        var str='';
            str+='<div class="col-sm-8 pad_left0" expanded-block="block1" style="display:none;">';
              str+='<div class="panel-right">';
                str+='<div style="box-shadow:0px 0px 2px 2px rgba(0,0,0,0.2)">';
                  str+='<i class="glyphicon glyphicon-remove pull-right" expanded-close="block1" style="cursor:pointer;"></i>';
                    str+='<div class="panel panel-default">';
                      str+='<div class="panel-heading" id="mainBlockStates">';
                        str+='<div class="row">';
                          str+='<div class="col-sm-4">';
                            str+='<div id="assignedUser">';
                              str+='<div class="media">';
                                str+='<div class="media-body">'
                                  str+='<p> ASSIGN TO: <i class="fas fa-level-down-alt"></i></p>';
                                  str+='<p>Superintending Engineer <br> <i class="glyphicon glyphicon-phone"></i> : 9100122100</p>';
                                  str+='<p>Location :  Anantapur</p>';
                                  str+='<p>Dept : RURAL WATER SUPPLY</p>';
                                  str+='<p></p>';
                                str+='</div>';
                              str+='</div>';
                            str+='</div>';
                          str+='</div>';
                          str+='<div class="col-sm-8 pull-right" style="">';
                            str+='<ul class="list-icons list-inline pull-right" status-icon="block1">';
                              str+='<li status-icon-block="alertStatus" attr_alert_id="16214" subalertid="" data-toggle="tooltip" data-placement="top" title="" id="displayStatusId" style="" data-original-title="alert status">';
                                str+='<span class="status-icon arrow-icon" id="statusIdColor" style="background-color: rgb(201, 172, 130);"></span>';
                                str+='<span id="statusId">Reopen</span>';
                              str+='</li>';
                              str+='<li data-toggle="tooltip" data-placement="top" title="" id="proposalId" style="display:none;" data-original-title="Present Proposal Status">';
                                str+='<span class="status-icon arrow-icon"></span>';
                                str+='<span id="presntPrposalstatusId"></span>';
                              str+='</li>';
                            str+='</ul>';
                          str+='</div>';
                        str+='</div>';
                      str+='</div>';
                      str+='<div class="panel-body">';
                        str+='<p>';
                          str+='<i class="fa fa-fire"></i>';
                            str+='Impact Level :';
                              str+='<span id="impactLevel">MANDAL</span>';
                              str+='<span class="text-danger pull-right">';
                                str+='<i class="glyphicon glyphicon-cog"></i>';
                                  str+='Priority:';
                                    str+='<span id="priorityBodyId">High</span>';
                              str+='</span>';
                        str+='</p>';
						str+='<div class="row m_top20">';
							str+='<div class="col-sm-1 text-center body-icons">';
								str+='<i class="fas fa-phone-volume fa-2x"></i>';
							str+='</div>';
							str+='<div class="col-sm-11">';
								str+='<h3>Caller Details </h3>';
								str+='<ul class="list-inline slickSlider">';
									str+='<li style="padding:0px 8px;margin:0px 5px;border:1px solid #ddd;">';
										str+='<table class="table table-condensed">';
											str+='<tbody>';
												str+='<tr>';
													str+='<td>Name</td>';
													str+='<td>: Ismail</td>';
												str+='</tr>';
												str+='<tr>';
													str+='<td>Mobile No</td>';
													str+='<td>: 8317609005</td>';
												str+='</tr>';
												str+='<tr>';
													str+='<td>Caller</td>';
													str+='<td>: CITIZEN</td>';
												str+='</tr>';
											str+='</tbody>';
										str+='</table>';
									str+='</li>';
								str+='</ul>';
							str+='</div>';
						str+='</div>';
                        str+='<div class="row m_top20">';
                          str+='<div class="col-sm-1 text-center body-icons">';
                            str+='<i class="fa fa-check fa-2x"></i>';
                          str+='</div>';
                          str+='<div class="col-sm-11">';
                            str+='<h3>Water not reaching </h3>';
                            str+='<p class="m_top10">Citizen Name:-Ismail Citizen Mobile No:-8317609005 Citizen Address:-Santhajutur Problem:-Citizen informing that they are not getting water supply from last few days,, they don\'t have any alternate source, so they are suffering a lot with water deficiency. Resolution:-Citizen requesting us to solve their problem as soon as possible and provide water supply regularly and sufficiently</p>';
							str+='<p class="m_top10"><small> <i class="fa fa-map-marker"></i> Andhra Pradesh(S),Anantapur(D),MADAKASIRA(C),Amarapuram(M),(P),(H)</small></p>';
                            str+='<p class="m_top10"><small> <i class="fa fa-calendar"></i> Created : 2017-04-21</small></p>';
                          str+='</div>';
                        str+='</div>';
                        str+='<div class="row m_top20">';
                          str+='<div class="col-sm-1 text-center body-icons">';
                            str+='<i class="fa fa-paperclip fa-2x"></i>';
                          str+='</div>';
                          str+='<div class="col-sm-4">';
                            str+='<h4 class="text-muted text-capital">article attachment</h4>';
                            str+='<img class="articleDetailsCls img-responsive m_top20" attr_articleid="685354" src="" style="width: 150px; height: 150px;cursor:pointer">';
                          str+='</div>';
                        str+='</div>';
                        str+='<div class="row m_top20">';
							str+='<div class="col-sm-1 text-center body-icons">';
								str+='<i class="fa fa-tags fa-2x"></i>';
							str+='</div>';
							str+='<div class="col-sm-11">';
								str+='<h4 class="text-muted text-capital">category</h4>';
								str+='<p class="m_top20"><span class="label label-default label-category">DRINKING WATER PROBLEM IN VILLAGES</span></p>';
							str+='</div>';
                        str+='</div>';
                        str+='<div class="row m_top20">';
							str+='<div class="col-sm-1 text-center body-icons">';
								str+='<i class="fa fa-road fa-2x"></i>';
							str+='</div>';
							str+='<div class="col-sm-11">';
								str+='<h4 class="text-muted text-capital">complete alert history</h4>';
									str+='<ul class="pad_left0 m_top10" style="list-style:outside none none">';
										str+='<li>';
											str+='<span class="alert-history-date" style="background-color: lightpink;padding: 3px;border-radius: 5px;">2017-07-10</span>';
											str+='<p class="alert-history-status m_top20 text-capital" style="background-color: lightgrey;padding: 3px;border-radius: 5px;">';
												str+='<span class="status-icon">Action : Status Change</span>';
												str+='<span class="pull-right">';
													str+='<span style="color:slategrey;font-weight:bold;margin-left: 25px">';
													str+='Time </span> :';
													str+='<span style="font-size:10px">  12:06:02 AM </span>';
												str+='</span>';
											str+='</p>';
											str+='<p class="m_top20 text-capital myfontStyle">';
												str+='<span style="color:slategrey;font-weight:bold;margin-left: 25px">';
												str+='Status </span> :Reopen';
											str+='</p>';
											str+='<p class="alert-history-body m_top5 text-capital myfontStyle">';
												str+='<span style="color:slategrey;font-weight:bold;margin-left: 25px"> Comment </span>:';
												str+='Please enter action taken report immediately.';
											str+='</p>';
											str+='<p class=" alert-history-user m_top20 text-capital ">';
												str+='<span style="color:slategrey;font-weight:bold;margin-left: 25px">UPDATED BY:</span>';   
												str+='<span style="font-size:10px">  RWS_CE4_AP  </span>';
											str+='</p>';
										str+='</li>';
									str+='</ul>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
						
						str+='<div class="panel-footer white-block">';
									str+='<div class="row">';
										str+='<div class="col-sm-1 text-center">';
											str+='<span class="icon-name icon-primary">Ra</span>';
										str+='</div>';
										str+='<div class="col-sm-11">';
											str+='<div class="panel panel-default panel-border-white">';
												str+='<div class="panel-heading" style="display: none;">';
													str+='<label class="radio-inline" style="margin-bottom: 5px;">';
													str+='<input value="te" name="language1" class="lang" id="telugu1" checked="true" onclick="languageChangeHandler1();" type="radio">Telugu</label>';
													str+='<label class="radio-inline" style="margin-bottom: 5px;">';
													str+='<input value="en" name="language1" class="lang" id="eng1" onclick="languageChangeHandler1();" type="radio">English</label>';
												str+='</div>';
												str+='<div class="panel-body">';
													str+='<div class="comment-area">Comment Here</div>';
														str+='<textarea class="form-control comment-area" id="alertCommentId1" placeholder="Comment here..." style="line-height: 1.5em; font-family: Arial, Helvetica, sans-serif; font-size: 14px; display: none;"></textarea>';
													str+='</div>';
													str+='<div class="panel-footer text-right" style="display: none;">';
														str+='<button class="btn btn-primary comment-btn commentChangeCls" attr_alert_id="16442" subalertid="" id="commentChangeId">Save</button>';
														str+='<span id="commentPostingSpinner" style="height:50px;width:50px"></span>';
													str+='</div>';
												str+='</div>';
											str+='</div>';
										str+='</div>';
								str+='</div>';	
						
					str+='</div>';
				str+='</div>';
			str+='</div>';
				
                    
      $("#rightSideExpandView").html(str);
  }
  $(document).on("click","[role='tablist']",function(){
    $(this).find('li .ARI').hide();
    $(this).find('li.active .ARI').show();
    
  });

