<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BOOTH WISE RESULTS</title>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
 <script type="text/javascript" src="js/googleAnalytics/googleChartsColourPicker.js"></script>
<script type="text/javascript"> 
  google.load("visualization", "1", {packages:["corechart"]});
</script>
<script type="text/javascript" src="js/commonVoterDetails.js"></script>

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
<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.js"></script>
<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.css" />
<script src="js/maps/googleMap.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 


<style type="text/css">
			.ui-icon-closethick
			{
				margin-left:-8px !important;
				margin-top:5px !important;
			}
			.checkbox
			{
			float:none !important;
			width: auto !important;
			}
		
			body{background:#f0f0f0;}
			.m_top20{margin-top:20px;}
			
			.widgetservey{background:#fafafa; display:block; border:1px solid #cccccc; width:100%; padding:0px 20px 20px 20px;}
			.widgetservey_Red{background:#fafafa; display:block; border:1px solid #cccccc; width:100%; padding:0px 20px 20px 20px; border-top:3px solid #ff0000;}
			.widgetservey h4{font-size:26px; color:#333; line-height:30px; border-bottom: 1px solid #cccccc;text-align:center; text-transform:uppercase; ;}
			.widgetservey_Red h4{font-size:26px; color:#ff0000; line-height:30px; border-bottom: 1px solid #cccccc;text-align:center; text-transform:uppercase; }
			
			
			.requiredFont{
				color:red;
				font-size:13px;
			}
			
			#boothWiseResultsPage  thead th , #majarityCandidatesTab thead th ,#majarityCandidatesTab thead th, #majarityCandidatesTab  thead th, #contiInfoTab thead th, #boothsForPartyTab thead th{
				background-color: #dff0d8  !important;
				color : #468847 !important;
				line-height: 15px !important;
			}
		 
		 #boothWiseResultsPage_paginate, #boothWiseResultsPage_info {
			font-size:15px !important;
		 }
		 .dataTables_length, .dataTables_filter , .dataTables_info {
			color : #666666 !important;
		 }
		</style>
		
		<script>
		var constituencyListArr = new Array();
		<c:forEach items="${constituencyList}" var="constituency"> 
           constituencyListArr.push('${constituency.id}');
        </c:forEach>

		</script>
</head>
		  
<body>
<div class="container">
			<div class="row" id="boothWise">
			<div class="span12">
			
				
				<div class="row-fluid ">
				
						
					<div class="span12 widgetservey_Red m_top20">
							<h4> Booth Wise Election Results
								<span id="totalState" class="pull-right btn-group   pull-right">
									<a  onClick="getEntaieSelectionDetails(0,'entaieSelection')" id="all" class="btn btn-mini btn-success entaieSelection">ALL</a>|
									<a  onClick="getEntaieSelectionDetails(1,'APSelection')" id="ap" class="btn btn-mini APSelection ">AP</a>|
									<a  onClick="getEntaieSelectionDetails(2,'TSSelection')" id="ts" class="btn btn-mini TSSelection">TS</a>
								</span>
							</h4>	
							<img id="fieldDataSummaryimg" style="display: none;width: 70px; height: 60px;" src="./images/Loading-data.gif" alt="Processing Image"  class="offset5"></img>
							<div class="row">
									<div class="span12">
										<div class="row-fluid">
											<div class="span4 offset4 well well-small form-inline">
												<label>	Election Type : </label>
												<label class="radio"><input type="radio" onclick="getEntaieSelectionDetails(3,this.value)" checked="checked" value="Assembly" name="electionType">  Assembly</label>  
												<label class="radio"><input type="radio" onclick="getEntaieSelectionDetails(3,this.value)" value="Parliament" name="electionType">  Parliament 
											</div></label>

									</div>
								</div>
							</div>
						
						<div class="row" style="display:none;" id="assemblyConstiListDiv">
								<div class="span12">
									<div class="row-fluid">
									
										<div class="span6 offset3">
											<label>Select Parliament  
											<span style="margin-left:15px;"> : 
												<select class="input-xlarge" id="parliamentConstiId" onchange="getConstituenciesForParliament(this.value);"> 
													<option value="0"> Select Parliament </option>
												</select> 
												</span>
												<img src='images/ajaxImg2.gif' id="searchDataImg2" style="display:none;"/>	
											</label>											
										</div>
									</div>	
									
								</div>
							</div>
							
						<div class="row">
								<div class="span12">
									<div class="row-fluid">									
										<div class="span6 offset3">
											<label>Select Constituency : 
												<select class="input-xlarge" id="constituencyId" onchange="getDetailsForConstituency(this.value);">
													<option value="0"> Select Constituency </option>
												</select>	
												<img src='images/ajaxImg2.gif' id="searchDataImg1" style="display:none;"/>	
											</label>											
										</div>
									</div>	
									
								</div>
							</div>
							
							<div class="row">
								<div class="span12">
									<div class="row-fluid">
									
										<div class="span8 offset3 form-inline">
											<label>Election Years : </label>		
											<span id="electionYearsDiv"></span>
											<img src='images/ajaxImg2.gif' id="searchDataImg3" style="display:none;"/>											
										</div>
									</div>	
									
								</div>
							</div>
							<div class="row">
								<div class="span12">
									<div class="row-fluid">									
										<div class="span8 offset3 form-inline">
											<label>Party Names :		 </label>	
											<span id="partyListDiv"></span>
										
										</div>
									</div>	
									
								</div>
							</div>

						
			<img src='images/Loading-data.gif' class="offset5"  id="searchDataImg" style="width:70px;height:60px;display:none;"/>			
			<div id="majarityCandidatesDiv"></div>
			<div id="pollingPecentDiv"></div>
			<div id="pollingPecentDialoguDiv">
				<div id="boothInfoForPartyDiv"></div>
			
			</div>
			<div id="pollingVotesPercDiv"></div>
			<div id="boothWiseResultsDiv" style="dispaly:none;"></div>
			<div id="boothWiseResultsForExcelDiv" style="display:none;"></div>
				</div>
			</div>
		</div>
		
		

		
	</div>

<script>


var stateType = 0;
var electionYear = 0;
var globalelectionType = 0;
getEntaieSelectionDetails(0,'entaieSelection');
function getEntaieSelectionDetails(id,clsName)
{

if(id <3 )
{
	$('.btn-mini').removeClass('btn-success');
	$('.'+clsName+'').addClass('btn-success');	
	stateType = id;
}
$('#searchDataImg1').show();
		$('#majarityCandidatesDiv').html('');
		$('#boothWiseResultsDiv').html('');
		$('#pollingVotesPercDiv').html('');
		$('#pollingPecentDiv').html('');
var electionType = $("input:radio[name=electionType]:checked").val();

 var jsObj = 
	       {
		      stateType:stateType,
			  electionType :electionType, 
			  electionYear:electionYear,
			  constituencyId : 0,
			  task:"getStateDetails"             
	       }	
		    $.ajax({
				type : "POST",
				url : "getStatewiseDetailsAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
			$('#searchDataImg1').hide();
				if(result != null )
					buildElectionDetailsByResult(result);
				else
					alert(" No result available.");
			});
}

function buildElectionDetailsByResult(result)
{
	var electionType = $("input:radio[name=electionType]:checked").val();
	$('#electionYearsDiv').html('');
	$('#partyListDiv').html('');
	if(result.selectOptionsList != null && result.selectOptionsList.length > 0)
	{
		$('#parliamentConstiId').find('option').remove();
		$('#constituencyId').find('option').remove();			
		
		var constId = 282;
		
			if(electionType =='Assembly')
			{
				$('#assemblyConstiListDiv').hide();
				for(var i in result.selectOptionsList)
				{
					if(constituencyListArr.indexOf('\''+result.selectOptionsList[i].id+'\'') == -1)
					{
						if( i == 0 )
						{				
							constId = result.selectOptionsList[i].id;
							$('#constituencyId').append('<option value="'+result.selectOptionsList[i].id+'" selected="selected">'+result.selectOptionsList[i].name+'</option>');
						}						
						else
						{
							if(result.selectOptionsList[i].id == 282)
							{				
								constId = 282;
								$('#constituencyId').append('<option value="'+result.selectOptionsList[i].id+'" selected="selected">'+result.selectOptionsList[i].name+'</option>');
							}
							else
							{
								$('#constituencyId').append('<option value="'+result.selectOptionsList[i].id+'">'+result.selectOptionsList[i].name+'</option>');
							}							
						}							
					}
				}				
						getDetailsForConstituency(constId);
			}
			else
			{
				$('#assemblyConstiListDiv').show();
				
				for(var i in result.selectOptionsList)
				{
					if( i == 0 )
					{				
						getConstituenciesForParliament(result.selectOptionsList[i].id);
						$('#parliamentConstiId').append('<option value="'+result.selectOptionsList[i].id+'" selected="selected">'+result.selectOptionsList[i].name+'</option>');
					}
					else
					{
						$('#parliamentConstiId').append('<option value="'+result.selectOptionsList[i].id+'">'+result.selectOptionsList[i].name+'</option>');
					}
				}				
			}		
		}

}

function getConstituenciesForParliament(parliamentConstiId)
{
		$('#searchDataImg2').show();
	var jsObj = 
	       {
			  parliamentConstiId : parliamentConstiId,
			  electionYear:2005,
			  task:"getAssemblyDetailsForParliamnt"             
	       }	
		    $.ajax({
				type : "POST",
				url : "getAssemblyDetailsForParliamntAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
			$('#searchDataImg2').hide();
				$('#constituencyId').find('option').remove();
				$('#constituencyId').append('<option value="0"> Select Constituency </option>');
							
				if(result != null )
				{
					if(result.selectOptionsList != null && result.selectOptionsList.length > 0)
						{						
							for(var i in result.selectOptionsList)
							{
								if(constituencyListArr.indexOf('\''+result.selectOptionsList[i].id+'\'') == -1)
								{

									if( i == 0 )
									{				
										constId = result.selectOptionsList[i].id;									
										$('#constituencyId').append('<option value="'+result.selectOptionsList[i].id+'" selected="selected">'+result.selectOptionsList[i].name+'</option>');
									}
									else
									{
										$('#constituencyId').append('<option value="'+result.selectOptionsList[i].id+'">'+result.selectOptionsList[i].name+'</option>');
									}
								}
							}
							
							getDetailsForConstituency(constId);
						}	
				}	
				else
					alert(" No result available.");
			});		

}
function getDetailsForConstituency(constiId)
{

var electionType = $("input:radio[name=electionType]:checked").val();
$('#searchDataImg1').show();
 var jsObj = 
	       {
		      stateType:stateType,
			  electionType :electionType, 
			  electionYear:0,
			  constituencyId : constiId,
			  task:"getStateDetails"             
	       }	
		    $.ajax({
				type : "POST",
				url : "getStatewiseDetailsAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
			$('#searchDataImg1').hide();
				if(result != null )
					buildconstituencyDetailsByResult(result);
				else
					alert(" No result available.");
			});						
}

function buildconstituencyDetailsByResult(result)
{

	$('#electionYearsDiv').html('');
	$('#partyListDiv').html('');
	
	if(result.selectOptionsList1 != null && result.selectOptionsList1.length > 0)
	{
		var str = ''; 
		for(var i in result.selectOptionsList1)
		{
			if(parseInt(result.selectOptionsList1[i].name) > 2000)
			{
				if(i == 0)
				{
					str +='  <label class="radio"> <input  type="radio" id="'+result.selectOptionsList1[i].name+'RadoiId" name="PartiesName" key="'+result.selectOptionsList1[i].name+'" value="'+result.selectOptionsList1[i].id+'" checked="true" onclick="getPartyDetailsForConstituency()"> '+result.selectOptionsList1[i].name+'</label>';
				}
				else
				{
					str +=' <label class="radio"> <input  type="radio" id="'+result.selectOptionsList1[i].name+'RadoiId" name="PartiesName" key="'+result.selectOptionsList1[i].name+'" value="'+result.selectOptionsList1[i].id+'" onclick="getPartyDetailsForConstituency()">'+result.selectOptionsList1[i].name+'</label>';
				}
			}
		}
		$('#electionYearsDiv').html(str);
	}
	
	if(result.selectOptionsList2 != null && result.selectOptionsList2.length > 0)
	{
		var str = ''; 
		for(var i in result.selectOptionsList2)
		{
			str +='  <label class="checkbox"> <input type="checkbox" id="'+result.selectOptionsList2[i].name+'Id" class="PartiesNameCls" value="'+result.selectOptionsList2[i].id+'" checked="checked" onclick="getSurveyUserLoctionCount();">'+result.selectOptionsList2[i].name+'</label>';
		}
		$('#partyListDiv').html(str);
		
		getSurveyUserLoctionCount();
	}
	
}


function getPartyDetailsForConstituency()
{
	var constiteuncyId =  $('#constituencyId').val();
	var electionYear = $("input:radio[name=PartiesName]:checked").attr('key');
	$('#partyListDiv').html('');
	$('#searchDataImg3').show();
	var jsObj = 
	       {
			  constiteuncyId :constiteuncyId, 
			  electionYear:electionYear,
			  task:"getPartyDetails"             
	       }	
		    $.ajax({
				type : "POST",
				url : "getPartyDetailsForConstituencyAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
			$('#searchDataImg3').hide();
			var str = ''; 
				if(result != null )
				{				
					if(result.selectOptionsList != null && result.selectOptionsList.length >0)
					{
						for(var i in result.selectOptionsList)
						{
							str +='  <label class="checkbox"> <input type="checkbox" id="'+result.selectOptionsList[i].name+'Id" class="PartiesNameCls" value="'+result.selectOptionsList[i].id+'" checked="checked" onclick="getSurveyUserLoctionCount();">'+result.selectOptionsList[i].name+'</label>';
						}
						$('#partyListDiv').html(str)
						
						getSurveyUserLoctionCount();
					}
					else
					{
						str +=' No Data Avaialable.';
					}
				}				
				else
				{
					str +=' No Data Avaialable.';
					$('#partyListDiv').html(str);
				}
			});	
			
}

function getSurveyUserLoctionCount(){

	var constiteuncyId =  $('#constituencyId').val();
	var partyList = new Array();
	var electionYearsList = new Array();
	var electionYear = $("input:radio[name=PartiesName]:checked").attr('key');
	boothWiseResultObj = {};
	$('.PartiesNameCls').each(function(){
		if($(this).is(':checked')){
			partyList.push($(this).val());
		}
	});
	var electionType = $("input:radio[name=electionType]:checked").val();
	var searchType='assemblyWiseResults';
	if(electionType == 'Parliament')
	{
		searchType='parliamentWiseResults';
	}
	else
	{
		searchType='assemblyWiseResults';
	}
		electionYearsList.push(electionYear);
		$('#majarityCandidatesDiv').html('');
		$('#boothWiseResultsDiv').html('');
		$('#pollingVotesPercDiv').html('');
		$('#pollingPecentDiv').html('');
		$('#boothWiseResultsForExcelDiv').html('');
		
		$('#searchDataImg').show();
		$('#excelBtnId').hide();
		var jsObj = 
	       {
			  constituencyId : constiteuncyId,
			  partyList:partyList,
			  electionYearsList : electionYearsList,
			  task:"assemblyWiseResults"             
	       }	
		    $.ajax({
				type : "POST",
				url : "ajaxpartyBoothResult2Action.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
					$('#searchDataImg').hide();
				if(result != null)
				{
					buildBoothWiseResultReport(result,electionYear);
				}
				else
				{
					alert("No Result Availabale.");
				}
			});			
			
}

var mainArr = new Array();
var boothWiseDetailsArr;
function buildBoothWiseResultReport(result,electionYear)
{
	mainArr = [];
	boothWiseDetailsArr = [];
	mainArr.push(result);
	var constiName = $('#constituencyId option:selected').text();
	var str='';
	var isWonCandidateAvailable = false;
	str+='<div class="row text-center m_top20" style="margin-right:51px;" id="excelBtnId">	';
	str+='<input type="button" class="btn btn-success" onclick="generateExcel(\'boothWiseResultsForExcelDiv\');" value=" Export To Excel "/></div>';
	str +='<h4> <br>  '+constiName+' Assembly Booth Wise '+electionYear+' Election Results </h4> <br>';
	var remainingVotes = 0;
	var remainingPercentage = 0.00;
	var totalVotes = parseInt(result.partyBoothPerformanceVOList[0].totalValidVotes);
		if(result.partyBoothPerformanceVOList != null && result.partyBoothPerformanceVOList.length >0)
		{
					
			str +='<table class="table table-bordered" id="contiInfoTab" >';
			str +='<th>Total Voters </th>';
			str +='<td>'+result.partyBoothPerformanceVOList[0].totalVotes+'</td>';
			str +='<th>Total Polled Voters </th>';
			str +='<td> '+result.partyBoothPerformanceVOList[0].totalValidVotes+'</td>';
			str +='<th>Voting Percentage </th>';
			str +='<td>'+result.partyBoothPerformanceVOList[0].votingPercentage+' </td>';
			str +='</table>';

			str +='<h4> Candidates Details  </h4>';
				
			str +='<table class="table table-bordered m_top20 " id="majarityCandidatesTab">';	
			str +='<thead>';
				str +='<tr>';
					str +='<th style="width:200px;"> Candidate Name </th>';
					str +='<th> Party Name </th>';
					str +='<th> Total Votes Gained </th>';	
					str +='<th> Total Votes Gained Percentage </th>';
					str +='<th> Margin Votes </th>';
				str +='</tr>';
			str +='</thead>';	
			str +='<tbody>';
			
				for(var k in result.partyBoothPerformanceVOList)
				{
					if(result.partyBoothPerformanceVOList[k].rank ==1)
					{
						isWonCandidateAvailable = true;
						str +='<tr>';
							str +='<td style="background:#DBEAF9;width:200px;">'+result.partyBoothPerformanceVOList[k].candidateName+'</td>';
							str +='<td style="background:#DBEAF9;">'+result.partyBoothPerformanceVOList[k].partyName+'</td>';
							str +='<td  style="background:#DBEAF9;">'+result.partyBoothPerformanceVOList[k].votesGained+'</td>';						
							str +='<td  style="background:#DBEAF9;">'+result.partyBoothPerformanceVOList[k].percentage+'  </td>';
							str +='<td  style="background:#DBEAF9;">'+result.partyBoothPerformanceVOList[k].marginVotes+'  ( Rank - '+result.partyBoothPerformanceVOList[k].rank+') </td>';
						str +='</tr>';
						remainingVotes = remainingVotes + parseInt(result.partyBoothPerformanceVOList[k].votesGained);
						remainingPercentage = parseFloat(remainingPercentage) + parseFloat(result.partyBoothPerformanceVOList[k].percentage);
					}
					else
					{
						str +='<tr>';
							str +='<td  style="width:200px;">'+result.partyBoothPerformanceVOList[k].candidateName+'</td>';
							str +='<td>'+result.partyBoothPerformanceVOList[k].partyName+'</td>';
							str +='<td>'+result.partyBoothPerformanceVOList[k].votesGained+'</td>';						
							str +='<td>'+result.partyBoothPerformanceVOList[k].percentage+'  </td>';
							str +='<td>'+result.partyBoothPerformanceVOList[k].marginVotes+'  ( Rank - '+result.partyBoothPerformanceVOList[k].rank+') </td>';
						str +='</tr>';
						remainingVotes = remainingVotes + parseInt(result.partyBoothPerformanceVOList[k].votesGained);
						remainingPercentage = parseFloat(remainingPercentage) + parseFloat(result.partyBoothPerformanceVOList[k].percentage);
					}
															
				}		
				if(!isWonCandidateAvailable)
					{
						str +='<tr>';
							str +='<td style="background:#DBEAF9;width:200px;">'+result.partyBoothPerformanceVOList[0].wonCandidate[0].candidateName+'</td>';
							str +='<td style="background:#DBEAF9;">'+result.partyBoothPerformanceVOList[0].wonCandidate[0].partyName+'</td>';
							str +='<td  style="background:#DBEAF9;">'+result.partyBoothPerformanceVOList[0].wonCandidate[0].votesEarned+'</td>';						
							str +='<td  style="background:#DBEAF9;">'+result.partyBoothPerformanceVOList[0].wonCandidate[0].votesPercengate+'  </td>';
							str +='<td  style="background:#DBEAF9;">'+result.partyBoothPerformanceVOList[0].wonCandidate[0].marginVotes+' ( Rank - '+result.partyBoothPerformanceVOList[0].wonCandidate[0].rank+') </td>';
						str +='</tr>';
						remainingVotes = remainingVotes + parseInt(result.partyBoothPerformanceVOList[0].wonCandidate[0].votesEarned);
						remainingPercentage = parseFloat(remainingPercentage) + parseFloat(result.partyBoothPerformanceVOList[k].percentage);

					}
				
						str +='<tr>';
							str +='<td> Others </td>';
							str +='<td> --  </td>';
							str +='<td> '+(totalVotes - remainingVotes)+' </td>';						
							str +='<td> '+(parseFloat(100.00) - remainingPercentage).toFixed(2)+' </td>';
							str +='<td> --  </td>';
						str +='</tr>';
					
			str +='</tbody>';
			str +='</table>';
		}
	
	$('#majarityCandidatesDiv').html(str);

	str='';
	str +='<h4> Polling Percentage vs Party Votes Percentage <h4>';	
	partiesSize = 0;
	if(result.partyPerWiseboothResults != null && result.partyPerWiseboothResults.length >0)
		{
			str +='<table class="table table-bordered m_top20 " id="majarityCandidatesTab" style="font-size:12px;color:#000000;font-weight:normal;">';	
			str +='<thead>';
				str +='<tr>';				
					str +='<th > Polling % Range </th>';
					str +='<th> Total No of Booths </th>';
					if(result.partyBoothPerformanceVOList != null && result.partyBoothPerformanceVOList.length >0)
					{
					partiesSize =  result.partyBoothPerformanceVOList.length;
						for(var j in result.partyBoothPerformanceVOList)
						{
							str +='<th colspan="2" style="text-align:center"> '+result.partyBoothPerformanceVOList[j].partyName+' </th>';
						}
					}
					str +='<th colspan="2" style="text-align:center"> Others </th>';
				str +='</tr>';

				str +='<tr>';
					str +='<th></th>';
					str +='<th></th>';
						for(var p=0; p<partiesSize ; p++)
						{						
							str +='<th> Party Votes % </th>';
							str +='<th> Won Booths  </th>';					
						}	
					str +='<th> Party Votes % </th>';
					str +='<th> Won Booths  </th>';						
				str +='</tr>';
			str +='</thead>';	
			str +='<tbody>';
			
				for(var k in result.partyPerWiseboothResults)
				{
					var totalBooths = result.partyPerWiseboothResults[k].boothResultVOList[0].votesEarned;
					var remainingBooths = 0;
					var percentage = 0.00;
					
						str +='<tr>';
							str +='<td  style="text-align:center">'+result.partyPerWiseboothResults[k].location+'</td>';
							str +='<td  style="text-align:center">'+totalBooths+'</td>';
							if(result.partyPerWiseboothResults[k].boothResultVOList != null && result.partyPerWiseboothResults[k].boothResultVOList.length >0 )
							{
								for(var p = 0; p<partiesSize ; p++)
								{
									if(result.partyPerWiseboothResults[k].boothResultVOList[p].percentage != null)
									{
										str +='<td  style="text-align:center">'+result.partyPerWiseboothResults[k].boothResultVOList[p].percentage+'</td> ';
										if( (result.partyPerWiseboothResults[k].boothResultVOList[p].percentage).trim() != '--')
										{
											percentage = parseFloat(percentage) + parseFloat(result.partyPerWiseboothResults[k].boothResultVOList[p].percentage);
										}
												
									}
									else
									{
										str +='<td  style="text-align:center"> -- </td> ';
									}
									
									if(result.partyPerWiseboothResults[k].boothResultVOList[p].boothResultVOList != null && result.partyPerWiseboothResults[k].boothResultVOList[p].boothResultVOList.length >0)
									{
										var isBuild = true;
										for(var r in result.partyPerWiseboothResults[k].boothResultVOList[p].boothResultVOList)
										{
											if(result.partyPerWiseboothResults[k].boothResultVOList[p].boothResultVOList[r].wonParty == result.partyPerWiseboothResults[k].boothResultVOList[p].message)
											{
												isBuild = false;
												str+='<td  style="text-align:center"> <a href="javascript:{getCountBoothDetails(\''+result.partyPerWiseboothResults[k].location+'\',\''+result.partyPerWiseboothResults[k].boothResultVOList[p].boothResultVOList[r].wonParty+'\');}">'+result.partyPerWiseboothResults[k].boothResultVOList[p].boothResultVOList[r].resultState+'</a></td>';
													remainingBooths =  remainingBooths + parseInt(result.partyPerWiseboothResults[k].boothResultVOList[p].boothResultVOList[r].resultState);
													
													var boothObj = {
														type : result.partyPerWiseboothResults[k].location,
														partyName : result.partyPerWiseboothResults[k].boothResultVOList[p].boothResultVOList[r].wonParty,
														value : result.partyPerWiseboothResults[k].boothResultVOList[p].boothResultVOList[r].boothResultVOList
													}
													
													boothWiseDetailsArr.push(boothObj);
													
											}
										}
//88888 
										if(isBuild)
										{
												str+='<td  style="text-align:center"> -- </td>';
										}										
									}
									else
									{
										str+='<td  style="text-align:center"> -- </td>';
									}
								}
					
							}
							
									if(percentage == 0.00)
										str +='<td style="text-align:center"> -- </td>';
									else
										str +='<td style="text-align:center"> '+(parseFloat(100.00) - parseFloat(percentage)).toFixed(2)+' </td>';
									
									if(result.partyPerWiseboothResults[k].boothResultVOList[0].boothResultVOList1 != null && result.partyPerWiseboothResults[k].boothResultVOList[0].boothResultVOList1.length >0)
									{
									
										str+='<td  style="text-align:center"> <a href="javascript:{getCountBoothDetails(\''+result.partyPerWiseboothResults[k].location+'\',\'OTHERS\');}">'+result.partyPerWiseboothResults[k].boothResultVOList[0].boothResultVOList1.length+'</a></td>';
										
											var boothObj = 
												{
													type : result.partyPerWiseboothResults[k].location,
													partyName : "OTHERS",
													value : result.partyPerWiseboothResults[k].boothResultVOList[0].boothResultVOList1
												}
													
													console.log(result.partyPerWiseboothResults[k].boothResultVOList[0].boothResultVOList1[0].wonParty);
													boothWiseDetailsArr.push(boothObj);
										
									}
									else
										str+='<td  style="text-align:center"> -- </td>';
						str +='</tr>';						
				}
			
			
			str +='</tbody>';
			str +='</table>';
		}

		$('#pollingPecentDiv').html(str);

		str='';

		str +='<h4> Party Votes Percentage vs Polling Percentage <h4>';
	var partiesSize = 0;
	if(result.perWiseboothResults != null && result.perWiseboothResults.length >0)
		{

			str +='<table class="table table-bordered m_top20 " id="majarityCandidatesTab" style="font-size:12px;color:#000000;font-weight:normal;">';	
			str +='<thead>';
				str +='<tr>';				
					str +='<th > Party Votes % Range </th>';
					if(result.partyBoothPerformanceVOList != null && result.partyBoothPerformanceVOList.length >0)
					{
						partiesSize =  result.partyBoothPerformanceVOList.length;
						for(var j in result.partyBoothPerformanceVOList)
						{
							str +='<th colspan="2" style="text-align:center"> '+result.partyBoothPerformanceVOList[j].partyName+' </th>';
						}
					}
					
				str +='</tr>';

				str +='<tr>';
				str +='<th></th>';
					for(var p=0; p<partiesSize ; p++)
					{
						str +='<th> Total No of Booths </th>';
						str +='<th> Polling % </th>';	
					}								
				str +='</tr>';
			str +='</thead>';	
			str +='<tbody>';
			
				for(var k in result.perWiseboothResults)
				{
						str +='<tr>';
							str +='<td  style="text-align:center">'+result.perWiseboothResults[k].location+'</td>';
							
							if(result.perWiseboothResults[k].boothResultVOList != null && result.perWiseboothResults[k].boothResultVOList.length >0 )
							{
								for(var p=0; p<partiesSize ; p++)
								{
									if(result.perWiseboothResults[k].boothResultVOList[p].votesEarned != null)
										str +='<td  style="text-align:center">'+result.perWiseboothResults[k].boothResultVOList[p].votesEarned+'</td>';
									else
										str+='<td  style="text-align:center"> -- </td>';
									if(result.perWiseboothResults[k].boothResultVOList[p].percentage != null)
										str +='<td  style="text-align:center">'+result.perWiseboothResults[k].boothResultVOList[p].percentage+' </td>';
									else
										str+='<td  style="text-align:center"> -- </td>';
								}
					
							}
							
						str +='</tr>';						
				}
			
			
			str +='</tbody>';
			str +='</table>';
		}
		
		var str1='';
		str +='<h4> Booth Wise Performance :  <h4>';
		if(result.boothResults != null && result.boothResults.length >0)
		{
			str+='<div class="row" style="font-size : 15px;color:#000000;">';
			str+='	<div class="span12">';
			str+='		<div class="row-fluid">						';			
			str+='			<div class="span8 form-inline"> <label style="font-weight:bold;"> Select Options : </label>';
			
			str +='  <label class="checkbox"> <input type="checkbox"  id="locationId" onclick="hideAndShow();"> Show Location </label>';
			str +='  <label class="checkbox"> <input type="checkbox" id="villageId" onclick="hideAndShow();"> Show Villages </label>';
			str +='  <label class="checkbox"> <input type="checkbox"  id="mandalId" onclick="hideAndShow();"> Show Mandal </label>';			
			
			str+='			</div>';
			str+='		</div>						';
			str+='	</div>';
			str+='</div>';
							
			str1 +='<table class="table table-bordered m_top20 " id="boothWiseResultsPage" style="font-size:12px;color:#000000;font-weight:normal;">';	
			str1 +='<thead>';
				str1 +='<tr>';				
					str1 +='<th> S.No  </th>';
					str1 +='<th> Booth No  </th>';
					str1 +='<th> Total Votes  </th>';
					//str1 +='<th> Location  </th>';
					//str1 +='<th> Village Covered  </th>';
					//str1 +='<th> Mandal </th>';
					str1 +='<th >   Polled Votes </th>';	
					str1 +='<th > Polling %   </th>';
					if(result.partyBoothPerformanceVOList != null && result.partyBoothPerformanceVOList.length >0)
					{
						partiesSize =  result.partyBoothPerformanceVOList.length;
						for(var j in result.partyBoothPerformanceVOList)
						{
							str1 +='<th> '+result.partyBoothPerformanceVOList[j].partyName+' Polled Votes </th>';
							str1 +='<th> '+result.partyBoothPerformanceVOList[j].partyName+' Polling % </th>';
						}
					}
					str1 +='<th >  Others Polled Votes </th>';	
					str1 +='<th > Others Polling %   </th>';
					//str1 +='<th > Won Party   </th>';
									
				str1 +='</tr>';

				str1 +='</thead>';	
			str1 +='<tbody>';	
			for(var k in result.boothResults)
			{
				var votersCount = result.boothResults[k].boothResultVOList[0].totalVoters;
				var percentage = 0.00;
					str1 +='<tr>';
					str1 +='<td>'+(parseInt(k)+parseInt(1))+'</td>';
					str1 +='<td> Booth-'+result.boothResults[k].partNo+'</td>';
						if(result.boothResults[k].boothResultVOList != null && result.boothResults[k].boothResultVOList.length>0)
						{
							
							
							//str1 +='<td>'+result.boothResults[k].boothResultVOList[0].location+'</td>';
							//str1 +='<td>'+result.boothResults[k].boothResultVOList[0].villagesCovered+'</td>';
							//str1 +='<td>'+result.boothResults[k].boothResultVOList[0].mandal+'</td>';
							str1 +='<td>'+result.boothResults[k].boothResultVOList[0].totalBoothVoters+'</td>';
							str1 +='<td>'+result.boothResults[k].boothResultVOList[0].totalVoters+'</td>';
							str1 +='<td>'+result.boothResults[k].boothResultVOList[0].pollingPercentage+'</td>';
							
							for(var m in result.boothResults[k].boothResultVOList)
							{
								votersCount = parseInt(votersCount) - parseInt(result.boothResults[k].boothResultVOList[m].votesEarned);
								percentage = parseFloat(percentage) + parseFloat(parseInt(result.boothResults[k].boothResultVOList[m].percentage));
								str1 +='<td>'+result.boothResults[k].boothResultVOList[m].votesEarned+'</td>';
								str1 +='<td>'+result.boothResults[k].boothResultVOList[m].percentage+'</td>';
							}
						}
						
						str1 +='<td>'+votersCount+'</td>';
						str1 +='<td>'+(parseFloat(100.00) - parseFloat(percentage) ).toFixed(2)+'</td>';
							//str1 +='<td>'+result.boothResults[k].boothResultVOList[m].wonParty+'</td>';	
					str1 +='</tr>';						
			}
			
			str1 +='</tbody>';
			str1 +='</table>';
		}
		
		$('#pollingVotesPercDiv').html(str);
		$('#boothWiseResultsDiv').html(str1);
		$('#boothWiseResultsPage').dataTable({
		"iDisplayLength": 50,
		"aLengthMenu": [[50, 100, 200, -1], [50, 100, 200, "All"]]
		});
		$('#excelBtnId').show();

}

function hideAndShow()
{
	var isVillage = $('#villageId').is(":checked");
	var isMandal = $('#mandalId').is(":checked");
	var isLocation = $('#locationId').is(":checked");
	$('#boothWiseResultsDiv').html('');
	buildSelectionWiseTable(isVillage,isMandal,isLocation);

}

function buildSelectionWiseTable(isVillage,isMandal,isLocation)
{

var result = mainArr[0];
	var str1 = '';
	
			str1 +='<table class="table table-bordered m_top20 " id="boothWiseResultsPage" style="font-size:12px;color:#000000;font-weight:normal;">';	
			str1 +='<thead>';
				str1 +='<tr>';				
					str1 +='<th> S.No  </th>';
					str1 +='<th> Booth No  </th>';
					if(isLocation)
						str1 +='<th> Location  </th>';
					if(isVillage)
						str1 +='<th> Village Covered  </th>';
					if(isMandal)
						str1 +='<th> Mandal </th>';
					str1 +='<th >   Total Votes </th>';	
					str1 +='<th >   Polled Votes </th>';	
					str1 +='<th > Polling %   </th>';
					if(result.partyBoothPerformanceVOList != null && result.partyBoothPerformanceVOList.length >0)
					{
						partiesSize =  result.partyBoothPerformanceVOList.length;
						for(var j in result.partyBoothPerformanceVOList)
						{
							str1 +='<th> '+result.partyBoothPerformanceVOList[j].partyName+' Polled Votes </th>';
							str1 +='<th> '+result.partyBoothPerformanceVOList[j].partyName+' Polling % </th>';
						}
					}
					str1 +='<th >  Others Polled Votes </th>';	
					str1 +='<th > Others Polling %   </th>';
					//str1 +='<th > Won Party   </th>';
									
				str1 +='</tr>';
	
			str1 +='</thead>';	
			str1 +='<tbody>';	
			for(var k in result.boothResults)
			{
				var votersCount = result.boothResults[k].boothResultVOList[0].totalVoters;
				var percentage = 0.00;
					str1 +='<tr>';
					str1 +='<td>'+(parseInt(k)+parseInt(1))+'</td>';
					str1 +='<td> Booth-'+result.boothResults[k].partNo+'</td>';
						if(result.boothResults[k].boothResultVOList != null && result.boothResults[k].boothResultVOList.length>0)
						{
							if(isLocation)
								str1 +='<td>'+result.boothResults[k].boothResultVOList[0].location+'</td>';
							if(isVillage)
								str1 +='<td>'+result.boothResults[k].boothResultVOList[0].villagesCovered+'</td>';
							if(isMandal)
								str1 +='<td>'+result.boothResults[k].boothResultVOList[0].mandal+'</td>';
							str1 +='<td>'+result.boothResults[k].boothResultVOList[0].totalBoothVoters+'</td>';
							str1 +='<td>'+result.boothResults[k].boothResultVOList[0].totalVoters+'</td>';
							str1 +='<td>'+result.boothResults[k].boothResultVOList[0].pollingPercentage+'</td>';
							
							for(var m in result.boothResults[k].boothResultVOList)
							{
								votersCount = parseInt(votersCount) - parseInt(result.boothResults[k].boothResultVOList[m].votesEarned);
								percentage = parseFloat(percentage) + parseFloat(parseInt(result.boothResults[k].boothResultVOList[m].percentage));
								str1 +='<td>'+result.boothResults[k].boothResultVOList[m].votesEarned+'</td>';
								str1 +='<td>'+result.boothResults[k].boothResultVOList[m].percentage+'</td>';
							}
						}
						
						str1 +='<td>'+votersCount+'</td>';
						str1 +='<td>'+(parseFloat(100.00) - parseFloat(percentage) ).toFixed(2)+'</td>';
								//str1 +='<td>'+result.boothResults[k].boothResultVOList[m].wonParty+'</td>';	
					str1 +='</tr>';						
			}
			
			str1 +='</tbody>';
			str1 +='</table>';
		

		$('#boothWiseResultsDiv').html(str1);
		$('#boothWiseResultsPage').dataTable({
		"iDisplayLength": 50,
		"aLengthMenu": [[50, 100, 200, -1], [50, 100, 200, "All"]]
		});
}

function generateExcel(id)
{
$('#boothWiseResultsForExcelDiv').html('');
var constiName = $('#constituencyId option:selected').text();
	var str='';
	var isWonCandidateAvailable = false;
	var isVillage = $('#villageId').is(":checked");
	var isMandal = $('#mandalId').is(":checked");
	var isLocation = $('#locationId').is(":checked");
	
var result = mainArr[0];
	str +='<h4> <br>  '+constiName+' Assembly Booth Wise '+electionYear+' Election Results </h4> <br>';
	var remainingVotes = 0;
	var remainingPercentage = 0.00;
	var totalVotes = parseInt(result.partyBoothPerformanceVOList[0].totalValidVotes);
		if(result.partyBoothPerformanceVOList != null && result.partyBoothPerformanceVOList.length >0)
		{
					
			str +='<table class="table table-bordered" id="contiInfoTab" >';
			str +='<th>Total Voters </th>';
			str +='<td>'+result.partyBoothPerformanceVOList[0].totalVotes+'</td>';
			str +='<th>Total Polled Voters </th>';
			str +='<td> '+result.partyBoothPerformanceVOList[0].totalValidVotes+'</td>';
			str +='<th>Voting Percentage </th>';
			str +='<td>'+result.partyBoothPerformanceVOList[0].votingPercentage+' </td>';
			str +='</table>';

			str +='<h4> Candidates Details  </h4>';
				
			str +='<table class="table table-bordered m_top20 " id="majarityCandidatesTab">';	
			str +='<thead>';
				str +='<tr>';
					str +='<th  style="width:200px;"> Candidate Name </th>';
					str +='<th> Party Name </th>';
					str +='<th> Total Votes Gained </th>';	
					str +='<th> Total Votes Gained Percentage </th>';
					str +='<th> Margin Votes </th>';
				str +='</tr>';
			str +='</thead>';	
			str +='<tbody>';
			
				for(var k in result.partyBoothPerformanceVOList)
				{
					if(result.partyBoothPerformanceVOList[k].rank ==1)
					{
						isWonCandidateAvailable = true;
						str +='<tr>';
							str +='<td style="background:#DBEAF9;width:200px;">'+result.partyBoothPerformanceVOList[k].candidateName+'</td>';
							str +='<td style="background:#DBEAF9;">'+result.partyBoothPerformanceVOList[k].partyName+'</td>';
							str +='<td  style="background:#DBEAF9;">'+result.partyBoothPerformanceVOList[k].votesGained+'</td>';						
							str +='<td  style="background:#DBEAF9;">'+result.partyBoothPerformanceVOList[k].percentage+'  </td>';
							str +='<td  style="background:#DBEAF9;">'+result.partyBoothPerformanceVOList[k].marginVotes+'  ( Rank - '+result.partyBoothPerformanceVOList[k].rank+') </td>';
						str +='</tr>';
						remainingVotes = remainingVotes + parseInt(result.partyBoothPerformanceVOList[k].votesGained);
						remainingPercentage = parseFloat(remainingPercentage) + parseFloat(result.partyBoothPerformanceVOList[k].percentage);

					}
					else
					{
						str +='<tr>';
							str +='<td  style="width:200px;">'+result.partyBoothPerformanceVOList[k].candidateName+'</td>';
							str +='<td>'+result.partyBoothPerformanceVOList[k].partyName+'</td>';
							str +='<td>'+result.partyBoothPerformanceVOList[k].votesGained+'</td>';						
							str +='<td>'+result.partyBoothPerformanceVOList[k].percentage+'  </td>';
							str +='<td>'+result.partyBoothPerformanceVOList[k].marginVotes+'  ( Rank - '+result.partyBoothPerformanceVOList[k].rank+') </td>';
						str +='</tr>';
						remainingVotes = remainingVotes + parseInt(result.partyBoothPerformanceVOList[k].votesGained);
						remainingPercentage = parseFloat(remainingPercentage) + parseFloat(result.partyBoothPerformanceVOList[k].percentage);
					}
					

				}		
				if(!isWonCandidateAvailable)
					{
						str +='<tr>';
							str +='<td style="background:#DBEAF9;width:200px;">'+result.partyBoothPerformanceVOList[0].wonCandidate[0].candidateName+'</td>';
							str +='<td style="background:#DBEAF9;">'+result.partyBoothPerformanceVOList[0].wonCandidate[0].partyName+'</td>';
							str +='<td  style="background:#DBEAF9;">'+result.partyBoothPerformanceVOList[0].wonCandidate[0].votesEarned+'</td>';						
							str +='<td  style="background:#DBEAF9;">'+result.partyBoothPerformanceVOList[0].wonCandidate[0].votesPercengate+'  </td>';
							str +='<td  style="background:#DBEAF9;">'+result.partyBoothPerformanceVOList[0].wonCandidate[0].marginVotes+' ( Rank - '+result.partyBoothPerformanceVOList[0].wonCandidate[0].rank+') </td>';
						str +='</tr>';
						remainingVotes = remainingVotes + parseInt(result.partyBoothPerformanceVOList[0].wonCandidate[0].votesEarned);
						remainingPercentage = parseFloat(remainingPercentage) + parseFloat(result.partyBoothPerformanceVOList[k].percentage);
					}
					
					
						str +='<tr>';
							str +='<td> Others </td>';
							str +='<td> -- </td>';
							str +='<td> '+(totalVotes - remainingVotes)+' </td>';						
							str +='<td> '+(parseFloat(100.00) - remainingPercentage).toFixed(2)+' </td>';
							str +='<td style="text-align:center;"> --  </td>';
						str +='</tr>';
					
			str +='</tbody>';
			str +='</table>';
		}

	str +='<h4> Polling Percentage vs Party Votes Percentage <h4>';	
	partiesSize = 0;
	if(result.partyPerWiseboothResults != null && result.partyPerWiseboothResults.length >0)
		{
			str +='<table class="table table-bordered m_top20 " id="majarityCandidatesTab" style="font-size:12px;color:#000000;font-weight:normal;">';	
			str +='<thead>';
				str +='<tr>';				
					str +='<th > Polling % Range </th>';
					str +='<th> Total No of Booths </th>';
					if(result.partyBoothPerformanceVOList != null && result.partyBoothPerformanceVOList.length >0)
					{
					partiesSize =  result.partyBoothPerformanceVOList.length;
						for(var j in result.partyBoothPerformanceVOList)
						{
							str +='<th colspan="2" style="text-align:center"> '+result.partyBoothPerformanceVOList[j].partyName+' </th>';
						}
					}
					str +='<th colspan="2" style="text-align:center"> Others </th>';
				str +='</tr>';

				str +='<tr>';
					str +='<th></th>';
					str +='<th></th>';
						for(var p=0; p<partiesSize ; p++)
						{						
							str +='<th> Party Votes % </th>';
							str +='<th> Won Booths  </th>';					
						}	
					str +='<th> Party Votes % </th>';
					str +='<th> Won Booths  </th>';						
				str +='</tr>';
			str +='</thead>';	
			str +='<tbody>';
			
				for(var k in result.partyPerWiseboothResults)
				{
					var totalBooths = result.partyPerWiseboothResults[k].boothResultVOList[0].votesEarned;
					var remainingBooths = 0;
					var percentage = 0.00;
					
						str +='<tr>';
							str +='<td  style="text-align:center">'+result.partyPerWiseboothResults[k].location+'</td>';
							str +='<td  style="text-align:center">'+totalBooths+'</td>';
							if(result.partyPerWiseboothResults[k].boothResultVOList != null && result.partyPerWiseboothResults[k].boothResultVOList.length >0 )
							{
								for(var p = 0; p<partiesSize ; p++)
								{
									if(result.partyPerWiseboothResults[k].boothResultVOList[p].percentage != null)
									{
										str +='<td  style="text-align:center">'+result.partyPerWiseboothResults[k].boothResultVOList[p].percentage+'</td> ';
										if( (result.partyPerWiseboothResults[k].boothResultVOList[p].percentage).trim() != '--')
										{
											percentage = parseFloat(percentage) + parseFloat(result.partyPerWiseboothResults[k].boothResultVOList[p].percentage);
										}
												
									}
									else
									{
										str +='<td  style="text-align:center"> -- </td> ';
									}
									
									if(result.partyPerWiseboothResults[k].boothResultVOList[p].boothResultVOList != null && result.partyPerWiseboothResults[k].boothResultVOList[p].boothResultVOList.length >0)
									{
										var isBuild = true;
										for(var r in result.partyPerWiseboothResults[k].boothResultVOList[p].boothResultVOList)
										{
											if(result.partyPerWiseboothResults[k].boothResultVOList[p].boothResultVOList[r].wonParty == result.partyPerWiseboothResults[k].boothResultVOList[p].message)
											{
												isBuild = false;
												str+='<td  style="text-align:center"> <a href="javascript:{getCountBoothDetails(\''+result.partyPerWiseboothResults[k].location+'\',\''+result.partyPerWiseboothResults[k].boothResultVOList[p].boothResultVOList[r].wonParty+'\');}">'+result.partyPerWiseboothResults[k].boothResultVOList[p].boothResultVOList[r].resultState+'</a></td>';
													remainingBooths =  remainingBooths + parseInt(result.partyPerWiseboothResults[k].boothResultVOList[p].boothResultVOList[r].resultState);
													
													var boothObj = {
														type : result.partyPerWiseboothResults[k].location,
														partyName : result.partyPerWiseboothResults[k].boothResultVOList[p].boothResultVOList[r].wonParty,
														value : result.partyPerWiseboothResults[k].boothResultVOList[p].boothResultVOList[r].boothResultVOList
													}
													
													boothWiseDetailsArr.push(boothObj);
													
											}
										}
//88888 
										if(isBuild)
										{
												str+='<td  style="text-align:center"> -- </td>';
										}										
									}
									else
									{
										str+='<td  style="text-align:center"> -- </td>';
									}
								}
					
							}
							
									if(percentage == 0.00)
										str +='<td style="text-align:center"> -- </td>';
									else
										str +='<td style="text-align:center"> '+(parseFloat(100.00) - parseFloat(percentage)).toFixed(2)+' </td>';
									
									if(result.partyPerWiseboothResults[k].boothResultVOList[0].boothResultVOList1 != null && result.partyPerWiseboothResults[k].boothResultVOList[0].boothResultVOList1.length >0)
									{
									
										str+='<td  style="text-align:center"> <a href="javascript:{getCountBoothDetails(\''+result.partyPerWiseboothResults[k].location+'\',\'OTHERS\');}">'+result.partyPerWiseboothResults[k].boothResultVOList[0].boothResultVOList1.length+'</a></td>';
										
											var boothObj = 
												{
													type : result.partyPerWiseboothResults[k].location,
													partyName : "OTHERS",
													value : result.partyPerWiseboothResults[k].boothResultVOList[0].boothResultVOList1
												}
													
													console.log(result.partyPerWiseboothResults[k].boothResultVOList[0].boothResultVOList1[0].wonParty);
													boothWiseDetailsArr.push(boothObj);
										
									}
									else
										str+='<td  style="text-align:center"> -- </td>';
								
							
						str +='</tr>';						
				}
			
			
			str +='</tbody>';
			str +='</table>';
		}
		
		str +='<h4> Party Votes Percentage vs Polling Percentage <h4>';
	var partiesSize = 0;
	if(result.perWiseboothResults != null && result.perWiseboothResults.length >0)
		{

			str +='<table class="table table-bordered m_top20 " id="majarityCandidatesTab" style="font-size:12px;color:#000000;font-weight:normal;">';	
			str +='<thead>';
				str +='<tr>';				
					str +='<th > Party Votes % Range </th>';
					if(result.partyBoothPerformanceVOList != null && result.partyBoothPerformanceVOList.length >0)
					{
						partiesSize =  result.partyBoothPerformanceVOList.length;
						for(var j in result.partyBoothPerformanceVOList)
						{
							str +='<th colspan="2" style="text-align:center"> '+result.partyBoothPerformanceVOList[j].partyName+' </th>';
						}
					}
					
				str +='</tr>';

				str +='<tr>';
				str +='<th></th>';
					for(var p=0; p<partiesSize ; p++)
					{
						str +='<th> Total No of Booths </th>';
						str +='<th> Polling % </th>';	
					}								
				str +='</tr>';
			str +='</thead>';	
			str +='<tbody>';
			
				for(var k in result.perWiseboothResults)
				{
						str +='<tr>';
							str +='<td  style="text-align:center">'+result.perWiseboothResults[k].location+'</td>';
							
							if(result.perWiseboothResults[k].boothResultVOList != null && result.perWiseboothResults[k].boothResultVOList.length >0 )
							{
								for(var p=0; p<partiesSize ; p++)
								{
									if(result.perWiseboothResults[k].boothResultVOList[p].votesEarned != null)
										str +='<td  style="text-align:center">'+result.perWiseboothResults[k].boothResultVOList[p].votesEarned+'</td>';
									else
										str+='<td  style="text-align:center"> -- </td>';
									if(result.perWiseboothResults[k].boothResultVOList[p].percentage != null)
										str +='<td  style="text-align:center">'+result.perWiseboothResults[k].boothResultVOList[p].percentage+' </td>';
									else
										str+='<td  style="text-align:center"> -- </td>';
								}
					
							}
							
						str +='</tr>';						
				}
			
			
			str +='</tbody>';
			str +='</table>';
		}
		
		var str1='';
		str +='<h4> Booth Wise Performance :  <h4>';
		if(result.boothResults != null && result.boothResults.length >0)
		{

			str1 +='<table class="table table-bordered m_top20 " id="boothWiseResultsPage" style="font-size:12px;color:#000000;font-weight:normal;">';	
			str1 +='<thead>';
				str1 +='<tr>';				
					str1 +='<th> S.No  </th>';
					str1 +='<th> Booth No  </th>';
					if(isLocation)
						str1 +='<th> Location  </th>';
					if(isVillage)
						str1 +='<th id="villageClm" > Village Covered  </th>';
					if(isMandal)
						str1 +='<th  id="mandalClm" > Mandal </th>';
					str1 +='<th >   Total Votes </th>';	
					str1 +='<th >   Polled Votes </th>';	
					str1 +='<th > Polling %   </th>';
					if(result.partyBoothPerformanceVOList != null && result.partyBoothPerformanceVOList.length >0)
					{
						partiesSize =  result.partyBoothPerformanceVOList.length;
						for(var j in result.partyBoothPerformanceVOList)
						{
							str1 +='<th> '+result.partyBoothPerformanceVOList[j].partyName+' Polled Votes </th>';
							str1 +='<th> '+result.partyBoothPerformanceVOList[j].partyName+' Polling % </th>';
						}
					}
					str1 +='<th >  Others Polled Votes </th>';	
					str1 +='<th > Others Polling %   </th>';
					//str1 +='<th > Won Party  </th>';
									
				str1 +='</tr>';

			str1 +='</thead>';	
			str1 +='<tbody>';	
			for(var k in result.boothResults)
			{
				var votersCount = result.boothResults[k].boothResultVOList[0].totalVoters;
				var percentage = 0.00;
					str1 +='<tr>';
					str1 +='<td>'+(parseInt(k)+parseInt(1))+'</td>';
					str1 +='<td> Booth-'+result.boothResults[k].partNo+'</td>';
						if(result.boothResults[k].boothResultVOList != null && result.boothResults[k].boothResultVOList.length>0)
						{
							
							if(isLocation)
								str1 +='<td>'+result.boothResults[k].boothResultVOList[0].location+'</td>';
							if(isVillage)
								str1 +='<td>'+result.boothResults[k].boothResultVOList[0].villagesCovered+'</td>';
							if(isMandal)
								str1 +='<td>'+result.boothResults[k].boothResultVOList[0].mandal+'</td>';
							str1 +='<td>'+result.boothResults[k].boothResultVOList[0].totalBoothVoters+'</td>';
							str1 +='<td>'+result.boothResults[k].boothResultVOList[0].totalVoters+'</td>';
							str1 +='<td>'+result.boothResults[k].boothResultVOList[0].pollingPercentage+'</td>';
							
							for(var m in result.boothResults[k].boothResultVOList)
							{
								votersCount = parseInt(votersCount) - parseInt(result.boothResults[k].boothResultVOList[m].votesEarned);
								percentage = parseFloat(percentage) + parseFloat(parseInt(result.boothResults[k].boothResultVOList[m].percentage));
								str1 +='<td>'+result.boothResults[k].boothResultVOList[m].votesEarned+'</td>';
								str1 +='<td>'+result.boothResults[k].boothResultVOList[m].percentage+'</td>';
							}
						}
						
						str1 +='<td>'+votersCount+'</td>';
						str1 +='<td>'+(parseFloat(100.00) - parseFloat(percentage) ).toFixed(2)+'</td>';
						//str1 +='<td>'+result.boothResults[k].boothResultVOList[m].wonParty+'</td>';	
					str1 +='</tr>';						
			}
			
			str1 +='</tbody>';
			str1 +='</table>';
		}
		
		$('#boothWiseResultsForExcelDiv').html(str);
		$('#boothWiseResultsForExcelDiv').append(str1);
		
	tableToExcel(id, 'Booth Wise Results  Report');
}

function getCountBoothDetails(type,partyName)
{

$('#boothInfoForPartyDiv').html('');
	if(boothWiseDetailsArr != null && boothWiseDetailsArr.length >0)
	{
		
		for(var i in boothWiseDetailsArr)
		{
			
			if(boothWiseDetailsArr[i].type == type && boothWiseDetailsArr[i].partyName == partyName )
			{
				var str='';
				$('#pollingPecentDialoguDiv').dialog({				
						resizable: false,
						modal: true,
						title: " "+type+" Polling Percentage Info For "+boothWiseDetailsArr[i].partyName+" Party Won Booths ",
						height: 500,
						width: 950,
						buttons: {
							"Close": function () {
								$(this).dialog('close');
							}
						}					
				});
				var results = boothWiseDetailsArr[i].value;
				if(results != null && results.length >0)
				{
					
						str +='<table class="table table-bordered" id="boothsForPartyTab">';
						str +='<thead>';
						str +='<tr>';
							str +='<th> Booth No </th>';
							str +='<th> Mandal </th>';
							str +='<th> Total Votes </th>';
							str +='<th> Polled Votes </th>';
							str +='<th> Earned Votes </th>';
							//str +='<th> Party Name </th>';
						str +='</tr>';
						str +='</thead>';
						
						str +='<tbody>';
						
					for(var k in results)
					{						
							str +='<tr>';
							str +='<td>'+parseInt(results[k].partNo)+'</td>';
							str +='<td>'+results[k].mandal+'</td>';
							str +='<td>'+results[k].totalBoothVoters+'</td>';
							str +='<td>'+results[k].totalVoters+'</td>';
							str +='<td>'+results[k].votesEarned+' </td>';
							//str +='<td>'+results[k].wonParty+'</td>';
							
							str +='</tr>';
						
					}
					str +='</tbody>';						
					str +='</table>';
					
					$('#boothInfoForPartyDiv').html(str);
					$('#boothsForPartyTab').dataTable();
				}				
			}			
		}	
	}
}
</script>
</body>
</html>
 