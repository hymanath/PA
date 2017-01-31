var regionScopesArr = [{"id":"2","name":"State"},{"id":"3","name":"District"},{"id":"4","name":"Constituency"},{"id":"5","name":"Mandal / Municipality"},{"id":"6","name":"Village / Ward"}];

function getCandidateList(designationId){
	$("#overallDivId").hide();	
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
			$("#memberSlctBxId").append("  <option value='0'>SELECT NAME</option>");
			if(result != null && result.length > 0){
				for(var i in result){
					$("#memberSlctBxId").append("<option value="+result[i].id+">"+result[i].name.toUpperCase()+"</option>");  
				}
				// $("#memberSlctBxId").append("<option value='-1'>Other Name</option>");  
			}
			$("#memberSlctBxId").trigger("chosen:updated");
		});
	}
	$(document).on("change","#designationSlctBxId",function(){
		var designationId = $(this).val();
		$("#selectedMemberDtslDivId").html(' ');
		$(".showDivClsNew").hide();
		 if(designationId != null){
			 getCandidateList(designationId);
		 }
	});
	$(document).on("change","#memberSlctBxId",function(){
		var candidateId = $(this).val();
		 if(candidateId != null){
			//$(".otherMemberBlockCls").hide();
			//$(".showDivCls").show();
			//$(".hideProfileDivCls").show();
			
			$(".ownDivCls").hide();
			$(".inchageDivCls").hide();
			
			if(candidateId==0){
				$("#selectedMemberDtslDivId").html(' ');
				return;
			}
			
			
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
	$(".showDivClsNew").hide();
	$("#overallDivId").hide();
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
			$("#globalUpdateHiddentdpCadreId").val(result.tdpCadreId); 
		}
		
		
		var str='';
		str+='<h4 class="panel-title text-capital ">selected profile</h4>';
			str+='<ul class="list-inline m_top20" style="padding:5px;border:thin solid lightgray;border-radius:5px;width:1100px;">';
			str+='<li>';
				str+='<div class="panel panel-default panelProfile">';
					str+='<div class="panel-body">';
						str+='<img src="http://mytdp.com/images/cadre_images/'+result.image+'" onerror="setDefaultImage(this);" style="width: 50px; height: 50px;margin-left: 25px;"></img>';
						str+='<p>'+result.name+'</p>';
						str+='<p title="Voter Card No "> VID : '+result.voterCardNumber+'</p>';
						str+='<p title="Membership No">MID : '+result.memberShipNo+'</p>';
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
				$("#designationSlctBxId").append("<option value='0'>SELECT DESIGNATION LEVEL</option>");
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
			//$("#tourCategoryId0").empty();
			//$("#tourCategoryId0").append("  <option value='0'>Select Category</option>");
			
			$("#tourCategoryNew0").empty();
			$("#tourCategoryNew0").append("  <option value='0'>Select Category</option>");
			
			if(result != null && result.length > 0){
				TourCategoryArray =result;
				for(var i in result){
					//$("#tourCategoryId0").append("<option value="+result[i].id+">"+result[i].name+"</option>");  
					$("#tourCategoryNew0").append("<option value="+result[i].id+">"+result[i].name+"</option>");  
				}
			}
			//$("#tourCategoryId0").chosen();
			//$("#tourCategoryId0").trigger("chosen:updated");
			
			$("#tourCategoryNew0").chosen();
			$("#tourCategoryNew0").trigger("chosen:updated");
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
			//$("#tourTypeId0").empty();
			$("#tourTypeNew0").empty();
			//$("#tourTypeId0").append("  <option value='0'>Select TourTypes</option>");
			$("#tourTypeNew0").append("  <option value='0'>Select TourTypes</option>");
			if(result != null && result.length > 0){
				TourTypesArray=result;
				for(var i in result){
					//$("#tourTypeId0").append("<option value="+result[i].id+">"+result[i].name+"</option>");  
					$("#tourTypeNew0").append("<option value="+result[i].id+">"+result[i].name+"</option>");  
				}
			}
			//$("#tourTypeId0").chosen();
			//$("#tourTypeId0").trigger("chosen:updated");
			
			$("#tourTypeNew0").chosen();
			$("#tourTypeNew0").trigger("chosen:updated");
			
		});
	}
	function savingApplication(){

		/* $(".textErrCls").html("");
		$("#errFileId").html(""); */
		var flag = true;		
		/* var monthValue = $("#monthSelectBoxId").val(); */
		/* if(monthValue == 0){
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
		}); */
		
		//allocating designationId To Hidden Variable
		
		var desigId=$("#designationSlctBxId option:selected").val();
		if(desigId !=null){
			$("#globalHiddenDesignationId").val(desigId);
			$("#globalUpdateHiddenDesignationId").val(desigId);
		}
		if(flag == false){
			return;  
		}
		var errStr='',flag1=true;docErr=true;
		
		if(!($("#profileCheckboxId").is(':checked'))){
			errStr = "Please Select Candidate.";
			flag1=false;					
		}else if($("#tourMonthYear").val() == null || $("#tourMonthYear").val().length == 0 || $("#tourMonthYear").val() == undefined || $("#tourMonthYear").val().length == undefined){
			errStr="Please Select Month";flag1=false;
		}
		
		$(".outerDivClsNew").each(function(){
			if(flag1){
				var count = $(this).attr("attr_countNew");
				//alert($("#tourDaysNew"+count).val());
				if($("#tourCategoryNew"+count).val() == 0 || $("#tourCategoryNew"+count).val() == "undefined" || $("#tourCategoryNew"+count).val() === undefined){
					errStr="Please Select Tour Category";flag1=false;
					
				}else if($("#tourTypeNew"+count).val() == 0 || $("#tourTypeNew"+count).val() == "undefined" || $("#tourTypeNew"+count).val() === undefined){
					errStr="Please Select Tour Type";flag1=false;
				}else if($("#tourDaysNew"+count).val() == null || $("#tourDaysNew"+count).val() == undefined || $("#tourDaysNew"+count).val() == "undefined" || $("#tourDaysNew"+count).val().length == 0 || $("#tourDaysNew"+count).val() == 0){
					errStr="Please Enter Tour Days";flag1=false;
				}
			}
		});
		
		$(".outerDivCls").each(function(){
			if(flag1){
				var count = $(this).attr("attr_count");
				if($("#tourTypeId"+count).val() == 0 || $("#tourTypeId"+count).val() == "undefined" || $("#tourTypeId"+count).val() === undefined){
					errStr="Please Select Tour Type";flag1=false;
				}else if($("#tourCategoryId"+count).val() == 0 || $("#tourCategoryId"+count).val() == "undefined" || $("#tourCategoryId"+count).val() === undefined){
					errStr="Please Select Tour Category";flag1=false;
				}/*else if($("#tourLocationId"+count).val() > 0){
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
				}*/
			}
		});
		
		var childEleCount = $(".jFiler-items-list").children().length;
		var filerKit = $("#update_TourFileId2").prop("jFiler");
		
		if(childEleCount > 1){
			errStr = "Please Select Only One Document."; 
			docErr=false; 			
			flag1=false;      			
		}
		
		
		if(!flag1){
			alert(errStr);
			if(!docErr){
				filerKit.reset();  
			}
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
		$("#addNewTourBlock").html("");
		if($(this).is(':checked')){
			$("#overallDivId").show();
			$("#toursNewBlockClonedId").html("");
			$(".tourDescNewCls").val("");
			$("#tourMonthYear").val("");
			var desigDtlsId = $("#designationSlctBxId").val();
			var tdpCadreId = $(this).attr("attr_cadre_id");
			$("#hiddenTdpCadreId").val(tdpCadreId);
			//getAllTourCategorys($(this).attr("attr_cadre_id"),desigDtlsId);
		}else{
			$("#overallDivId").hide();
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
		$("#toursNewBlockClonedId").html("");
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
		str+='<thead class="text-center">';
			str+='<th></th>';
			str+='<th class="text-center">Total Leaders</th>';
			str+='<th class="text-center">Submited Leaders</th>';
			str+='<th class="text-center">Not Submited Leaders</th>';
			str+='<th class="text-center">Complaince</th>';
			str+='<th class="text-center">Non-Complaince</th>'; 
		str+='</thead>';
		str+='<tbody>';
		for(var i in result){
			str+='<tr>';
			str+='<td>';
			str+='<div class="dropup">';
			str+=''+result[i].designation+'<span class="dropdown-toggle" style="font-size: 16px; font-weight: 600; margin-left: 8px;cursor:pointer;" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">&#9432;</span>';
				str+='<div class="dropdown-menu pull-right bg_ED arrow_box_bottom" aria-labelledby="dropdownMenu2" style="padding:10px;">';
					str+='<p><i style="font-size: 17px;">Tour Target Days Per month</i></p>';
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
				str+='<td class="text-center">'+result[i].noOfLeaderCnt+'</td>';
			}else{
			str+='<td class="text-center"> - </td>';
			}
			if(result[i].submitedLeaderCnt != null && result[i].submitedLeaderCnt > 0){
				str+='<td class="text-center"><a style="cursor:pointer;" class="getSubMitedLeadersDtlsCls" attr_desig_name="'+result[i].designation+'" attr_designation_id='+result[i].id+'>'+result[i].submitedLeaderCnt+'</a></td> ';
			}else{
				str+='<td class="text-center"> - </td>';
			}
			if(result[i].notSubmitedLeaserCnt != null && result[i].notSubmitedLeaserCnt > 0){
				str+='<td class="text-center">'+result[i].notSubmitedLeaserCnt+'</td>';
			}else{
				str+='<td class="text-center"> - </td>';
			}
		   if(result[i].complainceCnt != null && result[i].complainceCnt > 0){
				str+='<td class="text-center">'+result[i].complainceCnt+'&nbsp;&nbsp;&nbsp;<small>'+result[i].complaincePer+'%</small></td>';
			}else{
				str+='<td class="text-center"> - </td>';
			}
			if(result[i].nonComplainceCnt != null && result[i].nonComplainceCnt > 0){				
				str+='<td class="text-center">'+result[i].nonComplainceCnt+'&nbsp;&nbsp;&nbsp;<small>'+result[i].nonComplaincePer+'%</small></td>';
			}else{
				str+='<td class="text-center"> - </td>';
			} 
			str+='</tr>';
		}
		str+='</tbody>';
	  str+='</table>';
	  $("#overAllLeaderDivId").html(str);
	}
	$(document).on("click",".getSubMitedLeadersDtlsCls",function(){
		//$("#membersOverviewModal").modal('show');
		$('#membersOverviewModal').modal({
            show: true,
            keyboard: false,
            backdrop: 'static'
        });
		
		var desigName = $(this).attr("attr_desig_name");
		$("#membersOverviewModalLabel").html(desigName+" OVERVIEW");
		$("#desigDtlsId").html("");
		$("#memDtlsId").html(""); 
		$("#desigDtlsProcessImgId").show();  
		$("#memDtlsProcessImgId").show();   		
		//$("#myModal").modal("show");  
		
		$('#myModal').modal({
            show: true,
            keyboard: false,
            backdrop: 'static'
        });
		
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
		$("#membersOverviewId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
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
					str+='<td style="position:relative" class="text-center">'+result[i].count+' <button class="btn btn-success editBtn editModalBtn btn-xs" attr_designation_id="'+desigId+'" attr_candidateId="'+result[i].id+'" attr_name="'+result[i].name+'" style="position:absolute;right:20px;">EDIT</button></td>';
				str+='</tr>';
			}

		str+='</table>';
		$("#membersOverviewId").html(str);
		$("#ministersOvwDataTableId").dataTable();
	}
	
	$(document).on("click",".editModalBtn",function(){
		//$("#membersOverviewModalEdit").modal('show');
		
		$('#membersOverviewModalEdit').modal({
            show: true,
            keyboard: false,
            backdrop: 'static'
        });
		
		
		var designationId = $(this).attr("attr_designation_id");
		var dates=$("#toursDateRangePickerNew").val();    
		var name = $(this).attr("attr_name");
		var fromDateStr="",toDateStr="";
		if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			fromDateStr = datesArr[0]; 
			toDateStr = datesArr[1]; 
		}
		$("#membersOverviewModalEditId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
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
			buildCandidateDetailedReport(result,name,fromDateStr,toDateStr);
		});
	});
	function buildCandidateDetailedReport(result,name,fromDateStr,toDateStr)
	{	$("#membersOverviewModalLabel1").html(name+" - ("+fromDateStr+" to "+toDateStr+")");
	
		//hidden variables For updation
		if(result.subList2 !=null && result.subList2.length>0){
			$("#globalUpdateHiddenDesignationId").val(result.subList2[0].designationId);
			$("#globalUpdateHiddentdpCadreId").val(result.subList2[0].candDtlsId);
		}
		
		var str='';
		
		if(result.subList != null && result.subList.length > 0){
			if(result.subList[0].subList3 != null && result.subList[0].subList3.length > 0){
				str+='<table class="table bg_ED">';
					str+='<thead>';
						for(var t in result.subList[0].subList3){
							str+='<th>'+result.subList[0].subList3[t].name+'</th>';
						}
						/* str+='<th>Compliance Ratio</th>'; */
					str+='</thead>';
					str+='<tbody>';
						str+='<tr>';
						var totalPerc=0.00;
							for(var t in result.subList[0].subList3){
								var touredDays = result.subList[0].subList3[t].complainceDays;
								var targetDays = result.subList[0].subList3[t].targetDays;
								str+='<td>'+result.subList[0].subList3[t].complainceDays+'</td>';															
								if(targetDays != null && targetDays > 0){
									var perc = ((touredDays/targetDays)*100).toFixed(2) > 100.00?100.00:((touredDays/targetDays)*100).toFixed(2);		
									if(perc !=null && perc>0.0){
										totalPerc = totalPerc + perc;
									}
									
								}
							}
							/* if(totalPerc !=null && totalPerc>0.0){
								str+='<td>'+totalPerc/result.subList[0].subList3.length+' %</td>';
							}else{
								str+='<td> - </td>';
							} */
							
						str+='</tr>';
					str+='</tbody>';
				str+='</table>';
			}
		}
		
		str+='<table class="table table-bordered tableModal" id="membersOverviewTableId">';
			str+='<thead>';
				str+='<th class="bg_D8">Month & Year</th>';
				str+='<th class="bg_D8">Category</th>';
				
				str+='<th class="bg_D8">Type</th>';
				str+='<th class="bg_D8">Toured Days</th>';
			str+='</thead>';
			for(var i in result.subList2)
			{
				str+='<tr>';
					str+='<td>'+result.subList2[i].tourDate+'</td>';
					str+='<td>'+result.subList2[i].tourCategory+'</td>';					
					str+='<td style="position:relative">'+result.subList2[i].tourType+'</td>';
					str+='<td>'+result.subList2[i].count+'<button class="btn editBtn btn-success btn-xs editTourRecordBtnCls" style="position:absolute;right:20px;" attr_id="'+result.subList2[i].id+'">View/Edit</button></td>';
				str+='</tr>';
			}
			
		str+='</table>';
		$("#membersOverviewModalEditId").html(str);
		$("#membersOverviewTableId").dataTable();
	}

	$(document).on("click",".editTourRecordBtnCls",function(){
		//$("#retrivalEditModalId").modal("show");
		$('#retrivalEditModalId').modal({
            show: true,
            keyboard: false,
            backdrop: 'static'
        });
		$("#retriveModalId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var detailsNewId = $(this).attr("attr_id");//detailsNewId
		
		var jsObj ={ 
			candidateDayTourId : detailsNewId
		}
		
		$.ajax({
			type : 'POST',
			url : 'getNewTourRetrivalDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null){
				//buildNewTourRetrivalDetails(result,candidateDayTourId);
				buildNewTourRetrivalDetailsForNew(result,detailsNewId);
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
						strt+='<td><i  class="fa fa-file-photo-o" style="font-size:20px"></i></td>';
					else if(extension == "pdf")
						strt+='<td><i class="fa fa-file-pdf-o" style="font-size:20px;color:red"></i></td>';
					else 
						strt+='<td><i class="fa fa-file-word-o" style="font-size:20px;color:blue"></i></td>';
					strt+='<td>'+result.documentList[t].name+'</td>';
					
					if(result.documentList[t].name != null && result.documentList[t].name.length > 0){
							var fullName = result.documentList[t].name;
							var nameArr = fullName.split(".");
							var type = nameArr[1];
							if(type=="pdf" || type=="PDF"){
								strt+='<td id="showPdfId" attr_filePath="'+result.documentList[t].name+'" style="cursor:pointer;" class="viewPdfCls"><span><img src="images/pdf.jpg" class="media-object" alt="" style="width:20px;"/></span></td>';
							}else if(type=="xls" ||type=="xlsx"){  
								strt+='<td id="showPdfId" attr_filePath="'+result.documentList[t].name+'" style="cursor:pointer;" class="viewPdfCls"><span><img src="images/excel.jpg" class="media-object" alt="" style="width:20px;"/></span></td>';       
							}else if(type=="doc" || type=="docx"){
								strt+='<td id="showPdfId" attr_filePath="'+result.documentList[t].name+'" style="cursor:pointer;" class="viewPdfCls"><span><img src="images/word.jpg" class="media-object" alt="" style="width:20px;"/></span></td>';         
							}else if(type != null){  
								strt+='<td id="showPdfId" attr_filePath="'+result.documentList[t].name+'" style="cursor:pointer;" class="viewPdfCls"><span><img src="images/fileImage.png" class="media-object" alt="" style="width:20px;"/></span></td>';         
							}           
					}else{    
						strt+='<td> - </td>';  
					}
					
					/* strt+='<td><button type="button" class="viewPdfCls btn btn-success btn-xs" style="background-color:#fff; color:#4CAE4C" attr_doc_name="'+result.documentList[t].name+'">View</button></td>'; */
					
					
					
					strt+='<td><button type="button" class="deletePdfCls btn btn-danger btn-xs" style="background-color:#fff; color:#F24236" attr_id="'+result.documentList[t].id+'">Delete</button></td>';
				strt+='</tr>';
			}
			strt+='</tbody>';
			strt +='</table>'; 
		}
		$("#retriveModalDocumentDivId").html(strt);
		
		var str1="";
		str1+='<div class="col-md-12 col-xs-12 col-sm-12 m_top20">';
			str1+='<h3 class="m_0 text-success font_weight" style="margin-left:425px;">UPLOAD SCAN COPY</h3>  ';
			str1+='<input type="file" id="update_TourFileId" multiple="multiple"  name="files[]" class="m_top20"/>';
			str1+='<span id="errFileId" style="color:red;margin-left:470px;"></span>  '; 
		str1+='</div>';
		$("#uploadUpdateFlDivId").html(str1);
		initializeFile();
	}
	
	function buildNewTourRetrivalDetailsForNew(result,detailsNewId)
	{		
		//latest
		$("#globalUpdateDayTourId").val(detailsNewId);
	
		var str='';
		/* var temp = result.tourDate.split(' ')[0].split("-");
		var date = temp[2]+"/"+temp[1]+"/"+temp[0]; */
		
		str+='<div class="row">';
			str+='<div class="col-md-3 col-xs-12 col-sm-3">';
				str+='<label>Tour Date</label>';
				str+='<div class="input-group">';
					str+='<input type="text" class="form-control" id="retriveDateId" name="toursVO.tourMonth" value='+result.tourDate+'/>';
					str+='<span class="input-group-addon">';
						str+='<i class="glyphicon glyphicon-calendar"></i>';
					str+='</span>';
				str+='</div>';
			str+='</div>';
			
			str+='<div class="col-md-3 col-xs-12 col-sm-3">';
				str+='<label>Tour Category</label>';
				str+='<select id="retriveCategoryId" class="form-control" name="toursVOListNew[0].tourCategoryId">';
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
				str+='<label>Tour Type</label>';
				str+='<select class="form-control" id="retriveTypeId" name="toursVOListNew[0].tourTypeId">';
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
			
			str+='<div class="col-md-3 col-xs-12 col-sm-2">';
				str+='<label>Tour Days</label>';
				str+='<input type="text" class="form-control" id="retrieveTourDaysId" name="toursVOListNew[0].tourDays" value='+result.count+' />';
			str+='</div>';
			
			str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top20">';
				str+='<label>Add Comments/ Tour Description</label>';
				if(result.name == null)
					result.name = "";
				str+='<textarea class="form-control" name="toursVOListNew[0].description">'+result.name+'</textarea>';
			str+='</div>';
			

		$("#retriveModalId").html(str);
		$("#retriveCategoryId").chosen();
		$("#retriveTypeId").chosen();
		$("#retriveDateId").datetimepicker({
			format:'MM-YYYY'
		});
		
		$("#retriveModalDocumentDivId").html("");
		if(result.documentList != null && result.documentList.length > 0){
			var strt = "";
			strt+='<table class="table">';
			strt+='<tbody>';
			for(var t in result.documentList){
				strt+='<tr style="border:1px solid #ddd;padding:10px;" id="documentTrId'+result.documentList[t].id+'">';
					var extension = result.documentList[t].name.split(".")[1];
					if(extension == "jpg" || extension == "jpeg")
						strt+='<td><i  class="fa fa-file-photo-o" style="font-size:20px"></i></td>';
					else if(extension == "pdf")
						strt+='<td><i class="fa fa-file-pdf-o" style="font-size:20px;color:red"></i></td>';
					else 
						strt+='<td><i class="fa fa-file-word-o" style="font-size:20px;color:blue"></i></td>';
					strt+='<td>'+result.documentList[t].name+'</td>';
					
					
					if(result.documentList[t].name != null && result.documentList[t].name.length > 0){
						
							var fullName = result.documentList[t].name;
							var nameArr = fullName.split(".");
							var type = nameArr[1];
							
							if(type=="pdf" || type=="PDF"){
								
								strt+='<td id="showPdfId" attr_filePath="'+result.documentList[t].name+'" style="cursor:pointer;" class="viewPdfCls"><span><img src="images/pdf.jpg" class="media-object" alt="" style="width:30px;"/></span></td>';
							}else if(type=="xls" ||type=="xlsx"){  
								strt+='<td id="showPdfId" attr_filePath="'+result.documentList[t].name+'" style="cursor:pointer;" class="viewPdfCls"><span><img src="images/excel.jpg" class="media-object" alt="" style="width:30px;"/></span></td>';       
							}else if(type=="doc" || type=="docx"){
								strt+='<td id="showPdfId" attr_filePath="'+result.documentList[t].name+'" style="cursor:pointer;" class="viewPdfCls"><span><img src="images/word.jpg" class="media-object" alt="" style="width:30px;"/></span></td>';         
							}else if(type != null){  
								strt+='<td id="showPdfId" attr_filePath="'+result.documentList[t].name+'" style="cursor:pointer;" class="viewPdfCls"><span><img src="images/fileImage.png" class="media-object" alt="" style="width:30px;"/></span></td>';         
							}           
					}else{    
						strt+='<td> - </td>';  
					}
					
					/* strt+='<td><button type="button" class="viewPdfCls btn btn-success btn-xs" style="background-color:#fff; color:#4CAE4C" attr_doc_name="'+result.documentList[t].name+'">View</button></td>'; */
					
					
					strt+='<td><button type="button" class="deletePdfCls btn btn-danger btn-xs" style="background-color:#fff; color:#F24236" attr_id="'+result.documentList[t].id+'">Delete</button></td>';
				strt+='</tr>';
			}
			strt+='</tbody>';
			strt +='</table>'; 
		}
		$("#retriveModalDocumentDivId").html(strt);
		
		var str1="";
		str1+='<div class="col-md-12 col-xs-12 col-sm-12 m_top20">';
			str1+='<h3 class="m_0 text-success font_weight" style="margin-left:425px;">UPLOAD SCAN COPY</h3>  ';
			str1+='<input type="file" id="update_TourFileId" multiple="multiple"  name="files[]" class="m_top20"/>';
			str1+='<span id="errFileId" style="color:red;margin-left:470px;"></span>  '; 
		str1+='</div>';
		$("#uploadUpdateFlDivId").html(str1);
		initializeFile();
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
	
	/* $(document).on("click",".viewPdfCls",function(){
		window.open(wurl+"/tour_documents/"+$(this).attr("attr_doc_name"));
	}); */
	
	function getDesignationWiseOverAllData(fromDate,toDate,designationIds){
		$("#membersOvrvwId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
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
		var filerKit = $("#update_TourFileId").prop("jFiler");
		var childEleCount = $(".jFiler-items-list").children().length;
		
		if($("#retriveDateId").val() == null || $("#retriveDateId").val().length == 0 || $("#retriveDateId").val() == undefined || $("#retriveDateId").val().length == undefined){
			alert("Please Select Month ");
			return;
		}
		
		if($("#retriveCategoryId").val() == 0 || $("#retriveCategoryId").val() == "undefined"){
			alert("Please Select Tour Category");
			return;
		}else if($("#retriveTypeId").val() == 0 || $("#retriveTypeId").val() == "undefined"){
			alert("Please Select Tour Type");
			return;
		}else if($("#retrieveTourDaysId").val() == null || $("#retrieveTourDaysId").val() == undefined || $("#retrieveTourDaysId").val() == "undefined" || $("#retrieveTourDaysId").val().length == 0 || $("#retrieveTourDaysId").val() == 0){
			alert("Please Enter Tour Days ");
			return;
		}
		
		
		/*else if($("#retriveLocId").val() > 0){
			var locLvl = $("#retriveLocId").val();
			if(locLvl == 2){
				if($("#stateSelId").val() == 0){
					alert("Please Select State");
					return;
				}
			}else if(locLvl == 3){
				if($("#stateSelId").val() == 0){
					alert("Please Select State");
					return;
				}else if($("#districtSelId").val() == 0){
					alert("Please Select District");
					return;
				} 
			}else if(locLvl == 4){
				if($("#stateSelId").val() == 0){
					alert("Please Select State");
					return;
				}else if($("#districtSelId").val() == 0){
					alert("Please Select District");
					return;
				}else if($("#constituenctSelId").val() == 0){
					alert("Please Select Constituency");
					return;
				}
			}else if(locLvl == 5){
				if($("#stateSelId").val() == 0){
					alert("Please Select State");
					return;
				}else if($("#districtSelId").val() == 0){
					alert("Please Select District");
					return;
				}else if($("#constituenctSelId").val() == 0){
					alert("Please Select Constituency");
					return;
				}else if($("#manMunDivId").val() == 0){
					alert("Please Select Mandal / Municipality");
					return;
				}
			}else if(locLvl == 6){
				if($("#stateSelId").val() == 0){
					alert("Please Select State");
					return;
				}else if($("#districtSelId").val() == 0){
					alert("Please Select District");
					return;
				}else if($("#constituenctSelId").val() == 0){
					alert("Please Select Constituency");
					return;
				}else if($("#manMunDivId").val() == 0){
					alert("Please Select Mandal / Municipality");
					return;
				}else if($("#villWardSelId").val()==0){
					alert("Please Select Village / Ward");
					return;
				}
			}
		} */
		
		var childEleCount = $(".jFiler-items-list").children().length;
		var filerKit = $("#update_TourFileId2").prop("jFiler");
		
		if(childEleCount > 1){
			alert( "Please Select Only One Document."); 
			filerKit.reset();
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
			$("#successUpdateSpanId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
			$("#successUpdateSpanId").show();
			$("#update_TourFileId").val('');			
			var filerKit = $("#update_TourFileId").prop("jFiler");
			setTimeout(function () {
				$("#successUpdateSpanId").html("<center style='color: green; font-size: 16px;'>Updated Successfully</center>").fadeOut(3000);
				filerKit.reset();
				$("#retrivalEditModalId").modal('hide');
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
	
	$(document).on("click",".addNewBlockBtnCls",function(){
		$("#addNewTourBlock").html("");
		if($("#dateWiseBlockId").is(':visible')){
			$("#dateWiseBlockId").hide();
			$(".addNewBlockBtnCls").text("Add Day Wise Tours Block");
			$(".addNewBlockBtnCls").addClass("btn-success").removeClass("btn-danger");
		}else{
			$("#dateWiseBlockId").show();
			$(".addAppendTourBlockCls").trigger("click");//add first block dynamically
			$(".addNewBlockBtnCls").text("Remove Day Wise Tours Block");
			$(".addNewBlockBtnCls").removeClass("btn-success").addClass("btn-danger");
		}
	});
	
	$(document).on('click','.viewPdfCls',function(){
		//$("#cdrModelId").modal("show");
		var dbFilePath = $(this).attr("attr_filePath");         
		var str = ''; 
		var fileNameArr = dbFilePath.split(".");
		var extName = fileNameArr[1];
		if((navigator.userAgent.match(/iPhone/i)) ||  (navigator.userAgent.match(/iPad/i))) {
			$("#tourNewDocumentId").modal("hide");
			window.open('http://mytdp.com/Reports/tour_documents/'+dbFilePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
			//window.open('http://ieee802.org/secmail/docIZSEwEqHFr.doc','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
			//window.open(wurl+'/PartyAnalyst/Reports/tour_documents/'+dbFilePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
		}else{
			
			if(extName.trim()=="pdf" || extName.trim()=="PDF"){
				//$("#tourNewDocumentId").modal("show");
				
				$('#tourNewDocumentId').modal({
					show: true,
					keyboard: false,
					backdrop: 'static'
				});
				
				
				str += '<iframe src="http://mytdp.com/Reports/tour_documents/'+dbFilePath+'" width="100%" height="800">';    
				str += '</iframe>';
			}
			if(extName.trim()=="jpg"){  
				//$("#tourNewDocumentId").modal("show");
				$('#tourNewDocumentId').modal({
					show: true,
					keyboard: false,
					backdrop: 'static'
				});
				
				str += '<iframe src="http://mytdp.com/Reports/tour_documents/'+dbFilePath+'" width="100%" height="800">';    
				str += '</iframe>';
			}              
			if(extName.trim()=="doc" || extName.trim()=="docx"){
				//$("#tourNewDocumentId").modal("show");
				
				$('#tourNewDocumentId').modal({
					show: true,
					keyboard: false,
					backdrop: 'static'
				});
				
				str += '<iframe src="https://docs.google.com/gview?url=http://mytdp.com/Reports/tour_documents/'+dbFilePath+'&embedded=true" frameborder="0" style="width: 100%; height: 500px;">';
				str += '</iframe>';
			}
			if(extName.trim()=="xls" || extName.trim()=="xlsx"){      
				window.open('http://mytdp.com/Reports/tour_documents/'+dbFilePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
			}            
			$("#tourNewDocumentBodyId").html(str);
			//window.open(wurl+'/Reports/tour_documents/'+dbFilePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
			// window.open(wurl+'/PartyAnalyst/Reports/tour_documents/'+dbFilePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
		}      
	});
	$(document).on("keyup","#tourMonthYear",function(){
	getAllTourDetailsOverview();
});
function getAllTourDetailsOverview(){//Teja
	var tourdate = $("#tourMonthYear").val();
	var cadreId = $("#hiddenTdpCadreId").val();
	var jsObj = {     
		tourdate : tourdate,
		tdpCadreId:cadreId
	}
	$.ajax({
		type : 'POST',
		url : 'getSelectedprofileToursOverviewAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		console.log(result);
		buildAllTourDetailsOverview(result)
	});
}
function buildAllTourDetailsOverview(result){
	$("#candidateNameId").html("SELECT PROFILE TOURS OVERVIEW");
	var str = '';
	var str1 = '';
	if(result != null && result.length > 0){
		str+='<table class="table table-bordered">';
		  str+='<thead>';
			str+='<th>Designation</th>';
			str+='<th>Tour Category</th>';
			str+='<th>Type</th>';
			str+='<th>Tours Submitted</th>';
			str+='<th>Updated Date</th>';
		  str+='</thead>';
		  str+='<tbody>';
		  for(var i in result){
				str+='<tr>';
					if(result[i].designation != null){
						str+='<td>'+result[i].designation+'</td>';
					}else{
						str+='<td>-</td>';
					}				  
				  str+='<td>'+result[i].category+'</td>';
				  str+='<td>'+result[i].comment+'</td>';
				  if(result[i].tourDays != null){
					  str+='<td><input type="text" class="form-control" value="'+result[i].tourDays+'"/></td>';
				  }else{
					  str+='<td><input type="text" class="form-control" value="0"/></td>'; 
				  }
				  if(result[i].tourDate != null && result[i].tourDate==""){
					   str+='<td>'+result[i].tourDate+'</td>';
				  }else{
					   str+='<td class="text-center">-</td>';
				  }
				 
				str+='</tr>';
			}
		  str+='</tbody>';
		str+='</table>';
		str1+='<h4 class="panel-title m_top20">Uploaded Attachments</h4>';
		str1+='<div class="m_top10">';
			str1+='<table class="table tableAttachments">';
			for(var j in result[0].toursVoList){
				str1+='<tr>';
					str1+='<td><img src="images/pdf.jpg" class="media-object" style="width:20px;"/></td>';
					str1+='<td>'+result[0].toursVoList[j].filePath+'</td>';
					str1+='<td>UPDATED : '+result[0].toursVoList[j].tourDate+'</td>';
					str1+='<td><button class="btn btnView" type="button">VIEW</button></td>';
					str1+='<td><button class="btn btnDelete" type="button">DELETE</button></td>';
				str1+='</tr>';
			}	
			str1+='</table>';
		str1+='</div>';
	}
	
	$("#toursCandidateDetails").html(str);
	$("#attachementsId").html(str1);
}
function savingApplication(){
		var uploadHandler = { 
			upload: function(o) {
				$("#savingAjaxImg").css("display","none");
				uploadResult = o.responseText;
				showSbmitStatusNew(uploadResult);
			}
		};
		YAHOO.util.Connect.setForm('toursFormApplication',true);  
		//YAHOO.util.Connect.asyncRequest('POST','savingTourDtlsApplicationAction.action',uploadHandler);
		YAHOO.util.Connect.asyncRequest('POST','saveDesignationWiseTourDetailsAction.action',uploadHandler);
	}