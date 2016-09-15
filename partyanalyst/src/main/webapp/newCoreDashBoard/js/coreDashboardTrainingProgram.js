//Training Program
var globalStateId=1; //default Ap 
 $('#dateRangeIdForTrainingCamp').on('apply.daterangepicker', function(ev, picker) {
		getTrainingCampBasicDetailsCntOverview();
		getUserTypeWiseTotalEligibleAndAttendedCnt();
	});
 function initialiseDatePickerForTrainingProgram(){
		$("#dateRangeIdForTrainingCamp").daterangepicker({
			singleDatePicker: true,
			minDate:'01/01/2014',
			maxDate:moment(),
			locale: {
			  format: 'DD/MM/YYYY'
			},
			opens:'left'
		})
}
var getDocumentWidth = $(document).width();

	function getTrainingCampBasicDetailsCntOverview()
	{
	 $("#programsDtlsCntTableId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	 $("#villageWardTblId").html(' ');
	 $("#mdlTwnDvsnTabId").html(' ');
	 var dateStr = $("#dateRangeIdForTrainingCamp").val();
		var jsObj ={ 
		             userAccessLevelId : globalUserAccessLevelId,
					 userAccessLevelValuesArray : globalUserAccessLevelValues,
					 stateId : globalStateId,
					 dateStr : dateStr
				  }
		$.ajax({
			type : 'POST',
			url : 'getTrainingCampBasicDetailsCntOverviewAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		  $("#programsDtlsCntTableId").html(' ');
		 if(result != null){
			 buildTrainingProgramBasicDetails(result);
		 }else{
			$("#programsDtlsCntTableId").html("NO DATA AVAILABLE");
			$("#villageWardTblId").html("NO DATA AVAILABLE");
			$("#mdlTwnDvsnTabId").html("NO DATA AVAILABLE");
		 }
		});
	}
  function buildTrainingProgramBasicDetails(result){
	  var str='';
	  var programList = result.trainingProgramList;
	if(programList != null && programList.length > 0){
		  for(var i in programList){
	       str+='<div class="col-md-12 col-xs-12 col-sm-12">';
			 str+='<h4 class="text-capital " attr_program_id='+programList[i].id+'><span class="bg_49 pad_custom">'+programList[i].name+'</span>';
			 str+='<span class="programSkillsCls" style="background-color:#fff;margin-left:5px;color:#555;font-size:14px;cursor:pointer;" data-toggle="tooltip" data-placement="top" title="Click here to expand">';
				str+='<i class="glyphicon glyphicon-fullscreen"></i>';
			str+='</span></h4>';  
			str+='<table class="table tableTraining">';
				str+='<tr>';
					str+='<td>';
						str+='<h4>'+programList[i].totalEligibleCount+'</h4>';
						str+='<p class="text-muted text-capital">Total eligible</p>';
					str+='</td>';
					str+='<td>';
						str+='<h4>'+programList[i].totalAttenedCount+'&nbsp;<span class="font-10 text-success">'+programList[i].totalAttenedCountPer+' %</span></h4>';
						str+='<p class="text-muted text-capital">attended</p>';
					str+='</td>';
					str+='<td>';
						str+='<h4>'+programList[i].totalNotAttenedCount+'&nbsp;<span class="font-10 text-danger"> '+programList[i].totalNotAttenedCountPer+'%</span></h4>';
						str+='<p class="text-muted text-capital">yet to train</p>';
					str+='</td>';
				str+='</tr>';
			str+='</table>';
			str+='<hr class="m_0"/>';
		 str+='</div>';	
	  }
	}else{
		str+='NO DATA AVAILABLE';
	}
	 $("#programsDtlsCntTableId").html(str);
	 
	var villageWardRslt = result.villageWardVO;
 	var str1='';
	str1='<h4 class="text-capitalize m_top10">village / ward</h4>';
	if(villageWardRslt != null){
	if(villageWardRslt.totalEligibleCount !=0){
	 str1+='<table class="table tableTraining bg_ED">';
		str1+='<tr>';
			str1+='<td>';
				str1+='<p class="text-muted text-capitalize">total eligible</p>';
				str1+='<h4>'+villageWardRslt.totalEligibleCount+'</h4>';
			str1+='</td>';
			str1+='<td>';
				str1+='<p class="text-muted text-capitalize">attended</p>';
				str1+='<h4>'+villageWardRslt.totalAttenedCount+'&nbsp;<span class="font-10 text-success"> '+villageWardRslt.totalAttenedCountPer+'%</span></h4>'
			str1+='</td>';
			str1+='<td>';
				str1+='<p class="text-muted text-capitalize">yet to train</p>';
				str1+='<h4>'+villageWardRslt.totalNotAttenedCount+'&nbsp;<span class="font-10 text-danger"> '+villageWardRslt.totalNotAttenedCountPer+'%</span></h4>';
			str1+='</td>';
		str1+='</tr>';
	str1+='</table>';		
	}else{
	str1+='NO DATA AVAILABLE';	
	}	
	}else{
	str1+='NO DATA AVAILABLE';		
	}
	$("#villageWardTblId").html(str1); 
	
	var mandalTwnDivRslt = result.mandalTownDivisionVO; 
    var str2='';
	 str2+='<h4 class="text-capitalize m_top10">mandal / town / division</h4>';
	if(mandalTwnDivRslt != null){
	if(+mandalTwnDivRslt.totalEligibleCount !=0){
	  str2+='<table class="table tableTraining bg_ED">';
		str2+='<tr>';
			str2+='<td>';
				str2+='<p class="text-muted text-capitalize">total eligible</p>';
				str2+='<h4>'+mandalTwnDivRslt.totalEligibleCount+'</h4>';
			str2+='</td>';
			str2+='<td>';
				str2+='<p class="text-muted text-capitalize">attended</p>';
				str2+='<h4>'+mandalTwnDivRslt.totalAttenedCount+' <span class="font-10 text-success">'+mandalTwnDivRslt.totalAttenedCountPer+'%</span></h4>'
			str2+='</td>';
			str2+='<td>';
				str2+='<p class="text-muted text-capitalize">yet to train</p>';
				str2+='<h4>'+mandalTwnDivRslt.totalNotAttenedCount+' <span class="font-10 text-danger">'+mandalTwnDivRslt.totalNotAttenedCountPer+'%</span></h4>';
			str2+='</td>';
		str2+='</tr>';
	 str2+='</table>';		
	}else{
	 str2+='NO DATA AVAILABLE';		
	}
	}else{
	 str2+='NO DATA AVAILABLE';	
	}
    $("#mdlTwnDvsnTabId").html(str2);  
	/* var str3='';
	var districtRslt = result.districtVO;
	str3+='<h4 class="text-capitalize m_top20">District</h4>';
	if(districtRslt != null){
	  str3+='<table class="table tableTraining bg_ED">';
		str3+='<tr>';
			str3+='<td>';
				str3+='<p class="text-muted text-capitalize">total eligible</p>';
				str3+='<h4>'+districtRslt.totalEligibleCount+'</h4>';
			str3+='</td>';
			str3+='<td>';
				str3+='<p class="text-muted text-capitalize">attended</p>';
				str3+='<h4>'+districtRslt.totalAttenedCount+'<span class="font-10 text-success">'+districtRslt.totalAttenedCountPer+'%</span></h4>'
			str3+='</td>';
			str3+='<td>';
				str3+='<p class="text-muted text-capitalize">yet to train</p>';
				str3+='<h4>'+districtRslt.totalNotAttenedCount+'<span class="font-10 text-success">'+districtRslt.totalNotAttenedCountPer+'%</span></h4>';
			str3+='</td>';
		str3+='</tr>';
	 str3+='</table>';	
	}else{
	 str3+='NO DATA AVAILABLE';	
	}
	$("#districtTblId").html(str3);  
	var str4='';
	var stateRslt = result.stateVO;
	str4+='<h4 class="text-capitalize m_top20">State</h4>';
	if(stateRslt != null){
	  str4+='<table class="table tableTraining bg_ED">';
		str4+='<tr>';
			str4+='<td>';
				str4+='<p class="text-muted text-capitalize">total eligible</p>';
				str4+='<h4>'+stateRslt.totalEligibleCount+'</h4>';
			str4+='</td>';
			str4+='<td>';
				str4+='<p class="text-muted text-capitalize">attended</p>';
				str4+='<h4>'+stateRslt.totalAttenedCount+'<span class="font-10 text-success">'+stateRslt.totalAttenedCountPer+'%</span></h4>'
			str4+='</td>';
			str4+='<td>';
				str4+='<p class="text-muted text-capitalize">yet to train</p>';
				str3+='<h4>'+stateRslt.totalNotAttenedCount+'<span class="font-10 text-success">'+stateRslt.totalNotAttenedCountPer+'%</span></h4>';
			str4+='</td>';
		str4+='</tr>';
	 str4+='</table>';	
	}else{
	 str4+='NO DATA AVAILABLE';	
	}
	$("#stateTblDivId").html(str4);   */
  }
 function getTrainingCampProgramsDetailsCntByUserType(){
		$("#districtWiseProgramCntDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		 var dateStr = $("#dateRangeIdForTrainingCamp").val();
		var jsObj ={ 
		             userAccessLevelId : globalUserAccessLevelId,
					 userAccessLevelValuesArray : globalUserAccessLevelValues,
					 stateId : globalStateId,
					 dateStr : dateStr,
					 userTypeId : globalUserTypeId
				  }
		  
		$.ajax({
			type : 'POST',
			url : 'getTrainingCampProgramsDetailsCntByUserTypeAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#districtWiseProgramCntDivId").html(" ");
			if(result != null && result.length > 0){
			  buildLocationWiseTrainingProgramDetails(result);	
			}else{
			$("#districtWiseProgramCntDivId").html("NO DATA AVAILABLE");	
			}
		});
	}

function buildLocationWiseTrainingProgramDetails(result){
		$("#districtWiseProgramCntDivId").html('');
		if(result != null && result.length > 0){
			var str='';
			for(var i in result){
				str+=result[i].name
				str+='<div id="locationDivId'+i+'" class="chartLiD" style="height:300px" ></div>';
			}
		}
		$("#districtWiseProgramCntDivId").html(str);
	if(result != null && result.length > 0){
		for(var i in result){
			var distIdArray = [];
			var districtNamesArray =[];
			var districtWiseAttendedPercArray = [];
			var districtWiseYetToTrainPercArray = [];
			if(result[i].locationList !=null && result[i].locationList.length > 0){
				for(var j in result[i].locationList){
						districtNamesArray.push(result[i].locationList[j].name);
						distIdArray.push(result[i].locationList[j].id);
						districtWiseAttendedPercArray.push(result[i].locationList[j].totalAttenedCountPer);
						districtWiseYetToTrainPercArray.push(result[i].locationList[j].totalNotAttenedCountPer);
					}
			}
						$(function () {
							$('#locationDivId'+i+'').highcharts({
								colors: ['#F56800','#53BF8B'],
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
									headerFormat: '<b>{point.x}</b><br/>',
									pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y:.0f}%</b><br/>',
									shared: true
								},
								plotOptions: {
									column: {
										stacking: 'percent',
										dataLabels: {
											enabled: true,
											formatter: function(){
												return Highcharts.numberFormat(this.y,0) + '%';
											}
										  
										}
									}
								},
								series: [ {
									name: 'Yet to Train',
									data: districtWiseYetToTrainPercArray
								},{
									name: 'Attended',
									data: districtWiseAttendedPercArray
								}]
							});
						});
						$.each($('#locationDivId'+i+'').find(".highcharts-xaxis-labels").find("tspan"),function(index,item){ 
							$(this).attr("style","cursor:pointer;");
							$(this).attr("class","distDtlsCls");    
							//$(this).attr("state_Program_Id","6");
							$(this).attr("attr_dist_id",distIdArray[index]); 
							$(this).attr("attr_position_id","leadership");
						});   
		}
	}else{
		$("#districtWiseProgramCntDivId").html("No Data Available");
	}	
}
var globalUserWiseMemberRslt;
 function getUserTypeWiseTotalEligibleAndAttendedCnt(){
		$(".hideCls").show();    
	 	$("#clickInfoId").hide();
	$("#userTypeWiseTrainingProgramTopFiveStrongAndPoorMemsDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	   var dateStr = $("#dateRangeIdForTrainingCamp").val();
	 var jsObj ={
			          userAccessLevelId:globalUserAccessLevelId,
					  userAccessLevelValuesArray:globalUserAccessLevelValues,
					  activityMemberId : globalActivityMemberId,
					  userTypeId : globalUserTypeId,
					  stateId : globalStateId,
					  dateStr : dateStr
				}
		
		$.ajax({
			type : 'POST',
			url : 'getUserTypeWiseTotalEligibleAndAttendedCntAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		     $("#userTypeWiseTrainingProgramTopFiveStrongAndPoorMemsDivId").html(' ');
			 buildgetUserTypeWiseTrainingProgramAttendedCountTopFiveStrongResults(result);
			 globalUserWiseMemberRslt = result;
		});
 }
 
	function buildgetUserTypeWiseTrainingProgramAttendedCountTopFiveStrongResults(result){
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
			  str+='<div id="genSecTraining'+i+'" style="width:100%;height:100px;"></div>';
			str+='</div>'
		  }
		}
		$("#clickInfoId").hide();
		$("#userTypeWiseTrainingProgramTopFiveStrongAndPoorMemsDivId").html(str);
	if(result != null && result.length > 0){  
			for(var i in result){
				var candidateNameArray = [];
				var trainingProgramCountArray = [];
				var countVar =0;
			  if(result[i] !=null && result[i].length>0){
					for(var j in result[i]){
						countVar =countVar+1;
						candidateNameArray.push(result[i][j].name);
						trainingProgramCountArray.push(result[i][j].totalAttenedCountPer);
						if (countVar === 5) {
							break;
						}
					}
				}
		//if(result[i][j].totalAttenedCountPer!=0){
				var getWidth = $("#genSecTraining"+i).parent().width()+'px';
				$("#genSecTraining"+i).width(getWidth);
		     $(function () {
			$('#genSecTraining'+i).highcharts({
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
						pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b><br/>',
						shared: true,
						valueSuffix: '%'
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
					name: 'Attended',
					data: trainingProgramCountArray
				}]
			});
		});
		
		/* }else{
		$("#genSecTraining"+i).html("No Data Available");
		$("#genSecTraining"+i).css("height","35px");
		} */
	}
	}else{
    $("#userTypeWiseTrainingProgramTopFiveStrongAndPoorMemsDivId").html('NO DATA AVAILABLE.');
	}
	}
	function buildgetUserTypeWiseTrainingProgramAttendedCountTopFivePoorResults(result){
		$("#clickInfoId").hide(); 
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
				str+='<div id="genSecTraining'+i+'" style="width:100%;height:100px;"></div>';
				str+='</div>'
			}
		}
		$("#clickInfoId").hide();
		$("#userTypeWiseTrainingProgramTopFiveStrongAndPoorMemsDivId").html(str);
	if(result != null && result.length > 0){
		for(var i in result){
				var candidateNameArray = [];
				var trainingProgramCountArray = [];
				var countVar = 0;
				var length = result[i].length - 1;
				for(var j = length; j >= 0; j--){
					candidateNameArray.push(result[i][j].name);
					trainingProgramCountArray.push(result[i][j].totalAttenedCountPer);
					countVar =countVar+1;
					if (countVar === 5) {
						break;
					}
				}
			//if( result[i][j].totalAttenedCountPer!=0){
			var getWidth = $("#genSecTraining"+i).parent().width()+'px';
				$("#genSecTraining"+i).width(getWidth);
				$(function () {
			   $('#genSecTraining'+i).highcharts({
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
					name: 'Attended',
					data: trainingProgramCountArray
				}]
			});
		});
		/* }else{
		$("#genSecTraining"+i).html("No Data Available");
		$("#genSecTraining"+i).css("height","35px");	
		} */
		}
	}else{
	$("#clickInfoId").hide();   
	 $("#userTypeWiseTrainingProgramTopFiveStrongAndPoorMemsDivId").html('NO DATA AVAILABLE.');
	}
	}
function stateLevelCampDetails(){ 
	$("#stateLevelCampId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	var programIdArr = [6]; 
	var dateStr = $("#dateRangeIdForTrainingCamp").val();
	var jsObj={
		programIdArr : programIdArr,
		stateId : globalStateId,
		dateStr : dateStr
	}
	$.ajax({
		type : 'GET',
		url : 'getStateLevelCampAttendedDetails.action',     
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)}
	}).done(function(result){    
		buildStateLevelCampAttendedDetails(result);
	});
}  
function buildStateLevelCampAttendedDetails(result){ 
	var programIdsStr = [];
	for(var i in result){
		programIdsStr.push(result[i].id);     
	}      
	var str = '';
	str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
		str+='<h4 class="text-capital"><span class="headingColor">state level training program</span><span style="background-color:#fff;margin-left:5px;" attr_program_id="'+programIdsStr+'" class="stateLevelTraining" attr_location="State Level Training Program"><i class="glyphicon glyphicon-fullscreen" ></i></span></h4>';
		for(var i in result){    
		str+='<h5 class="text-capital m_top10">'+result[i].name+'<span style="background-color:#fff;margin-left:5px;border:0px;padding:2px;" class="stateLevelTrainingInd" attr_program_id="'+result[i].id+'" attr_location="'+result[i].name+'"><i class="glyphicon glyphicon-fullscreen"></i></span></h5>';  
		str+='<table class="table tableTraining bg_ED">';     
			str+='<tbody>';
				str+='<tr>';
					str+='<td>';
						str+='<p class="text-muted text-capitalize">eligible</p>';
						str+='<p class="responsiveFont">'+result[i].count+'</p>';
					str+='</td>';
					str+='<td>';
						str+='<p class="text-muted text-capitalize">invited</p>';
						str+='<p class="responsiveFont">'+result[i].count+'</p>';
			str+='</td>';
			str+='<td>';
				var per = (result[i].actualCount*(100/result[i].count)).toFixed(2);
				
				str+='<p class="text-muted text-capitalize">attended</p>';
				str+='<p class="responsiveFont">'+result[i].actualCount+'<span class="font-10 text-success">'+per+'%</span></hp>'; 
							str+='</td>';  
							str+='<td>';
								var abs = result[i].count - result[i].actualCount; 
								str+='<p class="text-muted text-capitalize">absent</p>    ';
								str+='<p class="responsiveFont">'+abs+'<span class="font-10 text-danger">'+(100-per).toFixed(2)+'%</span></p>';     
								
					str+='</td>';
				str+='</tr>';
			str+='</tbody>';
		str+='</table>';  
		}
	str+='</div>';  
	$("#stateLevelCampId").html(str);        
}
$(document).on("click",".liCls",function(){
	var memberType=$(this).attr("attr_value");
	 if(memberType != null && memberType == "strong"){
		buildgetUserTypeWiseTrainingProgramAttendedCountTopFiveStrongResults(globalUserWiseMemberRslt); 
	 }else if(memberType == "poor"){
	  buildgetUserTypeWiseTrainingProgramAttendedCountTopFivePoorResults(globalUserWiseMemberRslt);
	 }
});

/* Training Funcitons Start*/
$(document).on("click",".stateLevelTrainingInd",function(){
	$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
	if( !$(this).find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".trainingsHiddenBlock,.trainingDetailedBlock").hide();
		$(".trainingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".trainingsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
	}else{
		$(".trainingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".trainingsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
		$(".trainingsHiddenBlock,.moreTrainingCampBlocksIcon").show();
		var programId = [];
		programId.push($(this).attr("attr_program_id"));
		getStateLevelCampCount(programId);         
		stateLevelCampMembersDistWise(); 
		stateLevelCampDetailsRepresentativeWise()
		var val = $(this).attr("attr_location");
		$("#clickInfoId").html(val); 
		$("#switchButtonId").removeClass("moreTrainingBlocksIcon");
		$("#switchButtonId").addClass("moreTrainingCampBlocksIcon");   
		$("#detailedId").removeClass("trainingDetailed");
		$("#detailedId").addClass("trainingCampDetailed");
		$("#clickInfoId").show();
		$(".trainingComparison").hide();
		$(".trainingCampDetailed").attr("attr_program_id",$(this).attr("attr_program_id"));  
	}
	
});
$(document).on("click",".trainingIconExpand,.programSkillsCls",function(){
	
	$(".dateRangePickerClsForTraining").toggleClass("hide");	
	//$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
	$(".programSkillsCls,.trainingIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
	
	$(".trainingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	$(".trainingsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
    if( !$(this).find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".moreTrainingBlocks").hide();
	}else{
		getUserTypeWiseTotalEligibleAndAttendedCnt();
	}
	if( !$(".iconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".moreBlocks").hide();
		$(".moreBlocks1").hide();
		$(".moreBlocksDetailAndComp").hide();
	}
	if( $(".iconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".iconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".committeesHiddenBlock,.moreBlocks,.moreBlocks1,.moreBlocksDetailAndComp,.moreBlocksIcon").hide();
		$(".committeesBlock,.basicCommitteesBlock,.userTypeCommitteesBlock,.committeesBlock1").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".dateRangePickerCls").toggleClass("hide");
		$(".moreBlocksIcon").removeClass("unExpandBlock");
	}else if( $(".debatesIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".debatesIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".debatesMoreHiddenBlock,.debatesHiddenBlock,.dateRangePickerClsForDebates").hide();
		$(".moreDebatesBlocksIcon").removeClass("unExpandDebatesBlock");
		$(".debatesBlock").toggleClass("col-md-6").toggleClass("col-md-12");
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
	
	 setTimeout(function(){
		$(".trainingsHiddenBlock,.moreTrainingBlocksIcon").toggle();
	},800); 
});
$(document).on("click",".stateLevelTraining",function(){
	$(this).find("i").toggleClass("glyphicon-resize-small").toggleClass("glyphicon-fullscreen");
	$(".trainingIconExpand").find("i").toggleClass("glyphicon-resize-small").toggleClass("glyphicon-fullscreen");
	
	if( !$(this).find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".trainingsHiddenBlock,.moreTrainingCampBlocksIcon,.trainingDetailedBlock ").hide();
		$(".trainingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".trainingsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
	}else{
		
		if( !$(".trainingsBlockExpand").hasClass("col-md-6"))
		{
			$(".trainingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".trainingsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
		}
		if($(".programSkillsCls").find("i").hasClass("glyphicon-resize-small"))
		{
			$(".programSkillsCls").find("i").toggleClass("glyphicon-resize-small").toggleClass("glyphicon-fullscreen");
		}
		$(".trainingsHiddenBlock,.moreTrainingCampBlocksIcon").show();
		var idStr = $(this).attr("attr_program_id");
		var programIdArr = [];
		var arr = idStr.split(","); 
		for(var i in arr){
			programIdArr.push(arr[i]);
		} 
		getStateLevelCampCount(programIdArr);         
		stateLevelCampMembersDistWise();       
		stateLevelCampDetailsRepresentativeWise() 
		var val = $(this).attr("attr_location");
		$("#clickInfoId").html(val);
		$("#switchButtonId").removeClass("moreTrainingBlocksIcon");
		$("#switchButtonId").addClass("moreTrainingCampBlocksIcon");
		
		$("#detailedId").removeClass("trainingDetailed");
		$("#detailedId").addClass("trainingCampDetailed");
		$("#clickInfoId").show();
		$(".trainingComparison").hide();
		$(".trainingCampDetailed").attr("attr_program_id",$(this).attr("attr_program_id")); 
	}
	
	
	
});
$(document).on("click",".moreTrainingBlocksIcon",function(){
	$(this).addClass("unExpandTrainingBlock");
	$(".moreTrainingBlocks").toggle();
	setTimeout(function(){
		getTrainingCampProgramsDetailsCntByUserType();
		//getTrainingProgramPoorCompletedLocationDtls();
	},600);
	var moreBlocksWidth = $(".trainingsUl").width();
	var getEachLiWidth;
	if(getDocumentWidth > 1024)
	{
		getEachLiWidth = moreBlocksWidth / 3 +'px';
		$(".trainingsUl li").width(getEachLiWidth);
	}else if(getDocumentWidth < 1024 && getDocumentWidth > 600)
	{
		getEachLiWidth = moreBlocksWidth / 4 +'px';
		$(".trainingsUl li").width(getEachLiWidth);
	}
	$(".trainingDetailed").trigger("click");
});
$(document).on("click",".trainingDetailed",function(){
	$(this).addClass("active")
	$(".trainingComparison").removeClass("active");
	$(".trainingDetailedBlock").show();
	$(".trainingComparisonBlock").hide();
	//buildTrainingProgramRslt(globalTrainingProgramsRslt);
	getTrainingProgramBasicCnt();
});
$(document).on("click",".trainingComparison",function(){
	$(this).addClass("active")
	$(".trainingDetailed").removeClass("active");
	$(".trainingDetailedBlock").hide();
	$(".trainingComparisonBlock").show();
	getAllItsSubUserTypeIdsByParentUserTypeIdForTrainingProgram();
});

$(document).on("click",".unExpandTrainingBlock",function(){
		$(this).removeClass("unExpandTrainingBlock");
		$(".moreTrainingBlocks").hide();
	});
  function getAllItsSubUserTypeIdsByParentUserTypeIdForTrainingProgram(){
		 $("#childUserTypeDetailsDivForTrainingProgram").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var jsObj = {parentUserTypeId : globalUserTypeId}
		$.ajax({
			type : 'POST',
			url : 'getAllItsSubUserTypeIdsByParentUserTypeIdAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#childUserTypeDetailsDivForTrainingProgram").html(" ");	
			if(result != null && result.length > 0){
			buildgetChildUserTypesByItsParentUserTypeForTrainingProgram(result);	
			}else{
			$("#childUserTypeDetailsDivForTrainingProgram").html("NO DATA AVAILABLE");	
			}
		});		 
	}
	
function buildgetChildUserTypesByItsParentUserTypeForTrainingProgram(result){
		var str='';
		 str+='<ul class="comparisonSelect">';
		 
		 var firstChildUserTypeIdString;
		 
		 if(result !=null && result.length >0){
			  firstChildUserTypeIdString = result[0].shortName;
			 for(var i in result){
				 str+='<li attr_userTypeId="'+result[i].shortName+'" class="childUserTypeClsForTrainingProgram">'+result[i].userType+'<span class="closeIconComparison"></span></li>';
			 }
		 }
		str+='</ul>';
		$("#childUserTypeDetailsDivForTrainingProgram").html(str);
		$(".comparisonSelect li:first-child").addClass("active")
		
		getSelectedChildTypeMembersForTrainingProgram(firstChildUserTypeIdString);
		//getTrainingProgramPoorCompletedLocationDtls();
	}
	function getSelectedChildTypeMembersForTrainingProgram(firstChildUserTypeIdString){
	 $("#childActivityMemberDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	 $("#userTypeWiseChildDtlsTabId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	  var parentActivityMemberId = globalActivityMemberId;
	  var childUserTypeIdsArray = firstChildUserTypeIdString.split(",");
	  var dateStr = $("#dateRangeIdForTrainingCamp").val();
	  var jsObj ={ 
	               parentActivityMemberId : parentActivityMemberId,
				   childUserTypeIdsArray : childUserTypeIdsArray,
				   userAccessLevelId : globalUserAccessLevelId,
				   userAccessLevelValuesArray : globalUserAccessLevelValues,
				   reportType :"selectedUserType",
				   stateId : globalStateId,
				   dateStr : dateStr
				 }
	  $.ajax({
			type : 'POST',
			url : 'getSelectedChildTypeMembersForTrainingProgramAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		   $("#childActivityMemberDivId").html(' ');
		   $("#userTypeWiseChildDtlsTabId").html(' ');
		  if(result != null && result.length > 0){
			  buildChildTypeMembersForTrainingReslt(result);
		  }else{
			  $("#childActivityMemberDivId").html("NO DATA AVAILABLE");
		  }
		});
 }
 function buildChildTypeMembersForTrainingReslt(result){
	  var userTypeId = result[0].userTypeId;
	  var activityMemberId = result[0].activityMemberId;
	  var selectedMemberName = result[0].name;
	  var selectedUserType = result[0].userType;
	 var str='';
	  str+='<ul class="list-inline slickPanelSliderTraining">';
	  var rank=1; 
	   for(var i in result){
	str+='<li style="cursor:pointer;" class="activityMemberCls"  attr_selectedusertype="'+result[i].userType+'"  attr_id="userTypeWiseChildDtlsTabId"  attr_selectedmembername="'+result[i].name+'"  attr_activitymemberid='+result[i].activityMemberId+'  attr_usertypeid='+result[i].userTypeId+' style="width:380px !important;">';
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
		 if(result[i].userTypeId != null && result[i].userTypeId==7 || result[i].userTypeId==9){ // MLA and Constituency Incharge 
		   var lctnName = result[i].locationName;
           lctnName = lctnName.substring(0, lctnName.lastIndexOf(" "));
		 str+='<h4 class="text-capital">'+result[i].userType+' - '+lctnName+'</h4>';	 
		 }else{
		 str+='<h4 class="text-capital">'+result[i].userType+'</h4>';	 
		 }
			
			 str+='<table class="table table-condensed">';
				 str+='<thead>';
					 str+='<th>Eligible</th>';
					 str+='<th colspan="2">Attended</th>';
				    str+='<th colspan="2">Yet to train</th>';
				 str+='</thead>';
				 str+='<tr>';
					 str+='<td>'+result[i].totalEligibleCount+'</td>';
					 str+='<td>'+result[i].totalAttenedCount+'</td>';
					 str+='<td>'+result[i].totalAttenedCountPer+'%</td>';
					 str+='<td>'+result[i].totalNotAttenedCount+'</td>';
					 str+='<td>'+result[i].totalNotAttenedCountPer+'%</td>';
				 str+='</tr>';
			 str+='</table>';
		 str+='</div>';
	 str+='</div> ';
    str+=' </li> ';  
	rank=rank+1;
	   }
   $("#childActivityMemberDivId").html(str);
	$(".slickPanelSliderTraining").slick({
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
	getTrainingProgramPoorCompletedLocationDtls(userTypeId,activityMemberId,selectedMemberName,selectedUserType);
	getDirectChildActivityTrainingProgramMemberDetails(activityMemberId,userTypeId,selectedMemberName,selectedUserType,"userTypeWiseChildDtlsTabId");
 }
 $(document).on("click",".childUserTypeClsForTrainingProgram",function(){
	var childUserTypeId = $(this).attr("attr_userTypeId");
	getSelectedChildTypeMembersForTrainingProgram(childUserTypeId);
	//getTrainingProgramPoorCompletedLocationDtls();
});
$(document).on("click",".activityMemberCls",function(){
	    
		$(".slickPanelSliderTraining").find("li").find(".panelSlick").removeClass("panelActiveSlick");
		$(this).find(".panelSlick").addClass("panelActiveSlick");
	    var activityMemberId = $(this).attr("attr_activitymemberid");  
		var userTypeId = $(this).attr("attr_usertypeid"); 
    	var selectedMemberName = $(this).attr("attr_selectedmembername");  
		var selectedUserType = $(this).attr("attr_selectedusertype"); 	
		var childActivityMemberId = $(this).attr("attr_id");  
		getDirectChildActivityTrainingProgramMemberDetails(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId);
		getTrainingProgramPoorCompletedLocationDtls(userTypeId,activityMemberId,selectedMemberName,selectedUserType);
});
$(document).on("click",".remveSlcUsrType",function(){
		 var removeSelected = $(this).attr("attr_removeSelecUserType"); 
		 $("#"+removeSelected).remove();
	});
$(document).on("click",".lowLevelActivityMemberClsForTrainingProgram",function(){
	    $(this).closest('tr').next('tr.showHideTr').show(); 
		var activityMemberId = $(this).attr("attr_activitymemberid");  
		var userTypeId = $(this).attr("attr_usertypeid"); 
		var selectedMemberName = $(this).attr("attr_selectedmembername");  
		var selectedUserType = $(this).attr("attr_selectedusertype");  
		var childActivityMemberId = $(this).closest('tr').next('tr.showHideTr').attr("attr_id");  
		getDirectChildActivityTrainingProgramMemberDetails(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId);
		getTrainingProgramPoorCompletedLocationDtls(userTypeId,activityMemberId,selectedMemberName,selectedUserType);
});
  function getDirectChildActivityTrainingProgramMemberDetails(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId){
	  $("#"+childActivityMemberId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	  var dateStr = $("#dateRangeIdForTrainingCamp").val();
	  var childUserTypeIdsArray=[];
	             childUserTypeIdsArray.push(userTypeId);
	  var jsObj ={  activityMemberId : activityMemberId,
			         childUserTypeIdsArray : childUserTypeIdsArray,
					 reportType : "directChild",
					 stateId : globalStateId,
					 dateStr : dateStr
				  }
	   	$.ajax({
			type : 'POST',
			url : 'getDirectChildActivityTrainingProgramMemberDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		  $("#"+childActivityMemberId).html('');
		  if(result != null && result.length > 0){
			  buildChildActivityMembersDetails(result,selectedMemberName,selectedUserType,childActivityMemberId,userTypeId);
		  }else{
			  $("#"+childActivityMemberId).html('NO DATA AVAILABLE');  
		  }
		});
	}
	function buildChildActivityMembersDetails(result,selectedMemberName,selectedUserType,childActivityMemberId,userTypeId){
		var str='';
		str+='<h4><span  class="text-capital">'+selectedMemberName+'</span> - <span class="text-capitalize">'+selectedUserType+'</span></h4>';
		 if(childActivityMemberId != "userTypeWiseChildDtlsTabId"){
				str+='<span class="remveSlcUsrType pull-right" attr_removeSelecUserType = "'+childActivityMemberId+'" style="margin-top: -5px;"><i class="glyphicon glyphicon-remove"></i></span>';
		 } 
		 if(childActivityMemberId != "userTypeWiseChildDtlsTabId"){
			 str+='<table  class="table table-condensed tableHoverLevelsInner m_top20">';
		 }else{
			str+='<table class="table table-condensed tableHoverLevels m_top20">';  
		 }
				str+='<thead   class="bg_D8 text-capital">';
					str+='<th>Rank</th>';
					str+='<th>Designation</th>';
					str+='<th>Name</th>';
					str+='<th style="text-align:center;">Total Eligible</th>';
					str+='<th style="text-align:center;">Attended</th>';
					str+='<th style="text-align:center;">%</th>';
					str+='<th style="text-align:center;">Not Attended</th>';
					str+='<th style="text-align:center;">%</th>';
				str+'=</thead>';
		str+='<tbody>';
		var rank=1;
		 for(var i in result){
		var yourValues = result[i].locationName;
		   str+='<tr  class="lowLevelActivityMemberClsForTrainingProgram"  attr_activitymemberid = "'+result[i].activityMemberId+'" attr_usertypeid = "'+result[i].userTypeId+'" attr_selectedmembername = "'+result[i].name+'" attr_selectedusertype = "'+result[i].userType+'">';
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
			str+='<td style="text-align:center;">'+result[i].totalEligibleCount+'</td>';
			str+='<td style="text-align:center;">'+result[i].totalAttenedCount+'</td>';
			str+='<td style="text-align:center;">'+result[i].totalAttenedCountPer+'</td>';
			str+='<td style="text-align:center;">'+result[i].totalNotAttenedCount+'</td>';
			str+='<td style="text-align:center;">'+result[i].totalNotAttenedCountPer+'</td>';
		 str+='</tr>';
		str+='<tr class="showHideTr" style="display:none" attr_id = "subChildLevelMemDtslId'+result[i].userTypeId+''+i+'">';
		str+='<td colspan="8"  id="subChildLevelMemDtslId'+result[i].userTypeId+''+i+'">';
		str+='</td>';
		 rank=rank+1;
		 }
		str+='</tbody>';
		str+='</table>';
	$("#"+childActivityMemberId).html(str);
	}
/* more training blocks start*/
function getTrainingProgramBasicCnt(){
	$("#programsDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	 var dateStr = $("#dateRangeIdForTrainingCamp").val();
 	  var jsObj ={ 
	               userAccessLevelId : globalUserAccessLevelId,
				   userAccessLevelValuesArray : globalUserAccessLevelValues,
				    stateId : globalStateId,
					dateStr : dateStr
				 }
	  $.ajax({
			type : 'POST',
			url : 'getTrainingProgramBasicCntAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#programsDivId").html(' ');
		  if(result != null && result.length > 0){
			  buildTrainingProgramRslt(result);
		  }else{
			  $("#programsDivId").html("NO DATA AVAILABLE");
		  }
		});	
}
function buildTrainingProgramRslt(result){
	var str='';
		str+='<ul class="trainingsUl">';
		  for(var i in result){
			  str+='<li>';
			  str+='<h4 class="text-capitalize text-muted">'+result[i].name+'</h4>';
			  str+='<div id="programHighChartId'+i+'" class="chartLi trainingGraphWidth"></div>';
			  str+='</li>';
		  }
		str+='</ul>';
	$("#programsDivId").html(str);
	 $(".trainingsUl").slick({ //santosh
			 slide: 'li',
			 slidesToShow: 4,
			 slidesToScroll: 4,
			 infinite: false,
			  responsive: [
				{
				  breakpoint: 1024,
				  settings: {
					slidesToShow: 4,
					slidesToScroll: 4,
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
	if(result != null && result.length > 0){
		var  jsonDataArrAttended=[];
		var  jsonDataArrYettotrain=[];
		  for(var i in result){
			
			//jsonDataArr.push({name:"Total Eligible",data:[result[i].totalEligibleCountPer,result[i].totalEligibleCountPer]});
			//jsonDataArrAttended.push({name:"Attended",data:[result[i].totalAttenedCountPer,0]});
			jsonDataArrAttended.push(result[i].totalAttenedCountPer);
			jsonDataArrYettotrain.push(result[i].totalNotAttenedCountPer); 
			
		var chartWidth = $("#programHighChartId"+i).parent().width()/2;
		$("#programHighChartId"+i).width(chartWidth);
		$(function () {
		  $('#programHighChartId'+i).highcharts({
			colors: ['#F56800','#53BF8B','#66728C'],   
			chart: {
			  type: 'column',
			  
			},
			title: {
			  text: null,
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
			  labels: {
				enabled: false,
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
			  pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}%</b><br/>',
			  shared: true
			},
			legend: {
			  enabled: true,
			  align: 'left'
		  
			},
			plotOptions: {
			  column: {
				stacking: 'percent',
				dataLabels:{
				  enabled: true,
				  formatter: function () {
					if (this.y > 0){ return this.y + '%';}
					else {return '';}
				  }
				}
			  }
			},
			series: [ {   
				name: 'Yet to Train',
				data: jsonDataArrYettotrain
				},{
				name: 'Attended',
				data: jsonDataArrAttended
				}]  
		  });
		});  
	   }
	}
}
//swadhin
	 function getTrainingProgramPoorCompletedLocationDtls(userTypeId,activityMemberId,selectedUserName,userType){
	 $("#poorPerformancTrainingPrograLocationsDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	  var dateStr = $("#dateRangeIdForTrainingCamp").val();
		var jsObj ={ 
		              userTypeId : userTypeId,
					  activityMemberId:activityMemberId,
					  stateId : globalStateId,
					  dateStr : dateStr
				  }
		
		$.ajax({
			type : 'POST',
			url : 'getTrainingProgramPoorCompletedLocationDtlsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		   $("#poorPerformancTrainingPrograLocationsDivId").html('');
	       if(result != null){
			   buildTrainingProgramPoorPerformanceLocationRslt(result,userTypeId,selectedUserName,userType);
		   }
		});
	}
	function buildTrainingProgramPoorPerformanceLocationRslt(result,userTypeId,selectedUserName,userType){
    var resultListFirst;
	var resultListSecond;
    var str='';
		str+='<b><span class="color_333 pad_5 bg_CC text-capital"><span class="text-danger">poor</span> training completed locations&nbsp&nbsp('+selectedUserName+" - "+userType+')</span></b>';
	   str+='<div class="col-md-6 col-xs-12 col-sm-6 m_top10">';
	  if(userTypeId!= null && userTypeId==3 || userTypeId==2){
		str+='<p class="text-capital">districts</p>';  
		resultListFirst = result.districtList;
		resultListSecond = result.constituencyList;
	  }
	  if(userTypeId!= null && userTypeId==5 || userTypeId==11 || userTypeId==4 || userTypeId==6){
		 str+='<p class="text-capital">Constituencies</p>';  
		resultListFirst = result.constituencyList;
		resultListSecond = result.mandalList;  
	  }
	   if(userTypeId!= null && userTypeId==7 || userTypeId==8 || userTypeId==9){
		 str+='<p class="text-capital">Mandal/Town/Division</p>';  
		resultListFirst = result.mandalList;
		resultListSecond = result.villageList;  
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
				str+='<div class="progress progressCustom" data-toggle="tooltip" data-placement="top" title="'+resultListFirst[i].totalAttenedCountPer+'%">';
			str+='<div class="progress-bar" role="progressbar" aria-valuenow="'+resultListFirst[i].totalAttenedCountPer+'" aria-valuemin="0" aria-valuemax="100" style="width:'+resultListFirst[i].totalAttenedCountPer+'%;">';
					str+='<span class="sr-only">'+resultListFirst[i].totalAttenedCountPer+'</span>';
				  str+='</div>';
				str+='</div>';
			str+='</td>';
			str+='<td class="text-danger">'+resultListFirst[i].totalAttenedCountPer+'%</td>';
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
	   if(userTypeId!= null && userTypeId==3 || userTypeId==2){
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
				str+='<div class="progress progressCustom" data-toggle="tooltip" data-placement="top" title="'+resultListSecond[i].totalAttenedCountPer+'%">';
		str+='<div class="progress-bar" role="progressbar" aria-valuenow="'+resultListSecond[i].totalAttenedCountPer+'" aria-valuemin="0" aria-valuemax="100" style="width:'+resultListSecond[i].totalAttenedCountPer+'%;">';
			str+='<span class="sr-only">'+resultListSecond[i].totalAttenedCountPer+'</span>';
			str+='</div>';
			str+='</div>';
			str+='</td>';
			str+='<td class="text-danger">'+resultListSecond[i].totalAttenedCountPer+'%</td>';
			str+='</tr>';
			order=order+1;
			if(order==6)
				break;
			BGColor = BGColor - 0.2;
			}
				str+='</table>';
			}
	     str+='</div>';
																				
	 $("#poorPerformancTrainingPrograLocationsDivId").html(str);	
	 $('.progressCustom').tooltip()
	}
/* Training Funcitons End*/

/*Notes Functionality*/
function displayDashboardCommentsForTraining(dashBoardComponentId){
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
      		 
	     str+='<ul class="notesUlTraining m_top20" style="text-transform: none;font-weight: normal;font-size: 14px;">';  	
            	     
					for(var i in result){ 
                        str+='<li style="margin-top:3px;">'; 
                        str+='<span class="notesTextTraining" id="editTextTrngId'+i+'"  attr_commentId="'+result[i].dashBoardCommentId+'">'+result[i].comment+' </span>- <span class="text-muted"><i>'+result[i].insertedTime+'</i></span>';
					    str+='<i class="glyphicon glyphicon-trash pull-right hoverBlock deleteNotesTraining" attr_cmt_id="editTextTrngId'+i+'" id="'+result[i].dashBoardCommentId+'" onClick="deleteDashBoardcomments(this.id);"></i>';
                        str+='<i class="glyphicon glyphicon-edit pull-right hoverBlock editNotesTraining" attr_cmt_id="editTextTrngId'+i+'" attr_comment="'+result[i].comment+'"></i>';
                        str+='</li>';
					}
                        str+='</ul>';
						/*str+='<hr/>';
						str+='<div id="id2" style="color:red;"></div>';
                        str+='<label>Create Notes</label>';
                        str+='<textarea class="form-control notesAreaTraining"></textarea>';
                        str+='<button class="btn btn-default btnCustomCreateTraining btn-sm " id=buttonId" onClick="savingDashboardCommentForTraing(4);">create</button>';*/
			
			$("#notesTrainingId").html(str);	 
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

function savingDashboardCommentForTraing(dashboardComponentId){  
  var comment=$(".notesAreaTraining").val();
  if(comment.trim() ==""){
		  $("#id2").html("Notes Required.");
		  return;
	  }
	var editId = $("#cmtTrngId").val();
	//$("#"+editId).parent().html(' ');
	$("#"+editId).html(comment);
	 var dashboardCommentId=0;
	 if($(".notesAreaTraining").attr("attr_commentid")>0)
	 {
		dashboardCommentId=$(".notesAreaTraining").attr("attr_commentid");		
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
				
				//$("#id2").html('update succuss');
				displayDashboardCommentsForTraining(4);
			}
		}			
	});
}
$(document).on("click",".notesIconTraining",function(){
	$(this).closest(".panel-heading").find(".notesDropDown").toggle();
});
$(document).on("click",".btnCustomCreate",function(){
	var getNewNotes = $(".notesArea").val();
	var todayDate = moment().format("DD MMMM YYYY");
	var cmtId = $("#cmtId").val();
	var commentText = '<span class="notesText" id="'+cmtId+'" >'+getNewNotes+'</span> - <span class="text-muted"><i>'+todayDate+'</i></span> <i  class="glyphicon glyphicon-trash pull-right hoverBlock deleteNotes"></i><i class="glyphicon glyphicon-edit pull-right hoverBlock editNotes" attr_cmt_id="'+cmtId+'"></i>'; 
	if(cmtId>0)
	$(".notesUl").append("<li>"+commentText+"</li>");
	$(".notesArea").val('');	
});
$(document).on("click",".deleteNotesTraining",function(){
	$(this).closest("li").remove();
});
$(document).on("click",".editNotesTraining",function(){ 
	var commentId = $(this).attr("attr_cmt_id");
	var commentId1 = $(this).parent().find(".notesTextTraining").attr("attr_commentid");
	var notesHtml = $("#"+commentId).html();
	$(".notesAreaTraining").val(notesHtml);  
	$(".notesAreaTraining").attr("attr_commentid",commentId1);  
	$("#cmtId").val(commentId);
	//$("#cmtId").val();
	$("#id2").html('');		
});

$(document).on("click",".btnCustomCreateTraining",function(){
	var getNewNotes = $(".notesAreaTraining").val();
	var todayDate = moment().format("DD MMMM YYYY");
	var cmtId = $("#cmtId").val();
	var commentText = '<span class="notesText" id="'+cmtId+'" >'+getNewNotes+'</span> - <span class="text-muted"><i>'+todayDate+'</i></span> <i  class="glyphicon glyphicon-trash pull-right hoverBlock deleteNotesTraining"></i><i class="glyphicon glyphicon-edit pull-right hoverBlock editNotes" attr_cmt_id="'+cmtId+'"></i>'; 
	if(cmtId>0)
	$(".notesUlTraining").append("<li>"+commentText+"</li>");
	$(".notesAreaTraining").val('');
});
//training camp start
$(document).on("click",".moreTrainingCampBlocksIcon",function(){
	$(this).addClass("unExpandTrainingBlock");
	$(".moreTrainingBlocks").toggle();
	setTimeout(function(){
		stateLevelCampMembersDistWise();
		//getTrainingProgramPoorCompletedLocationDtls();
	},600);
	var moreBlocksWidth = $(".trainingsUl").width();
	var getEachLiWidth;
	if(getDocumentWidth > 1024)
	{
		getEachLiWidth = moreBlocksWidth / 3 +'px';
		$(".trainingsUl li").width(getEachLiWidth);
	}else if(getDocumentWidth < 1024 && getDocumentWidth > 600)
	{
		getEachLiWidth = moreBlocksWidth / 4 +'px';
		$(".trainingsUl li").width(getEachLiWidth);
	}
	$(".trainingCampDetailed").trigger("click");
});
$(document).on("click",".trainingCampDetailed",function(){
	$(this).addClass("active")
	$(".trainingComparison").removeClass("active");
	$(".trainingDetailedBlock").show();
	$(".trainingComparisonBlock").hide();
	//getTrainingProgramBasicCnt();
	var idStr = $(this).attr("attr_program_id");
	var programIdArr = [];
	var arr = idStr.split(","); 
	for(var i in arr){
		programIdArr.push(arr[i]);
	}       
	getStateLevelCampCount(programIdArr);
});
function getStateLevelCampCount(programIdArr){
	$("#programsDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	//var programIdArr = [6]; 
	var dateStr = $("#dateRangeIdForTrainingCamp").val();
	var jsObj={  
		programIdArr : programIdArr,
		stateId : globalStateId,
		dateStr : dateStr
	} 
	$.ajax({
		type : 'GET',
		url : 'getStateLevelCampAttendedDetails.action',     
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)}
	}).done(function(result){ 
		$("#programsDivId").html(' ');
		if(result != null){    
			buildStateLevelCampDetails(result);
		}else{
			$("#programsDivId").html("NO DATA AVAILABLE");
		}
	});
}
function buildStateLevelCampDetails(result){ 
	var str='';
	str+='<ul class="trainingsUl">';
	for(var i in result){
		str+='<li>';
		str+='<h4 class="text-capitalize text-muted">'+result[i].name+'</h4>';    
		str+='<div id="programHighChartId'+i+'" class="chartLi trainingGraphWidth"></div>';
		str+='</li>';
	}
	str+='</ul>';
	$("#programsDivId").html(str); 
	$(".trainingsUl").slick({ 
			slide: 'li',
			slidesToShow: 4,
			slidesToScroll: 4,
			infinite: false,
			responsive: [
				{
				  breakpoint: 1024,
					settings: {
						slidesToShow: 4,
						slidesToScroll: 4,
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
				
			]
	}); 
	if(result != null){
		for(var i in result){  
			var  jsonDataArrAttended=[]; 
			var  jsonDataArrYettotrain=[];
			var precent = (result[i].actualCount*(100/result[i].count)).toFixed(2);
			jsonDataArrAttended.push(parseFloat(precent));          
			var abs = 100-precent;
			jsonDataArrYettotrain.push(abs);      
			var chartWidth = $("#programHighChartId"+i).parent().width()/2;
			$("#programHighChartId"+i).width(chartWidth);  
			$(function () {
				$('#programHighChartId'+i).highcharts({  
				colors: ['#F56800','#53BF8B','#66728C'],         
				chart: {
					type: 'column',    
				},
				title: {
					text: null,
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
					labels: {
						enabled: false,
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
					pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}%</b><br/>',
					shared: true
				},
				legend: {
					enabled: true,
					align: 'left'
				},
				plotOptions: {
					column: {
						stacking: 'percent',
						dataLabels:{
							enabled: true,
							formatter: function () {
								if (this.y > 0){ return this.y + '%';}
								else {return '';}
							}
						}
					}
				},
				series: [ { 
							name: 'Yet to Train',
							data: jsonDataArrYettotrain
						},{
							name: 'Attended',
							data: jsonDataArrAttended
						}]
				});
			});  
		}
	}
	
}
function stateLevelCampMembersDistWise(){
	$("#districtWiseProgramCntDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		$.ajax({
			type : 'GET',
			url : 'stateLevelCampDetailsDistWise.action',  
			dataType : 'json',
			data : {}
		}).done(function(result){ 
			$("#districtWiseProgramCntDivId").html(" ");
			if(result != null && result.length > 0){
			  buildStateLevelCampDetailsDistWise(result);
			}else{
			$("#districtWiseProgramCntDivId").html("NO DATA AVAILABLE");	
			}
			
		});
}
function buildStateLevelCampDetailsDistWise(result){
	$("#districtWiseProgramCntDivId").html('');
		if(result != null && result.length > 0){
			var str='';
			//for(var i in result){
				//str+=result[i].name
				str+='<div id="trainingLocationDivId" class="chartLiD" style="height:300px" ></div>';
			//}
		}
		$("#districtWiseProgramCntDivId").html(str);
		if(result != null && result.length > 0){
		//for(var i in result){ 
			var districtIdArr=[];
			var districtNamesArray =[];
			var districtWiseAttendedPercArray = [];
			var districtWiseYetToTrainPercArray = [];
			//if(result[i].districtList !=null && result[i].districtList.length > 0){
				//debugger; 
				for(var j in result){
					districtNamesArray.push(result[j].name);
					districtIdArr.push(result[j].districtid);
					var precent = (result[j].actualCount*(100/result[j].count)).toFixed(2);
					districtWiseAttendedPercArray.push(parseFloat(precent));  
					var abs = 100-precent;
					districtWiseYetToTrainPercArray.push(parseFloat(abs.toFixed(2)));
				}  
			//}
						$(function () {
							$('#trainingLocationDivId').highcharts({  
								colors: ['#F56800','#53BF8B'],
								chart: {
									type: 'column'  
								},
								title: {
									text: 'Official Spokesperson'
								},
								xAxis: {
									 min: 0,
										 gridLineWidth: 0,
										 minorGridLineWidth: 0,
										categories: districtNamesArray,
										
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
									headerFormat: '<b>{point.x}</b><br/>',
									pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.percentage:.0f}%</b><br/>',
									shared: true
								},
								plotOptions: {
									column: {
										stacking: 'percent',
										dataLabels: {
											enabled: true,
											formatter: function(){
												return Highcharts.numberFormat(this.y,0) + '%';
											}
										  
										}
									}
								},
								series: [ {
									name: 'Yet to Train',
									data: districtWiseYetToTrainPercArray
								},{
									name: 'Attended',
									data: districtWiseAttendedPercArray
								}]
							});
						});
		//}districtIdArr
		$.each($("#districtWiseProgramCntDivId").find(".highcharts-xaxis-labels").find("tspan"),function(index,item){ 
			$(this).attr("style","cursor:pointer;");    
			//$(this).addClass("distDtlsCls");
			$(this).attr("class","distDtlsCls");    
			$(this).attr("state_Program_Id","6");
			$(this).attr("attr_dist_id",districtIdArr[index]); 
			$(this).attr("attr_position_id","camp");	  		
		});    
	}else{
		$("#districtWiseProgramCntDivId").html("No Data Available");
	}	
}
$(document).on("click",".distDtlsCls",function(){ 
	var distId = 0;
	var programId = 0;
	var stateId = 0;
	var dateStr = '';
	$("#myModelId").modal('show');
	$("#positionId").html('');  
	$("#memberId").html(''); 
	$("#processingImgId").show();	  
	$("#processingImgId").html('<div><center><img style="height:20px" src="images/icons/loading.gif"></center></div>');
	var position = $(this).attr("attr_position_id");
	if(position=="camp"){
		distId = $(this).attr("attr_dist_id");  
		programId = $(this).attr("state_Program_Id");
		stateId = globalStateId;
		dateStr = $("#dateRangeIdForTrainingCamp").val();
		getCampMemberDtlsPerDist(distId,programId,stateId,dateStr);
	}else{
		distId = $(this).attr("attr_dist_id");
		getLeaderShipMemDtlsPerDist(distId);
	}
	
 	
});
function getCampMemberDtlsPerDist(distId,programId,stateId,dateStr){
	var jsObj ={ 
	    distId : distId,
		programId : programId,  
		stateId : stateId,
		dateStr : dateStr  
	}
	$.ajax({
		type : 'POST',
		url : 'getCandidateDtlsPerDist.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#processingImgId").hide();    
			if(result != null && result.length > 0){
				buildMemberRslt(result,"camp");
				
			}else{
				$("#memberId").html('No Data Available');   
			}
		});	
}
function getLeaderShipMemDtlsPerDist(distId){
	
	var dateStr = $("#dateRangeIdForTrainingCamp").val();
	var jsObj ={ 
		userAccessLevelId : globalUserAccessLevelId,
		userAccessLevelValuesArray : globalUserAccessLevelValues,
		stateId : globalStateId,
		distId : distId,
		dateStr : dateStr   
	}
	$.ajax({
		type : 'POST',
		url : 'getLeaderShipCandidateDtlsPerDist.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#processingImgId").hide();    
			if(result != null && result.length > 0){
				buildMemberRslt(result,"leadership"); 
				
			}else{  
				$("#memberId").html('No Data Available');   
			}
		});	
}
function buildMemberRslt(result,status){ 
	var str2 = '';
	var totalMember = result.length;  
	var attendedMember = 0;
	var absent = 0;
	
	var str = '';
	str+='<table class="table table-condensed" id="campMemberDtlsId">';
	str+='<thead>';
		str+='<th>NAME</th>';
		str+='<th>DESIGNATION</th>';
		str+='<th>CONTACT NUMBER</th>'; 
		str+='<th>STATUS</th>';
	str+='</thead>';
	str+='<tbody>';
	for(var i in result){
		if(result[i].wish=="attended"){
			attendedMember+=1;
		}
		str+='<tr>'; 
			str+='<td>'+result[i].name.toUpperCase()+'</td>';
			if(result[i].status==""){ 
				str+='<td>-</td>';
			}else{    
				str+='<td>'+result[i].status.toUpperCase()+'</td>';   
			}  
			str+='<td>'+result[i].mobileNo+'</td>'; 
			if(status=="camp"){
				str+='<td>'+result[i].wish.toUpperCase()+'</td>';
			}else{
				if(result[i].wish=="absent"){
					str+='<td>YET TO TRAIN</td>';      
				}else{
					str+='<td>'+result[i].wish.toUpperCase()+'</td>';
				}
			}		
		str+='</tr>';   
	}
	absent = totalMember - attendedMember;
	str2+='<span class="label label-primary">All-'+totalMember+'</span>'; 
	str2+='<span class="label label-default">Attended-'+attendedMember+'</span>'; 
	if(status=="camp"){
		str2+='<span class="label label-warning">Absent-'+absent+'</span>';  
	}else{
		str2+='<span class="label label-warning">Yet to train-'+absent+'</span>';    
	}
	 
	str+='</tbody>';
	$("#positionId").html(str2);
	$("#memberId").html(str); 
	$("#campMemberDtlsId").dataTable();    
	
}
