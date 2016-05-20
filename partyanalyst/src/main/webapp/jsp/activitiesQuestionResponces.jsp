<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Activity</title>
	<link href="dist/activity/css/bootstrap.min.css" rel="stylesheet"/>
	<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/TDP.gif">
	<link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
	<link href='dist/activity/css/activity.custom.css' rel='stylesheet' type='text/css'>
	<link href='dist/activity/Date/daterangepicker-bs3.css' rel='stylesheet' type='text/css'>
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>	
	<script src="dist/activity/js/bootstrap.min.js" type="text/javascript"></script> 
	<!--<script type="text/javascript" src="js/bootstrap.js" ></script> -->
	 <script src="dist/activity/Date/moment.min.js" type="text/javascript"></script>
	 <script src="dist/activity/Date/daterangepicker.js" type="text/javascript"></script>
	 
	
	 <!-- YUI Dependency files (Start) -->
	<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/button-min.js"></script> 	
	<script src="js/yahoo/resize-min.js"></script> 
	<script src="js/yahoo/layout-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
	
	<script type="text/javascript" src="js/json/json-min.js"></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/tabview-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	
	<!-- Skin CSS files resize.css must load before layout.css --> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css"> 
 	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css">
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/calendar.css"> 
 <script type="text/javascript" src="js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jQ_datatables/css/jquery.dataTables.css"/> 
	
	
<link href="dragAndDropPhoto/css/jquery.filer.css" type="text/css" rel="stylesheet" />
<link href="dragAndDropPhoto/css/themes/jquery.filer-dragdropbox-theme.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">	
<link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/>
	
 <style type="text/css">
	.m_top10{margin-top:10px}
	.m_top20{margin-top:20px}
	.input-g1 .form-control{border-radius:0px;border-left:0px}
	.input-g1 .input-group-addon{border-radius:0px;background:#fff;}
	.starMark{font:15px;color:red;}
	.navtabsCustom{border:0px}
	.navtabsCustom li{border:0px;}
	.navtabsCustom li a{border-radius:0px;color:#666 }
	.navtabsContent{background:#fff;padding:10px;border:1px solid #ddd}
	.bg_cc{background:#ccc}
	.or{padding:8px;border-radius:50%;border:1px solid #ddd;width:40px;}

.errorCls{color:red;font-size:12px;}
.prev, .next{min-width:44px !important}

 </style>
</head>
<body>
	
<div class="container">


	<div class="row">
   		<div class="col-md-12">
        	<div class="panel panel-default panel-custom">
            	<div class="panel-heading">
                	<h4 class="panel-title">ACTIVITIES RESPONCE REPORT
						<!--<span class="pull-right" >
							<div class="input-group col-md-12" style="margin-top:-8px">
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
								<input type="text" class="searchDateCls form-control" />
							</div>
						</span>-->
					</h4>
					
                </div>
                <div class="panel-body">
                	<div class="row">
                    	
                        <div class="col-md-12">
							<div class="row">
								<div class="col-md-12" id="ErrDiv" style="color:#E6211E;">
								</div>
							</div>
                        	<div class="row">
							<div class="col-md-4"><span class="starMark">*</span>
                                	<label>Activity Type</label>
                                    <s:select theme="simple" headerKey="0" headerValue="Select Activity Type" name="surveyType" id="activityTypeList" value="surveyTypeId" list="basicVO.panchayatVoterInfo" listKey="id" listValue="name" cssClass="input-block-level form-control"/>
                            </div>
							<div class="col-md-4"><span class="starMark">*</span>
								<label>Activity Level</label>
								<s:select theme="simple" headerKey="0" headerValue="Select Activity Level" name="surveyType" id="activityLevelList" value="surveyTypeId" list="idNameVOList" listKey="id" listValue="name" onchange="getActivityNames(this.value);" cssClass="input-block-level form-control"/>
							</div>
							<div class="col-md-4"><span class="starMark">*</span>
								<label> Activity Name </label>
								<select id="ActivityList" class="form-control" name="activityVO.activityLevelId">
									<option value="0"> Select Activity </option>
								</select>
							</div>
								<!--
                                <div class="col-md-12 m_top10">
                                	<label class="radio-inline">
                                    	<input type="radio">Constituency
                                    </label>
                                    <label class="radio-inline">
                                    	<input type="radio">Mandal/ Town / Division
                                    </label>
                                    <label class="radio-inline">
                                    	<input type="radio">Panchayat/ Ward
                                    </label>
                                </div>
								-->
								
							<div class="col-md-4"><span class="starMark">*</span>
								<label>Report Type</label>
								<select id="reportList" class="form-control">
								<option value="1"> District</option>
								<option value="2"> Constituency</option>
								</select>
							</div>
							<div class="col-md-4"><span class="starMark">*</span>
								<label> Questions </label>
								<select id="questnsListId" class="form-control">
								</select>
							</div>
								
							<!--<div class="col-md-4 m_top10" id="constituencyDivId"  style="display:none;"><span class="starMark">*</span>
								<label>Constituency</label>
								<select id="constiList" class="form-control" onchange="getMunciMandalsList(this.value)" name="activityVO.constituencyId" >
								</select><span > <img src="images/ajaxImg2.gif" style="width:20px;margin-top:-20px;margin-left:-25px;display:none;" id="procesingImg3"></span>
							</div>
							<div class="col-md-4 m_top10" id="mandalDivId" style="display:none;">
								<label >Mandal/ Town/ Division</label>
								<select id="mandalsList" class="form-control" onchange="getPanchayatWardByMandal(this.value);">
									<option value="0"> Mandal/ Town/ Division</option>
								</select>
								<span > <img src="images/ajaxImg2.gif" style="width:20px;margin-top:-20px;margin-left:-25px;display:none;" id="procesingImg"></span>
							</div>
							<div class="col-md-4 m_top10" id="panchayatDivId" style="display:none;">
								<label>Panchayat/ Ward</label>
								<select id="villageWardsList" class="form-control">
									<option value="0"> Select Panchayat/ Ward</option>
								</select>
							</div>-->
							</div>
							<div class="row">
							<div class="col-md-3 m_top10 col-md-offset-4">
								<button id="searchId" class="btn btn-block btn-custom btn-success" type="button" onclick="getOptionDetailsForQuestion();">GET REPORT</button>
							</div>
							<div class="col-md-12">
							<button class="btn btn-success pull-right" id="lcnExcelBtn" onclick="generateExcel('responseTab')" style="display:none;">Export Excel</button>
							</div>
							</div>
							
							<div class="panel panel-default m_top10" id="questionDetailsDivId" style="display:none;">
							  <div class="panel-heading">
								<h4 class="panel-title"> QUESTION WISE REPORT </h4>
							  </div>
							  <div class="panel-body">
								<div id="questionWiseDetailsDiv"></div>
							  </div>
							</div>
							
						<div class="row  m_top10" id="optnsCntDiv" style="display:none;">
							<div class="col-md-12">
								<div class="bg_66" style="padding:10px 15px;background:#663300;color:#fff">
									<h4 class="panel-title" id="actvtyQstnOptnHdng" style="font-weight:bold;display:none;">ACTIVITY QUESTION OPTION RESPONCE</h4>
								</div>
							</div>
							<div class="col-md-12">
								<div  id="optionsCntId">
								</div>
							</div>
						</div>
						<div id="actvtyQstnOptnExclId" style="display:none;"></div>
							</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
	
	
</div>

<div id="dialogSummaryDistsrict" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">

		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				
				 <div class="modal-header">
				  <button aria-label="Close" data-dismiss="modal" class="close" type="button"><span aria-hidden="true">x</span></button>
					<h3 class="panel-header text-center"></h3>
				  </div>
					<div id="cadreDetailsDiv" style="margin-top:25px;padding:10px;"></div>
					<center><img class="text-center" id="dataLoadingImg" src="images/Loading-data.gif" style="display:none;"/></center>
			</div>
		</div>
    </div>

	<!-- questions modal start-->
	
	<div class="modal fade" id="questionsModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog modal-lg" role="document">
		<div class="modal-content" style="border-radius:0px;">
		  <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h4 class="modal-title" id="myModalLabel">Questionnaire</h4>
		  </div>
		  <div class="modal-body">
			<div id="questionsDivBodyId"></div>
		  </div>
		  <div id="errMsg" style="color:green;margin:20px;" class="errMsgCls"></div>
		  <div class="modal-footer" id="questionsDivFooterId">
			<!--<button type="button" id="saveResult" class="btn btn-custom btn-success">Save</button>-->
		  </div>
		</div>
	  </div>
	</div>
	 <div id="excelData" style="display:none;"></div>
	
	<!-- questions modal end -->
	
					
</div>						
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title imgTitleCls myModalLabel" id="myModalLabel" ></h4>
      </div>
      <div class="modal-body">
       <div id="imagesDiv" class="row"></div>
	   <div id="paginationDiv"></div>
      </div>
      <div class="modal-footer">
        
        <button type="button" class="btn btn-primary deleteBtnCls" style="display:none">Delete</button>
      </div>
    </div>
  </div>
</div>
<!-- Modal -->
<div class="modal fade" id="viewCommentsBlock" tabindex="-1" role="dialog">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Question Option Comments</h4>
      </div>
      <div class="modal-body">
	  <div id="commentsDiv">
	  
	  </div>
	  </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script src="dist/activity/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/activity/js/custom.js" type="text/javascript"></script>
<script src="dist/activity/Date/moment.min.js" type="text/javascript"></script>
<script src="dist/activity/Date/daterangepicker.js" type="text/javascript"></script>
<script type="text/javascript" src="dragAndDropPhoto/js/jquery.filer.js?v=1.0.5"></script>
<script type="text/javascript" src="dragAndDropPhoto/js/custom-ver1.js?v=1.0.5"></script>
<script type="text/javascript" src="dragAndDropPhoto/js/uploadImage.js"></script>
<script type="text/javascript" src="js/simplePagination/simplePagination.js" ></script>
<script>
$(document).ready(function(){
	//$('.searchDateCls').daterangepicker();
	$('#activityTypeList').val(1);
	$('#activityLevelList').val(1);
	getActivityNames(1);
	$('.applyBtn').click(function(){
		
		/*var startDate = $("input[name=daterangepicker_start]").val();
		var endDate =  $("input[name=daterangepicker_end]").val();*/
	var startDate = "";
	var endDate = "";
	var dates=$('.searchDateCls ').val();
		if(dates != null && dates.trim().length > 0){
			var dateArray=dates.split("to");
		  startDate=dateArray[0];
		  endDate=dateArray[1];
		}
		getLocationDetailsForActivity(startDate,endDate);
		//alert(startDate);
	});
});
function getActivityNames(id)
{
	$('#mandalsList').find('option').remove();
	$('#mandalsList').append('<option value="0"> Select Mandal/Town/Division</option>');
	$('#villageWardsList').find('option').remove();
	$('#villageWardsList').append('<option value="0"> Select Mandal/Town/Division</option>');
	
	$("#constituencyDivId").hide();
	$("#mandalDivId").hide();
	$("#panchayatDivId").hide();
	$("#districtDivId").hide();
	$("#constiList").val(0);
	$('#ActivityList').find('option').remove();
	$('#ActivityList').append('<option value="0"> Select Activity </option>');	
	
	var activityLevelId = id;
	if(id == 0)
		activityLevelId = $('#activityLevelList').val();
	
	if($("#activityTypeList").val()!=4){
		if(activityLevelId == 1){
		$("#constituencyDivId").show();
		$("#mandalDivId").show();
		$("#panchayatDivId").show();
		}
		else if(activityLevelId == 2){
			$("#constituencyDivId").show();
			$("#mandalDivId").show();
		}else if(activityLevelId == 5){
			$("#districtDivId").show();
		}
		else if(activityLevelId == 3 ){
			$("#districtDivId").show();
		}
	}
	
	
	var jObj = {
			activityTypeId : $('#activityTypeList').val(),
			activityLevelId:$('#activityLevelList').val(),
			task:"activityDetails"
		};
		
		$.ajax({
          type:'GET',
          url: 'getActivityDetails.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){			
			if(result != null && result.length >0)
			{
				for(var i in result)
					$('#ActivityList').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');	
				
				$('#ActivityList').val(12);
				//$('#ActivityList').trigger('change');
			}
			getQuestionsForReportTypeAction();
		});
		
}
$(document).on("change","#activityTypeList",function(){
		if($(this).val()==4){
			$("#searchId").hide();
		}else{
			$("#searchId").show();
		}
		getActivityNames(0);
	});

	$('#ActivityList').change(function(){
		getQuestionsForReportTypeAction();
	});
function getQuestionsForReportTypeAction(){
var activityScopeId = $("#ActivityList").val();
$('#questnsListId').find('option').remove();
$('#questnsListId').append('<option value="0"> All </option>');
	 var jsObj=
	   {				
		  scopeId:activityScopeId,
		  task:""				
		}
		$.ajax({
				  type:'GET',
				  url: 'getQuestionsForReportTypeAction.action',
				  dataType: 'json',
				  data: {task:JSON.stringify(jsObj)}
		   }).done(function(result){
			  // console.log(result)
			   				for(var i in result)
					$('#questnsListId').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
		   });
	}
	
function getOptionDetailsForQuestionn(){
	$('#optnsCntDiv').hide();
     var activityScopeId = $("#ActivityList").val();
	 var reportType = $("#reportList").val();
	 var reportText = $("#reportList option:selected").text();
	 var questionId = $("#questnsListId").val();
	 var jsObj=
	   {				
		  scopeId:activityScopeId,
		  reportType:reportType,
		  questionId:questionId,
		  task:""				
		}
		$.ajax({
				  type:'GET',
				  url: 'getOptionDetailsForQuestionAction.action',
				  dataType: 'json',
				  data: {task:JSON.stringify(jsObj)}
		   }).done(function(result){
			   if(result != null && result.length >0){
				   $("#lcnExcelBtn").show();
			   buildOptionsCount(result,reportText);
			   }
		   });
	}
	function buildOptionsCount(result,reportText){
		$("#actvtyQstnOptnHdng").show();
		$("#optionsCntId").html("");
		var str = '';
		str+='<div class="table-responsive">';
		str+='<table class="table table-bordered table-condensed" style="background:#fff;margin-ytop:15px;" id="optionsTableId">';
		var optnindx = 0;
		for(var i in result){
			for(var x in result[i].optionsList){
				if(optnindx == 0){
					str+='<tr>';
				str+='<td style="width:150px;font-weight:bold;">'+reportText+' Name</td>';
				}
				if(i==0){
				str+='<td style="width:100px;font-weight:bold;">'+result[i].optionsList[x].constincyName+'</td>';//option name
				}
				if(optnindx < x)
					str+='</tr>';
				optnindx++;
			}
			str+='<tr>';
			str+='<td style="width:150px;font-weight:bold;" id='+result[i].constincyId+' class="constituncyCls">'+result[i].constincyName+'</td>';
			for(var x in result[i].optionsList){
				str+='<td class="text-center" style="width:100px;">'+result[i].optionsList[x].count+'';
				if(result[i].optionsList[x].optionTypeId > 0){
				str+=' ('+result[i].optionsList[x].optionTypeId+'  <img src="images/edit.png"  style="cursor:pointer;" attr_consncy_id='+result[i].constincyId+' title="Click Here to View Comments" class="optnCommentsCls">)';	
				}
				str+='</td>';
			}
			str+='</tr>';
		}
		str+='</table>';
		str+='</div>';
		$("#optionsCntId").html(str);
		 $('#optnsCntDiv').show();
      excelReportForOptionsCnt(result,reportText);
	}
	function excelReportForOptionsCnt(result,reportText){
		var exlStr = '';
		exlStr+='<div class="table-responsive">';
		exlStr+='<table class="table table-bordered table-condensed" style="background:#fff" id="optionsTableId">';
		var optnindx = 0;
		for(var i in result){
			for(var x in result[i].optionsList){
				if(optnindx == 0){
					exlStr+='<tr>';
					exlStr+='<td style="width:150px;">'+reportText+' Name</td>';
				}
				if(i==0){
					exlStr+='<td style="width:100px;">'+result[i].optionsList[x].constincyName+'</td>';//option name
				}
				if(optnindx < x)
					exlStr+='</tr>';
					optnindx++;
			}
			exlStr+='<tr>';
			exlStr+='<td style="width:150px;">'+result[i].constincyName+'</td>';
			for(var x in result[i].optionsList){
				exlStr+='<td class="text-center" style="width:100px;">'+result[i].optionsList[x].count+'</td>';
			}
			exlStr+='</tr>';
		}
		exlStr+='</table>';
		exlStr+='</div>';
		$("#actvtyQstnOptnExclId").html(exlStr);	
	}
	function generateExcel(divId){
	tableToExcel(divId, 'Activity Question Option Responce');
}
</script>
<script>
var tableToExcel = (function() {
   var uri = 'data:application/vnd.ms-excel;base64,'
    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--><meta http-equiv="content-type" content="text/plain; charset=UTF-8"/></head><body><table>{table}</table></body></html>'
    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
	return function(table, name) {
    if (!table.nodeType) table = document.getElementById(table)
    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
    window.location.href = uri + base64(format(template, ctx))
  }
})()

$(document).on("click",".optnCommentsCls",function(){
	 var levelId = $("#activityLevelList").val();	
     var activityScopeId = $("#ActivityList").val();
	 var reportType = $("#reportList").val();
	 var questionId = $("#questnsListId").val();
	 var reportTypeId = $(this).attr("attr_consncy_id");
	
	 var jsObj= {				
		  scopeId:activityScopeId,
		  reportType:reportType,
		  questionId:questionId,
		  levelId  :levelId,
		  reportTypeId : reportTypeId,
		  task:""				
		}
		$.ajax({
				  type:'GET',
				  url: 'getCommentDetailsAction.action',
				  dataType: 'json',
				  data: {task:JSON.stringify(jsObj)}
		   }).done(function(result){
			  
				 //console.log(result);
				 buildCommentsDetails(result)
			   
		   });
});
function buildCommentsDetails(result){
	var str ='';
	var excelDivid = "excelData";
	if(result != null && result.length >0){
	str+='<button class="btn btn-success pull-right" id="cmntsExcl" onclick="generateExcel(\'excelData\')">Export Excel</button>';
	str+='<table class="table table-bordered "  style="margin-top:15px;">';
	str+='<thead>';
	str+='<th style="font-weight:bold;">District</th>';
	str+='<th style="font-weight:bold;">Constituency</th>';
	str+='<th style="font-weight:bold;">Mandal/Municipality</th>';
	str+='<th style="font-weight:bold;">Village/Ward</th>';
	str+='<th style="font-weight:bold;">Comments</th>';
	str+='</thead>';
	str+='<tbody>';
	
	for(var i in result){
	str+='<tr>';
	str+='<td style="font-weight:bold;">'+result[i].districtName+'</td>';
	str+='<td style="font-weight:bold;">'+result[i].constincyName+'</td>';
	str+='<td style="font-weight:bold;">'+result[i].tehsilName+'</td>';
	if(result[i].panchayatName != ""){
	str+='<td style="font-weight:bold;">'+result[i].panchayatName+'</td>';
	}
	str+='<td style="font-weight:bold;">'+result[i].optnCommnt+'</td>';
	str+='</tr>';
	}			
	
	str+='</tbody>';
	str+='</table>';
	}else{
		str+='No Data Available';
	}
	$("#commentsDiv").html(str);
	$("#viewCommentsBlock").modal('show');
	excelCommentDetails(result);
}
function excelCommentDetails(result){
	var str ='';
	if(result != null && result.length >0){
	
	str+='<table class="table table-bordered">';
	str+='<thead>';
	str+='<th style="font-weight:bold;">District</th>';
	str+='<th style="font-weight:bold;">Constituency</th>';
	str+='<th style="font-weight:bold;">Mandal/Municipality</th>';
	str+='<th style="font-weight:bold;">Village/Ward</th>';
	str+='<th style="font-weight:bold;">Comments</th>';
	str+='</thead>';
	str+='<tbody>';
	
	for(var i in result){
	str+='<tr>';
	str+='<td>'+result[i].districtName+'</td>';
	str+='<td>'+result[i].constincyName+'</td>';
	str+='<td>'+result[i].tehsilName+'</td>';
	if(result[i].panchayatName != ""){
	str+='<td>'+result[i].panchayatName+'</td>';
	}
	str+='<td>'+result[i].optnCommnt+'</td>';
	str+='</tr>';
	}			
	
	str+='</tbody>';
	str+='</table>';
	}else{
		str+='No Data Available';
	}
	$("#excelData").html(str);
}


function getOptionDetailsForQuestion(){
getActivityQuestionInfo();
$('#optnsCntDiv').html('');
	var activityLevelIdsArr=[];
	activityLevelIdsArr.push($('#activityLevelList').val());
	var questionArr=[];
	
	if($('#questnsListId').val() != 0)
		questionArr.push($('#questnsListId').val());
	  else
	  {
		$('#questnsListId option').each(function(){
				   questionArr.push(this.value);
		});

	  }
  
		var jObj = {
				stateId:1,
				activityScopeId:$('#ActivityList').val(),
				searchType:$('#reportList option:selected').text().trim(),
				activityLevelIdsArr:activityLevelIdsArr,
				questionArr:questionArr
			};		
			$.ajax({
				  type:'GET',
				  url: 'getActivityQuestionnnairWiseReportAction.action',
				 data : {task:JSON.stringify(jObj)} ,
			 }).done(function(result){	
				console.log(result);
				buildQuestionResponseTable(result);
				//$("#buildActivityReasonReportTableId").show();
			 });
}

function buildQuestionResponseTable(result){
	
	if(result != null && result.sublist1 != null && result.sublist1.length>0){
		var colspancount=2;
		if(result.sublist1[0].sublist1 != null && result.sublist1[0].sublist1.length>0){
			colspancount = colspancount+parseInt(1)*2;
		}
		if(result.sublist1[0].sublist2 != null && result.sublist1[0].sublist2.length>0){
			colspancount = colspancount+parseInt(result.sublist1[0].sublist2.length);
		}	
		
		var str='';
		str+='<table id="responseTab" class="table table-condensed table-bordered">';
		str+='<thead>';
		str+='<tr>';
		str+='<th colspan="'+colspancount+'" style="text-align:center;"> '+result.name+'</th>';
		str+='</tr>';
		str+='<tr>';
		str+='<th rowspan="2" style="text-align:center;"> LOCATION  </th>';
		str+='<th rowspan="2" style="text-align:center;"> TOTAL  </th>';
		if(result.sublist1[0].sublist1 != null && result.sublist1[0].sublist1.length>0)
			for(var i in result.sublist1[0].sublist1){
				if(i==0)
					str+='<th colspan="2" style="text-align:center;"> '+result.sublist1[0].sublist1[i].name+' </th>';
			}
		
		if(result.sublist1[0].sublist2 != null && result.sublist1[0].sublist2.length>0)
			for(var i in result.sublist1[0].sublist2){
				str+='<th rowspan="2" style="text-align:center;"> '+result.sublist1[0].sublist2[i].name+' </th>';
			}
			
		str+='</tr>';
		str+='<tr>';
		//str+='<th rowspan="2"> LOCATION NAME  </th>';
		if(result.sublist1[0].sublist1 != null && result.sublist1[0].sublist1.length>0)
			for(var i in result.sublist1[0].sublist1){
				if(i==0){
					str+='<th style="text-align:center;"> CALLED </th>';
					str+='<th style="text-align:center;"> PENDING </th>';
				}
			}			
		str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
			for(var i in result.sublist1){
				str+='<tr>';
				str+='<td>'+result.sublist1[i].name+'</td>';
				str+='<td>'+result.sublist1[i].totalCount+'</td>';
				for(var j in result.sublist1[i].sublist1){
					//str+='<td>'+result.sublist1[i].sublist1[j].totalCount+'</td>';
					if(j==0){
					str+='<td style="text-align:center;">'+result.sublist1[i].sublist1[j].called+'</td>';
					str+='<td style="text-align:center;">'+result.sublist1[i].sublist1[j].pending+'</td>';
					}
				}
				for(var j in result.sublist1[i].sublist2){
					//str+='<td>'+result.sublist1[i].sublist1[j].totalCount+'</td>';
					str+='<td style="text-align:center;">'+result.sublist1[i].sublist2[j].totalCount+'</td>';
				}
				str+='</tr>';
			}
		str+='</tbody>';
	str+='</table>';
	$('#optnsCntDiv').html(str);
	
	$('#responseTab').dataTable({
		"iDisplayLength": 20,
		"aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
		
	});
	 $('#optnsCntDiv').show();
	  $("#lcnExcelBtn").show();
	}	
}

function getActivityQuestionInfo(){
	$("#questionWiseDetailsDiv").html("");
	
	var questionArr=[];
	if($('#questnsListId').val() != 0)
		questionArr.push($('#questnsListId').val());
	  else
	  {
		$('#questnsListId option').each(function(){
				   questionArr.push(this.value);
		});
	  }
  
	var jObj = {
		activityScopeId:$('#ActivityList').val(),
		activityLevelId:$('#activityLevelList').val(),
		questionIds:questionArr
	};		
	$.ajax({
		  type:'GET',
		  url: 'getActivityLocationInfoDetailsByActivityScopeAction.action',
		 data : {task:JSON.stringify(jObj)} ,
	 }).done(function(result){
		if(result != null && result.length > 0)
			buildQuestionsDetails(result);
		else{
			$("#questionDetailsDivId").show();
			$("#questionWiseDetailsDiv").html("NO DATA AVAILABLE...");
		}	
	});
}

function buildQuestionsDetails(result){
	var str='';
	
	str+='<table class="table table-condensed table-bordered">';
		str+='<thead>'
			str+='<th> QUESTION </th>'
			str+='<th> TOTAL </th>'
			str+='<th> AP </th>'
			str+='<th> TS </th>'
		str+='</thead>'
		str+='<tbody>'
			for(var i in result){
				str+='<tr>'
					str+='<td>'+result[i].question+'</td>';
					if(result[i].desTotalCount > 0){
						str+='<td>'+result[i].totalCount+' ('+result[i].desTotalCount+')</td>';
						str+='<td>'+result[i].APCount+' ('+result[i].desAPCount+')</td>';
						str+='<td>'+result[i].TSCount+' ('+result[i].desTSCount+')</td>';
					}
					else{
						str+='<td>'+result[i].totalCount+'</td>';
						str+='<td>'+result[i].APCount+'</td>';
						str+='<td>'+result[i].TSCount+'</td>';
					}
				str+='</tr>'
			}
		str+='</tbody>'
	str+='</table>'
	
	$("#questionDetailsDivId").show();
	$("#questionWiseDetailsDiv").html(str);
}
</script>
</body>
</html>