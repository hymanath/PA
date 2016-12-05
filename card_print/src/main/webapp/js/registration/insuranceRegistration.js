 var familyDetails='';
$(document).on("click","#addFamDtlsDiv",function(){
	familyDetails=familyDetails+1;
	var c = $("#cloneDiv").clone(true);
	c.removeAttr("style");
	
	//ids
	c.attr("id","familyDetails"+familyDetails);
	c.find(".removeBtn").attr("attr_remove_id","familyDetails"+familyDetails);
	c.find(".familyOccupation").attr("id","familyOccupation"+familyDetails);
	c.find(".familyRelation").attr("id","familyRelation"+familyDetails);
	c.find(".familyCategory").attr("id","familyCategory"+familyDetails);
	c.find(".familyEducation").attr("id","familyEducation"+familyDetails);
	c.find(".familyBank").attr("id","familyBank"+familyDetails);
	//c.find(".familyScheme").attr("id","familyScheme"+familyDetails);
	c.find(".familySchool").attr("id","familySchool"+familyDetails);
	c.find(".dynamicDob").attr("id","dynamicDob"+familyDetails);
	c.find(".familyAge").attr("id","familyAge"+familyDetails);
	c.find(".familyGender").attr("id","familyGender"+familyDetails);
	c.find(".familyNominee").attr("id","familyNominee"+familyDetails);
	c.find(".nomineeShareCheckBoxId").attr("attr_box_id","familyNominee"+familyDetails);
	c.find(".familyAadharNo").attr("id","familyAadharNo"+familyDetails);
	//names
	c.find(".familyName").attr("name","registrationVO.subList["+familyDetails+"].fullName");//san
	c.find(".dynamicDob").attr("name","registrationVO.subList["+familyDetails+"].dateOfBirth");
	c.find(".familyAge").attr("name","registrationVO.subList["+familyDetails+"].age");
	c.find(".familyGender").attr("name","registrationVO.subList["+familyDetails+"].gender");
	c.find(".familyMobile").attr("name","registrationVO.subList["+familyDetails+"].mobileNo");
	c.find(".familyAadharNo").attr("name","registrationVO.subList["+familyDetails+"].adhaarNo");
	c.find(".familyIncome").attr("name","registrationVO.subList["+familyDetails+"].annualIncome");
	c.find(".familyNominee").attr("name","registrationVO.subList["+familyDetails+"].nomineeShare");
	c.find(".familyRelation").attr("name","registrationVO.subList["+familyDetails+"].relationId");
	c.find(".familyOccupation").attr("name","registrationVO.subList["+familyDetails+"].occupationId");
	c.find(".familyCategory").attr("name","registrationVO.subList["+familyDetails+"].categoryOfWorkerId");
	c.find(".familyEducation").attr("name","registrationVO.subList["+familyDetails+"].educationId");
	c.find(".familyTraining").attr("name","registrationVO.subList["+familyDetails+"].trainingRequiredOn");
	c.find(".familyMigrate1").attr("name","registrationVO.subList["+familyDetails+"].isMigDistrict");
	c.find(".familyMigrate2").attr("name","registrationVO.subList["+familyDetails+"].isMigState");
	c.find(".familyBank").attr("name","registrationVO.subList["+familyDetails+"].bankId");
	c.find(".familyBankName").attr("name","registrationVO.subList["+familyDetails+"].bankName");
	c.find(".familyBankBranch").attr("name","registrationVO.subList["+familyDetails+"].branchName");
	c.find(".familyAccount").attr("name","registrationVO.subList["+familyDetails+"].bankAccountNo");
	c.find(".familyIFSC").attr("name","registrationVO.subList["+familyDetails+"].ifscCode");
	//c.find(".familyScheme").attr("name","registrationVO.subList["+familyDetails+"].scheemId");
	//c.find(".familyOtherScheme").attr("name","registrationVO.subList["+familyDetails+"].otherScheem");
	//c.find(".familyRegistrationNo").attr("name","registrationVO.subList["+familyDetails+"].enrollMentNo");
	c.find(".familySchool").attr("name","registrationVO.subList["+familyDetails+"].instituteName");
	c.find(".familyRoolNo").attr("name","registrationVO.subList["+familyDetails+"].rollNo");
	c.find(".familyInstArea").attr("name","registrationVO.subList["+familyDetails+"].instituteArea");
	c.find(".familyInstLocation").attr("name","registrationVO.subList["+familyDetails+"].instituteLocation");
	c.find(".familyClassStudying").attr("name","registrationVO.subList["+familyDetails+"].classOfStudying");
	c.find(".familyName").attr("id","familyName"+familyDetails);
	//error ids
	c.find(".errfamilyName").attr("id","errfamilyName"+familyDetails);
	c.find(".errdynamicDob").attr("id","errdynamicDob"+familyDetails);
	c.find(".errfamilyAadharNo").attr("id","errfamilyAadharNo"+familyDetails);
	c.find(".errfamilyAge").attr("id","errfamilyAge"+familyDetails);
	c.find(".errfamilyGender").attr("id","errfamilyGender"+familyDetails);
	c.find(".errfamilyNominee").attr("id","errfamilyNominee"+familyDetails);
	c.find(".errfamilyRelation").attr("id","errfamilyRelation"+familyDetails);
	c.find(".errfamilyEducation").attr("id","errfamilyEducation"+familyDetails);
	
	
	$("#extraDetails").append(c);
	
	$("#dynamicDob"+familyDetails+"").datetimepicker({
		maxDate: moment(),
		format: "YYYY-MM-DD"
	}); 
	
	$( "#dynamicDob"+familyDetails+"" ).blur(function() {
		familyAgeCount();
	});

});

$(document).on("click",".removeBtn",function(){
	
	var divId = $(this).attr("attr_remove_id");
	$("#"+divId+"").remove();
});

var govtScheme='';
$(document).on("click","#addGoctSchemesDiv",function(){
	govtScheme=govtScheme+1;
	var c = $("#govtSchemesCloneDiv").clone(true);
	c.removeAttr("style");
	
	c.attr("id","govtScheme"+govtScheme);
	c.find(".removeBtnGS").attr("attr_remove_id","govtScheme"+govtScheme);
	c.find(".familyScheme").attr("id","familyScheme"+govtScheme);
	
	c.find(".familyScheme").attr("id","familyScheme"+govtScheme);
	c.find(".familyOtherScheme").attr("id","familyOtherScheme"+govtScheme);
	c.find(".familyRegistrationNo").attr("id","familyRegistrationNo"+govtScheme);
	
	c.find(".familyScheme").attr("name","registrationVO.govtSchemeList["+govtScheme+"].scheemId");
	c.find(".familyOtherScheme").attr("name","registrationVO.govtSchemeList["+govtScheme+"].otherScheem");
	c.find(".familyRegistrationNo").attr("name","registrationVO.govtSchemeList["+govtScheme+"].enrollMentNo");
	
	$("#extraGovtSchemes").append(c);
	
});

$(document).on("click",".removeBtnGS",function(){
	
	var divId = $(this).attr("attr_remove_id");
	$("#"+divId+"").remove();
});

function getTehsilAndLocalElectionBodyForADistrict(districtId)
{
	//fields empty
	
	$("#errWorkerDistrictId").html('');
	$("#errWorkerMandalTownId").html('');
	$("#errWorkerPanchayatIds").html('');
	$("#errWorkerHamletId").html('');
	
	$("#hamletMainDivId").hide();
	$("#errStreetTownId").hide();
	$("#panchayatDivId").hide();
	
	$("#workerMandalOrTownId  option").remove();
	$("#workerMandalOrTownId").append('<option value="0">Select Mandal/Town</option>');
	
	//Doing Migration District Empty when we change the other district
	if($('.migChkBoxWrkr').is(":checked")){
		$(".migChkBoxWrkr").trigger("click");
	}
	
	var jsObj={
		districtId:districtId
	}
	$.ajax({
		  type:'GET',
		  url: 'getTehsilAndLocalElectionBodyForADistrictAction.action',
		  dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null){
			for(var i in result){
				$("#workerMandalOrTownId").append("<option value='"+result[i].id+"'>"+result[i].name+"</option>");
			}
		}
	});
}

function getPanchayatOrWard(mandalOrTown)
{
	//error fields empty 
	$("#errWorkerDistrictId").html('');
	$("#errWorkerMandalTownId").html('');
	$("#errWorkerPanchayatIds").html('');
	$("#errWorkerHamletId").html('');
	
	$("#workerPanchayatId  option").remove();
	$("#workerPanchayatId").append('<option value="0">Select Village</option>');
	$("#workerWardId  option").remove();
	$("#workerWardId").append('<option value="0">Select Ward</option>');
	
	var str = mandalOrTown;

	if(str>0){
		var firstPosition = str.charAt(0);
	
		if(firstPosition == 1){
			$("#panchayatDivId").show();
			$("#wardDivId").hide();
			getPanchayatOrWardByMandalOrTown(mandalOrTown,"Mandal");
		}
		else if(firstPosition == 2){
			$("#panchayatDivId").hide();
			$("#wardDivId").show();
			getPanchayatOrWardByMandalOrTown(mandalOrTown,"Town");
		}
	}
	else{
			$("#panchayatDivId").hide();
			$("#wardDivId").hide();
			$("#hamletMainDivId").hide();
	}
	
	
}

$( "#workerDobId" ).blur(function() {
	workerAgeCount();
});

$("#workerAgeId").blur(function(){
	
	var date = new Date();
	var givenAge = $("#workerAgeId").val();
	var y = date.getFullYear()-givenAge;
	var m = date.getMonth()+1;
	var d = date.getDate();
	
	if(m<9){
		m="0"+m;
	}
	if(d<9){
		d="0"+d;
	}
	//If same year before then dont change date.  
	var dob = $("#workerDobId").val().trim();
	if(dob.length>0){
		var dobYear=dob.split("-")[0];
		if(dobYear!=y){
			$( "#workerDobId" ).val(""+y+"-"+m+"-"+d);
		}
	}else{
		$( "#workerDobId" ).val(""+y+"-"+m+"-"+d);
	}
	
});

function workerAgeCount()
{
	var date1 = new Date();
	var dob = $("#workerDobId").val();
	var date2 = new Date(dob);
	var y1 = date1.getFullYear();
	var y2 = date2.getFullYear();
	var age = y1-y2;
	if(age != null && age >0)
		$("#workerAgeId").val(age);
	
	return true;
}

$( "#dynamicDob0" ).blur(function() {
	//familyMemberAgeCount();
});

function familyMemberAgeCount()
{
	var date1 = new Date();
	var dob = $("#dynamicDob0").val();
	var date2 = new Date(dob);
	var y1 = date1.getFullYear();
	var y2 = date2.getFullYear();
	var age = y1-y2;
	$("#familyAge0").val(age);
	return true;
}

function familyAgeCount()
{
	var date1 = new Date();
	var dob = $("#dynamicDob"+familyDetails+"").val();
	var date2 = new Date(dob);
	var y1 = date1.getFullYear();
	var y2 = date2.getFullYear();
	var age = y1-y2;
	$("#familyAge"+familyDetails+"").val(age);
	return true;
}

function getPanchayatOrWardByMandalOrTown(mandalTownId,type)
{
	$("#hamletMainDivId").hide();
	
	var jsObj={
		mandalTownId:mandalTownId,
		type:type
	}
	$.ajax({
		  type:'GET',
		  url: 'getPanchayatOrWardByMandalOrTownAction.action',
		  dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null){
			if(type == "Mandal"){
				for(var i in result){
					$("#workerPanchayatId").append("<option value='"+result[i].id+"'>"+result[i].name+"</option>");
				}
			}
			else if(type == "Town"){
				for(var i in result){
					$("#workerWardId").append("<option value='"+result[i].id+"'>"+result[i].name+"</option>");
				}
			}
		}
	});
}
  function clearErrorValidations(){
	
	$("#errStreetRoadLaneId").html("");
	$("#errAnnualIncomeId").html("");
	$("#errInsuranceUserTypeId").html("");
	$("#errDrivingLicenseId").html("");
	$("#errWorkerAadharNoId").html("");
	$("#errWorkerFullNameId").html("");
	$("#errUploadImageId").html("");
	$("#errWorkerOccupationId").html("");
	$("#errWorkerFatherNameId").html("");
	$("#errWorkerMotherNameId").html("");
	$("#errWorkerGenderId").html("");
	$("#errWorkerdobId").html("");
	$("#errWorkerAgeId").html("");
	$("#errWorkerCasteId").html("");
	$("#errWorkerMobileNoId").html("");
	$("#errWorkerCategoryId").html("");
	$("#errMobileNumTypeId").html("");
	$("#errWorkerBankId").html("");
	$("#errWorkerBankNameId").html("");
	$("#errWorkerBranchNameId").html("");
	$("#errWorkerBranchId").html("");
	
	$("#errWorkerAccountNoId").html("");
	$("#errWorkerDistrictId").html("");
	$("#errWorkerMandalTownId").html("");
	$("#errWorkerPanchayatId").html("");
	$("#errWorkerHamletId").html("");
	$("#errWorkerWardId").html("");
	$("#errWorkerHouseNoId").html("");
	$("#errWorkerPincodeId").html("");
	
	$("#errDuplicateId").html("");
	$(".errdocumentTypeCls").html("");
	$("#erruploadDocumentId0").html("");
	$(".erruploadDocumentCls").html("");
	
	$("#errfamilyName0").html("");
	$("#errdynamicDob0").html("");
	$("#errfamilyAge0").html("");
	$("#errfamilyGender0").html("");
	$("#errfamilyNominee0").html("");
	$("#errfamilyRelation0").html("");
	$("#errfamilyEducation0").html("");
	$("#errfamilyAadharNo0").html("");
	
	$(".errfamilyName").html("");
	$(".errdynamicDob").html("");
	$(".errfamilyAge").html("");
	$(".errfamilyGender").html("");
	$(".errfamilyNominee").html("");
	$(".errfamilyRelation").html("");
	$(".errfamilyEducation").html("");
	$(".errfamilyAadharNo").html("");
	$("#errIgreeCheckId").html("");
	$("#errTotalPercShareId").html("");
	$("#errGovtSchemesId").html("");
	$("#errdocumentTypeId0").html("");
}
 function generateMsg(workerType){
	var msgType="";
	if(workerType=="labour"){
		msgType="Please Upload Aadhar Front And Back Copies";
	}else if(workerType=="driver"){
		msgType="Please Upload Aadhar & Driving License Front And Back Copies";
	}
	return msgType;
 }
 function buildMsg(index,msg){
	$("#err"+index+"").html(msg);
	$('html,body').animate({
	   scrollTop: $("#err"+index+"").offset().top -100
	});
 }
 function animateFunScrollTop(errorDivId){
		$('html,body').animate({
			scrollTop: $("#"+errorDivId+"").offset().top -100
		});
  }
function validateDetails()
{
   clearErrorValidations();
   var flag = true;var index = "";var msg='';
	
	
	//INSURANCE USER TYPE VALIDATION.
     var insuranceUserType = $("#insuranceUserTypeId").val();
	if(insuranceUserType == null || insuranceUserType == 0){
		$("#errInsuranceUserTypeId").html("Please select User Type");
		animateFunScrollTop("errInsuranceUserTypeId");
		return false;
	}
	
	//AADHAR AND AADHAR ENROLLMENT NUMBER VALIDATION.
	 var aadharNo = $("#workerAadharNoId").val();
	 var aadharEnrollmentNo = $("#workerEnrollmentNoId").val();
	if(aadharNo == null || aadharNo.trim().length == 0){
		if(aadharEnrollmentNo == null || aadharEnrollmentNo.length == 0){
			$("#errWorkerAadharNoId").html("Please enter either Aadhar Number or Aadhar Enrollment Number");
			animateFunScrollTop("errWorkerAadharNoId");
		return false;
		}
	}
	else if(isNaN(aadharNo) || aadharNo.trim().length != 12){
		$("#errWorkerAadharNoId").html("Please enter Valid Aadhar Number");
		animateFunScrollTop("errWorkerAadharNoId");
	    return false;
	}
	
	//NAME VALIDATION.
	 var fullName=$("#workerFullNameId").val();
	 if(fullName == null || fullName.length == 0){
		$("#errWorkerFullNameId").html("Please enter the Name");
		animateFunScrollTop("errWorkerFullNameId");
		return false;
	 }
	
	//Profile Image Uploading Validation Checking.
	var existingPhotoPath = $('#existingImgPathId').val();
	var uploadImage = $("#uploadImageId").val();
	var base64Img = $("#base64Image").val();
	if(existingPhotoPath == null || existingPhotoPath.length == 0)
	{
		if(uploadImage == null || uploadImage.trim().length == 0){
			if(base64Img == null || base64Img.trim().length == 0){
				$("#errUploadImageId").html("Please Upload Image");
				animateFunScrollTop("errUploadImageId");
				return false;
			}
		}
	}
	else if(existingPhotoPath != null || existingPhotoPath.length != 0)
	{
		if(ImageUpdateCase == null || ImageUpdateCase.trim().length == 0){
			if(uploadImage == null || uploadImage.trim().length == 0){
				if(base64Img == null || base64Img.trim().length == 0){
					$("#errUploadImageId").html("Please Upload Image");
					animateFunScrollTop("errUploadImageId");
					return false;
				}
			}
		}
	}
	
	
	//Documents Uploading Validation Checking.
	var workerType="";
	if($("#insuranceUserTypeId").val()==1){
		workerType="labour";
	}else if($("#insuranceUserTypeId").val()==2){
		workerType="driver";
	}
		 	
	
	//mandatory documents validation for labour and driver.
	if(checkSaveOrUpdate=="save"){
		var idsArr=[];
		if($("#documentTypeId0").val()!=null && $("#documentTypeId0").val()>0){
			idsArr.push(parseInt($("#documentTypeId0").val()));
		}
		$(".documentTypeCls").each(function(){
			if($(this).val()>0){
				idsArr.push(parseInt($(this).val()));
			}
		});
		
		if(idsArr!=null && idsArr.length>0){
			
			if(workerType=="labour"){
				
				if($.inArray(1, idsArr)==-1){
					$("#errDuplicateId").html("Please Upload Aadhar Card Front Copy");
					animateFunScrollTop("errDuplicateId");
					return false;
				}
				if($.inArray(2, idsArr)==-1){
					$("#errDuplicateId").html("Please Upload Aadhar Card Back Copy");
					animateFunScrollTop("errDuplicateId");
					return false;
				}
			}
			else if(workerType=="driver"){
				if($.inArray(1, idsArr)==-1){
					$("#errDuplicateId").html("Please Upload Aadhar Card Front Copy");
					animateFunScrollTop("errDuplicateId");
					return false;
				}
				if($.inArray(2, idsArr)==-1){
					$("#errDuplicateId").html("Please Upload Aadhar Card Back Copy");
					animateFunScrollTop("errDuplicateId");
					return false;
				}
				if($.inArray(5, idsArr)==-1){
					$("#errDuplicateId").html("Please Upload Driving Liecence Front Copy");
					animateFunScrollTop("errDuplicateId");
					return false;
				}
				if($.inArray(6, idsArr)==-1){
					$("#errDuplicateId").html("Please Upload Driving Liecence Back Copy");
					animateFunScrollTop("errDuplicateId");
					return false;
				}
			}	
		
	}else{
			var msgType=generateMsg(workerType);
			$("#errDuplicateId").html(msgType);
		   animateFunScrollTop("errDuplicateId");
		   return false;
	}
  }
	if(checkSaveOrUpdate=="update"){//update scenario.
	  
		if(workerId>0 || workerDataId>0){
			var idsArr=[];
			$(".existingDocumentCls").each(function(){
				if($(this).val()>0){
					idsArr.push(parseInt($(this).val()));	
				}
			});
			
			$(".documentTypeCls").each(function(){
				if($(this).val()>0){
					idsArr.push(parseInt($(this).val()));
				}
			});
			
			if(idsArr!=null && idsArr.length>0){
				if(workerType=="labour"){
					if($.inArray(1, idsArr)==-1){
						$("#errDuplicateId").html("Please Upload Aadhar Card Front Copy");
						animateFunScrollTop("errDuplicateId");
						return false;
					}
					if($.inArray(2, idsArr)==-1){
						$("#errDuplicateId").html("Please Upload Aadhar Card Back Copy");
						animateFunScrollTop("errDuplicateId");
						return false;
					}
				}
				else if(workerType=="driver"){
					//var flag=false;
					if($.inArray(1, idsArr)==-1){
						$("#errDuplicateId").html("Please Upload Aadhar Card Front Copy");
						animateFunScrollTop("errDuplicateId");
						return false;
					}
					if($.inArray(2, idsArr)==-1){
						$("#errDuplicateId").html("Please Upload Aadhar Card Back Copy");
						animateFunScrollTop("errDuplicateId");
						return false;
					}
					if($.inArray(5, idsArr)==-1){
						$("#errDuplicateId").html("Please Upload Driving Liecence Front Copy");
						animateFunScrollTop("errDuplicateId");
						return false;
					}
					if($.inArray(6, idsArr)==-1){
						$("#errDuplicateId").html("Please Upload Driving Liecence Back Copy");
						animateFunScrollTop("errDuplicateId");
						return false;
					}
				}	
			}else{
				var msgType=generateMsg(workerType);
			    $("#errDuplicateId").html(msgType);
		        animateFunScrollTop("errDuplicateId");
		        return false;
			}
		}
	}
	
	  
	   //Saving scenario.
	   
	  if(checkSaveOrUpdate=="save"){
	   var docType = $("#documentTypeId0").val();
       var uploadDoc = $("#uploadDocumentId0").val();
	   var docBase64Img = $("#docBase64Image0").val();
	   
		if(docType == null || docType == 0){
			var msgType=generateMsg(workerType);
			$("#errdocumentTypeId0").html("Please Select Document Name.");
			
			$('html,body').animate({
				scrollTop: $("#errdocumentTypeId0").offset().top -100
			});
			return false;
		}
		if(uploadDoc == null || uploadDoc.trim().length == 0){
			if(docBase64Img == null || docBase64Img.trim().length == 0){
				$("#erruploadDocumentId0").html("Please upload Document");
				$('html,body').animate({
					 scrollTop: $("#erruploadDocumentId0").offset().top -100
					 });
				return false;
			}
		}
	  }	
	
	
	
	$(".documentTypeCls").each(function(){//remaining "select document"
		var divId = $(this).attr("id");
		var famValue=$(this).val();
		if(typeof divId != "undefined" && divId != ""){
			if(famValue == null || famValue == 0){
				flag = false;
				index=divId;
				return false;//to stop the for each loop.
			}
		}
	});
	
	if(!flag){
		buildMsg(index,"Please Select Document Name.");
		return false;
	}
	 
	$(".uploadDocumentCls").each(function(){//remaining "select upload document"
	
		var divId = $(this).attr("id");
		if(typeof divId != "undefined"){
			var famValue=$(this).val();
			var docImageId = $(this).parent().find(".docBase64ImageCls").attr("id");
			var docImageVal = $("#"+docImageId).val();
			if(famValue == null || famValue.length == 0){
				if(docImageVal == null || docImageVal.trim().length == 0){
					if(flag){
						flag = false;
						index = divId;
					}
				}
			}
		}	
	});
	
	if(!flag){
		buildMsg(index,"Please upload Document");
		return false;
	}
	
	//FATHER NAME VALIDATION.
	if($("#workerFatherNameId").val() == null || $("#workerFatherNameId").val().length == 0){
		$("#errWorkerFatherNameId").html("Please enter the Father's Name");
		animateFunScrollTop("errWorkerFatherNameId");
		return false;
	}
	//MOTHER NAME VALIDATION.
	if($("#workerMotherNameId").val() == null || $("#workerMotherNameId").val().length == 0){
		$("#errWorkerMotherNameId").html("Please enter the Mother's Name");
		animateFunScrollTop("errWorkerMotherNameId");
		return false;
	}
	//DRIVING LICENSE NUMBER VALIDATION.
	 if($("#insuranceUserTypeId option:selected").text() == 'Driver'){
		if($("#drivingLicenseId").val() == null || $("#drivingLicenseId").val().length == 0){
			$("#errDrivingLicenseId").html("Please enter Driving License Number");
			animateFunScrollTop("errDrivingLicenseId");
			return false;
		}
	 }
	 //OCCUPATION VALIDATION.
	if( $("#workerOccupation").val() == null || $("#workerOccupation").val() == 0 ){
		$("#errWorkerOccupationId").html("Please select Any Occupation");
		animateFunScrollTop("errWorkerOccupationId");
		return false;
	}
	//WORKER ANNUAL INCOME VALIDATION.
	if($("#workerAnnualIncome").val()==null || $("#workerAnnualIncome").val()==0){
		$("#errAnnualIncomeId").html("Please Enter Worker Annual Income");
		animateFunScrollTop("errAnnualIncomeId");
		return false; 
	}else if($("#workerAnnualIncome").val()!=null && $("#workerAnnualIncome").val()>180000){
		$("#errAnnualIncomeId").html("Annual Income should be Less than 180000");
		animateFunScrollTop("errAnnualIncomeId");
		return false; 
	}
	//GENDER VALIDATION.
	if($("#workerGenderId").val() == null || $("#workerGenderId").val() == 0){
		$("#errWorkerGenderId").html("Please select the Gender");
		animateFunScrollTop("errWorkerGenderId");
		return false;
	}
	//DOB VALIDATION.
	if($("#workerDobId").val() == null || $("#workerDobId").val().length == 0){
		$("#errWorkerdobId").html("Please enter the Date Of Birth");
		animateFunScrollTop("errWorkerdobId");
		return false;
	}
	 //AGE VALIDATION.
    var age = $("#workerAgeId").val();
	if(age == null || age == 0 || age.trim().length==0){
		
		$("#errWorkerAgeId").html("Please enter the Age");
		animateFunScrollTop("errWorkerAgeId");
		return false;
	}else if(age != null && (age<18 || age>60)){
		$("#errWorkerAgeId").html("Age Should Between 18 to 60");
		animateFunScrollTop("errWorkerAgeId");
		return false;
	}
	
	//CASTE VALIDATION
	if( $("#workerCaste").val() == null || $("#workerCaste").val() == 0){
		$("#errWorkerCasteId").html("Please select the Caste");
		animateFunScrollTop("errWorkerCasteId");
		return false;
	}
	//MOBILE VALIDATION.
	var mobileNo=$("#workerMobileNoId").val();
	if(mobileNo == null || mobileNo.length == 0){
		$("#errWorkerMobileNoId").html("Please enter the Mobile Number");
		animateFunScrollTop("errWorkerMobileNoId");
		return false;
	}
	if(mobileNo.length != 10 || isNaN(mobileNo)){
		$("#errWorkerMobileNoId").html("Please enter Valid Mobile Number");
		animateFunScrollTop("errWorkerMobileNoId");
		return false;
	}
	if(invalidMobilesList.indexOf(mobileNo) != -1){
		$("#errWorkerMobileNoId").html("Please enter Valid Mobile Number");
		animateFunScrollTop("errWorkerMobileNoId");
		return false;
	}
	//MOBILE TYPE VALIDATION
	if( $("#mobileNumType").val() == null || $("#mobileNumType").val() == 0){
		$("#errMobileNumTypeId").html("Please select Mobile Type");
		animateFunScrollTop("errMobileNumTypeId");
		return false;
	}
	//WORKER CATEGORY VALIDATION
	if( $("#workerCategory").val() == null || $("#workerCategory").val() == 0){
		$("#errWorkerCategoryId").html("Please select Category");
		animateFunScrollTop("errWorkerCategoryId");
		return false;
	}
	//BANK VALIDATIONS.
	if( $("#workerBank").val() == null || $("#workerBank").val() == 0){
		$("#errWorkerBankId").html("Please select the Bank");
		animateFunScrollTop("errWorkerBankId");
		return false;
	}
	var bankText = $("#workerBank option:selected").text();
	var bankName = $("#workerBankNameId").val();
	if(bankText == "Others"){
		if(bankName.length == 0 || bankName == null){
			$("#errWorkerBankNameId").html("Please enter the Name Of Bank");
			animateFunScrollTop("errWorkerBankNameId");
			return false;
		}
	}
	//BRANCH VALIDATION
    var branchId = $("#workerBranchIdOfBank option:selected").val();
	var branchName= $("#workerBranchNameId").val();
	if(branchId == null || branchId == 0){
		if(branchName == null || branchName.length == 0){
			$("#errWorkerBranchId").html("Please select the Branch or specify any other Branch Name");
			animateFunScrollTop("errWorkerBranchId");
			return false;
		}
	}
	//ACCOUNT NO VALIDATION.
	var accountNo=$("#workerAccountNoId").val();
	if(accountNo == null || accountNo.length == 0){
		$("#errWorkerAccountNoId").html("Please enter the Account Number");
		animateFunScrollTop("errWorkerAccountNoId");
		return false;
	}
	if(isNaN(accountNo)){
		$("#errWorkerAccountNoId").html("Please enter valid Account Number");
		animateFunScrollTop("errWorkerAccountNoId");
		return false;
	}
	//House No Validation.
	if( $("#workerHouseNoId").val() == null || $("#workerHouseNoId").val().length == 0){
		$("#errWorkerHouseNoId").html("Please enter House Number");
		animateFunScrollTop("errWorkerHouseNoId");
		return false;
	}
	//PINCODE VALIDATION.
	var pincode = $("#workerPincodeId").val();
	if(pincode == null || pincode.length == 0){
		$("#errWorkerPincodeId").html("Please enter Pincode");
		animateFunScrollTop("errWorkerPincodeId");
		return false;
	}
	if(isNaN(pincode) || pincode.trim().length != 6){
		$("#errWorkerPincodeId").html("Please enter Valid Pincode");
		animateFunScrollTop("errWorkerPincodeId");
		return false;
	}
	if(pincode != null && pincode.trim().length == 6){
		var pincodeFirstIndex = pincode.charAt(0);
		if(pincodeFirstIndex != 5){
			$("#errWorkerPincodeId").html("Please enter Valid Pincode");
			animateFunScrollTop("errWorkerPincodeId");
			return false;
		}
	}
	//DISTRICT VALIDATION
	if( $("#workerDistrict").val() == null || $("#workerDistrict").val() == 0){
		$("#errWorkerDistrictId").html("Please select any District");
		animateFunScrollTop("errWorkerDistrictId");
		return false;
	}
	//MANDAL/TOWN VALIDATION
	var mandalTown = $("#workerMandalOrTownId").val();
	if(mandalTown == null || mandalTown == 0){
		$("#errWorkerMandalTownId").html("Please select any Mandal/Town");
		animateFunScrollTop("errWorkerMandalTownId");
		return false;
	}
	var firstPosition = mandalTown.charAt(0);
	var panchayat=0;
	panchayat = $("#workerPanchayatId").val();
	var ward=0;
	ward = $("#workerWardId").val();
	if(firstPosition == 1){
		if(panchayat == null || panchayat == 0){
			$("#errWorkerPanchayatId").html("Please select any Village");
			animateFunScrollTop("errWorkerPanchayatId");
			return false;
		}
	}
	else if(firstPosition == 2){
		if(ward == null || ward == 0){
			$("#errWorkerWardId").html("Please select any Ward");
			animateFunScrollTop("errWorkerWardId");
			return false;
		}
	}
	
	var hamletId = $("#workerHamletId").val();
		if(hamletId ==null || hamletId ==0){
		if(firstPosition == 1){
			$("#errWorkerHamletId").html("Please select any Hamlet");
			animateFunScrollTop("errWorkerHamletId");
			return false;
		}
	}
	
	//if town select-- street should enter
	if($("#workerMandalOrTownId").val()>0){
		var selectedVal = $("#workerMandalOrTownId").val();
		if(selectedVal.substring(0,1)==2){
			if($("#streetRoadLaneId").val()==null || $("#streetRoadLaneId").val().trim()==""){
				$("#errStreetRoadLaneId").html("Please Enter Street.");
				animateFunScrollTop("errStreetRoadLaneId");
				return false;
			}
		}
	}
	
	//govt scheme validation
	var govtScheme = $("#familyScheme0").val();
	var govtSchemeText = $("#familyScheme0 option:selected").text();
	var govtOtherScheme = $("#familyOtherScheme0").val();
	if(govtScheme != 0 && govtSchemeText == "Others"){
		if(govtOtherScheme == null || govtOtherScheme.length == 0){
			$("#errGovtSchemesId").html("Please Enter Any Other Scheme Name");
			animateFunScrollTop("errGovtSchemesId");
			return false;
		}
	}
	
	var govtRegistrationNo = $("#familyRegistrationNo0").val();
	if(govtScheme != 0){
		if(govtRegistrationNo == null || govtRegistrationNo.length == 0){
			$("#errGovtSchemesId").html("Please Enter Registration Number For Scheme");
			animateFunScrollTop("errGovtSchemesId");
			return false;
		}
	}
	
		$(".familyScheme").each(function(){
		var divId = $(this).attr("id");
		if(typeof divId != "undefined" && divId != ""){
			var idNumber = divId.substring(12);
			var otherSchemeId = "familyOtherScheme"+idNumber;
			var registrationNoId = "familyRegistrationNo"+idNumber;
			var famValue=$(this).val();
			var otherSchemeVal = $("#"+otherSchemeId).val();
			var registrationNoVal = $("#"+registrationNoId).val();
			if(famValue != 0 && famValue == 8){
				if(otherSchemeVal == null || otherSchemeVal.length == 0){
					if(flag){
						flag = false;
						index = "scheme";
					}
				}
				else if(registrationNoVal == null || registrationNoVal.length == 0){
					if(flag){
						flag = false;
						index = "regNo";
					}
				}
			}
			else if(famValue != 0){
				if(registrationNoVal == null || registrationNoVal.length == 0){
					if(flag){
						flag = false;
						index = "regNo";
					}
				}
			}
		}	
	});
	
	if(!flag){
		if(index == "scheme"){
			$("#errGovtSchemesId").html("Please Enter Any Other Scheme Name");
			animateFunScrollTop("errGovtSchemesId");
			return false;
		}
		else if(index == "regNo"){
			$("#errGovtSchemesId").html("Please Enter Registration Number For Scheme");
			animateFunScrollTop("errGovtSchemesId");
			return false;
		}
	}
	//FAMILY MEMBERS VALIDATION.
	var fmName = $("#familyName0").val();
	if(fmName == null || fmName.length == 0){
		$("#errfamilyName0").html("Please enter Name");
		animateFunScrollTop("errfamilyName0");
		return false;
	}
	
	var fmAadharNo = $("#familyAadharNo0").val();
	if(fmAadharNo != null && fmAadharNo.trim().length != 0){
		if(isNaN(fmAadharNo) || fmAadharNo.trim().length != 12){
			$("#errfamilyAadharNo0").html("Please enter Valid Aadhar Number");
			animateFunScrollTop("errfamilyAadharNo0");
			return false;
		}
	}
	var fmAge = $("#familyAge0").val();
	if(fmAge == null || fmAge.length == 0){
		$("#errfamilyAge0").html("Please enter Age");
		animateFunScrollTop("errfamilyAge0");
		return false;
	}
	 var fmGender = $("#familyGender0").val();
	if(fmGender == null || fmGender == 0){
		$("#errfamilyGender0").html("Please select Gender");
		animateFunScrollTop("errfamilyGender0");
		return false;
	}
	
	var fmPercShare = 0;
	fmPercShare = $("#familyNominee0").val();
	if(fmPercShare == null || fmPercShare == 0){
		$("#errfamilyNominee0").html("Please enter Percentage Share");
		animateFunScrollTop("errfamilyNominee0");
		return false;
	}
	var fmRelation = 0;
	fmRelation = $("#familyRelation0").val();
	if(fmRelation == null || fmRelation == 0){
		$("#errfamilyRelation0").html("Please select Relation");
		animateFunScrollTop("errfamilyRelation0");
		return false;
	}
	
	var fmHighEduc = 0;
	fmHighEduc = $("#familyEducation0").val();
	if(fmHighEduc == null || fmHighEduc == 0){
		$("#errfamilyEducation0").html("Please select Highest Education");
		animateFunScrollTop("errfamilyEducation0");
		return false;
	}
	
	
	$(".familyName").each(function(){
		var divId = $(this).attr("id");
		
		if(typeof divId != "undefined"){
			var famValue=$(this).val();
			if(famValue == null || famValue.length == 0){
				if(flag){
					
					flag = false;
					index = divId;
				}
			}
		}	
	});
	
	if(!flag){
		$("#err"+index+"").html("Please enter Name1");
			$('html,body').animate({
			 scrollTop: $("#err"+index+"").offset().top -100
			 });
		return false;
	}
	
	$(".dynamicDob").each(function(){
		var divId = $(this).attr("id");
		if(typeof divId != "undefined"){
			var famValue=$(this).val();
				if(famValue == null || famValue.length == 0){
					if(flag){
						flag = false;
						index = divId;
					}
			}
		}	    
	});
	
	if(!flag){
		$("#err"+index+"").html("Please enter Date Of Birth");
			$('html,body').animate({
			 scrollTop: $("#err"+index+"").offset().top -100
			 });
		return false;
	}
	
	$(".familyAadharNo").each(function(){
		var divId = $(this).attr("id");
		if(typeof divId != "undefined"){
			var famValue=$(this).val();
			if(famValue != null && famValue.trim().length != 0){
				if(famValue.trim().length != 12 || isNaN(famValue)){
					if(flag){
						flag = false;
						index = divId;
					}
				}
			}
		}
	 });
	 
	 if(!flag){
		$("#err"+index+"").html("Please enter valid Aadhar Number");
			$('html,body').animate({
			 scrollTop: $("#err"+index+"").offset().top -100
			 });
		return false;
		
	}
	
	$(".familyAge").each(function(){
		var divId = $(this).attr("id");
		if(typeof divId != "undefined"){
			var famValue=$(this).val();
			if(famValue == null || famValue == 0 || isNaN(famValue)){
				if(flag){
					flag = false;
					index = divId;
				}
			}
		}
	 });
	 
	 if(!flag){
		$("#err"+index+"").html("Please enter valid Age");
			$('html,body').animate({
			 scrollTop: $("#err"+index+"").offset().top -100
			 });
		return false;
		
	}
	
	$(".familyGender").each(function(){
		var divId = $(this).attr("id");
		if(typeof divId != "undefined"){
			var famValue=$(this).val();
			if(famValue == null || famValue == 0){
				if(flag){
					flag = false;
					index = divId;
				}
			}
		}
	    
	});
	
	if(!flag){
		$("#err"+index+"").html("Please select Gender");
			$('html,body').animate({
			 scrollTop: $("#err"+index+"").offset().top -100
			 });
		return false;
		
	}
	
	var totalPercShare = 0;
	totalPercShare = Number(totalPercShare) + Number(fmPercShare);
	$(".familyNominee").each(function(){
		var divId = $(this).attr("id");
		if(typeof divId != "undefined" && divId != "familyNominee0"){
			var famValue=$(this).val();
			totalPercShare = Number(totalPercShare) + Number(famValue);
			if(famValue == null || famValue.length == 0){
				if(flag){
					flag = false;
					index = divId;
				}
			}
		}
	    
	});
	
	if(!flag){
		$("#err"+index+"").html("Please enter Percentage Share");
			$('html,body').animate({
			 scrollTop: $("#err"+index+"").offset().top -100
			 });
		return false;
		
	}
	if(totalPercShare != 100){
		$("#errTotalPercShareId").html("Entered Total Percentage Share is "+totalPercShare+ ", Please Change Total Percentage Share to 100." );
		$('html,body').animate({
			 scrollTop: $("#errTotalPercShareId").offset().top -100
			 });
		return false;
	}
	
	$(".familyRelation").each(function(){
		var divId = $(this).attr("id");
		if(typeof divId != "undefined" && divId != ""){
			var famValue=$(this).val();
			if(famValue == null || famValue == 0){
				if(flag){
					flag = false;
					index = divId;
				}
			}
		}
	    
	});
	
	if(!flag){
		$("#err"+index+"").html("Please select Relation");
			$('html,body').animate({
			 scrollTop: $("#err"+index+"").offset().top -100
			 });
		return false;
		
	}
	
	$(".familyEducation").each(function(){
		var divId = $(this).attr("id");
		if(typeof divId != "undefined" && divId != ""){
			var famValue=$(this).val();
			if(famValue == null || famValue == 0){
				if(flag){
					flag = false;
					index = divId;
				}
			}
		}
	    
	});
	
	if(!flag){
		$("#err"+index+"").html("Please select Highest Education");
			$('html,body').animate({
			 scrollTop: $("#err"+index+"").offset().top -100
			 });
		return false;
	}
	
	
	
	var igreeCheck = document.getElementById("igreeCheckId").checked;
	if(igreeCheck == false){
		$("#errIgreeCheckId").html("Please agree the below Condition");
		$('html,body').animate({
			 scrollTop: $("#errIgreeCheckId").offset().top -100
			 });
		return false;
	}
	
	
	return true;
	
} 
	
	 $('.migChkBox').change(function(){
		if($(this).val()=="N"){
			$(this).val('Y');
		}else{
			$(this).val('N');
		}
	});
	
	function getHamletsOfVillage(villageId){
		
		//error fields empty 
			$("#errWorkerDistrictId").html('');
			$("#errWorkerMandalTownId").html('');
			$("#errWorkerPanchayatIds").html('');
			$("#errWorkerHamletId").html('');
		
		$("#hamletMainDivId").hide();
		$("#workerHamletId option").remove();
		$("#workerHamletId").append('<option value="0">Select Hamlet</option>');
		
		var jsObj = {
			villageId:villageId
		}
		$.ajax({
			  type:'POST',
			  url: 'getHamletsOfVillageAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result !=null && result.length>0){
				$("#hamletMainDivId").show();
				for(var i in result){
					$("#workerHamletId").append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
				}
			}
		});
		
	}
	
var maleRelationSList = [{id:1, value:'Father'},{id:3, value:'Husband'},{id:5, value:'Brother'},{id:7, value:'Father-in-Law'},{id:9, value:'Brother-in-Law'},{id:11, value:'Son'},{id:13, value:'Son-in-Law'},{id:15, value:'Grand Son'},{id:17, value:'Grand Father'},{id:19, value:'Uncle'},{id:21, value:'Others'}];

var femaleRelationsList = [{id:2, value:'Mother'},{id:4, value:'Wife'},{id:6, value:'Sister'},{id:8, value:'Mother-in-Law'},{id:10, value:'Sister-in-Law'},{id:12, value:'Daughter'},{id:14, value:'Daughter-in-Law'},{id:16, value:'Grand Daughter'},{id:18, value:'Grand Mother'},{id:20, value:'Aunt'},{id:21, value:'Others'}];

var transGenderRelationSList = [{id:1, value:'Father'},{id:2, value:'Mother'},{id:3, value:'Husband'},{id:4, value:'Wife'},{id:5, value:'Brother'},{id:6, value:'Sister'},{id:7, value:'Father-in-Law'},{id:8, value:'Mother-in-Law'},{id:9, value:'Brother-in-Law'},{id:10, value:'Sister-in-Law'},{id:11, value:'Son'},{id:12, value:'Daughter'},{id:13, value:'Son-in-Law'},{id:14, value:'Daughter-in-Law'},{id:15, value:'Grand Son'},{id:16, value:'Grand Daughter'},{id:17, value:'Grand Father'},{id:18, value:'Grand Mother'},{id:19, value:'Uncle'},{id:20, value:'Aunt'},{id:21, value:'Others'}];

$(document).on("change","#familyGender0",function(){
	
	$("#familyRelation0 option").remove();
	$("#familyRelation0").append('<option value="0">Select Relation</option>');
	
	var genderVal = $(this).val();
	if(genderVal == 1){
		for(var i=0;i<maleRelationSList.length;i++){    
			$("#familyRelation0").append("<option value='"+maleRelationSList[i].id+"'>"+maleRelationSList[i].value+"</option>");
		}
	}
	else if(genderVal == 2){
		for(var i=0;i<femaleRelationsList.length;i++){
			$("#familyRelation0").append("<option value='"+femaleRelationsList[i].id+"'>"+femaleRelationsList[i].value+"</option>");
		}
	}
	else{
		for(var i=0;i<transGenderRelationSList.length;i++){
			$("#familyRelation0").append("<option value='"+transGenderRelationSList[i].id+"'>"+transGenderRelationSList[i].value+"</option>");
		}
	}
});

$(document).on("change",".familyGender",function(){
	
	var divId = $(this).attr("id");
	var value = $(this).val();
	var index = divId.charAt(12);
	
	$("#familyRelation"+index+ " option").remove();
	$("#familyRelation"+index).append('<option value="0">Select Relation</option>');
	
	if(value == 1 || value == 'M'){
		for(var i=0;i<maleRelationSList.length;i++){
			$("#familyRelation"+index).append("<option value='"+maleRelationSList[i].id+"'>"+maleRelationSList[i].value+"</option>");
		}
	}
	else if(value == 2 || value == 'F'){
		for(var i=0;i<femaleRelationsList.length;i++){
			$("#familyRelation"+index).append("<option value='"+femaleRelationsList[i].id+"'>"+femaleRelationsList[i].value+"</option>");
		}
	}
	else{
		for(var i=0;i<transGenderRelationSList.length;i++){
			$("#familyRelation"+index).append("<option value='"+transGenderRelationSList[i].id+"'>"+transGenderRelationSList[i].value+"</option>");
		}
	}
});
$("#workerMandalOrTownId").change(function(){
	if($("#workerMandalOrTownId").val()>0){
		var selectedVal = $("#workerMandalOrTownId").val();
	if(selectedVal.substring(0,1)==2){
			$("#errStreetTownId").show();
		}else{
			$("#errStreetTownId").hide();
		}
	}
});
$("#workerHamletId").change(function(){
	//error fields empty 
	$("#errWorkerDistrictId").html('');
	$("#errWorkerMandalTownId").html('');
	$("#errWorkerPanchayatIds").html('');
	$("#errWorkerHamletId").html('');
});