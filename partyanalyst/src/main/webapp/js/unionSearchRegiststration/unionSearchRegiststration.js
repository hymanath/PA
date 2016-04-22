
$(".searchCls").click(function(){
	$('#constErrId').html("");
	$("#errChkDivId").html("");
	$("#nextStepId").hide();
	$("#success").hide();
	$("#fail").hide();
	$(".cadreMemberListCls").hide();
	$("#cadreDetailsDiv").hide();
	$("#cadreDetailsDiv").html("");
	$(".paginationDivId").html('');
	$("#generateOtpId").hide();
	$("#otpId").hide();
	$("#otpId").val("");
	$("#getOtpId").html("");
	$('#searchErrDiv').html('');
	var choice = $(this).val();
	if(choice=="no"){
		$("#searchBy").attr("placeholder","Search By VoterCard Number");
		$(".inputChoice").hide();
		$("#searchBy").val("");
		$('#cadreDetailsDiv').html("");
	}else{
		$("#searchBy").attr("placeholder","Search By Membership Id/Mobile No/Voter Id");
		$(".inputChoice").show();
		$("#searchBy").val("");
	}
});
$(".searchTypeCls").click(function(){
	$('#constErrId').html("");
	$("#errChkDivId").html("");
	$("#success").hide();
	$("#fail").hide();
	$(".cadreMemberListCls").hide();
	$("#cadreDetailsDiv").html("");
	$("#generateOtpId").hide();
	$("#otpId").hide();
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

$(document).keypress(function(e) {
	if(e.keyCode==13){
		$('#constErrId').html("");
		var registeredChk = $('input[name="tdpCadreRadio"]:checked').val();
		if(registeredChk=="yes"){
			getCadreDetailsBySearchCriteria(0);
		}else{
			getVoterDetailsBySearch();
		}
	}
});

$("#searchId").click(function(){
	$('#getOtpId').html("");
	$('#constErrId').html("");
	$(".cadreMemberListCls").hide();
	$("#cadreDetailsDiv").hide();
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
	$(".cadreMemberListCls").hide();
	$('#searchErrDiv').html('');
	$('#cadreDetailsDiv').html('');
	var constituencyId = $('#constituencyId').val();
	if(constituencyId==0){
		$('#constErrId').html('Please Select Your Constituency.');
		return;
	}
	var voterCardNo = $("#searchBy").val();
	if($("#searchBy").val().length==0){
		$('#searchErrDiv').html('Please enter voterCard No.');
		return;
	}
	var jsObj =
	{   constId : constituencyId,
		voterIDCardNo : voterCardNo
	}
	
	$.ajax({    
		type : "POST",
		url : "getVoterDetailsByVoterCardNumberAction.action",
		data : {task:JSON.stringify(jsObj)} ,   
	}).done(function(result){
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
			str+='<div class="media " id="main'+result[i].voterId+'" attr_voterId='+result[i].voterId+' style="border-bottom: 1px solid rgb(51, 51, 51);cursor:pointer;">';
				//str+='<span href="#" class="media-left">';
				//str+='<img style="width: 64px; height: 64px;" src="images/Member_thamb_image.png" />';
				//str+='</span>';
				str+='<div class="media-body">';
				str+='<h5 class="media-heading"> <span style="font-weight:bold;"> Name:</span> '+result[i].voterName+'';				
				str+=' <span style="font-weight:bold;"> Relative Name: </span>'+result[i].relativeName+'  <button class=" detailsCls btn btn-success btn-xs pull-right" id="main'+result[i].voterId+'" attr_voterId='+result[i].voterId+'>REGISTER</button> </h5>';
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
		$(".cadreMemberListCls").show();
		$('#cadreDetailsDiv').html(str);
}

$(document).on("click",".detailsCls",function(){
	var voterId = $(this).attr("attr_voterId");
	getDetailsForVoter(voterId);
});

function getDetailsForVoter(voterId){ 
	window.open("affiliatedGraduatesRegistrationAction.action?candidateId="+voterId+"&searchType=voter&constiteucnyId=0&houseNo=0&boothId=0&panchayatId=0&tdpMemberTypeId=5");
}
		  
function getCadreDetailsBySearchCriteria(startIndex){
	$("#generateOtpId").hide();
	$("#searchErrDiv").html("");
	$("#otpId").hide();
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
	if(constituencyId==0){
		$('#constErrId').html('Please Select Your Constituency.');
		return;
	}
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
	}
	
	$("#searchDataImg").show();  

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
				str+='<li><span style="font-weight:bold;">Mobile No:</span> <span id="mobile'+result[i].tdpCadreId+'">'+result[i].mobileNo+'</span><input type="hidden" id="mobileNo'+result[i].tdpCadreId+'" value="'+result[i].mobileNo+'"/></li><br>';
				str+='<li><span style="font-weight:bold;">Caste: </span><span id="caste'+result[i].tdpCadreId+'">'+result[i].casteName+'</span></li>';
				str+='<li><span style="font-weight:bold;">Voter ID:</span> '+result[i].voterCardNo+'</li><br>';
				str+='<li><span style="font-weight:bold;">MemberShipNo:</span> '+result[i].memberShipCardId+'</li>';
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
			}
		
		}
		$(".cadreMemberListCls").show();
		$('#cadreDetailsDiv').html(str);
}

function generateOTPForMobileNo(currentButton){
	$(currentButton).attr("disabled","disabled");
	$("#success").hide();
	$("#fail").hide();
	$("#otpId").val("");
	$("#nextStepId").hide();
	var memberShipNo = $('input[name="otpMobileNo"]:checked').val();
	var mobileNo = $('#mobileNo'+memberShipNo+'').val();
	$("#getOtpId").html("");
	if (typeof(mobileNo) != "undefined"){
		$("#getOtpId").html("");
		var refNo = Math.floor((Math.random() * 1000000) + 1);
		$("#randomRefNo").val(refNo);
		if(refNo >0)
			$("#getOtpId").html(" OTP Reference No: "+refNo+" (Note: If you not get OTP, click Generate OTP once again.)");
		var jsObj =
		{    
			mobileNo : mobileNo,
			refNo : refNo
		}
		//console.log(mobileNo);
		generateOTPForMobileNumber(jsObj,currentButton);
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

	var memberShipNo = $('input[name="otpMobileNo"]:checked').val();
	var mobileNo = $('#mobileNo'+memberShipNo+'').val();
	var refNo = $("#randomRefNo").val();
	$("#fail,#success").hide();
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
		$("#otpId").show();
		$("#otpId").val("");
	}
	if(cntMmbr==0){
		$("#generateOtpId").hide();
		$("#otpId").hide();
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
	window.open("affiliatedGraduatesRegistrationAction.action?candidateId="+caderId+"&searchType=cadre&constiteucnyId=0&houseNo=0&boothId=0&panchayatId=0&tdpMemberTypeId=5");
});
