function userLogin()
	{    
	var json = {
		username:$("#unameId").val(),
		passwordHashText:$("#pwdId").val()
		};
	
   $.ajax({
    url: 'userLogin',
    data: JSON.stringify(json),
    type: "POST",
    dataType: 'json', 
    beforeSend: function(xhr) {
    xhr.setRequestHeader("Accept", "application/json");
    xhr.setRequestHeader("Content-Type", "application/json");
    },
     success: function(ajaxresp) {
		  if(ajaxresp.responceCode==0){
			 $("#errorMsgId").html("<span style='color:red'> UserName or Password Invalid</span>");
		  }else{
			  $("#errorMsgId").html("<span style='color:green'>Login Successfull</span>");
		  }
     },
   error: function(request,error) { 
   //alert(request.responseText);
   //alert(error);
    }
      });
	
  }