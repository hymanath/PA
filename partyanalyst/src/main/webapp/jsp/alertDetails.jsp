<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title> Alert Details </title>
	<!-- Bootstrap -->
	<link href="dist/2016DashBoard/css/bootstrap.css" rel="stylesheet" type="text/css">
	<link href="dist/DateRange/daterangepicker.css" rel="stylesheet" type="text/css">
	<link href="dist/activityDashboard/SelectDropDown/dropkick.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/>
	<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" rel="stylesheet" type="text/css">
	<link href="dist/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css">
	<link href="dist/Alert/custom.css" rel="stylesheet" type="text/css">
	<!-- JQuery files (Start) -->
	<script src="dist/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="dist/js/bootstrap.js"></script>
	<script src="dist/DateRange/moment.js" type="text/javascript"></script>
	<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
	<script src="dist/Appointment/DropkickNew/dropkick.2.1.8.min.js" type="text/javascript"></script>
	<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
	
	<script src="js/simplePagination/simplePagination.js" type="text/javascript"></script>
	<style type="text/css">
		#commomCadreSearchDiv .panel .panel-body
		{
			background:#fff !important;
		}
		
		.panelAlert
		{
			border:0px;
			box-shadow:none;
			box-shadow:0px 0px 4px rgba(0,0,0,0.4);
			background:#fff;
		}
		.panelAlert .panel-heading , .panelAlert .panel-body
		{
			background:#fff;
			padding:15px;
		}
		.createAlertModalCls
		{
			border-radius:0px;
		}
		.form-control
		{
			border-radius:0px;
		}
		.createAlertModalCls .modal-header
		{background:#ccc;}
		.input-group .form-control , .input-group .input-group-addon
		{
			border-radius:0px;
		}
		
		
		.btnCreateAlert
		{
			position:absolute;bottom:30px;right:30px;height:40px;width:40px;border-radius:50%;box-shadow:0px 0px 5px rgba(0,0,0,0.4);padding:3px 7px
		}
		.m_top10
		{
			margin-top:10px;
		}
		.tableModal tr td
		{
			border:0px !important; 
		}
		p,ul,h1,h2,h3,h4,h5,h6
		{
			margin:0px;
		}
		.alertStatusTracking
		{
			padding-left:20px;
			border-left:1px solid #ddd;
		}
		.alertStatusTracking li
		{
			list-style:none;
			border-radius:4px;
			position:relative;
			margin-top:5px;
		}
		.alertStatusTracking li:before
		{
			content:' ';
			height:10px;
			width:10px;
			border-radius:50%;
			background:#ddd;
			top:10px;
			padding:4px;
			left:-26px;
			position:absolute;
		}
		.arrow_box {
			position: relative;
			background: #fff;
			border: 1px solid #ddd;
			padding:7px;
			border-radius:4px;
		}
		.arrow_box:after, .arrow_box:before {
			right: 100%;
			top: 10px;
			border: solid transparent;
			content: " ";
			height: 0;
			width: 0;
			position: absolute;
			pointer-events: none;
		}

		.arrow_box:after {
			border-color: rgba(255, 255, 255, 0);
			border-right-color: #fff;
			border-width: 5px;
			margin-top: -5px;
		}
		.arrow_box:before {
			border-color: rgba(194, 225, 245, 0);
			border-right-color: #c2e1f5;
			border-width: 6px;
			margin-top: -6px;
		}
	</style>
 </head>     

<!-- language convertion-->
  <script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">

// Load the Google Transliterate API
   google.load("elements", "1", {
         packages: "transliteration"
       });

</script> 
<script type="text/javascript">
	var control;
	var lang;
   function onLoad() {
	
       lang = $("input[name=language]:checked").val();
     var options = {
         sourceLanguage:
             google.elements.transliteration.LanguageCode.ENGLISH,
         destinationLanguage:
             [''+lang+''],
         shortcutKey: 'alt+t',
         transliterationEnabled: true
     };

     // Create an instance on TransliterationControl with the required
     // options.
     control =
         new google.elements.transliteration.TransliterationControl(options);

     // Enable transliteration in the textbox with id
     // 'descrptionId'.

	 	if ($('#commentsId').length){
control.makeTransliteratable(['commentsId']);
 }
   }
   function languageChangeHandler() {
  
        var lang1 = $("input[name=language]:checked").val();
		if(lang1 =="en")
	   {
		control.disableTransliteration();
		}
		else
	   {
		   control.enableTransliteration();
           control.setLanguagePair(
            google.elements.transliteration.LanguageCode.ENGLISH,
            lang1);
			}
      }
 google.setOnLoadCallback(onLoad);
</script> 
<body style="position:relative;">


<div class="container">

        <div class="row">
			<div class="col-md-12 col-xs-12 col-sm-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">ALERT DETAILS</h4>
					</div>
					<div class="panel-body">
						<div class="table-responsive" id="alertBasicDataDiv"></div>
						<table class="table table-bordered tableCategory">
							<tr>
								<td>
									<p class="text-muted text-capital">type of alert</p>
									<p id="typeId"></p>
								</td>
								<td>
									<p class="text-muted text-capital">alert created</p>
									<p id="createdDate"></p>
								</td>
								<td>
									<p class="text-muted text-capital">status</p>
									<p id="alertStatus"></p>
								</td>
								<td>
									<p class="text-muted text-capital">alert severity</p>
									<p><span class="circle critical"></span><span  id="severityId">Critical</span></p>
								</td>
								<td>
									<p class="text-muted text-capital">alert location level</p>
									<p class="text-capitalize" id="levelId"></p>
								</td>
								<td>
									<p class="text-muted text-capital">alert location</p>
									<p class="text-capitalize" id="LocationId"></p>
								</td>
							</tr>
							<tr>
								<td colspan="8">
									<p class="text-muted text-capital">alert description</p>
									<p id="descriptionId"></p>
								</td>
							</tr>
						</table>
						 <div class="row m_top10">
							<div class="col-md-4 col-xs-12 col-sm-4" style="border-right:1px solid #ddd;">
								<h4 class="panel-title text-capital">involved members in this alert</h4>
								<ul class="involvedMembersUl" id="alertCandidateDataId">
									<li>
										<div class="media">
											<div class="media-left">
												<img src="dist/img/thumb.jpg" alt="Profile Image" style="width:50px;"/>
											</div>
											<div class="media-body">
											   <!-- <p class="text-capital"><b>Ramesh</b></p>
												<p>+91 99848455</p>
												<p>Constituency Name</p>
												<span class="label label-success">+ Ve</span>-->
											</div>
										</div>
									</li>
								</ul>
							</div>
							<div class="col-md-8 col-xs-12 col-sm-8">
								<h4 class="panel-title text-capital">alert status tracking comments</h4>
								<div  id="alertCommentsDiv"></div>
							</div>
						</div>
						<div class="row m_top10">
							<div class="col-md-12 col-xs-12 col-sm-12">
								<div class="bg_cc pad_10">
									<div class="row">
										<div class="col-md-4 col-xs-12 col-sm-4">
											<div class="panel panel-default">
												<div class="panel-heading bg_ff">
													<h4 class="panel-title text-success">ASSIGNED CANDIDATES - 	
														<span id="assignCandidatesCnt">0</span>
														<c:if test="${fn:contains(sessionScope.USER.entitlements, 'UPDATE_ALERT_ENTITLEMENT')}">
														<input type="button" value="ASSIGN" class="btn btn-primary assignModel pull-right btn-xs">
														</c:if>
													</h4>
												</div>
												<div class="panel-body">
													<div  id="alertAssignedCandidateDataId"></div>
												</div>
											</div>
										</div>
										<c:if test="${fn:contains(sessionScope.USER.entitlements, 'UPDATE_ALERT_ENTITLEMENT')}">
										<div class="col-md-8 col-xs-12 col-sm-8">
											<label>Alert Status</label>
											<select class="dropkickClass" id="statusId">
											<option value='0'>Select Status</option>
											<option value='1'>Notified</option>
											<option value='2'>Action In Progess</option>
											<option value='3'>Completed</option>
											</select>
											<label>Comments</label>
											<label class="radio-inline">
												<input type="radio" value="te" name="language" class="lang" id="telugu" checked  onclick="languageChangeHandler();"/>Telugu
											</label>
											<label class="radio-inline">
												<input type="radio"  value="en" name="language" class="lang" id="eng" onclick="languageChangeHandler();"/>English
											</label>
											<textarea class="form-control" placeholder="alert tracking comments" id="commentsId"></textarea>
											<div id="errorId" class="m_top10"></div>
											<button class="btn btn-success text-capital m_top10 updateAlertStatusCls">Update Alert status</button>
										</div>
										</c:if>
									</div>
								</div>
							</div>
						</div>
						
					</div>
				</div>
			</div>
		</div>
	</div>
		
<div class="modal fade" id="ModalShow" tabindex="-1" role="dialog">
  <div class="modal-dialog" style="width:85%;">
	<div class="modal-content">
	  <div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="descriptionTitleId">Assign Alert</h4>
	  </div>
	  <div class="modal-body">
		<jsp:include page="commonCadreSearch.jsp" flush="true"/>
	 </div>
	 <div class="modal-footer">
		<div id="assignBtnId" >
			<div id="assignEroorDiv"></div>
				<input type="button" value="ASSIGN ALERT" onclick="saveAlertAssignedUser();"  class="btn btn-primary btnNewCustom1">
		</div>	
	 </div>
	</div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
		
		<!--
		<div class="row">
			<div class="col-md-6 col-xs-12 col-sm-6">
				<div class="panel panel-default panelAlert">
					<div class="panel-heading">
						<h4 class="panel-title text-success">INVOLVED CANDIDATES</h4>
					</div>
					<div class="panel-body" style="height:320px;">
						
						<div class="media" id="alertCandidateDataId">
							
						</div>
					</div>
				</div>
				
			</div>
			<div class="col-md-6 col-xs-12 col-sm-6">
				<div id="alertCommentsDiv"></div>
			</div>  
		</div>
		 <div class="row">
			<div class="col-md-6 col-xs-12 col-sm-6">
				<div class="panel panel-default panelAlert">
					<div class="panel-heading">
						<h4 class="panel-title text-success">ASSIGNED CANDIDATES - <span id="assignCandidatesCnt"> - 0</span><input type="button" class="btn btn-primary assignModel pull-right btn-xs" value="ASSIGN"/></h4>
					</div>
					<div class="panel-body" style="height:320px;">
						<div class="media" id="alertAssignedCandidateDataId"></div>
					</div>
				</div>
			</div> 			
			<div class="col-md-6 col-xs-12 col-sm-6">
				<div class="panel panel-default panelAlert">
					<div class="panel-heading">
						<h4 class="panel-title text-success">UPDATE ALERT STATUS</h4>
					</div>
					<div class="panel-body">
						<label>Status&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
						<select class="dropkickClass" id="statusId">
							<option value='0'>Select Status</option>
							<option value='1'>Created</option>
							<option value='2'>Action In Progess</option>
							<option value='3'>Completed</option>
						</select>
						
						<div class="m_top10"><b>Select Language: </b> <input type="radio"  value="te" name="language" class="lang" id="telugu" checked  onclick="languageChangeHandler();"> Telugu<input type="radio"  value="en" name="language" class="lang" id="eng" onclick="languageChangeHandler();"> English </div>
						<label class="m_top10">Comments</label>
						<textarea class="form-control" id="commentsId"></textarea>
						<div id="errorId" class="m_top10"></div>
						<button class="btn btn-success updateAlertStatusCls m_top10">UPDATE</button>
					</div>
					
					
				</div>
			</div>
		</div>	
			<!--<input type="button" value="ASSIGN" class="btn btn-primary assignModel">
			
		<div class="modal fade" id="ModalShow" tabindex="-1" role="dialog">
		  <div class="modal-dialog" style="width:85%;">
			<div class="modal-content">
			  <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="descriptionTitleId">Assign Alert</h4>
			  </div>
			  <div class="modal-body">
				<jsp:include page="commonCadreSearch.jsp" flush="true"/>
			 </div>
			 <div class="modal-footer">
				<div style="display:none;" id="assignBtnId" >
					<div id="assignEroorDiv"></div>
						<input type="button" value="ASSIGN ALERT" onclick="saveAlertAssignedUser();"  class="btn btn-primary btnNewCustom1">
				</div>	
			 </div>
			</div><!-- /.modal-content 
		  </div><!-- /.modal-dialog 
		</div><!-- /.modal 
		</div>-->

				
<script type="text/javascript">


var alertId = '${alertId}';

$(".dropkickClass").dropkick();

</script>
<script src="dist/alertDashBoard/alertDetails.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mCustomScrollbar.js" type="text/javascript"></script>

</body>
</html>