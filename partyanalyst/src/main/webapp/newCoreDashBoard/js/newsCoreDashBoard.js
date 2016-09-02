function getNewsBasicCounts(){
	var startDate,endDate;
	var state = globalState;
	var jsObj ={
			userAccessLevelId : globalUserAccessLevelId,
			userAccessLevelValuesArray : globalUserAccessLevelValues,
			state : state,
			startDate : startDate,
			endDate : endDate
	}
	
	$.ajax({
		url: wurl+"/CommunityNewsPortal/webservice/getNewsBasicCounts/"+userAccessLevelId+"/"+userAccessLevelValuesArray+"/"+state+"/"+startDate+"/"+endDate+""
	}).then(function(result){
		if(result != null && result.length > 0){
			
		}
	});
}