
//getLocationLevelWiseBoothDetails();
function getLocationLevelWiseBoothDetails(){

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
		url : 'getLocationLevelWiseBoothDetailsAction.action',  
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)} 
	}).done(function(result){ 
	  console.log(result);
	});
}