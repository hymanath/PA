
//getLocationLevelWiseBoothCount();
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