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
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" rel="stylesheet" type="text/css">


<link href="dist/activityDashboard/SelectDropDown/dropkick.css" rel="stylesheet" type="text/css">
<link href="dragAndDropPhoto/css/jquery.filer.css" type="text/css" rel="stylesheet" />
<link href="dragAndDropPhoto/css/themes/jquery.filer-dragdropbox-theme.css" type="text/css" rel="stylesheet" />
<link href="dist/activityDashboard/Date/daterangepicker.css" rel="stylesheet" type="text/css">
<link href="dist/slick/slick.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
.involveBlock {
    background: #f8f9fb none repeat scroll 0 0;
    border: 1px solid #ddd;
    padding: 5px;
    height :100px;
}
.involveBlockNew {
    background: #f8f9fb none repeat scroll 0 0;
    border: 1px dashed #ddd;
    cursor: pointer;
    height: 105px;
}
.involveBlockNew .media-left, .involveBlockNew .media-body {
    padding: 17px;
    position: relative;
}

.removeIconNew {
    cursor: pointer;
    display: none;
    position: absolute;
    right: 20px;
    top: 5px;
	font-size:12px;
	background:#ccc;
	border-radius:50%;
	padding:2px;
}
.involveBlock:hover .removeIconNew {
    display: block;
}
.involveBlock:hover {
    box-shadow: 0 0 5px 2px rgba(0, 0, 0, 0.4);
}
.removeIconNew
	{
		position:absolute;
		display:none;
		cursor:pointer;
	}
	.involveBlock:hover .removeIconNew
	{
		display:block;
	}
	.createAppointmentSearch>li{
	width:300px !important;
	
}
.createAppointmentSearch {
    padding: 0;
}
.createAppointmentSearch li {
    border: 1px solid #ddd;
    box-shadow: 0 0 5px rgba(0, 0, 0, 0.4);
    list-style: outside none none;
    margin-top: 8px;
    padding: 8px;
}	
</style>
</head>
<body>
<header>
</header>
<main>
	<section>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-12">
					<div class="panel panel-default" style="margin-bottom: 0px">
						 <div class="panel-heading" style="background-color:#cccccc">
							<h3 class="panel-title" style="font-weight:600;font-size:17px">QUICK APPROVAL APPLICANT - NOMINATED POST PROFILE CREATION</h3>
						 </div>
						<div class="panel-body">
							<div class="row">
								<div class="col-sm-12 m_top20">
									<h5 style="font-weight:600"><span style="color:#FF0000;padding-right:20px;">STEP-1</span>APPLYING POST TYPE</h5>
									<div class="m_top20" id="selectPost">
										<div class="errorMsgCls" style="color:red;"></div>
										<span class="iconClose" style="display:none;cursor:pointer;">
											<i class="glyphicon glyphicon-remove"></i>
										</span>
										<div class="row">
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
								</div>
								<div class="col-sm-12 m_top20">
									<h5 style="font-weight:600"><span style="color:#FF0000;padding-right:20px;">STEP-2</span>SEARCH APPLICANT</h5>
									
									<div class="row m_top20">
										<form name="searchMember" id="searchMember">
											<div class="col-sm-3">
												<div class=" selectSearch">
													<label class="radio-inline">
														<input type="radio" name="searchBasedOn" checked="true" class="searchTypeCls" onclick="refreshExistingDetails();" id="membershipId" value="1" attr_type="memberShipNumber"/>Membership No
													</label>
													<label class="radio-inline">
														<input type="radio" name="searchBasedOn" class="searchTypeCls" id="voterId"  onclick="refreshExistingDetails();"  value="2"  attr_type="voterId"/>Voter ID
													</label>
												</div>
											</div>
											<div class="col-sm-9">
												<div class="input-group">
													<input type="text" class="form-control" placeholder="Enter Number" name="memberField" id="searchBy" required/>
													<span class="input-group-btn">
														<button type = "button" class="btn btn-success btn-block btnSearch" id="searchbtn">SEARCH</button>
													</span>
												</div>
											</div>
										</form>
									</div>
									
								</div>
								<div class="col-sm-12 m_top20" id="">
								<div class="errMessageId" style="color:red;"></div>
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
	<section id="showPositionBlock">
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-12">
					<form name="submitApplication" id="submitApplication"  method="post" enctype="multipart/form-data">
						<div class="" id="addPositionsBlock"></div>
					</form>
				</div>
			</div>
		</div>
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog">
  <div class="modal-dialog" style="width:80%" role="document">
    <div class="modal-content">
      <div class="modal-header" style="background-color: rgb(204, 204, 204);padding:8px;">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Select Referral Details</h4>
      </div>
      <div class="modal-body">
        <div class="row">
			<div class="col-md-12 col-xs-12 col-sm-12">
				<div style="background:#fff">
					<div class="col-md-3 col-xs-12 col-sm-6 advanceSearchCls">
						<label>Search Type</label>
						<select class="dropkickClass"  id="advanceSearchTypeId" onchange="showHideBySearchType();buildLevels();">
							<option value="0">Select Search Type</option>
							<option value="1">Name</option>
							<option value="mobileno">Mobile No</option>
							<option value="mebershipno">Membership No</option>
							<option value="votercardno">Voter Id Card No</option>
							<option value="2">Public Representative</option>
							<option value="3">Party Committee</option>
							<option value="4">Caste</option>
							<option value="5">Gender</option>
							<option value="6">Age</option>
							<option value="7">Caste Group</option>
							<option value="8">Education</option>
						</select>
					</div>
					
					<div class="col-md-3 col-xs-12 col-sm-12 hideStateDivCls">
						<label>State</label>
						<select class="dropkickClass" id="filterStateId">
							<option value="0">Select State</option>
							<option value="1">Andhra Pradesh</option>
							<option value="36">Telangana</option>
						</select>
					</div>
					<div class="col-md-3 col-xs-12 col-sm-12 hideDistrictDivCls">
						<label>District</label>
						<select class="dropkickClass" id="filterDistrictId">
							<option value="0">Select District</option>
						</select>
					</div>
					<div class="col-md-3 col-xs-12 col-sm-12 hideConstituencyDivCls">
						<label>Constituency</label>
						<select class="dropkickClass" id="filterConstituencyId">
							<option value="0">Select Constituency</option>
						</select>
					</div>
					<div class="col-md-3 col-xs-12 col-sm-12 hidemanTowDivCls">
						<label>Mandal/Town/Division</label>
						<select class="dropkickClass" id="filterManTowDivId">
							<option value="0">Select Mandal/Muncipality</option>
						</select>
					</div>
					<div class="col-md-3 col-xs-12 col-sm-12 hideCasteCls">
						<label>Caste</label>
						<select class="dropkickClass" id="filterCasteId">
							<option value="0">Select Caste</option>
						</select>
					</div>
					<div class="col-md-3 col-xs-12 col-sm-12 hideGenderCls">
						<label>Gender</label>
						<select class="dropkickClass" id="filterGenderId">
							<option value="0">Select Gender</option>
							<option value="M">Male</option>
							<option value="F">Female</option>
						</select>
					</div>
					<div class="col-md-3 col-xs-12 col-sm-12 hideAgeCls">
						<label>Age Range</label>
						<select class="dropkickClass" id="filterAgeId">
							<option value="0">Select Age Range</option>
						</select>
					</div>
					<div class="col-md-3 col-xs-12 col-sm-12 hideCasreGroupCls">
						<label>Caste Group</label>
						<select class="dropkickClass" id="filterCasteGroupId">
							<option value="0">Select Caste Group</option>
						</select>
					</div>
					<div class="col-md-3 col-xs-12 col-sm-12 hideEducationCls">
						<label>Education</label>
						<select class="dropkickClass" id="filterEducationId">
							<option value="0">Select Education</option>
						</select>
					</div>
					
					 <div class="col-md-3 col-xs-12 col-sm-6 advanceSearchCls advanceprclsDiv">
						<label class="advanceNameCls" id="searchNameLabel">Name/Membership No*<span class="text-danger">*</span></label>
						<input type="text" class="form-control advanceNameCls clearCls" id="advanceSearchValueId" >
						
					</div>
					<div class="col-md-3 col-xs-12 col-sm-6" class="advancePRCls">
						<label class="advancePRCls">Search Designation</label>
						 <select class="advancePRCls dropkickClass"  id="advanceDesignationId" onchange="getLevelByDesignation();">
							<option value="0">Select Designation</option>
						</select>
						<span id="advanceErrDigitsId" class="full-right" style="color:red;"></span>
					</div>
					
						
						<div class="col-md-3 col-xs-12 col-sm-6 levelShowCls" >
							<label>Level</label>
							<select class="dropkickClass" id="alertlevelId" attr-index="0" onchange="disableByLevel('');" >
							<option value="2">State</option>
							 <option value="3">District</option>
							 <option value="4">Constituency</option>
							 <option value="5">Mandal/Muncipality</option>
							 <option value="6">Village/Ward</option>
							</select>
						</div>
						<div class="col-md-3 col-xs-12 col-sm-6 stateShowCls" >
							<label>State</label>
							 <select class="dropkickClass" id="stateId" onChange="getDistrictsForReferPopup('');">
								 <option value="0">All</option>
								 <option value="1">AP</option>
								 <option value="36">TS</option>
							 </select>
						</div>
				   
						<div class="col-md-3 col-xs-12 col-sm-6 locationsFilterCls distCls">
							 <label>District</label>
							 <select class="dropkickClass" id="referdistrictId" onChange="getConstituenciesBydistrictForReferPopup('');" >
							 <option value="0">All</option></select>
						</div>
						<div class="col-md-3 col-xs-12 col-sm-6 locationsFilterCls constiCls">
							<label>Assembly</label>
							<select class="dropkickClass" id="referconstituencyId" onChange="getMandalsByConstituencyForReferPopup('');" >
							<option value="0">All</option>
							</select>
						</div>
						<div class="col-md-3 col-xs-12 col-sm-6 locationsFilterCls mandalCls">
							<label>Mandal/ Municipality</label>
							 <select class="dropkickClass" id="refermandalNameId" onChange="getPanchayatsForReferPopup('');" >
								<option value="0">All</option>
							 </select>
						</div>
						<div class="col-md-3  col-xs-12 col-sm-6 locationsFilterCls panchayatCls">
							<label>Panchayat/Ward</label>
							<select class="dropkickClass" id="referpanchayatId" >
							<option value="0">All</option>
							</select>
						</div>
						
						
						
						<div>
									<div class="advanceCadreCommittee" id="referCommitteeDiv">
									 <div class="col-md-3 col-xs-12 col-sm-6">
										<label>Select Committee</label>
										<select id="referCommitteeId" class="dropkickClass" >
											<option value="0">All</option>
											<option value="1">Main</option><option value="2">Telugu Yuvatha</option>
											<option value="3">Telugu Mahila</option><option value="4">Telugu Rythu</option>
											<option value="17">Trade</option><option value="6">BC Cell</option><option value="7">SC Cell</option>
											<option value="8">ST Cell</option><option value="9">Minority Cell</option><option value="18">Christian</option>
											<option value="11">TNSF (Student Union)</option><option value="5">TNTUC</option><option value="15">TSNV (Technical Expert Cell)</option>
											<option value="10">Legal Cell</option><option value="16">Doctor Cell</option><option value="20">Kallu Geetha Karmikulu</option>
											<option value="21">Chenetha</option><option value="19">Telugu Rakshana Vedika</option><option value="14">TNUS ( Teachers Union) </option>
											<option value="12">Commercial Cell</option><option value="13">Cultural Cell</option>
										</select>
									  </div>
									</div> 
									<div>
										<div class="col-md-6 col-xs-12 col-sm-6  advanceCadreCommittee">
											<select id="cadreCommitteeDiv" multiple class="" style="width:250px !important;"></select>
											<div id="representativesDiv"></div>
											<div id="referRoleErrorDiv"></div>
										</div>
									</div>
							</div></br>
							<p id="errorDivId" style="color:red;clear:both;margin-left:5px;"></p>
						<div class="col-md-12 col-xs-12 col-sm-12">
							<button type="button" class="btn btn-success m_top20 advancedSearchBtn btnNewCustom1" onclick="handleBySearchType();" id="searchBtnId"  style="margin-top: 25px;display:none;">Search Member</button>
						</div>
						<div class="col-md-1 col-xs-12 col-sm-12">
							<img src="images/search.gif" style="display:none;" id="ajaxImgForAppintId"/>
						</div>
							<div style="margin-top: 50px;"><img id="searchMemberAjax" src="images/icons/loading.gif" style="display:none;"/></div>
						
						<div class="row m_top25">
						<div class="col-md-12 col-sm-12 col-xs-12" id="clearSerchDivId">
							<div id="apptmemberDetailsDiv" class="table-responsive"></div>
						</div>
					</div>
						</div>
						</div>
						</div>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
	</section>
	
</main>
<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/Appointment/DropkickNew/dropkick.2.1.8.min.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="dist/slick/slick.js" type="text/javascript"></script>
<script type="text/javascript" src="dragAndDropPhoto/js/customNominated.jquery.filter.min.js"></script>
<script type="text/javascript" src="dragAndDropPhoto/js/nominatedPostQuickApply.js"></script>
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
	function buildapptmemberDetails(result){
		var str='';
		var selectedProfileCadreId = 0;
		$(".cadreCls").each(function(){
			 if ($(this).is(":checked")){
			  selectedProfileCadreId = $(this).attr('attr_cadreid');
			}			
		});
		
		if(result !=null && result.length>0){
			str+='<table id="searchedMembersId">';
			str+='<thead><th></th><th></th><th></th></thead>';
			str+='<tbody>';
			var xindex =0;
			for(var i in result){
				if(selectedProfileCadreId !=result[i].id)
				{
					if( xindex == 0)
					{
						str+='<tr>';
					}
				str+='<td style="padding:0px !important;">';
					str+='<div class="col-md-12 col-xs-12 col-sm-12">';
					str+='<ul class="createAppointmentSearch">';
						str+='<li class="searhMemberCls'+result[i].id+'">';
							str+='<div class="row">'; 
								
								str+='<div class="col-md-12 col-xs-12 col-sm-12">';
									str+='<div class="media">';
										str+='<div class="media-left">';
											str+='<img class="media-object thumbnailSearch thumbnail" src="'+result[i].imageURL+'" onerror="setDefaultImage(this);" alt="Candidate Image" style="width: 60px !important; height: 60px  !important;">';
										str+='</div>';
										str+='<div class="media-body">';
										
							
										if(result[i].constituency !=null && result[i].constituency.length>0 ){
											<c:choose>
											<c:when test="${fn:contains(sessionScope.USER.entitlements, 'TDP_CADRE_DETAILS')}">
											if(result[i].id != null && result[i].id > 0){
							
							if(result[i].candidateType=="voter"){
												 str+='<div style="color:#34A7C1;"><span >'+result[i].name+'</span>   -   <span >'+result[i].constituency+' Constituency</span></div>';
												}else{
													str+='<a  target="_blank" data-toggle="tooltip" data-placement="top" title="Cadre Details" style="cursor:pointer;" href="cadreDetailsAction.action?cadreId='+result[i].id+'"><div style="color:#34A7C1;"><span >'+result[i].name+'</span>   -   <span >'+result[i].constituency+' Constituency</span></div></a>';
												}
											}
											else
											str+='<span style="color:#34A7C1;">'+result[i].name+'</span> ';
											</c:when>
											<c:otherwise>
											str+='<div style="color:#34A7C1;"><span>'+result[i].name+'</span>   -   <span>'+result[i].constituency+' Constituency</span></div>';
											</c:otherwise>
											</c:choose>
											
										}else{
											str+='<div style="color:#34A7C1;">'+result[i].name+'</div>';
										}
										if(result[i].mobileNo !=null && result[i].mobileNo.length>0){
												str+='<p ><span><i class="fa fa-mobile" style="font-size:15px"></i> &nbsp '+result[i].mobileNo+'</span>';
										}else{
											str+='<p><span><i class="fa fa-mobile" style="font-size:15px"></i>   - </span>';
										}
										
										if(result[i].designation !=null && result[i].designation.length>0){
											
												str+='<span style="margin-left:10px;"> '+result[i].designation+'</span></p>';
										}else{
											
											//str+='<span style="margin-left:10px;">Designation: - </span></p>';
											
											 if($("#searchTypeId").val()=="mobileno" || $("#searchTypeId").val() == "mebershipno" || $("#searchTypeId").val() == "votercardno" || $("#advanceSearchTypeId").val() == 1){
												 if(result[i].candidateType == 'cadre'){
													str+='<span style="margin-left:10px;"> - Cadre</span></p>'; 
												 }else{
													 str+='<span style="margin-left:10px;"> - </span></p>';
												 }
											}else{
												str+='<span style="margin-left:10px;"> - </span></p>';
											} 
										}
										str+='</div>';
									str+='</div>';
								str+='</div>';
								str+='<div class="btn btn-success btn-sm col-md-4 col-md-offset-4 m_top10 col-xs-6 col-xs-offset-3" style="border-radius:20px;"><label style="margin-bottom: 0px; line-height: 10px;"><input style="margin-left: 0px; margin-top: 0px;" type="checkbox" data-toggle="tooltip" data-placement="top" class="apptDetailsDiv candidatecls close'+result[i].id+'"  attr_designation = "'+result[i].designation+'" attr_candidateType="'+result[i].candidateType+'" attr_name="'+result[i].name+'" attr_mobile="'+result[i].mobileNo+'" attr_desg="'+result[i].designationId+'" attr_memberShipNo="'+result[i].memberShipId+'" attr_voterCardNo="'+result[i].voterCardNo+'" attr_id="'+result[i].id+'" attr_close_id="uncheck'+result[i].id+'" attr_img_url="'+result[i].imageURL+'" attr_candidateType_id='+result[i].candidateTypeId+' title="Select Candidate" attr-consti="'+result[i].constituency+'"> &nbsp;SELECT</label></div>';	
							  
								/*if(result[i].appointmentCandidateId != null && result[i].appointmentCandidateId > 0){
									
									str+='<div class="col-md-1 m_top10"><a  title="Click here to View '+result[i].name+' History" data-toggle="tooltip" data-placement="top" class="historyShowModalBtn"  style="cursor:pointer;" attr-id="'+result[i].appointmentCandidateId+'" attr-name="'+result[i].name+'" attr-designation="'+result[i].designation+'" attr-mobile="'+result[i].mobileNo+'"><i class="glyphicon glyphicon-time" style="color: rgb(142, 142, 142); font-size: 16px;"></i></a></div>&nbsp;&nbsp;';
									
								}
								
								
							if(result[i].designation==null){
								result[i].designation = "";
							}
							if(result[i].aptExists == false && result[i].appointmentCandidateId != null){
								str+='<div class="col-md-1   m_top10" attr_id="'+result[i].id+'" >';
							}
							else{
								str+='<div class="col-md-1 col-xs-offset-1 m_top10" attr_id="'+result[i].id+'" >';
							}
							if(result[i].aptExists == false)
							{
								str+='<div class="btn btn-success btn-sm" style="border-radius:20px;"><label style="margin-bottom: 0px; line-height: 10px;"><input style="margin-left: 0px; margin-top: 0px;" type="checkbox" data-toggle="tooltip" data-placement="top" class="apptDetailsDiv"  attr_designation = "'+result[i].designation+'" attr_candidateType="'+result[i].candidateType+'" attr_name="'+result[i].name+'" attr_mobile="'+result[i].mobileNo+'" attr_desg="'+result[i].designationId+'" attr_memberShipNo="'+result[i].memberShipId+'" attr_voterCardNo="'+result[i].voterCardNo+'" attr_id="'+result[i].id+'" attr_close_id="uncheck'+result[i].id+'" attr_img_url="'+result[i].imageURL+'" attr_candidateType_id='+result[i].candidateTypeId+' title="Create Appointment Request"> &nbsp;SELECT</label></div>';	
							}								
							else{
								str+='<label data-toggle="tooltip"  data-placement="top" title="This Candidate Already in '+result[i].aptName+' Appointment with '+result[i].aptStatus+' Status: you can not addtion to another Appointment"> ';
								str+=''+result[i].aptName+' - '+result[i].aptStatus+'';
								str+='</label>';
							}
							
								str+='</div>';
								
							str+='</div>';*/
						str+='</li>';
					 
					str+='</ul>';
				str+='</div>';
					
				str+='</td>';
				xindex++;
				if(result.length-1 == i){
					if(xindex % 3 == 2){
						str+='<td></td>';
						str+='</tr>';
					}
					if(xindex % 3 == 1){
						str+='<td></td>';
						str+='<td></td>';
						str+='</tr>';
					}
				}
				 if( xindex == 3){
					str+='</tr>';
					xindex = 0;
				} 
			  }
			}
			str+='</tbody>';
			str+='</table>';
		}
		
		$("#apptmemberDetailsDiv").html(str);
		/* Make Selected People next time who has already selected for referral in first search */
		if(globalSelectedMemberIdsArr != null && globalSelectedMemberIdsArr.length > 0){
			for(var i=0;i<globalSelectedMemberIdsArr.length;i++){
				$(".close"+globalSelectedMemberIdsArr[i]).prop("checked",true);
				$(".searhMemberCls"+globalSelectedMemberIdsArr[i]).css("background-color", "lightgrey"); 
			}	
		}
		
		$('[data-toggle="tooltip"]').tooltip()
		$('.check').tooltip()
		$('#searchedMembersId').DataTable({
			
		});
		//applyPagination();
	}
</script>
</body>
</html>