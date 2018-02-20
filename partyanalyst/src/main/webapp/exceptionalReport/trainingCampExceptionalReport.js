alert(1);
getOverallTrainingCampReport();
getListOfParliamentsWithPoorPerformance();
getListOfAssemblyWithPoorPerformance();    

function getOverallTrainingCampReport(){  
	var jsObj = { 
		fromDateStr : "01/01/2017",
		toDateStr : "01/01/2018",
		stateId : 1,
		size:5
	}
	$.ajax({
		type : 'POST',      
		url : 'getOverallTrainingCampReport.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		console.log(result);
	});
}

function getListOfParliamentsWithPoorPerformance(){  
	var jsObj = { 
		fromDateStr : "01/01/2017",
		toDateStr : "01/01/2018",
		stateId : 1,
		size:5
	}
	$.ajax({
		type : 'POST',      
		url : 'getListOfParliamentsWithPoorPerformance.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		console.log(result);
	});
}


function getListOfAssemblyWithPoorPerformance(){    
	var jsObj = { 
		fromDateStr : "01/01/2017",
		toDateStr : "01/01/2018",
		stateId : 1,
		size:5
	}
	$.ajax({
		type : 'POST',
		url : 'getListOfAssemblyWithPoorPerformance.action',       
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		console.log(result);
	});
}

	