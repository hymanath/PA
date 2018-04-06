function getOpenPositionDistrictsForState(state,id,num){
	if(num == 0)
		  num='';
	   $("#deptBoardId"+num).html('');
	   $("#errdeptBoardPostnId"+num).html("");
	   $("#deptBoardId"+num).trigger("chosen:updated")
	   $("#deptBoardPostnId"+num).html('');
	   $("#deptBoardPostnId"+num).trigger("chosen:updated")
       $("#depmtsId"+num).html('');
	   $("#depmtsId"+num).trigger("chosen:updated")
	$("#searchDataImgForDistrict"+num).show();
	//state = $("#nominatedStaeId"+num).val();
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
	if(state == 0){
		$("#searchDataImgForDistrict"+num).hide();
		return;
	}else{
		$("#searchDataImgForDistrict"+num).show();
	}
	
	var jsObj=
   {				
		stateId:state,
		boardLevelId:$('#boardLvlId'+num+'').val()
	}
    $.ajax({
	  type:'GET',
	  url: 'getOpenPositionDistrictsForStateAction.action',
	  dataType: 'json',
	  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){   
   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   //location.reload(); 
	   }
	   $("#searchDataImgForDistrict"+num).hide();
	   $("#nominatedDistId"+num).empty();
	   $("#nominatedDistId"+num).append('<option value="0">Select District</option>');
	   for(var i in result){
			$("#nominatedDistId"+num).append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }
		$("#nominatedDistId"+num).trigger("chosen:updated");
		 if(globallevelId ==  3 || globallevelId ==  4 || globallevelId ==  5 || globallevelId ==  7){
			  getOpenPositionConstituenciesForDistrict(gDisId,'nominatedDistId','');
			  $("#nominatedDistId").val(gDisId).trigger('chosen:updated');
			   
		   }
		   if(globallevelId == 3){
			   getDepartments(0);
		   }
   });
}

function getOpenPositionConstituenciesForDistrict(district,id,num){
	if(num == 0)
		  num='';
	   $("#deptBoardId"+num).html('');
	   $("#errdeptBoardPostnId"+num).html("");
	   $("#deptBoardId"+num).trigger("chosen:updated")
	   $("#deptBoardPostnId"+num).html('');
	   $("#deptBoardPostnId"+num).trigger("chosen:updated")
       $("#depmtsId"+num).html('');
	   $("#depmtsId"+num).trigger("chosen:updated")
	$("#searchImgForDistr"+num).show();
	$("#nominatdConstId"+num+"").empty();
	$("#nominatedMandlId"+num+"").empty();
	$("#nominatedPanchayatId"+num+"").empty();					
	$("#nominatdConstId"+num+"").append('<option value="0">Select Constituency</option>');		
	$("#nominatedMandlId"+num+"").append('<option value="0">Select Mandal/Municipality</option>');		
	$("#nominatedPanchayatId"+num+"").append('<option value="0">Select Panchayat</option>');
	
	$("#nominatdConstId"+num+"").trigger("chosen:updated");
	$("#nominatedMandlId"+num+"").trigger("chosen:updated");
	$("#nominatedPanchayatId"+num+"").trigger("chosen:updated");
	
	var jsObj=
   {				
		districtId:district,
		boardLevelId:$('#boardLvlId'+num+'').val()			
	}
    $.ajax({
	  type:'GET',
	  url: 'getOpenPositionConstituenciesForDistrictAction.action',
	  dataType: 'json',
	  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){   
   $("#searchImgForDistr"+num).hide();
   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   location.reload(); 
	   }
	   $("#nominatdConstId"+num).empty();
	   $("#nominatdConstId"+num).append('<option value="0">Select Constituency</option>');
	   for(var i in result){
			$("#nominatdConstId"+num).append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }
		$("#nominatdConstId"+num).trigger("chosen:updated");
		if(globallevelId ==  4  || globallevelId ==  5 || globallevelId ==  7){
			   getOpenPositionMandalsForConstituency('','nominatdConstId');
			   $("#nominatdConstId").val(gConsId).trigger('chosen:updated');
			}
		   if(globallevelId == 4){
			   getDepartments(0);
		   }
   });
}

function getOpenPositionMandalsForConstituency(num,id){
	if(num == 0)
		  num='';
	   $("#deptBoardId"+num).html('');
	   $("#errdeptBoardPostnId"+num).html("");
	   $("#deptBoardId"+num).trigger("chosen:updated")
	   $("#deptBoardPostnId"+num).html('');
	   $("#deptBoardPostnId"+num).trigger("chosen:updated")
       $("#depmtsId"+num).html('');
	   $("#depmtsId"+num).trigger("chosen:updated")
	var constituencyId  =0;
	
	 $("#searchImgForConst"+num).show();
	constituencyId = $("#nominatdConstId"+num).val();
	 if(gConsId != null && gConsId >0){
		constituencyId =gConsId; 
	} 
	//$("#nominatdConstId").val(constituencyId).trigger('chosen:updated');
	$("#nominatedMandlId"+num+"  option").remove();
	$("#nominatedMandlId"+num).append('<option value="0">Select Mandal/Municipality</option>');
	$("#nominatedPanchayatId"+num+" option").remove();
	$("#nominatedPanchayatId"+num).append('<option value="0">Select Panchayat</option>');
	
	$("#nominatedMandlId"+num+"").trigger("chosen:updated");
	$("#nominatedPanchayatId"+num+"").trigger("chosen:updated");
	
	var jsObj=
   {				
		constituencyId:constituencyId,
		boardLevelId:$("#boardLvlId"+num).val()			
	}
    $.ajax({
	  type:'GET',
	  url: 'getMandalMuncilIdsForConstituencyAction.action',
	  dataType: 'json',
	  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){   
   
   $("#searchImgForConst"+num).hide();
   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   location.reload(); 
	   }
	    
	   $("#nominatedMandlId"+num).empty();
	   $("#nominatedMandlId"+num).append('<option value="0">Select Mandal/Municipality</option>');
	   for(var i in result){
			$("#nominatedMandlId"+num).append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
	   }
		$("#nominatedMandlId"+num).trigger("chosen:updated");
		 if(globallevelId ==  5 || globallevelId ==  7){
			  getOpenPositionVillagesForMandal('','nominatedMandlId');
			  $("#nominatedMandlId"+num).val(gMandlId).trigger('chosen:updated');
			}
		   
		   if(globallevelId == 5){
			   getDepartments(0);
		   }
   });
}

function getOpenPositionVillagesForMandal(num,id){
	var mandalId=0;
	$("#errdeptBoardPostnId"+num).html("");
	
	var constituencyId = 0; 
		 $("#searchImgForMandl"+num).show();
	mandalId=$("#nominatedMandlId"+num).val();
	constituencyId = $("#nominatdConstId"+num).val();
	if(gConsId != null && gConsId.length >0){
		constituencyId =gConsId; 
	}
	if(gMandlId != null && gMandlId.length > 0){
		mandalId =gMandlId; 
	}
	
	$("#nominatedPanchayatId"+num+"  option").remove();
	$("#nominatedPanchayatId"+num).append('<option value="0">Select Panchayat</option>');
	$("#nominatedPanchayatId"+num+"").trigger("chosen:updated");
	
	var jsObj=
	{			
		mandalId	:mandalId,
		constituencyId:constituencyId,
		boardLevelId:$('#boardLvlId'+num+'').val()			
	}
    $.ajax({
	  type:'GET',
	  url: 'getPanchaytWardForMandalAction.action',
	  dataType: 'json',
	  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){   
   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   location.reload(); 
	   }
	   	 $("#searchImgForMandl"+num).hide();
	   $("#nominatedPanchayatId"+num).empty();
	   $("#nominatedPanchayatId"+num).append('<option value="0">Select Panchayat/Ward</option>');
	   for(var i in result){
			$("#nominatedPanchayatId"+num).append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
	   }
		$("#nominatedPanchayatId"+num).trigger("chosen:updated");
		
		if(globallevelId == 7){
			   getDepartments(0);
		   }
   });
}

function getDistrictsForStates(state,id,num){
	
	//$(".allcls").hide();
	$("#searchBy").val("");	
	$("#searchErrDiv").html("");
	if(id == "statesDivId"){
		hideDetails();
		$('#districtIdImg').show();
			getConstituenciesForState(state,'constituencyId');
			//$("#searchDataImgForDist").show();
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
			//$("#searchDataImgForDist").show();
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
			//$("#searchDataImgForDist").show();
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
	//document.getElementById('membershipId').checked = true;
	
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
		   $("#districtIdImg").hide();
			$("#districtId").empty();
	   }else if(id == "notCadreStateId"){
		   $("#notCadreDistId").empty();
	   }else if(id == "changestateId"){
		   $("#changedistrictId").empty();
	   }else {
			$("#nominatedDistId"+num).empty();
	   }
	   
		//$("#searchDataImgForDist").hide();
	     //$("#districtId").append('<option value="-1">Please Select District</option>');
     for(var i in result){
		 if(id == "statesDivId"){
			   if(result[i].id == 0){
				  $("#districtId").append('<option value='+result[i].id+'>ALL</option>');
			   }else{
				   if(result[i].id != 517)
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
	 //debugger;
	 $("#searchBy").val("");
	 $("#searchErrDiv").html("");
	 if(id == "districtId"){
		 hideDetails();
		 $("#constituencyIdImg").show();
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
			//$("#searchDataImgForDist").show();
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
		if(id == "districtId")
			getConstituenciesForState($('#statesDivId').val(),'constituencyId');
		/*else if(id == "notCadreDistId")
			getConstituenciesForState($('#notCadreStateId').val(),'notCadreConstId');
		else if(id == "changedistrictId")
			getConstituenciesForState($('#changestateId').val(),'changeConstiId');
		else
			getConstituenciesForState($('#nominatedStaeId').val(),'nominatdConstId');*/		
		return;
	}
	
	//document.getElementById('membershipId').checked = true;
	
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
		    $("#constituencyIdImg").hide();
			$("#constituencyId").empty();
	   }else if(id == "notCadreDistId"){
		   	$("#notCadreConstId").empty();
	   }else if(id == "changedistrictId"){
		   	$("#changeConstiId").empty();
	   }else {
			$("#nominatdConstId"+num).empty();
	   }
	   $("#searchDataImgForConst").hide();
	    //$("#constituencyId").append('<option value="-1">Please Select Constituency</option>');
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
	$("#searchBy").val("");
	$("#searchErrDiv").html("");
	var constituencyId  =0;
	if(id == "constituencyId"){
		hideDetails();
		$("#mandalListImg").show();
			$("#searchDataImgForMandl").show();
			//refreshExistingDetails();
			constituencyId = $('#constituencyId').val();
			$("#mandalList  option").remove();
			$("#mandalList").append('<option value="0">Select Mandal/Municipality</option>');
			$("#panchaytList  option").remove();
			$("#panchaytList").append('<option value="0">Select Panchayat</option>');
			//document.getElementById('membershipId').checked = true;
	}else if(id == "notCadreConstId"){			
			//$("#searchDataImgForDist").show();
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
						$("#mandalListImg").hide();
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
							/*if(result[i].locationId == 120 || result[i].locationId == 1124)
								$("#mandalList").append('<option value="0">Select Mandal/Muncipality</option>');*/
							$("#mandalList").append('<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>');
						}else if(id == "notCadreConstId"){
							/*if(result[i].locationId == 120 || result[i].locationId == 1124)
								$("#notCadreMandlId").append('<option value="0">Select Mandal/Muncipality</option>');*/
								$("#notCadreMandlId").append('<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>');		
						}else if(id == "changeConstiId"){
							/*if(result[i].locationId == 120 || result[i].locationId == 1124)
								$("#changeMandalId").append('<option value="0">Select Mandal/Muncipality</option>');*/
							$("#changeMandalId").append('<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>');
						}else{
							/*if(result[i].locationId == 120 || result[i].locationId == 1124)
								$("#nominatedMandlId"+num).append('<option value="0">Select Mandal/Muncipality</option>');*/
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
   $("#searchBy").val("");
   $("#searchErrDiv").html("");
			var mandalId=0;
			var constituencyId = 0; //cadreSearchDtls
		if(id == "mandalList"){	
			//hideDetails();
                $("#panchaytListImg").show();		
				$("#searchDataImgForPanc").show();
				//refreshExistingDetails();
				mandalId=$("#mandalList").val();
				constituencyId = $('#constituencyId').val();
				$("#panchaytList  option").remove();
				$("#panchaytList").append('<option value="0">Select Panchayat</option>');
		}else if(id == "notCadreMandlId"){			
			//$("#searchDataImgForDist").show();
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
					$("#panchaytListImg").hide();
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
	getConstituenciesForState(0,'constituencyId');
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
		$("#searchBy").val("");
		$("#searchErrDiv").html("");
		//document.getElementById('allId').checked = false;
		//document.getElementById('membershipId').checked = true;
		//$(".allcls").show();
		//refreshExistingDetails();
	} 
	
	var isFree =true;
function getNominatedPostApplication(startIndex)
		{
		
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

		
			//$('#cadreSearchDtls').html(' <img style="margin-left: 400px; margin-top: 20px; width: 200px; height: 150px;" id="" class="offset7" src="images/icons/cadreSearch.gif">');
	
	$("#scrollDivId").show();
	 $("#textId").hide();
	   
	if(startIndex == 0)
	{
		$(".paginationDivId").html('');
	}
		$(".paginationDivId").hide();	
		
		$('#searchLevelErrDiv,#committeePositionIdErr,#nonAfflitCommitteeIdErr').html('');
		$("#cadreDetailsDiv").hide();
		//$("#cadreSearchDtls").show();
		
		var cadreSearchTypeStr =$('.cadreCheckCls:checked').val();
		
		
		var searchBy = $('#searchBy').val().trim();
		var searchRadioType =$('.searchTypeCls:checked').val();
		var parentLocation = 0;
		var panchayatId = $("#panchaytList").val();
		var mandalId = $("#mandalList").val();
		var constituencyId = $("#constituencyId").val();
		var districtId = $("#districtId").val();
		var stateId = $("#statesDivId").val();
		if(searchRadioType ==1)
				searchRadioType ="membershipId";
		else if(searchRadioType ==2)
				searchRadioType ="voterId";
		else if(searchRadioType ==3)
				searchRadioType ="mobileNo";
		else if(searchRadioType ==4)
				searchRadioType ="name";
			
			//alert(searchRadioType);
			
		if(panchayatId !=0 && panchayatId>0)
		{
			if(panchayatId.substr(0,1) == 1){
				  locationLevel = 6;
			}
			else if(panchayatId.substr(0,1) == 2){
				 locationLevel = 8;
				 
			}								
			locationValue = panchayatId.substr(1);
		}
		else if(mandalId !=0 && mandalId>0)
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
		
		else if(constituencyId != 0 && constituencyId>0)
		{
			locationValue = constituencyId;
			locationLevel = 4;	
		}
		else if(districtId != 0 && districtId>0)
		{
			locationValue = districtId;
			locationLevel = 3;
		}
		else if(stateId !=0 && stateId>0){
			locationValue = stateId;
			locationLevel = 2;
		}
		if(searchRadioType == 'membershipId')
		{
			memberShipCardNo = $('#searchBy').val().trim();
			if(searchBy.trim().length == 0 )
			{
				$('#searchErrDiv').html('Please enter Membership Card No.');
				return;
			}
			if(searchBy.trim().length != 8||searchBy.trim().length > 8)
			{
				$('#searchErrDiv').html('Invalid memberShipCardNo.');
				return;	
			}
			else if(searchRadioType=="membershipId"){
					
					var numericExpression = /^[0-9]+$/;
					if(!$('#searchBy').val().trim().match(numericExpression)){
						$('#searchErrDiv').html('Enter  Number Digits Only.');
						return;
					}else{
						$('#searchErrDiv').html(' ');
					}
			}
			/*else if(memberShipCardNo.trim().length != 8)
			{
				alert(3);
				$('#searchErrDiv').html('Invalid memberShipCardNo No.');
				return;				
			}else{
				$('#searchErrDiv').html(' ');
			}*/	
		}			
		if(searchRadioType == 'voterId')
		{
			if(stateId ==0){
			$('#searchErrDiv').html('Please Select State.');
				return;				
			}else{
				$('#searchErrDiv').html(' ');
			}		
			voterCardNo = $('#searchBy').val().trim();
			
			if(searchBy.trim().length == 0 || searchBy.trim() == null )
			{
				$('#searchErrDiv').html('Please enter Voter Card No.');
				return;
			}/*else if(searchBy.trim().length > 12)
			{
				$('#searchErrDiv').html('Invalid voter No.');
				return;
			}*/
		}
		if(searchRadioType == 'mobileNo')
		{	
			mobileNo = $('#searchBy').val().trim();
			if(searchBy.trim().length == 0 )
			{
				$('#searchErrDiv').html('Please enter Mobile No.');
				return;
			}
			if(searchBy.trim().length != 10 ||searchBy.trim().length > 10)
			{
				$('#searchErrDiv').html('Invalid Mobile No.');
				return;
			}
			else if(searchRadioType=="mobileNo"){
					var numericExpression = /^[0-9]+$/;
					if(!$('#searchBy').val().match(numericExpression)){
						$('#searchErrDiv').html('Enter Number Digits Only.');
						return;
					}
			}
			/*else if(mobileNo.trim().length != 10)
			{
				alert(2);
				$('#searchErrDiv').html('Invalid Mobile No.');
				return;				
			}else{
				$('#searchErrDiv').html(' ');
			}*/	
			
		}
	if(searchRadioType == 'name')
		{
			if(stateId ==0){
			$('#searchErrDiv').html('Please Select State.');
				return;				
			}else{
				$('#searchErrDiv').html(' ');
			}	
			if( districtId==0){
			$('#searchErrDiv').html('Please Select District.');
				return;				
			}else{
				$('#searchErrDiv').html(' ');
			}
			if(constituencyId ==0){
			$('#searchErrDiv').html('Please Select Constituency.');
				return;				
			}else{
				$('#searchErrDiv').html(' ');
			}
			searchName = $('#searchBy').val().trim();
		  
		        var numericExpression =  /^[a-z,A-Z," "]+$/;
					if(!$('#searchBy').val().match(numericExpression)){
						$('#searchErrDiv').html('Enter characters Only.');
						return;
					}
			
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
		$('#cadreSearchDtls').html(' <img style="height: 150px;" id="" class="col-md-4 col-md-offset-2 col-xs-6 col-xs-offset-3 col-sm-6 col-sm-offset-3" src="images/icons/cadreSearch.gif">');
		if(isFree){
			 isFree =false;
			 $("#searchDivId").show();
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
			enrollmentId : 0,
			task:"NominatedPostSearch"
		}
		$.ajax({
				type : "POST",
				url : "getCadreSearchDetailsAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				globalSelectedMemberIdsArr = []; // Clearing Array 
				$("#textId").hide();
				 isFree =true;
				  $("#cadreSearchDtls").html('');
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
					$('#membersCountId').show();
					buildCadreDetails(result.previousRoles,"cadre"); 
				}
				 else
				{
					$('#cadreSearchDtls').html("");
					  $('#textId').html("");
					$('#cadreSearchDtls').html("<span style='font-weight:bold;text-align:center;'> No Data Available...</span>");
				} 
			});  
		}
	}
	
   function buildCadreDetails(result,status){ 
	    $("#cadreSearchDtls").html('');
         $("#textId").hide();
		$("#cadreSearchDtls").show();
		$("#scrollDivId").show();
		var str='';
		var str1='';
		
		var isEligibleToDelete=false;
		if(entitlementsArr != null && entitlementsArr.length>0){
			for(var i in entitlementsArr){
				if(entitlementsArr[i].trim() =='CADRE_DELETE_ENTITLEMENT'){
					isEligibleToDelete=true;
				}
			}
		}

		
		str1+='<h4 class="m_0 text-success">APPLICANT PROFILE DETAILS : </h4>';
		str1+='<p>Search Results: <b><u id="cadreSearchSize">'+result.length+'</u></b> Members</p>';
		$("#searchData1").html(str1);
		
			
		if(result != null && result.length >0){
			str +='<ul class="list-inline best-matched-profile ">';
                                
		for(var i in result)
			{	
				if(result[i].nominatedPostCandidateId != null && result[i].nominatedPostCandidateId >0 )
					str +='<li  style="background:lightgrey;height: 250px;">';
				else
					str +='<li style="height: 250px;">';
				
				str +='<div class="img-center">';
				
				
				//str+='<img src="dist/img/profile.png" class="img-responsive img-circle" alt="Profile"/>';
				//str +='</div>';
				//console.log(result[i].id);
				if(result[i].id != null && result[i].id >= 0){ // no cadre search  candidate id
				
					str +='<img style="width: 70px;height:70px;border:1px solid #ddd;" src="https://mytdp.com/images/'+result[i].imageURL+'" class="img-responsive img-circle" alt="Profile"/>';
						str +='</div>';
					str +='<input type="checkbox" attr_cadreId="'+result[i].tdpCadreId+'" class="cadreCls checkboxCls hideShowDivCls" name="checkbox" style="margin:auto;display:block;" id="appProfCheckBoxId" sri attr_nominated_post_candidate_id="'+result[i].tdpCadreId+'" attr_membership_id="" />';
				}else {
						str +='<img style="width: 70px;height:70px;border:1px solid #ddd;" src="https://mytdp.com/images/cadre_images/'+result[i].imageURL+'" class="img-responsive img-circle" alt="Profile"/>';
							str +='</div>';
						// cadre search  candidate id
						str +='<input type="checkbox" attr_cadreId="'+result[i].tdpCadreId+'" class="cadreCls checkboxCls hideShowDivCls" name="checkbox" style="margin:auto;display:block;" id="appProfCheckBoxId" attr_nominated_post_candidate_id="'+result[i].nominatedPostCandidateId+'" attr_membership_id="'+result[i].memberShipCardId+'" />';
						
						/*if(result[i].nominatedPostCandidateId == null){
							result[i].nominatedPostCandidateId = 0;
						}
						if(result[i].nominatedPostCandidateId != null && result[i].nominatedPostCandidateId >0 ){
							str +='<input type="checkbox" attr_cadreId="'+result[i].tdpCadreId+'" class="cadreCls checkboxCls hideShowDivCls" name="checkbox" style="margin:auto;display:block;" id="appProfCheckBoxId" attr_nominated_post_candidate_id="'+result[i].nominatedPostCandidateId+'" attr_membership_id="'+result[i].memberShipCardId+'" />';
						}else{
							str +='<input type="checkbox" attr_cadreId="'+result[i].tdpCadreId+'" class="cadreCls checkboxCls hideShowDivCls" name="checkbox" style="margin:auto;display:block;" id="appProfCheckBoxId" attr_nominated_post_candidate_id="'+result[i].tdpCadreId+'" attr_membership_id="'+result[i].memberShipCardId+'" />';
						}
						*/
				}
			   // str +='<input type="checkbox" style="margin:auto;display:block;" class="" />';
				str +='<p class="m_0 m_top5 text-center cadreName" value='+result[i].cadreName+'><b>'+result[i].cadreName+'</b></p>';
				str +='<p class="m_0 m_top5 text-center cadreVotrCardId" value="'+result[i].voterCardNo+'"><b>VOTERID : </b> '+result[i].voterCardNo+'</p>';
				if(result[i].memberShipCardId != null && result[i].memberShipCardId != "")
				str +='<p class="m_0 text-center cadreMembrShpNo" value="'+result[i].memberShipCardId+'"><b> MID : </b> '+result[i].memberShipCardId+'</p>';
				str +='<p class="m_0 text-center cadreMobilNo" value="'+result[i].mobileNo+'"><b>MOBILE : </b> '+result[i].mobileNo+'</p>';
				str +='<input type="hidden" class="tdpCadreIdCls" value="'+result[i].tdpCadreId+'" attr_nominated_post_candidate_id="'+result[i].nominatedPostCandidateId+'"/>';
				
					if(result[i].addressVO != null && result[i].addressVO.constituencyName != null && result[i].addressVO.constituencyName.length > 0)
					{
						str +='<p class="text-center m_0"><b> ASSEMBLY : </b><span style="white-space: pre-wrap;"> '+result[i].addressVO.constituencyName+'</span></p>';
						
					}else if(result[i].constituency != null && result[i].constituency.length > 0){
						 str +='<p class="text-center m_0">'+result[i].constituency+'</p>';
						
					}else{
						str +='<p class="text-center m_0">&nbsp;</p>';
						
					}
					if(status == "cadre"){
			 str+='<ul class="enrolled-mem" id="">';
					//$("#familyMembersDiv").html(result[i].enrollmentYear);
					if(result[i].enrollmentYears != null && result[i].enrollmentYears.trim().length > 0)
					{
						var years = result[i].enrollmentYears.split(", ");	

						if(years.indexOf("2016") > -1)
							str+='<li class="yes" style="width: 49px;margin :0px;">2016<span></span></li>&nbsp;';
						else
							str+='<li class="no" style="width: 49px;margin :0px;">2016<span></span></li>&nbsp;';
						if(years.indexOf("2014") > -1)
							str+='<li class="yes" style="width: 49px;margin :0px;">2014<span></span></li>&nbsp;';
						else
							str+='<li class="no" style="width: 49px;margin :0px;">2014<span></span></li>&nbsp;';
						if(years.indexOf("2012") > -1)
							str+='<li class="yes" style="width: 49px;margin :0px;">2012<span></span></li>&nbsp;';
						else
							str+='<li class="no" style="width: 49px;margin :0px;">2012<span></span></li>&nbsp;';
						if(years.indexOf("2010") > -1)
							str+='<li class="yes" style="width: 49px;margin :0px;">2010<span></span></li>&nbsp;';
						else
							str+='<li class="no" style="width: 49px;margin :0px;">2010<span></span></li>&nbsp;';
						    //str+='<i style ="margin-left:28px;;cursor:pointer;color:red;" class="glyphicon glyphicon-remove remove-icon removeIconCls" data-toggle="tooltip" data-placement="bottom" title="Remove Candidate"></i>';
					}
					else
					{
						str+='<li class="no" style="width: 49px;margin :0px;">2016<span></span></li>&nbsp;';
						str+='<li class="no" style="width: 49px;margin :0px;">2014<span></span></li>&nbsp;';
						str+='<li class="no" style="width: 49px;margin :0px;">2012<span></span></li>&nbsp;';
						str+='<li class="no" style="width: 49px;margin :0px;">2010<span></span></li>&nbsp;';
					}
					
					
					/*if(isEligibleToDelete){
						str+='<i style ="margin-left:28px;;cursor:pointer;color:red;" attr_nomination_post_candidate_id="'+result[i].nominatedPostCandidateId+'" attr_tdp_cadre_id="'+result[i].tdpCadreId+'" class="glyphicon glyphicon-remove remove-icon removeIconCls" data-toggle="tooltip" data-placement="bottom" title="Remove Candidate"></i>';
					}*/
		   			str+='</ul>	';
			}
					str +='</li>';
				 
			}
			
 		str +='</ul>';	
			$("#cadreSearchDtls").html(str);
			var length = $("#cadreSearchDtls").find("li").length;
			$("#membersCountId").html("<p id='memberCountSpanId'>Search Results <span class='font_weight'>"+result.length+"</span> Members</p>")
			if(result.length>3)
			{
			$(".best-matched-profile").slick({
				slide: 'li',
				slidesToShow: 4,
			   infinite: false
			   });
			   $(".slick-next").css("margin-right","10px;")
			   $(".slick-prev").css("margin-left","10px;")
			}
			$("#textId").show();
		}else{
				str+='No Data Available';
				$("#cadreSearchDtls").html(str);
				$("#textId").hide();
				//$("#cadreSearchDtls").html(No Data Available);			
		}
	    
	}
	function refreshExistingDetails(){ 	
		hideDetails();    
		$("#searchErrDiv").html(""); 
		$("#notCadreErrMsg").html("");
		$("#searchById").val("");
		/*$("#searchBy").val("");
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
		$("#searchErrDiv1").html("");*/
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
	//$("#searchDataImgForDist").show();
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
   //$("#searchDataImgForDist").hide();
    
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
			$("#searchBy").val("");	
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
          url: 'getOpenedPositionsBoardLevelsAction.action',
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
	
	function getOpenedPostionsStates(id,num){
		if(num == 0)
		  num='';
	   $("#deptBoardId"+num).html('');
	   $("#deptBoardId"+num).trigger("chosen:updated")
	   $("#deptBoardPostnId"+num).html('');
	   $("#deptBoardPostnId"+num).trigger("chosen:updated")
       $("#depmtsId"+num).html('');
	   $("#depmtsId"+num).trigger("chosen:updated")
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
		$("#searchDataImgForState"+num).show();
		var jsObj = {			
			boardLevelId:$('#boardLvlId'+num+'').val()
		}
	    $.ajax({
	          type:'GET',
	          url: 'getStatesForOpenedPositionsAction.action',
	          dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		   $("#searchDataImgForState"+num).hide();
	   $("#"+id+''+num+'').empty();
	    
	   if(result != null && result.length >0){
			 $("#"+id+''+num+'').append('<option value="0">Select State </option>');
	     for(var i in result){
			 $("#"+id+''+num+'').append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		 }
	   }
		  $("#"+id+''+num+'').trigger("chosen:updated");
		  $("#nominatedStaeId").val(gsId).trigger('chosen:updated');
		  getOpenPositionDistrictsForState(gsId,'nominatedStaeId','');
		  if(globallevelId == 2){
			   getDepartments(0);
		   }
	   });
	  }
  
  function getDepartmentBoardPositions(num){
	$("#searchDataImgForPos"+num).show();
	$("#errdeptBoardPostnId"+num).html("");
	 var postTypeId=1;
	 var boardLevelId = $("#boardLvlId"+num).val();
     var isActive = $("#nomintdPostId"+num).hasClass("btnActive");
	   if(isActive){
		 postTypeId = $("#nomintdPostId"+num).attr("attr_postid");  
	   }else{
		postTypeId = $("#partyPostId"+num).attr("attr_postid");     
	   }

	   var searchLevelValue=1;
	   if(boardLevelId == 1){
		   searchLevelValue = 1;
	   }
	   else if(boardLevelId == 2){
			if(num >0)
				searchLevelValue = $('#nominatedStaeId'+num).val();
			else
				searchLevelValue = $('#nominatedStaeId').val();	;
	   }
	   else if(boardLevelId == 3){
		   if(num >0)
				searchLevelValue = $('#nominatedDistId'+num).val();
			else
				searchLevelValue = $('#nominatedDistId').val();;
	   }else if(boardLevelId == 4){
		   if(num >0)
				searchLevelValue = $('#nominatdConstId'+num).val();
			else
				searchLevelValue = $('#nominatdConstId').val();;
	   }else if(boardLevelId == 5 || boardLevelId == 6){
		   if(num >0)
				searchLevelValue = $('#nominatedMandlId'+num).val();
			else
				searchLevelValue = $('#nominatedMandlId').val();;
	   }else if(boardLevelId == 7){
		  ;
	   }
	   if(num==0)
		   num='';
	   var boardId =  $("#deptBoardId"+num).val();
	   if(boardId == " "){
		   return;
	   }
	   
	var jsObj = {
		
		depmtId : $("#depmtsId"+num).val(),
		boardId :boardId,
		boardLevelId : $("#boardLvlId"+num).val(),
		searchLevelValue:searchLevelValue,
		searchLevelId:0,
		nominatedPostCandId:globalNPCandiId
	}
    $.ajax({
          type:'GET',
          url: 'getDepartmentBoardPositionsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   $("#searchDataImgForPos"+num).hide();
    $("#deptBoardPostnId"+num).empty();
   if(result != null && result.length >0){
	  //$("#deptBoardPostnId"+num).append('<option value="" >Select Board Position</option>');
	  
	   if(result[0].status != "Applied"){
		   $("#deptBoardPostnId"+num).append('<option value="0">Any</option>');
	  } 
		for(var i in result){
			if(result[i].name != null && result[i].id == globalposId){
					$("#deptBoardPostnId"+num).append('<option selected="selected" value='+result[i].id+'>'+result[i].name+'</option>');
				}else if(result[i].name != null){
					$("#deptBoardPostnId"+num).append('<option value='+result[i].id+'>'+result[i].name+'</option>');
				}
			}
		$("#deptBoardPostnId"+num).trigger("chosen:updated");
   }else{
	   $("#errdeptBoardPostnId"+num).html('<b style="color:red;"> Already applied to this position.</b>');
	   $("#deptBoardPostnId"+num).trigger("chosen:updated");
   }
   	  
   });
  }
  
    function getDepartmentBoards(num){
		$("#errdeptBoardPostnId"+num).html("");
	$("#searchDataImgForDep"+num).show();
	 var postTypeId=1;
	 var boardLevelId = $("#boardLvlId"+num).val();
	 if(num =="")
		 boardLevelId = $("#boardLvlId").val();
     var isActive = $("#nomintdPostId"+num).hasClass("btnActive");
	   if(isActive){
		 postTypeId = $("#nomintdPostId"+num).attr("attr_postid");  
	   }else{
		postTypeId = $("#partyPostId"+num).attr("attr_postid");     
	   }
	   var searchLevelValue=1;
	   if(boardLevelId == 1){
		   searchLevelValue = 1;
	   }
	   else if(boardLevelId == 2){
			if(num != "" && num >0)
				searchLevelValue = $('#nominatedStaeId'+num).val();
			else
				searchLevelValue = $('#nominatedStaeId').val();	;
	   }
	   else if(boardLevelId == 3){
		   if(num != "" && num >0)
				searchLevelValue = $('#nominatedDistId'+num).val();
		   else
				searchLevelValue = $('#nominatedDistId').val();
		}else if(boardLevelId == 4){
		   if(num != "" && num >0)
				searchLevelValue = $('#nominatdConstId'+num).val();
			else
				searchLevelValue = $('#nominatdConstId').val();;
	   }else if(boardLevelId == 5 || boardLevelId == 6){
		   if(num != "" && num >0)
				searchLevelValue = $('#nominatedMandlId'+num).val();
			else
				searchLevelValue = $('#nominatedMandlId').val();;
	   }else if(boardLevelId == 7){
		  ;
	   }
	   if(num==0)
		   num='';
	   var depmtId = $("#depmtsId"+num).val();
   var jsObj = {
		depmtId : depmtId,
		boardLevelId : $("#boardLvlId"+num).val(),
		searchLevelValue:searchLevelValue,
		searchLevelId:0,
		applicationId:0,
		positionId :0
	}
    $.ajax({
          type:'GET',
          url: 'getDepartmentBoardsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
   $("#searchDataImgForDep"+num).hide();
     $("#deptBoardId"+num).empty(); 
     $("#deptBoardPostnId"+num).empty();   
   if(result != null && result.length >0){
	       $("#deptBoardId"+num).append('<option value=" ">Select Department Board</option>');
			$("#deptBoardId"+num).append('<option value="0">Any</option>');
	   
	   if(depmtId > 0 ){
				for(var i in result){
					if(globalboardId == result[i].id){
						$("#deptBoardId"+num).append('<option selected value='+result[i].id+'>'+result[i].name+'</option>');
					}else{
						$("#deptBoardId"+num).append('<option value='+result[i].id+'>'+result[i].name+'</option>');
					}
			}
	   }
	   if(globalboardId > 0)
		getDepartmentBoardPositions(num);   
   }
   else{
	   //$("#errdeptBoardId"+num).html('<b style="color:red;"> All Boards are filled out.</b>');
		$("#deptBoardId"+num).append('<option value=" ">  </option>'); 
		$("#deptBoardPostnId"+num).val('').trigger('chosen'); 
	}
	  $("#deptBoardId"+num).trigger("chosen:updated");
	   $("#deptBoardPostnId"+num).trigger("chosen:updated");
   });
  }  
  function getDepartments(num){
	  //$("#searchDataImgForDist").show();
	  $("#errdeptBoardPostnId"+num).html("");
	 var postTypeId=0;
	 var boardLevelId = $("#boardLvlId"+num).val();
	 if(num ==0)
		 boardLevelId = $("#boardLvlId").val();
     var isActive = $("#nomintdPostId"+num).hasClass("btnActive");
	   if(isActive){
		 postTypeId = $("#nomintdPostId"+num).attr("attr_postid");  
	   }else{
		postTypeId = $("#partyPostId"+num).attr("attr_postid");     
	   }
	if(num ==0){
		 isActive = $("#nomintdPostId").hasClass("btnActive");
	   if(isActive){
		 postTypeId = $("#nomintdPostId").attr("attr_postid");  
	   }else{
		postTypeId = $("#partyPostId").attr("attr_postid");     
	   }	
	}
	
	   var searchLevelValue=1;
	   if(boardLevelId == 1){
		   searchLevelValue = 1;
		   buildDepartments(postTypeId,boardLevelId,searchLevelValue,num);
	   }
	   else if(boardLevelId == 2){
			if(num >0)
				searchLevelValue = $('#nominatedStaeId'+num).val();
			else
				searchLevelValue = $('#nominatedStaeId').val();	
			if(searchLevelValue >0)
				buildDepartments(postTypeId,boardLevelId,searchLevelValue,num);
	   }
	   else if(boardLevelId == 3){
		   if(num >0)
				searchLevelValue = $('#nominatedDistId'+num).val();
			else
				searchLevelValue = $('#nominatedDistId').val();
			if(searchLevelValue >0)
				buildDepartments(postTypeId,boardLevelId,searchLevelValue,num);
	   }else if(boardLevelId == 4){
		   if(num >0)
				searchLevelValue = $('#nominatdConstId'+num).val();
			else
				searchLevelValue = $('#nominatdConstId').val();
			if(searchLevelValue >0)
				buildDepartments(postTypeId,boardLevelId,searchLevelValue,num);
	   }else if(boardLevelId == 5 || boardLevelId == 6){
		   if(num >0)
				searchLevelValue = $('#nominatedMandlId'+num).val();
			else
				searchLevelValue = $('#nominatedMandlId').val();
			if(searchLevelValue >0)
				buildDepartments(postTypeId,boardLevelId,searchLevelValue,num);
	   }else if(boardLevelId == 7){
		  if(searchLevelValue >0)
				buildDepartments(postTypeId,boardLevelId,searchLevelValue,num);
	   }
  }
  
  function buildDepartments(postTypeId,boardLevelId,searchLevelValue,num){
	$("#searchDataImgForDistrict"+num).show();
	  var jsObj = {
		postType:postTypeId,
		boardLevelId:boardLevelId,
		searchLevelValue : searchLevelValue,
		searchLevelId:0,
		positionId:0
		}
		
    $.ajax({
          type:'GET',
          url: 'getDepartmentsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   $("#searchDataImgForDistrict"+num).hide();
	  if(num == 0)
		  num='';
	   $("#deptBoardId"+num).html('');
	   $("#deptBoardId"+num).trigger("chosen:updated")
	   $("#deptBoardPostnId"+num).html('');
	   $("#deptBoardPostnId"+num).trigger("chosen:updated")
       $("#depmtsId"+num).html('');
	   $("#depmtsId"+num).trigger("chosen:updated")
     
	   if(result != null && result.length >0){
		   $("#depmtsId"+num).append('<option value=" ">Select Department</option>'); 
		   
		   //if(result != null && result.length >1)
			$("#depmtsId"+num).append('<option value="0">Any</option>'); 
		
		 for(var i in result){
			 if(globaldeptId == result[i].id){
				 $("#depmtsId"+num).append('<option selected value='+result[i].id+'>'+result[i].name+'</option>');
			 }else{
				$("#depmtsId"+num).append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			 }
			 
		 }
	   }else{
		  // $("#errdepmtsId"+num).html('<b style="color:red;"> All Departments are filled out.</b>');
		   $("#depmtsId"+num).append('<option value=" "> No Departmets Available </option>'); 
	   }
		  $("#depmtsId"+num).trigger("chosen:updated");
		  if(globaldeptId > 0){
			getDepartmentBoards(num);
		  }
   });
  }
  
getBoardLevels("boardLvlId"); 
//getOpenedPostionsStates("nominatedStaeId"); 
//getDepartments("",1); 
 $(document).on("click",".checkboxCls",function(){
/*
	if($(this).is(":checked")){
		$('.ramakrishnaCls').show();
		$('#searchDivId').show();
	}else{
		$('.ramakrishnaCls').hide();
		$('#searchDivId').hide();
	}*/
   // $(".checkboxCls").prop( "checked" ,false);
	//$( this ).prop( 'checked', true );
	
	
}) ;

$(document).on("click",".boardLvelCls",function(){
	var id = $(this).attr('id');
	$('#'+id+'').val(0);
});

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
  e.find(".cloneImgStaCls").attr("id","searchDataImgForState"+cloneCount);
  e.find(".cloneImgDstCls").attr("id","searchDataImgForDistrict"+cloneCount);
  e.find(".cloneImgPosCls").attr("id","searchDataImgForPos"+cloneCount);
  e.find(".cloneImgDepCls").attr("id","searchDataImgForDep"+cloneCount);
  e.find(".cloneImgConCls").attr("id","searchImgForDistr"+cloneCount);
  e.find(".cloneImgMandlCls").attr("id","searchImgForConst"+cloneCount);
  e.find(".cloneImgPanchCls").attr("id","searchImgForMandl"+cloneCount);
  e.find(".cloneImgDeptCls").attr("id","searchImgForPanch"+cloneCount);
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
  //e.find(".boardLvlCls").attr("class","boardLvelCls");
  e.find(".boardLvlCls").attr("id","boardLvlId"+cloneCount);
  e.find(".boardLvlCls").addClass("validateCls");
  e.find(".boardLvlCls").attr("attr_no",cloneCount);
  getBoardLevels("boardLvlId"+cloneCount);
  e.find(".boardLvlCls").attr("onChange",'showHideByNominatedPost('+cloneCount+');getDepartments('+cloneCount+');getOpenedPostionsStates(\'nominatedStaeId\','+cloneCount+')');
 // e.find(".boardLvlCls").attr("onChange",'');
  
  e.find(".nominatedStaeCls").attr("name",'nominatedPostVO.nominatdList['+cloneCount+'].stateId');
  e.find(".nominatedStaeCls").attr("id","nominatedStaeId"+cloneCount);
 // getOpenedPostionsStates("nominatedStaeId"+cloneCount);
  e.find(".nominatedStaeCls").attr("attr_no",cloneCount);
  e.find(".stateShowsCls").attr("id","statesShowDivId"+cloneCount);
  e.find(".nominatedStaeCls").attr("onChange",'getOpenPositionDistrictsForState(this.value,nominatedStaeId'+cloneCount+','+cloneCount+');getDepartments('+cloneCount+');');
  
  e.find(".nominatedDistCls").attr("name",'nominatedPostVO.nominatdList['+cloneCount+'].districtId');
  e.find(".nominatedDistCls").attr("id","nominatedDistId"+cloneCount);
  e.find(".nominatedDistCls").attr("attr_no",cloneCount);
  e.find(".districtShowsCls").attr("id","districtShowDivId"+cloneCount);
  e.find(".nominatedDistCls").attr("onChange",'getOpenPositionConstituenciesForDistrict(this.value,nominatedDistId'+cloneCount+','+cloneCount+');;getDepartments('+cloneCount+');');
  
  e.find(".nominatdConstCls").attr("name",'nominatedPostVO.nominatdList['+cloneCount+'].constituencyId');
  e.find(".nominatdConstCls").attr("id","nominatdConstId"+cloneCount);
  e.find(".nominatdConstCls").attr("attr_no",cloneCount);
  e.find(".constituencyShowsCls").attr("id","constituencyshowDivId"+cloneCount);
  e.find(".nominatdConstCls").attr("onChange",'getOpenPositionMandalsForConstituency('+cloneCount+',nominatdConstId'+cloneCount+');;getDepartments('+cloneCount+');');
  
  e.find(".nominatedMandlCls").attr("name",'nominatedPostVO.nominatdList['+cloneCount+'].mandalId');
  e.find(".nominatedMandlCls").attr("id","nominatedMandlId"+cloneCount);
  e.find(".nominatedMandlCls").attr("attr_no",cloneCount);
  e.find(".mandalShowsCls").attr("id","mondalShowDivId"+cloneCount);
  e.find(".nominatedMandlCls").attr("onChange",'getOpenPositionVillagesForMandal('+cloneCount+',nominatedMandlId'+cloneCount+');;getDepartments('+cloneCount+');');
   
  e.find(".nominatedPanchayatCls").attr("name",'nominatedPostVO.nominatdList['+cloneCount+'].panchayatId');
  e.find(".nominatedPanchayatCls").attr("id","nominatedPanchayatId"+cloneCount);
   e.find(".nominatedPanchayatCls").attr("attr_no",cloneCount);
  e.find(".panchayatShowsCls").attr("id","panchayatShowDivId"+cloneCount);
  
    e.find(".nominatedPanchayatCls").attr("onChange",';getDepartments('+cloneCount+');');
	
  e.find(".depmtsCls").attr("name",'nominatedPostVO.nominatdList['+cloneCount+'].deptId');  
  e.find(".depmtsCls").attr("id","depmtsId"+cloneCount);
  e.find(".depmtsCls").attr("attr_no",cloneCount);
  //getDepartments(cloneCount,1);
  e.find(".depmtsCls").attr("onChange",'getDepartmentBoards('+cloneCount+',1);');
  
  e.find(".errdepmtscls").attr("id","errdepmtsId"+errdepmtsId);
  
  e.find(".deptBoardCls").attr("name",'nominatedPostVO.nominatdList['+cloneCount+'].deptBoardId');
  e.find(".deptBoardCls").attr("id","deptBoardId"+cloneCount);
  e.find(".deptBoardCls").attr("attr_no",cloneCount);
  e.find(".deptBoardCls").attr("onChange",'getDepartmentBoardPositions('+cloneCount+');');
  
  e.find(".errdeptBoardCls").attr("id","errdepmtsId"+errdepmtsId);

  
  e.find(".deptBoardPostnCls").attr("name",'nominatedPostVO.nominatdList['+cloneCount+'].positions');
  e.find(".deptBoardPostnCls").attr("id","deptBoardPostnId"+cloneCount);
  e.find(".deptBoardPostnCls").attr("multiple","multiple");
  e.find(".deptBoardPostnCls").attr("attr_no",cloneCount);
  
    e.find(".errdeptBoardPostnCls").attr("id","errdeptBoardPostnId"+errdepmtsId);
	
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
	
	  $(".cadreCheckCls").each(function(){
				if($(this).prop('checked')==true && $(this).val() == "Cadre"){
					if(!searchByApplicant()){
						 flag = false;
					}
				}
				
			}); 
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
			var nominatedCandId;
			
			$(".cadreCheckCls").each(function(){
				if($(this).is(":checked")==true && $(this).val() == "Cadre"){
					$(".checkboxCls").each(function(){
						if($(this).is(":checked")){
							cadreName = $(this).parent().find(".cadreName").text();
							cadreId = $(this).parent().find(".tdpCadreIdCls").attr("value");
							nominatedCandId = $(this).parent().find(".tdpCadreIdCls").attr("attr_nominated_post_candidate_id");
							cadreVoterId = $(this).parent().find(".cadreVotrCardId").attr("value");
							cadreMobilNo = $(this).parent().find(".cadreMobilNo").attr("value");
							if(nominatedCandId != null && nominatedCandId >0 ){
								$(".nominatedCandId").val(nominatedCandId);
							}else{
								$(".tdpCadreId").val(cadreId);
							}
							
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
		$(".involveBlock").each(function(){
			var cadreId = $(this).attr("attr_cadreId");
			if(cadreId != null && cadreId.length > 0)	
			candidateId += cadreId+",";	
						
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
		$("#submitBtnId").hide();
		//$( "#addressCheckId" ).prop( "checked", false );
		if($("#addressCheckId").is(":checked")){
			$("#addressCheckId1").val(true);
		}else{
			$("#addressCheckId1").val(false);
		}
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
			saveFlag =true;
			//if (confirm('Application Received Successfully...')) {
				//location.reload();
				//clearAssignValues();
			//}
			$("#savingStatusDivId").html("<span style='color: green;font-size:22px;'>Application Received Successfully...</span>");
			
			setTimeout(function(){
			clearAssignValues();
			refreshExistingDetailsInNominatedLevel();
			refreshExistingDetails();
			}, 2000);
			
				
		}else {
			$("#submitBtnId").show();
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
		$(".validateCls").each(function(){
			$(".errorMsgCls").html("");
			 var clonNo = $(this).attr("attr_no");
			 
				if($(this).val() == 0){
					$(this).parent().find(".chosen-single").css("border","1px solid red");
					errorMsg = "Please Select Board Level";
					flag = false;
				}else{
					errorMsg = '';
						$(this).parent().find(".chosen-single").css("border","1px solid #ddd");
						flag = true;
				}
			
				if($(this).val() == 2 || $(this).val() == 3 || $(this).val() == 4 || $(this).val() == 5 || $(this).val() == 6 || $(this).val() == 7){
							
							if($("#nominatedStaeId"+clonNo).val() == 0 && typeof $("#nominatedStaeId"+clonNo).val() !== "undefined"){
								$("#nominatedStaeId"+clonNo).parent().find(".chosen-single").css("border","1px solid red");
								errorMsg = "Please Select State";
								flag = false;
							}else{
								errorMsg = '';
								$("#nominatedStaeId"+clonNo).parent().find(".chosen-single").css("border","1px solid #ddd");
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
								$("#nominatedDistId"+clonNo).parent().find(".chosen-single").css("border","1px solid #ddd");
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
								$("#nominatdConstId"+clonNo).parent().find(".chosen-single").css("border","1px solid #ddd");
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
								$("#nominatedMandlId"+clonNo).parent().find(".chosen-single").css("border","1px solid #ddd");
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
								$("#nominatedPanchayatId"+clonNo).parent().find(".chosen-single").css("border","1px solid #ddd");
								flag = true;
								} 
				}
			 	 if($("#depmtsId"+clonNo).val() == null || $("#depmtsId"+clonNo).val() == " " || $("#depmtsId"+clonNo).val() == undefined){
						$("#depmtsId"+clonNo).parent().find(".chosen-single").css("border","1px solid red");
								errorMsg = "Please select hilighted";
								flag = false;
				}else{
					errorMsg = '';
						$("#depmtsId"+clonNo).parent().find(".chosen-single").css("border","1px solid #ddd");
								flag = true;
				} 
				if($("#deptBoardId"+clonNo).val() == null || $("#deptBoardId"+clonNo).val() == " " || $("#deptBoardId"+clonNo).val() == undefined){
						$("#deptBoardId"+clonNo).parent().find(".chosen-single").css("border","1px solid red");
							errorMsg = "Please select hilighted";
								flag = false;
				}else{
					errorMsg = '';
						$("#deptBoardId"+clonNo).parent().find(".chosen-single").css("border","1px solid #ddd");
								flag = true;
				} 
								
				if($("#deptBoardPostnId"+clonNo).val() == null || $("#deptBoardPostnId"+clonNo).val() == " " || $("#deptBoardPostnId"+clonNo).val() == undefined){
						$("#deptBoardPostnId"+clonNo).parent().find(".chosen-choices").css("border","1px solid red");
								errorMsg = "Please select hilighted";
								flag = false;
				}else{
					errorMsg = '';
						$("#deptBoardPostnId"+clonNo).parent().find(".chosen-choices").css("border","1px solid #ddd");
								flag = true;
				}  
				if(errorMsg != ''){
					$(this).parent().find(".errorMsgCls").html(errorMsg);
						flag = false;
				}
				
	});
				
		return flag;
	} 

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


$(document).on("click","#addressCheckId",function(){
	
	$('#phoneNumId').val('');
	$('#addressLane1Id').val('');
	$('#houseNumberId').val('');
	$('#addressLane2Id').val('');
	$('#changestateId').val(0);
	$('#changedistrictId').val(0);
	$('#changeConstiId').val(0);
	$('#changeMandalId').val(0);
	$('#changePanchyatId').val(0);
	$('#addPincodeId').val('');
	
	if ($(this).is(':checked')) {
		 $(this).val('true');		
		$("#changePhoneNumberDiv").show();
		getPopulateApplicantDetailsForMember(globalCadreId);
  }
  else {
	  $(this).val('false');
	  $("#changePhoneNumberDiv").hide();
  }
	
});
function getPopulateApplicantDetailsForMember(globalCadreId){ 
 var type = $("input[type='radio']:checked").val();
 $("#addPrcssngImgId").show();
 var id =globalNPCandiId;

		if(id > 0){
			type="Not Cadre";
		}else if(id !== "undefined" || id <=0){
			id =globalCadreId;
			//type="Cadre";
		}else{
			id =globalCadreId;
			//type="Cadre";
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
		  $("#addPrcssngImgId").hide();
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
 $( document ).on("click",".cadreCls",function(){
 
	    involvedCadreIds = [];
	    globalSelectedMemberIdsArr = [];
	    $("#involvedMembers").html('(0 - Members added)');
 
		$('#phoneNumId').val('');
		$('#houseNumberId').val('');
		$('#addressLane1Id').val('');
		$('#addressLane2Id').val('');
		$('#changestateId').val(0);
		$('#changedistrictId').val(0);
		$('#changeConstiId').val(0);
		$('#changeMandalId').val(0);
		$('#changePanchyatId').val(0);
		$('#addPincodeId').val('');
		
		$('.membersBlock').html('');
		$("#appliedPostId").html('');
		$('.jFiler-row').html('');
		
		$('.boardLvlCls').val(0);
		$('.nominatedStaeCls,.nominatedDistCls.nominatdConstCls,.nominatedMandlCls.nominatedPanchayatCls,.depmtsCls,.deptBoardCls,.deptBoardPostnCls').each(function(){
			$(this).empty();
			$(this).trigger("chosen:updated");
		});
		
		$('.tdpCadreId').val('');
		$('.tdpCadreName').val('');
		$('.cadreVoterId').val('');
		$('.cadreMobileNo').val('');
		$('.referCadreIds').val('');
		$('.nominatedCandId').val('');
		$('#candidateTypeId').val('');
		
		$('.chosenSelect').val(0);
		$(".chosenSelect").trigger("chosen:updated");
		if($("#addressCheckId").is(':checked')){ 
			$("#addressCheckId").trigger("click");
		}
		
	if($(this).is(':checked')){ 
	   $(".cadreCls").prop("checked" ,false);
	   $(this).prop( "checked" ,true);
	 //$("#showAndHideDivId").show();
		 globalCadreId = $(this).attr("attr_cadreId"); 
		var candiId = $(this).attr("attr_nominated_post_candidate_id"); 
		globalNPCandiId = $(this).attr("attr_nominated_post_candidate_id"); 
		  if($(this).is(':checked')){
			$(".ramakrishnaCls").show();
			$("#addedRefferalsDiv").show();
			$("#uploadFlDivId").show();
			$("#submitBtnId").show();
			 if(globallevelId > 0){
			   $('#boardLvlId').val(globallevelId).trigger('chosen:updated');
		   }
		   if(globallevelId == 1){
			  getDepartments(0);
		   }
		   if(globallevelId > 1){
			   getLocationByDepartment();
				getOpenedPostionsStates('nominatedStaeId','');
				showHideByNominatedPost('');
		   }
			getCandidateAppliedPostsByCadre(globalCadreId,candiId);
		  }
		
	}else{
			$(".ramakrishnaCls").hide();
			$("#addedRefferalsDiv").hide();
			$("#uploadFlDivId").hide();
			$("#submitBtnId").hide();
		}
});

 function getCandidateAppliedPostsByCadre(globalCadreId,candiId){

	 var type = $("input[type='radio']:checked").val();
	 var cadreId = candiId>0?0:globalCadreId;
		var jsObj={
				globalCadreId :cadreId,
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
			   buildCandidateAppliedPostByCadreDetails(result,cadreId,candiId);
		   } 
	});	
  }
  function buildCandidateAppliedPostByCadreDetails(result,cadreId,candiId){
	 var str = '';
	 var applicationStatus = "applicationStatus";
	 var goPassedStatus =  "goPassedStatus";
	 var singleGoPassedStatus ="";
	 if(result.subList.length > 0 || result.subList1.length > 0){
		 $("#appliedPostForSelectedId").show();
	 if(result.subList != null && result.subList.length > 0){
		 str+='<div class="col-md-12 col-xs-12 col-sm-12 col-lg-12 m_top20">';
                    str+='<div class="bg_ff pad_10" style="border: 1px solid rgb(204, 204, 204);" id="appliedPostId">';
                        	str+='<h4 class="panel-title font_weight">APPLIED POSTS FOR THE SELECTED PROFILE </h4>';
							/*<i class="glyphicon glyphicon-list-alt pull-right" style="cursor:pointer;" title="View documents for all application" onclick="getApplicationDocuments('+cadreId+','+candiId+',0,0,\''+applicationStatus+'\');"></i><i class="glyphicon glyphicon-list-alt pull-right" style="cursor: pointer; right: 12px;color:#008000;" title="View documents for Go application" onclick="getApplicationDocuments('+cadreId+','+candiId+',0,0,\''+goPassedStatus+'\');"></i>*/
							
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
												if(result.subList[i].applStatusId == 1)
                                                	str+='<p class="labelStatus " style="background:orange;" > Pending </p>';
												else if(result.subList[i].applStatusId == 2 || result.subList[i].applStatusId == 4)
                                                	str+='<p class="labelStatus " style="background:red;"> '+result.subList[i].status+' </p>';
												else if(result.subList[i].applStatusId == 3)
                                                	str+='<p class="labelStatus " style="background:lightblue;" > '+result.subList[i].status+' </p>';
												else if(result.subList[i].applStatusId == 9)
                                                	str+='<p class="labelStatus " style="background:gray;">'+result.subList[i].status+' </p>';
												else
													str+='<p class="labelStatus " style="background:green;">'+result.subList[i].status+' </p>';
												
													/*str+='<i class="glyphicon glyphicon-list-alt pull-right" style="cursor:pointer;" title="View documents for this application" onclick="getApplicationDocuments('+cadreId+','+candiId+','+result.subList[i].nominatePostApplicationId+','+result.subList[i].applStatusId+',\''+singleGoPassedStatus+'\');"></i>';*/
													if(result.subList[i].levelName != null){
														str+=''+result.subList[i].level+'-'+result.subList[i].levelName+'→  Dept-'+ result.subList[i].cadreName+"→  Board- "+result.subList[i].subCaste+" →  Position- "+result.subList[i].voterName+" : "+result.subList[i].status+"</br>";
												if(result.subList[i].status == "GO Passed" && result.subList[i].fromDate != null && result.subList[i].toDate != null){
													     str+='<b>Duration : '+" <span style='color: #008000;'> "+result.subList[i].fromDate+"</span> <span style='color: #FFA500;'> to </span> <span style='color: #008000;'>"+result.subList[i].toDate+"</span></b>"; 
													    }
													 str+='</li>';
													}else{
														str+=''+result.subList[i].level+'→' +result.subList[i].subCaste+" → "+result.subList[i].cadreName+" → "+result.subList[i].voterName+" : "+result.subList[i].status+"</br>";
												if(result.subList[i].status == "GO Passed" && result.subList[i].fromDate != null && result.subList[i].toDate != null){
													     str+='<b>Duration : '+" <span style='color: #008000;'> "+result.subList[i].fromDate+"</span> <span style='color: #FFA500;'> to </span> <span style='color: #008000;'>"+result.subList[i].toDate+"</span></b>"; 
													    }
													 str+='</li>';
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
													str+=''+result.subList1[i].level+'-'+result.subList1[i].levelName+ '→Dept- ' + result.subList[i].cadreName+"→Board- "+result.subList1[i].subCaste+" →Position- "+result.subList1[i].voterName+" : "+result.subList1[i].status+"</li>";
													}
													else{
														str+=''+result.subList1[i].level+'→' +result.subList1[i].subCaste+" → "+result.subList1[i].cadreName+" → "+result.subList1[i].voterName+" : "+result.subList1[i].status+"</li>";
													}
											}
                                          str+='</ul>';
                                       str+=' </div>';
                                    str+='</div>';
                               str+=' </div>';
							} /* else{
								str+='<div class="col-md-6 col-xs-12 col-sm-6 col-lg-6">';
                                	str+='<div class="panel panel-default panelPost">';
                                    	str+='<div class="panel-heading">';
                                        	str+='<h4 class="panel-title">Party Post</h4>';
                                        str+='</div>';
                                        str+='<div class="" style="padding: 10px;;text-transform: uppercase;">';
                                        	str+='<h4 style="height:100%;width:100%" class="text-center">No Existing Applied Posts are Available...</h4>';
                                       str+=' </div>';
                                    str+='</div>';
                               str+=' </div>';
							}  */
	 
                           str+='</div>';
                       str+='</div>';
					   //str+='<p class="text-muted">Note: Do you want to apply for more posts select below options</p>';
                   str+='</div>';
				    $("#appliedPostForSelectedId").html(str);
				   }
				   else{
			          $("#appliedPostForSelectedId").css("display","none");
				   }
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
	
	 $('#statesDivId').val(0).trigger('chosen:updated');
	 $('#districtId').empty();
	 getConstituenciesForState(0,'constituencyId');
	 getDistricts();
	 $('#districtId').val(0).trigger('chosen:updated');
	 $('#constituencyId').val(0).trigger('chosen:updated');
	 $('#mandalList').empty();
	 $("#mandalList").append('<option value="0">Select Mandal/Municipality </option>');	
	 $('#mandalList').trigger('chosen:updated');
	 $('#panchaytList').empty();
	 $("#panchaytList").append('<option value="0">Select Panchayat</option>');	
	 $('#panchaytList').trigger('chosen:updated');
	 
     $("#cadreSearchDtls").html('');	
	hideDetails();	

  if ($("#cadreSearchId").is(":checked")) {
		$("#searchMemberDiv").show();
		$("#cadreById").hide();
         $("#addMemberDivId").hide();
         $("#searchBy").val(' ');
         $( "#membershipId" ).prop( "checked", true);
		 if ($("#advanceSearchBtnId").is(":checked")){
		 $("#advanceSearchBtnId").trigger("click");
		 }
	}
	else {
	 $("#searchMemberDiv").hide();
	 $("#cadreById").show();
	 $("#scrollDivId").hide();
	 $("#textId").hide();
	 $("#addMemberDivId").show();
	  
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
 $(".jFiler-row").html('');
 $("#apptmemberDetailsDiv").html('');
  $("#advanceSearchTypeId").val(0);
    var select = new Dropkick("#advanceSearchTypeId");
    select.refresh();
  showHideBySearchType();//Clear Fields  
}
var isNotCadreFree=true;
function notCadresearch(){
	 
		var cadreTypeStr=$("input[name='checkBoxName']:checked").val();
		var searchType=$("input[name='searchBasedOn']:checked").val();
		var searchValue=$("#searchById").val();
		
		if(cadreTypeStr == "Not Cadre"){
			searchType=$("input[name='radioGroup']:checked").val();
			searchValue=$("#searchById").val();
		}
		 
		
		if(searchType == "1")
		{	
			if(searchValue.length == 0 )
			{
				$('#notCadreErrMsg').html('Please Enter Membership No.');
				return;
			}else{
				$('#notCadreErrMsg').html(' ');
			}			
		}                                     
		else if(searchType == "2")
		{
			var AlphaNumericExpression = /^[a-zA-Z0-9]+$/;	
			var numericExpression = /^[0-9]+$/;
			if(searchValue.length == 0 || searchValue == null)
			{
				$('#notCadreErrMsg').html('Please Enter Voter Card No.');
				return;
			}
			/*if(!searchValue.match(AlphaNumericExpression) || !searchValue.match(numericExpression)){
				$('#notCadreErrMsg').html('Enter Alpha Digits Only.');
				return;
			}*/
			/*else if(searchValue.trim().length > 12)
			{
				$('#notCadreErrMsg').html('Invalid voter No.');
				return;	
			}*/else{
				$('#notCadreErrMsg').html(' ');
			}
		}
		else if(searchType == "3")
		{
			var numericExpression = /^[0-9]+$/;
			if(searchValue.length == 0 )
			{
				$('#notCadreErrMsg').html('Please Enter Mobile No.');
				return;
			}		
			if(!searchValue.match(numericExpression)){
				$('#notCadreErrMsg').html('Enter Number Digits Only.');
				return;
			}	
			else if(searchValue.trim().length != 10)
			{
				$('#notCadreErrMsg').html('Invalid Mobile No.');
				return;				
			}else{
				$('#notCadreErrMsg').html(' ');
			}
			
		}
		else if(searchType == 4)
		{
			var numericExpression =  /^[a-z,A-Z," "]+$/;
					if(!$('#searchById').val().match(numericExpression)){
						$('#notCadreErrMsg').html('Enter characters Only.');
						return;
					}
			if(searchValue.length == 0 )
			{
				$('#notCadreErrMsg').html('Please Enter Name.');
				return;
			}
			else if(searchValue.length < 3)
			{
				$('#notCadreErrMsg').html('Please Enter Minimum 3 Characters.');
				return;
			}else{
				
				$('#notCadreErrMsg').html(' ');
			}
		}
		$("#textId").hide();
		$('#cadreSearchDtls').html(' <img style="margin-left: 400px; margin-top: 20px; width: 200px; height: 150px;" id="" class="offset7" src="images/icons/cadreSearch.gif">');
	$("#searchData1,#searchData").html('');

	if(isNotCadreFree){
		isNotCadreFree = false;
		
 $("#scrollDivId").show();
$("#searchDivId").show();
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
       $("#cadreSearchDtls").html('');					
					if(result != null){
						buildCadreDetails(result,"notCadre"); 				
					}
					else if(cadreTypeStr =="Cadre"){
						getNominatedPostApplication(0);
					}
					else{
						//$("#cadreSearchDtls").hide();
						$("#cadreSearchDtls").html("No Data Available...");
					}
					
				});
			}
		}
 $(document).on("click","#searchbtn",function(){
	 var advanSearchVal = $("#advanceSearchBtnId").is(':checked');
	if(advanSearchVal){
		getAdvanceSearchDetails();
		return;
	}
	 
	  var value = $("input[name='checkBoxName']:checked").val();
	  $("#addedRefferalsDiv").hide();
	  	$('.ramakrishnaCls').hide();
		$('#searchDivId').hide();
		$("#uploadFlDivId").hide();
		$("#submitBtnId").hide();
		$("#membersCountId").html(""); 
        $("#searchErrDiv").html(""); 
		$("#notCadreErrMsg").html(""); 
        $("#memberCountSpanId").html("");  		
	  if(value == "Cadre"){
			getNominatedPostApplication(0);
		}
		else if(value == "Not Cadre"){
			notCadresearch();
		}
 });
 
function getAdvanceSearchDetails(){
	$("#searchErrDiv").html("");
	$("#scrollDivId").show();
	$("#textId").hide();
	$("#searchData1").html("");
	$("#membersCountId").html(""); 
	
	var searchType;
	var searchValue = 0;
	var locationType;
	var locationVal;
	var gender = "";
	
	var manMunId = $("#mandalList").val();
	if(manMunId == 0){
		$("#searchErrDiv").html("Select Mandal/Muncipality/Corporation");
		return;
	}
	
	if(manMunId > 0){
		if(manMunId.substr(0,1) == 2){
			  locationType = "mandal";
		}
		else if(manMunId.substr(0,1) == 1){
			 locationType = "muncipality";
			 
		}								
		locationVal = manMunId.substr(1);
	}
	
	var advanceSearchType = $('input:radio[name=advncdSearch]:checked').val();
	
	if(advanceSearchType == 2){
		searchType = "caste";
		searchValue = $("#advancSearchSelectId").val();
		if(searchValue == 0){
			$("#searchErrDiv").html("Select Caste");
			return;
		}
	}
	else if(advanceSearchType == 1){
		searchType = "gender";
		gender = $("#advancSearchSelectId").val();
		if(gender == 0){
			$("#searchErrDiv").html("Select Gender");
			return;
		}
	}
	else if(advanceSearchType == 3){
		searchType = "age";
		searchValue = $("#advancSearchSelectId").val();
		if(searchValue == 0){
			$("#searchErrDiv").html("Select Age");
			return;
		}
	}
	else if(advanceSearchType == 4){
		searchType = "casteGroup";
		searchValue = $("#advancSearchSelectId").val();
		if(searchValue == 0){
			$("#searchErrDiv").html("Select Caste Group");
			return;
		}
	}
	else if(advanceSearchType == 5){
		searchType = "education";
		searchValue = $("#advancSearchSelectId").val();
		if(searchValue == 0){
			$("#searchErrDiv").html("Select Education");
			return;
		}
	}
	$('#cadreSearchDtls').html(' <img style="height: 150px;" id="" class="col-md-4 col-md-offset-2 col-xs-6 col-xs-offset-3 col-sm-6 col-sm-offset-3" src="images/icons/cadreSearch.gif">');
	$("#searchDivId").show();
	var jsObj={
		searchType:searchType,
		searchValue:searchValue,
		locationType:locationType,
		locationVal:locationVal,
		gender:gender
	}
	//$("#apptmemberDetailsDiv").html('');
		$.ajax({
			type : 'POST',
			url : 'getNewCadreSearchBySearchTypeAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#cadreSearchDtls").html("");
			if(result !=null && result.length>0){
			buildAdvancedSearchDetails(result);		
			}else{
				$("#cadreSearchDtls").html("<center><h4>No Data Available</h4></center>");
			}
	  }); 
}

function buildAdvancedSearchDetails(result){
	$("#cadreSearchDtls").html('');
         $("#textId").hide();
		$("#cadreSearchDtls").show();
		$("#scrollDivId").show();
		var str='';
		var str1='';
		

		
		str1+='<h4 class="m_0 text-success">APPLICANT PROFILE DETAILS : </h4>';
		str1+='<p>Search Results: <b><u id="cadreSearchSize">'+result.length+'</u></b> Members</p>';
		$("#searchData1").html(str1);
		
			
		if(result != null && result.length >0){
			str +='<ul class="list-inline best-matched-profile ">';
                                
		for(var i in result)
			{	
				/*if(result[i].nominatedPostCandidateId != null && result[i].nominatedPostCandidateId >0 )
					str +='<li  style="background:lightgrey;height: 213px;">';
				else*/
					str +='<li style="height: 213px;">';
				
				str +='<div class="img-center">';
				
				
				//str+='<img src="dist/img/profile.png" class="img-responsive img-circle" alt="Profile"/>';
				//str +='</div>';
				//console.log(result[i].id);
				if(result[i].id != null && result[i].id >= 0){ // no cadre search  candidate id
				
					str +='<img style="width: 70px;height:70px;border:1px solid #ddd;" src="https://mytdp.com/'+result[i].imageURL+'" class="img-responsive img-circle" alt="Profile"/>';
						str +='</div>';
					str +='<input type="checkbox" attr_cadreId="'+result[i].id+'" class="cadreCls checkboxCls hideShowDivCls" name="checkbox" style="margin:auto;display:block;" id="appProfCheckBoxId" sri attr_nominated_post_candidate_id="'+result[i].id+'" attr_membership_id="" />';
				}else {
						str +='<img style="width: 70px;height:70px;border:1px solid #ddd;" src="https://mytdp.com/'+result[i].imageURL+'" class="img-responsive img-circle" alt="Profile"/>';
							str +='</div>';
						// cadre search  candidate id
						str +='<input type="checkbox" attr_cadreId="'+result[i].id+'" class="cadreCls checkboxCls hideShowDivCls" name="checkbox" style="margin:auto;display:block;" id="appProfCheckBoxId" attr_nominated_post_candidate_id="'+result[i].appointmentCandidateId+'" attr_membership_id="'+result[i].memberShipId+'" />';
						
						/*if(result[i].nominatedPostCandidateId == null){
							result[i].nominatedPostCandidateId = 0;
						}
						if(result[i].nominatedPostCandidateId != null && result[i].nominatedPostCandidateId >0 ){
							str +='<input type="checkbox" attr_cadreId="'+result[i].tdpCadreId+'" class="cadreCls checkboxCls hideShowDivCls" name="checkbox" style="margin:auto;display:block;" id="appProfCheckBoxId" attr_nominated_post_candidate_id="'+result[i].nominatedPostCandidateId+'" attr_membership_id="'+result[i].memberShipCardId+'" />';
						}else{
							str +='<input type="checkbox" attr_cadreId="'+result[i].tdpCadreId+'" class="cadreCls checkboxCls hideShowDivCls" name="checkbox" style="margin:auto;display:block;" id="appProfCheckBoxId" attr_nominated_post_candidate_id="'+result[i].tdpCadreId+'" attr_membership_id="'+result[i].memberShipCardId+'" />';
						}
						*/
				}
			   // str +='<input type="checkbox" style="margin:auto;display:block;" class="" />';
				str +='<p class="m_0 m_top5 text-center cadreName" value='+result[i].name+'><b>'+result[i].name+'</b></p>';
				str +='<p class="m_0 m_top5 text-center cadreVotrCardId" value="'+result[i].voterCardNo+'"><b>VOTERID : </b> '+result[i].voterCardNo+'</p>';
				if(result[i].memberShipId != null && result[i].memberShipId != "")
				str +='<p class="m_0 text-center cadreMembrShpNo" value="'+result[i].memberShipId+'"><b> MEMBERSHIP : </b> '+result[i].memberShipId+'</p>';
				str +='<p class="m_0 text-center cadreMobilNo" value="'+result[i].mobileNo+'"><b>MOBILE : </b> '+result[i].mobileNo+'</p>';
				str +='<input type="hidden" class="tdpCadreIdCls" value="'+result[i].id+'" attr_nominated_post_candidate_id="'+result[i].appointmentCandidateId+'"/>';
				
					//if(result[i].addressVO != null && result[i].addressVO.constituencyName != null && result[i].addressVO.constituencyName.length > 0)
					//{
						str +='<p class="text-center m_0"><b> ASSEMBLY : </b><span style="white-space: pre-wrap;"> '+result[i].constituency+'</span></p>';
						str +='</li>';
					/*}else if(result[i].constituency != null && result[i].constituency.length > 0){
						 str +='<p class="text-center m_0">'+result[i].constituency+'</p>';
						str +='</li>';
					}else{
						str +='<p class="text-center m_0">&nbsp;</p>';
						str +='</li>';
					}*/
				 
			}
			str +='</ul>';	
			$("#cadreSearchDtls").html(str);
			var length = $("#cadreSearchDtls").find("li").length;
			$("#membersCountId").html("<p id='memberCountSpanId'>Search Results <span class='font_weight'>"+length+"</span> Members</p>")
			if(result.length>3)
			{
			$(".best-matched-profile").slick({
				slide: 'li',
				slidesToShow: 4,
			   infinite: false
			   });
			   $(".slick-next").css("margin-right","10px;")
			   $(".slick-prev").css("margin-left","10px;")
			}
			$("#textId").show();
		}else{
				str+='No Data Available';
				$("#cadreSearchDtls").html(str);
				$("#textId").hide();
				//$("#cadreSearchDtls").html(No Data Available);			
		}
}
  
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
/*
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
     // $(".hideDivCls").hide();	
      isAlreadyChecked = false;	  
   }
   if($(this).is(':checked')){
	  globalCadreId = $(this).attr("attr_cadreId"); 
	  candidateId = $(this).attr("attr_nominated_post_candidate_id");
	  getCandidateAppliedPostsByCadre(globalCadreId,globalNPCandiId); 
	   //$(".hideDivCls").show();
        isAlreadyChecked = true;	   
   }
 });

*/
	$(document).on('blur','#voterId',function(){
		validateVoterIdCardNo();
	 });
	 function validateVoterIdCardNo(){		 
		 var voterIdCard = $(".voterCls").val(); 
		  //var mobileno = $("#mobilenoId").val();
		 if(voterIdCard ==null || voterIdCard.length==0 || voterIdCard == undefined){
			  $(".addNewCandidateErrorCls").html("Please Enter Voter Id.");
				return;
		 }	
		 var jsObj={
				voterIdCardNo :voterIdCard
		 }
		 $.ajax({
			  type:'GET',
			  url: 'validateVoterIdCardNoAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		   if(result == null){			
			  // $(".addNewCandidateErrorCls").html("Please Enter Valid VoterId.");
			    $("#voterExtraErrId").html("Please Enter Valid VoterId.");  
				//$("#addCandidateBtnId").hide();
			    $("#addCandidateBtnId").prop('disabled','disabled');
			    $(".addNewCandidateErrorCls").html(""); 
		   }else{
			   if(result.message == "applied")
			   {
				   $("#voterExtraErrId").html("This voter Id already Registered");  
				
			    $("#addCandidateBtnId").prop('disabled','disabled');
			    $(".addNewCandidateErrorCls").html(""); 
			   }else{
				  $("#voterExtraErrId").html(""); 
				  $("#addCandidateBtnId").removeAttr('disabled');
			   }
			   //$("#voterExtraErrId").html("");
			   //$("#addCandidateBtnId").show();
			  // $("#addCandidateBtnId").removeAttr('disabled');
		   }
	   });
	 }
function hideDetails(){
	$('.ramakrishnaCls').hide();
	$('#searchDivId').hide();
	$("#uploadFlDivId").hide();
	$("#submitBtnId").hide();
	$("#addedRefferalsDiv").hide();
}
$(document).keypress(function(e) {
				if(e.keyCode==13){
					if($("#myModal").is(':visible') == false){
					 var value = $("input[name='checkBoxName']:checked").val();
					$('.ramakrishnaCls').hide();
					$('#searchDivId').hide();
					$("#uploadFlDivId").hide();
					$("#submitBtnId").hide();
				  if(value == "Cadre"){
					  $("#membersCountId").hide();
						getNominatedPostApplication(0);
					}
					else if(value == "Not Cadre"){
						$("#membersCountId").hide();
						notCadresearch();
					}
				}else{
					getAdvancedSearchDetails();
				}
				}
		  });
		  
function refreshOnLoadFields(){
	$('#searchBy').val('');
	$("#membershipId").prop("checked", true);
	$("#cadreSearchId").prop("checked", true);
	
	$('.chosenSelect').val(0);
	$('#phoneNumId').val('');
	$('#houseNumberId').val('');
	$('#addressLane1Id').val('');
	$('#addressLane2Id').val('');
	$('#changestateId').val(0);
	$('#changedistrictId').val(0);
	$('#changeConstiId').val(0);
	$('#changeMandalId').val(0);
	$('#changePanchyatId').val(0);
	$('#addPincodeId').val('');
	
	$('#membersBlock').html(''); 
	$('.jFiler-row').html(''); 
	
	$('.boardLvlCls').val(0);
	$('.nominatedStaeCls').val(0);
	$('.nominatedDistCls').val(0);
	$('.nominatdConstCls').val(0);
	$('.nominatedMandlCls').val(0);
	$('.nominatedPanchayatCls').val(0);
	$('.depmtsCls').val(0);
	$('.deptBoardCls').val(0);
	$('.deptBoardPostnCls').empty();	
	
	$('.tdpCadreId').val('');
	$('.tdpCadreName').val('');
	$('.cadreVoterId').val('');
	$('.cadreMobileNo').val('');
	$('.referCadreIds').val('');
	$('.nominatedCandId').val('');
	$('#candidateTypeId').val('');
	$("#statesDivId").val(0).trigger("chosen:updated");  
	
}
$(document).on("click",".searchTypeCls",function(){
	$("#searchBy").removeAttr('maxLength');
	var value = $('input[name=searchBasedOn]:checked').val();
	if(value == 3){
		$("#searchBy").attr("maxLength","10");	
	}	
	if(value == 1){
		$("#searchBy").attr("maxLength","9");
	}		
});
$(document).on("click",".searchTypeCls1",function(){
	$("#searchById").removeAttr('maxLength');
	var value = $('input[name=radioGroup]:checked').val();
	if(value == 3)
		$("#searchById").attr("maxLength","10");
});

function getApplicationDocuments(cadreId,candiId,applicationId,statusId,applicationType){

	 var type = $("input[type='radio']:checked").val();
	 //var cadreId = candiId>0?0:globalCadreId;
		var jsObj={
				globalCadreId :cadreId,
				searchType:type,
				nominateCandId:candiId,//3423
				applicationId:applicationId,
				statusId:statusId,
				applicationType :applicationType
		}
		$.ajax({
			type:"POST",
			url :"getApplicationDocumentsAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
		   //if(result !=null && result.length > 0){
			  buildUploadedDocuments(result,statusId,applicationType);
		   //}   
   });	
  }
  
  function buildUploadedDocuments(result,statusId,applicationType){
  $("#applicationDocsModelId").modal("show");
  if(result !=null && result.length > 0){
  $('#uploadedDopcumentsDivId').html(' ');
  var buildStr='';
							buildStr+='<div class="panel-body">';
							buildStr +='<div class="row image-response" style="text-align:center;">';
							buildStr+='<ul class="list-inline slick-training " style=" margin-left: 20px;">';
							for(var i in result)
							{
								buildStr+='<li >';
								//buildStr+='<div class="" style="width: 800px;">';
								var indexOdfDot = result[i].imagePathStr.indexOf(".");
								var fileExt = result[i].imagePathStr.substring(indexOdfDot+1,result[i].imagePathStr.length);
								if((statusId == 9 || statusId == 7 || statusId == 0 ) && (applicationType == "goPassedStatus" || applicationType == "")){
									buildStr += '<iframe src="https://mytdp.com/GO_documents/'+result[i].imagePathStr+'"   height="800" width="1000px">'; 
								}else if((statusId == 1 || statusId == 2 || statusId == 3 || statusId == 4 || statusId == 0 || statusId == 5 || statusId == 6 || statusId == 8) && (applicationType == "applicationStatus" || applicationType == "")){
									buildStr += '<iframe src="https://mytdp.com/nominated_post_documents/'+result[i].imagePathStr+'"   height="800" width="1000px">'; 
								}
								buildStr += '</iframe>';
								
								//buildStr+='</div>';
								buildStr+='</li>';
							}
							buildStr+='</ul>';
							buildStr +='</div>';
							buildStr+='</div>';
						
							$('#uploadedDopcumentsDivId').html(buildStr);
					}else{
						$('#uploadedDopcumentsDivId').html(" NO UPLOADED DOCUMENTS ARE AVAILABLE");
					}
				$('.slick-training').slick({
					  slide: 'li',
					 slidesToShow: 1,
					 slidesToScroll: 1,
					 infinite: false,
					 swipeToSlide:false,
					 swipe:false,
					 touchMove:false,
					 responsive: [
						{
						  breakpoint: 1024,
						  settings: {
							slidesToShow: 3,
							slidesToScroll: 3
						  }
						},
						{
						  breakpoint: 768,
						  settings: {
							slidesToShow: 1,
							slidesToScroll: 1
						  }
						},
						{
						  breakpoint: 600,
						  settings: {
							slidesToShow: 1,
							slidesToScroll: 1
						  }
						},
						{
						  breakpoint: 480,
						  settings: {
							slidesToShow: 1,
							slidesToScroll: 1
						  }
						}
						// You can unslick at a given breakpoint now by adding:
						// settings: "unslick"
						// instead of a settings object
					  ]
				});
				
  }
 $(document).on('click', '#advanceSearchBtnId', function(){
	   $("#searchBy").val(""); 
       $("#searchErrDiv").html('');	   
	   hideDetails();   
	   $("#genderId").prop("checked",true);
		  var str = '';
		str+='<option value="0">Select Gender</option>';
		str+='<option value="M">Male</option>';
		str+='<option value="F">Female</option>';
		$("#advancSearchSelectId").html(str);
        $('#advancSearchSelectId').val(0).trigger("chosen:updated");   	  
  });
   $(document).on('click', '.resetCls', function(){
	 $("#searchData").html('');
	 $("#searchData1").html('');
	 $("#searchErrDiv").html("");
	 $('#statesDivId').val(0).trigger('chosen:updated');
	 $('#districtId').empty();
	 getConstituenciesForState(0,'constituencyId');
	 getDistricts();
	 $('#districtId').val(0).trigger('chosen:updated');
	 $('#constituencyId').val(0).trigger('chosen:updated');
	 $('#mandalList').empty();
	 $("#mandalList").append('<option value="0">Select Mandal/Municipality </option>');	
	 $('#mandalList').trigger('chosen:updated');
	 $('#panchaytList').empty();
	 $("#panchaytList").append('<option value="0">Select Panchayat</option>');	
	 $('#panchaytList').trigger('chosen:updated');
	 $('#notCadreErrMsg').html('');
	 $('#searchById').val('');
     $("#cadreSearchDtls").html('');	
	hideDetails();	

  if ($("#cadreSearchId").is(":checked")) {
		$("#searchMemberDiv").show();
		$("#cadreById").hide();
         $("#addMemberDivId").hide();
         $("#searchBy").val(' ');
         $( "#membershipId" ).prop( "checked", true);
		 if ($("#advanceSearchBtnId").is(":checked")){
		 $("#advanceSearchBtnId").trigger("click");
		 }
	}
	else {
	 $("#searchMemberDiv").hide();
	 $("#cadreById").show();
	 $("#scrollDivId").hide();
	 $("#textId").hide();
	 $("#addMemberDivId").show();
	  
	}  
   });
   
   function checkIsEligibleToaddOrNotAction(){
			var jsObj={
					deptId :departmentId,
					boardId:boardId
					
			}
			$.ajax({
				type:"POST",
				url :"checkIsEligibleToaddOrNotAction.action",
				dataType: 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
			   //if(result !=null && result.length > 0){
				  buildUploadedDocuments(result,statusId,applicationType);
			   //}   
	   });	
	  }