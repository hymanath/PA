function getAllCallingPurpose(){
	$("#callPurposeId").val('');
	var jObj={};
	$.ajax({
		type:"GET",
		url:'getAllCallingPurposeAction.action',
		dataType:'json',
		data:{task:JSON.stringify(jObj)}
	}).done(function(result){
		 if(result!=null && result.length>0){
				$("#callPurposeId").append('<option value="0">Select Call Purpose</option>');
				for(var i in result){
					$("#callPurposeId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
				}
			} 
		
});
}
function getCallSuportType(){
	$("#callSupportTypId").val('');
	var jObj={};
	$.ajax({
		type:"GET",
		url:'getCallSuportTypeAction.action',
		dataType:'json',
		data:{task:JSON.stringify(jObj)}
	}).done(function(result){
		 if(result!=null && result.length>0){
				$("#callSupportTypId").append('<option value="0">Select Call Response</option>');
				for(var i in result){
					$("#callSupportTypId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
				}
			} 
		
});
} 
function getCallStatus(){
	$("#callStatusId").val('');
	var jObj={};
	$.ajax({
		type:"GET",
		url:'getCallStatusAction.action',
		dataType:'json',
		data:{task:JSON.stringify(jObj)}
	}).done(function(result){
		 if(result!=null && result.length>0){
				$("#callStatusId").append('<option value="0">Select Call Status</option>');
				for(var i in result){
					$("#callStatusId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
				}
			} 
		
});
}
$(document).on("click",".updateBtnCls",function(){
	$("#upadateCallerModalDivId").modal('show');
	var tdpCadreId = $(this).attr("attr_tdp_cadre_id"); 
	$("#hiddenTdpCadreId").val(tdpCadreId);
	$("#callPurposeId option").remove();
	$("#callStatusId option").remove();
	$("#callSupportTypId option").remove();
	$("#descriptionId").val('');
	$("#errorUpCallId").html("");
	getCallSuportType();
	getCallStatus();
	getAllCallingPurpose();
	
});
function updateCallerFeedBackDetails(){
	$("#errorUpCallId").html("");
	var cadreId = $("#hiddenTdpCadreId").val(); 
	var callPurposeId = $("#callPurposeId").val();
	var callStatusId = $("#callStatusId").val();
	var desription = $("#descriptionId").val();
	var callSupportTypId = $("#callSupportTypId").val();
	$("#errorUpCallId").html('');
	 if(callPurposeId ==0){
		  $("#errorUpCallId").html("Select  Call Purpose");
		  return;
	  }
	  $("#errorUpCallId").html('');
	   if(callStatusId ==0){
		  $("#errorUpCallId").html("Select Call Status");
		  return;
	  }
	  $("#errorUpCallId").html('');
	   if(desription ==0){
		  $("#errorUpCallId").html("Select Description");
		  return;
	  }
	  $("#errorUpCallId").html('');
	   if(callSupportTypId ==0){
		  $("#errorUpCallId").html("Select Call Support Type");
		  return;
	  }
	  
	var jObj={
		cadreId:cadreId,
		callPurposeId:callPurposeId,
		callStatusId:callStatusId,
		callSupportTypId:callSupportTypId,
		desription:desription
	};
	$.ajax({
	  type:'POST',
	  url: 'saveCallerFeedBackDetailsForCadreAction.action',
	  dataType: 'json',
	  data: {task:JSON.stringify(jObj)},
	  }).done(function(result){
			if(result != null){
				if(result.message == "Success"){
						$("#errorUpCallId").html("Caller Feedback Is Successfully Updated...");
					$("#upadateCallerModalDivId").modal('hide');
				setTimeout(function(){
					$("body").addClass('modal-open');
				},1000)
				}else{
					$("#errorUpCallId").html("Caller Feedback Is Not Updated...");
				}
			}
	  });
}