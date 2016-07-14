function getDistrictsForStates(state){
   getConstituenciesForState(state);
    $("#searchDataImgForDist").show();
    refreshExistingDetails();
	$(".allcls").hide();
	$("#districtId  option").remove();
	$("#districtId").append('<option value="0">Select District</option>');
	$("#constituencyId  option").remove();
	$("#constituencyId").append('<option value="0">Select Constituency</option>');
	$("#mandalList  option").remove();
	$("#mandalList").append('<option value="0">Select Mandal/Municipality</option>');
	$("#panchaytList  option").remove();
	$("#panchaytList").append('<option value="0">Select Panchayat</option>');
	
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
	   $("#districtId").empty();
	   $("#searchDataImgForDist").hide();
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
 function getConstituenciesForDistricts(district){
   $("#searchDataImgForConst").show();
    refreshExistingDetails();
	$(".allcls").hide();
   $("#constituencyId  option").remove();
	$("#constituencyId").append('<option value="0">Select Constituency</option>');
	$("#mandalList  option").remove();
	$("#mandalList").append('<option value="0">Select Mandal/Municipality</option>');
	$("#panchaytList  option").remove();
	$("#panchaytList").append('<option value="0">Select Panchayat</option>');
	
	if(district == 0){
		getConstituenciesForState(0);
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
	   $("#constituencyId").empty();
	   $("#searchDataImgForConst").hide();
     for(var i in result){
	   if(result[i].id == 0){
          $("#constituencyId").append('<option value='+result[i].id+'>ALL</option>');
	   }else{
	      $("#constituencyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }
	 }
	 $("#constituencyId").trigger("chosen:updated");
   });
  }
 function getMandalCorporationsByConstituency()
	{	
			$("#searchDataImgForMandl").show();
			refreshExistingDetails();
			//document.getElementById('allId').checked = false;
			//$(".allcls").hide();
			var constituencyId = $('#constituencyId').val();
			$("#mandalList  option").remove();
			$("#mandalList").append('<option value="0">Select Mandal/Municipality</option>');
			$("#panchaytList  option").remove();
			$("#panchaytList").append('<option value="0">Select Panchayat</option>');
			document.getElementById('membershipId').checked = true;
			
				var jsObj ={					
					constituencyId:constituencyId
				};
				 $.ajax({
					type : "GET",
					url : "getMandalDetailsByConstituencyAction.action",
					data : {task:JSON.stringify(jsObj)} 
				}).done(function(result){
				$("#searchDataImgForMandl").hide();
				if(result !=null)
				{
					for(var i in result)
					{
						$("#mandalList").append('<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>');
					}	
				}
             $("#mandalList").trigger("chosen:updated");				
				});
	}
function getPanchayatWardByMandal(){
			$("#searchDataImgForPanc").show();
			refreshExistingDetails();
			//document.getElementById('allId').checked = false;
			//$(".allcls").hide();
		     
			var mandalId=$("#mandalList").val();
			var constituencyId = $('#constituencyId').val();
			
			$("#panchaytList  option").remove();
			$("#panchaytList").append('<option value="0">Select Panchayat</option>');
			
			var jsObj={
				mandalId:mandalId,
				constituencyId : constituencyId
			}
			$.ajax({
				type : "POST",
				url : "getPanchayatWardByMandalAction.action",
				data : {task:JSON.stringify(jsObj)} 
			}).done(function(result){
				$("#searchDataImgForPanc").hide();
			for(var i in result){
				$("#panchaytList").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
			}
			$("#panchaytList").trigger("chosen:updated");	
		});	
			
	}
	getConstituenciesForState(0);
	 function getConstituenciesForState(state){
  
 $("#searchDataImgForConst").show();
   var jsObj=
   {				
				stateId:0,
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
	   $("#constituencyId").empty();
	   
     for(var i in result){
	   if(result[i].id == 0){
         $("#constituencyId").append('<option value='+result[i].id+'>ALL</option>');
	   }else{
	     $("#constituencyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }  	
	 }
	  $("#constituencyId").trigger("chosen:updated");
   });
  }
function getAllCadreInPanchayat()
	{
		//document.getElementById('allId').checked = false;
		document.getElementById('membershipId').checked = true;
		//$(".allcls").show();
		refreshExistingDetails();
	} 
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
				
		
	$('#cadreDetailsDiv,#searchErrDiv,#committeeLocationIdErr,#committeLocationIdErr,#advancedSearchErrDiv').html('');
	if(startIndex == 0)
	{
		$(".paginationDivId").html('');
	}
		$(".paginationDivId").hide();	
		
		$('#searchLevelErrDiv,#committeePositionIdErr,#nonAfflitCommitteeIdErr').html('');
		$("#cadreDetailsDiv").hide();
		//$("#cadreSearchDtls").hide();
		var searchBy = $('#searchBy').val().trim();
		var searchRadioType =$('#cadreSearchType').val();;
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
		/*else if(constituencyId == 0)
		{
			if(accessType == "DISTRICT")
			{
				locationLevel = 3;
				locationValue = accessValue;
			}
			if(accessType == "MP")
			{
				locationLevel = 10;
				locationValue = accessValue;
			}
			if(accessType == "STATE")
			{
				var stateId = $("#statesDivId").val();
				locationLevel = 2;
				if($("#statesDivId").val() == 0)
				{
				stateId = 3;
				}
				locationValue = stateId;
			}	
		}*/
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
		/* else
		{
			if(accessType == "DISTRICT")
			{
				locationLevel = 3;
				locationValue = accessValue;
			}
			if(accessType == "MP")
			{
				locationLevel = 10;
				locationValue = accessValue;
			}
			if(accessType == "STATE")
			{
				var stateId = $("#statesDivId").val();
				locationLevel = 2;
				if($("#statesDivId").val() == 0)
				{
					stateId = 3;
				}
				locationValue = stateId;
			}	
		} */
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
		/* if(searchRadioType == 'membershipIdAndMobileNo')
		{
			membershipAndMobileNo = $('#searchBy').val().trim();
			
			var splitList = new Array();
			splitList = membershipAndMobileNo.split(",");
			memberShipCardNo = splitList[0];
			mobileNo = splitList[1];
			
			if(memberShipCardNo.trim().length == 0 )
			{
				$('#searchErrDiv').html('Please enter MemberShip Number..');
				return;
			}
			
			if(mobileNo.trim().length == 10){
					
					var numericExpression = /^[0-9]+$/;
					if(!mobileNo.match(numericExpression)){
						$('#searchErrDiv').html('Enter Numerics Only.');
						return;
					}
			}	
			else
			{
				$('#searchErrDiv').html('Please enter Valid Mobile No.');
				return;
			}
			
		} */
		
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
			task:"tdpCadreSearch"
		}
		$.ajax({
				type : "POST",
				url : "getCadreSearchDetailsAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
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
	function buildCadreDetails(result){
		var str='';
		var str1='';
		str1+='<h4 class="m_0 text-success">APPLICANT PROFILE DETAILS</h4>';
		str1+='<p>Search Results: <b><u id="cadreSearchSize">'+result.length+'</u></b> Members</p>';
		$("#searchData").html(str1);
		//var serchSize = result.length;
		//$("#cadreSearchSize").val(serchSize);
		if(result != null && result.length >0){
		for(var i in result)
			{
		str +='<li>';
        str +='<div class="img-center">';
        //str +='<img src="images/cadre_images/'+result[i].imageURL+'" class="img-responsive img-circle" alt="Profile"/>';
		str+='<img src="dist/img/profile.png" class="img-responsive img-circle" alt="Profile"/>';
        str +='</div>';
        str +='<input type="checkbox" style="margin:auto;display:block;"/>';
        str +='<p class="m_0 m_top5 text-center"><b>'+result[i].cadreName+'</b></p>';
        str +='<p class="m_0 m_top5 text-center">V.ID:'+result[i].voterCardNo+'</p>';
        str +='<p class="m_0 text-center">M.NO:'+result[i].memberShipCardId+'</p>';
        str +='<p class="m_0 text-center">'+result[i].mobileNo+'</p>';
		if(result[i].addressVO != null && result[i].addressVO.constituencyName != null && result[i].addressVO.constituencyName.length > 0)
				{
        str +='<p class="text-center m_0">'+result[i].addressVO.constituencyName+'</p>';
        str +='</li>';
				}
			}
		}else{
		str+='No Data Available';	
		}
		
		$("#cadreSearchDtls").html(str);
		
	}
	function refreshExistingDetails(){ 
		//$("#fromAgeId").val("");
		//$("#toAgeId").val("");
		$("#searchBy").val("");
		$("#cadreDetailsDiv").html("");
		$(".paginationDivId").html('');
		$("#cadreDetailsDiv").hide();
		$("#searchErrDiv").html("");
		$("#membrshipAndMobileNoDiv").html("");
		$("#cadreSearchSize").hide();
		$("#cadreSearchDtls").html("");
		$("#searchData").html("");
		//$("#casteCategory").val(0);
		//$("#casteList").val(0);
		//$("#ageRange").val(0);
		//$("#gender").val(0);
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
    $('#sub_menu').trigger("chosen:updated");
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
