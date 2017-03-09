

function getMultiLocationWiseMeetingGroupsData()
{
	$("#MultiLocationWiseMeetingGroupsData").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	
	var jObj = {
		fromDateStr :customStartDateMeetings,
		toDateStr : customEndDateMeetings,
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
function buildMultiLocationWiseMeetingGroupsData(result)
{
	var str='';
	str+='<div class="row">';
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
			for(var i in result.userAccessLevelList)
			{
				str+='<h4 class="panel-title">'+result.userAccessLevelList[i].name+'</h4>';
				str+='<table class="table table-bordered bg_ED">';
					str+='<tr>';
					for(var j in result.userAccessLevelList[i].userAccessLevelValuesList)
					{
						if(result.userAccessLevelList[i].userAccessLevelValuesList[j].count != null && result.userAccessLevelList[i].userAccessLevelValuesList[j].count > 0)
						{
							str+='<td style="position:relative;">';
								str+='<p class="text-muted">'+result.userAccessLevelList[i].userAccessLevelValuesList[j].name+'</p>';
								str+='<p class="multiLocationWiseMeetingCount " attr_count="'+result.userAccessLevelList[i].userAccessLevelValuesList[j].count+'">'+result.userAccessLevelList[i].userAccessLevelValuesList[j].count+'</p>';	
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
}
$(document).on("click",".multiLocationWiseMeetingCount",function(){
	$(this).addClass("active");
	var count = $(this).attr("attr_count");
	getPartyLevelIdWiseMeetingsCount(count);
});

function getPartyLevelIdWiseMeetingsCount(count)
{
	$("#partyLevelIdWiseMeetingsCount").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');

	var jObj = {
		fromDateStr :customStartDateMeetings,
		toDateStr : customEndDateMeetings,
		activityMemberId : globalActivityMemberId,
		stateId : globalStateId,
		partyMeetingMainTypeId:4,
		partyMeetingLevelId:3,
		meetingGrpId:1
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
				}
			str+='</td>';
			str+='<td>';
				str+='<p class="text-muted">Not Conducted</p>';
				str+='<p>'+((count)-(result.conducted))+'  <small></small></p>';	
			str+='</td>';
			str+='<td>';
				str+='<p class="text-muted">Image Covered</p>';
				str+='<p>0</p>';	
			str+='</td>';
			str+='<td>';
				str+='<p class="text-muted">Images</p>';
				str+='<p>0</p>';	
			str+='</td>';
		str+='</tr>';
		str+='<tr>';
			if(result.invited != null && result.invited > 0)
			{
				str+='<td class="bg_E0">';
					str+='<p>'+result.invited+'</p>';
					str+='<p class="text-muted">Invited</p>';
				str+='</td>';
			}
			if(result.attended != null && result.attended > 0)
			{
				str+='<td class="bg_E0">';
						str+='<p>'+result.attended+'</p>';
						str+='<p class="text-success">Attended</p>';
				str+='</td>';
			}
			if(result.late != null && result.late > 0)
			{
				str+='<td class="bg_E0">';
					str+='<p>'+result.late+'</p>';
					str+='<p class="text-danger">Late</p>';
				str+='</td>';
			}
			if(result.absent != null && result.absent > 0)
			{
				str+='<td class="bg_E0">';
					str+='<p>'+result.absent+'</p>';
					str+='<p class="text-muted">Absent</p>';
				str+='</td>';
			}
			if(result.nonInvitee != null && result.nonInvitee > 0)
			{
				str+='<td class="bg_E0">';
					str+='<p>'+result.nonInvitee+'</p>';
					str+='<p class="text-muted">Non-Invitee</p>';
				str+='</td>';
			}
				
		str+='</tr>';
		
	str+='</table>';
	$("#partyLevelIdWiseMeetingsCount").html(str);
}