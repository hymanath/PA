<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BOOTH WISE RESULTS</title>
<script type="text/javascript" src="http://www.google.com/jsapi"></script>
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
			.no-border{ border: medium none;}
			.checkbox
			{
			float:none !important;
			width: auto !important;
			}
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
			.survey_nav ul li a{color:#333; font-weight:bold; font-size:12px; padding:12px 2px;text-decoration:none;text-shadow:0px 1px #ffcc00; }
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

			#errorDiv
			{
			 font-weight:bold;
			 color:red;
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

			#boothWiseResultsPage  thead th , #majarityCandidatesTab thead th ,#majarityCandidatesTab thead th, #majarityCandidatesTab  thead th{
				font-weight: bold  !important;
				background-color: #dff0d8  !important;
				color : #468847 !important;
				line-height: 15px !important;
			}
			
			
			.errorRed{background:red;}
			.errorYellow{background:yellow;}
			.errorLgreen{background:lightgreen;}
			.errorGreen{background:green;}
	
		.ui-icon-closethick 
		{
			margin-top:-8px !important;
			margin-left:-8px !important;
		}
		
		h4{
			font-size:20px !important;
		}
		</style>
		
		<script>
		var constituencyListArr = new Array();
		<c:forEach items="${constituencyList}" var="constituency"> 
           constituencyListArr.push('${constituency.id}');
        </c:forEach>
				
				//console.log(constituencyListArr);
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

						<div class="row text-center m_top20" style="margin-right:51px;display:none;" id="excelBtnId">
							<input type="button" class="btn btn-success" onclick="generateExcel('boothWiseResultsForExcelDiv');" value=" Export Excel "/>								
						</div>
						
			<img src='images/Loading-data.gif' class="offset5"  id="searchDataImg" style="width:70px;height:60px;display:none;"/>			
			<div id="majarityCandidatesDiv"></div>
			<div id="pollingPecentDiv"></div>
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
	//globalelectionType = electionType;
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
		$('#parliamentConstiId').append('<option value="0"> Select Parliament </option>');
		$('#constituencyId').append('<option value="0"> Select Constituency </option>');
		
		var constId = 282;
		
			if(electionType =='Assembly')
			{
			
			
				$('#assemblyConstiListDiv').hide();
				for(var i in result.selectOptionsList)
				{
					if(constituencyListArr.indexOf('\''+result.selectOptionsList[i].id+'\'') == -1){

						if( i == 0 )
						{				
							constId = result.selectOptionsList[i].id;
							$('#constituencyId').append('<option value="'+result.selectOptionsList[i].id+'" selected="selected">'+result.selectOptionsList[i].name+'</option>');
						}
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
				
						getDetailsForConstituency(constId);
			}
			else
			{
				$('#assemblyConstiListDiv').show();

				
				for(var i in result.selectOptionsList)
				{
					if( i == 0 )
					{				
						constId = result.selectOptionsList[i].id;
						$('#parliamentConstiId').append('<option value="'+result.selectOptionsList[i].id+'" selected="selected">'+result.selectOptionsList[i].name+'</option>');
					}
					else
					{
							$('#parliamentConstiId').append('<option value="'+result.selectOptionsList[i].id+'">'+result.selectOptionsList[i].name+'</option>');
					}
				}
				getConstituenciesForParliament(constId);
			}		
		}
		
	
	/*
	if(result.selectOptionsList1 != null && result.selectOptionsList1.length > 0)
	{
		var str = ''; 
		for(var i in result.selectOptionsList1)
		{
			if(parseInt(result.selectOptionsList1[i].name) > 2000){
				if(i == 0)
				{
					str +=' <label class="radio"><input type="radio" id="'+result.selectOptionsList1[i].name+'RadoiId" name="PartiesName" key="'+result.selectOptionsList1[i].name+'"  value="'+result.selectOptionsList1[i].id+'" checked="true" onclick="getPartyDetailsForConstituency()">'+result.selectOptionsList1[i].name+'</label>';
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
		//str +=' <input  type="checkbox" id="AllPartiesId" class="PartiesNameCls" value="0"> All ';
		for(var i in result.selectOptionsList2)
		{
			str +=' <label class="checkbox"> <input  type="checkbox" id="'+result.selectOptionsList2[i].name+'Id" class="PartiesNameCls" value="'+result.selectOptionsList2[i].id+'" checked="checked" onclick="getSurveyUserLoctionCount();">'+result.selectOptionsList2[i].name+'</label>';
		}
		$('#partyListDiv').html(str);
	}
	
	*/
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
		//str +=' <input  type="checkbox" id="AllPartiesId" class="PartiesNameCls" value="0"> All ';
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

//<div id="majarityCandidatesDiv"></div>
function buildBoothWiseResultReport(result,electionYear)
{
	var mainStr = '';
	var constiName = $('#constituencyId option:selected').text();
	var str='';
	var isWonCandidateAvailable = false;
	str +='<h4> <br>'+constiName+' Assembly Constituency '+electionYear+' Election Booth Wise Results </h4> <br>';
	

	
		if(result.partyBoothPerformanceVOList != null && result.partyBoothPerformanceVOList.length >0)
		{
					
			str +='<table class="table table-bordered">';
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
					str +='<th> Candidate Name </th>';
					//str +='<th> Total Votes </th>';
					//str +='<th> Total Valid Votes </th>';
					//str +='<th> Voting Percentage </th>';
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
							str +='<td style="background:#DBEAF9;">'+result.partyBoothPerformanceVOList[k].candidateName+' - ('+result.partyBoothPerformanceVOList[k].partyName+')</td>';
							str +='<td  style="background:#DBEAF9;">'+result.partyBoothPerformanceVOList[k].votesGained+'</td>';						
							str +='<td  style="background:#DBEAF9;">'+result.partyBoothPerformanceVOList[k].percentage+'  </td>';
							str +='<td  style="background:#DBEAF9;">'+result.partyBoothPerformanceVOList[k].marginVotes+'  ( Rank - '+result.partyBoothPerformanceVOList[k].rank+') </td>';
						str +='</tr>';
				
					}
					else
					{
						str +='<tr>';
							str +='<td>'+result.partyBoothPerformanceVOList[k].candidateName+' - ('+result.partyBoothPerformanceVOList[k].partyName+')</td>';
							str +='<td>'+result.partyBoothPerformanceVOList[k].votesGained+'</td>';						
							str +='<td>'+result.partyBoothPerformanceVOList[k].percentage+'  </td>';
							str +='<td>'+result.partyBoothPerformanceVOList[k].marginVotes+'  ( Rank - '+result.partyBoothPerformanceVOList[k].rank+') </td>';
						str +='</tr>';
				
					}
						
				}		
				if(!isWonCandidateAvailable)
					{
						str +='<tr>';
							str +='<td style="background:#DBEAF9;">'+result.partyBoothPerformanceVOList[0].wonCandidate[0].candidateName+' - ('+result.partyBoothPerformanceVOList[0].wonCandidate[0].partyName+')</td>';
							str +='<td  style="background:#DBEAF9;">'+result.partyBoothPerformanceVOList[0].wonCandidate[0].votesEarned+'</td>';						
							str +='<td  style="background:#DBEAF9;">'+result.partyBoothPerformanceVOList[0].wonCandidate[0].votesPercengate+'  </td>';
							str +='<td  style="background:#DBEAF9;">'+result.partyBoothPerformanceVOList[0].wonCandidate[0].marginVotes+' ( Rank - '+result.partyBoothPerformanceVOList[0].wonCandidate[0].rank+') </td>';
						str +='</tr>';
				
					}
					
			str +='</tbody>';
			str +='</table>';
		}
	
	$('#boothWiseResultsForExcelDiv').html(str);
	$('#majarityCandidatesDiv').html(str);
	mainStr = mainStr+str;
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
					
				str +='</tr>';

				str +='<tr>';
				str +='<th></th>';
				str +='<th></th>';
					for(var p=0; p<partiesSize ; p++)
					{						
						str +='<th> Party Votes % </th>';
						str +='<th> Won Booths  </th>';					
					}								
				str +='</tr>';
			str +='</thead>';	
			str +='<tbody>';
			
				for(var k in result.partyPerWiseboothResults)
				{
						str +='<tr>';
							str +='<td  style="text-align:center">'+result.partyPerWiseboothResults[k].location+'</td>';
							str +='<td  style="text-align:center">'+result.partyPerWiseboothResults[k].boothResultVOList[0].votesEarned+'</td>';
							if(result.partyPerWiseboothResults[k].boothResultVOList != null && result.partyPerWiseboothResults[k].boothResultVOList.length >0 )
							{
								for(var p=0; p<partiesSize ; p++)
								{
									str +='<td  style="text-align:center">'+result.partyPerWiseboothResults[k].boothResultVOList[p].percentage+'</td> ';
									
									if(result.partyPerWiseboothResults[k].boothResultVOList[p].boothResultVOList != null && result.partyPerWiseboothResults[k].boothResultVOList[p].boothResultVOList.length >0)
									{
										var isBuild = true;
										for(var r in result.partyPerWiseboothResults[k].boothResultVOList[p].boothResultVOList)
										{
											if(result.partyPerWiseboothResults[k].boothResultVOList[p].boothResultVOList[r].wonParty == result.partyPerWiseboothResults[k].boothResultVOList[p].message)
											{
												isBuild = false;
												//console.log(result.partyPerWiseboothResults[k].boothResultVOList[p].boothResultVOList[r].wonParty);
												str+='<td  style="text-align:center">'+result.partyPerWiseboothResults[k].boothResultVOList[p].boothResultVOList[r].resultState+'</td>';
											}
										}

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
							
						str +='</tr>';						
				}
			
			
			str +='</tbody>';
			str +='</table>';
		}
			$('#boothWiseResultsForExcelDiv').append(str);
		$('#pollingPecentDiv').html(str);
		
		mainStr = mainStr+str;
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
									str +='<td  style="text-align:center">'+result.perWiseboothResults[k].boothResultVOList[p].votesEarned+'</td>';
									str +='<td  style="text-align:center">'+result.perWiseboothResults[k].boothResultVOList[p].percentage+' </td>';
								}
					
							}
							
						str +='</tr>';						
				}
			
			
			str +='</tbody>';
			str +='</table>';
		}
		$('#boothWiseResultsForExcelDiv').append(str);
		$('#pollingVotesPercDiv').html(str);
		mainStr = mainStr+str;
		
		str='';
		str +='<h4> Booth Wise Performance :  <h4>';
		if(result.boothResults != null && result.boothResults.length >0)
		{
			str +='<table class="table table-bordered m_top20 " id="boothWiseResultsPage" style="font-size:12px;color:#000000;font-weight:normal;">';	
			str +='<thead>';
				str +='<tr>';				
					str +='<th > Booth No  </th>';
					str +='<th > Location  </th>';
					str +='<th > Village Covered  </th>';
					//str +='<th > Mandal </th>';
					str +='<th > Polled Votes  </th>';	
					str +='<th > Polling %   </th>';
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
				str +='<th></th>';
				str +='<th></th>';
				str +='<th></th>';
				str +='<th></th>';
					for(var p=0; p<partiesSize ; p++)
					{
					str +='<th > Votes Earned  </th>';	
					str +='<th > Votes %   </th>';		
					}								
				str +='</tr>';
				
			str +='</thead>';	
			str +='<tbody>';	
			for(var k in result.boothResults)
			{
					str +='<tr>';
					str +='<td>'+result.boothResults[k].partNo+'</td>';
						if(result.boothResults[k].boothResultVOList != null && result.boothResults[k].boothResultVOList.length>0)
						{
							str +='<td>'+result.boothResults[k].boothResultVOList[0].location+'</td>';
							str +='<td>'+result.boothResults[k].boothResultVOList[0].villagesCovered+'</td>';
							//str +='<td>'+result.boothResults[k].boothResultVOList[0].mandal+'</td>';
							str +='<td>'+result.boothResults[k].boothResultVOList[0].totalVoters+'</td>';
							str +='<td>'+result.boothResults[k].boothResultVOList[0].pollingPercentage+'</td>';
							for(var m in result.boothResults[k].boothResultVOList)
							{
								str +='<td>'+result.boothResults[k].boothResultVOList[m].votesEarned+'</td>';
								str +='<td>'+result.boothResults[k].boothResultVOList[m].percentage+'</td>';
							}
						}
					str +='</tr>';						
			}
			
			str +='</tbody>';
			str +='</table>';
		}
		
		$('#boothWiseResultsForExcelDiv').append(str);
		$('#boothWiseResultsDiv').html(str);

		$('#excelBtnId').show();
}

function generateExcel(id)
{
	tableToExcel(id, 'Booth Wise Results  Report');
}

</script>
</body>
</html>
