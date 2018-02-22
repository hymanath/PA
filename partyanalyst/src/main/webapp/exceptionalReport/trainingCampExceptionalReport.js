getListOfParliamentsWithPoorPerformance();
getListOfAssemblyWithPoorPerformance();    


function getListOfParliamentsWithPoorPerformance(){  
	var jsObj = { 
		fromDateStr : "01/01/2017",
		toDateStr : "01/01/2018",
		stateId : 1,
		size : 5,
		tdpCommitteeLevelIds : [5,6,7,8,9],
		enrollmentYearIds : [4],
		trainingCampProgramIds : [8],   
		locationLevelId : 2,
		locationLevelValues : []
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
		size : 5,
		tdpCommitteeLevelIds : [5,6,7,8,9],
		enrollmentYearIds : [4],
		trainingCampProgramIds : [8,9,10],
		locationLevelId : 2,
		locationLevelValues : []
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

	