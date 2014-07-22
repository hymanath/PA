function showHideTabs(id)
{

	$(".errClass").html('');
	if(id == "completedBoothsTab")
	{
		$('#statusReportDiv').hide();
		$('#webMontrId').hide();
		$('#callCenter').hide();
		$('#startTime').hide();
		$('#boothWise').hide();
		$('#dataCollector').hide();
        $('#inActiveUsersDetails').hide(); 
		$('#inActiveUsersDetails').hide();
		 $('#completeBooths').show();

		
	}
	else if(id == "callCenterTab")
	{
		$('#statusReportDiv').hide();
		$('#webMontrId').show();
		$('#callCenter').show();
		$('#startTime').hide();
		$('#boothWise').hide();
		$('#dataCollector').hide();
        $('#inActiveUsersDetails').hide(); 
		$('#completeBooths').hide();

		
	}
	else if (id == "startTimeTab")
	{
		$('#statusReportDiv').hide();
		$('#webMontrId').hide();
		$('#callCenter').hide();
		$('#startTime').show();
		$('#boothWise').hide();
		$('#dataCollector').hide();
		$('#inActiveUsersDetails').show();
		$('#completeBooths').hide();
	}
	else if(id == "boothWiseTab")
	{
		$('#statusReportDiv').hide();
		$('#webMontrId').hide();
		$('#callCenter').hide();
		$('#startTime').hide();
		$('#boothWise').show();
		$('#dataCollector').hide();
		$('#inActiveUsersDetails').hide();
		$('#completeBooths').hide();

	}
	else if(id == "surveyStatusRprtTab"){

		$('#statusReportDiv').show();
		$('#callCenter').hide();
		$('#startTime').hide();
		$('#boothWise').hide();
		$('#dataCollector').hide();
        $('#inActiveUsersDetails').hide();	
		$('#completeBooths').hide();
	}
	else
	{
		$('#statusReportDiv').hide();
		$('#webMontrId').hide();
		$('#callCenter').hide();
		$('#startTime').hide();
		$('#boothWise').hide();
		$('#dataCollector').show();
		$('#inActiveUsersDetails').hide();
		$('#completeBooths').hide();

	}
}
function getConstituencyLeadersList(divId){
var value = $('#constiList').val();
var jsObj = 
	{
		constiId:value,
		task : "assignLeader"
	}
	$("#userTypeProcessingImage").show();
	$.ajax({
		type:'GET',
		url: 'getSurveyConstituencyUsersListAction.action',
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		}).done(function(result){
				$("#userTypeProcessingImage").hide();
				$('#'+divId+'').find('option').remove();
				$('#'+divId+'').append('<option value="0"> Select User </option>');
				if(result != null && result.length>0){
					for(var i in result){
						$('#'+divId+'').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
					}
				}
		});
		
}


	
function getAssignedBoothsForLeader(divId){
var constiId = $('#constiList').val();
var leaderId = $('#leaderList').val();
$("#boothProcessingImage").show();
var jsObj = 
	{
		constituencyId:constiId,
		surveyUserId:leaderId,
		task : "getAssignedBoothsForLeader"
	}

	$.ajax({
		type:'GET',
		url: 'getAssignedBoothsForLeaderAction.action',
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		}).done(function(result){
		$("#boothProcessingImage").hide();
				$('#'+divId+'').find('option').remove();
				$('#'+divId+'').append('<option value="0"> Select Booth </option>');
				if(result != null && result.length>0){
					for(var i in result){
						$('#'+divId+'').append('<option value="'+result[i].id+'">Booth No - '+result[i].name+'</option>');
					}
				}
		});
		
}

var casteList = new Array();
function getSurveyVotersList(userId,userName,boothId,boothNo,date){
$('#userInfoDiv1').hide();
$('#userInfoDiv2').hide();
$('#userInfoDiv3').hide();
$('#casteInfoDiv').hide();
var newCasteArr = new  Array();
var surveyDate = $('#FielddateId').val();
$('#searchDataImg').show();
		$('#webMonitoringImage').show();
		var jsObj = 
		{
			constituencyId:webConstId,
			surveyUserId:userId,
			boothId : boothId,
			searchDate:date,
			task : "getSurveyVotersList"
		}
	$('#webMonitoringImage').hide();
	$.ajax({
		type:'GET',
		url: 'getSurveyVotersListAction.action',
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		}).done(function(results){
		$('#searchDataImg').hide();
		$('#voterInfoDIv').html('');
		$('#casteInfoDiv').html('');
		$('#userInfoDiv').html('');
		var str = '';

		var totalVoters = 0;
		totalVoters = results != null ? results[0].count:0;
		$('#webMontrId').show();
		if(results != null && results[0].subList.length>0)
		{
		
				var result = results[0].subList;
				
				str +='<table class="table table-bordered m_top20 table-hover table-striped" id="voterDetlsTab" >';
				str +='	<thead class="alert alert-success">';
				str +='	<tr>';
				str +='<th> S.NO </th>';
				str +='<th>H.NO</th>';
				str +='<th> Name </th>';
				str +='<th> Gaurdian Name </th>';
				str +='<th> Caste </th>';
				str +='<th> Caste Matched  </th>';
				str +='<th> Mobile Number </th>';
				str +='<th> Hamlet </th>';
				str +='</tr>';
				str +='</thead>';
				str +='<tbody>';
				for(var i in result)
				{
					str +='<tr>';					
					str +='<input type="hidden" value="'+result[i].voterId+'" id="voterId"/>';
					str +='<input type="hidden" value="'+result[i].userid+'" id="surveyUserId"/></td>';
					str +='<td>'+result[i].serailNo+'</td>';
					str +='<td>'+result[i].partNo+'</td>';
					str +='<td>'+result[i].userName+'</td>';
					str +='<td>'+result[i].voterName+'</td>';
					
					/*	starting  only caste available details */
					/*
					str +='<td>'+result[i].caste+'';
				
					if(result[i].casteMatchedCount == 1) //correct
					{ 
						str +='<div data-toggle="buttons-radio" class="btn-group">';
						str +='<button class="btn btn-mini  btn-success " id="updateBtnId'+i+'" type="button" onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',1,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+');">correct</button>';
						str +='<button class="btn btn-mini " id="updateBtnIdA'+i+'" type="button" onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',0,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+');" >wrong</button>';
						str +='<div id="casteErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
						
						str +='<input type="hidden" value="1" id="isCasteMatched'+i+'"/>';
						
						str +='</div>';
						str +='</td>';
						
						str +='<td> <select id="casteListId'+i+'"  disabled="disabled" style="width:165px;" onchange="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',10,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+');"><option value="0"> Select Caste </option> ';
						str +='</select></td>';	
											
					}
					else if(result[i].casteMatchedCount == 2)
					{
						str +='	<div data-toggle="buttons-radio" class="btn-group">';
						
						str +='	<button class="btn btn-mini " id="updateBtnId'+i+'" type="button"  onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',1,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+');" >correct</button>';
						
						str +='	<button class="btn btn-mini  btn-warning " id="updateBtnIdA'+i+'" type="button"  onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',0,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+');">wrong</button>';
						
						str +='<div id="casteErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
						str +='<input type="hidden" value="0" id="isCasteMatched'+i+'"/>';
						str +='</div>';
						str +='</td>';
						
						str +='<td> <select id="casteListId'+i+'"   style="width:165px;" onchange="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',10,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+');"><option value="0"> Select Caste </option> ';
						if(results[1] != null  && results[1].genericVOList.length > 0){
							for(var k in results[1].genericVOList){
								if(results[1].genericVOList[k].id == result[i].casteId)
								{
									str +='<option value="'+results[1].genericVOList[k].id+'" selected="selected">'+results[1].genericVOList[k].name+'</option>';
								}
								else
								{
									str +='<option value="'+results[1].genericVOList[k].id+'">'+results[1].genericVOList[k].name+'</option>';
								}
								
							}
						}
						str +='</select></td>';	
					}
					else
					{
						str +='<div data-toggle="buttons-radio" class="btn-group">';
						
						str +='<button class="btn btn-mini " id="updateBtnId'+i+'" type="button" onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',1,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+');" >correct</button>';
						
						str +='<button class="btn btn-mini " id="updateBtnIdA'+i+'" type="button" onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',0,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+');" >wrong</button>';
						
						str +='<div id="casteErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
						str +='<input type="hidden" value="" id="isCasteMatched'+i+'"/>';
						str +='</div>';
						str +='</td>';
						
						str +='<td> <select id="casteListId'+i+'"  disabled="disabled" style="width:165px;" onchange="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',10,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+');"><option value="0"  > Select Caste </option> ';
						str +='</select></td>';	
					}			
					*/
					/* ending  only caste available details */	
					
					/* starting  caste available and not available details */
					
			
					
					if(result[i].caste.trim().length >0)
					{
						str +='<td>'+result[i].caste+'';
					
					
				
						if(result[i].casteMatchedCount == 1) //correct
						{ 
							str +='<div data-toggle="buttons-radio" class="btn-group">';
							str +='<button class="btn btn-mini  btn-success " id="updateBtnId'+i+'" type="button" onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',1,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+');">correct</button>';
							str +='<button class="btn btn-mini " id="updateBtnIdA'+i+'" type="button" onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',0,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+');" >wrong</button>';
							str +='<div id="casteErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
							
							str +='<input type="hidden" value="1" id="isCasteMatched'+i+'"/>';
							
							str +='</div>';
							str +='</td>';
							
							str +='<td> <select id="casteListId'+i+'"  disabled="disabled" style="width:165px;" onchange="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',10,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+');"><option value="0"> Select Caste </option> ';
							str +='</select></td>';	
												
						}
						else if(result[i].casteMatchedCount == 2)
						{
							str +='	<div data-toggle="buttons-radio" class="btn-group">';
							
							str +='	<button class="btn btn-mini " id="updateBtnId'+i+'" type="button"  onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',1,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+');" >correct</button>';
							
							str +='	<button class="btn btn-mini  btn-warning " id="updateBtnIdA'+i+'" type="button"  onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',0,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+');">wrong</button>';
							
							str +='<div id="casteErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
							str +='<input type="hidden" value="0" id="isCasteMatched'+i+'"/>';
							str +='</div>';
							str +='</td>';
							
							str +='<td> <select id="casteListId'+i+'"   style="width:165px;" onchange="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',10,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+');"><option value="0"> Select Caste </option> ';
							if(results[1] != null  && results[1].genericVOList.length > 0){
								for(var k in results[1].genericVOList){
									if(results[1].genericVOList[k].id == result[i].casteId)
									{
										str +='<option value="'+results[1].genericVOList[k].id+'" selected="selected">'+results[1].genericVOList[k].name+'</option>';
									}
									else
									{
										str +='<option value="'+results[1].genericVOList[k].id+'">'+results[1].genericVOList[k].name+'</option>';
									}
									
								}
							}
							str +='</select></td>';	
						}
						else
						{
							str +='<div data-toggle="buttons-radio" class="btn-group">';
							
							str +='<button class="btn btn-mini " id="updateBtnId'+i+'" type="button" onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',1,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+');" >correct</button>';
							
							str +='<button class="btn btn-mini " id="updateBtnIdA'+i+'" type="button" onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',0,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+');" >wrong</button>';
							
							str +='<div id="casteErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
							str +='<input type="hidden" value="" id="isCasteMatched'+i+'"/>';
							str +='</div>';
							str +='</td>';
							
							str +='<td> <select id="casteListId'+i+'"  disabled="disabled" style="width:165px;" onchange="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',10,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+');"><option value="0"  > Select Caste </option> ';
							str +='</select></td>';	
						}			
					
					}
					else{
						str +='<td>';
						str +='<input type="hidden" value="" id="isCasteMatched'+i+'"/>';
						str +='</td>';
						
							str +='<td> <select id="casteListId'+i+'"   style="width:165px;" onchange="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',10,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+');"><option value="0"> Select Caste </option> ';
							if(results[1] != null  && results[1].genericVOList.length > 0){
								for(var k in results[1].genericVOList){
									if(results[1].genericVOList[k].id == result[i].casteId)
									{
										str +='<option value="'+results[1].genericVOList[k].id+'" selected="selected">'+results[1].genericVOList[k].name+'</option>';
									}
									else
									{
										str +='<option value="'+results[1].genericVOList[k].id+'">'+results[1].genericVOList[k].name+'</option>';
									}
									
								}
							}
							str +='</select></td>';
					}
					
					
					/* ending  caste available and not available details */
					
							var mobNo = '';
							if(result[i].mobileNo != null  && result[i].mobileNo.length != 0 )
							{						
										mobNo = result[i].mobileNo					
										str +='<td>'+mobNo+'<div data-toggle="buttons-radio" class="btn-group">';
																			
										if(result[i].mobileMatchedCount == 1)
										{
											str +='<button class="btn btn-mini  btn-success " id="updateMBtnId'+i+'" type="button" onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',2,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+');" >correct </button>';
											
											str +='<button class="btn btn-mini "  id="updateMBtnIdA'+i+'" type="button" onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',3,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+');">wrong</button>';
											
											str +='<div id="mobileErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
											str +='<input type="hidden" value="2" id="isTestedMobile'+i+'"/>';
											str +='</div>';
														
										}
										else if(result[i].mobileMatchedCount == 2)
										{

											str +='<button class="btn btn-mini "  id="updateMBtnId'+i+'" type="button" onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',2,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+');">correct </button>';
											
											str +='<button class="btn btn-mini  btn-warning " id="updateMBtnIdA'+i+'" type="button"  onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',3,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+');">wrong</button>';
											
											str +='<div id="mobileErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
											str +='<input type="hidden" value="3" id="isTestedMobile'+i+'"/>';
											str +='</div>';
														
										}
										else
										{	

											str +='<button class="btn btn-mini "  type="button" id="updateMBtnId'+i+'" onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',2,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+');" >correct </button>';
										
											str +='<button class="btn btn-mini "  id="updateMBtnIdA'+i+'" type="button" onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',3,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+');">wrong</button>';
										
											str +='<div id="mobileErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
											str +='<input type="hidden" id="isTestedMobile'+i+'"  value=""/>';
											str +='</div>';
										}

											str +='</td>';
								}	
							else{
											str +='<td>';
											str +='<input type="hidden" value="" id="isTestedMobile'+i+'"/>';
											str +='</td>';

								}			
											str +='<td>';
											str +='<div class="callcenter_voterDetals_widget">';
											str +='<ul class="unstyled inline">';
											str +='<li>'+result[i].hamletName+'</li>';					
											str +='</ul>';
											str +='</div>';
											str +='</td>';
											str +='</tr>';
											

									/*	var obj = {
										id: 'casteListId'+i+'',
										value : result[i].casteId
									}
									newCasteArr.push(obj);*/
									
									
				}
				str +='</tbody>';
				str +='</table>';
								
								
		}
		else
		{
			str +='	No Data Available. ';
		}
		
		$('#voterInfoDIv').html(str);
		$('#voterDetlsTab').dataTable({
		"iDisplayLength": 100,
		"aLengthMenu": [[100, 200, 500, -1], [100, 200, 500, "All"]]
		});
		
		var str1='';
		var str2='';
		
		$('#userInfoDiv1').show();
		$('#userInfoDiv2').show();
		$('#userInfoDiv3').show();
		
		str2 +=' <span style="font-weight:bold;"> User Name  </span> : '+userName+'</br>';		
		$('#userInfoDiv1').html(str2);
		
		str2 ='';
		str2 +=' <span style="font-weight:bold;"> Booth No </span> : '+boothNo+' </br>';
		
		$('#userInfoDiv2').html(str2);
		
		str2 ='';
		str2 +=' <span style="font-weight:bold;"> Total Votes </span> : '+totalVoters+' </br>';
		$('#userInfoDiv3').html(str2);
		
		if(results != null && results[0].genericVOList.length>0)
		{
				$('#casteInfoDiv').show();
				str1 +='<b> Caste Details </b> : <br> ';
				var length = results[0].genericVOList.length;
				
				for(var k in results[0].genericVOList){
				var perc = (results[0].genericVOList[k].count * 100 ) / totalVoters;
					str1 +=''+results[0].genericVOList[k].name+' ('+parseFloat(perc).toFixed(2)+') ';
					
					if(k < length-1){
						str1 +=', ';
					}					
				}
		 }
		 
			//console.log(newCasteArr);
		 
		  $('#casteInfoDiv').html(str1);
		  
		/*  if(newCasteArr != null && newCasteArr.length >0){
				for(var i in newCasteArr){
					if(newCasteArr[i].value != null)
						$('#'+newCasteArr[i].id+'').val(newCasteArr[i].value);
				}
		  }*/
		
		
		
			if(results[1] != null  && results[1].genericVOList.length > 0){
				for(var k in results[1].genericVOList){
					var objec = {
						id:results[1].genericVOList[k].id,
						value : results[1].genericVOList[k].name
					}								
					casteList.push(objec);
				}
			}

		});


}

function updateDetails(casteId,mobileId, value,voterId,surveyUserId,selectionType,userId,boothId,date,newCasteId,updateBtnId)
{
	var casteMatched = $('#'+casteId+'').val();
	var mobileMached = $('#'+mobileId+'').val();

	// 0 ,2 -- correct
	// 1 ,3 -- wrong
	if(value == 0 || value == 1)
	{
		// caste 
		$('#'+casteId+'').val(value);
		if(mobileMached.length == 0 || (mobileMached != 2 && mobileMached != 3)){
			$('#'+mobileId+'').val('6');
		}
	}
	
	if(value == 2 || value == 3)
	{
		// mobile
		
		$('#'+mobileId+'').val(value);
		if(casteMatched.length == 0 || (casteMatched != 0 && casteMatched != 1)){
			$('#'+casteId+'').val('5');
		}
		
	}
	
	
	if(value == 10){
		if(casteMatched == null || casteMatched.trim().length == 0){
			$('#'+casteId+'').val('0');
		}
		if(mobileMached == null || mobileMached.trim().length == 0){
			$('#'+mobileId+'').val('6');
		}
	}



updateStatusDetails(casteId,mobileId,voterId,surveyUserId,selectionType,userId,boothId,date,newCasteId,updateBtnId);
}

function updateStatusDetails(isCasteMatched,mobileMatched,voterId,surveyUserId,selectionType,userId,boothId,date,newCasteId,updateBtnId)
{

	if(selectionType =='mobile'){
		$('#updateMBtnId'+updateBtnId+'').removeClass('btn-success');
		$('#updateMBtnIdA'+updateBtnId+'').removeClass('btn-warning');
	}
	else{
		$('#updateBtnId'+updateBtnId+'').removeClass('btn-success');
		$('#updateBtnIdA'+updateBtnId+'').removeClass('btn-warning');  
	}

	var voterInfoArr = new Array();
	var isMobileVerified = $('#'+mobileMatched+'').val();
	var isMatched = $('#'+isCasteMatched+'').val();
	console.log("isMatched   :"+isMatched );
	var casteId = 0;

	if(newCasteId != 0){
		casteId = $('#'+newCasteId+'').val();
		console.log(casteId);
		if(isMatched == 1){
			casteId = 0;
		}
	}
	

	var obj = {
		voterId:voterId,
		surveyUserId:userId,
		isMobileVerified:isMobileVerified,
		isMatched :isMatched,
		boothId : boothId,
		casteId:casteId
	}
	
	voterInfoArr.push(obj);

	//console.log(voterInfoArr);
	var jsObj = 
	{
		voterInfoArr:voterInfoArr,
		task : "getSurveyVotersList"
	}

	$.ajax({
		type:'GET',
		url: 'saveSurveyCallStatusDetilsAction.action',
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		}).done(function(result){
			if(result != null && result.resultCode == 0){
				
				
				if(selectionType =='mobile')
				{
					if(isMobileVerified == 2){
						$('#updateMBtnId'+updateBtnId+'').addClass('btn-success');
						$('#updateMBtnIdA'+updateBtnId+'').removeClass('btn-warning');
					}
					if(isMobileVerified == 3){
							$('#updateMBtnIdA'+updateBtnId+'').addClass('btn-warning');
							$('#updateMBtnId'+updateBtnId+'').removeClass('btn-success');
					}
				}
				else
				{
					$('#'+newCasteId+'').find('option').remove();
					$('#'+newCasteId+'').append('<option value="0"> Select Caste </option>');
					
					
					if(isMatched == 1){
						$('#updateBtnId'+updateBtnId+'').addClass('btn-success');
						$('#updateBtnIdA'+updateBtnId+'').removeClass('btn-warning');
						$('#'+newCasteId+'').prop('disabled','disabled');
					}
					if(isMatched == 0){
						$('#updateBtnIdA'+updateBtnId+'').addClass('btn-warning');	
						$('#updateBtnId'+updateBtnId+'').removeClass('btn-success');
						$('#'+newCasteId+'').prop('disabled',false);	
						
						if(casteList.length >0){
							for(var k in casteList){
								$('#'+newCasteId+'').append('<option value="'+casteList[k].id+'">'+casteList[k].value+'</option>');
							}
						}
					}
					
					$('#'+newCasteId+'').val(casteId);
				}
			
			}
			else{
				alert('Error occured while saving record.');
			}
			
		});
	

}


function getconstituencies(divId)
{


	var jsObj =
	{
	
	task : "getConstituencies"
	}
	$.ajax({
	type:'GET',
	url: 'getsurveyuserConstituenciesAction.action',
	dataType: 'json',
	data: {task:JSON.stringify(jsObj)},
	}).done(function(result){

	$('#'+divId+'').find('option:not(:first)').remove();
	if(result != null && result.length > 0)
	{
	for(var i in result)
	{
		
	$('#'+divId+'').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
	}

	}
	
	});
}


function getSurveyUserLoctionCount()
{
	
var constituencyID = $("#constituencyId").val();
var userTypeId = $("#userTypeId").val();
var date=$("#FielddateId").val();
$("#basicCountDiv").html('');
$("#errorMsgDiv").html('');
var str ='';

if(constituencyID == 0)
	{
str +='<font color="red">Select Constituency</font>';
	}
else if(userTypeId == 0)
	{
str +='<font color="red">Select User Type</font>';
	}
	
	if(str.length > 0)
	{
		
$("#errorMsgDiv").html(str);
return;
	}
	$("#processingImg").show();
	var jsObj =
	{
	    constituencyId : constituencyID,
		userTypeId:userTypeId,
		date:date,
		userIds : userIds,
		task : "getLocationCount"
	}
	$.ajax({
	type:'GET',
	url: 'getSurveyUserLoctionCount.action',
	dataType: 'json',
	data: {task:JSON.stringify(jsObj)},
	}).done(function(result){
		$("#processingImg").hide();
     buildSurveyUserStatusCount(result);
	
	});
}

var webConstId =0;
var webuserId =0;
var webBoothId =0;
 function buildSurveyUserStatusCount(result)
{
	var str ='';
	if(result.length == 0)
	{
str+='<font style="color:red;">No Data avilable</font>';
	$("#basicCountDiv").html(str).css("text-align","center");
return;
	}
	
	str+='<table class=" table table-bordered m_top20 table-hover table-striped">';
	str+='<thead >';
	str+='<tr class="alert alert-success">'
	str+='<th rowspan="5">DCName</th>';
	str+='<th rowspan="5">Booth</th>';
	str+='<th rowspan="5"> Total Voters</th>';
	str+='<th colspan="3" style="text-align : center;">Data Collector</th>';
	str+='<th colspan="5" style="text-align : center;">Web monitoring</th>';
	
	str+='</tr>';

	str+='<tr class="alert alert-success">';
    str+='<th >Caste Mapped</th>';
	str+='<th >Hamlet Mapped</th>';
	str+='<th >Mobile Collected</th>';

	str+='<th>TOTAL </th>';
	str+='<th>Mobile MATCHED</th>';
	str+='<th>Mobile UN MATCHED</th>';
	str+='<th>CASTE MATCHED</th>';
	str+='<th>CASTE UN MATCHED</th>';
	str+='</tr>';
	str+='</thead>';
	str+='<tbody>';
	for(var i in result)
	{

		for(var j=0;j<result[i].subList.length;j++)
		{
			webBoothId = result[i].subList[j].boothId;
			str+='<tr>';
			str+='<td> <a href="javascript:{getDataCollectorInfo('+result[i].userid+',\''+result[i].userName+'\','+result[i].subList[j].boothId+','+result[i].subList[j].partNo+',\''+$('#FielddateId').val()+'\');}">'+result[i].userName+' </a></td>';
			str+='<td> '+result[i].subList[j].partNo+'</td>';
			if(result[i].subList[j].totalVoters == null)
			{
				str+='<td>-</td>';
			}
			else
			{
			str+='<td>'+result[i].subList[j].totalVoters+'</td>';
			}
			str+='<td>'+result[i].subList[j].casteCount+'</td>';
			str+='<td>'+result[i].subList[j].hamletCount+'</td>';
			str+='<td>'+result[i].subList[j].mobileNoCount+'</td>';
		    str+='<td>'+result[i].subList[j].count+'</td>';
			str+='<td>'+result[i].subList[j].mobileMatchedCount+'</td>';
			str+='<td>'+result[i].subList[j].mobileNotMatchedCount+'</td>';
			str+='<td>'+result[i].subList[j].casteMatchedCount+'</td>';
			str+='<td>'+result[i].subList[j].casteNotMatchedCount+'</td>';

			str+='</tr>';
		}
	}
	str+='</tbody>';
	str+='</table>';
	$("#basicCountDiv").html(str);
	

}

$(function() {
	$("#dateId,#FielddateId").datepicker({ 
	dateFormat: 'dd-mm-yy',
   }).datepicker('setDate', new Date());
  
});


function getLeadrDetailsByConstituency()
{
	var jObj =
	{
	  constiId: $('#userConstituencyId').val()
	};
	$.ajax({
			type:'GET',
			url: 'getConstituencyWiseLeadersAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		  }).done(function(result){	
			if(result != null && result.length > 0)
			{
				var str = '';
				 str += '<div class="span12 m_top20 widgetservey">';
				 str += '<h4>Leader Details</h4>';
				 str += '<div class="row-fluid" >';
				 str += '<table class="table table-bordered m_top20 table-hover table-striped username">';	
				 str += '<thead class="alert alert-success">';
				 str += '<tr> ';
				 str += '<th>Name </th>	';
				 str += '<th>Mobile No</th>	';		 
				 str += '</tr>	';						
				 str += '</thead>';
				 str += '<tbody>';
				for(var i in result)
				{
					str += '<tr>';
					str += '<td><a href="javascript:{getInActiveUsersDetailsByLeaderId('+result[i].id+')}">'+result[i].name+'</a></td>';
					str += '<td>'+result[i].desc+'</td>';
					str += '</tr>';
				}
				 str += '</tbody>';
				str += '</table>';
				str += '</div></div>';
				$('#leaderDetailsDiv').html(str);
			}
		});
}
function getUserDetailsByConstituency()
{
	getLeadrDetailsByConstituency();
	$('#userDetailsReportDiv').html("");
	var constituencyId = $("#userConstituencyId").val();
	var dateVal = $("#dateId").val();

	if(constituencyId == 0)
	{
		$("#errDivIdForStartTime").html("Please Select Constituency").css("color","red");
		return;
	}
	if(dateVal.length == 0)
	{
		$("#errDivIdForStartTime").html("Please Select Date").css("color","red");
		return;
	}
	$("#userFieldImage").show();
	$("#errDivIdForStartTime").html("");
	var jObj =
	{
	 
	  constituencyId: constituencyId,
	  dateStr :dateVal,
	  userId : userIds

	};
	$.ajax({
			type:'GET',
			url: 'getLatLongForSurveyUsersByConstituencyAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		  }).done(function(result){				
				//buildDetailsTable(result);
				buildLocationDetails(result);
		});
}



var map = '';
var apaccampus = {
"type": "Point",
"crs": { "type": "name", "properties": { "name": "urn:ogc:def:crs:OGC:1.3:CRS84" } },
"features": []
}
function buildLocationDetails(result)
{ 
	document.getElementById('weathermap').innerHTML = "<div class='span12 m_top20 widgetservey' id='map' style='height:450px'></div>";
	$('#detaildDiv').show();
	$.each(result,function(index,value){
	if(value.latitude != null && value.longititude != null)
	{
		var voterDetails = 
		{ "type": "Feature",
		"properties": {"name":value.name,"location":value.location,"partno":value.partno,"url":value.url,"value":value.value,"villageCovered":value.villageCovered},
			"geometry": { "type": "Polygon", "coordinates": [[[value.latitude,value.longititude]]] }
		};
		apaccampus.features.push(voterDetails);
	}
	});
	map = new L.Map('map').setView(new L.LatLng(result[0].longititude,result[0].latitude), 10);
	var osm = new L.TileLayer('http://{s}.tile.osmosnimki.ru/kosmo/{z}/{x}/{y}.png');
	var mpn = new L.TileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png');
	var qst = new L.TileLayer('http://otile1.mqcdn.com/tiles/1.0.0/osm/{z}/{x}/{y}.png', {attribution:'Tiles Courtesy of <a href="http://www.mapquest.com/" target="_blank">MapQuest</a> <img src="http://developer.mapquest.com/content/osm/mq_logo.png">'}).addTo(map);
	var hyb = new L.TileLayer('http://{s}.tile.osmosnimki.ru/hyb/{z}/{x}/{y}.png');
	var irs = new L.TileLayer('http://tile.osmosnimki.ru/basesat/{z}/{x}/{y}.jpg');
	var wms = new L.TileLayer.WMS('http://wms.latlon.org/', {layers:'irs', crs: L.CRS.EPSG4326});
	var kadastr = new L.TileLayer.WMS('http://maps.rosreestr.ru/arcgis/services/Cadastre/CadastreWMS/MapServer/WMSServer', {format:'image/png', transparent:'true', layers:'16,15,14,13,11,10,9,22,21,20,19,18,7,6', tileSize:512});

	map.addControl(new L.Control.Scale({width: 100, position: 'bottomleft'}));
	map.addControl(new L.Control.Permalink());

	map.addControl(new L.Control.Layers({ 'Mapnik':mpn, 'MapQuest':qst,  'Google':new L.Google()},{}
	 ));
	L.geoJson(apaccampus, {
		
		style: function (feature) {
			return feature.properties && feature.properties.style;
		},
		
		onEachFeature: onEachFeature,
		
		
	   pointToLayer: function (feature, latlng) {
			return L.circleMarker(latlng, {
				
			});
		}
	}).addTo(map);
	for(var i in result)
	{
		if(result[i].latitude != null && result[i].longititude != null)
		{
			var iconImg = '';
			if(result[i].type == "Data Collectors")
			{
				iconImg = 'images/DC.png';
			}
			else
			{
				iconImg = 'images/DV.png';
			}
			var icon = L.icon({
			iconUrl: iconImg,

			iconSize:     [30, 30], // size of the icon
			shadowSize:   [10, 10], // size of the shadow
			iconAnchor:   [10, 10], // point of the icon which will correspond to marker's location
			shadowAnchor: [4, 62],  // the same for the shadow
			popupAnchor:  [-3, -76] // point from which the popup should open relative to the iconAnchor
		   });
			
	
			var markers = new L.Marker([result[i].longititude,result[i].latitude],{icon: icon});
			var popuoContent = "<table class='table table-bordered m_top20 table-hover table-striped username'><tr><td>Name : </td><td>"+result[i].name+"</td></tr>";
			popuoContent += "<tr><td>Mobile : </td><td>"+result[i].mandalName+"</td></tr>";
			popuoContent += "</table>";
			markers.bindPopup(popuoContent);
			map.addLayer(markers);	
		}
		
	}
	getUserDetailsByConstituencyForTable();
} 


function getUserDetailsByConstituencyForTable()
{
	$('#userDetailsReportDiv').html("");
	var constituencyId = $("#userConstituencyId").val();
	var dateVal = $("#dateId").val();

	if(constituencyId == 0)
	{
		$("#errDivIdForStartTime").html("Please Select Constituency").css("color","red");
		return;
	}
	if(dateVal.length == 0)
	{
		$("#errDivIdForStartTime").html("Please Select Date").css("color","red");
		return;
	}

	
	var jObj =
	{
	 constituencyId:constituencyId,
	 date:dateVal,
	 userIds : userIds,
	 task:"getDetails"

	};
	$.ajax({
			type:'GET',
			url: 'getUserDetailsByConstituencyAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		  }).done(function(result){				
				buildDetailsTable(result);
		});
}
function buildDetailsTable(result)
{
	$("#userFieldImage").hide();
	if(result == null || result.length == 0)
	{
		$('#userDetailsReportDiv').html("<font color='red'> NO DATA AVILABLE<font>");
		return;
	}

	var str = '';

	str+='<table class="table table-bordered m_top20 table-hover table-striped">';
    str+='<thead class="alert alert-success">';
	 str+='<tr>';
	  str+='<th>DC Name</th>';
  	  str+='<th>Mobile No</th>';
	  str+='<th>Start Time</th>';
	  str+='<th>Booth No</th>';
	  str+='<th>Mandal</th>';
	  str+='<th>Panchayat</th>';
	  str+='<th>Booth Location</th>';
	  str+='<th>Areas Covered</th>';
	  str += '<th>Data Collected Map</th>	';	
	  str += '<th>Tracking MAP</th>	';	
	 str+='</tr>';
	str+='</thead>';

	str+='<tbody>'
	
	for(var i in result){	 
	   for(var j in result[i].subList)
	   {
	   if(result[i].userType == 'Data Collectors'){
			str+='<tr>';
			str+='<td><a onClick="getUserDetails('+result[i].userid+','+result[i].subList[j].boothId+',\'resultDiv'+i+''+j+'\',\'buildDiv'+i+''+j+'\')" style="cursor: pointer;">'+result[i].userName+'</a> <span class="label label-info pull-right"></td>';
	
			if(result[i].mobileNo == null)
				str+='<td>-</td>';
			else
				str+='<td>'+result[i].mobileNo+'</td>';	
				
			if(result[i].surveyDate == null)
				str+='<td>-</td>';
			else
				str+='<td>'+result[i].surveyDate+'</td>';	
				
			str+='<td>'+result[i].subList[j].partNo+'</td>';
			str+='<td>'+result[i].subList[j].mandalName+'</td>';
			str+='<td>'+result[i].subList[j].panchayatName+'</td>';
			str+='<td>'+result[i].subList[j].localArea+'</td>';
			var areaCovered = result[i].subList[j].villageCovered.replace(/,/g , "  ");
			str+='<td>'+areaCovered+'</td>';
			str += '<td><a onClick="openTrackinWindow('+result[i].userid+',\''+$('#dateId').val()+'\',1) " style="cursor: pointer;"><img src="images/DC.png"></img></a></td>	';
		 str += '<td><a onClick="openTrackinWindow('+result[i].userid+',\''+$('#dateId').val()+'\',2) " style="cursor: pointer;"><img src="images/DC.png"></img></a></td>	';
			str+='</tr>';
			str+='<tr id="resultDiv'+i+''+j+'" style="display:none;" class="buildDivCls">';
			str+='<td colspan="10"> <div id="buildDiv'+i+''+j+'"></div></td>';
			str+='</tr>';
		}
		}
   }
   str+='</tbody>';
   str+='</table>';

 $('#tableDiv').html(str);
} 

function openTrackinWindow(userId,date,id)
{
	window.open('userWiseTrackingAction.action?userId='+userId+'&date='+date+'&userTypeId='+id+'');
}
/* function openUserTrackingMapPage(id)
{
	window.open('surveyUserTrackingAction.action?userId='+$('#userConstituencyId').val()+'&date='+$('#dateId').val()+'&userTypeId='+id+'');
} */


function getUserDetails(userId,boothId,resultDiv,buildDiv)
{

$('.buildDivCls').hide();
$('#'+resultDiv+'').show();

	var jObj = 
	{
	 userId: userId,
	 boothId : boothId
	}
	$.ajax({
			type:'GET',
			url: 'getRespectiveCountForBoothAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
			}).done(function(result){
			$('#'+buildDiv+'').html('');
			if(result != null)
			{
				var str = '';
				str += '<div class="row-fluid">';
				str += '<div class="span12 booths-Overview-widget">';
				str += '<div class="row-fluid">';
				str += '<h4>Total Booth Voters - '+result[0]+'</h4>';
				str += '</div>';
				str += '<div class="row-fluid">';
				str += '<ul class="inline unstyled booths-Overview-widget-nav">';
				str += '<li>';
				str += '<hgroup>';
				str += '<h4>CASTE</h4>';
				str += '<h2>'+result[1]+'</h2>';
				str += '<h5>Completed</h5>';
				str += '</hgroup>';
				str += '</li>';
				str += '<li>';
				str += '<hgroup>';
				str += '<h4>HAMLET</h4>';
				str += '<h2>'+result[2]+'</h2>';
				str += '<h5>Mapped</h5>';
				str += '</hgroup>';
				str += '</li>';
				str += '<li>';
				str += '<hgroup>';
				str += '<h4>LOCAL AREA</h4>';
				str += '<h2>'+result[3]+'</h2>';
				str += '<h5>Identified</h5>';
				str += '</hgroup>';
				str += '</li>';
				str += '<li>';
				str += '<hgroup>';
				str += '<h4>CADRE</h4>';
				str += '<h2>'+result[4]+'</h2>';
				str += '<h5>Identified</h5>';
				str += '</hgroup>';
				str += '</li>';
				str += '<li>';
				str += '<hgroup>';
				str += '<h4 style="font-size:14px;">INFLUENCE PEOPLE</h4>';
				str += '<h2>'+result[5]+'</h2>';
				str += '<h5>Identified</h5>';
				str += '</hgroup>';
				str += '</li>';
				str += '</ul>';
				str += '</div>';
				str += '</div>';
				str += '</div>';
				$('#'+buildDiv+'').html(str);
			}
			
		});
		
		
}
function onEachFeature(feature, layer) 
{
}


function getDataCollectorInfo(userId,userName,boothId,boothNo,date){
	
	$('#boothWiseTab,#startTimeTab').removeClass('selected');
	$('#callCenterTab').addClass('selected');
	showHideTabs('callCenterTab');
	getSurveyVotersList(userId,userName,boothId,boothNo,date);

}

function setConstituency(value){
webConstId = value;
}
function setUserTypeId(value){
webuserId = value;
}


function getBoothsDetailsByConstituencyId(constituencyId)
{

	$('#dayWiseReportDiv1').html('');
	if($('#constituencyIdForVerfication').val() == 0)
		return;
	

	$("#boothImage").show();
	var jObj =
	{
	  constituencyId:constituencyId     
	};

	 $.ajax({
			type:'GET',
			url: 'getBoothDetailsByConstituencyAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		  }).done(function(result){				
				//buildDayWiseReportByUserType(result);		
				$("#boothImage").hide();
				$('#boothIdForVerfication').find('option').remove();

				$.each(result,function(index,value){
					$('#boothIdForVerfication').append('<option value="'+value.boothId+'">Booth - '+value.partNo+'</option>');
				});
				
				$('#boothIdForVerfication').multiselect('refresh');

		});
	
	
	
}

function getDayWiseReportByConstituencyIdAndUserType()
{

$('#dayWiseReportDiv1,#retunMsg,#errorDiv').html('');
	var constituencyId = $("#constituencyIdForVerfication").val();
	//var userTypeId = $("#userType").val();
	var userTypeId = 1;
	var startDate = $("#fromDate").val();
	var endDate = $("#toDate").val();
	var heading = $( "#userType option:selected" ).text();

	var errorStr ="";

	if(constituencyId == 0)
		errorStr += 'Please select constituency<br>';


if(startDate.length == 0 || endDate.length == 0)
	{
		errorStr += 'Please Select From Date<br>';
	}
	if( endDate.length == 0)
	{
		errorStr += 'Please Select To Date<br>';
	}
	if(startDate.length > 0 && endDate.length > 0 )
	{		    
		  var dt1  = parseInt(startDate.substring(0,2),10);
		  var mon1 = parseInt(startDate.substring(3,5),10);
		  var yr1  = parseInt(startDate.substring(6,10),10);
		  var dt2  = parseInt(endDate.substring(0,2),10);
		  var mon2 = parseInt(endDate.substring(3,5),10);
		  var yr2  = parseInt(endDate.substring(6,10),10);
		  var date1 = new Date(yr1, mon1, dt1);
		  var date2 = new Date(yr2, mon2, dt2);

		if(date2 < date1)
		{ 
			errorStr += 'From Date should be Less Than To Datee';
		}
	}

	if(errorStr.length >0)
	{
        $('#errorDiv').html(errorStr);
		return;
	}

	
	var jObj =
	{
	  constituencyId:constituencyId,
      userTypeId:userTypeId,
	  startDate:startDate,
	  endDate:endDate,
	  heading:heading,
	  userIds : userIds,
      boothIds:[]
 	};
	
	$('#mainajaximg').show();

	 jObj.boothIds= $('#boothIdForVerfication').val();

	  $.ajax({
			type:'GET',
			url: 'getDayWiseReportByConstituencyIdAndUserType.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		  }).done(function(result){				
				buildDayWiseReportByUserType(result);				
		});
}
function buildDayWiseReportByUserType(result)
{
	$('#mainajaximg').hide();
	if(result == null || result.length == 0)
	{
		$('#retunMsg').html("<font color='red'>NO DATA AVILABLE</font>");
		return;
	}

	

	 var str = '';

   str+='<table class="table table-bordered m_top20 table-hover table-striped username" id="daywisereportTableId">';
    str+='<thead class="alert alert-success">';
	 str+='<tr>';
	  str+='<th>UserName</th>';
  	  str+='<th>Booth No</th>';
	  str+='<th>Total Voters</th>';

	     if($('#fromDate').val()  == $('#toDate').val())
		 {
		     str+='<th>Collected Count</th>';
			 str+='<th>Percent</th>';

		 }else
		 {
			$.each(result[0].subList,function(index,value){
			  str+='<th>'+value.surveyDate+'</th>';
			});
		 }

	 str+='</tr>';
	str+='</thead>';

   str+='<tbody>'
   $.each(result,function(index,value){
    str+='<tr>';
	   str+='<td>'+value.userName+'</td>';
   	   str+='<td>'+value.partNo+'</td>';
	   str+='<td>'+value.totalVoters+'</td>';

	   	if($('#fromDate').val()  != $('#toDate').val())
	      $.each(value.subList,function(index1,value1){
			   str+='<td>'+value1.count+'</td>';
		  });
	    else
		  $.each(value.subList,function(index1,value1){
			   str+='<td>'+value1.count+'</td>';
			   str+='<td>'+value1.percent+'</td>';
		  });

    str+='</tr>';
   });
   str+='</tbody>';
   str+='</table>';

 $('#dayWiseReportDiv1').html(str);

	if($('#fromDate').val()  == $('#toDate').val())
		 $('#daywisereportTableId').dataTable();

}

function getReportForConstituency(){
	var constituencyId = $('#reportConstituencyId').val();
	$('#errDivIdForReport').html('');
	$('#basicCountDiv').html('');
	 $("#panchayatsStatusDiv").html('');
	if(constituencyId == 0){
		$('#errDivIdForReport').html('Please Select Constituency.');
		return;
	}
	
	var jsObj = 
	{
		constituencyId:constituencyId,
		task : "getSurveyStatusBoothData"
	}
	$("#reportDataImg").show();

	$.ajax({
		type:'GET',
		url: 'getSurveyStatusBoothListAction.action',
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		}).done(function(result){
				$("#reportDataImg").hide();
				if(result != null && result.genericVOList != null && result.genericVOList.length > 0 ){
					buildReportData(result.genericVOList);
					getPanchayatsStatusCount(constituencyId);
				}
				else{
					$('#basicCountDiv').html(' No Data Available ... ');
				}
		});
		
}

function buildReportData(result){

$('#basicStatusReport').html('');

var str ='';
	

				str += '<div class="row-fluid">';
				str += '<div class="span12 booths-Overview-widget">';
				str += '<div class="row-fluid">';
				str += '<div class="row-fluid">';
				str += '<h5 class="text-center label"> Booth Wise Survey Details   </h5>';
				str += '</div>';
				str += '<ul class="inline unstyled booths-Overview-widget-nav">';
				
			   for(var i in result){
			 
					str += '<li>';
					str += '<hgroup>';
					str += '<h4>'+result[i].name+'</h4>';
					str += '<h2> <a href="javascript:{getSurveyBoothDetails(\''+result[i].desc+'\','+result[i].id+');}">'+result[i].count+'</a></h2>';
					str += '</hgroup>';
					str += '</li>';	
				}	
				
				str += '</ul>';
				str += '</div>';
				str += '</div>';
				str += '</div> ';
				
				
	$('#basicStatusReport').html(str);

		
}

	
function getSurveyBoothDetails(boothDtls,statusId)
{

var boothArr = new Array();

var boothInfo = boothDtls.split(',');

	if(boothInfo != null && boothInfo.length>0){

		for(var i in boothInfo){
			if(boothInfo[i].trim().length > 0)
				boothArr.push(boothInfo[i]);
		}
	}
	console.log(boothArr);

	$('#tableDtailsDiv').html('');
	var jsObj =
	{
		boothIds:boothArr
	}
	$.ajax({
	type:'GET',
	url: 'getSurveyBoothDetailsAction.action',
	dataType: 'json',
	data: {task:JSON.stringify(jsObj)},
	}).done(function(result){
		buildSurveyBoothDetailsTable(result,statusId);
	});
	
}

function buildSurveyBoothDetailsTable(result,statusId)
{

	if(result != null && result.length > 0)
			{	
				var str = '';
			
				str += '<table class="table table-bordered" id="reportTab">';
				str += '<thead>';
				str += '<tr>	';									  
				str += ' <th>Booth NO</th>';
				str += '<th>Total Voters</th>	';	
				str += ' <th>Mobile Numbers Collected</th>';
				str += '<th>Caste Collected</th>	';
				str += ' <th>Hamlets Collected</th>';
				str += ' <th></th>';
						
				str += '</tr>';
				str += ' </thead>';
				str += '<tbody>	';

				console.log(statusId);				
				
				for(var i in result)
				{
					str += '<tr>		';								  
					str += '<td>'+result[i].partNo+'</td>';
					str += '<td>'+result[i].totalVoters+'</td>';
					str += '<td>'+result[i].mobileNoCount+'</td>';
					str += '<td>'+result[i].casteCount+'</td>';
					str += '<td>'+result[i].hamletCount+'</td>';
					if(statusId == 1){
							str += '<td> <button> Completed </button> <br> <button> WM Completed </button></td>';
					}
					else if(statusId == 2){
							str += '<td> <button> Prcessing </button> <br> <button> WM Completed </button></td>';
					}
					else if(statusId == 3){
							str += '<td> <button> Prcessing </button> <br> <button> Completed </button></td>';
					}
					
					str += '</tr>	';
									
				}
				
				str += '</tbody>';
				str += '</table>';
				
				$('#tableDtailsDiv').html(str);
				$('#reportTab').dataTable({});
			}			
		
}
function getPanchayatsStatusCount(constituencyId)
{
   
	var jObj =
	{
	  constituencyId:constituencyId     
	};

	 $.ajax({
			type:'GET',
			url: 'getPanchayatsStatusCountAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		  }).done(function(result){				
			buildStatusForPanchayats(result,constituencyId);	
		});
	
	
}
function getPanchayatDetails(status,constituencyId)
{
var jObj =
	{
	  constituencyId:constituencyId ,
	  status:status
	};

	 $.ajax({
			type:'GET',
			url: 'getPanchayatsStatusDetailsAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		  }).done(function(result){				
			buildStatusForPanchayatDetials(result);	
		});
}
function buildStatusForPanchayats(result,constituencyId)
{

	var str='';
	str+='<div class="span12 booths-Overview-widget">';
	str+='<div class="row-fluid"></div>';
	str+='<div class="row-fluid">';
	str+='<ul class="inline unstyled booths-Overview-widget-nav">';
	str+='<li><hgroup><h4>PANCHAYAT</h4><h2 id="panchayatreadyID"><a onclick="getPanchayatDetails(\'completed\',\''+constituencyId+'\')">'+result.panchayatCount+'</a></h2><h5>Ready</h5></hgroup>';
	str+='</li>';
	str+='<li><hgroup><h4>PANCHAYAT</h4><h2 id="panchayatNotReady"><a onclick="getPanchayatDetails(\'notcompleted\',\''+constituencyId+'\')">'+result.panchayatNotCompleteCount+'</a></h2><h5>Not Ready</h5></hgroup>';
	str+='</li></ul></div></div></div>';
	$("#panchayatsStatusDiv").html(str);
}

function buildStatusForPanchayatDetials(result)
{
	var str='';
	if(result.length == 0)
	{
	
	$("#panchayatDetailsDiv").html('<font color="red">No Data Available...</font>').css("text-align","center");
	return;
	}

		str+='<table class="table table-bordered" id="panchayatStatusTable">';
		str+='<thead>';
		str+='<th>Panchayat Name</th>';
		str+='<th>Total Voters</th>';
		str+='<th>Mobile Collected</th>';
		str+='<th>Caste Collected</th>';
		str+='<th>Hamlet Collected</th>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result)
		{
			str+='<tr>';
			str+='<td id='+result[i].id+'>'+result[i].name+'</td>';
			str+='<td>'+result[i].totalVoters+'</td>';
			str+='<td>'+result[i].mobileNoCount+'</td>';
			str+='<td>'+result[i].casteCount+'</td>';
			str+='<td>'+result[i].hamletCount+'</td>';
			str+='</tr>';
		}
		str+='</tbody>';
		str+='</table>';
	$("#panchayatDetailsDiv").html(str);
	$("#panchayatStatusTable").dataTable();
	
}