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
	<form method="POST" enctype="multipart/form-data" name="uploadInsureeDetailsForm" action="saveActivityDetailsAction.action">
<div class="container">


	<div class="row">
   		<div class="col-md-12">
        	<div class="panel panel-default panel-custom">
            	<div class="panel-heading">
                	<h4 class="panel-title">SEARCH TO UPDATE PROGRAM ACTIVITIES 
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
                    	<div class="col-md-3">
                        	<img src="img/searchicon.png" style="border-right:1px solid #00B17D">
                        </div>
                        <div class="col-md-9">
							<div class="row">
								<div class="col-md-9" id="ErrDiv" style="color:#E6211E;">
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
								<select id="districtList" class="form-control">
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
								<button id="searchId" class="btn btn-block btn-custom btn-success" type="button" onclick="getOptionDetailsForQuestion();">SEARCH</button>
							</div>
							</div>
								<div class="m_top10 col-md-12" id="optionsCntId">
								</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
	</form>
	
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
	
function getQuestionsForReportTypeAction(){
var activityScopeId = $("#ActivityList").val();
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
	
function getOptionDetailsForQuestion(){
     var activityScopeId = $("#ActivityList").val();
	 var reportType = $("#districtList").val();
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
			   buildOptionsCount(result);
			   }
		   });
	}
	function buildOptionsCount(result){
		$("#optionsCntId").html("");
		var str = '';
		str+='<div class="table-responsive">';
		str+='<table class="table table-bordered table-condensed" style="background:#fff" id="optionsTableId">';
		var optnindx = 0;
		for(var i in result){
			for(var x in result[i].optionsList){
				if(optnindx == 0){
					str+='<tr>';
				str+='<td style="width:150px;"></td>';
				}
				if(i==0){
				str+='<td style="width:100px;">'+result[i].optionsList[x].constincyName+'</td>';//option name
				}
				if(optnindx < x)
					str+='</tr>';
				optnindx++;
			}
			str+='<tr>';
			str+='<td style="width:150px;">'+result[i].constincyName+'</td>';
			for(var x in result[i].optionsList){
				str+='<td class="text-center" style="width:100px;">'+result[i].optionsList[x].count+'</td>';
			}
			str+='</tr>';
		}
		str+='</table>';
		str+='</div>';
		$("#optionsCntId").html(str);
	}
</script>
</body>
</html>