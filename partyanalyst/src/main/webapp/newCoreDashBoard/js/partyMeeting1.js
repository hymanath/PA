//GLOBAL VARIABLES IN MEETINGS.
 var customStartDateMeetings = moment().subtract(1, 'month').startOf('month').format('DD/MM/YYYY');
 var customEndDateMeetings = moment().subtract(1, 'month').endOf('month').format('DD/MM/YYYY');
 
var globalStateId=1; 
	globalClickActionMeetings();
	function globalClickActionMeetings()
	{
		$(".specialMeetingBtnClsNew").each(function(){
			if($(this).attr("attr_date") == 'default'){
				$(this).attr("attr_startDate",moment().startOf('month').format("DD/MM/YYYY"));
				$(this).attr("attr_endDate",moment().endOf('month').format("DD/MM/YYYY"));
			}else if($(this).attr("attr_date") == 'lastMonth'){
				$(this).attr("attr_startDate",moment().subtract(1,'month').startOf('month').format("DD/MM/YYYY"));
				$(this).attr("attr_endDate",moment().subtract(1,'month').endOf('month').format("DD/MM/YYYY"));
			}
		});	
		$(document).on("click",".committeeMeetingsSettings",function(e){
			//$("#committeeTypeDivId").show();
			e.stopPropagation();
			$(".settingsDropDown").toggle();
		});
		$(document).on("click","#committeeMeetingsSettingsSubmitId",function(e){
			getPartyMeetingBasicCountDetails();
			$("#committeeTypeDivId").hide();
		});
		$(document).on("click",".specialMeetingsCollapseBtn",function(e){
			if($(this).html() == '+')
			{
				$(this).html("-")
			}else{
				$(this).html("+")
			}
			$(".specialMeetingsCollapseBody").toggle();
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
		$(document).on("click",function(){
			$(".settingsDropDown,.specialMeetingDropDown ").hide();
		});
		$(document).on("click",".meetingsConflictsCls1",function(){
			$("#myModalImageId").modal("show");
			$("#myModalLabelId").html("Image Details");
			globalImgsMdlCnt = 1;
			var meetingId = $(this).attr("attr_meeting_id");
			var divId = $(this).attr("attr_div_id");
			var str='';
				str+='<div class="row">';
					str+='<div class="col-md-12">';
						str+='<nav class="navbar navbar-default navbarCollapseCustom">';
							str+='<div class="collapse navbar-collapse " id="bs-example-navbar-collapse-1">';
							  str+='<ul class="nav navbar-nav" id="popupDaysDiv">';
							  str+='</ul>';
							str+='</div>';
						str+='</nav>';
						str+='<div class="pad_10" id="popupImages">';
						
						str+='</div>';
						
						str+=' <div id="paginationDivId"></div>';
					str+='</div>';
				str+='</div>';

			$("#buildPoupupImage").html(str);
			$("#popupImages").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div>');
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
				buildDocumentListDetails1(result);
			});
		});
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
		$(document).on("click",".updationDetailsCls",function(){
			$("#commentsModalId").modal('show');
			$("#commentsBlock").html('');
			var levelType = $(this).attr("attr_level_type");
			$("#levelTypeId").attr("attr_level_type",levelType);
			var status = $(this).attr("attr_status");
			$("#levelTypeId").attr("attr_status",status);
			var locationLevel ;
			$("#thirdPartyStatusId").val("ALL");// 33333 
			$("#constId").val(0);
			$("#distId").val(0);
			if(levelType == "Village/Ward"){
				locationLevel = 7;
			}else if(levelType == "Mandal/Town/Division"){
				locationLevel = 4;
			}else if(levelType == "Constituency"){
				locationLevel = 3;
			}else if(levelType == "District"){
				locationLevel = 2;
			}
		  var distIdsList = [];
		  var constIdsList =[];
			 if( $("#distId").val() != null && $("#distId").val() != 0){
			 distIdsList.push($("#distId").val());
			 }
			   if($("#constId").val() != null && $("#constId").val() != 0){
			   constIdsList.push($("#constId").val());
			   }
		 var thridPartyStatus =$("#thirdPartyStatusId").val();
			$("#mdlHeadingId").html("<span>"+levelType+ " wise Party Meeting Conflicts");
			
			var jsObj ={ 
						 levelId : locationLevel,
						 startDate : customStartDateMeetings,   
						 endDate : customEndDateMeetings,
						 status : status,
						 distIdsList :distIdsList,
						 constIdsList :  constIdsList,
						 locationType : "district",
						 thridPartyStatus : status
					  };
						$('#thirdPartyStatusId').val(status);
			$.ajax({
				type : 'POST',
				url : 'getUpdationDetailsAction.action',
				dataType : 'json',
				data : {task:JSON.stringify(jsObj)}
			}).done(function(result){
				if(result != null && result.length > 0){
					//buildCommentsMeetingDetailsAction(result);
				   buildCommentsMeetingDetailsAction1(result);
				}else{
					$("#commentsBlock").html("No Data Available.");
				}
			});
		});
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
			  /*if(districtId == 0 && constituencyId ==0 && mandalId ==0){
				 $(".districtFilterErrrorCls").html("Please Select Search Type.");
				 return;			 
			  }
			 $(".districtFilterErrrorCls").html(' ');*/
		
			  //if(districtId > 0){
			if(meetingLevel == 'District'){
			  locationId = districtId; 
			  locationType = "District";
			  }
			  //if(constituencyId > 0){
			if(meetingLevel == 'Constituency'){
				locationId = constituencyId; 
				locationType = "Constituency";
				if(districtId > 0){
					locationId = districtId; 
					locationType = "District";
				}
				if(constituencyId > 0){
					locationId = constituencyId; 
					locationType = "Constituency";
				}
			  }
			  //if(mandalId > 0){
			if(meetingLevel == 'Mandal/Town/Division' || meetingLevel == 'Village/Ward'){
			  locationId = mandalId; 
			  locationType = "Mandal";
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
			var reportTypeId = $("#commentFilterSelectBoxId").val();
			var reportType;

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

		$(document).on("click",".specialMeetingBtnClsNew",function(){
			$(this).addClass('specialMeetingsDate');
			$(".specialMeetingBtnClsNew").removeClass('btn-primary');
			$(this).addClass('btn-primary');
			getPartySpecialMeetingsMainTypeOverview(0);
		});
		
		$(document).on("click",".specialMeetingBtncls",function(){
			var isChecked=false; 
			$("#specialMeetingUlId li").each(function(){
				if($(this).find("input").is(":checked")){
					isChecked = true;
				}
			});
			if(isChecked == true){
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

		$(document).on("click",".stateLevelMeetingSeeting",function(){
			$("#stateLevelMeetingDivId").show();
			$(".settingsStateLevelMeetingDropDown").toggle();
			$(".specialMeetingDropDown").hide();
		});
		$(document).on("click",".specialMeetingDropDown,#committeeTypeDivId",function(e){
			e.stopPropagation();
		});

		$(document).on("click",".specialMeetingSeeting",function(e){
			e.stopPropagation();
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
		$(document).on("click",".meetingLiCls",function(){
			var memberType=$(this).attr("attr_value");
			 if(memberType != null && memberType == "strong"){
			  buildgUserTypeWiseConductedAndMayBeTopFiveStrongPerResults(globalUserWiseMeetingMemberRslt); 
			 }else if(memberType == "poor"){
			  buildgUserTypeWiseConductedAndMayBeTopFivePoorPerResults(globalUserWiseMeetingMemberRslt);
			 }
		});
		
		var globalMeetingMembersResult = '';
		$(document).on("click",".getCmtMemDtls,.getCmtMemDtlsDesgClick",function(){
			globalCount = 0;
			var position = $(this).attr("attr_position");
			
			if(position == "overview"){
				$("#meetingMemDetailsId").modal("show");     
				$("#meetingMemDetailsBodyId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div>');
			}else{
				$("#meetingMemDetailsId").modal("show");
				//$("#meetingMemberDtlsDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div>');
				$("#meetingMemDetailsBodyId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div>');
			}
			
			var status = $(this).attr("attr_status");
			
			var desgSearchRequired = $(this).attr("attr_search"); 
			var searchDesignation ="";
			if(desgSearchRequired == "required"){
				searchDesignation = $(this).attr("attr_desg_name"); 
			}else{
				globalMeetingMembersResult = '';
			}
			var isNonInvitee = $(this).attr("attr_non_invitee");
			
			if(desgSearchRequired == "notrequired"){
				
				var sessionId = $(this).attr("attr_session_id");
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
					 startDateString : customStartDateMeetings,
					 endDateString : customEndDateMeetings,
					 partyMeetingTypeIds:partyMeetingTypeArr,
					 partyMeetingId:partyMeetingId,
					 sessionId : sessionId,
					 status : status,
					 districtId : 0  ,
					 isNonInvitee : isNonInvitee
				  };
				
					$.ajax({
					type : 'POST',
					url : 'getMeetingMemberDtlsAction.action',
					dataType : 'json',
					data : {task:JSON.stringify(jsObj)}
				}).done(function(result){
					$("#meetingMemberDtlsDivId").html('');
					if(result != null && result.length > 0){
						globalMeetingMembersResult = result;
						buildMeetingMemberDtls(result,position,isNonInvitee,status,desgSearchRequired,searchDesignation);
					} 
				});
			}else{
				buildMeetingMemberDtls(globalMeetingMembersResult,position,isNonInvitee,status,desgSearchRequired,searchDesignation);
			}
		});
		$(document).on("click",".moreMeetingsBlocksIcon",function(){
			$(".moreMeetingsBlocksList").show();
			var $this = $(this);
			var expandName = $this.attr("expand_event_name");
			if(expandName == "statelevelmeeting")
			{
				
			}else if(expandName == "mainMeetings")
			{
				var selectedType = $(this).parent().attr("attr_selected_type");
				if(selectedType != null && selectedType != undefined && selectedType=="sessionMeeting"){
					$("#meetingLevelHIghChartsDivId").html(' ');
					$("#userAccessLevelLocationDivId").html(' ');
					$("#districtWisePartyMeetingTypeDivId").html(' ');
					$("#districtWiseSpecialMeetingsGraph").html(' ');
					$("#partyMeetingOverviewTabDiv").html(' ');
					$(".moreMeetingsBlocksDetailed").show();
					var party_meeting_id = $(this).attr("party_meeting_id");
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
				}
				
			}else if(expandName == "multiLocation")
			{
				$(this).removeClass("moreMeetingsBlocksIconMulti").addClass("moreMeetingsBlocksIcon");
				var locLevelId = $(this).attr('attr_levelId'); 
				var partyMeetingGroupId = $(this).attr('attr_group_id');
				var sessionId= $(this).attr('attr_sessionId');
				$(".detailedMeetngsBlkId,.multiLocationMeetingsCLs").show();
				getDistWiseMeetingDtlsForDiffLevelOfMeetings(locLevelId,partyMeetingGroupId,sessionId,0);
			}else if(expandName == "specialMeetings")
			{
				$("[expand-block-more='meetings']").show();
				var party_meeting_id = $(this).attr("party_meeting_id");
				var party_meeting_type_id = $(this).attr("party_meeting_type_id");
				getPartySpecialMeetingsMainTypeOverview(party_meeting_id);
				getParyMeetingDetailsDistrictWise(0,party_meeting_id,"");
				$(".detailedMeetngsBlkId,.moreMeetingsBlocksList").hide();
				$(".multiMetingDetailedBlock").parent("ul").hide();
			}
		});
		$(document).on("click",".sessnWiseAttendnceBsd",function(){
			$(".moreMeetingsBlocksMultiLocationComparision,.moreMultiMeetingsBlocksDetailed").hide();
			
			$(".multiMeetingChortCls,.detailedMeetngsBlkId").show();
			var locLevelId = $(this).attr('attr_levelId');
			var partyMeetingGroupId = $(this).attr('attr_group_id');
			var sessionId= $(this).attr('attr_sessionId');
			var num = $(this).attr('attr_num');
			getDistWiseMeetingDtlsForDiffLevelOfMeetings(locLevelId,partyMeetingGroupId,sessionId,num);
		});
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
	
	$('#dateRangeIdForMeetings').on('apply.daterangepicker', function(ev, picker) {
	  
		customStartDateMeetings = picker.startDate.format('DD/MM/YYYY');
		customEndDateMeetings = picker.endDate.format('DD/MM/YYYY');
		  
		singleBlockDateStart = picker.startDate.format('MMM YY');
		singleBlockDateEnd = picker.endDate.format('MMM YY');
		$(".meetingsHiddenBlock").show();
		$(".stateGeneralMeeting,.stateLevelMeetingsExpand,.specialMeetings, .statelevelSessionMeeting").find('i').removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen");
		$(".showMoreBlockCls").attr("attr_main_type_meeting_id",1);//committee meeting
		getPartyMeetingBasicCountDetails();
		//getCustomPartyMeetingsMainTypeOverViewDataDetails();
		getUserTypeWiseMeetingCounductedNotCounductedMayBeDetailsCnt();
		getPartySpecialMeetingsMainTypeOverview(0);
		getPartyMeetingsMainTypeStateLevelOverview();
		var dates= $("#dateRangeIdForMeetings").val();
		// $("#dateMeetingHeadingId").html(" THIS MONTH ( "+dates+" )");
		$("#dateMeetingHeadingId").html(picker.chosenLabel+" ( "+dates+" )");
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
		 str+="<li><label class='checkbox-inline'><input checked type='checkbox' id="+result[i].id+">&nbsp&nbsp"+result[i].name+"</label></li>";
	 }
	 str+='</ul> ';
	str+='<button class="btn btn-success btn-xs" id="committeeMeetingsSettingsSubmitId">SUBMIT</button>';
	 $("#committeeTypeDivId").html(str);
 }	
function getPartyMeetingBasicCountDetails()
{ 
	$("#meetingBasicCountDivId").html(spinner);

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
						str+='<p class="text-muted text-capital">Yes&nbsp&nbsp<a attr_meeting_status="Y" attr_comment="yes" attr_level_type="'+levelWiseResult[i].name+'" style="color: rgb(173, 173, 173);cursor: pointer;text-decoration: none;" class="glyphicon glyphicon-comment overAllMeetingCls" data-toggle="tooltip" data-placement="top" id="partyMeetingsId'+i+'"  onclick="overAllMeetings(this.id);" title="Counducted Meeting Comment '+levelWiseResult[i].conductedCommentCnt+'('+levelWiseResult[i].conductedCommentCntPer+'%)" data-original-title=""></a> </p>';
					
						str+='<h4>'+levelWiseResult[i].conductedCount+'<span class="font-10 text-success"> '+levelWiseResult[i].conductedCountPer+'%</span></h4>';
						 if(levelWiseResult[i].yesCount != null && levelWiseResult[i].yesCount > 0){
							str+='<span class="glyphicon glyphicon-info-sign updationDetailsCls" style="cursor: pointer;margin-left: 4px;font-size:14px;" attr_level_type="'+levelWiseResult[i].name+'" attr_status="Yes" title="Click here to get Conducted Meetings details (from Maybe)"></span>';
						 }
						 //str+='</h4>';
					}else if(levelWiseResult[i].conductedCount > 0){
						str+='<p class="text-muted text-capitalize">yes </p>';	  
						str+='<h4 attr_meeting_status="Y" attr_comment="No" attr_level_type="'+levelWiseResult[i].name+'" style="cursor: pointer;" class=" overAllMeetingCls" id="partyMeetingsIdA'+i+'"  onclick="overAllMeetings(this.id);">'+levelWiseResult[i].conductedCount+'<span class="font-10 text-success"> '+levelWiseResult[i].conductedCountPer+'%</span></h4>';
						 if(levelWiseResult[i].yesCount != null && levelWiseResult[i].yesCount > 0){
							 
							str+='<span class="glyphicon glyphicon-info-sign updationDetailsCls" style="cursor: pointer;margin-left: 4px;font-size:14px;" attr_level_type="'+levelWiseResult[i].name+'" attr_status="Yes" title="Click here to get Conducted Meetings details (from Maybe)"></span>';
						 }
						 //str+='</h4>';
					 }else{
						str+='<p class="text-muted text-capitalize">yes</p>';	  
						str+='<h4>'+levelWiseResult[i].conductedCount+'<span class="font-10 text-success"> '+levelWiseResult[i].conductedCountPer+'%</span><!--<span class="glyphicon glyphicon-info-sign updationDetailsCls" 	style="cursor: pointer;margin-left: 4px;font-size:14px;" attr_level_type="'+levelWiseResult[i].name+'"></span>--></h4>';
					 }
					 str+='</td>';  
						str+='<td>';
					   if(levelWiseResult[i].notConductedCommentCnt > 0 && levelWiseResult[i].notConductedCount > 0){
						str+='<p class="text-muted text-capital">no &nbsp&nbsp<a attr_meeting_status="N" attr_comment="yes" attr_level_type="'+levelWiseResult[i].name+'" style="color: rgb(173, 173, 173);cursor: pointer;text-decoration: none;" class="glyphicon glyphicon-comment overAllMeetingCls" id="partyMeetingsIdB'+i+'"  onclick="overAllMeetings(this.id);" data-toggle="tooltip" data-placement="top" title="Not Counducted Meeting Comment '+levelWiseResult[i].notConductedCommentCnt+'('+levelWiseResult[i].notConductedCommentCntPer+'%)" data-original-title=""></a></p>'; 
						str+='<h4 id="partyMeetingsIdB'+i+'" onclick="overAllMeetings(this.id);" style="cursor: pointer;">'+levelWiseResult[i].notConductedCount+' <span class="font-10 text-danger"> '+levelWiseResult[i].notConductedCountPer+'%</span></h4>';	
						  if(levelWiseResult[i].noCount != null && levelWiseResult[i].noCount > 0){
							  str+=' <span class="glyphicon glyphicon-info-sign updationDetailsCls overAllMeetingCls" style="cursor: pointer;margin-left: 4px;font-size:14px;" attr_level_type="'+levelWiseResult[i].name+'" attr_status="No" title=" Click here to get Not Conducted Meetings details (from Maybe)"> </span>';
						  }
						   //str+='</h4>';		
						}else if(levelWiseResult[i].notConductedCount > 0){
						  str+='<p class="text-muted text-capital">no</p>';	 
						  str+='<h4 attr_meeting_status="N" attr_comment="No" attr_level_type="'+levelWiseResult[i].name+'" style="cursor: pointer;" class="overAllMeetingCls" id="partyMeetingsIdC'+i+'"  onclick="overAllMeetings(this.id);">'+levelWiseResult[i].notConductedCount+' <span class="font-10 text-danger"> '+levelWiseResult[i].notConductedCountPer+'%</span></h4>';	
						  if(levelWiseResult[i].noCount != null && levelWiseResult[i].noCount > 0){
							  str+=' <span class="glyphicon glyphicon-info-sign updationDetailsCls" style="cursor: pointer;margin-left: 4px;font-size:14px;" attr_level_type="'+levelWiseResult[i].name+'" attr_status="No" title=" Click here to get Not Conducted Meetings details (from Maybe)"> </span>';
						  }
						   //str+='</h4>';
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
							 str+='<h4><span attr_meeting_status="M" attr_level_type="'+levelWiseResult[i].name+'" style="cursor: pointer;" attr_comment="No" class="overAllMeetingCls" id="partyMeetingsIdE'+i+'"  onclick="overAllMeetings(this.id);">'+levelWiseResult[i].mayBeCount+'</span><span class="font-10 text-customColor"> '+levelWiseResult[i].mayBeCountPer+'%</span><span class="glyphicon glyphicon-info-sign updationDetailsCls" style="cursor: pointer;margin-left: 4px;font-size:14px;" attr_level_type="'+levelWiseResult[i].name+'" attr_status="maybe"></span></h4>';
							  }else{
								  str+='<p class="text-muted text-capital">maybe</p>';	
							 str+='<h4><span attr_meeting_status="M" attr_level_type="'+levelWiseResult[i].name+'" style="cursor: pointer;" attr_comment="No" class="overAllMeetingCls" id="partyMeetingsIdE'+i+'"  onclick="overAllMeetings(this.id);">'+levelWiseResult[i].mayBeCount+'</span><span class="font-10 text-customColor"> '+levelWiseResult[i].mayBeCountPer+'%</span><!--<span class="glyphicon glyphicon-info-sign updationDetailsCls" style="cursor: pointer;margin-left: 4px;font-size:14px;" attr_level_type="'+levelWiseResult[i].name+'" attr_status="maybe" ></span>--></h4>';
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
function getPartyMeetingsMainTypeStateLevelOverview(){
	$("#stateLevelMeetingBasicCnt").closest(".panelBlock").show();
	$("#stateLevelMeetingBasicCnt").html(spinner);
	   var partyMeetingTypeArr=[];
	   $("#stateLevelMeetingUlId li").each(function() {
		  if($(this).find("input").is(":checked")){
			  partyMeetingTypeArr.push($(this).find("input").attr("id"));
		  }
	   });   
	    $(".specialMeetingBtnClsNew").each(function(){
	});  
	var state = globalStateId
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
						 str+='<td>';
							if(result[i].imagesCount != null && result[i].imagesCount > 0)
							  str+='<h4>'+result[i].imagesCount+'</h4>';
							else
								str+='<h4> - </h4>'
							  str+='<p class="text-muted text-capital">total images</p>';
						 str+='</td>';
						 str+='<td>';
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
												str+='<span class="statelevelSessionMeeting" expand-icon-inner="meetings" expand_event_name="specialMeetings" party_meeting_type_id="'+result[i].id+'"  party_meetingId="'+result[i].subList1[k].id+'" style="background-color:#fff;padding:4px;margin-right: 10px; margin-left: 10px;""><i class="glyphicon glyphicon-fullscreen "  style="cursor:pointer;"></i></span>';
												str+='<span class="text-capital" style="margin-right: 10px;">Invitees : <b>'+result[i].subList1[k].invitedCount+'</b></span>';
												if(result[i].subList1[k].imagesCount != null && result[i].subList1[k].imagesCount > 0)
													str+='<span class="text-capital getModalImagesCls " attr_Meeting_level_id="0" attr_Meeting_id="'+result[i].subList1[k].id+'" attr_count="0" attr_location_value="0" style="cursor:pointer;" >Images : <b>'+result[i].subList1[k].imagesCount+'</b></span>';
												//else
												//	str+='<span class="text-capital"></span>';
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
									 str+='<h5 class="text-success getCmtMemDtls" attr_search="notrequired" style="cursor:pointer;" attr_position="overview" attr_status="attended" attr_session_id="'+sessionId+'" attr_party_meeting_id="'+result[i].subList1[k].id+'" attr_non_invitee="false"><u>'+result[i].subList1[k].subList1[s].attendedCount+'</u> <span class="font-10 text-success"> '+result[i].subList1[k].subList1[s].attendedPerc+'%</span></h5>';
								 }
								 
								 str+='</td>';
								 str+='<td>';
								 if(result[i].subList1[k].subList1[s].lateAttendedCount == 0 || result[i].subList1[k].subList1[s].lateAttendedCount == null ){
									 str+='<h5 class="text_oragane"> 0  <span class="font-10 text-danger"> 0.00 %</span> </h5>';
								 }else{
									var attendedCount = parseInt(result[i].subList1[k].subList1[s].attendedCount);
									var lateCount = parseInt(result[i].subList1[k].subList1[s].lateAttendedCount);
									
									var perc = (lateCount*100.0/attendedCount);
													
									 str+='<h5 class="text_oragane getCmtMemDtls" attr_search="notrequired" style="cursor:pointer;" attr_position="overview" attr_status="late" attr_session_id="'+sessionId+'" attr_party_meeting_id="'+result[i].subList1[k].id+'" attr_non_invitee="false"><u>'+result[i].subList1[k].subList1[s].lateAttendedCount+'</u> <span class="font-10 text-danger"> '+perc.toFixed(2)+'%</span> </h5>';
								 }
									
								 str+='</td>';
								 str+='<td>';
								 if(result[i].subList1[k].subList1[s].notAttendedCount == 0 || result[i].subList1[k].subList1[s].notAttendedCount == null ){
									 str+='<h5> 0  <span class="font-10 text-danger">  0.00 %</span></h5>';
								 }else{
									 
									 var attendedCount = parseInt(result[i].subList1[k].invitedCount);
									var lateCount = parseInt(result[i].subList1[k].subList1[s].notAttendedCount);
									
									var perc = (lateCount*100.0/attendedCount);
									
									 str+='<h5 class="getCmtMemDtls" attr_search="notrequired" style="cursor:pointer;" attr_position="overview" attr_status="absent" attr_session_id="'+sessionId+'" attr_party_meeting_id="'+result[i].subList1[k].id+'" attr_non_invitee="false"><u>'+result[i].subList1[k].subList1[s].notAttendedCount+' </u><span class="font-10 text-danger"> '+perc.toFixed(2)+'%</span></h5>';
								 }   
								 
								 str+='</td>';
									 
								str+='<td style="text-align:center;">';
								 if(result[i].subList1[k].subList1[s].nonInviteeCount == 0 || result[i].subList1[k].subList1[s].nonInviteeCount == null ){
									 str+='<h5>  - </h5>';
								 }else{
									 str+='<h5 class="getCmtMemDtls" attr_search="notrequired" style="cursor:pointer;" attr_position="overview" attr_status="attended" attr_session_id="'+sessionId+'" attr_party_meeting_id="'+result[i].subList1[k].id+'" attr_non_invitee="true"><u>'+result[i].subList1[k].subList1[s].nonInviteeCount+' </u></h5>';
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
function getPartySpecialMeetingsMainTypeOverview(partyMeetingId){
	if(partyMeetingId == 0){
		$("#specialMeetingBasicCnt").closest(".panelBlock").show();
		$("#specialMeetingBasicCnt").html(spinner);
		partyMeetingId="0";
	}
	$(".specialMeetingBtnClsNew").each(function(){
		if($(this).hasClass('specialMeetingsDate'))
		{
			customStartDateMeetings = $(this).attr("attr_startDate");
		    customEndDateMeetings = $(this).attr("attr_endDate");
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
		 startDateString : customStartDateMeetings,
		 endDateString : customEndDateMeetings,
		 partyMeetingTypeIds:partyMeetingTypeArr,
		 partyMeetingId:partyMeetingId
		 
	  }
	$.ajax({
		type : 'POST',
		url : 'getPartyMeetingsMainTypeOverViewDataAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(partyMeetingId >0 ){
			if(result != null && result.length > 0){
				buildOverviewPartiMeetingOverviewResustlt(result,"specialMeetingBasicCnt",3,"specialMeetingsExpandId",partyMeetingTypeArr,customStartDateMeetings,customEndDateMeetings,state,partyMeetingId);
			}else{
				$('#partyMeetingOverviewTabDiv').html("NO DATA");
			}
		}else{
			$("#specialMeetingBasicCnt").html(" ");
			if(result != null && result.length > 0){
				buildPartyMeetingOverviewRslt(result,"specialMeetingBasicCnt",3,"specialMeetingsExpandId",partyMeetingTypeArr,customStartDateMeetings,customEndDateMeetings,state);
			}else{
				$("#specialMeetingBasicCnt").html("NO DATA AVAILABLE.");	   
				$("#specialMeetingBasicCnt").closest(".panelBlock").hide();
			}  
		}
	   
		  
	   
	});
}
function buildOverviewPartiMeetingOverviewResustlt(result,divId,mainTypeMeetingId,expandTypeId,partyMeetingTypeArr,fromDateStr,toDateStr,state,partyMeetingId){
	
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
								str+='<td class="text-success getCmtMemDtls" attr_search="notrequired" style="text-align:center;cursor:pointer;" attr_party_meeting_id="'+partyMeetingId+'" attr_session_id="0" attr_status="attended" attr_non_invitee="false" >Total&nbsp;Attended<br><u>'+partyMeetingArr.attendedCount+'</u></td>';
							}
							if(partyMeetingArr.lateAttendedCount == 0 || partyMeetingArr.lateAttendedCount == null){
								str+='<td class="text_oragane" style="text-align:center" >Late&nbsp;Attended<br> 0 </td>';
							}else{
								str+='<td class="text_oragane getCmtMemDtls" attr_search="notrequired" style="text-align:center;cursor:pointer;" attr_party_meeting_id="'+partyMeetingId+'" attr_session_id="0" attr_status="late" attr_non_invitee="false" >Late&nbsp;Attended<br> <u>'+partyMeetingArr.lateAttendedCount+' </u></td>';
							}
							if(partyMeetingArr.notAttendedCount == 0 || partyMeetingArr.notAttendedCount == null){
								str+='<td style="text-align:center">Absent<br> 0  </td>';
							}else{
								str+='<td class="getCmtMemDtls" attr_search="notrequired" style="text-align:center;cursor:pointer;" attr_party_meeting_id="'+partyMeetingId+'" attr_session_id="0" attr_status="absent" attr_non_invitee="false" >Absent<br> <u>'+partyMeetingArr.notAttendedCount+' </u></td>';
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
									str+='<td class="text-success getCmtMemDtls" attr_search="notrequired" style="text-align:center;cursor:pointer;" attr_party_meeting_id="'+partyMeetingId+'" attr_session_id="'+sessionId+'" attr_status="attended" attr_non_invitee="false">Total&nbsp;Attended<br> <u>'+partyMeetingArr.subList1[k].attendedCount+' </u></td>';
								}
								if(partyMeetingArr.subList1[k].lateAttendedCount == 0 || partyMeetingArr.subList1[k].lateAttendedCount == null){
									str+='<td class="text_oragane" style="text-align:center" >Late&nbsp;Attended<br> 0 </td>';
								}else{
									str+='<td class="text_oragane getCmtMemDtls" attr_search="notrequired" style="text-align:center;cursor:pointer;" attr_party_meeting_id="'+partyMeetingId+'" attr_session_id="'+sessionId+'" attr_status="late" attr_non_invitee="false" >Late&nbsp;Attended<br><u>'+partyMeetingArr.subList1[k].lateAttendedCount+'</u></td>';
								}
								if(partyMeetingArr.subList1[k].notAttendedCount == 0 || partyMeetingArr.subList1[k].notAttendedCount == null){
									str+='<td style="text-align:center" >Absent<br> 0 </td>';    
								}else{
									str+='<td class="getCmtMemDtls" attr_search="notrequired" style="text-align:center;cursor:pointer;" attr_party_meeting_id="'+partyMeetingId+'" attr_session_id="'+sessionId+'" attr_status="absent" attr_non_invitee="false">Absent<br><u>'+partyMeetingArr.subList1[k].notAttendedCount+'</u></td>';
								}  
								if(partyMeetingArr.subList1[k].nonInviteeCount == 0 || partyMeetingArr.subList1[k].nonInviteeCount == null){
									str+='<td><span data-toggle="tooltip" data-placement="top" title="Non Invitees Attended" style="cursor:default;"> NIA</span><br> 0 </td>'; 
								}else{
									str+='<td class="getCmtMemDtls" attr_search="notrequired" style="text-align:center;cursor:pointer;" attr_party_meeting_id="'+partyMeetingId+'" attr_session_id="'+sessionId+'" attr_status="attended" attr_non_invitee="true" ><span data-toggle="tooltip" data-placement="top" title="Non Invitees Attended" > NIA</span><br><u>'+partyMeetingArr.subList1[k].nonInviteeCount+'</u></td>';
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
						 str+='<td>';
							if(result[i].imagesCount != null && result[i].imagesCount > 0)
							  str+='<h4>'+result[i].imagesCount+'</h4>';
							else
								str+='<h4> - </h4>'
							  str+='<p class="text-muted text-capital">total images</p>';
						 str+='</td>';
						 str+='<td>';
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
												str+='<span class="statelevelSessionMeeting" expand-icon-inner="meetings" expand_event_name="specialMeetings" party_meeting_type_id="'+result[i].id+'"  party_meetingId="'+result[i].subList1[k].id+'" style="background-color:#fff;padding:4px;margin-right: 10px; margin-left: 10px;""><i class="glyphicon glyphicon-fullscreen "  style="cursor:pointer;"></i></span>';
												str+='<span class="text-capital" style="margin-right: 10px;">Invitees : <b>'+result[i].subList1[k].invitedCount+'</b></span>';
												if(result[i].subList1[k].imagesCount != null && result[i].subList1[k].imagesCount > 0)
													str+='<span class="text-capital getModalImagesCls " attr_Meeting_level_id="0" attr_Meeting_id="'+result[i].subList1[k].id+'" attr_count="0" attr_location_value="0" style="cursor:pointer;" >Images : <b>'+result[i].subList1[k].imagesCount+'</b></span>';
												//else
												//	str+='<span class="text-capital"></span>';
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
									 str+='<h5 class="text-success getCmtMemDtls" attr_search="notrequired" style="cursor:pointer;" attr_position="overview" attr_status="attended" attr_session_id="'+sessionId+'" attr_party_meeting_id="'+result[i].subList1[k].id+'" attr_non_invitee="false"><u>'+result[i].subList1[k].subList1[s].attendedCount+'</u> <span class="font-10 text-success"> '+result[i].subList1[k].subList1[s].attendedPerc+'%</span></h5>';
								 }
								 
								 str+='</td>';
								 str+='<td>';
								 if(result[i].subList1[k].subList1[s].lateAttendedCount == 0 || result[i].subList1[k].subList1[s].lateAttendedCount == null ){
									 str+='<h5 class="text_oragane"> 0  <span class="font-10 text-danger"> 0.00 %</span> </h5>';
								 }else{
									var attendedCount = parseInt(result[i].subList1[k].subList1[s].attendedCount);
									var lateCount = parseInt(result[i].subList1[k].subList1[s].lateAttendedCount);
									
									var perc = (lateCount*100.0/attendedCount);
													
									 str+='<h5 class="text_oragane getCmtMemDtls" attr_search="notrequired" style="cursor:pointer;" attr_position="overview" attr_status="late" attr_session_id="'+sessionId+'" attr_party_meeting_id="'+result[i].subList1[k].id+'" attr_non_invitee="false"><u>'+result[i].subList1[k].subList1[s].lateAttendedCount+'</u> <span class="font-10 text-danger"> '+perc.toFixed(2)+'%</span> </h5>';
								 }
									
								 str+='</td>';
								 str+='<td>';
								 if(result[i].subList1[k].subList1[s].notAttendedCount == 0 || result[i].subList1[k].subList1[s].notAttendedCount == null ){
									 str+='<h5> 0  <span class="font-10 text-danger">  0.00 %</span></h5>';
								 }else{
									 
									 var attendedCount = parseInt(result[i].subList1[k].invitedCount);
									var lateCount = parseInt(result[i].subList1[k].subList1[s].notAttendedCount);
									
									var perc = (lateCount*100.0/attendedCount);
									
									 str+='<h5 class="getCmtMemDtls" attr_search="notrequired" style="cursor:pointer;" attr_position="overview" attr_status="absent" attr_session_id="'+sessionId+'" attr_party_meeting_id="'+result[i].subList1[k].id+'" attr_non_invitee="false"><u>'+result[i].subList1[k].subList1[s].notAttendedCount+' </u><span class="font-10 text-danger"> '+perc.toFixed(2)+'%</span></h5>';
								 }   
								 
								 str+='</td>';
									 
								str+='<td style="text-align:center;">';
								 if(result[i].subList1[k].subList1[s].nonInviteeCount == 0 || result[i].subList1[k].subList1[s].nonInviteeCount == null ){
									 str+='<h5>  - </h5>';
								 }else{
									 str+='<h5 class="getCmtMemDtls" attr_search="notrequired" style="cursor:pointer;" attr_position="overview" attr_status="attended" attr_session_id="'+sessionId+'" attr_party_meeting_id="'+result[i].subList1[k].id+'" attr_non_invitee="true"><u>'+result[i].subList1[k].subList1[s].nonInviteeCount+' </u></h5>';
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
function getMultiLocationWiseMeetingGroupsData(){
	$("#MultiLocationWiseMeetingGroupsData").html(spinner);
	var dates=$('.searchDateCls ').val();

	var jObj = {
		fromDateStr : '01/02/2000'  ,//customStartDateMeetings,
		toDateStr : '01/02/2020',//customEndDateMeetings,
		activityMemberId : 44,
		stateId : globalStateId,
		partyMeetingMainTypeId:4
	};
	 
	$.ajax({
	  type:'GET',
	  url: 'getMultiLocationWiseMeetingGroupsDataAction.action',
	 data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		buildMultiLocationWiseMeetingGroupsData(result);
	});
}
function buildMultiLocationWiseMeetingGroupsData(result)
{
	var str='';
	var levelId=0;
	var meetingGrpId =0;
	str+='<div class="row">';
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
			for(var i in result.userAccessLevelList)
			{
				if(i==0)
					meetingGrpId = result.userAccessLevelList[i].levelId;
				str+='<h4 class="panel-title text-capital ">'+result.userAccessLevelList[i].name+'</h4>';
				str+='<table class="table table-bordered bg_ED">';
				str+='<tr>';
					
					for(var j in result.userAccessLevelList[i].userAccessLevelValuesList)
					{
						if(result.userAccessLevelList[i].userAccessLevelValuesList != null && result.userAccessLevelList[i].userAccessLevelValuesList.length>0)
						{
							if(j==0)
							{
								levelId = result.userAccessLevelList[i].userAccessLevelValuesList[j].levelId; 
							    locationName =result.userAccessLevelList[i].name;
								str+='<td style="position:relative;" class="bg_E0"> ';
									str+='<p class="text-muted text-capital">'+result.userAccessLevelList[i].userAccessLevelValuesList[j].name+'<span class="meetingsHeadingExpandIcon multicLocationMeetingCls"  expand-icon-inner="meetings" expand_event_name="multiLocation" attr_levelId="'+levelId+'" attr_group_id="'+meetingGrpId+'"  attr_sessionId="0" style="margin-left:5px;font-size:12px;"><i class="glyphicon glyphicon-fullscreen"></i></span></p>';
									str+='<span class="multiLocationWiseMeetingCount" attr_count="'+result.userAccessLevelList[i].userAccessLevelValuesList[j].count+'" attr_group_id="'+result.userAccessLevelList[i].levelId+'" attr_levelId="'+result.userAccessLevelList[i].userAccessLevelValuesList[j].levelId+'" attr_locationName ="'+result.userAccessLevelList[i].name+'">'+result.userAccessLevelList[i].userAccessLevelValuesList[j].count+'</span>';
								str+='</td>';	
							}else{
								str+='<td style="position:relative;">';
									str+='<p class="text-muted text-capital">'+result.userAccessLevelList[i].userAccessLevelValuesList[j].name+'<span class="meetingsHeadingExpandIcon multicLocationMeetingCls"  attr_levelId="'+levelId+'" attr_group_id="'+meetingGrpId+'"  attr_sessionId="0"  style="margin-left:5px;display:none;font-size:12px;"><i class="glyphicon glyphicon-fullscreen"></i></span></p>';
									str+='<span class="multiLocationWiseMeetingCount" attr_count="'+result.userAccessLevelList[i].userAccessLevelValuesList[j].count+'" attr_group_id="'+result.userAccessLevelList[i].levelId+'" attr_levelId="'+result.userAccessLevelList[i].userAccessLevelValuesList[j].levelId+'" attr_locationName ="'+result.userAccessLevelList[i].name+'">'+result.userAccessLevelList[i].userAccessLevelValuesList[j].count+'</span>';
								str+='</td>';	
							}
								
													
						}
					}
						str+='</tr>';	
				str+='</table>';
				str+='<div class="row">';
					str+='<div class="col-md-12 col-xs-12 col-sm-12">';
						str+='<div class="bg_E0 pad_10">';
							str+='<div id="partyLevelIdWiseMeetingsCount"></div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			}
		str+='</div>';
	str+='</div>';
	$("#MultiLocationWiseMeetingGroupsData").html(str);
	getPartyLevelIdWiseMeetingsCount(meetingGrpId,levelId,result.userAccessLevelList[i].userAccessLevelValuesList[j].count,locationName);
}
function getPartyLevelIdWiseMeetingsCount(meetingGrpId,levelId,count,locationName)
{

	$("#partyLevelIdWiseMeetingsCount").html(spinner);

	var jObj = {
		fromDateStr : '01/02/2000'  ,//customStartDateMeetings,
		toDateStr : '01/02/2020',//customEndDateMeetings,
		activityMemberId : globalActivityMemberId,
		stateId : globalStateId,
		partyMeetingMainTypeId:4,
		partyMeetingLevelId:levelId,
		meetingGrpId:meetingGrpId
	};
	
	$.ajax({
	  type:'GET',
	  url: 'getPartyLevelIdWiseMeetingsCountAction.action',
	 data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		buildPartyLevelIdWiseMeetingsCount(result,count,levelId,locationName,meetingGrpId);
	});
}
function buildPartyLevelIdWiseMeetingsCount(result,count,levelId,locationName,meetingGrpId)
{
	var str='';
	str+='<table class="table bg_E0">';
		str+='<tr>';
			str+='<td>';
				str+='<p class="text-muted">Planned</p>';
				str+='<p><a class="locWisedetails" attr_meetingGrpId="'+meetingGrpId+'" attr_level_id="'+levelId+'" attr_type="All" style="cursor:pointer;">'+count+'</a></p>';	
			str+='</td>';
			str+='<td class="bg_ED">';
				str+='<p class="text-muted">Conducted</p>';
				if(result.conducted != null && result.conducted > 0)
				{
					str+='<p><a class="locWisedetails" attr_meetingGrpId="'+meetingGrpId+'" attr_level_id="'+levelId+'" attr_type="conducted" style="cursor:pointer;">'+result.conducted+'</a></p>';
				}else{
					str+='<p> - </p>';
				}
			str+='</td>';
			str+='<td>';
				str+='<p class="text-muted">Not Conducted</p>';
				str+='<p><a class="locWisedetails" attr_meetingGrpId="'+meetingGrpId+'" attr_level_id="'+levelId+'" attr_type="Not-Conducted" style="cursor:pointer;">'+((count)-(result.conducted))+'  </a><small></small></p>';	
			str+='</td>';
			str+='<td>';
				str+='<p class="text-muted">Image Covered</p>';
				if(result.imagesCovered != null && result.imagesCovered>0)
					str+='<p>'+result.imagesCovered+'</p>';	
				else
					str+='<p> - </p>';	
			str+='</td>';
			str+='<td>';
				str+='<p class="text-muted">Images</p>';
				if(result.totalImages != null && result.totalImages>0)
					str+='<p><a class="getModalImagesCls" attr_Meeting_level_id="'+levelId+'"  attr_location ="'+locationName+'" attr_location_value="0" attr_count="0" style="cursor:pointer;">'+result.totalImages+'</a></p>';	
				else
					str+='<p> - </p>';					
			str+='</td>';
		str+='</tr>';
		str+='<tr>';
			str+='<td class="bg_ED">';
			if(result.invited != null && result.invited > 0)
			{
				str+='<p> <a style="font-weight:bold;" href="javascript:{getAttendedCadreDetails(\'invited\',0,'+levelId+','+meetingGrpId+',0,4,0);}"> '+result.invited+'</a></p>';
			}
			else
				str+='<p> - </p>';
				str+='<p class="text-muted">Invited</p>';
			str+='</td>';
			str+='<td class="bg_ED">';
			if(result.attended != null && result.attended > 0)
			{
				str+='<p>  <a  style="font-weight:bold;"  href="javascript:{getAttendedCadreDetails(\'attended\',0,'+levelId+','+meetingGrpId+',0,4,0);}"> '+result.attended+' </a></p>';
			}
			else
				str+='<p> - </p>';
				str+='<p class="text-success">Attended</p>';
			str+='</td>';
			
			str+='<td class="bg_ED">';
			if(result.late != null && result.late > 0)
			{
					str+='<p>  <a   class="text-danger" style="font-weight:bold;"  href="javascript:{getAttendedCadreDetails(\'late\',0,'+levelId+','+meetingGrpId+');}"> '+result.late+' </a></p>';
			}else
				str+='<p> - </p>';
				str+='<p class="text-danger">Late</p>';
			str+='</td>';
			str+='<td class="bg_ED">';
			if(result.absent != null && result.absent > 0)
			{				
				str+='<p>  <a  style="font-weight:bold;"  href="javascript:{getAttendedCadreDetails(\'absent\',0,'+levelId+','+meetingGrpId+',0,4,0);}"> '+result.absent+' </a></p>';
			}else
				str+='<p> - </p>';
				str+='<p class="text-muted">Absent</p>';
			str+='</td>';
			str+='<td class="bg_ED">';
			if(result.nonInvitee != null && result.nonInvitee > 0)
			{				
				str+='<p>  <a  style="font-weight:bold;"  href="javascript:{getAttendedCadreDetails(\'noninvitees\',0,'+levelId+','+meetingGrpId+',0,4,0);}"> '+result.nonInvitee+' </a></p>';				
			}
			else
				str+='<p> - </p>';
			str+='<p class="text-muted">Non-Invitee</p>';
				str+='</td>';
				
		str+='</tr>';
		
		if(result.subList != null && result.subList.length>0){
			for(var k in result.subList){
				str+='<tr>';
					
						str+='<td class="bg_ED">';
							str+='<p>'+result.subList[k].name+'</p>';
						str+='</td>';
					
					if(result.subList[k].inviteeAttendedCount != null && result.subList[k].inviteeAttendedCount > 0)
					{
						str+='<td class="bg_ED">';
								str+='<p>  <a  style="font-weight:bold;"  href="javascript:{getAttendedCadreDetails(\'attended\','+result.subList[k].id+','+levelId+','+meetingGrpId+',0,4,0);}"> '+result.subList[k].inviteeAttendedCount+'</p>';
								//str+='<p class="text-success">Attended</p>';
						str+='</td>';
					}
					else{
						str+='<td class="bg_ED">';
								str+='<p> - </p>';								
						str+='</td>';
					}
					if(result.subList[k].lateCount != null && result.subList[k].lateCount > 0)
					{
						str+='<td class="bg_ED">';
							str+='<p >  <a  class="text-danger" style="font-weight:bold;"  href="javascript:{getAttendedCadreDetails(\'late\','+result.subList[k].id+','+levelId+','+meetingGrpId+',0,4,0);}"> '+result.subList[k].lateCount+' </a></p>';
							//str+='<p class="text-danger">Late</p>';
						str+='</td>';
					}else{
						str+='<td class="bg_ED">';
								str+='<p> - </p>';								
						str+='</td>';
					}
					var absent =result.subList[k].absentCount;
					
					if(absent != null && absent > 0)
					{
						str+='<td class="bg_ED">';
							str+='<p>  <a  style="font-weight:bold;"  href="javascript:{getAttendedCadreDetails(\'absent\','+result.subList[k].id+','+levelId+','+meetingGrpId+',0,4,0);}"> '+absent+'  </a> </p>';
							//str+='<p class="text-muted">Absent</p>';
						str+='</td>';
					}else{
						str+='<td class="bg_ED">';
								str+='<p>  -  </p>';								
						str+='</td>';
					}
					
					if(result.subList[k].nonInviteeCount != null && result.subList[k].nonInviteeCount > 0)
					{
						str+='<td class="bg_ED">';
							str+='<p>  <a  style="font-weight:bold;"  href="javascript:{getAttendedCadreDetails(\'nonInvitees\','+result.subList[k].id+','+levelId+','+meetingGrpId+',0,4,0);}"> '+result.subList[k].nonInviteeCount+' </a></p>';
							//str+='<p class="text-muted">Non-Invitee</p>';
						str+='</td>';
					}else{
						str+='<td class="bg_ED">';
								str+='<p> - </p>';								
						str+='</td>';
					}
						
				str+='</tr>';
			}
		}
	str+='</table>';
	$("#partyLevelIdWiseMeetingsCount").html(str);
}
function getUserTypeWiseMeetingCounductedNotCounductedMayBeDetailsCnt(){
	$("#stateLevelMeetingBlockId").html(' ');	
	$("#userTypeWiseTopFiveStrongAndPoorMeetingMemsDivId").html(spinner);
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
function getCommitteesAndPublicRepresentativeMembersInvitedAndAttendedToSeeionWiseMeetingDtls(partyMeetingMainTypeId,partyMeetingTypeIdsString,partyMeetingId){
	$("#userTypeWiseTopFiveStrongAndPoorMeetingMemsDivId").html(' ');
	$("#stateLevelMeetingBlockId").html(spinner);
	var partyMeetingTypeArr = partyMeetingTypeIdsString.split(",");
	var state = globalState
	
	var jsObj ={ 
				 partyMeetingMainTypeId : partyMeetingMainTypeId,
				 state : state,
				 startDateString : customStartDateMeetings,
				 endDateString : customEndDateMeetings,
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
		  buildCommitteesAndPublicRepresentativeMembersInvitedAndDtls(result,"")	
		}else{
          $("#stateLevelMeetingBlockId").html("NO DATA AVAILABLE.");
		} 
	});
}
function getCmmttsAndPblcRprsnttvMembersInvitedAndAttendedToSeeionWiseMultiLocationMeetingDtls(locLevelId,partyMeetingGroupId){
	$("#stateLevelMeetingBlockId").html(spinner);
	var jsObj ={    
		activityMemberId:globalActivityMemberId,
		partyMeetingMainTypeId : 4,          
		locLevelId:locLevelId,                
		startDateString:"01/03/2017",
		endDateString:"30/03/2025",   
		state:globalStateId,                             
		partyMeetingGroupId:partyMeetingGroupId  
	}      
	$.ajax({
		type : 'POST',
		url : 'getCmmttsAndPblcRprsnttvMembersInvitedAndAttendedToSeeionWiseMultiLocationMeetingDtlsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		buildCommitteesAndPublicRepresentativeMembersInvitedAndDtls(result,"noClick");	
	});    
}
function buildCommitteesAndPublicRepresentativeMembersInvitedAndDtls(result,isClickRequired){
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
				str+='<li style="padding:0px !important;border-right:none !important"><div style="height:200px;" id="stateLevelMeetingBlockIdGr'+i+'" class="chartLiMee1"></div></li>';
				str+='<li style="padding:0px !important;border-right:none !important;"><div style="height:200px;" id="stateLevelMeetingBlockIdGr1'+i+'" class="chartLiMee" ></div></li>';
				str+='<li style="padding:0px !important;border-right:none !important;"><div style="height:200px;" id="stateLevelMeetingBlockIdGr2'+i+'" class="chartLiMee" ></div></li>';
				str+='<li style="padding:0px !important;border-right:none !important"><div style="height:200px;" id="stateLevelMeetingBlockIdGr3'+i+'" class="chartLiMee" ></div></li>';
				str+='<li style="padding:0px !important;border-right:none !important"><div style="height:200px;" id="stateLevelMeetingBlockIdGr4'+i+'" class="chartLiMee" ></div></li>';
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
		var type = {
			type: 'column',
			backgroundColor:'transparent'
		};
		var legend = {
			enabled: false,
		}
		var tooltip = {
			valueSuffix: '',
			shared : true,
		}
		var yAxis = {
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
		};
		var xAxis = {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,	
			categories: ['Invitees Absent']
		};
		var plotOptions = {
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
						getPublicRepAndcommitteeInviteeAbsentDtls(id,status,name,sessionId,partyMeetingId,isClickRequired);            
						}         
					}
				}
			}
		};
		var xAxis1 = {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			
			categories: inviteeNameArray,
			labels: {
						formatter: function() {
							return this.value.toString().substring(0, 2)+'..';
						},
					}
		};
		var plotOptions1 = {
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
						getPublicRepAndcommitteeInviteeDtls(id,partyMeetingId,isClickRequired);        
						}                     
					}
				}
			}
		};
		var xAxis2 =  {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,	
				categories: ['Invitees Attended']
			};
		var plotOptions2 = {
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
						getPublicRepAndcommitteeAttendedDtls(id,status,name,sessionId,partyMeetingId,isClickRequired);
						}         
					}
				}
			}
		};
		var xAxis3 = {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,	
				categories: ['Invitees Late']
			};
		var plotOptions3 = {
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
						getPublicRepAndcommitteeLateAttendedDtls(id,status,name,sessionId,partyMeetingId,isClickRequired);          
						}         
					}
				}
			}  
		};
		var xAxis4 = {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,	
				categories: ['Non Invitees Attended']
			};
		var plotOptions4 = {
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
							getPublicRepAndcommitteeNonInviteeAttendedDtls(id,status,name,sessionId,partyMeetingId,isClickRequired);
						}         
					}
				}
			}
		};
		if(inviteeArr != 0 && inviteeArr.length > 0){
			highcharts('stateLevelMeetingBlockIdGr'+i,type,xAxis1,yAxis,legend,inviteeArr,plotOptions1,tooltip);
		}else{
		   $('#stateLevelMeetingBlockIdGr'+i).html("No Data Available")
		}
		if(attendedArr != 0 && attendedArr.length > 0){
			highcharts('stateLevelMeetingBlockIdGr1'+i,type,xAxis2,yAxis,legend,attendedArr,plotOptions2,tooltip)
		}else{
		   $('#stateLevelMeetingBlockIdGr1'+i).html("No Data Available")
		}
		
		if(lateAttendedArr != null && lateAttendedArr.length > 0){
			highcharts('stateLevelMeetingBlockIdGr2'+i,type,xAxis3,yAxis,legend,lateAttendedArr,plotOptions3,tooltip)
		}else{
		   $('#stateLevelMeetingBlockIdGr2'+i).html("No Data Available")
		}
		if(absentArr != null && absentArr.length > 0){
			highcharts('stateLevelMeetingBlockIdGr3'+i,type,xAxis,yAxis,legend,absentArr,plotOptions,tooltip);
		}else{
			$('#stateLevelMeetingBlockIdGr3'+i).html("No Data Available")
		}
		
		highcharts('stateLevelMeetingBlockIdGr4'+i,type,xAxis4,yAxis,legend,nonInviteesAttArr,plotOptions4,tooltip);
  }
}
function buildMeetingMemberDtls(result,position,isNonInvitee,status,desgSearchRequired,searchDesignation)
{
	
	var str = '';
	//Building Summary
	if(position == "overview"){
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
								str+='( <span class ="getCmtMemDtlsDesgClick" attr_search="required" attr_position="'+position+'" attr_non_invitee="'+isNonInvitee+'" attr_status="'+status+'" attr_desg_name="'+result[0].publicRepDesgList[i].name+'" style="font-weight:bold;color:green;cursor:pointer;">'+result[0].publicRepDesgList[i].count+'</span> )';
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
	}
	
	
 //BULDING MEMBERS
	str+='<div class="row m_top10">';
	str+='<div class="col-md-12 col-xs-12 col-sm-12">';
	  str+='<div class="table-responsive">';
	  str+='<table class="table border_top_apply" id="cmtMemberDtlsTableId2">';
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
			str+='<th> Remarks </th>';    
			str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
		
		for(var i in result){
			var build = false;
			if(desgSearchRequired == "required"){
			  if(result[i].subList != null && result[i].subList.length > 0){
					for( var j in result[i].subList){
						if(result[i].subList[j].trim().toUpperCase() == searchDesignation.trim().toUpperCase()){
							build = true;
							break;
						}
					}
				}
			}else{
				build = true;
			}
			if(build){
			  //block
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
				if(result[i].remark != null && result[i].remark.length > 0){
					str+='<td>'+result[i].remark+'</td>';   
				}else{
					str+='<td><center>-</center></td>';   
				}
			  str+='</tr>';					  
			}
		}
	   str+='</tbody>';
	   str+='</table>';
	str+='</div>';
	str+='</div>';
	str+='</div>';
	
	if(position == "overview"){
		$("#meetingMemDetailsBodyId").html(str);
		$("#cmtMemberDtlsTableId2").dataTable();   
	}else{
		$("#meetingMemDetailsBodyId").html(str);     
		$("#cmtMemberDtlsTableId2").dataTable();
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
				   startDateString : customStartDateMeetings,
				   endDateString : customEndDateMeetings,              
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
					str+='<h4 class="panel-title">Party Meetings</h4>';	
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
function getDistWiseMeetingDtlsForDiffLevelOfMeetings(locLevelId,partyMeetingGroupId,sessionId,num)
{  
		if(sessionId == 0){
			$("#districtWiseSpecialMeetingsGraph").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		}else{
			$("#districtWiseGraphSpecialMee"+num).html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		}
	
	if(locLevelId != 0 && locLevelId.length == 1){
		locLevelId = parseInt(locLevelId);
	}else if(locLevelId != 0 && locLevelId.length == 3){
		locLevelId = 7;
	}else if(locLevelId != 0 && locLevelId.length == 5){
		locLevelId = 4;
	}
	var jsObj ={
		activityMemberId:globalActivityMemberId,
		partyMeetingMainTypeId : 4,          
		locLevelId:locLevelId,                          
		startDateString:"01/03/2010",
		endDateString:"30/03/2017",   
		state:globalStateId,                                 
		partyMeetingGroupId:partyMeetingGroupId,
		sessionId:sessionId               
	}      
		$.ajax({
			type : 'POST',
			url : 'getDistWiseMeetingDtlsForDiffLevelOfMeetingsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}  
		}).done(function(result){
			buildParyMeetingDetailsLocLvlWise(result,sessionId,num);
		});  
}
function buildParyMeetingDetailsLocLvlWise(resultList,sessionId,num){
	if(sessionId == 0){
		$("#districtWiseSpecialMeetingsGraph").html('');  
	}else{
		$("#districtWiseGraphSpecialMee"+num).html('');
	}
	var str='';
	for(var j in resultList){
		var result = resultList[j];
		if(result != null && result.length > 0){
			
			if(sessionId == 0){
				str+='<div><h4><span class="headingColor text-capitalize">'+result[0].name+' Level Multi Location Meetings </span>'; 
				for(var k in result[0].sessionVOs){
					str+='<span style="margin-left : 20px" class="active sessnWiseAttendnceBsd btn btn-mini btn-xs " attr_levelId="'+result[0].locLevelIdList+'" attr_group_id="1" attr_num="'+j+'" attr_sessionId="'+result[0].sessionVOs[k].id+'" >'+result[0].sessionVOs[k].name+'</span>';
				}
				str+='<div id="districtWiseGraphSpecialMee'+j+'" class="chartLiD" style="height:300px" ></div></h4></div>';
			}else{
				str+='<div id="districtWiseGraphSpecialMee'+num+'" class="chartLiD" style="height:300px" ></div></h4></div>';
			}
		}
	}

	if(sessionId == 0){
		$("#districtWiseSpecialMeetingsGraph").html(str);  
	}else{
		$("#districtWiseGraphSpecialMee"+num).html(str);
	}		
	
	for(var j in resultList){  
		var result = resultList[j];
		if(result != null && result.length > 0){
			var locationNameArray =[];
			var attendedCountArray = [];
			var inviteAbsentCountArray = [];
			for(var i in result){
				locationNameArray.push(result[i].locationName);
				var sessionId = 0;
				if(result[i].sessionId != null && parseInt(result[i].sessionId)>0){
					sessionId = parseInt(result[i].sessionId);
				}
				attendedCountArray.push({"dataBuildType":"attended","y":parseInt(result[i].attendedCount),"id":parseInt(result[i].locationId),"meetingId":parseInt(result[i].meetingId),"sessionId":parseInt(sessionId),"locLevelIdList":result[i].locLevelIdList});
				inviteAbsentCountArray.push({"dataBuildType":"absent","y":parseInt(result[i].inviteAbsentCount),"id":parseInt(result[i].locationId),"meetingId":parseInt(result[i].meetingId),"sessionId":parseInt(sessionId),"locLevelIdList":result[i].locLevelIdList});
			}  
		}
		if(sessionId == 0){
			functionHighchartsForAttendence(j,locationNameArray,attendedCountArray,inviteAbsentCountArray);
		}else{
			functionHighchartsForAttendence(num,locationNameArray,attendedCountArray,inviteAbsentCountArray);
		}
	}
}
function functionHighchartsForAttendence(id,locationNameArray,attendedCountArray,inviteAbsentCountArray){
	$(function () {
		$('#districtWiseGraphSpecialMee'+id).highcharts({
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
							var locLevelIdList = this.locLevelIdList;
							var sessionId = this.sessionId;
							var dataBuildType = this.dataBuildType;
							getInviteeAtendedDetails(locationId,locLevelIdList,sessionId,dataBuildType);
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
		 districtId : locationId ,
		 isNonInvitee : false			 
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
function getPublicRepAndcommitteeInviteeDtls(id,partyMeetingId,isClickRequired){
	if(isClickRequired=="noClick"){
		return;  
	}
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
function convetFormat(date){
   var dateArr=date.split("/");
   return dateArr[1]+"/"+dateArr[0]+"/"+dateArr[2];
}
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
			$("#districtSlctBxId").append('<option value="0">All</option>');
			for(var i in result){
			 $("#districtSlctBxId").append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
			}
		}
	});  	
}
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
		  $("#mandalSlctBxId").append('<option value="0">All</option>');
		if(result != null && result.length > 0){
			$("#constituencySlctBxId").append('<option value="0">All</option>');
			for(var i in result){
			 $("#constituencySlctBxId").append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
			}
		}
	});  	
}
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
			 $("#mandalSlctBxId").append('<option value="0">All</option>');
			for(var i in result){
			  $("#mandalSlctBxId").append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
			}
		}
	});  	
}
var globalDesignationSummaryResult;
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
	$("#meetingSummaryDtlsTblId").html('');
	var ajaxUrl ="getPartyMeetingCommentsDetailsAction.action";
	if(meetingLevel == "District")
	   ajaxUrl="getDistrictPartyMeetingCommentsDetailsAction.action";

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
		url : ajaxUrl,
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		   if(statusType=="directRslt"){
			 $("#meetingCommentProcessingImgId").hide();
		   }else{
			 $("#meetingSubLevelRsltProcessingImgId").hide();
		   }
		if(result != null && result.length > 0){
			if(meetingLevel == "District"){
				globalDesignationSummaryResult = result[0];
				buildMeetingIndividualDtlsRslt(result,divId,statusType,meetingLevel,"","");
			}
			else
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
function buildMeetingIndividualDtlsRslt(result,divId,statusType,meetingLevel,designationSumm,searchtpe){
	if(designationSumm == 'designaSumm'){
	  var str2='';
	  if(result != null && result.length > 0 ){
			//str+='<div class="col-md-12 col-xs-12 col-sm-12" id="meetingDesignSummaryDtlsTblId">';
			str2+='<div class="panel panel-default">';
			  str2+='<div class="panel-heading">';
				str2+='<h3 class="panel-title" style="text-transform:uppercase">'+searchtpe+' - DESIGNATIONS SUMMARY</h3>';
			  str2+='</div>';
			  str2+='<div class="panel-body">';
					str2+='<p style="font-size:15px;" class="m_top10">';
						if(result != null && result.length > 0){
							 for(var i in result){
								str2+='<span style="text-transform:uppercase;">'+result[i].name+'</span> ';
								str2+='( <span style="font-weight:bold;color:green;">'+result[i].count+'</span> )';
								if( i!= result.length -1 ){
									str2+=' , ';
								}
							 }
						}
					str2+='</p>';
			  str2+='</div>';
			str2+='</div>';
			//str+='</div>';
			
			$("#meetingDesignSummaryDtlsTblId").html(str2);
	  }
  }
  else{
	  var str='';
   var levelId=1;
   
   var totalInvitedCount = 0;
   var totalInviteeAttndedCount = 0;
   var totalAbscentCount = 0;
   var totalNonInviteeCount = 0;
   
  /* str+='<div style="background-color:#EDEEF0;border:1px solid #ddd">';
	str+='<label class="radio-inline">';
	  str+='<input type="radio" name="optradio" class="desigSummaryCls" attr_search="invited">Invited';
	str+='</label>';
	str+='<label class="radio-inline">';
	  str+='<input type="radio" name="optradio" class="desigSummaryCls" attr_search="attended" checked>Attended';
	str+='</label>';
	str+='<label class="radio-inline">';
	  str+='<input type="radio" name="optradio" class="desigSummaryCls" attr_search="inviteeAttended">Invitee Attended';
	str+='</label>';
	str+='<label class="radio-inline">';
	  str+='<input type="radio" name="optradio" class="desigSummaryCls" attr_search="absent">Absent';
	str+='</label>';
	str+='<label class="radio-inline">';
	  str+='<input type="radio" name="optradio" class="desigSummaryCls" attr_search="nonInvitee">Non-Invitee';
	str+='</label>';
   str+='</div>';*/
   if(statusType == "directRslt"){
   //var str='';
	if(result[0].inviteeAttndDesgnList != null && result[0].inviteeAttndDesgnList.length > 0 ){
		str+='<div class="row">';
			str+='<div class="col-md-12 col-xs-12 col-sm-12" id="meetingDesignSummaryDtlsTblId">';
			str+='<div class="panel panel-default">';
			  str+='<div class="panel-heading">';
				str+='<h3 class="panel-title">INVITEE ATTENDED - DESIGNATIONS SUMMARY</h3>';
			  str+='</div>';
			  str+='<div class="panel-body">';
					str+='<p style="font-size:15px;" class="m_top10">';
						if(result[0].inviteeAttndDesgnList != null && result[0].inviteeAttndDesgnList.length > 0){
							 for(var i in result[0].inviteeAttndDesgnList){
								str+='<span style="text-transform:uppercase;">'+result[0].inviteeAttndDesgnList[i].name+'</span> ';
								str+='( <span style="font-weight:bold;color:green;">'+result[0].inviteeAttndDesgnList[i].count+'</span> )';
								if( i!= result[0].inviteeAttndDesgnList.length -1 ){
									str+=' , ';
								}
							 }
						}
					str+='</p>';
			  str+='</div>';
			str+='</div>';
			str+='</div>';
			str+='</div>';
			
			//$("#meetingDesignSummaryDtlsTblId").html(str2);
	  }
   }
   
   str+='<div class="table-responsive">';
   if(statusType == "directRslt"){
	str+='<table style="background-color:#EDEEF0;border:1px solid #ddd" class="table table-bordered" id="meetingCommentDataTblId">';   
   }else{
	str+='<table style="background-color:#EDEEF0;border:1px solid #ddd" class="table table-bordered" id="subLevelDataTblRsltId">';   
   }
   str+='<thead>';
   str+='<tr>';
	if(meetingLevel == "District"){
		levelId=2;
		 str+='<th rowspan="2" style="text-align: center;">District</th>';
		 if(meetingLevel == "Constituency"){
			 levelId=3;
		 str+='<th rowspan="2" style="text-align: center;">Constituency</th>'	 
		 }
		 if(meetingLevel=="Village/Ward" || meetingLevel=="Mandal/Town/Division"){
			str+='<th rowspan="2" style="text-align: center;">Constituency</th>'	  
			str+='<th rowspan="2" style="text-align: center;">Mandal/Town/Division</th>'	  
		 }
		 str+='<th rowspan="2" style="text-align: center;">Meeting Name</th>';
		 str+='<th rowspan="2" style="text-align: center;">Conducted Date</th>';
		 
		
			 str+='<th rowspan="2" style="text-align: center;">Invited</th>';
			 if(result[0].subList != null && result[0].subList.length > 0){
				 for(var i in result[0].subList){
					 str+='<th colspan="3" style="text-align: center;">'+result[0].subList[i].sessionName+'</th>';
				 }
			 }
			  str+='<th rowspan="2" style="text-align: center;"> Images</th>';
			 str+='<th rowspan="2" style="text-align: center;">Comment  </th>';
			 str+='</tr>';
			 str+='<tr>';
			 if(result[0].subList != null && result[0].subList.length > 0){
				 for(var i in result[0].subList){
					 str+='<th>Invitee Attended</th>';
					 str+='<th>Absent</th>';
					 str+='<th>Non-Invitee Attended</th>';
					 //str+='<th>imagesCount</th>';
					 //str+='<th>imagesCovered</th>';
				 }
			 }
		 }else{
			 
			 str+='<th style="text-align: center;">District</th>';
			 if(meetingLevel == "Constituency"){
			 str+='<th style="text-align: center;">Constituency</th>'	 
			 }
			 if(meetingLevel=="Village/Ward" || meetingLevel=="Mandal/Town/Division"){
				str+='<th style="text-align: center;">Constituency</th>'	  
				str+='<th style="text-align: center;">Mandal/Town/Division</th>'	  
			 }
			 str+='<th style="text-align: center;">Meeting Name</th>';
			 str+='<th style="text-align: center;">Conducted Date</th>';
			 str+='<th style="text-align: center;">Comment </th>';
		 }
		 
		 str+='</tr>';
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
		   if(meetingLevel == "District"){
		  if(result[i].invitedCount > 0){
			  str+='<td><a class="attendanceClass" attr_cadreType="invited" attr_levelId="'+meetingLevel+'" attr_partyMeetingId="'+result[i].id+'" attr_meeting_group_id="0" style="cursor:pointer;">'+result[i].invitedCount+'</a></td>';
			  totalInvitedCount = parseInt(totalInvitedCount)+parseInt(result[i].invitedCount);
		  }
		  else
			  str+='<td> - </td>';
		  if(result[i].subList != null && result[i].subList.length > 0){
			  for(var j in result[i].subList){
				if(result[i].subList[j].inviteeAttendedCount > 0){
					str+='<td><a class="attendanceClass" attr_cadreType="attended" attr_levelId="'+meetingLevel+'" attr_partyMeetingId="'+result[i].id+'" attr_meeting_group_id="0" style="cursor:pointer;">'+result[i].subList[j].inviteeAttendedCount+'</a></td>';
					totalInviteeAttndedCount = parseInt(totalInviteeAttndedCount)+parseInt(result[i].subList[j].inviteeAttendedCount);
				}
				else
					str+='<td> - </td>';
				if(result[i].subList[j].absentCount > 0){
					str+='<td><a class="attendanceClass" attr_cadreType="absent" attr_levelId="'+meetingLevel+'" attr_partyMeetingId="'+result[i].id+'" attr_meeting_group_id="0" style="cursor:pointer;">'+result[i].subList[j].absentCount+'</a></td>';
					totalAbscentCount = parseInt(totalAbscentCount)+parseInt(result[i].subList[j].absentCount);
				}
				else
					str+='<td> - </td>';
				if(result[i].subList[j].nonInviteeCount > 0){
					str+='<td><a class="attendanceClass" attr_cadreType="nonInvitees" attr_levelId="'+meetingLevel+'" attr_partyMeetingId="'+result[i].id+'" attr_meeting_group_id="0" style="cursor:pointer;">'+result[i].subList[j].nonInviteeCount+'</a></td>';
					totalNonInviteeCount = parseInt(totalNonInviteeCount)+parseInt(result[i].subList[j].nonInviteeCount);
				}
				else
					str+='<td> - </td>';
				  //str+='<td>'+result[i].subList[j].imagesCount+'</td>';
				  //str+='<td>'+result[i].subList[j].imagesCovered+'</td>';
			  }
		  }
		  }
		  
		   str+='<td>';
			if(result[i].subList[j].imagesCount > 0){  
					str+='<ul class="list-inline modalImagesUl">';
			if(result[i].subList[j].imagesCount > 1){
				var remaingImagePath = result[i].subList[j].imagesCount-2;
				var time = result[i].imagesList[0].uploadedTime;
					str+='<li>';
						str+='<img src="https://www.mytdp.com/party_meetings/'+result[i].imagesList[0].imagePath+'" alt=""/>';
						str+='<p style="font-size:10px">'+time+'</p>';
						str+='<p style="font-size:10px">'+result[i].imagesList[0].upLoadedDate+'</p>';
					str+='</li>';
					var time1 = result[i].imagesList[1].uploadedTime;
					str+='<li>';
					   str+='<img src="https://www.mytdp.com/party_meetings/'+result[i].imagesList[1].imagePath+'" alt=""/>';
						str+='<p style="font-size:10px">'+time1+'</p>';
						str+='<p style="font-size:10px">'+result[i].imagesList[1].upLoadedDate+'</p>';
					str+='</li>';
					str+='<li  class="getModalImagesCls" attr_Meeting_level_id="'+levelId+'" attr_Meeting_id="'+result[i].subList[j].id+'" attr_location_value="0" attr_count="1" style="cursor:pointer;" >';
					if(remaingImagePath>result[i].subList[j].imagesCount)
						str+='<p style="font-size:10px">'+remaingImagePath+'+'+'</p>';
					 str+='<p style="font-size:10px">View All</p>';
					str+='</li>';
				}else{
					str+='<li>';
						str+='<img src="https://www.mytdp.com/party_meetings/'+result[i].imagesList[0].imagePath+'" alt=""/>';
						str+='<p>'+result[i].imagesList[0].uploadedTime+'</p>';
						str+='<p>'+result[i].imagesList[0].upLoadedDate+'</p>';
					str+='</li>';
					str+='<li  class="getModalImagesCls" attr_count="1" attr_Meeting_level_id="'+levelId+'" attr_Meeting_id="'+result[i].subList[j].id+'" attr_location_value="0" style="cursor:pointer;">';
					//str+='<p  class="getModalImagesCls" attr_Meeting_level_id="'+levelId+'" attr_Meeting_id="'+result[i].subList[j].id+'" style="cursor:pointer;"> 1 </p>';
					 str+='<p >View All</p>';
					str+='</li>';
				}
					str+='</ul>';
				}
			else{
					str+='-';
			}
			str+='</td>'; 
			
		  if(result[i].remarks != null && result[i].remarks.length > 0){
			 str+='<td>'+result[i].remarks+'</td>';       
		  }else{
			str+='<td> - </td>';  
		  }
		str+='</tr>';
		}
		 str+='</tbody>';
		 str+='</table>';
		 str+='</div>';
		//$("#"+divId).html(str);
		if(statusType == "directRslt"){
		var str1='';
		str1+='<div style="margin-bottom: 20px;">';
			str1+='<h4>OVERALL ATTENDANCE SUMMARY</h4>';
			str1+='<table style="background-color:#EDEEF0;border:1px solid #ddd" class="table table-bordered">';
				str1+='<tr>';
					str1+='<td><h4 class="desigSummaryCls" attr_search="invited" style="font-weight:bold;color:green;cursor:pointer;">'+totalInvitedCount+'</h4><p>Total Invited</p></td>';
					str1+='<td><h4 class="desigSummaryCls" attr_search="invitee-Attended" style="font-weight:bold;color:green;cursor:pointer;">'+totalInviteeAttndedCount+'</h4><p>Total Invitee Attended</p></td>';
					str1+='<td><h4 class="desigSummaryCls" attr_search="absent" style="font-weight:bold;color:green;cursor:pointer;">'+totalAbscentCount+'</h4><p>Total Absent</p></td>';
					str1+='<td><h4 class="desigSummaryCls" attr_search="non-Invitee" style="font-weight:bold;color:green;cursor:pointer;">'+totalNonInviteeCount+'</h4><p>Total Non-Invitee</p></td>';
				str1+='</tr>';
			str1+='</table>';
		str1+='</div>';
		
		$("#meetingSummaryDtlsTblId").html(str1);
		}
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
function getPublicRepAndcommitteeAttendedDtls(id,status,name,sessionId,partyMeetingId,isClickRequired){
	if(isClickRequired=="noClick"){
		return;  
	}
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
						str+='<li><img class="image-responsive thumbnail" src="http://mytdp.com/'+result.partyMeetingVOList[i].docmentsList[j]+'" style="height:400px;"></li>';
					}
				}
				str+='</ul>';
			}
		str+='</div>';
	}
	$("#"+divId).html(str);
	$(".documentSlickApply").slick({
			 slide: 'li',
			 slidesToShow: 1,
			 slidesToScroll: 1,
			 infinite: false,
			 variableWidth: true
		});
}

function getupdationDetailsConflicts(){
	$("#commentsModalId").modal('show');
	$("#commentsBlock").html('');
	var levelType = $("#levelTypeId").attr("attr_level_type");
	var status = $("#levelTypeId").attr("attr_status");
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
	var distIdsList = [];
	var constIdsList =[];
	 if( $("#distId").val() != null && $("#distId").val() != 0){
			 distIdsList.push($("#distId").val());
			 }
			   if($("#constId").val() != null && $("#constId").val() != 0){
			   constIdsList.push($("#constId").val());
			   }
	var thridPartyStatus =$("#thirdPartyStatusId").val();
	$("#mdlHeadingId").html("<span>"+levelType+ " wise Party Meeting Conflicts");
	
	var jsObj ={ 
				 levelId : locationLevel,
				 startDate : customStartDateMeetings,   
				 endDate : customEndDateMeetings,
				 status : thridPartyStatus,
				 distIdsList :distIdsList,
				 constIdsList :  constIdsList,
				 locationType : "district",
				 thridPartyStatus : thridPartyStatus
			  };
			  $('#thirdPartyStatusId').val(thridPartyStatus);
	$.ajax({
		type : 'POST',
		url : 'getUpdationDetailsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0)
		   buildCommentsMeetingDetailsAction1(result);
		else
			$("#commentsBlock").html("No Data Available.");
	});
}
	
function buildCommentsMeetingDetailsAction1(result){
	var str = '';
	str+='<div class="table-responsive">';
	str +='<table class="table bg_ED " id="partMeetingModalData">'; 
	str +='<thead>';
	str +='<th style="text-transform: uppercase;"> District Name</th>';
	str +='<th style="text-transform: uppercase;"> Constituency Name</th>';
	str +='<th style="text-transform: uppercase;"> PartyMeeting Name</th>';
	str +='<th style="text-transform: uppercase;"> updated By</th>';
	str +='<th style="text-transform: uppercase;"> Evidence</th>';
	str +='</thead>';
	str +='<tbody>';
	for(var i in result){		
			str+='<tr>';
		if(result[i].distName != null){
			str+='<td style="text-transform: uppercase;">'+result[i].distName+'</td>';
		}else{
			str+='<td>-</td>';
		}
		if(result[i].constNmae != null){
			str+='<td style="text-transform: uppercase;">'+result[i].constNmae+'</td>';
		
		}else{
			str+='<td>-</td>';
		}
		if(result[i].partyMeetingName != null){
			str+='<td style="text-transform: uppercase;">'+result[i].partyMeetingName+'</td>';
		}else{
			str+='<td>-</td>';
		}
		if(result[i].name != null){
			str+='<td style="text-transform: uppercase;">'+result[i].name+'</td>';
		}else{
			str+='<td>-</td>';
		}
		 str+='<td>';
		 if(result[i].docmentsList.length > 0){
			 str+='<ul class="list-inline modalImagesUl">';
			if(result[i].docmentsList.length  > 2){
			var extension = getExtension(''+result[i].docmentsList[0]+'');
			if(extension =='pdf'){
			 str+='<li>';
	         //str+='<img src="https://www.mytdp.com/'+result[i].docmentsList[0]+'"/>';
			 str+='<iframe src="http://mytdp.com/'+result[i].docmentsList[0]+'"></iframe>';
			 str+='</li>'; 
			}else{
				 str+='<li>';
				 str+='<img src="https://www.mytdp.com/'+result[i].docmentsList[0]+'"/>';
			     str+='</li>';
			}
			 str+='<li>';
             str+='<img src="https://www.mytdp.com/'+result[i].docmentsList[1]+'"/>';
			 str+='</li>';
			 
			}else{
			 var extension = getExtension(''+result[i].docmentsList[0]+'');
				if(extension =='pdf'){
				 str+='<li>';
				 //str+='<img src="https://www.mytdp.com/'+result[i].docmentsList[0]+'"/>';
				 str+='<iframe src="http://mytdp.com/'+result[i].docmentsList[0]+'" style="width: 100px;height: 80px;"></iframe>';
				 str+='</li>';
				}else{
					 str+='<li modalImagesUl>';
					 str+='<img src="https://www.mytdp.com/'+result[i].docmentsList[0]+'"/>';
					 str+='</li>';
				}
			
			}
			str+='<li>';
			str+='<p class="meetingsConflictsCls1" attr_meeting_id="'+result[i].partyMeetingId+'" attr_div_id="meetingDetailsDivId'+i+'" style="cursor: pointer;">View All</p>';
			str+='</li>';
			
			str+='</ul>'; 
		 }else{
			str+='Not Conducted';
		}
		str+='</td>';
		str+='</tr>';
		
		}
    str +='</tbody>';
	str +='</table>';
	str +='</div>';
	$("#commentsBlock").html(str); 
	$("#partMeetingModalData").dataTable();
	//$("#partMeetingModalData").removeClass("dataTable ");
	
}  
	
getConstituenciesForState();
function getConstituenciesForState(){
	
	var jsObj={			
		stateId :globalStateId
	}
	$.ajax({   
		type:'GET',
		url:'getConstituenciesForStateAjaxAction.action',  
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		 if(result != null){
			$('#constId').append('<option value="0">All</option>');
			for(var i in result){
				if(result[i].id>0)
				$('#constId').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');		
			}			
		}
	});
}
function buildDocumentListDetails1(result){
	var str='';
	if(result !=null && result.partyMeetingVOList !=null && result.partyMeetingVOList.length>0){
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
		str+='<ul class="list-inline documentSlickApply">';
			for(var i in result.partyMeetingVOList){
				if(i==0){
					str+='<div class="arrow_box_left">';
					if(result.partyMeetingVOList[i].meetingLevel != null && result.partyMeetingVOList[i].meetingLevel.length > 0){
						str+='<span class="m_0"><strong>INFORMED BY:</strong> '+result.partyMeetingVOList[i].subName+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <strong>MOBILE NO:</strong>'+result.partyMeetingVOList[i].mobileNo+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; MEMBERSHIP NO:<strong style="color:green">'+result.partyMeetingVOList[i].meetingLevel+'</strong></br> <strong>Remarks:&nbsp;</strong> '+result.partyMeetingVOList[i].memberStatus+'</br><span class="pull-right"><strong> TIME:</strong> '+result.partyMeetingVOList[i].insertedTime+'</span></span>';
					}else{
						str+='<span class="m_0"><strong>INFORMED BY:</strong> '+result.partyMeetingVOList[i].subName+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <strong>MOBILE NO:</strong>'+result.partyMeetingVOList[i].mobileNo+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</br> <strong>Remarks:&nbsp;</strong> '+result.partyMeetingVOList[i].memberStatus+'</br><span class="pull-right"><strong> TIME:</strong> '+result.partyMeetingVOList[i].insertedTime+'</span></span>';
					}
						str+='</div>';
				}				
					
					/*if(result.partyMeetingVOList[i].docmentsList !=null && result.partyMeetingVOList[i].docmentsList.length>0){
						for(var j in result.partyMeetingVOList[i].docmentsList){
							var extension = getExtension(''+result.partyMeetingVOList[i].docmentsList[j]+'');
							str+='<li>'+buildPdsStr('http://mytdp.com/'+result.partyMeetingVOList[i].docmentsList[j]+'',extension)+'</li>';
						}
					}*/
						for(var j in result.partyMeetingVOList[i].docmentsList){
							var scanCopyExt = getExtension(''+result.partyMeetingVOList[i].docmentsList[j]+'');
							
							if(scanCopyExt =='pdf'){
								str+='<li style="height:400px;width: 900px"><iframe src="http://mytdp.com/'+result.partyMeetingVOList[i].docmentsList[j]+'" style="height: 400px;width: 900px;"></iframe>  </li>';
							} else if( scanCopyExt =="jpeg" || scanCopyExt =="jpg"  || scanCopyExt =="gif"  || scanCopyExt =="bmp"  || scanCopyExt =="png"){
								str+='<li><img class="image-responsive thumbnail" src="http://mytdp.com/'+result.partyMeetingVOList[i].docmentsList[j]+'" style="height:400px;width: 900px"></li>';
							}
							else{
								str+='<b>Click <a href="javascript:{};" onclick="window.open(\'http://mytdp.com/'+result.partyMeetingVOList[i].docmentsList[j]+'\')">Here</a> To View Document</b>';
							}
								
						}
						
					
			}
			str+='</ul>';
		str+='</div>';
	}
	$("#popupImages").html(str);
	$(".documentSlickApply").slick({
			 slide: 'li',
			 slidesToShow: 1,
			 slidesToScroll: 1,
			 infinite: false,
			 variableWidth: true
		});
}

function buildPdsStr(path,scanCopyExt){
	var pdfStr='';
	if(scanCopyExt =="pdf"){
		pdfStr+='<object data="'+path+'\" type="application/pdf" width="100%" height="300px;"></object>';
		
	}else if( scanCopyExt =="jpeg" || scanCopyExt =="jpg"  || scanCopyExt =="gif"  || scanCopyExt =="bmp"  || scanCopyExt =="png"){
		pdfStr+='<img src="'+path+'\" width="100%" height="300px;"/>';
	}else{
		pdfStr+='<b>Click <a href="javascript:{};" onclick="openDoc(\''+path+'\')">Here</a> To View Document</b>';
	}
	return pdfStr;
}

function getExtension(path) {
    var basename = path.split(/[\\/]/).pop(),  // extract file name from full path ...
                                               // (supports `\\` and `/` separators)
        pos = basename.lastIndexOf(".");       // get last position of `.`

    if (basename === "" || pos < 1)            // if file name is empty or ...
        return "";                             //  `.` not found (-1) or comes first (0)

    return basename.slice(pos + 1);            // extract extension ignoring `.`
}

function onchangeFunction(){
	 $("#commentsBlock").html('');
}
getDistrictsForState();
function getDistrictsForState(){
	var jsObj={
		stateId :globalStateId
	}
	$.ajax({   
		type:'GET',
		url:'getDistrictsListForStateAction.action',  
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0){
			$('#distId').append('<option value="0">All</option>');
			for(var i in result){
				if(result[i].id > 0)
				$('#distId').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
				//distIdArr.push(result[i].id);					
			}			
		}
	});
}

function getConstituenciesForDistricts(distId){
	 $("#commentsBlock").html('');
	var distId  =$("#distId").val();
	if(distId == 0)
		getConstituenciesForState();
	$('#constId').empty();
		var jsObj={
			
			districtId :distId
		}
		$.ajax({   
			type:'GET',
			url:'getConstituenciesForADistrictAjaxAction.action',  
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			 if(result != null){
				$('#constId').append('<option value="0">All</option>');
				for(var i in result){
					if(result[i].id>0){
					$('#constId').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');	
					}					
				}			
			}
		});
	}
	var globalCount = 0;
$(document).on("click",".attendanceClass",function(){
	globalCount = 1;
	var cadreType = $(this).attr("attr_cadreType");
	//var sessionId = $(this).attr("attr_sessionId");
	//var levelId = $(this).attr("attr_levelId");
	var meetingGrpId = $(this).attr("attr_meeting_group_id");
	//var partyMeetingMainTypeId = $(this).attr("attr_meeting_mainType_id");
	var partyMeetingId = $(this).attr("attr_partyMeetingId");
	//alert(levelId);
	getAttendedCadreDetails(cadreType,0,2,meetingGrpId,0,1,partyMeetingId);
});

$(document).on('click','.closeBtnCls',function(){  
	if(globalCount > 0){
		setTimeout(function(){
			$('body').addClass("modal-open");
		}, 500);
	}
});
var globalImgsMdlCnt = 0;
$(document).on('click','.imageCloseBtnCls',function(){ 
	if(globalImgsMdlCnt > 0){
		setTimeout(function(){
			$('body').addClass("modal-open");
		}, 500);
	}
});

$(document).on("click",".getModalImagesCls",function(){
	$("#myModalImageId").modal("show");
	var location = $(this).attr('attr_location');
	if(location != null && location != 'undefined')
		$("#myModalLabelId").html(location+" "+"IMAGE DETAILS");
	else
		$("#myModalLabelId").html("IMAGE DETAILS");
	globalImgsMdlCnt = $(this).attr("attr_count");
	var levelId = $(this).attr('attr_Meeting_level_id');
	var meetingId = $(this).attr('attr_Meeting_id');
	var locationValue = $(this).attr("attr_location_value");
	getDistrictsForCustomMeetingImges(levelId,meetingId,locationValue);
	var str='';
		str+='<div class="row">';
			str+='<div class="col-md-9">';
				str+='<nav class="navbar navbar-default navbarCollapseCustom">';
					str+='<div class="collapse navbar-collapse " id="bs-example-navbar-collapse-1">';
					  str+='<ul class="nav navbar-nav" id="popupDaysDiv">';
					 
					  str+='</ul>';
					str+='</div>';
				str+='</nav>';
				str+='<div class="pad_10" id="popupImages">';
				
				str+='</div>';
				str+=' <div id="paginationDivId"></div>';
			str+='</div>';
			str+='<div class="col-md-3" style="box-shadow:0 2px 10px 0 rgba(0, 0, 0, 0.35);padding:0px">';
				str+='<div id="districtsUlId"></div>';
			str+='</div>';
		str+='</div>';

	$("#buildPoupupImage").html(str);
	getEventDocumentForPopupForMultiLocation(levelId,meetingId,0,locationValue);
	getDistrictsForCustomMeetingImges(levelId,meetingId,locationValue);	
});

function getDistrictsForCustomMeetingImges(levelId,meetingId,locationValue){
	var meetingLevelId = levelId;
	var meetingId = meetingId;
	var jObj = {
		partyMeetingLevelId:meetingLevelId,
		activityMemberId : globalActivityMemberId,
	    stateId : globalStateId,
		fromDate : '01/01/2017',
		toDate : '01/02/2018',
		meetingId:meetingId,
		meetingGrpId :0,
		locationValue : locationValue
	};
	
	$.ajax({
	  type:'GET',
	  url: 'getDistrictsForCustomMeetingImgesAction.action',
	 data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		if(result != null && result.length > 0){
			buildDistrictNamesForCustomMeetings(result,meetingLevelId,meetingId);
		}
	});
}
function buildDistrictNamesForCustomMeetings(result,meetingLevelId,meetingId){
	var str='';
	str+='<div class="panel-group" id="accordionModalMeeting" role="tablist" aria-multiselectable="true">';
	for(var i in result)
	{
	  str+='<div class="panel panel-default panel-custommodal">';
		str+='<div class="panel-heading panel-headingModal" role="tab" id="headingModalMeetings'+i+'">';
			str+='<a role="button" class="constituencyClsMeetings accordionmodal-toggle collapsed" data-toggle="collapse" data-parent="#accordionModalMeeting" attr_distId="'+result[i].districtId+'" href="#collapseModalMeetings'+i+'" aria-expanded="true" attr_level_id="'+meetingLevelId+'" attr_meeting_id ="'+meetingId+'" aria-controls="collapseModalMeetings'+i+'">';
				str+='<h4 class="panel-title">'+result[i].name+'('+result[i].count+')</h4>';
			  str+='</a>';
		str+='</div>';
		str+='<div id="collapseModalMeetings'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingModalMeetings'+i+'">';
		  str+='<div class="panel-body pad_0">';
			str+='<div id="constituenciesBlockMeeting'+result[i].districtId+'"></div>';
		  str+='</div>';
		str+='</div>';
	  str+='</div>';
	}
	str+='</div>';
	$("#districtsUlId").html(str);
}

function getEventDocumentForPopupForMultiLocation(levelId,meetingId,num,locationValue){
	 $("#popupImages").html('<img src="./images/Loading-data.gif" />');
	 var dates=$('.searchDateCls ').val();

		var jObj = {
			fromDateStr : '01/01/2017',
		    toDateStr : '01/02/2018',
			activityMemberId : globalActivityMemberId,
			stateId : globalStateId,
			partyMeetingLevelId:levelId,
			startIndex:num,
			maxIndex:10,
			meetingGrpId :0,
			meetingId :meetingId,
			locationValue : locationValue
		};
		
		$.ajax({
          type:'GET',
          url: 'getCustomWisePartyMeetingDocumentsAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			buildDayWisImagesForPopup1ForMultiLocation(result,jObj,locationValue);
		});
}
function buildDayWisImagesForPopup1ForMultiLocation(result,jObj,locationValue){
	
	//$("#buildPoupupImage").html('');
	var str ='';
	$('.slider-for,.slider-nav').slick('unslick');
	if(result != null)
	{
	
	str+='<div class="row">';	
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';	
		str+='<ul class="slider-forparty_meeting slider-for">';
		//if(path != null && path.length>0)
			//str+='<li><img src="https://mytdp.com/DocFiles/' +path+'"></li>';
			for(var i in result)
			{
				/* for(var j in result[i])
				{ */
					
					str+='<li><img src="https://mytdp.com/party_meetings/' +result[i].path+'" style="min-width: 100px;min-height:300px; margin-left: 12px;"></li>';
					
				//str+='<li><img src="https://www.mytdp.com/party_meetings/ea18acce-756a-4fc2-aec9-6d8090a27dcd_Tulips.jpg" style="min-width: 100px;min-height:300px; margin-left: 12px;"></li>';
				//}
			}
			  str+='</ul>';
		str+='<ul class="slider-nav m_top20">';	
		//if(path != null && path.length>0)
			//str+='<li><img src="https://mytdp.com/DocFiles/' +path+'" style="cursor:pointer;"></li>';
		for(var i in result)
		{	 
			//for(var j in result[i])
		//	{
				str+='<li><img src="https://mytdp.com/party_meetings/' +result[i].path+'" style="cursor:pointer;"/></li>';	
				//str+='<li><img src="https://www.mytdp.com/party_meetings/ea18acce-756a-4fc2-aec9-6d8090a27dcd_Tulips.jpg"style="cursor:pointer;" ></li>';
				
			//}
		}
				str+='</ul>';
			str+='</div>';	
		str+='</div>';	
			$("#popupImages").html(str);
			
			setTimeout(function(){		
			$('.slider-for').slick({
			  slidesToShow: 1,
			  slidesToScroll: 1,
			  slide: 'li',
			  arrows: false,
			  fade: true,
			  asNavFor: '.slider-nav'
			});
			$('.slider-nav').slick({
			  slidesToShow: 11,
			  slidesToScroll: 0,
			  slide: 'li',
			  asNavFor: '.slider-for',
			  dots: false,
			 // centerMode: true,
			focusOnSelect: true,
			  variableWidth: true

				})
			$(".slick-list").css("margin-left","17px;");	
			$(".slick-list").css("margin-right","17px;");	
			//$('.slider-nav li:first-child').trigger('click');
			//$('.slider-nav li:first-child').trigger('click');
		},300);
		
			var itemsCount=result[0].totalCount;
			
	    var maxResults=jObj.maxIndex;
	   if(jObj.startIndex==0){
		   $("#paginationDivId").html('');
		   $("#paginationDivId").pagination({
				items: itemsCount,
				itemsOnPage: maxResults,
				cssStyle: 'light-theme',
				
				onPageClick: function(pageNumber, event) {
					var num=(pageNumber-1)*10;
					 getEventDocumentForPopupForMultiLocation(jObj.levelId,jObj.meetingId,num,locationValue); 
				}
			});
			$("#paginationDivId").find("ul").addClass("pagination");
		}
		
	GlobalPopupScope = globallocationScope;
	GlobalPopuplocation =globallocationValue;
	
	}
}

 function getPartyMeetingComulativeCommentDetails(meetingStatus,meetingLevel,isComment,customStartDateMeetings,customEndDateMeetings,reportType,locationId,locationType){
	   $("#meetingSummaryDtlsTblId").html('');
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
	 var levelId = 1;
	   str+='<table style="background-color:#EDEEF0;border:1px solid #ddd" class="table table-condensed table-bordered" id="comulativeCommentTblId">';
		 str+='<thead>';
		    if(reportType=="District"){
				 str+='<th>District Name</th>';
			}else if(reportType=="Constituency"){
				 str+='<th>Constituency Name</th>';
			}
            str+='<th style="text-align:center;">Meeting Count</th>';
			if(reportType=="District"){
				levelId = 2;
				  str+='<th style="text-align:center;"> Total Invitees </th>';
				  str+='<th style="text-align:center;"> Invitee Attended </th>';
				  str+='<th style="text-align:center;"> Absent </th>';
				  str+='<th style="text-align:center;"> Non-Invitee Attended </th>';
			}
			 str+='<th style="text-align:center;">Images</th>';
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
				str+='<td attr_comment="No" attr_location_id="'+result[i].id+'" attr_meeting_status='+meetingStatus+' attr_level_type='+meetingLevel+' attr_location_type='+reportType+' style="cursor: pointer;text-align:center;color:green;font-weight:bold;" class="commentDetailsCls"> '+result[i].meetingCount+'</td>';  
			  }else{
			  str+='<td> - </td>';  
			  }
			  if(reportType=="District"){
				  str+='<td>'+result[i].invitedCount+'</td>';  
				  str+='<td>'+((result[i].invitedCount) - (result[i].absentCount))+'</td>';  
				  str+='<td>'+result[i].absentCount+'</td>';  
				  str+='<td>'+result[i].nonInviteesCount+'</td>';  
			  }
			  
			  str+='<td>';
				if(result[i].imagesCount > 0){  
						str+='<ul class="list-inline modalImagesUl">';
				if(result[i].imagesCount > 1){
					var remaingImagePath = result[i].imagesCount-2;
					var time = result[i].imagesList[0].uploadedTime;
						str+='<li>';
							str+='<img src="https://www.mytdp.com/party_meetings/'+result[i].imagesList[0].imagePath+'" alt=""/>';
							str+='<p style="font-size:10px">'+time+'</p>';
							str+='<p style="font-size:10px">'+result[i].imagesList[0].upLoadedDate+'</p>';
						str+='</li>';
						var time1 = result[i].imagesList[1].uploadedTime;
						str+='<li>';
						   str+='<img src="https://www.mytdp.com/party_meetings/'+result[i].imagesList[1].imagePath+'" alt=""/>';
							str+='<p style="font-size:10px">'+time1+'</p>';
							str+='<p style="font-size:10px">'+result[i].imagesList[1].upLoadedDate+'</p>';
						str+='</li>';
						str+='<li  class="getModalImagesCls" attr_Meeting_level_id="'+levelId+'" attr_Meeting_id="0" attr_location_value="'+result[i].id+'" attr_count="1" style="cursor:pointer;" >';
						if(remaingImagePath>result[i].imagesCount)
							str+='<p style="font-size:10px">'+remaingImagePath+'+'+'</p>';
						 str+='<p style="font-size:10px">View All</p>';
						str+='</li>';
					}else{
						str+='<li>';
							str+='<img src="https://www.mytdp.com/party_meetings/'+result[i].imagesList[0].imagePath+'" alt=""/>';
							str+='<p>'+result[i].imagesList[0].uploadedTime+'</p>';
							str+='<p>'+result[i].imagesList[0].upLoadedDate+'</p>';
						str+='</li>';
						str+='<li  class="getModalImagesCls" attr_Meeting_level_id="'+levelId+'" attr_Meeting_id="0"  attr_location_value="'+result[i].id+'" attr_count="1" style="cursor:pointer;">';
						//str+='<p  class="getModalImagesCls" attr_Meeting_level_id="'+levelId+'" attr_Meeting_id="'+result[i].subList[j].id+'" style="cursor:pointer;"> 1 </p>';
						 str+='<p >View All</p>';
						str+='</li>';
					}
						str+='</ul>';
					}
				else{
						str+='-';
				}
				str+='</td>'; 
			  
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
