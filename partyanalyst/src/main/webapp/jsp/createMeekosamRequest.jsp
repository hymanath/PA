<%@ page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title> MEEKOSAM GRIEVANCE </title>
<!-- Bootstrap -->
<!--<link href="dist/2016DashBoard/css/bootstrap.css" rel="stylesheet" type="text/css">-->
<link href="alertDepartment/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="alertDepartment/css/responsive.css" rel="stylesheet" type="text/css">
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/Slick/slick.css" type="text/css" rel="stylesheet"/>
<link href="dist/alertDashBoard/dist/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<link href="training/dist/Timepicker/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet"/>
<link href="alertDepartment/css/customLess.less" rel="stylesheet" type="text/less">
<!--<link href="alertDepartment/css/custom.css" rel="stylesheet" type="text/css">-->
<!-- JQuery files (Start) -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/less.js/2.7.2/less.min.js"></script>

<!-- YUI Dependency files (Start) -->
<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
<script type="text/javascript" src="js/yahoo/animation-min.js"></script> 
<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script>
<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
<script type="text/javascript" src="js/yahoo/button-min.js"></script> 	
<script src="js/yahoo/resize-min.js"></script> 
<script src="js/yahoo/layout-min.js"></script> 
<script type="text/javascript" src="js/yahoo/container-min.js"></script> 

<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
<script type="text/javascript" src="js/json/json-min.js"></script>
<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
<script type="text/javascript" src="js/yahoo/tabview-min.js"></script> 
<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script> 
<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/calendar-min.js"></script>
<!-- Skin CSS files resize.css must load before layout.css --> 
<link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css"> 
<link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css">
<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
<link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css"> 
<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css">
<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
<link rel="stylesheet" type="text/css" href="styles/yuiStyles/calendar.css"> 
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">    
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css"> 
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">	
<link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
<!-- YUI Dependency files (End) -->
	
</head>  	
<body>
<div class="container" style="margin-top:30px">
	<div class="row">
		<div class="col-sm-12">
			<div class="panel panel-default">
				<div class="panel-heading headingColor">
					<h4 class="panel-title text-capital fontColor"><strong>Create New Grievance</strong></h4>
					
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-12" id="newGrevanceDivId">
							<form id="saveMeekosamGrievanceForm" name="saveMeekosamGrievanceForm" enctype="multipart/form-data" method="POST">
								<div class="row">      
									<div class="col-sm-12">
										<h4 class="text-success text-capital">grievance using</h4>
									</div>
									<div class="col-sm-12 m_top10">
										<label class="radio-inline">
											<input type="radio" name="typeOfSearch" class="typeOfSearch" value="aadhar"/>Aadhar Number
										</label>
										<label class="radio-inline">
											<input type="radio" name="typeOfSearch" class="typeOfSearch" value="mobile"/>Mobile Number
										</label>
										<label class="radio-inline">
											<input type="radio" name="typeOfSearch" class="typeOfSearch" value="voter"/>Voter Card
										</label>
									</div>
									<div class="m_top20 col-sm-4">
										<input type="text" class="form-control searchValue" placeholder="Enter Aadhar Number"/>
									</div>
									<div class="m_top20 col-sm-4">
										<button class="btn btn-success text-capital" onclick="searchPetitionerDetailsByVoterNoAadharNoMobileNo()">search</button>
									</div>
									<div class="col-sm-12 m_top20">
										<div id="searchPetitionerDetailsByVoterNoAadharNoMobileNo"></div>
									</div>
									<div id="buildProfileData">
										<div class="col-sm-12 m_top20">
											<h4 class="text-success text-capital">about petitioner</h4>
										</div>
										<div class="col-sm-3 m_top10">
											<label>Name <span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgPetNameId"></span></label>
											<input type="text" class="form-control" id="petitionerNameId" name="meekosamGrievanceVO.petitionerName"/>
										</div>
										<div class="col-sm-3 m_top10">
											<label>Relative Name <span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgPetRelNameId"></span></label>
											<input type="text" class="form-control" id="petitionerRelativeNameId" name="meekosamGrievanceVO.petitionerRelativeName"/>
										</div>
										<div class="col-sm-2 m_top10">
											<label>Gender <span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgPetGenId"></span></label>
											<input type="text" class="form-control" id="petitionerGenderId" placeholder="Male/Female" name="meekosamGrievanceVO.petitionerGender"/>
										</div>
										<div class="col-sm-2 m_top10">
											<label>Date Of Birth <span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgPetDOBId"></span></label>
											<div class="input-group">
												<span class="input-group-addon">
													<i class="glyphicon glyphicon-calendar"></i>
												</span>
												<input type="text" id="datePicker" class="form-control" id="petitionerDOBId" name="meekosamGrievanceVO.petitionerDOB"/>
											</div>
										</div>
										<div class="col-sm-2 m_top10">
											<label>Age <span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgPetAgeId"></span></label>
											<input type="text" class="form-control" id="petitionerAgeId" name="meekosamGrievanceVO.petitionerAge"/>
										</div>
										<div class="col-sm-2 m_top10">
											<label>Phone Number <span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgPetMobNoId"></span></label>
											<input type="text" class="form-control" id="petitionerMobileNO" name="meekosamGrievanceVO.petitionerMobileNo"/>
										</div>
										<div class="col-sm-2 m_top10">
											<label>Voter Number <span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgPetVoterId"></span></label>
											<input type="text" class="form-control" id="petitionerVoterId" name="meekosamGrievanceVO.petitionerVoterCardNo"/>
										</div>
										<div class="col-sm-3 m_top10">
											<label>Aadhar Number <span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgPetAadharId"></span></label>
											<input type="text" class="form-control" id="petitionerAadharId" name="meekosamGrievanceVO.petitionerAadharCardNo"/>
										</div>
										<div class="col-sm-3 m_top10">
											<label>Email</label>
											<input type="text" class="form-control" id="petitionerEmailId" name="meekosamGrievanceVO.petitionerEmailId"/>
										</div>
										<div class="col-sm-12 m_top20">
											<h4 class="text-success text-capital">location details</h4>
										</div>
										<div class="col-sm-3 m_top10">
											<label>District <span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgPetDistrictId"></span></label>
											<select class="selectChosen" attr_districtId="'+districtId+'" id="districts" name="meekosamGrievanceVO.petitionerDistrictId"></select>
										</div>
										<div class="col-sm-3 m_top10">
											<label>Mandal <span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgPetMandalId"></span></label>
											<select class="selectChosen" id="mandals" name="meekosamGrievanceVO.petitionerTehsilId"></select>
										</div>
										<div class="col-sm-3 m_top10">
											<label>Village/Town <span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgPetVillageId"></span></label>
											<select class="selectChosen" id="panchayats" name="meekosamGrievanceVO.petitionerPanchayatId"></select>
										</div>
										<div class="col-sm-3 m_top10">
											<label>Habitation</label>
											<select class="selectChosen" id="habitationId" name="meekosamGrievanceVO.petitionerHamletId"></select>
										</div>
										<div class="col-sm-3 m_top10">
											<label>House No <span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgPetHouseNOId"></span></label>
											<input type="text" class="form-control" id="houseNo" name="meekosamGrievanceVO.petitionerHouseNO"/>
										</div>
										<div class="col-sm-12">
											<div class="row">
												<div class="col-sm-5 m_top20">
													<h4 class="text-success text-capital">Caste Information <span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgPetCasteId"></span></h4>
													<div class="panel panel-default m_top10">
														<div class="panel-body">
															<div id="casteDataId"></div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="col-sm-12 m_top20">
											<h4 class="text-success text-capital">Other</h4>
										</div>
										<div class="col-sm-3 m_top20">
											<label>Occupation <span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgPetOccupationId"></span></label>
											<select class="selectChosen" id="occupationListId" name="meekosamGrievanceVO.petitionerOccupation"><option value="0">Select Occupation</option></select>
										</div>
										<div class="col-sm-3 m_top20">
											<label>Argee Category <span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgPetArgeeId"></span></label>
											<select class="selectChosen" id="argeeCategoryListId" name="meekosamGrievanceVO.petitionerArgeeCategory"><option value="0">Select Argee Category</option></select>
										</div>
										<div class="col-sm-4 m_top20">
											<label>Annual Income <span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgPetIncomeId"></span></label>
											<div class="panel panel-default">
												<div class="panel-body">
													<div id="annaulIncomeDataId"></div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12 m_top20">
										<h4 class="text-success text-capital">Details From Petitioner</h4>
									</div>
									<div class="col-sm-3 m_top10">
										<label>Category <span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgCategoryId"></span></label>
										<select class="selectChosen" id="categoryId" name="meekosamGrievanceVO.categoryId">
											<option value="0">Select Category</option>
											<option value="6">Monday Grievance</option>
											<option value="7">Janmabhoomi</option>
											<option value="8">Special Grievance - SC/ST</option>
											<option value="9">General Grievance</option>
										</select>
									</div>
									<div class="col-sm-3 m_top10">
										<label>Department <span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgDepartmentId"></span></label>
										<select class="selectChosen" id="departmentId" onchange="getIssueTypesForDepartment();" name="meekosamGrievanceVO.departmentId">
											<option value="0">Select Department</option>
										</select>
									</div>
									<div class="col-sm-3 m_top10">
										<label>Issue Type <span style="color:red">*</span></label>
										<select class="selectChosen" id="issueTypeId" onchange="getIssueSubTypes();">
											<option value="0">Select Issue Type</option>
										</select>
									</div>
									<div class="col-sm-3 m_top10">
										<label>Issue Sub Type <span style="color:red">*</span></label>
										<select class="selectChosen" id="issueSubTypeId" onchange="getDynamicValuesForIssue();">
											<option value="0">Select Issue Sub Type</option>
										</select>
									</div>
									<div id="buildPetitionerData" class="col-sm-12"></div>
									<div id="revenueDeptSplBlock" style="display:none;">
										<div class="col-sm-12 m_top20">
											<h4 class="panel-title text-capital fontColor">Other Details</h4>
										</div>
										<div class="col-sm-4 m_top20">
											<label>District</label>
											<select class="selectChosen" id="districtsPetitionerId">
												<option>Select District</option>
											</select>
										</div>
										<div class="col-sm-4 m_top20">
											<label>Mandal</label>
											<select class="selectChosen" id="mandalsPetitionerId">
												<option></option>
											</select>
										</div>
										<div class="col-sm-4 m_top20">
											<label>Village</label>
											<select class="selectChosen" id="villagePetitionerId">
												<option></option>
											</select>
										</div>
										<div class="col-sm-4 m_top20">
											<label>Survey Number</label>
											<input type="text" class="form-control" id="surveyNoPetitionerId"/>
										</div>
										<div class="col-sm-4 m_top20">
											<label>Land In Acres</label>
											<input type="text" class="form-control" id="landInAcresPetitionerId"/>
										</div>
										<div class="col-sm-4 m_top20">
											<label>Land In Cent</label>
											<input type="text" class="form-control" id="landInCentPeitionerId"/>
										</div>
										<div class="col-sm-4 m_top20">
											<button class="btn btn-success" type="button" id="addOneMorePetitionerId"><i class="fa fa-plus"></i> ADD MORE</button>
										</div>
										<div class="col-sm-12 m_top20">
											<table class="table" id="petitionerTableId">
												<thead>
													<th>District</th>
													<th>Mandal</th>
													<th>Village</th>
													<th>Survey Number</th>
													<th>Land in Acres</th>
													<th>Land in Cent</th>
													<th></th>
												</thead>
											</table>
										</div>
									</div>
									
								</div>
								<div class="row">
									<div class="col-sm-12 m_top10">
										<h4 class="text-success text-capital m_top20">alert details</h4>
									</div>
									<div class="col-sm-3 m_top10">
										<label>Location Level<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgAlertLevelId"></span></label>
										<select id="alertLocLevelId" class="selectChosen" onchange="displayLevelsByLevelId();">
											<option value="0">Select Level</option>
											<option value="1">District</option>
											<option value="2">Mandal/Muncipality</option>
											<option value="3">Village/Ward</option>
										</select>
									</div>
									<div class="col-sm-3 m_top10" id="locDisDivId" style="display:none;">
										<label>District<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgAlertDistId"></span></label>
										<select id="locationDistrictId" class="selectChosen" name="meekosamGrievanceVO.grievanceDistrictId">
											<option value="0">Select District</option>
										</select>
									</div>
									<div class="col-sm-3 m_top10" id="locManDivId" style="display:none;">
										<label>Mandal/Muncipality<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgAlertMandId"></span></label>
										<select id="locationMandalId" class="selectChosen" name="meekosamGrievanceVO.grievanceTehsilId">
											<option value="0">Select Mandal/Muncipality</option>
										</select>
									</div>
									<div class="col-sm-3 m_top10" id="locVilDivId" style="display:none;">
										<label>Village/Ward<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgAlertVillId"></span></label>
										<select id="locationVillageId" class="selectChosen" name="meekosamGrievanceVO.grievancePanchayatId">
											<option value="0">Select Village/Ward</option>
										</select>
									</div>
								</div>
								<div class="row m_top20">
									<div class="col-sm-8 m_top10">
										<label>Grievance Title<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgAlertTitleId"></span></label>
										<label class="radio-inline" style="margin-bottom: 5px;">
											<input type="radio" value="te" name="language" class="lang" id="telugu" checked  onclick="languageChangeHandler();"/>Telugu
										</label>
										<label class="radio-inline" style="margin-bottom: 5px;">
											<input type="radio" value="en" name="language" class="lang" id="eng" onclick="languageChangeHandler();"/>English
										</label>
										<input type="text" class="form-control" id="alertTitleId" name="meekosamGrievanceVO.grievanceTitle"/>
									</div>
									<div class="col-sm-12 m_top10">
										<label>Grievance Description : <span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgAlertDescId"></span></label>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12 m_top10">
										<textarea class="form-control" id="alertdescriptionId" name="meekosamGrievanceVO.grievanceDesc"></textarea>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12 m_top10">
										<h4 class="text-success text-capital m_top20">referral information</h4>
									</div>
									<div class="col-sm-4 m_top10">
										<label>Referred by<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgReferredById"></span></label>
										<select class="selectChosen" id="referredTypeId" name="meekosamGrievanceVO.referalTypeId">
											<option></option>
										</select>
									</div>
									<div class="col-sm-4 m_top10">
										<label>District by<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgReferDistId"></span></label>
										<select class="selectChosen" id="districtsReferralId" name="meekosamGrievanceVO.referalDistrictId">
											<option></option>
										</select>
									</div>
									<div class="col-sm-4 m_top10">
										<label>Name<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgReferNameId"></span></label>
										<select class="selectChosen" id="referredNameId" name="meekosamGrievanceVO.referalNameId">
											<option></option>
										</select>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12 m_top10">
										<h4 class="text-success text-capital m_top20">assigning to officer</h4>
									</div>
									<div class="col-sm-4 m_top10">
										<label>Sub Department<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgSubDeptId"></span></label>
										<select  class="selectChosen" id="subDepartmentSelectId" name="meekosamGrievanceVO.subDeptId">  
											<option value="0">Select Sub Department</option>
										</select>
									</div>
									<div class="col-sm-4 m_top10">
										<label>Location Level<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgLevelId"></span></label>
										<select  class="selectChosen" id="locationLevelSelectId" name="meekosamGrievanceVO.assignLevelId">  
											<option value="0">Select Level</option>
										</select>
									</div>
									<div id="parentLevelDivId"> </div>
									<div class="col-sm-4 m_top10">
										<label>Designation<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgDesignationId"></span></label>
										<select id="designationsId" class="selectChosen" name="meekosamGrievanceVO.designationId">
											<option></option>
										</select>
									</div>
									<div class="col-sm-4 m_top10">
										<label>Department Officer<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgOfficerId"></span></label>
										<select id="officerNamesId" class="selectChosen" name="meekosamGrievanceVO.officerId">
											<option></option>
										</select>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-4 m_top20">
										<button class="btn btn-success" onclick="saveGrievanceInfo();" id="createMeekosamId">SUBMIT GRIEVANCE</button>
									</div>
									<div class="col-sm-4 m_top20">
										<img style="display: none;" alt="Processing Image" src="./images/icons/search.gif" id="creatingLdngImg"/>
									</div>
									<div class="col-sm-4 m_top20">
										<span id="successmsg"></span>
									</div>
								</div>
								<div id="dynamicDataDivId"></div>
								<input type="hidden" id="hiddenAlertLocationLevelId" name="meekosamGrievanceVO.grievanceImpactLevel" value="0"/>
								<input type="hidden" id="hiddenAlertLocationValueId" name="meekosamGrievanceVO.grievanceLevelValue" value="0"/>
								<input type="hidden" id="hiddenPetitionerCasteId" name="meekosamGrievanceVO.petitionerCaste" value="0"/>
								<input type="hidden" id="hiddenPetitionerIncomeId" name="meekosamGrievanceVO.petitionerAnnulaIncome" value="0"/>
								<input type="hidden" id="hiddenPetitionerId" name="meekosamGrievanceVO.meekosamPetitionerId" value="0"/>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="daterangepicker/moment.js" type="text/javascript"></script>
<script src="daterangepicker/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Slick/slick.js" type="text/javascript"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript" src="alertDepartment/js/createMeekosamRequest.js"></script>
<script type="text/javascript">
google.load("elements", "1", {
	packages: "transliteration"
});
var control;
var lang;

function onLoad() {
   lang = $("input[name=language]:checked").val();
	var options = {
		sourceLanguage:google.elements.transliteration.LanguageCode.ENGLISH,
		destinationLanguage:[''+lang+''],
		shortcutKey: 'alt+t',
		transliterationEnabled: true
	};
	// Create an instance on TransliterationControl with the required options.
	control = new google.elements.transliteration.TransliterationControl(options);
	// Enable transliteration in the textbox with id 'descrptionId'.
	if ($('#alertTitleId').length){
		control.makeTransliteratable(['alertTitleId']);
	}
	if ($('#alertdescriptionId').length){
		control.makeTransliteratable(['alertdescriptionId']);
	}
	
}
function languageChangeHandler(){
   var lang1 = $("input[name=language]:checked").val();
	if(lang1 =="en"){
		control.disableTransliteration();
	}else{
		control.enableTransliteration();
		control.setLanguagePair(
		google.elements.transliteration.LanguageCode.ENGLISH,
		lang1);
	}
}
google.setOnLoadCallback(onLoad);
</script>
</body>
</html>