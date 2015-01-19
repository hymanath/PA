
	function showAndHideTabs(searchType)
	{
		$('#basicCommitteeTab, #publicrepresantativeTab, #mandalaffiliatedTab').removeClass('arrow_selected');
		if(searchType == 'basicCommitteeDiv')
		{
			$('#'+searchType+'').show();
			$('#committeeMngtType').val(1);
			$('#publicrepresantative').hide();
			$('#mandalaffiliated').hide();			
			$('#basicCommitteeTab').addClass('arrow_selected');
		}
		
		else if(searchType == 'publicrepresantative')
		{				
			$('#basicCommitteeDiv').hide();
			$('#committeeMngtType').val(2);
			$('#'+searchType+'').show();
			$('#mandalaffiliated').hide();
			$('#publicrepresantativeTab').addClass('arrow_selected');
		}
		else if(searchType == 'mandalaffiliated')
		{				
			$('#committeeMngtType').val(3);
			$('#publicrepresantative').hide();
			$('#basicCommitteeDiv').hide();
			$('#'+searchType+'').show();
			$('#mandalaffiliatedTab').addClass('arrow_selected');
		}
		
	}
	
	function validateSearchType(areaTypeId)
	{
		if(areaTypeId == 1) //  Village / Ward / Division
		{
			$('#areaTypeId').val(areaTypeId);
		}
		
		else if(areaTypeId == 2) // Mandal / Town / GHMC 
		{				
			$('#areaTypeId').val(areaTypeId);
		}
	}
	
	function getCommiteeDetailsForCommittee()
	{
		var committeeLevelId = 	$('#areaTypeId').val(); //  Village / Ward / Division (or) Mandal / Town / GHMC 
		var committeeTypeId = 	$('#committeeTypeId').val(); // main committee (or) affiliated committee
		var committeeId = 	$('#committeeId').val();
		
		var jsObj = {
			committeeLevelId : committeeLevelId,
			committeeTypeId : committeeTypeId,
			committeeId : committeeId
		}
		
		 $.ajax({
				type : "POST",
				url : "getStatewiseDetailsAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
			});
			
	}
	
	function getCadreDetailsBySearchCriteria()
	{
		var areaTypeId  =  $('#areaTypeId').val();
		var committeeLocationId =$("#committeeLocationId").val();

		var locationLevel = 0;
		var locationValue = '';
		var searchName = '';
		var mobileNo = '';
		var casteCategory = '';
		var casteStateId = 0;
		var fromAge = 0;
		var toAge = 0;
		var memberShipCardNo = '';
		var trNumber = '';
		var voterCardNo = '';
		var gender = '';
		var houseNo = '';
		$('#cadreDetailsDiv').html('');
		if(areaTypeId ==1)
		{
			if(committeeLocationId.substr(0,1) == 1){
				  locationLevel = 6;
			}
			else if(committeeLocationId.substr(0,1) == 2){
				 locationLevel = 8;
			}
		}
		if(areaTypeId ==2)
		{
			if(committeeLocationId.substr(0,1) == 1){
				 locationLevel = 7;
			}
			else if(committeeLocationId.substr(0,1) == 2){
				 locationLevel = 5;
			}					
		}
		var locationValue = committeeLocationId.substr(1);
		
		var searchRadioType = $('#cadreSearchType').val();
		
		if(searchRadioType == 'membershipId')
		{
			memberShipCardNo = $('#searchBy').val().trim();
		}			
		if(searchRadioType == 'voterId')
		{
			voterCardNo = $('#searchBy').val().trim();
		}
		if(searchRadioType == 'mobileNo')
		{
			mobileNo = $('#searchBy').val().trim();
		}
		if(searchRadioType == 'name')
		{
			searchName = $('#searchBy').val().trim();
		}
		if(searchRadioType == 'advancedSearch')
		{
			gender = $('#gender option:selected').text().trim();
			var ageRange = $('#ageRange option:selected').text();
			var ageRange = ageRange.split('-');
			fromAge = ageRange[0].trim();
			toAge = ageRange[1].trim();		
			casteCategory = $('#casteCategory option:selected').text().trim();
			casteStateId = $('#casteList').val().trim();
			
		}
		
		
		var jsObj =
		{
			locationLevel :locationLevel,
			locationValue:locationValue,
			searchName : searchName,
			mobileNo: mobileNo,
			casteCategory : casteCategory,
			fromAge : fromAge,
			toAge : toAge,
			memberShipCardNo: memberShipCardNo,
			casteStateId : casteStateId,
			trNumber : trNumber,
			voterCardNo:voterCardNo,
			gender:gender,
			houseNo:houseNo
		}
		 $.ajax({
				type : "POST",
				url : "getCadreSearchDetailsAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				if(result != null && result.tdpCadreDetailsList != null && result.tdpCadreDetailsList.length>0)
				{
					buildCadreDetails(result.tdpCadreDetailsList);
				}
				else
				{
					console.log("no data available...");
				}
			});  
	}
	
	function buildCadreDetails(result)
	{
		var str ='';
		
		if(result != null)
		{
			for(var i in result)
			{			
				str+='<div class="media">';
				str+='<a href="#" class="media-left">';
				str+='<img style="width: 64px; height: 64px;" src="http://www.mytdp.com/images/cadre_images/'+result[i].imageURL+'" />';
				str+='</a>';
				str+='<div class="media-body">';
				str+='<h4 class="media-heading">'+result[i].cadreName+'</h4>';
				str+='<ul class="list-inline">';
				str+='<li>Age:33;</i>';
				str+='<li>Gender: '+result[i].gender+'</i>';
				str+='<li>Mobile No: '+result[i].mobileNo+'</i>';
				str+='<li>Caste: '+result[i].casteName+'</i>';
				str+='<li>Voter ID: '+result[i].voterCardNo+'</i>';
				//str+='<li>Aadhar: '+result[i].imageURL+'</i>';
				str+='</ul>';
				str+='</div>';
				str+='</div>';
				str+='<div class="form-inline ">';
				str+='<label><input type="radio" name="searchedDetails"> &nbsp;&nbsp;</label>';
				str+='<a target="_blank" href="cadreProfileDetailsAction.action?tdpCadreId='+result[i].id+'&task='+$('#areaTypeId').val()+'&committeeMngtType='+$('#committeeMngtType').val()+;" class="btn btn-success btn-medium">UPDATE PROFILE</a>';
				str+='</div>	';
			}
		}
		
		$('#cadreDetailsDiv').html(str);
	}
	
	function addMoreRolesForCadre()
	{
	
		str+='<div class="well well-sm" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); border: medium none transparent;margin-bottom:2px;" >';
		str+='<div class="row">';
		str+='<div class="form-group col-md-6 col-sm-6 col-xs-6 ">';
		str+='<label for="exampleInputEmail2" >Committee Level</label>';
		str+='<select class="form-control "></select>';
		str+='</div>';
		str+='<div class="form-group col-md-6 col-sm-6 col-xs-6">';
		str+='<label >Committee Name</label>';
		str+='<select class="form-control "></select>';
		str+='</div>	';				  
		str+='</div>';

		str+='<div class="row">';
		str+='<div class="form-group col-md-4 col-sm-4 col-xs-4 ">';
		str+='<label for="exampleInputEmail2" >Committee Role</label>';
		str+='<input type="text" class="form-control ">';
		str+='</div>';
		str+='<div class="form-group col-md-4 col-sm-4 col-xs-4">';
		str+='<label >From Date</label>';
		str+='<input type="text" class="form-control ">';
		str+='</div>';
		str+='<div class="form-group col-md-4 col-sm-4 col-xs-4">';
		str+='<label >To Date</label>';
		str+='<input type="text" class="form-control ">';
		str+='</div>	';				  
		str+='</div>';
		str+='<a href="javascript:{addMoreRolesForCadre();}" class="btn btn-danger btn-xs ">Tap to Add+ Details</a>	';				
		str+='</div>';
		$('#addMoreRolesDiv').append(str);
	}
	
	function getCadreCommitteDetails(levelId,divId)
	{
		$('#'+divId+'').find('option').remove();
		$('#'+divId+'').append('<option value="0"> Select Committee </option>');
		
		var jsObj = 
		   {
				levelId : levelId,
				task:"getCadreCommitteDetails"             
		   }				   
		   $.ajax({
				type : "POST",
				url : "getCadreCommitteDetailsAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				for(var i in result)
				{
					$('#'+divId+'').append('<option value="'+result[i].id+'"> '+result[i].name+' </option>');
				}
			});
	}

	function getCadreCommitteRoles(committeeId,buildDivId,divId)
	{
		var levelId = $('#'+divId+'').val();
		
		$('#'+buildDivId+'').find('option').remove();
		$('#'+buildDivId+'').append('<option value="0"> Select Role </option>');
		
		
		var jsObj = 
		   {
				levelId : levelId,
				committeeId : committeeId,
				task:"getCadreCommitteRoles"             
		   }				   
		   $.ajax({
				type : "POST",
				url : "getCadreCommitteRolesAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				for(var i in result)
				{
					$('#'+buildDivId+'').append('<option value="'+result[i].id+'"> '+result[i].name+' </option>');
				}
			});
	}
	
	function getElectionYears(id,divId)
	{
		var jsObj = 
			   {			
				  eletionTypeId : id,
				  task          : "getConstituncyWiseDetails"             
			   }				   
			   $.ajax({
					type : "POST",
					url : "getElectionYearsByElectionType.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
						$('#'+divId+'').html('');
						$('#'+divId+'').append('<option value="0"> Select Election </option>');
						if(result != null && result.length >0)
						{
							for(var i in result)
							{
								$('#'+divId+'').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');		
							}
						}
				});
	}

	function getConstiteuncyListForElection(eletionId,constiListId)
	{
			$('#loadingImg').show();
			
			$('#'+constiListId+'').find('option').remove();
			$('#'+constiListId+'').append('<option value="0"> Select Location </option>');
			
			var jsObj = 
			   {			
				  electionId : eletionId,
				  constituencyId : 282,
				  task       : "getConstiteuncyListForElection"             
			   }				   
			   $.ajax({
					type : "POST",
					url : "getConstiteuncyListForElectionAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){

					$('#loadingImg').hide();
					if(result != null)
					{
						for(var i in result)
						{
							$('#'+constiListId+'').append('<option value="'+result[i].id+'"> '+result[i].name+' </option>');
						}
					}					
				});
	}
	
	function clearDiv(divId)
	{
		 var where_to= confirm("Are you want remove it?");
		 if (where_to== true)
			 $('#'+divId+'').html('');		
	}
	function submitCadreForm()
	{	
		$(".requiredFields").html('');
		
	   //$("#submitCadreFormBtnReqId").removeAttr("onclick");
	   //$("#submitCadreFormBtnReqId").attr("disabled","disabled");
	   
	   var errString="";
				var ageId=$("#ageId").val();
				var genderId=$("#genderDetailsId").val();
				var voterCardNoId=$("#voterCardNoId").val();
				var adhaarNoId =$("#adhaarNoId").val();
				//var mobileNumber = document.getElementById('mobileNoId');
				var castId = document.getElementById('casteId');
				var emailId = document.getElementById('emailIdDiv');
				
			if($("#ageId").val().trim().length  == 0 ){
					$("#ageIdErrorDiv").html("please enter age details.");
					errString="error";
				}
				if($("#genderDetailsId").val()== 0){
					$("#genderIdErrorDiv").html("please enter Gender Details.");
					errString="error";
				}
				if($("#voterCardNoId").val().trim().length == 0){
					$("#voterCardNoIdError").html("please enter Voter Card Number.");
					errString="error";
				}
				if($("#adhaarNoId").val().trim().length != 12){
					$("#adhaarNoIdError").html("please enter valid Aadhar id Number.");
					errString="error";
				}
				if($("#mobileNoId").val().trim().length != 10){
					$("#mobileNOErrorId").html("Mobile No doesn't exist.please check it once.");
					errString="error";
				}
				if($("#casteId").val()== 0){
					$("#casteIdErrorDiv").html("caste Needs to be Select");
					errString="error";
				}
				if($("#addressId").val().trim().length==0){
					$("#addressIdError").html("address Field need to be Required.");
				}
				if($("#educationId").val()==0){
					$("#educationIdError").html("education Field need to be Select");
					errString="error";
				}
				if($("#occupationId").val()==0){
					$("#occupationIdError").html("Occupation field Need to be Select");
					errString="error";
				}
				
		/*		if(emailId.length == 0){
					 $('#emailIdDivError').html('email id is Required.');
					 errString="error";
					} 
					else if( emailId != null && !validateEmail(emailId)) {  	  
					  $('#emailIdDivError').html('Invalid email.');
					 errString="error";
				}	*/
				if(errString.trim().length >0){
					return;
				}
				else{	
					var uploadHandler = {
						upload: function(o) {
							uploadResult = o.responseText;
							console.log(uploadResult);
							//showUploadStatus(uploadResult);	
						}
					};

				YAHOO.util.Connect.setForm('uploadCadreForm',true);
				YAHOO.util.Connect.asyncRequest('POST','committeTdpCadreSaveRegistrationAction.action',uploadHandler);	
			}	
	}
	function casteDetailsByGroupId(){
		var casteGroupId = $("#casteCategory").val();
		$('#casteList').find('option').remove();
		$('#casteList').append('<option value="0"> Select Caste </option>');
		var jsObj={			
				  casteGroupId : casteGroupId       
			   }
			 $.ajax({
					type : "POST",
					url : "getCasteDetailsBygroupIdAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
				
				if(result != null)
					{
						for(var i in result)
						{
							$('#casteList').append('<option value="'+result[i].id+'"> '+result[i].name+' </option>');
						}
					}	
			});
	}
	
	function validateEmail($email) {
	  var emailReg = /^([A-Za-z0-9_\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	  if( !emailReg.test( $email ) ) {
		return false;
	  } else {
		return true;
	  }
	}

	function getCommitteCadreMembersInfo(type){
		$("#committeeLocationIdErr").html("");
		$("#committeeTypeIdErr").html("");
		$("#afflitCommitteeIdErr").html("");
		var locId = $("#committeeLocationId").val();
		var locVal = $("#afflitCommitteeId").val();
		if(locId == null || locId == 0){
			$("#committeeLocationIdErr").html("Please Select Location");
			return;
		}
		if($("#committeeTypeId").val() == 0){
			$("#committeeTypeIdErr").html("Please Select Committee Type");
			return;
		}
		if(type == 2)
		{		
			if($("#committeeTypeId").val() == 2){
				 if(locVal == null || locVal == 0){
					$("#afflitCommitteeIdErr").html("Please Select Affiliated Committee");
					return;
				}
			}
		}
		
		 $("#committeeDetailsDiv").show();
		 //$("#searchcadrenewDiv").hide();
		 $("#commitMembrsCountDiv").html('<center><img src="images/icons/loading.gif"  /></center>');
		 $("#committeeMmbrsMainDiv").html("");
		 var reqCommitteeType = "main";
		 var reqLocationType = "";
		 var title ="MAIN COMMITTEE";
		 if($("#committeeTypeId").val() == 2){
			 reqCommitteeType = "affiliated";
			 title =$.trim($("#afflitCommitteeId option:selected").text())+" COMMITTEE";
		 }
		 $("#affComitteeMainTitle").html(title.toUpperCase());
		 if(reqCommitteeType == "main"){
		   if(areaType == 2){
		     reqLocationType ="mandal";
		   }
		   reqLocationValue=$("#committeeLocationId").val();
		 }else{
			 reqLocationValue=$("#afflitCommitteeId").val();
		 }
		 $("#committeePositionId  option").remove();
		 $("#committeePositionId").append('<option value="0">Select Position</option>');
		  $.ajax({
				type : "POST",
				url : "getCommitteMembersInfoAction.action",
				data : {locationType:reqLocationType,locationValue:reqLocationValue,committeeType:reqCommitteeType} ,
			}).done(function(result){
				
				if(result != null)				
				{
					if(result.result != null && result.result.length>0)
					{
						for(var i in result.result)
							$("#committeePositionId").append('<option value="'+result.result[i].locationId+'">'+result.result[i].locationName+'</option>');
					}
				}
		    });
	}
	