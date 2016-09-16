function getEventBasicCntDtls(){
	 	var eventIds=[];
		eventIds.push(7);
		eventIds.push(30);
		
		var jsObj ={ 
		             activityMemberId : globalActivityMemberId,
					 stateId : 1,
					 eventIds:eventIds
					 
				  }
		$.ajax({
			type : 'POST',
			url : 'getEventBasicCntDtlsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
	//	console.log(result);
		});
}
function getUserTypeWiseTotalInviteeAndInviteeAttendedCnt(){
		var eventIds=[];
		eventIds.push(7);
		eventIds.push(30);
		
		var jsObj ={ 
		             activityMemberId : globalActivityMemberId,
					 stateId : 1,
					 eventIds:eventIds,
					 userTypeId : globalUserTypeId
					 
				  }
		$.ajax({
			type : 'POST',
			url : 'getUserTypeWiseTotalInviteeAndInviteeAttendedCntAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		});
}