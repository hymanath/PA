/*
DISTRICT
PARLIAMENT CONSTITUENCY
CONSTITUENCY
TEHSIL
PANCHAYAT

NotStarted
Started
Completed
*/
//getLocationLevelWiseBoothCount();
//getLocationBasedOnSelection();
//getLocationLevelWiseBoothDetails();
//validateBoothToMakeConfirm();
function getLocationLevelWiseBoothCount(){

	var jsObj={  
		locationLevel : "Constituency",         
		filterLevel : "District",
		filterValue : 19,
		boothInchargeEnrollmentId : 1,
		startDate : "13/07/2017",
		endDate : "13/07/2017"
	} 
	$.ajax({
		type : 'POST',
		url : 'getLocationLevelWiseBoothCountAction.action',  
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)} 
	}).done(function(result){ 
	  console.log(result);
	});
}
function getLocationBasedOnSelection(){
	var jsObj={  
		locationLevel : "Constituency",         
		filterLevel : "District",
		filterValue : 19,
		boothInchargeEnrollmentId : 1,
	
	} 
	$.ajax({
		type : 'POST',
		url : 'getLocationBasedOnSelectionAction.action',  
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)} 
	}).done(function(result){ 
	  console.log(result);
	});
}

function getLocationLevelWiseBoothDetails(){

	var jObj={  
		filterType : "Tehsil",
		filterValue : "1240",
		fromDate : "13/07/2017",
		toDate : "13/07/2017",
		boothEnrollementYearId : 1,
		resultType : "NotStarted"
	} 
	$.ajax({
		type : 'POST',
		url : 'getLocationLevelWiseBoothDetailsAction.action',  
		dataType : 'json',
		data : {task :JSON.stringify(jObj)} 
	}).done(function(result){ 
	  console.log(result);
	});
}

function validateBoothToMakeConfirm(){

	var jObj = {  
			boothId : 922852,
			boothInchargeEnrollmentId : 1
		} 
	$.ajax({
		type : 'POST',
		url : 'validateBoothToMakeConfirmAction.action',  
		dataType : 'json',
		data : {task :JSON.stringify(jObj)} 
	}).done(function(result){ 
	  console.log(result);
	});
}