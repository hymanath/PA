 function getConstituenciesForDistricts(district){
	 $("#districtDivIdImg").show();
	 
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
	  $("#districtDivIdImg").hide();
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
	 $("#constituencyDivIdImg").show();
	 
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
	   $("#constituencyDivIdImg").hide();
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
    $("#mandalDivIdImg").show();		
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
				  $("#mandalDivIdImg").hide();
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
	$("#panchayatDivIdImg").show();
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
					$("#panchayatDivIdImg").hide();
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

 function getDistrictsForStates(state){
	$("#statesDivIdImg").show();
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
	   $("#statesDivIdImg").hide();
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
      var state=$("#statesDivId").val();
	  var district=$("#districtId").val();
	  var constituency=$("#constituencyId").val();          
	  var names=$("#nameId").val(); 
	  var mobileNo=$("#mobileId").val();
	  var houseNo=$("#huseNOId").val();
     if(state == 0)
	  {
		 $("#errorDivId").html("please select state");
		 return;
	   }
	  if(district == 0)
	  {
		 $("#errorDivId").html("please select district");
		 return;
	   }
	  if(constituency == 0)
	  {
		 $("#errorDivId").html("please select constituency");
		 return;
	   }
	  if(names.trim().length == 0 && mobileNo.trim().length == 0 && houseNo.trim().length == 0 )
	   {
		  $("#errorDivId").html("please select name or mobileNo or houseNo");
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
      str += '<input type="checkbox" id="checkbox'+i+'" class="checkbox-custom searchChkboxCls"/>';
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
	   var flag = 0;
   $(".searchChkboxCls").each(function(){
	  if($(this).is(":checked")){  
		  flag=1;
	  }
   });
   if(flag ==  0)
	  {
		  $("#checkVoterId").html("please check the voterDetails");
		  return;
	  }
	  myVoterButtonDetails();
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
 
  function validateRenewalMemshipDetails(){
	  var membershipId=$("#validateRenMemshipId").val();
	  var name=0;
	  var mobileNo=0;
	  var voterId=0;
	  
	  var jsObj={
		  memberShipNo:membershipId,
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
	   
  }
  
  function searchRenewalMemshipDetails(){
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
  }
  
  
 function renewalSearchMembershipDetails(result){
	   var str = '';
  str += '<ul class="renewalSearchResults">';
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
      str += '<p>V.ID:'+result[i].voterId+'&nbsp;&nbsp;';
	  if(result[i].memberShipNo!=null){
	   str += '<b>MemShip.ID:</b>'+result[i].memberShipNo+'</p>';	  
		  }
      str += '<p>H.no:'+result[i].houseNo+'&nbsp;&nbsp;|';
      str += '<span>&nbsp;&nbsp;Gender : '+result[i].gender+'&nbsp;&nbsp;|</span>';
      str += '<span>&nbsp;&nbsp;Age :'+result[i].age+'</span>';
      str += '</p>';
      str += '<div class="checkboxAlign">';
      str += '<input type="checkbox" id="checkbox'+i+'" class="checkbox-custom"/>';
      str += '<label for="checkbox'+i+'" class="checkbox-custom-label searchChkboxCls" attr_voterId="'+result[i].voterIDCardNo+'" attr_tdpCadre_id="'+result[i].tdpCadreId+'" attr_enrol_yId="'+result[i].enrollmentYearId+'" style="font-size:13px;font-weight:200;text-transform:uppercase">&nbsp;</label>';
      str += '</div>';
	  str += '</div>';
      str += '</div>';
      str += '</li>';
    }
  }
  str += '</ul>';
  $("#renewalMembershipId").html(str);
  if(result.length > 6)
  {
	$(".searchResults").mCustomScrollbar({
		setHeight:'300px'
	}); 
  }
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
          $("#renewalconstitId").append('<option value='+result[i].id+'>Select District</option>');
	   }else{
	      $("#renewalconstitId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }
	 }
	  $("#renewalconstitId").trigger("chosen:updated");
	
  })
  }