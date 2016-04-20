//getCadreDetailsAction.action
function getCadreDetails(jsObj){
	$.ajax({    
			type : "POST",
			url : "getCadreDetailsAction.action",
			data : {task:JSON.stringify(jsObj)} ,   
		}).done(function(result){
			$(".paginationDivId").show();
			if(typeof result == "string"){
				if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
				  location.reload(); 
				}
			}
			$("#searchDataImg").hide();
			$('#cadreDetailsDiv').show();
			if(result != null && result.previousRoles != null && result.previousRoles.length>0)
			{
				buildCadreDetails(result.previousRoles,jsObj);
			}
			else
			{
				$(".cadreMemberListCls").show();
				$('#cadreDetailsDiv').show();
				$('#cadreDetailsDiv').html("<span style='font-weight:bold;text-align:center;'> No Data Available...</span>");
			}
		});
}	

//generateOTPForMobileNumberAction.action
function generateOTPForMobileNumber(jsObj){
	$.ajax({    
		type : "POST",
		url : "generateOTPForMobileNumberAction.action",
		data : {task:JSON.stringify(jsObj)} ,   
	}).done(function(result){
		if(result != null && result == "Success"){
			$(currentButton).removeAttr('disabled');
		}
	}); 
}	

//validateOTPAction.action
function validateOTPAction(jsObj){
	$.ajax({    
		type : "POST",  
		url : "validateOTPAction.action",
		data : {task:JSON.stringify(jsObj)} ,   
	}).done(function(result){
		if(result != null && result=="Success"){
			//$("#otpSuccessDiv").html("success");
			$("#success").show();
			$("#nextStepId").show();
			$(".otpCheckboxCls").prop("disabled", true);
			
		}else{
			$("#fail").show();
		}
	});
}	