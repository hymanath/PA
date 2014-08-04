var fieldDataflg = false;
var fieldReportflg = false;

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
		$('#userReport').hide();
		$('#saveBoothsPercentage').hide();
		$("#verifierReportIdForVerifier").hide();;
		$("#wmReportDiv").hide();
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
		$('#userReport').hide();
		$('#saveBoothsPercentage').hide();
		$("#verifierReportIdForVerifier").hide();;
		$("#wmReportDiv").hide();
		
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
		$('#userReport').hide();
		$('#saveBoothsPercentage').hide();
		$("#verifierReportIdForVerifier").hide();;
		$("#wmReportDiv").hide();
		if(fieldReportflg == false)
		getFieldReportSummary();
		fieldReportflg = true;
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
		$('#userReport').hide();
		$('#saveBoothsPercentage').hide();
		$("#verifierReportIdForVerifier").hide();;
		$("#wmReportDiv").hide();
		if(fieldDataflg == false)
		getDataReportSummary();
		fieldDataflg = true;


	}
	else if(id == "surveyStatusRprtTab"){

		$('#statusReportDiv').show();
		$('#callCenter').hide();
		$('#startTime').hide();
		$('#boothWise').hide();
		$('#dataCollector').hide();
        $('#inActiveUsersDetails').hide();	
		$('#completeBooths').hide();
		$('#userReport').hide();
		$('#saveBoothsPercentage').hide();
		$("#verifierReportIdForVerifier").hide();;
		$("#wmReportDiv").hide();
	}
	else if (id == "surveyUserWise")
	{
		$('#statusReportDiv').hide();
		$('#callCenter').hide();
		$('#startTime').hide();
		$('#boothWise').hide();
		$('#dataCollector').hide();
        $('#inActiveUsersDetails').hide();	
		$('#completeBooths').hide();
		$('#userReport').show();
		$('#saveBoothsPercentage').hide();
		$("#verifierReportIdForVerifier").hide();;
		$("#wmReportDiv").hide();
	}
	else if (id == "saveBoothPercentagesTab")
	{
		$('#statusReportDiv').hide();
		$('#callCenter').hide();
		$('#startTime').hide();
		$('#boothWise').hide();
		$('#dataCollector').hide();
        $('#inActiveUsersDetails').hide();	
		$('#completeBooths').hide();
		$('#userReport').hide();
		$('#saveBoothsPercentage').show();
		$("#verifierReportIdForVerifier").hide();;
		$("#wmReportDiv").hide();
	}
	else if (id == "wmReportTab")
	{
		$('#statusReportDiv').hide();
		$('#callCenter').hide();
		$('#startTime').hide();
		$('#boothWise').hide();
		$('#dataCollector').hide();
        $('#inActiveUsersDetails').hide();	
		$('#completeBooths').hide();
		$('#userReport').hide();
		$('#saveBoothsPercentage').hide();
		$("#wmReportDiv").show();
		$("#verifierReportIdForVerifier").hide();
	}
	else if (id == "verifierReportTab")
	{
		$('#statusReportDiv').hide();
		$('#callCenter').hide();
		$('#startTime').hide();
		$('#boothWise').hide();
		$('#dataCollector').hide();
        $('#inActiveUsersDetails').hide();	
		$('#completeBooths').hide();
		$('#userReport').hide();
		$('#saveBoothsPercentage').hide();
		$("#verifierReportIdForVerifier").show();;
		$("#wmReportDiv").hide();
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
		$('#userReport').hide();
		$('#saveBoothsPercentage').hide();

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

var newCasteArr = new Array();
function getSurveyVotersList(userId,userName,mobileNo,leaderName,leaderMobile,boothId,boothNo,date,userType,searchType){

$('#voterInfoDIv').html('');

if($('#userTypeId').val() == 4 )
{
	getVerifierDetails(userId,boothId);
}

	if(searchType == 1)
		{
			$('#userInfoDiv1').hide();
			$('#userInfoDiv2').hide();
			$('#userInfoDiv3').hide();
			$('#userInfoDiv4').hide();
			$('#userInfoDiv5').hide();
			$('#userInfoDiv6').hide();
			$('#userInfoDiv7').hide();
			$('#casteInfoDiv').hide();
			$('#collecedCountDiv').hide();
			$('#verifiedCountDiv').hide();
			$('#updatedCountDiv').hide();
			$('#matchedCountDiv').hide();
			$('#unMatchedCountDiv').hide();
			$('#notVerifiedCountDiv').hide();
			$('#casteInfoDiv').html('');
		}

var surveyDate = $('#FielddateId').val();
$('#searchDataImg').show();
		$('#webMonitoringImage').show();
		var jsObj = 
		{
			constituencyId:webConstId,
			surveyUserId:userId,
			boothId : boothId,
			searchDate:date,
			userType : $('#userTypeId').val(),
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
				str +='<th> <input type="checkbox" id="alluserChkbx" class="allCheckbxCls"  onclick="selectAllCheckBoxes();"/> Select All </th>';
				str +='<th> S.NO </th>';
				str +='<th>H.NO</th>';
				str +='<th> Name </th>';
				str +='<th> Gaurdian Name </th>';
				if(userType == 4)
				{
					str +='<th>DC CASTE</th>';
					str +='<th>DC MOBILE</th>';
					str +='<th>WM CASTE</th>';
					str +='<th>MATCHED STATUS</th>';
				}
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
					str +='<td> <input type="checkbox" id="userChkbx" value="'+result[i].voterId+'"class="checkbxCls" /></td>';					
					str +='<td>'+result[i].serailNo+'</td>';
					str +='<td>'+result[i].partNo+'</td>';
					str +='<td>'+result[i].userName+'</td>';
					str +='<td>'+result[i].voterName+'</td>';
					if(userType == 4)
					{
							str +='<td>';
							str +='<div >';
							str +='<ul class="unstyled inline">';
							str +='<li>'+result[i].dcCaste+'</li>';					
							str +='</ul>';
							str +='</div>';
							str +='</td>';
							
							str +='<td>';
							str +='<div>';
							str +='<ul class="unstyled inline">';
							if(result[i].mobileNumber != null)
							str +='<li>'+result[i].mobileNumber+'</li>';
							else
							str +='<li></li>';
							str +='</ul>';
							str +='</div>';
							str +='</td>';
							
							str +='<td>';
							str +='<div>';
							str +='<ul class="unstyled inline">';
							str +='<li>'+result[i].wmCaste+'</li>';					
							str +='</ul>';
							str +='</div>';
							str +='</td>';
							
							str +='<td>';
							str +='<div>';
							str +='<ul class="unstyled inline">';
							str +='<li>'+result[i].status+'</li>';					
							str +='</ul>';
							str +='</div>';
							str +='</td>';
							
							
						
					}
						
					/* starting  caste available and not available details */
					
					if(result[i].caste.trim().length >0)
					{
						str +='<td>'+result[i].caste+'';
					
					
				
						if(result[i].casteMatchedCount == 1) //correct
						{ 
							str +='<div data-toggle="buttons-radio" class="btn-group">';
							str +='<button class="btn btn-mini  btn-success " id="updateBtnId'+i+'" type="button" onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',1,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">correct</button>';
							str +='<button class="btn btn-mini " id="updateBtnIdA'+i+'" type="button" onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',0,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >wrong</button>';
							str +='<div id="casteErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
							
							str +='<input type="hidden" value="1" id="isCasteMatched'+i+'"/>';
							
							str +='</div>';
							str +='</td>';
							
							str +='<td> <select id="casteListId'+i+'"  disabled="disabled" style="width:165px;" onchange="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',10,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');"><option value="0"> Select Caste </option> ';
							str +='</select></td>';	
												
						}
						else if(result[i].casteMatchedCount == 2)
						{
							str +='	<div data-toggle="buttons-radio" class="btn-group">';
							
							str +='	<button class="btn btn-mini " id="updateBtnId'+i+'" type="button"  onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',1,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >correct</button>';
							
							str +='	<button class="btn btn-mini  btn-warning " id="updateBtnIdA'+i+'" type="button"  onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',0,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">wrong</button>';
							
							str +='<div id="casteErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
							str +='<input type="hidden" value="0" id="isCasteMatched'+i+'"/>';
							str +='</div>';
							str +='</td>';
							
							str +='<td> <select id="casteListId'+i+'"   style="width:165px;" onchange="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',10,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');"><option value="0"> Select Caste </option> ';
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
							
							str +='<button class="btn btn-mini " id="updateBtnId'+i+'" type="button" onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',1,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >correct</button>';
							
							str +='<button class="btn btn-mini " id="updateBtnIdA'+i+'" type="button" onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',0,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >wrong</button>';
							
							str +='<div id="casteErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
							str +='<input type="hidden" value="" id="isCasteMatched'+i+'"/>';
							str +='</div>';
							str +='</td>';
							
							str +='<td> <select id="casteListId'+i+'"  disabled="disabled" style="width:165px;" onchange="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',10,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');"><option value="0"  > Select Caste </option> ';
							str +='</select></td>';	
							
							
						}			
					}
					else{
						str +='<td>';
						str +='<input type="hidden" value="" id="isCasteMatched'+i+'"/>';
						str +='</td>';
						
							str +='<td> <select id="casteListId'+i+'"   style="width:165px;" onchange="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',10,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');"><option value="0"> Select Caste </option> ';
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
											str +='<button class="btn btn-mini  btn-success " id="updateMBtnId'+i+'" type="button" onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',2,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >correct </button>';
											
											str +='<button class="btn btn-mini "  id="updateMBtnIdA'+i+'" type="button" onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',3,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">wrong</button>';
											
											str +='<div id="mobileErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
											str +='<input type="hidden" value="2" id="isTestedMobile'+i+'"/>';
											str +='</div>';
														
										}
										else if(result[i].mobileMatchedCount == 2)
										{

											str +='<button class="btn btn-mini "  id="updateMBtnId'+i+'" type="button" onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',2,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">correct </button>';
											
											str +='<button class="btn btn-mini  btn-warning " id="updateMBtnIdA'+i+'" type="button"  onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',3,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">wrong</button>';
											
											str +='<div id="mobileErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
											str +='<input type="hidden" value="3" id="isTestedMobile'+i+'"/>';
											str +='</div>';
														
										}
										else
										{	

											str +='<button class="btn btn-mini "  type="button" id="updateMBtnId'+i+'" onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',2,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >correct </button>';
										
											str +='<button class="btn btn-mini "  id="updateMBtnIdA'+i+'" type="button" onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',3,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">wrong</button>';
										
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
				}
				str +='</tbody>';
				str +='</table>';
				
			str +='<div id="scrollBtnDiv" >';
			str +='<a id="updateBtnInNewWndow" style="position: fixed; left :0px; top: 320px;" href="javascript:{updateVoterDetlsStatus('+userId+','+boothId+',0,'+webConstId+','+date+','+userType+');}" class="btn btn-primary"> Update Details </a>';
			str +='</div>';				
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
		if(searchType == 1)
		{
				var str1='';
				var str2='';
				
				$('#userInfoDiv1').show();
				$('#userInfoDiv2').show();
				$('#userInfoDiv5').show();
				$('#userInfoDiv6').show();
				str2 +=' <span style="font-weight:bold;"> DC Name  </span> : '+userName+'</br>';		
				$('#userInfoDiv1').html(str2);
				
				str2 ='';
				str2 +=' <span style="font-weight:bold;"> Booth No </span> : '+boothNo+' </br>';
				
				$('#userInfoDiv5').html(str2);
				
				str2 ='';
				str2 +=' <span style="font-weight:bold;"> Total Voters </span> : '+totalVoters+' </br>';
				$('#userInfoDiv6').html(str2);

				str2 ='';
				str2 +=' <span style="font-weight:bold;"> Mobile Number </span> : '+mobileNo+' </br>';
				$('#userInfoDiv2').html(str2);
				str2 ='';
				if(leaderName != null)
				{
				$('#userInfoDiv3').show();
				str2 +=' <span style="font-weight:bold;">Leader Name</span> : '+leaderName+' </br>';
				$('#userInfoDiv3').html(str2);
				}
				str2 ='';
				if(leaderMobile != null)
				{
				$('#userInfoDiv4').show();	
				str2 +=' <span style="font-weight:bold;">Mobile Number</span> : '+leaderMobile+' </br>';
				$('#userInfoDiv4').html(str2);
				}
				
				var collectdVoters = results[0].subList.length;
				var remainingCount = totalVoters - collectdVoters;
				if( remainingCount!= 0)
				{
					$('#userInfoDiv7').show();
					str2 ='';
					str2 +=' <span style="font-weight:bold;"> Not Collected Voters </span> : '+remainingCount+' </br>';
					$('#userInfoDiv7').html(str2);
				}
				
				if(results != null && results[0].genericVOList.length>0)
				{
						$('#casteInfoDiv').show();
						str1 +='<b> Caste Details </b> : <br> ';
						var length = results[0].genericVOList.length;
						
						str1 +='<a style="color:#008000;" href="javascript:{getSurveyVotersList('+userId+',\''+userName+'\','+mobileNo+',\''+leaderName+'\','+leaderMobile+','+boothId+','+boothNo+',\''+date+'\','+userType+',2);}">All</a>, ';
						
						for(var k in results[0].genericVOList){
						var perc = (results[0].genericVOList[k].count * 100 ) / totalVoters;
							str1 +=' <a href="javascript:{getCasteWiseVotersListByCaste('+results[0].genericVOList[k].id+','+webConstId+','+userId+','+boothId+',\''+date+'\','+userType+');}">'+results[0].genericVOList[k].name+' ('+parseFloat(perc).toFixed(2)+') </a> ';
							
							if(k < length-1){
								str1 +=', ';
							}					
						}
				}

				  $('#casteInfoDiv').html(str1);
				
					if(results[1] != null  && results[1].genericVOList.length > 0){
						for(var k in results[1].genericVOList){
							var objec = {
								id:results[1].genericVOList[k].id,
								value : results[1].genericVOList[k].name
							}								
							newCasteArr.push(objec);
						}
					}
			}
			//console.log(newCasteArr);
		});


}

function updateDetails(casteId,mobileId, value,voterId,surveyUserId,selectionType,userId,boothId,date,newCasteId,updateBtnId,userTYpe)
{
	var casteMatched = $('#'+casteId+'').val();
	var mobileMached = $('#'+mobileId+'').val();
	console.log($('#userTypeId').val());
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



updateStatusDetails(casteId,mobileId,voterId,surveyUserId,selectionType,userId,boothId,date,newCasteId,updateBtnId,userTYpe);
}

function updateStatusDetails(isCasteMatched,mobileMatched,voterId,surveyUserId,selectionType,userId,boothId,date,newCasteId,updateBtnId,userType)
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
//	console.log("isMatched   :"+isMatched );
	var casteId = 0;

	if(newCasteId != 0){
		casteId = $('#'+newCasteId+'').val();
	//	console.log(casteId);
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
		casteId:casteId,
		isHamletMatched:0,
		hamletId:0,
		userType : userType
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
						
						if(newCasteArr.length >0){
							for(var k in newCasteArr){
								$('#'+newCasteId+'').append('<option value="'+newCasteArr[k].id+'">'+newCasteArr[k].value+'</option>');
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
var toDate = $("#FieldTodateId").val();
$("#basicCountDiv").html('');
$("#errorMsgDiv").html('');
$("#excelTableID").css("display","none");
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
	$("#reportDataImg").show();
	var jsObj =
	{
	    constituencyId : constituencyID,
		userTypeId:userTypeId,
		date:date,
		toDate:toDate,
		userIds : userIds,
		task : "getLocationCount"
	}
	$.ajax({
	type:'GET',
	url: 'getSurveyUserLoctionCount.action',
	dataType: 'json',
	data: {task:JSON.stringify(jsObj)},
	}).done(function(result){
		$("#basicCountDiv").show();

		$("#reportDataImg").hide();
		//$("#SurveyUsertable").show();
     buildSurveyUserStatusCount(result,userTypeId);
	
	});
}

var webConstId =0;
var webuserId =0;
var webBoothId =0;
 function buildSurveyUserStatusCount(result,userTypeId)
{
	var userType = userTypeId;
	$("#excelTableID").css("display","inline-block");
	var str ='';
	if(result.length == 0)
	{
str+='<font style="color:red;">No Data avilable</font>';
	$("#basicCountDiv").html(str).css("text-align","center");
return;
	}
	str +="<h5>For Mobile No's </h5>"
	str +="<div style='background:red;padding:10px;width:2px;float:left;'></div>  <span style='float:left;'>&nbsp;&nbsp; 0 - 9 &nbsp;&nbsp;</span>";
	str +="<div style='background:yellow;padding:10px;width:2px;float:left;'></div>  <span style='float:left;'>&nbsp;&nbsp; 10 - 25 &nbsp;&nbsp;</span>";
	str +="<div style='background:lightgreen;padding:10px;width:2px;float:left;'></div>  <span style='float:left;'>&nbsp;&nbsp; 26 - 40 &nbsp;&nbsp;</span>";
	str +="<div style='background:green;padding:10px;width:2px;float:left;'></div>  <span style='float:left;'>&nbsp;&nbsp; 40 Above &nbsp;&nbsp;</span><br>";
	
	str +="<h5>For Castes Error & Mobile Error </h5>"
	str +="<div style='background:red;padding:10px;width:2px;float:left;'></div>  <span style='float:left;'>&nbsp;&nbsp; 2.01 Above &nbsp;&nbsp;</span>";
	str +="<div style='background:yellow;padding:10px;width:2px;float:left;'></div>  <span style='float:left;'>&nbsp;&nbsp; 1.01 - 2.00  &nbsp;&nbsp;</span>";
	str +="<div style='background:lightgreen;padding:10px;width:2px;float:left;'></div>  <span style='float:left;'>&nbsp;&nbsp;0.51 - 1.00  &nbsp;&nbsp;</span>";
	str +="<div style='background:green;padding:10px;width:2px;float:left;'></div>  <span style='float:left;'>&nbsp;&nbsp; 0.0 - 0.50 &nbsp;&nbsp;</span>";
	
	
	str+='<table class=" table table-bordered m_top20" id="SurveyUsertable">';
	str+='<thead>';

	str+='<tr class="alert alert-success">';
	if(userType ==1)
	str+='<th>DCName</th>';
	else if (userType ==4)
	str+='<th>DVName</th>';
	str+='<th>Leader Name</th>';
	str+='<th>Booth</th>';
	str+='<th> Total Voters</th>';
//	str+='<th style="text-align : center;">Data Collector</th>';
	//str+='<th style="text-align : center;">Web monitoring</th>';
	if(userType ==1)
	{
    str+='<th>DC Caste Mapped</th>';
	str+='<th>DC Hamlet Mapped</th>';
	str+='<th>DC Mobile Collected</th>';
	}
else if(userType ==4)
	{
    str+='<th>DV Caste Mapped</th>';
	str+='<th>DV Hamlet Mapped</th>';
	str+='<th>DV Mobile Collected</th>';
	}
	str+='<th>TOTAL </th>';
	str+='<th>Mobile MATCHED</th>';
	str+='<th>Mobile UN MATCHED</th>';
	str+='<th>MOBILE ERROR %</th>';
	str+='<th>CASTE MATCHED</th>';
	str+='<th>CASTE UN MATCHED</th>';
	str+='<th>CASTE ERROR %</th>';
	str+='</tr>';
	str+='</thead>';
	str+='<tbody>';
	for(var i in result)
	{

		for(var j=0;j<result[i].subList.length;j++)
		{
			webBoothId = result[i].subList[j].boothId;
			str+='<tr>';
			if(result[i].verifier != null)
			{
			str+='<td> <a href="javascript:{getDataCollectorInfo('+result[i].userid+',\''+result[i].userName+'\','+result[i].mobileNo+',\''+result[i].verifier.name+'\','+result[i].verifier.verified+','+result[i].subList[j].boothId+','+result[i].subList[j].partNo+',\''+$('#FielddateId').val()+'\','+userType+',1);}">'+result[i].userName+' </a><br>'+result[i].mobileNo+'</td>';
			str+='<td> '+result[i].verifier.name+'<br>'+result[i].verifier.verified+'</td>';
			}
			else
			{
			str+='<td> <a href="javascript:{getDataCollectorInfo('+result[i].userid+',\''+result[i].userName+'\','+result[i].mobileNo+',null,null,'+result[i].subList[j].boothId+','+result[i].subList[j].partNo+',\''+$('#FielddateId').val()+'\','+userType+',1);}">'+result[i].userName+' </a><br>'+result[i].mobileNo+'</td>';
			str+='<td>-</td>';
			}
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
			if(result[i].subList[j].mobileNoCount>=0 && result[i].subList[j].mobileNoCount<=9){
				str+='<td class="errorRed">'+result[i].subList[j].mobileNoCount+'</td>';
			}else if(result[i].subList[j].mobileNoCount>=10 && result[i].subList[j].mobileNoCount<=25){
				str+='<td class="errorYellow">'+result[i].subList[j].mobileNoCount+'</td>';
			}else if(result[i].subList[j].mobileNoCount>=26 && result[i].subList[j].mobileNoCount<=40){
				str+='<td class="errorLgreen">'+result[i].subList[j].mobileNoCount+'</td>';
			}else if(result[i].subList[j].mobileNoCount>=41){
				str+='<td class="errorGreen">'+result[i].subList[j].mobileNoCount+'</td>';
			}else{
				str+='<td>'+result[i].subList[j].mobileNoCount+'</td>';
			}
			str+='<td>'+result[i].subList[j].count+'</td>';
			str+='<td>'+result[i].subList[j].mobileMatchedCount+'</td>';
			str+='<td>'+result[i].subList[j].mobileNotMatchedCount+'</td>';
			var Mobiletotal = result[i].subList[j].mobileMatchedCount + result[i].subList[j].mobileNotMatchedCount;
			if(Mobiletotal >0){
			
			var mobErrorPercent = (Math.round(result[i].subList[j].mobileNotMatchedCount * 100)/Mobiletotal).toFixed(2);
			if(mobErrorPercent>=0.0 && mobErrorPercent<=0.50){
				str+='<td class="errorGreen">'+mobErrorPercent+'</td>';
			}else if(mobErrorPercent>=0.51 && mobErrorPercent<=1.00){
				str+='<td class="errorLgreen">'+mobErrorPercent+'</td>';
			}else if(mobErrorPercent>=1.01 && mobErrorPercent<=2.00){
				str+='<td class="errorYellow">'+mobErrorPercent+'</td>';
			}else if(mobErrorPercent>=2.01){
				str+='<td class="errorRed">'+mobErrorPercent+'</td>';
			}else{
				str+='<td>'+mobErrorPercent+'</td>';
			}
			
			//str+='<td>'+(Math.round(result[i].subList[j].mobileNotMatchedCount * 100)/Mobiletotal).toFixed(2)+'%</td>';
			}
			else{
			str+='<td> - </td>';
			}
			str+='<td>'+result[i].subList[j].casteMatchedCount+'</td>';
			str+='<td>'+result[i].subList[j].casteNotMatchedCount+'</td>';
			var Castetotal = result[i].subList[j].casteMatchedCount + result[i].subList[j].casteNotMatchedCount;
			if(Castetotal >0){		

			var casteErrorPercent = (Math.round(result[i].subList[j].casteNotMatchedCount * 100)/Castetotal).toFixed(2);
			if(casteErrorPercent>=0.0 && casteErrorPercent<=0.50){
				str+='<td class="errorGreen">'+casteErrorPercent+'</td>';
			}else if(casteErrorPercent>=0.51 && casteErrorPercent<=1.00){
				str+='<td class="errorLgreen">'+casteErrorPercent+'</td>';
			}else if(casteErrorPercent>=1.01 && casteErrorPercent<=2.00){
				str+='<td class="errorYellow">'+casteErrorPercent+'</td>';
			}else if(casteErrorPercent>=2.01){
				str+='<td class="errorRed">'+casteErrorPercent+'</td>';
			}else{
				str+='<td>'+casteErrorPercent+'</td>';
			}
			
			//str+='<td>'+(Math.round(result[i].subList[j].casteNotMatchedCount * 100)/Castetotal).toFixed(2)+'%</td>';
			}
			else{
			str+='<td> - </td>';
			}
			str+='</tr>';
		}
	}
	str+='</tbody>';
	str+='</table>';
	

	/* for export to excel */
	str+='<table class=" table table-bordered m_top20" id="SurveyUsertable1" style="display:none;">';
	str+='<thead >';

	str+='<tr class="alert alert-success">'
	str+='<th>DCName</th>';
	str+='<th>DC Mobile NO</th>';
	str+='<th>Leader Name</th>';
	str+='<th>Leader Mobile No</th>';
	str+='<th >Booth</th>';
	str+='<th> Total Voters</th>';
	

    str+='<th >Caste Mapped</th>';
	str+='<th >Caste Mapped %</th>';
	str+='<th >Hamlet Mapped</th>';
	str+='<th >Mobile Collected</th>';
	
	str+='<th>WM TOTAL</th>';
	str+='<th>WM Mobile MATCHED</th>';
	str+='<th>WM Mobile UN MATCHED</th>';
	str+='<th>WM MOBILE ERROR %</th>';
	str+='<th>WM CASTE MATCHED</th>';
	str+='<th>WM CASTE MATCHED %</th>';
	str+='<th>WM CASTE UN MATCHED</th>';
	str+='<th>WM CASTE ERROR %</th>';
	str+='</tr>';
	str+='</thead>';
	str+='<tbody>';
	for(var i in result)
	{

		for(var j=0;j<result[i].subList.length;j++)
		{
			webBoothId = result[i].subList[j].boothId;
			str+='<tr>';
			str+='<td> '+result[i].userName+'</td>'; 
			str+='<td> '+result[i].mobileNo+'</td>';
			if(result[i].verifier != null)
			{
			
			str+='<td> '+result[i].verifier.name+'</td>';
			str+='<td> '+result[i].verifier.verified+'</td>';
			}
			else
			{
			str+='<td>-</td>'; 
			str+='<td>-</td>';
			}
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
			var casteMappedperc = (Math.round(result[i].subList[j].casteCount * 100)/result[i].subList[j].totalVoters).toFixed(2);
			str+='<td>'+casteMappedperc+'</td>';
			str+='<td>'+result[i].subList[j].hamletCount+'</td>';
			if(result[i].subList[j].mobileNoCount>=0 && result[i].subList[j].mobileNoCount<=9){
				str+='<td class="errorRed">'+result[i].subList[j].mobileNoCount+'</td>';
			}else if(result[i].subList[j].mobileNoCount>=10 && result[i].subList[j].mobileNoCount<=25){
				str+='<td class="errorYellow">'+result[i].subList[j].mobileNoCount+'</td>';
			}else if(result[i].subList[j].mobileNoCount>=26 && result[i].subList[j].mobileNoCount<=40){
				str+='<td class="errorYellow">'+result[i].subList[j].mobileNoCount+'</td>';
			}else if(result[i].subList[j].mobileNoCount>=41){
				str+='<td class="errorYellow">'+result[i].subList[j].mobileNoCount+'</td>';
			}else{
				str+='<td>'+result[i].subList[j].mobileNoCount+'</td>';
			}
		    str+='<td>'+result[i].subList[j].count+'</td>';
			str+='<td>'+result[i].subList[j].mobileMatchedCount+'</td>';
			str+='<td>'+result[i].subList[j].mobileNotMatchedCount+'</td>';
			var Mobiletotal = result[i].subList[j].mobileMatchedCount + result[i].subList[j].mobileNotMatchedCount;
			if(Mobiletotal >0){
			
			var mobErrorPercent = (Math.round(result[i].subList[j].mobileNotMatchedCount * 100)/Mobiletotal).toFixed(2);
			if(mobErrorPercent>=0.0 && mobErrorPercent<=0.50){
				str+='<td class="errorGreen">'+mobErrorPercent+'</td>';
			}else if(mobErrorPercent>=0.51 && mobErrorPercent<=1.00){
				str+='<td class="errorLgreen">'+mobErrorPercent+'</td>';
			}else if(mobErrorPercent>=1.01 && mobErrorPercent<=2.00){
				str+='<td class="errorYellow">'+mobErrorPercent+'</td>';
			}else if(mobErrorPercent>=2.01){
				str+='<td class="errorRed">'+mobErrorPercent+'</td>';
			}else{
				str+='<td>'+mobErrorPercent+'</td>';
			}
			
			//str+='<td>'+(Math.round(result[i].subList[j].mobileNotMatchedCount * 100)/Mobiletotal).toFixed(2)+'%</td>';
			}
			else{
			str+='<td> - </td>';
			}
			str+='<td>'+result[i].subList[j].casteMatchedCount+'</td>';
			var wmcasteMappedperc = (Math.round(result[i].subList[j].casteMatchedCount * 100)/result[i].subList[j].casteCount).toFixed(2);
			str+='<td>'+wmcasteMappedperc+'</td>';
			str+='<td>'+result[i].subList[j].casteNotMatchedCount+'</td>';
			
			var Castetotal = result[i].subList[j].casteMatchedCount + result[i].subList[j].casteNotMatchedCount;
			if(Castetotal >0){
			var casteErrorPercent = (Math.round(result[i].subList[j].casteNotMatchedCount * 100)/Castetotal).toFixed(2);
			
			if(casteErrorPercent>=0.0 && casteErrorPercent<=0.50){
				str+='<td class="errorGreen">'+casteErrorPercent+'</td>';
			}else if(casteErrorPercent>=0.51 && casteErrorPercent<=1.00){
				str+='<td class="errorLgreen">'+casteErrorPercent+'</td>';
			}else if(casteErrorPercent>=1.01 && casteErrorPercent<=2.00){
				str+='<td class="errorYellow">'+casteErrorPercent+'</td>';
			}else if(casteErrorPercent>=2.01){
				str+='<td class="errorRed">'+casteErrorPercent+'</td>';
			}else{
				str+='<td>'+casteErrorPercent+'</td>';
			}
			
			//str+='<td>'+(Math.round(result[i].subList[j].casteNotMatchedCount * 100)/Castetotal).toFixed(2)+'%</td>';
			}
			else{
			str+='<td> - </td>';
			}
			str+='</tr>';
		}
	}
	str+='</tbody>';
	str+='</table>';
	
	$("#basicCountDiv").html(str);

$("#SurveyUsertable").dataTable({
				"iDisplayLength": 100,
				"aLengthMenu": [[100, 200, 500, -1], [100, 200, 500, "All"]]
				});
}

$(function() {
	$("#dateId").datepicker({ 
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
					str += '<td><a onClick="getUsersOfLeader('+result[i].id+');">'+result[i].name+'</a></td>';
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
				getUserDetailsByConstituencyForTable();
				
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

	str+='<table class="table table-bordered m_top20 table-hover table-striped" id="dayreport">';
    str+='<thead class="alert alert-success">';
	 str+='<tr>';
	  str+='<th>DC NAME</th>';
	  str+='<th>MOBILE NO</th>';
	  str+='<th>START TIME</th>';
	  str+='<th>END TIME</th>';
	  str+='<th>BOOTH NO</th>';
	  str+='<th>MANDAL</th>';
	  str+='<th>PANCHAYAT</th>';
	  str+='<th>BOOTH LOCATION</th>';
	  str+='<th>AREAS COVERED</th>';
	  str+='<th>DATA COLLECTED MAP</th>';
	  str+='<th>TRACKING MAP</th>	';	
	 str+='</tr>';
	str+='</thead>';

	$.each(result,function(index,value){
		str+='<tr>';
			str+='<td><a onClick="getUserDetails('+value.userid+','+value.boothId+',\'resultDiv'+index+'\',\'buildDiv'+index+'\')" style="cursor: pointer;">'+value.userName+'</a></td>';
			str+='<td>'+value.mobileNo+'</td>';
			str+='<td>'+value.startTime+'</td>';
			str+='<td>'+value.endTime+'</td>';
			str+='<td>'+value.partNo+'</td>';
			str+='<td>'+value.mandalName+'</td>';
			str+='<td>'+value.panchayatName+'</td>';
			str+='<td>'+value.localArea+'</td>';
			str+='<td>'+value.villageCovered+'</td>';
			str+='<td><a onClick="openTrackinWindow('+value.userid+',\''+$('#dateId').val()+'\',1) " style="cursor: pointer;"><img src="images/DC.png"></img></a></td>';
			str+='<td><a onClick="openTrackinWindow('+value.userid+',\''+$('#dateId').val()+'\',2) " style="cursor: pointer;"><img src="images/DC.png"></img></a></td>';
		str+='</tr>';

			str+='<tr id="resultDiv'+index+'" style="display:none;" class="buildDivCls">';
			str+='<td colspan="12"> <div id="buildDiv'+index+'"></div></td>';
			str+='</tr>';
	});

	/*var str = '';

	str+='<table class="table table-bordered m_top20 table-hover table-striped">';
    str+='<thead class="alert alert-success">';
	 str+='<tr>';
	  str+='<th>DC Name</th>';
	  str+='<th>START TIME</th>';
	  str+='<th>END TIME</th>';
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
	    
		     str+='<td>'+result[i].subList[j].startTime+'</td>';	
			 str+='<td>'+result[i].subList[j].endTime+'</td>';	


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
   str+='</table>';*/

 $('#tableDiv').html(str);

   // $('#dayreport').dataTable();

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
				if(result[6] > 0)
				{
				str += '<li>';
				str += '<hgroup>';
				str += '<h4 style="font-size:14px;">WARD</h4>';
				str += '<h2>'+result[6]+'</h2>';
				str += '<h5>Mapped</h5>';
				str += '</hgroup>';
				str += '</li>';
				}
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


function getDataCollectorInfo(userId,userName,mobileNo,leaderName,leaderMobile,boothId,boothNo,date,userType){
	
	$('#boothWiseTab,#startTimeTab').removeClass('selected');
	$('#callCenterTab').addClass('selected');
	showHideTabs('callCenterTab');

	getSurveyVotersList(userId,userName,mobileNo,leaderName,leaderMobile,boothId,boothNo,date,userType,1);

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
function getVerifierDetails(surveyUserId,boothId)
{
	
	$('#collecedCountDiv').html('');
	$('#verifiedCountDiv').html('');
	$('#updatedCountDiv').html('');
	$('#matchedCountDiv').html('');
	$('#unMatchedCountDiv').html('');
	$('#notVerifiedCountDiv').html('');
	var jObj =
	{
	  surveyUserId:surveyUserId,
	  boothId : boothId
	};

	 $.ajax({
			type:'GET',
			url: 'getVerifierCollectedDetailsAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		  }).done(function(result){	
			if(result != null)
			{
				$('#collecedCountDiv').show();
				$('#verifiedCountDiv').show();
				$('#updatedCountDiv').show();
				$('#matchedCountDiv').show();
				$('#unMatchedCountDiv').show();
				$('#notVerifiedCountDiv').show();
				
				$('#collecedCountDiv').html('<span style="font-weight:bold;"> Collectd  </span> : '+result[0].mobileCount+'</br>');
				$('#verifiedCountDiv').html('<span style="font-weight:bold;"> Verified  </span> : '+result[0].cadreCount+'</br>');
				$('#updatedCountDiv').html('<span style="font-weight:bold;">  Updated  </span> : '+result[0].casteCount+'</br>');
				$('#matchedCountDiv').html('<span style="font-weight:bold;"> Matched   </span> : '+result[0].totalCount+'</br>');
				$('#unMatchedCountDiv').html('<span style="font-weight:bold;"> Un Matched  </span> : '+result[0].influencePeopleCount+'</br>');
				$('#notVerifiedCountDiv').html('<span style="font-weight:bold;"> Not Identifed  </span> : '+result[0].localAreaCount+'</br>');
			}
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
		errorStr += 'Please Select Constituency<br>';


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

	$("#excelTableID1").show();
	$('#mainajaximg').hide();
	if(result == null || result.length == 0)
	{
		$('#retunMsg').html("<font color='red'>NO DATA AVILABLE</font>");
		return;
	}

	

	var str ='';
	

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
			 str+='<th>Total Collected Count</th>';
			 str+='<th>Total Collected Percent</th>';
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
	  str+='<td>'+value.totalCollectedCount+'</td>';
	   str+='<td>'+value.totalCollectedPercent+'</td>';



	   	if($('#fromDate').val()  != $('#toDate').val())
	      $.each(value.subList,function(index1,value1){
			   str+='<td>'+value1.count+'</td>';
		  });
	    /*else
	   {		
		  $.each(value.subList,function(index1,value1){
		
			   str+='<td>'+value1.count+'</td>';
			   str+='<td>'+value1.percent+'</td>';
		  });
	   }*/

    str+='</tr>';
   });
   str+='</tbody>';
   str+='</table>';
   
   
   //FOR EXPORTING TO EXCEL -- SASI
   str+='<table class="table table-bordered m_top20 table-hover table-striped username" id="daywisereportTableIdTemp" style="display:none;">';
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
			 str+='<th>Total Collected Count</th>';
			 str+='<th>Total Collected Percent</th>';
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
	  str+='<td>'+value.totalCollectedCount+'</td>';
	   str+='<td>'+value.totalCollectedPercent+'</td>';



	   	if($('#fromDate').val()  != $('#toDate').val())
	      $.each(value.subList,function(index1,value1){
			   str+='<td>'+value1.count+'</td>';
		  });
	    /*else
	   {		
		  $.each(value.subList,function(index1,value1){
		
			   str+='<td>'+value1.count+'</td>';
			   str+='<td>'+value1.percent+'</td>';
		  });
	   }*/

    str+='</tr>';
   });
   str+='</tbody>';
   str+='</table>';

 $('#dayWiseReportDiv1').html(str);

	//if($('#fromDate').val()  == $('#toDate').val())

	$('#daywisereportTableId').dataTable({
		"iDisplayLength": 100,
		"aLengthMenu": [[100, 200, 500, -1], [100, 200, 500, "All"]]
	});

}

function getReportForConstituency(){
	var constituencyId = $('#reportConstituencyId').val();
	$('#errDivIdForReport').html('');
	$('#basicStatusReport').html('');
	$('#panchayatDetailsDiv').html('');
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
	$("#stateStatusAjax").show();

	$.ajax({
		type:'GET',
		url: 'getSurveyStatusBoothListAction.action',
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		}).done(function(result){
				$("#stateStatusAjax").hide();
				if(result != null && result.genericVOList != null && result.genericVOList.length > 0 ){
					getPanchayatsStatusCount(constituencyId,result.genericVOList,result);
				}
				else{
					$('#basicCountDiv').html(' No Data Available ... ');
				}
		});
		
}

function getBoothsDetailsByStatusAndConstituency(statusId)
{
$('#tableDtailsDiv').html('');
$("#panchayatDetailsDiv").html('');
$('#statusAjaxImg').show();

	var constituency = $("#reportConstituencyId").val();
	var status = statusId;
	//console.log(boothArr);
	$('#tableDtailsDiv').html('');
	var jsObj =
	{
		//boothIds:boothArr
		constituency : constituency,
		status : status,
		scope : 9 // For Booth
		
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
	$('#statusAjaxImg').hide();
	if(result != null && result.length > 0)
			{	
				var str = '';
				
				if(statusId == 1)
				{
					str+='<div style="font-weight:bold; padding:5px;border-radius:5px;background-color:#DFF0D8;margin-bottom:15px;" align="center"> PROCESSING BOOTHS DETAILS </div>';
				}
				else if(statusId == 2)
				{
					str+='<div style="font-weight:bold; padding:5px;border-radius:5px;background-color:#DFF0D8;margin-bottom:15px;" align="center"> COMPLETED BOOTHS DETAILS </div>';
				}
				else if(statusId == 3)
				{
					str+='<div style="font-weight:bold; padding:5px;border-radius:5px;background-color:#DFF0D8;margin-bottom:15px;" align="center"> WEB MONITORING COMPLETED BOOTHS DETAILS </div>';
				}else if(statusId == 4)
				{
					str+='<div style="font-weight:bold; padding:5px;border-radius:5px;background-color:#DFF0D8;margin-bottom:15px;" align="center"> VERIFICATION IN PROCESS BOOTHS DETAILS </div>';
				}else if(statusId == 5)
				{
					str+='<div style="font-weight:bold; padding:5px;border-radius:5px;background-color:#DFF0D8;margin-bottom:15px;" align="center"> VERIFICATION COMPLETED BOOTHS DETAILS </div>';
				}
				str += '<table class="table table-bordered m_top20 table-hover table-striped " id="reportTab">';
				str += '<thead>';
				str += '<tr>	';									  
				str += ' <th>Booth NO</th>';
				str += '<th>Total Voters</th>	';	
				str += ' <th>Mobile Numbers Collected</th>';
				str += '<th>Caste Collected</th>	';
				str += ' <th>Hamlets Collected</th>';
				if(statusId == 3){
					str += ' <th> Caste Error % </th>';
				}
				str += ' <th> Update Status </th>';
						
				str += '</tr>';
				str += ' </thead>';
				str += '<tbody>	';

				//console.log(statusId);				
				
				for(var i in result)
				{
					str += '<tr id="updateDiv'+i+'">		';								  
					str += '<td>'+result[i].partNo+'</td>';
					str += '<td>'+result[i].totalVoters+'</td>';
					str += '<td>'+result[i].mobileNoCount+'</td>';
					var castePer = ((result[i].casteCount * 100)/result[i].totalVoters).toFixed(2);
					var hamletPer = ((result[i].hamletCount * 100)/result[i].totalVoters).toFixed(2);
					str += '<td>'+result[i].casteCount+'('+castePer+'%)</td>';
					str += '<td>'+result[i].hamletCount+'('+hamletPer+'%)</td>';
					if(statusId == 3){
						str += '<td>'+result[i].casteErrorPercent+'</td>';
					}
					str += '<td><select id="boothStatus" onChange="updateBoothStatusDetails(this.value,'+result[i].boothId+','+i+')"><option>Select Status</option>';
					if(statusId == 1)
					{
						str += '<option value="1" selected="selected">DC PROCESS</option>';
					}
					else
					{
						str += '<option value="1">DC PROCESS</option>';
					}
					if(statusId == 2)
					{
						str += '<option value="2" selected="selected">DC COMPLETED</option>';
					}
					else
					{
						str += '<option value="2">DC COMPLETED</option>';
					}
					if(statusId == 3)
					{
						str += '<option value="3" selected="selected">WM COMPLETED</option>';
					}
					else
					{
						str += '<option value="3">WM COMPLETED</option>';
					}
					if(statusId == 4)
					{
						str += '<option value="4" selected="selected" >DV PROCESS</option>';
					}
					else
					{
						str += '<option value="4">DV PROCESS</option>';
					}
					if(statusId == 5)
					{
						str += '<option value="5" selected="selected" >DV COMPLETED</option>';
					}
					else
					{
						str += '<option value="5">DV COMPLETED</option>';
					}				
					str += '</select></td>';
					str += '</tr>	';
									
				}
				
				str += '</tbody>';
				str += '</table>';
				
				$('#tableDtailsDiv').html(str);
				$('#reportTab').dataTable({
				"iDisplayLength": 100,
				"aLengthMenu": [[100, 200, 500, -1], [100, 200, 500, "All"]]
				});
			}			
		
}

function getPanchayatsStatusCount(constituencyId,boothResult,result1)
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
			buildStatusForPanchayats(result,constituencyId,boothResult,result1);	
		});
	
	
}
function getPanchayatDetails(status,constituencyId)
{
$('#tableDtailsDiv').html('');
$("#panchayatDetailsDiv").html('');
$('#statusAjaxImg').show();
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
			buildStatusForPanchayatDetials(result,status);	
		});
}
function buildStatusForPanchayats(result,constituencyId,boothResult,result1)
{

	var str='';
	
	
		$('#basicStatusReport').html('');
	

				str += '<div class="row-fluid">';
				str += '<div class="span12 booths-Overview-widget">';
				str += '<div class="row-fluid">';
				str += '<ul class="inline unstyled booths-Overview-widget-nav">';
				
			   for(var i in boothResult){
			 
					str += '<li>';
					str += '<hgroup>';
					str += '<h4>'+boothResult[i].name+'</h4>';
					if(boothResult[i].count != 0)
						str += '<h2> <a href="javascript:{getSurveyBoothDetails('+boothResult[i].id+');}">'+boothResult[i].count+'</a></h2>';
					else
						str += '<h2>'+boothResult[i].count+'</h2>';
						
					str += '</hgroup>';
					str += '</li>';	
				}	
str+='<li><hgroup><h4>VERIFICATION Processing </h4><h2 id="verificationProcessId"><a href="javascript:{getSurveyBoothDetails(4);}"}">'+result1.verificationProcessList.length+'</a></h2></hgroup>';
	str+='<li><hgroup><h4>VERIFICATION Completed </h4><h2 id="verificationCompletedId"><a href="javascript:{getSurveyBoothDetails(5);}"}">'+result1.verificationCompletionList.length+'</a></h2></hgroup>';
	/* str+='<li><hgroup><h4>VERIFICATION Processing </h4><h2 id="verificationProcessId"><a href="javascript:{getVerifiedBoothsDetails(\'process\',4)}">'+result1.verificationProcessList.length+'</a></h2></hgroup>';
	str+='<li><hgroup><h4>VERIFICATION Completed </h4><h2 id="verificationCompletedId"><a href="javascript:{getVerifiedBoothsDetails(\'completed\',5)}">'+result1.verificationCompletionList.length+'</a></h2></hgroup>'; */


	if(result.panchayatCount == 0)
	{
		str+='<li><hgroup><h4>PANCHAYAT Completed </h4><h2 id="panchayatreadyID">'+result.panchayatCount+'</h2></hgroup>';
	str+='</li>';
	}
	else
	{
	str+='<li><hgroup><h4>PANCHAYAT Completed </h4><h2 id="panchayatreadyID"><a href="javascript:{getPanchayatDetails(\'completed\',\''+constituencyId+'\');}">'+result.panchayatCount+'</a></h2></hgroup>';
	str+='</li>';
	}	
	
	if(result.panchayatNotCompleteCount == 0)
	{
		 str+='<li><hgroup><h4>PANCHAYAT Not Completed </h4><h2 id="panchayatNotReady">'+result.panchayatNotCompleteCount+'</h2></hgroup>';
	str+='</li></ul></div></div></div>';
	
	}
	else
	{
    str+='<li><hgroup><h4>PANCHAYAT Not Completed </h4><h2 id="panchayatNotReady"><a href="javascript:{getPanchayatDetails(\'notcompleted\',\''+constituencyId+'\');}">'+result.panchayatNotCompleteCount+'</a></h2></hgroup>';
	str+='</li></ul></div></div></div>';
	}
					
	$('#basicStatusReport').html(str);
}

function buildStatusForPanchayatDetials(result, status)
{
	$('#tableDtailsDiv').html('');
	$("#panchayatDetailsDiv").html('');
	$('#statusAjaxImg').hide();
	var str='';
	
	if(status == "completed"){
		str+='<div style="font-weight:bold; padding:5px;border-radius:5px;background-color:#DFF0D8;margin-bottom:15px;" align="center"> COMPLETED PANCHAYAT DETAILS </div>';
	}
	else{
		str+='<div style="font-weight:bold; padding:5px;border-radius:5px;background-color:#DFF0D8;margin-bottom:15px;" align="center"> NOT COMPLETED PANCHAYAT DETAILS  </div>';
	}
	if(result.length == 0)
	{
	
	$("#panchayatDetailsDiv").html('<font color="red">No Data Available...</font>').css("text-align","center");
	return;
	}

		str+='<table class="table table-bordered m_top20 table-hover table-striped" id="panchayatStatusTable">';
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
			str+='<tr id="hamletTr'+i+'">';
			str+='<td  class="hamletDiv"><a id='+result[i].id+' onclick="getHamletInfo(this.id,\'hamletTr'+i+'\','+i+');">'+result[i].name+'</a>';
			str+='</td>';
			str+='<td>'+result[i].totalVoters+'</td>';
			str+='<td>'+result[i].mobileNoCount+'</td>';
			str+='<td>'+result[i].casteCount+'</td>';
			str+='<td>'+result[i].hamletCount+'</td>';
			str+='</tr>';
			/*str+='<tr id="hamletresultDiv'+i+'" style="display:none;" class="hamletbuildDivCls">';
			str+='<td colspan="10"> <div id="hamletbuildbuildDiv'+i+'"></div></td>';
			str+='</tr>';*/
		}

		str+='</tbody>';
		str+='</table>';
	$("#panchayatDetailsDiv").html(str);
	$("#panchayatStatusTable").dataTable({
	"iDisplayLength": 100,
	"aLengthMenu": [[100, 200, 500, -1], [100, 200, 500, "All"]]
	});
	
}
function getHamletInfo(id,tr,index)
{

//$("#"+tr).last().append("<tr><td colspan='5'><div id='hamletbuildbuildDiv'>dsfdhfhd dhfdg</div></td></tr>");
$(".hamletRow").remove();
$('<tr class="hamletRow"><td colspan="5" id="hamletRow'+index+'"></td></tr>').insertAfter($("#"+tr));
var jsObj={
		
		panchayatId:id
	};
	$.ajax({
	type:'GET',
	url: 'getHamletDetialsByPanchayatAction.action',
	dataType: 'json',
	data: {task:JSON.stringify(jsObj)},

	}).done(function(result){
		buildHamletInfo(result,'hamletRow'+index+'');
	});

}

function buildHamletInfo(result,DivEle)
{
	var str='';
	if(result.panchayatsList.length == 0)
	{
		$("#"+DivEle).html('<font color="red">No Data available</font>');
		return;
	}
	if(result.panchayatsList.length > 0)
	{
	 str+='<div class="row-fluid"><div class="span12 booths-Overview-widget">';
     str+='<div class="row-fluid"><h4>Hamlet</h4><ul class="inline unstyled booths-Overview-widget-nav">';

	for(var i in result.panchayatsList)
	{
		for(var j in result.panchayatsList[i].hamletsCountList)
		{

 str+='<li style="width:180px;"><hgroup><h5>'+result.panchayatsList[i].hamletsCountList[j].hamletName+' - '+result.panchayatsList[i].hamletsCountList[j].surveyCount+'</h5></hgroup></li>';
 
		}
	}
	str+='</ul></div></div></div></div>';
 $("#"+DivEle).html(str);
	}


}

function getRespectiveUsers(id)
{
	$("#userProcessingImg").show();
	var jsObj={
		//constituencyId:$('#userWiseReportConstituencyId').val(),
		userTypeId:id
	};
	$.ajax({
	type:'GET',
	url: 'getDcorDvUsersByConstituencyAction.action',
	dataType: 'json',
	data: {task:JSON.stringify(jsObj)},
	}).done(function(result){
				$("#userProcessingImg").hide();
				$('#userReportUser').find('option').remove();
				$('#userReportUser').append('<option value="0"> Select User </option>');
				if(result != null && result.length>0){
					for(var i in result){
						$('#userReportUser').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
					}
				}	
	});
}

function getUserWiseReport()
{
	$('#userWiseReportImg').show();
	var jsObj={
		//constituencyId:$('#userWiseReportConstituencyId').val(),
		userTypeId:$('#userReportUserType').val(),
		fromDate : $('#fromDateForUserReport').val(),
		toDate : $('#toDateForUserReport').val(),
		surveyUserId : $('#userReportUser').val()
	};
	$.ajax({
	type:'GET',
	url: 'getDcAndDvByConstituencyByUserAction.action',
	dataType: 'json',
	data: {task:JSON.stringify(jsObj)},
	}).done(function(result){
		$('#userWiseReport').html('');
		if(result != null && result.length > 0)
		{
			buildUserReport(result,$('#userReportUserType').val());
		}
		else
		{
			$('#userWiseReportImg').hide();
		}		
	});
}

function buildUserReport(result,userType)
{
	$("#excelTableID2").show();

	var str = '';
	str+='<table class=" table table-bordered m_top20 table-hover table-striped" id="userReportTable">';
	str+='<thead>';
	str+='<th>Constituency</th>';
	str+='<th>Booth</th>';
	str+='<th>Date</th>';
	str+='<th>Total</th>';
	str+='<th>Mobile</th>';
	str+='<th>Caste</th>';
	str+='<th>Hamlet</th>';
	str+='<th>Local Area</th>';
	str+='<th>Cadre</th>';
	str+='<th>Influence People</th>';
	if(userType == 1)
	{
		str+='<th>WM Error Rate</th>';
		str+='<th>DV Error Rate</th>';
	}
	
	str+='</thead>';
	str+='<tbody>';
	for(var i in result)
	{
		str += '<tr>';
		str+='<td>'+result[i].constituency+'</td>';
		str+='<td>'+result[i].partNo+'</td>';
		str+='<td>'+result[i].date+'</td>';
		str+='<td>'+result[i].totalCount+'</td>';
		str+='<td>'+result[i].mobileCount+'</td>';
		str+='<td>'+result[i].casteCount+'</td>';
		str+='<td>'+result[i].hamletCount+'</td>';
		str+='<td>'+result[i].localAreaCount+'</td>';
		str+='<td>'+result[i].cadreCount+'</td>';
		str+='<td>'+result[i].influencePeopleCount+'</td>';
		if(userType == 1)
		{
			if(result[i].wmErrorRate == null)
			{
				str+='<td>--</td>';
			}
			else
			{	
				str+='<td>'+result[i].wmErrorRate+'</td>';
			}
			if(result[i].verifierErrorRate == null)
			{
				str+='<td>--</td>';
			}
			else
			{	
				str+='<td>'+result[i].verifierErrorRate+'</td>';
			}
					
		}

		str += '</tr>';
	}
	str+='</tbody>';
	str+='</table>';
	$('#userWiseReport').html(str);
	$('#userWiseReportImg').hide();

}
function updateBoothStatusDetails(statusId,boothId,divId)
{
	
	$.ajax({
	type:'GET',
	url: 'saveBoothStatusDetails.action',
	dataType: 'json',
	data: {statusId:statusId,locationValue:boothId,locationType:"booth"},
	}).done(function(result){
		if(result != null && result == "success"){
		//getReportForConstituency();
		getBoothsStatusDetailsOfConstituency();
		$('#updateDiv'+divId+'').hide();			
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

function generateExcel(id)
{
tableToExcel(id, 'Users Report');
}

function getBoothsDetailsInCallStatusInfoForSavePercnt(constituencyId,divId)
{

$("#errorDivSB").html("");

	$("#boothImage").show();
	var jObj =
	{
	  constituencyId:constituencyId     
	};

	 $.ajax({
			type:'GET',
			url: 'getBoothsDetailsInCallStatusAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		  }).done(function(result){				
				//buildDayWiseReportByUserType(result);		
				$("#boothImage").hide();
				$('#'+divId+'').find('option').remove();

				$.each(result,function(index,value){
					$('#'+divId+'').append('<option value="'+value.id+'">Booth - '+value.name+'</option>');
				});
			

		});	
}
function saveBoothPercentage(){
	$("#boothImageForSavingPercent").show();
	var consId = $("#constituencyForSP").val();
	var boothId = $("#boothIdForSavePercentages").val();
	var percentage =$("#percenageForBooth").val();
	
	$("#errorDivSB").html("");
	
	if(consId == 0){
		$("#errorDivSB").html("<span style='color:red'>Please Select Constituency</span>");
		$("#boothImageForSavingPercent").hide();
		return;
	}
	if(boothId == 0 || boothId == null){
		$("#errorDivSB").html("<span style='color:red'>Please Select Booth</span>");
		$("#boothImageForSavingPercent").hide();
		return;
	}
	if(percentage==""){
		$("#errorDivSB").html("<span style='color:red'>Please Enter Percentage</span>");
		$("#boothImageForSavingPercent").hide();
		return;
	}
	
	var jObj ={
	  boothId:boothId,
	  percentage:percentage
	};

	 $.ajax({
			type:'GET',
			url: 'saveBoothPercentageForCasteSurveyAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)}
		  }).done(function(result){		
				$("#boothImageForSavingPercent").hide();
				$("#errorDivSB").html("<span style='color:blue'>"+result+"</span>");
		});	
}
var users=[];
function getUsersOfLeader(id)
{
	var constituencyId = $('#userConstituencyId').val();
	var jsObj =
	{
	leaderId :id,
	constituencyId : constituencyId,
	task : "getSurveyUsersByleaderId"
	}
	$.ajax({
	type:'GET',
	url: 'getLeaderUsersAction.action',
	dataType: 'json',
	data: {task:JSON.stringify(jsObj)},
	}).done(function(result){	
			users = result;
			getInActiveUsersDetailsByLeaderId(id,users);
		});
		
}

function getInActiveUsersDetailsByLeaderId(leaderId,users)
{
$('#tableDiv').html('');
var jObj =
	{	 
	  constituencyId: $("#userConstituencyId").val(),
	  dateStr : $("#dateId").val(),
	  userId : users

	};
	$.ajax({
			type:'GET',
			url: 'getLatLongForSurveyUsersByConstituencyAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		  }).done(function(result){	
			  
				buildLocationDetails(result);
				getUserDetailsByConstituencyForTable1(users);
				
				
		});

}

function getUserDetailsByConstituencyForTable1(users)
{

var jObj =
	{
	 constituencyId: $("#userConstituencyId").val(),
	 date : $("#dateId").val(),
	 userIds : users,
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


function buildVerifierOrWMReport(result,buildType,buildDiv,imgId)
{
	if(buildDiv=="dayWiseReportDivForVerifier"){
		$("#excelTableID4").show();
		$("#excelTableID3").hide();
	}else{
		$("#excelTableID3").show();
		$("#excelTableID4").hide();
	}
	
	//console.log(buildDiv);
	$('#dayWiseReportDivForVerifier').html('');
	$('#dayWiseReportDivForWm1').html('');
	var str = '';
	str += '<div class="row-fluid">';
	if(result[0].matchedList[0] != null )
	{
		if(buildType == 1)
		{
			str += '<div class="span12" style="background-color: yellow; padding: 5px;margin-top:25px;margin-bottom:10px;" >PANCHAYAT : '+result[0].matchedList[0].panchayatName+'</div> ';
		}
		else
		{
			str += '<div class="row-fluid">';
			str += '<div class="span4" style="background-color: yellow; padding: 5px;margin-top:25px;margin-bottom:10px;" >PANCHAYAT : '+result[0].matchedList[0].panchayatName+'</div> ';
			str += '<div class="span4" style="background-color: yellow; padding: 5px;margin-top:25px;margin-bottom:10px;">DC NAME: '+result[0].surveyUser+'</div> ';
			str += '<div class="span4" style="background-color: yellow; padding: 5px;margin-top:25px;margin-bottom:10px;">DC Date : '+result[0].date+'</div> ';
		}
		
	}
	else if(result[0].unMatchedList[0] != null )
	{
		if(buildType == 1)
		{
			str += '<div class="span12" style="background-color: yellow; padding: 5px;margin-top:25px;margin-bottom:10px;" >PANCHAYAT : '+result[0].unMatchedList[0].panchayatName+'</div> ';
		}
		else
		{
			str += '<div class="row-fluid">';
			str += '<div class="span4" style="background-color: yellow; padding: 5px;margin-top:25px;margin-bottom:10px;" >PANCHAYAT : '+result[0].unMatchedList[0].panchayatName+'</div> ';
			str += '<div class="span4" style="background-color: yellow; padding: 5px;margin-top:25px;margin-bottom:10px;">DC NAME: '+result[0].surveyUser+'</div> ';
			str += '<div class="span4" style="background-color: yellow; padding: 5px;margin-top:25px;margin-bottom:10px;">DC Date : '+result[0].date+'</div> ';
		}
	}
	else if(result[0].notVerifiedList[0] != null )
	{
		if(buildType == 1)
		{
			str += '<div class="span12" style="background-color: yellow; padding: 5px;margin-top:25px;margin-bottom:10px;" >PANCHAYAT : '+result[0].notVerifiedList[0].panchayatName+'</div> ';
		}
		else
		{
			str += '<div class="row-fluid">';
			str += '<div class="span4" style="background-color: yellow; padding: 5px;margin-top:25px;margin-bottom:10px;" >PANCHAYAT : '+result[0].notVerifiedList[0].panchayatName+'</div> ';
			str += '<div class="span4" style="background-color: yellow; padding: 5px;margin-top:25px;margin-bottom:10px;">DC NAME: '+result[0].surveyUser+'</div> ';
			str += '<div class="span4" style="background-color: yellow; padding: 5px;margin-top:25px;margin-bottom:10px;">DC Date : '+result[0].date+'</div> ';
		}
	}
	if(buildType == 1)
	{
		str += '</div>';
		str += '<div class="row-fluid">';
		str += '<div class="span3" style="background-color: yellow; padding: 5px;margin-top:25px;margin-bottom:10px;">DC NAME: '+result[0].surveyUser+'</div> ';
		str += '<div class="span3" style="background-color: yellow; padding: 5px;margin-top:25px;margin-bottom:10px;">DC Date : '+result[0].date+'</div> ';
		str += '<div class="span3" style="background-color: yellow; padding: 5px;margin-top:25px;margin-bottom:10px;">DV NAME: '+result[0].verifierUser+'</div> ';
		str += '<div class="span3" style="background-color: yellow; padding: 5px;margin-top:25px;margin-bottom:10px;">DV Date : '+result[0].verifierDate+'</div> ';
		str += '</div>';
	}
	if(buildType == 1)
	{
		str += '<div class="row-fluid">';
		str += '<div class="span3" style="background-color: yellow; padding: 5px;margin-top:25px;margin-bottom:10px;" >COLLECTED : '+result[0].collectedCount+'</div> ';
		str += '<div class="span3" style="background-color: yellow; padding: 5px;margin-top:25px;margin-bottom:10px;">UPDATED : '+result[0].updatedCount+'</div> ';
		str += '<div class="span3" style="background-color: yellow; padding: 5px;margin-top:25px;margin-bottom:10px;">VERIFIED : '+result[0].verifieCount+'</div> ';
		str += '<div class="span3" style="background-color: yellow; padding: 5px;margin-top:25px;margin-bottom:10px;">NOT IDENTIFED : '+result[0].notIdentifedCount+'</div> ';
		str += '</div>';
	}
	str += '<div>';
	str += '<div class="span3" style="background-color: yellow; padding: 5px;margin-top:25px;margin-bottom:10px;">TOTAL : '+result[0].totalCount+'</div> ';
	str += '<div class="span3" style="background-color: yellow; padding: 5px;margin-top:25px;margin-bottom:10px;">MATCHED : '+result[0].matchedCount+'</div>';
	str += '<div class="span3" style="background-color: yellow; padding: 5px;margin-top:25px;margin-bottom:10px;">UN MATCHED : '+result[0].unMatchedCount+'</div> ';
	str += '<div class="span3" style="background-color: yellow; padding: 5px;margin-top:25px;margin-bottom:10px;">NOT VERIFIED : '+result[0].notVerifiedCount+'</div> ';
	str += '</div>';
	
	str+='<table id="dayWiseReportTableForVerifier" class="table table-bordered m_top20 table-hover table-striped username">';
	str+='<thead class="alert alert-success">';
	str+='<tr>';
	str+='<th>Voter Name</th>';
	str+='<th>Relative Name</th>';
	str+='<th>DC CASTE</th>';
	str+='<th>WM CASTE</th>';
	if(buildType == 1)
	{
		str+='<th>DV CASTE</th>';
		str+='<th>DV STATUS</th>';
		str+='<th>MOBILE NO</th>';
	}
	str+='<th>MATCH STATUS</th>';
	str+='</tr>';
	str+='</thead>';
	str+='<tbody>'
	for(var i in result)
	{
		
		for(var j in result[i].matchedList)
		{
			str += '<tr>';
			str+='<td>'+result[i].matchedList[j].voterName+'</td>';
			str+='<td>'+result[i].matchedList[j].relativeName+'</td>';
			str+='<td>'+result[i].matchedList[j].dcCaste+'</td>';
			str+='<td>'+result[i].matchedList[j].wmCaste+'</td>';
			if(buildType == 1)
			{
				str+='<td>'+result[i].matchedList[j].dvCaste+'</td>';
				str+='<td>'+result[i].matchedList[j].verifierStatus+'</td>';
				if(result[i].matchedList[j].mobileNO != null)
				str+='<td>'+result[i].matchedList[j].mobileNO+'</td>';
				else
				str+='<td>-</td>';
			}
			str+='<td>MATCHED</td>';
			str += '</tr>';		
		}
		for(var j in result[i].unMatchedList)
		{
			str += '<tr>';
			str+='<td>'+result[i].unMatchedList[j].voterName+'</td>';
			str+='<td>'+result[i].unMatchedList[j].relativeName+'</td>';
			str+='<td>'+result[i].unMatchedList[j].dcCaste+'</td>';
			str+='<td>'+result[i].unMatchedList[j].wmCaste+'</td>';
			if(buildType == 1)
			{
				str+='<td>'+result[i].unMatchedList[j].dvCaste+'</td>';
				str+='<td>'+result[i].unMatchedList[j].verifierStatus+'</td>';
				if(result[i].unMatchedList[j].mobileNO != null)
				str+='<td>'+result[i].unMatchedList[j].mobileNO+'</td>';
				else
				str+='<td>-</td>';
			}
			str+='<td>UNMATCHED</td>';
			str += '</tr>';		
		}
		for(var j in result[i].notVerifiedList)
		{
			str += '<tr>';
			str+='<td>'+result[i].notVerifiedList[j].voterName+'</td>';
			str+='<td>'+result[i].notVerifiedList[j].relativeName+'</td>';
			str+='<td>'+result[i].notVerifiedList[j].dcCaste+'</td>';
			str+='<td>'+result[i].notVerifiedList[j].wmCaste+'</td>';
			if(buildType == 1)
			{
				str+='<td>'+result[i].notVerifiedList[j].dvCaste+'</td>';
				str+='<td>'+result[i].notVerifiedList[j].verifierStatus+'</td>';
				if(result[i].notVerifiedList[j].mobileNO != null)
				str+='<td>'+result[i].notVerifiedList[j].mobileNO+'</td>';
				else
				str+='<td>-</td>';
			}
			
			str+='<td>NOT VERIFIED</td>';
			str += '</tr>';		
		}
	}
	str += '</tbody>';
	str += '</table>';
	
	
	
	//FOR EXPORT TO EXCEL -- SASI
	str+='<table id="dayWiseReportTableForVerifier1" class="table table-bordered m_top20 table-hover table-striped username" style="display:none;"> ';
	
	str+='<thead class="alert alert-success">';
	str+='<tr>';
	str+='<th>Voter Name</th>';
	str+='<th>Relative Name</th>';
	str+='<th>DC CASTE</th>';
	str+='<th>WM CASTE</th>';
	if(buildType == 1)
	{
		str+='<th>DV CASTE</th>';
		str+='<th>DV STATUS</th>';
		str+='<th>MOBILE NO</th>';
	}
	str+='<th>MATCH STATUS</th>';
	str+='</tr>';
	str+='</thead>';
	str+='<tbody>'
	for(var i in result)
	{
		
		for(var j in result[i].matchedList)
		{
			str += '<tr>';
			str+='<td>'+result[i].matchedList[j].voterName+'</td>';
			str+='<td>'+result[i].matchedList[j].relativeName+'</td>';
			str+='<td>'+result[i].matchedList[j].dcCaste+'</td>';
			str+='<td>'+result[i].matchedList[j].wmCaste+'</td>';
			if(buildType == 1)
			{
				str+='<td>'+result[i].matchedList[j].dvCaste+'</td>';
				str+='<td>'+result[i].matchedList[j].verifierStatus+'</td>';
				if(result[i].matchedList[j].mobileNO != null)
				str+='<td>'+result[i].matchedList[j].mobileNO+'</td>';
				else
				str+='<td>-</td>';
			}
			str+='<td>MATCHED</td>';
			str += '</tr>';		
		}
		for(var j in result[i].unMatchedList)
		{
			str += '<tr>';
			str+='<td>'+result[i].unMatchedList[j].voterName+'</td>';
			str+='<td>'+result[i].unMatchedList[j].relativeName+'</td>';
			str+='<td>'+result[i].unMatchedList[j].dcCaste+'</td>';
			str+='<td>'+result[i].unMatchedList[j].wmCaste+'</td>';
			if(buildType == 1)
			{
				str+='<td>'+result[i].unMatchedList[j].dvCaste+'</td>';
				str+='<td>'+result[i].unMatchedList[j].verifierStatus+'</td>';
				if(result[i].unMatchedList[j].mobileNO != null)
				str+='<td>'+result[i].unMatchedList[j].mobileNO+'</td>';
				else
				str+='<td>-</td>';
			}
			str+='<td>UNMATCHED</td>';
			str += '</tr>';		
		}
		for(var j in result[i].notVerifiedList)
		{
			str += '<tr>';
			str+='<td>'+result[i].notVerifiedList[j].voterName+'</td>';
			str+='<td>'+result[i].notVerifiedList[j].relativeName+'</td>';
			str+='<td>'+result[i].notVerifiedList[j].dcCaste+'</td>';
			str+='<td>'+result[i].notVerifiedList[j].wmCaste+'</td>';
			if(buildType == 1)
			{
				str+='<td>'+result[i].notVerifiedList[j].dvCaste+'</td>';
				str+='<td>'+result[i].notVerifiedList[j].verifierStatus+'</td>';
				if(result[i].notVerifiedList[j].mobileNO != null)
				str+='<td>'+result[i].notVerifiedList[j].mobileNO+'</td>';
				else
				str+='<td>-</td>';
			}
			
			str+='<td>NOT VERIFIED</td>';
			str += '</tr>';		
		}
	}
	str += '</tbody>';
	str += '</table>';
	
	$('#'+buildDiv+'').html(str);
	$('#dayWiseReportTableForVerifier').dataTable({
		"iDisplayLength": 100,
		"aLengthMenu": [[100, 200, 500, -1], [100, 200, 500, "All"]]
	});
	
	$('#'+imgId+'').hide();
			
}

function getVerifiedBoothsDetails(status,statusId)
{
	$('#tableDtailsDiv').html('');
	$("#panchayatDetailsDiv").html('');
	$('#statusAjaxImg').show();
	 $.ajax({
			type:'GET',
			url: 'getVerifiedBoothsDetails.action',
			dataType: 'json',
			data: {status:status,constituencyId:$('#reportConstituencyId').val()}
		  }).done(function(result){
			  if(status == "process")
			   buildSurveyBoothDetailsTable(result,4);
			  else
			  buildSurveyBoothDetailsTable(result,5);
		});	

}

function getDataReportSummary()
{
	var jsObj = {
		task : "constituencySummary"
	}
	$("#fieldDataSummaryimg").css("display","block");
 $.ajax({
			type:'GET',
			url: 'getConstituencySummaryReport.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		  }).done(function(result){
			  $("#fieldDataSummaryimg").css("display","none");
			  if(result.length > 0)
			 buildSummaryForFieldData(result);
			
		});	

}
function buildSummaryForFieldData(result)
{

			var str ='';
			  
				  str+='<table class="table table-bordered m_top20 table-hover table-striped" id="fieldDataSummaryTable">';
				  str+='<thead class="alert alert-success">';
				  str+='<th>Constituency</th>';	
				  str+='<th>Total Voters</th>';	
				  str+='<th>Caste Tag Voters</th>';
				  str+='<th>Caste Error Rate</th>';
				  str+='<th>Mobile Error Rate</th>';
				  str+=' <th>Empty Fields</th>';
				 str+='</thead>';
				 str+='<tbody>';
				  for(var i in result)
				  {
					  str+='<tr>';
					str+='<td>'+result[i].constituency+'</td>';
					str+='<td>'+result[i].totalCount+'</td>';
					str+='<td>'+result[i].casteCount+'</td>';
					if(result[i].casteErrorRate == null)
					str+='<td>-</td>';
					else
					str+='<td>'+result[i].casteErrorRate+'</td>';
					if(result[i].mobileErrorRate == null)
					str+='<td>-</td>';
					else
					str+='<td>'+result[i].mobileErrorRate+'</td>';
					var count = result[i].totalCount - result[i].casteCount;
					str+='<td>'+count+'</td>';
					
					str+='</tr>';
				  }
				   str+='</tbody>';
				  str+='</table>';
				  $("#fieldDataSummary").html(str);
				  $('#fieldDataSummaryTable').dataTable();
			
}
function getLeadersAndUsersByConstituency()
{
	var constituencyId = $("#userConstituencyId").val();
	var name = $("#userConstituencyId option:selected").text();

	var dateVal = $("#dateId").val();
$("#leaderAndUserDetailsDiv").html('');
$("#leaderAndUserDetailsInactiveDiv").html('');
	var jObj =
	{	 
	  constituencyId: constituencyId,
	  dateStr :dateVal,
	   task:"getLeaderAndUserDetails"
	};
	$.ajax({
			type:'GET',
			url: 'getConstituencyLeadersAndUsersAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		  }).done(function(result){				
				buildLeadersAndUsersTableActive(result,name);	
				buildLeadersAndUsersTableInActive(result,name);	
		});

}
function buildLeadersAndUsersTableActive(result,name)
{
	var str ='';	
	str+='<h4>'+name+' Constituency Active Users</h4>';
	str+='<table class="table table-bordered m_top20 table-hover table-striped" id="leaderUserTableId">';
	str+='<thead class="alert alert-success">';
	str+='<th>Leader Name</th>';	
	str+='<th>User Name</th>';
	str+=' <th>Status</th>';
	str+='</thead>';
	str+='<tbody>';
	  for(var i in result)
	  {		
		for(var j in result[i].subList)
		{	
			if(result[i].subList[j].name == 'Active')
			{
			str+='<tr>';
			str+='<td>'+result[i].leaderName+'<br>'+result[i].mobileNo+'</td>';		
			str+='<td>'+result[i].subList[j].userName+'<br>'+result[i].subList[j].userType+'</td>';
			str+='<td>'+result[i].subList[j].name+'</td>';	
			str+='</tr>';
			}
		}		
	}
	str+='</tbody>';
	str+='</table>';
	$("#leaderAndUserDetailsDiv").html(str);
	$('#leaderUserTableId').dataTable();
}


function buildLeadersAndUsersTableInActive(result,name)
{
	var str ='';	
	str+='<h4>'+name+' Constituency InActive Users</h4>';
	str+='<table class="table table-bordered m_top20 table-hover table-striped" id="leaderUserTableInactiveId">';
	str+='<thead class="alert alert-success">';
	str+='<th>Leader Name</th>';	
	str+='<th>User Name</th>';
	str+=' <th>Status</th>';
	str+='</thead>';
	str+='<tbody>';
	  for(var i in result)
	  {		
		for(var j in result[i].subList)
		{	
			if(result[i].subList[j].name == 'Inactive')
			{
			str+='<tr>';
			str+='<td>'+result[i].leaderName+'<br>'+result[i].mobileNo+'</td>';	
			
			str+='<td>'+result[i].subList[j].userName+'<br>'+result[i].subList[j].userType+'</td>';
			str+='<td>'+result[i].subList[j].name+'</td>';	
			str+='</tr>';
			}
		}		
	}
	str+='</tbody>';
	str+='</table>';
	$("#leaderAndUserDetailsInactiveDiv").html(str);
	$('#leaderUserTableInactiveId').dataTable();
}

function getBoothsStatusDetailsOfConstituency()
{

	if($('#reportConstituencyId').val() ==0)
	{
      $("#constnErrDiv").html('Please Select Constituency');
	  return;
	}

	$('#stateStatusAjax').show();
		$.ajax({
		type:'GET',
		url: 'getBoothStatusDetailsByConstituency.action',
		dataType: 'json',
		data: {constituencyId:$('#reportConstituencyId').val()},
		}).done(function(result){
          buildBoothsStatusCountsDetails(result);
        });
}
function buildBoothsStatusCountsDetails(result)
{
		$('#stateStatusAjax').hide();
	var str='';

		str += '<div class="row-fluid">';
		str += '<div class="span12 booths-Overview-widget">';
		str += '<div class="row-fluid">';
		str += '<ul class="inline unstyled booths-Overview-widget-nav">';

		str += '<li>';
		str += '<hgroup>';
		str += '<h4>DATA COLLECTOR PROCESSING</h4>';
		str += '<h2> <a href="javascript:{getBoothsDetailsByStatusAndConstituency(1)}">'+result.processingCount+'</a></h2>';
		str += '</hgroup>';
		str += '</li>';
		
		str += '<li>';
		str += '<hgroup>';
		str += '<h4>DATA COLLECTOR COMPLETED</h4>';
		str += '<h2> <a href="javascript:{getBoothsDetailsByStatusAndConstituency(2)}">'+result.completedCount+'</a></h2>';
		str += '</hgroup>';
		str += '</li>';

		str += '<li>';
		str += '<hgroup>';
		str += '<h4>WEB MONITOR COMPLETED</h4>';
		str += '<h2> <a href="javascript:{getBoothsDetailsByStatusAndConstituency(3)}">'+result.wmCompletedCount+'</a></h2>';
		str += '</hgroup>';
		str += '</li>';

		str += '<li>';
		str += '<hgroup>';
		str += '<h4>VERIFICATION PROCESSING</h4>';
		str += '<h2> <a href="javascript:{getBoothsDetailsByStatusAndConstituency(4)}">'+result.dvProcessingCount+'</a></h2>';
		str += '</hgroup>';
		str += '</li>';

		str += '<li>';
		str += '<hgroup>';
		str += '<h4>VERIFICATION COMPLETED</h4>';
		str += '<h2> <a href="javascript:{getBoothsDetailsByStatusAndConstituency(5)}">'+result.dvCompletedCount+'</a></h2>';
		str += '</hgroup>';
		str += '</li>';

		str += '<li>';
		str += '<hgroup>';
		str += '<h4>ACTUAL VERIFICATION PROCESSING</h4>';
		str += '<h2> <a href="javascript:{getBoothsDetailsByStatusAndConstituency(6)}">'+result.actualProcessingCount+'</a></h2>';
		str += '</hgroup>';
		str += '</li>';

		str += '<li>';
		str += '<hgroup>';
		str += '<h4>PANCHAYAT PROCESSING</h4>';
		str += '<h2> <a href="javascript:{getpanchayatDetailsByStatusAndConstituency(1)}">'+result.panchayatDetails.processingCount+'</a></h2>';
		str += '</hgroup>';
		str += '</li>';

		str += '<li>';
		str += '<hgroup>';
		str += '<h4>PANCHAYAT COMPLETED</h4>';
		str += '<h2> <a href="javascript:{getpanchayatDetailsByStatusAndConstituency(2)}">'+result.panchayatDetails.count+'</a></h2>';
		str += '</hgroup>';
		str += '</li>';


		str+='</ul>';
		str+='</div>';
		str+='</div>';
		str+='</div>';

	$('#basicStatusReport').html(str);
	getMatchecUnMatchedDetails();
}
function getpanchayatDetailsByStatusAndConstituency(statusId)
{
	var status = '';
	if(statusId == 1)
	{
		status = 'process';
	}
	else
	{
		status = 'completed';
	}
	var jObj =
	{
	  status:status
	};
	$('#tableDtailsDiv').html('');
	$("#panchayatDetailsDiv").html('');
	$('#statusAjaxImg').show();
	var jObj =
	{
	  constituencyId:$('#reportConstituencyId').val() ,
	  status:status
	};

	 $.ajax({
			type:'GET',
			url: 'getPanchayatsStatusDetailsAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		  }).done(function(result){				
			buildStatusForPanchayatDetials(result,status);	
		});
}


function getBoothsDetailsInCallStatusInfo(constituencyId,divId)
{
	$("#boothImage").show();
	var jObj =
	{
	  constituencyId:constituencyId     
	};

	 $.ajax({
			type:'GET',
			url: 'getBoothsDetailsInCallStatusAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		  }).done(function(result){				
				//buildDayWiseReportByUserType(result);		
				$("#boothImage").hide();
				$('#'+divId+'').find('option').remove();

				$.each(result,function(index,value){
					$('#'+divId+'').append('<option value="'+value.id+'">Booth - '+value.name+'</option>');
				});
				
				$('#'+divId+'').multiselect('refresh');

		});	
}
function getMatchecUnMatchedDetails()
{
	//$('#stateStatusAjax').show();
	var jObj =
	{
	  constituencyId:$('#reportConstituencyId').val()     
	};

	 $.ajax({
			type:'GET',
			url: 'getBoothsDetailsInSurveyDetailsInfoAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		  }).done(function(result){	
			if(result != null)
			{
				var boothIds = new Array();
				for(var i in result)
				{
					boothIds.push(result[i].id);
				}
				getMatchedUnMatchedCountsByBoothWise(boothIds);
			}
			
		});	
}
function getMatchedUnMatchedCountsByBoothWise(boothIds)
{
		var jObj =
		{
			boothIds:boothIds     
		};
		
		$.ajax({
			type:'GET',
			url: 'checkForVerifierDataAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		 }).done(function(result){	
		  
		if(result != null)
		{
			buildMatchecdUnMatchedDetails(result);
		}	
		});	
}

function buildMatchecdUnMatchedDetails(result)
{
	var str = '';
	str += '<table class="table table-bordered m_top20 table-hover table-striped" id="matchedUnMatchedTable">';
	str += '<thead class="alert alert-success">';
	str += '<tr>';
	str += '<th>BOOTH</th>';
	str += '<th>DC NAME</th>';
	str += '<th>DC DATE</th>';
	str += '<th>DV NAME</th>';
	str += '<th>DV DATE</th>';
	str += '<th>TOTAL</th>';
	str += '<th>MATCHED</th>';
	str += '<th>MATCHED %</th>';
	str += '<th>UN MATCHED</th>';
	str += '<th>UN MATCHED %</th>';
	str += '<th>NOT VERIFIED</th>';
	str += '<th>NOT VERIFIED %</th>';
	str += '</tr>';
	str += '</thead><tbody>';
	for(var i in result)
	{
		str += '<tr>';
		if(result[i].matchedList[0] != null) 
		str+= '<td>'+result[i].matchedList[0].partNo+'</td>';
		else if(result[i].unMatchedList[0] != null)
		str+= '<td>'+result[i].unMatchedList[0].partNo+'</td>';
		else
		str+= '<td>'+result[i].notVerifiedList[0].partNo+'</td>';
		str+= '<td>'+result[i].surveyUser+'</td>';
		str+= '<td>'+result[i].date+'</td>';
		str+= '<td>'+result[i].verifierUser+'</td>';
		str+= '<td>'+result[i].verifierDate+'</td>';
		str+= '<td>'+result[i].totalCount+'</td>';
		str+= '<td>'+result[i].matchedCount+'</td>';
		str+= '<td>'+(Math.round(result[i].matchedCount * 100)/result[i].totalCount).toFixed(2)+'</td>';
		str+= '<td>'+result[i].unMatchedCount+'</td>';
		str+= '<td>'+(Math.round(result[i].unMatchedCount * 100)/result[i].totalCount).toFixed(2)+'</td>';
		str+= '<td>'+result[i].notVerifiedCount+'</td>';
		str+= '<td>'+(Math.round(result[i].notVerifiedCount * 100)/result[i].totalCount).toFixed(2)+'</td>';
		str += '</tr>';
	}
	str += '</tbody>';
	str += '</table>';
	$('#tableForMatchedAndUnMatched').html(str);
	$('#matchedUnMatchedTable').dataTable({
		"iDisplayLength": 30,
		"aLengthMenu": [[30, 60, 100, -1], [30, 60, 100, "All"]]
		});
	//$('#stateStatusAjax').hide();
}
function getVerfierDetails(buildType,imgId,buildDiv)
{
	$('#'+imgId+'').show();
	$('#'+buildDiv+'').html('');
	var boothIds = new Array();
	
	if(buildType == 1)
	{
		var boothId = $('#boothIdForVerifier').val();
		boothIds.push(boothId);
		var jObj =
		{
			boothIds:boothIds     
		};
		
		$.ajax({
			type:'GET',
			url: 'checkForVerifierDataAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		 }).done(function(result){	
		  
		if(result != null)
		{
			buildVerifierOrWMReport(result,buildType,buildDiv,imgId);
		}
		else
		{
			$('#'+buildDiv+'').html('<b style="color:red">No Data Avaliable</b>');
			$('#'+imgId+'').hide();
		}		
		});	
	}
	else
	{
		var jObj =
		{
			boothId:$('#boothIdForWm').val()     
		};
		
		$.ajax({
			type:'GET',
			url: 'checkForWebMonitorDataAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		 }).done(function(result){	
		  
		if(result != null)
		{
			buildVerifierOrWMReport(result,buildType,buildDiv,imgId);
		}
		else
		{
	
			$('#'+buildDiv+'').html('<b style="color:red">No Data Avaliable</b>');
			$('#'+imgId+'').hide();
		}		
		});	
	}

		
}

function getBoothsDetailsInSurveyDetailsInfo(constituencyId,divId)
{

	$("#boothImage").show();
	var jObj =
	{
	  constituencyId:constituencyId     
	};

	 $.ajax({
			type:'GET',
			url: 'getBoothsDetailsInSurveyDetailsInfoAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		  }).done(function(result){				
				//buildDayWiseReportByUserType(result);		
				$("#boothImage").hide();
				$('#'+divId+'').find('option').remove();

				$.each(result,function(index,value){
					$('#'+divId+'').append('<option value="'+value.id+'">Booth - '+value.name+'</option>');
				});
				
				$('#'+divId+'').multiselect('refresh');

		});	
}


function getCasteWiseVotersListByCaste(casteId,webConstId,userId,boothId,date,userType)
{

$('#voterInfoDIv').html('');
$('#searchDataImg').show();
		var jsObj = 
		{
			constituencyId:webConstId,
			surveyUserId:userId,
			boothId : boothId,
			searchDate:"22-07-2014",
			casteId :casteId,
			userType : userType,
			task : "getSurveyVotersListByCaste"
		};

	$.ajax({
		type:'GET',
		url: 'getCastewiseSurveyVotersListAction.action',
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		}).done(function(results){
		$('#searchDataImg').hide();
				buildCasteWiseVotersList(results,userId,boothId,date,casteId,webConstId,userType);
		});
		
}

function buildCasteWiseVotersList(results,userId,boothId,date,casteId1,webConstId1,userType){
		var str = '';
		var totalVoters = 0;
		var isCasteAvalbl = false;
		var isMobileAvalbl = false;
		totalVoters = results != null ? results[0].count:0;
		$('#webMontrId').show();
		if(results != null && results[0].subList.length>0)
		{
		
				var result = results[0].subList;
				
				str +='<table class="table table-bordered m_top20 table-hover table-striped" id="voterDetlsTab" >';
				str +='	<thead class="alert alert-success">';
				str +='	<tr>';
				str +='<th>  <input type="checkbox" id="alluserChkbx" class="allCheckbxCls" onclick="selectAllCheckBoxes();"/> Select All </th>';
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
					str +='<td> <input type="checkbox" id="userChkbx" value="'+result[i].voterId+'"class="checkbxCls" /></td>';
					str +='<td>'+result[i].serailNo+'</td>';
					str +='<td>'+result[i].partNo+'</td>';
					str +='<td>'+result[i].userName+'</td>';
					str +='<td>'+result[i].voterName+'</td>';
				
					/* starting  caste available and not available details */
					
			
					
					if(result[i].caste.trim().length >0)
					{
						str +='<td>'+result[i].caste+'';
					
					
				
						if(result[i].casteMatchedCount == 1) //correct
						{ 
							str +='<div data-toggle="buttons-radio" class="btn-group">';
							str +='<button class="btn btn-mini  btn-success " id="updateBtnId'+i+'" type="button" onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',1,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">correct</button>';
							str +='<button class="btn btn-mini " id="updateBtnIdA'+i+'" type="button" onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',0,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >wrong</button>';
							str +='<div id="casteErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
							
							str +='<input type="hidden" value="1" id="isCasteMatched'+i+'"/>';
							
							str +='</div>';
							str +='</td>';
							
							str +='<td> <select id="casteListId'+i+'"  disabled="disabled" style="width:165px;" onchange="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',10,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');"><option value="0"> Select Caste </option> ';
							str +='</select></td>';	
												
						}
						else if(result[i].casteMatchedCount == 2)
						{
							str +='	<div data-toggle="buttons-radio" class="btn-group">';
							
							str +='	<button class="btn btn-mini " id="updateBtnId'+i+'" type="button"  onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',1,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >correct</button>';
							
							str +='	<button class="btn btn-mini  btn-warning " id="updateBtnIdA'+i+'" type="button"  onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',0,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">wrong</button>';
							
							str +='<div id="casteErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
							str +='<input type="hidden" value="0" id="isCasteMatched'+i+'"/>';
							str +='</div>';
							str +='</td>';
							
							str +='<td> <select id="casteListId'+i+'"   style="width:165px;" onchange="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',10,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');"><option value="0"> Select Caste </option> ';
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
							
							str +='<button class="btn btn-mini " id="updateBtnId'+i+'" type="button" onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',1,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >correct</button>';
							
							str +='<button class="btn btn-mini " id="updateBtnIdA'+i+'" type="button" onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',0,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >wrong</button>';
							
							str +='<div id="casteErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
							str +='<input type="hidden" value="" id="isCasteMatched'+i+'"/>';
							str +='</div>';
							str +='</td>';
							
							str +='<td> <select id="casteListId'+i+'"  disabled="disabled" style="width:165px;" onchange="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',10,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');"><option value="0"  > Select Caste </option> ';
							str +='</select></td>';	
						}			
						isCasteAvalbl = true;
		
					}
					else{
						str +='<td>';
						str +='<input type="hidden" value="" id="isCasteMatched'+i+'"/>';
						str +='</td>';
						
							str +='<td> <select id="casteListId'+i+'"   style="width:165px;" onchange="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',10,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');"><option value="0"> Select Caste </option> ';
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
											str +='<button class="btn btn-mini  btn-success " id="updateMBtnId'+i+'" type="button" onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',2,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >correct </button>';
											
											str +='<button class="btn btn-mini "  id="updateMBtnIdA'+i+'" type="button" onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',3,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">wrong</button>';
											
											str +='<div id="mobileErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
											str +='<input type="hidden" value="2" id="isTestedMobile'+i+'"/>';
											str +='</div>';
														
										}
										else if(result[i].mobileMatchedCount == 2)
										{

											str +='<button class="btn btn-mini "  id="updateMBtnId'+i+'" type="button" onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',2,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">correct </button>';
											
											str +='<button class="btn btn-mini  btn-warning " id="updateMBtnIdA'+i+'" type="button"  onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',3,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">wrong</button>';
											
											str +='<div id="mobileErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
											str +='<input type="hidden" value="3" id="isTestedMobile'+i+'"/>';
											str +='</div>';
														
										}
										else
										{	

											str +='<button class="btn btn-mini "  type="button" id="updateMBtnId'+i+'" onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',2,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >correct </button>';
										
											str +='<button class="btn btn-mini "  id="updateMBtnIdA'+i+'" type="button" onclick="updateDetails(\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',3,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">wrong</button>';
										
											str +='<div id="mobileErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
											str +='<input type="hidden" id="isTestedMobile'+i+'"  value=""/>';
											str +='</div>';
										}

											str +='</td>';
											
											isMobileAvalbl = true;
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
																	
				}
				str +='</tbody>';
				str +='</table>';
								
								
		}
		else
		{
			str +='	No Data Available. ';
		}
		
		str +='<div id="scrollBtnDiv" >';
		str +='<a id="updateBtnInNewWndow" style="position: fixed; left :0px; top: 320px;" href="javascript:{updateVoterDetlsStatus('+userId+','+boothId+','+casteId1+','+webConstId1+','+date+',\''+isCasteAvalbl+'\',\''+isMobileAvalbl+'\','+userType+');}" class="btn btn-primary"> Update Details </a>';
		str +='</div>';

		$('#scrollBtnDiv').show();
		
		$('#voterInfoDIv').html(str);
		$('#voterDetlsTab').dataTable({
		"iDisplayLength": 100,
		"aLengthMenu": [[100, 200, 500, -1], [100, 200, 500, "All"]]
		});
		
}

function updateVoterDetlsStatus(userId,boothId,casteId1,webConstId1,date,userType){
	
	var voterIdsArr = new Array();

	$('.checkbxCls').each(function(){
		if($(this).is(':checked')){
			voterIdsArr.push($(this).val());
		}
	});

	if(voterIdsArr != null && voterIdsArr.length == 0){
		alert("Please select atleast one voter.");
		return;
	}
	
$('#finalInputDiv').html('');
	
	$("#editStatusDiv").dialog({
		width:600,
		height:220,
		modal: true,
		resizable: false,
		title:" Update Voters Caste and Mobile details"
	});

	var str='';
	str +='<div class="row">';
	str +='<div id="finalErrDiv" style="color:#FF0020;margin-left: 100px;"></div>';
		str +='<div class="offset1">';
		str +='<input type="hidden" id="casteStatusId" value=""/>';
		str +='<input type="hidden" id="mobileStatusId" value=""/>';

		str +='<div class="row-fluid">';
				str +='<div class="span4">';
					str +='Caste Status : <font class="requiredFont">*</font>';
						str +='<div data-toggle="buttons-radio" class="btn-group">';	
							str +='<button class="btn btn-mini selectCls1" name="selectCls1" id="correct1" onclick="changeSelectionStatus(\'correct1\',1,\'casteStatusId\')">correct </button>';
							str +='<button class="btn btn-mini selectCls1" name="selectCls1"  id="wrong1"  onclick="changeSelectionStatus(\'wrong1\',0,\'casteStatusId\')" >wrong</button>';							
						str +='</div>';
				str +='</div>';
		/*		
		str +='<div class="span4">';
			str +='Mobile Status : <font class="requiredFont">*</font>';
			str +='<div data-toggle="buttons-radio" class="btn-group">	';
				str +='<button class="btn btn-mini selectCls2" id="correct2"  name="selectCls2"  onclick="changeSelectionStatus(\'correct2\',2,\'mobileStatusId\')" >correct </button>';
				str +='<button class="btn btn-mini selectCls2" id="wrong2" name="selectCls2"  onclick="changeSelectionStatus(\'wrong2\',3,\'mobileStatusId\')" >wrong</button>';			
			str +='</div>';
		str +='</div>';
		
	str +='</div>';
	str +='</div>';
	*/
		str +='<div class="span4">';
			str +='Select Caste : <font class="requiredFont">*</font>';
			str +='<select id="finalCasteList" disabled="disabled"> ';
			str +='<option value="0"> Select Caste </option>';

			if(newCasteArr.length > 0)
			{			
				for(var i in newCasteArr)
				{
					str +='<option value="'+newCasteArr[i].id+'">'+newCasteArr[i].value+'</option>';
				}
			}
			str +='</select>';
		str +='</div>';
		
		
	str +='</div>';
	str +='</div>';
		str +='<div class="offset1">';
			str +='<div class="row-fluid">';
				str +='<div class="span6">';
					str +='<button class="btn btn-success" onClick="saveVoterCasteAndMobileDetals(\''+voterIdsArr+'\','+userId+','+boothId+','+casteId1+','+webConstId1+','+date+','+userType+');" style="float:right;"> Submit </button>';
				str +='</div>';
			str +='</div>';
		str +='</div>';
	str +='</div>';
	str +='</div>';
		str +='<div class="offset1">';
			str +='<div class="row-fluid">';
				str +='<div class="span">';
						str +='<div id="saviStatusDiv" style="color:#008000;display:none;" > Please wait survey details are updating...</div>';
				str +='</div>';
			str +='</div>';
		str +='</div>';
	str +='</div>';

							
	$('#finalInputDiv').html(str);
		
		
}

function changeSelectionStatus(btnId,value,filedId){
	
		var className = $('#'+btnId).attr('name');
		//console.log(className);
		$('.'+className).removeClass('btn-success');
		$('.'+className).removeClass('btn-warning');
	$('#'+filedId).val(value);
	if(value == 1 || value == 2){	
		$('#finalCasteList').prop('disabled','disabled');
		$('#'+btnId).addClass('btn-success');		
	}
	else if(value == 0 || value == 3){
	$('#finalCasteList').prop('disabled',false);
		$('#'+btnId).addClass('btn-warning');
	}
}




function saveVoterCasteAndMobileDetals(voterIds,userId,boothId,casteId1,webConstId1,date,userType){

	var voterInfoArr = [];
	var votersArr = voterIds.split(',');
	//var isMobileVerified = $('#mobileStatusId').val();
	var isMatched = $('#casteStatusId').val() ;
	var casteId = $('#finalCasteList').val() ;
	$('#finalErrDiv').html('');
	if(isMatched.trim().length == 0){
		isMatched = 5;
	}
	/*if(isMobileVerified.trim().length == 0){
		isMobileVerified = 6;
	}*/

	if(voterIds.trim().length == 0){
		$('#finalErrDiv').html('Please select atlease one voter.');
		return;
	}
	if(isMatched == 5 && casteId == 0){
		$('#finalErrDiv').html('Please update atlease one element.');
		return;
	}
	
	$('#saviStatusDiv').show();
	
	if(votersArr != null && votersArr.length >0)
	{
		for(var i in votersArr){
			var obj = {
				voterId:votersArr[i],
				surveyUserId:userId,
				isMobileVerified:6,
				isMatched :isMatched,
				boothId : boothId,
				isHamletMatched:0,
				hamletId:0,
				casteId:casteId,
				userType : userType
			}	
			
			voterInfoArr.push(obj);
		}
	}
	
	//console.log(voterInfoArr);
	var jsObj = 
	{
		voterInfoArr:voterInfoArr,
		userType : userType,
		task : "getSurveyVotersList"
	}

	$.ajax({
		type:'POST',
		url: 'saveSurveyCallStatusDetilsAction.action',
		data: {task:JSON.stringify(jsObj)},
		}).done(function(result){
			if(result != null && result.resultCode == 0){
				$('#saviStatusDiv').html(' <span style="color:#008000;">Survey details successfully updated...');
				$("#editStatusDiv").dialog('close');
				getCasteWiseVotersListByCaste(casteId1,webConstId1,userId,boothId,date);
			}
			else{
				$('#finalErrDiv').html(' Error occured while updating details.');
			}
		});

}

function selectAllCheckBoxes(){
	
	if($('.allCheckbxCls').is(':checked')){
		$('.checkbxCls').each(function(){
			$(this).prop('checked', true);
		});
	}
	else{
		$('.checkbxCls').each(function(){
			$(this).prop('checked', false);
		});
	}
	
	
}


function getFieldReportSummary()
{
	var jsObj = {
		task : "constituencySummary"
	}
	$("#fieldReportSummaryimg").css("display","block");
 $.ajax({
			type:'GET',
			url: 'getConstituencySummaryFieldReport.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		  }).done(function(result){
			 $("#fieldReportSummaryimg").css("display","none");
			  if(result.length > 0)
			 buildSummaryData(result);
			
		});	

}

function buildSummaryData(result)
{

			var str ='';
			  
				  str+='<table class="table table-bordered m_top20 table-hover table-striped">';
				  str+='<thead class="alert alert-success">';
				  str+='<th>Constituency</th>';	
				  str+='<th>Leader</th>';	
				  str+='<th>Total</th>';
				  str+='<th>Active</th>';
				  str+='<th>In Active</th>';
				 
				 str+='</thead>';
				 str+='<tbody>';
				  for(var i in result)
				  {
					  for(var j in result[i].subList)
					  {
					  str+='<tr>';
					str+='<td>'+result[i].name+'</td>';
					str+='<td>'+result[i].subList[j].name+'</td>';
					str+='<td>'+result[i].subList[j].total+'</td>';
					
					str+='<td>'+result[i].subList[j].activeUsersCount+'</td>';
					str+='<td>'+result[i].subList[j].inActiveUsersCount+'</td>';
					str+='</tr>';
					  }
				  }
				   str+='</tbody>';
				  str+='</table>';
				  $("#fieldReportSummary").html(str);
			
}

function getVerfierDetailsWithVerifier(){
	var imgId = "mainajaximgForVerifier";
	var buildDiv = "dayWiseReportDivForVerifier";
	var buildType =1;
	$('#'+imgId+'').show();
	$('#'+buildDiv+'').html('');
	var boothIds = new Array();
		var boothId = $('#boothIdForVerifier').val();
		var verifierId = $('input[name=verifiers]:checked').val();
		
		boothIds.push(boothId);
		
		var jObj ={
			boothIds:boothIds,
			verifierId:verifierId
		};
		
		$.ajax({
			type:'GET',
			url: 'checkForVerifierDataWithVeriferAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		 }).done(function(result){	
		  
		if(result != null)
		{
			buildVerifierOrWMReport(result,buildType,buildDiv,imgId);
		}
		else
		{
			$('#'+buildDiv+'').html('<b style="color:red">No Data Avaliable</b>');
			$('#'+imgId+'').hide();
		}		
		});	

}
//getThirdPartyVerificationDetails();
function getThirdPartyVerificationDetails()
{
	var jsObj = 
	{
		boothId : 439961
	}
	$.ajax({
			type:'GET',
			url: 'getThirdPartyVerificationDetailsAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)},
		 }).done(function(result){	
		 
			var str = '';
			str += '<table>';
			str += '<tr>';
			str += '<th>Name</th>';
			str += '<th>Caste</th>';
			str += '</tr>';
			for(var i in result)
			{
				str += '<tr>';
				str += '<td>'+result[i].voterName+'</td>';
				str += '<td>'+result[i].casteName+'</td>';
				str += '</tr>';
			}
			str += '</table>';
			$('#tableForMatchedAndUnMatched').html(str);
		});	
}