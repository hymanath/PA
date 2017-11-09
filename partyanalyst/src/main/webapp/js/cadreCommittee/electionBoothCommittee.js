var spinner = '<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>'
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
		$("#commitTypeId").show();
		/* $("#committeeTypeId  option").remove();
		 $("#committeeTypeId").append('<option value="0">Select Committee Type</option>');
		$("#committeeTypeId").append('<option value="3">View All Committee Info</option>');
		$("#committeeTypeId").append('<option value="1">Main Committee</option>');
		$("#committeeTypeId").append('<option value="2">Affiliated Committee</option>');*/
		if(areaTypeId == 1) //  Village / Ward / Division
		{
			$('#areaTypeId').val(areaTypeId);
			$("#affiliCommitteeAllInfoDivId").html("");
			$("#printBtnDiv").hide();
			$("#addMembrsBtn").show();
			$("#viewMembrsBtn").show();
			$("#mandalMainDivId").show();
		}
		
		else if(areaTypeId == 2) // Mandal / Town / GHMC 
		{				
			$('#areaTypeId').val(areaTypeId);
			$("#affiliCommitteeAllInfoDivId").html("");
			$("#mandalMainDivId").hide();
		}else if(areaTypeId == 3) //  Village / Ward / Division
		{
			$('#areaTypeId').val(areaTypeId);
			//$("#affiliCommitteeAllInfoDivId").html("");
			//$("#printBtnDiv").hide();
			//$("#addMembrsBtn").show();
			//$("#viewMembrsBtn").show();
			$("#mandalMainDivId").show();
			$("#commitTypeId").hide();
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
		
		if(committeTypeID ==1)
		{
			if(committeeLocationId == null || committeeLocationId == 0)
			{
				$('#committeeLocationIdErr').html('Please Select Location');
				return;
			}
			if(searchRadioType == 'name' || searchRadioType == 'advancedSearch'){
				committeeLocationId = $('#committeeLocationId').val();
				if(committeeLocationId == null || committeeLocationId == 0)
				{
					$('#committeeLocationIdErr').html('Please Select Location');
					return;
				}
				if(areaTypeId ==1)
					{
						if(committeeLocationId.substr(0,1) == 1){
							  locationLevel = 6;
						}
						else if(committeeLocationId.substr(0,1) == 2){
							 locationLevel = 8;
							 parentLocation = $("#panchayatWardByMandal option:selected").val();
							  parentLocation = parentLocation.substr(1);
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
						else if(committeeLocationId.substr(0,1) == 3){
							 locationLevel = 8;
						}						
					}
					locationValue = committeeLocationId.substr(1);
			}	
			
			if(committeePosition == 0)
			{
				$('#committeePositionIdErr').html('Please Select Designation.');
				return;
			} 			
		}
		else if(committeTypeID ==2 || committeTypeID ==3)
		{		
			
				var searchLevel = $('#searchLevelId').val();
				committeeLocationId = $('#committeLocationId').val();
				if(searchLevel == null || searchLevel == 0)
				{
					$('#searchLevelErrDiv').html('Please Select SearchLevel');
					return;
				}
				if(committeeLocationId == null || committeeLocationId == 0)
				{
					$('#committeLocationIdErr').html('Please Select Location');
					return;
				}
			
		}
		

		if(searchRadioType == 'mobileNo' || searchRadioType == 'voterId' || searchRadioType == 'membershipId')
		{				
				if(committeTypeID != 1 )
				{
					       committeeLocationId = $('#committeLocationId').val();
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
								else if(committeeLocationId.substr(0,1) == 3){
									 locationLevel = 8;
								}
							}
							locationValue = committeeLocationId.substr(1);				
				}				
				else
				{
					if(areaTypeId ==1)
					{
						if(committeeLocationId.substr(0,1) == 1){
							  locationLevel = 6;
						}
						else if(committeeLocationId.substr(0,1) == 2){
							 locationLevel = 8;
							 parentLocation = $("#panchayatWardByMandal option:selected").val();
							  parentLocation = parentLocation.substr(1);
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
						else if(committeeLocationId.substr(0,1) == 3){
							 locationLevel = 8;
						}						
					}
					locationValue = committeeLocationId.substr(1);
				}	
		}
		else
		{
			committeeLocationId = $('#committeLocationId').val();
			if(committeTypeID != 1 )
			{						
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
					else if(committeeLocationId.substr(0,1) == 3){
						 locationLevel = 8;
					}
				}
				locationValue = committeeLocationId.substr(1);
			}
			else
			{
				committeeLocationId = $('#committeeLocationId').val();
				if(areaTypeId ==1)
				{
					if(committeeLocationId.substr(0,1) == 1){
						  locationLevel = 6;
					}
					else if(committeeLocationId.substr(0,1) == 2){
						 locationLevel = 8;
						 parentLocation = $("#panchayatWardByMandal option:selected").val();
						 parentLocation = parentLocation.substr(1);
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
					else if(committeeLocationId.substr(0,1) == 3){
							 locationLevel = 8;
					}					
				}
				locationValue = committeeLocationId.substr(1);
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
		if(searchRadioType == 'name')
		{
			searchName = $('#searchBy').val().trim();
			
			if(searchBy.trim().length == 0 )
			{
				$('#searchErrDiv').html('Please enter Name.');
				return;
			}
			else if(searchBy.trim().length < 3)
			{
				$('#searchErrDiv').html('Please enter Minimum 3 Characters.');
				return;
			}
		}
		if(searchRadioType == 'advancedSearch')
		{			
			gender = $('#gender option:selected').text().trim();
			var casteGroup = $('#casteCategory').val();
			var casteName  = $('#casteList').val();
			var age = $('#ageRange').val();
			
			var locfromAge = $('#fromAgeId').val().trim();
			var loctoAge = $('#toAgeId').val().trim(); 
			
			if(casteGroup == 0 && casteName == 0 && age == 0 && gender == 'All' && locfromAge.length == 0 && loctoAge.length == 0 )
			{
				$('#advancedSearchErrDiv').html('Please Select Any of Search Criteria');
				return;			
			}			
			if($('#ageRange').val() != 0)
			{
				var ageRange = $('#ageRange option:selected').text();
				var ageRange = ageRange.split('-');
				fromAge = ageRange[0].trim();
				toAge = ageRange[1].trim();
			}
			else
			{
				fromAge = $('#fromAgeId').val().trim();
				toAge = $('#toAgeId').val().trim(); 
				
				if(fromAge.length >0 || toAge.length >0)
				{
					if(fromAge.length == 0 || toAge.length == 0)
					{
						$('#advancedSearchErrDiv').html('Please Enter Between Age Details.');
						return;	
					}
					if(fromAge > toAge){
						$('#advancedSearchErrDiv').html('From Age Should be Less than To Age.');
						return;							
					}
				}
				else
				{
					fromAge = 0;
					toAge = 0;					
				}
			}				
			casteCategory = $('#casteCategory option:selected').text().trim();
			casteStateId = $('#casteList').val().trim();
			
			if(casteCategory == 'All')
			{
				casteCategory = "";				
			}			
		}
		
		$("#searchDataImg").show();
		
		if(committeTypeID ==1 && locationLevel==8 && parentLocation!=null && parentLocation !=0){
			locationLevel = 7,
			locationValue = parentLocation
		}
		
		if(loginAreaType == 'URBAN')
		{
			locationLevel = 4;
			locationValue = userLocation;
		}

		if(locationValue == null)
			locationValue =0;
		
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
			houseNo:houseNo,
			removedStatus:"false",
			enrollmentId : 4,
			task:"search"
		}
		
		//console.log(jsObj);
		$.ajax({
				type : "POST",
				url : "getCadreSearchDetailsForCommitteeAction.action",
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
	
	function buildCadreDetails(result,locationId)
	{
		var str ='';
		var committeeMngntTypeId = $('#committeeMngtType').val();
		var elegRolCnt=0;
		var dtCnt = 0;
		if(result != null)
		{
			for(var i in result)
			{
				var addressVO = result[i].addressVO;
				str+='<div class="media">';
				str+='<span href="#" class="media-left">';
				str+='<img style="width: 64px; height: 64px;" src="https://www.mytdp.com/images/cadre_images/'+result[i].imageURL+'" />';
				str+='</span>';
				str+='<div class="media-body">';
				str+='<h5 class="media-heading"> <span style="font-weight:bold;"> Name:</span> '+result[i].cadreName+' ; ';				
				str+=' <span style="font-weight:bold;"> Relative Name: </span>'+result[i].relativeName+' </h5>';
				str+='<ul class="list-inline">';
				str+='<li>Age:'+result[i].age+';</li>';
				str+='<li>Gender: '+result[i].gender+'</li>';
				str+='<li>Mobile No: '+result[i].mobileNo+'</li>';
				str+='<li>Caste: '+result[i].casteName+'</li>';
				str+='<li>Voter ID: '+result[i].voterCardNo+'</li>';
				str+='<li> <span style="font-weight:bold;color:red;"> Serial No : '+result[i].serialNo+' </span></li>';
				  if(addressVO != null ){
						str+='<li>Mandal : '+addressVO.mandalName+'</li>';
						str+='<li>Panchayat : '+addressVO.panchayatName+'</li>';				
					}
				str+='</ul>';
				
				if(result[i].type != null && result[i].type.trim() == 'Added Member')
				{
					str+='<ul  style="color:#449D44;">';
					if(result[i].type != null && result[i].type.trim().length > 0){						
						if(result[i].localElectionBody == null )
							str+='<li style="font-weight:bold;"> Already added as  <span style="color:#000;"> '+result[i].roleName+'</span> Position for  Booth No - '+result[i].boothNumber+' ,'+result[i].panchayat+' Panchayat , '+result[i].tehsil+' Mandal. </li>';	
						else if(result[i].tehsil == null )
							str+='<li style="font-weight:bold;"> Already added as  <span style="color:#000;"> '+result[i].roleName+'</span> Position for Booth No - '+result[i].boothNumber+' , '+result[i].tehsil+' Muncipality. </li>';	
						else 
							str+='<li style="font-weight:bold;"> Already added as  <span style="color:#000;"> '+result[i].roleName+'</span>  Position for Booth No - '+result[i].boothNumber+' ,'+result[i].tehsil+' Mandal/Muncipality. </li>';	
					}
					str+='</ul>';	
					str+='</div>';
					str+='</div>';
													
				}
				else{
					str+='</div>';
					str+='</div>';
					
					if(result[i].type == "Not Added" && (result[i].isDuplicate=="No" || result[i].isDuplicate==""))
					{
						str+='<div class="form-inline ">';
						str+='<a onclick="javascript:{saveBoothDetails('+result[i].tdpCadreId+',\'\')}" class="btn btn-success btn-medium m_top5 addProfileCls" > ADD PROFILE</a><span id="errMsgId"></span>';
						str+='</div>';	
					}else if(result[i].isDuplicate=="Yes") {
						str+='<div class="form-inline " style="color:red;" title="This cadre may or may not registered with Own voterId or Family Voter Id." > No access to add .  Because This Serial No already added as incharger.</div> ';	
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
		var mandalId=$("#panchayatWardByMandal").val();
		
		var committeTypeID = $('#committeeMngtType').val();		
		var committeeLocationId = $('#committeeLocationId').val();
		var committeeTypeId = $('#committeeTypeId').val();
		var committeeId = $('#afflitCommitteeId').val();
		var isError = "";
		var areaType = $('#areaTypeId').val();
		var committeeMngtType = $('#committeeMngtType').val();
		
		var committeePosition = $('#committeeDesignationId').val();
		var committeePositionRoleType = $('#committeeDesignationId option:selected').attr("attr_role_type");
		
		
		var existingDesignation = '';
		var committeePositionStr = $('#committeeDesignationId option:selected').text().trim();
		var locationTypeStr= " Mandal " ;
		if(existingRole != null && existingRole != 0 && existingRole.trim().length>0)
		{
			existingDesignation = $('#'+existingRole+'').val();
		}
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
					  window.location.href = 'cadreProfileDetailsAction.action?tdpCadreId='+tdpCadreId+'&task='+areaType+'&committeeMngtType='+committeeMngtType+'&panchayatId='+committeeLocationId+'&committeeTypeId='+committeeTypeId+'&committeeId='+committeeId+'&result1='+$('#committeePositionId option:selected').text().trim()+'&result2='+committeeType+'&result3='+committeePosition+'&result4='+$('#committeeLocationId option:selected').text()+''+locationTypeStr+'&mandalId='+mandalId+'&roleType='+committeePositionRoleType+'&locationId='+reqlocationId+'';
				    //var url = 'cadreProfileDetailsAction.action?tdpCadreId='+tdpCadreId+'&task='+areaType+'&committeeMngtType='+committeeMngtType+'&panchayatId='+committeeLocationId+'&committeeTypeId='+committeeTypeId+'&committeeId='+committeeId+'&result1='+$('#committeePositionId option:selected').text().trim()+'&result2='+committeeType+'&result3='+committeePosition+'&result4='+$('#committeeLocationId option:selected').text()+''+locationTypeStr+'&mandalId='+mandalId+'&roleType='+committeePositionRoleType+'';
				    //window.open(url,'_blank');
				}
				else{
					 window.location.href = 'cadreProfileDetailsAction.action?tdpCadreId='+tdpCadreId+'&task='+areaType+'&committeeMngtType='+committeeMngtType+'&panchayatId='+committeeLocationId+'&committeeTypeId='+committeeTypeId+'&committeeId='+committeeId+'&result1='+$('#committeePositionId option:selected').text().trim()+'&result2='+committeeType+'&result3='+committeePosition+'&result4='+$('#committeeLocationId option:selected').text()+''+locationTypeStr+'&mandalId='+mandalId+'&roleType='+committeePositionRoleType+'&locationId='+reqlocationId+'';
				    //var url = 'cadreProfileDetailsAction.action?tdpCadreId='+tdpCadreId+'&task='+areaType+'&committeeMngtType='+committeeMngtType+'&panchayatId='+committeeLocationId+'&committeeTypeId='+committeeTypeId+'&committeeId='+committeeId+'&result1='+$('#committeePositionId option:selected').text().trim()+'&result2='+committeeType+'&result3='+committeePosition+'&result4='+$('#committeeLocationId option:selected').text()+''+locationTypeStr+'&mandalId='+mandalId+'&roleType='+committeePositionRoleType+'';
			        //window.open(url,'_blank');
				}
			}
		}else
		{
			 window.location.href = 'cadreProfileDetailsAction.action?tdpCadreId='+tdpCadreId+'&task='+areaType+'&committeeMngtType='+committeeMngtType+'&mandalId='+mandalId+'&roleType='+committeePositionRoleType+'&locationId='+reqlocationId+'';
		    //var url = 'cadreProfileDetailsAction.action?tdpCadreId='+tdpCadreId+'&task='+areaType+'&committeeMngtType='+committeeMngtType+'&mandalId='+mandalId+'&roleType='+committeePositionRoleType+'';
			//window.open(url,'_blank');
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
       // alert( reqcommitteeMngtType + "-" +result.resultCode );
		if(result.resultCode == 0){//Success

			$('.existingDiv').hide();
			$('#step2Id').hide();
			$('html,body').animate({scrollTop: $('.successDiv').offset().top}, 800);
			if(reqcommitteeMngtType == 4){
				
				if(reqlocationId != null && reqlocationId>0)
					$('.successDiv').html('<span style="font-weight: bold;text-transform: uppercase;"> '+result.status+'</span> <a class="btn btn-success btn-xs" href="committeeManagementAction.action?locationId='+reqlocationId+'"  style="padding: 10px;margin-left:250px;"> <i class="glyphicon glyphicon-home"  title="BACK TO HOME"></i> </a>');
				else
					$('.successDiv').html('<span style="font-weight: bold;text-transform: uppercase;"> '+result.status+'</span> <a class="btn btn-success btn-xs" href="committeeManagementAction.action"  style="padding: 10px;margin-left:250px;"> <i class="glyphicon glyphicon-home"  title="BACK TO HOME"></i> </a>');
			}else{
					if(reqlocationId != null && reqlocationId>0)
						$('.successDiv').html('<span style="font-weight: bold;text-transform: uppercase;"> '+result.status+'</span> <a class="btn btn-success btn-xs" href="cadreCommitteeAction.action?locationId='+reqlocationId+'"  style="padding: 10px;margin-left:250px;"> <i class="glyphicon glyphicon-home"  title="BACK TO HOME"></i> </a>');
					else
						$('.successDiv').html('<span style="font-weight: bold;text-transform: uppercase;"> '+result.status+'</span> <a class="btn btn-success btn-xs" href="cadreCommitteeAction.action"  style="padding: 10px;margin-left:250px;"> <i class="glyphicon glyphicon-home"  title="BACK TO HOME"></i> </a>');
			}
			$('#profileDiv').html('');

		}		
		else if(result.resultCode == 2){ // Not Eligible For This Committee.
					
			var redirectURL = $('#redirectURLId').val();
			$('#profileDiv').hide();
			$('#step2Id').hide();
			$('.existingDiv').hide();
			$('html,body').animate({scrollTop: $('.successDiv').offset().top}, 800);
			if(reqcommitteeMngtType == 4){
			
				if(reqlocationId != null && reqlocationId>0)
					$('#redirectURLId').html('<span style="font-weight: bold;text-transform: uppercase;"> '+result.status+'</span> <br> <a class="btn btn-success btn-xs" href="'+redirectURL+'">  CLICK HERE IF YOU WANT DESIGNATION </a><a class="btn btn-success btn-xs" href="committeeManagementAction.action?locationId='+reqlocationId+'"  style="padding: 4px;margin-left:10px;"> <i class="glyphicon glyphicon-home"  title="BACK TO HOME"></i> </a>');
				else{
					$('#redirectURLId').html('<span style="font-weight: bold;text-transform: uppercase;"> '+result.status+'</span> <br> <a class="btn btn-success btn-xs" href="'+redirectURL+'">  CLICK HERE IF YOU WANT DESIGNATION </a><a class="btn btn-success btn-xs" href="committeeManagementAction.action"  style="padding: 4px;margin-left:10px;"> <i class="glyphicon glyphicon-home"  title="BACK TO HOME"></i> </a>');
				}
				if(reqlocationId != null && reqlocationId>0)
					$('.successDiv').html('<span style="font-weight: bold;text-transform: uppercase;color:red;"> '+result.status+'</span> <br> <a class="btn btn-success btn-xs" href="'+redirectURL+'">  CLICK HERE IF YOU WANT DESIGNATION </a><a class="btn btn-success btn-xs" href="committeeManagementAction.action?locationId='+reqlocationId+'"  style="padding: 4px;margin-left:10px;"> <i class="glyphicon glyphicon-home"  title="BACK TO HOME"></i> </a>');
				else{
					$('.successDiv').html('<span style="font-weight: bold;text-transform: uppercase;color:red;"> '+result.status+'</span> <br> <a class="btn btn-success btn-xs" href="'+redirectURL+'">  CLICK HERE IF YOU WANT DESIGNATION </a><a class="btn btn-success btn-xs" href="committeeManagementAction.action"  style="padding: 4px;margin-left:10px;"> <i class="glyphicon glyphicon-home"  title="BACK TO HOME"></i> </a>');
				}
			}else{
				
				if(reqlocationId != null && reqlocationId>0)
					$('#redirectURLId').html('<span style="font-weight: bold;text-transform: uppercase;"> '+result.status+'</span> <br> <a class="btn btn-success btn-xs" href="'+redirectURL+'">  CLICK HERE IF YOU WANT DESIGNATION </a><a class="btn btn-success btn-xs" href="cadreCommitteeAction.action?locationId='+reqlocationId+'"  style="padding: 4px;margin-left:10px;"> <i class="glyphicon glyphicon-home"  title="BACK TO HOME"></i> </a>');
				else
					$('#redirectURLId').html('<span style="font-weight: bold;text-transform: uppercase;"> '+result.status+'</span> <br> <a class="btn btn-success btn-xs" href="'+redirectURL+'">  CLICK HERE IF YOU WANT DESIGNATION </a><a class="btn btn-success btn-xs" href="cadreCommitteeAction.action"  style="padding: 4px;margin-left:10px;"> <i class="glyphicon glyphicon-home"  title="BACK TO HOME"></i> </a>');
				 
				 if(reqlocationId != null && reqlocationId>0)
					$('.successDiv').html('<span style="font-weight: bold;text-transform: uppercase;color:red;"> '+result.status+'</span> <br> <a class="btn btn-success btn-xs" href="'+redirectURL+'">  CLICK HERE IF YOU WANT DESIGNATION </a><a class="btn btn-success btn-xs" href="cadreCommitteeAction.action?locationId='+reqlocationId+'"  style="padding: 4px;margin-left:10px;"> <i class="glyphicon glyphicon-home"  title="BACK TO HOME"></i> </a>');
				else
					$('.successDiv').html('<span style="font-weight: bold;text-transform: uppercase;color:red;"> '+result.status+'</span> <br> <a class="btn btn-success btn-xs" href="'+redirectURL+'">  CLICK HERE IF YOU WANT DESIGNATION </a><a class="btn btn-success btn-xs" href="cadreCommitteeAction.action"  style="padding: 4px;margin-left:10px;"> <i class="glyphicon glyphicon-home"  title="BACK TO HOME"></i> </a>');
			}
		}
		else{
			
			$("#submitCadreFormBtnReqId").show();
			$('html,body').animate({scrollTop: $('.successDiv').offset().top}, 800);
			if(reqcommitteeMngtType == 4){
				
				if(reqlocationId != null && reqlocationId>0){
					  $('.successDiv').html('<span style="font-weight: bold;text-transform: uppercase;color:red;"> '+result.status+'</span> <br><a class="btn btn-success btn-xs" href="committeeManagementAction.action?locationId='+reqlocationId+'"  style="padding: 4px;margin-left:10px;"> <i class="glyphicon glyphicon-home"  title="BACK TO HOME"></i> </a>');
				}
				else{
					  $('.successDiv').html('<span style="font-weight: bold;text-transform: uppercase;color:red;"> '+result.status+'</span> <br><a class="btn btn-success btn-xs" href="committeeManagementAction.action"  style="padding: 4px;margin-left:10px;"> <i class="glyphicon glyphicon-home"  title="BACK TO HOME"></i> </a>');
				}
			
			}else{
				if(reqlocationId != null && reqlocationId>0){
					$('.successDiv').html('<span style="font-weight: bold;text-transform: uppercase;color:red;"> '+result.status+'</span> <br><a class="btn btn-success btn-xs" href="cadreCommitteeAction.action?locationId='+reqlocationId+'"  style="padding: 4px;margin-left:10px;"> <i class="glyphicon glyphicon-home"  title="BACK TO HOME"></i> </a>');
				}
				else{
					$('.successDiv').html('<span style="font-weight: bold;text-transform: uppercase;color:red;"> '+result.status+'</span> <br><a class="btn btn-success btn-xs" href="cadreCommitteeAction.action"  style="padding: 4px;margin-left:10px;"> <i class="glyphicon glyphicon-home"  title="BACK TO HOME"></i> </a>');
				}
			}
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
	var loginAreaType ="rural";
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
				loginAreaType = result[i].description;
				}
				}
		});
	}
	function getUserLocation1(locationId){
	
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
				loginAreaType = result[i].description;
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
	
$(document).on("click",".updateMemberCls",function(){
	var tdpCadreId = $(this).attr("attr_tdp_cadre_id");
	if(confirm("Are you sure want to Delete his Current Designation?")){
		$(".dataLoadingImgCls").show();
		var jsObj = {
		   tdpCadreId :tdpCadreId
		}
	    $.ajax({
			type : "POST",
			url : "updateCommitteeMemberDesignationByCadreIdAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			if(result != null && result == "success"){
				alert("Current Designation Deleted Successfully...");
				$(".dataLoadingImgCls").hide();
				gettingBoothInchargeRoleDetails();
			}
			else{
				alert("Sorry,Exception Occured.Please Try Again...");
				$(".dataLoadingImgCls").hide();
			}
		});
	}
});

function getCadreDetailsForBoothBySearchCriteria()
	{
		//committeTypeID means 
			//for committiee management 1
			//for Mandal/Muncipality Main Committee Electoral Management 2
			//for Mandal/Muncipality Affiliated Committee Electoral Management 3
		//areaTypeId means
		    //for panchayat level 1
			//for mandal level 2
			  
		var areaTypeId  =  $('#areaTypeId').val();
		//var committeeLocationId =$("#committeeLocationId").val();
		
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
		var houseNo = $('#committeeLocationId1').val();
		$('#cadreDetailsDiv,#searchErrDiv,#committeeLocationIdErr,#committeLocationIdErr,#advancedSearchErrDiv').html('');
		$('#searchLevelErrDiv,#committeePositionIdErr,#nonAfflitCommitteeIdErr').html('');
		$("#cadreDetailsDiv").hide();
		var searchBy = $('#searchBy').val().trim();
		var searchRadioType = $('#cadreSearchType').val();
		var committeTypeID = $('#committeeMngtType').val();
		var committeePosition = $('#committeePositionId').val();
		var parentLocation = 0;
		
		$("#step3Id").hide();
		
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
		if(searchRadioType == 'name')
		{
			searchName = $('#searchBy').val().trim();
			
			if(searchBy.trim().length == 0 )
			{
				$('#searchErrDiv').html('Please enter Name.');
				return;
			}
			else if(searchBy.trim().length < 3)
			{
				$('#searchErrDiv').html('Please enter Minimum 3 Characters.');
				return;
			}
		}
		if(searchRadioType == 'advancedSearch')
		{			
			gender = $('#gender option:selected').text().trim();
			var casteGroup = $('#casteCategory').val();
			var casteName  = $('#casteList').val();
			var age = $('#ageRange').val();
			
			var locfromAge = $('#fromAgeId').val().trim();
			var loctoAge = $('#toAgeId').val().trim(); 
			
			if(casteGroup == 0 && casteName == 0 && age == 0 && gender == 'All' && locfromAge.length == 0 && loctoAge.length == 0 )
			{
				$('#advancedSearchErrDiv').html('Please Select Any of Search Criteria');
				return;			
			}			
			if($('#ageRange').val() != 0)
			{
				var ageRange = $('#ageRange option:selected').text();
				var ageRange = ageRange.split('-');
				fromAge = ageRange[0].trim();
				toAge = ageRange[1].trim();
			}
			else
			{
				fromAge = $('#fromAgeId').val().trim();
				toAge = $('#toAgeId').val().trim(); 
				
				if(fromAge.length >0 || toAge.length >0)
				{
					if(fromAge.length == 0 || toAge.length == 0)
					{
						$('#advancedSearchErrDiv').html('Please Enter Between Age Details.');
						return;	
					}
					if(fromAge > toAge){
						$('#advancedSearchErrDiv').html('From Age Should be Less than To Age.');
						return;							
					}
				}
				else
				{
					fromAge = 0;
					toAge = 0;					
				}
			}				
			casteCategory = $('#casteCategory option:selected').text().trim();
			casteStateId = $('#casteList').val().trim();
			
			if(casteCategory == 'All')
			{
				casteCategory = "";				
			}			
		}
		
		$("#searchDataImg").show();
		
		var committeeLocationId =$("#committeeLocationId").val();
		
		var jsObj =
		{
			locationLevel :4,
			locationValue:globalLocationId,
			searchName : searchName,
			mobileNo: mobileNo,
			casteCategory : casteCategory,
			fromAge : fromAge,
			toAge : toAge,
           //memberShipCardNo: "14899082",
            memberShipCardNo: memberShipCardNo,
		   casteStateId : casteStateId,
			trNumber : trNumber,
			voterCardNo:voterCardNo,
			gender:gender,
			houseNo:houseNo,
			removedStatus:"false",
			enrollmentId : 4,
			task:"search"
		}
		
		//console.log(jsObj);
		$.ajax({
				type : "POST",
				url : "getCadreSearchDetailsForBoothsCommitteeAction.action",
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
				
					buildCadreDetails(result.previousRoles,committeeLocationId);
				}
				else
				{
					$('#cadreDetailsDiv').html("<span style='font-weight:bold;text-align:center;'> No Data Available...</span>");
				}
			}); 
	}

function saveBoothDetails(tdpCadreId,isOtherRange){
	$('#addProfileId').show();
	$("#addProfileId").html('<img id="dataLoadingImg" src="images/icons/loading.gif" style="width:50px;height:40px;"/>');
	var committeeLocationId =$("#committeeLocationId1").val();
	var boothNo =$("#committeeLocationId1 option:selected").text();
	var committeePositionStr = $('#committeeDesignationId option:selected').text().trim();
	var confm = confirm(" Are you sure want to add this cadre as "+committeePositionStr+" Position for " +boothNo+ " ");
    if (confm == true) {
    } else {
		$('#addProfileId').hide();
		return;
    }
	var boothIncrgRoleId = $("#committeeDesignationId").val();
	var boothIncrgeDesiText = $("#committeeDesignationId option:selected").text();
	
	$('.addProfileCls').hide();
	var enrollmentYrIds = [];
	enrollmentYrIds.push(1);
	var jsObj =
		{
			boothId : committeeLocationId,
			tdpCadreId : tdpCadreId,
			boothIncrgRoleId:boothIncrgRoleId,
			enrollmentYrIds:enrollmentYrIds,
			isOtherRange:isOtherRange
		}
		
		$.ajax({
				type : "POST",
				url : "saveElectionBoothCommitteeDetailsAction.action",
				data : {task:JSON.stringify(jsObj)}
			}).done(function(result){
				$("#addProfileId").hide();
				 if(result.resultCode == 0){
					 alert(boothIncrgeDesiText+" added successfully.....");
					 $('#boothInchargeRoleDivId1').html('');
					/* $("#errMsgId").html("<span style='color:green;'>Member added successfully.....</span>"); */
					setTimeout(function(){
						gePanchayatOrBooth();
						updateRangeIdsOfBoothIncharge(committeeLocationId);
					},1200);
				}else if(result.resultCode == 2){
					$('.addProfileCls').show();
					alert(result.message);
					/* $("#errMsgId").html("<span style='color:green;'>Member added failed.Please try again..</span>"); */
				}else if(result.resultCode == 3){
					$('.addProfileCls').show();
					alert("No Vacancy.");
					/* $("#errMsgId").html("<span style='color:green;'>Member added failed.Please try again..</span>"); */
				}else {
					$('.addProfileCls').show();
					alert(boothIncrgeDesiText+" added failed.Please try again..")
					/* $("#errMsgId").html("<span style='color:green;'>Member added failed.Please try again..</span>"); */
				}
			});
}
$(document).on("click",".remveMbrCls",function(){
	var tdpCadreId = $(this).attr("attr_cadre_id");
	
	var jsObj =
		{
			tdpCadreId : tdpCadreId
		}
		
		$.ajax({
				type : "POST",
				url : "removeMbrFromCurentLocationAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				if(result.resultCode == 0){
					alert("Member removed successfully.....");
					$("#cadreDetailsDiv1,#cadreSerialNoWiseId").html('');
					setTimeout(function(){
						gePanchayatOrBooth();
						updateRangeIdsOfBoothIncharge(committeeLocationId);
					},1200);
				}else{
					alert("Member removed failed.Please try again.");
				}
			});
	
});	

function getBoothUserDetailsbuild(result,locationName,boothId){
	$("#userDetailsId").show();
	$("#locationDivId").html('<center><h4>'+locationName+' Committee Details.</center></h4>');
	var str = '';
	
	str +='<div class="row">';
		str +='<div class="col-sm-12">';
			str +='<a class="btn btn-xs btn-mini btn-success pull-right" href="javascript:{exportToExcel(\'rangeWiseExportBoothExportExcelReport\')}"  style="margin-bottom: 7px;"> Export Excel</a>';
		str +='</div>';
		str +='<div class="col-sm-12">';
			str +='<div class="table-responsive">';
		  str +='<table class="table table-bordered" id="rangeWiseBoothReport">';
				 str +='<thead>';
					 str +='<tr class="text-center">';
						str +='<th>MEMBERSHIP&nbsp;NO</th>';
						str +='<th>PHOTO</th>';
						str +='<th>CADRE&nbsp;NAME</th>';
						str +='<th>RELATIVE&nbsp;NAME</th>';
						str +='<th>AGE</th>';
						str +='<th>MOBILE&nbsp;NO</th>';
						str +='<th>DESIGNATION</th>';
						str +='<th>SERIAL&nbsp;NO</th>';	
						str +='<th>INCHARGE&nbsp;BOOTH&nbsp;NO</th>';
						str +='<th>OWN&nbsp;BOOTH&nbsp;NO</th>';
						str +='<th>MANDAL/MUNCI/CORP/GREATER&nbsp;CITY&nbsp;</th>';
						str +='<th>VILLAGE/WARD&nbsp;</th>';

						str +='<th> REMOVE &nbsp;</th>';		 
					 str +='</tr>';
				 str +='</thead>';			 
			  str +='<tbody>';
						for(var i in result){
							var membershipNo = result[i].memberShipNo != null ? result[i].memberShipNo+"":"-";
							//alert(membershipNo.length);
							
							str +='<tr>';
								//str +='<td>'+result[i].name+'</td>';
								if( membershipNo.length==8)
									str +='<td>'+result[i].memberShipNo+'</td>';
								else if(membershipNo.length==7)
									str +='<td>0'+result[i].memberShipNo+'</td>';
								else 
									str +='<td>  -  </td>';
									str +='<td><img src="http://www.mytdp.com/images/cadre_images/'+result[i].url+'" onerror="setDefaultImage(this);" style="width: 50px; height: 50px;"></img></td>';
									str +='<td>'+result[i].firstName+'</td>';
									str +='<td>'+result[i].committeeMemberStatus+'</td>';//relativeName
									str +='<td>'+result[i].constituencyId+'</td>';//age
									str +='<td>'+result[i].mobileNumber+'</td>';
									if(result[i].roleName != null)								
									  str +='<td>'+result[i].roleName+'</td>';
									else
									  str +='<td>  -  </td>';
								   if(result[i].serialNo != null)								
								    str +='<td>'+result[i].serialNo+'</td>';
								    str +='<td><span></span>'+'Booth&nbsp;No&nbsp;-&nbsp;'+result[i].boothNumber+'</td>';
									str +='<td><span></span>'+'Booth&nbsp;No&nbsp;-&nbsp;'+result[i].originalLocation+'</td>';
									str +='<td>'+result[i].mandalName+'</td>';
									str +='<td>'+result[i].panchayatName+'</td>';
								//str +='<td>'+result[i].boothName+'</td>';
																
								if(result[i].status != null && (result[i].status=="N" || result[i].status=="N " || result[i].status==" N" || result[i].status==" N "))	
									str +='<td><input id="deleteMembrsBtn" class="btn btn-success btn-xs" attr_booth_id='+boothId+' attr_incharge_id='+result[i].inchargeId+' attr_roleMapping_id ='+result[i].roleMappingId+' attr_role="'+result[i].roleName+'" value="DELETE" type="button"><img id="deleteImageId"></img></td>';
								else
								
                                  	str +='<td>  -  </td>';								  
								str +='</tr>';
								}
			       str +='</tbody>';
			       str +='</table>';
			  
			   str +='<table class="table table-bordered" style="display:none;" id="rangeWiseExportBoothExportExcelReport">';
				  str +='<thead>';
					 str +='<tr class="text-center">';
					 str +='<th>MEMBERSHIP&nbsp;NO</th>';
					 	str +='<th>CADRE&nbsp;NAME</th>';
						str +='<th>RELATIVE&nbsp;NAME</th>';
						str +='<th>AGE</th>';
						str +='<th>MOBILE&nbsp;NO</th>';
						str +='<th>DESIGNATION</th>';
						str +='<th>SERIAL&nbsp;NO</th>';	
                        str +='<th>INCHARGE&nbsp;BOOTH&nbsp;NO</th>';
	                    str +='<th>OWN&nbsp;BOOTH&nbsp;NO</th>';						
						str +='<th>CONSTITUENCY&nbsp;</th>';
						str +='<th>MANDAL/MUNICIPALITY/CORPORATION&nbsp;</th>';
						str +='<th>VILLAGE/WARD&nbsp;</th>';
						str +='<th>VILLAGE&nbsp;COVERED </th>';			
						//str +='<th>PHOTO</th>';
						//str +='<th> REMOVE &nbsp;</th>';
					 						 
					 str +='</tr>';
				 str +='</thead>';			 
			  str +='<tbody>';
						for(var i in result){
							var membershipNo = result[i].memberShipNo != null ? result[i].memberShipNo+"":"-";
							//alert(membershipNo.length);
							
							str +='<tr>';
							 if( membershipNo.length==8)
									str +='<td>'+result[i].memberShipNo+'</td>';
								else if(membershipNo.length==7)
									str +='<td>0'+result[i].memberShipNo+'</td>';
								else 
									str +='<td>  -  </td>';
								str +='<td>'+result[i].firstName+'</td>';
                                str +='<td>'+result[i].committeeMemberStatus+'</td>';//relativeName
								str +='<td>'+result[i].constituencyId+'</td>';//age
	                            str +='<td>'+result[i].mobileNumber+'</td>';
                                if(result[i].roleName != null)								
								  str +='<td>'+result[i].roleName+'</td>';
							    else
								  str +='<td>  -  </td>';	
                                if(result[i].serialNo != null)								
								  str +='<td>'+result[i].serialNo+'</td>';
								else
								   str +='<td> - </td>';		
                                   str +='<td><span></span>'+'Booth No - '+result[i].boothNumber+'</td>';
                                   str +='<td><span></span>'+'Booth&nbsp;No&nbsp;-&nbsp;'+result[i].originalLocation+'</td>';								   
								str +='<td>'+result[i].name+'</td>';
								str +='<td>'+result[i].mandalName+'</td>';
								str +='<td>'+result[i].panchayatName+'</td>';
								str +='<td>'+result[i].boothName+'</td>';
								//str +='<td><img src="http://www.mytdp.com/images/cadre_images/'+result[i].url+'" onerror="setDefaultImage(this);" style="width: 50px; height: 50px;"></img></td>';
								//str +='<td><input id="deleteMembrsBtn" class="btn btn-success btn-xs" attr_incharge_id='+result[i].inchargeId+' attr_roleMapping_id ='+result[i].roleMappingId+' attr_role="'+result[i].roleName+'" value="DELETE" type="button"></td>';	
							str +='</tr>';
							}
			  str +='</tbody>';
			  str +='</table>';
			str +='</div>';
		str +='</div>';
	str +='</div>';
	
		
			 // str+='</div>';
			   $("#userDetailsId").html(str);
				$("#rangeWiseBoothReport").dataTable({
						"iDisplayLength": 20,
						"aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
				});
}

function setDefaultImage(img){
    img.onerror = "";
    img.src = "images/cadre_images/human.jpg";
    return true;
  }
function exportToExcel(tableId)
{
	tableToExcel(''+tableId+'', 'Booth Committee Details.. ');
}

$(document).on("click","#deleteMembrsBtn",function(){
	$('#deleteImageId').show();
	$("#deleteImageId").html('<img id="dataLoadingImg" src="images/icons/loading.gif" style="width:50px;height:40px;"/>');
	 var roleName = $(this).attr('attr_role');
	if(!confirm(" Are You sure want to remove this "+roleName+" Position Cadre ?")){
		$('#deleteImageId').hide();
		return ;
	}
	$(this).hide();
	var inchargeId = $(this).attr("attr_incharge_id");
	var roleMappingId = $(this).attr("attr_roleMapping_id");
	var boothId = $(this).attr("attr_booth_id");
	
	var jsObj =
		{
			boothInchargeMappingId : roleMappingId,
            boothInchargeId :  inchargeId,
            boothId:0,
            boothInchargeEnrollementId:1			
		}
		   $.ajax({
				    type : "POST",
					url : "deleteRoleMemberDetailsAction.action",
					data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				$('#deleteImageId').hide();
				if(result == "delete Successfully"){
					alert(roleName+" deleted successfully.....");
					$('#boothInchargeRoleDivId1').html('');
					gettingBoothInchargeRoleDetails();
					getBoothUserDetails();
					gePanchayatOrBooth();
					updateRangeIdsOfBoothIncharge(boothId);
				}else{
					alert(roleName+" deleted failed.Please try again.");
				}
			});
	
});
//$("#cadreSerialNoWiseId").hide();
$(document).on("change","#committeeLocationId1",function(){
	$("#cadreAvailableDetailsDivId").html('');
	$("#cadreSerialNoWiseId").html('');
	$("#cadreSerialNoWiseId").show();
	$("#cadreDetailsDiv1,#cadreSerialNoWiseId").html('');
	$("#cadreDetailsDiv1").html('<img id="dataLoadingImg" src="images/icons/loading.gif" style="width:25px;height:20px;"/>');
  var boothNO = $("#committeeLocationId1").val();
  var jsObj =
		{
			locationValue:globalLocationId,
			gender:"summary",
			houseNo:boothNO
			
		}
	
		$.ajax({
				type : "POST",
				url : "getCadreVoterBthSerilNoAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				if(result != null){
					buildBoothSearchDetails(result);
				}else{
					$("#cadreDetailsDiv1").html('No Data Available');
					$("#cadreSerialNoWiseId").hide();
				}
			}); 
	
});

function buildBoothSearchDetails(result){
	$('#committeeDesigDivId1').show();
    $("#cadreDetailsDiv1").show();
//$("#cadreDetailsDiv1").html(spinner);
	$("#cadreSerialNoWiseId").html('SERIAL NO WISE CADRE DETAILS');
	var str='';
	var total=0;
	var totalMale =0;
	var totalFemale =0;
	var addedCount=0;
	 var sublist = result.casteList;
	 var subListLength=sublist.length;
	 var countList=0;
	
	str+='<table class ="table table-bordered" id="bothWiseRangeId">';
		str +='<thead>';
		str +='</thead>';
		str +='<tbody>';
		str +='<tr style="background-color:#d3d3d3">';
			str +='<th class="text-center">SERIAL NO RANGE</th>';
				str +='<th class="text-center">MALE</th>';
				str +='<th class="text-center">FEMALE</th>';
				str +='<th class="text-center">AVAILABLE CADRE</th>';
				str +='<th class="text-center">MEMBER STATUS</th>';
				str +='<th class="text-center">CONVENOR STATUS</th>';
				str +='</tr>';	
		 if(isAvailableOrNOt == "true"){
			$('#committeeDesigDivId1').show();
			for (var i in sublist){
				var isPrevious = 'Yes';
				var isNext = 'Yes' ;
	 
				if(parseInt(sublist[i].totalCount) == 0){
					if(i==parseInt(sublist.length-1)){
						 isNext = 'No';
						 isPrevious = 'Yes';
					}
					if(i==0){
						 isPrevious = 'No' ;
						 isNext = 'Yes';
					}
				}
				
				
				str +='<tr class="text-center">';
				str +='<td class="text-center "><label id="rangeId'+i+'">'+sublist[i].finalRangeStr+'</label></td>';

				if(parseInt(sublist[i].maleCount) >0){				
					str +='<td class="text-center" attr_range_id='+sublist[i].finalRangeStr+'><label class="totalClass" id="totalCountId'+countList+'" style="color:green;cursor:pointer;" attr_gender_id="M"><u>'+sublist[i].maleCount+'</u></label></td>';
				}else{
					str +='<td class="text-center" attr_range_id='+sublist[i].finalRangeStr+'><label class="" id="totalCountId'+countList+'" style="color:green;cursor:pointer;" attr_gender_id="M"> - </label></td>';
				}if(parseInt(sublist[i].femaleCount) >0){
					str +='<td class="text-center" attr_range_id='+sublist[i].finalRangeStr+'><label class="totalClass" id="totalCountId'+countList+'" style="color:green;cursor:pointer;" attr_gender_id="F"><u>'+sublist[i].femaleCount+'</u></label></td>';
				}else{
					str +='<td class="text-center" attr_range_id='+sublist[i].finalRangeStr+'><label class="" id="totalCountId'+countList+'" style="color:green;cursor:pointer;" attr_gender_id="F">  -  </label></td>';
				}if(parseInt(sublist[i].totalCount) >0){					
					str +='<td class="text-center" attr_range_id='+sublist[i].finalRangeStr+'><label class="totalClass" id="totalCountId'+countList+'" style="color:green;cursor:pointer;" attr_gender_id="0"><u>'+sublist[i].totalCount+'</u></label></td>';
				}else{
					if(parseInt(sublist[i].addedCount) == 0){
						str +='<td class="text-center" attr_range_id='+sublist[i].finalRangeStr+'><label class="" id="totalCountId'+countList+'" style="color:green;cursor:pointer;" attr_gender_id="0">  ';
						str+='<button class="btn-min btn btn-success btn-xs" id="addMemberId" range="'+sublist[i].finalRangeStr+'" isPrevious="'+isPrevious+'", isNext="'+isNext+'" > ADD MEMEBER </button>';
						str+='</label></td>';
					}else{
						str +='<td class="text-center" attr_range_id='+sublist[i].finalRangeStr+'><label class="" id="totalCountId'+countList+'" style="color:green;cursor:pointer;" attr_gender_id="0">  -  </label></td>';
					}
				}
				 if(sublist[i].alreadyRegistered == null || sublist[i].alreadyRegistered.length == 0 ){
					str +='<td class="text-center" style="color:red;"> NOT ADDED </td>';
				}else{
					if(parseInt(sublist[i].addedCount)>0){
						str +='<td class="text-center" style="color:green;" >'+sublist[i].alreadyRegistered+'('+sublist[i].addedCount+')</td>';
					
					//str +='<td class="text-center" style="color:green;" >'+sublist[i].alreadyRegistered+'('+'M'+')</td>';
					
					}else{
						
						str +='<td class="text-center" style="color:green;">'+sublist[i].alreadyRegistered+'</td>';
						
					}
				}if(sublist[i].convenerAddedCount != null && parseInt(sublist[i].convenerAddedCount) > 0 ){
					str +='<td class="text-center" style="color:green;;"> ADDED </td>';
				}else{
					str +='<td class="text-center" style=""> - </td>';
				}
				total=total+sublist[i].totalCount;
				totalMale=totalMale+sublist[i].maleCount;
				totalFemale =totalFemale+sublist[i].femaleCount;
				
				str +='</tr>';
				countList++;
			}
			
			str +='<tr class="text-center">';
			
			str +='<td class="text-center"><label class="totalClass" id="totalCountId'+0+'" attr_gender_id="0">'+'TOTAL'+'</label></td>';
				
				if(parseInt(totalMale) >0){
					str +='<td class="text-center" attr_range_id='+sublist[i].finalRangeStr+'><label class="totalClass" id="totalMailId'+countList+'" style="color:green;cursor:pointer" attr_gender_id="M"><u>'+totalMale+'</u></label></td>';
				} else{
					str +='<td class="text-center" attr_range_id='+sublist[i].finalRangeStr+'><label class="" id="totalMailId'+countList+'" style="color:green;cursor:pointer" attr_gender_id="M"> - </label></td>'; 	
				}if(parseInt(totalFemale) >0){
					str +='<td class="text-center" attr_range_id='+sublist[i].finalRangeStr+'><label class="totalClass" id="totalFmailId'+countList+'" style="color:green;cursor:pointer" attr_gender_id="F"><u>'+totalFemale+'</u></label></td>';
				}else{
					str +='<td class="text-center" attr_range_id='+sublist[i].finalRangeStr+'><label class="" id="totalFmailId'+countList+'" style="color:green;cursor:pointer" attr_gender_id="F"> - </label></td>';
				}if(parseInt(total) >0 ){
					str +='<td class="text-center" attr_range_id='+sublist[i].finalRangeStr+'><label class="totalClass" id="totalGenderId'+countList+'" style="color:green;cursor:pointer" attr_gender_id="0"><u> '+total+' </u></label></td>';
				} else{
					if(parseInt(sublist[i].addedCount) == 0){
						str +='<td class="text-center" attr_range_id='+sublist[i].finalRangeStr+'><label class="" id="totalCountId'+countList+'" style="color:green;cursor:pointer;" attr_gender_id="0">  ';
						str+='<button class="btn-min btn btn-success btn-xs" id="addMemberId" range="'+sublist[i].finalRangeStr+'" isPrevious="'+isPrevious+'", isNext="'+isNext+'" > ADD MEMEBER </button>';
						str+='</label></td>';
					}else{
						str +='<td class="text-center" attr_range_id='+sublist[i].finalRangeStr+'><label class="" id="totalCountId'+countList+'" style="color:green;cursor:pointer;" attr_gender_id="0">  -  </label></td>';
					}
				}
				str +='</tr>';
				
		}else {
			$('#committeeDesigDivId1').hide();
			for (var i in sublist){
				str +='<tr class="text-center">';
				str +='<td class="text-center "><label id="rangeId'+i+'">'+sublist[i].finalRangeStr+'</label></td>';

				if(parseInt(sublist[i].maleCount) >0){				
					str +='<td class="text-center" attr_range_id='+sublist[i].finalRangeStr+'><label class="" id="totalCountId'+countList+'" attr_gender_id="M">'+sublist[i].maleCount+'</label></td>';
				}else{
					str +='<td class="text-center" attr_range_id='+sublist[i].finalRangeStr+'><label class="" id="totalCountId'+countList+'" attr_gender_id="M"> - </label></td>';
				}if(parseInt(sublist[i].femaleCount) >0){
					str +='<td class="text-center" attr_range_id='+sublist[i].finalRangeStr+'><label class="" id="totalCountId'+countList+'" attr_gender_id="F">'+sublist[i].femaleCount+'</label></td>';
				}else{
					str +='<td class="text-center" attr_range_id='+sublist[i].finalRangeStr+'><label class="" id="totalCountId'+countList+'" attr_gender_id="F"> - </label></td>';
				}if(parseInt(sublist[i].totalCount) >0){					
					str +='<td class="text-center" attr_range_id='+sublist[i].finalRangeStr+'><label class="" id="totalCountId'+countList+'" attr_gender_id="0">'+sublist[i].totalCount+'</label></td>';
				}else{
					str +='<td class="text-center" attr_range_id='+sublist[i].finalRangeStr+'><label class="" id="totalCountId'+countList+'" attr_gender_id="0"> - </label></td>';
				}
				 if(sublist[i].alreadyRegistered == null || sublist[i].alreadyRegistered.length == 0 ){
					str +='<td class="text-center" style="color:red;"> NOT ADDED </td>';
				}else{
					if(parseInt(sublist[i].addedCount)>0){
						str +='<td class="text-center" style="color:green;" >'+sublist[i].alreadyRegistered+'('+sublist[i].addedCount+')</td>';
					
					//str +='<td class="text-center" style="color:green;" >'+sublist[i].alreadyRegistered+'('+'M'+')</td>';
					
					}else{
						
						str +='<td class="text-center" style="color:green;">'+sublist[i].alreadyRegistered+'</td>';
						
					}
				}if(sublist[i].convenerAddedCount != null && parseInt(sublist[i].convenerAddedCount) > 0 ){
					str +='<td class="text-center" style="color:green;;"> ADDED </td>';
				}else{
					str +='<td class="text-center" style=""> - </td>';
				}
				total=total+sublist[i].totalCount;
				totalMale=totalMale+sublist[i].maleCount;
				totalFemale =totalFemale+sublist[i].femaleCount;
				
				str +='</tr>';
				countList++;
			}
			
			str +='<tr class="text-center">';
			
			str +='<td class="text-center"><label class="" id="totalCountId'+0+'" attr_gender_id="0">'+'TOTAL'+'</label></td>';
				
				if(parseInt(totalMale) >0){
					str +='<td class="text-center" attr_range_id='+sublist[i].finalRangeStr+'><label class="" id="totalMailId'+countList+'" attr_gender_id="M">'+totalMale+'</label></td>';
				}else{
					str +='<td class="text-center" attr_range_id='+sublist[i].finalRangeStr+'><label class="" id="totalMailId'+countList+'" style="color:green;cursor:pointer" attr_gender_id="M"> - </label></td>';	
				}if(parseInt(totalFemale) >0){
					str +='<td class="text-center" attr_range_id='+sublist[i].finalRangeStr+'><label class="" id="totalFmailId'+countList+'" attr_gender_id="F">'+totalFemale+'</label></td>';
				}else{
					str +='<td class="text-center" attr_range_id='+sublist[i].finalRangeStr+'><label class="" id="totalFmailId'+countList+'" style="color:green;cursor:pointer" attr_gender_id="F"> - </label></td>';
				}if(parseInt(total) >0 ){
					str +='<td class="text-center" attr_range_id='+sublist[i].finalRangeStr+'><label class="" id="totalGenderId'+countList+'" attr_gender_id="0">'+total+'</label></td>';
				} else{
					if(parseInt(sublist[i].addedCount) == 0){
						str +='<td class="text-center" attr_range_id='+sublist[i].finalRangeStr+'><label class="" id="totalCountId'+countList+'" style="color:green;cursor:pointer;" attr_gender_id="0">  ';
						str+='<button class="btn-min btn btn-success btn-xs" id="addMemberId" range="'+sublist[i].finalRangeStr+'" isPrevious="'+isPrevious+'", isNext="'+isNext+'" > ADD MEMEBER </button>';
						str+='</label></td>';
					}else{
						str +='<td class="text-center" attr_range_id='+sublist[i].finalRangeStr+'><label class="" id="totalCountId'+countList+'" style="color:green;cursor:pointer;" attr_gender_id="0">  -  </label></td>';
					}
				}
				str +='</tr>';
			
		}			
				
				 
		str +='</tbody>';
	str+='</table>';
	str+='';
	$("#cadreDetailsDiv1").html(str);
} 

function getRangeDetails(range,isPrevious,isNext){
	var rangeArr = range.split("-");
	var startRange=parseInt(rangeArr[0]);
	var endRange=parseInt(rangeArr[1]);
	if(isPrevious =='Yes'){		
		if(isNext !='Yes'){//no 
			startRange = endRange-200;
		}else{
			startRange = startRange-100;
		}
		if(startRange<0)
			startRange=1;
	}
	if(isNext =='Yes'){		
		endRange = parseInt(rangeArr[1])+100;
		if(isPrevious =='Yes'){
			startRange = endRange-300;
		}
	}
	
	return startRange+' - '+endRange;
}

$(document).on("click","#addMemberId",function(){
	var addToRange = $(this).attr('range');
	var range=$(this).attr('range');
	var isPrevious=$(this).attr('isPrevious');
	var isNext=$(this).attr('isNext');
	
	var finalRange = getRangeDetails(range,isPrevious,isNext);

	$('#locationDivId').hide();
	$('#userDetailsId').hide();
	$("#searchcadrenewDiv").hide();
	$("#cadreDetailsDiv").hide();
	
		var roleId = $('#committeeDesignationId').val();
		if(parseInt(roleId) ==0){
			alert("Please select Designation.");
			return;
		}
		
	
	$('#committeeDesigDivId1').removeClass("designationHideCls");
	$('#cadreAvailableDetailsDivId').html('');
	$("#cadreAvailableDetailsDivId").html('<img id="dataLoadingImg" src="images/icons/loading.gif" style="width:50px;height:40px;"/>');
	var mandalId=$('#panchayatWardByMandal').val();
	var boothId=$('#committeeLocationId1').val();
	//var range=$(this).closest('tr').find("td:first").find('label').text();
	var gender=0;
	var count='totalGenderId'+0;
	
	if(range == "TOTAL"){
		range='0';
	}
	 var jsObj =
		{			
			mandalId:mandalId,
			boothId:boothId,
			range:finalRange	,
			gender:gender
		}
	
		$.ajax({
				type : "POST",
				url : "getSerialNoAvailbleCadreRangeWiseAction.action",
				data : {task:JSON.stringify(jsObj)}
			}).done(function(result){
				if(result != null){
					//buildCadreDetails(result,0);
					getAvailableCadreDetails(result,'other'+addToRange);
				}else{
					$('#cadreAvailableDetailsDivId').html('No Data Available....???');
				}
			}); 
			
});

$(document).on("click",".totalClass",function(){
	$('#locationDivId').hide();
	$('#userDetailsId').hide();
	$("#searchcadrenewDiv").hide();
	$("#cadreDetailsDiv").hide();
	
		var roleId = $('#committeeDesignationId').val();
		if(parseInt(roleId) ==0){
			alert("Please select Designation.");
			return;
		}
	
	$('#committeeDesigDivId1').removeClass("designationHideCls");
	$('#cadreAvailableDetailsDivId').html('');
	$("#cadreAvailableDetailsDivId").html('<img id="dataLoadingImg" src="images/icons/loading.gif" style="width:50px;height:40px;"/>');
	var mandalId=$('#panchayatWardByMandal').val();
	var boothId=$('#committeeLocationId1').val();
	var range=$(this).closest('tr').find("td:first").find('label').text();
	var gender=$(this).attr("attr_gender_id");
	var count=$(this).attr("id");
	
	if(range == "TOTAL"){
		range='0';
	}
	 var jsObj =
		{			
			mandalId:mandalId,
			boothId:boothId,
			range:range	,
			gender:gender
		}
	
		$.ajax({
				type : "POST",
				url : "getSerialNoAvailbleCadreRangeWiseAction.action",
				data : {task:JSON.stringify(jsObj)}
			}).done(function(result){
				if(result != null){
					//buildCadreDetails(result,0);
					getAvailableCadreDetails(result,range);
				}else{
					$('#cadreAvailableDetailsDivId').html('No Data Available....???');
				}
			}); 
	
});



function getAvailableCadreDetails(result,isOtherRange){
$('#cadreAvailableDetailsDivId').show();
		var str ='';
		var committeeMngntTypeId = $('#committeeMngtType').val();
		var elegRolCnt=0;
		var dtCnt = 0;
		var sublist = result.casteList;
		if(sublist != null)
		{
			str+='<div class="media"><h4 class="text-center">CADRE DETAILS</h4></div>';
			for(var i in sublist)
			{
				var addressVO = sublist[i].addressVO;
				str+='<div class="media" style="background-color: rgb(204, 204, 204); padding: 8px; border-bottom: 1px solid rgb(51, 51, 51);">';
				str+='<span href="#" class="media-left">';
				str+='<img style="width: 64px; height: 64px;" src="https://www.mytdp.com/images/cadre_images/'+sublist[i].imageURL+'" />';
				str+='</span>';
				str+='<div class="media-body">';
				str+='<h5 class="media-heading"> <span style="font-weight:bold;"> Name:</span> '+sublist[i].name+' ; ';				
				str+=' <span style="font-weight:bold;"> Relative Name: </span>'+sublist[i].relativeName+' </h5>';
				str+='<ul class="list-inline">';
				str+='<li>Age:'+sublist[i].age+';</li>';
				str+='<li>Gender: '+sublist[i].gender+'</li>';
				str+='<li>MemberShipCard No: '+sublist[i].memberShipCardId+'</li>';
				str+='<li>Mobile No: '+sublist[i].mobileNo+'</li>';
				str+='<li>Caste: '+sublist[i].casteName+'</li>';
				str+='<li>Voter ID: '+sublist[i].voterCardNo+'</li>';
				str+='<li> <span style="font-weight:bold;color:red;"> Serial No : '+sublist[i].serialNo+' </span></li>';
				  if(addressVO != null ){
						//str+='<li>Mandal : '+addressVO.mandalName+'</li>';
						str+='<li>Panchayat : '+addressVO.panchayat+'</li>';				
					}
				str+='</ul>';
				
				if(sublist[i].type != null && sublist[i].type.trim() == 'Added Member')
				{
					str+='<ul  style="color:#449D44;">';
					if(sublist[i].type != null && sublist[i].type.trim().length > 0){						
						if(sublist[i].tehsil == null )
							str+='<li style="font-weight:bold;"> Already added as  <span style="color:#000;"> '+sublist[i].roleName+'</span> Position for  Booth No - '+sublist[i].boothNumber+' ,'+sublist[i].panchayat+' Panchayat , '+sublist[i].tehsil+' Mandal. </li>';	
						else if(sublist[i].tehsil == null )
							str+='<li style="font-weight:bold;"> Already added as  <span style="color:#000;"> '+sublist[i].roleName+'</span> Position for Booth No - '+sublist[i].boothNumber+' , '+sublist[i].tehsil+' Muncipality. </li>';	
						else 
							str+='<li style="font-weight:bold;"> Already added as  <span style="color:#000;"> '+sublist[i].roleName+'</span>  Position for Booth No - '+sublist[i].boothNumber+' ,'+sublist[i].tehsil+' Mandal/Muncipality. </li>';	
					}
					str+='</ul>';
					str+='</div>';
					str+='</div>';
													
				}
				else{
					str+='</div>';
					str+='</div>';
					
					if(sublist[i].type == "Not Added" && (sublist[i].isDuplicate=="No" || sublist[i].isDuplicate==""))
					{
						str+='<div class="form-inline ">';
						str+='<a onclick="javascript:{saveBoothDetails('+sublist[i].tdpCadreId+',\''+isOtherRange+'\')}" class="btn btn-success btn-medium m_top5 addProfileCls" > ADD PROFILE</a><img id="addProfileId"></img><span id="errMsgId"></span>';
						str+='</div>';
						
					}else if(sublist[i].isDuplicate=="Yes") {
						str+='<div class="form-inline " style="color:red;" title="This cadre may or may not registered with Own voterId or Family Voter Id." > No access to add .  Because This Serial No already added as incharger.</div> ';	
					}
				}
				elegRolCnt++;
				dtCnt++;
			}
		}
		
		$('#cadreAvailableDetailsDivId').html(str);
}
		