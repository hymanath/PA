function getRegistrationPersonDetails(){

var jsObj={  
			status :"renewal",
			voterId : 10,
			familyVoterId :0 ,
			cadreId : 5238680
		};
		$.ajax({          
			type : 'GET',    
			url : 'getRegistrationPersonDetailsAction.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
			buildProfileDetails(result);
		 	buildCasteDetails(result);
		 	buildEductnQualifns(result);
		 	buildCadreFamilyDetails(result);
			buildCadreRelativesDetails(result);
		});

}
function buildProfileDetails(result){
var str = "";
		 if(result.lastName != null){
			$("#nameId").val(result.lastName);
		 }
		 if(result.gender != null){
			$("#genderId").val(result.gender);
		 }
		 if(result.age != null){
			$("#ageId").val(result.age);
		 }
		 if(result.dobStr != null && result.dobStr != ""){
			 var dob = result.dobStr.substring(0, 11);
			 $("#dobId").val(dob);
		 }
		 if(result.tdpCadreId != null && result.imageBase64String != null){
			$("#existImgId").attr('src','https://mytdp.com/images/cadre_images/'+result.imageBase64String+'');
		}else if(result.voterRelationId != null && result.imageBase64String != null){
			$("#existImgId").attr('src','https://mytdp.com/images/voter_images/'+result.imageBase64String+'');
		 }else{
			str+='<img src="dist/img/default_image.png" class="cadreImage img-responsive" alt="existing image"/>';
		 }
		 
		 if(result.mobileNumber != null){
			$("#mobileId").val(result.mobileNumber);
		 }
		 if(result.email != null && result.email != ""){
			$("#emailId").val(result.email);
		 }
		 if(result.voterCardNo != null && result.voterCardNo != ""){
			 $("#relVotCls").addClass("text-muted");
			 $("#selfVotCls").addClass("text-success");
			 $("#voterId").val(result.voterCardNo);
		 }else if(result.voterCardNumber != null && result.voterCardNumber != ""){
			 $("#relVotCls").addClass("text-success");
			 $("#selfVotCls").addClass("text-muted");
			 $("#voterId").val(result.voterCardNumber);
		 }
		 if(result.candidateAadherNo != null && result.candidateAadherNo != ""){
			$("#aadharId").val(result.candidateAadherNo);
		 }
		 if(result.nomineeName != null && result.nomineeName != ""){
			$("#nomineeNameId").val(result.nomineeName);
		 }
		 if(result.nomineeAge != null && result.nomineeAge != ""){
			$("#nomineeAgeId").val(result.nomineeAge);
		 }
		 if(result.nomineeGender != null && result.nomineeGender != ""){
			$("#nomineeGenderId").val(result.nomineeGender);
		 }
		  
}
function buildCasteDetails(result) {
$("#casteListId").append('<option value="0">Select Caste</option>');
   if (result.casteList != null && result.casteList.length > 0) {
     for ( var i in result.casteList) {
	 if(result.casteId == result.casteList[i].id)
       $("#casteListId").append('<option selected value="'+result.casteList[i].id+'">'+result.casteList[i].name+'</option>');
	 else
	   $("#casteListId").append('<option value="'+result.casteList[i].id+'">'+result.casteList[i].name+'</option>');
     }
	 $("#casteListId").trigger("chosen:updated");
  }
  }
  function buildEductnQualifns(result) {
   $("#eductnQualId").append('<option  value="0">Select Caste</option>');
   if (result.eduQualftnList != null && result.eduQualftnList.length > 0) {
     for ( var i in result.eduQualftnList) {
	 if(result.educationId == result.eduQualftnList[i].id)
       $("#eductnQualId").append('<option selected value="'+result.eduQualftnList[i].id+'">'+result.eduQualftnList[i].name+'</option>');
	 else
	   $("#eductnQualId").append('<option value="'+result.eduQualftnList[i].id+'">'+result.eduQualftnList[i].name+'</option>');
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
		for ( var i in result.cadreFamilyDetails) {
			str += '<li>';
			str += '<div class="media">';
			str += '<div class="media-left">';
			str += '<img src="dist/img/default_image.png" class="media-object cadreImage" alt="candidateImage"/>';
			str += '</div>';
			str += '<div class="media-body">';
			str += '<h5 class="text-capitalize">'
					+ result.cadreFamilyDetails[i].voterName + '</h5>';
			str += '<p>S/o:' + result.cadreFamilyDetails[i].relativeName
					+ '</p>';
			str += '<p>V.ID' + result.cadreFamilyDetails[i].voterCadreNO
					+ ' </p>';
			str += '<p>H.no:' + result.cadreFamilyDetails[i].houseNo
					+ '&nbsp;&nbsp;|';
			str += '<span>&nbsp;&nbsp;Gender : '
					+ result.cadreFamilyDetails[i].gender
					+ '&nbsp;&nbsp;|</span>';
			str += '<span>&nbsp;&nbsp;Age :' + result.cadreFamilyDetails[i].age
					+ ' </span>';
			str += '</p>';
			str += '<div class="checkboxAlign">';
			str += '<input id="checkbox'+i+'" class="checkbox-custom checkboxCls" name="checkbox-1" type="checkbox">';
			str += '<label for="checkbox'+i+'" class="checkbox-custom-label" style="font-size:13px;font-weight:200;text-transform:uppercase">&nbsp;</label>';
			str += '</div>';
			str += '</div>';
			str += '</div>';
			str += '</li>';
		}
	}
	str += '</ul>';
	str += '<p class="m_top30">Note: If no nominee is present in the above list. Please click <a  class="text-capital" id="addNewNomineeId"> add new nominee</a></p>';
	$(".cadreFamilyDetailsCls").html(str);

}
  $(document).on("click","#addNewNomineeId",function(){
      $("#addNewNominatedId").show();  
   });

function buildCadreRelativesDetails(result) {
	$("#relativeId").append('<option  value="0">Select Relationship</option>');
	 if (result.relativesList != null
			&& result.relativesList.length > 0) {
				
	   for ( var i in result.relativesList) {           
		   if(result.relativeType == result.relativesList[i].name)
		   {
			    $("#relativeId").append('<option selected value="'+result.relativesList[i].id+'">'+result.relativesList[i].name+'</option>');
		   }else
		   {
		   $("#relativeId").append('<option value="'+result.relativesList[i].id+'">'+result.relativesList[i].name+'</option>');
		   }
	   }
	   $("#relativeId").trigger("chosen:updated");       
	}
		     
   }
  $(document).on("click",".checkboxCls",function(){
     $(".checkboxCls").prop( "checked" ,false);
	 $( this ).prop( 'checked', true );
     })   