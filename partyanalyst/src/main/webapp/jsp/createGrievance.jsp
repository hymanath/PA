<%@ page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title> GOVT GRIEVANCE </title>
	<!-- Bootstrap -->
	<!--<link href="dist/2016DashBoard/css/bootstrap.css" rel="stylesheet" type="text/css">-->
	<link href="alertDepartment/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="alertDepartment/css/custom.css" rel="stylesheet" type="text/css">
	<!--<link href="dist/Alert/custom.css" rel="stylesheet" type="text/css">-->
	<!-- JQuery files (Start) -->
	
	<!--<script src="dist/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="dist/js/bootstrap.js"></script>-->
	<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
	<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
	<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
	<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
	<link href="dist/DateRange/daterangepicker.css" type="text/css" rel="stylesheet"/>
		<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
	
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
	
	<style>
	label{
		font-weight : 400;
	}
	.block{
		padding:10px;
		background-color:#fff;
	}
	h4{
		font-weight : 600;
	}
	.m_top25{
		margin-top : 25px;
	}
	</style>
	</head>  	
<body>
<div class="container">
	<div class="row">
    	<div class="col-md-12 col-xs-12 col-sm-12">
        	<div class="panel panel-default m_top10">
            	<div class="panel-heading bg_cc"   style="height:50px;" >
                	<h4 class="panel-title" >GOVERNMENT GRIEVANCE 
						<button class="btn btn-success pull-right" onclick="showDashboard();"> Dashboard </button>
						<button class="btn btn-primary  pull-right" style="margin-right:5px"  onclick="showNewGrievance();" > + New Grievance </button> 
					</h4>
					
                </div>
				<div class="col-md-4 col-xs-12 col-sm-6 pull-right" id="dateDivId" style="display:none;margin-bottom:10px">
				<span class="input-group pull-right dateRangePickerClsForEvents m_top10" expand-block-date="events" style="width:200px;">
						<input type="text" id="dateRangeIdForEvents" style="width:180px" class="form-control" onChange="getGrievanceSummary()"/>
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-calendar"></i>
						</span>
					</span>
				</div>
				<div class="" id="newGrevanceDivId">
					<form id="saveGrievanceAlertForm" name="saveGrievanceAlertForm" enctype="multipart/form-data" method="POST">
						<div class="panel-body bg_EF">
							<div class="row">
								<div class="col-sm-3 m_top10" >
									<label>Complaint Given By<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgCallerTypeId"></span></label>
									<select class="chosen" id="callerTypeId" disabled>
										<option value="1">CITIZEN</option>
									</select>
								</div>
								<div class="col-sm-3 m_top10">
									<label>Grievance Related To<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgIssueTypeId"></span></label>
									<select class="chosen" id="issueTypeId" disabled>
										<option value="1">Drinking Water</option>
									</select>
								</div>
								<div class="col-sm-3 m_top10">
									<label>Problem<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgSubTypeId"></span></label>
									<select class="chosen" id="issueSubTypeId" name="grievanceAlertVO.alertIssueSubTypeId">
										<option value="0">Select Problem</option>
									</select>
								</div>
								<!-- <div class="col-sm-3 m_top10">
									<label>Grievance Entry Category<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgEntrySourceId"></span></label>
									<select class="chosen" id="entrySourceId" disabled>
										<option value="1">Toll Free Number</option>
									</select>
								</div> -->
								<div class="col-sm-3 m_top10">
									<label>Department<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgDeptId"></span></label>
									<select class="chosenSelect" id="departmentsId" disabled>  
										<option value="49">RWS</option>
									</select>
								</div>
							</div>
							
							<div class="row">
								<div class="col-sm-12 m_top10">
									<h4 class="text-success text-capital">caller details</h4>
								</div>
								<div class="col-sm-3 m_top10">
									<label>Mobile No<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgMobileNoId"></span></label>
									<input id="mobileNoId" class="form-control" name="grievanceAlertVO.mobileNo" style="width: 230px" type="text"><span class="glyphicon glyphicon-search pull-right govtGrievanceCls" style="margin-top: -34px;background-color: lightgrey;padding:10px;margin-right: -10px;: 2px;cursor:pointer;" attr_id="mobile" title=" Click here to get existing Grievance requests with this mobile no"></span>
								</div>
								<div class="col-sm-3 m_top10">
									<label>Name<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgNameId"></span></label>
									<input type="text" id="nameId" class="form-control" name="grievanceAlertVO.name"/>
								</div>
								<div class="col-sm-3 m_top10">
									<label>Email ID<!--<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgEmailId"></span>--></label>
									<input type="text" id="emailId" class="form-control" placeholder="abc@xyz.com" name="grievanceAlertVO.emailId"/>
								</div>
								<div class="col-sm-3 m_top10">
									<label>Address<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgAddressId"></span></label>
									<input type="text" id="addressId" class="form-control" name="grievanceAlertVO.address"/>
								</div>
							</div>
							
							<div class="row">
								<div class="col-sm-12">
									<h4 class="text-success text-capital m_top10">location details</h4>
								</div>
								<div class="col-sm-3 m_top10">
									<label>Grievance Location Level<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgLevelId"></span></label>
									<select class="chosen" id="alertlevelId" onchange="disableByGrievLevel();" disabled>
										<!--<option value="0">Select Location Level</option>
										<option value="1">Central</option>
										<option value="2">State</option>
										<option value="3">District</option>
										<option value="4">Constituency</option>
										<option value="5">Mandal/Muncipality</option>
										<option value="6">Village/Ward</option>-->
										<option value="7" selected>Habitation</option>
									</select>
								</div>
								<div class="col-sm-3 m_top10" id="stateDiv">
									<label>State<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgStateId"></span></label>
									<select class="chosen" id="stateId" onChange="getDistrictsForReferPopup();" name="grievanceAlertVO.stateId" disabled>
										<!--<option value="0">Select State</option>-->
										<option value="1" selected>Andhra Pradesh</option>
									</select>
								</div>
								<div class="col-sm-3 m_top10" id="districtDiv" style="display:block;">
									<label>District<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgDistrictId"></span></label>
									<select class="chosen" id="referdistrictId" onChange="getMandalsByConstituencyForReferPopup();" name="grievanceAlertVO.districtId">
										<option value="0">Select District</option>
									</select>
								</div>
								<!--<div class="col-sm-3 m_top10" id="constituencyDiv" style="display:block;">
									<label>Constituency<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgConstId"></span></label>
									<select class="chosen" id="referconstituencyId" onChange="getMandalsByConstituencyForReferPopup();" name="grievanceAlertVO.constituencyId">
										<option value="0">Select Constituency</option>
									</select>
								</div>-->
								<div class="col-sm-3 m_top10" id="mandalDiv" style="display:block;">
									<label>Mandal<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgMandalId"></span></label>
									<select class="chosen" id="refermandalNameId" onChange="getPanchayatsForReferPopup();" name="grievanceAlertVO.mandalId">
										<option value="0">Select Mandal</option>
									</select>
								</div>
								<div class="col-sm-3 m_top10" id="panchayatDiv" style="display:block;">
									<label>Village/Panchayat<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgVillageId"></span></label>
									<select class="chosen" id="referpanchayatId" onchange="getHamletss();" name="grievanceAlertVO.panchayatId">
										<option value="0">Select Village/Panchayat</option>
									</select>
								</div>
								<div class="col-sm-3 m_top10" id="villageDiv" style="display:block;">
									<label>Habitation<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgHamletId"></span></label>
									<select class="chosen" id="hamletId" name="grievanceAlertVO.hamletId">
										<option value="0">Select Habitation</option>
									</select>
								</div>
								<div class="col-md-2 col-xs-12 col-sm-6">
                                 <input type="button" class="btn btn-success govtGrievanceCls" value="SEARCH" id="" onclick="" style="margin-top: 35px;"  attr_id="location"></input>
                                 </div>
							</div>
							
							<div class="row">
								<div class="col-sm-12 m_top10">
									<h4 class="text-success text-capital">grievance details</h4>
								</div>
							</div>
							
							<div class="row">
								<div class="col-sm-8 m_top10">
									<label>Grievance Title<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgTitleId"></span></label>
									<input type="text" class="form-control" id="alertTitleId" name="grievanceAlertVO.alertTitle"/>
								</div>
								<!--<div class="col-sm-4 m_top10">
									<label>Complaint No</label>
									<input type="text" class="form-control" id="complaintNo" name="grievanceAlertVO.complaintNo"/>
								</div>-->
								<div class="col-sm-12 m_top10">
									<label>Grievance Description : <span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgDescId"></span></label>
									<label class="radio-inline">
										<input type="radio" value="te" name="language" class="lang" id="telugu" checked  onclick="languageChangeHandler();"/>Telugu
									</label>
									<label class="radio-inline">
										<input type="radio" value="en" name="language" class="lang" id="eng" onclick="languageChangeHandler();"/>English
									</label>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12 m_top10">
									<textarea class="form-control" id="alertdescriptionId" name="grievanceAlertVO.description"></textarea>
								</div>
							</div>
							
							<div class="row">
								<div class="col-sm-12 m_top10">
									<h4 class="text-success text-capital">assign alert to department officer</h4>
								</div>
								  
								<div class="col-sm-3 m_top10">
									<label>Impact Level<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgLvlId"></span></label>
									<select  class="chosenSelect" id="locationLevelSelectId" disabled>  
										<option value="13">MANDAL</option>
									</select>
								</div>
								<div id="parentLevelDivId"> </div>

								<div class="col-sm-3 m_top10">
									<label>Designation<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgDesgId"></span></label>
									<select name="grievanceAlertVO.designationId" id="designationsId" class="chosenSelect">
										<option></option>  
									</select>
								</div>
								<div class="col-sm-3 m_top10">
									<label>Officer Name<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgOffcrId"></span></label>
									<select name="grievanceAlertVO.govtOfficerId" id="officerNamesId" class="chosenSelect">
										<option></option>
									</select>
								</div>
							</div>
							
							<div class="row">
								<div class="col-sm-12 m_top10">
									<h4 class="text-success text-capital">upload document linking to this alert</h4>
									<div class="block  m_top10">
										<input type="file" id="uploadFileId0" name="imageForDisplay"/>
										<button type="button" class="close closeFileCls"  style="margin-top:-26px;margin-right:700px" title="Click here to remove document" >x</button>
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-sm-4 m_top25">
									<input type="button" class="btn btn-success btn-block text-capital" value="create grievance request" onclick="createGrievanceAlert();" id="createAlertId"></input>
								</div>
								<div class="col-sm-4">
									<img style="display: none;" alt="Processing Image" src="./images/icons/search.gif" id="creatingLdngImg"/>
								</div>
								<div class="col-sm-4">
									<span id="successmsg"></span>
								</div>
							</div>
						</div>
						<input type="hidden" class="form-control" id="locationLevelValhidden" name="grievanceAlertVO.locationValue"/>
						<input type="hidden" class="form-control" id="locationLevelIdhidden" name="grievanceAlertVO.locationLevelId"/>
						
						<input type="hidden" class="form-control" value="1" name="grievanceAlertVO.callerTypeId"/>
						<input type="hidden" class="form-control" value="1" name="grievanceAlertVO.issueTypeId"/>
						<input type="hidden" class="form-control" value="1" name="grievanceAlertVO.entrySourceId"/>
						<input type="hidden" class="form-control" value="49" name="grievanceAlertVO.departmentId"/>
						<input type="hidden" class="form-control" value="13" name="grievanceAlertVO.levelId"/>
						<input type="hidden" class="form-control" value="1" name="grievanceAlertVO.stateId"/>
					</form>
				</div>
				<div id="dashboardGrevanceDivId" style="display:none;">
					<div id="summaryDiv"></div>
					<div id="alertDataDivId"></div>
				<div>
            </div>
        </div>
    </div>
</div>

<!-- PopUp -->
<!--<div class="modal fade" id="updateAlertModalDivId" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-lg" role="document"  id="slick-modal" style="width:90%">
    <div class="modal-content customModal">
      <div class="modal-header">
        <button type="button" class="close " data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Feedback Status Updation</h4>
      </div>
      <div class="modal-body" style="padding:0px 15px 0px 0px;">
       <div id="buildUpdateDivId"></div>
	    <div class="row">
		   <div class="col-sm-4">
			   <label>Comment</label><br>
			   <textarea id="comntId"  ></textarea>
		   </div>
		</div>
		<div class="row">
		    <div class="col-sm-3">
			   <label>FeedBack Status :</label><br>
			  <select id="feedbackStatusList" class="form-control">
				<option value="0"> Select Feedback Status</option>
			  </select>
		   </div>
		</div>
		<div class="row">
		   <div class="col-sm-4">
				<button type="button" class="btn btn-success" onClick="saveAlertStatusDetails();" style="margin-top:22px;">Save</button>
				<span id="saveMsgId"></span>
		   </div>
	   </div>
      </div>
    </div>
  </div>
</div>-->


<div class="modal fade" id="updateAlertModalDivId" tabindex="-1" role="dialog">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content customModal">
      <div class="modal-header">
       <button type="button" class="close " data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Feedback Status Updation</h4>
      </div>
      <div class="panel-body" style="padding:10px;background-color: lightgrey;">
		<div id="buildUpdateDivId">
		</div>
		
		<div class="col-md-12 col-xs-12 col-sm-12  m_top10">
			  <label>FeedBack Status :</label><br>
			  <select id="feedbackStatusList" class="form-control">
				<option value="0"> Select Feedback Status</option>
			  </select>
		</div>
		<div class="col-md-12 col-xs-12 col-sm-12">			
				<span id="saveMsgId"></span>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" onClick="saveAlertStatusDetails();" >Save</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!--srujana-->
 <div class="modal fade" id="govtGrievanceAlertModalId" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document" style="width:80%">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="mdlHeadingId" style="text-transform: uppercase;">Search</h4>
      </div>
      <div class="modal-body">
	  <div class ="row">
		   <div class="col-md-3 col-sm-4 col-xs-12 govtGrievanceView" >
		   <label>Search By:</label>
                    <input  type="radio" name="checkBoxName"  style="" value="1" class="radioBtnCls" id="radioBtnMobileId" checked="true"/><span> Mobile No </span>
                    <input  type="radio" name="checkBoxName"  style="" value="2" class="radioBtnCls" id="radioBtnClsId" /><span>Location</span>  
                </div>
		</div>
	   <div class="row locationSearchCls" style="display:none;">
	   <div class="col-sm-3 m_top10" id="districtIdDiv" style="display:block;">
		<label>District<span style="color:red"></span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgDistrictId"></span></label>
		<select class="chosen" id="modalDistrictId" onChange="getMandalsByConstituencyForReferPopup1();" name="grievanceAlertVO.districtId">
			<option value="0">ALL</option>
		</select>
	  </div>						
	 <div class="col-sm-3 m_top10" id="mandalDiv" style="display:block;">
				<label>Mandal<span style="color:red"></span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgMandalId"></span></label>
				<select class="chosen" id="modalMandalNameId" onChange="getPanchayatsForReferPopup1();" name="grievanceAlertVO.mandalId">
					<option value="0">ALL</option>
				</select>
		</div>
		<div class="col-sm-3 m_top10" id="panchayatDiv" style="display:block;">
			<label>Village<span style="color:red"></span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgVillageId"></span></label>
			<select class="chosen" id="modalPanchayatId" onchange="getHamletss1();" name="grievanceAlertVO.panchayatId">
				<option value="0">ALL</option>
			</select>
		</div>
		<div class="col-sm-3 m_top10" id="villageDiv" style="display:block;">
				<label>Habitation<span style="color:red"></span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgHamletId"></span></label>
				<select class="chosen" id="modalhamletId" name="grievanceAlertVO.hamletId">
					<option value="0">ALL</option>
				</select>
		 </div>
		</div>
		<div class="row mobileSearchCls"  style="display:none;">
		 <div class="col-sm-9 m_top10">
		    <input type="text" class="form-control" id="searchBy"/><span class="glyphicon glyphicon-search pull-right" style="margin-top: -34px;background-color: lightgrey;padding:10px;margin-right: -10px;: 2px"></span>
			<div id="errorMassgeId" style="color:red"></div>
			</div>
		 <div class="col-sm-3 pad_left0">
                 <!--<button class="btn btn-success btn-block btnSearch" id="searchbtn">SEARCH</button>-->
           </div>
		 </div>
		<div class="row">
		<div class="col-md-2 col-xs-12 col-sm-6">
             <!--<button class="btn btn-success"  id="" onclick="getGovtGrievanceAlertDetails();" style="margin-top: 19px;">SEARCH</button>-->
			 <input type="button" class="btn btn-success" value="SEARCH" id="" onclick="getGovtGrievanceAlertDetails();" style="margin-top: 19px;"></input>
           </div>
		 </div>
        <div id="commentsBlock" class="m_top10"></div>		
        <!--<div id="commentsDivId"></div>-->
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>
<!-- Hidden Variables -->
<input type="hidden" id="hiddenAlertId"></input>
<input type="hidden" id="hiddenSourceId"></input>
<input type="hidden" id="hiddenStatusId"></input>


<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script src="dist/DateRange/moment.js" type="text/javascript"></script>
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
 <script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
  <script type="text/javascript" src="js/jquery.dataTables.js"></script>
<script>

$(".chosen").chosen({
	width : '100%'
});

// Load the Google Transliterate API
google.load("elements", "1", {
	packages: "transliteration"
});

//getAlertIssueTypes();
//getAlertCallerTypes();
$("#dateRangeIdForEvents").daterangepicker({
	opens: 'left',
	startDate: moment().subtract('month').startOf('month'),
	endDate: moment().subtract('month').endOf('month'),
	locale: {
	  format: 'DD/MM/YYYY'
	},
	ranges: {
		'This Month': [moment().startOf('month'), moment()],
	   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
	   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
	   'Last 3 Months': [moment().subtract(3, 'month'), moment()],
	   'Last 6 Months': [moment().subtract(6, 'month'), moment()],
	   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
	   
	   'This Year': [moment().startOf('Year'), moment()]
	}
});
function getAlertType(){
	$("#alertTypeId").html('');
	var jsObj ={
		task:""
	}
	$.ajax({
		type:'GET',
		url: 'getAlertTypeAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		$('#alertTypeId').append('<option value="0"> Select Alert Type </option>');
		if(result != null){
			for(var i in result){			
				$('#alertTypeId').append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
		}
		$("#alertTypeId").trigger('chosen:updated');	  
	});
}

function getAlertImpactScope(){
	$("#alertImpactId").html('');
	var jsObj ={
		task:""
	}
	$.ajax({
		type:'GET',
		url: 'getAlertImpactScopeAction.action',
		data: {task :JSON.stringify(jsObj)}
   }).done(function(result){
		$('#alertImpactId').append('<option value="0"> Select Alert Impact </option>');
		if(result != null){
			for(var i in result){			
				$('#alertImpactId').append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
		}
		$("#alertImpactId").trigger('chosen:updated');
	});
}

function getAlertsource(){
	$("#alertSourceId").html('');
	var jsObj ={
		userId : loginUserId,
		task:""
	}
	$.ajax({
		type:'GET',
		url: 'getAlertSourceForUserAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		$('#alertSourceId').append('<option value="0"> Select Alert Source </option>');
		if(result != null){
			for(var i in result){			
				$('#alertSourceId').append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
		}
		$("#alertSourceId").trigger('chosen:updated');	  
	});
}


function getDistrictsForReferPopup() {
	var stateId = $("#stateId").val();
	
	var jobj = {
		stateId : stateId  
	}
	$.ajax({
		type : 'GET',
		url : 'getDistrictsForStateAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jobj)} 
	}).done(function(result){
		var str='';
		
			str+='<option value="0">Select District</option>';
		if(result != null && result.length > 0){    
			for(var i in result){
				if(result[i].id > 0 && result[i].id != 517)
				str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
			}
			$("#referdistrictId").html(str);
			$("#referdistrictId").trigger('chosen:updated');
			//for const
			//$("#referconstituencyId").html('<option value="0">Select Assembly</option>');
			//$("#referconstituencyId").trigger('chosen:updated');
			//for mandal/municipality
			$("#refermandalNameId").html('<option value="0">Select Mandal</option>');
			$("#refermandalNameId").trigger('chosen:updated');
			//for panchayat
			$("#referpanchayatId").html('<option value="0">Select Village/Panchayat</option>');
			$("#referpanchayatId").trigger('chosen:updated');
			$("#hamletId").html('<option value="0">Select Habitation</option>');
			$("#hamletId").trigger('chosen:updated');
			$("#modalDistrictId").html(str);
			$("#modalDistrictId").trigger('chosen:updated');
			$("#modalMandalNameId").html('<option value="0">Select Mandal</option>');
			$("#modalMandalNameId").trigger('chosen:updated');
			$("#modalPanchayatId").html('<option value="0">Select Village</option>');
			$("#modalPanchayatId").trigger('chosen:updated');
			$("#hamletId").html('<option value="0">Select Habitation</option>');
			$("#hamletId").trigger('chosen:updated');
			
			 
		}else{
			//for dist
			$("#referdistrictId").html('<option value="0">Select District</option>');
			$("#referdistrictId").trigger('chosen:updated');   //srujana modalDistrictId  modalMandalNameId modalPanchayatId
			//for const
			//$("#referconstituencyId").html('<option value="0">Select Assembly</option>');
			//$("#referconstituencyId").trigger('chosen:updated');
			//for mandal/municipality
			$("#refermandalNameId").html('<option value="0">Select Mandal</option>');
			$("#refermandalNameId").trigger('chosen:updated');
			//for panchayat
			$("#referpanchayatId").html('<option value="0">Select Village/Panchayat</option>');
			$("#referpanchayatId").trigger('chosen:updated');  
			$("#hamletId").html('<option value="0">Select Habitation</option>');
			$("#hamletId").trigger('chosen:updated');
			$("#modalDistrictId").html('<option value="0">Select District</option>');
			$("#modalDistrictId").trigger('chosen:updated');
			$("#modalMandalNameId").html('<option value="0">Select Mandal</option>');
			$("#modalMandalNameId").trigger('chosen:updated');
			$("#modalPanchayatId").html('<option value="0">Select Village</option>');
			$("#modalPanchayatId").trigger('chosen:updated'); 
		} 
	});  
  }
 /*function getConstituenciesBydistrictForReferPopup(){
	 var districtId = $("#referdistrictId").val();
	 $("#designationId").empty();
	 $("#designationId").trigger('chosen:updated');
	 $("#officerId").empty();
	 $("#officerId").trigger('chosen:updated');
	 $("#villageDiv").hide();
	var jobj = {
		districtId : districtId
	}
		$.ajax({
			type : "POST",
			url  : "getConstituenciesByDistrictAction.action",
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			var constiStr='';
			if(result != null && result.length > 0){
				constiStr +='<option value="0">Select Assembly</option>';
				for(var i in result){
					if(result[i].id > 0)
					constiStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
				}
				$("#referconstituencyId").html(constiStr);
				$("#referconstituencyId").trigger('chosen:updated');
				//for mandal/municipality
				$("#refermandalNameId").html('<option value="0">Select Mandal/Muncipality</option>');
				$("#refermandalNameId").trigger('chosen:updated');
				//for panchayat
				$("#referpanchayatId").html('<option value="0">Select Panchayat</option>');
				$("#referpanchayatId").trigger('chosen:updated');           
			}else{
				//for const
				$("#referconstituencyId").html('<option value="0">Select Assembly</option>');
				$("#referconstituencyId").trigger('chosen:updated');
				//for mandal/municipality
				$("#refermandalNameId").html('<option value="0">Select Mandal/Muncipality</option>');
				$("#refermandalNameId").trigger('chosen:updated');
				//for panchayat
				$("#referpanchayatId").html('<option value="0">Select Panchayat</option>');
				$("#referpanchayatId").trigger('chosen:updated'); 
			}
		});
 }*/

 function getMandalsByConstituencyForReferPopup(){
	 var districtId = $('#referdistrictId').val();
	 
	 var jobj = {
		districtId : districtId
	}
		$.ajax({
			type : "POST",
			url  : "getAllMandalsByDistrictIDAction.action",
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			var mandalStr='';
			if(result != null && result.length > 0){
				
			    mandalStr +='<option value="0">Select Mandal</option>';
				for(var i in result){
					if(result[i].id > 0)
					mandalStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
				}
				$("#refermandalNameId").html(mandalStr);
				$("#refermandalNameId").trigger('chosen:updated');                
				//for panchayat
				$("#referpanchayatId").html('<option value="0">Select Village/Panchayat</option>');
				$("#referpanchayatId").trigger('chosen:updated'); 
				$("#hamletId").html('<option value="0">Select Habitation</option>');
				$("#hamletId").trigger('chosen:updated');
				$("#designationsId").html('<option value="0">Select Designation</option>');//empty();
				$("#designationsId").trigger("chosen:updated");
			}else{
				//for mandal/municipality
				$("#refermandalNameId").html('<option value="0">Select Mandal</option>');
				$("#refermandalNameId").trigger('chosen:updated');
				//for panchayat
				$("#referpanchayatId").html('<option value="0">Select Village/Panchayat</option>');
				$("#referpanchayatId").trigger('chosen:updated');
				$("#hamletId").html('<option value="0">Select Habitation</option>');
				$("#hamletId").trigger('chosen:updated');				
			}
		});
 }
 
 function getPanchayatsForReferPopup(){
	 designationsByDepartment();
	
	 $("#referpanchayatId").find('option').not(':first').remove();
	 var mandalId = $('#refermandalNameId').val();
	 var  type = $("#refermandalNameId option:selected").text();
	 		   
	 if(type.indexOf("Mandal") == -1) 
		type = "muncipality" ;
	else
		type = "mandal" ; 
	  var jsObj={
				mandalId :mandalId,
				type:type,
				task:""
				
			}
	 $.ajax({
				type:"POST",
				url :"getPanchayatAndWardDetailsAction.action",
				 dataType: 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				var panchyatStr='';
				if(result!=null && result.length>0){
					panchyatStr +='<option value="0">Select Village/Panchayat</option>';
					for(var i in result){
						if(result[i].id > 0)
							panchyatStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
					}
					$("#referpanchayatId").html(panchyatStr);
					$("#referpanchayatId").trigger('chosen:updated');
					$("#hamletId").html('<option value="0">Select Habitation</option>');
					$("#hamletId").trigger('chosen:updated');
				}else{
					//for panchayat
					$("#referpanchayatId").html('<option value="0">Select Village/Panchayat</option>');
					$("#referpanchayatId").trigger('chosen:updated');
					$("#hamletId").html('<option value="0">Select Habitation</option>');
					$("#hamletId").trigger('chosen:updated');					
				}     
		});
}

function getHamletss(){
	
	 $("#hamletId").find('option').not(':first').remove();
	 var panchayatId = $('#referpanchayatId').val();
	 var  type = $("#refermandalNameId option:selected").text();
			   
	 if(type.indexOf("Mandal") == -1) 
		type = "muncipality" ;
	else
		type = "mandal" ; 
	
	if(type == "mandal"){
		
		var jsObj={
				panchayatId :panchayatId,
				task:""
			}
	 $.ajax({
				type:"POST",
				url :"getHamletsForPanchayatAction.action",
				 dataType: 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				var panchyatStr='';
				if(result!=null && result.length>0){
					panchyatStr +='<option value="0">Select Habitation</option>';
					for(var i in result){
						if(result[i].id > 0)
							panchyatStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
					}
					$("#hamletId").html(panchyatStr);
					$("#hamletId").trigger('chosen:updated');
				}else{
					//for panchayat
					$("#hamletId").html('<option value="0">Select Habitation</option>');
					$("#hamletId").trigger('chosen:updated'); 
				}     
		});
	}
}

function getAlertCallerTypes(){
	var jsObj={
		task:""
	}
	$.ajax({
		type:"POST",
		url :"getAlertCallerTypesAction.action",
		 dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		var str='';
		if(result!=null && result.length>0){
			str +='<option value="0">Select Caller Type</option>';
			for(var i in result){
				if(result[i].id > 0)
					str +='<option value='+result[i].id+'>'+result[i].name+'</option>';
			}
			$("#callerTypeId").html(str);
			$("#callerTypeId").trigger('chosen:updated');
		}
	});
}

function getAlertIssueTypes(){
	var jsObj={
		task:""
	}
	$.ajax({
		type:"POST",
		url :"getAlertIssueTypesAction.action",
		 dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		var str='';
		if(result!=null && result.length>0){
			str +='<option value="0">Select Issue Type</option>';
			for(var i in result){
				if(result[i].id > 0)
					str +='<option value='+result[i].id+'>'+result[i].name+'</option>';
			}
			$("#issueTypeId").html(str);
			$("#issueTypeId").trigger('chosen:updated');
		}
	});
}

function disableByGrievLevel(){
	var alertLevelId = $("#alertlevelId").val();
	if(alertLevelId == 2){
		$("#stateId").val(1).trigger('chosen:updated');
		$("#referdistrictId").empty().trigger('chosen:updated');
		$("#referconstituencyId").empty().trigger('chosen:updated');
		$("#refermandalNameId").empty().trigger('chosen:updated');
		$("#referpanchayatId").empty().trigger('chosen:updated');
		$("#hamletId").empty().trigger('chosen:updated');
		
		$("#stateDiv").show();
		$("#districtDiv").hide();
		$("#constituencyDiv").hide();
		$("#mandalDiv").hide();
		$("#panchayatDiv").hide();
		$("#villageDiv").hide();
	}
	else if(alertLevelId == 3){
		$("#stateId").val(1).trigger('chosen:updated');
		$("#referdistrictId").empty().trigger('chosen:updated');
		$("#referconstituencyId").empty().trigger('chosen:updated');
		$("#refermandalNameId").empty().trigger('chosen:updated');
		$("#referpanchayatId").empty().trigger('chosen:updated');
		$("#hamletId").empty().trigger('chosen:updated');
		
		$("#stateDiv").show();
		$("#districtDiv").show();
		$("#constituencyDiv").hide();
		$("#mandalDiv").hide();
		$("#panchayatDiv").hide();
		$("#villageDiv").hide();
	}
	else if(alertLevelId == 4){
		$("#stateId").val(1).trigger('chosen:updated');
		$("#referdistrictId").empty().trigger('chosen:updated');
		$("#referconstituencyId").empty().trigger('chosen:updated');
		$("#refermandalNameId").empty().trigger('chosen:updated');
		$("#referpanchayatId").empty().trigger('chosen:updated');
		$("#hamletId").empty().trigger('chosen:updated');
		
		$("#stateDiv").show();
		$("#districtDiv").show();
		$("#constituencyDiv").show();
		$("#mandalDiv").hide();
		$("#panchayatDiv").hide();
		$("#villageDiv").hide();
	}
	else if(alertLevelId == 5){
		$("#stateId").val(1).trigger('chosen:updated');
		$("#referdistrictId").empty().trigger('chosen:updated');
		$("#referconstituencyId").empty().trigger('chosen:updated');
		$("#refermandalNameId").empty().trigger('chosen:updated');
		$("#referpanchayatId").empty().trigger('chosen:updated');
		$("#hamletId").empty().trigger('chosen:updated');
		
		$("#stateDiv").show();
		$("#districtDiv").show();
		$("#constituencyDiv").show();
		$("#mandalDiv").show();
		$("#panchayatDiv").hide();
		$("#villageDiv").hide();
	}
	else if(alertLevelId == 6){
		$("#stateId").val(1).trigger('chosen:updated');
		$("#referdistrictId").empty().trigger('chosen:updated');
		$("#referconstituencyId").empty().trigger('chosen:updated');
		$("#refermandalNameId").empty().trigger('chosen:updated');
		$("#referpanchayatId").empty().trigger('chosen:updated');
		$("#hamletId").empty().trigger('chosen:updated');
		
		$("#stateDiv").show();
		$("#districtDiv").show();
		$("#constituencyDiv").show();
		$("#mandalDiv").show();
		$("#panchayatDiv").show();
		$("#villageDiv").hide();
	}
	else if(alertLevelId == 7){
		$("#stateId").val(1).trigger('chosen:updated');
		$("#referdistrictId").empty().trigger('chosen:updated');
		$("#referconstituencyId").empty().trigger('chosen:updated');
		$("#refermandalNameId").empty().trigger('chosen:updated');
		$("#referpanchayatId").empty().trigger('chosen:updated');
		$("#hamletId").empty().trigger('chosen:updated');
		
		$("#stateDiv").show();
		$("#districtDiv").show();
		$("#constituencyDiv").show();
		$("#mandalDiv").show();
		$("#panchayatDiv").show();
		$("#villageDiv").show();
	}
}

</script>
<script type="text/javascript">

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

function createGrievanceAlert()
{
	$(".errorMsgClas").html('');
	//var validate = true;
	
  var  level=$("#alertlevelId").val();
  var  state=$("#stateId").val();
  var  district=$("#referdistrictId").val();
  //var  assembly=$("#referconstituencyId").val();
  var  mandal=$("#refermandalNameId").val();
  var  panchayat=$("#referpanchayatId").val();
  var  village = $("#hamletId").val();
  
  var title=$("#alertTitleId").val().trim();
  var description=$("#alertdescriptionId").val().trim();
  
  var callerType = $("#callerTypeId").val();
  var issueType = $("#issueTypeId").val();
  var entrySource = $("#entrySourceId").val();
  var issueSubType = $("#issueSubTypeId").val();
  
  var name = $("#nameId").val().trim();
  var address = $("#addressId").val().trim();
  var mobileNo = $("#mobileNoId").val().trim();
  var email = $("#emailId").val().trim();
  
  var department = $("#departmentsId").val();
  var assignLevelId = $("#locationLevelSelectId").val();
  var levelValue = $(".locationCls").val();
  var designation = $("#designationsId").val();
  var officer = $("#officerNamesId").val();
  
  if(issueSubType == 0){
	  $("#errMsgSubTypeId").html("Select Issue Sub Type");
	  return;
  }
  if(mobileNo.length==0 ||mobileNo==''){
		$("#errMsgMobileNoId").html(" Please Enter MobileNo ");
		return;
	}
	if(mobileNo.length != 10){
		$("#errMsgMobileNoId").html(" Please Enter Valid MobileNO ");
		return;
	}
	if(mobileNo.length > 0){
		var numericExpression = /^[0-9]+$/;
		if(!mobileNo.match(numericExpression)){
			$('#errMsgMobileNoId').html('Enter Numerics Only.');
			return;
		}
	}
	if(name.length==0 ||name==''){
		$("#errMsgNameId").html(" Please Enter Name ");
		return;
	}
	if(address.length==0 ||address==''){
		$("#errMsgAddressId").html(" Please Enter Address ");
		return;
	}
	
  
  if(level==0)
  {
     $("#errMsgLevelId").html(" Please Select Level ");
  }
  
  if(level==2)
  {
    if(state==0)               
      {
      $("#errMsgStateId").html(" Please Select State ");
          return;
    }
	$("#locationLevelIdhidden").val(2);
	$("#locationLevelValhidden").val(state);
    
  }
  if(level==3)
  {
    if(state==0)
    {
      $("#errMsgStateId").html(" Please Select State ");
          return;
    }
    if(district==0)
    {
      $("#errMsgDistrictId").html(" Please Select District ");
          return;
    }
	$("#locationLevelIdhidden").val(3);
	$("#locationLevelValhidden").val(district);
  }
  
 if(level==4)
  {
    if(state==0)
      {
        $("#errMsgStateId").html(" Please Select State ");
            return;
      }
    if(district==0)
      {
        $("#errMsgDistrictId").html(" Please Select District ");
            return;
      }
    /*if(assembly==0)
    {
      $("#errMsgConstId").html(" Please select Assembly ");
          return;
    }
	$("#locationLevelIdhidden").val(4);
	$("#locationLevelValhidden").val(assembly);*/
  }
  if(level==5)
  {
	  var mandalName = $("#refermandalNameId1 option:selected").text();
    if(state==0)
      {
        $("#errMsgStateId").html(" Please Select State ");
            return;
      }
    if(district==0)
      {
        $("#errMsgDistrictId").html(" Please Select District ");
            return;
      }
    /*if(assembly==0)
     {
      $("#errMsgConstId").html(" Please select Assembly ");
          return;
     }*/
    
    if(mandal==0)
    {
      $("#errMsgMandalId").html(" Please Select Mandal/ Municipality ");
          return;
    }
	$("#locationLevelValhidden").val(mandal);
		if(mandalName.indexOf('Mandal') == -1)
		$("#locationLevelIdhidden").val(7);
	else
		$("#locationLevelIdhidden").val(5);
		
  }
  if(level==7)
  {
	if(state==0)
      {
        $("#errMsgStateId").html(" Please Select State ");
            return;
      }
    if(district==0)
      {
        $("#errMsgDistrictId").html(" Please Select District ");
            return;
      }
     /* if(assembly==0)
     {
      $("#errMsgConstId").html(" Please select Assembly ");
          return;
     }*/
    
      if(mandal==0)
     {
      $("#errMsgMandalId").html(" Please Select Mandal ");
          return;
     }
     if(panchayat==0)
     {
    $("#errMsgVillageId").html(" Please Select Village ");
        return;
     }
	 if(village==0)
     {
    $("#errMsgHamletId").html(" Please Select Habitation ");
        return;
     }
	 $("#locationLevelValhidden").val(village);
		 $("#locationLevelIdhidden").val(11);
    }
	if(level==6)
  {
	   var panchayatName = $("#referpanchayatId1 option:selected").text();
    if(state==0)
      {
        $("#errMsgStateId").html(" Please Select State ");
            return;
      }
    if(district==0)
      {
        $("#errMsgDistrictId").html(" Please Select District ");
            return;
      }
      /*if(assembly==0)
     {
      $("#errMsgConstId").html(" Please select Assembly ");
          return;
     }*/
    
      if(mandal==0)
     {
      $("#errMsgMandalId").html(" Please Select Mandal/ Municipality ");
          return;
     }
     if(panchayat==0)
     {
    $("#errMsgVillageId").html(" Please Select Panchayat ");
        return;
     }
	 $("#locationLevelValhidden").val(panchayat);
		 if(panchayatName.indexOf('WARD') == -1)
			$("#locationLevelIdhidden").val(6);
		else
			$("#locationLevelIdhidden").val(8);
    }
	
	 if(title.length==0 || title=='')
	  {
		$("#errMsgTitleId").html(" Please Enter Alert Title.  ");
			return;
	  }
  
	if(description.length==0 ||description==''){
		$("#errMsgDescId").html(" Please Enter Description ");
		return;
	}
	
	if(assignLevelId == 0){
		$("#errMsgLvlId").html(" Select Level ");
		return;
	}
	if(levelValue == 0){
		$("#errMsgLocationId").html(" Select Location ");
		return;
	}
	if(designation == 0){
		$("#errMsgDesgId").html(" Select Designation ");
		return;
	}
	if(officer == 0){
		$("#errMsgOffcrId").html(" Select Officer ");
		return;
	}
	
	if(callerType == 0){
		$("#errMsgCallerTypeId").html(" Select Caller Type ");
		return;
	}
	if(issueType == 0){
		$("#errMsgIssueTypeId").html(" Select Issue Type ");
		return;
	}
	if(entrySource == 0){
		$("#errMsgEntrySourceId").html(" Select Entry Category ");
		return;
	}
	
  if(department == 0){
		$("#errMsgDeptId").html(" Select Department ");
		return;
	}
	
	
	//if(validate){
		$("#creatingLdngImg").show();
		$("#createAlertId").hide();
		
		var uploadHandler = {
			upload: function(o) {
				uploadResult = o.responseText;
				$("#createAlertId").show();
				if(uploadResult.indexOf("success") !=-1)
				{
				 $("#successmsg").html("Alert Created And Assigned Successfully ").css("color","green");	
				 $("#creatingLdngImg").hide();
				 setTimeout(function(){ 
					$("#successmsg").html("");
					clearFields();
					location.reload();
				 }, 1000);
				}else{  
					$("#successmsg").html("Exception Occured..Try Again...").css("color","red");	
					$("#creatingLdngImg").hide();
					setTimeout(function(){ 
						$("#successmsg").html("");
					}, 1000);
				}  
				return false;
			}
		};
		
		YAHOO.util.Connect.setForm('saveGrievanceAlertForm',true);
		YAHOO.util.Connect.asyncRequest('POST','saveGrievanceAlertAction.action',uploadHandler);
	//}
	
}

function clearFields(){
	$("#alertlevelId").val(0).trigger('chosen:updated');
	$("#departmentsId").val(1).trigger('chosen:updated');
	$("#entrySourceId").val(0).trigger('chosen:updated');
	$("#telugu").prop("checked", true);
	
	$("#alertTitleId").val('');
	$("#alertdescriptionId").val('');
	$("#nameId").val('');
	$("#addressId").val('');
	$("#mobileNoId").val('');
	$("#emailId").val('');
	$("#uploadFileId0").val('');
}
getAlertIsuueSubTypes();
function getAlertIsuueSubTypes()
{
	$('#issueSubTypeId').empty();
	$('#issueSubTypeId').trigger('chosen:updated');
	var jObj = {
			issueTypeId : 1
		}
	$.ajax({
	  type:'GET',
	  url: 'getAlertIssueSubTypesAction.action',
	  data: {task :JSON.stringify(jObj)}
	}).done(function(result){
		$('#issueSubTypeId').append('<option value="0">Select Problem</option>');
		if(result != null && result.length > 0)
		{
			for(var i in result)
				$('#issueSubTypeId').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
		}
		$('#issueSubTypeId').trigger('chosen:updated');
	});
}

//getDepartmentLevels();
	var loginUserId = "${sessionScope.USER.registrationID}";
	/* Assign */
	$(".chosenSelect").chosen({width:'100%'})
	$(document).on('change', '#locationLevelSelectId', function(){
		getParentLevelsOfLevel();
	});
	$(document).on('change','.locationCls', function(evt, params) {
		designationsByDepartment();
	});
	/*$(document).on('change', '#departmentsId', function(){
		getDepartmentLevels();
	});*/
	$(document).on('change','#designationsId', function(evt, params) {
		var designationId = $(this).val();
		officersByDesignationAndLevel(designationId)
	});
	function getDepartmentLevels(){
		
		var jsObj = {
			departmentId : 49
		}
		$.ajax({
		  type:'GET',
		  url: 'getDepartmentLevelsAction.action',
		  data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result !=null && result.length>0){
				buildDepartmentLevels(result);
			}
		});
		
	}
	function buildDepartmentLevels(result){
		
		var str='';	
		str+='<option value="0">Select Level</option>';
		for(var i in result){
				str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
		}
		
		$("#locationLevelSelectId").html(str);
		$("#locationLevelSelectId").trigger("chosen:updated");
		
		$("#locationLevelSelectId").val(8);
		$("#locationLevelSelectId").trigger("chosen:updated");
		setTimeout(function(){
			getParentLevelsOfLevel();
		},1000);
	}


	function getParentLevelsOfLevel(){
		departmentId = 49;
		var jsObj = {
			departmentId : departmentId,
			levelId : $("#locationLevelSelectId").val()
		}
		$.ajax({
		  type:'GET',
		  url: 'getParentLevelsOfLevelAction.action',
		  data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result !=null && result.length>0){
				buildParentLevelsOfLevel(result,departmentId);
			}
		});
	}
	function buildParentLevelsOfLevel(result,departmentId){
		var str='';
			
			for(var i in result){
				if(i<result.length-1){
					str+='<div class="col-sm-3 m_top10">';
						str+='<label>'+result[i].name+'<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgLocationId"></span></label>';
						str+='<select  class="chosenSelect" id="locationSubLevelSelectId'+result[i].id+'" onchange="getGovtSubLevelInfo('+departmentId+','+result[i].id+')"  ></select>';
					str+='</div>';
				}else{
					str+='<div class="col-sm-3 m_top10">';
						str+='<label>Location<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgLocationId"></span></label>';
						str+='<select  class="chosenSelect locationCls" id="locationSubLevelSelectId'+result[i].id+'" name="grievanceAlertVO.levelValue" ></select>';
					str+='</div>';
				}
				
			}
		
		$("#parentLevelDivId").html(str);
		$(".chosenSelect").chosen({width:'100%'});
		
		for(var i in result){
			
			if(result[i].idnameList !=null && result[i].idnameList.length>0){
				var newStr='';		
				newStr+='<option value="0">Select '+result[i].name+'</option>';
				for(var j in result[i].idnameList){
					 newStr+='<option value="'+result[i].idnameList[j].id+'">'+result[i].idnameList[j].name+'</option>';
				}			
				$("#locationSubLevelSelectId"+result[i].id+"").html(newStr);
				$("#locationSubLevelSelectId"+result[i].id+"").trigger("chosen:updated");
			}
		}
		
		$("#locationSubLevelSelectId1").val(1);
		$("#locationSubLevelSelectId1").trigger('chosen:updated');
		$("#locationSubLevelSelectId1").trigger("change");
	}
	function getGovtSubLevelInfo(departmentId,levelId){
		
		$("#designationsId").empty();
		$("#designationsId").trigger("chosen:updated");
		$("#officerNamesId").empty();
		$("#officerNamesId").trigger("chosen:updated");	
		
		var levelValue=$("#locationSubLevelSelectId"+levelId+"").val();	
		
		var jsObj = {
			departmentId : departmentId,
			levelId :levelId,
			levelValue:levelValue
		}
		$.ajax({
		  type:'GET',
		  url: 'getGovtSubLevelInfoAction.action',
		  data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result !=null){
				buildGovtSubLevelInfoAction(result);
			}
				
		});
	}
	function buildGovtSubLevelInfoAction(result){
		
		var str='';
		if(result !=null){		
			if(result.idnameList !=null && result.idnameList.length>0){
				str+='<option value="0">Select '+result.name+'</option>';
				for(var i in result.idnameList){
					str+='<option value="'+result.idnameList[i].id+'">'+result.idnameList[i].name+'</option>';
				}
			}
			
			$("#locationSubLevelSelectId"+result.id+"").html(str);
			$("#locationSubLevelSelectId"+result.id+"").trigger("chosen:updated");
		}
		
	}



	function designationsByDepartment()
	{
		$("#designationsId").empty();
		$("#designationsId").trigger("chosen:updated");
		$("#officerNamesId").empty();
		$("#officerNamesId").trigger("chosen:updated");
		var LevelId = $("#locationLevelSelectId").chosen().val();
		var deprtmntId = $("#departmentsId").chosen().val();
		var levelValue = $("#refermandalNameId").chosen().val();
		
		var jsObj = {
			departmentId	: deprtmntId,
			levelId			: LevelId,
			levelValue			: levelValue
		}
		$.ajax({
		  type:'GET',
		  url: 'getOldDesignationsByDepartmentNewAction.action',
		  data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			var str='';
			str+='<option value="0">Select Designation</option>';
			for(var i in result)
			{
				str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
			}
			$("#designationsId").html(str);
			$("#designationsId").trigger("chosen:updated");
		});
	}

	function officersByDesignationAndLevel(designationId)
	{
		$("#officerNamesId").empty();
		$("#officerNamesId").trigger("chosen:updated");
		var LevelId = $("#locationLevelSelectId").chosen().val()
		var LevelValue = $("#refermandalNameId").chosen().val()
		
		var jsObj = {
			levelId				: LevelId,
			levelValue			: LevelValue,
			designationId		: designationId
		}
		$.ajax({
		  type:'GET',
		  url: 'getOldOfficersByDesignationAndLevelNewAction.action',
		  data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			var str='';
			str+='<option value="0">Select Officer</option>';
			for(var i in result)
			{
				str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
			}
			$("#officerNamesId").html(str);
			$("#officerNamesId").trigger("chosen:updated");
		});
	}

function getAlertDetails(alertStatusId,status,feedbackStattusId)
	{
		/* var datesArr =[]; 
		if(datesArr != null){
			startDate = datesArr[0];
			endDate = datesArr[1];
		}
		var mobileNo;
		var statusId; */
		$('#alertDataDivId').html('<center><img id="" style="width:50px;height:50px;"  src="./images/Loading-data.gif" alt="Processing Image"/></center>');
		
		var fromDateStr;
		var toDateStr;
		
		 var dates = $('#dateRangeIdForEvents').val();
		 var dateArray = dates.split("-");
		 if(dateArray != null){
			 fromDateStr = dateArray[0];
			 toDateStr = dateArray[1];
		  }
		var jObj = {
			alertStatusId : alertStatusId,
			mobileNo : '',
			fromDate :fromDateStr,//"12/04/2016",
			toDate : toDateStr,//"12/04/2017"
			feedbackStattusId:feedbackStattusId
			
		}
		$.ajax({
		  type:'GET',
		  url: 'getAlertDetailsByStatusAction.action',
		  data: {task :JSON.stringify(jObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				buildAlertDetails(result,status,alertStatusId);
			}
		});
}

function getAlertCallerDetails(alertId)
	{
		$("#comntId").val('');
		$("#saveMsgId").html('');
		$("#feedbackStatusList").val(0);
		$("#updateAlertModalDivId").modal('show');
		var jObj = {
			alertId : alertId
		}
		$.ajax({
		  type:'GET',
		  url: 'getAlertCallerDetailsAction.action',
		  data: {task :JSON.stringify(jObj)}
		}).done(function(result){
			if(result != null){
				buildAlertCallerDetails(result);
			}
		});
}

function saveAlertStatusDetails()
	{
		var comment = $("#comntId").val();
		var feedBackStatus =$("#feedbackStatusList").val();
		var alertId = $("#hiddenAlertId").val();
		var sourceId = $("#hiddenSourceId").val();
		var statusId = $("#hiddenStatusId").val();
		
		var jObj = {
			alertId : alertId,
			comment : comment,
			alertStatusId : statusId,
			alertFeedBackStatusId : feedBackStatus,
			alertSourceId : sourceId
		}
		$.ajax({
		  type:'GET',
		  url: 'saveAlertStatusAction.action',
		  data: {task :JSON.stringify(jObj)}
		}).done(function(result){
			if(result == "success"){
				showDashboard();
				$("#saveMsgId").html("<span style='color:green;'>Updated Successfully..</span>");
			}else{
				$("#saveMsgId").html("<span style='color:green;'>Updation failed.Please try again.</span>");
			}
			setTimeout(function(){ 
				 $("#updateAlertModalDivId").modal('hide');
			},1500);
		});
}
getFeedBackStatusDetails();
function getFeedBackStatusDetails()
	{
		var jObj = {
			}
		$.ajax({
		  type:'GET',
		  url: 'getAlertFeedBackStatusDetailsAction.action',
		  data: {task :JSON.stringify(jObj)}
		}).done(function(result){
			if(result != null)
			{
				for(var i in result)
					$('#feedbackStatusList').append('<option value="'+result[i].id+'">'+result[i].status+'</option>');
			}
		});
}	
	
function buildAlertDetails(result,status,alertStatusId){
	var str = '';
	if(status == 0)
		status = "Total";
		str+='<div style="background-color: lightgrey; font-weight: bold; margin-top: 5px; margin-bottom: 5px; border-radius: 5px; text-align: center; text-transform: uppercase; font-size: 15px;">  '+status+' Status Grievance Details </div>';
		
	str+='<table class="table table-bordered " style="text-align:center;" id="tabbDetails" >';
		str+='<thead>';
			str+='<th  style="text-align:center">TITLE</th>';
			str+='<th  style="text-align:center">DEPARTMENT</th>';
			str+='<th  style="text-align:center">ISSUE TYPE</th>';
			str+='<th  style="text-align:center">ISSUE SUB TYPE</th>';
			str+='<th  style="text-align:center">IMPACT LEVEL</th>';
			str+='<th  style="text-align:center">LOCATION</th>';
			str+='<th  style="text-align:center"> CREATED ON</th>'; 
			str+='<th  style="text-align:center"> CALLER DETAILS</th>'; 
			str+='<th  style="text-align:center"> FEEDBACK STATUS  </th>'; 
			str+='<th  style="text-align:center"> UPDATE STATUS  </th>'; 
		str+='</thead>';
		str+='<tbody>';
		for( var i in result){
			var locationName ="";
			if(result[i].district != null && result[i].district.length>0)
				locationName = locationName+" "+result[i].district+" District,<br> ";
			if(result[i].constituency != null && result[i].constituency.length>0)
				locationName = locationName+" "+result[i].constituency+" Assembly ,<br> ";
			
			if(result[i].tehsil != null){
				if(result[i].tehsil != null && result[i].tehsil.length>0)
					locationName = locationName+" "+result[i].tehsil+" Mandal,<br> ";
				if(result[i].panchayat != null && result[i].panchayat.length>0)
					locationName = locationName+" "+result[i].panchayat+" Panchayat,<br> ";
				if(result[i].hamlet != null && result[i].hamlet.length>0)
					locationName = locationName+" "+result[i].hamlet+" Hamlet , <br>";
			}
			else{
				if(result[i].leb != null && result[i].leb.length>0)
					locationName = locationName+" "+result[i].leb+" Munci/Corp/Greater City, <br>";
				if(result[i].ward != null && result[i].ward.length>0)
					locationName = locationName+" "+result[i].ward+"  ";
			}
			
			
		
		
			str+='<tr>';
				str+='<td>'+result[i].title+'</td>';
				str+='<td>'+result[i].deptName+'</td>';
				str+='<td>'+result[i].issueType+'</td>';
				str+='<td>'+result[i].issueSubType+'</td>';
				str+='<td>'+result[i].locationName+'</td>';
				str+='<td style="">'+locationName+'</td>';
				str+='<td>'+result[i].createdTime+'</td>';
				str+='<td>'+result[i].name+'<br>MobileNo:'+result[i].mobileNo+'</td>';
				
				str+='<td>';
				if(result[i].feedbackStatus != null){
					if(result[i].feedbackStatus == 	"Received" || result[i].feedbackStatus == 	"Not Received"){
						str+=' '+result[i].feedbackStatus+' <br>';
					}
				}
			str+='</td>';
			str+='<td>';
				str+='<button class="btn btn-success updateAlertCls btn-xs btn-mini" attr_alert_id ="'+result[i].alertId+'" attr_alert__source_id ="'+result[i].alertSourceId+'" attr_alert__status_id ="'+result[i].statusId+'">Update</button>';
			str+='</td>';
			str+='</tr>';
		}
		str+='</tbody>';
		str+='</table>';
		$("#alertDataDivId").html(str);
		$('#tabbDetails').dataTable({
				"iDisplayLength": 20,
				"aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
			});
			
}
$(document).on("click",".updateAlertCls",function(){ 
	var alertId = $(this).attr("attr_alert_id");
	var sourceId =$(this).attr("attr_alert__source_id");
	var statusId =$(this).attr("attr_alert__status_id");
	getAlertCallerDetails(alertId);
	$("#hiddenAlertId").val(alertId);
	$("#hiddenSourceId").val(sourceId);
	$("#hiddenStatusId").val(statusId);
});	
	
function buildAlertCallerDetails(result){
	var str= '';
	
	//	str+='<div class="panel panel-default m_top10">';
		//	str+='<div class="panel-body">';
			
				for(var i in result){
				//str+='<p> Given By : '+result[i].name+' - '+result[i].mobileNo+'</p>';
					//str+='<div class="col-md-12 col-xs-12 col-sm-12">';
					str+='<div class="col-md-4 col-xs-12 col-sm-6">';
					str+='<p><span>Given By : </span> <span>'+result[i].name+'</span></p>';
					str+='</div>';
					str+='<div class="col-md-4 col-xs-12 col-sm-6">';
					str+='<p><span>Mobile No : </span> <span>'+result[i].mobileNo+'</span></p>';
					str+='</div>';
					str+='<div class="col-md-4 col-xs-12 col-sm-6">';
					str+='<p><span>Created On : </span> <span>'+result[i].date1+'</span></p>';
					str+='</div>';
					//str+='</div>';
					str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
					str+='<h5><span>Title : </span> <span>'+result[i].title+'</span></h5>';
					str+='</div>';
					str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
					str+='<h5><span>description : </span> <span>'+result[i].desc+'</span></h5>';
					str+='</div>';
					str+='<div class="col-md-12 col-xs-12 col-sm-12  m_top10">';
					str+='<label>Comment</label><br>';
					str+='<textarea id="comntId" rows="3" style="width: 799px;"></textarea>';
					str+='</div>';
				}
			 
		//	str+='</div>';
		//str+='</div>';
		
$("#buildUpdateDivId").html(str);
}



function showDashboard(){
		$("#dateDivId").show();
		getGrievanceSummary();
		$('#dashboardGrevanceDivId').show();
		$('#newGrevanceDivId').hide();
	}
	function showNewGrievance(){
		$('#dateDivId').hide();
		$('#dashboardGrevanceDivId').hide();
		$('#newGrevanceDivId').show();
	}
	function getGrievanceSummary(){
	    $('#alertDataDivId').html('');
		$('#summaryDiv').html('<center><img id="" style="width:50px;height:50px;"  src="./images/Loading-data.gif" alt="Processing Image"/></center>');
		var fromDateStr;
		var toDateStr;
		
		 var dates = $('#dateRangeIdForEvents').val();
		  var dateArray = dates.split("-");
		  if(dateArray != null){
			  fromDateStr = dateArray[0];
			 toDateStr = dateArray[1];
		  }
		  
		var jsObj ={
			startDate:fromDateStr,//'01/04/2017',
			endDate:toDateStr,//'01/05/2017',
			mobileNo:'',
			status:"",
			deptId:49,
			task:""
		}
		$.ajax({
			type:'GET',
			url: 'getAlertCallerDetails.action',
			data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null){
				buildGrievanceSummary(result);
			}	  
		});
	}
	
	function buildGrievanceSummary(result){
		if(result != null && result.length>0){
			var str='';
			var total = 0;
			if(result[0].statusList != null && result[0].statusList.length>0) {
				str+='<div style="background-color: lightgrey; font-weight: bold; margin-top: 55px; margin-bottom: 5px; border-radius: 5px; text-align: center; text-transform: uppercase; font-size: 15px;">  Grievance Details </div>';
				str+='<table  class="table table-bordered"  style="text-align:center;" id="tabDetails">';
					str+='<thead>';
					//str+='<tr>';
						str+='<th  style="text-align:center">  </th>';
						var totalCount =0;
						for(var k in result[0].statusList[0].statusList){
							if(result[0].statusList[0].statusList[k].alertStatusId != 1)
							str+='<th  style="text-align:center" > '+result[0].statusList[0].statusList[k].status+' </th>';
						}
						str+='<th  style="text-align:center">TOTAL COUNT </th>';
					//str+='</tr>';
					
					str+='</thead>';
					str+='<tbody>';
						/*for(var k in result[0].statusList){
							str+='<tr>';
							str+='<th  style="text-align:center" > '+result[0].statusList[k].status+' </th>';
							for(var l in result[0].statusList[k].statusList){
								if(result[0].statusList[k].statusList[l].count != null){
									if(result[0].statusList[k].statusList[l].alertStatusId != 2 && result[0].statusList[k].statusList[l].alertStatusId != 1 && result[0].statusList[k].statusList[l].alertStatusId != 3)
											str+='<td  style="text-align:center" > <a style="color:green;font-weight:bold;" href="javascript:{getAlertDetails('+result[0].statusList[k].statusList[l].alertStatusId+',\''+result[0].statusList[k].statusList[l].status+'\','+result[0].statusList[k].alertStatusId+')}"> '+result[0].statusList[k].statusList[l].count+' </a> </td>';
										else
											str+='<td  style="text-align:center" > '+result[0].statusList[k].statusList[l].count+' </td>';
								}	
								else 
									str+='<td  style="text-align:center" > -  </td>';
							}
							str+='</tr>';
						}
						str+='<tr>';*/
						str+='<th  style="text-align:center" > TOTAL </th>';
						for(var k in result[0].statusList[0].statusList){
							if(result[0].statusList[0].statusList[k].alertStatusId != 1){
								if(result[0].statusList[0].statusList[k].totalCount != null && parseInt(result[0].statusList[0].statusList[k].totalCount)>0){
									total = total+result[0].statusList[0].statusList[k].totalCount;
									//str+='<td  style="text-align:center" > '+result[0].statusList[0].statusList[k].count+' </td>';
									str+='<td  style="text-align:center" > <a style="color:green;font-weight:bold;" href="javascript:{getAlertDetails('+result[0].statusList[0].statusList[k].alertStatusId+',\''+result[0].statusList[0].statusList[k].status+'\',0)}"> '+result[0].statusList[0].statusList[k].totalCount+' </a> </td>';
								}
								else if(result[0].statusList[0].statusList[k].count != null && parseInt(result[0].statusList[0].statusList[k].count)>0){
									//str+='<td  style="text-align:center" > '+result[0].statusList[0].statusList[k].count+' </td>';
									str+='<td  style="text-align:center" > <a style="color:green;font-weight:bold;" href="javascript:{getAlertDetails('+result[0].statusList[0].statusList[k].alertStatusId+',\''+result[0].statusList[0].statusList[k].status+'\',0)}"> '+result[0].statusList[0].statusList[k].count+' </a> </td>';
								}
								else{
									str+='<td  style="text-align:center" >  -  </td>';
								}
								}
							}
							str+='<td  style="text-align:center" ><a style="color:green;font-weight:bold;" href="javascript:{getAlertDetails(0,0,0)}"> '+total+'</a></td>';
						str+='</tr>';
					str+='</tbody>';
				str+='</table>';
			}
			
			$('#summaryDiv').html(str);
			
		}
	}
	getDistrictsForReferPopup();
	function getGovtGrievanceAlertDetails(){
		//$("#commentsBlock").html('');
		 var locatoinType;
		 var mobileNo;
		 var locationId;
		 $(document).on("click",".radioBtnCls",function(){
			  var searchType  = $('input[name=checkBoxName]:checked').val();
			  if(searchType == 1){
				  $(".mobileSearchCls").show();
				  $(".locationSearchCls").hide();
				 mobileNo =$("#searchBy").val();
				if(mobileNo != null && mobileNo.trim().length>0){
				      locationId = 0;
					  locatoinType = "";
				}
			  }else if(searchType == 2){
				  $(".locationSearchCls").show();
				  $(".mobileSearchCls").hide();
				  var  districtId = $("#modalDistrictId").val();
				  var  mandalId = $("#modalMandalNameId").val();
				  var  villageId = $("#modalPanchayatId").val();
				  var hamletId = $("#modalhamletId").val();
				  if(hamletId != null && hamletId>0){
					  locatoinType = "hamlet";
					  locationId = hamletId;
					   mobileNo ="";
				  }
				  if(villageId != null && villageId>0){
					  locatoinType = "panchayat";
					  locationId = villageId;
					   mobileNo ="";
				  }
				  if(mandalId != null && mandalId>0){
					  locatoinType = "tehsil";
					  locationId = mandalId;
					   mobileNo ="";
				  }
				  if(districtId != null && districtId>0){
					  locatoinType = "district";
					  locationId = districtId;
					  mobileNo ="";
				  }
				  
			  }
			  });
		 $(".radioBtnCls").each(function(){
			 if($(this).prop('checked')==true){
			  var searchType  = $('input[name=checkBoxName]:checked').val();
			  if(searchType == 1){
				  $(".mobileSearchCls").show();
				  $(".locationSearchCls").hide();
				 mobileNo =$("#searchBy").val();
				if(mobileNo != null && mobileNo.trim().length>0){
				      locationId = 0;
					  locatoinType = "";
				}
			  }else if(searchType == 2){
				  $(".locationSearchCls").show();
				  $(".mobileSearchCls").hide();
				  var  districtId = $("#modalDistrictId").val();
				  var  mandalId = $("#modalMandalNameId").val();
				  var  villageId = $("#modalPanchayatId").val();
				  var hamletId = $("#modalhamletId").val();
				  if(districtId != null && districtId>0){
					  locatoinType = "district";
					  locationId = districtId;
					  mobileNo ="";
				  }
				  if(mandalId != null && mandalId>0){
					  locatoinType = "tehsil";
					  locationId = mandalId;
					   mobileNo ="";
				  }
				  if(villageId != null && villageId>0){
					  locatoinType = "panchayat";
					  locationId = villageId;
					   mobileNo ="";
				  }
				if(hamletId != null && hamletId>0){
					  locatoinType = "hamlet";
					  locationId = hamletId;
					   mobileNo ="";
				  }				  
			  }
			 }
		});
		 /*mobileNo = $('#searchBy').val().trim();
			if(mobileNo.length == 0 )
			{
				$('#errorMassgeId').html('Please enter Mobile No.');
				return;
			}
			if(mobileNo.length != 10 ||mobileNo.length > 10)
			{
				$('#errorMassgeId').html('Invalid Mobile No.');
				return;
			}*/
      	$('#commentsBlock').html('<center><img id="" style="width:50px;height:50px;"  src="./images/Loading-data.gif" alt="Processing Image"/></center>');		  
		var jsObj ={
			mobileNo: mobileNo,
			locatoinType:locatoinType,
			locationId : locationId,
			fromDate : "",
			toDate : "",
			alertStatusId : 0
		}
		$.ajax({
			type:'GET',
			url: 'getGovtGrievanceAlertDetailsAction.action',
			data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				buildGovtGrievanceAlertDetails(result);
			}else{
			 $("#commentsBlock").html("<span style='padding:15px;font-weight:bold;'>NO DATA AVAILABLE</span>"); 
		  }	  
		});
	}
  function buildGovtGrievanceAlertDetails(result){
	var str = '';
	str+='<div class="table-responsive">';
	str +='<table class="table bg_ED " id="partMeetingModalData">'; 
	str +='<thead>';
	str +='<th style="text-transform: uppercase;"> Date</th>';
	str +='<th style="text-transform: uppercase;"> Time</th>';
	str +='<th style="text-transform: uppercase;"> Location</th>';
	str +='<th style="text-transform: uppercase;"> Title</th>';
	str +='<th style="text-transform: uppercase;"> Discription</th>';
	str +='<th style="text-transform: uppercase;"> Related To</th>';
	str +='<th style="text-transform: uppercase;"> Problem</th>';
	str +='<th style="text-transform: uppercase;"> Status</th>';
	str +='<th style="text-transform: uppercase;"> Caller Name</th>';
	str +='<th style="text-transform: uppercase;"> Mobile No</th>';
	str +='</thead>';
	str +='<tbody>';
	for(var i in result){		
			str+='<tr>';
		if(result[i].date != null){
			str+='<td style="text-transform: uppercase;">'+result[i].date+'</td>';
		}else{
			str+='<td>-</td>';
		}
		if(result[i].time != null){
			str+='<td style="text-transform: uppercase;">'+result[i].time+'</td>';
		
		}else{
			str+='<td>-</td>';
		}
		if(result[i].tehsil != null){
			var locationName ="";
			if(result[i].district != null && result[i].district.length>0)
				locationName = locationName+" "+result[i].district+" District,<br> ";
			if(result[i].assembly != null && result[i].assembly.length>0)
				locationName = locationName+" "+result[i].assembly+" Assembly ,<br> ";
			if(result[i].tehsil != null && result[i].tehsil.length>0)
				locationName = locationName+" "+result[i].tehsil+" Mandal,<br> ";
			if(result[i].panchayat != null && result[i].panchayat.length>0)
				locationName = locationName+" "+result[i].panchayat+" Panchayat,<br> ";
			if(result[i].hamlet != null && result[i].hamlet.length>0)
				locationName = locationName+" "+result[i].hamlet+" Hamlet , <br>";
			if(result[i].leb != null && result[i].leb.length>0)
				locationName = locationName+" "+result[i].leb+" Munci/Corp/Greater City, <br>";
			if(result[i].ward != null && result[i].ward.length>0)
				locationName = locationName+" "+result[i].ward+"  ";
			
			str+='<td style="">'+locationName+'</td>';
		}else{
			str+='<td>-</td>';
		}
		if(result[i].title != null){
			str+='<td style="text-transform: uppercase;">'+result[i].title+'</td>';
		}else{
			str+='<td>-</td>';
		}
		if(result[i].description != null){
			str+='<td style="text-transform: uppercase;">'+result[i].description+'</td>';
		}else{
			str+='<td>-</td>';
		}
		if(result[i].relatedTo != null){
			str+='<td style="text-transform: uppercase;">'+result[i].relatedTo+'</td>';
		}else{
			str+='<td>-</td>';
		}
		if(result[i].problem != null){
			str+='<td style="text-transform: uppercase;">'+result[i].problem+'</td>';
		}else{
			str+='<td>-</td>';
		}
		if(result[i].status != null){
			str+='<td style="text-transform: uppercase;">'+result[i].status+'</td>';
		}else{
			str+='<td>-</td>';
		}
		if(result[i].createdBy != null){
			str+='<td style="text-transform: uppercase;">'+result[i].createdBy+'</td>';
		}else{
			str+='<td>-</td>';
		}
		if(result[i].mobileNo != null && result[i].mobileNo != ""){
			str+='<td style="text-transform: uppercase;">'+result[i].mobileNo+'</td>';
		}else{
			str+='<td>-</td>';
		}
		str+='</tr>';
		
		}
    str +='</tbody>';
	str +='</table>';
	str +='</div>';
	$("#commentsBlock").html(str); 
	$("#partMeetingModalData").dataTable();
	
	}
	
	function getMandalsByConstituencyForReferPopup1(){
		$("#commentsBlock").html('');
	 var districtId = $('#modalDistrictId').val();
	 
	 var jobj = {
		districtId : districtId
	}
		$.ajax({
			type : "POST",
			url  : "getAllMandalsByDistrictIDAction.action",
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			var mandalStr='';
			if(result != null && result.length > 0){
				
			    mandalStr +='<option value="0">ALL</option>';
				for(var i in result){
					if(result[i].id > 0)
					mandalStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
				}
				$("#modalMandalNameId").html(mandalStr);
				$("#modalMandalNameId").trigger('chosen:updated');                
				//for panchayat
				$("#modalPanchayatId").html('<option value="0">ALL</option>');
				$("#modalPanchayatId").trigger('chosen:updated'); 
			
				if(glMandalId != null && glMandalId>0)
				$("#modalMandalNameId").val(glMandalId);
				$("#modalMandalNameId").trigger('chosen:updated'); 
				
				if(glPanchayatId != null && glPanchayatId>0){
					$("#modalMandalNameId").trigger('onchange');
				}
				if(glhamletId != null && glhamletId>0)
					$("#modalMandalNameId").trigger('onchange');
				
			}else{
				//for mandal/municipality
				$("#modalMandalNameId").html('<option value="0">ALL</option>');
				$("#modalMandalNameId").trigger('chosen:updated');
				//for panchayat
				$("#modalPanchayatId").html('<option value="0">ALL</option>');
				$("#modalPanchayatId").trigger('chosen:updated'); 
			}
		});
 }
 function getPanchayatsForReferPopup1(){
	 $("#commentsBlock").html('');
	 designationsByDepartment();
	
	 $("#modalPanchayatId").find('option').not(':first').remove();
	 var mandalId = $('#modalMandalNameId').val();
	 var  type = $("#modalMandalNameId option:selected").text();
	 		   
	 if(type.indexOf("Mandal") == -1) 
		type = "muncipality" ;
	else
		type = "mandal" ; 
	  var jsObj={
				mandalId :mandalId,
				type:type,
				task:""
				
			}
	 $.ajax({
				type:"POST",
				url :"getPanchayatAndWardDetailsAction.action",
				 dataType: 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				var panchyatStr='';
				if(result!=null && result.length>0){
					panchyatStr +='<option value="0">ALL</option>';
					for(var i in result){
						if(result[i].id > 0)
							panchyatStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
					}
					$("#modalPanchayatId").html(panchyatStr);
					$("#modalPanchayatId").trigger('chosen:updated');
					
					if(glPanchayatId != null && glPanchayatId>0)
					$("#modalPanchayatId").val(glPanchayatId);
					$("#modalPanchayatId").trigger('chosen:updated'); 
					if(glhamletId != null && glhamletId>0)
					$("#modalPanchayatId").trigger('onchange');
				}else{
					//for panchayat
					$("#modalPanchayatId").html('<option value="0">ALL</option>');
					$("#modalPanchayatId").trigger('chosen:updated'); 
				}     
		});
}

 $(document).on("click",".radioBtnCls",function(){ //mobileSearchByCls
	  $("#searchBy").val('');
	  $("#commentsBlock").html(''); 
	  $("#modalDistrictId").val(0).trigger('chosen:updated');
	  $("#modalMandalNameId").val(0).trigger('chosen:updated');
	  $("#modalPanchayatId").val(0).trigger('chosen:updated');
	  $("#modalhamletId").val(0).trigger('chosen:updated');
	  var searchType  = $('input[name=checkBoxName]:checked').val();
	  if(searchType == 1){
		  $(".mobileSearchCls").show();
		  $(".locationSearchCls").hide();
		 mobileNo =$("#searchBy").val();
	  }else if(searchType == 2){
		  $(".locationSearchCls").show();
		  $(".mobileSearchCls").hide();
	  }
});
			  
var glDistrictId =0;
var glMandalId = 0;
var glPanchayatId = 0;
var glhamletId =0;

 $(document).on("click",".govtGrievanceCls",function(){
	  $("#govtGrievanceAlertModalId").modal("show");
       $("#commentsBlock").html('');
      $("#searchBy").val('');	   
	   $("#radioBtnClsId").trigger("click");
	    $("#modalDistrictId").val(0);
		$("#modalMandalNameId").val(0);
		$("#modalPanchayatId").val(0);
		$("#modalhamletId").val(0);
		
		$("#modalDistrictId").trigger('chosen:updated');
		$("#modalMandalNameId").trigger('chosen:updated');
		$("#modalPanchayatId").trigger('chosen:updated');
		$("#modalhamletId").trigger('chosen:updated');
		glDistrictId =0;
		glMandalId = 0;
		glPanchayatId = 0;
        glhamletId =0;
	   var type = $(this).attr('attr_id');

		 if(type =='mobile'){
			$(".mobileSearchCls").show();
			$(".locationSearchCls").hide();
			$('#radioBtnMobileId').prop('checked',true);
			var priviousMobileNo =$("#mobileNoId").val();
			$("#searchBy").val(priviousMobileNo);
			
		 }
		 else if(type =='location'){
			 $(".mobileSearchCls").hide();
			 $(".locationSearchCls").show();
			 $('#radioBtnClsId').prop('checked',true);
		 } 
		 
		glDistrictId =$("#referdistrictId").val();
		glMandalId = $("#refermandalNameId").val();
		glPanchayatId = $("#referpanchayatId").val();
		glhamletId =$("#hamletId").val();
		
		$("#modalDistrictId").val(glDistrictId);
		$("#modalDistrictId").trigger('chosen:updated');
		$("#modalDistrictId").trigger("onchange");
 });
 function getHamletss1(){
	  $("#commentsBlock").html('');
	 $("#modalhamletId").find('option').not(':first').remove();
	 var panchayatId = $('#modalPanchayatId').val();
	 var  type = $("#modalMandalNameId option:selected").text();
			   
	 if(type.indexOf("Mandal") == -1) 
		type = "muncipality" ;
	else
		type = "mandal" ; 
	
	if(type == "mandal"){
		
		var jsObj={
				panchayatId :panchayatId,
				task:""
			}
	 $.ajax({
				type:"POST",
				url :"getHamletsForPanchayatAction.action",
				 dataType: 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				var panchyatStr='';
				if(result!=null && result.length>0){
					panchyatStr +='<option value="0">ALL</option>';
					for(var i in result){
						if(result[i].id > 0)
							panchyatStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
					}
					$("#modalhamletId").html(panchyatStr);
					$("#modalhamletId").trigger('chosen:updated');
					if(glhamletId != null && glhamletId>0)
					$("#modalhamletId").val(glhamletId);
					$("#modalhamletId").trigger('chosen:updated');
				}else{
					//for panchayat
					$("#modalhamletId").html('<option value="0">ALL</option>');
					$("#modalhamletId").trigger('chosen:updated'); 
				}     
		});
	}
}
$(document).on("click",".closeFileCls",function(){
	$("#uploadFileId0").val('');
});
</script>
</body>
</html>