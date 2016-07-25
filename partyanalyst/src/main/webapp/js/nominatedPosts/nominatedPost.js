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
			//$("#nominatedDistId  option").remove();
			$("#nominatedDistId"+num+"  option").remove();
			$("#nominatedDistId"+num).append('<option value="0">Select District</option>');
			$("#nominatdConstId"+num+"   option").remove();
			$("#nominatdConstId"+num).append('<option value="0">Select Constituency</option>');
			$("#nominatedMandlId"+num+"   option").remove();
			$("#nominatedMandlId"+num).append('<option value="0">Select Mandal/Municipality</option>');
			$("#nominatedPanchayatId"+num+"   option").remove();
			$("#nominatedPanchayatId"+num).append('<option value="0">Select Panchayat</option>');
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
			$("#nominatdConstId"+num+"  option").remove();
			$("#nominatdConstId"+num).append('<option value="0">Select Constituency</option>');
			$("#nominatedMandlId"+num+"  option").remove();
			$("#nominatedMandlId"+num).append('<option value="0">Select Mandal/Municipality</option>');
			$("#nominatedPanchayatId"+num+"  option").remove();
			$("#nominatedPanchayatId"+num).append('<option value="0">Select Panchayat</option>');
	 }
	
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
	   if(id == "districtId"){
	   $("#constituencyId").empty();
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
						$("#mandalList").append('<option value="0">Select Mandal</option>');
					}else {
						$("#nominatedMandlId"+num).empty();
					}
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
						$("#panchaytList").append('<option value="0">Select Panchayat</option>');
					}else {
						$("#nominatedPanchayatId"+num).empty();
					}
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
		//$("#cadreSearchDtls").show();
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
  
       $("#scrollDivId").show();
	   $("#textId").show();
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
       str +='<input type="checkbox" attr_cadreId="'+result[i].tdpCadreId+'" class="cadreCls checkboxCls" name="checkbox" style="margin:auto;display:block;" id="appProfCheckBoxId" attr_membership_id='+result[i].memberShipCardId+'/>';
       // str +='<input type="checkbox" style="margin:auto;display:block;" class="" />';
        str +='<p class="m_0 m_top5 text-center cadreName" value='+result[i].cadreName+'><b>'+result[i].cadreName+'</b></p>';
        str +='<p class="m_0 m_top5 text-center cadreVotrCardId" value="'+result[i].voterCardNo+'">V.ID:'+result[i].voterCardNo+'</p>';
		if(result[i].memberShipCardId != null && result[i].memberShipCardId != "")
        str +='<p class="m_0 text-center cadreMembrShpNo" value="'+result[i].memberShipCardId+'">M.NO:'+result[i].memberShipCardId+'</p>';
        str +='<p class="m_0 text-center cadreMobilNo" value="'+result[i].mobileNo+'">'+result[i].mobileNo+'</p>';
		str +='<input type="hidden" class="tdpCadreIdCls" value="'+result[i].tdpCadreId+'"/>';
		if(result[i].addressVO != null && result[i].addressVO.constituencyName != null && result[i].addressVO.constituencyName.length > 0)
				{
        str +='<p class="text-center m_0">'+result[i].addressVO.constituencyName+'</p>';
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
		
	}
	function refreshExistingDetails(){ 
		//$("#uploadFlDivId").hide();
		$("#searchBy").val("");
		$("#cadreDetailsDiv").html("");
		$(".paginationDivId").html('');
		$("#cadreDetailsDiv").hide();
		$("#searchErrDiv").html("");
		$("#membrshipAndMobileNoDiv").html("");
		$("#cadreSearchSize").hide();
		$("#cadreSearchDtls").html("");
		$("#searchData").html("");
		$("#notCadreNameId").val("");
		$("#notCadreVoterId").val("");
		$("#notCadreMobilNoId").val("");
		$("#notCadreErrMsg").html("");  
		$("#searchById").val("");
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
        $("#searchData").html("");
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
		 $("#"+id).append('<option value="0">Select Board</option>');
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
		boardId : $("#deptBoardId"+num).val()
	}
    $.ajax({
          type:'GET',
          url: 'getDepartmentBoardPositionsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
    $("#deptBoardPostnId"+num).empty();
   if(result != null && result.length >0){
	  $("#deptBoardPostnId"+num).append('<option value="0">Select Board Position</option>');
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
  $("#deptBoardId"+num).empty();
   if(result != null && result.length >0){
	     $("#deptBoardId"+num).append('<option value="0">Select Department Board</option>');
		for(var i in result){
			$("#deptBoardId"+num).append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	  }
   }
	  $("#deptBoardId"+num).trigger("chosen:updated");
   });
  }
  function getDepartments(num,postType){
	//$("#searchDataImgForDist").show();
   
	var jsObj = {postType:postType}
    $.ajax({
          type:'GET',
          url: 'getDepartmentsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   $("#deptBoardId"+num).empty();
	   $("#deptBoardPostnId"+num).empty();
   $("#depmtsId"+num).empty();
    
   if(result != null && result.length >0){
		$("#depmtsId"+num).append('<option value="0">Select Department</option>'); 
     for(var i in result){
	   $("#depmtsId"+num).append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	 }
   }
	  $("#depmtsId"+num).trigger("chosen:updated");
   });
  }
getBoardLevels("boardLvlId"); 
getDepartments("",1); 
$(document).on("click",".checkboxCls",function(){
	
    $(".checkboxCls").prop( "checked" ,false);
	//$("#uploadFlDivId").hide();
	$( this ).prop( 'checked', true );
	//$("#uploadFlDivId").show();
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
$(document).on("click",".iconClose",function(){
	$(this).closest(".addBlockNew").remove()
});
var cloneCount=1;

$(document).on("click","#addOneMore",function(){
	$(".errorMsgCls").html("");
  var e = $("#cloneDivBlock").clone();
  e.removeClass("cloneBlockDiv")
  e.attr("id",'block'+cloneCount);
  e.attr("attr_count",cloneCount);
  e.show();
  e.find(".nominatdPostSelCls").attr("id","nomintdPostId"+cloneCount);
  e.find(".nominatdPostSelCls").attr("onClick",'getDepartments('+cloneCount+',1);');
  e.find(".partyPostSelCls").attr("id","partyPostId"+cloneCount);
  e.find(".partyPostSelCls").attr("onClick",'getDepartments('+cloneCount+',2);');
  e.find(".iconClose").show();
  
 e.find(".boardLvlCls").attr("name",'nominatedPostVO.nominatdList['+cloneCount+'].boardLevelId');
  e.find(".boardLvlCls").attr("id","boardLvlId"+cloneCount);
  e.find(".boardLvlCls").attr("attr_no",cloneCount);
  getBoardLevels("boardLvlId"+cloneCount);
  e.find(".boardLvlCls").attr("onChange",'showHideByNominatedPost('+cloneCount+');');
  
  e.find(".nominatedStaeCls").attr("name",'nominatedPostVO.nominatdList['+cloneCount+'].stateId');
  e.find(".nominatedStaeCls").attr("id","nominatedStaeId"+cloneCount);
  e.find(".nominatedStaeCls").attr("attr_no",cloneCount);
  e.find(".stateShowCls").attr("id","statesShowDivId"+cloneCount);
  e.find(".nominatedStaeCls").attr("onChange",'getDistrictsForStates("",nominatedStaeId'+cloneCount+','+cloneCount+');');
  
  e.find(".nominatedDistCls").attr("name",'nominatedPostVO.nominatdList['+cloneCount+'].districtId');
  e.find(".nominatedDistCls").attr("id","nominatedDistId"+cloneCount);
  e.find(".nominatedDistCls").attr("attr_no",cloneCount);
  e.find(".districtShowCls").attr("id","districtShowDivId"+cloneCount);
  e.find(".nominatedDistCls").attr("onChange",'getConstituenciesForDistricts("",nominatedDistId'+cloneCount+','+cloneCount+');');
  
  e.find(".nominatdConstCls").attr("name",'nominatedPostVO.nominatdList['+cloneCount+'].ConstituencyId');
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
  getDepartments(cloneCount,1);
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
	
	 $(".cadreCheckCls").each(function(){
				if($(this).prop('checked')==true && $(this).val() == "Cadre"){
					if(!searchByApplicant()){
						return flag = false;
					}
				}else if($(this).prop('checked')==true && $(this).val() == "Not Cadre"){
					var numericExpression = /^[0-9]+$/;
					var mobileNo = $('#notCadreMobilNoId').val();
					if($("#notCadreNameId").val() == ""){
						$("#notCadreErrMsg").html("Please Fill Name");
						flag = false;
					}
					else if($("#notCadreVoterId").val() == ""){
						$("#notCadreErrMsg").html("Please Fill VoterCardNo ");
						flag = false;
					}
					else if(mobileNo.trim().length == 0 )
					{
						$('#notCadreErrMsg').html('Please enter Mobile No.');
						flag = false;
					}
					else if(!$('#notCadreMobilNoId').val().match(numericExpression)){
						$('#notCadreErrMsg').html('Enter Numerics Only.');
						flag = false;
					}
					 else if(mobileNo.trim().length != 10)
					{
						$('#notCadreErrMsg').html('Invalid Mobile No.');
						flag = false;				
					}else{
						$('#notCadreErrMsg').html("");
						flag = true;
					}
				}
			});
			if(flag){
					if(!validatationFields()){
						return flag = false;
					}
			}
				
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
						}
					});	
				}else if($(this).is(":checked")==true && $(this).val() == "Not Cadre")
				{
					cadreName = $("#notCadreNameId").val();
					cadreVoterId = $("#notCadreVoterId").val();
					cadreMobilNo =  $("#notCadreMobilNoId").val();
				}
			
		});  
		var refferCadreId="";
			for(var i=0;i<commontdpCadreIds.length;i++)
				 refferCadreId += commontdpCadreIds[i]+",";
			 
			 var n=refferCadreId.lastIndexOf(",");
			refferCadreId=refferCadreId.substring(0,n) ;
			
			$(".tdpCadreId").val(cadreId);
			$(".tdpCadreName").val(cadreName);
			$(".cadreVoterId").val(cadreVoterId);
			$(".cadreMobileNo").val(cadreMobilNo);
			$(".referCadreIds").val(refferCadreId); 
			
			var uploadHandler = {
				upload: function(o) {
					$("#savingAjaxImg").css("display","none");
					uploadResult = o.responseText;
					showSbmitStatus(uploadResult);
				}
			};
			
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
			
			$("#savingStatusDivId").html("<span style='color: green;font-size:22px;'>Application received  successfully , To complete the registration ,Upload the profiles below</span>");
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
					$(this).parent().find(".chosen-single").css("border","1px solid gray");
					flag = true;
			}
			
				if($(this).val() == 2 || $(this).val() == 3 || $(this).val() == 4 || $(this).val() == 5 || $(this).val() == 6 || $(this).val() == 7){
							
							if($("#nominatedStaeId"+clonNo).val() == 0 && typeof $("#nominatedStaeId"+clonNo).val() !== "undefined"){
								$("#nominatedStaeId"+clonNo).parent().find(".chosen-single").css("border","1px solid red");
								errorMsg = "Please Select State";
								flag = false;
							}else{
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
								$("#nominatedMandlId"+clonNo).parent().find(".chosen-single").css("border","1px solid gray");
								flag = true;
								} 
				}
				if($(this).val() == 7){
						
							if($("#nominatedPanchayatId"+clonNo).val() == 0 && typeof $("#nominatedPanchayatId"+clonNo).val() !== "undefined"){
								$("#nominatedPanchayatId"+clonNo).parent().find(".chosen-single").css("border","1px solid red");
								errorMsg = "Please Select Mandal/Corporation";
								flag = false;
							}else{
								$("#nominatedPanchayatId"+clonNo).parent().find(".chosen-single").css("border","1px solid gray");
								flag = true;
								} 
				}
				if($("#depmtsId"+clonNo).val() == 0){
						$("#depmtsId"+clonNo).parent().find(".chosen-single").css("border","1px solid red");
								errorMsg = "Please select hilighted";
								flag = false;
				}else{
						$("#depmtsId"+clonNo).parent().find(".chosen-single").css("border","1px solid gray");
							flag = true;
				} 
				if($("#deptBoardId"+clonNo).val() == 0){
						$("#deptBoardId"+clonNo).parent().find(".chosen-single").css("border","1px solid red");
								errorMsg = "Please select hilighted";
								flag = false;
				}else{
						$("#deptBoardId"+clonNo).parent().find(".chosen-single").css("border","1px solid gray");
								flag = true;
				} 
				if($("#deptBoardPostnId"+clonNo).val() == 0){
						$("#deptBoardPostnId"+clonNo).parent().find(".chosen-single").css("border","1px solid red");
								errorMsg = "Please select hilighted";
								flag = false;
				}else{
						$("#deptBoardPostnId"+clonNo).parent().find(".chosen-single").css("border","1px solid gray");
								flag = true;
				} 
				if(errorMsg != ''){
		$(this).parent().find(".errorMsgCls").html(errorMsg);
			flag = false;
		}
				
	});
				
		
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
	  getCandidateAppliedPostsByCadre(globalCadreId);
});
function getPopulateApplicantDetailsForMember(globalCadreId){ 
var jObj={
		globalCadreId:globalCadreId
	};
	$.ajax({
	  type:'POST',
	  url: 'getPopulateApplicantDetailsForMemberAction.action',
	  dataType: 'json',
	  data: {task:JSON.stringify(jObj)},
	  }).done(function(result){
		  if(result != null){
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
		$("#addStateId").val(''+result[0].stateId+'');
		$("#addStateId").trigger("chosen:updated");
	}
	else {
		$("#addStateId").val(0);
	}
	
	$("#addDistrictId").html("");
	$("#addDistrictId").append("<option value='0'>Select District</option>");
	if(result[0].distList != null && result[0].distList.length > 0){
		for(var i in result[0].distList){
			if(result[0].districtId != null && result[0].distList[i].id == result[0].districtId){
				$("#addDistrictId").append("<option selected value='"+result[0].distList[i].id+"'>"+result[0].distList[i].name+"</option>");
			}else{
				$("#addDistrictId").append("<option value='"+result[0].distList[i].id+"'>"+result[0].distList[i].name+"</option>");
			}
		}
		$("#addDistrictId").trigger("chosen:updated");
	}
	
	
	$("#addConstituencyId").html("");
	$("#addConstituencyId").append("<option value='0'>Select Constituency</option>");
	if(result[0].consList != null && result[0].consList.length > 0){
		for(var i in result[0].consList){
			if(result[0].constituencyId != null && result[0].consList[i].id == result[0].constituencyId){
				$("#addConstituencyId").append("<option selected value='"+result[0].consList[i].id+"'>"+result[0].consList[i].name+"</option>");
			}else{
				$("#addConstituencyId").append("<option value='"+result[0].consList[i].id+"'>"+result[0].consList[i].name+"</option>");
			}
		}
		$("#addConstituencyId").trigger("chosen:updated");
	}
	
	$("#addMandalsId").html("");
	$("#addMandalsId").append("<option value='0'>Select Mandal</option>");
	if(result[0].consList != null && result[0].consList.length > 0){
		for(var i in result[0].consList){
			if(result[0].constituencyId != null && result[0].consList[i].id == result[0].constituencyId){
				$("#addMandalsId").append("<option selected value='"+result[0].consList[i].id+"'>"+result[0].consList[i].name+"</option>");
			}else{
				$("#addMandalsId").append("<option value='"+result[0].consList[i].id+"'>"+result[0].consList[i].name+"</option>");
			}
		}
		$("#addMandalsId").trigger("chosen:updated");
	}
	
	
	$("#addVillageId").html("");
	$("#addVillageId").append("<option value='0'>Select Panchayat</option>");
	if(result[0].panList != null && result[0].panList.length > 0){
		for(var i in result[0].panList){
			if(result[0].panchayatId != null && result[0].panList[i].id == result[0].panchayatId){
				$("#addVillageId").append("<option selected value='"+result[0].panList[i].id+"'>"+result[0].panList[i].name+"</option>");
			}else{
				$("#addVillageId").append("<option value='"+result[0].panList[i].id+"'>"+result[0].panList[i].name+"</option>");
			}
		}
		$("#addVillageId").trigger("chosen:updated");
	}
}
 function getDistrictsForReferPopup()
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
 }
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
	}
function getMandalsByConstituencyForReferPopup()
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
  }

 function getCandidateAppliedPostsByCadre(globalCadreId){

		var jsObj={
				globalCadreId :globalCadreId
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
                                                	str+=result.subList[i].level+" → "+result.subList[i].subCaste+" → "+result.subList[i].cadreName+" "+result.subList[i].voterName+":"+result.subList[i].status+"</li>";
											}
                                           str+='</ul>';
                                        str+='</div>';
                                    str+='</div>';
                                str+='</div>';
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
                                                	str+=result.subList[i].level+" → "+result.subList[i].subCaste+" → "+result.subList[i].cadreName+" "+result.subList[i].voterName+":"+result.subList[i].status+"</li>";
											}
                                          str+='</ul>';
                                       str+=' </div>';
                                    str+='</div>';
                               str+=' </div>';
							}
                           str+='</div>';
                       str+='</div>';
                   str+='</div>';
	 }
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
     $("#cadreSearchDtls").html('');	 
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
function notCadresearch(){
	 var searchType=$("input[name='radioGroup']:checked").val();
	   var searchValue=$("#searchById").val();
	  // alert($('#searchByca').val());
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
					
					if(result != null)
					{
					buildCadreDetails(result);	
					}
				  
				});
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
