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
 
 //getLocationWiseNominatedPostAnalysisDetails("ageGroup");
//	getLocationWiseNominatedPostAnalysisDetails("casteGroup");
//	getLocationWiseNominatedPostAnalysisDetails("subCaste");
	//getLocationWiseNominatedPostAnalysisDetails("mandal");
function getLocationWiseNominatedPostAnalysisDetails(type){
	alert(4)
		var jsObj={
			boardLevelId:4,
			levelValues:["282"],
			levelId:4,
			dataType:type,
			statusIds:[3,4]
		}
		$.ajax({   
			type:'GET',
			url:'getLocationWiseNominatedPostAnalysisDetailsAction.action',  
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			
		});
	}
	//getAreaWiseDashboardCandidatesCountView();
	function getAreaWiseDashboardCandidatesCountView(){
	alert(5)
		var jsObj={
			levelValues:["282"],
			levelId:4,
			statusIds:[3,4]
		}
		$.ajax({   
			type:'GET',
			url:'getAreaWiseDashboardCandidatesCountViewAction.action',  
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			
		});
	}