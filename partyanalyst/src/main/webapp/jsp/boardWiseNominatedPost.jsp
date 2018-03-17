<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<title>Board wise nominated posts</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/NominatedPost/custom.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
<link href="dist/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
.enrolled-mem li.yes {
    background-color: #d7f0db;
}
.enrolled-mem li {
    border: 1px solid #666;
    border-radius: 3px;
    display: inline-block;
    padding: 3px;
}
.enrolled-mem li.no {
    background-color: #e3c5c7;
}
.tableShort thead th:nth-child(1)
{
	width:10% !important
}
.tableShort thead th:last-child
{
	width:7% !important
}
.headingUlForShort
{
	padding:0px;
}
.headingUlForShort li
{
	font-size:22px;
	background-color:#B7B7B7;
	border-radius:8px;
	margin-top:2px;
	list-style:none;
	text-transform:uppercase;
	padding:3px 6px;
	text-align:left;
	font-weight:bold;
}

</style>
<script>
	var entitlementsArr =[];
		<c:forEach items="${sessionScope.USER.entitlements}" var="value">
		entitlementsArr.push('${value}');
		//console.log('${item}');
	</c:forEach>
</script>
</head>
<body>
<div class="container">
	<div class="row">
	<div class="col-md-12 col-xs-12 col-lg-12 col-sm-12 m_top20">
		<ul class="geoGrpahicBreadCrumb">
				<li style="text-transform: uppercase; font-weight: 500;" data-placement="bottom" data-toggle="tooltip" title="Nominated Posts Overview Details"><a href="nominatedPostApplicationReviewAction.action"><i class="glyphicon glyphicon-home" style="color:#fff;"></i></a></li>
				<li  style="text-transform: uppercase; font-weight: 500;cursor:pointer" class="redirectPageCls" id="flowHeading" data-placement="bottom" data-toggle="tooltip" title="Click here to go Nominated Post Management Page"></li>
				<li  style="text-transform: uppercase; font-weight: 500;">SHORTLISTING</li>
		</ul>
	</div>
    	<div class="col-md-12 col-xs-12 col-lg-12 col-sm-12 m_top20">
		 <!--<ol class="breadcrumb text-capital" id="headLvlDeptId" style="margin-bottom: -10px;margin-left: -13px;"></ol>-->
			<div class="panel panel-default">
				<div class="panel-heading bg_cc">
					<div class="row">
						<div class="col-md-3 col-xs-12 col-sm-4">
							<ul class="headingUlForShort">
								<li style="color:#249E24">SHORTLISTING</li>
								<li class="headingColor"><span  id="headPosId"></span></li>
							</ul>
						</div>						
						
						<div class="col-md-2 col-xs-4 col-sm-2 m_top20 pad_right0">
							<h4 class="text-right"><b>Level - </b></h4>
							<h4 class="text-right"><b>Department - </b></h4>
							<h4 class="text-right"><b>Board/Corp - </b></h4>
						</div>
						<div class="col-md-7 col-xs-8 col-sm-6 m_top20" style="padding-left:12px">
							<h4 class="text-left" id="levelTextId"></h4>
							<h4 class="text-left" id="deptHeadId"></h4>
							<h4 class="text-left" id="boardHeadId"></h4>
						</div>
						
					</div>
				</div>
				<div class="panel-body">
					<div class="panel panel-default panelDepartmentHead" style="margin-top: 10px;">
						<div style="background-color:#f4f4f4;">
							<div class="table-responsive" id="positionDivId"></div>
						</div>
					</div>
					<div class="panel panel-default panelDepartmentHead">
						<div class="panel-heading">
							<h4 class="panel-title"  id="postMembersId">APPLIED THIS POST - MEMBERS DETAILS</h4>
						</div>
						<div class="panel-body" style="background-color:#f4f4f4;">
							<div class="">
								<div id="resultDivId"></div>
							</div>
							<div  id="detailedDiv"></div>
							<!--<button class="btn btn-success appleNewPostBtn">APPLY HERE</button>-->
							<a class="btn  appleNewPostBtn" style="float:right;font-weight:bold" title="You can apply Nomianted Post here"><u>Click here to apply for new post</u></a>
						</div>
					</div>
				</div>
				<!--<h3 class="headingColor"><small><span id="headBrdId" style="text-transform:uppercase;margin-bottom: 10px;"></span></small> - <span id="headPosId" style="text-transform:uppercase"></span> </h3>-->
			</div>
        	
           
        	
        </div>
        <div class="col-md-12 col-xs-12 col-sm-12 col-lg-12">
        	
        </div>
    </div>
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

<div class="modal fade" tabindex="-1" id="detailedReprot" role="dialog">  
	<div class="modal-dialog" style="width:85%;">      
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">DETAILED REPORT </h4>
			</div>
			<div class="modal-body">
				<div  id="detailedDiv1" class="table-responsive"></div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" tabindex="-1" id="mahanaduDetailedReprotsId" role="dialog">  
	<div class="modal-dialog" style="width:85%;">      
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">MAHANADU 2015 REPORT </h4>
			</div>
			<div class="modal-body">
				<div  id="mahanadu2015DetilsId" class="table-responsive"></div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
<!-- Modal for Remove cadre -->
			<div class="modal fade" id="removeModalDivId">
			  <div class="modal-dialog modal-sm">
				<div class="modal-content">
				  <div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="removeModalTitleId" style="color:red;">Removing Cadre</h4>
				  </div>
				  <div class="modal-body" id="ramoveModalBodyDivId">
					<div id="errorDivId" style="color:red"></div>
					<div id="successDivId"></div>
					<div class="row">
						<div class="col-md-12">
							<div><b>Cadre Name :</b> <span id="cadreName"></span></div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 m_top10">
							<div><b>Reason <span style="color:red">*</span>:</b><span style="color:red;" class ="reasonErrCls"></span> 
								<select id="reasonSelectId" class="form-control">
									<option value="0">Select Reason</option>
								</select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 m_top10">
							<div><b>Remark <span style="color:red">*</span>:</b><span style="color:red;" class ="remarkTestErrCls"></span> 
							<textarea class="form-control" id="remarkTextAreaId"></textarea></div>
						</div>
					</div>
					</div>
				  <div class="modal-footer">
					<button type="button" class="btn btn-primary btn-sm" id="saveRemovingCadreDetailsId">Remove</button>
					<button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
				  </div>
				</div><!-- /.modal-content -->
			  </div><!-- /.modal-dialog -->
			</div><!-- /.modal -->

<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mousewheel.js" type="text/javascript"></script>

<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script type="text/javascript" src="js/nominatedPosts/deleteCandidateNominatedPost.js"></script>
<script type="text/javascript">

var windowUrl = window.location.href;
var wurl = windowUrl.substr(0,(windowUrl.indexOf("/boardWiseNominatedPostAction")));
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
var globalLevelTxt = '${param.levelTxt}';
var globalDeptName = '${param.deptName}'; 
var headBrdId='${param.brdName}';
var globalPosName='${param.posName}';
var globalPostId='${param.positionId}';
var globalStats='${param.sts}';

if(globalDeptName ==null || globalDeptName.trim().length==0){
	globalDeptName  = "ANY";
}
if(headBrdId ==null || headBrdId.trim().length==0){
	headBrdId = "ANY";
}
if(globalPosName == null || globalPosName.trim().length==0){
	globalPosName = "ANY";
}

var globalPositionId = parseInt('${param.positionId}');
var globalDeptId = parseInt('${param.deptId}');
var globalBoardId = parseInt('${param.boardId}');

var globalStatusMainName = '${param.stN}';
var globalLevelId = parseInt('${param.lId}');
var globalStatusId = parseInt('${param.stId}');
var globalSearchLevelId = parseInt('${param.searchLevelId}');
var globalSearchLevelValue = parseInt('${param.searchLevelValue}');


	if(globalStatusMainName == "notYet"){
		$("#flowHeading").html(""+globalLevelTxt.substr(0,1).toUpperCase()+globalLevelTxt.substr(1)+" Level - Board/Corporation &mdash; Ready to shortlist");
	}
	else{
		$("#flowHeading").html(globalLevelTxt.substr(0,1).toUpperCase()+globalLevelTxt.substr(1)+" Level - Board/Corporation <span>	&mdash;</span> "+globalStatusMainName.substr(0,1).toUpperCase()+globalStatusMainName.substr(1)+"");
	}
	
if(globalPositionId == 0)
	globalStats =" <b class='headingColor'  style='text-transform:uppercase;' >ANY POST</b>  ";
if(globalStats == "readyToShortList")
	globalStats =" <b class='headingColor'  style='text-transform:uppercase;' >"+globalPosName+" POSITION</b>";
else if(globalStats == "positionLink")
	globalStats =" Linking  <b class='headingColor'  style='text-transform:uppercase;' >"+globalPosName+" POST</b>";

$("#headPosId").html(globalStats+" ");
$("#headBrdId").html(headBrdId+" Board");


$("#levelTextId").html("<span>"+globalLevelTxt.substr(0,1).toUpperCase()+globalLevelTxt.substr(1)+"  </span>"); 
$("#deptHeadId").html("<span>"+globalDeptName+" Department</span>");
$("#boardHeadId").html("<span>"+headBrdId+" Board</span>");




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
	$(this).closest('td').find(".appliedPostPopup").show();
	$(this).closest('td').find(".appliedPostPopupArrow").html(' ');
	e.stopPropagation();
	var candidateId = $(this).attr("attr_cand_id");
	var divId = $(this).attr("attr_divId");
	var searchType = $(this).attr("attr_type");
	getBrdWisNominPstAppliedDepOrCorpDetails(candidateId,divId,searchType);
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
		type:type,//type
		statusId:1
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
var globalCadreIds = [];
function buildNominatedPostMemberDetails(result,type,departmentId,boardId,positionId){
	var str='';
	var isEligibleToDelete=false;
		if(entitlementsArr != null && entitlementsArr.length>0){
			for(var i in entitlementsArr){
				if(entitlementsArr[i].trim() =='CADRE_DELETE_ENTITLEMENT'){
					isEligibleToDelete=true;
				}
			}
		}
	if(result.subList != null && result.subList.length > 0){
		str+='<div class="pull-right"> <button class="btn btn-xs btn-mini btn-success"  onclick="getCadreDetailsReport()"> Detailed Report </button></div>'
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
			str+='<th>Applied in Any Dep/Corp</th>';			
			str+='<th>Shortlisted in any dep/ Corp</th>';
			str+='<th>Reference</th>';
			str+='<th>Current Status</th>';
			str+='<th>Update Status</th>';
			str+='<th> DELETE </th>';
		str+='</thead>';
		
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
							str+='<div class="media-body"> '+result.subList[i].cadreName+'</div></div></a>';
						}else{
							str +='<img style="width: 70px;height:70px;border:1px solid #ddd;" src="https://mytdp.com/images/'+ result.subList[i].imageURL+'" class="img-responsive img-circle" onerror="setDefaultImage(this);" alt="Profile"/> '+result.subList[i].voterName+'';
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
							str +='<img style="width: 70px;height:70px;border:1px solid #ddd;" src="https://mytdp.com/images/'+ result.subList[i].imageURL+'" class="img-responsive img-circle" onerror="setDefaultImage(this);" alt="Profile"/> '+result.subList[i].voterName+'';
						}
					</c:otherwise>
				</c:choose>
				str+=' </td>';
				
				if(result.subList[i].tdpCadreId != null && result.subList[i].tdpCadreId > 0){
					globalCadreIds.push(result.subList[i].tdpCadreId);
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
				if(result.subList[i].appliedCount != null && result.subList[i].appliedCount > 0)
					str+='<span class="appliedCount" attr_cand_id="'+result.subList[i].nominatedPostCandidateId+'" attr_divId="departmentsTableId'+i+'" attr_type="applied" style="font-weight:bold;color:green;">'+result.subList[i].appliedCount+'</span>';
				else
					str+='<span> NO </span>';
					str+='<div class="appliedPostPopup" style="margin-left: -71px;">';
						str+='<div class="appliedPostPopupArrow" id="departmentsTableId'+i+'">';
						str+='</div>';
					str+='</div>';
				str+='</td>';
				
				str+='<td style="position:relative" class="text-center">';
					if(result.subList[i].shortListedCount != null && result.subList[i].shortListedCount >0)
						str+='<span class="appliedCount" attr_cand_id="'+result.subList[i].nominatedPostCandidateId+'" attr_divId="shortyListedTableId'+i+'" attr_type="shortlisted" style="font-weight:bold;color:green;">'+result.subList[i].shortListedCount+'</span>';
					
						//str+='<td>'+result.subList[i].shortListedCount+'</td>';
					else
						str+='<span> NO </span>';
						str+='<div class="appliedPostPopup" style="margin-left: -71px;">';
							str+='<div class="appliedPostPopupArrow" id="shortyListedTableId'+i+'">';
							str+='</div>';
						str+='</div>';
					
				str+='</td>';
				
				if(result.subList[i].referCandCount != null)
					str+='<td><a class="referenceCls" data-toggle="modal" data-target="#referModelId" attr_application_id="'+result.subList[i].nominatePostApplicationId+'" style="font-weight:bold;color:green;cursor:pointer;" >'+result.subList[i].referCandCount+'</a></td>';
				else
					str+='<td> - </td>';
				
				str+='<td>'+result.subList[i].status+'</td>';
				str+='<td style="position:relative; width:20%;" >';
				if(type == "this"){
					str+='<button class="btn btn-success btnPopup updateButtonCls" attr_selected_status_id="updatedStatusSelectId'+i+'" arrt_num="'+i+'">UPDATE</button>';
					//str+='<i  style="cursor:pointer;" attr_nomination_post_candidate_id="'+result.subList[i].nominatedPostCandidateId++'" attr_tdp_cadre_id="'+result.subList[i].tdpCadreId+'" class="glyphicon glyphicon-remove remove-icon removeIconCls pull-right" data-toggle="tooltip" data-placement="bottom" title="Remove Candidate"></i>';
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
						str+='<button class="btn btn-success btnPopupThisAny updateButtonThisAnyCls" attr_count="'+i+'">UPDATE</button>';
					
					str+='<button class="btn btn-success btnPopupAny updateButtonAnyCls m_top10" attr_count="'+i+'" attr_applctnId="'+result.subList[i].nominatePostApplicationId+'" style="margin-right:20px;">UPDATE</button>';
					//str+='<i attr_nomination_post_candidate_id="'+result.subList[i].nominatedPostCandidateId+'" attr_tdp_cadre_id="'+result[i].tdpCadreId+'" class="glyphicon glyphicon-remove remove-icon removeIconCls" data-toggle="tooltip" data-placement="bottom" title="Remove Candidate"></i>';
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
							str+='<i class="glyphicon glyphicon-remove pull-right closeDivAnyCls" id="updateDropDownAny'+i+'" style="cursor: pointer; position: absolute; right: 7px; top: 8px;"></i>';
								str+='<div class="row m_top10" >';
								str+='<div class="col-md-3 col-xs-12 col-sm-3">';
									str+='<label>Department</label>';
									str+='<select class="chosenSelect" id="departmentAnyId'+i+'" attr_applctnId="'+result.subList[i].nominatePostApplicationId+'" onchange="getBoardsForDepartments('+i+')">';
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
			 str+='<td>';
				if(isEligibleToDelete){
				str+='<i attr_nomination_post_candidate_id="'+result.subList[i].nominatedPostCandidateId+'" attr_tdp_cadre_id="'+result.subList[i].tdpCadreId+'" ';
                  str+='attr_cadre_name= "'+result.subList[i].cadreName+'"  attr_dept_id="'+departmentId+'" attr_board_id="'+boardId+'" attr_positn_id="'+positionId+'" class="glyphicon glyphicon-remove remove-icon removeIconCls pull-right" data-toggle="tooltip" data-placement="bottom" title="Remove Candidate" style="cursor:pointer;" ></i>';
				} 
				str+='</td>';
			str+='</tr>';
			/*str+='<tr>';
				str+='<td>Preferable<i class="glyphicon glyphicon-list-alt pull-right"></i></td>';
			str+='</tr>';*/
		}
		str+='</table>';
	}
	else{
			str+='No Members are available for shortlisting...';
	}
	
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
	var applicationId = $(this).attr("attr_application_id");
	
	var jsObj=
	   {				
		applicationId:applicationId
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
	str += '<iframe src="https://mytdp.com/nominated_post_documents/'+filePath+'" width="100%" height="800">';    
	str += '</iframe>';
	$("#pdfReportDetailsId").html(str);
}); 

$(document).on("click",".updateButtonCls",function(){
	var selectDivId = $(this).attr("attr_selected_status_id");
	var num = $(this).attr("attr_num");
	$("#successDivId"+num).html('');
	 $("#"+selectDivId).chosen();
	//getApplicationStatus(selectDivId);
});

$(document).on("click",".updateButtonAnyCls",function(){
	$(".updateDropDownThisAny").hide();
	var num = $(this).attr("attr_count");
	getDepartments(num);
	$("#successDivAnyId"+num).html('');
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
	   $("#errorMsg"+num).html('');
	<!--$("#departmentAnyId"+num+" option").remove();-->
	   $("#statusCommentAnyId"+num).html('');
	   $("#updatedStatusAnyId"+num).val(0);
	   //$("#departmentAnyId"+num).val(0).trigger("chosen:updated");
	   $("#boardAnyId"+num).html('');
	   $("#boardAnyId"+num).trigger("chosen:updated")
	   $("#boardAnyId"+num).trigger("chosen:updated")
	   $("#positionAnyId"+num).html('');
	   $("#positionAnyId"+num).trigger("chosen:updated")
	   //$("#departmentAnyId"+num).trigger("chosen:updated");
	   $("#updatedStatusAnyId"+num).trigger("chosen:updated");
	   
	   

	var jsObj={
		postType:1,
		boardLevelId:parseInt('${param.lId}'),
		searchLevelId:parseInt('${param.searchLevelId}'),
		searchLevelValue : parseInt('${param.searchLevelValue}'),
		positionId:parseInt('${positionId}')
	}
	$.ajax({
          type:'GET',
          url: 'getDepartmentsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	    if(result != null && result.length > 0){
		   <!--$("#departmentAnyId"+num).append('<option value="0">Select Department</option>');-->
		   for(var i in result){
			   if(globalDeptId > 0){
					if(globalDeptId == parseInt(result[i].id)){
						$("#departmentAnyId"+num).append('<option selected value="'+result[i].id+'">'+result[i].name+'</option>');
						getBoardsForDepartments(num)
					}
			   }else
					$("#departmentAnyId"+num).append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
		   }
		   $("#departmentAnyId"+num).chosen();
		   
	   }
   });
}

function getBoardsForDepartments(num){   
	
	$("#boardAnyId"+num+" option").remove();
	  $("#boardAnyId"+num).trigger('chosen:updated');
	$("#positionAnyId"+num+" option").remove();
	$("#positionAnyId"+num).trigger('chosen:updated');
	var depmtId = $("#departmentAnyId"+num).val();
	$("#statusCommentAnyId"+num).html('');
	 $("#updatedStatusAnyId"+num).val(0);
	  $("#updatedStatusAnyId"+num).trigger("chosen:updated");
	  var applctnId = $("#departmentAnyId"+num).attr("attr_applctnId");
	var jsObj={
		depmtId : depmtId,
		boardLevelId:parseInt('${param.lId}'),
		searchLevelValue : parseInt('${param.searchLevelValue}'),
		searchLevelId : parseInt('${param.searchLevelId}'),
		applicationId:applctnId,
		positionId:parseInt('${positionId}')
		
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
	
	$("#errorMsg"+num).html('');
	
	$("#positionAnyId"+num+" option").remove();
	 $("#positionAnyId"+num).trigger('chosen:updated');
	 $("#updatedStatusAnyId"+num).val(0);
	 $("#statusCommentAnyId"+num).html('');
	var depmtId = $("#departmentAnyId"+num).val();
	var boardId = $("#boardAnyId"+num).val();
	
	$("#updatedStatusAnyId"+num).trigger("chosen:updated");
	 if(boardId==0)
	{
		return;
	}
	var jsObj={
		depmtId : depmtId,
		boardId : boardId,
		boardLevelId:parseInt('${param.lId}'),
		searchLevelValue : parseInt('${param.searchLevelValue}'),
		searchLevelId : parseInt('${param.searchLevelId}'),
		nominatedPostCandId:0
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
			   if(parseInt('${positionId}') > 0 ){
				   if(result[i].id == parseInt('${positionId}'))
				 $("#positionAnyId"+num).append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
			   }else{
				   $("#positionAnyId"+num).append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
			   }
			   /* if(globalPositionId>0){
				   if( globalPositionId == parseInt(result[i].id)){
					   $("#positionAnyId"+num).append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
				   }else{
					   $("#positionAnyId"+num).append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
				   }
				}else{
					$("#positionAnyId"+num).append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
				} */
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
	 $("#"+divId).html("");
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
	
	if(status == 2){
		$(this).hide();
		updateApplicationStatusDetailsAction(applicationId,status,comment,
		levelId,levelVal,deptId,boardId,positionId,candidateId,divId);
		return;
	}
	var jsObj=
	   {				
		nominatePostApplicationId:applicationId,
		levelId : levelId,
		levelVal : levelVal,
		deptId : deptId,
		boardId : boardId,
		positionId : positionId,
		candidateId : candidateId
		}
    $.ajax({
          type:'GET',
          url: 'isApplicationAlreadyShortlistedAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	  
	    if(result == "Shortlisted"){
			$(this).show();
			$("#"+divId).html("Already shortlisted for this Candidate").css("color","red");
		}else{
			$(this).hide();
			updateApplicationStatusDetailsAction(applicationId,status,comment,levelId,levelVal,deptId,boardId,positionId,candidateId,divId);
		}
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


	 $("#successDivAnyId"+num).html('');
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
if(statusId == 3 || statusId == 0){
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
}else if(statusId ==  2 || statusId == 0){
	 $("#successDivAnyId"+num).html('');
	$("#departmentAnyId"+num).val(0);
	$("#boardAnyId"+num).val(0);
	$("#positionAnyId"+num).val(0);
	if(comment =='' || comment.trim().length == 0){
			$("#successDivAnyId"+num).html("  <b style='color: red;' > Comment is required.</b>");
			return;
	}
	
	savingAnyPostCandidatesToPosition(applicationId,candidateId,levelId,levelVal,deptId,boardId,positionId,statusId,comment,num);
	return;
}
	
	$("#successDivAnyId"+num).html('<img id="" src="images/icons/loading.gif" style="width:15px;"/>');
	$('#'+butonId+'').hide();
	
	var jsObj=
	   {	
		nominatePostApplicationId:applicationId,
		levelId : levelId,
		levelVal : levelVal,
		deptId : deptId,
		boardId : boardId,
		positionId : positionId,
		candidateId : candidateId
		}
    $.ajax({
          type:'GET',
          url: 'isApplicationAlreadyShortlistedAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   $("#successDivAnyId"+num).html('');
	  if(result == "Shortlisted"){
			$('#'+butonId+'').show();
			$("#successDivAnyId"+num).html("Already shortlisted for this Candidate").css("color","red");
		}else{
			$('#'+butonId+'').hide();
			savingAnyPostCandidatesToPosition(applicationId,candidateId,levelId,levelVal,deptId,boardId,
			positionId,statusId,comment,num);
		}
   });
});

getNominatedPostPostionDetails();
getBoardWiseNominatedPostMemberDetails();
function getNominatedPostPostionDetails(){
$('#positionDivId').html(' <img style="margin-left: 400px; margin-top: 20px; width: 20px; height: 20px;" id="" class="offset7" src="images/search.gif">');

var positionId=parseInt('${positionId}');
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
			  buildNominatePostPositionDetails(result,positionId);
		  }
	   });  
}
function buildNominatePostPositionDetails(result,positionId){
	 var str='';
		   if(result !=null && result.length>0){
			   	str+='<table class="table table-bordered" id="nominatePositionDetilsId" style="background-color:#EFF3F4">';
					str+='<thead>';
					str+='<tr>';
					str+='<th rowspan="2"></th>';
					//str+='<th rowspan="2">TOTAL AVAILABLE POSTS</th>';
					//str+='<th rowspan="2">APPLICATIONS RECEIVED</th>';
					//str+='<th rowspan="2">SHORTLISTED</th>'; 
					str+='<th rowspan="2">TOTAL POSTS</th>';
					str+='<th rowspan="2">OPEN POSTS</th>';
					str+='<th rowspan="2">READY TO SHORT LIST</th>';
					str+='<th rowspan="2">READY TO FINAL REVIEW</th>';
					<!--str+='<th rowspan="2">READY FOR FINAL REVIEW</th>';-->
					str+='<th rowspan="2">FINALIZED/GO ISSUED</th>';
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
				   
				   //alert(i);
				   
				   if(positionId !=null && positionId>0){
						if(result[i].id != null && result[i].id > 0){
							str+='<tr>';
							str+='<td><p>THIS POSITION';
							str+='<i style="cursor: pointer; font-size: 12px; margin-top: 10px;color:#524C4C;" class="glyphicon glyphicon-info-sign "  title="Requested for this post members shortlisted..."></i></p> </td>';
							//<small>Requested for this post members shortlisted</small></td>';
							//str+='<td>02</td>';
							//str+='<td>'+result[i].receivedCount+'</td>';
							//str+='<td>'+result[i].shortListedCount+'</td>';	
							//str+='<td>'+result[i].shortListedCount+'</td>';
                            str+='<td>'+result[i].totalPosts+'</td>';
                            str+='<td>'+result[i].openPosts+'</td>';								
							str+='<td>'+result[i].readyToShortListedCnt+'</td>'; 
                            str+='<td>'+result[i].shortListedCount+'</td>';								
							str+='<td>'+result[i].finalizeAndGOCount+'</td>';	
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
						/*else {
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
						}*/
				   }/*else{
					   
					   //alert(i+"22");
					   
					   if(result[i].id == null || result[i].id ==0){
						   //alert(i+"22"+i); 
						   str+='<tr>';
								str+='<td><p> ANY POST  </p><small>Requested for any post members shortlisted for this</small></td>';
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
							
				   }*/
				}	
				str+='<tr><td colspan="14"><p> <b> NOTE :</b> Here Caste and Age details may or may not matched . Because no data updated respective attributes. </p> </td></tr>';
				if(globalPostId>0)  
				{
					$("#positionDivId").html(str);
				}else{
					$("#positionDivId").closest(".panelDepartmentHead").remove();
				}
			str+='</tbody>';
            str+='</table>';			
	 
		   }
}
function getBrdWisNominPstAppliedDepOrCorpDetails(candidateId,divId,searchType){
	
	var jsObj=
	{				
		candidateId:candidateId,
		searchType:searchType
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
	str+='<div id="tableScrollId">';
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
			if(result[i].perc == "GO Expired")
			str+='<tr style=" background-color:gray;">';
		else
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
				if(result[i].perc == "GO Expired"){
					str+='<td style="color:red;" ><b>'+result[i].perc+'</b></td>';
				}else{
					str+='<td>'+result[i].perc+'</td>';
				}
				
			str+='</tr>';
		}
		str+='</tbody>';
	str+='</table>';
	str+='</div>';
	$("#"+divId).html(str);
	if(result.length > 5)
	{
		$("#tableScrollId").mCustomScrollbar({setHeight: '200px'})
	}
}
function checkPositionAvailableOrNot(num){
	$("#updatedStatusAnyId"+num).val(0);
	$("#updatedStatusAnyId"+num).trigger("chosen:updated");
	var positionName = $("#positionAnyId"+num).find(":selected").text();
		 if($("#positionAnyId"+num).val()==0)
		 {
			 return;
		 }
	 var jsObj={
		departmentId:$("#departmentAnyId"+num).val(),
		boardId     : $("#boardAnyId"+num).val(),
		positionId  : $("#positionAnyId"+num).val(),
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
		  $("#errorMsg"+num).html("");
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
	
	 function updateApplicationStatusDetailsAction(applicationId,status,comment,levelId,levelVal,deptId,boardId,
	 positionId,candidateId,divId){
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
			setTimeout(function(){getBoardWiseNominatedPostMemberDetails();
				getNominatedPostPostionDetails();
			}, 1000);
			
		}
		else
			$("#"+divId).html("Sorry,Exception Occured...Please try again...").css("color","red"); 
   });
	}
	
	function savingAnyPostCandidatesToPosition(applicationId,candidateId,levelId,levelVal,deptId,boardId,positionId,statusId,comment,num){

	$("#successDivAnyId"+num).html('<img id="" src="images/icons/loading.gif" style="width:15px;"/>');
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
		   setTimeout(function(){getBoardWiseNominatedPostMemberDetails();getNominatedPostPostionDetails();}, 1000);
   });
   }
   
   function refreshingPage(){
	   
	   window.location.reload();
   }
   
   </script>
   <script>
	
function getCadreDetailsReport(){
	$("#detailedDiv1").html("");
	$('#detailedReprot').modal('show');
	$('#detailedDiv1').html(' <img style="margin-left: 400px; margin-top: 20px; width: 20px; height: 20px;" id="" class="offset" src="images/search.gif">');
	var tdpCadreIdsArr  = [];
	
	var jsObj={
		tdpCadreIds:globalCadreIds	
	}
	$.ajax({
          type:'GET',
          url: 'getCadreDetailedReportAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	  
	 if(result.subList != null && result.subList.length>0){
		  buildCadreDetailedReport(result);
	  }else{
		$("#detailedDiv1").html("<center>NO DATA AVAILABLE</center>");  
	  }
   }); 
}
function exportToExcel()
{
	tableToExcel('detailRepTableId', 'District Wise Committees');
}
function buildCadreDetailedReport(result){
	var str='';
	if(result.subList != null && result.subList.length>0){
		str+='<span class="btn btn-info pull-right excelId form-inline" onclick="exportToExcel()" style="display:inline-block;"> Export To Excel </span>';
			str+='<table id="detailRepTableId" class="table table-bordered table-condensed">';
			
			str+='<thead>';
			str+='<tr>';
			//str+='<th rowspan="2" > SNO </th>';
			str+='<th rowspan="2"> PHOTO </th>';
			str+='<th rowspan="2"> NAME  </th>';
			str+='<th rowspan="2"> DISTRICT  </th>';
			str+='<th rowspan="2"> CONSTITUENCY  </th>';
			str+='<th rowspan="2"> PARTY DESIGNATION  </th>';
			str+='<th rowspan="2"> GOVT DESIGNATION  </th>';
			str+='<th rowspan="2"> CELL NO  </th>';
			str+='<th rowspan="2"> MEMBERSHIP NO  </th>';
			str+='<th rowspan="2"> CASTE NAME  </th>';
			str+='<th rowspan="2"> SUB CASTE NAME  </th>';
			str+='<th colspan="4" class="text-center"> MEMBERSHIP(Own) </th>';
			//str+='<th colspan="6"> 2009 Election Performance </th>';
			str+='<th colspan="4" class="text-center"> 2014 Election Performance(Own) </th>';
			str+='<th colspan="4" class="text-center"> 2014 Membership Enrollment(Own) </th>';
			str+='<th colspan="4" class="text-center"> 2016 Membership Enrollment(Own) </th>';
			//str+='<th colspan="5"> 2016 Membership Enrollment </th>';
			
			 str+='<th rowspan="2"> 2015 Mahanadu Attendance </th>';
			str+='<th rowspan="2"> 2016 Mahanadu Attendance </th>';
			
			str+='<th rowspan="2"> Training Attendance </th>';
			str+='<th rowspan="2"> Invited Party Meetings </th>';
			str+='<th rowspan="2"> Attended Party Meetings </th>';
			//str+='<th rowspan="2"> Involved Alerts </th>';
			
			str+='</tr>';
			str+='<tr>';
			str+='<th> 2010  </th>';
			str+='<th> 2012  </th>';
			str+='<th> 2014  </th>';
			str+='<th> 2016  </th>';
			str+='<th> Constituency%  </th>';
			str+='<th> Mandal/Own Muncipality%  </th>';
			str+='<th> Panchayat/Own Ward%  </th>';
			str+='<th> Booth%  </th>';
			//str+='<th> Booth Influence%  </th>';
			
			str+='<th> Constituency%  </th>';
			str+='<th>  Mandal/Own Muncipality%  </th>';
			str+='<th>  Panchayat/Own Ward%  </th>';
			str+='<th>  Booth%  </th>';
			//str+='<th> Booth Influence%  </th>';
			
			str+='<th> Constituency%  </th>';
			str+='<th> Mandal/Own Muncipality%  </th>';
			str+='<th> Panchayat/Own Ward%  </th>';
			str+='<th> Booth%  </th>';
			//str+='<th> Booth Influence%  </th>';
			str+='</tr>';
			
			str+='</thead>';
			str+='<tbody>';
			for(var i in result.subList){
				str+='<tr>';
					//str+='<td>'+(parseInt(i)+1)+'</td>';
					str+='<td> <img style="width:50px;height:50px;" src="https://www.mytdp.com/images/cadre_images/'+result.subList[i].cadreBasicPerformaceVO.imagePath+'"/></td>';
					str+='<td> '+result.subList[i].cadreBasicPerformaceVO.name+'</td>';
					str+='<td> '+result.subList[i].cadreBasicPerformaceVO.districtName+' </td>';
					str+='<td> '+result.subList[i].cadreBasicPerformaceVO.constituencyName+' </td>';
					
					if(result.subList[i].cadreBasicPerformaceVO.designation != null)
						str+='<td> '+result.subList[i].cadreBasicPerformaceVO.designation+' </td>';
					else
						str+='<td> - </td>';
					
					if(result.subList[i].cadreBasicPerformaceVO.partyPosition != null)
						str+='<td> '+result.subList[i].cadreBasicPerformaceVO.partyPosition+' </td>';
					else
						str+='<td> - </td>';
					
					str+='<td> '+result.subList[i].cadreBasicPerformaceVO.mobileNO+' </td>';
					str+='<td> '+result.subList[i].cadreBasicPerformaceVO.membershipNo+' </td>';
					str+='<td> '+result.subList[i].cadreBasicPerformaceVO.casteGroup+' </td>';
					str+='<td> '+result.subList[i].cadreBasicPerformaceVO.casteName+' </td>';
					
					if(result.subList[i].cadreBasicPerformaceVO.subList != null && result.subList[i].cadreBasicPerformaceVO.subList.length>0){
						for(var j in result.subList[i].cadreBasicPerformaceVO.subList){
						
								str+='<td> ';
								if(result.subList[i].cadreBasicPerformaceVO.subList[j].status =='YES')	{								
								str+='<ul class="enrolled-mem">';
									str+='<li class="yes">'+result.subList[i].cadreBasicPerformaceVO.subList[j].year+'<span></span></li>&nbsp;';
									str+=' </ul> ';
								}
								else{
									str+='<ul class="enrolled-mem">';
									str+='<li class="no">'+result.subList[i].cadreBasicPerformaceVO.subList[j].year+'<span></span></li>&nbsp;';
									str+=' </ul> ';
								}
									str+='</td> ';								
							
						
						}
					}
					
					if(result.subList[i].cadreStatsVO != null && result.subList[i].cadreStatsVO.subList != null && result.subList[i].cadreStatsVO.subList.length>0){
						for(var j in result.subList[i].cadreStatsVO.subList){
							if(result.subList[i].cadreStatsVO.subList[j].assemblyPerc != null)
								str+='<td>'+result.subList[i].cadreStatsVO.subList[j].assemblyPerc+'</td>';
							else
								str+='<td> - </td>';
							
							if(result.subList[i].cadreStatsVO.subList[j].mandalORMuncORUrbanPerc != null)
								str+='<td>'+result.subList[i].cadreStatsVO.subList[j].mandalORMuncORUrbanPerc+'</td>';
							else
								str+='<td> - </td>';
							if(result.subList[i].cadreStatsVO.subList[j].panchayatORWardPerc != null)
								str+='<td>'+result.subList[i].cadreStatsVO.subList[j].panchayatORWardPerc+'</td>';
							else
								str+='<td> - </td>';
							if(result.subList[i].cadreStatsVO.subList[j].boothPerc != null)
								str+='<td>'+result.subList[i].cadreStatsVO.subList[j].boothPerc+'</td>';
							else
								str+='<td> - </td>';
							
						}
					}
					
					if(result.subList[i].cadreEventsVO != null && result.subList[i].cadreEventsVO.subList != null && result.subList[i].cadreEventsVO.subList.length>0){
						for(var j in result.subList[i].cadreEventsVO.subList){
							if(result.subList[i].cadreEventsVO.subList[j].name =='PartyMeetings' || result.subList[i].cadreEventsVO.subList[j].name =='Alert'){
								if(result.subList[i].cadreEventsVO.subList[j].invitedCount != null && result.subList[i].cadreEventsVO.subList[j].invitedCount>0)
									str+='<td> '+result.subList[i].cadreEventsVO.subList[j].invitedCount+' </td>';
								else
									str+='<td> '+result.subList[i].cadreEventsVO.subList[j].attendedCount+' </td>';
							
							}else if(result.subList[i].cadreEventsVO.subList[j].name == 'Mahanadu - 2015' && result.subList[i].cadreEventsVO.subList[j].id == 7){
								if(result.subList[i].cadreEventsVO.subList[j].attendedCount != null && result.subList[i].cadreEventsVO.subList[j].attendedCount>0){
									str+='<td >';
									str+='<ul class="enrolled-mem enrolledMembr2015Cls" attr_cdr_id ="'+result.subList[i].cadreBasicPerformaceVO.id+'" style="cursor:pointer;" >';
									str+='<li class="yes" > YES <span></span></li>&nbsp;';
									str+=' </ul> </td> ';
									
								}else{
									//str+='<td> NO </td>';
									str+='<td>  ';
									str+='<ul class="enrolled-mem">';
									str+='<li class="no" > NO <span></span></li>&nbsp;';
									str+=' </ul> ';
									str+='</td>  ';
								}
							}else{
								if(result.subList[i].cadreEventsVO.subList[j].attendedCount != null && result.subList[i].cadreEventsVO.subList[j].attendedCount>0){
									str+='<td >  ';
									str+='<ul class="enrolled-mem ">';
									str+='<li class="yes" > YES <span></span></li>&nbsp;';
									str+=' </ul> </td> ';
									
								}else{
									//str+='<td> NO </td>';
									str+='<td>  ';
									str+='<ul class="enrolled-mem">';
									str+='<li class="no"> NO <span></span></li>&nbsp;';
									str+=' </ul> ';
									str+='</td>  ';
								}
							}
						}
					}
					
				str+='</tr>';
			}
			str+='</tbody>';
			str+='</table>';
	}
	$("#detailedDiv1").html(str);
	$("#detailRepTableId").dataTable({
			"iDisplayLength": 10,
			"aLengthMenu": [[10, 50, 100, -1], [10, 50, 100, "All"]]
		});
}
   $(document).on("click",'.enrolledMembr2015Cls', function() {
	   var tdpCadreId =$(this).attr("attr_cdr_id");
	       getMahanaduEventDetilsByTdpCadreId(tdpCadreId);
	   $("#mahanadu2015DetilsId").html("");
	   $('#mahanaduDetailedReprotsId').modal('show');
	
    }); 
	
function getMahanaduEventDetilsByTdpCadreId(tdpCadreId){
		var jsObj = { 
				 parentEventId : 7,                  
				 tdpCadreId :tdpCadreId
				 
			    }
	$.ajax({
		type : 'POST',
		url : 'getMahanaduEventDetilsByCadreIdsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		 if(result != null && result.length>0){
			 buildMahanaduEventDetilsByTdpCadreWise(result);
		 }else{
			 $("#mahanadu2015DetilsId").html("<center>NO DATA AVAILABLE</center>");
		 }
	});
}
  function buildMahanaduEventDetilsByTdpCadreWise(result){
	 var str ='';
	 str +='<table class ="table table-bordered" id="dataTableCadreDetailsIds">';
	 str+='<thead>';
	  str+='<th>Event Name</th>';
		   var locationList = result[0].locationList;
		       str+='<th>'+locationList[0].dateStr+'</th>';
			   str+='<th>'+locationList[1].dateStr+'</th>';  
			   str+='<th>'+locationList[2].dateStr+'</th>';
	  str+='</thead>';
	  str +='<tbody>';
	  for(var i in result){
		   str+='<tr>';
		   var locationList = result[i].locationList;
		   var name = result[i].name;
		   var id = result[i].id;
		   if((name == "Main Entry" || name == "Blood Donation Camp" || name == "Exit") 
			       && (id==8 || id==12 || id == 16)){
			       str+='<td>'+result[i].name+'</td>';
					   for(var j in locationList){
					   var date = locationList[j].dateStr;
						 if(date == "2015-05-27"){
							var time = locationList[j].time;
							  if(time != null){
									 str+='<td>'+time+'</td>';
								 }else{
									 str+='<td> - </td>';
								 }	 
						  }else if(date == "2015-05-28"){
							 var time = locationList[j].time;
							   if(time != null){
									 str+='<td>'+time+'</td>';
								 }else{
									 str+='<td> - </td>';
								 } 	 
						  }else if(date == "2015-05-29"){
							  var time = locationList[j].time;
								if(time != null){
									 str+='<td>'+time+'</td>';
								}else{
									 str+='<td> - </td>';
								} 
						  }
					   }
		    str+='</tr>';
		   }	        
	 }  
	 str +='</tbody>';
     str +='</table>';	 
	 $("#mahanadu2015DetilsId").html(str);
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
</script>
</body>
</html>
