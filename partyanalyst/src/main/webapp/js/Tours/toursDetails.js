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
		var jsObj = {}
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
	
	function getAllTourCategorys(cadreId,designationId){ 
		var jsObj = {     
			cadreId : cadreId,
			designationId:designationId
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
		
		var errStr='',flag1=true;
		$(".outerDivCls").each(function(){
			if(flag1){
				var count = $(this).attr("attr_count");
				if($("#tourTypeId"+count).val() == 0 || $("#tourTypeId"+count).val() == "undefined" || $("#tourTypeId"+count).val() === undefined){
					errStr="Please Select Tour Type";flag1=false;
				}else if($("#tourCategoryId"+count).val() == 0 || $("#tourCategoryId"+count).val() == "undefined" || $("#tourCategoryId"+count).val() === undefined){
					errStr="Please Select Tour Category";flag1=false;
				}else if($("#tourLocationId"+count).val() > 0){
					var locLvl = $("#tourLocationId"+count).val();
					if(locLvl == 2){
						if($("#stateSelId"+count).val() == 0){
							errStr="Please Select State";flag1=false;
						}
					}else if(locLvl == 3){
						if($("#stateSelId"+count).val() == 0){
							errStr="Please Select State";flag1=false;
						}else if($("#districtSelId"+count).val() == 0){
							errStr="Please Select District";flag1=false;
						} 
					}else if(locLvl == 4){
						if($("#stateSelId"+count).val() == 0){
							errStr="Please Select State";flag1=false;
						}else if($("#districtSelId"+count).val() == 0){
							errStr="Please Select District";flag1=false;
						}else if($("#constituencySelId"+count).val() == 0){
							errStr="Please Select Constituency";flag1=false;
						}
					}else if(locLvl == 5){
						if($("#stateSelId"+count).val() == 0){
							errStr="Please Select State";flag1=false;
						}else if($("#districtSelId"+count).val() == 0){
							errStr="Please Select District";flag1=false;
						}else if($("#constituencySelId"+count).val() == 0){
							errStr="Please Select Constituency";flag1=false;
						}else if($("#tehMunSelId"+count).val() == 0){
							errStr="Please Select Mandal / Municipality";flag1=false;
						}
					}else if(locLvl == 6){
						if($("#stateSelId"+count).val() == 0){
							errStr="Please Select State";flag1=false;
						}else if($("#districtSelId"+count).val() == 0){
							errStr="Please Select District";flag1=false;
						}else if($("#constituencySelId"+count).val() == 0){
							errStr="Please Select Constituency";flag1=false;
						}else if($("#tehMunSelId"+count).val() == 0){
							errStr="Please Select Mandal / Municipality";flag1=false;
						}else if($("#villWardSelId"+count).val()==0){
							errStr="Please Select Village / Ward";flag1=false;
						}
					}
				}
			}
		});
		
		if(!flag1){
			alert(errStr);
			return;
		}
	
		var uploadHandler = { 
			upload: function(o) {
				$("#savingAjaxImg").css("display","none");
				uploadResult = o.responseText;
				showSbmitStatusNew(uploadResult);
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
	function showSbmitStatusNew(uploadResult){
		if(uploadResult !=null && uploadResult.search("success") != -1){
			$("#successSpanId").show();			
			$("#update_TourFileId2").val('');
			var filerKit = $("#update_TourFileId2").prop("jFiler");
			setTimeout(function () {
				$("#successSpanId").html("<center style='color: green; font-size: 16px;'>Saved Successfully</center>").fadeOut(3000);
				filerKit.reset();
				location.reload(true);				
			}, 500);
		}
	}
	$(document).on("click","#profileCheckboxId",function(){
		if($(this).is(':checked')){
			$(".showDivCls").show();
			var desigDtlsId = $("#designationSlctBxId").val();
			getAllTourCategorys($(this).attr("attr_cadre_id"),desigDtlsId);
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
		$("#tourLocationId"+count).html("<option value='0'>Select Location</option>");
			if(regionScopesArr!=null && regionScopesArr.length>0 && result.locationLevel!=null && result.locationLevel>0){
				for(var i in regionScopesArr){
					if(parseInt(regionScopesArr[i].id)>=result.locationLevel)
						$("#tourLocationId"+count).append("<option value='"+regionScopesArr[i].id+"'>"+regionScopesArr[i].name+"</option>");
				}
			}
			
			if(result.locationLevel != null && result.locationLevel > 0){
				if(result.locationLevel == 5 || result.locationLevel == 7)
					$("#tourLocationId"+count).val(5);
				else if(result.locationLevel == 6 || result.locationLevel == 8)
					$("#tourLocationId"+count).val(6);
				else
					$("#tourLocationId"+count).val(result.locationLevel);
			}else{
				$("#tourLocationId"+count).val(0);
			}
			
			
			$("#tourLocationId"+count).attr("attr_candidate_loc_level",result.locationLevel);
			
			$(".locationDivCls"+count).hide();
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
		$("#tehMunSelId"+count).html("<option value='0'>Select Mandal / Municipality</option>");
		$("#villWardSelId"+count).html("<option value='0'>Select Village / Ward</option>");
		var jsObj = {     
			constituencyId :$(this).val()
		}
		$.ajax({
		  type : 'POST',
		  url : 'getAllMunicipalitiesAction.action',
		  dataType : 'json',
		  data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			
			if(result != null && result.length > 0){
				for(var i in result){
					$("#tehMunSelId"+count).append("<option value="+result[i].locationId+">"+result[i].locationName+"</option>");  
				}
			}
		});
	});
	$(document).on("change",".tehMunSelCls",function(){
		var count = $(this).attr("attr_count");
		$("#villWardSelId"+count).html("<option value='0'>Select Village / Ward</option>");
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
				
				for(var i in result){
					$("#villWardSelId"+count).append("<option value="+result[i].locationId+">"+result[i].locationName+"</option>");  
				}
			}
		});
	});
	var fromDate = moment().format('DD/MM/YYYY');
	var toDate = moment().format('DD/MM/YYYY');
	$('#toursDateRangePickerNew').on('apply.daterangepicker', function(ev, picker) {
		fromDate = picker.startDate.format('DD/MM/YYYY');
		toDate = picker.endDate.format('DD/MM/YYYY');
		//$(".trainingDate").html("( "+customStartDate+" )");
		//getToursDetailsOverview(fromDate,toDate); //default call 
		var designationIds = [];
	    getTourBasicOverviewDtlsDesignationWise(fromDate,toDate,designationIds);
	});
	/* getToursDetailsOverview(fromDate,toDate); //default call 
	function getToursDetailsOverview(fromDate,toDate){ 
	$("#overAllLeaderDivId").html(' ');
	$("#overAllLeaderDivProcessImgId").show();
	  var dates=$("#toursDateRangePickerNew").val();
		var fromDateStr;
		var toDateStr;
		if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			fromDateStr = datesArr[0]; 
			toDateStr = datesArr[1]; 
		}
		var jsObj = { 
			 fromDate : fromDate,
			 toDate : toDate,
			}
		$.ajax({
			type : 'POST',
			url : 'getToursDetailsOverviewForNewAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#overAllLeaderDivProcessImgId").hide();
		 if(result != null && result.length > 0){
			 buildLeadersOverviewRslt(result);
		 }else{
			$("#overAllLeaderDivId").html('NO DATA AVAILABLE.'); 
		 }
		});
	} */
	var designationIds = [];
	getTourBasicOverviewDtlsDesignationWise(fromDate,toDate,designationIds); //default call 
	function getTourBasicOverviewDtlsDesignationWise(fromDate,toDate,designationIds){ 
	$("#overAllLeaderDivId").html(' ');
	$("#overAllLeaderDivProcessImgId").show();
	  var dates=$("#toursDateRangePickerNew").val();
		var fromDateStr;
		var toDateStr;
		if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			fromDateStr = datesArr[0]; 
			toDateStr = datesArr[1]; 
		}
		var jsObj = { 
			 fromDate : fromDate,
			 toDate : toDate,
			 designationIds :designationIds
			}
		$.ajax({
			type : 'POST',
			url : 'getTourBasicOverviewDtlsDesignationWiseAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#overAllLeaderDivProcessImgId").hide();
		 if(result != null && result.length > 0){
			 buildLeadersOverviewRslt(result);
		 }else{
			$("#overAllLeaderDivId").html('NO DATA AVAILABLE.'); 
		 }
		});
	}
	
	function buildLeadersOverviewRslt(result){  
	 var str='';
	 str+='<table class="table table-condensed tableOverview">';
		str+='<thead>';
			str+='<th></th>';
			str+='<th>Total Leaders</th>';
			str+='<th>Submited Leaders</th>';
			str+='<th>Not Submited Leaders</th>';
			str+='<th>Complaince</th>';
			str+='<th>Non-Complaince</th>';
			/* str+='<th>Submited Tours</th>';
			str+='<th>Average Tours</th>'; */
		str+='</thead>';
		str+='<tbody>';
		for(var i in result){
			str+='<tr>';
			str+='<td>';
			str+='<div class="dropup">';
			str+=''+result[i].designation+'<span class="dropdown-toggle" style="font-size: 16px; font-weight: 600; margin-left: 8px;cursor:pointer;" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">&#9432;</span>';
				str+='<div class="dropdown-menu pull-right bg_ED arrow_box_bottom" aria-labelledby="dropdownMenu2" style="padding:10px;">';
					str+='<p><i style="font-size: 17px;">Tours Target Per month</i></p>';
					str+='<table class="table">';
					 for(var j in result[i].subList)
					 {
						 str+='<tr>';
							str+='<td class="bg_ED">'+result[i].subList[j].name+'</td>';
							str+='<td class="bg_ED">'+result[i].subList[j].targetDays+'</td>';
						 str+='</tr>';
					 }
					str+='</table>';
				str+='</div>';
			str+='</div>';
			str+='</td>';
			if(result[i].noOfLeaderCnt != null && result[i].noOfLeaderCnt > 0){
				str+='<td>'+result[i].noOfLeaderCnt+'</td>';
			}else{
			str+='<td> - </td>';
			}
			if(result[i].submitedLeaderCnt != null && result[i].submitedLeaderCnt > 0){
				str+='<td><a style="cursor:pointer;" class="getSubMitedLeadersDtlsCls" attr_desig_name="'+result[i].designation+'" attr_designation_id='+result[i].id+'>'+result[i].submitedLeaderCnt+'</a></td> ';
			}else{
				str+='<td> - </td>';
			}
			if(result[i].notSubmitedLeaserCnt != null && result[i].notSubmitedLeaserCnt > 0){
				str+='<td>'+result[i].notSubmitedLeaserCnt+'</td>';
			}else{
				str+='<td> - </td>';
			}
			if(result[i].complainceCnt != null && result[i].complainceCnt > 0){
				str+='<td>'+result[i].complainceCnt+'&nbsp;&nbsp;&nbsp;<small>'+result[i].complaincePer+'%</small></td>';
			}else{
				str+='<td> - </td>';
			}
			if(result[i].nonComplainceCnt != null && result[i].nonComplainceCnt > 0){				
				//var nonComplinceCount= result[i].submitedLeaderCnt - result[i].complainceCnt ;				
				str+='<td>'+result[i].nonComplainceCnt+'&nbsp;&nbsp;&nbsp;<small>'+result[i].nonComplaincePer+'%</small></td>';
			}else{
				str+='<td> - </td>';
			}
			str+='</tr>';
		}
		str+='</tbody>';
	  str+='</table>';
	  $("#overAllLeaderDivId").html(str);
	}
	$(document).on("click",".getSubMitedLeadersDtlsCls",function(){
		var desigName = $(this).attr("attr_desig_name");
		$("#membersOverviewModalLabel").html(desigName+" OVERVIEW");
		$("#desigDtlsId").html("");
		$("#memDtlsId").html(""); 
		$("#desigDtlsProcessImgId").show();  
		$("#memDtlsProcessImgId").show();   		
		$("#myModal").modal("show");  
		
		var desigId = $(this).attr("attr_designation_id");
		var dates=$("#toursDateRangePickerNew").val();    
		
		var fromDateStr;
		var toDateStr;
		if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			fromDateStr = datesArr[0]; 
			toDateStr = datesArr[1]; 
		}
		getMemberDetailsByDesignationWise(fromDateStr,toDateStr,desigId);
		var designationIds=[];
		designationIds.push(desigId);
		getDesignationWiseOverAllData(fromDateStr,toDateStr,designationIds);
	});
	
	function getMemberDetailsByDesignationWise(fromDateStr,toDateStr,desigId){
		var jsObj = { 
			 desigId : desigId,
			 startDateStr : fromDateStr,
			 endDateStr : toDateStr,
			 candidateId : 0			 
			}
		$.ajax({
			type : 'POST',
			url : 'getMemberDetailsByDesignationWiseAction.action',
			dataType : 'json',      
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			//$("#memDtlsProcessImgId").hide();  
			$("#membersOverviewModal").modal('show');
			if(result != null){
				buildMemberDetailsByDesignationWise(result,desigId);
			}
		});
	}
	function buildMemberDetailsByDesignationWise(result,desigId)
	{
		var str='';
		str+='<table class="table table-bordered tableModal" id="ministersOvwDataTableId">';
			str+='<thead class="text-capital">';
				str+='<tr>';
					str+='<th rowspan="2" class="bg_D8">minister name</th>';
					for(var j in result[0].subList3)
					{
						str+='<th colspan="2" class="bg_D8 text-center">'+result[0].subList3[j].name+'</th>';
					}
					str+='<th rowspan="2" class="bg_D8">Attachment</th>';
				str+='</tr>';
				str+='<tr>';
					for(var i=0;i<result[0].subList3.length;i++)
					{
						str+='<th class="bg_ED text-center">target</th>';
						str+='<th class="bg_ED text-center">toured</th>';
					}
				str+='</tr>';
			str+='</thead>';
			
			for(var i in result)
			{
				str+='<tr>';
					str+='<td>'+result[i].name+'</td>'
					for(var j in result[i].subList3)
					{
						str+='<td class="bg_ED text-center">'+result[i].subList3[j].targetDays+'</td>'
						str+='<td class="bg_ED text-center">'+result[i].subList3[j].complainceDays+'</td>'
					}
					str+='<td>'+result[i].count+' <button class="btn btn-success editBtn editModalBtn btn-xs" attr_designation_id="'+desigId+'" attr_candidateId="'+result[i].id+'" attr_name="'+result[i].name+'">EDIT</button></td>';
				str+='</tr>';
			}

		str+='</table>';
		$("#membersOverviewId").html(str);
		$("#ministersOvwDataTableId").dataTable();
	}
	
	$(document).on("click",".editModalBtn",function(){
		var designationId = $(this).attr("attr_designation_id");
		var dates=$("#toursDateRangePickerNew").val();    
		var name = $(this).attr("attr_name");
		var fromDateStr="",toDateStr="";
		if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			fromDateStr = datesArr[0]; 
			toDateStr = datesArr[1]; 
		}
		
		var jsObj ={ 
			candidateId : $(this).attr("attr_candidateId"),
			designationId : designationId,
			fromDate : fromDateStr ,
			toDate : toDateStr
		}
		
		$.ajax({
			type : 'POST',
			url : 'getCandidateDetailedReportAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#membersOverviewModalEdit").modal('show');
			buildCandidateDetailedReport(result,name,fromDateStr,toDateStr);
		});
	});
	function buildCandidateDetailedReport(result,name,fromDateStr,toDateStr)
	{	$("#membersOverviewModalLabel1").html(name+" - ("+fromDateStr+" to "+toDateStr+")");
		var str='';
		
		if(result.subList != null && result.subList.length > 0){
			if(result.subList[0].subList3 != null && result.subList[0].subList3.length > 0){
				str+='<table class="table bg_ED">';
					str+='<thead>';
						for(var t in result.subList[0].subList3){
							str+='<th>'+result.subList[0].subList3[t].name+'</th>';
						}
						str+='<th>Compliance Ratio</th>';
					str+='</thead>';
					str+='<tbody>';
						str+='<tr>';
						var touredDays=0;targetDays=0;
							for(var t in result.subList[0].subList3){
								touredDays = touredDays+result.subList[0].subList3[t].complainceDays;
								targetDays = targetDays+result.subList[0].subList3[t].targetDays;
								str+='<td>'+result.subList[0].subList3[t].complainceDays+'</td>';
							}
							if(targetDays != null && targetDays > 0){
								var perc = ((touredDays/targetDays)*100).toFixed(2) > 100.00?100.00:((touredDays/targetDays)*100).toFixed(2);
								str+='<td>'+perc+' %</td>';
							}
						str+='</tr>';
					str+='</tbody>';
				str+='</table>';
			}
		}
		
		str+='<table class="table table-bordered tableModal" id="membersOverviewTableId">';
			str+='<thead>';
				str+='<th class="bg_D8">Month & Date</th>';
				str+='<th class="bg_D8">Category</th>';
				str+='<th class="bg_D8">District Name</th>';
				str+='<th class="bg_D8">Type</th>';
			str+='</thead>';
			for(var i in result.subList2)
			{
				str+='<tr>';
					str+='<td>'+result.subList2[i].tourDate+'</td>';
					str+='<td>'+result.subList2[i].tourCategory+'</td>';
					str+='<td>'+result.subList2[i].locationName+'</td>';
					str+='<td style="position:relative">'+result.subList2[i].tourType+'<button class="btn editBtn btn-success btn-xs editTourRecordBtnCls" style="position:absolute;right:20px;" attr_id="'+result.subList2[i].id+'">View/Edit</button></td>';
				str+='</tr>';
			}
			
		str+='</table>';
		$("#membersOverviewModalEditId").html(str);
		$("#membersOverviewTableId").dataTable();
	}

	$(document).on("click",".editTourRecordBtnCls",function(){
		
		 var candidateDayTourId = $(this).attr("attr_id");
		
		var jsObj ={ 
			candidateDayTourId : candidateDayTourId
		}
		
		$.ajax({
			type : 'POST',
			url : 'getNewTourRetrivalDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null){
				buildNewTourRetrivalDetails(result,candidateDayTourId);
				$("#retrivalEditModalId").modal("show");
				initializeFile();
			}
		});
	});
	function buildNewTourRetrivalDetails(result,candidateDayTourId)
	{		
		$("#globalUpdateDayTourId").val(candidateDayTourId);
		
		var str='';
		var temp = result.tourDate.split(' ')[0].split("-");
		var date = temp[2]+"/"+temp[1]+"/"+temp[0];
		
		str+='<div class="row">';
			str+='<div class="col-md-3 col-xs-12 col-sm-3">';
				str+='<label>Tour Date</label>';
				str+='<div class="input-group">';
					str+='<input type="text" class="form-control" id="retriveDateId" name="toursVOList[0].tourDateId"/>';
					str+='<span class="input-group-addon">';
						str+='<i class="glyphicon glyphicon-calendar"></i>';
					str+='</span>';
				str+='</div>';
			str+='</div>';
			str+='<div class="col-md-3 col-xs-12 col-sm-3">';
				str+='<label>Tour Type</label>';
				str+='<select class="form-control" id="retriveTypeId" name="toursVOList[0].tourTypeId">';
					str+='<option value="0">Select Tour Type</option>';
					if(result.tourTypeList != null && result.tourTypeList.length > 0){
						for(var i in result.tourTypeList){
							if(result.tourTypeId == result.tourTypeList[i].id){
								str+='<option value="'+result.tourTypeList[i].id+'" selected>'+result.tourTypeList[i].name+'</option>';
							}else{
								str+='<option value="'+result.tourTypeList[i].id+'">'+result.tourTypeList[i].name+'</option>';
							}							
						}
					}
				str+='</select>';
			str+='</div>';
			str+='<div class="col-md-3 col-xs-12 col-sm-3">';
				str+='<label>Tour Category</label>';
				str+='<select id="retriveCategoryId" class="form-control" name="toursVOList[0].tourCategoryId">';
					str+='<option value="0">Selecct Tour Category</option>';
					if(result.categoryList != null && result.categoryList.length > 0){
						for(var i in result.categoryList){							
							if(result.tourCategoryId == result.categoryList[i].id){
								str+='<option value="'+result.categoryList[i].id+'" selected>'+result.categoryList[i].name+'</option>';
							}else{
								str+='<option value="'+result.categoryList[i].id+'">'+result.categoryList[i].name+'</option>';
							}
						}
					}
					
				str+='</select>';
			str+='</div>';
			str+='<div class="col-md-3 col-xs-12 col-sm-3">';
				str+='<label>Tour Location</label>';
				str+='<select id="retriveLocId" attr_candiLocLevel="'+result.locationScopeId+'" name="toursVOList[0].tourLocationId">';
					str+='<option value="0">Select Tour Location</option>';
						if(regionScopesArr!=null && regionScopesArr.length>0 && result.locationScopeId!=null && result.locationScopeId>0){
							for(var i in regionScopesArr){
								if(regionScopesArr[i].id>=result.locationScopeId)
									if(regionScopesArr[i].id == result.locationScopeId)
										str+="<option value='"+regionScopesArr[i].id+"' selected>"+regionScopesArr[i].name+"</option>";
									else
										str+="<option value='"+regionScopesArr[i].id+"'>"+regionScopesArr[i].name+"</option>";
							}
						}
				str+='</select>';
			str+='</div>';
			str+='<div>';
				str+='<div class="col-md-3 col-xs-12 col-sm-3 locationsDivCls" id="stateDivId" style="display:none;">';
					str+='<label>State</label>';
					str+='<select id="stateSelId" class="form-control" name="toursVOList[0].stateId">';
						str+='<option value="0">Select State</option>';
					str+='</select>';
				str+='</div>';
				str+='<div class="col-md-3 col-xs-12 col-sm-3 locationsDivCls" id="districtDivId" style="display:none;">';
					str+='<label>District</label>';
					str+='<select id="districtSelId" class="form-control" name="toursVOList[0].districtId">';
						str+='<option value="0">Select District</option>';
					str+='</select>';
				str+='</div>';
				str+='<div class="col-md-3 col-xs-12 col-sm-3 locationsDivCls" id="constituencyDivId" style="display:none;">';
					str+='<label>Constituency</label>';
					str+='<select class="form-control" id="constituenctSelId" name="toursVOList[0].constituencyId">';
						str+='<option value="0">Select Constituency</option>';
					str+='</select>';
				str+='</div>';
				str+='<div class="col-md-3 col-xs-12 col-sm-3 locationsDivCls" id="manMunDivId" style="display:none;">';
					str+='<label>Mandal / Municipality</label>';
					str+='<select class="form-control" id="manMunSelId" name="toursVOList[0].localBodyId">';
						str+='<option value="0">Select Mandal / Municipality</option>';
					str+='</select>';
				str+='</div>';
				str+='<div class="col-md-3 col-xs-12 col-sm-3 locationsDivCls" id="villWardDivId" style="display:none;">';
					str+='<label>Village / Ward</label>';
					str+='<select class="form-control" id="villWardSelId" name="toursVOList[0].panchayatWardId">';
						str+='<option value="0">Select Villeage / Ward</option>';
					str+='</select>';
				str+='</div>';
			str+='</div>';
			str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top20">';
				str+='<label>Add Comments/ Tour Description</label>';
				if(result.name == null)
					result.name = "";
				str+='<textarea class="form-control" name="toursVOList[0].description">'+result.name+'</textarea>';
			str+='</div>';
		str+='</div>';
		$("#retriveModalId").html(str);
		$("#retriveCategoryId").chosen();
		$("#retriveLocId").chosen();
		$("#retriveTypeId").chosen();
		$("#retriveDateId").daterangepicker({
			opens: 'right',
			singleDatePicker: true,
			startDate: date,
			locale: {
				format:'DD/MM/YYYY' 
			},
		});
		if(result.locationScopeId!=null && result.locationScopeId>0){
			if(result.locationScopeId == 2){
				$("#stateDivId").show();
			}else if(result.locationScopeId == 3){
				$("#stateDivId,#districtDivId").show();
			}else if(result.locationScopeId == 4){
				$("#stateDivId,#districtDivId,#constituencyDivId").show();
			}else if(result.locationScopeId == 5 || result.locationScopeId == 7){
				$("#stateDivId,#districtDivId,#constituencyDivId,#manMunDivId").show();
			}else if(result.locationScopeId == 6 || result.locationScopeId == 8){
				$("#stateDivId,#districtDivId,#constituencyDivId,#manMunDivId,#villWardDivId").show();
			}
			buildLocations(result);
		}
		$("#retriveModalDocumentDivId").html("");
		if(result.documentList != null && result.documentList.length > 0){
			var strt = "";
			strt+='<table class="table">';
			strt+='<tbody>';
			for(var t in result.documentList){
				strt+='<tr style="border:1px solid #ddd;padding:10px;" id="documentTrId'+result.documentList[t].id+'">';
					var extension = result.documentList[t].name.split(".")[1];
					if(extension == "jpg" || extension == "jpeg")
						strt+='<td class="fa fa-file-photo-o"></td>';
					else if(extension == "pdf")
						strt+='<td class="fa fa-file-pdf-o"></td>';
					else 
						strt+='<td class="fa fa-file-word-o"></td>';
					strt+='<td>'+result.documentList[t].name+'</td>';
					strt+='<td><button type="button" class="viewPdfCls btn btn-success btn-xs" style="background-color:#fff; color:#4CAE4C" attr_doc_name="'+result.documentList[t].name+'">View</button></td>';
					strt+='<td><button type="button" class="deletePdfCls btn btn-danger btn-xs" style="background-color:#fff; color:#F24236" attr_id="'+result.documentList[t].id+'">Delete</button></td>';
				strt+='</tr>';
			}
			strt+='</tbody>';
			strt +='</table>'; 
		}
		$("#retriveModalDocumentDivId").html(strt);
	}
	
	$(document).on("change","#retriveLocId",function(){
		$(".locationsDivCls").hide();
		var candiLocLevel = $(this).attr("attr_candiLocLevel");
		var val = $(this).val();
		if(val == 2){
			$("#stateDivId").show();
		}else if(val == 3){
			$("#stateDivId,#districtDivId").show();
		}else if(val == 4){
			$("#stateDivId,#districtDivId,#constituencyDivId").show();
		}else if(val == 5 || val == 7){
			$("#stateDivId,#districtDivId,#constituencyDivId,#manMunDivId").show();
		}else if(val == 6 || val == 8){
			$("#stateDivId,#districtDivId,#constituencyDivId,#manMunDivId,#villWardDivId").show();
		}
		
		if(candiLocLevel == 2){
			$("#districtSelId").val(0);
			$("#constituenctSelId").html("<option value='0'>Select Constituency</option>");
			$("#manMunSelId").html("<option value='0'>Select Mandal / Municipality</option>");
			$("#villWardSelId").html("<option value='0'>Seelcy Village / Ward</option>");
		}else if(candiLocLevel == 3){
			$("#constituenctSelId").val(0);
			$("#manMunSelId").html("<option value='0'>Select Mandal / Municipality</option>");
			$("#villWardSelId").html("<option value='0'>Seelcy Village / Ward</option>");
		}else if(candiLocLevel == 4){
			$("#manMunSelId").val(0);
			$("#villWardSelId").html("<option value='0'>Seelcy Village / Ward</option>");
		}
	});
	
	function buildLocations(result){
		if(result != null){
			if(result.statesList != null && result.statesList.length > 0){
				$("#stateSelId").html("<option value='0'>Select State</option>");
				for(var i in result.statesList){
					$("#stateSelId").append("<option value='"+result.statesList[i].id+"'>"+result.statesList[i].name+"</option>");
				}
			}
			
			if(result.distList != null && result.distList.length > 0){
				$("#districtSelId").html("<option value='0'>Select District</option>");
				for(var i in result.distList){
					$("#districtSelId").append("<option value='"+result.distList[i].id+"'>"+result.distList[i].name+"</option>");
				}
			}
			
			if(result.constList != null && result.constList.length > 0){
				$("#constituenctSelId").html("<option value='0'>Select Constituency</option>");
				for(var i in result.constList){
					$("#constituenctSelId").append("<option value='"+result.constList[i].id+"'>"+result.constList[i].name+"</option>");
				}
			}
			
			if(result.manTowDivList != null && result.manTowDivList.length > 0){
				$("#manMunSelId").html("<option value='0'>Select Tehsil / Municipality</option>");
				for(var i in result.manTowDivList){
					$("#manMunSelId").append("<option value='"+result.manTowDivList[i].id+"'>"+result.manTowDivList[i].name+"</option>");
				}
			}
			
			if(result.panWardList != null && result.panWardList.length > 0){
				$("#villWardSelId").html("<option value='0'>Select Village / Ward</option>");
				for(var i in result.panWardList){
					$("#villWardSelId").append("<option value='"+result.panWardList[i].id+"'>"+result.panWardList[i].name+"</option>");
				}
			}
			
			if(result.stateId != null && result.stateId > 0 && result.locationScopeId == 2){
				$("#stateSelId").val(result.stateId);
			}else if(result.districtId != null && result.districtId > 0 && result.locationScopeId == 3){
				$("#stateSelId").val(result.stateId);
				$("#districtSelId").val(result.districtId);
			}else if(result.constituencyId != null && result.constituencyId > 0 && result.locationScopeId == 4){
				$("#stateSelId").val(result.stateId);
				$("#districtSelId").val(result.districtId);
				$("#constituenctSelId").val(result.constituencyId);
			}else if((result.locationScopeId == 5 || result.locationScopeId == 7) && result.tehsilId != null && result.tehsilId > 0){
				$("#stateSelId").val(result.stateId);
				$("#districtSelId").val(result.districtId);
				$("#constituenctSelId").val(result.constituencyId);
				if(result.locationScopeId == 5)
					$("#manMunSelId").val(result.tehsilId);
				if(result.locationScopeId == 7)
					$("#manMunSelId").val(result.localElectionBodyId);
			}else if((result.locationScopeId == 6 || result.locationScopeId == 8) && result.panchayatId != null && result.panchayatId > 0){
				$("#stateSelId").val(result.stateId);
				$("#districtSelId").val(result.districtId);
				$("#constituenctSelId").val(result.constituencyId);
				if(result.locationScopeId == 6){
					$("#manMunSelId").val(result.tehsilId);
					$("#villWardSelId").val(result.panchayatId);
				}	
				if(result.locationScopeId == 8){
					$("#manMunSelId").val(result.localElectionBodyId);
					$("#villWardSelId").val(result.wardId);
				}
			}
		}
	}
	
	$(document).on("change","#constituenctSelId",function(){	
		$("#manMunSelId").html("<option value='0'>Select Mandal / Municipality</option>");
		$("#villWardSelId").html("<option value='0'>Select Village / Ward</option>");
		var jsObj = {     
			constituencyId :$(this).val()
		}
		$.ajax({
		  type : 'POST',
		  url : 'getAllMunicipalitiesAction.action',
		  dataType : 'json',
		  data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			
			if(result != null && result.length > 0){
				for(var i in result){
					$("#manMunSelId").append("<option value="+result[i].locationId+">"+result[i].locationName+"</option>");  
				}
			}
		});
	});
	$(document).on("change","#manMunSelId",function(){
		$("#villWardSelId").html("<option value='0'>Select Village / Ward</option>");
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
				for(var i in result){
					$("#villWardSelId").append("<option value="+result[i].locationId+">"+result[i].locationName+"</option>");
				}
			}
		});
	});
	
	$(document).on("click",".viewPdfCls",function(){
		window.open(wurl+"/tour_documents/"+$(this).attr("attr_doc_name"));
	});
	
	function getDesignationWiseOverAllData(fromDate,toDate,designationIds){
		var jsObj = { 
			 fromDate : fromDate,
			 toDate : toDate,
			 designationIds :designationIds
			}
		$.ajax({
			type : 'POST',
			url : 'getTourBasicOverviewDtlsDesignationWiseAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result !=null && result.length>0){
				buildDesignationWiseOverAllData(result);
			}
		});
	}
	function buildDesignationWiseOverAllData(result)
	{
		var str='';
		str+='<div style="padding:10px" class="bg_ED">';
		str+='<table class="table bg_ED">';
			for(var i in result)
			{
				str+='<tr>';
					str+='<td><p class="text-muted">Total Leaders</p><h4 class="panel-title">'+result[i].noOfLeaderCnt+'</h4></td>';
					str+='<td><p class="text-muted">Submited</p><h4 class="panel-title">'+result[i].submitedLeaderCnt+'</h4></td>';
					str+='<td><p class="text-muted">Not Submited</p><h4 class="panel-title">'+result[i].notSubmitedLeaserCnt+'</h4></td>';
					if(result[i].complainceCnt !=null && result[i].complainceCnt >0){
						str+='<td><p class="text-muted">Compliance</p><h4 class="panel-title">'+result[i].complainceCnt+'&nbsp;&nbsp;&nbsp;<small>'+result[i].complaincePer+'%</small></h4></td>';
					}else{
						str+='<td><p class="text-muted">Compliance</p><h4 class="panel-title"> - </h4></td>';
					}
					
					if(result[i].nonComplainceCnt !=null && result[i].nonComplainceCnt>0){
						str+='<td><p class="text-muted">Non Compliance</p><h4 class="panel-title">'+result[i].nonComplainceCnt+'&nbsp;&nbsp;&nbsp;<small>'+result[i].nonComplaincePer+'%</small></h4></td>';						
					}else{
						str+='<td><p class="text-muted">Non Compliance</p><h4 class="panel-title"> - </h4></td>';
					}
					
					
				str+='</tr>';
			}
			
		str+='</table>';
		str+='</div>';
		$("#membersOvrvwId").html(str);
	}
	
	function updateApplication(){

		/*$(".textErrCls").html("");
		$("#errFileId").html(""); */
		var flag = true;		
		var filerKit = $("#update_TourFileId").prop("jFiler");

		/* $(".isActive").each(function(){
			var value = $(this).val();
			var bool = $.isNumeric(value);
			if(bool == false){
				var errId = $(this).attr("attr_err_id");
				$("#"+errId).html("Please Enter Numbers.");
				flag = false;
				return false;      
			}  
		}); */
		
		var childEleCount = $(".jFiler-items-list").children().length;
		
		//allocating designationId To Hidden Variable
		
		
		if(flag == false){
			return;  
		}
		
		var errStr='',flag1=true;
		/* $(".outerDivCls").each(function(){
			if(flag1){
				var count = $(this).attr("attr_count");
				if($("#tourTypeId"+count).val() == 0 || $("#tourTypeId"+count).val() == "undefined" || $("#tourTypeId"+count).val() === undefined){
					errStr="Please Select Tour Type";flag1=false;
				}else if($("#tourCategoryId"+count).val() == 0 || $("#tourCategoryId"+count).val() == "undefined" || $("#tourCategoryId"+count).val() === undefined){
					errStr="Please Select Tour Category";flag1=false;
				}else if($("#tourLocationId"+count).val() > 0){
					var locLvl = $("#tourLocationId"+count).val();
					if(locLvl == 2){
						if($("#stateSelId"+count).val() == 0){
							errStr="Please Select State";flag1=false;
						}
					}else if(locLvl == 3){
						if($("#stateSelId"+count).val() == 0){
							errStr="Please Select State";flag1=false;
						}else if($("#districtSelId"+count).val() == 0){
							errStr="Please Select District";flag1=false;
						} 
					}else if(locLvl == 4){
						if($("#stateSelId"+count).val() == 0){
							errStr="Please Select State";flag1=false;
						}else if($("#districtSelId"+count).val() == 0){
							errStr="Please Select District";flag1=false;
						}else if($("#constituencySelId"+count).val() == 0){
							errStr="Please Select Constituency";flag1=false;
						}
					}else if(locLvl == 5){
						if($("#stateSelId"+count).val() == 0){
							errStr="Please Select State";flag1=false;
						}else if($("#districtSelId"+count).val() == 0){
							errStr="Please Select District";flag1=false;
						}else if($("#constituencySelId"+count).val() == 0){
							errStr="Please Select Constituency";flag1=false;
						}else if($("#tehMunSelId"+count).val() == 0){
							errStr="Please Select Mandal / Municipality";flag1=false;
						}
					}else if(locLvl == 6){
						if($("#stateSelId"+count).val() == 0){
							errStr="Please Select State";flag1=false;
						}else if($("#districtSelId"+count).val() == 0){
							errStr="Please Select District";flag1=false;
						}else if($("#constituencySelId"+count).val() == 0){
							errStr="Please Select Constituency";flag1=false;
						}else if($("#tehMunSelId"+count).val() == 0){
							errStr="Please Select Mandal / Municipality";flag1=false;
						}else if($("#villWardSelId"+count).val()==0){
							errStr="Please Select Village / Ward";flag1=false;
						}
					}
				}
			}
		}); */
		
		if(!flag1){
			alert(errStr);
			return;
		}
	
		var uploadHandler = { 
			upload: function(o) {
				$("#savingAjaxImg").css("display","none");
				uploadResult = o.responseText;
				showSbmitStatusUpdateNew(uploadResult);
			}
		};
		YAHOO.util.Connect.setForm('submitUpdateApplication',true);  
		YAHOO.util.Connect.asyncRequest('POST','saveNewTourDetailsAction.action',uploadHandler);
	}
	function showSbmitStatusUpdateNew(uploadResult){
		if(uploadResult !=null && uploadResult.search("success") != -1){
			$("#successUpdateSpanId").show();
			$("#update_TourFileId").val('');			
			var filerKit = $("#update_TourFileId").prop("jFiler");
			setTimeout(function () {
				$("#successUpdateSpanId").html("<center style='color: green; font-size: 16px;'>Updated Successfully</center>").fadeOut(3000);
				//location.reload(true);
				filerKit.reset();
				$("#retrivalEditModalId").hide();
			}, 500);
		}
	}	
	
	$(document).on("click",".deletePdfCls",function(){
		var id = $(this).attr("attr_id");
		var arr=[id];
		
		var dt = confirm(" Are you sure want to delete Attatchment ? ")
		if(dt == false){
			return;
		}
		
		var jsObj = {     
			documents : arr
		}
		$.ajax({
			type : 'POST',
			url : 'deleteDocumentByDocumentAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result == "success"){
				$("#documentTrId"+id).remove();
			}
		});
	});