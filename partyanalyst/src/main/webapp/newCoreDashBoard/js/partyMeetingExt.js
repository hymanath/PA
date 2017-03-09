
getMultiLocationWiseMeetingGroupsData();
function getMultiLocationWiseMeetingGroupsData(){
	$("#popupImages").html('<img src="./images/Loading-data.gif" />');
	 var dates=$('.searchDateCls ').val();

	var jObj = {
		fromDateStr : '01/01/2017',
		toDateStr : '01/02/2018',
		activityMemberId : 44,
		stateId : globalStateId,
		partyMeetingMainTypeId:4
	};
	 
	$.ajax({
	  type:'GET',
	  url: 'getMultiLocationWiseMeetingGroupsDataAction.action',
	 data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		console.log(result);
	});
}
function buildMultiLocationWiseMeetingGroupsData()
{
	
	
}
//getPartyLevelIdWiseMeetingsCount();
function getPartyLevelIdWiseMeetingsCount(){
	$("#popupImages").html('<img src="./images/Loading-data.gif" />');
	var dates=$('.searchDateCls ').val();

	var jObj = {
		fromDateStr : '01/01/2017',
		toDateStr : '01/02/2018',
		activityMemberId : 44,
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
		console.log(result);
	});
}