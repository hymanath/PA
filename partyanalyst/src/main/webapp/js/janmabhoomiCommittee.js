getDistrictWiseCommitteeDetails();
function getDistrictWiseCommitteeDetails(){
	var jsObj={
 		"fromDate"			:"",
 		"endDate"			:"",
		type:"district"
 	}
 	   $.ajax({
 		  type : "POST",
 		  url : "getDistrictWiseCommitteeDetailsAction.action",
 		  dataType : 'json',
 		  data : {task :JSON.stringify(jsObj)}
 		}).done(function(result){ 
			
 		});
  }
//getJbCommitteeStatusCount();
function getJbCommitteeStatusCount(){

	var jsObj={
		"fromDateStr"	:"",
		"toDateStr"	    :""
	}
	   $.ajax({
		  type : "POST",
		  url : "janmabhoomiCommitteeAction.action",
		  dataType : 'json',
		  data : {task :JSON.stringify(jsObj)}
		}).done(function(result){ 
			
		});
}