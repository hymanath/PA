
$(".searchCls").click(function(){
	$("#otpErr").html("");
	$("#constituencyId").val(0);
	$("#errChkDivId").html("");
	$("#nextStepId").hide();
	$("#success").hide();
	$("#fail").hide();
	$(".cadreMemberListCls").hide();
	$("#cadreDetailsDiv").hide();
	$("#cadreDetailsDiv").html("");
	$(".paginationDivId").html('');
	$("#generateOtpId").hide();
	$("#submitId").hide();
	$("#otpId").hide();
	$("#otpSpanId").hide();
	$("#otpId").val("");
	$("#getOtpId").html("");
	$('#searchErrDiv').html(''); 
	var choice = $(this).val();
	if(choice=="no"){
		$("#searchBy").attr("placeholder","Search By VoterCard Number");
		$(".inputChoice").hide();
		$("#searchBy").val("");
		$('#cadreDetailsDiv').html("");
		$("#searchBy").removeAttr("maxLength"); 
		$("#searchBy").removeClass("onlyDigit");
		$("#searchBy").unbind('keydown');
		$("#searchBy").addClass("txtNumeric");
		initializeTextNumber();
	}else{
		$("#searchBy").attr("placeholder","Search By Membership Id/Mobile No/Voter Id");
		$(".inputChoice").show();
		$("#searchBy").val("");
		var id = $('input[name="searchBasedOn"]:checked').attr("id");
		if(id.trim() == 'membershipId')
	{
		$("#searchBy").removeClass("txtNumeric");
		//$("#searchBy").attr("maxLength","8"); 
		$("#searchBy").removeAttr("maxLength"); 
		$("#searchBy").addClass("onlyDigit");  
		initializeNumber();
		$('#cadreSearchType').val('membershipId');
	}
	if(id.trim() == 'voterId')
	{
		$("#searchBy").removeAttr("maxLength"); 
		$("#searchBy").removeClass("onlyDigit");
		$("#searchBy").unbind('keydown');
		$("#searchBy").addClass("txtNumeric");
		initializeTextNumber();
		$('#cadreSearchType').val('voterId');
	}
	if(id.trim() == 'mobileNo')   
	{
		$("#searchBy").removeClass("txtNumeric");
		$("#searchBy").unbind('keydown');
		$("#searchBy").addClass("onlyDigit");
		$("#searchBy").attr("maxLength","10"); 
		initializeNumber(); 
		$('#cadreSearchType').val('mobileNo');
	}
		
	}
});
$(".searchTypeCls").click(function(){
	//debugger;
	$("#otpErr").html("");
	$("#constituencyId").val(0);
	$("#errChkDivId").html("");
	$("#success").hide();
	$("#fail").hide();
	$(".cadreMemberListCls").hide();
	$("#cadreDetailsDiv").html("");
	$("#generateOtpId").hide();
	$("#submitId").hide();
	$("#otpId").hide();
	$("#otpSpanId").hide();
	$("#otpId").val("");
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
		$("#searchBy").removeClass("txtNumeric");
		//$("#searchBy").attr("maxLength","8"); 
		$("#searchBy").removeAttr("maxLength"); 
		$("#searchBy").addClass("onlyDigit");  
		initializeNumber();
		$('#cadreSearchType').val('membershipId');
	}
	if(id.trim() == 'voterId')
	{
		$("#searchBy").removeAttr("maxLength"); 
		$("#searchBy").removeClass("onlyDigit");
		$("#searchBy").unbind('keydown');
		$("#searchBy").addClass("txtNumeric");
		initializeTextNumber();
		$('#cadreSearchType').val('voterId');
	}
	if(id.trim() == 'mobileNo')
	{
		$("#searchBy").removeClass("txtNumeric");
		$("#searchBy").unbind('keydown');
		$("#searchBy").addClass("onlyDigit");
		$("#searchBy").attr("maxLength","10"); 
		initializeNumber(); 
		$('#cadreSearchType').val('mobileNo');
	}
	
});

$(document).keypress(function(e) {
	if(e.keyCode==13){
		var registeredChk = $('input[name="tdpCadreRadio"]:checked').val();
		if(registeredChk=="yes"){
			getCadreDetailsBySearchCriteria(0);
		}else{
			getVoterDetailsBySearch();
		}
	}
});

$("#searchId").click(function(){
	$("#otpErr").html("");
	$('#getOtpId').html("");
	$(".cadreMemberListCls").hide();
	$("#cadreDetailsDiv").hide();
	$("#generateOtpId").hide();
	$("#submitId").hide();
	$("#nextStepId").hide();
	$("#success").hide();
	$("#otpId").hide();
	$("#otpSpanId").hide();
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
	$(".cadreMemberListCls").hide();
	$('#searchErrDiv').html('');
	$('#cadreDetailsDiv').html('');
	var constituencyId = $('#constituencyId').val();
	/* if(constituencyId==0){
		$('#constErrId').html('Please Select Your Constituency.');
		return;
	} */
	var voterCardNo = $("#searchBy").val();
	if($("#searchBy").val().length==0){
		$('#searchErrDiv').html('Please Enter Voter ID /Voter Card No.');
		return;
	}
	$("#searchDataImg").show(); 
	var jsObj =
			{   
				constId : constituencyId,
				voterIDCardNo : voterCardNo
			}
	$.ajax({    
		type : "POST",
		url : "getVoterDetailsByVoterCardNumberAction.action",
		data : {task:JSON.stringify(jsObj)} ,   
	}).done(function(result){
		$("#searchDataImg").hide(); 
		if(result.subList != null && result.subList.length > 0){
			buildVoterDetails(result.subList);
		}else{
			$(".cadreMemberListCls").show();
			$('#cadreDetailsDiv').show();
			$('#cadreDetailsDiv').html("<span style='font-weight:bold;text-align:center;'> No Data Available...</span>");
		}
	});
}

function buildVoterDetails(result){
	$('#cadreDetailsDiv').show();
	var str = '';
	
	if(result != null){
		for(var i in result){
			str+='<div class="media " id="main'+result[i].voterId+'" attr_voterId='+result[i].voterId+' style="border:1px solid #ddd;padding:8px;background:#f8f8f8">';
			if(result[i].alreadyRegistered=="Already Registered" || result[i].alreadyRegistered == "Payment is Pending"){
					str+='<span href="#" class="media-left">';
					str+='<img style="width: 64px; height: 64px;" src="https://www.mytdp.com/images/cadre_images/'+result[i].image+'" />';
					str+='</span>';
				}
				str+='<div class="media-body">';
				str+='<div class="row">';
				str+='<div class="col-md-8">';
				str+='<ul class="list-inline" style="margin-bottom:0px">';
					str+='<li><b> Name:</b> '+result[i].voterName+'</li>';
					str+='<li><b> Relative Name: </b>'+result[i].relativeName+' </li>';
				str+='</ul>';
				str+='<ul class="list-inline"  style="margin-bottom:0px">';
				str+='<li>Age:'+result[i].age+'</li>';	
				str+='<li>Gender: '+result[i].gender+'</li>';
				if(result[i].mobileNo != null && result[i].mobileNo.length > 0)
					str+='<li>Mobile No:'+result[i].mobileNo+'</span></li>';
				str+='<li>Voter ID: '+result[i].voterIDCardNo+'</li>';
				str+='</ul>';
				str+='<ul class="list-inline"  style="margin-bottom:0px">';
				str+='<li>District: '+result[i].districtName+'</li>';
				str+='<li>Constituency: '+result[i].constituencyName+'</li>';
				if(result[i].tehsilId > 0)
					str+='<li>Mandal: '+result[i].tehsil+'</li>';
				else if(result[i].localElectionBodyId > 0)
					str+='<li>Muncipality: '+result[i].leb+'</li>';
				
				str+='</ul>';
				
				str+='</div>';
			
					if(result[i].alreadyRegistered != null && result[i].alreadyRegistered == "Already Registered"){
							str+='<div class="col-md-2">';
							str+='<span class="text-success pull-right" style="font-weight:bold;"> Already Registered </span>';
					}						
					else if(result[i].alreadyRegistered != null && result[i].alreadyRegistered == "Payment is Pending"){
							str+='<div class="col-md-4">';
						str+='<li class="text-warning " style="font-weight:bold;" >Payment is Pending';
						str+='  <button class=" detailsCls btn btn-warning btn-xs " id="main'+result[i].voterId+'" attr_voterId='+result[i].voterId+'>PAY NOW</button> </li>';
					}						
					else{
							str+='<div class="col-md-2">';
							str+='  <button class=" detailsCls btn btn-success btn-xs pull-right" id="main'+result[i].voterId+'" attr_voterId='+result[i].voterId+'>REGISTER</button>';
					}
						
					
				str+='</div>';
				str+='</div>';
				str+='</div>';
				str+='</div>';
			}
		}
		$(".cadreMemberListCls").show();
		$('#cadreDetailsDiv').html(str);
		$(".bgImage").css("background-image","none");
		$(".bgImage").css("background-color","#959595");
		$(".changeDiv").removeClass("col-md-7 col-md-offset-5 col-xs-12 col-sm-offset-4 col-sm-8")
		$(".changeDiv").addClass("col-md-12 col-xs-12 col-sm-12")
		$(".imgDiv").removeClass("col-md-3")
		$(".imgDiv").addClass("col-md-2")
}

$(document).on("click",".detailsCls",function(){
	var voterId = $(this).attr("attr_voterId");
	getDetailsForVoter(voterId);
});

function getDetailsForVoter(voterId){ 
	window.location.href = "affiliatedGraduatesRegistrationAction.action?candidateId="+voterId+"&searchType=voter&constiteucnyId=0&houseNo=0&boothId=0&panchayatId=0&tdpMemberTypeId=5";
}
		  
function getCadreDetailsBySearchCriteria(startIndex){
	$("#otpErr").html("");
	$("#generateOtpId").hide();
	$("#submitId").hide();
	$("#searchErrDiv").html("");
	$("#otpId").hide();
	$("#otpSpanId").hide();
	$("#otpId").val("");
	$("#cadreDetailsDiv").hide();
	$("#cadreDetailsDiv").html("");
	$("#nextStepId").hide();
	$("#success").hide();
	$("#fail").hide();
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
	
	var constituencyId = $('#constituencyId').val();
	/* if(constituencyId==0){
		$('#constErrId').html('Please Select Your Constituency.');
		return;
	} */
	if(searchRadioType == 'membershipId')   
	{
		memberShipCardNo = $('#searchBy').val().trim();
		
		if(searchBy.trim().length == 0 )
		{
			$('#searchErrDiv').html('Please Enter Membership Card No.');
			return;       
		}
		if(constituencyId==0){
			memberShipCardNo+="-HIDE";
		}
	}			
	if(searchRadioType == 'voterId')  
	{
		voterCardNo = $('#searchBy').val().trim();
		
		if(searchBy.trim().length == 0 )
		{
			$('#searchErrDiv').html('Please Enter Voter ID /Voter Card No.');
			return;
		}
		voterCardNo = voterCardNo +"-HIDE";
	}
	if(searchRadioType == 'mobileNo')
	{	
		mobileNo = $('#searchBy').val().trim();
		
		if(searchBy.trim().length == 0 )
		{
			$('#searchErrDiv').html('Please Enter Mobile No.');
			return;
		}
		
		if(searchRadioType=="mobileNo"){
				
				var numericExpression = /^[0-9]+$/;
				if(!$('#searchBy').val().match(numericExpression)){
					$('#searchErrDiv').html('Enter Numerics Only.');
					return;
				}
		}
		
		if(!(mobileNo.trim().length == 10 || mobileNo.trim().length == 12))
		{
			$('#searchErrDiv').html('Invalid Mobile No.');
			return;				
		}  
		
		var mobileNoLen = mobileNo.length;
		if(mobileNoLen==12 && mobileNo.substr(0,2)=="91"){
			mobileNo = mobileNo.substr(2,10);
		}else if(mobileNoLen==12 && mobileNo.substr(0,2)!="91"){
			$('#searchErrDiv').html('Invalid Mobile No.');
			return;
		}
		if(constituencyId==0){
			mobileNo+="-HIDE";
		}
	}
	
	$("#searchDataImg").show(); 
	if(constituencyId==0){
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
	}else{
		var jsObj =
		{  
			locationLevel :4,
			locationValue:constituencyId,
			mobileNo: mobileNo,  
			memberShipCardNo: memberShipCardNo,
			voterCardNo:voterCardNo,
			startIndex:startIndex,
			maxIndex : 50,
			removedStatus:false,  
			task:"tdpCadreSearch"     
		}
	}
	getCadreDetails(jsObj);
	
}

function buildCadreDetails(result,jsObj){
		$(".paginationDivId").show();
		var str ='';
		if(result != null)
		{
			for(var i in result)
			{  
				
				if(result[i].deletedStatus == "MD"){
					str+='<div class="media eachCadreMainDivCls" style="background: rgba(255, 0, 0, 0.1) none repeat scroll 0 0;padding: 5px;border-bottom: 1px solid rgb(51, 51, 51);" attr_cadre_id='+result[i].tdpCadreId+'>';
				}else{
					 str+='<div class="media" id="main'+result[i].tdpCadreId+'" style="border:1px solid #ddd;padding:8px;background:#f8f8f8" attr_cadre_id='+result[i].tdpCadreId+'>';
				}
				
				str+='<span href="#" class="media-left">';
				str+='<img style="width: 66px; height: 66px;border:1px solid #ddd" src="images/cadre_images/'+result[i].imageURL+'" />';
				str+='</span>';
				str+='<div class="media-body">';
				//ss
				str+='<div class="row">';
					str+='<div class="col-md-8">';
						str+='<ul class="list-inline" style="margin-bottom:0px">';
							str+='<li><b>Name</b>: '+result[i].cadreName+' </li>';
							str+='<li><b>Relative Name</b>: '+result[i].relativeName+' </li>'; 
							if(result[i].alreadyRegistered != null && result[i].alreadyRegistered == "Already Registered")
								str+='<li class="text-success pull-right" style="font-weight:bold;">Already Registered</li>';
							else if(result[i].alreadyRegistered != null && result[i].alreadyRegistered == "Payment is Pending")
								str+='<li class="text-warning pull-right" style="font-weight:bold;" >Payment is Pending</li>';
								
						str+='</ul>';
						str+='<ul class="list-inline" style="margin-bottom:0px">';
							str+='<li><b>Age</b>:'+result[i].age+'<li>';
							str+='<li><b>Gender</b>: '+result[i].gender+' </li>';
							str+='<li><b>Mobile No</b>: <span id="mobile'+result[i].tdpCadreId+'">'+result[i].mobileNo+'</span><input type="hidden" id="mobileNo'+result[i].tdpCadreId+'" value="'+result[i].mobileNo+'"/></li>';
						str+='</ul>';
						str+='<ul class="list-inline" style="margin-bottom:0px">';
							str+='<li><b>Voter ID</b>: '+result[i].voterCardNo+'</li>';
							if(result[i].deletedStatus == "MD"){
								str+='<li><b style="color:red;">Deleted Reason</b> : '+result[i].deletedReason+'</li>';
							}
							else{
								str+='<li id="delete'+result[i].tdpCadreId+'"></li>';
							}
						str+='</ul>';
					str+='</div>';
					if(result[i].alreadyRegistered != null && result[i].alreadyRegistered == "Already Registered"){
						str+='';
					}
					else if(result[i].alreadyRegistered != null && result[i].alreadyRegistered == "Payment is Pending"){
						str+='<div class="col-md-2">';
							str+='<div class="btn btn-warning  btn-xs">';
								str+='<label style="margin-bottom:0px;">';
									str+='<input type="checkbox" class="otpCheckboxCls  " style="color:red;" attr_cadreId="'+result[i].tdpCadreId+'" id="cadreCheckId0" value="'+result[i].tdpCadreId+'" name="otpMobileNo">&nbsp;PAY NOW';
								str+='</label>';
							str+='</div>';
						str+='</div>';
					}
					else{
						str+='<div class="col-md-2">';
							str+='<div class="btn btn-success  btn-xs">';
								str+='<label style="margin-bottom:0px;">';
									str+='<input type="checkbox" class="otpCheckboxCls " style="color:red;" attr_cadreId="'+result[i].tdpCadreId+'" id="cadreCheckId0" value="'+result[i].tdpCadreId+'" name="otpMobileNo">&nbsp;Select';
								str+='</label>';
							str+='</div>';
						str+='</div>';
					}
					
				str+='</div>';
				//ee
				/*if(result[i].alreadyRegistered != null && result[i].alreadyRegistered == "Already Registered")
					str+='<span class="text-success pull-right" style="font-weight:bold;margin-right: 250px;">  Already Registered </span>';
				else{
					str+='<div class="btn btn-success pull-right btn-xs">';
					str+='<label style="margin-bottom:0px;"><input type="checkbox" name="otpMobileNo"  attr_cadreId="'+result[i].tdpCadreId+'" value="'+result[i].tdpCadreId+'" id="cadreCheckId'+i+'" class="otpCheckboxCls color:red;"/>&nbsp;Select</label>';
					str+='</div>';
				}
				str+='<h5 class="media-heading"><div id="nameId" attr_cadreId="'+result[i].tdpCadreId+'"> <span style="font-weight:bold;"> Name:</span> '+result[i].cadreName+'</div> ';				
				str+='<span style="font-weight:bold;"> Relative Name: </span>'+result[i].relativeName+' </h5>';
				str+='<ul class="list-inline">';
				str+='<li><span style="font-weight:bold;">Age:</span>'+result[i].age+';</li>';
				str+='<li><span style="font-weight:bold;">Gender: </span>'+result[i].gender+'</span></li>';
				str+='<li><span style="font-weight:bold;">Mobile No:</span> <span id="mobile'+result[i].tdpCadreId+'">'+result[i].mobileNo+'</span><input type="hidden" id="mobileNo'+result[i].tdpCadreId+'" value="'+result[i].mobileNo+'"/></li><br>';
				//str+='<li><span style="font-weight:bold;">Caste: </span><span id="caste'+result[i].tdpCadreId+'">'+result[i].casteName+'</span></li>';
				str+='<li><span style="font-weight:bold;">Voter ID:</span> '+result[i].voterCardNo+'</li><br>';
				//str+='<li><span style="font-weight:bold;">MemberShipNo:</span> '+result[i].memberShipCardId+'</li>';
				//str+='<li><span style="font-weight:bold;">Registered Through:</span> '+result[i].dataSourceType+'</li>';
				if(result[i].deletedStatus == "MD"){
					str+='<li><b style="color:red;">Deleted Reason</b> : '+result[i].deletedReason+'</li>';
				}
				else{
					str+='<li id="delete'+result[i].tdpCadreId+'"></li>';
				}
				//str+='<li>Aadhar: '+result[i].imageURL+'</i>';
				str+='</ul>';
				
				str+='<div>';  
				
				str+='</div>';*/
				
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
			}
		
		}
		$(".cadreMemberListCls").show();
		$('#cadreDetailsDiv').html(str);
		$(".bgImage").css("background-image","none");
		$(".bgImage").css("background-color","#959595");
		$(".changeDiv").removeClass("col-md-7 col-md-offset-5 col-xs-12 col-sm-offset-4 col-sm-8")
		$(".changeDiv").addClass("col-md-12 col-xs-12 col-sm-12")
		$(".imgDiv").removeClass("col-md-3")
		$(".imgDiv").addClass("col-md-2")
}

function generateOTPForMobileNo(currentButton){
	
		$(currentButton).attr("disabled","disabled");
		$(currentButton).html("REGENERATE OTP");
		$("#otpErr").html("");
		$("#success").hide();
		$("#fail").hide();
		$("#otpId").val("");
		$("#otpSpanId").hide();
		$("#nextStepId").hide();   
		var memberShipNo = $('input[name="otpMobileNo"]:checked').val();
		var mobileNo = $('#mobileNo'+memberShipNo+'').val();
		$("#getOtpId").html("");
		if (typeof(mobileNo) != "undefined"){
			$("#getOtpId").html("");
			var refNo = Math.floor((Math.random() * 1000000) + 1);
			$("#randomRefNo").val(refNo);
			//var prevRefNo = $("#priviousRandomRefNo").val();
			$("#getOtpId").html("<span class='text-success'>Please wait OTP is Sending to your registered mobile no..</span>");
			var jsObj =
			{    
				mobileNo : mobileNo,
				refNo : refNo        
				//prevRefNo : prevRefNo
			}
			//console.log(mobileNo);
			generateOTPForMobileNumber(jsObj,currentButton);
		}else{
			$("#getOtpId").html("Please select atleast one member.");
		}
		//$("#priviousRandomRefNo").val(refNo);
	
}

function updateOTPDetails(result,currentButton){
	$(currentButton).removeAttr('disabled');
	$("#otpId").removeAttr('disabled');
	$("#submitId").removeAttr('disabled');
	if(result != null && result == "Success"){
			$("#getOtpId").html("<span class='text-success'> OTP sent successfully.</span>");
	}
	else{
		$("#getOtpId").html("<span class='text-danger'> Error occured Generate OTP once again.</span>");
	}
}
$("#otpId").keyup(function(){
	$("#fail,#success").hide();
	$("#otpErr").html("");
});
$(document).on("click","#submitId",function(){
	var otp=$("#otpId").val().trim();
	if(otp.length==0  || otp.length<6){
		$("#otpErr").html("Please Enter Six digit");
		return;
	}
	validateOTP(otp);
	
	
	
});
function validateOTP(otp){
	
	var memberShipNo = $('input[name="otpMobileNo"]:checked').val();
	var mobileNo = $('#mobileNo'+memberShipNo+'').val();
	var refNo = $("#randomRefNo").val();
	$("#fail,#success").hide();
	$("#otpErr").html("");
	//$("#validateOTPImg,").show();
	//alert(mobileNo+"::"+refNo+"::"+otp);
	var jsObj =
	{    
		mobileNo : mobileNo,
		refNo : refNo,
		otp : otp
	}
	validateOTPAction(jsObj);
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
	var cntMmbr = $("input.otpCheckboxCls:checked").length;
	if(cntMmbr==1){
		$("#generateOtpId").show();
		$("#generateOtpId").removeAttr('disabled');
		$("#otpId").removeAttr('disabled');
		$("#submitId").removeAttr('disabled');
		$("#submitId").show(); 
		$("#otpId").show();
		$("#otpSpanId").show();
		$("#otpId").val("");
	}
	if(cntMmbr==0){
		$("#generateOtpId").hide();
		$("#submitId").hide();
		$("#otpId").hide();
		$("#otpSpanId").hide();
		$("#otpId").val("");
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
	window.location.href = "affiliatedGraduatesRegistrationAction.action?candidateId="+caderId+"&searchType=cadre&constiteucnyId=0&houseNo=0&boothId=0&panchayatId=0&tdpMemberTypeId=5";
});


function startSearchingPage(){
	window.location.href="affiliatedCadreSearchAction.action";
}
$(document).on("change","#constituencyId",function(){
	$("#searchBy").val("");
	$(".cadreMemberListCls").hide();
	$('#cadreDetailsDiv').html("");
	$('#generateOtpId').hide();
	$('#submitId').hide();
	$('#otpId').hide();
	$('#otpSpanId').hide();
	$('#nextStepId').hide();
	$('#success').hide();
	$('#fail').hide();	
});
initializeNumber();
function initializeNumber(){
$(".onlyDigit").keydown(function (e) {
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
            (e.keyCode == 65 && ( e.ctrlKey === true || e.metaKey === true ) ) || 
            (e.keyCode >= 35 && e.keyCode <= 40)) {
                return;
        }
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
            e.preventDefault();
        }
});
}
function initializeTextNumber() {
$('.txtNumeric').keydown(function (e) {
if (e.shiftKey || e.ctrlKey || e.altKey) {
e.preventDefault();
} else {
var key = e.keyCode;
if (!((key == 8) || (key == 32) || (key == 46) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90) || (key >= 48 && key <= 57) || (key >= 96 && key <= 105))) {
e.preventDefault();
}
}
});
}