<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <html>
  <head>	
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">	

		<style>
			body{background:#f0f0f0;}
			.m_top20{margin-top:20px;}
			.widgetservey{background:#fafafa; display:block; border:1px solid #cccccc; width:100%; padding:0px 20px 20px 20px;}
			.widgetservey_Red{background:#fafafa; display:block; border:1px solid #cccccc; width:100%; padding:0px 20px 20px 20px; border-top:3px solid #ff0000;}
			.widgetservey h4{font-size:26px; color:#333; line-height:30px; border-bottom: 1px solid #cccccc;text-align:center; text-transform:uppercase; text-shadow: 0px 1px #4f4f4f;}
			.widgetservey_Red h4{font-size:26px; color:#ff0000; line-height:30px; border-bottom: 1px solid #cccccc;text-align:center; text-transform:uppercase; text-shadow: 0px 1px #4f4f4f;}
			.survey_nav{height:40px; background: #ffea51;
					background: url(data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiA/Pgo8c3ZnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgd2lkdGg9IjEwMCUiIGhlaWdodD0iMTAwJSIgdmlld0JveD0iMCAwIDEgMSIgcHJlc2VydmVBc3BlY3RSYXRpbz0ibm9uZSI+CiAgPGxpbmVhckdyYWRpZW50IGlkPSJncmFkLXVjZ2ctZ2VuZXJhdGVkIiBncmFkaWVudFVuaXRzPSJ1c2VyU3BhY2VPblVzZSIgeDE9IjAlIiB5MT0iMCUiIHgyPSIwJSIgeTI9IjEwMCUiPgogICAgPHN0b3Agb2Zmc2V0PSIwJSIgc3RvcC1jb2xvcj0iI2ZmZWE1MSIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjEwMCUiIHN0b3AtY29sb3I9IiNmZmE2MDAiIHN0b3Atb3BhY2l0eT0iMSIvPgogIDwvbGluZWFyR3JhZGllbnQ+CiAgPHJlY3QgeD0iMCIgeT0iMCIgd2lkdGg9IjEiIGhlaWdodD0iMSIgZmlsbD0idXJsKCNncmFkLXVjZ2ctZ2VuZXJhdGVkKSIgLz4KPC9zdmc+);
					background: -moz-linear-gradient(top,  #ffea51 0%, #ffa600 100%);
					background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#ffea51), color-stop(100%,#ffa600));
					background: -webkit-linear-gradient(top,  #ffea51 0%,#ffa600 100%);
					background: -o-linear-gradient(top,  #ffea51 0%,#ffa600 100%);
					background: -ms-linear-gradient(top,  #ffea51 0%,#ffa600 100%);
					background: linear-gradient(to bottom,  #ffea51 0%,#ffa600 100%);
					filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ffea51', endColorstr='#ffa600',GradientType=0 );
					}
			.survey_nav ul li{line-height:40px;}
			.survey_nav ul li a{color:#333; font-weight:bold; font-size:13px; padding:12px 4px;text-decoration:none;text-shadow:0px 1px #ffcc00; }
			.survey_nav ul li a:hover{background:rgba(255,0,0,0.1);}
			.survey_nav ul li a.selected{color:#fff; background:red;text-shadow:0px 1px #4f4f4f; }
			.requiredFont{
				color:red;
				font-size:13px;
			}
			
			.highlight{
			cursor: pointer;
			}
			
			.datePickerCls{
			 cursor: text !important;
			}
			
			.survey_nav ul li .dropdown-menu{background:#ffcc00; border-top-left-radius:0px; border-top-right-radius:0px;margin-top:-1px; margin-right:5px;}
			.survey_nav ul li .dropdown li a{color:#333; }
			
			.ui-multiselect{
				width:200px !important;
				}

		</style>
		
  </head>
  
  <body>
	<div class="container">
		<div class="row">
			<div class="span12 m_top20 survey_nav">
				<ul class="inline unstyled">
					<li><a class="highlight  selected" id="callCenterTab" onclick="showHideTabs(this.id);"> Call Centre </a></li>
					<li><a class="highlight" id="startTimeTab" onclick="showHideTabs(this.id);">Survey Start Time</a></li>
					<li><a class="highlight" id="boothWiseTab" onclick="showHideTabs(this.id);">Booth Wise Report</a></li>
				</ul>
			</div>
		</div>
		<div class="row" id="callCenter">
			<div class="span12">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
							<h4>Call Center Verification</h4>	
					<div id="errDivId" style="color:#FF0020;font-size:15px;" ></div>							
						<div class="row-fluid">
							
							
							<div class="span4">
								<label> Constituency </label>
								<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level" id="constiList" list="constituenciesList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Constituency" onChange="getConstituencyLeadersList('leaderList');"/>
							</div>
							<div class="span4">
								<label> Lead </label>
								<select class="input-block-level" id="leaderList" onchange="getAssignedBoothsForLeader('boothList');"><option value="0"> Select Leader </option></select>
							</div>
							<div class="span4">
								<label> Booth </label>
								<select class="input-block-level" id="boothList"><option value="0"> Select Booth </option></select>
							</div>
						</div>
						 <div class="row text-center m_top20"><button class="btn btn-success" type="button" onclick="getSurveyVotersList();">GET DETAILS</button></div> 
						<div class="row-fluid">
							<div id="casteInfoDiv" style="background-color: #dff0d8; padding: 5px;display:none;margin-top:25px;margin-bottom:25px;"></div>	
						</div>
						
						<div class="row-fluid">
							<div id="voterInfoDIv"></div>	
						</div>
						<!--<div class="row text-center m_top20"><button class="btn btn-large btn-success" type="button" onclick="saveSurveyCallStatusDetils();">UPDATE STATUS</button></div>
							-->
					</div>
				</div>
			</div>
		</div>
		
		<div class="row" id="startTime">
			<div class="span12">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
							<h4>Data Collector Started Time</h4>	
					<div id="errDivIdForStartTime" style="color:#FF0020;font-size:15px;" ></div>							
					</div>
				</div>
			</div>
		</div>
		
		<div class="row" id="boothWise">
			<div class="span12">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
							<h4>Booth Wise Report</h4>	
					<div id="errDivIdForStartTime" style="color:#FF0020;font-size:15px;" ></div>							
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
showHideTabs('callCenterTab');
$(".highlight").click(function()
{
	$(".highlight").removeClass("selected");
	$(this).addClass("selected");
})
	
function showHideTabs(id)
{
	if(id == "callCenterTab")
	{
		$('#callCenter').show();
		$('#startTime').hide();
		$('#boothWise').hide();
	}
	else if (id == "startTimeTab")
	{
		$('#callCenter').hide();
		$('#startTime').show();
		$('#boothWise').hide();
	}
	else
	{
		$('#callCenter').hide();
		$('#startTime').hide();
		$('#boothWise').show();
	}
}
function getConstituencyLeadersList(divId){
var value = $('#constiList').val();
var jsObj = 
	{
		constiId:value,
		task : "assignLeader"
	}

	$.ajax({
		type:'GET',
		url: 'getSurveyConstituencyLeadersListAction.action',
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		}).done(function(result){
				$('#'+divId+'').find('option').remove();
				$('#'+divId+'').append('<option value="0"> Select Leader </option>');
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
				$('#'+divId+'').find('option').remove();
				$('#'+divId+'').append('<option value="0"> Select Booth </option>');
				if(result != null && result.length>0){
					for(var i in result){
						$('#'+divId+'').append('<option value="'+result[i].id+'">Booth No - '+result[i].name+'</option>');
					}
				}
		});
		
}

function getSurveyVotersList(){
var constiId = $('#constiList').val();
var leaderId = $('#leaderList').val();
var boothId = $('#boothList').val();
var flag = true;
$('#errDivId').html('');
if(constiId == 0 ){
	$('#errDivId').html(' Please select Constituency.');
	flag = false;
}
else if(leaderId == 0){
	$('#errDivId').html(' Please select Leader .');
	flag = false;
}

else if(boothId == 0){
	$('#errDivId').html(' Please select Booth.');
	flag = false;
}

if(flag)
	{
		var jsObj = 
		{
			constituencyId:constiId,
			surveyUserId:leaderId,
			boothId : boothId,
			task : "getSurveyVotersList"
		}

	$.ajax({
		type:'GET',
		url: 'getSurveyVotersListAction.action',
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		}).done(function(results){

		$('#voterInfoDIv').html('');
		$('#casteInfoDiv').html('');
		var str = '';
		var serialNo = 1;
		var totalVoters = 0;
		totalVoters = results != null ? results[0].count:0;
		if(results != null && results[0].subList.length>0){
		
			var result = results[0].subList;
				str +='<table class="table table-bordered m_top20 table-hover table-striped" id="voterDetlsTab" >';
				str +='				<thead class="alert alert-success">';
				str +='					<tr>';
				str +='						<th> S.No </th>';
				str +='						<th> House No. </th>';
				str +='						<th> Name </th>';
				str +='						<th> Gaurdian Name </th>';
				str +='						<th> Caste </th>';
				//str +='						<th> Caste Matched  </th>';
				str +='						<th> Mobile Number </th>';
				//str +='						<th> Mobile Matched </th>';
				str +='						<th> Hamlet </th>';
				str +='						<th>  </th>';

				str +='					</tr>';
				str +='				</thead>';
				str +='				<tbody>';
				for(var i in result)
				{

					str +='					<tr>';
					str +='						<td>'+serialNo+'';
					str +='<input type="hidden" value="'+result[i].voterId+'" id="voterId"/>';
					str +='<input type="hidden" value="'+result[i].userid+'" id="surveyUserId"/></td>';
					str +='						<td>'+result[i].partNo+'</td>';
					str +='						<td>'+result[i].userName+'</td>';
					str +='						<td>'+result[i].voterName+'</td>';
					//str +='						<td>'+result[i].caste+'</td>';
					
					str +='						<td>'+result[i].caste+'';
					str +='							<div data-toggle="buttons-radio" class="btn-group">';
		str +='	<button class="btn btn-mini" type="button" onclick="updateStatus(\'isCasteMatched'+i+'\',1);">correct</button>';
		str +='	<button class="btn btn-mini" type="button" onclick="updateStatus(\'isCasteMatched'+i+'\',0);">wrong</button>';
					str +='             <div id="casteErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
					str +='             <input type="hidden" value="" id="isCasteMatched'+i+'"/>';
					str +='							 </div>';
					str +='						</td>';				
					
					var mobNo = '';
					if(result[i].mobileNo == 'null')
					{
						mobNo = '';
					}
					else
					{
						mobNo = result[i].mobileNo
					}
					str +='<td>'+mobNo+'<div data-toggle="buttons-radio" class="btn-group">';
	str +='<button class="btn btn-mini" type="button" onclick="updateStatus(\'isTestedMobile'+i+'\',1);">correct </button>';
	str +='<button class="btn btn-mini " type="button" onclick="updateStatus(\'isTestedMobile'+i+'\',0);">wrong</button>';
	str +='             <div id="mobileErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
					str +='					<input type="hidden" value="" id="isTestedMobile'+i+'"/>';
					str +='							 </div></td>';
					
					str +='						<td>';
					str +='							<div class="callcenter_voterDetals_widget">';
					str +='								<ul class="unstyled inline">';
					str +='									<li>'+result[i].hamletName+'</li>';					
					str +='								</ul>';
					str +='							</div>';
					str +='						</td>';
					str +='						<td> <input class="btn" type="button" onclick="saveVoterDetails('+result[i].voterId+','+result[i].userid+',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',\'casteErrDiv'+i+'\',\'mobileErrDiv'+i+'\');" value="save"/></td>';

					str +='					</tr>';
					
					
			serialNo = serialNo+1;
				}
				str +='				</tbody>';
				str +='			</table>';
			
		}
		else{
		str +='	No Data Available. ';
		}
		
		$('#voterInfoDIv').html(str);
		$('#voterDetlsTab').dataTable();
		
		
		var boothNo = $('#boothList option:selected').text();
		var str1='';
		if(results != null && results[0].genericVOList.length>0)
		{
				$('#casteInfoDiv').show();
				str1 +='<b> Top Castes </b> :  ';
				var length = results[0].genericVOList.length;
				
				for(var k in results[0].genericVOList){
				var perc = (results[0].genericVOList[k].count * 100 ) / totalVoters;
					str1 +=''+results[0].genericVOList[k].name+' ('+parseFloat(perc).toFixed(2)+') ';
					
					if(k < length-1){
						str1 +=', ';
					}					
				}
		 }
		 
		  $('#casteInfoDiv').html(str1);
		});
	}	
}

function updateStatus(id,value){
		$('#'+id+'').val(value);
}

function saveVoterDetails(voterId,surveyUserId,isCasteMatched,mobileMatched,casteErrDiv,mobileErrDiv){

var voterInfoArr = new Array();

	var isMobileVerified = $('#'+mobileMatched+'').val();
	var isMatched = $('#'+isCasteMatched+'').val();
	var boothId = $('#boothList').val();
	$('#'+casteErrDiv+'').html('');
	$('#'+mobileErrDiv+'').html('');

	if(isMatched == undefined || isMatched.trim().length == 0){
		$('#'+casteErrDiv+'').html('Caste verification not updated.');
	} 
	else if(isMobileVerified == undefined || isMobileVerified.trim().length ==0){
		$('#'+mobileErrDiv+'').html('Mobile verification not updated.');
	} 
	else{
	
	var obj = {
		voterId:voterId,
		surveyUserId:surveyUserId,
		isMobileVerified:isMobileVerified,
		isMatched :isMatched,
		boothId : boothId
	}
	
	voterInfoArr.push(obj);
	
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
				getSurveyVotersList();
			}
			else{
				alert('Error occured while saving record.');
			}
			
		});
	}
}
	</script>
	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/jquery.dataTables.js"></script>
   <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
  </body>
 </html>