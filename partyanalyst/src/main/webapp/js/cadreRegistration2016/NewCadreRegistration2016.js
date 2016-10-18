 function getConstituenciesForDistricts(district){
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
	 $("#constituencyDivIdImg").hide();
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
	   $("#mandalDivIdImg").hide();
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

	   var mandalSubStrId=mandal.substring(0,1);
	   if(mandalSubStrId==1){
		   $("#panchayatTwnId").show();
		   $("#pancyatDivIdImg").show();
	   }
	   else{
		   $("#panchayatTwnId").hide();
		    $("#boothDivIdImg").show();	
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
				  for(var i in result){
					 
						if(mandalSubStrId==1){
							$("#pancyatDivIdImg").hide();
						if(result[i].id == 0){
							  $("#panchayatList").append('<option value='+result[i].id+'>Select Panchayat</option>');
							}else{
							$("#panchayatList").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
							}
							$("#panchayatList").trigger("chosen:updated");
							}else{
								$("#boothDivIdImg").hide();
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

 function getDistrictsForStates(state){
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
  
$(document).on("change","#boothsList",function(){
	$("#searchVoterId").val('');
	$("#nameId").val('');
	$("#mobileId").val('');
	$("#huseNOId").val('');
});
  
  function getNewCadreDetails(){
	  $("#newRenwalBtnId").hide();
      $("#newUpdateBtnId").hide();
	  $("#errorDivId").html("");
	  $("#searchVoterDetailsId").html("");
	  $("#searchVoterDetailsImgId").show();
	  $("#voterBtnsDivId").hide();
	  var constituency=$("#constituencyId").val();
	  var mandal=$("#mandalList").val();
	  var village=$("#panchayatList").val();
	  var booth=$("#boothsList").val();
	  var name=$("#nameId").val();
	  //var mobileNo=$("#mobileId").val();
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
	 
	  if(voterId.trim().length == 0 && name.trim().length == 0 && hNo.trim().length == 0 )
	   {   
		  $("#errorDivId").html("Enter Atleast One VoterId or Name or HouseNo");
		 return; 
	   }
	   searchVoterDetails();
	  var jsObj={
		  constituencyId:constituency,
		  mandalId:mandal,
		  villageId:village,
		  boothId:booth,
		  name:name,
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
  }
		
  function searchCadreVoterDetails(result){
	   var str = '';
  str += '<ul class="searchResults">';
  if (result!= null && result.length > 0) {
    for ( var i in result) {
      str += '<li>';
		  str += '<div class="media">';
			  str += '<div class="media-left">';
				str += '<img src="https://mytdp.com/'+result[i].imagePath+'" class="media-object cadreImage" alt=""/>';
			  str += '</div>';
			  str += '<div class="media-body">';
				  str += '<h5 class="text-capitalize">'+result[i].name+ '</h5>';
				if(result[i].relationshipType == 'Mother'){
					if(result[i].gender == 'F')
						str += '<p>D/O: '+result[i].relativeName+'</p>';
					else
						str += '<p>S/O: '+result[i].relativeName+'</p>';
				}
				else if(result[i].relationshipType == 'Husband'){
					str += '<p>W/O: '+result[i].relativeName+'</p>';
				}
				else{
					if(result[i].gender == 'F')
						str += '<p>D/O: '+result[i].relativeName+'</p>';
					else
						str += '<p>S/O: '+result[i].relativeName+'</p>';
				}
					
				  str += '<p>V.ID: '+result[i].voterIDCardNo+'&nbsp;&nbsp;';
				  if(result[i].memberShipNo!=null){
				   str += '<b>M.ID:</b> '+result[i].memberShipNo+'</p>';	  
					  }
				  str += '<p>H.NO: '+result[i].houseNo+'&nbsp;&nbsp;|';
				  str += '<span>&nbsp;&nbsp;GENDER: '+result[i].gender+'&nbsp;&nbsp;|</span>';
				  str += '<span>&nbsp;&nbsp;AGE: '+result[i].age+'</span>';
				  str += '</p>';
				  str += '<div class="checkboxAlign">';
				  str += '<input type="radio" id="checkbox'+i+'" name="searchNewSelect" class="checkbox-custom searchChkboxCls" attr_voterId="'+result[i].voterId+'" attr_tdpCadre_id="'+result[i].tdpCadreId+'" attr_fam_voter_id="'+result[i].voterIDCardNo+'" attr_enrol_yId="'+result[i].enrollmentYearId+'"/>';
				  str += '<label for="checkbox'+i+'" class="checkbox-custom-label" style="font-size:13px;font-weight:200;text-transform:uppercase">&nbsp;</label>';
			  str += '</div>';
			  str += '</div>';
		  str += '</div>';
      str += '</li>';
    }
  }
  str += '</ul>';
  $("#searchVoterDetailsId").html(str);
   $("#voterBtnsDivId").show();
  if(result.length > 6)
  {
	$(".searchResults").mCustomScrollbar({
		setHeight:'300px'
	}); 
  }
  
  }
 
  $(document).on("click",".searchChkboxCls",function(){
	  $("#searchResultsBackBtn").parent().removeClass("hide");
	  $("#searchResultsBackBtnR").parent().addClass("hide");
	  var voterId = $(this).attr("attr_voterId");
	  var tdpCadreId = $(this).attr("attr_tdpCadre_id");
	  var enrolYear = $(this).attr("attr_enrol_yId");
	  var familyVoterCardNumber=$(this).attr("attr_fam_voter_id");
	  var status = "new";
	  
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
	
	 $("#voterId").val(voterId);
     $("#tdpCadreId").val(tdpCadreId);
	 $("#statusId").val(status);
	$("#hidnFamlyVoterId").val(familyVoterCardNumber);
  });
  
   function getSearchByRelativeVoterIdDetails(){
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
		  $("#checkVoterId").html("please check the voterDetails");
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
	 $.ajax({
		 type:'GET',
		 url: 'getRegistrationPersonDetailsAction.action',
         dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   $("#submitCadreForm").show();
	   $(".newProfile").show();
	   $("#populatingDtsDivImgId").hide();
   hideShowDivs(status);
   buildRelatVoterDetails(familyVoterCardNo);
   buildCasteDetails(result);
   buildEductnQualifns(result);
   buildCadreFamilyDetails(result);
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
	  if(mobileNo.trim().length>0 && mobileNo.trim().length < 10 )
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
				  str += '<span>&nbsp;&nbsp;AGE: <span  id="profileAge'+i+'">'+result[i].age+'</span></span>';
				  str += '</p>';
				  str += '<div class="checkboxAlign">';
					str += '<input type="radio" id="checkbox'+i+'" class="checkbox-custom"/>';
					str += '<label for="checkbox'+i+'" class="checkbox-custom-label searchChkboxClsR" name="renewalRadioBtn" attr_voterId="'+result[i].voterId+'" attr_tdpCadre_id="'+result[i].id+'" attr_enrol_yId="'+result[i].enrollmentYearId+'" attr_relative_voter="'+result[i].familyVoterId+'" attr_number="'+i+'" attr_img1="'+result[i].imageURL+'" attr_relativeType="'+result[i].relativeType+'" style="font-size:13px;font-weight:200;text-transform:uppercase">&nbsp;</label>';
				  str += '</div>';
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
			$(".profileDetailsBlock,.subBlock,.renewalN").removeClass("hide");
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
	$.ajax({          
		type : 'GET',    
		url : 'getRegistrationPersonDetailsAction.action',  
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)} 
	}).done(function(result){
		$("#submitCadreForm").show();
		$("#populatingDtsDivImgId").hide();
		hideShowDivs(status);
		buildProfileDetails(result,status);
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

function getCadreDetailsForRelativeCadre(type){
	eachTimeClearFields();
	$("#populatingDtsDivImgId").show();
	$("#submitCadreForm").hide();
	var status = $("#stusIdR").val();
	var jsObj={
		 voterId:0,
		 familyVoterId:$("#votrIdR").val(),
		 cadreId:$("#tdpCdrIdR").val(),
		 status:$("#stusIdR").val()
	 }
	$.ajax({          
		type : 'GET',    
		url : 'getRegistrationPersonDetailsAction.action',  
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)} 
	}).done(function(result){
		$("#populatingDtsDivImgId").hide();
		$("#submitCadreForm").show();
		hideShowDivs(status);
		buildProfileDetails(result,status);
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
          $("#renewalconstitId").append('<option value='+result[i].id+'>Select District</option>');
	   }else{
	      $("#renewalconstitId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }
	 }
	  $("#renewalconstitId").trigger("chosen:updated");
	
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
			   str +='<img src="https://mytdp.com/'+image+'" class="media-object cadreImage" alt=""/>';
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
  
$(document).on("click",".searchChkboxCls",function(){
	$("#submitCadreForm").hide();
});

$(document).on("click",".registerNew",function(){
	$(".renewalN").hide();
});

function buildRelatVoterDetails(familyVoterCardNo){
	 if(familyVoterCardNo!=null && familyVoterCardNo!=""){
		 $("#voterIdText").val(familyVoterCardNo);
	 }
}
$(document).on("click",".renewalBtn",function(){
	$("#renewalMembershipId").html('');
	$("#validateRenMemshipId").val('');
	$("#renewalMobileId").val('');
	$("#renewalMobileId").val('');
	});