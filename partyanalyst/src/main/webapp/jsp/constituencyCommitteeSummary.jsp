<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title> Constituency Committee Summary </title>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- Bootstrap -->
	<script src="js/cadreCommittee/dist/js/bootstrap.min.js"></script>
   <script src="js/cadreCommittee/js/jquery.smartmenus.bootstrap.min.js" type="text/javascript"></script>

    <link href="css/cadreCommitee/bootstrap.min.css" rel="stylesheet">
	 <!-- Custom Styles -->
    <link href="css/cadreCommitee/style.css" rel="stylesheet">
	<!----slick.css----->
	<link rel="stylesheet" type="text/css" href="css/cadreCommitee/slick/slick.css"/>
	
    <!-- Include all compiled plugins (below), or include individual files as needed -->

	<!----slick Js----->
	<script type="text/javascript" src="js/cadreCommittee/slick/slick.min.js"></script>
	<script type="text/javascript" src="js/cadreCommitteeRequest/cadreCommitteeRequest.js"></script>
	    <script type="text/javascript" src="js/jquery.dataTables.js"></script>
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
	
	<style>
	
	label {
    display: inline-block;
    font-weight: normal !important;
	}

#constiTableId th{
    background-color: #d3d3d3 !important;
}
#constiTableId td{
    background-color: #F3F3F3 !important;
}

.widget
	{
		background-color:rgba(0,0,0,0.1);
		text-align:center;
		padding:10px 0px 10px 0px;
		margin-right:20px;
		border:1px solid #999;
		box-shadow:1px 1px 5px #999;
	}
	.borderbox
	{
		margin-top:10px;
		border:5px solid #CCC;
		border-radius:5px;
	}
	.tablecaption
	{
		background-color:#FC6;
		padding-left:5px;
		font-size:16px;
		font-weight:bold;
		color:#FFF;
	}
	</style>
</head>
<body>
<style>
	.locationName{text-transform: uppercase;}
	
</style>

	<script>
		var userAccessType = '${pageAccessType}';
	</script>
<div class="container">
<div class="row m_top20">
<h4 style="color:#46b8da;font-weight:bold;text-align:center;border-radius: 5px;height: 31px;padding: 5px" id="titleId"> </h4>
</div>
    <div class="row m_top20 locationCls" style="display:none;">
  	   <div class="col-md-4 col-md-offset-2 col-sm-6 col-xs-6">Select District:<select id="districtsId" class="form-control" onChange="getAllConstituencysForADistrict()"><option value="0">Select District</option></select> </div>
       <div class="col-md-4  col-sm-6 col-xs-6">Select Constituency:<select id="constituencysId" class="form-control"><option value="0">Select Constituency</option></select> </div>
    </div>
		<div class="row m_top20">
	
	 	
						<c:if test="${pageAccessType == 'ALL'}">		
	<div  class="row m_top20 form-inline" class="row m_top20 form-inline" style="margin-left:150px">
		
					 
						Select State	<select id="stateId" onchange="getUserAccessInfo();" class="form-control" style="width:200px;">	
						<option value="0">All</option> 
							<option value="1"> Andhra Pradesh </option>
							<option value="2"> Telangana </option>
							</select>
							
							<img id="imgajax" src="images/icons/search.gif" 
								alt="Processing Image" style="display:none;width:17px;height:11px;"/>
						Select Constituency	<select id="userAccessconstituencyId" class="form-control" style="width:200px;"onchange="reload()">						 
							
							</select>
							
			Select Committee Type<select id="committetypeId"  class="form-control"  onchange="getRolesBasedReport(0)">	
						<option value="0">All</option> 
							<option value="1"> Main </option>
							<option value="2"> Affiliated </option>
							</select>
				</div>	
			</c:if>	
	<c:if test="${pageAccessType != 'ALL'}">
	<div  class="row m_top20 form-inline" class="row m_top20 form-inline" style="margin-left:300px">
		Select Committee Type<select id="committetypeId"  class="form-control" >	
						<option value="0">All</option> 
							<option value="1"> Main </option>
							<option value="2"> Affiliated </option>
							</select>
							</div>
					 
	</c:if>

	<div class="col-md-4 m_top20 col-md-offset-3 form-inline"><span class="btn btn-success btn-xs"><label class="radio"><input type="radio" style="vertical-align: text-bottom;" class="reportTypeCls" value=1 name="reportType" checked="true"> VILLAGE / WARD</label></span>&nbsp;&nbsp;
		<span class="btn btn-success btn-xs"><label class="radio"><input type="radio" style="vertical-align: text-bottom;" class="reportTypeCls" value=2 name="reportType">MANDAL / TOWN / DIVISION</label></span>
		
		</div>
	</div>
	<div id="constiRoleSummry" style="display:none;">
		<img id="summaryAjaxRole" src="./images/Loading-data.gif" class=" col-sm-offset-4" alt="Processing Image"/>
		
	</div>
		<a id="hide" href="javascript:{}" class="pull-right btn btn-warning summaryEventCls" title="Hide Constituency Wise Detailed Overview"> Hide <i class="glyphicon glyphicon-chevron-up"></i></a>
	<a id="show"  href="javascript:{}" class="pull-right btn btn-success summaryEventCls" style="display:none;" title="Show Constituency Wise Detailed Overview"> Show
	<i class="glyphicon glyphicon-chevron-down"></i></a>
	<div id="constiRoleSummary">		
	</div>
	
	<!--<div class="row m_top20">
		<div class="col-md-4 col-md-offset-3"><label class="radio"><input type="radio" style="vertical-align: text-bottom;" class="reportTypeCls" value=1 name="reportType" checked="true"> VILLAGE / WARD</label></div>
		<div class="col-md-4 "><label class="radio"><input type="radio" style="vertical-align: text-bottom;" class="reportTypeCls" value=2 name="reportType">MANDAL / TOWN / DIVISION</label></div>
	</div>-->
	
	<span class="btn btn-info pull-right exportToExcel" onclick="exportToExcel()" style="display:none;"> Export To Excel </span>
	
	<div id="constSummary" style="display:none;">
		<img id="summaryAjax" src="./images/Loading-data.gif" class="" alt="Processing Image"/>
	</div>
	
	
</div>

<div id="dialogSummary" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">

<div class="modal-dialog modal-lg">
<div class="modal-content">
    
	 <div class="modal-header">
	  <button aria-label="Close" data-dismiss="modal" class="close" type="button"><span aria-hidden="true">x</span></button>
		<h3 class="panel-header text-center"></h3>
	  </div>
    <div id="cadreDetailsDiv" style="margin-top:25px;padding:10px;"></div>
</div>
</div>

<script type="text/javascript">
var accessConstituency = '${accessConstituency}';
var accessConstituencyId = '${accessConstituencyId}';

$(document).ready(function(){
	$('.summaryEventCls').click(function(){
		var btnId = $(this).attr('id');
		if(btnId == 'hide')
		{
			$('#show').show();
			$('#hide').hide();
			$('#constiRoleSummary').hide();
			
		}
		else if(btnId == 'show')
		{
			$('#show').hide();
			$('#hide').show();
			$('#constiRoleSummary').show();
		}
	});
});
if(accessConstituencyId!='null' && accessConstituencyId!=""){
	$(".locationCls").hide();
	getConstituencySummary();
	$("#constSummary").show();
}else{
	getAllDistricts();
}


function getAllDistricts()
{

 $.ajax({
			type : "GET",
			url : "getAllDistrictsAction.action",
			data : {} ,
		}).done(function(result){
			if(typeof result == "string"){
				if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
				  location.reload(); 
				}
			}
			$("#districtsId  option").remove();
			$("#districtsId").append('<option value="0">Select District</option>');
			if(result!=null){
			for(var i in result){
				   $("#districtsId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
				 }
			}	 
		});
}

$("#constituencysId").change(function(){
	$("#constSummary").show();
	$(".exportToExcel").hide();
	getConstituencySummary();
});
$(".reportTypeCls").click(function(){
	getConstituencySummary();
	getRolesBasedReport(0);

	
});
	
function getAllConstituencysForADistrict()
{
    var districtId = $("#districtsId").val();
	$("#constituencysId  option").remove();
	$("#constituencysId").append('<option value="0">Select Constituency</option>');
	if(districtId==0){
	  return;	 
	}
	
	$("#constSummary").html('');
 
    $.ajax({
			type : "GET",
			url : "getAllConstituencysForADistrictAction.action",
			data : {districtId:districtId} ,
		}).done(function(result){
			if(typeof result == "string"){
				if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
				  location.reload(); 
				}
			}
			if(result!=null){
			   for(var i in result){
				   $("#constituencysId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			    }
			}	 
		});
}

function getConstituencySummary(){
	$("#titleId").html("");
	$("#constSummary").html('<img id="summaryAjax" src="./images/Loading-data.gif" class=" col-sm-offset-4" alt="Processing Image"/>');
	var constiId = "";
	if(accessConstituencyId!=null && accessConstituencyId!=""){
		constiId = accessConstituencyId;
	}else{
		constiId = $("#constituencysId").val();
	}
	
	if(constiId==0){
		$("#constSummary").html('');
		return;	 
	}
	var reportType = $("input[type='radio'][name='reportType']:checked").val();
	
	var jsObj ={
		 constituencyId:constiId,reportType :reportType            
	}	
	$.ajax({
		type : "POST",
		url : "getConstituencyCommitteSummary.action",
		data : {task:JSON.stringify(jsObj)} ,
	}).done(function(result){
		if(typeof result == "string"){
				if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
				  location.reload(); 
				}
		}
		$(".exportToExcel").show();
		buildConstituencySummary(result,jsObj);
	});
	
}

function buildConstituencySummary(results,jsObj){

	var repType = jsObj.reportType;
	
	$("#constSummary").html("");
		var str = '';
	
		if(repType==1){
		
			if((results.mandalsList==null && results.localBodiesList ==null) || (results.mandalsList.length<=0 && results.localBodiesList.length<=0)){
				$("#constSummary").html("<br><h4 style='text-align:center;;color:red'> NO RESULTS TO DISPLAY.</h4>");
				return;
			}
			$("#titleId").append(''+results.accessState+' CONSTITUENCY CADRE COMMITTEE OVERVIEW ');
			if(results.mandalsList!=null && results.mandalsList.length>0){
					for(var i in results.mandalsList){
				var rest = results.mandalsList[i];
				if(rest.locationsList!=null && rest.locationsList.length>0){
					var reqPositionsArray = new Array();
					
					str += '<h4 class="locationName">'+rest.locationName+'</h4>';
				str+='<table class="table table-yellow-bordered table-condensed " style="width:100%; ">';
				str+='<thead>';
					str+='<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">';
						str+='<th  rowspan=2> Location </th>';

						for(var k in results.resultList){
							if(results.resultList[k].basicCommitteeName == 'Main'){
								if(reqPositionsArray.indexOf(k) == -1){
								  reqPositionsArray.push(k);
								}
								str+='<th colspan=2>'+results.resultList[k].basicCommitteeName+'</th>';
							}else{
								 for(var c in rest.locationsList){
									 if(rest.locationsList[c].resultList[k].membersCount!=null || rest.locationsList[c].resultList[k].electrolsCount!=null){
										 if(reqPositionsArray.indexOf(k) == -1){
										      str+='<th colspan=2>'+results.resultList[k].basicCommitteeName+'</th>';
											  reqPositionsArray.push(k);
											}
									 }
								 }
							}
							
						}
						if(results.mandalsList[0].locationsList[0].cadreIVRVO != null)
						{
							var length = (results.mandalsList[0].locationsList[0].cadreIVRVO.optionsList.length -1)*2;
							str+='<th  colspan='+length+'> IVR Details </th>';
						}
						
						
					str+='</tr>';
					str+='<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">';
						for(var k in reqPositionsArray){
							str+='<th> Members </th>';
							str+='<th> Electrols </th>';
						}
						if(results.mandalsList[0].locationsList[0].cadreIVRVO != null)
						{
							for(var tp in results.mandalsList[0].locationsList[0].cadreIVRVO.optionsList)
							{
								 if(results.mandalsList[0].locationsList[0].cadreIVRVO.optionsList[tp].id != 8){
								 str+='<th> '+results.mandalsList[0].locationsList[0].cadreIVRVO.optionsList[tp].name+' </th>';
								  str+='<th> % </th>';
								  }
							}
						}
						
					str+='</tr>';
				str+='</thead>';
					str+='<tbody>';
					var percentage = 0;
					var perc = 0;
						for(var j in rest.locationsList){
							str+='<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">';
								str+='<td>'+rest.locationsList[j].locationName+'</td>';
									for(var k in rest.locationsList[j].resultList){
									  if(reqPositionsArray.indexOf(k) != -1){
										if(rest.locationsList[j].resultList[k].membersCount!=null){
											//str+='<td><a id="location'+rest.locationsList[j].locationId+'" class="lctnCmmty" attr_cmmtyType='+rest.locationsList[j].resultList[k].basicCommitteeTypeId+' attr_locationId='+rest.locationsList[j].locationId+' attr_locationType="6" attr_memType="members" href="javascript:{gettingCadreDetails(\'location'+rest.locationsList[j].locationId+'\',\'members\',6,'+rest.locationsList[j].locationId+','+rest.locationsList[j].resultList[k].basicCommitteeTypeId+');}">'+rest.locationsList[j].resultList[k].membersCount+'</a></td>';
										    str+='<td><a href="javascript:{gettingCadreDetails(\'members\',6,'+rest.locationsList[j].locationId+','+rest.locationsList[j].resultList[k].basicCommitteeTypeId+',\''+rest.locationsList[j].locationName+'\',\''+rest.locationsList[j].resultList[k].basicCommitteeName+'\');}">'+rest.locationsList[j].resultList[k].membersCount+'</a></td>';
										}else{
											str+='<td> </td>';
										} 
										
										if(rest.locationsList[j].resultList[k].electrolsCount!=null){
										    //str+='<td><a id="location'+rest.locationsList[j].locationId+'" class="lctnCmmty" attr_cmmtyType='+rest.locationsList[j].resultList[k].basicCommitteeTypeId+' attr_locationId='+rest.locationsList[j].locationId+' attr_locationType="6" attr_memType="electrols" href="javascript:{gettingCadreDetails(\'location'+rest.locationsList[j].locationId+'\',\'electrols\');}">'+rest.locationsList[j].resultList[k].electrolsCount+'</a></td>';
											//str+='<td class="lctnCmmty" attr_cmmtyType='+rest.locationsList[j].resultList[k].basicCommitteeTypeId+' attr_locationId='+rest.locationsList[j].locationId+' attr_locationType="6" attr_memType="electrols">'+rest.locationsList[j].resultList[k].electrolsCount+'</td>';
										     str+='<td><a  href="javascript:{gettingCadreDetails(\'electrols\',6,'+rest.locationsList[j].locationId+','+rest.locationsList[j].resultList[k].basicCommitteeTypeId+',\''+rest.locationsList[j].locationName+'\',\''+rest.locationsList[j].resultList[k].basicCommitteeName+'\');}">'+rest.locationsList[j].resultList[k].electrolsCount+'</a></td>';

										}else{
											str+='<td> </td>';
										}
									  }
									}
									if(rest.locationsList[j].cadreIVRVO != null)
									{
										for(var pp in rest.locationsList[j].cadreIVRVO.optionsList)
										{
											if(rest.locationsList[j].cadreIVRVO.optionsList[pp].id != 8)
											{
												if(rest.locationsList[j].cadreIVRVO.total >0)
												{
													percentage = (rest.locationsList[j].cadreIVRVO.optionsList[pp].count *100)/ rest.locationsList[j].cadreIVRVO.total;
													perc = percentage.toFixed(0);
													str+='<td>'+rest.locationsList[j].cadreIVRVO.optionsList[pp].count+'</td>';
													str+='<td>'+perc+'</td>';
												}				
												else
												{
													str+='<td></td>';
													str+='<td></td>';
												}
											}
										}
									}
									
										
										
									
									
									
									
									
							str+='</tr>';
						}
					str+='</tbody>';
				str+='</table> ';
				}
				
			}
			}
			
			if(results.localBodiesList !=null && results.localBodiesList.length>0){
				for(var i in results.localBodiesList){
				var rest = results.localBodiesList[i];
				var reqPositionsArray = new Array();
				str += '<h4 class="locationName">'+rest.locationName+'</h4>'
				str+='<table class="table table-yellow-bordered table-condensed " style="width:100%; ">';
				str+='<thead>';
					str+='<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">';
						str+='<th rowspan=2> Location </th>';
						for(var k in results.resultList){
							if(results.resultList[k].basicCommitteeName == 'Main'){
								if(reqPositionsArray.indexOf(k) == -1){
									  reqPositionsArray.push(k);
									}
								str+='<th colspan=2>'+results.resultList[k].basicCommitteeName+'</th>';
							}else{
								 for(var c in rest.locationsList){
									 if(rest.locationsList[c].resultList[k].membersCount!=null || rest.locationsList[c].resultList[k].electrolsCount!=null){
										 if(reqPositionsArray.indexOf(k) == -1){
										      str+='<th colspan=2>'+results.resultList[k].basicCommitteeName+'</th>';
											  reqPositionsArray.push(k);
											}
									 }
								 }
							}
							
						}
						if(results.mandalsList[0].locationsList[0].cadreIVRVO != null)
						{
							var length = (results.mandalsList[0].locationsList[0].cadreIVRVO.optionsList.length -1)*2;
							str+='<th  colspan='+length+'> IVR Details </th>';
						}
					str+='</tr>';
					str+='<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">';
						for(var k in reqPositionsArray){
							str+='<th> Members </th>';
							str+='<th> Electrols </th>';
						}
						if(results.mandalsList[0].locationsList[0].cadreIVRVO != null)
						{
							for(var tp in results.mandalsList[0].locationsList[0].cadreIVRVO.optionsList)
							{
								 if(results.mandalsList[0].locationsList[0].cadreIVRVO.optionsList[tp].id != 8){
								 str+='<th> '+results.mandalsList[0].locationsList[0].cadreIVRVO.optionsList[tp].name+' </th>';
								  str+='<th> % </th>';
								  }
							}
						}
					str+='</tr>';
				str+='</thead>';
					str+='<tbody>';
						for(var j in rest.locationsList){
							str+='<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">';
								str+='<td>'+rest.locationsList[j].locationName+'</td>';
									for(var k in rest.locationsList[j].resultList){
									  if(reqPositionsArray.indexOf(k) != -1){
										if(rest.locationsList[j].resultList[k].membersCount!=null){
											//str+='<td class="lctnCmmty" attr_cmmtyType='+rest.locationsList[j].resultList[k].basicCommitteeTypeId+' attr_locationId='+rest.locationsList[j].locationId+' attr_locationType="8" attr_memType="members">'+rest.locationsList[j].resultList[k].membersCount+'</td>';
										     // str+='<td><a class="lctnCmmty" attr_cmmtyType='+rest.locationsList[j].resultList[k].basicCommitteeTypeId+' attr_locationId='+rest.locationsList[j].locationId+' attr_locationType="8" attr_memType="members" id="location'+rest.locationsList[j].locationId+'" href="javascript:{gettingCadreDetails(\'location'+rest.locationsList[j].locationId+'\');}">'+rest.locationsList[j].resultList[k].membersCount+'</a></td>';
									        str+='<td><a href="javascript:{gettingCadreDetails(\'members\',8,'+rest.locationsList[j].locationId+','+rest.locationsList[j].resultList[k].basicCommitteeTypeId+',\''+rest.locationsList[j].locationName+'\',\''+rest.locationsList[j].resultList[k].basicCommitteeName+'\');}">'+rest.locationsList[j].resultList[k].membersCount+'</a></td>';

										}else{
											str+='<td> </td>';
										}
										
										if(rest.locationsList[j].resultList[k].electrolsCount!=null){
											//str+='<td class="lctnCmmty" attr_cmmtyType='+rest.locationsList[j].resultList[k].basicCommitteeTypeId+' attr_locationId='+rest.locationsList[j].locationId+' attr_locationType="8" attr_memType="electrols">'+rest.locationsList[j].resultList[k].electrolsCount+'</td>';
										    //str+='<td><a class="lctnCmmty" attr_cmmtyType='+rest.locationsList[j].resultList[k].basicCommitteeTypeId+' attr_locationId='+rest.locationsList[j].locationId+' attr_locationType="8" attr_memType="electrols" id="location'+rest.locationsList[j].locationId+'" href="javascript:{gettingCadreDetails(\'location'+rest.locationsList[j].locationId+'\');}">'+rest.locationsList[j].resultList[k].electrolsCount+'</a></td>';
										    str+='<td><a href="javascript:{gettingCadreDetails(\'electrols\',8,'+rest.locationsList[j].locationId+','+rest.locationsList[j].resultList[k].basicCommitteeTypeId+',\''+rest.locationsList[j].locationName+'\',\''+rest.locationsList[j].resultList[k].basicCommitteeName+'\');}">'+rest.locationsList[j].resultList[k].electrolsCount+'</a></td>';

										}else{
											str+='<td> </td>';
										}
									 }	
									}
									if(rest.locationsList[j].cadreIVRVO != null)
									{
										for(var pp in rest.locationsList[j].cadreIVRVO.optionsList1)
										{
											if(rest.locationsList[j].cadreIVRVO.optionsList1[pp].id != 13)
											{
												if(rest.locationsList[j].cadreIVRVO.total >0)
												{
													percentage = (rest.locationsList[j].cadreIVRVO.optionsList1[pp].count *100)/ rest.locationsList[j].cadreIVRVO.total;
													perc = percentage.toFixed(0);
													str+='<td>'+rest.locationsList[j].cadreIVRVO.optionsList1[pp].count+'</td>';
													str+='<td>'+perc+'</td>';
												}				
												else
												{
													str+='<td>0</td>';
													str+='<td>0</td>';
												}
											}
										}
									}
									
							str+='</tr>';
						}
					str+='</tbody>';
				str+='</table> ';
			}
			}
			
			
		}else{
				str += '<h4 class="locationName"> Mandal/Town/Division Wise Committees Summary</h4>'
				str+='<table class="table table-yellow-bordered table-condensed " style="width:100%; ">';
				str+='<thead>';
					str+='<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">';
						str+='<th> Location </th>';
						for(var k in results.resultList){
							str+='<th>'+results.resultList[k].basicCommitteeName+'</th>';
						}
					str+='</tr>';
				str+='</thead>';
					str+='<tbody>';
						for(var i in results.mandalsList){
							var rest = results.mandalsList[i]
							str+='<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">';
							str+='<td>'+rest.locationName+'</td>';
							for(var j in rest.resultList){
								if(rest.resultList[j].membersCount!=null){
									//str+='<td class="lctnCmmty" attr_cmmtyType='+rest.resultList[j].basicCommitteeTypeId+' attr_locationId='+rest.locationId+' attr_locationType="5">'+rest.resultList[j].membersCount+'</td>';
							        //str+='<td><a class="lctnCmmty" attr_cmmtyType='+rest.resultList[j].basicCommitteeTypeId+' attr_locationId='+rest.locationId+' attr_locationType="5"  id="location'+rest.locationId+'" href="javascript:{gettingCadreDetails(\'location'+rest.locationId+'\');}">'+rest.resultList[j].membersCount+'</a></td>';
									
                                    str+='<td><a href="javascript:{gettingCadreDetails(\'members\',5,'+rest.locationId+','+rest.resultList[j].basicCommitteeTypeId+',\''+rest.locationName+'\',\''+rest.resultList[j].basicCommitteeName+'\');}">'+rest.resultList[j].membersCount+'</a></td>';
									

								}else{
									str+='<td> - </td>';
								}
								
							}
							str+='</tr>';
						}
						
						for(var i in results.localBodiesList){
							var rest = results.localBodiesList[i]
							str+='<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">';
							str+='<td>'+rest.locationName+'</td>';
							for(var j in rest.resultList){
								if(rest.resultList[j].membersCount!=null){
									//str+='<td class="lctnCmmty" attr_cmmtyType='+rest.resultList[j].basicCommitteeTypeId+' attr_locationId='+rest.locationId+' attr_locationType="7">'+rest.resultList[j].membersCount+'</td>';
								   //str+='<td><a class="lctnCmmty" attr_cmmtyType='+rest.resultList[j].basicCommitteeTypeId+' attr_locationId='+rest.locationId+' attr_locationType="7"  id="location'+rest.locationId+'" href="javascript:{gettingCadreDetails(\'location'+rest.locationId+'\');}">'+rest.resultList[j].membersCount+'</a></td>';
 								   str+='<td><a href="javascript:{gettingCadreDetails(\'members\',7,'+rest.locationId+',\''+rest.resultList[j].basicCommitteeTypeId+'\',\''+rest.locationName+'\',\''+rest.resultList[j].basicCommitteeName+'\');}">'+rest.resultList[j].membersCount+'</a></td>';
								   }else{
									str+='<td> - </td>';
								}
							
							}
							str+='</tr>';
						}
						for(var i in results.divisionList){
							var rest = results.divisionList[i]
							str+='<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">';
							str+='<td>'+rest.locationName+'</td>';
							for(var j in rest.resultList){
								if(rest.resultList[j].membersCount!=null){
									//str+='<td class="lctnCmmty" attr_cmmtyType='+rest.resultList[j].basicCommitteeTypeId+' attr_locationId='+rest.locationId+' attr_locationType="11">'+rest.resultList[j].membersCount+'</td>';
									 //str+='<td><a class="lctnCmmty" attr_cmmtyType='+rest.resultList[j].basicCommitteeTypeId+' attr_locationId='+rest.locationId+' attr_locationType="11"  id="location'+rest.locationId+'" href="javascript:{gettingCadreDetails(\'location'+rest.locationId+'\');}">'+rest.resultList[j].membersCount+'</a></td>';
									 //str+='<td><a href="javascript:{gettingCadreDetails(\'members\',9,'+rest.locationId+','+rest.resultList[j].basicCommitteeTypeId+','+rest.locationName+','+rest.resultList[j].basicCommitteeName+');}">'+rest.resultList[j].membersCount+'</a></td>';
									 str+='<td><a href="javascript:{gettingCadreDetails(\'members\',9,'+rest.locationId+',\''+rest.resultList[j].basicCommitteeTypeId+'\',\''+rest.locationName+','+rest.resultList[j].basicCommitteeName+'\');}">'+rest.resultList[j].membersCount+'</a></td>';

								}else{
									str+='<td> - </td>';
								}
								
							}
							str+='</tr>';
						}
						
					str+='</tbody>';
				str+='</table> ';
			
		}
		
	$("#constSummary").html(str);
}
function gettingCadreDetails(type,locationType,location,basicCmmtyId,locationName,basicCmmtyName){
	
		
		 var jsObj={
		         locationId:location,locationType:locationType,basicCommitteeTypeId:basicCmmtyId,type:type,casteStateId:0,gender:"",fromAge:0,toAge:0
		       };
			   
		 $.ajax({
			type : "GET",
			url : "gettingCadreDetailsAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(typeof result == "string"){
				if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
				  location.reload(); 
				}
			}
			 buildingResults(result,type,locationName,basicCmmtyName,basicCmmtyId,locationType);
		});
		
	}
function buildingResults(result,type,locationName,basicCmmtyName,basicCmmtyId,locationTypeId)
{
	
  if(result!=null)
  {
    var name = '';
	if(locationName.indexOf("(") != -1){
	 name=(locationName.slice(0,locationName.indexOf("(")));
	 }
	else{
	name=locationName;
	}
	
    if(result.length>0){
    var str='';
	str+='<div>';
	str+='<table class="table">';
str+='<thead>';
str+='<tr>';
str+='<td>';
str+='<span class="">';
str+='<table class="table table-bordered" style="width:200px">';
str+='<thead>';
str+='<tr>';
str+='<th style="background-color:#EDEDED;"> Total Candidates : </th>';
str+='<td> '+result[0].total+' </td>';
str+='</tr>';
str+='</thead>';
str+='</table>';
str+='</span>';
str+='</td>';
str+='<td>';
str+='<span class="">';
str+='<table class="table table-bordered" style="width:200px">';
str+='<thead>';
str+='<tr>';
str+='<th  style="background-color:#EDEDED;"> Male Candidates : </th>';
str+='<td> '+result[0].maleCount+' </td>';
str+='</tr>';
str+='</thead>';
str+='</table>';
str+='</span>';
str+='</td>';
str+='<td>';
str+='<span class="">';
str+='<table class="table table-bordered" style="width:250px">';
str+='<thead>';
str+='<tr>';
str+='<th  style="background-color:#EDEDED;"> Female Candidates : </th>';
str+='<td> '+result[0].femaleCount+' </td>';
str+='</tr>';
str+='</thead>';
str+='</table>';
str+='</span>';
str+='</td>';
str+='</tr>';
str+='</thead>';
str+='</table>';
	str+='</div>';
	//console.log(userAccessType);
	if(userAccessType != null && userAccessType == 'All') 
	{
		str += '<div>';
		str += '<table class="table table-bordered" style="border:2px solid #FC6 !important">';
		str += '<caption class="tablecaption" >Caste Category Wise Information';
		str += '<hr style="margin-top:0px;margin-bottom:0px;margin-right:50%;"/>';
		str += '</caption>';
		str += ' <thead>';
		str += ' <tr>';
		str += '<th width="25%">Caste Category</th>';
		str += '<th width="25%">Total Count</th>';
		str += '<th width="25%">Male</th>';
		str += '<th width="25%">Female</th>';
		str += '</tr>';
		str += '</thead>';
		for(var pr in result[0].casteGroupVO)
		{
			str += ' <tr>';
			str += '<td>'+result[0].casteGroupVO[pr].castName+'</td>';
			str += '<td>'+result[0].casteGroupVO[pr].casteId+'</td>';
			str += '<td>'+result[0].casteGroupVO[pr].stateId+'</td>';
			str += '<td>'+result[0].casteGroupVO[pr].castStateId+'</td>';
			str += '</tr>';
		}
		

		str += '</table>';
		str += '</div>';
		
	}
	
	str += '<div>';
	str += '<table class="table table-bordered" style="border:2px solid #FC6 !important">';
	str += '<caption class="tablecaption">Age Range Wise Information';
	str += '<hr style="margin-top:0px;margin-bottom:0px;margin-right:50%;"/>                </caption>';
	str += '<thead>';
	str += '<tr>';
	str += ' <th width="25%">Between Age</th>';
	str += '<th width="25%">Total Count</th>';
	str += '<th width="25%">Male</th>';
	str += '<th width="25%">Female</th>';
	str += '</tr>';
	str += '</thead>';
	for(var tp in result[0].ageDetailsIfoVO)
	{
		str += ' <tr>';
		str += '<td>'+result[0].ageDetailsIfoVO[tp].castName+'</td>';
		str += '<td>'+result[0].ageDetailsIfoVO[tp].casteId+'</td>';
		str += '<td>'+result[0].ageDetailsIfoVO[tp].stateId+'</td>';
		str += '<td>'+result[0].ageDetailsIfoVO[tp].castStateId+'</td>';
		str += '</tr>';
	}

	str += ' </table> ';
	str += '</div>';
	str+='<table class="table table-bordered" id="constiTableId">';
	str+='<thead>';
	//if(basicCmmtyId == 1)
		//str+='<th  style="width: 178px; padding-left: 34px;">Designation</th>';
	
	str+='<th style="width:50px;"> </th>';
	str+='<th style="padding-left: 72px;"> MEMBER </th>';
	//str+='<th style="padding-left: 19px;">MemberShipNo</th>';
	str+='<th style="padding-left: 19px;"> MOBILE NO </th>';
	str+='<th style="padding-left: 19px;"> AGE </th>';
	str+='<th style="padding-left: 19px;"> GENDER </th>';
	str+='<th style="padding-left: 19px;"> CASTE NAME </th>';
	str+='</thead>';
    for(var i in result){
	 str+='<tr>';
	/* if(basicCmmtyId == 1){
	 if(result[i].role!=null){
		str+='<td  style="padding-top: 14px; padding-left: 37px;">'+result[i].role+'</td>';
	 }else{
		str+='<td  style="padding-top: 14px; padding-left: 37px;">  </td>';
	 }
	 }*/
	 str+='<td><img  style="margin-top: 5px;" width="50"  height="50" src="images/cadre_images/'+result[i].imagePath+'" onerror="setDefaultImage(this);"/>';
	
	 str+=' </td>';
	 str+='<td> '+result[i].name+'';
	  if(basicCmmtyId == 1){
	 if(result[i].role!=null){
		 str+='<br>'+result[i].role+'';
	 }else{
		 str+='';
	 }
	 }	 
	  str+=' </td>';	  
	// str+='<td style="padding-top: 15px; padding-left: 15px;width:281px;">'+result[i].name+'</td>';
	 //str+='<td style="padding-left: 15px; padding-top: 13px;">'+result[i].membershipNo+'</td>';
	 str+='<td style="padding-left: 15px; padding-top: 13px;">'+result[i].mobileNo+'</td>';
	 str+='<td style="padding-left: 15px; padding-top: 13px;">'+result[i].age+' </td>';
	 str+='<td style="padding-left: 15px; padding-top: 13px;"> '+result[i].gender+' </td>';
	 str+='<td style="padding-left: 15px; padding-top: 13px;"> '+result[i].casteName+'('+result[i].casteGroupName+') </td>';
	 str+='</tr>';
	}
   str+='</tbody>';
   str+='</table>';
	str+=' <div class="modal-footer" style="margin-top: 50px;">';
	str+=' <button data-dismiss="modal" class="btn btn-default" type="button">Close</button>';
	str+='</div>';
	
   $("#cadreDetailsDiv").html(str);
   
   var areaType = "";
   if(locationTypeId != 8)
   {
	   areaType = "Village Committee Members";
   }
   //$('#dialogSummary').find('h3').html('<span>'+ capitalize(name)+' '+areaType+' '+ basicCmmtyName +' '+ capitalize(type) +' </span>');
   $('#dialogSummary').find('h3').html('<span>'+ capitalize(name)+' '+areaType+' ('+ basicCmmtyName +') </span>');
   $("#dialogSummary").modal("show");
  
  } 
  else{
        $("#cadreDetailsDiv").html("No Data Available.");
	}
	
	/*$("#constiTableId").dataTable({
			"iDisplayLength": 20,
			"aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
		});
	*/
}
}	
function capitalize(str) {

   // return string.charAt(0).toUpperCase() + string.slice(1).toLowerCase();
   var words = str.split(" ");
   var arr = Array();
   for (i in words)
   {
      temp = words[i].toLowerCase();
      temp = temp.charAt(0).toUpperCase() + temp.substring(1);
      arr.push(temp);
   }
   return arr.join(" ");
}	
</script>

<script>
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
function exportToExcel()
{
	  tableToExcel('constSummary', 'Constituency Committee Summary');
}
function getUserAccessInfo()
{
	$("#imgajax").show();
	var stateId = 0;
	$('#userAccessconstituencyId').find('option').remove();
	
	if ($('#stateId').length != null && $('#stateId').length != 0)
	 stateId = $("#stateId").val();
	var jObj ={
		stateId:stateId,
		task:"getConstituencies"
	}
	$.ajax({
		type: "GET",
		url: "getUserAccessAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jObj)},
		})
		.done(function( result ) {
			$("#imgajax").hide();
	$.each(result.hamletVoterInfo,function(index,value){
		$('#userAccessconstituencyId').append('<option value="'+value.id+'">'+value.name+'</option>');
	});
	$("#userAccessconstituencyId option[value="+accessConstituencyId+"]").attr('selected', 'selected');
});	

 
}
function reload() {
	accessConstituencyId = $("#userAccessconstituencyId").val();
	 window.location.href ='constituencyCommitteeSummaryAction.action?accessConstituencyId='+accessConstituencyId+'','location=no','_blank'; 
    
}

function getRolesBasedReport(roleId)
	{
		$('#constiRoleSummary').html("");
		$("#constiRoleSummry").show();
		var rolesArr = new Array();
		var casteCategoryArr = new Array();
		var casteCategoryGroupArr = new Array();
		var casteIdsArr = new Array();
		
		var locationLevelId =3;
		var committeeTypeId =$("#committetypeId").val();
		var userAccessType ="Constituency";
		var castePercentage =5;
		var searchTypeId =0;
		var selectedRadio = $("input[type='radio'][name='reportType']:checked").val();
		if(roleId == 0)
		{
			rolesArr.push(1);
			rolesArr.push(2);
			rolesArr.push(3);
			rolesArr.push(4);
			rolesArr.push(5);
			rolesArr.push(6);
			rolesArr.push(7);
			rolesArr.push(8);
			rolesArr.push(9);
		}
		else
		{
			rolesArr.push(roleId);
		}

		var jObj = {
			rolesArr : rolesArr,
			casteCategoryArr : casteCategoryArr,
			casteCategoryGroupArr : casteCategoryGroupArr,
			casteIdsArr : casteIdsArr,
			locationLevelId: locationLevelId,
			committeeTypeId : committeeTypeId,
			userAccessType : userAccessType,
			castePercentage:'${accessConstituencyId}',
			searchTypeId:searchTypeId,
			selectedRadio:selectedRadio
		};
		$.ajax({
          type:'GET',
          url: 'getCommitteeRolesBasedReport.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			  $("#constiRoleSummry").hide();
			if(result != null)
			{
				buildRoleWiseDetailsInfo(result);
			}
			else{
				$('#constiRoleSummary').html('No Data Available');
			}
		});
	}
	
	function buildRoleWiseDetailsInfo(result)
	{
		var totalResult = result.cadreCommitteeRolesInfoVOList;
			var str='';
		if(totalResult != null)
		{
			for(var i in totalResult)
			{
				str+='<div class="col-md-10 col-md-offset-1 widget" style="margin-top:10px;font-size: 16px;">';
				str+='<div class="col-md-4" >';
				str+='<div class="col-md-5" style="padding:5px;">Total Candidates</div>';
				str+='<div class="col-md-7" style="font-size:32px;padding:0px;display:inline-block">'+totalResult[i].totalCount+'</div>';
				str+='</div>';
				str+='<div class="col-md-4" style="border-left:1px solid #FFF" >';
				str+='<div class="col-md-5" style="padding:0px;">';
				str+='<i class="icon-male"></i>';
				str+='Male Candidates</div>';
				str+='<div class="col-md-7" style="font-size:32px;padding:0px;display:inline-block">'+totalResult[i].maleCount+'</div>';
				str+='</div>';
				str+='<div class="col-md-4" style="border-left:1px solid #FFF" >';
				str+='<div class="col-md-5" style="padding:0px;">';
				str+='<i class="icon-female"></i>';
				str+='Female Candidates</div>';
				var femaleCount = totalResult[i].femaleCount != null?totalResult[i].femaleCount:0;
				str+='<div class="col-md-7" style="font-size:32px;padding:0px;display:inline-block">'+femaleCount+'</div>';
				str+='</div>';
				str+='</div>';
			}			
		}
		
			str+='<div class="col-md-10 col-md-offset-1" style="margin-top:20px;">';
			str+='<div class="table-responsive">';
		var casteCategoryResult = result.casteCategoryWiseList;
		if(casteCategoryResult != null)
		{
			if(userAccessType != null && userAccessType == 'All')
			{
				str+='<table class="table table-bordered" style="border:2px solid #FC6 !important;margin-bottom:35px" id="casteCategoryId">';
				str+='<caption class="tablecaption" >Caste Category Wise Information';
				str+='<hr style="margin-top:0px;margin-bottom:0px;margin-right:50%;"/>';
				str+='</caption>';
				str+='<thead>';
				str+='<tr>';
				str+='<th width="25%">Caste Category</th>';
				str+='<th width="25%">Total Count</th>';
				str+='<th width="25%">Male</th>';
				str+='<th width="25%">Female</th>';
				str+='</tr>';
				str+='</thead>';
				for(var i in casteCategoryResult)
				{
					str+='<tr>';
					str+='<td>'+casteCategoryResult[i].casteCategory+'</td>';
					str+='<td>'+casteCategoryResult[i].totalCount+'</td>';
					str+='<td>'+casteCategoryResult[i].maleCount+'</td>';
					str+='<td>'+casteCategoryResult[i].femaleCount+'</td>';
					str+='</tr>';
				}
				str+='</table>';	
			}				
		}
			
		var casteWiseResult = result.casteWiseList;
		if(casteWiseResult != null)
		{
			if(userAccessType != null && userAccessType == 'All')
			{
				str+='<table class="table table-bordered" style="border:2px solid #FC6 !important" id="casteDetailsId">';
				str+='<caption class="tablecaption" >Caste Wise Information';
				str+='<hr style="margin-top:0px;margin-bottom:0px;margin-right:50%;"/>';
				str+='</caption>';
				str+='<thead>';
				str+='<tr>';
				str+='<th width="25%">Caste Name</th>';
				str+='<th width="25%">Total Count</th>';
				str+='<th width="25%">Male</th>';
				str+='<th width="25%">Female</th>';
				str+='</tr>';
				str+='</thead>';
				for(var i in casteWiseResult)
				{
					str+='<tr>';
					str+='<td>'+casteWiseResult[i].caste+'</td>';
						str+='<td>'+casteWiseResult[i].totalCount+'</td>';
						str+='<td>'+casteWiseResult[i].maleCount+'</td>';
						str+='<td>'+casteWiseResult[i].femaleCount+'</td>';
					str+='</tr>';
				}
				str+='</table>    ';
			}
		}	
		
		var ageRangeWiseResult = result.ageRangeWiseList;
		if(ageRangeWiseResult != null)
		{
			str+='<table class="table table-bordered" style="border:2px solid #FC6 !important" id="ageRangeID">';
			str+='<caption class="tablecaption">Age Range Wise  Information';
			str+='<hr style="margin-top:0px;margin-bottom:0px;margin-right:50%;"/>                </caption>';
			str+='<thead>';
			str+='<tr>';
			str+='<th width="25%">Between Age</th>';
			str+='<th width="25%">Total Count</th>';
			str+='<th width="25%">Male</th>';
			str+='<th width="25%">Female</th>';
			str+='</tr>';
			str+='</thead>';
			for(var i in ageRangeWiseResult)
			{
				str+='<tr>';
				str+='<td>'+ageRangeWiseResult[i].ageRange+'</td>';
				str+='<td>'+ageRangeWiseResult[i].totalCount+'</td>';
				str+='<td>'+ageRangeWiseResult[i].maleCount+'</td>';
				str+='<td>'+ageRangeWiseResult[i].femaleCount+'</td>';
				str+='</tr>';
			}
		}
			str+='</table> ';
			str+='</div>  '; 
			str+='</div>';

		$('#constiRoleSummary').html(str);
		
		
		$("#casteDetailsId").dataTable({
			"iDisplayLength": 15,
			"aaSorting": [[ 1, "desc" ]],
			"aLengthMenu": [[15,30, 50, 100, -1], [15,30, 50, 100, "All"]]
		});
		
		$("#casteCategoryId").dataTable({
			"iDisplayLength": 15,
			"aaSorting": [[ 1, "desc" ]],
			"aLengthMenu": [[15,30, 50, 100, -1], [15,30, 50, 100, "All"]]
		});
		
		$("#ageRangeID").dataTable({
			"iDisplayLength": 15,
			"aaSorting": [[ 1, "desc" ]],
			"aLengthMenu": [[15,30, 50, 100, -1], [15,30, 50, 100, "All"]]
		});
		
		$('#casteCategoryId_length,#ageRangeID_length').hide();
		$('#casteCategoryId_filter,#ageRangeID_filter').hide();
		$('#casteCategoryId_info,#ageRangeID_info').hide();
		$('#casteCategoryId_paginate,#ageRangeID_paginate').hide();
		
		$('#casteDetailsId_info').css('margin-bottom','35px');
	}
	
	getRolesBasedReport(0);
</script>
<script>
<c:if test="${pageAccessType == 'ALL'}">
 getUserAccessInfo();
 </c:if>
</script>
</body>
</html>