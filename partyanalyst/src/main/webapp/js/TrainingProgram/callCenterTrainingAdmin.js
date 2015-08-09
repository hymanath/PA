
function getAllCampBatches()
	{
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
	var callerId = $("#callerId").val();
	var callPurposeId = $("#memeberBatchConformationId").val();
	
	if(scheduleId == null && scheduleId == 0){
		$("#searchErrDivID").html("Please Select Any Schedule Date");
		return;
	}
	if(membersCount == null && membersCount.length == 0){
		$("#").html("Please Enter No.of Calls");
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
		if(result != null){
			
		}
	});
}

function getCampusWiseDateWiseInterestedMembersDetails()
{
	var fromDate=$(".dp_startDate").val();
	var toDate=$(".dp_endDate").val();
	
	var jsObj={
			searchType:"notStarted",
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
			for(var i in result.trainingCampVOList){
				str+='<tbody>';
				str+='<tr>';
				str+='<td rowspan="4">'+result.trainingCampVOList[i].name+'</td>';
				for(var j in result.trainingCampVOList[i].trainingCampVOList[0].trainingCampVOList){
					
					str+='<td>'+result.trainingCampVOList[i].trainingCampVOList[0].trainingCampVOList[j].trainingCampName+'</td>';
					str+='<td>'+result.trainingCampVOList[i].trainingCampVOList[0].trainingCampVOList[j].startDateStr+'</td>';
					str+='<td>'+result.trainingCampVOList[i].trainingCampVOList[0].trainingCampVOList[j].endDateStr+'</td>';
					str+='<td>'+result.trainingCampVOList[i].trainingCampVOList[0].trainingCampVOList[j].batchConfirmationCount+'</td>';
					
				}
				str+='</tr>';
			}
			/*else{
				str+='<tr>Data Not Available</tr>';
			}*/
			str+='</tbody>';
			str+='</table>';
			
			$(".InterestedMembersCountDivId").html(str);
	}
}

function getCampusWiseBatchWiseMembersDetails()
{
	var fromDate=$(".dp_startDate").val();
	var toDate=$(".dp_endDate").val();
	var jsObj={
			searchType:"notStarted",
			fromdate : fromDate,
			toDate   : toDate
		}
		
	$.ajax({
		type:'POST',
		 url: 'getCampusWiseBatchWiseMembersDetails.action',
		 data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			if(result != null){
				buildCampusWiseBatchWiseMembersDetails(result);
			}
		});
}

function buildCampusWiseBatchWiseMembersDetails(result)
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
			for(var i in result.trainingCampVOList)
			{
				str+='<tbody>';
				str+='<tr>';
				str+='<td rowspan="4">'+result.trainingCampVOList[i].name+'</td>';
				str+='<td>'+result.trainingCampVOList[i].trainingCampName+'</td>';
			
				str+='<td>'+result.trainingCampVOList[i].trainingCampVOList[0].scheduleName+'</td>';
				str+='<td>'+result.trainingCampVOList[i].trainingCampVOList[0].nextBatchInterestedCount+'</td>';
				str+='<td>'+result.trainingCampVOList[i].trainingCampVOList[1].nextBatchInterestedCount+'</td>';
				if(result.trainingCampVOList[i].trainingCampVOList[2].notInterestedCount == null){
				str+='<td>0</td>';
				}else{
				str+='<td>'+result.trainingCampVOList[i].trainingCampVOList[2].notInterestedCount+'</td>';
				}
				str+='<td>'+result.trainingCampVOList[i].trainingCampVOList[3].batchConfirmationCount+'</td>';
				str+='<td>'+result.trainingCampVOList[i].trainingCampVOList[4].availableMembersCount+'</td>';
				str+='</tr>';
			}
			str+='</tbody>';
			str+='</table>';									
                                                	
			$("#scheduled").html(str);										
    									
	}
}