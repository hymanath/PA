<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Activity</title>
	<link href="dist/activity/css/bootstrap.min.css" rel="stylesheet"/>
	<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/TDP.gif">
	<link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
	<link href='dist/activity/css/activity.custom.css' rel='stylesheet' type='text/css'>
	<link href='dist/activity/Date/daterangepicker-bs3.css' rel='stylesheet' type='text/css'>
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>	
	<script src="dist/activity/js/bootstrap.min.js" type="text/javascript"></script> 
	<!--<script type="text/javascript" src="js/bootstrap.js" ></script> -->
	 <script src="dist/activity/Date/moment.min.js" type="text/javascript"></script>
	 <script src="dist/activity/Date/daterangepicker.js" type="text/javascript"></script>
	 
	
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
	
	<!-- Skin CSS files resize.css must load before layout.css --> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css"> 
 	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css">
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/calendar.css"> 
 <script type="text/javascript" src="js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jQ_datatables/css/jquery.dataTables.css"/> 
	
	
<link href="dragAndDropPhoto/css/jquery.filer.css" type="text/css" rel="stylesheet" />
<link href="dragAndDropPhoto/css/themes/jquery.filer-dragdropbox-theme.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">	
<link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/>
<link href="dist/newmultiselect/chosen.css" rel="stylesheet" type="text/css">

	
 <style type="text/css">
	.labelD{background:#C4D6FA;border-radius:3px;border:1px solid #333;padding:0px 3px 0px 23px;}
	.m_top10{margin-top:10px}
	.m_top20{margin-top:20px}
	.input-g1 .form-control{border-radius:0px;border-left:0px}
	.input-g1 .input-group-addon{border-radius:0px;background:#fff;}
	.starMark{font:15px;color:red;}
	.navtabsCustom{border:0px}
	.navtabsCustom li{border:0px;}
	.navtabsCustom li a{border-radius:0px;color:#666 }
	.navtabsContent{background:#fff;padding:10px;border:1px solid #ddd}
	.bg_cc{background:#ccc}
	.or{padding:8px;border-radius:50%;border:1px solid #ddd;width:40px;}

.errorCls{color:red;font-size:12px;}
.prev, .next{min-width:44px !important}

 </style>
</head>
<body>
	<form method="POST" enctype="multipart/form-data" name="saveActivityDetails" action="saveActivityDetailsAction.action">
<div class="container">


	<div class="row">
   		<div class="col-md-12">
        	<div class="panel panel-default panel-custom">
            	<div class="panel-heading">
                	<h4 class="panel-title">SEARCH TO UPDATE PROGRAM ACTIVITIES 
						<!--<span class="pull-right" >
							<div class="input-group col-md-12" style="margin-top:-8px">
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
								<input type="text" class="searchDateCls form-control" />
							</div>
						</span>-->
					</h4>
					
                </div>
                <div class="panel-body">
                	<div class="row">
                    	<div class="col-md-3">
                        	<img src="img/searchicon.png" style="border-right:1px solid #00B17D">
                        </div>
                        <div class="col-md-9">
							<div class="row">
								<div class="col-md-9" id="ErrDiv" style="color:#E6211E;">
								</div>
							</div>
                        	<div class="row">
							<!--<div class="col-md-4"><span class="starMark">*</span>
                                	<label>Activity Type</label>
                            </div>-->
							<div class="col-md-6"><span class="starMark">*</span>
								<label> Activity Name </label>
								<select id="ActivityList" class="form-control" name="activityVO.activityLevelId" onchange="getAllActivityLevelsByActivity(this.value);">
									<option value="0"> Select Activity </option>
								</select>
							</div>
							<div class="col-md-6"><span class="starMark">*</span>
								<label>Activity Level</label>
								<select id="activityLevelList" class="form-control" >
									<option value="0" > Select Activity Level</option>
								</select>
							</div>
							
								<!--
                                <div class="col-md-12 m_top10">
                                	<label class="radio-inline">
                                    	<input type="radio">Constituency
                                    </label>
                                    <label class="radio-inline">
                                    	<input type="radio">Mandal/ Town / Division
                                    </label>
                                    <label class="radio-inline">
                                    	<input type="radio">Panchayat/ Ward
                                    </label>
                                </div>
								-->
								
							<div class="col-md-4 m_top10" id="districtDivId" style="display:none;">
								<label>District</label>
								<select id="districtList" class="form-control" name="activityVO.districtId" >
								</select>
							</div>	
							<div class="col-md-4 m_top10" id="constituencyDivId"  style="display:none;"><span class="starMark">*</span>
								<label>Constituency</label>
								<select id="constiList" class="form-control" onchange="getMunciMandalsList(this.value)" name="activityVO.constituencyId" >
								</select><span > <img src="images/ajaxImg2.gif" style="width:20px;margin-top:-20px;margin-left:-25px;display:none;" id="procesingImg3"></span>
							</div>
							<div class="col-md-4 m_top10" id="mandalDivId" style="display:none;">
								<label >Mandal/ Town/ Division</label>
								<select id="mandalsList" class="form-control" onchange="getPanchayatWardByMandal(this.value);">
									<option value="0"> Mandal/ Town/ Division</option>
								</select>
								<span > <img src="images/ajaxImg2.gif" style="width:20px;margin-top:-20px;margin-left:-25px;display:none;" id="procesingImg"></span>
							</div>
							<div class="col-md-4 m_top10" id="panchayatDivId" style="display:none;">
								<label>Panchayat/ Ward</label>
								<select id="villageWardsList" class="form-control">
									<option value="0"> Select Panchayat/ Ward</option>
								</select>
							</div>
							</div>
							<div class="row">
							<div class="col-md-3 m_top10 col-md-offset-4">
								<button id="searchId" class="btn btn-block btn-custom btn-success" type="button" >SEARCH</button>
							</div>
							</div>
								
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        
		
		
        <div class="col-md-12" >
		<div class="panel panel-default panel-custom" id="assemblydivId" style="display:none">
		  <div class="panel-heading">
			<!--<h4 class="panel-title">ASSEMBLY CONSTITUENCY WISE ACTIVITIES -  <small style="text-transform: uppercase;"><b>${sessionScope.UserName}</b></small></h4> -->
			<h4 class="panel-title"><span  id="constituencyHeadingId"  style="display:none">  CONSTITUENCY WISE ACTIVITIES</span>
			</h4>
			<h4 class="panel-title" id="districtHeadingId" style="display:none"> DISTRICT WISE ACTIVITIES </h4>
			<span class="pull-right" style="margin-top: -20px;"><a href="javascript:{}" class="btn btn-success btn-xs" id="showAsmblyData" style="display:none" >Show Data</a></span> 
			<span class="pull-right" style="margin-top: -20px;"><a href="javascript:{}" class="btn btn-success btn-xs" id="hideAsmblyData" style="display:none" >Hide Data</a></span>
		  </div>
		   <div class="panel-body" id="assblyBody">
		   <div id="bloodDonationDetails"></div>
			<div id="buildAssConsActivity" ></div>
		   </div>
		
		</div><!--
		<div class="row" id="activityresponsesDiv" style="display:none;">
			<div class="col-md-12">
				<div class="panel panel-default panel-custom">
					<div class="panel-heading ">
						<h4 class="panel-title">ACTIVITY RESPONSES</h4>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-4" style="display:none;" id="questionsDiv">
								<select class="form-control" id="questionsId">
									<option value="0">Select Questions</option>
								</select>	
							</div>
							<div class="col-md-4" style="display:none;" id="questionsForOptDiv">
							<select class="form-control" id="questionsForOptionsId">
								<option value="0">Select All </option>
							</select>
							</div>
						</div>
						<div class="row  m_top10" id="statsDiv" style="display:none;">
							<div class="col-md-12">
								<div class="bg_66" style="padding:10px 15px;background:#663300;color:#fff">
									<h4 class="panel-title" id="activityHeadingDiv" style="font-weight:bold;display:none;">STATUS REPORT</h4>
								</div>
							</div>
							<div class="col-md-12">
								<div id="buildActivityReasonReportTableId"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		-->
		<div class="row" id="datesDivId" style="margin-bottom:10px;margin-top:5px;margin-left:5px;display:none;">
			<div class="col-md-5" >
				<select class="form-control" id="datesId" multiple data-placeholder="Select Date">
					<option value="0">Select Date</option>
				</select>
			</div>
			<div class="col-md-2">
				<input type="button" value="submit" id="locationInfoBtnId">
			</div>
			<!--<div class="col-md-5">
			<span class="btn-success btn btn-xs pull-right" style="font-weight:bold;">
			<input type="checkbox" onclick="getLocationDetailsForActivity('','','0','0',2);" id="imageChekId">
				SHOW ONLY IMAGES UPLOADED LOCATIONS
			</span>
			</div>-->
		</div>
		<div class="panel panel-default panel-custom" id="resultsDiv" style="display:none;">
		
            	<div class="panel-heading">
                	<h4 class="panel-title"><span class="font-40" id="constncyId">SEARCH RESULTS  </span><span class="font-12" id="headingId"> - Activity Name(Activity level)</span>
                    <!--<span class="pull-right">
                    	<label class="checkbox-inline">
							<span>
								<input type="radio" checked="checked" id="allId" onclick="getLocationDetailsForActivity('','','0','0');" name="radio1">All
							</span>
							<span>
								<input type="radio" id="conductedId" onclick="getLocationDetailsForActivity('','','0','0',2);" name="radio1">Show Conducted Locations
							</span>
							<span  style="margin-left:30px;">
								<input type="radio" id="notConductedId" onclick="getLocationDetailsForActivity('','','0','0',2);" name="radio1">Show Not Conducted Locations
							</span>
							<!--<span  style="margin-left:30px;">
								<input type="button" class="btn btn-success btn-xs" value="Get Details" onclick="getLocationDetailsForActivity('','');">
							</span>
							
                        </label>
                    </span>-->
                    </h4>
                </div>
                <div class="panel-body">
                	<div>
                      <!-- Nav tabs -->
                      <!--<ul class="nav nav-tabs nav-tabs-custom" role="tablist">
                        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Planed</a></li>
                       <!-- <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Conducted</a></li>
                        <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">Lately Conducted</a></li>
                        <li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab">Not Conducted</a></li>
                        <button class="btn btn-sm btn-custom btn-success pull-right">SEARCH</button>
                        <li class="pull-right">
                        	<div class="input-group input-g">
                            	<input type="text" class="form-control">
                                <span class="input-group-addon">
                                	<i class="glyphicon glyphicon-search"></i>
                                </span>
                                
                            </div>
                        </li>
                      </ul>-->
                    
                      <!-- Tab panes -->
                      <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="home" style="margin-top:10px;"></div>
						          
                        <div role="tabpanel" class="tab-pane" id="profile">...</div>
                        <div role="tabpanel" class="tab-pane" id="messages">...</div>
                        <div role="tabpanel" class="tab-pane" id="settings">...</div>
                      </div>
                    
                    </div>
                </div>
            </div>
			<!---Start  Assembly wise Activity--->
		
		<!--- Assembly wise Activity End--->
		</div>
    </div>
	</form>
	
</div>
</div>
<div id="dialogSummaryDistsrict" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">

			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					
					 <div class="modal-header">
					  <button aria-label="Close" data-dismiss="modal" class="close" type="button"><span aria-hidden="true">x</span></button>
						<h3 class="panel-header text-center"></h3>
					  </div>
						<div id="cadreDetailsDiv" style="margin-top:25px;padding:10px;"></div>
						<center><img class="text-center" id="dataLoadingImg" src="images/Loading-data.gif" style="display:none;"/></center>
				</div>
			</div>
    </div>

	<!-- questions modal start-->
	
	<div class="modal fade" id="questionsModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog modal-lg" role="document">
		<div class="modal-content" style="border-radius:0px;">
		  <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h4 class="modal-title" id="myModalLabel">Questionnaire</h4>
		  </div>
		  <div class="modal-body">
			<div class="col-md-5" >
			  <select class="form-control" id="selectDateId">
			  </select>
		    </div>
			<div id="questionsDivBodyId"></div>
		  </div>
		  <div id="errMsg" style="color:green;margin:20px;" class="errMsgCls"></div>
		  <div class="modal-footer" id="questionsDivFooterId">
			<!--<button type="button" id="saveResult" class="btn btn-custom btn-success">Save</button>-->
		  </div>
		</div>
	  </div>
	</div>
	
	<!-- questions modal end -->
	
<div class="modal fade" id="activityCadre" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header bg_cc">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">ENTRY DETAILS</h4>
      </div>
      <div class="modal-body" style="background:#EFF3F4">
		<div class="row">
			<!--<div class="col-md-4">
				<label>Planned Date</label>
				<div class="input-group input-g1">
					<span class="input-group-addon">
						<i class="glyphicon glyphicon-calendar"></i>
					</span>
					<input type="text" class="form-control" id="plannedDate">
				</div>
			</div>-->
			<div class="col-md-4">
				<label>Select Conducted Date</label>
				<div class="input-group input-g1">
					<span class="input-group-addon">
						<i class="glyphicon glyphicon-calendar"></i>
					</span>
					<input type="text" class="form-control" id="conductedDate">
				</div>
			</div>
		</div>
       <div id="tabsId">
		  <ul class="nav nav-tabs navtabsCustom m_top20" role="tablist">
			<li role="presentation" class="active" ><a href="#cadre" id="cadreLiTabId" aria-controls="cadre" role="tab" data-toggle="tab">Cadre</a></li>
			<li role="presentation"><a href="#public" aria-controls="public" role="tab" data-toggle="tab" onclick="getBloodGroups();">Public</a></li>
			<li role="presentation"><a href="#uploadphotos" id="upldPhotosId" aria-controls="uploadphotos" role="tab" data-toggle="tab" >Upload Photos</a></li>
			<!--<li role="presentation"><a href="#questionnaire" aria-controls="questionnaire" role="tab" data-toggle="tab" onclick="getQuestionnaireDetails(4)">Questionnaire</a></li>
			<li role="presentation"><a href="#organizerQuestionnaire" aria-controls="organizerQuestionnaire" role="tab" data-toggle="tab" onclick="getQuestionnaireDetails(5)">Organizer Questionnaire</a></li>-->
		  </ul>
		  <div class="tab-content navtabsContent">
		  <!-- Cadre -->
			<div role="tabpanel" class="tab-pane active" id="cadre">
				<div class="row">
				 <div id="searchErrDiv" style="margin-left:10px;"></div>
					<div class="col-md-3">
						<label>Enter Membership No</label>
						<input type="text" class="form-control clearCadre" id="membershipId">
					</div>
					<div class="col-md-1 m_top20">
						<div class="or">OR</div>
					</div>
					<div class="col-md-3">
						<label>Voter ID</label>
						<input type="text" class="form-control clearCadre" id="voterId">
					</div>
					<div class="col-md-5 m_top20">
					  <button type="button" class="btn btn-primary" onclick="getCadreDetailsBySearchCriteria(0);">Submit</button>
					  <button type="button" class="btn btn-primary" id="cadreSaveBtn"  style="display:none;" onclick="saveAttendedCadre();">Save changes</button>
					</div>
					 
					<img src='images/icons/cadreSearch.gif' class="offset7"  id="searchDataImg" style=" margin-left: 400px;margin-top: 20px;width:250px;height:200px;display:none;"/>
				<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 m_top20">
				<div id="topPaginationDivId" class="paginationDivId"></div>
				<div class="well well-sm" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); border: medium none transparent;margin-bottom:2px;margin-top:10px;overflow:scroll:900px;display:none;" id="searchcadreDetailsDiv"></div>
				<div id="paginationDivId"  class="paginationDivId"></div>

			</div>
				</div>
			</div>
			<!-- end -->
			<div role="tabpanel" class="tab-pane" id="public">
			<div class="row">
			 <div id="searchErrDiv1" style="margin-left:10px;"></div>
					<div class="col-md-3">
						<label>Name</label>
						<input type="text" class="form-control clearPublic" id="publicNameId">
					</div>
					
					<div class="col-md-3">
						<label>Mobile</label>
						<input type="text" class="form-control clearPublic" id="publicMobileNoId">
					</div>
					
					<div class="col-md-3">
						<label>Voter Card</label>
						<input type="text" class="form-control clearPublic" id="publicVoterCardId">
					</div>
					<!--<div class="col-md-3">
						<label>blood Group</label>
						<select type="text" class="form-control" id="publicbloodGroupId"></select>
					</div>-->
					<div class="col-md-5 m_top20">
						<button type="button" class="btn btn-primary" id="publicAttndId"  onclick="saveAttendedPublic();">Save changes</button>
					</div>
			</div>
			</div>
			<div role="tabpanel" class="tab-pane uploadphotosDiv" id="uploadphotos">
				<div id="uploadInnerDiv" class="row"></div>
			</div>
			<!--<div class="row">
				<div id="uploadDiv" class="col-md-6 m_top20">
					<button type="button" class="btn btn-primary" id="getImagesBtnId" onclick="getActivityImages(0)">View Images</button>
				</div>
			</div>-->
		  </div>
		  
		  <div role="tabpanel" class="tab-pane" id="questionnaire">
				
			<div class="row">
				<div class="questionnaireCls"></div>
			</div>
		  </div>
		  <div role="tabpanel" class="tab-pane" id="organizerQuestionnaire">
				
			<div class="row">
				<div class="questionnaireCls"></div>
			 </div>
		  </div>
		</div>
      </div>
    <!--  <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" onclick="saveAttendedCadre();">Save changes</button>
      </div>-->
    </div>
  </div>
</div>						
</div>						
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title imgTitleCls myModalLabel" id="myModalLabel" ></h4>
      </div>
      <div class="modal-body">
       <div id="imagesDiv" class="row"></div>
	   <div id="paginationDiv"></div>
      </div>
      <div class="modal-footer">
        
        <button type="button" class="btn btn-primary deleteBtnCls" style="display:none">Delete</button>
      </div>
    </div>
  </div>
</div>
<!-- Modal -->
<div class="modal fade" id="upadateCallerModalDivId" style="display:none;">
			  <div class="modal-dialog">
				<div class="modal-content">
				
				  <div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="addModalTitleId" style="color:red;"><span id="addModalId"></span>Update Caller Feedback Details</h4>
				  </div>
				  
				  <div class="modal-body" id="upadateCallerModalBodyId">
					<div class="row">
					<div id="errorUpCallId" style="color:red"></div>
					<input type="hidden" id="hiddenTdpCadreId"/>
						<div class="col-md-4">
						<label>Call Purpose:</label>
						<select class="form-control" id="callPurposeId">
							</select>
						</div>
						<div class="col-md-4">
						<label>Call Status:</label>
						<select class="form-control" id="callStatusId">
							</select>
						</div>
						<div class="col-md-4">
						<label>Call Response:</label>
						<select class="form-control" id="callSupportTypId">
							</select>
						</div>
						<div class="col-md-12">
							<label>Description:</label>
							<textarea id="descriptionId" class="form-control"></textarea>
						</div>
					</div>
				  </div>
				  
				  <div class="modal-footer">
					<span class="pull-left" id="upadateCallerSuccessId"></span>
					<span id="updatefooterNameId"></span>
					<button type="button" class="btn btn-default btn-sm" id="closeButtonId" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary btn-sm" id="updateButtonId" onclick="updateCallerFeedBackDetails()">Save</button>
				  </div>
				  
				</div>
			  </div>
</div>
<script src="dist/activity/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/activity/js/custom.js" type="text/javascript"></script>
<script src="dist/activity/Date/moment.min.js" type="text/javascript"></script>
<script src="dist/activity/Date/daterangepicker.js" type="text/javascript"></script>
<script type="text/javascript" src="dragAndDropPhoto/js/jquery.filer.js?v=1.0.5"></script>
<script type="text/javascript" src="dragAndDropPhoto/js/custom-ver1.js?v=1.0.5"></script>
<script type="text/javascript" src="dragAndDropPhoto/js/uploadImage.js"></script>
<script type="text/javascript" src="js/simplePagination/simplePagination.js" ></script>
<script src="dist/newmultiselect/chosen.jquery.js" type="text/javascript"></script>
<script src="js/cadre_response.js/cadre_response.js" type="text/javascript"></script>
<script>

var actScopeId = '${activityScopeId}';
var locationValueID = '${locationValue}';
var actvityLevelId = '${activityLevel}';
var locationName = '${locationName}';

var gobalLevelId = 0;
var gobalLevelValue = 0;
var gobalActivityScopeId = 0;
var gobalLocName = "";
var gobalActivityDate = null;
var gobalTempVar = "dayCalCulationNotReq";
var gobalDay = 0;
var globalLctnInfoId = null;

$(document).ready(function(){
	//$('.searchDateCls').daterangepicker();
	$('#activityTypeList').val(1);
	$('#activityLevelList').val(2);
	//getActivityNames(2);	
	getAllActivities();
	$('.applyBtn').click(function(){
		
		/*var startDate = $("input[name=daterangepicker_start]").val();
		var endDate =  $("input[name=daterangepicker_end]").val();*/
	var startDate = "";
	var endDate = "";
	var dates=$('.searchDateCls ').val();
		if(dates != null && dates.trim().length > 0){
			var dateArray=dates.split("to");
		  startDate=dateArray[0];
		  endDate=dateArray[1];
		}
		getLocationDetailsForActivity(startDate,endDate,0,0,2);
		//alert(startDate);
	});
});
var fromTypeGlob;
$(function () {
	var cb = function(start, end, label) {
	//console.log(start.toISOString(), end.toISOString(), label);
	//$('.searchDateCls').html(start.format('D MMMM, YYYY')- + ' - ' + end.format('D MMMM, YYYY'));
	//alert("Callback has fired: [" + start.format('MMMM D, YYYY') + " to " + end.format('MMMM D, YYYY') + ", label = " + label + "]");
  }
  var optionSet1 = {
	//startDate: moment().startOf('month'),
	//endDate: moment().endOf('month'),
	showDropdowns: true,
	showWeekNumbers: true,
	timePicker: false,
	timePickerIncrement: 1,
	timePicker12Hour: true,
	ranges: {
	   'Today': [moment(), moment()],
	   //'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	   //'Last 7 Days': [moment().subtract(6, 'days'), moment()],
	   'Last 30 Days': [ moment().subtract(30, 'days'),moment()],
	   'Lat 60 Days': [moment().subtract(60, 'days'),moment()],
	   'Last 180 Days': [moment().subtract(6, 'months'),moment()],
	   'Last 365 Days': [moment().subtract(1, 'year'),moment()],
	   'This Month': [moment().startOf('month'), moment().endOf('month')],
	   //'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
	},
	opens: 'left',
	buttonClasses: ['btn btn-default'],
	applyClass: 'btn-small btn-primary',
	cancelClass: 'btn-small',
	format: 'DD/MM/YYYY',
	separator: ' to ',
	locale: {
		applyLabel: 'Submit',
		cancelLabel: 'Clear',
		fromLabel: 'From',
		toLabel: 'To',
		customRangeLabel: 'Custom',
		daysOfWeek: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr','Sa'],
		monthNames: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
		firstDay: 1
	}
  };
  //$('.searchDateCls').val(moment().format('DD/MM/YYYY') + ' - ' + moment().format('DD/MM/YYYY'));

  $('.searchDateCls').daterangepicker(optionSet1, cb);

  $('.searchDateCls').on('show.daterangepicker', function() { console.log("show event fired"); });
  $('.searchDateCls').on('hide.daterangepicker', function() { console.log("hide event fired"); });
});
/*
function getActivityNames()
{
	var jObj = {
			activityTypeId : $('#activityTypeList').val(),
			activityLevelId:$('#activityLevelList').val(),
			task:"activityDetails"
		};
		
		$.ajax({
          type:'GET',
          url: 'getLocationWiseAsOfNowDetailsAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			console.log(result);
		});
		
}
*/
function submitForm(){
	var uploadHandler = {
		upload: function(result) {
			//console.log(result);
			uploadResult = result.responseText;
			var myResult = (String)(uploadResult);
			
			if(myResult.search('success') != -1){
				//alert("Successfully Updated");
				/*var startDate = $("input[name=daterangepicker_start]").val();
				var endDate =  $("input[name=daterangepicker_end]").val();*/
			var startDate = "";
			var endDate = "";
			
			var dates=$('.searchDateCls ').val();
				if(dates != null && dates.trim().length > 0){
					var dateArray=dates.split("to");
				  startDate=dateArray[0];
				  endDate=dateArray[1];
				}
				getLocationDetailsForActivity(startDate,endDate,0,0,2);
			}else{
			}
		},
		error: function(){
			console.log('upload error');
		}
		};
	YAHOO.util.Connect.setForm('uploadInsureeDetailsForm',true);
	YAHOO.util.Connect.asyncRequest('POST','saveActivityDetailsAction.action',uploadHandler);
}

	
function getActivityNames(id)
{
	$('#mandalsList').find('option').remove();
	$('#mandalsList').append('<option value="0"> Select Mandal/Town/Division</option>');
	$('#villageWardsList').find('option').remove();
	$('#villageWardsList').append('<option value="0"> Select Mandal/Town/Division</option>');
	
	$("#constituencyDivId").hide();
	$("#mandalDivId").hide();
	$("#panchayatDivId").hide();
	$("#districtDivId").hide();
	$("#constiList").val(0);
	$('#ActivityList').find('option').remove();
	$('#ActivityList').append('<option value="0"> Select Activity </option>');	
	
	var activityLevelId = id;
	if(id == 0)
		activityLevelId = $('#activityLevelList').val();
	
	if($("#activityTypeList").val()!=4){
		if(activityLevelId == 1){
		$("#constituencyDivId").show();
		$("#mandalDivId").show();
		$("#panchayatDivId").show();
		}
		else if(activityLevelId == 2){
			$("#constituencyDivId").show();
			$("#mandalDivId").show();
		}else if(activityLevelId == 5){
			$("#districtDivId").show();
		}
		else if(activityLevelId == 3 ){
			$("#districtDivId").show();
		}
	}
	
	
	var jObj = {
			activityTypeId : $('#activityTypeList').val(),
			activityLevelId:id,//$('#activityLevelList').val(),
			task:"activityDetails"
		};
		
		$.ajax({
          type:'GET',
          url: 'getActivityDetails.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){			
			if(result != null && result.length >0)
			{
				for(var i in result)
					$('#ActivityList').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');	
				
				$('#ActivityList').val(16);
				$('#ActivityList').trigger('change');
			}
		});
		
}


function getUserAccessConstituencyList()
{
	$('#constiList').find('option').remove();
	$('#constiList').append('<option value="0"> Select Constituency</option>');	
	var jObj = {
			task:"getUserAccessConstituencyList",
			stateId:1
		};
		
		$.ajax({
          type:'GET',
          url: 'getUserAccessListConstituency.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			
			if(result != null && result.hamletVoterInfo != null && result.hamletVoterInfo.length >0)
			{
				for(var i in result.hamletVoterInfo)
					$('#constiList').append('<option value="'+result.hamletVoterInfo[i].id+'">'+result.hamletVoterInfo[i].name+'</option>');
			}
		});
		
}
function getUserAccessDistrictList()
{
	$('#districtList').find('option').remove();
	//$('#districtList').append('<option value="0"> Select District</option>');	
	var jObj = {
		};
		
		$.ajax({
          type:'GET',
          url: 'getUserAccessDistrictListAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			
			if(result != null && result.length >0)
			{
				$('#districtList').append('<option value="0"> All </option>');
				for(var i in result)
					$('#districtList').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
			}
		});
		
}

function getMunciMandalsList(constituencyId)
{
	$('#mandalsList').find('option').remove();
	$('#mandalsList').append('<option value="0"> Select Mandal/Town/Division</option>');
	$('#villageWardsList').find('option').remove();
	$('#villageWardsList').append('<option value="0"> Select Mandal/Town/Division</option>');
	var jObj = {
			task:"getUserAccessConstituencyList",
			locationId:constituencyId
		};
		$("#procesingImg").show();
		$.ajax({
          type:'GET',
          url: 'getMandalCorporationsByConstituencyAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			
			if(result != null && result.length >0)
			{
				$("#procesingImg").hide();
				for(var i in result)
					$('#mandalsList').append('<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>');
			}
		});
		
}

function getPanchayatWardByMandal(mandalId){
		     $('#villageWardsList').find('option').remove();
			 $('#villageWardsList').append('<option value="0"> Select Mandal/Town/Division</option>');	
			var jsObj={
				mandalId:mandalId,
				constituencyId : $("#constiList").val()
			}
			$.ajax({
				type : "POST",
				url : "getPanchayatWardByMandalAction.action",
				data : {task:JSON.stringify(jsObj)} 
			}).done(function(result){
				
				if(result != null && result.length >0)
				{
					for(var i in result)
						$('#villageWardsList').append('<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>');
				}
		});	
			
	}
	
function buildingResults(result,locationName){
	 
	var str = '';
	
			str+='<table class="table table-bordered" id="constiTableId">';
		str+='<thead>';
		str+='<th style="width:50px;"> </th>';
		str+='<th style="padding-left: 72px;"> MEMBER </th>';
		str+='<th style="padding-left: 19px;"> MOBILE NO </th>';
		str+='<th style="padding-left: 19px;"> AGE </th>';
		str+='<th style="padding-left: 19px;"> GENDER </th>';
		str+='<th style="padding-left: 19px;"> CASTE NAME </th>';
		str+='<th style="padding-left: 19px;"> Update Mobile No </th>';
		//str+='<th style="padding-left: 19px;"> Caller FeedBack</th>';
		str+='</thead>';
		for(var i in result){
		 str+='<tr>';
		str+='<td><img  style="margin-top: 5px;" width="50"  height="50" src="https://www.mytdp.com/images/cadre_images/'+result[i].imagePath+'" onerror="setDefaultImage(this);"/>';
		
		 str+=' </td>';
		 str+='<td> '+result[i].name+' ';
		 //if(basicCmmtyId != 1){
		 if(result[i].commiteeName!=null){
			 str+='<br>'+result[i].commiteeName+' - ';
		 }else{
			 str+='<br>';
		 }
		/* }
		else{
			 str+='<br>';
		 }*/
		 
		  //if(basicCmmtyId == 1){
		 if(result[i].role!=null){
			 str+=' '+result[i].role+'';
		 }else{
			 str+='';
		 }
		str+=' <br/> <span> Constituency : '+result[i].constituencyName+' </span>';
		str+=' <br/> <span> MemberShipNo : '+result[i].id+' </span>';
		//str+=' <br/> <span> MemberShipNo : <a target="_blank" href="cadreDetailsAction.action?cadreId='+result[i].id+'"> '+result[i].id+' </a> </span>';
		  str+=' </td>';	  
		str+='<td style="padding-left: 15px; padding-top: 13px;">'+result[i].mobileNo+'</td>';
		 str+='<td style="padding-left: 15px; padding-top: 13px;">'+result[i].age+' </td>';
		 str+='<td style="padding-left: 15px; padding-top: 13px;"> '+result[i].gender+' </td>';
		 str+='<td style="padding-left: 15px; padding-top: 13px;"> '+result[i].casteName+'('+result[i].casteGroupName+') </td>';
		 // str+='<td style="padding-left: 15px; padding-top: 13px;"> '+result[i].voterCardNo+' </td>';
		 str+='<td><input type="button" class="btn btn-custom btn-success" value="Update Mobile No" id="editBtnId'+i+'" onclick="showHide('+i+');" name="Edit">';
		 str+='</br>'
		 str+='<div id="errMobileId'+i+'" style="color:red"></div>';
		 str+='<input type="text" class="m_top10" disabled maxlength="10" id="updatemobileNo'+i+'" value="'+result[i].mobileNo+'" />';
		 str+='</br>'
		 str+='<input type="button" class="btn btn-custom btn-success m_top10" name="save" onclick="updateMobileNumber(\''+i+'\',\''+result[i].id+'\')" id="saveBtnId'+i+'" style="display:none" value="Save Mobile No" />';
		 str+='<div id="successMobileId'+i+'" style="color:green"></div>';
		 str+='</td>';
		 //str+='<td ><div class="updateBtnCls" attr_tdp_cadre_id="'+result[i].id+'"><i class="glyphicon glyphicon-ok pull-right" style="padding:4px;border-radius:50%;background:#ccc;color:#FFFFFF;color:green;right:25px;cursor:pointer"></i></div></td>';
		 str+='</tr>';
		 
		}
	   str+='</tbody>';
	   str+='</table>';
	
	$("#dataLoadingImg").hide();
	$("#cadreDetailsDiv").html(str);
	
}

function showHide(index){
	
	$("#saveBtnId"+index).show();
	$('#updatemobileNo'+index).removeAttr('disabled');
	$("#editBtnId"+index).hide();
}

function updateMobileNumber(index,tdpCadreId){
	
	$("#successMobileId"+index).html("");
	$("#errMobileId"+index).html("");
	
	var mobileNo = $("#updatemobileNo"+index).val();
	if(isNaN(mobileNo) || mobileNo.trim().length != 10){
		$("#errMobileId"+index).html("Enter Valid Number");
		return;
	}
	
	var jObj={
		tdpCadreId:tdpCadreId,
		mobileNo:mobileNo
	};
	$.ajax({
	  type:'POST',
	  url: 'updateMobileNumberForCadreAction.action',
	  dataType: 'json',
	  data: {task:JSON.stringify(jObj)},
	  }).done(function(result){
			if(result != null){
				if(result.resultCode == 0){
					if(result.message == "SUCCESS"){
						$("#successMobileId"+index).html("Mobile Number Is Successfully Updated...");
					}else{
						$("#errMobileId"+index).html("Invalid Mobile Number...");
					}
				}else{
					$("#errMobileId"+index).html("Sorry,Mobile Number Is Not Updated...");
				}
			}
	  });
}

function getLocationDetailsForActivity(startDate,endDate,optionId,questionId,searchById,datesArr)
{
	if(searchById == 1){
		$('#questionsId,#questionsForOptionsId').val(0);
	}
	getActivityStatusDetailsByScopeIdAndLocationValue();
	$("#questionsDiv").show();
	$("#activityresponsesDiv").show();
	var activityTypeId =$('#activityTypeList').val();
	var activityLevelId =$('#activityLevelList').val();
	var ActivityId =$('#ActivityList').val();
	var constituencyId =$('#constiList').val();
	var districtId =$('#districtList').val();
	$('#ErrDiv').html("");
	var errStr ='';
	
	if(ActivityId == null || ActivityId == 0)
	{
		errStr+="Please Select Activity .";
	}
	else if(activityLevelId == null || activityLevelId == 0)
	{
		errStr+="Please Select Activity Level.";
	}	
	else if(districtId == null || districtId == 0)
	{
		if(activityLevelId == 5 || activityLevelId == 3){
		errStr+="Please Select District.";
		}
	}
	else if(constituencyId == null || constituencyId == 0)
	{
		if(activityLevelId == 1 || activityLevelId == 2){
		errStr+="Please Select Constituency.";
		}
	}
	
	/*if(errStr!= null && errStr.length>0){
		//$('#ErrDiv').html(errStr);
		//return;
	}
	else
	{*/
		/*if(activityLevelId == 3){
		$('#resultsDiv').hide();
		}else{*/
			$('#resultsDiv').show();
		//}		
		$('#home').html("<img src='images/Loading-data.gif'/>");	
			if(startDate.trim().length == 0)
			{
				/*startDate = $("input[name=daterangepicker_start]").val();
				endDate =  $("input[name=daterangepicker_end]").val();*/
				var dates=$('.searchDateCls ').val();
				if(dates != null && dates.trim().length > 0){
					var dateArray=dates.split("to");
				  startDate=dateArray[0];
				  endDate=dateArray[1];
				}
			}	
			
			var searchBy="Panchayat";
			var locationId = $('#villageWardsList').val();	
			if(locationId == 0)
			{
				locationId = $('#mandalsList').val();
				searchBy = "mandal";
				
				 if(locationId == 0)
				{
					locationId = $('#constiList').val();
					searchBy = "Constituency";
					if(locationId == 0)
				{
					locationId = $('#districtList').val();
					searchBy = "District";
				}
				}
				
			}
			
			var value = "all";
			if($("#all").is(':checked'))
			{
				value = "all";
			}
			else{
				if($("#notConductedId").is(':checked'))
				value = "notConducted";
			if($("#conductedId").is(':checked'))
				value = "conducted";
			}
			
			var activityScopeId = $("#activityLevelList option:selected").attr("attr_scopeId");
			
			var jObj = {
				startDate:startDate,
				endDate:endDate,
				checkedValue:value,
				activityScopeId:activityScopeId,
				activityLevelId:activityLevelId,
				searchBy:searchBy,
				locationId:locationId,
				task:"getLocationDetailsForActivity",
				constituencyId : constituencyId,
				optionId : optionId,
				questionId : questionId,
				datesArr:datesArr
			};		
			$.ajax({
				  type:'GET',
				  url: 'getLocationDetailsForActivityNew.action',
				 data : {task:JSON.stringify(jObj)} ,
			 }).done(function(result){	
					var str='';
					if( result!= null)
					{
						$("#buildAssConsActivity").hide();
						$("#hideAsmblyData").hide();
						$("#showAsmblyData").show();
						str+='<table class="table table-bordered bg_ff" id="locationsTab">';
						str+='<thead>';
						str+='<tr>';
						//str+='<th>CONSTITUENCY</th>';
								if(activityLevelId == 2)
								str+='<th style="background-color:#00B17D; color:#fff;">MANDAL/ TOWN/ DIVISION</th>';
							else if(activityLevelId == 1)					
								str+='<th style="background-color:#00B17D; color:#fff;">PANCHAYAT/ WARD</th>';
							else if(activityLevelId == 5)
								str+='<th style="background-color:#00B17D; color:#fff;">CONSTITUENCY</th>';
							else if(activityLevelId == 4)
								str+='<th style="background-color:#00B17D; color:#fff;">STATE</th>';
							else if(activityLevelId == 3)
								str+='<th style="background-color:#00B17D; color:#fff;">DISTRICT</th>';
							
							str+='<th style="background-color:#00B17D; color:#fff;" >Committee Members</th>';	
							str+='<th style="background-color:#00B17D; color:#fff;"></th>';
										
									if(result !=null && result.idNameVolist !=null && result.idNameVolist.length>0){
										for(var i in result.idNameVolist){
											str+='<th style="background-color:#00B17D; color:#fff;" id='+result.idNameVolist[i].id+'>'+result.idNameVolist[i].name.toUpperCase()+'</th>';
										}								
									}		
							//str+='<th style="background-color:#00B17D; color:#fff;" >Committee Members</th>';
							str+='</thead>';
							str+='</tr>';
							
							if(result.result !=null && result.result.length>0){
								
								for(var j in result.result){
								
									for(var k in result.result[j].result2)	
									{
									str+='<tr>';
									
									str+='<input type="hidden" value="'+activityLevelId+'" name="activityVO.activityVoList['+j+'].locationLevel">';
									str+='<input type="hidden" value="'+result.result[j].locationId+'" name="activityVO.activityVoList['+j+'].locationValue">';
									str+='<td> '+result.result[j].locationName+'</td>';
									str+='<td>';
												str+='<input type="button" value="View" class="btn btn-success btn-xs" onclick="gettingCadreDetails('+result.result[i].locationId+',\''+result.result[i].locationName+'\',\''+constituencyId+'\');"/>&nbsp;&nbsp;';
									str+='</td>';
									str+='<td  style="text-align:center;">'+result.result[j].result2[k].day+'</td>';
									var date =result.result[j].result2[k].day;
									var daystr = date.split("(");
									
									var dayNum = daystr[0].replace( /^\D+/g, '');
									
									if(result !=null && result.idNameVolist !=null && result.idNameVolist.length>0){
										for(var i in result.idNameVolist){
											if(result.idNameVolist[i].name == "Planned Date" ){
													str+='<td  style="text-align:center;width:180px">';
													str+='<div class="input-g1 input-group">';
														str+='<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>';
														if(result.result[j].planedDate != null)
															str+='<input type="text" class="dateCls form-control"  name="activityVO.activityVoList['+j+'].plannedDate" value="'+result.result[j].planedDate+'" id="plandateId'+result.result[j].locationId+'"/>';
														else
															str+='<input type="text" class="dateCls form-control"  name="activityVO.activityVoList['+j+'].plannedDate" value="" id="plandateId'+result.result[j].locationId+'"/>';
													
													str+='<span class="input-group-addon" style="padding:6px 0px;" title="Click here to update Date Details."><i class="glyphicon glyphicon-ok updateDateDetls" attr_location_Value="'+result.result[j].locationId+'" attr_plan_date="plandateId'+result.result[j].locationId+'" attr_date="dateId'+result.result[j].locationId+'"  style="cursor:pointer;" attr_day='+dayNum+' attr_img="img'+result.result[j].locationId+'"></i></span>';
													
													str+='</div>  </td>';
											}
											
											if(result.idNameVolist[i].name == "Conducted Date" ){
											if(activityScopeId != 16){
												str+='<td  style="text-align:center;width:180px">';
												str+='<div class="input-g1 input-group">';
													str+='<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>';
													if(result.result[j].conductedDate != null)
														str+='<input type="text" id="dateId'+result.result[j].locationId+'" class="dateCls conductedDtCls form-control" name="activityVO.activityVoList['+j+'].conductedDate" value="'+result.result[j].conductedDate+'"/>';
													else
														str+='<input type="text" id="dateId'+result.result[j].locationId+'" class="dateCls conductedDtCls form-control" name="activityVO.activityVoList['+j+'].conductedDate" value=""/>';
													str+='<span class="input-group-addon" style="padding:6px 0px;" title="Click here to update Date Details."><i class="glyphicon glyphicon-ok updateDateDetls" attr_location_Value="'+result.result[j].locationId+'" attr_plan_date="plandateId'+result.result[j].locationId+'" attr_date="dateId'+result.result[j].locationId+'"  style="cursor:pointer;" attr_day='+dayNum+' attr_img="img'+result.result[j].locationId+'"></i></span>';
												str+='</div>';
											}
												str+='<div id="errdateId'+result.result[j].locationId+'" class="errCls"  style="color:red;"></div> </td>';
											}
											
											if(result.idNameVolist[i].name == "Upload Images"){	
												str+='<td style="text-align:center;padding-left:0px;padding-right:0px;">';
												

													if(result.result[j].isAlreadyImageUpload == "true"){								
														str+='<img attr_location_Value="'+result.result[j].locationId+'" attr_location_Name=\''+result.result[j].locationName+'\' id="uploadImagesId" style="position:absolute;width: 40px; height: 40px; margin-left: 10px;background:#FFA500;" src="images/ImageUpload1.png"  title="Upload Images" attr_date="dateId'+result.result[j].locationId+'" />';
													}else{
														str+='<img attr_location_Value="'+result.result[j].locationId+'" attr_location_Name=\''+result.result[j].locationName+'\' id="uploadImagesId" style="position:absolute;width: 40px; height: 40px; margin-left: 10px;" src="images/imageUpload.png"  title="Upload Images" attr_date="dateId'+result.result[j].locationId+'" />';
													} 
												
												str+='</td>';												
											}			
											if(result.idNameVolist[i].name == "Questionnaire"){
												str+='<td>';
												str+='<input type="button" value="Update Questionnaire" attr_location_Value="'+result.result[j].locationId+'" attr_location_Name=\''+result.result[j].locationName+'\' class="btn btn-success btn-xs" id="updateQBtnId" attr_date="dateId'+result.result[j].locationId+'" attr_Selected_day="'+result.result[j].result2[k].day+'"/>';
												str+='</td>';
											}											
										}
									}
									/*str+='<td>';
												str+='<input type="button" value="View" class="btn btn-success btn-xs" onclick="gettingCadreDetails('+result.result[i].locationId+',\''+result.result[i].locationName+'\',\''+constituencyId+'\');"/>&nbsp;&nbsp;';
									str+='</td>';*/
									str+='</tr>';
								}
							}
							}
						
						str+='</table>';
					 
					}
					$('#home').html(str);
					
					$('#plannedDate').daterangepicker({singleDatePicker:true,format: 'DD/MM/YYYY'});
					$('#conductedDate').daterangepicker({singleDatePicker:true,format: 'DD/MM/YYYY'});
					
					$('.dateCls').daterangepicker({singleDatePicker:true,format: 'DD/MM/YYYY'});
					
					$("#locationsTab").dataTable({
					"iDisplayLength": 10,
					"aLengthMenu": [[10,20, 50, 100, -1], [10,20, 50, 100, "All"]]
					});
					$("#locationsTab").removeClass("dataTable");
					$("#constncyId").html(''+$("#constiList option:selected").text()+' constituency ');
					$('#headingId').html(' '+$("#activityLevelList option:selected").text()+' - '+$("#ActivityList option:selected").text()+'');
				});	
}


	$(document).on("click",".activityCadrePopup",function(){
	
		$("#cadreSaveBtn").hide();
		$("#conductedDate").val("");
		$("#membershipId").val("");
		$("#voterId").val("");
		$("#searchcadreDetailsDiv").html("");
		$("#searchcadreDetailsDiv").hide();
		$("#publicNameId").val("");
		$("#publicMobileNoId").val("");
		$("#publicVoterCardId").val("");
		$("#uploadInnerDiv").html("");
		
		gobalLevelValue = $(this).attr("attr_location_Value");
		gobalLocName  = $(this).attr("attr_location_name");
		var actvty_lctn_info_id = $(this).attr("actvty_lctn_info_id");
		globalLctnInfoId = actvty_lctn_info_id;
		//getRequiredAttributesByActScopeId();
		$("#cadreSaveBtn").attr("actvty_lctn_info_id",actvty_lctn_info_id);
		$("#publicAttndId").attr("actvty_lctn_info_id",actvty_lctn_info_id);
		
		$("#activityCadre").modal("show")
		//$(".jFiler-row li").html('');
		
	});

function gettingCadreDetails(locationId,locationName,constituencyId){	
	
	$("#cadreDetailsDiv").html('');
	
	$('#dialogSummaryDistsrict').find('h3').html('<span>'+locationName+'  '+$("#activityLevelList option:selected").text()+' Main Committee Members </span>');
	$("#dialogSummaryDistsrict").modal("show");
	$("#dataLoadingImg").show();
	
	locationId = ""+locationId+"";
	var firstChar = locationId.substr(0,1);
	//console.log(firstChar);
	 locationId = locationId.slice(1);
	 
	 
	var locationTypeId = $('#activityLevelList').val();
	var activityLevellId = $('#activityLevelList').val();
	
	var locationType = 5;
	if(activityLevellId == 2)
	{
		if(firstChar == 2)
			locationType = 5;
		else if(firstChar == 3)
			locationType = 7;
		else if(firstChar == 1)
			locationType = 9;	
	}
	else if(activityLevellId == 1)
	{
		if(firstChar == 1)
			locationType = 6;
		else if(firstChar == 2)
			locationType = 8;
	}
	
		 var jsObj={
		         locationId:locationId,locationType:locationType,basicCommitteeTypeId:1,constituencyId:constituencyId
		       };
			   
		 $.ajax({
			type : "GET",
			url : "getComitteeMembersInfoInActivityAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(typeof result == "string"){
				if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
				  location.reload(); 
				}
			}
			if(result != null && result.length > 0){
				buildingResults(result,locationName);
			}else{
				$("#dataLoadingImg").hide();
				$("#cadreDetailsDiv").html('NO DATA AVAILABLE...');
			}
		});
	}
	
getUserAccessConstituencyList();
getUserAccessDistrictList();
 
 $("#ActivityList").change(function(){
 var ActivityId =$('#ActivityList').val(); 
 if(ActivityId == 17){
		getActivityDatesByScopeId();
		return;
	}
	/*
	var activityTypeId =$('#activityTypeList').val();
	var activityLevelId =$('#activityLevelList').val();
	
	var districtId =$('#districtList').val();
	
	
	
	getQuestions();
	getActivityDatesByScopeId();
	//$('#ErrDiv').html("");
	var errStr ='';
	
	if(activityLevelId == null || activityLevelId == 0)
	{
		errStr+="Please Select Activity Level.";
	}
	else if(ActivityId == null || ActivityId == 0)
	{
		errStr+="Please Select Activity .";
	}
	
	
	if(errStr!= null && errStr.length>0){
		$('#ErrDiv').html(errStr);
		return;
	}
	 $("#assemblydivId").show();
	// $("#districtHeadingId").hide();
	//$("#constituencyHeadingId").hide();
   //$('#assemblydivId').show();
	$("#buildAssConsActivity").html("<img style='margin-left: 390px;' src='images/Loading-data.gif'/>");
	var startDate = "";
	var endDate = "";
	var levelId = $("#activityLevelList option:selected").val();
	
	var dates=$('.searchDateCls ').val();
		if(dates != null && dates.trim().length > 0){
			var dateArray=dates.split("to");
		  startDate=dateArray[0];
		  endDate=dateArray[1];
		}
	
    var jsObj={   startDate:startDate,    //$("input[name=daterangepicker_start]").val(),
                  endDate:endDate,     //$("input[name=daterangepicker_end]").val(),
                  activityScopeId:$('#ActivityList').val(),
                  activityLevelId:$("#activityLevelList option:selected").val(),
				  levelId:levelId,
                  stateId:1
              };
      $.ajax({
      type : "GET",
      url : "asemblyConstWiseActivitiesAction.action",
      dataType: 'json',
      data: {task:JSON.stringify(jsObj)}
    }).done(function(result){
		 $('#assemblydivId').show();
       if(result!=null && result.length>0){
		   $("#hideAsmblyData").show();
		   $("#showAsmblyData").hide();
		   if(levelId == 3){
				$("#districtHeadingId").show();
				$("#constituencyHeadingId").hide();
				 $("#districtHeadingId").html('DISTRICT WISE ACTIVITIES  '+$("#activityLevelList option:selected").text()+' - '+$("#ActivityList option:selected").text()+'');
		   }else{
			   $("#constituencyHeadingId").html('ASSEMBLY CONSTITUENCY WISE ACTIVITIES  '+$("#activityLevelList option:selected").text()+' - '+$("#ActivityList option:selected").text()+'');
		        $("#constituencyHeadingId").show();
				$("#districtHeadingId").hide();
		   }
			buildAsemblyConstWiseActivities(result,levelId);
			
       }else{
		   $("#constituencyHeadingId").html('ASSEMBLY CONSTITUENCY WISE ACTIVITIES  '+$("#activityLevelList option:selected").text()+' - '+$("#ActivityList option:selected").text()+'');
		   $("#constituencyHeadingId").hide();
		   $("#districtHeadingId").hide();
		   $("#hideAsmblyData").hide();
		    $("#showAsmblyData").hide();
         $("#buildAssConsActivity").html("NO DATA AVAILABLE...");
       }
	  
		 if(activityTypeId == 4){
			getActivityLocationWiseDetails();
			$("#bloodDonationDetails").show();
			$("#buildAssConsActivity").hide();
			}
			else{
				$("#bloodDonationDetails").hide();
				$("#buildAssConsActivity").show();
			}
    });   */
   });
   
$("#showAsmblyData").click(function(){
	 $("#buildAssConsActivity").show();
	 $("#assblyBody").show();
	 $("#showAsmblyData").hide();
	 $("#hideAsmblyData").show();
});
$("#hideAsmblyData").click(function(){
	 $("#buildAssConsActivity").hide();
	 $("#assblyBody").hide();
	 $("#showAsmblyData").show();
	 $("#hideAsmblyData").hide();
});
  function buildAsemblyConstWiseActivities(result,levelId){
   
	if(levelId == 3){
		$("#districtHeadingId").show();
	}
		
	else{
		$("#constituencyHeadingId").show();
	}
	 
    var str ='';
    str+='<table class="table table-bordered table-responsive bg_ff dataTableDiv">';
          str+='<thead>';
            str+='<tr role="row">';
			if(levelId == 3)
				str+='<th style="background-color: rgb(0, 177, 125); color:#fff;cursor:pointer;" class="text_center"> DISTRICT </th>';
			else
				str+='<th style="background-color: rgb(0, 177, 125); color:#fff;cursor:pointer;" class="text_center">ASSEMBLY CONSTITUENCY </th>';
              //str+='<th style="background-color: rgb(0, 177, 125); color:#fff;cursor:pointer;" class="text_center">ASSEMBLY CONSTITUENCY </th>';
              str+='<th style="background-color: rgb(0, 177, 125); color:#fff;cursor:pointer;" class="text_center">TOTAL ACTIVITIES</th>';
              str+='<th style="background-color: rgb(0, 177, 125); color:#fff;cursor:pointer;" class="text_center" >PLANNED ACTIVITIES</th>';
			  str+='<th style="background-color: rgb(0, 177, 125); color:#fff;cursor:pointer;"  class="text_center">NOT PLANNED ACTIVITIES</th>';
              str+='<th style="background-color: rgb(0, 177, 125); color:#fff;cursor:pointer;"  class="text_center"> CONDUCTED ACTIVITIES</th>';
			  str+='<th style="background-color: rgb(0, 177, 125); color:#fff;cursor:pointer;" class="text_center" >NOT EXECUTED ACTIVITIES</th>';
            str+='</tr>';
          str+='</thead>';
        str+='<tbody>';
          for(var i in result){
            str+='<tr class="text_center">';
              
                str+='<td >'+result[i].name+'</td>';
             
              if(result[i].totalCount !=null){
                str+='<td >'+result[i].totalCount+'</td>';
              }else{
                str+='<td> 0 </td>';
              }
              if(result[i].plannedCount !=null){
                str+='<td >'+result[i].plannedCount+'</td>';
              }else{
                str+='<td > 0 </td>';
              }
			  if(result[i].notPlannedCount !=null){
                str+='<td >'+result[i].notPlannedCount+'</td>';
              }else{
                str+='<td > 0 </td>';
              }
              if(result[i].conductedCount !=null){
                str+='<td >'+result[i].conductedCount+'</td>';
              }else{
                str+='<td > 0 </td>';
              }
			  if(result[i].nonConductedCount !=null){
                str+='<td >'+result[i].nonConductedCount+'</td>';
              }else{
                str+='<td > 0 </td>';
              }
              str+='</tr>';
          }
        
        str+='</tbody>';
    str+='</table>';
    
    $("#buildAssConsActivity").html(str);
   
     $(".dataTableDiv").dataTable({
		"iDisplayLength": 10,
		"aLengthMenu": [[10, 20, 30, -1], [10, 20, 30, "All"]]
	 });
	  $(".dataTableDiv").removeClass("dataTable");
	 // $("#buildAssConsActivity").hide();
  }
      $(document).on("change",".selectedVal",function(){
		var serialNoTypeId=['a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'];  
		$(".errMsgCls").html("");
		var optionId=$(this).val();
		var questionId=$(this).attr("attr_qid");
		var subQustionDivId =$(this).attr("subQustionDivId");
		var locationValue = $(this).attr("attr_location_Value");
		/* if(optionId >0)
			getQuestionnaire(locationValue,questionId,optionId,subQustionDivId,serialNoTypeId,0); */
		
	});
  
	
	$(document).on("click","#updateQBtnId",function(){
		var dateFieldId = $(this).attr('attr_date');
		/*$(".errCls").html("");
		$("#err"+dateVal).html("");
		if($("#"+dateVal).val() == ""){
			$("#err"+dateVal).html("Date Required");
			return;
		}*/
		var locationValue = $(this).attr("attr_location_Value");
		var d = $(this).attr("attr_Selected_day");
		var selectedDay = d.substring(4,5); 
		getQuestionnaire(locationValue,0,0,'questionsDivBodyId',1,dateFieldId,selectedDay);
		
 	});
function getQuestionnaire(locationValue,questionId,optionId,divId,serialNoTypeId,dateFieldId,selectedDay){
		//console.log(serialNoTypeId[0]);
		$(".errMsgCls").html("");
			$("#errMsg").html("");
		$("#"+divId+"").html('');
		var scopeId = $("#ActivityList").val();
		if(scopeId==null || scopeId==0){
			alert("Please Select Activity Name");
			return false;
		} 
		
		var jsObj={   
				scopeId : scopeId,
				requiredAttributeId:0,
				questionId:questionId,
				optionId:optionId,
				selectedDay:selectedDay
            };
       
			$.ajax({
				type : "GET",
				url : "getQuestionnaireForScopeAction.action",
				dataType: 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				$("#questionsModal").modal("show");
				var str='';
				if(result!=null && result.activityVoList!=null && result.activityVoList.length>0){
					for(var i in result.activityVoList){
						str+='<div class="row" style="margin-left: 0px;">';
						str+='<div class="col-md-12 m_top10">';
						
						
						if(divId!="questionsDivBodyId"){
							str+='<label>'+serialNoTypeId[i]+''+result.activityVoList[i].question+' ? </label><br/>';
						}else{
							str+='<label>'+result.activityVoList[i].question+'  </label><br/>';
						}
						str+='</div>';
						str+='<div class="col-md-4">';

						if(result.activityVoList[i].optionsList!=null && result.activityVoList[i].optionsList.length>0){
							if(result.activityVoList[i].optionTypeId==1){
								str+='<select class="form-control selectedVal" attr_option_type_id="'+result.activityVoList[i].optionTypeId+'" attr_type="selectbox" attr_qid="'+result.activityVoList[i].questionId+'"';
								if(result.activityVoList[i].remarks=="true")
									str+=' ramarkFieldId="remark'+result.activityVoList[i].questionId+'" ';
								else
									str+=' ramarkFieldId="0" ';
								str+=' subQustionDivId="questionId'+result.activityVoList[i].questionId+'" attr_location_Value="'+locationValue+'">';
								str+='<option value="0">Select Option </option>';
								for(var j in result.activityVoList[i].optionsList){
									str+='<option value="'+result.activityVoList[i].optionsList[j].optionId+'">'+result.activityVoList[i].optionsList[j].option+'</option>';
								}
								str+='</select>';
							}
							else if(result.activityVoList[i].optionTypeId==2){
								for(var j in result.activityVoList[i].optionsList){
									str+='&nbsp;&nbsp;<label><input type="checkbox" attr_type="checkBox" name="result'+result.activityVoList[i].questionId+'" class="selectedVal"  attr_option_type_id="'+result.activityVoList[i].optionTypeId+'" attr_qid="'+result.activityVoList[i].questionId+'" value="'+result.activityVoList[i].optionsList[j].optionId+'" attr_location_Value="'+locationValue+'"/>&nbsp;&nbsp;'+result.activityVoList[i].optionsList[j].option+'</label>';
								}
							}
							else if(result.activityVoList[i].optionTypeId==3){
									str+='&nbsp;&nbsp;<label><input type="text" attr_type="textBox" name="result'+result.activityVoList[i].questionId+'" class="selectedVal"   attr_option_type_id="'+result.activityVoList[i].optionTypeId+'" attr_qid="'+result.activityVoList[i].questionId+'" attr_location_Value="'+locationValue+'"/></label>';
							}
							else if(result.activityVoList[i].optionTypeId==4){
									str+='&nbsp;&nbsp;<label><input type="text" attr_type="countTextBox"  name="result'+result.activityVoList[i].questionId+'" class="selectedVal"   attr_option_type_id="'+result.activityVoList[i].optionTypeId+'" attr_qid="'+result.activityVoList[i].questionId+'" attr_location_Value="'+locationValue+'"/></label>';
							}
						} 
						if(result.activityVoList[i].remarks=="true"){
							   str+='&nbsp;&nbsp;<br><input type="text" rows="2" cols="10" maxlength="200" name="result'+result.activityVoList[i].questionId+'" placeholder="Enter total count " class="remarksCls form-control" attr_qid="'+result.activityVoList[i].questionId+'" id="remark'+result.activityVoList[i].questionId+'"/>';
						    }
						str+='</div>';
							str+='<div id="questionId'+result.activityVoList[i].questionId+'" ></div>';
						str+='</div>';
					}
					if(dateFieldId == 0)
						str+='';
					else
						$("#questionsDivFooterId").html('<button type="button" id="saveResult" class="btn btn-custom btn-success" attr_location_Value="'+locationValue+'" attr_date_field_id="'+dateFieldId+'">Save</button>');
				}else if(serialNoTypeId ==1){
					str+='<h4>No Data Found.</h4>';
				}
				$("#"+divId+"").html(str);
				});
			getSelectedBetweenDatesOfActivityScope();
			
	}
	
	$(document).on("click","#saveResult",function(){
		var day = $("#selectDateId").val();
		var resultArr=[];
		$(".selectedVal").each(function(){
		var value = '';
		var remarks='0';
		var count=0;
		if($(this).attr("attr_type")=="selectbox" && $(this).val()>0){
			var key=$(this).attr("attr_qid");
			value=$(this).val();
			var ramarkFieldKey = $(this).attr("ramarkId");
			if(ramarkFieldKey != 0)
				remarks = $("#remark"+key+"").val();			
		}
			/*if($(this).attr("attr_type")=="checkBox"){
				if(this.checked)
					value = this.value;			
			}*/		
		else if($(this).attr("attr_type")=="countTextBox" && $(this).val()>0){
			count = $(this).val();
			value = "3";
		}
		else{
			remarks = $(this).val();
			value = "3";
		} 
		     if(remarks==undefined || remarks==" "){
				 remarks='0';
			 }
			if(value != null && value.length>0)
			{
				
				var obj={
				questionId : $(this).attr("attr_qid"),
				optionId : value,
				optionTypeId:$(this).attr("attr_option_type_id"),
				remarks: remarks,
				count:count,
				others:" "
				};
				resultArr.push(obj);
			}	
				
		});
		
		var dateFieldId = $(this).attr('attr_date_field_id');
		if(resultArr != null && resultArr.length>0){
			 var jsObj={
		         activityScopeId : $("#ActivityList").val(),
				 activityLevelId : $("#activityLevelList").val(),
				 activityLevelValue : $(this).attr("attr_location_Value"),
				 responseArray : resultArr,
				 //conductedDate : $("#"+dateFieldId+"").val()
				 conductedDate:'',
				 day : day
				
		       };
			  
			 $.ajax({
				type : "GET",
				url : "saveActivityQuestionnaireDetailsAction.action",
				dataType: 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				if(result != null && result.resultCode == 0){
					$(".errMsgCls").html("Question Saved Successfully");
					
					setTimeout(function(){$(".errMsgCls").html("");$("#questionsModal").modal("hide");},2000);
				}else{
					$(".errMsgCls").html("Exception Occurred try Again")
				}
				//console.log(result);
			});
		}else{
			$(".errMsgCls").html("<b class='text-danger'>Please Answer atleast one question.</b>");
		}
		
		//console.log(resultArr);
	});
	
	$(document).on("click","#uploadImagesId",function(){
		 var dateVal = $(this).attr('attr_date');
		/*$(".errCls").html("");
		$("#err"+dateVal).html("");
		if($("#"+dateVal).val() == ""){
			$("#err"+dateVal).html("Date Required");
			return;
		}
		*/
		
		/*var locationValue = $(this).attr("attr_location_Value");
		var activityLevel = $("#activityLevelList").val();
		var activityScopeId = $("#ActivityList").val();
		var locationName = $(this).attr("attr_location_Name");
		
		
		
		var locationTypeId = $('#activityLevelList').val();
		var activityLevellId = $('#activityLevelList').val();
		
		var locationLevelId = $('#activityLevelList').val();
		
	    if(activityLevellId == 3 || activityLevellId == 4){}
		else{
			var firstChar = locationValue.substr(0,1);
			//locationLevelId = 5;
			if(activityLevellId == 2)
		    {
				if(firstChar == 2)
					locationLevelId = 5;//mandal
				else if(firstChar == 3)
					locationLevelId = 8;//division
				else if(firstChar == 1)
					locationLevelId = 7;//localEleBody	
			}
			else if(activityLevellId == 1)
			{
				if(firstChar == 1)
					locationLevelId = 6;//panchayat
				else if(firstChar == 2)
					locationLevelId = 8;//ward
			}
		}*/
		
		var locationName = $(this).attr("attr_location_Name");
		gobalLevelValue = $(this).attr("attr_location_Value");
		var dateStr = $("#"+dateVal).val();
		setGobalValues();
		window.open('eventFieUploadAction.action?activityScopeId='+gobalActivityScopeId+'&locationValue='+gobalLevelValue+'&activityLevel='+gobalLevelId+'&locationName='+locationName+'&gobalTempVar='+gobalTempVar+'&eventDateStr='+dateStr+'','_blank');
		
	});
	
	
	function setGobalValues()
	{
		//var locationValue = $(this).attr("attr_location_Value");
		var activityScopeId = $("#ActivityList").val();
		//var locationName = $(this).attr("attr_location_Name");
		var locationLevelId = $('#activityLevelList').val();
		
		//gobalLevelValue       = locationValue;
		gobalActivityScopeId  = activityScopeId;
		//gobalLocName          = locationName;
		
		
	    if(locationLevelId == 3){
			gobalLevelId = 11;
		}else if(locationLevelId == 4){
			gobalLevelId = 10;
		}else if(locationLevelId == 5){
			gobalLevelId = 13;
		}else{
			var firstChar = gobalLevelValue.substr(0,1);
			
			if(locationLevelId == 2){
				if(firstChar == 2)
					gobalLevelId = 5;//mandal
				else if(firstChar == 3)
					gobalLevelId = 9;//division
				else if(firstChar == 1)
					gobalLevelId = 7;//localEleBody	
			}else if(locationLevelId == 1){
				if(firstChar == 1)
					gobalLevelId = 6;//panchayat
				else if(firstChar == 2)
					gobalLevelId = 8;//ward
			}
		}
	}
	
	
	$(document).on("change","#activityTypeList",function(){
		if($(this).val()==4){
			$("#searchId").hide();
		}else{
			$("#searchId").show();
		}
		//getActivityNames(0);
	});
	
	function getCadreDetailsBySearchCriteria(startIndex)
		{
		$("#cadreSaveBtn").hide();	
		var locationLevel = 0;
		var locationValue = 0;
		var searchName = '';
		var mobileNo = '';
		var casteCategory = '';
		var casteStateId = 0;
		var fromAge = 0;
		var toAge = 0;
		var memberShipCardNo = '';
		var trNumber = '';
		var voterCardNo = '';
		var gender = '';
		var houseNo = '';
		var membershipAndMobileNo = '';
	$('#searchcadreDetailsDiv,#searchErrDiv').html('');
		if(startIndex == 0)
		{
			$(".paginationDivId").html('');
		}
		$(".paginationDivId").hide();	
			var memberShipCardNo = $('#membershipId').val().trim();
			var voterCardNo = $('#voterId').val().trim();
			
			if(memberShipCardNo.trim().length == 0 && voterCardNo.trim().length == 0)
			{
				$('#searchErrDiv').html('Search Value is Required').css("color","red");
				return;
			}
			
		var removedStatus =false;
		$("#searchDataImg").show();
		var jsObj =
		{
			locationLevel :locationLevel,
			locationValue:locationValue,
			searchName : searchName,
			mobileNo: mobileNo,
			casteCategory : casteCategory,
			fromAge : fromAge,
			toAge : toAge,
			memberShipCardNo: memberShipCardNo,
			casteStateId : casteStateId,
			trNumber : trNumber,
			voterCardNo:voterCardNo,
			gender:gender,
			houseNo:houseNo,
			startIndex:startIndex,
			maxIndex : 50,
			removedStatus:removedStatus,
			enrollmentId : 3,
			task:"tdpCadreSearch"
		}
		$.ajax({
				type : "POST",
				url : "getCadreSearchDetailsAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
			$(".paginationDivId").show();
				 if(typeof result == "string"){
					if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
					  location.reload(); 
					}
				}
				$("#searchDataImg").hide();
				$('#searchcadreDetailsDiv').show();
				if(result != null && result.previousRoles != null && result.previousRoles.length>0)
				{
				buildCadreDetails(result.previousRoles,jsObj);
				}
				else
				{
					
					$('#searchcadreDetailsDiv').html("<span style='font-weight:bold;text-align:center;'> No Data Available...</span>");
				}
			});  

	}
	function buildCadreDetails(result,jsObj)
	{
		$(".paginationDivId").show();
	
		var str ='';
		var elegRolCnt=0;
		var dtCnt = 0;
		if(result != null)
		{
			for(var i in result)
			{
				
				if(result[i].deletedStatus == "MD"){
					str+='<div class="media eachCadreMainDivCls" style="background: rgba(255, 0, 0, 0.1) none repeat scroll 0 0;padding: 5px;border-bottom: 1px solid rgb(51, 51, 51);" attr_cadre_id='+result[i].tdpCadreId+'>';
				}else{
					 str+='<div class="media" id="main'+result[i].tdpCadreId+'" style="border-bottom: 1px solid rgb(51, 51, 51);" attr_cadre_id='+result[i].tdpCadreId+'>';
				}
				
				str+='<span href="#" class="media-left">';
				str+='<img style="width: 64px; height: 64px;" src="images/cadre_images/'+result[i].imageURL+'" />';
				str+='</span>';
				str+='<div class="media-body">';
				str+='<h5 class="media-heading"> <span style="font-weight:bold;"> Name:</span> '+result[i].cadreName+'<input type="checkbox" value="'+result[i].tdpCadreId+'" class="pull-right searchCadreCheck"> ; ';				
				str+=' <span style="font-weight:bold;"> Relative Name: </span>'+result[i].relativeName+' </h5>';
				str+='<ul class="list-inline">';
				str+='<li>Age:'+result[i].age+';</li>';
				str+='<li>Gender: '+result[i].gender+'</li>';
				str+='<li>Mobile No: <span id="mobile'+result[i].tdpCadreId+'">'+result[i].mobileNo+'</span></li>';
				str+='<li>Caste: <span id="caste'+result[i].tdpCadreId+'">'+result[i].casteName+'</span></li>';
				str+='<li>Voter ID: '+result[i].voterCardNo+'</li>';
				str+='<li>MemberShipNo: '+result[i].memberShipCardId+'</li>';
				str+='<li>Registered Through: '+result[i].dataSourceType+'</li>';
				if(result[i].deletedStatus == "MD"){
					str+='<li><b style="color:red;">Deleted Reason</b> : '+result[i].deletedReason+'</li>';
				}
				else{
					str+='<li id="delete'+result[i].tdpCadreId+'"></li>';
				}
				//str+='<li>Aadhar: '+result[i].imageURL+'</i>';
				str+='</ul>';
				
				/*str+='<div>';
				if(result[i].deletedStatus != "MD"){
					
					<c:if test="${fn:contains(sessionScope.USER.entitlements, 'CADRE_DELETE_ENTITLEMENT_GROUP') || fn:contains(sessionScope.USER.entitlements, 'CADRE_DELETE_ENTITLEMENT')}">
					
						str+='<div id="rc'+result[i].tdpCadreId+'" class="pull-right cadreRemoveCls" style="margin-left:3px;" attr_cadre_id='+result[i].tdpCadreId+' attr_cadre_name ="'+result[i].cadreName+'"><i class="glyphicon glyphicon-remove remove-icon" data-toggle="tooltip" data-placement="bottom" title="Remove Cadre"></i></div>';
						
						str+='<div id="uc'+result[i].tdpCadreId+'" class="pull-right updateCadreClass" style="margin-left:3px;" attr_cadre_id='+result[i].tdpCadreId+' attr_mobile_no ="'+result[i].mobileNo+'" attr_caste_name ="'+result[i].casteName+'" attr_cadre_name ="'+result[i].cadreName+'"><i class="glyphicon glyphicon-edit remove-icon" data-toggle="tooltip" data-placement="bottom" style="margin-right: 3px;" title="Update Cadre MobileNo And Caste"></i></div>';
						
					</c:if> 
				}
				
				<c:if test="${fn:contains(sessionScope.USER.entitlements, 'TDP_CADRE_DETAILS' )}">
				str+='<div id="cadreDetailsDivId" class="cadreDetailsCls" attr_cadre_id='+result[i].tdpCadreId+' attr_membership_id='+result[i].memberShipCardId+' style="cursor:pointer;"><input type="button" value="More Cadre Details" class="btn btn-sm btn-primary pull-right"></div>';
				</c:if> 
				str+='</div>';*/
				
				if(result[i].committeePosition != null && result[i].committeePosition.trim().length > 0)
				{
					str+='<ul>';
					str+='<li style="font-weight:bold;display: block;">Existing Designation : '+result[i].committeePosition+' for '+result[i].committeeName+' Committee in '+result[i].committeeLocation+'</li>';	
					if(result[i].previousRoles != null && result[i].previousRoles.length > 0){
						for(var j in result[i].previousRoles){
							str+='<li style="font-weight:bold;">Electoral for '+result[i].previousRoles[j].committeeName+' Committee in '+result[i].previousRoles[j].committeeLocation+'</li>';
						}
					}
					
					str+='</ul>';	
					str+='</div>';
					str+='</div>';
			}
				else{
					if(result[i].previousRoles != null && result[i].previousRoles.length > 0){
					    str+='<ul>';
						for(var j in result[i].previousRoles){
							str+='<li style="font-weight:bold;">Electoral for '+result[i].previousRoles[j].committeeName+' Committee in '+result[i].previousRoles[j].committeeLocation+'</li>';
						}
					    str+='</ul>';
					}
					str+='</div>';
					str+='</div>';
				
				}
				elegRolCnt++;
				dtCnt++;
			}
		if(result[0].mobileType > 50)	
		{
		var itemsCount=result[0].mobileType;
	    var maxResults=jsObj.maxIndex;
	   
	     if(jsObj.startIndex==0){
		   $(".paginationDivId").pagination({
			items: itemsCount,
			itemsOnPage: maxResults,
			cssStyle: 'light-theme',
			onPageClick: function(pageNumber, event) {
				var num=(pageNumber-1)*50;
				getCadreDetailsBySearchCriteria(num);
				
			}
			});
		}
		
		}
		$("#cadreSaveBtn").show();
		}
		$('#searchcadreDetailsDiv').html(str);
		$('[data-toggle="tooltip"]').tooltip();
	}
		
	 $(document).on("click",".searchCadreCheck",function(){
	 $(".searchCadreCheck").removeAttr("checked");
	 $(this).prop( "checked", true );;
  });
  function saveAttendedCadre()
  {
	  $('#searchErrDiv').html('');
	 
	 var conductedDate = $("#conductedDate").val();
	 if(conductedDate.length<=0){
		 alert("Please Selected Conducted Date");
		 return;
	 }
	 
	 var lctnInfoId = $("#cadreSaveBtn").attr("actvty_lctn_info_id");
	  
	   $(".searchCadreCheck").each(function(){
		   var cadreId = 0;
		   if($(this).is(':checked'))
			cadreId  = $(this).val();
			if(cadreId == 0)
			{
				$('#searchErrDiv').html('Select Cadre').css("color","red");
				return;
			}
				var jsObj =
			{
				tdpCadreId :cadreId,
				activityLocationInfoId:lctnInfoId,
				conductedDate:conductedDate,
				task:""
			}
		$.ajax({
				type : "POST",
				url : "saveCadreActivityAttendanceInfoAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
			$('#searchErrDiv').html("Saved Successfully").css("color","green");
			setTimeout(function() {
				$("#searchErrDiv").hide();
			}, 2000);
			$(".clearCadre").val("");
			
		})
	})
  }
   function saveAttendedPublic()
  {
	  $("#searchErrDiv1").html('');
	 // var activityId= $("#ActivityList").val();
	  var name = $("#publicNameId").val().trim();
	  var mobileNumber = $("#publicMobileNoId").val().trim();
	  var voterCard = $("#publicVoterCardId").val().trim();
	  //var bloodGroupId = $("#publicbloodGroupId").val();
	  var pattern = /^\d{10}$/;
	  
	  var lctnInfoId = $("#publicAttndId").attr("actvty_lctn_info_id");
	  
	  var conductedDate = $("#conductedDate").val();
	  if(conductedDate.length<=0){
		 alert("Please Selected Conducted Date");
		 return;
	 }
	  if(name.length == 0)
			{
				$('#searchErrDiv1').html('Name is Required').css("color","red");
				return;
			}
			 if(mobileNumber.length == 0)
			{
				$('#searchErrDiv1').html('mobileNumber is Required').css("color","red");
				return;
			}
			else if(!pattern.test(mobileNumber)) {
			$('#searchErrDiv1').html('Mobile Number Should be Numerical & 10 digits only <br>');
			return;
			}
		
			 if(voterCard.length == 0)
			{
				$('#searchErrDiv1').html('Voter Card is Required').css("color","red");
				return;
			}
			/* if(bloodGroupId == 0)
			{
				$('#searchErrDiv1').html('Blood Group is Required').css("color","red");
				return;
			} */
			 var jsObj =
			{
				name :name,
				mobileNumber:mobileNumber,
				voterCard:voterCard,
				activityLocationInfoId:lctnInfoId,
				bloodGroupId:"",
				conductedDate:conductedDate,
				tdpCadreId :0,
				task:""
			}
		$.ajax({
				type : "POST",
				url : "savePublicActivityAttendanceInfoAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
			$(".clearPublic").val("");
			$("#publicbloodGroupId").val(0);
			$("#searchErrDiv1").html("Saved Successfully").css("color","green");
			setTimeout(function() {
				$("#searchErrDiv1").hide();
			}, 2000);
		})
	
  }
  function getBloodGroups()
  {
	  
	$('#publicbloodGroupId').find('option').remove();
	$('#publicbloodGroupId').append('<option value="0"> Select </option>');
	   var jsObj =
			{
				task:""
			}
		$.ajax({
				type : "POST",
				url : "getBloodGroupsAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
			if(result != null && result.length >0)
			{
				for(var i in result)
					$('#publicbloodGroupId').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');	
			}
		}) 
  }
  function validateFields()
  {
	  $(".errorDiv").html("");
	  var str = '';
	  var flag = true;
	  var conductDate = $("#conductedDate").val();
	  //var conductDate = "20/01/2016";
	  if(conductDate == null || conductDate.trim().length == 0){
		str += 'Please Select Conducted Date';
	   flag = false;
	  }
   
   if(!flag){
		$(".jFiler-items-list").html("");
	}
   if(flag){
     setGobalValues();
	 var d = conductDate.split("/");//dd/mm/yyyy
	 gobalActivityDate = d[1] +'/'+ d[0] +'/'+ d[2];//mm/dd/yyyy
	 gobalTempVar = "dayCalCulationReq";
   }
   $(".errorDiv").html(str).addClass("errorCls");  
	
   return flag;
  }
$(document).on("click",".deleteLocationImgCheck",function() {
	var imageCheck = false;
	$(".deleteLocationImgCheck").each(function(){
		if($(this).is(":checked") == true){imageCheck = true;}
		});
	if(!imageCheck){
		$(".deleteBtnCls").hide();
	}
    else{
	   $(".deleteBtnCls").show();
    }	
});

function getRequiredAttributesByActScopeId()
{
	var activityScopeId = $("#ActivityList").val();
	var jsObj =
			{
				activityScopeId:activityScopeId,
				task:""
			}
		$.ajax({
				type : "POST",
				url : "getRequiredAttributesByActScopeIdAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
			console.log(result)
		}) 
}

function getQuestionnaireDetails(requiredAttributeId)
{
	var activityScopeId = $("#ActivityList").val();
	$(".questionnaireCls").html("");
	
	var jsObj={   
				scopeId 			: activityScopeId,
				requiredAttributeId : requiredAttributeId,
            };
       
			$.ajax({
				type : "GET",
				url : "getQuestionnaireForScopeAction.action",
				dataType: 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				buildQuestionnaireDetails(result);
		});
			
}
function buildQuestionnaireDetails(result)
{    
     var str='';
	if(result!=null && result.activityVoList!=null && result.activityVoList.length>0){
		for(var i in result.activityVoList){
			//alert(result.activityVoList[i].question);
			//alert(result.activityVoList[i].remarks);
			
			str+='<div class="col-md-12">'
			str+='<div class="row">'
			str+='<div class="col-md-12 m_top10">';
					str+='<label>'+result.activityVoList[i].question+' </label><br/>';
			str+='</div>';
			str+='<div class="col-md-4">';

				if(result.activityVoList[i].optionsList!=null && result.activityVoList[i].optionsList.length>0){
					if(result.activityVoList[i].optionTypeId==1){
						str+='<select class="form-control selectedVal" attr_type="selectbox" attr_qid="'+result.activityVoList[i].questionId+'">';
						str+='<option value="0">Select Option </option>';
							for(var j in result.activityVoList[i].optionsList){
								str+='<option value="'+result.activityVoList[i].optionsList[j].optionId+'">'+result.activityVoList[i].optionsList[j].option+'</option>';
							}
							str+='</select>';
					}
					else if(result.activityVoList[i].optionTypeId==2){
						for(var j in result.activityVoList[i].optionsList){
							str+='&nbsp;&nbsp;<label><input type="checkbox" attr_type="ckeckBox" name="result'+result.activityVoList[i].questionId+'" class="selectedVal" attr_qid="'+result.activityVoList[i].questionId+'" value="'+result.activityVoList[i].optionsList[j].optionId+'"/>&nbsp;&nbsp;'+result.activityVoList[i].optionsList[j].option+'</label>';
						}
					}
					else if(result.activityVoList[i].optionTypeId==3){
						str+='&nbsp;&nbsp;<label><input type="text" name="result'+result.activityVoList[i].questionId+'" class="selectedVal" attr_qid="'+result.activityVoList[i].questionId+'" /></label>';
					}
				}
				
					str+='</div>';
					str+='</div>';
					str+='</div>';
		}
		str += ' <div id="errMsg" class="col-md-12 m_top10 errMsgCls" style="color:green;margin:20px;"></div>';
		str+= '<div class="col-md-12 m_top10"><button type="button" id="saveResult" class="btn btn-custom btn-success" attr_location_Value="'+gobalLevelValue+'">Save</button></div>';
		
	}else{
	str+='<div class="col-md-12"><h4>No Data Found.</h4></div>';
	}
	$(".questionnaireCls").html(str);
	
}

function getActivityLocationWiseDetails()
{
	var scopeId = $("#ActivityList").val();
	var jsObj = {
		scopeId : scopeId
	};
	$.ajax({
		type : "GET",
		url : "getActivityLocationWiseDetailsByScopeIdAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null){
			buildBloodDonation(result);
		}
	});
}

	function buildBloodDonation(result){
		var str='';
		
		str+='<table class="table table-bordered m_top10" id="bloodCampTab">';
			str+='<thead>';
				str+='<tr>';
					 str+='<th rowspan=3 style="vertical-align: middle;text-align: center;">CONSTITUENCY NAME </th>';
					 str+='<th colspan=7 style="text-align: center;vertical-align: middle;">MEMBERS ATTENDED</th>';
					 str+='<th colspan=2 style="text-align: center;">PHOTOS UPLOAD</th>';
					 /* str+='<th rowspan=3 style="vertical-align: middle;">QUESTIONAIRE</th>';
					 str+='<th rowspan=3 style="vertical-align: middle;text-align: center;">ORGANISER QUESTIONAIRE</th>'; */
					 str+='<th rowspan=3 style="vertical-align: middle;">UPDATE</th>';
				str+='</tr>';
				str+='<tr>';
					str+='<th rowspan=2 style="vertical-align: middle;">TOTAL</th>';
					str+='<th colspan=3 style="text-align: center;">CADRE</th>';
					str+='<th colspan=3 style="text-align: center;">PUBLIC</th>';
					str+='<th rowspan=2 style="vertical-align: middle;">TAB</th>';
					str+='<th rowspan=2 style="vertical-align: middle;">WEB</th>';
				str+='</tr>';
				str+='<tr>';
					str+='<th style="text-align: center;">Total</th>';
					str+='<th style="text-align: center;">Tab</th>';
					str+='<th style="text-align: center;">WEB</th>';
					str+='<th style="text-align: center;">Total</th>';
					str+='<th style="text-align: center;">Tab</th>';
					str+='<th style="text-align: center;">WEB</th>';
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
			for(var i in result.constituencyList){
				str+='<tr>';
					str+='<td>'+result.constituencyList[i].name+'</td>';
					var total=result.constituencyList[i].activityAttendanceInfoVO.totalWebCadreAttendance+result.constituencyList[i].activityAttendanceInfoVO.totalInfoCellCadreAttendance+result.constituencyList[i].activityAttendanceInfoVO.totalWebPublicAttendance+result.constituencyList[i].activityAttendanceInfoVO.totalInfoCellPublicAttendance;
					var cadreTotal=result.constituencyList[i].activityAttendanceInfoVO.totalWebCadreAttendance+result.constituencyList[i].activityAttendanceInfoVO.totalInfoCellCadreAttendance;
					var publicTotal=result.constituencyList[i].activityAttendanceInfoVO.totalWebPublicAttendance+result.constituencyList[i].activityAttendanceInfoVO.totalInfoCellPublicAttendance;
					str+='<td>'+total+'</td>';
					str+='<td>'+cadreTotal+'</td>';
					str+='<td>'+result.constituencyList[i].activityAttendanceInfoVO.totalInfoCellCadreAttendance+'</td>';
					str+='<td>'+result.constituencyList[i].activityAttendanceInfoVO.totalWebCadreAttendance+'</td>';
					str+='<td>'+publicTotal+'</td>';
					str+='<td>'+result.constituencyList[i].activityAttendanceInfoVO.totalInfoCellPublicAttendance+'</td>';
					str+='<td>'+result.constituencyList[i].activityAttendanceInfoVO.totalWebPublicAttendance+'</td>';
					str+='<td>'+result.constituencyList[i].activityAttendanceInfoVO.totalInfoCellPhotosAttendance+'</td>';
					str+='<td>'+result.constituencyList[i].activityAttendanceInfoVO.totalWebPhotosAttendance+'</td>';
					/* str+='<td>200</td>';
					str+='<td>Yes</td>'; */
					str+='<td><span class="btn btn-success btn-xs activityCadrePopup" attr_location_Value="'+result.constituencyList[i].id+'" actvty_lctn_info_id="'+result.constituencyList[i].locationInfoId+'" attr_location_Name=\''+result.constituencyList[i].name+'\'  >UPDATE</span></td>';
				str+='</tr>';
			}
			str+='</tbody>';
		str+='</table>';
				
		$("#bloodDonationDetails").html(str);
		$("#bloodCampTab").dataTable({
					"iDisplayLength": 20,
					"aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
					});
		//$("#bloodCampTab").removeClass("dataTable");
	}

function getActivityDatesByScopeId(){
	var scopeId =$('#ActivityList').val();
	 var jsObj =
			{
				activityScopeId:scopeId,
				task:""
			}
		$.ajax({
				type : "POST",
				url : "getActivityDatesByScopeAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
			if(result != null && result.length >0){
				$('#conductedDate').daterangepicker({
						singleDatePicker:true,
						format: 'MM/DD/YYYY',
						startDate: new Date(result[0]),
						endDate: new Date(result[1]),
						minDate: new Date(result[0]),
						maxDate: new Date(result[1])
						});
			//	conductedDate	
			}
		});
}


function getActivityDates(){
	var scopeId =$('#ActivityList').val();
	 var jsObj =
			{
				activityScopeId:scopeId,
				task:""
			}
		$.ajax({
				type : "POST",
				url : "getActivityDatesAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
			if(result != null && result.length >0){
				$('#conductedDate').daterangepicker({
						singleDatePicker:true,
						format: 'MM/DD/YYYY',
						startDate: new Date(result[0]),
						endDate: new Date(result[1]),
						minDate: new Date(result[0]),
						maxDate: new Date(result[1])
						});
			//	conductedDate	
			}
		});
}
	
	$(document).on("change","#districtList",function(){
		var activityLevelId = $('#activityLevelList').val();
		if(activityLevelId == 5){
		$('#constiList').find('option').remove();
		var districtId = $(this).val();
		var jObj = {
			task:"getConstituenciesForDistrict",
			districtId:districtId
		};
		$("#procesingImg3").show();
		$.ajax({
          type:'GET',
          url: 'getConstituenciesForADistrictAjaxAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			
			if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1)
			{}else{
				$("#constituencyDivId").show();
				$("#procesingImg3").hide();
				for(var i in result)
					$('#constiList').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
			}
		});
		}
	});
	
	
	$(document).on("click",".deleteFile",function() {
 
	 var acitivityInfoDocId = $(this).attr("id");
	 
	 var jsObj=
	   {				
		  acitivityInfoDocId:acitivityInfoDocId,
		  task:"deleteFile"				
		}
		$.ajax({
				  type:'GET',
				  url: 'deleteUploadedFileAction.action',
				  dataType: 'json',
				  data: {task:JSON.stringify(jsObj)}
		   }).done(function(result){
			   if(result.resultCode == 0){
				 $(this).closest("li").html("");
			   }
		   });

	});
	$(document).on("click","#upldPhotosId",function(){
	//$("#upldPhotosId").click(function(){
		var conductDate = $("#conductedDate").val();
		if(conductDate==""){
			alert("Please Enter Conducted Date");
			//$('#cadreLiTabId').trigger("click");
			$("#cadreLiTabId").trigger("click");
			//$("#cadre").addClass("active");
			return;
		}
		// var d = conductDate.split("/");//dd/mm/yyyy
		// gobalActivityDate = d[1] +'/'+ d[0] +'/'+ d[2];//mm/dd/yyyy
		//gobalTempVar = "dayCalCulationReq";
		gobalActivityDate = conductDate;
		setGobalValues();
		uploadImgs();
	});
	
function getActivityStatusDetailsByScopeIdAndLocationValue(){
	 $("#buildActivityReasonReportTableId").html('');
	 $('#statsDiv').hide();	 

	var scopeId = $("#activityLevelList option:selected").attr("attr_scopeId");
	var constituencyId = $("#constiList").val();
	var mandalId = $("#mandalsList").val();
	var villageId = $("#villageWardsList").val();
	
	var jsObj = {
		scopeId : scopeId,
		constituencyId : constituencyId,
		mandalId : mandalId,
		villageId : villageId,
		questionId:$('#questionsId').val()
	}
	
	$.ajax({
		type : 'GET',
		url : 'getActivityStatusDetailsByScopeIdAndLocationValueAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)} 
	}).done(function(result){ 
	  if(result!=null && result!=0){
		  buildActivityReasonReport(result);
	  }
	});     
}
function buildActivityReasonReport(result)
{
	$("#activityHeadingDiv").show();
	var str='';
	str+='<table class="table table-bordered table-condensed" style="background:#fff">';
	 str+='<tbody>';
	  str+='<tr>';
	   for(var i in result){
		 str+='<td attr_option_id='+result[i].id+' class="bg_ef text-center" style="text-transform:uppercase">'+result[i].name+'</td>';
	  }
	  str+='</tr>';
	  str+='<tr>';
	  for(var i in result){
		str+='<td class="text-center">'+result[i].actualCount+'</td>';
	  }
	  str+='</tr>';
	  str+='</tbody>';
	  str+='</table>';
	 $("#buildActivityReasonReportTableId").html(str); 
	 $('#statsDiv').show();	
}
	
		
	

$(document).on("click",".updateDateDetls",function(){
var plandateVal = '';
		var dateVal = $(this).attr('attr_date');
		var attrImgCls = $(this).attr('attr_img');
		$(".errCls").html("");
		$("#err"+dateVal).html("");
		
		/*if($("#"+dateVal).val() == ""){
			$("#err"+dateVal).html("Date Required");
			return;
		}*/
		var day = $(this).attr('attr_day');
	var plandateVal = $(this).attr('attr_plan_date');
	var jObj = {
			plannedDateStr :$("#"+plandateVal).val(),
			conductedDateStr : $("#"+dateVal).val(),
			locationValue : $(this).attr('attr_location_value'),
			activityScopeId  : $('#ActivityList').val(),
			activityLevelId:$('#activityLevelList').val(),
			contituencyId  : $('#constiList').val(),
			day:day,
			task:"saveActivityDetails"
		};
		
		$.ajax({
          type:'GET',
          url: 'saveActivityDetailsAction.action',
         data : {task:JSON.stringify(jObj)} 
        }).done(function(result){
			if(result != null && result.resultCode==0){
				$('.'+attrImgCls+'').show();
				$("#err"+dateVal).html("<b class='text-success'> Updated Successfully.</b>");
			}
			else
				$("#err"+dateVal).html("<b> Error Occured.</b>");
		});			
		
});
	
	function getQuestions(){
		
	var scopeId = $("#ActivityList").val();
	$('#questionsId').find('option').remove();
	//$('#questionsId').append('<option value="0"> Select Question </option>');	
	var jsObj={	
			scopeId :scopeId		 
		}
		$.ajax({
				  type:'GET',
				  url: 'getQuestionsAction.action',
				  dataType: 'json',
				  data: {task:JSON.stringify(jsObj)}
		   }).done(function(result){
			   if(result != null && result.length >0)
			{
				
				for(var i in result){
					console.log(result[i].id);
					$('#questionsId').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');	
				}
					
			}
			   
		   });
	}
$(document).on("change","#questionsId",function(){	
$("#questionsForOptDiv").show();
$('#questionsForOptionsId').find('option').remove();
$('#questionsForOptionsId').append('<option value="0">All</option>');	
var questionId = $("#questionsId").val();
getActivityStatusDetailsByScopeIdAndLocationValue();
getLocationDetailsForActivity('','',0,questionId);
	var jsObj={	
			questionId:questionId		 
		}
		$.ajax({
				  type:'GET',
				  url: 'getOptionsForQuestionAction.action',
				  dataType: 'json',
				  data: {task:JSON.stringify(jsObj)}
		   }).done(function(result){
			    if(result != null && result.length >0)
			{
				for(var i in result)
					$('#questionsForOptionsId').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');	
			}
		   });
	});
$(document).on("change","#questionsForOptionsId",function(){

		var optionId =$(this).val();
		var questionId = $("#questionsId").val();
		getActivityStatusDetailsByScopeIdAndLocationValue();
		
		getLocationDetailsForActivity('','',optionId,questionId);
});

function getAllActivities(){
	$('#ActivityList').find('option').remove();
		$.ajax({
				  type:'GET',
				  url: 'getAllActivitiesAction.action',
				  dataType: 'json',
				  data: {}
		   }).done(function(result){
			   var str= '';
				if(result != null && result.length >0)
				{
					str+='<option value="0">Select Activity </option>';
					for(var i in result){						
						str+='<option value='+result[i].id+'>'+result[i].name+'</option>';						
					}
					$("#ActivityList").html(str);
						//$('#questionsForOptionsId').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');	
				}
		   });
}

function getAllActivityLevelsByActivity(id){

	$('#activityLevelList').find('option').remove();	
	var jsObj={	
			activityId:id		 
		}
		$.ajax({
				  type:'GET',
				  url: 'getAllActivityLevelsByActivityAction.action',
				  dataType: 'json',
				  data: {task:JSON.stringify(jsObj)}
		   }).done(function(result){
			   var str='';
			   str+='<option value="0">Select Activity Level </option>';
				if(result != null && result.length >0)
				{					
					for(var i in result)
					{
						str+='<option value='+result[i].id+' attr_scopeId='+result[i].orderId+'>'+result[i].name+'</option>';	
					}
					$('#activityLevelList').html(str);					
				}
		   });
}

$(document).on("change","#activityLevelList",function(){
	$('#mandalsList').find('option').remove();
	$('#mandalsList').append('<option value="0"> Select Mandal/Town/Division</option>');
	$('#villageWardsList').find('option').remove();
	$('#villageWardsList').append('<option value="0"> Select Mandal/Town/Division</option>');
	
	$("#constituencyDivId").hide();
	$("#mandalDivId").hide();
	$("#panchayatDivId").hide();
	$("#districtDivId").hide();
	$("#constiList").val(0);
	

	var	activityLevelId = $('#activityLevelList').val();
	
	if($("#activityTypeList").val()!=4){
		if(activityLevelId == 1){
		$("#constituencyDivId").show();
		$("#mandalDivId").show();
		$("#panchayatDivId").show();
		}
		else if(activityLevelId == 2){
			$("#constituencyDivId").show();
			$("#mandalDivId").show();
		}else if(activityLevelId == 5){
			$("#districtDivId").show();
		}
		else if(activityLevelId == 3 ){
			$("#districtDivId").show();
		}
	}
});

function getBetweenDatesOfActivityScope(){
	$('#datesId').find('option').remove();
	var scopeId = $("#activityLevelList option:selected").attr("attr_scopeId");
	
	var jsObj ={
		scopeId : scopeId
	}
	$.ajax({
			  type:'GET',
			  url: 'getBetweenDatesOfActivityScopeAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		     var str='';			 
			 str+='<option value="0">Select Dates </option>';
			 if(result != null)
				{		j=0;			
					for(var i in result.datesList){						
						j=j+1;
						str+='<option value="Day '+j+' ('+result.datesList[i]+')">Day '+j+' ('+result.datesList[i]+')</option>';		
					}
					$("#datesId").html(str);
					$("#datesId").chosen();	
				}			 
	   });
	   $("#datesDivId").show();
}
function getSelectedBetweenDatesOfActivityScope(){
	$('#selectDateId').html("");
	var scopeId = $("#activityLevelList option:selected").attr("attr_scopeId");
	var jsObj ={
		scopeId : scopeId
	}
	$.ajax({
			  type:'GET',
			  url: 'getBetweenDatesOfActivityScopeAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		     var str='';			 
			if(result != null)
				{		j=0;			
					for(var i in result.datesList){						
						j=j+1;
						str+='<option value="Day '+j+' ('+result.datesList[i]+')">Day '+j+' ('+result.datesList[i]+')</option>';			
					}
					$("#selectDateId").html(str);
				}		
			retrievingActivityQuestionnaireAndOption();				
	   });
	  
}

$(document).on("change","#selectDateId",function(){
		var selectedDay = $("#selectDateId").val();
		getQuestionnaire(locationValue,0,0,'questionsDivBodyId',1,dateFieldId,selectedDay);
});
$(document).on("click","#searchId",function(){
	datesArr=[];	
	getBetweenDatesOfActivityScope();
});

$(document).on("click","#locationInfoBtnId",function(){	
	var datesArr=[];
	$('#datesId :selected').each(function(i, sel){ 
		//datesArr.push($(sel).val().split("(")[0]); 
		datesArr.push($(sel).val()); 
	});	
	getLocationDetailsForActivity('','','0','0',1,datesArr);
});
function retrievingActivityQuestionnaireAndOption(){
	var d = $("#selectDateId").val();
	var day = d.substring(4,5); 
	var jsObj ={
		day : day
	}
	$.ajax({
			  type:'GET',
			  url: 'getquestinaireForRetrievingAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
	});
}
$(document).on("click","#closeButtonId",function(){
	setTimeout(function(){
		$("body").addClass('modal-open');
	},1000)
	
});
</script>
</body>
</html>