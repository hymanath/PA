 var globalStateId=1; //default AP
 var globalUserWiseEventMemberRslt;
 /* EVENT FUNCTIONALITY START*/  
  $(document).on("click",".eventsIconExpand",function(){
	$(".dateRangePickerClsForEvents").toggleClass("hide");
	$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
	$(".eventYearExpandIcon").find("i").addClass("glyphicon-fullscreen").removeClass("glyphicon-resize-small");
	$(".eventsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	$(".eventsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
	if($(this).find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		
		var eventIdsString="7,30";
		
		$(".moreEventsBlocksIcon").attr("attr_type","event");
	    $(".moreEventsBlocksIcon").attr("attr_event_idsString",eventIdsString);
		$(".detailedEvent").attr("attr_type","event");
	    $(".detailedEvent").attr("attr_event_idsString",eventIdsString);
		$(".comparisonEvent").attr("attr_type","event");
		$(".comparisonEvent").attr("attr_event_idsString",eventIdsString);
		
		getUserTypeWiseTotalInviteeAndInviteeAttendedCnt(eventIdsString);
		$(".eventsHiddenBlock,.moreEventsBlocksIcon").show();
	}else{
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
	
});

$(document).on("click",".moreEventsBlocksIcon",function(){
	var type=$(this).attr("attr_type");
	var attrEventIdsString=$(this).attr("attr_event_idsString");
	if(type != null && type=="event"){
	 $(".moreEventsBlocks").toggle();
	 getLocationWiseByInviteeAttendedAndInviteeAttendedCntBasedOnUserType(attrEventIdsString);
	 getSelectedEventDetails(attrEventIdsString);
	 $(".detailedBlockEvents,.activeUlCls").show();
	 $(".detailedEvent").addClass("active")	
	 $(".comparisonEvent").removeClass("active")	
	}else{
		// activity functionality
	}
});
$(document).on("click",".detailedEvent",function(){
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
	getAllItsSubUserTypeIdsByParentUserTypeIdForEvent(attrEventIdsString);
});

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
var customStartDateActivities = moment().subtract(2,"year").format('DD/MM/YYYY');
var customEndDateActivities = moment().format('DD/MM/YYYY');  
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
})
$('#dateRangeIdForEvents').on('apply.daterangepicker', function(ev, picker) {
  customStartDateActivities = picker.startDate.format('DD/MM/YYYY');
  customEndDateActivities = picker.endDate.format('DD/MM/YYYY');  
});

function getEventBasicCntDtls(){
	$("#mainEventsList").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	buildEventBasicCntDtls();
	return;
	var eventIds=[];
	eventIds.push(7);
	eventIds.push(30);
	
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
		}else{
		 $("#mainEventsList").html("NO DATA AVAILABLE.");	
		}
	});
}
function buildEventBasicCntDtls()
{ 
	var eventIdsString;
	var str=' ';
	//str+='<div class="panel-group m_top10" id="accordionEvents" role="tablist" aria-multiselectable="true">';
	/*	for(var i in result)
		{
			if(i== 0){
			eventIdsString = result[i].id;	
			}else{
			 eventIdsString = eventIdsString+','+result[i].id;	
			}
			str+='<div class="panel panel-default panelNewEvents">';
				str+='<div class="panel-heading" role="tab" id="headingEvents'+i+'">';
					str+='<h4 class="panel-title">'+result[i].name+'';
						str+='<span attr_event_idsString='+result[i].id+' class="eventsListExpandIcon eventCls" style="background-color:#fff;font-size:10px;margin-left:5px;"><i class="glyphicon glyphicon-fullscreen"></i></span>';
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
					
			//	str+='</div>';
				/* if(i == 0)
				{
					str+='<div id="collapseEvents'+i+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingEvents'+i+'">';
				}else{
					str+='<div id="collapseEvents'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingEvents'+i+'">';
				} */
					/*str+='<div class="panel-body pad_5">';
						str+='<div class="row">';
							str+='<div class="col-md-12 col-xs-12 col-sm-12">';
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
							str+='</div>';
						str+='</div>';
						
					//str+='</div>';
				str+='</div>';
			str+='</div>';
		}
	//str+='</div>';
	//$("#eventIds").attr("attr_event_idsString",eventIdsString);*/
	
	
	     str+='<div class="panel panel-default panelNewEvents">';
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
	$("#eventIds").attr("attr_event_idsString","7,30"); 
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
										return Highcharts.numberFormat(this.percentage,1) + '%';
									}
								}
							  
							}
						}
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
	getSelectedChildTypeMembersForEvent(childUserTypeId,attrEventIdsString,childUserType);
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
		  getEventPoorPerformanceLocation(userTypeId,activityMemberId,selectedMemberName,selectedUserType,attrEventIdsString);
		 }else{
	      getDirectChildTypeMembersForEvent(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId,attrEventIdsString);
		  getEventPoorPerformanceLocation(userTypeId,activityMemberId,selectedMemberName,selectedUserType,attrEventIdsString);
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
		  getEventPoorPerformanceLocation(userTypeId,activityMemberId,selectedMemberName,selectedUserType,attrEventIdsString);
	}else{
	      getDirectChildTypeMembersForEvent(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId,attrEventIdsString);
		  getEventPoorPerformanceLocation(userTypeId,activityMemberId,selectedMemberName,selectedUserType,attrEventIdsString);
	}
});
 function getAllItsSubUserTypeIdsByParentUserTypeIdForEvent(attrEventIdsString){
	     
		  $("#childEvnetMemberDivId").html(' ');
		  $("#directChildMemberForEventDivId").html(' ');
		  $("#topPoorLocationsEventDivId").html(' ');
		  
		 $("#allItsSubUserTypeIdsByParentUserTypeDivIdForEvent").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var jsObj = {parentUserTypeId : globalUserTypeId}
		$.ajax({
			type : 'POST',
			url : 'getAllItsSubUserTypeIdsByParentUserTypeIdAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#allItsSubUserTypeIdsByParentUserTypeDivIdForEvent").html(" ");	
			if(result != null && result.length > 0){
			buildgetChildUserTypesByItsParentUserTypeForEvent(result,attrEventIdsString);	
			}else{
			$("#allItsSubUserTypeIdsByParentUserTypeDivIdForEvent").html("NO DATA AVAILABLE");	
			}
		});		 
	}
	
function buildgetChildUserTypesByItsParentUserTypeForEvent(result,attrEventIdsString){
		var str='';
		 str+='<ul class="comparisonSelect">';
		 
		 var firstChildUserTypeIdString;
		 var userType;
		 if(result !=null && result.length >0){
			  firstChildUserTypeIdString = result[0].shortName;
			  userType=result[0].userType;
			 for(var i in result){
				 str+='<li attr_userTypeId="'+result[i].shortName+'" attr_userType=\''+result[i].userType+'\'  attr_event_idsString='+attrEventIdsString+' class="allItsSubUserTypeClsForEvent">'+result[i].userType+'<span class="closeIconComparison"></span></li>';
			 }
		 }
		str+='</ul>';
		$("#allItsSubUserTypeIdsByParentUserTypeDivIdForEvent").html(str);
		$(".comparisonSelect li:first-child").addClass("active")
		
		getSelectedChildTypeMembersForEvent(firstChildUserTypeIdString,attrEventIdsString,userType);
	}
function getSelectedChildTypeMembersForEvent(firstChildUserTypeIdString,attrEventIdsString,childUserType){
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
				   eventIds:eventIds
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
			  buildChildTypeMembersForEventReslt(result,attrEventIdsString,childUserType);
		  }else{
			  $("#childEvnetMemberDivId").html("NO DATA AVAILABLE");
		  }
		});
 }
 function buildChildTypeMembersForEventReslt(result,attrEventIdsString,childUserType){
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
	  getEventPoorPerformanceLocation(userTypeId,activityMemberId,selectedMemberName,selectedUserType,attrEventIdsString);
	  }else{
	  str+='<ul class="list-inline slickPanelSliderForEvent">';
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
     getEventPoorPerformanceLocation(userTypeId,activityMemberId,selectedMemberName,selectedUserType,attrEventIdsString);
	getDirectChildTypeMembersForEvent(activityMemberId,userTypeId,selectedMemberName,selectedUserType,"directChildMemberForEventDivId",attrEventIdsString);		
	  }		
 }
function getDirectChildTypeMembersForEvent(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId,attrEventIdsString){
	  $("#"+childActivityMemberId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
			     var eventIds=attrEventIdsString.split(",");
		         var childUserTypeIdsArray=[];
	             childUserTypeIdsArray.push(userTypeId);
	  var jsObj ={   activityMemberId : activityMemberId,
			         childUserTypeIdsArray : childUserTypeIdsArray,
					 reportType : "directChild",
					 stateId : globalStateId,
					 eventIds:eventIds
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

function getEventPoorPerformanceLocation(userTypeId,activityMemberId,selectedUserName,userType,attrEventIdsString){
$("#topPoorLocationsEventDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
var eventIds=attrEventIdsString.split(",");
	var jsObj ={ 
				 activityMemberId : activityMemberId,
				 stateId : globalStateId,
				 eventIds:eventIds,
				 userTypeId : userTypeId
				 
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
	  if(userTypeId!= null && userTypeId==5 || userTypeId==11 || userTypeId==4 || userTypeId==6){
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
	  if(userTypeId!= null && userTypeId==5 || userTypeId==11 || userTypeId==4 || userTypeId==6){
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

function getActivitiesDetails(){
	$("#activityEventsListNew").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	
	var jsObj={
		fromDate:customStartDateActivities,
		toDate: customEndDateActivities
	}	
	$.ajax({
	 type: "POST",
	 url: "getActivityDetailsAction.action",
	 data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0)
			buildActivityEventBasicCntDtlsNew(result);
	});
}
function buildActivityEventBasicCntDtlsNew(result)
{
	var str='';
	var activityIdsString='';
    str+='<div class="row">';
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
			str+='<div class="">';
				str+='<div class="panel-group panelBlockCollapse" id="accordionAct" role="tablist" aria-multiselectable="true" style="margin-top: 10px;">';
				for(var i in result)
				{
				  if(i== 0){
					activityIdsString = result[i].id;	
					}else{
					 activityIdsString = activityIdsString+','+result[i].id;	
					}
					str+='<div class="panel panel-default">';
						str+='<div class="panel-heading" role="tab" id="headingOneAct'+i+'">';
							str+='<h4 class="text-capital" style="color:#4a5863">'+result[i].name+'';
							str+='<span class="activitesExpandIcon activityCls"  attr_level_id="0" attr_activity_name="\''+result[i].name+'\'" attr_id="'+result[i].id+'"><i class="glyphicon glyphicon-fullscreen text-center"></i>';
							str+='<a role="button" class="panelBlockCollapseIcon collapsed activitiesClass" attr_activity_name="\''+result[i].name+'\'" data-toggle="collapse" data-parent="#accordionAct" href="#collapseOneAct'+i+'" aria-expanded="true" aria-controls="collapseOneAct'+i+'" attr_id="'+result[i].id+'" attr_divId="activityBodyId'+i+'">';
							str+='</a></span></h4>';
						str+='</div>';
						str+='<div id="collapseOneAct'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOneAct'+i+'">';
							str+='<div class="panel-body">';
								str+='<div id="activityBodyId'+i+'"></div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				}
				
				str+='</div>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	$("#activityEventsListNew").html(str);
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
		activityId:activityId
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
		
		str+='<div class="m_top20">';
			str+='<h5 class="text-capital">'+result[i].name+' <span class="activitesExpandIcon activityCls" attr_level_id="'+result[i].id+'"  attr_activity_name='+activityName+' attr_id="'+activityId+'"><i class="glyphicon glyphicon-fullscreen"></i> </span></h5>';
			str+='<table class="table bg_ED tablePaddingSyle">';
				str+='<tbody>';
					str+='<tr>';
						str+='<td>';
							str+='<p class="text-muted text-capital">total</p>';
							str+='<h5>'+result[i].apTotal+'</h5>';
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
						str+='<td>';
							//str+='<button type="button" class="btn btn-success text-capital getImageCls">get Images</button>';
						str+='</td>';
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
						}else{
							str+='<td>';
							str+='<p class="text-muted text-capital">attended Count </p>';
							str+='<h5 style="cursor:pointer;" data-toggle="tooltip" data-placement="top" > - </h5>';
							str+='</td>';
						}
					
						str+='<td>';
							str+='<p class="text-muted text-capital">Planned</p>';
							if(result[i].inviteeCount != null && result[i].inviteeCount>0)
								str+='<u><h5 style="cursor:pointer;" class="activityCountCls" attr_actvty_scope_id="'+result[i].tdpcadreId+'" style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title="Total Planned Locations" >'+result[i].inviteeCount+' <small><span class="text-success">'+result[i].mobileNumber+'%</span></small></h5><u>';
							else
								str+='<h5 style="cursor:pointer;" class="" attr_actvty_scope_id="'+result[i].tdpcadreId+'" data-toggle="tooltip" data-placement="top" >'+result[i].inviteeCount+' <small><span class="text-success">'+result[i].mobileNumber+'%</span></small></h5>';
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
						str+='<td>';
							//str+='<button type="button" class="btn btn-success text-capital getImageCls">get Images</button>';
						str+='</td>';
						str+='<td>';
							str+='<p class="text-muted text-capital">Images Covered</p>';
							if(result[i].totalImages != null && result[i].totalImages>0)
								str+='<u><h5 style="cursor:pointer;" class="getImageCls" attr_activity_scopeid="'+result[i].tdpcadreId+'" style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title="Click here to view images." >'+result[i].imagesCovered+' <small><span class="text-success">'+result[i].totalImages+' images covered</span></small></h5></u>';
							else
								str+='<h5 style="cursor:pointer;" class="" attr_activity_scopeid="'+result[i].tdpcadreId+'" data-toggle="tooltip" data-placement="top"  >'+result[i].imagesCovered+' <small><span class="text-success">'+result[i].totalImages+' images covered</span></small></h5>';
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
	$("#locatnNamId").text('');
	if(refresh == "onload"){
		$("#constncyDivId").hide();
		$("#mandalDivId").hide();
		$("#villgWardDivId").hide();
		$("#districtId").val(0);
	}
	if(activityScopeId == 0){
		var t = type.split("-");
		activityScopeId = parseInt(t[0]);
		districtId = parseInt(t[1]);
		type="all";
		globalActvtyScopeId = activityScopeId;
		acvtyNm = t[2];
		levlNm = t[3];
		loctnNm = t[4];
	}
	
	$("#activityId").html('<div style="text-align: center" ><img src="./images/Loading-data.gif" /></div>');
	
	$("#activityId").html("");
	var jsObj={
		districtId : districtId,
		activity_scope_id:activityScopeId,
		search_type :searchType,
		stateId : globalStateId
	}	
	$.ajax({
	 type: "POST",
	 url: "getDistrictWiseActivityCountsAction.action",
	 data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		//if(result != null && result.length > 0){
			buildDistrictWiseActivitiesCount(result,type,refresh,acvtyNm,levlNm,loctnNm,searchType);
		//}else{
		//		$("#activityId").html('<div style="text-align: center;font-weight:bold;" > No data available...</div>');
		//}
	});
}

function buildDistrictWiseActivitiesCount(result,type,refresh,acvtyNm,levlNm,loctnNm,searchType){
	var str = '';
	 if(refresh == "onload"){
		 $("#myModelActivityhead").text(acvtyNm);
		 $("#smallHeadngId").text(levlNm);
	}
	
	$("#locatnNamId").text(loctnNm +'  DETAILS');
	if(type == "count")
		$("#myModelActivityId").modal('show');
	else if(type == "all")
		$("#myModelActivityId").modal('show');
	$("#activityId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');	
	if(result != null && result.length > 0){
	 str +='<table class="table table-bordered table-condensed " id="activityTableId">';
	 str+='<thead style="background-color:#EEE">';
	str +='<tr>';
	if(searchType == "constituency"){
		str +='<th class="text-capital">Constituency name</th>';
	}else if(searchType == "mandal"){
		str +='<th class="text-capital">Mandal/Town/Division name</th>';
	}else if(searchType == "villageWard"){
		str +='<th class="text-capital">Village/Ward name</th>';
	}
	str +='<th class="text-capital">total</th>';
    //str +='<th class="text-capital">Planned</th>';
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
	str +='<td>'+result[i].attendedCount+'</td>';
	//str +='<td>'+result[i].inviteeCount+'</td>';
	str +='<td>'+result[i].inviteeNotAttendedCount+'</td>';
	str +='<td>'+result[i].inviteeAttendedCount+'</td>';
	str +='<td>'+result[i].imagesCovered+'</td>';
	str +='<td>'+result[i].totalImages+'</td>';
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
$(document).on("click",".submitCls",function(){
	//$("#districtId").val('');
	var searchType="constituency";
	var locationNm = "All";
	var locationId = 0;
	if($("#districtId").val() == 0){
		locationId = 0;
		searchType="constituency";
		locationNm = "All Districts";
	}else{
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
				}else{
					locationNm = $('#villgWardId option:selected').text();
					locationId = $("#villgWardId").val();
					searchType="villageWard";
				}
			}
		}
	}
	getDistrictWiseActivityCounts(globalActvtyScopeId,locationId,"change",searchType,"submit","NA","NA",locationNm);
	
});
$(document).on("change",".districtCls",function(){

	var districtId = $(this).val();
	//getDistrictWiseActivityCounts(globalActvtyScopeId,districtId,"change");
	if(districtId == 0){
		$("#constituencyId").val(0);
		$("#mandalId").val(0);
		$("#villgWardId").val(0);
		$("#mandalDivId").hide();
		$("#villgWardDivId").hide();
	}else {
		//if(globalActivityLvlId == 5 || globalActivityLvlId == 2 || globalActivityLvlId == 1 )
		$("#constncyDivId").show();
		getConstituenciesForDistrict(districtId);
	}
	
});
$(document).on("change",".constituencyCls",function(){

	var constId = $(this).val();
	if(constId == 0){
		$("#mandalId").val(0);
	}else {
		//if(globalActivityLvlId == 2 || globalActivityLvlId == 1)
		$("#mandalDivId").show();
		getMandalsByConstituency(constId);
	}
	
});
$(document).on("change",".mandalsCls",function(){
	var constId = $("#constituencyId").val();
	var mandalId = $(this).val();
	if(mandalId == 0){
		$("#villgWardId").val(0);
	}else {
		//if(globalActivityLvlId == 1)
		$("#villgWardDivId").show();
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
	getDistrictWiseActivityCounts(activityScopeId,0,"count","constituency","onload",activityName,levelName,"All")
	
});

$(document).on("click",".activitesExpandIcon",function(){
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
});
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
	$(this).removeClass("acitivitiesMoreExpand");
	$(".moreEventsBlocks").toggle();
	var activityId = $("#hiddenActivityId").val();
		  $(".moreEventsBlocks").toggle();
			districtWiseCohort(activityId);
			//activitiesQuestions(activityId);
			$(".detailedBlockEvents,.activeUlCls").show();
	        $(".detailedEvent").addClass("active")	
	        $(".comparisonEvent").removeClass("active")
	
});
function districtWiseCohort(activityId){
	$("#eventsDistWiseCohort1").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
    //var eventIds = attrEventIdsString.split(",");
	var jsObj ={ 
		// activityId : [1,3,4,5,6,7,8,26,27,28,29,30], 
		 activityId : [activityId],
	   fromDate : customStartDateActivities,
	   toDate : customEndDateActivities
		 
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
		str+='<h4 class="panel-title">'+result[i].name+'</h4>';
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
$(document).on("click",".getImageCls",function(){
	$("#myModalImageId").modal("show");
	var attr_activity_scopeid = $(this).attr('attr_activity_scopeid');
	$("#hiddenActivityScopeId").val(attr_activity_scopeid);
	//alert(attr_activity_scopeid);
	getDistrictNames();
	var str='';
		str+='<div class="row">';
			str+='<div class="col-md-9">';
				str+='<nav class="navbar navbar-default navbarCollapseCustom">';
					str+='<div class="collapse navbar-collapse " id="bs-example-navbar-collapse-1">';
					  str+='<ul class="nav navbar-nav" id="popupDaysDiv">';
					  
					  str+='</ul>';
					str+='</div>';
				str+='</nav>';
				str+='<div class="bg_CC pad_10" id="popupImages">';
					
				str+='</div>';
				str+=' <div id="paginationDivId"></div>';
			str+='</div>';
			str+='<div class="col-md-3" style="box-shadow:0 2px 10px 0 rgba(0, 0, 0, 0.35);padding:0px">';
				str+='<div id="districtsUlId"></div>';
			str+='</div>';
		str+='</div>';

		
	$("#buildPoupupImage").html(str);
		
//buildDayWiseImagesForPopup(globalPopupresult,$(this).attr("imgpath"),$(this).attr("dayattr"));
//getAvailableDates(globallocationScope,globallocationValue,day,path);
globalActivityScope = attr_activity_scopeid;
 getAvailablDates('state',1,1,'',attr_activity_scopeid)
buildLocationForPopup(globallocationScope,globallocationValue,attr_activity_scopeid);
getEventsDocuments("","",attr_activity_scopeid);
getEventDocumentForPopup("district",1,0,0,'',attr_activity_scopeid,"state",1);
});

 $(document).on('click','.daysCls',function(){
	 $(".daysCls").removeClass("active" )
	 $(this).addClass("active");
	 var day = $(this).attr("attr");
	 var locationScope = $(this).attr("locationScope");
	 var locationScopeValue = $(this).attr("locationScopeValue");
	 var attr_activity_scopeid = $(this).attr("attr_activity_scopeid");
	 var path = $(this).attr("path");
	 
		getEventDocumentForPopup(GlobalPopupScope,GlobalPopuplocation,day,0,path,attr_activity_scopeid,locationScope,locationScopeValue);
  });
  
function getEventsDocuments(divId,Obj,attr_activity_scopeid)
{
	//$('#'+divId+'').html('<div style="text-align: center" ><img src="./images/Loading-data.gif" /></div>');
	// var dates = $('.dateRangeIdForEvents').val();
	//  var dateArray = dates.split("/");
	//  var fromDateStr=dateArray[0];
	//  var toDateStr=dateArray[1];
	// var activityId = $("#ActivityList").val();
	  /* if(dateArray.length == 1)
			{
				fromDateStr=" ";
				toDateStr=" ";
			} */
		var jObj = {
		activityId:attr_activity_scopeid,
		locationScope:"state",
		locationValue:1,		
		day:1,
		fromDateStr:"01-01-2015",
		toDateStr:"22-01-2017",
		locationName:Obj.locationName,
		startIndex:0,
		maxIndex:0,
		callFrom:"BD",
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
			buildDayWiseImages(result,divId);
			
			});
}

function getAvailablDates(locationScope,locationValue,day,path,attr_activity_scopeid)
	{
		
	  $("#popupDaysDiv").html('<img src="./images/Loading-data.gif" />');
	 /* var dates = $('.dateRangeIdForEvents').val();
	  var dateArray = dates.split("/");
	  var fromDateStr=dateArray[0];
	  var toDateStr=dateArray[1];*/
	  var activityScopeId = $("#ActivityList").val();
	
		var jObj = {
		activityId:attr_activity_scopeid,
		locationScope:locationScope,
		locationValue:locationValue,	
		fromDateStr:"01-01-2016",
		toDateStr:"01-01-2017",
		task:""
		};
		$.ajax({
          type:'GET',
          url: 'getAvailableDatesForActivitiesAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
				var str ='';
				for(var i in result)
				{
					if(result[i].id==day)
					{//attr_activity_scopeid,locationScope,locationScopeValue
						str+='<li class="active daysCls"  locationScope="'+locationScope+'"   locationScopeValue="'+locationValue+'"  path = "'+path+'" attr_activity_scopeid="'+attr_activity_scopeid+'" attr="'+result[i].id+'"><a href="#">Day '+result[i].id+' <span class="sr-only">(current)</span></a></li>';
						getEventDocumentForPopup(jObj.locationScope,jObj.locationValue,day,0,path,attr_activity_scopeid,locationScope,locationValue);
					}
					
					  else
					str+='<li class="daysCls" locationScope="'+locationScope+'"   locationScopeValue="'+locationScopeValue+'"  attr_activity_scopeid="'+attr_activity_scopeid+'" attr="'+result[i].id+'"><a href="#">Day '+result[i].id+' <span class="sr-only">(current)</span></a></li>';
				}
				$("#popupDaysDiv").html(str);
				GlobalPopupScope = jObj.locationScope;
				GlobalPopuplocation =jObj.locationValue;
				
		});
}

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

function getEventDocumentForPopup(searchType,locationId,day,num,path,attr_activity_scopeid,locationScope,locationScopeValue)
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
		
		var jObj = {
		activityId:globalActivityScope,
		locationScope:locationScope,
		locationValue:locationScopeValue,		
		day:day,
		fromDateStr:"01-01-2015",
		toDateStr:"22-01-2017",
		type:"popup",
		startIndex:num,
		maxIndex:10,
		callFrom:"",
		//locationName:obj.locationName,
		 task:"popupdaywise"
		};
		
		$.ajax({
          type:'GET',
          url: 'getEventDocumentsAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			buildDayWisImagesForPopup1(result,jObj,path,attr_activity_scopeid,locationScope,locationScopeValue);
			});
}


function buildDayWisImagesForPopup1(result,jObj,path,attr_activity_scopeid,locationScope,locationScopeValue)
{
	$("#popupImages").html('');
	var str ='';

	if(result != null)
	{
	
		str+='<ul class="slider-for">';
		//if(path != null && path.length>0)
			//str+='<li><img src="https://mytdp.com/activity_documents/' +path+'"></li>';
			for(var i in result)
			{
			for(var j in result[i].subList)
			{
				
				str+='<li><img src="https://mytdp.com/activity_documents/' +result[i].subList[j].path+'"></li>';
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
					getEventDocumentForPopup(jObj.locationScope,jObj.locationValue,jObj.day,num,path,attr_activity_scopeid,locationScope,locationScopeValue);
					
				}
			});
			$("#paginationDivId").find("ul").addClass("pagination");
		}
		
	GlobalPopupScope = globallocationScope;
	GlobalPopuplocation =globallocationValue;
	
	}
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
function getDistrictNames(){
	
	$("#districtsUlId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	var scopeId = $("#hiddenActivityScopeId").val();
	var jObj = {
		activityScopeId:scopeId
	};
	
	$.ajax({
	  type:'GET',
	  url: 'getDistrictListAction.action',
	 data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		buildDistrictNames(result);
	});
}
function buildDistrictNames(result)
{
	var str='';
	str+='<div class="panel-group" id="accordionModal" role="tablist" aria-multiselectable="true">';
	for(var i in result)
	{
	  str+='<div class="panel panel-default panel-custommodal">';
		str+='<div class="panel-heading panel-headingModal" role="tab" id="headingOneModal'+i+'">';
		  str+='<a role="button" class="constituencyPopups accordionmodal-toggle collapsed" data-toggle="collapse" data-parent="#accordionModal" attr_distId="'+result[i].districtId+'" href="#collapseOneModal'+i+'" aria-expanded="true" aria-controls="collapseOneModal'+i+'">';
			str+='<h4 class="panel-title">'+result[i].name+'('+result[i].count+')</h4>';
		  str+='</a>';
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
	getConstituencyList(distId);
});

function getConstituencyList(distId){
	$('.daysCls').each(function(){
		if($(this).hasClass("active")){
			 var day = $(this).attr("attr");
			var locationScope = 'district';
			var locationScopeValue = distId;
			var attr_activity_scopeid = $(this).attr("attr_activity_scopeid");
			var path = $(this).attr("path");
			getEventDocumentForPopup(GlobalPopupScope,GlobalPopuplocation,day,0,path,attr_activity_scopeid,locationScope,locationScopeValue);
		}
	});
	
	$("#constituenciesBlock"+distId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	var scopeId = $("#hiddenActivityScopeId").val();
	var jObj = {
		activityScopeId:scopeId,
		districtId : distId
	};
	
	$.ajax({
	  type:'GET',
	  url: 'getConstituencyListAction.action',
	 data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		buildConstituencyList(result,distId);
	});
}
function buildConstituencyList(result,distId)
{
	var str='';
	str+='<div class="panel-group" id="accordionModalCons'+distId+'" role="tablist" aria-multiselectable="true">';
	for(var i in result)
	{
	  str+='<div class="panel panel-default panel-custommodal">';
		str+='<div class="panel-heading panel-headingModal" role="tab" id="headingOneModalCons'+i+'">';
		  str+='<a role="button" class="mandalPopups accordionmodal-toggle collapsed" data-toggle="collapse" data-parent="#accordionModalCons'+distId+'" attr_consId="'+result[i].constituencyId+'" href="#collapseOneModalCons'+i+'" aria-expanded="true" aria-controls="collapseOneModalCons'+i+'">';
			str+='<h4 class="panel-title">'+result[i].name+'('+result[i].count+')</h4>';
		  str+='</a>';
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
	getMandalOrMuncList(constituencyId);
});

function getMandalOrMuncList(constituencyId){
	
	$('.daysCls').each(function(){
		if($(this).hasClass("active")){
			 var day = $(this).attr("attr");
			var locationScope = 'constituency';
			var locationScopeValue = constituencyId;
			var attr_activity_scopeid = $(this).attr("attr_activity_scopeid");
			var path = $(this).attr("path");
			getEventDocumentForPopup(GlobalPopupScope,GlobalPopuplocation,day,0,path,attr_activity_scopeid,locationScope,locationScopeValue);
		}
	});
	
	$("#mandalsBlock"+constituencyId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	var scopeId = $("#hiddenActivityScopeId").val();
	var jObj = {
		activityScopeId:scopeId,
		constituencyId : constituencyId
	};
		
	$.ajax({
	  type:'GET',
	  url: 'getMandalOrMuncpalityListAction.action',
	 data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		buildMandalOrMuncList(result,constituencyId)
	});
}
function buildMandalOrMuncList(result,constituencyId)
{
	var str='';
	str+='<div class="panel-group" id="accordionModalMandal'+constituencyId+'" role="tablist" aria-multiselectable="true">';
	for(var i in result)
	{
	  str+='<div class="panel panel-default panel-custommodal">';
		str+='<div class="panel-heading panel-headingModal" role="tab" id="headingOneModalMandal'+i+'">';
		  str+='<a role="button" class="panchayatPopups accordionmodal-toggle collapsed" data-toggle="collapse" data-parent="#accordionModalMandal'+constituencyId+'" attr_mandalId="'+result[i].mandalId+'" href="#collapseOneModalMandal'+i+'" aria-expanded="true" aria-controls="collapseOneModalMandal'+i+'">';
			str+='<h4 class="panel-title">'+result[i].name+'('+result[i].count+')</h4>';
		  str+='</a>';
		str+='</div>';
		str+='<div id="collapseOneModalMandal'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOneModalMandal'+i+'">';
		  str+='<div class="panel-body pad_0"><div id="panchayatBlock'+result[i].mandalId+'"></div></div>';
		str+='</div>';
	  str+='</div>';
	}
	str+='</div>';
	$("#mandalsBlock"+constituencyId).html(str);
}
$(document).on("click",".panchayatPopups",function(){
	var mandalId = $(this).attr("attr_mandalId");
	getPanchayatList(mandalId)
});

function getPanchayatList(mandalId){
	
	$('.daysCls').each(function(){
		if($(this).hasClass("active")){
			 var day = $(this).attr("attr");
			var locationScope = 'mandal';
			var locationScopeValue = mandalId;
			var attr_activity_scopeid = $(this).attr("attr_activity_scopeid");
			var path = $(this).attr("path");
			getEventDocumentForPopup(GlobalPopupScope,GlobalPopuplocation,day,0,path,attr_activity_scopeid,locationScope,locationScopeValue);
		}
	});
	
	$("#panchayatBlock"+mandalId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	var scopeId = $("#hiddenActivityScopeId").val();
	var jObj = {
		activityScopeId:scopeId,
		mandalOrMuncipalityId : mandalId
	};
	
	$.ajax({
	  type:'GET',
	  url: 'getPanchayatOrWardListAction.action',
	 data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		buildPanchayatList(result,mandalId)
	});
}
function buildPanchayatList(result,mandalId)
{
	var str='';
	str+='<ul class="villageDaysModal">';
		for(var i in result)
		{
			str+='<li><span class="line"/></span><a href="#">'+result[i].name+'</a></li>';
		}
	 str+='</ul>';
	$("#panchayatBlock"+mandalId).html(str);
}

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
	var jsObj ={ 
				 activityMemberId : globalActivityMemberId,
				 stateId : globalStateId,
				 activityIds:activityIds,
				 activityLevelIds:activityLevelIds,
				 userTypeId : globalUserTypeId,
				 fromDateStr:customStartDateActivities,
				 toDateStr:customEndDateActivities
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
