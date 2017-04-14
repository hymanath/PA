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
<title>IAS OFFICER VIEW & STATE LEVEL DEPARTMENT</title>
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
						
							<div class="col-sm-6">
								<div class="panel panel-default">
									<div class="panel-heading headingColor">
										<h4 class="panel-title text-capital fontColor">status overview</h4>
									</div>
									<div class="panel-body">
									<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
										<div class="panel panel-default">
											<div class="" role="tab" id="headingOne" style="padding: 15px;">
											 
												<a class ="collapseIconForIAS" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
												 <h4 class="panel-title"> ALERTS</h4>
												
												</a>
											  
											</div>
											<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
											  <div class="panel-body">
												<div id="statusOverview"></div>
											  </div>
											</div>
										</div>
										<div class="panel panel-default">
											<div class="" role="tab" id="headingTwo" style="padding: 15px;">
											
												<a class="collapsed collapseIconForIAS subTaskViewDts" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
												  <h4 class="panel-title">
												  SUB TASKS</h4>
												</a>
											  
											</div>
											<div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
											  <div class="panel-body">
												<div id="statusWiseSubTasksOverview"></div>
											  </div>
											</div>
										</div>
									</div>
										
									</div>
								</div>
							</div>
							<div class="col-sm-6">
								<div class="panel panel-default">
									<div class="panel-heading headingColor">
										<h4 class="panel-title text-capital fontColor">location level overview</h4>
									</div>
									<div class="panel-body">
										<div class="panel-group" id="accordionL" role="tablist" aria-multiselectable="true">
											<div class="panel panel-default">
												<div class="" role="tab" id="headingOneL" style="padding: 15px;">
												 
													<a class ="collapseIconForIAS" role="button" data-toggle="collapse" data-parent="#accordionL" href="#collapseOneL" aria-expanded="true" aria-controls="collapseOneL">
													 <h4 class="panel-title"> ALERTS</h4>
													
													</a>
												  
												</div>
												<div id="collapseOneL" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOneL">
												  <div class="panel-body">
													<div id="levelWiseAlertOverview"></div>
												  </div>
												</div>
											</div>
											<div class="panel panel-default">
												<div class="" role="tab" id="headingTwoL" style="padding: 15px;">
												
													<a class="collapsed collapseIconForIAS subTaskLocViewDts" role="button" data-toggle="collapse" data-parent="#accordionL" href="#collapseTwoL" aria-expanded="false" aria-controls="collapseTwoL">
													  <h4 class="panel-title">
													  SUB TASKS</h4>
													</a>
												  
												</div>
												<div id="collapseTwoL" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwoL">
												  <div class="panel-body">
													<div id="levelWiseSubTasksAlertOverview"></div>
												  </div>
												</div>
											</div>
										</div>
										
									</div>
								</div>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading headingColor">
								<h4 class="panel-title text-capital fontColor">status overview</h4>
							</div>
									
						<div class="panel-body">
						<div class="row">
							<div class="col-sm-12 col-xs-12 col-md-12 ">
								<h4>STATE LEVEL</h4>
								<div class="col-sm-12 col-xs-12 col-md-12">
									<div id="stateLevelIASDetails" class=""></div>
								</div>	
							</div>
							<div class="col-sm-12 col-xs-12 col-md-12 ">
								<h4>ZONE LEVEL</h4>
								<div class="col-md-2 col-xs-12 col-sm-4 m_top10">
							
									<ul class="list-inline activeUlCls  constituencyUl locationWiseSortingZone">
									<li class="active " attr_sorting_type="count" attr_order_type="desc" >
										<i class="glyphicon glyphicon-sort-by-attributes" ></i>
									</li>
									<li class="" attr_sorting_type="count" attr_order_type="asc">
										<i class="glyphicon glyphicon-sort-by-attributes-alt" style="transform:rotate(180deg)"></i>
									</li>
									<li class="" attr_sorting_type="name" attr_order_type="asc">
										A-Z
									</li>
									<li class="" attr_sorting_type="name" attr_order_type="desc">
										Z-A
									</li>
									</ul>
								</div>
								<div class="col-sm-12 col-xs-12 col-md-12">
									<div id="zoneLevelIASDetails" class=""></div>
								</div>	
							</div>
							<div class="col-sm-12 col-xs-12 col-md-12 ">
								<h4>DISTRICT LEVEL</h4>
								<div class="col-md-2 col-xs-12 col-sm-4 m_top10">
							
									<ul class="list-inline activeUlCls  constituencyUl locationWiseSortingDistrict">
									<li class="active " attr_sorting_type="count" attr_order_type="desc" >
										<i class="glyphicon glyphicon-sort-by-attributes" ></i>
									</li>
									<li class="" attr_sorting_type="count" attr_order_type="asc">
										<i class="glyphicon glyphicon-sort-by-attributes-alt" style="transform:rotate(180deg)"></i>
									</li>
									<li class="" attr_sorting_type="name" attr_order_type="asc">
										A-Z
									</li>
									<li class="" attr_sorting_type="name" attr_order_type="desc">
										Z-A
									</li>
									</ul>
								</div>
								<div class="col-sm-4 col-xs-12 col-md-2 locationWiseSortingDist">
									<select class="form-control locationWiseDistOnChange" id="DistrictNamesId" >
									
									</select>
								</div>
								<div class="col-sm-12 col-xs-12 col-md-12">
									<div id="districtLevelSubOrdinarteDetails" class=""></div>
								</div>	
							</div>
							<div class="col-sm-12 col-xs-12 col-md-12 ">
							<h4>DIVISION LEVEL</h4>
								<div class="col-md-2 col-xs-12 col-sm-4 m_top10">
								
									<ul class="list-inline activeUlCls  constituencyUl locationWiseSortingDivision">
									<li class="active " attr_sorting_type="count" attr_order_type="desc" >
										<i class="glyphicon glyphicon-sort-by-attributes" ></i>
									</li>
									<li class="" attr_sorting_type="count" attr_order_type="asc">
										<i class="glyphicon glyphicon-sort-by-attributes-alt" style="transform:rotate(180deg)"></i>
									</li>
									<li class="" attr_sorting_type="name" attr_order_type="asc">
										A-Z
									</li>
									<li class="" attr_sorting_type="name" attr_order_type="desc">
										Z-A
									</li>
									</ul>
								</div>
								<div class="col-sm-4 col-xs-12 col-md-2 ">
									<select class="form-control locationWiseDiviDistOnChange" id="DivisionDistNamesId" >
										
									</select>
								</div>
								<div class="col-sm-4 col-xs-12 col-md-2">
									<select class="form-control locationWiseDiviOnChange" id="DivisionNamesId" >
										<option value="0">Select Division</option>
									</select>
								</div>
								<div class="col-sm-12 col-xs-12 col-md-12">
									<div id="divisionLevelSubOrdinarteDetails" class=""></div>
								</div>	
							</div>
							
							<div class="col-sm-12 col-xs-12 col-md-12 ">
								<h4>SUB-DIVISION LEVEL</h4>
								<div class="col-md-2 col-xs-12 col-sm-4 m_top10">
							
									<ul class="list-inline activeUlCls  constituencyUl locationWiseSortingSubDivision">
									<li class="active" attr_sorting_type="count" attr_order_type="desc" >
										<i class="glyphicon glyphicon-sort-by-attributes" ></i>
									</li>
									<li class="" attr_sorting_type="count" attr_order_type="asc">
										<i class="glyphicon glyphicon-sort-by-attributes-alt" style="transform:rotate(180deg)"></i>
									</li>
									<li class="" attr_sorting_type="name" attr_order_type="asc">
										A-Z
									</li>
									<li class="" attr_sorting_type="name" attr_order_type="desc">
										Z-A
									</li>
									</ul>
								</div>
								<div class="col-sm-4 col-xs-12 col-md-2">
									<select class="form-control locationWiseSubDiviDistOnChange" id="SubDivisionDistNamesId" >
									
									</select>
								</div>
								<div class="col-sm-4 col-xs-12 col-md-2">
									<select class="form-control locationWiseSubDiviDiviOnChange" id="SubDivisionDiviNamesId" >
									<option value="0">Select Division</option>
									</select>
								</div>
								<div class="col-sm-4 col-xs-12 col-md-2">
									<select class="form-control locationWiseSubDiviOnChange" id="SubDivisionNamesId" >
									<option value="0">Select SubDivision</option>
									</select>
								</div>
								<div class="col-sm-12 col-xs-12 col-md-12">
									<div id="SubdivisionLevelSubOrdinarteDetails" class=""></div>
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
	
</div>
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
<script src="alertDepartment/js/newAlertUserManagementDetail.js" type="text/javascript"></script>
<script type="text/javascript">


</script>
</body>
</html>