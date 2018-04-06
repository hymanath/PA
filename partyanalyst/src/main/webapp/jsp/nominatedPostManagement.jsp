<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Nominated post management</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/NominatedPost/custom.css" rel="stylesheet" type="text/css">
<link href='https://fonts.googleapis.com/css?family=Roboto:400,500,700,700italic,900,900italic,400italic,500italic,300italic,300,100italic,100' rel='stylesheet' type='text/css'>
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="js/nominatedPosts/nominatedPostManagement.js" type="text/javascript"></script>
<style type="text/css">
.tableShort thead th:nth-child(1)
{
	width:10% !important
}
.tableShort thead th:last-child
{
	width:7% !important
}
.filterIcon
{
	background:#ddd;
	padding:4px;
	border-radius:50%;
	cursor:pointer;
	top:-3px
}
.font_17{font-size:16px;}
.tableFontSize thead th {font-size:12px; !important;font-weight:normal !important}
.tableFontSize tr td{font-size:12px; !important;font-weight:normal !important}
.font_weight{font-weight:bold}
.text-center{text-align:center};
</style>
</head>
<body >
<div class="container">
    <div class="row">
	 <div class="col-md-12 col-xs-12 col-sm-12">
		<ul class="geoGrpahicBreadCrumb">
				<li style="text-transform: uppercase; font-weight: 500;" data-placement="bottom" data-toggle="tooltip" title="Nominated Posts Overview Details"><a href="nominatedPostApplicationReviewAction.action"><i class="glyphicon glyphicon-home" style="color:#fff;"></i></a></li>
				<li id="flowHeading" style="text-transform: uppercase; font-weight: 500;"></li>
		</ul>
        </div>
	  <div class="col-md-12 col-xs-12 col-sm-12 m_top10">
	  <h3 class="text-capital" id="headinggId" style="color:#5C2D25;display:none"></h3>
		<h3 class="text-capital headingColor" ><i class="pull-right glyphicon glyphicon-filter filterBtn filterIcon" title="Select Locations" ></i></h3>
		<div class="row">
			<div class="col-md-12 col-xs-12 col-sm-12 filterSection">
				<div class="panel panel-default">
					<div class="panel-heading" style="background:#fff;border:0px;">
						<h4 class="panel-title">Select Location</h4>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-3 col-xs-12 col-sm-3" id="stateMainId" >
								<label>State</label>
								<select class="form-control" id="stateId">
									<option value="0">All</option>
									<option value="1">Andhra Pradesh</option>
									<option value="36">Telangana</option>
								</select>
							</div>
							<div class="col-md-3 col-xs-12 col-sm-3" style="display:none" id="districtMainId">
								<label>District</label>
								<span id="districtIdImg"><img src="images/search.gif"/></span>
								<select class="form-control" id="districtId">
									<option value="0">Select District</option>
								</select>
							</div>
							<div class="col-md-3 col-xs-12 col-sm-3" style="display:none" id="constituencyMainId">
								<label>Constituency</label>
								<span id="constituencyIdImg"><img src="images/search.gif"/></span>
								<select class="form-control" id="constituencyId">
									<option value="0">Select Constituency</option>
								</select>
							</div>
							<div class="col-md-3 col-xs-12 col-sm-3" style="display:none" id="manTowDivMainId">
								<label>Mandal/Town/Division</label>
									<span id="manTowDivIdImg"><img src="images/search.gif"/></span>
								<select class="form-control" id="manTowDivId">
									<option value="0">Select Mandal/Town/Division</option>
								</select>
							</div>
							<div class="col-md-3 col-xs-12 col-sm-3" style="display:none" id="villageWardDivMainId">
								<label>Village/Ward</label>
									<span id="villWardDivIdImg"><img src="images/search.gif"/></span>
								<select class="form-control" id="villageWardId">
									<option value="0">Select Village/Ward</option>
								</select>
							</div>
							<div class="col-md-3 col-xs-12 col-sm-3">						
								<input type="button" class="btn btn-primary btn-sm" value="Submit" style="margin-top: 25px;" id="locationWiseDataId"/>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="anyDeptCorTblId" class=" filterSectionIconCls"></div>
		<!--<div class="row" id="anyPostDivId" style="display:none;"> 
			<div class="col-md-12 col-xs-12 col-sm-12">
				<div class="panel panel-default">
					<div class="" style="padding: 12px;">
						<p class="text-capital headingColor filterBtnCls font_17" style="cursor:pointer;">
							<b><span id="anyDeptHeadingId"></span></b>
							<i class="pull-right glyphicon glyphicon-chevron-down " style="cursor:pointer;font-size:12px;"></i>
						</p>
					</div>
					<div class="panel-body">
						<div id="anyDeptCorTblId" class=" filterSectionIconCls"></div>
					</div>
				</div>
			</div>
		</div>-->
	  </div>
	</div>
	<div class="row m_top20">
		<div class="col-md-12 col-xs-12 col-sm-12">
			<div class="panel panel-default">
				<!--<div class="panel-heading">
					<h4 class="panel-title text-capital headingColor"><span id="headinggId"></span></h4>
				</div>-->
				<div class="panel-body">
					<center><img src="images/Loading-data.gif" id="departmentsBuildSearchId" style="display:none;width:50px;height:50px;margin:auto;"/></center>
				<div id="departmentsBuildId" class="col-md-12 col-xs-12 col-sm-12"></div>
				</div>
			</div>
		</div>
	</div>
			<!--<button  class="btn btn-default pull-right filterBtn"><i class="glyphicon glyphicon-filter filterIcon"></i></button>-->
		
</div>
<div class="modal fade" tabindex="-1" id="newApplyPost" role="dialog">  
	<div class="modal-dialog" style="width:80%;">      
		<div class="modal-content">
			<div class="modal-body">
				<button type="button" class="close" data-dismiss="modal" onclick="refreshingPage();" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				 <iframe  style="width:100%;border:0px" class="newPostApplyPopup"></iframe> 
			</div>
		</div>
	</div>
</div>
<div class="modal fade" tabindex="-1" id="readyToFinalReviewDiv" role="dialog">  
	<div class="modal-dialog" style="width:60%;">      
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">Move postion to final review </h4>
			</div>
			<div class="modal-body" id="readyToFinalRevieId">
			
			</div>
			<div class="modal-footer">
				<span class="pull-left" style="color:red;" id="modelErrId"></span>
				<span class="pull-left" style="color:green;" id="modelSuccessId"></span>
			
				<!--<span id="updateSearchId" style="display:none"><img src="images/search.gif"/></span>-->
				<button type="button" class="btn btn-primary" id="readyToFinalRevewBtn"> Update </button>
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
var globalLevelId = '${param.lId}';
var globalStateId = '${param.stId}'; 
var globalStatus='${param.sts}';
var globalLvlTxt='${param.levelTxt}';
if(globalLvlTxt == "central"){
	globalStateId =0;
}else{
	globalStateId = '${param.stId}'; 
}
var windowUrl = window.location.href;
var wurl = windowUrl.substr(0,(windowUrl.indexOf("/nominatedPostManagementAction")));
wurl = wurl.replace("/PartyAnalyst","");

var getHeight = $(window).height();
getHeight = (getHeight -( ( getHeight/100 ) * 10));

$(".newPostApplyPopup").css("height",getHeight)
$(document).on("click",".appleNewPostBtn",function(){
	$(".newPostApplyPopup").attr("src",wurl+"/nominatedPostProfileAction.action?status=openedInPopup");
	setTimeout(function(){
		$("#newApplyPost").modal('show');
	}, 1000);
});
if(globalStatus !=null && globalStatus.trim().length>0){	
	$(document).prop('title', capitalizeFirstLetter(globalStatus.toLowerCase())+" nominated post management");
}
function capitalizeFirstLetter(stringStr){
	var returnStmt = stringStr.charAt(0).toUpperCase() + stringStr.slice(1);
	if(returnStmt=="Notyet"){
		returnStmt = "Ready To ShortList";
	}
	return returnStmt;	
}

//$(document).prop('title', globalStatus+" Nominated Post Management");
/* var globalLevelId = 2;
var globalStateId = 1; */

$("#stateId").val(globalStateId);

var grlobalDistrictArr=[];
var globalAssmblyArr=[];
var globalMandalTowDivArr=[];
var globalVillageWardDivArr=[];
$(document).ready(function() {
	if(globalStatus == "notYet"){
		$("#headinggId").html("yet to start "+globalLvlTxt+" level - board/corporation");
		$("#flowHeading").html(""+globalLvlTxt.substr(0,1).toUpperCase()+globalLvlTxt.substr(1)+" Level - Board/Corporation &mdash; ready to shortlist");
	}else if(globalStatus == "notRecieved "){
		$("#headinggId").html("Not Received "+globalLvlTxt+" level - board/corporation");
		$("#flowHeading").html(""+globalLvlTxt.substr(0,1).toUpperCase()+globalLvlTxt.substr(1)+" Level - Board/Corporation &mdash; NOT RECEIVED");
	}	
	else{
		$("#headinggId").html(globalStatus+"  - "+globalLvlTxt+" level  - board/corporation");
		$("#flowHeading").html(globalLvlTxt.substr(0,1).toUpperCase()+globalLvlTxt.substr(1)+" Level - Board/Corporation <span>	&mdash;</span> "+globalStatus.substr(0,1).toUpperCase()+globalStatus.substr(1)+"");
	}
		
	if(globalLevelId !=null && globalLevelId !="" && globalLevelId !=1 && globalLevelId !=2){
		getDistrictsForStates(globalStateId);
	}else{
		$("#districtMainId").hide();
		$("#constituencyMainId").hide();
		$("#manTowDivMainId").hide();
		$("#villageWardDivMainId").hide();
	}	
	$(".filterSection").hide();
	//$(".filterSectionIconCls").hide();
	setTimeout(function(){ $( "#locationWiseDataId" ).trigger( "click" ); }, 1000);
	if(globalLevelId ==1){
	$("#anyDeptHeadingId").html(" <u style='text-decoration: none;'> Any Department - Any Corporation/Board <small style='color:#76514C;'>(Central Level)</small> </u> ");	
	}else if(globalLevelId == 2){
	$("#anyDeptHeadingId").html(" <u style='text-decoration: none;'> Any Department - Any Corporation/Board  <small style='color:#76514C;'>(State Level)</small> </u>");	
	}else if(globalLevelId == 3){
	$("#anyDeptHeadingId").html(" <u style='text-decoration: none;'> Any Department - Any Corporation/Board  <small style='color:#76514C;'>(District Level)</small> </u>");	
	}else if(globalLevelId == 4){
	$("#anyDeptHeadingId").html(" <u style='text-decoration: none;'> Any Department - Any Corporation/Board  <small style='color:#76514C;'>(Assembly Level)</small> </u>");	
	}else {
	$("#anyDeptHeadingId").html(" <u style='text-decoration: none;'> Any Department - Any Corporation/Board <small style='color:#76514C;'>(Mandal/Muncipality/Corporation Level)</small> </u>");	
	}
	//getAnyDeptApplicationOverviewCountLocationWise();
});
$(document).on("click",".filterBtn",function(){
	$(".filterSection").toggle("slow");
	//$(".filterSectionIconCls").toggle("slow");
	//$(".filterSectionIconCls").hide();
});
$(document).on("click",".filterBtnCls",function(){
	//$(".filterSectionIconCls").toggle();
	$(this).closest(".panel").find(".panel-body").toggle();
	$(this).find("i").toggleClass("glyphicon-chevron-down").toggleClass("glyphicon-chevron-up");
});
</script>
</body>
</html>