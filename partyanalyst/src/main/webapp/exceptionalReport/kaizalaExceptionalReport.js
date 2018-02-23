alert("kaizala");
getConstituencyWisePoorPerformance();
function getConstituencyWisePoorPerformance(){  
	var jsObj = { 
		stateId : 1,
		size : 50,
		location : "parliament"//constituency
	}
	$.ajax({
		type : 'POST',      
		url : 'getConstituencyWisePoorPerformance.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		console.log(result);   
	});
}
getOverallReport();
function getOverallReport(){  
	var jsObj = { 
		stateId : 1
	}
	$.ajax({
		type : 'POST',      
		url : 'getOverallReport.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		console.log(result);   
	});
}      