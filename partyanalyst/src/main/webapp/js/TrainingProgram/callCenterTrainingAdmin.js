
function getAllCampBatches(batchMemId)
	{
		$("#availableCounstDivId").hide();
		$("#availableCallsDivId").hide();
		if(batchMemId == 1){
			$("#batchConfirmationDivId").hide();
			$("#memberConfirmationDivId").show();
		}
		if(batchMemId == 2){
			$("#memberConfirmationDivId").hide();
			$("#batchConfirmationDivId").show();
		}
	$("#campId  option").remove();
	$("#campId").append('<option value="0">Select Camp</option>');
		var districtIds = 0;
		var jsObj={
				districtIds:districtIds
		}
		
		$.ajax({
			type:'POST',
			url :'getAllCampBatchesAction.action',
			data:{task:JSON.stringify(jsObj)},
		}).done(function(result){
			if(result != null)
			{
				for(var i in result)
				{
					if(result[i].id == 0){
					  $("#campId").append('<option value='+result[i].id+'>ALL</option>');
				   }else{
					  $("#campId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
				   }
				}
			}
		});
	}
	
function getAllProgramsList(campId)
{
	$("#programId  option").remove();
	$("#programId").append('<option value="0">Select Program</option>');
	var jsObj={
		campId:campId
	}
	
	$.ajax({
		type:'POST',
		url :'getAllProgramsListAction.action',
		data:{task:JSON.stringify(jsObj)},
	}).done(function(result){
		if(result != null){
			for(var i in result){
				if(result[i].id == 0){
				  $("#programId").append('<option value='+result[i].id+'>ALL</option>');
			   }else{
				  $("#programId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			   }
			}
		}
	});
}

function getAllSchedulesDatesList(programId)
{
	$("#scheduleId  option").remove();
	$("#scheduleId").append('<option value="0">Select Schedule</option>');
	var jsObj={
		programId:programId
	}
	
	$.ajax({
		type:'POST',
		url :'getAllScheduleListAction.action',
		data:{task:JSON.stringify(jsObj)},
	}).done(function(result){
		if(result != null){
			for(var i in result){
				if(result[i].id == 0){
				  $("#scheduleId").append('<option value='+result[i].id+'>ALL</option>');
			   }else{
				  $("#scheduleId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			   }
			}
		}
	});
}

function saveAllDetails()
{
	var scheduleId = $("#scheduleId").val();
	var membersCount = $("#membersCountId").val().trim();
	var callerId = $(".callerId").val();
	var callPurposeId = $("#memeberBatchConformationId").val();
	
	if(scheduleId == null && scheduleId == 0){
		$("#searchErrDivID").html("Please Select Any Schedule Date");
		return;
	}
	if(membersCount == null && membersCount.length == 0){
		$("#searchErrDivID").html("Please Enter No.of Calls");
		return;
	}
	
	
	var jsObj={
		scheduleId:scheduleId,
		membersCount:membersCount,
		callerId:callerId,
		callPurposeId:callPurposeId
	}
	
	$.ajax({
		type:'POST',
		url :'saveAllDetailsAction.action',
		data:{task:JSON.stringify(jsObj)},
	}).done(function(result){
		$("#searchErrDivID").html("");
		if(result != null){
			$("#searchErrDivID").html("<span style='color:green;'>Details Are Updated Successfully</span>");
		}
	});
}

function getCampusWiseDateWiseInterestedMembersDetails(searchType)
{
	var fromDate=$(".dp_startDate").val();
	var toDate=$(".dp_endDate").val();
	
	var jsObj={
			searchType:searchType,
			fromdate : fromDate,
			toDate   : toDate
		}
		
	$.ajax({
		type:'POST',
		 url: 'getCampusWiseDateWiseInterestedMembersDetailsAction.action',
		 data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			if(result != null){
				buildCampusWiseDateWiseInterestedMembersDetails(result);
			}
		});
}

function buildCampusWiseDateWiseInterestedMembersDetails(result)
{
	//alert(1);
	var str='';
	if(result.trainingCampVOList != null)
	{
		str+='<table class="table table-bordered m_0" style="width: 750px;">';
			str+='<thead class="bg_d">';
				str+='<th>TRAINING <br/> PROGRAM NAME</th>';
				str+='<th>TRAINING CAMP<br/> NAME</th>';
				str+='<th>START <br/> DATE</th>';
				str+='<th>END <br/> DATE</th>';										
				str+='<th>MEMBERS <br/>ACCEPTED</th>';
			str+='</thead>';
			str+='<tbody>';
			for(var i in result.trainingCampVOList){
				
				if(result.trainingCampVOList[i].trainingCampVOList != null && result.trainingCampVOList[i].trainingCampVOList.length>0)
				{
					for(var k in result.trainingCampVOList[i].trainingCampVOList)
					{
						if(result.trainingCampVOList[i].trainingCampVOList[k].trainingCampVOList != null && result.trainingCampVOList[i].trainingCampVOList[k].trainingCampVOList.length>0)
						{
							for(var s in result.trainingCampVOList[i].trainingCampVOList[k].trainingCampVOList)
							{
								str+='<tr>';
								if(s==0 && result.trainingCampVOList[i].trainingCampVOList[k].trainingCampVOList[s].name != null)
									str+='<td style="text-align:center;"  rowspan="'+result.trainingCampVOList[i].trainingCampVOList[k].trainingCampVOList.length+'">'+result.trainingCampVOList[i].trainingCampVOList[k].trainingCampVOList[s].name+'</td>';
								else if(result.trainingCampVOList[i].trainingCampVOList[k].trainingCampVOList[s].name == null)
									str+='<td style="text-align:center;"  rowspan="'+result.trainingCampVOList[i].trainingCampVOList[k].trainingCampVOList.length+'"> - </td>';
								
								if(result.trainingCampVOList[i].trainingCampVOList[k].trainingCampVOList[s].trainingCampName != null)
									str+='<td style="text-align:center;" >'+result.trainingCampVOList[i].trainingCampVOList[k].trainingCampVOList[s].trainingCampName+'</td>';
								else
									str+='<td style="text-align:center;" > - </td>';
								
								if(result.trainingCampVOList[i].trainingCampVOList[k].trainingCampVOList[s].trainingCampName != null)
									str+='<td style="text-align:center;" >'+result.trainingCampVOList[i].trainingCampVOList[k].trainingCampVOList[s].trainingCampName+'</td>';
								else
									str+='<td style="text-align:center;" > - </td>';
								
								if(result.trainingCampVOList[i].trainingCampVOList[k].trainingCampVOList[s].startDateStr != null)
									str+='<td style="text-align:center;" >'+result.trainingCampVOList[i].trainingCampVOList[k].trainingCampVOList[s].startDateStr+'</td>';
								else
									str+='<td style="text-align:center;" > - </td>';
								
								if(result.trainingCampVOList[i].trainingCampVOList[k].trainingCampVOList[s].endDateStr != null)
									str+='<td style="text-align:center;" >'+result.trainingCampVOList[i].trainingCampVOList[k].trainingCampVOList[s].endDateStr+'</td>';
								else
									str+='<td style="text-align:center;" > - </td>';
								
								str+='</tr>';
							}
						}
					}
				}
			
			}
			/*else{
				str+='<tr>Data Not Available</tr>';
			}*/
			str+='</tbody>';
			str+='</table>';
			
			$(".InterestedMembersCountDivId").html(str);
	}
}

function getCampusWiseBatchWiseMembersDetails(searchType,divId)
{

	var fromDate=$(".dp_startDate").val();
	var toDate=$(".dp_endDate").val();
	var jsObj={
			searchType:searchType,
			fromdate : fromDate,
			toDate   : toDate
		}
		
	$.ajax({
		type:'POST',
		 url: 'getCampusWiseBatchWiseMembersDetails.action',
		 data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			if(result != null){
				if(searchType=='notStarted')
					buildCampusWiseBatchWiseMembersDetails(result,divId);
				else
					buildBAtchWiseMembersDetails(result,divId,searchType);
			}
			else{
				$('#'+divId+'').html('<div class="text-center"><b> No Data Available...</b></div>');
			}
		});
}


function buildBAtchWiseMembersDetails(result,divId,searchType)
{
	var str='';
	if(result.trainingCampVOList != null)
	{//alert(1);
		str+='<table class="table table-bordered m_0">';
			str+='<thead>';
				str+='<th>PROGRAM NAME</th>';
				str+='<th>CAMP NAME</th>';
				str+='<th> SCHEDULE </th>';
				str+='<th>BATCH NAME & DATE </th>';
				if(searchType=='running'){
					str+='<th>BATCH MEMBERS</th>';
					str+='<th>ABSENT</th>';
				}
				else if(searchType=='completed'){
					str+='<th>COMPLETED</th>';
				}
				else if(searchType=='cancelled'){
					str+='<th>status</th>';
				}
				
			str+='</thead>';
			for(var i in result.trainingCampVOList)
			{
				str+='<tbody>';
				if(result.trainingCampVOList[i].trainingCampVOList != null && result.trainingCampVOList[i].trainingCampVOList.length>0)
				{
					for(var k in result.trainingCampVOList[i].trainingCampVOList)
					{
						str+='<tr>';
						
						if(k==0 && result.trainingCampVOList[i].name != null)
								str+='<td style="text-align:center;"  rowspan='+result.trainingCampVOList[i].trainingCampVOList.length+'>'+result.trainingCampVOList[i].name+'</td>';
						else if(k==0)
							str+='<td style="text-align:center;"  rowspan='+result.trainingCampVOList[i].trainingCampVOList.length+'> - </td>';
							
						if(result.trainingCampVOList[i].trainingCampName != null)
								str+='<td style="text-align:center;" >'+result.trainingCampVOList[i].trainingCampName+'</td>';
						else
							str+='<td style="text-align:center;" > - </td>';
						if(result.trainingCampVOList[i].trainingCampVOList[k].scheduleName != null)
								str+='<td style="text-align:center;" >'+result.trainingCampVOList[i].trainingCampVOList[k].scheduleName+'</td>';
						else
							str+='<td style="text-align:center;" > - </td>';
						if(result.trainingCampVOList[i].trainingCampVOList[k].name != null)
								str+='<td style="text-align:center;" >'+result.trainingCampVOList[i].trainingCampVOList[k].name+'</td>';
						else
							str+='<td style="text-align:center;" > - </td>';
						
						if(searchType=='running'){
							if(result.trainingCampVOList[i].trainingCampVOList[k].availableMembersCount != null)
								str+='<td style="text-align:center;" >'+result.trainingCampVOList[i].trainingCampVOList[k].availableMembersCount+'</td>';
							else
								str+='<td style="text-align:center;" >0</td>';
							
							str+='<td style="text-align:center;" >0</td>';
						}
						else if(searchType=='completed'){
							if(result.trainingCampVOList[i].trainingCampVOList[k].availableMembersCount != null)
								str+='<td style="text-align:center;" >'+result.trainingCampVOList[i].trainingCampVOList[k].availableMembersCount+'</td>';
							else
								str+='<td style="text-align:center;" > 0 </td>';
						}
						else if(searchType=='cancelled'){
							if(result.trainingCampVOList[i].trainingCampVOList[k].memberStatus != null)
								str+='<td style="text-align:center;" >'+result.trainingCampVOList[i].trainingCampVOList[k].memberStatus+'</td>';
							else
								str+='<td style="text-align:center;" > - </td>';
						}
						
						str+='</tr>';
					}
				}
			}
			str+='</tbody>';
			str+='</table>';									
                                                	
			$("#"+divId+"").html(str);										
    									
	}
}

function buildCampusWiseBatchWiseMembersDetails(result,divId)
{
	var str='';
	if(result.trainingCampVOList != null)
	{//alert(1);
		str+='<table class="table table-bordered m_0">';
			str+='<thead>';
				str+='<th>TRAINING PROGRAM <br/> NAME</th>';
				str+='<th>TRAINING CAMP <br/> NAME</th>';
				str+='<th>SCHEDULED <br/> CALENDAR DATES</th>';
				str+='<th>INTERESTED <br/> MEMBERS</th>';
				str+='<th>LATER</th>';
				str+='<th>NOT <br/> INTERESTED</th>';
				str+='<th>ASSIGNED TO <br/> <span class="font-12">BATCH CONFORMATION</span> </th>';
				str+='<th>AVAILABLE MEMBERS <br/> IN CALENDAR DATES</th>';
			str+='</thead>';
			str+='<tbody>';
			
			for(var i in result.trainingCampVOList)
			{
				
				var statusCount = result.trainingCampVOList[i].trainingCampVOList.length;
				if(result.trainingCampVOList[i].trainingCampVOList != null && result.trainingCampVOList[i].trainingCampVOList.length>0)
				{
					for(var k in result.trainingCampVOList[i].trainingCampVOList)
					{
							str+='<tr>';	
								if(k==0 && result.trainingCampVOList[i].name != null)
									str+='<td style="text-align:center;"  rowspan="'+result.trainingCampVOList[i].trainingCampVOList[k].trainingCampVOList.length+'">'+result.trainingCampVOList[i].name+'</td>';
								else if(result.trainingCampVOList[i].name == null)
									str+='<td style="text-align:center;" > - </td>';
								str+='<td style="text-align:center;" >'+result.trainingCampVOList[i].trainingCampVOList[k].trainingCampName+'</td>';							
								str+='<td style="text-align:center;" >'+result.trainingCampVOList[i].trainingCampVOList[k].scheduleName+'</td>';
								if(result.trainingCampVOList[i].trainingCampVOList[k].nextBatchInterestedCount != null)
									str+='<td style="text-align:center;" >'+result.trainingCampVOList[i].trainingCampVOList[k].nextBatchInterestedCount+'</td>';
								else
									str+='<td style="text-align:center;" > 0 </td>';
								if(k<statusCount-1)
									k=parseInt(k)+parseInt(1);
								if(result.trainingCampVOList[i].trainingCampVOList[k].nextBatchInterestedCount != null)
									str+='<td style="text-align:center;" >'+result.trainingCampVOList[i].trainingCampVOList[k].nextBatchInterestedCount+'</td>';
								else
									str+='<td style="text-align:center;" > 0 </td>';
								if(k<statusCount-1)
									k=parseInt(k)+parseInt(1);
								if(result.trainingCampVOList[i].trainingCampVOList[k].nextBatchInterestedCount != null)
									str+='<td style="text-align:center;" >'+result.trainingCampVOList[i].trainingCampVOList[k].nextBatchInterestedCount+'</td>';
								else
									str+='<td style="text-align:center;" > 0 </td>';
								if(k<statusCount-1)
									k=parseInt(k)+parseInt(1);
								if(result.trainingCampVOList[i].trainingCampVOList[k].batchConfirmationCount != null)
									str+='<td style="text-align:center;" >'+result.trainingCampVOList[i].trainingCampVOList[k].batchConfirmationCount+'</td>';
								else
									str+='<td style="text-align:center;" > 0 </td>';
								if(k<statusCount-1)
									k=parseInt(k)+parseInt(1);
								if(result.trainingCampVOList[i].trainingCampVOList[k].availableMembersCount != null)
									str+='<td style="text-align:center;" >'+result.trainingCampVOList[i].trainingCampVOList[k].availableMembersCount+'</td>';
								else
									str+='<td style="text-align:center;" > 0 </td>';
								str+='</tr>';
								break;
					}
				}
				
				
			}
			
			str+='</tbody>';
			str+='</table>';									
                                                	
			$("#"+divId+"").html(str);										
    									
	}
}

function getAvailableMembersCountDetails(caallerrId)
{
	var schedduleeId = $("#scheduleId").val();
	if(caallerrId != 0)
	{
		var jsObj={
		schedduleeId:schedduleeId,
		caallerrId:caallerrId
		}
		
		$.ajax({
			type:'POST',
			url :'getAvailableMembersCountDetailsAction.action',
			data:{task:JSON.stringify(jsObj)},
		}).done(function(result){
			if(result != null){
				$("#availableCounstDivId").show();
				$("#ownCountId").html(result.availableCount);
				$("#othersCountId").html(result.availableCount);
			}
		});
	}
	
}

function getAvailableCountForMemberConfirmation(scheduleId)
{
	var jsObj={
			scheduleId:scheduleId
			}
	
	$.ajax({
		type:'POST',
		url :'getAvailableCountForMemberConfirmationAction.action',
		data:{task:JSON.stringify(jsObj)},
	}).done(function(result){
		if(result != null){
			$("#availableCallsDivId").show();
				$("#availCountId").html(0);
			if(result>=0)
				$("#availCountId").html(result);
		}		
	});
}