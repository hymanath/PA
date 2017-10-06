
	getAllNominatedStatusListLevelWiseDataDashBoard(); //1st block Call
	getAreaWiseDashboardCandidatesCountView();//second block
	getLocationWiseNominatedPostAnalysisDetails("ageGroup");
	getLocationWiseNominatedPostAnalysisDetails("casteGroup");
	getLocationWiseNominatedPostAnalysisDetails("subCaste");
	getLocationWiseNominatedPostAnalysisDetails("mandal");
	
	function getAllNominatedStatusListLevelWiseDataDashBoard(){
		var jsObj={
			boardLevelId:2,  //state level 1 //parliament -4 or constituenct -4 // mandal Or muni -5 // panchayat/ward -7 //
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
	
	
	function getAreaWiseDashboardCandidatesCountView(){
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
 
 
 function getLocationWiseNominatedPostAnalysisDetails(type){
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
	
	