<%@ page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Meeting Dashboard</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/DateRange/daterangepicker.css" rel="stylesheet" type="text/css">
<link href="dist/Icomoon/style.css" rel="stylesheet" type="text/css">
<link href="dist/Timepicker/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
<link href="dist/Dropzone/basic.css" rel="stylesheet" type="text/css">
<link href="dist/activityDashboard/SelectDropDown/dropkick.css" rel="stylesheet" type="text/css">
<link href="dist/Dropzone/dropzone.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
<link href="dist/newmultiselect/chosen.css" rel="stylesheet" type="text/css">
<link  rel="stylesheet" type="text/css" href="debate/js/jquery.google.api/jquery-ui1.10.3.css"/>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
  <script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
    <script src="debate/js/jquery.google.api/ajax.googleapis.com.ajax.libs.jquery.1.8.2.jquery.min.js"></script>	
	<script src="debate/js/jquery.google.api/code.jquery.com.ui.1.10.2.jquery-ui.js"></script>
	<script src="debate/js/jquery.google.api/trentrichardson.com.examples.timepicker.jquery-ui-sliderAccess.js"></script>
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<style type="text/css">
.btn {
    border-radius: 0;
}
.btnCustom, .btnCustom:active, .btnCustom {
    background: #666666 none repeat scroll 0 0;
    color: #fff;
}
.profileImage {
    height: 75px;
    width: 75px;
}
.attendanceIcon {
    display: inline-block;
    height: 30px;
    width: 30px;
}
.linkinner label{color:#fff}
.custom-select
{
	padding:3px;
	height:24px !important;
	font-size:12px;
	border-radius:0px;	
}

.panel .panel-default , .panel-group 
{
	margin:0px !important;
}
.panel-default .panel-heading
{
	background-color:#FFF
}
.panel .panel-default .panel .panel-default
{
	border:0px;
}
header.eventsheader { 
 background:url("dist/img/header-footer.png") no-repeat scroll center bottom / 100% auto #fed501;
 background-origin: border-box;
 background-repeat: no-repeat;
 height: 71px; 
}
.meetingAbsentCls {
  text-decoration:underline;
}
footer{background-color:#5c2d25;color:#ccc;padding:30px}
</style>

</head>
<body>
<!--<header  class="eventsheader">
<!-- <img src="css/Training/img/header.jpg" width="100%"> -->
	<!--<div class="container">
        <div class="row">
            <div class="col-md-2 col-xs-4 col-sm-1">
                <img src="dist/img/logo.png" class="img-responsive">
            </div>
            <div class="col-md-1 col-xs-1 col-sm-1">
                <img src="dist/img/CBN1.png" class="img-responsive">
            </div>
            <div class="col-md-6 col-xs-7 col-sm-7 text-center">               
                 <p class="header-text display-style" id="mainheading" style="font-size:38px;"></p>               
            </div>
            <div class="col-md-1 col-xs-1 col-sm-1"><img src="dist/img/NTR1.png" class="img-responsive" />   
            </div>
			<div class="col-md-2 col-xs-1 col-sm-1">
				<div class="" style="color:white;margin-top: 5px;"><b> Welcome ${sessionScope.UserName} </b></div>
                    <a href="#" class="dropdown-toggle btn btn-default btn-xs m_top10" data-toggle="dropdown" aria-expanded="false" style="margin-top: 5px;">
                    Menu <img src="images/menu_icon.png" />
                    </a>
					<ul class="dropdown-menu" role="menu" aria-labelledby="drop6" style="    background-color: rgb(239, 64, 54);">
					   <!--<li><a href="mahanaduCadreVisitInfoAction.action"><span>ENTRY/EXIT DASHBOARD</span></a> </li>-->
					   <!--<li><a href="dashBoardAction.action"><span>DASHBOARD</span></a> </li>
					   <li><a tabindex="-1" href="meetingList.action">Meetings List</a></li>
					   <li><a tabindex="-1" href="newlogoutAction.action">Sign Out</a></li>
					
                    </ul>   
            </div>			
        </div>       
    </div>
	
	
</header>-->
<main>
	<div class="container">
    	<div class="row">
        	<section>
            	<div class="col-md-12">
                	<div class="panel panel-default">
                    	<div class="panel-heading" style="background-color:#CCC">
                        	<h3 class="panel-title text-bold">Meetings Dashboard</h3>
                        </div>
                        <div class="panel-body" style="background-color:#EFF3F4">
                        	<div class="row">
                            	<div class="col-md-6">
                                	<div class="panel panel-default">
                                    	<div class="panel-heading" style="background-color:#DDD">
                                        	<h4 class="panel-title text-center">MEETING SUMMARY</h4>
                                        </div>
                                        <div class="panel-body pad_0">
                                        	<div class="table-responsive">
                                            	<table class="table m_0">
													<thead align="center">
														<tr style="background-color:#F4F2E6">
															<td>
																<h4 class="m_0">INVITEES<br/><span id="totalInvitieesHead">0</span></h4>
															</td>
															<td>
																<h4 class="m_0">ATTENDED<br/><span id="attendedHead">0</span></h4>
															</td>
															<td>
																<h4 class="m_0">NON INVITEES<br/><span id="nonInviteeHead">0</span></h4>
															</td>
															<td colspan="2">
																<h4 class="m_0">TOTAL ABSENT<br/><span id="totalAbsentHead">0</span></h4>
															</td>
														</tr>
													</thead>
													<tbody id="meetingSummaryBodyId" align="center"></table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                	<div class="table-responsive bg_ff">
                                        <table class="table m_0 table-bordered">
                                            <tr class="font-12">
                                                <td><h4 class="m_top20">
                                                	TOTAL COMMITTEES<span class="pull-right" id="ttlDistMtngsSpanId">0</span>
                                                </h4></td>
                                            </tr>
                                            <tr>
                                                <td><h4 class="m_top20">
                                                NO OF PLANNED MEETINGS<span class="pull-right" id="ttlPlnndMtngsSpanId">0</span>
                                                </h4></td>
                                            </tr>
                                            <tr>
                                                <td><h4 class="m_top20">
                                                	TOTAL NO OF MEETINGS CONDUCTED<span class="pull-right" id="ttlCndctdMtngsSpanId">0(0%)</span>
                                                </h4></td>
                                            </tr>
                                            <tr>
                                                <td><h4 class="m_top20">
                                                	AVERAGE ATTENDANCE OF INVITEES<span class="pull-right" id="avgAttndInvtsSpanId">0</span>
                                                </h4></td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                            	<div class="col-md-12 m_top20">
                                	<div class="panel panel-default">
                                    	<div class="panel-heading" style="background-color:#CCC">
                                            	<select id="resultTypeSelId">
                                                    <option value="individual" selected>Individual</option>
													<option value="cumulative">Cumulative</option>
                                                </select>
											<div class="pull-right">
												<span class="btn-sm btn-success" style="cursor:pointer;" onclick="exportToExcel()">Export to excel</span>
                                            	<label>GROUP BY : </label>
                                            	<label class="checkbox-inline" id="grpLctnStId"><input type="checkbox" class="grpLctn"  value="state">State</label>
                                            	<label class="checkbox-inline" id="grpLctnDistId" style="display:none;"><input type="checkbox" class="grpLctn"  value="district" >District</label>
                                            	<label class="checkbox-inline" id="grpLctnConstId" style="display:none;"><input type="checkbox" class="grpLctn"  value="constituency" >Constituency</label>
                                            </div>
                                        </div>
                                        <div class="panel-body pad_0">
										<div id="updatedCounts"></div>
											<div class="table-responsive" id="individualMeetingResultId">
												<img src="./images/icons/search.gif" class="offset7"  id="indiAjax" style="width:20px;height:20px;display:none;"/>
											</div>
                                            <div class="table-responsive" id="cumulativeMeetingResultId" style="display:none;">
											<img src='./images/icons/search.gif' class="offset7"  id="cummAjax" style="width:20px;height:20px;display:none;"/>
												<!--<table class="table table-bordered m_0">
													<thead>
														<tr>
															<th rowspan="2">No Of Meetings</th>
															<th rowspan="2">Location</th>
															<th rowspan="2">Total <br/>Invitees</th>
															<th colspan="3" class="text-center">Attendance</th>
															<th rowspan="2">Total<br/> Absent</th>
															<th colspan="3"  class="text-center">MOM</th>
															<th colspan="3" class="text-center">ATR</th>
														</tr>
														<tr>
															<th>Total Attended</th>
															<th>Invitees</th>
															<th>Non Inivtees</th>
															<th></th>
															<th>File</th>
															<th>Text</th>
															<th></th>
															<th>File</th>
															<th>Text</th>
														</tr>
													</thead>
													<img src='./images/icons/search.gif' class="offset7"  id="cummAjax" style="width:20px;height:20px;display:none;"/>
													<tbody id="cumulativeMeetingTableBodyId"></tbody>
												</table>-->
                                            </div>
											<div id="dummyForExcel" style="display:none;"></div>
                                            <!-- <div class="checkbox-select">
                                                <div class="panel-group" id="accordion10" role="tablist" aria-multiselectable="true">
                                                  <div class="panel panel-default border_custom">
                                                    <div class="panel-heading" role="tab" id="heading10One">
                                                      <h4 class="panel-title">
                                                        <a role="button" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion10" href="#collapse10One" aria-expanded="true" aria-controls="collapse10One">
                                                          Andhra Pradesh
                                                        </a>
                                                      </h4>
                                                    </div>
                                                    <div id="collapse10One" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                                                      <div class="panel-body pad_0">
                                                       <div class="padleft_30">
                                                    <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                                                      <div class="panel panel-default">
                                                        <div class="panel-heading" role="tab" id="headingOne">
                                                            <a class="accordion-toggle collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                                              <h4 class="panel-title">
                                                                  SRIKAKULAM - 70
                                                                  
                                                              </h4>
                                                            </a>
                                                        </div>
                                                        <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                                                          <div class="panel-body">
                                                          </div>
                                                        </div>
                                                      </div>
                                                      <div class="panel panel-default">
                                                        <div class="panel-heading" role="tab" id="headingTwo">
                                                          <h4 class="panel-title">
                                                            <a class="accordion-toggle" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                                                  VISAKAPATNAM - 80
                                                                  
                                                            </a>
                                                          </h4>
                                                        </div>
                                                        <div id="collapseTwo" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingTwo">
                                                          <div class="panel-body">
                                                            <div class="padleft_30">
                                                                <div class="panel-group" id="accordionInner" role="tablist" aria-multiselectable="true">
                                                                  <div class="panel panel-default border_0">
                                                                    <div class="panel-heading" role="tab" id="headingInnerOne">
                                                                      <h4 class="panel-title">
                                                                        <a role="button" class="accordion-toggle" data-toggle="collapse" data-parent="#accordionInner" href="#collapseInnerOne" aria-expanded="true" aria-controls="collapseInnerOne">
                                                                            
                                                                          Srungavarapukota - 20
                                                                          <span class="pull-right">
                                                                            
                                                                          </span>
                                                                        </a>
                                                                      </h4>
                                                                    </div>
                                                                    <div id="collapseInnerOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingInnerOne">
                                                                      <div class="panel-body border_0 padleft_30">
                                                                            <div class="table-responsive">
                                                                            <table class="table table-bordered table-condensed m_0">
                                                                                <thead style="background-color:#DDD">
                                                                                    <tr>
                                                                                        <th rowspan="2">Meeting Name</th>
                                                                                        <th rowspan="2">No Of Meetings</th>
                                                                                        <th rowspan="2">Location</th>
                                                                                        <th rowspan="2">Schedule<br/> On</th>
                                                                                        <th rowspan="2">Total <br/>Invitees</th>
                                                                                        <th colspan="3" class="text-center">Attendance</th>
                                                                                        <th rowspan="2">Total<br/> Absent</th>
                                                                                        <th colspan="3"  class="text-center">MOM</th>
                                                                                        <th colspan="3" class="text-center">ATR</th>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th>Total Attended</th>
                                                                                        <th>Invitees</th>
                                                                                        <th>Non Inivtees</th>
                                                                                        <th></th>
                                                                                        <th>File</th>
                                                                                        <th>Text</th>
                                                                                        <th></th>
                                                                                        <th>File</th>
                                                                                        <th>Text</th>
                                                                                    </tr>
                                                                                </thead>
                                                                                <tbody>
                                                                                    <tr>
                                                                                        <td>District Committee Body Meeting</td>
                                                                                        <td>8</td>
                                                                                        <td>Srikakulam</td>
                                                                                        <td>Aug-15-2015</td>
                                                                                        <td>100</td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>8</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td>8</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td rowspan="3">District Committee Body Meeting</td>
                                                                                        <td rowspan="3">6</td>
                                                                                        <td rowspan="3">Srikakulam</td>
                                                                                        <td rowspan="3">Aug-15-2015</td>
                                                                                        <td rowspan="3">100</td>
                                                                                        <td rowspan="3">10<span class="font-10">(10%)</span></td>
                                                                                        <td rowspan="3">10<span class="font-10">(10%)</span></td>
                                                                                        <td rowspan="3">10<span class="font-10">(10%)</span></td>
                                                                                        <td rowspan="3"	>10<span class="font-10">(10%)</span></td>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>District Committee Body Meeting</td>
                                                                                        <td>2</td>
                                                                                        <td>Srikakulam</td>
                                                                                        <td>Aug-15-2015</td>
                                                                                        <td>100</td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                    </tr>
                                                                                </tbody>
                                                                            </table>
                                                                            </div>
                                                                      </div>
                                                                    </div>
                                                                  </div>
                                                                  <div class="panel panel-default border_0">
                                                                    <div class="panel-heading" role="tab" id="headingInnerTwo">
                                                                      <h4 class="panel-title">
                                                                        <a class="collapsed accordion-toggle" role="button" data-toggle="collapse" data-parent="#accordionInner" href="#collapseInnerTwo" aria-expanded="false" aria-controls="collapseInnerTwo">
                                                                          
                                                                          Srungavarapukota - 20
                                                                          <span class="pull-right">
                                                                            
                                                                          </span>
                                                                        </a>
                                                                      </h4>
                                                                    </div>
                                                                    <div id="collapseInnerTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingInnerTwo">
                                                                      <div class="panel-body border_0 padleft_30">
                                                                            <div class="table-responsive">
                                                                            <table class="table table-bordered table-condensed m_0">
                                                                                <thead style="background-color:#DDD">
                                                                                    <tr>
                                                                                        <th rowspan="2">Meeting Name</th>
                                                                                        <th rowspan="2">No Of Meetings</th>
                                                                                        <th rowspan="2">Location</th>
                                                                                        <th rowspan="2">Schedule<br/> On</th>
                                                                                        <th rowspan="2">Total <br/>Invitees</th>
                                                                                        <th colspan="3" class="text-center">Attendance</th>
                                                                                        <th rowspan="2">Total<br/> Absent</th>
                                                                                        <th colspan="3"  class="text-center">MOM</th>
                                                                                        <th colspan="3" class="text-center">ATR</th>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th>Total Attended</th>
                                                                                        <th>Invitees</th>
                                                                                        <th>Non Inivtees</th>
                                                                                        <th></th>
                                                                                        <th>File</th>
                                                                                        <th>Text</th>
                                                                                        <th></th>
                                                                                        <th>File</th>
                                                                                        <th>Text</th>
                                                                                    </tr>
                                                                                </thead>
                                                                                <tbody>
                                                                                    <tr>
                                                                                        <td>District Committee Body Meeting</td>
                                                                                        <td>8</td>
                                                                                        <td>Srikakulam</td>
                                                                                        <td>Aug-15-2015</td>
                                                                                        <td>100</td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>8</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td>8</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td rowspan="3">District Committee Body Meeting</td>
                                                                                        <td rowspan="3">6</td>
                                                                                        <td rowspan="3">Srikakulam</td>
                                                                                        <td rowspan="3">Aug-15-2015</td>
                                                                                        <td rowspan="3">100</td>
                                                                                        <td rowspan="3">10<span class="font-10">(10%)</span></td>
                                                                                        <td rowspan="3">10<span class="font-10">(10%)</span></td>
                                                                                        <td rowspan="3">10<span class="font-10">(10%)</span></td>
                                                                                        <td rowspan="3"	>10<span class="font-10">(10%)</span></td>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>District Committee Body Meeting</td>
                                                                                        <td>2</td>
                                                                                        <td>Srikakulam</td>
                                                                                        <td>Aug-15-2015</td>
                                                                                        <td>100</td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                    </tr>
                                                                                </tbody>
                                                                            </table>
                                                                            </div>
                                                                      </div>
                                                                    </div>
                                                                  </div>
                                                                  <div class="panel panel-default border_0">
                                                                    <div class="panel-heading" role="tab" id="headingInnerThree">
                                                                      <h4 class="panel-title">
                                                                        <a class="collapsed accordion-toggle" role="button" data-toggle="collapse" data-parent="#accordionInner" href="#collapseInnerThree" aria-expanded="false" aria-controls="collapseInnerThree">
                                                                            
                                                                            Srungavarapukota - 20
                                                                          <span class="pull-right">
                                                                            
                                                                          </span>
                                                                        </a>
                                                                      </h4>
                                                                    </div>
                                                                    <div id="collapseInnerThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingInnerThree">
                                                                      <div class="panel-body border_0 padleft_30">
                                                                            <div class="table-responsive">
                                                                            <table class="table table-bordered table-condensed m_0">
                                                                                <thead style="background-color:#DDD">
                                                                                    <tr>
                                                                                        <th rowspan="2">Meeting Name</th>
                                                                                        <th rowspan="2">No Of Meetings</th>
                                                                                        <th rowspan="2">Location</th>
                                                                                        <th rowspan="2">Schedule<br/> On</th>
                                                                                        <th rowspan="2">Total <br/>Invitees</th>
                                                                                        <th colspan="3" class="text-center">Attendance</th>
                                                                                        <th rowspan="2">Total<br/> Absent</th>
                                                                                        <th colspan="3"  class="text-center">MOM</th>
                                                                                        <th colspan="3" class="text-center">ATR</th>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th>Total Attended</th>
                                                                                        <th>Invitees</th>
                                                                                        <th>Non Inivtees</th>
                                                                                        <th></th>
                                                                                        <th>File</th>
                                                                                        <th>Text</th>
                                                                                        <th></th>
                                                                                        <th>File</th>
                                                                                        <th>Text</th>
                                                                                    </tr>
                                                                                </thead>
                                                                                <tbody>
                                                                                    <tr>
                                                                                        <td>District Committee Body Meeting</td>
                                                                                        <td>8</td>
                                                                                        <td>Srikakulam</td>
                                                                                        <td>Aug-15-2015</td>
                                                                                        <td>100</td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>8</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td>8</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td rowspan="3">District Committee Body Meeting</td>
                                                                                        <td rowspan="3">6</td>
                                                                                        <td rowspan="3">Srikakulam</td>
                                                                                        <td rowspan="3">Aug-15-2015</td>
                                                                                        <td rowspan="3">100</td>
                                                                                        <td rowspan="3">10<span class="font-10">(10%)</span></td>
                                                                                        <td rowspan="3">10<span class="font-10">(10%)</span></td>
                                                                                        <td rowspan="3">10<span class="font-10">(10%)</span></td>
                                                                                        <td rowspan="3"	>10<span class="font-10">(10%)</span></td>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>District Committee Body Meeting</td>
                                                                                        <td>2</td>
                                                                                        <td>Srikakulam</td>
                                                                                        <td>Aug-15-2015</td>
                                                                                        <td>100</td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                    </tr>
                                                                                </tbody>
                                                                            </table>
                                                                            </div>
                                                                      </div>
                                                                    </div>
                                                                  </div>
                                                                </div>
                                                            </div>
                                                          </div>
                                                        </div>
                                                      </div>
                                                      <div class="panel panel-default">
                                                        <div class="panel-heading" role="tab" id="headingThree">
                                                          <h4 class="panel-title">
                                                            <a class="collapsed accordion-toggle" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                                             
                                                                Srungavarapukota - 20
                                                              <span class="pull-right">
                                                                
                                                              </span>
                                                            </a>
                                                          </h4>
                                                        </div>
                                                        <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                                                          <div class="panel-body">
                                                            <div class="padleft_30">
                                                                <div class="panel-group" id="accordioninner1" role="tablist" aria-multiselectable="true">
                                                                  <div class="panel panel-default border_0">
                                                                    <div class="panel-heading" role="tab" id="headinginner1One">
                                                                      <h4 class="panel-title">
                                                                        <a role="button" class="accordion-toggle" data-toggle="collapse" data-parent="#accordioninner1" href="#collapseinner1One" aria-expanded="true" aria-controls="collapseinner1One">
                                                                            
                                                                          Srungavarapukota - 20
                                                                          <span class="pull-right">
                                                                            
                                                                          </span>
                                                                        </a>
                                                                      </h4>
                                                                    </div>
                                                                    <div id="collapseinner1One" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headinginner1One">
                                                                      <div class="panel-body border_0 padleft_30">
                                                                            <div class="table-responsive">
                                                                            <table class="table table-bordered table-condensed m_0">
                                                                                <thead style="background-color:#DDD">
                                                                                    <tr>
                                                                                        <th rowspan="2">Meeting Name</th>
                                                                                        <th rowspan="2">No Of Meetings</th>
                                                                                        <th rowspan="2">Location</th>
                                                                                        <th rowspan="2">Schedule<br/> On</th>
                                                                                        <th rowspan="2">Total <br/>Invitees</th>
                                                                                        <th colspan="3" class="text-center">Attendance</th>
                                                                                        <th rowspan="2">Total<br/> Absent</th>
                                                                                        <th colspan="3"  class="text-center">MOM</th>
                                                                                        <th colspan="3" class="text-center">ATR</th>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th>Total Attended</th>
                                                                                        <th>Invitees</th>
                                                                                        <th>Non Inivtees</th>
                                                                                        <th></th>
                                                                                        <th>File</th>
                                                                                        <th>Text</th>
                                                                                        <th></th>
                                                                                        <th>File</th>
                                                                                        <th>Text</th>
                                                                                    </tr>

                                                                                </thead>
                                                                                <tbody>
                                                                                    <tr>
                                                                                        <td>District Committee Body Meeting</td>
                                                                                        <td>8</td>
                                                                                        <td>Srikakulam</td>
                                                                                        <td>Aug-15-2015</td>
                                                                                        <td>100</td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>8</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td>8</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td rowspan="3">District Committee Body Meeting</td>
                                                                                        <td rowspan="3">6</td>
                                                                                        <td rowspan="3">Srikakulam</td>
                                                                                        <td rowspan="3">Aug-15-2015</td>
                                                                                        <td rowspan="3">100</td>
                                                                                        <td rowspan="3">10<span class="font-10">(10%)</span></td>
                                                                                        <td rowspan="3">10<span class="font-10">(10%)</span></td>
                                                                                        <td rowspan="3">10<span class="font-10">(10%)</span></td>
                                                                                        <td rowspan="3"	>10<span class="font-10">(10%)</span></td>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>District Committee Body Meeting</td>
                                                                                        <td>2</td>
                                                                                        <td>Srikakulam</td>
                                                                                        <td>Aug-15-2015</td>
                                                                                        <td>100</td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                    </tr>
                                                                                </tbody>
                                                                            </table>
                                                                            </div>
                                                                      </div>
                                                                    </div>
                                                                  </div>
                                                                  <div class="panel panel-default border_0">
                                                                    <div class="panel-heading" role="tab" id="headinginner1Two">
                                                                      <h4 class="panel-title">
                                                                        <a class="collapsed accordion-toggle" role="button" data-toggle="collapse" data-parent="#accordioninner1" href="#collapseinner1Two" aria-expanded="false" aria-controls="collapseinner1Two">
                                                                          
                                                                          Srungavarapukota - 20
                                                                          <span class="pull-right">
                                                                            
                                                                          </span>
                                                                        </a>
                                                                      </h4>
                                                                    </div>
                                                                    <div id="collapseinner1Two" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headinginner1Two">
                                                                      <div class="panel-body border_0 padleft_30">
                                                                            <div class="table-responsive">
                                                                            <table class="table table-bordered table-condensed m_0">
                                                                                <thead style="background-color:#DDD">
                                                                                    <tr>
                                                                                        <th rowspan="2">Meeting Name</th>
                                                                                        <th rowspan="2">No Of Meetings</th>
                                                                                        <th rowspan="2">Location</th>
                                                                                        <th rowspan="2">Schedule<br/> On</th>
                                                                                        <th rowspan="2">Total <br/>Invitees</th>
                                                                                        <th colspan="3" class="text-center">Attendance</th>
                                                                                        <th rowspan="2">Total<br/> Absent</th>
                                                                                        <th colspan="3"  class="text-center">MOM</th>
                                                                                        <th colspan="3" class="text-center">ATR</th>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th>Total Attended</th>
                                                                                        <th>Invitees</th>
                                                                                        <th>Non Inivtees</th>
                                                                                        <th></th>
                                                                                        <th>File</th>
                                                                                        <th>Text</th>
                                                                                        <th></th>
                                                                                        <th>File</th>
                                                                                        <th>Text</th>
                                                                                    </tr>
                                                                                </thead>
                                                                                <tbody>
                                                                                    <tr>
                                                                                        <td>District Committee Body Meeting</td>
                                                                                        <td>8</td>
                                                                                        <td>Srikakulam</td>
                                                                                        <td>Aug-15-2015</td>
                                                                                        <td>100</td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>8</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td>8</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td rowspan="3">District Committee Body Meeting</td>
                                                                                        <td rowspan="3">6</td>
                                                                                        <td rowspan="3">Srikakulam</td>
                                                                                        <td rowspan="3">Aug-15-2015</td>
                                                                                        <td rowspan="3">100</td>
                                                                                        <td rowspan="3">10<span class="font-10">(10%)</span></td>
                                                                                        <td rowspan="3">10<span class="font-10">(10%)</span></td>
                                                                                        <td rowspan="3">10<span class="font-10">(10%)</span></td>
                                                                                        <td rowspan="3"	>10<span class="font-10">(10%)</span></td>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>District Committee Body Meeting</td>
                                                                                        <td>2</td>
                                                                                        <td>Srikakulam</td>
                                                                                        <td>Aug-15-2015</td>
                                                                                        <td>100</td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                    </tr>
                                                                                </tbody>
                                                                            </table>
                                                                            </div>
                                                                      </div>
                                                                    </div>
                                                                  </div>
                                                                  <div class="panel panel-default border_0">
                                                                    <div class="panel-heading" role="tab" id="headinginner1Three">
                                                                      <h4 class="panel-title">
                                                                        <a class="collapsed accordion-toggle" role="button" data-toggle="collapse" data-parent="#accordioninner1" href="#collapseinner1Three" aria-expanded="false" aria-controls="collapseinner1Three">
                                                                            
                                                                            Srungavarapukota - 20
                                                                          <span class="pull-right">
                                                                            
                                                                          </span>
                                                                        </a>
                                                                      </h4>
                                                                    </div>
                                                                    <div id="collapseinner1Three" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headinginner1Three">
                                                                      <div class="panel-body border_0 padleft_30">
                                                                            <div class="table-responsive">
                                                                            <table class="table table-bordered table-condensed m_0">
                                                                                <thead style="background-color:#DDD">
                                                                                    <tr>
                                                                                        <th rowspan="2">Meeting Name</th>
                                                                                        <th rowspan="2">No Of Meetings</th>
                                                                                        <th rowspan="2">Location</th>
                                                                                        <th rowspan="2">Schedule<br/> On</th>
                                                                                        <th rowspan="2">Total <br/>Invitees</th>
                                                                                        <th colspan="3" class="text-center">Attendance</th>
                                                                                        <th rowspan="2">Total<br/> Absent</th>
                                                                                        <th colspan="3"  class="text-center">MOM</th>
                                                                                        <th colspan="3" class="text-center">ATR</th>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th>Total Attended</th>
                                                                                        <th>Invitees</th>
                                                                                        <th>Non Inivtees</th>
                                                                                        <th></th>
                                                                                        <th>File</th>
                                                                                        <th>Text</th>
                                                                                        <th></th>
                                                                                        <th>File</th>
                                                                                        <th>Text</th>
                                                                                    </tr>
                                                                                </thead>
                                                                                <tbody>
                                                                                    <tr>
                                                                                        <td>District Committee Body Meeting</td>
                                                                                        <td>8</td>
                                                                                        <td>Srikakulam</td>
                                                                                        <td>Aug-15-2015</td>
                                                                                        <td>100</td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>8</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td>8</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td rowspan="3">District Committee Body Meeting</td>
                                                                                        <td rowspan="3">6</td>
                                                                                        <td rowspan="3">Srikakulam</td>
                                                                                        <td rowspan="3">Aug-15-2015</td>
                                                                                        <td rowspan="3">100</td>
                                                                                        <td rowspan="3">10<span class="font-10">(10%)</span></td>
                                                                                        <td rowspan="3">10<span class="font-10">(10%)</span></td>
                                                                                        <td rowspan="3">10<span class="font-10">(10%)</span></td>
                                                                                        <td rowspan="3"	>10<span class="font-10">(10%)</span></td>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>District Committee Body Meeting</td>
                                                                                        <td>2</td>
                                                                                        <td>Srikakulam</td>
                                                                                        <td>Aug-15-2015</td>
                                                                                        <td>100</td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>10<span class="font-10">(10%)</span></td>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td>2</td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                                                    </tr>
                                                                                </tbody>
                                                                            </table>
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
                                                </div>
                                                      </div>
                                                    </div>
                                                  </div>
                                                  <div class="panel panel-default">
                                                    <div class="panel-heading" role="tab" id="heading10Two">
                                                      <h4 class="panel-title">
                                                        <a class="collapsed accordion-toggle" role="button" data-toggle="collapse" data-parent="#accordion10" href="#collapse10Two" aria-expanded="false" aria-controls="collapse10Two">
                                                          Telangana
                                                        </a>
                                                      </h4>
                                                    </div>
                                                    <div id="collapse10Two" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading10Two">
                                                      <div class="panel-body">
                                                        Telangana
                                                      </div>
                                                    </div>
                                                  </div>
                                                </div>
                                            	
                                            </div> -->
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
		
		<!-- added -->
		<div class="col-md-12 meetingDiv" style="display:none;" id="meetingTableDiv">
			<div class="panel panel-default">
				<div class="panel-heading bg_cc">
					<h4 class="panel-title" id="meetingTitleId" style="text-transform: uppercase;font-weight:bold;"></h4>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-12" id="meetingAttendanceCntDiv"></div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="panel panel-default">
								<div class="panel-heading bg_E5" id="attendenceDiv" style="display:none;">
									<div class="row"> <!--
										<div class="col-md-12">
											<label class="radio-inline">
												<input type="radio"  class="meetingradioCls" name="meetingradio" value="TP" checked>Total Attended Members
											</label>
											<label class="radio-inline">
												<input type="radio"  class="meetingradioCls" name="meetingradio" value="TI" checked>Total Invitees
											</label>
											<label class="radio-inline">
												<input type="radio" class="meetingradioCls" name="meetingradio" value="IP">Invitee Attended Members
											</label>
											<label class="radio-inline">
												<input type="radio" class="meetingradioCls" name="meetingradio" value="AB">Invitee Absent
											</label>
											<label class="radio-inline">
												<input type="radio" class="meetingradioCls" name="meetingradio" value="NI">Non - Invitee Attended Members
											</label>
										</div>
										-->
									<div class="panel-heading bg_cc">
										<h4 class="panel-title" id="filterHeading" style="text-transform: uppercase;font-weight:bold;background: #cccccc;border-radius: 5px;text-align: center;padding: 5px"></h4>
									</div>	
										<div class="col-md-7">
											<!--<div class="dk-select " id="dk0"><div class="dk-selected " tabindex="0" id="dk0-combobox" aria-live="assertive" aria-owns="dk0-listbox" role="combobox">All Designations</div><ul class="dk-select-options" id="dk0-listbox" role="listbox" aria-expanded="false"><li class="dk-option  dk-option-selected" data-value="All Designations" role="option" aria-selected="true" id="dk0-All-Designations">All Designations</li></ul></div><select data-dkcacheid="0">
												<option>All Designations</option>-->
											

											<select id="disignationDiv" class="form-control" multiple>
											</select>
										</div>
										<div class="col-md-2">
											<button class="btn btn-success btn-block" onclick="getTdpCadreDetailsForPartyMeeting('',1);">APPLY FILTERS</button>
										</div>
										<div class="col-md-2">
											<button class="btn btn-default btnCustom btn-block" onclick="exportToExcell('excelMembersTab');">EXPORT EXCEL</button>
										</div>
										<!--<div class="col-md-2">
											<button class="btn btn-default btnCustom btn-block">CLEAR FILTERS</button>
										</div>-->
									</div>
								</div>
								<div class="panel-body pad_0" id="meetingAttendedMembersDiv" style="margin-top: 25px;"></div>
								<div class="panel-body pad_0" id="exportMeetingAttendedMembersDiv" style="display:none;"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		
						</div>	
		<!-- end -->
    </div>
</main>
<div class="themeControll">

  <div class="linkinner">
  		<div class="row">
        	<div class="col-md-10 col-md-offset-1 m_top10">
                <label>Meeting Level</label>
                <select class="form-control custom-select" id="meetingLevel" onchange="getTypeOfMeeting();">
					<option value="0">Select Level</option>
                    <option value="1">STATE</option> 
					<option value="2">DISTRICT</option> 
					<option value="3">CONSTITUENCY</option> 
					<option value="4">MANDAL/TOWN/DIVISION</option> 
					<option value="5">VILLAGE/WARD</option>
			    </select>
            </div>
            <div class="col-md-10 col-md-offset-1">
                <label>Type Of Meeting</label>
                <select class="form-control custom-select" id="typeOfMeeting">
                   <option value="0">Select Type</option> 
                </select>
            </div>
            <div class="col-md-10 col-md-offset-1">
                <label>Meeting Duration</label>
                <select class="form-control custom-select" id="meetingDuration">
                	<option value="1">Last 1 Months</option>
                    <option value="2">Last 3 Months</option>
                    <option value="3">Last 6 Months</option>
                    <option value="4">Last 9 Months</option>
                </select>
            </div>
			<div class="col-md-10 col-md-offset-1">
                <label>Location Level</label>
                <select class="form-control custom-select" id="locationLevelSelId">
                   	<option value="">Select Location Level</option>
					<option value="1">State</option>
					<option value="2">District</option>
					<option value="3">Constituency</option>
					<option value="4">Mandal/Town/Divison</option>
					<option value="5">Village/Ward</option>
                </select>
            </div>
            <div class="col-md-10 col-md-offset-1 locationDiv" id="stateDiv" style="display:none;">
                <label>Select State</label>
                <select class="form-control custom-select locationCls" id="stateId" onchange="getDistrictsForStates();">
					<option value="">Select State</option>
                   	<!--<option value="0">All</option>
					<option value="1">AndhraPradesh</option>
					<option value="36">Telangana</option>-->
                </select>
            </div>
            <div class="col-md-10 col-md-offset-1 locationDiv" id="districtDiv" style="display:none;">
                <label>Select District</label><img src='./images/icons/search.gif' class="offset7"  id="imgForDist" style="width:15px;height:15px;display:none;"/>
                <select class="form-control custom-select locationCls" id="districtId" onchange="getConstituenciesForDistricts(this.value);">
                   <option>Select District</option>
                </select>
            </div>
            <div class="col-md-10 col-md-offset-1 locationDiv" id="constituencyDiv" style="display:none;">
                <label>Select Constituency</label><img src='./images/icons/search.gif' class="offset7"  id="imgForConsti" style="width:15px;height:15px;display:none;"/>
                <select class="form-control custom-select locationCls" id="constituencyId" onchange="getMandalCorporationsByConstituency();">
                    <option>Select Constituency</option>
                </select>
            </div>
			
			<div class="col-md-10 col-md-offset-1 locationDiv" id="mandalDiv" style="display:none;">
                <label>Mandal/Town/Divison</label><img src='./images/icons/search.gif' class="offset7"  id="imgForMandl" style="width:15px;height:15px;display:none;"/>
                <select class="form-control custom-select locationCls" id="mandalId" onchange="getPanchayatWardByMandal();">
                   <option>Select Mandal/Town/Divison</option>
                </select>
            </div>
			<div class="col-md-10 col-md-offset-1 locationDiv" id="panchayatDiv" style="display:none;">
                <label>Village/Ward</label><img src='./images/icons/search.gif' class="offset7"  id="imgForPanc" style="width:15px;height:15px;display:none;"/>
                <select class="form-control custom-select locationCls" id="panchayatId">
                   <option>Select Village/Ward</option>
                </select>
            </div>
			
           
            <div class="col-md-10 col-md-offset-1 m_top20" style="margin-bottom:10px;">
			  <button class="btn btn-block btn-success btn-sm btn-custom" onClick="updateFunctions();">UPDATE</button>
            </div>
        </div>
  </div>

  <p class="tbtn"> <i class="glyphicon glyphicon-filter"></i> FILTERS</p>
</div> 

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document" style="width:62%; margin:auto;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="modelTitle"></h4>
      </div>
      <div class="modal-body" id="modelBody">
       <!--<div id="absentId"></div>-->
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="myModalId" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close closeSecondModal" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="modelTitleId"></h4>
      </div>
      <div class="modal-body" id="modelBodyId">
	    <label>Remarks :<span style="color:red;">*</span><span>(NOTE:Press Alt+t to toggle between Telugu & English)</span></label>
       <textarea  placeholder="Please Enter Candidate Remarks ..." class="form-control remarksTextCls" rows="3" name="remarksId" id="remarksId"></textarea>
	   <input type="hidden" class="hiddenAnswerCls" attr_hidden_id="remarksId"/>
      <div class="modal-footer">
	  <div id="errorId" style="color:red;"></div>
	  <div id="successId" style="color:green;"></div>
        <!--<button type="button" class="btn btn-default closeSecondModal"  id="updateId" data-dismiss="modal">UPDATE</button>-->
		<button type="button" class="btn btn-success " id="updateId">UPDATE</button>
		<span id="updateImgId"></span>
      </div>
    </div>
  </div>
</div>
<s:hidden name="pageAccessTypeName" id="pageAccessTypeId" value="%{pageAccessType}"/>

<!--<footer>
		<p class="text-center">All &copy; 2015. Telugu Desam Party</p>
</footer>-->
<script src="dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/activityDashboard/SelectDropDown/dropkick.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<script src="dist/newmultiselect/chosen.jquery.js" type="text/javascript"></script>
<script type="text/javascript">
//$("#mainheading").html(" PARTY MEETINGS DASHBOARD ");

$('input:checkbox').removeAttr('checked');

$(document).ready(function(e) {
	
	getStatesForLocationLevel();

	$('.cumulative,.individual').hide();
	$(document).on('click', '#cumulative', function (event) {
		$('.cumulative').show();
		$('.individual').hide();
		});
/*	$('#cumulative').click(function(){
		$('.cumulative').show();
		$('.individual').hide();
		});
*/	$('#individual').click(function(){
		$('.cumulative').hide();
		$('.individual').show();
		});
	
	$(".tbtn").click(function(){
		$(".themeControll").toggleClass("active");
	});
	$('#meetingLevel').val('2').trigger('change');
	$('#locationLevelSelId').val('2').trigger('change');
	//$('#stateId').val('1').trigger('change');
	setTimeout(function(){
	  $('#districtId').val(0);
	}, 2000);
	//meetinglevel,typeOfMeeting,meetingduration,locationscope,stateId,distId,constId,mtdId,vwId
});



	$("#resultTypeSelId").change(function(){
		if($(this).val()=="cumulative"){
			$("#cumulativeMeetingResultId").show();
			$("#individualMeetingResultId").hide();
		}else{
			$("#cumulativeMeetingResultId").hide();
			$("#individualMeetingResultId").show();
		}
		updateFunctions();
	});

  
  function getDistrictsForStates(){
	  
	  var accessType ='';
		var pageAccessTypeValue = $("#pageAccessTypeId").val();
		if(pageAccessTypeValue !=null && pageAccessTypeValue.length>0){
			accessType = pageAccessTypeValue.split(/[ ]+/).pop();
		}
		
		var lastIndex = pageAccessTypeValue.lastIndexOf(" ");
	  
  var state = $("#stateId").val();
	$("#districtId  option").remove();
	$("#districtId").append('<option value="0">Select District</option>');
	$("#imgForDist").show();
	var jsObj=
	{				
				stateId:state,
				elmtId:"districtList_d",
                type:"default",
				task:"getDistrictsForState"				
	}
    $.ajax({
          type:'GET',
          url: 'getDistrictsForStateAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
   $("#imgForDist").hide();
   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   location.reload(); 
	   }
	   $("#districtId").empty();
	  
     for(var i in result){
		 if(accessType !=null && accessType =="District"){
			 if(pageAccessTypeValue.substring(0,lastIndex) == result[i].name){
				  $("#districtId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
				  
				   //changing function calls				   
				   setTimeout(function(){
					   getMeetingSummary(2,0,1,2,0,0,0,0,0);	
						getPartyMeetingDetails(2,0,1,2,0,0,0,0,0);
				   }, 1000);
					 
				return;
			 }			
	    }		 
	  else{
		  if(result[i].id == 0){
		  $("#districtId").append('<option value="">Select District</option>');
          $("#districtId").append('<option value='+result[i].id+'>ALL</option>');
		}else{
		  $("#districtId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		}
	  } 	  	
	 }
	 
	    setTimeout(function(){
					   //changing function calls
						getMeetingSummary(2,0,1,2,0,0,0,0,0);	
						getPartyMeetingDetails(2,0,1,2,0,0,0,0,0);	 
				   }, 1000);
			
   });
  }

  	function getTypeOfMeeting()
	{
	 var locationLevel = $("#meetingLevel").val();
		var jsObj =	{locationLevel:locationLevel}
		$.ajax(
		{
			type: "POST",
			url:"getMeetingTypesAction.action",
			data:{task :JSON.stringify(jsObj)}
		}
		).done(function(result){
			$("#typeOfMeeting  option").remove();
			$("#typeOfMeeting").append('<option value="0">Select Meeting Type</option>');
			if(result!=null && result.length>0){
				for(var i in result){
					$("#typeOfMeeting").append('<option value="'+result[i].id+'">'+result[i].meetingType+'</option>');
				}
			}
			
			
		});
	}
	function getConstituenciesForDistricts(district){
	$("#constituencyId  option").remove();
	$("#constituencyId").append('<option value="0">Select Constituency</option>');
	$("#imgForConsti").show();
	var jsObj=
   {				
				districtId:district,
				elmtId:"districtList_d",
                type:"default",
				task:"getConstituenciesForDistricts"				
	}
    $.ajax({
          type:'GET',
          url: 'getConstituenciesForADistrictAjaxAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
   	$("#imgForConsti").hide();
   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   location.reload(); 
	   }
	   $("#constituencyId").empty();
	  
     for(var i in result){
	   if(result[i].id == 0){
		  $("#constituencyId").append('<option value="">Select Constituency</option>');
          $("#constituencyId").append('<option value='+result[i].id+'>ALL</option>');
	   }else{
	      $("#constituencyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }
	 }
   });
  }
   function getMandalCorporationsByConstituency()
	{	
			$("#imgForMandl").show();
			var constituencyId = $('#constituencyId').val();
			$("#mandalId  option").remove();
			$("#mandalId").append('<option value="">Select Mandal/Town/Divison</option>');
				var jsObj ={					
					constituencyId:constituencyId
				};
				 $.ajax({
					type : "GET",
					url : "getMandalDetailsByConstituencyAction.action",
					data : {task:JSON.stringify(jsObj)} 
				}).done(function(result){
				$("#imgForMandl").hide();
				if(result !=null)
				{	$("#mandalId").append('<option value="0">All</option>');
					for(var i in result)
					{
						$("#mandalId").append('<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>');
					}	
				}				
				});
	}
	
	function getPanchayatWardByMandal(){
			$("#imgForPanc").show();
			var mandalId=$("#mandalId").val();
			$("#panchayatId  option").remove();
			$("#panchayatId").append('<option value="">Select Village/Ward</option>');
			
			var jsObj={
				mandalId:mandalId
			}
			$.ajax({
				type : "POST",
				url : "getPanchayatWardByMandalAction.action",
				data : {task:JSON.stringify(jsObj)} 
			}).done(function(result){
				$("#imgForPanc").hide();
			if(result!=null && result.length>0){
				$("#panchayatId").append('<option value="0">Select Panchayat</option>');
				for(var i in result){
					$("#panchayatId").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
				}
			}
			
		});	
	}
  $(".locationCls").change(function() {
  });
  
 
	function getPartyMeetingDetails(meetinglevel,typeOfMeeting,meetingduration,locationscope,stateId,distId,constId,manTowDivId,wardPanId){
		$("#individualMeetingResultId").html('<img src="./images/icons/search.gif" class="offset7"  id="indiAjax" style="width:40px;height:40px;margin-left:500px;"/>');
		$("#dummyForExcel").html("");
		
		var dateType=meetingduration;
		var fromDate,temp1=new Date(),toDate;
		var month;
		if((temp1.getMonth()+1)<10){
			month=0+""+(temp1.getMonth()+1);
		}else{
			month=(temp1.getMonth()+1);
		}
		toDate = temp1.getFullYear()+"/"+month+"/"+temp1.getDate();
		var temp = new Date();
		if(dateType==1){
			temp.setDate(temp.getDate() - 30);
		}
		else if(dateType==2){
			temp.setDate(temp.getDate() - 90);
		}
		else if(dateType==3){
			temp.setDate(temp.getDate() - 180);
		}
		else if(dateType==4){
			temp.setDate(temp.getDate() - 270);
		}
		
		var finalmonth;
		if((temp.getMonth()+1)<10){
			finalmonth=0+""+(temp.getMonth()+1);
		}else{
			finalmonth=temp.getMonth()+1;
		}
		
		fromDate = temp.getFullYear()+"/"+finalmonth+"/"+temp.getDate();
		
		
		//Only Working In district Scenario
		var accessType ='';
		var pageAccessTypeValue = $("#pageAccessTypeId").val();
		if(pageAccessTypeValue !=null && pageAccessTypeValue.length>0){
			accessType = pageAccessTypeValue.split(/[ ]+/).pop();
		}
		
		if(accessType !=null && accessType == "District"){
			stateId = $("#stateId").val();
			distId = $("#districtId").val();
		}
		
		var jsObj =	{
			meetinglevel:meetinglevel,
			typeOfMeeting:typeOfMeeting,
			locationLevel:locationscope,
			stateId:stateId,
			distId:distId,
			constId:constId,
			manTowDivId:manTowDivId,
			wardPanId:wardPanId,
			startDate:fromDate,
			endDate:toDate
		}
			
		$.ajax({
			type: "POST",
			url:"getMeetingSummaryForLocationAction.action",
			data:{task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result!=null && result.partyMeetingsList!=null && result.partyMeetingsList.length>0){
				 $('.individual').show();
				var pmList = result.partyMeetingsList;
				
				$("#ttlDistMtngsSpanId").html(pmList[0].meetingsCount);
				$("#ttlPlnndMtngsSpanId").html(pmList[0].plannedMeetings);
				$("#ttlCndctdMtngsSpanId").html(pmList[0].conductedMeetings+" ("+pmList[0].conductedMeetingsPercent+" % )");
				var attndncPercent = pmList[0].averageInviteesAttendedPercent;
				if(attndncPercent!=null){
					$("#avgAttndInvtsSpanId").html(attndncPercent +" % ");	
				}else{
					$("#avgAttndInvtsSpanId").html("0.0 % ");
				}
				
				
				$("#updatedCounts").html("<div><table width='100%' class='table table-bordered'><tr align='center'><td><h4>MOM UPDATED MEETINGS : <span>"+pmList[0].momUpdatedMeetings+"</span></h4></td><td><h4>ATR UPDATED MEETINGS : <span>"+pmList[0].atrUpdatedMeetings+"</span></h4></td></tr></table></div>");

				var str = "";
				if(pmList!=null && pmList.length>0){
					
					str+='<table class="table table-bordered m_0" id="individualTableId">';
					str+='<thead>';
					str+='<tr>';
					if(meetinglevel==3){
						str+='<th rowspan="2">Assembly No</th>';
					}
					str+='<th rowspan="2">Meeting Name</th>';
					str+='<th rowspan="2">Location</th>';
					str+='<th rowspan="2">Schedule<br/> On</th>';
					str+='<th rowspan="2">Total <br/>Invitees</th>';
					str+='<th colspan="3" class="text-center">Attendance</th>';
					str+='<th rowspan="2">Total<br/> Absent</th>';
					str+='<th colspan="2"  class="text-center">MOM</th>';
					str+='<th colspan="2" class="text-center">ATR</th>';
					str+='</tr>';
					str+='<tr>';
					str+='<th>Total Attended</th>';
					str+='<th>Invitees</th>';
					str+='<th>Non Inivtees</th>';
					str+='<th>File</th>';
					str+='<th>Text</th>';
					str+='<th>File</th>';
					str+='<th>Text</th>';
					str+='</tr>';
					str+='</thead>';
					str+='<tbody id="individualResultBody">';
					for(var i in pmList){
						str+="<tr>";
						if(meetinglevel==3){
							str+="<th>"+pmList[i].assemblyNo+"</th>";
						}
						str+="<td><a style='cursor:pointer;' class='meetingNameCls' attr-title='"+pmList[i].meetingName+"' id='"+pmList[i].meetingId+"' value='TA'>"+pmList[i].meetingName+"</a></td>";
						if(pmList[i].location != null )
							str+="<td>"+pmList[i].location+"</td>";
						else 
							str+="<td></td>";
						str+="<td>"+pmList[i].scheduledOn+"</td>";
						if(pmList[i].attendanceInfo!=null){
							str+="<td>"+pmList[i].attendanceInfo.totalInvitees+"</td>";
							str+="<td>"+pmList[i].attendanceInfo.totalAttended+"</td>";
							str+="<td>"+pmList[i].attendanceInfo.inviteesAttended+"</td>";
							str+="<td>"+pmList[i].attendanceInfo.nonInviteesAttended+"</td>";
							//str+="<td class="meetingAbsentCls" style="cursor:pointer;" party_meeting_id='"+pmList[i].attendanceInfo.meetingId+"' location_value='"+pmList[i].attendanceInfo.locationId+"' location_level='"+meetinglevel+"' >"+pmList[i].attendanceInfo.totalAbsent+"</td>";
							str+="<td  class='meetingAbsentCls' style='cursor:pointer' title='click here' party_meeting_id='"+pmList[i].attendanceInfo.meetingId+"' attr-title='"+pmList[i].meetingName+"' >"+pmList[i].attendanceInfo.totalAbsent+"</td>";
						}else{
							str+="<td> - </td>";
							str+="<td> - </td>";
							str+="<td> - </td>";
							str+="<td> - </td>";
							str+="<td> - </td>";
						}
						
						if(pmList[i].docTxtInfo!=null){
							if(pmList[i].docTxtInfo.momFilesExist){
								str+="<td style='cursor:pointer' class='getSummary' attr_meetingId='"+pmList[i].meetingId+"' attr_type='MINUTE'><i class='glyphicon glyphicon-ok text-success'>("+pmList[i].docTxtInfo.momFilesCount+")</i></td>";
							}else{
								str+="<td><i class='glyphicon glyphicon-remove text-danger'></i></td>";
							}
							
							if(pmList[i].docTxtInfo.momTextExist){
								str+="<td style='cursor:pointer' class='getSummary'  data-toggle='modal' attr_meetingId='"+pmList[i].meetingId+"' attr_type='momText'><i class='glyphicon glyphicon-ok text-success'>("+pmList[i].docTxtInfo.momPointsCount+")</i></td>";
							}else{
								str+="<td><i class='glyphicon glyphicon-remove text-danger'></i></td>";
							}
							
							if(pmList[i].docTxtInfo.atrFilesExist){
								str+="<td style='cursor:pointer' class='getSummary' attr_meetingId='"+pmList[i].meetingId+"' attr_type='ATR'><i class='glyphicon glyphicon-ok text-success'>("+pmList[i].docTxtInfo.atrFilesCount+")</i></td>";
							}else{
								str+="<td><i class='glyphicon glyphicon-remove text-danger'></i></td>";
							}
							
							if(pmList[i].docTxtInfo.atrTextExist){
								str+="<td style='cursor:pointer' class='getSummary' attr_meetingId='"+pmList[i].meetingId+"' attr_type='atrText'><i class='glyphicon glyphicon-ok text-success'>("+pmList[i].docTxtInfo.atrTextCount+")</i></td>";
							}else{
								str+="<td><i class='glyphicon glyphicon-remove text-danger'></i></td>";
							}
						}else{
							str+="<td><i class='glyphicon glyphicon-remove text-danger'></i></td>";
							str+="<td><i class='glyphicon glyphicon-remove text-danger'></i></td>";
							str+="<td><i class='glyphicon glyphicon-remove text-danger'></i></td>";
							str+="<td><i class='glyphicon glyphicon-remove text-danger'></i></td>";
						}
						
						str+="</tr>";
					}
					str+='</tbody>';
					str+='</table>';
												
					
					
				}
				$("#individualMeetingResultId").html(str); 
				//$('#individualTableId').dataTable();//srujana
				$('#individualTableId').dataTable({
                     "aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
                  });
				
			}else{
				$('.individual').show();
				$("#individualMeetingResultId").html("No Records Found"); 
			}
			
			//for excel startDate
				if(pmList!=null && pmList.length>0){
					var str1='';
					str1+='<table class="table table-bordered m_0" id="individualTableIdForExcel">';
					str1+='<thead>';
					str1+='<tr>';
					if(meetinglevel==3){
						str1+='<th rowspan="2">Meeting Name</th>';
					}
					str1+='<th rowspan="2">Meeting Name</th>';
					str1+='<th rowspan="2">Location</th>';
					str1+='<th rowspan="2">Schedule<br/> On</th>';
					str1+='<th rowspan="2">Total <br/>Invitees</th>';
					str1+='<th colspan="3" class="text-center">Attendance</th>';
					str1+='<th rowspan="2">Total<br/> Absent</th>';
					str1+='<th colspan="2"  class="text-center">MOM</th>';
					str1+='<th colspan="2" class="text-center">ATR</th>';
					str1+='</tr>';
					str1+='<tr>';
					str1+='<th>Total Attended</th>';
					str1+='<th>Invitees</th>';
					str1+='<th>Non Inivtees</th>';
					str1+='<th>File</th>';
					str1+='<th>Text</th>';
					str1+='<th>File</th>';
					str1+='<th>Text</th>';
					str1+='</tr>';
					str1+='</thead>';
					//str+='<img src="./images/icons/search.gif" class="offset7"  id="indiAjax" style="width:20px;height:20px;display:none;"/>';
					str1+='<tbody>';
					for(var i in pmList){
						str1+="<tr>";
						if(meetinglevel==3){
							str1+="<th>"+pmList[i].assemblyNo+"</th>";
						}
						str1+="<td>"+pmList[i].meetingName+"</td>";
						str1+="<td>"+pmList[i].location+"</td>";
						str1+="<td>"+pmList[i].scheduledOn+"</td>";
						if(pmList[i].attendanceInfo!=null){
							str1+="<td>"+pmList[i].attendanceInfo.totalInvitees+"</td>";
							str1+="<td>"+pmList[i].attendanceInfo.totalAttended+"</td>";
							str1+="<td>"+pmList[i].attendanceInfo.inviteesAttended+"</td>";
							str1+="<td>"+pmList[i].attendanceInfo.nonInviteesAttended+"</td>";
							str1+="<td>"+pmList[i].attendanceInfo.totalAbsent+"</td>";
						}else{
							str1+="<td> - </td>";
							str1+="<td> - </td>";
							str1+="<td> - </td>";
							str1+="<td> - </td>";
							str1+="<td> - </td>";
						}
						
						if(pmList[i].docTxtInfo!=null){
							if(pmList[i].docTxtInfo.momFilesExist){
								str1+="<td>"+pmList[i].docTxtInfo.momFilesCount+"</td>";
							}else{
								str1+="<td>-</td>";
							}
							
							if(pmList[i].docTxtInfo.momTextExist){
								str1+="<td>"+pmList[i].docTxtInfo.momPointsCount+"</td>";
							}else{
								str1+="<td>-</td>";
							}
							
							if(pmList[i].docTxtInfo.atrFilesExist){
								str1+="<td>"+pmList[i].docTxtInfo.atrFilesCount+"</td>";
							}else{
								str1+="<td>-</td>";
							}
							
							if(pmList[i].docTxtInfo.atrTextExist){
								str1+="<td>"+pmList[i].docTxtInfo.atrTextCount+"</td>";
							}else{
								str1+="<td>-</td>";
							}
						}else{
							str1+="<td>-</td>";
							str1+="<td>-</td>";
							str1+="<td>-</td>";
							str1+="<td>-</td>";
						}
						
						str1+="</tr>";
					}
					str1+='</tbody>';
					str1+='</table>';
												
					$("#dummyForExcel").html(str1);
				}
		});
	}
	
	function updateFunctions(){
		$(".meetingDiv").css("display","none"); 
		GLSearchTypeStr = "TP"; 
		if($(".themeControll").hasClass("active")){
			$(".themeControll").removeClass("active");
		}
		var stateId=0,distId=0,constId=0,manTowDivId=0,wardPanId=0;
		
		if($("#locationLevelSelId").val()==0){
			alert("Please Select Location Level");
			return;
		}
		else if($("#locationLevelSelId").val()==1){
			if($("#stateId").val()==""){
				alert("Please Select State");
				return;
			}else{
				stateId=$("#stateId").val();
			}
		}else if($("#locationLevelSelId").val()==2){
			
			if($("#stateId").val()==""){
				alert("Please Select State");
				return;
			}else{
				stateId=$("#stateId").val();
			}
			
			if($("#districtId").val()==""){
				alert("Please Select District");
				return;
			}else{
				distId=$("#districtId").val();
			}
		}else if($("#locationLevelSelId").val()==3){
			
			if($("#stateId").val()==""){
				alert("Please Select State");
				return;
			}else{
				stateId=$("#stateId").val();
			}
			
			if($("#districtId").val()==""){
				alert("Please Select District");
				return;
			}else{
				distId=$("#districtId").val();
			}
			
			if($("#constituencyId").val()==""){
				alert("Please Select Constituency");
				return;
			}else{
				constId=$("#constituencyId").val();
			}
		}else if($("#locationLevelSelId").val()==4){
			
			if($("#stateId").val()==""){
				alert("Please Select State");
				return;
			}else{
				stateId=$("#stateId").val();
			}
			
			if($("#districtId").val()==""){
				alert("Please Select District");
				return;
			}else{
				distId=$("#districtId").val();
			}
			
			if($("#constituencyId").val()==""){
				alert("Please Select Constituency");
				return;
			}else{
				constId=$("#constituencyId").val();
			}
			
			if($("#mandalId").val()==""){
				alert("Please Select Mandal/Town/Divison");
				return;
			}else{
				manTowDivId=$("#mandalId").val();
			}
		}else if($("#locationLevelSelId").val()==5){
			
			if($("#stateId").val()==""){
				alert("Please Select State");
				return;
			}else{
				stateId=$("#stateId").val();
			}
			
			if($("#districtId").val()==""){
				alert("Please Select District");
				return;
			}else{
				distId=$("#districtId").val();
			}
			
			if($("#constituencyId").val()==""){
				alert("Please Select Constituency");
				return;
			}else{
				constId=$("#constituencyId").val();
			}
			
			if($("#mandalId").val()==""){
				alert("Please Select Mandal/Town/Divison");
				return;
			}else{
				manTowDivId=$("#mandalId").val();
			}
			
			if($("#panchayatId").val()==""){
				alert("Please Select Village/Ward");
				return;
			}else{
				wardPanId=$("#panchayatId").val();
			}
		}
		if($("#meetingLevel").val()==0){
			alert("Please Select MeetingLevel");
			$(".tbtn").trigger( "click" );
			return;
		}
		//alert("ls"+$("#locationLevelSelId").val()+",SI"+stateId+",DI"+distId+",CI"+constId+",MTDI"+manTowDivId+",VWI"+wardPanId);
		//$(".tbtn").trigger( "click" );
		
		var value = $("input:checkbox[class=grpLctn]:checked").val();
		
		getMeetingSummary($("#meetingLevel").val(),$("#typeOfMeeting").val(),$("#meetingDuration").val(),$("#locationLevelSelId").val(),stateId,distId,constId,manTowDivId,wardPanId);
		
		 if(typeof value === 'undefined'){
		   if($("#resultTypeSelId").val()=="individual"){
				getPartyMeetingDetails($("#meetingLevel").val(),$("#typeOfMeeting").val(),$("#meetingDuration").val(),$("#locationLevelSelId").val(),stateId,distId,constId,manTowDivId,wardPanId);	
			}else{
				getPartyMeetingDetailsCumulative($("#meetingLevel").val(),$("#typeOfMeeting").val(),$("#meetingDuration").val(),$("#locationLevelSelId").val(),stateId,distId,constId,manTowDivId,wardPanId);	
			}	
		 }else{
			 getPartyMeetingDetailsGroupLctn($("#meetingLevel").val(),$("#typeOfMeeting").val(),$("#meetingDuration").val(),$("#locationLevelSelId").val(),stateId,distId,constId,manTowDivId,wardPanId,value);	
		 }
		}
	
	function getMeetingSummary(meetingLevel,typeOfMeeting,meetingduration,locationscope,stateId,distId,constId,manTowDivId,wardPanId)
	{	
		var dateType=meetingduration;
		var fromDate,temp1=new Date(),toDate;
		var month;
		if((temp1.getMonth()+1)<10){
			month=0+""+(temp1.getMonth()+1);
		}else{
			month=(temp1.getMonth()+1);
		}
		toDate = temp1.getFullYear()+"/"+month+"/"+temp1.getDate();
		var temp = new Date();
		if(dateType==1){
			temp.setDate(temp.getDate() - 30);
		}
		else if(dateType==2){
			temp.setDate(temp.getDate() - 90);
		}
		else if(dateType==3){
			temp.setDate(temp.getDate() - 180);
		}
		else if(dateType==4){
			temp.setDate(temp.getDate() - 270);
		}
		
		var finalmonth;
		if((temp.getMonth()+1)<10){
			finalmonth=0+""+(temp.getMonth()+1);
		}else{
			finalmonth=temp.getMonth()+1;
		}
		
		fromDate = temp.getFullYear()+"/"+finalmonth+"/"+temp.getDate();
		
		//Only Working In district Scenario
		var accessType ='';
		var pageAccessTypeValue = $("#pageAccessTypeId").val();
		if(pageAccessTypeValue !=null && pageAccessTypeValue.length>0){
			accessType = pageAccessTypeValue.split(/[ ]+/).pop();
		}
		
		if(accessType !=null && accessType == "District"){
			stateId = $("#stateId").val();
			distId = $("#districtId").val();
		}
		
		var jsObj ={
				meetingLevel:meetingLevel,
				typeOfMeeting:typeOfMeeting,
				fromDate:fromDate,
				toDate:toDate,
				locationscope:locationscope,
				stateId:stateId,
				distId:distId,
				constId:constId,
				manTowDivId:manTowDivId,
				wardPanId:wardPanId
			};
		 $.ajax({
			type : "GET",
			url : "getMeetingSummaryAction.action",
			data : {task:JSON.stringify(jsObj)} 
		}).done(function(result){
			if(result!=null){
				if(result.totalInvitees!=null){
					$("#totalInvitieesHead").html(result.totalInvitees);
				}else{
					$("#totalInvitieesHead").html("0");
				}
				
				if(result.totalAttended!=null){
					$("#attendedHead").html(result.totalAttended);
				}else{
					$("#attendedHead").html("0");
				}
				
				if(result.totalNonInvitees!=null){
					$("#nonInviteeHead").html(result.totalNonInvitees);
				}else{
					$("#nonInviteeHead").html("0");
				}
				
				if(result.totalAbsent!=null){
					$("#totalAbsentHead").html(result.totalAbsent);
				}else{
					$("#totalAbsentHead").html("0");
				}
				
				
				var str='';
				str+='<tr class="font-12">';
				str+='<td  width="80px"></td>';
				str+='<td  width="80px">Total Invitees</td>';
				str+='<td width="140px">ATTENDED</td>';
				str+='<td>Non Invitees</td>';
				str+='<td>Invitees Absent</td>';
				str+='</tr>';
				str+='<tr>';
				str+='<tr>';
				str+='<td>PUBLIC REPRESENTATIVE</td>';
				if(result.totalCandidateInvitees!=null){
					str+='<td>'+result.totalCandidateInvitees+'</td>';
				}else{
					str+='<td>0</td>';
				}
				if(result.totalCandidateAttended!=null){
					str+='<td>'+result.totalCandidateAttended+'</td>';
				}else{
					str+='<td>0</td>';
				}
				
				if(result.totalCandidateNonInvitees!=null){
					str+='<td>'+result.totalCandidateNonInvitees+'</td>';
				}else{
					str+='<td>0</td>';
				}
				
				if(result.totalCandidateAbsent!=null){
					str+='<td>'+result.totalCandidateAbsent+'</td>';
				}else{
					str+='<td>0</td>';
				}
				
				str+='</tr>';
				str+='<td>PARTY ROLE</td>';
				
				if(result.totalCommitteeMemberInvitees!=null){
					str+='<td>'+result.totalCommitteeMemberInvitees+'</td>';
				}else{
					str+='<td>0</td>';
				}
				
				if(result.totalCommitteeMemberAttended!=null){
					str+='<td>'+result.totalCommitteeMemberAttended+'</td>';
				}else{
					str+='<td>0</td>';
				}
				
				if(result.totalCommitteeMemberNonInvitees!=null){
					str+='<td>'+result.totalCommitteeMemberNonInvitees+'</td>';
				}else{
					str+='<td>0</td>';
				}
				
				if(result.totalCommitteeMemberAbsent!=null){
					str+='<td>'+result.totalCommitteeMemberAbsent+'</td>';
				}else{
					str+='<td>0</td>';
				}
				
				str+='</tr>';
				str+='<tr>';
				str+='<td>NO ROLE</td>';
				
				if(result.totalNoRoleInvitees!=null){
					str+='<td>'+result.totalNoRoleInvitees+'</td>';
				}else{
					str+='<td>0</td>';
				}
				
				if(result.totalNoRoleAttended!=null){
					str+='<td>'+result.totalNoRoleAttended+'</td>';
				}else{
					str+='<td>0</td>';
				}
				
				if(result.totalNoRoleNonInvitees!=null){
					str+='<td>'+result.totalNoRoleNonInvitees+'</td>';
				}else{
					str+='<td>0</td>';
				}
				
				if(result.totalNoRoleAbsent!=null){
					str+='<td>'+result.totalNoRoleAbsent+'</td>';
				}else{
					str+='<td>0</td>';
				}
				str+='</tr>';
				
				$("#meetingSummaryBodyId").html(str);
			}
		});
	}
	
	$("#locationLevelSelId").change(function(){
		$(".locationDiv").hide();
		$(".locationCls").val($(".locationCls option:first").val());
		if($("#locationLevelSelId").val()==1){
			$("#stateDiv").show();
		}else if($("#locationLevelSelId").val()==2){
			$("#stateDiv").show();
			$("#districtDiv").show();
		}else if($("#locationLevelSelId").val()==3){
			$("#stateDiv").show();
			$("#districtDiv").show();
			$("#constituencyDiv").show();
		}else if($("#locationLevelSelId").val()==4){
			$("#stateDiv").show();
			$("#districtDiv").show();
			$("#constituencyDiv").show();
			$("#mandalDiv").show();
		}else if($("#locationLevelSelId").val()==5){
			$("#stateDiv").show();
			$("#districtDiv").show();
			$("#constituencyDiv").show();
			$("#mandalDiv").show();
			$("#panchayatDiv").show();
		}
	});
	
	function getPartyMeetingDetailsCumulative(meetinglevel,typeOfMeeting,meetingduration,locationscope,stateId,distId,constId,manTowDivId,wardPanId){
		
		$("#cumulativeMeetingResultId").html('<img src="./images/icons/search.gif" class="offset7"  id="cummAjax" style="width:40px;height:40px;margin-left:500px;"/>');
		$("#dummyForExcel").html("");
		
		$("#cumulativeMeetingTableBodyId").html("");
		var dateType=meetingduration;
		var fromDate,temp1=new Date(),toDate;
		var month;
		if((temp1.getMonth()+1)<10){
			month=0+""+(temp1.getMonth()+1);
		}else{
			month=(temp1.getMonth()+1);
		}
		toDate = temp1.getFullYear()+"/"+month+"/"+temp1.getDate();
		var temp = new Date();
		if(dateType==1){
			temp.setDate(temp.getDate() - 30);
		}
		else if(dateType==2){
			temp.setDate(temp.getDate() - 90);
		}
		else if(dateType==3){
			temp.setDate(temp.getDate() - 180);
		}
		else if(dateType==4){
			temp.setDate(temp.getDate() - 270);
		}
		
		var finalmonth;
		if((temp.getMonth()+1)<10){
			finalmonth=0+""+(temp.getMonth()+1);
		}else{
			finalmonth=temp.getMonth()+1;
		}
		
		fromDate = temp.getFullYear()+"/"+finalmonth+"/"+temp.getDate();
		
		var jsObj =	{
			meetinglevel:meetinglevel,
			typeOfMeeting:typeOfMeeting,
			locationLevel:locationscope,
			stateId:stateId,
			distId:distId,
			constId:constId,
			manTowDivId:manTowDivId,
			wardPanId:wardPanId,
			startDate:fromDate,
			endDate:toDate
		}
			
		$.ajax({
			type: "POST",
			url:"getMeetingCumulativeSummaryForLocationAction.action",
			data:{task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result!=null && result.partyMeetingsList!=null && result.partyMeetingsList.length>0){
				 $('.individual').show();
				var pmList = result.partyMeetingsList;
				var str = "";
				if(pmList!=null && pmList.length>0){
					
					$("#updatedCounts").html("<div><table width='100%' class='table table-bordered'><tr align='center'><td><h4>MOM UPDATED LOCATIONS : <span>"+pmList[0].momUpdatedMeetings+"</span></h4></td><td><h4>ATR UPDATED LOCATIONS : <span>"+pmList[0].atrUpdatedMeetings+"</span></h4></td></tr></table></div>");
					
					str+='<table class="table table-bordered m_0" id="cummulativeTableId">';
					str+='<thead>';
					str+='<tr>';
					if(meetinglevel==3){
						str+='<th rowspan="2">Assembly No</th>';
					}
					str+='<th rowspan="2">No Of Meetings</th>';
					str+='<th rowspan="2">Location</th>';
					str+='<th rowspan="2">Total <br/>Invitees</th>';
					str+='<th colspan="3" class="text-center">Attendance</th>';
					str+='<th rowspan="2">Total<br/> Absent</th>';
					str+='<th colspan="3"  class="text-center">MOM</th>';
					str+='<th colspan="3" class="text-center">ATR</th>';
					str+='</tr>';
					str+='<tr>';
					str+='<th>Total Attended</th>';
					str+='<th>Invitees</th>';
					str+='<th>Non Inivtees</th>';
					str+='<th></th>';
					str+='<th>File</th>';
					str+='<th>Text</th>';
					str+='<th></th>';
					str+='<th>File</th>';
					str+='<th>Text</th>';
					str+='</tr>';
					str+='</thead>';
					//str+='<img src='./images/icons/search.gif' class="offset7"  id="cummAjax" style="width:20px;height:20px;display:none;"/>';
					str+='<tbody id="cumulativeMeetingTableBodyId">';
					for(var i in pmList){
						str+="<tr>";
						if(meetinglevel==3){
							str+="<td rowspan=4>"+pmList[i].assemblyNo+"</td>";
						}
						str+="<td rowspan=4>"+pmList[i].meetingsCount+"</td>";
						str+="<td rowspan=4>"+pmList[i].location+"</td>";
						if(pmList[i].totalInvitees!=null){
							str+="<td rowspan=4>"+pmList[i].totalInvitees+"</td>";
							str+="<td rowspan=4>"+pmList[i].totalAttended+"</td>";
							str+="<td rowspan=4>"+pmList[i].inviteesAttended+"</td>";
							str+="<td rowspan=4>"+pmList[i].nonInviteesAttended+"</td>";
							str+="<td rowspan=4>"+pmList[i].totalAbsent+"</td>";
						}else{
							str+="<td rowspan=4> - </td>";
							str+="<td rowspan=4> - </td>";
							str+="<td rowspan=4> - </td>";
							str+="<td rowspan=4> - </td>";
							str+="<td rowspan=4> - </td>";
						}
						
						var atrDocs = pmList[i].atrDocTxtInfo;
						var momDocs = pmList[i].momDocTxtInfo;
						
						str+="<td>"+momDocs.bothCount+"</td>";
						str+="<td><i class='glyphicon glyphicon-ok text-success'></i></td>";
						str+="<td><i class='glyphicon glyphicon-ok text-success'></i></td>";
						str+="<td>"+atrDocs.bothCount+"</td>";
						str+="<td><i class='glyphicon glyphicon-ok text-success'></i></td>";
						str+="<td><i class='glyphicon glyphicon-ok text-success'></i></td>";
						str+="</tr>";
						
						str+="<tr>";
						str+="<td>"+momDocs.onlyFileCount+"</td>";
						str+="<td><i class='glyphicon glyphicon-ok text-success'></i></td>";
						str+="<td><i class='glyphicon glyphicon-remove text-danger'></i></td>";
						str+="<td>"+atrDocs.onlyFileCount+"</td>";
						str+="<td><i class='glyphicon glyphicon-ok text-success'></i></td>";
						str+="<td><i class='glyphicon glyphicon-remove text-danger'></i></td>";
						str+="</tr>";
						
						str+="<tr>";
						str+="<td>"+momDocs.onlyTxtCount+"</td>";
						str+="<td><i class='glyphicon glyphicon-remove text-danger'></i></td>";
						str+="<td><i class='glyphicon glyphicon-ok text-success'></i></td>";
						str+="<td>"+atrDocs.onlyTxtCount+"</td>";
						str+="<td><i class='glyphicon glyphicon-remove text-danger'></i></td>";
						str+="<td><i class='glyphicon glyphicon-ok text-success'></i></td>";
						str+="</tr>";
						
						str+="<tr>";
						str+="<td>"+momDocs.nothingCount+"</td>";
						str+="<td><i class='glyphicon glyphicon-remove text-danger'></i></td>";
						str+="<td><i class='glyphicon glyphicon-remove text-danger'></i></td>";
						str+="<td>"+atrDocs.nothingCount+"</td>";
						str+="<td><i class='glyphicon glyphicon-remove text-danger'></i></td>";
						str+="<td><i class='glyphicon glyphicon-remove text-danger'></i></td>";
						str+="</tr>";
					}
					str+='</tbody>';
					str+='</table>';
					
					
					
				}
				$("#cumulativeMeetingResultId").html(str); 
			}else{
				$("#cumulativeMeetingResultId").html("No Records Found"); 
			}
				
			if(pmList!=null && pmList.length>0){
				var str1='';
				str1+='<table class="table table-bordered m_0" id="cummulativeTableIdForExcel">';
				str1+='<thead>';
				str1+='<tr>';
				if(meetinglevel==3){
					str1+='<th rowspan="2">Assembly No</th>';
				}
				str1+='<th rowspan="2">No Of Meetings</th>';
				str1+='<th rowspan="2">Location</th>';
				str1+='<th rowspan="2">Total <br/>Invitees</th>';
				str1+='<th colspan="3" class="text-center">Attendance</th>';
				str1+='<th rowspan="2">Total<br/> Absent</th>';
				str1+='<th class="text-center">MOM</th>';
				str1+='<th class="text-center">ATR</th>';
				str1+='</tr>';
				str1+='<tr>';
				str1+='<th>Total Attended</th>';
				str1+='<th>Invitees</th>';
				str1+='<th>Non Inivtees</th>';
				str1+='<th></th>';
				str1+='<th></th>';
				str1+='<th>Type</th>';
				str1+='</tr>';
				str1+='</thead>';
				str1+='<tbody>';
				for(var i in pmList){
					str1+="<tr>";
					if(meetinglevel==3){
						str1+="<td rowspan=4>"+pmList[i].assemblyNo+"</td>";
					}
					str1+="<td rowspan=4>"+pmList[i].meetingsCount+"</td>";
					if(pmList[i].location != null)
						str1+="<td rowspan=4>"+pmList[i].location+"</td>";
					else 
						str1+="<td rowspan=4> - </td>";
					if(pmList[i].totalInvitees!=null){
						str1+="<td rowspan=4>"+pmList[i].totalInvitees+"</td>";
						str1+="<td rowspan=4>"+pmList[i].totalAttended+"</td>";
						str1+="<td rowspan=4>"+pmList[i].inviteesAttended+"</td>";
						str1+="<td rowspan=4>"+pmList[i].nonInviteesAttended+"</td>";
						str1+="<td rowspan=4>"+pmList[i].totalAbsent+"</td>";
					}else{
						str1+="<td rowspan=4> - </td>";
						str1+="<td rowspan=4> - </td>";
						str1+="<td rowspan=4> - </td>";
						str1+="<td rowspan=4> - </td>";
						str1+="<td rowspan=4> - </td>";
					}
					
						var atrDocs = pmList[i].atrDocTxtInfo;
						var momDocs = pmList[i].momDocTxtInfo;
						
						str1+="<td>"+momDocs.bothCount+"</td>";
						str1+="<td>"+atrDocs.bothCount+"</td>";
						str1+="<td>Both</td>";
						str1+="</tr>";
						
						str1+="<tr>";
						str1+="<td>"+momDocs.onlyFileCount+"</td>";
						str1+="<td>"+atrDocs.onlyFileCount+"</td>";
						str1+="<td>Only File</td>";
						str1+="</tr>";
						
						str1+="<tr>";
						str1+="<td>"+momDocs.onlyTxtCount+"</td>";
						str1+="<td>"+atrDocs.onlyTxtCount+"</td>";
						str1+="<td>Only Text</td>";
						str1+="</tr>";
						
						str1+="<tr>";
						str1+="<td>"+momDocs.nothingCount+"</td>";
						str1+="<td>"+atrDocs.nothingCount+"</td>";
						str1+="<td>Nothing</td>";
						str1+="</tr>";
						
						
				}
				str1+='</tbody>';
				str1+='</table>';
				
				$("#dummyForExcel").html(str1);
			}
		});
	}
	
	function getPartyMeetingDetailsGroupLctn(meetinglevel,typeOfMeeting,meetingduration,locationscope,stateId,distId,constId,manTowDivId,wardPanId,groupingLocationType){
		$("#cumulativeMeetingResultId").html('<img src="./images/icons/search.gif" class="offset7"  id="cummAjax" style="width:40px;height:40px;margin-left:500px;"/>');
		$("#dummyForExcel").html("");
		
		$("#cumulativeMeetingTableBodyId").html("");
		var dateType=meetingduration;
		var fromDate,temp1=new Date(),toDate;
		var month;
		if((temp1.getMonth()+1)<10){
			month=0+""+(temp1.getMonth()+1);
		}else{
			month=(temp1.getMonth()+1);
		}
		toDate = temp1.getFullYear()+"/"+month+"/"+temp1.getDate();
		var temp = new Date();
		if(dateType==1){
			temp.setDate(temp.getDate() - 30);
		}
		else if(dateType==2){
			temp.setDate(temp.getDate() - 90);
		}
		else if(dateType==3){
			temp.setDate(temp.getDate() - 180);
		}
		else if(dateType==4){
			temp.setDate(temp.getDate() - 270);
		}
		
		var finalmonth;
		if((temp.getMonth()+1)<10){
			finalmonth=0+""+(temp.getMonth()+1);
		}else{
			finalmonth=temp.getMonth()+1;
		}
		
		fromDate = temp.getFullYear()+"/"+finalmonth+"/"+temp.getDate();
		
		var jsObj =	{
			meetinglevel:meetinglevel,
			typeOfMeeting:typeOfMeeting,
			locationLevel:locationscope,
			stateId:stateId,
			distId:distId,
			constId:constId,
			manTowDivId:manTowDivId,
			wardPanId:wardPanId,
			startDate:fromDate,
			endDate:toDate,
			groupingLocationType:groupingLocationType
		}
			
		$.ajax({
			type: "POST",
			url:"getGroupingSummaryOfLocationAction.action",
			data:{task :JSON.stringify(jsObj)}
		}).done(function(result){
			var momCount=0;
			var atrCount=0;
			if(result!=null && result.partyMeetingsList!=null && result.partyMeetingsList.length>0){
				 $('.individual').show();
				var pmList = result.partyMeetingsList;
				var str = "";
				if(pmList!=null && pmList.length>0){
					str+='<table class="table table-bordered m_0" id="cummulativeTableId">';
					str+='<thead>';
					str+='<tr>';
					if(groupingLocationType=="constituency" && meetinglevel==3){
						str+='<th rowspan="2">Assembly No</th>';
					}
					str+='<th rowspan="2">No Of Meetings</th>';
					str+='<th rowspan="2">Location</th>';
					str+='<th rowspan="2">Total <br/>Invitees</th>';
					str+='<th colspan="3" class="text-center">Attendance</th>';
					str+='<th rowspan="2">Total<br/> Absent</th>';
					str+='<th colspan="3"  class="text-center">MOM</th>';
					str+='<th colspan="3" class="text-center">ATR</th>';
					str+='</tr>';
					str+='<tr>';
					str+='<th>Total Attended</th>';
					str+='<th>Invitees</th>';
					str+='<th>Non Inivtees</th>';
					str+='<th></th>';
					str+='<th>File</th>';
					str+='<th>Text</th>';
					str+='<th></th>';
					str+='<th>File</th>';
					str+='<th>Text</th>';
					str+='</tr>';
					str+='</thead>';
					//str+='<img src='./images/icons/search.gif' class="offset7"  id="cummAjax" style="width:20px;height:20px;display:none;"/>';
					str+='<tbody id="cumulativeMeetingTableBodyId">';
					for(var i in pmList){
						
						if(pmList[i].partyMeetingsList!=null && pmList[i].partyMeetingsList.length>0){
							for(var j in pmList[i].partyMeetingsList){
								if(pmList[i].partyMeetingsList[j].atrTextExist || pmList[i].partyMeetingsList[j].atrFilesExist){
									atrCount++;
								}
								if(pmList[i].partyMeetingsList[j].momFilesExist || pmList[i].partyMeetingsList[j].momTextExist){
									momCount++;
								}
							}
						}
						
						
						str+="<tr>";
						if(groupingLocationType=="constituency" && meetinglevel==3){
							str+="<td rowspan=4>"+pmList[i].assemblyNo+"</td>";
						}
						str+="<td rowspan=4>"+pmList[i].meetingsCount+"</td>";
						
						if(groupingLocationType=="state"){
							str+="<td rowspan=4>"+pmList[i].stateName+"</td>";
						}
						if(groupingLocationType=="district"){
							str+="<td rowspan=4>"+pmList[i].districtName+"</td>";
						}
						if(groupingLocationType=="constituency"){
							str+="<td rowspan=4>"+pmList[i].constituencyName+"</td>";
						}
						
						if(pmList[i].totalInvitees!=null){
							str+="<td rowspan=4>"+pmList[i].totalInvitees+"</td>";
							str+="<td rowspan=4>"+pmList[i].totalAttended+"</td>";
							str+="<td rowspan=4>"+pmList[i].inviteesAttended+"</td>";
							str+="<td rowspan=4>"+pmList[i].nonInviteesAttended+"</td>";
							str+="<td rowspan=4>"+pmList[i].totalAbsent+"</td>";
						}else{
							str+="<td rowspan=4> - </td>";
							str+="<td rowspan=4> - </td>";
							str+="<td rowspan=4> - </td>";
							str+="<td rowspan=4> - </td>";
							str+="<td rowspan=4> - </td>";
						}
						
						var atrDocs = pmList[i].atrDocTxtInfo;
						var momDocs = pmList[i].momDocTxtInfo;
						
						str+="<td>"+momDocs.bothCount+"</td>";
						str+="<td><i class='glyphicon glyphicon-ok text-success'></i></td>";
						str+="<td><i class='glyphicon glyphicon-ok text-success'></i></td>";
						str+="<td>"+atrDocs.bothCount+"</td>";
						str+="<td><i class='glyphicon glyphicon-ok text-success'></i></td>";
						str+="<td><i class='glyphicon glyphicon-ok text-success'></i></td>";
						str+="</tr>";
						
						str+="<tr>";
						str+="<td>"+momDocs.onlyFileCount+"</td>";
						str+="<td><i class='glyphicon glyphicon-ok text-success'></i></td>";
						str+="<td><i class='glyphicon glyphicon-remove text-danger'></i></td>";
						str+="<td>"+atrDocs.onlyFileCount+"</td>";
						str+="<td><i class='glyphicon glyphicon-ok text-success'></i></td>";
						str+="<td><i class='glyphicon glyphicon-remove text-danger'></i></td>";
						str+="</tr>";
						
						str+="<tr>";
						str+="<td>"+momDocs.onlyTxtCount+"</td>";
						str+="<td><i class='glyphicon glyphicon-remove text-danger'></i></td>";
						str+="<td><i class='glyphicon glyphicon-ok text-success'></i></td>";
						str+="<td>"+atrDocs.onlyTxtCount+"</td>";
						str+="<td><i class='glyphicon glyphicon-remove text-danger'></i></td>";
						str+="<td><i class='glyphicon glyphicon-ok text-success'></i></td>";
						str+="</tr>";
						
						str+="<tr>";
						str+="<td>"+momDocs.nothingCount+"</td>";
						str+="<td><i class='glyphicon glyphicon-remove text-danger'></i></td>";
						str+="<td><i class='glyphicon glyphicon-remove text-danger'></i></td>";
						str+="<td>"+atrDocs.nothingCount+"</td>";
						str+="<td><i class='glyphicon glyphicon-remove text-danger'></i></td>";
						str+="<td><i class='glyphicon glyphicon-remove text-danger'></i></td>";
						str+="</tr>";
					}
					$("#updatedCounts").html("<div><table width='100%' class='table table-bordered'><tr align='center'><td><h4>MOM UPDATED MEETINGS : <span>"+momCount+"</span></h4></td><td><h4>ATR UPDATED MEETINGS : <span>"+atrCount+"</span></h4></td></tr></table></div>");
					//console.log(atrCount+"--"+momCount);
					str+='</tbody>';
					str+='</table>';
				}
				$("#cumulativeMeetingResultId").html(str); 
				
				var str1 = "";
				if(pmList!=null && pmList.length>0){
					str1+='<table class="table table-bordered m_0" id="cummulativeTableIdForExcel">';
					str1+='<thead>';
					str1+='<tr>';
					if(groupingLocationType=="constituency" && meetinglevel==3){
						str1+='<th rowspan="2">Assembly No</th>';
					}
					str1+='<th rowspan="2">No Of Meetings</th>';
					str1+='<th rowspan="2">Location</th>';
					str1+='<th rowspan="2">Total <br/>Invitees</th>';
					str1+='<th colspan="3" class="text-center">Attendance</th>';
					str1+='<th rowspan="2">Total<br/> Absent</th>';
					str1+='<th class="text-center">MOM</th>';
					str1+='<th class="text-center">ATR</th>';
					str1+='</tr>';
					str1+='<tr>';
					str1+='<th>Total Attended</th>';
					str1+='<th>Invitees</th>';
					str1+='<th>Non Inivtees</th>';
					str1+='<th></th>';
					str1+='<th></th>';
					str1+='</tr>';
					str1+='</thead>';
					//str1+='<img src='./images/icons/search.gif' class="offset7"  id="cummAjax" style="width:20px;height:20px;display:none;"/>';
					str1+='<tbody id="cumulativeMeetingTableBodyId">';
					for(var i in pmList){
						
						if(pmList[i].partyMeetingsList!=null && pmList[i].partyMeetingsList.length>0){
							for(var j in pmList[i].partyMeetingsList){
								if(pmList[i].partyMeetingsList[j].atrTextExist || pmList[i].partyMeetingsList[j].atrFilesExist){
									atrCount++;
								}
								if(pmList[i].partyMeetingsList[j].momFilesExist || pmList[i].partyMeetingsList[j].momTextExist){
									momCount++;
								}
							}
						}
						
						
						str1+="<tr>";
						if(groupingLocationType=="constituency" && meetinglevel==3){
							str1+="<td rowspan=4>"+pmList[i].assemblyNo+"</td>";
						}
						str1+="<td rowspan=4>"+pmList[i].meetingsCount+"</td>";
						
						if(groupingLocationType=="state"){
							str1+="<td rowspan=4>"+pmList[i].stateName+"</td>";
						}
						if(groupingLocationType=="district"){
							str1+="<td rowspan=4>"+pmList[i].districtName+"</td>";
						}
						if(groupingLocationType=="constituency"){
							str1+="<td rowspan=4>"+pmList[i].constituencyName+"</td>";
						}
						
						if(pmList[i].totalInvitees!=null){
							str1+="<td rowspan=4>"+pmList[i].totalInvitees+"</td>";
							str1+="<td rowspan=4>"+pmList[i].totalAttended+"</td>";
							str1+="<td rowspan=4>"+pmList[i].inviteesAttended+"</td>";
							str1+="<td rowspan=4>"+pmList[i].nonInviteesAttended+"</td>";
							str1+="<td rowspan=4>"+pmList[i].totalAbsent+"</td>";
						}else{
							str1+="<td rowspan=4> - </td>";
							str1+="<td rowspan=4> - </td>";
							str1+="<td rowspan=4> - </td>";
							str1+="<td rowspan=4> - </td>";
							str1+="<td rowspan=4> - </td>";
						}
						
						var atrDocs = pmList[i].atrDocTxtInfo;
						var momDocs = pmList[i].momDocTxtInfo;
						
						str1+="<td>"+momDocs.bothCount+"</td>";
						str1+="<td>"+atrDocs.bothCount+"</td>";
						str1+="<td> Both Exist</td>";
						str1+="</tr>";
						
						str1+="<tr>";
						str1+="<td>"+momDocs.onlyFileCount+"</td>";
						str1+="<td>"+atrDocs.onlyFileCount+"</td>";
						str1+="<td> Only File Exist</i></td>";
						str1+="</tr>";
						
						str1+="<tr>";
						str1+="<td>"+momDocs.onlyTxtCount+"</td>";
						str1+="<td>"+atrDocs.onlyTxtCount+"</td>";
						str1+="<td>Only Text Exist</i></td>";
						str1+="</tr>";
						
						str1+="<tr>";
						str1+="<td>"+momDocs.nothingCount+"</td>";
						str1+="<td>"+atrDocs.nothingCount+"</td>";
						str1+="<td> Nothing Exist</td>";
						str1+="</tr>";
					}
					$("#updatedCounts").html("<div><table width='100%' class='table table-bordered'><tr align='center'><td><h4>MOM UPDATED MEETINGS : <span>"+momCount+"</span></h4></td><td><h4>ATR UPDATED MEETINGS : <span>"+atrCount+"</span></h4></td></tr></table></div>");
					//console.log(atrCount+"--"+momCount);
					str1+='</tbody>';
					str1+='</table>';
				}
				$("#dummyForExcel").html(str1);
				
			}else{
				$("#cumulativeMeetingResultId").html("No Records Found"); 
				$("#dummyForExcel").html(" No Records Found");
			}
		});
	}
	
	$(document).on('click','.grpLctn', function() {
		$('.grpLctn').not(this).prop('checked', false);
		//$("#resultTypeSelId").val("cumulative");
		var value = $("input:checkbox[class=grpLctn]:checked").val();
		
		if(typeof value === 'undefined'){
			var resSelId = $("#resultTypeSelId").val();
			if(resSelId=="cumulative"){
				$("#cumulativeMeetingResultId").show();
				$("#individualMeetingResultId").hide();
			}else{
				$("#cumulativeMeetingResultId").hide();
				$("#individualMeetingResultId").show();
			}

		}else{
			$("#cumulativeMeetingResultId").show();
			$("#individualMeetingResultId").hide();
			updateFunctions();
		}
				
		
	});
	
		var meetingLvl = $("#meetingLevel").val();
		if(meetingLvl>=2){
			$("#grpLctnDistId").show();
			$("#grpLctnConstId").hide();
		}
		if(meetingLvl>=3){
			$("#grpLctnConstId").show();
		}
		
	$("#meetingLevel").change(function(){
		var level = $("#meetingLevel").val();
		if(level==1){
			$("#grpLctnDistId").hide();
			$("#grpLctnConstId").hide();
		}
		if(level>=2){
			$("#grpLctnDistId").show();
			$("#grpLctnConstId").hide();
		}
		if(level>=3){
			$("#grpLctnConstId").show();
		}
	});
	
	function exportToExcell(tableDivId){
		var titleStr ="TOTAL ATTENDED";
		if(GLSearchTypeStr =='TP')
			titleStr ="TOTAL ATTENDED";
		else if(GLSearchTypeStr =='TI')
			titleStr ="TOTAL INVITEES";
		else if(GLSearchTypeStr =='IP')
			titleStr =" TOTAL INVITEES ATTENDED";
		else if(GLSearchTypeStr =='NI')
			titleStr ="TOTAL NON INVITEES ";
		else if(GLSearchTypeStr =='AB')
			titleStr ="TOTAL ABSENT";

		tableToExcel(''+tableDivId+'',''+titleStr+' MEMBER DETAILS ');
	}
	function exportToExcel(){
		var isGrpd = false;
		var value = $("input:checkbox[class=grpLctn]:checked").val();
		if(typeof value !== 'undefined'){
			isGrpd = true;
		}
			 
		if($("#resultTypeSelId").val()=="individual"){
			if(isGrpd){
				tableToExcel('cummulativeTableIdForExcel','Grouped Meetings Report');
			}else{
				tableToExcel('individualTableIdForExcel','Individual Meetings Report');
			}
		}else if($("#resultTypeSelId").val()=="cumulative"){
			tableToExcel('cummulativeTableIdForExcel','Cumulative Meetings Report');
		}
	}	
$(document).on('click','.getSummary', function() {
	  var fromType = $(this).attr("attr_type");
	  var jsObj =	{
			meetingId : $(this).attr("attr_meetingId"),
			type:$(this).attr("attr_type")
			}
			
		$.ajax({
			type: "POST",
			url:"getSummaryForAMeetingAction.action",
			data:{task :JSON.stringify(jsObj)}
		}).done(function(result){
			
			if(fromType=="momText"){
				if(result!=null && result.minutes!=null && result.minutes.length>0){
					var str='';
					for(var i in result.minutes){
						str+='<div style="border-bottom:1px dashed; padding:5px;">'+result.minutes[i]+"</div>";
					}
					$("#modelBody").html(str);
				}
				
				$("#modelTitle").html(result.subName);
				$("#myModal").modal('toggle');
			}else if(fromType=="atrText"){
				if(result!=null && result.atrPoints!=null && result.atrPoints.length>0){
					var str='';
					for(var i in result.atrPoints){
						str+='<div style="border-bottom:1px dashed; padding:5px;">'+result.atrPoints[i]+"</div>";
					}
					$("#modelBody").html(str);
				}
				
				$("#modelTitle").html(result.subName);
				$("#myModal").modal('toggle');
			}else{
				if(result!=null && result.docsList!=null && result.docsList.length>0){
					var str='';
					for(var i in result.docsList){
						str+='<div style="border-bottom:1px dashed; padding:5px;">';
						str+='<a target="_tab" href="https://mytdp.com/DocFiles/'+result.docsList[i].path+'">'+result.docsList[i].name+'</a>';
						str+='</div>';
					}
					$("#modelBody").html(str);
				}
				$("#modelTitle").html(result.docsList[0].subName);
				$("#myModal").modal('toggle');
			}
			
		});
	 
  });
  $(document).on('click','.meetingradioCls',function(){
	  /*$("#disignationDiv").val(0);
	  $('#disignationDiv').dropkick('refresh');*/
	   var searchType = $(this).attr("value");
	   GLSearchTypeStr = searchType;
	getTdpCadreDetailsForPartyMeeting(searchType,2);
  })
   $(document).on('click','.meetingNameCls',function(){
	   var id = $(this).attr("id");
	   var title = $(this).attr("attr-title");
	   GLSearchTypeStr = "TP"; 
	getTdpCadreDetailsForPartyMeetingOverView(id,title,2);
	
  })
  var membersResult = [];
  var meetingId;
  
  function getTdpCadreDetailsForPartyMeetingOverView(partyMeetingId,title,applyFilterTypeId)
  {
	 $(".meetingDiv").css("display","block"); 
	 $("#meetingTitleId").html(''+title+' Attended Members Details ');
	if(applyFilterTypeId ==2)
		$("#disignationDiv").html('');

	 meetingId =partyMeetingId;
	  var designationsArr = new Array();
		// designationsArr.push("");
		 
	 var jsObj =	{
			meetingId : partyMeetingId,
			searchType:GLSearchTypeStr,
			designationsArr:designationsArr
			}
			$("#disignationDiv").html('');
			$("#meetingAttendedMembersDiv").html('');
			$("#attendenceDiv").hide();
			$(".dk0-disignationDiv").html('');
			 $('#meetingAttendanceCntDiv').html('<img src="./images/icons/search.gif"  style="width:30px;height:30px;text-align:center;"/>');
			 
		$.ajax({
			type: "POST",
			url:"getTdpCadreDetailsForPartyMeetingAction.action",
			data:{task :JSON.stringify(jsObj)}
		}).done(function(result){
			 $('html,body').animate({
                scrollTop: $('#meetingAttendanceCntDiv').offset().top
            }, 1000);
			 $('#meetingAttendanceCntDiv').html('');
			if(result != null)
			{
				var titleStr ="TOTAL ATTENDED";
				if(GLSearchTypeStr =='TP')
					titleStr ="TOTAL ATTENDED";
				else if(GLSearchTypeStr =='TI')
					titleStr ="TOTAL INVITEES";
				else if(GLSearchTypeStr =='IP')
					titleStr =" TOTAL INVITEES ATTENDED";
				else if(GLSearchTypeStr =='NI')
					titleStr ="TOTAL NON INVITEES ";
				else if(GLSearchTypeStr =='AB')
					titleStr ="TOTAL ABSENT";
				$('#filterHeading').html(titleStr+' MEMBERS DETAILS ');
				$("#attendenceDiv").show();

				buildTdpCadreAttendanceCount(result);
				//if(result.inviteesAttendedCount != null && result.inviteesAttendedCount >0)
					buildTdpCadreAttendedMembers(result,applyFilterTypeId);
			}
			
			
			/*var designationVal = $("#disignationDiv").val();
			if(designationVal != null && designationVal.length > 0)
			 buildTdpCadreAttendedMembersByFilter();
			else
			buildTdpCadreAttendedMembers(result);*/
		})			
  }
   
  var GLSearchTypeStr = "TP";
  function getTdpCadreDetailsForPartyMeeting(searchType,applyFilterTypeId)
  {
	 $("#meetingAttendedMembersDiv").html(' <img src="./images/icons/search.gif" class="offset7" style="width:20px;height:20px;"/>');
	 
	// var searchType = $("input[type='radio'][name='meetingradio']:checked").val();
	if(applyFilterTypeId ==2)
		$("#disignationDiv").html('');

	 var designationsArr = new Array();
	 designationsArr  = $("#disignationDiv").val();

	 if(searchType == null || searchType.length == 0)
		 searchType = GLSearchTypeStr;
	 
	 if(designationsArr != null && designationsArr.length>0 && parseInt(designationsArr[0]) == 0)
		 designationsArr=[];
	 else if(designationsArr == null)
		 designationsArr=new Array();
	 var jsObj =	{
			meetingId : meetingId,
			searchType:searchType,
			designationsArr:designationsArr
			}
	

		$.ajax({
			type: "POST",
			url:"getTdpCadreDetailsForPartyMeetingAction.action",
			data:{task :JSON.stringify(jsObj)}
		}).done(function(result){
			 membersResult =[];
			 membersResult = result;
			 if(result != null){
				var titleStr ="TOTAL ATTENDED";
				if(searchType =='TP')
					titleStr ="TOTAL ATTENDED";
				else if(searchType =='TI')
					titleStr ="TOTAL INVITEES";
				else if(searchType =='IP')
					titleStr =" TOTAL INVITEES ATTENDED";
				else if(searchType =='NI')
					titleStr ="TOTAL NON INVITEES ";
				else if(searchType =='AB')
					titleStr ="TOTAL ABSENT";
				$('#filterHeading').html(titleStr+' MEMBERS DETAILS ');
				
				//if(result.inviteesAttendedCount != null && result.inviteesAttendedCount >0)
					buildTdpCadreAttendedMembers(result,applyFilterTypeId);
				
			}
			
				
		});			
  }
  function buildTdpCadreAttendanceCount(result)
  { 
     var str ='';
	 
	// if(result.inviteesAttendedCount != null && result.inviteesAttendedCount >0){
		 str+='<table class="table table-bordered bg_ff">';
		 str+='<tbody><tr>';
		  str+='<td class="text-center">';
		 str+='<h3> <a class="meetingradioCls" name="meetingradio" value="TP" style="cursor:pointer;" href="javascript:{};"> <span  style="color:green;font-weight: bold">'+result.attendedCount+' </span> </a></h3>';
		 str+='<h4> TOTAL ATTENDED </h4>';
		 //str+='<p>Members in Meeting</p>';
		 str+='</td>';
		 str+='<td class="text-center">';
		 str+='<h3> <a class="meetingradioCls" name="meetingradio" value="TI" style="cursor:pointer;" href="javascript:{};" > <span  style="color:green;font-weight: bold"> '+result.inviteesCount+' </span> </a></h3>';
		 str+='<h4>TOTAL INVITEES</h4>';
		// str+='<p>Members in Meeting</p>';
		 str+='</td>';
		 str+='<td class="text-center">';
		 str+='<h3>  <a class="meetingradioCls" name="meetingradio" value="IP" style="cursor:pointer;" href="javascript:{};" > <span  style="color:green;font-weight: bold">'+result.inviteesAttendedCount+' </span> </a></h3>';
		 str+='<h4 class="text-danger">INVITEES ATTENDED</h4>';
		// str+='<p>Members in Meeting</p>';
		 str+='</td>';
		 str+='<td class="text-center">';
		 str+='<h3>  <a class="meetingradioCls" name="meetingradio" value="AB" style="cursor:pointer;" href="javascript:{};" > <span  style="color:green;font-weight: bold">'+result.absentCount+' </span> </a></h3>';
		 str+='<h4 class="text-warning">INVITEES ABSENT</h4>';
		 //str+='<p>Members in Meeting</p>';
		 str+='</td>';
		 str+='<td class="text-center">';
		 str+='<h3>  <a class="meetingradioCls" name="meetingradio" value="NI" style="cursor:pointer;" href="javascript:{};" > <span  style="color:green;font-weight: bold">'+result.nonInviteesAttendedCount+' </span></a></h3>';
		 str+='<h4>NON INVITEES</h4>';
		 //str+='<p>Members in Meeting</p>';
		 str+='</td>';
		 str+='</tr>';
		 str+='</tbody></table>';
	// }
	// else{
	//	 str+='No Data Available ...';
	// }
	  
	  $("#meetingAttendanceCntDiv").html(str);
	/*  $("#disignationDiv").html('');
	
	  $("#disignationDiv").append("<option value='0'>Select Designation</option>");
	  if(result.designationWiseCountsList != null && result.designationWiseCountsList.length > 0)
	  {
		   console.log(result.designationWiseCountsList.length);
		   for(var i in result.designationWiseCountsList)
			$("#disignationDiv").append("<option value='"+result.designationWiseCountsList[i].designation+"'>"+result.designationWiseCountsList[i].designation+" ("+result.designationWiseCountsList[i].count+")</option>");
	  }
	 $("#disignationDiv").dropkick();
	 */
  }
 
  function buildTdpCadreAttendedMembers(result,applyFilterTypeId)
  {	 
	
	  var str ='';	  
	 /* if(result.partyMeetingWSVoList == null || result.partyMeetingWSVoList.length == 0)
	  {
			$("#meetingAttendedMembersDiv").html("No Data Available..");  
		    return;
	  }*/
		str+='<table class="table table-condensed" id="membersTab" border=1>';
		str+='<thead class="bg_f0">';
		str+='<tr><th></th>';
		str+='<th>NAME</th>';
		str+='<th>MEMBERSHIP NO</th>';
		str+='<th>DESIGNATION</th>';
		str+='<th>OWN CONSTITUENCY</th>';
		str+='<th>PARTICIPATED CONSTITUENCY</th>';
		//str+='<th>Mobile Number</th>';
		str+='<th> STATUS </th>';
		str+='</tr></thead>';
		str+='<tbody>';
		for(var i in result.partyMeetingWSVoList)
		{
		str+='<tr>';
	    str+='<td>';
		str+='<img class="img-thumbnail img-responsive profileImage" src="'+result.partyMeetingWSVoList[i].imgStr+'">';
		str+='</td>';
		str+='<td>'+result.partyMeetingWSVoList[i].name+'</td>';
		str+='<td>'+result.partyMeetingWSVoList[i].memberShipNo+'</td>';
		if(result.partyMeetingWSVoList[i].designation != null)
		str+='<td>'+result.partyMeetingWSVoList[i].designation+'</td>';
		else
		str+='<td></td>';
		//str+='<td>'+result.partyMeetingWSVoList[i].mobileNo+'</td>';
		if(result.partyMeetingWSVoList[i].ownConstituency != null)
			str+='<td>'+result.partyMeetingWSVoList[i].ownConstituency+'</td>';
		else
			str+='<td></td>';
	
		if(result.partyMeetingWSVoList[i].participatedConstituency != null)
			str+='<td>'+result.partyMeetingWSVoList[i].participatedConstituency+'</td>';
		else
			str+='<td></td>';
		str+='<td>';
		if(result.partyMeetingWSVoList[i].memberType != null && result.partyMeetingWSVoList[i].memberType == 'Invitee Present')
		{
			str+='<img class="img-responsive attendanceIcon" src="dist/img/invitee-present.jpg">';
			str+='<span>'+result.partyMeetingWSVoList[i].memberType+'</span>';
		}
		else if(result.partyMeetingWSVoList[i].memberType != null && result.partyMeetingWSVoList[i].memberType == 'Invitee Absent')
		{
			str+='<img class="img-responsive attendanceIcon" src="dist/img/invitee-absent.jpg">';
			str+='<span>'+result.partyMeetingWSVoList[i].memberType+'</span>';
		}
		else {
			str+='<img class="img-responsive attendanceIcon" src="dist/img/non-invitee-absent.jpg">';
			str+='<span>'+result.partyMeetingWSVoList[i].memberType+'</span>';
		}
		
		str+='</td>';
		str+='</tr>';
		
		}
		str+='</tbody>';
		str+='</table>';

		$("#meetingAttendedMembersDiv").html(str);
		$("#membersTab").dataTable({
			"iDisplayLength": 50,
				"aLengthMenu": [[50,100, 200, 500, -1], [50,100, 200, 500, "All"]]			
		});

/* START EXPORT EXCEL TABLE */
str="";
str+='<table class="table table-condensed" id="excelMembersTab" border=1>';
		str+='<thead class="bg_f0">';
		//str+='<tr><th></th>';
		str+='<th>NAME</th>';
		str+='<th>MEMBERSHIP NO</th>';
		str+='<th>DESIGNATION</th>';
		str+='<th>OWN CONSTITUENCY</th>';
		str+='<th>PARTICIPATED CONSTITUENCY</th>';
		//str+='<th>Mobile Number</th>';
		str+='<th> STATUS </th>';
		str+='</tr></thead>';
		str+='<tbody>';
		for(var i in result.partyMeetingWSVoList)
		{
		str+='<tr>';
	   // str+='<td>';
		//str+='<img class="img-thumbnail img-responsive profileImage" src="'+result.partyMeetingWSVoList[i].imgStr+'">';
		//str+='</td>';
		str+='<td>'+result.partyMeetingWSVoList[i].name+'</td>';
		str+='<td>'+result.partyMeetingWSVoList[i].memberShipNo+'</td>';
		if(result.partyMeetingWSVoList[i].designation != null)
		str+='<td>'+result.partyMeetingWSVoList[i].designation+'</td>';
		else
		str+='<td></td>';
		//str+='<td>'+result.partyMeetingWSVoList[i].mobileNo+'</td>';
		if(result.partyMeetingWSVoList[i].ownConstituency != null)
			str+='<td>'+result.partyMeetingWSVoList[i].ownConstituency+'</td>';
		else
			str+='<td></td>';
	
		if(result.partyMeetingWSVoList[i].participatedConstituency != null)
			str+='<td>'+result.partyMeetingWSVoList[i].participatedConstituency+'</td>';
		else
			str+='<td></td>';
		str+='<td>';
		if(result.partyMeetingWSVoList[i].memberType != null && result.partyMeetingWSVoList[i].memberType == 'Invitee Present')
		{
			//str+='<img class="img-responsive attendanceIcon" src="dist/img/invitee-present.jpg">';
			str+='<span>'+result.partyMeetingWSVoList[i].memberType+'</span>';
		}
		else if(result.partyMeetingWSVoList[i].memberType != null && result.partyMeetingWSVoList[i].memberType == 'Invitee Absent')
		{
			//str+='<img class="img-responsive attendanceIcon" src="dist/img/invitee-absent.jpg">';
			str+='<span>'+result.partyMeetingWSVoList[i].memberType+'</span>';
		}
		else {
			//str+='<img class="img-responsive attendanceIcon" src="dist/img/non-invitee-absent.jpg">';
			str+='<span>'+result.partyMeetingWSVoList[i].memberType+'</span>';
		}
		
		str+='</td>';
		str+='</tr>';
		
		}
		str+='</tbody>';
		str+='</table>';
$("#exportMeetingAttendedMembersDiv").html(str);
/* END EXPORT EXCEL TABLE */

	  
		if(applyFilterTypeId == 2){
			 $("#disignationDiv").html('');
				   //$("#disignationDiv").append("<option value='0'>Select Designation</option>");
				  if(result.designationWiseCountsList != null && result.designationWiseCountsList.length > 0)
				  {
					   for(var i in result.designationWiseCountsList)
						$("#disignationDiv").append("<option value='"+result.designationWiseCountsList[i].designation+"'>"+result.designationWiseCountsList[i].designation+" ("+result.designationWiseCountsList[i].count+")</option>");
				  }
				$("#disignationDiv").trigger("chosen:updated");
				 /* $("#disignationDiv").dropkick();
				var select = new Dropkick("#disignationDiv");
				select.refresh(); */
		}
	$("#disignationDiv").chosen();
  }
  function clearDesignations()
  {
	  $("#disignationDiv").val(0);
	  //$('#disignationDiv').dropkick('refresh');
  }
</script>
<script>
 var tableToExcel = (function() {
	var uri = 'data:application/vnd.ms-excel;base64,'
    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>'
    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
	return function(table, name) {
    if (!table.nodeType) table = document.getElementById(table)
    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
    window.location.href = uri + base64(format(template, ctx))
  }
  })() 
</script>
<script>
var pageAccessTypeValue = $("#pageAccessTypeId").val();
if(pageAccessTypeValue !=null && pageAccessTypeValue.length>0){
	var accessType = pageAccessTypeValue.split(/[ ]+/).pop();
	if(accessType !=null && accessType.length>0 && accessType=="District"){
		
		$("#meetingLevel  option").remove();		
		$("#meetingLevel").append('<option value="0">Select Level</option>');
		
		$("#meetingLevel").append('<option value="2">DISTRICT</option>');
		$("#meetingLevel").append('<option value="3">CONSTITUENCY</option>');
		$("#meetingLevel").append('<option value="4>MANDAL/TOWN/DIVISION</option>');
		$("#meetingLevel").append('<option value="5">VILLAGE/WARD</option>');
		$("#meetingLevel").append('<option value="5">VILLAGE/WARD</option>');
		
		$("#locationLevelSelId option").remove();
		$("#locationLevelSelId").append('<option value="0">Select Location Level</option>');
		
		$("#locationLevelSelId").append('<option value="2">District</option>');
		$("#locationLevelSelId").append('<option value="3">Constituency</option>');
		$("#locationLevelSelId").append('<option value="4>Mandal/Town/Division</option>');
		$("#locationLevelSelId").append('<option value="5">Village/Ward</option>');
	}
}

function getStatesForLocationLevel(){	

		var accessType ='';
		var pageAccessTypeValue = $("#pageAccessTypeId").val();
		if(pageAccessTypeValue !=null && pageAccessTypeValue.length>0){
			accessType = pageAccessTypeValue.split(/[ ]+/).pop();
		}
		
		
		$("#stateId  option").remove();	
		if(accessType !=null && accessType !="District"){
			$("#stateId").append('<option value="0">All</option>');
		}
		
		
		$.ajax({
			type: "POST",
			url:"getStatesForLocationLevelAction.action",
			data:{}
		}).done(function(result){
			if(result !=null && result.length>0){
				for(var i in result){
					$("#stateId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
				}
			}
			if(accessType !=null && accessType !="District"){
				$('#stateId').val('1').trigger('change');
			}else{
				getDistrictsForStates();
			}
		});
}

function getPartyMettingOfAbsents(meetingId,locationValue,locationLevel,title){
	$("#modelBody").html('<img src="./images/icons/search.gif" class="offset7"  id="indiAjax" style="width:30px;height:30px;margin-left:270px;"/>');
	$("#myModal").modal('show');
	$("#modelTitle").html(title+" of Absent Candidates");
	var dateType=$("#meetingDuration").val();
	var fromDate,temp1=new Date(),toDate;
		var month;
		if((temp1.getMonth()+1)<10){
			month=0+""+(temp1.getMonth()+1);
		}else{
			month=(temp1.getMonth()+1);
		}
		toDate = temp1.getFullYear()+"/"+month+"/"+temp1.getDate();
		var temp = new Date();
		if(dateType==1){
			temp.setDate(temp.getDate() - 30);
		}
		else if(dateType==2){
			temp.setDate(temp.getDate() - 90);
		}
		else if(dateType==3){
			temp.setDate(temp.getDate() - 180);
		}
		else if(dateType==4){
			temp.setDate(temp.getDate() - 270);
		}
		
		var finalmonth;
		if((temp.getMonth()+1)<10){
			finalmonth=0+""+(temp.getMonth()+1);
		}else{
			finalmonth=temp.getMonth()+1;
		}
		
		fromDate = temp.getFullYear()+"/"+finalmonth+"/"+temp.getDate();
			var jsObj={
			startDate:fromDate,
			endDate:toDate,
			typeOfMeeting :0,
			locationLevel :0,
			locationValue:0,
			meetingId:meetingId
			}
			$.ajax({
				type : "POST",
				url : "getPartyMettingOfAbsentsAction.action",
				data : {task:JSON.stringify(jsObj)} 
			}).done(function(result){
				if(result != null){
					buildPartyMettingOfAbsents(result,meetingId);
				}
			
		});	
	}
	function updateMeetingAbsentRemarks(invitteId,candidateName,meetingId,candidateId){
		var comment =$(".remarksTextCls").val();
		if(comment.trim() == null || comment.trim() == ""){
			$("#errorId").html("please give remarks");
			return;
		}else{
			$("#errorId").html("");
		}
		$("#updateImgId").html('<img src="./images/icons/search.gif" class="offset7"  id="indiAjax" style="width:30px;height:30px;margin-left:10px;"/>');
			var jsObj={
			inviteeId:invitteId,
			remarks:comment,
			meetingId:meetingId,
			candidateId:candidateId
			}
			$.ajax({
				type : "POST",
				url : "updateMeetingAbsentRemarksAction.action",
				data : {task:JSON.stringify(jsObj)} 
			}).done(function(result){
				if(result == "success"){
					//alert("update remarks successfully");
					$("#successId").html("update remarks successfully");
					$("#updateImgId").html('');
					setTimeout(function(){
					  $("#myModalId").modal('hide');
					}, 500);
					setTimeout(function(){
                      $('body').addClass("modal-open");
                    }, 1000);  
					getPartyMettingOfAbsents(meetingId,0,0,title);
				}
			
		});	
	} 
	var meetingId;
	var title;
	 $(document).on('click','.meetingAbsentCls',function(){
		 $(".remarksTextCls").val("");
		 $("#errorId").html("");
	    meetingId = $(this).attr("party_meeting_id");
	   var locationValue=$(this).attr("location_value");
	   var locationLevel =$(this).attr("location_level");
	    title=$(this).attr("attr-title");
	  getPartyMettingOfAbsents(meetingId,locationValue,locationLevel,title);
  })
   $(document).on('click','.remarkCls',function(){
	   $("#myModalId").modal('show');
	   $(".remarksTextCls").val("");
	   $("#errorId").html(""); 
	   $("#successId").html(""); 
	  var invitteId = $(this).attr("attr_invitee");
	  var candidateName =$(this).attr("attr_candidate");
	  var meetingId=$(this).attr("attr_meeting_id");
	  var candidateId =$(this).attr("attr_candidateId");
	  var remarks =$(this).attr("attr_remarks");
	  $("#modelTitleId").html(candidateName+" of Remarks");
	  $("#updateId").attr("attr_invitee",invitteId)
	  $("#updateId").attr("attr_candidate",candidateName)
	  $("#updateId").attr("attr_meeting_id",meetingId)
	  $("#updateId").attr("attr_candidateId",candidateId)
	   $(".remarksTextCls").val(remarks);
  })
  $(document).on('click','#updateId',function(){
	  var invitteId = $(this).attr("attr_invitee");
	  var candidateName =$(this).attr("attr_candidate");
	  var meetingId = $(this).attr("attr_meeting_id");
	   var candidateId = $(this).attr("attr_candidateId");
	  updateMeetingAbsentRemarks(invitteId,candidateName,meetingId,candidateId);
  })
  //var userId = '${sessionScope.USER.registrationID}';
  function buildPartyMettingOfAbsents(result,meetingId)
  {
	var str='';
	if(result !=null){
			str+='<div class="table-responsive m_top20">';
				str+='<table class="table table-bordered  dataTableSorting" id="absentCandidateId">';
				str+='<thead>';
					str+='<tr>';
						str+='<th class="text-capital">Candidate Name</th>';
						str+='<th class="text-capital">Contact Number</th>';
						str+='<th class="text-capital">Meeting Type</th>';
						//str+='<th class="text-capital">Attendance</th>';
						str+='<th class="text-capital">Remarks</th>';
						
					str+='</tr>';
				str+='</thead>';
				
				  str+='<tbody>';
					for(var i in result){
						var userGroupId = result[0].userGroupId;
					str+='<tr>';
						str+='<td>'+result[i].name+'</td>';
						str+='<td>'+result[i].mobileNo+'</td>';
						str+='<td>'+result[i].meetingType+'</td>';
						//str+='<td>'+result[i].isCondacted+'</td>';
						if(userGroupId == "35" || userGroupId == "188"){
						if(result[i].remarks == "" || result[i].remarks == null){
							
							str+='<td><i class="fa fa-pencil-square-o text-danger remarkCls" attr_invitee='+result[i].inviteeId+' attr_candidate=\''+result[i].name+'\' attr_meeting_id='+meetingId+' attr_candidateId='+result[i].id+' attr_remarks=\''+result[i].remarks+'\' style="cursor:pointer" title="click here" ></i></td>';
						}else{
							str+='<td>'+result[i].remarks+'&nbsp<i class="fa fa-pencil-square-o text-danger remarkCls" attr_invitee='+result[i].inviteeId+' attr_candidate=\''+result[i].name+'\'  attr_meeting_id='+meetingId+' attr_candidateId='+result[i].id+'  attr_remarks=\''+result[i].remarks+'\' style="cursor:pointer" title="click here" ></i></td>';
						}
						}else{
						
							str+='<td>'+result[i].remarks+'</td>';
						}
					str+='</tr>';
					}
				 str+='</tbody>';
				str+='</table>';	
			str+='</div>';
			
		$("#modelBody").html(str);
		 $("#absentCandidateId").dataTable({
			"aaSorting": [],
			"iDisplayLength" : 10	
		   });
	}else{
			$("#modelBody").html('<h3>NO DATA AVAILABLE</h3>')
		}

}
 $(document).on("click",".closeSecondModal",function(){
	$(".remarksTextCls").val("");
	$("#errorId").html(""); 
  setTimeout(function(){
    $('body').addClass("modal-open");
  }, 1000);                     
}); 
//Code related to Google Translator START
var control;
 google.load("elements", "1", {
          packages: "transliteration"
    });

    function onLoad() {
      var options = {
          sourceLanguage:
              google.elements.transliteration.LanguageCode.ENGLISH,
          destinationLanguage:
              [google.elements.transliteration.LanguageCode.TELUGU],
          shortcutKey: 'alt+t',
          transliterationEnabled: true
      };
     control  = new google.elements.transliteration.TransliterationControl(options);
		control.makeTransliteratable(['remarksId']);
			 $(".hiddenAnswerCls").each(function(){
				var id = $(this).attr("attr_hidden_id");
				control.makeTransliteratable([''+id+'']);
			}); 
    }
 google.setOnLoadCallback(onLoad);
//Code related to Google Translator END
</script>
</body>
</html>