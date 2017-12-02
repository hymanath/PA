
	function showAndHideTabs(searchType)
	{
		 $('#basicCommitteeTab, #publicrepresantativeTab, #mandalaffiliatedTab').removeClass('arrow_selected');
		 $("#cadreDetailsDiv,#committeLocationIdErr,#committeeLocationIdErr,#searchErrDiv,#searchLevelErrDiv").html('');
		 $('#nonAfflitCommitteeIdErr').html('');
		 $("#basicSearchDiv").show();
		 $("#searchBy").val('');
		 $("#committeLocationId").val(0);
		 $("#committeeLocationId").val(0);
		// $("#panchayatWardByMandal").val(0); 
		 $("#membershipId").prop("checked","checked");
		 $('#cadreSearchType').val('membershipId');
		 $('#committeLocationsDiv').hide();
		 $('#advancedSearchDiv,#cadreDetailsDiv').hide();
		 $("#headingDiv").html("");
		 $("#villageId").prop("checked","checked");
		 $('#areaTypeId').val(1);
		 $('#searchLevelId').val(0);
		 $("#step1Id,#step2Id,#step3Id").hide();
		 
		$("#committeLocationId  option").remove();
		$("#committeLocationId").append('<option value="0">Select Location</option>');
		$("#committeeLocationId  option").remove();
		$("#committeeLocationId").append('<option value="0">Select Location</option>');	
		
		$("#panchayatWardByMandal  option").remove();
		$("#panchayatWardByMandal").append('<option value="0">Select Mandal</option>');
		
		
		 $("#cadreDetailsDiv,#step3Id,#searchcadrenewDiv,#designationDivId,#step1Id,#committeeMainId").hide();
		 $("#cadreDetailsDiv").html("");
		 
		 
		$("#fromAgeId").val("");
		$("#toAgeId").val("");
		$("#searchBy").val("");
		$("#cadreDetailsDiv").html("");
		$("#casteCategory").val(0);
		$("#casteList").val(0);
		$("#ageRange").val(0);
		$("#gender").val(0);
			
		if(searchType == 'basicCommitteeDiv')
		{
			$('#'+searchType+'').show();
			$('#committeeMngtType').val(1);
			$('#publicrepresantative').hide();
			$('#mandalaffiliated').hide();			
			$('#basicCommitteeTab').addClass('arrow_selected');			
			$("#searchcadrenewDiv").hide();
			$("#headingDiv").html("Select Candidate for Selected Designation");
			//getCommitteeLocations();
			getMandalCorporationsByConstituency();
			
			$("#fromAgeId").val("");
			$("#toAgeId").val("");
			$("#searchBy").val("");
			$("#cadreDetailsDiv").html("");
			$("#casteCategory").val(0);
			$("#casteList").val(0);
			$("#ageRange").val(0);
			$("#gender").val(0);
		
		}
		
		else if(searchType == 'publicrepresantative')
		{	$('#committeLocationsDiv').show();			
			$('#basicCommitteeDiv').hide();
			$('#committeeMngtType').val(2);
			$('#'+searchType+'').show();
			$('#mandalaffiliated').hide();
			$('#publicrepresantativeTab').addClass('arrow_selected');			
			$("#searchcadrenewDiv").show();
			$("#advancedSearchDiv").hide();	
			$("#headingDiv").html("Select Candidate for a Public Representative ");	
		
		}
		else if(searchType == 'mandalaffiliated')
		{	$('#committeLocationsDiv').show();				
			$('#committeeMngtType').val(3);
			$('#publicrepresantative').hide();
			$('#basicCommitteeDiv').hide();
			$('#'+searchType+'').show();
			$('#mandalaffiliatedTab').addClass('arrow_selected');
			$("#searchcadrenewDiv").show();
			$("#advancedSearchDiv").hide();	
			$("#headingDiv").html("Select Candidate for a affiliated Committee Member ");			
	}
	}
	
	function validateSearchType(areaTypeId)
	{
		$("#cadreDetailsDiv").hide();
		$('#cadreDetailsDiv').html("");
		$("#designationDivId,#step1Id,#step2Id,#step3Id").hide();	
		$("#searchcadrenewDiv").hide();	
		if(areaTypeId == 1) //  Village / Ward / Division
		{
			$('#areaTypeId').val(areaTypeId);
			$("#affiliCommitteeAllInfoDivId").html("");
			$("#printBtnDiv").hide();
			$("#addMembrsBtn").show();
			$("#viewMembrsBtn").show();
		}
		
		else if(areaTypeId == 2) // Mandal / Town / GHMC 
		{				
			$('#areaTypeId').val(areaTypeId);
			$("#affiliCommitteeAllInfoDivId").html("");
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
		//committeTypeID means 
			//for committiee management 1
			//for Mandal/Muncipality Main Committee Electoral Management 2
			//for Mandal/Muncipality Affiliated Committee Electoral Management 3
		//areaTypeId means
		    //for panchayat level 1
			//for mandal level 2
			
		var areaTypeId  =  $('#areaTypeId').val();
		var committeeLocationId =$("#committeeLocationId").val();
		
		var locationLevel = 0;
		var locationValue = 0;
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
		$('#cadreDetailsDiv,#searchErrDiv,#committeeLocationIdErr,#committeLocationIdErr,#advancedSearchErrDiv').html('');
		$('#searchLevelErrDiv,#committeePositionIdErr,#nonAfflitCommitteeIdErr').html('');
		$("#cadreDetailsDiv").hide();
		var searchBy = $('#searchBy').val().trim();
		var searchRadioType = $('#cadreSearchType').val();
		var committeTypeID = $('#committeeMngtType').val();
		var committeePosition = $('#committeePositionId').val();
		var parentLocation = 0;
		
		$("#step3Id").hide();
		
		if(committeTypeID ==3)
		{
			var afiliatedCommitteeId = $("#nonafiliatedCommitteeId").val();
			if(afiliatedCommitteeId == null ||  afiliatedCommitteeId ==0)
			{
				$('#nonAfflitCommitteeIdErr').html('Please Select Affiliated Committee.');
				return;
			}			
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
		}
		if(searchRadioType == 'mobileNo')
		{	
			mobileNo = $('#searchBy').val().trim();
			
			if(searchRadioType=="mobileNo"){
					
					var numericExpression = /^[0-9]+$/;
					if(!$('#searchBy').val().match(numericExpression)){
						$('#searchErrDiv').html('Enter Numerics Only.');
						return;
					}
			}	
			
			if(searchBy.trim().length == 0 )
			{
				$('#searchErrDiv').html('Please enter Mobile No.');
				return;
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
			locationLevel :"0",
			locationValue:"0",
			searchName : "",
			mobileNo: mobileNo,
			casteCategory : "",
			fromAge : "0",
			toAge : "0",
			memberShipCardNo: memberShipCardNo,
			casteStateId : "0",
			trNumber : trNumber,
			voterCardNo:voterCardNo,
			gender:"",
			houseNo:"",
			removedStatus:"false",
			enrollmentId : 4,
			task:"search"
		}

		$.ajax({
				type : "POST",
				url : "getCadreSearchDetailsAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				 if(typeof result == "string"){
					if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
					  location.reload(); 
					}
				}
				$("#searchDataImg").hide();
				$('#cadreDetailsDiv').show();
				var committeTypesId = $('#committeeMngtType').val();
				
				if(result != null && result.previousRoles != null && result.previousRoles.length>0)
				{
					if(committeTypesId == 1)
					{
						$("#step3Id").show();
					}
				
					buildCadreDetails(result.previousRoles);
				}
				else
				{
					$('#cadreDetailsDiv').html("<span style='font-weight:bold;text-align:center;'> No Data Available...</span>");
				}
			});  

	}
	
	function buildCadreDetails(result)
	{
		var str ='';
		var committeeMngntTypeId = $('#committeeMngtType').val();
		var elegRolCnt=0;
		var dtCnt = 0;
		if(result != null)
		{
			for(var i in result)
			{
				
				str+='<div class="media">';
				str+='<span href="#" class="media-left">';
				str+='<img style="width: 64px; height: 64px;" src="https://www.mytdp.com/images/cadre_images/'+result[i].imageURL+'" />';
				str+='</span>';
				str+='<div class="media-body">';
				str+='<h5 class="media-heading"> <span style="font-weight:bold;"> Name:</span> '+result[i].cadreName+' ; ';				
				str+=' <span style="font-weight:bold;"> Relative Name: </span>'+result[i].relativeName+' </h5>';
				str+='<ul class="list-inline">';
				str+='<li>Age:'+result[i].age+';</i>';
				str+='<li>Gender: '+result[i].gender+'</i>';
				str+='<li>Mobile No: '+result[i].mobileNo+'</i>';
				str+='<li>Caste: '+result[i].casteName+'</i>';
				str+='<li>Voter ID: '+result[i].voterCardNo+'</i>';
				//str+='<li>Aadhar: '+result[i].imageURL+'</i>';
				str+='</ul>';
				
				if(result[i].committeePosition != null && result[i].committeePosition.trim().length > 0)
				{
					str+='<ul>';
					str+='<li style="font-weight:bold;">Existing Designation : '+result[i].committeePosition+' for '+result[i].committeeName+' Committee in '+result[i].committeeLocation+'</i>';	
					if(result[i].previousRoles != null && result[i].previousRoles.length > 0){
						for(var j in result[i].previousRoles){
							str+='<li style="font-weight:bold;">Electoral for '+result[i].previousRoles[j].committeeName+' Committee in '+result[i].previousRoles[j].committeeLocation+'</i>';
						}
					}
					str+='<input type="hidden" id="existingRole'+i+'" value=" Aleady  '+result[i].cadreName+' added as '+result[i].committeePosition+' for '+result[i].committeeName+' Committee in '+result[i].committeeLocation+'"/>';
					str+='</ul>';	
					str+='</div>';
					str+='</div>';
					
					if(committeeMngntTypeId == 1)
					{
						str+='<div class="form-inline ">';
						str+='<a onclick="jacascript:{getCadreProfileInfo('+result[i].tdpCadreId+',\'existingRole'+i+'\','+result[i].voterId+')}" class="btn btn-success btn-medium m_top5" > SELECT & UPDATE PROFILE</a>';
						str+='</div>	';	
					}
				
					if(committeeMngntTypeId == 2)
					{

						str+='<div class="form-inline  m_top5" id="elecroleDiv'+result[i].tdpCadreId+'" ">';
						str+='</div>	';					
						
						str+='<div class="row">	';
						str+='<div class="col-md-8 col-sm-12 col-xs-12 form-group addmoreId'+result[i].tdpCadreId+'" id="updateBtnId'+result[i].tdpCadreId+'">';
						str+='<a href="javascript:{addMoreEligibleRoles(\'elecroleDiv'+result[i].tdpCadreId+'\',0,\'updateBtnId'+result[i].tdpCadreId+'\','+result[i].tdpCadreId+');}" class="btn btn-success  btn-xs ">Click here to Add+ Details</a>';	
						str+='</div>';
						str+='</div>';	
						
						str+='<div class="row">	';
							str+='<div class="col-md-12 col-sm-12 col-xs-12 form-group" id="statusDiv'+result[i].tdpCadreId+'">';
							str+='</div>';
						str+='</div>';	
						
						str+='<div class="form-inline ">';
						str+='<a onclick="jacascript:{addAsElectrole('+result[i].tdpCadreId+',\'elecroleDiv'+result[i].tdpCadreId+'\',\'addmoreId'+result[i].tdpCadreId+'\',\'statusDiv'+result[i].tdpCadreId+'\')}" class="btn btn-success btn-medium m_top5 elecroleDiv'+result[i].tdpCadreId+'" style="display:none;" > UPDATE ELECTORAL DETAILS </a>';
						str+='</div>	';
					}	
					else if(committeeMngntTypeId == 3)
					{
						str+='<div class="row">	';
							str+='<div class="col-md-8 col-sm-12 col-xs-12 form-group" id="statusDiv'+result[i].tdpCadreId+'">';
							str+='</div>';
						str+='</div>';	
						
						str+='<div class="form-inline elecroleDiv'+result[i].tdpCadreId+'">';
						str+='<a onclick="jacascript:{addAsAfiliatedElectrole('+result[i].tdpCadreId+',\'elecroleDiv'+result[i].tdpCadreId+'\',\'statusDiv'+result[i].tdpCadreId+'\')}" class="btn btn-success btn-medium m_top5 " > ADD AS AFFILIATED ELECTROLE </a>';
						str+='</div>	';
					}								
				}
				else{
					if(result[i].previousRoles != null && result[i].previousRoles.length > 0){
					    str+='<ul>';
						for(var j in result[i].previousRoles){
							str+='<li style="font-weight:bold;">Electoral for '+result[i].previousRoles[j].committeeName+' Committee in '+result[i].previousRoles[j].committeeLocation+'</i>';
						}
					    str+='</ul>';
					}
					str+='</div>';
					str+='</div>';
					
					if(committeeMngntTypeId == 1)
					{
						str+='<div class="form-inline ">';
						str+='<a onclick="jacascript:{getCadreProfileInfo('+result[i].tdpCadreId+' ,0)}" class="btn btn-success btn-medium m_top5" > SELECT & UPDATE PROFILE</a>';
						str+='</div>	';	
					}
					else if(committeeMngntTypeId == 2)
					{

						str+='<div class="form-inline m_top5" id="elecroleDiv'+result[i].tdpCadreId+'" >';						
						str+='</div>	';
						str+='<div class="row">	';
						str+='<div class="col-md-8 col-sm-12 col-xs-12 form-group addmoreId'+result[i].tdpCadreId+'" id="updateBtnId'+result[i].tdpCadreId+'">';
						str+='<a href="javascript:{addMoreEligibleRoles(\'elecroleDiv'+result[i].tdpCadreId+'\',0,\'updateBtnId'+result[i].tdpCadreId+'\','+result[i].tdpCadreId+');}" class="btn btn-success  btn-xs ">Click here to Add+ Details</a>';	
						str+='</div>';
						str+='</div>';
						str+='<div class="row">	';
							str+='<div class="col-md-8 col-sm-12 col-xs-12 form-group" id="statusDiv'+result[i].tdpCadreId+'">';
							str+='</div>';
						str+='</div>';	
						
						str+='<div class="form-inline " >';
						str+='<a onclick="jacascript:{addAsElectrole('+result[i].tdpCadreId+',\'elecroleDiv'+result[i].tdpCadreId+'\',\'addmoreId'+result[i].tdpCadreId+'\',\'statusDiv'+result[i].tdpCadreId+'\')}" class="btn btn-success btn-medium m_top5 elecroleDiv'+result[i].tdpCadreId+'"  style="display:none;"> UPDATE  ELECTORAL DETAILS </a>';
						str+='</div>	';
					}	
					else if(committeeMngntTypeId == 3)
					{
						str+='<div class="row">	';
							str+='<div class="col-md-8 col-sm-12 col-xs-12 form-group" id="statusDiv'+result[i].tdpCadreId+'">';
							str+='</div>';
						str+='</div>';	
						
						str+='<div class="form-inline elecroleDiv'+result[i].tdpCadreId+'">';
						str+='<a onclick="jacascript:{addAsAfiliatedElectrole('+result[i].tdpCadreId+',\'elecroleDiv'+result[i].tdpCadreId+'\',\'statusDiv'+result[i].tdpCadreId+'\')}" class="btn btn-success btn-medium m_top5 " > ADD AS AFFILIATED ELECTROLE </a>';
						str+='</div>	';
					}	
				}
				elegRolCnt++;
				dtCnt++;
			}
		}
		
		$('#cadreDetailsDiv').html(str);
	}
	
	
	function showElectroleDiv(divId)
	{
			$('#'+divId+'').show();
			$('.'+divId+'').show();
	}
	function removeselDiv(eqId){
		if(confirm("Do you want to remove?")){
		  $("#"+eqId).remove();
		}
	}
	function getCadreProfileInfo(tdpCadreId,existingRole,existingId)
	{
		if(existingId != undefined && existingId != null && existingId != 'null'){
			if(existingId == committeePosition){
				alert("Candidate Already Added To This Designation");
				return;
			}
		}
		var existingDesignation = '';
		if(existingRole != null && existingRole != 0 && existingRole.trim().length>0)
		{
			existingDesignation = $('#'+existingRole+'').val();
		}
		var committeePositionStr = $('#committeePositionId option:selected').text().trim();
		var committeeTypeId = $('#committeeTypeId').val();
		var committeeType = $('#committeeTypeId option:selected').text().trim();
	    var committeeId = $('#afflitCommitteeId').val();
			if(committeeTypeId ==2 )
			{
				committeeType = $('#afflitCommitteeId option:selected').text().trim();
			} 
		if(existingDesignation != null && existingDesignation.trim().length>0)
		{
			if(!confirm(""+existingDesignation+". Are you sure want to change his Designation as "+committeePositionStr+" for "+committeeType+" in "+ globalLocationName+".?" ))
			{
				return;
			}
		}
		 //committeeTypeId(1 Main Committee ,2 Affiliated Committee),committeeId(afflitCommitteeId),result3(COMMITTEE DESIGNATION id)
		
			
		window.location.href = 'cadreProfileDetailsAction.action?locationId='+reqlocationId+'&reqLocationType='+reqLocationType+'&tdpCadreId='+tdpCadreId+'&result1='+$("#committeePositionId option:selected").text().trim()+'&result2='+committeeType+'&result4='+globalLocationName+'&committeeMngtType=4&result3='+$("#committeePositionId").val()+'&committeeTypeId='+committeeTypeId+'&committeeId='+committeeId;

		return;
		var mandalId=$("#panchayatWardByMandal").val();
		
		var committeTypeID = $('#committeeMngtType').val();		
		var committeeLocationId = $('#committeeLocationId').val();
		var committeeTypeId = $('#committeeTypeId').val();
		
		var isError = "";
		var areaType = $('#areaTypeId').val();
		var committeeMngtType = $('#committeeMngtType').val();
		var committeePosition = $('#committeePositionId').val();
		

		
		var locationTypeStr= " Mandal " ;
		
		if(committeTypeID ==1)
		{			
			$('#afflitCommitteeIdErr,#committeeTypeIdErr,#committeeLocationIdErr,#committeePositionIdErr').html('');
			if(committeeLocationId == 0)
			{
				$('#committeeLocationIdErr').html('Please Select Location.');
				isError = "true";
			}
			else
			{
				if(areaType ==1)
				{
					if(committeeLocationId.substr(0,1) == 1){
						  locationTypeStr = " Panchayat";
					}
					else if(committeeLocationId.substr(0,1) == 2){
						  locationTypeStr = " Ward";
					}
				}
				if(areaType ==2)
				{
					if(committeeLocationId.substr(0,1) == 1){
						  locationTypeStr = " Muncipality ";
					}
					else if(committeeLocationId.substr(0,1) == 2){
						  locationTypeStr = " Mandal";
					}
					else if(committeeLocationId.substr(0,1) == 3){
						 locationTypeStr = " Division ";
					}
				}
			}
			if(committeeTypeId == 0)
			{
				$('#committeeTypeIdErr').html('Please Select Committee Type.');
				isError = "true";
			}
			if(committeeTypeId ==2 && committeeId == 0)
			{
				$('#afflitCommitteeIdErr').html('Please Select Committee.');
				isError = "true";
			} 
			if(committeePosition == 0)
			{
				$('#committeePositionIdErr').html('Please Select Designation.');
				isError = "true";
			} 
			
			var committeeType = $('#committeeTypeId option:selected').text().trim();
			
			if(committeeTypeId ==2 )
			{
				committeeType = $('#afflitCommitteeId option:selected').text().trim();
			} 
			if(isError.trim().length >0)
			{
				$('html,body').animate({scrollTop: $('#committeeLocationIdErr').offset().top}, 800);
				return;
			}
			else{
				if(existingId != undefined && existingId != null && existingId != 'null'){
					if(existingId == committeePosition){
						alert("Candidate Already Added To This Designation");
						return;
					}
				}
				if(existingDesignation != null && existingDesignation.trim().length>0)
				{
					if(!confirm(""+existingDesignation+". Are you sure want to change his Designation as "+committeePositionStr+" for "+committeeType+" in "+ $('#committeeLocationId option:selected').text() +" "+locationTypeStr+".?" ))
					{
						return;
					}
					window.location.href = 'cadreProfileDetailsAction.action?locationId='+reqlocationId+'&reqLocationType='+reqLocationType+'&tdpCadreId='+tdpCadreId+'&task='+areaType+'&committeeMngtType='+committeeMngtType+'&panchayatId='+committeeLocationId+'&committeeTypeId='+committeeTypeId+'&committeeId='+committeeId+'&result1='+$('#committeePositionId option:selected').text().trim()+'&result2='+committeeType+'&result3='+committeePosition+'&result4='+$('#committeeLocationId option:selected').text()+''+locationTypeStr+'&mandalId='+mandalId+'';
				}
				else{
					window.location.href = 'cadreProfileDetailsAction.action?locationId='+reqlocationId+'&reqLocationType='+reqLocationType+'&tdpCadreId='+tdpCadreId+'&task='+areaType+'&committeeMngtType='+committeeMngtType+'&panchayatId='+committeeLocationId+'&committeeTypeId='+committeeTypeId+'&committeeId='+committeeId+'&result1='+$('#committeePositionId option:selected').text().trim()+'&result2='+committeeType+'&result3='+committeePosition+'&result4='+$('#committeeLocationId option:selected').text()+''+locationTypeStr+'&mandalId='+mandalId+'';
				}
				
				
			}
		}else
		{
			window.location.href = 'cadreProfileDetailsAction.action?locationId='+reqlocationId+'&reqLocationType='+reqLocationType+'&tdpCadreId='+tdpCadreId+'&task='+areaType+'&committeeMngtType='+committeeMngtType+'&mandalId='+mandalId+'';
		}
	}
	
	function submitCadreForm()
	{	
		
	var isexisting = $('#exitingPositionId').val();
	var existingMsg = $('#existingMsg').val();

	
	
		$(".requiredFields").html('');
		
	   
	   
	   var errString="";
				var ageId=$("#ageId").val();
				var genderId=$("#genderDetailsId").val();
				var voterCardNoId=$("#voterCardNoId").val();
				var adhaarNoId =$("#adhaarNoId").val();
				//var mobileNumber = document.getElementById('mobileNoId');
				var castId =  $("#casteId").val();
				var emailId =  $("#emailIdDiv").val();
				
			if($("#ageId").val().trim().length  == 0 ){
					$("#ageIdErrorDiv").html("Please Enter Age Details.");
					errString="error";
				}
				if($("#genderDetailsId").val()== 0){
					$("#genderIdErrorDiv").html("Please Enter Gender Details.");
					errString="error";
				}
				var isDisabled = $("#voterCardNoId").prop('disabled');
				
				if(isDisabled == false)
			{
				
				if($("#voterCardNoId").val().trim().length == 0){
					$("#voterCardNoIdError").html("Please Enter Voter Card Number.");
					errString="error";
				}
			}
				/*if($("#adhaarNoId").val().trim().length != 12){
					$("#adhaarNoIdError").html("Please Enter valid Aadhar id Number.");
					errString="error";
				}*/
			if($("#mobileNoId").val().trim().length != 10){
					$("#mobileNOErrorId").html("Invalid Mobile No.");
					errString="error";
				}
				if($("#casteId").val()== 0){
					$("#casteIdErrorDiv").html("Please Select Caste");
					errString="error";
				}
				/*if($("#addressId").val().trim().length==0){
					$("#addressIdError").html("Please Enter Address.");
				}*/
				/*if($("#educationId").val()==0){
					$("#educationIdError").html(" Please Select Education. ");
					errString="error";
				}
				if($("#occupationId").val()==0){
					$("#occupationIdError").html("Please Select  Occupation.");
					errString="error";
				}
				
				if(emailId.length == 0){
					 $('#emailIdDivError').html('Please Enter Email id .');
					 errString="error";
					 return;
					} 
					else if( emailId != null && !validateEmail(emailId)) {  	  
					  $('#emailIdDivError').html('Invalid email.');
					 errString="error";
				}
				*/
				
				$('.fromDateCls').each(function(){
		
						var keyId = $(this).attr('key');
						$('#fromDateErr'+keyId+'').html('')	;		
						$('#toDateErr'+keyId+'').html('');
						
						var startDate = $('#fromDateId'+keyId+'').val();
						var endDate = $('#toDateId'+keyId+'').val();	
						
						if((startDate != null && startDate.trim().length >0) && (endDate != null && endDate.trim().length >0))
						{
							var arrr = startDate.split("-");
								var fromyear=arrr[0];
								var frommonth=arrr[1];
								var fromDat=arrr[2];
						   var arr = endDate.split("-");
								var toyear=arr[0];
								var tomonth=arr[1];
								var toDat=arr[2];
								
								if(fromyear>toyear){
									$('#fromDateErr'+keyId+'').html('<font style="color:red;">From Date should not greater than To Date </font>');
									  errString = " error";
								}
								 if(frommonth>tomonth){
									   if(fromyear == toyear){
										$('#fromDateErr'+keyId+'').html('<font style="color:red;">From Date should not greater than To Date </font>');
										errString = " error";
									}
									
								}
								
								if(fromDat>toDat){	
									if(frommonth == tomonth && fromyear == toyear){			
										$('#fromDateErr'+keyId+'').html('<font style="color:red;">From Date should not greater than To Date </font>');
										errString = " error";		
									   }
								}
						}
			
		         });
				if(errString.trim().length >0){
					return;
				}
				if(isexisting.trim().length>0)
				{
					if(!confirm(existingMsg+" ? "))
					{
						return;
					}
				}
				$("#submitCadreFormBtnReqId").hide();
			
					var uploadHandler = {
						upload: function(o) {
							uploadResult = o.responseText;
							showUploadStatus(uploadResult);	
						}
					};

				YAHOO.util.Connect.setForm('uploadCadreForm',true);
				YAHOO.util.Connect.asyncRequest('POST','committeTdpCadreSaveRegistrationAction.action',uploadHandler);
			
			
	}
	
	function showUploadStatus(uploadResult)
	{
		var res = uploadResult.split('{');
		if(res.length >0){
			uploadResult = uploadResult.replace(res[0],"");
		}
		res = uploadResult.split('}');
		if(res.length >1){
			uploadResult = uploadResult.replace(res[res.length-1],"");
		}

		var result = JSON.parse(uploadResult);

		if(result.resultCode == 0)
		{			

		$('.existingDiv').hide();
		$('#step2Id').hide();
		$('html,body').animate({scrollTop: $('.successDiv').offset().top}, 800);
		$('.successDiv').html('<span style="font-weight: bold;text-transform: uppercase;"> '+result.status+'</span> <a class="btn btn-success btn-xs" href="cadreCommitteeAction.action"  style="padding: 10px;margin-left:250px;"> <i class="glyphicon glyphicon-home"  title="BACK TO HOME"></i> </a>');
		$('#profileDiv').html('');

		}		
		else if(result.resultCode == 2)
		{			
			var redirectURL = $('#redirectURLId').val();
			$('#profileDiv').hide();
			$('#step2Id').hide();
			$('.existingDiv').hide();
			$('html,body').animate({scrollTop: $('.successDiv').offset().top}, 800);
			$('#redirectURLId').html('<span style="font-weight: bold;text-transform: uppercase;"> '+result.status+'</span> <br> <a class="btn btn-success btn-xs" href="'+redirectURL+'">  CLICK HERE IF YOU WANT DESIGNATION </a><a class="btn btn-success btn-xs" href="cadreCommitteeAction.action"  style="padding: 4px;margin-left:10px;"> <i class="glyphicon glyphicon-home"  title="BACK TO HOME"></i> </a>');
			$('.successDiv').html('<span style="font-weight: bold;text-transform: uppercase;color:red;"> '+result.status+'</span> <br> <a class="btn btn-success btn-xs" href="'+redirectURL+'">  CLICK HERE IF YOU WANT DESIGNATION </a><a class="btn btn-success btn-xs" href="cadreCommitteeAction.action"  style="padding: 4px;margin-left:10px;"> <i class="glyphicon glyphicon-home"  title="BACK TO HOME"></i> </a>');
		}
		else{
			$("#submitCadreFormBtnReqId").show();
			$('html,body').animate({scrollTop: $('.successDiv').offset().top}, 800);
			$('.successDiv').html('<span style="font-weight: bold;text-transform: uppercase;color:red;"> '+result.status+'</span> <br><a class="btn btn-success btn-xs" href="cadreCommitteeAction.action"  style="padding: 4px;margin-left:10px;"> <i class="glyphicon glyphicon-home"  title="BACK TO HOME"></i> </a>');
		}
		
		
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
				if(typeof result == "string"){
					if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
					  location.reload(); 
					}
				}
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
				if(typeof result == "string"){
					if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
					  location.reload(); 
					}
				}
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
						if(typeof result == "string"){
							if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
							  location.reload(); 
							}
						}
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
				  constituencyId : userLocation,
				  task       : "getConstiteuncyListForElection"             
			   }				   
			   $.ajax({
					type : "POST",
					url : "getConstiteuncyListForElectionAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){

					$('#loadingImg').hide();
					if(typeof result == "string"){
						if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
						  location.reload(); 
						}
					}
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
		 var where_to= confirm("Do you want to remove this information?");
		 if (where_to== true)
			 //$('#'+divId+'').html('');		
			 $('#'+divId+'').remove();		
	}
	
	function validateEmail($email) {
	  var emailReg = /^([A-Za-z0-9_\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	  if( !emailReg.test( $email ) ) {
		return false;
	  } else {
		return true;
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
				if(typeof result == "string"){
					if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
					  location.reload(); 
					}
				}
				if(result != null)
					{
						for(var i in result)
						{
							$('#casteList').append('<option value="'+result[i].id+'"> '+result[i].name+' </option>');
						}
					}	
			});
	}
	
	
	var userLocation = "";
	function getUserLocation(){
		var locationId = 0;
			var jObj ={
			locationId:locationId,
			task:"getConstituency"             
		}	
		$.ajax({
			type : "POST",
			url : "getUserAccessConstituencyAction.action",
			data : {task:JSON.stringify(jObj)} ,
		}).done(function(result){
			if(result != null){
				for(var i in result){				
				userLocation = result[i].id;
				}
				}
		});
	}
	
	function addAsElectrole(cadreId,btnId,addmoreId,resultDiv)
	{	
		var designationArr = new Array();
		var fromDatArr = new Array();
		var toDatArr = new Array();
		var isError = "";
		var fromdateidarr= new Array();
		var todateidarr=new Array();
		$('.validErrCls').html('');
		$('#'+resultDiv+'').html('');
			$('#'+resultDiv+'').hide();
		$('.designationCls'+cadreId+'').each(function(){
			var id= $(this).attr('id');
			var designationId = $('#'+id+'').val();
			
			if(designationId == null || designationId == 0)
			{
				$('#'+id+'Err').html('Please Select Designation.');
				isError = "true";
				return;
			}
			
			designationArr.push(designationId);
		});
		
		$('.fromDateCls'+cadreId+'').each(function(){
			var id= $(this).attr('id');
			var fromdateId = $('#'+id+'').val();
			if(fromdateId == null || fromdateId.trim().length == 0)
			{
				$('#'+id+'Err').html('From Date is required.');
				isError = "true";
				return;
			}
			fromdateidarr.push(id);
			fromDatArr.push(fromdateId);
		});
	
		$('.toDateCls'+cadreId+'').each(function(){
			var id= $(this).attr('id');
			var todateId = $('#'+id+'').val();
			if(todateId == null || todateId.trim().length == 0)
			{
				$('#'+id+'Err').html('To Date is required.');
				isError = "true";
				return;
			}	
			todateidarr.push(id);
			toDatArr.push(todateId);
			
		});
		var flag="false";
		for(var i=0;i<todateidarr.length;i++){
				
				var fromdate = $('#'+fromdateidarr[i]+'').val();
				var todate = $('#'+todateidarr[i]+'').val();
				if($('#'+fromdateidarr[i]+'').val()>$('#'+todateidarr[i]+'').val()){
					$('#'+fromdateidarr[i]+'Err').html('Fromdate should be lessthan Todate.');
					flag="true";
				}
			}
			if(flag=="true"){
				return;
			}
		
		if(isError.trim().length == 0)
		{	
				$('.'+btnId+'').hide();
				$('.'+addmoreId+'').hide();
				var jsObj = 
				   {
					   tdpCadreId :cadreId,
					   designationArr : designationArr,
					   fromDatArr : fromDatArr,
					   toDatArr : toDatArr,
					   task:"updateElectrolsDetails"             
				   }

				   $.ajax({
						type : "POST",
						url : "updateElectrolsDetails.action",
						data : {task:JSON.stringify(jsObj)} ,
					}).done(function(result){
						 if(typeof result == "string"){
								if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
								  location.reload(); 
								}
			             }
						
						$('#'+resultDiv+'').show();
							if(result != null)
							{
								if(result.resultCode == 0)
								{
									$('#'+resultDiv+'').html('<span style="color:green;font-weight:bold;"> '+result.message+'</span>');
								}
								else 
								{
									$('#'+resultDiv+'').html('<span style="color:red;font-weight:bold;"> '+result.message+'</span>');
								}
								
							}
					});
					
		}
		
			
	}
	
	function addAsAfiliatedElectrole(cadreId,btnId,resultDiv)
	{
		var afiliatedCommitteeId = $("#nonafiliatedCommitteeId").val();
		$('#nonAfflitCommitteeIdErr').html('');
		$('#'+resultDiv+'').html('');
		if(afiliatedCommitteeId == null && afiliatedCommitteeId ==0)
		{
			$('#nonAfflitCommitteeIdErr').html('Please Select Affiliated Committee.');
			return;
		}
		$('.'+btnId+'').hide();
		var jsObj = 
		   {
			   tdpCadreId :cadreId,
			   afiliatedCommitteeId:afiliatedCommitteeId
		   }
		   
		   $.ajax({
				type : "POST",
				url : "updateMandalAfiliatedElectrolsDetails.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				if(typeof result == "string"){
					if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
					  location.reload(); 
					}
				}

				if(result != null)
				{
					if(result.resultCode == 0)
					{
						$('#'+resultDiv+'').html('<span style="color:green;font-weight:bold;"> '+result.message+'</span>');
					}
					else 
					{
						$('#'+resultDiv+'').html('<span style="color:red;font-weight:bold;"> '+result.message+'</span>');
					}
					
				}
			});
			
	}