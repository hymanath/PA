<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <html>
  <head>	
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<script src="js/maps/leaflet.js"></script>
<link rel="stylesheet" href="css/leaflet.css"></link>
<script src="js/maps/google.js"></script>
<script src="js/maps/Permalink.js"></script>

<script src="js/maps/googleMap.js"></script>

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
			
			table tr td a{color:#333;}

			.wiget-yellow{background:#ffcc00; border:1px solid #ccc; width:100%; height:370px;padding:10px;}
			.wiget-yellow:hover{box-shadow: 0px -1px 5px #333;}
			.wiget-yellow small{color:red; font-size:18px;}
			.wiget-yellow h4{font-size:22px;border-bottom:2px solid #eee; text-align:center;}
			/*-----*/
			.boothdetails-nav li a{color:#333333; background:#eee;padding:10px; width:136px; display:table;line-height:20px;border:1px solid #ccc;text-align:center; font-weight:bold; text-transform:uppercase; margin-bottom: 10px;text-decoration:none; font-size:16px;}
			.boothdetails-nav li a:hover{ background:#ccc; border:1px solid #ffcc00;text-show:0px 1px #fff;}
			.booths-Overview-widget{background:#ddd;padding:10px; width:100%;}
			.booths-Overview-widget-nav li{color:#333333; background:#F6DD78;padding:10px; width:140px; display:table;line-height:20px;border:1px solid #ccc;text-align:center; font-weight:bold; text-transform:uppercase; margin: 10px;text-decoration:none; font-size:16px;}
			.booths-Overview-widget-nav li hgroup h4,h5{font-size:15px;}
	
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
					
					<li><a class="highlight   selected" id="startTimeTab" onclick="showHideTabs(this.id);">Field Report</a></li>
					<li><a class="highlight" id="boothWiseTab" onclick="showHideTabs(this.id);">Data Report</a></li>
					<li><a class="highlight" id="callCenterTab" onclick="showHideTabs(this.id);"> Web Monitoring </a></li>
				</ul>
			</div>
		</div>
		<div class="row" id="callCenter">
			<div class="span12">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
							<h4> Web Monitoring </h4>	
					<!--		
					<div id="errDivId" class="errClass offset2" style="color:#FF0020;font-size:15px;" ></div>							
						<div class="row-fluid offset2">
							
							
							<div class="span3">
								<label> Constituency </label>
								<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level" id="constiList" list="constituenciesList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Constituency" onChange="getConstituencyLeadersList('leaderList');"/>
							</div>
							<div class="span2">
								<label>Select User </label>
								<select class="input-block-level" id="leaderList" onchange="getAssignedBoothsForLeader('boothList');"><option value="0"> Select Leader </option></select>
							</div>
							<div class="span1" style="margin:29px -9px 0px 1px;width: 15px;">
								<img id="userTypeProcessingImage" style="display: none;" src="./images/icons/search.gif" alt="Processing Image"></img>
							</div>
							<div class="span3">
								<label>Select Booth </label>
								<select class="input-block-level" id="boothList"><option value="0"> Select Booth </option></select>
							</div>
							<div class="span1" style="margin:29px 8px 0px 6px; width: 15px;">
								<img id="boothProcessingImage" style="display: none;" src="./images/icons/search.gif" alt="Processing Image"></img></div>
						</div>
						 <div class="row text-center m_top20"><button class="btn btn-success" type="button" onclick="getSurveyVotersList();">GET DETAILS</button><img id="webMonitoringImage" style="display: none;" src="./images/icons/search.gif" alt="Processing Image"></img></div> 
						 -->
						 <img id="searchDataImg" style="display: none;" src="./images/icons/barloader.gif" alt="Processing Image"></img>
						<div class="row-fluid">
							<div id="casteInfoDiv" style="background-color: #dff0d8; padding: 5px;display:none;margin-top:25px;margin-bottom:25px;" class="errClass"></div>	
						</div>
						
						<div class="row-fluid">
							<div id="voterInfoDIv" class="errClass"></div>	
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
							<h4>User Field Report</h4>						
						<div class="row">
						<div class="span8 offset3">
									<div class="row-fluid">
						<div id="errDivIdForStartTime" class="span8 errClass offset" style="color:#FF0020;font-size:15px;" ></div>
						</div></div></div>
						<div class="row">
								<div class="span8 offset3">
									<div class="row-fluid">
										
										<div class="span6">
											Select Constituency <font class="requiredFont">*</font>
												<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level" id="userConstituencyId" list="constituenciesList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Constituency" />
										</div>
										<div class="span6">
											Select Date <font class="requiredFont">*</font>
											<div>
											<input type="text" placeholder="Select Date" class="input-block-level date" id="dateId" readonly>
											</div>
										</div>
										
									</div>	
								
									</div>
									</div>
					
						<div class="row text-center m_top20"><button type="button" class="btn btn-success" style="cursor:pointer;" onclick="getUserDetailsByConstituency()">SUBMIT</button></div>
						  <div id="userDetailsReportDiv"></div>
						<div class="row-fluid " id = "leaderDetailsDiv"></div>
						<div class="row-fluid " id="detaildDiv" style="display:none;">
							<div class="span12 m_top20 widgetservey" id="weathermap" style="height:500px"></div>
						</div>
						<div class="row-fluid " id= "tableDiv"></div>
						<div class="row-fluid " id= "responceCountDiv"></div>
				
											
					</div>
				</div>
			</div>
		</div>
		
		<div class="row" id="boothWise">
			<div class="span12">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
							<h4>Field Data Report</h4>	
					<div id="errDivIdForStartTime" style="color:#FF0020;font-size:15px;" ></div>	
					
	<div class="row">
								<div class="span8 offset2">
								<div id="errorMsgDiv" class="offset1 errClass"  style="color:#FF0020;font-size:15px;"></div>
									<div class="row-fluid">
									
										<div class="span4 offset1">
											<label>Select Constituency</label>
										<!--<select name="constituency" id="constituencyId" list="constituenciesList" style="width:130px;"></select>-->
										<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level" id="constituencyId" list="constituenciesList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Constituency" onchange="setConstituency(this.value);" />
								
										</div>
										<div class="span3">
											<label>Select User Type</label>
											<select name="constituency" id="userTypeId"  style="width:145px;" onchange="setUserTypeId(this.value)">
											<option value="0">Select user type</option>
											<option value="1">Data Collectors</option>
											
											</select>
										</div>	
										<div class="span3">
											<label>Select Date</label>
											<input type="text" placeholder="Select Date" class="input-block-level date" id="FielddateId" readonly/>
										</div>	
									</div>	
									
								</div>
							</div>
							<div class="row text-center m_top20" style="margin-right:51px;"><button type="button" class="btn btn-success" onClick="getSurveyUserLoctionCount();">SUBMIT</button>
							<img id="processingImg" style="display: none;" src="./images/icons/search.gif" alt="Processing Image"></img>
							</div>
							<div id="basicCountDiv" class="span10 m_top20">

					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
showHideTabs('startTimeTab');
$(".highlight").click(function()
{
	$(".highlight").removeClass("selected");
	$(this).addClass("selected");
})
	

function showHideTabs(id)
{
	$(".errClass").html('');
	
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
		//getconstituencies('userConstituencyId');
	}
	else
	{
		$('#callCenter').hide();
		$('#startTime').hide();
		$('#boothWise').show();
		// getconstituencies('constituencyId');
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

function getSurveyVotersList(userId,boothId,date){

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
				//str +='						<th>  </th>';

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
					
	if(result[i].casteMatched)
	{
		str +='							<div data-toggle="buttons-radio" class="btn-group">';
		str +='	<button class="btn btn-mini active" type="button" onclick="updateStatus(\'isCasteMatched'+i+'\',1,'+result[i].voterId+','+result[i].userid+',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',\'casteErrDiv'+i+'\',\'mobileErrDiv'+i+'\','+userId+','+boothId+',\''+date+'\');">correct</button>';
		str +='	<button class="btn btn-mini" type="button" onclick="updateStatus(\'isCasteMatched'+i+'\',0,'+result[i].voterId+','+result[i].userid+',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',\'casteErrDiv'+i+'\',\'mobileErrDiv'+i+'\','+userId+','+boothId+',\''+date+'\');">wrong</button>';
		str +='             <div id="casteErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
		str +='             <input type="hidden" value="1" id="isCasteMatched'+i+'"/>';
		str +='							 </div>';
	}
	else
	{
		str +='							<div data-toggle="buttons-radio" class="btn-group">';
		str +='	<button class="btn btn-mini" type="button" onclick="updateStatus(\'isCasteMatched'+i+'\',1,'+result[i].voterId+','+result[i].userid+',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',\'casteErrDiv'+i+'\',\'mobileErrDiv'+i+'\','+userId+','+boothId+',\''+date+'\');">correct</button>';
		str +='	<button class="btn btn-mini" type="button" onclick="updateStatus(\'isCasteMatched'+i+'\',0,'+result[i].voterId+','+result[i].userid+',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',\'casteErrDiv'+i+'\',\'mobileErrDiv'+i+'\','+userId+','+boothId+',\''+date+'\');">wrong</button>';
					str +='             <div id="casteErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
					str +='             <input type="hidden" value="" id="isCasteMatched'+i+'"/>';
					str +='							 </div>';
	}

					str +='						</td>';				
					
	var mobNo = '';

	if(result[i].mobileNo != null)
	{						
		mobNo = result[i].mobileNo					
	str +='<td>'+mobNo+'<div data-toggle="buttons-radio" class="btn-group">';					
	if(result[i].mobileMatchedCount != 0)
	{
	
		str +='<button class="btn btn-mini active" type="button" onclick="updateStatus(\'isTestedMobile'+i+'\',1,'+result[i].voterId+','+result[i].userid+',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',\'casteErrDiv'+i+'\',\'mobileErrDiv'+i+'\','+userId+','+boothId+',\''+date+'\');">correct </button>';
	str +='<button class="btn btn-mini " type="button" onclick="updateStatus(\'isTestedMobile'+i+'\',0,'+result[i].voterId+','+result[i].userid+',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',\'casteErrDiv'+i+'\',\'mobileErrDiv'+i+'\','+userId+','+boothId+',\''+date+'\');">wrong</button>';
	str +='             <div id="mobileErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
					str +='					<input type="hidden" value="1" id="isTestedMobile'+i+'"/>';
					str +='							 </div>';
					
	}
	else
	{	
	str +='<button class="btn btn-mini" type="button" onclick="updateStatus(\'isTestedMobile'+i+'\',1,'+result[i].voterId+','+result[i].userid+',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',\'casteErrDiv'+i+'\',\'mobileErrDiv'+i+'\','+userId+','+boothId+',\''+date+'\');">correct </button>';
	str +='<button class="btn btn-mini " type="button" onclick="updateStatus(\'isTestedMobile'+i+'\',0,'+result[i].voterId+','+result[i].userid+',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',\'casteErrDiv'+i+'\',\'mobileErrDiv'+i+'\','+userId+','+boothId+',\''+date+'\');">wrong</button>';
	str +='             <div id="mobileErrDiv'+i+'" style="color:#FF0020;font-size:12px;"> </div>';
					str +='					<input type="hidden" value="" id="isTestedMobile'+i+'"/>';
					str +='							 </div>';
	}

					str +='</td>';
			}	
		else{
		str +='<td>';
		str +='					<input type="hidden" value="3" id="isTestedMobile'+i+'"/>';
		str +='</td>';

		}			
					str +='						<td>';
					str +='							<div class="callcenter_voterDetals_widget">';
					str +='								<ul class="unstyled inline">';
					str +='									<li>'+result[i].hamletName+'</li>';					
					str +='								</ul>';
					str +='							</div>';
					str +='						</td>';
					//str +='						<td> <input class="btn" type="button" onclick="saveVoterDetails('+result[i].voterId+','+result[i].userid+',\'isCasteMatched'+i+'\',\'isTestedMobile'+i+'\',\'casteErrDiv'+i+'\',\'mobileErrDiv'+i+'\');" value="save"/></td>';

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
		$('#voterDetlsTab').dataTable({});
		
		
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

function updateStatus(id,value,voterId,surveyUserId,isCasteMatched,mobileMatched,casteErrDiv,mobileErrDiv,userId,boothId,date){
		$('#'+id+'').val(value);
		
		
	var voterInfoArr = new Array();
	var isMobileVerified = $('#'+mobileMatched+'').val();
	var isMatched = $('#'+isCasteMatched+'').val();
	$('#'+casteErrDiv+'').html('');
	$('#'+mobileErrDiv+'').html('');

	if(isMatched == undefined || isMatched.trim().length == 0){
		isMatched = 0;
	} 
	else if(isMobileVerified == undefined || isMobileVerified.trim().length ==0){
		isMobileVerified = 0;
	} 
	

	
	var obj = {
		voterId:voterId,
		surveyUserId:userId,
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
				getSurveyVotersList(userId,boothId,date);
			}
			else{
				alert('Error occured while saving record.');
			}
			
		});
	
	
}

/*
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

*/

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
			str+='<td> <a href="javascript:{getDataCollectorInfo('+result[i].userid+','+result[i].subList[j].boothId+',\''+$('#FielddateId').val()+'\');}">'+result[i].userName+' </a></td>';
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
					str += '<td>'+result[i].name+'</td>';
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
	  userId : 0

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
	
	//buildTable(result);
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
/* function buildTable(result)
{
	var date = $('#appendedInput').val();
	var str = '';
	 str += '<div class="span12 m_top20 widgetservey">';
	 str += '<h4>Logged in users Details </h4>';
	 str += '<div class="row-fluid" >';
	 str += '<table class="table table-bordered m_top20 table-hover table-striped username">';	
	 str += '<thead class="alert alert-success">';
	 str += '<tr> ';
	 str += '<th>User Name </th>	';
	 str += '<th>Booth</th>	';
	 str += '<th>Mandal</th>';	
	 str += '<th>Panchayat</th>	';
	 str += '<th>Area Covered</th>	';
	 str += '<th>Location</th>	';				
	 str += '<th>Data Collected Map</th>	';	
	str += '<th>Tracking MAP</th>	';		 
	 str += '</tr>	';						
	 str += '</thead>';
	 str += '<tbody>';
	 for(var i in result)
	 {
		 str += '<tr>';
		 str += '<td><a onClick="getUserDetails('+result[i].id+','+result[i].orderId+')" style="cursor: pointer;">'+result[i].name+'</a> <span class="label label-info pull-right">';
		 str += '</span></td>	';
		 str += '<td>'+result[i].partno+'</td>	';
		 str += '<td>'+result[i].value+'</td>	';
		 str += '<td>'+result[i].url+'</td>	';
		 str += '<td>'+result[i].villageCovered+'</td>	';
		 str += '<td>'+result[i].location+'</td>	';
		 str += '<td><a onClick="openTrackinWindow('+result[i].id+',\''+$('#dateId').val()+'\',1) " style="cursor: pointer;"><img src="images/DC.png"></img></a></td>	';
		 str += '<td><a onClick="openTrackinWindow('+result[i].id+',\''+$('#dateId').val()+'\',2) " style="cursor: pointer;"><img src="images/DC.png"></img></a></td>	';
		 str += '</tr>	';	
	 }		
	 str += '</tbody>';
	 str += '</table>	';
	 str += '</div>';
	 str += '</div>';
	 
	 $('#tableDiv').html(str);
} */
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
	  str+='<th>Village Covered</th>';
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
			str+='<td><a onClick="getUserDetails('+result[i].userid+','+result[i].boothId+',\'resultDiv'+i+'\',\'buildDiv'+i+'\')" style="cursor: pointer;">'+result[i].userName+'</a> <span class="label label-info pull-right"></td>';
	
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
			str+='<td>'+result[i].subList[j].villageCovered+'</td>';
			str += '<td><a onClick="openTrackinWindow('+result[i].userid+',\''+$('#dateId').val()+'\',1) " style="cursor: pointer;"><img src="images/DC.png"></img></a></td>	';
		 str += '<td><a onClick="openTrackinWindow('+result[i].userid+',\''+$('#dateId').val()+'\',2) " style="cursor: pointer;"><img src="images/DC.png"></img></a></td>	';
			str+='</tr>';
			str+='<tr id="resultDiv'+i+'" style="display:none;" class="buildDivCls">';
			str+='<td colspan="10"> <div id="buildDiv'+i+'"></div></td>';
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


function getDataCollectorInfo(userId,boothId,date){
	
	$('#boothWiseTab,#startTimeTab').removeClass('selected');
	$('#callCenterTab').addClass('selected');
	showHideTabs('callCenterTab');
	getSurveyVotersList(userId,boothId,date);

}

function setConstituency(value){
webConstId = value;
}
function setUserTypeId(value){
webuserId = value;
}

	</script>
	<script src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/jquery.dataTables.js"></script>
   <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
  </body>
 </html>