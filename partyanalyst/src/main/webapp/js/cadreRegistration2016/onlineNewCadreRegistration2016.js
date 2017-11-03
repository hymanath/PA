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
		
		if(district ==5 || district ==13 || district ==6){
			$('#notGreaterCitiesDivId').addClass('hide');
		}else{
			$('#notGreaterCitiesDivId').removeClass('hide');
		}
		
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
		$('#constErrId').html('<img src="images/search.gif">');
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
		$('#wrkConstitErrId').html('<img src="images/search.gif">');
	 }
   var jsObj=
   {				
		districtId:district
	}
    $.ajax({
          type:'GET',
          url: 'getConstituenciesByDistrictForUser.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(id == 1){
			$("#constituencyDivIdImg").hide();
			for(var i in result){
			   /*if(result[i].id == 0){
				  $("#constituencyId").append('<option value='+result[i].id+'>Select Constituency</option>');
			   }else if(result[i].id == 262){
				  $("#constituencyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			   }*/
			   
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
			//if(presntConstituencyId != null && presntConstituencyId!='' && typeof presntConstituencyId != "undefined" &&presntConstituencyId.lenght > 0  ) 
			//{
				$("#PrsntConstutuencyList").val(presntConstituencyId);
				$("#PrsntConstutuencyList").trigger("chosen:updated");
				$('#constErrId').html('');
				 setTimeout(function(){
					 getMandalCorporationsByConstituency(presntConstituencyId,2);
				},500);
			//}
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
			
			$('#wrkConstitErrId').html('');
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
		
		var districtId = $('#districtId').val();
		if(districtId !=5 && districtId !=13 && districtId !=6){
			if(consistency ==331 || consistency ==195 || consistency ==196){
				$('#notGreaterCitiesDivId').addClass('hide');
			}else{
				$('#notGreaterCitiesDivId').removeClass('hide');
			}
		}
		
	 }else if(id==2){
		$("#PrsntMandalList  option").remove();
		$("#PrsntMandalList").append('<option value="0">Select Mandal/Municipality</option>');
		$("#PrsntVillageList  option").remove();
		$("#PrsntVillageList").append('<option value="0">Select Panchayat</option>');
		
		$("#PrsntMandalList").trigger("chosen:updated");
		$("#PrsntVillageList").trigger("chosen:updated");
		 
			$('#mandalErrId').html('<img src="images/search.gif">');
	 }else if(id==3){
		$("#workMandalList  option").remove();
		$("#workMandalList").append('<option value="0">Select Mandal/Municipality</option>');
		$("#workVillageList  option").remove();
		$("#workVillageList").append('<option value="0">Select Panchayat</option>');
		
		$("#workMandalList").trigger("chosen:updated");
		$("#workVillageList").trigger("chosen:updated");
			$('#wrkMadalErrId').html('<img src="images/search.gif">');
	 }
	var jsObj=
   {				
		consistencyId:consistency
   }
    $.ajax({
          type:'GET',
          url: 'getMandalsForConstituenciAction.action',
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
				  $("#PrsntMandalList").append('<option value='+result[i].id+' attr_type="0">Select Mandal</option>');
			   }else{
				  $("#PrsntMandalList").append('<option value='+result[i].id+'  attr_type="'+result[i].mobileNumber+'">'+result[i].name+'</option>');
			   }
			}

		
				var presntMandalsId = 0;
				if(presntLebId != null && presntLebId>0){
						presntMandalsId = presntLebId+'';
						presntMandalsId = parseInt("2"+presntMandalsId);
				}else{
					presntMandalsId = presntMandalId+'';
					presntMandalsId = presntMandalsId.substring(1);
					presntMandalsId = parseInt("1"+presntMandalsId);
				}
		
			//if(presntMandalsId != null && presntMandalsId!='' && typeof presntMandalsId != "undefined" &&presntMandalsId.lenght > 0  ) 
			//{
				 $("#PrsntMandalList").val(presntMandalsId);
				 $("#PrsntMandalList").trigger("chosen:updated");
				
				$('#mandalErrId').html('');
				 setTimeout(function(){
					 getPanchayatWardByMandal(presntMandalsId,2);
				},500);
			//}	   
	   }else if(id==3){		   
		   for(var i in result){
			   if(result[i].id == 0){
				  $("#workMandalList").append('<option value='+result[i].id+' attr_type="0">Select Mandal</option>');
			   }else{
				  $("#workMandalList").append('<option value='+result[i].id+'  attr_type="'+result[i].mobileNumber+'">'+result[i].name+'</option>');
			   }			   
			}
			$("#workMandalList").trigger("chosen:updated");
			 $('#wrkMadalErrId').html('');
	   }
   });
  }
  
   var present_attr_type ="RURAL";
   var work_attr_type ="RURAL";
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
		 $('#wardErrId').html('<img src="images/search.gif">');
		 
		 var present_attr_type = $('#PrsntMandalList :selected ').attr('attr_type');
		 $('#PareaTypeStr').val(present_attr_type);
		 if(present_attr_type =='RURAL'){
			 $('#phamletId').val('');
			$('#appartMentId').addClass('hide');		
			$('#hamletId').removeClass('hide');	
		 }else{
			  $('#paptId').val('');
			 $('#appartMentId').removeClass('hide');		
			 $('#hamletId').addClass('hide');	
		 }
		 
		 console.log(" present area type : "+present_attr_type);
	}else if(id==3){
		$("#workVillageList  option").remove();
		$("#workVillageList").append('<option value="0">Select Panchayat</option>');
		$("#workVillageList").trigger("chosen:updated");
		 $('#wrkVillageErrId').html('<img src="images/search.gif">');
		 
		 
		  var work_attr_type = $('#workMandalList :selected ').attr('attr_type');
		 $('#WareaTypeStr').val(work_attr_type);

		 if(work_attr_type =='RURAL'){
			 $('#dhamletId').val('');
			 $('#appartMentsId').addClass('hide');		
			 $('#hamletsId').removeClass('hide');
		 }else{
			 $('#dAptId').val('');
			  $('#appartMentsId').removeClass('hide');		
			  $('#hamletsId').addClass('hide');	
		 }
		 
		 
	}
				var jsObj ={					
					mandalId:mandal,
					typeId:id
				};
				 $.ajax({
					type : "GET",
					url : "getPanchayatsForMandalAction.action",
					data : {task:JSON.stringify(jsObj)} 
				}).done(function(result){
					//$("#boothDivIdImg").hide();
					$("#boothDivIdImg").hide();
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
						 var presntVillagId =0;
					
						 /*if(localElectionBodyId != null && localElectionBodyId>0){
							 presntVillagId = localElectionBodyId+'';
							 presntVillagId = presntVillagId.substring(1);
						 }else{	*/					
							presntVillagId = presntVillageId+'';
							presntVillagId = presntVillagId.substring(1);
						// }
						
						
							 
						//if(presntVillagId != null && presntVillagId!='' && typeof presntVillagId != "undefined" &&presntVillagId.lenght > 0  ) 
						//{
							 $("#PrsntVillageList").val(presntVillagId);
							 $("#PrsntVillageList").trigger("chosen:updated");
							 $('#wardErrId').html('');
						//}
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
						$('#wrkVillageErrId').html('');
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
		
		$('#distriErrId').html('<img src="images/search.gif">');
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
		$('#wrkDistErrId').html('<img src="images/search.gif">');
		
		if(state == 9999){
			$('.apTsStateCls').addClass('hide');
			$('.shipAddressCls').removeClass('hide');
			
		}
	 else{
			$('.apTsStateCls').removeClass('hide');
			$('.shipAddressCls').addClass('hide');
	 }
		
	 }
   var jsObj=
   {				
				stateId:state
							
	}
    $.ajax({
          type:'GET',
          url: 'getDistrictsForUserStateWise.action',
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
			 $('#distriErrId').html('');	
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
			 $('#wrkDistErrId').html('');
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
						str += '<input type="radio" id="checkbox'+i+'" name="searchNewSelect" class="checkbox-custom searchChkboxCls" attr_voterId="'+result[i].voterId+'" attr_tdpCadre_id="0" attr_fam_voter_id="'+result[i].voterIDCardNo+'" attr_enrol_yId="'+result[i].enrollmentYearId+'" attr_mobile_no="'+result[i].mobileNumber+'" attr_act_mbl_no="'+result[i].actualMobiNumber+'"/>';
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
		 $("#otpInputId").val('');
		 $("#otpMsgDivId").html('');
		 
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
	   
	  // $('#nameId1').prop('readonly',false);
	  // $('#nameId1').removeAttr('readonly');
	   $('#familyDetailsDivId').hide();

	   if(registrationVoterType=='ownVoterId')
			$('#imagDivId').show();
		else
			$('#imagDivId').hide();
	   
   	   $("#hiddenFamilyVoterId").val();
   	   $(".rRenewal").addClass('hide');
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
		  $("#checkVoterId").html("Check atleast one voter.");
		  return;
	  }
	  myVoterButtonDetails();
	 var voterId1=0;
	 var familyVoterId=$("#voterId").val();
	 var tdpCadreId=$("#tdpCadreId").val();
	 var status=$("#statusId").val();
	var familyVoterCardNo=$("#hidnFamlyVoterId").val();
	//console.log(tdpCadreId);
	 var jsObj={
		 voterId:voterId1,
		 familyVoterId:familyVoterId,
		 cadreId:0,
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
					  if(result[i].isCsd != null && result[i].isCsd == 'Y'){
						  str += '<span>&nbsp;&nbsp;AGE :<span  id="profileAge'+i+'">'+result[i].age+'</span></span>';
							str += '</p>';
						  str += '<div class="checkboxAlign">';
							str += '<input type="radio" id="checkbox'+i+'" class="checkbox-custom"/>';
							str += '<label for="checkbox'+i+'" class="checkbox-custom-label searchChkboxClsR" name="renewalRadioBtn" attr_voterId="'+result[i].voterId+'" attr_tdpCadre_id="'+result[i].id+'" attr_enrol_yId="'+result[i].enrollmentYearId+'" attr_relative_voter="'+result[i].familyVoterId+'" attr_number="'+i+'" attr_mobile_no="'+result[i].mobileNo+'" attr_act_mbl_no="'+result[i].occupation+'" attr_img1="'+result[i].imageURL+'" style="font-size:13px;font-weight:200;text-transform:uppercase">&nbsp;</label>';
						  str += '</div>';
					  }
					  else{
						  str += '<span>&nbsp;&nbsp;AGE :<span  id="profileAge'+i+'">'+result[i].age+'</span></span>';
							str += '</p>';
						  str += '<div class="checkboxAlign">';
							str += ' <span style="color:green;font-weight:bold" title="Already Registered for 2016-2018. " alt="Already Registered for 2016-2018. ">&nbsp;&nbsp; Already Registered </span></div>';
					  }
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
  
function renMemberDetails(){
	//$(document).on("click",".searchChkboxClsR",function(){
	/*populating data*/
	$("#searchResultsBackBtn").parent().addClass("hide");
	$("#searchResultsBackBtnR").parent().removeClass("hide");
	var profileId = $(this).closest(".profileData").attr("attr_pid");
		$('#populatingDtsDivImgId').show();
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
			//$(".selectMembership,.renewal,.subBlockR,.newProfile").addClass("hide");
			$(".profileDetailsBlock,.subBlock,").removeClass("hide");
			$(".profileDetailsBlock").addClass("animated fadeIn");
		},500)
		setTimeout(function(){
			$(".selectMembership").removeClass("animated fadeOut");
			$(".profileDetailsBlock").removeClass("animated fadeIn");
		},1500)
	}
	  var voterId = $('.validateROTPCls').attr("attr_voterId");
	  var tdpCadreId = $('.validateROTPCls').attr("attr_tdpCadre_id");
	  var enrolYear = $('.validateROTPCls').attr("attr_enrol_yId");
	  var relativeVoter = $('.validateROTPCls').attr("attr_relative_voter");
	  var relativeType = $('.validateROTPCls').attr("attr_relativeType");
	  var attrnumber = $('.validateROTPCls').attr("attr_number");
	  
	  var status = "renewal";
	  $(".renewalN").addClass("hide");
	//  $(".newNomineeID").addClass("hide");
	 // $(".existingNomineeID").removeClass("hide");
	  $("#familyDetailsDivId").hide();
	  
	  if(tdpCadreId != null && tdpCadreId > 0 && enrolYear == 3)
		status = "renewal";
	else if(tdpCadreId != null && tdpCadreId > 0 && enrolYear == 4)
		status = "update";
	  
	   getCadreDetailsForCadre(tdpCadreId,voterId,status,relativeVoter);
	/*  if(relativeVoter != null && relativeVoter > 0){
		  var image=$('.validateROTPCls').attr("attr_img1");//
		  renewalSearchRelativeMembershipDetails($('.validateROTPCls').attr("attr_number"),image,tdpCadreId,status,relativeVoter,relativeType);
	  }
	  else{		  
		 getCadreDetailsForCadre(tdpCadreId,voterId,status);
	  }
	  */
}
//});

  
function getCadreDetailsForCadre(tdpCadreId,voterId,status,relativeVoter){
	//eachTimeClearFields();
	 $("#submitCadreForm").hide();
	
	 $("#populatingDtsDivImgId").show();
	var jsObj={
		 voterId:voterId,
		 familyVoterId:relativeVoter,
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
		//$("#familyDetailsDivId").show();
			//console.log(result);
			$('#changeNomineeId').prop('checked','checked');
			if(result != null && result.paymentStatus != null && result.paymentStatus =='NOT PAID'){
				var str='<div class="col-md-12 col-xs-12 col-sm-12 " style="margin-top:150px;">';
				str+='	<form id="affiliatedCadreForm" action="https://www.ccavenue.com/shopzone/cc_details.jsp" method="post"  >';
				str+='<input type="hidden" name="ip" value="'+userip+'" readonly>';
				str+='<input type="hidden" name="Merchant_Id" value="M_tdpcbn_2144">';			
				str+='<input type="hidden" name="Order_Id" value="'+result.paymentGatewayVO.orderNo+'">';				
				str+='<input type="hidden" name="Checksum" value="'+result.paymentGatewayVO.checkSum+'">';
				str+='<input type="hidden" name="Redirect_Url" value="'+result.paymentGatewayVO.redirectURL+'">';
				str+='<input type="hidden" name="Amount" value="'+result.paymentGatewayVO.amount+'">';
				str+='<input type="hidden" name="billing_cust_name" value="">';
				str+='<input type="hidden" name="billing_cust_address" value="">';
				str+='<input type="hidden" name="billing_cust_tel" value="">';
				str+='<input type="hidden" name="billing_cust_email" value="">';
				str+='<input type="hidden" name="Merchant_Param" value="">';
				str+='<input type="hidden" name="billing_cust_notes" value="TDP E-Member Registration fee">';
				str+='<input type="hidden" name="billing_zip_code" value="">';
				str+='<input type="hidden" name="delivery_cust_name" maxlength="6" value="Telugau Desam Party">';
				str+='<input type="hidden" name="delivery_cust_address" value="NTR Bhavan, Banjarahills, Road No:12">';
				str+='<input type="hidden" name="delivery_cust_country" value="India">';
				str+='<input type="hidden" name="delivery_cust_state" value="Andhrapradesh & Telangana">';
				str+='<input type="hidden" name="delivery_cust_city" value="Vijayawada & Hyderabad">';
				str+='<input type="hidden" name="delivery_zip_code" value="500008">	'; 

				if(registrationType=="new"){
					str+='<div class="panel-heading new animated fadeIn">';
					str+='	<h3 class="text-left text-muted">à°•à±Šà°¤à±�à°¤ à°¸à°­à±�à°¯à°¤à±�à°µà°‚</h3>';
					str+='    <h3 class="text-left text-capital text-muted m_top10">New Membership <button class="btn btn-xs btn-mini homeCls" style="float:right;"> Home </button></h3>';
					str+='</div>';
				}
				if(registrationType=="renewal"){
					str+='<div class="panel-heading renewal">';
					str+='	<h3 class="text-left text-muted">à°¸à°­à±�à°¯à°¤à±�à°µà°‚  à°ªà±�à°¨à°°à±�à°¦à±�à°§à°°à°£</h3>';
					str+='    <h3 class="text-left text-capital text-muted m_top10">Renewal Membership - <small class="text-capitalize">Using Existing [2014-2016] Membership Number</small>  <button class="btn btn-xs btn-mini homeCls" style="float:right;"> Home </button></h3>';
					str+='</div>';
				} 
				str+='</hr>';
				str+='<div class="container m_top10" id="yourElement">';
				str+='<div class="span12  show-grid" style="position: relative;margin-left:250px">';
				str+= '<div class="span12  show-grid" style="position: relative;">';
				str+= '<p class="text-align"> <b>Cadre Name :</b> '+result.lastName+' </p>';
				str+= '<p class="text-align"> <b>Voter Card NO :</b> '+result.voterCardNo+' </p>';			
				str+= '</div>';
				str+='<p class="text-align" style="font-weight:bold;color:green;"> You are Already Registered for 2016-2018. <span style="color:orange">Payment is pending! </span> </p>';
				str+='<h3 class="text-align"> Please make payment , to complete your Registration .   </h3>';
				str+='</div>';
				str+='</div>';
				str+='<div class="container m_top10" id="yoursElement">';
				str+='<div class="span12  show-grid" style="position: relative;text-align: center;margin-bottom: 25px;">';
				str+='<input type="button" attr_order_id="'+result.paymentGatewayVO.orderNo+'" name="submit button" value="PAY NOW" class="btn btn-warning offset5 paymentStatusCls" onclick="updateTransactionTrackingDtals(\''+result.paymentGatewayVO.orderNo+'\')">'; 
				str+='</div>';
				str+='</div>';
				str+='</form>';
				str+='</div>';
				$('#savingStatusDivId').html(str);
				$('.ExistingPaymentCls').removeClass('hide');
				$(".selectMembership,.renewal").addClass('hide');
			}
			else{
				
				$("#submitCadreForm").show();
				$("#populatingDtsDivImgId").hide();
				hideShowDivs(status);
				buildProfileDetails(result,status,"0"); 
				buildCasteDetails(result);
				buildEductnQualifns(result);
				buildCadreFamilyDetails(result);
				buildOccupationList(result,"occupationId");
				
				if(status == "new"){
					buildCadreRelativesDetails(result,"relativeId");
				}else if(status == "update" || status == "renewal"){
					buildCadreRelativesDetails(result,"prevNomneReltvId");
					buildCadreRelativesDetails(result,"relativeId");
				}		
				 $(".rRenewal").removeClass('hide');
			}
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
	var mobileNo=0;
	var tdpCadreId=$("#tdpCadreId").val();
	
	var jsObj={
		tdpCadreId : tdpCadreId,
		mobileNumber : mobileNo 
	}
	$.ajax({
		  type:'GET',
		  url:'getSendOtpDetailsAction.action',
		 dataType:'json',
	  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(result == "failure" && result == "null")
	   {
		$("#otpMsgDivId").html("<span style='color:red;'>Check once OTP.</span>");  
	   }else
	   {
		 $("#otpMsgDivId").html("<span style='color:green;'>Enter OTP of Reference #" +result+ "</span>")
	   }
   });
	
}
function getVoterDetails(){
	if(!fieldsValidation())
	{
		return;
	}
	registrationVoterType='ownVoterId';
	$("#searchVoterDetailsId").hide();
	$("#myVoterId,#searchVoterDetailsId").show();
	$("#searchVoterDetailsId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	submitVoterIdDetails();
	//var constituencyId=$("#voterConstId").val();
	var voterId=$("#inpVoterId").val();
	
	 var jsObj={
		//constituencyId:constituencyId,
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
		   $("#searchVoterDetailsId").html("May be your voter id card Not Available in present Voter list .Please check once Election Website then try again.If not available in Election Website ,you can register with your family voter id card also.")
	   }
   });
}

function getSearchVoterDetails()
{
	//console.log("registrationVoterType  :"+registrationVoterType);
	
	divsEmpty();
	if(!fieldsValidationForSearch())
	{
		return;
	}
	submitVoterDetails();
	$("#searchVoterDetailsId").html('<span style="margin-left:150px;"><img src="images/search.gif"/></span>');
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
		   $("#searchVoterDetailsId").html("NO DATA AVAILABLE....."); 
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
		 $("#errorDivId").html("Select State");
		 return false;
	   }
	 if(district == 0)
	  {
		 $("#errorDivId").html("Select district");
		 return false;
	   }
	if(constituency == 0)
	  {
		 $("#errorDivId").html("Select constituency");
		 return false;
	   }
	 if(typeof type == "undefined")
	  {
		 $("#errorDivId").html("Select Category.");
		 return false;
	   }
	 if(typeVal == "")
	  {
		if(type == "name")
		{
		 $("#errorDivId").html("Enter Name");
		 return false;
		}
		else if(type == "hNo")
		{
			 $("#errorDivId").html("Enter H No");
		 return false;
		}
		else if(type == "voterId")
		{
			$("#errorDivId").html("Enter Voter ID.");
		 return false;
		}
	   }
	
	return true;
}
function fieldsValidation()
{
	var voterId=$("#inpVoterId").val();
	if(voterId == ""){
		$("#voterErrDivId").html("Enter Voter ID.");
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
/*
$(document).keypress(function(e) {
	if(e.keyCode==13){
			getVoterDetails();
			getSearchVoterDetails();
			validateRenewalMemshipDetails();
			getSearchByMyVoterIdDetails();
			getSearchByRelativeVoterIdDetails();
		}
});
*/
function confirmOtpDetails()
{
	$("#otpStusErrDivId").html("<span style='color:green;'>Your OTP validate Successfully..Please wait...</span>");
	$("#otpStusErrImgId").show();
	$("#otpInputId").val('');
	$("#otpMsgDivId").html('');
	setTimeout(function(){
		$("#memChckBoxModalId").modal('hide');
		getSearchByMyVoterIdDetails();
		$("#otpStusErrImgId").hide();
	}, 1500);
	
	return;
	
	/*
	 $("#otpStusErrDivId").html("<span style='color:green;'>Your OTP validate Successfully..Please wait...</span>");
		    $("#otpStusErrImgId").show();
		   setTimeout(function(){
			   $("#memChckBoxModalId").modal('hide');
			 getSearchByMyVoterIdDetails();
			  $("#otpStusErrImgId").hide();
			   }, 1500);
		  */ 
	
	//var mobileNo=$("#hiddenMblNo").val();
	var tdpCadreId=$("#tdpCadreId").val();
	var otp=$("#otpInputId").val();
	 var jsObj={
		//mobileNumber:mobileNo,
		tdpCadreId:tdpCadreId,
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
			 $("#otpInputId").val('');
			$("#otpMsgDivId").html('');
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

$(document).on("click",".searchChkboxClsR",function(){

	//fieldsValusEmpty();
	$("#memChckBoxModalId").modal('show');
	 $("#otpInputId").val('');
		 $("#otpMsgDivId").html('');
	RvoterId=$(this).attr("attr_voterId");
	RtdpCadreId=$(this).attr("attr_tdpCadre_id");//checkMblNoId

	  var voterId = $(this).attr("attr_voterId");
	  var tdpCadreId = $(this).attr("attr_tdpCadre_id");
	  var enrolYear = $(this).attr("attr_enrol_yId");
	  var relativeVoter = $(this).attr("attr_relative_voter");
	  var mobileNumber=$(this).attr("attr_mobile_no");
	  var actualMobileNumber=$(this).attr("attr_act_mbl_no");
	  var attrnumber=$(this).attr("attr_number");
	  var status = "renewal";
	  
	  $('#tdpCadreId').val(tdpCadreId);
	  if(tdpCadreId != null && tdpCadreId > 0 && enrolYear == 3)
		status = "renewal";
	  else if(tdpCadreId != null && tdpCadreId > 0 && enrolYear == 4)
		status = "update";
	  //renMemberDetails();
	  RStatus = status;
	  
	  $("#checkMblNoId").val(actualMobileNumber);
	  $("#hiddenMblNo").val(mobileNumber);	
	   $("#hiddenCadreIdFrRewl").val(tdpCadreId);
	
		$('.validateROTPCls').attr("attr_voterId",voterId);
		$('.validateROTPCls').attr("attr_tdpCadre_id",tdpCadreId);
		$('.validateROTPCls').attr("attr_enrol_yId",enrolYear);
		$('.validateROTPCls').attr("attr_relative_voter",relativeVoter);
		$('.validateROTPCls').attr("attr_mobile_no",mobileNumber);
		$('.validateROTPCls').attr("attr_act_mbl_no",actualMobileNumber);
		$('.validateROTPCls').attr("attr_number",attrnumber);
		
		/*setTimeout(function(){
			     sendOtpToMble();
		}, 1500);*/

});

//7777
function renwalOtpDetails()
{	
	 $("#otpStusErrDivId").html("<span style='color:green;'>Your OTP validate Successfully..Please wait...</span>");
	$("#otpStusErrImgId").show();
	$("#otpInputId").val('');
	$("#otpMsgDivId").html('');
	setTimeout(function(){
	   $("#memChckBoxModalId").modal('hide');
		renMemberDetails();
		$("#otpStusErrImgId").hide();
	}, 1500);
	return;			
/*
$("#otpStusErrDivId").html("<span style='color:green;'>Your OTP validate Successfully..Please wait...</span>");
		   $("#otpStusErrImgId").show();
		    $("#otpInputId").val('');
		 $("#otpMsgDivId").html('');
		   setTimeout(function(){
			   $("#memChckBoxModalId").modal('hide');
			    renMemberDetails();
				$("#otpStusErrImgId").hide();
			   }, 1500);
	
	return;
*/
	//var mobileNo=$("#hiddenMblNo").val();
	var tdpCadreId=$("#tdpCadreId").val();
	var otp=$("#otpInputId").val();
	 var jsObj={
		 //mobileNumber:mobileNo,
		 tdpCadreId:tdpCadreId,
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
		    $("#otpInputId").val('');
		 $("#otpMsgDivId").html('');
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
            $("#renErrDivId").html("Enter valid mobileNo.");
            return false;
        }
		return true;
 }

function addressFieldsValidation()
{
	var isError=false;
	
	var  emailTxt=$("#emailId").val();
	var name =$("#nameId1").val();  
	var gender =$("#genderId").val();  
	var age =$("#ageId").val();
    var dob =$("#dobId").val();	
    var mobileNo=$("#mobileId1").val();
    var caste=$("#casteListId").val();
    var eductnQual=$("#eductnQualId").val();
	var occupation=$("#occupationId").val();
    var PrvNomneName=$("#prvNomneNameId").val();
	var PrvNomneGendr=$("#prvNomneGendrId").val();
	var PrvNomneAge=$("#prevNomneAgeId").val();
	var PrvNomneReltv=$("#prevNomneReltvId").val();	
	 
	//var wardId=$("#wardsList").val();	
	
	/*if(presntLebId > 0 && wardId ==0){
		$("#wardErr").html("Select Ward .");  
        isError=true;
	}else{
		 $("#wardErr").html("");
	}*/
	 if(name == 0 && name.trim() == '')
	 {
		$("#cadreNameId").html("Enter Name");  
        isError=true;		
	 }else
	 {
		 $("#cadreNameId").html("");
	 }
	 if(gender == 0)
	 {
		$("#cadreGenderId").html("Enter Gender");  
         isError=true;	
	 }else
	 {
		 $("#cadreGenderId").html("");
	 }
	 
	 if(age == 0 && age.trim() == '')
	 {
		$("#cadreAgeId").html("Enter Age");  
        isError=true;	
	 }
	 else if(age <15)
	 {
		 $("#cadreAgeId").html("Min. 15 years."); 
		 isError=true;	
	 }else
	 {
		 $("#cadreAgeId").html("");
	 }
	 var numericExpression2 =  /^[0-9]+$/;
	 if (!age.match(numericExpression2)) {
            $("#cadreAgeId").html("Enter valid Age");
           isError=true;	
        }
	 if(dob == 0 && dob.trim() == '')
	 {
		$("#cadredobId").html("Enter date of birth.");  
         isError=true;		
	 }else
	 {
		 $("#cadredobId").html("");
	 }
	 if(mobileNo.length < 10 )
	 {
		$("#cadreMobileNoId").html("Enter mobileNo");  
      isError=true;		
	 }else
	 {
		 $("#cadreMobileNoId").html("");
	 }
	 var numericExpression = /^[0-9]+$/;
        if (!mobileNo.match(numericExpression)) {
            $("#cadreMobileNoId").html("Enter valid mobileNo");
            isError=true;	
        }
	 if(caste == 0 )
	 {
		$("#cadreCasteId").html("Select caste");  
      isError=true;		
	 }else
	 {
		 $("#cadreCasteId").html("");
	 }
	 if(eductnQual == 0 )
	 {
		$("#cadreEducationId").html("Select Qualification.");  
        isError=true;	
	 }else
	 {
		 $("#cadreEducationId").html("");
	 }
	 if(occupation == 0 )
	 {
		$("#cadreOccupationId").html("Select Occupation");  
         isError=true;		
	 }else
	 {
		 $("#cadreOccupationId").html("");
	 }
	 
	 if(PrvNomneName == 0 && PrvNomneName.trim() == '')
	 {
		$("#prvNomneNameDivId").html("Enter Name");  
        isError=true;	
	 }else
	 {
		 $("#prvNomneNameDivId").html("");
	 }
     if(PrvNomneGendr == 0)
	 {
		 $("#prvNomneGendrDivId").html("select Gender"); 
          isError=true;		 
	 }else
	 {
		 $("#prvNomneGendrDivId").html(""); 
	 }
   	 if(PrvNomneAge == 0 && PrvNomneAge.trim() == '')
	 {
		$("#prevNomneAgeDivId").html("Enter Age"); 
           isError=true;		
	 }else{
		 $("#prevNomneAgeDivId").html("");
	 }
	 
	 var numericExpression1 = /^[0-9]+$/;
	 if (!PrvNomneAge.match(numericExpression1)) {
            $("#prevNomneAgeDivId").html("Enter valid age");
              isError=true;	
        }
	 if(PrvNomneReltv == 0)
	 {
		 $("#prevNomneReltvDivId").html("Select relation type. ");
         isError=true;			 
	 }else{
		  $("#prevNomneReltvDivId").html("");
	 }
	 var existingTdpCadreId = $('#hiddenTdpCadreId').val();
	 var flag = 0;
	  $(".nomineeDetailsCls").each(function(){
		  if($(this).is(":checked")){
				flag=1;
		  }
	  });
      if(flag == 0 && existingTdpCadreId != null && existingTdpCadreId >0)
	  {
		  $("#nomineeDivId").html("Check any nominee.");
		  isError=true;	
	  }
	  
	  
	 var mailExpression = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
    if (emailTxt != null && emailTxt.length>0 && !emailTxt.match(mailExpression)) {
		$("#emailErrId").html("<span style='color:red;'>Enter Valid email address</span>");
		isError=true;
	}else{
	  $("#emailErrId").html("");
	}
	
	var state=$("#PrsntStateList").val();
	var district=$("#PrsntDistrictList").val();
	var constituency=$("#PrsntConstutuencyList").val();
	var mandal=$("#PrsntMandalList").val();
	var village=$("#PrsntVillageList").val();
	if(village==null){
		village = 0;
	}
		//alert(village);
	var prsmtHno=$("#phnoId").val();
	var prsmtAprt=$("#paptId").val();
	var prsmtArea=$("#pareaId").val();
	var prsmtStreet=$("#pStreetId").val();
	var prsmtLandmark=$("#pLandmarkId").val();
	var prsmtHamlet=$("#phamletId").val();
	
	var workState=$("#workStateList").val();
	var workDistrict=$("#workDistrictList").val();
	var workConstituency=$("#workConstuencyList").val();
	var workMandal=$("#workMandalList").val();
	var workVillage=$("#workVillageList").val();
	
	var workHno=$("#dhnoId").val();
	var workAprt=$("#dAptId").val();
	var workArea=$("#dareaId").val();
	var workStreet=$("#dStreetId").val();
	var workLandmark=$("#dLandmarkId").val();
	var workHamlet=$("#dhamletId").val();
	
	//stateErrId
	return isError;
		
	var pAreaTypeStr = $('#PareaTypeStr').val();
	
		if(prsmtHno == null || prsmtHno.length ==0 )
		{
			$("#stateErrPhId").html("<span style='color:red;'>Enter H No.</span>");
			isError=true;
		}else{
			$("#stateErrPhId").html("");
		}	
		if(pAreaTypeStr =="URBAN"){
			if( prsmtAprt == null || prsmtAprt.length ==0 )
			{
				$("#stateErrPaId").html("<span style='color:red;'>Enter Appt Name.</span>");
				isError=true;
			}else{
				$("#stateErrPaId").html("");
			}
		}
		if(prsmtArea == null || prsmtArea.length ==0 )
		{
			$("#stateErrPa1Id").html("<span style='color:red;'>Enter Area Name.</span>");
			isError=true;
		}else{
			$("#stateErrPa1Id").html("");
		}
		
		if(prsmtStreet == null || prsmtStreet.length ==0 )
		{
			$("#stateErrPsId").html("<span style='color:red;'>Enter Steet Name.</span>");
			isError=true;
		}else{
			$("#stateErrPsId").html("");
		}
		
		if(prsmtLandmark == null || prsmtLandmark.length ==0 )
		{
			$("#stateErrPlId").html("<span style='color:red;'>Enter Landmark Name.</span>");
			isError=true;
		}else{
			$("#stateErrPlId").html("");
		}
		
		if(pAreaTypeStr =="RURAL"){
			if(prsmtHamlet == null || prsmtHamlet.length ==0 )
			{
				$("#stateErrPh1Id").html("<span style='color:red;'>Enter Hamlet Name.</span>");
				isError=true;
			}else{
				$("#stateErrPh1Id").html("");
			}
		}
		if(state == null || state == 0)
		{
			$("#stateErrId").html("<span style='color:red;'>Select State</span>");
			isError=true;
		}else{
			$("#stateErrId").html("");
		}
		if(district == null || district == 0)
		{
			$("#distriErrId").html("<span style='color:red;'>Select District</span>");
			isError=true;
		}else{
			$("#distriErrId").html("");
		}
		if(constituency == null || constituency == 0)
		{
			$("#constErrId").html("<span style='color:red;'>Select Constituency</span>");
			isError=true;
		}else{
			$("#constErrId").html("");
		}
		if(mandal == null || mandal == 0)
		{
			$("#mandalErrId").html("<span style='color:red;'>Select Mandal/Town/Division</span>");
			isError=true;
		}else{
			$("#mandalErrId").html("");
		}
		if(village == null || village == 0)
		{
			//alert(1212);
			$("#wardErrId").html("<span style='color:red;'>Select Village/Ward</span>");
			isError=true;
		}else{
			$("#wardErrId").html("");
		}
	
	$("#wrkVillageErrId").html("");
	$("#wrkMadalErrId").html("");
	$("#wrkConstitErrId").html("");
	$("#wrkDistErrId").html("");
	$("#wrkSateErrId").html("");
	$("#stateErrDh1Id").html("");
	$("#stateErrDlId").html("");
	$("#stateErrDsId").html("");
	$("#stateErrDa1Id").html("");
	$("#stateErrDhId").html("");
	$("#stateErrDaId").html("");
	var wAreaTypeStr = $('#WareaTypeStr').val();

	
	if($('#deliveryCheckBox').is(":checked"))
	{
	
		if(workState == null || workState<9999)
		{
			if(workHno == null || workHno.length ==0 )
			{
				$("#stateErrDhId").html("<span style='color:red;'>Enter House No.</span>");
				isError=true;
			}else{
				$("#stateErrDhId").html("");
			}
			if(wAreaTypeStr =="URBAN"){			
				if( workAprt == null || workAprt.length ==0 )
				{
					$("#stateErrDaId").html("<span style='color:red;'>Enter Appt Name.</span>");
					isError=true;
				}else{
					$("#stateErrDaId").html("");
				}
			}
			if(workArea == null || workArea.length ==0 )
			{
				$("#stateErrDa1Id").html("<span style='color:red;'>Enter Area Name.</span>");
				isError=true;
			}else{
				$("#stateErrDa1Id").html("");
			}
			
			if(workStreet == null || workStreet.length ==0 )
			{
				$("#stateErrDsId").html("<span style='color:red;'>Enter Steet Name.</span>");
				isError=true;
			}else{
				$("#stateErrDsId").html("");
			}
			
			if(workLandmark == null || workLandmark.length ==0 )
			{
				$("#stateErrDlId").html("<span style='color:red;'>Enter Landmark Name.</span>");
				isError=true;
			}else{
				$("#stateErrDlId").html("");
			}
			
			if(wAreaTypeStr =="RURAL"){
				if(workHamlet == null || workHamlet.length ==0 )
				{
					$("#stateErrDh1Id").html("<span style='color:red;'>Enter Hamlet Name.</span>");
					isError=true;
				}else{
					$("#stateErrDh1Id").html("");
				}
			}
			
			if(workState == null || workState == 0)
			{
				$("#wrkSateErrId").html("<span style='color:red;'>Select State</span>");
				isError=true;
			}else{
				$("#wrkSateErrId").html("");
			}
			if(workDistrict == null ||workDistrict == 0)
			{
				$("#wrkDistErrId").html("<span style='color:red;'>Select District</span>");
				isError=true;
			}else{
				$("#wrkDistErrId").html("");
			}
			if(workConstituency == null ||workConstituency == 0)
			{
				$("#wrkConstitErrId").html("<span style='color:red;'>Select Constituency.</span>");
				isError=true;
			}else{
				$("#wrkConstitErrId").html("");
			}
			if(workMandal == null ||workMandal == 0)
			{
				$("#wrkMadalErrId").html("<span style='color:red;'>Select Mandal/Town.</span>");
				isError=true;
			}else{
				$("#wrkMadalErrId").html("");
			}
			if(workVillage == null ||workVillage == 0)
			{
				$("#wrkVillageErrId").html("<span style='color:red;'>Select Village/ward.</span>");
				isError=true;
			}else{
				$("#wrkVillageErrId").html("");
			}
		}else if(workState == 9999){
				
				var shipAddress = $('#shipAddress').val();
				if(shipAddress == null || shipAddress=='' || shipAddress.lenght==0)
				{
					$("#wrkShippingErrId").html("<span style='color:red;'>Enter your Courier Address Details.</span>");
					isError=true;
				}else{
					$("#wrkShippingErrId").html("");
				}
			}
		
	}
	else if($('#deliveryAbrodCheckBox').is(":checked"))
	{
		var shipAddress = $('#shipAddress').val();
		if(shipAddress == null || shipAddress=='' || shipAddress.lenght==0)
		{
			$("#wrkShippingErrId").html("<span style='color:red;'>Enter your Courier Address Details.</span>");
			isError=true;
		}else{
			$("#wrkShippingErrId").html("");
		}
	}
	return isError;
}  
  function getDistrictsForStateId(state,id){
	  if(id == 1){
		$("#districtDivIdImg").show();
		$("#panchayatTwnId").show();
		$("#trackDistrictId  option").remove();
		$("#trackDistrictId").append('<option value="0">Select District</option>');
		$("#trackConstituencyId  option").remove();
		$("#trackConstituencyId").append('<option value="0">Select Constituency</option>');
		$("#mandalList  option").remove();
		$("#mandalList").append('<option value="0">Select Mandal/Municipality</option>');
		$("#panchayatList  option").remove();
		$("#panchayatList").append('<option value="0">Select Panchayat</option>');
		$("#boothsList  option").remove();
		$("#boothsList").append('<option value="0">Select Booth</option>');
		
		$("#trackDistrictId").trigger("chosen:updated");
		$("#trackConstituencyId").trigger("chosen:updated");
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
		
		$('#distriErrId').html('<img src="images/search.gif">');
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
		$('#wrkDistErrId').html('<img src="images/search.gif">');
	 }
   var jsObj=
   {				
				stateId:state
							
	}
    $.ajax({
          type:'GET',
          url: 'getDistrictsForUserStateWise.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   $("#districtDivIdImg").hide();
	   if(id == 1){
			 for(var i in result){
			   if(result[i].id == 0){
				  $("#trackDistrictId").append('<option value='+result[i].id+'>Select District</option>');
			   }else{
				  $("#trackDistrictId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			   }
			 }
			 $("#trackDistrictId").trigger("chosen:updated");
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
			 $('#distriErrId').html('');	
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
			 $('#wrkDistErrId').html('');
	   }
   });
  }
  
  $(document).on("click","#findTrackId",function(){
	   $("#errorDivTrackId"). html("");
	  var stateId = $("#stateId").val();
	  var districtId = $("#trackDistrictId").val();
	  var constitunecyId = $("#trackConstituencyId").val();
	  var currentDt = new Date();
		var mm = currentDt.getMonth() + 1;
		var dd = currentDt.getDate();
		var yyyy = currentDt.getFullYear();
		var date = mm + '/' + dd + '/' + yyyy;
	  var startDate = date;
	  var endDate = date;
	var browser1 = 0;
		if(browser1 == 0){
			if(stateId == 0){
				$("#errorDivTrackId").html("Select State");
				return;
			}
		    else if(districtId == 0){
				$("#errorDivTrackId").html("Select District");
				return ;
			}
			else if(constitunecyId == 0){
				$("#errorDivTrackId"). html("Select Constituency");
				return ;
			}			
		}
		browser1 = window.open("tdpAgentAreaMapAction.action?constitunecyId="+constitunecyId+"&startDate="+startDate+"&endDate="+endDate+"");
		//browser1 = window.open("tdpAgentAreaMapAction.action?constitunecyId="+constitunecyId+"&startDate="+"10/20/2016"+"&endDate="+"10/20/2016"+"");
		browser1.focus();
  }); 
function sendOtpToNewMbleNumbr(){
	var mobileNo=$("#changeCheckMblNoId").val();
	var tdpCadreIdR=$("#hiddenCadreIdFrRewl").val();
	
	
	var jsObj={
		tdpCadreId : tdpCadreIdR,
		mobileNumber : mobileNo
		}
	$.ajax({
		  type:'GET',
		  url:'getSendOtpDetailsAction.action',
		 dataType:'json',
	  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(result == "failure" && result == "null")
	   {
		$("#otpMsgDivId").html("<span style='color:red;'>Check once OTP.</span>");  
	   }else
	   {
		 $("#otpMsgDivId").html("<span style='color:green;'>Enter OTP of Reference #" +result+ "</span>")
	   }
   });
}

$(document).on("click","#changeMblNubrId",function(){
	if($("#changeMblNubrId").is(':checked')){
    $(".btnCls").hide();
	$("#chageMblDivId").show(); 
	}else{
    $(".btnCls").show();
    $("#chageMblDivId").hide(); 
	}
});