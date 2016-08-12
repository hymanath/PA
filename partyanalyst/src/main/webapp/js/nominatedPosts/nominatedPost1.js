
function getDistrictsForStatesForNotCadre(state){

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
      
 for(var i in result) {
if(result[i].id == 0){
				  $("#notCadreDistId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			   }else{
				  $("#notCadreDistId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			   }
	 }	 
	 $("#notCadreDistId").trigger('chosen:updated');
  });
}
 

 function getConstituenciesForDistrictsForNotcadre(district){
	 
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
	for(var i in result){   
	 if(result[i].id == 0){
				  $("#notCadreConstId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			   }else{
				  $("#notCadreConstId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			   }
	}
	
	$("#notCadreConstId").trigger('chosen:updated');
	});
 }

function getMandalCorporationsByConstituencyForNotcadre(constituency)
	{	
	var jsObj ={					
					constituencyId:constituency
				};
				 $.ajax({
					type : "GET",
					url : "getMandalDetailsByConstituencyAction.action",//getMandalsForAConstituencyAjaxAction
					data : {task:JSON.stringify(jsObj)} 
				}).done(function(result){
				if(result !=null)
				{
					$("#notCadreMandlId").empty();
					
					for(var i in result)
					{
						if(result[i].id == 0){
							$("#notCadreMandlId").append('<option value='+result[i].id+'>Select Mandal/Muncipality/Corporation</option>');
						}
						else{
							$("#notCadreMandlId").append('<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>');
						}
					}	
				}
				$("#notCadreMandlId").trigger("chosen:updated");
				});
	}

	function getPanchayatWardByMandalForNotcadre(mandalId){
		var constituencyId = $('#notCadreConstId').val();
			var jsObj={
				mandalId:mandalId,
				constituencyId : constituencyId
			}
			$.ajax({
				type : "POST",
				url : "getPanchayatWardByMandalAction.action",
				data : {task:JSON.stringify(jsObj)} 
			}).done(function(result){
				
						$("#notCadrePanchayatId").empty();
					
			for(var i in result){
				if(result[i].id == 0){
							$("#notCadrePanchayatId").append('<option value='+result[i].id+'>Select Panchayat/Ward/Division/City</option>');
						}
						else{
					$("#notCadrePanchayatId").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
						}
			}
			
				$("#notCadrePanchayatId").trigger("chosen:updated");	
			
		});	
			
	}

	function getRelationTypeDetails(){
		$("#relativetypeId").empty();
  var jsObj={
        task:""
      }
      $.ajax({
         type:'GET',
         url:'getAllRelationDetails.action',
         dataType: 'json',
         data: {task:JSON.stringify(jsObj)}
      }).done(function(result){
      if(result !=null){
      $("#relativetypeId").append("<option value='0'>Select RelationType</option>");
        for(var i in result)
            $("#relativetypeId").append("<option value='"+result[i].id+"'>"+result[i].name+"</option>");
        }
		$("#relativetypeId").trigger("chosen:updated");
		});

	}

  	var saveFlag=false;
	function saveNotCadreDetails(){
		saveFlag=false;
		var uploadHandler = {
				upload: function(o) {
					$("#savingAjaxImg").css("display","none");
					uploadResult = o.responseText;
					
					showNotCadreSavingStatus(uploadResult);
				}
			};
			
			YAHOO.util.Connect.setForm('saveNotCadre',true);
			YAHOO.util.Connect.asyncRequest('POST','saveNotCadreDetailsAction.action',uploadHandler);
	
	}
	function showNotCadreSavingStatus(result){
		globalNominatedCandId = "";
		if(result.indexOf("SUCCESS") > -1){
			globalNominatedCandId = result.replace( /[^\d.]/g, '' );
				//saveFlag =true;
			$("#notCadreSavId").html("<span style='color: green;font-size:22px;'>Not Cadre Saved Successfully</span>");
			 setTimeout(function(){
			$("#notCadreSavId").html("");
			
			//$("#addMemberModalBlock").modal('hide');
			}, 2000); 
			getNotCadreDetails(globalNominatedCandId);
		}else {
			setTimeout(function(){
			$("#notCadreSavId").html("<span style='color: red;font-size:22px;'>Application Saved Failed. Please Try Again.</span>");
			//$("#addMemberModalBlock").modal('hide');
			}, 1000);
		}
	}

	function getCastesForAP(){
	
		var jsObj={}
		$.ajax({
	          type:'GET',
	          url: 'getCastesForAPAction.action',
	          dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		   if(result != null && result.length > 0){
			   $("#casteId").append('<option value="0">Select Caste</option>');
			   for(var i in result){
				   $("#casteId").append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
			   }
			  
			   $("#casteId").trigger("chosen:updated");	
		   }
	   });
	}
getCastesForAP();

function getNotCadreDetails(globalNominatedCandId){
	
	var jsObj={
		nominatedPostCandiId : globalNominatedCandId
	}
		$.ajax({
	          type:'GET',
	          url: 'getNotCadreDetailsAction.action',
	          dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		   if(result != null)
					{
					buildCadreDetails(result);
					$("#addMemberModalBlock").modal('hide');
					$("#searchDivId").show();
					}
	   });
}
getRelationTypeDetails();

function validateAddNewCandidateFields(){
	var voterId =$(".voterCls").val();
	var name = $("#nameId").val(); 
	var mobileNo = $("#mobilenoId").val();
	var gender = $("#genderId").val();
	var dob=$("#DOBId").val();
	var image = $("#imageurlId").val();
	var casteId = $("#casteId").val();
	var stateId = $("#notCadreStateId").val();
	var districtId = $("#notCadreDistId").val();
	var  constituencyId = $("#notCadreConstId").val();
	var  panWardDivisionId = $("#notCadreMandlId").val();
	var  notCadrePanchayatId = $("#notCadrePanchayatId").val();
	var pinCodeNo=$("#pincodeId").val();
	 if(voterId == null || voterId.trim().length == 0){
		 $(".addNewCandidateErrorCls").html("Please Enter Voter Id.");
		 return false;
	 }
	 if(name == null || name.trim().length == 0){
		 $(".addNewCandidateErrorCls").html("Please Enter Name.");
		 return false;
	 }
	 if(mobileNo == null || mobileNo.trim().length == 0){
		 $(".addNewCandidateErrorCls").html("Please Enter Mobile No.");
		 return false;
	 }
	 if(mobileNo.trim().length != 10)
			{
				$('.addNewCandidateErrorCls').html('Invalid Mobile No.');
				return false;				
			}
	  var mobileValidateDigits= /^\d{10}$/;
	 if(!mobileValidateDigits.test(mobileNo)) {
		 $(".addNewCandidateErrorCls").html("Mobile Number Must be 10 digits");  
		  return false; 
	 }
	 if(gender==0){
		 $(".addNewCandidateErrorCls").html("Please Select Gender.");
		 return false; 
	 }
	 
	 if(dob== null || dob.trim().length == 0){
		 $(".addNewCandidateErrorCls").html("Please Date Of Birth.");
		 return false;  
	 }
 	 if(dob !=null && dob !=undefined && dob.trim() !=""){
		 var dobArr=dob.split("/");	
		  var year=(new Date().getFullYear())-(dobArr[2]);
		  $("#ageId").val(year);
	 }	 
	 var age = $("#ageId").val();
	 if(age == null || age.trim().length == 0 || age == 0){
		 $(".addNewCandidateErrorCls").html("Please Enter Age.");
		 return false; 
	 }
	 if(image == null || image.trim().length == 0){
	   $(".addNewCandidateErrorCls").html("Please Upload Image.");
		return false;  
	 }
	 if(casteId==0){
		$(".addNewCandidateErrorCls").html("Please Select Caste.");
		return false;   
	 }
	 if(stateId ==null || stateId == undefined || stateId =="" || stateId==0){
		$(".addNewCandidateErrorCls").html("Please Select State.");
		return false;   
	 }
	 if(districtId ==null || districtId == undefined || districtId=="" || districtId==0){
		$(".addNewCandidateErrorCls").html("Please Select District.");
		return false;   
	 }
	 if(constituencyId ==null || constituencyId == undefined || constituencyId==0 || constituencyId==0){
		$(".addNewCandidateErrorCls").html("Please Select Constituency.");
		return false;   
	 }
	 if(panWardDivisionId ==null || panWardDivisionId == undefined || panWardDivisionId==0 || panWardDivisionId==0){
		$(".addNewCandidateErrorCls").html("Please Select Mandal/ Muncipality / Corporation.");
		return false;   
	 }
	 if(notCadrePanchayatId ==null || notCadrePanchayatId == undefined || notCadrePanchayatId == "" || notCadrePanchayatId==0){
		$(".addNewCandidateErrorCls").html("Please Select Panchayat/ Ward / Division/City.");
		return false;   
	 }
	 if(pinCodeNo == null || pinCodeNo.trim().length == 0){
		 $(".addNewCandidateErrorCls").html("Please Enter pinCode.");
		 return false;
	 }
   if(pinCodeNo.trim().length != 6)
			{
				$('.addNewCandidateErrorCls').html('Invalid pinCode.');
				return false;				
			}
	 $(".addNewCandidateErrorCls").html(" ");
	  return true;
}
 $(document).on("blur","#DOBId",function(){
	var dob=$("#DOBId").val();
	if(dob!=null && dob!=undefined && dob.trim() !=""){		
	 var dobArr=dob.split("/");	
	 var year=(new Date().getFullYear())-(dobArr[2]);
	 $("#ageId").val(year);
	}
}); 
$(document).on("click","#addCandidateBtnId",function(){
	var flag = validateAddNewCandidateFields();
	if(!$('#voterExtraErrId').is(':empty')){
		flag=false;
	}
	if(flag){
		saveNotCadreDetails();
	}
});
  function showHideBySearchType(){
		
		//setToDefaultAdvancedSearch();
			$('#errorDivId').html('');
			var selectVal = $("#advanceSearchTypeId").val();
			
			
			if(selectVal == 2)
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
		/*if(stateId==0 || stateId=='select'){
			
				errorStr +="Please Select State";
			}*/
		}
          if(levelId == 3){
		
			 districtId = $("#referdistrictId").val();
			
			/*if(districtId==0 || districtId=='select'){
			
				errorStr +="Please Select District";
			}*/
		}
		
		 else if(levelId == 4){
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
	/*$(document).on("click",".closeIcon",function(){
	//$(this).parent().remove();
	$(this).closest(".col-md-3").remove();
	var id=$(this).attr("id");
	$(".candidatecls"+id).prop('checked', false); 
	$(".close"+id).prop('checked', false); 
	involvedCadreIds.pop(id);	
	$("#involvedMembers").html('('+involvedCadreIds.length+' - Members added)');
});*/
function setDefaultImage(img){
	  img.src = "dist/Appointment/img/thumb.jpg";
   }
	
	var involvedCadreIds = [];
   $(document).on("click",".apptDetailsDiv",function(){
		
		 if($(this).is(':checked')){
		
			 $("#involvedCandidatesDiv").show();
			 $(".membersBlock").show();
			 $("#addedRefferalsDiv").show();
			  var name  = $(this).attr("attr_name");
			  var image = $(this).attr("attr_img_url");
			  var attrId = $(this).attr("attr_id");
			  var attrConsti =  $(this).attr("attr-consti");
			   var mobile = $(this).attr("attr_mobile");
			/* $(".membersBlock").append('<div class="block"><input type="hidden" class="form-control candidatecls"  name="alertVO.idNamesList['+cloneCount+'].id" value="'+attrId+'" /><div id="memberDiv'+attrId+'" class="row m_top10"><div class="col-md-3 col-md-offset-1"><p>Name : '+name+'</p></div>  <div class="col-md-3"><p>Constituency : '+attrConsti+' </p></div><span class="closeIcon" clone_block_count="'+cloneCount+'"><i class="glyphicon glyphicon-remove"></i></span></span><div class="col-md-3"><label>Alert Impact</label><select class="form-control"  id="alertImpactId" name="alertVO.idNamesList['+cloneCount+'].orderId"><option value="1">Positive </option>	<option value="2">Negative </option></select></div></div></div>');*/
			var str ='';
			str+='<div class="col-md-3">';
            str+='<div class="involveBlock" attr_cadreId="'+attrId+'">';
			str+='<div class="media"><div class="media-left">';
			str+='<img src="'+image+'" onerror="setDefaultImage(this);" alt="image" style="height:30px;width:30px;" class="img-circle">';
			str+='</div>';
			str+='<div class="media-body">';
			str+='<input type="hidden" class="form-control memberDatacls" name="alertVO.idNamesList['+cloneCount+'].id" value="'+attrId+'"/>';
			str+='<div class="col-md-12"><b>'+name+'</b></div>';
			str+='<div class="col-md-12"><b>'+mobile+'</b></div>';
			str+='<div class="col-md-12"><label>'+attrConsti+'</label></div>';
			str+='<div class="col-md-12"><div class="form-inline">';
			//str+='<select class="form-control" name="alertVO.idNamesList['+cloneCount+'].orderId"><option value="1">Positive</option><option value="2">Negative</option></select>';
			str+='<div class="onoffswitch" style="display:inline-block">';
			/*str+='<input type="checkbox"  name="alertVO.idNamesList['+cloneCount+'].name" name="onoffswitch" class="onoffswitch-checkbox checkedReffrl" id="myonoffswitch'+cloneCount+'" checked value="'+attrId+'">';*/
			str+='<label class="onoffswitch-label" for="myonoffswitch'+cloneCount+'">';
            str+='<span class="onoffswitch-inner"></span>';
            str+='<span class="onoffswitch-switch"></span>';
			str+='</label>';
			str+='</div>';
			str+='</div></div></div></div><span class="closeIcon" id="'+attrId+'" clone_block_count="'+cloneCount+'"><i class="glyphicon glyphicon-remove removeIconNew" style="display: block;"  ></i></span></div></div>';
			$("#duplicateCandidateBlock").html('');
			
			if(jQuery.inArray(attrId, involvedCadreIds) == -1)
			{
				involvedCadreIds.push(attrId);	
				
				$(".membersBlock").append(str);
				$("#involvedMembers").html('('+involvedCadreIds.length+' - Members added)');
			}else{
				var duplicateStr ='';
				duplicateStr+='<p class="text-capital" >'+name+'</p>';
				duplicateStr+='<p>'+mobile+'</p>';
				duplicateStr+='<p class="text-capitalize">'+attrConsti+'</p>';
				$("#duplicateCandidateBlock").html(''+duplicateStr+'');
				$("#myModalConformation").modal('show');
			}
			 
					 
			  cloneCount = cloneCount+1;
			  /*  $('html, body').animate({
                    scrollTop: $('.membersBlock').offset().bottom
                }, 2000); */
		 }
   })
   
    $(document).on("click",".closeIcon",function(){
	//var id=$(this).attr("id");
	var id =0;
	var retVal = confirm("Are you sure want to remove this refer ?");
               if( retVal == true ){
				    id=$(this).attr("id");
					  $(".involveBlock").each(function(){
						var cadreId = $(this).attr("attr_cadreId") ; 
							if(id == cadreId)
							{
								$(this).remove();
							}
					  });
				  $(".candidatecls"+id).prop('checked', false); 
				  $(".close"+id).prop('checked', false); 
				  involvedCadreIds.pop(id);	
				  $("#involvedMembers").html('('+involvedCadreIds.length+' - Members added)');
				return true;
               }
               else{
                  return false;
               }
			   
}); 
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
		$('#searchedMembersId').DataTable({
			
		});
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
		function getLevelByDesignation()
 {
  
    $("#alertlevelId").find('option').remove();
     var stateGrpIds = ["7","12","16"];
   var distGrpIds = ["6","14","15","23","19","20"];
   var mandalGrpIds =["3","25"];
   var constiGrpIds =["2","4","5","8","10","1","9","11","13","17","18","22","21"];
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
      //str+='<option value="5">Mandal/Muncipality</option>';
      //str+='<option value="6">Village/Ward</option>';
      $("#alertlevelId").append(str);
   }
     $("#alertlevelId").dropkick();
       var select = new Dropkick("#alertlevelId");
       select.refresh();
           disableByLevel('');
     }
	 /* function getConfirmation(){
               var retVal = confirm("Are you sure want to remove this refer ?");
			   alert(retVal)
               if( retVal == true ){
				   return true;
               }
               else{
                  return false;
               }
            } */