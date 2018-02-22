alert("alert");  
getAssignedCandidateWisePendingAlerts();
function getAssignedCandidateWisePendingAlerts(){  
	var jsObj = { 
		fromDateStr : "01/01/2010",
		toDateStr : "01/01/2050",
		stateId : 1,
		size : 50,
		alertTypeIds : [1]
	}
	$.ajax({
		type : 'POST',      
		url : 'getAssignedCandidateWisePendingAlerts.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		console.log(result);   
	});
}
getOverAllAlertsDetails();
function getOverAllAlertsDetails(){  
	var jsObj = { 
		fromDateStr : "01/01/2010",
		toDateStr : "01/01/2050",
		stateId : 1,
		size : 50,
		alertTypeIds : [1]
	}
	$.ajax({
		type : 'POST',      
		url : 'getOverAllAlertsDetails.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		console.log(result);   
	});         
}

