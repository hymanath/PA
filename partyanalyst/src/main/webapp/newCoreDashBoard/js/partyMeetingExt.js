


getMultiLocationWiseMeetingGroupsData();
function getMultiLocationWiseMeetingGroupsData(){
	$("#MultiLocationWiseMeetingGroupsData").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
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
/*
function get111MultiLocationWiseMeetingGroupsData()
{
	$("#MultiLocationWiseMeetingGroupsData").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	
	var jObj = {
		fromDateStr : '01/02/2000'  ,//customStartDateMeetings,
		toDateStr : '01/02/2020',//customEndDateMeetings,
		activityMemberId : globalActivityMemberId,
		stateId : globalStateId,
		partyMeetingMainTypeId:4
	};
	 
	$.ajax({
	  type:'GET',
	  url: 'getMultiLocationWiseMeetingGroupsDataAction.action',
	 data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		if(result.length != null && result.length > 0 )
		{
			buildMultiLocationWiseMeetingGroupsData(result);
		}else{
			$("#MultiLocationWiseMeetingGroupsData").html('NO DATA AVAILABLE');
		}
	});
}
*/
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
								levelId = result.userAccessLevelList[i].userAccessLevelValuesList[j].levelId;
							str+='<td style="position:relative;">';
								str+='<p class="text-muted text-capital ">'+result.userAccessLevelList[i].userAccessLevelValuesList[j].name+'</p>';
								str+='<p class="multiLocationWiseMeetingCount " attr_count="'+result.userAccessLevelList[i].userAccessLevelValuesList[j].count+'" attr_group_id="'+result.userAccessLevelList[i].levelId+'" attr_levelId="'+result.userAccessLevelList[i].userAccessLevelValuesList[j].levelId+'">'+result.userAccessLevelList[i].userAccessLevelValuesList[j].count+'</p>';
							str+='</td>';							
						}
					}
						str+='</tr>';	
				str+='</table>';
				str+='<div class="row">';
					str+='<div class="col-md-12 col-xs-12 col-sm-12">';
						str+='<div class="bg_ED pad_10">';
							str+='<div id="partyLevelIdWiseMeetingsCount"></div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			}
		str+='</div>';
	str+='</div>';
	$("#MultiLocationWiseMeetingGroupsData").html(str);
	getPartyLevelIdWiseMeetingsCount(meetingGrpId,levelId,result.userAccessLevelList[i].userAccessLevelValuesList[j].count);
}
$(document).on("click",".multiLocationWiseMeetingCount",function(){
	$(this).addClass("active");
	var count = $(this).attr("attr_count");
	var attr_group_id = $(this).attr("attr_group_id");
	var attr_levelId = $(this).attr("attr_levelId");
	
	getPartyLevelIdWiseMeetingsCount(attr_group_id,attr_levelId,count);
});

function getPartyLevelIdWiseMeetingsCount(meetingGrpId,levelId,count)
{
	
	$("#partyLevelIdWiseMeetingsCount").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');

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
		buildPartyLevelIdWiseMeetingsCount(result,count);
	});
}

function buildPartyLevelIdWiseMeetingsCount(result,count)
{
	var str='';
	str+='<table class="table bg_ED">';
		str+='<tr>';
			str+='<td>';
				str+='<p class="text-muted">Planned</p>';
				str+='<p>'+count+'</p>';	
			str+='</td>';
			str+='<td class="bg_E0">';
				str+='<p class="text-muted">Conducted</p>';
				if(result.conducted != null && result.conducted > 0)
				{
					str+='<p>'+result.conducted+'</p>';
				}else{
					str+='<p> - </p>';
				}
			str+='</td>';
			str+='<td>';
				str+='<p class="text-muted">Not Conducted</p>';
				str+='<p>'+((count)-(result.conducted))+'  <small></small></p>';	
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
					str+='<p>'+result.totalImages+'</p>';	
				else
					str+='<p> - </p>';					
			str+='</td>';
		str+='</tr>';
		str+='<tr>';
			str+='<td class="bg_E0">';
			if(result.invited != null && result.invited > 0)
			{
				str+='<p>'+result.invited+'</p>';
			}
			else
				str+='<p> - </p>';
				str+='<p class="text-muted">Invited</p>';
			str+='</td>';
			str+='<td class="bg_E0">';
			if(result.attended != null && result.attended > 0)
			{
				str+='<p>'+result.attended+'</p>';
			}
			else
				str+='<p> - </p>';
				str+='<p class="text-success">Attended</p>';
			str+='</td>';
			
			str+='<td class="bg_E0">';
			if(result.late != null && result.late > 0)
			{
					str+='<p>'+result.late+'</p>';
			}else
				str+='<p> - </p>';
				str+='<p class="text-danger">Late</p>';
			str+='</td>';
			str+='<td class="bg_E0">';
			if(result.absent != null && result.absent > 0)
			{				
				str+='<p>'+result.absent+'</p>';
			}else
				str+='<p> - </p>';
				str+='<p class="text-muted">Absent</p>';
			str+='</td>';
			str+='<td class="bg_E0">';
			if(result.nonInvitee != null && result.nonInvitee > 0)
			{				
				str+='<p>'+result.nonInvitee+'</p>';				
			}
			else
				str+='<p> - </p>';
			str+='<p class="text-muted">Non-Invitee</p>';
				str+='</td>';
				
		str+='</tr>';
		
		if(result.subList != null && result.subList.length>0){
			str+='<tr><td colspan="5"></td></tr>';
			/*
			str+='<tr>';
					str+='<td class="bg_E0">';
						str+='<p> </p>';
					str+='</td>';
					str+='<td class="bg_E0">';
							str+='<p class="text-success">Attended</p>';
					str+='</td>';
					str+='<td class="bg_E0">';
						str+='<p class="text-danger">Late</p>';
					str+='</td>';
					str+='<td class="bg_E0">';
						str+='<p class="text-muted">Absent</p>';
					str+='</td>';
					str+='<td class="bg_E0">';
						str+='<p class="text-muted">Non-Invitee</p>';
					str+='</td>';
				str+='</tr>';
				*/
			for(var k in result.subList){
				str+='<tr>';
					
						str+='<td class="bg_E0">';
							str+='<p>'+result.subList[k].name+'</p>';
						str+='</td>';
					
					if(result.subList[k].inviteeAttendedCount != null && result.subList[k].inviteeAttendedCount > 0)
					{
						str+='<td class="bg_E0">';
								str+='<p>'+result.subList[k].inviteeAttendedCount+'</p>';
								//str+='<p class="text-success">Attended</p>';
						str+='</td>';
					}
					else{
						str+='<td class="bg_E0">';
								str+='<p> - </p>';								
						str+='</td>';
					}
					if(result.subList[k].lateCount != null && result.subList[k].lateCount > 0)
					{
						str+='<td class="bg_E0">';
							str+='<p>'+result.subList[k].lateCount+'</p>';
							//str+='<p class="text-danger">Late</p>';
						str+='</td>';
					}else{
						str+='<td class="bg_E0">';
								str+='<p> - </p>';								
						str+='</td>';
					}
					var absent =result.subList[k].absentCount;
					
					if(absent != null && absent > 0)
					{
						str+='<td class="bg_E0">';
							str+='<p>'+absent+'</p>';
							//str+='<p class="text-muted">Absent</p>';
						str+='</td>';
					}else{
						str+='<td class="bg_E0">';
								str+='<p> '+absent+' </p>';								
						str+='</td>';
					}
					
					if(result.subList[k].nonInviteeCount != null && result.subList[k].nonInviteeCount > 0)
					{
						str+='<td class="bg_E0">';
							str+='<p>'+result.subList[k].nonInviteeCount+'</p>';
							//str+='<p class="text-muted">Non-Invitee</p>';
						str+='</td>';
					}else{
						str+='<td class="bg_E0">';
								str+='<p> - </p>';								
						str+='</td>';
					}
						
				str+='</tr>';
			}
		}
	str+='</table>';
	$("#partyLevelIdWiseMeetingsCount").html(str);
}

function getAttendedCadreDetails(){
	
	$("#partyLevelIdWiseMeetingsCount").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');

	var jObj = {
		fromDateStr : '01/02/2000'  ,//customStartDateMeetings,
		toDateStr : '01/02/2020',//customEndDateMeetings,
		activityMemberId : globalActivityMemberId,
		stateId : globalStateId,
		partyMeetingMainTypeId:4,
		partyMeetingLevelId:levelId,
		meetingGrpId:meetingGrpId,
		sessionId:1,
		cadreType:"inviteeAttended"
	};
	
	$.ajax({
	  type:'GET',
	  url: 'getPartyLevelIdWiseMeetingsAttendanceDetailsAction.action',
	 data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		buildPartyLevelIdWiseMeetingsAttendanceDetails(result);
	});
	
}

function buildPartyLevelIdWiseMeetingsAttendanceDetails(result){
	console.log(result);	
}