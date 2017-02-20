$(document).on("click",".meetingsRefresh",function(){
	  var isMeetingExpand = $(this).attr("attr_refresh_status");
	  var selectedMeetingType = $(this).attr("attr_meeting_type");
	  var mainMeetingTypeId = $(this).attr("attr_main_type_meeting_id");
	  var partymeetingtypeidsstring = $(this).attr("attr_partymeetingtypeidsstring");
	 if(isMeetingExpand == "true"){
		 if(selectedMeetingType=="committeeMeeting"){
		       getUserTypeWiseMeetingCounductedNotCounductedMayBeDetailsCnt();	 
		 }else if(selectedMeetingType=="stateAndSpecialTypeMeeting"){
		        getCommitteesAndPublicRepresentativeMembersInvitedAndAttendedToMeetings(mainMeetingTypeId,partymeetingtypeidsstring);
		 }else if(selectedMeetingType=="specailSessionTypeMeeting"){
			    var meetingId = $(this).attr("attr_special_meeting_id");
				var partyMeetingIdArr = [meetingId];
				getCommitteesAndPublicRepresentativeMembersInvitedAndAttendedToSeeionWiseMeetingDtls(mainMeetingTypeId,partymeetingtypeidsstring,partyMeetingIdArr);
		 }
	 }
	getPartyMeetingBasicCountDetails();
	getPartyMeetingsMainTypeStateLevelOverview();
	getPartySpecialMeetingsMainTypeOverview(0); 
});
//GLOBAL VARIABLES FOR DATES IN MEETINGS.
 var customStartDateMeetings = moment().subtract(1, 'month').startOf('month').format('DD/MM/YYYY');
 var customEndDateMeetings = moment().subtract(1, 'month').endOf('month').format('DD/MM/YYYY');
 var globalStartDateForSpecialMeeting;
 var globalEndDateForSpecialMeeting;

function globalMeetingsCalls(type)
{
	defaultFunctionaliySpecicalMeeting();
	/* if(type == "default"){
		$('#dateRangeIdForMeetings').data('daterangepicker').setStartDate(moment());
		$('#dateRangeIdForMeetings').data('daterangepicker').setEndDate(moment());
		customStartDateMeetings = moment().format('DD/MM/YYYY')
		customEndDateMeetings = moment().format('DD/MM/YYYY')
		$("#dateMeetingHeadingId").html("TODAY"+" ( "+moment().format('DD/MM/YYYY')+"-"+moment().format('DD/MM/YYYY')+" )");
	} */
	if(type == "currentMonth"){
		$('#dateRangeIdForMeetings').data('daterangepicker').setStartDate(moment().startOf("month"));
		$('#dateRangeIdForMeetings').data('daterangepicker').setEndDate(moment().endOf("month"));
		customStartDateMeetings = moment().startOf("month").format('DD/MM/YYYY')
		customEndDateMeetings = moment().endOf("month").format('DD/MM/YYYY')
		$("#dateMeetingHeadingId").html("THIS MONTH"+" ( "+moment().startOf("month").format('DD/MM/YYYY')+"-"+moment().endOf("month").format("DD/MM/YYYY")+" )");
	}else if(type == "lastMonth" || type == "default"){
		$('#dateRangeIdForMeetings').data('daterangepicker').setStartDate(moment().subtract(1,'month').startOf("month"));
		$('#dateRangeIdForMeetings').data('daterangepicker').setEndDate(moment().subtract(1,'month').endOf("month"));
		customStartDateMeetings = moment().subtract(1,'month').startOf("month").format('DD/MM/YYYY')
		customEndDateMeetings = moment().subtract(1,'month').endOf("month").format('DD/MM/YYYY')
		$("#dateMeetingHeadingId").html("LAST MONTH"+" ( "+moment().subtract(1,'month').startOf("month").format('DD/MM/YYYY')+"-"+moment().subtract(1,'month').endOf("month").format('DD/MM/YYYY')+" )");
	}
	$("#dateRangeIdForMeetings").val(customStartDateMeetings+" - "+customEndDateMeetings);
	getPartyMeetingBasicCountDetails();
	getPartySpecialMeetingsMainTypeOverview(0);
	getPartyMeetingsMainTypeStateLevelOverview();
	if($(".meetingsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".dateRangePickerClsForMeetings").toggleClass("hide");
		$(".meetingHead").toggleClass('col-md-9 col-sm-9').toggleClass('col-md-8 col-sm-8');
		$(".meetingHead1").toggleClass('col-md-3 col-sm-3').toggleClass('col-md-4 col-sm-4');
		$(".meetingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".meetingsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
		$(".meetingsHiddenBlock,.moreMeetingsBlocks").hide();
		$(".moreMeetingsBlocks1").hide();
		$(".moreMeetingsBlocksDetailed").hide();
		$(".moreMeetingsBlocksComparision").hide();
		$(".stateGeneralMeeting,.specialMeetings,.stateLevelMeetingsExpand,.statelevelSessionMeeting").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen")
	}
}


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
           'This Month': [moment().startOf('month'), moment().endOf('month')],
           'This Year': [moment().startOf('Year'), moment()],
		   'Overall' : [moment().subtract(30, 'years').startOf('year'), moment()],
        }
	});
	
	
	
	var dates= $("#dateRangeIdForMeetings").val();
	//$("#dateMeetingHeadingId").html(" THIS MONTH ( "+customStartDate+" to "+customEndDate+" )");
	$("#dateMeetingHeadingId").html(" Last MONTH ( "+dates+" )");
	var singleBlockDateStart = moment().startOf('month').format('MMM YY');
	var singleBlockDateEnd = moment().format('MMM YY');
	
	$('#dateRangeIdForMeetings').on('apply.daterangepicker', function(ev, picker) {
	  
	  customStartDateMeetings = picker.startDate.format('DD/MM/YYYY');
	  customEndDateMeetings = picker.endDate.format('DD/MM/YYYY');
	  globalStartDateForSpecialMeeting = picker.startDate.format('DD/MM/YYYY');
      globalEndDateForSpecialMeeting = picker.endDate.format('DD/MM/YYYY');
	  
	  
	  singleBlockDateStart = picker.startDate.format('MMM YY');
	  singleBlockDateEnd = picker.endDate.format('MMM YY');
	  //$("#dateMeetingHeadingId").html(picker.chosenLabel+" ( "+customStartDate+" to "+customEndDate+" )");
	  
	  //alert(customStartDateMeetings + "-" +customEndDateMeetings);    
	  $(".meetingsHiddenBlock").show();
	  $(".stateGeneralMeeting,.stateLevelMeetingsExpand,.specialMeetings, .statelevelSessionMeeting").find('i').removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen");
	  $(".showMoreBlockCls").attr("attr_main_type_meeting_id",1);//committee meeting
	  getPartyMeetingBasicCountDetails();
	  getUserTypeWiseMeetingCounductedNotCounductedMayBeDetailsCnt();
	  getPartySpecialMeetingsMainTypeOverview(0);
	  getPartyMeetingsMainTypeStateLevelOverview();
	  var dates= $("#dateRangeIdForMeetings").val();
	 // $("#dateMeetingHeadingId").html(" THIS MONTH ( "+dates+" )");
	  $("#dateMeetingHeadingId").html(picker.chosenLabel+" ( "+dates+" )");
	});
	
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
	    
		var jsObj ={ 
		             activityMemberId : globalActivityMemberId,
					 stateId : globalStateId,
					 fromDate : customStartDateMeetings,
					 toDate : customEndDateMeetings,
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
			 str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10" style="display:none;">';
			 if($(window).width() < 300)
			 {
				 str+='<div class="table-responsive">';
			 }
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
			if($(window).width() < 300)
			 {
				 str+='</div>';
			 }
		  str+='<hr class="m_0">';
		  str+='</div>';  
		  }
		 if(levelWiseResult != null && levelWiseResult.length > 0){
		   for(var i in levelWiseResult){
			 str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
				if(levelWiseResult[i].name == "Village/Ward")
			   {
				 str+='<h4 class="text-capitalize">'+levelWiseResult[i].name+'<small style="margin-left:15px;">(Every month : 9th/10th/11th)</small><small class="meetingsInnerBlock"></small></h4>';
			   }else if(levelWiseResult[i].name == "Mandal/Town/Division"){
				 str+='<h4 class="text-capitalize">'+levelWiseResult[i].name+'<small style="margin-left:15px;">(Every month : 14th/15th/16th)</small><small class="meetingsInnerBlock"></small></h4>';
			   }else if(levelWiseResult[i].name == "Constituency"){
				 str+='<h4 class="text-capitalize">'+levelWiseResult[i].name+'<small style="margin-left:15px;">(Every month : 18th/19th/20th)</small><small class="meetingsInnerBlock"></small></h4>';
			   }else if(levelWiseResult[i].name == "District"){
				 str+='<h4 class="text-capitalize">'+levelWiseResult[i].name+'<small style="margin-left:15px;">(Every month : 22nd/23rd/24th)</small><small class="meetingsInnerBlock"></small></h4>';
			   }else{
				 str+='<h4 class="text-capitalize">'+levelWiseResult[i].name+'<small style="margin-left:15px;">(Every month : 22nd/23rd/24th)</small><small class="meetingsInnerBlock"></small></h4>';
			   }	
				if($(document).width() < 300)
				{
					str+='<div class="table-responsive">';
				}			   
				str+='<table class="table tableTraining bg_ED">';
					 str+='<tbody><tr>';
						 str+='<td>';
							 str+='<p class="text-muted text-capitalize">total</p>';
							 str+='<h4>'+levelWiseResult[i].totalCount+'</h4>';
						 str+='</td>';
					   str+='<td>';
					  if(levelWiseResult[i].conductedCommentCnt > 0 && levelWiseResult[i].conductedCount > 0){
						  var obj1 = levelWiseResult[i].name;
		
						  
						  
						str+='<p class="text-muted text-capital">Yes&nbsp&nbsp<a attr_meeting_status="Y" attr_comment="yes" attr_level_type="'+levelWiseResult[i].name+'" style="color: rgb(173, 173, 173);cursor: pointer;text-decoration: none;" class="glyphicon glyphicon-comment overAllMeetingCls" data-toggle="tooltip" data-placement="top" id="partyMeetingsId'+i+'"  onclick="overAllMeetings(this.id);" title="Counducted Meeting Comment '+levelWiseResult[i].conductedCommentCnt+'('+levelWiseResult[i].conductedCommentCntPer+'%)" data-original-title=""></a></p>';

						
						str+='<h4>'+levelWiseResult[i].conductedCount+'<span class="font-10 text-success"> '+levelWiseResult[i].conductedCountPer+'%</span></h4>';
				          }else if(levelWiseResult[i].conductedCount > 0){
						 str+='<p class="text-muted text-capitalize">yes</p>';	  
						 str+='<h4 attr_meeting_status="Y" attr_comment="No" attr_level_type="'+levelWiseResult[i].name+'" style="cursor: pointer;" class=" overAllMeetingCls" id="partyMeetingsIdA'+i+'"  onclick="overAllMeetings(this.id);">'+levelWiseResult[i].conductedCount+'<span class="font-10 text-success"> '+levelWiseResult[i].conductedCountPer+'%</span></h4>';
						 }else{
						 str+='<p class="text-muted text-capitalize">yes</p>';	  
						 str+='<h4>'+levelWiseResult[i].conductedCount+'<span class="font-10 text-success"> '+levelWiseResult[i].conductedCountPer+'%</span></h4>';
						 }
						 str+='</td>';  
						 	str+='<td>';
						   if(levelWiseResult[i].notConductedCommentCnt > 0 && levelWiseResult[i].notConductedCount > 0){
							str+='<p class="text-muted text-capital">no&nbsp&nbsp<a attr_meeting_status="N" attr_comment="yes" attr_level_type="'+levelWiseResult[i].name+'" style="color: rgb(173, 173, 173);cursor: pointer;text-decoration: none;" class="glyphicon glyphicon-comment overAllMeetingCls" id="partyMeetingsIdB'+i+'"  onclick="overAllMeetings(this.id);" data-toggle="tooltip" data-placement="top" title="Not Counducted Meeting Comment '+levelWiseResult[i].notConductedCommentCnt+'('+levelWiseResult[i].notConductedCommentCntPer+'%)" data-original-title=""></a></p>'; 
                            str+='<h4>'+levelWiseResult[i].notConductedCount+' <span class="font-10 text-danger"> '+levelWiseResult[i].notConductedCountPer+'%</span></h4>';		
                     		}else if(levelWiseResult[i].notConductedCount > 0){
							  str+='<p class="text-muted text-capital">no</p>';	 
							  str+='<h4 attr_meeting_status="N" attr_comment="No" attr_level_type="'+levelWiseResult[i].name+'" style="cursor: pointer;" class="overAllMeetingCls" id="partyMeetingsIdC'+i+'"  onclick="overAllMeetings(this.id);">'+levelWiseResult[i].notConductedCount+' <span class="font-10 text-danger"> '+levelWiseResult[i].notConductedCountPer+'%</span></h4>';	
						     }else{
								  str+='<p class="text-muted text-capital">no</p>';	 
                                  str+='<h4>'+levelWiseResult[i].notConductedCount+' <span class="font-10 text-danger"> '+levelWiseResult[i].notConductedCountPer+'%</span></h4>';	
							}
						 str+='</td>';
						 	str+='<td>';
						    if(levelWiseResult[i].mayBeCommentCnt > 0 && levelWiseResult[i].mayBeCount > 0){
								str+='<p class="text-muted text-capital">maybe&nbsp&nbsp<a attr_meeting_status="M" attr_level_type="'+levelWiseResult[i].name+'" style="color: rgb(173, 173, 173);cursor: pointer;text-decoration: none;" attr_comment="yes" class="glyphicon glyphicon-comment overAllMeetingCls" data-toggle="tooltip" id="partyMeetingsIdD'+i+'"  onclick="overAllMeetings(this.id);" data-placement="top" title="Maybe Meeting Comment '+levelWiseResult[i].mayBeCommentCnt+'('+levelWiseResult[i].mayBeCommentCntPer+'%)" data-original-title=""></a></p>';  
						         str+='<h4>'+levelWiseResult[i].mayBeCount+' <span class="font-10 text-customColor"> '+levelWiseResult[i].mayBeCountPer+'%</span></h4>';
							  }else if(levelWiseResult[i].mayBeCount > 0){
								  if(levelWiseResult[i].updationCount != null && levelWiseResult[i].updationCount > 0){
									str+='<p class="text-muted text-capital">maybe</p>';	
	                             str+='<h4><span attr_meeting_status="M" attr_level_type="'+levelWiseResult[i].name+'" style="cursor: pointer;" attr_comment="No" class="overAllMeetingCls" id="partyMeetingsIdE'+i+'"  onclick="overAllMeetings(this.id);">'+levelWiseResult[i].mayBeCount+'</span><span class="font-10 text-customColor"> '+levelWiseResult[i].mayBeCountPer+'%</span><span class="glyphicon glyphicon-info-sign updationDetailsCls" style="cursor: pointer;margin-left: 4px;font-size:14px;" attr_level_type="'+levelWiseResult[i].name+'"></span></h4>';
								  }else{
									  str+='<p class="text-muted text-capital">maybe</p>';	
	                             str+='<h4><span attr_meeting_status="M" attr_level_type="'+levelWiseResult[i].name+'" style="cursor: pointer;" attr_comment="No" class="overAllMeetingCls" id="partyMeetingsIdE'+i+'"  onclick="overAllMeetings(this.id);">'+levelWiseResult[i].mayBeCount+'</span><span class="font-10 text-customColor"> '+levelWiseResult[i].mayBeCountPer+'%</span><!--<span class="glyphicon glyphicon-info-sign updationDetailsCls" style="cursor: pointer;margin-left: 4px;font-size:14px;" attr_level_type="'+levelWiseResult[i].name+'"></span>--></h4>';
								  }
							  }else{
								 str+='<p class="text-muted text-capital">maybe</p>';	
	                             str+='<h4>'+levelWiseResult[i].mayBeCount+' <span class="font-10 text-customColor"> '+levelWiseResult[i].mayBeCountPer+'%</span></h4>';
							  }
							str+='</td>';
						str+='<td>';
					   if(levelWiseResult[i].notUpdatedCommentCnt > 0 && levelWiseResult[i].totalNotUpdatedCnt > 0){
						str+='<p class="text-muted text-capital">Not Updated&nbsp&nbsp<a attr_meeting_status="NU" attr_level_type="'+levelWiseResult[i].name+'" attr_comment="yes" style="color: rgb(173, 173, 173);cursor: pointer;text-decoration: none;" class="glyphicon glyphicon-comment overAllMeetingCls" id="partyMeetingsIdG'+i+'"  onclick="overAllMeetings(this.id);" data-toggle="tooltip" data-placement="top" title="Not Updated Meeting Comment '+levelWiseResult[i].notUpdatedCommentCnt+'('+levelWiseResult[i].notUpdatedCommentCntPer+'%)" data-original-title=""></a></p>'; 
					     str+='<h4>'+levelWiseResult[i].totalNotUpdatedCnt+' <span class="font-10 text-customColor"> '+levelWiseResult[i].totalNotUpdatedCntPer+'%</span></h4>';
					     }else if(levelWiseResult[i].totalNotUpdatedCnt > 0){
						  str+='<p class="text-muted text-capital">Not Updated</p>';	 
	                      str+='<h4 class="overAllMeetingCls" id="partyMeetingsIdF'+i+'"  onclick="overAllMeetings(this.id);" attr_meeting_status="NU" attr_level_type="'+levelWiseResult[i].name+'" attr_comment="No" style="cursor: pointer;">'+levelWiseResult[i].totalNotUpdatedCnt+' <span class="font-10 text-customColor"> '+levelWiseResult[i].totalNotUpdatedCntPer+'%</span></h4>';
						  }else {
						  str+='<p class="text-muted text-capital">Not Updated</p>';	 
	                      str+='<h4>'+levelWiseResult[i].totalNotUpdatedCnt+' <span class="font-10 text-customColor"> '+levelWiseResult[i].totalNotUpdatedCntPer+'%</span></h4>';
				 		  }
						 str+='</td>';
					 str+='</tr>';
				 str+='</tbody></table>';
				 if($(document).width() < 300)
				 {
					 str+='</div>';
				 }
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
	    
		  var jsObj ={ 
		             activityMemberId : globalActivityMemberId,
					 userTypeId : globalUserTypeId,
					 stateId : globalStateId,
					 fromDate : customStartDateMeetings,
					 toDate : customEndDateMeetings,
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
			  str+='<div id="genSecMeeting'+i+'" style="height:80px;"></div>';
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
				str+='<div id="genSecMeeting'+i+'" style="height:100px;"></div>';
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
			$(".meetingsRefresh").attr("attr_refresh_status","false");
			$(".meetingsRefresh").attr("attr_meeting_type","committeeMeeting");
			$(".meetingsHiddenBlock,.moreMeetingsBlocks").hide();
			$(".moreMeetingsBlocks1").hide();
			$(".moreMeetingsBlocksDetailed").hide();
			$(".moreMeetingsBlocksComparision").hide();
			$(".stateGeneralMeeting,.specialMeetings,.stateLevelMeetingsExpand,.statelevelSessionMeeting").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen")
		}else{
			//getUserTypeWiseTotalEligibleAndAttendedCnt();
			$(".meetingsRefresh").attr("attr_refresh_status","true");
			$(".meetingsRefresh").attr("attr_meeting_type","committeeMeeting");
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
			$(".districtAltCtnCls ,.alertLocationDiv,.dateRangePickerClsForAlert").hide();
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
	
		
	});
	$(document).on("click",".stateLevelMeetingsExpand",function(){
		$(".stateGeneralMeeting").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen");
		var mainMeetingTypeId = $(this).attr("attr_main_type_meeting_id");
		var partymeetingtypeidsstring = $(this).attr("attr_partymeetingtypeidsstring");
		$(".showMoreBlockCls").attr("attr_main_type_meeting_id",mainMeetingTypeId);
	    $(".showMoreBlockCls").attr("attr_meeting_type_id",partymeetingtypeidsstring);
		  $(".showMoreBlockCls").attr("attr_selected_type","stateLevel");
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
			$(".meetingsRefresh").attr("attr_refresh_status","false");
			$(".stateLevelMeetingBlock").hide();
			$(".dateRangePickerClsForMeetings").addClass("hide");
			$(".meetingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".meetingsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
			$(".meetingsIconExpand").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen")
		}else{
			$(".meetingsRefresh").attr("attr_refresh_status","true");
		   $(".meetingsRefresh").attr("attr_meeting_type","stateAndSpecialTypeMeeting");
		   $(".meetingsRefresh").attr("attr_main_type_meeting_id",mainMeetingTypeId);
	       $(".meetingsRefresh").attr("attr_partymeetingtypeidsstring",partymeetingtypeidsstring);
		   $(".dateRangePickerClsForMeetings").removeClass("hide");
		   $(".meetingsIconExpand").find("i").addClass("glyphicon-resize-small").removeClass("glyphicon-fullscreen")
		   getCommitteesAndPublicRepresentativeMembersInvitedAndAttendedToMeetings(mainMeetingTypeId,partymeetingtypeidsstring);	
		}
	});
	$(document).on("click",".stateGeneralMeeting",function(){
		var attrMainTypeMeetingId = $(this).attr("attr_main_type_meeting_id");
		var meetingTypeId = $(this).attr("attr_meeting_type_id");
		$(".stateLevelMeetingsExpand,.specialMeetings,.statelevelSessionMeeting").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen")
		
		$(".showMoreBlockCls").attr("attr_main_type_meeting_id",attrMainTypeMeetingId);
		$(".showMoreBlockCls").attr("attr_meeting_type_id",meetingTypeId);
		$(".showMoreBlockCls").attr("attr_selected_type","stateLevel");
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
			$(".meetingsRefresh").attr("attr_refresh_status","false");
			$(".stateLevelMeetingBlock").hide();
			$(".dateRangePickerClsForMeetings").addClass("hide");
			$(".meetingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".meetingsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
			$(".meetingsIconExpand").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen")
		}else{
			$(".meetingsRefresh").attr("attr_refresh_status","true");
			$(".meetingsRefresh").attr("attr_main_type_meeting_id",attrMainTypeMeetingId);
	        $(".meetingsRefresh").attr("attr_partymeetingtypeidsstring",meetingTypeId);
			$(".meetingsRefresh").attr("attr_meeting_type","stateAndSpecialTypeMeeting");
			$(".stateGeneralMeeting").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen")
			$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".stateLevelMeetingBlock").show();
			$(".showMoreBlockCls,.moreMeetingsBlocksIcon").show();
			$(".dateRangePickerClsForMeetings").removeClass("hide");
			$(".meetingsIconExpand").find("i").addClass("glyphicon-resize-small").removeClass("glyphicon-fullscreen")
			getCommitteesAndPublicRepresentativeMembersInvitedAndAttendedToMeetings(attrMainTypeMeetingId,meetingTypeId);
		}
	});
	//santosh
	    $(document).on("click",".statelevelSessionMeeting",function(){
	   var partyMeetingId = $(this).attr("party_meetingId");
		var partyMeetingTypeId = $(this).attr("party_meeting_type_id");
		var partyMeetingMainTypeId = 3;
		var partyMeetingIdArr = [];
		partyMeetingIdArr.push(partyMeetingId);
		//var attrMainTypeMeetingId = $(this).attr("attr_main_type_meeting_id");
		//var meetingTypeId = $(this).attr("attr_meeting_type_id");
		  $(".stateLevelMeetingsExpand,.specialMeetings,.stateGeneralMeeting").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen")
		
		  $(".showMoreBlockCls").attr("attr_selected_type","sessionMeeting");
		  //$(".showMoreBlockCls").attr("attr_meeting_type_id",meetingTypeId);
		$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		
		$(".showMoreBlockCls,.moreMeetingsBlocksIcon").show();
		$(".moreMeetingsBlocksIcon").removeClass("unExpandBlock");
		$(".moreMeetingsBlocksIcon").attr("party_meeting_id","0");
		if(!$(".meetingsIconExpand").find("i").hasClass("glyphicon-resize-small"))
		{
			
			$(".meetingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".meetingsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
		}
		$(".meetingsHiddenBlock,.moreMeetingsBlocksDetailed,.moreMeetingsBlocksComparision,moreMeetingsBlocksDetailed,.moreMeetingsBlocks1").hide();
		$(".moreMeetingsBlocksIcon").attr("party_meeting_id",$(this).attr("party_meetingId"));
		//console.log("party_meeting_id :  123456");
		if(!$(this).find("i").hasClass("glyphicon-resize-small"))
		{
			$(".meetingsRefresh").attr("attr_refresh_status","false");
			$(".stateLevelMeetingBlock").hide();
			$(".dateRangePickerClsForMeetings").addClass("hide");
			$(".meetingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".meetingsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
			$(".meetingsIconExpand").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen")
		}else{
			$(".meetingsRefresh").attr("attr_refresh_status","true");
			$(".meetingsRefresh").attr("attr_main_type_meeting_id",partyMeetingMainTypeId);
	        $(".meetingsRefresh").attr("attr_partymeetingtypeidsstring",partyMeetingTypeId);
	        $(".meetingsRefresh").attr("attr_special_meeting_id",partyMeetingId);
			$(".meetingsRefresh").attr("attr_meeting_type","specailSessionTypeMeeting");
			$(".statelevelSessionMeeting").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen")
			$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".stateLevelMeetingBlock").show();
			$(".showMoreBlockCls,.moreMeetingsBlocksIcon").show();
			$(".dateRangePickerClsForMeetings").removeClass("hide");
			$(".meetingsIconExpand").find("i").addClass("glyphicon-resize-small").removeClass("glyphicon-fullscreen")
			//getstatelevelSessionMettings();
			setSessioncolorsForStatus();
		    getCommitteesAndPublicRepresentativeMembersInvitedAndAttendedToSeeionWiseMeetingDtls(partyMeetingMainTypeId,partyMeetingTypeId,partyMeetingIdArr);
		
		}
		
	}); 
	$(document).on("click",".specialMeetings",function(){
		var mainMeetingTypeId = $(this).attr("attr_main_type_meeting_id");
		var partymeetingtypeidsstring = $(this).attr("attr_partymeetingtypeidsstring");
		$(".stateLevelMeetingsExpand,.statelevelSessionMeeting").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen")
		$(".showMoreBlockCls").attr("attr_main_type_meeting_id",mainMeetingTypeId);
	    $(".showMoreBlockCls").attr("attr_meeting_type_id",partymeetingtypeidsstring);
	    $(".showMoreBlockCls").attr("attr_selected_type","allMeeings");
		$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".showMoreBlockCls,.moreMeetingsBlocksIcon,.stateLevelMeetingBlock").show();
		if(!$(".meetingsIconExpand").find("i").hasClass("glyphicon-resize-small"))
		{
			$(".meetingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".meetingsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
		}
		if(!$(this).find("i").hasClass("glyphicon-resize-small"))
		{
			$(".meetingsRefresh").attr("attr_refresh_status","false");
			$(".stateLevelMeetingBlock,.moreMeetingsBlocksDetailed,.meetingsHiddenBlock").hide();
			$(".dateRangePickerClsForMeetings").addClass("hide");
			$(".meetingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".meetingsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
			$(".meetingsIconExpand").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen")
		}else{
			$(".meetingsRefresh").attr("attr_refresh_status","true");
			$(".meetingsRefresh").attr("attr_meeting_type","stateAndSpecialTypeMeeting");
			$(".meetingsRefresh").attr("attr_main_type_meeting_id",mainMeetingTypeId);
	        $(".meetingsRefresh").attr("attr_partymeetingtypeidsstring",partymeetingtypeidsstring);
			$(".stateGeneralMeeting").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen")
			$(".dateRangePickerClsForMeetings").removeClass("hide");
			$(".moreMeetingsBlocksDetailed,.moreMeetingsBlocks1").hide();
			$(".meetingsIconExpand").find("i").addClass("glyphicon-resize-small").removeClass("glyphicon-fullscreen")
			getCommitteesAndPublicRepresentativeMembersInvitedAndAttendedToMeetings(mainMeetingTypeId,partymeetingtypeidsstring);
       }
	});
	
	$(document).on("click",".sessionsCls",function(){
		$('.sessionsCls').removeClass('active');
		var sessionId = $(this).attr("attr_sessionId");
		var partyMeetingId = $(this).attr("attr_party_meeting_id");
		getParyMeetingDetailsDistrictWise(sessionId,partyMeetingId,"refreshBtn");      
		$(this).addClass('active');
	});
	$(document).on("click",".moreMeetingsBlocksIcon",function(){
		$(this).addClass("unExpandBlock");
		var selectedType = $(this).parent().attr("attr_selected_type");
		 if(selectedType != null && selectedType != undefined && selectedType=="sessionMeeting"){
			  $("#meetingLevelHIghChartsDivId").html(' ');
			 $("#userAccessLevelLocationDivId").html(' ');
			 $("#districtWisePartyMeetingTypeDivId").html(' ');
			  $("#districtWiseSpecialMeetingsGraph").html(' ');
			 $("#partyMeetingOverviewTabDiv").html(' ');
			 $(".moreMeetingsBlocksDetailed").show();
			  $(".meetingPerformancCls").hide();
			 var party_meeting_id = $(this).attr("party_meeting_id");
			//console.log(party_meeting_id);
			 getPartySpecialMeetingsMainTypeOverview(party_meeting_id);
			 getParyMeetingDetailsDistrictWise(0,party_meeting_id,"");
		 }else{
			
			 $("#districtWiseSpecialMeetingsGraph").html(' ');
			 $("#partyMeetingOverviewTabDiv").html(' ');
			var mainTypeMeetingId = $(this).parent().attr("attr_main_type_meeting_id");
			var meetingtypeId = $(this).parent().attr("attr_meeting_type_id");
			if(mainTypeMeetingId != null && mainTypeMeetingId!=undefined && mainTypeMeetingId==2 || mainTypeMeetingId==3){
			$(".moreMeetingsBlocksDetailed").show();	
			$(".meetingChortCls").hide();
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
			//buildStatelevelSpecialMeetingsPerformanceReport(); 
		 }
	});
	$(document).on("click",".unExpandBlock",function(){
		$(this).removeClass("unExpandBlock");
		$(".moreMeetingsBlocksDetailed").hide();
		$(".moreMeetingsBlocksComparision").hide();
		$(".moreMeetingsBlocks1").hide();
		$(".meetingComparisionBlock").removeClass("active");
		$(".meetingDetailedBlock").addClass("active");
		$("#meetingMemberDtlsDivId").html('');      
	});

function getMeetingLevelDetails(){
   $("#meetingLevelHIghChartsDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var partyMeetingTypeArr=[];
		  $("#committeeTypeId li").each(function() {
		  if($(this).find("input").is(":checked")){
			  partyMeetingTypeArr.push($(this).find("input").attr("id"));
		  }
	       });
		
		var jsObj ={ 
		             activityMemberId : globalActivityMemberId,
					 stateId : globalStateId,
					 fromDate : customStartDateMeetings,
					 toDate : customEndDateMeetings,
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
		 
    	   $("#childUserTypeDetailsDivIdForMeeting").html('');
	       $("#childActivityMemberDivIdForMeeting").html(' ');
		   $("#directChildActivityMeetingMemberDiv").html(' ');
		   $("#topPoorLocationsMeetingDiv").html('');
		
		var jsObj = { parentUserTypeId : globalUserTypeId }
		$.ajax({
			type : 'POST',
			url : 'getAllItsSubUserTypeIdsByParentUserTypeIdAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
			 buildgetChildUserTypesByItsParentUserTypeForMeeting(result)	
			}else{
			 $("#childUserTypeDetailsDivIdForMeeting").html('NO DATA AVAILABLE.');	
			}
		});			 
	}
	function buildgetChildUserTypesByItsParentUserTypeForMeeting(result){
		
		var str='';
		 str+='<ul class="comparisonSelect">';
		 
		 var firstChildUserTypeIdString;
		 var userType;
		 if(result !=null && result.length >0){
			 firstChildUserTypeIdString = result[0].shortName;
			 userType=result[0].userType;
			 for(var i in result){
				 str+='<li attr_usertypeid="'+result[i].shortName+'" attr_userType=\''+result[i].userType+'\'  class="childUserTypeClsForMeeting">'+result[i].userType+'<span class="closeIconComparison"></span></li>';
			 }
		 }
		str+='</ul>';
		$("#childUserTypeDetailsDivIdForMeeting").html(str);
		$(".comparisonSelect li:first-child").addClass("active")
		
		getSelectedChildUserTypeMembersWithMeetingsCount(firstChildUserTypeIdString,userType);
		
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
	   
	  var jsObj ={ 
	               parentActivityMemberId : parentActivityMemberId,
				   childUserTypeIdsArray : childUserTypeIdsArray,
				   state : state,
				   partyMeetingTypeIds : partyMeetingTypeArr,
				   startDateString : customStartDateMeetings,
				   endDateString : customEndDateMeetings
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
	   
	  var jsObj ={ 
	               activityMemberId : activityMemberId,
				   userTypeId : userTypeId,
				   state :state,
				   partyMeetingTypeIds : partyMeetingTypeArr,
				   startDateString : customStartDateMeetings,
				   endDateString : customEndDateMeetings
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
	  
	   
	   var jsObj ={  activityMemberId : activityMemberId,
					 state:state,
					 partyMeetingTypeIds : partyMeetingTypeArr,
 			         startDateString :  customStartDateMeetings ,
					 endDateString   :  customEndDateMeetings
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
	   
		var jsObj ={ 
		             activityMemberId : globalActivityMemberId,
					 stateId : globalStateId,
					 fromDate : customStartDateMeetings,
					 toDate : customEndDateMeetings,
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
		 getPartySpecialMeetingsMainTypeOverview(0); 
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
			getPartySpecialMeetingsMainTypeOverview(0);
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
	$("#stateLevelMeetingBasicCnt").closest(".panelBlock").show();
	$("#stateLevelMeetingBasicCnt").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	   var partyMeetingTypeArr=[];
	   $("#stateLevelMeetingUlId li").each(function() {
		  if($(this).find("input").is(":checked")){
			  partyMeetingTypeArr.push($(this).find("input").attr("id"));
		  }
	   });   
	    $(".specialMeetingBtnClsNew").each(function(){
	});  
 	    var state = globalState
		var jsObj ={ 
		             partyMeetingMainTypeId : 2,
					 state : state,
					 startDateString : customStartDateMeetings,
					 endDateString : customEndDateMeetings,
				     partyMeetingTypeIds:partyMeetingTypeArr,
					 partyMeetingId:0
				  }
		$.ajax({
			type : 'POST',
			url : 'getPartyMeetingsMainTypeOverViewDataAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#stateLevelMeetingBasicCnt").html(" ");
		    if(result != null && result.length > 0){
				buildPartyMeetingOverviewRslt(result,"stateLevelMeetingBasicCnt",2,"stateLevelMeetingsExpandId",partyMeetingTypeArr,customStartDateMeetings,customEndDateMeetings,state);
			}else{
			  $("#stateLevelMeetingBasicCnt").html("<div class='col-md-12 col-xs-12 col-sm-12'>NO DATA AVAILABLE.</div>");	
			  $("#stateLevelMeetingBasicCnt").closest(".panelBlock").hide();
			}
		});
	
}
//Spacial
function getPartySpecialMeetingsMainTypeOverview(partyMeetingId){
	if(partyMeetingId == 0){
		$("#specialMeetingBasicCnt").closest(".panelBlock").show();
		$("#specialMeetingBasicCnt").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		partyMeetingId="0";
	}
	 $(".specialMeetingBtnClsNew").each(function(){
		if($(this).hasClass('specialMeetingsDate'))
		{
			globalStartDateForSpecialMeeting = $(this).attr("attr_startDate");
		    globalEndDateForSpecialMeeting = $(this).attr("attr_endDate");
			$(this).removeClass('specialMeetingsDate');
		}
	}); 
		var partyMeetingTypeArr=[];
		$("#specialMeetingUlId li").each(function() {
		  if($(this).find("input").is(":checked")){
			  partyMeetingTypeArr.push($(this).find("input").attr("id"));
		  }
	   }); 
	    var state = globalState;
	    
		var jsObj ={ 
			 partyMeetingMainTypeId : 3,
			 state : state,
			 startDateString : globalStartDateForSpecialMeeting,
			 endDateString : globalEndDateForSpecialMeeting,
			 partyMeetingTypeIds:partyMeetingTypeArr,
			 partyMeetingId:partyMeetingId
			 
		  }
		$.ajax({
			type : 'POST',
			url : 'getPartyMeetingsMainTypeOverViewDataAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			
		   if(result != null && result.length > 0){
			   if(partyMeetingId >0 ){//111
				   //partyMeetingOverviewTabDiv
				   buildOverviewPartiMeetingOverviewResustlt(result,"specialMeetingBasicCnt",3,"specialMeetingsExpandId",partyMeetingTypeArr,globalStartDateForSpecialMeeting,globalEndDateForSpecialMeeting,state,partyMeetingId);
			   }else{
				   $("#specialMeetingBasicCnt").html(" ");
				buildPartyMeetingOverviewRslt(result,"specialMeetingBasicCnt",3,"specialMeetingsExpandId",partyMeetingTypeArr,globalStartDateForSpecialMeeting,globalEndDateForSpecialMeeting,state);
			   }
		   }else{
			$("#specialMeetingBasicCnt").html("NO DATA AVAILABLE.");	   
			$("#specialMeetingBasicCnt").closest(".panelBlock").hide();
		   }  
		});
}

// 
function buildOverviewPartiMeetingOverviewResustlt(result,divId,mainTypeMeetingId,expandTypeId,partyMeetingTypeArr,fromDateStr,toDateStr,state,partyMeetingId){
	//for(var i in result){
		var partyMeetingArr = result[0].subList1[0];
		var hasSession ="YES";
		if(partyMeetingArr.subList1 != null && partyMeetingArr.subList1.length>0){
			var sessionName = partyMeetingArr.subList1[0].name.toUpperCase();
			if(sessionName == "ATTENDENCE")
				hasSession = "NO";
		}
		var str ='';
		str+='<div class="row m_top10" style="margin-top:20px;">';
			str+='<div class="col-md-4 col-xs-12 col-sm-6">';
			str+='<div class="panel panel-default">';
			 if(hasSession == "NO"){
				 str+=' <div class="panel-heading" style="text-align:center;background-color:#fff;"> ATTENDENCE</div>';
			 }else{
				 str+=' <div class="panel-heading" style="text-align:center;background-color:#fff;"> ATTENDENCE IN ALL SESSIONS </div>';  
			 }  
			 
			  str+='<div class="panel-body">';
				str+='<table class="table border_top_noneCoh tablenthChild">';
					str+='<tbody>';
						str+='<tr>';
								if(partyMeetingArr.attendedCount == 0 || partyMeetingArr.attendedCount == null){
									str+='<td class="text-success" style="text-align:center" >Total&nbsp;Attended<br> 0 </td>';
								}else{
									str+='<td class="text-success getCmtMemDtls" style="text-align:center;cursor:pointer;" attr_party_meeting_id="'+partyMeetingId+'" attr_session_id="0" attr_status="attended" attr_non_invitee="false" >Total&nbsp;Attended<br><u>'+partyMeetingArr.attendedCount+'</u></td>';
								}
								if(partyMeetingArr.lateAttendedCount == 0 || partyMeetingArr.lateAttendedCount == null){
									str+='<td class="text_oragane" style="text-align:center" >Late&nbsp;Attended<br> 0 </td>';
								}else{
									str+='<td class="text_oragane getCmtMemDtls" style="text-align:center;cursor:pointer;" attr_party_meeting_id="'+partyMeetingId+'" attr_session_id="0" attr_status="late" attr_non_invitee="false" >Late&nbsp;Attended<br> <u>'+partyMeetingArr.lateAttendedCount+' </u></td>';
								}
								if(partyMeetingArr.notAttendedCount == 0 || partyMeetingArr.notAttendedCount == null){
									str+='<td style="text-align:center">Absent<br> 0  </td>';
								}else{
									str+='<td class="getCmtMemDtls" style="text-align:center;cursor:pointer;" attr_party_meeting_id="'+partyMeetingId+'" attr_session_id="0" attr_status="absent" attr_non_invitee="false" >Absent<br> <u>'+partyMeetingArr.notAttendedCount+' </u></td>';
								}	
						str+='</tr>';
					str+='</tbody>';
				str+='</table>';
			  str+='</div>';
			str+='</div>';
		str+='</div>';
		
		if(hasSession =='YES')
		{
			if(partyMeetingArr.subList1 != null && partyMeetingArr.subList1.length>0){
				for(var k in partyMeetingArr.subList1){
					str+='<div class="col-md-4 col-xs-12 col-sm-6">';
					str+='<div class="panel panel-default">';
					 str+=' <div class="panel-heading" style="color:#35009B;text-align:center;background-color:#fff;">'+partyMeetingArr.subList1[k].name.toUpperCase()+'</div>';
					  str+='<div class="panel-body">';
						str+='<table class="table border_top_noneCoh tablenthChild">';
							str+='<tbody>';
								str+='<tr>';  
									var sessionId = partyMeetingArr.subList1[k].id;  
									
									if(partyMeetingArr.subList1[k].attendedCount == 0 || partyMeetingArr.subList1[k].attendedCount == null){
										str+='<td class="text-success" style="text-align:center" >Total&nbsp;Attended<br> 0 </td>';
									}else{
										str+='<td class="text-success getCmtMemDtls" style="text-align:center;cursor:pointer;" attr_party_meeting_id="'+partyMeetingId+'" attr_session_id="'+sessionId+'" attr_status="attended" attr_non_invitee="false">Total&nbsp;Attended<br> <u>'+partyMeetingArr.subList1[k].attendedCount+' </u></td>';
									}
									if(partyMeetingArr.subList1[k].lateAttendedCount == 0 || partyMeetingArr.subList1[k].lateAttendedCount == null){
										str+='<td class="text_oragane" style="text-align:center" >Late&nbsp;Attended<br> 0 </td>';
									}else{
										str+='<td class="text_oragane getCmtMemDtls" style="text-align:center;cursor:pointer;" attr_party_meeting_id="'+partyMeetingId+'" attr_session_id="'+sessionId+'" attr_status="late" attr_non_invitee="false" >Late&nbsp;Attended<br><u>'+partyMeetingArr.subList1[k].lateAttendedCount+'</u></td>';
									}
									if(partyMeetingArr.subList1[k].notAttendedCount == 0 || partyMeetingArr.subList1[k].notAttendedCount == null){
										str+='<td style="text-align:center" >Absent<br> 0 </td>';    
									}else{
										str+='<td class="getCmtMemDtls" style="text-align:center;cursor:pointer;" attr_party_meeting_id="'+partyMeetingId+'" attr_session_id="'+sessionId+'" attr_status="absent" attr_non_invitee="false">Absent<br><u>'+partyMeetingArr.subList1[k].notAttendedCount+'</u></td>';
									}  
									if(partyMeetingArr.subList1[k].nonInviteeCount == 0 || partyMeetingArr.subList1[k].nonInviteeCount == null){
										str+='<td><span data-toggle="tooltip" data-placement="top" title="Non Invitees Attended" style="cursor:default;"> NIA</span><br> 0 </td>'; 
									}else{
										str+='<td class="getCmtMemDtls" style="text-align:center;cursor:pointer;" attr_party_meeting_id="'+partyMeetingId+'" attr_session_id="'+sessionId+'" attr_status="attended" attr_non_invitee="true" ><span data-toggle="tooltip" data-placement="top" title="Non Invitees Attended" > NIA</span><br><u>'+partyMeetingArr.subList1[k].nonInviteeCount+'</u></td>';
									}
								str+='</tr>';
							str+='</tbody>';
						str+='</table>';
					  str+='</div>';
					str+='</div>';
				str+='</div>';
				}
			}		
		}
			
		$('#partyMeetingOverviewTabDiv').html(str);
		$('[data-toggle="tooltip"]').tooltip();
	//}
}
function buildPartyMeetingOverviewRslt(result,divId,mainTypeMeetingId,expandTypeId,partyMeetingTypeArr,fromDateStr,toDateStr,state){
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
				 str+='<h4 attr_meeting_type_id="'+result[i].id+'" attr_main_type_meeting_id='+mainTypeMeetingId+'  class="text-capital meetingTypeCls">'+result[i].name+'';
				 str+='</h4>';
				 //<span class="stateGeneralMeeting" attr_meeting_type_id="'+result[i].id+'" attr_main_type_meeting_id='+mainTypeMeetingId+' style="background-color:#fff;"><i class="glyphicon glyphicon-fullscreen"></i></span>';
				 
				//if($(window).width() < 300)
				//{
					str+='<div class="table-responsive">';
				//}
				str+='<table class="table tableTraining bg_ED">';
					 str+='<tbody><tr>';
						 str+='<td>';
							 str+='<h4>'+result[i].noOfMeetings+'</h4>';
							  str+='<p class="text-muted text-capital">total meetings</p>';
						 str+='</td>';
						 str+='<td>';
						 str+='<h4 attr_search = "notrequired" attr_meeting_id="'+result[i].id+'" class="meetingMemberDtlsCls" style="cursor:pointer;" attr_state="'+state+'" attr_status="invited" attr_main_type_id="'+mainTypeMeetingId+'" attr_meeting_type_arr="'+partyMeetingTypeArr+'" attr_start_date="'+fromDateStr+'" attr_end_date="'+toDateStr+'">'+result[i].invitedCount+'<span class="font-10 text-success"></span></h4>';
						  str+='<p class="text-muted text-capital">Invited</p>';
						 str+='</td>';
						 str+='<td class="text-muted text-capital">  </td>';
						 str+='<td class="text-muted text-capital">  </td>';
						 str+='<td class="text-muted text-capital" >  </td>';
						 str+='<td class="text-muted text-capital" >  </td>';
						 /*str+='<td>';//late
							 str+='<h4 attr_meeting_id="'+result[i].id+'" class="meetingMemberDtlsCls" style="cursor:pointer;" attr_state="'+state+'" attr_status="attended" attr_main_type_id="'+mainTypeMeetingId+'" attr_meeting_type_arr="'+partyMeetingTypeArr+'" attr_start_date="'+fromDateStr+'" attr_end_date="'+toDateStr+'">'+result[i].attendedCount+' <span class="font-10 text-success"> '+result[i].attendedPerc+'%</span></h4>';
							  str+='<p class="text-muted text-capital text-success">Attended</p>';//late
						 str+='</td>';*/
						/*  if(result[i].subList1 != null && result[i].subList1.length>0){
							  var attendedCount = parseInt(result[i].attendedCount);
								var lateCount = parseInt(result[i].lateAttendedCount);								
								var perc = (lateCount*100.0/attendedCount);
								
								str+='<td>';
								 str+='<h4  class="" style="cursor:pointer;" >'+result[i].lateAttendedCount+'<span class="font-10 text-danger">'+perc.toFixed(2)+'%</span></h4>';
								  str+='<p class="text-muted text-capital text_oragane">Late</p>';
							 str+='</td>';  
						  }
						  */
						 /*   var invitedCount = parseInt(result[i].invitedCount);
							var lateCount = parseInt(result[i].lateAttendedCount);								
							var perc = (lateCount*100.0/invitedCount);
								
								
						 str+='<td>';  
							 str+='<h4 attr_meeting_id="'+result[i].id+'" class="meetingMemberDtlsCls" style="cursor:pointer;" attr_state="'+state+'" attr_status="absent" attr_main_type_id="'+mainTypeMeetingId+'" attr_meeting_type_arr="'+partyMeetingTypeArr+'" attr_start_date="'+fromDateStr+'" attr_end_date="'+toDateStr+'">'+result[i].notAttendedCount+' <span class="font-10 text-danger"> '+perc.toFixed(2)+'%</span></h4>';
							  str+='<p class="text-muted text-capital">Absent</p>';    
						 str+='</td>';
						 */
						//	str+='<td>';  
							/* str+='<h4 attr_meeting_id="'+result[i].id+'" class="meetingMemberDtlsCls" style="cursor:pointer;" attr_state="'+state+'" attr_status="absent" attr_main_type_id="'+mainTypeMeetingId+'" attr_meeting_type_arr="'+partyMeetingTypeArr+'" attr_start_date="'+fromDateStr+'" attr_end_date="'+toDateStr+'">'+result[i].nonInviteeCount+' </h4>';*/
							//  str+='<p class="text-muted text-capital"> Non Invitees </p>';    
						 //str+='</td>';   						 
					 str+='</tr>';
					 
					 
					 if(result[i].subList1 != null && result[i].subList1.length>0){
						 for(var k in result[i].subList1){							 
							 if(result[i].subList1[k].subList1.length != null && result[i].subList1[k].subList1.length >0){
								 for(var s in result[i].subList1[k].subList1){
									 if(s==0){
										 
										 str+='<tr style="border-top: 1px solid rgb(211, 211, 211);"><td style="padding:0px;"><hr class="m_0" ></td></tr>';
										 
										 str+='<tr>';
											str+='<td colspan=6>';
												str+='<span data-toggle="tooltip" data-placement="top" title="Total Available Sessions('+result[i].subList1[k].subList1.length+')" style="cursor:default;font-weight:bold;">'+result[i].subList1[k].name+'  </span>';
												str+='<span class="statelevelSessionMeeting"  party_meeting_type_id="'+result[i].id+'"  party_meetingId="'+result[i].subList1[k].id+'" style="background-color:#fff;padding:4px;margin-right: 15px; margin-left: 15px;""><i class="glyphicon glyphicon-fullscreen "  style="cursor:pointer;"></i></span>';
												str+='<span class="text-capital">Total - Invitees : <b>'+result[i].subList1[k].invitedCount+'</b></span>';
												str+='</td>';
										 str+='</tr>';
										  str+='<tr>';
												str+='<td></td>';
											 str+='<td class="text-muted text-capital text-success"><p>Attended</p></td>';
											 str+='<td class="text-muted text-capital text_oragane"> <p>Late </p></td>';
											 str+='<td class="text-muted text-capital" ><p> Absent </p></td>';
											 str+='<td class="text-muted text-capital" ><p data-toggle="tooltip" data-placement="top" title="Non Invitees">NI</p> </td>';
										 str+='</tr>';
									 }
									  str+='<tr>';
									  
								 str+='<td style="padding:0px;">';
									  str+='<p class="text-muted f_12 specialMeeColorA" style="margin-left:16px !important;">'+result[i].subList1[k].subList1[s].name+'</p>';
								 str+='</td>';
								 
								 str+='<td>';
								 
								 var sessionId = parseInt(result[i].subList1[k].subList1[s].id);
								/* if(parseInt(result[i].subList1[k].id) == 445221){
									 sessionId = 3;
								 }
								 else if(parseInt(result[i].subList1[k].id) ==445222)
									sessionId = 1;
								
								console.log("111 "+sessionId);
								*/
								 if(result[i].subList1[k].subList1[s].attendedCount == 0 || result[i].subList1[k].subList1[s].attendedCount == null ){
									 str+='<h5 class="text-success"> 0  <span class="font-10 text-success">  0.00 %</span></h5>';
								 }else{
									 str+='<h5 class="text-success getCmtMemDtls" style="cursor:pointer;" attr_position="overview" attr_status="attended" attr_session_id="'+sessionId+'" attr_party_meeting_id="'+result[i].subList1[k].id+'" attr_non_invitee="false"><u>'+result[i].subList1[k].subList1[s].attendedCount+'</u> <span class="font-10 text-success"> '+result[i].subList1[k].subList1[s].attendedPerc+'%</span></h5>';
								 }
								 
								 str+='</td>';
								 str+='<td>';
								 if(result[i].subList1[k].subList1[s].lateAttendedCount == 0 || result[i].subList1[k].subList1[s].lateAttendedCount == null ){
									 str+='<h5 class="text_oragane"> 0  <span class="font-10 text-danger"> 0.00 %</span> </h5>';
								 }else{
									var attendedCount = parseInt(result[i].subList1[k].subList1[s].attendedCount);
									var lateCount = parseInt(result[i].subList1[k].subList1[s].lateAttendedCount);
									
									var perc = (lateCount*100.0/attendedCount);
													
									 str+='<h5 class="text_oragane getCmtMemDtls" style="cursor:pointer;" attr_position="overview" attr_status="late" attr_session_id="'+sessionId+'" attr_party_meeting_id="'+result[i].subList1[k].id+'" attr_non_invitee="false"><u>'+result[i].subList1[k].subList1[s].lateAttendedCount+'</u> <span class="font-10 text-danger"> '+perc.toFixed(2)+'%</span> </h5>';
								 }
									
								 str+='</td>';
								 str+='<td>';
								 if(result[i].subList1[k].subList1[s].notAttendedCount == 0 || result[i].subList1[k].subList1[s].notAttendedCount == null ){
									 str+='<h5> 0  <span class="font-10 text-danger">  0.00 %</span></h5>';
								 }else{
									 
									 var attendedCount = parseInt(result[i].subList1[k].invitedCount);
									var lateCount = parseInt(result[i].subList1[k].subList1[s].notAttendedCount);
									
									var perc = (lateCount*100.0/attendedCount);
									
									 str+='<h5 class="getCmtMemDtls" style="cursor:pointer;" attr_position="overview" attr_status="absent" attr_session_id="'+sessionId+'" attr_party_meeting_id="'+result[i].subList1[k].id+'" attr_non_invitee="false"><u>'+result[i].subList1[k].subList1[s].notAttendedCount+' </u><span class="font-10 text-danger"> '+perc.toFixed(2)+'%</span></h5>';
								 }   
								 
								 str+='</td>';
									 
								str+='<td style="text-align:center;">';
								 if(result[i].subList1[k].subList1[s].nonInviteeCount == 0 || result[i].subList1[k].subList1[s].nonInviteeCount == null ){
									 str+='<h5>  - </h5>';
								 }else{
									 str+='<h5 class="getCmtMemDtls" style="cursor:pointer;" attr_position="overview" attr_status="attended" attr_session_id="'+sessionId+'" attr_party_meeting_id="'+result[i].subList1[k].id+'" attr_non_invitee="true"><u>'+result[i].subList1[k].subList1[s].nonInviteeCount+' </u></h5>';
								 }   
								 
								 str+='</td>';
								 
							 str+='</tr>';
								 }
							}
						 }
					 }
					
				 str+='</tbody></table>';
				
				//if($(window).width() < 300)
				//{
					str+='</div>';
				//}
				 str+='<hr class="m_0">';
			 str+='</div>';  
			  }
			 $("#"+expandTypeId).attr("attr_partyMeetingTypeIdsString",partyMeetingTypeIdsString); 
	$("#"+divId).html(str);  
	$('[data-toggle="tooltip"]').tooltip();
}        

var globalMembersResult = '';
$(document).on("click",".meetingMemberDtlsCls,.desgnationsClick",function(){
	
	var meetingMainTypeId = $(this).attr("attr_main_type_id");
	var meetingTypeIdStr = $(this).attr("attr_meeting_type_arr");
	var meetingTypeIdArr = meetingTypeIdStr.split(",");
	var meetingId = $(this).attr("attr_meeting_id");
	var startDate = $(this).attr("attr_start_date");      
	var endDate = $(this).attr("attr_end_date");
	var state = $(this).attr("attr_state");
	var status = $(this).attr("attr_status");  

	var desgSearchRequired = $(this).attr("attr_search"); 
	var searchDesignation ="";
	if(desgSearchRequired == "required"){
		searchDesignation = $(this).attr("attr_desg_name"); 
	}else{
		globalMembersResult = '';
	}
	
    $("#myModalLabel").html('Meeting Member Details');   
    $("#memberId").html('<div class="col-md-12 col-xs-12 col-sm-12" style="text-align: center;"><img src="images/search.gif" style="width:20px;height:20px;" /></div>');
	$("#positionId").html(''); 
	$("#myModelId").modal('show'); 
	
	if(desgSearchRequired == "notrequired"){
		var jsObj ={ 
				 meetingMainTypeId : meetingMainTypeId,       
				 meetingTypeIdArr : meetingTypeIdArr,
				 meetingId : meetingId,  
				 startDateString : startDate,      
				 endDateString : endDate,
				 state:state,
				 status:status,
				 searchDesignation : searchDesignation
			}
		$.ajax({
			type : 'POST',
			url : 'getParyMeetingMemberDtlsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				globalMembersResult = result;
				buildParyMeetingMemberDtls(result,meetingMainTypeId,meetingTypeIdStr,meetingId,startDate,endDate,state,status,desgSearchRequired,searchDesignation);
			}else{
				$("#memberId").html("<h4> NO DATA AVAILABLE..</h4>");      
			}
		});
	}else{
		buildParyMeetingMemberDtls(globalMembersResult,meetingMainTypeId,meetingTypeIdStr,meetingId,startDate,endDate,state,status,desgSearchRequired,searchDesignation);
    }
	
});
function buildParyMeetingMemberDtls(result,meetingMainTypeId,meetingTypeIdStr,meetingId,startDate,endDate,state,status,desgSearchRequired,searchDesignation){  
	
	//Summary Building
	var str = '';
	if(result[0].publicRepDesgList != null && result[0].publicRepDesgList.length > 0 ){
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
		str+='<div class="panel panel-default">';
		  str+='<div class="panel-heading">';
			str+='<h3 class="panel-title">DESIGNATIONS SUMMARY</h3>';
		  str+='</div>';
		  str+='<div class="panel-body">';
				str+='<p style="font-size:15px;" class="m_top10">';
					if(result[0].publicRepDesgList != null && result[0].publicRepDesgList.length > 0){
						 for(var i in result[0].publicRepDesgList){
								str+='<span style="text-transform:uppercase;">'+result[0].publicRepDesgList[i].name+'</span> ';
								str+='( <span class ="desgnationsClick" attr_search = "required" attr_meeting_id="'+meetingId+'"  attr_state="'+state+'" attr_status="'+status+'" attr_main_type_id="'+meetingMainTypeId+'" attr_meeting_type_arr="'+meetingTypeIdStr+'" attr_start_date="'+startDate+'" attr_end_date="'+endDate+'"  attr_desg_name = "'+result[0].publicRepDesgList[i].name+'" style="font-weight:bold;color:green;cursor:pointer;">'+result[0].publicRepDesgList[i].count+'</span> )';
						        
								if( i!= result[0].publicRepDesgList.length -1 ){
									str+=' , ';
								}
						 }
					}
				str+='</p>';
		  str+='</div>';
		str+='</div>';
		str+='</div>';
	}
	
	str+='<table class="table table-condensed" id="campMemberDtlsId">';
	str+='<thead>';
		str+='<th>NAME</th>';
		str+='<th>DESIGNATION</th>';
		str+='<th>CONTACT NUMBER</th>';
		str+='<th>STATUS</th>';
	str+='</thead>';
	str+='<tbody>'; 
			    
			if(desgSearchRequired == "required"){
				for(var i in result){
					var build = false;
					if(result[i].subList != null && result[i].subList.length > 0){
						for( var j in result[i].subList){
							if(result[i].subList[j].trim().toUpperCase() == searchDesignation.trim().toUpperCase()){
								build = true;
								break;
							}
						}
					}
					if(build){
						//block
					   str+='<tr>';
						str+='<td attr_cadre_id = '+result[i].id+' >'+result[i].name.toUpperCase()+'</td>'; 
						str+='<td>'+result[i].status.toUpperCase()+'</td>'; 
						str+='<td>'+result[i].mobileNo+'</td>';
						if(result[i].wish == "absent"){
							str+='<td style="color:#F0AD4E;">'+result[i].wish+'</td>';   
						}else{
							str+='<td>'+result[i].wish+'</td>'; 
						}
						str+='</tr>'; 
					}
				}
			}else{
				for(var i in result){
				   str+='<tr>';
						str+='<td attr_cadre_id = '+result[i].id+'>'+result[i].name.toUpperCase()+'</td>'; 
						str+='<td>'+result[i].status.toUpperCase()+'</td>'; 
						str+='<td>'+result[i].mobileNo+'</td>';
						if(result[i].wish == "absent"){
							str+='<td style="color:#F0AD4E;">'+result[i].wish+'</td>';   
						}else{
							str+='<td>'+result[i].wish+'</td>'; 
						}
						str+='</tr>'; 
				}
			}
	str+='</tbody>';
	$("#memberId").html(str);        
	//$("#campMemberDtlsId").dataTable();
    $('#campMemberDtlsId').DataTable({
        "lengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
    });	
} 


function getParyMeetingTypeDetailsDistrictWise(mainMeetingTypeId,partyMeetingTypeIdsString){
	$("#districtWisePartyMeetingTypeDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	
	 var partyMeetingTypeArr = partyMeetingTypeIdsString.split(",");
	 
	     var state = globalState
		 /*This ajax call is called by stateLevel And Special Meeting Also.So We are changing date based on meeting type because for special meeting different date is their like */
			 var frmDtStr;
			 var toDtStr;
			if(mainMeetingTypeId == 2){
			  frmDtStr = customStartDateMeetings;
			  toDtStr =	customEndDateMeetings;  	
			}else if(mainMeetingTypeId==3){
			  frmDtStr = globalStartDateForSpecialMeeting;
			  toDtStr =	globalEndDateForSpecialMeeting;  
			}
		var jsObj ={ 
		             partyMeetingMainTypeId : mainMeetingTypeId,
					 state : state,
					 startDateString : frmDtStr,
					 endDateString : toDtStr,
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
	
	var state = globalState;
	 /*This ajax call is called by stateLevel And Special Meeting Also.So We are changing date based on meeting type because for special meeting different date is their like */
	 var frmDtStr;
	 var toDtStr;
	if(mainMeetingTypeId == 2){
	  frmDtStr = customStartDateMeetings;
      toDtStr =	customEndDateMeetings;  	
	}else if(mainMeetingTypeId==3){
	  frmDtStr = globalStartDateForSpecialMeeting;
      toDtStr =	globalEndDateForSpecialMeeting;  
	}
	
	var jsObj ={ 
		partyMeetingMainTypeId : mainMeetingTypeId,
		state : state,
		startDateString :frmDtStr,//fromDateStr
		endDateString : toDtStr,//toDateStr
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
     /*This ajax call is called by stateLevel And Special Meeting Also.So We are changing date based on meeting type because for special meeting different date is their like */
	  var frmDtStr;
	  var toDtStr;
	if(partyMeetingMainTypeId == 2){
	  frmDtStr = customStartDateMeetings;
      toDtStr =	customEndDateMeetings;  	
	}else if(partyMeetingMainTypeId==3){
	  frmDtStr = globalStartDateForSpecialMeeting;
      toDtStr =	globalEndDateForSpecialMeeting;  
	}
	var jsObj ={ 
				 partyMeetingMainTypeId : partyMeetingMainTypeId,
				 state : state,
				 startDateString : frmDtStr,
				 endDateString : toDtStr,
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
		   if( subList[j].invitedCount == 0 && subList[j].invitteeAttendedCount == 0 && subList[j].notAttendedCount==0){
			   str+='<h5 class="text-capital"></h5>'; 
		   }else{
			    str+='<h5 class="text-capital">'+subList[j].name+'</h5>'; 
				str+='<div id="stateLevelMeetingGraph'+i+''+j+'" style="width:100%;height:80px;"></div>';
		   }
       }
     }
     }
     
     if(otherRslt != null ){
		  if(otherRslt.invitedCount == 0 && otherRslt.invitteeAttendedCount == 0 && otherRslt.notAttendedCount==0){
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
	  stateLevelPerArr.push(subList[j].invitedCount);  
      stateLevelPerArr.push(subList[j].invitteeAttendedCount);
      stateLevelPerArr.push(subList[j].notAttendedCount);
       
      if( subList[j].invitedCount == 0 && subList[j].invitteeAttendedCount == 0 && subList[j].notAttendedCount==0){
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
				categories: ['invited','Invitees Attended','Absent'],
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
				valueSuffix: ''
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
						return Highcharts.numberFormat(this.y,0);
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
	   stateLevelOtherPerArr.push(otherRslt.invitedCount);  
	  stateLevelOtherPerArr.push(otherRslt.invitteeAttendedCount);
      stateLevelOtherPerArr.push(otherRslt.notAttendedCount);
	  
	  if(otherRslt.invitedCount == 0 && otherRslt.invitteeAttendedCount == 0 && otherRslt.notAttendedCount==0){
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
				categories: ['invited','Invitees Attended','Absent'],
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
				valueSuffix: ''
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
						return Highcharts.numberFormat(this.y,0);
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
	
	return;
 	$.ajax({
		type : 'POST',
		url : 'getMeetingRecentTimeAction.action',
		dataType : 'json',
		data : {task:JSON.stringify( )}
	}).done(function(result){
		if(result != null){
		 setMeeingLastUpdatedTime(result)	
		}
	});
}
function setMeeingLastUpdatedTime(lastUpdatedTime){
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
	       var jsObj ={ 
		             activityMemberId : globalActivityMemberId,
					 stateId : globalStateId,
					 fromDate : customStartDateMeetings,
					 toDate : customEndDateMeetings,
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
	 
           var jsObj ={ 
		             activityMemberId : globalActivityMemberId,
					 stateId : globalStateId,
					 fromDate : customStartDateMeetings,
					 toDate : customEndDateMeetings,
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
	
           var jsObj ={ 
		             activityMemberId : globalActivityMemberId,
					 stateId : globalStateId,
					 fromDate : customStartDateMeetings,
					 toDate : customEndDateMeetings,
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
		
	
    	var reportType;
		if(reportTypeId == 0){
		reportType = "District"; 
		 }else if(reportTypeId == 1){
			reportType = "Constituency";  
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
		 var isCheckedConslidated=$("#ConsolidatedradioId").is(':checked');
	     var isCheckedIndividual=$("#individualradioId").is(':checked');
        if(isCheckedIndividual){
		 getMeetingComments(meetingStatus,meetingLevel,isComment,customStartDateMeetings,customEndDateMeetings,locationId,locationType,"meetingCommentDtlsTblId","directRslt");	
		} 
	   if(isCheckedConslidated){
		 getPartyMeetingComulativeCommentDetails(meetingStatus,meetingLevel,isComment,customStartDateMeetings,customEndDateMeetings,reportType,locationId,locationType);  	
		} 	
	//	}
 });
 $(document).on("click","#ConsolidatedradioId",function(){
        var meetingStatus = $(this).attr("attr_meeting_status");
	    var meetingLevel = $(this).attr("attr_level_type");
		var isComment = $(this).attr("attr_comment");
	
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
	if($(this).is(':checked')){
		 getPartyMeetingComulativeCommentDetails(meetingStatus,meetingLevel,isComment,customStartDateMeetings,customEndDateMeetings,reportType,0," ");  
	 } 
	 
 });
 $(document).on("click","#individualradioId",function(){
	    var meetingStatus = $(this).attr("attr_meeting_status");
	    var meetingLevel = $(this).attr("attr_level_type");
		var isComment = $(this).attr("attr_comment");
		
		$("#getDetailsBtnId").attr("attr_comment","No");
	   $("#districtSlctBxId").attr("attr_comment","No");		 
       $("#constituencySlctBxId").attr("attr_comment","No");	
	   getDistrictByState(meetingStatus,meetingLevel,"No");	
      getConstituencyByDistrict(meetingStatus,meetingLevel,"No",0);
      getMandalByConstituency(meetingStatus,meetingLevel,"No",0);	
	 if($(this).is(':checked')){
		 getMeetingComments(meetingStatus,meetingLevel,isComment,customStartDateMeetings,customEndDateMeetings,0," ","meetingCommentDtlsTblId","directRslt"); 
	 } 
	 
 });
 $(document).on("click",".commentDetailsCls",function(){
	    var meetingStatus = $(this).attr("attr_meeting_status");
	    var meetingLevel = $(this).attr("attr_level_type");
		var isComment = $(this).attr("attr_comment");
		
		var locationId;
		var locationType;
		locationId=$(this).attr("attr_location_id");
		locationType=$(this).attr("attr_location_type");
		
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
	   getMeetingComments(meetingStatus,meetingLevel,isComment,customStartDateMeetings,customEndDateMeetings,locationId,locationType,"meetingDetailsTblId","subLevel");
 });
 function overAllMeetings(divId)
 {
	        var divId = $("#"+divId)
			var meetingStatus = divId.attr("attr_meeting_status");
			var meetingLevel = divId.attr("attr_level_type");
			var isComment = divId.attr("attr_comment");
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
			
			$("#ConsolidatedradioId").attr("attr_meeting_status",meetingStatus);
			$("#ConsolidatedradioId").attr("attr_level_type",meetingLevel);
			$("#ConsolidatedradioId").attr("attr_comment","No");
			
			$("#individualradioId").attr("attr_meeting_status",meetingStatus);
			$("#individualradioId").attr("attr_level_type",meetingLevel);
			$("#individualradioId").attr("attr_comment","No");
			
			$("#constituencySlctBxId").attr("attr_meeting_status",meetingStatus);
			$("#constituencySlctBxId").attr("attr_level_type",meetingLevel);
			
			$("#districtSlctBxId").attr("attr_meeting_status",meetingStatus);
			$("#districtSlctBxId").attr("attr_level_type",meetingLevel);
			
		   $("#getDetailsBtnId").attr("attr_comment","No");
		   $("#districtSlctBxId").attr("attr_comment","No");		 
		   $("#constituencySlctBxId").attr("attr_comment","No");	
		 
		   getDistrictByState(meetingStatus,meetingLevel,"No");	
		   getConstituencyByDistrict(meetingStatus,meetingLevel,"No",0);
		   getMandalByConstituency(meetingStatus,meetingLevel,"No",0);	
	
		
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
	 
		  if((meetingLevel =="Village/Ward" || meetingLevel =="Mandal/Town/Division") && diffDays <=31){
			 $(".mandalSlctBxCls").show();
			 $(".constituencySlctBxCls").show();
			 getMeetingComments(meetingStatus,meetingLevel,"No",fromDateStr,toDateStr,0," ","meetingCommentDtlsTblId","directRslt"); 
			 $("#ConsolidatedradioId").prop('checked',false);
			 $("#individualradioId").prop('checked', true);
		   }else if((meetingLevel =="Village/Ward" || meetingLevel =="Mandal/Town/Division") && diffDays>=31){
			  $(".mandalSlctBxCls").show();
			  $(".constituencySlctBxCls").show();
			   $("#individualradioId").prop('checked', false);						  
			  $("#ConsolidatedradioId").prop('checked', true);
			  getPartyMeetingComulativeCommentDetails(meetingStatus,meetingLevel,"No",fromDateStr,toDateStr,"District",0," "); 
		 }else if(meetingLevel=="District" && diffDays <=31) {
			  $(".mandalSlctBxCls").hide();
			  $(".constituencySlctBxCls").hide();
			  $("#commentFilterSelectBoxId").children('option:not(:first)').remove();
			  getMeetingComments(meetingStatus,meetingLevel,"No",fromDateStr,toDateStr,0," ","meetingCommentDtlsTblId","directRslt"); 	 
			   $("#ConsolidatedradioId").prop('checked', false);
			   $("#individualradioId").prop('checked', true);				   
		 }else if(meetingLevel == "District" && diffDays >=31){
			  getPartyMeetingComulativeCommentDetails(meetingStatus,meetingLevel,"No",fromDateStr,toDateStr,"District",0," "); 
			  $("#individualradioId").prop('checked', false);						 
			  $("#ConsolidatedradioId").prop('checked', true);
			  $("#commentFilterSelectBoxId").children('option:not(:first)').remove();
			  $(".mandalSlctBxCls").hide();
			  $(".constituencySlctBxCls").hide();
		 }else if(meetingLevel=="Constituency" && diffDays <= 31 ) {
			  $(".mandalSlctBxCls").hide();
			  $(".constituencySlctBxCls").show();
			  getMeetingComments(meetingStatus,meetingLevel,"No",fromDateStr,toDateStr,0," ","meetingCommentDtlsTblId","directRslt"); 
			   $("#ConsolidatedradioId").prop('checked', false);	  
			  $("#individualradioId").prop('checked', true);				 
		 }else if(meetingLevel=="Constituency" && diffDays >=31){
			   $("#individualradioId").prop('checked', false);			
			  $("#ConsolidatedradioId").prop('checked', true);
			  $(".mandalSlctBxCls").hide();
			  $(".constituencySlctBxCls").show();
			  $("#commentFilterSelectBoxId").val(0);
			  getPartyMeetingComulativeCommentDetails(meetingStatus,meetingLevel,"No",fromDateStr,toDateStr,"District",0," ");
		}else if(meetingLevel=="State" && diffDays <= 31){
			  getMeetingComments(meetingStatus,meetingLevel,"No",fromDateStr,toDateStr,0," ","meetingCommentDtlsTblId","directRslt");
			  $(".mandalSlctBxCls").hide();
			  $(".constituencySlctBxCls").hide();	
			  $("#commentFilterSelectBoxId").children('option:not(:first)').remove();
			  $("#ConsolidatedradioId").prop('checked', false);	   
			  $("#individualradioId").prop('checked', true);			
		 }else if(meetingLevel=="State" && diffDays >= 31){
			  $("#individualradioId").prop('checked', false);			
			  $("#ConsolidatedradioId").prop('checked', true);
			  $("#commentFilterSelectBoxId").children('option:not(:first)').remove();
			  getPartyMeetingComulativeCommentDetails(meetingStatus,meetingLevel,"No",fromDateStr,toDateStr,"District",0," ");
			  $(".mandalSlctBxCls").hide();	
			  $(".constituencySlctBxCls").hide();		   
		 } 
	 
 }
 
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
			
			$("#ConsolidatedradioId").attr("attr_meeting_status",meetingStatus);
			$("#ConsolidatedradioId").attr("attr_level_type",meetingLevel);
			$("#ConsolidatedradioId").attr("attr_comment","No");
			
			$("#individualradioId").attr("attr_meeting_status",meetingStatus);
			$("#individualradioId").attr("attr_level_type",meetingLevel);
			$("#individualradioId").attr("attr_comment","No");
			
			$("#constituencySlctBxId").attr("attr_meeting_status",meetingStatus);
			$("#constituencySlctBxId").attr("attr_level_type",meetingLevel);
			
			$("#districtSlctBxId").attr("attr_meeting_status",meetingStatus);
			$("#districtSlctBxId").attr("attr_level_type",meetingLevel);
			
		   $("#getDetailsBtnId").attr("attr_comment","No");
		   $("#districtSlctBxId").attr("attr_comment","No");		 
		   $("#constituencySlctBxId").attr("attr_comment","No");	
		 
		   getDistrictByState(meetingStatus,meetingLevel,"No");	
		   getConstituencyByDistrict(meetingStatus,meetingLevel,"No",0);
		   getMandalByConstituency(meetingStatus,meetingLevel,"No",0);	
	
		
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
	 
		  if((meetingLevel =="Village/Ward" || meetingLevel =="Mandal/Town/Division") && diffDays <=31){
			 $(".mandalSlctBxCls").show();
			 $(".constituencySlctBxCls").show();
			 getMeetingComments(meetingStatus,meetingLevel,"No",fromDateStr,toDateStr,0," ","meetingCommentDtlsTblId","directRslt"); 
			 $("#ConsolidatedradioId").prop('checked',false);
			 $("#individualradioId").prop('checked', true);
		   }else if((meetingLevel =="Village/Ward" || meetingLevel =="Mandal/Town/Division") && diffDays>=31){
			  $(".mandalSlctBxCls").show();
			  $(".constituencySlctBxCls").show();
			   $("#individualradioId").prop('checked', false);						  
			  $("#ConsolidatedradioId").prop('checked', true);
			  getPartyMeetingComulativeCommentDetails(meetingStatus,meetingLevel,"No",fromDateStr,toDateStr,"District",0," "); 
		 }else if(meetingLevel=="District" && diffDays <=31) {
			  $(".mandalSlctBxCls").hide();
			  $(".constituencySlctBxCls").hide();
			  $("#commentFilterSelectBoxId").children('option:not(:first)').remove();
			  getMeetingComments(meetingStatus,meetingLevel,"No",fromDateStr,toDateStr,0," ","meetingCommentDtlsTblId","directRslt"); 	 
			   $("#ConsolidatedradioId").prop('checked', false);
			   $("#individualradioId").prop('checked', true);				   
		 }else if(meetingLevel == "District" && diffDays >=31){
			  getPartyMeetingComulativeCommentDetails(meetingStatus,meetingLevel,"No",fromDateStr,toDateStr,"District",0," "); 
			  $("#individualradioId").prop('checked', false);						 
			  $("#ConsolidatedradioId").prop('checked', true);
			  $("#commentFilterSelectBoxId").children('option:not(:first)').remove();
			  $(".mandalSlctBxCls").hide();
			  $(".constituencySlctBxCls").hide();
		 }else if(meetingLevel=="Constituency" && diffDays <= 31 ) {
			  $(".mandalSlctBxCls").hide();
			  $(".constituencySlctBxCls").show();
			  getMeetingComments(meetingStatus,meetingLevel,"No",fromDateStr,toDateStr,0," ","meetingCommentDtlsTblId","directRslt"); 
			   $("#ConsolidatedradioId").prop('checked', false);	  
			  $("#individualradioId").prop('checked', true);				 
		 }else if(meetingLevel=="Constituency" && diffDays >=31){
			   $("#individualradioId").prop('checked', false);			
			  $("#ConsolidatedradioId").prop('checked', true);
			  $(".mandalSlctBxCls").hide();
			  $(".constituencySlctBxCls").show();
			  $("#commentFilterSelectBoxId").val(0);
			  getPartyMeetingComulativeCommentDetails(meetingStatus,meetingLevel,"No",fromDateStr,toDateStr,"District",0," ");
		}else if(meetingLevel=="State" && diffDays <= 31){
			  getMeetingComments(meetingStatus,meetingLevel,"No",fromDateStr,toDateStr,0," ","meetingCommentDtlsTblId","directRslt");
			  $(".mandalSlctBxCls").hide();
			  $(".constituencySlctBxCls").hide();	
			  $("#commentFilterSelectBoxId").children('option:not(:first)').remove();
			  $("#ConsolidatedradioId").prop('checked', false);	   
			  $("#individualradioId").prop('checked', true);			
		 }else if(meetingLevel=="State" && diffDays >= 31){
			  $("#individualradioId").prop('checked', false);			
			  $("#ConsolidatedradioId").prop('checked', true);
			  $("#commentFilterSelectBoxId").children('option:not(:first)').remove();
			  getPartyMeetingComulativeCommentDetails(meetingStatus,meetingLevel,"No",fromDateStr,toDateStr,"District",0," ");
			  $(".mandalSlctBxCls").hide();	
			  $(".constituencySlctBxCls").hide();		   
		 }  
   });
   $(document).on("change","#commentFilterSelectBoxId",function(){
	    $("#individualradioId").prop('checked',false);
	    $("#ConsolidatedradioId").prop('checked',true);
	    var meetingStatus = $(this).attr("attr_meeting_status");
	    var meetingLevel = $(this).attr("attr_level_type");
		var isComment = $(this).attr("attr_comment");
		var reportTypeId = $(this).val();
		
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
		getPartyMeetingComulativeCommentDetails(meetingStatus,meetingLevel,isComment,customStartDateMeetings,customEndDateMeetings,reportType,0," ")
   });
   function convetFormat(date){
	   var dateArr=date.split("/");
	   return dateArr[1]+"/"+dateArr[0]+"/"+dateArr[2];
   }
   function getPartyMeetingComulativeCommentDetails(meetingStatus,meetingLevel,isComment,customStartDateMeetings,customEndDateMeetings,reportType,locationId,locationType){
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
					 fromDate : customStartDateMeetings,
					 toDate : customEndDateMeetings,
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
					 fromDate : customStartDateMeetings,
					 toDate : customEndDateMeetings,
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
   
    function buildStatelevelSpecialMeetingsPerformanceReport(){
	   
	   var str='';
	   str+='<div class="col-md-12 col-xs-12 col-sm-12">';
		str+='<div class="row">';
			str+='<div class="col-md-6 col-xs-12 col-sm-8" style="margin-top: 6px;">';
				str+='<h4>State Co-Ordination Meetings with CBN - DEC - 16</h4>';
			str+='</div>';
			str+='<div class="col-md-4 col-xs-12 col-sm-4">';
				str+='<ul class="activeUlCls  list-inline pull-right">';
					str+='<li class="active " id="" attr_over_all_cnt="0"  attr_filter_value="All">All Sessions</li>';
					str+='<li class="">Session-|</li>';
					str+='<li class="">Session-||</li>';
				str+='</ul>';
			str+='</div>';
		str+='</div>';
		//9999
		str+='<div class="row m_top10">';
			str+='<div class="col-md-4 col-xs-12 col-sm-6">';
			str+='<div class="panel panel-default">';
			 str+=' <div class="panel-heading">All Sessions</div>';
			  str+='<div class="panel-body">';
				str+='<table class="table border_top_noneCoh tablenthChild">';
					str+='<tbody>';
						str+='<tr>';
							str+='<td class="text-success">Total&nbsp;Attended<br>2500</td>';
								str+='<td class="text_oragane">Late&nbsp;Attended<br>2500</td>';
									str+='<td>Absent<br>2500</td>';
						str+='</tr>';
					str+='</tbody>';
				str+='</table>';
			  str+='</div>';
			str+='</div>';
		str+='</div>';
		
		str+='<div class="col-md-4 col-xs-12 col-sm-6">';
			str+='<div class="panel panel-default">';
			 str+=' <div class="panel-heading" style="color:#35009B;">Session-|</div>';
			  str+='<div class="panel-body">';
				str+='<table class="table border_top_noneCoh tablenthChild">';
					str+='<tbody>';
						str+='<tr>';
							str+='<td class="text-success">Total&nbsp;Attended<br>2500</td>';
								str+='<td class="text_oragane">Late&nbsp;Attended<br>2500</td>';
									str+='<td>Absent<br>2500</td>';
						str+='</tr>';
					str+='</tbody>';
				str+='</table>';
			  str+='</div>';
			str+='</div>';
		str+='</div>';
		str+='<div class="col-md-4 col-xs-12 col-sm-6">';
			str+='<div class="panel panel-default">';
			 str+=' <div class="panel-heading" style="color:#630134;">Session-||</div>';
			  str+='<div class="panel-body">';
				str+='<table class="table border_top_noneCoh tablenthChild">';
					str+='<tbody>';
						str+='<tr>';
							str+='<td class="text-success">Total&nbsp;Attended<br>2500</td>';
								str+='<td class="text_oragane">Late&nbsp;Attended<br>2500</td>';
									str+='<td>Absent<br>2500</td>';
						str+='</tr>';
					str+='</tbody>';
				str+='</table>';
			  str+='</div>';
			str+='</div>';
		str+='</div>';
		str+='</div>';
		
		str+='<div class="row m_top10">';
			str+='<table class="table border_top_apply">';
				str+='<thead>';
					str+='<tr>';
						str+='<th>District Name</th>';
						str+='<th>Leader Name</th>';
						str+='<th>Designation</th>';
						str+='<th>Contact Number</th>';
						str+='<th>All Sessions</th>';
						str+='<th>Sessions-|</th>';
						str+='<th>Sessions-||</th>';
					str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
					str+='<tr>';
						str+='<td>Srikakulam</td>';
						str+='<td>Srikakulam</td>';
						str+='<td>MLA</td>';
						str+='<td>98856322852</td>';
						str+='<td>01</td>';
						str+='<td>Y</td>';
						str+='<td>N</td>';
					str+='</tr>';
					str+='<tr>';
						str+='<td>Srikakulam</td>';
						str+='<td>Srikakulam</td>';
						str+='<td>MLA</td>';
						str+='<td>98856322852</td>';
						str+='<td>01</td>';
						str+='<td>Y</td>';
						str+='<td>N</td>';
					str+='</tr>';
					str+='<tr>';
						str+='<td>Srikakulam</td>';
						str+='<td>Srikakulam</td>';
						str+='<td>MLA</td>';
						str+='<td>98856322852</td>';
						str+='<td>01</td>';
						str+='<td>Y</td>';
						str+='<td>N</td>';
					str+='</tr>';
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
	   str+='</div>';
	   
	   $("#specialMeetingsPerformanceOverViewDiv").html(str);
   }
    /* Meeting Comment Block end */
  function getCommitteesAndPublicRepresentativeMembersInvitedAndAttendedToSeeionWiseMeetingDtls(partyMeetingMainTypeId,partyMeetingTypeIdsString,partyMeetingId){
	$("#userTypeWiseTopFiveStrongAndPoorMeetingMemsDivId").html(' ');
	$("#stateLevelMeetingBlockId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	var partyMeetingTypeArr = partyMeetingTypeIdsString.split(",");
	var state = globalState
	
	var jsObj ={ 
				 partyMeetingMainTypeId : partyMeetingMainTypeId,
				 state : state,
				 startDateString : globalStartDateForSpecialMeeting,
				 endDateString : globalEndDateForSpecialMeeting,
				 partyMeetingTypeIds:partyMeetingTypeArr,
				 partyMeetingIds :partyMeetingId
			  }
	$.ajax({
		type : 'POST',
		url : 'getCommitteesAndPublicRepresentativeMembersInvitedAndAttendedToSeeionWiseMeetingDtlsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null ){
		  buildCommitteesAndPublicRepresentativeMembersInvitedAndDtls(result)	
		}else{
          $("#stateLevelMeetingBlockId").html("NO DATA AVAILABLE.");
		} 
	});
}
function buildCommitteesAndPublicRepresentativeMembersInvitedAndDtls(result){
	var str='';
	
	str+='<div class="col-md-12 col-xs-12 col-sm-12 ">'; 
	str+='<div class="row">';
		str+='<ul class="list-inline pull-right">'; 
			str+='<li class=""><span class="allSessionColor"></span> All Session</li>';
			
				if(result[0].subList1 != null && result[0].subList1.length > 0){
					for(var j in result[0].subList1){
						var color = getColorCodeBySessionStatus(result[0].subList1[j].name);
						str+='<li class="" ><span class="sessionColor" style="background-color:'+color+'"></span> '+result[0].subList1[j].name+'</li>'; 
						
						
					}
				}
		str+='</ul>'; 
	str+='</div>'; 
	str+='</div>'; 
	
	for(var i in result){
		if(result[i].invitedCount == 0)
			continue;
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
		str+='<h5 class="text-capital">'+result[i].name+'</h5>'; 
		
			str+='<div class="scroll-div">';
			str+='<ul class="list-inline best-matched-profile " style="padding:0px !important;">';
				str+='<li style="padding:0px !important;border-right:none !important"><div id="stateLevelMeetingBlockIdGr'+i+'" class="chartLiMee1"></div></li>';
				str+='<li style="padding:0px !important;border-right:none !important"><div id="stateLevelMeetingBlockIdGr1'+i+'" class="chartLiMee" ></div></li>';
				str+='<li style="padding:0px !important;border-right:none !important"><div id="stateLevelMeetingBlockIdGr2'+i+'" class="chartLiMee" ></div></li>';
				str+='<li style="padding:0px !important;border-right:none !important"><div id="stateLevelMeetingBlockIdGr3'+i+'" class="chartLiMee" ></div></li>';
				str+='<li style="padding:0px !important;border-right:none !important"><div id="stateLevelMeetingBlockIdGr4'+i+'" class="chartLiMee" ></div></li>';
			str+='</ul>';
			str+='</div>';
     str+='</div>'	
	}
	
  $("#stateLevelMeetingBlockId").html(str);
  for(var i in result){
	if(result[i].invitedCount == 0)
		continue
	 var inviteeArr=[];  
	 var inviteeNameArray=[];
	
	 inviteeArr.push({"y":result[i].invitedCount,"id":result[i].id,"partyMeetingId":result[i].partyMeetingId});
	
	 inviteeNameArray.push("Invitees");  
	  
	  var sessionList = result[i].subList1;  
	   var attendedArr=[];
	   var lateAttendedArr=[];
	   var absentArr=[];
	   var nonInviteesAttArr=[];
	   
	if(sessionList != null && sessionList.length > 0){
		for(var j in sessionList){
			var color = getColorCodeBySessionStatus(sessionList[j].name);
			var color1 = getColorCodeBySessionStatus('All Sessions');
				if(j == 0){      
					attendedArr.push({"name":'All Sessions',"data":[sessionList[j].allSessionAttendedCnt],"color":color1,"id":result[i].id,"status":'inviteAttended',"sessionId":0,"partyMeetingId":result[i].partyMeetingId});  	  
					lateAttendedArr.push({name:'All Sessions',data:[sessionList[j].allSessionLateAttendedCnt],color:color1,"id":result[i].id,"status":'inviteLate',"sessionId":0,"partyMeetingId":result[i].partyMeetingId});           	  
					absentArr.push({name:'All Sessions',data:[sessionList[j].allSessionAbsentCnt],color:color1,"id":result[i].id,"status":'inviteAbsent',"sessionId":0,"partyMeetingId":result[i].partyMeetingId});	
					nonInviteesAttArr.push({name:'All Sessions',data:[parseInt(sessionList[j].allSessionNonInviteeAttendedCnt)-parseInt(sessionList[j].allSessionAttendedCnt)],color:color1,"id":result[i].id,"status":'nonInviteAttended',"sessionId":0,"partyMeetingId":result[i].partyMeetingId});				
				}  
				attendedArr.push({"name":''+sessionList[j].name+'',"data":[sessionList[j].invitteeAttendedCount],"color":color,"id":result[i].id,"status":'inviteAttended',"sessionId":sessionList[j].id,"partyMeetingId":result[i].partyMeetingId});	  
			    lateAttendedArr.push({name:''+sessionList[j].name+'',data:[sessionList[j].lateAttendedCnt],color:color,"id":result[i].id,"status":'inviteLate',"sessionId":sessionList[j].id,"partyMeetingId":result[i].partyMeetingId});	  
			    absentArr.push({name:''+sessionList[j].name+'',data:[sessionList[j].notAttendedCount],color:color,"id":result[i].id,"status":'inviteAbsent',"sessionId":sessionList[j].id,"partyMeetingId":result[i].partyMeetingId});
				nonInviteesAttArr.push({name:''+sessionList[j].name+'',data:[parseInt(sessionList[j].attendedCount)-parseInt(sessionList[j].invitteeAttendedCount)],color:color,"id":result[i].id,"status":'nonInviteAttended',"sessionId":sessionList[j].id,"partyMeetingId":result[i].partyMeetingId});	
		}   
	}
	  //if(inviteeArr != 0 && inviteeArr.length > 0){ sssssss
		 
		 $(function () {
			var chart;
			$('#stateLevelMeetingBlockIdGr'+i).highcharts({
				colors: ['#0066DC'],
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
					
					categories: inviteeNameArray,
					labels: {
								formatter: function() {
									return this.value.toString().substring(0, 2)+'..';
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
					stackLabels: {
						enabled: false,
						style: {
							fontWeight: 'bold',
							color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
						}
					}
				},
				legend: {
					enabled: false,
					align: 'right',
					x: -30,
					verticalAlign: 'top',
					y: 25,
					floating: true,
					backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
					borderColor: '#CCC',
					borderWidth: 1,
					shadow: false
				},
				tooltip: {
					headerFormat: '<b>All</b><br/>',
					pointFormat: '{series.name}<br/>({point.y})',
						
				},
				plotOptions: {
					useHTML: true,
					column: {
						dataLabels: {
							align: 'top',
							enabled: true,
							//rotation: 270,
							x: -8,
							//y: -10,
							formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return '<span style="text-align:center">'+(this.y)+'</span>';
								}
							},
						},
					},
					series: {
							cursor: 'pointer',
							point: {
							events: {
							click: function () {
								var id = this.id;
								var group = this.group;
								var partyMeetingId = this.partyMeetingId;
								getPublicRepAndcommitteeInviteeDtls(id,partyMeetingId);        
								}                     
							}
						}
					}
				},
				series: [{
					name: 'Invitees',
					data: inviteeArr
				}]
			});
		});  
	  //}
	   if(attendedArr != 0 && attendedArr.length > 0){
		 
		$(function () {
			$('#stateLevelMeetingBlockIdGr1'+i).highcharts({
				//colors: ['#0066DC','#320199','#660032'],
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
					categories: ['Invitees Attended']
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
					enabled: false,
					align: 'right',
					x: -30,
					verticalAlign: 'top',
					y: 25,
					floating: true,
					backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
					borderColor: '#CCC',
					borderWidth: 1,
					shadow: false
				},
				tooltip: {
					headerFormat: '<b>{point.x}</b><br/>',
					pointFormat: '{series.name}<br/>({point.y})',
						
				},
				plotOptions: {
					useHTML: true,
					column: {
						dataLabels: {
							enabled: true,
							formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return '<span style="text-align:center">'+(this.y)+'</span>';
								}
							}
						}
					},
					series: {
							cursor: 'pointer',
							point: {
							events: {
							click: function () {  								
								var id = this.series.userOptions.id;
								var status = this.series.userOptions.status;
								var name = this.series.userOptions.name;
								var sessionId = this.series.userOptions.sessionId;
								var partyMeetingId = this.series.userOptions.partyMeetingId;
								getPublicRepAndcommitteeAttendedDtls(id,status,name,sessionId,partyMeetingId);
								}         
							}
						}
					}
				},
				series: attendedArr
			});
		});   
	   }
		
		if(lateAttendedArr != null && lateAttendedArr.length > 0){
			
		$(function () {
			$('#stateLevelMeetingBlockIdGr2'+i).highcharts({
				//colors: ['#0066DC','#320199','#660032'],
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
					categories: ['Invitees Late']
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
					enabled: false,
					align: 'right',
					x: -30,
					verticalAlign: 'top',
					y: 25,
					floating: true,
					backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
					borderColor: '#CCC',
					borderWidth: 1,
					shadow: false
				},
				tooltip: {
					headerFormat: '<b>{point.x}</b><br/>',
					pointFormat: '{series.name}<br/>({point.y})',
						
				},
				plotOptions: {
					useHTML: true,
					column: {
						dataLabels: {
							enabled: true,
							formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return '<span style="text-align:center">'+(this.y)+'</span>';
								}
							}
						}
					},
					series: {
							cursor: 'pointer',
							point: {
							events: {
							click: function () {  								
								var id = this.series.userOptions.id;
								var status = this.series.userOptions.status;
								var name = this.series.userOptions.name;
								var sessionId = this.series.userOptions.sessionId;
								var partyMeetingId = this.series.userOptions.partyMeetingId;
								getPublicRepAndcommitteeLateAttendedDtls(id,status,name,sessionId,partyMeetingId);          
								}         
							}
						}
					}  
				},
				series:lateAttendedArr 
			});
		});	
		}
		if(absentArr != null && absentArr.length > 0){
		
		$(function () {
			$('#stateLevelMeetingBlockIdGr3'+i).highcharts({
				//colors: ['#0066DC','#320199','#660032'],
				//useHTML: true,
				background :'transparent',
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
					categories: ['Invitees Absent']
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
					enabled: false,
					align: 'right',
					x: -30,
					verticalAlign: 'top',
					y: 25,
					floating: true,
					backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
					borderColor: '#CCC',
					borderWidth: 1,
					shadow: false
				},
				tooltip: {
					headerFormat: '<b>{point.x}</b><br/>',
					pointFormat: '{series.name}({point.y})',
						
				},
				plotOptions: {
					useHTML: true,
					column: {
						dataLabels: {
							enabled: true,
							formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return '<span style="text-align:center">'+(this.y)+'</span>';
								}
							}
						}
					},
					series: {
							cursor: 'pointer',
							point: {
							events: {
							click: function () {  								
								var id = this.series.userOptions.id;
								var status = this.series.userOptions.status;
								var name = this.series.userOptions.name;
								var sessionId = this.series.userOptions.sessionId;
								var partyMeetingId = this.series.userOptions.partyMeetingId;
								getPublicRepAndcommitteeInviteeAbsentDtls(id,status,name,sessionId,partyMeetingId);            
								}         
							}
						}
					}
				},
				series: absentArr
			});
		});	
		}else{
			$('#stateLevelMeetingBlockIdGr3'+i).html("No Data Available")
		}

	$(function () {
			$('#stateLevelMeetingBlockIdGr4'+i).highcharts({
				//colors: ['#0066DC','#320199','#660032'],
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
					categories: ['Non Invitees Attended']
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
					enabled: false,
					align: 'right',
					x: -30,
					verticalAlign: 'top',
					y: 25,
					floating: true,
					backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
					borderColor: '#CCC',
					borderWidth: 1,
					shadow: false
				},
				tooltip: {
					headerFormat: '<b>{point.x}</b><br/>',
					pointFormat: '{series.name}<br/>({point.y})',
						
				},
				plotOptions: {
					useHTML: true,
					column: {
						dataLabels: {
							enabled: true,
							formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return '<span style="text-align:center">'+(this.y)+'</span>';
								}
							}
						}
					},
					series: {
							cursor: 'pointer',
							point: {
							events: {
							click: function () {  								
								var id = this.series.userOptions.id;
								var status = this.series.userOptions.status;
								var name = this.series.userOptions.name;
								var sessionId = this.series.userOptions.sessionId;
								var partyMeetingId = this.series.userOptions.partyMeetingId;
								getPublicRepAndcommitteeNonInviteeAttendedDtls(id,status,name,sessionId,partyMeetingId);                  
								}         
							}
						}
					}
				},
				series: nonInviteesAttArr     
			});
		});
  }
}
	function getParyMeetingDetailsDistrictWise(sessionId,partyMeetingId,position){
		$(".specialMeetingCls").show();
		$("#partyMeetingHeadingId").html('');
		$("#districtWiseSpecialMeetingsGraph").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var partyMeetingTypeArr=[];  
		$("#specialMeetingUlId li").each(function() {       
		  if($(this).find("input").is(":checked")){
			  partyMeetingTypeArr.push($(this).find("input").attr("id"));
		  }
		});
		var state = globalState;  
		var jsObj ={ 
					   partyMeetingMainTypeId : 3,  
					   state : state,
					   startDateString : globalStartDateForSpecialMeeting,
					   endDateString : globalEndDateForSpecialMeeting,              
					   partyMeetingTypeIds:partyMeetingTypeArr,           
					   partyMeetingId : partyMeetingId,   
					   sessionId : sessionId
			  };
			  
		$.ajax({
		  type : 'POST',
		  url : 'getParyMeetingDetailsDistrictWiseAction.action',
		  dataType : 'json',
		  data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				buildParyMeetingDetailsDistrictWise(result,partyMeetingId,position);
			}else{
				$("#districtWiseGraphSpecialMee").html("No Data Available");
			}
		});                
	}
  
  function buildParyMeetingDetailsDistrictWise(result,partyMeetingId,position){
		$("#districtWiseSpecialMeetingsGraph").html('');
		if(result != null && result.length > 0){
			var meetingName = result[0].name;
			$("#partyMeetingHeadingId").html(meetingName);
			var str='';
				str+='<div id="districtWiseGraphSpecialMee" class="chartLiD" style="height:300px" ></div>';
		}
		$("#districtWiseSpecialMeetingsGraph").html(str);
		
		
		
	if(result != null && result.length > 0){
		var locationNameArray =[];
			var invitieCountArray = [];
			var attendedCountArray = [];
			var inviteAbsentCountArray = [];
			
		for(var i in result){
			
			
			locationNameArray.push(result[i].locationName);
			//invitieCountArray.push(result[i].invitieCount);
			var sessionId = 0;
			if(result[i].sessionId != null && parseInt(result[i].sessionId)>0){
				sessionId = parseInt(result[i].sessionId);
			}
			attendedCountArray.push({"dataBuildType":"present","y":parseInt(result[i].attendedCount),"id":parseInt(result[i].locationId),"meetingId":parseInt(result[i].meetingId),"sessionId":parseInt(sessionId)});
			inviteAbsentCountArray.push({"dataBuildType":"absent","y":parseInt(result[i].inviteAbsentCount),"id":parseInt(result[i].locationId),"meetingId":parseInt(result[i].meetingId),"sessionId":parseInt(sessionId)});
			//inviteAbsentCountArray.push(result[i].inviteAbsentCount);
		}	     				
		
		
						$(function () {
							$('#districtWiseGraphSpecialMee').highcharts({
								colors: ['#3C763D','#A94442'],
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
										categories: locationNameArray,
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
									pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y} - {point.percentage:.1f}%</b><br/>',
									shared: true
								},
								plotOptions: {
									useHTML: true,
									//text-align:'center',
									column: {
										stacking: 'percent',
										dataLabels: {
											enabled: true,
											 formatter: function() {
												if (this.y === 0) {
													return null;
												} else {
													return '<span style="text-align:center">'+(this.y)+'</span>';
												}
											}
										  
										}
									},
									series: {
										cursor: 'pointer',
										point: {
										events: {
										click: function () {
											var locationId = this.id;
											var meetingId = this.meetingId;
											var sessionId = this.sessionId;
											var dataBuildType = this.dataBuildType;
											getDistDtls(locationId,meetingId,sessionId,dataBuildType);
											}  
										}
									}
								}
								},
								series: [{
									name: 'Attended',
									data: attendedCountArray
								}, {
									name: 'Absent',
									data: inviteAbsentCountArray
								}]
							});
						});
				
		
	}else{
		$("#districtWiseGraphSpecialMee").html("No Data Available");
	}
	if(position != "refreshBtn")
		getSessionButton(partyMeetingId);  
}
	function getSessionButton(partyMeetingId){
		var jsObj ={
					 partyMeetingId : partyMeetingId
				};
			  
		$.ajax({  
		  type : 'POST',
		  url : 'getPartyMeetingSessionAction.action',
		  dataType : 'json',
		  data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				buildSessionButton(result,partyMeetingId);
			}else{
				      
			}
		});
	}
	function buildSessionButton(result,partyMeetingId){
		var str = '';
		str+='<ul class="activeUlCls  list-inline pull-right">';
		str+='<li class="active sessionsCls " attr_sessionId="0" attr_party_meeting_id="'+partyMeetingId+'">All Sessions</li>';
		for(var i in result){
			str+='<li class="sessionsCls"  attr_sessionId="'+result[i].id+'" attr_party_meeting_id="'+partyMeetingId+'">'+result[i].name+'</li>'; 
		}
		str+='</ul>'; 
		$("#sessionBtnDivId").html(str);    		
	}
	$(document).on("click",".getCmtMemDtls",function(){  
		var position = $(this).attr("attr_position");
		var isNonInvitee = $(this).attr("attr_non_invitee");
		
		if(position == "overview"){
			$("#meetingMemDetailsId").modal("show");     
			$("#meetingMemDetailsBodyId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div>');
		}else{
			$("#meetingMemberDtlsDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div>');
		}
		var sessionId = $(this).attr("attr_session_id");
		var status = $(this).attr("attr_status");
		
		var partyMeetingId = $(this).attr("attr_party_meeting_id");  
		var partyMeetingTypeArr=[];
		$("#specialMeetingUlId li").each(function() {
		  if($(this).find("input").is(":checked")){
			  partyMeetingTypeArr.push($(this).find("input").attr("id"));
		  }
		}); 
		var state = globalState;  
	    
		var jsObj ={ 
			 partyMeetingMainTypeId : 3,
			 state : state,
			 startDateString : globalStartDateForSpecialMeeting,
			 endDateString : globalEndDateForSpecialMeeting,
			 partyMeetingTypeIds:partyMeetingTypeArr,
			 partyMeetingId:partyMeetingId,
			 sessionId : sessionId,
			 status : status,
			 districtId : 0  
		};

		$.ajax({
			type : 'POST',
			url : 'getMeetingMemberDtlsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#meetingMemberDtlsDivId").html('');
			if(result != null && result.length > 0){
				buildMeetingMemberDtls(result,position,isNonInvitee,partyMeetingId,status);
			}else{      
			           
			}  
		});
	});
	function buildMeetingMemberDtls(result,position,isNonInvitee,partyMeetingId,status){
		
		var str = '';
			str+='<div class="row m_top10">';
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
			  str+='<div class="table-responsive">';
			  str+='<table class="table border_top_apply" id="cmtMemberDtlsTableId">';
				str+='<thead>';
					str+='<tr>';
						str+='<th>District Name</th>';
						str+='<th>Leader Name</th>';
						str+='<th>Designation</th>';
						str+='<th>Contact Number</th>';
						str+='<th>Invitation Status</th>';  
						str+='<th>All Sessions</th>';
						if(result[0].sessionLevel.length > 0){
							for(var k in result[0].sessionLevel){      
								str+='<th>'+result[0].sessionLevel[k]+'</th>';
							}  
						}else{
							str+='<th>Attendance</th>';    
						}
					/*if(status =='absent'){
						str+='<th>Absent Reason</th>';    
					}
					*/
					str+='<th> Remarks </th>';    
					str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
				
				
				for(var i in result){
					if(isNonInvitee =="false") {
						if(result[i].isInvitee == "true"){
							str+='<tr>';
								if(result[i].districtName != null && result[i].districtName.length > 0){
									str+='<td>'+result[i].districtName+'</td>';
								}else{
									str+='<td>-</td>';
								}
								
								if(result[i].name != null && result[i].name.length > 0){
									str+='<td>'+result[i].name+'</td>';
								}else{
									str+='<td>-</td>';
								}
								
								if(result[i].status != null && result[i].status.length > 0){
									str+='<td>'+result[i].status+'</td>';
								}else{
									str+='<td>-</td>';   
								}
								
								if(result[i].mobileNo != null && result[i].mobileNo.length > 0){
									str+='<td>'+result[i].mobileNo+'</td>';
								}else{
									str+='<td>-</td>';  
								}
								if(result[i].isInvitee == "true"){
									str+='<td>Invitee</td>'; 
								}else{
									str+='<td class="text-danger">Non Invitee</td>';   
								}
								
								for(var j in result[i].sessionList){
									if(result[i].sessionList[j] == "intime"){
										str+='<td class="text-success">Y('+(result[i].attendedTimeList[parseInt(j)-1]).substring(0,5)+')</td>';
									}else if(result[i].sessionList[j] == "late"){
										str+='<td class="text-danger">Y('+(result[i].attendedTimeList[parseInt(j)-1]).substring(0,5)+')</td>';
									}else if(result[i].sessionList[j] == "absent"){  
										str+='<td>N</td>';  
									}else{  
										str+='<td>'+result[i].sessionList[j]+'</td>';             
									}  
								}
								/*if(status =='absent'){
									if(result[i].remark != null && result[i].remark.length > 0){
										str+='<td>'+result[i].remark+'</td>';
									}else{
										str+='<td><center>-</center></td>';   
									}
								}
								*/
								if(result[i].remark != null && result[i].remark.length > 0){
										str+='<td>'+result[i].remark+'</td>';   
								}else{
										str+='<td><center>-</center></td>';   
									}
							str+='</tr>';	
						}
					}
					else{
						if(result[i].isInvitee == "false"){
							str+='<tr>';
									if(result[i].districtName != null && result[i].districtName.length > 0){
										str+='<td>'+result[i].districtName+'</td>';
									}else{
										str+='<td>-</td>';
									}
									
									if(result[i].name != null && result[i].name.length > 0){
										str+='<td>'+result[i].name+'</td>';
									}else{
										str+='<td>-</td>';
									}
									
									if(result[i].status != null && result[i].status.length > 0){
										str+='<td>'+result[i].status+'</td>';
									}else{
										str+='<td>-</td>';
									}
									
									if(result[i].mobileNo != null && result[i].mobileNo.length > 0){
										str+='<td>'+result[i].mobileNo+'</td>';
									}else{
										str+='<td>-</td>';
									}
									if(result[i].isInvitee == "true"){
										str+='<td>Invitee</td>'; 
									}else{
										str+='<td class="text-danger">Non Invitee</td>';   
									}
									for(var j in result[i].sessionList){
										if(result[i].sessionList[j] == "intime"){
											str+='<td class="text-success">Y('+(result[i].attendedTimeList[parseInt(j)-1]).substring(0,5)+')</td>';
										}else if(result[i].sessionList[j] == "late"){
											str+='<td class="text-danger">Y('+(result[i].attendedTimeList[parseInt(j)-1]).substring(0,5)+')</td>';
										}else if(result[i].sessionList[j] == "absent"){  
											str+='<td>N</td>';  
										}else{  
											str+='<td>'+result[i].sessionList[j]+'</td>';                
										} 
									}
									str+='<td><center>-</center></td>';   
								str+='</tr>';
						}
					}
				}
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
		str+='</div>';
		
		if(position == "overview"){
			$("#meetingMemDetailsBodyId").html(str);
			$("#cmtMemberDtlsTableId").dataTable();   
		}else{
			$("#meetingMemberDtlsDivId").html(str);     
			$("#cmtMemberDtlsTableId").dataTable();
		}
	   
	}
	
	function getDistDtls(locationId,partyMeetingId,sessionId,dataBuildType){
		$("#meetingMemDetailsId").modal("show");     
		$("#meetingMemDetailsBodyId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div>');
		var partyMeetingTypeArr=[];	
		$("#specialMeetingUlId li").each(function() {
			if($(this).find("input").is(":checked")){
				partyMeetingTypeArr.push($(this).find("input").attr("id"));
			}
		}); 
		var state = globalState;  
	   
		
		
			
		if(sessionId != null && sessionId!=undefined)
			sessionId = sessionId;
		else
			sessionId = 0;
		
		if(locationId != null && locationId!=undefined)
			locationId = locationId;
		else
			locationId = 0;
		
		var jsObj ={ 
			 partyMeetingMainTypeId : 3,
			 state : state,
			 startDateString : globalStartDateForSpecialMeeting,
			 endDateString : globalEndDateForSpecialMeeting,
			 partyMeetingTypeIds:partyMeetingTypeArr,
			 partyMeetingId:partyMeetingId,
			 sessionId : sessionId,
			 status : "",
			 districtId : locationId  
		};
		
		$.ajax({
			type : 'POST',
			url : 'getMeetingMemberDtlsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){  
			$("#meetingMemDetailsBodyId").html('');
			if(result != null && result.length > 0){
				buildDistDtls(result,dataBuildType);
			}else{              
			           
			}  
		});
	}
	function buildDistDtls(result,dataBuildType){  

		var str = '';
		str+='<div class="row m_top10">';
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
			str+='<div class="table-responsive">';
				str+='<table class="table border_top_apply" id="cmtMemberDtlsTableId">';
				str+='<thead>';
					str+='<tr>';
						str+='<th>District Name</th>';
						str+='<th>Leader Name</th>';
						str+='<th>Designation</th>';
						str+='<th>Contact Number</th>';
						str+='<th>Invitation Status</th>';  
						str+='<th>All Sessions</th>';
						if(result[0].sessionLevel.length > 0){
							for(var k in result[0].sessionLevel){      
								str+='<th>'+result[0].sessionLevel[k]+'</th>';
							}
						}else{
							str+='<th>Attendance</th>';    
						}
					str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
				for(var i in result){
					if(dataBuildType == result[i].wish){
						str+='<tr>';
							if(result[i].districtName != null && result[i].districtName.length > 0){
								str+='<td>'+result[i].districtName+'</td>';
							}else{
								str+='<td>-</td>';
							}
									
							if(result[i].name != null && result[i].name.length > 0){
								str+='<td>'+result[i].name+'</td>';
							}else{
								str+='<td>-</td>';
							}
									
							if(result[i].status != null && result[i].status.length > 0){
								str+='<td>'+result[i].status+'</td>';
							}else{
								str+='<td>-</td>';   
							}
									
							if(result[i].mobileNo != null && result[i].mobileNo.length > 0){
								str+='<td>'+result[i].mobileNo+'</td>';
							}else{
								str+='<td>-</td>';  
							}
							if(result[i].isInvitee == "true"){
								str+='<td>Invitee</td>'; 
							}else{
								str+='<td class="text-danger">Non Invitee</td>';   
							}
									
							for(var j in result[i].sessionList){
								if(result[i].sessionList[j] == "intime"){
									str+='<td class="text-success">Y('+(result[i].attendedTimeList[parseInt(j)-1]).substring(0,5)+')</td>';
								}else if(result[i].sessionList[j] == "late"){
									str+='<td class="text-danger">Y('+(result[i].attendedTimeList[parseInt(j)-1]).substring(0,5)+')</td>';
								}else if(result[i].sessionList[j] == "absent"){  
									str+='<td>N</td>';  
								}else{  
									str+='<td>'+result[i].sessionList[j]+'</td>';               
								}  
							}		
								str+='</tr>';
					}		
				}
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
		str+='</div>';
		str+='</div>';
		$("#meetingMemDetailsBodyId").html(str);
		$("#cmtMemberDtlsTableId").dataTable();
	}
	
	function getPublicRepAndcommitteeInviteeDtls(id,partyMeetingId){
		var partyMeetingTypeArr=[];	
		$("#specialMeetingUlId li").each(function() {
			if($(this).find("input").is(":checked")){
				partyMeetingTypeArr.push($(this).find("input").attr("id"));
			}
		}); 
		var state = globalState;  
	   
		 
		var partyMeetingIdArr = [];    
		partyMeetingIdArr.push(partyMeetingId);
		var categoryIds = []; 
		var category = '';
		if(id==10 || id==11){
			category = "Committees";
		}else if(id==1 || id==2 || id==12 || id==21 ){
			category = "Representative";      
		}else{
			category = "other"; 
			return;
		}
		$("#meetingMemDetailsId").modal("show");     
		$("#meetingMemDetailsBodyId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div>');
		if(id==2){
			categoryIds.push(2);
			categoryIds.push(12);
		}else{
			categoryIds.push(id);        
		}
		var jsObj ={ 
					 partyMeetingMainTypeId : 3,                  
					 state : state,
					 startDateString : globalStartDateForSpecialMeeting,
					 endDateString : globalEndDateForSpecialMeeting,
					 partyMeetingTypeIds:partyMeetingTypeArr,
					 partyMeetingIds :partyMeetingIdArr,  
					 category : category,
					 categoryIds : categoryIds,
					 location : "invited",
					 sessionId : 0
				  }
		$.ajax({
			type : 'POST',
			url : 'getPublicRepAndcommitteeInviteeDtlsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#meetingMemDetailsBodyId").html('');
			if(result != null ){  
				buildDistDtls(result);          			
			}else{
			} 
		});
	}
	
	function getPublicRepAndcommitteeAttendedDtls(id,status,name,sessionId,partyMeetingId){
		var partyMeetingTypeArr=[];	
		$("#specialMeetingUlId li").each(function() {
			if($(this).find("input").is(":checked")){
				partyMeetingTypeArr.push($(this).find("input").attr("id"));
			}
		}); 
		var state = globalState;  
	   
		var partyMeetingIdArr = [];    
		partyMeetingIdArr.push(partyMeetingId);  
		var categoryIds = []; 
		var category = '';
		if(id==10 || id==11){
			category = "Committees";
		}else if(id==1 || id==2 || id==12 || id==21 ){
			category = "Representative";      
		}else{
			category = "other";
			return;			
		}
		$("#meetingMemDetailsId").modal("show");     
		$("#meetingMemDetailsBodyId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div>');
		if(id==2){
			categoryIds.push(2);
			categoryIds.push(12);
		}else{
			categoryIds.push(id);        
		}
		var jsObj ={ 
					 partyMeetingMainTypeId : 3,                  
					 state : state,
					 startDateString : globalStartDateForSpecialMeeting,
					 endDateString : globalEndDateForSpecialMeeting,
					 partyMeetingTypeIds:partyMeetingTypeArr,
					 partyMeetingIds :partyMeetingIdArr,  
					 category : category,
					 categoryIds : categoryIds,
					 location : "attended",  
					 sessionId : sessionId       
				  }
		$.ajax({
			type : 'POST',
			url : 'getPublicRepAndcommitteeInviteeDtlsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#meetingMemDetailsBodyId").html('');
			if(result != null ){  
				buildDistDtls(result);          			
			}else{
			} 
		});
	}
	function getPublicRepAndcommitteeLateAttendedDtls(id,status,name,sessionId,partyMeetingId){
		var partyMeetingTypeArr=[];	
		$("#specialMeetingUlId li").each(function() {
			if($(this).find("input").is(":checked")){
				partyMeetingTypeArr.push($(this).find("input").attr("id"));
			}
		}); 
		var state = globalState;  
	    
		
		var partyMeetingIdArr = [];    
		partyMeetingIdArr.push(partyMeetingId);  
		var categoryIds = []; 
		var category = '';
		if(id==10 || id==11){
			category = "Committees";
		}else if(id==1 || id==2 || id==12 || id==21 ){
			category = "Representative";      
		}else{
			category = "other";
			return;     
		}
		$("#meetingMemDetailsId").modal("show");     
		$("#meetingMemDetailsBodyId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div>');
		if(id==2){
			categoryIds.push(2);
			categoryIds.push(12);
		}else{
			categoryIds.push(id);        
		}
		var jsObj ={ 
					 partyMeetingMainTypeId : 3,                  
					 state : state,
					 startDateString : globalStartDateForSpecialMeeting,
					 endDateString : globalEndDateForSpecialMeeting,
					 partyMeetingTypeIds:partyMeetingTypeArr,
					 partyMeetingIds :partyMeetingIdArr,  
					 category : category,
					 categoryIds : categoryIds,
					 location : "late",          
					 sessionId : sessionId         
				  }
		$.ajax({
			type : 'POST',
			url : 'getPublicRepAndcommitteeInviteeDtlsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#meetingMemDetailsBodyId").html('');
			if(result != null ){  
				buildDistDtls(result);          			
			}else{
			} 
		});
	}
	function getPublicRepAndcommitteeInviteeAbsentDtls(id,status,name,sessionId,partyMeetingId){
		var partyMeetingTypeArr=[];	
		$("#specialMeetingUlId li").each(function() {
			if($(this).find("input").is(":checked")){
				partyMeetingTypeArr.push($(this).find("input").attr("id"));
			}
		}); 
		var state = globalState;  
	    
		var partyMeetingIdArr = [];    
		partyMeetingIdArr.push(partyMeetingId);  
		var categoryIds = []; 
		var category = '';
		if(id==10 || id==11){
			category = "Committees";
		}else if(id==1 || id==2 || id==12 || id==21 ){
			category = "Representative";      
		}else{
			category = "other";
			return;
		}
		$("#meetingMemDetailsId").modal("show");     
		$("#meetingMemDetailsBodyId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div>');
		if(id==2){
			categoryIds.push(2);
			categoryIds.push(12);
		}else{
			categoryIds.push(id);        
		}
		var jsObj ={ 
					 partyMeetingMainTypeId : 3,                  
					 state : state,
					 startDateString : globalStartDateForSpecialMeeting,
					 endDateString : globalStartDateForSpecialMeeting,
					 partyMeetingTypeIds:partyMeetingTypeArr,
					 partyMeetingIds :partyMeetingIdArr,  
					 category : category,
					 categoryIds : categoryIds,
					 location : "absent",            
					 sessionId : sessionId               
				
				  }
		$.ajax({
			type : 'POST',
			url : 'getPublicRepAndcommitteeInviteeDtlsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#meetingMemDetailsBodyId").html('');
			if(result != null ){  
				buildDistDtls(result);          			
			}else{
			} 
		});
	}
	function getPublicRepAndcommitteeNonInviteeAttendedDtls(id,status,name,sessionId,partyMeetingId){
		var partyMeetingTypeArr=[];	
		$("#specialMeetingUlId li").each(function() {
			if($(this).find("input").is(":checked")){
				partyMeetingTypeArr.push($(this).find("input").attr("id"));
			}
		}); 
		var state = globalState;  
	    
		
		var partyMeetingIdArr = [];    
		partyMeetingIdArr.push(partyMeetingId);  
		var categoryIds = []; 
		var category = '';
		if(id==10 || id==11){
			category = "Committees";
		}else if(id==1 || id==2 || id==12 || id==21 ){      
			category = "Representative";      
		}else{
			category = "other";
			return;
		}
		$("#meetingMemDetailsId").modal("show");     
		$("#meetingMemDetailsBodyId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div>');
		if(id==2){
			categoryIds.push(2);
			categoryIds.push(12);
		}else{
			categoryIds.push(id);        
		}
		var jsObj ={ 
					 partyMeetingMainTypeId : 3,                  
					 state : state,
					 startDateString : globalStartDateForSpecialMeeting,
					 endDateString : globalStartDateForSpecialMeeting,
					 partyMeetingTypeIds:partyMeetingTypeArr,
					 partyMeetingIds :partyMeetingIdArr,  
					 category : category,
					 categoryIds : categoryIds,
					 location : "nonInviteeAttended",                
					 sessionId : sessionId               
				
				  }      
		$.ajax({
			type : 'POST',
			url : 'getPublicRepAndcommitteeInviteeDtlsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#meetingMemDetailsBodyId").html('');
			if(result != null ){  
				buildDistDtls(result);          			
			}else{    
			}   
		});
	}    
  function getinsertDataInToPartyMeetingStatus(){
	  $("#partyMettingStatusIdImg").show();
		$.ajax({
			type : 'POST',
			url : 'insertDataInToPartyMeetingStatusTableAction.action',
			dataType : 'json',
			data : {task:JSON.stringify()}
		}).done(function(result){
			$("#partyMettingStatusIdImg").hide();
			if(result != null && result.message == "success" ){
				//$("#partyMettingStatusId").html("updated  partyMettings status")
			}else{
				//$("#partyMettingStatusId").html("Exception Occurred Please Try again")
			}
			
		});	
	}
$(document).on("click",".updationDetailsCls",function(){
		$("#commentsModalId").modal('show');
		$("#commentsBlock").html('');
		var levelType = $(this).attr("attr_level_type");
		var locationLevel ;
		if(levelType == "Village/Ward"){
			locationLevel = 7;
		}else if(levelType == "Mandal/Town/Division"){
			locationLevel = 4;
		}else if(levelType == "Constituency"){
			locationLevel = 3;
		}else if(levelType == "District"){
			locationLevel = 2;
		}
	   
		$("#mdlHeadingId").html("<span>"+levelType+ " wise Party Meeting Conflicts");
		var jsObj ={ 
		             levelId : locationLevel,
					 startDate : customStartDateMeetings,
					 endDate : customEndDateMeetings
				  }
		$.ajax({
			type : 'POST',
			url : 'getUpdationDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0)
				buildCommentsMeetingDetailsAction(result);
			else
				$("#commentsBlock").html("No Data Available.");
		});
});

function buildCommentsMeetingDetailsAction(result)
{
	var str='';
	str+='<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">';
	for(var i in result){
		str+='<div class="panel panel-default">';
			str+='<div class="panel-heading" role="tab" id="headingOne'+i+'">';
			str+='<div class="row">';
				str+='<div class="col-md-8 col-sm-12 col-xs-8">';
					str+='<h4 class="panel-title">';
					str+='<a role="button" class="meetingsConflictsCls" data-toggle="collapse" data-parent="#accordion" href="#collapseOne'+i+'" aria-expanded="true" aria-controls="collapseOne" attr_div_id="meetingDetailsDivId'+i+'" attr_meeting_id="'+result[i].partyMeetingId+'">'+result[i].partyMeetingName+' </a></h4>';
				str+='</div>';
				str+='<div class="col-md-4 col-sm-12 col-xs-4">';
					str+='<span class="pull-right"><small> Created By : </small>'+result[i].name+'</span>';
				str+='</div>';
				str+='</div>';
			str+='</div>';
			str+='<div id="collapseOne'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne'+i+'">';
			  str+='<div class="panel-body">';
				str+='<div id="meetingDetailsDivId'+i+'"></div>';
			  str+='</div>';
			str+='</div>';
		str+='</div>';
	}
	str+='</div>';
	$("#commentsBlock").html(str);
}

$(document).on("click",".meetingsConflictsCls",function(){
	var meetingId = $(this).attr("attr_meeting_id");
	var divId = $(this).attr("attr_div_id");
	$("#"+divId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div>');
	$('.documentSlickApply').slick('unslick');
	var jsObj ={ 
		partyMeetingId : meetingId
	}
	$.ajax({
		type : 'POST',
		url : 'getDocumentListAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		buildDocumentListDetails(result,divId);
	});
});

function buildDocumentListDetails(result,divId){
	var str='';
	if(result !=null && result.partyMeetingVOList !=null && result.partyMeetingVOList.length>0){
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
		
			for(var i in result.partyMeetingVOList){
				str+='<div class="arrow_box_left">';
			if(result.partyMeetingVOList[i].meetingLevel != null && result.partyMeetingVOList[i].meetingLevel.length > 0){
				str+='<span class="m_0"><strong>UPDATED BY:</strong> '+result.partyMeetingVOList[i].subName+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <strong>MOBILE NO:</strong>'+result.partyMeetingVOList[i].mobileNo+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; MEMBERSHIP NO:<strong style="color:green">'+result.partyMeetingVOList[i].meetingLevel+'</strong></br> <strong>Remarks:&nbsp;</strong> '+result.partyMeetingVOList[i].memberStatus+'</br><span class="pull-right"><strong> TIME:</strong> '+result.partyMeetingVOList[i].insertedTime+'</span></span>';
			}else{
				str+='<span class="m_0"><strong>UPDATED BY:</strong> '+result.partyMeetingVOList[i].subName+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <strong>MOBILE NO:</strong>'+result.partyMeetingVOList[i].mobileNo+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</br> <strong>Remarks:&nbsp;</strong> '+result.partyMeetingVOList[i].memberStatus+'</br><span class="pull-right"><strong> TIME:</strong> '+result.partyMeetingVOList[i].insertedTime+'</span></span>';
			}
				str+='</div>';
				str+='<ul class="list-inline documentSlickApply">';
				if(result.partyMeetingVOList[i].docmentsList !=null && result.partyMeetingVOList[i].docmentsList.length>0){
					for(var j in result.partyMeetingVOList[i].docmentsList){
						str+='<li><img class="image-responsive thumbnail" src="http://mytdp.com/'+result.partyMeetingVOList[i].docmentsList[j]+'" style="width:800px;height:400px;"></li>';
					}
				}
				str+='</ul>';
			}
		str+='</div>';
	}
	$("#"+divId).html(str);
	$(".documentSlickApply").slick({
			 slide: 'li',
			 slidesToShow: 4,
			 slidesToScroll: 2,
			 infinite: false,
			 variableWidth: true
		});
}

$(document).on("click",".specialMeetingBtnClsNew",function(){
	$(this).addClass('specialMeetingsDate');
	$(".specialMeetingBtnClsNew").removeClass('btn-primary');
	$(this).addClass('btn-primary');
	getPartySpecialMeetingsMainTypeOverview(0);
});
defaultFunctionaliySpecicalMeeting();
function defaultFunctionaliySpecicalMeeting(){
$(".lastMonthSpecialCls").removeClass('btn-primary');
$(".defalutSpecialMeetingCls").addClass('btn-primary');
$(".lastMonthSpecialCls").removeClass("specialMeetingsDate");
$(".defalutSpecialMeetingCls").addClass("specialMeetingsDate");

$(".specialMeetingBtnClsNew").each(function(){
	if($(this).attr("attr_date") == 'default'){
		$(this).attr("attr_startDate",moment().startOf('month').format("DD/MM/YYYY"));
		$(this).attr("attr_endDate",moment().endOf('month').format("DD/MM/YYYY"));
	}else if($(this).attr("attr_date") == 'lastMonth'){
		$(this).attr("attr_startDate",moment().subtract(1,'month').startOf('month').format("DD/MM/YYYY"));
		$(this).attr("attr_endDate",moment().subtract(1,'month').endOf('month').format("DD/MM/YYYY"));
	}
});	
}
