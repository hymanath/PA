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
<link href="dist/Plugins/Dropzone/basic.css" rel="stylesheet" type="text/css"/>
<link href="dragAndDropPhoto/css/jquery.filer.css" type="text/css" rel="stylesheet" />
<link href="dragAndDropPhoto/css/themes/jquery.filer-dragdropbox-theme.css" type="text/css" rel="stylesheet" />
<link href="dist/Plugins/Datatables/datatables.css" rel="stylesheet" type="text/css"/>
<link href="dist/activityDashboard/Date/daterangepicker.css" rel="stylesheet" type="text/css">
<link href="dist/activity/Timepicker/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
<link href="dist/activityDashboard/SelectDropDown/dropkick.css" rel="stylesheet" type="text/css">
<link href="dist/slick/slick.css" rel="stylesheet" type="text/css"/>
<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/Appointment/DropkickNew/dropkick.2.1.8.min.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="dist/Plugins/Datatables/datatables.js" type="text/javascript"></script>
<script type="text/javascript" src="dragAndDropPhoto/js/customNominated.jquery.filter.min.js?v=1.0.5"></script>
<script type="text/javascript" src="dragAndDropPhoto/js/customNominatedPost.js?v=1.0.5"></script>
<script src="js/simplePagination/simplePagination.js" type="text/javascript"></script>
<script src="dist/slick/slick.js" type="text/javascript"></script>
<style type="text/css">
#searchedMembersId_length , #searchedMembersId_filter , #searchedMembersId_info , #searchedMembersId_paginate , #searchedMembersId thead th
{
	display:none;
}
.panelPost .panel-body
{
	height:250px;
	overflow-y:scroll;
}
.createAppointmentSearch>li{
	width:300px !important;
	
}
.jFiler-input-dragDrop
{
	width:100%;
}
.panel-footer
{
	background:#ccc;
	cursor:pointer;
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
.involveBlock {
    background: #f8f9fb none repeat scroll 0 0;
    border: 1px solid #ddd;
    padding: 5px;
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
.text-capital {
    text-transform: uppercase;
}
.removeIconNew {
    cursor: pointer;
    display: none;
    position: absolute;
    right: 15px;
    top: 0;
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
		top:0px;
		right:15px;
		display:none;
		cursor:pointer;
	}
	.involveBlock:hover .removeIconNew
	{
		display:block;
	}
	.best-matched-profile .slick-list
    {
       margin:0px !important;
    }
	.slick-prev::before, .slick-next::before
	{
		color:#333 !important;
	}
</style>
</head>
<body>
<div class="container">
	<div class="row">
    	<div class="col-md-12">
       	  <div class="panel panel-default">
        	  <div class="panel-heading">
        	    <h3 class="panel-title">NOMINATED POST PROFILE CREATION</h3>
	      	  </div>
        	  <div class="panel-body bg_ef">
              	<div class="row">
                	<div class="col-md-12 col-xs-12 col-sm-12 col-lg-12 m_top20">
                    	<p class="text-success">SEARCH APPLICANT</p>
                    </div>
					<div class="col-md-12 col-xs-12 col-sm-12 col-lg-12 m_top10">
                    	<label class="checkbox-inline">
                        	<input value="Cadre" type="radio" class="cadreCheckCls" name="checkBoxName" id="cadreSearchId" checked="true" onclick="refreshExistingDetails();" />Cadre
                        </label>
                        <label class="checkbox-inline" style="padding-left: 10px;">
                        	<input value="Not Cadre" type="radio" class="cadreCheckCls" id="notCadreSearchId" name="checkBoxName"/>Not Cadre
                        </label>
						
						<span id="addMemberDivId" style="display:none;position: relative; margin-left: 8px; cursor: pointer; background: #ddd; padding: 4px; border-radius: 12px; top: 2px;" class="addMemberModal" onclick="clearAssignValues();">
                           <i class="glyphicon glyphicon-plus-sign"></i>
                           Add Member
                          </span>		
                    </div>
					</div>
					
          <div id="searchMemberDiv">
                    <div class="col-md-2 col-xs-12 col-sm-6 col-lg-2 m_top10" id="statedisplaydivid">
                    	<label>State</label>
						<span id="statesDivIdImg"><img src="images/search.gif" style="display:none;"/></span>
                        <select id="statesDivId"  onchange="getDistrictsForStates(this.value,this.id,'');" class="chosenSelect">
                        	<option value="0">All</option>
							<option value="1">AndhraPradesh</option>
							<option value="36">Telangana</option>
                        </select>
                    </div>
                    <div class="col-md-2 col-xs-12 col-sm-6 col-lg-2 m_top10" id="districtDiv">
                    	<label>District</label>
						<span id="districtIdImg"><img src="images/search.gif" style="display:none;"/></span>
                        <select id="districtId" onchange="getConstituenciesForDistricts(this.value,this.id,'');" class="chosenSelect" >
                        
                        </select>
                    </div>
                    <div class="col-md-2 col-xs-12 col-sm-6 col-lg-2 m_top10" id="constitunecyDiv">
                    	<label>Constituency</label>
						<span id="constituencyIdImg"><img src="images/search.gif" style="display:none;"/></span>
                        <select id="constituencyId" onchange="getMandalCorporationsByConstituency('',this.id);" class="chosenSelect" >
                        </select>
                    </div>
                    <div class="col-md-3 col-xs-12 col-sm-6 col-lg-3 m_top10" id="mandalDiv">
                    	<label>Mandal/Muncipality/Corporation</label>
						<span id="mandalListImg"><img src="images/search.gif" style="display:none;"/></span>
                        <select id="mandalList" onchange="getPanchayatWardByMandal('',this.id);"  class="chosenSelect">
							<option value="0"> Select Mandal/Municipality </option>
                        </select>
                    </div>
                    <div class="col-md-3 col-xs-12 col-sm-6 col-lg-3 m_top10" id="panchayatDiv">
                    	<label>Panchayat/Ward/Division</label>
						<span id="panchaytListImg"><img src="images/search.gif" style="display:none;"/></span>
                        <select id="panchaytList"  onchange="getAllCadreInPanchayat();" class="chosenSelect">
							<option value="0"> Select Panchayat </option>
                        </select>
                    </div>
                
                <div class="row" class="searchMemberCls">
                	<div class="col-md-8 col-xs-12 col-sm-12 col-lg-12">
					<label class="text-capitalize">Search member by voter id/membership no/mobile number/Name</label>
                        <div class="searchDiv">
                            <div class="row">
                                <div class="col-md-9 col-sm-9 col-xs-12 col-lg-9 pad_right0">
                                	<div class="pad_5 bg_ff">
									<label class="radio-inline">
                                            <input type="radio" name="searchBasedOn" checked="true" class="searchTypeCls" onclick="refreshExistingDetails();" id="membershipId" value="1"/>Membership No
                                        </label>
                                        <label class="radio-inline">
                                        <input type="radio" name="searchBasedOn" class="searchTypeCls" id="voterId"  onclick="refreshExistingDetails();"  value="2" />Voter ID
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio"  name="searchBasedOn" class="searchTypeCls" id="mobileNo"  onclick="refreshExistingDetails();"  value="3"/>Mobile Number
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="searchBasedOn" class="searchTypeCls" id="name"  onclick="refreshExistingDetails();"  value="4"/>Name
                                        </label>
										<input type="hidden" id="cadreSearchType" value="membershipId" />
                                    </div>
                                    <input type="text" class="form-control" id="searchBy"/>
									<div id="searchErrDiv" style="color:red;"></div>
                                </div>
                                <div class="col-md-3 col-sm-3 col-xs-3 col-lg-3 pad_left0">
                                    <button class="btn btn-success btn-block btnSearch" id="searchbtn">SEARCH</button>
                                </div>
                            </div>
                        </div>
                     </div>
                 </div>
				 </div>
				 <div id="cadreById" style="display:none;">
				 <div class="row" class="searchMemberCls">
                	<div class="col-md-8 col-xs-12 col-sm-12 col-lg-12">
					<label class="text-capitalize">Search member by voter id/membership no/mobile number/Name</label>
                        <div class="searchDiv">
                            <div class="row">
                                <div class="col-md-9 col-sm-9 col-xs-12 col-lg-9 pad_right0">
                                	<div class="pad_5 bg_ff">
									    <label class="radio-inline">
											<input type="radio" name="radioGroup" id="voterId1"  onclick="refreshExistingDetails();"  value="2" class="searchTypeCls1" />Voter ID
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio"  name="radioGroup" id="mobileNo1"  onclick="refreshExistingDetails();"  value="3" class="searchTypeCls1"/>Mobile Number
                                        </label>
										<label class="radio-inline">
                                            <input type="radio" name="radioGroup"  id="name1"  onclick="refreshExistingDetails();"  value="4" checked="true" class="searchTypeCls1"/>Name
                                        </label>
										<input type="hidden" id="cadreSearchType1" value="mobileNo1" />
                                    </div>
                                    <input type="text" class="form-control" id="searchById"/>
									<div id="searchErrDiv1" style="color:red;"></div>
                                </div>
                                <div class="col-md-3 col-sm-3 col-xs-3 col-lg-3 pad_left0">
                                    <button class="btn btn-success btn-block btnSearch" id="searchbtn">SEARCH</button>
                                </div>
                            </div>
                        </div>
						<div id="notCadreErrMsg" style="color:red;"></div>
                     </div>
                 </div>
				 </div> 
				 
				 <form name="submitApplication" id="submitApplication"  method="post" enctype="multipart/form-data">
                 <div class="row">
				
						
				   <div class="col-md-12 col-xs-12 col-sm-12" id="searchDivId" style="display:none;">
                    		<!--<h4 class="m_0 text-success">APPLICANT PROFILE DETAILS</h4>-->
                        	<div id="searchData"></div>
                        	<div class="" id="scrollDivId" style="display:none;">
								<div  id="cadreSearchDtls" ></div>
                                
								<div class="" id="loadingSmblDivId"></div>
                             </div>							
                             <p class="text-muted" id="textId" style="display:none;"><small>Note: Please select matches profile</small></p>
	                </div>
	                </div>
	               
				   <div class="row ramakrishnaCls" style="display:none;" id="" >
					<div id="appliedPostForSelectedId" class="hideDivCls"></div>
                    <div class="col-md-12 col-xs-12 col-sm-12 col-lg-12 m_top20 hideDivCls">
                    	<div class="panel panel-default panelNewDepartments">
                        	<div class="panel-heading">
                            	<h4 class="panel-title">DO YOU WANT TO CHANGE YOUR ADDRESS OR PHONE NUMBER <input type="checkbox" id="addressCheckId"/></h4>
                            </div>
                            <div class="panel-body bg_ff pad_10" style="display:none;" id="changePhoneNumberDiv">
                            	<div class="row">
									<div id="errorMsg" style="color: red;margin-left:25px;"></div>
                                   	<div class="col-md-4 col-lg-4 col-sm-5 col-xs-11">
                                    	<label>CHANGE PHONE NUMBER</label>
                                    	<div class="input-group">
                                        	<input type="text" class="form-control" id="phoneNumId" name="nominatedPostVO.phoneNumName" maxLength="10"/>
                                            <span class="input-group-addon bg_ff">
                                            	<!--<i class="glyphicon glyphicon-plus-sign"></i>
                                                <i class="glyphicon glyphicon-minus-sign"></i>-->
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                	<div class="col-md-12 col-xs-12 col-sm-12 col-lg-12 m_top10">
	                                	<h4 class="panel-title">ADDRESS CHANGE</h4>
                                    </div>
                                	<div class="col-md-3 col-sm-6 col-xs-12 col-lg-3 m_top10">
                                    	<label>H No</label>
                                        <input type="text" class="form-control" id="houseNumberId" name="nominatedPostVO.houseNumberName"/>
                                    </div>
                                    <div class="col-md-3 col-sm-6 col-xs-12 col-lg-3 m_top10">
                                    	<label>Address Lane 1</label>
                                        <input type="text" class="form-control" id="addressLane1Id" name="nominatedPostVO.addressLane1Name"/>
                                    </div>
                                    <div class="col-md-3 col-sm-6 col-xs-12 col-lg-3 m_top10">
                                    	<label>Address Lane 2</label>
                                        <input type="text" class="form-control" id="addressLane2Id" name="nominatedPostVO.addressLane2Name"/>
                                    </div>
                                    <div class="col-md-3 col-sm-6 col-xs-12 col-lg-3 m_top10">
                                    	<label>State</label>
                                        <select class="chosenSelect" attr_id="addStateId" id="changestateId" onchange="getDistrictsForStates(this.value,this.id,'');"  name="nominatedPostVO.addStateName">
										<option value="0">Select State</option>
                                        	<option value="1">AndhraPradesh</option>
											<option value="36">Telangana</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="row">
                                	<div class="col-md-3 col-sm-6 col-xs-12 col-lg-3 m_top10">
                                    	<label>District</label>
                                        <select class="chosenSelect" attr_id="addDistrictId" id="changedistrictId" onchange="getConstituenciesForDistricts(this.value,this.id,'');" name="nominatedPostVO.addDistrictName">
                                        	<option value="0"> Select District</option>
                                        </select>
                                    </div>
                                    <div class="col-md-3 col-sm-6 col-xs-12 col-lg-3 m_top10">
                                    	<label>Constituency</label>
                                        <select class="chosenSelect" attr_id="addConstituencyId" id="changeConstiId"  onchange="getMandalCorporationsByConstituency('',this.id);" name="nominatedPostVO.addConstituencyName">
                                        	<option value="0"> Select Constituency </option>
                                        </select>
                                    </div>
									  <div class="col-md-3 col-sm-6 col-xs-12 col-lg-3 m_top10">
                                    	<label>Mandals/Municipality</label>
                                        <select class="chosenSelect" attr_id="addMandalsId" id="changeMandalId" onchange="getPanchayatWardByMandal('',this.id);" name="nominatedPostVO.addMandalsName">
                                        	<option value="0"> Select Mandals/Municipality </option>
                                        </select>
                                    </div>
                                    <div class="col-md-3 col-sm-6 col-xs-12 col-lg-3 m_top10">
                                    	<label>Village</label>
                                        <select class="chosenSelect" attr_id="addVillageId" id="changePanchyatId"  onchange="getAllCadreInPanchayat();" name="nominatedPostVO.addVillageName">
                                        	<option value="0"> Select Panchayat </option>
                                        </select>
                                    </div>
                                    <div class="col-md-3 col-sm-6 col-xs-12 col-lg-3 m_top10">
                                    	<label>Pincode</label>
                                        <input type="text" class="form-control" id="addPincodeId" name="nominatedPostVO.addPincodeName" maxLength="6"/>
                                    </div>
									 <!--<div class="col-md-3 col-sm-6 col-xs-12 col-lg-3 m_top10 col-md-offset-6">
                                    	<button type="button" class="btn btn-primary btn-sm pull-right" id="updateButnId" onclick="savechangeAddressForNominatedPost()" style="margin-top:25px;">UPDATE</button>
                                    </div>-->
								</div>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-12 col-xs-12 col-sm-12 col-lg-12 m_top20 hideDivCls">
                    	<!--<div class="bg_ff pad_10">
                        	
                        </div>-->
                    </div>
                	<div class="col-md-12 col-xs-12 col-sm-12 col-lg-12 m_top20 hideDivCls">
                    	<h4 class="m_0 text-success">NOMINATED LEVEL AND LOCATION 
							<!--<span class="pull-right f_14" style="cursor:pointer" >
								Add One More 
								<i class="glyphicon glyphicon-plus-sign"></i>
							</span>-->
						</h4>
                    </div>
					<!-- ss -->
					<div class="col-md-12 col-xs-12 col-sm-12 col-lg-12 hideDivCls">
						<div class="addBlockNew cloneBlockDiv">
							<div class="row">
                            	<div class="col-md-4 col-sm-4 col-xs-12 m_top10 col-lg-4">
                                	<h4 class="panel-title">PLEASE SELECT APPLYING POST TYPE</h4>
                                </div>
                                <div class="col-md-5 col-sm-5 col-xs-12 col-lg-5">
                                	<div class="btn-group">
                                      <button  type="button" id="nomintdPostId" class="btn btnClassChange btnNewCustom btnActive btn-xs nominatdPostSelCls" attr_postId="1">Nominated Post</button>
                                      <button  type="button" id="partyPostId" class="btn btnClassChange btnNewCustom btn-xs partyPostSelCls" attr_postId="2">Party Post</button>
										<input attr_no='' type="hidden" class="postTypeCls" id="postTypeId" name="nominatedPostVO.nominatdList[0].postTypeId">
									</div>
                                </div>
                            </div>
							<div class="row">
							<div class="errorMsgCls" style="color:red;"></div>
								<span class="iconClose" style="display:none;cursor:pointer;">
									<i class="glyphicon glyphicon-remove"></i>
								</span>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10">
									<label>Nominated Post Level</label>
									<select class="chosenSelect boardLvlCls boardLvelCls validateCls" id="boardLvlId" name="nominatedPostVO.nominatdList[0].boardLevelId" onchange="showHideByNominatedPost(''); getDepartments(0);" attr_no="">
									<option value="0">Select Post Level</option>
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10 stateShowCls" id="statesShowDivId">
									<label>State Name</label>
									<select class="chosenSelect nominatedStaeCls" onchange="getDistrictsForStates(this.value,this.id,'');getDepartments(0);" id="nominatedStaeId" name="nominatedPostVO.nominatdList[0].stateId" attr_no="">
										<option value="0">Select State</option>
										<option value="1">Andhra Pradesh</option>
										<option value="36">Telangana</option>
									</select>
								</div>
						        <div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10 districtShowCls" id="districtShowDivId">
									<label>District</label>
									<select class="chosenSelect nominatedDistCls" onchange="getConstituenciesForDistricts(this.value,this.id,'');getDepartments(0);" id=
									"nominatedDistId" name="nominatedPostVO.nominatdList[0].districtId" attr_no="">
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10 constituencyShowCls" id="constituencyshowDivId">
									<label>Constituency</label>
									<select attr_no="" class="chosenSelect nominatdConstCls" onchange="getMandalCorporationsByConstituency('',this.id);getDepartments(0);" id="nominatdConstId" name="nominatedPostVO.nominatdList[0].constituencyId">
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10 mandalShowCls" id="mondalShowDivId">
									<label>Mandal/ Muncipality / Corporation</label>
									<select attr_no="" class="chosenSelect nominatedMandlCls" onchange="getPanchayatWardByMandal('',this.id);getDepartments(0);" id="nominatedMandlId" name="nominatedPostVO.nominatdList[0].mandalId">
									
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10 panchayatShowCls" id="panchayatShowDivId">
									<label>Panchayat/ Ward / Division</label>
									<select attr_no="" class="chosenSelect nominatedPanchayatCls" id="nominatedPanchayatId" name="nominatedPostVO.nominatdList[0].panchayatId" onchange="getDepartments(0);">
										
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10">
									<label>Department Name</label>
									<select attr_no="" class="chosenSelect depmtsCls"  id="depmtsId" onchange="getDepartmentBoards(0);" name="nominatedPostVO.nominatdList[0].deptId">
									<option value="0">Select Department</option>
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10">
									<label>Corporation/Board Name</label>
									<select attr_no="" class="chosenSelect deptBoardCls" id="deptBoardId" onchange="getDepartmentBoardPositions('');" name="nominatedPostVO.nominatdList[0].deptBoardId">
									<option value="0">Select Department Board</option>	
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10">
									<label>Position Name</label>
									<select attr_no="" multiple class="chosenSelect deptBoardPostnCls" id="deptBoardPostnId" name="nominatedPostVO.nominatdList[0].positions">
									<option value="0">Select Board Position</option>
									</select>
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
						<div class="addBlockNew cloneBlockDiv" id="cloneDivBlock" style="display:none;">
							<div class="row">
                            	<div class="col-md-4 col-sm-4 col-xs-12 m_top10 col-lg-4">
                                	<h4 class="panel-title">PLEASE SELECT APPLYING POST TYPE</h4>
                                </div>
                                <div class="col-md-5 col-sm-5 col-xs-12 col-lg-5">
                                	<div class="btn-group">
                                      <button type="button" id="nomintdPostId" class="btn btnClassChange btnNewCustom btnActive btn-xs nominatdPostSelCls" attr_postId="1">Nominated Post</button>
                                      <button type="button" id="partyPostId" class="btn btnClassChange btnNewCustom btn-xs partyPostSelCls" attr_postId="2">Party Post</button>
									  <input type="hidden" class="postTypeCls" id="postTypeId">
                                    </div>
                                </div>
                            </div>
							<div class="row">
							<div class="errorMsgCls" style="color:red;"></div>
								<span class="iconClose" style="display:none;cursor:pointer;">
									<i class="glyphicon glyphicon-remove"></i>
								</span>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10">
									<label>Nominated Post Level</label>
									<select class="boardLvlCls" id="" onchange="showHideByNominatedPost('');getDepartments('');" >
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10 stateShowCls" id="statesShowDivId">
									<label>State Name</label>
									<select  class="nominatedStaeCls" onchange="getDistrictsForStates(this.value,this.id,'');" id="">
										<option value="0">Select State</option>
										<option value="1">Andhra Pradesh</option>
										<option value="36">Telangana</option>
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10 districtShowCls" id="">
									<label>District</label>
									<select  class="nominatedDistCls" onchange="getConstituenciesForDistricts(this.value,this.id,'');" id=
									"">
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10 constituencyShowCls" id="">
									<label>Constituency</label>
									<select class="nominatdConstCls" onchange="getMandalCorporationsByConstituency('',this.id);" id="">
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10 mandalShowCls" id="">
									<label>Mandal/ Muncipality / Corporation</label>
									<select  class="nominatedMandlCls" onchange="getPanchayatWardByMandal('',this.id);" id="nominatedMandlId">
										
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10 panchayatShowCls" id="">
									<label>Panchayat/ Ward / Division</label>
									<select  class="nominatedPanchayatCls" id="nominatedPanchayatId">
										
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10">
									<label>Department Name</label>
									<select  class="depmtsCls"  id="" onchange="getDepartmentBoards('');">
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10">
									<label>Corporation/Board Name</label>
									<select  class="deptBoardCls" id="" onchange="getDepartmentBoardPositions('');">
										
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10">
									<label>Position Name</label>
									<select  class="deptBoardPostnCls" id="">
									</select>
								</div>
								
							</div>
							
						</div>
						
					</div>
					
					
					<div class="col-md-12 col-xs-12 col-sm-12 col-lg-12 hideDivCls">
						<div id="addOneMoreBlock"></div>
						<div class="panel-footer m_top10"  id="addOneMore">
							<p class="text-center text-capital" >+ Click to add more nominations</p>
						</div>
					</div>
					
               
                 <div class="col-md-12 col-xs-12 col-sm-12 m_top10 hideDivCls">
                        <h4 class="panel-title text-success">REFERRAL</h4>
                        <label>Do you have reference</label><br/>
						
                        <label class="radio-inline">
                        	<input type="radio" class="referenceModal" name="radio"/>Yes
                        </label>
                        <label class="radio-inline">
                        	<input type="radio" class="referenceModalNo" checked="true" name="radio"/>No
                        </label>
                 </div>
					<!--<div class="m_top10 hideDivCls" id="involvedCandidatesDiv1">
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">INVOVLED CANDIDATES</h4>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="membersBlock1 col-md-12"></div>
									</div>
								</div>
								
							</div>
						</div>
					</div>-->
					<div class="row m_top10" id="addedRefferalsDiv" style="display:none;">
                    	<div class="col-md-12 col-sm-12 col-xs-12 m_top10">
                        	<h4 class="text-success text-capital">refered members to this candidate<small class="text-muted" id="involvedMembers">(0 - Members added)</small></h4>
                        </div>
						<!--<div class="row m_top10" id="involvedCandidatesDiv" style="display:none;">
							<div class="col-md-12">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">ADD INVOVLED CANDIDATES TO THIS ALERT</h4>
									</div>
									<div class="panel-body">
										<div class="row">
											<div class="membersBlock col-md-12" style="display:none;"></div>
										</div>
									</div>
								</div>
							</div>
						</div>-->
						<div class="membersBlock"></div>
                        <!--<div class="col-md-3">
                        	<div class="involveBlockNew">
                            	<div class="media">
                                	<div class="media-left" style="font-size:36px">
                                    	+
                                    </div>
                                    <div class="media-body">
                                    	Click to Search Involved Members Link to this alert
                                    </div>
                                </div>
                            </div>
                        </div>-->
                    </div>
					<div class="row hideDivCls" id="uploadFlDivId">
                     	<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
                        	<p>UPLOAD SCAN COPY</p>
								<input type="file" id="filer_input3" multiple="multiple"  name="fileImage"/>
                        </div>
                    </div>
                    <div class="row hideDivCls">
                    	<div class="col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4 col-xs-12 col-lg-4 col-lg-offset-4 m_top20">
                        	<button class="btn btn-success btn-block" onclick="savingApplication();" type="button">SUBMIT APPLICATION</button>
                        </div>
						<div style="margin-top:12px;"><img id="savingAjaxImg" src="images/icons/loading.gif" style="display:none;"/></div>
						<div class="col-md-6 m_top25" id="savingStatusDivId"></div>
                    </div>
					</div>
				</form>
	       </div>
      	  </div>
        </div>
    </div>
</div>
<!--<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" style="width:85%" role="document">
    <div class="modal-content">
      <div class="modal-body" style="padding:0px;">
	  <jsp:include page="commonCadreSearch.jsp" flush="true"/>
      </div>
    </div>
  </div>
</div>-->
<div class="modal fade" id="addMemberModalBlock" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" style="width:85%;" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">ADDING NEW CANDIDATE <span style="color:red;" class="addNewCandidateErrorCls"><span></h4>
      </div>
	  <form name="saveNotCadre" id="saveNotCadre"  method="post" enctype="multipart/form-data">
      <div class="modal-body">
		<div class="row">
			<div class="col-md-3 col-xs-12 col-sm-3">
				<label class="text-capitalize">voter Id<span style="color:red;">* <span id="voterExtraErrId"></span><span></label>
				<input type="text"  id="voterId" class="form-control voterCls clearClss" name="addNotcadreRegistrationVO.voterId"/>
			</div>
			<div class="col-md-3 col-xs-12 col-sm-3">
				<label class="text-capitalize">name<span style="color:red;">*<span></label>
				<input type="text" id="nameId" class="form-control clearClss"  name="addNotcadreRegistrationVO.name"/>
			</div>
			<div class="col-md-3 col-xs-12 col-sm-3">
				<label class="text-capitalize">mobile no<span style="color:red;" id="mobileExtraErrId">*<span></label>
				<input type="text" id="mobilenoId" class="form-control clearClss" maxLength="10" name="addNotcadreRegistrationVO.mobileno"/>
			</div>
			<div class="col-md-3 col-xs-12 col-sm-3">
				<label class="text-capitalize">house no</label>
				<input type="text" id="HouseId" class="form-control clearClss" name="addNotcadreRegistrationVO.houseno"/>
			</div>
			<div class="col-md-3 col-xs-12 col-sm-3 m_top10">
				<label class="text-capitalize">gender<span style="color:red;">*<span></label>
				<select class="chosenSelect clearSelectClss" id="genderId" name="addNotcadreRegistrationVO.gender">
					<option value="0">Select Gender</option>
					<option value="Male">Male</option>
					<option value="Female">female</option>
				</select>
			</div>
			<div class="col-md-3 col-xs-12 col-sm-3 m_top10">
			<label class="text-capitalize">date of birth<span style="color:red;">*<span></label>
			<div class="input-group">
				<input type="text"  id="DOBId" placeholder="mm/dd/yyyy" class="form-control clearClss" name="addNotcadreRegistrationVO.dob" />
				<span class="input-group-addon">
					<i class="glyphicon glyphicon-calendar"></i>
				</span>
			</div>
			</div>
			<div class="col-md-3 col-xs-12 col-sm-3 m_top10">
				<label class="text-capitalize">age<span style="color:red;">*<span></label>
				<input type="text"  id="ageId" class="form-control clearClss" name="addNotcadreRegistrationVO.age"/>
			</div>
			<div class="col-md-3 col-xs-12 col-sm-3 m_top10">
				<label class="text-capitalize">relative name</label>
				<input type="text" id="relativenamebyId" class="form-control clearClss" name="addNotcadreRegistrationVO. relativename"/>
			</div>
	<div class="col-md-3 col-xs-12 col-sm-3 m_top10">
				<label class="text-capitalize">relation type</label>
				<select class="chosenSelect clearSelectClss" id="relativetypeId" name="addNotcadreRegistrationVO. relativetype">
					<option></option>
				</select>
			</div>
			<div class="col-md-3 col-xs-12 col-sm-3 m_top10">
				<label class="text-capitalize">image<span style="color:red;">*<span></label>
				<input type="file" id="imageurlId" class="form-control clearClss" name="file"/>
			</div>
			<div class="col-md-3 col-xs-12 col-sm-3 m_top10">
				<label class="text-capitalize">caste<span style="color:red;">*<span></label>
				<!--<input type="text" id="casteId" class="form-control" name="addNotcadreRegistrationVO. castestateId"/> -->
				 <select id="casteId" class="chosenSelect clearSelectClss" name="addNotcadreRegistrationVO.castestateId">
                 </select>
			</div>
			<div class="col-md-3 col-xs-12 col-sm-3 m_top10">
				<label class="text-capitalize">address1</label>
				<input type="text" id="address1Id" class="form-control clearClss" name="addNotcadreRegistrationVO.address1"/>
			</div>
			<div class="col-md-3 col-xs-12 col-sm-3 m_top10">
				<label class="text-capitalize">address2</label>
				<input type="text" id="address2Id" class="form-control clearClss" name="addNotcadreRegistrationVO.address2"/>
			</div>
			
			<div id="searchMemberDiv">
                    <div class="col-md-3 col-xs-12 col-sm-3 m_top10">
                    	<label>State<span style="color:red;">*<span></label>
                        <select id="notCadreStateId"  onchange="getDistrictsForStates(this.value,this.id,'');" class="chosenSelect clearSelectClss" name="addNotcadreRegistrationVO.stateId">
							<option value="0">Select State</option>
							<option value="1">AndhraPradesh</option>
							<option value="36">Telangana</option>
                        </select>
                    </div>
			         <div class="col-md-3 col-xs-12 col-sm-3 m_top10 districtShowCls">
									<label>District<span style="color:red;">*<span></label>
									<select class="chosenSelect clearSelectClss" onchange="getConstituenciesForDistricts(this.value,this.id,'');" id=
									"notCadreDistId" name="addNotcadreRegistrationVO.districtId">
								</select>
						</div>
					<div class="col-md-3 col-xs-12 col-sm-3 m_top10 constituencyShowCls">
									<label>Constituency<span style="color:red;">*<span></label>
									<select class="chosenSelect clearSelectClss" onchange="getMandalCorporationsByConstituency('',this.id);" id="notCadreConstId"  name="addNotcadreRegistrationVO.constituencyId">
									</select>
						</div>
					<div class="col-md-3 col-xs-12 col-sm-3 m_top10 mandalShowCls">
									<label>Mandal/ Muncipality / Corporation<span style="color:red;">*<span></label>
									<select  class="chosenSelect clearSelectClss" onchange="getPanchayatWardByMandal('',this.id);" id="notCadreMandlId" name="addNotcadreRegistrationVO.mandalId">
									
									</select>
							</div>
					<div class="col-md-3 col-xs-12 col-sm-3 m_top10 panchayatShowCls">
									<label>Panchayat/ Ward / Division<span style="color:red;">*<span></label>
									<select  class="chosenSelect clearSelectClss" id="notCadrePanchayatId" name="addNotcadreRegistrationVO.panchayatId">
										
									</select>
								</div>
					
					
					
		<div class="col-md-3 col-xs-12 col-sm-3 m_top10">
				<label class="text-capitalize">pincode</label>
				<input class="form-control clearClss" id="pincodeId" type="text" maxlength="6" name="addNotcadreRegistrationVO.pincode"/>
			</div>
		</div>
      </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="addCandidateBtnId" style="display:none;">Add Candidate</button>
		<div id="notCadreSavId"></div>
      </div>
    </div>
	</form>
  </div>
</div>
</div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog">
  <div class="modal-dialog" style="width:90%" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Search Members</h4>
      </div>
      <div class="modal-body">
        <div class="row">
					<div class="col-md-12">
					<div style="background:#fff">
					<div class="col-md-3 advanceSearchCls">
						<label>Search Type</label>
						<select class="dropkickClass"  id="advanceSearchTypeId" onchange="showHideBySearchType();buildLevels();">
							<option value="0">Select Search Type</option>
							<option value="1">Name</option>
							<option value="mobileno">Mobile No</option>
							<option value="mebershipno">Membership No</option>
							<option value="votercardno">Voter Id Card No</option>
							<option value="2">Public Representative</option>
							<option value="3">Party Committee</option>
						</select>
					</div>
					
					
					 <div class="col-md-3 pad_0 advanceSearchCls advanceprclsDiv">
						<label class="advanceNameCls">Search By Name/Membership No*<span class="text-danger">*</span></label>
						<input type="text" class="form-control advanceNameCls clearCls" id="advanceSearchValueId">
						
					</div>
					<div class="col-md-3">
						<label class="advancePRCls">Search Designation</label>
						 <select class="advancePRCls dropkickClass"  id="advanceDesignationId" onchange="getLevelByDesignation();">
							<option value="0">Select Designation</option>
						</select>
						<span id="advanceErrDigitsId" class="full-right" style="color:red;"></span>
					</div>
					
						
						<div class="col-md-3 levelShowCls" >
							<label>Level</label>
							<select class="dropkickClass" id="alertlevelId" attr-index="0" onchange="disableByLevel('');" >
							<option value="2">State</option>
							 <option value="3">District</option>
							 <option value="4">Constituency</option>
							 <option value="5">Mandal/Muncipality</option>
							 <option value="6">Village/Ward</option>
							</select>
						</div>
						<div class="col-md-3 stateShowCls" >
							<label>State</label>
							 <select class="dropkickClass" id="stateId" onChange="getDistrictsForReferPopup('');">
								 <option value="0">Select State</option>
								 <option value="1">AP</option>
								 <option value="36">TS</option>
							 </select>
						</div>
				   
						<div class="col-md-3 locationsFilterCls distCls">
							 <label>District</label>
							 <select class="dropkickClass" id="referdistrictId" onChange="getConstituenciesBydistrictForReferPopup('');" >
							 <option value="0">Select District</option></select>
						</div>
						<div class="col-md-3 locationsFilterCls constiCls">
							<label>Assembly</label>
							<select class="dropkickClass" id="referconstituencyId" onChange="getMandalsByConstituencyForReferPopup('');" >
							<option value="0">Select Assembly</option>
							</select>
						</div>
						<div class="col-md-3 locationsFilterCls mandalCls">
							<label>Mandal/ Municipality</label>
							 <select class="dropkickClass" id="refermandalNameId" onChange="getPanchayatsForReferPopup();" >
								<option value="0">Select Mandal/ Municipality</option>
							 </select>
						</div>
						<div class="col-md-3 locationsFilterCls panchayatCls">
							<label>Panchayat/Ward</label>
							<select class="dropkickClass" id="referpanchayatId" >
							<option value="0">Select Panchayat/Ward</option>
							</select>
						</div>
						
						
						
						<div>
									<div class="advanceCadreCommittee" id="referCommitteeDiv">
									 <div class="col-md-3">
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
									<div >
										<div class="col-md-6">
											<select id="cadreCommitteeDiv" multiple class="advanceCadreCommittee" style="width:250px !important;"></select>
											<div id="representativesDiv"></div>
											<div id="referRoleErrorDiv"></div>
										</div>
									</div>
								</div></br>
								
								
				


											<p id="errorDivId" style="color:red;clear:both;margin-left:5px;"></p>
						<div class="col-md-2">
							<button type="button" class="btn btn-block btn-success m_top20 advancedSearchBtn btnNewCustom1" onclick="handleBySearchType();" id="searchBtnId"  style="margin-top: 25px;display:none;">Search Member</button>
						</div>
						<div class="col-md-1">
							<img src="images/search.gif" style="display:none;" id="ajaxImgForAppintId"/>
						</div>
							<div style="margin-top: 50px;"><img id="searchMemberAjax" src="images/icons/loading.gif" style="display:none;"/></div>
						
						<div class="row m_top25">
						<div class="col-md-12" id="clearSerchDivId">
							<div id="apptmemberDetailsDiv"></div>
						</div>
					</div>
						</div>
						</div>
						</div>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script>
var globalCadreId=0;
var globalNPCandiId=0;
</script>
<script type="text/javascript" src="js/nominatedPosts/nominatedPost.js"></script>
<script type="text/javascript" src="js/nominatedPosts/nominatedPost1.js"></script>
<script src="dist/activityDashboard/Date/moment.js" type="text/javascript"></script>
<script src="dist/activityDashboard/Date/daterangepicker.js" type="text/javascript"></script>
<script src="dist/activity/Timepicker/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(function() {
    $("#DOBId").daterangepicker({
        singleDatePicker: true,
        showDropdowns: true,
		parentEl:'#addMemberModalBlock'
    }, 
    function(start, end, label) {
        var years = moment().diff(start, 'years');
        alert("You are " + years + " years old.");
    });
});
$("#DOBId").val(moment().format('MM/DD/YYYY'));
$("#DOBId").val(" ");
/*
$(document).keypress(function(e) {
				if(e.keyCode==13){
					  $("#cadreSearchDtls").html("");
					 notCadresearch();
					
				}
		  });
		  */
$(document).on("click",".addMemberModal",function(){
	 $(".addNewCandidateErrorCls").html(" ");
	$("#addMemberModalBlock").modal('show');
})
$('.chosenSelect').chosen({width:'100%'});
$(document).on("click",".btnClassChange",function(){
	$(this).parent().find(".btnActive").removeClass("btnActive");
	$(this).addClass("btnActive");
});
/* $(".referenceModal").click(function(){
	commontdpCadreIds = [];
	clearAssignFields();
	$("#myModal").modal('show')
	
}); */
$(".dropkickClass").dropkick();
$("#membersDetailsTable").dataTable();
$(document).on("change","#addStateId",function(){
  getDistrictsForReferPopup();
});
$(document).on("change","#addDistrictId",function(){
  getConstituenciesForDistrict();
});
$(document).on("change","#addConstituencyId",function(){
  //getMandalsByConstituencyForReferPopup();
  subLevelForConstituency(4);
});
$(document).on("change","#addMandalsId",function(){
	subLevelForConstituency(5);
 // getPanchayatsForMandal(5);
});
//$(".changHeading").html("SELECT REFER DETAILS<button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>")
/*
$(document).ready(function(){
	$(".hideDivCls").hide();
});
*/
function clearAssignValues() 
{
	$(".clearClss").val('');
	$('.clearSelectClss').val(0).trigger('chosen:updated');	
	$("#voterExtraErrId").html('');
	if(saveFlag==true)
	{
	window.location.reload();
	cadreReloadFun();
	}
	
}
$(document).on("click",".referenceModal",function(){
	$("#myModal").modal('show')
	commontdpCadreIds = [];;
	 $("#apptmemberDetailsDiv").html("");
	 $("#advanceSearchTypeId").val(0);
	var select = new Dropkick("#advanceSearchTypeId");
				select.refresh();	
		showHideBySearchType();	
					disableByLevel(1);
})
function buildapptmemberDetails(result){
		var str='';
		if(result !=null && result.length>0){
			str+='<table id="searchedMembersId">';
			str+='<thead><th></th><th></th><th></th></thead>';
			str+='<tbody>';
			var xindex =0;
			for(var i in result){
				if( xindex == 0)
			{
				str+='<tr>';
			}
				
				
				str+='<td style="padding:0px !important;">';
					str+='<div class="col-md-12">';
					str+='<ul class="createAppointmentSearch">';
						str+='<li>';
							str+='<div class="row">';
								
								str+='<div class="col-md-12">';
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
											str+='<div style="color:#34A7C1;"><span >'+result[i].name+'</span>   -   <span>'+result[i].constituency+' Constituency</span></div>';
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
								str+='<div class="btn btn-success btn-sm col-md-4 col-md-offset-4 m_top10" style="border-radius:20px;"><label style="margin-bottom: 0px; line-height: 10px;"><input style="margin-left: 0px; margin-top: 0px;" type="checkbox" data-toggle="tooltip" data-placement="top" class="apptDetailsDiv candidatecls close'+result[i].id+'"  attr_designation = "'+result[i].designation+'" attr_candidateType="'+result[i].candidateType+'" attr_name="'+result[i].name+'" attr_mobile="'+result[i].mobileNo+'" attr_desg="'+result[i].designationId+'" attr_memberShipNo="'+result[i].memberShipId+'" attr_voterCardNo="'+result[i].voterCardNo+'" attr_id="'+result[i].id+'" attr_close_id="uncheck'+result[i].id+'" attr_img_url="'+result[i].imageURL+'" attr_candidateType_id='+result[i].candidateTypeId+' title="Select Candidate" attr-consti="'+result[i].constituency+'"> &nbsp;SELECT</label></div>';	
							  
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
			str+='</tbody>';
			str+='</table>';
		}
		
		$("#apptmemberDetailsDiv").html(str);
		$('[data-toggle="tooltip"]').tooltip()
		$('.check').tooltip()
		
		applyPagination();
	}
	function cadreReloadFun()
	{
		$("#cadreSearchId").prop('checked', true);
	}
</script>
</body>
</html>
