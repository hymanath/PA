/*	function getBoothDetails()
{
	var jObj =
	{
	 constituencyId:$('#constituencyId').val()
	}
	$.ajax({
			type:'GET',
			url: 'getBoothsByConstituency.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		  }).done(function(result){
			$("#boothId option").remove();
			$("#boothId").append("<option value='0'>Select Booth</option>");
			for(var i in result){
                $("#boothId").append("<option value="+result.id+">"+result.partiesInMandal[i].name+"</option>");
			}
	});
		
}*/
//getconstituencies();
function getconstituencies()
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

	$("#constituencyId").append('<option value="0">Select Constituency</option>');
	if(result != null && result.length > 0)
	{
	for(var i in result)
	{
		
	$("#constituencyId").append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
	}

	}
	
	});
}

	
function getUserTypes(divId)
{
	var jsObj =
	{

	}
	$.ajax({
	type:'GET',
	url: 'getSurveyUserTypeAction.action',
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
function getDayWiseReport()
{

$("#dayWiseReportDiv").html('');
$("#heading").html('');
	var constituencyId = $("#constituencyId").val();
	var userTypeId = $("#userType").val();
	var startDate = $("#fromDate").val();
	var endDate = $("#toDate").val();
	var heading = $( "#userType option:selected" ).text();
	if(constituencyId == 0)
	{
		$("#errorDiv").html("Please Select Constituency").css("color","red");
		return;
	}
	if(userTypeId == 0)
	{
		$("#errorDiv").html("Please Select User Type").css("color","red");
		return;
	}
	if(startDate.length == 0 || endDate.length == 0)
	{
		$("#errorDiv").html("Please Select From Date").css("color","red");
		return;
	}
	if( endDate.length == 0)
	{
		$("#errorDiv").html("Please Select To Date").css("color","red");
		return;
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
		 $('#errorDiv').html("From Date should be Less Than To Date");
		 return;
		}
	}
	$("#mainajaximg").show();
	var jObj =
	{
	 constituencyId:constituencyId,
     userTypeId:userTypeId,
	 startDate:startDate,
	 endDate:endDate,
	heading:heading

	};
	$.ajax({
			type:'GET',
			url: 'getDayWisereportDetailsByConstituencyId.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		  }).done(function(result){
				$("#mainajaximg").hide();
				$("#reportDiv").css("display","block");
						buildDayWiseReport(result,heading,startDate,endDate);
				
		});
}

function buildDayWiseReport(result,heading,startDate,endDate)
{
var months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];

var dt1 = startDate.split("-").reverse().join("-");
var d = new Date(dt1);
var strdate= d.getDate() + '-' +  months[d.getMonth()] + '-' + d.getFullYear();

var dt2 = endDate.split("-").reverse().join("-");
var d1 = new Date(dt2);
var enddate= d1.getDate() + '-' +  months[d1.getMonth()] + '-' + d1.getFullYear();
if(strdate < enddate)
	{
	$("#heading").html(''+heading+'  Report For Daily Verification  </br><small style="color:#333;padding-bottom:10px;display:inline-block;">  From '+strdate+' to '+enddate+' </small> ');
	}

	else
	{
	$("#heading").html(''+heading+'  Report For Daily Verification  </br><small style="color:#333;padding-bottom:10px;display:inline-block;"> '+strdate+' </small> ');
	}
	$("#errorDiv").html("");
	if(result.length == 0)
	 $('#dayWiseReportDiv').html('<font style="color:red;">No data available....</font>');
	 else
	 {
  var str = '';

  str+='<table class="table table-bordered m_top20 table-hover table-striped offset3" style="width:422px;">';
  str+='<thead class="alert alert-success">';
   str+='<tr>';
    str+='<th>UserName</th>';
	$.each(result[0].subList,function(index,value){
      str+='<th>'+value.surveyDate1+'</th>';
	});
   str+='</tr>';
  str+='</thead>';
  str+='<tbody>';
  
    $.each(result,function(index,value){
		 str+='<tr>';
		 str+='<td>'+value.userName+'</td>';
		   $.each(value.subList,function(index1,value1){
			    str+='<td><a href="javascript:{getDayWiseReportDetailsOfUser('+value.userid+',\''+value.surveyDate1+'\')}">'+value1.count+'</a></td>';
		   });
		str+='</tr>';
	});
  
  str+='</tbody>';
  str+='</table>';

  $('#dayWiseReportDiv').html(str);
  }
}


function getDayWiseReportDetailsOfUser(userId,surveyDate)
{
	
  $('#boothWiseCountDivId').html('');
 // var startDate = $("#fromDate").val();
	//var endDate = $("#toDate").val();
	var jObj =
	{
	 userId:userId,
	 startDate:surveyDate
	// endDate:endDate
	}
	$.ajax({
			type:'GET',
			url: 'getBoothWiseUserSamplesDetailsByDates.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		  }).done(function(result){
				buildUserBoothWiseCountDetails(result);
		});
}


function buildUserBoothWiseCountDetails(result)
{
  var str ='';

  str+='<table class="table table-bordered m_top20 table-hover table-striped username">';
   str+='<thead class="alert alert-success">';
    str+='<tr>';
	  str+='<th>Booth No</th>';
	  str+='<th>Total</th>';
	  str+='<th>Completed</th>';
	str+='</tr>';
   str+='</thead>';	  
   str+='<tbody>';
    $.each(result,function(index,value){
		str+='<tr>';
		str+='<td>'+value.partNo+'</td>';
		str+='<td>'+value.totalVoters+'</td>';
		str+='<td>'+value.count+'</td>';
		str+='</tr>';
	});
   str+='</tbody>';
  str+='</table>';

  $('#boothWiseCountDivId').html(str);
  $('#boothWiseCountDivId').dialog({
			
			autoOpen: true,
			show: "blind",
			width: 500,
            title:'Booth Details', 			
			modal: true,
			height:"auto",
			hide: "explode"
		});
}



function getDayWiseReportByConstituencyIdAndUserType()
{

   $('#dayWiseReportDiv1,#retunMsg,#errorDiv').html('');
	var constituencyId = $("#constituencyId").val();
	//var userTypeId = $("#userType").val();
	var userTypeId = userTypeVal;
	
		var startDate = $("#fromDate").val();
	var endDate = $("#toDate").val();
	var heading = $( "#userType option:selected" ).text();

	var errorStr ="";

	if(constituencyId == 0)
		errorStr += 'Please select constituency<br>';

	if($('#boothId').val() == null)
errorStr += 'Please select at least one booth<br>';

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

	var userIds = new Array();
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
	console.log(jObj);
	$('#mainajaximg').show();

	 jObj.boothIds= $('#boothId').val();

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
		$('#retunMsg').html("NO DATA AVILABLE");
		return;
	}

	 var str = '';

   str+='<table id="dayWiseReportTable" class="table table-bordered m_top20 table-hover table-striped username">';
    str+='<thead class="alert alert-success">';
	 str+='<tr>';
	  str+='<th>UserName</th>';
  	  str+='<th>Booth No</th>';
	  str+='<th>Total Voters</th>';

	  
		if($('#fromDate').val() == $('#toDate').val())
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

	   if($('#fromDate').val() != $('#toDate').val())
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
  	/*str+='<tr>';
	str+='<td><b>Total</b></td>';
	str+='<td></td>';
	str+='<td></td>';

	if($('#fromDate').val() == $('#toDate').val())
		str+='<td></td>';

		$.each(result[0].subList,function(index,value){
			if(index == result[0].subList.length-1)
			  str+='<td><b>'+value.total+'</b></td>';
			else
			  str+='<td></td>';
		});
   	str+='</tr>';*/	
   	str+='</tbody>';
    str+='<tfoot>';
	str+=' <tr><td>Total</td>';
	str+='<td></td>';
	str+='<td></td>';

		$.each(result[0].subList,function(index,value){
			//if(index == result[0].subList.length-1)
			  str+='<td><b>'+value.total+'</b></td>';
			//else
			 // str+='<td></td>';
		});

		  if($('#fromDate').val() == $('#toDate').val())
			   str+='<td></td>';
	str+='</tr>';
	str+='</tfoot>';
	str+='</table>';

 $('#dayWiseReportDiv1').html(str);
 $('#dayWiseReportTable').dataTable({
		"iDisplayLength": 100,
		"aLengthMenu": [[100, 200, 500, -1], [100, 200, 500, "All"]]
		});
		

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

function buildVerifierOrWMReport(result,buildType,buildDiv,imgId)
{
	var str = '';
	if(result[0].matchedList[0] != null )
	{
		str += '<div class="row-fluid">';
		str += '<div class="span4" style="background-color: yellow; padding: 5px;margin-top:25px;margin-bottom:10px;" >PANCHAYAT : '+result[0].matchedList[0].panchayatName+'</div> ';
		str += '<div class="span4" style="background-color: yellow; padding: 5px;margin-top:25px;margin-bottom:10px;">SURVEY USER : '+result[0].matchedList[0].surveyUser+'</div> ';
		str += '<div class="span4" style="background-color: yellow; padding: 5px;margin-top:25px;margin-bottom:10px;">Date : '+result[0].matchedList[0].date+'</div> ';
		str += '</div>';
	}
	else if(result[0].unMatchedList[0] != null )
	{
		str += '<div class="row-fluid">';
		str += '<div class="span4" style="background-color: yellow; padding: 5px;margin-top:25px;margin-bottom:10px;" >PANCHAYAT : '+result[0].unMatchedList[0].panchayatName+'</div> ';
		str += '<div class="span4" style="background-color: yellow; padding: 5px;margin-top:25px;margin-bottom:10px;">SURVEY USER : '+result[0].unMatchedList[0].surveyUser+'</div> ';
		str += '<div class="span4" style="background-color: yellow; padding: 5px;margin-top:25px;margin-bottom:10px;">Date : '+result[0].unMatchedList[0].date+'</div> ';
		str += '</div>';
	}
	else if(result[0].notVerifiedList[0] != null )
	{
		str += '<div class="row-fluid">';
		str += '<div class="span4" style="background-color: yellow; padding: 5px;margin-top:25px;margin-bottom:10px;" >PANCHAYAT : '+result[0].notVerifiedList[0].panchayatName+'</div> ';
		str += '<div class="span4" style="background-color: yellow; padding: 5px;margin-top:25px;margin-bottom:10px;">SURVEY USER : '+result[0].notVerifiedList[0].surveyUser+'</div> ';
		str += '<div class="span4" style="background-color: yellow; padding: 5px;margin-top:25px;margin-bottom:10px;">Date : '+result[0].notVerifiedList[0].date+'</div> ';
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
	//str+='<th>Booth</th>';
	//str+='<th>Panchayat</th>';
	//str+='<th>Survey User</th>';
	str+='<th>Voter Name</th>';
	str+='<th>Relativen Name</th>';
	//str+='<th>DC Collected Date</th>';
	str+='<th>DC CASTE</th>';
	str+='<th>WM CASTE</th>';
	if(buildType == 1)
	str+='<th>DV CASTE</th>';
	str+='<th>STATUS</th>';
	str+='</tr>';
	str+='</thead>';
	str+='<tbody>'
	for(var i in result)
	{
		
		for(var j in result[i].matchedList)
		{
			str += '<tr>';
			//str+='<td>'+result[i].matchedList[j].partNo+'</td>';
			//str+='<td>'+result[i].matchedList[j].panchayatName+'</td>';
			//str+='<td>'+result[i].matchedList[j].surveyUser+'</td>';
			str+='<td>'+result[i].matchedList[j].voterName+'</td>';
			str+='<td>'+result[i].matchedList[j].relativeName+'</td>';
			//str+='<td>'+result[i].matchedList[j].date+'</td>';
			str+='<td>'+result[i].matchedList[j].dcCaste+'</td>';
			str+='<td>'+result[i].matchedList[j].wmCaste+'</td>';
			if(buildType == 1)
			str+='<td>'+result[i].matchedList[j].dvCaste+'</td>';
			str+='<td>MATCHED</td>';
			str += '</tr>';		
		}
		for(var j in result[i].unMatchedList)
		{
			str += '<tr>';
			//str+='<td>'+result[i].unMatchedList[j].partNo+'</td>';
			//str+='<td>'+result[i].unMatchedList[j].panchayatName+'</td>';
			//str+='<td>'+result[i].unMatchedList[j].surveyUser+'</td>';
			str+='<td>'+result[i].unMatchedList[j].voterName+'</td>';
			str+='<td>'+result[i].unMatchedList[j].relativeName+'</td>';
			//str+='<td>'+result[i].unMatchedList[j].date+'</td>';
			str+='<td>'+result[i].unMatchedList[j].dcCaste+'</td>';
			str+='<td>'+result[i].unMatchedList[j].wmCaste+'</td>';
			if(buildType == 1)
			str+='<td>'+result[i].unMatchedList[j].dvCaste+'</td>';
			str+='<td>UNMATCHED</td>';
			str += '</tr>';		
		}
		for(var j in result[i].notVerifiedList)
		{
			str += '<tr>';
			//str+='<td>'+result[i].notVerifiedList[j].partNo+'</td>';
			//str+='<td>'+result[i].notVerifiedList[j].panchayatName+'</td>';
			//str+='<td>'+result[i].notVerifiedList[j].surveyUser+'</td>';
			str+='<td>'+result[i].notVerifiedList[j].voterName+'</td>';
			str+='<td>'+result[i].notVerifiedList[j].relativeName+'</td>';
			//str+='<td>'+result[i].notVerifiedList[j].date+'</td>';
			str+='<td>'+result[i].notVerifiedList[j].dcCaste+'</td>';
			str+='<td>'+result[i].notVerifiedList[j].wmCaste+'</td>';
			if(buildType == 1)
			str+='<td>'+result[i].notVerifiedList[j].dvCaste+'</td>';
			str+='<td>NOT VERIFIED</td>';
			str += '</tr>';		
		}
		
		
	}
	str += '</tbody>';
	str += '</table>';
	$('#'+buildDiv+'').html(str);
	//generateExcel('dataTableDiv');
	$('#dayWiseReportTableForVerifier').dataTable({
		"iDisplayLength": 100,
		"aLengthMenu": [[100, 200, 500, -1], [100, 200, 500, "All"]]
	});
	$('#'+imgId+'').hide();
			
}
/*var tableToExcel = (function() {
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
}*/

function getBoothsDetailsByConstituencyId(constituencyId,divId)
{
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
				$('#'+divId+'').find('option').remove();

				$.each(result,function(index,value){
					$('#'+divId+'').append('<option value="'+value.boothId+'">Booth - '+value.partNo+'</option>');
				});
				
				$('#'+divId+'').multiselect('refresh');

		});	
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


function showHideReportTabs(id)
{
	$(".clearCls").html("");
	$("#surveyMonitoringConstituencyId,#surveyMonitoringBoothId,#userTrackingConstituencyId,#constituencyId").val(0);	
	$("#boothId").find("option").remove();
	if(id == "dataCollectorTab")
	{
		$("#surveyUserTrackingId").hide();
		$("#comparisonReportId").hide();
		$("#stateWiseReportId").hide();
		$("#verifierReportId").show();
		$("#verifierReportIdForVerifier").hide();
		$("#wmReportDiv").hide();
		$("#saveBoothsPercentage").hide();
		$('#boothId').multiselect('refresh');
		$("#thirdpPartyReport").hide();
	}
	else if(id == "userTrackingReportTab")
	{
		$("#verifierReportId").hide();
		$("#comparisonReportId").hide();
		$("#stateWiseReportId").hide();
		$("#wmReportDiv").hide();
		$("#verifierReportIdForVerifier").hide();
		$("#saveBoothsPercentage").hide();
		$("#surveyUserTrackingId").show();
		$("#thirdpPartyReport").hide();
	}
	else if(id == "stateWiseReportTab")
	{
		$("#surveyUserTrackingId").hide();
		$("#verifierReportId").hide();
		$("#comparisonReportId").hide();
		$("#wmReportDiv").hide();
		$("#verifierReportIdForVerifier").hide();
		$("#saveBoothsPercentage").hide();
		$("#stateWiseReportId").show();
		$("#thirdpPartyReport").hide();
	}
	else if(id == "thirdpPartyReportTab")
	{
		$("#surveyUserTrackingId").hide();
		$("#verifierReportId").hide();
		$("#comparisonReportId").hide();
		$("#wmReportDiv").hide();
		$("#verifierReportIdForVerifier").hide();
		$("#stateWiseReportId").hide();
		$("#saveBoothsPercentage").hide();
		$("#thirdpPartyReport").show();
		//getThirdPartySummaryDetails();
		getTPTotalBoothsDetailsConstituencyWise();
	}
	

}



var availableDates;
function getCasteCollectedDatesByConstituencyId(constituencyId)
{
$("#dateAjaxImage").show();
var jsObj =
{
constituencyId :constituencyId
}

$.ajax({
type:'GET',
url: 'getCasteCollectionDatesByConstituencyId.action',
dataType: 'json',
data: {task:JSON.stringify(jsObj)},
}).done(function(result){
$("#dateAjaxImage").hide();
availableDates = result;
var strtDt = availableDates[0];
var endtDt = availableDates[availableDates.length-1];

var arrStrt = strtDt.split('-');
var arrEnd =  endtDt.split('-');
		
	var dt = arrStrt[0];
	var month = arrStrt[1];
	var yr = arrStrt[2];
	
	if(dt.length<2){
		dt = "0"+dt;
	}
	
	if(month.length<2){
		month = "0"+month;
	}
	
	var dt1 = arrEnd[0];
	var month1 = arrEnd[1];
	var yr1 = arrEnd[2];
	
	if(dt1.length<2){
		dt1 = "0"+dt1;
	}
	
	if(month1.length<2){
		month1 = "0"+month1;
	}
var date1 = dt+"-"+month+"-"+yr;
var date2 = dt1+"-"+month1+"-"+yr1;
$('#fromDate').val(date1).datepicker({ beforeShowDay: displayDates,maxDate: '0',dateFormat: 'dd-mm-yy'});
$('#toDate').val(date2).datepicker({ beforeShowDay: displayDates ,maxDate: '0' ,dateFormat: 'dd-mm-yy'});
});
}
function displayDates(date)
{
	
var dmy = date.getDate() + "-" + (date.getMonth()+1) + "-" + date.getFullYear();

if ($.inArray(dmy, availableDates) != -1) {
return [true, "","Available"];
} else {
return [false,"","unAvailable"];
}

}

//getTPTotalBoothsDetailsConstituencyWise();
function getTPTotalBoothsDetailsConstituencyWise(){
	//$('#mainajaximg').show();
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

function buildConstituencySummary(myrslt){
       $("#constSummary").html("");
       var rest = myrslt[0].constituencyDetails;
       var str = "";
       str +="<table id='FinalReportWithTPTableId' class='table table-bordered table-striped '>";
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
       for(var q in myrslt){
               var result = myrslt[q].constituencyDetails;
               
			   if(myrslt[q].constituencyDetails.finalList!=null){
               str +="<tr>";
			   if(myrslt[q].constituencyType=="RURAL-URBAN"){
				str +="<td> <a   onclick='buildConstituencySummarySub(\""+myrslt[q].constituency+"\");'>"+myrslt[q].constituency+"</td>";
			   }else{
				str +="<td> <a  onclick='getMeBoothsUnder(\"ALL\",\""+myrslt[q].constituencyId+"\",\""+myrslt[q].constituency+"\");'>"+myrslt[q].constituency+"</td>";
			   }
			   
               for(var i in result.boothTypeSummaryList){
               if(result.boothTypeSummaryList[i].totalVoters > 0)
               {
                       if(result.boothTypeSummaryList[i].boothType == 'ALL')
                       {
                               str +="<td>"+result.boothTypeSummaryList[i].finalList.length+"</td>";
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
			str +="</tr>";
			}
		}
		}
		
	}
	
	
	
	}
	str +="</tbody>";
	str +="</table>";
	$("#constSummary").html(str);
}

function getMeBoothsUnder(bthType,constiId,constituency){
	console.log(finalRes);
	$("#thirdPartyAjax").show();
	$("#boothsSummary").html("");
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
	str +="<table id='FinalReportWithTPTableId' class='table table-bordered table-striped'>";
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
					if(i == 1 || i == 2 || i == 3 )
					str +="<th>"+stList[i].statusName+"</th>";
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
								for(var p in sttsList){
									str +="<td>"+sttsList[p].statusCount+"("+sttsList[p].statusPercentage+")</td>";
								}
								str +="<td><a style='cursor: pointer;' onCLick='getWmUpdatedDetails("+reslt.finalList[j].boothId+","+reslt.finalList[j].partNo+")'> REVIEW</a></td>";
								str +="</tr>";
							}
						}
					}
			}
		}
			
		str +="<tbody>";
	str +="</table>";
	
	$("#boothsSummary").html(str);
	//$('#FinalReportWithTPTableId').dataTable();
	
	$("#thirdPartyAjax").hide();
}

function getWmUpdatedDetails(boothId,partNo)
{
	$('#CommentsDiv').html('');
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
					buildCommentedDetails(result,partNo);
				}
				else
				{
					$('#CommentsDiv').html('<b style="color:red;">NO DATA AVALIABLE</b>');
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
	
	str += '<table  class="table table-bordered table-striped">';
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


function showConstituenciesDetails(districtId,locationName)
{
	 window.open('constituencyDetailReportAction.action?districtId='+districtId+'&task='+locationName+' District ','_blank');
}

function getSurveyCompletedLocationsDetailsForSurveyStartedConstituencies()
{  
   document.getElementById("popupImgid1").style.display="block";
  
	var jobj = {
			     task:"getSurveyCompletedLocationDetails"
	  	       }
		$.ajax({
	          type:'GET',
	          url: 'getSurveyCompletedLocationsDetailsForSurveyStartedConstituenciesAction.action',
	          dataType: 'json',
	          data: {task:JSON.stringify(jobj)}
	    }).done(function(result){
		    
		    buildingSurveyCompletedLocationsDetailsForSurveyStartedConstituencies(result);
		});
	
	
	
}
function buildingSurveyCompletedLocationsDetailsForSurveyStartedConstituencies(result)
{
	// document.getElementById("popupImgid1").style.display="none";
	 $("#buldingConstituenciesDivId").html('');
	 $('#constituencyOverView').html('')
	var str='';
	str+='<table class=" m_top20 table table-bordered table-hover table-striped" id="constituencyOverView">';
	str+='<thead class="alert alert-success">';
	str+='<tr>';
	str+='<th>Constituency Name</th>';
	str+='<th>Total Voters </th>';
	str+='<th>Data Collected Count </th>';
	str+='<th>Total Booths</th>';
	str+='<th>Completed</th>';
	str+='<th>Processing </th>';
	str+='<th>Not Yet Started </th>';
	//str+='<th>Third Party Started ?</th>';
	str+='<th> QC Verification </th>';
	str+='</tr>';
	str+='</thead>';
	str+='<tbody>';
	for(i=0;i<result.length;i++)
	{
	  if( (result[i].total) != (result[i].notStartedCount))
	  {

		  str+='<tr>';
		if( (result[i].total) == (result[i].completedCount))
			{
			 str+='<td style="background:#12BB1B;" ><a href="javascript:{getConstituencyDetalReport('+result[i].id+',\''+result[i].name+'\')}"> '+result[i].name+'</a></td>';
			 if(result[i].totalVoters==null)
			 {
			  str+='<td style="background:#12BB1B;" ></td>';
			 }
			 else{str+='<td style="background:#12BB1B;" >'+result[i].totalVoters+'</td>';}
			 
			 str+='<td style="background:#12BB1B;" >'+result[i].totalCollectedCount+'</td>';
			 str+='<td style="background:#12BB1B;" >'+result[i].total+'</td>';
			 str+='<td style="background:#12BB1B;" >'+result[i].completedCount+'</td>';
			 str+='<td style="background:#12BB1B;" >'+result[i].processingCount+'</td>';
			 str+='<td style="background:#12BB1B;" >'+result[i].notStartedCount+'</td>';
			 if(result[i].forThirdParty == true)
			   str+='<td style="background:#12BB1B;" >Y</td>';
			 else 
			   str+='<td style="background:#12BB1B;" >N</td>';

		}
		else
		{
			 str+='<td><a href="javascript:{getConstituencyDetalReport('+result[i].id+',\''+result[i].name+'\')}"> '+result[i].name+'</a></td>';
			 if(result[i].totalVoters==null)
			 {
			  str+='<td></td>';
			 }
			 else{str+='<td>'+result[i].totalVoters+'</td>';}
			 
			 str+='<td>'+result[i].totalCollectedCount+'</td>';
			 str+='<td>'+result[i].total+'</td>';
			 str+='<td>'+result[i].completedCount+'</td>';
			 str+='<td>'+result[i].processingCount+'</td>';
			 str+='<td>'+result[i].notStartedCount+'</td>';
			 if(result[i].forThirdParty == true)
			   str+='<td>Y</td>';
			 else 
			   str+='<td>N</td>';
			   

		}
		 str+='</tr>';
	  }
	}
	str+='</tbody>';
	str+='</table>';
	$("#buldingConstituenciesDivId").append(str);
	 $('#constituencyOverView').dataTable({
		"iDisplayLength": 50,
		"aLengthMenu": [[50, 100, 150, -1], [50, 100, 150, "All"]]
		});
	
	
	
}
function getConstituencyDetalReport(constituencyId,locationName){

	var jobj = {
		constituencyId : constituencyId,
		task:"getDistrictDetailsForConstituency"
	}
	$.ajax({
          type:'POST',
          url: 'getDistrictDetailsForConstituencyAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(jobj)}
    }).done(function(result){
	var district = parseInt(result[1].id);
		window.open('constituencyDetailReportAction.action?districtId='+district+'&constituencyId='+constituencyId+'&task='+locationName+' Assembly','_blank');
	});
	
}

function getTotalCasteCounts()
{
var jObj = 
	{
	
	 task : "getCount"
	}
	$.ajax({
			type:'GET',
			url: 'getTotalCasteCollectedCountsAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
			}).done(function(result){

				buildCastCounts(result);
			});
			setTimeout(getTotalCasteCounts, 30000);
}

function buildCastCounts(result)
{

var str=''
$("#TotalcasteCount").html('<a href="#" onclick="getDateWiseCount(\'\')">'+result.count+'</a>');
$("#TodaycasteCount").html(''+result.casteCount+'');

}
function getDateWiseCount(date)
{
	$("#popupImg").show();
	$("#PopupContentDiv").html('');
	

var jObj = 
	{
	 date:date,
	 task : "getDataCount"
	}
	$.ajax({
			type:'GET',
			url: 'getCasteCollectedCountsAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
			}).done(function(result){

				buildDayWiseCasteCount(result);
			});


}
function buildDayWiseCasteCount(result)
{
$("#popupImg").hide();
var str='';
str+='<table class="table table-bordered">';
str+='<thead>';
str+='<th>Date</th>';
str+='<th>Count</th>';
str+='</thead>';
for(var i in result)
	{
	str+='<tr>';
	str+='<td>'+result[i].surveyDate+'</td>';
	str+='<td>'+result[i].count+'</td>';
	str+='</tr>';
	}
str+='</table>';
$("#PopupContentDiv").html(str);
$("#dateWisecastePopupDiv").dialog({
			title:"Caste Collected Count Details",
			autoOpen: true,
			show: "blind",
			width: 500,
			
			modal: true,
			height:500,
			hide: "explode"
		});
	
}

function getConstituencyWiseReport(constituencyIds){

console.log(constituencyIds);
var jObj = 
	{
	 constituencyIds:constituencyIds,
	 task : "getConstituenciWiseReport"
	}
	$.ajax({
		type:'GET',
		url: 'getConstituencyWiseReportForDashBoardAction.action',
		dataType: 'json',
		data: {task:JSON.stringify(jObj)},
	}).done(function(result){
		buildingSurveyCompletedLocationsDetailsForSurveyStartedConstituencies(result);
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
getBigPictureDetails();

function getBigPictureDetails()
{
	alert(123);
	var jsObj = {
	}
	$.ajax({
			type:'GET',
			url: 'getBigPictureDetails.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)},
		 }).done(function(result){	
		});	
		getInternalVerificationSummary();
		getQcVerificationSummaryReport();
		getTodayTeamDetails();
}

function getInternalVerificationSummary()
{
	var jsObj = {
	}
	$.ajax({
			type:'GET',
			url: 'getInternalVerificationSummary.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)},
		 }).done(function(result){	
		});	
}
function getQcVerificationSummaryReport()
{
	var jsObj = {
	}
	$.ajax({
			type:'GET',
			url: 'getQcVerificationSummaryReport.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)},
		 }).done(function(result){	
		});	
}
function getTodayTeamDetails()
{
	var jsObj = {
	}
	$.ajax({
			type:'GET',
			url: 'getTodayTeamDetails.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)},
		 }).done(function(result){	
		});	
}


