<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Committee Detailed Analysis</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">	
	<link href="dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="js/exportexcel.js"></script>
	<script type="text/javascript" src="js/jquery.dataTables.js"></script>
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 

	<style>
	.table-bordered > thead > tr > th,
	.table-bordered > tbody > tr > th,
	.table-bordered > tfoot > tr > th,
	.table-bordered > thead > tr > td,
	.table-bordered > tbody > tr > td,
	.table-bordered > tfoot > tr > td { border: 1px solid #cfcfcf;}
	body{background:#E5E5E5}
	.bg_fff
	{
		background-color:#fff !important;
	}
	.m_top25{margin-top:25px;}
	</style>
	
</head>

<body>

	<div class="container">
		<div class="row" style="text-align:center;">
			<div class="col-md-6 col-md-offset-3 col-xs-8 col-xs-offset-2 col-sm-6 col-sm-offset-3 ">
				<h3 class="panel-header">COMMITTEE DETAILED ANALYSIS</h3>
				<hr style="border-color:#F00;margin:0px 0px 10px 0px;">
			</div>
		</div>
		<div class="row" style="background:#ffffff;padding:15px;">
		<div id="errMsgDiv"></div>
			<div class="col-md-3" id="levelDiv">
				<label><b>Committee Level:</b></label>
				<select class="form-control" id="levelId" onchange="clearAllFields();">
					<option value="2">State</option>
					<option value="3">District</option>
					<option value="5">Town/Mandal/Division</option>
					<option value="6">Village/Ward</option>
				</select>
			</div>
			<div class="col-md-3" id="stateDiv">
				<label><b>Select State:</b></label>
				<select class="form-control" id="stateId" onchange="getDistrictsForState(this.value);">
					<option value="0">All</option>
					<option value="1">Andhra Pradesh</option>
					<option value="36">Telangana</option>
				</select>
			</div>
			<div class="col-md-3" id="districtDiv">
				<label><b>Select District:</b></label>
				<select class="form-control" id="districtId" onchange="getConstituenciesForDistrict(this.value);">
					
				</select>
			</div>
			<div class="col-md-3" id="constituencyDiv">
				<label><b>Select Constituency:</b></label>
				<select class="form-control" id="constituencyId" onchange="getMandalsForConstituency(this.value);">
					<option value="0">Select Constituency</option>
				</select>
			</div>
			<div class="col-md-3" id="mandalDiv">
				<label><b>Select Mandal/Municipality:</b></label>
				<select class="form-control" id="mandalId" onchange="getPanchayatsForMandal(this.value);">
					<option value="0">Select Mandal/Municipality</option>
				</select>
			</div>
			<div class="col-md-3" id="panchayatDiv">
				<label><b>Select Panchayat:</b></label>
				<select class="form-control" id="panchayatId">
					<option value="0">Select Panchayat</option>
				</select>
			</div>
			
			<div class="col-md-12" style="text-align:center">
				<div style="display:inline-block; margin-right: 25px;">
				<h5 style="margin-bottom:5px;border-bottom:1px solid #F00;text-align:center;margin:0px 15px 5px 15px" class="text-success"> COMMITTEE LEVEL </h5>
				<span class="btn btn-success btn-mini form-inline">
					<label class="radio"><input type="checkbox" checked="true" name="selectCheck" value="village" class="scopeRd" style="vertical-align: text-bottom;" id="completedId">&nbsp; Completed &nbsp;</label>
				</span>&nbsp;&nbsp;
				<span class="btn btn-success btn-mini form-inline">
					<label class="checkbox"><input type="checkbox" name="selectCheck" value="mandal" class="scopeRd" style="vertical-align: text-bottom;" id="startedId">&nbsp; Started &nbsp;&nbsp;</label>
				</span>
				
				
				<span id="districtCommDiv" class="btn btn-success btn-mini form-inline">
					<label class="checkbox"><input type="checkbox" name="selectCheck" value="districtComm" class="scopeRd" style="vertical-align: text-bottom;" id="notYetStartedId">&nbsp; Not Yet Started &nbsp;&nbsp;</label>
				</span>
				</div>
				<div style="display:inline-block">
				<h5 style="margin-bottom:5px;border-bottom:1px solid #F00;text-align:center;margin:0px 15px 5px 15px" class="text-success"> CONSIDER AFFILIATED COMMITEES </h5>
				<span id="considerAffDiv" class="btn btn-success btn-mini form-inline  ">
					<label class="checkbox"><input type="checkbox" name="selectCheck" class="scopeRd" style="vertical-align: text-bottom;" id="considerAfflId">&nbsp; WITH ALL AFFILIATED COMMITTEES &nbsp;&nbsp;</label>
				</span>
				</div>
			</div>
			<div class="col-md-4 col-md-offset-5 m_top25">
				<button class="btn btn-success">Get Details</button>
			</div>
		</div>
		<div style="display:inline-block; margin-top:10px;text-align:center;" class="col-md-12">
            <h4 id="headingId" style="display:inline-block" class="text-danger">DISTRICT WISE COMMITTEES</h4>
			<span class="btn btn-info pull-right excelId form-inline" onclick="exportToExcel()" "display:inline-block;"> Export To Excel </span>
		</div>
		<div class="row">
			<div class="col-md-12 col-xs-12 col-md-12">
				<center><img id="summaryAjax" style="width:80px;height:50px;"  src="./images/Loading-data.gif" alt="Processing Image"/></center>
				<div id="distSummaryBody" style="overflow-x:scroll;">							
				</div>
			</div>
		</div>
	</div>

<script type="text/javascript">
  $(".eventsheader").find(".span2").removeClass("span2")
  $(".eventsheader").find(".span1").removeClass("span1")
  $(".eventsheader").find(".span3").removeClass("span3")
  $(".eventsheader").find(".span5").removeClass("span5")

  function getDistricts(){
     var jsObj=
		{				
				stateId:1,
				elmtId:"districtList_d",
                type:"default",
				task:"findDistrictsForAState"				
		}
    $.ajax({
          type:'GET',
          url: 'getDistrictsForAStateAjaxAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   location.reload(); 
	   }
     for(var i in result){
	   if(result[i].id == 0){
          $("#districtId").append('<option value='+result[i].id+'>All</option>');
	   }else{
	      $("#districtId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }
	 }
   });
  }
  
    function getDistrictsForState(state){
   
	   var jsObj=
	   {				
					stateId:state,
					elmtId:"districtList_d",
					type:"default",
					task:"getDistrictsForState"				
		}
		$.ajax({
			  type:'GET',
			  url: 'getDistrictsForStateAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
	   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
			   location.reload(); 
		   }
		   $("#districtId").empty();
		 for(var i in result){
		   if(result[i].id == 0){
			  $("#districtId").append('<option value='+result[i].id+'>All</option>');
		   }else{
			  $("#districtId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		   }
		 }
	   });
  }

    function getConstituenciesForDistrict(district){
		var jsObj=
	   {				
					districtId:district,
					elmtId:"districtList_d",
					type:"default",
					task:"getConstituenciesForDistricts"				
		}
		$.ajax({
			  type:'GET',
			  url: 'getConstituenciesForADistrictAjaxAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
	   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
			   location.reload(); 
		   }
		   $("#constituencyId").empty();
		 for(var i in result){
		   if(result[i].id == 0){
			  $("#constituencyId").append('<option value='+result[i].id+'>All</option>');
		   }else{
			  $("#constituencyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		   }
		 }
	   });
  }
  
    function getMandalsForConstituency(constiId){
      $("#mandalId option").remove();
	  if(constiId == 0){
	    return;
	  }
      var jsObj=
		{
			id:constiId,
			task:"subRegionsInConstituency",
			taskType:"",
			selectElementId:"",
			address:"",
			areaType:"null",
			constId:constiId				
		}
    $.ajax({
          type:'GET',
          url: 'locationsHierarchiesAjaxAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   location.reload(); 
	   }
     for(var i in result){
	   if(result[i].id == 0){
          $("#mandalId").append('<option value='+result[i].id+'>All</option>');
	   }else{
	      $("#mandalId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }
	 }
   });	
  }
  
      function getPanchayatsForMandal(mandalId){
		
		$("#panchayatId").find('option').remove();
		  if(mandalId == 0)
		  {
			return;
		  }
		var jsObj={
			mandalId :mandalId
		}
		$.ajax({
			type:"POST",
			url :"getPanchayatDetailsAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			 if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   location.reload(); 
			}
		for(var i in result){
	   if(result[i].id == 0){
          $("#panchayatId").append('<option value='+result[i].id+'>All</option>');
	   }else{
	      $("#panchayatId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }
	 }
   });	
  }
  
  function clearAllFields()
  {
	  //alert("hiiiiii");
	  $("#constituencyId").append('<option value="0">Select Constituency</option>');
	  $("#mandalId").append('<option value="0">Select Mandal/Municipality</option>');
	  $("#panchayatId").append('<option value="0">Select Panchayat</option>');
  }
  
function getDistrictWiseCommittesSummary(){
	$(".excelId").hide();
	$("#distSummaryBody").html('');
	$("#summaryAjax").show();
	//$("#districtCommDiv").show();
	//$("#stateCommDiv").show();
	var state = state; 
	var mandalCheck=  $('#mandalId').is(':checked')?"true":"false";
	var villageCheck=  $('#villageId').is(':checked')?"true":"false";
	//var districtCommCheck =  $('#districtCommId').is(':checked')?"true":"false";
	
	/*var selected = $("input[type='radio'][name='selectstate']:checked");
	if (selected.length > 0) {
		state = selected.val();
	}*/
	var districtCommCheck = true;
	/*if(districtCommCheck == "true"){
		$("#constiRdId").attr('disabled', true);		
	}
	else{
	$("#constiRdId").attr('disabled', false);	
	}*/
	var startDate=$(".dp_startDate").val();
	var endDate=$(".dp_endDate").val();
	
	var jObj = {
		startDate:'02/02/2015',
		endDate:'06/24/2015',
		state:'AP',
		districtCommCheck:districtCommCheck,
		mandalCheck:mandalCheck,
		villageCheck:villageCheck
	}
			
	$.ajax({
	  type:'GET',
	  url: 'getDistrictWiseCommittesSummaryAction.action',
	  data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		$("#summaryAjax").hide();
		if(typeof result == "string"){
			if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
			  location.reload(); 
			}
		}
	
		if(result == null){
			$(".excelId").hide();
			$("#distSummaryBody").html("<td style='text-align:center' colspan='13'><h4> NO DATA AVAILABLE </h4></td>");
			return;
		}
		
		buildResultDistrictSummary(result,mandalCheck,villageCheck,districtCommCheck,jObj);	
					
	});
}

function buildResultDistrictSummary(result,mandalCheck,villageCheck,districtCommCheck,jObj){
	
	var districtInfoArr = [];
	var villageInfoArr = [];
	
	var mandTotal =0; 	
	var mandStarted =0; 	
	var mandCompleted =0; 	
	var mandMembers =0; 	
	var mandAfStarted =0; 	
	var mandAfCompleted =0;
	var panTotal =0; 	
	var panStarted =0; 	
	var panCompleted =0; 	
	var panMembers =0; 	
	var panAffStarted =0; 	
	var panAffCompleted =0;
	var distTotal =0; 	
	var distStarted =0; 	
	var distCompleted =0; 	
	var distMembers =0; 	
	var distAffStarted =0; 	
	var distAfStarted =0; 	
	var distAffCompleted =0;
	var percentage = 0;
	var perc = 0;
	
	var yuvathaStartedCount = 0;
	var mahilaStartedCount = 0;
	var rythuStartedCount = 0;
	var othersStartedCount = 0;
	var yuvathaCompltdCount = 0;
	var mahilaCompltdCount = 0;
	var rythuCompltdCount = 0;
	var othersCompltdCount = 0;
	
	
	
	var bcCellStartedCount = 0;
	var scCellStartedCount = 0;
	var stCellStartedCount = 0;
	var minorityStartedCount = 0;
	var CristianStartedCount = 0;
	var tnsfStartedCount = 0;
	var tntucStartedCount = 0;
	var tsnvStartedCount = 0;
	var legalCellStartedCount = 0;
	var doctorStartedCount = 0;
	var kalluGeethaStartedCount = 0;
	var chenethaStartedCount = 0;
	var rakshaVedikaStartedCount = 0;
	var tnusStartedCount = 0;
	var commercialStartedCount = 0;
	var culturalStartedCount = 0;
	var tradeStartedCount = 0;
	var  tradeCmpltdCount = 0;
	var  bcCellCmpltdCount = 0;
	var  scCellCmpltdCount = 0;
	var  stCellCmpltdCount = 0;
	var  minorityCmpltdCount = 0;
	var  CristianCmpltdCount = 0;
	var  tnsfCmpltdCount = 0;
	var  tntucCmpltdCount = 0;
	var  tsnvCmpltdCount = 0;
	var  legalCmpltdCount = 0;
	var  doctorCmpltdCount = 0;
	var  kalluGeethaCmpltdCount = 0;
	var  chenethaCmpltdCount = 0;
	var  rakshaVedikaCmpltdCount = 0;
	var  tnusCmpltdCount = 0;
	var  commercialCmpltdCount = 0;
	var  culturalCmpltdCount = 0;	

	
	var dstctyuvathaStartedCount = 0;
	var dstctmahilaStartedCount = 0;
	var dstctrythuStartedCount = 0;
	var dstctothersStartedCount = 0;
	var dstctyuvathaCompltdCount = 0;
	var dstctmahilaCompltdCount = 0;
	var dstctrythuCompltdCount = 0;
	var dstctothersCompltdCount = 0;
	
	
	
	var dstctbcCellStartedCount = 0;
	var dstctscCellStartedCount = 0;
	var dstctstCellStartedCount = 0;
	var dstctminorityStartedCount = 0;
	var dstctCristianStartedCount = 0;
	var dstcttnsfStartedCount = 0;
	var dstcttntucStartedCount = 0;
	var dstcttsnvStartedCount = 0;
	var dstctlegalCellStartedCount = 0;
	var dstctdoctorStartedCount = 0;
	var dstctkalluGeethaStartedCount = 0;
	var dstctchenethaStartedCount = 0;
	var dstctrakshaVedikaStartedCount = 0;
	var dstcttnusStartedCount = 0;
	var dstctcommercialStartedCount = 0;
	var dstctculturalStartedCount = 0;
	var dstcttradeStartedCount = 0;
	var  dstcttradeCmpltdCount = 0;
	var  dstctbcCellCmpltdCount = 0;
	var  dstctscCellCmpltdCount = 0;
	var  dstctstCellCmpltdCount = 0;
	var  dstctminorityCmpltdCount = 0;
	var  dstctCristianCmpltdCount = 0;
	var  dstcttnsfCmpltdCount = 0;
	var  dstcttntucCmpltdCount = 0;
	var  dstcttsnvCmpltdCount = 0;
	var  dstctlegalCmpltdCount = 0;
	var  dstctdoctorCmpltdCount = 0;
	var  dstctkalluGeethaCmpltdCount = 0;
	var  dstctchenethaCmpltdCount = 0;
	var  dstctrakshaVedikaCmpltdCount = 0;
	var  dstcttnusCmpltdCount = 0;
	var  dstctcommercialCmpltdCount = 0;
	var  dstctculturalCmpltdCount = 0;	
	
	$("#headingId").html("DISTRICT WISE COMMITTEES");
	$(".excelId").show();
	$("#distSummaryBody").html("<td style='text-align:center' colspan='13'><h4> DATA AVAILABLE </h4></td>");
	
	var str = '';
	
	str+='<table class="table table-bordered"id="districtTableId" style="width:1150px !important;">';
	//alert(districtCommCheck);
	if(districtCommCheck == true )
	{
		//alert("1111");
		str+='<thead>';
		str+='<tr>';
		str+='<th rowspan="4" style="text-align:center">District No</th>';
		str+='<th rowspan="4" style="text-align:center">District Name</th>';
		str+='<th style="text-align:center" colspan="15">DISTRICT LEVEL </th>';
		str+='</tr>';
		str+='<tr role="row">';
		str+='<th class="mainCls" colspan="4" style="text-align:center;" rowspan="1"> Main Committees </th>';
		str+='<th class="affilCls" colspan="11" style="text-align:center;" rowspan="1"> Affiliated Committees </th>';
		str+='</tr>';
		str+='<tr>';
		str+='<th rowspan="2" class="mainCls" >Total</th>';
		str+='<th rowspan="2" class="mainCls" >Started</th>';
		str+='<th rowspan="2" class="mainCls" >Completed</th>';
		str+='<th rowspan="2" class="mainCls" >Members</th>';
		str+='<th rowspan="2" class="affilCls" >Total</th>';
		str+='<th rowspan="2" class="affilCls"> Started </th>';
		str+='<th rowspan="2" class="affilCls"> Completed  </th>';
		str+='<th colspan="2"  class="affilCls" > Telugu Yuvatha  </th>';
		str+='<th colspan="2"  class="affilCls"> Telugu Mahila </th>';
		str+='<th colspan="2"  class="affilCls"> Telugu Rythu </th>';
		str+='<th colspan="2"   class="affilCls"> Others </th>';
		str+='<tr>';
		str+='<th class="affilCls">Started</th>';
		str+='<th class="affilCls">Completed</th>';
		str+='<th class="affilCls">Started</th>';
		str+='<th class="affilCls">Completed</th>';
		str+='<th class="affilCls">Started</th>';
		str+='<th class="affilCls">Completed</th>';
		str+='<th class="affilCls">Started</th>';
		str+='<th class="affilCls">Completed</th>';
		str+='<tbody>';
		
		
		
		
		for(var i in result){
			//alert("sssss");
			if(result[i].townMandalDivisionVO != null || result[i].villageWardVO != null || result[i].districtCommVO != null){
				//alert("rrrr");
			str += '<tr id='+result[i].districtId+' class="removeCls clearCls'+result[i].districtId+'">';
			str+='<td class="removeCls clearClsTD'+result[i].districtId+'" style="text-align:center;">'+result[i].districtId+'';
			str+='</td>';
			
            str += '<td style="color:#333333;font-weight:bold;">'+result[i].districtName+'&nbsp;&nbsp;</td>';
			
            //str += '<td><a onclick="getConstituencyWiseCommittesSummaryForSubLevel(\''+jObj.startDate+'\',\''+jObj.endDate+'\',\''+jObj.state+'\',\''+jObj.mandalCheck+'\',\''+jObj.villageCheck+'\',\''+result[i].districtId+'\',\''+result[i].districtName+'\');" style="cursor:pointer;">'+result[i].districtName+'</a></td>';
			
			
			//alert("aaaaa");
			if(result[i].districtCommVO != null){
				if(result[i].districtCommVO.totalCommittees!=null){
					str += '<td style="text-align:center" >'+result[i].districtCommVO.totalCommittees+'</td>';
					distTotal=distTotal+result[i].districtCommVO.totalCommittees;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].districtCommVO.mainStarted!=null){
					//str += '<td>'+result[i].districtCommVO.mainStarted+'<span id="mini-pie-chart-district'+i+'" class="pull-right mini-pie-chart-district"></span></td>';
					str += '<td style="text-align:center" >'+result[i].districtCommVO.mainStarted+'</td>';
					distStarted=distStarted+result[i].districtCommVO.mainStarted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].districtCommVO.mainCompleted!=null){
					str += '<td style="text-align:center" >'+result[i].districtCommVO.mainCompleted+'</td>';
					distCompleted=distCompleted+result[i].districtCommVO.mainCompleted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].districtCommVO.membersCount!=null){
					str += '<td style="text-align:center" >'+result[i].districtCommVO.membersCount+'</td>';
					distMembers=distMembers+result[i].districtCommVO.membersCount;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				if(result[i].districtCommVO.totalCommittees!=null){
					str += '<td style="text-align:center" >'+((result[i].districtCommVO.totalCommittees)*20)+'</td>';					
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				if(result[i].districtCommVO.afflStarted!=null){
					str += '<td style="text-align:center" >'+result[i].districtCommVO.afflStarted+'</td>';
					distAffStarted=distAffStarted+result[i].districtCommVO.afflStarted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].districtCommVO.afflCompleted!=null){
					str += '<td style="text-align:center" >'+result[i].districtCommVO.afflCompleted+'</td>';
					distAffCompleted=distAffCompleted+result[i].districtCommVO.afflCompleted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].districtCommVO.youvathaStarted != null){
					str += '<td style="text-align:center" >'+result[i].districtCommVO.youvathaStarted+'</td>';
					dstctyuvathaStartedCount = dstctyuvathaStartedCount+result[i].districtCommVO.youvathaStarted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].districtCommVO.youvathaCmpltd!=null){
					str += '<td style="text-align:center" >'+result[i].districtCommVO.youvathaCmpltd+'</td>';
					dstctyuvathaCompltdCount = dstctyuvathaCompltdCount+result[i].districtCommVO.youvathaCmpltd;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].districtCommVO.mahilaStarted != null){
					str += '<td style="text-align:center" >'+result[i].districtCommVO.mahilaStarted+'</td>';
					dstctmahilaStartedCount = dstctmahilaStartedCount+result[i].districtCommVO.mahilaStarted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].districtCommVO.mahilaCmpltd!=null){
					str += '<td style="text-align:center" ><'+result[i].districtCommVO.mahilaCmpltd+'</td>';
					dstctmahilaCompltdCount = dstctmahilaCompltdCount+result[i].districtCommVO.mahilaCmpltd;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				
				if(result[i].districtCommVO.rythuStarted != null){
					str += '<td style="text-align:center;">'+result[i].districtCommVO.rythuStarted+'</td>';
						dstctrythuStartedCount = dstctrythuStartedCount+result[i].districtCommVO.rythuStarted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].districtCommVO.rythuCmpltd!=null){
					str += '<td style="text-align:center" >'+result[i].districtCommVO.rythuCmpltd+'</td>';
						dstctrythuCompltdCount = dstctrythuCompltdCount+result[i].districtCommVO.rythuCmpltd;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
			
					if(result[i].districtCommVO.othersStarted != null){
						str += '<td style="text-align:center" >'+result[i].districtCommVO.othersStarted+'</td>';
						dstctothersStartedCount = dstctothersStartedCount+result[i].districtCommVO.othersStarted;
					}else{
						str += '<td style="text-align:center" > - </td>';
					}
					
					if(result[i].districtCommVO.othersCmpltd != null){
						str += '<td style="text-align:center" >'+result[i].districtCommVO.othersCmpltd+'</td>';
						dstctothersCompltdCount = dstctothersCompltdCount+result[i].districtCommVO.othersCmpltd;
					}else{
						str += '<td style="text-align:center" > - </td>';
					}
				
			}else{
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				if(isConsiderAffl == "true")
				{
					for(var z=0;z<17;z++)
					 {
						str += '<td>  </td>';
						str += '<td>  </td>';
					 }
				}
				else
				{
					str += '<td>  </td>';
					str += '<td>  </td>';
				}
			}
		
			
			
			str += '</tr>';
			
			if(result[i].townMandalDivisionVO != null){
				var details = [];
				if(result[i].townMandalDivisionVO.mainStarted != null){
					details = [result[i].townMandalDivisionVO.totalCommittees, result[i].townMandalDivisionVO.mainStarted];
				}else{
					details = [result[i].townMandalDivisionVO.totalCommittees, 0];
				}
				
				districtInfoArr.push(details);
			}else{
				 var details = [0, 0];
				districtInfoArr.push(details);
			}
			
			
			if(result[i].villageWardVO != null){
				var details = [];
				if(result[i].villageWardVO.mainStarted != null){
					details = [result[i].villageWardVO.totalCommittees, result[i].villageWardVO.mainStarted];
				}else{
					details = [result[i].villageWardVO.totalCommittees, 0];
				}
				//var villageDetails  = [result[i].villageWardVO.totalCommittees, result[i].villageWardVO.mainStarted];
				villageInfoArr.push(details);
			}else{
				var villageDetails  = [0, 0];
				villageInfoArr.push(villageDetails);
			}
			}
		}
		
		
		
		str += '<tfoot>';
		str += '<tr>';
		
		str	+= '<td style="text-align:center"></td>';
		str	+= '<td style="text-align:center">TOTAL</td><td >'+distTotal+'</td>'; 			
		str += '<td style="text-align:center" >'+distStarted+'</td>'; 	
		str += '<td style="text-align:center" >'+distCompleted+'</td>'; 	
		str += '<td style="text-align:center" >'+distMembers+'</td>'; 
		str += '<td style="text-align:center">'+(distTotal * 20 )+'</td>';			
		str += '<td style="text-align:center">'+distAffStarted+'</td>'; 	
		str += '<td style="text-align:center">'+distAffCompleted+'</td>';
		
		str += '<td style="text-align:center" >'+dstctyuvathaStartedCount+'</td>';
		str += '<td style="text-align:center" >'+dstctyuvathaCompltdCount+'</td>';
		str += '<td style="text-align:center" >'+dstctmahilaStartedCount+'</td>';
		str += '<td style="text-align:center" >'+dstctmahilaCompltdCount+'</td>';
		str += '<td style="text-align:center" >'+dstctrythuStartedCount+'</td>';
		str += '<td style="text-align:center" >'+dstctrythuCompltdCount+'</td>';
		str += '<td style="text-align:center" >'+dstctothersStartedCount+'</td>';
		str += '<td style="text-align:center" >'+dstctothersCompltdCount+'</td>';
		str += '</tr>';
		str += '</tfoot>';
		str+='</table>';
		
		$("#distSummaryBody").html(str);
		$(".excelId").show();
		$("#districtTableId").dataTable({
			"iDisplayLength": 50,
			"aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
		});
		
	}
} 
  
</script>

<script>

getDistricts();
getDistrictWiseCommittesSummary();

</script>
</body>
</html>