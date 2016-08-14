<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Ready To Final Review</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/NominatedPost/custom.css" rel="stylesheet" type="text/css">
<link href='https://fonts.googleapis.com/css?family=Roboto:400,500,700,700italic,900,900italic,400italic,500italic,300italic,300,100italic,100' rel='stylesheet' type='text/css'>
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
<link href="dist/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
.filterIcon
{
	background:#ddd;
	padding:6px;
	border-radius:50%;
	cursor:pointer
}

</style>
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
		<div class="col-md-12 col-xs-12 col-sm-8 col-sm-offset-2 col-md-offset-0 filterSection">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="row">
						<div class="col-md-3 col-xs-12 col-sm-12 hideStateDivCls" id="stateMainId" >
							<label>State</label>
							<select class="form-control" id="stateId">
								<option value="0">All</option>
								<option value="1">Andhara Pradesh</option>
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
	<div class="row m_top20">
    	
		<div class="col-md-4 col-xs-12 col-sm-8 col-sm-offset-2 col-md-offset-0  heightSet" style="background:#DFDFDF;padding-right:0px;border:1px solid #CCC;">
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
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" style="width:85%;" role="document">
    <div class="modal-content chairmenFinaliseModal">
      <div class="modal-header">
        
        <div class="row">
            <div class="col-md-10 col-xs-12 col-sm-8">
                <h3>FINALIZING - <span id="headingPostId"></span></h3>
                <p id="totalHeadingId">State Level - Labour Department - A.P Building and Other Construction Workers Welfare Board</p>
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
	   <div id="resultDivId" class="table-responsive"></div>
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
<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mousewheel.js" type="text/javascript"></script>
<script src="js/nominatedPosts/nominatedReadyToFinalReview.js" type="text/javascript"></script>
<script type="text/javascript">

var gblStatus = '${param.sts}';
$("#headStsId").html(gblStatus+" Nominated Post details");
$(document).prop('title', gblStatus+" Nominated Post details");

function setHeight(){
	var maxHeight = 0;
	
	$(".heightSet").each(function(){
	   if ($(this).height() > maxHeight) { maxHeight = $(this).height(); }
	});
	$(".heightSet").height(maxHeight);
}
$(document).on("click",".modalViewBtn",function(){
	$("#myModal").modal('show');
});
$(document).on("click",".commentsBtn",function(e){
	$(".commentsDiv").hide();
	$(this).closest('tr').find(".commentsDiv").show();
	e.stopPropagation()
});
$(document).on("click",".updateBtnDrop",function(e){
	$(".updateDropDown").hide();
	$(this).closest('tr').find(".updateDropDown").show();
	e.stopPropagation()
});

$(document).on("click",function(){
	$(".commentsDiv,.updateDropDown").hide();
});
$(document).on("click",".updateDropDown,.commentsDiv",function(e){
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
}

var stateId = '${param.stId}';
$("#stateId").val(stateId);
$(document).ready(function(){
	if(gblStatus == "finaliZed"){
		$("#headinggId").html("yet to start "+globalLvlTxt+" level - board/corporation");
		$("#flowHeading").html(""+globalLvlTxt.substr(0,1).toUpperCase()+globalLvlTxt.substr(1)+" Level - Board/Corporation &mdash; finaliZed");
	}
		
	else{
		$("#headinggId").html(gblStatus+"  - "+globalLvlTxt+" level  - board/corporation");
		$("#flowHeading").html(globalLvlTxt.substr(0,1).toUpperCase()+globalLvlTxt.substr(1)+" Level - Board/Corporation <span>	&mdash;</span> "+gblStatus.substr(0,1).toUpperCase()+gblStatus.substr(1)+"");
	}
if(boardLevelId == 1){
 $(".hideRowCls").hide();	
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
if(boardLevelId != null && boardLevelId >= 5){
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
</body>
</html>