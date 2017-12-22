
//getDeptIdsListBYUserIdsLst();
function getDeptIdsListBYUserIdsLst(){
	$("#designationrepresent").html('');
	  var json = {
			 
	  };
	$.ajax({              
		type:'POST',    
		url: 'getDeptIdsListBYUserIdsLst',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		
	});	

}

//getPmDeptStatusIdsByUserIdsLst();
function getPmDeptStatusIdsByUserIdsLst(){
	
	  var json = {
			 
	  };
	$.ajax({              
		type:'POST',    
		url: 'getPmDeptStatusIdsByUserIdsLst',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		
	});	

}
//getDistrictBySearchType();
function getDistrictBySearchType(){

var json = {
		 fromDate :"",
		 toDate:""
		}           
	$.ajax({              
		type:'POST',    
		url: 'getCompleteOrStatusOverviewDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		console.log(result)
	});	
}