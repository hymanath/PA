//State Level view
stateLevelDeptOfficerStatusOverview();
stateLevelDeptOfficerLocationLevelOverview();
getIASOfficerMyAlertsCountMainView();
getIASOfficerMySubTasksCountView();
getIASOfficerMyAssignedSubTasksCountView();
function stateLevelDeptOfficerStatusOverview(){
	//$("#statusOverview").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	var deptIdArr =[];
    var paperIdArr =[];
    var chanelIdArr =[];
    var jsObj ={
      fromDate:"01/01/1997",
      toDate:"01/01/2027",
      stateId : 1,
      deptIdArr : deptIdArr,  
      paperIdArr : paperIdArr,
      chanelIdArr :chanelIdArr
    }
    $.ajax({
      type:'GET',
      url: 'stateLevelDeptOfficerStatusOverViewAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
    });
}
function stateLevelDeptOfficerLocationLevelOverview(){
	//$("#statusOverview").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	var deptIdArr =[];
    var paperIdArr =[];
    var chanelIdArr =[];
    var jsObj ={
      fromDate:"01/01/1997",
      toDate:"01/01/2027",
      stateId : 1,
      deptIdArr : deptIdArr,  
      paperIdArr : paperIdArr,
      chanelIdArr :chanelIdArr
    }
    $.ajax({
      type:'GET',
      url: 'stateLevelDeptOfficerLocationLevelOverviewAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
    });
}
function getIASOfficerMyAlertsCountMainView(){

    var jsObj ={};
    $.ajax({
      type:'GET',
      url: 'getIASOfficerMyAlertsCountMainViewAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
    });
}
function getIASOfficerMySubTasksCountView(){

    var jsObj ={};
    $.ajax({
      type:'GET',
      url: 'getIASOfficerMySubTasksCountViewAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
    });
}
function getIASOfficerMyAssignedSubTasksCountView(){
    var jsObj ={};
    $.ajax({
      type:'GET',
      url: 'getIASOfficerMyAssignedSubTasksCountViewAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
    });
}
stateLevelDeptOfficerDepartmentWiseAlertsView();
function stateLevelDeptOfficerDepartmentWiseAlertsView(){
	//$("#statusOverview").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	var deptIdArr =[];
    var paperIdArr =[];
    var chanelIdArr =[];
    var jsObj ={
      fromDate:"01/01/1997",
      toDate:"01/01/2027",
      stateId : 1,
      deptIdArr : deptIdArr,  
      paperIdArr : paperIdArr,
      chanelIdArr :chanelIdArr
    }
    $.ajax({
      type:'GET',
      url: 'stateLevelDeptOfficerDepartmentWiseAlertsViewAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
    });
}
getAllDistrictDetails();
function getAllDistrictDetails(){
	var jsObj ={		
	}
	$.ajax({
		type:'GET',
		url: 'getAllDistrictDetailsAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){		
	});
}
getAllDivisionDetails();
function getAllDivisionDetails(){
	var jsObj ={
		districtId : 5,
	}
	$.ajax({
		type:'GET',
		url: 'getAllDivisionDetailsAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){		
	});
}
getAllSubDivisionDetails();
function getAllSubDivisionDetails(){
	var jsObj ={
			divisionId : 20 ,
	}
	$.ajax({
		type:'GET',
		url: 'getAllSubDivisionDetailsAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){		
	});
}
getWorkLocationWiseThenGovtDeptScopeWiseAlertCountForOverview();
function getWorkLocationWiseThenGovtDeptScopeWiseAlertCountForOverview(){
	
        var printIdArr =[];
        var electronicIdArr =[];        
	   
	var jsObj={
			fromDateStr:"01/01/1997",
			toDateStr:"01/01/2027",
			stateId : 1,
			printIdArr : printIdArr,
			electronicIdArr : electronicIdArr,
			
			
			govtDepartmentId : 0,
			parentGovtDepartmentScopeId : 6,
			sortingType : "",
			order : "",
			alertType : "",
			districtWorkLocationId : 0,
			divisionWorkLocationId : 0,
			subDivisionWorkLocationId : 0,
			group : ""
			}
	$.ajax({
		type:'GET',
		url: 'getWorkLocationWiseThenGovtDeptScopeWiseAlertCountForOverviewAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){		
	});
}
