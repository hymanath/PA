
$(document).on("click","#submitQuerreisId",function(){
	$("#modalSubmitQuriesId").modal("show");
});


function validateFieldUnionRegistrion(){
	var flag=true;
	var errorStr='';
	$(".queryNameErrCls").html('');
	$(".queryMobileNOErrCls").html('');
	$(".queryEmailErrCls").html('');
	$(".querySuggErrCls").html('');
	var name=$("#modalNameId").val();
	var number=$("#modalMobileNoId").val();
	var emalValue=$("#emailId").val();
	var emailReg=/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	var digReg=/^((0091)|(\91)|0?)[789]{1}\d{9}$/;
	var textArea=$("#qurySuggstinsId").val();
	
	if(name=="" || name==null || name==undefined || name.trim().length==0 || (!isNaN(name))){
		errorStr ='Please Enter Name';
     $(".queryNameErrCls").html(errorStr);
	flag=false;
	}else if(!digReg.test(number)){
		errorStr ='Please Enter 10 Digits Mobile Number'
      $(".queryMobileNOErrCls").html(errorStr);
	 flag=false;
	}else if(!emailReg.test(emalValue)){
		errorStr ='Please Email Format Must be ram@gmail.com';
      $(".queryEmailErrCls").html(errorStr);
	 flag=false;
	}else if(textArea.trim().length<10){
	    errorStr ='Please Enter Minimum 10 characters';
       $(".querySuggErrCls").html(errorStr);
	   flag=false;
	}

	if(!flag){
		return;	
	}
	else{
	var jsObj =
		{   
			name : name,
			number : number,
			emalValue : emalValue,
			textArea : textArea
		}
	$.ajax({    
		type : "POST",
		url : "saveRegistrationFeedbackQueriesDetailsAction.action",
		data : {task:JSON.stringify(jsObj)} ,   
	}).done(function(result){
		if(result != null){
			if(result.resultCode == 0){
				$(".querySuggErrCls").html(" <span style='color:green;'> <br> Your Feedback Submitted Successfully...</span>");
			}
		}
	});
}
}
