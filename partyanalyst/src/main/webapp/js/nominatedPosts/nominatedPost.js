function getDistrictsForStates(state,id,num){

	//$(".allcls").hide();
	if(id == "statesDivId"){
			getConstituenciesForState(state,'constituencyId');
			$("#searchDataImgForDist").show();
			//refreshExistingDetails();
			$("#districtId").empty();
			$("#constituencyId").empty();
			$("#mandalList").empty();
			$("#panchaytList").empty();			
			$("#districtId").append('<option value="0">Select District</option>');		
			$("#constituencyId").append('<option value="0">Select Constituency</option>');		
			$("#mandalList").append('<option value="0">Select Mandal/Municipality</option>');		
			$("#panchaytList").append('<option value="0">Select Panchayat</option>');
			
			$("#districtId").trigger("chosen:updated");
			$("#constituencyId").trigger("chosen:updated");
			$("#mandalList").trigger("chosen:updated");
			$("#panchaytList").trigger("chosen:updated");
			
			 
	}else if(id == "changestateId"){
			getConstituenciesForState(state,'changeConstiId');
			$("#searchDataImgForDist").show();
			//refreshExistingDetails();
			$("#changedistrictId").empty();
			$("#changeConstiId").empty();
			$("#changeMandalId").empty();
			$("#changePanchyatId").empty();			
			$("#changedistrictId").append('<option value="0">Select District</option>');		
			$("#changeConstiId").append('<option value="0">Select Constituency</option>');		
			$("#changeMandalId").append('<option value="0">Select Mandal/Municipality</option>');		
			$("#changePanchyatId").append('<option value="0">Select Panchayat</option>');
			
			$("#changedistrictId").trigger("chosen:updated");
			$("#changeConstiId").trigger("chosen:updated");
			$("#changeMandalId").trigger("chosen:updated");
			$("#changePanchyatId").trigger("chosen:updated");
	}else if(id == "notCadreStateId"){
			getConstituenciesForState(state,'notCadreConstId');
			$("#searchDataImgForDist").show();
			//refreshExistingDetails();
			$("#notCadreDistId").empty();
			$("#notCadreConstId").empty();
			$("#notCadreMandlId").empty();
			$("#notCadrePanchayatId").empty();			
			$("#notCadreDistId").append('<option value="0">Select District</option>');		
			$("#notCadreConstId").append('<option value="0">Select Constituency</option>');		
			$("#notCadreMandlId").append('<option value="0">Select Mandal/Municipality</option>');		
			$("#notCadrePanchayatId").append('<option value="0">Select Panchayat</option>');
			
			$("#notCadreDistId").trigger("chosen:updated");
			$("#notCadreConstId").trigger("chosen:updated");
			$("#notCadreMandlId").trigger("chosen:updated");
			$("#notCadrePanchayatId").trigger("chosen:updated");
	}else {
		
		 state = $("#nominatedStaeId"+num).val();
			//$("#nominatedDistId  option").remove();
			$("#nominatedDistId"+num+"").empty();
			$("#nominatdConstId"+num+"").empty();
			$("#nominatedMandlId"+num+"").empty();
			$("#nominatedPanchayatId"+num+"").empty();			
			$("#nominatedDistId"+num+"").append('<option value="0">Select District</option>');		
			$("#nominatdConstId"+num+"").append('<option value="0">Select Constituency</option>');		
			$("#nominatedMandlId"+num+"").append('<option value="0">Select Mandal/Municipality</option>');		
			$("#nominatedPanchayatId"+num+"").append('<option value="0">Select Panchayat</option>');
			
			$("#nominatedDistId"+num+"").trigger("chosen:updated");
			$("#nominatdConstId"+num+"").trigger("chosen:updated");
			$("#nominatedMandlId"+num+"").trigger("chosen:updated");
			$("#nominatedPanchayatId"+num+"").trigger("chosen:updated");
			if(state == 0)
				return;
	}
	document.getElementById('membershipId').checked = true;
	
   var jsObj=
   {				
				stateId:state,
				elmtId:"districtList_d",
                type:"default",
				task:"getDistrictsForState"				
	}
    $.ajax({
          type:'GET',
          url: 'getDistrictsListForStateAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   location.reload(); 
	   }
	   if(id == "statesDivId"){
			$("#districtId").empty();
	   }else if(id == "notCadreStateId"){
		   $("#notCadreDistId").empty();
	   }else if(id == "changestateId"){
		   $("#changedistrictId").empty();
	   }else {
			$("#nominatedDistId"+num).empty();
	   }
	   
	   $("#searchDataImgForDist").hide();
     for(var i in result){
		 if(id == "statesDivId"){
			   if(result[i].id == 0){
				  $("#districtId").append('<option value='+result[i].id+'>ALL</option>');
			   }else{
				  $("#districtId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			   }
			   
		 }else if(id == "notCadreStateId"){
		   $("#notCadreDistId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		}else if(id == "changestateId"){
			$("#changedistrictId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		}else {
			$("#nominatedDistId"+num).append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		 }
	 }
	 if(id == "statesDivId"){
			$("#districtId").trigger("chosen:updated");
	 }else if(id == "notCadreStateId"){
		   $("#notCadreDistId").trigger("chosen:updated");
		}else if(id == "changestateId"){
			$("#changedistrictId").trigger("chosen:updated");
		}else{
			$("#nominatedDistId"+num).trigger("chosen:updated");
	 }
   });
  }
 function getConstituenciesForDistricts(district,id,num){
	// debugger;
	 if(id == "districtId"){
			$("#searchDataImgForConst").show();
			//refreshExistingDetails();
			$("#constituencyId").empty();
			$("#mandalList").empty();
			$("#panchaytList").empty();					
			$("#constituencyId").append('<option value="0">Select Constituency</option>');		
			$("#mandalList").append('<option value="0">Select Mandal/Municipality</option>');		
			$("#panchaytList").append('<option value="0">Select Panchayat</option>');			
			$("#constituencyId").trigger("chosen:updated");
			$("#mandalList").trigger("chosen:updated");
			$("#panchaytList").trigger("chosen:updated");
			
	 }else if(id == "notCadreDistId"){			
			$("#searchDataImgForDist").show();
			//refreshExistingDetails();
			$("#notCadreConstId").empty();
			$("#notCadreMandlId").empty();
			$("#notCadrePanchayatId").empty();					
			$("#notCadreConstId").append('<option value="0">Select Constituency</option>');		
			$("#notCadreMandlId").append('<option value="0">Select Mandal/Municipality</option>');		
			$("#notCadrePanchayatId").append('<option value="0">Select Panchayat</option>');
			
			$("#notCadreConstId").trigger("chosen:updated");
			$("#notCadreMandlId").trigger("chosen:updated");
			$("#notCadrePanchayatId").trigger("chosen:updated");
	}else if(id == "changedistrictId"){ 
			$("#searchDataImgForConst").show();
			//refreshExistingDetails();
			$("#changeConstiId").empty();
			$("#changeMandalId").empty();
			$("#changePanchyatId").empty();					
			$("#changeConstiId").append('<option value="0">Select Constituency</option>');		
			$("#changeMandalId").append('<option value="0">Select Mandal/Municipality</option>');		
			$("#changePanchyatId").append('<option value="0">Select Panchayat</option>');
			
			$("#changeConstiId").trigger("chosen:updated");
			$("#changeMandalId").trigger("chosen:updated");
			$("#changePanchyatId").trigger("chosen:updated");
	 }else{			
			$("#nominatdConstId"+num+"").empty();
			$("#nominatedMandlId"+num+"").empty();
			$("#nominatedPanchayatId"+num+"").empty();					
			$("#nominatdConstId"+num+"").append('<option value="0">Select Constituency</option>');		
			$("#nominatedMandlId"+num+"").append('<option value="0">Select Mandal/Municipality</option>');		
			$("#nominatedPanchayatId"+num+"").append('<option value="0">Select Panchayat</option>');
			
			$("#nominatdConstId"+num+"").trigger("chosen:updated");
			$("#nominatedMandlId"+num+"").trigger("chosen:updated");
			$("#nominatedPanchayatId"+num+"").trigger("chosen:updated");
	 }
	
	if(district == 0){
		getConstituenciesForState(0,'districtId');
		return;
	}
	
	document.getElementById('membershipId').checked = true;
	
	var jsObj=
   {				
				districtId:district,
				elmtId:"districtList_d",
                type:"default",
				task:"getConstituenciesForDistricts"				
	}
    $.ajax({
          type:'GET',
          url: 'getConstituenciesForADistrictAjaxAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   location.reload(); 
	   }
	   if(id == "districtId"){
			$("#constituencyId").empty();
	   }else if(id == "notCadreDistId"){
		   	$("#notCadreConstId").empty();
	   }else if(id == "changedistrictId"){
		   	$("#changeConstiId").empty();
	   }else {
			$("#nominatdConstId"+num).empty();
	   }
	   $("#searchDataImgForConst").hide();
     for(var i in result){
		 if(id == "districtId"){
		   if(result[i].id == 0){
			  $("#constituencyId").append('<option value='+result[i].id+'>ALL</option>');
		   }else{
			  $("#constituencyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		   }
		 }else if(id == "notCadreDistId"){
		   	$("#notCadreConstId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		}else if(id == "changedistrictId"){
				$("#changeConstiId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		 }else {
			  $("#nominatdConstId"+num).append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		 }
	 }
	 if(id == "districtId"){
		$("#constituencyId").trigger("chosen:updated");
	 }else if(id == "notCadreDistId"){
		   	$("#notCadreConstId").trigger("chosen:updated");
	 }else if(id == "changedistrictId"){
			$("#changeConstiId").trigger("chosen:updated");
	 }else{
		$("#nominatdConstId"+num).trigger("chosen:updated"); 
	 }
   });
  }
 function getMandalCorporationsByConstituency(num,id)
	{	
	var constituencyId  =0;
	if(id == "constituencyId"){
			$("#searchDataImgForMandl").show();
			//refreshExistingDetails();
			constituencyId = $('#constituencyId').val();
			$("#mandalList  option").remove();
			$("#mandalList").append('<option value="0">Select Mandal/Municipality</option>');
			$("#panchaytList  option").remove();
			$("#panchaytList").append('<option value="0">Select Panchayat</option>');
			document.getElementById('membershipId').checked = true;
	}else if(id == "notCadreConstId"){			
			$("#searchDataImgForDist").show();
			//refreshExistingDetails();
			$("#notCadreMandlId").empty();
			$("#notCadrePanchayatId").empty();
			constituencyId = $('#notCadreConstId').val();
			$("#notCadreMandlId").append('<option value="0">Select Mandal/Municipality</option>');		
			$("#notCadrePanchayatId").append('<option value="0">Select Panchayat</option>');
			
			$("#notCadreMandlId").trigger("chosen:updated");
			$("#notCadrePanchayatId").trigger("chosen:updated");
	}else if(id == "changeConstiId"){ 
			$("#searchDataImgForMandl").show();
			//refreshExistingDetails();
			constituencyId = $('#changeConstiId').val();
			
			$("#changeMandalId").empty();
			$("#changePanchyatId").empty();
			constituencyId = $('#changeConstiId').val();
			$("#changeMandalId").append('<option value="0">Select Mandal/Municipality</option>');		
			$("#changePanchyatId").append('<option value="0">Select Panchayat</option>');
			
			$("#changeMandalId").trigger("chosen:updated");
			$("#changePanchyatId").trigger("chosen:updated");
	}else{
		 constituencyId = $("#nominatdConstId"+num).val();
			$("#nominatedMandlId"+num+"  option").remove();
			$("#nominatedMandlId"+num).append('<option value="0">Select Mandal/Municipality</option>');
			$("#nominatedPanchayatId"+num+" option").remove();
			$("#nominatedPanchayatId"+num).append('<option value="0">Select Panchayat</option>');
	}
			
				var jsObj ={					
					constituencyId:constituencyId
				};
				 $.ajax({
					type : "GET",
					url : "getMandalDetailsByConstituencyAction.action",
					data : {task:JSON.stringify(jsObj)} 
				}).done(function(result){
				if(result !=null)
				{
					if(id == "constituencyId"){
						$("#mandalList").empty();
						//$("#mandalList").append('<option value="0">Select Mandal</option>');
					}else if(id == "notCadreConstId"){
						$("#notCadreMandlId").empty();						
					}else if(id == "changeConstiId"){
						$("#changeMandalId").empty();						
					}else{
						$("#nominatedMandlId"+num).empty();
					}
					for(var i in result)
					{
						if(id == "constituencyId"){
							if(result[i].locationId == 120 || result[i].locationId == 1124)
								$("#mandalList").append('<option value="0">Select Mandal/Muncipality</option>');
							$("#mandalList").append('<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>');
						}else if(id == "notCadreConstId"){
							if(result[i].locationId == 120 || result[i].locationId == 1124)
								$("#notCadreMandlId").append('<option value="0">Select Mandal/Muncipality</option>');
								$("#notCadreMandlId").append('<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>');		
						}else if(id == "changeConstiId"){
							if(result[i].locationId == 120 || result[i].locationId == 1124)
								$("#changeMandalId").append('<option value="0">Select Mandal/Muncipality</option>');
							$("#changeMandalId").append('<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>');
						}else{
							if(result[i].locationId == 120 || result[i].locationId == 1124)
								$("#nominatedMandlId"+num).append('<option value="0">Select Mandal/Muncipality</option>');
							$("#nominatedMandlId"+num).append('<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>');
						}
					}	
				}
				if(id == "constituencyId"){
					$("#mandalList").trigger("chosen:updated");
				}else if(id == "notCadreConstId"){
					$("#notCadreMandlId").trigger("chosen:updated");
				}else if(id == "changeConstiId"){
					$("#changeMandalId").trigger("chosen:updated");
				}else{
					$("#nominatedMandlId"+num).trigger("chosen:updated");
				}				
				});
	}
function getPanchayatWardByMandal(num,id){ 
			var mandalId=0;
			var constituencyId = 0; //cadreSearchDtls
		if(id == "mandalList"){			
				$("#searchDataImgForPanc").show();
				//refreshExistingDetails();
				mandalId=$("#mandalList").val();
				constituencyId = $('#constituencyId').val();
				$("#panchaytList  option").remove();
				$("#panchaytList").append('<option value="0">Select Panchayat</option>');
		}else if(id == "notCadreMandlId"){			
			$("#searchDataImgForDist").show();
			//refreshExistingDetails();
			$("#notCadrePanchayatId").empty();
			mandalId=$("#notCadreMandlId").val();
			constituencyId = $('#notCadreConstId').val();		
			$("#notCadrePanchayatId").append('<option value="0">Select Panchayat</option>');
			
			$("#notCadrePanchayatId").trigger("chosen:updated");
		}else if(id == "changeMandalId"){
			
				$("#searchDataImgForPanc").show();
				//refreshExistingDetails();
				 mandalId=$("#changeMandalId").val();
				 constituencyId = $('#changeConstiId').val();
				$("#changePanchyatId  option").remove();
				//$("#changePanchyatId").append('<option value="0">Select Panchayat</option>');
		}else{
				 mandalId=$("#nominatedMandlId"+num).val();
				 constituencyId = $("#nominatdConstId"+num).val();
				$("#nominatedPanchayatId"+num+"  option").remove();
				$("#nominatedPanchayatId"+num).append('<option value="0">Select Panchayat</option>');
		}
			var jsObj={
				mandalId:mandalId,
				constituencyId : constituencyId
			}
			$.ajax({
				type : "POST",
				url : "getPanchayatWardByMandalAction.action",
				data : {task:JSON.stringify(jsObj)} 
			}).done(function(result){
				if(id == "mandalList"){
						$("#panchaytList").empty();
						//$("#panchaytList").append('<option value="0">Select Panchayat</option>');
				}else if(id == "notCadreMandlId"){
					$("#notCadrePanchayatId").empty();
				}else if (id == "changeMandalId"){
					$("#changePanchyatId").empty();
				}else {
					$("#nominatedPanchayatId"+num).empty();
				}
			for(var i in result){
				if(id == "mandalList"){
					$("#panchaytList").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
				}else if(id == "notCadreMandlId"){
					$("#notCadrePanchayatId").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
				}else if (id == "changeMandalId"){
					$("#changePanchyatId").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
				}else{
					$("#nominatedPanchayatId"+num).append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
				}
			}
			if(id == "mandalList"){
				$("#panchaytList").trigger("chosen:updated");	
			}else if(id == "notCadreMandlId"){
					$("#notCadrePanchayatId").trigger("chosen:updated");	
			}else if (id == "changeMandalId"){
				$("#changePanchyatId").trigger("chosen:updated");	
			}else{
				$("#nominatedPanchayatId"+num).trigger("chosen:updated");
			}
		});	
			
	}
	getConstituenciesForState(0);
     function getConstituenciesForState(state,fieldId){	
  
   $("#searchDataImgForConst").show();
   var jsObj=
   {				
				stateId:state,
				elmtId:"stateList",
                type:"default",
				task:"getConstituenciesForState"				
	}
    $.ajax({
          type:'GET',
          url: 'getConstituenciesForStateAjaxAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
		$("#searchDataImgForConst").hide();
	    $("#"+fieldId+"").empty();
	   
     for(var i in result){
	   if(fieldId == "constituencyId" && result[i].id == 0)
         $("#"+fieldId+"").append('<option value='+result[i].id+'>ALL</option>');
	   else
	     $("#"+fieldId+"").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	 }	 
	  $("#"+fieldId+"").trigger("chosen:updated");
   });
  }
function getAllCadreInPanchayat()
	{
		//document.getElementById('allId').checked = false;
		document.getElementById('membershipId').checked = true;
		//$(".allcls").show();
		refreshExistingDetails();
	} 
	
	var isFree =true;
function getNominatedPostApplication(startIndex)
		{
		if(isFree){
			 isFree =false;
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
		var membershipAndMobileNo = '';

		
			$('#cadreSearchDtls').html(' <img style="margin-left: 400px; margin-top: 20px; width: 200px; height: 150px;" id="" class="offset7" src="images/icons/cadreSearch.gif">');
	
	$("#scrollDivId").show();
	   
	if(startIndex == 0)
	{
		$(".paginationDivId").html('');
	}
		$(".paginationDivId").hide();	
		
		$('#searchLevelErrDiv,#committeePositionIdErr,#nonAfflitCommitteeIdErr').html('');
		$("#cadreDetailsDiv").hide();
		//$("#cadreSearchDtls").show();
		var searchBy = $('#searchBy').val().trim();
		var searchRadioType =$('#cadreSearchType').val();
		var parentLocation = 0;
		var panchayatId = $("#panchaytList").val();
		var mandalId = $("#mandalList").val();
		var constituencyId = $("#constituencyId").val();
		var districtId = $("#districtId").val();
		
		if(panchayatId !=0)
		{
			if(panchayatId.substr(0,1) == 1){
				  locationLevel = 6;
			}
			else if(panchayatId.substr(0,1) == 2){
				 locationLevel = 8;
				 
			}								
			locationValue = panchayatId.substr(1);
		}
		else if(mandalId !=0)
		{
			if(mandalId.substr(0,1) == 1){
				 locationLevel = 7;
			}
			else if(mandalId.substr(0,1) == 2){
				 locationLevel = 5;
			}
			else if(mandalId.substr(0,1) == 3){
				 locationLevel = 8;
			}
			locationValue = mandalId.substr(1);
		}
		
		else if(constituencyId != 0)
		{
			locationValue = constituencyId;
			locationLevel = 4;	
		}
		else if(districtId != 0)
		{
			locationValue = districtId;
			locationLevel = 3;
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
		if(searchRadioType == 'trNo')
		{
			trNumber = $('#searchBy').val().trim();
			
			if(searchBy.trim().length == 0 )
			{
				$('#searchErrDiv').html('Please enter trNo.');
				return;
			}
			
		}
		
		var removedStatus =false;
		if($('#onlyCandidatesId').is(':checked')){
			 removedStatus = true;
		 }
		
		$("#searchDataImg").show();

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
			startIndex:startIndex,
			maxIndex : 50,
			removedStatus:removedStatus,
			task:"NominatedPostSearch"
		}
		$.ajax({
				type : "POST",
				url : "getCadreSearchDetailsAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				 isFree =true;
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
					buildCadreDetails(result.previousRoles);
				}
				 else
				{
					
					$('#cadreDetailsDiv').html("<span style='font-weight:bold;text-align:center;'> No Data Available...</span>");
				} 
			});  
		}
	}
	
   function buildCadreDetails(result){ 

	   //$("#textId").show();
	    $("#cadreSearchDtls").html('');
		$("#cadreSearchDtls").show();
		$("#scrollDivId").show();
		var str='';
		var str1='';
		

		$("#textId").show();
		str1+='<h4 class="m_0 text-success">APPLICANT PROFILE DETAILS : </h4>';
		str1+='<p>Search Results: <b><u id="cadreSearchSize">'+result.length+'</u></b> Members</p>';
		$("#searchData1").html(str1);
		
			
		if(result != null && result.length >0){
		for(var i in result)
			{	
				if(result[i].nominatedPostCandidateId != null && result[i].nominatedPostCandidateId >0 )
					str +='<li  style="background:lightgrey">';
				else
					str +='<li>';
				
				str +='<div class="img-center">';
				str +='<img style="width: 70px;height:70px;border:1px solid #ddd;" src="http://mytdp.com/images/cadre_images/'+result[i].imageURL+'" class="img-responsive img-circle" alt="Profile"/>';
				
				//str+='<img src="dist/img/profile.png" class="img-responsive img-circle" alt="Profile"/>';
				str +='</div>';
				
				if(result[i].id != null){ // no cadre search  candidate id
					str +='<input type="checkbox" attr_cadreId="'+result[i].id+'" class="cadreCls checkboxCls hideShowDivCls" name="checkbox" style="margin:auto;display:block;" id="appProfCheckBoxId" attr_nominated_post_candidate_id="'+result[i].tdpCadreId+'" attr_membership_id="'+result[i].memberShipCardId+'" />';
				}else{
						// cadre search  candidate id
						if(result[i].nominatedPostCandidateId == null){
							result[i].nominatedPostCandidateId = 0;
						}
						if(result[i].nominatedPostCandidateId != null && result[i].nominatedPostCandidateId >0 ){
							str +='<input type="checkbox" attr_cadreId="'+result[i].tdpCadreId+'" class="cadreCls checkboxCls hideShowDivCls" name="checkbox" style="margin:auto;display:block;" id="appProfCheckBoxId" attr_nominated_post_candidate_id="'+result[i].nominatedPostCandidateId+'" attr_membership_id="'+result[i].memberShipCardId+'" />';
						}else{
							str +='<input type="checkbox" attr_cadreId="'+result[i].tdpCadreId+'" class="cadreCls checkboxCls hideShowDivCls" name="checkbox" style="margin:auto;display:block;" id="appProfCheckBoxId" attr_nominated_post_candidate_id="'+result[i].tdpCadreId+'" attr_membership_id="'+result[i].memberShipCardId+'" />';
						}
						
				}
			   // str +='<input type="checkbox" style="margin:auto;display:block;" class="" />';
				str +='<p class="m_0 m_top5 text-center cadreName" value='+result[i].cadreName+'><b>'+result[i].cadreName+'</b></p>';
				str +='<p class="m_0 m_top5 text-center cadreVotrCardId" value="'+result[i].voterCardNo+'"><b>VOTERID : </b> '+result[i].voterCardNo+'</p>';
				if(result[i].memberShipCardId != null && result[i].memberShipCardId != "")
				str +='<p class="m_0 text-center cadreMembrShpNo" value="'+result[i].memberShipCardId+'"><b> MEMBERSHIP : </b> '+result[i].memberShipCardId+'</p>';
				str +='<p class="m_0 text-center cadreMobilNo" value="'+result[i].mobileNo+'"><b>MOBILE : </b> '+result[i].mobileNo+'</p>';
				str +='<input type="hidden" class="tdpCadreIdCls" value="'+result[i].tdpCadreId+'"/>';
				
					if(result[i].addressVO != null && result[i].addressVO.constituencyName != null && result[i].addressVO.constituencyName.length > 0)
					{
						str +='<p class="text-center m_0"><b> ASSEMBLY : </b> '+result[i].addressVO.constituencyName+'</p>';
						str +='</li>';
					}else if(result[i].constituency != null && result[i].constituency.length > 0){
						 str +='<p class="text-center m_0">'+result[i].constituency+'</p>';
						str +='</li>';
					}
				 
			}
		}else{
				str+='No Data Available';	
		}
		
		$("#cadreSearchDtls").html(str);
		/*if(id == 2)
			$("#cadreSearchDtls").append(str);
		else
			$("#cadreSearchDtls").html(str);*/
	}
	function refreshExistingDetails(){ 
		$("#searchBy").val("");
		//$("#cadreDetailsDiv").html("");
		$(".paginationDivId").html('');
		//$("#cadreDetailsDiv").hide();
		$("#searchErrDiv").html("");
		$("#membrshipAndMobileNoDiv").html("");
		$("#cadreSearchSize").hide();
		//$("#cadreSearchDtls").html("");
		$("#searchData").html("");
		$("#searchData1").html("");
		$("#notCadreNameId").val("");
		$("#notCadreVoterId").val("");
		$("#notCadreMobilNoId").val("");
		$("#notCadreErrMsg").html("");  
		$("#searchById").val("");
		$("#searchErrDiv1").html("");
		}
	function refreshExistingDetailsInNominatedLevel()
	{
		$('#boardLvlId').val(0).trigger('chosen:updated');
		$('#nominatedStaeId').val(0).trigger('chosen:updated');
		$("#nominatedDistId").val(0).trigger("chosen:updated");
		$("#nominatdConstId").val(0).trigger("chosen:updated");
		$("#nominatedMandlId").val(0).trigger("chosen:updated");
		$("#nominatedPanchayatId").val(0).trigger("chosen:updated");
		$("#depmtsId").val(0).trigger("chosen:updated");
		$("#deptBoardId").val(0).trigger("chosen:updated");
		$("#deptBoardPostnId").val(0).trigger("chosen:updated");
		$("#statesDivId").val(0).trigger("chosen:updated");
		$("#districtId").val(0).trigger("chosen:updated");
		$("#constituencyId").val(0).trigger("chosen:updated");
		$("#mandalList").val(0).trigger("chosen:updated");
		$("#panchaytList").val(0).trigger("chosen:updated");
		$("#addOneMoreBlock").html("");
	    $("#scrollDivId").hide();
	    $("#textId").hide();
		$("#cadreSearchSize").hide(); 
        $("#searchData1").html("");
		$("#searchErrDiv").hide();
		
		}
getDistricts();
function getDistricts(){
	$("#searchDataImgForDist").show();
     var jsObj=
		{				
				stateId:1,
				elmtId:"districtList_d",
                type:"default",
				task:"findDistrictsForAState"				
		}
    $.ajax({
          type:'GET',
          url: 'getDistrictsForAStateAjaxAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
   $("#searchDataImgForDist").hide();
    
   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   location.reload(); 
	   }
     for(var i in result){
	   if(result[i].id == 0){
          $("#districtId").append('<option value='+result[i].id+'>ALL</option>');
	   }else{
	      $("#districtId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }
	 }
	  $("#districtId").trigger("chosen:updated");
   });
  } 
$('.searchTypeCls').click(function(){
			var id = $(this).attr('id');
				
			$('#advancedSearchDiv').hide();			
			$('#basicSearchDiv').show();
			$('#committeLocationsDiv').hide();
		
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
			
			if(id.trim() == 'name')
			{
				$('#cadreSearchType').val('name');
			}
			if(id.trim() == 'trNo')
			{	
				$('#cadreSearchType').val('trNo');
			}
			if(id.trim() == 'membershipIdAndMobileNo')
			{	
				$('#cadreSearchType').val('membershipIdAndMobileNo');
			}
		}); 
	function getBoardLevels(id){
	
	var jsObj = {}
    $.ajax({
          type:'GET',
          url: 'getBoardLevelsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
   $("#"+id).empty();
    
   if(result != null && result.length >0){
		 $("#"+id).append('<option value="0">Select Post Level </option>');
     for(var i in result){
		 if(result[i].id != 7)
			$("#"+id).append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	 }
   }
	  $("#"+id).trigger("chosen:updated");
   });
  }
  
  function getDepartmentBoardPositions(num){
	//$("#searchDataImgForDist").show();
   
	var jsObj = {
		
		depmtId : $("#depmtsId"+num).val(),
		boardId : $("#deptBoardId"+num).val(),
		boardLevelId : $("#boardLvlId"+num).val()
	}
    $.ajax({
          type:'GET',
          url: 'getDepartmentBoardPositionsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
    $("#deptBoardPostnId"+num).empty();
   if(result != null && result.length >0){
	  $("#deptBoardPostnId"+num).append('<option value=" ">Select Board Position</option>');
	   $("#deptBoardPostnId"+num).append('<option value="0">Any</option>');
		for(var i in result){
			$("#deptBoardPostnId"+num).append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	  }
   }
	  $("#deptBoardPostnId"+num).trigger("chosen:updated");
   });
  }
  
    function getDepartmentBoards(num){
	//$("#searchDataImgForDist").show();
   var jsObj = {
		depmtId : $("#depmtsId"+num).val(),
		boardLevelId : $("#boardLvlId"+num).val()
	}
    $.ajax({
          type:'GET',
          url: 'getDepartmentBoardsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
   //$("#searchDataImgForDist").hide();
  $("#deptBoardId"+num).empty();
   if(result != null && result.length >0){
	       $("#deptBoardId"+num).append('<option value=" ">Select Department Board</option>');
		   $("#deptBoardId"+num).append('<option value="0">Any</option>');
		for(var i in result){
			$("#deptBoardId"+num).append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	  }
   }
	  $("#deptBoardId"+num).trigger("chosen:updated");
   });
  }  
  function getDepartments(num){
	//$("#searchDataImgForDist").show();
	 var postTypeId=0;
	 var boardLevelId = $("#boardLvlId"+num).val();
     var isActive = $("#nomintdPostId"+num).hasClass("btnActive");
	   if(isActive){
		 postTypeId = $("#nomintdPostId"+num).attr("attr_postid");  
	   }else{
		postTypeId = $("#partyPostId"+num).attr("attr_postid");     
	   }
	var jsObj = {
		postType:postTypeId,
		boardLevelId:boardLevelId
		}
    $.ajax({
          type:'GET',
          url: 'getDepartmentsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	  
	   $("#deptBoardId"+num).html('');
	   $("#deptBoardId"+num).trigger("chosen:updated")
	   $("#deptBoardPostnId"+num).html('');
	   $("#deptBoardPostnId"+num).trigger("chosen:updated")
       $("#depmtsId"+num).html('');
	   $("#depmtsId"+num).trigger("chosen:updated")
    
   if(result != null && result.length >0){
		$("#depmtsId"+num).append('<option value=" ">Select Department</option>'); 
		$("#depmtsId"+num).append('<option value="0">Any</option>'); 
     for(var i in result){
	   $("#depmtsId"+num).append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	 }
   }
	  $("#depmtsId"+num).trigger("chosen:updated");
   });
  }
getBoardLevels("boardLvlId"); 
//getDepartments("",1); 
/* $(document).on("click",".checkboxCls",function(){
	
    $(".checkboxCls").prop( "checked" ,false);
	//$("#uploadFlDivId").hide();
	$( this ).prop( 'checked', true );
	//$("#uploadFlDivId").show();
}) */
function showHideByNominatedPost(num)
{
	var selectVal = $("#boardLvlId"+num).val();
	if(selectVal==0||selectVal==1)
	{
	$("#statesShowDivId"+num).hide();
	$("#nominatedStaeId"+num).hide();
	$("#districtShowDivId"+num).hide();
	$("#nominatedDistId"+num).hide();
	$("#constituencyshowDivId"+num).hide();
	$("#nominatdConstId"+num).hide();
	$("#mondalShowDivId"+num).hide();
	$("#nominatedMandlId"+num).hide();
	$("#panchayatShowDivId"+num).hide();
	$("#nominatedPanchayatId"+num).hide();
	
	}
else if(selectVal==2)
{
	$("#statesShowDivId"+num).show();
	$("#nominatedStaeId"+num).hide();
	$("#districtShowDivId"+num).hide();
	$("#nominatedDistId"+num).hide();
	$("#constituencyshowDivId"+num).hide();
	$("#nominatdConstId"+num).hide();
	$("#mondalShowDivId"+num).hide();
	$("#nominatedMandlId"+num).hide();
	$("#panchayatShowDivId"+num).hide();
	$("#nominatedPanchayatId"+num).hide();
}else if(selectVal==3)
{
	$("#statesShowDivId"+num).show();
	$("#nominatedStaeId"+num).hide();
	$("#districtShowDivId"+num).show();
	$("#nominatedDistId"+num).hide();
	$("#constituencyshowDivId"+num).hide();
	$("#nominatdConstId"+num).hide();
	$("#mondalShowDivId"+num).hide();
	$("#nominatedMandlId"+num).hide();
	$("#panchayatShowDivId"+num).hide();
	$("#nominatedPanchayatId"+num).hide();
}else if(selectVal==4)
{
    $("#statesShowDivId"+num).show();
	$("#nominatedStaeId"+num).hide();
	$("#districtShowDivId"+num).show();
	$("#nominatedDistId"+num).hide();
	$("#constituencyshowDivId"+num).show();
	$("#nominatdConstId"+num).hide();
	$("#mondalShowDivId"+num).hide();
	$("#nominatedMandlId"+num).hide();
	$("#panchayatShowDivId"+num).hide();
	$("#nominatedPanchayatId"+num).hide();	
}else if(selectVal==5||selectVal==6)
{
	$("#statesShowDivId"+num).show();
	$("#nominatedStaeId"+num).hide();
	$("#districtShowDivId"+num).show();
	$("#nominatedDistId"+num).hide();
	$("#constituencyshowDivId"+num).show();
	$("#nominatdConstId"+num).hide();
	$("#mondalShowDivId"+num).show();
	$("#nominatedMandlId"+num).hide();
	$("#panchayatShowDivId"+num).hide();
	$("#nominatedPanchayatId"+num).hide();
}else if(selectVal=7)
{
	$("#statesShowDivId"+num).show();
	$("#nominatedStaeId"+num).hide();
	$("#districtShowDivId"+num).show();
	$("#nominatedDistId"+num).hide();
	$("#constituencyshowDivId"+num).show();
	$("#nominatdConstId"+num).hide();
	$("#mondalShowDivId"+num).show();
	$("#nominatedMandlId"+num).hide();
	$("#panchayatShowDivId"+num).show();
	$("#nominatedPanchayatId"+num).hide();
}
	
	
}
$(document).on("click",".iconClose",function(){
	$(this).closest(".addBlockNew").remove();
});
var cloneCount=1;
$(document).on("click","#addOneMore",function(){
	$(".errorMsgCls").html("");
  var e = $("#cloneDivBlock").clone();
  e.removeClass("cloneBlockDiv");
  e.attr("id",'block'+cloneCount);
  e.attr("attr_count",cloneCount);
  e.show();
  e.find(".nominatdPostSelCls").attr("id","nomintdPostId"+cloneCount);
	// e.find(".nominatdPostSelCls").attr("onClick",'getDepartments('+cloneCount+',1);');
  e.find(".partyPostSelCls").attr("id","partyPostId"+cloneCount);
 // e.find(".partyPostSelCls").attr("onClick",'getDepartments('+cloneCount+',2);');
  e.find(".iconClose").show();
  
  e.find(".postTypeCls").attr("attr_no",cloneCount);
  e.find(".postTypeCls").attr("id","postTypeId"+cloneCount);
  e.find(".postTypeCls").attr("name",'nominatedPostVO.nominatdList['+cloneCount+'].postTypeId');
  
  e.find(".boardLvlCls").attr("name",'nominatedPostVO.nominatdList['+cloneCount+'].boardLevelId');
  e.find(".boardLvlCls").attr("id","boardLvlId"+cloneCount);
  e.find(".boardLvlCls").attr("attr_no",cloneCount);
  getBoardLevels("boardLvlId"+cloneCount);
  e.find(".boardLvlCls").attr("onChange",'showHideByNominatedPost('+cloneCount+');getDepartments('+cloneCount+');');
 // e.find(".boardLvlCls").attr("onChange",'');
  
  e.find(".nominatedStaeCls").attr("name",'nominatedPostVO.nominatdList['+cloneCount+'].stateId');
  e.find(".nominatedStaeCls").attr("id","nominatedStaeId"+cloneCount);
  e.find(".nominatedStaeCls").attr("attr_no",cloneCount);
  e.find(".stateShowCls").attr("id","statesShowDivId"+cloneCount);
  e.find(".nominatedStaeCls").attr("onChange",'getDistrictsForStates(this.value,nominatedStaeId'+cloneCount+','+cloneCount+');');
  
  e.find(".nominatedDistCls").attr("name",'nominatedPostVO.nominatdList['+cloneCount+'].districtId');
  e.find(".nominatedDistCls").attr("id","nominatedDistId"+cloneCount);
  e.find(".nominatedDistCls").attr("attr_no",cloneCount);
  e.find(".districtShowCls").attr("id","districtShowDivId"+cloneCount);
  e.find(".nominatedDistCls").attr("onChange",'getConstituenciesForDistricts(this.value,nominatedDistId'+cloneCount+','+cloneCount+');');
  
  e.find(".nominatdConstCls").attr("name",'nominatedPostVO.nominatdList['+cloneCount+'].constituencyId');
  e.find(".nominatdConstCls").attr("id","nominatdConstId"+cloneCount);
  e.find(".nominatdConstCls").attr("attr_no",cloneCount);
  e.find(".constituencyShowCls").attr("id","constituencyshowDivId"+cloneCount);
  e.find(".nominatdConstCls").attr("onChange",'getMandalCorporationsByConstituency('+cloneCount+',nominatdConstId'+cloneCount+');');
  
  e.find(".nominatedMandlCls").attr("name",'nominatedPostVO.nominatdList['+cloneCount+'].mandalId');
  e.find(".nominatedMandlCls").attr("id","nominatedMandlId"+cloneCount);
  e.find(".nominatedMandlCls").attr("attr_no",cloneCount);
  e.find(".mandalShowCls").attr("id","mondalShowDivId"+cloneCount);
  e.find(".nominatedMandlCls").attr("onChange",'getPanchayatWardByMandal('+cloneCount+',nominatedMandlId'+cloneCount+');');
   
  e.find(".nominatedPanchayatCls").attr("name",'nominatedPostVO.nominatdList['+cloneCount+'].panchayatId');
  e.find(".nominatedPanchayatCls").attr("id","nominatedPanchayatId"+cloneCount);
   e.find(".nominatedPanchayatCls").attr("attr_no",cloneCount);
  e.find(".panchayatShowCls").attr("id","panchayatShowDivId"+cloneCount);
  
  e.find(".depmtsCls").attr("name",'nominatedPostVO.nominatdList['+cloneCount+'].deptId');  
  e.find(".depmtsCls").attr("id","depmtsId"+cloneCount);
  e.find(".depmtsCls").attr("attr_no",cloneCount);
  //getDepartments(cloneCount,1);
  e.find(".depmtsCls").attr("onChange",'getDepartmentBoards('+cloneCount+',1);');
  
  e.find(".deptBoardCls").attr("name",'nominatedPostVO.nominatdList['+cloneCount+'].deptBoardId');
  e.find(".deptBoardCls").attr("id","deptBoardId"+cloneCount);
  e.find(".deptBoardCls").attr("attr_no",cloneCount);
  e.find(".deptBoardCls").attr("onChange",'getDepartmentBoardPositions('+cloneCount+');');
  
  e.find(".deptBoardPostnCls").attr("name",'nominatedPostVO.nominatdList['+cloneCount+'].positions');
  e.find(".deptBoardPostnCls").attr("id","deptBoardPostnId"+cloneCount);
  e.find(".deptBoardPostnCls").attr("multiple","multiple");
  e.find(".deptBoardPostnCls").attr("attr_no",cloneCount);
  $("#addOneMoreBlock").append(e);
  
  var boardlvl= "boardLvlId"+cloneCount;
  $("#"+boardlvl).chosen();
  
  var nominatedState= "nominatedStaeId"+cloneCount;
  $("#"+nominatedState).chosen();
  
  var nomintdDist= "nominatedDistId"+cloneCount;
  $("#"+nomintdDist).chosen();
  
  var nomintedConst= "nominatdConstId"+cloneCount;
  $("#"+nomintedConst).chosen();
   
  var nomintdMandl= "nominatedMandlId"+cloneCount;
  $("#"+nomintdMandl).chosen();
  
  var nomintdPanchyt= "nominatedPanchayatId"+cloneCount;
  $("#"+nomintdPanchyt).chosen();
  
  var depts= "depmtsId"+cloneCount;
  $("#"+depts).chosen();
  
  var deptBrd= "deptBoardId"+cloneCount;
  $("#"+deptBrd).chosen();
  
  var deptBrdPostn= "deptBoardPostnId"+cloneCount;
  $("#"+deptBrdPostn).chosen();
  
  cloneCount=cloneCount+1;
});
function savingApplication(){
	 $('#notCadreErrMsg').html("");
	var flag = true;
	
	/*  $(".cadreCheckCls").each(function(){
				if($(this).prop('checked')==true && $(this).val() == "Cadre"){
					if(!searchByApplicant()){
						 flag = false;
					}
				}
				
			}); */
			if(flag){
					if(!validatationFields()){
						 flag = false;
					}
			}
				
			 var value = $("input[name='checkBoxName']:checked").val();
			 $("#candidateTypeId").val(value);
			 
			var cadreName ;
			var cadreId;
			var cadreVoterId ;
			var cadreMobilNo;
			
			$(".cadreCheckCls").each(function(){
				if($(this).is(":checked")==true && $(this).val() == "Cadre"){
					$(".checkboxCls").each(function(){
						if($(this).is(":checked")){
							cadreName = $(this).parent().find(".cadreName").text();
							cadreId = $(this).parent().find(".tdpCadreIdCls").attr("value");
							cadreVoterId = $(this).parent().find(".cadreVotrCardId").attr("value");
							cadreMobilNo = $(this).parent().find(".cadreMobilNo").attr("value");
							$(".tdpCadreId").val(cadreId);
							$(".tdpCadreName").val(cadreName);
							$(".cadreVoterId").val(cadreVoterId);
							$(".cadreMobileNo").val(cadreMobilNo);
						}
					});	
				}else if($(this).is(":checked")==true && $(this).val() == "Not Cadre")
				{
					
					$(".checkboxCls").each(function(){
						if($(this).is(":checked")){
							cadreId = $(this).parent().find(".tdpCadreIdCls").attr("value");
							$(".nominatedCandId").val(cadreId);
					}
					});	
				}
			
		});  
		var candidateId="";
		$(".checkedReffrl").each(function(){
						if($(this).is(":checked")){
						if($(this).val() != null && $(this).val().length > 0)	
						candidateId += $(this).val()+",";	
						}
		});
	
	var n=candidateId.lastIndexOf(",");
    candidateId=candidateId.substring(0,n) ;
	
			$(".referCadreIds").val(candidateId); 
			
			var uploadHandler = {
				upload: function(o) {
					$("#savingAjaxImg").css("display","none");
					uploadResult = o.responseText;
					showSbmitStatus(uploadResult);
				}
			};
		$(".boardLvlCls").each(function(){
			 var num = $(this).attr("attr_no");
			 var isActive = $("#nomintdPostId"+num).hasClass("btnActive");
			   if(isActive){
				 postTypeId = $("#nomintdPostId"+num).attr("attr_postid");  
				 $("#postTypeId"+num).val(postTypeId);
			   }else{
				postTypeId = $("#partyPostId"+num).attr("attr_postid"); 
				 $("#postTypeId"+num).val(postTypeId);			
			   }
			 });
	if(flag){
		$("#savingAjaxImg").css("display","block");	
			YAHOO.util.Connect.setForm('submitApplication',true);
			YAHOO.util.Connect.asyncRequest('POST','savingNominatedPostApplicationAction.action',uploadHandler);
	}
		
	}
	var globalNominatedCandId ;
	function showSbmitStatus(result){
		globalNominatedCandId = "";
		if(result.indexOf("SUCCESS") > -1){
			globalNominatedCandId = result.replace( /[^\d.]/g, '' );
			
			//$("#savingStatusDivId").html("<span style='color: green;font-size:22px;'>Application received  successfully , To complete the registration ,Upload the profiles below</span>");
			if (confirm('Application Received Successfully...')) {
				location.reload();
			}
			refreshExistingDetailsInNominatedLevel();
			refreshExistingDetails();
			//setTimeout(function(){
			//$("#savingStatusDivId").html("");
			//}, 5000);
			
		}else {
			setTimeout(function(){
			$("#savingStatusDivId").html("<span style='color: red;font-size:22px;'>Application Submission Failed. Please Try Again.</span>");
			}, 1000);
		}
	}
	
	 function validatationFields(){
		var flag = true;
		
		$(".errorMsgCls").html("");
		
		var errorMsg='';
		var boardLvlId;
		$(".boardLvlCls").each(function(){
			$(".errorMsgCls").html("");
			 var clonNo = $(this).attr("attr_no");
			 
				if($(this).val() == 0){
					$(this).parent().find(".chosen-single").css("border","1px solid red");
					errorMsg = "Please Select Board Level";
					flag = false;
				}else{
					errorMsg = '';
						$(this).parent().find(".chosen-single").css("border","1px solid gray");
						flag = true;
				}
			
				if($(this).val() == 2 || $(this).val() == 3 || $(this).val() == 4 || $(this).val() == 5 || $(this).val() == 6 || $(this).val() == 7){
							
							if($("#nominatedStaeId"+clonNo).val() == 0 && typeof $("#nominatedStaeId"+clonNo).val() !== "undefined"){
								$("#nominatedStaeId"+clonNo).parent().find(".chosen-single").css("border","1px solid red");
								errorMsg = "Please Select State";
								flag = false;
							}else{
								errorMsg = '';
								$("#nominatedStaeId"+clonNo).parent().find(".chosen-single").css("border","1px solid gray");
								flag = true;
							} 
				}
				if($(this).val() == 3 || $(this).val() == 4 || $(this).val() == 5 || $(this).val() == 6 || $(this).val() == 7){
						
							if($("#nominatedDistId"+clonNo).val() == 0 && typeof $("#nominatedDistId"+clonNo).val() !== "undefined"){
								
								$("#nominatedDistId"+clonNo).parent().find(".chosen-single").css("border","1px solid red");
								errorMsg = "Please Select District";
								flag = false;
							}else{
								errorMsg = '';
								$("#nominatedDistId"+clonNo).parent().find(".chosen-single").css("border","1px solid gray");
								flag = true;
								} 
				}
				if($(this).val() == 4 || $(this).val() == 5 || $(this).val() == 6 || $(this).val() == 7){
						
							if($("#nominatdConstId"+clonNo).val() == 0 && typeof $("#nominatdConstId"+clonNo).val() !== "undefined"){
								
								$("#nominatdConstId"+clonNo).parent().find(".chosen-single").css("border","1px solid red");
								errorMsg = "Please Select Constituency";
								flag = false;
							}else{
								errorMsg = '';
								$("#nominatdConstId"+clonNo).parent().find(".chosen-single").css("border","1px solid gray");
								flag = true;
								} 
				}
				if($(this).val() == 5 || $(this).val() == 6 || $(this).val() == 7){
						
							if($("#nominatedMandlId"+clonNo).val() == 0 && typeof $("#nominatedMandlId"+clonNo).val() !== "undefined"){
								$("#nominatedMandlId"+clonNo).parent().find(".chosen-single").css("border","1px solid red");
								errorMsg = "Please Select Mandal/Corporation";
								flag = false;
							}else{
								errorMsg = '';
								$("#nominatedMandlId"+clonNo).parent().find(".chosen-single").css("border","1px solid gray");
								flag = true;
								} 
				}
				if($(this).val() == 7){
						
							if($("#nominatedPanchayatId"+clonNo).val() == 0 && typeof $("#nominatedPanchayatId"+clonNo).val() !== "undefined"){
								$("#nominatedPanchayatId"+clonNo).parent().find(".chosen-single").css("border","1px solid red");
								errorMsg = "Please Select Panchayat";
								flag = false;
							}else{
								errorMsg = '';
								$("#nominatedPanchayatId"+clonNo).parent().find(".chosen-single").css("border","1px solid gray");
								flag = true;
								} 
				}
			 	/* if($("#depmtsId"+clonNo).val() == null || $("#depmtsId"+clonNo).val() == " " || $("#depmtsId"+clonNo).val() == undefined){
						$("#depmtsId"+clonNo).parent().find(".chosen-single").css("border","1px solid red");
								errorMsg = "Please select hilighted";
								flag = false;
				}else{
					errorMsg = '';
						$("#depmtsId"+clonNo).parent().find(".chosen-single").css("border","1px solid gray");
								flag = true;
				} 
				if($("#deptBoardId"+clonNo).val() == null || $("#deptBoardId"+clonNo).val() == " " || $("#deptBoardId"+clonNo).val() == undefined){
						$("#deptBoardId"+clonNo).parent().find(".chosen-single").css("border","1px solid red");
								errorMsg = "Please select hilighted";
								flag = false;
				}else{
					errorMsg = '';
						$("#deptBoardId"+clonNo).parent().find(".chosen-single").css("border","1px solid gray");
								flag = true;
				} 			
				if($("#deptBoardPostnId"+clonNo).val() == null || $("#deptBoardPostnId"+clonNo).val() == " " || $("#deptBoardPostnId"+clonNo).val() == undefined){
						$("#deptBoardPostnId"+clonNo).parent().find(".chosen-single").css("border","1px solid red");
								errorMsg = "Please select hilighted";
								flag = false;
				}else{
					errorMsg = '';
						$("#deptBoardPostnId"+clonNo).parent().find(".chosen-single").css("border","1px solid gray");
								flag = true;
				}  */
				if(errorMsg != ''){
					//alert($(this).parent().find(".errorMsgCls").html())		
		$(this).parent().find(".errorMsgCls").html(errorMsg);
			flag = false;
		}
				
	});
		//alert(flag)		
		return flag;
	} 
$(document).on("click","#addressCheckId",function(){
	if ($(this).is(':checked')) {
		$("#changePhoneNumberDiv").show();
  }
  else {
	  $("#changePhoneNumberDiv").hide();
  }
	getPopulateApplicantDetailsForMember(globalCadreId);
});
function savechangeAddressForNominatedPost(){ 
$("#errorMsg").html("");
var mobileNo = $("#phoneNumId").val();
var houseNo = $("#houseNumberId").val();
var addressVal = $("#addressLane1Id").val();
var addressValue = $("#addressLane2Id").val();
var pincode = $("#addPincodeId").val();
var stateId =$("#addStateId").val();
var districtId=$("#addDistrictId").val();
var constituencyId=$("#addConstituencyId").val();
var panchayatId = $("#addVillageId").val();
var mandalId=$("#addMandalsId").val();
if(mobileNo == 0){
	$("#errorMsg").html("MobileNo Required.");
	return;
}
else if(isNaN(mobileNo) || mobileNo.length != 10){
	$("#errorMsg").html("Enter Valid Mobile No.");
	return;
}
if(pincode == 0){
		  $("#errorMsg").html("Pincode Required.");
		  return;
}	
else if(isNaN(pincode) || pincode.length != 6){
	$("#errorMsg").html("Enter 6 digits pincode.");
	return;
}
var jObj={
		tdpCadreId:globalCadreId,
		stateId:stateId,
		districtId:districtId,
		constituencyId:constituencyId,
		panchayatId:panchayatId,
		mobileNo:mobileNo,
		houseNo : houseNo,
		addressVal:addressVal,
		addressValue:addressValue,
		pincode:pincode,
		mandalId:mandalId
	};
	$.ajax({
	  type:'POST',
	  url: 'savechangeAddressForNominatedPostAction.action',
	  dataType: 'json',
	  data: {task:JSON.stringify(jObj)},
	  }).done(function(result){
		  if(result != null){
				 if(result == "success"){
					$("#errorMsg").html("Change address and phone Successfully Updated...");
					$("#errorMsg").html("");
					$("#phoneNumId").val('');
					$("#houseNumberId").val('');
					$("#addressLane1Id").val('');
					$("#addPincodeId").val('');
					$('#addressCheckId').attr('checked', false);
					$("#changePhoneNumberDiv").hide();
					}else{
					$("#errorMsg").html("Error Occured Try agian..");
				} 
			}
			
	  });
}
 $( document ).on("click",".cadreCls",function(){
	globalCadreId = $(this).attr("attr_cadreId"); 
	var candiId = $(this).attr("attr_nominated_post_candidate_id"); 
	globalNPCandiId = $(this).attr("attr_nominated_post_candidate_id"); 
	  getCandidateAppliedPostsByCadre(globalCadreId,candiId);
});
function getPopulateApplicantDetailsForMember(globalCadreId){ 
 var type = $("input[type='radio']:checked").val();
 var id =globalNPCandiId;
		if(id > 0){
			type="Not Cadre";
		}else if(id !== "undefined" || id <=0){
			id =globalCadreId;
			type="Cadre";
		}else{
			id =globalCadreId;
			type="Cadre";
		}
   var jObj={
		globalCadreId:id,
		searchType:type
		//nominateCandId:globalNPCandiId
	};
	$.ajax({
	  type:'POST',
	  url: 'getPopulateApplicantDetailsForMemberAction.action',
	  dataType: 'json',
	  data: {task:JSON.stringify(jObj)},
	  }).done(function(result){
		  if(result != null && result.length>0){
				populateFields(result);  
			}
			
	  });
}
function populateFields(result){
	if(result[0].hno != null && result[0].hno !=''){
		$("#houseNumberId").val(''+result[0].hno+'');
	}else{
		$("#houseNumberId").val('');
	}
	if(result[0].mobileNo != null && result[0].mobileNo !='')
		$("#phoneNumId").val(''+result[0].mobileNo+'');
	else
		$("#phoneNumId").val('');
	if(result[0].address1 != null && result[0].address1 !='')
		$("#addressLane1Id").val(''+result[0].address1+'');
	else
		$("#addressLane1Id").val('');
	if(result[0].address2 != null && result[0].address2 !='')
		$("#addressLane2Id").val(''+result[0].address2+'');
	else
		$("#addressLane2Id").val('');
	if(result[0].pincode != null && result[0].pincode !='')
		$("#addPincodeId").val(''+result[0].pincode+'');
	else
	$("#addPincodeId").val('');
	
	if(result[0].stateId != null && result[0].stateId !=""){
		$("#changestateId").val(''+result[0].stateId+'');
		$("#changestateId").trigger("chosen:updated");
	}
	else {
		$("#changestateId").val(0);
	}
	
	$("#changedistrictId").html("");
	$("#changedistrictId").append("<option value='0'>Select District</option>");
	if(result[0].distList != null && result[0].distList.length > 0){
		for(var i in result[0].distList){
			if(result[0].districtId != null && result[0].distList[i].id == result[0].districtId){
				$("#changedistrictId").append("<option selected value='"+result[0].distList[i].id+"'>"+result[0].distList[i].name+"</option>");
			}else{
				$("#changedistrictId").append("<option value='"+result[0].distList[i].id+"'>"+result[0].distList[i].name+"</option>");
			}
		}
		$("#changedistrictId").trigger("chosen:updated");
	}
	
	
	$("#changeConstiId").html("");
	$("#changeConstiId").append("<option value='0'>Select Constituency</option>");
	if(result[0].consList != null && result[0].consList.length > 0){
		for(var i in result[0].consList){
			if(result[0].constituencyId != null && result[0].consList[i].id == result[0].constituencyId){
				$("#changeConstiId").append("<option selected value='"+result[0].consList[i].id+"'>"+result[0].consList[i].name+"</option>");
			}else{
				$("#changeConstiId").append("<option value='"+result[0].consList[i].id+"'>"+result[0].consList[i].name+"</option>");
			}
		}
		$("#changeConstiId").trigger("chosen:updated");
	}
	
	$("#changeMandalId").html("");
	$("#changeMandalId").append("<option value='0'>Select Mandal</option>");
	if(result[0].consList != null && result[0].consList.length > 0){
		for(var i in result[0].mandalsList){
			if(result[0].mandalsList[i].id != null && result[0].mandalsList[i].id == result[0].mandalId){
				$("#changeMandalId").append("<option selected value='"+result[0].mandalsList[i].id+"'>"+result[0].mandalsList[i].name+"</option>");
			}else{
				$("#changeMandalId").append("<option value='"+result[0].mandalsList[i].id+"'>"+result[0].mandalsList[i].name+"</option>");
			}
		}
		$("#changeMandalId").trigger("chosen:updated");
	}
	
	
	$("#changePanchyatId").html("");
	$("#changePanchyatId").append("<option value='0'>Select Panchayat</option>");
	if(result[0].panList != null && result[0].panList.length > 0){
		for(var i in result[0].panList){
			if(result[0].panchayatId != null && result[0].panList[i].id == result[0].panchayatId){
				$("#changePanchyatId").append("<option selected value='"+result[0].panList[i].id+"'>"+result[0].panList[i].name+"</option>");
			}else{
				$("#changePanchyatId").append("<option value='"+result[0].panList[i].id+"'>"+result[0].panList[i].name+"</option>");
			}
		}
		$("#changePanchyatId").trigger("chosen:updated");
	}
}
/*  function getDistrictsForReferPopup()
{
	var stateId = $("#addStateId").val();
	var jobj = {
		stateId : stateId
	}
	$.ajax({
		type : 'GET',
		url : 'getDistrictsForStateForNominatedAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jobj)} 
	}).done(function(result){
		 if(result !=null && result.length>0){
				$("#addDistrictId").html("");
				$("#addDistrictId").append('<option value="0">Select District</option>');
			   for(var i in result){   
			  $("#addDistrictId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		   }
		 }
		 $("#addDistrictId").trigger("chosen:updated");
	});
 } */
 function getDistrictsForReferPopup()
{
	var stateId = $("#addStateId").val();
	
	 $("#addConstituencyId").html("");
	 $("#addConstituencyId").append('<option value="0">Select Constituency</option>');
	 
	 $("#addMandalsId").html("");
	 $("#addMandalsId").append('<option value="0">Select Mandal</option>');
	 
	 $("#addVillageId").html("");
	 $("#addVillageId").append('<option value="0">Select Village</option>');
	 
	var jobj = {
		stateId : stateId,
		elmtId:"districtList_d",
		type:"default",
		task:"getDistrictsForState"
	}
	$.ajax({
		type : 'GET',
		url : 'getNewDistrictsOfStateSplittedAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jobj)} 
	}).done(function(result){
		 if(result !=null && result.length>0){
				$("#addDistrictId").html("");
				$("#addDistrictId").append('<option value="0">Select District</option>');
			   for(var i in result){   
				$("#addDistrictId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			   }
		 }
		 $("#addDistrictId").trigger("chosen:updated");
	});
 }
 function getConstituenciesForDistrict(){
	 var districtArr=[];
	  var districtId= $("#addDistrictId").val();
	  var stateId= $("#addStateId").val();

	 $("#addMandalsId").html("");
	 $("#addMandalsId").append('<option value="0">Select Mandal</option>');
	 
	 $("#addVillageId").html("");
	 $("#addVillageId").append('<option value="0">Select Village</option>');
	  
	  districtArr.push(districtId);
	  
		 var jsObj={
			 stateId:stateId,
			 districtId:districtArr
		 }
		          
		$.ajax({
			  type:'GET',
			  url: 'getConstituenciesOfDistrictWithSplittedAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		   $("#addConstituencyId").html("");
		   $("#addConstituencyId").append('<option value="0">Select Constituency</option>');
		   if(result !=null && result.length>0){
			   for(var i in result){
				   $("#addConstituencyId").append('<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>');
			   }
		   }
		    $("#addConstituencyId").trigger("chosen:updated");
	   });
	}
 
 /* 
  function getConstituenciesForDistrict(){
	  var districtId= $("#addDistrictId").val();
		 var jsObj={ districtId:districtId }
		          
		$.ajax({
			  type:'GET',
			  url: 'getConstituenciesForDistrictAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		   $("#addConstituencyId").html("");
		   $("#addConstituencyId").append('<option value="0">Select Constituency</option>');
		   if(result !=null && result.length>0){
			   for(var i in result){
				   $("#addConstituencyId").append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
			   }
		   }
		    $("#addConstituencyId").trigger("chosen:updated");
	   });
	} */
/* function getMandalsByConstituencyForReferPopup()
{
	var constituencyId = $('#addConstituencyId').val();
	var jobj = {
	constituencyId:constituencyId,
	task       : ""
	}
		$.ajax({
		 type : "POST",
		 url  : "getMandalsByConstituencyAction.action",
		data : {task:JSON.stringify(jobj)} 
		}).done(function(result){
			if(result != null && result.length > 0){
			 $("#addMandalsId").html("");
			 $("#addMandalsId").append('<option value="0">Select Mandal</option>');
		   if(result !=null && result.length>0){
			   for(var i in result){
				   $("#addMandalsId").append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
			   }
		   }
		    $("#addMandalsId").trigger("chosen:updated");
			}
		});
}	
 function getPanchayatsForMandal(){
	 var mandalId=$("#addMandalsId").val();

		var jsObj={
			mandalId :mandalId
		}
		$.ajax({
			type:"POST",
			url :"getVillagesForMandalIdNominatedAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			 $("#addVillageId").html("");
			 $("#addVillageId").append('<option value="0">Select Panchayats</option>');
		   if(result !=null && result.length>0){
			   for(var i in result){
				   $("#addVillageId").append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
			   }
		   }
		    $("#addVillageId").trigger("chosen:updated");
   });	
  } */

 function getCandidateAppliedPostsByCadre(globalCadreId,candiId){
	 var type = $("input[type='radio']:checked").val();
		var jsObj={
				globalCadreId :globalCadreId,
				searchType:type,
				nominateCandId:candiId
		}
		$.ajax({
			type:"POST",
			url :"getCandidateAppliedPostsByCadreAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
		   if(result !=null){
			   buildCandidateAppliedPostByCadreDetails(result);
		   }
   });	
  }
  function buildCandidateAppliedPostByCadreDetails(result){
	 var str = '';
	 if(result.subList != null && result.subList.length > 0){
		 str+='<div class="col-md-12 col-xs-12 col-sm-12 col-lg-12 m_top20">';
                    str+='<div class="bg_ff pad_10">';
                        	str+='<h4 class="panel-title">APPLIED POSTS FOR THE SELECTED PROFILE</h4>';
                           str+='<div class="row">';
                            str+='<div class="col-md-6 col-xs-12 col-sm-6 col-lg-6">';
                                	str+='<div class="panel panel-default panelPost">';
                                       str+='<div class="panel-heading">';
                                        str+='<h4 class="panel-title">Nominated Post</h4>';
                                       str+='</div>';
                                       str+='<div class="panel-body">';
                                        	str+='<ul class="ulPost">';
											for(var i in result.subList){
                                            	str+='<li>';
                                                	str+='<span class="labelStatus shortlisted">Shortlisted</span>';
													if(result.subList[i].levelName != null){
                                                	str+=''+result.subList[i].level+'-'+result.subList[i].levelName+'→' +result.subList[i].subCaste+" → "+result.subList[i].cadreName+" → "+result.subList[i].voterName+" : "+result.subList[i].status+"</li>";
													}
													else{
														str+=''+result.subList[i].level+'→' +result.subList[i].subCaste+" → "+result.subList[i].cadreName+" → "+result.subList[i].voterName+" : "+result.subList[i].status+"</li>";
													}
											}
                                           str+='</ul>';
                                        str+='</div>';
                                    str+='</div>';
                                str+='</div>';
								}
							//nominated Party 
							if(result.subList1 != null && result.subList1.length > 0){
								
								str+='<div class="col-md-6 col-xs-12 col-sm-6 col-lg-6">';
                                	str+='<div class="panel panel-default panelPost">';
                                    	str+='<div class="panel-heading">';
                                        	str+='<h4 class="panel-title">Party Post</h4>';
                                        str+='</div>';
                                        str+='<div class="panel-body">';
                                        	str+='<ul class="ulPost">';
											for(var i in result.subList1){
                                                str+='<li>';
                                                	str+='<span class="labelStatus shortlisted">Shortlisted</span>';
                                                	if(result.subList1[i].levelName != null){
													str+=''+result.subList1[i].level+'-'+result.subList1[i].levelName+ '→' +result.subList1[i].subCaste+" → "+result.subList1[i].cadreName+" → "+result.subList1[i].voterName+" : "+result.subList1[i].status+"</li>";
													}
													else{
														str+=''+result.subList1[i].level+'→' +result.subList1[i].subCaste+" → "+result.subList1[i].cadreName+" → "+result.subList1[i].voterName+" : "+result.subList1[i].status+"</li>";
													}
											}
                                          str+='</ul>';
                                       str+=' </div>';
                                    str+='</div>';
                               str+=' </div>';
							}
                           str+='</div>';
                       str+='</div>';
                   str+='</div>';
	 
	 $("#appliedPostForSelectedId").html(str);
}
function searchByApplicant()
  {
	  var flag=true;
  var search=$("#searchbtn").val();
    var cadres = [];
    $(".checkboxCls:checked").each(function() {
     cadres.push(this.value);
	 });
   if(search == 0&&cadres.length==0){
       $("#searchErrDiv").html("Please Require Search Applicant");
       flag=false;
        }
      else{
        $("#searchErrDiv").html('');
		flag=true;
      }
     return flag;
    }
	
	$(document).on("click",".cadreCheckCls",function(){
	 $("#searchData").html('');
	 $("#searchData1").html('');
    // $("#cadreSearchDtls").html('');	 
  if ($("#cadreSearchId").is(":checked")) {
		$("#searchMemberDiv").show();
		$("#cadreById").hide();

	}
	else {
	 $("#searchMemberDiv").hide();
	 $("#cadreById").show();
	 $("#scrollDivId").hide();
	 $("#textId").hide();
	}
});
$(document).on("click",".deleteFile",function() {
 
 var applicatnDocId = $(this).attr("id");
 
 var jsObj=
   {				
	  applicatnDocId:applicatnDocId,
	  task:"deleteFile"				
	}
	$.ajax({
			  type:'GET',
			  url: 'deleteNominatedUploadedFileAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		   if(result.resultCode == 0){
	         $(this).closest("li").html("");
		   }
	   });

});
$(document).on("click",".referenceModal",function(){
    $(".referenceModal").prop( "checked" ,false);
	$( this ).prop( 'checked', true );
})
function clearAssignFields()
{
commontdpCadreIds = [];
$("#involvedCandidatesDiv").hide();
 $(".membersBlock").html('');
 $("#apptmemberDetailsDiv").html('');
  $("#advanceSearchTypeId").val(0);
    var select = new Dropkick("#advanceSearchTypeId");
    select.refresh();
  showHideBySearchType();//Clear Fields  
}
var isNotCadreFree=true;
function notCadresearch(){
	
	if(isNotCadreFree){
		isNotCadreFree = false;
		var cadreTypeStr=$("input[name='checkBoxName']:checked").val();
		var searchType=$("input[name='searchBasedOn']:checked").val();
		var searchValue=$("#searchBy").val();
		
		if(cadreTypeStr == "Not Cadre"){
			searchType=$("input[name='radioGroup']:checked").val();
			searchValue=$("#searchById").val();
		}
		
		
		if(searchType == "1")
		{
			if(searchValue.length == 0 )
			{
				$('#searchErrDiv').html('Please enter Membership No.');
				return;
			}
		}
		else if(searchType == "2")
		{
			if(searchValue.length == 0 )
			{
				$('#searchErrDiv').html('Please enter Voter Card No.');
				return;
			}
		}
		else if(searchType == "3")
		{
			
			var numericExpression = /^[0-9]+$/;
			if(searchValue.length == 0 )
			{
				$('#searchErrDiv').html('Please enter Mobile No.');
				return;
			}		
			if(!searchValue.match(numericExpression)){
				$('#searchErrDiv').html('Enter Numerics Only.');
				return;
			}	
			else if(searchValue.trim().length != 10)
			{
				$('#searchErrDiv').html('Invalid Mobile No.');
				return;				
			}
			
		}
		else if(searchType == 4)
		{
			if(searchValue.length == 0 )
			{
				$('#searchErrDiv').html('Please enter Name.');
				return;
			}
			else if(searchValue.length < 3)
			{
				$('#searchErrDiv').html('Please enter Minimum 3 Characters.');
				return;
			}
		}
		$('#cadreSearchDtls').html(' <img style="margin-left: 400px; margin-top: 20px; width: 200px; height: 150px;" id="" class="offset7" src="images/icons/cadreSearch.gif">');
	$("#searchData1,#searchData").html('');
	$("#textId").hide();
	
	
       $("#scrollDivId").show();
   
		var jsObj =
		        {
		searchType : searchType,
		searchValue : searchValue,
		task       : ""
		          }
				$.ajax({
					  type:'GET',
					  url: 'notCadresearchAction.action',
					  data: {task :JSON.stringify(jsObj)}
			   }).done(function(result){
					isNotCadreFree =true;	   
					if(result != null){
						buildCadreDetails(result);					
					}
					else if(cadreTypeStr =="Cadre"){
						getNominatedPostApplication(0);
					}
					else
						$("#cadreSearchDtls").html("No Data Available...");
				  
				});
			}
		}
 $(document).on("click","#searchbtn",function(){
	  var value = $("input[name='checkBoxName']:checked").val();
	  if(value == "Cadre"){
			getNominatedPostApplication(0);
		}
		else if(value == "Not Cadre"){
			notCadresearch();
		}
 });
 
 function subLevelForConstituency(locationLevel){
	 var distArrTemp=[];
	 var assmblyArrTemp=[];
	 var mandalId=0;
	 var constituencyId = $('#addConstituencyId').val();
	 var stateId = $('#addStateId').val();
	 var districtId = $('#addDistrictId').val();
	 var mandal= $('#addDistrictId').val();
	 distArrTemp.push(districtId);
	 assmblyArrTemp.push(constituencyId);
	 if(mandal !=null && mandal !="" && mandalId>0){
		 assmblyArrTemp.push();
	 }
	  var jsObj={				
			stateId : stateId,
			districtId : distArrTemp,
			constituencyId : assmblyArrTemp,//228
			mandalId : mandalId,
			locationLevel : locationLevel,
			task:""
		}
		
		$.ajax({
			  type:'GET',
			  url: 'getSubLevelLctnsForConstituencyAndMandalAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		   if(result != null && result.length > 0){
			   
			   if(locationLevel == 4){
				    $("#addMandalsId").html("");
					$("#addMandalsId").append('<option value="0">Select Mandal</option>');
			   }else if(locationLevel == 5){
				    $("#addVillageId").html("");
					$("#addVillageId").append('<option value="0">Select Village</option>');
			   }
		   if(result !=null && result.length>0){
			    if(locationLevel == 4){
					for(var i in result){				 
					   $("#addMandalsId").append('<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>');
					}
					$("#addMandalsId").trigger("chosen:updated");
				}else if(locationLevel == 5){
					for(var i in result){		
					  $("#addVillageId").append('<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>');
					}
					$("#addVillageId").trigger("chosen:updated");
				  }				  
			   }
		   }
		    
		 }); 
 }
 var isAlreadyChecked=false;
var isSameCheckBoxClicked=0;
 $(document).on("click",".hideShowDivCls",function(){
   $(".hideShowDivCls").prop( "checked", false);
   $(this).prop( "checked", true);
   var currentClickTdpCadredId= $(this).attr("attr_cadreId");
    if(currentClickTdpCadredId!=isSameCheckBoxClicked){
	    isAlreadyChecked = false;	  	 
	 }
 isSameCheckBoxClicked = currentClickTdpCadredId;
  if(isAlreadyChecked){
	 $(".hideShowDivCls").prop( "checked", false); 
      $(".hideDivCls").hide();	
      isAlreadyChecked = false;	  
   }
   if($(this).is(':checked')){
	  globalCadreId = $(this).attr("attr_cadreId"); 
	  candidateId = $(this).attr("attr_nominated_post_candidate_id");
	  getCandidateAppliedPostsByCadre(globalCadreId,globalNPCandiId); 
	   $(".hideDivCls").show();
        isAlreadyChecked = true;	   
   }
 });

 $(document).on('click','.cadreCls',function(){
		$("#showAndHideDivId").show();
	});