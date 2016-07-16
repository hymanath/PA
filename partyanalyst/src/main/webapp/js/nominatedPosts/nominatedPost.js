function getDistrictsForStates(state,id,num){

	//$(".allcls").hide();
	if(id == "statesDivId"){
			getConstituenciesForState(state);
			$("#searchDataImgForDist").show();
			refreshExistingDetails();
			$("#districtId  option").remove();
			$("#districtId").append('<option value="0">Select District</option>');
			$("#constituencyId  option").remove();
			$("#constituencyId").append('<option value="0">Select Constituency</option>');
			$("#mandalList  option").remove();
			$("#mandalList").append('<option value="0">Select Mandal/Municipality</option>');
			$("#panchaytList  option").remove();
			$("#panchaytList").append('<option value="0">Select Panchayat</option>');
	}else {
		
		 state = $("#nominatedStaeId"+num).val();
			$("#nominatedDistId  option").remove();
			$("#nominatedDistId").append('<option value="0">Select District</option>');
			$("#nominatdConstId  option").remove();
			$("#nominatdConstId").append('<option value="0">Select Constituency</option>');
			$("#nominatedMandlId  option").remove();
			$("#nominatedMandlId").append('<option value="0">Select Mandal/Municipality</option>');
			$("#nominatedPanchayatId  option").remove();
			$("#nominatedPanchayatId").append('<option value="0">Select Panchayat</option>');
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
	   }else {
	   $("#nominatedDistId").empty();
	   }
	   
	   $("#searchDataImgForDist").hide();
     for(var i in result){
		 if(id == "statesDivId"){
			   if(result[i].id == 0){
				  $("#districtId").append('<option value='+result[i].id+'>ALL</option>');
			   }else{
				  $("#districtId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			   }
			   
		 }else {
				  $("#nominatedDistId"+num).append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		 }
	 }
	 if(id == "statesDivId"){
	 $("#districtId").trigger("chosen:updated");
	 }else{
	 $("#nominatedDistId"+num).trigger("chosen:updated");
	 }
   });
  }
 function getConstituenciesForDistricts(district,id,num){
	 if(id == "districtId"){
			$("#searchDataImgForConst").show();
			refreshExistingDetails();
			$("#constituencyId  option").remove();
			$("#constituencyId").append('<option value="0">Select Constituency</option>');
			$("#mandalList  option").remove();
			$("#mandalList").append('<option value="0">Select Mandal/Municipality</option>');
			$("#panchaytList  option").remove();
			$("#panchaytList").append('<option value="0">Select Panchayat</option>');
	 }else{
		 district = $("#nominatedDistId"+num).val();
			$("#nominatdConstId  option").remove();
			$("#nominatdConstId").append('<option value="0">Select Constituency</option>');
			$("#nominatedMandlId  option").remove();
			$("#nominatedMandlId").append('<option value="0">Select Mandal/Municipality</option>');
			$("#nominatedPanchayatId  option").remove();
			$("#nominatedPanchayatId").append('<option value="0">Select Panchayat</option>');
	 }
	
	if(district == 0){
		 alert(district)
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
	   if(id == "districtId"){
	   $("#constituencyId").empty();
	   }else {
	   $("#nominatdConstId").empty();
	   }
	   $("#searchDataImgForConst").hide();
     for(var i in result){
		 if(id == "districtId"){
			   if(result[i].id == 0){
				  $("#constituencyId").append('<option value='+result[i].id+'>ALL</option>');
			   }else{
				  $("#constituencyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			   }
		 }else {
			  $("#nominatdConstId"+num).append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		 }
	 }
	 if(id == "districtId"){
		$("#constituencyId").trigger("chosen:updated");
	 }else{
		$("#nominatdConstId"+num).trigger("chosen:updated"); 
	 }
   });
  }
 function getMandalCorporationsByConstituency(num,id)
	{	
	if(id == "constituencyId"){
			$("#searchDataImgForMandl").show();
			refreshExistingDetails();
			var constituencyId = $('#constituencyId').val();
			$("#mandalList  option").remove();
			$("#mandalList").append('<option value="0">Select Mandal/Municipality</option>');
			$("#panchaytList  option").remove();
			$("#panchaytList").append('<option value="0">Select Panchayat</option>');
			document.getElementById('membershipId').checked = true;
	}else {
		var constituencyId = $("#nominatdConstId"+num).val();
			$("#nominatedMandlId  option").remove();
			$("#nominatedMandlId").append('<option value="0">Select Mandal/Municipality</option>');
			$("#nominatedPanchayatId option").remove();
			$("#nominatedPanchayatId").append('<option value="0">Select Panchayat</option>');
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
					for(var i in result)
					{
						if(id == "constituencyId"){
							$("#mandalList").append('<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>');
						}else{
							$("#nominatedMandlId"+num).append('<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>');
						}
					}	
				}
				if(id == "constituencyId"){
					$("#mandalList").trigger("chosen:updated");
				}else{
					$("#nominatedMandlId"+num).trigger("chosen:updated");
				}				
				});
	}
function getPanchayatWardByMandal(num,id){
		if(id == "mandalList"){
				$("#searchDataImgForPanc").show();
				refreshExistingDetails();
				var mandalId=$("#mandalList").val();
				var constituencyId = $('#constituencyId').val();
				$("#panchaytList  option").remove();
				$("#panchaytList").append('<option value="0">Select Panchayat</option>');
		}else{
				var mandalId=$("#nominatedMandlId"+num).val();
				var constituencyId = $("#nominatdConstId"+num).val();
				$("#nominatedPanchayatId  option").remove();
				$("#nominatedPanchayatId").append('<option value="0">Select Panchayat</option>');
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
				
			for(var i in result){
				if(id == "mandalList"){
					$("#panchaytList").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
				}else{
					$("#nominatedPanchayatId"+num).append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
				}
			}
			if(id == "mandalList"){
				$("#panchaytList").trigger("chosen:updated");	
			}else{
				$("#nominatedPanchayatId"+num).trigger("chosen:updated");
			}
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
		if(result != null && result.length >0){
		for(var i in result)
			{
		str +='<li>';
        str +='<div class="img-center">';
        //str +='<img src="images/cadre_images/'+result[i].imageURL+'" class="img-responsive img-circle" alt="Profile"/>';
		str+='<img src="dist/img/profile.png" class="img-responsive img-circle" alt="Profile"/>';
        str +='</div>';
        str +='<input type="checkbox" style="margin:auto;display:block;" class="checkboxCls" name="checkbox"/>';
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
		$("#searchBy").val("");
		$("#cadreDetailsDiv").html("");
		$(".paginationDivId").html('');
		$("#cadreDetailsDiv").hide();
		$("#searchErrDiv").html("");
		$("#membrshipAndMobileNoDiv").html("");
		$("#cadreSearchSize").hide();
		$("#cadreSearchDtls").html("");
		$("#searchData").html("");
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
	function getBoardLevels(id){
	
	var jsObj = {}
    $.ajax({
          type:'GET',
          url: 'getBoardLevelsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
   $("#"+id).empty();
    $("#"+id).append('<option value="0">Select Board</option>');
   if(result != null && result.length >0){
		 
     for(var i in result){
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
		boardId : $("#boardLvlId"+num).val()
	}
    $.ajax({
          type:'GET',
          url: 'getDepartmentBoardPositionsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
    $("#deptBoardId"+num).append('<option value="0">Select Board Position</option>');
   if(result != null && result.length >0){
	  
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
		depmtId : $("#depmtsId"+num).val()
	}
    $.ajax({
          type:'GET',
          url: 'getDepartmentBoardsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
   //$("#searchDataImgForDist").hide();
   $("#deptBoardId"+num).append('<option value="0">Select Department Board</option>');
   if(result != null && result.length >0){
	    
		for(var i in result){
			$("#deptBoardId"+num).append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	  }
   }
	  $("#deptBoardId"+num).trigger("chosen:updated");
   });
  }
  function getDepartments(id){
	//$("#searchDataImgForDist").show();
    
	var jsObj = {}
    $.ajax({
          type:'GET',
          url: 'getDepartmentsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
   $("#"+id).empty();
   $("#"+id).append('<option value="0">Select Department</option>');  
   if(result != null && result.length >0){
		
     for(var i in result){
	   $("#"+id).append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	 }
   }
	  $("#"+id).trigger("chosen:updated");
   });
  }
getBoardLevels("boardLvlId"); 
getDepartments("depmtsId"); 
$(document).on("click",".checkboxCls",function(){
    $(".checkboxCls").prop( "checked" ,false);
	$( this ).prop( 'checked', true );
})
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
