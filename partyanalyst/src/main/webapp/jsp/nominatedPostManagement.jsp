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
<title>NOMINATED POST PROFILE CREATION</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/NominatedPost/custom.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
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
</style>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-md-12 col-xs-12 col-sm-12">
			<h3 class="text-capital headingColor"><span id="headinggId"></span><i class="pull-right glyphicon glyphicon-filter filterBtn filterIcon"></i></h3>
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
										<option value="1">Andhara Pradesh</option>
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
								<div class="col-md-3 col-xs-12 col-sm-3">						
									<input type="button" class="btn btn-primary btn-sm" value="Submit" style="margin-top: 25px;" id="locationWiseDataId"/>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-12 col-xs-12 col-lg-12 col-sm-12 m_top20">
				<center><img src="images/Loading-data.gif" id="departmentsBuildSearchId" style="display:none;width:50px;height:50px;margin:auto;"/></center>
				<div id="departmentsBuildId"></div>
			</div>
		</div>
	</div>
			<!--<button  class="btn btn-default pull-right filterBtn"><i class="glyphicon glyphicon-filter filterIcon"></i></button>-->
		
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
/* var globalLevelId = 2;
var globalStateId = 1; */

$("#stateId").val(globalStateId);

var grlobalDistrictArr=[];
var globalAssmblyArr=[];
var globalMandalTowDivArr=[];
$(document).ready(function() {
	if(globalStatus == "notYet")
		$("#headinggId").html("yet to start nominated post details");
	else
		$("#headinggId").html(globalStatus+" nominated post details");
	if(globalLevelId !=null && globalLevelId !="" && globalLevelId !=1 && globalLevelId !=2){
		getDistrictsForStates(globalStateId);
	}else{
		$("#districtMainId").hide();
		$("#constituencyMainId").hide();
		$("#manTowDivMainId").hide();
	}	
	$(".filterSection").hide();
	setTimeout(function(){ $( "#locationWiseDataId" ).trigger( "click" ); }, 1000);
});
$(document).on("click",".filterBtn",function(){
	$(".filterSection").toggle("slow");
});

</script>
</body>
</html>