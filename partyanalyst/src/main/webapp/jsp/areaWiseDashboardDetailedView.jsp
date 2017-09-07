//getLocationWiseNominatedPostCandidateAgeRangeAndCasteCategorDetails();
function getLocationWiseNominatedPostCandidateAgeRangeAndCasteCategorDetails(){
	 var jobj = {
		levelValues : [218],
		statusIds :[3,4],
		levelId :4,
		type : "ageRange"//or(casteCategory)
	 }
		$.ajax({
			type : "POST",
			url  : "getLocationWiseNominatedPostCandidateAgeRangeAndCasteCategorDetailsAction.action",
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			
		});
 }