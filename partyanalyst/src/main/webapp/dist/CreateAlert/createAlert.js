disableByLevel('');

function disableByLevel(index)
  {
	  setDefault(index);	
	  var levelId = $("#alertlevelId"+index).val();
	  var districtId = $("#referdistrictId"+index).val();
	  var constituencyId = $("#referconstituencyId"+index).val();
	  var panchayatId = $("#referpanchayatId"+index).val();
	  var mandalId = $("#refermandalNameId"+index).val();
	  var select = new Dropkick("#referdistrictId"+index);
		select.refresh();
		
		if(levelId != 2 && levelId != 0)
		{
			getDistrictsForReferPopup(index);
		}
		
		if(levelId == 2 || levelId == 0)
		{
			  $("#referdistrictId"+index).find('option').not(':first').remove();
			  $("#referconstituencyId"+index).find('option').not(':first').remove();
			  $("#refermandalNameId"+index).find('option').not(':first').remove();
			  $("#referpanchayatId"+index).find('option').not(':first').remove();
			   var select = new Dropkick("#referdistrictId"+index);
				select.refresh();
			   var select = new Dropkick("#referconstituencyId"+index);
				select.refresh();
				var select = new Dropkick("#refermandalNameId"+index);
				select.refresh();
				var select = new Dropkick("#referpanchayatId"+index);
				select.refresh();
				$(".stateCls"+index).show();
				$(".distCls"+index).hide();
				$(".constiCls"+index).hide();
				$(".mandalCls"+index).hide();
				$(".panchayatCls"+index).hide();
				
		}
		else if(levelId == 3)
		{
			  $("#referconstituencyId"+index).find('option').not(':first').remove();
			  $("#refermandalNameId"+index).find('option').not(':first').remove();
			  $("#referpanchayatId"+index).find('option').not(':first').remove();
			   var select = new Dropkick("#referconstituencyId"+index);
				select.refresh();
				var select = new Dropkick("#refermandalNameId"+index);
				select.refresh();
				var select = new Dropkick("#referpanchayatId"+index);
				select.refresh();
				$(".stateCls"+index).show();
				$(".distCls"+index).show();
				$(".constiCls"+index).hide();
				$(".mandalCls"+index).hide();
				$(".panchayatCls"+index).hide();
		}
		else if(levelId == 4)
		{
			  $("#referconstituencyId"+index).find('option').not(':first').remove();
			  $("#refermandalNameId"+index).find('option').not(':first').remove();
			  $("#referpanchayatId"+index).find('option').not(':first').remove();
			   var select = new Dropkick("#referconstituencyId"+index);
				select.refresh();
				var select = new Dropkick("#refermandalNameId"+index);
				select.refresh();
				var select = new Dropkick("#referpanchayatId"+index);
				select.refresh();
				$(".stateCls"+index).show();
				$(".distCls"+index).show();
				$(".constiCls"+index).show();
				$(".mandalCls"+index).hide();
				$(".panchayatCls"+index).hide();
		}
		else if(levelId == 5)
		{
			$("#referpanchayatId"+index).find('option').not(':first').remove();
			var select = new Dropkick("#referpanchayatId"+index);
				select.refresh();
				$(".stateCls"+index).show();
				$(".distCls"+index).show();
				$(".constiCls"+index).show();
				$(".mandalCls"+index).show();
				$(".panchayatCls"+index).hide();
		}
		else if(levelId == 6)
		{
			$("#referpanchayatId"+index).find('option').not(':first').remove();
			var select = new Dropkick("#referpanchayatId"+index);
				select.refresh();
				$(".stateCls"+index).show();
				$(".distCls"+index).show();
				$(".constiCls"+index).show();
				$(".mandalCls"+index).show();
				$(".panchayatCls"+index).show();
		}
		
  }
  
  function showHideBySearchType(){
		
		//setToDefaultAdvancedSearch();
			$('#errorDivId').html('');
			var selectVal = $("#advanceSearchTypeId").val();
			
			
			if(selectVal == 2)
			{
				$(".advancePRCls,#searchBtnId").show();
				$(".advanceprclsDiv").show();
				$(".advanceNameCls").hide();
				$(".advanceCadreCommittee,.advanceprclsDiv").hide();
				$(".locationsFilterCls").show();
				$(".advanceprcls").show();
				$("#cadreCommitteeDiv_chosen").hide();
				$(".stateShowCls").show();
				$(".levelShowCls").show();
				setToDefaultAdvancedSearch();
				$("#advanceDesignationId").css("display","none");
				getPublicRepresentsDetails();
				//disableByLevel();
				
			}
			else if(selectVal == 3)
			{
				$("#searchBtnId").show();
				$(".advancePRCls").hide();
				$(".advancePRCls").parent().hide();
				$(".advanceNameCls").hide();
				$(".advanceCadreCommittee").show();
				$(".locationsFilterCls").show();
				$(".advanceprcls").hide();
				$(".stateShowCls").show();
				$(".levelShowCls").show();
				$(".advanceprclsDiv").hide();
				$("#cadreCommitteeDiv_chosen").show();
				$("#cadreCommitteeDiv").css("display","none");
				$(".chosen-choices").css("display","block");
				getCommitteeRoles();
				$(".referRolesCheck").removeAttr("checked");
				setToDefaultAdvancedSearch();
				//disableByLevel();
			}
			else if(selectVal == 1)
			{
				$("#searchBtnId").show();
				$(".stateShowCls").show();
				$(".advanceprclsDiv").show();
				$(".advanceNameCls").show();
				$(".levelShowCls").show();
				$(".advancePRCls").hide();
				$(".advancePRCls").parent().hide();
				$("#cadreCommitteeDiv_chosen").hide();
				$("#referCommitteeDiv").hide();
				clearNameSearchTypeFields();
				
			}
			else if(selectVal == "mobileno" || selectVal == "mebershipno" || selectVal == "votercardno")
			{
				
				
				
				$(".levelShowCls").hide();
				$(".stateShowCls").hide();
				$(".advanceprcls").show();
				$(".advanceNameCls").show();
				$(".advancePRCls").hide();
				$(".advanceCadreCommittee").hide();
				$(".locationsFilterCls").show();
				$("#advanceSearchValueId").val("");
				$(".advanceprclsDiv").show();
				$("#searchBtnId").show();
			}
			else
			{
				
				$(".levelShowCls").hide();
				$(".stateShowCls").hide();
				$(".advanceprcls").show();
				$(".advanceNameCls").show();
				$(".advancePRCls").hide();
				$(".advanceCadreCommittee").hide();
				$(".locationsFilterCls").show();
				$("#advanceSearchValueId").val("");
				$(".advanceprclsDiv").hide();
			}
				disableByLevel('');
				$(".stateCls").show();
				$(".distCls").hide();
				$(".constiCls").hide();
				$(".mandalCls").hide();
				$(".panchayatCls").hide();
	}
 function setDefault(index)
  {
	  $("#referconstituencyId"+index).find('option').not(':first').remove();
	  $("#refermandalNameId"+index).find('option').not(':first').remove();
	  $("#referpanchayatId"+index).find('option').not(':first').remove();
	    var select = new Dropkick("#referconstituencyId"+index);
		select.refresh();
		var select = new Dropkick("#refermandalNameId"+index);
		select.refresh();
		var select = new Dropkick("#referpanchayatId"+index);
		select.refresh();
  } 
  
  
  
  function getDistrictsForReferPopup(index) {
{
	var stateId = $("#stateId"+index).val();
	var jobj = {
		stateId : stateId
	}
	$.ajax({
		type : 'GET',
		url : 'getDistrictsForStateAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jobj)} 
	}).done(function(result){
		var str='';
		str+='<option value="0">Select District</option>';
		if(result != null && result.length > 0){
			for(var i in result){
				str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
			}
		}
		$("#referdistrictId"+index).html(str);
		$("#referdistrictId"+index).dropkick();
		var select1 = new Dropkick("#referdistrictId"+index);
		select1.refresh();
	});
 }
  }
 function getConstituenciesBydistrictForReferPopup(index){
	 var districtId = $("#referdistrictId"+index).val();
	var jobj = {
		districtId : districtId
	}
		$.ajax({
			type : "POST",
			url  : "getConstituenciesByDistrictAction.action",
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			var constiStr='';
			if(result != null && result.length > 0){
			    constiStr +='<option value="0">Select Assembly</option>';
				for(var i in result){
					constiStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
					}
			 $("#referconstituencyId"+index).html(constiStr);
			 $("#referconstituencyId"+index).dropkick();
			var select = new Dropkick("#referconstituencyId"+index);
			 select.refresh();
			}
		});
 }

 function getMandalsByConstituencyForReferPopup(index){
	 var constituencyId = $('#referconstituencyId'+index).val();
	var jobj = {
		constituencyId : constituencyId
	}
		$.ajax({
			type : "POST",
			url  : "getMandalsByConstituencyAction.action",
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			var mandalStr='';
			if(result != null && result.length > 0){
			    mandalStr +='<option value="0">Select Mandal/ Municipality</option>';
				for(var i in result){
					mandalStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
					}
			 $("#refermandalNameId"+index).html(mandalStr);
			 $("#refermandalNameId"+index).dropkick();
			 var select = new Dropkick("#refermandalNameId"+index);
			 select.refresh();
			}
		});
 }
 
 function getPanchayatsForReferPopup(index){
	 $("#referpanchayatId"+index).find('option').not(':first').remove();
	 var mandalId = $('#refermandalNameId'+index).val();
	 var  type = $("#refermandalNameId"+index+" option:selected").text();
			   
			 if(type.indexOf("Mandal") == -1) 
				type = "muncipality" ;
			else
				type = "mandal" ; 
			  var jsObj={
						mandalId :mandalId,
						type:type,
						task:""
						
					}
			 $.ajax({
						type:"POST",
						url :"getPanchayatDetailsAction.action",
						 dataType: 'json',
						data: {task:JSON.stringify(jsObj)}
					}).done(function(result){
						var panchyatStr='';
						if(result!=null && result.length>0){
							panchyatStr +='<option value="0">Select Panchayat</option>';
			            for(var i in result){
				 panchyatStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
			 }
			    $("#referpanchayatId"+index).html(panchyatStr);
			    $("#referpanchayatId"+index).dropkick();
			 var select = new Dropkick("#referpanchayatId"+index);
			 select.refresh();
			}
		   });
		}
		
		
	
	function buildLevels()
	{
	
		var searchType = $("#advanceSearchTypeId").val();
		var str='';
		 $("#alertlevelId").find('option').remove();
		  str+='<option value="2">State</option>';
		  str+='<option value="3">District</option>';
		  if(searchType != 3)
		  str+='<option value="4">Constituency</option>';
		  str+='<option value="5">Mandal/Muncipality</option>';
		  str+='<option value="6">Village/Ward</option>';
		  $("#alertlevelId").append(str);
		    $("#alertlevelId").dropkick();
			 var select = new Dropkick("#alertlevelId");
			 select.refresh();
	}
	
		function buildLevelsForCreatAlert(index)
	{
	
	
		/*var str='';
		 $("#alertlevelId"+index).find('option').remove();
		  str+='<option value="2">State</option>';
		  str+='<option value="3">District</option>';
		 
		  str+='<option value="4">Constituency</option>';
		  str+='<option value="5">Mandal/Muncipality</option>';
		  str+='<option value="6">Village/Ward</option>';
		  $("#alertlevelId"+index).append(str);
		    $("#alertlevelId"+index).dropkick();
			 var select = new Dropkick("#alertlevelId"+index);
			 select.refresh();*/
			 $(".stateCls"+index).show();
				$(".distCls1"+index).hide();
				$(".constiCls1"+index).hide();
				$(".mandalCls1"+index).hide();
				$(".panchayatCls"+index).hide();
	}
	function setToDefaultAdvancedSearch()
	{	
			$("#advanceDesignationId").val(0);
			$("#advanceDesignationId").dropkick('reset');
			
			$("#alertlevelId").val(0);	
			$("#alertlevelId").dropkick('reset');
			
			$("#stateId").val(0);	
			$("#stateId").dropkick('reset');
			
			$("#referCommitteeId").val(0);	
			$("#referCommitteeId").dropkick('reset');
			
			$("#referdistrictId").val(0);
			$("#referdistrictId").dropkick('reset');
			
		   $("#referconstituencyId").val(0);
			$("#referconstituencyId").dropkick('reset');
			
		   $("#refermandalNameId").val(0);
			$("#refermandalNameId").dropkick('reset');
			
		   $("#referpanchayatId").val(0);
		   $("#referpanchayatId").dropkick('reset');
		
	}
	function  clearNameSearchTypeFields(){
		  clearLevelField();
		  clearStateField();
	  }
	   function clearLevelField(){
		  $("#alertlevelId").val(0);	
		  $("#alertlevelId").dropkick('reset'); 
	   }
	   function clearStateField(){
		 $("#stateId").val(0);	
		 $("#stateId").dropkick('reset');
	   }
	   
	   function getPublicRepresentsDetails(){
    	 $("#advanceDesignationId").html('');
    	var jsObj={
    			task:"publicRepresentatives"
    		}
    		$.ajax({
    			  type:'GET',
    			  url: 'getPublicRepresentativeTypes.action',
    			  data: {task:JSON.stringify(jsObj)}
    	   }).done(function(result){
		   var str ='';
    		if(result != null && result.length > 0){
				str +='<option value="0">All</option>';
				for(var i in result){
					str +='<option value='+result[i].id+'>'+result[i].name+'</option>';
				}
				 $("#advanceDesignationId").html(''+str+'');
				 $("#advanceDesignationId").dropkick();
				 var select = new Dropkick("#advanceDesignationId");
				 select.refresh();
			}
		   });	
	}
	
	function getLevelByDesignation()
 {
	 
	  $("#alertlevelId").find('option').remove();
	   var stateGrpIds = ["6","23","7","12","16","22"];
	 var distGrpIds = ["1","9","11"];
	 var mandalGrpIds =["13","3","4","5","17","18","19","20","21"];
	 var constiGrpIds =["2","8","10",];
	 var designationId =$("#advanceDesignationId").val();
	
	 var str ='';
	  if(jQuery.inArray(designationId, stateGrpIds ) > -1)
	 {
		 str+='<option value="2">State</option>';
		
		 $("#alertlevelId").append(str);
	 }
	else if(jQuery.inArray(designationId, distGrpIds ) > -1)
	 {
		 str+='<option value="2">State</option>';
		 str+='<option value="3">District</option>';
		 $("#alertlevelId").append(str);
	 }
	 else if(jQuery.inArray(designationId, distGrpIds ) > -1)
	 {
		 str+='<option value="2">State</option>';
		 str+='<option value="3">District</option>';
		  str+='<option value="4">Constituency</option>';
		 $("#alertlevelId").append(str);
	 }
	 else if(jQuery.inArray(designationId, mandalGrpIds ) > -1)
	 {
		 str+='<option value="2">State</option>';
		 str+='<option value="3">District</option>';
		 str+='<option value="5">Mandal/Muncipality</option>';
		 $("#alertlevelId").append(str);
	 }
	 
	else
	 {
		  str+='<option value="2">State</option>';
		  str+='<option value="3">District</option>';
		   str+='<option value="4">Constituency</option>';
		  str+='<option value="5">Mandal/Muncipality</option>';
		  str+='<option value="6">Village/Ward</option>';
		  $("#alertlevelId").append(str);
	 }
	   $("#alertlevelId").dropkick();
			 var select = new Dropkick("#alertlevelId");
			 select.refresh();
			 		disableByLevel('');
	 	
 }
 
  function showHideSearch(type)
  {
	  if(type == "search")
	  {
		  $(".searchCls").show();
		  $(".advanceSearchCls").hide();
		  $("#cadreCommitteeDiv_chosen").hide();
		  $(".levelShowCls").hide();
		  $(".stateShowCls").hide();
		  
	  }
	  else
	  {
		 $(".advanceSearchCls").show();  
		  $(".searchCls").hide();
		  $("#advanceSearchTypeId").val(0);
		   $("#advanceSearchTypeId").dropkick('reset');
		  $(".chosen-choices").css("display","none");
		    $("#cadreCommitteeDiv_chosen").show();
		    
	  }
		 
	  
  }
  
  function getCommitteeRoles(){
    	var jsObj={
    			task:"roles"
    		}
    		$.ajax({
    			  type:'GET',
    			  url: 'getAllCommitteesAction.action',
    			  data: {task:JSON.stringify(jsObj)}
    	   }).done(function(result){
				
				var str ='';
				str +='<option id="0" attr_value="All"  >All</option>';
					for(var i in result){
						str +='<option value="'+result[i].id+'" attr_value="'+result[i].name+'"  >'+result[i].name+'</option>';
					}
				
				$("#cadreCommitteeDiv").html(str);
				$("#cadreCommitteeDiv").chosen();
				$("#cadreCommitteeDiv").trigger("chosen:updated");
				$("#cadreCommitteeDiv_chosen").addClass("m_top20");
				$("#cadreCommitteeDiv_chosen").addClass("addwidth");
				});			  
    }
	
	function handleBySearchType()
	{
		getAdvancedSearchDetails();
				
	}
	
	
	function getAdvancedSearchDetails(){
		
		$("#apptmemberDetailsDiv").html("");
		var statusArr=[];
		var tdpCadreIds=[];
		var level;
		var levelValue;
		var tehsilId = 0;
		var committeeId = 0;
		var referCommitteeId;
		var errorStr='';
		var levelStr;
		$("#errorDivId").html('');
		var searchType;
		var searchValue = "";
		var districtId=0;
		var constituencyId=0;
		var mandalId = 0;
		var panchayatId=0;
		var levelId=0;
		var stateId=0;
		var advanceSearchType = $("#advanceSearchTypeId").val();
		stateId = $("#stateId").val();
		 if(advanceSearchType==0){
			 errorStr='Please Select Search Type';
			 $("#errorDivId").html(errorStr);
		 }
		  var aptUserId = 0;
		 // aptUserId = $("#appointmentUserSelectBoxId").val();
		if(advanceSearchType == 1)
		{
			levelStr ="";
			 searchType = "name";
			 searchValue = $("#advanceSearchValueId").val().trim();
			 if(searchValue == null || searchValue.length ==0){
				 errorStr='Please Enter Name';
				 $("#errorDivId").html(errorStr);
				 return;
			 }else if(searchValue.length<3){
				 errorStr='Name should be minimum three characters.';
				 $("#errorDivId").html(errorStr);
				 return; 
			 }
		}
		else if(advanceSearchType == "mobileno" || advanceSearchType == "mebershipno" || advanceSearchType == "votercardno")
		{
			getDetailsBySrch();
			return;
		}
		
		else if(advanceSearchType == 2)
		{
			 searchType = "publicRepresentative";
			 searchValue = $("#advanceDesignationId").val();
			
		}
		else if(advanceSearchType == 3)
		{
			 searchType = "CadreCommittee";
				$("#cadreCommitteeDiv option:selected").each(function ()
			{		
				var desgnaValue = $(this).attr("value");
				if(desgnaValue ==null || desgnaValue =="" || desgnaValue == undefined){
					return false;
				}
				else{
					statusArr.push($(this).attr("value"));
				}		
			});
			 referCommitteeId = $("#referCommitteeId").val();
		}
		
		 districtId = $("#referdistrictId").val();
		 constituencyId = $("#referconstituencyId").val();
		var tehsilName =  $("#refermandalNameId selected:option").text();
		if($("#refermandalNameId").val() > 0){
			if(tehsilName.indexOf('Mandal') == -1)
		tehsilId = "2"+$("#refermandalNameId").val();
		else
		tehsilId = "1"+$("#refermandalNameId").val();
		}
		if($("#refermandalNameId").val() == 0)
		tehsilId = $("#refermandalNameId").val();
	     panchayatId = $("#referpanchayatId").val();
		 
		 var panchayatName =  $("#referpanchayatId selected:option").text();
		 if($("#referpanchayatId").val() > 0){
			if(panchayatName.indexOf('WARD') == -1)
		panchayatId = "1"+$("#referpanchayatId").val();
		
		}
		
		 levelId  = $("#alertlevelId").val();
		 var alertLevelId =levelId;
		if(levelId == 2)
		{
			
			level = "state";
			
			alertLevelId = 10; 
		
		}
			
		if(levelId == 3)
		{
			level = "district";
			alertLevelId = 11; 
		}
			
		if(levelId == 4)
		{
			level = "constituency";
			alertLevelId = 1; 
		}
			
		if(levelId == 5)
		{
			level = "mandal";
			alertLevelId = 6;
		}
			
		if(levelId == 6)
		{
			level = "village";
			alertLevelId = 6;
		}
			
		if(districtId == 0)
		{
			levelStr = "state";
			levelValue = 0;
			
		}
		else if(districtId > 0 && constituencyId == 0)
		{
			levelStr = "district";
			levelValue = districtId;
			
		}
		else if(districtId > 0 && constituencyId > 0 && tehsilId == 0)
		{
			levelStr = "constituency";
			levelValue = constituencyId;
			
		}
		
		else if(districtId > 0 && constituencyId > 0 && tehsilId > 0 && panchayatId == 0)
		{
			
			levelStr = "mandal";
			levelValue = tehsilId;
			
		}
		
		else if(districtId > 0 && constituencyId > 0 && tehsilId > 0 && panchayatId > 0)
		{
			levelStr = "village";
			levelValue = panchayatId;
			
		}
		$('#errorDivId').html(errorStr);
		if(levelId == 2){
		if(stateId==0 || stateId=='select'){
			
				errorStr +="Please Select State";
			}
		}
          if(levelId == 3){
		
			 districtId = $("#referdistrictId").val();
			
			if(districtId==0 || districtId=='select'){
			
				errorStr +="Please Select District";
			}
		}
		
		 else if(levelId == 3){
			 districtId = $("#referdistrictId").val();
			if(districtId==0 || districtId=='select'){
				
				errorStr +="Please Select District";
				$("#errorDivId").html(errorStr);
				return ;
				
			}
			  if(constituencyId == 0 || constituencyId=='select'){
				 constituencyId = $("#referconstituencyId").val();
				errorStr +="Please Select Assembly";
				$("#errorDivId").html(errorStr);
				return;
			}
		}
		if(errorStr.length >0)
       {
	  $('#errorDivId').html(errorStr);
	  return ;
       } 
	   
	//Party Commitee Members	
	if(advanceSearchType !=null && advanceSearchType == 3){
		$("#cadreCommitteeDiv option:selected").each(function ()
		{		
			var desgnaValue = $(this).attr("value");
			if(desgnaValue ==null || desgnaValue =="" || desgnaValue == undefined){
				return false;
			}
			else{
				statusArr.push($(this).attr("value"));
			}		
		});
		committeeId = referCommitteeId;	
	}
	
	//Public Representatives
	if(advanceSearchType !=null && advanceSearchType == 2){
		var desgnaValue = $("#advanceDesignationId").val();
		if(desgnaValue > 0)
		statusArr.push(desgnaValue);
		committeeId = "0";	
	}
		if(errorStr.length>0){
			$("#errorDivId").html(errorStr);
			return;
		}
		$("#searchMemberAjax").css("display","block");
	
		$("#apptmemberDetailsDiv").html("<center><img src='images/search.gif'/> </center>");
		
		var jsObj={
			searchType:searchType,
			searchValue:searchValue,
			designations:statusArr,
			committeeId:committeeId, // "PR" -- if public representatives
			levelId:alertLevelId,
			districtId:districtId,
			constituencyId:constituencyId,
			mandalId:tehsilId,
			panchayatId:panchayatId,
			stateId:stateId,
			levelStr:levelStr,
			aptUserId:aptUserId
		}
		$("#apptmemberDetailsDiv").html('');
		  	$.ajax({
				type : 'POST',
				url : 'getAdvancedSearchDetailsAction.action',
				dataType : 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				$("#searchMemberAjax").css("display","none");
				$("#apptmemberDetailsDiv").html("");
				if(result !=null && result.length>0){
				buildapptmemberDetails(result);
				
				}else{
					$("#apptmemberDetailsDiv").html("<center><h4>No Data Available</h4></center>");
				}
		  }); 
	}
	
	function getDetailsBySrch()
	{
		//clearing the Data Div
		$("#apptmemberDetailsDiv").html("");
		//clearing err Div
		$("#errDigitsId").html(" ");
		
		var searchValue=$("#searchValueId").val();
		var errStr=false;
		//Validations
		if($("#searchTypeId").val()==0){
			 $("#errDigitsId").html("Please Select Search Type");
			 errStr=true;
		}
		else if($("#searchTypeId").val()=="mobileno"){			
			if(searchValue ==null || searchValue.length ==0 || searchValue == undefined || searchValue ==""){
					  $("#errDigitsId").html("Please enter Mobile No");
					  errStr=true;
				 }	
				else if(searchValue.length != 10 || isNaN(searchValue)){		
					$("#errDigitsId").html("Please Enter Valid Mobile Number");
					 errStr=true;
				}
		}else if($("#searchTypeId").val() == "mebershipno"){
			if(searchValue ==null || searchValue.length ==0 || searchValue == undefined || searchValue ==""){
					  $("#errDigitsId").html("Please Enter MembershipNo ");
					  errStr=true;
			}else if(searchValue.length !=8 || isNaN(searchValue)){
				  $("#errDigitsId").html("Please Enter valid Membership ");
					  errStr=true;
			}
		}else if($("#searchTypeId").val() == "votercardno"){
			if(searchValue ==null || searchValue.length ==0 || searchValue == undefined || searchValue ==""){
					  $("#errDigitsId").html("Please Enter Votercardno ");
					  errStr=true;
			}
		}
		
		if(errStr){
			return;
		}else{
				getSearchDetails();
				//$(".addattrid").hide();
			}
		
	}
	

	function getSearchDetails(){
	  
	  $("#searchMemberAjax").css("display","block");
	  var searchType = $("#advanceSearchTypeId").val();
	  var searchValue = $("#advanceSearchValueId").val();
	  var aptUserId = 0;
		
		var jsObj={
			searchType:searchType,
			searchValue:searchValue,
			aptUserId:aptUserId
		  }
		$("#apptmemberDetailsDiv").html('');
		  	$.ajax({
				type : 'POST',
				url : 'getsearchRequestedMembersAction.action',
				dataType : 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				 $("#searchMemberAjax").css("display","none");
				
				if(result !=null && result.length>0){
					buildapptmemberDetails(result);
				
				}else{
					$("#apptmemberDetailsDiv").html("<center><h4>No Data Available</h4></center>");
				}
		  }); 
	 }
	 
	
	function applyPagination(){
		$('#searchedMembersId').DataTable();
	}
	
	
	function setDefaultImage(img){
	  img.src = "dist/Appointment/img/thumb.jpg";
   }
	var cloneCount=0;
	var involvedCadreIds = [];
   $(document).on("click",".apptDetailsDiv",function(){
		
		 if($(this).is(':checked')){
			 $("#involvedCandidatesDiv").show();
			 $(".membersBlock").show();
			  var name  = $(this).attr("attr_name");
			  var image = $(this).attr("attr_img_url");
			  var attrId = $(this).attr("attr_id");
			  var attrConsti =  $(this).attr("attr-consti");
			   var mobile = $(this).attr("attr_mobile");
			/* $(".membersBlock").append('<div class="block"><input type="hidden" class="form-control candidatecls"  name="alertVO.idNamesList['+cloneCount+'].id" value="'+attrId+'" /><div id="memberDiv'+attrId+'" class="row m_top10"><div class="col-md-3 col-md-offset-1"><p>Name : '+name+'</p></div>  <div class="col-md-3"><p>Constituency : '+attrConsti+' </p></div><span class="closeIcon" clone_block_count="'+cloneCount+'"><i class="glyphicon glyphicon-remove"></i></span></span><div class="col-md-3"><label>Alert Impact</label><select class="form-control"  id="alertImpactId" name="alertVO.idNamesList['+cloneCount+'].orderId"><option value="1">Positive </option>	<option value="2">Negative </option></select></div></div></div>');*/
			var str ='';
			str+='<div class="col-md-3">';
            str+='<div class="involveBlock">';
			str+='<div class="media"><div class="media-left">';
			str+='<img src="'+image+'" alt="image" style="height:30px;width:30px;" class="img-circle">';
			str+='</div>';
			str+='<div class="media-body">';
			str+='<input type="hidden" class="form-control memberDatacls" name="alertVO.idNamesList['+cloneCount+'].id" value="'+attrId+'"/>';
			str+='<div class="col-md-12"><b>'+name+'</b></div>';
			str+='<div class="col-md-12"><b>'+mobile+'</b></div>';
			str+='<div class="col-md-12"><label>'+attrConsti+'</label></div>';
			str+='<div class="col-md-12"><div class="form-inline">';
			//str+='<select class="form-control" name="alertVO.idNamesList['+cloneCount+'].orderId"><option value="1">Positive</option><option value="2">Negative</option></select>';
			str+='<div class="onoffswitch" style="display:inline-block">';
			str+='<input type="checkbox"  name="alertVO.idNamesList['+cloneCount+'].name" name="onoffswitch" class="onoffswitch-checkbox" id="myonoffswitch'+cloneCount+'" checked>';
			str+='<label class="onoffswitch-label" for="myonoffswitch'+cloneCount+'">';
            str+='<span class="onoffswitch-inner"></span>';
            str+='<span class="onoffswitch-switch"></span>';
			str+='</label>';
			str+='</div>';
			str+='</div></div></div></div><span class="closeIcon" id="'+attrId+'" clone_block_count="'+cloneCount+'"><i class="glyphicon glyphicon-remove removeIconNew"></i></span></div></div>';
			 $(".membersBlock").append(str);
			 //jQuery.inArray( attrId, involvedCadreIds )
				involvedCadreIds.push(attrId);			 
			  cloneCount = cloneCount+1;
			   $('html, body').animate({
                    scrollTop: $('.membersBlock').offset().bottom
                }, 2000);
		 }
   })
   $(document).on("click",".closeIcon",function(){
	$(this).parent().remove();
	var id=$(this).attr("id");
	
	$(".candidatecls"+id).prop('checked', false); 
	//var blockCount = $(this).attr("clone_block_count");
	$(".close"+id).prop('checked', false); 
	//$("#uncheck"+id).parent().find(".apptDetailsDiv").prop('checked', false); 
});
 
	
