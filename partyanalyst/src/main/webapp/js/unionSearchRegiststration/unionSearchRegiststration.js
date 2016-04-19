
$(".searchCls").click(function(){
	//$("#otpSuccessDiv").html("");
	$("#errChkDivId").html("");
	$("#nextStepId").hide();
	$("#success").hide();
	$("#fail").hide();
	$("#cadreDetailsDiv").hide();
	$("#cadreDetailsDiv").html("");
	$(".paginationDivId").html('');
	$("#generateOtpId").hide();
	$("#otpId").hide();
	$("#getOtpId").html("");
	$('#searchErrDiv').html('');
	var choice = $(this).val();
	if(choice=="no"){searchBy
		$("#searchBy").attr("placeholder","Search By VoterCard Number");
		$(".inputChoice").hide();
		$("#searchBy").val("");
		$('#cadreDetailsDiv').html();
	}else{
		$("#searchBy").attr("placeholder","Search By Membership Id/Mobile No/Voter Id");
		$(".inputChoice").show();
		$("#searchBy").val("");
	}
});
$(".searchTypeCls").click(function(){
	//$("#otpSuccessDiv").html("");
	$("#errChkDivId").html("");
	$("#success").hide();
	$("#fail").hide();
	$("#cadreDetailsDiv").html("");
	$("#generateOtpId").hide();
	$("#otpId").hide();
	$("#getOtpId").html("");
	$("#searchBy").val("");
	$("#cadreDetailsDiv").html("");
	$(".paginationDivId").html('');
	$("#cadreDetailsDiv").hide();
	$("#searchErrDiv").html("");
	$("#nextStepId").hide();
	var id = $(this).attr('id');		
	$('#basicSearchDiv').show();

	if(id.trim() == 'membershipId')
	{
		$('#cadreSearchType').val('membershipId');
	}
	if(id.trim() == 'voterId')
	{
		$('#cadreSearchType').val('voterId');
	}
	if(id.trim() == 'mobileNo')
	{
		$('#cadreSearchType').val('mobileNo');
	}
	
});
	
	
	
$("#searchId").click(function(){
	//debugger;
	$("#generateOtpId").hide();
	$("#nextStepId").hide();
	$("#success").hide();
	$("#otpId").hide();
	$("#otpId").val("");
	$("#searchErrDiv").html("");
	var searchText = $("#searchBy").val().trim();
	var registeredChk = $('input[name="tdpCadreRadio"]:checked').val();
	if(registeredChk == "yes"){
		getCadreDetailsBySearchCriteria(0);
	}
	else if(registeredChk == "no"){
		getVoterDetailsBySearch();
	}
});

function getVoterDetailsBySearch(){
	$('#searchErrDiv').html('');
	$('#cadreDetailsDiv').html('');
	var voterCardNo = $("#searchBy").val();
	if($("#searchBy").val().length==0){
		$('#searchErrDiv').html('Please enter voterCard No.');
		return;
	}
	var jsObj =
	{    
		voterIDCardNo : voterCardNo
	}
	
	$.ajax({    
		type : "POST",
		url : "getVoterDetailsByVoterCardNumberAction.action",
		data : {task:JSON.stringify(jsObj)} ,   
	}).done(function(result){
		if(result.subList != null && result.subList.length > 0){
			buildVoterDetails(result.subList);
		}
	});
}

function buildVoterDetails(result){
	$('#cadreDetailsDiv').show();
	var str = '';
	
	if(result != null){
		for(var i in result){
			str+='<div class="media detailsCls" id="main'+result[i].voterId+'" attr_voterId='+result[i].voterId+' style="border-bottom: 1px solid rgb(51, 51, 51);cursor:pointer;">';
				
				//str+='<span href="#" class="media-left">';
				//str+='<img style="width: 64px; height: 64px;" src="images/Member_thamb_image.png" />';
				//str+='</span>';
				str+='<div class="media-body">';
				str+='<h5 class="media-heading"> <span style="font-weight:bold;"> Name:</span> '+result[i].voterName+'';				
				str+=' <span style="font-weight:bold;"> Relative Name: </span>'+result[i].relativeName+' </h5>';
				str+='<ul class="list-inline">';
				str+='<li>Age:'+result[i].age+'</li>';
				str+='<li>Gender: '+result[i].gender+'</li>';
				if(result[i].mobileNo != null && result[i].mobileNo.length > 0)
					str+='<li>Mobile No:'+result[i].mobileNo+'</span></li>';
				str+='<li>Voter ID: '+result[i].voterIDCardNo+'</li>';
				str+='</ul>';
				str+='<ul class="list-inline">';
				str+='<li>District: '+result[i].districtName+'</li>';
				str+='<li>Constituency: '+result[i].constituencyName+'</li>';
				if(result[i].tehsilId > 0)
					str+='<li>Mandal: '+result[i].tehsilId+'</li>';
				else if(result[i].localElectionBodyId > 0)
					str+='<li>Muncipality: '+result[i].leb+'</li>';
				
				str+='</ul>';
				
				str+='</div>';
				str+='</div>';
			}
		}
		$('#cadreDetailsDiv').html(str);
}

$(document).on("click",".detailsCls",function(){
	var voterId = $(this).attr("attr_voterId");
	getDetailsForVoter(voterId);
});

function getDetailsForVoter(voterId){ 
	//window.open('affiliatedCadreRegistrationAction.action?candidateId='+voterId+'&searchType=voter&constiteucnyId=0&houseNo=0&boothId=0&panchayatId=0&tdpMemberTypeId=5');
	
	
	window.open("affiliatedGradiatesRegistrationAction.action?candidateId="+voterId+"&searchType=voter&constiteucnyId=0&houseNo=0&boothId=0&panchayatId=0&tdpMemberTypeId=5");
}
		  
function getCadreDetailsBySearchCriteria(startIndex){
	//debugger;
	$("#cadreDetailsDiv").html("");
	var mobileNo = '';
	var memberShipCardNo = '';
	var voterCardNo = '';
	if(startIndex == 0)
	{
	$(".paginationDivId").html('');
	}
	$(".paginationDivId").hide();	   
	

	var searchBy = $('#searchBy').val().trim();
	var searchRadioType =$('#cadreSearchType').val();
	
	
	if(searchRadioType == 'membershipId')   
	{
		memberShipCardNo = $('#searchBy').val().trim();
		
		if(searchBy.trim().length == 0 )
		{
			$('#searchErrDiv').html('Please enter Membership Card No.');
			return;       
		}
	}			
	if(searchRadioType == 'voterId')  
	{
		voterCardNo = $('#searchBy').val().trim();
		
		if(searchBy.trim().length == 0 )
		{
			$('#searchErrDiv').html('Please enter Voter Card No.');
			return;
		}
		voterCardNo = voterCardNo +"-HIDE";
	}
	if(searchRadioType == 'mobileNo')
	{	
		mobileNo = $('#searchBy').val().trim();
		
		if(searchBy.trim().length == 0 )
		{
			$('#searchErrDiv').html('Please enter Mobile No.');
			return;
		}
		
		if(searchRadioType=="mobileNo"){
				
				var numericExpression = /^[0-9]+$/;
				if(!$('#searchBy').val().match(numericExpression)){
					$('#searchErrDiv').html('Enter Numerics Only.');
					return;
				}
		}
		
		else if(mobileNo.trim().length != 10)
		{
			$('#searchErrDiv').html('Invalid Mobile No.');
			return;				
		}
	}
	
	$("#searchDataImg").show();  

	var jsObj =
	{  
		locationLevel :2,
		locationValue:3,
		mobileNo: mobileNo,  
		memberShipCardNo: memberShipCardNo,
		voterCardNo:voterCardNo,
		startIndex:startIndex,
		maxIndex : 50,
		removedStatus:false,  
		task:"tdpCadreSearch"     
	}
	
	$.ajax({    
			type : "POST",
			url : "getCadreDetailsAction.action",
			data : {task:JSON.stringify(jsObj)} ,   
		}).done(function(result){
		$(".paginationDivId").show();
			 if(typeof result == "string"){
				if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
				  location.reload(); 
				}
			}
			$("#searchDataImg").hide();
			$('#cadreDetailsDiv').show();
			if(result != null && result.previousRoles != null && result.previousRoles.length>0)
			{
				$("#generateOtpId").show();
				$("#otpId").show();
				$("#otpId").val("");
				buildCadreDetails(result.previousRoles,jsObj);
			}
			else
			{
				$("#generateOtpId").hide();
				$("#otpId").hide();
				$("#otpId").val("");
				$('#cadreDetailsDiv').html("<span style='font-weight:bold;text-align:center;'> No Data Available...</span>");
			}
		});
}

function buildCadreDetails(result,jsObj){
		$(".paginationDivId").show();
		var str ='';
		var elegRolCnt=0;
		var dtCnt = 0;
		if(result != null)
		{
			for(var i in result)
			{  
				
				if(result[i].deletedStatus == "MD"){
					str+='<div class="media eachCadreMainDivCls" style="background: rgba(255, 0, 0, 0.1) none repeat scroll 0 0;padding: 5px;border-bottom: 1px solid rgb(51, 51, 51);" attr_cadre_id='+result[i].tdpCadreId+'>';
				}else{
					 str+='<div class="media" id="main'+result[i].tdpCadreId+'" style="border-bottom: 1px solid rgb(51, 51, 51);" attr_cadre_id='+result[i].tdpCadreId+'>';
				}
				
				str+='<span href="#" class="media-left">';
				str+='<img style="width: 64px; height: 64px;" src="images/cadre_images/'+result[i].imageURL+'" />';
				str+='</span>';
				str+='<div class="media-body">';
				str+='<span class="pull-right"><input type="checkbox" name="otpMobileNo"  attr_cadreId="'+result[i].tdpCadreId+'" value="'+result[i].tdpCadreId+'" id="cadreCheckId'+i+'" class="otpCheckboxCls"/></span>'
				str+='<h5 class="media-heading"><div id="nameId" attr_cadreId="'+result[i].tdpCadreId+'"> <span style="font-weight:bold;"> Name:</span> '+result[i].cadreName+'</div> ';				
				str+='<span style="font-weight:bold;"> Relative Name: </span>'+result[i].relativeName+' </h5>';
				str+='<ul class="list-inline">';
				str+='<li><span style="font-weight:bold;">Age:</span>'+result[i].age+';</li>';
				str+='<li><span style="font-weight:bold;">Gender: </span>'+result[i].gender+'</span></li>';
				str+='<li><span style="font-weight:bold;">Mobile No:</span> <span id="mobile'+result[i].tdpCadreId+'">'+result[i].mobileNo+'</span></li><br>';
				str+='<li><span style="font-weight:bold;">Caste: </span><span id="caste'+result[i].tdpCadreId+'">'+result[i].casteName+'</span></li>';
				str+='<li><span style="font-weight:bold;">Voter ID:</span> '+result[i].voterCardNo+'</li><br>';
				str+='<li><span style="font-weight:bold;">MemberShipNo:</span> '+result[i].memberShipCardId+'</li>';
				str+='<li><span style="font-weight:bold;">Registered Through:</span> '+result[i].dataSourceType+'</li>';
				if(result[i].deletedStatus == "MD"){
					str+='<li><b style="color:red;">Deleted Reason</b> : '+result[i].deletedReason+'</li>';
				}
				else{
					str+='<li id="delete'+result[i].tdpCadreId+'"></li>';
				}
				//str+='<li>Aadhar: '+result[i].imageURL+'</i>';
				str+='</ul>';
				
				str+='<div>';
				
				str+='</div>';
				
				if(result[i].committeePosition != null && result[i].committeePosition.trim().length > 0)
				{
					str+='<ul>';
					str+='<li style="font-weight:bold;display: block;">Existing Designation : '+result[i].committeePosition+' for '+result[i].committeeName+' Committee in '+result[i].committeeLocation+'</li>';	
					if(result[i].previousRoles != null && result[i].previousRoles.length > 0){
						for(var j in result[i].previousRoles){
							str+='<li style="font-weight:bold;">Electoral for '+result[i].previousRoles[j].committeeName+' Committee in '+result[i].previousRoles[j].committeeLocation+'</li>';
						}
					}
					
					str+='</ul>';	
					str+='</div>';
					str+='</div>';
				}
			
				else{
					if(result[i].previousRoles != null && result[i].previousRoles.length > 0){
					    str+='<ul>';
						for(var j in result[i].previousRoles){
							str+='<li style="font-weight:bold;">Electoral for '+result[i].previousRoles[j].committeeName+' Committee in '+result[i].previousRoles[j].committeeLocation+'</li>';
						}
					    str+='</ul>';
					}
					str+='</div>';
					str+='</div>';
				
				}
				elegRolCnt++;
				dtCnt++;
			}
		if(result[0].mobileType > 50)	
		{
		var itemsCount=result[0].mobileType;
	    var maxResults=jsObj.maxIndex;
	   
	     if(jsObj.startIndex==0){
		   $(".paginationDivId").pagination({
			items: itemsCount,
			itemsOnPage: maxResults,
			cssStyle: 'light-theme',
			onPageClick: function(pageNumber, event) {
				var num=(pageNumber-1)*50;
				getCadreDetailsBySearchCriteria(num);
				
			}
			});
		}
		
		}
		}
		$('#cadreDetailsDiv').html(str);
		$('[data-toggle="tooltip"]').tooltip();
}

function generateOTPForMobileNo(){
	$("#success").hide();
	$("#fail").hide();
	var mobileNo = $('input[name="otpMobileNo"]:checked').val();
	$("#getOtpId").html("");
	if (typeof(mobileNo) != "undefined"){
		$("#getOtpId").html("");
		var refNo = Math.floor((Math.random() * 1000000) + 1);
		$("#randomRefNo").val(refNo);
		if(refNo >0)
			$("#getOtpId").html("OTP Reference No: "+refNo+  "(Note: If you not get OTP.click Generate OTP once again.)");
		var jsObj =
		{    
			mobileNo : mobileNo,
			refNo : refNo
		}
		
		$.ajax({    
			type : "POST",
			url : "generateOTPForMobileNumberAction.action",
			data : {task:JSON.stringify(jsObj)} ,   
		}).done(function(result){
			if(result != null && result == "Success"){
				//$("#otpSuccessDiv").html("OTP sent to given number...");
				//$("#success").show();
			}else{
				//$("#fail").show();
			}
		});
		
	}else{
		$("#getOtpId").html("Please select atleast one member.");
	}
}
$("#otpId").keyup(function(){
	var otp=$("#otpId").val().trim();
	if(otp.length==6){
		validateOTP(otp);
	}
});
function validateOTP(otp){
	var mobileNo = $('input[name="otpMobileNo"]:checked').val();
	var refNo = $("#randomRefNo").val();
	//alert(mobileNo+"::"+refNo+"::"+otp);
	var jsObj =
	{    
		mobileNo : mobileNo,
		refNo : refNo,
		otp : otp
	}
	
	$.ajax({    
		type : "POST",
		url : "validateOTPAction.action",
		data : {task:JSON.stringify(jsObj)} ,   
	}).done(function(result){
		if(result != null && result=="Success"){
			//$("#otpSuccessDiv").html("success");
			$("#success").show();
			$("#nextStepId").show();
			$(".otpCheckboxCls").prop("disabled", true);
		}else{
			$("#fail").show();
		}
	});
}

$(document).on("click",".otpCheckboxCls",function(){
	$("#getOtpId").html("");
	$("#errChkDivId").html("");
	$this=$(this);
	var count = $("input.otpCheckboxCls:checked").length;
	if(count>1){
		$('.otpCheckboxCls').each(function() { 
                this.checked = false;                 
        }); 
		$(this).prop( "checked", true );
	}
	
	
});

$(document).on("click","#nextStepId",function(){
	var count = $("input.otpCheckboxCls:checked").length;
	if(count==0){
		$("#errChkDivId").html("Please Select Atleast One Checkbox.");
		return;
	}
	var caderId = $('input[name="otpMobileNo"]:checked').val();//attr("attr_cadreId");
	
	//var caderId=$(this).attr("attr_cadreId");//target="_blank"
	window.open("affiliatedGradiatesRegistrationAction.action?candidateId="+caderId+"&searchType=cadre&constiteucnyId=0&houseNo=0&boothId=0&panchayatId=0&tdpMemberTypeId=5");
});

$(document).on("click",".cadreDetailsCls",function(){
		var cadreId=$(this).attr("attr_cadre_id");
		var memberShipId=$(this).attr("attr_membership_id");
		var redirectWindow=window.open('cadreDetailsAction.action?cadreId='+cadreId+'','_blank');
	});
	
$(document).on("click",".cadreRemoveCls",function(){
	  
	  var tdpCadreId = $(this).attr("attr_cadre_id");
	  var cadreName= $(this).attr("attr_cadre_name");
	  
	  $("#hiddenCadreId").val(tdpCadreId);
	  $("#cadreName").html(cadreName);
	  
	  $("#errorDivId").html("");
	  $("#successDivId").html("");
	   $("#remarkTextAreaId").val("");
	  
	   getAllCadreDeleteReasons();
	  
	$("#removeModalDivId").modal("show");
  });	
  
  
$(document).on("click",".updateCadreClass",function(){
	$("#modalSuccessId").html('');
	buildModal(this);
});


$(document).on("click","#updatingCadreId",function(){
	  
	  $("#updateErrorMobileId").html('');
	  $("#updateErrorCasteId").html('');
	   
	  var tdpCadreId=$(this).attr("attr_cadre_id");
	  var mobileNo=$('#updateCadreMobileId').val();
      var casteId=$('#updateCadreCasteSelectId option:selected').val();
      var casteName=$('#updateCadreCasteSelectId option:selected').text();
	  
      if(isNaN(mobileNo) || mobileNo.indexOf(" ") != -1){
		 $("#updateErrorMobileId").html("Please Enter Numbers Only...");
		 return;
	  }
      if(mobileNo.trim().length < 10){
		 $("#updateErrorMobileId").html("Please Enter Valid Mobile Number...");
		 return;
      }
	  if(casteId==0){
		  $("#updateErrorCasteId").html("Please Select Caste.");
		  return;
	  }
	  if(!confirmDelete("Are you sure you want to Update Cadre ?")){
		 return;
	 }
	 $("#modalSuccessId").html("<span style='color:green;'>Please Wait ,While updating...</span>");
	 
	  var jObj={
		         tdpCadreId:tdpCadreId,
		         mobileNo:mobileNo,
				 casteId:casteId
		       };
	  $.ajax({
		  type:'POST',
		  url: 'updateMobileNumberAndCasteForCadreAction.action',
		  dataType: 'json',
		  data: {task:JSON.stringify(jObj)},
		  }).done(function(result){
				if(result != null){
					
					if(result.resultCode == 0){
					   $("#modalSuccessId").html("<span style='color:green;'>MobileNo And Caste Updated Successfully.</span>");
					   
					   //Refreshing data.
					   $("#uc"+tdpCadreId).attr('attr_mobile_no',mobileNo);
					   $("#uc"+tdpCadreId).attr('attr_caste_name',casteName);
					   
					   $("#mobile"+tdpCadreId).html(mobileNo);
					   $("#caste"+tdpCadreId).html(casteName);
					   
					   setTimeout(function(){
				            $("#modalDivId").modal("hide");
				       }, 3000);
					}else if(result.resultCode == 1){
						$("#modalSuccessId").html("<span style='color:red;'>Sorry,MobileNo And Caste Are Not Updated.</span>");
					}
				}
		  });
  });



function buildModal(cadreDetails){
	    
		$("#modalTitleNameId").html('');
	    $("#modalBodyDivId").html('');
		$("#modalfooterNameId").html('');
		
		$("#modalTitleNameId").html('Update Cadre Mobile And Caste.');
		$("#modalfooterNameId").html('<button type="button" id="updatingCadreId" attr_cadre_id='+$(cadreDetails).attr("attr_cadre_id")+' class="btn btn-primary btn-sm">Update</button>');
		
	    var str='';
		str+='<div class="row">';
		str+='<div class="col-md-12">';
		str+='<div><b>Cadre Name :</b> <span>'+$(cadreDetails).attr("attr_cadre_name")+'</span></div>';
		str+='</div>';
		str+='</div>';

		str+='<div class="row">';
		str+='<div class="col-md-6 m_top10">';
		str+='<div><b>Mobile NO : <span style="color:red">*</span></b> ';
		str+='<input class="form-control" id="updateCadreMobileId" maxlength="10" value="'+$(cadreDetails).attr("attr_mobile_no")+'"></input></div>';
		str+='<div id="updateErrorMobileId" style="color:red;"></div>';
		str+='</div>';
		str+='<div class="col-md-6 m_top10">';
		str+='<div><b>Caste <span style="color:red">*</span>:</b>';
		    str+='<select id="updateCadreCasteSelectId" class="form-control">';
		        str+='<option value="0">Select Caste</option>';
				for(var i in casteArray){
					if(casteArray[i].name== $(cadreDetails).attr("attr_caste_name")){
						str+='<option value="'+casteArray[i].id+'" selected>'+casteArray[i].name+'</option>';
					}else{
						str+='<option value="'+casteArray[i].id+'">'+casteArray[i].name+'</option>';
					}	
				}
		    str+='</select>';
		str+='</div>';
		str+='<div id="updateErrorCasteId" style="color:red;"></div>';
		str+='</div>';
		
		str+='</div>';
		
	  $("#modalBodyDivId").html(str);
	  $("#modalDivId").modal("show");
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

function confirmDelete(msg){
	var deleteCadre = confirm(msg);
	  if (deleteCadre)
		  return true;
	  else    
		return false;   
}

/* $(document).on('click','#nameId',function(){
	var caderId=$(this).attr("attr_cadreId");//target="_blank"
	window.open("affiliatedGradiatesRegistrationAction.action?candidateId="+caderId+"&searchType=cadre&constiteucnyId=0&houseNo=0&boothId=0&panchayatId=0&tdpMemberTypeId=5");
});
   */
 