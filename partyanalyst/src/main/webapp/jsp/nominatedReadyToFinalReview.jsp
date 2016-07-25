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
</head>
<body>
<div class="container">
<div class="row hideRowCls">
		<div class="col-md-12 col-xs-12 col-sm-12">
			<div class="panel panel-default">
				<!--<div class="panel-heading">
					<h4 class="panel-title">Select Location</h4>
				</div>-->
				<div class="panel-body">
					<div class="row">
						<div class="col-md-3 col-xs-12 col-sm-3 hideDistrictDivCls">
							<label>District</label>
							<select class="form-control" id="districtId">
								<!--<option value="">Select District</option>-->
							</select>
						</div>
						<div class="col-md-3 col-xs-12 col-sm-3 hideConstituencyDivCls">
							<label>Constituency</label>
							<select class="form-control" id="constituencyId">
								<!--<option value="0">Select Constituency</option>-->
							</select>
						</div>
						<div class="col-md-3 col-xs-12 col-sm-3 hidemanTowDivCls">
							<label>Mandal/Town/Division</label>
							<select class="form-control" id="manTowDivId">
								<!--<option value="0">Select Mandal/Town/Division</option>-->
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
    	<div class="col-md-12 col-xs-12 col-sm-12">
        	<h3 class="text-capitalize">Ready For Final</h3>
        </div>
		<div class="col-md-4 col-xs-12 col-sm-4 heightSet" style="background:#DFDFDF;padding-right:0px;">
          <div class="pad_15">
	          <h4 class="headingColor text-capital"><u>departments</u></h4>
          </div>
		   <div id="candiateReadyToFinalReviewDivId"></div>
        </div>
        <div class="col-md-4 col-xs-12 col-sm-4 pad_0 heightSet" style="padding-right:0px;background:#EBEBEB;">
        	<div class="tab-content">
			 <div id="boardRsltDivId"></div>
            </div>
        </div>
        <div class="col-md-4 col-xs-12 col-sm-4 pad_0 heightSet" style="padding-right:0px;background:#F6F6F6;">
        	<div class="tab-content">
				<div id="positionRsltDivId"></div>
            </div>
        </div>
    </div>
</div>

<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="js/nominatedPosts/nominatedReadyToFinalReview.js" type="text/javascript"></script>
<script type="text/javascript">
var boardLevelId = '${param.lId}';
var stateId = '${param.stateId}';
$(document).ready(function(){
if(boardLevelId == 1 || boardLevelId ==2){
 $(".hideRowCls").hide();	
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
});
</script>
</body>
</html>