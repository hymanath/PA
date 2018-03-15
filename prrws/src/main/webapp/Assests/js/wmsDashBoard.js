var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
getWorkTypeWiseCompletedDetails();
function getWorkTypeWiseCompletedDetails(){
	
	var json = {};
	$.ajax({                
		type:'POST',    
		url: 'getWorkTypeWiseCompletedDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildWorkTypeWiseCompletedDetails(result);
		}
	});
}
function buildWorkTypeWiseCompletedDetails(result){
	
}