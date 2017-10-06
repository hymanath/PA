getLocationWiseNominatedPostCandidateAgeRangeAndCasteCategorDetails();
function getLocationWiseNominatedPostCandidateAgeRangeAndCasteCategorDetails(){
	 var jobj = {
		locationValues : [218],
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
	getAreaWiseDashboardCandidatesCountView();
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
	getAllNominatedStatusListLevelWiseDataDashBoard();
	function getAllNominatedStatusListLevelWiseDataDashBoard(){
	alert(6);
		var jsObj={
			boardLevelId:2,
			levelValues:[1],
			levelId:2
		}
		$.ajax({   
			type:'GET',
			url:'getAllNominatedStatusListLevelWiseDataDashBoardAction.action',  
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			
		});
	}