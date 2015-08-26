<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Meeting Dashboard</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/DateRange/daterangepicker.css" rel="stylesheet" type="text/css">
<link href="dist/Icomoon/style.css" rel="stylesheet" type="text/css">
<link href="dist/Timepicker/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
<link href="dist/Dropzone/basic.css" rel="stylesheet" type="text/css">
<link href="dist/Dropzone/dropzone.css" rel="stylesheet" type="text/css">
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
</style>

</head>
<body>
<header>
	<img src="dist/img/header.jpg" width="100%">
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
															<td colspan="2">
																<h4 class="m_0">&nbsp;&nbsp;TOTAL INVITEES<br/><span id="totalInvitieesHead">0</span></h4>
															</td>
															<td>
																<h4 class="m_0">Attended<br/><span id="attendedHead">0</span></h4>
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
                                            	<select>
                                                	<option id="cumulative">Cumulative</option>
                                                    <option id="individual">Individual</option>
                                                </select>
                                            <div class="pull-right">
                                            	<label>GROUP BY : </label>
                                            	<label class="checkbox-inline"><input type="checkbox">State</label>
                                            	<label class="checkbox-inline"><input type="checkbox">District</label>
                                            	<label class="checkbox-inline"><input type="checkbox">Constituency</label>
                                            	<label class="checkbox-inline"><input type="checkbox">Mandal</label>
                                            </div>
                                        </div>
                                        <div class="panel-body pad_0">
                                        	<div class="table-responsive individual">
                                        	<table class="table table-bordered m_0">
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
                                                <tbody id="individualResultBody">
                                                	<tr>
                                                    	<td>District Committee Body Meeting</td>
                                                        <td>Srikakulam</td>
                                                        <td>Aug-15-2015</td>
                                                        <td>100</td>
                                                        <td>10<span class="font-10">(10%)</span></td>
                                                        <td>10<span class="font-10">(10%)</span></td>
                                                        <td>10<span class="font-10">(10%)</span></td>
                                                        <td>10<span class="font-10">(10%)</span></td>
                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                    </tr>
                                                	<tr>
                                                    	<td>District Committee Body Meeting</td>
                                                        <td>Srikakulam</td>
                                                        <td>Aug-15-2015</td>
                                                        <td>100</td>
                                                        <td>10<span class="font-10">(10%)</span></td>
                                                        <td>10<span class="font-10">(10%)</span></td>
                                                        <td>10<span class="font-10">(10%)</span></td>
                                                        <td>10<span class="font-10">(10%)</span></td>
                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                    </tr>
                                                	<tr>
                                                    	<td>District Committee Body Meeting</td>
                                                        <td>Srikakulam</td>
                                                        <td>Aug-15-2015</td>
                                                        <td>100</td>
                                                        <td>10<span class="font-10">(10%)</span></td>
                                                        <td>10<span class="font-10">(10%)</span></td>
                                                        <td>10<span class="font-10">(10%)</span></td>
                                                        <td>10<span class="font-10">(10%)</span></td>
                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                    </tr>
                                                	<tr>
                                                    	<td>District Committee Body Meeting</td>
                                                        <td>Srikakulam</td>
                                                        <td>Aug-15-2015</td>
                                                        <td>100</td>
                                                        <td>10<span class="font-10">(10%)</span></td>
                                                        <td>10<span class="font-10">(10%)</span></td>
                                                        <td>10<span class="font-10">(10%)</span></td>
                                                        <td>10<span class="font-10">(10%)</span></td>
                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                        <td><i class="glyphicon glyphicon-ok text-success"></i></td>
                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                    </tr>
                                                	<tr>
                                                    	<td>District Committee Body Meeting</td>
                                                        <td>Srikakulam</td>
                                                        <td>Aug-15-2015</td>
                                                        <td>100</td>
                                                        <td>10<span class="font-10">(10%)</span></td>
                                                        <td>10<span class="font-10">(10%)</span></td>
                                                        <td>10<span class="font-10">(10%)</span></td>
                                                        <td>10<span class="font-10">(10%)</span></td>
                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                        <td><i class="glyphicon glyphicon-remove text-danger"></i></td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                            <!-- </div>
                                            <div class="table-responsive cumulative">
                                        	<table class="table table-bordered m_0">
                                            	<thead>
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
                                            </div> -->
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
            <div class="col-md-10 col-md-offset-1" id="stateDiv">
                <label>Select State</label>
                <select class="form-control custom-select locationCls" id="stateId" onchange="getDistrictsForStates();">
                   	<option value="0">All</option>
					<option value="1">AndhraPradesh</option>
					<option value="36">Telangana</option>
                </select>
            </div>
            <div class="col-md-10 col-md-offset-1 " id="districtDiv">
                <label>Select District</label><img src='./images/icons/search.gif' class="offset7"  id="imgForDist" style="width:15px;height:15px;display:none;"/>
                <select class="form-control custom-select locationCls" id="districtId" onchange="getConstituenciesForDistricts(this.value);">
                   
                </select>
            </div>
            <div class="col-md-10 col-md-offset-1" id="constituencyDiv">
                <label>Select Constituency</label><img src='./images/icons/search.gif' class="offset7"  id="imgForConsti" style="width:15px;height:15px;display:none;"/>
                <select class="form-control custom-select locationCls" id="constituencyId" onchange="getMandalCorporationsByConstituency();">
                    <option>Select Constituency</option>
                </select>
            </div>
			
			<div class="col-md-10 col-md-offset-1" id="mandalDiv">
                <label>Mandal</label><img src='./images/icons/search.gif' class="offset7"  id="imgForMandl" style="width:15px;height:15px;display:none;"/>
                <select class="form-control custom-select locationCls" id="mandalId" onchange="getPanchayatWardByMandal();">
                   <option>Select Mandal</option>
                </select>
            </div>
			<div class="col-md-10 col-md-offset-1" id="panchayatDiv">
                <label>Panchayat</label><img src='./images/icons/search.gif' class="offset7"  id="imgForPanc" style="width:15px;height:15px;display:none;"/>
                <select class="form-control custom-select locationCls" id="panchayatId">
                   <option>Select Panchayat</option>
                </select>
            </div>
			
           
            <div class="col-md-10 col-md-offset-1 m_top20" style="margin-bottom:10px;">
			  <button class="btn btn-block btn-success btn-sm btn-custom" onClick="getMeetingSummary();">UPDATE</button>
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
<script type="text/javascript">
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
          $("#districtId").append('<option value='+result[i].id+'>ALL</option>');
	   }else{
	      $("#districtId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }
	 }
   });
  }
  function showHide()
  {

   var stateId = $("#stateId").val();
   var districtId = $("#districtId").val();
   var constituencyId = $("#constituencyId").val();
   var mandalId = $("#mandalId").val();
  
   if(stateId == 0)
   {
	$("#districtDiv").hide();
	$("#constituencyDiv").hide();
	$("#mandalDiv").hide();
	$("#panchayatDiv").hide();
  }
   else if(districtId == 0)
   {
	$("#districtDiv").show();
	$("#constituencyDiv").hide();
	$("#mandalDiv").hide();
	$("#panchayatDiv").hide();
   }
    else if(constituencyId == 0)
   {
	$("#districtDiv").show();
	$("#constituencyDiv").show();
	$("#mandalDiv").hide();
	$("#panchayatDiv").hide();
   }
    else if(mandalId == 0)
   {
	$("#districtDiv").show();
	$("#constituencyDiv").show();
	$("#mandalDiv").show();
	$("#panchayatDiv").hide();
   }
   else{
	$("#districtDiv").show();
	$("#constituencyDiv").show();
	$("#mandalDiv").show();
	$("#panchayatDiv").show();
   }
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
			$("#mandalId").append('<option value="0">All</option>');
			
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
				{
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
			$("#panchayatId").append('<option value="0">Select Panchayat</option>');
			
			var jsObj={
				mandalId:mandalId
			}
			$.ajax({
				type : "POST",
				url : "getPanchayatWardByMandalAction.action",
				data : {task:JSON.stringify(jsObj)} 
			}).done(function(result){
				$("#imgForPanc").hide();
			for(var i in result){
				$("#panchayatId").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
			}
		});	
	}
  $(".locationCls").change(function() {
  showHide();
  });
  
  getPartyMeetingDetails();
	function getPartyMeetingDetails(){
		var locationLevelValues = [11,12,13,14,15,16,17,18,19,20,21,22,23,517];
		var locationLevel= 2;
		var jsObj =	{
			locationLevel:locationLevel,
			locationLevelValues:locationLevelValues,
			startDate:"07/28/2015",
			endDate:"08/28/2015"
		}
					
		$.ajax({
			type: "POST",
			url:"getMeetingSummaryForLocationAction.action",
			data:{task :JSON.stringify(jsObj)}
		}).done(function(result){
			$('.individual').show();
			var pmList = result.partyMeetingsList;
			
			$("#ttlDistMtngsSpanId").html(pmList[0].meetingsCount);
			$("#ttlPlnndMtngsSpanId").html(pmList[0].plannedMeetings);
			$("#ttlCndctdMtngsSpanId").html(pmList[0].conductedMeetings+" ("+pmList[0].conductedMeetingsPercent+" %)");
			$("#avgAttndInvtsSpanId").html(pmList[0].averageInviteesAttended);
			
			var str = "";
			if(pmList!=null && pmList.length>0){
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
							str+="<td><i class='glyphicon glyphicon-ok text-success'></i></td>";
						}else{
							str+="<td><i class='glyphicon glyphicon-remove text-danger'></i></td>";
						}
						
						if(pmList[i].docTxtInfo.momTextExist){
							str+="<td><i class='glyphicon glyphicon-ok text-success'></i></td>";
						}else{
							str+="<td><i class='glyphicon glyphicon-remove text-danger'></i></td>";
						}
						
						if(pmList[i].docTxtInfo.atrFilesExist){
							str+="<td><i class='glyphicon glyphicon-ok text-success'></i></td>";
						}else{
							str+="<td><i class='glyphicon glyphicon-remove text-danger'></i></td>";
						}
						
						if(pmList[i].docTxtInfo.atrTextExist){
							str+="<td><i class='glyphicon glyphicon-ok text-success'></i></td>";
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
				
			}
			$("#individualResultBody").html(str);
		});
	}
	
	function getMeetingSummary()
	{	
		var dateType=$("#meetingDuration").val();
		var fromDate,temp1=new Date(),toDate;
		fromDate = temp1.getFullYear()+"/"+(temp1.getMonth()+1)+"/"+temp1.getDate()
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
		toDate = temp.getFullYear()+"/"+(temp.getMonth()+1)+"/"+temp.getDate();
		
		var meetingLevel = $("#meetingLevel").val();
		var typeOfMeeting = $("#typeOfMeeting").val();
		
		var jsObj ={
				meetingLevel:meetingLevel,
				typeOfMeeting:typeOfMeeting,
				fromDate:fromDate,
				toDate:toDate
			};
		 $.ajax({
			type : "GET",
			url : "getMeetingSummaryAction.action",
			data : {task:JSON.stringify(jsObj)} 
		}).done(function(result){
			if(result!=null){
				
				$("#totalInvitieesHead").html(result.totalInvitees);
				$("#attendedHead").html(result.totalAttended);
				$("#totalAbsentHead").html(result.totalAbsent);
				
				var str='';
				str+='<tr class="font-12">';
				str+='<td  width="80px"></td>';
				str+='<td  width="80px">Total Invitees</td>';
				str+='<td width="140px">ATTENDED</td>';
				str+='<td>Total Non Invitees</td>';
				str+='<td>Total Invitees Absent</td>';
				str+='</tr>';
				str+='<tr>';
				str+='<td>PARTY ROLE</td>';
				str+='<td>'+result.totalCommitteeMemberInvitees+'</td>';
				str+='<td>'+result.totalCommitteeMemberAttended+'</td>';
				str+='<td>'+result.totalCommitteeMemberNonInvitees+'</td>';
				str+='<td>'+result.totalCommitteeMemberAbsent+'</td>';
				str+='</tr>';
				str+='<tr>';
				str+='<td>PUBLIC REPRESENTATIVE</td>';
				str+='<td>'+result.totalCandidateInvitees+'</td>';
				str+='<td>'+result.totalCandidateAttended+'</td>';
				str+='<td>'+result.totalCandidateNonInvitees+'</td>';
				str+='<td>'+result.totalCandidateAbsent+'</td>';
				str+='</tr>';
				str+='<tr>';
				str+='<td>NO ROLE</td>';
				str+='<td>'+result.totalNoRoleInvitees+'</td>';
				str+='<td>'+result.totalNoRoleAttended+'</td>';
				str+='<td>'+result.totalNoRoleNonInvitees+'</td>';
				str+='<td>'+result.totalNoRoleAbsent+'</td>';
				str+='</tr>';
				
				$("#meetingSummaryBodyId").html(str);
			}
		});
	}
					
</script>
<script>
showHide();
getMeetingSummary();
</script>
</body>
</html>