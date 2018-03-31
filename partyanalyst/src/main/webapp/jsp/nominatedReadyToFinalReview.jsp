<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Ready To Final Review</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/NominatedPost/custom.css" rel="stylesheet" type="text/css">
<link href='https://fonts.googleapis.com/css?family=Roboto:400,500,700,700italic,900,900italic,400italic,500italic,300italic,300,100italic,100' rel='stylesheet' type='text/css'>
<link href="newCoreDashBoard/Plugins/Date/daterangepicker.css" rel="stylesheet" type="text/css"/>
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
<link href="dist/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css"/>
<link href="dragAndDropPhoto/css/jquery.filer.css" type="text/css" rel="stylesheet" />
<link href="dragAndDropPhoto/css/themes/jquery.filer-dragdropbox-theme.css" type="text/css" rel="stylesheet" />
<style type="text/css">
.updateDropDownArrowModal::after, .updateDropDownArrowModal::before
{
	right:0px;
	left:35px;
}
.updateDropDown
{
	width:400px;
	text-align:left;
}
#resultDivId
{
	min-height:400px;
}
.filterIcon
{
	background:#ddd;
	padding:6px;
	border-radius:50%;
	cursor:pointer
}
.jFiler-input-dragDrop
{
	width:100%;
}
.panelGO
{
	border:2px solid #CCC;
}
.panelGO .panel-heading , .panelGO .panel-body , .panelGO .panel-heading , .panelGO .panel-footer
{
	background-color:#EFF3F4
}
.selectBox
{
	padding:8px 12px;
	border:2px solid #DDD;
	background-color:#fff;
	position:relative;
	text-transform:capitalize;
	cursor:pointer;
}
.selectBox.active:after
{
	font-family: 'Glyphicons Halflings';
	content:'\e014';
	display:block;
	position:absolute;
	right:10px;
	font-size:10px;
	background-color:#535353;
	color:#fff;
	padding:2px 4px;
	border-radius:50%;
	top:10px;
} 
.filterIcon
{
	background:#ddd;
	padding:4px;
	border-radius:50%;
	cursor:pointer;
	top:-3px
}
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
   <div class="row hideRowCls">
		<div class="col-md-12 col-xs-12 col-sm-12">
			<ul class="geoGrpahicBreadCrumb">
				<li style="text-transform: uppercase; font-weight: 500;" data-placement="bottom" data-toggle="tooltip" title="Nominated Posts Overview Details"><a href="nominatedPostApplicationReviewAction.action"><i class="glyphicon glyphicon-home" style="color:#fff;"></i></a></li>
				<li id="flowHeading" style="text-transform: uppercase; font-weight: 500;"></li>
			</ul>
		</div>
		<!--<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
        	<h3 class="text-capitalize headingColor" style="font-weight:300"><span id="headStsId"></span> <i class="glyphicon glyphicon-filter filterIcon filterBtn pull-right"></i></h3>
        </div>-->
		<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
	  <h3 class="text-capital" id="headinggId" style="color:#5C2D25;display:none"></h3>
		<h3 class="text-capital headingColor hideFilterCls" ><i class="pull-right glyphicon glyphicon-filter filterBtn filterIcon" title="Select Locations" ></i></h3>
		<div class="row hideFilterCls">
		<div class="col-md-12 col-xs-12 col-sm-8 col-sm-offset-2 col-md-offset-0 filterSection">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="row">
						<div class="col-md-3 col-xs-12 col-sm-12 hideStateDivCls" id="stateMainId" >
							<label>State</label>
							<select class="form-control" id="stateId">
								<option value="0">All</option>
								<option value="1">Andhra Pradesh</option>
								<option value="36">Telangana</option>
							</select>
						</div>
						<div class="col-md-3 col-xs-12 col-sm-12 hideDistrictDivCls">
							<label>District</label>
							<select class="form-control" id="districtId">
							</select>
						</div>
						<div class="col-md-3 col-xs-12 col-sm-12 hideConstituencyDivCls">
							<label>Constituency</label>
							<select class="form-control" id="constituencyId">
							</select>
						</div>
						<div class="col-md-3 col-xs-12 col-sm-12 hidemanTowDivCls">
							<label>Mandal/Town/Division</label>
							<select class="form-control" id="manTowDivId">
							</select>
						</div>
						<div class="col-md-3 col-xs-12 col-sm-12">						
							<input type="button" class="btn btn-primary btn-sm" value="Submit" style="margin-top: 25px;" id="submitBtnId"/>
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
    	
		<div class="col-md-4 col-xs-12 col-sm-8 col-sm-offset-2 col-md-offset-0  heightSet" style="background:#DFDFDF;padding-right:0px;border:1px solid #CCC;padding-left:5px;">
          <div class="pad_15">
	          <h4 class="headingColor text-capital"><u>departments</u></h4>
          </div>
		   <div id="candiateReadyToFinalReviewDivId"></div>
        </div>
        <div class="col-md-4 col-xs-12 col-sm-8 col-sm-offset-2 col-md-offset-0 pad_0 heightSet boardCorporation" style="padding-right:0px;background:#EBEBEB;display:none;border-top:1px solid #CCC;border-bottom:1px solid #CCC;border-right:1px solid #CCC;">
        	<div class="tab-content">
			 <div id="boardRsltDivId"></div>
            </div>
        </div>
        <div class="col-md-4 col-xs-12 col-sm-8 col-sm-offset-2 col-md-offset-0 pad_0 heightSet positionsDivCls" style="padding-right:0px;background:#F6F6F6;display:none;border-top:1px solid #CCC;border-bottom:1px solid #CCC;border-right:1px solid #CCC;">
        	<div class="tab-content">
				<div id="positionRsltDivId"></div>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="postDetails" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">   
  <div class="modal-dialog" style="width:85%;" role="document">
    <div class="modal-content chairmenFinaliseModal">
      <div class="modal-header">
        
      </div>
      <div class="modal-body">
	   <div id="postDetailsResultDivId"></div>        
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" style="width:85%;" role="document">
    <div class="modal-content chairmenFinaliseModal">
      <div class="modal-header">
        
        <div class="row">
            <div class="col-md-10 col-xs-12 col-sm-8">
                <h3> <span id="actionId" > </span>- <span id="headingPostId"></span></h3>
                <p id="totalHeadingId"></p>
            </div>
            <div class="col-md-2 col-xs-12 col-sm-4">
            	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <div class="wishList">
                	<h1 class="textEffect"><img src="dist/nominatedImages/Icon4.png" alt="count"/><span id="wishListCountId" class="textEffect"> 0 </span></h1>
                	<p class="text-success">Added to wishlist</p>
                </div>
            </div>
        </div>
      </div>
      <div class="modal-body">
	   <div id="resultDivId"></div>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" tabindex="-1" id="pdfModelId" role="dialog">  
	<div class="modal-dialog" style="width:60%;">      
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close pdfModalCloseBtn" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">CADRE REPORT DETAILS</h4>
			</div>
			<div class="modal-body" id="pdfReportDetailsId">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default pdfModalCloseBtn" data-dismiss="modal">Close</button>
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
			<div class="modal-body" id="referenceDetailsId">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<div class="modal fade bs-example-modal-lg"  id="goAssignPopup" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
  <div class="modal-dialog modal-lg" role="document" style="width:90%;">
    <div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close pdfModalCloseBtn" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h4 class="modal-title">G.O APPROVED</h4>
		</div>
		<div class="modal-body">
			<div id="goAssignPopupInDivId"></div>
		</div>
    </div>
  </div>
</div>

<div class="modal fade" id="nominatedPostDetailsModalId" tabindex="-1"  role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-lg" role="document" style="margin:auto">
    <div class="modal-content">
      <div class="modal-header">
	      <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	    <h4 class="modal-title text-capital" id="postHeadingId"></h4>
	  <div class="modal-body">
	    <div class="row">
			<div class="col-md-12 col-xs-12 col-sm-12">
			   <div ><center ><img style="display: none;" src="images/icons/loading.gif" id="nominatedPostDlsProcessingImgId"></center></div>
				<div id="nominatedPostDtlsTblId"> </div>    
			</div>      
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
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
<script src="js/nominatedPosts/nominatedReadyToFinalReview.js" type="text/javascript"></script>
<script type="text/javascript" src="js/nominatedPosts/deleteCandidateNominatedPost.js"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script type="text/javascript" src="dragAndDropPhoto/js/customNominated.jquery.filter.min.js?v=1.0.5"></script>
<script type="text/javascript">

var gblStatus = '${param.sts}'; 
$("#headStsId").html(gblStatus+" Nominated Post details");
if(gblStatus !=null && gblStatus.trim().length>0){
	$(document).prop('title', capitalizeFirstLetter(gblStatus.toLowerCase())+" nominated post details");
}
function capitalizeFirstLetter(stringStr){
	return stringStr.charAt(0).toUpperCase() + stringStr.slice(1);
}
var windowUrl = window.location.href;
var wurl = windowUrl.substr(0,(windowUrl.indexOf("/nominatedReadyToFinalReviewAction")));
wurl = wurl.replace("/PartyAnalyst","");

var getHeight = $(window).height();
getHeight = (getHeight -( ( getHeight/100 ) * 10));

$(".newPostApplyPopup").css("height",getHeight)
$(document).on("click",".appleNewPostBtn",function(){
	var pId = $(this).attr("attr_position_id");
	var dId = $(this).attr("attr_department_id");
	var bId = $(this).attr("attr_board_id");
	var lId= $(this).attr("attr_level_id");
	
	$(".newPostApplyPopup").attr("src",wurl+"/nominatedPostProfileAction.action?status=openPost&lId="+lId+"&deptId="+dId+"&boardId="+bId+"&positionId="+pId+"");
	setTimeout(function(){
		$("#newApplyPost").modal('show');
	}, 1000);
});
function setHeight(){
	var maxHeight = 0;
	$(".heightSet").height("100%");
	$(".heightSet").each(function(){
	   if ($(this).height() > maxHeight) { maxHeight = $(this).height(); }
	});
	$(".heightSet").height(maxHeight);
}
$(document).on("click",".modalViewBtn",function(){  
	if(gblStatus != "goPassed"){
		$("#myModal").modal('show');
	}
});
$(document).on("click",".commentsBtn",function(e){
	$(".commentsDiv").hide();
	$(this).closest('tr').find(".commentsDiv").show();
	e.stopPropagation()
});
$(document).on("click",".updateBtnDrop",function(e){
	$(this).closest('tr').find(".updateDropDown").show();
	if(gblStatus=="finaliZed"){
		$(".updateDropDown").hide().html(' ');
		$(this).closest('tr').find(".updateDropDown").show().html(strglob);
		var t = $(this).attr("attr_nominatedPostApplicationId");
		var d = $(this).attr("attr_department_id"); 
		var b = $(this).attr("attr_doard_id"); 
		var p = $(this).attr("attr_position_id"); 
		
		$(document).find(".saveGoForCandidateCls").attr("attr_nominatedPostApplicationId",t);
		$(document).find(".saveGoForCandidateCls").attr("attr_department_id",d);
		$(document).find(".saveGoForCandidateCls").attr("attr_doard_id",b);
		$(document).find(".saveGoForCandidateCls").attr("attr_position_id",p);
		
		
		$('#filer_input').filer({
			changeInput: '<div class="jFiler-input-dragDrop"><div class="jFiler-input-inner"><div class="jFiler-input-icon"><i class="icon-jfi-folder"></i></div><div class="jFiler-input-text"><h3>Click on this box</h3> <span style="display:inline-block; margin: 15px 0">or</span></div><a class="jFiler-input-choose-btn blue">Browse Files</a></div></div>',
			showThumbs: true,
			theme: "dragdropbox",
			templates: {
				box: '<ul class="jFiler-items-list jFiler-items-grid"></ul>',
				item: '<li class="jFiler-item">\
							<div class="jFiler-item-container">\
								<div class="jFiler-item-inner">\
									<div class="jFiler-item-thumb">\
										<div class="jFiler-item-status"></div>\
										<div class="jFiler-item-info">\
											<span class="jFiler-item-title"><b title="{{fi-name}}">{{fi-name | limitTo: 25}}</b></span>\
											<span class="jFiler-item-others">{{fi-size2}}</span>\
										</div>\
										{{fi-image}}\
									</div>\
									<div class="jFiler-item-assets jFiler-row">\
										<ul class="list-inline pull-left">\
											<li>{{fi-progressBar}}</li>\
										</ul>\
										<ul class="list-inline pull-right">\
											<li><a class="icon-jfi-trash jFiler-item-trash-action"></a></li>\
										</ul>\
									</div>\
								</div>\
							</div>\
						</li>',
				itemAppend: '<li class="jFiler-item">\
					<div class="jFiler-item-container">\
						<div class="jFiler-item-inner">\
							<div class="jFiler-item-thumb">\
								<div class="jFiler-item-status"></div>\
								<div class="jFiler-item-info">\
									<span class="jFiler-item-title"><b title="{{fi-name}}">{{fi-name | limitTo: 25}}</b></span>\
									<span class="jFiler-item-others">{{fi-size2}}</span>\
								</div>\
								{{fi-image}}\
							</div>\
							<div class="jFiler-item-assets jFiler-row">\
								<ul class="list-inline pull-left">\
									<li><span class="jFiler-item-others">{{fi-icon}}</span></li>\
								</ul>\
								<ul class="list-inline pull-right">\
									<li><a class="icon-jfi-trash jFiler-item-trash-action"></a></li>\
								</ul>\
							</div>\
						</div>\
					</div>\
				</li>',
				progressBar: '<div class="bar"></div>',
				itemAppendToEnd: true,
					removeConfirmation: true,
					_selectors: {
						list: '.jFiler-items-list',
						item: '.jFiler-item',
						progressBar: '.bar',
						remove: '.jFiler-item-trash-action'
					}
				},
				dragDrop: {
					dragEnter: null,
					dragLeave: null,
					drop: null,
				},
				
				files: null,
				addMore: true,
				clipBoardPaste: true,
				excludeName: null,
				beforeRender: null,
				afterRender: null,
				beforeShow: null,
				beforeSelect: null,
				onSelect: null,
				afterShow: null,
			   
				onEmpty: null,
				options: null,
				captions: {
					button: "Choose Files",
					feedback: "Choose files To Upload",
					feedback2: "files were chosen",
					drop: "Drop file here to Upload",
					removeConfirmation: "Are you sure you want to remove this file?",
					errors: {
						filesLimit: "Only {{fi-limit}} files are allowed to be uploaded.",
						filesType: "Only Images are allowed to be uploaded.",
						filesSize: "{{fi-name}} is too large! Please upload file up to {{fi-maxSize}} MB.",
						filesSizeAll: "Files you've choosed are too large! Please upload files up to {{fi-maxSize}} MB."
					}
				}
				
		});	
	}	
	//e.stopPropagation();
	$(".chosenSelect").chosen();
	$(".dateR").daterangepicker({
		opens:'left'
	});
	
});
$(document).on("click",".input-mini",function(e){
	e.stopPropagation();
	
});	
$(document).on("click",".btnUpdateAll",function(e){
	if(gblStatus=="finaliZed"){
		$(".updateDropDown").hide().html(' ');
		$(".updateAllShowPopup").show().html(strglob);	
		$(document).find(".saveGoForCandidateCls").attr("attr_nominatedPostApplicationId","0");
		$(document).find(".saveGoForCandidateCls").attr("attr_position_id","0");
		$(".updateAllShowPopup").find(".updateDropDownArrow").addClass("updateDropDownArrowModal");
		$(".updateAllShowPopup").css("position","relative");
		e.stopPropagation();
		$(".chosenSelect").chosen();
		$(".dateR").daterangepicker({
			opens:'left'
		});
		$('#filer_input').filer({
			changeInput: '<div class="jFiler-input-dragDrop"><div class="jFiler-input-inner"><div class="jFiler-input-icon"><i class="icon-jfi-folder"></i></div><div class="jFiler-input-text"><h3>Click on this box</h3> <span style="display:inline-block; margin: 15px 0">or</span></div><a class="jFiler-input-choose-btn blue">Browse Files</a></div></div>',
			showThumbs: true,
			theme: "dragdropbox",
			templates: {
				box: '<ul class="jFiler-items-list jFiler-items-grid"></ul>',
				item: '<li class="jFiler-item">\
							<div class="jFiler-item-container">\
								<div class="jFiler-item-inner">\
									<div class="jFiler-item-thumb">\
										<div class="jFiler-item-status"></div>\
										<div class="jFiler-item-info">\
											<span class="jFiler-item-title"><b title="{{fi-name}}">{{fi-name | limitTo: 25}}</b></span>\
											<span class="jFiler-item-others">{{fi-size2}}</span>\
										</div>\
										{{fi-image}}\
									</div>\
									<div class="jFiler-item-assets jFiler-row">\
										<ul class="list-inline pull-left">\
											<li>{{fi-progressBar}}</li>\
										</ul>\
										<ul class="list-inline pull-right">\
											<li><a class="icon-jfi-trash jFiler-item-trash-action"></a></li>\
										</ul>\
									</div>\
								</div>\
							</div>\
						</li>',
				itemAppend: '<li class="jFiler-item">\
					<div class="jFiler-item-container">\
						<div class="jFiler-item-inner">\
							<div class="jFiler-item-thumb">\
								<div class="jFiler-item-status"></div>\
								<div class="jFiler-item-info">\
									<span class="jFiler-item-title"><b title="{{fi-name}}">{{fi-name | limitTo: 25}}</b></span>\
									<span class="jFiler-item-others">{{fi-size2}}</span>\
								</div>\
								{{fi-image}}\
							</div>\
							<div class="jFiler-item-assets jFiler-row">\
								<ul class="list-inline pull-left">\
									<li><span class="jFiler-item-others">{{fi-icon}}</span></li>\
								</ul>\
								<ul class="list-inline pull-right">\
									<li><a class="icon-jfi-trash jFiler-item-trash-action"></a></li>\
								</ul>\
							</div>\
						</div>\
					</div>\
				</li>',
				progressBar: '<div class="bar"></div>',
				itemAppendToEnd: true,
					removeConfirmation: true,
					_selectors: {
						list: '.jFiler-items-list',
						item: '.jFiler-item',
						progressBar: '.bar',
						remove: '.jFiler-item-trash-action'
					}
				},
				dragDrop: {
					dragEnter: null,
					dragLeave: null,
					drop: null,
				},
				
				files: null,
				addMore: true,
				clipBoardPaste: true,
				excludeName: null,
				beforeRender: null,
				afterRender: null,
				beforeShow: null,
				beforeSelect: null,
				onSelect: null,
				afterShow: null,
			   
				onEmpty: null,
				options: null,
				captions: {
					button: "Choose Files",
					feedback: "Choose files To Upload",
					feedback2: "files were chosen",
					drop: "Drop file here to Upload",
					removeConfirmation: "Are you sure you want to remove this file?",
					errors: {
						filesLimit: "Only {{fi-limit}} files are allowed to be uploaded.",
						filesType: "Only Images are allowed to be uploaded.",
						filesSize: "{{fi-name}} is too large! Please upload file up to {{fi-maxSize}} MB.",
						filesSizeAll: "Files you've choosed are too large! Please upload files up to {{fi-maxSize}} MB."
					}
				}
				
		});
	}
	
});
$(document).on("click",function(){
	$(".commentsDiv,.updateDropDown").hide();
});
$(document).on("click",".updateDropDown,.commentsDiv,.table-condensed tr td,.prev,.next,.applyBtn,.cancelBtn",function(e){
  e.stopPropagation()
});
var boardLevelId = '${param.lId}';
var globalLvlTxt='';
if(boardLevelId ==1){
	globalLvlTxt = "central";
}else if(boardLevelId ==2){
	globalLvlTxt = "State";
}else if(boardLevelId ==3){
	globalLvlTxt = "district";
}else if(boardLevelId ==4){
	globalLvlTxt = "constituency";
}else if(boardLevelId ==5){
	globalLvlTxt = "Mandal/Muncipality/Corporation";
}else if(boardLevelId ==7){
	globalLvlTxt = "Panchayat/Ward/Division";
}

var stateId = '${param.stId}';
$("#stateId").val(stateId);
$(document).ready(function(){

	if(gblStatus == "finaliZed"){
		$("#headinggId").html("yet to start "+globalLvlTxt+" level - board/corporation");
		$("#flowHeading").html(""+globalLvlTxt.substr(0,1).toUpperCase()+globalLvlTxt.substr(1)+" Level - Board/Corporation &mdash; finalyzed");
	}else if(gblStatus == "finalReview"){
		$("#headinggId").html("Final Review "+globalLvlTxt+" level - board/corporation");
		$("#flowHeading").html(""+globalLvlTxt.substr(0,1).toUpperCase()+globalLvlTxt.substr(1)+" Level - Board/Corporation &mdash; FINAL REVIEW");
	}else if(gblStatus == "goPassed"){
		$("#headinggId").html("G O Issued / Completed  "+globalLvlTxt+" level - board/corporation");
		$("#flowHeading").html(""+globalLvlTxt.substr(0,1).toUpperCase()+globalLvlTxt.substr(1)+" Level - Board/Corporation &mdash; G O Issued / Completed ");
	}else if(gblStatus == "notRecieved"){
		$("#headinggId").html(" Not Recieved "+globalLvlTxt+" level - board/corporation");
		$("#flowHeading").html(""+globalLvlTxt.substr(0,1).toUpperCase()+globalLvlTxt.substr(1)+" Level - Board/Corporation &mdash; NOT RECEIVED");
	}
		
	else{
		$("#headinggId").html(gblStatus+"  - "+globalLvlTxt+" level  - board/corporation");
		$("#flowHeading").html(globalLvlTxt.substr(0,1).toUpperCase()+globalLvlTxt.substr(1)+" Level - Board/Corporation <span>	&mdash;</span> "+gblStatus.substr(0,1).toUpperCase()+gblStatus.substr(1)+"");
	}

if(boardLevelId == 1){
 $(".hideFilterCls").hide();	
}
if(boardLevelId == 2){
$(".hideDistrictDivCls").hide();	
$(".hideConstituencyDivCls").hide();
$(".hidemanTowDivCls").hide();		
}
if(boardLevelId == 3){	
$(".hideConstituencyDivCls").hide();
$(".hidemanTowDivCls").hide();	
}
if(boardLevelId == 4){
$(".hidemanTowDivCls").hide();
}
if(boardLevelId != null && boardLevelId > 6){
getBoardLevelId(7,stateId);		
}else if(boardLevelId != null && (boardLevelId == 5 || boardLevelId == 6)){
getBoardLevelId(5,stateId);		
}else{
getBoardLevelId(boardLevelId,stateId);		
}
$(".filterSection").hide();
});
tableResponsive();
function tableResponsive()
{
	if($(window).width < 800)
	{
		$("#resultDivId").addClass("table-responsive");   
	}
}
$(document).on("click",".filterBtn",function(){
	$(".filterSection").toggle("slow");
});
$(document).on("click",".pdfModalCloseBtn",function(){
	setTimeout(function(){
		$("#myModal").modal('show');
		$("body").addClass('modal-open');
	},500);
});
 
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

