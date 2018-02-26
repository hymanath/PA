function validatePhoneNumber(phoneNo){
	var status="";
	var filter = /^\d{10}$/;
	if(phoneNo == null || phoneNo.trim().length == 0){
		status = 0;
	}else if (!filter.test(phoneNo)) {
		status = 1;
	}else{
		status = 2;
	}
	return status;
}
function validateHouseNumber(houseNo){
	var status="";
	var filter = /^\d+\w*\s*(?:[\-\/]?\s*)?\d*\s*\d+\/?\s*\d*\s*/;    
	if(houseNo == null || houseNo.trim().length == 0){
		status = 0;
	}
	return status;
}
function validatePinNumber(pinCode){
	var status="";
	var filter = /^\d{6}$/;    
	if(pinCode == null || pinCode.trim().length == 0){
		status = 0;
	}else if (!filter.test(pinCode)) {
		status = 1;
	}else{
		status = 2;
	}
	return status;
}
function validatePanNumber(panNumber){
	var status="";
	var filter =  /^([a-zA-Z]){5}([0-9]){4}([a-zA-Z]){1}?$/;
	if(panNumber == null || panNumber.trim().length == 0){
		status = 0;
	}else if (!filter.test(panNumber)){
		status = 1;
	}else{
		status = 2;
	}
	return status;
}
function validateAadharNumber(aadharCard){
	var status="";
	var filter =  /^\d{12}$/;     
	if(aadharCard == null || aadharCard.trim().length == 0){
		status = 0;
	}else if (!filter.test(aadharCard)) {
		status = 1;
	}else{
		status = 2;
	}
	return status;
}
function validateRegNumber(houseNo){
	var status="";
	var filter = /^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _]*$/;    
	if(houseNo == null || houseNo.trim().length == 0){
		status = 0;
	}else if (!filter.test(houseNo)) {
		status = 1;
	}else{
		status = 2;
	}
	return status;
}
function validateGstnNumber(gstnNumber){  
	var status="";
	var filter = /^[0-9]{2}[A-Za-z]{5}[0-9]{4}[A-Za-z]{1}[1-9A-Z]{1}Z[0-9A-Za-z]{1}$/;        
	if(gstnNumber == null || gstnNumber.trim().length == 0){
		status = 0;
	}else if (!filter.test(gstnNumber)) {
		status = 1;
	}else{
		status = 2;
	}
	return status;
}
function bankAccountNumber(accNumber){    
	var status="";
	var filter = /^\d{9,18}$/;
	if(accNumber == null || accNumber.trim().length == 0){
		status = 0;
	}else if (!filter.test(accNumber)) {
		status = 1;
	}else{
		status = 2;
	}
	return status;   
}
function checkIfscCode(ifscCode){   
	var status="";
	var filter = /[A-Z|a-z]{4}[0][a-zA-Z0-9]{6}$/;
	if(ifscCode == null || ifscCode.trim().length == 0){
		status = 0;
	}else if (!filter.test(ifscCode)) {
		status = 1;
	}else{
		status = 2;
	}
	return status;
}
function emailValidation(email){     
	var status="";
	var filter = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	if(email == null || email.trim().length == 0){
		status = 0;
	}else if (!filter.test(email)) {
		status = 1;
	}else{
		status = 2;
	}
	return status;
}
function callAjax(jsObj,url){
	$.ajax({
		url:url,
        data: JSON.stringify(jsObj),
        type: 'POST',
        dataType :'json',
       	beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},	
		success:function(ajaxresp){
			if(ajaxresp != null && ajaxresp.length > 0){
				$("#districtId  option").remove();
				$("#districtId").append('<option value="0">Select District</option>');
				for(var i in ajaxresp){
					$("#districtId").append('<option value='+ajaxresp[i].id+'>'+ajaxresp[i].name+'</option>');
				}
			}else{
				$("#districtId  option").remove();
				$("#districtId").append('<option value="0">Select District</option>');
			}
			$("#districtId").trigger("chosen:updated");	
		},
		error:function(request,error){
		
		}
	});
}            