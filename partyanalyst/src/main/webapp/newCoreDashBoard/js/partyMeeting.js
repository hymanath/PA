var customStartDateMeetings = moment().subtract(1, 'month').startOf('month').format('DD/MM/YYYY')
var customEndDateMeetings = moment().subtract(1, 'month').endOf('month').format('DD/MM/YYYY');

	$("#dateRangeIdForMeetings").daterangepicker({
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
	$('#dateRangeIdForMeetings').on('apply.daterangepicker', function(ev, picker) {
	  customStartDateMeetings = picker.startDate.format('DD/MM/YYYY');
	  customEndDateMeetings = picker.endDate.format('DD/MM/YYYY');
	  //alert(customStartDateMeetings + "-" +customEndDateMeetings);
	  getPartyMeetingBasicCountDetails();
	  getUserTypeWiseMeetingCounductedNotCounductedMayBeDetailsCnt();
	  getPartySpecialMeetingsMainTypeOverview();
	  getPartyMeetingsMainTypeStateLevelOverview();
	});
    $(document).on("click",".meetingGetDtlsBtncls",function(){
		getPartyMeetingBasicCountDetails();
		getUserTypeWiseMeetingCounductedNotCounductedMayBeDetailsCnt();
		$(".settingsDropDown").hide();
		$("#committeeTypeDivId").hide();
	});
	function getPartyMeetingTypeByPartyMeetingMainType()
	{ 
		var jsObj ={ 
		             partyMeetingMainTypeId : 1
				  }
		$.ajax({
			type : 'POST',
			url : 'getPartyMeetingTypeByPartyMeetingMainTypeAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				buildCommitteeTypes(result);
			}
			getPartyMeetingBasicCountDetails();
		});
	}
 function buildCommitteeTypes(result){
	 var str='';
	 str+='<ul style="list-style: none;" id="committeeTypeId" class="selectAllOptions">';
	 for(var i in result){
		 str+="<li><label><input checked type='checkbox' id="+result[i].id+">&nbsp&nbsp"+result[i].name+"</label></li>";
	 }
	 str+='</ul> ';
	 $("#committeeTypeDivId").html(str);
 }	
$(document).on("click",".committeeMeetingsSettings",function(){
    $("#committeeTypeDivId").show();
	$(".settingsDropDown").toggle();
});
$(document).on("click",".selectAll",function(){
   if($(this).is(":checked")){
	$("#committeeTypeId li").each(function() {
	  $(this).find("input").prop("checked",true)
	});
   }else{
	 $("#committeeTypeId li").each(function() {
	  $(this).find("input").prop("checked",false)
	});
   }	
});

      var globalStateId=1; 
	function getPartyMeetingBasicCountDetails()
	{ 
	  $("#meetingBasicCountDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	
	 var partyMeetingTypeArr=[];
	  $("#committeeTypeId li").each(function() {
		  if($(this).find("input").is(":checked")){
			  partyMeetingTypeArr.push($(this).find("input").attr("id"));
		  }
	   });
	    var dates=$("#dateRangeIdForMeetings").val();
		var fromDateStr;
		var toDateStr;
		if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			fromDateStr = datesArr[0]; 
			toDateStr = datesArr[1]; 
		}
		var jsObj ={ 
		             activityMemberId : globalActivityMemberId,
					 stateId : globalStateId,
					 fromDate : fromDateStr,
					 toDate : toDateStr,
					 partyMeetingTypeArr:partyMeetingTypeArr
					 
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
			 str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
		      str+='<table class="table tableTraining">';
			  str+='<tbody><tr>';
				  str+='<td>';
					  str+='<h4>'+overAllResult.totalCount+'</h4>';
					  str+='<p class="text-muted text-capital">total</p>';
				  str+='</td>';
				  str+='<td>';
					  str+='<h4>'+overAllResult.conductedCount+'<span class="font-10 text-success"> '+overAllResult.conductedCountPer+'%</span></h4>';
					  str+='<p class="text-muted text-capital">yes</p>';
				  str+='</td>';
				  str+='<td>';
					  str+='<h4>'+overAllResult.notConductedCount+'<span class="font-10 text-danger"> '+overAllResult.notConductedCountPer+'%</span></h4>';
					  str+='<p class="text-muted text-capital">no</p>';
				  str+='</td>';
				  str+='<td>';
					  str+='<h4>'+overAllResult.mayBeCount+'<span class="font-10 text-customColor"> '+overAllResult.mayBeCountPer+'%</span></h4>';
					  str+='<p class="text-muted text-capital">maybe</p>';
				  str+='</td>';
			  str+='</tr>';
	       str+='</tbody></table>';
		  str+='<hr class="m_0">';
		  str+='</div>';  
		  }
		 if(levelWiseResult != null && levelWiseResult.length > 0){
		   for(var i in levelWiseResult){
			 str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
				 str+='<h4 class="text-capitalize">'+levelWiseResult[i].name+'</h4>';
				str+='<table class="table tableTraining bg_ED">';
					 str+='<tbody><tr>';
						 str+='<td>';
							 str+='<p class="text-muted text-capitalize">total</p>';
							 str+='<h4>'+levelWiseResult[i].totalCount+'</h4>';
						 str+='</td>';
						 str+='<td>';
							 str+='<p class="text-muted text-capitalize">yes</p>';
							 str+='<h4>'+levelWiseResult[i].conductedCount+'<span class="font-10 text-success"> '+levelWiseResult[i].conductedCountPer+'%</span></h4>';
						 str+='</td>';
						 str+='<td>';
							 str+='<p class="text-muted text-capitalize">no</p>';
							 str+='<h4>'+levelWiseResult[i].notConductedCount+' <span class="font-10 text-danger"> '+levelWiseResult[i].notConductedCountPer+'%</span></h4>';
						 str+='</td>';
						 str+='<td>';
							 str+='<p class="text-muted text-capitalize">maybe</p>';
							 str+='<h4>'+levelWiseResult[i].mayBeCount+' <span class="font-10 text-customColor"> '+levelWiseResult[i].mayBeCountPer+'%</span></h4>';
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
	$("#stateLevelMeetingBlockId").html(' ');	
   $("#userTypeWiseTopFiveStrongAndPoorMeetingMemsDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
      var partyMeetingTypeArr=[];
	  $("#committeeTypeId li").each(function() {
		  if($(this).find("input").is(":checked")){
			  partyMeetingTypeArr.push($(this).find("input").attr("id"));
		  }
	  });
	     var dates=$("#dateRangeIdForMeetings").val();
		var fromDateStr;
		var toDateStr;
		if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			fromDateStr = datesArr[0]; 
			toDateStr = datesArr[1]; 
		}
		  var jsObj ={ 
		             activityMemberId : globalActivityMemberId,
					 userTypeId : globalUserTypeId,
					 stateId : globalStateId,
					 fromDate : fromDateStr,
					 toDate : toDateStr,
					 partyMeetingTypeArr : partyMeetingTypeArr
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
			  str+='<div id="genSecMeeting'+i+'" style="width:100%;height:80px;"></div>';
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
					name: 'Conducted',
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
		$("#stateLevelMeetingBlockId").html(' ');
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
					name: 'Conducted',
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
	  buildgUserTypeWiseConductedAndMayBeTopFiveStrongPerResults(globalUserWiseMeetingMemberRslt); 
	 }else if(memberType == "poor"){
	  buildgUserTypeWiseConductedAndMayBeTopFivePoorPerResults(globalUserWiseMeetingMemberRslt);
	 }
});
	$(document).on("click",".meetingsIconExpand",function(){
		$(".dateRangePickerClsForMeetings").toggleClass("hide");
		getUserTypeWiseMeetingCounductedNotCounductedMayBeDetailsCnt();
		$(".showMoreBlockCls").attr("attr_main_type_meeting_id",1);//committee meeting
		$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".meetingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".meetingsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
		$(".meetingsHiddenBlock,.moreMeetingsBlocksIcon").toggle();
		$(".moreMeetingsBlocks1,.stateLevelMeetingBlock,.stateGeneralMeetBlock").hide();
		if( !$(this).find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".meetingsHiddenBlock,.moreMeetingsBlocks").hide();
			$(".moreMeetingsBlocks1").hide();
			$(".moreMeetingsBlocksDetailed").hide();
			$(".moreMeetingsBlocksComparision").hide();
			$(".stateGeneralMeeting,.specialMeetings,.stateLevelMeetingsExpand").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen")
		}else{
			//getUserTypeWiseTotalEligibleAndAttendedCnt();
		}
		if( $(".trainingIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".trainingIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".trainingsHiddenBlock,.moreTrainingBlocks,.moreTrainingBlocksIcon").hide();
			$(".moreTrainingBlocksIcon").removeClass("unExpandTrainingBlock");
			$(".trainingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".moreMeetingsBlocks1").hide();
			$(".moreMeetingsBlocksDetailed").hide();
			$(".moreMeetingsBlocksComparision").hide();
		}else if( $(".debatesIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".debatesIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".debatesMoreHiddenBlock,.debatesHiddenBlock,.dateRangePickerClsForDebates").hide();
			$(".moreDebatesBlocksIcon").removeClass("unExpandDebatesBlock");
			$(".debatesBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".moreMeetingsBlocks1").hide();
			$(".moreMeetingsBlocksDetailed").hide();
			$(".moreMeetingsBlocksComparision").hide();
		}else if( $(".iconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".iconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".committeesHiddenBlock,.moreBlocks,.moreBlocks1,.moreBlocksDetailAndComp,.moreBlocksIcon").hide();
			$(".committeesBlock,.basicCommitteesBlock,.userTypeCommitteesBlock,.committeesBlock1").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".dateRangePickerCls").toggleClass("hide");
			$(".moreBlocksIcon").removeClass("unExpandBlock");
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
	$(document).on("click",".stateLevelMeetingsExpand",function(){
		$(".stateGeneralMeeting").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen");
		var mainMeetingTypeId = $(this).attr("attr_main_type_meeting_id");
		var partymeetingtypeidsstring = $(this).attr("attr_partymeetingtypeidsstring");
		$(".showMoreBlockCls").attr("attr_main_type_meeting_id",mainMeetingTypeId);
	    $(".showMoreBlockCls").attr("attr_meeting_type_id",partymeetingtypeidsstring);
        $(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".moreMeetingsBlocksIcon").removeClass("unExpandBlock");
		$(".meetingsHiddenBlock,.moreMeetingsBlocksDetailed,.moreMeetingsBlocks1").hide();
		$(".showMoreBlockCls,.moreMeetingsBlocksIcon,.stateLevelMeetingBlock").show();
		
		if(!$(".meetingsIconExpand").find("i").hasClass("glyphicon-resize-small"))
		{
			$(".meetingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".meetingsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
		}
		
		if(!$(this).find("i").hasClass("glyphicon-resize-small"))
		{
			$(".stateLevelMeetingBlock").hide();
			$(".dateRangePickerClsForMeetings").addClass("hide");
			$(".meetingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".meetingsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
			$(".meetingsIconExpand").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen")
		}else{
		   $(".dateRangePickerClsForMeetings").removeClass("hide");
		   $(".meetingsIconExpand").find("i").addClass("glyphicon-resize-small").removeClass("glyphicon-fullscreen")
		   getCommitteesAndPublicRepresentativeMembersInvitedAndAttendedToMeetings(mainMeetingTypeId,partymeetingtypeidsstring);	
		}
	});
	$(document).on("click",".stateGeneralMeeting",function(){
		var attrMainTypeMeetingId = $(this).attr("attr_main_type_meeting_id");
		var meetingTypeId = $(this).attr("attr_meeting_type_id");
		$(".stateLevelMeetingsExpand,.specialMeetings").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen")
		
		$(".showMoreBlockCls").attr("attr_main_type_meeting_id",attrMainTypeMeetingId);
		$(".showMoreBlockCls").attr("attr_meeting_type_id",meetingTypeId);
		$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		
		$(".showMoreBlockCls,.moreMeetingsBlocksIcon").show();
		$(".moreMeetingsBlocksIcon").removeClass("unExpandBlock");
		if(!$(".meetingsIconExpand").find("i").hasClass("glyphicon-resize-small"))
		{
			
			$(".meetingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".meetingsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
		}
		$(".meetingsHiddenBlock,.moreMeetingsBlocksDetailed,.moreMeetingsBlocksComparision,moreMeetingsBlocksDetailed,.moreMeetingsBlocks1").hide();
		
		if(!$(this).find("i").hasClass("glyphicon-resize-small"))
		{
			
			$(".stateLevelMeetingBlock").hide();
			$(".dateRangePickerClsForMeetings").addClass("hide");
			$(".meetingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".meetingsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
			$(".meetingsIconExpand").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen")
		}else{
			$(".stateGeneralMeeting").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen")
			$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".stateLevelMeetingBlock").show();
			$(".showMoreBlockCls,.moreMeetingsBlocksIcon").show();
			$(".dateRangePickerClsForMeetings").removeClass("hide");
			$(".meetingsIconExpand").find("i").addClass("glyphicon-resize-small").removeClass("glyphicon-fullscreen")
			getCommitteesAndPublicRepresentativeMembersInvitedAndAttendedToMeetings(attrMainTypeMeetingId,meetingTypeId);	
			
		}
	});
	
	$(document).on("click",".specialMeetings",function(){
		var mainMeetingTypeId = $(this).attr("attr_main_type_meeting_id");
		var partymeetingtypeidsstring = $(this).attr("attr_partymeetingtypeidsstring");
		$(".stateLevelMeetingsExpand").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen")
		$(".showMoreBlockCls").attr("attr_main_type_meeting_id",mainMeetingTypeId);
	    $(".showMoreBlockCls").attr("attr_meeting_type_id",partymeetingtypeidsstring);
		$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".showMoreBlockCls,.moreMeetingsBlocksIcon,.stateLevelMeetingBlock").show();
		if(!$(".meetingsIconExpand").find("i").hasClass("glyphicon-resize-small"))
		{
			$(".meetingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".meetingsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
		}
		if(!$(this).find("i").hasClass("glyphicon-resize-small"))
		{
			$(".stateLevelMeetingBlock,.moreMeetingsBlocksDetailed,.meetingsHiddenBlock").hide();
			$(".dateRangePickerClsForMeetings").addClass("hide");
			$(".meetingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".meetingsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
			$(".meetingsIconExpand").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen")
		}else{
			$(".stateGeneralMeeting").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen")
			$(".dateRangePickerClsForMeetings").removeClass("hide");
			$(".moreMeetingsBlocksDetailed").hide();
			$(".meetingsIconExpand").find("i").addClass("glyphicon-resize-small").removeClass("glyphicon-fullscreen")
			getCommitteesAndPublicRepresentativeMembersInvitedAndAttendedToMeetings(mainMeetingTypeId,partymeetingtypeidsstring);	
		}
	});
	$(document).on("click",".moreMeetingsBlocksIcon",function(){
		$(this).addClass("unExpandBlock");
		var mainTypeMeetingId = $(this).parent().attr("attr_main_type_meeting_id");
		var meetingtypeId = $(this).parent().attr("attr_meeting_type_id");
		if(mainTypeMeetingId != null && mainTypeMeetingId!=undefined && mainTypeMeetingId==2 || mainTypeMeetingId==3){
		$(".moreMeetingsBlocksDetailed").show();	
		 getParyMeetingTypeDetailsDistrictWise(mainTypeMeetingId,meetingtypeId);
	     $("#meetingLevelHIghChartsDivId").html(' ');
	     $("#userAccessLevelLocationDivId").html(' ');
		}else{
		getMeetingLevelDetails();
		getPartyMeetingCntDetailstLevelWiseByUserAccessLevel();
		$("#districtWisePartyMeetingTypeDivId").html(' ');
		$(".moreMeetingsBlocksDetailed").show();
		$(".moreMeetingsBlocks1").show();
		}
	});
	$(document).on("click",".unExpandBlock",function(){
		$(this).removeClass("unExpandBlock");
		$(".moreMeetingsBlocksDetailed").hide();
		$(".moreMeetingsBlocksComparision").hide();
		$(".moreMeetingsBlocks1").hide();
		$(".meetingComparisionBlock").removeClass("active");
		$(".meetingDetailedBlock").addClass("active");
	});

function getMeetingLevelDetails(){
   $("#meetingLevelHIghChartsDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var partyMeetingTypeArr=[];
		  $("#committeeTypeId li").each(function() {
		  if($(this).find("input").is(":checked")){
			  partyMeetingTypeArr.push($(this).find("input").attr("id"));
		  }
	       });
		var dates=$("#dateRangeIdForMeetings").val();   
		var fromDateStr;
		var toDateStr;
		if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			fromDateStr = datesArr[0]; 
			toDateStr = datesArr[1]; 
		}
		var jsObj ={ 
		             activityMemberId : globalActivityMemberId,
					 stateId : globalStateId,
					 fromDate : fromDateStr,
					 toDate : toDateStr,
					 partyMeetingTypeArr : partyMeetingTypeArr
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
		if(levelWiseResult != null && levelWiseResult.length > 0){
			var str='';
			var locationLevelNameArray =[];
		//	str+='<h4 class="text-capitalize">meetings attendance</h4>';
			str+='<div class="col-md-12 col-xs-12 col-sm-12 ">';
				str+='<div class="panel panel-default">';
					str+='<div class="panel-body">';
					 var length = levelWiseResult.length - 1;
					  str+='<ul class="villageWardUlMeeting">';
						for(var i = length; i >= 0; i--){
						str+='<li   style="height:300px" >';
							str+='<h4>'+levelWiseResult[i].name+'</h4>';
						 str+='<div id="meetingsLevel'+i+'" class="chartLi"></div></li>';
						}
					  str+='</ul>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
			 
		}
		$("#meetingLevelHIghChartsDivId").html(str);
		$(".villageWardUlMeeting").slick({
			 slide: 'li',
			 slidesToShow: 4,
			 slidesToScroll: 2,
			 infinite: false,
			 responsive: [
				{
				  breakpoint: 1024,
				  settings: {
					slidesToShow: 3,
					slidesToScroll: 3,
					infinite: true,
					dots: true
				  }
				},
				{
				  breakpoint: 600,
				  settings: {
					slidesToShow: 2,
					slidesToScroll: 2
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
 $(document).on("click",".childUserTypeClsForMeeting",function(){
	var childUserTypeId = $(this).attr("attr_userTypeId");
	getSelectedChildUserTypeMembersWithMeetingsCount(childUserTypeId);
});
$(document).on("click",".compareActivityMemberClsForMeeting",function(){
		//$(".slickPanelSlider").find("li").removeClass("active");
		//$(this).addClass("active");
		$(".slickPanelSliderMeeting").find("li").removeClass("panelActiveSlick");
		$(this).addClass("panelActiveSlick");
		var activityMemberId = $(this).attr("attr_activitymemberid");  
		var userTypeId = $(this).attr("attr_usertypeid"); 
		var selectedMemberName = $(this).attr("attr_selectedmembername");  
		var selectedUserType = $(this).attr("attr_selectedusertype");  
		var childActivityMemberId = $(this).attr("attr_id");  
		getDirectChildActivityMemberMeetingDetails(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId);
		getTopPoorMeetingLocations(activityMemberId,selectedMemberName,selectedUserType);
	});
 $(document).on("click",".compareLowLevelActivityMeetingMemberCls",function(){
	 $(this).closest('tr').next('tr.showHideTr').show(); 
	 
	var activityMemberId = $(this).attr("attr_activitymemberid");  
	var userTypeId = $(this).attr("attr_usertypeid"); 
	var selectedMemberName = $(this).attr("attr_selectedmembername");  
	var selectedUserType = $(this).attr("attr_selectedusertype");  
	var childActivityMemberId = $(this).closest('tr').next('tr.showHideTr').attr("attr_id");  
	getDirectChildActivityMemberMeetingDetails(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId);
	getTopPoorMeetingLocations(activityMemberId,selectedMemberName,selectedUserType);

});
	
function getChildUserTypesByItsParentUserTypeForMeeting(){
		
		var jsObj = { parentUserTypeId : globalUserTypeId }
		$.ajax({
			type : 'POST',
			url : 'getChildUserTypesByItsParentUserTypeAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			buildgetChildUserTypesByItsParentUserTypeForMeeting(result)
		});			 
	}
	function buildgetChildUserTypesByItsParentUserTypeForMeeting(result){
		$("#childUserTypeDetailsDivIdForMeeting").html('');
		var str='';
		 str+='<ul class="comparisonSelect">';
		 
		 var firstChildUserTypeId;
		 
		 if(result !=null && result.length >0){
			 firstChildUserTypeId = result[0].userTypeId;
			 for(var i in result){
				 str+='<li attr_usertypeid="'+result[i].userTypeId+'" class="childUserTypeClsForMeeting">'+result[i].userType+'<span class="closeIconComparison"></span></li>';
			 }
		 }
		str+='</ul>';
		$("#childUserTypeDetailsDivIdForMeeting").html(str);
		$(".comparisonSelect li:first-child").addClass("active")
		
		getSelectedChildUserTypeMembersWithMeetingsCount(firstChildUserTypeId);
		
	}
	function getSelectedChildUserTypeMembersWithMeetingsCount(firstChildUserTypeId){
		 $("#childActivityMemberDivIdForMeeting").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	  var parentActivityMemberId = globalActivityMemberId;
	  var childUserTypeId = firstChildUserTypeId;
	   var state = globalState
	  var partyMeetingTypeArr=[];
	  $("#committeeTypeId li").each(function() {
		  if($(this).find("input").is(":checked")){
			  partyMeetingTypeArr.push($(this).find("input").attr("id"));
		  }
	   });
	    var dates=$("#dateRangeIdForMeetings").val();
		var fromDateStr;
		var toDateStr;
		if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			fromDateStr = datesArr[0]; 
			toDateStr = datesArr[1]; 
		}
	  var jsObj ={ 
	               parentActivityMemberId : parentActivityMemberId,
				   childUserTypeId : childUserTypeId,
				   state : state,
				   partyMeetingTypeIds : partyMeetingTypeArr,
				   startDateString : fromDateStr,
				   endDateString : toDateStr
				 }
	  $.ajax({
			type : 'POST',
			url : 'getSelectedChildUserTypeMembersWithMeetingsCountAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		   $("#childActivityMemberDivIdForMeeting").html(' ');
		  if(result != null && result.length > 0){
			  buildgetSelectedChildUserTypeMembersForMeeting(result);
		  }else{
			  $("#childActivityMemberDivIdForMeeting").html("NO DATA AVAILABLE");
		  }
		});
		
	}
	function buildgetSelectedChildUserTypeMembersForMeeting(result){
	var str='';
	if(result !=null && result.length >0){
		str+='<ul class="list-inline slickPanelSliderMeeting">';
		var firstActivityMemberId;
		var firstUserTypeId;
		var firstChildActivityMemberId = "directChildActivityMeetingMemberDiv";
		var firstuserType;
		var firstUserMemberName;
			firstActivityMemberId = result[0].activityMemberId;
			firstUserTypeId = result[0].userTypeId;
			firstuserType = result[0].userType;
			firstUserMemberName = result[0].name;
		var rankVar =0;
		
		
		for(var i in result){
			rankVar =rankVar+1;
			
			if(i == 0){
				str+='<li  style="cursor:pointer;" class="compareActivityMemberClsForMeeting panelActiveSlick" attr_id ="directChildActivityMeetingMemberDiv" attr_selectedmembername="'+result[i].name+'" attr_selectedusertype="'+result[i].userType+'" attr_activitymemberid='+result[i].activityMemberId+'  attr_usertypeid='+result[i].userTypeId+' >';
			}else{
				
				str+='<li  style="cursor:pointer;" class="compareActivityMemberClsForMeeting" attr_id ="directChildActivityMeetingMemberDiv" attr_selectedmembername="'+result[i].name+'" attr_selectedusertype="'+result[i].userType+'" attr_activitymemberid='+result[i].activityMemberId+'  attr_usertypeid='+result[i].userTypeId+' >';
				
			}
				str+='<div class="panel panel-default panelSlick">';
					str+='<div class="panel-heading">';
						str+='<h4 class="panel-title"  >'+result[i].name+'</h4>';
							str+='<span class="count">'+rankVar+'</span>';
					str+='</div>';
					str+='<div class="panel-body">';
						str+='<h4 class="text-capital">'+result[i].userType+'</h4>';
						str+='<div class="table-responsive">';
							str+='<table class="table table-condensed">';
								str+='<thead>';
								 str+='<th>Total</th>';
								  str+='<th colspan="2">Yes</th>';
								  str+='<th colspan="2">No</th>';
								  str+='<th colspan="2">Maybe</th>';
							 str+='</thead>';
								str+='</thead>';
								str+='<tr>';
								if(result[i].totalMeetingCnt !=null && result[i].totalMeetingCnt >0){
									str+='<td>'+result[i].totalMeetingCnt+'</td>';
								}else{
									str+='<td> - </td>';
								}
								if(result[i].conductedMeetingCnt !=null && result[i].conductedMeetingCnt >0){
									str+='<td>'+result[i].conductedMeetingCnt+'</td>';
								}else{
									str+='<td> - </td>';
								}
								if(result[i].conductedMeetingPerc !=null && result[i].conductedMeetingPerc >0){
									str+='<td>'+result[i].conductedMeetingPerc+'%</td>';
								}else{
									str+='<td> - </td>';
								}
								if(result[i].notConductedMeetingCnt !=null && result[i].notConductedMeetingCnt >0){
									str+='<td>'+result[i].notConductedMeetingCnt+'</td>';
								}else{
									str+='<td> - </td>';
								}
								if(result[i].notConductedMeetingPerc !=null && result[i].notConductedMeetingPerc >0){
									str+='<td>'+result[i].notConductedMeetingPerc+'%</td>';
								}else{
									str+='<td> - </td>';
								}
								if(result[i].mayBeMeetingCnt !=null && result[i].mayBeMeetingCnt >0){
									str+='<td>'+result[i].mayBeMeetingCnt+'</td>';
								}else{
									str+='<td> - </td>';
								}
								if(result[i].mayBeMeetingPerc !=null && result[i].mayBeMeetingPerc >0){
									str+='<td>'+result[i].mayBeMeetingPerc+'%</td>';
								}else{
									str+='<td> - </td>';
								}
								str+='</tr>';
							str+='</table>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</li>';
		
		}
		str+='</ul>';
		$("#childActivityMemberDivIdForMeeting").html(str);
			$(".slickPanelSliderMeeting").slick({
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
	}else{
		$("#childActivityMemberDivIdForMeeting").html("No Data Available");
	}
	getDirectChildActivityMemberMeetingDetails(firstActivityMemberId,firstUserTypeId,firstUserMemberName,firstuserType,firstChildActivityMemberId);
	getTopPoorMeetingLocations(firstActivityMemberId,firstUserMemberName,firstuserType);
	}
	function getDirectChildActivityMemberMeetingDetails(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId){
	   $("#"+childActivityMemberId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	   var state = globalState
	   
	  var partyMeetingTypeArr=[];
	  $("#committeeTypeId li").each(function() {
		  if($(this).find("input").is(":checked")){
			  partyMeetingTypeArr.push($(this).find("input").attr("id"));
		  }
	   });
	    var dates=$("#dateRangeIdForMeetings").val();
		var fromDateStr;
		var toDateStr;
		if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			fromDateStr = datesArr[0]; 
			toDateStr = datesArr[1]; 
		}
	  var jsObj ={ 
	               activityMemberId : activityMemberId,
				   userTypeId : userTypeId,
				   state :state,
				   partyMeetingTypeIds : partyMeetingTypeArr,
				   startDateString : fromDateStr,
				   endDateString : toDateStr
				 }
	   
	   	$.ajax({
			type : 'POST',
			url : 'getDirectChildActivityMeetingMemberDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#"+childActivityMemberId).html('');
			buildgetDirectChildActivityMemberMeetingsDetails(result,selectedMemberName,selectedUserType,childActivityMemberId);
		});
	}
	function buildgetDirectChildActivityMemberMeetingsDetails(result,selectedMemberName,selectedUserType,childActivityMemberId){
		$("#"+childActivityMemberId).html('');
		var str ='';
		
		if(result != null && result.length >0){
			var rankVar =0;
			str+='<h4><span  class="text-capital">'+selectedMemberName+'</span> - <span class="text-capitalize">'+selectedUserType+'</span></h4>';
			if(childActivityMemberId != "directChildActivityMeetingMemberDiv"){
				str+='<span class="removeSelecUserType pull-right" attr_removeSelecUserType = "'+childActivityMemberId+'" style="margin-top: -5px;"><i class="glyphicon glyphicon-remove"></i></span>';
			}
				if(childActivityMemberId != "directChildActivityMeetingMemberDiv")
				{
					str+='<table class="table table-condensed tableLevels m_top20">';
				}else{
					str+='<table class="table table-condensed tableHoverLevels m_top20">';
				}
				
					str+='<thead class="bg_D8 text-capital">';
						str+='<th>Rank</th>';
						str+='<th>Designation</th>';
						str+='<th>Name</th>';
						str+='<th style="text-align:center;">total</th>';
						str+='<th style="text-align:center;">Yes</th>';
						str+='<th style="text-align:center;">%</th>';
						str+='<th style="text-align:center;">No</th>';
						str+='<th style="text-align:center;">%</th>';
						str+='<th style="text-align:center;">Maybe</th>';
						str+='<th style="text-align:center;">%</th>';
					str+='</thead>';
					str+='<tbody>';
					for(var i in result){
						rankVar = rankVar+1;
						 var locationNamevar = result[i].locationName;
						str+='<tr class="compareLowLevelActivityMeetingMemberCls"  attr_activitymemberid = "'+result[i].activityMemberId+'" attr_usertypeid = "'+result[i].userTypeId+'" attr_selectedmembername = "'+result[i].name+'" attr_selectedusertype = "'+result[i].userType+'">';
							str+='<td>';
							str+='<span class="tableCount">'+rankVar+'</span>';	
							str+='</td>';
							if( locationNamevar.indexOf(',') == -1){
								str+='<td>'+result[i].userType+' (<b>'+result[i].locationName+'</b>)</td>';
							}else{
								str+='<td>'+result[i].userType+'</td>';
							}
							if( result[i].name != null && $.trim(result[i].name).length > 0 ){
									str+='<td>'+result[i].name+'</td>';
							}else{
								str+='<td> - </td>';
							}
							if(result[i].totalMeetingCnt !=null && result[i].totalMeetingCnt >0){
								str+='<td style="text-align:center;" >'+result[i].totalMeetingCnt+'</td>';
							}else{
								str+='<td> - </td>';
							}
							if(result[i].conductedMeetingCnt !=null && result[i].conductedMeetingCnt >0){
								str+='<td style="text-align:center;">'+result[i].conductedMeetingCnt+'</td>';
							}else{
								str+='<td> - </td>';
							}
							if(result[i].conductedMeetingPerc !=null && result[i].conductedMeetingPerc >0){
								str+='<td style="text-align:center;">'+result[i].conductedMeetingPerc+'%</td>';
							}else{
								str+='<td> - </td>';
							}
							if(result[i].notConductedMeetingCnt !=null && result[i].notConductedMeetingCnt >0){
								str+='<td style="text-align:center;">'+result[i].notConductedMeetingCnt+'</td>';
							}else{
								str+='<td> - </td>';
							}
							if(result[i].notConductedMeetingPerc !=null && result[i].notConductedMeetingPerc >0){
								str+='<td style="text-align:center;">'+result[i].notConductedMeetingPerc+'%</td>';
							}else{
								str+='<td> - </td>';
							}
							if(result[i].mayBeMeetingCnt !=null && result[i].mayBeMeetingCnt >0){
								str+='<td style="text-align:center;">'+result[i].mayBeMeetingCnt+'</td>';
							}else{
								str+='<td> - </td>';
							}
							if(result[i].mayBeMeetingPerc !=null && result[i].mayBeMeetingPerc >0){
								str+='<td style="text-align:center;">'+result[i].mayBeMeetingPerc+'%</td>';
							}else{
								str+='<td> - </td>';
							}
						str+='</tr>';
						str+='<tr class="showHideTr" style="display:none" attr_id = "districtpositionId'+result[i].userTypeId+''+i+'">';
							
							str+='<td colspan="10"  id="districtpositionId'+result[i].userTypeId+''+i+'">';
							
							str+='</td>';
						str+='</tr>';
			}
			str+='</tbody>';
			str+='</table>';
			$("#"+childActivityMemberId).html(str);
		}else{
			if(childActivityMemberId == "directChildActivityMeetingMemberDiv"){
				$("#"+childActivityMemberId).html("No Data Available");
			}
		}
	}
	function getTopPoorMeetingLocations(activityMemberId,selectedMemberName,selectedUserType){
	 $("#topPoorLocationsMeetingDiv").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	   var state = globalState;
	   
	   var partyMeetingTypeArr=[];
	  $("#committeeTypeId li").each(function() {
		  if($(this).find("input").is(":checked")){
			  partyMeetingTypeArr.push($(this).find("input").attr("id"));
		  }
	   });
	    var dates=$("#dateRangeIdForMeetings").val();
		var fromDateStr;
		var toDateStr;
		if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			fromDateStr = datesArr[0]; 
			toDateStr = datesArr[1]; 
		}
	   
	   var jsObj ={  activityMemberId : activityMemberId,
					 state:state,
					 partyMeetingTypeIds : partyMeetingTypeArr,
 			         startDateString :   fromDateStr,
					 endDateString   :  toDateStr
				  }
	   	$.ajax({
			type : 'POST',
			url : 'getTopPoorMeetingLocationsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			buildTopPoorMeetingLocations(result,selectedMemberName,selectedUserType);
		});
	}
	function buildTopPoorMeetingLocations(result,selectedMemberName,selectedUserType){
		$("#topPoorLocationsMeetingDiv").html('');
		var str ='';
		
		if(result !=null && result.length >0){
			str+='<b><span class="color_333 pad_5 bg_CC text-capital">top five <span class="text-danger">poor</span> locations - (<span style="font-size:11px;"><i> '+selectedMemberName+' - '+selectedUserType+'</i></span>)</span></b>';
			str+='<div class="row m_top20">';
				str+='<div class="col-md-12 col-xs-12 col-sm-12">';
					str+='<p class="text-capital"><b>'+result[0].requiredName+'</b></p>';
					str+='<table class="table tableCumulative">';
			var countVar =0;
			var BGColor = 1;
				for(var i in  result){
					
					//top 5 should build.
					countVar =countVar+1;
					if (countVar === 6) {
						break;
					}
						str+='<tr>';
							str+='<td><span class="count" style="background-color:rgba(237, 29, 38,'+BGColor+')">'+countVar+'</span></td>';
							
							if(result[0].requiredName == "Mandals/Muncipalitys/Divisions" || result[0].requiredName == "Villages/Wards"){
								str+='<td>'+result[i].name+' ('+result[i].locationLevelName+')</td>';
							}else{
								str+='<td>'+result[i].name+'</td>';
							}
							str+='<td>';
							if(result[i].conductedCount !=null && result[i].conductedCount >0){
								str+='<div class="progress progressCustom" data-toggle="tooltip" data-placement="top" title="'+result[i].conductedPerc+'%">';
								  str+='<div class="progress-bar" role="progressbar" aria-valuenow="'+result[i].conductedCount+'" aria-valuemin="0" aria-valuemax="100" style="width: '+result[i].conductedPerc+'%;">';
									str+='<span class="sr-only">'+result[i].conductedPerc+'% Complete</span>';
								  str+='</div>';
								str+='</div>';
							str+='</td>';
							str+='<td class="text-danger">'+result[i].conductedCount+'</td>';
							}else{
								str+='<td class="text-danger"> - </td>';
							}
								
						str+='</tr>';
						BGColor = BGColor - 0.2;
				}
				str+='</table>';
				str+='</div>';
			str+='</div>';
			$("#topPoorLocationsMeetingDiv").html(str);
			$('.progressCustom').tooltip();
		}else{
			$("#topPoorLocationsMeetingDiv").html("No Data Available");
		}			
	}
	
	
	function getPartyMeetingCntDetailstLevelWiseByUserAccessLevel()
	{ 
	  $("#userAccessLevelLocationDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	
	 var partyMeetingTypeArr=[];
	  $("#committeeTypeId li").each(function() {
		  if($(this).find("input").is(":checked")){
			  partyMeetingTypeArr.push($(this).find("input").attr("id"));
		  }
	   });
	    var dates=$("#dateRangeIdForMeetings").val();
		var fromDateStr;
		var toDateStr;
		if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			fromDateStr = datesArr[0]; 
			toDateStr = datesArr[1]; 
		}
		var jsObj ={ 
		             activityMemberId : globalActivityMemberId,
					 stateId : globalStateId,
					 fromDate : fromDateStr,
					 toDate : toDateStr,
					 partyMeetingTypeArr:partyMeetingTypeArr
					 
				  }
		$.ajax({
			type : 'POST',
			url : 'getPartyMeetingCntDetailstLevelWiseByUserAccessLevelAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		    buildPartyMeetingCntDetailstLevelWiseByUserAccessLevel(result);
		});
	}
	
	
	function buildPartyMeetingCntDetailstLevelWiseByUserAccessLevel(result){
		$("#userAccessLevelLocationDivId").html('');
		if(result != null && result.length > 0){
			var str='';
			
			for(var i in result){
				str+=result[i].name;
				str+='<div id="locationWiseMeeting'+i+'" class="chartLiD" style="height:300px" ></div>';
			}
									
		}
		$("#userAccessLevelLocationDivId").html(str);
		
		
		
	if(result != null && result.length > 0){
		for(var i in result){
			var districtNamesArray =[];
			var conductedCountPercArray = [];
			var notConductedCountPerArray = [];
			var mayBeCountPerArray = [];
			if(result[i].partyMettingsVOList !=null && result[i].partyMettingsVOList.length > 0){
				for(var j in result[i].partyMettingsVOList){
						districtNamesArray.push(result[i].partyMettingsVOList[j].name);
							conductedCountPercArray.push(result[i].partyMettingsVOList[j].conductedCountPer);
							notConductedCountPerArray.push(result[i].partyMettingsVOList[j].notConductedCountPer);
							mayBeCountPerArray.push(result[i].partyMettingsVOList[j].mayBeCountPer);
					}
			}
						$(function () {
							$('#locationWiseMeeting'+i+'').highcharts({
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
								series: [{
									name: 'Yes',
									data: conductedCountPercArray
								}, {
									name: 'No',
									data: notConductedCountPerArray
								}, {
									name: 'Maybe',
									data: mayBeCountPerArray
								}]
							});
						});
				
			
		}
	}else{
		$("#userAccessLevelLocationDivId").html("No Data Available")
	}	
}
$(document).on("click",".meetingComparisionBlock",function(){
	getChildUserTypesByItsParentUserTypeForMeeting();
	$(".moreMeetingsBlocksComparision").show();
	$(".moreMeetingsBlocksDetailed").hide();
});

$(document).on("click",".meetingDetailedBlock",function(){
	getMeetingLevelDetails();
	getPartyMeetingCntDetailstLevelWiseByUserAccessLevel();
	$(".moreMeetingsBlocksDetailed").show();
	$(".moreMeetingsBlocksComparision").hide();
});
/*Notes Functionality*/
function displayDashboardCommentsForMeetings(dashBoardComponentId){
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
  		 
     str+='<ul class="notesUlMeetings m_top20" style="text-transform: none;font-weight: normal;font-size: 14px;">';  	
        	     
				for(var i in result){ 
                    str+='<li style="margin-top:3px;">'; 
                    str+='<span class="notesTextMeetings" id="editTextmettingId'+i+'"  attr_commentId="'+result[i].dashBoardCommentId+'">'+result[i].comment+' </span>- <span class="text-muted"><i>'+result[i].insertedTime+'</i></span>';
				    str+='<i class="glyphicon glyphicon-trash pull-right hoverBlock deleteNotesMeetings" attr_cmt_id="editTextmettingId'+i+'" id="'+result[i].dashBoardCommentId+'" onClick="deleteDashBoardcomments(this.id);"></i>';
                    str+='<i class="glyphicon glyphicon-edit pull-right hoverBlock editNotesMeetings" attr_cmt_id="editTextmettingId'+i+'" attr_comment="'+result[i].comment+'"></i>';
                    str+='</li>';
				}
                    str+='</ul>';
					/*str+='<hr/>';
					str+='<div id="meetingsUpId" style="color:red;"></div>';
                    str+='<label>Create Notes</label>';
                    str+='<textarea class="form-control notesAreaMeetings"></textarea>';
                    str+='<button class="btn btn-default btnCustomCreateMeetings btn-sm "  onClick="savingDashboardCommentForMeetings(2);">create</button>';*/
		
		$("#notesMeetingId").html(str);	 
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

function savingDashboardCommentForMeetings(dashboardComponentId){  
var comment=$(".notesAreaMeetings").val();
if(comment.trim() ==""){
	  $("#meetingsUpId").html("Notes Required.");
	  return;
  }
var editId = $("#cmtMeetingId").val();
//$("#"+editId).parent().html(' ');
$("#"+editId).html(comment);
 var dashboardCommentId=0;
 if($(".notesAreaMeetings").attr("attr_commentid")>0)
 {
	dashboardCommentId=$(".notesAreaMeetings").attr("attr_commentid");		
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
			
			//$("#meetingsUpId").html('update succuss');
			displayDashboardCommentsForMeetings(2);
		}
	}			
});
}
$(document).on("click",".notesIconMeeting",function(){
$(this).closest(".panel-heading").find(".notesDropDown").toggle();
});
$(document).on("click",".deleteNotesMeetings",function(){
$(this).closest("li").remove();
});
$(document).on("click",".editNotesMeetings",function(){ 
var commentId = $(this).attr("attr_cmt_id");
var commentId1 = $(this).parent().find(".notesTextMeetings").attr("attr_commentid");
var notesHtml = $("#"+commentId).html();
$(".notesAreaMeetings").val(notesHtml);  
$(".notesAreaMeetings").attr("attr_commentid",commentId1);  
$("#cmtId").val(commentId);
//$("#cmtId").val();
$("#meetingsUpId").html('');		
});

$(document).on("click",".btnCustomCreateMeetings",function(){
var getNewNotes = $(".notesAreaMeetings").val();
var todayDate = moment().format("DD MMMM YYYY");
var cmtId = $("#cmtId").val();
var commentText = '<span class="notesText" id="'+cmtId+'" >'+getNewNotes+'</span> - <span class="text-muted"><i>'+todayDate+'</i></span> <i  class="glyphicon glyphicon-trash pull-right hoverBlock deleteNotesMeetings"></i><i class="glyphicon glyphicon-edit pull-right hoverBlock editNotes" attr_cmt_id="'+cmtId+'"></i>'; 
if(cmtId>0)
$(".notesUlMeetings").append("<li>"+commentText+"</li>");
$(".notesAreaMeetings").val('');	
});



//State Level Meeting  && Special meeting section

$(document).on("click",".stateLevelMeetingBtnCls",function(){
	
	//var attrMainTypeMeetingId = $(this).attr("attr_main_type_meeting_id");
	//$(".showMoreBlockCls").attr("attr_main_type_meeting_id",attrMainTypeMeetingId);
	//$(".showMoreBlockCls").attr("attr_meeting_type_id",0);
	getPartyMeetingsMainTypeStateLevelOverview();
	$(".settingsStateLevelMeetingDropDown").hide();
	$("#stateLevelMeetingDivId").hide();
	$(".moreMeetingsBlocksDetailed").hide();
});
 $(document).on("click",".specialMeetingBtncls",function(){
	// var attrMainTypeMeetingId = $(this).attr("attr_main_type_meeting_id");
	// $(".showMoreBlockCls").attr("attr_main_type_meeting_id",attrMainTypeMeetingId);
	// $(".showMoreBlockCls").attr("attr_meeting_type_id",0);
	 getPartySpecialMeetingsMainTypeOverview(); 
	 $(".specialMeetingDropDown").hide();
	 $("#specialMeetingDivId").hide();
	 $(".moreMeetingsBlocksDetailed").hide();
});
/* $(document).on("click",".meetingTypeCls",function(){
	var attrMainTypeMeetingId = $(this).attr("attr_main_type_meeting_id");
	var meetingTypeId = $(this).attr("attr_meeting_type_id");
	 $(".showMoreBlockCls").attr("attr_main_type_meeting_id",attrMainTypeMeetingId);
	 $(".showMoreBlockCls").attr("attr_meeting_type_id",meetingTypeId);
	 $(".moreMeetingsBlocksDetailed").hide();
}); */
function getStateLevelMeetingsByMeetingType()
	{ 
		var jsObj ={ 
		             partyMeetingMainTypeId : 2
				  }
		$.ajax({
			type : 'POST',
			url : 'getPartyMeetingTypeByPartyMeetingMainTypeAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				buildStateLevelMeeting(result);
			}
			getPartyMeetingsMainTypeStateLevelOverview(); 
		});
	}
	 function buildStateLevelMeeting(result){
	 var str='';
	 str+='<ul style="list-style: none;" id="stateLevelMeetingUlId" class="selectAllStateLevelOptions">';
	 for(var i in result){
		 str+="<li><label><input checked type='checkbox' id="+result[i].id+">&nbsp&nbsp"+result[i].name+"</label></li>";
	 }
	 str+='</ul> ';
	 $("#stateLevelMeetingDivId").html(str);
 }	
	function getSpecialMeetingsByMeetingType(){
	var jsObj ={ 
		             partyMeetingMainTypeId : 3
				  }
		$.ajax({
			type : 'POST',
			url : 'getPartyMeetingTypeByPartyMeetingMainTypeAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				buildSpecialMeetingResult(result);
			}
			getPartySpecialMeetingsMainTypeOverview();
		});	
	}
	 function buildSpecialMeetingResult(result){
	 var str='';
	 str+='<ul style="list-style: none;" id="specialMeetingUlId" class="selectAllSpecialMeetingOptions">';
	 for(var i in result){
		 str+="<li><label><input checked type='checkbox' id="+result[i].id+">&nbsp&nbsp"+result[i].name+"</label></li>";
	 }
	 str+='</ul> ';
	 $("#specialMeetingDivId").html(str);
 }	
$(document).on("click",".stateLevelMeetingSeeting",function(){
	$("#stateLevelMeetingDivId").show();
	$(".settingsStateLevelMeetingDropDown").toggle();
	$(".specialMeetingDropDown").hide();
});
$(document).on("click",".specialMeetingSeeting",function(){
    $("#specialMeetingDivId").show();
	$(".specialMeetingDropDown").toggle();
	$(".settingsStateLevelMeetingDropDown").hide();
});
$(document).on("click",".selectAllStateLevelMeeting",function(){
   if($(this).is(":checked")){
	$("#stateLevelMeetingUlId li").each(function() {
	  $(this).find("input").prop("checked",true)
	});
   }else{
	 $("#stateLevelMeetingUlId li").each(function() {
	  $(this).find("input").prop("checked",false)
	});
   }	
});
$(document).on("click",".selectAllSpecialMeeting",function(){
   if($(this).is(":checked")){
	$("#specialMeetingUlId li").each(function() {
	  $(this).find("input").prop("checked",true)
	});
   }else{
	 $("#specialMeetingUlId li").each(function() {
	  $(this).find("input").prop("checked",false)
	});
   }	
});
function getPartyMeetingsMainTypeStateLevelOverview(){
	$("#stateLevelMeetingBasicCnt").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	var partyMeetingTypeArr=[];
	   $("#stateLevelMeetingUlId li").each(function() {
		  if($(this).find("input").is(":checked")){
			  partyMeetingTypeArr.push($(this).find("input").attr("id"));
		  }
	   }); 
	     var state = globalState
	    var dates=$("#dateRangeIdForMeetings").val();
		var fromDateStr;
		var toDateStr;
		if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			fromDateStr = datesArr[0]; 
			toDateStr = datesArr[1]; 
		}
		var jsObj ={ 
		             partyMeetingMainTypeId : 2,
					 state : state,
					 startDateString : fromDateStr,
					 endDateString : toDateStr,
					 partyMeetingTypeIds:partyMeetingTypeArr
					 
				  }
		$.ajax({
			type : 'POST',
			url : 'getPartyMeetingsMainTypeOverViewDataAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#stateLevelMeetingBasicCnt").html(" ");
		    if(result != null && result.length > 0){
				buildPartyMeetingOverviewRslt(result,"stateLevelMeetingBasicCnt",2,"stateLevelMeetingsExpandId");
			}else{
			  $("#stateLevelMeetingBasicCnt").html("NO DATA AVAILABLE.");	
			}
		});
	
}
function getPartySpecialMeetingsMainTypeOverview(){
	$("#specialMeetingBasicCnt").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	var partyMeetingTypeArr=[];
	   $("#specialMeetingUlId li").each(function() {
		  if($(this).find("input").is(":checked")){
			  partyMeetingTypeArr.push($(this).find("input").attr("id"));
		  }
	   }); 
	     var state = globalState;
	    var dates=$("#dateRangeIdForMeetings").val();
		var fromDateStr;
		var toDateStr;
		if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			fromDateStr = datesArr[0]; 
			toDateStr = datesArr[1]; 
		}
		var jsObj ={ 
		             partyMeetingMainTypeId : 3,
					 state : state,
					 startDateString : fromDateStr,
					 endDateString : toDateStr,
					 partyMeetingTypeIds:partyMeetingTypeArr
					 
				  }
		$.ajax({
			type : 'POST',
			url : 'getPartyMeetingsMainTypeOverViewDataAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#specialMeetingBasicCnt").html(" ");
		   if(result != null && result.length > 0){
			   buildPartyMeetingOverviewRslt(result,"specialMeetingBasicCnt",3,"specialMeetingsExpandId");
		   }else{
			$("#specialMeetingBasicCnt").html("NO DATA AVAILABLE.");	   
		   }
		});
	
}
function buildPartyMeetingOverviewRslt(result,divId,mainTypeMeetingId,expandTypeId){
	var partyMeetingTypeIdsString = 0;
	var count =0;
    
	var str='';
		   for(var i in result){
			   
			   if(count ==  0){
				   partyMeetingTypeIdsString = result[i].id ;
			   }else{
				   partyMeetingTypeIdsString = partyMeetingTypeIdsString + "," + result[i].id ;
			   }
			   count = count +1;
			   
			 str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
				 str+='<h4 attr_meeting_type_id="'+result[i].id+'" attr_main_type_meeting_id='+mainTypeMeetingId+'  class="text-capital meetingTypeCls">'+result[i].name+'<span class="stateGeneralMeeting" attr_meeting_type_id="'+result[i].id+'" attr_main_type_meeting_id='+mainTypeMeetingId+' style="background-color:#fff;"><i class="glyphicon glyphicon-fullscreen"></i></span></h4>';
				str+='<table class="table tableTraining bg_ED">';
					 str+='<tbody><tr>';
						 str+='<td>';
							 str+='<h4>'+result[i].noOfMeetings+'</h4>';
							  str+='<p class="text-muted text-capital">total meetings</p>';
						 str+='</td>';
						 str+='<td>';
						 str+='<h4>'+result[i].invitedCount+'<span class="font-10 text-success"></span></h4>';
						  str+='<p class="text-muted text-capital">Invited</p>';
						 str+='</td>';
						 str+='<td>';
							 str+='<h4>'+result[i].attendedCount+' <span class="font-10 text-success"> '+result[i].attendedPerc+'%</span></h4>';
							  str+='<p class="text-muted text-capital">Attended</p>';
						 str+='</td>';
						 str+='<td>';
							 str+='<h4>'+result[i].notAttendedCount+' <span class="font-10 text-danger"> '+result[i].notAttendedPerc+'%</span></h4>';
							  str+='<p class="text-muted text-capital">Absent</p>';
						 str+='</td>';
					 str+='</tr>';
				 str+='</tbody></table>';
				 str+='<hr class="m_0">';
			 str+='</div>';  
			  }
			 $("#"+expandTypeId).attr("attr_partyMeetingTypeIdsString",partyMeetingTypeIdsString); 
	$("#"+divId).html(str);  
}

function getParyMeetingTypeDetailsDistrictWise(mainMeetingTypeId,partyMeetingTypeIdsString){
	$("#districtWisePartyMeetingTypeDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	
	 var partyMeetingTypeArr = partyMeetingTypeIdsString.split(",");
	 
	     var state = globalState
	    var dates=$("#dateRangeIdForMeetings").val();
		var fromDateStr;
		var toDateStr;
		if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			fromDateStr = datesArr[0]; 
			toDateStr = datesArr[1]; 
		}
		var jsObj ={ 
		             partyMeetingMainTypeId : mainMeetingTypeId,
					 state : state,
					 startDateString : fromDateStr,
					 endDateString : toDateStr,
					 partyMeetingTypeIds:partyMeetingTypeArr
					 
				  }
		$.ajax({
			type : 'POST',
			url : 'getParyMeetingTypeDetailsDistrictWiseAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			 $("#districtWisePartyMeetingTypeDivId").html(' ');
				buildDistrictWisePartyMeetingTypeDtlsRslt(result);
		});
}
function buildDistrictWisePartyMeetingTypeDtlsRslt(result){
		$("#districtWisePartyMeetingTypeDivId").html('');
		if(result != null && result.length > 0){
			var str='';
			
			for(var i in result){
				str+=result[i].name;
				str+='<div id="districtWiseMeetingType'+i+'" class="chartLiD" style="height:300px" ></div>';
			}
									
		}
		$("#districtWisePartyMeetingTypeDivId").html(str);
		
	if(result != null && result.length > 0){
		for(var i in result){
			var districtNamesArray =[];
			var attendedCntPercArray = [];
			var notAttendedCntPerArray = [];
			var mayBeCountPerArray = [];
			if(result[i].districtList !=null && result[i].districtList.length > 0){
				for(var j in result[i].districtList){
						districtNamesArray.push(result[i].districtList[j].name);
							attendedCntPercArray.push(result[i].districtList[j].inviteeAttendedPerc);
							notAttendedCntPerArray.push(result[i].districtList[j].notAttendedPerc);
						//}
						//if(result[i].subList[j].notStartedPerc !=null && result[i].subList[j].notStartedPerc >0){
							//mayBeCountPerArray.push(result[i].partyMettingsVOList[j].mayBeCountPer);
						//}
					}
			}
						$(function () {
							$('#districtWiseMeetingType'+i+'').highcharts({
								colors: ['#53BF8B','#F56800'],
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
								series: [{
									name: 'Attended',
									data: attendedCntPercArray
								}, {
									name: 'Absent',
									data: notAttendedCntPerArray
								}]
							});
						});
				
			
		}
	}else{
		$("#districtWisePartyMeetingTypeDivId").html("No Data Available")
	}	
}

function getCommitteesAndPublicRepresentativeMembersInvitedAndAttendedToMeetings(partyMeetingMainTypeId,partyMeetingTypeIdsString){
	$("#userTypeWiseTopFiveStrongAndPoorMeetingMemsDivId").html(' ');
	$("#stateLevelMeetingBlockId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	var partyMeetingTypeArr = partyMeetingTypeIdsString.split(",");
	var state = globalState
	var dates=$("#dateRangeIdForMeetings").val();
	var fromDateStr;
	var toDateStr;
	if(dates != null && dates!=undefined){
		var datesArr = dates.split("-");
		fromDateStr = datesArr[0]; 
		toDateStr = datesArr[1]; 
	}
	var jsObj ={ 
				 partyMeetingMainTypeId : partyMeetingMainTypeId,
				 state : state,
				 startDateString : fromDateStr,
				 endDateString : toDateStr,
				 partyMeetingTypeIds:partyMeetingTypeArr
				 
			  }
	$.ajax({
		type : 'POST',
		url : 'getCommitteesAndPublicRepresentativeMembersInvitedAndAttendedToMeetingsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
	   buildCommitteesAndPublicRepresentativeMembersInvitedAndAttendedToMeetingsAction(result)
	});
}
function buildCommitteesAndPublicRepresentativeMembersInvitedAndAttendedToMeetingsAction(result)
{
  
   var stateLevelMemDtlsArr=[];
   var otherRslt;
   if(result != null ){
     stateLevelMemDtlsArr.push(result.subList1);
     stateLevelMemDtlsArr.push(result.subList2);
     otherRslt = result.subVO;
   }
   var str='';
   str+='<div class="col-md-12 col-xs-12 col-sm-12">';
     for(var i=0;i<stateLevelMemDtlsArr.length;i++){
     var subList = stateLevelMemDtlsArr[i];
     if(subList != null && subList.length > 0){
       for(var j in subList){
         str+='<h5 class="text-capital">'+subList[j].name+'</h5>';      
           str+='<div id="stateLevelMeetingGraph'+i+''+j+'" style="width:100%;height:80px;"></div>';
       }
     }
     }
     
     if(otherRslt != null ){
     str+='<h5 class="text-capital">'+otherRslt.name+'</h5>';      
     str+='<div id="stateLevelMeetingOtherId" style="width:100%;height:80px;"></div>';
      }
     str+='</div>'

  $("#stateLevelMeetingBlockId").html(str);
  for(var i=0;i<stateLevelMemDtlsArr.length;i++){
     var subList = stateLevelMemDtlsArr[i];
     if(subList != null && subList.length > 0){
        for(var j in subList){
      var stateLevelPerArr=[];
	  if(subList[j].invitedCount == 0){
		 stateLevelPerArr.push(0); 
	  }else{
		  stateLevelPerArr.push(100);  
	  }
      stateLevelPerArr.push(subList[j].attendedPerc);
      stateLevelPerArr.push(subList[j].notAttendedPerc);
       
      
      $('#stateLevelMeetingGraph'+i+''+j).highcharts({
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
        categories: ['invited','Attended','Absent'],
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
          //stacking: 'percent',
          dataLabels: {
            enabled: true,
             formatter: function() {
              if (this.y === 0) {
                return null;
              } else {
                return Highcharts.numberFormat(this.y) + '%';
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
        name: 'Members',
        data: stateLevelPerArr
      }]
    }); 
     }
  }
  }
  //for other
  var stateLevelOtherPerArr=[];
     if(otherRslt != null ){
	  if(otherRslt.invitedCount == 0){
		 stateLevelOtherPerArr.push(0); 
	  }else{
		  stateLevelOtherPerArr.push(100);  
	  }
      stateLevelOtherPerArr.push(otherRslt.attendedPerc);
      stateLevelOtherPerArr.push(otherRslt.notAttendedPerc);
      $('#stateLevelMeetingOtherId').highcharts({
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
        categories: ['invited','Attended','Absent'],
        title: {
          text: null
        },
        labels: {
            formatter: function() {
              return this.value.toString();
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
          //stacking: 'percent',
          dataLabels: {
            enabled: true,
               formatter: function() {
              if (this.y === 0) {
                return null;
              } else {
                return Highcharts.numberFormat(this.y) + '%';
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
        name: 'Members',
        data: stateLevelOtherPerArr
      }]
    }); 
  }      
}