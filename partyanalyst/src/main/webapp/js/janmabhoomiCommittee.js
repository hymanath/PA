
getJbCommitteeStatusCount();//1st
function getJbCommitteeStatusCount(){

	var jsObj={
		"fromDateStr"	:"",
		"toDateStr"	    :""
	}
	   $.ajax({
		  type : "POST",
		  url : "getJbCommitteeStatusCountAction.action",
		  dataType : 'json',
		  data : {task :JSON.stringify(jsObj)}
		}).done(function(result){ 
			
		});
}

getDistrictWiseCommitteeDetails();//2nd
function getDistrictWiseCommitteeDetails(){
	var jsObj={
 		"fromDate"			:"",
 		"endDate"			:"",
		type:"district"//constituency,parliament
 	}
 	   $.ajax({
 		  type : "POST",
 		  url : "getDistrictWiseCommitteeDetailsAction.action",
 		  dataType : 'json',
 		  data : {task :JSON.stringify(jsObj)}
 		}).done(function(result){ 
			
 		});
  }
getJanmabhoomiCommitteeOverview();//3rd
function getJanmabhoomiCommitteeOverview(){
	var jsObj={
 		"fromDate"			:"",
 		"toDate"			:"",
		committeId:1
 	}
 	   $.ajax({
 		  type : "POST",
 		  url : "getJanmabhoomiCommitteeOverviewAction.action",
 		  dataType : 'json',
 		  data : {task :JSON.stringify(jsObj)}
 		}).done(function(result){ 
			
 		});
  }