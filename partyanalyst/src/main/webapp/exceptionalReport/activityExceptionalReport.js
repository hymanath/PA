
getActivityPerformanceDetailsLocationWise();
getActivityAttendedAndImageCoveredDetails();

function getActivityPerformanceDetailsLocationWise() {
	var jsObj = {
		activityScopeId : 60,
		fromDate : "01/01/2018",
		toDate : "31/01/2018"
	}
	$.ajax({
		type : 'POST',
		url : 'getActivityPerformanceDetailsLocationWiseAction.action',
		dataType : 'json',
		data : {task : JSON.stringify(jsObj)}
	}).done(function(result) {
		console.log(result);
	});
}
function getActivityAttendedAndImageCoveredDetails() {
	var jsObj = {
		activityScopeId : 60,
		fromDate : "01/01/2018",
		toDate : "31/01/2018"

	}
	$.ajax({
		type : 'POST',
		url : 'getActivityAttendedAndImageCoveredDetailsAction.action',
		dataType : 'json',
		data : {task : JSON.stringify(jsObj)}
	}).done(function(result) {
		console.log(result);
	});
}