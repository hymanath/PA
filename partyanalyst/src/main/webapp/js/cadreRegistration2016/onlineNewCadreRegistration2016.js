 function getConstituenciesForDistricts(district,id){
	  if(id == 1){
		 $("#constituencyDivIdImg").show();
		 $("#panchayatTwnId").show();
		$("#constituencyId  option").remove();
		$("#constituencyId").append('<option value="0">Select Constituency</option>');
		$("#mandalList  option").remove();
		$("#mandalList").append('<option value="0">Select Mandal/Municipality</option>');
		$("#panchayatList  option").remove();
		$("#panchayatList").append('<option value="0">Select Panchayat</option>');
		$("#boothsList  option").remove();
		$("#boothsList").append('<option value="0">Select Booth</option>');
		
		$("#constituencyId").trigger("chosen:updated");
		$("#mandalList").trigger("chosen:updated");
		$("#panchayatList").trigger("chosen:updated");
		$("#boothsList").trigger("chosen:updated");
		$("#searchVoterId").val('');
		$("#nameId").val('');
		$("#mobileId").val('');
		$("#huseNOId").val('');
	  }else  if(id == 2){		
		$("#PrsntConstutuencyList  option").remove();
		$("#PrsntConstutuencyList").append('<option value="0">Select Constituency</option>');
		$("#PrsntMandalList  option").remove();
		$("#PrsntMandalList").append('<option value="0">Select Mandal/Municipality</option>');
		$("#PrsntVillageList  option").remove();
		$("#PrsntVillageList").append('<option value="0">Select Panchayat</option>');
		
		$("#PrsntConstutuencyList").trigger("chosen:updated");
		$("#PrsntMandalList").trigger("chosen:updated");
		$("#PrsntVillageList").trigger("chosen:updated");
	 }
	 else  if(id == 3){		
		$("#workConstuencyList  option").remove();
		$("#workConstuencyList").append('<option value="0">Select Constituency</option>');
		$("#workMandalList  option").remove();
		$("#workMandalList").append('<option value="0">Select Mandal/Municipality</option>');
		$("#workVillageList  option").remove();
		$("#workVillageList").append('<option value="0">Select Panchayat</option>');
				
		$("#workConstuencyList").trigger("chosen:updated");
		$("#workMandalList").trigger("chosen:updated");
		$("#workVillageList").trigger("chosen:updated");
	 }
   var jsObj=
   {				
		districtId:district
	}
    $.ajax({
          type:'GET',
          url: 'getConstituenciesListForDistrictAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(id == 1){
			$("#constituencyDivIdImg").hide();
			for(var i in result){
			   if(result[i].id == 0){
				  $("#constituencyId").append('<option value='+result[i].id+'>Select Constituency</option>');
			   }else{
				  $("#constituencyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			   }
			}
			$("#constituencyId").trigger("chosen:updated");
	   }
	   else if(id == 2){
			for(var i in result){
			   if(result[i].id == 0){
				  $("#PrsntConstutuencyList").append('<option value='+result[i].id+'>Select Constituency</option>');
			   }else{
				  $("#PrsntConstutuencyList").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			   }
			}
			
			
			 $("#PrsntConstutuencyList").val(presntConstituencyId);
			$("#PrsntConstutuencyList").trigger("chosen:updated");
			 setTimeout(function(){
				 getMandalCorporationsByConstituency(presntConstituencyId,2);
			},500);
			
	   }
	   else if(id == 3){
			for(var i in result){
			   if(result[i].id == 0){
				  $("#workConstuencyList").append('<option value='+result[i].id+'>Select Constituency</option>');
			   }else{
				  $("#workConstuencyList").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			   }
			}
			$("#workConstuencyList").trigger("chosen:updated");
	   }
   });
  }


 function getMandalCorporationsByConstituency(consistency,id){
	 if(id==1){
		 $("#mandalDivIdImg").show();
		 $("#panchayatTwnId").show();
		$("#mandalList  option").remove();
		$("#mandalList").append('<option value="0">Select Mandal/Municipality</option>');
		$("#panchayatList  option").remove();
		$("#panchayatList").append('<option value="0">Select Panchayat</option>');
		$("#boothsList  option").remove();
		$("#boothsList").append('<option value="0">Select Booth</option>');
		
		$("#mandalList").trigger("chosen:updated");
		$("#panchayatList").trigger("chosen:updated");
		$("#boothsList").trigger("chosen:updated");

		$("#searchVoterId").val('');
		$("#nameId").val('');
		$("#mobileId").val('');
		$("#huseNOId").val('');
	 }else if(id==2){
		$("#PrsntMandalList  option").remove();
		$("#PrsntMandalList").append('<option value="0">Select Mandal/Municipality</option>');
		$("#PrsntVillageList  option").remove();
		$("#PrsntVillageList").append('<option value="0">Select Panchayat</option>');
		
		$("#PrsntMandalList").trigger("chosen:updated");
		$("#PrsntVillageList").trigger("chosen:updated");
		 
	 }else if(id==3){
		$("#workMandalList  option").remove();
		$("#workMandalList").append('<option value="0">Select Mandal/Municipality</option>');
		$("#workVillageList  option").remove();
		$("#workVillageList").append('<option value="0">Select Panchayat</option>');
		
		$("#workMandalList").trigger("chosen:updated");
		$("#workVillageList").trigger("chosen:updated");
	 }
	var jsObj=
   {				
		consistencyId:consistency
   }
    $.ajax({
          type:'GET',
          url: 'getMandalsForConstituencyAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   
	   if(id==1){
		   $("#mandalDivIdImg").hide();
			for(var i in result){
			   if(result[i].id == 0){
				  $("#mandalList").append('<option value='+result[i].id+'>Select Mandal</option>');
			   }else{
				  $("#mandalList").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			   }
			}
			$("#mandalList").trigger("chosen:updated");
	   }else if(id==2){
		   for(var i in result){
			   if(result[i].id == 0){
				  $("#PrsntMandalList").append('<option value='+result[i].id+'>Select Mandal</option>');
			   }else{
				  $("#PrsntMandalList").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			   }
			}
			 $("#PrsntMandalList").val(presntMandalId);
			 $("#PrsntMandalList").trigger("chosen:updated");
			 setTimeout(function(){
				 getPanchayatWardByMandal(presntMandalId,2);
			},500);
					   
	   }else if(id==3){		   
		   for(var i in result){
			   if(result[i].id == 0){
				  $("#workMandalList").append('<option value='+result[i].id+'>Select Mandal</option>');
			   }else{
				  $("#workMandalList").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			   }
			}
			$("#workMandalList").trigger("chosen:updated");
	   }
   });
  }
  
  function getPanchayatWardByMandal(mandal,id)
	{
		var mandalSubStrId=0;
	if(id==1){
		mandalSubStrId=mandal.substring(0,1);
		//$("#boothDivIdImg").show();	
		$("#panchayatList  option").remove();
		$("#panchayatList").append('<option value="0">Select Panchayat</option>');
		$("#boothsList  option").remove();
		$("#boothsList").append('<option value="0">Select Booth</option>');
		
		$("#panchayatList").trigger("chosen:updated");
		$("#boothsList").trigger("chosen:updated");

		$("#searchVoterId").val('');
		$("#nameId").val('');
		$("#mobileId").val('');
		$("#huseNOId").val('');
		
	   if(mandalSubStrId==1){
		   $("#panchayatTwnId").show();
		   $("#pancyatDivIdImg").show();
	   }
	   else{
		   $("#panchayatTwnId").hide();
		   $("#boothDivIdImg").show();	
	   }
	}
	else if(id==2){
		$("#PrsntVillageList  option").remove();
		$("#PrsntVillageList").append('<option value="0">Select Panchayat</option>');
		$("#PrsntVillageList").trigger("chosen:updated");
	}else if(id==3){
		$("#workVillageList  option").remove();
		$("#workVillageList").append('<option value="0">Select Panchayat</option>');
		$("#workVillageList").trigger("chosen:updated");
	}
				var jsObj ={					
					mandalId:mandal
				};
				 $.ajax({
					type : "GET",
					url : "getPanchayatsForMandalAction.action",
					data : {task:JSON.stringify(jsObj)} 
				}).done(function(result){
					//$("#boothDivIdImg").hide();
					
					if(id==1){
					  for(var i in result){					 
							if(mandalSubStrId==1){
								$("#pancyatDivIdImg").hide();
								if(result[i].id == 0){
								  $("#panchayatList").append('<option value='+result[i].id+'>Select Panchayat</option>');
								}else{
									$("#panchayatList").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
								}
								
							}else{
								$("#boothDivIdImg").hide();
								if(result[i].id == 0){
									$("#boothsList").append('<option value='+result[i].id+'>Selct Booth</option>');
								}else{
									$("#boothsList").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
								}
								
							}
							
							$("#panchayatList").trigger("chosen:updated");
							$("#boothsList").trigger("chosen:updated");	
					  }
					}else if(id==2){
						 for(var i in result){
							if(result[i].id == 0){
							  $("#PrsntVillageList").append('<option value='+result[i].id+'>Select Panchayat</option>');
							}else{
								$("#PrsntVillageList").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
							}
						 }
						 
						 $("#PrsntVillageList").val(presntVillageId);
						 $("#PrsntVillageList").trigger("chosen:updated");
						 
					}
					else if(id==3){
						 for(var i in result){
							if(result[i].id == 0){
							  $("#workVillageList").append('<option value='+result[i].id+'>Select Panchayat</option>');
							}else{
								$("#workVillageList").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
							}
						 }
						$("#workVillageList").trigger("chosen:updated");
					}	
				});
	}
	
	function getAllCadreInPanchayat(panchayat)
	{	
	$("#boothDivIdImg").show();
	$("#boothsList  option").remove();
	$("#boothsList").append('<option value="0">Select Booth</option>');
	
	$("#boothsList").trigger("chosen:updated");

	$("#searchVoterId").val('');
	$("#nameId").val('');
	$("#mobileId").val('');
	$("#huseNOId").val('');
			
				var jsObj ={					
					panchayatId:panchayat
				};
				 $.ajax({
					type : "GET",
					url : "getBoothsForPanchayatAction.action",
					data : {task:JSON.stringify(jsObj)} 
				}).done(function(result){
					$("#boothDivIdImg").hide();
					for(var i in result)
					{
					if(result[i].id == 0){
                  $("#boothsList").append('<option value='+result[i].id+'>Select Booth</option>');
	          }else{
	      $("#boothsList").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
					}
					}
$("#boothsList").trigger("chosen:updated");						
				});
	}

 function getDistrictsForStates(state,id){
	 
	 if(id == 1){
		$("#districtDivIdImg").show();
		$("#panchayatTwnId").show();
		$("#districtId  option").remove();
		$("#districtId").append('<option value="0">Select District</option>');
		$("#constituencyId  option").remove();
		$("#constituencyId").append('<option value="0">Select Constituency</option>');
		$("#mandalList  option").remove();
		$("#mandalList").append('<option value="0">Select Mandal/Municipality</option>');
		$("#panchayatList  option").remove();
		$("#panchayatList").append('<option value="0">Select Panchayat</option>');
		$("#boothsList  option").remove();
		$("#boothsList").append('<option value="0">Select Booth</option>');
		
		$("#districtId").trigger("chosen:updated");
		$("#constituencyId").trigger("chosen:updated");
		$("#mandalList").trigger("chosen:updated");
		$("#panchayatList").trigger("chosen:updated");
		$("#boothsList").trigger("chosen:updated");
		
		$("#searchVoterId").val('');
		$("#nameId").val('');
		$("#mobileId").val('');
		$("#huseNOId").val('');
	 }else  if(id == 2){
		$("#PrsntDistrictList  option").remove();
		$("#PrsntDistrictList").append('<option value="0">Select District</option>');
		$("#PrsntConstutuencyList  option").remove();
		$("#PrsntConstutuencyList").append('<option value="0">Select Constituency</option>');
		$("#PrsntMandalList  option").remove();
		$("#PrsntMandalList").append('<option value="0">Select Mandal/Municipality</option>');
		$("#PrsntVillageList  option").remove();
		$("#PrsntVillageList").append('<option value="0">Select Panchayat</option>');
		
		$("#PrsntDistrictList").trigger("chosen:updated");
		$("#PrsntConstutuencyList").trigger("chosen:updated");
		$("#PrsntMandalList").trigger("chosen:updated");
		$("#PrsntVillageList").trigger("chosen:updated");
	 }
	 else  if(id == 3){
		$("#workDistrictList  option").remove();
		$("#workDistrictList").append('<option value="0">Select District</option>');
		$("#workConstuencyList  option").remove();
		$("#workConstuencyList").append('<option value="0">Select Constituency</option>');
		$("#workMandalList  option").remove();
		$("#workMandalList").append('<option value="0">Select Mandal/Municipality</option>');
		$("#workVillageList  option").remove();
		$("#workVillageList").append('<option value="0">Select Panchayat</option>');
		
		$("#workDistrictList").trigger("chosen:updated");
		$("#workConstuencyList").trigger("chosen:updated");
		$("#workMandalList").trigger("chosen:updated");
		$("#workVillageList").trigger("chosen:updated");
	 }
   var jsObj=
   {				
				stateId:state
							
	}
    $.ajax({
          type:'GET',
          url: 'getDistrictsWiseStateAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   $("#districtDivIdImg").hide();
	   if(id == 1){
			 for(var i in result){
			   if(result[i].id == 0){
				  $("#districtId").append('<option value='+result[i].id+'>Select District</option>');
			   }else{
				  $("#districtId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			   }
			 }
			 $("#districtId").trigger("chosen:updated");
	   }
		else if(id == 2){
			 for(var i in result){
			   if(result[i].id == 0){
				  $("#PrsntDistrictList").append('<option value='+result[i].id+'>Select District</option>');
			   }else{
				  $("#PrsntDistrictList").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			   }
			 }
			 $("#PrsntDistrictList").val(presntDistrictId);
			 $("#PrsntDistrictList").trigger("chosen:updated");
			 setTimeout(function(){
				 getConstituenciesForDistricts(presntDistrictId,2);
			},500);			 
	   }
	   else if(id == 3){
			 for(var i in result){
			   if(result[i].id == 0){
				  $("#workDistrictList").append('<option value='+result[i].id+'>Select District</option>');
			   }else{
				  $("#workDistrictList").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			   }
			 }
			 $("#workDistrictList").trigger("chosen:updated");
	   }
   });
  }
  
$(document).on("change","#boothsList",function(){
	$("#searchVoterId").val('');
	$("#nameId").val('');
	$("#mobileId").val('');
	$("#huseNOId").val('');
});
  /*
  function getNewCadreDetails(){
	  $("#errorDivId").html("");
	  $("#searchVoterDetailsId").html("");
	  $("#searchVoterDetailsImgId").show();
	  $("#voterBtnsDivId").hide();
	  var constituency=$("#constituencyId").val();
	  var mandal=$("#mandalList").val();
	  var village=$("#panchayatList").val();
	  var booth=$("#boothsList").val();
	  var name=$("#nameId").val();
	  var mobileNo="";
	  var hNo=$("#huseNOId").val();
      var state=$("#statesDivId").val();
	  var district=$("#districtId").val();
	  var voterId = $("#searchVoterId").val();
	  
     if(state == 0)
	  {
		 $("#errorDivId").html("Please Select State");
		 return;
	   }
	  if(district == 0)
	  {
		 $("#errorDivId").html("Please Select district");
		 return;
	   }
	  if(constituency == 0)
	  {
		 $("#errorDivId").html("Please Select constituency");
		 return;
	   }
	  if(voterId.trim().length == 0 && name.trim().length == 0 && mobileNo.trim().length == 0 && houseNo.trim().length == 0 )
	   {
		  $("#errorDivId").html("Enter Atleast One VoterId or Name or MobileNo or HouseNo");
		 return; 
	   }
	   searchVoterDetails();
	  var jsObj={
		  constituencyId:constituency,
		  mandalId:mandal,
		  villageId:village,
		  boothId:booth,
		  name:name,
		  mobileNo:mobileNo,
		  hNo:hNo,
		  voterCrdNo : voterId
	  }
	   $.ajax({
          type:'GET',
          url: 'getVotersBySearchAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   $("#searchVoterDetailsImgId").hide();
	   if(result!=null && result.length>0){
	  searchCadreVoterDetails(result);
	   }
  });
  }*/
		
  function searchCadreVoterDetails(result){
	   var str = '';
  str += '<ul class="searchResults">';
  if (result!= null && result.length > 0) {
    for ( var i in result) {
      str += '<li>';
	  if(result[i].memberShipNo!=null && result[i].memberShipNo.length > 0)
		  str += '<div class="media" style="background-color: #DBDEDE;padding:5px;">';
			  str += '<div class="media-body">';
				  str += '<h5 class="text-capitalize">'+result[i].name+ '</h5>';
				  str += '<p>S/o:'+result[i].relativeName+'</p>';
				  str += '<p>V.ID:'+result[i].voterIDCardNo+'&nbsp;&nbsp;';
				  if(result[i].memberShipNo!=null){
				   str += '<span style="background-color:#B0C4DE;padding:2px;"><b>M.ID:</b>'+result[i].memberShipNo+'</span></p>';	  
					  }
				  str += '<p>H.NO:'+result[i].houseNo+'&nbsp;&nbsp;|';
				  str += '<span>&nbsp;&nbsp;GENDER : '+result[i].gender+'&nbsp;&nbsp;|</span>';
				  str += '<span>&nbsp;&nbsp;AGE :'+result[i].age+'</span>';
				  str += '</p>';
					if(registrationVoterType=='familyVoterId'){
						str += '<div class="checkboxAlign">';
						str += '<input type="radio" id="checkbox'+i+'" name="searchNewSelect" class="checkbox-custom searchChkboxCls" attr_voterId="'+result[i].voterId+'" attr_tdpCadre_id="'+result[i].tdpCadreId+'" attr_fam_voter_id="'+result[i].voterIDCardNo+'" attr_enrol_yId="'+result[i].enrollmentYearId+'" attr_mobile_no="'+result[i].mobileNumber+'" attr_act_mbl_no="'+result[i].actualMobiNumber+'"/>';
						str += '<label for="checkbox'+i+'" class="checkbox-custom-label" style="font-size:13px;font-weight:200;text-transform:uppercase">&nbsp;</label>';
					}				  
					else if(result[i].enrollmentYearId != 4){
					    str += '<div class="checkboxAlign">';
					str += '<input type="radio" id="checkbox'+i+'" name="searchNewSelect" class="checkbox-custom searchChkboxCls" attr_voterId="'+result[i].voterId+'" attr_tdpCadre_id="'+result[i].tdpCadreId+'" attr_fam_voter_id="'+result[i].voterIDCardNo+'" attr_enrol_yId="'+result[i].enrollmentYearId+'" attr_mobile_no="'+result[i].mobileNumber+'" attr_act_mbl_no="'+result[i].actualMobiNumber+'"/>';
					str += '<label for="checkbox'+i+'" class="checkbox-custom-label" style="font-size:13px;font-weight:200;text-transform:uppercase">&nbsp;</label>';
					}else {
					    str += '<div class="checkboxAlign" style="margin-right: 25px;">';
						str += ' <span style="color:green;font-weight:bold" title="Already Registered for 2016-2018. " alt="Already Registered for 2016-2018. ">&nbsp;&nbsp; Already Registered </span></div>';
				  }
			  str += '</div>';
			  str += '</div>';
		  str += '</div>';
      str += '</li>';
    }
	
	if (result!= null && result.length == 1) {		
		if(result[i].enrollmentYearId == 4){
			if(registrationVoterType=='ownVoterId'){ 
				$('#myVoterId').hide();
			}
			else{
				$('#rlatveVoterId').show();
			}				
		}
	}else{
		if(registrationVoterType=='ownVoterId'){
			$('#myVoterId').show();
			$('#rlatveVoterId').hide();
		}else{
			$('#myVoterId').hide();
			$('#rlatveVoterId').show();
		}
	}
	
  }
  str += '</ul>';
  $("#searchVoterDetailsId").html(str);
  
  if(registrationVoterType == 'ownVoterId'){
	$("#voterBtnsDivId").show();
	  $("#rlatveVoterId").hide();
  }else{
	  $("#rlatveVoterId").show();
	  $("#voterBtnsDivId").hide();
  }
  
  if(result.length > 6)
  {
	$(".searchResults").mCustomScrollbar({
		setHeight:'300px'
	}); 
  }
  
  }
 
  $(document).on("click",".searchChkboxCls",function(){
	  
	$("#submitCadreForm").hide();
	divsEmpty();
	/*var cadreId=$(this).attr("attr_tdpCadre_id");
	if(cadreId != 'null')
	{
		setTimeout(function(){
			     sendOtpToMble();
		}, 1500);
	}else
	{
		return;
	}*/
	
	  $("#searchResultsBackBtn").parent().removeClass("hide");
	  $("#searchResultsBackBtnR").parent().addClass("hide");
	  var voterId = $(this).attr("attr_voterId");
	  var tdpCadreId = $(this).attr("attr_tdpCadre_id");
	  var enrolYear = $(this).attr("attr_enrol_yId");
	  var familyVoterCardNumber=$(this).attr("attr_fam_voter_id");
	  var mobileNumber=$(this).attr("attr_mobile_no");
	  var actualMblNumber=$(this).attr("attr_act_mbl_no");
	  var status = "new";
	   $('#checkVoterId').hide();
	 if(tdpCadreId != null && tdpCadreId > 0 && enrolYear == 3)
		status = "renewal";
	else if(tdpCadreId != null && tdpCadreId > 0 && enrolYear == 4)
		status = "update";
	else 
		tdpCadreId = 0;
	
	if(tdpCadreId != null && tdpCadreId > 0 && enrolYear == 3){
		$("#voterBtnsDivId").hide();
		$("#newUpdateBtnId").hide();
		$("#newRenwalBtnId").show();
	}
	else if(tdpCadreId != null && tdpCadreId > 0 && enrolYear == 4){
		$("#voterBtnsDivId").hide();
		$("#newUpdateBtnId").show();
		$("#newRenwalBtnId").hide();
	}
	else{
		$("#voterBtnsDivId").show();
		$("#newUpdateBtnId").hide();
		$("#newRenwalBtnId").hide();
	}
	
	if(mobileNumber != 'null'){
		 fieldsValusEmpty();
		 if(registrationVoterType == 'ownVoterId')
			$("#memChckBoxModalId").modal('show');
		 $("#checkMblNoId").val(mobileNumber);
		 
	}
	else{
		$("#memChckBoxModalId").modal('hide');
	}
	 $("#voterId").val(voterId);
     $("#tdpCadreId").val(tdpCadreId);
	 $("#statusId").val(status);
	$("#hidnFamlyVoterId").val(familyVoterCardNumber);
	$("#hiddenMblNo").val(actualMblNumber);
	
  });
  
   function getSearchByRelativeVoterIdDetails(){
   	   $("#hiddenFamilyVoterId").val();
	   eachTimeClearFields();
	   $("#populatingDtsDivImgId").show();
	   var flag = 0;
       $(".searchChkboxCls").each(function(){
	  if($(this).is(":checked")){  
		  flag=1;
	  }
   });
   if(flag ==  0)
	  {
		  $("#checkVoterId").html("Please check atleast one voter.");
		  return;
	  }
	  myVoterButtonDetails();
	 var voterId1=0;
	 var familyVoterId=$("#voterId").val();
	 var tdpCadreId=$("#tdpCadreId").val();
	 var status=$("#statusId").val();
	var familyVoterCardNo=$("#hidnFamlyVoterId").val();
	
	 var jsObj={
		 voterId:voterId1,
		 familyVoterId:familyVoterId,
		 cadreId:tdpCadreId,
		 status:status
	 }
	 //alert(333);
	 $.ajax({
		 type:'GET',
		 url: 'getRegistrationPersonDetailsAction.action',
         dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   $("#submitCadreForm").show();
	   $(".newProfile").show();
	   $("#populatingDtsDivImgId").hide();
	   $("#hiddenFamilyVoterId").val(familyVoterId);
	   $(".populatingDtsDivImgId").removeClass('hide');
   	   hideShowDivs(status);
       buildProfileDetails(result,status,familyVoterId);
       buildRelatVoterDetails(familyVoterCardNo);
       buildCasteDetails(result);
       buildEductnQualifns(result);
       buildCadreFamilyDetails(result);
	   buildCadreRelativesDetails(result,"prevNomneReltvId");
	   buildOccupationList(result,"occupationId");
	 })
	
  }
 
  function validateRenewalMemshipDetails(){
	  $("#errorId").html("");
	  var membershipNo = $("#validateRenMemshipId").val();
	  var mobileNo = $("#renewalMobileId").val();
	  var voterId = $("#renewalVoterId").val();
	  if(membershipNo.trim().length == 0 && mobileNo.trim().length == 0 && voterId.trim().length == 0)
	  {
		  $("#errorId").html("Enter Atleast One MembershipNo or MobileNo or VoterId");
		 return;
	  }else{
		 $("#errorId").html(" "); 
	  }
	  if(membershipNo.trim().length>0 && membershipNo.trim().length < 8)
	  { 
		  $("#errorId").html("Enter valid membershipNo");
            return;  
	  }else{
		 $("#errorId").html(" "); 
	  }
	  if(membershipNo.trim().length == 8){
		   var numericExpression1 = /^[0-9]+$/;
        if (!membershipNo.match(numericExpression1)) {
            $("#errorId").html("Enter Number Digits Only");
            return;
        }else{
		 $("#errorId").html(" "); 
	  }
	  }
	  if(mobileNo.trim().length>0 && mobileNo.trim().length < 10 && mobileNo.trim().length > 10 )
	  {
		  $("#errorId").html("Enter valid mobileNo");
            return;  
	  }else{
		 $("#errorId").html(" "); 
	  }
	  if(mobileNo.trim().length == 10)
	  {
		  var numericExpression = /^[0-9]+$/;
			if (!mobileNo.match(numericExpression)) {
				$("#errorId").html("Enter Number Digits Only");
				return;
			}else{
			 $("#errorId").html(" "); 
		  }
	  }  
	  $("#renewalMembershipId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	  var membershipId=$("#validateRenMemshipId").val();
	  var mobileNo=$("#renewalMobileId").val();
	  var voterId=$("#renewalVoterId").val();
	  
	  var jsObj={
		  memberShipNo:membershipId,
		  mobileNo:mobileNo,
		  voterNo:voterId
	  }
	  $.ajax({
		 type:'GET',
		 url: 'getTdpCadresBySearchAction.action',
         dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(result!=null && result.length>0){
		   renewalSearchMembershipDetails(result);
	   }else{
		   $("#renewalMembershipId").html('<h4 class="text-center text-danger">NO DATA AVAILABLE</h4>');
	   }
	 })
	   
  }
  
  /*function searchRenewalMemshipDetails(){
	  var memshipId=0;
	  var name=$("#renewalNameId").val();
	  var mobileNo=$("#renewalMobileId").val();
	  var voterId=$("#renewalVoterId").val();
	  
	  var jsObj={
		  memberShipNo:memshipId,
		  name:name,
		  mobileNo:mobileNo,
		  voterNo:voterId
	  }
	    $.ajax({
		 type:'GET',
		 url: 'getTdpCadresBySearchAction.action',
         dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(result!=null && result.length>0){
		   renewalSearchMembershipDetails(result);
	   }
	 })
  }*/
  
  
 function renewalSearchMembershipDetails(result){
	 divsEmpty();
	   var str = '';
  str += '<ul class="renewalSearchResults">';
  if (result!= null && result.length > 0) {
    for ( var i in result) {
      str += '<li class="profileData" attr_pid="'+i+'" id="profileId'+i+'"  attr_img="'+result[i].imageURL+'">';
		  str += '<div class="media">';
			  str += '<div class="media-left">';
				//str += '<img src="dist/img/default_image.png" class="media-object cadreImage" alt="candidateImage"/>';
			 str +='<img src="https://mytdp.com/'+result[i].imageURL+'" class="media-object cadreImage" alt=""/>';
			  str += '</div>';
			  str += '<div class="media-body" id="profileDataId'+i+'">';
				  str += '<h5 class="text-capitalize" id="candidateName'+i+'">'+result[i].name+ '</h5>';
				  
				  if(result[i].relativeType == 'Mother'){
					if(result[i].gender == 'F')
						str += '<p id="relaNameId'+i+'">D/O: '+result[i].relativeName+'</p>';
					else
						str += '<p id="relaNameId'+i+'">S/O: '+result[i].relativeName+'</p>';
					}
					else if(result[i].relativeType == 'Husband'){
						str += '<p id="relaNameId'+i+'">W/O: '+result[i].relativeName+'</p>';
					}
					else{
						if(result[i].gender == 'F')
							str += '<p id="relaNameId'+i+'">D/O: '+result[i].relativeName+'</p>';
						else
							str += '<p id="relaNameId'+i+'">S/O: '+result[i].relativeName+'</p>';
					}
					
				  //str += '<p id="relaNameId'+i+'" >S/o:'+result[i].relativeName+'</p>';
				  if(result[i].voterId != null && result[i].voterId > 0)
					str += '<p  class="voterCls ownVID">V.ID: <span id="ownVID'+i+'">'+result[i].voterCardNo+'</span>&nbsp;&nbsp;<span class="text-danger"> (Self V.ID)</span>&nbsp;&nbsp;';
				  else if(result[i].familyVoterId != null && result[i].familyVoterId > 0)
					  str += '<p class="voterCls relativeVID"><span>V.ID: </span><span id="relativeVID'+i+'">'+result[i].familyVoterCardNo+'&nbsp;&nbsp;</span><span class="text-warning"> (Relative V.ID)</span>&nbsp;&nbsp;';
				  str += '<b>M.ID: </b><span id="membershipNo'+i+'">'+result[i].memberShipNo+'</span></p>';	  
				  str += '<p>H.NO: <span  id="profileAddress1'+i+'">'+result[i].houseNo+'</span>&nbsp;&nbsp;|';
				  str += '<span>&nbsp;&nbsp;GENDER: <span  id="profileGender'+i+'">'+result[i].gender+'</span>&nbsp;&nbsp;|</span>';
				  if(result[i].enrollmentYearId !=4){
					   str += '<span>&nbsp;&nbsp;AGE :<span  id="profileAge'+i+'">'+result[i].age+'</span></span>';
						str += '</p>';
					  str += '<div class="checkboxAlign">';
						str += '<input type="radio" id="checkbox'+i+'" class="checkbox-custom"/>';
						str += '<label for="checkbox'+i+'" class="checkbox-custom-label searchChkboxClsR" name="renewalRadioBtn" attr_voterId="'+result[i].voterId+'" attr_tdpCadre_id="'+result[i].id+'" attr_enrol_yId="'+result[i].enrollmentYearId+'" attr_relative_voter="'+result[i].familyVoterId+'" attr_number="'+i+'" attr_mobile_no="'+result[i].mobileNo+'" attr_act_mbl_no="'+result[i].occupation+'" attr_img1="'+result[i].imageURL+'" style="font-size:13px;font-weight:200;text-transform:uppercase">&nbsp;</label>';
					  str += '</div>';
				  }else{
					 str += '<span>&nbsp;&nbsp;AGE :<span  id="profileAge'+i+'">'+result[i].age+'</span></span>';
						str += '</p>';
					  str += '<div class="checkboxAlign">';
						str += ' <span style="color:green;font-weight:bold" title="Already Registered for 2016-2018. " alt="Already Registered for 2016-2018. ">&nbsp;&nbsp; Already Registered </span></div>';
				  }
				  str += '<p class="hide" id="mobileNo'+i+'">'+result[i].mobileNo+'</p>';
			  str += '</div>';
		  str += '</div>';
      str += '</li>';
    }
  }
  str += '</ul>';
  $("#renewalMembershipId").html(str);
  if(result.length > 5)
  {
	$(".searchResults").mCustomScrollbar({
		setHeight:'300px'
	}); 
  }
  }
  
//function renMemberDetails(){
	$(document).on("click",".searchChkboxClsR",function(){
	/*populating data*/
	$("#searchResultsBackBtn").parent().addClass("hide");
	$("#searchResultsBackBtnR").parent().removeClass("hide");
	var profileId = $(this).closest(".profileData").attr("attr_pid");
	
	/*relative or own voter id check*/
	if($("#profileId"+profileId).find(".voterCls").hasClass("relativeVID"))
	{
		$(".selectMembership").addClass("animated fadeOut");
		setTimeout(function(){			
			$(".selectMembership").addClass("hide");
			$(".updateProfileR").removeClass("hide");
			$(".updateProfileR").addClass("animated fadeIn");
		},500)
		setTimeout(function(){
			$(".selectMembership").removeClass("animated fadeOut");
			$(".profileDetailsBlockR").removeClass("animated fadeIn");
		},1500)
	}else{
		/*hide and show animation effects*/
		$(".selectMembership").addClass("animated fadeOut");
		setTimeout(function(){
			$(".selectMembership,.renewal,.subBlockR,.newProfile").addClass("hide");
			$(".profileDetailsBlock,.subBlock,").removeClass("hide");
			$(".profileDetailsBlock").addClass("animated fadeIn");
		},500)
		setTimeout(function(){
			$(".selectMembership").removeClass("animated fadeOut");
			$(".profileDetailsBlock").removeClass("animated fadeIn");
		},1500)
	}
	  var voterId = $(this).attr("attr_voterId");
	  var tdpCadreId = $(this).attr("attr_tdpCadre_id");
	  var enrolYear = $(this).attr("attr_enrol_yId");
	  var relativeVoter = $(this).attr("attr_relative_voter");
	  var relativeType = $(this).attr("attr_relativeType");
	  var status = "renewal";
	  $(".renewalN").addClass("hide");
	  if(tdpCadreId != null && tdpCadreId > 0 && enrolYear == 3)
		status = "renewal";
	else if(tdpCadreId != null && tdpCadreId > 0 && enrolYear == 4)
		status = "update";
	  
	  if(relativeVoter != null && relativeVoter > 0){
		  var image=$(this).attr("attr_img1");
		renewalSearchRelativeMembershipDetails($(this).attr("attr_number"),image,tdpCadreId,status,relativeVoter,relativeType);
	  }
	  else{
		  
		 getCadreDetailsForCadre(tdpCadreId,voterId,status);
	  }
});
  
function getCadreDetailsForCadre(tdpCadreId,voterId,status){
	eachTimeClearFields();
	 $("#submitCadreForm").hide();
	
	 $("#populatingDtsDivImgId").show();
	var jsObj={
		 voterId:voterId,
		 familyVoterId:0,
		 cadreId:tdpCadreId,
		 status:status
	 }
	 //alert(111);
	$.ajax({          
		type : 'GET',    
		url : 'getRegistrationPersonDetailsAction.action',  
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)} 
	}).done(function(result){
		$("#familyDetailsDivId").show();
		$("#submitCadreForm").show();
		$("#populatingDtsDivImgId").hide();
		hideShowDivs(status);
		buildProfileDetails(result,status,"0");
		buildCasteDetails(result);
		buildEductnQualifns(result);
		buildCadreFamilyDetails(result);
		
		if(status == "new"){
			buildCadreRelativesDetails(result,"relativeId");
		}else if(status == "update" || status == "renewal"){
			buildCadreRelativesDetails(result,"prevNomneReltvId");
			buildCadreRelativesDetails(result,"relativeId");
		}
		
		 $(".renewal").removeClass('hide');
	});
}

function getCadreDetailsForRelativeCadre(type){
	eachTimeClearFields();
	$("#populatingDtsDivImgId").show();
	$("#submitCadreForm").hide();
	var familyVoterId = $("#votrIdR").val();
	var status = $("#stusIdR").val();
	var jsObj={
		 voterId:0,
		 familyVoterId:$("#votrIdR").val(),
		 cadreId:$("#tdpCdrIdR").val(),
		 status:$("#stusIdR").val()
	 }
	 //alert(222);
	$.ajax({          
		type : 'GET',    
		url : 'getRegistrationPersonDetailsAction.action',  
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)} 
	}).done(function(result){
		$("#populatingDtsDivImgId").hide();
		$("#submitCadreForm").show();
		hideShowDivs(status);
		buildProfileDetails(result,status,familyVoterId);
		buildCasteDetails(result);
		buildEductnQualifns(result);
		buildCadreFamilyDetails(result);
		
		if(status == "new"){
			buildCadreRelativesDetails(result,"relativeId");
		}else if(status == "update" || status == "renewal"){
			buildCadreRelativesDetails(result,"prevNomneReltvId");
			buildCadreRelativesDetails(result,"relativeId");
		}
	});
}
  
getAllConstitencyList();
function getAllConstitencyList()
  {
	  var jsObj={
		  
	  }
	  $.ajax({
		  type:'GET',
		  url:'getAllConstitencyListAction.action',
		 dataType:'json',
	  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	    for(var i in result){
	   if(result[i].id == 0){
          $("#voterConstId").append('<option value='+result[i].id+'>Select District</option>');
	   }else{
	      $("#voterConstId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }
	 }
	  $("#voterConstId").trigger("chosen:updated");
	
  })
  }
  
  
  /*$(document).on("click",".searchChkboxClsR",function(){
	   var image=$(this).attr("attr_img1");
	renewalSearchRelativeMembershipDetails($(this).attr("attr_number"),image);
  });*/
  
  
  function renewalSearchRelativeMembershipDetails(num,image,tdpCadreId,status,relativeVoter,relativeType){
	 var candidateName=$("#candidateName"+num+"").html();
	  var relativeName=$("#relaNameId"+num).html();
	  var voterId=$("#relativeVID"+num).html();
	  var houseNo=$("#profileAddress1"+num).html();
	  var gender=$("#profileGender"+num).html();
	  var age=$("#profileAge"+num).html();
	  var memberShipNo = $("#membershipNo"+num).html();
	  
	var str = '';
      str += '<ul class="renewalSearchRelativeResults">';
       str += '<li class="relativeProfileData" id="relativeProfileId" attr_img="'+image+'">';
		   str += '<div class="media">';
			  str += '<div class="media-left">';
			   str +='<img src="https://mytdp.com/'+image+'" class="media-object cadreImage" alt="" style="width:150px;height:100px"/>';
			     str += '</div>';
			       str += '<div class="media-body" id="relativeProfileDataId">';
				    str += '<h5 class="text-capitalize">'+candidateName+ '</h5>';
					
					/*if(relativeType == 'Mother'){
						if(gender == 'F')
							str += '<p>D/O: '+relativeName+'</p>';
						else
							str += '<p>S/O: '+relativeName+'</p>';
					}
					else if(relativeType == 'Husband'){
						str += '<p>W/O: '+relativeName+'</p>';
					}
					else{
						if(gender == 'F')
							str += '<p>D/O: '+relativeName+'</p>';
						else
							str += '<p>S/O: '+relativeName+'</p>';
					}*/
					str+='<p>'+relativeName+'</p>';
				     //str += '<p>S/O: '+relativeName+'</p>';
			           str += '<p>V.ID: '+voterId+ '&nbsp;&nbsp;<span class="text-warning"> (Relative V.ID)</span></p>';
				        str += '<p>H.NO: <span>'+houseNo+'</span>&nbsp;&nbsp;|';
				      str += '<span>&nbsp;&nbsp;GENDER: <span>'+gender+'</span>&nbsp;&nbsp;|</span>';
				    str += '<span>&nbsp;&nbsp;AGE: <span>'+age+'</span></span>';
				  str += '</p>';
			    str += '<div class="checkboxAlign">';
			 str += '<input type="checkbox" id="checkbox'+num+'" class="checkbox-custom"/>';
		 str += '<label for="checkbox'+num+'" class="checkbox-custom-label searchChkboxClsRelative" attr_voterId="'+voterId+'" style="font-size:13px;font-weight:200;text-transform:uppercase">&nbsp;</label>';
	 str += '</div>';
    str += '</div>';
  str += '</div>';
 str += '</li>';
str += '</ul>';
  $("#renwalMembRelativeId").html(str);
 
 $("#tdpCdrIdR").val(tdpCadreId);
 $("#stusIdR").val(status);
 $("#votrIdR").val(relativeVoter);
}
  /*
$(document).on("click",".searchChkboxCls",function(){
	$("#submitCadreForm").hide();
	divsEmpty();
	var cadreId=$(this).attr("attr_tdpCadre_id");
	if(cadreId != 'null')
	{
		setTimeout(function(){
			     sendOtpToMble();
		}, 1500);
	}else
	{
		return;
	}
	
});
*/
$(document).on("click",".registerNew",function(){
	$(".renewalN").hide();
});

function buildRelatVoterDetails(familyVoterCardNo){
	 if(familyVoterCardNo!=null && familyVoterCardNo!=""){
		 $("#voterIdText").val(familyVoterCardNo);
	 }
}
function sendOtpToMble(){
	
	//var mobileNo=$("#checkMblNoId").val();
	var mobileNo=$("#hiddenMblNo").val();
	
	var jsObj={
		mobileNumber:mobileNo
	}
	$.ajax({
		  type:'GET',
		  url:'getSendOtpDetailsAction.action',
		 dataType:'json',
	  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(result == "failure" && result == "null")
	   {
		$("#otpMsgDivId").html("<span style='color:red;'>Please check once OTP.</span>");  
	   }else
	   {
		 $("#otpMsgDivId").html("<span style='color:green;'>Please Enter OTP of Reference #" +result+ "</span>")
	   }
   });
	
}
function getVoterDetails(){
	if(!fieldsValidation())
	{
		return;
	}
	$("#searchVoterDetailsId").html('<span style="margin-left:150px;"><img src="images/search.gif"/></span>');
	submitVoterIdDetails();
	//var constituencyId=$("#voterConstId").val();
	var voterId=$("#inpVoterId").val();
	
	 var jsObj={
		// constituencyId:constituencyId,
		 VoterCardNumber:voterId
	 }
	 $.ajax({
		  type:'GET',
		  url:'getOnlineCadreRegistrationVoterDetailsAction.action',
		 dataType:'json',
	  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(result!=null && result.length>0){
	  searchCadreVoterDetails(result);
	   }else
	   {
		   $("#searchVoterDetailsId").html("NO DATA AVAILABLE.....")
	   }
   });
}

function getSearchVoterDetails()
{
	divsEmpty();
	if(!fieldsValidationForSearch())
	{
		return;
	}
	submitVoterDetails();
	
	var constituency=$("#constituencyId").val();
	var mandal=$("#mandalList").val();
	var village=$("#panchayatList").val();
	var booth=$("#boothsList").val();
	var type=$("input[name=radioVal]:checked").val();
	var typeVal=$("#serchVoterNameId").val();
	
	 var jsObj={
		 consistencyId:constituency,
		 mandalId:mandal,
		 villageId:village,
		 boothId:booth,
		 type:type,
		 typeValue:typeVal
	 }
	  $.ajax({
		  type:'GET',
		  url:'getOnliCadRegistrSearchVoteDetailsAction.action',
		 dataType:'json',
	  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(result!=null && result.length>0){
			searchCadreVoterDetails(result);
	   }else
	   {
		   $("#searchVoterDetailsId").html("NO DATA AVAILABLE.....")
	   }
   });
}

function fieldsValusEmpty()
{
	$("#statesDivId").val(0).trigger('chosen:updated');
	$("#districtId").val(0).trigger('chosen:updated');
	$("#constituencyId").val(0).trigger('chosen:updated');
	$("#mandalList").val(0).trigger('chosen:updated');
	$("#panchayatList").val(0).trigger('chosen:updated');
	$("#boothsList").val(0).trigger('chosen:updated');
	$("#serchVoterNameId").val('');
	$("#inpVoterId").val('');
	$("#checkMblNoId").val('');
	$("#otpInputId").val('');
	$("#validateRenMemshipId").val('');
	$("#renewalMobileId").val('');
	$("#renewalVoterId").val('');
	$("#renewalMembershipId").html("");
	
	
}

function fieldsValidationForSearch()
{

    var state=$("#statesDivId").val();
	var district=$("#districtId").val();
	var constituency=$("#constituencyId").val();
	var type=$("input[name=radioVal]:checked").val();
	var typeVal=$("#serchVoterNameId").val();
	
	if(state == 0)
	  {
		 $("#errorDivId").html("Please Select State");
		 return false;
	   }
	 if(district == 0)
	  {
		 $("#errorDivId").html("Please Select district");
		 return false;
	   }
	if(constituency == 0)
	  {
		 $("#errorDivId").html("Please Select constituency");
		 return false;
	   }
	 if(typeof type == "undefined")
	  {
		 $("#errorDivId").html("Please select any category.");
		 return false;
	   } 
	 if(typeVal == "")
	  {
		if(type == "name")
		{
		 $("#errorDivId").html("Please Enter Name");
		 return false;
		}
		else if(type == "hNo")
		{
			 $("#errorDivId").html("Please Enter House Number");
		 return false;
		}
		else if(type == "voterId")
		{
			$("#errorDivId").html("Please Enter Voter ID.");
		 return false;
		}
	   }
	
	return true;
}
function fieldsValidation()
{
	var voterId=$("#inpVoterId").val();
	if(voterId == ""){
		$("#voterErrDivId").html("Please Enter Voter ID.");
		return false;
	}
	return true;
}
function divsEmpty()
{
	$("#errorDivId").html("");
	$("#voterErrDivId").html("");
	$("#renErrDivId").html("");
	$("#otpStusErrDivId").html("");
	$("#otpMsgDivId").html("");
	
}

$(document).keypress(function(e) {
			if(e.keyCode==13){
					getVoterDetails();
					getSearchVoterDetails();
					validateRenewalMemshipDetails();
					getSearchByMyVoterIdDetails();
					getSearchByRelativeVoterIdDetails();
				}
});

function confirmOtpDetails()
{
	/*
	 $("#otpStusErrDivId").html("<span style='color:green;'>Your OTP validate Successfully..</span>");
		   setTimeout(function(){
			   $("#memChckBoxModalId").modal('hide');
			 getSearchByMyVoterIdDetails();
			   }, 1500);
		*/	   
	
	var mobileNo=$("#hiddenMblNo").val();
	//var tdpCadreId=$("#tdpCadreId").val();
	var otp=$("#otpInputId").val();
	 var jsObj={
		mobileNumber:mobileNo,
		//tdpCadreId:tdpCadreId,
		 otpTxt:otp
		
	 }
	 $.ajax({
		  type:'GET',
		  url:'getOtpStatusAction.action',
		 dataType:'json',
	  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(result == "success")
	   {
		   $("#otpStusErrDivId").html("<span style='color:green;'>Your OTP validate Successfully..Please wait...</span>");
		    $("#otpStusErrImgId").show();
		   setTimeout(function(){
			   $("#memChckBoxModalId").modal('hide');
			 getSearchByMyVoterIdDetails();
			  $("#otpStusErrImgId").hide();
			   }, 1500);
	   }
});

}
function renFieldsValidation()
{
	var membId=$("#validateRenMemshipId").val();
	var mobileNo=$("#renewalMobileId").val();
	var renVoterId=$("#renewalVoterId").val();
	
	if(membId == "" && mobileNo == "" && renVoterId == "")
	{
		$("#renErrDivId").html("Must contain atleast one parameter value.");
		return false;
	}
	return true;
}

var RtdpCadreId=0;
var RvoterId=0;
var RStatus='update';
/*
$(document).on("click",".searchChkboxClsR",function(){
	//fieldsValusEmpty();
	$("#memChckBoxModalId").modal('show');
	
	RvoterId=$(this).attr("attr_voterId");
	RtdpCadreId=$(this).attr("attr_tdpCadre_id");

	 var voterId = $(this).attr("attr_voterId");
	  var tdpCadreId = $(this).attr("attr_tdpCadre_id");
	  var enrolYear = $(this).attr("attr_enrol_yId");
	  var relativeVoter = $(this).attr("attr_relative_voter");
	  var mobileNumber=$(this).attr("attr_mobile_no");
	  var actualMobileNumber=$(this).attr("attr_act_mbl_no");
	  var status = "renewal";
	  
	  if(tdpCadreId != null && tdpCadreId > 0 && enrolYear == 3)
		status = "renewal";
	  else if(tdpCadreId != null && tdpCadreId > 0 && enrolYear == 4)
		status = "update";
	  //renMemberDetails();
	  RStatus = status;
	  
	  $("#checkMblNoId").val(actualMobileNumber);
	  $("#hiddenMblNo").val(mobileNumber);	  
	
		/*setTimeout(function(){
			     sendOtpToMble();
		}, 1500);*/
//});

function renwalOtpDetails()
{

	/*
	  $("#otpStusErrDivId").html("<span style='color:green;'>Your OTP validate Successfully..</span>");
		   setTimeout(function(){
			   $("#memChckBoxModalId").modal('hide');
			    renMemberDetails();
			   }, 1500);
		*/	  
	
	
	var mobileNo=$("#hiddenMblNo").val();
	var otp=$("#otpInputId").val();
	 var jsObj={
		 mobileNumber:mobileNo,
		 otpTxt:otp
		
	 }
	 $.ajax({
		  type:'GET',
		  url:'getOtpStatusAction.action',
		 dataType:'json',
	  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(result == "success")
	   {
		   $("#otpStusErrDivId").html("<span style='color:green;'>Your OTP validate Successfully..Please wait...</span>");
		   $("#otpStusErrImgId").show();
		   setTimeout(function(){
			   $("#memChckBoxModalId").modal('hide');
			    renMemberDetails();
				$("#otpStusErrImgId").hide();
			   }, 1500);
	   }
});

}
 function validFieldValues()
 {
	 var memberShipNumber=$("#validateRenMemshipId").val();
	 var mobileNumber=$("#renewalMobileId").val();
	  
	  var numeric=/^[0-9]+$/;
	  if(!memberShipNumber.match(numeric) && memberShipNumber != "")
	  {
		    $("#renErrDivId").html("Enter valid membership number.");
            return false;
	  }
	 
	 var numericExpression = /^[0-9]+$/;
        if (!mobileNumber.match(numericExpression) && mobileNumber!= "") {
            $("#renErrDivId").html("Enter valid mobileNo");
            return false;
        }
		return true;
 }
