//Training Program
var globalTrainingProgramsRslt;
var getDocumentWidth = $(document).width();

	function getTrainingCampBasicDetailsCntOverview()
	{
	 $("#programsDtlsCntTableId").html('<div ><center ><img  src="images/icons/loading.gif"></center></div>');
	 $("#villageWardTblId").html(' ');
	 $("#mdlTwnDvsnTabId").html(' ');
		var jsObj ={ 
		             userAccessLevelId : globalUserAccessLevelId,
					 userAccessLevelValuesArray : globalUserAccessLevelValues
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
	  globalTrainingProgramsRslt=result.trainingProgramList;
	if(programList != null && programList.length > 0){
		  for(var i in programList){
	       str+='<div class="col-md-12 col-xs-12 col-sm-10 col-sm-offset-1 col-md-offset-0">';
			 str+='<h4 class="text-capital" attr_program_id='+programList[i].id+'>'+programList[i].name+'</h4>';
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
	str1='<h4 class="text-capitalize m_top20">village / ward</h4>';
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
	 str2+='<h4 class="text-capitalize m_top20">mandal / town / division</h4>';
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
		$("#districtWiseProgramCntDivId").html('<div ><center ><img  src="images/icons/loading.gif"></center></div>');
		var jsObj ={ 
		             userAccessLevelId : globalUserAccessLevelId,
					 userAccessLevelValuesArray : globalUserAccessLevelValues
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
		  chartHeight:'150px',
		  labelLegendShow:false,
		  graphHeading:false
		});
 }
 $(document).on("click",".trainingProgramCohortLiCls",function(){
	 var reportType=$(this).attr("attr_li_value");
	 if(reportType!= null && reportType=="all"){
	  buildLocationWiseTrainingProgramDetails(globalTrainingProgramDtlsRslt);
	 }else if(reportType!= null && reportType=="attended"){
     buildAttendedTrainingProgramRslt(globalTrainingProgramDtlsRslt);		 
	 }else if(reportType!= null && reportType=="notAttended"){
	 buildNotAttendedTrainingProgramRslt(globalTrainingProgramDtlsRslt);		  
	 }
 });
 function buildAttendedTrainingProgramRslt(result){
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
		  colors:['rgba(102,114,140,1)','rgba(245,104,0,1)','rgba(49,170,116,1)'],
		  borderLine:true,
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
		  labelLegendShow:false,
		  graphHeading:false
		});
 }
var globalUserWiseMemberRslt;
 function getUserTypeWiseTotalEligibleAndAttendedCnt(){
	  $("#userTypeWiseTrainingProgramTopFiveStrongAndPoorMemsDivId").html('<div ><center ><img  src="images/icons/loading.gif"></center></div>');
	 var jsObj ={
			          userAccessLevelId:globalUserAccessLevelId,
					  userAccessLevelValuesArray:globalUserAccessLevelValues,
					  userId :1,
					  activityMemberId : globalActivityMemberId,
					  userTypeId : globalUserTypeId
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
					str+='<h4 class="text-capital">'+result[i][0].userType+'</h4>';
					str+='<div id="genSecTraining'+i+'" style="width:100%;height:100px;"></div>';
				str+='</div>'
			}
		}
		$("#userTypeWiseTrainingProgramTopFiveStrongAndPoorMemsDivId").html(str);
		if(result != null && result.length > 0){
			for(var i in result){
				var length1 = result[i].length ;
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
	}
	function buildgetUserTypeWiseTrainingProgramAttendedCountTopFivePoorResults(result){
		var str='';
		if(result != null && result.length > 0){
			var str='';
			for(var i in result){
				str+='<div class="col-md-12 col-xs-12 col-sm-12">';
					str+='<h4 class="text-capital">'+result[i][0].userType+'</h4>';
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
	 setTimeout(function(){
		$(".trainingsHiddenBlock,.moreTrainingBlocksIcon").toggle();
		getUserTypeWiseTotalEligibleAndAttendedCnt();
	},800); 
});
$(document).on("click",".moreTrainingBlocksIcon",function(){
	$(".moreTrainingBlocks").toggle();
	setTimeout(function(){
		moreTrainingBlocks();
		buildTrainingProgramRslt(globalTrainingProgramsRslt);
		getChildUserTypesByItsParentUserTypeForTrainingProgram();
		getTrainingCampProgramsDetailsCntByDistrict();
		getTrainingProgramPoorCompletedLocationDtls();
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
	
});
  function getChildUserTypesByItsParentUserTypeForTrainingProgram(){
		 $("#childUserTypeDetailsDivForTrainingProgram").html('<div ><center ><img  src="images/icons/loading.gif"></center></div>');
		var jsObj = { parentUserTypeId : globalUserTypeId }
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
		getTrainingProgramPoorCompletedLocationDtls();
	}
	function getSelectedChildTypeMembersForTrainingProgram(firstChildUserTypeId){
	 $("#childActivityMemberDivId").html('<div ><center ><img  src="images/icons/loading.gif"></center></div>');
	 $("#userTypeWiseChildDtlsTabId").html('<div ><center ><img  src="images/icons/loading.gif"></center></div>');
	 $("#poorPerformancTrainingPrograLocationsDivId").html('<div ><center ><img  src="images/icons/loading.gif"></center></div>');
	  var parentActivityMemberId = globalActivityMemberId;
	  var childUserTypeId = firstChildUserTypeId;
	  var jsObj ={ 
	               parentActivityMemberId : parentActivityMemberId,
				   childUserTypeId : childUserTypeId,
				   userAccessLevelId : globalUserAccessLevelId,
				   userAccessLevelValuesArray : globalUserAccessLevelValues,
				   reportType :"selectedUserType"
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
	 
	 var str='';
	  str+='<ul class="list-inline slickPanelSliderTraining">';
	  var rank=1; 
	   for(var i in result){
	str+='<li style="cursor:pointer;" class="activityMemberCls"  attr_selectedusertype="'+result[i].userType+'"  attr_id="userTypeWiseChildDtlsTabId"  attr_selectedmembername="'+result[i].name+'"  attr_activitymemberid='+result[i].activityMemberId+'  attr_usertypeid='+result[i].userTypeId+' style="width:380px !important;">';
		  str+='<div class="panel panel-default panelSlick">';
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
 }
 $(document).on("click",".childUserTypeClsForTrainingProgram",function(){
	var childUserTypeId = $(this).attr("attr_userTypeId");
	getSelectedChildTypeMembersForTrainingProgram(childUserTypeId);
});
$(document).on("click",".activityMemberCls",function(){
	    var activityMemberId = $(this).attr("attr_activitymemberid");  
		var userTypeId = $(this).attr("attr_usertypeid"); 
    	var selectedMemberName = $(this).attr("attr_selectedmembername");  
		var selectedUserType = $(this).attr("attr_selectedusertype"); 	
		var childActivityMemberId = $(this).attr("attr_id");  
		getDirectChildActivityTrainingProgramMemberDetails(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId,"");
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
		getDirectChildActivityTrainingProgramMemberDetails(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId,"subLevel");
});
  function getDirectChildActivityTrainingProgramMemberDetails(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId,types){
	  $("#"+childActivityMemberId).html('<div ><center ><img  src="images/icons/loading.gif" ></center></div>');
	  var jsObj ={  activityMemberId : activityMemberId,
			         userTypeId : userTypeId,
					 reportType : "directChild",
				  }
	   	$.ajax({
			type : 'POST',
			url : 'getDirectChildActivityTrainingProgramMemberDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		  $("#"+childActivityMemberId).html('');
		  if(result != null && result.length > 0){
			  buildChildActivityMembersDetails(result,selectedMemberName,selectedUserType,childActivityMemberId,types);
		  }else{
			  $("#"+childActivityMemberId).html('NO DATA AVAILABLE');  
		  }
		});
	}
	function buildChildActivityMembersDetails(result,selectedMemberName,selectedUserType,childActivityMemberId,types){
		var str='';
		str+='<h4><span  class="text-capital">'+selectedMemberName+'</span> - <span class="text-capitalize">'+selectedUserType+'</span></h4>';
		 if(childActivityMemberId != "userTypeWiseChildDtlsTabId"){
				str+='<span class="remveSlcUsrType pull-right" attr_removeSelecUserType = "'+childActivityMemberId+'" style="margin-top: -5px;"><i class="glyphicon glyphicon-remove"></i></span>';
		 } 
		str+='<table class="table table-condensed tableHoverLevels m_top20">';
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
		   str+='<tr class="lowLevelActivityMemberClsForTrainingProgram"  attr_activitymemberid = "'+result[i].activityMemberId+'" attr_usertypeid = "'+result[i].userTypeId+'" attr_selectedmembername = "'+result[i].name+'" attr_selectedusertype = "'+result[i].userType+'">';
			str+='<td>';
				str+='<span class="tableCount">'+rank+'</span>';
			str+='</td>';
			if(types != null && types=="subLevel"){
			if(result[i].localName != null && result[i].localName.trim().length > 0){
				str+='<td>'+result[i].userType+' (<b>'+result[i].locationName.split(" ")[0]+'</b>)</td>';	
			}
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
function buildTrainingProgramRslt(globalTrainingProgramsRslt){
	var str='';
	if(globalTrainingProgramsRslt != null && globalTrainingProgramsRslt.length > 0){
		str+'<ul class="trainingsUl">';
		  for(var i in globalTrainingProgramsRslt){
			  str+='<li>';
			  str+='<h4 class="text-capitalize text-muted">'+globalTrainingProgramsRslt[i].name+'</h4>';
			  str+='<div id="programHighChartId'+i+'" class="chartLi trainingGraphWidth"></div>';
			  str+='</li>';
		  }
		str+='</ul>';
	}
	$("#programsDivId").html(str);
	if(globalTrainingProgramsRslt != null && globalTrainingProgramsRslt.length > 0){
		  for(var i in globalTrainingProgramsRslt){
			var  jsonDataArr=[];
			jsonDataArr.push({name:"Total Eligible",data:[globalTrainingProgramsRslt[i].totalEligibleCountPer]});
			jsonDataArr.push({name:"Attended",data:[globalTrainingProgramsRslt[i].totalAttenedCountPer]});
			jsonDataArr.push({name:"Yet to train",data:[globalTrainingProgramsRslt[i].totalNotAttenedCountPer]});
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
			 series:jsonDataArr
		  });
		});  
	   }
	}
}
	 function getTrainingProgramPoorCompletedLocationDtls(){
	 $("#poorPerformancTrainingPrograLocationsDivId").html('<div ><center ><img  src="images/icons/loading.gif"></center></div>');
		var jsObj ={ 
		             userAccessLevelId : globalUserAccessLevelId,
					 userAccessLevelValuesArray : globalUserAccessLevelValues
				  }
		
		$.ajax({
			type : 'POST',
			url : 'getTrainingProgramPoorCompletedLocationDtlsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		   $("#poorPerformancTrainingPrograLocationsDivId").html('');
	       if(result != null){
			   buildTrainingProgramPoorPerformanceLocationRslt(result);
		   }
		});
	}
	function buildTrainingProgramPoorPerformanceLocationRslt(result){
	var districtList = result.districtList;
    var str='';
	  str+='<div class="col-md-6 col-xs-12 col-sm-6">';
	  str+='<p class="text-capital">districts</p>';
      str+='<table class="table tableCumulative">';
      if(districtList != null && districtList.length > 0){
		  var order=1;
		  for(var i in districtList){
			str+='<tr>';
			str+='<td><span class="count" style="background-color:rgba(237, 29, 38,1)">'+order+'</span></td>';
			str+='<td>'+districtList[i].name+'</td>';
			str+='<td>';
				str+='<div class="progress progressCustom">';
			str+='<div class="progress-bar" role="progressbar" aria-valuenow="'+districtList[i].totalAttenedCountPer+'" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">';
					str+='<span class="sr-only">'+districtList[i].totalAttenedCountPer+'</span>';
				  str+='</div>';
				str+='</div>';
			str+='</td>';
			str+='<td class="text-danger">'+districtList[i].totalAttenedCountPer+'</td>';
			str+='</tr>';
			order=order+1;
			if(order==5)
				break;
			}
			str+='</table>';
	  }	  
	  str+='</div>';
		
	var constituencyList = result.constituencyList;	
	  str+='<div class="col-md-6 col-xs-12 col-sm-6">';
	  str+='<p class="text-capital">Constituencies</p>';
      str+='<table class="table tableCumulative">';
      if(constituencyList != null && constituencyList.length > 0){
		  var order=1;
		  for(var i in constituencyList){
			str+='<tr>';
			str+='<td><span class="count" style="background-color:rgba(237, 29, 38,1)">'+order+'</span></td>';
			str+='<td>'+constituencyList[i].name+'</td>';
			str+='<td>';
				str+='<div class="progress progressCustom">';
			str+='<div class="progress-bar" role="progressbar" aria-valuenow="'+constituencyList[i].totalAttenedCountPer+'" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">';
			str+='<span class="sr-only">'+constituencyList[i].totalAttenedCountPer+'</span>';
			str+='</div>';
			str+='</div>';
			str+='</td>';
			str+='<td class="text-danger">'+constituencyList[i].totalAttenedCountPer+'</td>';
			str+='</tr>';
			order=order+1;
			if(order==5)
				break;
			}
				str+='</table>';
			}
	     str+='</div>';
																				
	 $("#poorPerformancTrainingPrograLocationsDivId").html(str);	
	}
	
	
	
	
	
function moreTrainingBlocks()
	{
		var chartWidth = $("#leadershipSkills").parent().width();
		$(".trainingGraphWidth").width(chartWidth);
		$(function () {
		  $('#leadershipSkills').highcharts({
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
			 series: [{
			  name: 'Total',
			  data: [10]
			}, {
			  name: 'Attended',
			  data: [10,20,30,40]
			}, {
			  name: 'Not attended',
			  data: [10,20,30,40]
			}]
		  });
		});
		$(function () {
		  $('#officialSpokespersons').highcharts({
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
			 series: [{
			  name: 'Started',
			  data: [10,20,30,40]
			}, {
			  name: 'Completed',
			  data: [10,20,30,40]
			}, {
			  name: 'Yet To Start',
			  data: [10,20,30,40]
			}]
		  });
		});
		$(function () {
			$('#leadershipSkillsDis').width($('#leadershipSkillsDis').parent().width());
		  $('#leadershipSkillsDis').highcharts({
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
			 series: [{
			  name: 'Started',
			  data: [10,20,30,40]
			}, {
			  name: 'Completed',
			  data: [10,20,30,40]
			}]
		  });
		});
		$(function () {
			$('#officialSpokesPerDis').width($('#leadershipSkillsDis').parent().width());
		  $('#officialSpokesPerDis').highcharts({
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
			 series: [{
			  name: 'Started',
			  data: [10,20,30,40]
			}, {
			  name: 'Completed',
			  data: [10,20,30,40]
			}]
		  });
		});
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
	}
/* more training blocks end*/
/* Training Funcitons End*/