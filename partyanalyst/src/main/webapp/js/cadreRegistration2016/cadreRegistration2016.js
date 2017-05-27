function onLoadCalls(){
	 getStatewisesCastNames();
	 getEducationalQualifications();
	 getAllRelationDetails();
	 getOccupationList();
  }

  function getStatewisesCastNames(){
	  var jsObj={
		 stateId:1
	 }
	  $.ajax({          
			type : 'GET',    
			url : 'getStatewisesCastNamesAction.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
			 if(result != null && result.length>0){
               for(var i in result){
                  var casteNamesObj = { id : result[i].id,name: result[i].name }  
                  casteNamesArray.push(casteNamesObj);   
               }
             }	 
		});
  }
  
 
  function getEducationalQualifications(){
	   $.ajax({          
			type : 'GET',    
			url : 'getEducationalQualificationsAction.action',  
			dataType : 'json',
			data : {} 
		}).done(function(result){
			if(result != null && result.length>0){
               for(var i in result){
                  var educationObj = { id : result[i].id,name: result[i].name }  
                  educationsArray.push(educationObj);   
               }
             }	 
		});
  }
  
  function getAllRelationDetails(){
	   $.ajax({          
			type : 'GET',    
			url : 'getAllRelationDetailsAction.action',  
			dataType : 'json',
			data : {} 
		}).done(function(result){
			if(result != null && result.length>0){
               for(var i in result){
                  var relationObj = { id : result[i].id,name: result[i].name }  
                  relationsArray.push(relationObj);   
               }
             } 
		});
  }

function getSearchByMyVoterIdDetails(){
	eachTimeClearFields();
	$("#populatingDtsDivImgId").show();
 var flag = 0;
  $(".searchChkboxCls").each(function(){
	  if($(this).is(":checked")){
		    flag=1;
	  }
  });
      if(flag == 0)
	  {
		  $("#checkVoterId").html("please check the voterDetails");
		  return;
	  }
	myVoterButtonDetails();
	
	 var voterId1=$("#voterId").val();
	 var familyVoterId=0;
	 var tdpCadreId=$("#tdpCadreId").val();
	 var status=$("#statusId").val();
	 var jsObj={
		 voterId:voterId1,
		 familyVoterId:familyVoterId,
		 cadreId:tdpCadreId,
		 status:status
	 }
		$.ajax({          
			type : 'GET',    
			url : 'getRegistrationPersonDetailsAction.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
		
			$("#submitCadreForm").show();
			$(".newProfile").show();
			$("#populatingDtsDivImgId").hide();
			hideShowDivs(status);
			buildProfileDetails(result,status,familyVoterId,"");
		 	buildCasteDetails(result);
		 	buildEductnQualifns(result);
		 	buildCadreFamilyDetails(result);
			buildCadreRelativesDetails(result,"prevNomneReltvId");
			buildOccupationList(result,"occupationId");
		});
		
}
function hideShowDivs(status){
if(status == "new"){
	$("#cadreMembrSpId").hide();
	$("#teluguNameDivId").show();
	$("#familyDetailsDivId").show();
	$("#voterDvId").show();
	$("#emailDivId").show();
	$("#cadreUpdateVotrDivId").hide(); 
	$("#prevNomineeId").hide();
	$("#prevNomiConId").hide();
	$("#prievsNmneDivId").hide();
}else if(status == "update" || status == "renewal"){
    $("#voterDvId").show();
	$("#cadreMembrSpId").show();
	$("#emailDivId").show();
	$("#teluguNameDivId").hide();
	$("#familyDetailsDivId").hide();
	$("#cadreUpdateVotrDivId").show();
	$("#prevNomineeId").show();
	$("#prevNomiConId").show();
	$("#prievsNmneDivId").show();
}
}
function buildProfileDetails(result,status,familyVoterId,type){
	$("#cadreUpdateVotrDivId").hide();
var str = "";
		 if(result.lastName != null){                    
			$("#nameId1").val(result.lastName);
		 }
		 if(result.gender != null){
			 if(result.gender == 'M' || result.gender == 'male' || result.gender == 'Male'){
				 $("#genderId").val('M');
				 $("#genderId").trigger("chosen:updated");
			 }
			else if(result.gender == 'F' || result.gender == 'female' || result.gender == 'FeMale' || result.gender == 'Female'){
				 $("#genderId").val('F');
				 $("#genderId").trigger("chosen:updated");
			 }
		 }
		 if(result.age != null){
			$("#ageId").val(result.age);
		 }
		 if(result.dobStr != null && result.dobStr != ""){
			 var dob = result.dobStr.substring(0, 11);
			 $("#dobId").val(dob);
		 }
		 if(result.constituencyId != null && result.constituencyId != ""){
			$("#hiddenConstId").val(result.constituencyId);
		 }
		 $("#hiddenVoterId").val(result.voterRelationId);
		 $("#hiddenTdpCadreId").val(result.tdpCadreId);
		 if(result.tdpCadreId != null && result.imageBase64String != null){
			$("#existImgId").attr('src','https://mytdp.com/images/cadre_images/'+result.imageBase64String+'');
		}else if(result.voterRelationId != null && result.imageBase64String != null){
			$("#existImgId").attr('src','https://mytdp.com/voter_images/'+result.imageBase64String+'');
		 }else{
			str+='<img src="dist/img/default_image.png" class="cadreImage img-responsive" alt="existing image"/>';
		 }
		 
		 if(result.mobileNumber != null){
			$("#mobileId1").val(result.mobileNumber);
		 }
		 if(result.email != null && result.email != ""){
			$("#emailId").val(result.email);
		 }
		
		 if(status == "new" ){
		 if(familyVoterId != null && familyVoterId != 0){
			 $("#selfVotCls").removeClass("text-success");
		     $("#relVotCls").addClass("text-success");
			 $("#selfVotCls").addClass("text-muted");
			 $("#voterIdText").val(result.voterCardNumber);
		}else {
		     $("#relVotCls").removeClass("text-success");
			 $("#relVotCls").addClass("text-muted");
			 $("#selfVotCls").addClass("text-success");
			 $("#voterIdText").val(result.voterCardNo);
		 }
		 }else{
		  if(result.voterCardNo != null && result.voterCardNo != ""){
		  	 $("#relVotCls").removeClass("text-success");
			 $("#relVotCls").addClass("text-muted");
			 $("#selfVotCls").addClass("text-success");
			 $("#voterIdText").val(result.voterCardNo);
		  }else if(result.voterCardNumber != null && result.voterCardNumber != ""){
			 $("#selfVotCls").removeClass("text-success");
		     $("#relVotCls").addClass("text-success");
			 $("#selfVotCls").addClass("text-muted");
			 $("#voterIdText").val(result.voterCardNumber);
		  }
		 }
		 if(result.candidateAadherNo != null && result.candidateAadherNo != ""){
			$("#aadharId").val(result.candidateAadherNo);
		 }
		 if(result.tdpCadreId != null && result.nomineeName != null && result.nomineeName != ""){
			$("#PrvNomineeDetailsId").prop( 'checked',true);
			$("#prvNomneNameId").val(result.nomineeName);
		 }
		 if(result.tdpCadreId != null && result.nomineeAge != null && result.nomineeAge != "" && result.nomineeAge > 0){
			$("#prevNomneAgeId").val(result.nomineeAge);
		 }
		 if(result.tdpCadreId != null && result.nomineeGender != null && result.nomineeGender != ""){
			 if(result.nomineeGender == 'M' || result.nomineeGender == 'male' || result.nomineeGender == 'Male'){
				 $("#prvNomneGendrId").val('M');
				 $("#prvNomneGendrId").trigger("chosen:updated");
			 }
			else if(result.nomineeGender == 'F' || result.nomineeGender == 'female' || result.nomineeGender == 'FeMale' || result.nomineeGender == 'Female'){
				 $("#prvNomneGendrId").val('F');
				 $("#prvNomneGendrId").trigger("chosen:updated");
			 }
			//$("#prvNomneGendrId").val(result.nomineeGender);
		 }
		 if(result.memberTypeId != null){                    
			$("#membershipId").val(result.memberTypeId);
		 }
		 if(result.nomineeName != null && result.nomineeName != "")
		 {
			 $("#defaultNomineeId").text("Use"+" "+result.nomineeName+" As Nominee");
		 }else{
			$("#defaultNomineeId").text("Use Previous Nominee");
		 }
		 $("#PrvNomineeDetailsId").attr("attr_nomineName",result.nomineeName);
		 $("#PrvNomineeDetailsId").attr("attr_nomineeGender",result.nomineeGender);
		 if(result.tdpCadreId != null && result.nomineeAge != null && result.nomineeAge != "" && result.nomineeAge > 0){
		 $("#PrvNomineeDetailsId").attr("attr_nomineAge",result.nomineeAge);
		 }
		 $("#PrvNomineeDetailsId").attr("attr_nomineRelative",result.nomineeRelationId);
		 
		 if(type == 'updateVoter'){
			$("#updateVoterModelDiv").modal('show');
		 }else{
			$("#updateVoterModelDiv").modal('hide');
		 }
		
}
function buildCasteDetails(result) {
  $("#casteListId").append('<option value="0">Select Caste</option>');
   if (casteNamesArray != null && casteNamesArray.length > 0) {
     for ( var i in casteNamesArray) {
		 if(result.casteId == casteNamesArray[i].id)
		   $("#casteListId").append('<option selected value="'+casteNamesArray[i].id+'">'+casteNamesArray[i].name+'</option>');
		 else
		   $("#casteListId").append('<option value="'+casteNamesArray[i].id+'">'+casteNamesArray[i].name+'</option>');
     }
	 $("#casteListId").trigger("chosen:updated");
  }
  }
  function buildEductnQualifns(result) {
   $("#eductnQualId").append('<option  value="0">Select Education</option>');
   if (educationsArray != null && educationsArray.length > 0) {
     for ( var i in educationsArray) {
	 if(result.educationId == educationsArray[i].id)
       $("#eductnQualId").append('<option selected value="'+educationsArray[i].id+'">'+educationsArray[i].name+'</option>');
	 else
	   $("#eductnQualId").append('<option value="'+educationsArray[i].id+'">'+educationsArray[i].name+'</option>');
     }
	 $("#eductnQualId").trigger("chosen:updated");
  }
  }
  function buildCadreFamilyDetails(result) {
	$(".cadreFamilyDetailsCls").html('');
	var str = '';
	str += '<div class="col-md-12 col-xs-12 col-sm-12">';
    str += '<h4 class="panel-title text-capital">select nominee</h4>';
    str += '</div>';
	str += '<ul class="searchResults">';
	if (result.cadreFamilyDetails != null
			&& result.cadreFamilyDetails.length > 0) {
			$("#familyNomineeChId").show();
			$("#familyNomineeOrId").show();
		for ( var i in result.cadreFamilyDetails) {
			str += '<li>';
			str += '<div class="media">';
			str += '<div class="media-left">';
			str += '<img src="https://mytdp.com/voter_images/'+result.cadreFamilyDetails[i].imagePath+'" class="media-object cadreImage" alt=""/>';
			str += '</div>';
			str += '<div class="media-body">';
			str += '<h5 class="text-capitalize">'+ result.cadreFamilyDetails[i].voterName + '</h5>';
			if(result.cadreFamilyDetails[i].relationshipType == 'Mother'){
				if(result.cadreFamilyDetails[i].gender == 'F')
					str += '<p>D/O: '+result.cadreFamilyDetails[i].relativeName+'</p>';
				else
					str += '<p>S/O: '+result.cadreFamilyDetails[i].relativeName+'</p>';
			}
			else if(result.cadreFamilyDetails[i].relationshipType == 'Husband'){
				str += '<p>W/O: '+result.cadreFamilyDetails[i].relativeName+'</p>';
			}
			else{
				if(result.cadreFamilyDetails[i].gender == 'F')
					str += '<p>D/O: '+result.cadreFamilyDetails[i].relativeName+'</p>';
				else
					str += '<p>S/O: '+result.cadreFamilyDetails[i].relativeName+'</p>';
			}
			//str += '<p>S/O: ' + result.cadreFamilyDetails[i].relativeName+ '</p>';
			str += '<p>V.ID: ' + result.cadreFamilyDetails[i].voterCadreNO+ ' </p>';
			str += '<p>H.NO: ' + result.cadreFamilyDetails[i].houseNo+ '&nbsp;&nbsp;|';
			str += '<span>&nbsp;&nbsp;GENDER : '+ result.cadreFamilyDetails[i].gender+ '&nbsp;&nbsp;|</span>';
			str += '<span>&nbsp;&nbsp;AGE :' + result.cadreFamilyDetails[i].age	+ ' </span>';
			str += '</p>';
			str += '<div class="checkboxAlign">';
			str += '<input id="checkboxfamily'+i+'" class="checkbox-custom checkboxCls" name="checkbox-1" type="checkbox" attr_name="'+result.cadreFamilyDetails[i].voterName+'"  attr_gender="'+result.cadreFamilyDetails[i].gender+'" attr_age="'+result.cadreFamilyDetails[i].age+'" attr_relTypeId="'+result.cadreFamilyDetails[i].relationshipTypeId+'" >';
			str += '<label for="checkboxfamily'+i+'" class="checkbox-custom-label"  style="font-size:13px;font-weight:200;text-transform:uppercase">&nbsp;</label>';
			str += '</div>';
			str += '</div>';
			str += '</div>';
			str += '</li>';	
		}
	}else{
	$("#familyNomineeChId").hide();
	$("#familyNomineeOrId").hide();
	}
	str += '</ul>';
	
	$(".cadreFamilyDetailsCls").html(str);
	//$("#familyDetailsDivId").show();
}
  /*$(document).on("click","#addNewNomineeId",function(){
      if ($("#addNewNominatedId").is(":visible")) {
	  $("#addNewNominatedId").hide();   
	}else{
	 $("#addNewNominatedId").show(); 
	}  
   });*/

function buildCadreRelativesDetails(result,id) {
	$('#'+id+'').append('<option  value="0">Select Relationship</option>');
	 if (relationsArray != null && relationsArray.length > 0) {
	   for ( var i in relationsArray) {
			if(id == 'prevNomneReltvId'){
				if(result.nomineeRelationId == relationsArray[i].id)
				   {
						$('#'+id+'').append('<option selected value="'+relationsArray[i].id+'">'+relationsArray[i].name+'</option>');
				   }else
				   {
				   $('#'+id+'').append('<option value="'+relationsArray[i].id+'">'+relationsArray[i].name+'</option>');
				   }
				}           
			
	   }
	   $('#'+id+'').trigger("chosen:updated");       
	}
}	

$(document).on("click", "#changeNomineeId", function(e) {
	$('.checkboxCls').attr('checked', false);
	$("#prvNomneNameDivId").html("");
	$("#prvNomneGendrDivId").html("");
	$("#prevNomneAgeDivId").html("");
	$("#prevNomneReltvDivId").html("");
	$("#nomineeModalId").modal('show');
    $("#familyDetailsDivId").show(); 
    });  
	
	function savingCadreDetails(){
	
		$("#nomineeDivId").html(""); 
	/*	if(!validations())
		{
		return;
		}
		if(!validationDetails()){
		return;
		}*/
		var tdpCadreId = $("#hiddenTdpCadreId").val();
		if(tdpCadreId != null && tdpCadreId != ""){
		$(".isNomineeChangd").each(function(){
				if($(this).is(":checked")){
					$("#hiddenIsNomneeChngd").val("Y");
				}
			});
		}
	var uploadHandler = {
				upload: function(o) {
					//$("#savingAjaxImg").css("display","none");
					uploadResult = o.responseText;
					
					showSbmitStatus(uploadResult);
				}
			};
	$("#savingCadreDivIdImg").show();
	//csole.log(submitCadreForm)
		YAHOO.util.Connect.setForm('submitCadreForm',true);
		YAHOO.util.Connect.asyncRequest('POST','savingCadreDetailsAction.action',uploadHandler);
	}
	
$(document).on("click",".checkboxCls",function(){
	  $(".checkboxCls").prop( 'checked',false);
	  $(this).prop( 'checked', true );
	  var name = $(this).attr("attr_name");
	  var gender = $(this).attr("attr_gender");
	  var age = $(this).attr("attr_age");
	  var relationTypeId = $(this).attr("attr_relTypeId");
	  $('#prevNomneReltvId').val('').trigger('chosen:updated');
	  $("#prvNomneNameId").val(name);
	  $('#prvNomneGendrId').val(gender).trigger('chosen:updated');
	  $("#prevNomneAgeId").val(age);
	 //$('#prevNomneReltvId').val(relationTypeId).trigger('chosen:updated');
	 $('#prevNomneReltvId').val(0).trigger('chosen:updated');
	
});
$(document).on("click",".nomineeDetailsCls",function(){
	$(".nomineeDetailsCls").prop( 'checked',false);
	 $(this).prop( 'checked', true );
});

function validations()
{
	$("#imgErrDivId").html("");
	var exstImgPath=$("#existImgId").attr("src");
	var newImagPath=$("#actuploadImg").attr("src");
	var existImgCheck=$("#exstCheckImgId").is(':checked') ? 1 : 0;
	var newImgCheck=$("#newCheckImgId").is(':checked') ? 1 : 0;

 
if(existImgCheck=="0" && newImgCheck == "0")
{
  $("#imgErrDivId").html("Please select Image.");
	 return false;	
}else{
	$("#imgErrDivId").html("");
}
if(newImgCheck == "1" || existImgCheck == "1")
  {
	if(newImagPath == "dist/img/default_image.png" && exstImgPath == "dist/img/default_image.png")
	{
		$("#imgErrDivId").html("Please select Image.");
	      return false;
	}else{
		$("#imgErrDivId").html("");
	}
}
return true;
}
function validationDetails()
{
	var name =$("#nameId1").val();  
	var gender =$("#genderId").val();  
	var age =$("#ageId").val();
    var dob =$("#dobId").val();	
    var mobileNo=$("#mobileId1").val();
    var caste=$("#casteListId").val();
    var eductnQual=$("#eductnQualId").val();
	var occupation=$("#occupationId").val();
    var PrvNomneName=$("#prvNomneNameId").val();
	var PrvNomneGendr=$("#prvNomneGendrId").val();
	var PrvNomneAge=$("#prevNomneAgeId").val();
	var PrvNomneReltv=$("#prevNomneReltvId").val();	
	 if(name == 0 && name.trim() == '')
	 {
		$("#cadreNameId").html("Enter Name");  
        return false;		
	 }else
	 {
		 $("#cadreNameId").html("");
	 }
	 if(gender == 0)
	 {
		$("#cadreGenderId").html("Enter Gender");  
        return false;	
	 }else
	 {
		 $("#cadreGenderId").html("");
	 }
	 
	 if(age == 0 && age.trim() == '')
	 {
		$("#cadreAgeId").html("Enter Age");  
        return false;	
	 }else
	 {
		 $("#cadreAgeId").html("");
	 }
	 var numericExpression2 =  /^[0-9]+$/;
	 if (!age.match(numericExpression2)) {
            $("#cadreAgeId").html("Enter valid Age");
            return false;
        }
	 if(dob == 0 && dob.trim() == '')
	 {
		$("#cadredobId").html("Enter dob");  
        return false;	
	 }else
	 {
		 $("#cadredobId").html("");
	 }
	 if(mobileNo.length < 10 )
	 {
		$("#cadreMobileNoId").html("Enter mobileNo");  
       return false;	
	 }else
	 {
		 $("#cadreMobileNoId").html("");
	 }
	 var numericExpression = /^[0-9]+$/;
        if (!mobileNo.match(numericExpression)) {
            $("#cadreMobileNoId").html("Enter valid mobileNo");
            return false;
        }
	 if(caste == 0 )
	 {
		$("#cadreCasteId").html("Select caste");  
        return;		
	 }else
	 {
		 $("#cadreCasteId").html("");
	 }
	 if(eductnQual == 0 )
	 {
		$("#cadreEducationId").html("Select eductnQual");  
        return false;	
	 }else
	 {
		 $("#cadreEducationId").html("");
	 }
	 if(occupation == 0 )
	 {
		$("#cadreOccupationId").html("Select occupation");  
        return false;	
	 }else
	 {
		 $("#cadreOccupationId").html("");
	 }
	 
	 if(PrvNomneName == 0 && PrvNomneName.trim() == '')
	 {
		$("#prvNomneNameDivId").html("Enter Name");  
        return false;
	 }else
	 {
		 $("#prvNomneNameDivId").html("");
	 }
     if(PrvNomneGendr == 0)
	 {
		 $("#prvNomneGendrDivId").html("select Gender"); 
          return false;		 
	 }else
	 {
		 $("#prvNomneGendrDivId").html(""); 
	 }
   	 if(PrvNomneAge == 0 && PrvNomneAge.trim() == '')
	 {
		$("#prevNomneAgeDivId").html("Enter Age"); 
          return false;		
	 }else{
		 $("#prevNomneAgeDivId").html("");
	 }
	 var numericExpression1 = /^[0-9]+$/;
	 if (!PrvNomneAge.match(numericExpression1)) {
            $("#prevNomneAgeDivId").html("Enter valid Age");
            return false;
        }
	 if(PrvNomneReltv == 0)
	 {
		 $("#prevNomneReltvDivId").html("select Relative");
         return false;		 
	 }else{
		  $("#prevNomneReltvDivId").html("");
	 }
 var flag = 0;
  $(".nomineeDetailsCls").each(function(){
	  if($(this).is(":checked")){
		    flag=1;
	  }
  });
      if(flag == 0)
	  {
		  $("#nomineeDivId").html("please check any nominee");
		  return false;
	  }
	  return true;
}
$(document).on("click","#addNewNomineeId",function(){
	$("#prvNomneNameId").val('');
	$('#prvNomneGendrId').val(0).trigger('chosen:updated');
	$("#prevNomneAgeId").val('');
	$('#prevNomneReltvId').val(0).trigger('chosen:updated'); 
});  

$(document).on("click",".isImageCheck",function(){
	var value = $(this).val();
	if(value == "existImage") {
	$("#hiddenNewImgExist").val(value);
	}else if("newImage"){
	$("#hiddenNewImgExist").val(value);
	}
	});    
 
 $(document).on("click","#PrvNomineeDetailsId",function(){ 
	$('#prvNomneGendrId').val(0).trigger('chosen:updated');
	//$('#prevNomneReltvId').val(0).trigger('chosen:updated'); 
	 $('#prvNomneGendrId').val();
	 $('#prevNomneReltvId').val();
	var prvNomineeGender = $("#PrvNomineeDetailsId").attr("attr_nomineeGender");
	var prvNomineeRelative = $("#PrvNomineeDetailsId").attr("attr_nomineRelative");
	 if(prvNomineeGender == 'Female' || prvNomineeGender == 'F' || prvNomineeGender == 'f' || prvNomineeGender == 'female')
	 {
				$("#prvNomneGendrId").val("F").trigger("chosen:updated");
	 }
	 else if(prvNomineeGender == 'Male' || prvNomineeGender == 'M' || prvNomineeGender == 'male' || prvNomineeGender == 'm')
	 {
		  $("#prvNomneGendrId").val("M").trigger("chosen:updated");
	 }
	 else if(prvNomineeGender == 'others' || prvNomineeGender == 'o' || prvNomineeGender == 'OTHERS' || prvNomineeGender ==O)
	 {
		  $("#prvNomneGendrId").val("O").trigger("chosen:updated");
	 }
		 for ( var i in relationsArray) {			
				if(prvNomineeRelative == relationsArray[i].id)
				   {
						$("#prevNomneReltvId").append('<option selected value="'+relationsArray[i].id+'">'+relationsArray[i].name+'</option>');
				   }
				
	   }
	  $("#prevNomneReltvId").trigger("chosen:updated"); 
	 
	$("#prvNomneNameId").val($("#PrvNomineeDetailsId").attr("attr_nomineName"));
	$("#prevNomneAgeId").val($("#PrvNomineeDetailsId").attr("attr_nomineAge"));
   });
 function eachTimeClearFields()
 {
	$("#existImgId").attr('src','dist/img/default_image.png');
	$("#nameId1").val('');  
	$("#actuploadImg").removeAttr('src');
	$("#actuploadImg").attr('src','dist/img/default_image.png');
	$("#newCheckImgId").attr('checked', false);
	$("#exstCheckImgId").attr('checked',false);
	$("#genderId").val(0).trigger('chosen:updated');
	$("#ageId").val(''); 
	$("#dobId").val('');  
	$("#selfVoetrId").val('');            
	$("#updatedVoetrId").val('');       
	$("#voterIdText").val('');
	$("#aadharId").val('');
	$("#casteListId").val(0).trigger('chosen:updated'); 
	$("#eductnQualId").val(0).trigger('chosen:updated');   
	$("#prvNomneNameId").val('');
	$("#prvNomneGendrId").val(0).trigger('chosen:updated');
	$("#prevNomneAgeId").val('');
	$("#prevNomneReltvId").val(0).trigger('chosen:updated');
	$('#changeNomineeId').attr('checked', false);
	$("#prvNomneNameDivId").html("");
	$("#imgErrDivId").html("");
	$("#prvNomneGendrDivId").html("");
	$("#prevNomneAgeDivId").html("");
	$("#prevNomneReltvDivId").html("");
	$("#savingStatusDivId").html("");
	$("#membershipId").html("");
	$("#submitCadreForm").hide();
	$("#mobileId1").val('');
	$("#emailId").val('');
	$("#occupationId").val(0).trigger('chosen:updated');
	$("#prevNomneAadharNoId").val('');
	$(".nomineeDetailsCls").prop( 'checked',false);
}
function getOccupationList(){
	   $.ajax({          
			type : 'GET',    
			url : 'getOccupationListAction.action',  
			dataType : 'json',
			data : {} 
		}).done(function(result){
			if(result != null && result.length>0){
               for(var i in result){
                  var relationObj = { id : result[i].id,name: result[i].name }  
                  occupationArray.push(relationObj);   
               }
             } 
		});
  }
  function buildOccupationList(result,id) {
	$('#'+id+'').append('<option  value="0">Select Occupation</option>');
	 if (occupationArray != null && occupationArray.length > 0) {
	   for ( var i in occupationArray) {
			if(id == 'occupationId'){
				if(result.occupationId == occupationArray[i].id)
				   {
						$('#'+id+'').append('<option selected value="'+occupationArray[i].id+'">'+occupationArray[i].name+'</option>');
				   }else
				   {
				   $('#'+id+'').append('<option value="'+occupationArray[i].id+'">'+occupationArray[i].name+'</option>');
				   }
				}           
			
	   }
	   $('#'+id+'').trigger("chosen:updated");       
	}
}	
 
 function showSbmitStatus(myResult)
 { 
	 $("#savingCadreDivIdImg").hide();
	 var result = (String)(myResult);
    
    var errorDivEle = document.getElementById('imgErrDivId');
    var str = '';
    
    var resultArr = result.split(',');
    if(result.search('SUCCESS') != -1)
    {
		$("#trNumberId").text(resultArr[1]);
		$("#membrShpNoId").text(resultArr[2]);
		$("#trNumberModal").modal('show');
	}
	 else
	 {
		$("#savingStatusDivId").html("<span style='color: red;font-size:22px;'>Application Submission Failed.Please try Again./span>"); 
	 }
		 
 }
 $(document).on("click","#contineueBtn",function(){ 
			 eachTimeClearFields();
			 window.location.reload();
 });
 
 function validateUpdateVoterId(){
 
 $("#updateVoterErr").html("");
 var voterCardNo = $("#voterCardNoId").val();
 if(voterCardNo == ""){
 $("#updateVoterErr").html("Enter Voter Card No");
 return;
 }else{
 $("#updateVoterErr").html("");
 }
  var jsObj={
		 voterCardNo:$("#voterCardNoId").val()
	 }
	  $.ajax({          
			type : 'GET',    
			url : 'validateUpdateVoterAction.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
			 if(result != null){
				if(result.nomineeName == 'Voter Exist'){
				   if(result.nameType == 'Cadre'){
						$("#updateVoterErr").html("With This Voter Cadre Already Exist");
				   }else{
				   $("#updateVoterModelDiv").modal('hide');
				     updatingVoterDetails(result);
				   }
				}else{
					$("#updateVoterErr").html("Voter Not Exist, Contineue with Family VoterId");
				}
               
             }	 
		});
 }
 
 function updatingVoterDetails(result){
		 if(result.lastName != null){                    
			$("#nameId1").val(result.lastName);
		 }
		 if(result.gender != null){
			 if(result.gender == 'M' || result.gender == 'male' || result.gender == 'Male'){
				 $("#genderId").val('M');
				 $("#genderId").trigger("chosen:updated");
			 }
			else if(result.gender == 'F' || result.gender == 'female' || result.gender == 'FeMale' || result.gender == 'Female'){
				 $("#genderId").val('F');
				 $("#genderId").trigger("chosen:updated");
			 }
		 }
		 if(result.age != null){
			$("#ageId").val(result.age);
		 }
		 
			 $("#relVotCls").removeClass("text-success");
			 $("#relVotCls").addClass("text-muted");
			 $("#selfVotCls").addClass("text-success");
			 $("#voterIdText").val(result.voterCardNo);
			 
			 $("#hiddenNewVoterId").val(result.voterRelationId);
			 
 }
 
 