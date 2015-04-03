var fieldDataflg = false;
var fieldReportflg = false;

function showHideTabs(id)
{

	$(".errClass").html('');
	if(id == "completedBoothsTab")
	{
		$("#thirdPartyReportDiv").hide();
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
		$('#dayWiseDCsReports').hide();
	}
	else if(id == "callCenterTab")
	{
		$("#thirdPartyReportDiv").hide();
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
		$('#dayWiseDCsReports').hide();
		
	}
	else if (id == "startTimeTab")
	{
		$("#thirdPartyReportDiv").hide();
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
		$('#dayWiseDCsReports').hide();
		if(fieldReportflg == false)
		getFieldReportSummary();
		fieldReportflg = true;
	}
	else if(id == "boothWiseTab")
	{
		$("#thirdPartyReportDiv").hide();
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
		$('#dayWiseDCsReports').hide();
		if(fieldDataflg == false)
		//getDataReportSummary();
		fieldDataflg = true;


	}
	else if(id == "surveyStatusRprtTab"){
		$("#thirdPartyReportDiv").hide();
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
		$('#dayWiseDCsReports').hide();
	}
	else if (id == "surveyUserWise")
	{
		$("#thirdPartyReportDiv").hide();
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
		$('#dayWiseDCsReports').hide();
	}
	else if (id == "saveBoothPercentagesTab")
	{
		$("#thirdPartyReportDiv").hide();
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
		$('#dayWiseDCsReports').hide();
	}
	else if (id == "wmReportTab")
	{
		$("#thirdPartyReportDiv").hide();
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
		$('#dayWiseDCsReports').hide();
	}
	else if (id == "verifierReportTab")
	{
		$("#thirdPartyReportDiv").hide();
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
		$('#dayWiseDCsReports').hide();
	}
	else if (id == "thirdPartyReportTab")
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
		$("#verifierReportIdForVerifier").hide();
		$("#wmReportDiv").hide();
		$('#dayWiseDCsReports').hide();
		$("#thirdPartyReportDiv").show();
        getThirdPartySummaryDetails();
		getTPTotalBoothsDetailsConstituencyWise();
	}
	else if(id =="daywiseDCReport")
	{

		$("#thirdPartyReportDiv").hide();
		$('#statusReportDiv').hide();
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
		$('#dayWiseDCsReports').show();
		//getDCDataAvalaiableConstituencyies();
	}
	else
	{
		$("#thirdPartyReportDiv").hide();
		$('#statusReportDiv').hide();
		$('#webMontrId').hide();
		$('#callCenter').hide();
		$('#startTime').hide();
		$('#boothWise').hide();
		$('#dataCollector').show();
		$('#inActiveUsersDetails').hide();
		$('#completeBooths').hide();
		$('#userReport').hide();
		$("#verifierReportIdForVerifier").hide();;
		$('#saveBoothsPercentage').hide();
		$("#wmReportDiv").hide();
		$('#dayWiseDCsReports').hide();
	}
}
function  getThirdPartySummaryDetails()
{	
	$('#thirdPartySummary').html('');

	$.ajax({
		type:'GET',
		url: 'getSurveyCompletedConstituencyDetails.action',
		dataType: 'json',
		data: {},
		}).done(function(result){
			buildThirdPartySummaryDetails(result);
			
		});
	
}
function buildThirdPartySummaryDetails(result)
{
	var str = '';
	if(result != null && result.length >0)
	{
		str+='<table class=" table table-bordered m_top20">';
		 str+='<thead>';
		  str+='<tr>';
		   str+='<th>Constituency Name</th>';
		   str+='<th>Status</th>';
		   str+='<th>Comment</th>';
		  str+='</tr>';
		 str+='</thead>';
		 str+='<tbody>';
		  $.each(result,function(index,value){
			  str+='<tr>';
				str+='<td>'+value.name+'</td>';
				str+='<td>'+value.status+'</td>';
				str+='<td>'+value.comment+'</td>';
			  str+='</tr>';
		  });
		 str+='</tbody>';
		str+='</table>';

	}

	$('#thirdPartySummary').html(str);
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


function getBasicSurveyUserLoction()
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
		task : "getBasicInfo"
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
     buildSurveyBasicUserStatus(result,userTypeId);
	
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
	if(userType ==1){
	str+='<th>DCName</th>';
	}else if (userType ==4){
	str+='<th>DVName</th>';
	}else if (userType ==10){
		   str+='<th>TPName</th>';
		}
	str+='<th>Leader Name</th>';
	str+='<th>Booth</th>';
	str+='<th> Total Voters</th>';
//	str+='<th style="text-align : center;">Data Collector</th>';
	//str+='<th style="text-align : center;">Web monitoring</th>';
	if(userType ==1)
	{
    str+='<th>DC Caste Mapped</th>';
	str+='<th>DC Hamlet Mapped</th>';
	str+='<th>DC HouseNos Mapped</th>';
	str+='<th>DC Mobile Collected</th>';
	}
else if(userType ==4)
	{
    str+='<th>DV Caste Mapped</th>';
	str+='<th>DV Hamlet Mapped</th>';
	str+='<th>DV HouseNos Mapped</th>';
	str+='<th>DV Mobile Collected</th>';
	}
	else if(userType ==10)
	{
	str+='<th>TP Caste Mapped</th>';
	str+='<th>TP Hamlet Mapped</th>';
	str+='<th>TP HouseNos Mapped</th>';
	str+='<th>TP Mobile Collected</th>';
	
	}
	str+='<th>TOTAL </th>';
	if(userType ==1 || userType ==4){
		str+='<th>Mobile MATCHED</th>';
		str+='<th>Mobile UN MATCHED</th>';
		str+='<th>MOBILE ERROR %</th>';
		str+='<th>CASTE MATCHED</th>';
		str+='<th>CASTE UN MATCHED</th>';
		str+='<th>CASTE ERROR %</th>';
	}else if(userType ==10)
	{
        str+='<th>WM Wrong</th>';
		  str+='<th>WM ERROR %</th>';
		  str+='<th>TP ERROR</th>';
		  str+='<th>TP ERROR %</th>';
		  str+='<th>Same Caste</th>';
		  str+='<th>Same Caste %</th>';
	      str+='<th>New Castes Collected</th>';
	}
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
				if(userType !=10){
				  str+='<td> <a href="javascript:{getDataCollectorInfo('+result[i].userid+',\''+result[i].userName+'\','+result[i].mobileNo+',\''+result[i].verifier.name+'\','+result[i].verifier.verified+','+result[i].subList[j].boothId+','+result[i].subList[j].partNo+',\''+$('#FielddateId').val()+'\','+userType+',1);}">'+result[i].userName+' </a><br>'+result[i].mobileNo+'</td>';
				}else{
				  str+='<td> <a href="javascript:{getThirdPartyProvidedAndCollectedDetails('+result[i].userid+','+result[i].subList[j].boothId+');}">'+result[i].userName+' </a><br>'+result[i].mobileNo+'</td>';
				}
			str+='<td> '+result[i].verifier.name+'<br>'+result[i].verifier.verified+'</td>';
			}
			else
			{
				if(userType !=10){
				   str+='<td> <a href="javascript:{getDataCollectorInfo('+result[i].userid+',\''+result[i].userName+'\','+result[i].mobileNo+',null,null,'+result[i].subList[j].boothId+','+result[i].subList[j].partNo+',\''+$('#FielddateId').val()+'\','+userType+',1);}">'+result[i].userName+' </a><br>'+result[i].mobileNo+'</td>';
				}else{
					  str+='<td> <a href="javascript:{getThirdPartyProvidedAndCollectedDetails('+result[i].userid+','+result[i].subList[j].boothId+');}">'+result[i].userName+' </a><br>'+result[i].mobileNo+'</td>';
				}
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
			if(result[i].subList[j].houseNoCount == null)
			{
				str+='<td>-</td>';
			}
			else
			{
			str+='<td>'+result[i].subList[j].houseNoCount+'</td>';
			}		
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
		if(userType !=10){
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
		}else{
		    str+='<td>'+result[i].subList[j].mobileMatchedCount+'</td>';
		    if(result[i].subList[j].mobileMatchedCount > 0 && result[i].subList[j].casteCount != null && result[i].subList[j].casteCount > 0){
			    var wMError = (Math.round(result[i].subList[j].mobileMatchedCount * 100)/result[i].subList[j].casteCount).toFixed(2);
				str+='<td>'+wMError+'</td>';
				/*if(wMError>=0.0 && wMError<=0.50){
					str+='<td class="errorGreen">'+wMError+'</td>';
				}else if(wMError>=0.51 && wMError<=1.00){
					str+='<td class="errorLgreen">'+wMError+'</td>';
				}else if(wMError>=1.01 && wMError<=2.00){
					str+='<td class="errorYellow">'+wMError+'</td>';
				}else if(wMError>=2.01){
					str+='<td class="errorRed">'+wMError+'</td>';
				}else{
					str+='<td>'+wMError+'</td>';
				}*/
			}else{
			   str+='<td> - </td>';
			}
			str+='<td>'+result[i].subList[j].casteNotMatchedCount+'</td>';
			if(result[i].subList[j].casteNotMatchedCount > 0  && result[i].subList[j].casteCount != null && result[i].subList[j].casteCount > 0){
			    var tPError = (Math.round(result[i].subList[j].casteNotMatchedCount * 100)/result[i].subList[j].casteCount).toFixed(2);
				str+='<td>'+tPError+'</td>';
				/*if(tPError>=0.0 && tPError<=0.50){
					str+='<td class="errorGreen">'+tPError+'</td>';
				}else if(tPError>=0.51 && tPError<=1.00){
					str+='<td class="errorLgreen">'+tPError+'</td>';
				}else if(tPError>=1.01 && tPError<=2.00){
					str+='<td class="errorYellow">'+tPError+'</td>';
				}else if(tPError>=2.01){
					str+='<td class="errorRed">'+tPError+'</td>';
				}else{
					str+='<td>'+tPError+'</td>';
				}*/
			}else{
			   str+='<td> - </td>';
			}
			str+='<td>'+result[i].subList[j].mobileNotMatchedCount+'</td>';
			if(result[i].subList[j].mobileNotMatchedCount > 0  && result[i].subList[j].casteCount != null && result[i].subList[j].casteCount > 0){
				
			    var sameCasteError = (Math.round(result[i].subList[j].mobileNotMatchedCount * 100)/result[i].subList[j].casteCount).toFixed(2);
				str+='<td>'+sameCasteError+'</td>';
				/*if(sameCasteError>=0.0 && sameCasteError<=0.50){
					str+='<td class="errorGreen">'+sameCasteError+'</td>';
				}else if(sameCasteError>=0.51 && sameCasteError<=1.00){
					str+='<td class="errorLgreen">'+sameCasteError+'</td>';
				}else if(sameCasteError>=1.01 && sameCasteError<=2.00){
					str+='<td class="errorYellow">'+sameCasteError+'</td>';
				}else if(sameCasteError>=2.01){
					str+='<td class="errorRed">'+sameCasteError+'</td>';
				}else{
					str+='<td>'+sameCasteError+'</td>';
				}*/
			}else{
			   str+='<td> - </td>';
			}
			 str+='<td>'+result[i].subList[j].casteMatchedCount+'</td>';			
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
	str+='<th> HouseNos Mapped</th>';

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
			if(result[i].subList[j].houseNoCount == null)
			{
				str+='<td>-</td>';
			}
			else
			{
			str+='<td>'+result[i].subList[j].houseNoCount+'</td>';
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
	 maxDate: new Date()
   }).datepicker('setDate', new Date());
  
});
 function buildSurveyBasicUserStatus(result,userTypeId)
{

	var userType = userTypeId;
	
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
	if(userType ==1){
	str+='<th>DCName</th>';
	}else if (userType ==4){
	str+='<th>DVName</th>';
	}else if (userType ==10){
		   str+='<th>TPName</th>';
		}
	str+='<th>Leader Name</th>';
	str+='<th>Booth</th>';
	str+='<th> Total Voters</th>';
//	str+='<th style="text-align : center;">Data Collector</th>';
	//str+='<th style="text-align : center;">Web monitoring</th>';
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
				if(userType !=10){
				  str+='<td> <a href="javascript:{getDataCollectorInfo('+result[i].userid+',\''+result[i].userName+'\','+result[i].mobileNo+',\''+result[i].verifier.name+'\','+result[i].verifier.verified+','+result[i].subList[j].boothId+','+result[i].subList[j].partNo+',\''+$('#FielddateId').val()+'\','+userType+',1);}">'+result[i].userName+' </a><br>'+result[i].mobileNo+'</td>';
				}else{
				  str+='<td> <a href="javascript:{getThirdPartyProvidedAndCollectedDetails('+result[i].userid+','+result[i].subList[j].boothId+');}">'+result[i].userName+' </a><br>'+result[i].mobileNo+'</td>';
				}
			str+='<td> '+result[i].verifier.name+'<br>'+result[i].verifier.verified+'</td>';
			}
			else
			{
				if(userType !=10){
				   str+='<td> <a href="javascript:{getDataCollectorInfo('+result[i].userid+',\''+result[i].userName+'\','+result[i].mobileNo+',null,null,'+result[i].subList[j].boothId+','+result[i].subList[j].partNo+',\''+$('#FielddateId').val()+'\','+userType+',1);}">'+result[i].userName+' </a><br>'+result[i].mobileNo+'</td>';
				}else{
					  str+='<td> <a href="javascript:{getThirdPartyProvidedAndCollectedDetails('+result[i].userid+','+result[i].subList[j].boothId+');}">'+result[i].userName+' </a><br>'+result[i].mobileNo+'</td>';
				}
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
				$("#userFieldImage").hide();
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

	
	$("#userFieldImage").show();	
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
	str += '<h4> </h4>';
	str+='<table class="table table-bordered m_top20 table-hover table-striped" id="dayreport">';
    str+='<thead class="alert alert-success">';
	 str+='<tr>';
	  str+='<th>USER NAME</th>';
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
			if(value.userTypeId == 1)
			{
			str+='<td><a onClick="openTrackinWindow('+value.userid+',\''+$('#dateId').val()+'\',1,1) " style="cursor: pointer;"><img src="images/DC.png"></img></a></td>';
			str+='<td><a onClick="openTrackinWindow('+value.userid+',\''+$('#dateId').val()+'\',2,1) " style="cursor: pointer;"><img src="images/DC.png"></img></a></td>';
			}
			else
			{
			str+='<td><a onClick="openTrackinWindow('+value.userid+',\''+$('#dateId').val()+'\',1,2) " style="cursor: pointer;"><img src="images/DV.png"></img></a></td>';
			str+='<td><a onClick="openTrackinWindow('+value.userid+',\''+$('#dateId').val()+'\',2,2) " style="cursor: pointer;"><img src="images/DV.png"></img></a></td>';
			}
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

function openTrackinWindow(userId,date,id,imgType)
{
	window.open('userWiseTrackingAction.action?userId='+userId+'&date='+date+'&userTypeId='+id+'&leaderId='+imgType+'');
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
				
				$('#collecedCountDiv').html('<span style="font-weight:bold;"> Collected  </span> : '+result[0].mobileCount+'</br>');
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
		$('#errDivIdForReport').html('Please Select Constituency');
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
				if(statusId == 9 || statusId == 10){
					for(var j in result[0].subList){
						str +='<th>'+result[0].subList[j].status+'</th>';
				}
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
					if(result[i].mobileNoCount == null || result[i].mobileNoCount == "null")
						str += '<td>0</td>';
					else
					    str += '<td>'+result[i].mobileNoCount+'</td>';
					var castePer = ((result[i].casteCount * 100)/result[i].totalVoters).toFixed(2);
					var hamletPer = ((result[i].hamletCount * 100)/result[i].totalVoters).toFixed(2);
					str += '<td>'+result[i].casteCount+'('+castePer+'%)</td>';
					str += '<td>'+result[i].hamletCount+'('+hamletPer+'%)</td>';
					if(statusId == 3){
						str += '<td>'+result[i].casteErrorPercent+'</td>';
					}
					if(statusId == 9 || statusId == 10){
				
					for(var j in result[i].subList){
					var percent = ((result[i].subList[j].count * 100)/result[i].totalVoters).toFixed(2);
					
						str +='<td>'+result[i].subList[j].count+'('+percent+'%)</td>';
						
					}
					}
					
					str += '<td><select id="boothStatus" style="width:150px;" onChange="updateBoothStatusDetails(this.value,'+result[i].boothId+','+i+')"><option>Select Status</option>';
					
				if(statusId < 6)
				{

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
				}else
				{
					if(statusId == 6)
					{
						str += '<option value="6" selected="selected" >TP READY</option>';
					}
					else
					{
						str += '<option value="6">TP READY</option>';
					}
					if(statusId == 7)
					{
						str += '<option value="7" selected="selected" >TP PROCESS</option>';
					}
					else
					{
						str += '<option value="7">TP PROCESS</option>';
					}
					if(statusId == 8)
					{
						str += '<option value="8" selected="selected" >TP COMPLETED</option>';
					}
					else
					{
						str += '<option value="8">TP COMPLETED</option>';
					}

					if(statusId == 9)
					{
						str += '<option value="9" selected="selected" >TP-WM PROCESSING</option>';
					}
					else
					{
						str += '<option value="9">TP-WM PROCESSING</option>';
					}

					if(statusId == 10)
					{
						str += '<option value="10" selected="selected" >TP-WM COMPLETED</option>';
					}
					else
					{
						str += '<option value="10">TP-WM COMPLETED</option>';
					}

					if(statusId == 11)
					{
						str += '<option value="11" selected="selected" >READY FOR REVIEW</option>';
					}
					else
					{
						str += '<option value="11">READY FOR REVIEW</option>';
					}
					
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
	$('#userReportUser').find('option').remove();
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
				//$('#userReportUser').append('<option value="0"> Select User </option>');
				if(result != null && result.length>0){
					for(var i in result){
						$('#userReportUser').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
					}
					//$('#userReportUser').multiselect('refresh');
				}

		$('#userReportUser').multiselect('refresh');		
	});
}

function getUserWiseReport()
{
	var userTypeId = $("#userReportUserType").val();
	var surveyUserId = $( "#userReportUser" ).val();
	var fromDate = $("#fromDateForUserReport").val();
	var toDate = $("#toDateForUserReport").val();
	$("#userReportTable").html("");
	if(userTypeId == 0)
	{
		$("#errorDiv").html("Please Select User Type").css("color","red");
		return;
	}
	if(surveyUserId == 0)
	{
		$("#errorDiv").html("Please Select User").css("color","red");
		return;
	}
	if(fromDate.length == 0)
	{
		$("#errorDiv").html("Please Select From Date").css("color","red");
		return;
	}
	if( toDate.length == 0)
	{
		$("#errorDiv").html("Please Select To Date").css("color","red");
		return;
	}
	if(fromDate.length > 0 && toDate.length > 0 )
	{		    
		  var dt1  = parseInt(fromDate.substring(0,2),10);
		  var mon1 = parseInt(fromDate.substring(3,5),10);
		  var yr1  = parseInt(fromDate.substring(6,10),10);
		  var dt2  = parseInt(toDate.substring(0,2),10);
		  var mon2 = parseInt(toDate.substring(3,5),10);
		  var yr2  = parseInt(toDate.substring(6,10),10);
		  var date1 = new Date(yr1, mon1, dt1);
		  var date2 = new Date(yr2, mon2, dt2);

		if(date2 < date1)
		{ 
		 $('#errorDiv').html("From Date should be Less Than To Date");
		 return;
		}
	}
	$('#errorDiv').html("");
	$('#userWiseReportImg').show();
	var jsObj={
		constitiencyIds:$('#userReportUserConstituency').val(),
		userTypeId:userTypeId,
		fromDate : fromDate,
		toDate :toDate,
		surveyUserId :surveyUserId
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
	str+='<th> User Name </th>';
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
		str+='<td>'+result[i].name+'</td>';
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
	$('#userReportTable').dataTable({
		"iDisplayLength": 100,
		"aLengthMenu": [[100, 200, 500, -1], [100, 200, 500, "All"]]
	});
	
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
	$("#errorDivSB").html("");
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
		//str+='<th>VOTER ID</th>';
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
				//str+='<td>'+result[i].matchedList[j].voterId+'</td>';
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
				//str+='<td>'+result[i].unMatchedList[j].voterId+'</td>';
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
				//str+='<td>'+result[i].notVerifiedList[j].voterId+'</td>';
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
				  str+='<th> Total Booths </th>';	
				  str+='<th> Total Voters </th>';	
				  str+='<th> Caste Tag Booths </th>';
				  str+='<th> Caste Tag Voters </th>';
				  str+='<th> Verified Booths </th>';
				  str+='<th> Caste Error Rate </th>';
				  str+='<th> Mobile Error Rate </th>';
				  //str+=' <th>Empty Fields</th>';
				 str+='</thead>';
				 str+='<tbody>';
				  for(var i in result)
				  {
					  str+='<tr>';
					str+='<td>'+result[i].constituency+'</td>';
					if(result[i].boothCount != null )
					{
						str+='<td>'+result[i].boothCount+'</td>';
					}
					else
					{
						str+='<td>-</td>';
					}
					if(result[i].totalCount != null )
					{
						str+='<td>'+result[i].totalCount+'</td>';
					}
					else
					{
						str+='<td>-</td>';
					}
					if(result[i].casteTagedBooths != null )
					{
						str+='<td>'+result[i].casteTagedBooths+'</td>';
					}
					else
					{
						str+='<td>-</td>';
					}
					if(result[i].casteCount != null )
					{
						str+='<td>'+result[i].casteCount+'</td>';
					}
					else
					{
						str+='<td>-</td>';
					}
					if(result[i].casteVerifiedBooths != null )
					{
						str+='<td>'+result[i].casteVerifiedBooths+'</td>';
					}
					else
					{
						str+='<td>-</td>';
					}
					if(result[i].casteErrorRate == null)
						str+='<td>-</td>';
					else
						str+='<td>'+result[i].casteErrorRate+'</td>';
					if(result[i].mobileErrorRate == null)
						str+='<td>-</td>';
					else
						str+='<td>'+result[i].mobileErrorRate+'</td>';
					//var count = result[i].totalCount - result[i].casteCount;
					//str+='<td>'+count+'</td>';
					
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
$("#tableDiv").html('');
if(constituencyId == 0)
{
 $("#errDivIdForStartTime").html("Please Select Constituency");
 return;
}
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
	$('#tableDtailsDiv').html('');
	$('#basicStatusReport').html('');
	$('#panchayatDetailsDiv').html('');
	if($('#reportConstituencyId').val() ==0)
	{
      $("#constnErrDiv").html('<font color="#FF0000">Please Select Constituency</font>');
	  return;
	}
	$("#constnErrDiv").html('');
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
		str += '<h4>THIRD PARTY READY</h4>';
		str += '<h2> <a href="javascript:{getBoothsDetailsByStatusAndConstituency(6)}">'+result.thirdpartyReady+'</a></h2>';
		str += '</hgroup>';
		str += '</li>';

		str += '<li>';
		str += '<hgroup>';
		str += '<h4>THIRD PARTY PROCESSING</h4>';
		str += '<h2> <a href="javascript:{getBoothsDetailsByStatusAndConstituency(7)}">'+result.thirdPartyProcessing+'</a></h2>';
		str += '</hgroup>';
		str += '</li>';

		str += '<li>';
		str += '<hgroup>';
		str += '<h4>THIRD PARTY COMPLETED</h4>';
		str += '<h2> <a href="javascript:{getBoothsDetailsByStatusAndConstituency(8)}">'+result.thirdPartyCompleted+'</a></h2>';
		str += '</hgroup>';
		str += '</li>';

		
		str += '<li>';
		str += '<hgroup>';
		str += '<h4>THIRD PARTY WEB MONITORING PROCESSING</h4>';
		str += '<h2> <a href="javascript:{getBoothsDetailsByStatusAndConstituency(9)}">'+result.tpWebMonitoringProcessing+'</a></h2>';
		str += '</hgroup>';
		str += '</li>';

		str += '<li>';
		str += '<hgroup>';
		str += '<h4>THIRD PARTY WEB MONITORING COMPLETED</h4>';
		str += '<h2> <a href="javascript:{getBoothsDetailsByStatusAndConstituency(10)}">'+result.tpWebMonitoringCompleted+'</a></h2>';
		str += '</hgroup>';
		str += '</li>';	

		str += '<li>';
		str += '<hgroup>';
		str += '<h4>READY FOR REVIEW</h4>';
		str += '<h2> <a href="javascript:{getBoothsDetailsByStatusAndConstituency(11)}">'+result.readyForReviewCount+'</a></h2>';
		str += '</hgroup>';
		str += '</li>';	

		str += '<li>';
		str += '<hgroup>';
		str += '<h4>ACTUAL VERIFICATION PROCESSING</h4>';
		str += '<h2> <a href="javascript:{getBoothsDetailsByStatusAndConstituency(0)}">'+result.actualProcessingCount+'</a></h2>';
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
	//getMatchecUnMatchedDetails();
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
	$("#boothImageForWm").show();
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
				$("#boothImageForWm").hide();
				$('#'+divId+'').find('option:not(:first)').remove();

				$.each(result,function(index,value){
					$('#'+divId+'').append('<option value="'+value.id+'">Booth - '+value.name+'</option>');
				});
				
				$('#'+divId+'').multiselect('refresh');

		});	
}
function getMatchecUnMatchedDetails()
{
	$('#stateStatusAjax').show();
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
		$('#stateStatusAjax').hide();  
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
	
	str += '<table class="table table-bordered m_top20 table-hover table-striped" id="matchedUnMatchedTable1" style="display:none;">';
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
	/*if($("#constituencyForWm").val() == 0)
	{
		$("#errorDivForWM").html("<font color='#FF0000'>Select Constituency</font>");
		return;
	}
	if($('#boothIdForWm').val()  == 0)
	{
		 $("#errorDivForWM").html('<font color="#FF0000">Select Booth</font>');
		 return;
	}*/
	 $("#errorDivForWM").html("");
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

	$("#boothImageForVerifier").show();
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
				$("#boothImageForVerifier").hide();
				$('#'+divId+'').find('option:not(:first)').remove();

				$.each(result,function(index,value){
					$('#'+divId+'').append('<option value="'+value.id+'">Booth - '+value.name+'</option>');
				});
				
				$('#'+divId+'').multiselect('refresh');

		});	
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
			  
				  str+='<table class="table table-bordered m_top20 table-hover table-striped" id="fieldReportSummaryTableId">';
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
					$("#fieldReportSummaryTableId").dataTable();
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
//getFinalReport();
function getFinalReport()
{
	$('#dayWiseReportDiv1').html('');
	var constituencyId = $('#constituencyIdForVerfication').val();
	if(constituencyId == 0)
	{
	 $("#errorDivForVerification").html("<font color='#FF0000'>Please Select Constituency</font>");
	 return;
	}
	 $("#errorDivForVerification").html("");
	$('#mainajaximg').show();
	var jsObj = 
	{
		constituencyId : constituencyId
	}
	$.ajax({
			type:'GET',
			url: 'finalDeselectionReport.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)},
		 }).done(function(result){
			$('#mainajaximg').hide();		 
			if(result != null)
			buildFinalReport(result)
		});	
}

function buildFinalReport(result)
{
	var str = '';
	str += '<table  class="table table-bordered m_top20 table-hover table-striped" id="daywisereportTableIdTemp">';
	str += '<tr>';
	str += '<th>Booth</th>';
	str += '<th>Total Voters</th>';
	str += '<th>DC Caste Mapped</th>';
	str += '<th>DC Caste Mapped %</th>';
	str += '<th>DC Hamlet Mapped</th>';
	str += '<th>DC Hamlet Mapped %</th>';
	str += '<th>DC Mobile Collected</th>';
	str += '<th>WM-DC TOTAL</th>';
	str += '<th>WM-DC Mobile MATCHED</th>';
	str += '<th>WM-DC Mobile UN MATCHED</th>';
	str += '<th>WM-DC MOBILE ERROR %</th>';
	str += '<th>WM-DC CASTE MATCHED</th>';
	str += '<th>WM-DC CASTE UN MATCHED</th>';
	str += '<th>WM-DC CASTE ERROR %</th>';
	str += '<th>DV-MATCHED</th>';
	str += '<th>DV-MATCHED %</th>';
	str += '<th>DV-UN MATCHED</th>';
	str += '<th>DV-UN MATCHED %</th>';
	str += '<th>DV-NOT VERIFIED</th>';
	str += '<th>DV-NOT VERIFIED %</th>';
	str += '<th>WM-DV Y</th>';
	str += '<th>WM-DV N</th>';
	str += '<th>WM-DV EMPTY</th>';
	str += '</tr>';
	for(var i in result)
	{
		str += '<tr>';
		str += '<td>'+result[i].partNo+'</td>';
		str += '<td>'+result[i].totalVoters+'</td>';
		if(result[i].dcCasteMapped != null)
		{
			if(!isNaN(result[i].dcCasteMapped))
			{
				str += '<td>'+result[i].dcCasteMapped+'</td>';
			}
			else
			{
				str += '<td>0</td>';
			}
		}
		else
		{
			str += '<td>0</td>';
		}
		if(result[i].dcCasteMapped != null)
		{
			if(!isNaN(Math.round(result[i].dcCasteMapped * 100)/result[i].totalVoters))
			{
				str += '<td>'+(Math.round(result[i].dcCasteMapped * 100)/result[i].totalVoters).toFixed(2);+'</td>';
			}
			else
			{
				str += '<td>0.00</td>';
			}
		}
		else
		{
			str += '<td>0.00</td>';
		}
		
		if(result[i].dcHamletMapped != null)
		{
			if(!isNaN(result[i].dcHamletMapped))
			{
				str += '<td>'+result[i].dcHamletMapped+'</td>';
			}
			else
			{
				str += '<td>0</td>';
			}
		}
		else
		{
			str += '<td>0</td>';
		}
		if(result[i].dcHamletMapped != null)
		{
			if(!isNaN(Math.round(result[i].dcHamletMapped * 100)/result[i].totalVoters))
			{
				str += '<td>'+(Math.round(result[i].dcHamletMapped * 100)/result[i].totalVoters).toFixed(2);+'</td>';
			}
			else
			{
				str += '<td>0.00</td>';
			}
		}
		
		else
		{
			str += '<td>0.00</td>';
		}
		
		if(result[i].dcMobileMapped != null)
		{
			if(!isNaN(result[i].dcMobileMapped))
			{
				str += '<td>'+result[i].dcMobileMapped+'</td>';
			}
			else
			{
				str += '<td>0</td>';
			}
		}
		else
		{
			str += '<td>0</td>';
		}
		
		if(result[i].wmDcTotal != null)
		{
			if(!isNaN(result[i].wmDcTotal))
			{
				str += '<td>'+result[i].wmDcTotal+'</td>';
			}
			else
			{
				str += '<td>0</td>';
			}
		}
		else
		{
			str += '<td>0</td>';
		}
		if(result[i].wmDcMobileMapped != null)
		{
			if(!isNaN(result[i].wmDcMobileMapped))
			{
				str += '<td>'+result[i].wmDcMobileMapped+'</td>';
			}
			else
			{
				str += '<td>0</td>';
			}
		}
		else
		{
			str += '<td>0</td>';
		}
		if(result[i].wmDcMobileUnMapped != null)
		{
			if(!isNaN(result[i].wmDcMobileUnMapped))
			{
				str += '<td>'+result[i].wmDcMobileUnMapped+'</td>';
			}
			else
			{
				str += '<td>0</td>';
			}
		}
		else
		{
			str += '<td>0</td>';
		}
		if(result[i].wmDcMobileMapped != null  && result[i].wmDcMobileUnMapped != null)
		{
			if(!isNaN(Math.round(result[i].wmDcMobileUnMapped * 100)/(result[i].wmDcMobileUnMapped + result[i].wmDcMobileMapped)))
			{
				str += '<td>'+(Math.round(result[i].wmDcMobileUnMapped * 100)/(result[i].wmDcMobileUnMapped + result[i].wmDcMobileMapped)).toFixed(2);+'</td>';
			}
			else
			{
				str += '<td>0.00</td>';
			}
		}
		else
		{
			str += '<td>0.00</td>';
		}
		if(result[i].wmDcCasteMapped != null)
		{
			if(!isNaN(result[i].wmDcCasteMapped))
			{
				str += '<td>'+result[i].wmDcCasteMapped+'</td>';
			}
			else
			{
				str += '<td>0</td>';
			}
		}
		else
		{
			str += '<td>0</td>';
		}
		if(result[i].wmDcCasteUnMapped != null)
		{
			if(!isNaN(result[i].wmDcCasteUnMapped))
			{
				str += '<td>'+result[i].wmDcCasteUnMapped+'</td>';
			}
			else
			{
				str += '<td>0</td>';
			}
		}
		else
		{
			str += '<td>0</td>';
		}

		if(result[i].wmDcCasteMapped != null && result[i].wmDcCasteUnMapped != null)
		{
			if(!isNaN(Math.round(result[i].wmDcCasteUnMapped * 100)/(result[i].wmDcCasteUnMapped + result[i].wmDcCasteMapped)))
			{
				str += '<td>'+(Math.round(result[i].wmDcCasteUnMapped * 100)/(result[i].wmDcCasteUnMapped + result[i].wmDcCasteMapped)).toFixed(2)+'</td>';
			}
			else
			{
				str += '<td>0.00</td>';
			}
			
		}
		else
		{
			str += '<td>0.00</td>';
		}
		
		if(result[i].matchedCount != null)
		{
			if(!isNaN(result[i].matchedCount))
			{
				str += '<td>'+result[i].matchedCount+'</td>';
			}
			else
			{
				str += '<td>0</td>';
			}
		}
		else
		{
			str += '<td>0</td>';
		}
		if(result[i].matchedCount != null)
		{
		
			if(!isNaN(Math.round(result[i].matchedCount * 100)/result[i].totalVoters))
			{
				str += '<td>'+(Math.round(result[i].matchedCount * 100)/result[i].totalVoters).toFixed(2)+'</td>';
			}
			else
			{
				str += '<td>0.00</td>';
			}
		}
		else
		{
			str += '<td>0.00</td>';
		}
		
		if(result[i].unMatchedCount != null)
		{
			if(!isNaN(result[i].unMatchedCount))
			{
				str += '<td>'+result[i].unMatchedCount+'</td>';
			}
			else
			{
				str += '<td>0</td>';
			}
		}
		else
		{
			str += '<td>0</td>';
		}
		if(result[i].unMatchedCount != null)
		{
		
			if(!isNaN(Math.round(result[i].unMatchedCount * 100)/result[i].totalVoters))
			{
				str += '<td>'+(Math.round(result[i].unMatchedCount * 100)/result[i].totalVoters).toFixed(2)+'</td>';
			}
			else
			{
				str += '<td>0.00</td>';
			}
			
		}
		else
		{
			str += '<td>0.00</td>';
		}
		
		if(result[i].notIdentifedCount != null)
		{
			if(!isNaN(result[i].notIdentifedCount))
			{
				str += '<td>'+result[i].notIdentifedCount+'</td>';
			}
			else
			{
				str += '<td>0</td>';
			}
		}
		else
		{
			str += '<td>0</td>';
		}
		if(result[i].notIdentifedCount != null)
		{
			if(!isNaN(Math.round(result[i].notIdentifedCount * 100)/result[i].totalVoters))
			{
				str += '<td>'+(Math.round(result[i].notIdentifedCount * 100)/result[i].totalVoters).toFixed(2)+'</td>';
			}
			else
			{
				str += '<td>0.00</td>';
			}
			
		}
		else
		{
			str += '<td>0.00</td>';
		}
		if(result[i].wmDvY != null)
		{
			if(!isNaN(result[i].wmDvY))
			{
				str += '<td>'+result[i].wmDvY+'</td>';
			}
			else
			{
				str += '<td>0</td>';
			}
		}
		else
		{
			str += '<td>0</td>';
		}
		if(result[i].wmDvN != null)
		{
			if(!isNaN(result[i].wmDvN))
			{
				str += '<td>'+result[i].wmDvN+'</td>';
			}
			else
			{
				str += '<td>0</td>';
			}
		}
		else
		{
			str += '<td>0</td>';
		}
		if(result[i].wmDvEmpty != null)
		{
			if(!isNaN(result[i].wmDvEmpty))
			{
				str += '<td>'+result[i].wmDvEmpty+'</td>';
			}
			else
			{
				str += '<td>0</td>';
			}
		}
		else
		{
			str += '<td>0</td>';
		}
	
		str += '</tr>';
	}
	$('#mainajaximg').hide();
	$('#excelTableID1').show();
	str += '</table>';
	$('#dayWiseReportDiv1').html(str);
	$('#daywisereportTableIdTemp').dataTable();
}

//getThirdPartyFinalDetails();
function getThirdPartyFinalDetails()
{
	var jsObj = 
	{
		boothId : 439822
	}
	$.ajax({
			type:'GET',
			url: 'getThirdPartyFinalDetails.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)},
		 }).done(function(result){	
		});
}
//getThirdPartyProvidedAndCollectedDetails();
function getThirdPartyProvidedAndCollectedDetails(surveyuserId,boothId)
{
 $('#voterInfoDIv').html("");
$('#boothWiseTab,#startTimeTab').removeClass('selected');
	$('#callCenterTab').addClass('selected');
	showHideTabs('callCenterTab');
	var jsObj = 
	{
		boothId : boothId,
		surveyuserId : surveyuserId
	}
	$('#searchDataImg').show();
	$.ajax({
			type:'GET',
			url: 'getCompressionReportForThirdParty.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)},
		 }).done(function(result){	
		   $('#searchDataImg').hide();
		   if(result != null && result.length>0){
		      var str ="<div id='tpReportBasicInfo'></div>";
			   str  +='<input type="hidden" id="boothIdHidden" value="'+boothId+'">';
			    str  +='<input type="hidden" id="userIdHidden" value="'+surveyuserId+'">';
		       str  +='<table class="table table-bordered m_top20 table-hover table-striped" id="voterDetlsTab" >';
				str +='	<thead class="alert alert-success">';
				str +='	<tr>';
				
					str +='<th> <input type="checkbox" id="thirdPartyChkBox" class="allCheckbxCls"  onclick="checkAllThirdPartyChkBoxes();"/> Select All </th>';
					str +='<th>S.NO</th>';
					str +='<th>VOTER NAME</th>';
					str +='<th>RELATIVE NAME</th>';
					str +='<th>H.NO</th>';
					str +='<th>GENDER</th>';
					str +='<th>AGE</th>';
					str +='<th>WM CASTE</th>';
					str +='<th>TP CASTE</th>';
					str +='<th>MATCHED STATUS</th>';
					str +='<th>MOBILE NO</th>';
					str +='<th>STATUS</th>';
					str +='<th>COMMENT</th>';
					str +='</tr>';
				    str +='</thead>';
				    str +='<tbody>';
					 for(var i in result){
			          str +='<tr>';
					   str +='<td><input type="checkbox" id="tPCommentCheck'+i+'" key="'+i+'" class="thirdPartyChkAllVoters" value="'+result[i].voterId+'"></input></td>';
					   str +='<td>'+result[i].serialNo+'</td>';
					   str +='<td>'+result[i].voterName+'</td>';
					   str +='<td>'+result[i].relativeName+'</td>';
					   str +='<td>'+result[i].houseNo+'</td>';
					   str +='<td>'+result[i].gender+'</td>';
					   str +='<td>'+result[i].age+'</td>';
					   str +='<td>'+result[i].tpCaste+'</td>';
					   str +='<td>'+result[i].wmCaste+'</td>';
					   str +='<td>'+result[i].matchedStatus+'</td>';
					   str +='<td>'+result[i].mobileNo+'</td>';
					   str +='<td>';
					    if(result[i].status != null){
						  if(result[i].status == 1)	
						  {
							 str +='<select id="tPmultipleupdtStsId'+i+'" disabled="disabled" onchange="updateThirdPartyDetails('+result[i].voterId+',this.value);">';
						  }
						  else
						  {
							 str +='<select id="tPmultipleupdtStsId'+i+'" onchange="updateThirdPartyDetails('+result[i].voterId+',this.value);">';
						  }
					     
					              str +='<option value="0">Select Status</option>';
								  if(result[i].status == 1){
								     str +='<option value="1" selected="selected">Matched</option>';
								  }else{
								     str +='<option value="1">Matched</option>';
								  }
								  if(result[i].status == 2){
								     str +='<option value="2" selected="selected">WM Wrong</option>';
								  }else{
								     str +='<option value="2">WM Wrong</option>';
								  }
								  if(result[i].status == 3){
								     str +='<option value="3" selected="selected">TP Wrong</option>';
								  }else{
								     str +='<option value="3">TP Wrong</option>';
								  }
								  if(result[i].status == 4){
								     str +='<option value="4" selected="selected">Same Caste</option>';
								  }else{
								     str +='<option value="4">Same Caste</option>';
								  }
								  if(result[i].status == 7){
									     str +='<option value="4" selected="selected">New Caste</option>';
								  }else{
									     str +='<option value="4">New Caste</option>';
									  }
					      str +='</select>';
						  if(result[i].status == 1)	
						  {
							str += '<a class="btn btn-success" style = "float: right; width: 40px; height: 17px;" onClick="enableSelectBox('+i+')">Enable</a>';
						  }
						 }else{
						   str +='<select id="tPmultipleupdtStsId'+i+'" onchange="updateThirdPartyDetails('+result[i].voterId+',this.value);">';
					              str +='<option value="0">Select Status</option>';
								  str +='<option value="1">Matched</option>';
								  str +='<option value="2">WM Wrong</option>';
								  str +='<option value="3">TP Wrong</option>';
								  str +='<option value="4">Same Caste</option>';
								   str +='<option value="7">New Caste</option>';
					       str +='</select>';
						 }
					    str +='</td>';
						 str +='<td><textarea id="tPCommentArea'+i+'">'+result[i].comment+'</textarea><br /><input type="button" id="tPCommentSubmit'+i+'" value="Submit" class="btn btn-success" onclick="updateTPComment('+i+')"/><img style="display:none;"  src="images/icons/search.gif" id="tPCommentSubmitImg'+i+'"/></td>';
					  str +='</tr>';
			         }
					str +='</tbody>';
					str +='</table>';
					str +='<div id="scrollBtnDiv" >';
				    str +='<a id="tPUpdateBtnInNewWndow" style="position: fixed; left :0px; top: 320px;" href="javascript:{tPUpdateVoterCommentStatus();}" class="btn btn-primary"> Update Details </a>';
				    str +='</div>';
		        $('#voterInfoDIv').html(str);
				$('#voterDetlsTab').dataTable({
				"iDisplayLength": 100,
				"aLengthMenu": [[100, 200, 500, -1], [100, 200, 500, "All"]]
				});
				buildThirdPartyBasicData(result[0]);
		   }else{
		       var str ="";
		       str +='	No Data Available. ';
			    $('#voterInfoDIv').html(str);
		   }
		});
}

function enableSelectBox(id)
{
	//$('#'+id+'').removeAttr('disabled');
	$('#tPmultipleupdtStsId'+id+'').removeAttr('disabled');
}
function getLatestBasicInfo(boothId,userId){
  var jsObj = 
	{
		boothId : boothId,
		userId : userId
	}
  $.ajax({
			type:'GET',
			url: 'getSurveyUserBasicInfo.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)},
		 }).done(function(result){
		   buildThirdPartyBasicDataNew(result);
		 });
}
function buildThirdPartyBasicData(myResult){
	var result = myResult.surveyResponceVO;
    var str="";
	str+='<div class="container">';
		str+='<div class="row">';
            str+='<div class="span12">';
				   str+=' <div class="row-fluid ">';
					    str+='<div class="row-fluid">';
								str+='<div  class="span3" style="background-color: #e9e9e9; padding: 5px;margin-top:25px;margin-bottom:10px;" class="errClass"><span style="font-weight:bold;"> TP NAME  </span>: '+result.voterName+'</div>';
								str+='<div  class="span3" style="background-color: #e9e9e9; padding: 5px;margin-top:25px;margin-bottom:10px;" class="errClass"><span style="font-weight:bold;"> TP MOBILE  </span>: '+result.longitude+'</div>';
								str+='<div  class="span3" style="background-color: #e9e9e9; padding: 5px;margin-top:25px;margin-bottom:10px;" class="errClass"><span style="font-weight:bold;"> TP LEAD  </span>: '+result.relativeName+'</div>';
								str+='<div  class="span3" style="background-color: #e9e9e9; padding: 5px;margin-top:25px;margin-bottom:10px;" class="errClass"><span style="font-weight:bold;"> TP LEAD MOBILE </span>: '+result.latitude+'</div>';
					    str+='</div>';
						 str+='<div class="row-fluid">';
								str+='<div  class="span3" style="background-color: #e9e9e9; padding: 5px;margin-top:25px;margin-bottom:10px;" class="errClass"><span style="font-weight:bold;"> BOOTH  </span>: '+result.casteName+'</div>';
								str+='<div  class="span3" style="background-color: #e9e9e9; padding: 5px;margin-top:25px;margin-bottom:10px;" class="errClass"><span style="font-weight:bold;"> TOTAL  </span>: '+result.voterId+'</div>';
								str+='<div id="sameCasteTpUpdtDiv"  class="span3" style="background-color: #e9e9e9; padding: 5px;margin-top:25px;margin-bottom:10px;" class="errClass"><span style="font-weight:bold;"> MATCHED  </span>: '+result.sameCount+'</div>';
								//var unMatched = (result.voterId) - (result.sameCount)
								str+='<div id="unMatchedCasteTpUpdtDiv"  class="span3" style="background-color: #e9e9e9; padding: 5px;margin-top:25px;margin-bottom:10px;" class="errClass"><span style="font-weight:bold;">UN MATCHED  </span>: '+myResult.unMatchedCount+'</div><input type="hidden" value="'+result.voterId+'" id="boothTpUnMatchedCount"></input>';
								
					    str+='</div>';
						 str+='<div class="row-fluid">';
								str+='<div  id="notIdentifedCasteTpUpdtDiv" class="span3" style="background-color: #e9e9e9; padding: 5px;margin-top:25px;margin-bottom:10px;" class="errClass"><span style="font-weight:bold;"> NOT IDENTIFED </span>: '+myResult.notIdentifedCount+'</div><input type="hidden" value="'+myResult.notIdentifedCount+'" id="boothTpNotIdentifedCount"></input>';
								str+='<div  id="wmWrongTpUpdtDiv" class="span3" style="background-color: #e9e9e9; padding: 5px;margin-top:25px;margin-bottom:10px;" class="errClass"><span style="font-weight:bold;"> WM WRONG </span>: '+result.wmWrong+'</div>';
								str+='<div  id="tpWrongTpUpdtDiv" class="span3" style="background-color: #e9e9e9; padding: 5px;margin-top:25px;margin-bottom:10px;" class="errClass"><span style="font-weight:bold;"> TP WRONG  </span>: '+result.tpWrong+'</div>';
								str+='<div  id="newCasteTpUpdtDiv" class="span3" style="background-color: #e9e9e9; padding: 5px;margin-top:25px;margin-bottom:10px;" class="errClass"><span style="font-weight:bold;"> NEWLY COLLECTED </span>: '+myResult.newCasteCount+'</div>';
								
					    str+='</div>';
				   str+='</div>';
	            str+='</div>';
			str+='</div>';
		str+='</div>';
		$("#tpReportBasicInfo").html(str);
}
function buildThirdPartyBasicDataNew(result){
   
		$("#sameCasteTpUpdtDiv").html('<span style="font-weight:bold;"> Same Caste  </span>: '+result.sameCount+'</div>');
		var notIdentidedCount = $('#notIdentifedCasteTpUpdtDiv').val();
		var unMatchedCount = ($('#boothTptotalCount').val())-((result.sameCount)+notIdentidedCount);
		
		$("#unMatchedCasteTpUpdtDiv").html('<span style="font-weight:bold;"> NOT IDENTIFED  </span>: '+notIdentidedCount+'</div>');
		$("#notIdentifedCasteTpUpdtDiv").html('<span style="font-weight:bold;"> Same Caste  </span>: '+unMatchedCount+'</div>');
		$("#wmWrongTpUpdtDiv").html('<span style="font-weight:bold;"> WM Wrong </span>: '+result.wmWrong+'</div>');

		$("#tpWrongTpUpdtDiv").html('<span style="font-weight:bold;"> TP Wrong  </span>: '+result.tpWrong+'</div>');
		$("#newCasteTpUpdtDiv").html('<span style="font-weight:bold;"> Newly Collected Caste  </span>: '+result.newCaste+'</div>');
								
					
}
function updateTPComment(id){
 var voterIds = $("#tPCommentCheck"+id).val();
 var comment = $.trim($("#tPCommentArea"+id).val());
 $("#tPCommentSubmit"+id).attr('disabled','disabled');
 $("#tPCommentSubmitImg"+id).show();
  var jsObj = 
	{
		voterIds : voterIds,
		statusId : "",
		comment  : comment
	}
	$.ajax({
			type:'GET',
			url: 'updateThirdPartyComment.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)},
		 }).done(function(result){	
		    $("#tPCommentSubmitImg"+id).hide();
		    $("#tPCommentSubmit"+id).removeAttr('disabled');
			alert("Comment Updated SuccessFully");
		});
}

function updateMultipleTPComment(){
var voterIds ="";
var comment = $("#multipleTpCommentUpdateId").val();
var statusId ="";
var ids = new Array();
 $(".thirdPartyChkAllVoters").each(function(){
     if($(this).is(":checked")){
	     ids.push($(this).attr("key"));
	     if(voterIds.length > 0){
	       voterIds = voterIds+","+$(this).val();
		 }else{
		   voterIds = $(this).val();
		 }
	 }
 
 });
 if($.trim(voterIds).length > 0){
	   if($("#multipleTpStatusUpdateCheckId").is(":checked")){
	      statusId = $("#multipleTpStatusUpdateId").val();
	   }
	   $("#multipleTpStatusUpdateSubmitId").attr('disabled','disabled');
	   $("#updateMultipleTPCommentImg").show();
	   var jsObj = 
		{
			voterIds : voterIds,
			statusId : statusId,
			comment  : comment
		}
		$.ajax({
				type:'GET',
				url: 'updateThirdPartyComment.action',
				dataType: 'json',
				data: {task:JSON.stringify(jsObj)},
			 }).done(function(result){	
			    $("#updateMultipleTPCommentImg").hide();
			    for(var i in ids){
	              $("#tPCommentArea"+ids[i]).val(comment);
				  if(statusId.length > 0){
				     $("#tPmultipleupdtStsId"+ids[i]).val(statusId);
				  }
				  $("#tPCommentCheck"+ids[i]).checked = false;	
                  $("#tPCommentCheck"+ids[i]).removeAttr('checked');					  
	            }
				
				$("#multipleTpStatusUpdateSubmitId").removeAttr('disabled');
				alert("Comment Updated SuccessFully");
				getLatestBasicInfo($("#boothIdHidden").val(),$("#userIdHidden").val());
			});
	}else{
	   alert("Please Select Voter To Update Comment");
	}
}

//updateThirdPartyDetails();
function updateThirdPartyDetails(voterId,statusId)
{
	
	var jsObj = 
	{
		voterId : voterId,
		statusId : statusId
	}
	$.ajax({
			type:'GET',
			url: 'updateThirdPartyStatus.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)},
		 }).done(function(result){	
		   getLatestBasicInfo($("#boothIdHidden").val(),$("#userIdHidden").val());
		});
}

function getFinalReportWithTP(){
	$('#dayWiseReportDiv1').html('');
	//$('#thirdPartyAjaxImg').show();
	var constituencyId = $('#constituencyForThirdParty').val();
	if(constituencyId == 0)	{
	 $("#errorDivForThirdParty").html("<font color='#FF0000'>Please Select Constituency</font>");
	 return;
	}
	 $("#errorDivForThirdParty").html("");
	//$('#mainajaximg').show();
	var jsObj = {
		constituencyId : $('#constituencyForThirdParty').val()
	}
	$("#thirdPartyAjax").show();
	$.ajax({
			type:'GET',
			url: 'getFinalReportWithTPAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)},
		 }).done(function(result){	
		 $("#thirdPartyAjax").hide();
			if(result != null)
			buildFinalReportWithTP(result)
			else
			$('#thirdPartyAjaxImg').show();
		});	
}

function buildFinalReportWithTP(result){
	
	//$("#FinalReportWithTPId").html("");
	var str = "";
	str +="<h4 style='text-align:center;color:red;'> BOOTH WISE OVERVIEW OF QC COLLECTED DETAILS</h4>";
	str +="<table id='FinalReportWithTPTableId' class='table table-bordered table-striped'>";
		str +="<thead class='alert alert-success'>";
			str +="<tr>";
				str +="<th rowspan=2>BOOTH</th>";
				str +="<th rowspan=2>BOOTH TYPE </th>";
				str +="<th rowspan=2>TOTAL VOTERS</th>";
				str +="<th rowspan=2>THIRD PARTY COLLECTED </th>";
				str +="<th rowspan=2>MATCHED</th>";
				str +="<th colspan=3>UN MATCHED</th>";
				str +="<th rowspan=2>NEW CASTE</th>";
				str +="<th rowspan=2>REVIEW</th>";
			str +="</tr>";
			str +="<tr>";
				var stList = result[0].statusList;
				for(var i in stList)
				{
					if(i == 1 || i == 2 || i == 3)
					{
						str +="<th>"+stList[i].statusName+"</th>";
					}
					
				}
			str +="</tr>";	
		str +="</thead>";
		
		for(var i in result)
		{
				for(var k in result[i].users.usersList)
				{
					str +="<tr>";
					str +="<td>"+result[i].partNo+"</td>";
					str +="<td>"+result[i].boothType+"</td>";
					str +="<td>"+result[i].totalVoters+"</td>";
					str +="<td>"+result[i].users.usersList[k].userCollected+"</td>";
					var sttsList = result[i].users.usersList[k].statusList;
					for(var p in sttsList)
					{
						str +="<td>"+sttsList[p].statusCount+"("+sttsList[p].statusPercentage+")</td>";
					}
					str +="<td><a style='cursor: pointer;' onCLick='getWmUpdatedDetails("+result[i].boothId+","+result[i].partNo+",2)'> REVIEW</a></td>";
					str +="</tr>";
				}
		}
			
		str +="<tbody>";
	str +="</table>";
	
	$("#FinalReportWithTPId").html(str);
	//$('#FinalReportWithTPTableId').dataTable();
	//$('#thirdPartyAjaxImg').show();
}

function getWmUpdatedDetails(boothId,partNo,id)
{
	var divId = '';
	if(id == 1)
	{
		divId = "commentsDiv1";
	}
	else
	{
		divId = "CommentsDiv";
	}
	$("#"+divId+"").html('');
	var jsObj = {
		boothId : boothId
	}
	$.ajax({
			type:'GET',
			url: 'getUpdatedCommentsFromWmForTP.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)},
		 }).done(function(result){
				if(result != null)
				{
					buildCommentedDetails(result,partNo,divId);
				}
				else
				{
					$("#"+divId+"").html('<b style="color:red;">NO DATA AVALIABLE</b>');
					$("#thirdPartyAjax").hide();
				}
		});	
}

function buildCommentedDetails(result,partNo,divId)
{
	var str = '';
	str += '<h4 style="text-align:center;color:red;">'+partNo+' BOOTH REVIEW DETAILS</h4>';
	str += '<table  class="table table-bordered table-striped" id="reviewDetails">';
	str += '<thead class="alert alert-success">';
	str += '<tr>';
	str += '<th>BOOTH NO</th>';
	str += '<th>VOTER NAME</th>';
	str += '<th>WM CASTE</th>';
	str += '<th>TP CASTE</th>';
	str += '<th>STATUS</th>';
	str += '<th>COMMENT</th>';
	str += '</tr>';
	str += '</thead>';
	str += '<tbody>';
	for(var i in result)
	{
		str += '<tr>';
		str += '<td>'+partNo+'</td>';
		str += '<td>'+result[i].name+'</td>';
		str += '<td>'+result[i].desc+'</td>';
		str += '<td>'+result[i].mobileNo+'</td>';
		str += '<td>'+result[i].caste+'</td>';
		str += '<td>'+result[i].percent+'</td>';
		str += '</tr>';
	}
	str += '</tbody>';
	str += '</table>';
	$("#"+divId+"").html(str);
	$("#thirdPartyAjax").hide();
	$('#reviewDetails').datatable();
}
function checkAllThirdPartyChkBoxes(){
 if($("#thirdPartyChkBox").is(':checked')){
    $('.thirdPartyChkAllVoters').each(function(){ this.checked = true; }); 
  }else{
    $('.thirdPartyChkAllVoters').each(function(){ this.checked = false; });
  }
}

function tPUpdateVoterCommentStatus(){
	
	var checked =false;

	$('.thirdPartyChkAllVoters').each(function(){
		if($(this).is(':checked')){
			checked=true;
		}
		if(checked){
		  return false;
		}
	});

	if(!checked){
		alert("Please select atleast one voter to update comment.");
		return;
	}
	
$('#finalInputDiv').html('');
	
	$("#editStatusDiv").dialog({
		width:600,
		height:300,
		modal: true,
		resizable: false,
		title:" Update Comment and Status"
	});

	var str='';
	str +='<div class="row">';
	str +='<div id="finalErrDiv" style="color:#FF0020;margin-left: 100px;"></div>';
		str +='<div class="offset1">';
		str +='<div class="row-fluid">';
				str +='<div class="span14">';
				str +='<table>';
				str +='<tr><td><b>Comment : </b></td><td><textarea id="multipleTpCommentUpdateId"></textarea></td></tr>';
				str +='<tr><td></td><td><input type="checkbox" id="multipleTpStatusUpdateCheckId" style="margin-top:-3px;" onclick="hideShowmultipleTpStatusUpdateDiv();"/> &nbsp; Check To Update Status</td></tr>';
				str +='<tr id="multipleTpStatusUpdateDivId" style="display:none;"><td><b>Select Status : </b></td><td><select id="multipleTpStatusUpdateId">';
				                  str +='<option value="0">Select Status</option>';
								  str +='<option value="1">Matched</option>';
								  str +='<option value="2">WM Wrong</option>';
								  str +='<option value="3">TP Wrong</option>';
								  str +='<option value="4">Same Caste</option>';
								   str +='<option value="7">New Caste</option>';
				str +='</select></td></tr>';
				str +='<tr><td></td><td><input id="multipleTpStatusUpdateSubmitId" type="button" class="btn btn-success" value="Submit" onclick="updateMultipleTPComment();" /><img style="display:none;"  src="images/icons/search.gif" id="updateMultipleTPCommentImg"/></td></tr>';
				str +='</table>';		
				str +='</div>';		
	str +='</div>';
	str +='</div>';
		
	str +='</div>';
	str +='</div>';					
	$('#finalInputDiv').html(str);
				
}
function hideShowmultipleTpStatusUpdateDiv(){

   if($("#multipleTpStatusUpdateCheckId").is(":checked")){
       $("#multipleTpStatusUpdateDivId").show();
   }else{
       $("#multipleTpStatusUpdateDivId").hide();
   }

}

function buildConstituencySummary(myrslt){
       $("#constSummary").html("");
       var rest = myrslt[0].constituencyDetails;
       var str = "";
       str +="<table id='FinalReportWithTPTableId' class='table table-bordered table-striped'>";
               str +="<thead class='alert alert-success'>";
                       
                       str +="<tr>";
                               str +="<th rowspan=2>CONSTITUENCY</th>";
                               str +="<th rowspan=2>TOTAL BOOTHS</th>";
                               str +="<th rowspan=2>TOTAL VOTERS</th>";
                               str +="<th rowspan=2>THIRD PARTY COLLECTED </th>";
                               str +="<th rowspan=2>MATCHED</th>";
                               str +="<th colspan=3>UN MATCHED</th>";
							   str +="<th rowspan=2>NEW CASTE</th>";
                               
                       str +="</tr>";
                       str +="<tr>";
                               var stList = rest.statusList;
                               for(var i in stList){
                                       if(i == 1 || i == 2 || i == 3)
                                       {
                                               str +="<th>"+stList[i].statusName+"</th>";
                                       }
                                       
                               }
                       str +="</tr>";
               str +="</thead>";
               str +="<tbody>";
       for(var q in myrslt)
	   {
               var result = myrslt[q].constituencyDetails;
               
			   if(myrslt[q].constituencyDetails.finalList!=null){
               str +="<tr>";
			   if(myrslt[q].constituencyType=="RURAL-URBAN")
			   {
				str +="<td> <a  href='javascript:{}' onclick='buildConstituencySummarySub(\""+myrslt[q].constituency+"\");'>"+myrslt[q].constituency+"</td>";
			   }
			   else
			   {
				str +="<td> <a  href='javascript:{}' onclick='getMeBoothsUnder(\"ALL\",\""+myrslt[q].constituencyId+"\",\""+myrslt[q].constituency+"\");'>"+myrslt[q].constituency+"</td>";
			   }
			   
               for(var i in result.boothTypeSummaryList)
			   {
					   if(result.boothTypeSummaryList[i].totalVoters > 0)
					   {
							   if(result.boothTypeSummaryList[i].boothType == 'ALL')
							   {
									   str +="<td>"+result.boothTypeSummaryList[i].finalList.length+"</td>";
									   if(result.boothTypeSummaryList[i].totalVoters==null)
									   {
											   str +="<td> 0 </td>";
									   }
									   else
									   {
											   str +="<td>"+result.boothTypeSummaryList[i].totalVoters+"</td>";
									   }
									   
									 if(result.boothTypeSummaryList[i].userCollected==null)
									 {
										str +="<td> 0 </td>";
									  }
									else
									{
										str +="<td>"+result.boothTypeSummaryList[i].userCollected+"</td>";
									}
						
								var stList = result.boothTypeSummaryList[i].statusList;
								for(var j in stList)
								{
									str +="<td>"+stList[j].statusCount+" ("+stList[j].statusPercentage+") </td>";
								}
					
							}
						}
				}
			str +="</tr>";
		}
		
		
		
	}
	str +="</tbody>";
		str +="</table>";
	$("#constSummary").html(str);
	$('#thirdPartyAjaxImg').hide();
}

function getCasteCollectedDatesByConstituencyId(constituencyId,fromDateId,toDateId){
var jsObj ={
	constituencyId :constituencyId
}

$.ajax({
		type:'GET',
		url: 'getCasteCollectionDatesByConstituencyId.action',
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
	}).done(function(result){
		availableDates = result;
		var strtDt = availableDates[0];
		var endtDt = availableDates[availableDates.length-1];
		
		var arrStrt = strtDt.split('-');
		var arrEnd =  endtDt.split('-');
		
		var strtDate = arrStrt[0];
		var strtMonth = arrStrt[1];
		var strtYear = arrStrt[2];
		
		if(strtDate.length<2){
			strtDate = "0"+strtDate;
		}
		
		if(strtMonth.length<2){
			strtMonth = "0"+strtMonth;
		}
		
		var endDate = arrEnd[0];
		var endMonth = arrEnd[1];
		var endYear = arrEnd[2];
		
		if(endDate.length<2){
			endDate = "0"+endDate;
		}
		
		if(endMonth.length<2){
			endMonth = "0"+endMonth;
		}
		
		/* 
		var dt= strtDt.substring(0,2);
		var month = 0+strtDt.substring(3,4);
		var yr = strtDt.substring(5,9);
		var dt1= endtDt.substring(0,2);
		var month1 = 0+endtDt.substring(3,4);
		var yr1 = endtDt.substring(5,9);
		var date1 = dt+"-"+month+"-"+yr;
		var date2 = dt1+"-"+month1+"-"+yr1; */
		
		var date1 =  strtDate+"-"+strtMonth+"-"+strtYear;
		var date2 =  endDate+"-"+endMonth+"-"+endYear;
		
		$('#'+fromDateId).val(date1).datepicker({ beforeShowDay: displayDates,maxDate: '0',dateFormat: 'dd-mm-yy'});
		$('#'+toDateId).val(date2).datepicker({ beforeShowDay: displayDates ,maxDate: '0' ,dateFormat: 'dd-mm-yy'});
	});
}

function getCasteCollectedDatesByUserId(userId,fromDateId,toDateId){

var jsObj ={
	userId :userId
}

$.ajax({
		type:'GET',
		url: 'getCasteCollectionDatesByUserId.action',
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
	}).done(function(result){
		availableDates = result;
		var strtDt = availableDates[0];
		var endtDt = availableDates[availableDates.length-1];
		var arrStrt = strtDt.split('-');
		var arrEnd =  endtDt.split('-');
		
		var strtDate = arrStrt[0];
		var strtMonth = arrStrt[1];
		var strtYear = arrStrt[2];
		
		if(strtDate.length<2){
			strtDate = "0"+strtDate;
		}
		
		if(strtMonth.length<2){
			strtMonth = "0"+strtMonth;
		}
		
		var endDate = arrEnd[0];
		var endMonth = arrEnd[1];
		var endYear = arrEnd[2];
		
		if(endDate.length<2){
			endDate = "0"+endDate;
		}
		
		if(endMonth.length<2){
			endMonth = "0"+endMonth;
		}
		
		/* 
		var dt= strtDt.substring(0,2);
		var month = 0+strtDt.substring(3,4);
		var yr = strtDt.substring(5,9);
		var dt1= endtDt.substring(0,2);
		var month1 = 0+endtDt.substring(3,4);
		var yr1 = endtDt.substring(5,9);
		var date1 = dt+"-"+month+"-"+yr;
		var date2 = dt1+"-"+month1+"-"+yr1; */
		
		var date1 =  strtDate+"-"+strtMonth+"-"+strtYear;
		var date2 =  endDate+"-"+endMonth+"-"+endYear;
		$('#'+fromDateId).val(date1).datepicker({ beforeShowDay: displayDates,maxDate: '0',dateFormat: 'dd-mm-yy'});
		$('#'+toDateId).val(date2).datepicker({ beforeShowDay: displayDates ,maxDate: '0' ,dateFormat: 'dd-mm-yy'});
	});
}

function displayDates(date){
	var dmy = date.getDate() + "-" + (date.getMonth()+1) + "-" + date.getFullYear();
	if ($.inArray(dmy, availableDates) != -1) {
		return [true, "","Available"];
	} else {
		return [false,"","unAvailable"];
	}
}

var finalRes = null;
function getTPTotalBoothsDetailsConstituencyWise(){

	$('#thirdPartyAjaxImg').show();
	var jsObj = {}
	$.ajax({
			type:'GET',
			url: 'getBoothDetailsConstituencyWiseWithTPAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)},
		 }).done(function(result){	
			if(result != null){
				finalRes= result;
				buildConstituencySummary(result);
			}
			else
			{
				$('#thirdPartyAjaxImg').hide();
			}
			//buildFinalReportWithTP(result)
		});	
}

function buildConstituencySummarySub(constiName){
	myrslt = finalRes;
	$("#constSummarySub").html("");
	$("#boothsSummary").html("");
	var rest = myrslt[0].constituencyDetails;
	var str = "";
	str +="<table id='FinalReportWithTPTableId' class='table table-bordered table-striped'>";
		str +="<thead class='alert alert-success'>";
			
			str +="<tr>";
				str +="<th rowspan=2>CONSTITUENCY</th>";
				str +="<th rowspan=2>BOOTHS TYPE</th>";
				str +="<th rowspan=2>TOTAL VOTERS</th>";
				str +="<th rowspan=2>THIRD PARTY COLLECTED </th>";
				str +="<th rowspan=2>MATCHED</th>";
                str +="<th rowspan=2>NEW CASTE</th>";
                str +="<th colspan=3>UN MATCHED</th>";
			str +="</tr>";
			str +="<tr>";
				 var stList = rest.statusList;
				 for(var i in stList){
                     if(i == 1 || i == 2 || i == 3){
                            str +="<th>"+stList[i].statusName+"</th>";
						}
                 }
			str +="</tr>";
		str +="</thead>";
		str +="<tbody>";
	for(var q in myrslt){
		var result = myrslt[q].constituencyDetails;
		
		var size = 0;
		for(var p in result.boothTypeSummaryList)
		{
			if(result.boothTypeSummaryList[p].totalVoters > 0)
			{
				size = size +1;
			}
		}
		if(constiName == myrslt[q].constituency){
		str +="<tr>";
		str +="<td rowspan='"+size+"'>"+myrslt[q].constituency+"</td>";
		for(var i in result.boothTypeSummaryList){
			if(result.boothTypeSummaryList[i].boothType!="ALL"){
		if(result.boothTypeSummaryList[i].totalVoters > 0)
		{
			str +="<td><a  href='javascript:{}' onclick='getMeBoothsUnder(\""+result.boothTypeSummaryList[i].boothType+"\",\""+myrslt[q].constituencyId+"\",\""+myrslt[q].constituency+"\");'>"+result.boothTypeSummaryList[i].boothType+"</a></td>";
				if(result.boothTypeSummaryList[i].totalVoters==null){
					str +="<td> 0 </td>";
				}else{
					str +="<td>"+result.boothTypeSummaryList[i].totalVoters+"</td>";
				}
				
				if(result.boothTypeSummaryList[i].userCollected==null){
					str +="<td> 0 </td>";
				}else{
					str +="<td>"+result.boothTypeSummaryList[i].userCollected+"</td>";
				}
				
				var stList = result.boothTypeSummaryList[i].statusList;
				for(var j in stList){
					str +="<td>"+stList[j].statusCount+" ("+stList[j].statusPercentage+") </td>";
				}
				}
			str +="</tr>";
		}
				
		}
	}
	}
	
	str +="</tbody>";
	str +="</table>";

	$("#constSummarySub").html(str);
}


function getMeBoothsUnder(bthType,constiId,constituency){
	$("#boothsSummary").html("");
	//$("#constSummarySub").html("");
	var str = "";
	if(finalRes==null){
		return;
	}
	var myrslt = null;
	for(var q in finalRes){
		myrslt = finalRes[q].constituencyDetails;
		for(var i in myrslt.boothTypeSummaryList){
			if(myrslt.boothTypeSummaryList[i].boothType == bthType && finalRes[q].constituencyId == constiId){
				var reslt = myrslt.boothTypeSummaryList[i];
				if(reslt.finalList == null){
					return;
				}						
			}
		}
	}
	
	str +="<h4 style='text-align:center;color:red;'>"+constituency+" CONSTITUENCY "+bthType+" BOOTHS OVERVIEW</h4>";
	str +="<table class='table table-bordered table-striped'>";
		str +="<thead class='alert alert-success'>";
			str +="<tr>";
			str +="<th rowspan=2>BOOTH</th>";
			str +="<th rowspan=2>TOTAL VOTERS</th>";
			str +="<th rowspan=2>THIRD PARTY COLLECTED </th>";
			str +="<th rowspan=2>MATCHED</th>";
			str +="<th colspan=3>UN MATCHED</th>";
			str +="<th rowspan=2>NEW CASTE</th>";
			str +="<th rowspan=2>REVIEW</th>";
			str +="</tr>";
			str +="<tr>";
				var stList = myrslt.boothTypeSummaryList[0].statusList;
				for(var i in stList)
				{
					if(i == 1 || i == 2 || i == 3)
                    {
							str +="<th>"+stList[i].statusName+"</th>";
					}
				}
			str +="</tr>";
		str +="</thead>";
		for(var q in finalRes){
			myrslt = finalRes[q].constituencyDetails;
			for(var i in myrslt.boothTypeSummaryList){
					if(myrslt.boothTypeSummaryList[i].boothType == bthType  && finalRes[q].constituencyId == constiId ){
						var reslt = myrslt.boothTypeSummaryList[i];
						for(var j in reslt.finalList){
							for(var k in reslt.finalList[j].users.usersList){
								str +="<tr>";
								str +="<td>"+ reslt.finalList[j].partNo+"</td>";
								str +="<td>"+reslt.finalList[j].totalVoters+"</td>";
								str +="<td>"+reslt.finalList[j].users.usersList[k].userCollected+"</td>";
								var sttsList = reslt.finalList[j].users.usersList[k].statusList;
								for(var p in sttsList)
								{
									str +="<td>"+sttsList[p].statusCount+"("+sttsList[p].statusPercentage+" )</td>";
								}
								str +="<td><a style='cursor: pointer;' onCLick='getWmUpdatedDetails("+reslt.finalList[j].boothId+","+reslt.finalList[j].partNo+",1)'> REVIEW</a></td>";
								str +="</tr>";
							}
						}
					}
			}
		}
			
		str +="<tbody>";
	str +="</table>";
	
	$("#boothsSummary").html(str);
	$("#thirdPartyAjax").hide();
}

function getVerifiersInBooth(){

	$("#verifiersInbooths").html("");
	var boothId = $("#boothIdForVerifier").val();
	if($("#constituencyForVeriFier").val() == 0)
	{
		$("#errorDiv1").html("<font color='#FF0000'>Select Constituency</font>");
		return;
	}
	if(boothId  == 0)
	{
		 $("#errorDiv1").html('<font color="#FF0000">Select Booth</font>');
		 return;
	}
	$("#errorDiv1").html("");
	var jsObj ={
		boothId :boothId
	}

	$.ajax({
			type:'GET',
			url: 'getVerifiersInBoothsAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)},
		}).done(function(result){
			buildVerifiers(result);
		});
}

function buildVerifiers(result){
	if(result.length>1){
		$("#verifiersInbooths").html("");
		var str  = "";
		for(var i in result){
			str += "<input type='radio' name='verifiers' onclick='getVerfierDetailsWithVerifier()' style='margin:4px;' value="+result[i].verifierId+"><span style='margin:4px;font-size:16px;color:blue;'>"+result[i].verifier+"</span></input>";
		}
		$("#verifiersInbooths").html(str);
		$("input:radio[name=verifiers]:first").attr('checked', true);
		getVerfierDetailsWithVerifier();
		
	}else{
		getVerfierDetails(1,'mainajaximgForVerifier','dayWiseReportDivForVerifier');
	}

	
}
	
	
function getDayWiseDCReport()
{
	$('#dayWiseDCReportDiv').html('');
	$('#DCmainajaximg').show();
	var jsObj = {
		constituencyArr : $('#DCConstituencyId').val(),
		reportDate : $('#reportDateId').val()
	}
	$.ajax({
			type:'GET',
			url: 'dayWiseDCReportAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)},
		 }).done(function(result){	
			buildDcReport(result);
		});	
		
}

function buildDcReport(result)
{
	$('#DCmainajaximg').hide();
	
	if(result != null && result[0].boothWiseSurveyDetailsVOList != null)
	{
		var str = '';	
			str +='<div> <button class="btn btn-success offset7" style="margin-top: -30px;" onclick="generateExcel(\'dayWiseDCReportTable\')"> Export Excel </button></div>';
			str+='<table class="table table-bordered m_top20 table-hover table-striped " id="dayWiseDCReportTable">';				
				str+='<thead>';
					str+='<tr>';
						str+='<td> CONSTITUENCY NAME </td>';
						str+='<td> DC_NAME </td>';
						//str+='<td> NO. OF BOOTHS </td>';
						str+='<td> BOOTH NO </td>';
						str+='<td> FIRST RECORD TIME </td>';
						str+='<td> LAST RECORD TIME </td>';
						str+='<td> TOTAL TIME </td>';
						str+='<td> TOTAL COUNT </td>';
					str+='</tr>';
				str+='</thead>';
				str+='</tbody>';
		for(var i in result[0].boothWiseSurveyDetailsVOList)
		{
			str+='<tr>';
						str+='<td> '+result[0].boothWiseSurveyDetailsVOList[i].constituency+' </td>';
						str+='<td> '+result[0].boothWiseSurveyDetailsVOList[i].name+' </td>';
						//str+='<td> '+result[0].boothWiseSurveyDetailsVOList[i].boothInfo.length+' </td>';
						str+='<td> '+result[0].boothWiseSurveyDetailsVOList[i].partNo+'</td>';
						str+='<td> '+result[0].boothWiseSurveyDetailsVOList[i].startTime+ '</td>';
						str+='<td> '+result[0].boothWiseSurveyDetailsVOList[i].endTime+ '</td>';
						str+='<td> '+result[0].boothWiseSurveyDetailsVOList[i].workedTime+ ' </td>';
						str+='<td> '+result[0].boothWiseSurveyDetailsVOList[i].count+ '</td>';
			str+='</tr>';
		}
			str+='</tbody>';
		str+='</table>';
		
		
		$('#dayWiseDCReportDiv').html(str);
		 $('#dayWiseDCReportTable').dataTable({
		"iDisplayLength": 30,
		"aLengthMenu": [[30,50,100, 200, -1], [30,50,100, 200, "All"]]
		});
	}
}

function buildMyReport(result)
{
	var str = '';
	str+='<table class="table table-bordered m_top20 table-hover table-striped" id="">';
 	 str+='<thead>';
	 str+='<tr>';
	  str+='<th>Booth No</th>';
	  str+='<th>Total Voters</th>';
	  str+='<th>DC Caste Mapped</th>';
	  str+='<th>DC Caste Mapped % </th>';
	  str+='<th>DC Hamlet Mapped</th>';
	  str+='<th>DC Hamlet Mapped %</th>';
	  str+='<th>DC Mobile Collected</th>';
	  str+='<th>DC Mobile Collected %</th>';
	  str+='<th>Ready for WMT- Configurable %.As of now >=80%</th>';
	  str+='<th>WMT Done</th>';
	  str+='<th>WM DC Total</th>';
	  str+='<th>WM DC Total %</th>';
	  str+='<th>DC VERIFIED RECORDS</th>';
	  str+='<th>WMT:DC Caste Verified% (DC Records Verified/DC Records Present*100)</th>';
	  str+='<th>WM DC Caste Matched</th>';
	  str+='<th>WM DC Caste UnMatched</th>';
	  str+='<th>WM DC Caste Error %</th>';
	  str+='<th>WM DC Mobile Matched</th>';
	  str+='<th>WM DC Mobile UnMatched</th>';
	  str+='<th>WM DC Mobile Error %</th>';
	  str+='<th>Newly Collected Count</th>';
	  str+='<th>Ready for RTP </th>';

	 str+='</tr>';
	 str+='</thead>';
	 str+='<tbody>';
	 $.each(result,function(index,value){
		 str+='<tr>';
		  str+='<td>'+value.partNo+'</td>';
		  str+='<td>'+value.totalVoters+'</td>';
		  str+='<td>'+value.dcDetails.casteCount+'</td>';
		  str+='<td>'+value.dcDetails.castePercent+'</td>';
		  str+='<td>'+value.dcDetails.hamletCount+'</td>';
		  str+='<td>'+value.dcDetails.hamletPercent+'</td>';
		  str+='<td>'+value.dcDetails.mobileNUmbersCount+'</td>';
		  str+='<td>'+value.dcDetails.mobilePercent+'</td>';
		  
		  var dcVerifiedRecords = (value.wmDcDetails.totalCount)-(value.wmDcDetails.newlyCollectedCount);
		  var dcVerifiedRecordsPerc = (Math.round(dcVerifiedRecords * 100)/value.dcDetails.casteCount).toFixed(2);
		  
		  if(value.dcDetails.castePercent >= 85)
		  {
			str+='<td>Y</td>';
		  }
		  else
		  {
			str+='<td>N</td>';
		  }
		  
		   if(dcVerifiedRecordsPerc >= 85)
		  {
			str+='<td>Y</td>';
		  }
		  else
		  {
			str+='<td>N</td>';
		  }
		  
          var wmDcCompletedPerc = (Math.round(value.wmDcDetails.totalCount * 100)/value.totalVoters).toFixed(2);
		  str+='<td>'+value.wmDcDetails.totalCount+'</td>';
		  str+='<td>'+wmDcCompletedPerc+'</td>';
		 
		  str+='<td>'+dcVerifiedRecords+'</td>';
		  str+='<td>'+dcVerifiedRecordsPerc+'</td>';
		  
		  
		  str+='<td>'+value.wmDcDetails.casteMatchedCount+'</td>';
		  str+='<td>'+value.wmDcDetails.casteUnMatchedCount+'</td>';
          str+='<td>'+value.wmDcDetails.castePercent+'</td>';
		  str+='<td>'+value.wmDcDetails.mobileMatchedCount+'</td>';
		  str+='<td>'+value.wmDcDetails.mobileUnmatchedCount+'</td>';
		  str+='<td>'+value.wmDcDetails.mobilePercent+'</td>';
		  str+='<td>'+value.wmDcDetails.newlyCollectedCount+'</td>';
		 
		 if(wmDcCompletedPerc >= 95 && dcVerifiedRecordsPerc >= 85 && value.wmDcDetails.castePercent <= 5)
		 {
			str+='<td>Y</td>';
		 }
		 else
		 {
			str+='<td>N</td>';
		 }
		 str+='</tr>';
	 });
	  
	 str+='</tbody>';
	str+='</table>';


	$('#mainajaximg').hide();
	//$('#excelTableID1').show();
	$('#dayWiseReportDiv2').html(str);
	//$('#daywisereportTableIdTemp').dataTable();
}
