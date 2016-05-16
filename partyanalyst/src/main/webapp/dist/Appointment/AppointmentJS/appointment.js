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
						 str+='<td class="appointmentStatusCls" attr_statusArrId ="'+statusArr+'"><span style="color: '+color+';cursor:pointer">'+result[i].status+' <br><span style="font-weight: bold; font-size: 28px ! important;">'+result[i].statusCount+'</span></span></td>';
					  }
					
					}else if(result[i].appointmentStatusId ==2){
						var statusArr= result[i].clickIds;
						 str+='<td><span style="color: '+color+';cursor:pointer">'+result[i].status+' - <span style="font-weight: bold; font-size: 28px ! important;"><span class="appointmentStatusCls" attr_status_type="totalApproved" attr_statusArrId ="'+statusArr+'">'+result[i].statusCount+'</span></span>';
						
						str+='<table style="font-size: 12px; color: rgb(51, 51, 51);" class="table table-border"><tbody>';
						str+='<tr>';
						
						if(result[i].subList !=null && result[i].subList.length>0){
							for(var j in result[i].subList){
							var internalColor = getColorCodeByStatus(result[i].subList[j].status)		
								if(result[i].subList[j].statusCount == 0){
								str+='<td style="background: '+internalColor+'"> '+result[i].subList[j].status+' - '+result[i].subList[j].statusCount+'</td>';
								}else{
								var statusArr= result[i].subList[j].clickIds;
								 str+='<td class="appointmentStatusCls" attr_statusArrId ="'+statusArr+'" style="background: '+internalColor+';cursor:pointer"> '+result[i].subList[j].status+' - '+result[i].subList[j].statusCount+'</td>';	
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