var spinner = '<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>';
var globalUserWiseMemberTrainingCampInfoRslt;
var globalProgarmId=[];
$(".chosen-select").chosen();

function onloadTrainingCampCalls(){
	getProgramIdsAction();
}
function getProgramIdsAction(){
   var jsObj ={}
	 $.ajax({        
	  type : 'POST',
	  url : 'getProgramIdsAction.action',
	  dataType : 'json',
	  data : {task:JSON.stringify(jsObj)}
	 }).done(function(result){
		if(result !=null && result.length>0){
			for(var i in result){
				globalProgarmId.push(result[i].id)
			}
			
		}
		getCadreCommiteeEnrollmentIdsAction();
	 });
}
function getCadreCommiteeEnrollmentIdsAction(){
   var jsObj ={}
	 $.ajax({        
	  type : 'POST',
	  url : 'getCadreCommiteeEnrollmentIdsAction.action',
	  dataType : 'json',
	  data : {task:JSON.stringify(jsObj)}
	 }).done(function(result){
		if(result !=null && result.length>0){
			
			for(var i in result){
				if(i == 0){
					$("#tdpTriningCampInfoYearId").append("<option value="+result[i].id+" selected>"+result[i].name+"</option>")
				}else{
					$("#tdpTriningCampInfoYearId").append("<option value="+result[i].id+">"+result[i].electionYear+"</option>")
				}
			}
		}
		$("#tdpTriningCampInfoYearId").chosen();
		$("#tdpTriningCampInfoYearId").trigger("chosen:updated");
		onloadTrainingCampInfoOverAllCalls();
	 });
	 
}

function onloadTrainingCampInfoOverAllCalls(){
	getTrainingCampBasicDetailsCntOverviewDayWise();
	getTrainingCampFeedBackDetails();
	//getTrainingCampBasicDetailsCntOverviewTrainingCampCenterWiseForFeedbackBlock();
	if($(".trainingCampInfoIconExpand").find("i").hasClass("glyphicon glyphicon-resize-small" )){
		 getUserTypeWiseTrainingCampInfoDetails();
	}
	 if($(".moreTrainingCampInfoBlocksIcon").hasClass("expandBlockTrainingCampInfo")){
		 buildMoreBlocks();
	 }
}

$(document).on("click",".trainingCampInfoIconExpand",function(){
	if($(this).find("i").hasClass("glyphicon glyphicon-resize-small" )){
		getUserTypeWiseTrainingCampInfoDetails();
	}
		
});
$(document).on("click",".moreTrainingCampInfoBlocksIcon",function(){
		$(this).addClass("expandBlockTrainingCampInfo");
		$(".moreTrainingCampInfoBlocksDetailed").show(); 
		buildMoreBlocks();
		
});
$(document).on("click",".expandBlockTrainingCampInfo",function(){
		$(this).removeClass("expandBlockTrainingCampInfo");
		$(".moreTrainingCampInfoBlocksDetailed").hide();
});
$(document).on("change","#questionLevelId",function(){
	getFeedbackOnLeaders()
});

$(document).on("change","#trainingCampLevelId",function(){
	
	$("#mainLevelDivId").html($("#trainingCampLevelId option:selected").text());
	
	onloadTrainingCampInfoOverAllCalls();
});
$(document).on("change","#tdpTriningCampInfoYearId",function(){
	
	onloadTrainingCampInfoOverAllCalls();
});

function getTrainingCampBasicDetailsCntOverviewDayWise(){
	  var enrollmentYrIds=[];
	  var trainingCampLevelIds=[];
	  var traingCampEnrollmentYearId = $("#tdpTriningCampInfoYearId").val();
	  enrollmentYrIds.push(traingCampEnrollmentYearId)
	  
	  var trainingCampLevel = $("#trainingCampLevelId").val();
	  
		if(trainingCampLevel == "all"){
			trainingCampLevelIds=[];
			trainingCampLevelIds=[5,6,7,8,9]
		}else if(trainingCampLevel == "mandal"){
			trainingCampLevelIds=[];
			trainingCampLevelIds=[5,7,9]
		}else if(trainingCampLevel == "village"){
			trainingCampLevelIds=[];
			trainingCampLevelIds=[6,8]
		}
	
	 $("#overAllTrainingCampInfoDivId").html(spinner);
	 
	 var jsObj ={
	  globalActivityMemberId:globalActivityMemberId,
	  stateId:1,
	  fromDate:'',
	  toDate:'',
	  enrollmentYearIds:enrollmentYrIds,
	  programIds:globalProgarmId,
	  committeeLevelIds:trainingCampLevelIds
	 }
	  
	 $.ajax({
	  type : 'POST',
	  url : 'getTrainingCampBasicDetailsCntOverviewDayWiseAction.action',
	  dataType : 'json',
	  data : {task:JSON.stringify(jsObj)}
	 }).done(function(result){
		 if(result !=null && result.trainingProgramList !=null && result.trainingProgramList.length>0){
			 buildOverAllTrainingCampInfoCounts(result);
		 }else{
			 $("#overAllTrainingCampInfoDivId").html("No Data Available");
		 }
		 
	 });
 }
 
function buildOverAllTrainingCampInfoCounts(result){
	var str='';
		str+='<div class="block_color">';
		str+='<div class="table-responsive">';
			str+='<table class="table table_borderedCss">';
				str+='<tbody>';
				str+='<tr>';
				for(var i in result.trainingProgramList){
					
						str+='<td>';
							str+='<p class="text-capitalize responsiveFont font_weight">Total Eligible</p>';
							str+='<h4 class="m_top10 font_weight">'+result.trainingProgramList[i].totalEligibleCount+'</h4>';
						str+='</td>';
						str+='<td>';
							str+='<p class="text-capitalize responsiveFont font_weight">Total Attended</p>';
							str+='<h4 class="m_top10 font_weight">'+result.trainingProgramList[i].totalAttenedCount+' </h4>';
						str+='</td>';
						str+='<td>';
							str+='<p class="text-capitalize responsiveFont font_weight">Invitee Attended</p>';
							str+='<h4 class="m_top10 font_weight">'+result.trainingProgramList[i].inviteeAttended+'&nbsp;<span class="f_13 text-success"> '+result.trainingProgramList[i].totalAttenedCountPer+'%</span></h4>';
						str+='</td>';
						str+='<td>';
							str+='<p class="text-capitalize responsiveFont font_weight">Non Invitee Attended</p>';
							str+='<h4 class="m_top10 font_weight">'+result.trainingProgramList[i].nonInviteeAttended+' </h4>';
						str+='</td>';
						str+='<td>';
							str+='<p class="text-capitalize responsiveFont font_weight">Yet To Train </p>';
							str+='<h4 class="m_top10 font_weight">'+result.trainingProgramList[i].totalNotAttenedCount+'&nbsp;<span class="f_13 text-success"> ('+result.trainingProgramList[i].totalNotAttenedCountPer+')%</span></h4>';
						str+='</td>';
					
				}
				str+='</tr>';	
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
		str+='</div>';
	
	str+='<div class="block_color_low_level">';
		str+='<div class="table-responsive">';
			str+='<table class="table table_borderedCss">';
				str+='<tbody>';
				for(var i in result.trainingProgramList){
					for(var j in result.trainingProgramList[i].locationList){
						str+='<tr>';
							str+='<td>';
								str+='<p class="text-capitalize responsiveFont font_weight">'+result.trainingProgramList[i].locationList[j].name+'</p>';
							str+='</td>';
							str+='<td>';
								str+='<p class="text-capitalize responsiveFont font_weight">'+result.trainingProgramList[i].locationList[j].totalAttenedCount+'</p>';
							str+='</td>';
							str+='<td>';
								str+='<p class="text-capitalize responsiveFont font_weight">'+result.trainingProgramList[i].locationList[j].inviteeAttended+'<span class="f_13 text-success"> ('+result.trainingProgramList[i].locationList[j].totalAttenedCountPer+'%)</span></p>';
							str+='</td>';
							str+='<td>';
								str+='<p class="text-capitalize responsiveFont font_weight">'+result.trainingProgramList[i].locationList[j].nonInviteeAttended+'</p>';
							str+='</td>';
							str+='<td>';
								str+='<p class="text-capitalize responsiveFont font_weight">'+result.trainingProgramList[i].totalNotAttenedCount+'</p>';
							str+='</td>';
						str+='</tr>';
					}
				}
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
		str+='</div>';
	$("#overAllTrainingCampInfoDivId").html(str);
}

  function getTrainingCampFeedBackDetails(){
	  $("#overAllTrainingCampInfoFeedbackDivId").html(spinner);
	   var trainingCampLevelIds='';
	   var enrollmentYrIds=[];
	   var traingCampEnrollmentYearId = $("#tdpTriningCampInfoYearId").val();
	   var trainingCampLevel = $("#trainingCampLevelId").val();
	  
	  
	  enrollmentYrIds.push(traingCampEnrollmentYearId)
	  
		if(trainingCampLevel == "all"){
			trainingCampLevelIds='';
			trainingCampLevelIds="5,6,7,8,9"
		}else if(trainingCampLevel == "mandal"){
			trainingCampLevelIds='';
			trainingCampLevelIds="5,7,9"
		}else if(trainingCampLevel == "village"){
			trainingCampLevelIds='';
			trainingCampLevelIds="6,8"
		}
		
	  var jsObj ={
		activityMemberId : globalActivityMemberId,
		committeLevel:trainingCampLevelIds,
		stateId : 1,
		dateStr : '',
		enrollmentYrIds:enrollmentYrIds,
		programIdArr:globalProgarmId
	  }
		
	  $.ajax({
		type : 'POST',
		url : 'getTrainingCampFeedBackDetailsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	  }).done(function(result){
		  if(result !=null){
			  buildOverAllTrainingCampInfoFeedBackCounts(result);
		  }else{
			  $("#overAllTrainingCampInfoFeedbackDivId").html("No Data Available");
		  }
			
	  });
  }
  
function buildOverAllTrainingCampInfoFeedBackCounts(result){
	var str='';
	str+='<div class="table-responsive">';
			str+='<table class="table table-bordered">';
				str+='<tbody>';
					str+='<tr>';
						str+='<td>';
							str+='<p class="text-capitalize responsiveFont font_weight">Feedback on Program</p>';
							if(result.feedBackProgramCount !=null && result.feedBackProgramCount>0){
								str+='<h4 class="m_top10 font_weight text-success"><i class="fa fa-arrow-up" aria-hidden="true"></i> '+result.feedBackProgramCount+'</h4>';
							}else{
								str+='<h4 class="m_top10 font_weight text-success"> - </h4>';
							}
							
							str+='<p class="text-success">Given</p>';
						str+='</td>';
						str+='<td>';
							str+='<p class="text-capitalize responsiveFont font_weight">Feedback on Leader</p>';
							if(result.leaderFeedBackCount !=null && result.leaderFeedBackCount>0){
								str+='<h4 class="m_top10 font_weight text-success"><i class="fa fa-arrow-up" aria-hidden="true"></i> '+result.leaderFeedBackCount+'</h4>';
							}else{
								str+='<h4 class="m_top10 font_weight text-success"> - </h4>';
							}
							
							str+='<p class="text-success">Given</p>';
						str+='</td>';
						str+='<td>';
							str+='<p class="text-capitalize responsiveFont font_weight">Quiz Feedback</p>';
							if(result.feedBackQuizCount !=null && result.feedBackQuizCount>0){
								str+='<h4 class="m_top10 font_weight text-success"><i class="fa fa-arrow-up" aria-hidden="true"></i> '+result.feedBackQuizCount+'</h4>';
							}else{
								str+='<h4 class="m_top10 font_weight text-success"> - </h4>';
							}
							
							str+='<p class="text-success">Given</p>';
						str+='</td>';
					str+='</tr>';
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
		
		$("#overAllTrainingCampInfoFeedbackDivId").html(str);
}

function buildMoreBlocks(){
	var str='';
	str+='<div class="border_Training_Camp">';
		str+='<ul class="nav nav-tabs trainingCampLi" role="tablist">';
			//str+='<li role="presentation" ><a href="#feedBackOnProgram" aria-controls="feedBackOnProgram" role="tab" data-toggle="tab">Feedback on Program</a></li>';
			str+='<li role="presentation" class="active"><a href="#feedBackOnLeader" aria-controls="feedBackOnLeader" role="tab" data-toggle="tab">Feedback on Leader</a></li>';
			//str+='<li role="presentation"><a href="#quizFeedBack" aria-controls="quizFeedBack" role="tab" data-toggle="tab">Quiz Feed Back</a></li>';
		  str+='</ul>';
		  
		str+='<div class="tab-content">';
			//str+='<div role="tabpanel" class="tab-pane" id="feedBackOnProgram"></div>';
			str+='<div role="tabpanel" class="tab-pane active" id="feedBackOnLeader">';
				str+='<div class="row">';
					str+='<div class="m_top10">';
						str+='<div class="col-sm-4">';
							str+='<select class="form-control chosen-select" id="questionLevelId">';
								str+='<option value="1" selected>Center Wise Report</option>';
								str+='<option value="2">Distict Wise Report</option>';
								str+='<option value="3">Parliament Wise Report</option>';
								str+='<option value="4">Constituency Wise Report</option>';
							str+='</select>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
				str+='<div class="row">'
					str+='<div class="col-sm-12">';
						str+='<div id="feedBackSurveyDetailsDivId"></div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
			//str+='<div role="tabpanel" class="tab-pane" id="quizFeedBack"></div>';
		 str+=' </div>';
	str+='</div>';
	
	$("#trainingCampInfoDetailsDivId").html(str);
	$("#questionLevelId").chosen();
	$("#questionLevelId").trigger("chosen:updated");
	getFeedbackOnLeaders();
}
function getFeedbackOnLeaders(){
	
	
  var trainingCampLevelIds=[];
  var traingCampEnrollmentYearId = $("#tdpTriningCampInfoYearId").val();
  var groupType = $("#questionLevelId").val();
  var levelName = $("#questionLevelId option:selected").text();
  var trainingCampLevel = $("#trainingCampLevelId").val();
  
	if(trainingCampLevel == "all"){
		trainingCampLevelIds=[];
		trainingCampLevelIds=[5,6,7,8,9]
	}else if(trainingCampLevel == "mandal"){
		trainingCampLevelIds=[];
		trainingCampLevelIds=[5,7,9]
	}else if(trainingCampLevel == "village"){
		trainingCampLevelIds=[];
		trainingCampLevelIds=[6,8]
	}
  
  $("#feedBackSurveyDetailsDivId").html(spinner);
	 var jsObj ={
		  userAccessLevelId:globalUserAccessLevelId,
		  userAccessLevelValuesArray:globalUserAccessLevelValues,
		  programIdArr:globalProgarmId,
		  traingCampEnrollmentYearId:traingCampEnrollmentYearId,
		  trainingCampLevelIds:trainingCampLevelIds,
		  groupType:groupType,                                                               
		  stateId:1,      
		  dateStr:''
	 }
	  
	 $.ajax({        
	  type : 'POST',
	  url : 'getFeedbackOnLeadersAction.action',
	  dataType : 'json',
	  data : {task:JSON.stringify(jsObj)}
	 }).done(function(result){
		if(result !=null && result.length>0){
			buildFeedbackOnLeaders(result,levelName);
		}else{
			$("#feedBackSurveyDetailsDivId").html("No Data Available");
		}
	 });
}
function buildFeedbackOnLeaders(result,levelName){
	var collapse='';
	
	for(var i in result)
	{
		collapse+='<div class="panel-group" id="accordion'+result[i].id+'" role="tablist" aria-multiselectable="true">';
			collapse+='<div class="panel panel-default m_top10">';
				collapse+='<div class="panel-heading" role="tab" id="heading'+result[i].id+'" style="background-color:#f1f1f1;">';
					if(i == 0)
					{
						collapse+='<a role="button" class="collapseDebatesIcon '+result[i].id+'"  data-toggle="collapse" data-parent="#accordion'+result[i].id+'" href="#collapse'+result[i].id+'" aria-expanded="true" level_name="'+result[i].name+'" aria-controls="collapse'+result[i].id+'">';
					}else{
						collapse+='<a role="button" class="collapseDebatesIcon collapsed '+result[i].id+'"  data-toggle="collapse" data-parent="#accordion'+result[i].id+'" href="#collapse'+result[i].id+'" level_name="'+result[i].name+'" aria-expanded="true" aria-controls="collapse'+result[i].id+'">';
					}
					collapse+='<h4 class="panel-title text-capital"><span class="qCss">Q</span> '+result[i].name+'</h4>';
						
					collapse+='</a>';
				collapse+='</div>';
				if(i == 0)
				{
					collapse+='<div id="collapse'+result[i].id+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+result[i].id+'">';
				}else{
					collapse+='<div id="collapse'+result[i].id+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+result[i].id+'">';
				}
				
					collapse+='<div class="panel-body">';
						collapse+='<div class="row">';
						collapse+='<div class="col-sm-12">';
							collapse+='<div class="table-responsive">';
								collapse+='<table class="table" id="dataTableFeedBackSurvey'+result[i].id+'">';
									collapse+='<thead>';
										collapse+='<tr>';
											collapse+='<th>'+levelName+'</th>';
											collapse+='<th>Total Trained</th>';
											collapse+='<th>Excellent</th>';
											collapse+='<th>Very Good</th>';
											collapse+='<th>Good</th>';
											collapse+='<th>Ok</th>';
											collapse+='<th>Poor</th>';
										collapse+='</tr>';
									collapse+='</thead>';
									collapse+='<tbody>';
										for(var j in result[i].programsList){
											var totalCount=result[i].programsList[j].excellent+result[i].programsList[j].veryGood+result[i].programsList[j].good+result[i].programsList[j].average+result[i].programsList[j].poor
											collapse+='<tr>';
												collapse+='<td>'+result[i].programsList[j].name+'</td>';
												collapse+='<td>'+totalCount+'</td>';
												collapse+='<td>'+result[i].programsList[j].excellent+'</td>';
												collapse+='<td>'+result[i].programsList[j].veryGood+'</td>';
												collapse+='<td>'+result[i].programsList[j].good+'</td>';
												collapse+='<td>'+result[i].programsList[j].average+'</td>';
												collapse+='<td>'+result[i].programsList[j].poor+'</td>';
											collapse+='</tr>';
										}
									collapse+='</tbody>';
								collapse+='</table>';
							collapse+='</div>';
						collapse+='</div>';
					collapse+='</div>';
					collapse+='</div>';
				collapse+='</div>';
			collapse+='</div>';
		collapse+='</div>';
		collapse+='</div>';
	}
	$("#feedBackSurveyDetailsDivId").html(collapse);
	for(var i in result){
		$("#dataTableFeedBackSurvey"+result[i].id).dataTable({
			"iDisplayLength": 13,
			"aaSorting": [],
			"aLengthMenu": [[13, 15, 20, -1], [13, 15, 20, "All"]]
			
		});
	}
	
	
}

function getUserTypeWiseTrainingCampInfoDetails(){
	  var enrollmentYrIds=[];
	  var traingCampEnrollmentYearId = $("#tdpTriningCampInfoYearId").val();
	  enrollmentYrIds.push(traingCampEnrollmentYearId)
	  
	  $("#userTypeWiseTrainingCampInfoDiv").html(spinner);
		//var trainingCampLevelIds=[];
		/* var trainingCampLevel = $("#trainingCampLevelId").val();
		if(trainingCampLevel == "all"){
			trainingCampLevelIds=[];
			trainingCampLevelIds=[5,6,7,8,9]
		}else if(trainingCampLevel == "mandal"){
			trainingCampLevelIds=[];
			trainingCampLevelIds=[5,7,9]
		}else if(trainingCampLevel == "village"){
			trainingCampLevelIds=[];
			trainingCampLevelIds=[6,8]
		} */
		
	 var jsObj ={
		  userAccessLevelId:globalUserAccessLevelId,
		  userAccessLevelValuesArray:globalUserAccessLevelValues,
		  activityMemberId : globalActivityMemberId,
		  userTypeId :globalUserTypeId,
		  stateId : 1,
		  dateStr : '',
		  enrollmentYrIds:enrollmentYrIds,
		  programIdArr:globalProgarmId
	 }
  
  $.ajax({
	   type : 'POST',
	   url : 'getUserTypeWiseTotalEligibleAndAttendedCntActionForTrainingCampAction.action',
	   dataType : 'json',
	   data : {task:JSON.stringify(jsObj)}
	  }).done(function(result){
			buildgetUserTypeWiseTrainingCampTopFiveStrong(result);
			globalUserWiseMemberTrainingCampInfoRslt = result;	
	  });
 }
 
$(document).on("click",".trainingCampInfocls li",function(){
	var memberType=$(this).attr("attr_value");
		buildgetUserTypeWiseTrainingCampTopFiveStrong(globalUserWiseMemberTrainingCampInfoRslt); 
	 if(memberType != null && memberType == "strong"){
	 }else if(memberType == "poor"){
		buildgetUserTypeWiseTrainingCampTopFivePoor(globalUserWiseMemberTrainingCampInfoRslt)
	 }
});
function buildgetUserTypeWiseTrainingCampTopFiveStrong(result){
	$("#userTypeWiseTrainingCampInfoDiv").html('');
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
			  str+='<div id="genSecTrainingCampInfo'+i+'" style="height:100px;"></div>';
			str+='</div>'
		  }
		}
		$("#userTypeWiseTrainingCampInfoDiv").html(str);
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
				$('#genSecTrainingCampInfo'+i).highcharts({
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
			}else{
				$("#genSecTrainingCampInfo"+i).css("height","35px");
			} 
		}
		}else{
			$("#userTypeWiseTrainingCampInfoDiv").html('NO DATA AVAILABLE.');
	}	
		
}
	
	function buildgetUserTypeWiseTrainingCampTopFivePoor(result){
		$("#userTypeWiseKaizalaDiv").html('');
		
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
				str+='<div id="genSecTrainingCamp1'+i+'" style="width:100%;height:100px;"></div>';
				str+='</div>'
			}
		}
		$("#userTypeWiseTrainingCampInfoDiv").html(str);
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
					   $('#genSecTrainingCamp1'+i).highcharts({
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
			$("#userTypeWiseTrainingCampInfoDiv").html('NO DATA AVAILABLE.');
		}
	}

	
	function getTrainingCampBasicDetailsCntOverviewTrainingCampCenterWiseForFeedbackBlock(){
		
	  var trainingCampLevelIds='';
	  var enrollmentYrIds=[];
	  var trainingCampLevelIds=[];
	  var traingCampEnrollmentYearId = $("#tdpTriningCampInfoYearId").val();
	  enrollmentYrIds.push(traingCampEnrollmentYearId)
	  var trainingCampLevel = $("#trainingCampLevelId").val();
	  
		if(trainingCampLevel == "all"){
			trainingCampLevelIds='';
			trainingCampLevelIds="5,6,7,8,9"
		}else if(trainingCampLevel == "mandal"){
			trainingCampLevelIds='';
			trainingCampLevelIds="5,7,9"
		}else if(trainingCampLevel == "village"){
			trainingCampLevelIds='';
			trainingCampLevelIds="6,8"
		}
	  
	  
	 var jsObj ={
		 userAccessLevelId:2,
		 userAccessLevelValues:[1],
		 activityMemberId : 44,
		 stateId : 1,
		 fromDate : '',
		 toDate:'',
		 enrollmentYearIds:enrollmentYrIds,
		 programIdArr:globalProgarmId,
		 committeLevel:trainingCampLevelIds
	 }
	 
	 $.ajax({
		 type : 'POST',
		 url : 'getTrainingCampBasicDetailsCntOverviewTrainingCampCenterWiseForFeedbackBlockAction.action',
		 dataType : 'json',
		 data : {task:JSON.stringify(jsObj)}
		 }).done(function(result){
			 buildTrainingProgramBasicDetailsInfoD(result)
		 });     
	 }
	 
	  function buildTrainingProgramBasicDetailsInfoD(result){
	  var str='';
	  str+='<h4 class="text-capital"><span class="headingColor">Leadership Skills</span></h4>';
		str+='<div class="panel-group leaderSkill m_top10" id="accordion" role="tablist" aria-multiselectable="true">';
		
	  var stringArrType =['Over-All Levels','village / ward Level','mandal / town / division Level'];
	  if(result.trainingProgramList != null && result.trainingProgramList.length > 0){
		  for(var i in stringArrType){
        
		        str+='<div class="panel panel-default">';
					str+='<div class="panel-heading" style="background: rgb(237, 238, 240);" role="tab" id="headingLeaderSkill'+i+'">';
					if(i == 0){  
						str+='<a role="button" class="collapsed collapseTraingIcon" data-toggle="collapse" data-parent="#accordion" href="#collapseLeaderSkill'+i+'" aria-controls="collapseLeaderSkill'+i+'">';
					}else{
						str+='<a role="button" class="collapsed collapseTraingIcon" data-toggle="collapse" data-parent="#accordion" href="#collapseLeaderSkill'+i+'" aria-controls="collapseLeaderSkill'+i+'">';
					}
					   str+='<h4 class="text-capital f_14 text_bold">'+stringArrType[i]+'';
				   if(stringArrType[i] == "Over-All Levels"){
					   str+='<h5 style="margin-top:10px !important;" class="text_decoration_hover f_12"> <span data-toggle="tooltip" data-placement="top" title="Total Eligible">[T.E: <b>'+result.trainingProgramList[0].totalEligibleCount+']</span> &nbsp&nbsp&nbsp<span data-toggle="tooltip" data-placement="top" title="Total Attended">[T.A:<b>'+result.trainingProgramList[0].totalAttenedCount+']</b></span>&nbsp&nbsp&nbsp<span data-toggle="tooltip" data-placement="top" title="Invitee Attended">[I.A:<b>'+result.trainingProgramList[0].inviteeAttended+']</b></span>&nbsp&nbsp&nbsp<span data-toggle="tooltip" data-placement="top" title="Non Invitee Attended">[N.I.A:<b>'+result.trainingProgramList[0].nonInviteeAttended+']</span>&nbsp&nbsp&nbsp<span data-toggle="tooltip" data-placement="top" title="Yet To Train">[Y.T.T:<b>'+result.trainingProgramList[0].totalNotAttenedCount+'] </span> </h5>'
					}
				   if(stringArrType[i] == "village / ward Level"){
					   str+='<h5 style="margin-top:10px !important;" class="text_decoration_hover f_12"> <span data-toggle="tooltip" data-placement="top" title="Total Eligible">[T.E: <b>'+result.villageWardVO.totalEligibleCount+']</span> &nbsp&nbsp&nbsp<span data-toggle="tooltip" data-placement="top" title="Total Attended">[T.A:<b>'+result.villageWardVO.totalAttenedCount+']</b></span>&nbsp&nbsp&nbsp<span data-toggle="tooltip" data-placement="top" title="Invitee Attended">[I.A:<b>'+result.villageWardVO.inviteeAttended+']</b></span>&nbsp&nbsp&nbsp<span data-toggle="tooltip" data-placement="top" title="Non Invitee Attended">[N.I.A:<b>'+result.villageWardVO.nonInviteeAttended+']</span>&nbsp&nbsp&nbsp<span data-toggle="tooltip" data-placement="top" title="Yet To Train">[Y.T.T:<b>'+result.villageWardVO.totalNotAttenedCount+'] </span> </h5>'
				   }
				   if(stringArrType[i] == "mandal / town / division Level"){
					  str+='<h5 style="margin-top:10px !important;" class="text_decoration_hover f_12"> <span data-toggle="tooltip" data-placement="top" title="Total Eligible">[T.E: <b>'+result.mandalTownDivisionVO.totalEligibleCount+']</span> &nbsp&nbsp&nbsp<span data-toggle="tooltip" data-placement="top" title="Total Attended">[T.A:<b>'+result.mandalTownDivisionVO.totalAttenedCount+']</b></span>&nbsp&nbsp&nbsp<span data-toggle="tooltip" data-placement="top" title="Invitee Attended">[I.A:<b>'+result.mandalTownDivisionVO.inviteeAttended+']</b></span>&nbsp&nbsp&nbsp<span data-toggle="tooltip" data-placement="top" title="Non Invitee Attended">[N.I.A:<b>'+result.mandalTownDivisionVO.nonInviteeAttended+']</span>&nbsp&nbsp&nbsp<span data-toggle="tooltip" data-placement="top" title="Yet To Train">[Y.T.T:<b>'+result.mandalTownDivisionVO.totalNotAttenedCount+'] </span> </h5>'
				   }
						str+='</h4>';
						str+='</a>';
					    str+='</div>';
					if(i == 0)
					{
					str+='<div id="collapseLeaderSkill'+i+'" class="panel-collapse collapse" aria-labelledby="headingLeaderSkill'+i+'" style="position:relative">';
					}else{
					str+='<div id="collapseLeaderSkill'+i+'" class="panel-collapse collapse" aria-labelledby="headingLeaderSkill'+i+'" style="position:relative">';
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
							str+='<p class="text-muted text-capitalize">Total eligible</p>';
							
						str+='</td>';
						str+='<td>';
						str+='<p class="text-muted text-capitalize">Total Attended </p>';
						str+='</td>';							
						str+='<td>';
							str+='<p class="text-muted text-capitalize" title="Invitee Attended">Invitee Attended</p>';
				str+='</td>';
				str+='<td>';
							str+='<p class="text-muted text-capitalize" title="Non Invitee Attended">Non Invitee Attended </p>';
				str+='</td>';
				str+='<td>';
							str+='<p class="text-muted text-capitalize" title="Yet TO Train">Yet To Train </p>';
				str+='</td>';
				str+='</tr>';
				if(stringArrType[i] == "Over-All Levels"){
				     str+='<tr>';
						str+='<td>'+result.trainingProgramList[0].totalEligibleCount+'</td>';
						str+='<td>'+result.trainingProgramList[0].totalAttenedCount+'</td>';
						str+='<td>'+result.trainingProgramList[0].inviteeAttended +'&nbsp;<span class="font-10 text-danger"> ('+result.trainingProgramList[0].totalAttenedCountPer+')%</span></td>';
						str+='<td>'+result.trainingProgramList[0].nonInviteeAttended+'</td>';
						str+='<td>'+result.trainingProgramList[0].totalNotAttenedCount+'&nbsp;<span class="font-10 text-danger"> ('+result.trainingProgramList[0].totalNotAttenedCountPer+')%</span></td>';
				    str+='</tr>';
					 str+='<tr>';
						for(var j in result.trainingProgramList[0].locationList)
						 {
						  str+='<tr>';
							str+='<td>'+result.trainingProgramList[0].locationList[j].name+'</td>';
							str+='<td>'+result.trainingProgramList[0].locationList[j].totalAttenedCount+'</td>';
							str+='<td>'+result.trainingProgramList[0].locationList[j].inviteeAttended+'<span class="font-10 text-danger"> ('+result.trainingProgramList[0].locationList[j].totalAttenedCountPer+'%)</span></td>';
							str+='<td>'+result.trainingProgramList[0].locationList[j].nonInviteeAttended+'</td>';
							str+='<td>'+result.trainingProgramList[0].totalNotAttenedCount+'</td>';
						  str+='</tr>';
						 }
				    str+='</tr>';
				}
				if(stringArrType[i] == "village / ward Level"){
				 str+='<tr>';
						str+='<td>'+result.villageWardVO.totalEligibleCount+'</td>';
						str+='<td>'+result.villageWardVO.totalAttenedCount+'</td>';
						str+='<td>'+result.villageWardVO.inviteeAttended +'<span class="font-10 text-danger"> ('+result.trainingProgramList[0].totalAttenedCountPer+')%</span></td>';
						str+='<td>'+result.villageWardVO.nonInviteeAttended+'</td>';
						str+='<td>'+result.villageWardVO.totalNotAttenedCount+'&nbsp;<span class="font-10 text-danger"> ('+result.villageWardVO.totalNotAttenedCountPer+')%</span></td>';
				    str+='</tr>';
					 str+='<tr>';
						for(var j in result.trainingProgramList[0].locationList)
						 {
						  str+='<tr>';
							str+='<td>'+result.villageWardVO.locationList[j].name+'</td>';
							str+='<td>'+result.villageWardVO.locationList[j].totalAttenedCount+'</td>';
							str+='<td>'+result.villageWardVO.locationList[j].inviteeAttended+'<span class="font-10 text-danger"> ('+result.villageWardVO.locationList[j].totalAttenedCountPer+'%)</span></td>';
							str+='<td>'+result.villageWardVO.locationList[j].nonInviteeAttended+'</td>';
							str+='<td>'+result.villageWardVO.totalNotAttenedCount+'</td>';
						  str+='</tr>';
						 }
				    str+='</tr>';
				}
				if(stringArrType[i] == "mandal / town / division Level"){
					 str+='<tr>';
						str+='<td>'+result.mandalTownDivisionVO.totalEligibleCount+'</td>';
						str+='<td>'+result.mandalTownDivisionVO.totalAttenedCount+'</td>';
						str+='<td>'+result.mandalTownDivisionVO.inviteeAttended +'&nbsp;<span class="font-10 text-danger"> ('+result.trainingProgramList[0].totalAttenedCountPer+')%</span></td>';
						str+='<td>'+result.mandalTownDivisionVO.nonInviteeAttended+'</td>';
						str+='<td>'+result.mandalTownDivisionVO.totalNotAttenedCount+'&nbsp;<span class="font-10 text-danger"> ('+result.mandalTownDivisionVO.totalNotAttenedCountPer+')%</span></td>';
				    str+='</tr>';
					 str+='<tr>';
						for(var j in result.trainingProgramList[0].locationList)
						 {
						  str+='<tr>';
							str+='<td>'+result.mandalTownDivisionVO.locationList[j].name+'</td>';
							str+='<td>'+result.mandalTownDivisionVO.locationList[j].totalAttenedCount+'</td>';
							str+='<td>'+result.mandalTownDivisionVO.locationList[j].inviteeAttended+'<span class="font-10 text-danger"> ('+result.mandalTownDivisionVO.locationList[j].totalAttenedCountPer+'%)</span></td>';
							str+='<td>'+result.mandalTownDivisionVO.locationList[j].nonInviteeAttended+'</td>';
							str+='<td>'+result.mandalTownDivisionVO.totalNotAttenedCount+'</td>';
						  str+='</tr>';
						 }
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
	  $("#programsTrainingCampInfoDtlsCntTableId").html(str);
  }