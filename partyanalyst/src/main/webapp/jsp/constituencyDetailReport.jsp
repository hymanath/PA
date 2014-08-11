<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <html>
  <head>
	<title>Dashboard</title>  

	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">	
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>

	<!--------------------
		<link rel="stylesheet" type="text/css" href="css/grid/default.css" />-->
		
			<!--------------------	-->
	<!----------------------->
		<style>
			body{background:#f0f0f0;}
			.m_top10{margin-top:10px;}
			.m_top20{margin-top:20px;}
			.m_top30{margin-top:30px;}
			.widgetservey{background:#fafafa; display:block; border:1px solid #cccccc; width:100%; padding:0px 20px 20px 20px;}
			.widgetservey_Red{background:#fafafa; display:block; border:1px solid #cccccc; width:100%; padding:0px 20px 20px 20px; border-top:3px solid #ff0000;}
			.widgetservey h4{font-size:26px; color:#333; line-height:30px; border-bottom: 1px solid #cccccc;text-align:center; text-transform:uppercase; text-shadow: 0px 1px #4f4f4f;}
			.widgetservey_Red h4{font-size:26px; color:#ff0000; line-height:30px; border-bottom: 1px solid #cccccc;text-align:center; text-transform:uppercase; text-shadow: 0px 1px #4f4f4f;}
			.username thead tr:nth-child(2){ background:#eee;}		
			.username td:first-child{ min-width: 200px; }		
			.username th small{ font-size:11px; }				
			.username th{ text-align:center; }
			/*-------*/
			
			.wiget-yellow{background:#ffcc00; border:1px solid #ccc; width:100%; height:390px;padding:10px;}
			.wiget-yellow:hover{box-shadow: 0px -1px 5px #333;}
			.wiget-yellow small{color:red; font-size:18px;}
			.wiget-yellow h4{font-size:22px;border-bottom:2px solid #eee; text-align:center;}
			/*-----*/
			.boothdetails-nav li a{color:#333333; background:#eee;padding:10px; width:165px; display:table;line-height:40px;border:1px solid #ccc;text-align:center; font-weight:bold; text-transform:uppercase; margin-bottom: 10px;text-decoration:none; font-size:16px;}
			.boothdetails-nav li a:hover{ background:#ccc; border:1px solid #ffcc00;text-show:0px 1px #fff;}
			.boothdetails-nav li a.selected{ background:#ddd; border:1px solid #ffcc00;text-show:0px 1px #fff;}
			.booths-Overview-widget{background:#ddd;padding:10px; width:100%;}
			.booths-Overview-widget-nav li{color:#333333; background:#F6DD78;padding:10px; width:140px; display:table;line-height:20px;border:1px solid #ccc;text-align:center; font-weight:bold; text-transform:uppercase; margin: 10px;text-decoration:none; font-size:16px;}
			.booths-Overview-widget-nav li hgroup h4,h5{font-size:15px;}
			
			.selectBoxWidth {
    padding: 2px;
    width: 250px;
}
.span2{
    font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
	font-weight: bold;
	margin-top: 8px;	
}
		</style>	
	
  </head>
  
  <body>
	<div class="container">
		<!-------->		
		<div class="row">
			<div class="span12">
				<!-----Constituency selection Div ---->
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top10">
							<h4>Constituency wise detailed reports</h4>
							<div class="row">
							<div id="errDivId" style="color:#FF0020;font-size:14px;" class="offset4"></div></div>
								<div class="row">
								<div class="span6 offset4 m_top10">
									<div class="row-fluid">										
										<div class="span4">
											<label>Select Constituency :</label></div>
											<!-- <select class="input-block-level" id="constiList">
											<option>01</option>
											</select>
											---->
											<div class="span6">
											<s:select theme="simple" cssClass="selectBoxWidth span12" id="constiList" list="constituenciesList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Constituency" value="task" />
										</div>	
									
									</div>										
								</div>
									</div>
							<div class="row text-center m_top10">
							<button type="button" class="btn btn-success" onclick="getConstituencyReport();">SUBMIT</button></div>
					</div>
				</div><!-----Constituency selection Div end---->
				
				<!----Constituency details main Div---->
				<div id="constiReportDiv"></div>
				<!----Constituency details main Div End---->
				<div class="row-fluid m_top20" id="summary"></div>
				<div class="row-fluid m_top20" id="boothsSummary"></div>
				<div class="row-fluid m_top20" id="CommentsDiv"></div>
			</div>
		</div>
	</div>

<script>
getTPTotalBoothsDetails();
var contiId = '${task}';
buildReport();
function buildReport(){
	if(contiId != null && contiId != 0){
		getConstituencyReport(contiId);
	}
}

function getConstituencyReport(constituencyId){
	var constituencyId = $('#constiList').val();
	var constiName = $('#constiList option:selected').text();
	if(constituencyId == 0 ){
		$('#errDivId').html(' Please select Constituency');
		return;
	}
	$('#errDivId').html('');
	var jobj = {
	constituencyId : constituencyId,
	task:"getReport"
	}
	$.ajax({
          type:'POST',
          url: 'getCosntituencyWiseReportByContiAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(jobj)}
    }).done(function(result){
		if(result != null ){
			$('#constiReportDiv').html('');
			var str ='';
			
				
					str +='<div class="row-fluid ">';
					str +='		<div class="span12 m_top20 widgetservey">';
					str +='			<h4>'+constiName +'  Constituency Survey Report </br><small class="text-center">Constituency Overview</small>';
					str +='<span class="label" style="margin-left: 10px; margin-bottom: 10px;">Total Voters - '+result.constituencyTotalVoters+'</span></h4>';
				//if(result.totalColelctedVoters != 0){
					str +='			<div class="row-fluid m_top10" >';
					str +='				<div class="span6 wiget-yellow">	';
					str +='					<h4 class="text-right">DATA COLLECTION</h4>';
					str +='					<div class="row-fluid">';
					str +='					<div class="well well-small span4  text-center">';
					str +='						<h4>'+result.totalColelctedVoters+'</h4>';
					str +='						<p class="">Data Collected</p>';
					str +='					</div>';
					str +='					<div class="well well-small span4 text-center">';
					str +='						<h4>'+result.casteCollectedCount+'</h4>';
					str +='						<p>Caste Collected</p>';
					str +='					</div>';
					str +='					<div class="well well-small span4 text-center">';
					str +='						<h4>'+result.hamletCollectedCount+'</h4>';
					str +='						<p> Hamlets Collected</p>';
					str +='					</div>		';					
					str +='					</div>';
					str +='					<div class="row-fluid">';
					str +='					<div class="well well-small span4 text-center">';
					str +='						<h4>'+result.cadreCollectedCount+'</h4>';
					str +='						<p>Cadre</p>';
					str +='					</div>';
					str +='					<div class="well well-small span4 text-center">';
					str +='						<h4>'+result.influencePeopleCollectedCount+'</h4>';
					str +='						<p>Influence People</p>';
					str +='					</div>';
					str +='					<div class="well well-small span4 text-center">';
					str +='						<h4>'+result.mobileNoCollectedCount+'</h4>';
					str +='						<p>Mobile Number</p>';
					str +='					</div>			';				
					str +='					</div>			';					
					str +='					<div class="row-fluid">';
					str +='					<div class="well well-small span10 offset1  ">	';								
					str +='						Not Collected Data Voters Count <span class="pull-right badge">'+result.notCollectedVoters+'</span>';
					str +='					</div>	';					
					str +='					</div>';
					str +='				</div>';
				/*	
					str +='				<div class="span6 wiget-yellow">	';
					str +='					<h4 class="text-right">DATA VERIFICATION</h4>';
					str +='					<div class="row-fluid">';
					str +='					<div class="well well-small span4  text-center">';
					str +='						<h4>'+result.totalVerifiedVoters+'</h4>';
					str +='						<p class="">Data Verified</p>';
					str +='					</div>';
					str +='					<div class="well well-small span4 text-center">';
					str +='						<h4>'+result.casteVerifiedCount+'</h4>';
					str +='						<p>Caste Verified</p>';
					str +='					</div>';
					str +='					<div class="well well-small span4 text-center">';
					str +='						<h4>'+result.hamletVerifiedCount+'</h4>';
					str +='						<p> Hamlets Mapped</p>';
					str +='					</div>		';					
					str +='					</div>';
					str +='					<div class="row-fluid">';
					str +='					<div class="well well-small span4 text-center">';
					str +='						<h4>'+result.cadreVerifiedCount+'</h4>';
					str +='						<p>Cadre</p>';
					str +='					</div>';
					str +='					<div class="well well-small span4 text-center">';
					str +='						<h4>'+result.influencePeopleVerifiedCount+'</h4>';
					str +='						<p>Influence People</p>';
					str +='					</div>';
					str +='					<div class="well well-small span4 text-center">';
					str +='						<h4>'+result.mobileNoVerifiedCount+'</h4>';
					str +='						<p>Mobile Number</p>';
					str +='					</div>				';			
					str +='					</div>						';		
					str +='					<div class="row-fluid">';
					str +='					<div class="well well-small span10 offset1  ">			';						
					str +='Not Verified Voter Count <span class="pull-right badge">'+result.notVerifiedVoters+'</span>';
					str +='					</div>';						
					str +='					</div>';
					str +='				</div>	';	

*/

					str +='				<div class="span6 wiget-yellow">	';
					str +='					<h4 class="text-right"> THIRD PARTY VERIFICATION</h4>';
					str +='					<div class="row-fluid">';
					str +='					<div class="well well-small span4  text-center">';
					str +='						<h4>'+result.dataTPVerifiedCount+'</h4>';
					str +='						<p class="">Data Verified</p>';
					str +='					</div>';
					str +='					<div class="well well-small span4 text-center">';
					str +='						<h4>'+result.casteTPVerifiedCount+'</h4>';
					str +='						<p>Caste Verified</p>';
					str +='					</div>';
					str +='					<div class="well well-small span4 text-center">';
					str +='						<h4>'+result.hamletTPVerifiedCount+'</h4>';
					str +='						<p> Hamlets Mapped</p>';
					str +='					</div>		';					
					str +='					</div>';
					str +='					<div class="row-fluid">';
					str +='					<div class="well well-small span4 text-center">';
					str +='						<h4>'+result.cadreTPVerifiedCount+'</h4>';
					str +='						<p>Cadre</p>';
					str +='					</div>';
					str +='					<div class="well well-small span4 text-center">';
					str +='						<h4>'+result.influencePeopleTPVerifiedCount+'</h4>';
					str +='						<p>Influence People</p>';
					str +='					</div>';
					str +='					<div class="well well-small span4 text-center">';
					str +='						<h4>'+result.mobileNoTPVerifiedCount+'</h4>';
					str +='						<p>Mobile Number</p>';
					str +='					</div>				';			
					str +='					</div>						';		
					str +='					<div class="row-fluid">';
					str +='					<div class="well well-small span10 offset1  ">			';						
					str +='Not Third Party Verified Voter Count <span class="pull-right badge">'+result.notTPVerifiedVoters+'</span>';
					str +='					</div>';						
					str +='					</div>';
					str +='				</div>	';					
					str +='			</div>';
										
					str +='			</div>';
					
					
					str +='			<div class="row-fluid m_top20" >';
					str +='						<div class="span12">';
					str +='							<p class="text-center"> <span class="text-error">Note:</span><span class="text-success"> Click on Booth Number to view Booth Overview</p>';
					str +='							<ul class="inline unstyled boothdetails-nav">';
					/*
					if(result.boothsList != null && result.boothsList.length > 0){
					var count = 0;
					var divCount = 1;
						for(var i in result.boothsList){
						count = count +1;
							str +='	<li><a href="javascript:{getBoothWiseReport(\'boothsDiv'+divCount+'\','+result.boothsList[i].id+',\'boothNo'+count+'\','+result.boothsList[i].name+')}" id="boothNo'+count+'" class="boothListCls" >Booth - '+result.boothsList[i].name+'</a></li>';
							
							if(count%5 == 0){					
								str +='<div id="boothsDiv'+divCount+'" class="boothsDivCls"></div>';
								divCount = divCount+1;
							}
						}
						str +='<div id="boothsDiv'+divCount+'" class="boothsDivCls"></div>';				
					}
					*/
					
					if(result.thirdPartyboothsList != null && result.thirdPartyboothsList.length > 0){
					var count = 0;
					var divCount = 1;
						for(var i in result.thirdPartyboothsList){
						count = count +1;
							str +='	<li><a href="javascript:{getBoothWiseReport(\'boothsDiv'+divCount+'\','+result.thirdPartyboothsList[i].id+',\'boothNo'+count+'\','+result.thirdPartyboothsList[i].name+')}" id="boothNo'+count+'" class="boothListCls" >Booth - '+result.thirdPartyboothsList[i].name+'</a></li>';
							
							if(count%5 == 0){					
								str +='<div id="boothsDiv'+divCount+'" class="boothsDivCls"></div>';
								divCount = divCount+1;
							}
						}
						str +='<div id="boothsDiv'+divCount+'" class="boothsDivCls"></div>';				
					}
					else
					{					
					str +='<div style="font-weight:bold;font-size:13px;"> No Booths are assigned for Third Party. </div>';
					}
					
					str +='							</ul>';
					str +='						</div>';
					str +='				</div>';
			
			/* }
			else{
					str += '				<div class="row-fluid">';
					str += '					<p>Data Collection not yet started...</p>';
					str += '				</div>';
			} */
			
		$('#constiReportDiv').html(str);
		}
	});
	
	
}


function getBoothWiseReport(divId,boothId,boothListId,boothNo){

var constituencyId =$('#constiList').val();
$('.boothListCls').removeClass('selected');
$('#'+boothListId+'').addClass('selected');

var jobj = {
	constituencyId : constituencyId,
	boothId : boothId,
	task:"getReport"
}
	$.ajax({
          type:'POST',
          url: 'getBoothWiseDetailsAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(jobj)}
    }).done(function(result){
	$('#'+divId+'').html('');
	$('.boothsDivCls').html('');
	
		var str = '';
			str += '		<div class="row-fluid">';
			str += '			<div class="span12 booths-Overview-widget">';
			str += '				<div class="row-fluid">';
			str += '					<h4> Booth No - '+boothNo+' Total Voters - '+result.constituencyTotalVoters+'</h4>';
			str += '				</div>';
			str += '				<div class="row-fluid">';
			str += '					<h5 class="text-center label"> DATA COLLECTION </h5>';
			str += '				</div>';
			str += '				<div class="row-fluid">';
			str += '				<ul class="inline unstyled booths-Overview-widget-nav">';
			str += '					<li>';
			str += '					<hgroup>';
			str += '							<h4>CASTE</h4>';
			str += '							<h2>'+result.casteCollectedCount+'</h2>';
			str += '							<h5>Completed</h5>';
			str += '						</hgroup>';
			str += '					</li>';
			str += '					<li>';
			str += '						<hgroup>';
			str += '							<h4>HAMLET</h4>';
			str += '							<h2>'+result.hamletCollectedCount+'</h2>';
			str += '							<h5>Mapped</h5>';
			str += '						</hgroup>';
			str += '				</li>';
			str += '					<li>';
			str += '						<hgroup>';
			str += '						<h4>LOCAL AREA</h4>';
			str += '							<h2>'+result.localAreaDataCount+'</h2>';
			str += '							<h5>Identified</h5>';
			str += '						</hgroup>';
			str += '					</li>';
			str += '					<li>';
			str += '						<hgroup>';
			str += '							<h4>CADRE</h4>';
			str += '							<h2>'+result.cadreCollectedCount+'</h2>';
			str += '							<h5>Identified</h5>';
			str += '						</hgroup>';
			str += '					</li>';
			str += '					<li>';
			str += '						<hgroup>';
			str += '							<h4 style="font-size:14px;">INFLUENCE PEOPLE</h4>';
			str += '							<h2>'+result.influencePeopleCollectedCount+'</h2>';
			str += '							<h5>Identified</h5>';
			str += '						</hgroup>';
			str += '					</li>';
			str += '				</ul>';
			str += '			</div>';
			str += '			</div>';
			str += '		</div> ';
			
			/*
			
			if( result.casteVerifiedCount !=0 || result.hamletVerifiedCount != 0 || result.totalVerifiedVoters != 0 || result.cadreVerifiedCount != 0 || result.influencePeopleVerifiedCount != 0)
			{
				str += '		<div class="row-fluid">';
				str += '			<div class="span12 booths-Overview-widget">';
				str += '				<div class="row-fluid">';
				str += '				<div class="row-fluid">';
				str += '				<h5 class="text-center label"> DATA VERIFICATION </h5>';
				str += '				</div>';
				str += '				<ul class="inline unstyled booths-Overview-widget-nav">';
				str += '					<li>';
				str += '					<hgroup>';
				str += '							<h4>CASTE</h4>';
				str += '							<h2>'+result.casteVerifiedCount+'</h2>';
				str += '							<h5>Completed</h5>';
				str += '						</hgroup>';
				str += '					</li>';
				str += '					<li>';
				str += '						<hgroup>';
				str += '							<h4>HAMLET</h4>';
				str += '							<h2>'+result.hamletVerifiedCount+'</h2>';
				str += '							<h5>Mapped</h5>';
				str += '						</hgroup>';
				str += '				</li>';
				str += '					<li>';
				str += '						<hgroup>';
				str += '						<h4>LOCAL AREA</h4>';
				str += '							<h2>'+result.localAreaCount+'</h2>';
				str += '							<h5>Identified</h5>';
				str += '						</hgroup>';
				str += '					</li>';
				str += '					<li>';
				str += '						<hgroup>';
				str += '							<h4>CADRE</h4>';
				str += '							<h2>'+result.cadreVerifiedCount+'</h2>';
				str += '							<h5>Identified</h5>';
				str += '						</hgroup>';
				str += '					</li>';
				str += '					<li>';
				str += '						<hgroup>';
				str += '							<h4 style="font-size:14px;">INFLUENCE PEOPLE</h4>';
				str += '							<h2>'+result.influencePeopleVerifiedCount+'</h2>';
				str += '							<h5>Identified</h5>';
				str += '						</hgroup>';
				str += '					</li>';
				str += '				</ul>';
				str += '			</div>';
				str += '			</div>';
				str += '		</div> ';
			}
			*/
			
			if( result.casteTPVerifiedCount !=0 || result.hamletTPVerifiedCount != 0 || result.localAreaTPCount != 0 || result.cadreTPVerifiedCount != 0 || result.influencePeopleTPVerifiedCount != 0)
			{
				str += '		<div class="row-fluid">';
				str += '			<div class="span12 booths-Overview-widget">';
				str += '				<div class="row-fluid">';
				str += '				<div class="row-fluid">';
				str += '				<h5 class="text-center label"> THIRD PARTY VERIFICATION </h5>';
				str += '				</div>';
				str += '				<ul class="inline unstyled booths-Overview-widget-nav">';
				str += '					<li>';
				str += '					<hgroup>';
				str += '							<h4>CASTE</h4>';
				str += '							<h2>'+result.casteTPVerifiedCount+'</h2>';
				str += '							<h5>Completed</h5>';
				str += '						</hgroup>';
				str += '					</li>';
				str += '					<li>';
				str += '						<hgroup>';
				str += '							<h4>HAMLET</h4>';
				str += '							<h2>'+result.hamletTPVerifiedCount+'</h2>';
				str += '							<h5>Mapped</h5>';
				str += '						</hgroup>';
				str += '				</li>';
				str += '					<li>';
				str += '						<hgroup>';
				str += '						<h4>LOCAL AREA</h4>';
				str += '							<h2>'+result.localAreaTPCount+'</h2>';
				str += '							<h5>Identified</h5>';
				str += '						</hgroup>';
				str += '					</li>';
				str += '					<li>';
				str += '						<hgroup>';
				str += '							<h4>CADRE</h4>';
				str += '							<h2>'+result.cadreTPVerifiedCount+'</h2>';
				str += '							<h5>Identified</h5>';
				str += '						</hgroup>';
				str += '					</li>';
				str += '					<li>';
				str += '						<hgroup>';
				str += '							<h4 style="font-size:14px;">INFLUENCE PEOPLE</h4>';
				str += '							<h2>'+result.influencePeopleTPVerifiedCount+'</h2>';
				str += '							<h5>Identified</h5>';
				str += '						</hgroup>';
				str += '					</li>';
				str += '				</ul>';
				str += '			</div>';
				str += '			</div>';
				str += '		</div> ';
			}
			
			
				$('#'+divId+'').html(str);
	});
}

var myResult = null;
function getTPTotalBoothsDetails(){
	$('#dayWiseReportDiv1').html('');
	var constituencyId = $('#constituencyForThirdParty').val();
	if(constituencyId == 0)	{
	 $("#errorDivForVerification").html("<font color='#FF0000'>Please Select Constituency</font>");
	 return;
	}
	 $("#errorDivForVerification").html("");
	$('#mainajaximg').show();
	var jsObj = {
		constituencyId : 217
	}
	$.ajax({
			type:'GET',
			url: 'getBoothDetailsWithTPAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)},
		 }).done(function(result){	
			if(result != null){
				myResult= result;
				buildBoothsSummary(result);
			}
			
			//buildFinalReportWithTP(result)
		});	
}



function buildFinalReportWithTP(result){
	$("#thirdPartyAjax").show();
	$("#FinalReportWithTPId").html("");
	var str = "";
	str +="<table id='FinalReportWithTPTableId' class='table table-bordered table-striped'>";
		str +="<thead class='alert alert-success'>";
			str +="<tr>";
			str +="<th>BOOTH</th>";
			str +="<th>TOTAL VOTERS</th>";
			str +="<th>THIRD PARTY COLLECTED </th>";
			var stList = result[0].statusList;
			for(var i in stList){
				str +="<th>"+stList[i].statusName+"</th>";
				str +="<th>"+stList[i].statusName+" % </th>";
			}
			str +="<th>BOOTH TYPE </th>";
			str +="<th>REVIEW</th>";
			str +="</tr>";
		str +="</thead>";
		
		for(var i in result){
				for(var k in result[i].users.usersList){
					str +="<tr>";
					str +="<td>"+result[i].partNo+"</td>";
					str +="<td>"+result[i].totalVoters+"</td>";
					str +="<td>"+result[i].users.usersList[k].userCollected+"</td>";
					var sttsList = result[i].users.usersList[k].statusList;
					for(var p in sttsList){
						str +="<td>"+sttsList[p].statusCount+"</td>";
						str +="<td>"+sttsList[p].statusPercentage+" </td>";
					}
					str +="<td>"+result[i].boothType+"</td>";
					str +="<td><a style='cursor: pointer;' onCLick='getWmUpdatedDetails("+result[i].boothId+","+result[i].partNo+")'> REVIEW</a></td>";
					str +="</tr>";
				}
		}
			
		str +="<tbody>";
	str +="</table>";
	
	$("#FinalReportWithTPId").html(str);
	$('#FinalReportWithTPTableId').dataTable();
	
	$("#thirdPartyAjax").hide();
}

function buildBoothsSummary(result){
	$("#summary").html("");
	var str = "";
	str +="<table id='FinalReportWithTPTableId' class='table table-bordered table-striped'>";
		str +="<thead class='alert alert-success'>";
			str +="<tr>";
			str +="<th>BOOTHS TYPE</th>";
			str +="<th>TOTAL VOTERS</th>";
			str +="<th>THIRD PARTY COLLECTED </th>";
			var stList = result.statusList;
			for(var i in stList){
				str +="<th>"+stList[i].statusName+"</th>";
				str +="<th>"+stList[i].statusName+" % </th>";
			}
			str +="</tr>";
		str +="</thead>";
		str +="<tbody>";
		for(var i in result.boothTypeSummaryList){
			str +="<tr>";
				str +="<td onclick='getMeBoothsUnder(\""+result.boothTypeSummaryList[i].boothType+"\");'>"+result.boothTypeSummaryList[i].boothType+"</td>";
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
					str +="<td>"+stList[j].statusCount+"</td>";
					str +="<td>"+stList[j].statusPercentage+"</td>";
				}
			str +="</tr>";
		}
		str +="</tbody>";
	str +="</table>";
	
	$("#summary").html(str);
}

function getMeBoothsUnder(bthType){
	$("#thirdPartyAjax").show();
	$("#boothsSummary").html("");
	var str = "";
	if(myResult==null){
		return;
	}
	for(var i in myResult.boothTypeSummaryList){
		if(myResult.boothTypeSummaryList[i].boothType == bthType){
			var reslt = myResult.boothTypeSummaryList[i];
			if(reslt.finalList == null){
				return;
			}						
		}
		}
	str +="<table id='FinalReportWithTPTableId' class='table table-bordered table-striped'>";
		str +="<thead class='alert alert-success'>";
			str +="<tr>";
			str +="<th>BOOTH</th>";
			str +="<th>TOTAL VOTERS</th>";
			str +="<th>THIRD PARTY COLLECTED </th>";
			var stList = myResult.statusList;
			for(var i in stList){
				str +="<th>"+stList[i].statusName+"</th>";
				str +="<th>"+stList[i].statusName+" % </th>";
			}
			str +="<th>REVIEW</th>";
			str +="</tr>";
		str +="</thead>";
		
		for(var i in myResult.boothTypeSummaryList){
				if(myResult.boothTypeSummaryList[i].boothType == bthType){
					var reslt = myResult.boothTypeSummaryList[i];
					for(var j in reslt.finalList){
						for(var k in reslt.finalList[j].users.usersList){
							str +="<tr>";
							str +="<td>"+ reslt.finalList[j].partNo+"</td>";
							str +="<td>"+reslt.finalList[j].totalVoters+"</td>";
							str +="<td>"+reslt.finalList[j].users.usersList[k].userCollected+"</td>";
							var sttsList = reslt.finalList[j].users.usersList[k].statusList;
							for(var p in sttsList){
								str +="<td>"+sttsList[p].statusCount+"</td>";
								str +="<td>"+sttsList[p].statusPercentage+" </td>";
							}
							str +="<td><a style='cursor: pointer;' onCLick='getWmUpdatedDetails("+reslt.finalList[j].boothId+","+reslt.finalList[j].partNo+")'> REVIEW</a></td>";
							str +="</tr>";
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

function buildCommentedDetails(result,partNo)
{
	var str = '';
	
	str += '<table  class="table table-bordered table-striped">';
	str += '<thead class="alert alert-success">';
	str += '<tr>';
	str += '<th>BOOTH NO</th>';
	str += '<th>VOTER NAME</th>';
	str += '<th>WM CASTE</th>';
	str += '<th>TP CASTE</th>';
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
		str += '<td>'+result[i].percent+'</td>';
		str += '</tr>';
	}
	str += '</tbody>';
	str += '</table>';
	$('#CommentsDiv').html(str);
}

</script>
	
  </body>

 </html>