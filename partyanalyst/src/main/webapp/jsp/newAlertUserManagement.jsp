<%@ page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Government Core DashBoard</title>
<link rel="SHORTCUT ICON" type="image/x-icon" href="govtCoreDashBoard/img/fevicon.png">
<link href="alertDepartment/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="alertDepartment/css/custom.css" rel="stylesheet" type="text/css">
<link href="alertDepartment/css/responsive.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
<link rel="stylesheet" href="dist/css/font-awesome.css">
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
<link href="dist/DateRange/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="dist/alertDashBoard/dist/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>

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
	<script type="text/javascript" src="js/yahoo/json-min.js"></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/tabview-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	
<!-- YUI Dependency files (End) -->

<style type="text/css">
.eventsheader
{
	display:none;
}
</style>
</head>
<body>
<div  class="AMS AUMS">
	
	<section class="m_top20">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-xs-12 col-sm-12"> 
					<div class="input-group dateRangePickerCls m_top5 pull-right" style="margin-right: 15px">
						<input type="text" class="form-control" style="width:180px" id="dateRangePickerAUM">
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-calendar"></i>
						</span>
					</div>
						<div class="row">
							<div class="col-md-4 col-xs-12 col-sm-12 m_top30">
								<div class="panel panel-default">
								  <div class="panel-heading headingColor">
									<h4 class="panel-title text-capital fontColor">My Alerts</h4>
								  </div>
								  <div class="panel-body">
									<div id="myAlertsDivID"></div>
								  </div>
								</div>
							</div>
							<div class="col-md-4 col-xs-12 col-sm-12 m_top30">
								<div class="panel panel-default">
								  <div class="panel-heading headingColor">
									<h4 class="panel-title text-capital fontColor">My Sub Tasks</h4>
								  </div>
								  <div class="panel-body">
									<div id="mySubTasksDivID"></div>
								  </div>
								</div>
							</div>
							<div class="col-md-4 col-xs-12 col-sm-12">
								<div class="panel panel-default">
								  <div class="panel-heading headingColor">
									<h4 class="panel-title text-capital fontColor">Assigned Sub Tasks</h4>
								  </div>
								  <div class="panel-body">
									<div id="assignedSubTasksDivID"></div>
								  </div>
								</div>
							</div>
						</div>
						<div class="row">
						<div class="col-md-12 col-xs-12 col-sm-12"> 	
							<div class="panel panel-default">
								<div class="panel-heading headingColor ">
									<div class="row">
										<div class="col-md-4 col-xs-12 col-sm-12">
											<h4 class="panel-title text-capital fontColor">District Level - Department Wise</h4>
										</div>
										<div class="col-md-6 col-xs-12 col-sm-12">
											<ul class="switch-btn pull-right">
												<li attr_type="status">status overview</li>
												<li attr_type="location" class="active">location level</li>
												<li attr_type="filter" >Filter View</li>
											</ul>
										</div>
										<div class="col-md-2 col-xs-12 col-sm-12 ">
											<ul class="switch-btn switch-btn-alertType pull-right">
												<li  attr_type="alert" class="active">Alerts</li>
												<li attr_type="subTask">Sub Tasks</li>
												
											</ul>
										</div>
										
										
									</div>
								</div>
								<div class="panel-body">
									<div class="row">
											<div class="col-sm-12 col-xs-12 col-md-12 departmentlocationShow">
													<ul class="list-inline activeUlCls  constituencyUl">
														<li class="descendingConstituencyCls active" onClick="">
															<i class="glyphicon glyphicon-sort-by-attributes" ></i>
														</li >
														<li class="ascendingConstituencyCls" onClick="">
															<i class="glyphicon glyphicon-sort-by-attributes-alt" style="transform:rotate(180deg)"></i>
														</li>
														<li class="atozConstituecySortingCls" onClick="">
															A-Z
														</li>
														<li class="ztozConstituecySortingCls" onClick="">
															Z-A
														</li>
														<li>
														<select class="form-control" id="" onChange="">
															<option value="0">Select Constitency </option>
															<option value="0">Select Constitency </option>
														</select>
														</li>
													</ul>
													<div id="departmentlocationCountDivId"></div>
												
											</div>
											<div class="col-sm-12 col-xs-12 col-md-12 departmentStatusShow" style="display:none;">	
												<ul class="list-inline activeUlCls  constituencyUl">
														<li class="descendingConstituencyCls active" onClick="">
															<i class="glyphicon glyphicon-sort-by-attributes" ></i>
														</li >
														<li class="ascendingConstituencyCls" onClick="">
															<i class="glyphicon glyphicon-sort-by-attributes-alt" style="transform:rotate(180deg)"></i>
														</li>
														<li class="atozConstituecySortingCls" onClick="">
															A-Z
														</li>
														<li class="ztozConstituecySortingCls" onClick="">
															Z-A
														</li>
														<li>
														<select class="form-control" id="" onChange="">
															<option value="0">Select Constitency </option>
															<option value="0">Select Constitency </option>
														</select>
														</li>
													</ul>
												<div id="departmentStatusCountDivId"></div>
											</div>
											<div class="col-sm-12 col-xs-12 col-md-12 departmentAlertCountShow" style="display:none;">
												<div id="departmentAlertCountDivId"></div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>			
		</div>
	</section>

<!-- Scripts-->
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
<script src="dist/alertDashBoard/dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mousewheel.js" type="text/javascript"></script>
<script src="alertDepartment/js/newAlertUserManagement.js" type="text/javascript"></script>
<script type="text/javascript">


</script>
</body>
</html>