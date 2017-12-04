function userLogin()
	{    
	$("#statusMessage").html("");;
	var json = {
		username:$("#loginNameId").val(),
		passwordHashText:$("#passwordId").val()
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
		  if(ajaxresp.responceCode == 0){
			 $("#statusMessage").html("<span style='color:red'> UserName or Password Invalid</span>");
		  }else{
			  $("#statusMessage").html("<span style='color:green'>Login Successfull</span>");
			  var redirectWindow=window.open(ajaxresp.url,'_self');
		  }
     },
   error: function(request,error) { 
   
    }
      });
	
  }