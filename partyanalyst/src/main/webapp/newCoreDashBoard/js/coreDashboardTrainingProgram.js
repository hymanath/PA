//Training Program
var globalStateId=1; //default Ap 
var customStartDate1 = moment().format('DD/MM/YYYY');
//$(".trainingDate").html(" UPTO DATE ( "+customStartDate1+" )");
 $('#dateRangeIdForTrainingCamp').on('apply.daterangepicker', function(ev, picker) {
	customStartDate = picker.startDate.format('DD/MM/YYYY');
	//$(".trainingDate").html("( "+customStartDate+" )");
	stateLevelCampDetails();
	getTrainingCampBasicDetailsCntOverview();
	getUserTypeWiseTotalEligibleAndAttendedCnt();
});
$("#tdpTriningYearId").on('change', function() {
	stateLevelCampDetails();
	getTrainingCampBasicDetailsCntOverview();
	getUserTypeWiseTotalEligibleAndAttendedCnt();
	var enrollmentYearId=$("#tdpTriningYearId").val();
	if(enrollmentYearId == 4){
		$("#campWiseTrainingId").show();
		getTrainingCampBasicDetailsCntOverviewTrainingCampCenterWise();
	}else{
		  $("#campWiseTrainingId").hide(); 
	}
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
	  $("#specialProgramLeaderId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	 $("#villageWardTblId").html(' ');
	 $("#mdlTwnDvsnTabId").html(' ');
	 
	 var programIdArr = [];//[6,7]
	var enrollmentYrIds = [];
	 enrollmentYrIds.push($("#tdpTriningYearId").val());
	 
	 if(enrollmentYrIds != null && enrollmentYrIds.length>0){
		 for(var i in enrollmentYrIds){
			 if(parseInt(enrollmentYrIds[i]) == 4){
				 programIdArr=[];
				 programIdArr=[8];
			 }
			 else  if(parseInt(enrollmentYrIds[i]) == 3){
				 programIdArr=[];
				 programIdArr=[1,6,7];
			 }
		 }
	 }
	 
	 
	 var dateStr = $("#dateRangeIdForTrainingCamp").val();
	  var enrollmentYrIds = [];
         enrollmentYrIds.push($("#tdpTriningYearId").val());
		var jsObj ={ 
					 programIdArr : programIdArr,
		             userAccessLevelId : globalUserAccessLevelId,
					 userAccessLevelValuesArray : globalUserAccessLevelValues,
					 stateId : globalStateId,
					 dateStr : "01/01/2000",
					 enrollmentYearIdsList :enrollmentYrIds
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
			 buildSpecialProgramLeaderIdWiseDetails(result);

			 
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
	  var lastUPdatedTime;
	if(programList != null && programList.length > 0){
		  for(var i in programList){
			 lastUPdatedTime= programList[0].lastUpdatedTime;
	       str+='<div class="col-md-12 col-xs-12 col-sm-12">';
		   str+='<div> <b>NOTE :</b> Same Cadre is attending for multiple batches. So, we are considering them individually in batch wise report and those cadres are unique in Camp wise/State wise report.</div><br>';
			 str+='<h4 class="text-capital " attr_program_id='+programList[i].id+'><span class="bg_49 pad_custom">'+programList[i].name+'</span>';
			 str+='<span class="programSkillsCls"  state-level="false" expand-icon-inner="training" style="background-color:#fff;margin-left:5px;color:#555;font-size:14px;cursor:pointer;" data-toggle="tooltip" data-placement="top" title="Click here to expand">';
				str+='<i class="glyphicon glyphicon-fullscreen"></i>';
			str+='</span></h4>';  
			str+='<table class="table tableTraining bg_ED">';
				str+='<tr>';
					str+='<td>';
						str+='<h4>'+programList[i].totalEligibleCount+'</h4>';
						str+='<p class="text-muted text-capital">Total eligible</p>';
					str+='</td>';
					str+='<td>';
						str+='<h4>'+programList[i].totalAttenedCount+'&nbsp;</h4>';
						str+='<p class="text-muted text-capital">Total Attended </p>';
					str+='</td>';
					str+='<td>';
						str+='<h4>'+programList[i].inviteeAttended+'&nbsp;<span class="font-10 text-danger"> '+programList[i].totalAttenedCountPer+'%</span></h4>';
						str+='<p class="text-muted text-capital">Invitee Attended </p>';
					str+='</td>';
					str+='<td>';
						str+='<h4>'+programList[i].nonInviteeAttended+'&nbsp;</h4>';
						str+='<p class="text-muted text-capital">Non Invitee Attended </p>';
					str+='</td>';
					str+='<td>';
						str+='<h4>'+programList[i].totalNotAttenedCount+'&nbsp;<span class="font-10 text-danger"> '+programList[i].totalNotAttenedCountPer+'%</span></h4>';
						str+='<p class="text-muted text-capital">yet to train</p>';
					str+='</td>';
				str+='</tr>';
				for(var j in programList[i].locationList)
         {
          str+='<tr>';
            str+='<td>'+programList[i].locationList[j].name+'</td>';
            str+='<td>'+programList[i].locationList[j].totalAttenedCount+'</td>';
            str+='<td>'+programList[i].locationList[j].inviteeAttended+'<span class="font-10 text-danger"> '+programList[i].locationList[j].totalAttenedCountPer+'%</span></td>';
            str+='<td>'+programList[i].locationList[j].nonInviteeAttended+'</td>';
            str+='<td>'+programList[i].totalNotAttenedCount+'</td>';
          str+='</tr>';
         }
			str+='</table>';
			str+='<hr class="m_0"/>';
		 str+='</div>';	
	  }
	  //setLastUpdatedTimeForTrainingCamp(lastUPdatedTime);
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
				str1+='<p class="text-muted text-capitalize">Total Attended </p>';
				str1+='<h4>'+villageWardRslt.totalAttenedCount+'&nbsp;</h4>'
			str1+='</td>';
			str1+='<td>';
				str1+='<p class="text-muted text-capitalize">Invitee Attended</p>';
				str1+='<h4>'+villageWardRslt.inviteeAttended+'&nbsp;<span class="font-10 text-danger"> '+programList[i].totalAttenedCountPer+'%</span></h4>'
			str1+='</td>';
			str1+='<td>';
				str1+='<p class="text-muted text-capitalize">Non Invitee Attended</p>';
				str1+='<h4>'+villageWardRslt.nonInviteeAttended+'&nbsp;</h4>'
			str1+='</td>';
			str1+='<td>';
				str1+='<p class="text-muted text-capitalize">yet to train</p>';
				str1+='<h4>'+villageWardRslt.totalNotAttenedCount+'&nbsp;<span class="font-10 text-danger"> '+villageWardRslt.totalNotAttenedCountPer+'%</span></h4>';
			str1+='</td>';
		str1+='</tr>';
		for(var j in villageWardRslt.locationList)
         {
			str1+='<tr>';
            str1+='<td>'+villageWardRslt.locationList[j].name+'</td>';
            str1+='<td>'+villageWardRslt.locationList[j].totalAttenedCount+'</td>';
            str1+='<td>'+villageWardRslt.locationList[j].inviteeAttended+'<span class="font-10 text-danger"> '+villageWardRslt.locationList[j].totalAttenedCountPer+'%</span></td>';
            str1+='<td>'+villageWardRslt.locationList[j].nonInviteeAttended+'</td>';
			str1+='<td>'+villageWardRslt.totalNotAttenedCount+'</td>';
            str1+='</tr>';
         }
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
				str2+='<p class="text-muted text-capitalize">Total Attended</p>';
				str2+='<h4>'+mandalTwnDivRslt.totalAttenedCount+'</h4>'
			str2+='</td>';
			str2+='<td>';
				str2+='<p class="text-muted text-capitalize">Invitee Attended</p>';
				str2+='<h4>'+mandalTwnDivRslt.inviteeAttended+'&nbsp;<span class="font-10 text-danger"> '+programList[i].totalAttenedCountPer+'%</span> </h4>'
			str2+='</td>';
			str2+='<td>';
				str2+='<p class="text-muted text-capitalize">Non Invitee Attended</p>';
				str2+='<h4>'+mandalTwnDivRslt.nonInviteeAttended+'</h4>'
			str2+='</td>';
			str2+='<td>';
				str2+='<p class="text-muted text-capitalize">yet to train</p>';
				str2+='<h4>'+mandalTwnDivRslt.totalNotAttenedCount+' <span class="font-10 text-danger">'+mandalTwnDivRslt.totalNotAttenedCountPer+'%</span></h4>';
			str2+='</td>';
		str2+='</tr>';
		for(var j in mandalTwnDivRslt.locationList)
         {
			str2+='<tr>';
            str2+='<td>'+mandalTwnDivRslt.locationList[j].name+'</td>';
            str2+='<td>'+mandalTwnDivRslt.locationList[j].totalAttenedCount+'</td>';
            str2+='<td>'+mandalTwnDivRslt.locationList[j].inviteeAttended+'<span class="font-10 text-danger"> '+mandalTwnDivRslt.locationList[j].totalAttenedCountPer+'%</span></td>';
            str2+='<td>'+mandalTwnDivRslt.locationList[j].nonInviteeAttended+'</td>';
			str2+='<td>'+mandalTwnDivRslt.totalNotAttenedCount+'</td>';
            str2+='</tr>';
         }
	 str2+='</table>';		
	}else{
	 str2+='NO DATA AVAILABLE';		
	}
	}else{
	 str2+='NO DATA AVAILABLE';	
	}
    $("#mdlTwnDvsnTabId").html(str2);  
  }
 function getTrainingCampProgramsDetailsCntByUserType(){
		$("#districtWiseProgramCntDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		 var dateStr = $("#dateRangeIdForTrainingCamp").val();
		 var enrollmentYrIds = [];
         enrollmentYrIds.push($("#tdpTriningYearId").val());
		 var programIdArr=[];
		  if(enrollmentYrIds != null && enrollmentYrIds.length>0){
			 for(var i in enrollmentYrIds){
				 if(parseInt(enrollmentYrIds[i]) == 4){
					 programIdArr=[];
					 programIdArr=[8];
				 }
				 else  if(parseInt(enrollmentYrIds[i]) == 3){
					 programIdArr=[];
					 programIdArr=[1,6,7];
				 }
			 }
		 }
	 
		var jsObj ={ 
		             userAccessLevelId : globalUserAccessLevelId,
					 userAccessLevelValuesArray : globalUserAccessLevelValues,
					 stateId : globalStateId,
					 dateStr : dateStr,
					 userTypeId : globalUserTypeId,
					 activityMemberId : globalActivityMemberId,
					 enrollmentYearIdsList :enrollmentYrIds,
					 trainingCampProgramIds:programIdArr
					 
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
	   var enrollmentYrIds = [];
	   enrollmentYrIds.push($("#tdpTriningYearId").val());
	var programIdArr = [];//[6,7]
		 if(enrollmentYrIds != null && enrollmentYrIds.length>0){
			 for(var i in enrollmentYrIds){
				 if(parseInt(enrollmentYrIds[i]) == 4){
					 programIdArr=[];
					 programIdArr=[8];
				 }
				 else  if(parseInt(enrollmentYrIds[i]) == 3){
					 programIdArr=[];
					 programIdArr=[1,6,7];
				 }
			 }
		 }
	 var jsObj ={
			          userAccessLevelId:globalUserAccessLevelId,
					  userAccessLevelValuesArray:globalUserAccessLevelValues,
					  activityMemberId : globalActivityMemberId,
					  userTypeId : globalUserTypeId,
					  stateId : globalStateId,
					  dateStr : dateStr,
					  enrollmentYrIds:enrollmentYrIds,
					  programIdArr:programIdArr
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
			  str+='<div id="genSecTraining'+i+'" style="height:100px;"></div>';
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
		 if(result[i][0].totalAttenedCountPer!=0){
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
		}else{
		//$("#genSecTraining"+i).html("No Data Available");
		$("#genSecTraining"+i).css("height","35px");
		} 
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
				if(result[i] != null && result[i].length > 0){
					var length = result[i].length - 1;
					for(var j = length; j >= 0; j--){
						candidateNameArray.push(result[i][j].name);
						trainingProgramCountArray.push(result[i][j].totalAttenedCountPer);
						countVar =countVar+1;
						if (countVar === 5) {
							break;
						}
					}	
				}
			//if( result[i][j].totalAttenedCountPer!=0){
			//var getWidth = $("#genSecTraining"+i).parent().width()+'px';
				//$("#genSecTraining"+i).width(getWidth);
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
	var programIdArr = [];//[6,7]
	var dateStr = $("#dateRangeIdForTrainingCamp").val();
	if(dateStr == undefined || dateStr== null){ // Stopping default call when user has no training access.
		return;
	}
	var enrollmentYrIds = [];
	 enrollmentYrIds.push($("#tdpTriningYearId").val());
	 
	 if(enrollmentYrIds != null && enrollmentYrIds.length>0){
		 for(var i in enrollmentYrIds){
			 if(parseInt(enrollmentYrIds[i]) == 4){
				 programIdArr=[];
				 programIdArr=[8];
			 }
			 else  if(parseInt(enrollmentYrIds[i]) == 3){
				 programIdArr=[];
				 programIdArr=[1,6,7];
			 }
		 }
	 }
	 
	var jsObj={
		programIdArr : programIdArr,
		stateId : globalStateId,
		dateStr : "01/01/2000",
		enrollmentYrIds:enrollmentYrIds,
		option : "total" 
	}
	$.ajax({
		type : 'GET',
		url : 'getStateLevelCampAttendedDetails.action',     
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)}
	}).done(function(result){    
		if(result != null && result.length > 0){
			buildStateLevelCampAttendedDetails(result);
		}else{
			var str = '';
			str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
			str+='<h4 class="text-capital"><span class="headingColor">state level training program</span><span style="background-color:#fff;margin-left:5px;" class="stateLevelTraining" expand-icon-inner="training" state-level="true" attr_location="State Level Training Program"><i class="glyphicon glyphicon-fullscreen" ></i></span></h4>';    
			str+='No Data Available';
			$("#stateLevelCampId").html(str);
		}
	});
}  
function buildStateLevelCampAttendedDetails(result){ 
	var programIdsStr = [];
	for(var i in result){
		programIdsStr.push(result[i].id);     
	}      
	var str = '';
	str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
		str+='<h4 class="text-capital"><span class="headingColor">state level training program</span><!--<span style="background-color:#fff;margin-left:5px;" attr_program_id="'+programIdsStr+'" class="stateLevelTraining" expand-icon-inner="training" state-level="true" attr_location="State Level Training Program"><i class="glyphicon glyphicon-fullscreen"></i>--></span></h4>';
		for(var i in result){    
		str+='<h5 class="text-capital m_top10">'+result[i].name+'<span style="background-color:#fff;margin-left:5px;border:0px;padding:2px;" state-level="true" expand-icon-inner="training" class="" attr_program_id="'+result[i].id+'" attr_location="'+result[i].name+'"> <!--<i class="glyphicon glyphicon-fullscreen"></i>--></span></h5>';  
		if($(window).width() < 300)
		{
			str+='<div class="table-responsive">';
		}
		str+='<table class="table tableTraining bg_ED">';     
			str+='<tbody>';
				str+='<tr>';
					str+='<td>';
						str+='<p class="text-muted text-capitalize">Days</p>';
						//str+='<p class="responsiveFont">'+result[i].count+'</p>';
						str+='<p class="responsiveFont">-</p>';
					str+='</td>';
					str+='<td>';
					str+='<p class="text-muted text-capitalize">Total Attended</p>';
					str+='<p class="responsiveFont">'+(parseInt(result[i].inviteeAttnd)+parseInt(result[i].nonInviteeAttnd))+'</hp>'; 
					str+='</td>';							
					str+='<td>';
						str+='<p class="text-muted text-capitalize" title="Invitee Attended">Invitee Attended</p>';
						str+='<p class="responsiveFont">'+result[i].inviteeAttnd+'</p>';
			str+='</td>';
			str+='<td>';
			var NIA = result[i].nonInviteeAttnd;
						str+='<p class="text-muted text-capitalize" title="Non Invitee Attended">Non Invitee Attended</p>';
						str+='<p class="responsiveFont">'+NIA+'</p>';
			str+='</td>';
			  
							str+='<td>';
								var abs = result[i].count - result[i].actualCount; 
								str+='<p class="text-muted text-capitalize">absent</p>    ';
								//str+='<p class="responsiveFont">'+abs+'<span class="font-10 text-danger">'+(100-per).toFixed(2)+'%</span></p>';     
								
					str+='</td>';
				str+='</tr>';
			for(var j in result[i].idnameList)
			 {
			  str+='<tr>';
				str+='<td>'+result[i].idnameList[j].name+'</td>';
				str+='<td>'+result[i].idnameList[j].totalCount+'</td>';
				str+='<td>'+result[i].idnameList[j].inviteeAttnd+'&nbsp;<span class="font-10 text-danger"> '+result[i].idnameList[j].percentage+'%</span></td>';
				str+='<td>'+result[i].idnameList[j].nonInviteeAttnd+'</td>';
				
			  str+='</tr>';
			 }
			str+='</tbody>';
		str+='</table>';  
		if($(window).width() < 300)
		 {
			 str+='</div>';
		 }
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
/* $(document).on("click",".stateLevelTrainingInd",function(){
	$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
	$(".stateLevelTraining,.programSkillsCls").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen")
	$(".trainingIconExpand").find("i").addClass("glyphicon-resize-small").removeClass("glyphicon-fullscreen");
	if( !$(this).find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".trainingsHiddenBlock,.trainingDetailedBlock").hide();
		$(".trainingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".trainingsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
	}else if($(".trainingsBlockExpand").hasClass("col-md-6")){
		$(".trainingsHiddenBlock,.moreTrainingCampBlocksIcon").show();
		var programId = [];
		programId.push($(this).attr("attr_program_id"));
		getStateLevelCampCount(programId);         
		stateLevelCampMembersDistWise(programId); 
		stateLevelCampDetailsRepresentativeWise(programId);
		var val = $(this).attr("attr_location");
		$("#clickInfoId").html(val); 
		$("#switchButtonId").removeClass("moreTrainingBlocksIcon");
		$("#switchButtonId").addClass("moreTrainingCampBlocksIcon");   
		$("#detailedId").removeClass("trainingDetailed");
		$("#detailedId").addClass("trainingCampDetailed");
		$("#clickInfoId").show();
		$(".trainingComparison").hide();
		$(".trainingCampDetailed").attr("attr_program_id",$(this).attr("attr_program_id"));  
	}else{
		
		if(!$(".trainingIconExpand").hasClass("glyphicon-fullscreen"))
		{
			$(".trainingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".trainingsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
			$(".trainingIconExpand").find("i").addClass("glyphicon-resize-small").removeClass("glyphicon-fullscreen");
		}
		$(".trainingsHiddenBlock,.moreTrainingCampBlocksIcon").show();
		var programId = [];
		programId.push($(this).attr("attr_program_id"));
		$("#districtWiseProgramsCntDivId").html(" ");
		getStateLevelCampCount(programId);         
		stateLevelCampMembersDistWise(programId); 
		stateLevelCampDetailsRepresentativeWise(programId);
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
	
}); */
/* $(document).on("click",".programSkillsCls",function(){
	$(".dateRangePickerClsForTraining").toggleClass("hide");	
	$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
	$(".trainingIconExpand").find("i").removeClass("glyphicon-fullscreen").addClass("glyphicon-resize-small");
	$(".stateLevelTraining,.stateLevelTrainingInd").find("i").addClass("glyphicon-fullscreen").removeClass("glyphicon-resize-small");
	if($(".trainingIconExpand").find("i").hasClass("glyphicon-fullscreen"))
	{
		$(".trainingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".trainingsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
	}
	if( !$(this).find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".moreTrainingBlocks").hide();
		$(".trainingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".trainingsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
		$(".trainingIconExpand").find("i").addClass("glyphicon-fullscreen").removeClass("glyphicon-resize-small");
	}else if($(".trainingsBlockExpand").hasClass("col-md-6")){
		getUserTypeWiseTotalEligibleAndAttendedCnt();
		setTimeout(function(){
			$('html,body').animate({
				scrollTop: $(".trainingsBlock").offset().top},
			'slow');
		},500);
	}else{
		setTimeout(function(){
			$('html,body').animate({
				scrollTop: $(".trainingsBlock").offset().top},
			'slow');
		},500);
		getUserTypeWiseTotalEligibleAndAttendedCnt();
		$(".trainingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".trainingsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
		$(".trainingIconExpand").find("i").removeClass("glyphicon-fullscreen").addClass("glyphicon-resize-small");
	}
	if( !$(".iconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".moreBlocks").hide();
		$(".moreBlocks1").hide();
		$(".moreBlocksDetailAndComp").hide();
		$(".moreBlocksDistrictlevel").hide();
	}
	if( $(".iconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".iconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".committeesHiddenBlock,.moreBlocks,.moreBlocks1,.moreBlocksDetailAndComp,.moreBlocksIcon,.moreBlocksDistrictlevel").hide();
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
	}else if( $(".eventsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".eventsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".eventsHiddenBlock,.moreEventsBlocks,.comparisonBlockEvents,.detailedBlockEvents,.comparisonBlockActivities ").hide();
		$(".panelBlockCollapseIcon").addClass("collapsed");
		$(".activitesExpandIcon").parent().parent().parent().parent().find(".collapse").removeClass("in").addClass("collapsed");
		$(".activitesExpandIcon").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen");
		$(".eventsListExpandIcon").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen");
		$(".eventsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".dateRangePickerClsForEvents").toggleClass("hide");
	}else if( $(".cadreExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".cadreExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".moreCadreBlock,.moreBlocksCadre,.moreBlocksCadreIcon").hide();
		$(".cadreBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}else if( $(".attendaceIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".attendaceIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".attendanceBlockMore,.moreAttBlocks,.moreAttBlocksIcon").hide();
		$(".dateRangePickerClsForAttendance").toggleClass('hide');
		$(".attendanceBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}else if( $(".NewTourExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".NewTourExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".NewTourExpandCls,.NewToursHiddenBlock,.moreNewToursBlocksDetailed").hide();
			$(".NewToursBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}else if( $(".alertsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".alertsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".districtAltCtnCls ,.alertLocationDiv,.dateRangePickerClsForAlert,.alertComparisonblock").hide();
			$(".alertsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}
	
	 setTimeout(function(){
		$(".trainingsHiddenBlock,.moreTrainingBlocksIcon").toggle();
	},800); 
}); */
/* $(document).on("click",".trainingIconExpand",function(){
	$(".dateRangePickerClsForTraining").toggleClass("hide");	
	$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
	$(".programSkillsCls").find("i").removeClass("glyphicon-fullscreen").addClass("glyphicon-resize-small");
	$(".stateLevelTraining,.stateLevelTrainingInd").find("i").addClass("glyphicon-fullscreen").removeClass("glyphicon-resize-small");
	if(!$(".trainingIconExpand").find("i").hasClass("glyphicon-fullscreen"))
	{
		$(".trainingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".trainingsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
	}
	if( !$(this).find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".trainingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".trainingsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
		$(".moreTrainingBlocks").hide();
		$(".programSkillsCls").find("i").addClass("glyphicon-fullscreen").removeClass("glyphicon-resize-small");
	}else{
		getUserTypeWiseTotalEligibleAndAttendedCnt();
		setTimeout(function(){
			$('html,body').animate({
				scrollTop: $(".trainingsBlock").offset().top},
			'slow');
		},500);
	}
	if( !$(".iconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".moreBlocks").hide();
		$(".moreBlocks1").hide();
		$(".moreBlocksDetailAndComp").hide();
		$(".moreBlocksDistrictlevel").hide();
	}
	if( $(".iconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".iconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".committeesHiddenBlock,.moreBlocks,.moreBlocks1,.moreBlocksDetailAndComp,.moreBlocksIcon,.moreBlocksDistrictlevel").hide();
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
	}else if( $(".eventsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".eventsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".eventsHiddenBlock,.moreEventsBlocks,.comparisonBlockEvents,.detailedBlockEvents").hide();
		$(".eventsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".dateRangePickerClsForEvents").toggleClass("hide");
	}else if( $(".cadreExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".cadreExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".moreCadreBlock,.moreBlocksCadre,.moreBlocksCadreIcon").hide();
		$(".cadreBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}else if( $(".attendaceIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".attendaceIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".attendanceBlockMore,.moreAttBlocks,.moreAttBlocksIcon").hide();
		$(".dateRangePickerClsForAttendance").toggleClass('hide');
		$(".attendanceBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}else if( $(".NewTourExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".NewTourExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".NewTourExpandCls,.NewToursHiddenBlock,.moreNewToursBlocksDetailed").hide();
			$(".NewToursBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}else if( $(".alertsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".alertsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".districtAltCtnCls ,.alertLocationDiv,.dateRangePickerClsForAlert,.alertComparisonblock").hide();
			$(".alertsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}else if( $(".tourExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
				$(".tourExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
				$(".tourExpandCls ,.toursHiddenBlock,.moreToursBlocks1,.moreToursBlocksDetailed ,.comparisonBlockTours ,.toursDateRangePickerCls").hide();
				$(".toursBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}else if( $(".emnIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
				$(".emnIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
				$(".moreBlockEMN ,.newEmnHideCls,.dateRangePickerClsForEmn,.newsComparisonUl").hide();
				$(".electronicMediaBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}
	
	 setTimeout(function(){
		$(".trainingsHiddenBlock,.moreTrainingBlocksIcon").toggle();
	},800); 
}); */
/* $(document).on("click",".stateLevelTraining",function(){
	$(".trainingIconExpand").find("i").addClass("glyphicon-resize-small").removeClass("glyphicon-fullscreen")
	$(".stateLevelTrainingInd").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen")
	$(this).find("i").toggleClass("glyphicon-resize-small").toggleClass("glyphicon-fullscreen");
	
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
		$("#districtWiseProgramsCntDivId").html(" ");
		getStateLevelCampCount(programIdArr);         
		stateLevelCampMembersDistWise(programIdArr);       
		stateLevelCampDetailsRepresentativeWise(programIdArr); 
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
	
	
	
}); */
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
	$("#detailedId").closest(".trainingDetailedBlock").show();
	getAllItsSubUserTypeIdsByParentUserTypeIdForTrainingProgram();
});

$(document).on("click",".unExpandTrainingBlock",function(){
		$(this).removeClass("unExpandTrainingBlock");
		$(".moreTrainingBlocks").hide();
	});
  function getAllItsSubUserTypeIdsByParentUserTypeIdForTrainingProgram(){
	     
		 $("#trainingChildActivityMemberDivId").html(' ');
		 $("#userTypeWiseChildDtlsTabId").html(' ');
		 $("#poorPerformancTrainingPrograLocationsDivId").html('');
		 
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
		 var userType;
		 if(result !=null && result.length >0){
			  firstChildUserTypeIdString = result[0].shortName;
			  userType=result[0].userType;
			 for(var i in result){
              str+='<li attr_userTypeId="'+result[i].shortName+'" attr_userType=\''+result[i].userType+'\' class="childUserTypeClsForTrainingProgram">'+result[i].userType+'<span class="closeIconComparison"></span></li>';
			 }
		 }
		str+='</ul>';
		$("#childUserTypeDetailsDivForTrainingProgram").html(str);
		$(".comparisonSelect li:first-child").addClass("active")
		
		getSelectedChildTypeMembersForTrainingProgram(firstChildUserTypeIdString,userType);
		//getTrainingProgramPoorCompletedLocationDtls();
	}
	function getSelectedChildTypeMembersForTrainingProgram(firstChildUserTypeIdString,childUserType){
	 $("#trainingChildActivityMemberDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	 $("#userTypeWiseChildDtlsTabId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	  var parentActivityMemberId = globalActivityMemberId;
	  var childUserTypeIdsArray = firstChildUserTypeIdString.split(",");
	  var dateStr = $("#dateRangeIdForTrainingCamp").val();
	  var enrollmentYrIds = [];
         enrollmentYrIds.push($("#tdpTriningYearId").val());
	
	var programIdArr = [];//[6,7]
	 if(enrollmentYrIds != null && enrollmentYrIds.length>0){
		 for(var i in enrollmentYrIds){
			 if(parseInt(enrollmentYrIds[i]) == 4){
				 programIdArr=[];
				 programIdArr=[8];
			 }
			 else  if(parseInt(enrollmentYrIds[i]) == 3){
				 programIdArr=[];
				 programIdArr=[1,6,7];
			 }
		 }
	 }
	 
	  var jsObj ={ 
	               parentActivityMemberId : parentActivityMemberId,
				   childUserTypeIdsArray : childUserTypeIdsArray,
				   userAccessLevelId : globalUserAccessLevelId,
				   userAccessLevelValuesArray : globalUserAccessLevelValues,
				   reportType :"selectedUserType",
				   stateId : globalStateId,
				   dateStr : "01/01/2000",
				   enrollmentYearIdsList :enrollmentYrIds,
				   programIdArr:programIdArr
				 }
	  $.ajax({
			type : 'POST',
			url : 'getSelectedChildTypeMembersForTrainingProgramAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		   $("#trainingChildActivityMemberDivId").html(' ');
		   $("#userTypeWiseChildDtlsTabId").html(' ');
		  if(result != null && result.length > 0){
			  buildChildTypeMembersForTrainingReslt(result,childUserType);
		  }else{
			  $("#trainingChildActivityMemberDivId").html("NO DATA AVAILABLE");
		  }
		});
 }
 function buildChildTypeMembersForTrainingReslt(result,childUserType){
	 //$('html,body').animate({scrollTop: $("#trainingChildActivityMemberDivId").offset().top}, 'slow');
	  var userTypeId = result[0].userTypeId;
	  var activityMemberId = result[0].activityMemberId;
	  var selectedMemberName = result[0].name;
	  var selectedUserType = result[0].userType;
	  var str='';
        if(childUserType != null && childUserType.trim()=="MLA/CI" || childUserType.trim()=="MLA" || childUserType.trim()=="CONSTITUENCY INCHARGE"){
	     str+='<table style="background-color:#EDEEF0;border:1px solid #ddd" class="table table-condensed tableHoverLevels" id="trainingMembersDtlsDataTblId">';
		 str+='<thead>';
		     str+='<th>Rank</th>';
			 str+='<th>Name</th>';
			 str+='<th>Designation</th>';
			 str+='<th>Location</th>';
			 str+='<th>Eligible</th>';
			 str+='<th>Attended</th>';
			 str+='<th>%</th>';
			 str+='<th title="Invitee Attended">IA</th>';
			 str+='<th title="Non Invitee Attended">NIA</th>';
			 str+='<th>Yet to train</th>';
			 str+='<th>%</th>';
		 str+='</thead>';
		 str+='<tbody>';
		 var rank=1;
		  for(var i in result){
			str+='<tr style="cursor:pointer;" class="activityMemberCls"  attr_selectedusertype="'+result[i].userType+'"  attr_id="userTypeWiseChildDtlsTabId"  attr_selectedmembername="'+result[i].name+'"  attr_activitymemberid='+result[i].activityMemberId+'  attr_usertypeid='+result[i].userTypeId+'>';
			 str+='<td><span class="counts">'+rank+'</span></td>';
			 str+='<td>'+result[i].name+'</td>';
			 str+='<td>'+result[i].userType+'</td>';
			 str+='<td>'+result[i].locationName+'</td>';
			 str+='<td>'+result[i].totalEligibleCount+'</td>';
			 str+='<td>'+result[i].totalAttenedCount+'</td>';
			 str+='<td>'+result[i].totalAttenedCountPer+'%</td>';
			 str+='<td>'+result[i].inviteeAttendedCnt+'</td>';
			 str+='<td>'+result[i].nonInviteeAttendedCnt+'</td>';
			 str+='<td>'+result[i].totalNotAttenedCount+'</td>';
			 str+='<td>'+result[i].totalNotAttenedCountPer+'%</td>';
			 str+='</tr>';
             rank=rank+1;			 
			}
			 str+='</tbody>';
			 str+='</table>';
	    $("#trainingChildActivityMemberDivId").html(str);
		$("#trainingMembersDtlsDataTblId").dataTable({
		"iDisplayLength": 5,
		"aLengthMenu": [[5, 20, 100, -1], [5, 20, 100, "All"]]
		}); 
	  getTrainingProgramPoorCompletedLocationDtls(userTypeId,activityMemberId,selectedMemberName,selectedUserType);
	  }else{
	  str+='<ul class="list-inline slickPanelSliderTraining">';
	  var rank=1; 
	   for(var i in result){
	str+='<li style="cursor:pointer;" class="activityMemberCls"  attr_selectedusertype="'+result[i].userType+'"  attr_id="userTypeWiseChildDtlsTabId"  attr_selectedmembername="'+result[i].name+'"  attr_activitymemberid='+result[i].activityMemberId+'  attr_usertypeid='+result[i].userTypeId+' style="width:400px !important;">';
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
	   if(result[i].userTypeId != null && result[i].userTypeId==7 || result[i].userTypeId==9 || result[i].userTypeId==5 || result[i].userTypeId==6){// MLA Constituency Incharge, MP and District President Incharge 
		   var lctnName = result[i].locationName;
           lctnName = lctnName.substring(0, lctnName.lastIndexOf(" "));
		 str+='<h4 class="text-capital">'+result[i].userType+' ('+lctnName+')</h4>';	 
		 }else{
		 str+='<h4 class="text-capital">'+result[i].userType+'</h4>';	 
		 }
			 str+='<table class="table table-condensed">';
				 str+='<thead>';
					 str+='<th>Eligible</th>';
					 str+='<th>Total Attended</th>';
					 str+='<th colspan="1"  title="Invitee Attended" >IA  </th>';
					 str+='<th colspan="1"  title="Non Invitee Attended">NIA</th>';
					  str+='<th colspan="1"  title="Invitee Attended %" >IA % </th>';
				    str+='<th colspan="2">Yet to train</th>';
				 str+='</thead>';
				 str+='<tr>';
					 str+='<td>'+result[i].totalEligibleCount+'</td>';
					 str+='<td>'+result[i].totalAttenedCount+'</td>';					
					 str+='<td>'+result[i].inviteeAttendedCnt+'</td>';					 
					 str+='<td>'+result[i].nonInviteeAttendedCnt+'</td>';
					 str+='<td>'+result[i].totalAttenedCountPer+'%</td>';
					 str+='<td>'+result[i].totalNotAttenedCount+'</td>';
					 str+='<td>'+result[i].totalNotAttenedCountPer+'%</td>';
				 str+='</tr>';
				 for(var j in result[i].daysList)
				 {
					str+='<tr>';
						str+='<td>'+result[i].daysList[j].name+'</td>';
						str+='<td>'+result[i].daysList[j].totalAttenedCount+'</td>';
						str+='<td>'+result[i].daysList[j].inviteeAttended+'</td>';
						str+='<td>'+result[i].daysList[j].nonInviteeAttended+'</td>';
						str+='<td>'+result[i].daysList[j].totalAttenedCountPer+'%</td>';
						str+='<td>'+result[i].totalNotAttenedCount+'</td>';
						str+='<td>'+result[i].totalNotAttenedCountPer+'%</td>';
					str+='</tr>';
				 }
			 str+='</table>';
		 str+='</div>';
	 str+='</div> ';
    str+=' </li> ';  
	rank=rank+1;
   }
   $("#trainingChildActivityMemberDivId").html(str);
	$(".slickPanelSliderTraining").slick({
			 slide: 'li',
			 slidesToShow: 3,
			 slidesToScroll: 3,
			 infinite: false,
			 variableWidth: true,
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
 }
 $(document).on("click",".childUserTypeClsForTrainingProgram",function(){
	var childUserTypeId = $(this).attr("attr_userTypeId");
	var childUserType = $(this).attr("attr_userType");
	getSelectedChildTypeMembersForTrainingProgram(childUserTypeId,childUserType);
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
		if(selectedUserType != null && selectedUserType.trim()=="MLA/CI" || selectedUserType.trim()=="MLA" || selectedUserType.trim()=="CONSTITUENCY INCHARGE"){
		getTrainingProgramPoorCompletedLocationDtls(userTypeId,activityMemberId,selectedMemberName,selectedUserType);	 
		 }else{
		 getDirectChildActivityTrainingProgramMemberDetails(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId);
	     getTrainingProgramPoorCompletedLocationDtls(userTypeId,activityMemberId,selectedMemberName,selectedUserType);	 
		 }
});
$(document).on("click",".remveSlcUsrType",function(){
		 var removeSelected = $(this).attr("attr_removeSelecUserType"); 
		 $("#"+removeSelected).html(' ');
		 $("#"+removeSelected).closest('.showHideTr').hide();
	});
$(document).on("click",".lowLevelActivityMemberClsForTrainingProgram",function(){
		$('tr.showHideTr').hide(); 
	    $(this).next('tr.showHideTr').show(); 
		var activityMemberId = $(this).attr("attr_activitymemberid");  
		var userTypeId = $(this).attr("attr_usertypeid"); 
		var selectedMemberName = $(this).attr("attr_selectedmembername");  
		var selectedUserType = $(this).attr("attr_selectedusertype");  
		var childActivityMemberId = $(this).closest('tr').next('tr.showHideTr').attr("attr_id");  
		 if(selectedUserType != null && selectedUserType.trim()=="MLA/CI" || selectedUserType.trim()=="MLA" || selectedUserType.trim()=="CONSTITUENCY INCHARGE"){
		  getTrainingProgramPoorCompletedLocationDtls(userTypeId,activityMemberId,selectedMemberName,selectedUserType);	 
		 }else{
		 getDirectChildActivityTrainingProgramMemberDetails(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId);
	     getTrainingProgramPoorCompletedLocationDtls(userTypeId,activityMemberId,selectedMemberName,selectedUserType);	 
		 }	
});
  function getDirectChildActivityTrainingProgramMemberDetails(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId){
	  $("#"+childActivityMemberId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	  var dateStr = $("#dateRangeIdForTrainingCamp").val();
	  var childUserTypeIdsArray=[];
	             childUserTypeIdsArray.push(userTypeId);
				 var enrollmentYrIds = [];
         enrollmentYrIds.push($("#tdpTriningYearId").val());
		 var programIdArr = [];//[6,7]
		 if(enrollmentYrIds != null && enrollmentYrIds.length>0){
			 for(var i in enrollmentYrIds){
				 if(parseInt(enrollmentYrIds[i]) == 4){
					 programIdArr=[];
					 programIdArr=[8];
				 }
				 else  if(parseInt(enrollmentYrIds[i]) == 3){
					 programIdArr=[];
					 programIdArr=[1,6,7];
				 }
			 }
		 }
		 
	  var jsObj ={  activityMemberId : activityMemberId,
			         childUserTypeIdsArray : childUserTypeIdsArray,
					 reportType : "directChild",
					 stateId : globalStateId,
					 dateStr : "01/01/2000",
					 enrollmentYearIdsList :enrollmentYrIds,
					 programIdArr:programIdArr
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
			  if(childActivityMemberId == "userTypeWiseChildDtlsTabId"){
				$("#"+childActivityMemberId).html("<h5><span  class='text-capital'>"+selectedMemberName+"</span> - <span class='text-capitalize'>"+selectedUserType+"</span> - ( No Data Available )</h5>");
			}
			//  $("#"+childActivityMemberId).html('NO DATA AVAILABLE');  
		  }
		});
	}
	function buildChildActivityMembersDetails(result,selectedMemberName,selectedUserType,childActivityMemberId,userTypeId){
		var str=''; 
		str+='<h4><span  class="text-capital">'+selectedMemberName+'</span> - <span class="text-capitalize">'+selectedUserType+'</span></h4>';
		 if(childActivityMemberId != "userTypeWiseChildDtlsTabId"){
				str+='<span class="remveSlcUsrType pull-right" attr_removeSelecUserType = "'+childActivityMemberId+'" style="margin-top: -5px;"><i class="glyphicon glyphicon-remove"></i></span>';
		 } 
		 str+='<div class="table-responsive">';
		 if(childActivityMemberId != "userTypeWiseChildDtlsTabId"){
			 str+='<table  class="table table-condensed tableHoverLevelsInner m_top20">';
		 }else{
			str+='<table class="table table-condensed tableHoverLevels m_top20">';  
		 }
				str+='<thead   class="bg_D8 text-capital">';
					str+='<th>Rank</th>';
					str+='<th>Designation</th>';
					str+='<th>Name</th>';
					str+='<th style="text-align:center;"> Eligible</th>';
					str+='<th style="text-align:center;"> Total Attended</th>';
					str+='<th style="text-align:center;" title="Invitee Attended "> IA </th>';
					str+='<th style="text-align:center;" title=" Non Invitee Attended "> NIA </th>';
					str+='<th style="text-align:center;"  title="Invitee Attended %"> IA %</th>';
					str+='<th style="text-align:center;">Not Attended</th>';
					str+='<th style="text-align:center;">%</th>';
					str+='<th style="text-align:center;">1 day</th>';
					str+='<th style="text-align:center;">%</th>';
					str+='<th style="text-align:center;">2 day</th>';
					str+='<th style="text-align:center;">%</th>';
					str+='<th style="text-align:center;">3 day</th>';
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
			str+='<td style="text-align:center;">'+result[i].inviteeAttendedCnt+'</td>';
			str+='<td style="text-align:center;">'+result[i].nonInviteeAttendedCnt+'</td>';
			str+='<td style="text-align:center;">'+result[i].totalAttenedCountPer+'</td>';
			str+='<td style="text-align:center;">'+result[i].totalNotAttenedCount+'</td>';
			str+='<td style="text-align:center;">'+result[i].totalNotAttenedCountPer+'</td>';
			
			for(var j in result[i].daysList)
			{
				str+='<td style="text-align:center;">'+result[i].daysList[j].inviteeAttended+'</td>';
				str+='<td style="text-align:center;">'+result[i].daysList[j].totalAttenedCountPer+'</td>';
			}
			
		 str+='</tr>';
		str+='<tr class="showHideTr" style="display:none" attr_id = "subChildLevelMemDtslId'+result[i].userTypeId+''+i+'">';
		str+='<td colspan="16"  id="subChildLevelMemDtslId'+result[i].userTypeId+''+i+'">';
		str+='</td>';
		 rank=rank+1;
		 }
		str+='</tbody>';
		str+='</table>';
		str+='</div>';
	    $("#"+childActivityMemberId).html(str);
	}
/* more training blocks start*/
function getTrainingProgramBasicCnt(){
	$("#programsDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	 var dateStr = $("#dateRangeIdForTrainingCamp").val();
	 var enrollmentYrIds = [];
         enrollmentYrIds.push($("#tdpTriningYearId").val());
 	  var jsObj ={ 
	               userAccessLevelId : globalUserAccessLevelId,
				   userAccessLevelValuesArray : globalUserAccessLevelValues,
				    stateId : globalStateId,
					dateStr : dateStr,
					enrollmentYearIdsList :enrollmentYrIds
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
				// You can unslick at a given breakpoint now by adding:
				// settings: "unslick"
				// instead of a settings object
			  ]
		}); 
	if(result != null && result.length > 0){
		
		  for(var i in result){
			var  jsonDataArrAttended=[];
			var  jsonDataArrYettotrain=[];
			//jsonDataArr.push({name:"Total Eligible",data:[result[i].totalEligibleCountPer,result[i].totalEligibleCountPer]});
			//jsonDataArrAttended.push({name:"Attended",data:[result[i].totalAttenedCountPer,0]});
			jsonDataArrAttended.push(result[i].totalAttenedCountPer);
			jsonDataArrYettotrain.push(result[i].totalNotAttenedCountPer); 
			
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
	 function getTrainingProgramPoorCompletedLocationDtls(userTypeId,activityMemberId,selectedUserName,userType){
    $("#poorPerformancTrainingPrograLocationsDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>'); 
	  var dateStr = $("#dateRangeIdForTrainingCamp").val();
	   var enrollmentYrIds = [];
         enrollmentYrIds.push($("#tdpTriningYearId").val());
	var programIdArr = [];//[6,7]
	 if(enrollmentYrIds != null && enrollmentYrIds.length>0){
		 for(var i in enrollmentYrIds){
			 if(parseInt(enrollmentYrIds[i]) == 4){
				 programIdArr=[];
				 programIdArr=[8];
			 }
			 else  if(parseInt(enrollmentYrIds[i]) == 3){
				 programIdArr=[];
				 programIdArr=[1,6,7];
			 }
		 }
	 }
	 
		var jsObj ={ 
		              userTypeId : userTypeId,
					  activityMemberId:activityMemberId,
					  stateId : globalStateId,
					  dateStr : "01/01/2000",
					  enrollmentYearIdsList :enrollmentYrIds,
					  programIdArr:programIdArr
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
	
	/* Intermediate Table Implementations */
	/*var resultListFirst;
	var resultListSecond;
    var str='';
		str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
			str+='<b><span class="color_333 pad_5 bg_CC text-capital"><span class="text-danger">poor</span> training completed locations&nbsp&nbsp('+selectedUserName+" - "+userType+')</span></b>';
		str+='</div>';
	   str+='<div class="col-md-6 col-xs-12 col-sm-6 m_top10">';
	  if(userTypeId!= null && userTypeId==3){
		str+='<p class="text-capital">districts<span style="margin-left:200px">Attended Percentage</span></p>';  
		resultListFirst = result.districtList;
		resultListSecond = result.constituencyList;
	  }
	  if(userTypeId!= null && userTypeId==4 || userTypeId==5 || userTypeId==6 || userTypeId==7 || userTypeId==8 || userTypeId==9 || userTypeId==11){
		 str+='<p class="text-capital">Constituencies<span style="margin-left:140px">Attended Percentage</span></p>';  
		resultListFirst = result.constituencyList;
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
	  }else{
		  str+='No Data Available';
	  }	  
	  str+='</div>';
	  
	    if(resultListSecond == null || resultListSecond.length == 0){
		   $("#poorPerformancTrainingPrograLocationsDivId").html(str);	
	        $('.progressCustom').tooltip();	
		  return;
	  }
	  
	  str+='<div class="col-md-6 col-xs-12 col-sm-6 m_top10">';
	   if(userTypeId!= null && userTypeId==3){
		str+='<p class="text-capital">Constituencies<span style="margin-left:140px">Attended Percentage</span></p>';  
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
			}else{
			 str+='No Data Available';	
			}
	        str+='</div>';
																				
	 $("#poorPerformancTrainingPrograLocationsDivId").html(str);	
	 $('.progressCustom').tooltip(); */
	 
   var resultListFirst;
	var resultListSecond;
    var str='';
		str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
			str+='<b><span class="color_333 pad_5 bg_CC text-capital"><span class="text-danger">poor</span> training completed locations&nbsp&nbsp('+selectedUserName+" - "+userType+')</span></b>';
		str+='</div>';
	   str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
	   str+='<table class="table tableCumulative">';
	   str+='<tr>';
	   str+='<td></td>';
	  if(userTypeId!= null && userTypeId==3 || userTypeId==2){
		//str+='<p class="text-capital">districts<span style="display:inline-block">Attended Percentage</span><span style="display:inline-block">1 DAY</span><span style="display:inline-block">2 Days</span><span style="display:inline-block">3 Days</span></p>'; 
		str+='<td style="text-transform:uppercase">District</td>';
		resultListFirst = result.districtList;
		resultListSecond = result.constituencyList;
	  }
	  if(userTypeId!= null && userTypeId==5 || userTypeId==11 || userTypeId==4 || userTypeId==6 || userTypeId==12 || userTypeId==14){
		 //str+='<p class="text-capital">Constituencies<span style="margin-left:140px">Attended Percentage</span></p>';  
		 str+='<td style="text-transform:uppercase">Constituencies</td>';
		resultListFirst = result.constituencyList;
		resultListSecond = result.mandalList;  
	  }
	  if(userTypeId!= null && userTypeId==7 || userTypeId==8 || userTypeId==9){
		 //str+='<p class="text-capital">Mandal/Town/Division<span style="margin-left:100px">Attended Percentage</span></p>';  
		 str+='<td style="text-transform:uppercase">Mandal/Town/Division</td>';
		resultListFirst = result.mandalList;
		resultListSecond = result.villageList;  
	  }
	  str+='<td></td>';
	  str+='<td class="text-capital">Attended Percentage</td>';
	  str+='<td>1 DAY</td>';
	  str+='<td>2 DAYS</td>';
	  str+='<td>3 DAYS</td>';
	  str+='</tr>';
     
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
			for(var j in resultListFirst[i].locationList)
				str+='<td class="text-danger">'+resultListFirst[i].locationList[j].totalAttenedCountPer+'%</td>';
			str+='</tr>';
			order=order+1;
			if(order==6)
				break;
			BGColor = BGColor - 0.2;
			}
			
	  }else{
		  str+='No Data Available';
	  }	
	str+='</table>';	  
	  str+='</div>';
	  
	  str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
	  
	   str+='<table class="table tableCumulative">';
	   str+='<tr>';
	   str+='<td></td>';
	   if(userTypeId!= null && userTypeId==3 || userTypeId==2){
		//str+='<p class="text-capital">Constituencies<span style="margin-left:140px">Attended Percentage</span></p>'; 
		str+='<td style="text-transform:uppercase">Constituencies</td>';		
	  }
	  if(userTypeId!= null && userTypeId==5 || userTypeId==11 || userTypeId==4 || userTypeId==6 || userTypeId==12 || userTypeId==14){
		// str+='<p class="text-capital">Mandal/Town/Division<span style="margin-left:100px">Attended Percentage</span></p>'; 
		str+='<td style="text-transform:uppercase">Mandal/Town/Division</td>';			
	  }
	   if(userTypeId!= null && userTypeId==7 || userTypeId==8 || userTypeId==9){
		// str+='<p class="text-capital">Village/Ward<span style="margin-left:170px">Attended Percentage</span></p>'; 
			str+='<td style="text-transform:uppercase">Village/Ward</td>';	
	  }
	  
	  str+='<td></td>';
	  str+='<td class="text-capital">Attended Percentage</td>';
	  str+='<td>1 DAY</td>';
	  str+='<td>2 DAYS</td>';
	  str+='<td>3 DAYS</td>';
	  str+='</tr>';
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
			for(var j in resultListSecond[i].locationList)
				str+='<td class="text-danger">'+resultListSecond[i].locationList[j].totalAttenedCountPer+'%</td>';
			str+='</tr>';
			order=order+1;
			if(order==6)
				break;
			BGColor = BGColor - 0.2;
			}
			
			}else{
			 str+='No Data Available';	
			}
			str+='</table>';
	        str+='</div>';
																				
	 $("#poorPerformancTrainingPrograLocationsDivId").html(str);	
	 $('.progressCustom').tooltip(); 
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
$(document).on("click",".notesIconTraining",function(e){
	$(this).closest(".panel-heading").find(".notesDropDown").toggle();
	e.stopPropagation();
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
	$("#districtWiseProgramsCntDivId").html(" ");
	//setTimeout(function(){
		//stateLevelCampMembersDistWise();
		//getTrainingProgramPoorCompletedLocationDtls();
	//},600);
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
	$("#districtWiseProgramsCntDivId").html(" ");    
	getStateLevelCampCount(programIdArr);
	stateLevelCampMembersDistWise(programIdArr);
});
function getStateLevelCampCount(programIdArr){     
	$("#programsDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	//var programIdArr = [6]; 
	var dateStr = $("#dateRangeIdForTrainingCamp").val();
	var enrollmentYrIds = [];
	enrollmentYrIds.push($("#tdpTriningYearId").val());
	var progIds = [];
	if(programIdArr != "" && programIdArr.length > 0){
		progIds=programIdArr;
	}
	var jsObj={  
		programIdArr : progIds,
		stateId : globalStateId,
		dateStr : "01/01/2000",
		enrollmentYrIds:enrollmentYrIds,
		option : "dayWise"   
	} 
	$.ajax({
		type : 'GET',
		url : 'getStateLevelCampAttendedDetails.action',     
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)}
	}).done(function(result){ 
		$("#programsDivId").html(' ');
		if(result != null){    
			buildStateLevelCampDetails(result,programIdArr);
		}else{
			$("#programsDivId").html("NO DATA AVAILABLE");
		}
	});
}
function buildStateLevelCampDetails(result,programIdArr){ 
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
			var text = '';
			var datesArray = [];
			datesArray.push(result[i].dateStr);   
			if(programIdArr.length == 1){
				if(result[i].dateStr != null){
					text = "Day"+(parseInt(i)+1); 
				}  
			}else{
				text = "";  
			}
			var  jsonDataArrAttended=[]; 
			var  jsonDataArrYettotrain=[];
			var precent = parseFloat((result[i].actualCount*(100/result[i].count)).toFixed(2));  
			jsonDataArrAttended.push(parseFloat(precent));          
			var abs = parseFloat((100 - precent).toFixed(2));    
			jsonDataArrYettotrain.push(abs);      
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
					categories: datesArray,  
					labels: {
						enabled: false,
					}
				},
				yAxis: {
					min: 0,
					gridLineWidth: 0, 
					minorGridLineWidth: 0, 
					title: {
						text: text     
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
			if(programIdArr.length == 1 && result[i].dateStr != null){  
				$.each($('#programHighChartId'+i+'').find(".highcharts-axis").find("text"),function(index,item){
					$(this).attr("style","cursor:pointer;");       
					$(this).attr("class","memberDtlsDayWiseCls");  
					$(this).attr("attr_program_id",programIdArr); 
					$(this).attr("attr_date_id",result[i].dateStr);  
					$(this).attr("attr_state_id",globalStateId);  
				});       
			}
		}
	}
}
	$(document).on("click",".memberDtlsDayWiseCls",function(){  
		$("#memberId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		$("#positionId").html('');  
		$("#myModelId").modal('show');    
		var programIdArr = [];
		programIdArr.push($(this).attr("attr_program_id")); 
		var dateStr = $(this).attr("attr_date_id");  
		var stateId = $(this).attr("attr_state_id");
		var jsObj={  
			programIdArr : programIdArr,      
			stateId : globalStateId,
			dateStr : dateStr
		}
		$.ajax({
			type : 'GET',
			url : 'stateLevelCampDetailsDayWiseAction.action',    
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)}
		}).done(function(result){ 
			$("#memberId").html('');
			if(result != null && result.length > 0){
				buildStateLevelCampDetailsDayWise(result);
			}else{
				$("#memberId").html('Data Not Available');
			}  
		});
	});
	function buildStateLevelCampDetailsDayWise(result){
		var present = 0;
		var absent = 0;
		var total = 0;
		var str = '';
		var str2 = '';
		str+='<table class="table table-condensed" id="campMemberDtlsId">';
	str+='<thead>';
		str+='<th>NAME</th>';
		str+='<th>DESIGNATION</th>';
		str+='<th>CONTACT NUMBER</th>';
		str+='<th>STATUS</th>';
	str+='</thead>';
	str+='<tbody>';
	for(var i in result){
		total += result[i].length;
		for(var j in result[i]){ 
		if(result[i][j].wish == "attended"){  
			present++;  
		}
			str+='<tr>';
		str+='<td>'+result[i][j].name.toUpperCase()+'</td>'; 
		if(result[i][j].status==""){ 
			str+='<td>-</td>';  
		}else{    
			str+='<td>'+result[i][j].status.toUpperCase()+'</td>';   
		}  
		str+='<td>'+result[i][j].mobileNo+'</td>';
		if(result[i][j].wish == "absent"){
			str+='<td style="color:#F0AD4E;">'+result[i][j].wish+'</td>';   
		}else{
			str+='<td>'+result[i][j].wish+'</td>'; 
		}
		
		str+='</tr>';    
		}
	}
	str+='</tbody>';
	absent = total - present;
	str2+='<span class="label label-primary" style="margin-right: 5px;">All-'+total+'</span>'; 
	str2+='<span class="label label-default" style="margin-right: 5px;">Attended-'+present+'</span>'; 
	str2+='<span class="label label-warning" style="margin-right: 5px;">Absent-'+absent+'</span>'; 
	$("#positionId").html(str2);     
	$("#memberId").html(str); 
	$("#campMemberDtlsId").dataTable({
		"iDisplayLength": 50,
		"aLengthMenu": [[50, 100, 150, -1], [50, 100, 150, "All"]]
		});        
	}  
	function stateLevelCampMembersDistWise(programIdArr){
	$("#districtWiseProgramCntDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	$("#districtWiseProgramsCntDivId").html(" ");
	var enrollmentYrIds = [];
	 enrollmentYrIds.push($("#tdpTriningYearId").val());
	//var programIdArr = [6];
	var progIds = [];
	if(programIdArr != "" && programIdArr.length > 0){
		progIds=programIdArr;
	}	
	var dateStr = $("#dateRangeIdForTrainingCamp").val();
	var jsObj={  
		programIdArr : progIds,      
		stateId : globalStateId,
		enrollmentYrIds:enrollmentYrIds,
		dateStr : dateStr
	} 
		$.ajax({
			type : 'GET',
			url : 'stateLevelCampDetailsDistWise.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)}
		}).done(function(result){ 
			$("#districtWiseProgramCntDivId").html(" ");
			if(result != null && result.length > 0){
				if(programIdArr.length > 0 && programIdArr.length == 1){
					if(programIdArr[0] == 7)
						spreciluildStateLevelCampDetailsDistWise(result,programIdArr);
					else
						buildStateLevelCampDetailsDistWise(result);
				}
				else{
					buildStateLevelCampDetailsDistWise(result);
				}
			}else{
			$("#districtWiseProgramCntDivId").html("NO DATA AVAILABLE");	
			}
			
		});
}
function buildStateLevelCampDetailsDistWise(result){
	$("#districtWiseProgramCntDivId").html('');
		if(result != null && result.length > 0){
			var str='';
			for(var i in result){
				//str+=result[i].name
				str+='<div id="trainingLocationDivId'+i+'" class="chartLiD" style="height:300px" ></div>';
			}
		}   
		$("#districtWiseProgramCntDivId").html(str);  
		if(result != null && result.length > 0){
		for(var i in result){
			if(result != null){
			var districtIdArr=[];
			var districtNamesArray =[];
			var districtWiseAttendedPercArray = [];
			var districtWiseYetToTrainPercArray = []; 
			if(result[i] !=null && result[i].length > 0){
				//debugger; 
				for(var j in result[i]){
					districtNamesArray.push(result[i][j].name);
					districtIdArr.push(result[i][j].districtid);
					var precent = (result[i][j].actualCount*(100/result[i][j].count)).toFixed(2);
					districtWiseAttendedPercArray.push(parseFloat(precent));  
					var abs = 100-precent;
					districtWiseYetToTrainPercArray.push(parseFloat(abs.toFixed(2)));
				}  
			}
						$(function () {
							$('#trainingLocationDivId'+i).highcharts({  
								colors: ['#F56800','#53BF8B'],
								chart: {
									type: 'column'  
								},
								title: { 
									text: result[i][0].applicationStatus   
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
		//}districtIdArraaaaa
		$.each($('#trainingLocationDivId'+i).find(".highcharts-xaxis-labels").find("tspan"),function(index,item){   
			$(this).attr("style","cursor:pointer;");    
			//$(this).addClass("distDtlsCls");
			$(this).attr("class","distDtlsCls");    
			$(this).attr("state_Program_Id",result[i][0].applicationStatusId);         
			$(this).attr("attr_dist_id",districtIdArr[index]);      
			$(this).attr("attr_position_id","camp");
			$(this).attr("attr_area_name",districtNamesArray[index]);
			$(this).attr("date_str",0 );		  		
		});
	  }		
	}		
	}else{
		$("#districtWiseProgramCntDivId").html("No Data Available");
	}	
}
function spreciluildStateLevelCampDetailsDistWise(myFirstresult,programIdArr){

	$("#districtWiseProgramsCntDivId").html('');
		/*if(result != null && result.length > 0){
			var str='';
			for(var i in result){
				//str+=result[i].name
				str+='<div id="trainingLocationDivId'+i+'" class="chartLiD" style="height:300px" ></div>';
			}
		}   
		$("#districtWiseProgramCntDivId").html(str); */ 
		var myresult = myFirstresult[0];
		if(myresult != null && myresult.length > 0){
			for(var z in myresult){
				
		
				$("#districtWiseProgramsCntDivId").append(''+myresult[z].name+' <br><div id="trainingLocationDivId'+z+'" class="chartLiD" style="height:300px;margin-top:15px;" ></div><br>');
				var result = myresult[z].subList1;	
				
				var districtIdArr=[];
				var districtNamesArray =[];
				var districtWiseAttendedPercArray = [];
				var districtWiseYetToTrainPercArray = [];				
				var districtWiseTotalCountArray = [];				
				for(var i in result){   
					 
					if(result[i] !=null){
						//debugger; 
						//for(var j in result){
							districtNamesArray.push(result[i].name);
							districtIdArr.push(result[i].districtid);
							var precent = (result[i].actualCount*(100/result[i].count)).toFixed(2);
							if(parseInt(result[i].count) < parseInt(result[i].actualCount)){
								precent = 100.00
							}
							districtWiseAttendedPercArray.push(parseFloat(precent));  
							var abs = 100-precent;
							districtWiseYetToTrainPercArray.push(parseFloat(abs.toFixed(2)));
							districtWiseTotalCountArray.push(result[i].count);
						//}  
					}
				}					
								$(function () {
									$('#trainingLocationDivId'+z).highcharts({  
										colors: ['#F56800','#53BF8B'],
										chart: {
											type: 'column'  
										},
										title: { 
											text: myresult[z].applicationStatus   
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
				$.each($('#trainingLocationDivId'+z).find(".highcharts-xaxis-labels").find("tspan"),function(index,item){   
					$(this).attr("style","cursor:pointer;");    
					//$(this).addClass("distDtlsCls");
					$(this).attr("class","distDtlsCls");    
					$(this).attr("state_Program_Id",programIdArr);         
					$(this).attr("attr_dist_id",districtIdArr[index]);      
					$(this).attr("attr_position_id","camp");	
					$(this).attr("attr_area_name",districtNamesArray[index]);	
					$(this).attr("date_str",myresult[z].dateStr );					
						//$(this).attr("date_str",'04-10-2016');	
				}); 
			
		}				
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
	var areaName = $(this).attr("attr_area_name");
	var dateSrt = $(this).attr("date_str");
	if(position=="camp"){
		distId = $(this).attr("attr_dist_id");  
		programId = $(this).attr("state_Program_Id");
		stateId = globalStateId;
		//dateStr = $("#dateRangeIdForTrainingCamp").val();
		getCampMemberDtlsPerDist(distId,programId,stateId,dateSrt,areaName);
	}else{
		distId = $(this).attr("attr_dist_id");
		getLeaderShipMemDtlsPerDist(distId,dateSrt,areaName);
	}
	
 	
});
function getCampMemberDtlsPerDist(distId,programId,stateId,dateSrt,areaName){
	
	//var dateStr = $("#dateRangeIdForTrainingCamp").val();
	
	if(typeof dateSrt != "undefined")
		dateSrt =dateSrt;
	else
		dateSrt = $("#dateRangeIdForTrainingCamp").val();
	
	var jsObj ={ 
	    distId : distId,
		programId : programId,  
		stateId : stateId,
		dateStr : dateSrt 
	}
	$.ajax({
		type : 'POST',
		url : 'getCandidateDtlsPerDist.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#processingImgId").hide();    
			if(result != null && result.length > 0){
				buildMemberRslt(result,"camp",dateSrt,areaName);
				
			}else{
				$("#memberId").html('No Data Available');   
			}
		});	
}
function getLeaderShipMemDtlsPerDist(distId,dateSrt,areaName){
	
	var dateStr = $("#dateRangeIdForTrainingCamp").val();
	if(dateSrt == '0')
		dateStr = 0;
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
				buildMemberRslt(result,"leadership",dateStr,areaName); 
				
			}else{  
				$("#memberId").html('No Data Available');   
			}
		});	
}
function buildMemberRslt(result,status,dateStr,areaName){ 
	var str2 = '';
	var totalMember = result.length;  
	var attendedMember = 0;
	var absent = 0;
	
	var str = '';
	if(dateStr !=0){
	if(result[0].idnameList != null && result[0].idnameList.length>0){
		if(areaName.length>0)
			
		
		str+='<div  style="margin-bottom:15px;" > <table class="table table-condensed  table-bordered" id="dyaWiseStatusId">';
		str+='<thead>';
		str+='<th style="text-align:center;"> TOTAL INVITEES </th>';
		for(var k in result[0].idnameList)
			str+='<th style="text-align:center;">'+result[0].idnameList[k].name+' ATTENDED </th>';
		str+='</thead>';
		str+='<tbody>';
		str+='<td style="text-align:center;">'+result.length+'</td>';
		for(var k in result[0].idnameList)
			str+='<td style="text-align:center;">'+result[0].idnameList[k].count+'</td>';
		str+='</tbody>';
		str+='</table> </div> ';
	
	}
	}
	str+='<div  style="margin-top:5px;" > <table class="table table-condensed" id="campMemberDtlsId">';
	str+='<thead>';
		str+='<th>NAME</th>';
		str+='<th>DESIGNATION</th>';
		str+='<th>CONTACT NUMBER</th>'; 
		str+='<th>STATUS</th>';
	str+='</thead>';
	str+='<tbody>';
	if(dateStr !=0)
		str+='<div style="background-color:lightgrey;border-radius:5px;padding:5px;text-align:center;margin-bottom: 10px;"><span style="color:green;font-weight:bold;">'+areaName.toUpperCase()+'</span> DISTRICT ATTENDENCE DETAILS ON : '+dateStr+'  </div>';
	
	for(var i in result){
		if(result[i].wish=="attended"){
			attendedMember+=1;
		}
		str+='<tr>'; 
			
			if(result[i].wish == "absent"){
				str+='<td style="color:#F0AD4E">'+result[i].name.toUpperCase()+'</td>';
			}else{  
				str+='<td>'+result[i].name.toUpperCase()+'</td>';  
			}
			if(result[i].status==""){ 
				str+='<td>-</td>';
			}else{    
				str+='<td>'+result[i].status.toUpperCase()+'</td>';   
			}  
			str+='<td>'+result[i].mobileNo+'</td>'; 
			if(status=="camp"){
				if(result[i].wish == "absent"){
					str+='<td style="color:#F0AD4E">'+result[i].wish.toUpperCase()+'</td>';  
				}else{
					str+='<td>'+result[i].wish.toUpperCase()+'</td>';  
				}
				
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
	str2+='<span class="label label-primary" style="margin-right: 5px;">All-'+totalMember+'</span>'; 
	str2+='<span class="label label-default" style="margin-right: 5px;">Attended-'+attendedMember+'</span>'; 
	if(status=="camp"){
		str2+='<span class="label label-warning" style="margin-right: 5px;">Absent-'+absent+'</span>';  
	}else{
		str2+='<span class="label label-warning" style="margin-right: 5px;">Yet to train-'+absent+'</span>';    
	}
	 
	str+='</tbody>';
	str+='</table> </div> ';
	$("#positionId").html(str2);
	$("#memberId").html(str); 
	$("#campMemberDtlsId").dataTable();    
	
}
function stateLevelCampDetailsRepresentativeWise(programIdArr){
	$("#userTypeWiseTrainingProgramTopFiveStrongAndPoorMemsDivId").html('');  
	$("#userTypeWiseTrainingProgramTopFiveStrongAndPoorMemsDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');   
	var dateStr = $("#dateRangeIdForTrainingCamp").val();
	var progIds = [];
	if(programIdArr != "" && programIdArr.length > 0){
		progIds=programIdArr;
	}	
	var enrollmentYrIds = [];
	 enrollmentYrIds.push($("#tdpTriningYearId").val());
	var jsObj={  
	programIdArr : progIds,         
	stateId : globalStateId,
	dateStr : dateStr,
	enrollmentYrIds:enrollmentYrIds
	} 
	$.ajax({
		type : 'GET',
		url : 'stateLevelCampDetailsRepresentativeWise.action',  
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)} 
	}).done(function(result){ 
	
		if(result != null && result.length >0){
			buildstateLevelCampDetailsRepresentativeWise(result,programIdArr,dateStr);
		}  
	});
}
function buildstateLevelCampDetailsRepresentativeWise(result,programIdArr,dateStr){
	$(".hideCls").hide();   
	$("#userTypeWiseTrainingProgramTopFiveStrongAndPoorMemsDivId").html('');  
		var str='';
		if(result != null && result.length > 0){ 
			var k = 0;
			for(var i in result){  
				for(var j in result[i]){
					str+='<div class="col-md-12 col-xs-12 col-sm-12">';
					if(result[i][j].status == "State"){
						str+='<h5 class="text-capital">'+result[i][j].status+' committee</h5>'; 
					}
					else if(result[i][j].status == "District"){
						str+='<h5 class="text-capital">'+result[i][j].status+' committee</h5>'; 
					}else{
						str+='<h5 class="text-capital">'+result[i][j].status+'</h5>';
					}
					 
					str+='<div id="genCampId'+k+'" style="width:100%;height:100px;"></div>';
					str+='</div>'
					k+=1;  
				}    
			} 
			
		}
		$("#userTypeWiseTrainingProgramTopFiveStrongAndPoorMemsDivId").html(str);  
		if(result != null && result.length > 0){
				var candidateNameArray = [];
				candidateNameArray.push("ELIGIBLE");  
				candidateNameArray.push("INVITED");  
				candidateNameArray.push("ATTENDED");  
				candidateNameArray.push("ABSENT");
				k=0;
			for(var i in result){
				for(var j in result[i]){
					//var cnt = (result[i][j].count)-(result[i][j].actualCount);
					//alert(cnt);
					var trainingProgramCountArray = [];
					trainingProgramCountArray.push(100);
					trainingProgramCountArray.push(100);
					var present = (result[i][j].actualCount*(100/result[i][j].count)).toFixed(2);
					trainingProgramCountArray.push(parseFloat(present));
					var abs = 100-present;  
					trainingProgramCountArray.push(parseFloat(abs.toFixed(2)));            
					//console.log(trainingProgramCountArray);
					var getWidth = $("#genCampId"+k).parent().width()+'px';
					$("#genCampId"+k).width(getWidth);
					$(function () {
						$('#genCampId'+k).highcharts({  
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
										return this.value.toString().substring(0, 10);
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
								 
								pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y:.0f}%</b><br/>',
								shared: true,
								valueSuffix: '%'    
							},
						plotOptions: {
								column: {
									stacking: 'normal',
									dataLabels: {
										enabled: true,
										
									  
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
							name: 'Member',    
							data: trainingProgramCountArray
						}]
					});
				});
				//add dynamic id here...
				var len = programIdArr.length;
				if(len == 1){
					$.each($('#genCampId'+k+'').find(".highcharts-xaxis-labels").find("text"),function(index,item){
						if(result[i][j].count != result[i][j].actualCount || $(this).html() != "ABSENT"){  
							$(this).attr("style","cursor:pointer;");       
							$(this).attr("class","memberDtlsCls");
							$(this).attr("attr_program_id",programIdArr); 
							$(this).attr("attr_date_id",dateStr);  
							$(this).attr("attr_state_id",globalStateId);
							$(this).attr("attr_status",$(this).html());
							$(this).attr("attr_designation",result[i][j].applicationStatus);
							$(this).attr("attr_designation_id",result[i][j].id);   
						}
					}); 
				}
				k+=1;
			}
		}
	}else{
		$("#userTypeWiseTrainingProgramTopFiveStrongAndPoorMemsDivId").html('NO DATA AVAILABLE.');
	}
}
getTrainingRecentTime();
window.setInterval(function(){
  getTrainingRecentTime(); 
},10*60*1000);/*every 10 minutes .this method will update time  */
function getTrainingRecentTime(){
 	$.ajax({
		type : 'POST',
		url : 'getTrainingRecentTimeAction.action',
		dataType : 'json',
		data : {task:JSON.stringify( )}
	}).done(function(result){
		if(result != null){
		 setTrainingLastUpdateTime(result)	
		}
	});
}
  function setTrainingLastUpdateTime(lastUPdatedTime){
	  $("#lastUpdatedTimeTrainingCampId").html("Last Updated : "+lastUPdatedTime+"");
  }
  $(document).on("click",".memberDtlsCls",function(){ 
  $("#positionId").html(''); 
  $("#myModelId").modal('show');
  
  $("#memberId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var programIdArr = [];
		programIdArr.push($(this).attr("attr_program_id"));
		var dateStr = $(this).attr("attr_date_id");
		var stateId = $(this).attr("attr_state_id");
		var status = $(this).attr("attr_status");
		var designation = $(this).attr("attr_designation");
		var designationId = $(this).attr("attr_designation_id");
		var jsObj ={ 
			programIdArr : programIdArr,
			dateStr : dateStr,
			stateId : stateId,
			status : status,
			designation : designation,
			designationId : designationId                 
		}
		$.ajax({
			type : 'POST',
			url : 'getTrainingProgramMemberDtlsStatusWiseAction.action',     
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			 $("#memberId").html('');
			if(result != null && result.length > 0){
				buildTrainingProgramMemberDtlsStatusWise(result,status)	
			}else{
				$("#memberId").html('No Data Available');  
			}
		});
  });
  function buildTrainingProgramMemberDtlsStatusWise(result,status){
	
	var str = '';
	str+='<table class="table table-condensed table-bordered" id="campMemberDtlsId">';
	str+='<thead>';
		if(status == "ATTENDED"){
			str+='<tr>';
		}
			if(status == "ATTENDED"){
				str+='<th rowspan="2">NAME</th>';
			}else{
				str+='<th >NAME</th>';
			}
			if(status == "ATTENDED"){
				str+='<th rowspan="2">DESIGNATION</th>';
			}else{
				str+='<th >DESIGNATION</th>';
			}
			if(status == "ATTENDED"){
				str+='<th rowspan="2">CONTACT NUMBER</th>';
			}else{
				str+='<th>CONTACT NUMBER</th>';
			}
			if(status == "ATTENDED"){
					str+='<th colspan="'+result[0].count+'" class="text-capital text-center">ATTENDANCE</th>';
				str+='</tr>';
				str+='<tr>';
			}
			
			
		if(status == "ATTENDED"){
			for(var i = 1; i <= result[0].count; i++){    
				str+='<th>DAY '+i+'</th>';
			}
		}else{
			str+='<th>STATUS</th>';    
		}
		if(status == "ATTENDED"){
			str+='</tr>';
		}
		
	str+='</thead>';
	str+='<tbody>';
	for(var i in result){
		str+='<tr>';
		str+='<td>'+result[i].name.toUpperCase()+'</td>'; 
		if(result[i].status==""){ 
			str+='<td>-</td>';  
		}else{    
			str+='<td>'+result[i].status.toUpperCase()+'</td>';   
		}  
		str+='<td>'+result[i].mobileNo+'</td>'; 
		if(status == "ATTENDED"){
			for(var k in result[i].subList){
				if(result[i].subList[k] == "No"){
					str+='<td class="text-danger">'+result[i].subList[k]+'</td>'; 
				}else{
					str+='<td class="text-success">'+result[i].subList[k]+'</td>'; 
				}   
			}
		}else{
			str+='<td>'+status+'</td>';  
		}	
		str+='</tr>';      
	}
	str+='</tbody>'; 
	$("#memberId").html(str); 
	$("#campMemberDtlsId").dataTable({
		"iDisplayLength": 50,
		"aLengthMenu": [[50, 100, 150, -1], [50, 100, 150, "All"]]
		});        
  }
  
function getTrainingCampBasicDetailsCntOverviewTrainingCampCenterWise(){
		$("#campWiseTrainingId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	var jsObj={  
	userAccessLevelId :globalUserAccessLevelId,         
	userAccessLevelValues : globalUserAccessLevelValues,        
	stateId : globalStateId,
	fromDate : '01/01/2017',
	toDate : '30/12/2017',                              
	enrollmentYearIds : [2],                   
	programIds : [8,9]
	} 
	$.ajax({            
		type : 'POST',    
		url : 'getTrainingCampBasicDetailsCntOverviewTrainingCampCenterWiseAction.action',  
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)} 
	}).done(function(result){
		if(result != null && result.trainingProgramList!=null && result.trainingProgramList.length > 0){
			buildTrainingCampBatchCenterWiseDetails(result)
		}else{
			$("#campWiseTrainingId").html("No Data Available"); 
		}
	});
}

 function buildTrainingCampBatchCenterWiseDetails(result){ 
 	
  var str='';
  str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
  str+='<h4 class="text-capital"><span class="headingColor">Training Center Wise Analysis</span></h4>';
  str+='<div class="panel-group trainingCenterPanel m_top10" id="accordion" role="tablist" aria-multiselectable="true">';
  for(var i in result.trainingProgramList)
			{
				if(result.trainingProgramList[i].totalBath != null && result.trainingProgramList[i].totalBath> 0)
				{
					str+='<div class="panel panel-default">';
						str+='<div class="panel-heading" style="background: rgb(237, 238, 240);" role="tab" id="headingTrainingBatch'+i+'">';
						if(i == 0)  
							{
								str+='<a role="button" class="collapseDebatesIcon" data-toggle="collapse" data-parent="#accordion" href="#collapseTrainingBatch'+i+'" aria-controls="collapseTrainingBatch'+i+'">';
							}else{
								str+='<a role="button" class="collapsed collapseDebatesIcon" data-toggle="collapse" data-parent="#accordion" href="#collapseTrainingBatch'+i+'" aria-controls="collapseTrainingBatch'+i+'">';
							}
				str+='<h4 class="text-capital">'+result.trainingProgramList[i].name+'&nbsp;<br/><small>[Total Batchs :'+result.trainingProgramList[i].totalBath+']</small></h4>';
							str+='</a>';
						str+='</div>';
						if(i == 0)
						{
							str+='<div id="collapseTrainingBatch'+i+'" class="panel-collapse collapse in" aria-labelledby="headingTrainingBatch'+i+'" style="position:relative">';
						}else{
							str+='<div id="collapseTrainingBatch'+i+'" class="panel-collapse collapse" aria-labelledby="headingTrainingBatch'+i+'" style="position:relative">';
						}
							str+='<div class="panel-body bg_ED " style="margin-top:0px;">';  
		if($(window).width() < 300)
		{
			str+='<div class="table-responsive">';
		}
		str+='<table class="table tableTrainingBatch bg_ED table-condensed">';     
			str+='<tbody>';
				str+='<tr>';
					str+='<td>';
						str+='<p class="text-muted text-capitalize">Days</p>';
						str+='<p class="responsiveFont">-</p>';
						
					str+='</td>';
					str+='<td>';
					str+='<p class="text-muted text-capitalize">Total Attended</p>';
					str+='<p class="responsiveFont">'+result.trainingProgramList[i].totalAttenedCount
					str+='</td>';							
					str+='<td>';
						str+='<p class="text-muted text-capitalize" title="Invitee Attended">Invitee Attended</p>';
						var totalpercentage = ((parseInt(result.trainingProgramList[i].inviteeAttended)/parseInt(result.trainingProgramList[i].totalAttenedCount))*100).toFixed(2);
						str+='<p class="responsiveFont">'+result.trainingProgramList[i].inviteeAttended+'&nbsp;<span class="font-10 text-danger"> ('+totalpercentage+')%</span></p>';
			str+='</td>';
			str+='<td>';
						str+='<p class="text-muted text-capitalize" title="Non Invitee Attended">Non Invitee Attended</p>';
						str+='<p class="responsiveFont">'+result.trainingProgramList[i].nonInviteeAttended+'</p>';
			str+='</td>';
			str+='</tr>';
			for(var j in result.trainingProgramList[i].trainingProgramList)
			 {
	var percentage = ((parseInt(result.trainingProgramList[i].trainingProgramList[j].only1dayCountInvited)/parseInt(result.trainingProgramList[i].trainingProgramList[j].only1dayCount))*100).toFixed(2);
			  str+='<tr>';
				str+='<td>'+result.trainingProgramList[i].trainingProgramList[j].name+'</td>';
				str+='<td>'+result.trainingProgramList[i].trainingProgramList[j].only1dayCount+'</td>';
				str+='<td>'+result.trainingProgramList[i].trainingProgramList[j].only1dayCountInvited +'&nbsp;<span class="font-10 text-danger"> ('+percentage+')%</span></td>';
				str+='<td>'+result.trainingProgramList[i].trainingProgramList[j].only1dayCountNonInvited+'</td>';
				
			  str+='</tr>';
			 }
			str+='</tbody>';
		str+='</table>';  
		if($(window).width() < 300)
		 {
			 str+='</div>';
		 }
						str+='</div>';
						str+='</div>';
						str+='</div>';
	}
	}
 
   str+='</div>';
  str+='</div>';
 
  $("#campWiseTrainingId").html(str);        
 }
  
 function buildSpecialProgramLeaderIdWiseDetails(result){ 
     if(result.leaderTrainingList != null && result.leaderTrainingList.size > 0L){
	var str='';
		str+='<h4 class="text-capital"><span class="headingColor">Special Leader Programs</span></h4>';
		str+='<div class="panel-group trainingCenterPanel m_top10" id="accordion" role="tablist" aria-multiselectable="true">';
		for(var i in result.leaderTrainingList){
				str+='<div class="panel panel-default">';
					str+='<div class="panel-heading" style="background: rgb(237, 238, 240);" role="tab" id="headingSpecialTrainingBatch'+i+'">';
					if(i == 0){  
						str+='<a role="button" class="collapseDebatesIcon" data-toggle="collapse" data-parent="#accordion" href="#collapseSpecialTrainingBatch'+i+'" aria-controls="collapseSpecialTrainingBatch'+i+'">';
					}else{
						str+='<a role="button" class="collapsed collapseDebatesIcon" data-toggle="collapse" data-parent="#accordion" href="#collapseSpecialTrainingBatch'+i+'" aria-controls="collapseSpecialTrainingBatch'+i+'">';
					}
						str+='<h4 class="text-capital">'+result.leaderTrainingList[i].name+'';
							str+='<h5 style="margin-top:10px !important;" class="text_decoration_hover"><span>Total Batches : <b>'+result.leaderTrainingList[i].completedBatch+'</b></span> </h5>';
						str+='</h4>';
					str+='</a>';
				str+='</div>';
				if(i == 0)
				{
					str+='<div id="collapseSpecialTrainingBatch'+i+'" class="panel-collapse collapse in" aria-labelledby="headingSpecialTrainingBatch'+i+'" style="position:relative">';
				}else{
					str+='<div id="collapseSpecialTrainingBatch'+i+'" class="panel-collapse collapse" aria-labelledby="headingSpecialTrainingBatch'+i+'" style="position:relative">';
				}
			str+='<div class="panel-body bg_ED " style="margin-top:0px;">';  
			if($(window).width() < 300)
			{
				str+='<div class="table-responsive">';
			}
			str+='<table class="table tableTraining bg_ED table-condensed">';     
				str+='<tbody>';
					str+='<tr>';
						str+='<td>';
							str+='<p class="text-muted text-capitalize">Days</p>';
							str+='<p class="responsiveFont">ALL</p>';
							
						str+='</td>';
						str+='<td>';
						str+='<p class="text-muted text-capitalize">Total Attended</p>';
						str+='<p class="responsiveFont">'+result.leaderTrainingList[i].totalAttenedCount
						str+='</td>';							
						str+='<td>';
							str+='<p class="text-muted text-capitalize" title="Invitee Attended">Invitee Attended</p>';
							var totalpercentage = ((parseInt(result.leaderTrainingList[i].inviteeAttended)/parseInt(result.leaderTrainingList[i].totalAttenedCount))*100).toFixed(2);
							str+='<p class="responsiveFont">'+result.leaderTrainingList[i].inviteeAttended+'&nbsp;<span class="font-10 text-danger"> ('+totalpercentage+')%</span></p>';
				str+='</td>';
				str+='<td>';
							str+='<p class="text-muted text-capitalize" title="Non Invitee Attended">Non Invitee Attended</p>';
							str+='<p class="responsiveFont">'+result.leaderTrainingList[i].nonInviteeAttended+'</p>';
				str+='</td>';
				str+='</tr>';
				for(var j in result.leaderTrainingList[i].trainingProgramList)
				 {
					var percentage = ((parseInt(result.leaderTrainingList[i].trainingProgramList[j].only1dayCountInvited)/parseInt(result.leaderTrainingList[i].trainingProgramList[j].only1dayCount))*100).toFixed(2);
					str+='<tr>';
						str+='<td>'+result.leaderTrainingList[i].trainingProgramList[j].name+'</td>';
						str+='<td>'+result.leaderTrainingList[i].trainingProgramList[j].only1dayCount+'</td>';
						str+='<td>'+result.leaderTrainingList[i].trainingProgramList[j].only1dayCountInvited +'&nbsp;<span class="font-10 text-danger"> ('+percentage+')%</span></td>';
						str+='<td>'+result.leaderTrainingList[i].trainingProgramList[j].only1dayCountNonInvited+'</td>';
					
				  str+='</tr>';
				 }
				str+='</tbody>';
			str+='</table>';  
			if($(window).width() < 300)
			 {
				 str+='</div>';
			 }
			str+='</div>';
			str+='</div>';
			str+='</div>';
		
		}
	   str+='</div>';
	  str+='</div>';	
  $("#specialProgramLeaderId").html(str); 
 }  
} 