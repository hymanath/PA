
function getAllCampBatches(batchMemId)
	{
		$("#availableCounstDivId").hide();
		$("#availableCallsDivId").hide();
		$("#searchErrDivID").html("");
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
	$("#programId  option").remove();
	$("#programId").append('<option value="0">Select Program</option>');
	$("#scheduleId  option").remove();
	$("#scheduleId").append('<option value="0">Select Schedule</option>');
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
	$("#searchErrDivID").html("");
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
	$("#searchErrDivID").html("");
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
	var availCalls = $("#availCallsId").val();
	
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
		callPurposeId:callPurposeId,
		availCalls:availCalls
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
	var fromDate='';
	var toDate='';
	var dateStr=$("#reportrange").val();
	var dateArr=[];
	if(dateStr !=null && dateStr.length>0){
		dateArr=dateStr.split("to");
		fromDate= dateArr[0];
		toDate=  dateArr[1];
	}
	
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
	if(result.trainingCampVOList != null && result.trainingCampVOList.length>0)
	{//alert(2);
		str+='<table class="table table-bordered m_0">';
			str+='<thead class="bg_d" style="font-size:12px;">';
			str+='<tr>';
				str+='<th>TRAINING <br/>PROGRAM NAME</th>';
				str+='<th>TRAINING CENTER</th>';
				str+='<th>TRAINING <br/>START DATE</th>';
				str+='<th>TRAINING <br/>END DATE</th>';
				str+='<th>ALLOCATED <br/>CALLS</th>';
				str+='<th class="text-yellow">DIALED/<br/> UN DIALED</th>';
				str+='<th class="interested-text"> ACCEPTED <br/>MEMBERS</th>';
				str+='<th>NOT <br/>INTERESTED</th>';
				str+='<th>LATER</th>';
				str+='<th class="font-12">CALL BACK/<br/>USER BUSY/<br/>OTHERS</th>';
			str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
			if(result.trainingCampVOList != null && result.trainingCampVOList.length>0){
				for(var i in result.trainingCampVOList){
				
					if(result.trainingCampVOList[i].trainingCampVOList != null && result.trainingCampVOList[i].trainingCampVOList.length>0)
					{
						for(var k in result.trainingCampVOList[i].trainingCampVOList)
						{
							str+='<tr>';
							if(k==0 && result.trainingCampVOList[i].trainingCampVOList[k] != null)
								str+='<td style="text-align:center;" rowspan="'+result.trainingCampVOList[i].trainingCampVOList.length+'">'+result.trainingCampVOList[i].trainingCampVOList[k].name+'</td>';
							else if(result.trainingCampVOList[i].trainingCampVOList[k].name == null)
								str+='<td style="text-align:center;" rowspan="'+result.trainingCampVOList[i].trainingCampVOList.length+'">-</td>';
								
							if(result.trainingCampVOList[i].trainingCampVOList[k].trainingCampName != null)
								str+='<td style="text-align:center;">'+result.trainingCampVOList[i].trainingCampVOList[k].trainingCampName+'</td>';
							else
								str+='<td style="text-align:center;" > 0 </td>';
								
							if(result.trainingCampVOList[i].trainingCampVOList[k].startDateStr != null)
								str+='<td style="text-align:center;">'+result.trainingCampVOList[i].trainingCampVOList[k].startDateStr+'</td>';
							else
								str+='<td style="text-align:center;" > 0 </td>';
								
							if(result.trainingCampVOList[i].trainingCampVOList[k].endDateStr != null)
								str+='<td style="text-align:center;">'+result.trainingCampVOList[i].trainingCampVOList[k].endDateStr+'</td>';
							else
								str+='<td style="text-align:center;" > 0 </td>';
								
							if(result.trainingCampVOList[i].trainingCampVOList[k].allocatedCalls != null)
								str+='<td style="text-align:center;">'+result.trainingCampVOList[i].trainingCampVOList[k].allocatedCalls+'</td>';
							else
								str+='<td style="text-align:center;" > 0 </td>';
								
							var unDialedCount=result.trainingCampVOList[i].trainingCampVOList[k].allocatedCalls-result.trainingCampVOList[i].trainingCampVOList[k].dialedCallsCount;
								
							if(result.trainingCampVOList[i].trainingCampVOList[k].dialedCallsCount != null)
								str+='<td style="text-align:center;" class="text-yellow">'+result.trainingCampVOList[i].trainingCampVOList[k].dialedCallsCount+'/'+unDialedCount+'</td>';
							else
								str+='<td style="text-align:center;" > 0/0 </td>';
								
							var acceptedCount=0;
							console.log(result.trainingCampVOList[i].trainingCampVOList[k].acceptedCount);
							if(result.trainingCampVOList[i].trainingCampVOList[k].acceptedCount != null )
								acceptedCount = result.trainingCampVOList[i].trainingCampVOList[k].acceptedCount;
								
							if(acceptedCount != null)
								str+='<td style="text-align:center;" class="interested-text"> '+acceptedCount+' </td>';
							else
								str+='<td style="text-align:center;" > 0 </td>';
								
							if(result.trainingCampVOList[i].trainingCampVOList[k].notInterestedCount != null)
								str+='<td style="text-align:center;">'+result.trainingCampVOList[i].trainingCampVOList[k].notInterestedCount+'</td>';
							else
								str+='<td style="text-align:center;" > 0 </td>';
								
							if(result.trainingCampVOList[i].trainingCampVOList[k].conformLaterCount != null)
								str+='<td style="text-align:center;">'+result.trainingCampVOList[i].trainingCampVOList[k].conformLaterCount+'</td>';
							else
								str+='<td style="text-align:center;" > 0 </td>';
								
							if(result.trainingCampVOList[i].trainingCampVOList[k].othersCount != null)
								str+='<td style="text-align:center;">'+result.trainingCampVOList[i].trainingCampVOList[k].othersCount+'</td>';
							else
								str+='<td style="text-align:center;" > 0 </td>';
						}
					}
				}
			}
			/*else{
				str+='<tr>Data Not Available</tr>';
			}*/
			str+='</tbody>';
			str+='</table>';
			
			$(".batchConforCls").html(str);
	}
	else{
		$(".batchConforCls").html('<div class="text-center"><b> Batch Confirmation Data Not Available...</b></div>');
	}
}

function getCampusWiseBatchWiseMembersDetails(searchType,divId,typeId)
{
	$("#"+divId+"").html("");

	$("#dataLoadingsImgForCalenderScheduleId").show();
	var fromDate='';
	var toDate='';
	var dateStr=$("#reportrange").val();
	var dateArr=[];
	if(dateStr !=null && dateStr.length>0){
		dateArr=dateStr.split("to");
		fromDate= dateArr[0];
		toDate=  dateArr[1];
	}
	
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
			$("#dataLoadingsImgForCalenderScheduleId").hide();
			if(result != null){
				//if(searchType=='notStarted')
					buildCampusWiseBatchWiseMembersDetails(result,divId);
				//else
				//	buildBAtchWiseMembersDetails(result,divId,searchType);
			}
			else{
				$('#'+divId+'').html('<div class="text-center"><b> Calender Schedule Data Not Available...</b></div>');
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
					str+='<th>STATUS</th>';
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
	if(result.trainingCampVOList != null && result.trainingCampVOList.length>0)
	{//alert(1);
		str+='<table class="table table-bordered m_0">';
			str+='<thead class="bg_d" style="font-size:12px;">';
				str+='<th>TRAINING PROGRAM <br/> NAME</th>';
				str+='<th>TRAINING CAMP <br/> NAME</th>';
				str+='<th>SCHEDULED <br/> CALENDAR DATES</th>';
				str+='<th>ALLOCATED CALLS</th>';
				str+='<th class="text-yellow">DIALED / UN DIALED</th>';
				str+='<th class="interested-text">INTERESTED <br/> MEMBERS</th>';				
				str+='<th>LATER</th>';
				str+='<th>NOT <br/> INTERESTED</th>';
				str+='<th>ASSIGNED TO <br/> <span>BATCH CONFORMATION</span> </th>';
				str+='<th>AVAILABLE MEMBERS <br/> IN CALENDAR DATES</th>';
				str+='<th>CALL BACK - BUSY / <br> OTHERS</th>';
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
								if(result.trainingCampVOList[i].trainingCampVOList[k].allocatedCalls != null)
									str+='<td style="text-align:center;" >'+result.trainingCampVOList[i].trainingCampVOList[k].allocatedCalls+'</td>';
								else
									str+='<td style="text-align:center;" > 0 </td>';
								
								if(result.trainingCampVOList[i].trainingCampVOList[k].dialedCallsCount != null)
									str+='<td style="text-align:center;" class="text-yellow" >'+result.trainingCampVOList[i].trainingCampVOList[k].dialedCallsCount+' / '+result.trainingCampVOList[i].trainingCampVOList[k].unDialedCount+'</td>';
								else
									str+='<td style="text-align:center;" > 0 </td>';
								
								if(result.trainingCampVOList[i].trainingCampVOList[k].interestedCount != null)
									str+='<td style="text-align:center;" class="interested-text" >'+result.trainingCampVOList[i].trainingCampVOList[k].interestedCount+'</td>';
								else
									str+='<td style="text-align:center;" > 0 </td>';
								
								if(result.trainingCampVOList[i].trainingCampVOList[k].conformLaterCount != null)
									str+='<td style="text-align:center;" >'+result.trainingCampVOList[i].trainingCampVOList[k].conformLaterCount+'</td>';
								else
									str+='<td style="text-align:center;" > 0 </td>';
								
								
								if(result.trainingCampVOList[i].trainingCampVOList[k].notInterestedCount != null)
									str+='<td style="text-align:center;" >'+result.trainingCampVOList[i].trainingCampVOList[k].notInterestedCount+'</td>';
								else
									str+='<td style="text-align:center;" > 0 </td>';
								
								
								if(result.trainingCampVOList[i].trainingCampVOList[k].batchConfirmationCount != null)
									str+='<td style="text-align:center;" >'+result.trainingCampVOList[i].trainingCampVOList[k].batchConfirmationCount+'</td>';
								else
									str+='<td style="text-align:center;" > 0 </td>';
								
								if(result.trainingCampVOList[i].trainingCampVOList[k].availableMembersCount != null)
									str+='<td style="text-align:center;" >'+result.trainingCampVOList[i].trainingCampVOList[k].availableMembersCount+'</td>';
								else
									str+='<td style="text-align:center;" > 0 </td>';
								
								if(result.trainingCampVOList[i].trainingCampVOList[k].othersCount != null)
									str+='<td style="text-align:center;" >'+result.trainingCampVOList[i].trainingCampVOList[k].busyCount+' / '+result.trainingCampVOList[i].trainingCampVOList[k].othersCount+'</td>';
								else
									str+='<td style="text-align:center;" > 0 </td>';
								str+='</tr>';
								
					}
				}
				
				
			}
			
			str+='</tbody>';
			str+='</table>';									
                                                	
			$("#"+divId+"").html(str);										
    									
	}
	else{
			$('#'+divId+'').html('<div class="text-center"><b> Calender Schedule Data Not Available...</b></div>');
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

$(document).on("click",".availTypeCls",function(){
	
var id = $(this).attr('id');

if(id.trim() == 'ownCallsId'){	
		$('#availCallsId').val('true');
	}
if(id.trim() == 'othersCallsId'){	
	$('#availCallsId').val('false');
	}
});