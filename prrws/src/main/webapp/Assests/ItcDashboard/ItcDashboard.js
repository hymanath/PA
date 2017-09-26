var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';


/*getPromotionsOverviewByDepartmentType();
getPromotionsDetailedDepartmentWise();
getEOfficePendencyDtlsByDepartmentType();
getEOfficePendencyByDepartmentAndDayWise();*/
getMeesevaSLAOverviewDtls();
getMeesevaSLAMonitoringDtlsDepartmentWise();
/*getMeesevaKPIIndicatorsProgressDtls();
getMeesevaKPIIndicatorsPeriodWise();
getCMEDOBOverview();
getCMEDOBReportStatusWise();
getAPInnovationSocietyOverview();
getAPISXLR8APDetailedData();
getCampaignsDetailedData();
getCampusInnovationCentersDetailedData();
getSuoMotoProposalsDetailedData();
getInnovationAwardsDetailedData();*/

function getPromotionsOverviewByDepartmentType(){
	var json = {
		fromDate:"",
		toDate:"",
		year:""
	}
	$.ajax({                
		type:'POST',    
		url: 'getPromotionsOverviewByDepartmentType',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		console.log(result);
	});		
}
function getPromotionsDetailedDepartmentWise(){
	var json = {
		fromDate:"",
		toDate:"",
		year:""
	}
	$.ajax({                
		type:'POST',    
		url: 'getPromotionsDetailedDepartmentWise',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		console.log(result);
	});		
}
function getEOfficePendencyDtlsByDepartmentType(){
	var json = {
		fromDate:"",
		toDate:"",
		year:""
	}
	$.ajax({                
		type:'POST',    
		url: 'getEOfficePendencyDtlsByDepartmentType',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		console.log(result);
	});		
}
function getEOfficePendencyByDepartmentAndDayWise(){
	var json = {
		fromDate:"",
		toDate:"",
		year:""
	}
	$.ajax({                
		type:'POST',    
		url: 'getEOfficePendencyByDepartmentAndDayWise',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		console.log(result);
	});		
}
function getMeesevaSLAOverviewDtls(){
	var json = {
	    fromDate:"01/04/2016",
		toDate:"01/09/2017",
		year:"",
		filterId:"2",
		year:""
	}
	$.ajax({                
		type:'POST',    
		url: 'getMeesevaSLAOverviewDtls',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		console.log(result);
	});		
}
function getMeesevaSLAMonitoringDtlsDepartmentWise(){
	var json = {
		fromDate:"01/04/2016",
		toDate:"01/09/2017",
		year:"",
		filterId:"2"
	}
	$.ajax({                
		type:'POST',    
		url: 'getMeesevaSLAMonitoringDtlsDepartmentWise',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		console.log(result);
	});		
}
function getMeesevaKPIIndicatorsProgressDtls(){
	var json = {
		fromDate:"",
		toDate:"",
		year:""
	}
	$.ajax({                
		type:'POST',    
		url: 'getMeesevaKPIIndicatorsProgressDtls',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		console.log(result);
	});		
}
function getMeesevaKPIIndicatorsPeriodWise(){
	var json = {
		fromDate:"",
		toDate:"",
		year:""
	}
	$.ajax({                
		type:'POST',    
		url: 'getMeesevaKPIIndicatorsPeriodWise',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		console.log(result);
	});		
}
function getCMEDOBOverview(){
	var json = {
		fromDate:"",
		toDate:"",
		year:""
	}
	$.ajax({                
		type:'POST',    
		url: 'getCMEDOBOverview',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		console.log(result);
	});		
}
function getCMEDOBReportStatusWise(){
	var json = {
		fromDate:"",
		toDate:"",
		year:""
	}
	$.ajax({                
		type:'POST',    
		url: 'getCMEDOBReportStatusWise',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		console.log(result);
	});		
}
function getAPInnovationSocietyOverview(){
	var json = {
		fromDate:"",
		toDate:"",
		year:""
	}
	$.ajax({                
		type:'POST',    
		url: 'getAPInnovationSocietyOverview',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		console.log(result);
	});		
}
function getAPISXLR8APDetailedData(){
	var json = {
		fromDate:"",
		toDate:"",
		year:""
	}
	$.ajax({                
		type:'POST',    
		url: 'getAPISXLR8APDetailedData',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		console.log(result);
	});		
}
function getCampaignsDetailedData(){
	var json = {
		fromDate:"",
		toDate:"",
		year:""
	}
	$.ajax({                
		type:'POST',    
		url: 'getCampaignsDetailedData',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		console.log(result);
	});		
}
function getCampusInnovationCentersDetailedData(){
	var json = {
		fromDate:"",
		toDate:"",
		year:""
	}
	$.ajax({                
		type:'POST',    
		url: 'getCampusInnovationCentersDetailedData',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		console.log(result);
	});		
}function getSuoMotoProposalsDetailedData(){
	var json = {
		fromDate:"",
		toDate:"",
		year:""
	}
	$.ajax({                
		type:'POST',    
		url: 'getSuoMotoProposalsDetailedData',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		console.log(result);
	});		
}function getInnovationAwardsDetailedData(){
	var json = {
		fromDate:"",
		toDate:"",
		year:""
	}
	$.ajax({                
		type:'POST',    
		url: 'getInnovationAwardsDetailedData',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		console.log(result);
	});		
}