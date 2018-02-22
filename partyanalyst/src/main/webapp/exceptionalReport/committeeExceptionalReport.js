getTdpMandalTowndivisionCommitteePerformanceDetails();
getTdpVillageWardCommitteePerformanceDetails();
getMandalTowndivisionAffliatedTdpCommitteePerformanceDetails();
getBoothInchargeCommitteePerformanceDetails();
function getTdpMandalTowndivisionCommitteePerformanceDetails() {
	var jsObj = {
		tdpCommitteeLevel : "mandalTownDivision",
		fromDate : "",
		toDate : "",
		tdpBasicCommitteeIds : [ 1 ],
		tdpCommitteeEnrollmentId : 2,
		stateId : 1

	}
	$.ajax({
		type : 'POST',
		url : 'getCommiteeOverviewPerformanceDetailsAction.action',
		dataType : 'json',
		data : {
			task : JSON.stringify(jsObj)
		}
	}).done(function(result) {
		console.log(result);
	});
}
function getTdpVillageWardCommitteePerformanceDetails() {
	var jsObj = {
		tdpCommitteeLevel : "villageWard",
		fromDate : "",
		toDate : "",
		tdpBasicCommitteeIds : [ 1 ],
		tdpCommitteeEnrollmentId : 2,
		stateId : 1

	}
	$.ajax({
		type : 'POST',
		url : 'getCommiteeOverviewPerformanceDetailsAction.action',
		dataType : 'json',
		data : {
			task : JSON.stringify(jsObj)
		}
	}).done(function(result) {
		console.log(result);
	});
}

function getMandalTowndivisionAffliatedTdpCommitteePerformanceDetails() {
	var affliatedCommitteeIdArr = [2,3,4,6,7,8,9,11];
	var jsObj = {
		tdpCommitteeLevel : "mandalTownDivision",
		fromDate : "",
		toDate : "",
		tdpBasicCommitteeIds : affliatedCommitteeIdArr,
		tdpCommitteeEnrollmentId : 2,
		stateId : 1

	}
	$.ajax({
		type : 'POST',
		url : 'getCommiteeOverviewPerformanceDetailsAction.action',
		dataType : 'json',
		data : {
			task : JSON.stringify(jsObj)
		}
	}).done(function(result) {
		console.log(result);
	});
}
function getBoothInchargeCommitteePerformanceDetails() {
	var jsObj = {
		fromDate : "",
		toDate : "",
		boothInchargeEnrollmentId : 1,
		stateId :1
	}
	$.ajax({
		type : 'POST',
		url : 'getBoothInchargeCommitteePerformanceDetailsAction.action',
		dataType : 'json',
		data : {
			task : JSON.stringify(jsObj)
		}
	}).done(function(result) {
		console.log(result);
	});
}