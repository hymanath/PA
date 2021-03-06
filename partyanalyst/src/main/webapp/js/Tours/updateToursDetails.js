var regionScopesArr = [{"id":"2","name":"State"},{"id":"3","name":"District"},{"id":"4","name":"Constituency"},{"id":"5","name":"Mandal / Municipality"},{"id":"6","name":"Village / Ward"}];

   /* Saving or updating tour details block start */
	
   //getting designation list
	getDesigationList(); 
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
    //getting candidate list based on designation selection
	$(document).on("change","#designationSlctBxId",function(){
		var designationId = $(this).val();
		$("#selectedMemberDtslDivId").html(' ');
		$(".showDivClsNew").hide();
		 if(designationId != null){
			 getCandidateList(designationId);
		 }
	});
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
			}
			$("#memberSlctBxId").trigger("chosen:updated");
		});
	}
	//getting candidate details based on candidate selection
	$(document).on("change","#memberSlctBxId",function(){
		var candidateId = $(this).val();
		 if(candidateId != null){
			if(candidateId==0){
				$("#selectedMemberDtslDivId").html(' ');
				return;
			}
			 getCandiateSearchDetails(candidateId);
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
						str+='<p class="nameCls">'+result.name+'</p>';
						if(result.voterCardNumber != null && result.voterCardNumber != ""){
							str+='<p title="Voter Card No "> VID : '+result.voterCardNumber+'</p>';
						}
						str+='<p title="Membership No">MID : '+result.memberShipNo+'</p>';
						str+='<p>'+result.mobileNo+'</p>';
					str+='</div>';
					str+='<div class="panel-footer">';
						str+='<label class="checkbox-inline">';
							str+='<input id="profileCheckboxId" attr_cadre_id="'+result.tdpCadreId+'" attr_name ='+result.name+' type="checkbox"/>Select Profile';
						str+='</label>';
					str+='</div>';
					
				str+='</div>';
			str+='</li>';
            str+='</ul>';
		$("#selectedMemberDtslDivId").html(str);
		$("#selectedProfileId").show();
	}
	//getting candidate need to submit tour category details based on click on checkbox
	
	$(document).on("click","#profileCheckboxId",function(){
		$("#remarkId").val('');
		$("#addNewTourBlock").html("");
		$(".jFiler-items-list").html(' ');
		if($(this).is(':checked')){
			$("#overallDivId").show();
			$("#toursNewBlockClonedId").html("");
			$(".tourDescNewCls").val("");
			$("#tourMonthYear").val("");
			var desigDtlsId = $("#designationSlctBxId").val();
			var tdpCadreId = $(this).attr("attr_cadre_id");
			var tdpCadreName = $(this).attr("attr_name");
			$("#hiddenTdpCadreId").val(tdpCadreId);
			$("#tourMonthYear").val(moment().format("MM-YYYY"));
			getAllTourDetailsOverview(1,'',0,$("#tourMonthYear").val(),tdpCadreName,'');
			
		}else{
			$("#toursCandidateDetails").html("");
			$("#attachementsId").html("");
			$("#overallDivId").hide();
		}
	});
	
	function getAllTourDetailsOverview(saveUpdate,tdpCadreId,toursMonthId,tourdate,tdpCadreName,monthDate){
	
	    $("#toursCandidateDetails").html("");
		$("#attachementsId").html("");
		$("#toursCandidateDetailsModal").html("");
		$("#attachementsIdModal").html("");
		$("#errorDiv").html("");
		if(saveUpdate == 1)
		{
			var tourdate = $("#tourMonthYear").val();
			var tdpCadreId = $("#hiddenTdpCadreId").val();
			if(tourdate == 0){
				$("#errorDiv").html("Please select date");
				return;
			}else{
				$("#errorDiv").html("");
			}
			var jsObj = {     
				tourdate : tourdate,
				tdpCadreId:tdpCadreId,
				toursMonthId:toursMonthId
			}
		}else{
			var jsObj = {     
				tourdate : "",
				tdpCadreId:tdpCadreId,
				toursMonthId:toursMonthId
			}
		}
	
		$.ajax({
			type : 'POST',
			url : 'getSelectedprofileToursOverviewAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#changedDate").html('('+moment().format("MMM-YYYY")+')');
			 
			if(result != null){
				buildAllTourDetailsOverview(result,saveUpdate,tdpCadreName,monthDate);
			}else{
				$("#candidateNameId").html("");
				if(saveUpdate == '1')
				{
					$("#toursCandidateDetails,attachementsId").html("No data available...");
				}else if(saveUpdate == '2')
				{
					$("#toursCandidateDetailsModal,attachementsIdModal").html("No data available...");
				}
			}	
	});
}
 function buildAllTourDetailsOverview(result,saveUpdate,tdpCadreName,monthDate){
	$("#candidateNameId").html("SELECT PROFILE TOURS OVERVIEW");
	if(saveUpdate == '2'){
		$("#monthEditId").html('('+(monthDate)+')');
	}
	$(this).parent(".panelProfile").find().html(tdpCadreName);
	var str = '';
	var str1 = '';
	
	var strp = '';
	
	if(result != null){
		if(result.remark!=null && !result.remark==''){
			$("#remarkId").val(result.remark);
		}else{
			$("#remarkId").val('');
		}
		
		//CategoryWise Info
		if(result.toursVoList != null && result.toursVoList.length > 0){
				str+='<table class="table table-bordered">';
				  str+='<thead style="background-color:#ccc">';
					str+='<th>Designation</th>';
					str+='<th>Tour Category</th>';
					str+='<th>Type</th>';
					str+='<th>category</th>';
					str+='<th>Category Sub Type</th>';
					str+='<th>Tours Submitted Days</th>';
					str+='<th>Updated Date</th>';
					str+='<th></th>';
				  str+='</thead>';
				  str+='<tbody>';
				  for(var i in result.toursVoList){
						str+='<tr  id="rowId'+i+'" class="tr_clone">';
							if(result.toursVoList[i].designation != null){
								str+='<td class="desigCls'+i+'" attr_desig_id='+result.toursVoList[i].designationId+' attr_candidate_id='+result.toursVoList[i].candidateId+' attr_category_id='+result.toursVoList[i].categoryId+' details_newId='+result.toursVoList[i].detailsNewId+' tour_type_id='+result.toursVoList[i].tourTypeId+' tour_Days_id='+result.toursVoList[i].tourDays+'>'+result.toursVoList[i].designation+'</td>';
								str+='<input type="hidden" name="toursNewVO.toursVoListNew['+i+'].designationId" value="'+result.toursVoList[i].designationId+'">';
							}else{
								str+='<td>-</td>';
								str+='<input type="hidden" name="toursNewVO.toursVoListNew['+i+'].designationId" value="0">';
							}	
							if(result.toursVoList[i].detailsNewId != null){
								str+='<input type="hidden" name="toursNewVO.toursVoListNew['+i+'].detailsNewId" value="'+result.toursVoList[i].detailsNewId+'">';
							}else{
								str+='<input type="hidden" name="toursNewVO.toursVoListNew['+i+'].detailsNewId" value="0">';
							}
							
							str+='<input type="hidden" name="toursNewVO.toursVoListNew['+i+'].candidateId" value="'+result.toursVoList[i].candidateId+'">';					
						    str+='<td>'+result.toursVoList[i].category+'</td>';
						    str+='<input type="hidden" name="toursNewVO.toursVoListNew['+i+'].tourCategoryId" value="'+result.toursVoList[i].categoryId+'">';
						    str+='<td>'+result.toursVoList[i].comment+'</td>';//TourType
						    str+='<input type="hidden" name="toursNewVO.toursVoListNew['+i+'].tourTypeId" value="'+result.toursVoList[i].tourTypeId+'">';
						  if(result.toursVoList[i].detailsNewId == null || result.toursVoList[i].tourTypeCategory==null || result.toursVoList[i].tourTypeCategory.trim().length == 0){
						   if (result.toursVoList[i].subList != null && result.toursVoList[i].subList.length > 0) {
							    str+='<td>'
								str+='<select id="tourTypeCategoryId'+i+'" name="toursNewVO.toursVoListNew['+i+'].tourTypeCategoryId" class="form-control tourTypeCategoryCls">';
								  str+='<option value="0">Select Category</option>';	
								for(var j in result.toursVoList[i].subList) {
									if (result.toursVoList[i].categoryId == 7) {
										if (result.toursVoList[i].subList[j].id == 3) {
											str+='<option value="'+result.toursVoList[i].subList[j].id+'">'+result.toursVoList[i].subList[j].name+'/Social welfare</option>';
										}
									} else {
										str+='<option value="'+result.toursVoList[i].subList[j].id+'">'+result.toursVoList[i].subList[j].name+'</option>';
									}
								}
								str+='</select>';
								str+='</td>';
						   }
						  } else {
							  if (result.toursVoList[i].categoryId == 7) {
								   str+='<td>'+result.toursVoList[i].tourTypeCategory+'/Social welfare</td>';
									str+='<input type="hidden" name="name="toursNewVO.toursVoListNew['+i+'].tourTypeCategoryId"" value="'+result.toursVoList[i].tourTypeCategoryId+'">';
							  } else {
								   str+='<td>'+result.toursVoList[i].tourTypeCategory+'</td>';
									str+='<input type="hidden" name="name="toursNewVO.toursVoListNew['+i+'].tourTypeCategoryId"" value="'+result.toursVoList[i].tourTypeCategoryId+'">';
							  }
							 
						  }
						    if (result.toursVoList[i].tourTypeId != null && result.toursVoList[i].tourTypeId==2)//govt 
							{
							 str+='<td></td>'
							} else {
								 if(result.toursVoList[i].detailsNewId == null || result.toursVoList[i].childTtourType == null|| result.toursVoList[i].childTtourType.trim().length == 0){
									 if (result.toursVoList[i].subList1 != null && result.toursVoList[i].subList1.length > 0) {
										str+='<td>'
										str+='<select class="form-control childTourTypeCls" id="childTourTypeId'+i+'" name="toursNewVO.toursVoListNew['+i+'].childTourTypeId">';
										 str+='<option value="0">Select Category Sub Type</option>';	
										for(var j in result.toursVoList[i].subList1) {
											if (result.toursVoList[i].categoryId == 7) {
												if (result.toursVoList[i].subList1[j].id == 3) {
													 str+='<option value="'+result.toursVoList[i].subList1[j].id+'">'+result.toursVoList[i].subList1[j].name+'</option>';
												}
											} else {
											  str+='<option value="'+result.toursVoList[i].subList1[j].id+'">'+result.toursVoList[i].subList1[j].name+'</option>';
											}
										}
										str+='</select>';
										str+='</td>';
									}
								 } else {
									 str+='<td>'+result.toursVoList[i].childTtourType+'</td>';
									str+='<input type="hidden" name="name="toursNewVO.toursVoListNew['+i+'].childTourTypeId"" value="'+result.toursVoList[i].tourTypeChildId+'">';
								 }
							}
						   
						  if(result.toursVoList[i].tourDays != null){
							  str+='<td><input type="text" name="toursNewVO.toursVoListNew['+i+'].tourDays" class="form-control toursDaysCls" attr_action_type="add" attr_index='+i+' attr_clone_index_id ="" onkeypress="return isNumberKey(event)" id="tourDaysId'+i+'" value="'+result.toursVoList[i].tourDays+'"/></td>';
						  }else{
							  str+='<td><input type="text" name="toursNewVO.toursVoListNew['+i+'].tourDays" class="form-control toursDaysCls" attr_action_type="add" attr_index='+i+'  attr_clone_index_id ="" value="0" id="tourDaysId'+i+'" onkeypress="return isNumberKey(event)" /></td>'; 
						  }
						  if(result.toursVoList[i].tourDate != null){
							  var tourDate = result.toursVoList[i].tourDate.substring(0, 19);
							   str+='<td>'+tourDate+'</td>';
						  }else{
							   str+='<td class="text-center">-</td>';
						  }
						   if(result.toursVoList[i].detailsNewId != null){
								 str+='<td></td>';
							}else{
								str+='<td class="text-center">';
							  str+='<div class="panel-footer borderGreen ">';
									str+='<button attr_index='+i+' attr_action_type="add" type="button"  class="btn btn-success addAppedTourEntryCls">+ ADD TOUR</button>';
									str+='</div>';
							 str+='</td>';
							}
						str+='</tr>';
					}
				  str+='</tbody>';
				str+='</table>';
				
			}
			if(result.docList != null && result.docList.length > 0){
				str1+='<h4 class="panel-title" style="margin-top:20px !important">Uploaded Attachments</h4>';
				str1+='<div class="m_top10">';
					str1+='<table class="table tableAttachments">';
					
						for(var j in result.docList){
							
						str1+='<tr id="documentTrId'+result.docList[j].id+'">';
							str1+='<td><img src="images/pdf.jpg" class="media-object" style="width:20px;"/></td>';
							str1+='<td>'+result.docList[j].filePath+'</td>';
							if(saveUpdate == '2')
							{
								var tourUpdatedDate = result.docList[j].tourDate.substring(0, 19);
								str1+='<td>UPDATED : '+tourUpdatedDate+'</td>';
							}
							str1+='<td><button class="btn btnView viewPdfCls" attr_filePath="'+result.docList[j].filePath+'" type="button">VIEW</button></td>';
							str1+='<td><button class="btn btnDelete deletePdfCls" type="button" attr_id='+result.docList[j].id+'>DELETE</button></td>';
							str1+='</tr>';
						}	
					
					str1+='</table>';
					str1+='</div>';
				}
			// ProgramWise Info
			
			if(result.toursVoListNew != null && result.toursVoListNew.length > 0){
				strp+='<h4 style="margin-top:20px !important">Program Wise Info</h4>';
				strp+='<table class="table table-bordered">';
				  strp+='<thead style="background-color:#ccc">';
					strp+='<th>Designation</th>';
					strp+='<th>Program</th>';
					strp+='<th>Tour Visits</th>';
					strp+='<th>Updated Date</th>';
				  strp+='</thead>';
				  strp+='<tbody>';
				  for(var i in result.toursVoListNew){
						strp+='<tr>';//toursVoProgramsList
							if(result.toursVoListNew[i].designation != null){
								strp+='<td class="desigCls'+i+'" attr_desig_id='+result.toursVoListNew[i].designationId+' attr_candidate_id='+result.toursVoListNew[i].candidateId+' details_newId='+result.toursVoListNew[i].detailsNewId+' tour_Days_id='+result.toursVoListNew[i].tourDays+'>'+result.toursVoListNew[i].designation+'</td>';
								strp+='<input type="hidden" name="toursNewVO.toursVoProgramsList['+i+'].designationId" value="'+result.toursVoListNew[i].designationId+'">';
							}else{
								strp+='<td>-</td>';
								strp+='<input type="hidden" name="toursNewVO.toursVoProgramsList['+i+'].designationId" value="0">';
							}	
							if(result.toursVoListNew[i].detailsNewId != null){
								strp+='<input type="hidden" name="toursNewVO.toursVoProgramsList['+i+'].detailsNewId" value="'+result.toursVoListNew[i].detailsNewId+'">';
							}else{
								strp+='<input type="hidden" name="toursNewVO.toursVoProgramsList['+i+'].detailsNewId" value="0">';
							}
							
							if(result.toursVoListNew[i].candidateId !=null && result.toursVoListNew[i].candidateId>0){
								strp+='<input type="hidden" name="toursNewVO.toursVoProgramsList['+i+'].candidateId" value="'+result.toursVoListNew[i].candidateId+'">';
							}
						  strp+='<td>'+result.toursVoListNew[i].category+'</td>';
						  strp+='<input type="hidden" name="toursNewVO.toursVoProgramsList['+i+'].tourCategoryId" value="'+result.toursVoListNew[i].categoryId+'">';
						  if(result.toursVoListNew[i].tourDays != null){
							  strp+='<td><input type="text" name="toursNewVO.toursVoProgramsList['+i+'].tourDays" class="form-control toursDaysCls" onkeypress="return isNumberKey(event)" id="tourDaysId'+i+'" value="'+result.toursVoListNew[i].tourDays+'"/></td>';
						  }else{
							  strp+='<td><input type="text" name="toursNewVO.toursVoProgramsList['+i+'].tourDays" class="form-control toursDaysCls" value="0" id="tourDaysId'+i+'" onkeypress="return isNumberKey(event)" /></td>'; 
						  }
						  if(result.toursVoListNew[i].tourDate != null){
							  var tourDate = result.toursVoListNew[i].tourDate.substring(0, 19);
							   strp+='<td>'+tourDate+'</td>';
						  }else{
							   strp+='<td class="text-center">-</td>';
						  }
						 
						strp+='</tr>';
					}
				  strp+='</tbody>';
				strp+='</table>';				
			}
	}
	
	if(saveUpdate == '1')
	{
		$("#toursCandidateDetails").html(str);
		$("#attachementsId").html(str1);
		
		$("#toursCandidateProgramDetails").html(strp);
		
	}else if(saveUpdate == '2')
	{
		if(result.remark!=null){
		$('#remarkId3').css('display','block');
		$('#remarkId2').val(result.remark);
		}else{}
		
		$("#toursCandidateDetailsModal").html(str);
		$("#attachementsIdModal").html(str1);
		$("#toursCandidateProgramDetailsModal").html(strp);
		
	}
 }

	function isNumberKey(evt){
		var charCode = (evt.which) ? evt.which : evt.keyCode;
	    if (charCode != 46 && charCode > 31  && (charCode < 48 || charCode > 57))
	             return false;
	          return true;
	}

  function savingApplication1(){
	  var tourdate = $("#tourMonthYear").val();
	  var flag = false;
	  var isDataEntered	= false;

	   var tourCategryTypeSelected = false;
	   var tourChildTypeSelected = false;
	   var isCloneExtraAdded = false;
	   var isTourTypeCategorySelected = false;
	   var isTourTypeChildSelected = false;
	  $(".toursDaysCls").each(function(){
		    var enteredValue = parseInt($(this).val());
		    var actionType = $(this).attr("attr_action_type");
			
			//validating is there any extra clone added and not entering value
			if (actionType == "remove") {
				if (enteredValue == 0) {
					$(this).css("border-color","red");
					isCloneExtraAdded = true;
					return;
				} else {
					$(this).css("border-color","white");
				} 
			}
			
			if(enteredValue>0){
			  flag = false;
			  isDataEntered=true;
			} 
			else if(!isDataEntered){
				if($(this).val().trim()== "" || $(this).val().trim()<=0 || $(this).val()=="undefined"){
					flag = true;
				}
			} 
			
			var clneIndxId  = 0;
			var index = $(this).attr("attr_index");
			var cloneIndexId = $(this).attr("attr_clone_index_id");
			var prepareTourCategryId = "tourTypeCategoryId"+index;
			if (cloneIndexId != null && cloneIndexId != undefined && cloneIndexId.trim().length > 0) {
				prepareTourCategryId = prepareTourCategryId+cloneIndexId;
			}
			var tourCategoryId = $("#"+prepareTourCategryId).val();
			if (enteredValue > 0) {
				if (tourCategoryId == 0) {
					$("#"+prepareTourCategryId).css("border-color","red");
					tourCategryTypeSelected = true;
					return;
				}
				//clear validation color
				$("#"+prepareTourCategryId).css("border-color","white");
			}
			//validating if tourTypeCategory has been seleted by enduser but tour days is not entered
			if (tourCategoryId != undefined && tourCategoryId > 0) {
				if (enteredValue == 0) {
				  isTourTypeCategorySelected = true;
				  $(this).css("border-color","red");
				  return;				  
				} else {
					$(this).css("border-color","white");
				}
			}
			
			var prepareTourChildTypeId = "childTourTypeId"+index;
			if (cloneIndexId != null && cloneIndexId != undefined && cloneIndexId.trim().length > 0) {
				prepareTourChildTypeId = prepareTourChildTypeId+cloneIndexId;
			}
			var childTourTypeId = $("#"+prepareTourChildTypeId).val();
			if (enteredValue > 0) {
				if (childTourTypeId != undefined) {
					if (childTourTypeId == 0) {
						$("#"+prepareTourChildTypeId).css("border-color","red");
						tourChildTypeSelected = true;
						return;
				   }
				}
				//clear validation color
				$("#"+prepareTourChildTypeId).css("border-color","white");
			}
			
			//validating if tourTypeCategory has been seleted by enduser but tour days is not entered
			if (childTourTypeId != undefined && childTourTypeId > 0) {
				if (enteredValue == 0) {
				  isTourTypeChildSelected = true;	
				  $(this).css("border-color","red");
				  return;
				} else {
					$(this).css("border-color","white");
				}
			}
			
		});
		
		if(flag){
			$("#errorDiv").html("Please enter tour days.");
			return;
		}
		if(tourdate == 0){
			$("#errorDiv").html("Please select date");
			return;
		}else{
			$("#errorDiv").html("");
		}
		
		if (tourCategryTypeSelected) {
			return;
		}
		if (tourChildTypeSelected) {
			return;
		}
		
		if (isCloneExtraAdded) {
			alert("Please Enter Tour Days,Otherwise remove it.");
			return;
		}
		if (isTourTypeCategorySelected) {
			alert("Please enter tour days.");
			return;
		}
		if (isTourTypeChildSelected) {
			$(this).css("border-color","red");
			alert("Please enter tour days.");
			return;
		}
	 $("#formSubmitLoader").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	 $("#hiddenCadreId").val($("#profileCheckboxId").attr("attr_cadre_id"));
	 $("#hiddenTourMonthId").val($("#tourMonthYear").val());
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
		$("#submitId").attr("disabled","disabled");
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
	
	function updateApplication1(){
		
	   var tourCategryTypeSelected = false;
	   var tourChildTypeSelected = false;
	   var isTourTypeCategorySelected = false;
	   var isTourTypeChildSelected = false;
	  $(".toursDaysCls").each(function(){
		    var enteredValue = parseInt($(this).val());
		    var actionType = $(this).attr("attr_action_type");
			
			var clneIndxId  = 0;
			var index = $(this).attr("attr_index");
			var cloneIndexId = $(this).attr("attr_clone_index_id");
			var prepareTourCategryId = "tourTypeCategoryId"+index;
			if (cloneIndexId != null && cloneIndexId != undefined && cloneIndexId.trim().length > 0) {
				prepareTourCategryId = prepareTourCategryId+cloneIndexId;
			}
			var tourCategoryId = $("#"+prepareTourCategryId).val();
			if (enteredValue > 0) {
				if (tourCategoryId == 0) {
					$("#"+prepareTourCategryId).css("border-color","red");
					tourCategryTypeSelected = true;
					return;
				}
				//clear validation color
				$("#"+prepareTourCategryId).css("border-color","white");
			}
			//validating if tourTypeCategory has been seleted by enduser but tour days is not entered
			if (tourCategoryId != undefined && tourCategoryId > 0) {
				if (enteredValue == 0) {
				  isTourTypeCategorySelected = true;
				  $(this).css("border-color","red");
				  return;				  
				} else {
					$(this).css("border-color","white");
				}
			}
			
			var prepareTourChildTypeId = "childTourTypeId"+index;
			if (cloneIndexId != null && cloneIndexId != undefined && cloneIndexId.trim().length > 0) {
				prepareTourChildTypeId = prepareTourChildTypeId+cloneIndexId;
			}
			var childTourTypeId = $("#"+prepareTourChildTypeId).val();
			if (enteredValue > 0) {
				if (childTourTypeId != undefined) {
					if (childTourTypeId == 0) {
						$("#"+prepareTourChildTypeId).css("border-color","red");
						tourChildTypeSelected = true;
						return;
				   }
				}
				//clear validation color
				$("#"+prepareTourChildTypeId).css("border-color","white");
			}
			
			//validating if tourTypeCategory has been seleted by enduser but tour days is not entered
			if (childTourTypeId != undefined && childTourTypeId > 0) {
				if (enteredValue == 0) {
				  isTourTypeChildSelected = true;	
				  $(this).css("border-color","red");
				  return;
				} else {
					$(this).css("border-color","white");
				}
			}
			
		});
		
		if (tourCategryTypeSelected) {
			return;
		}
		if (tourChildTypeSelected) {
			return;
		}
		if (isTourTypeCategorySelected) {
			alert("Please enter tour days.");
			return;
		}
		if (isTourTypeChildSelected) {
			alert("Please enter tour days.");
			return;
		}
		
		
	 $("#updateformSubmitLoader").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	  var flag = false;
		$("#hiddentdpCadreIdForModal").val($("#hiddentdpCadreIdForPopUp").val());
		//$("#hiddentourMonthIdForModal").val($("#hiddentourMonthIdForPopUp").val());
		$("#hiddentourMonthIdForModal").val($("#hiddentourMonthIdForPopUp").val());
		var uploadHandler = { 
			upload: function(o) {
				$("#savingAjaxImg").css("display","none");
				uploadResult = o.responseText;
				showSbmitStatusNew1(uploadResult);
			}
		};
		YAHOO.util.Connect.setForm('toursUpdateFormApplication',true);  
		//YAHOO.util.Connect.asyncRequest('POST','savingTourDtlsApplicationAction.action',uploadHandler);
		YAHOO.util.Connect.asyncRequest('POST','saveDesignationWiseTourDetailsAction.action',uploadHandler);
		$("#updateId").attr("disabled","disabled");
	}
	function showSbmitStatusNew1(uploadResult){
		if(uploadResult !=null && uploadResult.search("success") != -1){
			$("#successSpanModalId").show();			
			$("#update_TourFileId2").val('');
			var filerKit = $("#update_TourFileId2").prop("jFiler");
			setTimeout(function () {
				$("#successSpanModalId").html("<center style='color: green; font-size: 16px;'>Saved Successfully</center>").fadeOut(3000);
				filerKit.reset();
				location.reload(true);				
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
	
	$(document).on("click",".candidateUpdate",function(){
		$(".jFiler-items-list").html(' ');
		var tdpCadreId = $("#hiddentdpCadreIdForPopUp").val();
		$("#hiddentourMonthIdForPopUp").val($(this).attr("attr_monthId"));
		var toursMonthId = $("#hiddentourMonthIdForPopUp").val();
		$('#editTourDetailsModalId').modal({
            show: true,
            keyboard: false,
            backdrop: 'static'
        });
		var monthDate = $(this).attr("attr_monthdate");
		var monthId = $(this).attr("attr_monthId");
		getAllTourDetailsOverview(2,tdpCadreId,monthId,'','',monthDate);
		
	});
	
  /* Saving or updating tour details block End */
	
	/* Clone Add Tour Block Start */
	
	//var regex = /^(.*)(\d)+$/i;
	var cindex = 0;
	$(document).on("click",".addAppedTourEntryCls",function(){
		var actionType = $(this).attr("attr_action_type");
		if (actionType == "remove") {
			$(this).closest('.tr_clone').remove();
			cindex--;
			return;
		}
		var $tr    = $(this).closest('.tr_clone');
		var $clone = $tr.clone(true);
		var index = $(this).attr("attr_index");
		
		var categoryId = $clone.find("select.tourTypeCategoryCls").attr("id");
		var categoryValue = $("#"+categoryId).val();
		if (categoryValue == 0) {
			alert("Please Select Category");
			return;
		}
		
		var childTourTypeId = $clone.find("select.childTourTypeCls").attr("id");
		var childTourTypeValue = $("#"+childTourTypeId).val();
		if (childTourTypeValue != undefined && childTourTypeValue != null ) {
			if (childTourTypeValue == 0) {
			  alert("Please Select Child Tour Type.");
			  return;
		    }
		}
		var toursDaysValue = $("#tourDaysId"+index).val();
		if (toursDaysValue != undefined && toursDaysValue != null ) {
			if (toursDaysValue == 0) {
			  alert("Please Enter Tour Days.");
			  return;
		    }
		}
		$clone.find(".addAppedTourEntryCls").attr("attr_action_type","remove");
		$clone.find(".addAppedTourEntryCls").html("Remove");
		$clone.find(".addAppedTourEntryCls").css("border-color","red");
		$clone.find(".addAppedTourEntryCls").css("color","red");
		$clone.find(':text').val('0');
		$clone.find('input').removeAttr("name");
		$clone.find(".tourTypeCategoryCls").attr("name","toursNewVO.toursVoListNew["+index+"].toursVoListNew["+cindex+"].tourTypeCategoryId");
		$clone.find(".childTourTypeCls").attr("name","toursNewVO.toursVoListNew["+index+"].toursVoListNew["+cindex+"].childTourTypeId");
		$clone.find(".toursDaysCls").attr("name","toursNewVO.toursVoListNew["+index+"].toursVoListNew["+cindex+"].tourDays");
		$clone.find(".toursDaysCls").attr("attr_index",index);
		$clone.find(".toursDaysCls").attr("attr_clone_index_id",cindex);
		$clone.find(".toursDaysCls").attr("attr_action_type","remove");
		$clone.find("select.tourTypeCategoryCls").attr("id","tourTypeCategoryId"+index+""+cindex);
		$clone.find("select.childTourTypeCls").attr("id","childTourTypeId"+index+""+cindex);
		$clone.find("select.tourTypeCategoryCls").css("border-color","white");
		$clone.find("select.childTourTypeCls").css("border-color","white");
		cindex++;
		//$clone.attr('id', 'id'+(cindex) ); //update row id if required
		$tr.after($clone);
	});
	/* End */
	
	
	/*Dashboard retrival block start */
	
	var fromDate = moment().format('DD/MM/YYYY');
	var toDate = moment().format('DD/MM/YYYY');
	$('#toursDateRangePickerNew').on('apply.daterangepicker', function(ev, picker) {
		fromDate = picker.startDate.format('DD/MM/YYYY');
		toDate = picker.endDate.format('DD/MM/YYYY');
		fD = picker.startDate.format('MMMM');
		tD = picker.endDate.format('MMMM');
		$("#toursNewBlockClonedId").html("");
		var designationIds = [];
	    getTourBasicOverviewDtlsDesignationWise(fromDate,toDate,designationIds);
	});
	//first block ajax call building designation wise tour overview details
	function getTourBasicOverviewDtlsDesignationWise(fromDate,toDate,designationIds){ 
		$("#overAllLeaderDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
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
				str+='<td class="text-center"><a style="cursor:pointer;" class="getSubMitedLeadersDtlsCls" attr_desig_name="'+result[i].designation+'" attr_designation_id='+result[i].id+' attr_tdp_cadre_id='+result[i].tdpCadreId+' >'+result[i].submitedLeaderCnt+'</a></td> ';
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
	//getting canidate wise details when will u click on designation count
	$(document).on("click",".getSubMitedLeadersDtlsCls",function(){
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
		getMemberDetailsByDesignationWise(fromDateStr,toDateStr,desigId,desigName);
		var designationIds=[];
		designationIds.push(desigId);
		getDesignationWiseOverAllData(fromDateStr,toDateStr,designationIds);
	});
	
	function getMemberDetailsByDesignationWise(fromDateStr,toDateStr,desigId,desigName){
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
			if(result != null){
				buildMemberDetailsByDesignationWise(result,desigId,desigName);
			}
		});
	}
	function buildMemberDetailsByDesignationWise(result,desigId,desigName)
	{
		var str='';
		str+='<table class="table table-bordered tableModal" id="ministersOvwDataTableId">';
			str+='<thead class="text-capital">';
				str+='<tr>';
					str+='<th rowspan="2" class="bg_D8">Name</th>';
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
					str+='<td><span  attr_type="direct" class="candiateCls text-capital" attr_candiate_id="'+result[i].id+'" attr_candiate_name="'+result[i].name+'" attr_designation_name="'+desigName+'" attr_tdp_cadre_id='+result[i].tdpCadreId+' attr_tours_month_id='+result[i].toursMonthId+' >'+result[i].name+'</span></td>';
					
					str+='<div id="hiddentdpCadreIdForPopUp"></div>';
					str+='<div id="hiddentourMonthIdForPopUp"></div>';
					for(var j in result[i].subList3)
					{
						str+='<td class="bg_ED text-center">'+result[i].subList3[j].targetDays+'</td>'
						str+='<td class="bg_ED text-center">'+result[i].subList3[j].complainceDays+'</td>'
					}
					str+='<td style="position:relative" class="text-center">'+result[i].count+' ';
					str+='<button class="btn btn-success editBtn editModalBtn candiateCls btn-xs" attr_designation_name="'+desigName+'" attr_candiate_name="'+result[i].name+'" attr_candiate_id="'+result[i].id+'" attr_designation_id="'+desigId+'" attr_candidateId="'+result[i].id+'" attr_name="'+result[i].name+'" style="position:absolute;right:20px;" attr_tdp_cadre_id='+result[i].tdpCadreId+' attr_tours_month_id='+result[i].toursMonthId+'  >EDIT</button>';
					str+='</td>';
				str+='</tr>';
			}

		str+='</table>';
		$("#membersOverviewId").html(str);
		$("#ministersOvwDataTableId").dataTable();
	}
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
	
	$(document).on("click",".candiateCls",function(){
		var candiateId = $(this).attr("attr_candiate_id");
		 $("#subMitBtn").attr("attr_candidate_id",candiateId);
		var designationName = $(this).attr("attr_designation_name");
		var candiateName = $(this).attr("attr_candiate_name");
		var selectedLevel = $(this).attr("attr_type");
		$(".tourIndividualCls").attr("attr_type",selectedLevel);
		//var selectedDate = globalFormTourDate.split("/");
		$("#dateRangeSliderYear").val(0);
		var tdpHiddenCadre = $(this).attr("attr_tdp_cadre_id");
		var toursHiddenMonth = $(this).attr("attr_tours_month_id");
		$("#hiddentdpCadreIdForPopUp").val(tdpHiddenCadre);
		$("#hiddentourMonthIdForPopUp").val(toursHiddenMonth);
	   getCandiateWiseTourDetails(candiateId,designationName,candiateName);
	});
	function getCandiateWiseTourDetails(candiateId,designationName,candiateName)
	{ 
		$("#tourIndividualPerformanceDivId").modal({
            show: true,
            keyboard: false,
            backdrop: 'static'
        });
		
		var dates=$("#toursDateRangePickerNew").val();    
		var fromDateStr;
		var toDateStr;
		if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			fromDateStr = datesArr[0]; 
			toDateStr = datesArr[1]; 
		}
		var fromDateSlider = fromDateStr.split("/")
		var toDateSlider = toDateStr.split("/")
		var months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"];
		  $("#toursUpdateYear").dateRangeSlider({
			bounds: {min: new Date(fromDateSlider[2], 0, 1), max: new Date(toDateSlider[2], 11, 31)},
			defaultValues: {min: new Date(fromDateSlider[2], fromDateSlider[1]-1, fromDateSlider[0]), max: new Date(toDateSlider[2],toDateSlider[1]-1, toDateSlider[0])},
			scales: [{
			  first: function(value){ return value; },
			  end: function(value) {return value; },
			  next: function(value){
				var next = new Date(value);
				return new Date(next.setMonth(value.getMonth() + 1));
			  },
			  label: function(value){
				return months[value.getMonth()];
			  },
			  format: function(tickContainer, tickStart, tickEnd){
				tickContainer.addClass("myCustomClass");
			  }
			}]
		  });
		$("#nameOfMemberHeadingId").html('');
		$("#nameOfMemberHeadingId").html("<h4 class='modal-title text-capital'>"+candiateName+" - <small style='color:#4A5863'>"+designationName+"</small></h4>");
		$("#tourIndividualDetailsBlock").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		$("#tourIndividualDetailsTableBlock").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		$("#monthWiseComplainceDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jsObj ={ 
					 candiateId : candiateId,
					 fromDate :fromDateStr ,
					 toDate : toDateStr
				  }
		$.ajax({
			type : 'POST',
			url : 'getIndividualPersonTourDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#tourIndividualDetailsBlock").html('');
			$("#tourIndividualDetailsTableBlock").html('');
			$("#monthWiseComplainceDivId").html(' ');
			buildIndividualPersonTourDetails(result);
		});
	}
	function buildIndividualPersonTourDetails(result)
	{
		if(result !=null && result.subList != null && result.subList.length > 0){
			 var str='';
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				str+='<div class="row">';
				
					str+='<div class="col-md-4 col-xs-12 col-md-12">';
						str+='<h4 class="text-capital">TOTAL COMPLAINCE OVERVIEW</h4>';
						str+='<div id="overAllComplainsGraph" class="" style="height:150px" ></div>';
					str+='</div>';
					str+='<div class="col-md-8 col-xs-12 col-md-12">';
						str+='<div class="row">';
						for(var i in result.subList){
							str+='<div class="col-md-4 col-xs-12 col-md-12">';
								str+='<h4 class="text-capital">'+result.subList[i].name+'</h4>';
								str+='<div id="individualComplainsGraph'+i+'" class="" style="height:150px" ></div>';
							str+='</div>';
						
						}
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
			$("#tourIndividualDetailsBlock").html(str);
				
			var mainArrNma=[];
			var individualPerfArr=[];
			var nameArr;
			var jsonObj=[];	
			mainArrNma.push("All")
			jsonObj.push(result.complaincePer);
			for(var i in result.subList){
				jsonObj.push(result.subList[i].complaincePer);
				mainArrNma.push(result.subList[i].name);
				nameArr = result.subList[i].name;
			}
			
			 
			$('#overAllComplainsGraph').highcharts({
				colors: ['#80F6F8'],
				chart: {
					type: 'column'
				},
				title: {
					text: null
				},
				subtitle: {
					text: null
				},
				xAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: mainArrNma,
					title: {
						text: null
					},
				},
				yAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: null,
						align: 'high'
					},
					labels: {
						overflow: 'justify',
						enabled: false,
					}
				},
				 tooltip: {formatter: function(){
					return '<b>'+this.x+' : '+ Highcharts.numberFormat(this.y, 2) +'%</b>';
				}      
				},
				plotOptions: {
					column: {  
						//stacking: 'normal',
						dataLabels: {
							enabled: true,
							 formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return Highcharts.numberFormat(this.y,2) +"%"; 
								}
							}
						  
						},
					},
					
				},
				legend: {
					enabled: false,
					layout: 'vertical',
					align: 'right',
					verticalAlign: 'top',
					x: -40,
					y: 80,
					floating: true,
					borderWidth: 1,
					backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
					shadow: true
				},
				credits: {
					enabled: false
				},
				
				series:  [{
					name: nameArr,
					data: jsonObj
				}]
			});
			
			for(var i in result.subList){
				var performanceArr =[];
				performanceArr.push(result.subList[i].targetDays);
				performanceArr.push(result.subList[i].complainceDays);
				//performanceArr.push(result.subList[i].yetToTourCnt);
				if(performanceArr != 0 && performanceArr.length > 0){
					 $(function () {
						  $('#individualComplainsGraph'+i+'').highcharts({
							colors: ['#7F7037','#80F6F8'],
							chart: {
								type: 'column'
							},
							title: {
								text: null
							},
							subtitle: {
								text: null
							},
							xAxis: {
								min: 0,
								gridLineWidth: 0,
								minorGridLineWidth: 0,
								categories: ["Target","Toured"],
								title: {
									text: null
								},
								labels: {
										formatter: function() {
											return this.value.toString().substring(0, 5)+'...';
										},
										
									}
								
							},
							yAxis: {
								min: 0,
								gridLineWidth: 0,
								minorGridLineWidth: 0,
								title: {
									text: null,
									align: 'high'
								},
								labels: {
									overflow: 'justify',
									enabled: false,
								}
							},
							 tooltip: {formatter: function(){
								return '<b>'+this.x+' : '+ (this.y) +'</b>';
								
							}      
							},
							plotOptions: {
								column: {  
									//stacking: 'normal',
									dataLabels: {
										enabled: true,
										 formatter: function() {
											if (this.y === 0) {
												return null;
											} else {
												return '<span style="text-align:center">'+(this.y)+'</span>';
											}
										}
									  
									},
								},
								
							},
							legend: {
								enabled: false,
								layout: 'vertical',
								align: 'right',
								verticalAlign: 'top',
								x: -40,
								y: 80,
								floating: true,
								borderWidth: 1,
								backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
								shadow: true
							},
							credits: {
								enabled: false
							},
							
							series:  [{
								name: '',
								data: performanceArr,
								colorByPoint:true
							}]
						});
					});
				}else{
					 $('#individualComplainsGraph'+i+'').html("No Data Availble");
				}
			}	
		}else{
			$("#tourIndividualDetailsBlock").html("NO DATA AVAILABLE.");
		}
		if(result !=null && result.monthList != null && result.monthList.length > 0){
			var str2='';
			  
			str2+='<div class="col-md-12 col-xs-12 col-sm-12 ">';
				str2+='<h4 class="text-capital">MONTH WISE COMPLIANCE OVERVIEW</h4>';
				for(var i in result.monthList){
					//str2+='<li>';
					if(i == 0 || i%3 == 0){
						str2+='<div class="row">';
					}
				   if($(window).width() < 500)
					{
						str2+='<div class="table-responsive">';
					}
					str2+='<div class="col-md-4 col-xs-12 col-sm-12 m_top10">';
						str2+='<table class="table table-bordered">';
						 var categoryList = result.monthList[i].subList;
						 
						 if(categoryList != null && categoryList.length > 0){
								var moxCategoryLength = categoryList.length;
								var categoryVO = result.monthList[i].subList[0];
							str2+='<tr>';
								str2+='<td rowspan='+(moxCategoryLength+1)+' style="font-size:22px;background-color:#EDECE7">'+result.monthList[i].name+'<br>'+result.monthList[i].year+'</td>';
								str2+='<td style="background-color:#EDECE7"><p>'+categoryVO.name+'('+categoryVO.complainceDays+')';
								   if(categoryVO.complainceDays >= categoryVO.targetDays){
										str2+='<small style="text-align: center;"><i  style="color:#3DBC93;margin-left:7px;" class="glyphicon glyphicon-ok text-success "></i></small>';
									   }else{
										str2+='<small style="text-align: center;"><i  style="color:#E35B69;margin-left:7px;" class="glyphicon glyphicon-remove text-danger"></i></small>';
									   } 
								  str2+='<div class="dropup">';
								str2+='<span class="pull-right dropdown-toggle" style="font-size: 20px; font-weight: 600; margin-top: -23px;margin-right:-6px;cursor:pointer;" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">&#9432;</span>';
									str2+='<div class="dropdown-menu pull-right bg_ED arrow_box_bottom" aria-labelledby="dropdownMenu2" style="padding:10px;z-index:999;width:220px">';
										str2+='<p><span style="font-size: 14px; font-weight: 600; margin-top: -16px;">&#9432; </span><i style="font-size: 13px;">Tours Days Target Per Month</i></p>';
										str2+='<table class="table">';
											str2+='<tr><td style="background-color:#EDECE7">'+categoryVO.name+' - '+categoryVO.targetDays+'</td></tr>';
										str2+='</table>';
									str2+='</div>';
								str2+='</div></p>';  
									  str2+='</td>';  
									if(result.monthList[i].isComplaince != null && result.monthList[i].isComplaince.trim()=="True"){
									   str2+='<td style="background-color:#3DBC93;text-align:center;" rowspan="'+(moxCategoryLength+1)+'"><i class="glyphicon glyphicon-ok" style="font-size:55px;display:block;color:#068057;"></i><small style="color:#fff;">Compliance</small></td>';
									}else{
									   str2+='<td style="background-color:#E35B69;text-align:center;width:50px;" rowspan="'+(moxCategoryLength+1)+'"><i class="glyphicon glyphicon-remove" style="font-size:55px;display:block;color:#bf3646;"></i><small style="color:#fff;">Non Compliance</small></td>';
									} 	 
							  str2+='</tr>';
							
								for(var k in categoryList){
									if(k==0)
									continue;
									 str2+='<tr>';
									if(categoryList[k].name == null || categoryList[k].name == ""){
										str2+='<td style="background-color:#EDECE7;"> - </td>';
									}else{
									   str2+='<td style="background-color:#EDECE7"><p>'+categoryList[k].name+'('+categoryList[k].complainceDays+')';
										   if(categoryList[k].complainceDays >= categoryList[k].targetDays){
											str2+='<small style="text-align: center;"><i style="color:#3DBC93;margin-left:7px;" class="glyphicon glyphicon-ok text-danger"></i></small>';
										   }else{
												str2+='<small style="text-align: center;"><i style="color:#E35B69;margin-left:7px;" class="glyphicon glyphicon-remove text-danger"></i></small>';
										   } 
										 str2+='<div class="dropup">';
										str2+='<span class="pull-right dropdown-toggle" style="font-size: 20px; font-weight: 600; margin-top: -23px;margin-right:-6px;cursor:pointer;" id="dropdownMenu3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">&#9432;</span>';
											str2+='<div class="dropdown-menu pull-right bg_ED arrow_box_bottom" aria-labelledby="dropdownMenu3" style="padding:10px;z-index:999;width:220px !important;">';
												str2+='<p><span style="font-size: 14px; font-weight: 600; margin-top: -16px;">&#9432; </span><i style="font-size: 13px;">Tours Days Target Per Month</i></p>';
												str2+='<table class="table">';
													str2+='<tr style="background-color:#EDECE7"><td>'+categoryList[k].name+' - '+categoryList[k].targetDays+'</td></tr>';
												str2+='</table>';
											str2+='</div>';
										str2+='</div></p>';  
									   str2+='</td>';
									}
								str2+='</tr>';
							  }
						 }
						str2+='</table>';
						str2+='</div>';
						//str2+='</li>';
					   if(i == 2 || i%3 == 2){
							str2+='</div>';
						}
					 if($(window).width() < 500)
					{
						str2+='</div>';
					}
					
				}
			str2+='</div>';
			$("#monthWiseComplainceDivId").html(str2);
		}
		if(result !=null && result.subList3 != null && result.subList3.length > 0){
			var str1='';
			str1+='<div class="col-md-12 col-xs-12 col-sm-12 m_top20">';
			str1='<h4 class="text-capital">MONTH WISE COMPLIANCE COMMENTS & ATTACHMENTS</h4>';
					str1+='<div class="m_top20">';
						 if($(window).width() < 500)
						{
							str1+='<div class="table-responsive">';
						}
						str1+='<table class="table table-bordered borderedWeight tableModal">';
							str1+='<thead class="bg_D8">';
								str1+='<tr>';
									str1+='<th class="text-capital text-center" style="vertical-align: middle;">Month & Date</th>';
									str1+='<th class="text-capital text-center" style="vertical-align: middle;">Tour Category</th>';
									str1+='<th class="text-capital text-center" style="vertical-align: middle;">Tour Type</th>';
									str1+='<th class="text-capital text-center" style="vertical-align: middle;">NO OF Days</th>';
									str1+='<th class="text-capital text-center" style="vertical-align: middle;">ATTACHMENT</th>';
									//str1+='<th class="text-capital text-center" style="vertical-align: middle;">Comment</th>';
								str1+='</tr>';
								str1+='<tbody>';
								  
									for(var i in result.subList3){
										str1+='<tr>';
										var moxCategoryLength = 0;
										if(result.subList3[i].subList != null && result.subList3[i].subList.length > 0){
										   moxCategoryLength = result.subList3[i].subList.length;
										}
											str1+='<td rowspan='+(moxCategoryLength)+'>'+result.subList3[i].tourDate+'</td>';
											if(result.subList3[i].subList != null && result.subList3[i].subList.length > 0){
												 var monthVO = result.subList3[i].subList[0];
													if(monthVO != null){
														 if(monthVO.tourCategory == null || monthVO.tourCategory == ""){
																str1+='<td> - </td>';
															}else{
																str1+='<td>'+monthVO.tourCategory+'</td>';
															}
															if(monthVO.tourType == null || monthVO.tourType == ""){
																str1+='<td> - </td>';
															}else{
																str1+='<td>'+monthVO.tourType+'</td>';
															}
															if(monthVO.count == null || monthVO.count==0){
																str1+='<td> - </td>';
															}else{
																str1+='<td class="text-center">'+monthVO.count+'</td>';
															}	
													}
											} 
												if(result.subList3[i].filePath != null && result.subList3[i].filePath.length > 0){
														var filePathArr = result.subList3[i].filePath.split(",");
														if(filePathArr != null && filePathArr.length > 0){
															str1+='<td rowspan='+(moxCategoryLength)+' style="position:relative">';
														for (var m = 0; m < filePathArr.length; m++) { 
																var fullName = filePathArr[m];
																var nameArr = fullName.split(".");
																var type = nameArr[1];
																if(type=="pdf" || type=="PDF"){
																  str1+='<span class="viewPdfCls" attr_filePath="'+fullName+'" style="cursor:pointer;"><img src="images/pdf.jpg" class="media-object" alt="" style="width:30px;"/></span>';
																}else if(type=="xls" ||type=="xlsx"){  
																  str1+='<span class="viewPdfCls" attr_filePath="'+fullName+'" style="cursor:pointer;"><img src="images/excel.jpg" class="media-object" alt="" style="width:30px;"/></span>';
																}else if(type=="doc" || type=="docx"){
																  str1+='<span class="viewPdfCls" attr_filePath="'+fullName+'" style="cursor:pointer;"><img src="images/word.jpg" class="media-object" alt="" style="width:30px;"/></span>';
																}else if(type != null){  
																  str1+='<span class="viewPdfCls"  attr_filePath="'+fullName+'" style="cursor:pointer;display:inline-block;"><img src="images/fileImage.png" class="media-object" alt="" style="width:30px;"/></span>';
																}           
															}
															str1+='<button class="btn btn-success editBtn candidateUpdate btn-xs" attr_monthdate="'+result.subList3[i].tourDate+'"  attr_monthId="'+result.subList3[i].id+'" style="position:absolute;right:20px;" >EDIT</button></td>';	
														}
													}else{    
														str1+='<td rowspan='+(moxCategoryLength)+'> - <button class="btn btn-success editBtn candidateUpdate btn-xs" attr_monthdate="'+result.subList3[i].tourDate+'" attr_monthId="'+result.subList3[i].id+'" style="position:absolute;right:20px;">EDIT</button></td>';  
													} 	
													
										str1+='</tr>';
											if(result.subList3[i].subList != null && result.subList3[i].subList.length > 0){
												var categoryList = result.subList3[i].subList;
													for(var k in categoryList){
														if(k == 0)
														continue;
														str1+='<tr>';
														if(categoryList[k].tourCategory == null || categoryList[k].tourCategory == ""){
															str1+='<td> - </td>';
														}else{
															str1+='<td>'+categoryList[k].tourCategory+'</td>';
														}
														if(categoryList[k].tourType == null || categoryList[k].tourType == ""){
															str1+='<td> - </td>';
														}else{
															str1+='<td>'+categoryList[k].tourType+'</td>';
														}
														if(categoryList[k].count == null || categoryList[k].count==0){
															str1+='<td style="position:relative"> - </td>';
														}else{
															str1+='<td style="position:relative" class="text-center">'+categoryList[k].count+'</td>';
														}
												 str1+='</tr>';
												}
											}
								
								  }
				
								str1+='</tbody>';
							str1+='</thead>';
						str1+='</table>';	
						if($(window).width() < 500)
						{
							str1+='</div>';
						}
					str1+='</div>';
				str1+='</div>';
			$("#tourIndividualDetailsTableBlock").html(str1); 
		}

	}
	
	$(document).on("change","#dateRangeSliderYear",function(){
		var getYear = $(this).val();
		$("#toursUpdateYear").dateRangeSlider("destroy");
		var months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"];
		$("#toursUpdateYear").dateRangeSlider({
			  bounds: {min: new Date(getYear, 0, 1), max: new Date(getYear, 11, 31)},
			//defaultValues: {min: new Date(2012, 1, 10), max: new Date(2012, 4, 22)},
			defaultValues: {min: new Date(getYear, 0,1), max: new Date(getYear,2,30)},
			scales: [{
			  first: function(value){ return value; },
			  end: function(value) {return value; },
			  next: function(value){
				var next = new Date(value);
				return new Date(next.setMonth(value.getMonth() + 1));
			  },
			  label: function(value){
				return months[value.getMonth()];
			  },
			  format: function(tickContainer, tickStart, tickEnd){
				tickContainer.addClass("myCustomClass");
			  }
			 }] 
		});
	});
	//getting individual canidate details on submit button of slider
	 $(document).on("click","#subMitBtn",function(){
			var candiateId = $(this).attr("attr_candidate_id");
			var fromDateDate = $(".ui-rangeSlider-leftLabel").find(".ui-rangeSlider-label-value").html(); 
			var toDateDate = $(".ui-rangeSlider-rightLabel").find(".ui-rangeSlider-label-value").html(); 
			var frmDateInRequiredFormat;
			var toDateInRequiredFormat;
			if(fromDateDate != null && fromDateDate.length > 0){
				var fromDateArr = fromDateDate.split("-");
				frmDateInRequiredFormat =fromDateArr[2].trim()+"/"+fromDateArr[1].trim()+"/"+fromDateArr[0].trim();
			}
			if(toDateDate != null && toDateDate.length > 0){
				var toDateArr = toDateDate.split("-");
				toDateInRequiredFormat =toDateArr[2].trim()+"/"+toDateArr[1].trim()+"/"+toDateArr[0].trim();
			}
			getIndividualRslBasedOnDateSelection(candiateId,frmDateInRequiredFormat,toDateInRequiredFormat);
	}); 
	 function getIndividualRslBasedOnDateSelection(candiateId,frmDateInRequiredFormat,toDateInRequiredFormat){
		
		$("#tourIndividualDetailsBlock").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		$("#tourIndividualDetailsTableBlock").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		$("#monthWiseComplainceDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jsObj ={ 
						 candiateId : candiateId,
						 fromDate :frmDateInRequiredFormat ,
						 toDate : toDateInRequiredFormat
					  }
			$.ajax({
				type : 'POST',
				url : 'getIndividualPersonTourDetailsAction.action',
				dataType : 'json',
				data : {task:JSON.stringify(jsObj)}
			}).done(function(result){
				$("#tourIndividualDetailsBlock").html('');
				$("#tourIndividualDetailsTableBlock").html('');
				$("#monthWiseComplainceDivId").html(' ');
				buildIndividualPersonTourDetails(result);
			});
	}
	$(document).on('click','.viewPdfCls',function(){
		var dbFilePath = $(this).attr("attr_filePath");         
		var str = ''; 
		var fileNameArr = dbFilePath.split(".");
		var extName = fileNameArr[1];
		if((navigator.userAgent.match(/iPhone/i)) ||  (navigator.userAgent.match(/iPad/i))) {
			$("#tourNewDocumentId").modal("hide");
			window.open('http://mytdp.com/Reports/tour_documents/'+dbFilePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
		}else{
			
			if(extName.trim()=="pdf" || extName.trim()=="PDF"){
				$('#tourNewDocumentId').modal({
					show: true,
					keyboard: false,
					backdrop: 'static'
				});
				
				
				str += '<iframe src="http://mytdp.com/Reports/tour_documents/'+dbFilePath+'" width="100%" height="800">';    
				str += '</iframe>';
			}
			if(extName.trim()=="jpg"){  
				$('#tourNewDocumentId').modal({
					show: true,
					keyboard: false,
					backdrop: 'static'
				});
				
				str += '<iframe src="http://mytdp.com/Reports/tour_documents/'+dbFilePath+'" width="100%" height="800">';    
				str += '</iframe>';
			}              
			if(extName.trim()=="doc" || extName.trim()=="docx"){
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
		}      
	}); 
 /*Dashboard retrival block End */
	

//Closing model when esc key has been pressed
  $('body').keypress(function(e){
    if(e.keyCode == 27){
          var isModalOpened = $("#tourNewDocumentId").attr("isModalOpened");
		   if(isModalOpened == "true"){
			   setTimeout(function(){
				 $('body').addClass("modal-open");
			   }, 500);      
			   $("#tourNewDocumentId").attr("isModalOpened","false");
           }
    }
  }); 
$(document).on("click",".tourIndividualCls",function(){
	setTimeout(function(){
		$("body").addClass("modal-open");
	},400);
});
$(document).on("click",".closeClass",function(){
	setTimeout(function(){
		$("body").addClass("modal-open");
	},400);
});


/*this block of code is not used presently */
	/* Block Start */
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
	
	$(document).on("click",".editTourRecordBtnCls",function(){
	
		$('#retrivalEditModalId').modal({
            show: true,
            keyboard: false,
            backdrop: 'static'
        });
		$("#retriveModalId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var detailsNewId = $(this).attr("attr_id");
		
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
				//buildNewTourRetrivalDetailsForNew(result,detailsNewId);
			}
		});
	});
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
	/* Block End */