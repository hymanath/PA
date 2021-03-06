//getCadreDetailsAction.action
function getCadreDetails(jsObj){
	$.ajax({    
			type : "POST",
			url : "getCadreDetailsAction.action",
			data : {task:JSON.stringify(jsObj)} ,   
		}).done(function(result){
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
function generateOTPForMobileNumber(jsObj,currentButton){ 
	$.ajax({    
		type : "POST",
		url : "generateOTPForMobileNumberAction.action",
		data : {task:JSON.stringify(jsObj)} ,   
	}).done(function(result){
		updateOTPDetails(result,currentButton);		
	}); 
}	

//validateOTPAction.action
function validateOTPAction(jsObj){
	$.ajax({    
		type : "POST",  
		url : "validateOTPAction.action",
		data : {task:JSON.stringify(jsObj)} ,   
	}).done(function(result){
		//$("#validateOTPImg,").hide();
		if(result != null && result=="Success"){
			//$("#otpSuccessDiv").html("success");
			$("#otpSpanId").show();
			$("#success").show();
			$("#nextStepId").show();
			$("#otpId").attr("disabled","disabled");
			$("#submitId").attr("disabled","disabled");
			$("#generateOtpId").attr("disabled","disabled");
			$(".otpCheckboxCls").prop("disabled", true);
			
		}else{
			$("#otpSpanId").show();
			$("#fail").show();
			$("#nextStepId").hide();
			
		}
	});
}	