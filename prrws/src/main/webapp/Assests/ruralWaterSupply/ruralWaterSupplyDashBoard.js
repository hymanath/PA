onloadCalls();
function onloadCalls(){
	getHabitationCoverageByStatusByLocationType();
	getLabTestDetails();
}

function getHabitationCoverageByStatusByLocationType(){
	var json = {
		locationType:"state",
		year:"2017"
	  }
	$.ajax({
		url: 'getHabitationCoverageByStatusByLocationType',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			
		}
	});
}

function getLabTestDetails(){
	var json = {
		year:"2017"
	}
	$.ajax({
		url: 'getLabTestDetails',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			
		}
	});
}