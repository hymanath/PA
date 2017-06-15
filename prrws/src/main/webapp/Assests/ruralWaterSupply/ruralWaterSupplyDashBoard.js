onloadCalls();
function onloadCalls(){
	getHabitationCoverageByStatusByLocationType();
	getLabTestDetails();
	getHabitationSupplyDetails();
	getSchemesDetails();
	getSchemeWiseWorkDetails();
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

function getHabitationSupplyDetails(){
	var json = {
		year:"2017"
	  }
	$.ajax({
		url: 'getHabitationSupplyDetails',
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
function getSchemesDetails(){
	var json = {
		fromDateStr:"01-12-2016",
		toDateStr:"01-12-2017"
	  }
	$.ajax({
		url: 'getSchemesDetails',
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
function getSchemeWiseWorkDetails(){
	var json = {
			formDateStr:"01-01-2016",
			toDateStr:"01-04-2017"
	  }
	$.ajax({
		url: 'getSchemeWiseWorkDetails',
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