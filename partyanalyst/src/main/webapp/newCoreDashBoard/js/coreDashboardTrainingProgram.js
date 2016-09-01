//Training Program
var globalStateId=1; //default Ap 
$(document).on("click",".stateCls",function(){
 var stateId=$(this).attr("attr_state_id");
 globalStateId = stateId; 
});

var getDocumentWidth = $(document).width();

	function getTrainingCampBasicDetailsCntOverview()
	{
	 $("#programsDtlsCntTableId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	 $("#villageWardTblId").html(' ');
	 $("#mdlTwnDvsnTabId").html(' ');
		var jsObj ={ 
		             userAccessLevelId : globalUserAccessLevelId,
					 userAccessLevelValuesArray : globalUserAccessLevelValues,
					 stateId : globalStateId
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
	       str+='<div class="col-md-12 col-xs-12 col-sm-12 col-md-offset-0">';
			 str+='<span style="font-size:18px" class="text-capital bg_49 pad_custom" attr_program_id='+programList[i].id+'>'+programList[i].name+'</span>';
			str+='<table class="table tableTraining">';
				str+='<tr>';
					str+='<td>';
						str+='<h3>'+programList[i].totalEligibleCount+'</h3>';
						str+='<p class="text-muted text-capital">Total eligible</p>';
					str+='</td>';
					str+='<td>';
				 str+='<h3>'+programList[i].totalAttenedCount+'<span class="font-10 text-success">'+programList[i].totalAttenedCountPer+' %</span></h3>';
				   str+='<p class="text-muted text-capital">attended</p>';
					str+='</td>';
					str+='<td>';
				str+='<h3>'+programList[i].totalNotAttenedCount+'<span class="font-10 text-success">'+programList[i].totalNotAttenedCountPer+'%</span></h3>';
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
	str1='<h4 class="text-capitalize m_top20" style="color:#c9c0cc">village / ward</h4>';
	if(villageWardRslt != null){
	 str1+='<table class="table tableTraining bg_ED">';
		str1+='<tr>';
			str1+='<td>';
				str1+='<p class="text-muted text-capitalize">total eligible</p>';
				str1+='<h4>'+villageWardRslt.totalEligibleCount+'</h4>';
			str1+='</td>';
			str1+='<td>';
				str1+='<p class="text-muted text-capitalize">attended</p>';
				str1+='<h4>'+villageWardRslt.totalAttenedCount+'<span class="font-10 text-success">'+villageWardRslt.totalAttenedCountPer+'%</span></h4>'
			str1+='</td>';
			str1+='<td>';
				str1+='<p class="text-muted text-capitalize">yet to train</p>';
				str1+='<h4>'+villageWardRslt.totalNotAttenedCount+'<span class="font-10 text-success">'+villageWardRslt.totalNotAttenedCountPer+'%</span></h4>';
			str1+='</td>';
		str1+='</tr>';
	str1+='</table>';	
	}else{
	str1+='NO DATA AVAILABLE';		
	}
	$("#villageWardTblId").html(str1); 
	
	var mandalTwnDivRslt = result.mandalTownDivisionVO; 
    var str2='';
	 str2+='<h4 class="text-capitalize m_top20" style="color:#c9c0cc">mandal / town / division</h4>';
	if(mandalTwnDivRslt != null){
	  str2+='<table class="table tableTraining bg_ED">';
		str2+='<tr>';
			str2+='<td>';
				str2+='<p class="text-muted text-capitalize">total eligible</p>';
				str2+='<h4>'+mandalTwnDivRslt.totalEligibleCount+'</h4>';
			str2+='</td>';
			str2+='<td>';
				str2+='<p class="text-muted text-capitalize">attended</p>';
				str2+='<h4>'+mandalTwnDivRslt.totalAttenedCount+'<span class="font-10 text-success">'+mandalTwnDivRslt.totalAttenedCountPer+'%</span></h4>'
			str2+='</td>';
			str2+='<td>';
				str2+='<p class="text-muted text-capitalize">yet to train</p>';
				str2+='<h4>'+mandalTwnDivRslt.totalNotAttenedCount+'<span class="font-10 text-success">'+mandalTwnDivRslt.totalNotAttenedCountPer+'%</span></h4>';
			str2+='</td>';
		str2+='</tr>';
	 str2+='</table>';	
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
  var globalTrainingProgramDtlsRslt;
 function getTrainingCampProgramsDetailsCntByDistrict(){
		$("#districtWiseProgramCntDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var jsObj ={ 
		             userAccessLevelId : globalUserAccessLevelId,
					 userAccessLevelValuesArray : globalUserAccessLevelValues,
					 stateId : globalStateId
				  }
		
		$.ajax({
			type : 'POST',
			url : 'getTrainingCampProgramsDetailsCntByDistrictAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#districtWiseProgramCntDivId").html(" ");
			if(result != null && result.length > 0){
			  buildLocationWiseTrainingProgramDetails(result);	
			  globalTrainingProgramDtlsRslt=result;
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
			var districtNamesArray =[];
			var districtWiseAttendedPercArray = [];
			var districtWiseYetToTrainPercArray = [];
			if(result[i].districtList !=null && result[i].districtList.length > 0){
				for(var j in result[i].districtList){
						districtNamesArray.push(result[i].districtList[j].name);
						districtWiseAttendedPercArray.push(result[i].districtList[j].totalAttenedCountPer);
						districtWiseYetToTrainPercArray.push(result[i].districtList[j].totalNotAttenedCountPer);
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
		}
	}else{
		$("#districtWiseProgramCntDivId").html("No Data Available");
	}	
}
/*   function buildLocationWiseTrainingProgramDetails(result){
	
 var str='';	
 	  for(var i in result){
		  str+='<h4>'+result[i].name+'</h4>';
		   str+='<ul class="graphStructure" style="width:100px;">';
		  for(var j in result[i].districtList){
			str+='<li>';
	         str+='<span attr_percent="'+result[i].districtList[j].totalAttenedCountPer+'"></span>';
             str+='<span attr_percent="'+result[i].districtList[j].totalNotAttenedCountPer+'"></span>';
            str+='<p class="name">'+result[i].districtList[j].name+'</p>';
          str+='</li>'; 
		  }
		   str+='</ul>';
	  }
	  $("#districtWiseProgramCntDivId").html(str);
	   $(".graphStructure").KChart({
		  colors:['rgba(102,114,140,1)','rgba(245,104,0,1)','rgba(49,170,116,1)'],
		  borderLine:true,
		  labelText:true,
		  showPercentage:true,
		  percentColor:'#333',
		  widthBtnGraphs:'12',
		  chartHeight:'150px',
		  labelLegendShow:false,
		  graphHeading:false
		});
 }  */
/*  $(document).on("click",".trainingProgramCohortLiCls",function(){
	 var reportType=$(this).attr("attr_li_value");
	 if(reportType!= null && reportType=="all"){
	  buildLocationWiseTrainingProgramDetails(globalTrainingProgramDtlsRslt);
	 }else if(reportType!= null && reportType=="attended"){
     buildAttendedTrainingProgramRslt(globalTrainingProgramDtlsRslt);		 
	 }else if(reportType!= null && reportType=="notAttended"){
	 buildNotAttendedTrainingProgramRslt(globalTrainingProgramDtlsRslt);		  
	 }
 }); */
/*  function buildAttendedTrainingProgramRslt(result){
 var str='';	
 	  for(var i in result){
		  str+='<h4>'+result[i].name+'</h4>';
		   str+='<ul class="graphStructure" style="width:100px;">';
		  for(var j in result[i].districtList){
			str+='<li>';
	         str+='<span attr_percent="'+result[i].districtList[j].totalAttenedCountPer+'"></span>';
            str+='<p class="name">'+result[i].districtList[j].name+'</p>';
          str+='</li>'; 
		  }
		   str+='</ul>';
	  }
	  $("#districtWiseProgramCntDivId").html(str);
	   $(".graphStructure").KChart({
		  colors:['rgba(245,104,0,1)','rgba(102,114,140,1)','rgba(49,170,116,1)'],
		  borderLine:true,
		  widthBtnGraphs:'12',
		  labelText:true,
		  showPercentage:true,
		  percentColor:'#333',
		  chartHeight:'150px',
		  labelLegendShow:false,
		  graphHeading:false
		});
 }
  function buildNotAttendedTrainingProgramRslt(result){
 var str='';	
 	  for(var i in result){
		  str+='<h4>'+result[i].name+'</h4>';
		   str+='<ul class="graphStructure" style="width:100px;">';
		  for(var j in result[i].districtList){
			str+='<li>';
	        str+='<span attr_percent="'+result[i].districtList[j].totalNotAttenedCountPer+'"></span>';
            str+='<p class="name">'+result[i].districtList[j].name+'</p>';
          str+='</li>'; 
		  }
		   str+='</ul>';
	  }
	  $("#districtWiseProgramCntDivId").html(str);
	   $(".graphStructure").KChart({
		  colors:['rgba(102,114,140,1)','rgba(245,104,0,1)','rgba(49,170,116,1)'],
		  borderLine:true,
		  labelText:true,
		  showPercentage:true,
		  percentColor:'#333',
		  chartHeight:'150px',
		  widthBtnGraphs:'12',
		  labelLegendShow:false,
		  graphHeading:false
		});
 } */
var globalUserWiseMemberRslt;
 function getUserTypeWiseTotalEligibleAndAttendedCnt(){
	  $("#userTypeWiseTrainingProgramTopFiveStrongAndPoorMemsDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	 var jsObj ={
			          userAccessLevelId:globalUserAccessLevelId,
					  userAccessLevelValuesArray:globalUserAccessLevelValues,
					  activityMemberId : globalActivityMemberId,
					  userTypeId : globalUserTypeId,
					  stateId : globalStateId
					}
		
		$.ajax({
			type : 'POST',
			url : 'getUserTypeWiseTotalEligibleAndAttendedCntAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		$("#userTypeWiseTrainingProgramTopFiveStrongAndPoorMemsDivId").html(' ');
		  if(result != null && result.length > 0){
			  buildgetUserTypeWiseTrainingProgramAttendedCountTopFiveStrongResults(result);
			  globalUserWiseMemberRslt = result;
		  }else{
			  $("#userTypeWiseTrainingProgramTopFiveStrongAndPoorMemsDivId").html('NO DATA AVAILABLE.');
		  }
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
		$("#userTypeWiseTrainingProgramTopFiveStrongAndPoorMemsDivId").html(str);
		if(result != null && result.length > 0){
			for(var i in result){
				var length1 = result[i].length-1 ;
				var candidateNameArray = [];
				var trainingProgramCountArray = [];
				var countVar =0;
				for(var j = 0; j <= length1; j++){
					countVar =countVar+1;
					candidateNameArray.push(result[i][j].name);
					trainingProgramCountArray.push(result[i][j].totalAttenedCountPer);
					if (countVar === 5) {
						break;
					}
				}
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
					name: 'Attended',
					data: trainingProgramCountArray
				}]
			});
		});
			}
		}
	}
	function buildgetUserTypeWiseTrainingProgramAttendedCountTopFivePoorResults(result){
		var str='';
		if(result != null && result.length > 0){
			var str='';
			for(var i in result){
				str+='<div class="col-md-12 col-xs-12 col-sm-12">';
					str+='<h5 class="text-capital">'+result[i][0].userType+'</h5>';
					str+='<div id="genSecTraining'+i+'" style="width:100%;height:100px;"></div>';
				str+='</div>'
			}
		}
		$("#userTypeWiseTrainingProgramTopFiveStrongAndPoorMemsDivId").html(str);
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
					name: 'Attended',
					data: trainingProgramCountArray
				}]
			});
		});
		}
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
$(document).on("click",".trainingIconExpand",function(){

	$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
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
	}
	
	 setTimeout(function(){
		$(".trainingsHiddenBlock,.moreTrainingBlocksIcon").toggle();
	},800); 
});
$(document).on("click",".moreTrainingBlocksIcon",function(){
	$(this).addClass("unExpandTrainingBlock");
	$(".moreTrainingBlocks").toggle();
	setTimeout(function(){
		getTrainingCampProgramsDetailsCntByDistrict();
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
	getChildUserTypesByItsParentUserTypeForTrainingProgram();
});

$(document).on("click",".unExpandTrainingBlock",function(){
		$(this).removeClass("unExpandTrainingBlock");
		$(".moreTrainingBlocks").hide();
	});
  function getChildUserTypesByItsParentUserTypeForTrainingProgram(){
		 $("#childUserTypeDetailsDivForTrainingProgram").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var jsObj = {
			parentUserTypeId : globalUserTypeId,
		    stateId : globalStateId
		}
		$.ajax({
			type : 'POST',
			url : 'getChildUserTypesByItsParentUserTypeAction.action',
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
		 
		 var firstChildUserTypeId;
		 
		 if(result !=null && result.length >0){
			 firstChildUserTypeId = result[0].userTypeId;
			 for(var i in result){
				 str+='<li attr_userTypeId="'+result[i].userTypeId+'" class="childUserTypeClsForTrainingProgram">'+result[i].userType+'<span class="closeIconComparison"></span></li>';
			 }
		 }
		str+='</ul>';
		$("#childUserTypeDetailsDivForTrainingProgram").html(str);
		$(".comparisonSelect li:first-child").addClass("active")
		
		getSelectedChildTypeMembersForTrainingProgram(firstChildUserTypeId);
		//getTrainingProgramPoorCompletedLocationDtls();
	}
	function getSelectedChildTypeMembersForTrainingProgram(firstChildUserTypeId){
	 $("#childActivityMemberDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	 $("#userTypeWiseChildDtlsTabId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	  var parentActivityMemberId = globalActivityMemberId;
	  var childUserTypeId = firstChildUserTypeId;
	  var jsObj ={ 
	               parentActivityMemberId : parentActivityMemberId,
				   childUserTypeId : childUserTypeId,
				   userAccessLevelId : globalUserAccessLevelId,
				   userAccessLevelValuesArray : globalUserAccessLevelValues,
				   reportType :"selectedUserType",
				    stateId : globalStateId
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
			 str+='<h4 class="text-capital">'+result[i].userType+'</h4>';
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
		$(this).find(".panelSlick").addClass("panelActiveSlick")
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
	  var jsObj ={  activityMemberId : activityMemberId,
			         userTypeId : userTypeId,
					 reportType : "directChild",
					 stateId : globalStateId
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
			 str+='<table class="table table-condensed tableHoverLevelsInner m_top20">';
		 }else{
			str+='<table class="table table-condensed tableHoverLevels m_top20">';  
		 }
				str+='<thead class="bg_D8 text-capital">';
					str+='<th>% Rank</th>';
					str+='<th>Designation</th>';
					str+='<th>Name</th>';
					str+='<th>Total Eligible</th>';
					str+='<th>Attended</th>';
					str+='<th>%</th>';
					str+='<th>Not Attended</th>';
					str+='<th>%</th>';
				str+'=</thead>';
		str+='<tbody>';
		var rank=1;
		 for(var i in result){
		var yourValues = result[i].locationName;
		   str+='<tr class="lowLevelActivityMemberClsForTrainingProgram"  attr_activitymemberid = "'+result[i].activityMemberId+'" attr_usertypeid = "'+result[i].userTypeId+'" attr_selectedmembername = "'+result[i].name+'" attr_selectedusertype = "'+result[i].userType+'">';
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
			str+='<td>'+result[i].totalEligibleCount+'</td>';
			str+='<td>'+result[i].totalAttenedCount+'</td>';
			str+='<td>'+result[i].totalAttenedCountPer+'</td>';
			str+='<td>'+result[i].totalNotAttenedCount+'</td>';
			str+='<td>'+result[i].totalNotAttenedCountPer+'</td>';
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
 	  var jsObj ={ 
	               userAccessLevelId : globalUserAccessLevelId,
				   userAccessLevelValuesArray : globalUserAccessLevelValues,
				    stateId : globalStateId
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
		  for(var i in result){
			var  jsonDataArr=[];
			jsonDataArr.push({name:"Total Eligible",data:[result[i].totalEligibleCountPer,result[i].totalEligibleCountPer]});
			jsonDataArr.push({name:"Attended",data:[result[i].totalAttenedCountPer,0]});
			jsonDataArr.push({name:"Yet to train",data:[0,result[i].totalNotAttenedCountPer]});
			
		var chartWidth = $("#programHighChartId"+i).parent().width()/2;
		$("#programHighChartId"+i).width(chartWidth);
		$(function () {
		  $('#programHighChartId'+i).highcharts({
			colors: ['#66728C','#53BF8B','#F56800'],
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
			 series:jsonDataArr
		  });
		});  
	   }
	}
}
	 function getTrainingProgramPoorCompletedLocationDtls(userTypeId,activityMemberId,selectedUserName,userType){
	 $("#poorPerformancTrainingPrograLocationsDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var jsObj ={ 
		              userTypeId : userTypeId,
					  activityMemberId:activityMemberId,
					  stateId : globalStateId
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
		 str+='<p class="text-capital">Mandal</p>';  
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
			str+='<td class="text-danger">'+resultListFirst[i].totalAttenedCountPer+'</td>';
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
		 str+='<p class="text-capital">Mandal</p>';  
	  }
	   if(userTypeId!= null && userTypeId==7 || userTypeId==8 || userTypeId==9){
		 str+='<p class="text-capital">Village</p>';  
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
			str+='<td class="text-danger">'+resultListSecond[i].totalAttenedCountPer+'</td>';
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