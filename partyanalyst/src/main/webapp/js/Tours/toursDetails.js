var regionScopesArr = [{"id":"2","name":"State"},{"id":"3","name":"District"},{"id":"4","name":"Constituency"},{"id":"5","name":"Mandal / Municipality"},{"id":"6","name":"Village / Ward"}];

function getCandidateList(designationId){ 
		var jsObj = {     
			designationId : designationId
		}
		$.ajax({
			type : 'POST',
			url : 'getCandidateListAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#memberSlctBxId").empty();
			$("#memberSlctBxId").append("  <option value='0'>Select Name</option>");
			if(result != null && result.length > 0){
				for(var i in result){
					$("#memberSlctBxId").append("<option value="+result[i].id+">"+result[i].name+"</option>");  
				}
				// $("#memberSlctBxId").append("<option value='-1'>Other Name</option>");  
			}
			$("#memberSlctBxId").trigger("chosen:updated");
		});
	}
	$(document).on("change","#designationSlctBxId",function(){
		var designationId = $(this).val();
		$("#selectedMemberDtslDivId").html(' ');
		$(".showDivCls").hide();
		 if(designationId != null && designationId > 0){
			 getCandidateList(designationId);
		 }
	});
	$(document).on("change","#memberSlctBxId",function(){
		var candidateId = $(this).val();
		 if(candidateId != null && candidateId > 0){
			//$(".otherMemberBlockCls").hide();
			//$(".showDivCls").show();
			//$(".hideProfileDivCls").show();
			$(".ownDivCls").hide();
			$(".inchageDivCls").hide();
			 getCandiateSearchDetails(candidateId);
		 }else if(candidateId == -1){
			//$(".otherMemberBlockCls").show();
			//$(".showDivCls").hide();
			//$(".hideProfileDivCls").hide();
			 //$("#searchValueInputBoxId").val(' ');
			 //$("#searchValueInputBoxId").attr("placeholder","Enter MemberShip Number");	
			getConstituenciesList();
		 }
	});
	function getCandiateSearchDetails(candidateId){
    $("#selectProfileId").show();		
	$("#selectedMemberDtslDivId").html(' ');
	$(".showDivCls").hide();
	var jsObj = { 
			 candidateId : candidateId
			}
		$.ajax({
			type : 'POST',
			url : 'getCandiateDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#profileProcessingImgId").hide();
			
			if(result != null){ 
				buildSelectedProfileRslt(result);
			}
		});
	}
	function buildSelectedProfileRslt(result){
		
		//CadreId For Allocating Saving Hidden Variable
		
		if(result.tdpCadreId !=null){
			$("#globalHiddentdpCadreId").val(result.tdpCadreId); 
		}
		
		
		var str='';
		//str+='<h4 class="panel-title text-capital">selected profile</h4>';
			str+='<ul class="list-inline">';
			str+='<li>';
				str+='<div class="panel panel-default panelProfile">';
					str+='<div class="panel-body">';
						str+='<img src="http://mytdp.com/images/cadre_images/'+result.image+'" onerror="setDefaultImage(this);" style="width: 50px; height: 50px;"></img>';
						str+='<p>'+result.name+'</p>';
						str+='<p>'+result.memberShipNo+'</p>';
						str+='<p>'+result.mobileNo+'</p>';
					str+='</div>';
					str+='<div class="panel-footer">';
						str+='<label class="checkbox-inline">';
							str+='<input id="profileCheckboxId" attr_cadre_id="'+result.tdpCadreId+'" type="checkbox"/>Select Profile';
						str+='</label>';
					str+='</div>';
					
				str+='</div>';
			str+='</li>';
            str+='</ul>';
		$("#selectedMemberDtslDivId").html(str);
		$("#selectedProfileId").show();
		var locationList = result.subList;
	     if(locationList != null && locationList.length > 0){
			 $("#inchargeSelectBoxId").removeAttr("name");
			 $("#inchageLocationScopeId").removeAttr("name");
			 $("#inchageownLocationScopeValue").removeAttr("name");
			 
			 $("#ownSelectBoxId").removeAttr("name");
			 $("#ownLocationScopeId").removeAttr("name");
			 $("#ownLocationScopeValue").removeAttr("name");
			 
			 $("#ownLocationScopeId").removeAttr("value");
			 $("#ownLocationScopeValue").removeAttr("value");
			 
			 $("#inchageLocationScopeId").removeAttr("value");
			 $("#inchageownLocationScopeValue").removeAttr("value");
			 
			 $("#ownSelectBoxId").removeClass("isActive");
			 $("#inchargeSelectBoxId").removeClass("isActive");             
 
			 for(var i in locationList){
				 if(locationList[i].locationScopeId != null && locationList[i].locationScopeId==1){
					 if(locationList[i].type != null && locationList[i].type=="Own"){
						 $("#ownLabelId").html("Own DIstrict");
						 $("#ownLocationScopeId").attr("value",locationList[i].locationScopeId);
						 $("#ownLocationScopeValue").attr("value",locationList[i].locationValue);
						 
						 $("#ownSelectBoxId").attr("name","toursInputVO.ownTours");
						 $("#ownLocationScopeId").attr("name","toursInputVO.ownLocationScopeId");
						 $("#ownLocationScopeValue").attr("name","toursInputVO.ownLocationId");
						 $("#ownSelectBoxId").addClass("isActive");
						 
						 $(".ownDivCls").show();
					 }else if(locationList[i].type != null && locationList[i].type=="Incharge"){
						$("#inchargeLableId").html("Incharge DIstrict");
						 $("#inchageLocationScopeId").attr("value",locationList[i].locationScopeId);
						 $("#inchageownLocationScopeValue").attr("value",locationList[i].locationValue);
						 
						 $("#inchargeSelectBoxId").attr("name","toursInputVO.inchargeTours");
						 $("#inchageLocationScopeId").attr("name","toursInputVO.inchargeLocationScopeId");
						 $("#inchageownLocationScopeValue").attr("name","toursInputVO.inchargeLocationId");
						 $("#inchargeSelectBoxId").addClass("isActive");
					    $(".inchageDivCls").show();   
					 }
				 }else if(locationList[i].locationScopeId != null && locationList[i].locationScopeId==2){
					 if(locationList[i].type != null && locationList[i].type=="Own"){  
						 $("#ownLabelId").html("Own Parliament");
						 $("#ownLocationScopeId").attr("value",locationList[i].locationScopeId);
						 $("#ownLocationScopeValue").attr("value",locationList[i].locationValue);
						 
						 $("#ownSelectBoxId").attr("name","toursInputVO.ownTours");
						 $("#ownLocationScopeId").attr("name","toursInputVO.ownLocationScopeId");
						 $("#ownLocationScopeValue").attr("name","toursInputVO.ownLocationId");
						 $("#ownSelectBoxId").addClass("isActive");
						 $(".ownDivCls").show();    
					 }else if(locationList[i].type != null && locationList[i].type=="Incharge"){
						$("#inchargeLableId").html("Incharge Parliament");
						 $("#inchageLocationScopeId").attr("value",locationList[i].locationScopeId);
						 $("#inchageownLocationScopeValue").attr("value",locationList[i].locationValue);
						 
						 $("#inchargeSelectBoxId").attr("name","toursInputVO.inchargeTours");
						 $("#inchageLocationScopeId").attr("name","toursInputVO.inchargeLocationScopeId");
						 $("#inchageownLocationScopeValue").attr("name","toursInputVO.inchargeLocationId");
						 $("#inchargeSelectBoxId").addClass("isActive");
						 
						$(".inchageDivCls").show(); 
					 }
				 }else if(locationList[i].locationScopeId != null && locationList[i].locationScopeId==3){
					  if(locationList[i].type != null && locationList[i].type=="Own"){
						 $("#ownLabelId").html("Own Assembly");
						 $("#ownLocationScopeId").attr("value",locationList[i].locationScopeId);
						 $("#ownLocationScopeValue").attr("value",locationList[i].locationValue);
						 
						 $("#ownSelectBoxId").attr("name","toursInputVO.ownTours");
						 $("#ownLocationScopeId").attr("name","toursInputVO.ownLocationScopeId");
						 $("#ownLocationScopeValue").attr("name","toursInputVO.ownLocationId");
						 $("#ownSelectBoxId").addClass("isActive");
						 
						 $(".ownDivCls").show();
					 }else if(locationList[i].type != null && locationList[i].type=="Incharge"){
						$("#inchargeLableId").html("Incharge Assembly");
						 $("#inchageLocationScopeId").attr("value",locationList[i].locationScopeId);
						 $("#inchageownLocationScopeValue").attr("value",locationList[i].locationValue);
						 
						 $("#inchargeSelectBoxId").attr("name","toursInputVO.inchargeTours");
						 $("#inchageLocationScopeId").attr("name","toursInputVO.inchargeLocationScopeId");
						 $("#inchageownLocationScopeValue").attr("name","toursInputVO.inchargeLocationId");
						 $("#inchargeSelectBoxId").addClass("isActive");
						 
						$(".inchageDivCls").show();     
					 }
				 }
			 }
		 }
	}
	getDesigationList(); //default call 
	function getDesigationList(){ 	
		var jsObj = { }
		$.ajax({
			type : 'POST',
			url : 'getDesigationListAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				$("#designationSlctBxId").empty();
				$("#designationSlctBxId").append("<option value='0'>Select Designation Level</option>");
				for(var i in result){
				 $("#designationSlctBxId").append("<option value="+result[i].id+">"+result[i].name+"</option>");	
				}
			}
			$("#designationSlctBxId").trigger("chosen:updated");
		});
	}
	
	function getAllTourCategorys(cadreId){ 
		var jsObj = {     
			cadreId : cadreId
		}
		$.ajax({
			type : 'POST',
			url : 'getAllTourCategorysAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#tourCategoryId0").empty();
			$("#tourCategoryId0").append("  <option value='0'>Select Category</option>");
			if(result != null && result.length > 0){
				TourCategoryArray =result;
				for(var i in result){
					$("#tourCategoryId0").append("<option value="+result[i].id+">"+result[i].name+"</option>");  
				}
			}
			$("#tourCategoryId0").chosen();
			$("#tourCategoryId0").trigger("chosen:updated");
		});
	}
	getAllTourTypes()
	function getAllTourTypes(){ 
		var jsObj = {     
			
		}
		$.ajax({
			type : 'POST',
			url : 'getAllTourTypesAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#tourTypeId0").empty();
			$("#tourTypeId0").append("  <option value='0'>Select TourTypes</option>");
			if(result != null && result.length > 0){
				TourTypesArray=result;
				for(var i in result){
					$("#tourTypeId0").append("<option value="+result[i].id+">"+result[i].name+"</option>");  
				}
			}
			$("#tourTypeId0").chosen();
			$("#tourTypeId0").trigger("chosen:updated");
		});
	}
	function savingApplication(){
		$("#errMnthId").html("");
		$("#errYearId").html("");
		$(".textErrCls").html("");
		$("#errFileId").html("");
		var flag = true;
		var filerKit = $("#update_TourFileId2").prop("jFiler");
		var monthValue = $("#monthSelectBoxId").val();
		if(monthValue == 0){
			$("#errMnthId").html("Please Select Month.");
			return;
		}
		var yearValue = $("#yearId").val();
		if(yearValue == 0){  
			$("#errYearId").html("Please Select Year.");
			return;  
		}
		$(".isActive").each(function(){
			var value = $(this).val();
			var bool = $.isNumeric(value);
			if(bool == false){
				var errId = $(this).attr("attr_err_id");
				$("#"+errId).html("Please Enter Numbers.");
				flag = false;
				return false;      
			}  
		});
		
		var childEleCount = $(".jFiler-items-list").children().length;
		/* if(childEleCount > 1){
			$("#errFileId").html("Please Select Only One Document."); 
			filerKit.reset();   
			flag = false;         			
		} */
		
		//allocating designationId To Hidden Variable
		
		var desigId=$("#designationSlctBxId option:selected").val();
		if(desigId !=null){
			$("#globalHiddenDesignationId").val(desigId);
		}
		
		if(flag == false){
			return;  
		}
		var uploadHandler = { 
			upload: function(o) {
				$("#savingAjaxImg").css("display","none");
				uploadResult = o.responseText;
				showSbmitStatus(uploadResult);
			}
		};
		YAHOO.util.Connect.setForm('submitApplication',true);  
		//YAHOO.util.Connect.asyncRequest('POST','savingTourDtlsApplicationAction.action',uploadHandler);
		YAHOO.util.Connect.asyncRequest('POST','saveNewTourDetailsAction.action',uploadHandler);
	}
	function showSbmitStatus(uploadResult){
			$(".clearFieldCls").val(' ');
			$("#monthSelectBoxId").val(0);
			$("#yearId").val(0);    
			$("#update_TourFileId2").val('');        
			$(".selectChosen").trigger("chosen:updated");
			var filerKit = $("#update_TourFileId2").prop("jFiler");
			//if(uploadResult != null && uploadResult == '<pre>"Saved"</pre>'){
			setTimeout(function () {
			$("#successSpanId").html("<center style='color: green; font-size: 16px;'>Saved Successfully</center>").fadeOut(3000);
			}, 500);  
			$("#successSpanId").show();
			$("#successSpanId").html("");       
			filerKit.reset();
			/* }else{
				setTimeout(function () {      
					$("#successSpanId").html("<center style='color: red; font-size: 16px;'>Failed</center>").fadeOut(3000);
					}, 500);  
					$("#successSpanId").show();
					$("#successSpanId").html("");          
			} */
				
	}
	$(document).on("click","#profileCheckboxId",function(){
		if($(this).is(':checked')){
			$(".showDivCls").show();
			getAllTourCategorys($(this).attr("attr_cadre_id"));
		}else{
			$(".showDivCls").hide();
		}
	});
	
	$(document).on("change",".tourCategoryCls",function(){
		var jsObj = {     
			cadreId :$("#profileCheckboxId").attr("attr_cadre_id"),
			categoryId :$(this).val()
		}
		var count = $(this).attr("attr_count");
		
		$.ajax({
		  type : 'POST',
		  url : 'getAllCandidateLocationsAction.action',
		  dataType : 'json',
		  data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null){
				buildCandidateLocationResult(result,count);
			}
		});
	});
	
	function buildCandidateLocationResult(result,count){
			if(regionScopesArr != null && regionScopesArr.length > 0){
				$("#tourLocationId"+count).html("<option value='0'>Select Location</option>");
				for(var i in regionScopesArr){
					if(parseInt(regionScopesArr[i].id)>=result.locationLevel)
						$("#tourLocationId"+count).append("<option value='"+regionScopesArr[i].id+"'>"+regionScopesArr[i].name+"</option>");
				}
			}
			
			if(result.locationLevel == 5 || result.locationLevel == 7)
				$("#tourLocationId"+count).val(5);
			else if(result.locationLevel == 6 || result.locationLevel == 8)
				$("#tourLocationId"+count).val(6);
			else
				$("#tourLocationId"+count).val(result.locationLevel);
			
			$("#tourLocationId"+count).attr("attr_candidate_loc_level",result.locationLevel);
			
			if(result.locationLevel == 2){
				$("#stateDivId"+count).show();
			}else if(result.locationLevel == 3){
				$("#stateDivId"+count).show();
				$("#districtDivId"+count).show();
			}else if(result.locationLevel == 4){
				$("#stateDivId"+count).show();
				$("#districtDivId"+count).show();
				$("#constituencyDivId"+count).show();
			}else if(result.locationLevel == 5 || result.locationLevel == 7){
				$("#stateDivId"+count).show();
				$("#districtDivId"+count).show();
				$("#constituencyDivId"+count).show();
				$("#tehMunDivId"+count).show();
			}else if(result.locationLevel == 6 || result.locationLevel == 8){
				$("#stateDivId"+count).show();
				$("#districtDivId"+count).show();
				$("#constituencyDivId"+count).show();
				$("#tehMunDivId"+count).show();
				$("#villWardDivId"+count).show();
			}
			
			if(result.statesList != null && result.statesList.length > 0){
				$("#stateSelId"+count).html("<option value='0'>Select State</option>");
				for(var i in result.statesList){
					$("#stateSelId"+count).append("<option value='"+result.statesList[i].id+"'>"+result.statesList[i].name+"</option>");
				}
			}
			
			if(result.stateId != null && result.stateId > 0)
				$("#stateSelId"+count).val(result.stateId);
			
			if(result.distList != null && result.distList.length > 0){
				$("#districtSelId"+count).html("<option value='0'>Select District</option>");
				for(var i in result.distList){
					$("#districtSelId"+count).append("<option value='"+result.distList[i].id+"'>"+result.distList[i].name+"</option>");
				}
			}
			
			if(result.districtId != null && result.districtId > 0)
				$("#districtSelId"+count).val(result.districtId);
			
			if(result.constList != null && result.constList.length > 0){
				$("#constituencySelId"+count).html("<option value='0'>Select Constituency</option>");
				for(var i in result.constList){
					$("#constituencySelId"+count).append("<option value='"+result.constList[i].id+"'>"+result.constList[i].name+"</option>");
				}
			}
			
			if(result.constituencyId != null && result.constituencyId > 0)
				$("#constituencySelId"+count).val(result.constituencyId);
			
			if(result.manTowDivList != null && result.manTowDivList.length > 0){
				$("#tehMunSelId"+count).html("<option value='0'>Select Tehsil / Municipality</option>");
				for(var i in result.manTowDivList){
					$("#tehMunSelId"+count).append("<option value='"+result.manTowDivList[i].id+"'>"+result.manTowDivList[i].name+"</option>");
				}
			}
			
			if(result.locationLevel == 5)
				$("#tehMunDivId"+count).val(result.tehsilId);
			if(result.locationLevel == 7)
				$("#tehMunDivId"+count).val(result.localElectionBodyId);
			
			if(result.panWardList != null && result.panWardList.length > 0){
				$("#villWardSelId"+count).html("<option value='0'>Select Village / Ward</option>");
				for(var i in result.panWardList){
					$("#villWardSelId"+count).append("<option value='"+result.panWardList[i].id+"'>"+result.panWardList[i].name+"</option>");
				}
			}
			
			if(result.locationLevel == 6)
				$("#villWardSelId"+count).val(result.panchayatId);
			if(result.locationLevel == 8)
				$("#villWardSelId"+count).val(result.wardId);
			
	}
	$(document).on("change",".tourLocationCls",function(){
		var count = $(this).attr("attr_TourCount");
		var candiLocLevel = $(this).attr("attr_candidate_loc_level");
			
		$(".locationDivCls"+count).hide();
		if($(this).val() == 2){//state
			$("#stateDivId"+count).show();
		}else if($(this).val()==3){//district
			$("#stateDivId"+count).show();
			$("#districtDivId"+count).show();
		}else if($(this).val()==4){//constituency
			$("#stateDivId"+count).show();
			$("#districtDivId"+count).show();
			$("#constituencyDivId"+count).show();
		}else if($(this).val()==5 || $(this).val() == 7){//Mandal
			$("#stateDivId"+count).show();
			$("#districtDivId"+count).show();
			$("#constituencyDivId"+count).show();
			$("#tehMunDivId"+count).show();
		}else if($(this).val()==6 || $(this).val() == 8){//village
			$("#stateDivId"+count).show();
			$("#districtDivId"+count).show();
			$("#constituencyDivId"+count).show();
			$("#tehMunDivId"+count).show();
			$("#villWardDivId"+count).show();
		}
		
		if(candiLocLevel == 2){
			$("#districtSelId"+count).val(0);
			$("#constituencySelId"+count).html("<option value='0'>Select Constituency</option>");
			$("#tehMunSelId"+count).html("<option value='0'>Select Mandal / Municipality</option>");
			$("#villWardSelId"+count).html("<option value='0'>Seelcy Village / Ward</option>");
		}else if(candiLocLevel == 3){
			$("#constituencySelId"+count).val(0);
			$("#tehMunSelId"+count).html("<option value='0'>Select Mandal / Municipality</option>");
			$("#villWardSelId"+count).html("<option value='0'>Seelcy Village / Ward</option>");
		}else if(candiLocLevel == 4){
			$("#tehMunSelId"+count).val(0);
			$("#villWardSelId"+count).html("<option value='0'>Seelcy Village / Ward</option>");
		}	
	});
	$(document).on("change",".constituencySelCls",function(){
	var count = $(this).attr("attr_count");
		var jsObj = {     
			constituencyId :$(this).val()
		}
		$.ajax({
		  type : 'POST',
		  url : 'getAllMunicipalitiesAction.action',
		  dataType : 'json',
		  data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#tehMunSelId"+count).html("<option value='0'>Select Mandal / Municipality</option>");
			if(result != null && result.length > 0){
				for(var i in result){
					$("#tehMunSelId"+count).append("<option value="+result[i].locationId+">"+result[i].locationName+"</option>");  
				}
			}
		});
	});
	$(document).on("change",".tehMunSelCls",function(){
		var count = $(this).attr("attr_count");
		var jsObj = {     
			tehsilId :$(this).val()
		}
		$.ajax({
		  type : 'POST',
		  url : 'getPanchayatWardDivisionDetailsNewAction.action',
		  dataType : 'json',
		  data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				$("#villWardSelId"+count).html("<option value='0'>Select Village / Ward</option>");
				for(var i in result){
					$("#villWardSelId"+count).append("<option value="+result[i].locationId+">"+result[i].locationName+"</option>");  
				}
			}
		});
	});
