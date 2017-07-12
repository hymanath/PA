
//getLocationLevelWiseBoothCount();
//getLocationBasedOnSelection();
function getLocationLevelWiseBoothCount(){

	var jsObj={  
		locationLevel : "District",         
		filterLevel : " ",
		filterValue : 0,
		boothInchargeEnrollmentId : 0,
		startDate : "",
		endDate : ""
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
 alert(11);
	var jsObj={  
		locationLevel : "District",         
		filterLevel : " ",
		filterValue : 0,
		boothInchargeEnrollmentId : 0,
	
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