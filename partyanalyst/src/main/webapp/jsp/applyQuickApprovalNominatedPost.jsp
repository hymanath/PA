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
<title>Apply Nominated Post</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/NominatedPost/custom.css" rel="stylesheet" type="text/css">
<link href='https://fonts.googleapis.com/css?family=Roboto:400,500,700,700italic,900,900italic,400italic,500italic,300italic,300,100italic,100' rel='stylesheet' type='text/css'>
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
<link href="dragAndDropPhoto/css/jquery.filer.css" type="text/css" rel="stylesheet" />
<link href="dragAndDropPhoto/css/themes/jquery.filer-dragdropbox-theme.css" type="text/css" rel="stylesheet" />
<link href="dist/activityDashboard/Date/daterangepicker.css" rel="stylesheet" type="text/css">
<link href="dist/slick/slick.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<header>
</header>
<main>
	<section>
		<div class="container-fluid">
			<div class="col-sm-12">
				<div class="row">
				    <div class="col-sm-12">
                        <div class="panel panel-default" style="margin-bottom: 0px">
                             <div class="panel-heading" style="background-color:#cccccc">
                                <h3 class="panel-title" style="font-weight:600;font-size:17px">QUICK APPROVAL APPLICANT - NOMINATED POST PROFILE CREATION</h3>
                             </div>
                            <div class="panel-body">
                                <div class="col-sm-12 m_top20">
                                    <h5 style="font-weight:600"><span style="color:#FF0000;padding-right:20px;">STEP-1</span>APPLYING POST TYPE</h5>
                                    <div class="m_top20" id="selectPost">
										<div class="errorMsgCls" style="color:red;"></div>
								<span class="iconClose" style="display:none;cursor:pointer;">
									<i class="glyphicon glyphicon-remove"></i>
								</span>
								<div class="col-md-3 col-xs-12 col-sm-6 col-lg-3 m_top10">
									<label>Nominated Post Level</label>
									<select class="chosenSelect boardLvlCls boardLvelCls validateCls" id="boardLvlId" name="nominatedPostVO.nominatdList[0].boardLevelId" onchange="showHideByNominatedPost(); getDepartments();getOpenedPostionsStates('nominatedStaeId','')" attr_no="">
									<option value="0">Select Post Level</option>
									</select>
									<img id="searchDataImgForState" style="width: 15px; height: 15px; margin-left: 346px; margin-top: -26px;display:none;" src="images/icons/loading.gif">
								</div>
								<div class="col-md-3 col-xs-12 col-sm-6 col-lg-3 m_top10 stateShowsCls" id="statesShowDivId" style="display:none;">
									<label>State Name</label>
									<select class="chosenSelect nominatedStaeCls" onchange="getOpenPositionDistrictsForState(this.value,this.id,'');getDepartments(0);" id="nominatedStaeId" name="nominatedPostVO.nominatdList[0].stateId" attr_no="">
										<option value="0">Select State</option>
										<!--<option value="1">Andhra Pradesh</option>
										<option value="36">Telangana</option>-->
									</select>
									<img id="searchDataImgForDistrict" style="width: 15px; height: 15px; margin-left: 346px; margin-top: -26px;display:none;" src="images/icons/loading.gif">
								</div>
						        <div class="col-md-3 col-xs-12 col-sm-6 col-lg-3 m_top10 districtShowsCls" id="districtShowDivId" style="display:none;">
									<label>District</label>
									<select class="chosenSelect nominatedDistCls" onchange="getOpenPositionConstituenciesForDistrict(this.value,this.id,'');getDepartments(0);" id=
									"nominatedDistId" name="nominatedPostVO.nominatdList[0].districtId" attr_no="">
									</select>
									<img id="searchImgForDistr" class="cloneImgConCls" style="width:20px;height:20px; margin-left: 346px; margin-top: -26px;display:none;" src="images/icons/loading.gif">
									</div>
								<div class="col-md-3 col-xs-12 col-sm-6 col-lg-3 m_top10 constituencyShowsCls" id="constituencyshowDivId" style="display:none;">
									<label>Constituency</label>
									<select attr_no="" class="chosenSelect nominatdConstCls" onchange="getOpenPositionMandalsForConstituency('',this.id);getDepartments(0);" id="nominatdConstId" name="nominatedPostVO.nominatdList[0].constituencyId">
									</select>
									<img id="searchImgForConst" class="cloneImgMandlCls" style="width:20px;height:20px; margin-left: 346px; margin-top: -26px;display:none;" src="images/icons/loading.gif">
								</div>
								<div class="col-md-3 col-xs-12 col-sm-6 col-lg-3 m_top10 mandalShowsCls" id="mondalShowDivId" style="display:none;">
									<label>Mandal/ Muncipality / Corporation</label>
									<select attr_no="" class="chosenSelect nominatedMandlCls" onchange="getOpenPositionVillagesForMandal('',this.id);getDepartments(0);" id="nominatedMandlId" name="nominatedPostVO.nominatdList[0].mandalId">
									</select>
									<img id="searchImgForMandl" class="cloneImgPanchCls" style="width:20px;height:20px; margin-left: 346px; margin-top: -26px;display:none;" src="images/icons/loading.gif">
								</div>
								<div class="col-md-3 col-xs-12 col-sm-6 col-lg-3 m_top10 panchayatShowsCls" id="panchayatShowDivId" style="display:none;">
									<label>Panchayat/ Ward / Division</label>
									<select attr_no="" class="chosenSelect nominatedPanchayatCls" id="nominatedPanchayatId" name="nominatedPostVO.nominatdList[0].panchayatId" onchange="getDepartments(0);">
									</select>
									<img id="searchImgForPanch" class="cloneImgDeptCls" style="width:20px;height:20px; margin-left: 346px; margin-top: -26px;display:none;" src="images/icons/loading.gif">
								</div>
								<div class="col-md-3 col-xs-12 col-sm-6 col-lg-3 m_top10">
									<label>Department Name</label>
									<select attr_no="" class="chosenSelect depmtsCls"  id="depmtsId" onchange="getDepartmentBoards('');" name="nominatedPostVO.nominatdList[0].deptId">
									<option value="0">Select Department</option>
									</select>
									<br>
									<span id="errdepmtsId" class="errdepmtscls"></span>
									<img id="searchDataImgForDep" class="cloneImgDepCls" style="width: 15px; height: 15px; margin-left: 346px; margin-top: -26px;display:none;" src="images/icons/loading.gif">
								</div>
								<div class="col-md-3 col-xs-12 col-sm-6 col-lg-3 m_top10">
									<label>Corporation/Board Name</label>
									<select attr_no="" class="chosenSelect deptBoardCls" id="deptBoardId" onchange="getDepartmentBoardPositions('');" name="nominatedPostVO.nominatdList[0].deptBoardId">
									<option value="0">Select Department Board</option>	
									</select><br>
									<span id="errdeptBoardId" class="errdeptBoardCls"></span>
									<img id="searchDataImgForPos" style="width: 15px; height: 15px; margin-left: 346px; margin-top: -26px;display:none;" src="images/icons/loading.gif">
								</div>
								<div class="col-md-3 col-xs-12 col-sm-6 col-lg-3 m_top10">
									<label>Position Name</label>
									<select attr_no=""  class="chosenSelect deptBoardPostnCls" id="deptBoardPostnId" name="nominatedPostVO.nominatdList[0].positions" data-placeholder ="Select Board Position">
									<option value="0">Select Board Position</option>
									</select><br>
									<span id="errdeptBoardPostnId" class="errdeptBoardPostnCls"></span>
								</div>
								<input type="hidden" class="tdpCadreId" name="nominatedPostVO.id">
								<input type="hidden" class="tdpCadreName" name="nominatedPostVO.name">
								<input type="hidden" class="cadreMobileNo" name="nominatedPostVO.mobileNo">
								<input type="hidden" class="cadreVoterId" name="nominatedPostVO.voterCardNo">
								<input type="hidden" class="referCadreIds" name="nominatedPostVO.refferCadreIds">
								<input type="hidden" class="nominatedCandId" name="nominatedPostVO.nominatedCandId">
								<input type="hidden" id="candidateTypeId" name="nominatedPostVO.candidateType">
									</div>
                                </div>
                                <div class="col-sm-12 m_top20">
                                    <h5 style="font-weight:600"><span style="color:#FF0000;padding-right:20px;">STEP-2</span>SEARCH APPLICANT</h5>
                                    <div class="m_top20" style="border:1px solid grey padding-left:0px;padding-right:0px">
                                        <div class="">
                                        <form class="" name="searchMember" id="searchMember">
                                            <div class="col-sm-4 col-md-3 selectSearch" style="padding-left:0px;padding-right:0px;margin-right:-2px;">
                                                <label class="radio-inline">
                                                    <input type="radio" name="searchBasedOn" checked="true" class="searchTypeCls" onclick="refreshExistingDetails();" id="membershipId" value="1" attr_type="memberShipNumber"/>Membership No
                                                </label>
                                                <label class="radio-inline">
                                                    <input type="radio" name="searchBasedOn" class="searchTypeCls" id="voterId"  onclick="refreshExistingDetails();"  value="2"  attr_type="voterId"/>Voter ID
                                                </label>
                                            </div>
                                             <div class="col-sm-4 col-md-9" style="padding-left:0px;padding-right:0px;">
                                                <div class="input-group">
                                                <input type="text" class="form-control" placeholder="Enter Number" style="border-left-color:transparent" name="memberField" id="searchBy" required/>
                                                <span class="input-group-btn">
                                                    <button type = "button" class="btn btn-success btn-block btnSearch" id="searchbtn">SEARCH</button>
                                                </span>
                                            </div>
                                             </div>
                                        </form>
                                    </div>
                                    </div>
                                </div>
                                <div class="col-sm-12 m_top20" id="">
                                    <div class="m_top20" id="searchResultsBlock"></div>
									<p class="m_top20">Note:   1) Please select matches profile     2) You Can add Multiple members to above selected Post Name</p>
                                </div>
                            </div>
                        </div>
                    </div>
				</div>
			</div>
		</div>
	</section>
	<section class="" id="showPositionBlock">
		<div class="container-fluid">
			<div class="">
				<div class="row">
				<form name="submitApplication" id="submitApplication"  method="post" enctype="multipart/form-data">
					<div class="" id="addPositionsBlock">
						<!--<div class="panel-group" id="accordionOne" role="tablist" aria-multiselectable="true">
                            <div class="panel panel-default">
                                <div class="panel-heading" role="tab" id="headingTwo">
                                    <a role="button" class="panelCollapseIconChange" data-toggle="collapse" data-parent="#accordionOne" href="#collapsetwo" aria-expanded="true" aria-controls="collapseTwo">
                                        <h4 class="panel-title" style="font-weight:600">SELECTED POSITON & SEARCH MEMBERS</h4>
                                        <p>2 Positions & 10 Members</p>
                                        
                                    </a>
                                </div>
                                <div id="collapsetwo" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingTwo">
                                    <div class="panel-body">
                                        <div class="col-sm-9" style="border-right:1px solid grey">
                                            <h5 ><span style="color:#FF0000">STEP-3</span></h5>
										</div>
                                        <div class="col-sm-3">
                                            <h5 style="font-weight:600"><span style="color:#FF0000">STEP-4</span></h5>
											<div class="col-sm-12 m_top20">
												<h5>REFERED MEMBER</h5>
											</div>
											<div class="col-sm-12 m_top20">
												<h5>UPLOAD SCAN COPY</h5>
											</div>
                                        </div>
                                     </div>
                                </div>
                            </div>
                        </div>-->
					</div>
					</form>
				</div>
			</div>
		</div>
	</section>
	
</main>
<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="dist/slick/slick.js" type="text/javascript"></script>
<script type="text/javascript" src="dragAndDropPhoto/js/customNominated.jquery.filter.min.js?v=1.0.5"></script>
<script type="text/javascript" src="dragAndDropPhoto/js/customNominatedPost.js?v=1.0.5"></script>
<script src="dist/activityDashboard/Date/moment.js" type="text/javascript"></script>
<script src="dist/activityDashboard/Date/daterangepicker.js" type="text/javascript"></script>
<script type="text/javascript" src="js/nominatedPosts/applyQuickApprovalNominatedPost.js"></script>

<script>
$(document).ready(function(){
	refreshOnLoadFields();
});
var globalstatus='${param.status}';
var globallevelId = '${param.lId}';
var globaldeptId = '${param.deptId}';
var globalboardId = '${param.boardId}';
var globalposId = '${param.positionId}';
var gDisId = 0;
var gConsId = 0;
var gMandlId = 0;
var gVillId = 0;
var gsId = 0;
	$('.chosenSelect').chosen({width:'100%'});
</script>
</body>
</html>