 function getConstituenciesForDistricts(district){
	 
	$("#constituencyId  option").remove();
	$("#constituencyId").append('<option value="0">Select Constituency</option>');
	$("#mandalList  option").remove();
	$("#mandalList").append('<option value="0">Select Mandal/Municipality</option>');
	$("#panchayatList  option").remove();
	$("#panchayatList").append('<option value="0">Select Panchayat</option>');
	$("#boothsList  option").remove();
	$("#boothsList").append('<option value="0">Select Booth</option>');
	
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
	  
	    for(var i in result){
	   if(result[i].id == 0){
          $("#constituencyId").append('<option value='+result[i].id+'>Select Constituency</option>');
	   }else{
	      $("#constituencyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }
		}
		$("#constituencyId").trigger("chosen:updated");
		
   });
  }


 function getMandalCorporationsByConstituency(consistency){
	 
	$("#mandalList  option").remove();
	$("#mandalList").append('<option value="0">Select Mandal/Municipality</option>');
	$("#panchayatList  option").remove();
	$("#panchayatList").append('<option value="0">Select Panchayat</option>');
	$("#boothsList  option").remove();
	$("#boothsList").append('<option value="0">Select Booth</option>');
	
	
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
	    for(var i in result){
		   if(result[i].id == 0){
			  $("#mandalList").append('<option value='+result[i].id+'>Select Mandal</option>');
		   }else{
			  $("#mandalList").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		   }
		}
		$("#mandalList").trigger("chosen:updated");
   });
  }
  
  function getPanchayatWardByMandal(mandal)
	{	
	$("#panchayatList  option").remove();
	$("#panchayatList").append('<option value="0">Select Panchayat</option>');
	$("#boothsList  option").remove();
	$("#boothsList").append('<option value="0">Select Booth</option>');

	   var mandalSubStrId=mandal.substring(0,1);
				var jsObj ={					
					mandalId:mandal
				};
				 $.ajax({
					type : "GET",
					url : "getPanchayatsForMandalAction.action",
					data : {task:JSON.stringify(jsObj)} 
				}).done(function(result){
			  for(var i in result){
			if(mandalSubStrId==1){
			if(result[i].id == 0){
                  $("#panchayatList").append('<option value='+result[i].id+'>Select Panchayat</option>');
						}else{
				$("#panchayatList").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
					}
					$("#panchayatList").trigger("chosen:updated");
					}else{
					$("#panchayatTwnId").hide();
					if(result[i].id == 0){
                  $("#boothsList").append('<option value='+result[i].id+'>Selct Booth</option>');
						}else{
				$("#boothsList").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
					}
					$("#boothsList").trigger("chosen:updated");	
					}
			  }					
				});
	}
	
	function getAllCadreInPanchayat(panchayat)
	{	
	
	$("#boothsList  option").remove();
	$("#boothsList").append('<option value="0">Select Booth</option>');
			
				var jsObj ={					
					panchayatId:panchayat
				};
				 $.ajax({
					type : "GET",
					url : "getBoothsForPanchayatAction.action",
					data : {task:JSON.stringify(jsObj)} 
				}).done(function(result){
					for(var i in result)
					{
					if(result[i].id == 0){
                  $("#boothsList").append('<option value='+result[i].id+'>Select Booth</option>');
	          }else{
	      $("#boothsList").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
					}
					}			
				});
	}

 function getDistrictsForStates(state){
	
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
     for(var i in result){
	   if(result[i].id == 0){
          $("#districtId").append('<option value='+result[i].id+'>Select District</option>');
	   }else{
	      $("#districtId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }
	 }
	 $("#districtId").trigger("chosen:updated");
   });
  }
  
  function getNewCadreDetails(){
	  var constituency=$("#constituencyId").val();
	  var mandal=$("#mandalList").val();
	  var village=$("#panchayatList").val();
	  var booth=$("#boothsList").val();
	  var name=$("#nameId").val();
	  var mobileNo=$("#mobileId").val();
	  var hNo=$("#huseNOId").val();
	  
	  var jsObj={
		  constituencyId:constituency,
		  mandalId:mandal,
		  villageId:village,
		  boothId:booth,
		  name:name,
		  mobileNo:mobileNo,
		  hNo:hNo
	  }
	   $.ajax({
          type:'GET',
          url: 'getVotersBySearchAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(result!=null && result.length>0){
	  searchCadreVoterDetails(result);
	   }
  });
  }
  
  function searchCadreVoterDetails(result){
	   var str = '';
  str += '<ul class="searchResults">';
  if (result!= null && result.length > 0) {
    for ( var i in result) {
      str += '<li>';
      str += '<div class="media">';
      str += '<div class="media-left">';
      str += '<img src="dist/img/default_image.png" class="media-object cadreImage" alt="candidateImage"/>';
      str += '</div>';
      str += '<div class="media-body">';
      str += '<h5 class="text-capitalize">'+result[i].name+ '</h5>';
      str += '<p>S/o:'+result[i].relativeName+'</p>';
      str += '<p>V.ID:'+result[i].voterIDCardNo+'&nbsp;&nbsp;';
	  if(result[i].memberShipNo!=null){
	   str += '<b>MemShip.ID:</b>'+result[i].memberShipNo+'</p>';	  
		  }
      str += '<p>H.no:'+result[i].houseNo+'&nbsp;&nbsp;|';
      str += '<span>&nbsp;&nbsp;Gender : '+result[i].gender+'&nbsp;&nbsp;|</span>';
      str += '<span>&nbsp;&nbsp;Age :'+result[i].age+'</span>';
      str += '</p>';
      str += '<div class="checkboxAlign">';
      str += '<input type="checkbox" id="checkbox'+i+'" class="checkbox-custom"/>';
      str += '<label for="checkbox'+i+'" class="checkbox-custom-label searchChkboxCls" attr_voterId="'+result[i].voterId+'" attr_tdpCadre_id="'+result[i].tdpCadreId+'" attr_enrol_yId="'+result[i].enrollmentYearId+'" style="font-size:13px;font-weight:200;text-transform:uppercase">&nbsp;</label>';
      str += '</div>';
	  str += '</div>';
      str += '</div>';
      str += '</li>';
    }
  }
  str += '</ul>';
  $("#searchVoterDetailsId").html(str);
  if(result.length > 6)
  {
	$(".searchResults").mCustomScrollbar({
		setHeight:'300px'
	}); 
  }
  
  }
  
  $(document).on("click",".searchChkboxCls",function(){
	  var voterId = $(this).attr("attr_voterId");
	  var tdpCadreId = $(this).attr("attr_tdpCadre_id");
	  var enrolYear = $(this).attr("attr_enrol_yId");
	  var status = "new";
	  
	 if(tdpCadreId != null && tdpCadreId > 0 && enrolYear == 3)
		status = "renewal";
	else if(tdpCadreId != null && tdpCadreId > 0 && enrolYear == 4)
		status = "update";
	
	
	 $("#voterId").val(voterId);
     $("#tdpCadreId").val(tdpCadreId);
	 $("#statusId").val(status);
	
  });
  
   function getSearchByRelativeVoterIdDetails(){
	 var voterId1=0;
	 var familyVoterId=$("#voterId").val();
	 var tdpCadreId=$("#tdpCadreId").val();
	 var status=$("#statusId").val();
	 
	 var jsObj={
		 voterId:voterId1,
		 familyVoterId:familyVoterId,
		 cadreId:tdpCadreId,
		 status:status
	 }
	 $.ajax({
		 type:'GET',
		 url: 'getRegistrationPersonDetailsAction.action',
         dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	 })
	
  }