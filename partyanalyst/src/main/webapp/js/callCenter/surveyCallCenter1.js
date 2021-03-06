
var newCasteArr;
var newHamletsArr;
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
			userType : userType,
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
			var surveyArea = results[0].status;
		$('#webMontrId').show();
		if(results != null && results[0].subList.length>0)
		{
		
				var result = results[0].subList;
						str +='<input type="hidden" value="'+surveyArea+'" id="surveyArea"/>';
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
					str +='<th>DC WM CASTE</th>';
					str +='<th>MATCHED STATUS</th>';
					str +='<th> DV Caste </th>';
				}
				else
				{
				str +='<th> Caste </th>';				
				}
				str +='<th> Caste Matched  </th>';
				str +='<th> Mobile Number </th>';
				str +='<th> Hamlet/ Ward </th>';
				str +='<th> Hamlet/ Ward Matched </th>';
				str +='<th> H.NO With Diff Castes </th>';
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
							if(result[i].dcCaste == null){
								str +='<li> - </li>';
							}else{
								str +='<li>'+result[i].dcCaste+'</li>';					
							}
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
							if(result[i].wmCaste == null){
								str +='<li> - </li>';
							}else{
								str +='<li>'+result[i].wmCaste+'</li>';					
							}
							str +='</ul>';
							str +='</div>';
							str +='</td>';
							
							str +='<td>';
							str +='<div>';
							str +='<ul class="unstyled inline">';
							if(result[i].status == null){
								str +='<li> - </li>';
							}else{
								str +='<li>'+result[i].status+'</li>';				
							}							
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
							str +='<button class="btn btn-mini  btn-success " id="updateBtnId'+i+'" type="button" onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',1,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">correct</button>';
							str +='<button class="btn btn-mini " id="updateBtnIdA'+i+'" type="button" onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',0,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >wrong</button>';
							str +='<div id="casteErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
							
							str +='<input type="hidden" value="1" id="isCasteMatched'+i+'"/>';
							
							str +='</div>';
							str +='</td>';
							
							str +='<td> <select id="casteListId'+i+'"  disabled="disabled" style="width:165px;" onchange="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',10,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');"><option value="0"> Select Caste </option> ';
							str +='</select></td>';	
												
						}
						else if(result[i].casteMatchedCount == 2)
						{
							str +='	<div data-toggle="buttons-radio" class="btn-group">';
							
							str +='	<button class="btn btn-mini " id="updateBtnId'+i+'" type="button"  onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',1,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >correct</button>';
							
							str +='	<button class="btn btn-mini  btn-warning " id="updateBtnIdA'+i+'" type="button"  onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',0,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">wrong</button>';
							
							str +='<div id="casteErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
							str +='<input type="hidden" value="0" id="isCasteMatched'+i+'"/>';
							str +='</div>';
							str +='</td>';
							
							str +='<td> <select id="casteListId'+i+'"   style="width:165px;" onchange="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',10,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');"><option value="0"> Select Caste </option> ';
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
							
							str +='<button class="btn btn-mini " id="updateBtnId'+i+'" type="button" onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',1,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >correct</button>';
							
							str +='<button class="btn btn-mini " id="updateBtnIdA'+i+'" type="button" onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',0,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >wrong</button>';
							
							str +='<div id="casteErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
							str +='<input type="hidden" value="" id="isCasteMatched'+i+'"/>';
							str +='</div>';
							str +='</td>';
							
							str +='<td> <select id="casteListId'+i+'"  disabled="disabled" style="width:165px;" onchange="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',10,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');"><option value="0"  > Select Caste </option> ';
							str +='</select></td>';	
							
							
						}			
					}
					else{
						str +='<td>';
						str +='<input type="hidden" value="" id="isCasteMatched'+i+'"/>';
						str +='</td>';
						
							str +='<td> <select id="casteListId'+i+'"   style="width:165px;" onchange="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',10,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');"><option value="0"> Select Caste </option> ';
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
			
								str +='<td>';
													
										str +='<ul>';
										
						if(result[i].mobileNo != null  && result[i].mobileNo.length != 0 )
							{						
										var mobNo = result[i].mobileNo	;
													
									str +='<li style="background-color:#D3D6FF;margin-top:8px;">';
									str +=''+mobNo+'';
									if(result[i].mobileStatus != null)
									{
										str +='<input type="hidden" id="isTestedMobile'+i+'"  value=""/>';
										str +='<div data-toggle="buttons-radio"> Invalid Number </div>';
									}
									else
									{		
										str +='<div data-toggle="buttons-radio" class="btn-group">';										
										if(result[i].ctpMobileStatus == 1)
										{
											str +='<button class="btn btn-mini  btn-success " id="updateMBtnId'+i+'" type="button" onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',2,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >correct </button>';
											
											str +='<button class="btn btn-mini "  id="updateMBtnIdA'+i+'" type="button" onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',3,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">wrong</button>';
											
											str +='<div id="mobileErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
											str +='<input type="hidden" value="2" id="isTestedMobile'+i+'"/>';
											str +='</div>';
														
										}
										else if(result[i].ctpMobileStatus == 2)
										{

											str +='<button class="btn btn-mini "  id="updateMBtnId'+i+'" type="button" onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',2,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">correct </button>';
											
											str +='<button class="btn btn-mini  btn-warning " id="updateMBtnIdA'+i+'" type="button"  onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',3,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">wrong</button>';
											
											str +='<div id="mobileErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
											str +='<input type="hidden" value="3" id="isTestedMobile'+i+'"/>';
											str +='</div>';
														
										}
										else
										{	

											str +='<button class="btn btn-mini "  type="button" id="updateMBtnId'+i+'" onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',2,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >correct </button>';
										
											str +='<button class="btn btn-mini "  id="updateMBtnIdA'+i+'" type="button" onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',3,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">wrong</button>';
										
											str +='<div id="mobileErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
											str +='<input type="hidden" id="isTestedMobile'+i+'"  value=""/>';
											str +='</div>';
											
										}
									}
										//str +='(BY CTP)';
								}	
							else{	
									str +='<li>';
									str +='<input type="hidden" value="" id="isTestedMobile'+i+'"/>';
								}	
								str +='</li>';
															
						if(result[i].ceoMobileNoList != null  && result[i].ceoMobileNoList.length > 0 )
							{						
										var mobNo = result[i].ceoMobileNoList[0];
										str +='<li style="background-color:#D3D6FF;margin-top:8px;">';							
										str +=''+mobNo+'<div data-toggle="buttons-radio" class="btn-group">';
																			
										if(result[i].ceoMobileStatus == 1)
										{
											str +='<button class="btn btn-mini  btn-success " id="updateceoMBtnId'+i+'" type="button" onclick="updateMobileDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isceoTestedMobile'+i+'\',1,'+result[i].voterId+','+result[i].userid+',\'ceoMobileNumber\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >correct </button>';
											
											str +='<button class="btn btn-mini "  id="updateceoMBtnIdA'+i+'" type="button" onclick="updateMobileDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isceoTestedMobile'+i+'\',0,'+result[i].voterId+','+result[i].userid+',\'ceoMobileNumber\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">wrong</button>';
											
											str +='<div id="mobileErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
											str +='<input type="hidden" value="1" id="isceoTestedMobile'+i+'"/>';
											str +='</div>';
														
										}
										else if(result[i].ceoMobileStatus == 2)
										{

											str +='<button class="btn btn-mini "  id="updateceoMBtnId'+i+'" type="button" onclick="updateMobileDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isceoTestedMobile'+i+'\',1,'+result[i].voterId+','+result[i].userid+',\'ceoMobileNumber\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">correct </button>';
											
											str +='<button class="btn btn-mini  btn-warning " id="updateceoMBtnIdA'+i+'" type="button"  onclick="updateMobileDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isceoTestedMobile'+i+'\',0,'+result[i].voterId+','+result[i].userid+',\'ceoMobileNumber\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">wrong</button>';
											
											str +='<div id="mobileErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
											str +='<input type="hidden" value="0" id="isceoTestedMobile'+i+'"/>';
											str +='</div>';
														
										}
										else
										{	

											str +='<button class="btn btn-mini "  type="button" id="updateceoMBtnId'+i+'" onclick="updateMobileDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isceoTestedMobile'+i+'\',1,'+result[i].voterId+','+result[i].userid+',\'ceoMobileNumber\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >correct </button>';
										
											str +='<button class="btn btn-mini "  id="updateceoMBtnIdA'+i+'" type="button" onclick="updateMobileDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isceoTestedMobile'+i+'\',0,'+result[i].voterId+','+result[i].userid+',\'ceoMobileNumber\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">wrong</button>';
										
											str +='<div id="mobileErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
											str +='<input type="hidden" id="isceoTestedMobile'+i+'"  value=""/>';
											str +='</div>';
											
										}

									//str +='(BY CEO)';	
								}	
							else{
									str +='<li>';		
											str +='<input type="hidden" value="" id="isceoTestedMobile'+i+'"/>';
																					

								}
								str +='</li>';	
								
						if(result[i].surveyMobileNoList != null  && result[i].surveyMobileNoList.length > 0 )
							{			
										str +='<li style="background-color:#D3D6FF;margin-top:8px;">';	
										var mobNo = result[i].surveyMobileNoList[0];
									
										str +=''+mobNo+'<div data-toggle="buttons-radio" class="btn-group">';
																			
										if(result[i].surveyMobileStatus == 1)
										{
											str +='<button class="btn btn-mini  btn-success " id="updateSurveyMBtnId'+i+'" type="button" onclick="updateMobileDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isSurveyTestedMobile'+i+'\',2,'+result[i].voterId+','+result[i].userid+',\'surveyMobileNumber\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >correct </button>';
											
											str +='<button class="btn btn-mini "  id="updateSurveyMBtnIdA'+i+'" type="button" onclick="updateMobileDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isSurveyTestedMobile'+i+'\',3,'+result[i].voterId+','+result[i].userid+',\'surveyMobileNumber\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">wrong</button>';
											
											str +='<div id="mobileErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
											str +='<input type="hidden" value="2" id="isSurveyTestedMobile'+i+'"/>';
											str +='</div>';
														
										}
										else if(result[i].surveyMobileStatus == 2)
										{

											str +='<button class="btn btn-mini "  id="updateSurveyMBtnId'+i+'" type="button" onclick="updateMobileDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isSurveyTestedMobile'+i+'\',2,'+result[i].voterId+','+result[i].userid+',\'surveyMobileNumber\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">correct </button>';
											
											str +='<button class="btn btn-mini  btn-warning " id="updateSurveyMBtnIdA'+i+'" type="button"  onclick="updateMobileDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isSurveyTestedMobile'+i+'\',3,'+result[i].voterId+','+result[i].userid+',\'surveyMobileNumber\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">wrong</button>';
											
											str +='<div id="mobileErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
											str +='<input type="hidden" value="3" id="isSurveyTestedMobile'+i+'"/>';
											str +='</div>';
														
										}
										else
										{	

											str +='<button class="btn btn-mini "  type="button" id="updateSurveyMBtnId'+i+'" onclick="updateMobileDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isSurveyTestedMobile'+i+'\',2,'+result[i].voterId+','+result[i].userid+',\'surveyMobileNumber\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >correct </button>';
										
											str +='<button class="btn btn-mini "  id="updateSurveyMBtnIdA'+i+'" type="button" onclick="updateMobileDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isSurveyTestedMobile'+i+'\',3,'+result[i].voterId+','+result[i].userid+',\'surveyMobileNumber\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">wrong</button>';
										
											str +='<div id="mobileErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
											str +='<input type="hidden" id="isSurveyTestedMobile'+i+'"  value=""/>';
											str +='</div>';
											
										}
										//	str +='(BY SURVEY)';
								}	
							else{
								str +='<li>';
											str +='<input type="hidden" value="" id="isSurveyTestedMobile'+i+'"/>';
	
								}
							str +='</li>';	
							if(result[i].dataMobileNoList != null  && result[i].dataMobileNoList.length > 0 )
							{				
									str +='<li style="background-color:#D3D6FF;margin-top:8px;">';	
										var mobNo = result[i].dataMobileNoList[0];
								
										str +=''+mobNo+'<div data-toggle="buttons-radio" class="btn-group">';
																			
										if(result[i].dataMobileStatus == 1)
										{
											str +='<button class="btn btn-mini  btn-success " id="updateDataMBtnId'+i+'" type="button" onclick="updateMobileDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isDataTestedMobile'+i+'\',4,'+result[i].voterId+','+result[i].userid+',\'dataMobileNumber\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >correct </button>';
											
											str +='<button class="btn btn-mini "  id="updateDataMBtnIdA'+i+'" type="button" onclick="updateMobileDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isDataTestedMobile'+i+'\',5,'+result[i].voterId+','+result[i].userid+',\'dataMobileNumber\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">wrong</button>';
											
											str +='<div id="mobileErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
											str +='<input type="hidden" value="4" id="isDataTestedMobile'+i+'"/>';
											str +='</div>';
														
										}
										else if(result[i].dataMobileStatus == 2)
										{

											str +='<button class="btn btn-mini "  id="updateDataMBtnId'+i+'" type="button" onclick="updateMobileDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isDataTestedMobile'+i+'\',4,'+result[i].voterId+','+result[i].userid+',\'dataMobileNumber\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">correct </button>';
											
											str +='<button class="btn btn-mini  btn-warning " id="updateDataMBtnIdA'+i+'" type="button"  onclick="updateMobileDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isDataTestedMobile'+i+'\',5,'+result[i].voterId+','+result[i].userid+',\'dataMobileNumber\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">wrong</button>';
											
											str +='<div id="mobileErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
											str +='<input type="hidden" value="5" id="isDataTestedMobile'+i+'"/>';
											str +='</div>';
														
										}
										else
										{	

											str +='<button class="btn btn-mini "  type="button" id="updateDataMBtnId'+i+'" onclick="updateMobileDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isDataTestedMobile'+i+'\',4,'+result[i].voterId+','+result[i].userid+',\'dataMobileNumber\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >correct </button>';
										
											str +='<button class="btn btn-mini "  id="updateDataMBtnIdA'+i+'" type="button" onclick="updateMobileDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isDataTestedMobile'+i+'\',5,'+result[i].voterId+','+result[i].userid+',\'dataMobileNumber\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">wrong</button>';
										
											str +='<div id="mobileErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
											str +='<input type="hidden" id="isDataTestedMobile'+i+'"  value=""/>';
											str +='</div>';
											
										}
										
									//	str +='(BY DATA)';
								}	
							else{
									str +='<li>';
											str +='<input type="hidden" value="" id="isDataTestedMobile'+i+'"/>';
								}
									str +='</li>';
										str +='</ul>';
								
								str +='</td>';
						
						/*

							var mobNo = '';
							if(result[i].mobileNo != null  && result[i].mobileNo.length != 0 )
							{						
										mobNo = result[i].mobileNo					
										str +='<td>'+mobNo+'<div data-toggle="buttons-radio" class="btn-group">';
																			
										if(result[i].mobileMatchedCount == 1)
										{
											str +='<button class="btn btn-mini  btn-success " id="updateMBtnId'+i+'" type="button" onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',2,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >correct </button>';
											
											str +='<button class="btn btn-mini "  id="updateMBtnIdA'+i+'" type="button" onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',3,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">wrong</button>';
											
											str +='<div id="mobileErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
											str +='<input type="hidden" value="2" id="isTestedMobile'+i+'"/>';
											str +='</div>';
														
										}
										else if(result[i].mobileMatchedCount == 2)
										{

											str +='<button class="btn btn-mini "  id="updateMBtnId'+i+'" type="button" onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',2,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">correct </button>';
											
											str +='<button class="btn btn-mini  btn-warning " id="updateMBtnIdA'+i+'" type="button"  onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',3,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">wrong</button>';
											
											str +='<div id="mobileErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
											str +='<input type="hidden" value="3" id="isTestedMobile'+i+'"/>';
											str +='</div>';
														
										}
										else
										{	

											str +='<button class="btn btn-mini "  type="button" id="updateMBtnId'+i+'" onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',2,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >correct </button>';
										
											str +='<button class="btn btn-mini "  id="updateMBtnIdA'+i+'" type="button" onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',3,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">wrong</button>';
										
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
												*/
												hamletCount = result[i].hamletCount;
								
							//	if(results[0].status.indexOf('urban') == 0)
							//	{									
									if(result[i].hamletCount != null   )
									{
										
										if(hamletCount == 0)
										{
											str +='<td>';
												if(result[i].hamletName != null && result[i].hamletName.trim().length != 0 )
													{
														str +=''+result[i].hamletName+'<div data-toggle="buttons-radio" class="btn-group"> ';
														str +='<button class="btn btn-mini " id="updateHMBtnId'+i+'" type="button" onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',7,'+result[i].voterId+','+result[i].userid+',\'hamlet\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >correct</button>';
																
														str +='<button class="btn btn-mini " id="updateHMBtnIdA'+i+'" type="button" onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',8,'+result[i].voterId+','+result[i].userid+',\'hamlet\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >wrong</button>';
														
														str +='</div>';
													str +='<input type="hidden" value="" id="isHamletMatched'+i+'"/>';			
													}
													else{
													str +='';
													str +='<input type="hidden" value="8" id="isHamletMatched'+i+'"/>';													
													}		
								
												
											str +='</td>';
											
											str +='<td> ';
											if(result[i].hamletName != null && result[i].hamletName.trim().length != 0)
											{
											
											str +=' <select id="hamletsListId'+i+'"   style="width:165px;" onchange="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',10,'+result[i].voterId+','+result[i].userid+',\'hamlet\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" disabled="true"><option value="0"> Select </option> ';	
											}
											else
											{
											
											str +='<select id="hamletsListId'+i+'"   style="width:165px;" onchange="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',10,'+result[i].voterId+','+result[i].userid+',\'hamlet\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" ><option value="0"> Select  </option> ';
											}
											
											if(results[1] != null  && results[1].genericVOList1.length > 0)
											{
													for(var k in results[1].genericVOList1){
													if(results[1].genericVOList1[k].id == result[i].hamletId)
													{
														str +='<option value="'+results[1].genericVOList1[k].id+'" selected="selected">'+results[1].genericVOList1[k].name+'</option>';
													}
													else
													{
														str +='<option value="'+results[1].genericVOList1[k].id+'">'+results[1].genericVOList1[k].name+'</option>';
													}

													}
												}
											str +='</select></td>';
											
										}
										
										else if(hamletCount == 1)
										{
												str +='<td>';
													if(result[i].hamletName != null && result[i].hamletName.length != 0 )
													{
													str +=''+result[i].hamletName+'<div data-toggle="buttons-radio" class="btn-group">';	
													
													str +='<button class="btn btn-mini btn-success" id="updateHMBtnId'+i+'" type="button" onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',7,'+result[i].voterId+','+result[i].userid+',\'hamlet\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >correct</button>';
															
													str +='<button class="btn btn-mini " id="updateHMBtnIdA'+i+'" type="button" onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',8,'+result[i].voterId+','+result[i].userid+',\'hamlet\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >wrong</button>';
													
													str +='</div>';
													
													}
													else{
													str +='';	
													}		
													str +='<input type="hidden" value="7" id="isHamletMatched'+i+'"/>';
													str +='</div>';
													str +='</td>';
													
																					
											str +='<td> <select id="hamletsListId'+i+'"   style="width:165px;" onchange="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',10,'+result[i].voterId+','+result[i].userid+',\'hamlet\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');"  disabled="true"><option value="0"> Select </option> ';

												str +='</select></td>';
											
										}
										else if(hamletCount == 2)
										{
												str +='<td>';
													if(result[i].hamletName != null && result[i].hamletName.length != 0 )
													{
													str +=''+result[i].hamletName+'<div data-toggle="buttons-radio" class="btn-group">';
													
													str +='<button class="btn btn-mini " id="updateHMBtnId'+i+'" type="button" onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',7,'+result[i].voterId+','+result[i].userid+',\'hamlet\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >correct</button>';
															
													str +='<button class="btn btn-mini btn-warning" id="updateHMBtnIdA'+i+'" type="button" onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',8,'+result[i].voterId+','+result[i].userid+',\'hamlet\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >wrong</button>';
													
													str +='</div>';
															
													}
													else{
													str +='';	
													}
													str +='</div>';
												
											str +='<input type="hidden" value="8" id="isHamletMatched'+i+'"/>';		
											str +='</td>';
											
											str +='<td> <select id="hamletsListId'+i+'"   style="width:165px;" onchange="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',10,'+result[i].voterId+','+result[i].userid+',\'hamlet\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" ><option value="0"> Select  </option> ';	
											if(results[1] != null  && results[1].genericVOList1.length > 0)
											{
													for(var k in results[1].genericVOList1){
													if(results[1].genericVOList1[k].id == result[i].hamletId)
													{
														str +='<option value="'+results[1].genericVOList1[k].id+'" selected="selected">'+results[1].genericVOList1[k].name+'</option>';
													}
													else
													{
														str +='<option value="'+results[1].genericVOList1[k].id+'">'+results[1].genericVOList1[k].name+'</option>';
													}

													}
												}											
											str +='</select></td>';
											
										}
									}									
							//	}
								
											if(result[i].villageCovered != null){
											  str +='<td>'+result[i].villageCovered+'</td>';
											}else{
											  str +='<td></td>';
											}
											str +='</tr>';
											
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
				
				var collectdVoters = results[0].completedCount;
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
				
					newHamletsArr = new Array();
					newCasteArr = new Array();
					if(results[1] != null  && results[1].genericVOList.length > 0){
						for(var k in results[1].genericVOList){
							var objec = {
								id:results[1].genericVOList[k].id,
								value : results[1].genericVOList[k].name
							}								
							newCasteArr.push(objec);
						}
					}
					if(results[1] != null  && results[1].genericVOList1.length > 0){
						for(var k in results[1].genericVOList1){
							var objec1 = {
								id:results[1].genericVOList1[k].id,
								value : results[1].genericVOList1[k].name
							}								
							newHamletsArr.push(objec1);						
						}
					}
			}
			//console.log(newCasteArr);
		});


}

function updateMobileDetails(newHamletId,hamletFieldId,casteId,mobileId, value,voterId,surveyUserId,selectionType,userId,boothId,date,btnId,updateBtnId,userTYpe)
{
	
	$('#'+mobileId+'').val(value);
	
	var mobileMachedId = $('#'+mobileId+'').val();
	
	if( selectionType =='ceoMobileNumber')
	{
		$('#updateceoMBtnId'+updateBtnId+'').removeClass('btn-success');
		$('#updateceoMBtnIdA'+updateBtnId+'').removeClass('btn-warning');
	}
	else if( selectionType =='surveyMobileNumber')
	{
		$('#updateSurveyMBtnId'+updateBtnId+'').removeClass('btn-success');
		$('#updateSurveyMBtnIdA'+updateBtnId+'').removeClass('btn-warning');
	}
	else if( selectionType =='dataMobileNumber')
	{
		$('#updateDataMBtnId'+updateBtnId+'').removeClass('btn-success');
		$('#updateDataMBtnIdA'+updateBtnId+'').removeClass('btn-warning');
	}
			
	var jsObj = {	
		boothId : boothId,
		surveyUserId:userId,
		userType : userTYpe,
		voterId:voterId,			
		changetMobileType : selectionType,
		statusId : mobileMachedId,
		task : "getSurveyVotersMobileList"
	}
	
	$.ajax({
		type:'GET',
		url: 'saveSurveyCallStatusMobileDetilsAction.action',
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		}).done(function(result){
			
			if(result != null)
			{
				if( selectionType =='ceoMobileNumber')
				{
					if(value == 1 )
					{
						$('#updateceoMBtnId'+updateBtnId+'').addClass('btn-success');
					}
					else if(value == 0 )
					{
						$('#updateceoMBtnIdA'+updateBtnId+'').addClass('btn-warning');
					}
				}
				else if( selectionType =='surveyMobileNumber')
				{
					if(value == 2 )
					{
						$('#updateSurveyMBtnId'+updateBtnId+'').addClass('btn-success');
					}
					else if(value == 3 )
					{
						$('#updateSurveyMBtnIdA'+updateBtnId+'').addClass('btn-warning');
					}					
				}
				else if( selectionType =='dataMobileNumber')
				{
				
					if(value == 4 )
					{
						$('#updateDataMBtnId'+updateBtnId+'').addClass('btn-success');
					}
					else if(value == 5 )
					{
						$('#updateDataMBtnIdA'+updateBtnId+'').addClass('btn-warning');
					}
				}
			}			
		});
}
function updateDetails(newHamletId,hamletFieldId,casteId,mobileId, value,voterId,surveyUserId,selectionType,userId,boothId,date,newCasteId,updateBtnId,userTYpe)
{
	var casteMatched = $('#'+casteId+'').val();
	var mobileMached = $('#'+mobileId+'').val();
	var hamletmatched = $('#'+hamletFieldId+'').val();

	// 0 ,2,7 -- correct
	// 1 ,3,8 -- wrong
	if(value == 0 || value == 1)
	{
		// caste 
		$('#'+casteId+'').val(value);
		if(mobileMached.length == 0 || (mobileMached != 2 && mobileMached != 3)){
			$('#'+mobileId+'').val('6');
		}
		if(hamletmatched.length == 0 || (hamletmatched != 7 && hamletmatched != 8)){
			$('#'+hamletFieldId+'').val('9');
		}
	}
	
	if(value == 2 || value == 3)
	{
		// mobile
		
		$('#'+mobileId+'').val(value);
		if(casteMatched.length == 0 || (casteMatched != 0 && casteMatched != 1)){
			$('#'+casteId+'').val('5');
		}
		if(hamletmatched.length == 0 || (hamletmatched != 7 && hamletmatched != 8)){
			$('#'+hamletFieldId+'').val('9');
		}
	}
	
	if(value == 7 || value == 8)
	{
		// hamlet
		
		$('#'+hamletFieldId+'').val(value);
		if(casteMatched.length == 0 || (casteMatched != 0 && casteMatched != 1)){
			$('#'+casteId+'').val('5');
		}
		if(mobileMached.length == 0 || (mobileMached != 2 && mobileMached != 3)){
			$('#'+mobileId+'').val('6');
		}	
	}
	
	if(value == 10){
		if(casteMatched == null || casteMatched.trim().length == 0){
			$('#'+casteId+'').val('5');
		}
		if(mobileMached == null || mobileMached.trim().length == 0){
			$('#'+mobileId+'').val('6');
		}
		if(hamletmatched == null || hamletmatched.trim().length == 0){
			$('#'+hamletFieldId+'').val('9');
		}
	}

updateStatusDetails(newHamletId,hamletFieldId,casteId,mobileId,voterId,surveyUserId,selectionType,userId,boothId,date,newCasteId,updateBtnId,userTYpe);
}

function updateStatusDetails(newHamletId,hamletFieldId,isCasteMatched,mobileMatched,voterId,surveyUserId,selectionType,userId,boothId,date,newCasteId,updateBtnId,userType)
{
	if(selectionType =='mobile'){
		$('#updateMBtnId'+updateBtnId+'').removeClass('btn-success');
		$('#updateMBtnIdA'+updateBtnId+'').removeClass('btn-warning');
	}
	else if (selectionType == 'hamlet')
	{
		$('#updateHMBtnId'+updateBtnId+'').removeClass('btn-success');
		$('#updateHMBtnIdA'+updateBtnId+'').removeClass('btn-warning');
						
	}
	else{
		$('#updateBtnId'+updateBtnId+'').removeClass('btn-success');
		$('#updateBtnIdA'+updateBtnId+'').removeClass('btn-warning');  
	}

	var voterInfoArr = new Array();
	var isMobileVerified = $('#'+mobileMatched+'').val();
	var isMatched = $('#'+isCasteMatched+'').val();
	var isHamletMatched = $('#'+hamletFieldId+'').val();
//	console.log("isMatched   :"+isMatched );
	var casteId = 0;

	if(newCasteId != 0){
		casteId = $('#'+newCasteId+'').val();
	//	console.log(casteId);
		if(isMatched == 1){
			casteId = 0;
		}
	}
	
	var hamletId = 0 ;

	if(newHamletId != 0)
	{
		hamletId = $('#'+newHamletId+'').val();
		
		if(isHamletMatched == 7){
			hamletId = 0;
		}
	}
	
	if(casteId != 0 )
	{
		isMatched = 0;
	}
		
	var obj = {	
		boothId : boothId,
		casteId:casteId,
		hamletId:hamletId,
		isHamletMatched:isHamletMatched,
		isMatched :isMatched,
		isMobileVerified:isMobileVerified,
		surveyUserId:userId,
		updationType:"notCastewise",
		userType : userType,
		voterId:voterId ,
		surveyArea:$('#surveyArea').val()
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
				else if(selectionType =='hamlet')
				{
					$('#'+newHamletId+'').find('option').remove();
					$('#'+newHamletId+'').append('<option value="0"> Select  </option>');
					
					if(isHamletMatched == 7){
						$('#updateHMBtnId'+updateBtnId+'').addClass('btn-success');
						$('#updateHMBtnIdA'+updateBtnId+'').removeClass('btn-warning');	
						$('#'+newHamletId+'').val(0);						
						$('#'+newHamletId+'').prop('disabled','disabled');
					}
					if(isHamletMatched == 8){
							$('#updateHMBtnIdA'+updateBtnId+'').addClass('btn-warning');
							$('#updateHMBtnId'+updateBtnId+'').removeClass('btn-success');
							$('#'+newHamletId+'').prop('disabled',false);
														
							if(newHamletsArr.length >0){
							for(var k in newHamletsArr){
								$('#'+newHamletId+'').append('<option value="'+newHamletsArr[k].id+'">'+newHamletsArr[k].value+'</option>');
							}
						}
						
						$('#'+newHamletId+'').val(hamletId);	
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
		var surveyArea = results[0].status;
		$('#webMontrId').show();
		if(results != null && results[0].subList.length>0)
		{

				var result = results[0].subList;
				str +='<input type="hidden" value="'+surveyArea+'" id="surveyArea"/>';
				str +='<table class="table table-bordered m_top20 table-hover table-striped" id="voterDetlsTab" >';
				str +='	<thead class="alert alert-success">';
				str +='	<tr>';
				str +='<th>  <input type="checkbox" id="alluserChkbx" class="allCheckbxCls" onclick="selectAllCheckBoxes();"/> Select All </th>';
				str +='<th> S.NO </th>';
				str +='<th>H.NO</th>';
				str +='<th> Name </th>';
				str +='<th> Gaurdian Name </th>';
				if(userType == 4)
				{
					str +='<th>DC CASTE</th>';
					str +='<th>DC MOBILE</th>';
					str +='<th>DC WM CASTE</th>';
					str +='<th>MATCHED STATUS</th>';
					str +='<th> DV Caste </th>';
				}
				else
				{
				str +='<th> Caste </th>';
				}
				str +='<th> Caste Matched  </th>';
				str +='<th> Mobile Number </th>';
				str +='<th> Hamlet/ Ward </th>';
				str +='<th> Hamlet/ Ward Matched </th>';
				str +='<th> H.NO With Diff Castes </th>';
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
							if(result[i].dcCaste == null){
								str +='<li> - </li>';
							}else{
								str +='<li>'+result[i].dcCaste+'</li>';					
							}
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
							if(result[i].wmCaste == null){
								str +='<li> - </li>';
							}else{
								str +='<li>'+result[i].wmCaste+'</li>';					
							}
							str +='</ul>';
							str +='</div>';
							str +='</td>';
							
							str +='<td>';
							str +='<div>';
							str +='<ul class="unstyled inline">';
							if(result[i].status == null){
								str +='<li> - </li>';
							}else{
								str +='<li>'+result[i].status+'</li>';				
							}							
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
							str +='<button class="btn btn-mini  btn-success " id="updateBtnId'+i+'" type="button" onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',1,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">correct</button>';
							str +='<button class="btn btn-mini " id="updateBtnIdA'+i+'" type="button" onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',0,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >wrong</button>';
							str +='<div id="casteErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
							
							str +='<input type="hidden" value="1" id="isCasteMatched'+i+'"/>';
							
							str +='</div>';
							str +='</td>';
							
							str +='<td> <select id="casteListId'+i+'"  disabled="disabled" style="width:165px;" onchange="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',10,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');"><option value="0"> Select Caste </option> ';
							str +='</select></td>';	
												
						}
						else if(result[i].casteMatchedCount == 2)
						{
							str +='	<div data-toggle="buttons-radio" class="btn-group">';
							
							str +='	<button class="btn btn-mini " id="updateBtnId'+i+'" type="button"  onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',1,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >correct</button>';
							
							str +='	<button class="btn btn-mini  btn-warning " id="updateBtnIdA'+i+'" type="button"  onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',0,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">wrong</button>';
							
							str +='<div id="casteErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
							str +='<input type="hidden" value="0" id="isCasteMatched'+i+'"/>';
							str +='</div>';
							str +='</td>';
							
							str +='<td> <select id="casteListId'+i+'"   style="width:165px;" onchange="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',10,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');"><option value="0"> Select Caste </option> ';
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
							
							str +='<button class="btn btn-mini " id="updateBtnId'+i+'" type="button" onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',1,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >correct</button>';
							
							str +='<button class="btn btn-mini " id="updateBtnIdA'+i+'" type="button" onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',0,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >wrong</button>';
							
							str +='<div id="casteErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
							str +='<input type="hidden" value="" id="isCasteMatched'+i+'"/>';
							str +='</div>';
							str +='</td>';
							
							str +='<td> <select id="casteListId'+i+'"  disabled="disabled" style="width:165px;" onchange="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',10,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');"><option value="0"  > Select Caste </option> ';
							str +='</select></td>';	
						}			
						isCasteAvalbl = true;
		
					}
					else{
						str +='<td>';
						str +='<input type="hidden" value="" id="isCasteMatched'+i+'"/>';
						str +='</td>';
						
							str +='<td> <select id="casteListId'+i+'"   style="width:165px;" onchange="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',10,'+result[i].voterId+','+result[i].userid+',\'caste\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');"><option value="0"> Select Caste </option> ';
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
					/*
							var mobNo = '';
							if(result[i].mobileNo != null  && result[i].mobileNo.length != 0 )
							{						
										mobNo = result[i].mobileNo					
										str +='<td>'+mobNo+'<div data-toggle="buttons-radio" class="btn-group">';
																			
										if(result[i].mobileMatchedCount == 1)
										{
											str +='<button class="btn btn-mini  btn-success " id="updateMBtnId'+i+'" type="button" onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',2,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >correct </button>';
											
											str +='<button class="btn btn-mini "  id="updateMBtnIdA'+i+'" type="button" onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',3,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">wrong</button>';
											
											str +='<div id="mobileErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
											str +='<input type="hidden" value="2" id="isTestedMobile'+i+'"/>';
											str +='</div>';
														
										}
										else if(result[i].mobileMatchedCount == 2)
										{

											str +='<button class="btn btn-mini "  id="updateMBtnId'+i+'" type="button" onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',2,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">correct </button>';
											
											str +='<button class="btn btn-mini  btn-warning " id="updateMBtnIdA'+i+'" type="button"  onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',3,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">wrong</button>';
											
											str +='<div id="mobileErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
											str +='<input type="hidden" value="3" id="isTestedMobile'+i+'"/>';
											str +='</div>';
														
										}
										else
										{	

											str +='<button class="btn btn-mini "  type="button" id="updateMBtnId'+i+'" onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',2,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >correct </button>';
										
											str +='<button class="btn btn-mini "  id="updateMBtnIdA'+i+'" type="button" onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',3,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">wrong</button>';
										
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
						*/			

							
								str +='<td>';													
								str +='<ul>';										
						if(result[i].mobileNo != null  && result[i].mobileNo.length != 0 )
							{						
										var mobNo = result[i].mobileNo	;
													
									str +='<li style="background-color:#D3D6FF;margin-top:8px;">';
									str +=''+mobNo+'';
									if(result[i].mobileStatus != null)
									{
										str +='<div data-toggle="buttons-radio"> Invalid Number </div>';
										str +='<input type="hidden" id="isTestedMobile'+i+'"  value=""/>';
									}
									else
									{		
										str +='<div data-toggle="buttons-radio" class="btn-group">';
										if(result[i].ctpMobileStatus == 1)
										{
											str +='<button class="btn btn-mini  btn-success " id="updateMBtnId'+i+'" type="button" onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',2,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >correct </button>';
											
											str +='<button class="btn btn-mini "  id="updateMBtnIdA'+i+'" type="button" onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',3,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">wrong</button>';
											
											str +='<div id="mobileErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
											str +='<input type="hidden" value="2" id="isTestedMobile'+i+'"/>';
											str +='</div>';
														
										}
										else if(result[i].ctpMobileStatus == 2)
										{

											str +='<button class="btn btn-mini "  id="updateMBtnId'+i+'" type="button" onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',2,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">correct </button>';
											
											str +='<button class="btn btn-mini  btn-warning " id="updateMBtnIdA'+i+'" type="button"  onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',3,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">wrong</button>';
											
											str +='<div id="mobileErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
											str +='<input type="hidden" value="3" id="isTestedMobile'+i+'"/>';
											str +='</div>';
														
										}
										else
										{	

											str +='<button class="btn btn-mini "  type="button" id="updateMBtnId'+i+'" onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',2,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >correct </button>';
										
											str +='<button class="btn btn-mini "  id="updateMBtnIdA'+i+'" type="button" onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',3,'+result[i].voterId+','+result[i].userid+',\'mobile\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">wrong</button>';
										
											str +='<div id="mobileErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
											str +='<input type="hidden" id="isTestedMobile'+i+'"  value=""/>';
											str +='</div>';
											
										}
									}
										//str +='(BY CTP)';
								}	
							else{	
									str +='<li>';
									str +='<input type="hidden" value="" id="isTestedMobile'+i+'"/>';
								}	
								str +='</li>';
															
						if(result[i].ceoMobileNoList != null  && result[i].ceoMobileNoList.length > 0 )
							{						
										var mobNo = result[i].ceoMobileNoList[0];
										str +='<li style="background-color:#D3D6FF;margin-top:8px;">';							
										str +=''+mobNo+'<div data-toggle="buttons-radio" class="btn-group">';
																			
										if(result[i].ceoMobileStatus == 1)
										{
											str +='<button class="btn btn-mini  btn-success " id="updateceoMBtnId'+i+'" type="button" onclick="updateMobileDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isceoTestedMobile'+i+'\',1,'+result[i].voterId+','+result[i].userid+',\'ceoMobileNumber\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >correct </button>';
											
											str +='<button class="btn btn-mini "  id="updateceoMBtnIdA'+i+'" type="button" onclick="updateMobileDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isceoTestedMobile'+i+'\',0,'+result[i].voterId+','+result[i].userid+',\'ceoMobileNumber\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">wrong</button>';
											
											str +='<div id="mobileErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
											str +='<input type="hidden" value="1" id="isceoTestedMobile'+i+'"/>';
											str +='</div>';
														
										}
										else if(result[i].ceoMobileStatus == 2)
										{

											str +='<button class="btn btn-mini "  id="updateceoMBtnId'+i+'" type="button" onclick="updateMobileDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isceoTestedMobile'+i+'\',1,'+result[i].voterId+','+result[i].userid+',\'ceoMobileNumber\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">correct </button>';
											
											str +='<button class="btn btn-mini  btn-warning " id="updateceoMBtnIdA'+i+'" type="button"  onclick="updateMobileDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isceoTestedMobile'+i+'\',0,'+result[i].voterId+','+result[i].userid+',\'ceoMobileNumber\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">wrong</button>';
											
											str +='<div id="mobileErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
											str +='<input type="hidden" value="0" id="isceoTestedMobile'+i+'"/>';
											str +='</div>';
														
										}
										else
										{	

											str +='<button class="btn btn-mini "  type="button" id="updateceoMBtnId'+i+'" onclick="updateMobileDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isceoTestedMobile'+i+'\',1,'+result[i].voterId+','+result[i].userid+',\'ceoMobileNumber\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >correct </button>';
										
											str +='<button class="btn btn-mini "  id="updateceoMBtnIdA'+i+'" type="button" onclick="updateMobileDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isceoTestedMobile'+i+'\',0,'+result[i].voterId+','+result[i].userid+',\'ceoMobileNumber\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">wrong</button>';
										
											str +='<div id="mobileErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
											str +='<input type="hidden" id="isceoTestedMobile'+i+'"  value=""/>';
											str +='</div>';
											
										}

									//str +='(BY CEO)';	
								}	
							else{
									str +='<li>';		
											str +='<input type="hidden" value="" id="isceoTestedMobile'+i+'"/>';
																					

								}
								str +='</li>';	
								
						if(result[i].surveyMobileNoList != null  && result[i].surveyMobileNoList.length > 0 )
							{			
										str +='<li style="background-color:#D3D6FF;margin-top:8px;">';	
										var mobNo = result[i].surveyMobileNoList[0];
									
										str +=''+mobNo+'<div data-toggle="buttons-radio" class="btn-group">';
																			
										if(result[i].surveyMobileStatus == 1)
										{
											str +='<button class="btn btn-mini  btn-success " id="updateSurveyMBtnId'+i+'" type="button" onclick="updateMobileDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isSurveyTestedMobile'+i+'\',2,'+result[i].voterId+','+result[i].userid+',\'surveyMobileNumber\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >correct </button>';
											
											str +='<button class="btn btn-mini "  id="updateSurveyMBtnIdA'+i+'" type="button" onclick="updateMobileDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isSurveyTestedMobile'+i+'\',3,'+result[i].voterId+','+result[i].userid+',\'surveyMobileNumber\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">wrong</button>';
											
											str +='<div id="mobileErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
											str +='<input type="hidden" value="2" id="isSurveyTestedMobile'+i+'"/>';
											str +='</div>';
														
										}
										else if(result[i].surveyMobileStatus == 2)
										{

											str +='<button class="btn btn-mini "  id="updateSurveyMBtnId'+i+'" type="button" onclick="updateMobileDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isSurveyTestedMobile'+i+'\',2,'+result[i].voterId+','+result[i].userid+',\'surveyMobileNumber\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">correct </button>';
											
											str +='<button class="btn btn-mini  btn-warning " id="updateSurveyMBtnIdA'+i+'" type="button"  onclick="updateMobileDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isSurveyTestedMobile'+i+'\',3,'+result[i].voterId+','+result[i].userid+',\'surveyMobileNumber\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">wrong</button>';
											
											str +='<div id="mobileErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
											str +='<input type="hidden" value="3" id="isSurveyTestedMobile'+i+'"/>';
											str +='</div>';
														
										}
										else
										{	

											str +='<button class="btn btn-mini "  type="button" id="updateSurveyMBtnId'+i+'" onclick="updateMobileDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isSurveyTestedMobile'+i+'\',2,'+result[i].voterId+','+result[i].userid+',\'surveyMobileNumber\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >correct </button>';
										
											str +='<button class="btn btn-mini "  id="updateSurveyMBtnIdA'+i+'" type="button" onclick="updateMobileDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isSurveyTestedMobile'+i+'\',3,'+result[i].voterId+','+result[i].userid+',\'surveyMobileNumber\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">wrong</button>';
										
											str +='<div id="mobileErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
											str +='<input type="hidden" id="isSurveyTestedMobile'+i+'"  value=""/>';
											str +='</div>';
											
										}
										//	str +='(BY SURVEY)';
								}	
							else{
								str +='<li>';
											str +='<input type="hidden" value="" id="isSurveyTestedMobile'+i+'"/>';
	
								}
							str +='</li>';	
							if(result[i].dataMobileNoList != null  && result[i].dataMobileNoList.length > 0 )
							{				
									str +='<li style="background-color:#D3D6FF;margin-top:8px;">';	
										var mobNo = result[i].dataMobileNoList[0];
								
										str +=''+mobNo+'<div data-toggle="buttons-radio" class="btn-group">';
																			
										if(result[i].dataMobileStatus == 1)
										{
											str +='<button class="btn btn-mini  btn-success " id="updateDataMBtnId'+i+'" type="button" onclick="updateMobileDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isDataTestedMobile'+i+'\',4,'+result[i].voterId+','+result[i].userid+',\'dataMobileNumber\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >correct </button>';
											
											str +='<button class="btn btn-mini "  id="updateDataMBtnIdA'+i+'" type="button" onclick="updateMobileDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isDataTestedMobile'+i+'\',5,'+result[i].voterId+','+result[i].userid+',\'dataMobileNumber\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">wrong</button>';
											
											str +='<div id="mobileErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
											str +='<input type="hidden" value="4" id="isDataTestedMobile'+i+'"/>';
											str +='</div>';
														
										}
										else if(result[i].dataMobileStatus == 2)
										{

											str +='<button class="btn btn-mini "  id="updateDataMBtnId'+i+'" type="button" onclick="updateMobileDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isDataTestedMobile'+i+'\',4,'+result[i].voterId+','+result[i].userid+',\'dataMobileNumber\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">correct </button>';
											
											str +='<button class="btn btn-mini  btn-warning " id="updateDataMBtnIdA'+i+'" type="button"  onclick="updateMobileDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isDataTestedMobile'+i+'\',5,'+result[i].voterId+','+result[i].userid+',\'dataMobileNumber\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">wrong</button>';
											
											str +='<div id="mobileErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
											str +='<input type="hidden" value="5" id="isDataTestedMobile'+i+'"/>';
											str +='</div>';
														
										}
										else
										{	

											str +='<button class="btn btn-mini "  type="button" id="updateDataMBtnId'+i+'" onclick="updateMobileDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isDataTestedMobile'+i+'\',4,'+result[i].voterId+','+result[i].userid+',\'dataMobileNumber\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >correct </button>';
										
											str +='<button class="btn btn-mini "  id="updateDataMBtnIdA'+i+'" type="button" onclick="updateMobileDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isDataTestedMobile'+i+'\',5,'+result[i].voterId+','+result[i].userid+',\'dataMobileNumber\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');">wrong</button>';
										
											str +='<div id="mobileErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
											str +='<input type="hidden" id="isDataTestedMobile'+i+'"  value=""/>';
											str +='</div>';
											
										}
										
									//	str +='(BY DATA)';
								}	
							else{
									str +='<li>';
											str +='<input type="hidden" value="" id="isDataTestedMobile'+i+'"/>';
								}
									str +='</li>';
										str +='</ul>';
								
								str +='</td>';
						
									hamletCount = result[i].hamletCount;
								
							//	if(results[0].status.indexOf('urban') == 0)
							//	{									
									if(result[i].hamletCount != null   )
									{
										
										if(hamletCount == 0)
										{
											str +='<td>';
													if(result[i].hamletName != null && result[i].hamletName.trim().length != 0 )
													{
														str +=''+result[i].hamletName+'<div data-toggle="buttons-radio" class="btn-group"> ';
														str +='<button class="btn btn-mini " id="updateHMBtnId'+i+'" type="button" onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',7,'+result[i].voterId+','+result[i].userid+',\'hamlet\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >correct</button>';
																
														str +='<button class="btn btn-mini " id="updateHMBtnIdA'+i+'" type="button" onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',8,'+result[i].voterId+','+result[i].userid+',\'hamlet\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >wrong</button>';
														
														str +='</div>';
													str +='<input type="hidden" value="" id="isHamletMatched'+i+'"/>';			
													}
													else{
													str +='';
													str +='<input type="hidden" value="8" id="isHamletMatched'+i+'"/>';													
													}		
								
												
											str +='</td>';
											
											str +='<td> ';
											if(result[i].hamletName != null && result[i].hamletName.trim().length != 0)
											{
											
											str +=' <select id="hamletsListId'+i+'"   style="width:165px;" onchange="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',10,'+result[i].voterId+','+result[i].userid+',\'hamlet\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" disabled="true"><option value="0"> Select  </option> ';	
											}
											else
											{
											
											str +='<select id="hamletsListId'+i+'"   style="width:165px;" onchange="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',10,'+result[i].voterId+','+result[i].userid+',\'hamlet\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" ><option value="0"> Select  </option> ';
											}
											
											if(results[1] != null  && results[1].genericVOList1.length > 0)
											{
													for(var k in results[1].genericVOList1){
													if(results[1].genericVOList1[k].id == result[i].hamletId)
													{
														str +='<option value="'+results[1].genericVOList1[k].id+'" selected="selected">'+results[1].genericVOList1[k].name+'</option>';
													}
													else
													{
														str +='<option value="'+results[1].genericVOList1[k].id+'">'+results[1].genericVOList1[k].name+'</option>';
													}

													}
												}											
											str +='</select></td>';
											
										}
										
										else if(hamletCount == 1)
										{
												str +='<td>';
													if(result[i].hamletName != null && result[i].hamletName.length != 0 )
													{
													str +=''+result[i].hamletName+'<div data-toggle="buttons-radio" class="btn-group">';	
													
													str +='<button class="btn btn-mini btn-success" id="updateHMBtnId'+i+'" type="button" onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',7,'+result[i].voterId+','+result[i].userid+',\'hamlet\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >correct</button>';
															
													str +='<button class="btn btn-mini " id="updateHMBtnIdA'+i+'" type="button" onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',8,'+result[i].voterId+','+result[i].userid+',\'hamlet\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >wrong</button>';
													
													str +='</div>';
													
													}
													else{
													str +='';	
													}		
													str +='<input type="hidden" value="7" id="isHamletMatched'+i+'"/>';
													str +='</div>';
													str +='</td>';
													
																					
											str +='<td> <select id="hamletsListId'+i+'"   style="width:165px;" onchange="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',10,'+result[i].voterId+','+result[i].userid+',\'hamlet\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');"  disabled="true"><option value="0"> Select  </option> ';	
												
												str +='</select></td>';
											
										}
										else if(hamletCount == 2)
										{
												str +='<td>';
													if(result[i].hamletName != null && result[i].hamletName.length != 0 )
													{
													str +=''+result[i].hamletName+'<div data-toggle="buttons-radio" class="btn-group">';
													
													str +='<button class="btn btn-mini " id="updateHMBtnId'+i+'" type="button" onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',7,'+result[i].voterId+','+result[i].userid+',\'hamlet\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >correct</button>';
															
													str +='<button class="btn btn-mini btn-warning" id="updateHMBtnIdA'+i+'" type="button" onclick="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',8,'+result[i].voterId+','+result[i].userid+',\'hamlet\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" >wrong</button>';
													
													str +='</div>';
															
													}
													else{
													str +='';	
													}
													str +='</div>';
												
											str +='<input type="hidden" value="8" id="isHamletMatched'+i+'"/>';		
											str +='</td>';
											
											str +='<td> <select id="hamletsListId'+i+'"   style="width:165px;" onchange="updateDetails(\'hamletsListId'+i+'\',\'isHamletMatched'+i+'\',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',10,'+result[i].voterId+','+result[i].userid+',\'hamlet\','+userId+','+boothId+',\''+date+'\',\'casteListId'+i+'\','+i+','+userType+');" ><option value="0"> Select  </option> ';
												if(results[1] != null  && results[1].genericVOList1.length > 0){
													for(var k in results[1].genericVOList1){
														if(results[1].genericVOList1[k].id == result[i].hamletId)
														{
															str +='<option value="'+results[1].genericVOList1[k].id+'" selected="selected">'+results[1].genericVOList1[k].name+'</option>';
														}
														else
														{
															str +='<option value="'+results[1].genericVOList1[k].id+'">'+results[1].genericVOList1[k].name+'</option>';
														}
														
													}
												}
											str +='</select></td>';
											
										}
									}									
							//	}
								
										
											if(result[i].villageCovered != null){
											  str +='<td>'+result[i].villageCovered+'</td>';
											}else{
											  str +='<td></td>';
											}
											
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
		str +='<a id="updateBtnInNewWndow" style="position: fixed; left :0px; top: 320px;" href="javascript:{updateVoterDetlsStatus('+userId+','+boothId+','+casteId1+','+webConstId1+','+date+','+userType+');}" class="btn btn-primary"> Update Details </a>';
		str +='</div>';

		$('#scrollBtnDiv').show();
		
		$('#voterInfoDIv').html(str);
		$('#voterDetlsTab').dataTable({
		"iDisplayLength": 100,
		"aLengthMenu": [[100, 200, 500, -1], [100, 200, 500, "All"]]
		});
	
}
/*
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
/*		str +='<div class="span4">';
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
					str +='<button class="btn btn-success" onClick="saveVoterCasteAndMobileDetals(\''+voterIdsArr+'\','+userId+','+boothId+','+casteId1+','+webConstId1+',\''+date+'\','+userType+');" style="float:right;"> Submit </button>';
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
*/


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
		height:300,
		modal: true,
		resizable: false,
		title:" Update Voters Caste and Mobile details"
	});

    var str1='';
	str1+='<div id="casteOrHamletDiv" style="margin-left: 148px;">'; 
	str1+='<label class="radio inline control-label">';
    str1+='<input type="radio" name="casteOrHamletName" value="caste" checked="true"  id="casteUpdateid" onClick="ForUpdateDetails(\'caste\','+userId+','+boothId+','+casteId1+','+webConstId1+','+date+','+userType+',\''+voterIdsArr+'\')">Caste</input>';
	str1+='</label>';
	str1+='<label class="radio inline control-label">';
    str1+='<input type="radio" name="casteOrHamletName" value="hamlet" id="hamletUpdateId" onClick="ForUpdateDetails(\'hamlet\','+userId+','+boothId+','+casteId1+','+webConstId1+','+date+','+userType+',\''+voterIdsArr+'\')">Hamlet</input></label>';
	str1+='</div>'; 
	str1+='<div id="appendDivId">';
	str1+='</div>';
$('#finalInputDiv').html(str1);
ForUpdateDetails('caste',userId,boothId,casteId1,webConstId1,date,userType,voterIdsArr);
		
}
function ForUpdateDetails(value,userId,boothId,casteId1,webConstId1,date,userType,voterIdsArr)
{
 var str='';

    str +='<div class="row">';
	str +='<div id="finalErrDiv" style="color:#FF0020;margin-left: 100px;"></div>';
	str +='<div class="offset1">';
	str +='<input type="hidden" id="casteStatusId" value=""/>';
	str +='<input type="hidden" id="mobileStatusId" value=""/>';
	str +='<input type="hidden" id="hamletStatusId" value=""/>';
	str +='<div class="row-fluid" style="margin-top: 24px;">';
	str +='<div class="span4">';
	if(value=='caste')
	{
	str +='Caste Status : <font class="requiredFont">*</font>';
	}
	else if (value=='hamlet')
	{
 	 str +='Hamlet Status : <font class="requiredFont">*</font>';
	}
	str +='<div data-toggle="buttons-radio" class="btn-group">';	
	if(value=='caste')
	{
	 str +='<button class="btn btn-mini selectCls1" name="selectCls1"  id="correct1" onclick="changeSelectionStatus(\'correct1\',1,\'casteStatusId\')">correct </button>';
	 str +='<button class="btn btn-mini selectCls1" name="selectCls1"  id="wrong1"  onclick="changeSelectionStatus(\'wrong1\',0,\'casteStatusId\')" >wrong</button>';							
	}
	else if(value=='hamlet')
	{
	 str +='<button class="btn btn-mini selectCls2" name="selectCls2"  id="correct2" onclick="changeSelectionStatus(\'correct2\',7,\'hamletStatusId\')">correct </button>';
	 str +='<button class="btn btn-mini selectCls2" name="selectCls2"  id="wrong2"  onclick="changeSelectionStatus(\'wrong2\',8,\'hamletStatusId\')" >wrong</button>';							
    }
	str +='</div>';
	str +='</div>';
	str +='<div class="span4">';
	if(value=='caste')
	{
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
	
	}
	else if(value=='hamlet')
	{
	 str +='Select Hamlet/Ward : <font class="requiredFont">*</font>';
	 str +='<select id="finalHamletList" disabled="disabled"> ';
	 str +='<option value="0"> Select  </option>';
     if(newHamletsArr.length > 0)
	  {			
	  for(var i in newHamletsArr)
	  {
	   str +='<option value="'+newHamletsArr[i].id+'">'+newHamletsArr[i].value+'</option>';
	  }
	 }
	 str +='</select>';
	
	}
	
	str +='</div>';
	str +='</div>';
	str +='</div>';
	str +='<div class="offset1">';
	str +='<div class="row-fluid">';
	str +='<div class="span6">';
	
	str +='<button class="btn btn-success" onClick="saveVoterCasteAndMobileDetals(\''+voterIdsArr+'\','+userId+','+boothId+','+casteId1+','+webConstId1+',\''+date+'\','+userType+');" style="float:right;"> Submit </button>';
	
	
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

$('#appendDivId').html('');
$('#appendDivId').html(str);

}


function changeSelectionStatus(btnId,value,filedId){
	
		var className = $('#'+btnId).attr('name');
		//console.log(className);
		$('.'+className).removeClass('btn-success');
		$('.'+className).removeClass('btn-warning');
	$('#'+filedId).val(value);
	if(value == 1 || value == 2)
	{	
		$('#finalCasteList').prop('disabled','disabled');
		$('#'+btnId).addClass('btn-success');		
	}
	else if(value == 0 || value == 3)
	{
		$('#finalCasteList').prop('disabled',false);
		$('#'+btnId).addClass('btn-warning');
	}
	else if(value == 7)
	{
		$('#finalHamletList').prop('disabled','disabled');
		$('#'+btnId).addClass('btn-success');
	}
	else if(value == 8)
	{
		$('#finalHamletList').prop('disabled',false);
		$('#'+btnId).addClass('btn-warning');
	}
}




function saveVoterCasteAndMobileDetals(voterIds,userId,boothId,casteId1,webConstId1,date,userType){

	var voterInfoArr = [];
	var casteId = 0;
	var votersArr = voterIds.split(',');
	var isMatched = $('#casteStatusId').val() ;
	var isHamletMatched = $('#hamletStatusId').val() ;
	var submitType = 'casteWise';
	casteId = $('#finalCasteList').val() ;
	var hamletId = $('#finalHamletList').val();
	$('#finalErrDiv').html('');
	if(isMatched.trim().length == 0){
		isMatched = 5;
	}


	if(voterIds.trim().length == 0){
		$('#finalErrDiv').html('Please select atlease one voter.');
		return;
	}
	if(isMatched == 5 && casteId == 0){
		$('#finalErrDiv').html('Please update atlease one element.');
		return;
	}

	$('#saviStatusDiv').show();

	if(typeof casteId === "undefined"){
		casteId = 0;
		submitType = 'hamletWise'
	}
	if(isHamletMatched.trim().length == 0 || typeof isHamletMatched === "undefined"){
		isHamletMatched = 9;
		hamletId = 0;
	}

	if(votersArr != null && votersArr.length >0)
	{
		for(var i in votersArr){
			var obj = {
				voterId:votersArr[i],
				surveyUserId:userId,
				isMobileVerified:6,
				isMatched :isMatched,
				boothId : boothId,
				isHamletMatched:isHamletMatched,
				hamletId:hamletId,
				casteId:casteId,
				userType : userType,
				updationType : submitType,
				surveyArea:$('#surveyArea').val()
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
				getCasteWiseVotersListByCaste(casteId1,webConstId1,userId,boothId,date,userType);
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