
var attr_nomination_post_candidate_id=0;
var attr_tdp_cadre_id=0;
$(document).on('click','.removeIconCls',function(){
	   attr_nomination_post_candidate_id = $(this).attr('attr_nomination_post_candidate_id');
	   attr_tdp_cadre_id = $(this).attr('attr_tdp_cadre_id');
	   
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
	  var nominationPostCandidateId = 0;
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
	 
	 if(!confirm("Are you sure you want to delete candidate ?")){
		 return;
	 }
	
	  var jsObj = {
		  tdpCadreId : attr_tdp_cadre_id,
		  reasonId : reasonId,
		  remark : remarkTxt,
		  nominationPostCandidateId:attr_nomination_post_candidate_id
	  }
	  
	   $.ajax({
          type:'GET',
          url: 'UpdateExpiredAppicationsForCandidate.action',
		  //url:"",
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
		}).done(function(result){ 
			if(result.resultCode == 0){
				$("#successDivId").html("<span style='color:green;'>Candidate Removed Successfully.</span>");
				setTimeout(function(){
				  $("#removeModalDivId").modal("hide");
				  location.reload();
				}, 1000);
			//	$('#rc'+cadreId).remove();
			//	$("#main"+cadreId).css({"background":"rgba(255, 0, 0, 0.1)","padding":"5px","border-bottom":"1px solid rgb(51, 51, 51)"});
			//	$("#delete"+cadreId).append('<b style="color:red;">Deleted Reason</b> : '+reason);
				
			}
			else{
				$("#errorDivId").html("Candidate Not Removed Successfully.");
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
  