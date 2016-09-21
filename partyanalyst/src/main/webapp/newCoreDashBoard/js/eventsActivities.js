 var globalStateId=1; //default AP
 var globalUserWiseEventMemberRslt;
 /* EVENT FUNCTIONALITY END*/  
  $(document).on("click",".eventsIconExpand",function(){
	$(".dateRangePickerClsForEvents").toggleClass("hide");
	$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
	$(".eventsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	$(".eventsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
	if($(this).find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		
		var eventIdsString="7,30";
		
		$(".moreEventsBlocksIcon").attr("attr_type","event");
	    $(".moreEventsBlocksIcon").attr("attr_event_idsString",eventIdsString);
		$(".detailedEvent").attr("attr_type","event");
	    $(".detailedEvent").attr("attr_event_idsString",eventIdsString);
		$(".comparisonEvent").attr("attr_type","event");
		$(".comparisonEvent").attr("attr_event_idsString",eventIdsString);
		
		getUserTypeWiseTotalInviteeAndInviteeAttendedCnt(eventIdsString);
		$(".eventsHiddenBlock,.moreEventsBlocksIcon").show();
	}else{
		$(".eventsHiddenBlock,.moreEventsBlocks,.comparisonBlockEvents").hide();
	}
	if( $(".trainingIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".dateRangePickerClsForTraining").addClass("hide");
		$(".trainingIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".trainingsHiddenBlock,.moreTrainingBlocks,.moreTrainingBlocksIcon").hide();
		$(".moreTrainingBlocksIcon").removeClass("unExpandTrainingBlock");
		$(".trainingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}else if( $(".debatesIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".debatesIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".debatesMoreHiddenBlock,.debatesHiddenBlock,.dateRangePickerClsForDebates").hide();
		$(".moreDebatesBlocksIcon").removeClass("unExpandDebatesBlock");
		$(".debatesBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}else if( $(".iconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".iconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".committeesHiddenBlock,.moreBlocks,.moreBlocks1,.moreBlocksDetailAndComp,.moreBlocksIcon,.moreBlocksDistrictlevel").hide();
		$(".committeesBlock,.basicCommitteesBlock,.userTypeCommitteesBlock,.committeesBlock1").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".dateRangePickerCls").toggleClass("hide");
		$(".moreBlocksIcon").removeClass("unExpandBlock");
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
	}
});

$(document).on("click",".moreEventsBlocksIcon",function(){
	var type=$(this).attr("attr_type");
	var attrEventIdsString=$(this).attr("attr_event_idsString");
	if(type != null && type=="event"){
	 $(".moreEventsBlocks").toggle();
	 getLocationWiseByInviteeAttendedAndInviteeAttendedCntBasedOnUserType(attrEventIdsString);
	 getSelectedEventDetails(attrEventIdsString);
	 $(".detailedBlockEvents,.activeUlCls").show();
	 $(".detailedEvent").addClass("active")	
	}else{
		// activity functionality
	}
});
$(document).on("click",".detailedEvent",function(){
	$(".detailedBlockEvents").show();
	$(".comparisonBlockEvents").hide();
	//var type=$(this).attr("attr_type");
	var attrEventIdsString=$(this).attr("attr_event_idsString");
	getLocationWiseByInviteeAttendedAndInviteeAttendedCntBasedOnUserType(attrEventIdsString);
	getSelectedEventDetails(attrEventIdsString);	
});
$(document).on("click",".comparisonEvent",function(){
	$(".comparisonBlockEvents").show();
	$(".detailedBlockEvents").hide();
	//var type=$(this).attr("attr_type");
	var attrEventIdsString=$(this).attr("attr_event_idsString");
	getAllItsSubUserTypeIdsByParentUserTypeIdForEvent(attrEventIdsString);
});
$(document).on("click",".eventCls",function(){
	var eventIdsString = $(this).attr("attr_event_idsString");
	$(".moreEventsBlocksIcon").attr("attr_type","event");
	$(".moreEventsBlocksIcon").attr("attr_event_idsString",eventIdsString);
	$(".detailedEvent").attr("attr_type","event");
	$(".detailedEvent").attr("attr_event_idsString",eventIdsString);
	$(".comparisonEvent").attr("attr_type","event");
	$(".comparisonEvent").attr("attr_event_idsString",eventIdsString);
	getUserTypeWiseTotalInviteeAndInviteeAttendedCnt(eventIdsString);
});

$(document).on("click",".eventStrngPrCls",function(){
	var memberType=$(this).attr("attr_value");
	 if(memberType != null && memberType == "strong"){ 
		buildUserTypeWiseTotalInviteeAndInviteeAttendedCnt(globalUserWiseEventMemberRslt); 
	 }else if(memberType == "poor"){
	  buildUserTypeWisePoorInviteeAttendedEventMemDtlsCnt(globalUserWiseEventMemberRslt);
	 }
});

$(document).on("click",".eventsListExpandIcon",function(){
	
	$(".eventsListExpandIcon").find("i").addClass("glyphicon-fullscreen").removeClass("glyphicon-resize-small");
	$(this).find("i").removeClass("glyphicon-fullscreen").addClass("glyphicon-resize-small");
	if($(".eventsIconExpand").find("i").hasClass("glyphicon-fullscreen"))
	{
		$(".eventsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".eventsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
	}
	$(".eventsIconExpand").find("i").removeClass("glyphicon-fullscreen").addClass("glyphicon-resize-small");
});
$("#dateRangeIdForEvents").daterangepicker({
	opens: 'left',
	startDate: moment().subtract(1, 'month').startOf('month'),
	endDate: moment().subtract(1, 'month').endOf('month'),
	locale: {
	  format: 'DD/MM/YYYY'
	},
	ranges: {
	   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
	   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
	   'Last 3 Months': [moment().subtract(3, 'month'), moment()],
	   'Last 6 Months': [moment().subtract(6, 'month'), moment()],
	   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
	   'This Month': [moment().startOf('month'), moment()],
	   'This Year': [moment().startOf('Year'), moment()]
	}
})
$('#dateRangeIdForEvents').on('apply.daterangepicker', function(ev, picker) {
  customStartDate = picker.startDate.format('DD/MM/YYYY');
  customEndDate = picker.endDate.format('DD/MM/YYYY');  
});

function getEventBasicCntDtls(){
	$("#mainEventsList").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	var eventIds=[];
	eventIds.push(7);
	eventIds.push(30);
	
	var jsObj ={ 
				 activityMemberId : globalActivityMemberId,
				 stateId : globalStateId,
				 eventIds:eventIds
				 
			  }
	$.ajax({
		type : 'POST',
		url : 'getEventBasicCntDtlsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0 ){
		 buildEventBasicCntDtls(result);	
		}else{
		 $("#mainEventsList").html("NO DATA AVAILABLE.");	
		}
	});
}
function buildEventBasicCntDtls(result)
{
	var eventIdsString;
	var str=' ';
	str+='<div class="panel-group m_top10" id="accordion" role="tablist" aria-multiselectable="true">';
		for(var i in result)
		{
			if(i== 0){
			eventIdsString = result[i].id;	
			}else{
			 eventIdsString = eventIdsString+','+result[i].id;	
			}
			str+='<div class="panel panel-default panelNewEvents">';
				str+='<div class="panel-heading" role="tab" id="headingEvents'+i+'">';
					if(i == 0)
					{
						str+='<h4 class="panel-title">';
							str+='<a role="button" class="collapseDebatesIcon collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseEvents'+i+'" aria-expanded="true" aria-controls="collapseEvents'+i+'">'+result[i].name+'';
							str+='</a>';
							str+='<span attr_event_idsString='+result[i].id+' class="eventsListExpandIcon eventCls" style="background-color:#fff;font-size:10px;margin-left:5px;"><i class="glyphicon glyphicon-fullscreen"></i></span>';
						str+='</h4>';
					}else{
						str+='<h4 class="panel-title">';
							str+='<a role="button" class="collapseDebatesIcon collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseEvents'+i+'" aria-expanded="true" aria-controls="collapseEvents'+i+'">'+result[i].name+'';
							str+='</a>';
							str+='<span attr_event_idsString='+result[i].id+' class="eventsListExpandIcon eventCls" style="background-color:#fff;font-size:10px;margin-left:5px;"><i class="glyphicon glyphicon-fullscreen"></i></span>';
						str+='</h4>';
					}
					
				str+='</div>';
				if(i == 0)
				{
					str+='<div id="collapseEvents'+i+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingEvents'+i+'">';
				}else{
					str+='<div id="collapseEvents'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingEvents'+i+'">';
				}
					str+='<div class="panel-body pad_5">';
						str+='<div class="row">';
							str+='<div class="col-md-12 col-xs-12 col-sm-12">';
								str+='<table class="table tableTraining bg_ED">';
									str+='<tr>';
										str+='<td>';
											str+='<h4>'+result[i].inviteeCount+'</h4>';
											str+='<p class="text-capital text-muted">invited</p>';
										str+='</td>';
										str+='<td>';
											str+='<h4>'+result[i].inviteeAttendedCount+'';
											str+=' <small class="text-danger responsiveFont">'+result[i].inviteeAttendedCounPer+'%</small></h4>';
											str+='<p class="text-capital text-muted">invitees attended</p>';
										str+='</td>';
										str+='<td>';
											str+='<h4>'+result[i].nonInviteeAttendedCount+'';
											str+=' <small class="text-danger responsiveFont">'+result[i].nonInviteeAttendedCountPer+'%</small></h4>';
											str+='<p class="text-capital text-muted">non invitees attended</p>';
										str+='</td>';
									str+='</tr>';
								str+='</table>';
							str+='</div>';
						str+='</div>';
						
					str+='</div>';
				str+='</div>';
			str+='</div>';
		}
	str+='</div>';
	str+='';
	$("#eventIds").attr("attr_event_idsString",eventIdsString); 
	$("#mainEventsList").html(str)
}

function getUserTypeWiseTotalInviteeAndInviteeAttendedCnt(eventIdsString){
	$("#UserTypeWiseEventMemberDtslDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	var eventIds= eventIdsString.split(",");
	var jsObj ={ 
				 activityMemberId : globalActivityMemberId,
				 stateId : globalStateId,
				 eventIds:eventIds,
				 userTypeId : globalUserTypeId
			  }
	$.ajax({
		type : 'POST',
		url : 'getUserTypeWiseTotalInviteeAndInviteeAttendedCntAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		 $("#UserTypeWiseEventMemberDtslDivId").html(' ');
		 globalUserWiseEventMemberRslt = result;
		 buildUserTypeWiseTotalInviteeAndInviteeAttendedCnt(result);
	});
}

function buildUserTypeWiseTotalInviteeAndInviteeAttendedCnt(result){
	var str='';
	if(result != null && result.length > 0){
		for(var i in result){
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				str+='<h5 class="text-capital">'+result[i][0].userType+'</h5>';
				str+='<div id="eventsCountGraph'+i+'" style="height:100px;"></div>';
			str+='</div>'
				
		}
		
	}
	$("#UserTypeWiseEventMemberDtslDivId").html(str);
	if(result != null && result.length > 0){
		for(var i in result){
			var eventInviteeAttendedPerArr = [];
			var countVar =0;
			if(result[i] !=null && result[i].length>0){
				for(var j in result[i]){
					 var obj1 = {
							name: result[i][j].name,
							y: result[i][j].inviteeAttendedCntPer
						};
					eventInviteeAttendedPerArr.push(obj1);
					countVar =countVar+1;
					if (countVar === 5) {
						break;
					}
				}
			}
			
				
			//if( result[i][j].inviteeAttendedCntPer !=0){
				$(function () {
					 $("#eventsCountGraph"+i).highcharts({
						 colors: ['#0066DC'],
						chart: {
							type: 'column'
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
							
							type: 'category',
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
								text: ''
							},
							labels: {
								enabled:false
							}
						},
						legend: {
							enabled: false
						},
						
								
						plotOptions: {
							column: {
								stacking: 'normal',
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

						tooltip: {
							headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
							pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.1f}%</b>'
						},

						series: [{
							name: 'Invitees Attended',
							data: eventInviteeAttendedPerArr
						}],
					 
					});
				});
			/* } else{
				$("#eventsCountGraph"+i).html("No Data Available");
				$("#eventsCountGraph"+i).css("height","35px");
					
			}  */
			
		}
	}else{
		$("#UserTypeWiseEventMemberDtslDivId").html("No Data Available");
	}
	
}
function buildUserTypeWisePoorInviteeAttendedEventMemDtlsCnt(result){
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
				str+='<div id="eventsCountGraph'+i+'" style="width:100%;height:100px;"></div>';
				str+='</div>'
			}
		}
		$("#UserTypeWiseEventMemberDtslDivId").html(str);
	if(result != null && result.length > 0){
		for(var i in result){
				var candidateNameArray = [];
				var eventInviteeAttendedPerArr = [];
				var countVar = 0;
				var length = result[i].length - 1;
				for(var j = length; j >= 0; j--){
					candidateNameArray.push(result[i][j].name);
					eventInviteeAttendedPerArr.push(result[i][j].inviteeAttendedCntPer);
					countVar =countVar+1;
					if (countVar === 5) {
						break;
					}
				}
			//if( result[i][j].totalAttenedCountPer!=0){
			//var getWidth = $("#eventsCountGraph"+i).parent().width()+'px';
				//$("#eventsCountGraph"+i).width(getWidth);
				$(function () {
			   $('#eventsCountGraph'+i).highcharts({
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
				pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b>',
				valueSuffix: '%'
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
					name: 'Invitees Attended',
					data: eventInviteeAttendedPerArr
				}]
			});
		});
		/* }else{
		$("#genSecTraining"+i).html("No Data Available");
		$("#genSecTraining"+i).css("height","35px");	
		} */
		}
	}else{
	 $("#UserTypeWiseEventMemberDtslDivId").html('NO DATA AVAILABLE.');
	}	
	
}
function getLocationWiseByInviteeAttendedAndInviteeAttendedCntBasedOnUserType(attrEventIdsString){
	$("#eventsDistWiseCohort").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
    var eventIds = attrEventIdsString.split(",");
	var jsObj ={ 
		 activityMemberId : globalActivityMemberId,
		 stateId : globalStateId,
		 eventIds:eventIds,
		 userTypeId : globalUserTypeId
		 
	  }
	$.ajax({
		type : 'POST',
		url : 'getLocationWiseByInviteeAttendedAndInviteeAttendedCntBasedOnUserTypeAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		buildLocationWiseByInviteeAttendedAndInviteeAttendedCntBasedOnUserType(result);
	});
}
function buildLocationWiseByInviteeAttendedAndInviteeAttendedCntBasedOnUserType(result)
{
	var str='';
	for(var i in result)
	{
		str+='<h4 class="panel-title">'+result[i].name+'</h4>';
		str+='<div id="eventsGraph'+i+'" style="height:120px"></div>';
	}
	$("#eventsDistWiseCohort").html(str)
	if(result != null && result.length > 0){
		for(var i in result){
			
			var inviteesCounts = [];
			var nonInviteesCounts = [];
			var candidateNames = [];
			var countVar =0;
			
			
			for(var j in result[i].locationList){
				candidateNames.push(result[i].locationList[j].name)
				inviteesCounts.push(result[i].locationList[j].inviteeAttendedCounPer)
				nonInviteesCounts.push(result[i].locationList[j].nonInviteeAttendedCountPer)
			}
			
			$(function () {
				 $("#eventsGraph"+i).highcharts({
					colors: ['#D33E39','#64C664'],
					chart: {
						type: 'column'
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
						categories: candidateNames,
						type: 'category',
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
							text: ''
						},
						labels: {
							enabled:false
						}
					},
					legend: {
						enabled: false
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

					 tooltip: {
						 pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.percentage:.1f}%</b><br/>',
						/* formatter: function () {
							var s = '<b>' + this.x + '</b>';

							$.each(this.points, function () {
								s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
									Highcharts.numberFormat(this.percentage,1)+'%' +' - ' +
									(this.y);
							});

							return s;
						}, */
						shared: true
					},

					series: [{
						name: 'Invitees Attended',
						data: inviteesCounts,
						
					},{
						name: 'Non Invitees Attended',
						data: nonInviteesCounts,
						
					}],
				 
				});
			});
		}
	}else{
		$("#eventsDistWiseCohort").html("No Data Available");
	}
}
function getSelectedEventDetails(attrEventIdsString){
	$("#eventsGraphBlock").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	var eventIds=attrEventIdsString.split(",");
	var jsObj ={ 
		activityMemberId : globalActivityMemberId,
		stateId : globalStateId,
		eventIds:eventIds
	}
	$.ajax({
		type : 'POST',
		url : 'getEventBasicCntDtlsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0){
		  buildSelectedEventDetails(result);	
		}else{
		  $("#eventsGraphBlock").html("NO DATA AVAILABLE.");	
		}
	});	
}
function buildSelectedEventDetails(result)
{
	var str=' ';
	if(result.length > 3)
	{
		str+='<div class="scroll-div">';
	}
		str+='<ul class="list-inline best-matched-profile">';
			for(var i in result)
			{
				str+='<li><h4>'+result[i].name+'</h4>';
				str+='<div id="events'+i+'" class="chartLi"></div></li>';
			}
			
		str+='</ul>';
	if(result.length > 3)
	{
		str+='</div>';
	}
	
	$("#eventsGraphBlock").html(str);

	for(var i in result)
	{
		var eventsInviteeNoNAttendedCountPer= [];
		var eventsInviteeAttendedCounPer=[];
		eventsInviteeNoNAttendedCountPer.push(result[i].nonInviteeAttendedCountPer);
		eventsInviteeAttendedCounPer.push(result[i].inviteeAttendedCounPer);
		$(function () {
			$('#events'+i+'').highcharts({
				colors: ['#F56800','#53BF8B','#66728C'],
				chart: {
					type: 'column',
					
				},
				title: {
					text: ' ',
					style: {
						fontSize: '16px',
						fontFamily: '"Helvetica Neue",Helvetica,Arial,sans-serif',
						textTransform: "uppercase"
					}
				},
				subtitle: {
					text: null
				},
				 xAxis: {
					 min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: null,
					labels: {
						enabled: false,
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
						enabled: true,
						style: {
							fontWeight: 'bold',
							color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
						}
					}
				},
				tooltip: {
					pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.percentage:.1f}%</b><br/>',
					shared: true
				},
				legend: {
					enabled: true,
					align: 'left'
			
				},
				plotOptions: {
					column: {
						stacking: 'normal',
						dataLabels:{
							enabled: true,
							formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return Highcharts.numberFormat(this.y,1) + '%';
								}
							}
						},
						
					},
				},
				 series: [{
					name: 'Invitees Attended',
					data: eventsInviteeAttendedCounPer 
				}, {
					name: 'Non Invitees Attended',
					data: eventsInviteeNoNAttendedCountPer
				}]
			});
		});	
	}
	
}

$(document).on("click",".allItsSubUserTypeClsForEvent",function(){
	var childUserTypeId = $(this).attr("attr_userTypeId");
    var attrEventIdsString = $(this).attr("attr_event_idsString");
	getSelectedChildTypeMembersForEvent(childUserTypeId,attrEventIdsString);
});
 $(document).on("click",".remveSlcUsrTypeForEvent",function(){
		 var removeSelected = $(this).attr("attr_remove_SelecUserType"); 
		 $("#"+removeSelected).remove();
 });
 $(document).on("click",".childEventMemberCls",function(){
	    
		$(".slickPanelSliderForEvent").find("li").find(".panelSlick").removeClass("panelActiveSlick");
		$(this).find(".panelSlick").addClass("panelActiveSlick");
	    var activityMemberId = $(this).attr("attr_activitymemberid");  
		var userTypeId = $(this).attr("attr_usertypeid"); 
    	var selectedMemberName = $(this).attr("attr_selectedmembername");  
		var selectedUserType = $(this).attr("attr_selectedusertype"); 	
		var childActivityMemberId = $(this).attr("attr_id");  
		var attrEventIdsString = $(this).attr("attr_event_idsString");
		getDirectChildTypeMembersForEvent(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId,attrEventIdsString);
		getEventPoorPerformanceLocation(userTypeId,activityMemberId,selectedMemberName,selectedUserType,attrEventIdsString);
});
$(document).on("click",".subLevelEventMemberCls",function(){
	    $(this).closest('tr').next('tr.showHideTr').show(); 
		var activityMemberId = $(this).attr("attr_activitymemberid");  
		var userTypeId = $(this).attr("attr_usertypeid"); 
		var selectedMemberName = $(this).attr("attr_selectedmembername");  
		var selectedUserType = $(this).attr("attr_selectedusertype");  
		var childActivityMemberId = $(this).closest('tr').next('tr.showHideTr').attr("attr_id");  
	    var attrEventIdsString = $(this).attr("attr_event_idsString");
		getDirectChildTypeMembersForEvent(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId,attrEventIdsString);
		getEventPoorPerformanceLocation(userTypeId,activityMemberId,selectedMemberName,selectedUserType,attrEventIdsString);
});
 function getAllItsSubUserTypeIdsByParentUserTypeIdForEvent(attrEventIdsString){
		 $("#allItsSubUserTypeIdsByParentUserTypeDivIdForEvent").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var jsObj = {parentUserTypeId : globalUserTypeId}
		$.ajax({
			type : 'POST',
			url : 'getAllItsSubUserTypeIdsByParentUserTypeIdAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#allItsSubUserTypeIdsByParentUserTypeDivIdForEvent").html(" ");	
			if(result != null && result.length > 0){
			buildgetChildUserTypesByItsParentUserTypeForEvent(result,attrEventIdsString);	
			}else{
			$("#allItsSubUserTypeIdsByParentUserTypeDivIdForEvent").html("NO DATA AVAILABLE");	
			}
		});		 
	}
	
function buildgetChildUserTypesByItsParentUserTypeForEvent(result,attrEventIdsString){
		var str='';
		 str+='<ul class="comparisonSelect">';
		 
		 var firstChildUserTypeIdString;
		 
		 if(result !=null && result.length >0){
			  firstChildUserTypeIdString = result[0].shortName;
			 for(var i in result){
				 str+='<li attr_userTypeId="'+result[i].shortName+'" attr_event_idsString='+attrEventIdsString+' class="allItsSubUserTypeClsForEvent">'+result[i].userType+'<span class="closeIconComparison"></span></li>';
			 }
		 }
		str+='</ul>';
		$("#allItsSubUserTypeIdsByParentUserTypeDivIdForEvent").html(str);
		$(".comparisonSelect li:first-child").addClass("active")
		
		getSelectedChildTypeMembersForEvent(firstChildUserTypeIdString,attrEventIdsString);
	}
function getSelectedChildTypeMembersForEvent(firstChildUserTypeIdString,attrEventIdsString){
	 $("#childEvnetMemberDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	 $("#directChildMemberForEventDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	  var parentActivityMemberId = globalActivityMemberId;
	  var childUserTypeIdsArray = firstChildUserTypeIdString.split(",");
	   var eventIds=attrEventIdsString.split(",");
	 var jsObj ={ 
	               parentActivityMemberId : parentActivityMemberId,
				   childUserTypeIdsArray : childUserTypeIdsArray,
				   reportType :"selectedUserType",
				   stateId : globalStateId,
				   eventIds:eventIds
				 }
	  $.ajax({
			type : 'POST',
			url : 'getSelectedChildTypeMembersForEventAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		   $("#childEvnetMemberDivId").html(' ');
		   $("#directChildMemberForEventDivId").html(' ');
		  if(result != null && result.length > 0){
			  buildChildTypeMembersForEventReslt(result,attrEventIdsString);
		  }else{
			  $("#childEvnetMemberDivId").html("NO DATA AVAILABLE");
		  }
		});
 }
 function buildChildTypeMembersForEventReslt(result,attrEventIdsString){
	  var userTypeId = result[0].userTypeId;
	  var activityMemberId = result[0].activityMemberId;
	  var selectedMemberName = result[0].name;
	  var selectedUserType = result[0].userType;
	 var str='';
	  str+='<ul class="list-inline slickPanelSliderForEvent">';
	  var rank=1; 
	   for(var i in result){
	str+='<li style="cursor:pointer;" class="childEventMemberCls"  attr_event_idsString="'+attrEventIdsString+'" attr_selectedusertype="'+result[i].userType+'"  attr_id="directChildMemberForEventDivId"  attr_selectedmembername="'+result[i].name+'"  attr_activitymemberid='+result[i].activityMemberId+'  attr_usertypeid='+result[i].userTypeId+' style="width:380px !important;">';
	     if(i==0){
			str+='<div class="panel panel-default panelSlick panelActiveSlick">';
		  }else{
		  str+='<div class="panel panel-default panelSlick">';
		  }
		  str+='<div class="panel-heading">';
			 str+='<h4 class="panel-title">'+result[i].name+'</h4>';
			 str+='<span class="count">'+rank+'</span>';
		 str+='</div>';
		 str+='<div class="panel-body">';
	   if(result[i].userTypeId != null && result[i].userTypeId==7 || result[i].userTypeId==9 || result[i].userTypeId==5 || result[i].userTypeId==6){ // MLA Constituency Incharge, MP and District President Incharge 
		   var lctnName = result[i].locationName;
           lctnName = lctnName.substring(0, lctnName.lastIndexOf(" "));
		 str+='<h4 class="text-capital">'+result[i].userType+' ('+lctnName+')</h4>';	 
		 }else{
		 str+='<h4 class="text-capital">'+result[i].userType+'</h4>';	 
		 }
			
			 str+='<table class="table table-condensed">';
				 str+='<thead>';
					 str+='<th>Invitee</th>';
					 str+='<th>Invitee Attended</th>';
					 str+='<th>%</th>';
				     str+='<th>Non Invitee Attended</th>';
				     str+='<th>%</th>';
				 str+='</thead>';
				 str+='<tr>';
					 str+='<td>'+result[i].inviteeCnt+'</td>';
					 str+='<td>'+result[i].inviteeAttendedCnt+'</td>';
					 str+='<td>'+result[i].inviteeAttendedCntPer+'%</td>';
					 str+='<td>'+result[i].nonInviteeAttendedCnt+'</td>';
					 str+='<td>'+result[i].nonInviteeAttendedCntPer+'%</td>';
				 str+='</tr>';
			 str+='</table>';
		 str+='</div>';
	 str+='</div> ';
    str+=' </li> ';  
	rank=rank+1;
	   }
   $("#childEvnetMemberDivId").html(str);
   $(".slickPanelSliderForEvent").slick({
			 slide: 'li',
			 slidesToShow: 3,
			 slidesToScroll: 3,
			 infinite: false,
			  responsive: [
				{
				  breakpoint: 1024,
				  settings: {
					slidesToShow: 3,
					slidesToScroll: 3,
					infinite: false,
					dots: false
				  }
				},
				{
				  breakpoint: 800,
				  settings: {
					slidesToShow: 2,
					slidesToScroll: 2
				  }
				},
				{
				  breakpoint: 600,
				  settings: {
					slidesToShow: 1,
					slidesToScroll: 1
				  }
				},
				{
				  breakpoint: 480,
				  settings: {
					slidesToShow: 1,
					slidesToScroll: 1
				  }
				}
				// You can unslick at a given breakpoint now by adding:
				// settings: "unslick"
				// instead of a settings object
			  ]
		});   
	getEventPoorPerformanceLocation(userTypeId,activityMemberId,selectedMemberName,selectedUserType,attrEventIdsString);
	getDirectChildTypeMembersForEvent(activityMemberId,userTypeId,selectedMemberName,selectedUserType,"directChildMemberForEventDivId",attrEventIdsString);
 }
function getDirectChildTypeMembersForEvent(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId,attrEventIdsString){
	  $("#"+childActivityMemberId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
			     var eventIds=attrEventIdsString.split(",");
		         var childUserTypeIdsArray=[];
	             childUserTypeIdsArray.push(userTypeId);
	  var jsObj ={   activityMemberId : activityMemberId,
			         childUserTypeIdsArray : childUserTypeIdsArray,
					 reportType : "directChild",
					 stateId : globalStateId,
					 eventIds:eventIds
				  }
	   	$.ajax({
			type : 'POST',
			url : 'getDirectChildTypeMembersForEventAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
	     $("#"+childActivityMemberId).html('');
		  if(result != null && result.length > 0){
			  buildEventDirectChildDetailsRslt(result,selectedMemberName,selectedUserType,childActivityMemberId,userTypeId,attrEventIdsString)
		  }else{
		   $("#"+childActivityMemberId).html('NO DATA AVAILABLE');   
		  }
		});
 }
 function buildEventDirectChildDetailsRslt(result,selectedMemberName,selectedUserType,childActivityMemberId,userTypeId,attrEventIdsString){
	 var str='';
		str+='<h4><span  class="text-capital">'+selectedMemberName+'</span> - <span class="text-capitalize">'+selectedUserType+'</span></h4>';
		 if(childActivityMemberId != "directChildMemberForEventDivId"){
				str+='<span class="remveSlcUsrTypeForEvent pull-right" attr_remove_SelecUserType = "'+childActivityMemberId+'" style="margin-top: -5px;"><i class="glyphicon glyphicon-remove"></i></span>';
		 } 
		 if(childActivityMemberId != "directChildMemberForEventDivId"){
			 str+='<table  class="table table-condensed tableHoverLevelsInner m_top20">';
		 }else{
			str+='<table class="table table-condensed tableHoverLevels m_top20">';  
		 }
				str+='<thead   class="bg_D8 text-capital">';
					str+='<th>Rank</th>';
					str+='<th>Designation</th>';
					str+='<th>Name</th>';
				    str+='<th>Invitee</th>';
					 str+='<th>Invitee Attended</th>';
					 str+='<th>%</th>';
				     str+='<th>Non Invitee Attended</th>';
				     str+='<th>%</th>';
				str+'=</thead>';
		str+='<tbody>';
		var rank=1;
		 for(var i in result){
		var yourValues = result[i].locationName;
		   str+='<tr  class="subLevelEventMemberCls" attr_event_idsString="'+attrEventIdsString+'" attr_activitymemberid = "'+result[i].activityMemberId+'" attr_usertypeid = "'+result[i].userTypeId+'" attr_selectedmembername = "'+result[i].name+'" attr_selectedusertype = "'+result[i].userType+'">';
			str+='<td>';
				str+='<span class="tableCount">'+rank+'</span>';
			str+='</td>';
		  if(yourValues.indexOf(',') == -1){
				//  var locationNameArr=result[i].locationName.split(" ");
			 	var locatinName = result[i].locationName;
                 locatinName = locatinName.substring(0, locatinName.lastIndexOf(" "));
				str+='<td>'+result[i].userType+' (<b>'+locatinName+'</b>)</td>';
			}else{
				str+='<td>'+result[i].userType+'</td>';
			}
		   str+='<td>'+result[i].name+'</td>';
			str+='<td style="text-align:center;">'+result[i].inviteeCnt+'</td>';
			str+='<td style="text-align:center;">'+result[i].inviteeAttendedCnt+'</td>';
			str+='<td style="text-align:center;">'+result[i].inviteeAttendedCntPer+'</td>';
			str+='<td style="text-align:center;">'+result[i].nonInviteeAttendedCnt+'</td>';
			str+='<td style="text-align:center;">'+result[i].nonInviteeAttendedCntPer+'</td>';
		 str+='</tr>';
		str+='<tr class="showHideTr" style="display:none" attr_id = "subChildLevelEventMemDtslId'+result[i].userTypeId+''+i+'">';
		str+='<td colspan="8"  id="subChildLevelEventMemDtslId'+result[i].userTypeId+''+i+'">';
		str+='</td>';
		 rank=rank+1;
		 }
		str+='</tbody>';
		str+='</table>';
	$("#"+childActivityMemberId).html(str);
 }

function getEventPoorPerformanceLocation(userTypeId,activityMemberId,selectedUserName,userType,attrEventIdsString){
$("#topPoorLocationsEventDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
var eventIds=attrEventIdsString.split(",");
	var jsObj ={ 
				 activityMemberId : activityMemberId,
				 stateId : globalStateId,
				 eventIds:eventIds,
				 userTypeId : userTypeId
				 
			  }
	$.ajax({
		type : 'POST',
		url : 'getEventPoorPerformanceLocationAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		$("#topPoorLocationsEventDivId").html(" ");	
		if(result != null ){
		buildEventPoorPerformanceLocationRslt(result,userTypeId,selectedUserName,userType);	
		}
	});	
}
function buildEventPoorPerformanceLocationRslt(result,userTypeId,selectedUserName,userType){
    var resultListFirst;
	var resultListSecond;
    var str='';
		str+='<b><span class="color_333 pad_5 bg_CC text-capital"><span class="text-danger">poor</span> Event completed locations&nbsp&nbsp('+selectedUserName+" - "+userType+')</span></b>';
	   str+='<div class="col-md-6 col-xs-12 col-sm-6 m_top10">';
	  if(userTypeId!= null && userTypeId==3 || userTypeId==2 || userTypeId==1){
		str+='<p class="text-capital">districts</p>';  
		resultListFirst = result.districtList;
		resultListSecond = result.constituencyList;
	  }
	  if(userTypeId!= null && userTypeId==5 || userTypeId==11 || userTypeId==4 || userTypeId==6){
		 str+='<p class="text-capital">Constituencies</span></p>';  
		resultListFirst = result.constituencyList;
		resultListSecond = result.mandalTwnDivisionList;  
	  }
	   if(userTypeId!= null && userTypeId==7 || userTypeId==8 || userTypeId==9){
		 str+='<p class="text-capital">Mandal/Town/Division</span></p>';  
		resultListFirst = result.mandalTwnDivisionList;
		resultListSecond = result.villageWardList;  
	  }
	  
      str+='<table class="table tableCumulative">';
      if(resultListFirst != null && resultListFirst.length > 0){
		  var order=1;
		  var BGColor = 1;
		  for(var i in resultListFirst){
			
			str+='<tr>';
			str+='<td><span class="count" style="background-color:rgba(237, 29, 38,'+BGColor+')">'+order+'</span></td>';
			str+='<td>'+resultListFirst[i].name+'</td>';
			str+='<td>';
				str+='<div class="progress progressCustom" data-toggle="tooltip" data-placement="top" title="'+resultListFirst[i].inviteeAttendedCounPer+'%">';
			str+='<div class="progress-bar" role="progressbar" aria-valuenow="'+resultListFirst[i].inviteeAttendedCounPer+'" aria-valuemin="0" aria-valuemax="100" style="width:'+resultListFirst[i].inviteeAttendedCounPer+'%;">';
					str+='<span class="sr-only">'+resultListFirst[i].inviteeAttendedCounPer+'</span>';
				  str+='</div>';
				str+='</div>';
			str+='</td>';
			str+='<td class="text-danger">'+resultListFirst[i].inviteeAttendedCounPer+'%</td>';
			str+='</tr>';
			order=order+1;
			if(order==6)
				break;
			BGColor = BGColor - 0.2;
			}
			str+='</table>';
	  }	  
	  str+='</div>';
	  
	  str+='<div class="col-md-6 col-xs-12 col-sm-6 m_top10">';
	   if(userTypeId!= null && userTypeId==3 || userTypeId==2 || userTypeId==1){
		str+='<p class="text-capital">Constituencies</p>';  
	  }
	  if(userTypeId!= null && userTypeId==5 || userTypeId==11 || userTypeId==4 || userTypeId==6){
		 str+='<p class="text-capital">Mandal/Town/Division</p>';  
	  }
	   if(userTypeId!= null && userTypeId==7 || userTypeId==8 || userTypeId==9){
		 str+='<p class="text-capital">Village/Ward</p>';  
	  }
	  str+='<table class="table tableCumulative">';
      if(resultListSecond != null && resultListSecond.length > 0){
		  var order=1;
		  var BGColor = 1;
		  
		  for(var i in resultListSecond){
			str+='<tr>';
			str+='<td><span class="count" style="background-color:rgba(237, 29, 38,'+BGColor+')">'+order+'</span></td>';
			str+='<td>'+resultListSecond[i].name+'</td>';
			str+='<td>';
				str+='<div class="progress progressCustom" data-toggle="tooltip" data-placement="top" title="'+resultListSecond[i].inviteeAttendedCounPer+'%">';
		str+='<div class="progress-bar" role="progressbar" aria-valuenow="'+resultListSecond[i].inviteeAttendedCounPer+'" aria-valuemin="0" aria-valuemax="100" style="width:'+resultListSecond[i].inviteeAttendedCounPer+'%;">';
			str+='<span class="sr-only">'+resultListSecond[i].inviteeAttendedCounPer+'</span>';
			str+='</div>';
			str+='</div>';
			str+='</td>';
			str+='<td class="text-danger">'+resultListSecond[i].inviteeAttendedCounPer+'%</td>';
			str+='</tr>';
			order=order+1;
			if(order==6)
				break;
			BGColor = BGColor - 0.2;
			}
				str+='</table>';
			}
	     str+='</div>';
																				
	 $("#topPoorLocationsEventDivId").html(str);	
	 $('.progressCustom').tooltip()
	}
	
/* EVENT FUNCTIONALITY END*/






/*Notes Functionality*/
function displayDashboardCommentsForEvents(dashBoardComponentId){
	var jsObj={
		dashBoardComponentId:dashBoardComponentId
	}	
	$.ajax({
	 type: "POST",
	 url: "displayDashboardCommentsAction.action",
	 data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length >0){
		 var str=''; 
			 
		 str+='<ul class="notesUlEvents m_top20" style="text-transform: none;font-weight: normal;font-size: 14px;">';  	
			for(var i in result){ 
				str+='<li style="margin-top:3px;">'; 
				str+='<span class="notesTextEvents" id="editTextEventsId'+i+'"  attr_commentId="'+result[i].dashBoardCommentId+'">'+result[i].comment+' </span>- <span class="text-muted"><i>'+result[i].insertedTime+'</i></span>';
				str+='<i class="glyphicon glyphicon-trash pull-right hoverBlock deleteNotesEvents" attr_cmt_id="editTextEventsId'+i+'" id="'+result[i].dashBoardCommentId+'" onClick="deleteDashBoardcomments(this.id);"></i>';
				str+='<i class="glyphicon glyphicon-edit pull-right hoverBlock editNotesEvents" attr_cmt_id="editTextEventsId'+i+'" attr_comment="'+result[i].comment+'"></i>';
				str+='</li>';
			}
		str+='</ul>';
			
			$("#notesEventsId").html(str);	 
		}
	});
}
function deleteDashBoardcomments(dashboardCommentId)
{
	var jsObj={
		dashboardCommentId : dashboardCommentId
	}	
	$.ajax({
	 type: "POST",
	 url: "deleteDashBoardcommentsAction.action",
	 data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null){	
			if(result.message == "success"){
				
			}
		}
	});
}

function savingDashboardCommentForEvents(dashboardComponentId){  
	var comment=$(".notesAreaEvents").val();
	if(comment.trim() ==""){
		  $("#eventsUpId").html("Notes Required.");
		  return;
	  }
	var editId = $("#cmtEventsId").val();
	//$("#"+editId).parent().html(' ');
	$("#"+editId).html(comment);
	 var dashboardCommentId=0;
	 if($(".notesAreaEvents").attr("attr_commentid")>0)
	 {
		dashboardCommentId=$(".notesAreaEvents").attr("attr_commentid");		
	 }

	var jsObj={
		comment:comment,
		dashboardComponentId: dashboardComponentId,
		dashboardCommentId : dashboardCommentId
	}	
	$.ajax({
	 type: "POST",
	 url: "savingDashboardCommentAction.action",
	 data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null){	
			if(result.message == "success"){
				displayDashboardCommentsForEvents(6);
			}
		}			
	});
}
$(document).on("click",".notesIconEvents",function(){
	$(this).closest(".panel-heading").find(".notesDropDown").toggle();
});
$(document).on("click",".deleteNotesEvents",function(){
	$(this).closest("li").remove();
});
$(document).on("click",".editNotesEvents",function(){ 
	var commentId = $(this).attr("attr_cmt_id");
	var commentId1 = $(this).parent().find(".notesTextEvents").attr("attr_commentid");
	var notesHtml = $("#"+commentId).html();
	$(".notesAreaEvents").val(notesHtml);  
	$(".notesAreaEvents").attr("attr_commentid",commentId1);  
	$("#cmtId").val(commentId);
	//$("#cmtId").val();
	$("#eventsUpId").html('');		
});

$(document).on("click",".btnCustomCreateEvents",function(){
	var getNewNotes = $(".notesAreaEvents").val();
	var todayDate = moment().format("DD MMMM YYYY");
	var cmtId = $("#cmtId").val();
	var commentText = '<span class="notesText" id="'+cmtId+'" >'+getNewNotes+'</span> - <span class="text-muted"><i>'+todayDate+'</i></span> <i  class="glyphicon glyphicon-trash pull-right hoverBlock deleteNotesEvents"></i><i class="glyphicon glyphicon-edit pull-right hoverBlock editNotes" attr_cmt_id="'+cmtId+'"></i>'; 
	if(cmtId>0)
	$(".notesUlEvents").append("<li>"+commentText+"</li>");
	$(".notesAreaEvents").val('');	
});

/*Notes Functionality End*/