      var globalStateId=1; 
	function getPartyMeetingBasicCountDetails()
	{ 
	  $("#meetingBasicCountDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var jsObj ={ 
		             activityMemberId : globalActivityMemberId,
					 stateId : globalStateId,
					 fromDate : "9/06/2015",
					 toDate : "9/03/2016"
					 
				  }
		$.ajax({
			type : 'POST',
			url : 'getPartyMeetingBasicCountDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#meetingBasicCountDivId").html(' ');
			buildMeetingBasicCountDetails(result);
		});
	}
	function buildMeetingBasicCountDetails(result){
		var overAllResult= result.overAllVO;
		var levelWiseResult = result.partyMettingsVOList;
		var str='';
		  str+'<div class="row">';
		  if(overAllResult != null){
			 str+='<div class="col-md-12 col-xs-12 col-sm-10 col-sm-offset-1 col-md-offset-0 m_top10">';
		      str+='<table class="table tableTraining">';
			  str+='<tbody><tr>';
				  str+='<td>';
					  str+='<h3>'+overAllResult.totalCount+'</h3>';
					  str+='<p class="text-muted text-capital">total</p>';
				  str+='</td>';
				  str+='<td>';
					  str+='<h3>'+overAllResult.conductedCount+'<span class="font-10 text-success">'+overAllResult.conductedCountPer+'%</span></h3>';
					  str+='<p class="text-muted text-capital">yes</p>';
				  str+='</td>';
				  str+='<td>';
					  str+='<h3>'+overAllResult.notConductedCount+'<span class="font-10 text-success">'+overAllResult.notConductedCountPer+'%</span></h3>';
					  str+='<p class="text-muted text-capital">no</p>';
				  str+='</td>';
				  str+='<td>';
					  str+='<h3>'+overAllResult.mayBeCount+'<span class="font-10 text-success">'+overAllResult.mayBeCountPer+'%</span></h3>';
					  str+='<p class="text-muted text-capital">maybe</p>';
				  str+='</td>';
			  str+='</tr>';
	       str+='</tbody></table>';
		  str+='<hr class="m_0">';
		  str+='</div>';  
		  }
		 if(levelWiseResult != null && levelWiseResult.length > 0){
			  for(var i in levelWiseResult){
				 str+='<div class="col-md-12 col-xs-12 col-sm-10 col-sm-offset-1 col-md-offset-0 m_top10">';
				 str+='<h4 class="text-capitalize m_top10">'+levelWiseResult[i].name+'</h4>';
				 str+='<table class="table tableTraining bg_ED">';
					 str+='<tbody><tr>';
						 str+='<td>';
							 str+='<p class="text-muted text-capitalize">total</p>';
							 str+='<h4>'+levelWiseResult[i].totalCount+'</h4>';
						 str+='</td>';
						 str+='<td>';
							 str+='<p class="text-muted text-capitalize">yes</p>';
							 str+='<h4>'+levelWiseResult[i].conductedCount+'<span class="font-10 text-success">'+levelWiseResult[i].conductedCountPer+'%</span></h4>';
						 str+='</td>';
						 str+='<td>';
							 str+='<p class="text-muted text-capitalize">no</p>';
							 str+='<h4>'+levelWiseResult[i].notConductedCount+' <span class="font-10 text-success">'+levelWiseResult[i].notConductedCountPer+'%</span></h4>';
						 str+='</td>';
						 str+='<td>';
							 str+='<p class="text-muted text-capitalize">maybe</p>';
							 str+='<h4>'+levelWiseResult[i].mayBeCount+' <span class="font-10 text-success">'+levelWiseResult[i].mayBeCountPer+'%</span></h4>';
						 str+='</td>';
					 str+='</tr>';
				 str+='</tbody></table>';
				 str+='<hr class="m_0">';
			 str+='</div>';  
			  }
		  }else{
		  str+='No Data Available';	  
		  }
		str+='</div>';
	  $("#meetingBasicCountDivId").html(str);
	}
	var globalUserWiseMeetingMemberRslt;
	function getUserTypeWiseMeetingCounductedNotCounductedMayBeDetailsCnt(){
   $("#userTypeWiseTopFiveStrongAndPoorMeetingMemsDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		  var jsObj ={ 
		             activityMemberId : globalActivityMemberId,
					 userTypeId : globalUserTypeId,
					 stateId : globalStateId,
					 fromDate : "9/06/2015",
					 toDate : "9/03/2016"
				  }
		$.ajax({
			type : 'POST',
			url : 'getUserTypeWiseMeetingCounductedNotCounductedMayBeDetailsCntAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		    $("#userTypeWiseTopFiveStrongAndPoorMeetingMemsDivId").html(' ');
			 buildgUserTypeWiseConductedAndMayBeTopFiveStrongPerResults(result);
			 globalUserWiseMeetingMemberRslt = result;
		});
		
	} 
	function buildgUserTypeWiseConductedAndMayBeTopFiveStrongPerResults(result){
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
			  str+='<div id="genSecMeeting'+i+'" style="width:100%;height:100px;"></div>';
			str+='</div>'
		  }
		}
		$("#userTypeWiseTopFiveStrongAndPoorMeetingMemsDivId").html(str);
	   if(result != null && result.length > 0){
			for(var i in result){
				var candidateNameArray = [];
				var meetingCounductedAndMayBePerArray = [];
				var countVar =0;
			  if(result[i] !=null && result[i].length>0){
					for(var j in result[i]){
						countVar =countVar+1;
						candidateNameArray.push(result[i][j].name);
						meetingCounductedAndMayBePerArray.push(result[i][j].conductedAndMayBeMeetingPer);
						if (countVar === 5) {
							break;
						}
					}
				}
		//if(result[i][j].conductedAndMayBeMeetingPer!=0){
				var getWidth = $("#genSecMeeting"+i).parent().width()+'px';
				$("#genSecMeeting"+i).width(getWidth);
		     $(function () {
			$('#genSecMeeting'+i).highcharts({
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
					headerFormat: '<b>{point.x}</b><br/>',
					pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.percentage:.1f}%</b><br/>',
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
									return Highcharts.numberFormat(this.y,1);
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
					name: 'Counducted And MayBe ',
					data: meetingCounductedAndMayBePerArray
				}]
			});
		});
		
		/* }else{
		$("#genSecMeeting"+i).html("No Data Available");
		$("#genSecMeeting"+i).css("height","35px");
		} */
	}
	}else{
    $("#userTypeWiseTopFiveStrongAndPoorMeetingMemsDivId").html('NO DATA AVAILABLE.');
	}
	}
	function buildgUserTypeWiseConductedAndMayBeTopFivePoorPerResults(result){
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
				str+='<div id="genSecMeeting'+i+'" style="width:100%;height:100px;"></div>';
				str+='</div>'
			}
		}
		$("#userTypeWiseTopFiveStrongAndPoorMeetingMemsDivId").html(str);
	if(result != null && result.length > 0){
		for(var i in result){
				var candidateNameArray = [];
				var meetingCounductedAndMayBePerArray = [];
				var countVar = 0;
				var length = result[i].length - 1;
				for(var j = length; j >= 0; j--){
					candidateNameArray.push(result[i][j].name);
					meetingCounductedAndMayBePerArray.push(result[i][j].conductedAndMayBeMeetingPer);
					countVar =countVar+1;
					if (countVar === 5) {
						break;
					}
				}
			//if( result[i][j].conductedAndMayBeMeetingPer!=0){
			var getWidth = $("#genSecMeeting"+i).parent().width()+'px';
				$("#genSecMeeting"+i).width(getWidth);
				$(function () {
			   $('#genSecMeeting'+i).highcharts({
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
					headerFormat: '<b>{point.x}</b><br/>',
					pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.percentage:.1f}%</b><br/>',
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
									return Highcharts.numberFormat(this.y,1);
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
					name: 'Attended',
					data: meetingCounductedAndMayBePerArray
				}]
			});
		});
		/* }else{
		$("#genSecTraining"+i).html("No Data Available");
		$("#genSecTraining"+i).css("height","35px");	
		} */
		}
	}else{
	 $("#userTypeWiseTopFiveStrongAndPoorMeetingMemsDivId").html('NO DATA AVAILABLE.');
	}
	}
	
$(document).on("click",".meetingLiCls",function(){
	var memberType=$(this).attr("attr_value");
	 if(memberType != null && memberType == "strong"){
	getUserTypeWiseMeetingCounductedNotCounductedMayBeDetailsCnt(globalUserWiseMeetingMemberRslt); 
	 }else if(memberType == "poor"){
	  buildgUserTypeWiseConductedAndMayBeTopFivePoorPerResults(globalUserWiseMeetingMemberRslt);
	 }
});
	$(document).on("click",".meetingsIconExpand",function(){
		//$(".dateRangePickerClsForTraining").toggleClass("hide");	
		getUserTypeWiseMeetingCounductedNotCounductedMayBeDetailsCnt();
	$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".meetingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".meetingsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
		$(".meetingsHiddenBlock,.moreMeetingsBlocksIcon").toggle();
	});
	$(document).on("click",".moreMeetingsBlocksIcon",function(){
		getMeetingLevelDetails();
		$(".moreMeetingsBlocks").toggle();
	});


function getMeetingLevelDetails(){
   $("#meetingLevelHIghChartsDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var jsObj ={ 
		             activityMemberId : globalActivityMemberId,
					 stateId : globalStateId,
					 fromDate : "9/06/2015",
					 toDate : "9/03/2016"
					 
				  }
		$.ajax({
			type : 'POST',
			url : 'getPartyMeetingBasicCountDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		   $("#meetingLevelHIghChartsDivId").html(' ');
			buildLevelWiseHighCharts(result);
		});	
}
function buildLevelWiseHighCharts(result){
	var levelWiseResult = result.partyMettingsVOList;
		$("#meetingLevelHIghChartsDivId").html('');
		if(levelWiseResult != null && levelWiseResult.length > 0){
			var str='';
			var locationLevelNameArray =[];
		//	str+='<h4 class="text-capitalize">meetings attendance</h4>';
			  var length = levelWiseResult.length - 1;
			  str+='<ul class="villageWardUl">';
				for(var i = length; i >= 0; i--){
				str+='<li   style="height:300px" >';
					str+='<h4>'+levelWiseResult[i].name+'</h4>';
				 str+='<div id="meetingsLevel'+i+'" class="chartLi"></div></li>';
				}
			  str+='</ul>';
		}
		$("#meetingLevelHIghChartsDivId").html(str);
		$(".villageWardUl").slick({
			 slide: 'li',
			 slidesToShow: 4,
			 slidesToScroll: 2,
			 infinite: false,
		});
	if(levelWiseResult != null && levelWiseResult.length > 0){
	  var length = levelWiseResult.length - 1;
		  for(var i = length; i >= 0; i--){
			var meetingLevelArr =[];
			var conductedMeetingArr = [];
			var notCounductedArr = [];
			var mayBeMeetingArr = [];
			
				    meetingLevelArr.push(" ");
					conductedMeetingArr.push(levelWiseResult[i].conductedCountPer);
					notCounductedArr.push(levelWiseResult[i].notConductedCountPer);
					mayBeMeetingArr.push(levelWiseResult[i].mayBeCountPer);
				           
						    $(function () {
							$('#meetingsLevel'+i+'').highcharts({
								colors: ['#53BF8B','#F56800','#66728C'],
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
										categories: meetingLevelArr,
									labels: {
											rotation: -45,
											style: {
												fontSize: '13px',
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
								headerFormat: '<b>{point.x}</b>',
								pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y:.1f}%</b><br/>',
								shared: true
							},
								plotOptions: {
									column: {
										dataLabels: {
											enabled: true,
											 formatter: function() {
												if (this.y === 0) {
													return null;
												} else {
													return Highcharts.numberFormat(this.y,1);
												}
											}
										  
										}
									}
								},
								series: [{
									name: 'Yes',
									data: conductedMeetingArr
								}, {
									name: 'No',
									data: notCounductedArr
								}, {
									name: 'Maybe',
									data: mayBeMeetingArr
								}]
							});
						});
		}
	}else{
		$("#meetingLevelHIghChartsDivId").html("No Data Available")
	}
}	
