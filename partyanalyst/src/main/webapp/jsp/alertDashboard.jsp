<%@ page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8"%>
	<%@ taglib prefix="s" uri="/struts-tags" %>  
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title> Alerts </title>
	<!-- Bootstrap -->
	<link href="dist/2016DashBoard/css/bootstrap.css" rel="stylesheet" type="text/css">
	<link href="dist/DateRange/daterangepicker-bs3.css" rel="stylesheet" type="text/css">
		

	
	
	
	<link  rel="stylesheet" type="text/css" href="debate/js/jquery.google.api/jquery-ui1.10.3.css"/>
	
	
	<link rel="stylesheet" media="all" type="text/css" href="jquery-ui-timepicker-addon.css" />
	<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.filter.css" />
	<link rel="stylesheet" type="text/css" href="styles/custom-yui-styles.css">	
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 	
	<link rel="stylesheet" type="text/css" href="debate/pagination/simplePagination.css">
	<link href="dist/activityDashboard/SelectDropDown/dropkick.css" rel="stylesheet" type="text/css">
	<link href="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css">
    <link href="dist/activity/Timepicker/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
    <link href="dist/Appointment/MultiDatePicker/css/jquery-ui.css" rel="stylesheet" type="text/css">
		 		
	
	<!-- YUI Dependency files (Start) -->
		<script type="text/javascript" src="debate/js/yahoo/yahoo-min.js"></script>
		<script type="text/javascript" src="debate/js/yahoo/yahoo-dom-event.js"></script> 
		<script type="text/javascript" src="debate/js/yahoo/element-min.js"></script> 
		<script type="text/javascript" src="debate/js/yahoo/container-min.js"></script> 
		<script type="text/javascript" src="debate/js/yahoo/dom-min.js"></script> 
		<script type="text/javascript" src="debate/js/yahoo/yui-min.js"></script>
		<script type="text/javascript" src="debate/js/json/json-min.js"></script>
		<script type="text/javascript" src="debate/js/yahoo/connection-min.js"></script> 
		<script type="text/javascript" src="debate/js/yahoo/datasource-min.js"></script> 
		<script type="text/javascript" src="debate/js/yahoo/datatable-min.js"></script> 
		<script type="text/javascript" src="debate/js/yahoo/paginator-min.js"></script>
		
		
		
		<!-- Skin CSS files resize.css must load before layout.css --> 
		<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
		<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">

		<!-- YUI Dependency files (End) -->
		
		
	
	<!-- JQuery files (Start) -->
	<script src="debate/js/jquery.google.api/ajax.googleapis.com.ajax.libs.jquery.1.8.2.jquery.min.js"></script>
	<script type="text/javascript" src="debate/js/bootstrap.min.js"></script>
	<script src="debate/js/jquery.google.api/code.jquery.com.ui.1.10.2.jquery-ui.js"></script>
	<script src="debate/js/jquery-ui-themes-1.10.3.js"></script>
	<script src="debate/js/jquery-ui-timepicker-addon.js"></script>
	<script src="debate/js/jquery-ui-sliderAccess.js"></script>
	
	<script src="debate/js/jquery.google.api/trentrichardson.com.examples.timepicker.jquery-ui-sliderAccess.js"></script>
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<script src="js/debate.js"></script>
	
	<script type="text/javascript" src="debate/js/multiSelectBox/jquery.multiselect.js"></script>
	<script type="text/javascript" src="debate/js/multiSelectBox/jquery.multiselect.filter.js"></script>
	<script type="text/javascript" src="debate/js/jquery.dataTables.js"></script>	
	<script type="text/javascript" src="debate/js/jQuery/debate/js/jquery-ui-1.8.24.custom.min.js"/>
	<script type="text/javascript" src="debate/pagination/pagination1.js"></script>
	<script type="text/javascript" src="debate/pagination/jquery.simplePagination.js"></script>
	<script src="dist/Appointment/DropkickNew/dropkick.2.1.8.min.js" type="text/javascript"></script>
	
 </head>                                         							
<body>
<div class="container">
	<div class="row">
		<div class="col-md-8 col-md-offset-2 col-xs-12 col-sm-8 col-sm-offset-2">
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="row">
						<div class="col-md-8 col-xs-12 col-sm-6">
							<h4 class="panel-title">ALERT DASHBOARD</h4>
						</div>
						<div class="col-md-4 col-xs-12 col-sm-6">
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
        <h4 class="modal-title" id="">Modal title</h4>
      </div>
      <div class="modal-body">
        <div class="row">
		  <div class="col-md-12 col-xs-12 col-sm-12">
			<label>Type&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><span id="typeId">Name</span>
		  </div>
		  <div class="col-md-12 col-xs-12 col-sm-12">
			<label>Severity&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><span id="severityId">Name</span>
		  </div>
		  <div class="col-md-12 col-xs-12 col-sm-12">
			<label>Created&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><span id="createdDate">Name</span>
		  </div>
		  <div class="col-md-12 col-xs-12 col-sm-12">
			<label>Level&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><span id="levelId">Name</span>
		  </div>
		  <div class="col-md-12 col-xs-12 col-sm-12">
			<label>Location&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><span id="LocationId">Name</span>
		  </div>
		  <div class="col-md-12 col-xs-12 col-sm-12">
			<label>Status&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><span id="statusId">Name</span>
		  </div>
		  <div class="col-md-12 col-xs-12 col-sm-12">
			<label>Description&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><span id="descriptionId">Name</span>
		  </div>
		</div>
		<div class="row">
		  <div class="col-md-6 col-xs-12 col-sm-12">
			<label>Comments</label><span id="commentsId">Name</span>
			<textarea class="form-control"></textarea>
		  </div>
		  <div class="col-md-12 col-xs-12 col-sm-12">
			<button class="btn btn-success updateAlertStatusCls">UPDATE</button>
		  </div>
		</div>
		<div class="row">
		  <div class="col-md-4 col-xs-12 col-sm-6">
			<h4 class="panel-title">TRACING DETAILS</h4>
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
<script src="dist/alertDashBoard/alertDashboard.js" type="text/javascript"></script>
<script src="dist/DateRange/moment.js" type="text/javascript"></script>
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
<script type="text/javascript">
$("#dateRangePickerId").daterangepicker({opens:'left'});

//getLocationLevelAlertCount();
//getLocationLevelAlertData();
</script>
</body>
</html>