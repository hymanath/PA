
getDeptIdsListBYUserIdsLst();
function getDeptIdsListBYUserIdsLst(){
	$("#designationrepresent").html('');
	  var json = {
			  userId:"2"
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

getPmDeptStatusIdsByUserIdsLst();
function getPmDeptStatusIdsByUserIdsLst(){
	
	  var json = {
			  userId:"2"
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