 var globalStateId=1; //default AP
 var globalUserWiseEventMemberRslt;
 /* EVENT FUNCTIONALITY START*/  
/*  $(document).on("click",".eventsIconExpand",function(){
	$(".dateRangePickerClsForEvents").toggleClass("hide");
	$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
	$(".eventYearExpandIcon").find("i").addClass("glyphicon-fullscreen").removeClass("glyphicon-resize-small");
	$(".eventsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	$(".eventsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
	if($(this).find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$("#eventsCmpBlckDivId").find("ul li").attr("attr_type","events");
		var eventIdsString="7,30";
		
		$(".moreEventsBlocksIcon").attr("attr_type","event");
	    $(".moreEventsBlocksIcon").attr("attr_event_idsString",eventIdsString);
		$(".detailedEvent").attr("attr_type","event");
	    $(".detailedEvent").attr("attr_event_idsString",eventIdsString);
		$(".comparisonEvent").attr("attr_type","event");
		$(".comparisonEvent").attr("attr_event_idsString",eventIdsString);
		
		getUserTypeWiseTotalInviteeAndInviteeAttendedCnt(eventIdsString);
		$(".eventsHiddenBlock,.moreEventsBlocksIcon").show();
		setTimeout(function(){
			$('html,body').animate({
				scrollTop: $(".eventsBlock").offset().top},
			'slow');
		},500);
	}else{
		$("#activtyBlckDivId").hide();
		$(".activitesExpandIcon").find("i").addClass("glyphicon-fullscreen").removeClass("glyphicon-resize-small");
		$(".eventsHiddenBlock,.moreEventsBlocks,.comparisonBlockEvents").hide();
	}
	if( $(".trainingIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".dateRangePickerClsForTraining").addClass("hide");
		$(".trainingIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".trainingsHiddenBlock,.moreTrainingBlocks,.moreTrainingBlocksIcon").hide();
		$(".moreTrainingBlocksIcon").removeClass("unExpandTrainingBlock");
		$(".trainingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}else if( $(".debatesIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".debatesIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".debatesMoreHiddenBlock,.debatesHiddenBlock,.dateRangePickerClsForDebates").hide();
		$(".moreDebatesBlocksIcon").removeClass("unExpandDebatesBlock");
		$(".debatesBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}else if( $(".iconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".iconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".committeesHiddenBlock,.moreBlocks,.moreBlocks1,.moreBlocksDetailAndComp,.moreBlocksIcon,.moreBlocksDistrictlevel").hide();
		$(".committeesBlock,.basicCommitteesBlock,.userTypeCommitteesBlock,.committeesBlock1").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".dateRangePickerCls").toggleClass("hide");
		$(".moreBlocksIcon").removeClass("unExpandBlock");
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
	
});*/
$(document).on("click",".activitiesCollapseBtn",function(e){
	if($(this).html() == '+')
	{
		$(this).html("-")
	}else{
		$(this).html("+")
	}
	$(".activitiesCollapseBody").toggle();
});
$(document).on("click",".moreEventsBlocksIcon",function(){
	//$("#eventsCmpBlckDivId").find("ul li").attr("attr_type","events")	
	$("#eventsCmpBlckDivId").find("ul li:first-child").addClass("active");
	var type=$(this).attr("attr_type");
	var attrEventIdsString=$(this).attr("attr_event_idsString");
	$("#eventsCmpBlckDivId").find("ul li").attr("attr_event_idsString",attrEventIdsString)
	if(type != null && type=="event"){
	$("#activitesCmpBlockDivId").hide();
	$("#activtyBlckDivId").hide();
	$(".moreEventsBlocks").toggle();
	$(".activeUlCls").show();
	 getLocationWiseByInviteeAttendedAndInviteeAttendedCntBasedOnUserType(attrEventIdsString);
	 getSelectedEventDetails(attrEventIdsString);
	 $(".detailedBlockEvents,.activeUlCls").show();
	 $(".detailedEvent").addClass("active")	
	 $(".comparisonEvent").removeClass("active")
	}else{
		$("#activitesCmpBlockDivId").hide();
		// activity functionality		
	}
});
$(document).on("click","#eventsCmpBlckDivId ul li",function(){
	var typeId = $(this).attr("attr_typeId");
	var type = $(this).attr("attr_type");
	if(type == 'events')
	{
		if(typeId == 1)
		{
			$(".detailedBlockEvents").show();
			$(".comparisonBlockEvents,.comparisonBlockActivities").hide();
			//var type=$(this).attr("attr_type");
			var attrEventIdsString=$(this).attr("attr_event_idsString");
			getLocationWiseByInviteeAttendedAndInviteeAttendedCntBasedOnUserType(attrEventIdsString);
			getSelectedEventDetails(attrEventIdsString);	
		}else if(typeId == 2)
		{
			$(".comparisonBlockEvents").show();
			$(".detailedBlockEvents").hide();
			//var type=$(this).attr("attr_type");
			var attrEventIdsString=$(this).attr("attr_event_idsString");
			getAllItsSubUserTypeIdsByParentUserTypeIdForEvent(attrEventIdsString,"events",globalUserTypeId);
		}
	}else if(type == 'activities')
	{
		if(typeId == 1)
		{
			//$("#eventsCmpBlckDivId").find("ul li").attr("attr_type","events")	
			$("#eventsCmpBlckDivId").find("ul li:first-child").addClass("active");
			$("#eventsCmpBlckDivId").find("ul li:nth-child(2)").removeClass("active");
			var type=$(this).attr("attr_type");
			var attrEventIdsString=$(this).attr("attr_event_idsString");
			$("#eventsCmpBlckDivId").find("ul li").attr("attr_event_idsString",attrEventIdsString)	
			 		$("#activitesCmpBlockDivId").hide();	
				 	$(".moreActivitiesBlocks").toggle();
					var activityId = attrEventIdsString;
					$(".moreActivitiesBlocks").toggle();
					stateWiseCohort(activityId); //srujana
					districtWiseCohort(activityId);
					levelWiseSBData(activityId);
					//activitiesQuestions(activityId);
					$(".detailedBlockEvents,.activeUlCls").show();
					$(".detailedEvent").addClass("active")	
					$(".comparisonActivity").removeClass("active");
					$("#evntCmpBLockId,.comparisonBlockActivities").hide();
						
		}else if(typeId == 2){
			$("#childEvnetMemberDivId").html(' ');
			$("#directChildMemberForEventDivId").html(' ');
			$("#evntCmpBLockId").hide();
			$(".comparisonBlockActivities").show();
			$(".detailedBlockEvents").hide();
			var searchType = $(this).attr("attr_search_type");
			var attrEventIdsString=$(this).attr("attr_event_idsString");
			var attrActivityIdsString = attrEventIdsString;
			getAllItsSubUserTypeIdsByParentUserTypeIdForActivity(attrActivityIdsString,"activities",globalUserTypeId);			
			//getSelectedChildTypeMembersForEvent("",attrEventIdsString,globalUserTypeId,searchType);
			
		}
	}
});
$(document).on("click",".activitesExpandIcon",function(){
	
	$("#eventsDistWiseCohort,#eventsGraphBlock").html(' ');
	$(".eventsListExpandIcon").find("i").addClass("glyphicon-fullscreen").removeClass(".glyphicon-resize-small");
    var activityId = $(this).attr("attr_id");
	$("#hiddenActivityId").val(activityId);	
	if($(this).find("i").hasClass("glyphicon-fullscreen"))
	{
		if($(".eventsIconExpand").find("i").hasClass("glyphicon-resize-small"))
		{
			if(activityId == 37){
				$("#levelWiseOverviewId").show();
			}else{
				$("#levelWiseOverviewId").hide();
			}
		}else{
			if(activityId == 37){
				$("#levelWiseOverviewId").show();
			}else{
				$("#levelWiseOverviewId").hide();
			}
			//block closed
			$(".eventsIconExpand").find("i").addClass("glyphicon-resize-small").removeClass("glyphicon-fullscreen");
			$(".eventsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".eventsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
			
		}
		var searchType = $(this).attr("attr_search_type");
		$("#eventsCmpBlckDivId").find("ul li").attr("attr_type","activities");
		$("#eventsCmpBlckDivId ul li:nth-child(2)").attr("attr_search_type",searchType);
		$("#eventsCmpBlckDivId ul li:nth-child(2)").attr("attr_event_idsstring",activityId);
		var activityLevelIds=[];
		var activityId = $(this).attr("attr_id");
		var activityName = $(this).attr("attr_activity_name");
		var activityLevelId = $(this).attr("attr_level_id");
		 if(activityLevelId != null && activityLevelId > 0){
			activityLevelIds.push(activityLevelId); 
		 }
		 getUserTypeActivityConductedCnt(activityId,activityLevelIds);
		 activityName = activityName.replace("'","");
		 activityName = activityName.replace("\'","");
		 $(".eventAndActivityCls").html(activityName);
		$(".moreEventsBlocksIcon").addClass("acitivitiesMoreExpand");
		$(".dateRangePickerClsForEvents").removeClass("hide");
		$(".eventsHiddenBlock,.moreEventsBlocksIcon").show();
		var eventIdsString = $(this).attr("attr_event_idsString");
		$(".activitesExpandIcon").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen");
		$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		if($(".detailedBlockEvents").is(":visible"))
		{
			//alert("already opened");eventsCmpBlckDivId
			var activityId = $("#hiddenActivityId").val();
			if(activityId==37){
				levelWiseSBData(activityId);//sanjeev
			}
			stateWiseCohort(activityId);
			districtWiseCohort(activityId);
		}
		if($(".comparisonBlockActivities").is(":visible")){
			//alert('activities comaprison');
			$('#eventsCmpBlckDivId ul li').trigger('click');
		}
		if($(".detailedBlockEvents").is(":visible")){
			//alert('events comaprison');
		}
		
			$(".acitivitiesMoreExpand").removeAttr("attr_type");
			$(".acitivitiesMoreExpand").removeAttr("attr_event_idsString");
			$(".acitivitiesMoreExpand").attr("attr_type","activity");
			$(".acitivitiesMoreExpand").attr("attr_event_idsString",activityId);
	}else{
		//alert("close")
		$("#activtyBlckDivId").hide();
		$(".eventsHiddenBlock,.moreEventsBlocksIcon").hide();
		$(".dateRangePickerClsForEvents").addClass("hide");
		$(".eventsHiddenBlock,.moreEventsBlocksIcon,.comparisonBlockEvents,.moreEventsBlocks").hide();
		$(".eventsIconExpand").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen");
		$(".eventsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".eventsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
		$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
	}
	
	if( $(".trainingIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".dateRangePickerClsForTraining").addClass("hide");
		$(".trainingIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".trainingsHiddenBlock,.moreTrainingBlocks,.moreTrainingBlocksIcon").hide();
		$(".moreTrainingBlocksIcon").removeClass("unExpandTrainingBlock");
		$(".trainingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}else if( $(".debatesIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".debatesIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".debatesMoreHiddenBlock,.debatesHiddenBlock,.dateRangePickerClsForDebates").hide();
		$(".moreDebatesBlocksIcon").removeClass("unExpandDebatesBlock");
		$(".debatesBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}else if( $(".iconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".iconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".committeesHiddenBlock,.moreBlocks,.moreBlocks1,.moreBlocksDetailAndComp,.moreBlocksIcon,.moreBlocksDistrictlevel").hide();
		$(".committeesBlock,.basicCommitteesBlock,.userTypeCommitteesBlock,.committeesBlock1").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".dateRangePickerCls").toggleClass("hide");
		$(".moreBlocksIcon").removeClass("unExpandBlock");
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
});
/* $(document).on("click",".detailedEvent",function(){
	$(".detailedBlockEvents").show();
	$(".comparisonBlockEvents").hide();
	//var type=$(this).attr("attr_type");
	var attrEventIdsString=$(this).attr("attr_event_idsString");
	getLocationWiseByInviteeAttendedAndInviteeAttendedCntBasedOnUserType(attrEventIdsString);
	getSelectedEventDetails(attrEventIdsString);	
});
$(document).on("click",".comparisonEvent",function(){
	$(".comparisonBlockEvents").show();
	$(".detailedBlockEvents").hide();
	//var type=$(this).attr("attr_type");
	var attrEventIdsString=$(this).attr("attr_event_idsString");
	getAllItsSubUserTypeIdsByParentUserTypeIdForEvent(attrEventIdsString,"events");
});
 */
$(document).on("click",".eventStrngPrCls",function(){
	var selectedType = $(this).attr("attr_selected_type");
	var memberType=$(this).attr("attr_value");
	if(selectedType != null && selectedType!= undefined && selectedType=="Event"){
	 if(memberType != null && memberType == "strong"){ 
		buildUserTypeWiseTotalInviteeAndInviteeAttendedCnt(globalUserWiseEventMemberRslt); 
	 }else if(memberType == "poor"){
	  buildUserTypeWisePoorInviteeAttendedEventMemDtlsCnt(globalUserWiseEventMemberRslt);
	 }	
	}else if(selectedType != null && selectedType!= undefined && selectedType=="Activity"){
	 if(memberType != null && memberType == "strong"){ 
		buildUserTypeWiseTopFiveActivityConductedRlst(globalUserWiseConductedRslt); 
	 }else if(memberType == "poor"){
	  buildUserTypeWisePoorFiveActivityConductedRlst(globalUserWiseConductedRslt);
	 }	
	}
});

$(document).on("click",".eventsListExpandIcon",function(){
	$("#eventsCmpBlckDivId").find("ul li").attr("attr_type","events");	
	$("#eventsCmpBlckDivId").find("ul li:nth-child(2)").removeClass("active")	
	$(".moreEventsBlocksIcon").removeClass("acitivitiesMoreExpand");
	$("#eventsDistWiseCohort1,#eventsGraphBlock1").html(' ');
	$(".activitiesH4").html("Cohort")
	$(".activitesExpandIcon").find("i").addClass("glyphicon-fullscreen").removeClass(".glyphicon-resize-small");
	var eventIdsString = $(this).attr("attr_event_idsString");
	var eventName = $(this).attr("attr_event_name");
	$(".eventAndActivityCls").html(eventName);
	$(".moreEventsBlocksIcon").attr("attr_type","event");
	$(".moreEventsBlocksIcon").attr("attr_event_idsString",eventIdsString);
	$(".detailedEvent").attr("attr_type","event");
	$(".detailedEvent").attr("attr_event_idsString",eventIdsString);
	$(".comparisonEvent").attr("attr_type","event");
	$(".comparisonEvent").attr("attr_event_idsString",eventIdsString);
	$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
	
	$(".eventsHiddenBlock,.moreEventsBlocksIcon").show();
	$("#activtyBlckDivId").hide();
	
	if(!$(".eventsIconExpand").find("i").hasClass("glyphicon-resize-small"))
	{
		
		$(".eventsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".eventsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
	}
	$(".eventsHiddenBlock,.moreEventsBlocksIcon,.comparisonBlockEvents,.moreEventsBlocks").hide();
	
	if(!$(this).find("i").hasClass("glyphicon-resize-small"))
	{
		$(".eventsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".eventsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
		$(".eventsIconExpand").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen");
		$("#activtyBlckDivId").hide();
	}else{
		$(".eventsListExpandIcon").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen")
		$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".stateLevelMeetingBlock").show();
		$(".eventsHiddenBlock,.moreEventsBlocksIcon").show();
		$(".eventsIconExpand").find("i").addClass("glyphicon-resize-small").removeClass("glyphicon-fullscreen")
		getUserTypeWiseTotalInviteeAndInviteeAttendedCnt(eventIdsString);	
	}
	
	if( $(".trainingIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".dateRangePickerClsForTraining").addClass("hide");
		$(".trainingIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".trainingsHiddenBlock,.moreTrainingBlocks,.moreTrainingBlocksIcon").hide();
		$(".moreTrainingBlocksIcon").removeClass("unExpandTrainingBlock");
		$(".trainingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}else if( $(".debatesIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".debatesIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".debatesMoreHiddenBlock,.debatesHiddenBlock,.dateRangePickerClsForDebates").hide();
		$(".moreDebatesBlocksIcon").removeClass("unExpandDebatesBlock");
		$(".debatesBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}else if( $(".iconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".iconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".committeesHiddenBlock,.moreBlocks,.moreBlocks1,.moreBlocksDetailAndComp,.moreBlocksIcon,.moreBlocksDistrictlevel").hide();
		$(".committeesBlock,.basicCommitteesBlock,.userTypeCommitteesBlock,.committeesBlock1").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".dateRangePickerCls").toggleClass("hide");
		$(".moreBlocksIcon").removeClass("unExpandBlock");
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
});
//var customStartDateActivities = moment().subtract(2,"year").format('DD/MM/YYYY');
//var customEndDateActivities = moment().format('DD/MM/YYYY');  
$("#dateRangeIdForEvents").daterangepicker({
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
});
$('#dateRangeIdForEvents').on('apply.daterangepicker', function(ev, picker) {
  //customStartDateActivities = picker.startDate.format('DD/MM/YYYY');
  //customEndDateActivities = picker.endDate.format('DD/MM/YYYY'); 
  $("#eventsCmpBlckDivId").hide();
  $("#evntCmpBLockId").hide();
  $("#activtyBlckDivId").hide();
  $(".activeUlCls").hide();
  $(".detailedBlockEvents").hide();
	getEventBasicCntDtls();
	getActivitiesDetails();
	getUserTypeWiseTotalInviteeAndInviteeAttendedCnt("7,30");
  //getUserTypeActivityConductedCnt();
  
});

function getEventBasicCntDtls(){
	$("#mainEventsList").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	//buildEventBasicCntDtls();
	//return;
	var eventIds=[];
	eventIds.push(7);
	eventIds.push(30);
	eventIds.push(51);
	eventIds.push(58);
	var jsObj ={ 
				 activityMemberId : globalActivityMemberId,
				 stateId : globalStateId,
				 eventIds:eventIds
				 
			  }
	$.ajax({
		type : 'POST',
		url : 'getEventBasicCntDtlsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0 ){
		 buildEventBasicCntDtls(result);
		 buildActivityEventSettings(result,"events");		 
		}else{
		 $("#mainEventsList").html("NO DATA AVAILABLE.");	
		}
	});
}
function buildEventBasicCntDtls(result)
{ 
	var eventIdsString;
	var str=' ';
	//str+='<div class="panel-group m_top10" id="accordionEvents" role="tablist" aria-multiselectable="true">';
		for(var i in result)
		{
			if(i== 0){
			eventIdsString = result[i].id;	
			}else{
			 eventIdsString = eventIdsString+','+result[i].id;	
			}
			str+='<div class="panel panel-default panelNewEvents">';
				str+='<div class="panel-heading" role="tab" id="headingEvents'+i+'">';
					str+='<h4 class="panel-title">'+result[i].name+'';
						str+='<span attr_event_idsString='+result[i].id+' attr_event_name="'+result[i].name+'" class="eventsListExpandIcon eventCls" style="background-color:#fff;font-size:10px;margin-left:5px;"><i class="glyphicon glyphicon-fullscreen"></i></span>';
					str+='</h4>';
					
				
					/* if(i == 0)
					{
						str+='<h4 class="panel-title">';
							str+='<a role="button" class="collapseDebatesIcon" data-toggle="collapse" data-parent="#accordionEvents" href="#collapseEvents'+i+'" aria-expanded="true" aria-controls="collapseEvents'+i+'">'+result[i].name+'';
							str+='</a>';
							str+='<span attr_event_idsString='+result[i].id+' class="eventsListExpandIcon eventCls" style="background-color:#fff;font-size:10px;margin-left:5px;"><i class="glyphicon glyphicon-fullscreen"></i></span>';
						str+='</h4>';
					}else{
						str+='<h4 class="panel-title">';
							str+='<a role="button" class="collapseDebatesIcon collapsed" data-toggle="collapse" data-parent="#accordionEvents" href="#collapseEvents'+i+'" aria-expanded="true" aria-controls="collapseEvents'+i+'">'+result[i].name+'';
							str+='</a>';
							str+='<span attr_event_idsString='+result[i].id+' class="eventsListExpandIcon eventCls" style="background-color:#fff;font-size:10px;margin-left:5px;"><i class="glyphicon glyphicon-fullscreen"></i></span>';
						str+='</h4>';
					} */
					
				str+='</div>';
				/* if(i == 0)
				{
					str+='<div id="collapseEvents'+i+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingEvents'+i+'">';
				}else{
					str+='<div id="collapseEvents'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingEvents'+i+'">';
				} */
					str+='<div class="panel-body pad_5">';
						str+='<div class="row">';
							str+='<div class="col-md-12 col-xs-12 col-sm-12">';
								if($(window).width() < 300)
								{
									str+='<div class="table-responsive">';
								}
								str+='<table class="table tableTraining bg_ED">';
									str+='<tr>';
										str+='<td>';
											str+='<h4>'+result[i].inviteeCount+'</h4>';
											str+='<p class="text-capital text-muted">invited</p>';
										str+='</td>';
										str+='<td>';
											str+='<h4>'+result[i].inviteeAttendedCount+'';
											str+=' <small class="text-danger responsiveFont">'+result[i].inviteeAttendedCounPer+'%</small></h4>';
											str+='<p class="text-capital text-muted">invitees attended</p>';
										str+='</td>';
										str+='<td>';
											str+='<h4>'+result[i].nonInviteeAttendedCount+'';
											str+=' <small class="text-danger responsiveFont">'+result[i].nonInviteeAttendedCountPer+'%</small></h4>';
											str+='<p class="text-capital text-muted">non invitees attended</p>';
										str+='</td>';
									str+='</tr>';
								str+='</table>';
								if($(window).width() < 300)
								{
									str+='</div>';
								}
							str+='</div>';
						str+='</div>';
						
					//str+='</div>';
				str+='</div>';
			str+='</div>';
		}
	//str+='</div>';
	$("#eventIds").attr("attr_event_idsString",eventIdsString);
	
	
	     /*str+='<div class="panel panel-default panelNewEvents">';
				str+='<div class="panel-heading" role="tab"">';
					str+='<h4 class="panel-title">Mahanadu 2016';
						str+='<span attr_event_idsString="30" class="eventsListExpandIcon eventCls" attr_event_name="Mahanadu 2016" style="background-color:#fff;font-size:10px;margin-left:5px;"><i class="glyphicon glyphicon-fullscreen"></i></span>';
					str+='</h4>';
					str+='</div>';
					str+='<div class="panel-body pad_5">';
						str+='<div class="row">';
							str+='<div class="col-md-12 col-xs-12 col-sm-12">';
								if($(window).width() < 300)
								{
									str+='<div class="table-responsive">';
								}
								str+='<table class="table tableTraining bg_ED">';
									str+='<tr>';
										str+='<td>';
											str+='<h4>16796</h4>';
											str+='<p class="text-capital text-muted">invited</p>';
										str+='</td>';
										str+='<td>';
											str+='<h4>4618';
											str+=' <small class="text-danger responsiveFont">27.49%</small></h4>';
											str+='<p class="text-capital text-muted">invitees attended</p>';
										str+='</td>';
										str+='<td>';
											str+='<h4>21332';
											str+=' <small class="text-danger responsiveFont">82.2%</small></h4>';
											str+='<p class="text-capital text-muted">non invitees attended</p>';
										str+='</td>';
									str+='</tr>';
								str+='</table>';
								if($(window).width() < 300)
								{
									str+='</div>';
								}
							str+='</div>';
						str+='</div>';
				str+='</div>';
				
				str+='<div class="panel-heading" role="tab"">';
					str+='<h4 class="panel-title">Mahanadu 2015';
						str+='<span attr_event_idsString="7" class="eventsListExpandIcon eventCls" attr_event_name="Mahanadu 2015" style="background-color:#fff;font-size:10px;margin-left:5px;"><i class="glyphicon glyphicon-fullscreen"></i></span>';
					str+='</h4>';
					str+='</div>';
					str+='<div class="panel-body pad_5">';
						str+='<div class="row">';
							str+='<div class="col-md-12 col-xs-12 col-sm-12">';
								if($(window).width() < 300)
								{
									str+='<div class="table-responsive">';
								}
								str+='<table class="table tableTraining bg_ED">';
									str+='<tr>';
										str+='<td>';
											str+='<h4>12185</h4>';
											str+='<p class="text-capital text-muted">invited</p>';
										str+='</td>';
										str+='<td>';
											str+='<h4>2660';
											str+=' <small class="text-danger responsiveFont">21.83%</small></h4>';
											str+='<p class="text-capital text-muted">invitees attended</p>';
										str+='</td>';
										str+='<td>';
											str+='<h4>7948';
											str+=' <small class="text-danger responsiveFont">74.92%</small></h4>';
											str+='<p class="text-capital text-muted">non invitees attended</p>';
										str+='</td>';
									str+='</tr>';
								str+='</table>';
								if($(window).width() < 300)
								{
									str+='</div>';
								}
							str+='</div>';
						str+='</div>';
				str+='</div>';
			str+='</div>';
	str+='';
	$("#eventIds").attr("attr_event_idsString","7,30"); */
	$("#mainEventsList").html(str)
}

function getUserTypeWiseTotalInviteeAndInviteeAttendedCnt(eventIdsString){
	$("#UserTypeWiseEventMemberDtslDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	$(".eventStrngPrCls").attr("attr_selected_type","Event");
	var eventIds= eventIdsString.split(",");
	var jsObj ={ 
				 activityMemberId : globalActivityMemberId,
				 stateId : globalStateId,
				 eventIds:eventIds,
				 userTypeId : globalUserTypeId
			  }
	$.ajax({
		type : 'POST',
		url : 'getUserTypeWiseTotalInviteeAndInviteeAttendedCntAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		 $("#UserTypeWiseEventMemberDtslDivId").html(' ');
		 globalUserWiseEventMemberRslt = result;
		 buildUserTypeWiseTotalInviteeAndInviteeAttendedCnt(result);
	});
}

function buildUserTypeWiseTotalInviteeAndInviteeAttendedCnt(result){
	var str='';
	if(result != null && result.length > 0){
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
				str+='<div id="eventsCountGraph'+i+'" style="width:100%;height:100px;"></div>';
				str+='</div>'
			}
	}
	$("#UserTypeWiseEventMemberDtslDivId").html(str);
	if(result != null && result.length > 0){
		for(var i in result){
			var eventInviteeAttendedPerArr = [];
			var countVar =0;
			if(result[i] !=null && result[i].length>0){
				for(var j in result[i]){
					 var obj1 = {
							name: result[i][j].name,
							y: result[i][j].inviteeAttendedCntPer
						};
					eventInviteeAttendedPerArr.push(obj1);
					countVar =countVar+1;
					if (countVar === 5) {
						break;
					}
				}
			}
		if(result[i][0].inviteeAttendedCntPer !=0){
				$(function () {
					 $("#eventsCountGraph"+i).highcharts({
						 colors: ['#0066DC'],
						chart: {
							type: 'column'
						},
						title: {
							text: ''
						},
						subtitle: {
							text: ''
						},
						xAxis: {
							min: 0,
							gridLineWidth: 0,
							minorGridLineWidth: 0,
							
							type: 'category',
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
								text: ''
							},
							labels: {
								enabled:false
							}
						},
						legend: {
							enabled: false
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
											return Highcharts.numberFormat(this.y,2) + '%';
										}
									}
								  
								}
							}
						},

						tooltip: {
							headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
							pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}%</b>'
						},

						series: [{
							name: 'Invitees Attended',
							data: eventInviteeAttendedPerArr
						}],
					 
					});
				});
			 } else{
				$("#eventsCountGraph"+i).html("No Data Available");
				$("#eventsCountGraph"+i).css("height","35px");
			}  
		}
	}else{
		$("#UserTypeWiseEventMemberDtslDivId").html("No Data Available");
	}
	
}
function buildUserTypeWisePoorInviteeAttendedEventMemDtlsCnt(result){
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
				str+='<div id="eventsCountGraph'+i+'" style="width:100%;height:100px;"></div>';
				str+='</div>'
			}
		}
		$("#UserTypeWiseEventMemberDtslDivId").html(str);
	if(result != null && result.length > 0){
		for(var i in result){
				var candidateNameArray = [];
				var eventInviteeAttendedPerArr = [];
				var countVar = 0;
				var length = result[i].length - 1;
				for(var j = length; j >= 0; j--){
					candidateNameArray.push(result[i][j].name);
					eventInviteeAttendedPerArr.push(result[i][j].inviteeAttendedCntPer);
					countVar =countVar+1;
					if (countVar === 5) {
						break;
					}
				}
			//if( result[i][j].totalAttenedCountPer!=0){
			//var getWidth = $("#eventsCountGraph"+i).parent().width()+'px';
				//$("#eventsCountGraph"+i).width(getWidth);
				$(function () {
			   $('#eventsCountGraph'+i).highcharts({
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
					name: 'Invitees Attended',
					data: eventInviteeAttendedPerArr
				}]
			});
		});
		/* }else{
		$("#genSecTraining"+i).html("No Data Available");
		$("#genSecTraining"+i).css("height","35px");	
		} */
		}
	}else{
	 $("#UserTypeWiseEventMemberDtslDivId").html('NO DATA AVAILABLE.');
	}	
	
}
function getLocationWiseByInviteeAttendedAndInviteeAttendedCntBasedOnUserType(attrEventIdsString){
	$("#eventsDistWiseCohort").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
    var eventIds = attrEventIdsString.split(",");
	var jsObj ={ 
		 activityMemberId : globalActivityMemberId,
		 stateId : globalStateId,
		 eventIds:eventIds,
		 userTypeId : globalUserTypeId
		 
	  }
	$.ajax({
		type : 'POST',
		url : 'getLocationWiseByInviteeAttendedAndInviteeAttendedCntBasedOnUserTypeAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		buildLocationWiseByInviteeAttendedAndInviteeAttendedCntBasedOnUserType(result);
	});
}
function buildLocationWiseByInviteeAttendedAndInviteeAttendedCntBasedOnUserType(result)
{
	var str='';
	for(var i in result)
	{
		str+='<h4 class="panel-title">'+result[i].name+'</h4>';
		str+='<div id="eventsGraph'+i+'" style="height:120px"></div>';
	}
	$("#eventsDistWiseCohort").html(str)
	if(result != null && result.length > 0){
		for(var i in result){
			
			var inviteesCounts = [];
			var nonInviteesCounts = [];
			var candidateNames = [];
			var countVar =0;
			
			
			for(var j in result[i].locationList){
				candidateNames.push(result[i].locationList[j].name)
				inviteesCounts.push(result[i].locationList[j].inviteeAttendedCounPer)
				nonInviteesCounts.push(result[i].locationList[j].nonInviteeAttendedCountPer)
			}
			
			$(function () {
				 $("#eventsGraph"+i).highcharts({
					colors: ['#D33E39','#64C664'],
					chart: {
						type: 'column'
					},
					title: {
						text: ''
					},
					subtitle: {
						text: ''
					},
					xAxis: {
						min: 0,
						gridLineWidth: 0,
						minorGridLineWidth: 0,
						categories: candidateNames,
						type: 'category',
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
							text: ''
						},
						labels: {
							enabled:false
						}
					},
					legend: {
						enabled: false
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
										return Highcharts.numberFormat(this.y,1) + '%';
									}
								}
							  
							}
						}
					},

					 tooltip: {
						 pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y:.1f}%</b><br/>',
						/* formatter: function () {
							var s = '<b>' + this.x + '</b>';

							$.each(this.points, function () {
								s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
									Highcharts.numberFormat(this.percentage,1)+'%' +' - ' +
									(this.y);
							});

							return s;
						}, */
						shared: true
					},

					series: [{
						name: 'Invitees Attended',
						data: inviteesCounts,
						
					},{
						name: 'Non Invitees Attended',
						data: nonInviteesCounts,
						
					}],
				 
				});
			});
		}
	}else{
		$("#eventsDistWiseCohort").html("No Data Available");
	}
}
function getSelectedEventDetails(attrEventIdsString){
	$("#eventsGraphBlock").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	var eventIds=attrEventIdsString.split(",");
	var jsObj ={ 
		activityMemberId : globalActivityMemberId,
		stateId : globalStateId,
		eventIds:eventIds
	}
	$.ajax({
		type : 'POST',
		url : 'getEventBasicCntDtlsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0){
		  buildSelectedEventDetails(result);	
		}else{
		  $("#eventsGraphBlock").html("NO DATA AVAILABLE.");	
		}
	});	
}
function buildSelectedEventDetails(result)
{
	var str=' ';
	if(result.length > 3)
	{
		str+='<div class="scroll-div">';
	}
		str+='<ul class="list-inline best-matched-profile">';
			for(var i in result)
			{
				str+='<li><h4>'+result[i].name+'</h4>';
				str+='<div id="events'+i+'" class="chartLi"></div></li>';
			}
			
		str+='</ul>';
	if(result.length > 3)
	{
		str+='</div>';
	}
	
	$("#eventsGraphBlock").html(str);

	for(var i in result)
	{
		var eventsInviteeNoNAttendedCountPer= [];
		var eventsInviteeAttendedCounPer=[];
		eventsInviteeNoNAttendedCountPer.push(result[i].nonInviteeAttendedCountPer);
		eventsInviteeAttendedCounPer.push(result[i].inviteeAttendedCounPer);
		$(function () {
			$('#events'+i+'').highcharts({
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
					categories: ['Event'],
					labels: {
						enabled: false,
					}
				},
				yAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: null
					},
					stackLabels: {
						enabled: false,
						style: {
							fontWeight: 'bold',
							color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
						}
					}
				},
				tooltip: {
					pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y:.1f}%</b><br/>',
					shared: true
				},
				legend: {
					enabled: true,
					align: 'left'
			
				},
				plotOptions: {
					column: {
						stacking: 'normal',
						dataLabels:{
							enabled: true,
							formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return Highcharts.numberFormat(this.y,1) + '%';
								}
							}
						},
						
					},
				},
				 series: [{
					name: 'Invitees Attended',
					data: eventsInviteeAttendedCounPer 
				}, {
					name: 'Non Invitees Attended',
					data: eventsInviteeNoNAttendedCountPer
				}]
			});
		});	
	}
	
}

$(document).on("click",".allItsSubUserTypeClsForEvent",function(){
	var childUserTypeId = $(this).attr("attr_userTypeId");
    var attrEventIdsString = $(this).attr("attr_event_idsString");
	var childUserType = $(this).attr("attr_userType");
	var searchType = $(this).attr("attr_search_type"); // attrActivityIdsString 
		//alert(3333);
	//getAllItsSubUserTypeIdsByParentUserTypeIdForActivity(attrEventIdsString,searchType,childUserTypeId);//globalUserTypeId
	
	//getSelectedChildTypeMembersForEvent("",attrEventIdsString,userType,searchType);
	$("#topPoorLocationsEventDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	if(searchType == 'activities' || searchType == 'singleActivity' || searchType == 'scopeId')
		getSelectedChildTypeMembersForActivity(childUserTypeId,attrEventIdsString,childUserType,searchType);
	else{
		getSelectedChildTypeMembersForEvent(childUserTypeId,attrEventIdsString,childUserType,searchType);
	}
});
 $(document).on("click",".remveSlcUsrTypeForEvent",function(){
		 var removeSelected = $(this).attr("attr_remove_SelecUserType"); 
		 $("#"+removeSelected).html(' ');
		 $("#"+removeSelected).closest('.showHideTr').hide();
 });
 $(document).on("click",".childEventMemberCls",function(){
	    
		$(".slickPanelSliderForEvent").find("li").find(".panelSlick").removeClass("panelActiveSlick");
		$(this).find(".panelSlick").addClass("panelActiveSlick");
	    var activityMemberId = $(this).attr("attr_activitymemberid");  
		var userTypeId = $(this).attr("attr_usertypeid"); 
    	var selectedMemberName = $(this).attr("attr_selectedmembername");  
		var selectedUserType = $(this).attr("attr_selectedusertype"); 	
		var childActivityMemberId = $(this).attr("attr_id");  
		var attrEventIdsString = $(this).attr("attr_event_idsString");
		if(selectedUserType != null && selectedUserType.trim()=="MLA/CI" || selectedUserType.trim()=="MLA" || selectedUserType.trim()=="CONSTITUENCY INCHARGE"){
		  getEventPoorPerformanceLocation(userTypeId,activityMemberId,selectedMemberName,selectedUserType,attrEventIdsString,"events");
		 }else{
	      getDirectChildTypeMembersForEvent(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId,attrEventIdsString,"events");
		  getEventPoorPerformanceLocation(userTypeId,activityMemberId,selectedMemberName,selectedUserType,attrEventIdsString,"events");
		}
		
});
$(document).on("click",".subLevelEventMemberCls",function(){
	$(this).next('tr.showHideTr').show(); 
	var activityMemberId = $(this).attr("attr_activitymemberid");  
	var userTypeId = $(this).attr("attr_usertypeid"); 
	var selectedMemberName = $(this).attr("attr_selectedmembername");  
	var selectedUserType = $(this).attr("attr_selectedusertype");  
	var childActivityMemberId = $(this).closest('tr').next('tr.showHideTr').attr("attr_id");  
	var attrEventIdsString = $(this).attr("attr_event_idsString");
	if(selectedUserType != null && selectedUserType.trim()=="MLA/CI" || selectedUserType.trim()=="MLA" || selectedUserType.trim()=="CONSTITUENCY INCHARGE"){
		  getEventPoorPerformanceLocation(userTypeId,activityMemberId,selectedMemberName,selectedUserType,attrEventIdsString,"events");
	}else{
	      getDirectChildTypeMembersForEvent(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId,attrEventIdsString,"events");
		  getEventPoorPerformanceLocation(userTypeId,activityMemberId,selectedMemberName,selectedUserType,attrEventIdsString,"events");
	}
});
 function getAllItsSubUserTypeIdsByParentUserTypeIdForEvent(attrEventIdsString,searchType,userTypeId){
	     
		  $("#childEvnetMemberDivId").html(' ');
		  $("#directChildMemberForEventDivId").html(' ');
		  $("#topPoorLocationsEventDivId").html(' ');
		  
		 $("#allItsSubUserTypeIdsByParentUserTypeDivIdForEvent").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		  var userTypeArr =userTypeId.split(',');
		var jsObj = {parentUserTypeId : userTypeArr}
		$.ajax({
			type : 'POST',
			url : 'getAllItsSubUserTypeIdsByParentUserTypeIdAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#allItsSubUserTypeIdsByParentUserTypeDivIdForEvent").html(" ");	
			if(result != null && result.length > 0){
			buildgetChildUserTypesByItsParentUserTypeForEvent(result,attrEventIdsString,searchType);	
			}else{
			$("#allItsSubUserTypeIdsByParentUserTypeDivIdForEvent").html("NO DATA AVAILABLE");	
			}
		});		 
	}
	
function buildgetChildUserTypesByItsParentUserTypeForEvent(result,attrEventIdsString,searchType){
		var str='';
		 str+='<ul class="comparisonSelect">';
		 
		 var firstChildUserTypeIdString;
		 var userType;
		 if(result !=null && result.length >0){
			  firstChildUserTypeIdString = result[0].shortName;
			  userType=result[0].userType;
			 for(var i in result){
				 str+='<li attr_search_type="'+searchType+'" attr_userTypeId="'+result[i].shortName+'" attr_userType=\''+result[i].userType+'\'  attr_event_idsString='+attrEventIdsString+' class="allItsSubUserTypeClsForEvent">'+result[i].userType+'<span class="closeIconComparison"></span></li>';
			 }
		 }
		str+='</ul>';
		$("#allItsSubUserTypeIdsByParentUserTypeDivIdForEvent").html(str);
		$(".comparisonSelect li:first-child").addClass("active")
		
		getSelectedChildTypeMembersForEvent(firstChildUserTypeIdString,attrEventIdsString,userType,searchType);
	}
function getSelectedChildTypeMembersForEvent(firstChildUserTypeIdString,attrEventIdsString,childUserType,searchType){
	 $("#childEvnetMemberDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	 $("#directChildMemberForEventDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	  var parentActivityMemberId = globalActivityMemberId;
	  var childUserTypeIdsArray = firstChildUserTypeIdString.split(",");
	   var eventIds=attrEventIdsString.split(",");
	 var jsObj ={ 
	               parentActivityMemberId : parentActivityMemberId,
				   childUserTypeIdsArray : childUserTypeIdsArray,
				   reportType :"selectedUserType",
				   stateId : globalStateId,
				   eventIds:eventIds,
				   searchType : searchType
				 }
	  $.ajax({
			type : 'POST',
			url : 'getSelectedChildTypeMembersForEventAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		   $("#childEvnetMemberDivId").html(' ');
		   $("#directChildMemberForEventDivId").html(' ');
		  if(result != null && result.length > 0){
			  buildChildTypeMembersForEventReslt(result,attrEventIdsString,childUserType,searchType);
		  }else{
			  $("#childEvnetMemberDivId").html("NO DATA AVAILABLE");
		  }
		});
 }
 function buildChildTypeMembersForEventReslt(result,attrEventIdsString,childUserType,searchType){
	  var userTypeId = result[0].userTypeId;
	  var activityMemberId = result[0].activityMemberId;
	  var selectedMemberName = result[0].name;
	  var selectedUserType = result[0].userType;
	 var str='';
	 if(childUserType != null && childUserType.trim()=="MLA/CI" || childUserType.trim()=="MLA" || childUserType.trim()=="CONSTITUENCY INCHARGE"){
	     str+='<table style="background-color:#EDEEF0;border:1px solid #ddd" class="table table-condensed tableHoverLevels" id="eventMemberDataTblId">';
		 str+='<thead>';
		     str+='<th>Rank</th>';
			 str+='<th>Name</th>';
			 str+='<th>Designation</th>';
			 str+='<th>Location</th>';
			 str+='<th>Invitees</th>';
			 str+='<th>Invitees Attended</th>';
			 str+='<th>%</th>';
			 str+='<th>Non Invitees Attended</th>';
			 str+='<th>%</th>';
		 str+='</thead>';
		 str+='<tbody>';
		 var rank=1;
		  for(var i in result){
			str+='<tr style="cursor:pointer;" class="childEventMemberCls" attr_event_idsString="'+attrEventIdsString+'"  attr_selectedusertype="'+result[i].userType+'"  attr_id="directChildMemberForEventDivId"  attr_selectedmembername="'+result[i].name+'"  attr_activitymemberid='+result[i].activityMemberId+'  attr_usertypeid='+result[i].userTypeId+'>';
			 str+='<td><span class="counts">'+rank+'</span></td>';
			 str+='<td>'+result[i].name+'</td>';
			 str+='<td>'+result[i].userType+'</td>';
			 str+='<td>'+result[i].locationName+'</td>';
			 str+='<td>'+result[i].inviteeCnt+'</td>';
			 str+='<td>'+result[i].inviteeAttendedCnt+'</td>';
			 str+='<td>'+result[i].inviteeAttendedCntPer+'</td>';
			 str+='<td>'+result[i].nonInviteeAttendedCnt+'</td>';
			 str+='<td>'+result[i].nonInviteeAttendedCntPer+'</td>';
			 str+='</tr>';
             rank=rank+1;			 
			}
			 str+='</tbody>';
			 str+='</table>';
	    $("#childEvnetMemberDivId").html(str);
		$("#eventMemberDataTblId").dataTable({
			"aaSorting": [],
			"iDisplayLength" : 5	
		});
	  getEventPoorPerformanceLocation(userTypeId,activityMemberId,selectedMemberName,selectedUserType,attrEventIdsString,searchType);
	  }else{
	  str+='<ul class="list-inline slickPanelSliderForEventDiv">';
	  var rank=1; 
	   for(var i in result){
	str+='<li style="cursor:pointer;" class="childEventMemberCls"  attr_event_idsString="'+attrEventIdsString+'" attr_selectedusertype="'+result[i].userType+'"  attr_id="directChildMemberForEventDivId"  attr_selectedmembername="'+result[i].name+'"  attr_activitymemberid='+result[i].activityMemberId+'  attr_usertypeid='+result[i].userTypeId+' style="width:380px !important;">';
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
	   if(result[i].userTypeId != null && result[i].userTypeId==7 || result[i].userTypeId==9 || result[i].userTypeId==5 || result[i].userTypeId==6){ // MLA Constituency Incharge, MP and District President Incharge 
		   var lctnName = result[i].locationName;
           lctnName = lctnName.substring(0, lctnName.lastIndexOf(" "));
		 str+='<h4 class="text-capital">'+result[i].userType+' ('+lctnName+')</h4>';	 
		 }else{
		 str+='<h4 class="text-capital">'+result[i].userType+'</h4>';	 
		 }
			
			 str+='<table class="table table-condensed">';
				 str+='<thead>';
					 str+='<th>Invitees</th>';
					 str+='<th>Invitees Attended</th>';
					 str+='<th>%</th>';
				     str+='<th>Non Invitees Attended</th>';
				     str+='<th>%</th>';
				 str+='</thead>';
				 str+='<tr>';
					 str+='<td>'+result[i].inviteeCnt+'</td>';
					 str+='<td>'+result[i].inviteeAttendedCnt+'</td>';
					 str+='<td>'+result[i].inviteeAttendedCntPer+'</td>';
					 str+='<td>'+result[i].nonInviteeAttendedCnt+'</td>';
					 str+='<td>'+result[i].nonInviteeAttendedCntPer+'</td>';
				 str+='</tr>';
			 str+='</table>';
		 str+='</div>';
	 str+='</div> ';
    str+=' </li> ';  
	rank=rank+1;
	}
   $("#childEvnetMemberDivId").html(str);
   
		getEventPoorPerformanceLocation(userTypeId,activityMemberId,selectedMemberName,selectedUserType,attrEventIdsString,searchType);
		getDirectChildTypeMembersForEvent(activityMemberId,userTypeId,selectedMemberName,selectedUserType,"directChildMemberForEventDivId",attrEventIdsString,searchType);	
		
				
	  }
		
	$(".slickPanelSliderForEventDiv").slick({
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
function getDirectChildTypeMembersForEvent(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId,attrEventIdsString,searchType){
	  $("#"+childActivityMemberId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
			     var eventIds=attrEventIdsString.split(",");
		         var childUserTypeIdsArray=[];
	             childUserTypeIdsArray.push(userTypeId);
	  var jsObj ={   activityMemberId : activityMemberId,
			         childUserTypeIdsArray : childUserTypeIdsArray,
					 reportType : "directChild",
					 stateId : globalStateId,
					 eventIds:eventIds,
					 searchType : searchType
				  }
	   	$.ajax({
			type : 'POST',
			url : 'getDirectChildTypeMembersForEventAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
	     $("#"+childActivityMemberId).html('');
		  if(result != null && result.length > 0){
			  buildEventDirectChildDetailsRslt(result,selectedMemberName,selectedUserType,childActivityMemberId,userTypeId,attrEventIdsString)
		  }else{
		  // $("#"+childActivityMemberId).html('NO DATA AVAILABLE'); 
           if(childActivityMemberId == "userTypeWiseChildDtlsTabId"){
				$("#"+childActivityMemberId).html("<h5><span  class='text-capital'>"+selectedMemberName+"</span> - <span class='text-capitalize'>"+selectedUserType+"</span> - ( No Data Available )</h5>");
			}		  
		  }
		});
 }
 function buildEventDirectChildDetailsRslt(result,selectedMemberName,selectedUserType,childActivityMemberId,userTypeId,attrEventIdsString){
	 var str='';
		str+='<h4><span  class="text-capital">'+selectedMemberName+'</span> - <span class="text-capitalize">'+selectedUserType+'</span></h4>';
		 if(childActivityMemberId != "directChildMemberForEventDivId"){
				str+='<span class="remveSlcUsrTypeForEvent pull-right" attr_remove_SelecUserType = "'+childActivityMemberId+'" style="margin-top: -5px;"><i class="glyphicon glyphicon-remove"></i></span>';
		 } 
		 if(childActivityMemberId != "directChildMemberForEventDivId"){
			 str+='<table  class="table table-condensed tableHoverLevelsInner m_top20">';
		 }else{
			str+='<table class="table table-condensed tableHoverLevels m_top20">';  
		 }
				str+='<thead   class="bg_D8 text-capital">';
					str+='<th>Rank</th>';
					str+='<th>Designation</th>';
					str+='<th>Name</th>';
				    str+='<th>Invitees</th>';
					 str+='<th>Invitees Attended</th>';
					 str+='<th>%</th>';
				     str+='<th>Non Invitees Attended</th>';
				     str+='<th>%</th>';
				str+'=</thead>';
		str+='<tbody>';
		var rank=1;
		 for(var i in result){
		var yourValues = result[i].locationName;
		   str+='<tr  class="subLevelEventMemberCls" attr_event_idsString="'+attrEventIdsString+'" attr_activitymemberid = "'+result[i].activityMemberId+'" attr_usertypeid = "'+result[i].userTypeId+'" attr_selectedmembername = "'+result[i].name+'" attr_selectedusertype = "'+result[i].userType+'">';
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
			str+='<td style="text-align:center;">'+result[i].inviteeCnt+'</td>';
			str+='<td style="text-align:center;">'+result[i].inviteeAttendedCnt+'</td>';
			str+='<td style="text-align:center;">'+result[i].inviteeAttendedCntPer+'</td>';
			str+='<td style="text-align:center;">'+result[i].nonInviteeAttendedCnt+'</td>';
			str+='<td style="text-align:center;">'+result[i].nonInviteeAttendedCntPer+'</td>';
		 str+='</tr>';
		str+='<tr class="showHideTr" style="display:none" attr_id = "subChildLevelEventMemDtslId'+result[i].userTypeId+''+i+'">';
		str+='<td colspan="8"  id="subChildLevelEventMemDtslId'+result[i].userTypeId+''+i+'">';
		str+='</td>';
		 rank=rank+1;
		 }
		str+='</tbody>';
		str+='</table>';
	$("#"+childActivityMemberId).html(str);
 }

function getEventPoorPerformanceLocation(userTypeId,activityMemberId,selectedUserName,userType,attrEventIdsString,searchType){
$("#topPoorLocationsEventDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
var eventIds=attrEventIdsString.split(",");
	var jsObj ={ 
				 activityMemberId : activityMemberId,
				 stateId : globalStateId,
				 eventIds:eventIds,
				 userTypeId : userTypeId,
				 searchType : searchType
				 
			  }
	$.ajax({
		type : 'POST',
		url : 'getEventPoorPerformanceLocationAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		$("#topPoorLocationsEventDivId").html(" ");	
		if(result != null ){
		buildEventPoorPerformanceLocationRslt(result,userTypeId,selectedUserName,userType);	
		}
	});	
}
function buildEventPoorPerformanceLocationRslt(result,userTypeId,selectedUserName,userType){
    var resultListFirst;
	var resultListSecond;
    var str='';
		str+='<div class="col-md-12 col-xs-12 col-sm-12"><span class="color_333 pad_5 bg_CC text-capital"><span class="text-danger">poor</span> Event performance locations&nbsp&nbsp('+selectedUserName+" - "+userType+')</span></div>';
	   str+='<div class="col-md-6 col-xs-12 col-sm-6 m_top10">';
	  if(userTypeId!= null && userTypeId==3 || userTypeId==2 || userTypeId==1){
		str+='<p class="text-capital">districts<span style="margin-left:280px">Invitees Attended(%)</span></p>';  
		resultListFirst = result.districtList;
		resultListSecond = result.constituencyList;
	  }
	  if(userTypeId!= null && userTypeId==5 || userTypeId==11 || userTypeId==4 || userTypeId==6 || userTypeId==12 || userTypeId==14){
		str+='<p class="text-capital">Constituencies<span style="margin-left:240px">Invitees Attended(%)</span></p>';  
		resultListFirst = result.constituencyList;
		resultListSecond = result.mandalTwnDivisionList;  
	  }
	   if(userTypeId!= null && userTypeId==7 || userTypeId==8 || userTypeId==9){
		 str+='<p class="text-capital">Mandal/Town/Division<span style="margin-left:180px">Invitees Attended(%)</span></p>';  
		resultListFirst = result.mandalTwnDivisionList;
		resultListSecond = result.villageWardList;  
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
				str+='<div class="progress progressCustom" data-toggle="tooltip" data-placement="top" title="'+resultListFirst[i].inviteeAttendedCounPer+'%">';
			str+='<div class="progress-bar" role="progressbar" aria-valuenow="'+resultListFirst[i].inviteeAttendedCounPer+'" aria-valuemin="0" aria-valuemax="100" style="width:'+resultListFirst[i].inviteeAttendedCounPer+'%;">';
					str+='<span class="sr-only">'+resultListFirst[i].inviteeAttendedCounPer+'</span>';
				  str+='</div>';
				str+='</div>';
			str+='</td>';
			str+='<td class="text-danger">'+resultListFirst[i].inviteeAttendedCounPer+'</td>';
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
	   if(userTypeId!= null && userTypeId==3 || userTypeId==2 || userTypeId==1){
		str+='<p class="text-capital">Constituencies<span style="margin-left:240px">Invitees Attended(%)</span></p>';  
	  }
	  if(userTypeId!= null && userTypeId==5 || userTypeId==11 || userTypeId==4 || userTypeId==6 || userTypeId==12 || userTypeId==14){
		 str+='<p class="text-capital">Mandal/Town/Division<span style="margin-left:180px">Invitees Attended(%)</span></p>';  
	  }
	   if(userTypeId!= null && userTypeId==7 || userTypeId==8 || userTypeId==9){
		 str+='<p class="text-capital">Village/Ward<span style="margin-left:250px">Invitees Attended(%)</span></p>';  
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
				str+='<div class="progress progressCustom" data-toggle="tooltip" data-placement="top" title="'+resultListSecond[i].inviteeAttendedCounPer+'%">';
		str+='<div class="progress-bar" role="progressbar" aria-valuenow="'+resultListSecond[i].inviteeAttendedCounPer+'" aria-valuemin="0" aria-valuemax="100" style="width:'+resultListSecond[i].inviteeAttendedCounPer+'%;">';
			str+='<span class="sr-only">'+resultListSecond[i].inviteeAttendedCounPer+'</span>';
			str+='</div>';
			str+='</div>';
			str+='</td>';
			str+='<td class="text-danger">'+resultListSecond[i].inviteeAttendedCounPer+'</td>';
			str+='</tr>';
			order=order+1;
			if(order==6)
				break;
			BGColor = BGColor - 0.2;
			}
				str+='</table>';
			}
	     str+='</div>';
																				
	 $("#topPoorLocationsEventDivId").html(str);	
	 $('.progressCustom').tooltip()
	}
	
/* EVENT FUNCTIONALITY END*/






/*Notes Functionality*/
function displayDashboardCommentsForEvents(dashBoardComponentId){
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
			 
		 str+='<ul class="notesUlEvents m_top20" style="text-transform: none;font-weight: normal;font-size: 14px;">';  	
			for(var i in result){ 
				str+='<li style="margin-top:3px;">'; 
				str+='<span class="notesTextEvents" id="editTextEventsId'+i+'"  attr_commentId="'+result[i].dashBoardCommentId+'">'+result[i].comment+' </span>- <span class="text-muted"><i>'+result[i].insertedTime+'</i></span>';
				str+='<i class="glyphicon glyphicon-trash pull-right hoverBlock deleteNotesEvents" attr_cmt_id="editTextEventsId'+i+'" id="'+result[i].dashBoardCommentId+'" onClick="deleteDashBoardcomments(this.id);"></i>';
				str+='<i class="glyphicon glyphicon-edit pull-right hoverBlock editNotesEvents" attr_cmt_id="editTextEventsId'+i+'" attr_comment="'+result[i].comment+'"></i>';
				str+='</li>';
			}
		str+='</ul>';
			
			$("#notesEventsId").html(str);	 
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

function savingDashboardCommentForEvents(dashboardComponentId){  
	var comment=$(".notesAreaEvents").val();
	if(comment.trim() ==""){
		  $("#eventsUpId").html("Notes Required.");
		  return;
	  }
	var editId = $("#cmtEventsId").val();
	//$("#"+editId).parent().html(' ');
	$("#"+editId).html(comment);
	 var dashboardCommentId=0;
	 if($(".notesAreaEvents").attr("attr_commentid")>0)
	 {
		dashboardCommentId=$(".notesAreaEvents").attr("attr_commentid");		
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
				displayDashboardCommentsForEvents(6);
			}
		}			
	});
}
$(document).on("click",".notesIconEvents",function(){
	$(this).closest(".panel-heading").find(".notesDropDown").toggle();
});
$(document).on("click",".deleteNotesEvents",function(){
	$(this).closest("li").remove();
});
$(document).on("click",".editNotesEvents",function(){ 
	var commentId = $(this).attr("attr_cmt_id");
	var commentId1 = $(this).parent().find(".notesTextEvents").attr("attr_commentid");
	var notesHtml = $("#"+commentId).html();
	$(".notesAreaEvents").val(notesHtml);  
	$(".notesAreaEvents").attr("attr_commentid",commentId1);  
	$("#cmtId").val(commentId);
	//$("#cmtId").val();
	$("#eventsUpId").html('');		
});

$(document).on("click",".btnCustomCreateEvents",function(){
	var getNewNotes = $(".notesAreaEvents").val();
	var todayDate = moment().format("DD MMMM YYYY");
	var cmtId = $("#cmtId").val();
	var commentText = '<span class="notesText" id="'+cmtId+'" >'+getNewNotes+'</span> - <span class="text-muted"><i>'+todayDate+'</i></span> <i  class="glyphicon glyphicon-trash pull-right hoverBlock deleteNotesEvents"></i><i class="glyphicon glyphicon-edit pull-right hoverBlock editNotes" attr_cmt_id="'+cmtId+'"></i>'; 
	if(cmtId>0)
	$(".notesUlEvents").append("<li>"+commentText+"</li>");
	$(".notesAreaEvents").val('');	
});

/*Notes Functionality End*/

/* Activities Functionality Start */
var  globalActivityIdsList =[];
function getActivitiesDetails(){
	$("#activityEventsListNew,#janmabhoomiEventDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	 var dates = $('#dateRangeIdForEvents').val();
	  var dateArray = dates.split("-");
	  var fromDateStr=dateArray[0];
	  var toDateStr=dateArray[1];
	
	var jsObj={
		//fromDate: '01/01/2014',//customStartDateActivities,
		//toDate: '01/01/2020'  //customEndDateActivities
		fromDate : '01/02/2015',
	    toDate : '01/02/2020'
	}	
	$.ajax({
	 type: "POST",
	 url: "getActivityDetailsAction.action",
	 data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		$("#activityEventsListNew,#janmabhoomiEventDivId").html('');
		if(result != null && result.length > 0)
			buildActivityEventBasicCntDtlsNew(result);
	});
}
function buildActivityEventBasicCntDtlsNew(result)
{	
	var str='';
	var str1='';
	var activityIdsString='';
    str+='<div class="row">';
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
			str+='<div class="">';
				str+='<div class="panel-group panelBlockCollapse" id="accordionAct" role="tablist" aria-multiselectable="true" style="margin-top: 10px;">';				
				for(var i in result)
				{
					if((result[i].id != "36" || result[i].id != 36) && (result[i].id != "37" || result[i].id != 37)){
						 if(i== 0){
						activityIdsString = result[i].id;	
						}else{
						 activityIdsString = activityIdsString+','+result[i].id;	
						}
						globalActivityIdsList.push(parseInt(result[i].id));
						str+='<div class="panel panel-default">';
							str+='<div class="panel-heading" role="tab" id="headingOneAct'+i+'">';
								str+='<h4 class="text-capital" style="color:#4a5863;display:inline-block;">'+result[i].name+'';
								str+='<span class="activitesExpandIcon" attr_search_type="singleActivity"  attr_level_id="0" attr_activity_name="\''+result[i].name+'\'" attr_id="'+result[i].id+'"><i class="glyphicon glyphicon-fullscreen text-center"></i></span></h4>';
								str+='<a role="button" style="display:inline-block;float:right"	class="panelBlockCollapseIcon collapsed activitiesClass" attr_activity_name="\''+result[i].name+'\'" data-toggle="collapse" data-parent="#accordionAct" href="#collapseOneAct'+i+'" aria-expanded="true" aria-controls="collapseOneAct'+i+'" attr_id="'+result[i].id+'" attr_divId="activityBodyId'+i+'">';
								str+='</a>';
							str+='</div>';
							str+='<div id="collapseOneAct'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOneAct'+i+'">';
								str+='<div class="panel-body">';
									str+='<div id="activityBodyId'+i+'"></div>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
					}else if(result[i].id == "37" || result[i].id == 37){
						str1+='<div class="panel panel-default panelNewEvents">';
						str1+='<div class="panel-heading" role="tab">';
							str1+='<h4 class="panel-title">'+result[i].name+'';
								str1+='<span class="activitesExpandIcon" attr_search_type="singleActivity"  attr_level_id="0" attr_activity_name="\''+result[i].name+'\'" attr_id="'+result[i].id+'"><i class="glyphicon glyphicon-fullscreen text-center" style="padding-top: 2px;padding-bottom: 2px;padding-left: 3px;padding-right: 4px;"></i></span>';
								str1+='<a role="button" style="display:inline-block;float:right"	class="panelBlockCollapseIcon collapsed activitiesClass" attr_activity_name="\''+result[i].name+'\'" data-toggle="collapse" data-parent="#accordionAct" href="#collapseOneActJ'+i+'" aria-expanded="true" aria-controls="collapseOneActJ'+i+'" attr_id="'+result[i].id+'" attr_divId="activityBodyId'+i+'">';
							str1+='</h4>';
						str1+='</div>';
								str1+='<div id="collapseOneActJ'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOneActJ'+i+'">';
									str1+='<div class="panel-body">';
										str1+='<div id="activityBodyId'+i+'"></div>';
									str1+='</div>';
								str1+='</div>';
					str1+='</div>';
				}		
				 
				}
				
				str+='</div>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	$("#activityEventsListNew").html(str);
	$("#janmabhoomiEventDivId").html(str1);
	
	
	$(".overAllActivityCls").attr("attr_id",activityIdsString);
	$(".overAllActivityCls").attr("attr_level_id",0);
}

$(document).on("click",".activitiesClass",function(){
	var activityId = $(this).attr("attr_id");
	var activityName = $(this).attr("attr_activity_name");
	$("#hiddenActivityId").val(activityId);
	var divId = $(this).attr("attr_divId");
	$("#"+divId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	
	var jsObj={
		activityId:activityId,
		activityMemberId : globalActivityMemberId,
	    stateId : globalStateId,
	    userTypeId : globalUserTypeId
	}	
	$.ajax({
	 type: "POST",
	 url: "getActivityOverAllSummaryAction.action",
	 data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0)
			buildActivityCounts(result,divId,activityName,activityId);
	});
});

function buildActivityCounts(result,divId,activityName,activityId)
{
	var str=' ';
	for(var i in result){
		var totalCount =parseInt(result[i].apTotal);
		var updatedCount = parseInt(result[i].yesCount)+parseInt(result[i].noCount)+parseInt(result[i].mayBecount);
		var notUpdatedCount = parseInt(totalCount)-parseInt(updatedCount);
		
		str+='<div class="row">';
			str+='<h5 class="text-capital">'+result[i].name+' <span class="activitesExpandIcon" attr_search_type="scopeId" attr_level_id="'+result[i].id+'"  attr_activity_name='+activityName+' attr_id="'+activityId+'" style="padding-top: 2px;padding-bottom: 2px;padding-left: 5px;padding-right: 4px;"><i class="glyphicon glyphicon-fullscreen"></i> </span></h5>';
		str+='</div>';
		str+='<div class="row m_top10">';
			str+='</div><br>';
			
			str+='<div>';
			str+='<table class="table bg_ED tablePaddingSyle table-bordered">';
				str+='<tbody>';
					str+='<tr>';
						
						if(result[i].isCsd != null && result[i].isCsd.length>0 && parseInt(result[i].isCsd)>0){
							str+='<td rowspan="2">';
						}else{
							str+='<td rowspan="2">';
						}
							
						
							str+='<p class="text-muted text-capital">total</p>';
							str+='<h5 style="margin-top:25px !important;">'+result[i].apTotal+'</h5>';
						str+='</td>';
						
						str+='<td>';
							str+='<p class="text-muted text-capital">YES</p>';
							/*if(result[i].yesCount != null && result[i].yesCount>0)
								str+='<u><h5 class="activityCountCls" attr_actvty_scope_id="'+result[i].tdpcadreId+'" style="cursor:pointer;" >'+result[i].yesCount+' <small><span class="text-success">'+result[i].mobileNumber+'%</span></small></h5><u>';
							else*/
								str+='<h5 class="" attr_actvty_scope_id="'+result[i].tdpcadreId+'" data-toggle="tooltip" data-placement="top" title="Both Info Cell and IVR status updated as conducted " >'+result[i].yesCount+' </h5>';
						str+='</td>';
						
						str+='<td>';
							str+='<p class="text-muted text-capital">No</p>';
							/*if(result[i].noCount != null && result[i].noCount>0)
								str+='<h5>'+result[i].noCount+' <small><span class="text-success">'+result[i].imagePathStr+'%</span></small></h5>';
							else*/
								str+='<h5 class="activityCountCls" attr_actvty_scope_id="'+result[i].tdpcadreId+'"  data-toggle="tooltip" data-placement="top"  title="Both Info Cell and IVR status updated as Not conducted " >'+result[i].noCount+' </h5>';
						str+='</td>';
						
						str+='<td>';
							str+='<p class="text-muted text-capital">Maybe</p>';
							/*if(result[i].mayBecount != null && result[i].mayBecount >0 )
								str+='<u><h5 class="activityCountCls" attr_actvty_scope_id="'+result[i].tdpcadreId+'" style="cursor:pointer;">'+result[i].mayBecount+' <small><span class="text-success">'+result[i].actualMobNumber+'%</span></small></h5></u>';
							else*/
								str+='<h5 class="" attr_actvty_scope_id="'+result[i].tdpcadreId+'"  data-toggle="tooltip" data-placement="top"  title=" One of the Info Cell and IVR updated as not conducted " >'+result[i].mayBecount+' </h5>';
						str+='</td>';
						
						//str+='<td>';
							//str+='<button type="button" class="btn btn-success text-capital getImageCls">get Images</button>';
						//str+='</td>';
						
						str+='<td>';
							str+='<p class="text-muted text-capital">Not Updated </p>';
							//console.log(notUpdatedCount);
							/*if(notUpdatedCount>0)
								str+='<u><h5 class="getImageCls" attr_activity_scopeid="'+result[i].tdpcadreId+'" style="cursor:pointer;">'+notUpdatedCount+' </h5></u>';
							else*/
								str+='<h5 class=""  data-toggle="tooltip" data-placement="top"  attr_activity_scopeid="'+result[i].tdpcadreId+'" title=" No data available from Both Info Cell and IVR " >'+notUpdatedCount+' </h5>';
						str+='</td>';
						
					str+='</tr>';
					
					str+='<tr>';
						/*str+='<td>';
							str+='<p class="text-muted text-capital">total</p>';
							str+='<h5>'+result[i].apTotal+'</h5>';
						str+='</td>';*/
					
						if(result[i].isCsd != null && result[i].isCsd.length>0 && parseInt(result[i].isCsd)>0){
							str+='<td>';
								str+='<p class="text-muted text-capital">attended Count </p>';
								str+='<h5 style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title="Total Attended Members" >'+result[i].isCsd+'</h5>';
							str+='</td>';
						}
					
						str+='<td>';
							str+='<p class="text-muted text-capital">Planned</p>';
							/*if(result[i].inviteeCount != null && result[i].inviteeCount>0)
								str+='<u><h5 style="cursor:pointer;" class="activityCountCls" attr_actvty_scope_id="'+result[i].tdpcadreId+'" style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title="Total Planned Locations" >'+result[i].inviteeCount+' <small><span class="text-success">'+result[i].mobileNumber+'%</span></small></h5><u>';
							else
								str+='<h5 style="cursor:pointer;" class="" attr_actvty_scope_id="'+result[i].tdpcadreId+'" data-toggle="tooltip" data-placement="top" >'+result[i].inviteeCount+' <small><span class="text-success">'+result[i].mobileNumber+'%</span></small></h5>';*/
							//str+='<h5>'+result[i].apTotal+'</h5>';
							str+='<h5> - </h5>';
						str+='</td>';
						str+='<td>';
							str+='<p class="text-muted text-capital">IVR</p>';
							if(result[i].attenteeCount != null && result[i].attenteeCount>0)
								str+='<h5 style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title="Total IVR Conducted Locations " >'+result[i].attenteeCount+' <small><span class="text-success">'+result[i].imagePathStr+'%</span></small></h5>';
							else
								str+='<h5 style="cursor:pointer;" class="activityCountCls" attr_actvty_scope_id="'+result[i].tdpcadreId+'" data-toggle="tooltip" data-placement="top" >'+result[i].attenteeCount+' <small><span class="text-success">'+result[i].imagePathStr+'%</span></small></h5>';
						str+='</td>';
						str+='<td>';
							str+='<p class="text-muted text-capital">Infocell</p>';
							if(result[i].inviteeAttendeeCnt != null && result[i].inviteeAttendeeCnt >0 )
								str+='<u><h5 style="cursor:pointer;" class="activityCountCls" attr_actvty_scope_id="'+result[i].tdpcadreId+'" style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title=" Clikc here to view activity Conducted Locations "  attr_activity_name="'+result[i].locationName+'" attr_level_name="'+result[i].name+'" attr_level_id="'+result[i].id+'" >'+result[i].inviteeAttendeeCnt+' <small><span class="text-success">'+result[i].actualMobNumber+'%</span></small></h5></u>';
							else
								str+='<h5 style="cursor:pointer;" class="" attr_actvty_scope_id="'+result[i].tdpcadreId+'" data-toggle="tooltip" data-placement="top"  >'+result[i].inviteeAttendeeCnt+' <small><span class="text-success">'+result[i].actualMobNumber+'%</span></small></h5>';
						str+='</td>';
						//str+='<td>';
							//str+='<button type="button" class="btn btn-success text-capital getImageCls">get Images</button>';
						//str+='</td>';
						str+='<td>';
							str+='<p class="text-muted text-capital">Images Covered</p>';
							if(result[i].totalImages != null && result[i].totalImages>0)
								str+='<u><h5 style="cursor:pointer;" class="getImageCls"  type="overAll"  activity_name="'+activityName+'" attr_activity_scopeid="'+result[i].tdpcadreId+'" attr_level_id="'+result[i].id+'" style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title="Click here to view images." >'+result[i].imagesCovered+' <small><span class="text-success" >'+result[i].totalImages+' images covered</span></small></h5></u>';
							else
								str+='<h5 style="cursor:pointer;" class="" attr_activity_scopeid="'+result[i].tdpcadreId+'" attr_level_id="'+result[i].id+'" data-toggle="tooltip" data-placement="top"  >'+result[i].imagesCovered+' <small><span class="text-success">'+result[i].totalImages+' images covered</span></small></h5>';
						str+='</td>';
						
						/* str+='<td>'; //notes
						if(result[i].isCsd != null && result[i].isCsd.length>0 && parseInt(result[i].isCsd)>0){
							str+='<p class="text-muted text-capital">Count </p>';
							str+='<h5>'+result[i].isCsd+'</h5>';
						}
						str+='</td>'; */
						
					str+='</tr>';
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
	}
	
	$("#"+divId).html(str);
	$('[data-toggle="tooltip"]').tooltip();
}
getDistricts();
function getDistricts(){
	var jsObj ={ 
	               stateId : 1,
				   stateTypeId : globalStateId
				 };
	  $.ajax({
			type : 'POST',
			url : 'getDistrictByStateIdAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		   if( result != null && result.length>0){
			   buildDistrictsForActivityCounts(result);
		   }
		});
}
function buildDistrictsForActivityCounts(result){
	$("#districtId").append("<option value='0'>All</option>");        
      for(var i in result){
        $("#districtId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
      }
	  // $("#districtId").append('<option value="517"> Vishakapattanam-Rural</option>');
}
function getDistrictWiseActivityCounts(activityScopeId,districtId,type,searchType,refresh,acvtyNm,levlNm,loctnNm){
	$("#activityId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>')
	$("#locatnNamId").text('');
	if(activityScopeId == 0){
		var t = type.split("-");
		activityScopeId = parseInt(t[0]);
		districtId = parseInt(t[1]);
		type="all";
		globalActvtyScopeId = activityScopeId;
		acvtyNm = t[2];
		levlNm = t[3];
		//loctnNm = t[4];
	}
	if(refresh == "onload"){
		$("#constncyDivId").hide();
		$("#mandalDivId").hide();
		$("#villgWardDivId").hide();
		//$("#districtId").val(0);
		$("#districtId").val(districtId);
		loctnNm =$('#districtId option:selected').text();
	}
	//$("#activityId").html('<div style="text-align: center" ><img src="./images/Loading-data.gif" /></div>');
	$("#myModelActivityId").modal('show');
	//$("#activityId").html("");
	var radioVal = $('input[name=radioBtn]:checked').val();
	
	var jsObj={
		districtId : districtId,
		activity_scope_id:activityScopeId,
		search_type :searchType,
		stateId : globalStateId,
		activityMemberId : globalActivityMemberId,
		userTypeId : globalUserTypeId,
		showType : radioVal
	}	
	$.ajax({
	 type: "POST",
	 url: "getDistrictWiseActivityCountsAction.action",
	 data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		$("#activityId").html('');
		//if(result != null && result.length > 0){
			buildDistrictWiseActivitiesCount(result,type,refresh,acvtyNm,levlNm,loctnNm,searchType,activityScopeId,radioVal,radioVal);
		//}else{
		//		$("#activityId").html('<div style="text-align: center;font-weight:bold;" > No data available...</div>');
		//}
	});
}

function buildDistrictWiseActivitiesCount(result,type,refresh,acvtyNm,levlNm,loctnNm,searchType,activityScopeId,radioVal,radioVal){
	var str = '';
	var notUpdatedCount ;
	 if(refresh == "onload"){
		 $("#myModelActivityhead").text(acvtyNm);
		 $("#smallHeadngId").text(levlNm);
	}
	if(loctnNm =='All')
		$("#locatnNamId").text('ALL DISTRICT DETAILS ');
	else
		$("#locatnNamId").text(loctnNm +' DISTRICT  DETAILS');
	if(type == "count")
		$("#myModelActivityId").modal('show');
	else if(type == "all")
		$("#myModelActivityId").modal('show');
	//$("#activityId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');	
	if(result != null && result.length > 0){
	 str +='<table class="table table-bordered table-condensed " id="activityTableId">';
	 str+='<thead style="background-color:#EEE">';
	str +='<tr>';
	if(searchType == "constituency"){
		str +='<th class="text-capital">Constituency name</th>';
	}else if(searchType == "mandal"){
		str +='<th class="text-capital">Mandal/Town/Division name</th>';
	}else if(searchType == "villageWard" ||  searchType == "onlyvillage"){
		str +='<th class="text-capital">Village/Ward name</th>';
	}

	if((globalActivityLvlId != 1 || searchType != "villageWard") && (globalActivityLvlId != 1 ||  searchType != "onlyvillage")){
		str +='<th class="text-capital">total</th>';
	}
	str +='<th class="text-capital"> attended count </th>';
	//str +='<th class="text-capital">Planned</th>';
	/* if(searchType == "constituency" ||  searchType == "mandal")
		str +='<th class="text-capital">conducted</th>'; */
	str +='<th class="text-capital">Yes</th>';
	str +='<th class="text-capital">No</th>';
	str +='<th class="text-capital">Maybe</th>';
	str +='<th class="text-capital">Not updated</th>';
    str +='<th class="text-capital">infocell</th>';
	str +='<th class="text-capital">ivr</th>';
	str +='<th class="text-capital">images covered</th>';
	str +='<th class="text-capital">Total images</th>';
  str +='</tr>';
  str+='</thead>';
  str+='<tbody>';
  for(var i in result){
  str +='<tr>';
	str +='<td id="'+result[i].id+'">'+result[i].name+'</td>';
	if((globalActivityLvlId != 1 || searchType != "villageWard") && (globalActivityLvlId != 1 ||  searchType != "onlyvillage")){
		str +='<td>'+parseInt(result[i].totalCount)+'</td>';
	}
	//str +='<td>'+result[i].inviteeCount+'</td>';
	if(result[i].activityAttendedCount ==null || result[i].activityAttendedCount ==0)
		str +='<td> - </td>';
	else
		str +='<td>'+result[i].activityAttendedCount+'</td>';
	/* if(searchType == "constituency" ||  searchType == "mandal"){
		if(parseInt(result[i].conductedCount) > parseInt(result[i].attendedCount))
			str +='<td>'+result[i].attendedCount+'</td>';
		else if(parseInt(result[i].attendedCount) == 1 && radioVal != "NotConducted")
			str +='<td>'+result[i].attendedCount+'</td>';
		else
			str +='<td>'+result[i].conductedCount+'</td>';
	} */
	
	if(parseInt(result[i].inviteeNotAttendedCount) == 0 || parseInt(result[i].inviteeNotAttendedCount) == 0){
		
	if(parseInt(result[i].attendedCount) > 1 || radioVal == "NotConducted" )
		str +='<td> 0 </td>';
	else
		str +='<td>  1  </td>';
	
		str +='<td> 0 </td>'; 
		str +='<td> 0 </td>'; 
	}else{
		str +='<td>'+result[i].yesCount+'</td>';
		str +='<td>'+result[i].noCount+'</td>';
		str +='<td>'+result[i].mayBeCount+'</td>';
	}
	
	if(searchType == "constituency" ||  searchType == "mandal")
		notUpdatedCount = result[i].attendedCount-result[i].conductedCount;
	else
		notUpdatedCount = result[i].inviteeCount-result[i].conductedCount;
	
	if(radioVal=='NotConducted')
		str +='<td> '+parseInt(result[i].totalCount)+' </td>';
	else{
		if(notUpdatedCount < 0 )
			str +='<td> 0 </td>'; 
		else if(parseInt(result[i].attendedCount) == 1)
			str +='<td>  0 </td>';
		else 
			str +='<td>'+notUpdatedCount+'</td>'; 
	}
	
	
	if(parseInt(result[i].attendedCount) > 1 || radioVal == "NotConducted" )
		str +='<td>'+result[i].inviteeNotAttendedCount+'</td>';
	else
		str +='<td>  1  </td>';
	str +='<td>'+result[i].inviteeAttendedCount+'</td>';
	str +='<td>'+result[i].imagesCovered+'</td>';
	str +='<td>'+result[i].totalImages;
	 if(result[i].totalImages > 0){
		//str +='<i class="getPopUpImagesCls glyphicon glyphicon-camera" style="cursor:pointer;font-size:18px;margin-left:8px;"  attr_constituency_id ="'+result[i].id+'" attr_scope_id = "'+activityScopeId+'" attr_value="'+1+'" attr_search_type="'+searchType+'"title="View Images"></i>';
	}
	str +='</td>';
	  str +='</tr>';
  }
  str+='</tbody>';
str +='</table> ';
	}else{
		str +='No Data Available';
	}
//console.log(str)
$("#activityId").html(str);
//$("#activityTableId").dataTable({});
   $('#activityTableId').dataTable();
}
$(document).on("click",".modalCloseCls",function(){
	$("#constituencyId").empty();
	$("#mandalId").empty()
	$("#villgWardId").empty();
	$("#districtId").val(0);
});

$(document).on("click",".submitCls",function(){
	var searchType="constituency";
	var locationNm = "All";
	var locationId = 0;
	if($("#districtId").val() == 0){
			locationId = $("#districtId").val();
			searchType="constituency";
			locationNm = $('#districtId option:selected').text();
	}else{
		locationId = $("#districtId").val();
		searchType="constituency";
		locationNm = $('#districtId option:selected').text();
		if($("#constituencyId").val() == 0){
			locationId = $("#districtId").val();
			searchType="constituency";
			locationNm = $('#districtId option:selected').text();
		}else{
			if($("#mandalId").val() == 0){
				locationNm = $('#constituencyId option:selected').text();
				locationId = $("#constituencyId").val();
				searchType="mandal";
			}else{
				if($("#villgWardId").val() == 0){
					locationNm = $('#mandalId option:selected').text();
					locationId = $("#mandalId").val();
					searchType="villageWard";
				}else if($("#villgWardId").val() > 0){
					locationNm = $('#villgWardId option:selected').text();
					locationId = $("#villgWardId").val();
					searchType="onlyvillage";
				}
			}
		}
	}
	getDistrictWiseActivityCounts(globalActvtyScopeId,locationId,"change",searchType,"submit","NA","NA",locationNm);
	
});

$(document).on("change",".districtCls",function(){
	$("#constituencyId").val(0);
	$("#mandalId").val(0);
	$("#villgWardId").val(0);
	$("#mandalDivId").hide();
	$("#villgWardDivId").hide();
	var districtId = $(this).val();
	
	if(districtId == 0){
		//$("#mandalDivId").hide();
		//$("#villgWardDivId").hide();
		$("#constncyDivId").hide();
	}else if(globalActivityLvlId == 5 ){
		getConstituenciesForDistrict(districtId);
	}else if(globalActivityLvlId == 2 || globalActivityLvlId == 1){
		$("#constncyDivId").show();
		getConstituenciesForDistrict(districtId);
	}
	
});
$(document).on("change",".constituencyCls",function(){
	$("#mandalId").val(0);
	$("#villgWardId").val(0);
	var constId = $(this).val();
	if(constId == 0){
		$("#mandalDivId").hide();
		$("#villgWardDivId").hide();
	}else if(globalActivityLvlId == 1 ){
		$("#mandalDivId").show();
		getMandalsByConstituency(constId);
	}
	
});
$(document).on("change",".mandalsCls",function(){
	var constId = $("#constituencyId").val();
	$("#villgWardId").val(0);
	var mandalId = $(this).val();
	if(mandalId == 0){
		$("#villgWardDivId").hide();
	}else if(globalActivityLvlId == 1){
		getVillageWardByMandal(mandalId,constId);
	}
	
});
var globalActvtyScopeId;
var globalActivityLvlId;
$(document).on("click",".activityCountCls",function(){
	var activityScopeId = $(this).attr("attr_actvty_scope_id");
	globalActvtyScopeId = activityScopeId;
	var activityName = $(this).attr("attr_activity_name");
	var levelName = $(this).attr("attr_level_name");
	 globalActivityLvlId=$(this).attr("attr_level_id");
	 $("#myModelActivityId").modal('show');
	getDistrictWiseActivityCounts(activityScopeId,0,"count","constituency","onload",activityName,levelName,"All")
	
});


/*$(document).on("click",".activitesExpandIcon",function(){
	$("#eventsDistWiseCohort,#eventsGraphBlock").html(' ');
	$(".eventsListExpandIcon").find("i").addClass("glyphicon-fullscreen").removeClass(".glyphicon-resize-small");
    var activityId = $(this).attr("attr_id");
	$("#hiddenActivityId").val(activityId);	
	if($(this).find("i").hasClass("glyphicon-fullscreen"))
	{
		if($(".eventsIconExpand").find("i").hasClass("glyphicon-resize-small"))
		{
			//block opened
			//alert("block open")	
		}else{
			//block closed
			$(".eventsIconExpand").find("i").addClass("glyphicon-resize-small").removeClass("glyphicon-fullscreen");
			$(".eventsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".eventsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
		}
		//alert("open"); 
		$("#accordionAct").find(".panel-collapse").removeClass("in");
		$("#accordionAct").find(".panelBlockCollapseIcon").addClass("collapsed");
		//$(this).closest(".panel").find(".panel-collapse").addClass("in");
		//$(this).closest(".panel").find(".panelBlockCollapseIcon").removeClass("collapsed");
		var activityLevelIds=[];
		var activityId = $(this).attr("attr_id");
		var activityName = $(this).attr("attr_activity_name");
		var activityLevelId = $(this).attr("attr_level_id");
		 if(activityLevelId != null && activityLevelId > 0){
			activityLevelIds.push(activityLevelId); 
		 }
		 getUserTypeActivityConductedCnt(activityId,activityLevelIds);
		 activityName = activityName.replace("'","");
		 activityName = activityName.replace("\'","");
		 $(".eventAndActivityCls").html(activityName);
		$(".moreEventsBlocksIcon").addClass("acitivitiesMoreExpand");
		$(".dateRangePickerClsForEvents").removeClass("hide");
		$(".eventsHiddenBlock,.moreEventsBlocksIcon").show();
		var eventIdsString = $(this).attr("attr_event_idsString");
		$(".activitesExpandIcon").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen");
		$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		if($(".detailedBlockEvents").is(":visible"))
		{
			//alert("already opened");
			var activityId = $("#hiddenActivityId").val();
			districtWiseCohort(activityId);
		}
			
	}else{
		//alert("close")
		$(".eventsHiddenBlock,.moreEventsBlocksIcon").hide();
		$(".dateRangePickerClsForEvents").addClass("hide");
		$(".eventsHiddenBlock,.moreEventsBlocksIcon,.comparisonBlockEvents,.moreEventsBlocks").hide();
		$(".eventsIconExpand").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen");
		$(".eventsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".eventsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
		$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
	}
});*/
/* $(document).on("click",".activitesExpandIcon",function(){
	$(".activitesExpandIcon").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen")
	$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
	$(".moreEventsBlocksIcon").addClass("acitivitiesMoreExpand");
	$(".eventsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	$(".dateRangePickerClsForEvents").removeClass("hide");
	$(".eventsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
	//$("#hiddenActivityId").val(activityId);
	var eventIdsString = $(this).attr("attr_event_idsString");
	//$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
	$(".eventsHiddenBlock,.moreEventsBlocksIcon").show();
	
	if(!$(".eventsIconExpand").find("i").hasClass("glyphicon-resize-small"))
	{
		$(".eventsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".eventsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
	}
	$(".eventsHiddenBlock,.moreEventsBlocksIcon,.comparisonBlockEvents,.moreEventsBlocks").hide();
	
	if(!$(this).find("i").hasClass("glyphicon-resize-small"))
	{
		$(".eventsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".eventsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
		$(".eventsIconExpand").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen")
	}else{
		
		$(".stateLevelMeetingBlock").show();
		$(".eventsHiddenBlock,.moreEventsBlocksIcon").show();
		$(".eventsIconExpand").find("i").addClass("glyphicon-resize-small").removeClass("glyphicon-fullscreen")
	}
	
	if( $(".trainingIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".dateRangePickerClsForTraining").addClass("hide");
		$(".trainingIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".trainingsHiddenBlock,.moreTrainingBlocks,.moreTrainingBlocksIcon").hide();
		$(".moreTrainingBlocksIcon").removeClass("unExpandTrainingBlock");
		$(".trainingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}else if( $(".debatesIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".debatesIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".debatesMoreHiddenBlock,.debatesHiddenBlock,.dateRangePickerClsForDebates").hide();
		$(".moreDebatesBlocksIcon").removeClass("unExpandDebatesBlock");
		$(".debatesBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}else if( $(".iconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".iconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".committeesHiddenBlock,.moreBlocks,.moreBlocks1,.moreBlocksDetailAndComp,.moreBlocksIcon,.moreBlocksDistrictlevel").hide();
		$(".committeesBlock,.basicCommitteesBlock,.userTypeCommitteesBlock,.committeesBlock1").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".dateRangePickerCls").toggleClass("hide");
		$(".moreBlocksIcon").removeClass("unExpandBlock");
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
}); */
$(document).on("click",".acitivitiesMoreExpand",function(){
	$("#eventsCmpBlckDivId").find("ul li:first-child").addClass("active");
	$("#activitesCmpBlockDivId").show();
	$(".moreEventsBlocks").show();
	$(this).removeClass("acitivitiesMoreExpand");
	$(".moreActivitiesBlocks").toggle();
	var activityId = $("#hiddenActivityId").val();
		  $(".moreActivitiesBlocks").toggle();
		    stateWiseCohort(activityId); //srujana
			districtWiseCohort(activityId);
			levelWiseSBData(activityId);//sanjeev
			//activitiesQuestions(activityId);
				$(".detailedBlockEvents,.activeUlCls").show();
			   $(".detailedEvent").addClass("active")	
				$(".comparisonActivity").removeClass("active");
				$("#evntCmpBLockId").hide();
			
		//globalActivityIdsList
});

function levelWiseSBData(divId)
{
	levelWiseSBArr=['district','parliament','constituency'];
	var collapse='';
		collapse+='<section>';
			collapse+='<div class="row">';
			
				for(var i in levelWiseSBArr)
				{
					collapse+='<div class="col-sm-12">';
					collapse+='<div class="panel-group" id="accordion'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" role="tablist" aria-multiselectable="true">';
						collapse+='<div class="panel panel-default panel-black">';
							collapse+='<div class="panel-heading" role="tab" id="heading'+divId+''+levelWiseSBArr[i]+'">';
								if(i == 0)
								{
									collapse+='<a role="button" class="collapseDebatesIcon '+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'"  data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" href="#collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'">';
								}else{
									collapse+='<a role="button" class="collapseDebatesIcon collapsed '+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'"  data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" href="#collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'">';
								}
								if(levelWiseSBArr[i] == "state" || levelWiseSBArr[i] == "district" || levelWiseSBArr[i] == "constituency")
									collapse+='<h4 class="panel-title text-capital">'+levelWiseSBArr[i]+' level overview</h4>';
								else
									collapse+='<h4 class="panel-title text-capital">'+levelWiseSBArr[i]+' level overview</h4>';
									
								collapse+='</a>';
							collapse+='</div>';
							if(i == 0)
							{
								collapse+='<div id="collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'">';
							}else{
								collapse+='<div id="collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'">';
							}
							
								collapse+='<div class="panel-body">';
									collapse+='<div id="'+levelWiseSBArr[i]+'"></div>';
								collapse+='</div>';
							collapse+='</div>';
						collapse+='</div>';
					collapse+='</div>';
					collapse+='</div>';
				}
			
			collapse+='</div>';
			collapse+='</section>';
	
	$("#levelWiseOverviewId").html(collapse);
	
	
	setTimeout(function(){ 
		for(var i in levelWiseSBArr){
			getSettingActivitiesJBMData(levelWiseSBArr[i],divId);
		}	
	
	}, 1000);
	
}
function getSettingActivitiesJBMData(locationId,divId){
	$("#"+locationId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var locationTypeId =0;
	if(locationId == 'district'){
		locationTypeId =3
	}else if(locationId == 'constituency'){
		locationTypeId =4
	}else if(locationId == 'parliament'){
		locationTypeId =10
	}

	var jsObj={
		fromDate : '',
	    toDate : '',
		activityId:divId,
		locationScopeId:locationTypeId,
	}	
	$.ajax({
	 type: "POST",
	 url: "getLocationWiseJBDataAction.action",
	 data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0){
			buildActivityEventdata(result,locationId);
		}else{
			$("#"+locationId).html('NO DATA AVAILABLE');
		}
	});
}
function buildActivityEventdata(result,locationId){
	var tableView='';
	
	tableView+='<div class="table-responsive">';
		tableView+='<table class="table table-bordered" id="dataTable1'+locationId+'" style="width:100%;border:1px solid lightgrey">';
			tableView+='<thead class="text-capital">';
				tableView+='<tr>';
					if(locationId == 'district'){
						tableView+='<th rowspan =2>District</th>';
					}else if(locationId == 'constituency'){
						tableView+='<th rowspan =2>districtName</th>';
						tableView+='<th rowspan =2>Assembly Constituency</th>';
					}else if(locationId == 'parliament'){
						tableView+='<th rowspan =2>Parliment Constituency</th>';
					}
					tableView+='<th rowspan ="2">Total Panchayaths/ Wards</th>';
					tableView+='<th colspan="2">Info Cell Conducted</th>';
					//tableView+='<th rowspan="2">Conducted%</th>';
					for(var i in result[0].questionList){
						if(result[0].questionList[i].optionList.length==2 && result[0].questionList[i].questionId != 23){
							tableView+='<th colspan="2">'+result[0].questionList[i].questionName+'</th>';
						}else if(result[0].questionList[i].optionList.length==2 && result[0].questionList[i].questionId == 23){
							tableView+='<th colspan="5">'+result[0].questionList[i].questionName+'</th>';
						}else if (result[0].questionList[i].optionList.length==1){
							if(result[0].questionList[i].questionId != 21){
								tableView+='<th rowspan ="2">'+result[0].questionList[i].questionName+'</th>';
							}else{
								tableView+='<th rowspan ="2">'+result[0].questionList[i].questionName+'-(Minutes)</th>';
							}
						}else{
							tableView+='<th colspan="'+result[0].questionList[i].optionList.length+'">'+result[0].questionList[i].questionName+'</th>';
						}
						
					}
				tableView+='</tr>';
				tableView+='<tr>';
					tableView+='<th>Total Count</th>';
					tableView+='<th>%</th>';
				for(var i in result[0].questionList){
						if(result[0].questionList[i].optionList.length==2 && result[0].questionList[i].questionId != 23){
							
							tableView+='<th>Yes Count</th>';
							tableView+='<th>%</th>';
						}else if(result[0].questionList[i].optionList.length==2 && result[0].questionList[i].questionId == 23){
							tableView+='<th>Total Count</th>';
							tableView+='<th>Yes Count</th>';
							tableView+='<th>%</th>';
							tableView+='<th>No Count</th>';
							tableView+='<th>%</th>';
						}
						if(result[0].questionList[i].optionList.length !=2 && result[0].questionList[i].optionList.length !=1){
							for(var j in result[0].questionList[i].optionList){
								tableView+='<th>'+result[0].questionList[i].optionList[j].optionName+'</th>';
						}
						
					}
					//tableView+='<th></th>';
				}
				tableView+='</tr>';
			tableView+='</thead>';
			tableView+='<tbody>';
				for(var i in result){
					tableView+='<tr>';
					if(locationId == 'constituency'){
						tableView+='<td>'+result[i].districtName+'</td>';
					}
					tableView+='<td>'+result[i].locationName+'</td>';
					tableView+='<td>'+result[i].totalCount+'</td>';
					tableView+='<td>'+result[i].conductedCount+'</td>';
					tableView+='<td>'+parseFloat((result[i].conductedCount/result[i].totalCount)*100).toFixed(2)+'%</td>';
					for(var j in result[i].questionList){
						if(result[i].questionList[j].optionList.length==2 && result[i].questionList[j].questionId == 23){
							tableView+='<td>'+result[i].questionList[j].count+'</td>';
						}
						 for(var k in result[i].questionList[j].optionList){
							if(result[i].questionList[j].optionList.length==2 && result[i].questionList[j].questionId != 23){
								if(result[i].questionList[j].optionList[k].optionId ==1){
									tableView+='<td>'+result[i].questionList[j].optionList[k].count+'</td>';yesCount=result[i].questionList[j].optionList[k].count;
									if(result[i].conductedCount !==null && result[i].conductedCount !=0){
										tableView+='<td>'+parseFloat((result[i].questionList[j].optionList[k].count/result[i].conductedCount)*100).toFixed(2)+'%</td>';
									}else{
									tableView+='<td>-</td>';
									}
								}
							}else if(result[i].questionList[j].optionList.length==2 && result[i].questionList[j].questionId == 23){
								tableView+='<td>'+result[i].questionList[j].optionList[k].count+'</td>';
							 	if(result[i].conductedCount !==null && result[i].conductedCount !=0 ){
									tableView+='<td>'+parseFloat((result[i].questionList[j].optionList[k].count/result[i].questionList[j].count)*100).toFixed(2)+'%</td>';
								}else{
								tableView+='<td>-</td>';
								} 
							}else if (result[i].questionList[j].optionList.length==1){
								if(result[i].questionList[j].questionId !=21){
									tableView+='<td>'+result[i].questionList[j].optionList[k].percentage+'</td>';
								}else{
									tableView+='<td>'+parseInt(result[i].questionList[j].optionList[k].count/(result[i].conductedCount))+'</td>';
								}
								
							}else{
								tableView+='<td>'+result[i].questionList[j].optionList[k].count+'</td>';
							}
						}
						
					}
					tableView+='</tr>';
				}
			tableView+='</tbody>';
		tableView+='</table>';
	tableView+='</div>';
	$("#"+locationId).html(tableView);
	
	$("#dataTable1"+locationId).dataTable({
			"iDisplayLength": 15,
			"aaSorting": [],
			"order": [ 0, 'asc' ],
			"dom": "<'row'<'col-sm-4'l><'col-sm-6'f><'col-sm-2'B>>" +
			"<'row'<'col-sm-12'tr>>" +
			"<'row'<'col-sm-5'i><'col-sm-7'p>>",
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
			buttons: [
				{
					extend		:'csvHtml5',
					text		:'<i class="fa fa-file-text-o"></i>',
					titleAttr	: 'CSV',
					title		:  "ENC WORKS DASHBOARD",
					filename	:  locationId+''+moment().format("DD/MMMM/YYYY  HH:MM"),
				}
			]
		});

}

function districtWiseCohort(activityId){
	$("#eventsDistWiseCohort1").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	globalActivityIdsList = activityId.split(',');
	var dates = $('#dateRangeIdForEvents').val();
	  var dateArray = dates.split("-");
	  var fromDateStr=dateArray[0];
	  var toDateStr=dateArray[1];
	var  scopeId = 3;
	var jsObj ={
	   activityId : globalActivityIdsList,
	   fromDate : '01/02/2015',//fromDateStr,//customStartDateActivities,
	   toDate : '01/02/2020',//toDateStr,//customEndDateActivities,
	   scopeId : scopeId,
	   activityMemberId : globalActivityMemberId,
	   stateId : globalStateId,
	   userTypeId : globalUserTypeId
		 
	  }
	$.ajax({
		type : 'POST',
		url : 'activitiesDistrictWiseCohortAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		buildDistrictWiseCohort(result);
	});
}
 function buildDistrictWiseCohort(result)
{

	var str='';
	$('.activitiesH4').html(result[0].partyName);
	for(var i in result)
	{	
		globalActivityLvlId=result[i].apTotal;
		str+='<h4 class="panel-title"><span class="headingColor">'+result[i].name+'</span></h4>';
		str+='<div id="eventsGraph1'+i+'" style="height:120px"></div>';
	}
	$("#eventsDistWiseCohort1").html(str)
	if(result != null && result.length > 0){
		
		for(var i in result){
			var conductedCounts = [];
			var nonConductedCounts = [];
			var candidateNames = [];
			var countVar =0;
			
			
			for(var j in result[i].distList){
				candidateNames.push(result[i].distList[j].locationName)
				//conductedCounts.push(parseFloat(result[i].distList[j].perc))
				//nonConductedCounts.push(parseFloat(result[i].distList[j].remainingPerc))
				
				conductedCounts.push({"y":parseFloat(result[i].distList[j].perc),"extra":result[i].id+"-"+result[i].distList[j].locationId+"-"+result[0].partyName+"_"+result[0].actualMobNumber+"_"+result[i].distList[j].locationName});
				nonConductedCounts.push({"y":parseFloat(result[i].distList[j].remainingPerc),"extra":result[i].id+"-"+result[i].distList[j].locationId+"-"+result[0].partyName+"_"+result[0].actualMobNumber+"_"+result[i].distList[j].locationName});
			}
			
			$(function () {
				 $("#eventsGraph1"+i).highcharts({
					colors: ['#64C664','#D33E39'],
					chart: {
						type: 'column'
					},
					title: {
						text: ''
					},
					subtitle: {
						text: ''
					},
					xAxis: {
						min: 0,
						gridLineWidth: 0,
						minorGridLineWidth: 0,
						categories: candidateNames,
						type: 'category',
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
							text: ''
						},
						labels: {
							enabled:false
						}
					},
					legend: {
						enabled: false
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
										return Highcharts.numberFormat(this.percentage,1) + '%';
									}
								}
							  
							}
						},
						series: {
						cursor: 'pointer',
						point: {
							events: {
								click: function () {
									getDistrictWiseActivityCounts(0,0,this.extra,"constituency","onload")
								}
							}
						}  
				     },
				 
					},
					
					 tooltip: {
						 pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.percentage:.1f}%</b><br/>',
						/* formatter: function () {
							var s = '<b>' + this.x + '</b>';

							$.each(this.points, function () {
								s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
									Highcharts.numberFormat(this.percentage,1)+'%' +' - ' +
									(this.y);
							});

							return s;
						}, */
						shared: true
					},

					series: [{
						name: 'Conducted',
						data: conductedCounts,
						
					},{
						name: 'Not Conducted',
						data: nonConductedCounts,
						
					}],
				 
				});
			});
		}
	}else{
		$("#eventsDistWiseCohort1").html("No Data Available");
	}
}
//srujana
function stateWiseCohort(activityId){
	$("#eventsGraphBlock1").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	globalActivityIdsList = activityId.split(',');
	var  scopeId = 2;
	
	var dates = $('#dateRangeIdForEvents').val();
	  var dateArray = dates.split("-");
	  var fromDateStr=dateArray[0];
	  var toDateStr=dateArray[1];
	
	var jsObj ={
	   activityId : globalActivityIdsList,
	   //fromDate : fromDateStr,//customStartDateActivities,
	   //toDate : toDateStr,//customEndDateActivities,
	   fromDate : '01/02/2015',
	   toDate : '01/02/2020',
	   scopeId : scopeId,
	   activityMemberId : globalActivityMemberId,
	   stateId : globalStateId,
	   userTypeId : globalUserTypeId
		 
	  }
	$.ajax({
		type : 'POST',
		url : 'activitiesDistrictWiseCohortAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		buildStateWiseCohort(result);
	});
}

 function buildStateWiseCohort(result)
{
	var str=' ';
	if(result.length > 3)
	{
		str+='<div class="scroll-div">';
	}
		str+='<ul class="list-inline best-matched-profile">';
			for(var i in result)
			{
				str+='<li><h4>'+result[i].name+'</h4>';
				str+='<div id="events1'+i+'" class="chartLi"></div></li>';
			}
			
		str+='</ul>';
	if(result.length > 3)
	{
		str+='</div>';
	}
	
	$("#eventsGraphBlock1").html(str);

	for(var i in result)
	{
		var conductedCounts = [];
			var nonConductedCounts = [];
			var candidateNames = [];
			var countVar =0;
			
			
			for(var j in result[i].distList){
				candidateNames.push(result[i].distList[j].locationName)
				//conductedCounts.push(parseFloat(result[i].distList[j].perc))
				//nonConductedCounts.push(parseFloat(result[i].distList[j].remainingPerc))
				
				conductedCounts.push({"y":parseFloat(result[i].distList[j].perc),"extra":result[i].id+"-"+result[i].distList[j].locationId+"-"+result[0].partyName+"_"+result[0].actualMobNumber+"_"+result[i].distList[j].locationName});
				nonConductedCounts.push({"y":parseFloat(result[i].distList[j].remainingPerc),"extra":result[i].id+"-"+result[i].distList[j].locationId+"-"+result[0].partyName+"_"+result[0].actualMobNumber+"_"+result[i].distList[j].locationName});
			}
		
		$(function () {
			$('#events1'+i+'').highcharts({
				colors: ['#64C664','#D33E39'],
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
					categories: ['Event'],
					labels: {
						enabled: false,
					}
				},
				yAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: null
					},
					stackLabels: {
						enabled: false,
						style: {
							fontWeight: 'bold',
							color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
						}
					}
				},
				tooltip: {
					pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.percentage:.1f}%</b><br/>',
					shared: true
				},
				legend: {
					enabled: true,
					align: 'left'
			
				},
				plotOptions: {
					column: {
						stacking: 'normal',
						dataLabels:{
							enabled: true,
							formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return Highcharts.numberFormat(this.y,1) + '%';
								}
							}
						},
						
					},
						/* series: {
						cursor: 'pointer',
						point: {
							events: {
								click: function () {
									getDistrictWiseActivityCounts(0,0,this.extra,"constituency","onload")
								}
							}
						}  
				     }, */
				},
				 series: [{
					name: 'Conducted',
					data: conductedCounts 
				}, {
					name: 'Not Conducted',
					data: nonConductedCounts
				}]
			});
		});	
	}
	
}
/* Activities Functionality End */


function activitiesQuestions(activityId){
	var jsObj ={ 
	               activityId : activityId,
				   activityScopeId : activityId,
				   fromDateStr :"",
				   endDateStr : ""
				 }
	  $.ajax({
			type : 'POST',
			url : "getActivitiesQuesDetailsAction.action",
			data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			buildActivitiesQuesDetails(result);
		   //buildDistrictWiseActivitiesCount(result);
		});
}
function buildActivitiesQuesDetails(result)
{
	var str='';
	for(var i in result.activityVoList)
	{
		str+='<h4>'+result.activityVoList[i].activityLevelName+'</h4>';
		str+='<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">';
		for(var j in result.activityVoList[i].activityVoList)
		{
			str+='<div class="panel panel-default">';
				str+='<div class="panel-heading" role="tab" id="headingOne'+j+'">';
					str+='<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne'+j+'" aria-expanded="true" aria-controls="collapseOne'+j+'">';
						str+='<h4 class="panel-title">'+result.activityVoList[i].activityVoList[j].question+'</h4>';
					str+='</a>';
				str+='</div>';
				str+='<div id="collapseOne'+j+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne'+j+'">';
					str+='<div class="panel-body">';
						str+='<div class="row">';
							/*str+='<div class="col-md-6 col-xs-12 col-sm-6">';
								str+='<h4 class="panel-title">IVR REPORT</h4>';
								str+='<div id="reportsGraph'+j+'"></div>';
							str+='</div>';*/
							str+='<div class="col-md-6 col-xs-12 col-sm-6">';
								str+='<h4 class="panel-title">INFOCELL REPORT</h4>';
								str+='<div id="reportsGraph'+j+'"></div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		}
		str+='</div>';
	}
	$("#eventsDistWiseCohort2").html(str)
	
	for(var i in result.activityVoList)
	{
		for(var j in result.activityVoList[i].activityVoList)
		{
			var str ='';
			str+='<table class="table table-bordered table-condensed">';
			str+='<thead>';
			str+='<tr>';
			for(var k in result.activityVoList[i].activityVoList[j].optionsList)
			{
				str+='<th style="text-align:center;background-color:#B0C4DE;">'+result.activityVoList[i].activityVoList[j].optionsList[k].option+'</th>';
			}
			str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
			str+='<tr>';
			for(var k in result.activityVoList[i].activityVoList[j].optionsList)
			{
				str+='<td style="text-align:center;">'+result.activityVoList[i].activityVoList[j].optionsList[k].remainingPerc+'</td>';
			}
			str+='</tr>';
			str+='</thead>';
			str+='</table>';
			
			$("#reportsGraph"+j).html(str);
		}
	}
	/*
	for(var i in result.activityVoList)
	{
		for(var j in result.activityVoList[i].activityVoList)
		{
			
			for(var k in result.activityVoList[i].activityVoList[j].optionsList)
			{
				var Categories =  [{
					name:'XX',
					data : categoriesSub
				}];
				var categoriesSub = []
				categoriesSub.push(result.activityVoList[i].activityVoList[j].optionsList[k].option);
				categoriesSub.push(result.activityVoList[i].activityVoList[j].optionsList[k].percentage);
				//Categories.push(categoriesSub);
			}
			console.log(Categories);
			$("#reportsGraph"+j).highcharts({
				chart: {
					type: 'pie',
					options3d: {
						enabled: true,
						alpha: 45
					}
				},
				title: {
					text: null
				},
				subtitle: {
					text: null
				},
				plotOptions: {
					pie: {
						innerSize: 100,
						depth: 45
					}
				},
				series: Categories
			});
		}
	}
	*/
}

var globallocationScope;
var globalPopupresult = "";
var globlbuildType="dayswise";
var isBuildDate=false;
$(document).on("click",".getImageCls",function(){

	$("#myModalImageId").modal("show");
	var attr_activity_scopeid = $(this).attr('attr_activity_scopeid');
	var activityLevelId = $(this).attr('attr_level_id');
	var activity_name = $(this).attr('activity_name');
	globlbuildType =  $(this).attr('type');
	$("#hiddenActivityScopeId").val(attr_activity_scopeid);
	$("#hiddenActivityLevelId").val(activityLevelId);
	
	if(activity_name != null && activity_name.length>0)
		isBuildDate = false;
	
	if(!isBuildDate){
		isBuildDate = true;
		isWeeksBuild = false;
			$("#buildPoupupImage").html('');
		if(activity_name != null && activity_name.length>0){
		   $('#myModalLabelId').html(activity_name.replace("\'","").replace("\'",""));		
			//getDistrictNames($(this).attr('activity_name').replace("\'","").replace("\'",""),'','');
		}else{
			//getDistrictNames($(this).attr('activity_name').replace("\'","").replace("\'",""),'','');
		}
				var str='';
					str+='<div class="row">';
						str+='<div class="col-md-9">';
							str+='<nav class="navbar navbar-default navbarCollapseCustom">';
								str+='<div class="collapse navbar-collapse " id="bs-example-navbar-collapse-1">';
								
								 str+='<div class="row"  style="margin-top:5px;margin-bottom:5px;margin-left: 600px;">';
									 str+='<select id="daysListId">';
									 //  str+='<option value="2017-03-31 to 2017-04-06"> March: Week-1 (2017-03-31 to 2017-04-06) </option>';
									  // str+='<option value="2017-03-31 to 2017-04-06"> March: Week-2 (2017-03-31 to 2017-04-06) </option>';
									   //str+='<option value="2017-03-31 to 2017-04-06"> March: Week-3 (2017-03-31 to 2017-04-06) </option>';
									  //str+=' <option value="2017-03-31 to 2017-04-06"> March: Week-4 (2017-03-31 to 2017-04-06) </option>';
									 str+='</select>';
								 str+='</div>';
								 
								  str+='<div id="popupDaysDiv1" ></div>';
								 
								str+='</div>';
							str+='</nav>';
							str+='<div class=" pad_10" id="popupImages">';
								
							str+='</div>';
							str+=' <div id="paginationDivId"></div>';
						str+='</div>';
						str+='<div class="col-md-3" style="box-shadow:0 2px 10px 0 rgba(0, 0, 0, 0.35);padding:0px">';
							str+='<div id="districtsUlId"></div>';
						str+='</div>';
					str+='</div>';

				$("#buildPoupupImage").html(str);
	}
	
	
	
//buildDayWiseImagesForPopup(globalPopupresult,$(this).attr("imgpath"),$(this).attr("dayattr"));
//getAvailableDates(globallocationScope,globallocationValue,day,path);
globalActivityScope = attr_activity_scopeid;
//getAvailablDates('state',1,1,'',attr_activity_scopeid)
//buildLocationForPopup(globallocationScope,globallocationValue,attr_activity_scopeid);
//getEventsDocuments("","",attr_activity_scopeid);
getEventDocumentForPopup("district",1,0,0,'',attr_activity_scopeid,"state",1,"firstClick",activity_name);
	 
});
//var globallocationValue = 0;

 $(document).on('click','.dayssCls',function(){
	 $(".dayssCls").removeClass("active" )
	 $(this).addClass("active");
	 var day = $(this).attr("attr");
	 var locationScope = $(this).attr("locationScope");
	 var locationScopeValue = $(this).attr("locationScopeValue");
	 var attr_activity_scopeid = $(this).attr("attr_activity_scopeid");
	 var path = $(this).attr("path");
	 var date= $(this).attr("dateValue");
	 
	 var date = date;
	 var newdate = date.split("-").reverse().join("-");
	 
		getEventDocumentForPopup(GlobalPopupScope,GlobalPopuplocation,day,0,path,attr_activity_scopeid,locationScope,locationScopeValue,"");
		//getTempAvailablDates(GlobalPopupScope,GlobalPopuplocation,0,'',attr_activity_scopeid,newdate,newdate);
		getDistrictNames('',newdate,newdate);	
  });
  /* not using updated by srishailam */
function getEventsDocuments(divId,Obj,attr_activity_scopeid)
{
		var jObj = {
		activityId:attr_activity_scopeid,
		locationScope:"state",
		locationValue:1,		
		day:1,
		//fromDateStr:"01-01-2015",
		//toDateStr:"22-01-2017",
		fromDateStr : '01-02-2015',
	    toDateStr : '01-02-2020',
		locationName:Obj.locationName,
		startIndex:0,
		maxIndex:0,
		callFrom:"BD",
		activityMemberId : globalActivityMemberId,
	    stateId : globalStateId,
	    userTypeId : globalUserTypeId,
		task:"daywise"
		};
		/* var jObj = {
		activityId:Obj.activityScopeId,
		locationScope:Obj.searchType,
		locationValue:Obj.locationId,		
		day:0,
		fromDateStr:fromDateStr,
		toDateStr:toDateStr,
		locationName:Obj.locationName,
		startIndex:0,
		maxIndex:0,
		callFrom:Obj.callFrom,
		task:"daywise"
		}; */
	
		$.ajax({
          type:'GET',
          url: 'getEventDocumentsAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			//$('#'+divId+'').html('');
			globallocationScope = '';
			globallocationValue = '';
			globallocationName = '';
			globalActivityScope ='';
			globallocationScope = jObj.locationScope;
			globallocationValue = jObj.locationValue;
			globallocationName = jObj.locationName;
			globalActivityScope = jObj.activityId;
			//buildDayWiseImages(result,divId);
			
			});
}

function getAvailablDates(locationScope,locationValue,day,path,attr_activity_scopeid)
	{
	
	  $("#popupDaysDiv1").html('');
	 /* var dates = $('.dateRangeIdForEvents').val();
	  var dateArray = dates.split("/");
	  var fromDateStr=dateArray[0];
	  var toDateStr=dateArray[1];*/
	  var activityScopeId = $("#ActivityList").val();
		globallocationScope=locationScope;
		globallocationValue=locationValue
		var jObj = {
		activityId:attr_activity_scopeid,
		locationScope:locationScope,
		locationValue:locationValue,	
		fromDateStr:"",
		toDateStr:"",
		activityMemberId : globalActivityMemberId,
	    stateId : globalStateId,
	    userTypeId : globalUserTypeId,
		task:""
		};
		$.ajax({
          type:'GET',
          url: 'getAvailableDatesForActivitiesAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
				var str ='';
			
				var totalImagesCount=0;
				var coveredLocations=0;
				
				for(var i in result)
				{
					totalImagesCount=totalImagesCount+parseInt(result[i].totalResult);
					coveredLocations=coveredLocations+parseInt(result[i].coveredCount);
				}
				str+='<ul class="slickApplyPopupDays" style="width:810px;">';
				for(var i in result)
				{ 
					/*if(i==0 && (globlbuildType =='dayswise')){
						str+='<li class="" ><a href="javascript:{};" class="getImageCls" activity_name=""  type="overAll" locationScope="'+locationScope+'"   locationScopeValue="'+locationValue+'"  path = "'+path+'" attr_activity_scopeid="'+attr_activity_scopeid+'"  > OVER ALL <span class="sr-only">(current)</span></a></li>';
					}
					else if(i==0 && globlbuildType =='overAll'){
						str+='<li class=" " ><a href="javascript:{};" class="getImageCls"   activity_name=""   type="dayswise" locationScope="'+locationScope+'"   locationScopeValue="'+locationValue+'"  path = "'+path+'" attr_activity_scopeid="'+attr_activity_scopeid+'" > DAY WISE <span class="sr-only">(current)</span></a></li>';
					}
					*/
					
					if(i==0 && globlbuildType =='overAll'){
						str+='<li class="dayssCls" locationScope="'+locationScope+'"   locationScopeValue="'+locationValue+'"  dateValue="'+result[i].date+'" path = "'+path+'" attr_activity_scopeid="'+attr_activity_scopeid+'" attr="0"  ><a href="javascript:{};" > OVER ALL <br> (<span title="Total Images ">'+totalImagesCount+'</span> / <span title="Total Images Covered Locations ">'+coveredLocations+'</span>) <span class="sr-only">(current)</span><span class="sr-only">(current)</span></a></li>';
					}
						
					if(globlbuildType =='dayswise'){						
						if(result[i].id==day)
						{//attr_activity_scopeid,locationScope,locationScopeValue
							str+='<li class="active dayssCls"  locationScope="'+locationScope+'"   locationScopeValue="'+locationValue+'"  dateValue="'+result[i].date+'" path = "'+path+'" attr_activity_scopeid="'+attr_activity_scopeid+'" attr="'+result[i].id+'"><a href="javascript:{};">Day '+result[i].id+' (<span title="Total Images ">'+result[i].totalResult+'</span> / <span title="Total Images Covered Locations ">'+result[i].coveredCount+'</span>) <span class="sr-only">(current)</span></a></li>';
							/* getEventDocumentForPopup(jObj.locationScope,jObj.locationValue,day,0,path,attr_activity_scopeid,locationScope,locationValue); */
						}else if (i==0){
							str+='<li class="active dayssCls"  locationScope="'+locationScope+'"   locationScopeValue="'+locationValue+'"  dateValue="'+result[i].date+'" path = "'+path+'" attr_activity_scopeid="'+attr_activity_scopeid+'" attr="'+result[i].id+'"><a href="javascript:{};">Day '+result[i].id+' (<span title="Total Images ">'+result[i].totalResult+'</span> / <span title="Total Images Covered Locations ">'+result[i].coveredCount+'</span>) <span class="sr-only">(current)</span></a></li>';
							/* getEventDocumentForPopup(jObj.locationScope,jObj.locationValue,day,0,path,attr_activity_scopeid,locationScope,locationValue); */ 
						}else{
							str+='<li class="dayssCls" locationScope="'+locationScope+'" locationScopeValue="'+locationValue+'"  dateValue="'+result[i].date+'" attr_activity_scopeid="'+attr_activity_scopeid+'" attr="'+result[i].id+'"><a href="javascript:{};">Day '+result[i].id+' (<span title="Total Images ">'+result[i].totalResult+'</span> / <span title="Total Images Covered Locations ">'+result[i].coveredCount+'</span>) <span class="sr-only">(current)</span></a></li>';
						 }
					}
					else if(globlbuildType =='overAll' && i == 0 ){
						for(var j in result[i].documentsVOList)
						{
							
							if(result[i].documentsVOList[j].strDate==day)
							{//attr_activity_scopeid,locationScope,locationScopeValue
								str+='<li class="active dayssCls" locationScope="'+locationScope+'"   locationScopeValue="'+locationValue+'"  dateValue="'+result[i].documentsVOList[j].strDate+'" path = "'+path+'" attr_activity_scopeid="'+attr_activity_scopeid+'" attr="'+result[i].documentsVOList[j].day+'"><a href="javascript:{};"> Day '+result[i].documentsVOList[j].day+' <br>  '+result[i].documentsVOList[j].strDate+'  (<span title="Total Images ">'+result[i].documentsVOList[j].totalResult+'</span> / <span title="Total Images Covered Locations ">'+result[i].documentsVOList[j].coveredCount+'</span>) <span class="sr-only">(current)</span></a></li>';
								/* getEventDocumentForPopup(jObj.locationScope,jObj.locationValue,day,0,path,attr_activity_scopeid,locationScope,locationValue); */
							}else if (j==0){
								str+='<li class="active dayssCls"  locationScope="'+locationScope+'"   locationScopeValue="'+locationValue+'"  dateValue="'+result[i].documentsVOList[j].strDate+'" path = "'+path+'" attr_activity_scopeid="'+attr_activity_scopeid+'" attr="'+result[i].documentsVOList[j].day+'"><a href="javascript:{};"> Day '+result[i].documentsVOList[j].day+' <br>'+result[i].documentsVOList[j].strDate+'  (<span title="Total Images ">'+result[i].documentsVOList[j].totalResult+'</span> / <span title="Total Images Covered Locations ">'+result[i].documentsVOList[j].coveredCount+'</span>) <span class="sr-only">(current)</span></a></li>';
								/* getEventDocumentForPopup(jObj.locationScope,jObj.locationValue,day,0,path,attr_activity_scopeid,locationScope,locationValue); */ 
							}else{
								str+='<li class="dayssCls" locationScope="'+locationScope+'" locationScopeValue="'+locationValue+'"  dateValue="'+result[i].documentsVOList[j].strDate+'" attr_activity_scopeid="'+attr_activity_scopeid+'" attr="'+result[i].documentsVOList[j].day+'"><a href="javascript:{};">Day '+result[i].documentsVOList[j].day+' <br>'+result[i].documentsVOList[j].strDate+' (<span title="Total Images ">'+result[i].documentsVOList[j].totalResult+'</span> / <span title="Total Images Covered Locations ">'+result[i].documentsVOList[j].coveredCount+'</span>) <span class="sr-only">(current)</span></a></li>';
							 }
						}
					
					}
				}
				str+='</ul>';
				$("#popupDaysDiv1").html(str);
				$('.slickApplyPopupDays').slick({
					slide: 'li',
					slidesToShow: 3,
					slidesToScroll: 3,
					infinite: false,
					variableWidth:true
				});
				GlobalPopupScope = jObj.locationScope;
				GlobalPopuplocation =jObj.locationValue;
				
		});
}
//var globallocationName = '';
function buildLocationForPopup(locationScope,locationValue,ActivityScope)
{
	$("#myModalLabel").html(''+globallocationName+'');
	var subLeveldivId = '';
	if(locationScope == "state")
	subLeveldivId = "popupstateConstituencies"+locationValue;
		if(locationScope == "district")
		subLeveldivId = "popupdistConstituencies"+locationValue;
		if(locationScope == "constituency")
		subLeveldivId = "constiSubLevel"+locationValue;
		if(locationScope == "mandal")
		subLeveldivId= "mandalSubLevel"+locationValue;
		var str = '';
		str+='<div class="panel-group" id="accordionModal" role="tablist" aria-multiselectable="true">';
			
					   str+='<div class="panel panel-default panel-custommodal m_0">';
						  str+='<div class="panel-heading panel-headingModal popupLevel" role="tab" id="headinglevel'+locationValue+'Modal" attr='+locationValue+'>';
						  str+='<a role="button" class="accordionmodal-toggle collapsed" data-toggle="collapse" data-parent="#accordionModal" href="#collapselevel'+locationValue+'Modal" aria-expanded="true" aria-controls="collapselevel'+globallocationValue+'Modal">';
							 str+='<h4 class="panel-title popupTitle" attr="'+globallocationName+'">Anantapur District(157)';
							// str+='<h4 class="panel-title popupTitle" attr="'+globallocationName+'">Anantapur District('+globalTotalImages+')';
							 str+='</h4>';
						   str+='</a>';
						 str+='</div>';
						 str+='<div id="collapselevel'+locationValue+'Modal" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headinglevel'+locationValue+'Modal">';
						   str+='<div class="panel-body pad_0">';
							  str+='<div class="" id="'+subLeveldivId+'" style="margin-left:10px;">';
								// str+='<li><span class="line"/></span><a href="#">Constituency 1</a></li>';
							str+='</div>';
						   str+='</div>';
						 str+='</div>';
						 str+='</div>';
					
			  str+='</div>';
				$("#locationsPopup").html(str);
				
			
}

function getEventDocumentForPopup(searchType,locationId,day,num,path,attr_activity_scopeid,locationScope,locationScopeValue,calFrom, activity_name)
{
	 $("#popupImages").html('<img src="./images/Loading-data.gif" />');
	 var dates=$('.searchDateCls ').val();
/* 	  var dateArray=dates.split("/");
	  var fromDateStr=dateArray[0];
	  var toDateStr=dateArray[1];
	  if(dateArray.length == 1)
			{
				fromDateStr=" ";
				toDateStr=" ";
			} */
		globallocationScope=locationScope;
		globallocationValue=locationScopeValue;
		
		
		var jObj = {
		activityId:globalActivityScope,
		locationScope:locationScope,
		locationValue:locationScopeValue,		
		day:day,
		//fromDateStr:"01-01-2015",
		//toDateStr:"22-01-2017",
		fromDateStr : '01-02-2015',
	    toDateStr : '01-02-2020',
		type:"popup",
		startIndex:num,
		maxIndex:10,
		callFrom:calFrom,
		//locationName:obj.locationName,
		activityMemberId : globalActivityMemberId,
	    stateId : globalStateId,
	    userTypeId : globalUserTypeId,
		 task:"popupdaywise"
		};
				$.ajax({
          type:'GET',
          url: 'getEventDocumentsAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			buildDayWisImagesForPopup1(result,jObj,path,attr_activity_scopeid,locationScope,locationScopeValue,searchType,locationId, activity_name);
			});
}

var isWeeksBuild=false;
function buildDayWisImagesForPopup1(result,jObj,path,attr_activity_scopeid,locationScope,locationScopeValue,searchType,locationId, activity_name)
{
	$("#popupImages").html('');
	var datesArr = [];
	var str ='';
	$('.slider-for,.slider-nav').slick('unslick');
	if(result != null)
	{
		str+='<ul class="slider-for">';
		//if(path != null && path.length>0)
			//str+='<li><img src="https://mytdp.com/activity_documents/' +path+'"></li>';
			for(var i in result)
			{
				for(var j in result[i].subList)
				{					
					str+='<li> ';
					if(result[i].subList[j].address != null)
					str+='<div class="" style="margin-bottom: 5px;margin-left: -7px;margin-top: -8px;">';
						str+='<ul class="breadcrumb">';
						//if(result[i].subList[j].address.stateName != null && result[i].subList[j].address.stateName.trim().length>0)
						//	str+='<li>'+result[i].subList[j].address.stateName+'</li>';
						if(result[i].subList[j].address.districtName != null && result[i].subList[j].address.districtName.trim().length>0 ){
							if(locationScope=='district')
								str+='<li  class="text-primary"  title="'+result[i].subList[j].address.districtName+' District ">'+result[i].subList[j].address.districtName+' </li>';
							else
								str+='<li title="'+result[i].subList[j].address.districtName+' District ">'+result[i].subList[j].address.districtName+' </li>';
						}
						//if(result[i].subList[j].address.parliamentName != null && result[i].subList[j].address.parliamentName.trim().length>0 )
						//	str+='<li>'+result[i].subList[j].address.parliamentName+' Parliament </li>';
						if(result[i].subList[j].address.constituencyName != null && result[i].subList[j].address.constituencyName.trim().length>0 ){
							if(locationScope=='constituency')
								str+='<li  class="text-primary"  title="'+result[i].subList[j].address.constituencyName+' Assembly ">'+result[i].subList[j].address.constituencyName+' </li>';
							else
								str+='<li  title="'+result[i].subList[j].address.constituencyName+' Assembly ">'+result[i].subList[j].address.constituencyName+' </li>';
						}					
						if(result[i].subList[j].address.tehsilName != null && result[i].subList[j].address.tehsilName.trim().length>0 ){
							if(locationScope=='mandal')
								str+='<li  class="text-primary"  title="'+result[i].subList[j].address.tehsilName+' Mandal ">'+result[i].subList[j].address.tehsilName+' Mandal  </li>';
							else
								str+='<li  title="'+result[i].subList[j].address.tehsilName+' Mandal ">'+result[i].subList[j].address.tehsilName+' Mandal </li>';
						}					
						if(result[i].subList[j].address.townshipName != null && result[i].subList[j].address.townshipName.trim().length>0 ){
							if(locationScope=='mandal')
								str+='<li  class="text-primary"  title="'+result[i].subList[j].address.townshipName+' Mandal/Munci/Greater City " >'+result[i].subList[j].address.townshipName+' </li>';
							else
								str+='<li  title="'+result[i].subList[j].address.townshipName+' Mandal/Munci/Greater City  ">'+result[i].subList[j].address.townshipName+' </li>';
						}
						if(result[i].subList[j].address.panchayatName != null && result[i].subList[j].address.panchayatName.trim().length>0 ){
							if(locationScope=='village')
								str+='<li  class="text-primary"   title="'+result[i].subList[j].address.panchayatName+' Village  " >'+result[i].subList[j].address.panchayatName+' Village </li>';
							else
								str+='<li   title="'+result[i].subList[j].address.panchayatName+' Village  " >'+result[i].subList[j].address.panchayatName+' Village </li>';
						}
						if(result[i].subList[j].address.wardName != null && result[i].subList[j].address.wardName.trim().length>0 ){
							if(locationScope=='village')
								str+='<li  class="text-primary">'+result[i].subList[j].address.wardName+' </li>';
							else
								str+='<li>'+result[i].subList[j].address.wardName+' </li>';
						}
						
					str+='</ul>';
					str+='</div>';
					str+='<img src="https://mytdp.com/activity_documents/' +result[i].subList[j].path+'"></li>';
				}
				
				if(!isWeeksBuild){
					isWeeksBuild= true;
					$('#daysListId').find('option').remove();  
					$("#daysListId").append('<option value="0"> ALL </option>');				
					//$("#daysListId").append('<option value="0" attr_scope_id="'+globalActivityScope+'" > Select Week </option>');
					
					var maxlenght = result[i].subList2.length-1;
					
					for(var j in result[i].subList2){
						if(parseInt(j) == parseInt(maxlenght)){
							$("#daysListId").append('<option value="'+result[i].subList2[j].strDate+'" attr_scope_id="'+globalActivityScope+'" attr_location_scope_id="'+locationScope+'" attr_location_value="'+locationScopeValue+'" selected> Week-'+(parseInt(j)+1)+' ('+result[i].subList2[j].strDate+' ) </option>');	
						}else{
							$("#daysListId").append('<option value="'+result[i].subList2[j].strDate+'" attr_scope_id="'+globalActivityScope+'" attr_location_scope_id="'+locationScope+'" attr_location_value="'+locationScopeValue+'"> Week-'+(parseInt(j)+1)+' ('+result[i].subList2[j].strDate+' ) </option>');
						}
						
					}
						var weekDays = $("#daysListId").val();
						
						if(weekDays != null){
							datesArr = weekDays.split('to');
						}
						if(datesArr != null && datesArr.length==2){
							getTempEventDocumentForPopup(searchType,locationId,jObj.day,jObj.startIndex,path,attr_activity_scopeid,locationScope,locationScopeValue,jObj.callFrom,datesArr[0].trim(),datesArr[1].trim());
							getTempAvailablDates(globallocationScope,globallocationValue,0,'',globalActivityScope,datesArr[0].trim(),datesArr[1].trim());
							getDistrictNames(activity_name,datesArr[0].trim(),datesArr[1].trim());	
						}else{
							getTempEventDocumentForPopup(searchType,locationId,jObj.day,jObj.startIndex,path,attr_activity_scopeid,locationScope,locationScopeValue,jObj.callFrom,'','');
							getTempAvailablDates(globallocationScope,globallocationValue,0,'',globalActivityScope,'','');
							getDistrictNames('','','');	
						}
				}
			  
			}
			  str+='</ul>';
		str+='<ul class="slider-nav m_top20">';	
		//if(path != null && path.length>0)
			//str+='<li><img src="https://mytdp.com/activity_documents/' +path+'" style="cursor:pointer;"></li>';
		for(var i in result)
		{	 
			for(var j in result[i].subList)
			{
				str+='<li><img src="https://mytdp.com/activity_documents/' +result[i].subList[j].path+'" style="cursor:pointer;"/></li>';	
			}
		}
				str+='</ul>';
			$("#popupImages").html(str);
			
			setTimeout(function(){		
			$('.slider-for').slick({
			  slidesToShow: 1,
			  slidesToScroll: 1,
			  slide: 'li',
			  arrows: true,
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
		
			var itemsCount=result[0].totalResult;
			
			
	    var maxResults=jObj.maxIndex;
	   if(jObj.startIndex==0){
		   $("#paginationDivId").html('');
		   $("#paginationDivId").pagination({
				items: itemsCount,
				itemsOnPage: maxResults,
				cssStyle: 'light-theme',
				
				onPageClick: function(pageNumber, event) {
					var num=(pageNumber-1)*10;
					// getEventDocumentForPopup(jObj.locationScope,jObj.locationValue,jObj.day,num,path,attr_activity_scopeid,locationScope,locationScopeValue,""); .
					var weekDays = $("#daysListId").val();
					if(weekDays != null){
						datesArr = weekDays.split('to');
					}					
					if(datesArr != null && datesArr.length==2){
						getTempEventDocumentForPopup(jObj.locationScope,jObj.locationValue,jObj.day,num,path,attr_activity_scopeid,locationScope,locationScopeValue,"",datesArr[0].trim(),datesArr[1].trim());
					}else{
						getTempEventDocumentForPopup(jObj.locationScope,jObj.locationValue,jObj.day,num,path,attr_activity_scopeid,locationScope,locationScopeValue,"",'','');
					}					
				}
			});
			$("#paginationDivId").find("ul").addClass("pagination");
		}
		
	//GlobalPopupScope = globallocationScope;
	//GlobalPopuplocation =globallocationValue;
	
	}
}

$(document).on("change","#daysListId",function(){
	
	var weekDays = $(this).val();
	var datesArr = [];
	if(weekDays != null){
		datesArr = weekDays.split('to');
	}
	$("#paginationDivId").html('');
	if(weekDays == 0){
		getTempEventDocumentForPopup(GlobalPopupScope,GlobalPopuplocation,0,0,'',globalActivityScope,globallocationScope,globallocationValue,"","","");
		getTempAvailablDates(globallocationScope,globallocationValue,0,'',globalActivityScope,"","");
		getDistrictNames('',"","");	
	}else{
		if(datesArr != null && datesArr.length==2){
			getTempEventDocumentForPopup(GlobalPopupScope,GlobalPopuplocation,0,0,'',globalActivityScope,globallocationScope,globallocationValue,"",datesArr[0].trim(),datesArr[1].trim());
			getTempAvailablDates(globallocationScope,globallocationValue,0,'',globalActivityScope,datesArr[0].trim(),datesArr[1].trim());
			getDistrictNames('',datesArr[0].trim(),datesArr[1].trim());	
		}else{
			getTempEventDocumentForPopup(GlobalPopupScope,GlobalPopuplocation,0,0,'',globalActivityScope,globallocationScope,globallocationValue,"",'','');
			getTempAvailablDates(globallocationScope,globallocationValue,0,'',globalActivityScope,'','');
			getDistrictNames('','','');	
		}
	}
	
});


function getTempEventDocumentForPopup(searchType,locationId,day,num,path,attr_activity_scopeid,locationScope,locationScopeValue,calFrom,fromDateStr,toDateStr)
{
	 $("#popupImages").html('<img src="./images/Loading-data.gif" />');
	 var dates=$('.searchDateCls ').val();
		var jObj = {
		activityId:globalActivityScope,
		locationScope:locationScope,
		locationValue:locationScopeValue,		
		day:day,
		//fromDateStr:"01-01-2015",
		//toDateStr:"22-01-2017",
		fromDateStr : fromDateStr,
	    toDateStr :toDateStr,
		type:"popup",
		startIndex:num,
		maxIndex:10,
		callFrom:calFrom,
		//locationName:obj.locationName,
		activityMemberId : globalActivityMemberId,
	    stateId : globalStateId,
	    userTypeId : globalUserTypeId,
		 task:"popupdaywise"
		};
		$.ajax({
          type:'GET',
          url: 'getEventDocumentsAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			buildDayWisImagesForPopup1(result,jObj,path,attr_activity_scopeid,locationScope,locationScopeValue,searchType,locationId);
			});
}

function getTempAvailablDates(locationScope,locationValue,day,path,attr_activity_scopeid,fromDateStr,toDateStr)
	{
		
	  $("#popupDaysDiv1").html('');
	  var activityScopeId = $("#ActivityList").val();
	
		var jObj = {
		activityId:attr_activity_scopeid,
		locationScope:locationScope,
		locationValue:locationValue,	
		fromDateStr:fromDateStr,
		toDateStr:toDateStr,
		activityMemberId : globalActivityMemberId,
	    stateId : globalStateId,
	    userTypeId : globalUserTypeId,
		task:""
		};
		$.ajax({
          type:'GET',
          url: 'getAvailableDatesForActivitiesAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
				var str ='';
				str+='<ul class="slickApplyPopupDays" style="width:810px;">';
				for(var i in result)
				{ 
					/*if(i==0 && (globlbuildType =='dayswise')){
						str+='<li class="" ><a href="javascript:{};" class="getImageCls" activity_name=""  type="overAll" locationScope="'+locationScope+'"   locationScopeValue="'+locationValue+'"  path = "'+path+'" attr_activity_scopeid="'+attr_activity_scopeid+'"  > OVER ALL <span class="sr-only">(current)</span></a></li>';
					}
					else if(i==0 && globlbuildType =='overAll'){
						str+='<li class=" " ><a href="javascript:{};" class="getImageCls"   activity_name=""   type="dayswise" locationScope="'+locationScope+'"   locationScopeValue="'+locationValue+'"  path = "'+path+'" attr_activity_scopeid="'+attr_activity_scopeid+'" > DAY WISE <span class="sr-only">(current)</span></a></li>';
					}
					*/
					
					if(i==0 && globlbuildType =='overAll'){
						str+='<li class="" ><a href="javascript:{};" class="dayssCls" locationScope="'+locationScope+'"   locationScopeValue="'+locationValue+'"  dateValue="'+result[i].date+'" path = "'+path+'" attr_activity_scopeid="'+attr_activity_scopeid+'" attr="0" > OVER ALL <span class="sr-only">(current)</span></a></li>';
					}
						
					if(globlbuildType =='dayswise'){						
						if(result[i].id==day)
						{//attr_activity_scopeid,locationScope,locationScopeValue
							str+='<li class="active dayssCls"  locationScope="'+locationScope+'"   locationScopeValue="'+locationValue+'"  dateValue="'+result[i].date+'" path = "'+path+'" attr_activity_scopeid="'+attr_activity_scopeid+'" attr="'+result[i].id+'"><a href="javascript:{};">Day '+result[i].id+' (<span title="Total Images ">'+result[i].totalResult+'</span> / <span title="Total Images Covered Locations ">'+result[i].coveredCount+'</span>) <span class="sr-only">(current)</span></a></li>';
							/* getEventDocumentForPopup(jObj.locationScope,jObj.locationValue,day,0,path,attr_activity_scopeid,locationScope,locationValue); */
						}else if (i==0){
							str+='<li class="active dayssCls"  locationScope="'+locationScope+'"   locationScopeValue="'+locationValue+'"  dateValue="'+result[i].date+'" path = "'+path+'" attr_activity_scopeid="'+attr_activity_scopeid+'" attr="'+result[i].id+'"><a href="javascript:{};">Day '+result[i].id+' (<span title="Total Images ">'+result[i].totalResult+'</span> / <span title="Total Images Covered Locations ">'+result[i].coveredCount+'</span>) <span class="sr-only">(current)</span></a></li>';
							/* getEventDocumentForPopup(jObj.locationScope,jObj.locationValue,day,0,path,attr_activity_scopeid,locationScope,locationValue); */ 
						}else{
							str+='<li class="dayssCls" locationScope="'+locationScope+'" locationScopeValue="'+locationValue+'"  dateValue="'+result[i].date+'" attr_activity_scopeid="'+attr_activity_scopeid+'" attr="'+result[i].id+'"><a href="javascript:{};">Day '+result[i].id+' (<span title="Total Images ">'+result[i].totalResult+'</span> / <span title="Total Images Covered Locations ">'+result[i].coveredCount+'</span>) <span class="sr-only">(current)</span></a></li>';
						 }
					}
					else if(globlbuildType =='overAll' && i == 0 ){
						for(var j in result[i].documentsVOList)
						{
							if(result[i].documentsVOList[j].strDate==day)
							{//attr_activity_scopeid,locationScope,locationScopeValue
								str+='<li class="active dayssCls"  locationScope="'+locationScope+'"   locationScopeValue="'+locationValue+'"  dateValue="'+result[i].date+'" path = "'+path+'" attr_activity_scopeid="'+attr_activity_scopeid+'" attr="'+result[i].documentsVOList[j].day+'"><a href="javascript:{};"> Day '+result[i].documentsVOList[j].day+' <br>  '+result[i].documentsVOList[j].strDate+' (<span title="Total Images ">'+result[i].documentsVOList[j].totalResult+'</span> / <span title="Total Images Covered Locations ">'+result[i].documentsVOList[j].coveredCount+'</span>) <span class="sr-only">(current)</span></a></li>';
								/* getEventDocumentForPopup(jObj.locationScope,jObj.locationValue,day,0,path,attr_activity_scopeid,locationScope,locationValue); */
							}else if (j==0){
								str+='<li class="active dayssCls"  locationScope="'+locationScope+'"   locationScopeValue="'+locationValue+'"  dateValue="'+result[i].date+'" path = "'+path+'" attr_activity_scopeid="'+attr_activity_scopeid+'" attr="'+result[i].documentsVOList[j].day+'"><a href="javascript:{};"> Day '+result[i].documentsVOList[j].day+' <br>'+result[i].documentsVOList[j].strDate+' (<span title="Total Images ">'+result[i].documentsVOList[j].totalResult+'</span> / <span title="Total Images Covered Locations ">'+result[i].documentsVOList[j].coveredCount+'</span>) <span class="sr-only">(current)</span></a></li>';
								/* getEventDocumentForPopup(jObj.locationScope,jObj.locationValue,day,0,path,attr_activity_scopeid,locationScope,locationValue); */ 
							}else{
								str+='<li class="dayssCls" locationScope="'+locationScope+'" locationScopeValue="'+locationValue+'"  dateValue="'+result[i].date+'" attr_activity_scopeid="'+attr_activity_scopeid+'" attr="'+result[i].documentsVOList[j].day+'"><a href="javascript:{};">Day '+result[i].documentsVOList[j].day+' <br>'+result[i].documentsVOList[j].strDate+' (<span title="Total Images ">'+result[i].documentsVOList[j].totalResult+'</span> / <span title="Total Images Covered Locations ">'+result[i].documentsVOList[j].coveredCount+'</span>) <span class="sr-only">(current)</span></a></li>';
							 }
						}
					
					}
				}
				str+='</ul>';
				$("#popupDaysDiv1").html(str);
				$('.slickApplyPopupDays').slick({
					slide: 'li',
					slidesToShow: 3,
					slidesToScroll: 3,
					infinite: false,
					variableWidth:true
				});
				GlobalPopupScope = jObj.locationScope;
				GlobalPopuplocation =jObj.locationValue;
				
		});
}

function getConstituenciesForDistrict(districtId){
	$("#constituencyId").empty();
	
	var jsObj ={ 
	               districtId : districtId
				};
	  $.ajax({
			type : 'POST',
			url : 'getConstituenciesForDistrictAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		   if( result != null && result.length>0){
			   buildConstituenciesForDistrict(result);
		   }
		});
}
function buildConstituenciesForDistrict(result){
	$("#constituencyId").append("<option value='0'>All</option>");        
      for(var i in result){
        $("#constituencyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
      }
}
function getMandalsByConstituency(constId){
	$("#mandalId").empty();
	var jsObj ={ 
	               constituencyId : constId
				};
	  $.ajax({
			type : 'POST',
			url : 'getMandalDetailsByConstituencyAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		   if( result != null && result.length>0){
			   buildMandalsForConstituencies(result);
		   }
		});
}
function buildMandalsForConstituencies(result){
	$("#mandalId").append("<option value='0'>All</option>");        
      for(var i in result){
		  if(result[i].locationId != 0)
        $("#mandalId").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
      }
}
function getVillageWardByMandal(mandalId,constId){
	$("#villgWardId").empty();
	var jsObj ={ 
				   constituencyId:constId,
	               mandalId : mandalId
				};
	  $.ajax({
			type : 'POST',
			url : 'getPanchayatWardByMandalAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		   if( result != null && result.length>0){
			   buildVillageWardByMandal(result);
		   }
		});
}
function buildVillageWardByMandal(result){
	$("#villgWardId").append("<option value='0'>All</option>");        
      for(var i in result){
		  if(result[i].locationId != 0)
        $("#villgWardId").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
      }
}
$(document).on("click","#paginationDivId ul li",function(){
	$("#paginationDivId").find("ul").addClass("pagination");
});
function getDistrictNames(activity_name,startDate,endDate){
	
	$("#districtsUlId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	var scopeId = $("#hiddenActivityScopeId").val();
	var activityLevelId= $("#hiddenActivityLevelId").val();
	var date1Str ='';
	var date2Str ='';
	
	if( (startDate==null || startDate =='') || (endDate==null || endDate =='')){
		var date1 = $("#dateRangeIdForEvents").val();
		var date2Arr =date1.split(" - ");		
			date1Str = date2Arr[0].replace("/", "-");
			date1Str = date1Str.replace("/", "-");
			date2Str = date2Arr[1].replace("/", "-");
			date2Str = date2Str.replace("/", "-");		
	}
	else{
		date1Str =startDate;
		date2Str =endDate;
	}
	var jObj = {
		activityScopeId:scopeId,
		activityMemberId : globalActivityMemberId,
		startDate:date1Str,
		endDate:date2Str,
	    stateId : globalStateId,
	    userTypeId : globalUserTypeId,		
	};
	
	$.ajax({
	  type:'GET',
	  url: 'getDistrictListsAction.action',
	 data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		buildDistrictNames(result,activityLevelId,scopeId,activity_name);
	});
}
function buildDistrictNames(result,activityLevelId,scopeId,activity_name)
{
	var str='';
	str+='<div class="panel-group" id="accordionModal" role="tablist" aria-multiselectable="true">';
	
	var coveredCount =0;
	var imagesCount =0;
	for(var i in result)
	{
		if(result[i].count != null )
			imagesCount = parseInt(result[i].count)+imagesCount;
		if(result[i].imagesCnt != null )
			coveredCount = parseInt(result[i].imagesCnt)+coveredCount;
	}
	
	for(var i in result)
	{
		
	
		if(i==0){
			str+='<div style="text-align:right;margin:10px ">';
			//str+='<div class="panel-heading panel-headingModal" role="tab"  id="headingOneModa1l'+i+'" >';
				str+='<a role="button" class="getImageCls"  type="overAll"   activity_name="'+activity_name+'" attr_activity_scopeid="'+scopeId+'" attr_level_id="'+activityLevelId+'" aria-controls="collapseOneModa1l'+i+'"data-toggle="collapse" data-parent="#accordionModal"  aria-expanded="true" attr_activity_level_id="'+activityLevelId+'" >';
				/*str+='<h4 class="panel-title"> Andhra Pradesh ( <span title="Total Uploaded Images ">'+imagesCount+' </span>/<span title="Images Covered Locations ">'+coveredCount+')</h4>';*/
				str+='<i class="glyphicon glyphicon-refresh" title="click here to refresh"></i>';
				str+='</a>';
				
				str+='</div>';
		//str+='<div id="collapseOneModa1l'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOneModa1l'+i+'">';
		//  str+='<div class="panel-body pad_0">';
		//	str+='<div id="constituenciesBlocks'+result[i].districtId+'"></div>';
		//  str+='</div>';
		//str+='</div>';
	 // str+='</div>';
		
		}
		
		
	  str+='<div class="panel panel-default panel-custommodal">';
		str+='<div class="panel-heading panel-headingModal" role="tab" id="headingOneModal'+i+'">';
		if(activityLevelId == 1 || activityLevelId == 2 || activityLevelId == 5){
			str+='<a role="button" class="constituencyPopups accordionmodal-toggle collapsed" data-toggle="collapse" data-parent="#accordionModal" attr_distId="'+result[i].districtId+'" attr_dist_name="'+result[i].name+'" href="#collapseOneModal'+i+'" aria-expanded="true" attr_activity_level_id="'+activityLevelId+'" aria-controls="collapseOneModal'+i+'">';
			str+='<h4 class="panel-title">'+result[i].name+'( <span title="Total Uploaded Images ">'+result[i].count+' </span>/<span title="Images Covered Locations ">'+result[i].imagesCnt+')</h4>';
		  str+='</a>';
		}else{
			 str+='<a role="button" class="constituencyPopups accordionmodal-toggle collapsed"  data-parent="#accordionModal" attr_distId="'+result[i].districtId+'" attr_dist_name="'+result[i].name+'" href="#collapseOneModal'+i+'" aria-expanded="true" aria-controls="collapseOneModal'+i+'">'; 
			str+='<h4 class="panel-title">'+result[i].name+'( <span title="Total Uploaded Images ">'+result[i].count+' </span>/<span title="Images Covered Locations ">'+result[i].imagesCnt+')</h4>';
		  str+='</a>';
		}
		str+='</div>';
		str+='<div id="collapseOneModal'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOneModal'+i+'">';
		  str+='<div class="panel-body pad_0">';
			str+='<div id="constituenciesBlock'+result[i].districtId+'"></div>';
		  str+='</div>';
		str+='</div>';
	  str+='</div>';
	}
	str+='</div>';
	$("#districtsUlId").html(str);
}
$(document).on("click",".constituencyPopups",function(){
	var distId = $(this).attr("attr_distId");
	var activityLevelId = $(this).attr("attr_activity_level_id");
	var attr_dist_name = $(this).attr("attr_dist_name");
	getConstituencyList(distId,activityLevelId);
});

function getConstituencyList(distId,activityLevelId){
	$("#constituenciesBlock"+distId).html('');
	$(".allConstCls").html('');
	
	$('.dayssCls').each(function(){
		if($(this).hasClass("active")){
			// var day = $(this).attr("attr");
			var locationScope = 'district';
			var locationScopeValue = distId;
			var attr_activity_scopeid = $(this).attr("attr_activity_scopeid");
			
			var path = $(this).attr("path");
			//getAvailablDates(locationScope,locationScopeValue,0,path,attr_activity_scopeid);
			//getEventDocumentForPopup(GlobalPopupScope,GlobalPopuplocation,0,0,path,attr_activity_scopeid,locationScope,locationScopeValue,"");
			
			var weekDays = $("#daysListId").val();
			var datesArr = [];
			if(weekDays != null){
				datesArr = weekDays.split('to');
			}
			if(datesArr != null && datesArr.length==2){
				getTempEventDocumentForPopup(GlobalPopupScope,GlobalPopuplocation,0,0,'',attr_activity_scopeid,locationScope,locationScopeValue,"",datesArr[0].trim(),datesArr[1].trim());
				getTempAvailablDates(locationScope,locationScopeValue,0,'',attr_activity_scopeid,datesArr[0].trim(),datesArr[1].trim());
			}else{
				getTempEventDocumentForPopup(GlobalPopupScope,GlobalPopuplocation,0,0,'',attr_activity_scopeid,locationScope,locationScopeValue,"",'','');
				getTempAvailablDates(locationScope,locationScopeValue,0,'',attr_activity_scopeid,'','');
			}
			
		}
	});
	
	$("#constituenciesBlock"+distId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	var scopeId = $("#hiddenActivityScopeId").val();
	var date = $("#daysListId").val();
	var datesArr = [];
	if(date != null){
		datesArr = date.split('to');
	}
	var jObj;
	if(datesArr != null && datesArr.length==2){
		jObj = {
				activityScopeId:scopeId,
				districtId : distId,
				fromDate: datesArr[0].trim(),
				toDate: datesArr[1].trim()
			};
	}else{
		jObj = {
			activityScopeId:scopeId,
			districtId : distId,
			fromDate: '',
			toDate: ''
		};	
	}
		
	
	
	$.ajax({
	  type:'GET',
	  url: 'getConstituencyListsAction.action',
	 data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		if(result != null && result.length > 0)
			buildConstituencyList(result,distId,activityLevelId);
		else
		$("#constituenciesBlock"+distId).html("No Data Available.");
	});
}
function buildConstituencyList(result,distId,activityLevelId)
{
	var str='';
	str+='<div class="panel-group allConstCls" id="accordionModalCons'+distId+'" role="tablist" aria-multiselectable="true">';
	for(var i in result)
	{
	  str+='<div class="panel panel-default panel-custommodal">';
		str+='<div class="panel-heading panel-headingModal" role="tab" id="headingOneModalCons'+i+'">';
		if(activityLevelId == 1 || activityLevelId == 2){
			 str+='<a role="button" class="mandalPopups accordionmodal-toggle collapsed" data-toggle="collapse" data-parent="#accordionModalCons'+distId+'" attr_consId="'+result[i].constituencyId+'" href="#collapseOneModalCons'+i+'" attr_activity_level_id="'+activityLevelId+'"aria-expanded="true" aria-controls="collapseOneModalCons'+i+'" attr_num="'+i+'">';
			str+='<h4 class="panel-title">'+result[i].name+' ASSEMBLY ( <span title="Total Uploaded Images ">'+result[i].imagesCnt+' </span>/<span title="Images Covered Locations ">'+result[i].count+')</h4>';
		  str+='</a>';
		}else{
			  str+='<a role="button" class="mandalPopups" data-parent="#accordionModalCons'+distId+'" attr_consId="'+result[i].constituencyId+'" href="#collapseOneModalCons'+i+'" aria-expanded="true" aria-controls="collapseOneModalCons'+i+'">'; 
			str+='<h4 class="panel-title">'+result[i].name+' ASSEMBLY ( <span title="Total Uploaded Images ">'+result[i].imagesCnt+' </span>/<span title="Images Covered Locations ">'+result[i].count+'</span>)</h4>';
		  str+='</a>';
		}
		 
		str+='</div>';
		str+='<div id="collapseOneModalCons'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOneModalCons'+i+'">';
		  str+='<div class="panel-body pad_0">';
			str+='<div id="mandalsBlock'+result[i].constituencyId+'"></div>';
		  str+='</div>';
		str+='</div>';
	  str+='</div>';
	}
	str+='</div>';
	$("#constituenciesBlock"+distId).html(str);
	
}
$(document).on("click",".mandalPopups",function(){
	var constituencyId = $(this).attr("attr_consId");
	var activityLevelId = $(this).attr("attr_activity_level_id");
	getMandalOrMuncList(constituencyId,activityLevelId,0,"");
});

function getMandalOrMuncList(constituencyId,activityLevelId,value,scopeId){
	
	$("#mandalsBlock"+constituencyId).html('');
	$('.dayssCls').each(function(){
		if($(this).hasClass("active")){
			 //var day = $(this).attr("attr");
			var locationScope = 'constituency';
			var locationScopeValue = constituencyId;
			var attr_activity_scopeid = $(this).attr("attr_activity_scopeid");
			var path = $(this).attr("path");
			//getEventDocumentForPopup(GlobalPopupScope,GlobalPopuplocation,0,0,path,attr_activity_scopeid,locationScope,locationScopeValue,"");
			//getAvailablDates(locationScope,locationScopeValue,0,path,attr_activity_scopeid);
var weekDays = $("#daysListId").val();
						
						if(weekDays != null){
							datesArr = weekDays.split('to');
						}
						if(datesArr != null && datesArr.length==2){
							getTempEventDocumentForPopup(GlobalPopupScope,GlobalPopuplocation,0,0,path,attr_activity_scopeid,locationScope,locationScopeValue,"",datesArr[0].trim(),datesArr[1].trim());
							getTempAvailablDates(locationScope,locationScopeValue,0,'',attr_activity_scopeid,datesArr[0].trim(),datesArr[1].trim());
						}
						else{
							getTempEventDocumentForPopup(GlobalPopupScope,GlobalPopuplocation,0,0,path,attr_activity_scopeid,locationScope,locationScopeValue,"","","");
							getTempAvailablDates(locationScope,locationScopeValue,0,'',attr_activity_scopeid,"","");
						}
		
		}
	});
	
	$("#mandalsBlock"+constituencyId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	var activtyScopeId;
	if(scopeId !=null && scopeId.length > 0){
		activtyScopeId = scopeId;
	}else{
		activtyScopeId = $("#hiddenActivityScopeId").val();
	}
	//var scopeId = $("#hiddenActivityScopeId").val();
	var date = $("#daysListId").val();
	var datesArr = [];
	if(date != null){
		datesArr = date.split('to');
	}
	var jObj = {
		activityScopeId:activtyScopeId,
		constituencyId : constituencyId,
		fromDate: datesArr[0].trim(),
		toDate: datesArr[1].trim()
	};
		
	var jObj;
	if(datesArr != null && datesArr.length==2){
		jObj = {
			activityScopeId:activtyScopeId,
			constituencyId : constituencyId,
			fromDate: datesArr[0].trim(),
			toDate: datesArr[1].trim()
		};
	}else{
		jObj = {
			activityScopeId:activtyScopeId,
			constituencyId : constituencyId,
			fromDate: '',
			toDate: ''
		};
	}
		
	$.ajax({
	  type:'GET',
	  url: 'getMandalOrMuncpalityListAction.action',
	 data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		if(result != null && result.length > 0 )
		buildMandalOrMuncList(result,constituencyId,activityLevelId,value,scopeId);
	else
		$("#mandalsBlock"+constituencyId).html("No Data Available.");
	});
}
function buildMandalOrMuncList(result,constituencyId,activityLevelId,value,scopeId)
{
	var str='';
	str+='<div class="panel-group" id="accordionModalMandal'+constituencyId+'" role="tablist" aria-multiselectable="true">';
	for(var i in result)
	{
	//srinu
	  str+='<div class="panel panel-default panel-custommodal">';
		str+='<div class="panel-heading panel-headingModal" role="tab" id="headingOneModalMandal'+i+'">';
		if(activityLevelId == 1){
			str+='<a role="button" class="panchayatPopups accordionmodal-toggle collapsed" data-toggle="collapse" data-parent="#accordionModalMandal'+constituencyId+'" attr_mandalId="'+result[i].mandalId+'" attr_scopeId="'+scopeId+'"  href="#collapseOneModalMandal'+i+'" aria-expanded="true" aria-controls="collapseOneModalMandal'+i+'">';
			str+='<h4 class="panel-title">'+result[i].name+'(<span title="Total Uploaded Images ">'+result[i].count+' </span>/<span title="Images Covered Locations ">'+result[i].imagesCount+')</h4>';
		  str+='</a>';
		}else{
			 str+='<a role="button" class="panchayatPopups accordionmodal-toggle" data-parent="#accordionModalMandal'+constituencyId+'" attr_mandalId="'+result[i].mandalId+'"  attr_scopeId="'+scopeId+'" href="#collapseOneModalMandal'+i+'" aria-expanded="true" aria-controls="collapseOneModalMandal'+i+'">'; 
			str+='<h4 class="panel-title">'+result[i].name+'(<span title="Total Uploaded Images ">'+result[i].count+' </span>/<span title="Images Covered Locations ">'+result[i].imagesCount+')</h4>';
		  str+='</a>';
		}
		str+='</div>';
		str+='<div id="collapseOneModalMandal'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOneModalMandal'+i+'">';
		  str+='<div class="panel-body pad_0"><div id="panchayatBlock'+result[i].mandalId+'"></div></div>';
		str+='</div>';
	  str+='</div>';
	}
	str+='</div>';
	if(value == 0){
		$("#mandalsBlock"+constituencyId).html(str);
	}else{
		$("#mandalsUlId").html(str);
	}
	
}
$(document).on("click",".panchayatPopups",function(){
	var mandalId = $(this).attr("attr_mandalId");
	var scopeId = $(this).attr("attr_scopeId");
	var hrefRef = $(this).attr("href");
	$(hrefRef).collapse('toggle');
	getPanchayatList(mandalId,scopeId,0);
});

function getPanchayatList(mandalId,scopeId,value){
	$("#panchayatBlock"+mandalId).html('');
	$('.dayssCls').each(function(){
		if($(this).hasClass("active")){
			// var day = $(this).attr("attr");
			var locationScope = 'mandal';
			var locationScopeValue = mandalId;
			var attr_activity_scopeid = $(this).attr("attr_activity_scopeid");
			var path = $(this).attr("path");
			
			
			var weekDays = $("#daysListId").val();
			var datesArr = [];
			if(weekDays != null){
				datesArr = weekDays.split('to');
			}
			if(datesArr != null && datesArr.length==2){
				getTempEventDocumentForPopup(GlobalPopupScope,GlobalPopuplocation,0,0,path,attr_activity_scopeid,locationScope,locationScopeValue,"",datesArr[0].trim(),datesArr[1].trim());
				getTempAvailablDates(locationScope,locationScopeValue,0,'',attr_activity_scopeid,datesArr[0].trim(),datesArr[1].trim());
			}else{
				getTempEventDocumentForPopup(GlobalPopupScope,GlobalPopuplocation,0,0,'',attr_activity_scopeid,locationScope,locationScopeValue,"",'','');
				getTempAvailablDates(locationScope,locationScopeValue,0,'',attr_activity_scopeid,'','');
			}
			
			/* getEventDocumentForPopup(GlobalPopupScope,GlobalPopuplocation,0,0,path,attr_activity_scopeid,locationScope,locationScopeValue,"");
			getAvailablDates(locationScope,locationScopeValue,0,path,attr_activity_scopeid); */

		}
	});
	
	$("#panchayatBlock"+mandalId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	var activtyScopeId;
	if(scopeId != null && scopeId.length > 0){
		activtyScopeId = scopeId;
	}else{
		activtyScopeId = $("#hiddenActivityScopeId").val();
	}
	
	//var scopeId = $("#hiddenActivityScopeId").val();
	
	var date = $("#daysListId").val();
	var datesArr = [];
	if(date != null){
		datesArr = date.split('to');
	}
	var jObj;
	
	if(datesArr != null && datesArr.length==2){
		jObj = {
				activityScopeId:activtyScopeId,
				mandalOrMuncipalityId : mandalId,
				fromDate: datesArr[0].trim(),
				toDate: datesArr[1].trim()
			};
	}else{
		jObj  = {
			activityScopeId:activtyScopeId,
			mandalOrMuncipalityId : mandalId,
			fromDate: '',
			toDate: ''
		};
	}
	
	$.ajax({
	  type:'GET',
	  url: 'getPanchayatOrWardListAction.action',
	 data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		buildPanchayatList(result,mandalId,value);
	});
}
function buildPanchayatList(result,mandalId,value)
{
	var str='';
	if(result !=null && result.length > 0){
	str+='<ul class="villageDaysModal">';
		for(var i in result)
		{
			str+='<li><a class="villagePopup" attr_villageId="'+result[i].panchayatId+'" style="cursor:pointer;">'+result[i].name+'(<span title="Total Uploaded Images ">'+result[i].count+' </span>/<span title="Images Covered Locations ">'+result[i].imagesCount+')</a></li>';
		}
	 str+='</ul>';
	 if(value == 0)
		 $("#panchayatBlock"+mandalId).html(str);
	 else
		 $("#villageUlId").html(str);
	}else{
		 if(value == 0)
			$("#panchayatBlock"+mandalId).html("No Data Available.");
		else
		 $("#villageUlId").html("No Data Available.");
	}
}
$(document).on("click",".villagePopup",function(){
	var panchayatId = $(this).attr("attr_villageId");
	$('.dayssCls').each(function(){
		if($(this).hasClass("active")){
			// var day = $(this).attr("attr");
			var locationScope = 'village';
			var locationScopeValue = panchayatId;
			var attr_activity_scopeid = $(this).attr("attr_activity_scopeid");
			var path = $(this).attr("path");
			
			var weekDays = $("#daysListId").val();
			if(weekDays != null){
				datesArr = weekDays.split('to');
			}
			if(datesArr != null && datesArr.length==2){
				getTempEventDocumentForPopup(GlobalPopupScope,GlobalPopuplocation,0,0,path,attr_activity_scopeid,locationScope,locationScopeValue,"",datesArr[0].trim(),datesArr[1].trim());
				getTempAvailablDates(locationScope,locationScopeValue,0,'',attr_activity_scopeid,datesArr[0].trim(),datesArr[1].trim());
			}
			else{
				getTempEventDocumentForPopup(GlobalPopupScope,GlobalPopuplocation,0,0,path,attr_activity_scopeid,locationScope,locationScopeValue,"","","");
				getTempAvailablDates(locationScope,locationScopeValue,0,'',attr_activity_scopeid,"","");
			}
			/* getEventDocumentForPopup(GlobalPopupScope,GlobalPopuplocation,0,0,path,attr_activity_scopeid,locationScope,locationScopeValue,"");
			getAvailablDates(locationScope,locationScopeValue,0,path,attr_activity_scopeid); */

		}
	});
});

$(document).on("click",".activityCls",function(){
	var activityLevelIds=[];
	var activityId = $(this).attr("attr_id");
	var activityName = $(this).attr("attr_activity_name");
	var activityLevelId = $(this).attr("attr_level_id");
	 if(activityLevelId != null && activityLevelId > 0){
		activityLevelIds.push(activityLevelId); 
	 }
	 getUserTypeActivityConductedCnt(activityId,activityLevelIds);
	 activityName = activityName.replace("'","");
	 activityName = activityName.replace("\'","");
	 $(".eventAndActivityCls").html(activityName);
});
var globalUserWiseConductedRslt;
function getUserTypeActivityConductedCnt(activityIdsString,activityLevelIds){
	$("#UserTypeWiseEventMemberDtslDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	$(".eventStrngPrCls").attr("attr_selected_type","Activity");
	   var activityIds= activityIdsString.split(",");
	   var dates = $('#dateRangeIdForEvents').val();
	  var dateArray = dates.split("-");
	  var fromDateStr=dateArray[0];
	  var toDateStr=dateArray[1];
	var jsObj ={ 
				 activityMemberId : globalActivityMemberId,
				 stateId : globalStateId,
				 activityIds:activityIds,
				 activityLevelIds:activityLevelIds,
				 userTypeId : globalUserTypeId,
				 //fromDateStr: fromDateStr, //customStartDateActivities,
				 //toDateStr: toDateStr //customEndDateActivities
				 fromDateStr : '01/02/2015',
	             toDateStr : '01/02/2020'
			  }
	$.ajax({
		type : 'POST',
		url : 'getUserTypeActivityConductedCntAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		 $("#UserTypeWiseEventMemberDtslDivId").html(' ');
		 globalUserWiseConductedRslt = result;
		 buildUserTypeWiseTopFiveActivityConductedRlst(result);
	});
}
    
function buildUserTypeWiseTopFiveActivityConductedRlst(result){
		var str='';
		if(result != null && result.length > 0){
		  var str='';
		  for(var i in result){
			str+='<div class="col-md-12 col-xs-12 col-sm-12 ">';
				 if(result[i][0].userTypeId==4 || result[i][0].userTypeId==11){
				  if(result[i][0].userTypeId==4){
				  if(result[i][0].completedPerc!=0){
					  str+='<h5 class="text-capital">'+result[i][0].userType+' / SECRETARY</h5>';      
				  }
				  }
				  if(result[i][0].userTypeId==11){
				   if(result[i][0].completedPerc!=0){
					 str+='<h5 class="text-capital">ORGANIZING SECRETARY /'+result[i][0].userType+'</h5>';      
				   }
			     }
			   }else{ 
				 if(result[i][0].completedPerc!=0){
					str+='<h5 class="text-capital">'+result[i][0].userType+'</h5>'; 
				 }
		      }
			  str+='<div id="activityConductedGraph'+i+'" style="height:130px;"></div>';
			str+='</div>'
		  }
		}
		$("#UserTypeWiseEventMemberDtslDivId").html(str);
	   if(result != null && result.length > 0){
			for(var i in result){
				var candidateNameArray = [];
				var activityConcuctedPereArr = [];
				var countVar =0;
			  if(result[i] !=null && result[i].length>0){
					for(var j in result[i]){
						countVar =countVar+1;
						candidateNameArray.push(result[i][j].name);
						activityConcuctedPereArr.push({"y":result[i][j].completedPerc});
						
						if (countVar === 5) {
							break;
						}
					}
				}
		if(result[i][0].completedPerc!=0){
			$(function () {
			  $('#activityConductedGraph'+i).highcharts({
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
					
				},
				tooltip: {formatter: function(){
					return '<b>Activity Conducted:'+ Highcharts.numberFormat(this.y, 2) +'%</b><br/>'+
                    'Name: '+ this.x;   
				}      
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
					},
					series: {
						cursor: 'pointer',
							point: {
								events: {
								click: function () {
									getIndividualPersonTourDetails(this.extra);
								}
							}
						}
					},
				},
				legend: {
					enabled: false,
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
				
				series: [{
					name: 'Activity conducted',
					data: activityConcuctedPereArr
				}]
			});
		});
		}else{
		$("#activityConductedGraph"+i).html("No Data Available");
		$("#activityConductedGraph"+i).css("height","35px");
		$("#activityConductedGraph"+i).hide();
		} 
	}
	}else{
    $("#UserTypeWiseEventMemberDtslDivId").html('NO DATA AVAILABLE.');
	}
}
function buildUserTypeWisePoorFiveActivityConductedRlst(result){
		
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
				str+='<div id="activityConductedGraph'+i+'" style="height:180px;"></div>';
				str+='</div>'
			}
		}
		$("#UserTypeWiseEventMemberDtslDivId").html(str);
	  if(result != null && result.length > 0){
		for(var i in result){
				var candidateNameArray = [];
				var activityConcuctedPereArr = [];
				var countVar = 0;
				if(result[i] != null && result[i].length > 0){
					var length = result[i].length - 1;
					for(var j = length; j >= 0; j--){
						candidateNameArray.push(result[i][j].name);
						  activityConcuctedPereArr.push({"y":result[i][j].completedPerc});
						countVar =countVar+1;
						if (countVar === 5) {
							break;
						}
					}	
				}
			var getWidth = $("#activityConductedGraph"+i).parent().width()+'px';
				$("#activityConductedGraph"+i).width(getWidth);
				$(function () {
			   $('#activityConductedGraph'+i).highcharts({
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
				tooltip: {formatter: function(){
					return '<b>Activity Conducted:'+ Highcharts.numberFormat(this.y, 2) +'%</b><br/>'+
                    'Name: '+ this.x; 
				}      
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
									return Highcharts.numberFormat(this.y,2)+"%";
								}
							}
						  
						}
					},
					series: {
						cursor: 'pointer',
							point: {
								events: {
								click: function () {
									getIndividualPersonTourDetails(this.extra);
								}
							}
						}
					},
				},
				legend: {
					enabled: false,
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
					name: 'Activity Conducted',
					data: activityConcuctedPereArr
				}]
			});
		});
		//}
		/* }else{
		$("#genSecTour"+i).html("No Data Available");
		$("#genSecTour"+i).css("height","35px");	
		} */
		}
	}else{
	 $("#UserTypeWiseEventMemberDtslDivId").html('NO DATA AVAILABLE.');
	}
}
 $(document).on("click",".comparisonActivity",function(){
	 $("#childEvnetMemberDivId").html(' ');
	 $("#directChildMemberForEventDivId").html(' ');
	 $("#evntCmpBLockId").hide();
	$(".comparisonBlockActivities").show();
	$(".detailedBlockEvents").hide();
	//var type=$(this).attr("attr_type");
	var attrActivityIdsString="";
	getAllItsSubUserTypeIdsByParentUserTypeIdForActivity(attrActivityIdsString,"activities",globalUserTypeId);
});

function getAllItsSubUserTypeIdsByParentUserTypeIdForActivity(attrActivityIdsString,searchType,userTypeId){
	     
		  $("#childActivityMemberDivId").html(' ');
		  $("#directChildMemberForActivityDivId").html(' ');
		  $("#topPoorLocationsActivityDivId").html(' ');
		  
		 $("#allItsSubUserTypeIdsByParentUserTypeDivIdForActivity").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		 var userTypeArr =userTypeId.split(',');
		var jsObj = {parentUserTypeId : userTypeArr}
		$.ajax({
			type : 'POST',
			url : 'getAllItsSubUserTypeIdsByParentUserTypeIdAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#allItsSubUserTypeIdsByParentUserTypeDivIdForActivity").html(" ");	
			if(result != null && result.length > 0){
			buildgetChildUserTypesByItsParentUserTypeForActivity(result,attrActivityIdsString,searchType);	
			}else{
			$("#allItsSubUserTypeIdsByParentUserTypeDivIdForActivity").html("NO DATA AVAILABLE");	
			}
		});		 
	}
	
function buildgetChildUserTypesByItsParentUserTypeForActivity(result,attrActivityIdsString,searchType){
		var str='';
		 str+='<ul class="comparisonSelect">';
		 
		 var firstChildUserTypeIdString;
		 var userType;
		 if(result !=null && result.length >0){
			  firstChildUserTypeIdString = result[0].shortName;
			  userType=result[0].userType;
			 for(var i in result){
				 str+='<li attr_search_type="'+searchType+'" attr_userTypeId="'+result[i].shortName+'" attr_userType=\''+result[i].userType+'\'  attr_event_idsString='+attrActivityIdsString+' class="allItsSubUserTypeClsForEvent">'+result[i].userType+'<span class="closeIconComparison"></span></li>';
			 }
		 }
		str+='</ul>';
		$("#allItsSubUserTypeIdsByParentUserTypeDivIdForActivity").html(str);
		$(".comparisonSelect li:first-child").addClass("active")
		
		getSelectedChildTypeMembersForActivity(firstChildUserTypeIdString,attrActivityIdsString,userType,searchType);
	}	
	
function getSelectedChildTypeMembersForActivity(firstChildUserTypeIdString,attrActivityIdsString,childUserType,searchType){
	 $("#childActivityMemberDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	 $("#directChildMemberForActivityDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	  var parentActivityMemberId = globalActivityMemberId;
	  var childUserTypeIdsArray = firstChildUserTypeIdString.split(",");
	   var activityIds=attrActivityIdsString.split(",");
	   var activityScopeId = $("#hiddenActivityScopeId").val();
	 var jsObj ={ 
	               parentActivityMemberId : parentActivityMemberId,
				   childUserTypeIdsArray : childUserTypeIdsArray,
				   reportType :"selectedUserType",
				   stateId : globalStateId,
				   eventIds:activityIds,
				   searchType : searchType
				 }
	  $.ajax({
			type : 'POST',
			url : 'getSelectedChildTypeMembersForEventAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		   $("#childActivityMemberDivId").html(' ');
		   $("#directChildMemberForActivityDivId").html(' ');
		  if(result != null && result.length > 0){
			  buildChildTypeMembersForActivityReslt(result,attrActivityIdsString,childUserType,searchType);
		  }else{
			  $("#childActivityMemberDivId").html("NO DATA AVAILABLE");
		  }
		});
 }	
 function buildChildTypeMembersForActivityReslt(result,attrActivityIdsString,childUserType,searchType){
	  var userTypeId = result[0].userTypeId;
	  var activityMemberId = result[0].activityMemberId;
	  var selectedMemberName = result[0].name;
	  var selectedUserType = result[0].userType;
	 var str='';
	 if(childUserType != null && childUserType.trim()=="MLA/CI" || childUserType.trim()=="MLA" || childUserType.trim()=="CONSTITUENCY INCHARGE"){
	     str+='<table style="background-color:#EDEEF0;border:1px solid #ddd" class="table table-condensed tableHoverLevels" id="eventMemberDataTblId">';
		 str+='<thead>';
		     str+='<th>Rank</th>';
			 str+='<th>Name</th>';
			 str+='<th>Designation</th>';
			 str+='<th>Total Activities</th>';
			 str+='<th>Conducted</th>';
			 str+='<th>%</th>';
			 str+='<th>Not conducted</th>';
			 str+='<th>%</th>';
			 //str+='<th>Non Invitees Attended</th>';
			 //str+='<th>%</th>';
		 str+='</thead>';
		 str+='<tbody>';
		 var rank=1;
		  for(var i in result){
			str+='<tr style="cursor:pointer;" class="childActivityMemberCls" attr_event_idsString="'+attrActivityIdsString+'"  attr_selectedusertype="'+result[i].userType+'"  attr_id="directChildMemberForActivityDivId"  attr_selectedmembername="'+result[i].name+'"  attr_activitymemberid='+result[i].activityMemberId+'  attr_usertypeid='+result[i].userTypeId+' attr_search_type="'+searchType+'">';
			 str+='<td><span class="counts">'+rank+'</span></td>';
			 str+='<td>'+result[i].name+'</td>';
			 str+='<td>'+result[i].userType+'</td>';
			 //str+='<td>'+result[i].locationName+'</td>';
			 str+='<td>'+result[i].totalActvtiesCount+'</td>';
			 str+='<td>'+result[i].condctedActiesCount+'</td>';
			 str+='<td>'+result[i].conductedPerc+'</td>';
			 str+='<td>'+result[i].notCondctedActiesCount+'</td>';
			 str+='<td>'+result[i].notConductedPerc+'</td>';
			 str+='</tr>';
             rank=rank+1;			 
			}
			 str+='</tbody>';
			 str+='</table>';
	    $("#childActivityMemberDivId").html(str);
		$("#eventMemberDataTblId").dataTable({
			"aaSorting": [],
			"iDisplayLength" : 5	
		});
	  getActivityPoorPerformanceLocation(userTypeId,activityMemberId,selectedMemberName,selectedUserType,attrActivityIdsString,searchType);
	  }else{
	  str+='<ul class="list-inline slickPanelSliderForEvent">';
	  var rank=1; 
	   for(var i in result){
	str+='<li style="cursor:pointer;" class="childActivityMemberCls"  attr_event_idsString="'+attrActivityIdsString+'" attr_selectedusertype="'+result[i].userType+'"  attr_id="directChildMemberForActivityDivId"  attr_selectedmembername="'+result[i].name+'"  attr_activitymemberid='+result[i].activityMemberId+'  attr_usertypeid='+result[i].userTypeId+' attr_search_type="'+searchType+'" style="width:380px !important;">';
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
	   if(result[i].userTypeId != null && result[i].userTypeId==7 || result[i].userTypeId==9 || result[i].userTypeId==5 || result[i].userTypeId==6){ // MLA Constituency Incharge, MP and District President Incharge 
		   var lctnName = result[i].locationName;
           lctnName = lctnName.substring(0, lctnName.lastIndexOf(" "));
		 str+='<h4 class="text-capital">'+result[i].userType+' ('+lctnName+')</h4>';	 
		 }else{
		 str+='<h4 class="text-capital">'+result[i].userType+'</h4>';	 
		 }
			   str+='<h5 class=""> <i>Activities</i> </h5>';
			 str+='<table class="table table-condensed">';
				 str+='<thead>';
					 str+='<th>Total</th>';
					 str+='<th>Conducted</th>';
					// str+='<th>%</th>';
				     str+='<th>Not conducted</th>';
				     str+='<th>%</th>';
				 str+='</thead>';
				 str+='<tr>';
					 /* str+='<td>'+result[i].inviteeCnt+'</td>';
					 str+='<td>'+result[i].inviteeAttendedCnt+'</td>';
					 str+='<td>'+result[i].inviteeAttendedCntPer+'</td>';
					 str+='<td>'+result[i].nonInviteeAttendedCnt+'</td>';
					 str+='<td>'+result[i].nonInviteeAttendedCntPer+'</td>'; */
					 str+='<td>'+result[i].totalActvtiesCount+'</td>';
					 str+='<td>'+result[i].condctedActiesCount+'</td>';
					 //str+='<td>'+result[i].conductedPerc+'</td>';
					 str+='<td>'+result[i].notCondctedActiesCount+'</td>';
					 str+='<td>'+result[i].conductedPerc+'</td>';
				 str+='</tr>';
			 str+='</table>';
		 str+='</div>';
	 str+='</div> ';
    str+=' </li> ';  
	rank=rank+1;
	}
   $("#childActivityMemberDivId").html(str);
    
     getActivityPoorPerformanceLocation(userTypeId,activityMemberId,selectedMemberName,selectedUserType,attrActivityIdsString,searchType);
	getDirectChildTypeMembersForActivities(activityMemberId,userTypeId,selectedMemberName,selectedUserType,"directChildMemberForActivityDivId",attrActivityIdsString,searchType);		
	  }
		$(".slickPanelSliderForEvent").slick({
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
 
 function getDirectChildTypeMembersForActivities(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId,attrActivityIdsString,searchType){
	  $("#"+childActivityMemberId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
			     var activityIds=attrActivityIdsString.split(",");
		         var childUserTypeIdsArray=[];
	             childUserTypeIdsArray.push(userTypeId);
	  var jsObj ={   activityMemberId : activityMemberId,
			         childUserTypeIdsArray : childUserTypeIdsArray,
					 reportType : "directChild",
					 stateId : globalStateId,
					 eventIds:activityIds,
					 searchType : searchType
				  }
	   	$.ajax({
			type : 'POST',
			url : 'getDirectChildTypeMembersForEventAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
	     $("#"+childActivityMemberId).html('');
		  if(result != null && result.length > 0){
			  buildActivityDirectChildDetailsRslt(result,selectedMemberName,selectedUserType,childActivityMemberId,userTypeId,attrActivityIdsString,searchType);
		  }else{
		  // $("#"+childActivityMemberId).html('NO DATA AVAILABLE'); 
           if(childActivityMemberId == "userTypeWiseChildDtlsTabId"){
				$("#"+childActivityMemberId).html("<h5><span  class='text-capital'>"+selectedMemberName+"</span> - <span class='text-capitalize'>"+selectedUserType+"</span> - ( No Data Available )</h5>");
			}		  
		  }
		});
 }
  function buildActivityDirectChildDetailsRslt(result,selectedMemberName,selectedUserType,childActivityMemberId,userTypeId,attrActivityIdsString,searchType){
	 var str='';
		str+='<h4><span  class="text-capital">'+selectedMemberName+'</span> - <span class="text-capitalize">'+selectedUserType+'</span></h4>';
		 if(childActivityMemberId != "directChildMemberForEventDivId"){
				str+='<span class="remveSlcUsrTypeForEvent pull-right" attr_remove_SelecUserType = "'+childActivityMemberId+'" style="margin-top: -5px;"><i class="glyphicon glyphicon-remove"></i></span>';
		 } 
		 if(childActivityMemberId != "directChildMemberForEventDivId"){
			 str+='<table  class="table table-condensed tableHoverLevelsInner m_top20">';
		 }else{
			str+='<table class="table table-condensed tableHoverLevels m_top20">';  
		 }
				str+='<thead   class="bg_D8 text-capital">';
					str+='<th>Rank</th>';
					str+='<th>Designation</th>';
					str+='<th>Name</th>';
					str+='<th> TOTAL ACTIVITIES</th>';
					str+='<th>CONDUCTED </th>';
					str+='<th> %  </th>';
					str+='<th>NOT CONDUCTED </th>';
					str+='<th> %  </th>';
				//	str+='<th> MAYBE </th>';
				str+'=</thead>';
		str+='<tbody>';
		var rank=1;
		 for(var i in result){
		var yourValues = result[i].locationName;
		   str+='<tr  class="subLevelActivityMemberCls" attr_event_idsString="'+attrActivityIdsString+'" attr_activitymemberid = "'+result[i].activityMemberId+'" attr_usertypeid = "'+result[i].userTypeId+'" attr_selectedmembername = "'+result[i].name+'" attr_selectedusertype = "'+result[i].userType+'">';
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
			/* str+='<td style="text-align:center;">'+result[i].inviteeCnt+'</td>';
			str+='<td style="text-align:center;">'+result[i].inviteeAttendedCnt+'</td>';
			str+='<td style="text-align:center;">'+result[i].inviteeAttendedCntPer+'</td>';
			str+='<td style="text-align:center;">'+result[i].nonInviteeAttendedCnt+'</td>';
			str+='<td style="text-align:center;">'+result[i].nonInviteeAttendedCntPer+'</td>'; */
			str+='<td>'+result[i].totalActvtiesCount+'</td>';
		    str+='<td>'+result[i].condctedActiesCount+'</td>';
			str+='<td>'+result[i].conductedPerc+'</td>';
			str+='<td>'+result[i].notCondctedActiesCount+'</td>';
			str+='<td>'+result[i].notConductedPerc+'</td>';
			//str+='<td> 0 </td>';
		 str+='</tr>';
		str+='<tr class="showHideTr" style="display:none" attr_id = "subChildLevelEventMemDtslId'+result[i].userTypeId+''+i+'">';
		str+='<td colspan="8"  id="subChildLevelEventMemDtslId'+result[i].userTypeId+''+i+'">';
		str+='</td>';
		 rank=rank+1;
		 }
		str+='</tbody>';
		str+='</table>';
	$("#"+childActivityMemberId).html(str);
 }
 
 function getActivityPoorPerformanceLocation(userTypeId,activityMemberId,selectedUserName,userType,attrActivityIdsString,searchType){
$("#topPoorLocationsActivityDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
var activityIds=attrActivityIdsString.split(",");
	var jsObj ={ 
				 activityMemberId : activityMemberId,
				 stateId : globalStateId,
				 eventIds:activityIds,
				 userTypeId : userTypeId,
				 searchType : searchType
				 
			  }
	$.ajax({
		type : 'POST',
		url : 'getEventPoorPerformanceLocationAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		$("#topPoorLocationsActivityDivId").html(" ");	
		if(result != null ){
		buildActivityPoorPerformanceLocationRslt(result,userTypeId,selectedUserName,userType);	
		}
	});	
}

function buildActivityPoorPerformanceLocationRslt(result,userTypeId,selectedUserName,userType){
    var resultListFirst;
	var resultListSecond;
    var str='';
		str+='<div class="col-md-12 col-xs-12 col-sm-12"><span class="color_333 pad_5 bg_CC text-capital"><span class="text-danger">poor</span> Activity(ies) performance locations&nbsp&nbsp('+selectedUserName+" - "+userType+')</span></div>';
	   str+='<div class="col-md-6 col-xs-12 col-sm-6 m_top10">';
	   
	  if(userTypeId!= null && userTypeId==3 || userTypeId==2 || userTypeId==1){
		str+='<p class="text-capital">districts<span style="margin-left:280px">Conducted (%)</span></p>';  
		resultListFirst = result.districtList;
		resultListSecond = result.constituencyList;
	  }
	  if(userTypeId!= null && userTypeId==5 || userTypeId==11 || userTypeId==4 || userTypeId==6 || userTypeId==12 || userTypeId==14){
		str+='<p class="text-capital">Constituencies<span style="margin-left:240px">Conducted (%)</span></p>';  
		resultListFirst = result.constituencyList;
		resultListSecond = result.mandalTwnDivisionList;  
	  }
	   if(userTypeId!= null && userTypeId==7 || userTypeId==8 || userTypeId==9){
		 str+='<p class="text-capital">Mandal/Town/Division<span style="margin-left:180px">Conducted (%)</span></p>';  
		resultListFirst = result.mandalTwnDivisionList;
		resultListSecond = result.villageWardList;  
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
				str+='<div class="progress progressCustom" data-toggle="tooltip" data-placement="top" title="'+resultListFirst[i].inviteeAttendedCounPer+'%">';
			str+='<div class="progress-bar" role="progressbar" aria-valuenow="'+resultListFirst[i].inviteeAttendedCounPer+'" aria-valuemin="0" aria-valuemax="100" style="width:'+resultListFirst[i].inviteeAttendedCounPer+'%;">';
					str+='<span class="sr-only">'+resultListFirst[i].inviteeAttendedCounPer+'</span>';
				  str+='</div>';
				str+='</div>';
			str+='</td>';
			str+='<td class="text-danger">'+resultListFirst[i].inviteeAttendedCounPer+'</td>';
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
	   if(userTypeId!= null && userTypeId==3 || userTypeId==2 || userTypeId==1){
		str+='<p class="text-capital">Constituencies<span style="margin-left:240px">Conducted (%)</span></p>';  
	  }
	  if(userTypeId!= null && userTypeId==5 || userTypeId==11 || userTypeId==4 || userTypeId==6 || userTypeId==12 || userTypeId==14){
		 str+='<p class="text-capital">Mandal/Town/Division<span style="margin-left:180px">Conducted (%)</span></p>';  
	  }
	   if(userTypeId!= null && userTypeId==7 || userTypeId==8 || userTypeId==9){
		 str+='<p class="text-capital">Village/Ward<span style="margin-left:250px">Conducted (%)</span></p>';  
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
				str+='<div class="progress progressCustom" data-toggle="tooltip" data-placement="top" title="'+resultListSecond[i].inviteeAttendedCounPer+'%">';
		str+='<div class="progress-bar" role="progressbar" aria-valuenow="'+resultListSecond[i].inviteeAttendedCounPer+'" aria-valuemin="0" aria-valuemax="100" style="width:'+resultListSecond[i].inviteeAttendedCounPer+'%;">';
			str+='<span class="sr-only">'+resultListSecond[i].inviteeAttendedCounPer+'</span>';
			str+='</div>';
			str+='</div>';
			str+='</td>';
			str+='<td class="text-danger">'+resultListSecond[i].inviteeAttendedCounPer+'</td>';
			str+='</tr>';
			order=order+1;
			if(order==6)
				break;
			BGColor = BGColor - 0.2;
			}
				str+='</table>';
			}
	     str+='</div>';
																				
	 $("#topPoorLocationsActivityDivId").html(str);	
	 $('.progressCustom').tooltip()
	}
$(document).on("click",".childActivityMemberCls",function(){
	    
		$(".slickPanelSliderForEvent").find("li").find(".panelSlick").removeClass("panelActiveSlick");
		$(this).find(".panelSlick").addClass("panelActiveSlick");
	    var activityMemberId = $(this).attr("attr_activitymemberid");  
		var userTypeId = $(this).attr("attr_usertypeid"); 
    	var selectedMemberName = $(this).attr("attr_selectedmembername");  
		var selectedUserType = $(this).attr("attr_selectedusertype"); 	
		var childActivityMemberId = $(this).attr("attr_id");  
		var attrEventIdsString = $(this).attr("attr_event_idsString");
		if(selectedUserType != null && selectedUserType.trim()=="MLA/CI" || selectedUserType.trim()=="MLA" || selectedUserType.trim()=="CONSTITUENCY INCHARGE"){
		  getEventPoorPerformanceLocation(userTypeId,activityMemberId,selectedMemberName,selectedUserType,attrEventIdsString,"activities");
		 }else{
	      getDirectChildTypeMembersForActivities(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId,attrEventIdsString,"activities");
		  getEventPoorPerformanceLocation(userTypeId,activityMemberId,selectedMemberName,selectedUserType,attrEventIdsString,"activities");
		}
		
});
$(document).on("click",".subLevelActivityMemberCls",function(){
	$(this).next('tr.showHideTr').show(); 
	var activityMemberId = $(this).attr("attr_activitymemberid");  
	var userTypeId = $(this).attr("attr_usertypeid"); 
	var selectedMemberName = $(this).attr("attr_selectedmembername");  
	var selectedUserType = $(this).attr("attr_selectedusertype");  
	var childActivityMemberId = $(this).closest('tr').next('tr.showHideTr').attr("attr_id");  
	var attrEventIdsString = $(this).attr("attr_event_idsString");
	if(selectedUserType != null && selectedUserType.trim()=="MLA/CI" || selectedUserType.trim()=="MLA" || selectedUserType.trim()=="CONSTITUENCY INCHARGE"){
		  getActivityPoorPerformanceLocation(userTypeId,activityMemberId,selectedMemberName,selectedUserType,attrEventIdsString,"activities");
	}else{
	      getDirectChildTypeMembersForActivities(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId,attrEventIdsString,"activities");
		  getActivityPoorPerformanceLocation(userTypeId,activityMemberId,selectedMemberName,selectedUserType,attrEventIdsString,"activities");
	}
});
$(document).on("click",".ConstImagesClose",function(){
	$(this).removeClass("ConstImagesClose");
	setTimeout(function(){
		$("body").addClass("modal-open");
	},400)
});
$(document).on("click",".getPopUpImagesCls",function(){
	$(".imagesModalClose").addClass("ConstImagesClose");
	$('#imagesModalDivId').modal({
		show: true,
		keyboard: false,
		backdrop: 'static'
	});
	var attr_activity_scopeid = $(this).attr("attr_scope_id");
	var activityLevelId = $("#hiddenActivityLevelId").val();
	var cnstitncyId = $(this).attr("attr_constituency_id");
	var searchType = $(this).attr("attr_search_type");
	var value = $(this).attr("attr_value");
	if(searchType == "constituency"){
		getMandalOrMuncList(cnstitncyId,1,value,attr_activity_scopeid);
	}else if(searchType == "mandal"){
		getPanchayatList(cnstitncyId,attr_activity_scopeid,value);
	}/* else if(searchType == "villageWard" ||  searchType == "onlyvillage"){
		str +='<th class="text-capital">Village/Ward name</th>';
	} */
	
	var str='';
		str+='<div class="row">';
			str+='<div class="col-md-9">';
				str+='<nav class="navbar navbar-default navbarCollapseCustom">';
					str+='<div class="collapse navbar-collapse " id="bs-example-navbar-collapse-1">';
					  str+='<ul class="nav navbar-nav" id="popupDaysDiv">';
					  
					  str+='</ul>';
					str+='</div>';
				str+='</nav>';
				str+='<div class=" pad_10" id="popupImages">';
					
				str+='</div>';
				str+=' <div id="paginationDivId"></div>';
			str+='</div>';
			str+='<div class="col-md-3" style="box-shadow:0 2px 10px 0 rgba(0, 0, 0, 0.35);padding:0px">';
			if(searchType == "constituency")
				str+='<div id="mandalsUlId"></div>';
			else if(searchType == "mandal")
				str+='<div id="villageUlId"></div>';
			str+='</div>';
		str+='</div>';
	$("#buildImagesId").html(str);
	
//buildDayWiseImagesForPopup(globalPopupresult,$(this).attr("imgpath"),$(this).attr("dayattr"));
//getAvailableDates(globallocationScope,globallocationValue,day,path);

if(searchType == "constituency"){
	globalActivityScope = attr_activity_scopeid;
	getAvailablDates('constituency',cnstitncyId,1,'',attr_activity_scopeid)
	buildLocationForPopup(globallocationScope,globallocationValue,attr_activity_scopeid);
	getEventsDocuments("","",attr_activity_scopeid);
	getEventDocumentForPopup("constituency",1,0,0,'',attr_activity_scopeid,"constituency",cnstitncyId,"");
}else if(searchType == "mandal"){
	globalActivityScope = attr_activity_scopeid;
	getAvailablDates('mandal',cnstitncyId,1,'',attr_activity_scopeid)
	buildLocationForPopup(globallocationScope,globallocationValue,attr_activity_scopeid);
	getEventsDocuments("","",attr_activity_scopeid);
	getEventDocumentForPopup("mandal",1,0,0,'',attr_activity_scopeid,"mandal",cnstitncyId,"");
}else if(searchType == "villageWard" ||  searchType == "onlyvillage"){
	globalActivityScope = attr_activity_scopeid;
	getAvailablDates('village',cnstitncyId,1,'',attr_activity_scopeid)
	buildLocationForPopup(globallocationScope,globallocationValue,attr_activity_scopeid);
	getEventsDocuments("","",attr_activity_scopeid);
	getEventDocumentForPopup("village",1,0,0,'',attr_activity_scopeid,"village",cnstitncyId,"");
}

});
$(document).on("click",".radioBtnCls",function(){
	var searchType="constituency";
	var locationNm = "All";
	var locationId = 0;
	if($("#districtId").val() == 0){
			locationId = $("#districtId").val();
			searchType="constituency";
			locationNm = $('#districtId option:selected').text();
	}else{
		locationId = $("#districtId").val();
		searchType="constituency";
		locationNm = $('#districtId option:selected').text();
		if($("#constituencyId").val() == 0){
			locationId = $("#districtId").val();
			searchType="constituency";
			locationNm = $('#districtId option:selected').text();
		}else{
			if($("#mandalId").val() == 0){
				locationNm = $('#constituencyId option:selected').text();
				locationId = $("#constituencyId").val();
				searchType="mandal";
			}else{
				if($("#villgWardId").val() == 0){
					locationNm = $('#mandalId option:selected').text();
					locationId = $("#mandalId").val();
					searchType="villageWard";
				}else if($("#villgWardId").val() > 0){
					locationNm = $('#villgWardId option:selected').text();
					locationId = $("#villgWardId").val();
					searchType="onlyvillage";
				}
			}
		}
	}
	getDistrictWiseActivityCounts(globalActvtyScopeId,locationId,"change",searchType,"submit","NA","NA",locationNm);
});

	$(document).on("click",".settingsIconAct",function(e){
		$(this).closest(".eventsBlock").find(".actBlockDropDown").toggle();
		e.stopPropagation();
	});
	$(document).on("click",".actSetClose",function(){
		$(this).closest(".actBlockDropDown").hide();
	});
	
function getSettingActivities(){
	//getActivitiesDetails();
	 var type = "activities"
	 var dates = $('#dateRangeIdForEvents').val();
	  var dateArray = dates.split("-");
	  var fromDateStr=dateArray[0];
	  var toDateStr=dateArray[1];
	var jsObj={
		//fromDate: '01/01/2014',//customStartDateActivities,
		//toDate: '01/01/2020' //customEndDateActivities
		fromDate : '01/02/2015',
	    toDate : '01/02/2020'
	}	
	$.ajax({
	 type: "POST",
	 url: "getActivityDetailsAction.action",
	 data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0)
			buildActivityEventSettings(result,type);
	});
}	

function buildActivityEventSettings(result,typeVal){
	var str='';
		for(var i in result)
		{
			str+='<li>';
				str+='<label class="checkbox-inline">';
				if(typeVal == "events"){
						str+='<input type="checkbox" value="'+result[i].id+'" class="checkedEvntValCls" checked>';
					}else{
						str+='<input type="checkbox" value="'+result[i].id+'" class="checkedActValCls" checked>';
					}
						str+='<div style="margin-top: 3px;">';
						str+='<h5 class="text-capital" style="color:#54616C;">'+result[i].name+'</h5>';
					str+='</div>';
				str+='</label>';
			str+='</li>';
		}
		str+='<div class="col-md-8 col-md-offset-4 col-xs-12 col-sm-9 col-sm-offset-3">';
			str+='<button type="button" class="btn btn-success getDetailsOfSetingCls" attr_type="'+typeVal+'">Get Details</button>';
		str+='</div>';
		if(typeVal == "activities")
			$(".activitySettingsUl").html(str);
		else if(typeVal == "events")
			$(".evntsSettingsUl").html(str);
}
/*  function getSettingEvents(){
	var eventIds=[];
	eventIds.push(7);
	eventIds.push(30);
	var type="events";
	var jsObj ={ 
				 activityMemberId : globalActivityMemberId,
				 stateId : globalStateId,
				 eventIds:eventIds
				 
			  }
	$.ajax({
		type : 'POST',
		url : 'getEventBasicCntDtlsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		 if(result != null && result.length > 0 ){
			buildActivityEventSettings(result,type);	
		}
	});
} */
$(document).on("click",".getDetailsOfSetingCls",function(){
	var type=$(this).attr("attr_type");
	var evntActitiesIds = [];
	var evntActitiesIdStr = '';
	var typeId = orderTypeValue();
	if(type == "events"){
		$("#eventsDistWiseCohort1").html('');
		$("#eventsGraphBlock1").html('');
		//$(".activitiesH4").html('');
		$('.checkedEvntValCls:checked').each(function() {
			evntActitiesIds.push($(this).val());
		});
		if(evntActitiesIds != null && evntActitiesIds.length > 0){
			for(var i in evntActitiesIds){
				if(i == 0)
					evntActitiesIdStr+= evntActitiesIds[i];
				else{
					evntActitiesIdStr+= ",";
					evntActitiesIdStr+= evntActitiesIds[i];
				}
					
			}
		}
		if(typeId == 1){
			getLocationWiseByInviteeAttendedAndInviteeAttendedCntBasedOnUserType(evntActitiesIdStr);
			getSelectedEventDetails(evntActitiesIdStr);
		}else{
			$("#evntCmpBLockId").show();
			$("#activtyBlckDivId").hide();
			getAllItsSubUserTypeIdsByParentUserTypeIdForEvent(evntActitiesIdStr,type,globalUserTypeId);
		}
		//getAllItsSubUserTypeIdsByParentUserTypeIdForEvent(evntActitiesIdStr,type,globalUserTypeId);
	}else if(type == "activities"){
		$("#eventsDistWiseCohort").html('');
		$("#eventsGraphBlock").html('');
		$('.checkedActValCls:checked').each(function() {
			evntActitiesIds.push($(this).val());
		});
		if(evntActitiesIds != null && evntActitiesIds.length > 0){
			for(var i in evntActitiesIds){
				if(i == 0)
					evntActitiesIdStr+= evntActitiesIds[i];
				else{
					evntActitiesIdStr+= ",";
					evntActitiesIdStr+= evntActitiesIds[i];
				}
					
			}
		}
		if(typeId == 1){
			districtWiseCohort(evntActitiesIdStr);
			stateWiseCohort(evntActitiesIdStr);
		}else{
			$("#evntCmpBLockId").hide();
			$("#activtyBlckDivId").show();
		getAllItsSubUserTypeIdsByParentUserTypeIdForActivity(evntActitiesIdStr,type,globalUserTypeId);
		}
	}
});

function orderTypeValue(){
  var orderType = ''; 
     $('.activeLICls').each(function(){
        if($(this).hasClass("active")){
          orderType = $(this).attr("attr_typeId");
        }
     });
  return orderType;
}
function refreshEventsActivities(){
	getEventBasicCntDtls();
	getActivitiesDetails();
}