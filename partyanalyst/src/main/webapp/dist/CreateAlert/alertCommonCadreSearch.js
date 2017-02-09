disableByLevel('');
buildLevelsByIndex('');
getMemberTypes();
//showHideSearch("advanceSearch");
showHideBySearchType();
function disableByLevel(index)
  {
      	
	  var levelId = $("#commonLevelId"+index).val();
	  var districtId = $("#referdistrictId"+index).val();
	  var constituencyId = $("#referconstituencyId"+index).val();
	  var panchayatId = $("#referpanchayatId"+index).val();
	  var mandalId = $("#refermandalNameId"+index).val();
	  var select = new Dropkick("#referdistrictId"+index);
		select.refresh();
		
		if(levelId != 10 && levelId != 0)
		{
			getDistrictsForReferPopup(index);
		}
		
		 if(levelId == 10 || levelId == 0)
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
		else if(levelId == 11)
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
		else if(levelId == 1)
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
		}else if(levelId == 12){ //central level 
		   $("#referCommitteeDiv").hide(); // hiding committee in the case of central level
			$(".stateShowCls").hide();
			$(".distCls"+index).hide();
			$(".constiCls"+index).hide();
			$(".mandalCls"+index).hide();
			$(".panchayatCls"+index).hide();
		}
		var selectVal = $("#advanceSearchTypeId").val();
		if(selectVal == "committee"){
			 buildDesignation(globalDesignationRslt);  
			if(levelId != 12){
			 $("#referCommitteeDiv").show();	
			}
		}
}

function getLevelByDesignation()
 {
	
	  $("#levelId").find('option').remove();
	   var stateGrpIds = ["6","23","7","12","16","22"];
	 var distGrpIds = ["1","9","11"];
	 var mandalGrpIds =["13","3","4","5","17","18","19","20","21"];
	 var constiGrpIds =["2","8","10",];
	 var designationId =$("#advanceDesignationId").val();
	
	 var str ='';
	  if(jQuery.inArray(designationId, stateGrpIds ) > -1)
	 {
		 str+='<option value="10">State</option>';
		
		 $("#levelId").append(str);
	 }
	else if(jQuery.inArray(designationId, distGrpIds ) > -1)
	 {
		 str+='<option value="10">State</option>';
		 str+='<option value="11">District</option>';
		 $("#levelId").append(str);
	 }
	 else if(jQuery.inArray(designationId, distGrpIds ) > -1)
	 {
		 str+='<option value="10">State</option>';
		 str+='<option value="11">District</option>';
		  str+='<option value="1">Constituency</option>';
		 $("#levelId").append(str);
	 }
	 else if(jQuery.inArray(designationId, mandalGrpIds ) > -1)
	 {
		 str+='<option value="10">State</option>';
		 str+='<option value="11">District</option>';
		 str+='<option value="5">Mandal/Muncipality</option>';
		 $("#levelId").append(str);
	 }
	 
	else
	 {
		  str+='<option value="10">State</option>';
		  str+='<option value="11">District</option>';
		   str+='<option value="1">Constituency</option>';
		  str+='<option value="5">Mandal/Muncipality</option>';
		  str+='<option value="6">Village/Ward</option>';
		  $("#levelId").append(str);
	 }
	   $("#levelId").dropkick();
			 var select = new Dropkick("#levelId");
			 select.refresh();
			 		disableByLevel();
	 	
 }
  function showHideBySearchType(){
	 
			$('#errorDivId').html('');
			var selectVal = $("#advanceSearchTypeId").val();
			if(selectVal == 1){
				$(".requiredCls").hide();
			}else{
				$(".requiredCls").show();       
			}
			if(selectVal == "PR")
			{
				$(".advancePRCls,#searchBtnId").show();
				$(".advancePRCls").parent().show();
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
			else if(selectVal == "committee")
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
			else if(selectVal == "name")
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
				//$("#searchNameLabel").text("Search By Name/Membership No*");
				$("#searchNameLabel").text("Search By Name*");
			}
			else if(selectVal == "0")
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
				$("#advanceDesignationId").css("display","none");
				$(".advancePRCls").parent().hide();
			    $("#cadreCommitteeDiv_chosen").hide(); 
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
				$(".advanceprclsDiv").show();
				$("#searchBtnId").show();
				$("#cadreCommitteeDiv_chosen").hide();
				/* if(selectVal != "mebershipno" && selectVal != "votercardno")
				$("#searchNameLabel").text("Search By Mobile");
				else
				$("#searchNameLabel").text("Search By Name/Membership No*"); */
			    if(selectVal == "mobileno")
				{
					$("#searchNameLabel").html("Search By Mobile No <span style='color:red;' class='requiredCls'> * </span> :");
				}
				else if(selectVal == "mebershipno")
				{
					$("#searchNameLabel").html("Search By Membership No <span style='color:red;' class='requiredCls'> * </span> :");
				}
				else if(selectVal == "votercardno")
				{
					$("#searchNameLabel").html("Search By Voter Card No <span style='color:red;' class='requiredCls'> * </span> :");
				}else if(selectVal == 1)
				{
					$("#searchNameLabel").html("Search By Mobile No <span  class='requiredCls'> </span> :");
				}
		
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
  
  function clearCommonFields()
  {
	   $("#advanceSearchTypeId").val(0);
	   var select = new Dropkick("#advanceSearchTypeId");
		select.refresh();
	  $("#advanceSearchValueId").val('');
	  showHideBySearchType();
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
		str+='<option value="0">ALL</option>';
		if(result != null && result.length > 0){
			for(var i in result){
				if(result[i].id > 0)
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
			    constiStr +='<option value="0">ALL</option>';
				for(var i in result){
					if(result[i].id > 0)
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
			    mandalStr +='<option value="0">ALL</option>';
				for(var i in result){
					if(result[i].id > 0)
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
							panchyatStr +='<option value="0">ALL</option>';
			            for(var i in result){
							if(result[i].id > 0)
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
		 $("#commonLevelId").find('option').remove();
		 if(searchType == "committee"){
		   str+='<option value="12">Central</option>';
		 }
		  str+='<option value="10">State</option>';
		  str+='<option value="11">District</option>';
		  if(searchType != "committee")
		  str+='<option value="1">Constituency</option>';
		  str+='<option value="5">Mandal/Muncipality</option>';
		  str+='<option value="6">Village/Ward</option>';
		  $("#commonLevelId").append(str);
		    $("#commonLevelId").dropkick();
			 var select = new Dropkick("#commonLevelId");
			 select.refresh();
			 disableByLevel('');
	}
	
		function buildLevelsByIndex(index)
		{
				$(".stateCls"+index).show();
				$(".distCls"+index).hide();
				$(".constiCls"+index).hide();
				$(".mandalCls"+index).hide();
				$(".panchayatCls"+index).hide();
		}
	function setToDefaultAdvancedSearch()
	{	
			$("#advanceDesignationId").val(0);
			$("#advanceDesignationId").dropkick('reset');
			
			$("#commonLevelId").val(0);	
			$("#commonLevelId").dropkick('reset');
			
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
		  $("#commonLevelId").val(0);	
		  $("#commonLevelId").dropkick('reset'); 
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
  var globalDesignationRslt ;
  function getCommitteeRoles(){
    	var jsObj={
    			task:"roles"
    		}
    		$.ajax({
    			  type:'GET',
    			  url: 'getAllCommitteesAction.action',
    			  data: {task:JSON.stringify(jsObj)}
    	   }).done(function(result){
			   globalDesignationRslt = result;
			   if(result != null && result.length > 0){
				  buildDesignation(result);  
			    }
			});			  
    }
	function buildDesignation(result){
		var str ='';
		str +='<option id="0" attr_value="All">All</option>';
			for(var i in result){
				str +='<option value="'+result[i].id+'" attr_value="'+result[i].name+'"  >'+result[i].name+'</option>';
			}
		
		$("#cadreCommitteeDiv").html(str);
		$("#cadreCommitteeDiv").chosen();
		$("#cadreCommitteeDiv").trigger("chosen:updated");
		$("#cadreCommitteeDiv_chosen").addClass("m_top20");
		$("#cadreCommitteeDiv_chosen").addClass("addwidth");	
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
		if(advanceSearchType == "name")
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
			 var numericExpression =  /^[a-z,A-Z," "]+$/;
					if(!$("#advanceSearchValueId").val().match(numericExpression)){
						 errorStr='Enter characters Only';
						  $("#errorDivId").html(errorStr);
					 return;
					 }
		}
		
		else if(advanceSearchType == "PR")
		{
			 searchType = "publicRepresentative";
			 searchValue = $("#advanceDesignationId").val();
		}
		else if(advanceSearchType == "committee")
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
			 if($("#referCommitteeDiv").is(':visible')){
				 referCommitteeId = $("#referCommitteeId").val();
			 }else{
				  referCommitteeId = 0;
			 }
		}
		
		//else if(advanceSearchType == "mobileno" || advanceSearchType == "mebershipno" || advanceSearchType == "votercardno")
		else 
		{
			getDetailsBySrch();
			return;
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
		 levelId  = $("#commonLevelId").val();
		if(levelId == 10)
		{
			level = "state";
		}
		if(levelId == 11)
		{
			level = "district";
		}
		if(levelId == 1)
		{
			level = "constituency";
		}
		if(levelId == 5)
		{
			level = "mandal";
		}
		if(levelId == 6)
		{
			level = "village";
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
		/*if(levelId == 10){
		if(stateId==0 || stateId=='select'){
			
				errorStr +="Please Select State";
			}
		}*/
          if(levelId == 11){
		
			 districtId = $("#referdistrictId").val();
			
			/*if(districtId==0 || districtId=='select'){
			
				errorStr +="Please Select District";
			}*/
		}
		
		 else if(levelId == 1){
			 districtId = $("#referdistrictId").val();
			/*if(districtId==0 || districtId=='select'){
				
				errorStr +="Please Select District";
				$("#errorDivId").html(errorStr);
				return ;
				
			}
			  if(constituencyId == 0 || constituencyId=='select'){
				 constituencyId = $("#referconstituencyId").val();
				errorStr +="Please Select Assembly";
				$("#errorDivId").html(errorStr);
				return;
			}*/
		}
			else if(levelId == 5 || levelId == 6){
			 districtId = $("#referdistrictId").val();
			if(districtId==0 || districtId=='select'){
				
				errorStr +="Please Select District";
				$("#errorDivId").html(errorStr);
				return ;
				
			}
		}
		
		if(levelId == 12) //Central Level 
		{
			level = "central";
			levelStr = "central";
		}
		
		if(errorStr.length >0)
       {
	  $('#errorDivId').html(errorStr);
	  return ;
       } 
	//Party Commitee Members	
	if(advanceSearchType !=null && advanceSearchType == "committee"){
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
	if(advanceSearchType !=null && advanceSearchType == "PR"){
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
			levelId:levelId,
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
		$("#errorDivId").html(" ");
		
		var searchValue=$("#advanceSearchValueId").val().trim();
	
		var errStr=false;
		//Validations
		if($("#advanceSearchTypeId").val() == 0){
			 $("#errorDivId").html("Please Select Search Type");
			 errStr=true;
		}
		else if($("#advanceSearchTypeId").val()=="mobileno"){
			
			if(searchValue ==null || searchValue.length ==0 || searchValue == undefined || searchValue ==""){
					  $("#errorDivId").html("Please enter Mobile No");
					  errStr=true;
					  
				 }	
				else if(searchValue.length < 10 || isNaN(searchValue)){		
					$("#errorDivId").html("Please Enter Valid Mobile Number");
					 errStr=true;
				}
		}else if($("#advanceSearchTypeId").val() == "mebershipno"){
			if(searchValue ==null || searchValue.length ==0 || searchValue == undefined || searchValue ==""){
					  $("#errorDivId").html("Please Enter MembershipNo ");
					  errStr=true;
					 
			}else if(searchValue.length < 8 || isNaN(searchValue)){
				 
				  $("#errorDivId").html("Please Enter valid Membership ");
					  errStr=true;
			}
		}else if($("#advanceSearchTypeId").val() == "votercardno"){
			if(searchValue ==null || searchValue.length ==0 || searchValue == undefined || searchValue ==""){
					  $("#errorDivId").html("Please Enter Votercardno ");
					  errStr=true;
			}
		}
		else
		{
			 if((searchValue.length >0 && searchValue.length < 10) || isNaN(searchValue)){
								 
					$("#errorDivId").html("Please Enter Valid Mobile Number");
					 errStr=true;
				}
		}
		
		if(errStr){
			
			return;
		}else{
				getSearchDetails();
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

 	function getLevelByDesignation()
	{
	$("#commonLevelId").find('option').remove();
	 var stateGrpIds = ["6","23","7","12","16","22"];
	 var distGrpIds = ["1","9","11"];
	 var mandalGrpIds =["13","3","4","5","17","18","19","20","21"];
	 var constiGrpIds =["2","8","10",];
	 var designationId =$("#advanceDesignationId").val();
		var str ='';
	  if(jQuery.inArray(designationId, stateGrpIds ) > -1)
	 {
		 str+='<option value="10">State</option>';
		 $("#commonLevelId").append(str);
	 }
	else if(jQuery.inArray(designationId, distGrpIds ) > -1)
	 {
		 str+='<option value="10">State</option>';
		 str+='<option value="11">District</option>';
		 $("#commonLevelId").append(str);
	 }
	 else if(jQuery.inArray(designationId, distGrpIds ) > -1)
	 {
		 str+='<option value="10">State</option>';
		 str+='<option value="11">District</option>';
		  str+='<option value="1">Constituency</option>';
		 $("#commonLevelId").append(str);
	 }
	 else if(jQuery.inArray(designationId, mandalGrpIds ) > -1)
	 {
		 str+='<option value="10">State</option>';
		 str+='<option value="11">District</option>';
		 str+='<option value="5">Mandal/Muncipality</option>';
		 $("#commonLevelId").append(str);
	 }
	 
	else
	 {
		  str+='<option value="10">State</option>';
		  str+='<option value="11">District</option>';
		   str+='<option value="1">Constituency</option>';
		  str+='<option value="5">Mandal/Muncipality</option>';
		  str+='<option value="6">Village/Ward</option>';
		  $("#commonLevelId").append(str);
	 }
	   $("#commonLevelId").dropkick();
			 var select = new Dropkick("#commonLevelId");
			 select.refresh();
			 		disableByLevel('');
	 	
 }
   var cloneCount=0;
   var commontdpCadreIds = [];
   $(".membersBlock").html(' ');
	/* $(document).on("click",".apptDetailsDiv",function(){
		alert(321);
		var name  = $(this).attr("attr_name");
		var image = $(this).attr("attr_img_url");
		var attrId = $(this).attr("attr_id");
		var attrConsti =  $(this).attr("attr-consti");
		var mobile =$(this).attr("attr_mobile");
		if($(this).is(':checked')){
			$("#involvedCandidatesDiv").show();       
			$(".membersBlock").show();
			var str ='';
			str+='<div class="col-md-4 col-xs-12 col-sm-6">';
			str+='<div class="block">';
			str+='<div class="media"><div class="media-left">';
			str+='<img src="'+image+'" alt="image" style="height:30px;width:30px;" class="img-circle">';
			str+='</div>';
			str+='<div class="media-body">';
			str+='<input type="hidden" class="form-control memberDatacls" name="alertVO.idNamesList['+cloneCount+'].id" value="'+attrId+'"/>';
			str+='<div class="col-md-12 m_top5"><label><b>Name </b>: '+name+'</label></div>';
			str+='<div class="col-md-12 m_top5"><label><b>Constituency </b>: '+attrConsti+'</label></div>';
			str+='</div></div><span class="closeIcon closeIcon'+attrId+'" id="'+attrId+'" clone_block_count="'+cloneCount+'"><i class="glyphicon glyphicon-remove" style="cursor:pointer;"></i></span></div></div>';
			alert(str);
			$(".membersBlock").append(str);
			alert(str);  
			cloneCount = cloneCount+1;
			$("#assignBtnId").show();
			commontdpCadreIds.push(attrId);
			var addStr ='';
			addStr+='<p class="text-capital" >'+name+'</p>';
			addStr+='<p>'+mobile+'</p>';
			addStr+='<p class="text-capitalize">'+attrConsti+'</p>';
			$("#duplicateCandidateBlock").html(''+addStr+'');
			$("#memberConfirmation").html("Member Added");
			$("#myModalConformation").modal('show');
			setTimeout(function(){ 
				$("#myModalConformation").modal('hide');
			}, 2000);
			setTimeout(function(){
				$("body").addClass("modal-open");
			}, 3000);
		}else{
			var i = $.inArray(attrId,commontdpCadreIds)
			if(i>=0){
				commontdpCadreIds.splice(i, 1);
			}
			$(".closeIcon"+attrId).closest(".col-md-4").remove();
		}
   }); */
	
	function getMemberTypes(){
		var jsObj ={
			task : ""
		}
		$.ajax({
			type:'GET',
			url: 'getMemberTypesAction.action',
			data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			buildMemberTypes(result);
		})
	}

	function buildMemberTypes(result){
		for(var i in result){
			$("#advanceSearchTypeId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');	
		}
		var select = new Dropkick("#advanceSearchTypeId");
		select.refresh();
	}
