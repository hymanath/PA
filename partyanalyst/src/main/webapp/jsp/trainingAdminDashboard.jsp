<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Training Camp Main Dashboard</title>
	<link href="js/cadreCommittee/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<link href="css/Training/css/custom.css" rel="stylesheet" type="text/css"/>
	<link href="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
	<link href="js/cadreCommittee/dist/css/jquery.circliful.css" rel="stylesheet" type="text/css" />
	<link href="css/Training/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css"/>
	<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">

<!--<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/DateRange/daterangepicker-bs3.css" rel="stylesheet" type="text/css">
<link href="dist/css/jquery.circliful.css" rel="stylesheet" type="text/css">
<link href="dist/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">-->
</head>
<body>
<header>
	<img src="css/Training/img/header.jpg" width="100%">
</header>
<section>
	<div class="container">
    	<div class="row">
        	<div class="col-md-12">
            	<div class="section-container">
                    <div class="panel panel-default">
                        <div class="panel-heading bg_d">
                            <h4 class="panel-title">
                                TRAINING CAMP MAIN DASHBOARD
                                <div class="pull-right col-md-6 font-10">
                                	<div class="col-md-4">
                                        <label class="checkbox-inline">
                                          <input type="checkbox" class="m_top0" value="option1"> AP
                                        </label>
                                        <label class="checkbox-inline">
                                          <input type="checkbox" class="m_top0" value="option2"> TS
                                        </label>
                                        <label class="checkbox-inline">
                                          <input type="checkbox" class="m_top0" value="option3"> ALL
                                        </label>
                                    </div>
                                    <div class=" col-md-8">
                                        <div class="input-prepend input-group" style="margin-top:-8px;">
                                           <span class="add-on input-group-addon"><i class="glyphicon glyphicon-calendar fa fa-calendar"></i><span class="caret"></span></span><input type="text" name="reservation" id="CustomCalendar" class="form-control" /> 
                                        </div>
                                    </div>
                                </div>
                            </h4>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <table class="table table-bordered">
                                        <tr>
                                          <td>
                                                <span class="text-italic">Total </span><span class="font-30">01</span><br/>
                                                <span class="text-italic">Training Programs</span>
                                          </td>
                                          <td>
                                                <span class="text-italic">Total </span> <span class="font-30">04</span><br/>
                                                <span class="text-italic">Training Camps</span>
                                          </td>
                                          <td>
                                            <ul class="list-inline font-10">
                                                <li>
                                                    <span class="font-30">4000</span>
                                                    <p class="text-italic m_0">Total Members</p>
                                                </li>
                                                <li>
                                                    <p class="planned-batchs m_0">PLANNED</p>
                                                    <p class="running-batches m_0">RUNNING</p>
                                                    <p class="completed-batches m_0">COMPLETED</p>
                                                </li>
                                                <li>
                                                	<p class="planned-batchs m_0">- 09</p>
                                                    <p class="running-batches m_0">- 09</p>
                                                    <p class="completed-batches m_0">- 23</p>
                                                </li>
                                            </ul>
                                          </td>
                                          <td>
                                          	<table class="m_0">
                                            	<tr>
                                                	<td>
                                                    	<div id="donutchart" style="width:120px;height:80px;"></div></td>
                                                    <td>
                                                    	<h4 class="m_0">TOTAL TRAINING BATCHES - 40</h4>
                                                        <p class="m_0 font-10">
                                                            <span class="planned-batchs">PLANNED BATCHES - 09</span> //
                                                            <span class="running-batches">RUNNING - 09</span> //
                                                            <span class="completed-batches">COMPLETED - 23</span>
                                                        </p>
                                                    </td>
                                                </tr>
                                            </table>
                                          </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
							<div class="row">
								<div class="col-md-12">
                                    <div>
                                        <table class="table table-bordered">
                                        	<caption>ALL TRAINING PROGRAM WISE DETAILS</caption>
                                            <thead class="bg_d">
                                                <th>Training Program Name</th>
                                                <th>Training Campus Name</th>
                                                <th class="total-batches-td">Total <br/>Batches</th>
                                                <th class="planned-batches-td">Planned <br/> Batches</th>
                                                <th class="training-running-td">Training<br/> Running</th>
                                                <th class="completed-batches-td">Completed<br/> Batches</th>
                                                <th>Total <br/>Members</th>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td rowspan="4">Leadership Skills</td>
                                                    <td>SVV Batch Campus</td>
                                                    <td class="total-batches-td">12<p class="font-10 m_0">(1200 Members)</p></td>
                                                    <td class="planned-batches-td">12<p class="font-10 m_0">(1200 Members)</p></td>
                                                    <td class="training-running-td">12<p class="font-10 m_0">(1200 Members)</p></td>
                                                    <td>
                                                    	<ul class="list-inline">
                                                        	<li class="show-dropdown "><span class="completed-batches-td ">12<p class="font-10 m_0">(1200 Members)</p></span>
                                                                <ul class="count-hover up-arrow">
                                                                    <li>
                                                                    	<div class="count-hover-scroll">
                                                                        	<table class="table table-bordered m_bottom0">
                                                                            	<thead>
                                                                                	<th>BATCH NAME & DATE</th>
                                                                                    <th>TRAINED</th>
                                                                                    <th>BATCH RATING</th>
                                                                                    <th>TRAINER NAME & RATING</th>
                                                                                </thead>
                                                                                <tbody>
                                                                                	<tr>
                                                                                    	<td>
                                                                                        	EWK_Batch_201501
                                                                                            <p class="m_0 font-10">Date:24/07/2015-27/07/2015</p>
                                                                                        </td>
                                                                                        <td>100</td>
                                                                                        <td>
                                                                                            <i class="glyphicon glyphicon-star"></i>
                                                                                            <i class="glyphicon glyphicon-star"></i>
                                                                                            <i class="glyphicon glyphicon-star"></i>
                                                                                            <i class="glyphicon glyphicon-star"></i>
                                                                                            <i class="glyphicon glyphicon-star-empty"></i>
                                                                                        </td>
                                                                                        <td>
                                                                                        	Trainer Name
                                                                                            <div class="font-10">
                                                                                                <i class="glyphicon glyphicon-star"></i>
                                                                                                <i class="glyphicon glyphicon-star"></i>
                                                                                                <i class="glyphicon glyphicon-star"></i>
                                                                                                <i class="glyphicon glyphicon-star"></i>
                                                                                                <i class="glyphicon glyphicon-star-empty"></i>
                                                                                            </div>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                    	<td>
                                                                                        	EWK_Batch_201501
                                                                                            <p class="m_0 font-10">Date:24/07/2015-27/07/2015</p>
                                                                                        </td>
                                                                                        <td>100</td>
                                                                                        <td>
                                                                                            <i class="glyphicon glyphicon-star"></i>
                                                                                            <i class="glyphicon glyphicon-star"></i>
                                                                                            <i class="glyphicon glyphicon-star"></i>
                                                                                            <i class="glyphicon glyphicon-star"></i>
                                                                                            <i class="glyphicon glyphicon-star-empty"></i>
                                                                                        </td>
                                                                                        <td>
                                                                                        	Trainer Name
                                                                                            <div class="font-10">
                                                                                                <i class="glyphicon glyphicon-star"></i>
                                                                                                <i class="glyphicon glyphicon-star"></i>
                                                                                                <i class="glyphicon glyphicon-star"></i>
                                                                                                <i class="glyphicon glyphicon-star"></i>
                                                                                                <i class="glyphicon glyphicon-star-empty"></i>
                                                                                            </div>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                    	<td>
                                                                                        	EWK_Batch_201501
                                                                                            <p class="m_0 font-10">Date:24/07/2015-27/07/2015</p>
                                                                                        </td>
                                                                                        <td>100</td>
                                                                                        <td>
                                                                                            <i class="glyphicon glyphicon-star"></i>
                                                                                            <i class="glyphicon glyphicon-star"></i>
                                                                                            <i class="glyphicon glyphicon-star"></i>
                                                                                            <i class="glyphicon glyphicon-star"></i>
                                                                                            <i class="glyphicon glyphicon-star-empty"></i>
                                                                                        </td>
                                                                                        <td>
                                                                                        	Trainer Name
                                                                                            <div class="font-10">
                                                                                                <i class="glyphicon glyphicon-star"></i>
                                                                                                <i class="glyphicon glyphicon-star"></i>
                                                                                                <i class="glyphicon glyphicon-star"></i>
                                                                                                <i class="glyphicon glyphicon-star"></i>
                                                                                                <i class="glyphicon glyphicon-star-empty"></i>
                                                                                            </div>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                    	<td>
                                                                                        	EWK_Batch_201501
                                                                                            <p class="m_0 font-10">Date:24/07/2015-27/07/2015</p>
                                                                                        </td>
                                                                                        <td>100</td>
                                                                                        <td>
                                                                                            <i class="glyphicon glyphicon-star"></i>
                                                                                            <i class="glyphicon glyphicon-star"></i>
                                                                                            <i class="glyphicon glyphicon-star"></i>
                                                                                            <i class="glyphicon glyphicon-star"></i>
                                                                                            <i class="glyphicon glyphicon-star-empty"></i>
                                                                                        </td>
                                                                                        <td>
                                                                                        	Trainer Name
                                                                                            <div class="font-10">
                                                                                                <i class="glyphicon glyphicon-star"></i>
                                                                                                <i class="glyphicon glyphicon-star"></i>
                                                                                                <i class="glyphicon glyphicon-star"></i>
                                                                                                <i class="glyphicon glyphicon-star"></i>
                                                                                                <i class="glyphicon glyphicon-star-empty"></i>
                                                                                            </div>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                    	<td>
                                                                                        	EWK_Batch_201501
                                                                                            <p class="m_0 font-10">Date:24/07/2015-27/07/2015</p>
                                                                                        </td>
                                                                                        <td>100</td>
                                                                                        <td>
                                                                                            <i class="glyphicon glyphicon-star"></i>
                                                                                            <i class="glyphicon glyphicon-star"></i>
                                                                                            <i class="glyphicon glyphicon-star"></i>
                                                                                            <i class="glyphicon glyphicon-star"></i>
                                                                                            <i class="glyphicon glyphicon-star-empty"></i>
                                                                                        </td>
                                                                                        <td>
                                                                                        	Trainer Name
                                                                                            <div class="font-10">
                                                                                                <i class="glyphicon glyphicon-star"></i>
                                                                                                <i class="glyphicon glyphicon-star"></i>
                                                                                                <i class="glyphicon glyphicon-star"></i>
                                                                                                <i class="glyphicon glyphicon-star"></i>
                                                                                                <i class="glyphicon glyphicon-star-empty"></i>
                                                                                            </div>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                    	<td>
                                                                                        	EWK_Batch_201501
                                                                                            <p class="m_0 font-10">Date:24/07/2015-27/07/2015</p>
                                                                                        </td>
                                                                                        <td>100</td>
                                                                                        <td>
                                                                                            <i class="glyphicon glyphicon-star"></i>
                                                                                            <i class="glyphicon glyphicon-star"></i>
                                                                                            <i class="glyphicon glyphicon-star"></i>
                                                                                            <i class="glyphicon glyphicon-star"></i>
                                                                                            <i class="glyphicon glyphicon-star-empty"></i>
                                                                                        </td>
                                                                                        <td>
                                                                                        	Trainer Name
                                                                                            <div class="font-10">
                                                                                                <i class="glyphicon glyphicon-star"></i>
                                                                                                <i class="glyphicon glyphicon-star"></i>
                                                                                                <i class="glyphicon glyphicon-star"></i>
                                                                                                <i class="glyphicon glyphicon-star"></i>
                                                                                                <i class="glyphicon glyphicon-star-empty"></i>
                                                                                            </div>
                                                                                        </td>
                                                                                    </tr>
                                                                                </tbody>
                                                                            </table>
                                                                        </div>
                                                                    </li>
                                                                </ul>
															</li>
                                                        </ul>
                                                    </td>
                                                    <td>1200</td>
                                                </tr>
                                                <tr>
                                                    <td>EWK Batch Campus</td>
                                                    <td class="total-batches-td">12<p class="font-10 m_0">(1200 Members)</p></td>
                                                    <td class="planned-batches-td">12<p class="font-10 m_0">(1200 Members)</p></td>
                                                    <td class="training-running-td">12<p class="font-10 m_0">(1200 Members)</p></td>
                                                    <td class="completed-batches-td">12<p class="font-10 m_0">(1200 Members)</p></td>
                                                    <td>1200</td>
                                                </tr>
                                                <tr>
                                                    <td>GPN Batch Campus</td>
                                                    <td class="total-batches-td">12<p class="font-10 m_0">(1200 Members)</p></td>
                                                    <td class="planned-batches-td">12<p class="font-10 m_0">(1200 Members)</p></td>
                                                    <td class="training-running-td">12<p class="font-10 m_0">(1200 Members)</p></td>
                                                    <td class="completed-batches-td">12<p class="font-10 m_0">(1200 Members)</p></td>
                                                    <td>1200</td>
                                                </tr>
                                                <tr>
                                                    <td>AKKC Batch Campus</td>
                                                    <td class="total-batches-td">12<p class="font-10 m_0">(1200 Members)</p></td>
                                                    <td class="planned-batches-td">12<p class="font-10 m_0">(1200 Members)</p></td>
                                                    <td class="training-running-td">12<p class="font-10 m_0">(1200 Members)</p></td>
                                                    <td class="completed-batches-td">12<p class="font-10 m_0">(1200 Members)</p></td>
                                                    <td>1200</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
							<div class="row">
								<div class="col-md-12 m_top20">
                                    <div class="panel panel-default">
                                        <div class="panel-heading bg_d">
                                            <h4 class="panel-title">
                                                    PLANNED PROGRAM DETAILS
                                            </h4>
                                        </div>
                                        <div class="panel-body pad_0">
                                            <div class="panel-group m_0" id="accordion" role="tablist" aria-multiselectable="true">
                                              <div class="panel panel-default">
                                                <div class="panel-heading  bg_e9" role="tab" id="headingOne">
                                                  <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne" onclick="updateIconForReport('headingOne')">
                                                    <h4 class="panel-title text-bold">
                                                     Leadership Skills
                                                      <span class="pull-right">
                                                        <span class="text-warning font-10">180<span class="text-italic"> members ready for training b/w 01-15 dates</span></span>
                                                        <i class="glyphicon glyphicon-chevron-up"></i>
                                                      </span>
                                                    </h4>
                                                  </a>
                                                </div>
                                                <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                                                  <div class="panel-body  pad_5">
                                                    <table class="table table-bordered m_0">
                                                        <tr>
                                                            <td>BATCH NAME</td>
                                                            <td>MEMBERS ACCEPTED</td>
                                                            <td>TRAINING DATE</td>
                                                            <td>BATCH TRAINER</td>
                                                        </tr>
                                                        <tr>
                                                            <td>SVV_Batch_201503</td>
                                                            <td>100</td>
                                                            <td>Aug_2015_1 to 3</td>
                                                            <td>Trainer name</td>
                                                        </tr>
                                                        <tr>
                                                            <td>EWK_Batch_201503</td>
                                                            <td>100</td>
                                                            <td>Aug_2015_1 to 3</td>
                                                            <td>Trainer name</td>
                                                        </tr>
                                                        <tr>
                                                            <td>GPN_Batch_201503</td>
                                                            <td>100</td>
                                                            <td>Aug_2015_1 to 3</td>
                                                            <td>Trainer name</td>
                                                        </tr>
                                                    </table>
                                                  </div>
                                                </div>
                                              </div>
                                              <div class="panel panel-default">
                                                <div class="panel-heading bg_e9" role="tab" id="headingTwo">
                                                    <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo" onclick="updateIconForReport('headingTwo')">
                                                        <h4 class="panel-title text-bold">
                                                            Election Year
                                                          <span class="pull-right">
                                                            <span class="text-warning font-10">180<span class="text-italic"> members ready for training b/w 01-15 dates</span></span>
                                                            <i class="glyphicon glyphicon-chevron-up"></i>
                                                          </span>
                                                        </h4>
                                                    </a>
                                                </div>
                                                <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                                                  <div class="panel-body" style="padding-bottom:0px;">
                                                    
                                                  </div>
                                                </div>
                                              </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
							<div class="row">
								<div class="col-md-12">
                                	<div class="panel panel-default">
                                    	<div class="panel-heading">
                                        	<h4 class="panel-title">CURRENTLY RUNNING TRAINING PROGRAMS & BATCHES</h4>
                                        </div>
                                        <div class="panel-body pad_0">
                                        	<table class="table table-bordered m_0">
                                            	<tr>
                                                	<td rowspan="3">Leadership Skills</td>
                                                    <td>SVV Batch Campus</td>
                                                    <td>SVV_Batch_201501<p class="m_0 font-10 text-italic">Date:24/07/2015</p></td>
                                                    <td>100 Members Attend<span class="font-10 text-italic">Day-01(24/07/2015)</span></td>
                                                </tr>
                                                <tr>
                                                	<td rowspan="2">EWK Batch Campus</td>
                                                    <td>EWK_Batch_201501<p class="m_0 font-10 text-italic">Date:24/07/2015</p></td>
                                                    <td>100 Members Attend<span class="font-10 text-italic">Day-01(24/07/2015)</span></td>
                                                </tr>
                                                <tr>
                                                	<td>EWK_Batch_201501<p class="m_0 font-10 text-italic">Date:24/07/2015</p></td>
                                                    <td>100 Members Attend<span class="font-10 text-italic">Day-01(24/07/2015)</span></td>
                                                </tr>
                                                <tr>
                                                	<td>Leadership Skills</td>
                                                    <td>SVV Batch Campus</td>
                                                    <td>SVV_Batch_201501<p class="m_0 font-10 text-italic">Date:24/07/2015</p></td>
                                                    <td>100 Members Attend<span class="font-10 text-italic">Day-01(24/07/2015)</span></td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
							<div class="row">
								<div class="col-md-6">
                                	<div class="panel panel-default">
                                    	<div class="panel-heading">
                                        	<h4 class="panel-title">
                                            	PARTY DESIGNATION WISE MEMBERS PARTICIPATED IN TRAINING
                                            </h4>
                                        </div>
                                        <div class="panel-body pad_0">
                                        	<div>
                                            	<table class="table table-bordered m_0">
                                                	<tr>
                                                    	<td>VILLAGE LEVEL COMMITTEE MEMBERS</td>
                                                        <td>30 ATTEND</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>MANDAL LEVEL COMMITTEE MEMBERS</td>
                                                        <td>30 ATTEND</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>CONSTITUENCY LEVEL COMMITTEE MEMBERS</td>
                                                        <td>30 ATTEND</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>DISTRICT LEVEL COMMITTEE MEMBERS</td>
                                                        <td>30 ATTEND</td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                	<div class="panel panel-default">
                                    	<div class="panel-heading">
                                        	<h4 class="panel-title">
                                            	TRAINING RATINGS
                                            </h4>
                                        </div>
                                        <div class="panel-body pad_0">
                                        	<div class="table-scroll">
                                            	<table class="table table-bordered m_0">
                                                	<tr>
                                                    	<td>TRAINER NAME_1</td>
                                                        <td>
                                                        	<i class="glyphicon glyphicon-star"></i>
                                                            <i class="glyphicon glyphicon-star"></i>
                                                            <i class="glyphicon glyphicon-star"></i>
                                                            <i class="glyphicon glyphicon-star"></i>
                                                            <i class="glyphicon glyphicon-star-empty"></i>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                    	<td>TRAINER NAME_2</td>
                                                        <td>
                                                        	<i class="glyphicon glyphicon-star"></i>
                                                            <i class="glyphicon glyphicon-star"></i>
                                                            <i class="glyphicon glyphicon-star"></i>
                                                            <i class="glyphicon glyphicon-star"></i>
                                                            <i class="glyphicon glyphicon-star-empty"></i>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                    	<td>TRAINER NAME_3</td>
                                                        <td>
                                                        	<i class="glyphicon glyphicon-star"></i>
                                                            <i class="glyphicon glyphicon-star"></i>
                                                            <i class="glyphicon glyphicon-star"></i>
                                                            <i class="glyphicon glyphicon-star"></i>
                                                            <i class="glyphicon glyphicon-star-empty"></i>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                    	<td>TRAINER NAME_4</td>
                                                        <td>
                                                        	<i class="glyphicon glyphicon-star"></i>
                                                            <i class="glyphicon glyphicon-star"></i>
                                                            <i class="glyphicon glyphicon-star"></i>
                                                            <i class="glyphicon glyphicon-star"></i>
                                                            <i class="glyphicon glyphicon-star-empty"></i>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                    	<td>TRAINER NAME_4</td>
                                                        <td>
                                                        	<i class="glyphicon glyphicon-star"></i>
                                                            <i class="glyphicon glyphicon-star"></i>
                                                            <i class="glyphicon glyphicon-star"></i>
                                                            <i class="glyphicon glyphicon-star"></i>
                                                            <i class="glyphicon glyphicon-star-empty"></i>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
							<div class="row">
								<div class="col-md-6">
                                	<div class="panel panel-default">
                                    	<div class="panel-heading">
                                        	<h4 class="panel-title">
                                            	DISTRICT WISE TOTAL MEMBERS PARTICIPATED
                                            </h4>
                                        </div>
                                        <div class="panel-body pad_0">
                                        	<div>
                                            	<table class="table table-bordered m_0">
                                                	<thead>
                                                    	<th>DISTRICT NAME</th>
                                                        <th>INVITED</th>
                                                        <th>ATTEND</th>
                                                    </thead>
                                                	<tr>
                                                    	<td>Ananthapur</td>
                                                        <td>100</td>
                                                        <td>100</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>Chittoor</td>
                                                        <td>100</td>
                                                        <td>100</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>East Godavari</td>
                                                        <td>100</td>
                                                        <td>100</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>Guntur</td>
                                                        <td>100</td>
                                                        <td>100</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>Krishna</td>
                                                        <td>100</td>
                                                        <td>100</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>Kadapa</td>
                                                        <td>100</td>
                                                        <td>100</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>Kurnool</td>
                                                        <td>100</td>
                                                        <td>100</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>Prakasham</td>
                                                        <td>100</td>
                                                        <td>100</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>Nellore</td>
                                                        <td>100</td>
                                                        <td>100</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>Srikakulam</td>
                                                        <td>100</td>
                                                        <td>100</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>Visakhapatnam</td>
                                                        <td>100</td>
                                                        <td>100</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>Vizianagaram</td>
                                                        <td>100</td>
                                                        <td>100</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>West Godavari</td>
                                                        <td>100</td>
                                                        <td>100</td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                	<div class="panel panel-default">
                                    	<div class="panel-heading">
                                        	<h4 class="panel-title">
                                            	DISTRICT WISE TOTAL MEMBERS PARTICIPATED
                                            </h4>
                                        </div>
                                        <div class="panel-body pad_0">
                                        	<div>
                                            	<table class="table table-bordered m_0">
                                                	<thead>
                                                    	<th>CONSTITUENCY NAME</th>
                                                        <th>INVITED</th>
                                                        <th>ATTEND</th>
                                                    </thead>
                                                	<tr>
                                                    	<td>Kandukur</td>
                                                        <td>100</td>
                                                        <td>100</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>Kavali</td>
                                                        <td>100</td>
                                                        <td>100</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>Atmakur</td>
                                                        <td>100</td>
                                                        <td>100</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>Kovuru</td>
                                                        <td>100</td>
                                                        <td>100</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>Nellore</td>
                                                        <td>100</td>
                                                        <td>100</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>Udayanagaram</td>
                                                        <td>100</td>
                                                        <td>100</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>Kurnool</td>
                                                        <td>100</td>
                                                        <td>100</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>Prakasham</td>
                                                        <td>100</td>
                                                        <td>100</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>Nellore</td>
                                                        <td>100</td>
                                                        <td>100</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>Srikakulam</td>
                                                        <td>100</td>
                                                        <td>100</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>Visakhapatnam</td>
                                                        <td>100</td>
                                                        <td>100</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>Vizianagaram</td>
                                                        <td>100</td>
                                                        <td>100</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>West Godavari</td>
                                                        <td>100</td>
                                                        <td>100</td>
                                                    </tr>
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
</section>

<footer>
		<img src="css/Training/img/footer.jpg" width="100%">
</footer>
<script src="js/cadreCommittee/dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
  <script src="js/cadreCommittee/dist/js/bootstrap.min.js"></script>
  <script src="js/cadreCommittee/bootstrapDaterangepicker/moment.min.js" type="text/javascript"></script> 
<script src="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker.js" type="text/javascript"></script>
<script src="css/Training/scroll/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="css/Training/scroll/jquery.mousewheel.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/highcharts/js/highcharts_cadre.js"></script>
<script type="text/javascript">
function updateIconForReport(divId)
{
	var isAvailable = $('#'+divId+'').find('i').hasClass('glyphicon-chevron-up');
	
	if(isAvailable)
	{
		$('#'+divId+'').find('i').removeClass('glyphicon-chevron-up');
		$('#'+divId+'').find('i').addClass('glyphicon-chevron-down')
	}
	else
	{
		$('#'+divId+'').find('i').removeClass('glyphicon-chevron-down');
		$('#'+divId+'').find('i').addClass('glyphicon-chevron-up');
	}
}
$(document).ready(function() {
  $(".CustomCalendar").daterangepicker(null, function(start, end, label) {
	console.log(start.toISOString(), end.toISOString(), label);
  });
$(".count-hover-scroll").mCustomScrollbar({
	setHeight:140,
	theme:"minimal-dark"
});
$(".table-scroll").mCustomScrollbar({
	setHeight:149,
	theme:"minimal-dark"
});
});
</script>
<script type="text/javascript">
$(function () {
	Highcharts.setOptions({
        colors: ['#999966', '#669900', '#663300', '#ac69ae' , '#cccccc']
    });
    $('#donutchart').highcharts({
        chart: {
            type: 'pie',
			backgroundColor: 'transparent',
            options3d: {
                enabled: false,
                alpha: 50
            }
        },
		legend: {
                enabled: true,
                align: 'right',
                verticalAlign: 'right',
                floating: false,
                backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
                borderColor: '#CCC',
                borderWidth: 1,
                shadow: false
            },
        plotOptions: {
            pie: {
                innerSize: 70,
                depth: 10,
				dataLabels: {
                    enabled: false,
				}
            }, 
        },
		
		series: [{
            data: [
                ['Interested', 157],
                ['Not Interested', 100],
                ['Not Eligible', 100],
            ]
        }]
    });
});
</script>
</body>
</html>