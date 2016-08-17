<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<title>BOARD WISE NOMINATED POSTS</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/NominatedPost/custom.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
.tableShort thead th:nth-child(1)
{
	width:10% !important
}
.tableShort thead th:last-child
{
	width:7% !important
}
</style>
</head>
<body>
<div class="container">
	<div class="row">
	<div class="col-md-12 col-xs-12 col-lg-12 col-sm-12 m_top20">
		<ul class="geoGrpahicBreadCrumb">
				<li style="text-transform: uppercase; font-weight: bold;" data-placement="bottom" data-toggle="tooltip" title="Nominated Posts Overview Details"><a href="nominatedPostApplicationReviewAction.action"><i class="glyphicon glyphicon-home" style="color:#fff;"></i></a></li>
				<li  style="text-transform: uppercase; font-weight: bold;cursor:pointer" class="redirectPageCls" id="flowHeading" data-placement="bottom" data-toggle="tooltip" title="Click here to go Nominated Post Management Page"></li>
				<li  style="text-transform: uppercase; font-weight: bold;">SHORTLISTING</li>
		</ul>
	</div>
    	<div class="col-md-12 col-xs-12 col-lg-12 col-sm-12 m_top20">
		 <ol class="breadcrumb text-capital" id="headLvlDeptId" style="margin-bottom: -10px;margin-left: -13px;"></ol>
        	<h3 class="headingColor"><small><span id="headBrdId" style="text-transform:uppercase;margin-bottom: 10px;"></span></small> - <span id="headPosId" style="text-transform:uppercase"></span> </h3>
           
        	<div class="panel panel-default panelDepartmentHead" style="margin-top: 10px;">
            	<div class="panel-body" style="background-color:#f4f4f4;">
                	<div class="table-responsive" id="positionDivId"></div>
                </div>
            </div>
        </div>
        <div class="col-md-12 col-xs-12 col-sm-12 col-lg-12">
        	<div class="panel panel-default panelDepartmentHead">
            	<div class="panel-heading">
                	<h4 class="panel-title"  id="postMembersId">APPLIED THIS POST - MEMBERS DETAILS</h4>
                </div>
                <div class="panel-body" style="background-color:#f4f4f4;">
                	<div class="">
						<div id="resultDivId"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" tabindex="-1" id="pdfModelId" role="dialog">  
	<div class="modal-dialog" style="width:80%;">      
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">CADRE REPORT DETAILS</h4>
			</div>
			<div class="modal-body" id="pdfReportDetailsId">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" tabindex="-1" id="referModelId" role="dialog">  
	<div class="modal-dialog" style="width:60%;">      
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">REFERENCE DETAILS</h4>
			</div>
			<div class="modal-body">
				<div  id="referenceDetailsId" class="table-responsive"></div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script type="text/javascript">
var globalLevelTxt = '${param.levelTxt}';
var globalDeptName = '${param.deptName}'; 
var headBrdId='${param.brdName}';
var globalPosName='${param.posName}';
var globalStats='${param.sts}';

var globalPositionId = parseInt('${param.positionId}');
var globalDeptId = parseInt('${param.deptId}');
var globalBoardId = parseInt('${param.boardId}');

var globalStatusMainName = '${param.stN}';
var globalLevelId = parseInt('${param.lId}');
var globalStatusId = parseInt('${param.stId}');
var globalSearchLevelId = parseInt('${param.searchLevelId}');
var globalSearchLevelValue = parseInt('${param.searchLevelValue}');


	if(globalStatusMainName == "notYet"){
		$("#flowHeading").html(""+globalLevelTxt.substr(0,1).toUpperCase()+globalLevelTxt.substr(1)+" Level - Board/Corporation &mdash; Yet to start");
	}
	else{
		$("#flowHeading").html(globalLevelTxt.substr(0,1).toUpperCase()+globalLevelTxt.substr(1)+" Level - Board/Corporation <span>	&mdash;</span> "+globalStatusMainName.substr(0,1).toUpperCase()+globalStatusMainName.substr(1)+"");
	}
	
if(globalPositionId == 0)
	globalStats =" <b class='text-success'  style='text-transform:uppercase;' >  ANY </b> POST   "+globalPosName+" ";
else if(globalStats == "readyToShortList")
	globalStats =" SHORTLISTING  <b class='text-success'  style='text-transform:uppercase;' >  "+globalPosName+"  POST </b>";
else if(globalStats == "positionLink")
	globalStats =" Linking  <b class='text-success'  style='text-transform:uppercase;' >  "+globalPosName+"  POST </b>";

$("#headPosId").html(globalStats+" ");
$("#headBrdId").html(headBrdId+" board");

$("#headLvlDeptId").html("<li>"+globalLevelTxt+" level </li> <li> "+globalDeptName+" department</li>");
$('[data-toggle="tooltip"]').tooltip();
/*var globalDistrictArr=[];
var globalAssmblyArr=[];
var globalMandalTownDivArr=[];
var globalLocationLevelValueArr =[];
var globalLocationLevel=4;
var globalLocationLevelId = 3;
var globalDepartmentId = 0 ;
var globalBoardId = 0;
var globalPositionId = 0;*/

$('.chosenSelect').chosen();
$(document).on("click",".btnPopup",function(e){
	$(".updateDropDown").hide();
	$(this).closest('tr').find(".updateDropDown").show();
	e.stopPropagation()
});
$(document).on("click",".btnPopupAny",function(e){
	$(".updateDropDownAny").hide();
	$(this).closest('tr').find(".updateDropDownAny").show();
	e.stopPropagation()
});
$(document).on("click",".btnPopupThisAny",function(e){
	$(".updateDropDownThisAny").hide();
	$(this).closest('tr').find(".updateDropDownThisAny").show();
	e.stopPropagation()
});
$(document).on("click",function(){
	$(".updateDropDown").hide();
	$(".appliedPostPopup").hide();
});
$(document).on("click",".updateDropDown,.appliedPostPopup",function(e){
	e.stopPropagation()
});
$(document).on("click",".closeIcon",function(e){
	$(this).closest(".updateDropDown").hide();
});
$(document).on("click",".appliedCount",function(e){
	$(".appliedPostPopup").hide();
	$(this).closest('tr').find(".appliedPostPopup").show();
	e.stopPropagation();
	var candidateId = $(this).attr("attr_cand_id");
	var divId = $(this).attr("attr_divId");
getBrdWisNominPstAppliedDepOrCorpDetails(candidateId,divId);
});
var gblDeptId = '${deptId}';
var gblBoardId = '${boardId}';
var gblPositionId = '${positionId}';
function getBoardWiseNominatedPostMemberDetails(){
	
		$('#resultDivId').html(' <img style="margin-left: 400px; margin-top: 20px; width: 20px; height: 20px;" id="" class="offset7" src="images/search.gif">');
		var type = 'this';
		if(gblDeptId == 0)
			type = 'any'
		else if(gblPositionId == 0)
			type = 'any';
		else if(gblBoardId == 0)
			type = 'any'
	var jsObj=
	   {				
		levelId:parseInt('${lId}'),//levelId,
		searchLevelId:parseInt('${searchLevelId}'),
		levelValue:parseInt('${searchLevelValue}'),	//levelValue,
		departmentId:parseInt('${deptId}'),//departmentId,
		boardId:parseInt('${boardId}'),//boardId,
		positionId:parseInt('${positionId}'),//positionId,
		type:type//type
		}
    $.ajax({
          type:'GET',
          url: 'getNominatedPostMemberDetailsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(result != null)
		   buildNominatedPostMemberDetails(result,type,parseInt('${deptId}'),parseInt('${boardId}'),parseInt('${positionId}'));
   });
}

function buildNominatedPostMemberDetails(result,type,departmentId,boardId,positionId){
	var str='';
	str+='<table class="table table-bordered table-condensed tableShort">';
		str+='<thead style="background-color:#f8f8f8" class="text-capital">';
			str+='<th>Name</th>';
			str+='<th>Mobile</th>';
			str+='<th>Age</th>';
			str+='<th>Gender</th>';
			str+='<th>Caste</th>';
			str+='<th>Sub Caste</th>';
			str+='<th>Designations</th>';
			str+='<th style="width:90px">Reports</th>';
			str+='<th>Applied Any Dep/Corp</th>';
			str+='<th>Reference</th>';
			str+='<th>Shortlisted in any dep/ Corp</th>';
			str+='<th>Status</th>';
			str+='<th>Update Status</th>';
		str+='</thead>';
	if(result.subList != null && result.subList.length > 0){
		for(var i in result.subList){
			str+='<tr class="bg_ff">';
				//str+='<td><i class="glyphicon glyphicon-user"></i>  '+result.subList[i].voterName+'</td>';
				//TDP_CADRE_DETAILS
				str+='<td>';				
				<c:choose>
					<c:when test="${fn:contains(sessionScope.USER.entitlements, 'TDP_CADRE_DETAILS' )}">
						if(result.subList[i].tdpCadreId != null && result.subList[i].tdpCadreId > 0){
							str+='<a target="_blank" href="cadreDetailsAction.action?cadreId='+result.subList[i].tdpCadreId+'" >';
						if(result.subList[i].imageURL != null && result.subList[i].imageURL.length>0){
							str +='<div class="media"><div class="media-left"><img style="width: 50px;height:50px;border:1px solid #ddd;" src="https://mytdp.com/images/cadre_images/'+ result.subList[i].imageURL+'" class=" img-circle"  onerror="setDefaultImage(this);" alt="Profile"/></div>';
						}else
							str+='<i class="glyphicon glyphicon-user"></i> ';
							str+='<div class="media-body"> '+result.subList[i].voterName+'</div></div></a>';
						}else{
							str +='<img style="width: 70px;height:70px;border:1px solid #ddd;" src="https://mytdp.com/not_cadre_images/'+ result.subList[i].imageURL+'" class="img-responsive img-circle" onerror="setDefaultImage(this);" alt="Profile"/> '+result.subList[i].voterName+'';
						}
					</c:when> 
					<c:otherwise>
						if(result.subList[i].tdpCadreId != null && result.subList[i].tdpCadreId > 0){
							str+='';
						if(result.subList[i].imageURL != null && result.subList[i].imageURL.length>0){
							str +='<div class="media"><div class="media-left"><img style="width: 50px;height:50px;border:1px solid #ddd;" src="https://mytdp.com/images/cadre_images/'+ result.subList[i].imageURL+'" class=" img-circle"  onerror="setDefaultImage(this);" alt="Profile"/></div>';
						}else
							str+='<i class="glyphicon glyphicon-user"></i> ';
							str+='<div class="media-body"> '+result.subList[i].voterName+'</div></div>';
						}else{
							str +='<img style="width: 70px;height:70px;border:1px solid #ddd;" src="https://mytdp.com/not_cadre_images/'+ result.subList[i].imageURL+'" class="img-responsive img-circle" onerror="setDefaultImage(this);" alt="Profile"/> '+result.subList[i].voterName+'';
						}
					</c:otherwise>
				</c:choose>
				str+=' </td>';
				
				if(result.subList[i].tdpCadreId != null && result.subList[i].tdpCadreId > 0){
					//str+='<td><i class="glyphicon glyphicon-user"></i>  '+result.subList[i].cadreName+'</td>';
					str+='<td>'+result.subList[i].cadreMobile+'</td>';
					str+='<td>'+result.subList[i].cadreAge+'</td>';
					str+='<td>'+result.subList[i].cadreGen+'</td>';
					str+='<td>'+result.subList[i].caste+'</td>';
					str+='<td>'+result.subList[i].casteName+'</td>';
					str+='<td>';
					if(result.subList[i].publicReprStr != null && result.subList[i].publicReprStr.length > 0)
						str+='<p>'+result.subList[i].publicReprStr+'</p>';
					if(result.subList[i].partyPosition != null && result.subList[i].partyPosition.length > 0)
						str+='<p>'+result.subList[i].partyPosition+'</p>';
					if(result.subList[i].publicReprStr == null && result.subList[i].partyPosition == null)
						str+=' - ';
					str+='</td>';
					str+='<td>';
					if(result.subList[i].idNamevoList != null && result.subList[i].idNamevoList.length > 0){
						for(var j in result.subList[i].idNamevoList){
							str+='<p class="showPdfCls" attr_filePath="'+result.subList[i].idNamevoList[j].mobileNo+'" data-toggle="modal" data-target="#pdfModelId">'+result.subList[i].idNamevoList[j].status+'<i class="glyphicon glyphicon-list-alt pull-right" style="background-color:green;cursor:pointer;"></i></p>';
						}
					}
					if(result.subList[i].nomDocsList != null && result.subList[i].nomDocsList.length > 0){
						for(var j in result.subList[i].nomDocsList){
							str+='<p class="showPdfCls" attr_filePath="'+result.subList[i].nomDocsList[j].mobileNo+'" data-toggle="modal" data-target="#pdfModelId">'+result.subList[i].nomDocsList[j].status+'<i class="glyphicon glyphicon-list-alt pull-right" style="background-color:green;cursor:pointer;"></i></p>';
						}
					}
					if(result.subList[i].idNamevoList == null && result.subList[i].nomDocsList == null)
						str+=' - ';
					str+='</td>';
					//Suitable<i class="glyphicon glyphicon-list-alt pull-right"></i></td>';
				}
				else{
					str+='<td>'+result.subList[i].voterMoblie+'</td>';
					str+='<td>'+result.subList[i].age+'</td>';
					str+='<td>'+result.subList[i].gender+'</td>';
					str+='<td>'+result.subList[i].candCaste+'</td>';
					str+='<td>'+result.subList[i].candCasteName+'</td>';
					str+='<td> - </td>';
					str+='<td>';
					if(result.subList[i].nomDocsList != null && result.subList[i].nomDocsList.length > 0){
						for(var j in result.subList[i].nomDocsList){
							str+='<p class="showPdfCls" attr_filePath="'+result.subList[i].nomDocsList[j].mobileNo+'" data-toggle="modal" data-target="#pdfModelId">'+result.subList[i].nomDocsList[j].status+'<i class="glyphicon glyphicon-list-alt pull-right" style="background-color:green;cursor:pointer;"></i></p>';
						}
					}
					else
						str+=' - ';
					str+='</td>';
				}
				str+='<td style="position:relative" class="text-center">';
				if(result.subList[i].otherDepartmentsCount != null && result.subList[i].otherDepartmentsCount > 0)
					str+='<span class="appliedCount" attr_cand_id="'+result.subList[i].nominatedPostCandidateId+'" attr_divId="departmentsTableId'+i+'" style="font-weight:bold;color:green;">'+result.subList[i].otherDepartmentsCount+'</span>';
				else
					str+='<span> NO </span>';
					str+='<div class="appliedPostPopup">';
						str+='<div class="appliedPostPopupArrow" id="departmentsTableId'+i+'">';
						str+='</div>';
					str+='</div>';
				str+='</td>';
				if(result.subList[i].referCandCount != null)
					str+='<td><a class="referenceCls" data-toggle="modal" data-target="#referModelId" attr_candidate_id="'+result.subList[i].nominatedPostCandidateId+'" style="font-weight:bold;color:green;cursor:pointer;" >'+result.subList[i].referCandCount+'</a></td>';
				else
					str+='<td> - </td>';
				if(result.subList[i].otherDeptShortListed != null && result.subList[i].otherDeptShortListed == 'YES')
					str+='<td>'+result.subList[i].otherDeptShortListed+'</td>';
				else
					str+='<td> NO </td>';
				str+='<td>'+result.subList[i].status+'</td>';
				str+='<td style="position:relative;">';
				if(type == "this"){
					str+='<button class="btn btn-success btnPopup updateButtonCls" attr_selected_status_id="updatedStatusSelectId'+i+'">UPDATE</button>';
					str+='<div class="updateDropDown" id="updateDropDownId'+i+'">';
						str+='<div class="updateDropDownArrow">';						
						//str+='<div class="statusUpdateDivCls" id="statusUpdateDivId'+i+'"></div>';
							str+='<i class="glyphicon glyphicon-remove pull-right closeDivCls" id="updateDropDownId'+i+'" style="cursor:pointer;"></i>';
							str+='<label>Select Status <span class="text-success" id="successDivId'+i+'"></span></label>';
							str+='<span id="selectStatusIdImg"><img src="images/search.gif" style="display:none;"/></span>';
							str+='<select class="chosenSelect" id="updatedStatusSelectId'+i+'">';
								str+='<option value="0">Select Status</option>';
								str+='<option value="2">Rejected</option>';
								str+='<option value="3">Shortlisted</option>';
							str+='</select>';
							str+='<label>Comments</label>';
							str+='<textarea class="form-control" id="statusCommentId'+i+'"></textarea>';
							str+='<button class="btn btn-success btn-block m_top10 updateStatusCls" attr_application_id="'+result.subList[i].nominatePostApplicationId+'" attr_selected_status_id="updatedStatusSelectId'+i+'" attr_comment_id="statusCommentId'+i+'" attr_success_div="successDivId'+i+'" attr_levelId="'+result.subList[i].boardLevelId+'" attr_level_value="'+result.subList[i].levelValue+'" attr_departmentId="'+departmentId+'" attr_boardId="'+boardId+'" attr_positionId="'+positionId+'" attr_candidate_id="'+result.subList[i].nominatedPostCandidateId+'">SUBMIT</button>';
						str+='</div>';
					str+='</div>';
				}
				else if(type == "any"){
					
					if(globalPositionId>0 && globalDeptId >0 && globalBoardId>0)
						str+='<button class="btn btn-success btnPopupThisAny updateButtonThisAnyCls" attr_count="'+i+'">ASSIGN THIS POSITION</button>';
					
					str+='<button class="btn btn-success btnPopupAny updateButtonAnyCls m_top10" attr_count="'+i+'">ASSIGN A POSITION</button>';
					str+='<div class="updateDropDownThisAny" id="updateDropDownThisAny'+i+'">';
						str+='<div class="updateDropDownArrow">';
						str+='<div class="text-success" id="successDivThisAnyId'+i+'"></div>';
								str+='<i class="glyphicon glyphicon-remove pull-right closeDivThisAnyCls" id="updateDropDownThisAny'+i+'" style="cursor:pointer;"></i>';
								str+='<label>Select Status</label>';
								str+='<select class="chosenSelect" id="updatedStatusThisAnyId'+i+'">';
									str+='<option value="0">Select Status</option>';
									str+='<option value="2">Rejected</option>';
									str+='<option value="3">Shortlisted</option>';
								str+='</select>';
								str+='<label>Comments</label>';
								str+='<textarea class="form-control" id="statusCommentThisAnyId'+i+'"></textarea>';
								str+='<button class="btn btn-success btn-block m_top10 updateStatusThisAnyCls" attr_application_id="'+result.subList[i].nominatePostApplicationId+'" attr_candidate_id="'+result.subList[i].nominatedPostCandidateId+'" attr_count="'+i+'" attr_levelId="'+result.subList[i].boardLevelId+'" attr_level_value="'+result.subList[i].levelValue+'" attr_departmentId="'+departmentId+'" attr_boardId="'+boardId+'" attr_positionId="'+positionId+'">SUBMIT</button>';
						str+='</div>';
					str+='</div>';
					str+='<div class="updateDropDownAny" id="updateDropDownAny'+i+'">';
						str+='<div class="updateDropDownArrow">';
						str+='<div class="text-success" id="successDivAnyId'+i+'"></div>';
							str+='<i class="glyphicon glyphicon-remove pull-right closeDivAnyCls" id="updateDropDownAny'+i+'" style="cursor:pointer;"></i>';
								str+='<div class="row">';
								str+='<div class="col-md-3 col-xs-12 col-sm-3">';
									str+='<label>Department</label>';
									str+='<select class="chosenSelect" id="departmentAnyId'+i+'" onchange="getBoardsForDepartments('+i+')">';
										str+='<option value="0">Select Department</option>';
									str+='</select>';
								str+='</div>';
								str+='<div class="col-md-3 col-xs-12 col-sm-3">';
									str+='<label>Corporation/Board</label>';
									str+='<select class="chosenSelect" id="boardAnyId'+i+'" onchange="getPositionsForBoard('+i+')">';
										str+='<option value="0">Select Board</option>';
									str+='</select>';
								str+='</div>';
								str+='<div class="col-md-3 col-xs-12 col-sm-3">';
									str+='<label>Position</label>';
									str+='<select class="chosenSelect" id="positionAnyId'+i+'" onchange="checkPositionAvailableOrNot('+i+')">';
										str+='<option value="0">Select Position</option>';
									str+='</select>';
									str+='<b><span id="errorMsg'+i+'" style="color:red"></span></b>';
								str+='</div>';
								str+='<div class="col-md-3 col-xs-12 col-sm-3">';
									str+='<label>Select Status</label>';
									str+='<select class="chosenSelect" id="updatedStatusAnyId'+i+'">';
										str+='<option value="0">Select Status</option>';
										str+='<option value="2">Rejected</option>';
										str+='<option value="3">Shortlisted</option>';
									str+='</select>';
								str+='</div>';
								str+='<div class="col-md-12 col-xs-12 col-sm-12">';
									str+='<label>Comments</label>';
									str+='<textarea class="form-control" id="statusCommentAnyId'+i+'"></textarea>';
									str+='<button id="updateStatusAnyId" class="btn btn-success btn-block m_top10 updateStatusAnyCls" attr_application_id="'+result.subList[i].nominatePostApplicationId+'" attr_candidate_id="'+result.subList[i].nominatedPostCandidateId+'" attr_count="'+i+'" attr_levelId="'+result.subList[i].boardLevelId+'" attr_level_value="'+result.subList[i].levelValue+'">SUBMIT</button>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				}	
				str+='</td>';
			str+='</tr>';
			/*str+='<tr>';
				str+='<td>Preferable<i class="glyphicon glyphicon-list-alt pull-right"></i></td>';
			str+='</tr>';*/
		}
	}
	str+='</table>';
	
	if(globalPositionId == 0)
		$("#postMembersId").html(" APPLIED <b class='text-success'  style='text-transform:uppercase;' > ANY POST </b> - APPLIED MEMBERS DETAILS ");
	else 
		$("#postMembersId").html(" APPLIED <b class='text-success' style='text-transform:uppercase;'>  "+globalPosName+" POST </b>  - APPLIED  MEMBERS DETAILS ");
	
	$("#resultDivId").html(str);
	tableResponsive();
}

function setDefaultImage(img){
    img.src = "images/User.png";
}
   
$(document).on('click','.closeDivCls',function(){
	var divId = $(this).attr("id");
	$("#"+divId).hide();
});
$(document).on('click','.closeDivThisAnyCls',function(){
	var divId = $(this).attr("id");
	$("#"+divId).hide();
});
$(document).on('click','.closeDivAnyCls',function(){
	var divId = $(this).attr("id");
	$("#"+divId).hide();
});

$(document).on('click','.referenceCls',function(){
	var candidateId = $(this).attr("attr_candidate_id");
	
	var jsObj=
	   {				
		candidateId:candidateId
		}
    $.ajax({
          type:'GET',
          url: 'getReferCadreDetailsForCandidateAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(result != null && result.length > 0){
		   buildReferenceCandidateDetails(result);
	   }
   });
});

function buildReferenceCandidateDetails(result){
	var str='';
	
	str+='<table class="table table-bordered">';
		str+='<thead class="text-capital" style="background-color:#f4f4f4">';
			str+='<th>Image</th>';
			str+='<th>Name</th>';
			str+='<th>Membership No</th>';
			str+='<th>Mobile No</th>';
			str+='<th>Position</th>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result){
			str+='<tr>';
				//str+='<td><i class="glyphicon glyphicon-user"> </i></td>';
				str+='<td> <a target="_blank" href="cadreDetailsAction.action?cadreId='+result[i].id+'" >';
				str +='<div class="media"><div class="media-left"><img style="width: 50px;height:50px;border:1px solid #ddd;" src="https://mytdp.com/images/cadre_images/'+ result[i].status+'" class=" img-circle"  onerror="setDefaultImage(this);" alt="Profile"/></div>';
				str+='</a></td>';
				if(result[i].name != null)
					str+='<td> <a target="_blank" href="cadreDetailsAction.action?cadreId='+result[i].id+'" >'+result[i].name+'</a></td>';
				else
					str+='<td> - </td>';
				if(result[i].percentage != null)
					str+='<td>'+result[i].percentage+'</td>';
				else
					str+='<td> - </td>';
				if(result[i].mobileNo != null)
					str+='<td>'+result[i].mobileNo+'</td>';
				else
					str+='<td> - </td>';
				str+='<td>';
				if(result[i].publicRepr != null )
					str+='<p>'+result[i].publicRepr+'</p>';
				else
					str+='<p>  </p>';
				
				if(result[i].partyPos != null)
					str+='<p>'+result[i].partyPos+'</p>';
				else
					str+='<p>  </p>';
				str+='</td>';
			str+='</tr>';
		}
		str+='</tbody>';
	str+='</table>';
	
	$("#referenceDetailsId").html(str);
}

$(document).on('click','.showPdfCls',function(){        
	var str = '';
	var filePath = $(this).attr("attr_filePath");
	str += '<iframe src="https://mytdp.com/'+filePath+'" width="100%" height="800">';    
	str += '</iframe>';
	$("#pdfReportDetailsId").html(str);
}); 

$(document).on("click",".updateButtonCls",function(){
	var selectDivId = $(this).attr("attr_selected_status_id");
	 $("#"+selectDivId).chosen();
	//getApplicationStatus(selectDivId);
});

$(document).on("click",".updateButtonAnyCls",function(){
	$(".updateDropDownThisAny").hide();
	var num = $(this).attr("attr_count");
	getDepartments(num);
	//getApplicationStatus("updatedStatusAnyId"+num);
	$("#positionAnyId"+num).chosen();
	$("#boardAnyId"+num).chosen();
	$("#updatedStatusAnyId"+num).chosen();
});

$(document).on("click",".updateButtonThisAnyCls",function(){
	$(".updateDropDownAny").hide();
	var num = $(this).attr("attr_count");
	 $("#updatedStatusThisAnyId"+num).chosen();
	//getApplicationStatus("updatedStatusThisAnyId"+num);
});

function getApplicationStatus(divId){
	$("#"+divId+" option").remove();
	
	var jsObj={}
	$.ajax({
          type:'GET',
          url: 'getAllApplicationStatusListAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	    if(result != null && result.length > 0){
		   $("#"+divId).append('<option value="0">Select Status</option>');
		   for(var i in result){
			   $("#"+divId).append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
		   }
		   $("#"+divId).chosen();
	   }
   });
}

function getDepartments(num){
	$("#departmentAnyId"+num+" option").remove();
		$("#statusCommentAnyId"+num).html('');
	   $("#updatedStatusAnyId"+num).val(0);
	   $("#boardAnyId"+num).html('');
	   $("#boardAnyId"+num).trigger("chosen:updated")
	   $("#boardAnyId"+num).trigger("chosen:updated")
	   $("#positionAnyId"+num).html('');
	   $("#positionAnyId"+num).trigger("chosen:updated")
       $("#departmentAnyId"+num).html('');
	   $("#departmentAnyId"+num).trigger("chosen:updated")
	   $("#updatedStatusAnyId"+num).trigger("chosen:updated");
	   
	   

	var jsObj={
		postType:1,
		boardLevelId:parseInt('${param.lId}'),
		searchLevelId:parseInt('${param.searchLevelId}'),
		searchLevelValue : parseInt('${param.searchLevelValue}')
	}
	$.ajax({
          type:'GET',
          url: 'getDepartmentsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	    if(result != null && result.length > 0){
		   $("#departmentAnyId"+num).append('<option value="0">Select Department</option>');
		   for(var i in result){
			   if(globalDeptId > 0){
					if(globalDeptId == parseInt(result[i].id))
						$("#departmentAnyId"+num).append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
			   }else
					$("#departmentAnyId"+num).append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
		   }
		   $("#departmentAnyId"+num).chosen();
		   
	   }
   });
}

function getBoardsForDepartments(num){
	$("#boardAnyId"+num+" option").remove();
	var depmtId = $("#departmentAnyId"+num).val();
	$("#statusCommentAnyId"+num).html('');
	 $("#updatedStatusAnyId"+num).val(0);
	  $("#updatedStatusAnyId"+num).trigger("chosen:updated");
	var jsObj={
		depmtId : depmtId,
		boardLevelId:parseInt('${param.lId}'),
		searchLevelValue : parseInt('${param.searchLevelValue}'),
		searchLevelId : parseInt('${param.searchLevelId}')
	}
	$.ajax({
          type:'GET',
          url: 'getDepartmentBoardsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	    if(result != null && result.length > 0){
			$("#boardAnyId"+num).append('<option value="0">Select Corporation/Board</option>');
		   for(var i in result){
			   if(globalBoardId>0){
				   if(globalBoardId == parseInt(result[i].id))
						$("#boardAnyId"+num).append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
			   }
			   else{
				   $("#boardAnyId"+num).append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
			   }
		   }
		   $("#boardAnyId"+num).trigger('chosen:updated');
	   }
   });
}

function getPositionsForBoard(num){
	$("#positionAnyId"+num+" option").remove();
	 $("#updatedStatusAnyId"+num).val(0);
	 $("#statusCommentAnyId"+num).html('');
	var depmtId = $("#departmentAnyId"+num).val();
	var boardId = $("#boardAnyId"+num).val();
	
	 $("#updatedStatusAnyId"+num).trigger("chosen:updated");
	var jsObj={
		depmtId : depmtId,
		boardId : boardId,
		boardLevelId:parseInt('${param.lId}'),
		searchLevelValue : parseInt('${param.searchLevelValue}'),
		searchLevelId : parseInt('${param.searchLevelId}')
	}
	$.ajax({
          type:'GET',
          url: 'getDepartmentBoardPositionsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	    if(result != null && result.length > 0){
		   $("#positionAnyId"+num).append('<option value="0">Select Position</option>');
		   for(var i in result){
			   if(globalPositionId>0){
				   if( globalPositionId == parseInt(result[i].id)){
					   $("#positionAnyId"+num).append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
				   }
				}else{
					$("#positionAnyId"+num).append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
				}
		   }
		   $("#positionAnyId"+num).trigger('chosen:updated');
	   }
   });
}

$(document).on("click",".updateStatusCls",function(){
	var applicationId = $(this).attr("attr_application_id");
	var selectDivId = $(this).attr("attr_selected_status_id");
	var commentDivId = $(this).attr("attr_comment_id");
	var divId = $(this).attr("attr_success_div");
	var levelId= $(this).attr("attr_levelId");
	var levelVal = $(this).attr("attr_level_value");
	var deptId = $(this).attr("attr_departmentId");
	var boardId = $(this).attr("attr_boardId");
	var positionId = $(this).attr("attr_positionId");
	var candidateId = $(this).attr("attr_candidate_id");
	var status = $("#"+selectDivId).val();
	var comment = $("#"+commentDivId).val();
	var str=' ';
	var flag = true;
	if(status==0)
	{
		str+='Please Select Status</br>';
		flag = false;
	}
	if(comment.trim().length==0)
	{
		str+='Comment is required';
		flag = false;
	}
	if(!flag)
	{
		$("#"+divId).html(str).css("color","red");
		return;
	}
	$("#selectStatusIdImg").show();
	$("#"+divId).html('<img id="searchMemberAjax" src="images/icons/loading.gif" style="width:15px;"/>');
	$(this).hide();
	var jsObj=
	   {				
		nominatePostApplicationId:applicationId,
		statusId:status,
		comment :comment,
		levelId : levelId,
		levelVal : levelVal,
		deptId : deptId,
		boardId : boardId,
		positionId : positionId,
		candidateId : candidateId
		}
    $.ajax({
          type:'GET',
          url: 'updateApplicationStatusDetailsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   $("#selectStatusIdImg").hide();
	   $(this).show();
		if(result != null && result == 'success'){
			$("#"+divId).html("Successfully Updated...").css("color","green");
			//window.location.reload();
			setTimeout(function(){getBoardWiseNominatedPostMemberDetails();}, 1000);
			
		}
		else
			$("#"+divId).html("Sorry,Exception Occured...Please try again...").css("color","red");
   });
});

$(document).on("click",".updateStatusThisAnyCls",function(){
	var applicationId = $(this).attr("attr_application_id");
	var candidateId = $(this).attr("attr_candidate_id");
	var num = $(this).attr("attr_count");
	var levelId= $(this).attr("attr_levelId");
	var levelVal = $(this).attr("attr_level_value");
	var deptId = $(this).attr("attr_departmentId");
	var boardId = $(this).attr("attr_boardId");
	var positionId = $(this).attr("attr_positionId");
	
	var statusId = $("#updatedStatusThisAnyId"+num).val();
	var comment = $("#statusCommentThisAnyId"+num).val();
	var jsObj=
	   {	
		applicationId : applicationId,
		candidateId : candidateId,
		levelId : levelId,
		levelVal : levelVal,
		deptId : deptId,
		boardId : boardId,
		positionId : positionId,
		statusId : statusId,
		comment : comment
		}
    $.ajax({
          type:'GET',
          url: 'savingAnyPostCandidatesToPositionAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
		if(result != null && result == 'success')
			$("#successDivThisAnyId"+num).html("Successfully Updated...");
		else
			$("#successDivThisAnyId"+num).html("Sorry,Exception Occured...Please try again...");
   });
});

$(document).on("click",".updateStatusAnyCls",function(){


	
	var applicationId = $(this).attr("attr_application_id");
	var candidateId = $(this).attr("attr_candidate_id");
	var num = $(this).attr("attr_count");
	var levelId= $(this).attr("attr_levelId");
	var levelVal = $(this).attr("attr_level_value");
	var butonId = $(this).attr("id");
	
	var deptId = $("#departmentAnyId"+num).val();
	var boardId = $("#boardAnyId"+num).val();
	var positionId = $("#positionAnyId"+num).val();
	var statusId = $("#updatedStatusAnyId"+num).val();
	var comment = $("#statusCommentAnyId"+num).val();

	if(deptId == 0){
			$("#successDivAnyId"+num).html(" <b style='color: red;' >Please select department.</b>");
			return;
	}
	if(boardId == 0){
			$("#successDivAnyId"+num).html("  <b style='color: red;' >Please select Corporation/Board.</b>");
			return;
	}
	if(positionId == 0){
			$("#successDivAnyId"+num).html(" <b style='color: red;' > Please select Position.</b>");
			return;
	}
	if(statusId == 0){
			$("#successDivAnyId"+num).html(" <b style='color: red;' > Please select status.</b>");
			return;
	}
	if(comment =='' || comment.trim().length == 0){
			$("#successDivAnyId"+num).html("  <b style='color: red;' > Comment is required.</b>");
			return;
	}
	
	$("#successDivAnyId"+num).html('<img id="" src="images/icons/loading.gif" style="width:15px;"/>');
	$('#'+butonId+'').hide();
	var jsObj=
	   {	
		applicationId : applicationId,
		candidateId : candidateId,
		levelId : levelId,
		levelVal : levelVal,
		deptId : deptId,
		boardId : boardId,
		positionId : positionId,
		statusId : statusId,
		comment : comment
		}
    $.ajax({
          type:'GET',
          url: 'savingAnyPostCandidatesToPositionAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   $("#successDivAnyId"+num).html('');
	  // $('#'+butonId+'').show();
		if(result != null && result == 'success')
			$("#successDivAnyId"+num).html(" <b>Successfully Updated... </b>");
		else
			$("#successDivAnyId"+num).html(" <b>Sorry,Exception Occured...Please try again...</b>");
		
		  // window.location.reload();
		   setTimeout(function(){getBoardWiseNominatedPostMemberDetails();}, 1000);
   });
});

getNominatedPostPostionDetails();
getBoardWiseNominatedPostMemberDetails();
function getNominatedPostPostionDetails(){
$('#positionDivId').html(' <img style="margin-left: 400px; margin-top: 20px; width: 20px; height: 20px;" id="" class="offset7" src="images/search.gif">');
	var jsObj=
	   {	
			depmtId:parseInt('${deptId}'),
			boardId:parseInt('${boardId}'),
			positionId:parseInt('${positionId}'),
			bLId:parseInt('${lId}'),
			searchLevelId:parseInt('${searchLevelId}'),
			lValue:parseInt('${searchLevelValue}')
	   }
	    $.ajax({
          type:'GET',
          url: 'getNominatedPostPostionDetailsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		  // getBoardWiseNominatedPostMemberDetails();
		  if(result != null){
			  buildNominatePostPositionDetails(result);
		  }
	   });  
}
function buildNominatePostPositionDetails(result){
	 var str='';
		   if(result !=null && result.length>0){
			   	str+='<table class="table table-bordered bg_ff" id="nominatePositionDetilsId">';
					str+='<thead>';
					str+='<tr>';
					str+='<th rowspan="2"></th>';
					//str+='<th rowspan="2">TOTAL AVAILABLE POSTS</th>';
					str+='<th rowspan="2">APPLICATIONS RECEIVED</th>';
					str+='<th rowspan="2">SHORTLISTED</th>';
					str+='<th colspan="4">CASTE GROUP</th>';
					str+='<th colspan="5">AGE GROUP</th>';
					str+='</tr>';
					str+='<tr>';
					for(var j in result[0].idNameVoList){
					str+='<th>'+result[0].idNameVoList[j].name+'</th>';
					}
					str+='<th>20-29</th>';
					str+='<th>30-39</th>';
					str+='<th>40-49</th>';
					str+='<th>50-59</th>';
					str+='<th>60+</th>';
					str+='</tr>';
					str+='</thead>';
					str+='<tbody>';
			   //console.log(result);

			   for(var i in result){
					if(result[i].id != null && result[i].id > 0){
						str+='<tr>';
						str+='<td><p>THIS POST</p><small>Requested for this post members shortlisted</small></td>';
						//str+='<td>02</td>';
						str+='<td>'+result[i].receivedCount+'</td>';
						str+='<td>'+result[i].shortListedCount+'</td>';
						for(var j in result[i].idNameVoList){
						str+='<td>'+result[i].idNameVoList[j].count+'</td>';
						}
						str+='<td>'+result[i].firstAgeGroupCount+'</td>';
						str+='<td>'+result[i].secondAgeGroupCount+'</td>';
						str+='<td>'+result[i].thirdAgeGroupCount+'</td>';
						str+='<td>'+result[i].fourthAgeGroupCount+'</td>';
						str+='<td>'+result[i].fifthAgeGroupCount+'</td>';
						str+='</tr>';
					}
					else {
						str+='<tr>';
						str+='<td><p>ANY POST  </p><small>Requested for any post members shortlisted for this</small></td>';
						//str+='<td>02</td>';
						str+='<td>'+result[i].receivedCount+'</td>';
						str+='<td>'+result[i].shortListedCount+'</td>';
						for(var j in result[i].idNameVoList){
						str+='<td>'+result[i].idNameVoList[j].count+'</td>';
						}
						str+='<td>'+result[i].firstAgeGroupCount+'</td>';
						str+='<td>'+result[i].secondAgeGroupCount+'</td>';
						str+='<td>'+result[i].thirdAgeGroupCount+'</td>';
						str+='<td>'+result[i].fourthAgeGroupCount+'</td>';
						str+='<td>'+result[i].fifthAgeGroupCount+'</td>';
						str+='</tr>';
					}
				}
			   
			   $("#positionDivId").html(str);
		   }
}
function getBrdWisNominPstAppliedDepOrCorpDetails(candidateId,divId){
	var jsObj=
	   {				
		candidateId:candidateId
		}
    $.ajax({
          type:'GET',
          url: 'getBrdWisNominPstAppliedDepOrCorpDetailsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(result != null && result.length > 0){
		   buildDepartmentDetails(result,divId);
	   }
   });
}

function buildDepartmentDetails(result,divId){
	var str='';
	
	//str+='<i class="glyphicon glyphicon-remove pull-right"></i>';
	str+='<table class="table table-condensed">';
		str+='<thead style="background-color:#f4f4f4;" class="text-capital">';
			str+='<th>Level</th>';
			str+='<th>Location</th>';
			str+='<th>Department</th>';
			str+='<th>Corporation</th>';
			str+='<th>Position</th>';
			str+='<th>Status</th>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result){
			str+='<tr>';
				str+='<td>'+result[i].hno+'</td>';
				str+='<td>'+result[i].name+'</td>';
				if(result[i].mobileNo != null && result[i].mobileNo != "")
					str+='<td>'+result[i].mobileNo+'</td>';
				else
					str+='<td> Any </td>';
				if(result[i].pincode != null && result[i].pincode != "")
					str+='<td>'+result[i].pincode+'</td>';
				else
					str+='<td> Any </td>';
				if(result[i].voterCardNo != null && result[i].voterCardNo != "")
					str+='<td>'+result[i].voterCardNo+'</td>';
				else
					str+='<td> Any </td>';
				str+='<td>'+result[i].perc+'</td>';
			str+='</tr>';
		}
		str+='</tbody>';
	str+='</table>';
	$("#"+divId).html(str);
}
function checkPositionAvailableOrNot(num){
		var positionName = $("#positionAnyId"+num).find(":selected").text();
	 
	 var jsObj={
		departmentId:$("#departmentAnyId"+num).val(),
		boardId     : $("#boardAnyId"+num).val(),
		positionId  : 5,
		boardLevelId:parseInt('${param.lId}'),
		searchLevelId:parseInt('${param.searchLevelId}'),
		searchLevelValue : parseInt('${param.searchLevelValue}')
	}
	
	$.ajax({
          type:'GET',
          url: 'checkPositionAvailableOrNotAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	    if(result.message != null && result.message == "NOT AVAILABLE"){
		  $("#updateStatusAnyId").hide();
		  $("#errorMsg"+num).html("This&nbsp;&nbsp;"   +positionName+  "&nbsp;&nbsp;posts are already filled out.");
	   }else if(result.message != null && result.message == "AVAILABLE"){
		  $("#updateStatusAnyId").show(); 
	   }
   });
}
function tableResponsive()
{
  var getWidth = $(window).width();
  if(getWidth < 800)
  {
	$("#resultDivId").addClass("table-responsive");
  }
} 
	$(document).on("click",".redirectPageCls",function(){
		
		window.location.replace("nominatedPostManagementAction.action?lId="+globalLevelId+"&stId="+globalStatusId+"&sts="+globalStatusMainName+"&levelTxt="+globalLevelTxt+"");
	});
	
</script>
</body>
</html>
