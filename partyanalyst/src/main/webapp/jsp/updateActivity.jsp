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
	<!--<link href='dist/activity/Date/daterangepicker-bs3.css' rel='stylesheet' type='text/css'>
	<link href='daterangepicker/bootstrap-datetimepicker.min-bs3.css' rel='stylesheet' type='text/css'>-->
	<link href="dist/DateRange/daterangepicker.css" type="text/css" rel="stylesheet"/>
	
	<link href="newCoreDashBoard/Plugins/Slick/slick.css" type="text/css" rel="stylesheet"/>
	<link href="newCoreDashBoard/Plugins/Slick/slick-theme.css" type="text/css" rel="stylesheet"/>
	
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>	
	<script src="dist/activity/js/bootstrap.min.js" type="text/javascript"></script> 
	<!--<script type="text/javascript" src="js/bootstrap.js" ></script> -->
	 <script src="dist/activity/Date/moment.min.js" type="text/javascript"></script>
	 <script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
	 <!--<script src="dist/activity/Date/daterangepicker.js" type="text/javascript"></script>
	 <link href='daterangepicker/bootstrap-datetimepicker.min-bs3.js' rel='stylesheet' type='text/css'>-->
	
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
	<!-- images -->
	<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="newCoreDashBoard/css/custom.css" rel="stylesheet" type="text/css">
	<link href="newCoreDashBoard/css/responsive.css" rel="stylesheet" type="text/css">
	<link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet" type="text/css">
	<link href="newCoreDashBoard/Plugins/Slick/slick.css" type="text/css" rel="stylesheet"/>
	<link href="newCoreDashBoard/Plugins/Slick/slick-theme.css" type="text/css" rel="stylesheet"/>
	<link href="dist/DateRange/daterangepicker.css" type="text/css" rel="stylesheet"/>
	<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
	<link href="newCoreDashBoard/Plugins/Rating/bootstrap-rating.css" type="text/css" rel="stylesheet"/>
	<link href="dist/scroll/jquery.mCustomScrollbar.css" type="text/css" rel="stylesheet"/>
	<link href="dist/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" href="dist/sliderbar/bootstrap-slider.css">
	<link href="newCoreDashBoard/Plugins/RangeSlider/iThing.css" type="text/css" rel="stylesheet"/>
	<link href="newCoreDashBoard/Plugins/RangeSlider/jquery-ui-1.8.10.custom.css" type="text/css" rel="stylesheet"/>
	<link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/>
	
	
	
 <script type="text/javascript" src="js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jQ_datatables/css/jquery.dataTables.css"/> 
<!-- Multiselect -->
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
<!--<link rel="stylesheet" href="https://formden.com/static/cdn/bootstrap-iso.css" />-->

<link href="dragAndDropPhoto/css/jquery.filer.css" type="text/css" rel="stylesheet" />
<link href="dragAndDropPhoto/css/themes/jquery.filer-dragdropbox-theme.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">	
<!--<link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/>-->
	
 <style type="text/css">
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
.clearCls{
	cursor:pointer !important;
}
.glyphicon-ok{
	cursor:pointer !important;
}
</style>
</head>
<body>
	<form method="POST" enctype="multipart/form-data" name="saveActivityDetails" id="saveActivityDetails">
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
							<div class="col-md-4" style="display:none;"><span class="starMark">*</span>
                                	<label>Activity Type</label>
                                    <s:select theme="simple" headerKey="0" headerValue="Select Activity Type" name="surveyType" id="activityTypeList" value="surveyTypeId" list="basicVO.panchayatVoterInfo" listKey="id" listValue="name" cssClass="input-block-level form-control"/>
                            </div>
							<div class="col-md-4"><span class="starMark">*</span>
								<span id="errCnstId" style="color:red;"></span>
								<label>Activity Level</label>
								<s:select theme="simple" headerKey="0" headerValue="Select Activity Level" name="surveyType" id="activityLevelList" value="surveyTypeId" list="idNameVOList" listKey="id" listValue="name" onchange="getActivityNames(this.value);" cssClass="input-block-level form-control"/>
							</div>
							<div class="col-md-4"><span class="starMark">*</span>
								<label> Activity Name </label>
								<select id="ActivityList" class="form-control" name="activityVO.activityLevelId">
									<option value="0"> Select Activity</option>
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
							</div>
							<div class="row">
							<div class="col-md-4 m_top10" id="districtDivId" style="display:none;">
								<label>District</label>
								<select id="districtList" class="form-control" name="activityVO.districtId" >
									<option value="0"> Select District</option>
								</select>
								<span > <img src="images/ajaxImg2.gif" style="width:20px;margin-top:-20px;margin-left:-25px;display:none;" id="procesingImg2"></span>
							</div>	
							<div class="col-md-4 m_top10" id="constituencyDivId"  style="display:none;"><span class="starMark">*</span>
								<label>Constituency</label>
								<!--<select id="constiList" class="form-control" onchange="getMunciMandalsList(this.value)" name="activityVO.constituencyId" >-->
								<select id="constiList" class="form-control" name="activityVO.constituencyId" >
									<option value="0"> Select Constituency</option>
								</select><span > <img src="images/ajaxImg2.gif" style="width:20px;margin-top:-20px;margin-left:-25px;display:none;" id="procesingImg3"></span>
							</div>
							<div class="col-md-4 m_top10" id="mandalDivId" style="display:none;">
								<label >Mandal/ Town/ Division</label>
								<!--<select id="mandalsList" class="form-control" onchange="getPanchayatWardByMandal(this.value);">-->
								<select id="mandalsList" class="form-control">
									<option value="0"> Mandal/ Town/ Division</option>
								</select>
								<span > <img src="images/ajaxImg2.gif" style="width:20px;margin-top:-20px;margin-left:-25px;display:none;" id="procesingImg"></span>
							</div>
							<div class="col-md-4 m_top10" id="panchayatDivId" style="display:none;">
								<label>Panchayat/ Ward</label>
								<select id="villageWardsList" class="form-control">
									<option value="0"> Select Panchayat/ Ward</option>
								</select>
								<span > <img src="images/ajaxImg2.gif" style="width:20px;margin-top:-20px;margin-left:-25px;display:none;" id="procesingImg5"></span>
							</div>
							<div class="col-md-3" style="margin-top:35px;">
								<button id="searchId" class="btn btn-block btn-custom btn-success" type="button" onclick="getLocationWiseDetailsForActivity(0);">SEARCH</button>
							</div>
							</div>
								
                           
                        </div>
                    </div>
                </div>
            </div>
        
		
		
        
		<div class="panel panel-default panel-custom" id="assemblydivId" style="display:none;">
		  <div class="panel-heading">
			<!--<h4 class="panel-title">ASSEMBLY CONSTITUENCY WISE ACTIVITIES -  <small style="text-transform: uppercase;"><b>${sessionScope.UserName}</b></small></h4> -->
			<h4 class="panel-title"><span  id="constituencyHeadingId" style="display:none;" >  CONSTITUENCY WISE ACTIVITIES</span>
			<span id="districtHeadingId" style="display:none;">DISTRICT WISE ACTIVITIES</span>
			<span class="pull-right"><a href="javascript:{}" class="btn btn-success btn-xs" id="hideAsmblyData" style="display:none">Hide Data</a></span>
			<span class="pull-right"><a href="javascript:{}" class="btn btn-success btn-xs" id="showAsmblyData" style="display:none" >Show Data</a></span>
			</h4>
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
		<div class="row" style=" margin-bottom: 10px;display:none;">
			<div class="col-md-3" id="attributeTypeId" style="display:none;">
				<label>Required Fields : </label>
				<select attr_no="" multiple class="chosenSelect form-control" id="attributeTypeList" name="" data-placeholder ="Select Option" name="activityVO.types">
					<!-- <option value="0">Select Option</option>
					<option selected value="6"> Conducted Date</option>
					<!--<option value="5"> Planned Date</option>
					<option selected value="7"> Ivr Status</option>
					<option value="1">Upload Images</option> -->
				</select><br>
				<!--<span id="chCkBxErrMsgId"></span>-->
				
			</div>
		</div>
		<div class="row" style="margin-bottom: 10px;display:none;">
			<div class="col-md-3" id="planedDateDivId" style="display:none;">
				<label>Planned Date</label>
				<div class="input-group input-g1">
					<span class="input-group-addon">
						<i class="glyphicon glyphicon-calendar"></i>
					</span>
					<input type="text" class="form-control planedDateCls" id="plannedDateId" name="activityVO.selectedPlanedDate">
				</div>
			</div>
		<!--		
		<div class="col-md-3" id="conductedStatusDivId" style="display:none;">
				<label>Update Conducted Status As : </label>
				<select id="conductedStatus" class="form-control" name="activityVO.status">
					<option value="0"> Select Conducted Status </option>
					<option value="Conducted"> Conducted </option>
					<option value="Not Conducted"> Not Conducted  </option>
				</select>
			</div>
			<div class="col-md-4" id="conductedDateDivId" style="display:none;">
				<label>Conducted Date <i class="glyphicon glyphicon-remove clearCls" attr_id="conductedDateId" title="Please click here to clear Conducted Date "></i> <a href="javascript:{clearAllConductedDates('condDateCls');clearAllConductedDates('conductedDateCls');}" title="Please click here to clear all Conducted Dates " style="margin-left:10px;"> Clear All Conducted Dates</a> </label>
				<div class="input-group input-g1">
					<span class="input-group-addon">
						<i class="glyphicon glyphicon-calendar"></i>
					</span>
					<input type="text" class="form-control conductedDateCls" id="conductedDateId" name="activityVO.selectedCnductedDate">
				</div>
			</div>
			-->
			<div class="col-md-3" id="statusDivId" style="display:none;">
				<label>ivr Status</label>
				<select id="ivrStusId" class="form-control" name="activityVO.actalIvrStatus">
					<option value=""> Select IVR Status </option>
					<option value="Y"> Y </option>
					<option value="N"> N </option>
					<option value="NULL"> NA </option>
				</select>
			</div>
			<div class="col-md-3">
				<a id="reSearchId" class="btn btn-success" style="margin-top:23px;display:none;" href="javascript:{getLocationWiseDetailsForActivity(1);}" >Update Now</a>
			</div>
			
		</div>
		
		<div class="panel panel-default" style="display:none;">
			<div class="panel-heading">
				<div id="loadingsymbl"></div>
			</div>
		</div>
		
		<div class="panel panel-default panel-custom" id="resultsDiv" style="display:none;">
		    	<div class="panel-heading">
                	<h4 class="panel-title"><span class="font-40" id="constncyId">SEARCH RESULTS  </span><span class="font-12" id="headinggId"> <!--( <span >
							 <i class="getImageCls glyphicon glyphicon-camera" style="cursor:pointer;display:none" title="View All Images" id="imageButId"></i>
						</span> )--></span>
						
						<span  class="" style="background-color: lightblue; border-radius: 5px; padding: 10px;float:right;">
						<label class="checkbox-inline">
								<input type="radio" checked="checked" id="allId" onclick="getLocationWiseDetailsForActivity(2);" name="radio1"> Show All Locations 
							</label>
							<label class="checkbox-inline">
								<input type="radio" id="conductedId" onclick="getLocationWiseDetailsForActivity(2);" name="radio1">Show Conducted  Locations
							</label>
							<label class="checkbox-inline">
								<input type="radio" id="notConductedId" onclick="getLocationWiseDetailsForActivity(2);" name="radio1">Show Not Conducted Locations
							</label>
							<label class="checkbox-inline">
								<input type="radio" id="notUpdatedId" onclick="getLocationWiseDetailsForActivity(2);" name="radio1">Show Not Updated 
							</label>
						</span>	
                    </h4>
                </div>
                <div class="panel-body">
					<!--<div>
                    	
						
							<label class="checkbox-inline">
								<input type="radio" checked="checked" id="allId" onclick="getLocationWiseDetailsForActivity();" name="radio1">All
							</label>
							<label class="checkbox-inline">
								<input type="radio" id="conductedId" onclick="getLocationWiseDetailsForActivity();" name="radio1">Show Conducted Locations
							</label>
							<label class="checkbox-inline">
								<input type="radio" id="notConductedId" onclick="getLocationWiseDetailsForActivity();" name="radio1">Show Not Conducted Locations
							</label>
							<span  style="margin-left:30px;">
								<input type="button" class="btn btn-success btn-xs" value="Get Details" onclick="getLocationDetailsForActivity('','');">
							</span>
							
                        
                    </div>-->
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
<!-- Modal for Images -->
<div class="modal fade" id="myModalImageId" tabindex="-1" role="dialog" aria-labelledby="myModalLabelId">
  <div class="modal-dialog modal-lg" role="document"  id="slick-modal" style="width:90%">
    <div class="modal-content customModal">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabelId"></h4>
      </div>
      <div class="modal-body" style="padding:0px 15px;">
       <div id="buildPoupupImage"></div>
      </div>
    </div>
  </div>
</div>

<!-- modal for popup Images -->
<div class="modal fade" id="imagesModalDivId" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-lg" role="document"  id="slick-modal" style="width:90%">
    <div class="modal-content customModal">
      <div class="modal-header">
        <button type="button" class="close " data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalSLabel"></h4>
      </div>
      <div class="modal-body" style="padding:0px 15px;">
       <div id="buildImagesId"></div>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="errorModalId" tabindex="-1" role="dialog">
  <div class="modal-dialog modal-xs" role="document">
    <div class="modal-content">
      <div class="modal-body">
        <div id="statsErrDiv"></div>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!-- /.modal for updateactivitymodel user -->
	
	
<div class="modal fade" id="updateactivitymodel" tabindex="-1" role="dialog" aria-labelledby="myModalLabelId">
  <div class="modal-dialog modal-lg" role="document"  id="slick-modal" style="width:90%">
    <div class="modal-content customModal">
      <div class="modal-header">
	   <!--<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4>Activity Question Details - <span id="jbModalHeaderDivId"></span></h4><br>-->
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="text-capital" id="activityHeadingId">Activity Question Details</h4><br>
		 <div id="jbModalHeaderDivId"></div>
      </div>
      <div class="modal-body" style="padding:0px 15px;">
		 <div class="row">
			<div id="jbAnswerQuestionDivId"></div>
		 </div>
      </div>
	  <div class="modal-footer">
	        <span style="margin-right:50px" id="statusSpandId" style="display:none;"> </span>
			<button type="button" id="saveJbAnswerDtlsDivId"  class="btn btn-custom btn-success">Save</button>
	  </div>
    </div>
  </div>
</div>
	
	
<!-- /.modal -->
<input type="hidden" id="hiddenActivityScopeId"></input>
<input type="hidden" id="hiddenActivityLevelId"></input>
<input type="hidden" id="hiddenCheckBxVal"></input>

<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="dist/activity/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/activity/js/custom.js" type="text/javascript"></script>
<script src="dist/activity/Date/moment.min.js" type="text/javascript"></script>
<script src="dist/activity/Date/daterangepicker.js" type="text/javascript"></script>
<script type="text/javascript" src="dragAndDropPhoto/js/jquery.filer.js?v=1.0.5"></script>
<script type="text/javascript" src="dragAndDropPhoto/js/custom-ver1.js?v=1.0.5"></script>
<script type="text/javascript" src="dragAndDropPhoto/js/uploadImage.js"></script>
<script type="text/javascript" src="js/simplePagination/simplePagination.js" ></script>
<script src="js/cadre_response.js/cadre_response.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Slick/slick.js" type="text/javascript"></script>
<script src="js/Activities/activitiesImages.js" type="text/javascript"></script>
<script src="js/Activities/activityQuestionnaire.js" type="text/javascript"></script>


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

$('.chosenSelect').chosen({width:'100%'});
$(document).ready(function(){
  /* $('#attributeTypeList').multiselect({
	noneSelectedText:"Select option(s)"
  }); */

	//$(".conductedDateCls").daterangepicker();

	$(".planedDateCls").daterangepicker();
	//$('.searchDateCls').daterangepicker();
	$('#activityTypeList').val(1);
	$('#activityLevelList').val(1);
	getActivityNames(1);
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
	
	$(".conductedDateCls").daterangepicker({singleDatePicker:true,showDropdowns: true,format:'YYYY-MM-DD',maxDate:new Date()});
	$(".conductedDateCls").val(moment().format('YYYY-MM-DD'));
	$(".planedDateCls").daterangepicker({singleDatePicker:true,format:'YYYY-MM-DD'});
	$(".planedDateCls").val(moment().format('YYYY-MM-DD'));
});

/* var fromTypeGlob;
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
  //$('.conductedDateCls').daterangepicker(optionSet1, cb);
  //$('.planedDateCls').daterangepicker(optionSet1, cb);


$(".planedDateCls").daterangepicker({singleDatePicker:true});
  $('.searchDateCls').on('show.daterangepicker', function() { console.log("show event fired"); });
  $('.searchDateCls').on('hide.daterangepicker', function() { console.log("hide event fired"); });
  	var customStartDateAlert = moment().format('DD/MM/YYYY')
	var customEndDateAlert = moment().format('DD/MM/YYYY');
}); */

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
	$('#villageWardsList').append('<option value="0"> Select Panchayat/Ward</option>');
	
	$("#constituencyDivId").hide();
	$("#mandalDivId").hide();
	$("#panchayatDivId").hide();
	$("#districtDivId").hide();
	$("#constiList").val(0);
	$('#ActivityList').find('option').remove();
	
	
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
				//$('#ActivityList').append('<option value="0"> Select Activity </option>');	
				for(var i in result)
					$('#ActivityList').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');	
				
				//$('#ActivityList').val(51);
				$('#ActivityList').trigger('change');
			}
		});
		
}


function getUserAccessConstituencyList()
{
	//$("#procesingImg3").show();
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
				//$("#procesingImg3").hide();
				for(var i in result.hamletVoterInfo)
					$('#constiList').append('<option value="'+result.hamletVoterInfo[i].id+'">'+result.hamletVoterInfo[i].name+'</option>');
			}
		});
		
}
function getUserAccessDistrictList()
{
	$('#districtList').find('option').remove();
	$('#districtList').append('<option value="0"> Select District</option>');	
	
	var activityId = $("#ActivityList").val();
	var jObj = {
			activityId : activityId
		};
		$("#procesingImg2").show();
		$.ajax({
          type:'GET',
          url: 'getDistrictsListForActivitiesAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			
			if(result != null && result.length >0)
			{	
				$("#procesingImg2").hide();
				$('#districtList').find('option').remove();
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
			$("#procesingImg5").show();
			$.ajax({
				type : "POST",
				url : "getPanchayatWardByMandalAction.action",
				data : {task:JSON.stringify(jsObj)} 
			}).done(function(result){
				
				if(result != null && result.length >0)
				{
					$("#procesingImg5").hide();
					for(var i in result)
						$('#villageWardsList').append('<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>');
				}	
		});	
			
	}
	
function buildingResults(result,locationName){
	 
	var str = '';
	
			str+='<table class="table table-bordered m_top20 table-condensed" style="font-size:12px;"" id="constiTableId">';
		str+='<thead>';
		str+='<th style="width:50px;"> </th>';
		str+='<th style="padding-left: 72px;"> MEMBER </th>';
		str+='<th style="padding-left: 19px;"> MOBILE NO </th>';
		str+='<th style="padding-left: 19px;"> AGE </th>';
		str+='<th style="padding-left: 19px;"> GENDER </th>';
		str+='<th style="padding-left: 19px;"> CASTE NAME </th>';
		str+='<th style="padding-left: 19px;"> Update Mobile No </th>';
		//str+='<th style="padding-left: 19px;"> Caller Feedback</th>';
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
		// str+='<td ><div class="updateBtnCls" attr_tdp_cadre_id="'+result[i].id+'"><i class="glyphicon glyphicon-ok pull-right" style="padding:4px;border-radius:50%;background:#ccc;color:#FFFFFF;color:green;right:35px;cursor:pointer"></i></div></td>';
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

function getLocationDetailsForActivity(startDate,endDate,optionId,questionId,searchById)
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
	if(activityTypeId == null || activityTypeId == 0)
	{
		errStr+="Please Select Activity Type.";
	}
	else if(activityLevelId == null || activityLevelId == 0)
	{
		errStr+="Please Select Activity Level.";
	}
	else if(ActivityId == null || ActivityId == 0)
	{
		errStr+="Please Select Activity .";
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
			
			var activityScopeId = $('#ActivityList').val();
			var jObj = {
				startDate:startDate,
				endDate:endDate,
				checkedValue:value,
				activityScopeId:$('#ActivityList').val(),
				activityLevelId:activityLevelId,
				searchBy:searchBy,
				locationId:locationId,
				task:"getLocationDetailsForActivity",
				constituencyId : constituencyId,
				optionId : optionId,
				questionId : questionId
			};		
			$.ajax({
				  type:'GET',
				  url: 'getLocationDetailsForActivity.action',
				 data : {task:JSON.stringify(jObj)} ,
			 }).done(function(result){			
					//console.log(result);
					var str='';
					if( result!= null)
					{
						$("#buildAssConsActivity").hide();
						$("#hideAsmblyData").hide();
						$("#showAsmblyData").show();
						str+='<table class="table table-bordered bg_ff" id="locatiionsTab">';
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
						//str+='<th style="background-color:#00B17D; color:#fff;">PLANNED DATE</th>';
						if(activityScopeId != 16)
							str+='<th style="background-color:#00B17D; color:#fff;">CONDUCTED DATE</th>';
						
						//str+='<th>PRESIDENT</th>';
						//str+='<th>GENERAL SECRETARY</th>';
						str+='<th style="background-color:#00B17D; color:#fff;">COMMITTEE MEMBERS</th>';
						str+='</tr>';
						str+='</thead>';
						
						if(result.result != null && result.result.length>0){
							for(var i in result.result)
							{
								if(!$("#imageChekId").is(':checked'))
								{
								str+='<tr>';
								//str+='<td></td>';
								str+='<input type="hidden" value="'+activityLevelId+'" name="activityVO.activityVoList['+i+'].locationLevel">';
								str+='<input type="hidden" value="'+result.result[i].locationId+'" name="activityVO.activityVoList['+i+'].locationValue">';
								str+='<td> '+result.result[i].locationName+'</td>';
								/*str+='<td  style="text-align:center;width:180px">';
								str+='<div class="input-g1 input-group">';
									str+='<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>';
									if(result.result[i].planedDate != null)
										str+='<input type="text" class="dateCls form-control"  name="activityVO.activityVoList['+i+'].plannedDate" value="'+result.result[i].planedDate+'"/>';
									else
										str+='<input type="text" class="dateCls form-control"  name="activityVO.activityVoList['+i+'].plannedDate" value=""/>';
								
								str+='</div>  <i class="glyphicon glyphicon-ok updateDateDetls"></i> </td>';*/
								if(activityScopeId != 16){
									str+='<td  style="text-align:center;width:180px">';
									str+='<div class="input-g1 input-group">';
										str+='<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>';
										if(result.result[i].conductedDate != null)
											str+='<input type="text" id="dateId'+result.result[i].locationId+'" class="dateCls conductedDtCls form-control" name="activityVO.activityVoList['+i+'].conductedDate" value="'+result.result[i].conductedDate+'"/>';
										else
											str+='<input type="text" id="dateId'+result.result[i].locationId+'" class="dateCls conductedDtCls form-control" name="activityVO.activityVoList['+i+'].conductedDate" value=""/>';
										str+='<span class="input-group-addon" style="padding:6px 0px;" title="Click here to update Details."><i class="glyphicon glyphicon-ok updateDateDetls" attr_location_Value="'+result.result[i].locationId+'" attr_date="dateId'+result.result[i].locationId+'"  style="cursor:pointer;" attr_img="img'+result.result[i].locationId+'"></i></span>';
									str+='</div>';
								}
								str+='<div id="errdateId'+result.result[i].locationId+'" class="errCls"  style="color:red;"></div> </td>';
								
								
								/*
								if(result.result[i].hamletsOfTownship != null && result.result[i].hamletsOfTownship.length>0)
								{
									if(result.result[i].hamletsOfTownship.length >= 1)
										str+='<td  style="text-align:center;"> '+result.result[i].hamletsOfTownship[0].name+'<br>'+result.result[i].hamletsOfTownship[0].partno+' </td>';
									if(result.result[i].hamletsOfTownship.length >= 2)
										str+='<td  style="text-align:center;"> '+result.result[i].hamletsOfTownship[1].name+'<br>'+result.result[i].hamletsOfTownship[1].partno+' </td>';
								}else{
									str+='<td  style="text-align:center;"> - </td>';
									str+='<td  style="text-align:center;"> - </td>';
								}
								*/
								str+='<td style="text-align:center;padding-left:0px;padding-right:0px;">';
								<!--str+='<input type="button" value="Popup" class="btn btn-success btn-xs" attr_location_Value="'+result.result[i].locationId+'" attr_location_Name=\''+result.result[i].locationName+'\' id="activityCadrePopup" />&nbsp;&nbsp;';-->
								if(activityScopeId != 17){
								str+='<input type="button" value="View" class="btn btn-success btn-xs" onclick="gettingCadreDetails('+result.result[i].locationId+',\''+result.result[i].locationName+'\',\''+constituencyId+'\');"/>&nbsp;&nbsp;';
								str+='<input type="button" value="Update Questionnaire" attr_location_Value="'+result.result[i].locationId+'" attr_location_Name=\''+result.result[i].locationName+'\' class="btn btn-success btn-xs" id="updateQBtnId" attr_date="dateId'+result.result[i].locationId+'"/>';
								}
									if(activityScopeId != 16){
										if(result.result[i].isAlreadyImageUpload == "true"){								
											str+='<img attr_location_Value="'+result.result[i].locationId+'" attr_location_Name=\''+result.result[i].locationName+'\' id="uploadImagesId" style="position:absolute;width: 40px; height: 40px; margin-left: 10px;background:#FFA500;" src="images/ImageUpload1.png"  title="Upload Images" attr_date="dateId'+result.result[i].locationId+'" />';
										}else{
											str+='<img attr_location_Value="'+result.result[i].locationId+'" attr_location_Name=\''+result.result[i].locationName+'\' id="uploadImagesId" style="position:absolute;width: 40px; height: 40px; margin-left: 10px;" src="images/imageUpload.png"  title="Upload Images" attr_date="dateId'+result.result[i].locationId+'" />';
										} 
									}								
							   
								/* str+='<input type="button" value="Upload Images" attr_location_Value="'+result.result[i].locationId+'" attr_location_Name=\''+result.result[i].locationName+'\' class="btn btn-success btn-xs" id="uploadImagesId" style="margin-left: 5px;"/>'; */
								
								str+='</td>';
								str+='</tr>';
							}else if(result.result[i].isAlreadyImageUpload == "true")
							{
								str+='<tr>';
								//str+='<td></td>';
								str+='<input type="hidden" value="'+activityLevelId+'" name="activityVO.activityVoList['+i+'].locationLevel">';
								str+='<input type="hidden" value="'+result.result[i].locationId+'" name="activityVO.activityVoList['+i+'].locationValue">';
								str+='<td> '+result.result[i].locationName+'</td>';
								/*str+='<td  style="text-align:center;width:180px">';
								str+='<div class="input-g1 input-group">';
									str+='<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>';
									if(result.result[i].planedDate != null)
										str+='<input type="text" class="dateCls form-control"  name="activityVO.activityVoList['+i+'].plannedDate" value="'+result.result[i].planedDate+'"/>';
									else
										str+='<input type="text" class="dateCls form-control"  name="activityVO.activityVoList['+i+'].plannedDate" value=""/>';
								
								str+='</div>  <i class="glyphicon glyphicon-ok updateDateDetls"></i></td>';*/
								if(activityScopeId != 16){
								str+='<td  style="text-align:center;width:180px">';
								str+='<div class="input-g1 input-group">';
									str+='<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>';
									if(result.result[i].conductedDate != null)
										str+='<input type="text" id="dateId'+result.result[i].locationId+'" class="dateCls conductedDtCls form-control" name="activityVO.activityVoList['+i+'].conductedDate" value="'+result.result[i].conductedDate+'" readonly="true"/>';
									else
										str+='<input type="text" id="dateId'+result.result[i].locationId+'" class="dateCls conductedDtCls form-control" name="activityVO.activityVoList['+i+'].conductedDate" value="" />';
									str+='<span class="input-group-addon" style="padding:6px 0px;"  title="Click here to update Date Details."><i class="glyphicon glyphicon-ok updateDateDetls" attr_location_Value="'+result.result[i].locationId+'" attr_date="dateId'+result.result[i].locationId+'"  style="cursor:pointer;" attr_img="img'+result.result[i].locationId+'"></i></span>';
									
								str+='</div>';
								str+='<div id="errdateId'+result.result[i].locationId+'" class="errCls"  style="color:red;"></div>   </td>';
								}
								/*
								if(result.result[i].hamletsOfTownship != null && result.result[i].hamletsOfTownship.length>0)
								{
									if(result.result[i].hamletsOfTownship.length >= 1)
										str+='<td  style="text-align:center;"> '+result.result[i].hamletsOfTownship[0].name+'<br>'+result.result[i].hamletsOfTownship[0].partno+' </td>';
									if(result.result[i].hamletsOfTownship.length >= 2)
										str+='<td  style="text-align:center;"> '+result.result[i].hamletsOfTownship[1].name+'<br>'+result.result[i].hamletsOfTownship[1].partno+' </td>';
								}else{
									str+='<td  style="text-align:center;"> - </td>';
									str+='<td  style="text-align:center;"> - </td>';
								}
								*/
								
								str+='<td style="text-align:center;padding-left:0px;padding-right:0px;">';
								<!--str+='<input type="button" value="Popup" class="btn btn-success btn-xs" attr_location_Value="'+result.result[i].locationId+'" attr_location_Name=\''+result.result[i].locationName+'\' id="activityCadrePopup" />&nbsp;&nbsp;';-->
								str+='<input type="button" value="View" class="btn btn-success btn-xs" onclick="gettingCadreDetails('+result.result[i].locationId+',\''+result.result[i].locationName+'\',\''+constituencyId+'\');"/>&nbsp;&nbsp;';
								str+='<input type="button" value="Update Questionnaire" attr_location_Value="'+result.result[i].locationId+'" attr_location_Name=\''+result.result[i].locationName+'\' class="btn btn-success btn-xs" id="updateQBtnId" attr_date="dateId'+result.result[i].locationId+'"/>';
								if(activityScopeId != 16){
								if(result.result[i].isAlreadyImageUpload == "true")				
									str+='<img attr_location_Value="'+result.result[i].locationId+'" attr_location_Name=\''+result.result[i].locationName+'\' id="uploadImagesId" style="position:absolute;width: 40px; height: 40px; margin-left: 10px;background:#FFA500;" src="images/ImageUpload1.png"  title="Upload Images" attr_date="dateId'+result.result[i].locationId+'" />';
								}
								else
								{
									str+='<img attr_location_Value="'+result.result[i].locationId+'" attr_location_Name=\''+result.result[i].locationName+'\' id="uploadImagesId" style="position:absolute;width: 40px; height: 40px; margin-left: 10px;background:#FFA500;" src="images/ImageUpload.png"  title="Upload Images" attr_date="dateId'+result.result[i].locationId+'" />';
								}
								/* str+='<input type="button" value="Upload Images" attr_location_Value="'+result.result[i].locationId+'" attr_location_Name=\''+result.result[i].locationName+'\' class="btn btn-success btn-xs" id="uploadImagesId" style="margin-left: 5px;"/>'; */
								
								str+='</td>';
								str+='</tr>';
							}
							}
							str+='</table>';
						}
					}
					$('#home').html(str);
					
					$('#plannedDate').daterangepicker({singleDatePicker:true,format: 'DD/MM/YYYY'});
					$('#conductedDate').daterangepicker({singleDatePicker:true,format: 'DD/MM/YYYY'});
					/*$('#home').append(' <div style="position:fixed;bottom:0;margin-left:-30px"><input type="button" value="UPDATE DATE DETAILS" class="btn btn-custom btn-success" onclick="submitForm();"/></div>');
					*/
					$('.dateCls').daterangepicker({singleDatePicker:true,format: 'DD/MM/YYYY'});
					
					$("#locatiionsTab").dataTable({
					"iDisplayLength": 20,
					"aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
					});
					$("#locatiionsTab").removeClass("dataTable");
					$("#constncyId").html(''+$("#constiList option:selected").text()+' constituency ');
					$('#headingId').html(' '+$("#activityLevelList option:selected").text()+' - '+$("#ActivityList option:selected").text()+'');
				});	
	//}
	
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
	
	var locationTypeId = $('#activityLevelList').val();
	var activityLevellId = $('#activityLevelList').val();
	if(activityLevellId == 3){
		locationId = locationId;
	}else{
		locationId = ""+locationId+"";
	var firstChar = locationId.substr(0,1);
	//console.log(firstChar);
	 locationId = locationId.slice(1);
	} 
	
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
	}else if(activityLevellId == 3){
		locationType = 3;
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

 
 //$("#ActivityListTest").change(function(){
 function assemblyConsWiseActivitiesCount(){
	
	var activityTypeId =$('#activityTypeList').val();
	var activityLevelId =$('#activityLevelList').val();
	var ActivityId =$('#ActivityList').val();
	var districtId =$('#districtList').val();
	
	if(ActivityId == 17){
		getActivityDatesByScopeId();
		return;
	}
	
	getQuestions();
	getActivityDatesByScopeId();
	//$('#ErrDiv').html("");
	var errStr ='';
	if(activityTypeId == null || activityTypeId == 0)
	{
		errStr+="Please Select Activity Type.";
	}
	else if(activityLevelId == null || activityLevelId == 0)
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
    });   
 }
   
$(document).on("click","#showAsmblyData",function(){
	 $("#buildAssConsActivity").show();
	 $("#assblyBody").show();
	 $("#showAsmblyData").hide();
	 $("#hideAsmblyData").show();
});
$(document).on("click","#hideAsmblyData",function(){
	 $("#buildAssConsActivity").hide();
	 $("#assblyBody").hide();
	 $("#showAsmblyData").show();
	 $("#hideAsmblyData").hide();
});
  function buildAsemblyConstWiseActivities(result,levelId){
	$("#buildAssConsActivity").hide();
	$("#assblyBody").hide();
	$("#hideAsmblyData").hide();
	$("#showAsmblyData").show();
   
	if(levelId == 3){
		$("#districtHeadingId").show();
	}
		
	else{
		$("#constituencyHeadingId").show();
	}
	 
    var str ='';
	var totalCount;
    str+='<table class="table table-bordered table-responsive bg_ff dataTableDiv">';
          str+='<thead>';
            str+='<tr role="row">';
			if(levelId == 3)
				str+='<th style="background-color: rgb(0, 177, 125); color:#fff;cursor:pointer;" class="text_center"> DISTRICT </th>';
			else
				str+='<th style="background-color: rgb(0, 177, 125); color:#fff;cursor:pointer;" class="text_center">ASSEMBLY CONSTITUENCY </th>';
              //str+='<th style="background-color: rgb(0, 177, 125); color:#fff;cursor:pointer;" class="text_center">ASSEMBLY CONSTITUENCY </th>';
             // str+='<th style="background-color: rgb(0, 177, 125); color:#fff;cursor:pointer;" class="text_center" >PLANNED ACTIVITIES</th>';
			  //str+='<th style="background-color: rgb(0, 177, 125); color:#fff;cursor:pointer;"  class="text_center">NOT PLANNED ACTIVITIES</th>';
			   str+='<th style="background-color: rgb(0, 177, 125); color:#fff;cursor:pointer;" class="text_center">TOTAL </th>';
              str+='<th style="background-color: rgb(0, 177, 125); color:#fff;cursor:pointer;"  class="text_center"> CONDUCTED </th>';
			  str+='<th style="background-color: rgb(0, 177, 125); color:#fff;cursor:pointer;" class="text_center" >NOT CONDUCTED </th>';
			  str+='<th style="background-color: rgb(0, 177, 125); color:#fff;cursor:pointer;" class="text_center" >NOT UPDATED </th>';
            str+='</tr>';
          str+='</thead>';
        str+='<tbody>';
          for(var i in result){
			  
			  var flag = getMatchedLocationId(result[i].id);
			   if (flag == "false"){
				   continue;
			   }
			  
            str+='<tr class="text_center">';
              
                str+='<td >'+result[i].name+'</td>';
              total = result[i].conductedCount+result[i].nonConductedCount+result[i].notUpdatedCount;
             if(total != null && total !=0){
                str+='<td >'+total+'</td>';
              }else{
                str+='<td>'+total+'</td>';
              }
              if(result[i].conductedCount != null && result[i].conductedCount != 0){
                str+='<td >'+result[i].conductedCount+'</td>';
              }else{
                str+='<td >'+result[i].conductedCount+'</td>';
              }
			  if(result[i].nonConductedCount !=null && result[i].nonConductedCount != 0){
                str+='<td >'+result[i].nonConductedCount+'</td>';
              }else{
                str+='<td >'+result[i].nonConductedCount+'</td>';
              }
              if(result[i].notUpdatedCount !=null && result[i].notUpdatedCount != 0){
                str+='<td >'+result[i].notUpdatedCount+'</td>';
              }else{
                str+='<td>'+result[i].notUpdatedCount+'</td>';
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
		if(optionId >0)
			getQuestionnaire(locationValue,questionId,optionId,subQustionDivId,serialNoTypeId,0);
		
	});
  
     function getMatchedLocationId(locationId) {
		 var flag = "false";
		 for(var i in globalUserAccessLocationObjArr) {
			 if (globalUserAccessLocationObjArr[i].id == locationId){
				 flag="true";
				 return flag;
			 } 
		 }
		 return flag;
	 }
  
	
	$(document).on("click","#updateQBtnId",function(){
		var dateFieldId = $(this).attr('attr_date');
		/*$(".errCls").html("");
		$("#err"+dateVal).html("");
		if($("#"+dateVal).val() == ""){
			$("#err"+dateVal).html("Date Required");
			return;
		}*/
		var locationValue = $(this).attr("attr_location_Value");
		getQuestionnaire(locationValue,0,0,'questionsDivBodyId',1,dateFieldId);
 	});
    function getQuestionnaire(locationValue,questionId,optionId,divId,serialNoTypeId,dateFieldId){
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
				optionId:optionId
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
	}
	
	$(document).on("click","#saveResult",function(){
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
				 conductedDate:''
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
		var locatinId = $(this).attr("attr_info_id");
		var conducttdDate = $('#date'+locatinId+'').val();
		$('#date'+locatinId+'').css('border-color','grey');
		if(conducttdDate == null || conducttdDate.trim().length==0){
			$('#date'+locatinId+'').css('border-color','red');
			return;
		}
				
		var locationName = $(this).attr("attr_location_Name");
		gobalLevelValue = $(this).attr("attr_location_Value");
		//var dateStr = $("#"+dateVal).val();
		var locationInfoId = $(this).attr("attr_act_location_info_id");
		var tableName = $(this).attr("attr_table_name");
		var activityLevelTextId=$("#activityLevelList option:selected").text();
		setGobalValues();
		
		var newwindow = window.open('eventFieUploadAction.action?activityScopeId='+gobalActivityScopeId+'&locationValue='+gobalLevelValue+'&activityLevel='+gobalLevelId+'&locationName='+locationName+'&gobalTempVar='+gobalTempVar+'&temp='+locationInfoId+'&activityLevelTextId='+activityLevelTextId+'&task='+tableName+'','Upload Images for activity','width=700,height=900,toolbar=0,menubar=0,location=0');
        newwindow.focus();
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
		getActivityNames(0);
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
				//activityLocationInfoId:lctnInfoId,
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
				//activityLocationInfoId:lctnInfoId,
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
		if(globalLvelId == 5){
			$("#constituencyDivId").show();
		}else{
			$("#constituencyDivId").hide();
		}
		var scopeId =$('#ActivityList').val();
		var activityLevelId = $('#activityLevelList').val();
		$("#procesingImg3").show();
		if(activityLevelId == 5){
		$('#constiList').find('option').remove();
		var districtId = $(this).val();
		var jObj = {
			task:"getConstituenciesForDistrict",
			districtId:districtId,
			activityScopeId : scopeId
		};
		//$("#procesingImg3").show();
		$.ajax({
          type:'GET',
          url: '	getConstituenciesForADistrictsAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			$("#procesingImg3").hide();
			if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1)
			{}else{
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
	var scopeId = $('#ActivityList').val();
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

		var dateVal = $(this).attr('attr_date');
		var attrImgCls = $(this).attr('attr_img');
		$(".errCls").html("");
		$("#err"+dateVal).html("");
		/*if($("#"+dateVal).val() == ""){
			$("#err"+dateVal).html("Date Required");
			return;
		}*/
	
	var jObj = {
			plannedDateStr : "",
			conductedDateStr : $("#"+dateVal).val(),
			locationValue : $(this).attr('attr_location_value'),
			activityScopeId  : $('#ActivityList').val(),
			activityLevelId:$('#activityLevelList').val(),
			contituencyId  : $('#constiList').val(),
			task:"saveActivityDetails",
			day:1
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
$(document).on("click","#closeButtonId",function(){
	setTimeout(function(){
		$("body").addClass('modal-open');
	},1000)
	
});
var globalLvelId;
$(document).on("change","#ActivityList",function(){
	 globalLvelId = $("#activityLevelList").val();
	if(globalLvelId == 3 || globalLvelId ==5){
		getUserAccessDistrictList();
	}else if(globalLvelId == 1 || globalLvelId == 2){
		getConstitiensList();
	}
});

var globalUserAccessLocationObjArr;
function getConstitiensList(){ 	
	$('#constiList').find('option').remove();
	$('#constiList').append('<option value="0">Select Constituency</option>');
	$("#procesingImg3").show();
	var activityId = $("#ActivityList").val();
	
	$('#villageWardsList').find('option').remove();
	$('#villageWardsList').append('<option value="0">All</option>');
	$('#mandalsList').find('option').remove();
	$('#mandalsList').append('<option value="0">All</option>');
	var jsObj={	
			activityId:activityId,
			stateId:1			
		}
		$.ajax({
			type:'GET',
			url: 'getConstituenciesForActivitiesAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		   }).done(function(result){
			   globalUserAccessLocationObjArr = result;
			   if(result != null && result.length >0)
			{
				$("#procesingImg3").hide();
				$('#constiList').find('option').remove();
				$('#constiList').append('<option value="0">Select Constituency </option>');
				for(var i in result)
					$('#constiList').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
			}
		   });
	}
$(document).on("change","#constiList",function(){
	$('#mandalsList').find('option').remove();
	$('#mandalsList').append('<option value="0">All</option>');
	var constituencyId = $(this).val();
	var activityScopeId = $("#ActivityList").val();
	$('#villageWardsList').find('option').remove();
	$('#villageWardsList').append('<option value="0">All</option>');
	var jsObj={	
			constituencyId:constituencyId,
			activityScopeId : activityScopeId			
		}
		$("#procesingImg").show();
		$.ajax({
			type:'GET',
			url: 'getMandalsForConstituenciesAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		   }).done(function(result){
			   $("#procesingImg").hide();
			   if(result != null && result.length >0)
			{
				for(var i in result)
					$('#mandalsList').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
			}
		   });
});
$(document).on("change","#mandalsList",function(){
	$('#villageWardsList').find('option').remove();
	$('#villageWardsList').append('<option value="0">All</option>');
	var mandalId = $(this).val();
	var activityScopeId = $("#ActivityList").val();
	
		var locationType="";
		 if (mandalId != null ) {
			  if (mandalId.charAt(0)==2) {
				  locationType = "panchayat";
			  } else if (mandalId.charAt(0)==1) {
				  locationType = "ward";
			  }
		 }
	var jsObj={	
			mandalId:mandalId,
			activityScopeId : activityScopeId
		}
		$.ajax({
			type:'GET',
			url: 'getPanchayatsForMandalsAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		   }).done(function(result){
			   if(result != null && result.length >0)
				{
					for(var i in result) 
					$('#villageWardsList').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
			   }
			   /*adding locationtype because in this dropdown in the case of mandal selection panchayat ,in the case of muncipality 
				 ward will build */
				 $("#villageWardsList").attr("attr_location_type",locationType);
		   });
});

var round=0;
var attributesArr=[];
function getLocationWiseDetailsForActivity(roundId)
{
	assemblyConsWiseActivitiesCount();
	
	 $('#conductedDateId,#conductedStatus').css("border-color","grey");
	$("#chCkBxErrMsgId").html('');
	if(roundId==0){
		$('#resultsDiv').hide();
		round=0;
		$('#conductedStatus').val(0);
		$('#conductedDateId').val('');
		$('#conductedDateDivId').hide();
		
	}
	if(roundId == 1){
		var status = $('#conductedStatus').val();
		 if(status == 0){
			  $("#chCkBxErrMsgId").html('<span style="color:red;"> Please Select Conducted Status </span> ');
			  $('#conductedStatus').css("border-color","red");
			 return;
		 }
	}
//alert(222);	
	//getActivityStatusDetailsByScopeIdAndLocationValue();
	//$("#questionsDiv").show();
	//$("#activityresponsesDiv").show();
	$('#conductedDateId').val('');
	var activityTypeId =$('#activityTypeList').val();
	var activityLevelId =$('#activityLevelList').val();
	var ActivityId =$('#ActivityList').val();
	var constituencyId =$('#constiList').val();
	var districtId =$('#districtList').val();
	var attrTypeId=$("#attributeTypeList").val();
	$("#errCnstId").html("");
		
	if(constituencyId == 0)
	{
		if(activityLevelId == 1 || activityLevelId == 2){
			$("#errCnstId").html("Please Select One Constituency.");
			return;
		}
	}
		//$('#loadingsymbl').html("<img src='images/Loading-data.gif'/>");
		$('#home').html("<img src='images/Loading-data.gif'/>");
		$('#resultsDiv').show();
			var searchBy="panchayat";
			var locationId = $('#villageWardsList').val();	
			var locationType =  $("#villageWardsList").attr("attr_location_type");
			if(locationId == 0)
			{
				locationId = $('#mandalsList').val();
				searchBy = "mandal";
				
				 if(locationId == 0)
				{
					locationId = $('#constiList').val();
					searchBy = "constituency";
					if(locationId == 0)
					{
					locationId = $('#districtList').val();
					searchBy = "district";
					}
				}else if (locationId.charAt(0)==1){
					searchBy = "Muncipality";
				}
				
			}else{
				/* if (locationId.charAt(0)==2){ */
				searchBy = locationType;
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
				if($("#notUpdatedId").is(':checked'))
					value = "notupdated";
			}
		
			var activityScopeId = $('#ActivityList').val();
			 var statusId = $('#conductedStatus').val();
			$("#imageButId").show();
			var jObj = {
				//startDate:startDate,
				//endDate:endDate,
				checkedValue:value,
				activityScopeId:$('#ActivityList').val(),
				activityLevelId:activityLevelId,
				searchBy:searchBy,
				locationId:locationId,
				task:""
				//constituencyId : constituencyId,
				//optionId : optionId,
				//questionId : questionId
			};		
			$.ajax({
				  type:'POST',
				  url: 'getLocationDetailsForActivityAction.action',
				 data : {task:JSON.stringify(jObj)} ,
			 }).done(function(result){
				 $('#statsErrDiv').html('');
				 var str='';
				 	if( result!= null)
					{
						$("#attributeTypeId").show();
					
					if(round==0){
						round = 1;
						
						if(result.length>0 && result[0].subList != null && result[0].subList.length > 0){
							$("#attributeTypeList").empty();
							$("#attributeTypeList").append('<option value="0">Select Option</option>');
							$("#attributeTypeList").trigger("chosen:updated");
							
							attributesArr=[];
							if(attributesArr.length==0){
								 for(var i in result[0].subList){
									 var obj = {"id":result[0].subList[i].id, "name":result[0].subList[i].name}
									 attributesArr.push(obj);
								 }
							}
						  for(var i in result[0].subList){
						   //$("#attributeTypeList").css('display','block');
						   if(result[0].subList[i].id != null){
								$("#attributeTypeList").append('<option value="'+result[0].subList[i].id+'" selected>'+result[0].subList[i].name+'</option>');
							}
						  }
						  $("#attributeTypeList").trigger("chosen:updated");
						}else{
							$("#attributeTypeList").empty();
							$("#attributeTypeList").append('<option value="0">Select Option</option>');
							$("#attributeTypeList").trigger("chosen:updated");
							
							
							if(attributesArr != null && attributesArr.length>0){
								for(var k in attributesArr)
									 $("#attributeTypeList").append('<option value="'+attributesArr[k].id+'" selected>'+attributesArr[k].name+'</option>');
							}
							 $("#attributeTypeList").trigger("chosen:updated");
						}
						$('#attributeTypeList').trigger('change');
							$('#loadingsymbl').html("");
						getLocationWiseDetailsForActivity(1);
						return;
					}
					
						$('#home').html("<img src='images/Loading-data.gif'/>");
						$('#loadingsymbl').html("");
						$('#resultsDiv').show();
					
						str+='<table class="table table-bordered bg_ff" id="locationsTab">';
						str+='<thead>';
						str+='<tr>';
						//str+='<th>CONSTITUENCY</th>';
						//str+='<th style="background-color:#00B17D; color:#fff;" > <input type="checkbox" class="allcheckBoxCls" name="activityVO.isChecked"/> Select All </th>';
						if(activityLevelId == 2)
							str+='<th style="background-color:#00B17D; color:#fff;">MANDAL/ TOWN/ DIVISION </th>';
						else if(activityLevelId == 1){	
							//str+='<th style="background-color:#00B17D; color:#fff;">MANDAL/ TOWN/ DIVISION</th>';
							str+='<th style="background-color:#00B17D; color:#fff;">PANCHAYAT/ WARD</th>';
						}
						else if(activityLevelId == 5)
							str+='<th style="background-color:#00B17D; color:#fff;">CONSTITUENCY </th>';
						else if(activityLevelId == 4)
							str+='<th style="background-color:#00B17D; color:#fff;">STATE </th>';
						else if(activityLevelId == 3)
							str+='<th style="background-color:#00B17D; color:#fff;">DISTRICT </th>';
						
						str+='<th style="background-color:#00B17D; color:#fff;"> STATUS </th>';
							
						if(dataArr == null || dataArr == "" || typeof(dataArr) == "undefined"){
							str+='<th style="background-color:#00B17D; color:#fff;">CONDUCTED DATE</th>';
							//str+='<th style="background-color:#00B17D; color:#fff;">IVR STATUS</th>';
						}else{
							for(var i in dataArr){
								if(parseInt(dataArr[i].trim()) == 5){
									str+='<th style="background-color:#00B17D; color:#fff;">PLANNED DATE</th>';
								}else if(parseInt(dataArr[i].trim()) == 6){
									str+='<th style="background-color:#00B17D; color:#fff;">CONDUCTED DATE</th>';
								}else if(parseInt(dataArr[i].trim()) == 7){
								//	str+='<th style="background-color:#00B17D; color:#fff;">IVR STATUS</th>';
								}else if(parseInt(dataArr[i].trim()) == 1){
									str+='<th style="background-color:#00B17D; color:#fff;">UPLOAD IMAGES</th>';
									str+='<th style="background-color:#00B17D; color:#fff;">TOTAL IMAGES</th>';
								}
							}
						}
						
					//	str+='<th style="background-color:#00B17D; color:#fff;">COMMITTEE MEMBERS</th>';
						str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
						for(var i in result){
							var locationName="";
							str+='<tr>';
							str+='<input type="hidden" value="'+result[i].tableName+'" name="activityVO.activityVoList['+i+'].table">';
							str+='<input type="checkbox" id="check'+result[i].activityLocatInfoId+'" class="checkBoxCls" attr_no="'+i+'" value="'+result[i].activityLocatInfoId+'" name="activityVO.activityVoList['+i+'].isChecked" style="display:none;"/>';
							
							//str+='<td><input type="checkbox" id="check'+result[i].activityLocatInfoId+'" class="checkBoxCls" attr_no="'+i+'" value="'+result[i].activityLocatInfoId+'" name="activityVO.activityVoList['+i+'].isChecked"/></td>';
							str+='<input type="hidden" id="chckBxIdVal'+i+'" value="'+result[i].activityLocatInfoId+'" name="activityVO.activityVoList['+i+'].activityLocationInfoId"/>';
							if(activityLevelId == 5){
								str+='<td id='+result[i].constituencyId+'>'+result[i].constituencyName+' <span class="glyphicon glyphicon-eye-open pull-right activityLevelIdCls" attr_activity_location_info_id='+result[i].activityLocatInfoId+' title="get question details" style="cursor:pointer;"></span></td>';
								locationName = result[i].constituencyName+" Assembly Constituency ";
							}
							else if(activityLevelId == 1){
									//str+='<td id='+result[i].mandalId+'>'+result[i].mandalName+'</td>';
									str+='<td id='+result[i].villageId+'>'+result[i].villageName+' <span class="glyphicon glyphicon-eye-open pull-right activityLevelIdCls" attr_activity_location_info_id='+result[i].activityLocatInfoId+' title="get question details" style="cursor:pointer;"></span></td>';
									
										locationName = result[i].villageName+" Village / Ward  ";
										
							}
							else if(activityLevelId == 2){
									str+='<td id='+result[i].mandalId+'>'+result[i].mandalName+' <span class="glyphicon glyphicon-eye-open pull-right activityLevelIdCls" attr_activity_location_info_id='+result[i].activityLocatInfoId+' title="get question details" style="cursor:pointer;"></span></td>';
										locationName = result[i].mandalName+" Mandal/Munci./Corp. ";
										
							}else if(activityLevelId == 3){
								str+='<td id='+result[i].districtId+'>'+result[i].districtName+' <span class="glyphicon glyphicon-eye-open pull-right activityLevelIdCls" attr_activity_location_info_id='+result[i].activityLocatInfoId+' title="get question details" style="cursor:pointer;"></span></td>';
								locationName = result[i].mandalName+" District  ";
							}
							
							str+='<td> ';
							str+=' <select id="indivdualStatus" class="updateCls statusCls'+result[i].activityLocatInfoId+'" name="activityVO.activityVoList['+i+'].status" attr_id="'+result[i].activityLocatInfoId+'"> ';
							if(result[i].conductedDate != null &&result[i].conductedDate.length>0 && result[i].status =='UPDATED'){
								str+=' <option value="Not Updated"> Not Updated </option>'; 
								str+=' <option value="Conducted" selected="true" > Conducted </option> ';
								str+=' <option value="Not Conducted" > Not Conducted </option> ';
								//str+=' <option value="Not Updated"> Not Updated </option> ';
							}
							else if((result[i].conductedDate == null || result[i].conductedDate.length<=0) && result[i].status =='UPDATED'){
								str+=' <option value="Not Updated"> Not Updated </option>  ';
								str+=' <option value="Conducted" > Conducted </option> ';
								str+=' <option value="Not Conducted"  selected="true"> Not Conducted </option> ';
								//str+=' <option value="Not Updated"> Not Updated </option> ';
							}
							else if( result[i].status =='NOT UPDATED'){
								str+=' <option value="Not Updated"> Not Updated </option>';
								str+=' <option value="Conducted" > Conducted </option> ';
								str+=' <option value="Not Conducted" > Not Conducted </option> ';
								//str+=' <option value="Not Updated"  selected="true"> Not Updated </option> ';
							}
							
							str+='</select> <i class="glyphicon glyphicon-ok1 okcls'+result[i].activityLocatInfoId+'" attr_id="'+result[i].activityLocatInfoId+'"  style="margin-left:5px;display:none;" title="Please click here to Update details "></i> </td> ';
									
									
							if(dataArr == null || dataArr == "" || typeof(dataArr) == "undefined"){
								if(result[i].conductedDate != null && result[i].conductedDate != ""){
										if(statusId=='Not Conducted')//uploadImagesId
											str+='<td ><input type="text" name="activityVO.activityVoList['+i+'].conductedDate " value="'+result[i].conductedDate+'"  class="condDateCls" id="date'+result[i].activityLocatInfoId+'" disabled/> <i class="glyphicon glyphicon-remove clearCls" attr_id="'+result[i].activityLocatInfoId+'" title="Please click here to clear Conducted Date " attr_id="'+result[i].activityLocatInfoId+'"  ></i> <i class="glyphicon glyphicon-ok " attr_id="'+result[i].activityLocatInfoId+'"  style="margin-left:5px" title="Please click here to Update details "></i><span id="Err'+result[i].activityLocatInfoId+'"></span></td>';
										else
											str+='<td ><input type="text" name="activityVO.activityVoList['+i+'].conductedDate " value="'+result[i].conductedDate+'"  class="condDateCls" id="date'+result[i].activityLocatInfoId+'" />  <i class="glyphicon glyphicon-remove clearCls" attr_id="'+result[i].activityLocatInfoId+'" title="Please click here to clear Conducted Date " attr_id="'+result[i].activityLocatInfoId+'"  ></i> <i class="glyphicon glyphicon-ok " attr_id="'+result[i].activityLocatInfoId+'"  style="margin-left:5px" title="Please click here to Update details "></i><span id="Err'+result[i].activityLocatInfoId+'"></span></td>';
									}else{
										if(statusId=='Not Conducted')
											str+='<td><input type="text" name="activityVO.activityVoList['+i+'].conductedDate "  class="condDateCls"  id="date'+result[i].activityLocatInfoId+'" disabled/> <i class="glyphicon glyphicon-remove clearCls" attr_id="'+result[i].activityLocatInfoId+'" title="Please click here to clear Conducted Date " attr_id="'+result[i].activityLocatInfoId+'"  ></i> <i class="glyphicon glyphicon-ok " attr_id="'+result[i].activityLocatInfoId+'"  style="margin-left:5px" title="Please click here to Update details "></i><span id="Err'+result[i].activityLocatInfoId+'"></span></td>';
										else
											str+='<td><input type="text" name="activityVO.activityVoList['+i+'].conductedDate "  class="condDateCls"  id="date'+result[i].activityLocatInfoId+'" /> <i class="glyphicon glyphicon-remove clearCls" attr_id="'+result[i].activityLocatInfoId+'" title="Please click here to clear Conducted Date " attr_id="'+result[i].activityLocatInfoId+'"  ></i> <i class="glyphicon glyphicon-ok " attr_id="'+result[i].activityLocatInfoId+'"  style="margin-left:5px" title="Please click here to Update details "></i><span id="Err'+result[i].activityLocatInfoId+'"></span></td>';
									}
									
									
									
									
								/*	if(result[i].ivrStatus != null && result[i].ivrStatus != "" ){
										str+='<td><select  name="activityVO.activityVoList['+i+'].ivrStatus" value="'+result[i].ivrStatus+'" id="isValidOrNotId">';
											if(result[i].ivrStatus != null && result[i].ivrStatus == 'Y'){
												str+='<option selected value="Y">Y</option>';
												str+='<option  value="N">N</option>';
											}
											else if(result[i].ivrStatus != null && result[i].ivrStatus == 'N'){
												str+='<option value="Y">Y</option>';
												str+='<option selected value="N">N</option>';
											}
										str+='</select></td>';
									}else{
										str+='<td><select  name="activityVO.activityVoList['+i+'].ivrStatus" value="'+result[i].ivrStatus+'" id="isValidOrNotId">';
												str+='<option selected value="0">Select IVR Status </option>';
												str+='<option value="Y">Y</option>';
												str+='<option  value="N">N</option>';
										str+='</select></td>';
									}*/
							}else{
								for(var k in dataArr){
								if(parseInt(dataArr[k].trim()) == 5){
									if(result[i].planedDate != null && result[i].planedDate != ""){
										if(statusId=='Not Conducted')
											str+='<td><input type="text" name="activityVO.activityVoList['+i+'].plannedDate" value="'+result[i].planedDate+'" class="condDateCls" id="date'+result[i].activityLocatInfoId+'" disabled/> <i class="glyphicon glyphicon-remove clearCls" attr_id="'+result[i].activityLocatInfoId+'" title="Please click here to clear Conducted Date " attr_id="'+result[i].activityLocatInfoId+'"  ></i> <i class="glyphicon glyphicon-ok " attr_id="'+result[i].activityLocatInfoId+'"  style="margin-left:5px" title="Please click here to Update details "></i><span id="Err'+result[i].activityLocatInfoId+'"></span></td>';
										else
											
											str+='<td><input type="text" name="activityVO.activityVoList['+i+'].plannedDate" value="'+result[i].planedDate+'" class="condDateCls" id="date'+result[i].activityLocatInfoId+'" /> <i class="glyphicon glyphicon-remove clearCls" attr_id="'+result[i].activityLocatInfoId+'" title="Please click here to clear Conducted Date " attr_id="'+result[i].activityLocatInfoId+'"  ></i> <i class="glyphicon glyphicon-ok " attr_id="'+result[i].activityLocatInfoId+'"  style="margin-left:5px" title="Please click here to Update details "></i><span id="Err'+result[i].activityLocatInfoId+'"></span></td>';
										}else{
											if(statusId=='Not Conducted')
												str+='<td> <input type="text" name="activityVO.activityVoList['+i+'].plannedDate"  class="condDateCls" id="date'+result[i].activityLocatInfoId+'" disabled/> <i class="glyphicon glyphicon-remove clearCls" attr_id="'+result[i].activityLocatInfoId+'" title="Please click here to clear Conducted Date " attr_id="'+result[i].activityLocatInfoId+'"  ></i>  <i class="glyphicon glyphicon-ok " attr_id="'+result[i].activityLocatInfoId+'"  style="margin-left:5px" title="Please click here to Update details "></i><span id="Err'+result[i].activityLocatInfoId+'"></span></td>';
											else
												str+='<td> <input type="text" name="activityVO.activityVoList['+i+'].plannedDate"  class="condDateCls" id="date'+result[i].activityLocatInfoId+'" />  <i class="glyphicon glyphicon-remove clearCls" attr_id="'+result[i].activityLocatInfoId+'" title="Please click here to clear Conducted Date " attr_id="'+result[i].activityLocatInfoId+'"  ></i> <i class="glyphicon glyphicon-ok " attr_id="'+result[i].activityLocatInfoId+'"  style="margin-left:5px" title="Please click here to Update details "></i><span id="Err'+result[i].activityLocatInfoId+'"></span></td>';
										}
									}else if(parseInt(dataArr[k].trim()) == 6){
										if(result[i].conductedDate != null && result[i].conductedDate != ""){
											if(statusId=='Not Conducted')
												str+='<td ><input type="text" name="activityVO.activityVoList['+i+'].conductedDate " value="'+result[i].conductedDate+'"  class="condDateCls" id="date'+result[i].activityLocatInfoId+'" disabled /> <i class="glyphicon glyphicon-remove clearCls" attr_id="'+result[i].activityLocatInfoId+'" title="Please click here to clear Conducted Date " attr_id="'+result[i].activityLocatInfoId+'"  ></i> <i class="glyphicon glyphicon-ok " attr_id="'+result[i].activityLocatInfoId+'"  style="margin-left:5px" title="Please click here to Update details "></i><span id="Err'+result[i].activityLocatInfoId+'"></span></td>';
											else
												str+='<td ><input type="text" name="activityVO.activityVoList['+i+'].conductedDate " value="'+result[i].conductedDate+'"  class="condDateCls" id="date'+result[i].activityLocatInfoId+'" /> <i class="glyphicon glyphicon-remove clearCls" attr_id="'+result[i].activityLocatInfoId+'" title="Please click here to clear Conducted Date "> </i><i class="glyphicon glyphicon-ok " attr_id="'+result[i].activityLocatInfoId+'"  style="margin-left:5px" title="Please click here to Update details "></i><span id="Err'+result[i].activityLocatInfoId+'"></span></td>';
										}else{
											if(statusId=='Not Conducted')
												str+='<td><input type="text" name="activityVO.activityVoList['+i+'].conductedDate "  class="condDateCls" id="date'+result[i].activityLocatInfoId+'" disabled/> <i class="glyphicon glyphicon-remove clearCls" attr_id="'+result[i].activityLocatInfoId+'" title="Please click here to clear Conducted Date " attr_id="'+result[i].activityLocatInfoId+'"  ></i> <i class="glyphicon glyphicon-ok " attr_id="'+result[i].activityLocatInfoId+'"  style="margin-left:5px" title="Please click here to Update details "></i><span id="Err'+result[i].activityLocatInfoId+'"></span></td>';
											else
												str+='<td><input type="text" name="activityVO.activityVoList['+i+'].conductedDate "  class="condDateCls" id="date'+result[i].activityLocatInfoId+'" /> <i class="glyphicon glyphicon-remove clearCls" attr_id="'+result[i].activityLocatInfoId+'" title="Please click here to clear Conducted Date " attr_id="'+result[i].activityLocatInfoId+'"  ></i> <i class="glyphicon glyphicon-ok " attr_id="'+result[i].activityLocatInfoId+'"  style="margin-left:5px" title="Please click here to Update details "></i><span id="Err'+result[i].activityLocatInfoId+'"></span></td>';
										}
										
										
									
									
									}else if(parseInt(dataArr[k].trim()) == 7){
										/*if(result[i].ivrStatus != null && result[i].ivrStatus != "" ){
											str+='<td><select  name="activityVO.activityVoList['+i+'].ivrStatus" value="'+result[i].ivrStatus+'" id="isValidOrNotId">';
												if(result[i].ivrStatus != null && result[i].ivrStatus == 'Y'){
													str+='<option selected value="Y">Y</option>';
													str+='<option  value="N">N</option>';
												}
												else if(result[i].ivrStatus != null && result[i].ivrStatus == 'N'){
													str+='<option value="Y">Y</option>';
													str+='<option selected value="N">N</option>';
												}else{
													str+='<option selected value="0">Select IVR Status </option>';
													str+='<option value="Y">Y</option>';
													str+='<option  value="N">N</option>';
												}
											str+='</select></td>';
										}else{
											str+='<td>-</td>';
										}*/
									}else if(parseInt(dataArr[k].trim()) == 1){
											str+='<td>';
										if( result[i].status =='UPDATED' && result[i].conductedDate != null &&result[i].conductedDate.length>0)
											str+='<img class="imageCls'+result[i].activityLocatInfoId+'" style="position:absolute;width: 30px; height: 30px;background:#FFA500;cursor:pointer;" ';
										else
											str+='<img class="imageCls'+result[i].activityLocatInfoId+'" style="position:absolute;width: 30px; height: 30px;background:#FFA500;cursor:pointer;display:none;" ';
										
											if(activityLevelId == 5){
												str+=' attr_location_Value="'+result[i].constituencyId+'" attr_location_Name=\''+result[i].constituencyName+'\'  id="uploadImagesId"  src="images/imageUpload.png"  title="Upload Images"  attr_table_name="'+result[i].tableName+'" attr_act_location_info_id="'+result[i].activityLocatInfoId+'" attr_date="dateId'+result[i].constituencyId+'"  attr_info_id="'+result[i].activityLocatInfoId+'"';
											}else if(activityLevelId == 1){
												str+=' attr_location_Value="'+result[i].villageId+'" attr_location_Name=\''+result[i].villageName+'\' id="uploadImagesId" src="images/imageUpload.png"  title="Upload Images"  attr_table_name="'+result[i].tableName+'"  attr_act_location_info_id="'+result[i].activityLocatInfoId+'" attr_date="dateId'+result[i].villageId+'" attr_info_id="'+result[i].activityLocatInfoId+'"';
											}else if(activityLevelId == 3){
												str+=' attr_location_Value="'+result[i].districtId+'" attr_location_Name=\''+result[i].districtName+'\' id="uploadImagesId"  src="images/imageUpload.png"  title="Upload Images"   attr_table_name="'+result[i].tableName+'" attr_act_location_info_id="'+result[i].activityLocatInfoId+'" attr_date="dateId'+result[i].districtId+'"  attr_info_id="'+result[i].activityLocatInfoId+'"';
											}else if(activityLevelId == 2){
												str+=' attr_location_Value="'+result[i].mandalId+'" attr_location_Name=\''+result[i].mandalName+'\' id="uploadImagesId" src="images/imageUpload.png"  title="Upload Images"  attr_table_name="'+result[i].tableName+'"  attr_act_location_info_id="'+result[i].activityLocatInfoId+'" attr_date="dateId'+result[i].mandalId+'" attr_info_id="'+result[i].activityLocatInfoId+'"';
											}
										
										str+='/></td>';
										
										if(result[i].count != null && result[i].count > 0){
											str+='<td> <span class="imageCls1'+result[i].activityLocatInfoId+'">'+result[i].count;
											if(activityLevelId == 5){
											 str+='<i class="getImagesCls glyphicon glyphicon-camera " id="images'+result[i].activityLocatInfoId+'"  style="cursor:pointer;font-size:18px;margin-left:8px;"  attr_constituency_id ="'+result[i].constituencyId+'" attr_scope_id = "'+activityScopeId+'" attr_value="'+0+'" attr_activity_lvl_id="'+activityLevelId+'" attr_search_type="'+searchBy+'"title="View Images" attr_location_nam="'+locationName+'"></i>';
											}else if(activityLevelId == 1){
												str+='<i class="getImagesCls glyphicon glyphicon-camera"  id="images'+result[i].activityLocatInfoId+'"style="cursor:pointer;font-size:18px;margin-left:8px;"  attr_constituency_id ="'+result[i].villageId+'" attr_scope_id = "'+activityScopeId+'" attr_value="'+1+'" attr_activity_lvl_id="'+activityLevelId+'" attr_search_type="'+searchBy+'"title="View Images"  attr_location_nam="'+locationName+'"></i>';
											}else if(activityLevelId == 3){
												str+='<i class="getImagesCls glyphicon glyphicon-camera"  id="images'+result[i].activityLocatInfoId+'"style="cursor:pointer;font-size:18px;margin-left:8px;"  attr_constituency_id ="'+result[i].districtId+'" attr_scope_id = "'+activityScopeId+'" attr_value="'+1+'" attr_activity_lvl_id="'+activityLevelId+'" attr_search_type="'+searchBy+'"title="View Images"  attr_location_nam="'+locationName+'"></i>';
											}else if(activityLevelId == 2){
												str+='<i class="getImagesCls glyphicon glyphicon-camera"  id="images'+result[i].activityLocatInfoId+'"style="cursor:pointer;font-size:18px;margin-left:8px;"  attr_constituency_id ="'+result[i].mandalId+'" attr_scope_id = "'+activityScopeId+'" attr_value="'+1+'" attr_activity_lvl_id="'+activityLevelId+'" attr_search_type="'+searchBy+'"title="View Images"  attr_location_nam="'+locationName+'"></i>';
											}
											str+='</span></td>';
										}else{
											str+='<td> 0 </td>';
										}
									
										
									}
								}
							}
						/*	str+='<td>';
							if(activityLevelId == 5){
							   str+='<input type="button" value="View" class="btn btn-success btn-xs" onclick="gettingCadreDetails('+result[i].constituencyId+',\''+result[i].constituencyName+'\',\''+constituencyId+'\');" style="margin-left: 45px;"/>';
							}else if(activityLevelId == 1 || activityLevelId == 2){
								str+='<input type="button" value="View" class="btn btn-success btn-xs" onclick="gettingCadreDetails('+result[i].villageId+',\''+result[i].villageName+'\',\''+constituencyId+'\');" style="margin-left: 45px;"/>';
							}else if(activityLevelId == 3){
								str+='<input type="button" value="View" class="btn btn-success btn-xs" onclick="gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+'\',\''+constituencyId+'\');" style="margin-left: 45px;"/>';
							}
							str+='</td>';*/
							
							str+='</tr>';
						}
						str+='</tbody>';
						str+='</table>';
					}
					
					/*
					str+=' <div style="background-color: #e0e0e0;border-radius: 5px;bottom: 0;left: 10%;position: fixed;width: 80%;z-index: 99;box-shadow:0px 0px 3px 3px;" id="submtBtn">';
					str+='<div class="col-md-5" id="conductedStatusDivId" style="">';
					str+='<label>Update Status As : </label>';
					str+='<select id="conductedStatus" class="form-control" name="activityVO.status">';
					str+='	<option value="0"> Select Conducted Status </option>';
					str+='	<option value="Conducted"> Conducted </option>';
					str+='	<option value="Not Conducted"> Not Conducted  </option>';
					str+='</select>';
					str+='<br><span id="chCkBxErrMsgId"></span></div>';
					str+='<div class="col-md-5"  id="conductedDateDivId" style="display:none;">';
					str+='	<label>Conducted Date <i class="glyphicon glyphicon-remove clearCls" attr_id="conductedDateId" title="Please click here to clear Conducted Date "></i> <a href="javascript:{clearAllConductedDates(\'condDateCls\');clearAllConductedDates(\'conductedDateCls\');}" title="Please click here to clear all Conducted Dates " style="margin-left:10px;"> Clear All Conducted Dates</a> </label>';
					str+='	<div class="input-group input-g1">';
					str+='		<span class="input-group-addon">';
					str+='			<i class="glyphicon glyphicon-calendar"></i>';
					str+='		</span>';
					str+='		<input type="text" class="form-control conductedDateCls" id="conductedDateId" name="activityVO.selectedCnductedDate">';
					str+='	</div>';
					str+='</div>';
					str+='<div class="col-md-2"  style=""> ';
					str+='';
					*/
					//str+='<input style="margin-top:25px" type="button" value="UPDATE DETAILS" class="btn btn-custom btn-success" onclick="saveActyDetails(1,\'\');"/></div>';
				str+='</div>';
					$('#home').html(str);
					$('#loadingsymbl').html("");
					
					$(".condDateCls").daterangepicker({singleDatePicker:true,format:'YYYY-MM-DD',maxDate:new Date()});
					$(".conductedDateCls").daterangepicker({singleDatePicker:true,format:'YYYY-MM-DD',maxDate:new Date()});
					//$('#home').append('');
					$("#locationsTab").dataTable({
					"iDisplayLength": 20,
					"aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
					});
					$("#locationsTab").removeClass("dataTable");
					$("#constncyId").html(''+$("#constiList option:selected").text()+' constituency ');
					$('#headingId').html(' '+$("#activityLevelList option:selected").text()+' - '+$("#ActivityList option:selected").text()+'');
					
					 getActivityAttribute();//gettting activity attribute to show and hide question info popup
			 });
	}

function saveActyDetails(actionId,selectedVal){
	var infoId;
	if(actionId == 1){
		$("#chCkBxErrMsgId").html("");
	 $(".checkBoxCls").each(function(){
		 if(($(this).is(":checked")) == true){
		 infoId = $(this).val();
		var num = $(this).attr("attr_no");
		$("#chckBxIdVal"+num).val(infoId);
		}
	 });
	 $('.condDateCls,.conductedDateCls,#conductedDateId').css("border-color","grey");
	 var conductdDate = $('#conductedDateId').val();
	 var status = $('#conductedStatus').val();
	 if(status == 0){
		  $("#chCkBxErrMsgId").html('<span style="color:red;"> Please Select Conducted Status </span> ');
		  $('#conductedStatus').css("border-color","red");
		 return;
	 }
	 if(infoId == "" || infoId == null){
		 $("#chCkBxErrMsgId").html('<span style="color:red;">Please check atleast one check box.</span> ');
		 return;
	 }
	 if(status =='Conducted'){
		
		 if($('.allcheckBoxCls').is(':checked')){	
			if(conductdDate == null || conductdDate.length==0){
				$("#chCkBxErrMsgId").html('<span style="color:red;">Please Select Conducted Date to set all locations at a time.</span> ');
				 $('#conductedDateId').css("border-color","red");
				return;
			}
		 }else{
			if(conductdDate == null || conductdDate.length==0){
				var isError='false';
				  $(".checkBoxCls").each(function(){
					 
						if(($(this).is(":checked")) == true){
							var activityInfoId = $(this).val();
							var dateStr = $('#date'+activityInfoId+'').val();
							if(dateStr == null || dateStr.length == 0){
								isError='true';
								$('#date'+activityInfoId+'').css("border-color","red");
							}
						} 
				 });
			 
				 if(isError=="true"){
					 $("#chCkBxErrMsgId").html('<span style="color:red;">Please Provide Conducted Date for all selected locations.</span> ');
					 $('#conductedDateId').css("border-color","red");
					 return;
				 }
			}
		 }
	 }
		$('#submtBtn,#searchId,#reSearchId').hide();
		
	}else{
		$('.imageCls'+actionId+'').hide();// we are hiding individual image upload symbol
		$('.imageCls1'+actionId+'').html('0');
	}
	
	$("#errorModalId").modal("show");
	$('#statsErrDiv').html(" <span style='color:green;font-weight:bold;' class='popupMsgCls'> Please wait Activity Details are updating...</span>");
		
	var uploadHandler = {
		 upload: function(result) {
			//console.log(result);
			uploadResult = result.responseText; 
			var stringext = uploadResult.substr(6,7);
			$("#errorModalId").modal("hide");
			if(actionId == 1){
				$('#statsErrDiv').html("  <span style='color:green;font-weight:bold;'> Activity Details Updated Successfully... </span>");
				$("#errorModalId").modal("hide");
				
					getLocationWiseDetailsForActivity(2);
				$('#submtBtn,#searchId,#reSearchId').show();
				if(stringext == "success"){
					
					//getLocationWiseDetailsForActivity(1);
				}else{
					/*$('#statsErrDiv').html(" <span style='color:red;font-weight:bold;'>Activity Details Not Updated.Give all inputs properly.then try again...</span>");*/
					$('#submtBtn,#searchId,#reSearchId').show();
				}
			}else{
				
					if(selectedVal=='Conducted')
						$('.imageCls'+actionId+'').show();
					
					$('#Err'+actionId+'').html(' <span style="color:green;font-weight:bold;">Updated</span>').delay(2000).fadeOut();
				
				
			}
		}
	};
	
		YAHOO.util.Connect.setForm('saveActivityDetails',true);
		YAHOO.util.Connect.asyncRequest('POST','saveActivityLocDetailsAction.action',uploadHandler);
	
}
var dataArr;

$(document).on("change","#conductedStatus",function(){
	var value = $(this).val();
	$('#conductedDateDivId').hide();
	$('#conductedDateId').val('');
	if(value == "Conducted"){
			$('#conductedDateDivId').show();
	}
});

$(document).on("change","#attributeTypeList",function(){
	$("#reSearchId").show();
	$("#home").html('');
	
	 var attrTypeId = $(this).val();
	 $("#statusDivId").hide();
	$("#planedDateDivId").hide();
	$("#conductedStatusDivId").hide();
	
	 dataArr = ""+$(this).val()+"";
	 for(var i in dataArr){
		if(parseInt(dataArr[i].trim()) == 6)
			$("#conductedStatusDivId").show();
		else if(parseInt(dataArr[i].trim()) == 5)
			$("#planedDateDivId").show();
		else if(parseInt(dataArr[i].trim())== 7){
				$("#statusDivId").show();
		}
		else if(parseInt(dataArr[i].trim())== 1){
			$("#uploadImagesId").show();
		}
	 }
	 
});

 $(document).on("change",".condDateCls",function(){
		$(this).css('border-color','grey');
 });
 
 $(document).on("change",".updateCls",function(){
	 	 var id = $(this).attr('attr_id');		
		 var selectedVal = $(this).val();
		 $('.okcls'+id+'').hide();
		 $('#date'+id+'').attr('disabled','disabled');
		 if(selectedVal =='Conducted' || selectedVal=='Not Conducted'){
			$('.okcls'+id+'').show();
		 }
		 if(selectedVal =='Conducted'){
			$('#date'+id+'').removeAttr('disabled');
		 }
		 
		  
 });
 $(document).on("click",".glyphicon-ok",function(){
	 var id = $(this).attr('attr_id');
	 var value= $('#date'+id+'').val();
	 $('#date'+id+'').css('border-color','grey');
	 $('#check'+id+'').prop("checked",true);
	 var selectedVal  = $('.statusCls'+id+'').val();
	 if(selectedVal =='Conducted' && value != null && value.length>0){	
		 $('#date'+id+'').removeAttr('disabled');
		setTimeout(function(){ saveActyDetails(id,selectedVal); }, 1000);
	 }else if(selectedVal=='Not Conducted'){
		  $('#date'+id+'').val('');
		  $('#imageCls'+id+'').hide();
		  $('#date'+id+'').attr('disabled','disabled');
		 setTimeout(function(){ saveActyDetails(id,selectedVal); }, 1000);
	 }
	 else if(selectedVal=='Not Updated'){
		  $('#date'+id+'').val('');
		  $('#imageCls'+id+'').hide();
		  $('#date'+id+'').attr('disabled','disabled');
		 setTimeout(function(){ saveActyDetails(id,selectedVal); }, 1000);
	 }
	else
		$('#date'+id+'').css('border-color','red');
 });
  $(document).on("click",".glyphicon-remove",function(){
	 var id = $(this).attr('attr_id');
	 $('#check'+id+'').prop("checked",false);
 });
 
 $(document).on("click",".allcheckBoxCls",function(){
	 if($(this).is(':checked')){		
		 $('.checkBoxCls').prop('checked', true);
	 }else{
		 $('.checkBoxCls').prop('checked', false);
	 }
 });
 $(document).on("change","#activityLevelList",function(){
	 $('#constiList').find('option').remove();
	 $('#constiList').append('<option value="0">Select Constituency</option>');
});
 
 function clearAllConductedDates(clsName){
	 $('.'+clsName+'').val('');
 }
  $(document).on("click",".clearCls",function(){
	  var id=$(this).attr('attr_id');
	   $('#date'+id+'').val('');
  });
  $(document).on("click",".activityLevelIdCls",function(){
	  $('#updateactivitymodel').modal('show');
	  var activityLocationInfoId = $(this).attr("attr_activity_location_info_id");
	  var activityName = $("#ActivityList option:selected").text();
	  getQuestionByAcivityScope(activityLocationInfoId,activityName);
  });
  
</script>
</body>
</html>