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
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
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
        	<h4 class="text-capital"><span id="headStsId"></span> <i class="glyphicon glyphicon-filter filterIcon filterBtn pull-right"></i></h4>
        </div>
		<div class="col-md-12 col-xs-12 col-sm-12 filterSection">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="row">
						<div class="col-md-3 col-xs-12 col-sm-3 hideStateDivCls" id="stateMainId" >
							<label>State</label>
							<select class="form-control" id="stateId">
								<option value="0">All</option>
								<option value="1">Andhara Pradesh</option>
								<option value="36">Telangana</option>
							</select>
						</div>
						<div class="col-md-3 col-xs-12 col-sm-3 hideDistrictDivCls">
							<label>District</label>
							<select class="form-control" id="districtId">
							</select>
						</div>
						<div class="col-md-3 col-xs-12 col-sm-3 hideConstituencyDivCls">
							<label>Constituency</label>
							<select class="form-control" id="constituencyId">
							</select>
						</div>
						<div class="col-md-3 col-xs-12 col-sm-3 hidemanTowDivCls">
							<label>Mandal/Town/Division</label>
							<select class="form-control" id="manTowDivId">
							</select>
						</div>
						<div class="col-md-3 col-xs-12 col-sm-3">						
							<input type="button" class="btn btn-primary btn-sm" value="Submit" style="margin-top: 25px;" id="submitBtnId"/>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
    	
		<div class="col-md-4 col-xs-12 col-sm-4 heightSet" style="background:#DFDFDF;padding-right:0px;">
          <div class="pad_15">
	          <h4 class="headingColor text-capital"><u>departments</u></h4>
          </div>
		   <div id="candiateReadyToFinalReviewDivId"></div>
        </div>
        <div class="col-md-4 col-xs-12 col-sm-4 pad_0 heightSet boardCorporation" style="padding-right:0px;background:#EBEBEB;display:none;">
        	<div class="tab-content">
			 <div id="boardRsltDivId"></div>
            </div>
        </div>
        <div class="col-md-4 col-xs-12 col-sm-4 pad_0 heightSet positionsDivCls" style="padding-right:0px;background:#F6F6F6;display:none;">
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
            <div class="col-md-10 col-xs-12 col-sm-10">
                <h3>FINALIZING - <span id="headingPostId"></span></h3>
                <p id="totalHeadingId">State Level - Labour Department - A.P Building and Other Construction Workers Welfare Board</p>
            </div>
            <div class="col-md-2 col-xs-12 col-sm-2">
            	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <div class="wishList">
                	<h1 class="textEffect"><img src="dist/nominatedImages/Icon4.png" alt="count"/><span id="wishListCountId"> 0 </span></h1>
                	<p class="text-success">Added to wishlist</p>
                </div>
            </div>
        </div>
      </div>
      <div class="modal-body">
	   <div id="resultDivId"></div>
			<!--<table class="table table-bordered">
				<thead>
					<th>Name</th>
					<th>Age</th>
					<th>Caste</th>
					<th>Sub Caste</th>
					<th>Party Designations</th>
					<th>Reports</th>
					<th>Applied any Dept / Corp</th>
					<th>Shortlisted in Dept / Corp</th>
					<th>Current Status for this post</th>
					<th>Comments/ Update Status/ Wishlist</th>
				</thead>
				<tbody>
					<tr>
						<td rowspan="2"><i class="glyphicon glyphicon-user"></i> Sivaji</td>
						<td rowspan="2">35</td>
						<td rowspan="2">SC</td>
						<td rowspan="2">Mala</td>
						<td rowspan="2">State Organising Secretary</td>
						<td>Suitable <i class="glyphicon glyphicon-list-alt"></i></td>
						<td rowspan="2">02</td>
						<td rowspan="2">No
							<div class="appliedPostPopup">
								<div class="appliedPostPopupArrow">
									<table class="table table-bordered">
										<thead>
											<th>Department</th>
											<th>Corporation / Board</th>
											<th>Position</th>
											<th>Status</th>
										</thead>
										<tbody>
											<tr>
												<td>Agriculture Marketing</td>
												<td>Agriculture market committee</td>
												<td>Chairmen</td>
												<td>Shortlisted</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</td>
						<td rowspan="2">Shortlisted</td>
						<td rowspan="2" style="position:relative;">
							<img src="dist/nominatedImages/Icon5.png" class="commentsBtn" style="height:28px;"/> 
							<div class="commentsDiv">
								<div class="commentDropDownArrow">
									<p>COMMENTS <span class="pull-right">X</span></p>
									<ul class="commentsUl">
										<li class="shortList">
											<div class="panel-group" id="commentsAccordion" role="tablist" aria-multiselectable="true">
											  <div class="panel panel-default commentsPanel">
												<div class="panel-heading" role="tab" id="CommentsCollapseHeading">
													<a role="button" data-toggle="collapse" class="CommentsModalIcon" data-parent="#commentsAccordion" href="#CommentsCollapse" aria-expanded="true" aria-controls="CommentsCollapse">
													  <h4>Shortlisting</h4>
													</a>
												</div>
												<div id="CommentsCollapse" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="CommentsCollapseHeading">
												  <div class="panel-body">
													<div class="Comment">
														Shortlisting  Shortlisting Shortlisting Shortlisting Shortlisting
														<p class="text-danger"><i>- 10 July 2016 11:30Am</i></p>
														<p class="text-danger"><i>Updated By Ramesh</i></p>
													</div>
													<div class="Comment">
														Shortlisting  Shortlisting Shortlisting Shortlisting Shortlisting
														<p class="text-danger"><i>- 10 July 2016 11:30Am</i></p>
														<p class="text-danger"><i>Updated By Ramesh</i></p>
													</div>
													<div class="Comment">
														Shortlisting  Shortlisting Shortlisting Shortlisting Shortlisting
														<p class="text-danger"><i>- 10 July 2016 11:30Am</i></p>
														<p class="text-danger"><i>Updated By Ramesh</i></p>
													</div>
												  </div>
												</div>
											  </div>
											</div>
										</li>
										<li class="finaLize">
											<div class="panel-group" id="commentsAccordion12" role="tablist" aria-multiselectable="true">
											  <div class="panel panel-default commentsPanel">
												<div class="panel-heading" role="tab" id="CommentsCollapseHeading112">
													<a class="collapsed CommentsModalIcon" role="button" data-toggle="collapse" data-parent="#commentsAccordion12" href="#CommentsCollapseHeading112" aria-expanded="false" aria-controls="CommentsCollapseHeading2">
														<h4>Finalization</h4>
													</a>
												</div>
												<div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="CommentsCollapseHeading112">
												  <div class="panel-body">
													<div class="Comment">
														Shortlisting  Shortlisting Shortlisting Shortlisting Shortlisting
														<p class="text-danger"><i>- 10 July 2016 11:30Am</i></p>
														<p class="text-danger"><i>Updated By Ramesh</i></p>
													</div>
													<div class="Comment">
														Shortlisting  Shortlisting Shortlisting Shortlisting Shortlisting
														<p class="text-danger"><i>- 10 July 2016 11:30Am</i></p>
														<p class="text-danger"><i>Updated By Ramesh</i></p>
													</div>
												  </div>
												</div>
											  </div>
											</div>
										</li>
									</ul>
								</div>
							</div>
							<img src="dist/nominatedImages/Icon4.png" style="height:28px;"/> 
							<button class="btn btn-success updateBtnDrop">UPDATE</button>
							<div class="updateDropDown">
								<div class="updateDropDownArrow">
									<label class="m_top10">Select Status</label>
									<select class="form-control">
										<option>Status</option>
									</select>
									<label class="m_top10">Comments</label>
									<textarea class="form-control"></textarea>
									<button class="btn btn-success btn-block">SUBMIT</button>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td>Not Preferable<i class="glyphicon glyphicon-list-alt pull-right"></i></td>
					</tr>
				</tbody>
			</table>-->
			<!--<p class="pad_15">
				Note: Click on <i class="glyphicon glyphicon-user"></i> to view complete profile
			</p>-->
      </div>
    </div>
  </div>
</div>

<div class="modal fade" tabindex="-1" id="pdfModelId" role="dialog">  
	<div class="modal-dialog" style="width:60%;">      
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
<script src="js/nominatedPosts/nominatedReadyToFinalReview.js" type="text/javascript"></script>
<script type="text/javascript">

var gblStatus = '${param.sts}';
$("#headStsId").html(gblStatus+" Nominated Post details");

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
var stateId = '${param.stId}';
$("#stateId").val(stateId);
$(document).ready(function(){
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
</script>
</body>
</html>