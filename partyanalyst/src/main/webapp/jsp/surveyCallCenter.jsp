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
			/*----*/
			.callcenter_voterDetals_widget ul li{ padding:10px 5px;}
			
				
		</style>
		
  </head>
  
  <body>
	<div class="container">
		
		<div class="row">
			<div class="span12">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
							<h4>Call Center Verification</h4>														
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
							<div id="voterInfoDIv"></div>	
						</div>
						<div class="row text-center m_top20"><button class="btn btn-large btn-success" type="button" onclick="saveSurveyCallStatusDetils();">UPDATE STATUS</button></div>
							
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
	
	
	
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



function getSurveyVotersList(divId){
var constiId = $('#constiList').val();
var leaderId = $('#leaderList').val();
var boothId = $('#boothList').val();
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
		}).done(function(result){

		$('#voterInfoDIv').html('');
		var str = '';
		if(result != null && result.length>0){
			
				str +='<table class="table table-bordered m_top20 table-hover table-striped">';
				str +='				<thead class="alert alert-success">';
				str +='					<tr>';
				str +='						<th>User ID</th>';
				str +='						<th>Voter ID</th>';
				str +='						<th>Calling Number</th>';
				str +='						<th>Voter Detailes</th>';
				str +='						<th>Matched Status</th>';
				str +='					</tr>';
				str +='				</thead>';
				str +='				<tbody>';
				for(var i in result)
				{
					str +='<input type="hidden" value="'+result[i].voterId+'" id="voterId'+i+'" name="Id'+i+'" class="voterInfoCls"/>';
					str +='<input type="hidden" value="'+result[i].userid+'" id="surveyUserId'+i+'"/>';
					str +='					<tr>';
					str +='						<td>'+result[i].userName+'</td>';
					str +='						<td>'+result[i].voterIDCardNo+'</td>';
					str +='						<td>'+result[i].mobileNo+' ';
					str +='	<div data-toggle="buttons-radio" class="btn-group">';
	str +='<button class="btn btn-mini" type="button" onclick="updateStatus(\'isTestedMobileId'+i+'\',1);">correct </button>';
	str +='<button class="btn btn-mini active" type="button" onclick="updateStatus(\'isTestedMobileId'+i+'\',0);">wrong</button>';
					str +='					<input type="hidden" value="0" id="isTestedMobileId'+i+'"/>';
					str +='							 </div></td>';
					str +='						<td>';
					str +='							<div class="callcenter_voterDetals_widget">';
					str +='								<ul class="unstyled inline">';
					str +='						<li><lable><strong>Caste:</strong> '+result[i].caste+'</lable> </li>';
					str +='				<li><lable><strong>Hamlet:</strong> '+result[i].hamletName+'</lable></li>';
					if(result[i].localArea.trim().length > 0)
						str +='			<li><lable><strong>Local Area:</strong>  '+result[i].localArea+'</lable></li>';		
					
					if(result[i].cadre != null && result[i].cadre.toLowerCase() == 'y'){
						str +='	<li><lable><strong class="badge">C</strong> </lable></li>';
					}
					if(result[i].influencePeople != null &&  result[i].influencePeople.toLowerCase() == 'y'){
						str +='	<li><lable><strong class="badge">I</strong> </lable></li>';
					}		
					
					str +='								</ul>';
					str +='							</div>';
					str +='						</td>';
					str +='						<td>';
					str +='							<div data-toggle="buttons-radio" class="btn-group">';
		str +='	<button class="btn" type="button" onclick="updateStatus(\'isDetailsMatchedId'+i+'\',1);">Yes</button>';
		str +='	<button class="btn active" type="button" onclick="updateStatus(\'isDetailsMatchedId'+i+'\',0);">No</button>';
					str +='             <input type="hidden" value="0" id="isDetailsMatchedId'+i+'"/>';
					str +='							 </div>';
					str +='						</td>';
					str +='					</tr>';
				}
				str +='				</tbody>';
				str +='			</table>';
			
		}
		else{
			str +='	<b> Data Collection not yet started. </b>';
		}
		$('#voterInfoDIv').html(str);
		});
		
}

function updateStatus(id,value){
		$('#'+id+'').val(value);
}

function saveSurveyCallStatusDetils(){

var voterInfoArr = new Array();

$('.voterInfoCls').each(function(){
	var id = $(this).attr('name');
	var voterId  = $('#voter'+id+'').val();
	var surveyUserId = $('#surveyUser'+id+'').val();
	var isMobileVerified = $('#isTestedMobile'+id+'').val();
	var isMatched = $('#isDetailsMatched'+id+'').val();
	
	var obj = {
		voterId:voterId,
		surveyUserId:surveyUserId,
		isMobileVerified:isMobileVerified,
		isMatched :isMatched	
	}
	
	voterInfoArr.push(obj);
});

console.log(voterInfoArr);
	

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
			console.log(result);
		});
	
}
	</script>
	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
  </body>
 </html>