$(document).on('click','.removeIconCls',function(){
	
	   $("#removeModalDivId").modal("show");
	      getAllCadreDeleteReasons();
   })
   
    $(document).on("click","#saveRemovingCadreDetailsId",function(){
	  saveRemovingCadreDetails();
  });
  
  function saveRemovingCadreDetails(){
	  
	  var errorExist = false;
	  
	  var cadreId = $("#hiddenCadreId").val();
	  var reasonId = $("#reasonSelectId option:selected").val();
	  var reason = $("#reasonSelectId option:selected").text();
	  var remarkTxt = $("#remarkTextAreaId").val();
	  
	  if(reasonId != null && (reasonId == 0 || reasonId == undefined)){
		  $("#errorDivId").html("Please Select Reason");
		  errorExist=true;
	  }
	 else if(remarkTxt !=null && remarkTxt.trim().length ==0){
		  $("#errorDivId").html("Please Enter Remark");
		errorExist=true;
	 }
	 if(errorExist){
		 return;
	 }
	 
	 if(!confirmDelete("Are you sure you want to delete cadre ?")){
		 return;
	 }
	
	  var jsObj = {
		  cadreId : cadreId,
		  reasonId : reasonId,
		  remarkTxt : remarkTxt
	  }
	   $.ajax({
          type:'GET',
          url: 'saveRemovingCadreDetailsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
		}).done(function(result){ 
			if(result.resultCode == 0){
				$("#successDivId").html("<span style='color:green;'>Cadre Removed Successfully.</span>");
				setTimeout(function(){
				  $("#removeModalDivId").modal("hide");
				}, 1000);
				$('#rc'+cadreId).remove();
				
				$("#main"+cadreId).css({"background":"rgba(255, 0, 0, 0.1)","padding":"5px","border-bottom":"1px solid rgb(51, 51, 51)"});
				
				$("#delete"+cadreId).append('<b style="color:red;">Deleted Reason</b> : '+reason);
				
			}
			else{
				$("#errorDivId").html("Cadre Not Removed Successfully.");
				setTimeout(function(){
				  $("#removeModalDivId").modal("hide");
				}, 1000);
			}
		});
	  
	  
  }
  function getAllCadreDeleteReasons(){
	  $("#reasonSelectId option").remove();
	  
	  $("#reasonSelectId").append('<option value="0">Select Reason</option>');
	  
	  $.ajax({
          type:'GET',
          url: 'getAllCadreDeleteReasonsAction.action',
          dataType: 'json',
		  data: {}
	   }).done(function(result){
		   
		   if(result !=null && result.length>0){
			   for(var i in result){
				   $("#reasonSelectId").append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
			   }
		   }
		  
	   });
  }
  