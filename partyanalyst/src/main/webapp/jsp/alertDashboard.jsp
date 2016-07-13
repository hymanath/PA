<%@ page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title> Alerts </title>
	<!-- Bootstrap -->
	<link href="dist/2016DashBoard/css/bootstrap.css" rel="stylesheet" type="text/css">
	<link href="dist/DateRange/daterangepicker.css" rel="stylesheet" type="text/css">
	<link href="dist/activityDashboard/SelectDropDown/dropkick.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/>
	<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" rel="stylesheet" type="text/css">
	<!-- JQuery files (Start) -->
	<script src="dist/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="dist/js/bootstrap.js"></script>
	<script src="dist/DateRange/moment.js" type="text/javascript"></script>
	<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
	<script src="dist/Appointment/DropkickNew/dropkick.2.1.8.min.js" type="text/javascript"></script>
	<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
	<script src="dist/alertDashBoard/alertDashboard.js" type="text/javascript"></script>
	
	<link href="dist/Appointment/custom.css" rel="stylesheet" type="text/css">
	
<script src="js/simplePagination/simplePagination.js" type="text/javascript"></script>
	<style type="text/css">
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
<body style="position:relative;">
<div class="container">
	<div class="row">
		<div class="col-md-12 col-xs-12 col-sm-12">
			<div class="panel panel-default">
				<div class="panel-heading" style="background:#ccc;">
					<div class="row">
						<div class="col-md-9 col-xs-12 col-sm-6">
							<h4 class="panel-title m_top10">ALERT DASHBOARD</h4>
						</div>
						<div class="col-md-3 col-xs-12 col-sm-6">
							<div class="input-group">
								<input type="text" class="form-control" id="dateRangePickerId"/>
								<span class="input-group-addon">
									<i class="glyphicon glyphicon-calendar"></i>
								</span>
							</div>
						</div>
					</div>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div id="locationLevelId"></div>
						</div>
					</div>
					<br/>
					<div class="row">
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div id="locationLevelDataId"></div>
						</div>
					</div>
				</div>
			</div>
		</div>        
	</div>
</div>
<div class="modal fade" id="ModalShow" tabindex="-1" role="dialog">
  <div class="modal-dialog modal-xs">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="descriptionTitleId">Modal title</h4>
      </div>
      <div class="modal-body">
        <div class="row">
			<div class="col-md-12 col-xs-12 col-sm-12">
				<table class="table table-condensed tableModal">
					<tr>
						<td style="width:50%;">
							<label>Type</label>
							<p id="typeId"></p>
						</td>
						<td style="width:50%;">
							<label>Severity&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
							<p id="severityId"></p>
						</td>
					</tr>
					<tr>
						<td>
							<label>Created&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
							<p id="createdDate"></p>
						</td>
						<td>
							<label>Level&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
							<p id="levelId"></p>
						</td>
					</tr>
					<tr>
						<td>
							<label>Location&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
							<p id="LocationId"></p>
						</td>
						
					</tr>
					<tr>
						<td colspan="2">
							<label>Description&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
							<p id="descriptionId"></p>
						</td>
						<td></td>
					</tr>
					
				
					<tr>
					<td>
							<label>Status&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
							<select class="dropkickClass" id="statusId">
								<option value='0'>Select Status</option>
								
								<option value='1'>Created</option>
								<option value='2'>Action In Progess</option>
								<option value='3'>Completed</option>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<label>Comments</label><span ></span>
							<textarea class="form-control" id="commentsId"></textarea>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div id="errorId"></div>
		<div class="row">
		  <div class="col-md-12 col-xs-12 col-sm-12">
			<button class="btn btn-success updateAlertStatusCls">UPDATE</button>
		  </div>
		</div>
       <div class="row">
		  <div class="col-md-12 col-xs-12 col-sm-12">
			
			<div id="alertCommentsDiv"></div>
		  </div>  
		</div>
	 </div>
      <!--<div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>-->
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script type="text/javascript">
$(document).ready(function(){
	$("#dateRangePickerId").daterangepicker({
		opens:'left',
		ranges: {
           'Today': [moment(), moment()],
           'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
           'Last 7 Days': [moment().subtract(6, 'days'), moment()],
           'Last 30 Days': [moment().subtract(29, 'days'), moment()],
           'This Month': [moment().startOf('month'), moment().endOf('month')],
           'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        }
	});
});

$(document).on("click","#createAlertBtn",function(){
	$("#createAlertModal").modal('show');
	buildLevels();
	showHideSearch("advanceSearch");
	showHideBySearchType();
	disableByLevel(1);
	getAlertsource();
	$("#apptmemberDetailsDiv").html("");
});
$(".dropkickClass").dropkick();


</script>
</body>
</html>