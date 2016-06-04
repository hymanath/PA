	 
	 function validateFields(){
		 
		 var appDesigId=$("#manageAppDesigId").val();
		 var appPrrtyTypId= $("#manageAppTypeId").val();
		 var appStatusId=$("#manageAppStatusId").val();
		 
		 if(appDesigId==0){
		  $("#appDesigErrId").html("Please select Designation.");
           return;		  
		 }
		  $("#appDesigErrId").html(" ");
		 if(appPrrtyTypId==0){
		  $("#appPrrtyErrTypId").html("Please select Priority Type.");
           return;		  
		 }
		  $("#appPrrtyErrTypId").html(" ");
		 if(appStatusId==0){
		  $("#appStatusErrId").html("Please select Appointment Status.");
           return;		  
		 }
		 $("#appStatusErrId").html(" ");
    	 var isCheckedMonth=$("#mnthChckbxId").is(':checked');
	     var isCheckedAnyDate=$("#anyDtChckbxId").is(':checked');
		 var flag=true;
		 if(isCheckedAnyDate==false && isCheckedMonth==false){
			 $("#checkBoxErrId").html("Please select one checkbox."); 
			 flag=false;
			 return;
		 }
		 if(flag){
			 $("#checkBoxErrId").html(" ");
			  getAppntmntsCnddtDtls();
		 }
	 }
	 function getAppntmntsCnddtDtls(){
		 
		  var isCheckedMonth=$("#mnthChckbxId").is(':checked');
		  var isCheckedAnyDate=$("#anyDtChckbxId").is(':checked');
		  var currentMonth='';
		  var anyDate='';
	  
		   if(isCheckedMonth){
			  currentMonth=$("#mnthChckbxId").val();
		   }
		   if(isCheckedAnyDate){
			  anyDate=$("#anyDtChckbxId").val();
		   }
		   
    	var jsObj={
			candidateDsgntnId:$("#manageAppDesigId").val(),
			appntmntPrprtyId:$("#manageAppTypeId").val(),
			appntmntSttsId:$("#manageAppStatusId").val(),
			currentMonth:currentMonth,
			anyDate:anyDate
		  }
		  	$.ajax({
				type : 'POST',
				url : 'getAppntmntsCnddtDtlsAction.action',
				dataType : 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				if(result!=null && result!=0){
				  buildAppntmntCnddtDtlsRsult(result);
				}else{
				  $("#appntmntCnddtSttsUlId").html("<div><p style='color:green;font-size:20px'>No record available.")	
				}
		  }); 
	 }
   function buildAppntmntCnddtDtlsRsult(result){
	var str=''; 
	for(var i in result){
	    str+='<li>';
		 str+='<div class="row">';
			str+='<div class="col-md-12">';
				str+='<span class="requestedCheckbox">';
					str+='<input type="checkbox">';
				str+='</span>';
			str+='</div>';
			str+='<div class="col-md-6">';
				str+='<div class="media">';
					str+='<div class="media-left">';
						str+='<img class="media-object thumbnail" src="dist/Appointment/img/thumb.jpg" alt="...">';
						str+='<span class="colorStatus green"></span>';
					str+='</div>';
					str+='<div class="media-body">';
					str+='<p>'+result[i].name+'</p>';
						str+='<p>Contact Number:'+result[i].mobileNo+'</p>';
						str+='<p>Designation   :'+result[i].designation+'</p>';
						str+='<p>Constituency  :'+result[i].constituencyName+'</p>';
						str+='<p>Last Visit    :'+result[i].date+'</p>';
						str+='<p>Appt Type     :'+result[i].priority+'</p>';
						str+='<p>Purpose       :'+result[i].reason+'</p>';
					str+='</div>';
				str+='</div>';
				str+='<h4 class="m_top10"><b>PREVIOUS APPOINTMENT SNAPSHOT</b></h4>';
				str+='<table class="table table-bordered">';
				  if(result[i].appointStatusCountList!=null && result[i].appointStatusCountList.length>0){
				     str+='<tr>';					 
					 for(var j in result[i].appointStatusCountList){
						 str+='<td><h4>'+result[i].appointStatusCountList[j].statusCount+'</h4><p>'+result[i].appointStatusCountList[j].status+'</p></td>'; 
					  }
					  str+='</tr>';
				  }
				  str+='</table>';
			str+='</div>';
			str+='<div class="col-md-6">';
				str+='<h4>PREVIOUS APPOINTMENT REQUEST DETAILS</h4>';
				str+='<table class="table table-bordered m_top10">';
					str+='<thead>';
				        str+='<th>Appt Last Requested Date</th>';
						str+='<th colspan="2">Appt Status</th>';
					str+='</thead>';
				if(result[i].appointStatusRequestedList!=null && result[i].appointStatusRequestedList.length>0){
					for(var j in result[i].appointStatusRequestedList){
						str+='<tr>';
							str+='<td>'+result[i].appointStatusRequestedList[j].updatedTime+'</td>';
							str+='<td>'+result[i].appointStatusRequestedList[j].status+'</td>';
						str+='</tr>';					
					}
				}
				str+='</table>';
			str+='</div>';
		str+='</div>';
	str+='</li>';
	}
	$("#appntmntCnddtSttsUlId").html(str);
  }	 
	
	function getCandidateDesignation(){
		$.ajax({
			type : 'GET',
			url : 'getCandidateDesignation.action',
			dataType : 'json',
			data : {}
		}).done(function(result){ 
			if(result != null && result.length > 0){
				//app-appointment
				buildDesignationForCreateApp(result);
			}
			
		});
	}
	function buildDesignationForCreateApp(result){
			$("#designationListId  option").remove();
			$("#designationListId").append('<option value="0">Select Designation</option>');
			 $(".cloneDesignationCls option").remove(); 
			$(".cloneDesignationCls").append('<option value="0">Select Designation</option>'); 
			
			//$("#manageAppDesigId  option").remove();
			//$("#manageAppDesigId").append('<option value="select">Select Designation</option>');
			//$("#manageAppDesigId").append('<option value="0" selected>ALL</option>');
			for(var i in result){
				$("#designationListId").append('<option value='+result[i].id+' typeId='+result[i].orderId+'>'+result[i].name+'</option>');
				//$("#manageAppDesigId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
				$(".cloneDesignationCls").append('<option value='+result[i].id+' typeId='+result[i].orderId+'>'+result[i].name+'</option>');
			}
			 /* $(".designationListCls").dropkick();
			 var select = new Dropkick("#designationListId");
			select.refresh(); */ 
			 $("#manageAppDesigId").dropkick();
			var select1 = new Dropkick("#manageAppDesigId");
			select1.refresh();  
			
			
			
	} 
	

	function getAppointmentStatusList(){
			$.ajax({
			type : 'GET',
			url : 'getAppointmentStatusList.action',
			dataType : 'json',
			data : {}
		}).done(function(result){ 
			if(result != null && result.length > 0){
				buildAppointmentStatusList(result);
			}
			
		}); 
	}
		

	function buildAppointmentStatusList(result){
			$("#manageAppStatusId  option").remove();
			$("#manageAppStatusId").append('<option value="select">Select Appointment Status</option>');
			$("#manageAppStatusId").append('<option value="0" selected>ALL</option>');
			for(var i in result){
				$("#manageAppStatusId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
			$(".manageAppStatusCls").dropkick();
			var select = new Dropkick("#manageAppStatusId");
			select.refresh();
	}
	function buildAppointmentStatusList(result){
			for(var i in result){
				$("#selectStatusId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
			$(".dropkickClass").dropkick();
			var select = new Dropkick("#selectStatusId");
			select.refresh();
	}
	function getAppointmentPriority(){
		
		$.ajax({
			type : 'GET',
			url : 'getAppointmentPriority.action',
			dataType : 'json',
			data : {}
		}).done(function(result){ 
			if(result != null && result.length > 0){
				buildAppointmentPriorityList(result);
				
			}				
		});
	}
	function buildAppointmentPriorityList(result){
		$("#manageAppTypeId  option").remove();
		$("#manageAppTypeId").append('<option value="select">Select Priority</option>');
		$("#manageAppTypeId").append('<option value="0" selected>ALL</option>');
		$("#createAppTypeListId  option").remove();
		$("#createAppTypeListId").append('<option value="0">Select Appointment Priority Type</option>');
		for(var i in result){
			$("#manageAppTypeId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			$("#createAppTypeListId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		}
		$(".manageAppTypeCls").dropkick();
		var select = new Dropkick("#manageAppTypeId");
		select.refresh();
		
		var select1 = new Dropkick("#createAppTypeListId");
		select1.refresh();
	}
	
	$(document).on('click','#createNewLabelId',function(){
		$("#successDiv").html("");
		$("#successDiv").show();
		$("#errLabelName").html("");
		var lblName = $("#labelNameId").val();
		if(lblName=="" && lblName.length==0){
			$("#errLabelName").html("please enter label Name.").css("color","red");
			return;
		}
		
		var fromDate='';
		var toDate='';
		var dateStr = $("#modalDateId").val(); 
		if(dateStr !=null && dateStr.length>0){
			fromDate = dateStr.split("-")[0];
			toDate = dateStr.split("-")[1];
		}
		var aptuserId = $("#appointmentUserSelectBoxId").val();
		var jobj = {
			labelName	:	$("#labelNameId").val(),
			fromDate	:	fromDate,
			toDate		:	toDate,
			aptuserId	:	aptuserId
		}
		$.ajax({
			  type     : "POST",
			  url      : "createAppointmentLabel.action",
			  dataType : "json",
			  data     : {task:JSON.stringify(jobj)}
			}).done(function(result){
				if(result!=null){
					$("#successDiv").html(result.message).css("color","green");
					setTimeout(function(){	$("#successDiv").hide(); },3000);
					$("#labelNameId").val("");
					$("#createLabelModelId").modal('hide');
					getLabelDtls();
				}
		  });     
	});		 
		function getAppointmentUsersDtls(){
		$.ajax({
			  type:'GET',
			  url: 'getAppointmentUsersDtlsAction.action',
			  dataType: 'json',
			  data: {}
		}).done(function(result){
			if(result!=null && result!=0){
				buildAppntmntUsrSlctBx(result);
			}
		});
	}
	function buildAppntmntUsrSlctBx(result){
		$("#appointmentUserSelectBoxId  option").remove();
		for(var i in result){
			$("#appointmentUserSelectBoxId").append('<option attr_unique_code="'+result[i].date+'" value='+result[i].appointmentUserId+'>'+result[i].name+'</option>');
		}
		
		getSearchDetails(true);
	}
	$(document).on("click",".MngeAppntmntCls",function(){
		$(".commonDivCls").hide();
		$("#selectStsForLabelId").val(1);
		getLabelDtls();
		getAppointmentStatusOverview();
	});
	$('#appointmentUserSelectBoxId').change(function(){
		getLabelDtls();
		getAppointmentStatusOverview();
	});
	
	function getLabelDtls(){
		
		var slctDate='';
		var appntmntUsrId=$("#appointmentUserSelectBoxId").val();
		var status = $("#selectStsForLabelId").val();
		
		var jsObj={
			currentDate:slctDate,
			apptmntUsrId:appntmntUsrId,
			status: status
		}
		$.ajax({
			type : 'GET',
			url : 'getLabelDtlsAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){ 
			if(result!=null && result!=0){
				buildAppntmntLblResult(result);
			}else{
				$("#buildAppntmntLblTblId").html("<div><p style='color:green;font-size:20px'>No Data Available</p></div>")
			}
		}); 
	}
	function buildAppntmntLblResult(result){
	   var str='';
	  str+='<table class="table table-condensed bg_ff">';
		   str+='<thead>';
				 str+='<th>LABEL NAME</th>';
				 str+='<th>TOTAL</th>';
				 if(result[0].staticStatusList != null && result[0].staticStatusList.length > 0){
					 for(var i in result[0].staticStatusList){
						 if(result[0].staticStatusList[i].appointmentStatusId != 3 && result[0].staticStatusList[i].appointmentStatusId !=4)
							str+='<th>'+result[0].staticStatusList[i].status+'</th>';
					 }
				 }
				 str+='<th>LABEL STATUS</th>';
		   str+='</thead>';                                                      
			  str+='<tbody';                                                   
	  for(var i in result){
				str+='<tr>';
					str+='<td attr_label_id='+result[i].labelId+'>'+result[i].labelName+'</td>';
					var totalCount=0;
					if(result[i].statusList != null && result[i].statusList.length >0){
						for(var j in result[i].statusList){
							totalCount=totalCount+result[i].statusList[j].totalCount;
						}
					}
					str+='<td>'+totalCount+'</td>';
					if(result[i].statusList != null && result[i].statusList.length >0){
						for(var j in result[i].statusList){
							str+='<td>'+result[i].statusList[j].totalCount+'</td>';
						}
					}
					str+='<td>'+result[i].status+'</td>';
					str+='<td>';
					
					if(totalCount !=null && totalCount >0 ){
							str+='<button class="btn btn-success btn-xs viewMembersClass" attr_label_name="'+result[i].labelName+'" attr_label_id="'+result[i].labelId+'" title="View Appointments Of '+result[i].labelName+'" style="margin-right: 5px;">View</button>';
						}else{
							str+='<button class="btn btn-success btn-xs viewMembersClass" attr_label_name="'+result[i].labelName+'" attr_label_id="'+result[i].labelId+'" disabled style="margin-right: 5px;">View</button>';
						}
						
						str+='<button class="btn btn-success btn-xs addMembersClass" attr_label_name="'+result[i].labelName+'" title="Add Appointments To '+result[i].labelName+'" id="addApptsId" style="margin-right: 5px;">Add Appts</button>';
						if(totalCount !=null && totalCount >0 ){
							str+='<button class="btn btn-success btn-xs updateLableAppointmentsCls" attr_label_name="'+result[i].labelName+'" attr_label_id="'+result[i].labelId+'" title="Update Status Of Appointments" style="margin-right: 5px;">Update</button>';
						}else{
							str+='<button class="btn btn-success btn-xs updateLableAppointmentsCls" attr_label_name="'+result[i].labelName+'" attr_label_id="'+result[i].labelId+'" disabled style="margin-right: 5px;">Update</button>';
						}
						
						if(totalCount !=null && totalCount >0 ){
							str+='<button class="btn btn-success btn-xs labelStatusCls" attr_label_name="'+result[i].labelName+'" attr_label_id="'+result[i].labelId+'" attr_status="'+result[i].status+'" attr_status_id="'+result[i].statusId+'" title="Change The Status Of '+result[i].labelName+' Label" style="margin-right: 5px;">Status</button>';
						}else{
							str+='<button class="btn btn-success btn-xs labelStatusCls" attr_label_name="'+result[i].labelName+'" attr_label_id="'+result[i].labelId+'" attr_status="'+result[i].status+'" attr_status_id="'+result[i].statusId+'" disabled style="margin-right: 5px;">Status</button>';
						}
						if(totalCount !=null && totalCount >0 ){
							str+='<button class="btn btn-success btn-xs deleteAppointments" attr_label_name="'+result[i].labelName+'" attr_label_id="'+result[i].labelId+'" title="Delete Appointments Of '+result[i].labelName+'" style="margin-right: 5px;" id="delApptsScrollBarId">Del Appts</button>';
						}else{
							str+='<button class="btn btn-success btn-xs deleteAppointments" attr_label_name="'+result[i].labelName+'" attr_label_id="'+result[i].labelId+'" disabled style="margin-right: 5px;">Del Appts</button>';
						}
						
						
						str+='<i class="glyphicon glyphicon-remove lblDltCls" title="Delete Label '+result[i].labelName+'" attr_label_name="'+result[i].labelName+'" attr_label_id="'+result[i].labelId+'" style="color:red;cursor:pointer;"></i>';
						str+='<img src="images/search.gif" style="display:none;" id="ajaxImgForViewId'+i+'"></img>';
					str+='</td>';
			  str+='</tr>';
	  }
	  str+='</tbody>';   
	  str+='</table>';  
	  $("#buildAppntmntLblTblId").html(str);
	}
	var labelId='';
	var labelName='';
	$(document).on("click",".lblDltCls",function(){
	     labelId=$(this).attr("attr_label_id");
		 labelName=$(this).attr("attr_label_name");
		   showConfirmationBox();
	});
	$(document).on("click","#dlteLblBttnId",function(){
	 var isCheckedDelete=$("#dltChckbxMdlId").is(':checked');
	 var remarks = $("#remarksId").val();
     if(isCheckedDelete==true){
			$("#deleteLabelErr").html(" ");
		}else{
		 $("#deleteLabelErr").html("Please select agree to delete.");	
		 return;
	 }
	 if(remarks.length==0){
		 $("#deleteLabelErr").html("Please enter remarks.");	
		 return;
	 }else{
		 $("#deleteLabelErr").html(" ");
	 }
	 deleteAppointmentLabel(labelId,remarks);
	
		
	});
	function deleteAppointmentLabel(labelId,remarks){
		$("#appntmntLblDltSttsId").html(" ");
		$("#appntmntLblDltSttsId").show();
		var jsObj={
				labelId:labelId,   
				remarks:remarks
			}
			$.ajax({
				type : 'GET',
				url : 'deleteAppointmentLabelAction.action',
				dataType : 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				if(result!=null && result!=0){
					if(result.message=="success"){
					 $("#appntmntLblDltSttsId").html("<div><p style='color:red'>Label Deleted Successfully");	
                      setTimeout('$("#appntmntLblDltSttsId").hide()', 2000);					 
					}
					getLabelDtls();
				}
		  }); 
	}
	function showConfirmationBox(){
		 var str='';
		str+='<div class="modal fade" id="myModal" role="dialog">';
		  str+='<div class="modal-dialog modal-sm">';
				str+='<div class="modal-content">';
				  str+='<button style="margin-left:260px" type="button" class="btn btn-default" data-dismiss="modal">X</button>';
				  str+='<div class="modal-body text-center">';
				  str+='<p class="text-center">'+labelName+'</p>';
				  str+='<p class="text-center m_top10"><b>Are you sure want to delete Label ?</b></p>';
				  str+='<p class="text-center text-success m_top10">Current Status - INPROGRESS</p>';
				  str+='<label class="checkbox-inline text-center m_top10"><input id="dltChckbxMdlId" type="checkbox"/>Agree to delete</label>';
				  str+='<br>';
				  str+='<div class="m_top10">';
				  str+='<input type="text" class="form-control" name="" id="remarksId" placeholder="Remarks">';
				  str+='</div>';
				  str+='<p  style="color:red" id="deleteLabelErr"></p>';
				  str+='<input class="btn btn-success btn-block m_top10" type="button" id="dlteLblBttnId" value="DELETE"/>';
				  str+='<p>*Note : if you select (Agree to delete) total label remove  from appointment label list'; 
				  str+='</div>';
				str+='</div>';
			 str+='</div>';
		 str+='</div>';
		  $("#bldCnfrmtnMdlBoxId").html(str);
		  $("#myModal").modal("show");
	}
	

	
	 $(document).on("click","#searchAppointmentdetailsId",function(){
	   getAppointmentsBySearchCriteria($(this).attr("attr_label_name"));
    });
  function clearAppointmentsSearchFields(){
	  $("#appDesigErrId,#appPrrtyErrTypId,#appStatusErrId,#appDistErrId,#appConstErrId").html('');  
  }

  function getAppointmentsBySearchCriteria(labelName){
		  
		  clearAppointmentsSearchFields();
		  $("#appointmentRequestedMembersId").html('');  
		  
		var fromDate='';
		var toDate='';
		var dateStr = $("#addMembersFromDateId").val(); 
		if(dateStr !=null && dateStr.length>0){
			fromDate = dateStr.split("-")[0];
			toDate = dateStr.split("-")[1];
		}
		 
		 var designationId=$("#manageAppDesigId").val();
		 var priorityId= $("#manageAppTypeId").val();
		 var statusId=$("#manageAppStatusId").val();
		 var districtId = $("#manageAppDistId").val();
		 var constituencyId = $("#manageAppConstId").val();
		 var candidateTypeId = $("#candidateTypeAddSelId").val();
		 
		 if(candidateTypeId ==null && candidateTypeId.length == 0){
			  $("#addErrCandidateTypeAddCls").html("Select Candidate Type.");
				return;	
		 }
		 if(designationId=="select"){
		  $("#appDesigErrId").html("Select Designation.");
           return;		  
		 }		 
		  
		 if(priorityId=="select"){
		  $("#appPrrtyErrTypId").html("Select Priority Type.");
           return;		  
		 }
		 
		 if(statusId=="select"){
		  $("#appStatusErrId").html("Select Appointment Status.");
           return;		  
		 }
		 if(districtId=="select"){
		  $("#appDistErrId").html("Select District.");
           return;		  
		 }
		 if(constituencyId=="select"){
		  $("#appConstErrId").html("Select Constituency.");
           return;		  
		 }

		var radioValue = $("input[name='aptRequestedName']:checked").val();
		 
		 $("#ajaxImgForApntSearchId").show();
    	var jsObj={
			designationId:designationId,
			priorityId:priorityId,
			statusId:statusId,
			districtId:districtId,
			constituencyid:constituencyId,
			appointmentlabelId : appointmentlabelId,
			fromDate :fromDate,
			toDate:toDate,
			selUserId:$("#appointmentUserSelectBoxId").val(),
			candidateTypeId:candidateTypeId,
			dateType:radioValue,
			apptUserId:$("#appointmentUserSelectBoxId").val()
		  }
		  	$.ajax({
				type : 'POST',
				url : 'getAppointmentsBySearchCriteriaAction.action',
				dataType : 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				 $("#ajaxImgForApntSearchId").hide();
				$("#appointmentRequestedMembersId").show();
				if(result!=null && result!=0){
				  buidResult(result,labelName);
				}else{
					var str='';
					str+='<div class="block">';
					str+='<h4 class="text-success">APPOINTMENT REQUESTED MEMBERS To '+labelName+'</h4>';
					str+='<p class="m_top20" style="color:green;font-size:20px">No Data available.</p>';
					str+='</div>';
				  $("#appointmentRequestedMembersId").html(str);	
				}
		  }); 
	 }
  function buidResult(result,labelName){
		 var i = 0;
		 var str='';
		  str+='<div class="block">';
			 str+='<h4 class="text-success">Assign Appointments To '+labelName+'</h4>';
			
		 for(var i in result){
			  str+='<div class="panel panel-default manageAppViewPanelClass m_top10">';
				str+='<div class="panel-heading pad_5">';
				        if(result[i].labeled){
							str+='<div class="row">';
								str+='<div class="col-md-2">';
									str+='<span>Appointment ID: '+result[i].aptUniqueCode+'</span>';
								str+='</div>';
								str+='<div class="col-md-2">';
									if(result[i].priority !=null && result[i].priority.length>0){
										str+='<span>Priority : '+result[i].priority+'</span>';
									}else{
										str+='<span>Priority : - </span>';
									}
								str+='</div>';
								str+='<div class="col-md-3">';
									if(result[i].dateString !=null && result[i].dateString.length>0){
										str+='<span>Request Created Date : '+result[i].dateString.split(" ")[0]+'</span>';
									}else{
										str+='<span>Request Created Date : - </span>';
									}
								str+='</div>';
								str+='<div class="col-md-2 col-md-offset-3">';
									str+='<span>Current Status : '+result[i].status+'</span>';
									str+='<span data-toggle="tooltip" data-placement="top" title="Check this to assign a label" class="requestedCheckbox"><input class="appointmentcheckBoxClass" type="checkbox" value="'+result[i].appointmentId+'" checked></span>';
								str+='</div>';
							str+='</div>';
						}else{
							str+='<div class="row">';
								str+='<div class="col-md-2">';
									str+='<span>Appointment ID: '+result[i].aptUniqueCode+'</span>';
								str+='</div>';
								str+='<div class="col-md-2">';
									if(result[i].priority !=null && result[i].priority.length>0){
										str+='<span>Priority : '+result[i].priority+'</span>';
									}else{
										str+='<span>Priority : - </span>';
									}
								str+='</div>';
								str+='<div class="col-md-3">';
									if(result[i].dateString !=null && result[i].dateString.length>0){
										str+='<span>Request Created Date : '+result[i].dateString.split(" ")[0]+'</span>';
									}else{
										str+='<span>Request Created Date : - </span>';
									}
								str+='</div>';
								str+='<div class="col-md-2 col-md-offset-3">';
									str+='<span>Current Status : '+result[i].status+'</span>';
									str+='<span data-toggle="tooltip" data-placement="top" title="Check this to assign a label"  class="requestedCheckbox"><input class="appointmentcheckBoxClass" type="checkbox" value="'+result[i].appointmentId+'" ></span>';
								str+='</div>';
							str+='</div>';
						}
						
						if(result[i].subject !=null && result[i].subject.length>0){
							str+='<p>Purpose : '+result[i].subject+'</p>';
						}else{
							str+='<p>Purpose : - </p>';
						}
					
				str+='</div>';
				  str+='<div class="panel-body pad_5">';
					for(var j in result[i].subList){
						str+='<ul class="viewAppointmentRequestedMembers">';
							str+='<li>';
								str+='<div class="row">';
									str+='<div class="col-md-4">';
										str+='<div class="media">';
											str+='<div class="media-left">';
											str+='<img class="media-object thumbnail" src="'+result[i].subList[j].imageUrl+'" onerror="setDefaultImage(this);" alt="Candidate Image">';
											str+='</div>';
											str+='<div class="media-body">';
											if(result[i].subList[j].cadre == true){
												str+='<p>'+result[i].subList[j].name+' - Cadre</p>';
											}else{
												str+='<p>'+result[i].subList[j].name+'</p>';
											}if(result[i].subList[j].mobileNo != null && result[i].subList[j].mobileNo.length>0){
												str+='<p>Contact Number: '+result[i].subList[j].mobileNo+'</p>';
											}else{
												str+='<p>Contact Number: - </p>';
											}if(result[i].subList[j].designation !=null && result[i].subList[j].designation.length>0){
												str+='<p>Designation: '+result[i].subList[j].designation+'</p>';
											}else{
												str+='<p>Designation: - </p>';
											}if(result[i].subList[j].constituency !=null &&  result[i].subList[j].constituency.length>0){
												str+='<p>Constituency : '+result[i].subList[j].constituency+'</p>';
											}else{
												str+='<p>Constituency : - </p>';
											}
											str+='</div>';
										str+='</div>';
									str+='</div>';
									
									str+='<div class="col-md-8">';
									
									//history modal start
									 if(result[i].subList[j].candidateId != null && result[i].subList[j].candidateId > 0){
											str+='<a  title="Appointments History" data-toggle="tooltip" data-placement="top" class="historyShowModalBtn"  style="cursor:pointer;" attr-id="'+result[i].subList[j].candidateId+'" attr-name="'+result[i].subList[j].name+'" attr-designation="'+result[i].subList[j].designation+'" attr-mobile="'+result[i].subList[j].mobileNo+'"><i class="glyphicon glyphicon-time" style="color: rgb(142, 142, 142); font-size: 16px;"></i></a>&nbsp;&nbsp;';
									} 			
									//history modal end
									
										str+='<table class="table table-bordered table-condensed m_top10">';
										if(result[i].subList[j].subList != null && result[i].subList[j].subList.length>0){
											str+='<thead>';
											str+='<th>APPOINTMENT ID</th>';
											str+='<th>CREATED DATE</th>';
										    str+='<th>APPOINTMENT PREFERABLE DATES</th>';
											str+='<th>STATUS</th>';
												
											str+='</thead>';
											str+='<tbody>';
											
												for(var l in result[i].subList[j].subList){
													str+='<tr>';
													
													str+='<td>'+result[i].subList[j].subList[l].aptUniqueCode+'</td>';
													
													
													str+='<td>'+result[i].subList[j].subList[l].dateString+'</td>';
													
													if(result[i].subList[j].subList[l].dateTypeId !=null && result[i].subList[j].subList[l].dateTypeId >1){
														str+='<td>'+result[i].subList[j].subList[l].dateType+' : <span>'+result[i].subList[j].subList[l].minDate+' - '+result[i].subList[j].subList[l].maxDate+'</span></td>';
													}else if(result[i].subList[j].subList[l].dateTypeId !=null && result[i].subList[j].subList[l].dateTypeId ==1){
														str+='<td>'+result[i].subList[j].subList[l].apptpreferableDates+'</td>';
													}else{
														str+='<td>-</td>';
													}
													
													if(result[i].subList[j].subList[l].status !=null){
														str+='<td>'+result[i].subList[j].subList[l].status+'</td>';
													}else{
														str+='<td>-</td>';
													}													
													str+='</tr>';
												}
											}else{
													str+='<thead>';
													str+='<th>APPOINTMENT ID</th>';
													str+='<th>CREATED DATE</th>';
													str+='<th>APPOINTMENT PREFERABLE DATES</th>';
													str+='<th>STATUS</th>';
												
													str+='<tr>';
													str+='<td colspan="3"><center>No Data Available</center></td>';
													str+='</tr>';
											}
											
											str+='</tbody>';
										str+='</table>';
									str+='</div>';
								str+='</div>';
							str+='</li>';
						str+='</ul>';
						}
						
						str+='<div class=" m_top10">';
							str+='<span style="font-size: 16px;"><b>REQUESTED DATES : </b></span>';
							
							if(result[i].apptpreferableDates != null){							
								str+='<span>'+result[i].apptpreferableDates+'</span>';
							}else if(result[i].dateType != null && result[i].dateType.trim()!="" && result[i].maxDate != null && result[i].minDate != null){
								str+='<span>'+result[i].dateType+' ('+result[i].minDate+' to '+result[i].maxDate+') </span>';
							}else{
								str+='<span> - </span>';
							}							
						str+='</div>';
						
				  str+='</div>';
				str+='</div>';
		 }
		  str+='<button class="btn btn-success" id="updateLabelId" >Assign To Label</button>';
		  str+=' <span id="statusMsgAppntReqt"></span>';
		 str+='<div ><center ><img style="display: none;margin-top: -30px;" src="images/icons/loading.gif" id="updateMemberAjax"></center></div>';
		 str+='</div>';
		
		 $("#appointmentRequestedMembersId").html(str);
		 $('[data-toggle="tooltip"]').tooltip();
	 }
	 
	  $(document).on("click","#updateLabelId",function(){
	      addAppointmentsToLable();
      });
	 
	  function addAppointmentsToLable(){
		  $("#statusMsgAppntReqt").html("");
		var appointmentsArray = [];
		$('.appointmentcheckBoxClass').each(function(){
			if ($(this).is(':checked')){
				appointmentsArray.push( $(this).val() );
			 }
        });
		  
		if(appointmentsArray == null || appointmentsArray.length <= 0){
			$("#statusMsgAppntReqt").html("<center><h5 style='margin-top: -22px;color: red;'>Please Select Atleast One Appointment.</h5></center>");
			return;
		  }
		   $("#updateMemberAjax").css("display","block");
		  var jsObj={
				  	  apptLabelId:appointmentlabelId,
				  	  appointmentsArray:appointmentsArray
				  }
		  
		  	$.ajax({
				type : 'POST',
				url :  'addAppointmentstoLabelAction.action',
				dataType : 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				  $("#updateMemberAjax").css("display","none");
				if(result!=null && result!=0){
				  if(result.resultCode == 1){
					   setTimeout(function () {
						 $("#statusMsgAppntReqt").html("<center><h4 style='margin-top: -22px;color: green;'>Appointments Added To Label Successfully</h4></center>").fadeOut(4000);
						}, 500);
					    setTimeout(function() {$('html, body').animate({scrollTop:0}, 5000); },5000);
						 getLabelDtls();
				  }
				}else{
					setTimeout(function () {
						 $("#statusMsgAppntReqt").html("<center><h4 style='margin-top: -22px;color: green;'>Updation Failed..Try Later</h4></center>").fadeOut(4000);
						}, 500);
						setTimeout(function() {$('html, body').animate({scrollTop:0}, 5000); },5000);
				 }
		  }); 
	  }
	$(document).on("click","#viewAllAppointmentId",function(){
		var startIndex = 0;
		viewAllAppointment(startIndex,5);
	});
	function viewAllAppointment(startIndex,maxIndex){
		
		var aptUserId = $("#appointmentUserSelectBoxId").val();
	
		var jsObj={
				startIndex:	startIndex,
				maxIndex  :	maxIndex,
				aptUserId : aptUserId
			}  
		$.ajax({  
			type : 'GET',
			url : 'getAllAppointmentDetailsAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}                  
			}).done(function(result){ 
				buildAppointmentDetails(result); 
			});   
	}
	function buildAppointmentDetails(result){
		var str = '';
		str+='<table class="table table-condensed bg_ff" id="allMemberTableId">';
		str+='<thead>';
		str+='<th>APPOINTMENT UNIQUE CODE</th>';
		str+='<th>NAME</th>';
		str+='<th>CONTACT NUMBER</th>';
		str+='<th>DESIGNATION</th>';
		str+='<th>APPOINTMENT REQUESTED TIME</th>';
		str+='</thead>';
		for(var i in result){
		str+='<tr>';
		str+='<td>'+result[i].uniqueId+'</td>';
		str+='<td>'+result[i].name+'</td>';
		str+='<td>'+result[i].membershipNum+'</td>';
		str+='<td>'+result[i].designation+'</td>';
		str+='<td>'+result[i].date+'</td>';
		str+='</tr>';
		}	
		str+='</table>';
		$("#appointmentListId").html(str);
		$('#allMemberTableId').DataTable({
			responsive: true,
			"info":     false,
			"bSearching": false,
			"sDom": '<"bottom"flp><"clear">',
			"columnDefs": [{  "targets": 0 }],		
			"bPaginate": false,
			"bLengthChange": false,
			"bAutoWidth": false,
			
		});
		var total=result[0].count;
		if(total> 5){
			$("#paginationDivId").pagination({
			items: total,
			itemsOnPage: 5,
			cssStyle: 'light-theme',
			onPageClick: function(pageNumber, event) {
				var num=(pageNumber-1)*5;
				viewAllAppointment(num,5);
				}
				});
		}
	}
	
	$("#appointmentReqBlock").hide();
	
	$(document).on("click","#viewAllAppointmentId",function(){
		$("#appointmentReqBlock").show();
		$("#allAppointmentsHideBlock").hide();
	});
	
	$(document).on("click","#backToReqBlock",function(){
		$("#appointmentReqBlock").hide();
		$("#allAppointmentsHideBlock").show();
	});
	
	$(document).on("click",".updateLableAppointmentsCls",function(){
		$("#updateLabelNameSpanId").text($(this).attr("attr_label_name"));
		var jsObj={
			labelId : $(this).attr("attr_label_id")
		}
		  	$.ajax({
				type : 'POST',
				url : 'getAppointmentsOfALableForUpdateAction.action',
				dataType : 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				if(result != null && result.length > 0){
					var str = ''
					for(var i in result){
						str+='<div class="panel panel-default manageAppViewPanelClass">';
							str+='<div class="panel-heading">';
								str+='<div class="row">';
									str+='<div class="col-md-12">';
										str+='<span class="requestedCheckboxPanel text-danger" id="apptStatus'+result[i].appointmentId+'">'+result[i].status+'</span>';
									str+='<div class="col-xs-5">';
										str+='<p>Purpose : '+result[i].reason+'</p>';
										str+='<p>Priority Type : '+result[i].priority+'</p>';
									str+='</div>';
									str+='<div class="col-xs-7">';
									str+='<div class="col-xs-5">';
											str+='<label>Update Status</label>';
											str+='<select class="form-control upadteAppntStatusCls" id="upadteAppntStatus" attr_appnt_id="'+result[i].appointmentId+'">';
											str+='<option value="0"> Select Status</option>';
											str+='<option value="5"> Reschedule</option>';
											str+='<option value="6"> Cancelled</option>';
											str+='</select>';
										str+='</div>';
										str+='<div class="col-xs-7" id="appointmentStatusMsg'+result[i].appointmentId+'" style="margin-top: 25px;"></div>';
									str+='</div>';
									
									str+='</div>';
								str+='</div>';
								
									
							   str+='</div>';
							
							str+='<div class="panel-body">';
								
								str+='<ul class="updateLabelMembers" style="list-style-type: none;">';
									
									if(result[i].basicInfoList != null && result[i].basicInfoList.length > 0){
										for(var j in result[i].basicInfoList){
											str+='<li>';
												str+='<div class="row">';
													str+='<div class="col-md-5">';
														str+='<div class="media">';
															str+='<div class="media-left">';
																str+='<img class="media-object thumbnail" src="'+result[i].basicInfoList[j].imageUrl+'" onerror="setDefaultImage(this);" alt="Candidate Image">';
															str+='</div>';
															str+='<div class="media-body">';
																str+='<p>'+result[i].basicInfoList[j].name+'';
																if(result[i].basicInfoList[j].membershipNum != null)
																	str+='- Cadre</p>';
																else
																	str+='</p>';
																if(result[i].basicInfoList[j].mobileNo !=null && result[i].basicInfoList[j].mobileNo.length>0){
																	str+='<p>Contact Number: '+result[i].basicInfoList[j].mobileNo+'</p>';
																}else{
																	str+='<p>Contact Number: - </p>';
																}
																
																str+='<p>Designation: '+result[i].basicInfoList[j].designation+'</p>';
															str+='</div>';
														str+='</div>';
													str+='</div>';
												str+='</div>';
											str+='</li>';
										}
									}
									
								str+='</ul>';
								str+='<p class="m_top10">Appt Created By: '+result[i].userName+' &nbsp;&nbsp;&nbsp;&nbsp; <img src="dist/Appointment/img/message.png" class="messageIcon" title="Send Sms" data-toggle="tooltip" data-placement="top"></p>';
								str+='<div class="messageBlock arrow_box">';
				                str+='<span class="errorCls msgDiv1'+result[i].appointmentId+'"></span>';
				                str+='<textarea class="form-control sendSms'+result[i].appointmentId+'" ></textarea>';
				                str+='<button class="btn btn-success btn-block sendsms" value="'+result[i].appointmentId+'">SEND SMS</button>';
				               str+='</div>';
							str+='</div>';
						str+='</div>';
						
					}
					str+='<div class="col-md-12">';
						str+='<button class="btn btn-success" id="upStatusBtnId">UpDate</button>';
						str+='<div id="updateStatusErrDivId"></div>';	
					str+='</div>';
					
					$("#updateAppointmentsForLabelDivId").html(str);
				}else{
					$("#updateAppointmentsForLabelDivId").html("<span style='color:green;font-size:20px'>No Data Available.</span>");
				}
				
			});
		$(".commonDivCls").hide();
		$(".appointmentsUpdateDivCls").show();
	});
	
	$(document).on("click",".viewMembersClass",function(){
		var dynamicViewAjaxId=$(this).parent().find("img").attr("id");
		
		$("#"+dynamicViewAjaxId).show();
		$(".commonDivCls").hide();
		
		var labelName = $(this).attr("attr_label_name");
		
		var jsObj={
			labelId : $(this).attr("attr_label_id"),
			callFrom : "",
			apptuserId:$("#appointmentUserSelectBoxId").val()
		}
		
		$.ajax({
			type : 'POST',
			url : 'viewAppointmentsOfALableAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#"+dynamicViewAjaxId).hide();
			$(".appointmentsViewDivCls").show();
			if(result!=null && result!=0){
			  buildViewResult(result,labelName,jsObj.labelId,jsObj);
			}else{
			  $(".appointmentsViewDivCls").html("<div class='col-md-12'><div class='block'><h4 class='text-success' style='margin-bottom:10px;'>"+labelName +" MEMBERS</h4><center><p style='color:green;font-size:20px'>No Data available.</p></center></div></div>");	
			}
		});		
	});
	
	
	function printMembersForView(labelId,labelName)
	{
		
		$(".commonDivCls").hide();
		
		var labelName =labelName;
		var jsObj={
			labelId :labelId,
			callFrom : "print",
			labelName:labelName,
			apptuserId:$("#appointmentUserSelectBoxId").val()
		}
		$.ajax({
			type : 'POST',
			url : 'viewAppointmentsOfALableAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$(".appointmentsViewDivCls").show();
			if(result!=null && result!=0){
				buildViewResult(result,labelName,labelId,jsObj);
				}else{
			  $(".appointmentsViewDivCls").html("<div class='col-md-12'><div class='block'><h4 class='text-success' style='margin-bottom:10px;'>"+labelName +" MEMBERS</h4><center><p style='color:green;font-size:20px'>No Data available.</p></center></div></div>");	
			}
		});		
	}

	function buildViewResult(result,labelName,labelId,jsObj){
		var i = 0;
		var str='';
			str+='<div class="col-md-12">';
			str+='<div class="block">';
			str+='<table id="viewAllMembersId">';
			str+='<thead><th></th></thead>';
			str+='<tbody>';
			if(result[0].pdfPath != null && jsObj.callFrom == "print")
			str+='<a  id="pdffBtn" class="text-success" style="margin-bottom:10px;float:right;color:#fff;" value="Download" href="appointmentPdf/'+result[0].pdfPath+'" download  target_blank>Download</a>';
			str+='<input type="button" class="text-success" style="margin-bottom:10px;float:right;color:#fff;" value="Print" onClick="printMembersForView(\''+labelId+'\',\''+labelName+'\');"></input>';
			str+='<h4 class="text-success" style="margin-bottom:10px;">'+labelName +' MEMBERS</h4>';
			
			for(var i in result){
				str+='<tr><td>';
				str+='<div class="panel panel-default manageAppViewPanelClass">';
				str+='<div class="panel-heading pad_5">';
				    str+='<div class="row">';
						str+='<div class="col-md-2">';
							str+='<span>Appointment ID: '+result[i].aptUniqueCode+'</span>';
						str+='</div>';
						str+='<div class="col-md-2">';
							if(result[i].priority !=null && result[i].priority.length>0){
								str+='<p>Priority : '+result[i].priority+'</p>';
							}else{
								str+='<p>Priority : - </p>';
							}
						str+='</div>';
						str+='<div class="col-md-3">';
							if(result[i].dateString !=null && result[i].dateString.length>0){
								str+='<p>Request Created Date : '+result[i].dateString.split(" ")[0]+'</p>';
							}else{
								str+='<p>Request Date : - </p>';
							}
						str+='</div>';
						str+='<div class="col-md-2 col-md-offset-3">';
							//str+='<input type="button" class="text-danger" value="Print" onClick="printMembersForView(\''+labelId+'\',\''+labelName+'\');"></input>';
							str+='<span class="requestedCheckboxPanel text-danger">'+result[i].status+'</span>';
						str+='</div>';
					str+='</div>';
					if(result[i].subject !=null && result[i].subject.length>0){
						str+='<p>Purpose : '+result[i].subject+'</p>';
					}else{
						str+='<p>Purpose : - </p>';
					}	
					
				str+='</div>';
				str+='<div class="panel-body pad_5">';
					for(var j in result[i].subList){
						str+='<ul class="viewAppointmentRequestedMembers">';
							str+='<li>';
								str+='<div class="row">';
									
									str+='<div class="col-md-4">';
										str+='<div class="media">';
											str+='<div class="media-left">';
												str+='<img class="media-object thumbnail" src="'+result[i].subList[j].imageUrl+'" onerror="setDefaultImage(this);" alt="Candidate Image">';
											str+='</div>';
											str+='<div class="media-body">';
											if(result[i].subList[j].cadre == true){
												str+='<p>'+result[i].subList[j].name+' - Cadre</p>';
											}else{
												str+='<p>'+result[i].subList[j].name+' </p>';
											}if(result[i].subList[j].mobileNo !=null && result[i].subList[j].mobileNo.length>0){
													str+='<p>Contact Number: '+result[i].subList[j].mobileNo+'</p>';
											}else{
													str+='<p>Contact Number: - </p>';
											}if(result[i].subList[j].designation !=null && result[i].subList[j].designation.length>0){
												str+='<p>Designation: '+result[i].subList[j].designation+'</p>';
											}else{
												str+='<p>Designation: - </p>';
											}if(result[i].subList[j].constituency !=null && result[i].subList[j].constituency.length>0){
												str+='<p>Constituency : '+result[i].subList[j].constituency+'</p>';
											}else{
												str+='<p>Constituency : - </p>';
											}											
											str+='</div>';
										str+='</div>';
										
											
									str+='</div>';
									str+='<div class="col-md-8">';									
									//history modal start
									 if(result[i].subList[j].candidateId != null && result[i].subList[j].candidateId > 0){
											str+='<a  title="Appointments History"  data-toggle="tooltip" data-placement="top" class="historyShowModalBtn"  style="cursor:pointer;" attr-id="'+result[i].subList[j].candidateId+'" attr-name="'+result[i].subList[j].name+'" attr-designation="'+result[i].subList[j].designation+'" attr-mobile="'+result[i].subList[j].mobileNo+'"><i class="glyphicon glyphicon-time m_top20" style="color: rgb(142, 142, 142); font-size: 16px;"></i></a>&nbsp;&nbsp;';
									} 
									
									str+='<div class=" displayrow " style="margin-top: -25px;">';
										
										if(result[i].subList[j].subList != null && result[i].subList[j].subList.length>0){
											str+='<ul class="row">';
											str+='<li  style=" width: 116px;padding: 8px !important;" class="col-xs-2 alignmentprefrabledates">APPOINTMENT ID</li>';
											str+='<li	 style="width: 115px;padding: 8px !important;" class="col-xs-1 alignmentprefrabledates">CREATED DATE</li>';
											str+='<li  style="width:315px;padding: 8px !important;" class="col-xs-3 alignmentprefrabledates">APPOINTMENT PREFERABLE DATES</li>';
											str+='<li  style="padding: 8px !important;width: 110px;"class="col-xs-1 alignmentprefrabledates">STATUS</li>';
											str+='</ul>';
											for(var l in result[i].subList[j].subList){
												
												str+='<ul class="row datespadding" style="margin-top: -10px; margin-bottom: 0px;">';
												str+='<li style="width: 116px;padding: 8px !important;" class="col-xs-1 alignmentprefrabledates">'+result[i].subList[j].subList[l].aptUniqueCode+'</li>';
												str+='<li style="width: 115px;padding: 8px !important;" class="col-xs-1 alignmentprefrabledates">'+result[i].subList[j].subList[l].dateString+'</li>';
												if(result[i].subList[j].subList[l].dateTypeId !=null && result[i].subList[j].subList[l].dateTypeId >1){
														str+='<li  style="width: 315px;padding: 8px !important;" class="col-xs-3 alignmentprefrabledates">'+result[i].subList[j].subList[l].dateType+' : <span>'+result[i].subList[j].subList[l].minDate+' - '+result[i].subList[j].subList[l].maxDate+'</span></li>';
													}else if(result[i].subList[j].subList[l].dateTypeId !=null && result[i].subList[j].subList[l].dateTypeId ==1){
														str+='<li  style="width:315px;padding: 8px !important;" class="col-xs-3 alignmentprefrabledates">'+result[i].subList[j].subList[l].apptpreferableDates+'</li>';
													}else{
														str+='<li style="width:315px;padding: 8px !important;"  class="col-xs-3 alignmentprefrabledates">-</li>';
													}
													
												if(result[i].subList[j].subList[l].status !=null){
														str+='<li  style="padding: 8px ! important; width: 110px;" class="col-xs-1 alignmentprefrabledates">'+result[i].subList[j].subList[l].status+'</li>';
													}else{
														str+='<li  style="padding: 8px ! important; width: 110px;" class="col-xs-1 alignmentprefrabledates">-</li>';
													}		
												
												str+='</ul>';
											}
										}else{
														str+='<ul class="row">';
														str+='<li  style=" width: 116px;padding: 8px !important;" class="col-xs-2 alignmentprefrabledates">APPOINTMENT ID</li>';
														str+='<li	 style="width: 119px;padding: 8px !important;" class="col-xs-1 alignmentprefrabledates">CREATED DATE</li>';
														str+='<li  style="width:238px;padding: 8px !important;" class="col-xs-3 alignmentprefrabledates">APPOINTMENT PREFERABLE DATES</li>';
														str+='<li  style="padding: 8px !important;width: 117px;"class="col-xs-1 alignmentprefrabledates">STATUS</li>';
														str+='</ul>';
												
													str+='<ul class="row">';
														str+='<li  style=" width: 116px;padding: 8px !important;" class="col-xs-2 alignmentprefrabledates">No Data Available</li>';
														str+='<li	 style="width: 119px;padding: 8px !important;" class="col-xs-1 alignmentprefrabledates">No Data Available</li>';
														str+='<li  style="width:238px;padding: 8px !important;" class="col-xs-3 alignmentprefrabledates">No Data Available</li>';
														str+='<li  style="padding: 8px !important;width: 117px;"class="col-xs-1 alignmentprefrabledates">No Data Available</li>';
														str+='</ul>';
											}
										str+='</div>';
	
									//history modal end
										
									str+='</div>';
								str+='</div>';
							str+='</li>';
						str+='</ul>';
						}
						str+='<div class=" m_top10">';
							str+='<span style="font-size: 16px;"><b>REQUESTED DATES :</b></span>';
							if(result[i].apptpreferableDates != null && result[i].dateTypeId == 1){
								str+='<span>'+result[i].apptpreferableDates+'</span>';
							}else if(result[i].apptpreferableDates == null && result[i].dateTypeId > 1){ 
								str+='<span>'+result[i].dateType+' ( '+ result[i].minDate +' to '+result[i].maxDate+')</span>';
							}else{
								str+='<span> - </span>';
							}
							
						str+='</div>';
					
						
				  str+='</div>';
				str+='</div>';
				str+='</td></tr>';
					
			}
			str+='</div>';
			str+='</div>';
			str+='</tbody></table>';
			
		$(".appointmentsViewDivCls").html(str);
		$('[data-toggle="tooltip"]').tooltip();
		$("#viewAllMembersId").dataTable();
		
	}
	
	$(document).on("click",".cnfrmaptsCls",function(){
		
	   $("#appointmentDateSlotHeadingId").daterangepicker({singleDatePicker:true});
       $('#appointmentDateSlotHeadingId').val(moment().format('MM/DD/YYYY'));
	   $("#appointmentDateSlotId").daterangepicker({singleDatePicker:true,minDate:new Date()});
       $('#appointmentDateSlotId').val(moment().format('MM/DD/YYYY'));
	   
		$(".changeClass").removeClass("col-md-8");
		$(".changeClass").addClass("col-md-12");
		getAppointmentStatusOverview();	
		//Set Button disabling
		$('#setTimeSlotBtnId').attr('disabled',true);

		//clearing the Div Area and setting default time format		
		$("#confirmAppointmentBlockDropId").empty();
		$("#confirmAppointmentBlockDropId").html("<h4 class='deleteTag'>DROP HERE</h4>");
		   $("#fromTimeId").val("00:00 AM");
		   $("#toTimeId").val("00:00 PM");
		   $("#commentTxt").val("");
	
    	getTimeSlotsForADayByAppytUserId();
		getAllScheduledApptsByDate();
		
	});
	
	function getAppointmentLabels(){
		
		var aptUserId = $("#appointmentUserSelectBoxId").val();
		var jsObj = {
			aptUserId:aptUserId
		}
		$.ajax({
		type : 'GET',
		url : 'getAppointmentLabelsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
		}).done(function(result){ 
		if(result!=null && result!=0){
			buildAppointmentLabel(result);
		}else{
			$("#appointmentLabelToGetSlotsId").html("<option value='0'>Select Label</option>");
			var select = new Dropkick("#appointmentLabelToGetSlotsId");
			select.refresh();
		}
		
	});     
	}
	function buildAppointmentLabel(result){
		var str='';
			str+='<option value="0">Select Label</option>';
			for(var i in result){
				str+='<option value='+result[i].id+'>'+result[i].name+'</option>';
			}
			$("#appointmentLabelToGetSlotsId").html(str);
			$("#appointmentLabelToGetSlotsId").dropkick();
			var select = new Dropkick("#appointmentLabelToGetSlotsId");
			select.refresh();
	} 
	$("#pluginTableId").hide();
	$("#showTimeSlotsId").click(function(){
		
		$("#timeSlotsErrId").html("");

		var appointmentLabelId = $("#appointmentLabelToGetSlotsId").val();
		if(appointmentLabelId==0){
			$("#timeSlotsErrId").html("<span style='color:red'>Please select a label</span>");
			return;
		}
		$("#apptRqstMemberAjax").show();
		//View Details Of Appointments call
		getViewAppointmentsOfALable();
		//get appointments of a lable
		getAppointmentsOfALabel();
		
		var jsObj = {
		appointmentLabelId:appointmentLabelId
	}
	$.ajax({
		type : 'GET',
		url : 'getTimeSlotsDetailsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){
		$("#apptRqstMemberAjax").hide();
		if(result!= null && result.length!=0 ){
			$("#pluginTableId").show();
			buildTimeSlotsTable(result);
			$(".changeClass").removeClass("col-md-12")
			$(".changeClass").addClass("col-md-8")
			$("#timeSlotsWarnId").hide();
		}else{
			$(".changeClass").removeClass("col-md-12")
			$(".changeClass").addClass("col-md-8")
			$("#pluginTableId").hide();
			$("#timeSlotsWarnId").show();
		}
		});
		var user = $("#appointmentUserSelectBoxId").text();
	});
	
	function getAppointmentsOfALabel(){
		$("#confirmAppointmentsAjaxImg").show();
		var jsObj={
			labelId : $("#appointmentLabelToGetSlotsId").val(),
			callFrom : "timeSlot",
			apptuserId:$("#appointmentUserSelectBoxId").val()
		}
		
		$.ajax({
			type : 'POST',
			url : 'viewAppointmentsOfALableAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result!=null && result!=0){
			  buildLabelResult(result,labelName);
			}else{
			  $("#confirmAppointmentsAjaxImg").hide();
			  $("#confirmAppointmentsDivId").html("<div class='col-md-4'><div class='block'><div><p style='color:green;font-size:20px'>No Data available.</p></div></div></div>");	
			}
		});
	}
	
	function buildLabelResult(result,labelName){
		
		setcolorsForStatus();
		var i = 0;
		var str='';
		
			str+='<div class="col-md-4 block  m_top30" >';
			str+='<div >';
			str+='<table id="confirmAppointmentsdt"  >';
			str+='<thead>';
			str+='<th></th>';
			str+='</thead>';
			str+='<tbody id="dragId" >';
		for(var i in result){
			str+='<tr class="newClass manageAppViewPanelClass" >';
			str+='<td>';
				str+='<div class="panel panel-default appointmentCls" attr_appointment_id='+result[i].appointmentId+'>';//manageAppViewPanelClass
				str+='<div class="panel-heading">';
				    str+='<div class="row">';
						str+='<div class="col-md-12">';
						var color = getColorCodeByStatus(result[i].status);
						str+='<span class="requestedCheckboxPanel text-danger" style="margin-right:25px;color:'+color+'">'+result[i].status+'</span>';
						str+='<span class="requestedCheckboxPanel hidelabel"><i class="glyphicon glyphicon-remove"></i></span>';
						str+='</div>';
					str+='</div>';
					str+='<b>ID: '+result[i].aptUniqueCode+'</b>&nbsp;&nbsp;&nbsp;';
					if(result[i].priority !=null && result[i].priority.length>0){
						str+='<p>Priority : '+result[i].priority+'</p>';
					}					
					if(result[i].dateString !=null && result[i].dateString.length>0){
						str+='<p>Created Date : '+result[i].dateString.split(" ")[0]+'</p>';
					}
					
					if(result[i].subject !=null && result[i].subject.length>0){
						str+='<p>Purpose : '+result[i].subject+'</p>';
					}
					
				str+='</div>';
				str+='<div class="panel-body pad_5">';
					str+='<ul class="viewAppointmentRequestedMembers">';
					for(var j in result[i].subList){
							str+='<li>';
								str+='<div class="row">';
									str+='<div class="col-md-12">';
										str+='<span class="requestedCheckbox text-success"></span>';
									str+='</div>';
									str+='<div class="col-md-12">';
										str+='<div class="media">';
											str+='<div class="media-left">';
												if(result[i].subList[j].imageUrl != null && result[i].subList[j].imageUrl.length > 0)
													str+='<img class="media-object thumbnail" src="'+result[i].subList[j].imageUrl+'" onerror="setDefaultImage(this);" alt="Candidate Image">';
												else
													str+='<img class="media-object thumbnail" src="dist/Appointment/img/thumb.jpg" onerror="setDefaultImage(this);" alt="Candidate Image">';
											//	str+='<span class="colorStatus green"></span>';
											str+='</div>';
											str+='<div class="media-body">';
												str+='<p>'+result[i].subList[j].name+'</p>';
												
												if(result[i].subList[j].mobileNo !=null && result[i].subList[j].mobileNo.length>0){
													str+='<p> <i class="fa fa-mobile" style="font-size:15px"></i> &nbsp;'+result[i].subList[j].mobileNo+'</p>';
												}
												
												str+='<p>Designation: '+result[i].subList[j].designation+'</p>';
												if(result[i].subList[j].candidateTypeId !=null && 
													result[i].subList[j].candidateTypeId >0 && result[i].subList[j].candidateTypeId != 1)
														if(result[i].subList[j].constituency!=null && result[i].subList[j].constituency.trim().length>0){
															str+='<p>Constituency : '+result[i].subList[j].constituency+'</p>';
														}				
											str+='</div>';
										str+='</div>';
										//history modal start
										if(result[i].subList[j].candidateId != null && result[i].subList[j].candidateId > 0){
											str+='<a  title="click here to view '+result[i].subList[j].name+' history"  data-toggle="tooltip" data-placement="top"class="historyshowmodalbtn"  style="cursor:pointer;" attr-id="'+result[i].subList[j].candidateId+'" attr-name="'+result[i].subList[j].name+'" attr-designation="'+result[i].subList[j].designation+'" attr-mobile="'+result[i].subList[j].mobileNo+'"><i class="glyphicon glyphicon-time" style="color: rgb(142, 142, 142); font-size: 16px;"></i></a>&nbsp;&nbsp;';
									} 
									str+='</div>';
								str+='</div>';
							str+='</li>';
						}
						str+='<div class="m_top10">';
							str+='<span style="font-size: 16px;"><b>REQUESTED DATES :</b></span>';
							
							if(result[i].apptpreferableDates != null){							
								str+='<span>'+result[i].apptpreferableDates+'</span>';
							}else if(result[i].dateType != null && result[i].dateType.trim() != "" && result[i].minDate != null && result[i].maxDate != null){
								str+='<span>'+result[i].dateType+' ('+result[i].minDate+' to '+result[i].maxDate+')</span>';
							}else{
								str+='<span> - </span>';
							}							
							str+='<img src="dist/Appointment/img/reqHistoryicon+.png" class="pull-right statusTrackingModalbtn" attr-id='+result[i].appointmentId+' attr-aptName='+result[i].aptUniqueCode+' alt="ViewReqHistory" style="height:16px;cursor:pointer;margin-right:5px;" title="Appointment Requested History" data-toggle="tooltip" data-placement="top"/>';
							
						str+='</div>';
						 
					str+='</ul>';	
				  str+='</div>';
				str+='</div>';
				str+='</td>';
				str+='</tr>';
			}
				
			str+='</tbody>';
			str+='</table>'
			str+='</div>';
			str+='</div>';
		$("#confirmAppointmentsAjaxImg").hide();
		$("#confirmAppointmentsDivId").html(str);
		$('#confirmAppointmentsdt').DataTable({
			responsive: true,
			"info":     false,
			"bSearching": true,
			 "scrollY":   "625px",	
			"bPaginate": false,
			"bLengthChange": false,
			"bAutoWidth": false,
			
		});
		$(".custom-scroll-ins").mCustomScrollbar();
		$(".mCSB_dragger_bar").css("background-color","#000");
		$(document).on("click",".historyshowmodalbtn",function(){
			$("#appCandidateNameId").html('');
			$(".historyShowModal").modal("show");
			var id = $(this).attr("attr-id");
			var name = $(this).attr("attr-name");
			var designation = $(this).attr("attr-designation");
			var mobile = $(this).attr("attr-mobile");
		if(designation != null && designation.length > 0)
			$("#appCandidateNameId").html(''+name+' ('+designation+') - '+mobile+'');
		else
			$("#appCandidateNameId").html(''+name+' - '+mobile+'');
			getAppointStatusOverviewforCandidate(id);
			getAppointmentHistoryForCandidate(id);
		});
		Sortable.create(dragId,{
			  filter: '.js-remove',
			  onFilter: function (evt) {
				evt.item.parentNode.removeChild(evt.item);
			  },
			  setData: function (dataTransfer, dragEl) {
				dataTransfer.setData('Text', dragEl.textContent);
			  },
			  group: { name: "confirmAppointmentsBlock", put: false, pull: true },
			  animation: 150,
			  store: {
				get: function (sortable) {
				  var order = localStorage.getItem(sortable.options.group);
				  return order ? order.split('|') : [];
				},
				set: function (sortable) {
				  var order = sortable.toArray();
				  localStorage.setItem(sortable.options.group, order.join('|'));
				}
			  },
			  onAdd: function (evt){console.log('onAdd.editable:', [evt.item, evt.from]);},
			  onUpdate: function (evt){ console.log('onUpdate.editable:', [evt.item, evt.from]); },
			  onRemove: function (evt){ console.log('onRemove.editable:', [evt.item, evt.from]); },
			  onStart:function(evt){ console.log('onStart.editable:', [evt.item, evt.from]);},
			  onSort:function(evt){ console.log('onStart.editable:', [evt.item, evt.from]);},
			  onEnd: function(evt){ console.log('onEnd.editable:', [evt.item, evt.from]);}
		  });		
	}
	
	$(document).on("click",".labelStatusCls",function(){		
	     showStatusBox($(this).attr("attr_label_id"),$(this).attr("attr_label_name"),$(this).attr("attr_status"),$(this).attr("attr_status_id"));
	});
	
	function showStatusBox(labelId,labelName,labelStatus,statusId){
		 var str='';
		str+='<div class="modal fade" id="myModalId" role="dialog">';
		  str+='<div class="modal-dialog modal-sm">';
				str+='<div class="modal-content">';
				  str+='<button style="margin-left:260px" type="button" class="btn btn-default" data-dismiss="modal">X</button>';
				  str+='<div class="modal-body text-center">';
				  str+='<p class="text-center">'+labelName+'</p>';
				  str+='<p class="text-center m_top10"><b>Are you sure you want to change label status ?</b></p>';
				  str+='<p class="text-center text-success m_top10" >Current Status - '+labelStatus+'</p>';
				  str+='<br>';
				  str+='<div class="m_top10">';
				str+='<select id="selectStsId" class="form-control" placeholder="Select Status"></select>';
				  str+='</div>';
				  str+='<p  style="color:red" id="updateStsErr"></p>';
				  str+='<input class="btn btn-success btn-block m_top10" attr_label_id="'+labelId+'" attr_label_status_id="'+statusId+'" type="button" id="updateStsBttnId" value="UPDATE"/>';
				  str+='<div id="statusErrDivId"></div>';
				   str+='<div class="m_top10">';
				  str+='<p id="updateStatusMsg"></p>';
				  str+='</div>';
				  str+='</div>';
				str+='</div>';
			 str+='</div>';
		 str+='</div>';
		 getAppointmentsLabelStatus("status");
		  $("#buildAppntmntStsTblId").html(str);
		  $("#myModalId").modal("show");
	}
	
	function getAppointmentsLabelStatus(type){
		
		if(type !=null && type == "status"){
			$("#selectStsId option").remove();
		}else if(type !=null && type == "onload"){
			$("#selectStsForLabelId option").remove();
		}
		
		var jsObj={
		}
		$.ajax({
			type : 'POST',
			url : 'getAppointmentsLabelStatusAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			var str='';
			if(type !=null && type == "status"){
				$("#selectStsId").append('<option value="0">Select Status</option>');
				if(result!=null && result.length>0){
				for(var i in result){
					$("#selectStsId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
				}
			  }
			}else if(type !=null && type == "onload"){
					str+='<option value="0">Select Status</option>';
				if(result!=null && result.length>0){
				for(var i in result){
					if(result[i].id==1){
						str+='<option value='+result[i].id+' selected>'+result[i].name+'</option>';
					}else{
						str+='<option value='+result[i].id+'>'+result[i].name+'</option>';
					}
				}
				$("#selectStsForLabelId").html(str);
				
			  }
			}
			
		});
	}

	$(document).on("click","#updateStsBttnId",function(){
		
		var labelId = $(this).attr("attr_label_id");
		var attrlabelstatusId = $(this).attr("attr_label_status_id");
		var labelstatusId = $("#selectStsId").val();
		if(labelstatusId == 0){
			$("#statusErrDivId").html("<span style='color:red;'>Please Select Status.</span>");
		}else if(attrlabelstatusId == labelstatusId){
			$("#statusErrDivId").html("<span style='color:red;'>Label Already In Selected Status.</span>");
		}else{
			updateAppointmentsLabelStatus(labelId,labelstatusId);
			
		}
		
	});
	function updateAppointmentsLabelStatus(labelId,labelstatusId){
		$("#statusErrDivId").html("");
		var jsObj={
				labelId:labelId,   
				labelstatusId:labelstatusId
		}
		$.ajax({
			type : 'POST',
			url : 'updateAppointmentsLabelStatusAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result!=null && result!=0){
				  if(result.message=="success"){
					  getLabelDtls();
					  setTimeout(function () {
						$("#updateStatusMsg").html("<center><h4 style='color: green;'>Status Updated Successfully</h4></center>").fadeOut(3000);
						}, 500);
						setTimeout(function () {
						$("#myModalId").modal("hide");
						}, 1000);
				  }
				}else{
					setTimeout(function () {
						$("#updateStatusMsg").html("<center><h4 style='color: green;'>Updation Failed..Try Later</h4></center>").fadeOut(3000);
						}, 500);
					
				 }
		});
	}
	
	$(document).on("change",".upadteAppntStatusCls",function(){
		updateMemberAppointmentsStatus($(this).attr("attr_appnt_id"),$(this).val(),$(this).find("option:selected").text());
	});
	
	
	function updateMemberAppointmentsStatus(apptId,statusId,status){
		$("#appointmentStatusMsg"+apptId).html("");
		var jsObj={
				apptId:apptId,
				statusId:statusId
		}
		$.ajax({
			type : 'POST',
			url : 'updateMemberAppointmentsStatusAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result!=null && result.message=="success"){
				$("#appointmentStatusMsg"+apptId).html("<span style='color:green;'>Status Updated Successfully.</span>");
				$("#apptStatus"+apptId).text(status);
				getLabelDtls();	
			}else{
				$("#appointmentStatusMsg"+apptId).html("<span style='color:red;'>Updation Failed..Try Later</span>");
			}
			
		});
	}

var tableToExcel = (function() {
  var uri = 'data:application/vnd.ms-excel;base64,'
    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>'
    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
  return function(table, name) {
    if (!table.nodeType) table = document.getElementById(table)
    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
    window.location.href = uri + base64(format(template, ctx))
  }
})()

	function generateToExcel()
	{	
		tableToExcel('allMemberTableId', 'Users Report');
	}
	
	$(document).on("click","#setTimeSlotBtnId",function(){
		setTimeout(function(){ $(".updateChangeClass").addClass("col-md-6"); }, 500);
			
		//var appointmentId = $("#appointmentLabelToGetSlotsId").val();		
		var appointmentId = $("#confirmAppointmentBlockDropId div").attr("attr_appointment_id");
		var date = $("#appointmentDateSlotId").val();
		var fromTime = $("#fromTimeId").val().trim();
		var toTime = $("#toTimeId").val().trim();
		
		//Saving
		setTimeSlotForAppointment(appointmentId,date,fromTime,toTime,"save",0,$("#commentTxt").val().trim());
	});
function buildTimeSlotsTable(result){
	
		var str='';
		str+='<tr>';
		str+='<td class="text-center" style="height:29px;">';
		str+='<i class="glyphicon glyphicon-triangle-top"></i>';
		str+='</td>';
		str+='</tr>';
		if(result.listOfTimePairPerDate!=null && result.listOfTimePairPerDate.length>0){
			for(var i in result.listOfTimePairPerDate){
				str+='<tr>';
				str+='<td class="text-center" style="height:29px;">'+(((result.listOfTimePairPerDate[i])[0])[0]).substr(0,10)+'</td>';
				str+='</tr>';
			}	
		}else if(result.labelDate!=null && result.labelDate.length>0){
			    str+='<tr>';
				str+='<td class="text-center" style="height:29px;">'+result.labelDate.substr(0,10)+'</td>';
				str+='</tr>';
		}
		str+='<tr>';
		str+='<td class="text-center" style="height:29px;">';
		str+='<i class="glyphicon glyphicon-triangle-bottom"></i>';
		str+='</td>';
		str+='</tr>';
		$("#tablePluginDateId").html(str);
		
		var str1='';
		str1+='<thead>';
		str1+='<th colspan="4">6a</th>';
		str1+='<th colspan="4">7</th>';
		str1+='<th colspan="4">8</th>';
		str1+='<th colspan="4">9</th>';
		str1+='<th colspan="4">10</th>';
		str1+='<th colspan="4">11</th>';
		str1+='<th colspan="4">12p</th>';
		str1+='<th colspan="4">1</th>';
		str1+='<th colspan="4">2</th>';
		str1+='<th colspan="4">3</th>';
		str1+='<th colspan="4">4</th>';
		str1+='<th colspan="4">5</th>';
		str1+='<th colspan="4">6</th>';
		str1+='<th colspan="4">7</th>';
		str1+='<th colspan="4">8</th>';
		str1+='<th colspan="4">9</th>';
		str1+='<th colspan="4">10</th>';
		str1+='</thead>';
		if(result.listOfTimePairPerDate!=null && result.listOfTimePairPerDate.length>0){
			for(var i in result.listOfTimePairPerDate){
			str1+='<tr id="'+i+'"class="borderSlot">';
			for(var unique=0;unique<=63;unique++){
				str1+='<td id="'+i+''+unique+'"></td>';
			}
			str1+='</tr>';
			}
			$("#tablePluginId").html(str1);
			for(var i in result.listOfTimePairPerDate){
				for(var j in result.listOfTimePairPerDate[i]){
					var start=((result.listOfTimePairPerDate[i])[j])[0];
					var end = ((result.listOfTimePairPerDate[i])[j])[1];
					var startIdForHour=start.substr(11,2);
					var startIdForMin=start.substr(14,2);
					var startId=(startIdForHour-6)*4;
				
					startId= startId+(startIdForMin/15);
					var strtDividedVleForMnte=startIdForMin%15;
					   if(strtDividedVleForMnte>=8){
						   startId=startId+1;
					   }
					var endIdForHour=end.substr(11,2);
					var endIdForMin=end.substr(14,2);
					var endId=(endIdForHour-6)*4;
					
					endId= endId+(endIdForMin/15);
					var endDividedVleForMnte=endIdForMin%15;
					   if(endDividedVleForMnte>=8){
						   endId=endId+1;
					   }
					endId=endId-1;  
					for(var start=startId;start<=endId;start++){
						$("#"+i+""+start).addClass("bookedSlots");
					}
				}
			}
			
		}else{
			str1+='<tr class="borderSlot">';
			for(var unique=0;unique<=63;unique++){
				str1+='<td></td>';
			}
			str1+='</tr>';
			$("#tablePluginId").html(str1);
		}
		
	}
	function clearAllValidationCls(){
		//$(".errorAptCls").html('');
		//$(".errorSpdCls").html('');
		//$(".errorArCls").html('');
		$(".errorCandidateMainDivCls").html('');
		$(".cloneErrCandidateNameCls").html('');
		$(".cloneErrCandidateDesgCls").html('');
		//$(".cloneErrCandidateMobileCls").html('');
		//$(".cloneErrCandidateLcScopeCls").html('');
		//$(".cloneErrCandidateDistrictCls").html('');
		//$(".cloneErrCandidateConstCls").html('');
		//$(".cloneErrCandidateMandalCls").html('');
		//$(".cloneErrCandidateVillageCls").html('');
		//$(".cloneErrCandidateTypeCls").html('');
		$(".cloneErrCandidateMemShipCls").html('');
	}
	
	//Required validation For Appointment Creation
	function validateSavingDetails(){
	
		var isErrAvailable=false;
		//var prType = $("#createAppTypeListId").val();
		var selectDate = $(".multiDateCls").val();
		var validateReason=$("#appointmentReasonId").val();
		
		<!-- Priority type,Prefereble Dates and Reason Validation -->
		/* if(prType == null || prType ==0 || prType == undefined){
			$(".errorAptCls").html("Please Select AppointmentType");
			isErrAvailable=true;
		}	 */	
		/* if(selectDate == null || selectDate.length<=0 || selectDate == undefined){
			if($("#selectManualDateId").is(":checked")){
				$(".errorSpdCls").html("Please Select Appointment Date(s)");
				isErrAvailable=true;				
			}			
		} */
		/* if(validateReason ==null || validateReason.length<=0 || validateReason == undefined || validateReason==""){
			$(".errorArCls").html("Please Specify The Reason");	
			isErrAvailable=true;				
		} */
		
		
		if (isEmpty($('#moreCandidatesDivId'))) {
			$(".errorCandidateMainDivCls").html("Please Add Candidate");	
			isErrAvailable=true;				
		}else{
			$(".validateCls").each(function(i){
				i = $(this).attr("attr_count");
				var nameValue=$("#candidateNameId"+i).val();
				 if(nameValue ==null || nameValue.length<=0 || nameValue == undefined || typeof nameValue === "undefined" || nameValue.trim() == ""){
					  isErrAvailable=true;
					  $("#cloneErrCandidateNameId"+i+"").html("Please enter Name");
				 }
				 var canTypeValue=$("#candidateTypeSelId"+i).val();
				 if(canTypeValue>0){
					 var desgValue=$("#designationSelId"+i).val();
					  if(desgValue ==null || desgValue ==0 || desgValue == undefined || desgValue ==""){
					  $("#cloneErrCandidateDesgId"+i).html("Please Select Designation");
					  isErrAvailable=true;	
				      } 
				 }
				/*  var desgValue=$("#designationSelId"+i).val();
				 if(desgValue ==null || desgValue ==0 || desgValue == undefined || desgValue ==""){
					  $("#cloneErrCandidateDesgId"+i).html("Please Select Designation");
					  isErrAvailable=true;	
				 } */
				 
				 <!-- MobileNo Validation-->
				 
				 /*var mobileValue=$("#mobileNoId"+i).val();
				  if(mobileValue ==null || mobileValue.length ==0 || mobileValue == undefined || mobileValue ==""){
					  $("#cloneErrCandidateMobileId"+i).html("Please enter Mobile No");
					  isErrAvailable=true;
				 } 	
				else if(mobileValue.length != 10 || isNaN(mobileValue)){		
					$("#cloneErrCandidateMobileId"+i).html("Please enter Valid Mobile Number");
					isErrAvailable=true;
				}*/
				
				 /* var canTypeValue=$("#candidateTypeSelId"+i).val();
				 if(canTypeValue ==null || canTypeValue ==0 || canTypeValue == undefined || canTypeValue ==""){
					  $("#cloneErrCandidateTypeId"+i).html("Please Select Candidate Type");
					  isErrAvailable=true;	
				 }
				 
				if(canTypeValue==1 || canTypeValue==2 || canTypeValue==3){
					var membershpNmbr=$("#membershipNumId"+i).val();
					  if(membershpNmbr ==null || membershpNmbr.length<=0 || membershpNmbr == undefined || typeof membershpNmbr === "undefined" || membershpNmbr.trim() == ""){
						 $("#cloneErrCandidateMemShipId"+i).html("Please Enter Membership Number");
						 isErrAvailable=true;
				   }
				} */
				<!-- Location Scope validation -->	
				
			 /* var locationScopeValue=$("#locationScopeSelId"+i).val();
				 if(locationScopeValue ==null || locationScopeValue ==0 || locationScopeValue == undefined || locationScopeValue == ""){
					  $("#cloneErrCandidateLcScopeId"+i).html("Please Selection Location Scope");
					  isErrAvailable=true;	
				 }else{
					 //District Level
					 if(locationScopeValue == 3){					 
							  var districtValue=$("#districtId"+i).val(); 
							   if(districtValue ==null || districtValue ==0 || districtValue == undefined || districtValue == ""){
								  $("#cloneErrCandidateDistrictId"+i).html("Please select District"); 
								  isErrAvailable=true;
							   }
						 
					//Constituency Level
					 }else if(locationScopeValue == 4){
						 
							  var districtValue=$("#districtId"+i).val(); 
							   if(districtValue ==null || districtValue ==0 || districtValue == undefined || districtValue == ""){
								  $("#cloneErrCandidateDistrictId"+i).html("Please select District"); 
								  isErrAvailable=true;	
							   }
							   
							 var constValue = $("#constituencyId"+i).val();						
								if(constValue ==null || constValue ==0 || constValue == undefined || constValue == ""){
									$("#cloneErrCandidateConstId"+i).html("Please select Constituency"); 
									isErrAvailable=true;
								}						 
						 
						// Mandal Level
					 }else if(locationScopeValue == 5){
						 
							  var districtValue=$("#districtId"+i).val(); 
							   if(districtValue ==null || districtValue ==0 || districtValue == undefined || districtValue == ""){
								  $("#cloneErrCandidateDistrictId"+i).html("Please select District"); 
								  isErrAvailable=true;
							   }
						
							 var constValue = $("#constituencyId"+i).val();						
								if(constValue ==null || constValue ==0 || constValue == undefined || constValue == ""){
									$("#cloneErrCandidateConstId"+i).html("Please select Constituency"); 
									isErrAvailable=true;
								}
						var tehsilValue = $("#tehsilId"+i).val();
						if(tehsilValue ==null || tehsilValue ==0 || tehsilValue == undefined || tehsilValue == ""){					
							$("#cloneErrCandidateMandalId"+i).html("Please select Mandal"); 
							isErrAvailable=true;
						}					
						 
					// Village level
					 }else if(locationScopeValue == 6){
						 
							var districtValue=$("#districtId"+i).val(); 
							   if(districtValue ==null || districtValue ==0 || districtValue == undefined || districtValue == ""){
								  $("#cloneErrCandidateDistrictId"+i).html("Please select District");
									isErrAvailable=true;
							   }
						
							 var constValue = $("#constituencyId"+i).val();						
								if(constValue ==null || constValue ==0 || constValue == undefined || constValue == ""){
									$("#cloneErrCandidateConstId"+i).html("Please select Constituency"); 
									isErrAvailable=true;
								}
								
							var tehsilValue = $("#tehsilId"+i).val();
								if(tehsilValue ==null || tehsilValue ==0 || tehsilValue == undefined || tehsilValue == ""){					
									$("#cloneErrCandidateMandalId"+i).html("Please select Mandal"); 
									isErrAvailable=true;
								}	
								
								var villageValue = $("#villageId"+i).val();
								if(villageValue ==null || villageValue ==0 || villageValue == undefined || villageValue == ""){					
									$("#cloneErrCandidateVillageId"+i).html("Please select Village"); 
									isErrAvailable=true;
								}	
					//Municipality Level
					 }else if(locationScopeValue == 7){
						 
						 var districtValue=$("#districtId"+i).val(); 
							   if(districtValue ==null || districtValue ==0 || districtValue == undefined || districtValue == ""){
								  $("#cloneErrCandidateDistrictId"+i).html("Please select District"); 
								  isErrAvailable=true;
							   }
						
							 var constValue = $("#constituencyId"+i).val();						
								if(constValue ==null || constValue ==0 || constValue == undefined || constValue == ""){
									$("#cloneErrCandidateConstId"+i).html("Please select Constituency"); 
									isErrAvailable=true;
								}
						var MunicipalValue = $("#tehsilId"+i).val();
						if(MunicipalValue ==null || MunicipalValue ==0 || MunicipalValue == undefined || MunicipalValue == ""){					
							$("#cloneErrCandidateMandalId"+i).html("Please select Municipality"); 
							isErrAvailable=true;
						}
						
					//Ward Level
					 }else if(locationScopeValue == 8){
						 
						 var districtValue=$("#districtId"+i).val(); 
							   if(districtValue ==null || districtValue ==0 || districtValue == undefined || districtValue == ""){
								  $("#cloneErrCandidateDistrictId"+i).html("Please select District"); 
								  isErrAvailable=true;
							   }
						
							 var constValue = $("#constituencyId"+i).val();						
								if(constValue ==null || constValue ==0 || constValue == undefined || constValue == ""){
									$("#cloneErrCandidateConstId"+i).html("Please select Constituency"); 
									isErrAvailable=true;
								}
								
							var MunicipalValue = $("#tehsilId"+i).val();
								if(MunicipalValue ==null || MunicipalValue ==0 || MunicipalValue == undefined || MunicipalValue == ""){					
									$("#cloneErrCandidateMandalId"+i).html("Please select Municipality"); 
									isErrAvailable=true;
								}	
								
								var wardValue = $("#villageId"+i).val();
								if(wardValue ==null || wardValue ==0 || wardValue == undefined || wardValue == ""){					
									$("#cloneErrCandidateVillageId"+i).html("Please select Ward"); 
									isErrAvailable=true;
								}					 
					 }
				 } */
			});
			
			
		}
		
		return isErrAvailable;	
	}
	
	//div emtty checking
	function isEmpty(el){
      return !$.trim(el.html())
	}
	
	function getViewAppointmentsOfALable(){
		var jsObj={
			labelId : $("#appointmentLabelToGetSlotsId").val()
		}
		
		$.ajax({
			type : 'POST',
			url : 'getViewAppointmentsOfALableAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result!=null){
				buildAppointmentMembers(result);
			}
		});
	}
	
	function buildAppointmentMembers(result){
		var str = '';
		var fromTime = 0;
		var toTime = 0;
		var firstMean = '';
		var secondMean = '';
		var firstHour = 0;
		var firstMin = 0;
		var secondHour = 0;
		var secondMin = 0;
		str+='<table id="appntmntMmbrsTblId">';
		str+='<thead><th></th></thead>';
		for(var i in result){
			str+='<tr>';
			str+='<td>';
			str+='<div class="panel panel-default manageAppViewPanelClass m_top15">';
			str+='<div class="panel-heading">';
			str+='<i id="setDfltTmFrmtId" class="glyphicon glyphicon-edit settingsIconConfirm settingsIcon pull-right" title="Click here to update label time slot." style="margin-left:10px;cursor:pointer;"></i>';
			str+='<div class="appointmentSettingsBLock arrow_box" style="display: none;">';
			str+='<div class="row updateAppMemCls" attr_timeSlotId="'+result[i].timeSlotId+'" attr_appointmentId="'+result[i].appointmentId+'">';
			str+='<div class="col-md-12 m_top10">';
			str+='<label>Select Date &nbsp <span style="color:red;" class="errorDivFrTmSltUpdtId"></span></label>';
			str+='<div class="inputSearch input-group"><span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span><input type="text"    class="form-control appntmntCnddteUpdtDtRngPckrCls"/></div>';
			str+='</div>';
			str+='<div class="col-md-12 m_top10">';
			str+='<label>From Time</label>';
			str+='<div class="inputSearch input-group"><span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span><input type="text" class="form-control appntmntCnddteUpdtFrmTmCls "/></div>';
			str+='</div>';
			str+='<div class="col-md-12 m_top10">';
			str+='<label>To Time</label>';
			str+='<div class="inputSearch input-group"><span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span><input type="text" class="form-control appntmntCnddteUpdtTotmCls"/></div>';
			str+='</div>';
			str+='<div class="col-md-12">';
			str+='<button class="btn btn-success btn-block m_top10 updateTimeSlotCls">SET</button>';
			str+='</div>';
			str+='</div>';
			str+='</div>';
			str+='<span class="text-success pull-right" style="margin-left:8px;" id="timeSpnCls">';
			str+='<i class="glyphicon glyphicon-time"></i>';
			fromTime = result[i].fromDateStr.substr(11,5);
			firstHour = fromTime.substr(0,2);
			firstMin = fromTime.substr(3,2);
			if(firstHour>12){
				firstHour = firstHour-12;
				firstMean = "PM";
			}else{
				firstMean = "AM";
			}
			
			toTime = result[i].toDateStr.substr(11,5);
			secondHour = toTime.substr(0,2);
			secondMin = toTime.substr(3,2);
			if(secondHour>12){
				secondHour = secondHour-12;
				secondMean = "PM";
			}else{
				secondMean = "AM";
			}
			str+=''+firstHour+' : '+firstMin+' '+firstMean +' to '+secondHour+' : '+secondMin+' '+secondMean +'';
			str+='</span>&nbsp;';
			str+='<span class="pull-right" style="margin-left:8px;" id="dateSpnCls">';
			str+='<i class="glyphicon glyphicon-calendar"></i> '+result[i].fromDateStr.substr(0,10)+'</span>&nbsp;';
			
			str+='<p>Priority : '+result[i].priority+'</p>';
			str+='<p>Purpose : '+result[i].subject+'</p>';
			
			str+='</div>';
			str+='<div class="panel-body pad_5">';
			str+='<ul class="confirmSearchUl" style="list-style: none;padding:0px">';
			for(var j in result[i].subList){
				str+='<li>';
					str+='<div class="row">';
							str+='<div class="col-md-7">';
								str+='<div class="media">';
									str+='<div class="media-left">';
										str+='<img class="media-object thumbnailSearch thumbnail" src="'+result[i].subList[j].imageUrl+'" onerror="setDefaultImage(this);" alt="Candidate Image">';
									str+='</div>';
									str+='<div class="media-body">';
									if(result[i].subList[j].name !=null){
										str+='<p>'+result[i].subList[j].name+'</p>';
									}else{
										str+='<p> -- </p>';
									}
									if(result[i].subList[j].mobileNo !=null && result[i].subList[j].mobileNo.length>0){
											str+='<p >Contact Number: '+result[i].subList[j].mobileNo+'</p>';
									}else{
										str+='<p>Contact Number: - </p>';
									}
									if(result[i].subList[j].designation !=null && result[i].subList[j].designation.length>0){
											str+='<p >Designation: '+result[i].subList[j].designation+'</p>';
									}else{
										str+='<p>Designation: - </p>';
									}
									if(result[i].subList[j].constituency !=null && result[i].subList[j].constituency.length>0){
											str+='<p>Constituency: '+result[i].subList[j].constituency+'</p>';
									}else{
										str+='<p>Constituency: - </p>';
									}
									str+='</div>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
						str+='</li>';
			}
			str+='</ul>';
			str+='<p class="m_top10">';
			str+="<i>Appt Created By: "+result[i].userName+"</i>";
			str+='<img src="dist/Appointment/img/message.png" class="messageIcon" alt="messageIcon" title="Send Sms" data-toggle="tooltip" data-placement="top" ></p>';
			str+='</div>';
			str+='</div>';
			str+='</td>';
			str+='</tr>';
		}
		str+='</table>';
		$("#appointmentMembersId").html(str);
        $(".appntmntCnddteUpdtDtRngPckrCls").daterangepicker({singleDatePicker:true});		
		$('.appntmntCnddteUpdtDtRngPckrCls').val(moment().format('MM/DD/YYYY'));		
		$(".appntmntCnddteUpdtFrmTmCls").datetimepicker({format:"LT"});
	 	$(".appntmntCnddteUpdtTotmCls").datetimepicker({format:"LT"});
	    $('#appntmntMmbrsTblId').dataTable({
			"iDisplayLength": 2,
		});
	}
	
	$(document).on("click","#setDfltTmFrmtId",function(){
	    //setting default time format
	    var timeArr=[];
		timeArr = $(this).closest("tr").find("#timeSpnCls").text().split("to");
		var date = $(this).closest("tr").find("#dateSpnCls").text();
		var dateArr=date.split("-");
		
		$(this).closest("tr").find(".appntmntCnddteUpdtDtRngPckrCls").val(dateArr[1]+"/"+dateArr[2]+"/"+dateArr[0]);
		$(this).closest("tr").find(".appntmntCnddteUpdtFrmTmCls").val(timeArr[0].split(":")[0].trim()+":"+timeArr[0].split(":")[1].trim());
		$(this).closest("tr").find(".appntmntCnddteUpdtTotmCls").val(timeArr[1].split(":")[0].trim()+":"+timeArr[1].split(":")[1].trim());
	 });
	$(".dateRadioCls").click(function(){		
		if($("#selectManualDateId").is(":checked")){
			$("#multiDate").multiDatesPicker({numberOfMonths: [1,2],minDate:0})
			$(".disableCls").attr('disabled', false); 
		}else{
			$(".disableCls").attr('disabled', true); 
		}		
	}); 
	
	$(document).on("click",".deleteAppointments",function(){
		
		$(".commonDivCls").hide();
		
		var labelName = $(this).attr("attr_label_name");
		var labelId = $(this).attr("attr_label_id");
		var jsObj={
			labelId : labelId,
			callFrom : "",
			apptuserId:$("#appointmentUserSelectBoxId").val()
		}
		
		$.ajax({
			type : 'POST',
			url : 'viewAppointmentsOfALableAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$(".appointmentsDeleteDivCls").show();
			if(result!=null && result!=0){
			  buildDeleteAppResult(result,labelName,labelId);
			}else{
			  $(".appointmentsDeleteDivCls").html("<div class='col-md-12'><div class='block'><h4 class='text-success' style='margin-bottom:10px;'>"+labelName +" MEMBERS</h4><center><p style='color:green;font-size:20px'>No Data available.</p></center></div></div>");	
			}
		});		
	});
	
	function buildDeleteAppResult(result,labelName,labelId){
		var i = 0;
		var str='';
			str+='<div class="col-md-12">';
			str+='<div class="block">';
			str+='<h4 class="text-success" style="margin-bottom:10px;">'+labelName +' MEMBERS</h4>';
			for(var i in result){
			
				str+='<div class="panel panel-default manageAppViewPanelClass">';
				str+='<div class="panel-heading pad_5">';
				    str+='<div class="row">';
						str+='<div class="col-md-2">';
							str+='<span>Appointment Id: '+result[i].aptUniqueCode+'</span>';
						str+='</div>';
						str+='<div class="col-md-2">';
							if(result[i].priority !=null && result[i].priority.length>0){
								str+='<p>Priority : '+result[i].priority+'</p>';
							}else{
								str+='<p>Priority : - </p>';
							}
						str+='</div>';
						str+='<div class="col-md-3">';
							if(result[i].dateString !=null && result[i].dateString.length>0){
								str+='<p>Request Created Date : '+result[i].dateString.split(" ")[0]+'</p>';
							}else{
								str+='<p>Request Created Date : - </p>';
							}
						str+='</div>';
						str+='<div class="col-md-3 pull-right">';
							str+='<span class="requestedCheckbox" data-toggle="tooltip" data-placement="top" title="Check this to delete appointments"><input type="checkbox" value="'+result[i].appointmentId+'" class="deleteAppointmentChckCls"></input></span>';
							if(result[i].status != null){
								str+='<span>Current Status : '+result[i].status+'</span>';
							}else{
								str+='<span>Current Status : - </span>';
							}	
						str+='</div>';
					str+='</div>';
					if(result[i].subject !=null && result[i].subject.length>0){
						str+='<p>Purpose : '+result[i].subject+'</p>';
					}else{
						str+='<p>Purpose : - </p>';
					}
									
					
				str+='</div>';
				str+='<div class="panel-body pad_5">';
					for(var j in result[i].subList){
						str+='<ul class="viewAppointmentRequestedMembers">';
							str+='<li>';
								str+='<div class="row">';
									str+='<div class="col-md-6">';
									str+='<div class="col-md-6">';
										str+='<div class="media">';
											str+='<div class="media-left">';
												str+='<img class="media-object thumbnail" src="'+result[i].subList[j].imageUrl+'" onerror="setDefaultImage(this);" alt="Candidate Image">';
											//	str+='<span class="colorStatus green"></span>';
											str+='</div>';
											str+='<div class="media-body">';
											if(result[i].subList[j].cadre == true){
												str+='<p>'+result[i].subList[j].name+' - Cadre</p>';
											}else{
												str+='<p>'+result[i].subList[j].name+' </p>';
											}if(result[i].subList[j].mobileNo !=null && result[i].subList[j].mobileNo.length>0){
													str+='<p>Contact Number: '+result[i].subList[j].mobileNo+'</p>';
											}else{
													str+='<p>Contact Number: - </p>';
											}if(result[i].subList[j].designation !=null && result[i].subList[j].designation.length>0){
												str+='<p>Designation: '+result[i].subList[j].designation+'</p>';
											}else{
												str+='<p>Designation: - </p>';
											}if(result[i].subList[j].constituency !=null && result[i].subList[j].constituency.length>0){
												str+='<p>Constituency : '+result[i].subList[j].constituency+'</p>';
											}else{
												str+='<p>Constituency : - </p>';
											}				
											str+='</div>';
										str+='</div>';
										
									str+='</div>';
									str+='<div class="col-md-6">';
									
									//history modal start
									 if(result[i].subList[j].candidateId != null && result[i].subList[j].candidateId > 0){
											str+='<a  title="Click here to View '+result[i].subList[j].name+' History" data-toggle="tooltip" data-placement="top" class="historyShowModalBtn"  style="cursor:pointer;" attr-id="'+result[i].subList[j].candidateId+'" attr-name="'+result[i].subList[j].name+'" attr-designation="'+result[i].subList[j].designation+'" attr-mobile="'+result[i].subList[j].mobileNo+'"><i class="glyphicon glyphicon-time" style="color: rgb(142, 142, 142); font-size: 16px;"></i></a>&nbsp;&nbsp;';
									} 			
									//history modal end
									
										str+='<table class="table table-bordered m_top10 table-condensed">';
										if(result[i].subList[j].subList != null && result[i].subList[j].subList.length>0){
											str+='<thead>';
												str+='<th>APPOINTMENT ID</th>';
												str+='<th>CREATED DATE</th>';
												str+='<th>APPOINTMENT PREFERABLE DATES</th>';
												str+='<th>STATUS</th>';
											str+='</thead>';
											str+='<tbody>';
												for(var l in result[i].subList[j].subList){
													str+='<tr>';
													
													str+='<td>'+result[i].subList[j].subList[l].aptUniqueCode+'</td>';
													
													str+='<td>'+result[i].subList[j].subList[l].dateString+'</td>';
													
													if(result[i].subList[j].subList[l].dateTypeId !=null && result[i].subList[j].subList[l].dateTypeId >1){
														str+='<td>'+result[i].subList[j].subList[l].dateType+' : <span>'+result[i].subList[j].subList[l].minDate+' - '+result[i].subList[j].subList[l].maxDate+'</span></td>';
													}else if(result[i].subList[j].subList[l].dateTypeId !=null && result[i].subList[j].subList[l].dateTypeId ==1){
														str+='<td>'+result[i].subList[j].subList[l].apptpreferableDates+'</td>';
													}else{
														str+='<td>-</td>';
													}
													
													if(result[i].subList[j].subList[l].status !=null){
														str+='<td>'+result[i].subList[j].subList[l].status+'</td>';
													}else{
														str+='<td>-</td>';
													}													
													str+='</tr>';
												}
											}else{
													str+='<thead>';
													str+='<th>APPOINTMENT ID</th>';
													str+='<th>CREATED DATE</th>';
													str+='<th>APPOINTMENT PREFERABLE DATES</th>';
													str+='<th>STATUS</th>';
													str+='</thead>';
												
													str+='<tr>';
													str+='<td  colspan="3"><center>No Data Available</center></td>';
													str+='</tr>';
											}
											
											str+='</tbody>';
										str+='</table>';
									str+='</div>';
								str+='</div>';
							str+='</li>';
						str+='</ul>';
						}
						
						
						str+='<div class=" m_top10">';
							str+='<span style="font-size: 16px;"><b>REQUESTED DATES : </b></span>';
							
							if(result[i].apptpreferableDates != null){							
								str+='<span>'+result[i].apptpreferableDates+'</span>';
							}else if(result[i].apptpreferableDates == null && result[i].maxDate != null && result[i].minDate != null && result[i].dateType != null && result[i].dateType.trim() != ""){
								str+='<span>'+result[i].dateType+' ('+result[i].minDate+' to '+result[i].maxDate+')</span>';
							}else{
								str+='<span> - </span>';
							}						
						str+='</div>';
							
				  str+='</div>';
				str+='</div>';
			}
			str+='<button class="btn btn-success" attr_label_id="'+labelId+'" id="deleteMultipleAppointmentsId">Delete Appointments</button>';
			str+='<div id="deleteAppointmentErrDivId"></div>';
			str+='</div>';
			str+='</div>';
		
		$(".appointmentsDeleteDivCls").html(str)  
		$('[data-toggle="tooltip"]').tooltip()
	}
	
	function showHideBySearchType(){
		
		//setToDefaultAdvancedSearch();
			$('#errorDivId').html('');
			var selectVal = $("#advanceSearchTypeId").val();
			
			if(selectVal == 2)
			{
				$(".advancePRCls").show();
				$(".advanceprclsDiv").show();
				$(".advanceNameCls").hide();
				$(".advanceCadreCommittee").hide();
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
				$(".advancePRCls").hide();
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
				$(".stateShowCls").show();
				$(".advanceprclsDiv").show();
				$(".advanceNameCls").show();
				$(".levelShowCls").show();
				$(".advancePRCls").hide();
				$("#cadreCommitteeDiv_chosen").hide();
				$("#referCommitteeDiv").hide();
				clearNameSearchTypeFields();
				
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
				disableByLevel();
				$(".stateCls").show();
				$(".distCls").hide();
				$(".constiCls").hide();
				$(".mandalCls").hide();
				$(".panchayatCls").hide();
	}
	 
	  function  clearNameSearchTypeFields(){
		  clearLevelField();
		  clearStateField();
	  }
	   function clearLevelField(){
		  $("#levelId").val(0);	
		  $("#levelId").dropkick('reset'); 
	   }
	   function clearStateField(){
		 $("#stateId").val(0);	
		 $("#stateId").dropkick('reset');
	   }
	   
	function setToDefaultAdvancedSearch()
	{	
			$("#advanceDesignationId").val(0);
			$("#advanceDesignationId").dropkick('reset');
			
			$("#levelId").val(0);	
			$("#levelId").dropkick('reset');
			
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
	$(document).on("click","#deleteMultipleAppointmentsId",function(){
		$("#deleteAppointmentErrDivId").html("");
		var idsArr=[];
		$(".deleteAppointmentChckCls").each(function(){
			if($(this).prop('checked')==true){
				idsArr.push($(this).val());
			}
		});
		
		if(idsArr != null && idsArr.length <= 0){
			$("#deleteAppointmentErrDivId").html("<span style='color:red;'>Please Select Appointments To Delete.</span>");
			return;
		}
		
		var jsObj={
			idsArr : idsArr,
			labelId : $(this).attr("attr_label_id")
		}
		
		$.ajax({
			type : 'POST',
			url : 'deleteAppointmentsOfLabelAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null){
				if(result.exceptionMsg != null && result.exceptionMsg == "success" && result.resultCode != null && result.resultCode == 0){
					$("#deleteAppointmentErrDivId").html("<span style='color:green;'>Appointments Deleted</span>");
					getLabelDtls();
					setTimeout(function(){	$("#deleteAppointmentErrDivId").hide(); },2000);
				}else{
					$("#deleteAppointmentErrDivId").html("<span style='color:red;'>Appointments Not Deleted, Please Try Again.</span>");
				}
			}
		});
	});
	//getAppointmentCreatedUsers();
	function getAppointmentCreatedUsers(){
		$.ajax({
			type : 'GET',
			url : 'appointmentCreatedUsers.action',
			dataType : 'json',
			data : {}
		}).done(function(result){ 
			if(result != null && result.length > 0){
				//app-appointment
				buildAppointmentCreatedUsers(result);
			}
			
		});
	}
	function buildAppointmentCreatedUsers(result){
		$("#appointmentcreatedBy  option").remove();
		$("#appointmentcreatedBy").append('<option value="0">All</option>');
	  for(var i in result){
			$("#appointmentcreatedBy").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		 }
		//$(".appointmentCreatedidCls").dropkick();
		var select = new Dropkick("#appointmentcreatedBy");
		select.refresh();
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
		  aptUserId = $("#appointmentUserSelectBoxId").val();
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
			 /*if(searchValue == 0 || searchValue.length ==0)
			 {
				 errorStr='Please Select Designation';
				 $("#errorDivId").html(errorStr);
				 return;
			 }*/
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
		
		 levelId  = $("#levelId").val();
		if(levelId == 10)
			level = "state";
		if(levelId == 11)
			level = "district";
		if(levelId == 5)
			level = "mandal";
		if(levelId == 6)
			level = "village";
		if(levelId == 1)
			level = "constituency";
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
          if(levelId == 5){
		
			 districtId = $("#referdistrictId").val();
			
			if(districtId==0 || districtId=='select'){
			
				errorStr +="Please Select District";
			}
		}
		
		 else if(levelId == 6){
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
				url : 'getAppntmntAdvancedSearchDetailsAction.action',
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
		$(document).on("click",".btnClassChange",function(){
			var selectedValue = $(this).attr("attr_val");
			$("#apptmemberDetailsDiv").html("");
			searchTypeRadioCls(selectedValue);
			
		})
	function searchTypeRadioCls(selected)
	{
		
		$(".clearCls").val("");
		if(selected == 1)
		{
			showHideSearch("search");
			$(".locationsFilterCls").hide();
			$(".advanceCadreCommittee").hide();
			
		}
		else
		{
			showHideSearch("advanceSearch");
			showHideBySearchType();
		}
	}
	function handleBySearchType()
	{
		$(".btnClassChange").each(function(){
			if($(this).hasClass("btnActive"))
			{
			
				var selected = $(this).attr("attr_val");
				
				if(selected == 1)
				{
					getDetailsBySrch();
				}
				else
				{
					getAdvancedSearchDetails();
				}
			}
		})
		
		
	}
	
	$('#searchValueId').keydown(function(event){    
    if(event.keyCode==13){
       $('.advancedSearchBtn').trigger('click');
    }
	});

	$( "#appointmentUserSelectBoxId" ).change(function() {
		getAppointmentLabels();					
		getTotalAppointmentStatus();
		getAppointmentStatusCounts();
		getSearchDetails(false);
	});
	$( "#selectStsForLabelId" ).change(function() {
		getLabelDtls();
		
	});
	
	function buildDesgnationBlockForOtherCandidate(candidateTypeId,cnt){
    	var  othrCnddtDsgntnBlckId=$("#othrCnddtDsgntnBlckId"+cnt).attr("id");
			if(candidateTypeId==4){
				$("#"+othrCnddtDsgntnBlckId).show();
			}else{
				$("#"+othrCnddtDsgntnBlckId).hide();
			}
	}
	$(document).on("click",".cloneDesignationSpanCls",function(){
	    $(".designationCls").val(" ");
		$(".designationErrCls").html(" ");
		$(".statusCls").html(" ");
		var cloneId=$(this).attr("attr_coneId");
		$(".saveDesignationCls").attr("id",cloneId);
	    $("#blockForOtherCandidateModalId").modal("show");
 	});
	function getDesignationsByTypeForChange(cnt,status)
	{
	  var typeId = $("#candidateTypeSelId"+cnt).val();
	  if(status.trim()!="other"){
		  buildDesgnationBlockForOtherCandidate(typeId,cnt);
	   }
	   var jsObj = {
		typeId : typeId,
		task:""
	    }
		$.ajax({
			type : 'GET',
			url : 'getAppCandidateDesigListByTypeAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){ 
			if(result != null && result.length > 0){
				//app-appointment
				buildDesignationForCreateApp1(result,"designationSelId"+cnt,status);
			}
			
		});
	}
	
	function getDesignationsByType(typeId,selectId)
	{
	
	var jsObj = {
		typeId : typeId,
		task:""
	}
		$.ajax({
			type : 'GET',
			url : 'getAppCandidateDesigListByTypeAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){ 
			if(result != null && result.length > 0){
				//app-appointment
				buildDesignationForCreateApp1(result,selectId," ");
			}
			
		});
	}
	
	function buildDesignationForCreateApp1(result,selectId,status){
		 $("#"+selectId+"  option").remove();
		 $('#'+selectId).append('<option value="0">Select Designation</option>');
		for(var i in result){
			if(popDesignation == result[i].name)
			$('#'+selectId).append('<option value='+result[i].id+' typeId='+result[i].orderId+' selected="true">'+result[i].name+'</option>');
		else
			$('#'+selectId).append('<option value='+result[i].id+' typeId='+result[i].orderId+'>'+result[i].name+'</option>');
		 }
		 var listIndex=result.length;
		 if(status.trim()=="other"){
		  var select = new Dropkick('#'+selectId);
		  select.refresh();
		  select.select(listIndex);
		 }else{
		  var select = new Dropkick('#'+selectId);
		   select.refresh();
		 }
	} 
	function setDefaultImage(img){
	  img.src = "dist/Appointment/img/thumb.jpg";
   }
   $(document).on('click','.addMembersClass',function(){
		$("#appDesigErrId").html('');
		$("#appPrrtyErrTypId").html('');
		$("#appStatusErrId").html('');
		$("#appDistErrId").html('');
		$("#appConstErrId").html('');
	});
	$(document).on("click","#myonoffswitch",function(){
		$("#apptmemberDetailsDiv").html('');
	});
	function applyPagination(){
		$('#searchedMembersId').DataTable();
	}
	$(document).on("click",".updateTimeSlotCls",function(){
		
		var appointmentId =$(this).closest("tr").find(".updateAppMemCls").attr("attr_appointmentId");
		var timeSlotId = $(this).closest("tr").find(".updateAppMemCls").attr("attr_timeSlotId");
		var date = $(this).closest("tr").find(".appntmntCnddteUpdtDtRngPckrCls").val();
		var fromTime = $(this).closest("tr").find(".appntmntCnddteUpdtFrmTmCls").val().trim();
		var toTime = $(this).closest("tr").find(".appntmntCnddteUpdtTotmCls").val().trim();
		
		/*$(".errorDivFrTmSltUpdtId").html(' ');
		if(fromTime ==null || fromTime.length ==0 || fromTime == undefined){
			$(this).closest("tr").find(".errorDivFrTmSltUpdtId").html("Please Specify the From Time");
			return;
		}if(toTime ==null || toTime.length ==0 || toTime == undefined){
			$(this).closest("tr").find(".errorDivFrTmSltUpdtId").html("Please Specify the To Time");
			return;
		}
		if(!((Date.parse(date+" "+fromTime)>=Date.parse(date+" "+"6:00 AM")) && (   	Date.parse(date+" "+toTime)<=Date.parse(date+" "+"10:00 PM")))){	
			 $(this).closest("tr").find(".errorDivFrTmSltUpdtId").html("From time and to time should be between 6:00 AM to 10:00 PM.");
			 return;
		 } 
		if(!(Date.parse(date+" "+toTime) > Date.parse(date+" "+fromTime))){
			 $(this).closest("tr").find(".errorDivFrTmSltUpdtId").html("To Time should be greater than From Time.");
			 return;
		 } */
	    setTimeSlotForAppointment(appointmentId,date,fromTime,toTime,"update",timeSlotId,"");
	});	
	function setTimeSlotForAppointment(appointmentId,date,fromTime,toTime,type,timeSlotId,commentTxt){
		
		var errorMsgId='';
		if(type == "save"){
			$("#errorDivForTimeSlotId").show();
		    $("#errorDivForTimeSlotId").html(" ");
			
			errorMsgId = $("#errorDivForTimeSlotId");
		}else if(type == "update"){
		   $("#updateTimeSlotMsgShow").html('');
		   
		   errorMsgId =  $("#updateTimeSlotMsgShow");
		}
		
		//Validations For Time Slot Creation
		
		   if(appointmentId ==null || appointmentId <=0 || appointmentId ==undefined){
			$(errorMsgId).html("<span style='color:red;font-size:14px'>Please Specify the Appointment</span>");
			return;
		  }   
		
		/*if(fromTime ==null || fromTime.length ==0 || fromTime == undefined){
			$(errorMsgId).html("<span style='color:red;font-size:14px'>Please Specify the From Time</span>");
			return;
		}if(toTime ==null || toTime.length ==0 || toTime == undefined){
			$(errorMsgId).html("<span style='color:red;font-size:14px'>Please Specify the To Time</span>");
			return;
		}
		
		 
		if(!((Date.parse(date+" "+fromTime)>=Date.parse(date+" "+"6:00 AM")) && (   	Date.parse(date+" "+toTime)<=Date.parse(date+" "+"10:00 PM")))){
			 $(errorMsgId).html("<span style='color:red;font-size:14px'>From time and to time should be between 6:00 AM to 10:00 PM.</span>");
			 return;
		 } 
		if(!(Date.parse(date+" "+toTime) > Date.parse(date+" "+fromTime))){
			 $(errorMsgId).html("<span style='color:red;font-size:14px'>To Time should be greater than From Time.</span>");
			 return;
		 }*/
		
		if(type=="save"){
			$("#ajaxImgForTimeSlotId").css("display","inline-block");
		}else{
			$("#updateTimeSlotAjax").css("display","inline-block");
		}
		 
		var jsObj={
			appointmentId : appointmentId,
			date : date,
			fromTime : fromTime,
			toTime : toTime,
			type :type,
			timeSlotId:timeSlotId,
			commentTxt:commentTxt,
			apptUserId : $("#appointmentUserSelectBoxId").val()
		}
		
		$.ajax({
			type : 'POST',
			url : 'setTimeSlotForAppointmentAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			
			if(type=="save"){
				$("#errorDivForTimeSlotId").show();
			    $("#ajaxImgForTimeSlotId").css("display","none");
			}else if(type=="update"){
				$("#updateTimeSlotMsgShow").html('');
				$("#updateTimeSlotAjax").css("display","none");
			}
			if(result != null && result.exceptionMsg != null && result.exceptionMsg == "success"){
				
				if(type=="save"){
					
					  $("#errorDivForTimeSlotId").html("<p style='color:green;font-size:20px'>Saved Successfully</p>");
					  setTimeout('$("#errorDivForTimeSlotId").hide()', 2000);
					  $('html, body').animate({scrollTop: $("#errorDivForTimeSlotId").offset().top}, 2000);
					  $("#confirmAppointmentBlockDropId").empty();
					  $("#confirmAppointmentBlockDropId").html("<h4 class='deleteTag'>DROP HERE</h4>");
					  //setting default time format
					   $("#fromTimeId").val("00:00 AM");
					   $("#toTimeId").val("00:00 PM");
					   $("#commentTxt").val("");
					    //Reinitializing date range picker after setting time slot for appointment
					    $("#appointmentDateSlotId").daterangepicker({singleDatePicker:true,minDate:new Date()});
                        $('#appointmentDateSlotId').val(moment().format('MM/DD/YYYY')); 
						 
					    getAllScheduledApptsByDate();
					    getAppointmentStatusOverview();
					   
			   }else if(type=="update"){
				   
				     $("#updateTimeSlotMsgShow").html("<p style='color:green;font-size:20px'>Appointment TimeSlot Updated Successfully</p>");
					 setTimeout(function(){	$(".appointmentTimeSlotModalpopup").modal("hide");
						  setTimeout(function () {
								setTimeout('$("body").addClass("modal-open")', 2000);
							 }, 2500);
					 },2000);
				
					 //set updated values.
					 
					 var attrId = $("#updateSettingApptId"+appointmentId);
					 $(attrId).attr("attr_date",date);      
					 $(attrId).attr("attr_from_time",fromTime);
					 $(attrId).attr("attr_to_time",toTime);
					 var dateArr=date.split("/");
					// var dateAndtime = date;//+"  "+fromTime+' to '+toTime;
					 $('#updateApptTimeSlotDateAndTimeId'+appointmentId).html(dateArr[2]+"-"+dateArr[0]+"-"+dateArr[1]);
					 
				}
			}else{
				  if(type=="save"){
					  $("#errorDivForTimeSlotId").html("<p style='color:red;font-size:20px'>Failure,Some problem occured while creating time slot.</p>");
				      $('html, body').animate({scrollTop: $("#errorDivForTimeSlotId").offset().top}, 2000); 
				  }else if(type=="update"){
					  $("#updateTimeSlotMsgShow").html("<p style='color:red;font-size:20px'>Failure,Some problem occured while Updating time slot.</p>");
				  }
				  
			}
		});
		
	}
	
	function clearUpdateTimeSlotModalPopUp(){
		
		 $('#appointmentDateSlotModalId').val('');
		 $('#fromTimeModalId').val('');
		 $('#toTimeModalId').val('');
		 $('#showCommentTxt').val('');
		 
		 $('#hiddenTimeSlotModalId').val('');
		 $('#hiddenTimeSlotApptModalId').val('');
		 
		 $("#updateTimeSlotMsgShow").html('');
	}
	
	 $(document).on("click","#addApptsId",function(){
		 setTimeout(function() {$('html, body').animate({scrollTop:2000}, 3000); },0);
	});
	
	$(document).on("click",".viewMembersClass",function(){
		 setTimeout(function() {$('html, body').animate({scrollTop:2000}, 3000); },0);
	});
	$(document).on("click","#delApptsScrollBarId",function(){
		 setTimeout(function() {$('html, body').animate({scrollTop:2000}, 3000); },0);
	});


function getDistrictsForReferPopup()
{
	var stateId = $("#stateId").val();
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
		//str+='<option value="select">Select District</option>';
		str+='<option value="0">ALL</option>';
		if(result != null && result.length > 0){
			for(var i in result){
				str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
			}
		}
		$("#referdistrictId").html(str);
		$("#referdistrictId").dropkick();
		var select1 = new Dropkick("#referdistrictId");
		select1.refresh();
	});
 }
 
 
 function getConstituenciesBydistrictForReferPopup()
{
var constiStr ='';
	if($("#referdistrictId").val() == 0)
	{
	 setDefault();
	 return;	 
	}
	 var levelId = $("#levelId").val();
	 if(levelId == 10 || levelId == 11)
		  return; 
$("#referconstituencyId").find("option").remove();
$("#referconstituencyId").html('');
	
	var districtId= $("#referdistrictId").val();
	var jobj = {
	districtId:districtId,
	task       : ""
	}
	if(districtId > 0)
	{
		$.ajax({
			type : "POST",
			url  : "getConstituenciesByDistrictAction.action",
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			if(result != null && result.length > 0){
			    constiStr +='<option value="0">All</option>';
				for(var i in result){
					constiStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
					}
			 $("#referconstituencyId").html(''+constiStr+'');
			 $("#referconstituencyId").dropkick();
			 var select = new Dropkick("#referconstituencyId");
			select.refresh();
			//getMandalsByConstituency();
			}
		});
	
  }
}
 function setDefault1()
  {
	  $("#refermandalNameId").find('option').not(':first').remove();
	  $("#referpanchayatId").find('option').not(':first').remove();

	  var select = new Dropkick("#refermandalNameId");
		select.refresh();
		var select = new Dropkick("#referpanchayatId");
		select.refresh();
  }
function getMandalsByConstituencyForReferPopup()
{
var mandalStr ='';
if($("#referconstituencyId").val() == 0)
	{
	 setDefault1();
	 return;	 
	}
	$("#refermandalNameId").html('');
	var constituencyId = $('#referconstituencyId').val();
	var jobj = {
	constituencyId:constituencyId,
	task       : ""
	}
	if(constituencyId > 0){
		$.ajax({
		 type : "POST",
		 url  : "getMandalsByConstituencyAction.action",
		data : {task:JSON.stringify(jobj)} 
		}).done(function(result){
			if(result != null && result.length > 0){
			mandalStr +='<option value="0">All</option>';
				for(var i in result)
				{
					mandalStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
				}
			 $("#refermandalNameId").html(''+mandalStr+'');
			 $("#refermandalNameId").dropkick();
			 var select = new Dropkick("#refermandalNameId");
			 select.refresh();
			}
		});
		}
}	
	
function getPanchayatsForReferPopup(){
			$("#referpanchayatId").find('option').not(':first').remove();
			var mandalId =  $("#refermandalNameId").val();
			var  type = $("#refermandalNameId option:selected").text();
			  if(mandalId == 0){
				return;
			  }
			  var levelId = $("#levelId").val();
				if(levelId == 5)
				return;
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
			 for(var i in result){
			   $("#referpanchayatId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			 }
			  $("#referpanchayatId").dropkick();
			 var select = new Dropkick("#referpanchayatId");
			 select.refresh();
		   });
		}
	//disable by level
  function disableByLevel()
  {
	  setDefault();	
	  var levelId = $("#levelId").val();
	  //alert(levelId);
	  var districtId = $("#referdistrictId").val();
	  //alert(districtId);
	  var constituencyId = $("#referconstituencyId").val();
	  var panchayatId = $("#referpanchayatId").val();
	  var mandalId = $("#refermandalNameId").val();
	  var select = new Dropkick("#referdistrictId");
		select.refresh();
		
		if(levelId != 10 && levelId != 0)
		{
			getDistrictsForReferPopup();
		}
		
		if(levelId == 10 || levelId == 0)
		{
			  $("#referdistrictId").find('option').not(':first').remove();
			  $("#referconstituencyId").find('option').not(':first').remove();
			  $("#refermandalNameId").find('option').not(':first').remove();
			  $("#referpanchayatId").find('option').not(':first').remove();
			   var select = new Dropkick("#referdistrictId");
				select.refresh();
			   var select = new Dropkick("#referconstituencyId");
				select.refresh();
				var select = new Dropkick("#refermandalNameId");
				select.refresh();
				var select = new Dropkick("#referpanchayatId");
				select.refresh();
				$(".stateCls").show();
				$(".distCls").hide();
				$(".constiCls").hide();
				$(".mandalCls").hide();
				$(".panchayatCls").hide();
				
		}
		else if(levelId == 11)
		{
			  $("#referconstituencyId").find('option').not(':first').remove();
			  $("#refermandalNameId").find('option').not(':first').remove();
			  $("#referpanchayatId").find('option').not(':first').remove();
			   var select = new Dropkick("#referconstituencyId");
				select.refresh();
				var select = new Dropkick("#refermandalNameId");
				select.refresh();
				var select = new Dropkick("#referpanchayatId");
				select.refresh();
				$(".stateCls").show();
				$(".distCls").show();
				$(".constiCls").hide();
				$(".mandalCls").hide();
				$(".panchayatCls").hide();
		}
		else if(levelId == 1)
		{
			  $("#referconstituencyId").find('option').not(':first').remove();
			  $("#refermandalNameId").find('option').not(':first').remove();
			  $("#referpanchayatId").find('option').not(':first').remove();
			   var select = new Dropkick("#referconstituencyId");
				select.refresh();
				var select = new Dropkick("#refermandalNameId");
				select.refresh();
				var select = new Dropkick("#referpanchayatId");
				select.refresh();
				$(".stateCls").show();
				$(".distCls").show();
				$(".constiCls").show();
				$(".mandalCls").hide();
				$(".panchayatCls").hide();
		}
		else if(levelId == 5)
		{
			$("#referpanchayatId").find('option').not(':first').remove();
			var select = new Dropkick("#referpanchayatId");
				select.refresh();
				$(".stateCls").show();
				$(".distCls").show();
				$(".constiCls").show();
				$(".mandalCls").show();
				$(".panchayatCls").hide();
		}
		else if(levelId == 6)
		{
			$("#referpanchayatId").find('option').not(':first').remove();
			var select = new Dropkick("#referpanchayatId");
				select.refresh();
				$(".stateCls").show();
				$(".distCls").show();
				$(".constiCls").show();
				$(".mandalCls").show();
				$(".panchayatCls").show();
		}
		
  }
  function setDefault()
  {
	  $("#referconstituencyId").find('option').not(':first').remove();
	  $("#refermandalNameId").find('option').not(':first').remove();
	  $("#referpanchayatId").find('option').not(':first').remove();
	    var select = new Dropkick("#referconstituencyId");
		select.refresh();
		var select = new Dropkick("#refermandalNameId");
		select.refresh();
		var select = new Dropkick("#referpanchayatId");
		select.refresh();
  } 
   
	
	function getAllCandidateTypes(){
		$.ajax({
			type : 'GET',
			url : 'getAllCandidateTypesAction.action',
			dataType : 'json',
			data : {}
		}).done(function(result){ 
			if(result != null && result.length > 0){
				//app-appointment
				buildAllCandidateTypes(result);
			}
		});
	}
	
	function buildAllCandidateTypes(result){
		$(".cloneCandidateTypeCls option").remove(); 
		//$(".addCandidateTypeCls option").remove(); 
		$("#candidateType option").remove(); 
			$(".cloneCandidateTypeCls").append('<option value="0">Select Candidate Type</option>'); 
			$("#candidateType").append('<option value="select">Select Candidate Type</option>');
			$("#candidateType").append('<option value="0" selected>All</option>');
			for(var i in result){
				$(".cloneCandidateTypeCls").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
				$("#candidateType").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
			$("#candidateType").chosen();
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
     
	$(document).on("click",".refreshBlockDiv",function(e){
		$("#LineChart").html('');
		$("#searchStrId").attr("placeholder", "Name or MobileNumber").val("");
		$("#selectStatusId").val(0);
		getTotalAppointmentStatus();
		$(".daterangepicker").hide();
		//getAppointmentUsersDtls();
		getAppointmentStatusCounts();
		getSearchDetails(true);
	});
			
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
	 
	function getAppointmentStatusOverview(){	
	   
		   $("#approvedStatus").html('');
		   $("#approvedStatus").html('0');
		   $("#notAttendedStatus").html('');
		   $("#notAttendedStatus").html('0');
		   $("#cancelledStatus").html('');
		   $("#cancelledStatus").html('0');
		   $("#reScheduledStatus").html('');
		   $("#reScheduledStatus").html('0');
		   
			var aptUserId = $("#appointmentUserSelectBoxId").val();
			var jsObj={
					appointmentUserId : aptUserId,
					task:""
				}
				$.ajax({
					  type:'GET',
					  url: 'getAppointmentStatusOverviewAction.action',
					  data: {task:JSON.stringify(jsObj)}
			   }).done(function(result){
				   if(result!=null && result.length>0){
					   buildAppointmentStatusOverAllView(result);
				   }
			   });	
	}
	function buildAppointmentStatusOverAllView(result){
		var total = 0;
			for(var i in result){
				if(result[i].id == 2 || result[i].id == 10 || result[i].id == 5 || result[i].id == 8){
					total = total + result[i].availableCount;
				}				 
			}
		if(total>0){
			  $("#AllStatus").html('<a class="confirmViewClass" style="cursor:pointer;color:white" attr_statusId="0">'+total+'</a>');	
		}else{
			  $("#AllStatus").html('<a style="color:white">'+total+'</a>');	
			}
		for(var i in result){
			if(result[i].id == 2){ 
			  $("#approvedStatus").html('<a class="confirmViewClass" style="cursor:pointer;color:white" attr_statusId='+result[i].id+'>'+result[i].availableCount+'</a>');
			}
			if(result[i].id == 10){
			  $("#notAttendedStatus").html('<a class="confirmViewClass" style="cursor:pointer;color:white" attr_statusId='+result[i].id+'>'+result[i].availableCount+'</a>');
			  // $("#notAttendedStatus").html(''+result[i].availableCount+''); 
			}
			if(result[i].id == 5){
				$("#cancelledStatus").html('<a class="confirmViewClass" style="cursor:pointer;color:white" attr_statusId='+result[i].id+'>'+result[i].availableCount+'</a>');
				// $("#cancelledStatus").html(''+result[i].availableCount+''); 
			}
			if(result[i].id == 8){
			  $("#reScheduledStatus").html('<a class="confirmViewClass" style="cursor:pointer;color:white" attr_statusId='+result[i].id+'>'+result[i].availableCount+'</a>');
			  // $("#reScheduledStatus").html(''+result[i].availableCount+''); 
			}
		}
	}
	 
	$("#candidateTypeAddSelId").change(function(){
		getDesignationsByTypeForAddAptmnt(); 
	});
	 
  function getDesignationsByTypeForAddAptmnt()
	{
		var typeId = $("#candidateTypeAddSelId").val();	
		var jsObj = {
			typeId : typeId,
			task:""
		}
		
		$.ajax({
			type : 'GET',
			url : 'getAppCandidateDesigListByTypeAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){ 
			if(result != null && result.length > 0){
				//app-appointment
				buildDesignationForAddApp(result);
			}			
		});
	}
	
	function buildDesignationForAddApp(result){
		$("#manageAppDesigId  option").remove();
		$('#manageAppDesigId').append('<option value="select">Select Designation</option>');
		$('#manageAppDesigId').append('<option value="0" selected>All</option>');
		for(var i in result){
			$('#manageAppDesigId').append('<option value='+result[i].id+' typeId='+result[i].orderId+' >'+result[i].name+'</option>');
		}
		var select = new Dropkick('#manageAppDesigId');
		select.refresh();
	}
	
	
	  function getAppointStatusOverviewforCandidate(id){
		  $("#aptCandidateHistorystatusOverViewDiv").html('<img src="images/search.gif" />');
	    	var jsObj={
	    			appointmentCandidateId:id,
					apptUserId:$("#appointmentUserSelectBoxId").val(),
					task:""
	    		}
	    		$.ajax({
	    			  type:'GET',
	    			  url: 'getAppointStatusOverviewforCandidateAction.action',
	    			  data: {task:JSON.stringify(jsObj)}
	    	   }).done(function(result){
					
					buildAppointmentStatusOverView(result);
					
	    	   });	
		  }
	function buildAppointmentStatusOverView(result){
			  
			var str = '';
			var total = 0;
			for(var i in result)
			{
				total = total + result[i].availableCount;
			}
			str+='<p>Total Appointment Requested - '+total+'</p>';
			str+='<table class="table table-bordered">';
			str+='<tr class="text-center">';
			for(var i in result)
			{
			
			str+='<td>';
			str+='<h4>'+result[i].availableCount+'</h4>';
			str+='<h5>'+result[i].name+'</h5>';
			str+='</td>';
			
			}
			str+='</tr>';
			str+='</table>';
		   $("#aptCandidateHistorystatusOverViewDiv").html(str);			
	}
	
	
	function getAppointmentHistoryForCandidate(id){
			$("#aptCandidateHistoryDiv").html('<img src="images/search.gif" />');
			//$("#buildCommentsForHistoryView").html('<img src="images/search.gif" />');
			var jsObj={
					appointmentCandidateId:id,
					apptUserId:$("#appointmentUserSelectBoxId").val(),
					task:""
				}
				$.ajax({
				  type : 'GET',
				  url : 'getAppointmentHistoryForCandidateAction.action',
				  dataType : 'json',
				  data : {task:JSON.stringify(jsObj)}
				}).done(function(result){ 
					  buildAppointmentHistoryForCandidate(result);
					  buildAppointmentCommentsForViewHistory(result);
				 });
	}
	  
	function buildAppointmentHistoryForCandidate(result)
	{
		var str='';
		str+='<table class="table table-condensed" style="border:1px solid #ddd" id="aptCandidateHistorydatatable">';
		str+='<thead>';
		str+='<th>ID</th>';
		str+='<th>PURPOSE</th>';
		str+='<th>CREATED ON</th>';
		str+='<th>PREFERED DATE</th>';
		str+='<th>SCHEDULED DATE</th>';
		str+='<th>STATUS</th>';
		str+='<th></th>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result)
		{
		str+='<tr>';
		str+='<td>'+result[i].uniqueCode+'</td>';
		str+='<td>'+result[i].purpose+'</td>';
		str+='<td>'+result[i].createdOn+'</td>';
		str+='<td>'+result[i].preferredDate+'</td>';
		str+='<td>'+result[i].confirmedDate+'</td>';
		str+='<td>'+result[i].status+'</td>';
		str+='<td><img onclick="getAppointCommentsForTracking(\''+result[i].id+'\',\''+result[i].uniqueCode+'\')" style="height:16px;cursor:pointer;margin-right:5px;" title="View Status History" attr-aptname="'+result[i].uniqueCode+'" attr-id="'+result[i].id+'" class="pull-right " src="dist/Appointment/img/reqHistoryicon+.png">';
		str+='</tr>';	
		}
		str+='</tbody>';
		str+='</table>';
		str+='<div id="appointmentCommentsDiv" class="m_top30"></div>';
		$("#aptCandidateHistoryDiv").html(str);	
	     $('#aptCandidateHistorydatatable').DataTable();
	}
	
	function getAppointCommentsForTracking(id,name)
	{
		$("#appointmentCommentsDiv").html('<img src="images/search.gif" />');
		var jsObj={
	    			appntmntId:id,
					task:""
	    		}
		$.ajax({
		  type : 'GET',
		  url : 'getAppointmentStatusCommentsTrackingDetails.action',
		  dataType : 'json',
		  data : {task:JSON.stringify(jsObj)}
		}).done(function(result){ 
		  buildAppointCommentsForTracking(result,name);
		});
		
	}
	function buildAppointCommentsForTracking(result,aptName){
		
		var str='';
		str+='<h4>'+aptName+' Appointment Status Tracking </h4>';
		if(result == null || result.length == 0)
			$("#appointmentCommentsDiv").html('No Data Available');
			str+='<ul class="apptStatusTracking">';
				for(var i in result){
					str+='<li>';
						str+='<div class="arrow_box">';
						if(result[i].id == 1)
							str+='<p> <span class="text-success"></span> Appointment Created on '+result[i].date+' By <b>'+result[i].uname+'</b> </p>';	
						else
							str+='<p>Appointment status changed to <span class="text-success"><b>'+result[i].status+'</b></span> on '+result[i].date+' By <b>'+result[i].uname+'</b> </p>';
						if(result[i].commentsList != null && result[i].commentsList.length > 0 && result[i].commentsList[0].length > 0){
								str+='<u style="font-size:15px;">Comments</u>';
							for(var j in result[i].commentsList){
					
								str+='<p>'+result[i].commentsList[j]+'</p>';	
							}
						}	
				
						str+='</div>';
					str+='</li>';	
				}
		str+='</ul>';
		$("#appointmentCommentsDiv").html(str);
		$('html,body').animate({scrollTop: $("#appointmentCommentsDiv").offset().top}, 'slow');
	}
	function buildAppointmentCommentsForViewHistory(result){
		
		var str ='';
		str+='<div class="row">';
		str+='<div class="col-xs-12">';
		str+='<div class="row">';
		str+='<div class="col-md-3" style="font-size: 18px;font-weight:bold;"><span style="font-weight: bold;"><i class="glyphicon glyphicon-comment"></i> &nbsp;&nbsp;</span>Comments</div>';
		str+='</div>';
		str+='<table class="table table-bordered table-condensed" id="commentsdatatable">';
		str+='<thead>';
		str+='<th style="padding:0px"></th>';
		str+='</thead>';
		str+='<tbody>';
		if(result != null && result.length > 0){
			for(var i in result){
				if(result[i].commentlist != null && result[i].commentlist.length>0){
					/* for(var j in result[i].commentlist){
						str+='<tr>';
						str+='<td>';
						str+='<div class="row">';
						str+='<div class="col-md-3" style="font-size: 11px;"><span style="font-weight: bold;">Appointment ID :&nbsp;&nbsp;</span>'+result[i].commentlist[j].uniqueCode+'</div>';
						str+='<div class="col-md-3" style="font-size: 11px;"><span style="font-weight: bold;">Status :&nbsp;&nbsp;</span>'+result[i].commentlist[j].status+'</div>';
						str+='<div class="col-md-3" style="font-size: 11px;"><span style="font-weight: bold;">Date :&nbsp;&nbsp;</span>'+result[i].commentlist[j].createdOn+'</div>';
						str+='</div>';
						str+='<p style="margin-top: 10px;">'+result[i].commentlist[j].comment+'</p>';
						str+='<p class="text-bold" style="margin-top: 10px;">Comment By - '+result[i].commentlist[j].user+'</p>';
						str+='</td>';
						str+='</tr>';
					} */
				}
				
			}
		}
		str+='</tbody>';
		str+='</table>';
		str+='</div>';
		str+='</div>';
		//$("#buildCommentsForHistoryView").html(str);
		  $('#commentsdatatable').DataTable();
	}
	$(document).on("click",".sendsms",function() {
		
		var flag = false;
		
		var appointmentId = $(this).attr("value");
		$(".msgDiv1"+appointmentId).html("").css("color","");;
		var smsText = $(".sendSms"+appointmentId).val().trim();
		if(smsText == "" || smsText.length == 0){
		  $(".msgDiv1"+appointmentId).html("Sms Text is Required..").css("color","red");
		  flag = true;
		}
		if(flag == true){
			return;
		}
		var jsObj={
				appointmentId : appointmentId,
				smsText:smsText
			}
			$.ajax({
				  url      :   "sendSMSForAppointmtAction.action",
				  type     :   "POST",
				  datatype :   "json",
				  data     :  { task:JSON.stringify(jsObj) },
				  success  :  function (result) {
								  $(".msgDiv1"+appointmentId).html("Sms Sent Successfully").css("color","green");
								  setTimeout(function(){ $(".msgDiv1"+appointmentId).html(""); },2000); 
								  $(".sendSms"+appointmentId).val('');
							  },
				  error     : function (jqXHR, textStatus, errorThrown) {
								  if(jqXHR.status == 500){
									  $(".msgDiv1"+appointmentId).html("Unexpected Error.Please Try Again..").css("color","red");
								  }else{
									 alert('Unexpected error.');
								  }
							  }
			 }); 
	});
	 function clearFields(){
		$("#multiDate").val("");
		$("#appointmentReasonId").val(""); 
		$("#candidateNameId0").val(""); 
		/* $(".cloneCandidateTypeCls").val(0); 
		$(".cloneDesignationCls ").val(0); */
		$("#mobileNoId0").val(""); 	
		$("#voterCardNoID0").val(""); 
		$("#membershipNumId0").val(""); 
		/* $(".cloneLocationScopeCls").val(0); 
		$(".cloneDistrictCls").val(0); 
		$(".cloneConstituencyCls").val(0);  
		$(".cloneMandalCls").val(0);
		$(".cloneVillageCls").val(0);  */ 		
	 }
	
	function getPublicRepresentativeWiseAppointmentCnt(){
		
		var appointmntCandteId=$("#appointmentUserSelectBoxId").val();
		var jsObj = {
			apontntCnditeId:appointmntCandteId,
			task:""
		}
	$.ajax({
		type : 'GET',
		url : 'getPublicRepresentativeWiseAppointmentCntAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){ 

		if(result != null){
			buildPublicRepresentativeWiseAppointmentCnt(result,jsObj);
		}
		
	});     
}
	function buildPublicRepresentativeWiseAppointmentCnt(result,jsObj)
	{
	   var str='';
		str+='<div class="block">';
		  str+='<div class="row">';
			str+='<div class="col-md-12">';
			  str+='<h4 class="text-capitalize text-success">public representative wise appointments</h4>';
			  str+='<table class="table table-bordered" id="dataTablePublicId">';
				str+='<thead>';
				  str+='<tr>';
					str+='<th></th>';
					str+='<th class="text-capitalize text-center" colspan="2">total</th>';
					str+='<th class="text-capitalize text-center" colspan="2">requested</th>';
					str+='<th class="text-capitalize text-center" colspan="2">appointment scheduled</th>';
				  str+='</tr>';
				  str+='<tr>';
				  // str +='<th></th>';
					str+='<th class="text-capitalize">role</th>';
					str+='<th class="text-capitalize">total</th>';
					str+='<th class="text-capitalize">unique</th>';
					str+='<th class="text-capitalize">total</th>';
					str+='<th class="text-capitalize">unique</th>';
					str+='<th class="text-capitalize">total</th>';
					str+='<th class="text-capitalize">unique</th>';
				  str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
				for(var i in result){
				  str+='<tr>';
				  str+='<td>'+result[i].role+'</td>';
				  if(result[i].total>0)
				  {
				  str+='<td><a target="_blank" data-toggle="tooltip" data-placement="top" title="Click here to View '+result[i].role+' Wise Appointments" style="cursor:pointer;" onclick="getMemebersByScheduleType(\''+result[i].roleId+'\',\'PR\',\'unique\',\'total\',\''+jsObj.appointmentCndiateId+'\');">'+result[i].total+'</a></td>';
				  }
				  else
				  {
					str+='<td>'+result[i].total+'</td>';
				  }
				  if(result[i].uniquecnt>0)
				  {
				  str+='<td><a target="_blank" data-toggle="tooltip" data-placement="top" title="Click here to View '+result[i].role+' Wise Appointments" style="cursor:pointer;" onclick="getMemebersByScheduleType(\''+result[i].roleId+'\',\'PR\',\'unique\',\'total\',\''+jsObj.appointmentCndiateId+'\');">'+result[i].uniquecnt+'</a></td>';
				  }
				  else
				  {
				  str+='<td>'+result[i].uniquecnt+'</td>';  
				  }
				  if(result[i].requestedCnt>0)
				  {
				  str+='<td><a target="_blank" data-toggle="tooltip" data-placement="top" title="Click here to View '+result[i].role+' Wise Appointments" style="cursor:pointer;" onclick="getMemebersByScheduleType(\''+result[i].roleId+'\',\'PR\',\'unique\',\'Request\',\''+jsObj.appointmentCndiateId+'\');">'+result[i].requestedCnt+'</a></td>';
				  }
				  else
				  {
					str+='<td>'+result[i].requestedCnt+'</td>';
				  }
				  if(result[i].uniqueRequestedCnt>0)
				  {
				  str+='<td><a target="_blank" data-toggle="tooltip" data-placement="top" title="Click here to View '+result[i].role+' Wise Appointments" style="cursor:pointer;" onclick="getMemebersByScheduleType(\''+result[i].roleId+'\',\'PR\',\'unique\',\'Request\',\''+jsObj.appointmentCndiateId+'\');">'+result[i].uniqueRequestedCnt+'</a></td>';
				  }
				  else
				  {
					str+='<td>'+result[i].uniqueRequestedCnt+'</td>';
				  }
				  if(result[i].scheduledCnt>0)
				  {
				  str+='<td><a target="_blank" data-toggle="tooltip" data-placement="top" title="Click here to View '+result[i].role+' Wise Appointments" style="cursor:pointer;" onclick="getMemebersByScheduleType(\''+result[i].roleId+'\',\'PR\',\'unique\',\'Schedule\',\''+jsObj.appointmentCndiateId+'\');">'+result[i].scheduledCnt+'</a></td>';
				  }
				  else
				  {
				  str+='<td>'+result[i].scheduledCnt+'</td>';  
				  }
				  if(result[i].uniqueScheduledCnt>0)
				  {


				str+='<td><a target="_blank" data-toggle="tooltip" data-placement="top" title="Click here to View '+result[i].role+' Wise Appointments" style="cursor:pointer;" onclick="getMemebersByScheduleType(\''+result[i].roleId+'\',\'PR\',\'unique\',\'Schedule\',\''+jsObj.appointmentCndiateId+'\');">'+result[i].uniqueScheduledCnt+'</a></td>';
				  }
				  else
				  {
					  str+='<td>'+result[i].uniqueScheduledCnt+'</td>';  
				  }
					
				  str+='</tr>';
				}
				str+='</tbody>';
			  str+='</table>';
			str+='</div>';
		  str+='</div>';
		str+='</div>';
		$("#advanceDshAppointmentPrWiseDiv").html(str);
		
		$("#dataTablePublicId").DataTable({});
	}
	
	function getStatusTrackingDetls(appontmntId,aptName){
		
		var jsObj={
			appntmntId : appontmntId
		}
		$.ajax({
			type : 'POST',
			url : 'getAppointmentStatusCommentsTrackingDetails.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				apptTrackingStatus(result,aptName);
			}else{
				$("#apptStatusTracking").html("<center>No Data Available</center>")
			}
		});
	}


	function apptTrackingStatus(result,aptName)
	{
		setcolorsForStatus();
		var str='';
		$("#statusTrackingName").html(''+aptName+' Appointment Status Tracking')
			str+='<ul class="apptStatusTracking">';
		for(var i in result){
			var color = getColorCodeByStatus(result[i].status);
			str+='<li>';
				str+='<div class="arrow_box_left">';
				if(result[i].id == 1)
				str+='<p> <span class="text-success"></span> Appointment Created on '+result[i].date+' By <b>'+result[i].uname+'</b> </p>';	
					else
					str+='<p>Appointment status changed to <span class="" style="color:'+color+'"><b>'+result[i].status+'</b></span> on '+result[i].date+' By <b>'+result[i].uname+'</b> </p>';
					if(result[i].commentsList != null && result[i].commentsList.length > 0 && result[i].commentsList[0].length > 0)
					{
						str+='<u style="font-size:15px;">Comments</u>';
						for(var j in result[i].commentsList)
						{
						
						str+='<p>'+result[i].commentsList[j]+'</p>';	
						}
					}
					
				str+='</div>';
			str+='</li>';	
		}
		str+='</ul>';
		$("#apptStatusTracking").html(str)
	}

	$(document).on("click",".confirmViewClass",function(){
		var statusId = $(this).attr("attr_statusId");
		getSerchDetailsByStatus(statusId);
	});
	function getSerchDetailsByStatus(statusId){
	//getAppointmentsBySearchCriteria();
	
		 var fromDate='';
		var toDate='';
		/*var dateStr = $("#addMembersFromDateId").val(); 
		if(dateStr !=null && dateStr.length>0){
			fromDate = dateStr.split("-")[0];
			toDate = dateStr.split("-")[1];
		}
		 
		 var designationId=$("#manageAppDesigId").val();
		 var priorityId= $("#manageAppTypeId").val();
		 var statusId=$("#manageAppStatusId").val();
		 var districtId = $("#manageAppDistId").val();
		 var constituencyId = $("#manageAppConstId").val();
		 var candidateTypeId = $("#candidateTypeAddSelId").val();
		 
		 if(candidateTypeId ==null && candidateTypeId.length == 0){
			  $("#addErrCandidateTypeAddCls").html("Select Candidate Type.");
				return;	
		 }
		 if(designationId=="select"){
		  $("#appDesigErrId").html("Select Designation.");
           return;		  
		 }		 
		  
		 if(priorityId=="select"){
		  $("#appPrrtyErrTypId").html("Select Priority Type.");
           return;		  
		 }
		 
		 if(statusId=="select"){
		  $("#appStatusErrId").html("Select Appointment Status.");
           return;		  
		 }
		 if(districtId=="select"){
		  $("#appDistErrId").html("Select District.");
           return;		  
		 }
		 if(constituencyId=="select"){
		  $("#appConstErrId").html("Select Constituency.");
           return;		  
		 } */
		 
		 //clearing drag drop time slot div 
		 $("#confirmAppointmentBlockDropId").empty();
		 $("#confirmAppointmentBlockDropId").html("<h4 class='deleteTag'>DROP HERE</h4>");
		 var jsObj={
			designationId:0,
			priorityId:0,
			statusId:statusId,
			districtId:0,
			constituencyid:0,
			appointmentlabelId : 0,
			fromDate :fromDate,
			toDate:toDate,
			selUserId:$("#appointmentUserSelectBoxId").val(),
			candidateTypeId:0,
			dateType:2,
			apptUserId:$("#appointmentUserSelectBoxId").val()
		  }
		  $.ajax({
				type : 'POST',
				url : 'getAppointmentsBySearchCriteriaAction.action',
				dataType : 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				buildLabelResult(result,labelName);
			});

	}
	$(document).on("click",".appointmentStatusCls",function(){
	
		 var statusId=$(this).attr("attr_appntmnt_status_id");
         //display processing img
	    $("#appntmntPrcssngImgId"+statusId).show();
	     
		 var statusType=$(this).attr("attr_status_type");
		  if(statusType==undefined || statusType==" " || statusType==null){
			  statusType='singleStatus';
		  }
		 $('html, body').animate({
			scrollTop: $('.showTimeSlotsCls').offset().top
		}, 2000);
		
		var statusArray =[];
		 $(this).each(function(){
			 var statusIds= $(this).attr("attr_statusArrId");
			 if(statusIds.indexOf(',') !== -1){
				 statusArray = statusIds.split(",");
			 }else{
				 statusArray.push( statusIds );
			 }	
		 });
		
		getappointmentStatusDetails(statusArray,null,statusType,statusId);			
	
	});

	function  getappointmentStatusDetails(statusArray,type,statusType,statusId){
	
	if(type ==null){
			type='';
		}
		//clearing the searchAppointment div
		$("#searchApptmntDivId").empty();
		$("#srhPrcssngImgId").show();
		var jsObj={
				createdBy : 0,
				appointmentUserId:$("#appointmentUserSelectBoxId").val(),
				searchStr:'',
				strDate:'',
				endDate:'',
				statusArray:statusArray,
				type:type,
				task:""			
			}
				$.ajax({
					type : 'POST',
					url : 'getAppointmentSearchDetailsAction.action',
					dataType : 'json',
					data: {task:JSON.stringify(jsObj)}
				}).done(function(result){
					$("#srhPrcssngImgId").hide();
					$("#appntmntPrcssngImgId"+statusId).hide();
					$("#tdyAppntmntPrcssngImgId"+statusId).hide();
					$("#tdyAppntmntPrcssngImgId0").hide();
					buildAppointmentSearchResult(result," ",statusType);		
				})
	}

  $(document).on("click",".todayappointmentStatusCls",function(){
		
	   var statusId=$(this).attr("attr_appntmnt_status_id");
      //display processing img
	  $("#tdyAppntmntPrcssngImgId"+statusId).show();
	  
		 var statusType=$(this).attr("attr_status_type");
		  if(statusType==undefined || statusType==" " || statusType==null){
			  statusType='singleStatus';
		  }
		 $('html, body').animate({
			scrollTop: $('.showTimeSlotsCls').offset().top
		}, 2000);

		var statusArray =[];
		 $(this).each(function(){
			 var statusIds= $(this).attr("attr_todayStatusArr");
			 if(statusIds.indexOf(',') !== -1){
				 statusArray = statusIds.split(",");
			 }else{
				 statusArray.push( statusIds );
			 }	
		 });
		getappointmentStatusDetails(statusArray,"today",statusType,statusId);			
	
	}); 

	function getTimeSlotsForADayByAppytUserId(){
	
	$('#timeSlotErrMsgId').html('');
	$('#timeSlotDatesBuildId').html('');
	$("#ajaxImgFortimeSlotButtonId").show();
	var  dateStr       = $('#appointmentDateSlotHeadingId').val();
	var  appntmntId   =  $("#appointmentUserSelectBoxId option:selected").val();
	if(dateStr.trim().length <= 0){
		$('#timeSlotErrMsgId').html('Please Select Date');
		return;
	}
	var jsObj={
			dateStr : dateStr,
			appntmntId:appntmntId
		}
		  	$.ajax({
				type : 'POST',
				url : 'getTimeSlotsForADayByAppytUserIdAction.action',
				dataType : 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				$("#ajaxImgFortimeSlotButtonId").hide();
				timeSlotTableBuilding(result,dateStr);
			})
	}

function timeSlotTableBuilding(result,dateStr){
	
	var str='';
	str+='<div class="pluginTable">';
			str+='<ul class="row">';
			
				str+='<li class="col-md-2 col-xs-4 col-sm-2 m_top10">';
					str+='<table class="table tablePluginDate m_top20">';
						str+='<tr>';
							str+='<td class="text-center" style="height:29px;background-color:none;">'+dateStr+'</td>';
						str+='</tr>';
					str+='</table>';
				str+='</li>';
				
				str+='<li class="col-md-10 col-xs-8 col-sm-10">';
				str+='<table class="table table-bordered tablePlugin">';
					
						str+='<thead>';
							str+='<th colspan="4">6a</th>';
							str+='<th colspan="4">7</th>';
							str+='<th colspan="4">8</th>';
							str+='<th colspan="4">9</th>';
							str+='<th colspan="4">10</th>';
							str+='<th colspan="4">11</th>';
							str+='<th colspan="4">12p</th>';
							str+='<th colspan="4">1</th>';
							str+='<th colspan="4">2</th>';
							str+='<th colspan="4">3</th>';
							str+='<th colspan="4">4</th>';
							str+='<th colspan="4">5</th>';
							str+='<th colspan="4">6</th>';
							str+='<th colspan="4">7</th>';
							str+='<th colspan="4">8</th>';
							str+='<th colspan="4">9</th>';
							str+='<th colspan="4">10</th>';
						str+='</thead>';
				
	                   str+='<tr class="borderSlot">';
						for(var unique=0;unique<=63;unique++){
				          str+='<td id="'+unique+'"></td>';
			            }	
	                  str+='</tr>';
	
					//$('#timeSlotDatesBuildId').html(str);
					
					if(result!=null && result.length>0){
					
					for(var i in result){
					 
						var start=result[i].startDate;
						var end =result[i].endDate;
						var startIdForHour=start.substr(11,2);
						var startIdForMin=start.substr(14,2);
						var startId=(startIdForHour-6)*4;
					
						startId= startId+(startIdForMin/15);
						var strtDividedVleForMnte=startIdForMin%15;
						   if(strtDividedVleForMnte>=8){
							   startId=startId+1;
						   }
						var endIdForHour=end.substr(11,2);
						var endIdForMin=end.substr(14,2);
						var endId=(endIdForHour-6)*4;
						
						endId= endId+(endIdForMin/15);
						var endDividedVleForMnte=endIdForMin%15;
						   if(endDividedVleForMnte>=8){
							   endId=endId+1;
						   }
						endId=endId-1;  
						for(var start=startId;start<=endId;start++){
							$("#"+start).addClass("bookedSlots");
						}
				}
		    }
	}
	function getAllScheduledApptsByDate(param){
	 
	    var  dateStr       = $('#appointmentDateSlotHeadingId').val();
		var  apptUserId   =  $("#appointmentUserSelectBoxId option:selected").val();
		//scrolling page 
		 if(param!=null && param!=undefined){
			 if($(param).attr("id")=="timeSlotButtonId"){
				$('html,body').animate({scrollTop: $("#appointmentMembersDivId").offset().top}, 2000);
			 }
		} 
	var jsObj={
			dateStr : dateStr,
			apptUserId:apptUserId
		}
		
		  	$.ajax({
				type : 'POST',
				url : 'getAllScheduledApptsByDateAction.action',
				dataType : 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				buildAppointmentMembersDetails(result);
			})
	}
	function buildAppointmentMembersDetails(result){
		
		setcolorsForStatus();
		var str = '';
		var flag = false;
		str+='<div class="upcomingAppointments heightAdjust">';
		
		str+='<div class="updateAppointment arrow_box">';
			str+='<label class="radio-inline">';
		str+='<input type="radio" value="6" name="CompletedRadio1" class="statusAllCompleted" checked>Reschedule';
		str+='</label>';
		str+='<label class="radio-inline">';
		str+='<input type="radio" value="7" name="CompletedRadio1" class="statusAllCompleted">Cancel';
		str+='</label>';
		str+='<textarea class="form-control m_top10 CompletedSmsText" ></textarea>';
		str+='<span class="msgDiv2Completed"></span>';
		str+='<button class="btn btn-block btn-success updateAll" value="Completed">UPDATE APPOINTMENT</button>';
		str+='</div>';
		str+='<div><h4 class="text-success">APPOINTMENT TIMESLOT UPDATION</h4></div>';
		if(result != null)
		{
			var xindex = 0;
			
			for(var i in result)
			{ 
			if( xindex % 2 == 0)
			{
				str+='<div class="row m_top10">';
			}
					str+='<div class=" col-md-6 col-xs-12 updateChangeClass" >';
					str+='<div class="panel panel-default manageAppViewPanelClass m_top5">';
						str+='<div class="panel-heading bg_ff pad_5">';
							str+='<p class="settingClass" style="font-size:10px;cursor:pointer;"><i  attr_span_popup_id='+result[i].appointmentId+' attr_appt_status_id='+result[i].statusId+' attr_date='+result[i].formatDate+' attr_from_time="'+result[i].time+'" attr_to_time="'+result[i].toTime+'"  attr_comment="'+result[i].subject+'" attr_timeSlotId="'+result[i].apptTimeSlotId+'" class="glyphicon glyphicon-cog updateAppointmentClass  pull-right" id="updateSettingApptId'+result[i].appointmentId+'"  title="Appointment Status Update" data-toggle="tooltip" data-placement="top" ></i>ID: '+result[i].appointmentUniqueId+'&nbsp;&nbsp;&nbsp;';
							var color = getColorCodeByStatus(result[i].appointmentStatus);
							str+='<span style="font-weight:bold;color:'+color+'" id="statusSpanId'+result[i].appointmentId+'">'+result[i].appointmentStatus+'</span>';
							
							if(result[i].date != "" && result[i].statusId == 3){
								var dateAndtime = result[i].date;//+"  "+result[i].time+' to '+result[i].toTime;
								
								str+='<span class="pull-right"><span class="text-success"><i class="glyphicon glyphicon-time"></i>&nbsp;&nbsp;<span id="updateApptTimeSlotDateAndTimeId'+result[i].appointmentId+'">'+dateAndtime+'</span></span> &nbsp;</span>';
							}
							
							str+='</p>';
						str+='</div>';
						str+='<div class="panel-body pad_5">';
						str+='<ul>';
						flag = true;
						for(var j in result[i].subList)
						{
						
						str+='<li>';
						str+='<div class="media m_0">';
						str+='<div class="media-left">';
						str+='<img class="media-object thumbnail" src="'+result[i].subList[j].imageUrl+'" onerror="setDefaultImage(this);" alt="Candidate Image">';
						str+='</div>';
						str+='<div class="media-body">';
						str+='<p>'+result[i].subList[j].name+'</p>';
						str+='<p><i class="fa fa-mobile" style="font-size:15px"></i> &nbsp '+result[i].subList[j].mobileNo+'';
						if(result[i].subList[j].id != null && result[i].subList[j].id > 0){
								str+='<a style="display:inline-block;" title="Appointments History" data-toggle="tooltip" data-placement="top" class="historyShowModalBtn pull-right"  style="cursor:pointer;" attr-id="'+result[i].subList[j].id+'" attr-name="'+result[i].subList[j].name+'" attr-designation="'+result[i].subList[j].designation+'" attr-mobile="'+result[i].subList[j].mobileNo+'"><img src="dist/Appointment/img/view-Appt-History-icon.png"  alt="ViewApptHistory" style="height:16px;cursor:pointer;margin-right:5px;"/></a>&nbsp;&nbsp;';
						}
						str+='</p>';
						str+='</div>';
						str+='</div>';
						//multiple
						
						str+='</li>';
						
						}
						str+='</ul>';
						if(result[i].subject!=null && result[i].subject.length>35){
							  str+='<p class="" style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title="'+result[i].subject+'" >Purpose: '+result[i].subject.substring(0,35)+'...</p>';
							}else{
							  str+='<p class="" style="margin-left: 52px; margin-top: -6px;">Purpose:'+result[i].subject+' </p>';
							}
					str+='<p class="m_top10">';
					str+='<i>Appt Created By: '+result[i].subList[j].createdBy+'</i>';
					str+='<img src="dist/Appointment/img/message.png" class="messageIcon" alt="messageIcon" title="Send Sms" data-toggle="tooltip" data-placement="top" />';
					 
					  str+='<img src="dist/Appointment/img/reqHistoryicon+.png" class="pull-right statusTrackingModalbtn" attr-id='+result[i].appointmentId+' attr-aptName='+result[i].appointmentUniqueId+' alt="ViewReqHistory" style="height:16px;cursor:pointer;margin-right:5px;" title="Appointment Requested History" data-toggle="tooltip" data-placement="top"/>'; 
					str+='</p>';
					str+='<div class="messageBlock arrow_box">';
					str+='<span class="errorCls msgDiv1'+result[i].appointmentId+'"></span>';
					str+='<textarea class="form-control sendSms'+result[i].appointmentId+'" ></textarea>';
					str+='<button class="btn btn-success btn-block sendsms" value="'+result[i].appointmentId+'">SEND SMS</button>';
					str+='</div>';
					str+='</div>';
				str+='</div>';
				str+='</div>';
				//}
				if(xindex % 2 == 1)
				{
					str+='</div>';
				}
				
				if(result.length-1 == xindex && xindex % 3 != 2)
				{
					str+='</div>';
				}	
			xindex++;
			}
			
		}
		else
		{
			flag = false;
			//str+='No Data';	
		}
		
		if(flag == false)
		{
			$(".completedSetting").hide();
			str+='No Data Available';	
		}
		$("#appointmentMembersDivId").html(str);
		
		$('[data-toggle="tooltip"]').tooltip();
		if(flag == false){
		   $(".completedSetting").hide();
		}
			
		}
	$(document).on("click",".updateAppointmentClass",function(e){
		
		$(".appointmentTimeSlotModalpopup").modal("show");
		
		var appId             =  $(this).attr("attr_span_popup_id");
		var scheduledDate     =  $(this).attr("attr_date");
		var fromScheduledTime =  $(this).attr("attr_from_time");
		var toScheduledTime   =  $(this).attr("attr_to_time");
		var commentTxt        =  $(this).attr("attr_comment");
		var appntTimeSlotId   =  $(this).attr("attr_timeSlotId");
	
    	showModalForappointmentTimeSlot();
		 
		 //clear the values
		clearUpdateTimeSlotModalPopUp();
		  
		//initlization dates
		$("#appointmentDateSlotModalId").daterangepicker({singleDatePicker:true,minDate:new Date()});
		$("#toTimeModalId").datetimepicker({format: 'LT'})	
		$("#fromTimeModalId").datetimepicker({format: 'LT'})
		
		// set the values
		$('#appointmentDateSlotModalId').val(scheduledDate);
		//$('#fromTimeModalId').val(fromScheduledTime);
		//$('#toTimeModalId').val(toScheduledTime);
		$('#fromTimeModalId').val("00:00 AM");
		$('#toTimeModalId').val("00:00 PM");
		$('#showCommentTxt').val();
		$('#hiddenTimeSlotModalId').val(appntTimeSlotId);
		$('#hiddenTimeSlotApptModalId').val(appId);
		
	})
	
	function showModalForappointmentTimeSlot(){
		
		var str='';
	 str+='<div class="row m_top20">';
		 str+='<div class="col-md-12">';
			 str+='<div style="background:#F3f3f3;margin:0px -10px;padding:12px 0px;" class="row">';
			 
				 str+='<div id="" class="validateClr m_left16"></div>';
				 
				 str+='<div class="col-md-4">';
					 str+='<label>Select Date</label>';
					 str+='<div class="input-group inputSearch">';
						 str+='<span class="input-group-addon">';
							 str+='<i class="glyphicon glyphicon-calendar"></i>';
							 str+='<span class="caret"></span>';
						 str+='</span>';
						 str+='<input type="text" class="form-control" id="appointmentDateSlotModalId">';
					 str+='</div>';
				 str+='</div>';
				 
				 str+='<div class="col-md-3">';
					 str+='<label>From Time</label>';
					 str+='<div class="input-group inputSearch">';
						 str+='<span class="input-group-addon">';
							 str+='<i class="glyphicon glyphicon-time"></i>';
							 str+='<span class="caret"></span>';
						 str+='</span>';
						 str+='<input type="text" id="fromTimeModalId" class="form-control" style="width: 86px;">';
					 str+='</div>';
				 str+='</div>';
				 
				 str+='<div class="col-md-3">';
					 str+='<label>To Time</label>';
					 str+='<div class="input-group inputSearch">';
						 str+='<span class="input-group-addon">';
							 str+='<i class="glyphicon glyphicon-time"></i>';
							 str+='<span class="caret"></span>';
						 str+='</span>';
						 str+='<input type="text" id="toTimeModalId" class="form-control" style="width: 87px;">';
					 str+='</div>';
				 str+='</div>';
				 
				 str+='<div class="col-md-8 m_top10">';
				 str+='<textarea  placeholder="Please Enter Comment..." cols="40" rows="2" id="showCommentTxt"></textarea>';
				 str+='</div>';
				 str+='<div class="col-md-4">';
					 str+='<button class="btn btn-success m_top25" id="updateTimeSlotForApptId">SET</button>';
					  str+='<div ><center ><img style="display: none;margin-top: -80px;height:20px;width:20px;" src="images/icons/loading.gif" id="updateTimeSlotAjax"></center></div>';
				 str+='</div>';
				 
				   //hidden variable for time slotId
				  str+='<input type="hidden" id="hiddenTimeSlotModalId" class="form-control">';
				  str+='<input type="hidden" id="hiddenTimeSlotApptModalId" class="form-control">';
				  str+='<input type="hidden" id="hiddenTimeSlotApptModalId" class="form-control">';
				  
			 str+='</div>';
		 str+='</div>';
		
	 str+='</div>';
	 
	 $("#appointmentTimeSlotModal").html(str);
	}
	
	$(document).on("click","#updateTimeSlotForApptId",function(e){
		
		setTimeSlotForAppointment( $('#hiddenTimeSlotApptModalId').val() ,$('#appointmentDateSlotModalId').val(),$('#fromTimeModalId').val(),$('#toTimeModalId').val(),'update',$('#hiddenTimeSlotModalId').val(),$('#showCommentTxt').val());
	});
	
	
	$(document).on("click",".confirmViewClass",function(e){
		$(".changeClass").removeClass("col-md-12");
		$(".changeClass").addClass("col-md-8");
		$(".updateChangeClass").addClass("col-md-6"); 
	})
	  $(document).on("click","#timeSlotButtonId",function(e){
		setTimeout(function(){$(".updateChangeClass").addClass("col-md-6");
		}, 500);
	})
	
	$(".saveDesignationCls").click(function(){
		var designation=$(".designationCls").val().trim();
		var cnt=$(this).attr("id");
		 if(designation==" " || designation==undefined || designation.length==0){
			 $(".designationErrCls").html("Please Enter Designation.");
		 }else{
			 saveDesignationForOtherCandidate(designation,cnt); 
		 }
	});
	function saveDesignationForOtherCandidate(designation,cnt){
		
		var jsObj={
			designation:designation,
			candidateTypeId:4
		}
		$.ajax({
			type : 'POST',
			url : 'saveDesignationForOtherCandidateAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result!=null && result!=0){
				if(result.exceptionMsg=="Success"){
					$(".statusCls").html("<p style='color:green;'>Designation Saved Successfully.</p>");
					 setTimeout(function(){$(".statusCls").html("");}, 2000);
					 setTimeout(function(){$("#blockForOtherCandidateModalId").modal("hide");}, 2000);
					 getDesignationsByTypeForChange(cnt,"other");
				}else if(result.exceptionMsg=="exist"){
					$(".statusCls").html("<p style='color:red;text-center'>Designation already exist.</p>");
				}else{
				  $(".statusCls").html("<p style='color:red;text-center'>Error occured try again.</p>");
				}
			}
		});		
	}
	function buildLevels()
	{
		var searchType = $("#advanceSearchTypeId").val();
		var str='';
		 $("#levelId").find('option').remove();
		  str+='<option value="10">State</option>';
		  str+='<option value="11">District</option>';
		  if(searchType != 3)
		  str+='<option value="1">Constituency</option>';
		  str+='<option value="5">Mandal/Muncipality</option>';
		  str+='<option value="6">Village/Ward</option>';
		  $("#levelId").append(str);
		    $("#levelId").dropkick();
			 var select = new Dropkick("#levelId");
			 select.refresh();
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
buildLevels();

$(document).on("click",".timeSlotHideShowCls",function(){
	$(".changeTimeSlotClass").toggle();

	if($(this).hasClass("glyphicon-plus-sign")){
		$(this).removeClass("glyphicon-plus-sign");
		$(this).addClass("glyphicon-minus-sign");
	}else{
		$(this).removeClass("glyphicon-minus-sign");
		$(this).addClass("glyphicon-plus-sign");
	}
});
$(document).on("click",".btnClassChange",function(){
	$(".btnClassChange").removeClass("btnActive");
	$(this).addClass("btnActive");
});

function buildTotalAppointmentStatusForNew(result){
			var str='';
		   var totalApptCount =0;
			if(result!=null && result.length>0){
				for(var i in result){
					totalApptCount = totalApptCount + result[i].statusCount;
				}
			}
			str+='<table class="table table-bordered text-center b_border" style="font-size: 18px; text-transform: uppercase; color: rgb(255, 255, 255);">';
			str+='<tbody>';
			str+='<tr>';
				str+='<td colspan="7" style="background: rgba(10, 37, 63,0.5) none repeat scroll 0px 0px;">TOTAL APPOINTMENTS- '+totalApptCount+'</td>';
			str+='</tr>';
			str+='<tr></tr>';
			str+='<tr>';
			if(result !=null){
				for(var i in result){
					var color = getColorCodeByStatus(result[i].status);
					if(result[i].appointmentStatusId !=2){
						if(result[i].statusCount == 0){
						str+='<td><span style="color: '+color+'">'+result[i].status+' <br><span style="font-weight: bold; font-size: 28px ! important;">'+result[i].statusCount+'</span></span></td>';
						}else{
						 var statusArr= result[i].clickIds;
						 str+='<td class="appointmentStatusCls" attr_appntmnt_status_id='+result[i].appointmentStatusId+' attr_statusArrId ="'+statusArr+'"><span style="color: '+color+';cursor:pointer">'+result[i].status+' <br><span style="font-weight: bold; font-size: 28px ! important;">'+result[i].statusCount+'</span></span><div><center><img style="display:none;height:20px" src="images/icons/loading.gif" id="appntmntPrcssngImgId'+result[i].appointmentStatusId+'"></center></div></td>';
					  }
					
					}else if(result[i].appointmentStatusId ==2){
						var statusArr= result[i].clickIds;
						 str+='<td><span style="color: '+color+';cursor:pointer">'+result[i].status+' - <span style="font-weight: bold; font-size: 28px ! important;"><span class="appointmentStatusCls" attr_appntmnt_status_id='+result[i].appointmentStatusId+' attr_status_type="todayTotal" attr_statusArrId ="'+statusArr+'">'+result[i].statusCount+'</span><center><img style="display:none;height:20px" src="images/icons/loading.gif" id="appntmntPrcssngImgId'+result[i].appointmentStatusId+'"></center></span>';
						
						str+='<table style="font-size: 12px; color: rgb(51, 51, 51);" class="table table-border"><tbody>';
						str+='<tr>';
						
						if(result[i].subList !=null && result[i].subList.length>0){
							for(var j in result[i].subList){
							var internalColor = getColorCodeByStatus(result[i].subList[j].status)		
								if(result[i].subList[j].statusCount == 0){
								str+='<td style="background: '+internalColor+'"> '+result[i].subList[j].status+' - '+result[i].subList[j].statusCount+'</td>';
								}else{
								var statusArr= result[i].subList[j].clickIds;
								 str+='<td class="appointmentStatusCls" attr_appntmnt_status_id='+result[i].subList[j].appointmentStatusId+' attr_statusArrId ="'+statusArr+'" style="background: '+internalColor+';cursor:pointer"> '+result[i].subList[j].status+' - '+result[i].subList[j].statusCount+'   <div><center><img style="display: none;height:20px"    src="images/icons/loading.gif" id="appntmntPrcssngImgId'+result[i].subList[j].appointmentStatusId+'"></center></div></td>';	
								}					
							}						
						}
						str+='</tr>';
						str+='</tbody></table></span></td>';
					}
				}			
			}
			str+='</tr>';
		str+='</tbody>';
		str+='</table>';
	  $("#newStatusBuildingId").html(str);
	}

$(document).on("click",".appointmentAllDetailsModel",function(e){
	
	$("#buildAppointmentAllDetailsDiv").html('');
	$("#appointmentAllDetailsDiv").modal("show");
	$("#buildAppointmentAllDetailsDiv").html('<img class="col-xs-offset-6" src="images/search.gif" style="width: 30px; height: 30px;"/>');
	
	var appointmentId = $(this).attr("attr_appt_id");
		
		var jsObj={
			appointmentId:appointmentId
		}
		
		$.ajax({
			type : 'POST',
			url : 'getTotalAppointmentDetailsAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#buildAppointmentAllDetailsDiv").html('');
			buildappointmentAllDetails(result);
		});	
 })
	
	
	function emptyCheck(value){
		if(value != null && value.trim().length>0){
			return true;
		}
		return false;
	}
	
	function buildappointmentAllDetails(result){
		
		 setcolorsForStatus();
	var str='';
	
	//Header
	
	str+='<div class="modal-header" style="background:#CCCCCC;padding-bottom:0px;">';
		str+='<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>';
			str+='<div class="row">';
			
			  str+='<div class="col-md-7">';
				 str+='<h4 class="modal-title text-capitalize text-success text-capitalize" ><img style="width: 30px; height: 31px;" src="dist/Appointment/img/i_icon.png"/> appointment request information</h4>';
			  str+='</div>';
			  var color = getColorCodeByStatus(result.status);
			  str+='<div class="col-md-4" style="background:url(dist/Appointment/img/apptDetailsStrip.png) no-repeat; padding:11px;margin-top:-21px">';
				  str+='<p style="margin-top: 5px;text-align: center;">Appointment Status: <span  style="color:'+color+'">'+result.status+'</span></p>';
				  if(result.statusId==3){
					  var dateTime = result.date //+"  "+result.fromTime+" to "+result.toTime;
					  str+='<p class="text-success" style="text-align:center;"><i class="glyphicon glyphicon-time"></i>'+ dateTime   +'</p>';
				  }
			  str+='</div>';
			  
		str+='</div>';
      str+='</div>';
	  
	  //body
      str+='<div class="modal-body">';
		  str+='<div class="row">';
		  
		   str+=' <div class="col-md-12">';
			  str+='<div class="row" style="background:#8BA4A9;margin-top:-15px;padding:10px">';
			  
				str+='<div class="col-md-4">';
				 str+=' <label> ID : <b>'+result.uniqueId+'</b></label>';
				str+='</div>';
				
			   str+=' <div class="col-md-4">';
			   if( emptyCheck(result.priority) ){
				 str+='  <label>Priority Type : <b>'+result.priority+'</b></label>';  
			   }
			   str+=' </div>';
			   
			   str+=' <div class="col-md-4">';
				str+='  <label>Created Date : <b>'+result.createdDate+'</b></label>';
				str+='</div>';
				
			  str+='</div>';
			str+='</div>';
			
			var requestedDates;
			if(result.appointmentScheduleVO.apptpreferableDates!= null){
				requestedDates = result.appointmentScheduleVO.apptpreferableDates;
			}else if(result.appointmentScheduleVO.dateType != null && result.appointmentScheduleVO.dateType.trim()!="" && result.appointmentScheduleVO.maxDate != null && result.appointmentScheduleVO.minDate != null){
				requestedDates = result.appointmentScheduleVO.dateType+'('+result.appointmentScheduleVO.minDate+' to '+result.appointmentScheduleVO.maxDate+')';
			}
			if(emptyCheck(requestedDates)){
				str+='<div class="col-md-12 m_top10">';
			      str+=' <label>Requested Dates : <b> '+requestedDates+'</b></label>';
		        str+=' </div>';
			}
		 
			 str+='<div class="col-md-12 m_top10">';
			// str+=' <p class="text-success"  style="font-weight: bold;">APPOINTMENT PURPOSE</p>';
			 str+='<h5 class="text-success" style="font-weight: bold;">APPOINTMENT PURPOSE<label class="pull-right" style="border-radius:20px;" data-toggle="tooltip" data-placement="top" title="Check to edit appointment purpose"><input type="checkbox" id="editCheckBoxId"/></label></h5>';
			 str+='<textarea class="form-control appntmntRsnCls" rows="2" attr_reason=\''+result.reason+'\' readonly>'+result.reason+'</textarea>';
			 str+='</div>';
			 str+='<div style="text-center;margin-left:20px" id="updateReasonStatus"></div>';
		     str+='<div class="col-md-offset-10 col-md-2 m_top10">';
			 str+='<button type="button" class="btn btn-success" style="border-radius:20px;" attr_apppointment_id='+result.appointmentId+' id="updateAppBtnReasonId" disabled>Update</button>';
			str+='</div>';
			
			str+='<div class="col-xs-12  m_top10">';
			str+='<h4 class="panel-title text-success "  style="font-weight: bold;">';
				str+='<span style=" margin-left: 15px;"">PROFILE DETAILS</span>';
				str+='<span class="col-xs-offset-4">LOCATION DETAILS</span>';
			str+='</h4>';
			
			if(result.subList !=null && result.subList.length > 0){
				
				for( var i in result.subList){
					str+='<div class="row">';
					str+='<div class="col-md-6 m_top10">';
					str+=' <div class="media">';
					
						 str+='<div class="media-left">';
							str+='<img class="media-object thumbnail" src="'+result.subList[i].imageURL+'" style="height:80px;" onerror="setDefaultImage(this);" alt="Candidate Image" >';
						 str+='</div>';
						 
						 str+='<div class="media-body">';
							  str+='<p><span>'+result.subList[i].candidateName+'</span> </p>';
							  
							  var candDesignation = result.subList[i].candDesignation;
							  var location = result.subList[i].location;
							  var candiDesignationBuild = "";

								if(candDesignation!=null && candDesignation.length>0){
									candiDesignationBuild =  candDesignation;
									if(location!=null && location.length>0){
										candiDesignationBuild = candiDesignationBuild + " - " + location ;
									}
								}
								if(emptyCheck(candiDesignationBuild)){
									str+='<span>'+candiDesignationBuild+'</span>'
								}
							  
							  if( emptyCheck(result.subList[i].mobileNo) ){
								 str+='<p>Phone : <span>'+result.subList[i].mobileNo+'</span></p>'; 
							  }
							   if( emptyCheck(result.subList[i].memberShipId) ){
								 str+='<p>Membership Id : <span>'+result.subList[i].memberShipId+'</span></p>'; 
							  }
							  
							  if( emptyCheck(result.subList[i].voterCardNo) ){
								  if(emptyCheck(result.subList[i].voterConstituency)){
									str+='<p>Voter Card No : <span>'+result.subList[i].voterCardNo+' ('+result.subList[i].voterConstituency +' )</span></p>';   
								  }else{
									  str+='<p>Voter Card No : <span>'+result.subList[i].voterCardNo+'</span></p>'; 
								  }
								 
							  }
						 str+='</div>';
				  str+='</div>';
				 str+='</div>';
			 
				   str+='<div class=" col-md-6 m_top10">';
					
						str+='<div class="m_top10">';
						     if( emptyCheck(result.subList[i].state) ){
								 str+='<p>State : <span>'+result.subList[i].state+'</span></p>'; 
							  }
							   if( emptyCheck(result.subList[i].district) ){
								 str+='<p>District : <span>'+result.subList[i].district+'</span></p>'; 
							  }
							   if( emptyCheck(result.subList[i].constituency) ){
								 str+='<p>Constituency : <span>'+result.subList[i].constituency+'</span></p>'; 
							  }
							   if( emptyCheck(result.subList[i].tehsil) ){
								 str+='<p>Mandal : <span>'+result.subList[i].tehsil+'</span></p>'; 
							  }
							   if( emptyCheck(result.subList[i].localElectionBody) ){
								 str+='<p>Muncipality : <span>'+result.subList[i].localElectionBody+'</span></p>'; 
							  }
							   if( emptyCheck(result.subList[i].village) ){
								 str+='<p>Village : <span>'+result.subList[i].village+'</span></p>'; 
							  }
							  if( emptyCheck(result.subList[i].ward) ){
								 str+='<p>Ward : <span>'+result.subList[i].ward+'</span></p>'; 
							  }
							
						str+='</div>';
				  str+='</div>';
				  str+='</div>';
				}
			}
			
		 str+=' </div>';
		 
		 str+='</div>';
     str+=' </div>';
     str+=' <div class="modal-footer">';
     str+=' <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>';
     str+=' </div>';
	
	    $("#buildAppointmentAllDetailsDiv").html(str);
	}
	
$(document).on("click","#updateAppBtnReasonId",function(){
		var appointmentId=$(this).attr("attr_apppointment_id");
		var updatedReason=$(".appntmntRsnCls").val();
		
		var jsObj={
			appointmentId:appointmentId,
			updatedReason:updatedReason
		}
		 $.ajax({
			 type:'POST',
			 url :'updateAppointmentReasonAction.action',
			 dataType:'json',
			 data: {task:JSON.stringify(jsObj)}
		 }).done(function(result){
			 if(result!=null && result!=0){
				 if(result.exceptionMsg="success"){
					$("#updateReasonStatus").html("<p style='color:green'>Appointment purpose updated successfully.");  
					setTimeout(function(){$("#updateReasonStatus").html("");},2000);
					  //clear fields after updating
					  $('#editCheckBoxId').attr('checked', false);
					  $(".appntmntRsnCls").attr("readonly",true);
		              $("#updateAppBtnReasonId").attr("disabled",true);
					  $(".appntmntRsnCls").attr("attr_reason",updatedReason);
				 }else{
					 $("#updateReasonStatus").html("<p style='color:red'>Error occured ...Try again.");  
				 }
			 }
		 })
	});
	$(document).on("click","#editCheckBoxId",function(){
		 var reason=$(".appntmntRsnCls").attr("attr_reason");
		if($(this).is(':checked')){
			$(".appntmntRsnCls").removeAttr("readonly");
	        $("#updateAppBtnReasonId").removeAttr("disabled");
		}else{
		    $(".appntmntRsnCls").attr("readonly",true);
		    $("#updateAppBtnReasonId").attr("disabled",true);
		    $(".appntmntRsnCls").val(' ');
		    $(".appntmntRsnCls").val(reason);
		}
    });

	 function exportToExcel(result,statusId,statusType){
		 var str='';
		 if(result!=null && result.length>0){
			 str+='<table class="table table-bordered text-center b_border" id="ExprtTExclappntmntCnddtDtlsTblId">';
					 str+='<thead>';
					     str+='<th>Appointment Unique Id</th>';
						 str+='<th>Candidate Name</th>';
						 str+='<th>Contact No</th>';
						 str+='<th>Designation</th>';
						 str+='<th>Location</th>';
						 str+='<th>Constituency</th>';						 
						 str+='<th>Last Appointment Status</th>';
						 str+='<th>Last Visit Date</th>';
						 str+='<th>Total Appointments Requested</th>';
						 str+='<th>Total Completed Appointments</th>';
					 str+='</thead>'; 
					str+='<tbody>';
			 for(var i in result){
				 var candidateList=result[i].subList;
				 if(candidateList!=null && candidateList.length>0){
					 for(var i in candidateList){
					 str+='<tr>';//AppointmentUniqueId
					   if(candidateList[i].appointmentUniqueId!=null && candidateList[i].appointmentUniqueId.length>0){
						str+='<td>'+candidateList[i].appointmentUniqueId+'</td>';
						}else{
							str+='<td> - </td>';
						}
						  if(candidateList[i].name!=null && candidateList[i].name.length>0){
								str+='<td>'+candidateList[i].name+'</td>';
						  }else{
							  str+='<td>-</td>';
						  }
						 if(candidateList[i].mobileNo!=null && candidateList[i].mobileNo.length>0){
								 str+='<td>'+candidateList[i].mobileNo+'</td>';
						}else{
								 str+='<td>-</td>';
						}
						 if(candidateList[i].designation!=null && candidateList[i].designation.length>0){
							  str+='<td>'+candidateList[i].designation+'</td>';
						 }else{
							  str+='<td>-</td>';
						 }
						 if(candidateList[i].apptCandiTypeId!=null && candidateList[i].apptCandiTypeId==1){
						  if(candidateList[i].constituency!=null && candidateList[i].constituency.length>0){
							  str+='<td>'+candidateList[i].constituency+'</td>';
						   }else{
							  str+='<td>-</td>';
						   }
						 }else{
							  str+='<td>-</td>';
						 }
						 
						 if(candidateList[i].addressConstituency!=null && candidateList[i].addressConstituency.length>0){
							  str+='<td>'+candidateList[i].addressConstituency+'</td>';
						 }else{
							  str+='<td>-</td>';
						 }					    
						 if(candidateList[i].candidateLastUpdatedStatus!=null && candidateList[i].candidateLastUpdatedStatus.length>0){
							   str+='<td>'+candidateList[i].candidateLastUpdatedStatus+'</td>';
						 }else{
							   str+='<td>-</td>';
						 } 
						if(candidateList[i].candidateLastVisitDate!=null && candidateList[i].candidateLastVisitDate.length>0){
							  str+='<td>'+candidateList[i].candidateLastVisitDate+'</td>';
						}else{
							str+='<td>-</td>';
						}
						if(candidateList[i].totalRequestedAppCount!=null && candidateList[i].totalRequestedAppCount>0){
							 str+='<td>'+candidateList[i].totalRequestedAppCount+'</td>';
						}else{
							 str+='<td>-</td>';
						}
						if(candidateList[i].totalCompletedAppCount!=null && candidateList[i].totalCompletedAppCount>0){
							  str+='<td>'+candidateList[i].totalCompletedAppCount+'</td>';
						}else{
							  str+='<td>-</td>';
						}
					str+='</tr>';
					}
				
				 }
			 }
			  str+='</tbody>';
			str+='</table>';
		 }
		 $("#appntmntCnddtDtlsTblId").html(str);
	 }	 


 $(document).on('click', '.exportToExcelCls', function(){
   generateAppntmntCnddtDtlsToExcel();
 });
  function generateAppntmntCnddtDtlsToExcel()
  {	
		tableToExcel('ExprtTExclappntmntCnddtDtlsTblId', 'Appointment Candidates Report');
  }
 
 $(document).on("click","#dashboardSubmitBtn",function(){
	 $("#viewTimeSlotModelId").modal("show");
	 
	 getAllScheduledApptsByDateForDashBoard();
	
	 
});
 $("#appointmentDashboardDateSlotHeadingId").daterangepicker({singleDatePicker:true});
$("#appointmentDashboardDateSlotHeadingId").val(moment().format('MM/DD/YYYY'));

	function getAllScheduledApptsByDateForDashBoard(){
	 
	    var  dateStr       = $('#appointmentDashboardDateSlotHeadingId').val();
		var  apptUserId   =  $("#appointmentUserSelectBoxId option:selected").val();
		
		var jsObj={
			dateStr : dateStr,
			apptUserId:apptUserId
		}
		
		  	$.ajax({
				type : 'POST',
				url : 'getAllScheduledApptsByDateAction.action',
				dataType : 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				buildAppointmentMembersDetailsForDashBoard(result);
			})
	}
	
	function buildAppointmentMembersDetailsForDashBoard(result){
		
		setcolorsForStatus();
		var str = '';
		var flag = false;
		str+='<div class="upcomingAppointments heightAdjust">';
		
		str+='<div class="updateAppointment arrow_box">';
			str+='<label class="radio-inline">';
		str+='<input type="radio" value="6" name="CompletedRadio1" class="statusAllCompleted" checked>Reschedule';
		str+='</label>';
		str+='<label class="radio-inline">';
		str+='<input type="radio" value="7" name="CompletedRadio1" class="statusAllCompleted">Cancel';
		str+='</label>';
		str+='<textarea class="form-control m_top10 CompletedSmsText" ></textarea>';
		str+='<span class="msgDiv2Completed"></span>';
		str+='<button class="btn btn-block btn-success updateAll" value="Completed">UPDATE APPOINTMENT</button>';
		str+='</div>';
		//str+='<div><h4 class="text-success">APPOINTMENT TIMESLOT UPDATION</h4></div>';
		if(result != null)
		{
			var xindex = 0;
			
			for(var i in result)
			{ 
			if( xindex % 2 == 0)
			{
				str+='<div class="row m_top10">';
			}
					str+='<div class=" col-md-6 col-xs-12 updateChangeClass" >';
					str+='<div class="panel panel-default manageAppViewPanelClass m_top5">';
						str+='<div class="panel-heading bg_ff pad_5">';
							str+='<p class="settingClass" style="font-size:10px;cursor:pointer;"><i  attr_span_popup_id='+result[i].appointmentId+' attr_appt_status_id='+result[i].statusId+' attr_date='+result[i].formatDate+' attr_from_time="'+result[i].time+'" attr_to_time="'+result[i].toTime+'"  attr_comment="'+result[i].subject+'" attr_timeSlotId="'+result[i].apptTimeSlotId+'" class="glyphicon glyphicon-cog updateAppointmentClass  pull-right" id="updateSettingApptId'+result[i].appointmentId+'"  title="Appointment Status Update" data-toggle="tooltip" data-placement="top" ></i>ID: '+result[i].appointmentUniqueId+'&nbsp;&nbsp;&nbsp;';
							var color = getColorCodeByStatus(result[i].appointmentStatus);
							str+='<span style="font-weight:bold;color:'+color+'" id="statusSpanId'+result[i].appointmentId+'">'+result[i].appointmentStatus+'</span>';
							
							if(result[i].date != "" && result[i].statusId == 3){
								var dateAndtime = result[i].date;//+"  "+result[i].time+' to '+result[i].toTime;
								
								str+='<span class="pull-right"><span class="text-success"><i class="glyphicon glyphicon-time"></i>&nbsp;&nbsp;<span id="updateApptTimeSlotDateAndTimeId'+result[i].appointmentId+'">'+dateAndtime+'</span></span> &nbsp;</span>';
							}
							
							str+='</p>';
						str+='</div>';
						str+='<div class="panel-body pad_5">';
						str+='<ul>';
						flag = true;
						for(var j in result[i].subList)
						{
						
						str+='<li>';
						str+='<div class="media m_0">';
						str+='<div class="media-left">';
						str+='<img class="media-object thumbnail" src="'+result[i].subList[j].imageUrl+'" onerror="setDefaultImage(this);" alt="Candidate Image">';
						str+='</div>';
						str+='<div class="media-body">';
						str+='<p>'+result[i].subList[j].name+'</p>';
						str+='<p><i class="fa fa-mobile" style="font-size:15px"></i> &nbsp '+result[i].subList[j].mobileNo+'';
						if(result[i].subList[j].id != null && result[i].subList[j].id > 0){
								str+='<a style="display:inline-block;" title="Appointments History" data-toggle="tooltip" data-placement="top" class="historyShowModalBtn pull-right"  style="cursor:pointer;" attr-id="'+result[i].subList[j].id+'" attr-name="'+result[i].subList[j].name+'" attr-designation="'+result[i].subList[j].designation+'" attr-mobile="'+result[i].subList[j].mobileNo+'"><img src="dist/Appointment/img/view-Appt-History-icon.png"  alt="ViewApptHistory" style="height:16px;cursor:pointer;margin-right:5px;"/></a>&nbsp;&nbsp;';
						}
						str+='</p>';
						str+='</div>';
						str+='</div>';
						//multiple
						
						str+='</li>';
						
						}
						str+='</ul>';
						if(result[i].subject!=null && result[i].subject.length>35){
							  str+='<p class="" style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title="'+result[i].subject+'" >Purpose: '+result[i].subject.substring(0,35)+'...</p>';
							}else if(result[i].subject!=null && result[i].subject.length>0){
							  str+='<p class="" style="margin-left: 52px; margin-top: -6px;">Purpose:'+result[i].subject+' </p>';
							}
					str+='<p class="m_top10">';
					str+='<i>Appt Created By: '+result[i].subList[j].createdBy+'</i>';
					str+='<img src="dist/Appointment/img/message.png" class="messageIcon" alt="messageIcon" title="Send Sms" data-toggle="tooltip" data-placement="top" />';
					 
					  str+='<img src="dist/Appointment/img/reqHistoryicon+.png" class="pull-right statusTrackingModalbtn" attr-id='+result[i].appointmentId+' attr-aptName='+result[i].appointmentUniqueId+' alt="ViewReqHistory" style="height:16px;cursor:pointer;margin-right:5px;" title="Appointment Requested History" data-toggle="tooltip" data-placement="top"/>'; 
					str+='</p>';
					str+='<div class="messageBlock arrow_box">';
					str+='<span class="errorCls msgDiv1'+result[i].appointmentId+'"></span>';
					str+='<textarea class="form-control sendSms'+result[i].appointmentId+'" ></textarea>';
					str+='<button class="btn btn-success btn-block sendsms" value="'+result[i].appointmentId+'">SEND SMS</button>';
					str+='</div>';
					str+='</div>';
				str+='</div>';
				str+='</div>';
				//}
				if(xindex % 2 == 1)
				{
					str+='</div>';
				}
				
				if(result.length-1 == xindex && xindex % 3 != 2)
				{
					str+='</div>';
				}	
			xindex++;
			}
			
		}
		else
		{
			flag = false;
			//str+='No Data';	
		}
		
		if(flag == false)
		{
			$(".completedSetting").hide();
			str+='No Data Available';	
		}
		$("#dashBoardScheduleDivId").html(str);
		
		$('[data-toggle="tooltip"]').tooltip();
		if(flag == false){
		   $(".completedSetting").hide();
		}
		
	}
$(document).on("click",".closeForExtraTimeSlotCls",function(){
	setTimeout('$("body").addClass("modal-open")', 3000);	
});

$(document).on("click",".todayTotalAppointmentStatusCls",function(){
	$("#tdyAppntmntPrcssngImgId0").show();
	var statusArray=[];
	statusArray.push(3);
	statusArray.push(4);
	statusArray.push(10);
	$('html,body').animate({scrollTop: $("#searchApptmntDivId").offset().top}, 2000);
	getappointmentStatusDetails(statusArray,"Today","",0);
});
 
function getRescheduledsCounts(){
	
	 $("#reschdldAppntmntsRprtTblId").html(' ');
	 $("#reschdldAppntmntsRprtTblIdPrcssingImgId").show();
	var aptUserId = $("#appointmentUserSelectBoxId").val();
	 	var jsObj = {
			aptUserId:aptUserId
		}
	$.ajax({
		type :'GET',
		url  :'getRescheduledsCountsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){ 
	$("#reschdldAppntmntsRprtTblIdPrcssingImgId").hide();
	   if(result!=null){
		   buildRescheduledCountRslt(result);
	   }else{
		   $("#reschdldAppntmntsRprtTblId").html('<p>No Data Available</p>');
	   }
	});
}
function buildRescheduledCountRslt(result){
    var str='';
	  str+='<table class="table table-condensed table-bordered" style="font-size:20px;">';
		str+='<tr>';
		str+='<td>Total Rescheduled Appointments</td>';
		if( result.totalRescheduledCount != null && result.totalRescheduledCount > 0){
			str+='<td class="appntmntsSttsCntCls" title="View Rescheduled Appointments" data-toggle="tooltip" data-placement="top" style="cursor:pointer;">'+result.totalRescheduledCount+'</td>';
		}else{
			str+='<td> 0 </td>';
		}
		str+='</tr>	';
		str+='<tr>';
		 str+='<td>Total Rescheduled Candidates</td>';
		if(result.totalReschedCandidateCount != null && result.totalReschedCandidateCount > 0){
			str+='<td class="appntmntUnqCntCls" title="View Rescheduled Candidates" data-toggle="tooltip" data-placement="top" style="cursor:pointer;">'+result.totalReschedCandidateCount+'</td>';
		}else{
			str+='<td> 0 </td>';
		}
		str+='</tr>';
	str+='</table>';
	$("#reschdldAppntmntsRprtTblId").html(str);
	$('[data-toggle="tooltip"]').tooltip();
}

$(document).on("click",".appntmntsSttsCntCls",function(){
	 getRescheduledsAppointmentsDtls();
});
 function getRescheduledsAppointmentsDtls(){
      
	  $("#rschdldAppntmntsRprtMdlId").modal("show");
	 $("#rschdldAppntmntsRprtTblId").html(' ');
	 $("#ttlRschdlAppntmntsImgPrcssngId").show();
    var aptUserId = $("#appointmentUserSelectBoxId").val();
	 	var jsObj = {
			aptUserId:aptUserId
		}
	 $.ajax({
		 type:'POST',
		 url :'getRescheduledsAppointmentsDtlsAction.action',
		 dataType:'json',
		data : {task:JSON.stringify(jsObj)}  
	 }).done(function(result){
		  $("#ttlRschdlAppntmntsImgPrcssngId").hide();
		 if(result!=null && result.length>0){
			buildRescheduledAppsDtls(result); 
		 }else{
			$("#rschdldAppntmntsRprtTblId").html('<p>No Data Available</p>'); 
		 }
	 });
 } 
function buildRescheduledAppsDtls(result){ 

	var str='';
	 str+='<table style="font-size:12px" class="table table-bordered table-condensed" id="rschdldAppntmntsRprtPgntnTblId">';
	 str+='<thead>';
	 str+='<th style="width="5px !important;">ID</th>';
	 str+='<th>Candidate Name</th>';
	 str+='<th>Candidate Image</th>';
	 str+='<th>Mobile No</th>';
	 str+='<th>Designation</th>';
	 str+='<th>Rescheduled Date</th>';
	 str+='<th>Present Status</th>';
	 str+='<th >Comment</th>';
	 str+='</thead>'; 
	   str+='<tbody>';
	   for(var i in result){
		   
		   var candidteList=result[i].subList;
		   var rescheduledList=result[i].rescheduledList;
		   
		    if(candidteList!=null && candidteList.length>0){
				for(var j in candidteList){
				  if(rescheduledList!=null && rescheduledList.length>0){
					  for(var k in rescheduledList){
						str+='<tr>';
						if(result[i].appointmentUniqueId!=null && result[i].appointmentUniqueId.length>0){
						 str+='<td>'+result[i].appointmentUniqueId+'</td>';	
						}else{
						 str+='<td> - </td>';	
						}
						if(candidteList[j].name!=null && candidteList[j].name.length>0){
							str+='<td>'+candidteList[j].name.toUpperCase()+'</td>';
						}else{
							 str+='<td> - </td>';	
						}
						 str+='<td ><img style="width:65px;height:60px;" class="media-object thumbnail" src='+candidteList[j].imageUrl+' alt="Candidate Image" onerror="setDefaultImage(this);"></td>';
					
						 if(candidteList[j].mobileNo!=null && candidteList[j].mobileNo.length>0){
						 str+='<td>'+candidteList[j].mobileNo+'</td>';
						 }else{
						 str+='<td> - </td>';
						 }
						 var candidateDesignation=candidteList[j].candDesignation;
						 var location=candidteList[j].constituency;
						 var buildCandidateDesignation='';
					      if(candidateDesignation!=null && candidateDesignation.length>0){
							  buildCandidateDesignation=candidateDesignation;
							  if(location!=null && location.length>0){
								buildCandidateDesignation = buildCandidateDesignation + " - " + location ;
							}
						  }
						   if(buildCandidateDesignation!=null && buildCandidateDesignation.length>0){
								     str+='<td style="font-size:13px;">'+buildCandidateDesignation+'</td>';
							   }else{
								     str+='<td> - </td>';
							   }
						 if(rescheduledList[k].date!=null && rescheduledList[k].date.length>0){
							 str+='<td>'+rescheduledList[k].date+'</td>';
						 }else{
							str+='<td> - </td>';
						 }
						 if(result[i].presentStatus!=null && result[i].presentStatus.length>0){
							  str+='<td style="font-size:13px;">'+result[i].presentStatus+'</td>';
						 }else{
							 str+='<td> - </td>';
						 }
						 if(rescheduledList[k].subject!=null && rescheduledList[k].subject.length>0){
							 str+='<td>'+rescheduledList[k].subject+'</td>';
						 }else{
							str+='<td> - </td>';
						 }
						str+='</tr>'; 
					  }
				  }
				}
			}
	   }
	   str+='</tbody>';
	str+='</table>';
  $("#rschdldAppntmntsRprtTblId").html(str);
  $('#rschdldAppntmntsRprtPgntnTblId').dataTable({
			"aaSorting": [],
			"iDisplayLength" : 10	,
			 "bDestroy": true,
			 "bFilter": true,
			"aLengthMenu": [[10,20,30,50, 100, -1], [10,20,30,50, 100, "All"]]		
		});
   
}

$(document).on("click",".appntmntUnqCntCls",function(){
	  var aptUserId = $("#appointmentUserSelectBoxId").val();
	  window.open("rescheduledCandidateAction.action?appuserId="+aptUserId+"","_blank"); 
});
 