function getUserTypes(divId)
{
	var jsObj =
	{

	}
	$.ajax({
	type:'GET',
	url: 'getSurveyUserTypeAction.action',
	dataType: 'json',
	data: {task:JSON.stringify(jsObj)},
	}).done(function(result){
	$('#'+divId+'').find('option:not(:first)').remove();
	if(result != null && result.length > 0)
	{
	for(var i in result)
	{
	$('#'+divId+'').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
	}

	}

	});
}

function getSurveyUsersByUserTypeForLeaderAssign(divId)
{
	var value = $('#typeId').val();
	if(value == 3)
	{
		getRemeaningSurveyUsersByUserType(divId,1);
	}
	else
	{
		getRemeaningSurveyUsersByUserType(divId,4);
	}
	
}

function getRemeaningSurveyUsersByUserType(divId,value)
{
	var jsObj =
	{
	userTypeId :value,
	task : "getRemeaningSurveyUsersByUserType"
	}
	$.ajax({
	type:'GET',
	url: 'getNotAssignedSurveyUsersByUserTypeAction.action',
	dataType: 'json',
	data: {task:JSON.stringify(jsObj)},
	}).done(function(result){
	$('#'+divId+'').find('option:not(:first)').remove();
	if(result != null && result.length > 0)
	{
	for(var i in result)
	{
	$('#'+divId+'').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
	}

	}
	});
}

function getSurveyUsersByUserType(divId,value)
{
	$('#'+divId+'').html('<option value="0">Select User</option>');
	var jsObj =
	{
	userTypeId :value,
	task : "getSurveyUsersByUserType"
	}
	$.ajax({
	type:'GET',
	url: 'getSurveyUsersByUserTypeAction.action',
	dataType: 'json',
	data: {task:JSON.stringify(jsObj)},
	}).done(function(result){
	//$('#'+divId+'').append('<option value="0">Select User</option>');
	if(result != null && result.length > 0)
	{
	for(var i in result)
	{
	$('#'+divId+'').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
	}

	}
	/* else
	{
		$('#'+divId+'').html('<option value="0">Select User</option>');
	} */
	});
}


function saveSurveyUser()
{
	$('#processingImgForUserCreation').show();
	var	firstName =  $.trim($('#firstName').val());
	var	lastName =   $.trim($('#lastName').val());
	var	userName =   $.trim($('#userName').val());
	var	password =   $.trim($('#password').val());
	var address =   $.trim($('#address').val());
	var	mobileNo =   $.trim($('#mobileNumber').val());
	var	userType =  $('#userType').val();
	var retypePassword =  $.trim($('#retypePassword').val());
	var alphaExp = /^[a-zA-Z]+$/;
	
	if(firstName.length == 0)
	{
		$("#createUserErrorDiv").html("First Name is required").css("color","red");
		$('#processingImgForUserCreation').hide();
		return;
	}		
	if(firstName.length > 0) {
	if(!alphaExp.test(firstName))
	 {
		$("#createUserErrorDiv").html("First Name should not be Numeric").css("color","red");
		$('#processingImgForUserCreation').hide();
		return;
	 }
	 else if(firstName.length<4 || firstName.length>10)
	 {
		$("#createUserErrorDiv").html("First Name must be Minimum of 4 Characters and Maximum of 10 Characters").css("color","red");
		$('#processingImgForUserCreation').hide();
		return;
	 }
	  
	}
	if(lastName.length == 0)
	{
		$("#createUserErrorDiv").html("Last Name is required").css("color","red");
		$('#processingImgForUserCreation').hide();
		return;				
	}
	
	if(lastName.length > 0){ 
	if(!alphaExp.test(lastName))
	{
		$("#createUserErrorDiv").html("last Name should not be Numeric").css("color","red");
		$('#processingImgForUserCreation').hide();
		return;
	}
	
	else if(lastName.length <2 || lastName.length>10)
	{
		$("#createUserErrorDiv").html("Last Name must be Minimum of 2 Characters and Maximum of 10 Characters").css("color","red");
		$('#processingImgForUserCreation').hide();
		return;
	}
	}
	if(userName.length == 0)
	{
		$("#createUserErrorDiv").html("User Name is required").css("color","red");
		$('#processingImgForUserCreation').hide();
		return;
	}
	if(userName.length > 10 || userName.length<4)
	{
		$("#createUserErrorDiv").html("User Name must be Minimum of 4 Characters and Maximum of 10 Characters").css("color","red");
		$('#processingImgForUserCreation').hide();
		return;
	}
	if(address.length == 0)
	{
		$("#createUserErrorDiv").html("Address is required").css("color","red");
		$('#processingImgForUserCreation').hide();
		return;
	
	}
	if(address.length > 0 && address.length<10)
	{
		$("#createUserErrorDiv").html("Address must be Minimum Of 10 Characters").css("color","red");
		$('#processingImgForUserCreation').hide();
		return;
	
	}
	if(password.length == 0)
	{
		$("#createUserErrorDiv").html("Password is required").css("color","red");
		$('#processingImgForUserCreation').hide();
		return;
	}
	if(password.length > 0 && password.length < 6)
	{
		$("#createUserErrorDiv").html("Password must be Minimum Of 6 Characters").css("color","red");
		$('#processingImgForUserCreation').hide();
		return;
	}
	if(retypePassword.length == 0)
	{
		$("#createUserErrorDiv").html("Retype Password is required").css("color","red");
		$('#processingImgForUserCreation').hide();
		return;
	}
	 if(password.length > 0 && retypePassword.length > 0 && password != retypePassword)
	{
 		$("#createUserErrorDiv").html("Passwords donot match").css("color","red");
		$('#processingImgForUserCreation').hide();
       return
	}
	if(mobileNo.length == 0)
	{
		$("#createUserErrorDiv").html("Mobile Number is required").css("color","red");
		$('#processingImgForUserCreation').hide();
		return;
	}
	else if(isNaN(mobileNo) || mobileNo.length != 10)
	{
		$("#createUserErrorDiv").html("Enter Valid Mobile Number").css("color","red");
		$('#processingImgForUserCreation').hide();
		return;
	}
	
	if(userType == 0)
	{
		$("#createUserErrorDiv").html("UserType is required").css("color","red");
		$('#processingImgForUserCreation').hide();
		return;
	
	}
	var jsObj = 
	{
		firstName : firstName,
		lastName :  lastName,
		userName : userName,
		password :password,
		address :  address,
		mobileNo : mobileNo,
		userType : userType,
		task : "saveSurveyUser"
	}
	
	$.ajax({
		type:'GET',
		url: 'saveSurveyUserAction.action',
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		}).done(function(result){
			if(result.resultCode == 0) 
			{
				$("#createUserErrorDiv").html('User Created successfully.').css("color","green");
				setTimeout(function(){$('#createUserErrorDiv').html('');}, 3000);
				$('#mobileNo,#address,#password,#userName,#lastName,#firstName,#retypePassword').val('');
				$('#userType').val(0);
			}
			else
			{
				$("#createUserErrorDiv").html('Error Occured,Try again....').css("color","red");
			}
		    $('#processingImgForUserCreation').hide();	
		});

}


function saveSurveyUserType()
{
	$('#processingImgForUserType').show();
	var description = $.trim($('#userTypeDescription').val());
	var userTypeName = $.trim($('#userTypeName').val());
	var alphaExp = /^[a-zA-Z]+$/;
	if(userTypeName.length == 0)
	{
		$("#createUserTypeErrorDiv").html("Please Enter User Type ").css("color","red");
		$('#processingImgForUserType').hide();
		return;
	}
	if(!alphaExp.test(userTypeName))
	{
		$("#createUserTypeErrorDiv").html("Please Enter Valid User Type ").css("color","red");
		$('#processingImgForUserType').hide();
		return;
	} 
	
	var jsObj = 
	{
		description : description,
		userType : userTypeName,
		task : "saveSurveyUserType"
	}
	
	$.ajax({
		type:'GET',
		url: 'saveSurveyUserTypeAction.action',
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		}).done(function(result){
			if(result.resultCode == 0) 
			{
				$("#createUserTypeErrorDiv").html('User Type created successfully.').css("color","green");
				setTimeout(function(){$('#createUserTypeErrorDiv').html('');}, 3000);
				$('#userTypeDescription').val('');
				$('#userTypeName').val('');
			}
			else if(result.resultCode == 4)
			{
				$("#createUserTypeErrorDiv").html('User Type Already Exists ...').css("color","red");
			}
			else
			{
				$("#createUserTypeErrorDiv").html('Error Occured,Try again....').css("color","red");
			}
			$('#processingImgForUserType').hide();
		});
}


function AssignTab()
{
	$("#assignTabErrorDiv").html('');
	$("#processingImgForTabAssign").show();
	//var uname = $.trim($("#uname").val());
	var surveyUser = $("#surveyUserIdForSelect").val();
	var tabNo = $.trim($("#tabNo").val());
	var remarks = $.trim($("#remarks").val());
	var date = $("#date").val();
	var str = ''; 
	if(surveyUser == 0)
	{
		str +='Select User<br/>';
	}
	
	if(tabNo.length == 0)
	{
		str +='Tab No is required <br/>';
	}
	/* if(remarks.length == 0)
	{
		str +='Remarks is required <br/>';
	} */
	if(date.length == 0)
	{
		str +='Date is required <br/>';
	}
	if(str != '')
	{
	$("#assignTabErrorDiv").html(str).css("color","red");
	$("#processingImgForTabAssign").hide();
	return;
	}
	else
$("#assignTabErrorDiv").html('');
	var jObj = {
		surveyUserId : surveyUser,
		tabNo :tabNo,
		remarks : remarks,
		date : date
			
	}
	$.ajax({
          type:'POST',
          url: 'assignTabAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(jObj)},
     	  }).done(function(result){ 
			if(result.resultCode == 0) 
				$("#assignTabErrorDiv").html('Tab assigned successfully.').css("color","green");
			else
			  {
				$("#assignTabErrorDiv").html('Error Occured,Try again....').css("color","red");
			  }
			  $("#processingImgForTabAssign").hide();
	   });
	
}

function showHideTabs(id)
{


if(id == "userTypeTab")
	{
	$(".errorCls").html('');
	$("#createUserTypeDiv").show();
	$("#userCreationDiv").hide();
	$("#tabAssignDiv").hide();
	$("#boothAssigniv").hide();
	$("#userDeactivationDiv").hide();
	$("#assignBoothToLeaderDiv").hide();
	$("#verificationDiv").hide();
	$("#leaderNameDiv").hide();
	
	}

else if(id == "userCreationTab")
	{
	$(".errorCls").html('');
	$("#createUserTypeDiv").hide();
	$("#userCreationDiv").show();
	$("#tabAssignDiv").hide();
	$("#boothAssigniv").hide();
	$("#userDeactivationDiv").hide();
	$("#assignBoothToLeaderDiv").hide();
	$("#verificationDiv").hide();
	$("#leaderNameDiv").hide();
	getUserTypes('userType');
	}

	else if(id == "tabAssignTab")
	{
	$(".errorCls").html('');
	$("#createUserTypeDiv").hide();
	$("#userCreationDiv").hide();
	$("#tabAssignDiv").show();
	$("#boothAssigniv").hide();
	$("#userDeactivationDiv").hide();
	$("#assignBoothToLeaderDiv").hide();
	$("#verificationDiv").hide();
	$("#leaderNameDiv").hide();
	buildDatePicker();
	//getUserTypes('surveyUserTypeForSelect');
	}

	else if(id == "boothAssignTab")
	{
	$(".errorCls").html('');
	$("#createUserTypeDiv").hide();
	$("#userCreationDiv").hide();
	$("#tabAssignDiv").hide();
	$("#boothAssigniv").show();
	$("#userDeactivationDiv").hide();
	$("#assignBoothToLeaderDiv").hide();
	$("#verificationDiv").hide();
	$("#leaderNameDiv").hide();
	//getUserTypes('boothAssignUserType');
	}
	
	else if(id == "deactivationTab")
	{
	$(".errorCls").html('');
	$("#createUserTypeDiv").hide();
	$("#userCreationDiv").hide();
	$("#tabAssignDiv").hide();
	$("#boothAssigniv").hide();
	$("#userDeactivationDiv").show();
	$("#assignBoothToLeaderDiv").hide();
	$("#verificationDiv").hide();
	$("#leaderNameDiv").hide();
	getUserTypes('deactiveUserTypeId');
	}

	
		else if(id == "assignLeaderTab")
	{
	$(".errorCls").html('');
	$("#createUserTypeDiv").hide();
	$("#userCreationDiv").hide();
	$("#tabAssignDiv").hide();
	$("#boothAssigniv").hide();
	$("#userDeactivationDiv").hide();
	$("#assignBoothToLeaderDiv").show();
	$("#verificationDiv").hide();
	$("#leaderNameDiv").hide();
	//getUserTypes('typeId');
	
	}
		else if(id == "verificationDetailsTab")
	{
	$(".errorCls").html('');
	$("#createUserTypeDiv").hide();
	$("#userCreationDiv").hide();
	$("#tabAssignDiv").hide();
	$("#boothAssigniv").hide();
	$("#userDeactivationDiv").hide();
	$("#assignBoothToLeaderDiv").hide();
	$("#verificationDiv").show();
	$("#leaderNameDiv").show();
	
	getLeaderDetetilsByContituencyWise();

	}



}
function buildDatePicker()
{
	
	$("#date").datepicker({ 
	dateFormat: 'dd-mm-yy',
	
	onSelect: function(dateText, inst) {
	}
   }).datepicker('setDate', new Date());

}

function assignBooth()
{
	var	user =  $("#userId").val();
	var	constituencyId = $("#constituencyId").val();
	var	panchayatId =  $("#panchayatId").val();
	var	boothId =  $('#boothId').val();

	if(user == 0)
	{
		$("#assignBoothErrorDiv").html("Please Select The User").css("color","red");
		return;
	}
	if(constituencyId == 0)
	{
		$("#assignBoothErrorDiv").html("Please Select Constituency").css("color","red");
		return;
	}
	if(panchayatId == 0)
	{
		$("#assignBoothErrorDiv").html("Please Select Panchayat").css("color","red");
		return;
	}
	if(boothId == 0)
	{
		$("#assignBoothErrorDiv").html("Please Select Booth").css("color","red");
		return;
	}
	var jsObj = 
	{
		user : user,
		constituencyId :  constituencyId,
		panchayatId : panchayatId,
		boothId :  boothId,
		task : "assignBooth"
	}
	
	$.ajax({
		type:'GET',
		url: 'assignBoothAction.action',
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		}).done(function(result){
			if(result.resultCode == 0) 
			{
				$("#assignBoothErrorDiv").html('Booth Assigned successfully.').css("color","green");
				setTimeout(function(){$('#assignBoothErrorDiv').html('');}, 3000);				
				$('#userId,#boothId,#panchayatId,#constituencyId').val(0);
			}
			else
			{
				$("#assignBoothErrorDiv").html('Error Occured,Try again....').css("color","red");
			}
		
		});

}
function assignLeaderToUser()
{
	$('#processingImgForAssignLeader').show();
	var	typeId =  $("#typeId").val();
	var	constituencyLeaderId = $("#constituencyLeaderId").val();
	var	leaderId =  $("#leaderId").val();
	var	userLeaderId =  $('#userLeaderId').val();
	var userIds = new Array();
	userIds.push(userLeaderId);
	if(constituencyLeaderId == 0)
	{
		$("#assignLeaderErrorDiv").html("Please Select Constituency").css("color","red");
		$('#processingImgForAssignLeader').hide();
		return;
	}
	if(typeId == 0)
	{
		$("#assignLeaderErrorDiv").html("Please Select the Type").css("color","red");
		$('#processingImgForAssignLeader').hide();
		return;
	}
	if(leaderId == 0)
	{
		$("#assignLeaderErrorDiv").html("Please Select Leader").css("color","red");
		$('#processingImgForAssignLeader').hide();
		return;
	}
	if(userLeaderId == 0)
	{
		$("#assignLeaderErrorDiv").html("Please Select User").css("color","red");
		$('#processingImgForAssignLeader').hide();
		return;
	}
	var jsObj = 
	{
		constituencyId : constituencyLeaderId,
		userTypeId :  typeId,
		leaderId : leaderId,
		userIds :  userIds,
		task : "assignLeader"
	}
	
	$.ajax({
		type:'GET',
		url: 'saveServeyUserRelationDetailsAction.action',
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		}).done(function(result){
			if(result.resultCode == 0) 
			{
				$("#assignLeaderErrorDiv").html('Leader Assigned successfully.').css("color","green");
				setTimeout(function(){$('#assignLeaderErrorDiv').html('');}, 3000);				
				$('#userLeaderId,#leaderId,#typeId,#constituencyLeaderId').val(0);
			}
			else
			{
				$("#assignLeaderErrorDiv").html('Error Occured,Try again....').css("color","red");
			}		
			$('#processingImgForAssignLeader').hide();
		});

}

function deactivateUser()
{
	$('#processingImgForDeactivation').show();
	var	deactivateUserId =  $("#deactivateUserId").val();
	var	remarksId = $.trim($("#remarksId").val());

	if(deactivateUserId == 0)
	{
		$("#deactivateUserErrorDiv").html("Please Select The User").css("color","red");
		$('#processingImgForDeactivation').hide();
		return;
	}
	if(remarksId.length == 0)
	{
		$("#deactivateUserErrorDiv").html("Remarks is Required").css("color","red");
		$('#processingImgForDeactivation').hide();
		return;
	}
	
	var jsObj = 
	{
		userId : deactivateUserId,
		remarks :  remarksId,
		
		task : "deactivateUser"
	}
	
	$.ajax({
		type:'GET',
		url: 'deactiveSurveyUserAction.action',
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		}).done(function(result){
			
			if(result.resultCode == 0) 
			{
				$("#deactivateUserErrorDiv").html('Deactivated Usersuccessfully.').css("color","green");
				setTimeout(function(){$('#deactivateUserErrorDiv').html('');}, 3000);				
				$('#deactivateUserId').val(0);
				$('#remarksId').val('');
			}
			else
			{
				$("#deactivateUserErrorDiv").html('Error Occured,Try again....').css("color","red");
			}	
			$('#processingImgForDeactivation').hide();
		});

}

function getLeaderDetetilsByContituencyWise()
{
	var jsObj = 
	{
	}
	
	$.ajax({
		type:'GET',
		url: 'getSurveyLeadersAction.action',
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		}).done(function(result){
			if(result != null && result.length > 0)
			{	
				var str = '';
			
				str += '<div class="span10 offset1">';
				str += '<div class="row-fluid">';
				str += '<div class="span12 widgetservey_Red m_top20">';
				str += '<h4>User Verification Details</h4>';
				str += '<div class="row">';
				str += '<div class="span8 offset2">';
				str += '<table class="table table-bordered">';
				str += '<thead>';
				str += '<tr>	';									  
				str += ' <th>CONSTITUENCY NAME</th>';
				str += '<th>LEADER NAME</th>	';									  
				str += '</tr>';
				str += ' </thead>';
				str += '<tbody>	';			
				for(var i in result)
				{
					str += '<tr>		';								  
					str += '<td>'+result[i].desc+'</td>';
					str += '<td><a style = "cursor: pointer;" onClick="getBoothDetailsForSelectedUser('+result[i].id+','+result[i].rank+')">'+result[i].name+'</a></td>';
					str += '</tr>	';									
				}
				
				str += '</tbody>';
				str += '</table>';
				str += '</div>';
				str += '</div>';
				str += '</div>';
				str += '</div>';
			    str += '</div>';
				
				$('#verificationDiv').html(str);
			}			
		});
}

function getBoothDetailsForSelectedUser(leaderId,constituencyId)
{
	var jsObj = 
	{
		leaderId : leaderId,
		constituencyId : constituencyId,
		task : "getBoothDetailsForSelectedUser"
	}
	
	$.ajax({
		type:'GET',
		url: 'getSurveyUsersByLeadersAction.action',
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		}).done(function(result){
			if(result != null && result.length > 0)
			{	
				var str = '';
				str += '<div class="span10 offset1">';
				str += '<div class="row-fluid">';
				str += '<div class="span12 widgetservey m_top20">';
				str += '<h4>LEADER NAME</h4>';
				str += '<div class="row">';
				str += '<div class="span8 offset2">';
				str += '<table class="table table-bordered">';
				str += '<thead>';
				str += '<tr>	';									  
				str += '<th>USER NAMES</th>';
				str += ' <th>ASSIGNED BOOTH NUMBERS</th>	';									  
				str += '</tr>';
				str += '</thead>';
				str += '<tbody>	';	
				for(var i in result)	
				{
					str += '<tr>';								  
					str += '<td>'+result[i].name+'</td>';
					str += '<td>';
					var length = result[i].genericVOList.length;
					for(var j in result[i].genericVOList)
					{
						
						if(j == length-1)
						{
							str += '<a style="cursor: pointer;">'+result[i].genericVOList[j].rank+' </a>';
						}
						else
						{
							str += '<a style="cursor: pointer;">'+result[i].genericVOList[j].rank+' , </a>';
						}
					}
					str += ' </td>';
					str += '</tr>';	
				}			
											
				str += ' </tbody>';
				str += '</table>';
				str += '</div>';
				str += '</div>';
				str += '</div>';
				str += '</div>';
			    str += '</div>';
				$('#leaderNameDiv').html(str);
			}			
		});
}

function getUserAssignedBoothsDetailsForAConstituency()
{
	var jObj =
	{
	 constituencyId:$('#constituencyId').val(),
	 userId:$('#userId').val()
	}
	$.ajax({
			type:'GET',
			url: 'getAssignedBoothsDetailsByConstituencyIdAndUserId.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		  }).done(function(result){
				buildBoothDetails(result);
		});
}

function buildBoothDetails(result)
{
	var str = '';

	str+='<div class="span12 offset1">';
    str+='<br/><br/>';
	 $.each(result,function(index,value){
		 if(value.userHas == false)
		   str+='<div class="span2"><label><input type="checkbox" value="'+value.boothId+'" class="boothChckbox" style="margin:0px;"/>  Booth - '+value.partNo+'</label></div>';
		 else
		  str+='<div class="span2"><label><input type="checkbox" checked value="'+value.boothId+'" class="boothChckbox"/>  Booth - '+value.partNo+'</label></div>';

	 });
	str+='</div>';
	
	$('#boothsDtlsId').html(str);
}
function saveUserAssignedBoothsDetails()
{	
	var boothAssignUserType= $("#boothAssignUserType").val();
	var  surveyUserId = $('#userId').val();
	var constituencyId = $('#constituencyId').val();
	if(boothAssignUserType == 0)
	{
		$("#assignBoothErrorDiv").html("Please Select User Type").css("color","red");
		return;	
	}
	if(surveyUserId == 0)
	{
		$("#assignBoothErrorDiv").html("Please Select User Name").css("color","red");
		return;	
	}
	if(constituencyId == 0)
	{
		$("#assignBoothErrorDiv").html("Please Select Constituency").css("color","red");
		return;	
	}
	$('#processingImgForBoothAssign').show();
	
	var jObj =
	{
	  boothIds:[],
	  constituencyId:$('#constituencyId').val(),
	  surveyUserId:$('#userId').val(),
	  remainingDataBooths:''
	}

	if($('#remainingData').is(":checked"))
		jObj.remainingDataBooths = 'true';
	else
		jObj.remainingDataBooths = 'false';

	$('.boothChckbox').each(function(index,value){
		if(this.checked)
			jObj.boothIds.push(this.value);
	}); 

	if(jObj.boothIds.length == 0)
	{
		$("#assignBoothErrorDiv").html("Please Select Booth(s)").css("color","red");
		 $('html, body').animate({ scrollTop: $("#assignBoothErrorDiv").offset().top }, "slow");
		return;	
	}
		
	$("#assignboothimg").css("display","inline-block");
	$.ajax({
			type:'GET',
			url: 'saveUserAssignedBoothsDetails.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		  }).done(function(result){
				$("#assignboothimg").css("display","none");
				if(result.resultCode == 0)
				{
				$("#assignBoothErrorDiv").html("Booth assigned successfully...").css("color","green");
				 $('html, body').animate({ scrollTop: $("#assignBoothErrorDiv").offset().top }, "slow");
				}
			});

}

function getSurveyUsersByUserTypeForLeaderRelease()
{
	var jObj =
	{
	 leaderId:$('#leaderIdForRelease').val()
	}
	$.ajax({
			type:'GET',
			url: 'releaseLeadersWithUserAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		  }).done(function(result){
				$('#userLeaderIdForRelease').find('option:not(:first)').remove();
				if(result != null && result.length > 0)
				{
				$('#constituencyLeaderIdForRelease').append('<option value="'+result[0].rank+'">'+result[0].desc+'</option>');
				for(var i in result)
				{
				$('#userLeaderIdForRelease').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
				
				}

				}							
		});
}

function updateLeaderUserReleation()
{
	$('#processingImgForReleaseLeader').show();
	var	typeId =  $("#typeIdForRelease").val();
	var	constituencyLeaderId = $("#constituencyLeaderIdForRelease").val();
	var	leaderId =  $("#leaderIdForRelease").val();
	var	userLeaderId =  $('#userLeaderIdForRelease').val();
	var userIds = new Array();
	userIds.push(userLeaderId);
	if(constituencyLeaderId == 0)
	{
		$("#assignLeaderErrorDivForRelease").html("Please Select Constituency").css("color","red");
		$('#processingImgForReleaseLeader').hide();
		return;
	}
	if(typeId == 0)
	{
		$("#assignLeaderErrorDivForRelease").html("Please Select the Type").css("color","red");
		$('#processingImgForReleaseLeader').hide();
		return;
	}
	if(leaderId == 0)
	{
		$("#assignLeaderErrorDivForRelease").html("Please Select Leader").css("color","red");
		$('#processingImgForReleaseLeader').hide();
		return;
	}
	if(userLeaderId == 0)
	{
		$("#assignLeaderErrorDivForRelease").html("Please Select User").css("color","red");
		$('#processingImgForReleaseLeader').hide();
		return;
	}
	var jsObj = 
	{
		constituencyId : constituencyLeaderId,
		userTypeId :  typeId,
		leaderId : leaderId,
		userIds :  userIds,
		task : "assignLeader"
	}
	
	$.ajax({
		type:'GET',
		url: 'updateServeyUserRelationDetailsAction.action',
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		}).done(function(result){
			if(result.resultCode == 0) 
			{
				$("#assignLeaderErrorDivForRelease").html('Users Released successfully.').css("color","green");
				setTimeout(function(){$('#assignLeaderErrorDivForRelease').html('');}, 3000);				
				$('#userLeaderId,#leaderId,#typeId,#constituencyLeaderId').val(0);
			}
			else
			{
				$("#assignLeaderErrorDivForRelease").html('Error Occured,Try again....').css("color","red");
			}	
			$('#processingImgForReleaseLeader').hide();			
		});
}

function redicttoVerificationPage()
{

	 window.open('dailyVerificationReportsAction.action','_blank');
	
}

function getAssignedConstituencyUsers(divId,value)
{
	
	var jsObj =
	{
	userTypeId :value,
	task : "getAssignedUsers"
	}
	$.ajax({
	type:'GET',
	url: 'getAssignedConstituencyUsersAction.action',
	dataType: 'json',
	data: {task:JSON.stringify(jsObj)},
	}).done(function(result){
	$('#'+divId+'').find('option:not(:first)').remove();
	if(result != null && result.length > 0)
	{
	for(var i in result)
	{
		$('#'+divId+'').append('<option value="'+result[i].userid+'">'+result[i].userName+'</option>');
	}

	}
	});
}


function getAssignedConstituencies(divId)
{
	var jsObj =
	{
	task: "getAssignedConstituencies"
	}
	$.ajax({
	type:'GET',
	url: 'getAssignedConstituenciesAction.action',
	dataType: 'json',
	data: {task:JSON.stringify(jsObj)},
	}).done(function(result){	
	$('#'+divId+'').find('option:not(:first)').remove();
	if(result != null && result.length > 0)
	{
	for(var i in result)
	{
	$('#'+divId+'').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
	}

	}

	});
}


function assignConstituencyToUser()
{
	$('#processingImgForAssignConsti').show();
	var constiUserTypeId = $("#constiUserTypeId").val();
	var	userId = $("#assignConstituencyLeaderId").val();
	var	constituencyId =  $('#assignConstituencyId').val();
	
	if(constiUserTypeId == 0)
	{
		$("#assignconstiErrorDiv").html("Please Select User Type").css("color","red");
		$('#processingImgForAssignConsti').hide();
		return;
	}
	if(userId == 0)
	{
		$("#assignconstiErrorDiv").html("Please Select User").css("color","red");
		$('#processingImgForAssignConsti').hide();
		return;
	}
	if(constituencyId == 0)
	{
		$("#assignconstiErrorDiv").html("Please Select Constituency").css("color","red");
		$('#processingImgForAssignConsti').hide();
		return;
	}

	var jsObj = 
	{
		userId : userId,
		constituencyId :  constituencyId,
		task : "assignConstituency"
	}
	
	$.ajax({
		type:'GET',
		url: 'assignConstituencyToUserAction.action',
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		}).done(function(result){
			if(result.resultCode == 0) 
			{
				$("#assignconstiErrorDiv").html('Constituency Assigned successfully.').css("color","green");
				setTimeout(function(){$('#assignconstiErrorDiv').html('');}, 3000);				
				$('#constiUserTypeId,#assignConstituencyLeaderId,#assignConstituencyId').val(0);
			}
			else
			{
				$("#assignconstiErrorDiv").html('Error Occured,Try again....').css("color","red");
			}		
			$('#processingImgForAssignConsti').hide();
		});

}
