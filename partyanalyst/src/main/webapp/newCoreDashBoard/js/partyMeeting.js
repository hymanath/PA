var customStartDateMeetings = moment().startOf('month').format('DD/MM/YYYY')
var customEndDateMeetings = moment().format('DD/MM/YYYY');

	$("#dateRangeIdForMeetings").daterangepicker({
		opens: 'left',
		startDate: moment().startOf('month'),
        endDate: moment().endOf('month'),
		locale: {
		  format: 'DD/MM/YYYY'
		},
		ranges: {
           'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		   'Last 3 Months': [moment().subtract(3, 'month'), moment()],
		   'Last 6 Months': [moment().subtract(6, 'month'), moment()],
		   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
           'This Month': [moment().startOf('month'), moment().endOf('month')],
           'This Year': [moment().startOf('Year'), moment()],
		   'Overall' : [moment().subtract(30, 'years').startOf('year'), moment()],
        }
	})
	var dates= $("#dateRangeIdForMeetings").val();
	//$("#dateMeetingHeadingId").html(" THIS MONTH ( "+customStartDate+" to "+customEndDate+" )");
	$("#dateMeetingHeadingId").html(" THIS MONTH ( "+dates+" )");
	var singleBlockDateStart = moment().startOf('month').format('MMM YY');
	var singleBlockDateEnd = moment().format('MMM YY');
	$('#dateRangeIdForMeetings').on('apply.daterangepicker', function(ev, picker) {
	  customStartDateMeetings = picker.startDate.format('DD/MM/YYYY');
	  customEndDateMeetings = picker.endDate.format('DD/MM/YYYY');
	  singleBlockDateStart = picker.startDate.format('MMM YY');
	  singleBlockDateEnd = picker.endDate.format('MMM YY');
	  //$("#dateMeetingHeadingId").html(picker.chosenLabel+" ( "+customStartDate+" to "+customEndDate+" )");
	  
	  //alert(customStartDateMeetings + "-" +customEndDateMeetings);
	  $(".meetingsHiddenBlock").show();
	  $(".stateGeneralMeeting,.stateLevelMeetingsExpand,.specialMeetings").find('i').removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen");
	  $(".showMoreBlockCls").attr("attr_main_type_meeting_id",1);//committee meeting
	  getPartyMeetingBasicCountDetails();
	  getUserTypeWiseMeetingCounductedNotCounductedMayBeDetailsCnt();
	  getPartySpecialMeetingsMainTypeOverview();
	  getPartyMeetingsMainTypeStateLevelOverview();
	  var dates= $("#dateRangeIdForMeetings").val();
	 // $("#dateMeetingHeadingId").html(" THIS MONTH ( "+dates+" )");
	  $("#dateMeetingHeadingId").html(picker.chosenLabel+" ( "+dates+" )");
	});
	//Changing date heading based on date selection
	/* var dateTextRange;
	$(document).on("click",".ranges li",function(){
	    dateTextRange = $(this).text();
		if(dateTextRange!="Custom Range"){
	    $("#dateMeetingHeadingId").html(" - "+dateTextRange);	
		}
	});
	var selectDates;
	$(document).on("click",".applyBtn",function(){
	  selectDates = $("#dateRangeIdForMeetings").val();
	  if(dateTextRange=="Custom Range"){
	    $("#dateMeetingHeadingId").html(" - "+selectDates);	
		}
    }); */
    $(document).on("click",".meetingGetDtlsBtncls",function(){
		var isChecked=false;
		 $("#committeeTypeId li").each(function() {
		  if($(this).find("input").is(":checked")){
			isChecked = true;
		  }
	   });
	    if(isChecked){
		$("#committeeMeetingErrorId").html(' ');	
		getPartyMeetingBasicCountDetails();
		getUserTypeWiseMeetingCounductedNotCounductedMayBeDetailsCnt();
		$(".settingsDropDown").hide();
		$("#committeeTypeDivId").hide(); 
	   }else{
		$("#committeeMeetingErrorId").html("Please select at least one meeting.");   
		return;
	   }
	
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
			//  setLastUpdatedTime(overAllResult.updatedTime);
			 str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
		      str+='<table class="table tableTraining">';
			  str+='<tbody><tr>';
				  str+='<td>';
					  str+='<h4>'+overAllResult.totalCount+'</h4>';
					  str+='<p class="text-muted text-capital">total</p>';
				  str+='</td>';
				  str+='<td>';
					  str+='<h4>'+overAllResult.conductedCount+'<span class="font-10 text-success"> '+overAllResult.conductedCountPer+'%</span></h4>';
					if(overAllResult.conductedCommentCnt > 0){
						str+='<p class="text-muted text-capital">Yes&nbsp&nbsp<a style="color: rgb(173, 173, 173);cursor: pointer;text-decoration: none;" attr_meeting_status="Y" attr_comment="yes" attr_level_type="overAll" class=" " data-toggle="tooltip" data-placement="top" title="Counducted Meeting Comment '+overAllResult.conductedCommentCnt+'('+overAllResult.conductedCommentCntPer+'$)" data-original-title=""></a></p>';  }
					else{
					  str+='<p class="text-muted text-capital">Yes</p>';	  
					  }
				  str+='</td>';
				  str+='<td>';
					  str+='<h4>'+overAllResult.notConductedCount+'<span class="font-10 text-danger"> '+overAllResult.notConductedCountPer+'%</span></h4>';
					if(overAllResult.notConductedCommentCnt > 0){
					str+='<p class="text-muted text-capital">no&nbsp&nbsp<a style="color: rgb(173, 173, 173);cursor: pointer;text-decoration: none;" attr_meeting_status="N" attr_comment="yes"  attr_level_type="overAll" class=" " data-toggle="tooltip" data-placement="top" title="Not Counducted Meeting Comment '+overAllResult.notConductedCommentCnt+'('+overAllResult.notConductedCommentCntPer+'%)" data-original-title=""></a></p>';  
					}else{
					 str+='<p class="text-muted text-capital">no</p>';	  
					 }
					
				  str+='</td>';
				  str+='<td>';
					  str+='<h4>'+overAllResult.mayBeCount+'<span class="font-10 text-customColor"> '+overAllResult.mayBeCountPer+'%</span></h4>';
					  if(overAllResult.mayBeCommentCnt > 0){
						str+='<p class="text-muted text-capital">maybe&nbsp&nbsp<a attr_meeting_status="M" attr_level_type="overAll" attr_comment="yes" style="color: rgb(173, 173, 173);cursor: pointer;text-decoration: none;" class="" data-toggle="tooltip" data-placement="top" title="Maybe Meeting Comment '+overAllResult.mayBeCommentCnt+'('+overAllResult.mayBeCommentCntPer+')" data-original-title=""></a></p>';
						}
					else{
					  str+='<p class="text-muted text-capital">maybe</p>';	  
					  }
				   str+='</td>';
				    str+='<td>';
						 str+='<h4>'+overAllResult.totalNotUpdatedCnt+' <span class="font-10 text-customColor"> '+overAllResult.totalNotUpdatedCntPer+'%</span></h4>';
					 if(overAllResult.notUpdatedCommentCnt > 0){
						str+='<p class="text-muted text-capital">Not Updated&nbsp&nbsp<a style="color: rgb(173, 173, 173);cursor: pointer;text-decoration: none;" attr_meeting_status="NU" attr_level_type="overAll" attr_comment="yes" class="" data-toggle="tooltip" data-placement="top" title="Not Updated Meeting Comment '+overAllResult.notUpdatedCommentCnt+'('+overAllResult.notUpdatedCommentCntPer+')" data-original-title=""></a></p>';  }else{
					  str+='<p class="text-muted text-capital">Not Updated</p>';	  
					  }
				str+='</td>';
			  str+='</tr>';
	       str+='</tbody></table>';
		  str+='<hr class="m_0">';
		  str+='</div>';  
		  }
		 if(levelWiseResult != null && levelWiseResult.length > 0){
		   for(var i in levelWiseResult){
			 str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
				 str+='<h4 class="text-capitalize">'+levelWiseResult[i].name+'<small class="meetingsInnerBlock"></small></h4>';
				str+='<table class="table tableTraining bg_ED">';
					 str+='<tbody><tr>';
						 str+='<td>';
							 str+='<p class="text-muted text-capitalize">total</p>';
							 str+='<h4>'+levelWiseResult[i].totalCount+'</h4>';
						 str+='</td>';
					   str+='<td>';
					  if(levelWiseResult[i].conductedCommentCnt > 0 && levelWiseResult[i].conductedCount > 0){
						str+='<p class="text-muted text-capital">Yes&nbsp&nbsp<a attr_meeting_status="Y" attr_comment="yes" attr_level_type="'+levelWiseResult[i].name+'" style="color: rgb(173, 173, 173);cursor: pointer;text-decoration: none;" class="glyphicon glyphicon-comment overAllMeetingCls" data-toggle="tooltip" data-placement="top" title="Counducted Meeting Comment '+levelWiseResult[i].conductedCommentCnt+'('+levelWiseResult[i].conductedCommentCntPer+'%)" data-original-title=""></a></p>';  
						str+='<h4>'+levelWiseResult[i].conductedCount+'<span class="font-10 text-success"> '+levelWiseResult[i].conductedCountPer+'%</span></h4>';
				          }else if(levelWiseResult[i].conductedCount > 0){
						 str+='<p class="text-muted text-capitalize">yes</p>';	  
						 str+='<h4 attr_meeting_status="Y" attr_comment="No" attr_level_type="'+levelWiseResult[i].name+'" style="cursor: pointer;" class=" overAllMeetingCls">'+levelWiseResult[i].conductedCount+'<span class="font-10 text-success"> '+levelWiseResult[i].conductedCountPer+'%</span></h4>';
						 }else{
						 str+='<p class="text-muted text-capitalize">yes</p>';	  
						 str+='<h4>'+levelWiseResult[i].conductedCount+'<span class="font-10 text-success"> '+levelWiseResult[i].conductedCountPer+'%</span></h4>';
						 }
						 str+='</td>';  
						 	str+='<td>';
						   if(levelWiseResult[i].notConductedCommentCnt > 0 && levelWiseResult[i].notConductedCount > 0){
							str+='<p class="text-muted text-capital">no&nbsp&nbsp<a attr_meeting_status="N" attr_comment="yes" attr_level_type="'+levelWiseResult[i].name+'" style="color: rgb(173, 173, 173);cursor: pointer;text-decoration: none;" class="glyphicon glyphicon-comment overAllMeetingCls" data-toggle="tooltip" data-placement="top" title="Not Counducted Meeting Comment '+levelWiseResult[i].notConductedCommentCnt+'('+levelWiseResult[i].notConductedCommentCntPer+'%)" data-original-title=""></a></p>'; 
                            str+='<h4>'+levelWiseResult[i].notConductedCount+' <span class="font-10 text-danger"> '+levelWiseResult[i].notConductedCountPer+'%</span></h4>';		
                     		}else if(levelWiseResult[i].notConductedCount > 0){
							  str+='<p class="text-muted text-capital">no</p>';	 
							  str+='<h4 attr_meeting_status="N" attr_comment="No" attr_level_type="'+levelWiseResult[i].name+'" style="cursor: pointer;" class="overAllMeetingCls">'+levelWiseResult[i].notConductedCount+' <span class="font-10 text-danger"> '+levelWiseResult[i].notConductedCountPer+'%</span></h4>';	
						     }else{
								  str+='<p class="text-muted text-capital">no</p>';	 
                                  str+='<h4>'+levelWiseResult[i].notConductedCount+' <span class="font-10 text-danger"> '+levelWiseResult[i].notConductedCountPer+'%</span></h4>';	
							}
						 str+='</td>';
						 	str+='<td>';
						    if(levelWiseResult[i].mayBeCommentCnt > 0 && levelWiseResult[i].mayBeCount > 0){
								str+='<p class="text-muted text-capital">maybe&nbsp&nbsp<a attr_meeting_status="M" attr_level_type="'+levelWiseResult[i].name+'" style="color: rgb(173, 173, 173);cursor: pointer;text-decoration: none;" attr_comment="yes" class="glyphicon glyphicon-comment overAllMeetingCls" data-toggle="tooltip" data-placement="top" title="Maybe Meeting Comment '+levelWiseResult[i].mayBeCommentCnt+'('+levelWiseResult[i].mayBeCommentCntPer+'%)" data-original-title=""></a></p>';  
						         str+='<h4>'+levelWiseResult[i].mayBeCount+' <span class="font-10 text-customColor"> '+levelWiseResult[i].mayBeCountPer+'%</span></h4>';
							  }else if(levelWiseResult[i].mayBeCount > 0){
								 str+='<p class="text-muted text-capital">maybe</p>';	
	                             str+='<h4 attr_meeting_status="M" attr_level_type="'+levelWiseResult[i].name+'" style="cursor: pointer;" attr_comment="No" class="overAllMeetingCls" >'+levelWiseResult[i].mayBeCount+' <span class="font-10 text-customColor"> '+levelWiseResult[i].mayBeCountPer+'%</span></h4>';
							  }else{
								 str+='<p class="text-muted text-capital">maybe</p>';	
	                             str+='<h4>'+levelWiseResult[i].mayBeCount+' <span class="font-10 text-customColor"> '+levelWiseResult[i].mayBeCountPer+'%</span></h4>';
							  }
							str+='</td>';
						str+='<td>';
					   if(levelWiseResult[i].notUpdatedCommentCnt > 0 && levelWiseResult[i].totalNotUpdatedCnt > 0){
						str+='<p class="text-muted text-capital">Not Updated&nbsp&nbsp<a attr_meeting_status="NU" attr_level_type="'+levelWiseResult[i].name+'" attr_comment="yes" style="color: rgb(173, 173, 173);cursor: pointer;text-decoration: none;" class="glyphicon glyphicon-comment overAllMeetingCls" data-toggle="tooltip" data-placement="top" title="Not Updated Meeting Comment '+levelWiseResult[i].notUpdatedCommentCnt+'('+levelWiseResult[i].notUpdatedCommentCntPer+'%)" data-original-title=""></a></p>'; 
					     str+='<h4>'+levelWiseResult[i].totalNotUpdatedCnt+' <span class="font-10 text-customColor"> '+levelWiseResult[i].totalNotUpdatedCntPer+'%</span></h4>';
					     }else if(levelWiseResult[i].totalNotUpdatedCnt > 0){
						  str+='<p class="text-muted text-capital">Not Updated</p>';	 
	                      str+='<h4 class="overAllMeetingCls" attr_meeting_status="NU" attr_level_type="'+levelWiseResult[i].name+'" attr_comment="No" style="cursor: pointer;">'+levelWiseResult[i].totalNotUpdatedCnt+' <span class="font-10 text-customColor"> '+levelWiseResult[i].totalNotUpdatedCntPer+'%</span></h4>';
						  }else {
						  str+='<p class="text-muted text-capital">Not Updated</p>';	 
	                      str+='<h4>'+levelWiseResult[i].totalNotUpdatedCnt+' <span class="font-10 text-customColor"> '+levelWiseResult[i].totalNotUpdatedCntPer+'%</span></h4>';
				 		  }
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
	   $('[data-toggle="tooltip"]').tooltip()
	 // $(".meetingsInnerBlock").html("( "+customStartDateMeetings+" to "+customEndDateMeetings+" )");
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
		if(result[i][0].conductedAndMayBeMeetingPer!=0){
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
				pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}%</b>'
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
					name: 'Conducted',
					data: meetingCounductedAndMayBePerArray
				}]
			});
		});
		}else{
		$("#genSecMeeting"+i).html("No Data Available");
		$("#genSecMeeting"+i).css("height","35px");
		} 
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
				if(result[i] != null && result[i].length > 0){
					var length = result[i].length - 1;
					for(var j = length; j >= 0; j--){
						candidateNameArray.push(result[i][j].name);
						meetingCounductedAndMayBePerArray.push(result[i][j].conductedAndMayBeMeetingPer);
						countVar =countVar+1;
						if (countVar === 5) {
							break;
						}
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
				pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}%</b>'
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
		$(".meetingHead").toggleClass('col-md-9 col-sm-9').toggleClass('col-md-8 col-sm-8');
		$(".meetingHead1").toggleClass('col-md-3 col-sm-3').toggleClass('col-md-4 col-sm-4');
		$(".showMoreBlockCls").attr("attr_main_type_meeting_id",1);//committee meeting
		$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".meetingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".meetingsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
		$(".meetingsHiddenBlock,.moreMeetingsBlocksIcon").toggle();
		$(".meetingsHiddenBlock").find("i").show();
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
			$(".debatesMoreHiddenBlock,.debatesHiddenBlock").hide();
			$(".dateRangePickerClsForDebates").toggleClass("hide");
			$(".moreDebatesBlocksIcon").removeClass("unExpandDebatesBlock");
			$(".debatesBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".moreMeetingsBlocks1").hide();
			$(".moreMeetingsBlocksDetailed").hide();
			$(".moreMeetingsBlocksComparision").hide();
		}else if( $(".iconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".iconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".committeesHiddenBlock,.moreBlocks,.moreBlocks1,.moreBlocksDetailAndComp,.moreBlocksIcon,.moreBlocksDistrictlevel").hide();
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
			$(".moreMeetingsBlocksDetailed,.moreMeetingsBlocks1").hide();
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
		 if(result != null){
		   $("#meetingLevelHIghChartsDivId").html(' ');
			buildLevelWiseHighCharts(result);
		 }else{
		  $("#meetingLevelHIghChartsDivId").html("No Data Available"); 
		 }
		});	
}
function buildLevelWiseHighCharts(result){
	var levelWiseResult = result.partyMettingsVOList;
	var resultVO = result.overAllVO;
	  // setLastUpdatedTime(resultVO.updatedTime);
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
													return Highcharts.numberFormat(this.y,1) +"%";
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
		$("#meetingLevelHIghChartsDivId").html("No Data Available");
	}
}
 $(document).on("click",".childUserTypeClsForMeeting",function(){
	var childUserTypeIdString = $(this).attr("attr_userTypeId");
	var childUserType = $(this).attr("attr_userType");
	getSelectedChildUserTypeMembersWithMeetingsCount(childUserTypeIdString,childUserType);
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
		if(selectedUserType != null && selectedUserType.trim()=="MLA/CI" || selectedUserType.trim()=="MLA" || selectedUserType.trim()=="CONSTITUENCY INCHARGE"){
		  getTopPoorMeetingLocations(activityMemberId,selectedMemberName,selectedUserType); 
		 }else{
	      getDirectChildActivityMemberMeetingDetails(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId);
		  getTopPoorMeetingLocations(activityMemberId,selectedMemberName,selectedUserType);
		}
		
	});
 $(document).on("click",".compareLowLevelActivityMeetingMemberCls",function(){
	$(this).next('tr.showHideTr').show(); 
	 
	var activityMemberId = $(this).attr("attr_activitymemberid");  
	var userTypeId = $(this).attr("attr_usertypeid"); 
	var selectedMemberName = $(this).attr("attr_selectedmembername");  
	var selectedUserType = $(this).attr("attr_selectedusertype");  
	var childActivityMemberId = $(this).closest('tr').next('tr.showHideTr').attr("attr_id");  
	if(selectedUserType != null && selectedUserType.trim()=="MLA/CI" || selectedUserType.trim()=="MLA" || selectedUserType.trim()=="CONSTITUENCY INCHARGE"){
		  getTopPoorMeetingLocations(activityMemberId,selectedMemberName,selectedUserType); 
		 }else{
	      getDirectChildActivityMemberMeetingDetails(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId);
		  getTopPoorMeetingLocations(activityMemberId,selectedMemberName,selectedUserType);
		}

});
	
function getAllItsSubUserTypeIdsByParentUserTypeIdForMeeting(){
		
		var jsObj = { parentUserTypeId : globalUserTypeId }
		$.ajax({
			type : 'POST',
			url : 'getAllItsSubUserTypeIdsByParentUserTypeIdAction.action',
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
		 
		 var firstChildUserTypeIdString;
		 
		 if(result !=null && result.length >0){
			 firstChildUserTypeIdString = result[0].shortName;
			 for(var i in result){
				 str+='<li attr_usertypeid="'+result[i].shortName+'" attr_userType=\''+result[i].userType+'\'  class="childUserTypeClsForMeeting">'+result[i].userType+'<span class="closeIconComparison"></span></li>';
			 }
		 }
		str+='</ul>';
		$("#childUserTypeDetailsDivIdForMeeting").html(str);
		$(".comparisonSelect li:first-child").addClass("active")
		
		getSelectedChildUserTypeMembersWithMeetingsCount(firstChildUserTypeIdString," ");
		
	}
	function getSelectedChildUserTypeMembersWithMeetingsCount(childUserTypeIdString,userType){
		 $("#childActivityMemberDivIdForMeeting").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	  var parentActivityMemberId = globalActivityMemberId;
	  var childUserTypeIdsArray = childUserTypeIdString.split(",");
	  
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
				   childUserTypeIdsArray : childUserTypeIdsArray,
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
		   $("#directChildActivityMeetingMemberDiv").html(' ');
		  if(result != null && result.length > 0){
			  buildgetSelectedChildUserTypeMembersForMeeting(result,userType);
		  }else{
			  $("#childActivityMemberDivIdForMeeting").html("NO DATA AVAILABLE");
		  }
		});
		
	}
	function buildgetSelectedChildUserTypeMembersForMeeting(result,childUserType){
	var str='';
		     var firstChildActivityMemberId = "directChildActivityMeetingMemberDiv";
			var firstActivityMemberId = result[0].activityMemberId;
			var firstUserTypeId = result[0].userTypeId;
			var firstuserType = result[0].userType;
			var firstUserMemberName = result[0].name;
	if(childUserType != null && childUserType.trim()=="MLA/CI" || childUserType.trim()=="MLA" || childUserType.trim()=="CONSTITUENCY INCHARGE"){
	     str+='<table style="background-color:#EDEEF0;border:1px solid #ddd" class="table table-condensed tableHoverLevels" id="meetingMemberDtlsDataTblId">';
		 str+='<thead>';
		     str+='<th>Rank</th>';
			 str+='<th>Name</th>';
			 str+='<th>Designation</th>';
			 str+='<th>Location</th>';
			 str+='<th>Total</th>';
			 str+='<th>Yes</th>';
			 str+='<th>%</th>';
			 str+='<th>No</th>';
			 str+='<th>%</th>';
			 str+='<th>Maybe</th>';
			 str+='<th>%</th>';
		 str+='</thead>';
		 str+='<tbody>';
		 var rank=1;
		  for(var i in result){
			str+='<tr style="cursor:pointer;" class="compareActivityMemberClsForMeeting"  attr_selectedusertype="'+result[i].userType+'"  attr_id="directChildActivityMeetingMemberDiv"  attr_selectedmembername="'+result[i].name+'"  attr_activitymemberid='+result[i].activityMemberId+'  attr_usertypeid='+result[i].userTypeId+'>';
			 str+='<td><span class="counts">'+rank+'</span></td>';
			 str+='<td>'+result[i].name+'</td>';
			 str+='<td>'+result[i].userType+'</td>';
			 str+='<td>'+result[i].locationName+'</td>';
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
             rank=rank+1;			 
			}
			 str+='</tbody>';
			 str+='</table>';
	     $("#childActivityMemberDivIdForMeeting").html(str);
		  $("#meetingMemberDtlsDataTblId").dataTable({
			"aaSorting": [],
			"iDisplayLength" : 5	
		   });
	 getTopPoorMeetingLocations(firstActivityMemberId,firstUserMemberName,firstuserType);
	  }else{
	   if(result !=null && result.length >0){
		str+='<ul class="list-inline slickPanelSliderMeeting">';
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
					
					var userTypeId = result[i].userTypeId;
					 if(userTypeId!=5 && userTypeId!=6 && userTypeId!=7 && userTypeId!=9){//General Secretery or Organising secretery/secretery.
						 str+='<h4 class="text-capital">'+result[i].userType+'</h4>';
					 }else{//district President or Mp or MLA or Constituency Incharge
					     var locationName = result[i].locationName.split(" ")[0];
						 str+='<h4 class="text-capital">'+result[i].userType+' (' + locationName+ ' ) </h4>';
					 }	
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
			getDirectChildActivityMemberMeetingDetails(firstActivityMemberId,firstUserTypeId,firstUserMemberName,firstuserType,firstChildActivityMemberId);
			getTopPoorMeetingLocations(firstActivityMemberId,firstUserMemberName,firstuserType);
	}else{
	 $("#childActivityMemberDivIdForMeeting").html("No Data Available");
	}
	}
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
				str+='<span class="removeSelectTr pull-right" attr_removeSelecUserType = "'+childActivityMemberId+'" style="margin-top: -5px;"><i class="glyphicon glyphicon-remove"></i></span>';
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
				$("#"+childActivityMemberId).html("<h5><span  class='text-capital'>"+selectedMemberName+"</span> - <span class='text-capitalize'>"+selectedUserType+"</span> - ( No Data Available )</h5>");
			}		
		}
	}
	
	$(document).on("click",".removeSelectTr",function(){
		var removeSelected = $(this).attr("attr_removeSelecUserType"); 
		$("#"+removeSelected).html(' ');
		$("#"+removeSelected).hide();
	});
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
					str+='<p class="text-capital"><b>'+result[0].requiredName+'</b><span style="margin-left:150px">Conducted Percentage<span></p>';
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
							str+='<td class="text-danger">'+result[i].conductedCount+'('+(result[i].conductedPerc+"%")+')</td>';
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
													return Highcharts.numberFormat(this.y,1) +"%";
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
	//getChildUserTypesByItsParentUserTypeForMeeting();
	getAllItsSubUserTypeIdsByParentUserTypeIdForMeeting();
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
	    var isChecked=false; 
		 $("#stateLevelMeetingUlId li").each(function() {
		  if($(this).find("input").is(":checked")){
			isChecked = true;
		  }
	   });
	   if(isChecked){
		$("#stateLevelMeetingErrorId").html(' ');
		getPartyMeetingsMainTypeStateLevelOverview();
		$(".settingsStateLevelMeetingDropDown").hide();
		$("#stateLevelMeetingDivId").hide();
		$(".moreMeetingsBlocksDetailed").hide();   
	   }else{
	    $("#stateLevelMeetingErrorId").html("Please select at least one meeting.");
        return;		
	   }
	
});
 $(document).on("click",".specialMeetingBtncls",function(){
	 var isChecked=false; 
		 $("#specialMeetingUlId li").each(function() {
		  if($(this).find("input").is(":checked")){
			isChecked = true;
		  }
	   });
	   if(isChecked){
		 $("#specialMeetingErrorId").html(' ');
		 getPartySpecialMeetingsMainTypeOverview(); 
		 $(".specialMeetingDropDown").hide();
		 $("#specialMeetingDivId").hide();
		 $(".moreMeetingsBlocksDetailed").hide();   
	   }else{
		$("#specialMeetingErrorId").html("Please select at least one meeting.");
		return;
	   }
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
			  $("#stateLevelMeetingBasicCnt").html("<div class='col-md-12 col-xs-12 col-sm-12'>NO DATA AVAILABLE.</div>");	
			  $("#stateLevelMeetingBasicCnt").closest(".panelBlock").hide();
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
			$("#specialMeetingBasicCnt").closest(".panelBlock").hide();
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
				buildDistrictWisePartyMeetingTypeDtlsRslt(result,mainMeetingTypeId,partyMeetingTypeIdsString);
		});
}
function buildDistrictWisePartyMeetingTypeDtlsRslt(result,mainMeetingTypeId,partyMeetingTypeIdsString){
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
			var districtIdArr = [];
			var districtNamesArray =[];
			var attendedCntPercArray = [];
			var notAttendedCntPerArray = [];
			var mayBeCountPerArray = [];
			if(result[i].districtList !=null && result[i].districtList.length > 0){
				for(var j in result[i].districtList){
						districtNamesArray.push(result[i].districtList[j].name);
						districtIdArr.push(result[i].districtList[j].id);
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
													return Highcharts.numberFormat(this.y,1) +"%";
												}
											}
										  
										}
									}
								},
								series: [{
									name: 'Invitees Attended',
									data: attendedCntPercArray
								}, {
									name: 'Absent',
									data: notAttendedCntPerArray
								}]
							});
						});
						$.each($('#districtWiseMeetingType'+i+'').find(".highcharts-xaxis-labels").find("tspan"),function(index,item){ 
							$(this).attr("style","cursor:pointer;");
							$(this).attr("class","distDtlsMeetingCls");    
							$(this).attr("attr_meeting_type_id",mainMeetingTypeId);
							$(this).attr("attr_dist_id",districtIdArr[index]);   
							$(this).attr("attr_meeting_type_ids",partyMeetingTypeIdsString);
						});    
			
		}
	}else{
		$("#districtWisePartyMeetingTypeDivId").html("No Data Available")
	}	
}
$(document).on("click",".distDtlsMeetingCls",function(){ 
	$("#myModelId").modal('show');
	$("#myModalLabel").html('Meeting Member Details');    
	
	$("#positionId").html('');  
	$("#memberId").html(''); 
	$("#processingImgId").show();	  
	$("#processingImgId").html('<div><center><img style="height:20px" src="images/icons/loading.gif"></center></div>');
	var distId = $(this).attr("attr_dist_id");
	var mainMeetingTypeId = $(this).attr("attr_meeting_type_id");
	var partyMeetingTypeIdsString = $(this).attr("attr_meeting_type_ids");
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
		partyMeetingTypeIds:partyMeetingTypeArr,
		distId : distId  
	}
	$.ajax({
		type : 'POST',     
		url : 'getParyMeetingTypeDetailsPerDistrictAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		
		$("#processingImgId").hide();    
		if(result != null && result.length > 0){
			buildParyMeetingTypeDetailsPerDistrict(result);
				
		}else{
			$("#memberId").html('No Data Available');     
		}
	});
	
});
function buildParyMeetingTypeDetailsPerDistrict(result){
	var str2 = '';
	var totalMember = result.length;  
	var attendedMember = 0;
	var absent = 0;
	
	var str = '';
	str+='<table class="table table-condensed" id="meetingMemberDtlsId">';
	str+='<thead>';
		str+='<th>NAME</th>';
		str+='<th>DESIGNATION</th>';
		str+='<th>CONTACT NUMBER</th>'; 
		str+='<th>MEETING NAME</th>';
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
			if(result[i].applicationStatus != null){
				str+='<td>'+result[i].applicationStatus.toUpperCase()+'</td>';
			}else{
				str+='<td>ABSENT</td>';      
			}
			
		
		str+='</tr>';     
	}
	absent = totalMember - attendedMember;  
	str2+='<span class="label label-primary">All-'+totalMember+'</span>'; 
	str2+='<span class="label label-default">Attended-'+attendedMember+'</span>';  
	
	str2+='<span class="label label-warning">Absent-'+absent+'</span>';  
	
	str+='</tbody>';  
	$("#positionId").html(str2);
	$("#memberId").html(str);   
	$("#meetingMemberDtlsId").dataTable();    
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
		   if( subList[j].invitedCount == 0 && subList[j].attendedCount == 0 && subList[j].notAttendedCount==0){
			   str+='<h5 class="text-capital"></h5>'; 
		   }else{
			    str+='<h5 class="text-capital">'+subList[j].name+'</h5>'; 
				str+='<div id="stateLevelMeetingGraph'+i+''+j+'" style="width:100%;height:80px;"></div>';
		   }
            
           
       }
     }
     }
     
     if(otherRslt != null ){
		  if(otherRslt.invitedCount == 0 && otherRslt.attendedCount == 0 && otherRslt.notAttendedCount==0){
			  str+='<h5 class="text-capital"></h5>'; 
		  }else{
			   str+='<h5 class="text-capital">'+otherRslt.name+'</h5>';
			str+='<div id="stateLevelMeetingOtherId" style="width:100%;height:80px;"></div>';
		  }
     
    
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
       
      if( subList[j].invitedCount == 0 && subList[j].attendedCount == 0 && subList[j].notAttendedCount==0){
		 //$('#stateLevelMeetingGraph'+i+''+j).html("<h5>No Data Available</h5>");
	  }else{
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
				data: stateLevelPerArr
			  }]
			}); 
	  }
	
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
	  
	  if(otherRslt.invitedCount == 0 && otherRslt.attendedCount == 0 && otherRslt.notAttendedCount==0){
		  //$('#stateLevelMeetingOtherId').html("<h5> No Data Available </h5>");
	  }else{
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
}
getMeetingRecentTime();
window.setInterval(function(){
  getMeetingRecentTime(); 
},10*60*1000);/*every 10 minutes .this method will update time  */
function getMeetingRecentTime(){
 	$.ajax({
		type : 'POST',
		url : 'getMeetingRecentTimeAction.action',
		dataType : 'json',
		data : {task:JSON.stringify( )}
	}).done(function(result){
		if(result != null){
		 setLastUpdatedTime(result)	
		}
	});
}
function setLastUpdatedTime(lastUpdatedTime){
 $("#lastMeetingUpdatedIdTimeId").html(" Last Updated : "+lastUpdatedTime+"");
}


/* Meeting Comment Block Start */
function getDistrictByState(meetingStatus,meetingLevel,isComment){
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
			fromDateStr = datesArr[0].trim(); 
			toDateStr = datesArr[1].trim(); 
		}	
           var jsObj ={ 
		             activityMemberId : globalActivityMemberId,
					 stateId : globalStateId,
					 fromDate : fromDateStr,
					 toDate : toDateStr,
					 partyMeetingTypeArr:partyMeetingTypeArr,
					 meetingStatus : meetingStatus,
					 meetingLevel : meetingLevel,
					 isComment : isComment
				  }
		$.ajax({
			type : 'POST',
			url : 'getDistrictByStateAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			 $("#districtSlctBxId").empty();
			if(result != null && result.length > 0){
				$("#districtSlctBxId").append('<option value="0">Select District</option>');
				for(var i in result){
				 $("#districtSlctBxId").append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
				}
			}
		});  	
}
$(document).on("change","#districtSlctBxId",function(){
	    var districtId = $(this).val();
	    var meetingStatus = $(this).attr("attr_meeting_status");
	    var meetingLevel = $(this).attr("attr_level_type");
		var isComment = $(this).attr("attr_comment");
	    getConstituencyByDistrict(meetingStatus,meetingLevel,isComment,districtId);
});
$(document).on("change","#constituencySlctBxId",function(){
	    var constituencyId = $(this).val();
	    var meetingStatus = $(this).attr("attr_meeting_status");
	    var meetingLevel = $(this).attr("attr_level_type");
		var isComment = $(this).attr("attr_comment");
        getMandalByConstituency(meetingStatus,meetingLevel,isComment,constituencyId);	
});
function getConstituencyByDistrict(meetingStatus,meetingLevel,isComment,districtId){
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
			fromDateStr = datesArr[0].trim(); 
			toDateStr = datesArr[1].trim(); 
		}	
           var jsObj ={ 
		             activityMemberId : globalActivityMemberId,
					 stateId : globalStateId,
					 fromDate : fromDateStr,
					 toDate : toDateStr,
					 partyMeetingTypeArr:partyMeetingTypeArr,
					 meetingStatus : meetingStatus,
					 meetingLevel : meetingLevel,
					 isComment : isComment,
					 districtId : districtId
				  }
		$.ajax({
			type : 'POST',
			url : 'getConstituencyByDistrictAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			 $("#constituencySlctBxId").empty();
			  $("#mandalSlctBxId").empty();
			  $("#mandalSlctBxId").append('<option value="0">Select Mandal/Town/Division</option>');
			if(result != null && result.length > 0){
				$("#constituencySlctBxId").append('<option value="0">Select Constituency</option>');
				for(var i in result){
				 $("#constituencySlctBxId").append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
				}
			}
		});  	
}
$(document).on("click",".modalCloseCls",function(){
setTimeout(function(){
 $('body').addClass("modal-open");
}, 500);
});
function getMandalByConstituency(meetingStatus,meetingLevel,isComment,constituencyId){
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
			fromDateStr = datesArr[0].trim(); 
			toDateStr = datesArr[1].trim(); 
		}	
           var jsObj ={ 
		             activityMemberId : globalActivityMemberId,
					 stateId : globalStateId,
					 fromDate : fromDateStr,
					 toDate : toDateStr,
					 partyMeetingTypeArr:partyMeetingTypeArr,
					 meetingStatus : meetingStatus,
					 meetingLevel : meetingLevel,
					 isComment : isComment,
					 constituencyId : constituencyId
				  }
		$.ajax({
			type : 'POST',
			url : 'getMandalByConstituencyAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			 $("#mandalSlctBxId").empty();
			if(result != null && result.length > 0){
				 $("#mandalSlctBxId").append('<option value="0">Select Mandal/Town/Division</option>');
				for(var i in result){
				  $("#mandalSlctBxId").append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
				}
			}
		});  	
}
 $(document).on("click","#getDetailsBtnId",function(){
	    var meetingStatus = $(this).attr("attr_meeting_status");
	    var meetingLevel = $(this).attr("attr_level_type");
		var isComment = $(this).attr("attr_comment");
		var reportTypeId = $("#commentFilterSelectBoxId").val();
		var dates=$("#dateRangeIdForMeetings").val();
		//var resultType = $(this).attr("attr_result_type");
		var reportType;
		if(reportTypeId == 0){
		reportType = "District"; 
		 }else if(reportTypeId == 1){
			reportType = "Constituency";  
		 }
		var fromDateStr;
		var toDateStr;
		if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			fromDateStr = datesArr[0].trim(); 
			toDateStr = datesArr[1].trim(); 
		}	
		var locationId;
		var locationType;
		var districtId = $("#districtSlctBxId").val();
	    var constituencyId = $("#constituencySlctBxId").val();
		var mandalId = $("#mandalSlctBxId").val();
		  if(districtId == 0 && constituencyId ==0 && mandalId ==0){
			 $(".districtFilterErrrorCls").html("Please Select Search Type.");
             return;			 
		  }
		 $(".districtFilterErrrorCls").html(' ');
	
		  if(districtId > 0){
		  locationId = districtId; 
		  locationType = "District";
		  }
		  if(constituencyId > 0){
		  locationId = constituencyId; 
		  locationType = "Constituency";
		  }
		  if(mandalId > 0){
		  locationId = mandalId; 
		  locationType = "Mandal";
		  }
	/* 	if(resultType == "Individual"){
		 getMeetingComments(meetingStatus,meetingLevel,isComment,fromDateStr,toDateStr,locationId,locationType,"meetingCommentDtlsTblId","directRslt");	
		}else{ */
		 var isCheckedConslidated=$("#ConsolidatedradioId").is(':checked');
	     var isCheckedIndividual=$("#individualradioId").is(':checked');
        if(isCheckedIndividual){
		getMeetingComments(meetingStatus,meetingLevel,isComment,fromDateStr,toDateStr,locationId,locationType,"meetingCommentDtlsTblId","directRslt");	
		} 
	   if(isCheckedConslidated){
		getPartyMeetingComulativeCommentDetails(meetingStatus,meetingLevel,isComment,fromDateStr,toDateStr,reportType,locationId,locationType);  	
		} 	
	//	}
 });
 $(document).on("click","#ConsolidatedradioId",function(){
        var meetingStatus = $(this).attr("attr_meeting_status");
	    var meetingLevel = $(this).attr("attr_level_type");
		var isComment = $(this).attr("attr_comment");
		var dates=$("#dateRangeIdForMeetings").val();
		var reportTypeId = $("#commentFilterSelectBoxId").val();
		var fromDateStr;
		var toDateStr;
		var reportType;
		if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			fromDateStr = datesArr[0].trim(); 
			toDateStr = datesArr[1].trim(); 
		}	
	 if(reportTypeId == 0){
		reportType = "District"; 
	 }else if(reportTypeId == 1){
		reportType = "Constituency";  
	 }
	 $("#getDetailsBtnId").attr("attr_comment","No");
	 $("#districtSlctBxId").attr("attr_comment","No");		 
     $("#constituencySlctBxId").attr("attr_comment","No");	
	  getDistrictByState(meetingStatus,meetingLevel,"No");	
      getConstituencyByDistrict(meetingStatus,meetingLevel,"No",0);
      getMandalByConstituency(meetingStatus,meetingLevel,"No",0);	
	 //$("#getDetailsBtnId").attr("attr_result_type","consolidated");
	if($(this).is(':checked')){
		 getPartyMeetingComulativeCommentDetails(meetingStatus,meetingLevel,isComment,fromDateStr,toDateStr,reportType,0," ");  
	 } 
	 
 });
 $(document).on("click","#individualradioId",function(){
	    var meetingStatus = $(this).attr("attr_meeting_status");
	    var meetingLevel = $(this).attr("attr_level_type");
		var isComment = $(this).attr("attr_comment");
		var dates=$("#dateRangeIdForMeetings").val();
		var fromDateStr;
		var toDateStr;
		var reportType;
		if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			fromDateStr = datesArr[0].trim(); 
			toDateStr = datesArr[1].trim(); 
		}
		$("#getDetailsBtnId").attr("attr_comment","Yes");
	  // $("#getDetailsBtnId").attr("attr_result_type","individual");
	   $("#districtSlctBxId").attr("attr_comment","Yes");		 
       $("#constituencySlctBxId").attr("attr_comment","Yes");	
	   getDistrictByState(meetingStatus,meetingLevel,"Yes");	
      getConstituencyByDistrict(meetingStatus,meetingLevel,"Yes",0);
      getMandalByConstituency(meetingStatus,meetingLevel,"Yes",0);	
	 if($(this).is(':checked')){
		 getMeetingComments(meetingStatus,meetingLevel,isComment,fromDateStr,toDateStr,0," ","meetingCommentDtlsTblId","directRslt"); 
	 } 
	 
 });
 $(document).on("click",".commentDetailsCls",function(){
	    var meetingStatus = $(this).attr("attr_meeting_status");
	    var meetingLevel = $(this).attr("attr_level_type");
		var isComment = $(this).attr("attr_comment");
		var dates=$("#dateRangeIdForMeetings").val();
		var fromDateStr;
		var toDateStr;
		if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			fromDateStr = datesArr[0].trim(); 
			toDateStr = datesArr[1].trim(); 
		} 
		var locationId;
		var locationType;
		locationId=$(this).attr("attr_location_id");
		locationType=$(this).attr("attr_location_type");
		
		

        //var districtName=$("#districtSlctBxId option:selected").text();
        //var constituencyName=$("#constituencySlctBxId option:selected").text();
        //var mandalTwnDivisionName=$("#mandalSlctBxId option:selected").text();

		var constituencyId = $("#constituencySlctBxId").val();
		var mandalTwnDivisionId = $("#mandalSlctBxId").val();
		
		var consolidateType = $("#commentFilterSelectBoxId option:selected").text();
		  if(consolidateType=="District"){
			  if(constituencyId > 0){
				locationId =  constituencyId; 
				locationType = "Constituency";
			  }
			  if(mandalTwnDivisionId > 0){
				locationId =  mandalTwnDivisionId; 
				locationType = "mandal";  
			  }
			  
		  }else if(consolidateType=="Constituency"){
			  if(mandalTwnDivisionId > 0){
				locationId =  mandalTwnDivisionId; 
				locationType = "mandal";  
			  }  
		  }
	   getMeetingComments(meetingStatus,meetingLevel,isComment,fromDateStr,toDateStr,locationId,locationType,"meetingDetailsTblId","subLevel");
 });
  $(document).on("click",".overAllMeetingCls",function(){
	    var meetingStatus = $(this).attr("attr_meeting_status");
	    var meetingLevel = $(this).attr("attr_level_type");
		var isComment = $(this).attr("attr_comment");
		var date = $("#dateMeetingHeadingId").text();
		
		
	    if(meetingStatus=="N"){
		  $("#meetingHeadingId").html('<span class="text-capital">'+meetingLevel+'&nbsp&nbsp <b>Not Conducted</b> Meeting</span> - '+date+'');	
		}else if(meetingStatus=='Y'){
		 $("#meetingHeadingId").html('<span class="text-capital">'+meetingLevel+'&nbsp&nbsp<b>Conducted</b> Meeting - '+date+'');		
		}else if(meetingStatus=="M"){
		 $("#meetingHeadingId").html('<span class="text-capital">'+meetingLevel +'&nbsp&nbsp <b>Maybe</b> Meeting - '+date+'');		
		}else if(meetingStatus=="NU"){
		 $("#meetingHeadingId").html('<span class="text-capital">'+meetingLevel +'&nbsp&nbsp <b>Not Updated Meeting</b> - '+date+'');		
		}
		$("#commentFilterSelectBoxId").attr("attr_meeting_status",meetingStatus);
		$("#commentFilterSelectBoxId").attr("attr_level_type",meetingLevel);
		$("#commentFilterSelectBoxId").attr("attr_comment","No");
		
		$("#getDetailsBtnId").attr("attr_meeting_status",meetingStatus);
		$("#getDetailsBtnId").attr("attr_level_type",meetingLevel);
		//$("#getDetailsBtnId").attr("attr_comment","Yes");
		
		$("#ConsolidatedradioId").attr("attr_meeting_status",meetingStatus);
		$("#ConsolidatedradioId").attr("attr_level_type",meetingLevel);
		$("#ConsolidatedradioId").attr("attr_comment","No");
		
		$("#individualradioId").attr("attr_meeting_status",meetingStatus);
		$("#individualradioId").attr("attr_level_type",meetingLevel);
		$("#individualradioId").attr("attr_comment","Yes");
		
		$("#constituencySlctBxId").attr("attr_meeting_status",meetingStatus);
		$("#constituencySlctBxId").attr("attr_level_type",meetingLevel);
		
		$("#districtSlctBxId").attr("attr_meeting_status",meetingStatus);
		$("#districtSlctBxId").attr("attr_level_type",meetingLevel);
		
	
		var dates=$("#dateRangeIdForMeetings").val();
		var fromDateStr;
		var toDateStr;
		if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			fromDateStr = datesArr[0].trim(); 
			toDateStr = datesArr[1].trim(); 
		}
		 var date1 = new Date(convetFormat(fromDateStr));
		 var date2 = new Date(convetFormat(toDateStr));
		 var timeDiff = Math.abs(date2.getTime() - date1.getTime());
		 var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
		  $("#commentFilterSelectBoxId").val(0);
		  
		  var consolidateTypeDropDownLength = $('#commentFilterSelectBoxId').children('option').length;
		  if(consolidateTypeDropDownLength==1){
			 $("#commentFilterSelectBoxId"). append("<option value=1>Constituency</option>");
		   }
		  if(isComment == "No"){
			  if(meetingLevel == "District"){
			     $(".mandalSlctBxCls").hide();
                 $(".constituencySlctBxCls").hide();	
				 $("#commentFilterSelectBoxId").children('option:not(:first)').remove();
			   }else if(meetingLevel == "Constituency"){
				  $(".mandalSlctBxCls").hide();
				  $(".constituencySlctBxCls").show();
               }else{
				 $(".mandalSlctBxCls").show();
                 $(".constituencySlctBxCls").show();
			   }
			  $("#ConsolidatedradioId").prop('checked',true);
			  $(".individualRadioBtnCls").hide();
			  $("#constituencySlctBxId").attr("attr_comment","No");				  
              $("#districtSlctBxId").attr("attr_comment","No");
			  $("#getDetailsBtnId").attr("attr_comment","No");
			  getDistrictByState(meetingStatus,meetingLevel,"No");	
              getConstituencyByDistrict(meetingStatus,meetingLevel,"No",0);//for all
              getMandalByConstituency(meetingStatus,meetingLevel,"No",0);	
			  getPartyMeetingComulativeCommentDetails(meetingStatus,meetingLevel,"No",fromDateStr,toDateStr,"District",0," ");   
		 }else{
			  $(".individualRadioBtnCls").show();
			if((meetingLevel =="Village/Ward" || meetingLevel =="Mandal/Town/Division") && diffDays <=31){
			// $(".consolidatedCls").hide();
			   $(".mandalSlctBxCls").show();
			   $(".constituencySlctBxCls").show();
			 getDistrictByState(meetingStatus,meetingLevel,"Yes");	
             getConstituencyByDistrict(meetingStatus,meetingLevel,"Yes",0);
             getMandalByConstituency(meetingStatus,meetingLevel,"Yes",0);	
			 getMeetingComments(meetingStatus,meetingLevel,"Yes",fromDateStr,toDateStr,0," ","meetingCommentDtlsTblId","directRslt"); 
			 $("#getDetailsBtnId").attr("attr_comment","Yes");
            // $("#getDetailsBtnId").attr("attr_result_type","Individual");
             $("#districtSlctBxId").attr("attr_comment","Yes");		 
             $("#constituencySlctBxId").attr("attr_comment","Yes");	
			 $("#ConsolidatedradioId").prop('checked',false);
			 $("#individualradioId").prop('checked', true);
		}else if((meetingLevel =="Village/Ward" || meetingLevel =="Mandal/Town/Division") && diffDays>=31){
			 // $(".consolidatedCls").show();
			   $(".mandalSlctBxCls").show();
			   $(".constituencySlctBxCls").show();
			  $("#getDetailsBtnId").attr("attr_comment","No");
			  $("#districtSlctBxId").attr("attr_comment","No");		 
              $("#constituencySlctBxId").attr("attr_comment","No");
               $("#individualradioId").prop('checked', false);						  
         	  $("#ConsolidatedradioId").prop('checked', true);
			  // $("#getDetailsBtnId").attr("attr_result_type","consolidated");
			   getDistrictByState(meetingStatus,meetingLevel,"No");	
              getConstituencyByDistrict(meetingStatus,meetingLevel,"No",0);//for all
              getMandalByConstituency(meetingStatus,meetingLevel,"No",0);	
			  getPartyMeetingComulativeCommentDetails(meetingStatus,meetingLevel,"No",fromDateStr,toDateStr,"District",0," "); 
		 }else if(meetingLevel=="District" && diffDays <=31) {
			  //$(".consolidatedCls").hide();
			  $(".mandalSlctBxCls").hide();
			  $(".constituencySlctBxCls").hide();
			  $("#getDetailsBtnId").attr("attr_comment","Yes");
			  $("#commentFilterSelectBoxId").children('option:not(:first)').remove();
			  getDistrictByState(meetingStatus,meetingLevel,"Yes");	
			  getConstituencyByDistrict(meetingStatus,meetingLevel,"Yes",0);//for all
			  getMandalByConstituency(meetingStatus,meetingLevel,"Yes",0);	
			  getMeetingComments(meetingStatus,meetingLevel,"Yes",fromDateStr,toDateStr,0," ","meetingCommentDtlsTblId","directRslt"); 	 
			  // $("#getDetailsBtnId").attr("attr_result_type","Individual");
			   $("#districtSlctBxId").attr("attr_comment","Yes");		 
               $("#constituencySlctBxId").attr("attr_comment","Yes");
			   $("#ConsolidatedradioId").prop('checked', false);
               $("#individualradioId").prop('checked', true);				   
		 }else if(meetingLevel == "District" && diffDays >=31){
			  getPartyMeetingComulativeCommentDetails(meetingStatus,meetingLevel,"No",fromDateStr,toDateStr,"District",0," "); 
			  getDistrictByState(meetingStatus,meetingLevel,"No");	
              getConstituencyByDistrict(meetingStatus,meetingLevel,"No",0);//for all
              getMandalByConstituency(meetingStatus,meetingLevel,"No",0);	
             $("#districtSlctBxId").attr("attr_comment","No");		 
             $("#constituencySlctBxId").attr("attr_comment","No");				  
             $("#getDetailsBtnId").attr("attr_comment","No");
             $("#individualradioId").prop('checked', false);						 
		 	 $("#ConsolidatedradioId").prop('checked', true);
			 $("#commentFilterSelectBoxId").children('option:not(:first)').remove();
			// $("#getDetailsBtnId").attr("attr_result_type","consolidated");
			 $(".mandalSlctBxCls").hide();
			 $(".constituencySlctBxCls").hide();
		 }else if(meetingLevel=="Constituency" && diffDays <= 31 ) {
		  //$(".consolidatedCls").hide();
		  $(".mandalSlctBxCls").hide();
		   $(".constituencySlctBxCls").show();
		  getDistrictByState(meetingStatus,meetingLevel,"Yes");	
          getConstituencyByDistrict(meetingStatus,meetingLevel,"Yes",0);//for all
          getMandalByConstituency(meetingStatus,meetingLevel,"Yes",0);	
		  getMeetingComments(meetingStatus,meetingLevel,isComment,fromDateStr,toDateStr,0," ","meetingCommentDtlsTblId","directRslt"); 
          $("#getDetailsBtnId").attr("attr_comment","Yes");		
         // $("#getDetailsBtnId").attr("attr_result_type","Individual");
          $("#districtSlctBxId").attr("attr_comment","Yes");		 
          $("#constituencySlctBxId").attr("attr_comment","Yes");
           $("#ConsolidatedradioId").prop('checked', false);	  
          $("#individualradioId").prop('checked', true);				 
		 }else if(meetingLevel=="Constituency" && diffDays >=31){
		  $("#getDetailsBtnId").attr("attr_comment","No");
		   $("#individualradioId").prop('checked', false);			
		  $("#ConsolidatedradioId").prop('checked', true);
		 // $("#getDetailsBtnId").attr("attr_result_type","consolidated");
		  $(".mandalSlctBxCls").hide();
		   $(".constituencySlctBxCls").show();
		   $("#commentFilterSelectBoxId").val(0);
		  getPartyMeetingComulativeCommentDetails(meetingStatus,meetingLevel,"No",fromDateStr,toDateStr,"District",0," ");
		   getDistrictByState(meetingStatus,meetingLevel,"No");	
           getConstituencyByDistrict(meetingStatus,meetingLevel,"No",0);//for all
           getMandalByConstituency(meetingStatus,meetingLevel,"No",0);	
		   $("#districtSlctBxId").attr("attr_comment","No");		 
          $("#constituencySlctBxId").attr("attr_comment","No");				  
        }else if(meetingLevel=="State" && diffDays <= 31){
		  $("#getDetailsBtnId").attr("attr_comment","Yes");
		  getDistrictByState(meetingStatus,meetingLevel,"Yes");	
          getConstituencyByDistrict(meetingStatus,meetingLevel,"Yes",0);//for all
          getMandalByConstituency(meetingStatus,meetingLevel,"Yes",0);	
		  getMeetingComments(meetingStatus,meetingLevel,"Yes",fromDateStr,toDateStr,0," ","meetingCommentDtlsTblId","directRslt");
           $("#districtSlctBxId").attr("attr_comment","Yes");		 
           $("#constituencySlctBxId").attr("attr_comment","Yes");		
           $(".mandalSlctBxCls").hide();
           $(".constituencySlctBxCls").hide();	
		   $("#commentFilterSelectBoxId").children('option:not(:first)').remove();
           $("#ConsolidatedradioId").prop('checked', false);	   
           $("#individualradioId").prop('checked', true);			
		 }else if(meetingLevel=="State" && diffDays >= 31){
		  $("#getDetailsBtnId").attr("attr_comment","No");
		  $("#individualradioId").prop('checked', false);			
		  $("#ConsolidatedradioId").prop('checked', true);
		  $("#commentFilterSelectBoxId").children('option:not(:first)').remove();
		 // $("#getDetailsBtnId").attr("attr_result_type","consolidated");
		  getPartyMeetingComulativeCommentDetails(meetingStatus,meetingLevel,"No",fromDateStr,toDateStr,"District",0," ");
		  getDistrictByState(meetingStatus,meetingLevel,"No");	
          getConstituencyByDistrict(meetingStatus,meetingLevel,"No",0);//for all
          getMandalByConstituency(meetingStatus,meetingLevel,"No",0);
          $("#districtSlctBxId").attr("attr_comment","No");		 
          $("#constituencySlctBxId").attr("attr_comment","No");	
          $(".mandalSlctBxCls").hide();	
          $(".constituencySlctBxCls").hide();		   
         }  
		}
		 
   });
   $(document).on("change","#commentFilterSelectBoxId",function(){
	    $("#individualradioId").prop('checked',false);
	    $("#ConsolidatedradioId").prop('checked',true);
	    var meetingStatus = $(this).attr("attr_meeting_status");
	    var meetingLevel = $(this).attr("attr_level_type");
		var isComment = $(this).attr("attr_comment");
		var reportTypeId = $(this).val();
		var dates=$("#dateRangeIdForMeetings").val();
		var fromDateStr;
		var toDateStr;
		var reportType;
		if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			fromDateStr = datesArr[0].trim(); 
			toDateStr = datesArr[1].trim(); 
		}
		 if(reportTypeId == 0){
			reportType = "District"; 
		 }else if(reportTypeId == 1){
			reportType = "Constituency";  
		 }
		  getDistrictByState(meetingStatus,meetingLevel,"No");	
          getConstituencyByDistrict(meetingStatus,meetingLevel,"No",0);//for all
          getMandalByConstituency(meetingStatus,meetingLevel,"No",0);	
		  $("#districtSlctBxId").attr("attr_comment","No");		 
          $("#constituencySlctBxId").attr("attr_comment","No");	
          $("#getDetailsBtnId").attr("attr_comment","No");		  
		// $("#getDetailsBtnId").attr("attr_result_type","consolidated");
		getPartyMeetingComulativeCommentDetails(meetingStatus,meetingLevel,isComment,fromDateStr,toDateStr,reportType,0," ")
   });
   function convetFormat(date){
	   var dateArr=date.split("/");
	   return dateArr[1]+"/"+dateArr[0]+"/"+dateArr[2];
   }
   function getPartyMeetingComulativeCommentDetails(meetingStatus,meetingLevel,isComment,fromDateStr,toDateStr,reportType,locationId,locationType){
	 var partyMeetingTypeArr=[];
	   $("#meetingCommentDtlsTblId").html(' ');
	   $("#meetingCommentModalId").modal("show");
	   $("#meetingCommentProcessingImgId").show();
	   
	  $("#committeeTypeId li").each(function() {
		  if($(this).find("input").is(":checked")){
			  partyMeetingTypeArr.push($(this).find("input").attr("id"));
		  }
	   });
	 	var jsObj ={ 
		             activityMemberId : globalActivityMemberId,
					 stateId : globalStateId,
					 fromDate : fromDateStr,
					 toDate : toDateStr,
					 partyMeetingTypeArr:partyMeetingTypeArr,
					 meetingStatus : meetingStatus,
					 meetingLevel : meetingLevel,
					 isComment : isComment,
					 reportType : reportType,
					 locationId : locationId,
					 locationType : locationType
					 
				  }
		$.ajax({
			type : 'POST',
			url : 'getPartyMeetingComulativeCommentDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			 $("#meetingCommentProcessingImgId").hide();
			if(result != null && result.length > 0){
			   buildComulativeCommentResult(result,reportType,meetingStatus,meetingLevel);	
			}else{
			 $("#meetingCommentDtlsTblId").html('NO DATA AVAILABLE.');	
			}
		});  
  } 
  function buildComulativeCommentResult(result,reportType,meetingStatus,meetingLevel){
	 var str='';
	   str+='<table style="background-color:#EDEEF0;border:1px solid #ddd" class="table table-condensed table-bordered" id="comulativeCommentTblId">';
		 str+='<thead>';
		    if(reportType=="District"){
				 str+='<th>District Name</th>';
			}else if(reportType=="Constituency"){
				 str+='<th>Constituency Name</th>';
			}
            str+='<th style="text-align:center;">Meeting Count</th>';
			 str+='<th style="text-align:center;">Comment Count</th>';
		 str+='</thead>';
		 str+='<tbody>';
		  for(var i in result){
			str+='<tr>';
			 if(result[i].name != null && result[i].name.length > 0){
				str+='<td>'+result[i].name+'</td>';      
			  }else{
				str+='<td> - </td>';  
			  }
			  if(result[i].meetingCount != null && result[i].meetingCount > 0){
				str+='<td attr_comment="No" attr_location_id="'+result[i].id+'" attr_meeting_status='+meetingStatus+' attr_level_type='+meetingLevel+' attr_location_type='+reportType+' style="cursor: pointer;text-align:center;" class="commentDetailsCls">'+result[i].meetingCount+'</td>';  
			  }else{
			  str+='<td> - </td>';  
			  }
			  if(result[i].commentCount != null && result[i].commentCount > 0){
				  str+='<td attr_comment="Yes" attr_location_id="'+result[i].id+'" attr_meeting_status='+meetingStatus+' attr_level_type='+meetingLevel+' attr_location_type='+reportType+' style="cursor: pointer;text-align:center;" class="commentDetailsCls">'+result[i].commentCount+'</td>';      
			  }else{
				str+='<td> - </td>';  
			  }
			str+='</tr>';
			}
			 str+='</tbody>';
			 str+='</table>';
	      $("#meetingCommentDtlsTblId").html(str);
		  $("#comulativeCommentTblId").dataTable({
			"aaSorting": [],
			"iDisplayLength" : 10	
		   });   
  }
  function getMeetingComments(meetingStatus,meetingLevel,isComment,fromDateStr,toDateStr,locationId,locationType,divId,statusType){
	 var partyMeetingTypeArr=[];
	   if(statusType=="directRslt"){
		 $("#"+divId).html(' ');
	    $("#meetingCommentModalId").modal("show");
	    $("#meetingCommentProcessingImgId").show();   
	   }else{
	   $("#meetingCommentDtlsModalId").modal("show");
	   $("#meetingSubLevelRsltProcessingImgId").show();
	   $("#"+divId).html(' ');
	   }
	  $("#committeeTypeId li").each(function() {
		  if($(this).find("input").is(":checked")){
			  partyMeetingTypeArr.push($(this).find("input").attr("id"));
		  }
	   });
	 	var jsObj ={ 
		             activityMemberId : globalActivityMemberId,
					 stateId : globalStateId,
					 fromDate : fromDateStr,
					 toDate : toDateStr,
					 partyMeetingTypeArr:partyMeetingTypeArr,
					 meetingStatus : meetingStatus,
					 meetingLevel : meetingLevel,
					 isComment : isComment,
					 locationId : locationId,
					 locationType : locationType
				  }
		$.ajax({
			type : 'POST',
			url : 'getPartyMeetingCommentsDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			   if(statusType=="directRslt"){
				 $("#meetingCommentProcessingImgId").hide();
			   }else{
				 $("#meetingSubLevelRsltProcessingImgId").hide();
			   }
			if(result != null && result.length > 0){
				buildMeetingCommentDtlsRslt(result,divId,statusType,meetingLevel);
			}else{
			   if(statusType=="directRslt"){
				  $("#"+divId).html("NO DATA AVAILABLE.");
			   }else{
				  $("#"+divId).html("NO DATA AVAILABLE.");
			   }
			}
		});  
  } 
   function buildMeetingCommentDtlsRslt(result,divId,statusType,meetingLevel){
	   var str='';
	   if(statusType == "directRslt"){
		str+='<table style="background-color:#EDEEF0;border:1px solid #ddd" class="table table-condensed" id="meetingCommentDataTblId">';   
	   }else{
		str+='<table style="background-color:#EDEEF0;border:1px solid #ddd" class="table table-condensed" id="subLevelDataTblRsltId">';   
	   }
	   str+='<thead>';
             str+='<th>District</th>';
			 if(meetingLevel == "Constituency"){
			 str+='<th>Constituency</th>'	 
			 }
			 if(meetingLevel=="Village/Ward" || meetingLevel=="Mandal/Town/Division"){
				str+='<th>Constituency</th>'	  
				str+='<th>Mandal/Town/Division</th>'	  
			 }
			 str+='<th>Meeting Name</th>';
			 str+='<th>Conducted Date</th>';
			 str+='<th>Comment</th>';
		 str+='</thead>';
		 str+='<tbody>';
		  for(var i in result){
			str+='<tr>';
			 if(result[i].districtName != null && result[i].districtName.length > 0){
				str+='<td>'+result[i].districtName+'</td>';      
			  }else{
				str+='<td> - </td>';  
			  }
			 if(meetingLevel == "Constituency"){
			  if(result[i].constituencyName != null && result[i].constituencyName.length > 0){
				str+='<td>'+result[i].constituencyName+'</td>';  
			  }else{
			  str+='<td> - </td>';  
			  }
			  }
			  if(meetingLevel=="Village/Ward" || meetingLevel=="Mandal/Town/Division"){
				   if(result[i].constituencyName != null && result[i].constituencyName.length > 0){
					str+='<td>'+result[i].constituencyName+'</td>';  
				   }else{
				  str+='<td> - </td>';  
				  }  
				  if(result[i].mandalTwnDivision != null && result[i].mandalTwnDivision.length > 0){
					str+='<td>'+result[i].mandalTwnDivision+'</td>';  
				  }else{
				  str+='<td> - </td>';  
				  }	  
			 }
			 if(result[i].meetingName != null && result[i].meetingName.length > 0){
				str+='<td>'+result[i].meetingName+'</td>';  
			  }else{
			  str+='<td> - </td>';  
			  }
			  if(result[i].conductedDate != null && result[i].conductedDate.length > 0){
				  str+='<td>'+result[i].conductedDate+'</td>';      
			  }else{
				str+='<td> - </td>';  
			  }
			  if(result[i].remarks != null && result[i].remarks.length > 0){
				 str+='<td>'+result[i].remarks+'</td>';       
			  }else{
				str+='<td> - </td>';  
			  }
			str+='</tr>';
			}
			 str+='</tbody>';
			 str+='</table>';
			
	  if(statusType == "directRslt"){
	
     	$("#"+divId).html(str);
		 $("#meetingCommentDataTblId").dataTable({
			"aaSorting": [],
			"iDisplayLength" : 10	
		 });
	   }else{
		 $("#"+divId).html(str);
		 $("#subLevelDataTblRsltId").dataTable({
			"aaSorting": [],
			"iDisplayLength" : 10	
		 });
	   }
   }
   
  /* Meeting Comment Block end */
  