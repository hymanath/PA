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
	$('#checkbox8').attr('checked', false);
	//$('#prmaryAddrsId').attr('checked', true);
	//$('#deliveryCheckBox').attr('checked', false);
	// $('#nameId1').attr('readonly',true);
	//$('#prmaryAddrsId').trigger('click');
	$('.deliveryAddrCls').html('');
	$('.delvryAdrCls').val(0);
	$("delvryAdrCls").trigger("chosen:updated");
	$("#populatingDtsDivImgId").show();
 var flag = 0;
  $(".searchChkboxCls").each(function(){
	  if($(this).is(":checked")){
		    flag=1;
	  }
  });
      if(flag == 0)
	  {
		  $("#checkVoterId").html("Please check atleast one voter.");
		  return;
	  }
	  
	myVoterButtonDetails();
	
	 var voterId1=$("#voterId").val();
	 var familyVoterId=0;
	 var tdpCadreId=$("#tdpCadreId").val();
	 var status=$("#statusId").val();
	var memberShipNo=$("#hiddenMemberShipNumber").val();
	if(memberShipNo != null && memberShipNo != ""){
		$("#membrShipDivId").show();
		$("#updateSerchWVoterIdResultsBackBtn").parent().addClass("show");
		$("#searchResultsBackBtnR").parent().addClass("hide");
		$("#searchResultsBackBtn").parent().addClass("hide");
		
	}else{
		$("#membrShipDivId").hide();
	}
	
		if(tdpCadreId == null || tdpCadreId=='' || typeof tdpCadreId == "undefined" || tdpCadreId.lenght == 0  ) 
			tdpCadreId = 0;
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
			//console.log(result);
			$('#changeNomineeId').prop('checked','checked');
				
				$("#submitCadreForm").show();
				$(".newProfile").show();
				$(".rRenewal").hide();
				$("#populatingDtsDivImgId").hide();
				$("#voterIdText").val(voterId1);
				
				hideShowDivs(status);
				buildProfileDetails(result,status,'');
				buildCasteDetails(result);
				buildEductnQualifns(result);
				buildCadreFamilyDetails(result);
				buildCadreRelativesDetails(result,"prevNomneReltvId");
				buildOccupationList(result,"occupationId");
				 buildRelatMemberShipID(memberShipNo);
			
		});
		
}
function hideShowDivs(status){
if(status == "new"){
	$("#cadreMembrSpId").hide();
	//$("#teluguNameDivId").show();
	$("#familyDetailsDivId").show();
	$("#voterDvId").show();
	$("#emailDivId").show();
	$("#cadreVoterDivId").hide();
	$("#cadreUpdateVotrDivId").hide(); 
	$("#prevNomineeId").hide();
	$("#prevNomiConId").hide();
	$("#prievsNmneDivId").hide();
}else if(status == "update" || status == "renewal"){
	$("#cadreMembrSpId").show();
	$("#emailDivId").show();
	//$("#teluguNameDivId").hide();
	$("#familyDetailsDivId").hide();
	$("#cadreUpdateVotrDivId").show();
	$("#prevNomineeId").show();
	$("#prevNomiConId").show();
	$("#prievsNmneDivId").show();
}
}
function buildProfileDetails(result,status,familyVoterId){

	$("#cadreUpdateVotrDivId").hide();
	$(".populatingDtsDivImgId").addClass('hide');
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
		 $("#hiddenFamilyVoterId").val(familyVoterId);
		 $("#
		 ").val(result.voterRelationId);
		 $("#hiddenTdpCadreId").val(result.tdpCadreId);
		 $("#tdpCadreId").val(result.tdpCadreId);
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
		
		 $(".subBlockR").addClass('hide');
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
			 $(".profileDetailsBlock").removeClass('hide');			
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
		 /*if(result.houseNo != null && result.houseNo != ""){
			$("#phnoId").val(result.houseNo);
		 }*/
		 	$("#PrvNomineeDetailsId").prop( 'checked',false);
			$("#addNewNomineeId").prop( 'checked',false);
			$("#changeNomineeId").prop( 'checked',false);
				
		 if(result.tdpCadreId != null && result.nomineeName != null && result.nomineeName != ""){
			$("#PrvNomineeDetailsId").prop( 'checked',true);
			$("#changeNomineeId").prop( 'checked',false);
			$("#newNomineeID").hide();
			$("#existingNomineeID").show();
			$("#familyDetailsDivId").hide();
			$("#prvNomneNameId").val(result.nomineeName);
		 }else{
				$("#PrvNomineeDetailsId").prop( 'checked',false);
				$("#addNewNomineeId").prop( 'checked',false);
				
				$("#addNewNomineeId").prop( 'checked',true);
				
				//$("#prvNomneNameId").prop( 'disabled',true);

				$("#newNomineeID").show();
				$("#existingNomineeID").hide();
				$("#familyDetailsDivId").hide();
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
		 
		/*
		if(result.paymentGatewayVO != null){
			if(result.paymentGatewayVO.subList != null && result.paymentGatewayVO.subList.length>0){
				//console.log(result.paymentGatewayVO);
				//$('#wardsDivId').removeClass('hide');
				$("#wardsList").append('<option value="0"> Select Ward </option>');
				for(var k in result.paymentGatewayVO.subList){
					$("#wardsList").append('<option value="'+result.paymentGatewayVO.subList[k].id+'">'+result.paymentGatewayVO.subList[k].name+'</option>');
				 }
				 $("#wardsList").trigger("chosen:updated");
			}else{
				;//$('#wardsDivId').addClass('hide');
			}
		}
		else{
			;//$('#wardsDivId').addClass('hide');
		}
		*/
		
		  presntDistrictId =result.districtId;
		  presntConstituencyId =result.constituencyId;
		  if(result.localElectionBodyId != null && result.localElectionBodyId>0){
			presntLebId =result.localElectionBodyId; 
			
		  }else
			presntMandalId =result.mandalId; 
		  presntVillageId =result.villageId;
		  
		if(result.districtId<11){
			//$("#PrsntStateList").val(36);
			//$("#PrsntStateList").trigger("chosen:updated");
			getDistrictsForStates(36,2);
		}else{
			//$("#PrsntStateList").val(1);
			//$("#PrsntStateList").trigger("chosen:updated");
			getDistrictsForStates(1,2);
		}

		if(registrationVoterType=='familyVoterId'){
			$("#voterDvId").show();
			//$("#familyDetailsDivId").show();
			$('#nameId1').removeAttr("disabled");
			$("#cadreMembrSpId").hide();
			//$('#exstCheckImgId').removeAttr("checked");
			//$('#newCheckImgId').attr("checked","");
			
			$('#exstCheckImgId').prop("checked",false);			
			$('#newCheckImgId').prop("checked",true);
			
			
			//console.log(" family voter id");
		}else{
			//console.log("not family voter id");
			$('#nameId1').attr("disabled",true);		
			$('#newCheckImgId').prop("checked",false);			
			$('#exstCheckImgId').prop("checked",true);
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
  $(document).on("click","#addNewNomineeId",function(){
	  $("#familyDetailsDivId").hide(); 
	$('#prvsNomneHeadingId').hide();
	$("#prvNomneNameId").val('');
	$('#prvNomneGendrId').val(0).trigger('chosen:updated');
	$("#prevNomneAgeId").val('');
	$('#prevNomneReltvId').val(0).trigger('chosen:updated'); 
	
	// $("#prvNomneNameId").prop( 'disabled',false);
		
      if ($("#addNewNominatedId").is(":visible")) {
			$("#addNewNominatedId").hide();   
		}else{
			$("#addNewNominatedId").show(); 
		}  
   });

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
	$('#prvsNomneHeadingId').hide();
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
		if(!imageValidations())
		{
			//alert("fail 1");
			$("#imagDivId").scroll();
			return;
		}	
		if(addressFieldsValidation())
		{
			//alert("fail 2");
			$("#cadrePrvNomneDivId").scroll();
			return;
		}
		
		//alert("333");
		var tdpCadreId = $("#hiddenTdpCadreId").val();
		if(tdpCadreId != null && tdpCadreId != ""){
		$(".isNomineeChangd").each(function(){
				if($(this).is(":checked")){
					$("#hiddenIsNomneeChngd").val("Y");
				}
			});
		}
		
		//alert("success");
		//return;
		
		$('#saveBtnId').hide();
		
		
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
	
	
 function showSbmitStatus(myResult)
 {
		
		var result = (String)(myResult);
		
		var errorDivEle = document.getElementById('imgErrDivId');
		var str = '';
		
		var resultArr = result.split(',');
		//console.log(resultArr);
		if(result.search('SUCCESS') != -1)
		{				
			$('.subBlock').html('');
			
				str+= '<div class="container m_top10" id="yourElement" style="text-align: center;">';
				str+= '<div class="span12  show-grid" style="position: relative;">';
				str+= '<p class="text-align">Thank You For Your Registration</p>';
				str+= '<h3 class="text-align"> Successfully Registration Completed </h3>';
				str+= '</div>';
				str+= '<div class="span12  show-grid" style="position: relative;">';
				str+= '<p class="text-align">Your Membership No : '+resultArr[2]+' </p>';
				str+= '<p class="text-align">Your Enrollment No : '+resultArr[1]+' </p>';			
				str+= '</div>';
				str+= '</div>';
				str+= '<div class="container m_top10" id="yourElement">';
				str+= '<div class="span12  show-grid" style="position: relative;text-align: center;">';
				str+= '<a href="javascript:{startSearchingPage();}" class="btn btn-success  offset5 border-radius-0"  >Continue  <span class="glyphicon glyphicon-chevron-right"></span></a>';
				str+= '</div>';
				str+= '</div>';
			
			$('#savingStatusDivId').html(str);
		}
		else if(result.search('FAILURE') != -1)
		{
			$('#saveBtnId').show();
			str+= '<div class="container m_top10" id="yourElement">';
			str+= '<div class="span12  show-grid" style="position: relative;">';
			str+= '<h3 class="text-align">Error occured while cadre registration.</h3>';
			str+= '</div>';
			str+= '</div>';
			str+= '<div class="container m_top10" id="yourElement">';
			str+= '<div class="span12  show-grid" style="position: relative;">';
			str+= '<a href="affiliatedCadreSearchAction.action" class="btn btn-success  offset5 border-radius-0"  >Continue  <span class="glyphicon glyphicon-chevron-right"></span></a>';
			str+= '</div>';
			str+= '</div>';
			
			$('#savingStatusDivId').html(str);
		}
		else
		 {
			/*$("#savingStatusDivId").html("<span style='color: red;font-size:22px;'>"+resultArr[0]+" ,Application Submission Failed.Please try Again./span>"); */
			alert("Application Submission Failed.Please try Again.");
			$('#saveBtnId').show();
		 }
		/*else
		{
			str+= '<div class="container m_top10" id="yourElement">';
			str+= '<div class="span12  show-grid" style="position: relative;">';
			str+= '<p class="text-align">Thank You For Your Registration</p>';
			str+= '<h3 class="text-align">Data Not Found For your Voter Id</h3>';
			str+= '</div>';
			str+= '</div>';
			str+= '<div class="container m_top10" id="yourElement">';
			str+= '<div class="span12  show-grid" style="position: relative;">';
			str+= '<a href="affiliatedCadreSearchAction.action" class="btn btn-success  offset5 border-radius-0"  >Continue  <span class="glyphicon glyphicon-chevron-right"></span></a>';
			str+= '</div>';
			str+= '</div>';
			
		}*/
		
	 /*
	 if(result.indexOf("SUCCESS") > -1){
		 $("#savingStatusDivId").html("<span style='color: green;font-size:22px;'>Application Saved Successfully...</span>");
		 setTimeout(function(){
			 eachTimeClearFields();
			}, 2000);
	 }
		 */
 }
 
 function startSearchingPage(){
	 window.location.href="cadreWebRegistrationAction.action";
 }
$(document).on("click",".checkboxCls",function(){
	//$("#prvNomneNameId").prop( 'disabled',true);
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
	$('#prvsNomneHeadingId').hide();
	$(".nomineeDetailsCls").prop( 'checked',false);
	 $(this).prop( 'checked', true );
});

function imageValidations()
{
	$("#imgErrDivId").html("");
	var exstImgPath=$("#existImgId").attr("src");
	var newImagPath=$("#actuploadImg").attr("src");
	var existImgCheck=$("#exstCheckImgId").is(':checked') ? 1 : 0;
	var newImgCheck=0;
	$("#newCheckImgId").is(':checked') ? 1 : 0;
	if(existImgCheck == 0){
		newImgCheck=$("#newCheckImgId").is(':checked') ? 1 : 0;
	}
 
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

/*
$(document).on("click","#addNewNomineeId",function(){
	$("#prvNomneNameId").val('');
	$('#prvNomneGendrId').val(0).trigger('chosen:updated');
	$("#prevNomneAgeId").val('');
	$('#prevNomneReltvId').val(0).trigger('chosen:updated'); 
});  
*/
$("#hiddenNewImgExist").val("existImage");
$(document).on("click",".isImageCheck",function(){
	var value = $(this).val();
	if(value == "existImage") {
	$("#hiddenNewImgExist").val(value);
	}else if("newImage"){
	$("#hiddenNewImgExist").val(value);
	}
	});    
 
 $(document).on("click","#PrvNomineeDetailsId",function(){ 
	$('#prvsNomneHeadingId').show();
	$('#prvNomneGendrId').val(0).trigger('chosen:updated');
	$('#prevNomneReltvId').val(0).trigger('chosen:updated'); 
	
	var prvNomineeGender = $("#PrvNomineeDetailsId").attr("attr_nomineeGender");
	var prvNomineeRelative = $("#PrvNomineeDetailsId").attr("attr_nomineRelative");
	 if(prvNomineeGender == 'Female')
	 {
		       $("#prvNomneGendrId").val('F');
			   $("#prvNomneGendrId").trigger("chosen:updated");
	 }
	 if(prvNomineeGender == 'Male')
	 {
		  $("#prvNomneGendrId").val('M');
		  $("#prvNomneGendrId").trigger("chosen:updated");
	 }
		 for ( var i in relationsArray) {			
				if(prvNomineeRelative == relationsArray[i].name)
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
	 /*$("#checkVoterId,#imgErrDivId,#cadreMobileNoId,#emailErrId,#cadreCasteId,#cadreEducationId,#cadreOccupationId,#prvNomneNameDivId,#prvNomneGendrDivId,#prvNomneGendrDivId,#prevNomneAgeDivId,#prevNomneReltvDivId,#nomineeDivId,#stateErrPhId,#stateErrPaId,#stateErrPa1Id,#stateErrPsId,#stateErrPlId,#stateErrPh1Id,#stateErrId,#distriErrId,#constErrId,#mandalErrId,#wardErrId,#stateErrDhId,#stateErrDaId,#stateErrDa1Id,#stateErrDsId,#stateErrDlId,#stateErrDh1Id,#wrkSateErrId,#wrkDistErrId,#wrkDistErrId,#wrkConstitErrId,#wrkConstitErrId,#wrkMadalErrId,#wrkVillageErrId").html(''); */
	// $('#deliveryAddrId').hide();
	 $('#nameId1').attr('readonly');
	$("#existImgId").attr('src','dist/img/default_image.png');
	$("#nameId1").val('');  
	$("#actuploadImg").removeAttr('src');
	$("#actuploadImg").attr('src','dist/img/default_image.png');
	$("#newCheckImgId").attr('checked', false);
	//$("#exstCheckImgId").attr('checked',false);
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

		if(id == 'occupationId'){
		   for ( var i in occupationArray) {				
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
 
 $(document).on("click","#contineueBtn",function(){ 
			 eachTimeClearFields();
			 window.location.reload();
 });

  //$(document).on("click","#paymentStatusCls",function(){ 
  function updateTransactionTrackingDtals(orderId){
$("#affiliatedCadreForm").submit();	
	 var jsObj={
		orderId:orderId
	 }
	 
	  $.ajax({          
			type : 'GET',    
			url : 'updateTransactionTrackingDtalsAction.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
			 console.log(result);	
		});
  }
 //});
 
 function buildRelatMemberShipID(membershipId){
	 if(membershipId != null && membershipId != ""){
		 $("#membrShipId1").val(membershipId);
	 }
}