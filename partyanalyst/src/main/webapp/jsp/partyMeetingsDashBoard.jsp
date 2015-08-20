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
                                                	<tr style="background-color:#F4F2E6">
                                                    	<td colspan="2">
                                                        	<h3 class="m_0">TOTAL INVITEES - 800</h3>
                                                        </td>
                                                        <td colspan="2">
                                                        	<h3 class="m_0">TOTAL ABSENT - 50</h3>
                                                        </td>
                                                    </tr>
                                                    <tr class="font-12">
                                                    	<td></td>
                                                    	<td>Total<br/> Invitees</td>
                                                        <td>Total<br/>Non Invitees</td>
                                                        <td>Total Invitees<br/> Absent</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>PARTY ROLE</td>
                                                        <td>50</td>
                                                        <td>50</td>
                                                        <td>50</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>PUBLIC <br/>REPRESENTATIVE</td>
                                                        <td>50</td>
                                                        <td>50</td>
                                                        <td>50</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>NO ROLE</td>
                                                        <td>50</td>
                                                        <td>50</td>
                                                        <td>50</td>
                                                    </tr>
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
                                                	TOTAL DISTRICT COMMITTEES<span class="pull-right">10</span>
                                                </h4></td>
                                            </tr>
                                            <tr>
                                                <td><h4 class="m_top20">
                                                NO OF PLANNED MEETINGS<span class="pull-right">10</span>
                                                </h4></td>
                                            </tr>
                                            <tr>
                                                <td><h4 class="m_top20">
                                                	TOTAL NO OF MEETINGS CONDUCTED<span class="pull-right">10(10%)</span>
                                                </h4></td>
                                            </tr>
                                            <tr>
                                                <td><h4 class="m_top20">
                                                	AVERAGE ATTENDANCE OF INVITEES<span class="pull-right">10</span>
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
                                                <tbody>
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
                                            </div>
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
                                            </div>
                                            <div class="checkbox-select">
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
    </div>
</main>
<div class="themeControll">

  <div class="linkinner">
  		<div class="row">
        	<div class="col-md-10 col-md-offset-1 m_top10">
                <label>Meeting Level</label>
                <select class="form-control custom-select">
                    <option>Village</option>
                </select>
            </div>
            <div class="col-md-10 col-md-offset-1">
                <label>Type Of Meeting</label>
                <select class="form-control custom-select">
                    <option>General Body Meeting</option>
                </select>
            </div>
            <div class="col-md-10 col-md-offset-1">
                <label>Meeting Duration</label>
                <select class="form-control custom-select">
                	<option>Last 1 Months</option>
                    <option>Last 3 Months</option>
                    <option>Last 6 Months</option>
                    <option>Last 9 Months</option>
                </select>
            </div>
            <div class="col-md-10 col-md-offset-1" id="stateDiv">
                <label>Select State</label>
                <select class="form-control custom-select" id="stateId" onchange="getDistrictsForStates();showHide();">
                   	<option value="0">All</option>
					<option value="1">AndhraPradesh</option>
					<option value="36">Telangana</option>
                </select>
            </div>
            <div class="col-md-10 col-md-offset-1" id="districtDiv">
                <label>Select District</label>
                <select class="form-control custom-select" id="districtId">
                    <option>Visakapatnam</option>
                </select>
            </div>
            <div class="col-md-10 col-md-offset-1" id="constituencyDiv">
                <label>Select Constituency</label>
                <select class="form-control custom-select">
                    <option>Bhimli</option>
                </select>
            </div>
            <div class="col-md-10 col-md-offset-1" id="mandalDiv">
                <label>Mandal</label>
                <select class="form-control custom-select">
                    <option>Bobbili</option>
                </select>
            </div>
            <div class="col-md-10 col-md-offset-1">
                <label>Level</label>
                <select class="form-control custom-select">
                    <option>All</option>
                </select>
            </div>
            <div class="col-md-10 col-md-offset-1 m_top20" style="margin-bottom:10px;">
			  <button class="btn btn-block btn-success btn-sm btn-custom">UPDATE</button>
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
   if(stateId == 0)
   {
    $("#districtDiv").hide();
	$("#constituencyDiv").hide();
	$("#mandalDiv").hide();
   }
   if(districtId == 0)
   {
	$("#constituencyDiv").hide();
	$("#mandalDiv").hide();
   }
   else{
   $("#districtDiv").show();
	$("#constituencyDiv").show();
	$("#mandalDiv").show();
   }
  }
</script>
</body>
</html>