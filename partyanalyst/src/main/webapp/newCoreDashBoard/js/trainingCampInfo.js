//Training Camp Info
var getDocumentWidthForCampInfo = $(document).width();
var globalStateIdForCampInfo=1; //default Ap
$("#tdpTriningCampYearId").on('change', function() {
	var enrollmentYearId=$("#tdpTriningCampYearId").val();
});
var globalUserWiseMemberRsltForTrainingCamp;
getUserTypeWiseTotalEligibleAndAttendedCnt();
function getUserTypeWiseTotalEligibleAndAttendedCnt(){
	$("#userTypeWiseTrainingCampInfoTopFiveStrongAndPoorMemsDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	var dateStr = '';
	var enrollmentYrIds = [];
	enrollmentYrIds.push(2);
	var programIdArr = [];//[8]
	if(enrollmentYrIds != null && enrollmentYrIds.length>0){
		for(var i in enrollmentYrIds){
			if(parseInt(enrollmentYrIds[i]) == 2){
				programIdArr=[];
				programIdArr=[8];
			}else  if(parseInt(enrollmentYrIds[i]) == 1){
			}
		}
	}
	var jsObj ={
		userAccessLevelId:globalUserAccessLevelId,
		userAccessLevelValuesArray:globalUserAccessLevelValues,
		activityMemberId : globalActivityMemberId,
		userTypeId : globalUserTypeId,
		stateId : globalStateIdForCampInfo,
		dateStr : dateStr,
		enrollmentYrIds:enrollmentYrIds,
		programIdArr:programIdArr
	}
		
	$.ajax({
		type : 'POST',
		url : 'getUserTypeWiseTotalEligibleAndAttendedCntActionForTrainingCamp.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		 $("#userTypeWiseTrainingCampInfoTopFiveStrongAndPoorMemsDivId").html(' ');
		 //buildUserTypeWiseTrainingProgramAttendedCountTopFiveStrongResults(result);
		 globalUserWiseMemberRsltForTrainingCamp = result;
		 alert("result");
	});
}