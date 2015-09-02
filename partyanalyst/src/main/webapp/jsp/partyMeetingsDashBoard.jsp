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
<link href="dist/Dropzone/dropzone.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
<style type="text/css">
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
</style>

</head>
<body>
<header  class="eventsheader">
<!-- <img src="css/Training/img/header.jpg" width="100%"> -->
	<div class="container">
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
					   <li><a href="dashBoardAction.action"><span>DASHBOARD</span></a> </li>
					   <li><a tabindex="-1" href="newlogoutAction.action">Sign Out</a></li>
					
                    </ul>   
            </div>			
        </div>       
    </div>
	
	
</header>
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
													<tbody id="meetingSummaryBodyId" align="center">
														<!--<tr class="font-12">
															<td  width="100px"></td>
															<td  width="100px">Total Invitees</td>
															<td>Total Non Invitees</td>
															<td>Total Invitees Absent</td>
														</tr>
														<tr>
															<td>PARTY ROLE</td>
															<td>50</td>
															<td>50</td>
															<td>50</td>
														</tr>
														<tr>
															<td>PUBLIC REPRESENTATIVE</td>
															<td>50</td>
															<td>50</td>
															<td>50</td>
														</tr>
														<tr>
															<td>ATTENDED</td>
															<td>50</td>
															<td>50</td>
															<td>50</td>
														</tr>
														<tr>
															<td>NO ROLE</td>
															<td>50</td>
															<td>50</td>
															<td>50</td>
														</tr>-->
													</tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                	<div class="table-responsive bg_ff">
                                        <table class="table m_0 table-bordered">
                                            <tr class="font-12">
                                                <td><h4 class="m_top20">
                                                	TOTAL DISTRICT COMMITTEES<span class="pull-right" id="ttlDistMtngsSpanId">0</span>
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
												<!--<table class="table table-bordered m_0" id="individualTableId">
													<thead>
														<tr>
															<th rowspan="2">Meeting Name</th>
															<th rowspan="2">Location</th>
															<th rowspan="2">Schedule<br/> On</th>
															<th rowspan="2">Total <br/>Invitees</th>
															<th colspan="3" class="text-center">Attendance</th>
															<th rowspan="2">Total<br/> Absent</th>
															<th colspan="2"  class="text-center">MOM</th>
															<th colspan="2" class="text-center">ATR</th>
														</tr>
														<tr>
															<th>Total Attended</th>
															<th>Invitees</th>
															<th>Non Inivtees</th>
															<th>File</th>
															<th>Text</th>
															<th>File</th>
															<th>Text</th>
														</tr>
													</thead>
													<img src='./images/icons/search.gif' class="offset7"  id="indiAjax" style="width:20px;height:20px;display:none;"/>
													<tbody id="individualResultBody"></tbody>
												</table>-->
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
					<option value="4">MANDAL</option> 
					<option value="5">TOWN</option> 
					<option value="6">DIVISION</option> 
					<option value="7">VILLAGE</option> 
					<option value="8">WARD</option> 
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
                   	<option value="0">All</option>
					<option value="1">AndhraPradesh</option>
					<option value="36">Telangana</option>
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
<footer>
		<img src="dist/img/footer.jpg" width="100%">
</footer>
<script src="dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<script type="text/javascript">
$("#mainheading").html(" PARTY MEETINGS DASHBOARD ");

$('input:checkbox').removeAttr('checked');

$(document).ready(function(e) {

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
	
	$('#locationLevelSelId').val('2').trigger('change');
	$('#stateId').val('1').trigger('change');
	setTimeout(function(){
	  $('#districtId').val(0);
	}, 2000);
	getMeetingSummary(2,0,1,2,0,0,0,0,0);//meetinglevel,typeOfMeeting,meetingduration,locationscope,stateId,distId,constId,mtdId,vwId
	getPartyMeetingDetails(2,0,1,2,0,0,0,0,0);
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
	   if(result[i].id == 0){
		  $("#districtId").append('<option value="">Select District</option>');
          $("#districtId").append('<option value='+result[i].id+'>ALL</option>');
	   }else{
	      $("#districtId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }
	 }
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
		$("#indiAjax").show();
		$("#individualResultBody").html("");
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
					//str+='<img src="./images/icons/search.gif" class="offset7"  id="indiAjax" style="width:20px;height:20px;display:none;"/>';
					str+='<tbody id="individualResultBody">';
					for(var i in pmList){
						str+="<tr>";
						str+="<td>"+pmList[i].meetingName+"</td>";
						str+="<td>"+pmList[i].location+"</td>";
						str+="<td>"+pmList[i].scheduledOn+"</td>";
						if(pmList[i].attendanceInfo!=null){
							str+="<td>"+pmList[i].attendanceInfo.totalInvitees+"</td>";
							str+="<td>"+pmList[i].attendanceInfo.totalAttended+"</td>";
							str+="<td>"+pmList[i].attendanceInfo.inviteesAttended+"</td>";
							str+="<td>"+pmList[i].attendanceInfo.nonInviteesAttended+"</td>";
							str+="<td>"+pmList[i].attendanceInfo.totalAbsent+"</td>";
						}else{
							str+="<td> - </td>";
							str+="<td> - </td>";
							str+="<td> - </td>";
							str+="<td> - </td>";
							str+="<td> - </td>";
						}
						
						if(pmList[i].docTxtInfo!=null){
							if(pmList[i].docTxtInfo.momFilesExist){
								str+="<td><i class='glyphicon glyphicon-ok text-success'>("+pmList[i].docTxtInfo.momFilesCount+")</i></td>";
							}else{
								str+="<td><i class='glyphicon glyphicon-remove text-danger'></i></td>";
							}
							
							if(pmList[i].docTxtInfo.momTextExist){
								str+="<td><i class='glyphicon glyphicon-ok text-success'>("+pmList[i].docTxtInfo.momPointsCount+")</i></td>";
							}else{
								str+="<td><i class='glyphicon glyphicon-remove text-danger'></i></td>";
							}
							
							if(pmList[i].docTxtInfo.atrFilesExist){
								str+="<td><i class='glyphicon glyphicon-ok text-success'>("+pmList[i].docTxtInfo.atrFilesCount+")</i></td>";
							}else{
								str+="<td><i class='glyphicon glyphicon-remove text-danger'></i></td>";
							}
							
							if(pmList[i].docTxtInfo.atrTextExist){
								str+="<td><i class='glyphicon glyphicon-ok text-success'>("+pmList[i].docTxtInfo.atrTextCount+")</i></td>";
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
				
				$('#individualTableId').dataTable();
				
			}else{
				$('.individual').show();
				$("#individualMeetingResultId").html("No Records Found"); 
			}
			$("#indiAjax").hide();
		});
	}
	
	function updateFunctions(){
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
		$(".tbtn").trigger( "click" );
		
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
		$("#cummAjax").show();
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
			$("#cummAjax").hide();
		});
	}
	
	function getPartyMeetingDetailsGroupLctn(meetinglevel,typeOfMeeting,meetingduration,locationscope,stateId,distId,constId,manTowDivId,wardPanId,groupingLocationType){
		$("#cummAjax").show();
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
			if(result!=null && result.partyMeetingsList!=null && result.partyMeetingsList.length>0){
				 $('.individual').show();
				var pmList = result.partyMeetingsList;
				var str = "";
				if(pmList!=null && pmList.length>0){
					for(var i in pmList){
						str+="<tr>";
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
					
				}
				$("#cumulativeMeetingTableBodyId").html(str); 
			}else{
				$("#cumulativeMeetingTableBodyId").html("No Records Found"); 
			}
			$("#cummAjax").hide();
		});
	}
	
	$(document).on('click','.grpLctn', function() {
		$('.grpLctn').not(this).prop('checked', false);
		
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
		if(level>=2){
			$("#grpLctnDistId").show();
			$("#grpLctnConstId").hide();
		}
		if(level>=3){
			$("#grpLctnConstId").show();
		}
	});
					
</script>
</body>
</html>