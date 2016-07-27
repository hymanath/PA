
function getDistrictsForStatesForNotCadre(state){

   var jsObj=
   {				
				stateId:state,
				elmtId:"districtList_d",
                type:"default",
				task:"getDistrictsForState"				
	}
    $.ajax({
          type:'GET',
          url: 'getDistrictsListForStateAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   location.reload(); 
	   }
      
 for(var i in result) {
if(result[i].id == 0){
				  $("#notCadreDistId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			   }else{
				  $("#notCadreDistId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			   }
	 }	 
	 $("#notCadreDistId").trigger('chosen:updated');
  });
}
 

 function getConstituenciesForDistrictsForNotcadre(district){
	 
	 var jsObj=
   {				
				districtId:district,
				elmtId:"districtList_d",
                type:"default",
				task:"getConstituenciesForDistricts"				
	}
    $.ajax({
          type:'GET',
          url: 'getConstituenciesForADistrictAjaxAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   location.reload(); 
	   }
	for(var i in result){   
	 if(result[i].id == 0){
				  $("#notCadreConstId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			   }else{
				  $("#notCadreConstId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			   }
	}
	
	$("#notCadreConstId").trigger('chosen:updated');
	});
 }

function getMandalCorporationsByConstituencyForNotcadre(constituency)
	{	
	var jsObj ={					
					constituencyId:constituency
				};
				 $.ajax({
					type : "GET",
					url : "getMandalDetailsByConstituencyAction.action",//getMandalsForAConstituencyAjaxAction
					data : {task:JSON.stringify(jsObj)} 
				}).done(function(result){
				if(result !=null)
				{
					$("#notCadreMandlId").empty();
					
					for(var i in result)
					{
						if(result[i].id == 0){
							$("#notCadreMandlId").append('<option value='+result[i].id+'>Select Mandal/Muncipality/Corporation</option>');
						}
						else{
							$("#notCadreMandlId").append('<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>');
						}
					}	
				}
				$("#notCadreMandlId").trigger("chosen:updated");
				});
	}

	function getPanchayatWardByMandalForNotcadre(mandalId){
		var constituencyId = $('#notCadreConstId').val();
			var jsObj={
				mandalId:mandalId,
				constituencyId : constituencyId
			}
			$.ajax({
				type : "POST",
				url : "getPanchayatWardByMandalAction.action",
				data : {task:JSON.stringify(jsObj)} 
			}).done(function(result){
				
						$("#notCadrePanchayatId").empty();
					
			for(var i in result){
				if(result[i].id == 0){
							$("#notCadrePanchayatId").append('<option value='+result[i].id+'>Select Panchayat/Ward/Division/City</option>');
						}
						else{
					$("#notCadrePanchayatId").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
						}
			}
			
				$("#notCadrePanchayatId").trigger("chosen:updated");	
			
		});	
			
	}

	function getRelationTypeDetails(){
		$("#relativetypeId").empty();
  var jsObj={
        task:""
      }
      $.ajax({
         type:'GET',
         url:'getAllRelationDetails.action',
         dataType: 'json',
         data: {task:JSON.stringify(jsObj)}
      }).done(function(result){
      if(result !=null){
      $("#relativetypeId").append("<option value='0'>Select RelationType</option>");
        for(var i in result)
            $("#relativetypeId").append("<option value='"+result[i].id+"'>"+result[i].name+"</option>");
        }
		$("#relativetypeId").trigger("chosen:updated");
		});

	}

  
	function saveNotCadreDetails(){
		
		var uploadHandler = {
				upload: function(o) {
					$("#savingAjaxImg").css("display","none");
					uploadResult = o.responseText;
					
					showNotCadreSavingStatus(uploadResult);
				}
			};
			
			YAHOO.util.Connect.setForm('saveNotCadre',true);
			YAHOO.util.Connect.asyncRequest('POST','saveNotCadreDetailsAction.action',uploadHandler);
	
	}
	function showNotCadreSavingStatus(result){
		globalNominatedCandId = "";
		if(result.indexOf("SUCCESS") > -1){
			globalNominatedCandId = result.replace( /[^\d.]/g, '' );
			
			$("#notCadreSavId").html("<span style='color: green;font-size:22px;'>Not Cadre Saved Successfully</span>");
			setTimeout(function(){
			$("#notCadreSavId").html("");
			//$("#addMemberModalBlock").modal('hide');
			}, 5000);
			getNotCadreDetails(globalNominatedCandId);
		}else {
			setTimeout(function(){
			$("#notCadreSavId").html("<span style='color: red;font-size:22px;'>Application Saved Failed. Please Try Again.</span>");
			//$("#addMemberModalBlock").modal('hide');
			}, 1000);
		}
	}

	function getCastesForAP(){
	
		var jsObj={}
		$.ajax({
	          type:'GET',
	          url: 'getCastesForAPAction.action',
	          dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		   if(result != null && result.length > 0){
			   $("#casteId").append('<option value="0">Select Caste</option>');
			   for(var i in result){
				   $("#casteId").append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
			   }
			  
			   $("#casteId").trigger("chosen:updated");	
		   }
	   });
	}
getCastesForAP();

function getNotCadreDetails(globalNominatedCandId){
	
	var jsObj={
		nominatedPostCandiId : globalNominatedCandId
	}
		$.ajax({
	          type:'GET',
	          url: 'getNotCadreDetailsAction.action',
	          dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		   if(result != null)
					{
					buildCadreDetails(result);
					$("#addMemberModalBlock").modal('hide');
					$("#searchDivId").show();
					}
	   });
}
getRelationTypeDetails();

function validateAddNewCandidateFields(){
	var voterId =$(".voterCls").val();
	var name = $("#nameId").val(); 
	var mobileNo = $("#mobilenoId").val();
	var gender = $("#genderId").val();
	var dob=$("#DOBId").val();
	var image = $("#imageurlId").val();
	var casteId = $("#casteId").val();
	var stateId = $(".stateSlctBxCls").val();
	var districtId = $("#notCadreDistId").val();
	var  constituencyId = $("#notCadreConstId").val();
	var  panWardDivisionId = $("#notCadreMandlId").val();
	var  notCadrePanchayatId = $("#notCadrePanchayatId").val();
	 if(voterId == null || voterId.trim().length == 0){
		 $(".addNewCandidateErrorCls").html("Please Enter Voter Id.");
		 return false;
	 }
	 if(name == null || name.trim().length == 0){
		 $(".addNewCandidateErrorCls").html("Please Enter Name.");
		 return false;
	 }
	 if(mobileNo == null || mobileNo.trim().length == 0){
		 $(".addNewCandidateErrorCls").html("Please Enter Mobile No.");
		 return false;
	 }
	  var mobileValidateDigits= /^\d{10}$/;
	 if(!mobileValidateDigits.test(mobileNo)) {
		 $(".addNewCandidateErrorCls").html("Mobile Number Must be 10 digits");  
		  return false; 
	 }
	 if(gender==0){
		 $(".addNewCandidateErrorCls").html("Please Select Gender.");
		 return false; 
	 }
	 
	 if(dob== null || dob.trim().length == 0){
		 $(".addNewCandidateErrorCls").html("Please Date Of Birth.");
		 return false;  
	 }
 	 if(dob !=null && dob !=undefined && dob.trim() !=""){
		 var dobArr=dob.split("/");	
		  var year=(new Date().getFullYear())-(dobArr[2]);
		  $("#ageId").val(year);
	 }	 
	 var age = $("#ageId").val();
	 if(age == null || age.trim().length == 0 || age == 0){
		 $(".addNewCandidateErrorCls").html("Please Enter Age.");
		 return false; 
	 }
	 if(image == null || image.trim().length == 0){
	   $(".addNewCandidateErrorCls").html("Please Upload Image.");
		return false;  
	 }
	 if(casteId==0){
		$(".addNewCandidateErrorCls").html("Please Select Caste.");
		return false;   
	 }
	 if(stateId==0){
		$(".addNewCandidateErrorCls").html("Please Select State.");
		return false;   
	 }
	 if(districtId==0){
		$(".addNewCandidateErrorCls").html("Please Select District.");
		return false;   
	 }
	 if(constituencyId==0){
		$(".addNewCandidateErrorCls").html("Please Select Constituency.");
		return false;   
	 }
	 if(panWardDivisionId==0){
		$(".addNewCandidateErrorCls").html("Please Select Mandal/ Muncipality / Corporation.");
		return false;   
	 }
	 if(notCadrePanchayatId==0){
		$(".addNewCandidateErrorCls").html("Please Select Panchayat/ Ward / Division/City.");
		return false;   
	 }
	 $(".addNewCandidateErrorCls").html(" ");
	  return true;
}
 $(document).on("blur","#DOBId",function(){
	var dob=$("#DOBId").val();
	if(dob!=null && dob!=undefined && dob.trim() !=""){		
	 var dobArr=dob.split("/");	
	 var year=(new Date().getFullYear())-(dobArr[2]);
	 $("#ageId").val(year);
	}
}); 
$(document).on("click","#addCandidateBtnId",function(){
	var flag = validateAddNewCandidateFields();
	if(flag){
		saveNotCadreDetails();
	}
});