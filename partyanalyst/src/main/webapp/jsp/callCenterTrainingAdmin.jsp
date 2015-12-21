<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Call Center Training Admin </title>
<!-- Bootstrap -->
    <link href="js/cadreCommittee/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<link href="css/Training/css/custom.css" rel="stylesheet" type="text/css"/>
	<link href="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
	<link href="js/cadreCommittee/dist/css/jquery.circliful.css" rel="stylesheet" type="text/css" />
	<link href="css/Training/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css"/>


</head>


<style type="text/css">
.extra-ul
{
	padding:0px;
	border:1px solid #ddd;
}
.extra-ul li
{
	display:inline-block;
	list-style:none;
}
.count-hover
{
	margin-top:0px;
	width:330px;
	height:200px;
	overflow:auto;
}
footer
{
	padding:30px;
	background-color:#5c2d25;
	color:#fff;
}
.padding_left30
{
	padding-left:30px;
}
.border_0
{
	border:0px !important;
	box-shadow:none;
}
.border_0 .panel-heading
{
	padding:0px;
}

.accordion-toggle:before {
    /* symbol for "opening" panels */
    font-family:'Glyphicons Halflings';
    content:"\2212";
    float: left;
    color: inherit;
	margin-top:3px;
	margin-right:3px;
	font-size:9px;
}
.accordion-toggle.collapsed:before {
    /* symbol for "collapsed" panels */
    content:"\2b";
}

.panel-group .panel-default .panel-heading
{
	background-color:#fff;
}
.panel-group , ul
{
	margin-bottom:0px;
}

    header.eventsheader {  
    background:url("dist/img/header-footer.png") no-repeat scroll center bottom / 100% auto  #fed501;
    background-origin: border-box;
    background-repeat: no-repeat;
    height: 71px;   
}
.circle-text, .circle-info, .circle-text-half, .circle-info-half 
{
	font-size:22px;
	top:35%;
	left:0;
}
/*.invited{background-color:rgb(238,130,238);}
.pending{background-color:rgb(255,0,0);}
.interested{background-color:rgb(64,181,191);}
.confirmed{background-color:rgb(0,255,0);}*/

.invited-text{color:rgb(238,130,238) !important;}
.pending-text{color:rgb(255,0,0) !important;}
.interested-text{color:rgb(64,181,191) !important;}
.confirmed-text{color:rgb(0,255,0) !important;}
.successDivCls{color:green;margin-bottom:10px;margin-left:10px;}
#callCenterErrorDiv{color: red;
    font-size: 13px;
    margin-bottom: 5px;
    margin-left: 15px;}
#processingImg{clear: both; margin: -26px -25px 0px 0px; float: right;}
#batchProcessingImg{clear: both; margin: -26px -15px 0px 0px; float: right;}
.errorCls{color: red; font-size: 13px; margin-left: 14px;}
#agentProcessingImg,#trainingBatchProcessImg{margin-left: 12px;}
</style>
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
					  <li><a href="dashBoardAction.action"><span>DASHBOARD</span></a> </li>
							<c:if test="${fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_SUPER_ADMIN')}">
								
								<li><a tabindex="-1" href="callCenterTrainingAgentDashBoard.action"> CALLERS DASHBOARD </a></li>
								<li><a tabindex="-1" href="trainingCenterDashBoardAction.action"> TRAINING CAMP DASHBOARD </a></li>
								<li><a tabindex="-1" href="trainingCampMainDashboard.action"> TRAINING CAMP FEEDBACK </a></li>
						    </c:if>
							<c:if test="${fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_ADMIN')}">
								<li><a tabindex="-1" href="trainingCenterDashBoardAction.action"> TRAINING CAMP DASHBOARD </a></li>
						    </c:if>
							<c:if test="${fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_CALLER_ADMIN')}">
							<li><a tabindex="-1" href="callCenterTrainingAgentDashBoard.action"> CALLERS DASHBOARD </a></li>
						    </c:if>
						
							<c:if test="${fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_FEEDBACK_UPDATE_ENTITLEMENT')}">
								<li><a tabindex="-1" href="trainingCampMainDashboard.action"> TRAINING CAMP FEEDBACK </a></li>
						    </c:if>
							<c:if test="${fn:contains(sessionScope.USER.entitlements, 'TDP_COMMITTEE_AREAWISE_ACCESS')}">
								<li><a href="committeeDashBoardAction.action"><span>COMMITTEES DASHBOARD</span></a> </li>
								<li><a href="meetingList.action"><span>Party Meeting ATR & MOM</span></a> </li>
							</c:if>
					   <li><a tabindex="-1" href="newlogoutAction.action">Sign Out</a></li>
					
                    </ul>   
            </div>			
        </div>       
    </div>
	

	
</header>-->
<section>
	
	<div class="container">
		<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default panel-custom">
				<div class="panel-heading">
					<h4 class="panel-title"><b>CALL CENTER ADMIN DASHBOARD</b>
						<%-- <span class="pull-right col-md-3" style="margin-top:-8px">
							<div class="input-group">
								<span class="input-group-addon">
									<i class="glyphicon glyphicon-calendar"></i>
									<span class="caret"></span>
								</span>
								<input type="text" class="form-control" id="reportrange">
							</div>
						</span> --%>
                    <!--	<span class="pull-right">
                        	<label class="checkbox-inline">
                            	<input type="checkbox"> Planned
                            </label>
                            <label class="checkbox-inline">
                            	<input type="checkbox">All
                            </label>
                        </span> -->
					</h4>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-8">
							<ul class="extra-ul">
                            	<li style="width:150px;">
									<center><img id="dataLoadingsImgForDonutchartStatus" src="images/icons/loading.gif" style="width: 40px; height: 40px;margin-top:50px;"/></center>
									<div id="donutchartForStatus" class="display-style" style="height: 160px;float:left;width:175px; margin-top:20px;margin-left:-10px"></div>
                                </li>
                                <li style="width:550px">
                                	<table class="table table-bordered m_0">
                                    	<tr>
                                        	<td colspan="2" class="pad_15">
                                                <h4 class="m_0">TOTAL CADRE ALLOCATED TO ADMIN - <span id="totalCallsPerCallerId">
													<img id="dataLoadingsImgForTotalCallerCount" src="images/icons/loading.gif" style="width: 15px; height: 15px;"/>
													</span>
												</h4>
                                            </td>
                                        </tr>
										<tr>
											<td >
											<span class="pull-right font-12" style="margin-right:55px;">TODAY CADRE ALLOCATED TO ADMIN- <span id="todayCallsPerCallerId">
														<img id="dataLoadingsImgForTodayCount" src="images/icons/loading.gif" style="width: 10px; height: 10px;"/>
														</span></span>
											</td>
											<td>
														<span class="pull-right font-12" style="margin-right:55px;">ASSIGNED TO AGENTS - <span id="assignedToAgentsId">
														<img id="dataLoadingsImgForAssigning" src="images/icons/loading.gif" style="width: 10px; height: 10px;"/>
												</span></span>
											</td>
										</tr>
                                    </table>
                                	<div id="adminAssignedTdId" class="table-responsive">
										
									</div>                             
                                </li>
                            </ul>
						</div>
						
												
						<div class="col-md-4">
							<table class="table table-bordered table-condensed" style="margin-bottom:12px">
								<tr>
									<td style="text-align:center;" >
										<ul class="list-inline m_bottom0">
											<li><h4>TRAINING <br/>PROGRAMS</h4></li>
											<li class="show-dropdown">
												<div><h1 class="m_0"><span id="totalProgramsCountId">
												<img id="dataLoadingsImgFortotalProgramsCountId" src="images/icons/loading.gif" style="width: 15px; height: 15px;"/>
												</span></h1></div>
												<ul class="count-hover up-arrow">
													<li>
														<div class="count-hover-scroll" id="batchCountOfProgramId">
														
															<center><img id="dataLoadingsImgForBatchOfProgramId" src="images/icons/loading.gif" style="width: 30px; height: 30px;margin-top:30px;"/></center>
														
														</div>
													</li>
												</ul>
											</li>
										</ul>
									</td>
									<td style="text-align:center;" >
										<ul class="list-inline m_bottom0">
											<li><h4>TRAINING <br/>CENTRES</h4></li>
											<li class="show-dropdown">
												<div><h1 class="m_0"><span id="totalCampsCountId">
												<img id="dataLoadingsImgFortotalCampsCountId" src="images/icons/loading.gif" style="width: 15px; height: 15px;"/>
												</span></h1></div>
												<ul class="count-hover up-arrow">
													<li>
													<div class="count-hover-scroll1" id="batchCountOfCampId"> 
														
													</div>
													</li>
												</ul>
											</li>
										</ul>
									</td>
								</tr>
								
							</table>
							<table class="table table-bordered table-condensed" style="margin-bottom:12px">
								<tr>
									<td rowspan="2">UPCOMING SCHEDULE<br/><p class="text-center"><span id="upcmngSchCountId">0</span></p></td>
									<td>Allocated to Agents <span class="pull-right" id="upAlctId">0</span></td>
								</tr>
								<tr>
									<td>Not Allocated<span class="pull-right" id="upNtAlctId">0</span></td>
								</tr>
							</table>
							<table class="table table-bordered">
								<tr>
									<td rowspan="2">UPCOMING BATCHES FOR <br/>BATCH CONFIRMATION  <br/><p class="text-center"><span id="btchConfId">0</span></p></td>
									<td>Allocated to Agents <span class="pull-right" id="btchAlcId">0</span></td>
								</tr>
								<tr>
									<td>Not Allocated<span class="pull-right" id="btchNtalctedId">0</span></td>
								</tr>
							</table>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title"><b>CALENDAR SCHEDULED CONFIRMATION DETAILS</b>
										<button class="btn btn-success btn-xs pull-right" style="margin-top:-7px"
										data-toggle="modal" data-target="#myModal" onclick="clearAssignAgent();getPrograms('schedule');">Assign to Agents</button>
										
									</h4>
								</div>
								<div class="panel-body pad_0">
									<div class="row">
										<div class="col-md-12">
											<div class="panel panel-default">
												<div class="panel-heading pad_5 pad_bottom0">
													<ul class="nav nav-tabs tab-list-sch searchTypeCls" role="tablist">
														<li class="active"><a href="#scheduled" class="text-bold" data-toggle="tab">UPCOMING SCHEDULE</a></li>
														<li><a href="#running" class="text-bold" data-toggle="tab">RUNNING SCHEDULE</a></li>
														<li><a href="#completed" class="text-bold" data-toggle="tab">COMPLETED SCHEDULE</a></li>
														<li><a href="#cancelled" class="text-bold" data-toggle="tab">CANCELLED SCHEDULE</a></li>
													   
													</ul>
												</div>
												<div class="panel-body pad_0">
													<div>
													  <!-- Tab panes -->
													  <div class="tab-content">
													 <center><img id="dataLoadingsImgForCalenderScheduleId" src="images/icons/loading.gif" style="width: 30px; height: 30px;margin-top:30px;"/></center>
														<div role="tabpanel" class="tab-pane active table-responsive" id="scheduled" ></div>									
														<div role="tabpanel" class="tab-pane table-responsive" id="running" style="height:450px;"> </div>
														<div role="tabpanel" class="tab-pane table-responsive" id="completed" style="height:450px;">   </div>
														<div role="tabpanel" class="tab-pane table-responsive" id="cancelled" style="height:450px;"></div>
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
					<div class="row">
						<div class="col-md-12">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<b>BATCH CONFIRMATION DETAILS</b>
										<button class="btn btn-success btn-xs pull-right" style="margin-top:-7px"
										data-toggle="modal" data-target="#myModal1" onclick="clearBatchPopupFields();getPrograms('batch');";">Assign to Agents</button>
									</h4>
								</div>
								<div role="tabpanel" class="panel-body pad_0 batchConforCls table-responsive" style="height:500px;">
								<!--	<table class="table table-bordered m_0">
										<tr>
											<td>TRAINING <br/>PROGRAM NAME</td>
											<td>TRAINING CENTER</td>
											<td>TRAINING <br/>START DATE</td>
											<td>TRAINING <br/>END DATE</td>
											<td>ALLOCATED <br/>CALLS</td>
											<td>DIALED/<br/> UNDIALED</td>
											<td class="font-12">CALL BACK/<br/>USER BUSY/<br/>OTHERS</td>
											<td>INTERESTED <br/>MEMBERS</td>
											<td>LATER</td>
											<td>NOT <br/>INTERESTED</td>
										</tr>
										<tr>
											<td rowspan="4">Leadership Skills</td>
											<td rowspan="2">SVV Center</td>
											<td>Sep_01_2015</td>
											<td>Sep_03_2015</td>
											<td>100</td>
											<td>95/05</td>
											<td>05</td>
											<td>90</td>
											<td>02</td>
											<td>03</td>
										</tr>
										<tr>
											<td>Sep_01_2015</td>
											<td>Sep_03_2015</td>
											<td>100</td>
											<td>95/05</td>
											<td>05</td>
											<td>90</td>
											<td>02</td>
											<td>03</td>
										</tr>
										<tr>
											<td>AKKC Center</td>
											<td>Sep_01_2015</td>
											<td>Sep_03_2015</td>
											<td>100</td>
											<td>95/05</td>
											<td>05</td>
											<td>90</td>
											<td>02</td>
											<td>03</td>
										</tr>
										<tr>
											<td>GPN Center</td>
											<td>Sep_01_2015</td>
											<td>Sep_03_2015</td>
											<td>100</td>
											<td>95/05</td>
											<td>05</td>
											<td>90</td>
											<td>02</td>
											<td>03</td>
										</tr>
									</table>-->
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 col-xs-12 m_top20">
							<div class="panel panel-default">
								<div class="panel-heading pad_5 pad_bottom0">
									<ul class="nav nav-tabs tab-list" role="tablist">
										<li class="active"><a href="#area" class="text-bold" data-toggle="tab" onclick="getCallerWiseCallsDetails('Invitation')">SCHEDULE CONFIRMATION CALLS AGENT WISE</a></li>
										<li><a href="#participated" class="text-bold scheduled" data-toggle="tab" onclick="getCallerWiseCallsDetails('Confirmation')">BATCH CONFIRMATION CALLS AGENT WISE</a></li>
									</ul>
								</div>
								<div class="panel-body pad_0">
									<div>
									  <div class="tab-content">
										<div role="tabpanel" class="tab-pane active memberAvailabilityDivCls table-responsive" id="area">
										<center><img id="dataLoadingsImgForProgressOfAgentCountId" src="images/icons/loading.gif" style="width: 30px; height: 30px;margin-top:30px;"/></center>
											<!--<table class="table table-bordered m_0">
												<thead>
													<th>Agent Name</th>
													<th>Assigned Calls</th>
													<th>Completed Calls</th>
													<th>Pending Calls</th>
													<th class="font-12">
                                                    	Scheduled Camp <br/>Interested Members
                                                        
                                                    </th>
													<th>Interested Later</th>
													<th>Not interested</th>
												</thead>
												<tbody>
													<tr>
														<td style="text-align:center;" >Agent 1	</td>
														<td style="text-align:center;" >100</td>
														<td style="text-align:center;" >80</td>
														<td style="text-align:center;" >20</td>
														<td style="text-align:center;" >70</td>
														<td style="text-align:center;" >20</td>
														<td style="text-align:center;" >70</td>
													</tr>
													<tr>
														<td style="text-align:center;" >Agent 2</td>
														<td style="text-align:center;" >100</td>
														<td style="text-align:center;" >80</td>
														<td style="text-align:center;" >20</td>
														<td style="text-align:center;" >70</td>
														<td style="text-align:center;" >20</td>
														<td style="text-align:center;" >70</td>
													</tr>
													<tr>
														<td style="text-align:center;" >Agent 3</td>
														<td style="text-align:center;" >100</td>
														<td style="text-align:center;" >80</td>
														<td style="text-align:center;" >20</td>
														<td style="text-align:center;" >70</td>
														<td style="text-align:center;" >20</td>
														<td style="text-align:center;" >70</td>
													</tr>
													<tr>
														<td style="text-align:center;" >Agent 4</td>
														<td style="text-align:center;" >100</td>
														<td style="text-align:center;" >80</td>
														<td style="text-align:center;" >20</td>
														<td style="text-align:center;" >70</td>
														<td style="text-align:center;" >20</td>
														<td style="text-align:center;" >70</td>
													</tr>
													<tr>
														<td style="text-align:center;" >Agent 5</td>
														<td style="text-align:center;" >100</td>
														<td style="text-align:center;" >80</td>
														<td style="text-align:center;" >20</td>
														<td style="text-align:center;" >70</td>
														<td style="text-align:center;" >20</td>
														<td style="text-align:center;" >70</td>
													</tr>
												</tbody>
											</table>-->
										</div>
										<div role="tabpanel" class="tab-pane confirmationMemberAvailabilityDivCls table-responsive" id="participated">
											<center><img id="dataLoadingsImgForProgressOfConfirmationId" src="images/icons/loading.gif" style="width: 30px; height: 30px;margin-top:30px;"/></center>
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
	<!-- batch -->
	<!---Modal-1----------------->
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content" style="width: 600px;">
      <div class="modal-header  bg_d">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h5 class="modal-title text-uppercase">Assign Members to Batch Confirmation</h5>
      </div>
      <div class="modal-body">
      		<div class="row">
			 <div id="batchErrorDiv"></div>
			 <div class="col-md-12 m_top10">
					<label>Select Program Name</label>
					<select class="form-control border-radius-0" id="batchProgramId"  onchange="getCampsForProgram('batch');">
					 <option value="0">Select Program</option>
					</select>
				</div> 
				<div class="col-md-12 m_top5">
					<label>Select Center</label>
					<select class="form-control border-radius-0" id="batchCampId" onchange="getSchedulesForCamp('batch');">
						 <option value="0">Select Camp</option>
					</select>
				</div>   
				  
				<div class="col-md-12 m_top10">
					<label>Select Calender Scheduled Dates</label>
					<select class="form-control border-radius-0" id="batchScheduleId" onchange="getBatchesForSchedule();">
					 <option value="0">Select Schedule</option>		
					</select>
					<!--comment<small class="help-block pull-right" style="color:#996633;  margin-bottom: 0px;"><i>Avail Calls - 220</i></small>-->
				</div>  
				<div class="col-md-12 m_top10">
					<label>Select Training Batch / Date</label>
					<img src="images/icons/search.gif" style="display:none" id="trainingBatchProcessImg"/>
					<select class="form-control border-radius-0" id="batchId" onchange="clearErrMsg();getCallerOverViewForAdmin();">
					<option value="0">Select Batch</option>	
					</select>
					<!--comment<small class="help-block pull-right" style="color:#996633;  margin-bottom: 0px;"><i>Avail Calls - 110</i></small>-->
				</div>  			
				
				<div class="col-md-12 m_top5" id="AdminCallersOverview">
					<!--<table class="table table-condensed"  style="font-size:11px;  margin-bottom: 5px;">
						<tr class="custom-info">
							<td colspan="4" style="background:#99cccc;">Harish <small class="pull-right" >Selected Batch Confirmed Members - 50&nbsp;<input type="checkbox"> </small>  </td>
						</tr>
						<tr class="custom-info">
							<td>&nbsp;</td>
							<td>ASSIGNED</td>
							<td >COMPLETED</td>
							<td>PENDING</td>
						</tr>
						<tr class="custom-info">
							<td><small>Scheduled Confirmation </small></td>
							<td>250</td>
							<td>220</td>
							<td>30</td>
						</tr>
						<tr class="custom-info">
							<td><small>Batch Confirmation</small></td>
							<td>250</td>
							<td>220</td>
							<td>30</td>
						</tr>	
					</table>	
					
					<table class="table table-condensed"  style="font-size:11px;  margin-bottom: 5px;">
						<tr class="custom-info">
							<td colspan="4" style="background:#99cccc;">Ramesh <small class="pull-right" >Selected Batch Confirmed Members - 50&nbsp;<input type="checkbox"> </small>  </td>
						</tr>
						<tr class="custom-info">
							<td>&nbsp;</td>
							<td>ASSIGNED</td>
							<td >COMPLETED</td>
							<td>PENDING</td>
						</tr>
						<tr class="custom-info">
							<td><small>Scheduled Confirmation </small></td>
							<td>250</td>
							<td>220</td>
							<td>30</td>
						</tr>
						<tr class="custom-info">
							<td><small>Batch Confirmation</small></td>
							<td>250</td>
							<td>220</td>
							<td>30</td>
						</tr>	
					</table>
					
					<table class="table table-condensed"  style="font-size:11px;  margin-bottom: 5px;">
						<tr class="custom-info">
							<td colspan="4" style="background:#99cccc;">Suresh <small class="pull-right" >Selected Batch Confirmed Members - 50&nbsp;<input type="checkbox"> </small>  </td>
						</tr>
						<tr class="custom-info">
							<td>&nbsp;</td>
							<td>ASSIGNED</td>
							<td >COMPLETED</td>
							<td>PENDING</td>
						</tr>
						<tr class="custom-info">
							<td><small>Scheduled Confirmation </small></td>
							<td>250</td>
							<td>220</td>
							<td>30</td>
						</tr>
						<tr class="custom-info">
							<td><small>Batch Confirmation</small></td>
							<td>250</td>
							<td>220</td>
							<td>30</td>
						</tr>	
					</table>-->
					
				</div>  
				
				<div class="col-md-12 ">
					<label>Select Call Center Agent Name</label><img src="images/icons/search.gif" style="display:none;" id="agentProcessingImg">
					<select class="form-control border-radius-0" id="batchAgentId" onchange="getCallerOverView('batch');">
						
					</select>					
				</div>  
				<div class="col-md-12 m_top5" id="batchcallerOverViewDiv">
					<!--<table class="table table-condensed"  style="font-size:11px; margin-bottom: 0px;">
						<tr class="custom-info">
							<td>&nbsp;</td>
							<td>ASSIGNED</td>
							<td >COMPLETED</td>
							<td>PENDING</td>
						</tr>
						<tr class="custom-info">
							<td><small>Scheduled Confirmation </small></td>
							<td>250</td>
							<td>220</td>
							<td>30</td>
						</tr>
						<tr class="custom-info">
							<td><small>Batch Confirmation</small></td>
							<td>250</td>
							<td>220</td>
							<td>30</td>
						</tr>	
					</table>	-->		
				</div>  
				<!--comment<div class="col-md-12">
					<h5 style="color:#ff6666 !important;">Pending Calls 30 + New Calls 20 = 50</h5>
				</div>-->
				<div class="col-md-12 m_top20">
				   <div id="batchSuccessMsgDiv"></div>
					<button class="btn btn-success btn-block border-radius-0" id="assignBatchId"
					onclick="assignBatch();">Assign to Agent</button><img src="images/icons/search.gif" id="batchProcessingImg" style="display:none;"/>
				</div>  
			</div>
		</div>
    </div>
  </div>
</div>
<!---Modal-2----------------->

	<!-- end -->	
	
	<!-- Shedule -->
	<!---Modal-1----------------->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header  bg_d">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h5 class="modal-title text-uppercase">Assign Members to Schedule Confirmation</h5>
      </div>
      <div class="modal-body">
      		<div class="row">
			<div id="errorMsgDivId"></div>
			<div class="col-md-12 m_top20"><input type="radio" value="District" name="locationTypeRadio" checked onclick="getScheduleAvailableCallsCount();">&nbsp;District &nbsp;<input type="radio" value="Parliament" name="locationTypeRadio" onclick="getScheduleAvailableCallsCount();">&nbsp;Parliament</div>
			<div class="col-md-12 m_top10">
					<label>Select Program Name</label>
					<select class="form-control border-radius-0" id="programId" onchange="getCampsForProgram('schedule');">
					<option value="0">Select Program</option>	
					</select>
				</div> 
				<div class="col-md-12 m_top5">
					<label>Select Center</label>
					<select class="form-control border-radius-0"  id="campId" onchange="getSchedulesForCamp('schedule');">
					<option value="0">Select Camp</option>	
					</select>
				</div>   
				  
				<div class="col-md-12 m_top10">
					<label>Select Calender Scheduled Dates</label>
					<select class="form-control border-radius-0" id="scheduleId" onchange="getScheduleAvailableCallsCount();" >
					<option value="0">Select Schedule</option>	
					</select>
					<small class="help-block pull-right" style="color:#996633;  margin-bottom: 0px;" id="avaliableCallsCount"></small>
				</div>   
				<div id="callCenterErrorDiv"></div>
				<div class="col-md-12 ">
					<label>Select Call Center Agent Name</label>
					<img src="images/icons/search.gif" id="scheduleProcessImg" style="display:none;"/>
					<select class="form-control border-radius-0" id="agentId1" onchange="getCallerOverView('schedule');">
						
					</select>					
				</div>   
				<div class="col-md-12 m_top5" id="callerOverViewDiv">
							
				</div>  
				<div class="col-md-12 m_top5">
					<div class="panel-group distaccordion scheduleMembersDiv m_top10" id="distaccordion" role="tablist" aria-multiselectable="true">
					<!--<label>Select No Of Calls</label>
					<ul class="list-unstyled" style="background: rgb(255, 255, 255) none repeat scroll 0% 0%; border: 1px solid rgb(153, 153, 153); padding: 5px;">
						<li>Srikakulam  - 70 <input class="pull-right" type="checkbox"> </li>
						<li>Visakhapatnam  - 80 <input class="pull-right" type="checkbox"> 
							<ul>
								<li>Srungavarapukota -20 <input class="pull-right" type="checkbox"> </li>
								<li>Bhimli  -20 <input class="pull-right" type="checkbox"> 
									<ul>
										<li>Bobbili  -06 <input class="pull-right" type="checkbox"> </li>
										<li>Ramabhadrapuram  -08 <input class="pull-right" type="checkbox"> </li>
										<li>Badangi  -04 <input class="pull-right" type="checkbox"> </li>
										<li>Therlam  -04 <input class="pull-right" type="checkbox"> </li>
									</ul>
								</li>
								<li>Visakhapatnam East  -20 <input class="pull-right" type="checkbox"> </li>
								<li>Visakhapatnam South  -20 <input class="pull-right" type="checkbox"> </li>
							</ul>
						</li>
						<li>Srikakulam  - 70 <input class="pull-right" type="checkbox"> </li>
					</ul>-->					
				</div>   
				
				
				<!--<div class="col-md-12">
					<h5 style="color:#ff6666 !important;">Pending Calls 30 + New Calls 20 = 50</h5>
				</div>-->
				<div class="col-md-12 m_top20">
				   <div id="agentSuccessMsgDiv"></div>
				   
					<button id="assignSchedulebtn" class="btn btn-success btn-block border-radius-0" onclick="assignSchedule();">Assign to Agent</button><img src="images/icons/search.gif" id="processingImg" style="display:none;"/>
				</div>  
			</div>
		</div>
    </div>
  </div>
</div>

<!---Modal-2----------------->
	<!-- End -- >

-->
</section>
<footer>
		<p class="text-center">All &copy; 2015 Telugu Desam Party</p>
</footer>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src=" https://code.jquery.com/ui/1.11.1/jqueryui/1.11.1/jquery-ui.js "></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/cadreCommittee/dist/js/bootstrap.min.js"></script>
	<!--Circle js file-->
	
	<!--Bootstrap Date Picker-->
   <script src="js/cadreCommittee/bootstrapDaterangepicker/moment.min.js" type="text/javascript"></script> 
	<script src="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker.js" type="text/javascript"></script>
	<script src="js/cadreCommittee/dist/js/jquery.circliful.min.js"></script>
	<script src="css/Training/scroll/jquery.mCustomScrollbar.js" type="text/javascript"></script>
	<script src="css/Training/scroll/jquery.mousewheel.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/highcharts/js/highcharts_cadre.js"></script>
	<script type="text/javascript" src="js/TrainingProgram/callCenterTrainingAdmin.js"></script>
<!--
<script src="dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>

<script src="dist/DateRange/moment.js" type="text/javascript"></script>	
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>

<script src="dist/js/jquery.circliful.min.js" type="text/javascript"></script>

<script src="dist/scroll/jquery.mCustomScrollbar.js" type="text/javascript"></script>

<script src="dist/scroll/jquery.mousewheel.js" type="text/javascript"></script>
<script src="dist/HighCharts/highcharts.js" type="text/javascript"></script>  -->
<script type="text/javascript">
$(".table-scroll").mCustomScrollbar({
	setHeight:470,
	theme:"minimal-dark"
});
$(document).ready(function() {
  /* var cb = function(start, end, label) {
	console.log(start.toISOString(), end.toISOString(), label);
	$('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
	//alert("Callback has fired: [" + start.format('MMMM D, YYYY') + " to " + end.format('MMMM D, YYYY') + ", label = " + label + "]");
  }

  var optionSet1 = {
	startDate: moment().subtract(29, 'days'),
	endDate: moment(),
	showDropdowns: true,
	showWeekNumbers: true,
	timePicker: false,
	timePickerIncrement: 1,
	timePicker12Hour: true,
	ranges: {
	   'Today': [moment(), moment()],
	   'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	   'Last 7 Days': [moment().subtract(6, 'days'), moment()],
	   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
	   'This Month': [moment().startOf('month'), moment().endOf('month')],
	   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
	},
	opens: 'left',
	buttonClasses: ['btn btn-default'],
	applyClass: 'btn-small btn-primary',
	cancelClass: 'btn-small',
	format: 'MM/DD/YYYY',
	separator: ' to ',
	locale: {
		applyLabel: 'Submit',
		cancelLabel: 'Clear',
		fromLabel: 'From',
		toLabel: 'To',
		customRangeLabel: 'Custom',
		daysOfWeek: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr','Sa'],
		monthNames: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
		firstDay: 1
	}
  };
  $('#reportrange span').html(moment().subtract(29, 'days').format('MMMM D, YYYY') + ' - ' + moment().format('MMMM D, YYYY'));

  $('#reportrange').daterangepicker(optionSet1, cb);

  $('#reportrange').on('show.daterangepicker', function() { console.log("show event fired"); });
  $('#reportrange').on('hide.daterangepicker', function() { console.log("hide event fired"); });
  $('#reportrange').on('apply.daterangepicker', function(ev, picker) { 
	console.log("apply event fired, start/end dates are " 
	  + picker.startDate.format('MMMM D, YYYY') 
	  + " to " 
	  + picker.endDate.format('MMMM D, YYYY')
	); 
  });
  $('#reportrange').on('cancel.daterangepicker', function(ev, picker) { console.log("cancel event fired"); });
});

 */

   var cb = function(start, end, label) {
	console.log(start.toISOString(), end.toISOString(), label);
  }

  var optionSet1 = {
	//startDate: moment().subtract(29, 'days'),
	endDate: moment(),
	//minDate: '01/01/2012',
	//maxDate: '12/31/2015',
	//dateLimit: { days: 60 },
	showDropdowns: true,
	showWeekNumbers: true,
	timePicker: false,
	timePickerIncrement: 1,
	timePicker12Hour: true,
	ranges: {
	   'Today': [moment(), moment()],
	   'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	   'Last 7 Days': [moment().subtract(6, 'days'), moment()],
	   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
	  // 'Next 30 Days': [moment().add(29, 'days'), moment()],
	   'This Month': [moment().startOf('month'), moment().endOf('month')],
	   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
	},
	opens: 'left',
	buttonClasses: ['btn btn-default'],
	applyClass: 'btn-small btn-primary newsSubmitBtn',
	cancelClass: 'btn-small',
	format: 'MM/DD/YYYY',
	separator: ' to ',
	locale: {
		applyLabel: 'Submit',
		cancelLabel: 'Clear',
		fromLabel: 'From',
		toLabel: 'To',
		customRangeLabel: 'Custom',
		daysOfWeek: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr','Sa'],
		monthNames: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
		firstDay: 1
	}
  };

  var optionSet2 = {
	startDate: moment().subtract(7, 'days'),
	endDate: moment(),
	opens: 'left',
	ranges: {
	   'Today': [moment(), moment()],
	   'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	   'Last 7 Days': [moment().subtract(6, 'days'), moment()],
	   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
	   'This Month': [moment().startOf('month'), moment().endOf('month')],
	   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
	}
  };

  $('#reportrange span').html(moment().subtract(29, 'days').format('MMMM D, YYYY') + ' - ' + moment().format('MMMM D, YYYY'));

  $('#reportrange').daterangepicker(optionSet1, cb);

  $('#reportrange').on('show.daterangepicker', function() { console.log("show event fired"); });
  $('#reportrange').on('hide.daterangepicker', function() { console.log("hide event fired"); });
  $('#reportrange').on('apply.daterangepicker', function(ev, picker) { 
	console.log("apply event fired, start/end dates are " 
	  + picker.startDate.format('MMMM D, YYYY') 
	  + " to " 
	  + picker.endDate.format('MMMM D, YYYY')
	); 
  });
  $('#reportrange').on('cancel.daterangepicker', function(ev, picker) { console.log("cancel event fired"); });

  $('#options1').click(function() {
	$('#reportrange').data('daterangepicker').setOptions(optionSet1, cb);
  });

  $('#options2').click(function() {
	$('#reportrange').data('daterangepicker').setOptions(optionSet2, cb);
  });

  $('#destroy').click(function() {
	$('#reportrange').data('daterangepicker').remove();
  });
  
  	getAllUserIdsByUserType();
	getCallerWiseCallsDetails("Invitation");
	getTrainingProgramMembersBatchCount();
	getScheduleAndConfirmationCallsOfCallerToAgent();
	getCampusWiseDateWiseInterestedMembersDetails('interested');
	getCampusWiseBatchWiseMembersDetails('notStarted','scheduled');
	getCallsDetailsOfCallCenterAdmin();
	getUpComingBatchDetails();

});

</script>
<script>

	function getAllUserIdsByUserType(){
		
		$('.callerId').find('option').remove();
		$('#agentId').find('option').remove();
		
		$('.callerId').append('<option value="0">Select Caller</option>');
		$('#agentId').append('<option value="0">Select Caller</option>');
		
		var jsObj={
			searchType:"",
		}

		$.ajax({
			type:'POST',
			 url: 'getUsersofUserTypeAction.action',
			 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				var str='';
				if(result !=null){
					for(var i in result){
						str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
					}
					
					$(".callerId").append(str);
					$("#agentId").append(str);
				}
			});
		
	}
	
	function getCallerWiseCallsDetails(agentType){
	$("#participated").html('<center><img src="images/icons/loading.gif" style="width: 30px; height: 30px;margin-top:30px;"/></center>');
	$("#area").html('<center><img src="images/icons/loading.gif" style="width: 30px; height: 30px;margin-top:30px;"/></center>');
		$("#dataNotAvailableDiv").html('');
		$("#memberAvailabilityDivCls").html("");
		$("#statusWiseCountArraId").html("");
		$("#totalCallsPerCallerId").html("");
		$("#todayCallsPerCallerId").html("");
		$("#donutchartForStatus").html("");
		$("#circleForConfirmed").html("");
		//data loading images  showing for total counts of allocated callers
		$("#dataLoadingsImgForTotalCallerCount").show();
		$("#dataLoadingsImgForTodayCount").show();
		$("#dataLoadingsImgForDonutchartStatus").show();
		$("#dataLoadingsImgForCircleForConfirmed").show();
		
		//members count block data loading
		$("#dataLoadingsImgForProgressOfAgentCountId").show();
		$("#dataLoadingsImgForProgressOfConfirmationId").show();
		
		
		
		setcolorsForTrainingchart();
		
		var fromDate='';
		var toDate='';
		var dateStr=$("#reportrange").val();
		var dateArr=[];
		if(dateStr !=null && dateStr.length>0){
			dateArr=dateStr.split("to");
			fromDate= dateArr[0];
			toDate=  dateArr[1];
		}
		
		var jsObj={
			searchType:"",
			fromdate : fromDate,
			toDate   : toDate,
			agentType : agentType
		}
		$.ajax({
				type:'POST',
				 url: 'getCallerWiseCallsDetailsAction.action',
				 data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					
					//data loading images  hiding for total counts of allocated callers
					$("#dataLoadingsImgForTotalCallerCount").hide();
					$("#dataLoadingsImgForTodayCount").hide();
					$("#dataLoadingsImgForDonutchartStatus").hide();
					$("#dataLoadingsImgForCircleForConfirmed").hide();
					
					$("#dataLoadingsImgForProgressOfAgentCountId").hide();
					$("#dataLoadingsImgForProgressOfConfirmationId").hide();
					
					if(result !=null){ 
						$("#totalCallsPerCallerId").html(result.totalCount);
						$("#todayCallsPerCallerId").html(result.todayAllocatedCalls);
						buildingCallerWiseCallsDetails(result,agentType);
					}
					else{
						$("#memberAvailabilityDivCls").html("problem occured.please contact admin.");
					}
				});
		
	}
	function buildingCallerWiseCallsDetails(result,agentType){
		var str='';
		var str1='';
				if(result.trainingCampVOList !=null){
						str+='<table class="table table-bordered m_0" id="dataNotAvailableDiv">';
							str+='<thead class="bg_d" style="font-size:12px;">';
								str+='<th>Caller Name</th>';
								str+='<th>Assigned Calls</th>';
								str+='<th>Dialed Calls</th>';//completed calls
								str+='<th>Un Dialed</th>';//pending calls
								
									if(result.trainingCampVOList[0].trainingCampVOList !=null){
										
									if(agentType == "Confirmation"){
										for(var i in result.trainingCampVOList[0].trainingCampVOList){
										
										if(result.trainingCampVOList[0].trainingCampVOList[i].status != "Interested" && 
													 result.trainingCampVOList[0].trainingCampVOList[i].status != "Switch-Off"){
												str+='<th id='+result.trainingCampVOList[0].trainingCampVOList[i].statusId+'>'+result.trainingCampVOList[0].trainingCampVOList[i].status+'</th>';		 
										}	 
									 }
									}
									else{//Invitation
										for(var i in result.trainingCampVOList[0].trainingCampVOList){
										
										if(result.trainingCampVOList[0].trainingCampVOList[i].status != "Confirmed" && 
													 result.trainingCampVOList[0].trainingCampVOList[i].status != "Switch-Off"){
											str+='<th id='+result.trainingCampVOList[0].trainingCampVOList[i].statusId+'>'+result.trainingCampVOList[0].trainingCampVOList[i].status+'</th>';			 
										}	 
									 }
									}
									
								 }
								
							str+='</thead>';
							str+='<tbody>';
							
							if(result.trainingCampScheduleVOList !=null && result.trainingCampScheduleVOList.length>0){
								buildingHighchartForStatus(result.trainingCampScheduleVOList);
								buildingStatusWiseCountForUpper(result.trainingCampScheduleVOList);
								buildingMembersFilledInCalenderBatch(result.trainingCampScheduleVOList);
							}
							else{
								$("#donutchartForStatus").html("<div style='margin-top:100px;text-align:center;'>Status Data Not Available.</div>");
								
								str1+='<div id="myStathalf"  class="text-center" data-info="0" data-dimension="150px" data-percent="0" data-fgcolor="blue" data-bgcolor="#cccccc" data-type="half" >';
										str1+='</div>';
									
									$("#circleForConfirmed").html(str1);
									$('#myStathalf').circliful();
							}
							
							if(result.trainingCampVOList !=null && result.trainingCampVOList.length>0){
								for(var i in result.trainingCampVOList){
									str+='<tr>';
									str+='<td style="text-align:center;"  id="'+result.trainingCampVOList[i].id+'">'+result.trainingCampVOList[i].name+'</td>';
									str+='<td style="text-align:center;" >'+result.trainingCampVOList[i].assignedCallsCount+'</td>';
									str+='<td style="text-align:center;" >'+result.trainingCampVOList[i].completedCallsCount+'</td>';
									str+='<td style="text-align:center;" >'+result.trainingCampVOList[i].pendingCallsCount+'</td>';
									
									
									if(agentType == "Confirmation"){
										if(result.trainingCampVOList[i].trainingCampVOList !=null && result.trainingCampVOList[i].trainingCampVOList.length>0)
										{
											for(var j in result.trainingCampVOList[i].trainingCampVOList){
													if(result.trainingCampVOList[i].trainingCampVOList[j].status != "Interested" && 
													 result.trainingCampVOList[i].trainingCampVOList[j].status != "Switch-Off"){
														str+='<td style="text-align:center;" >'+result.trainingCampVOList[i].trainingCampVOList[j].count+'</td>';
													}	
											}
										}
									}
									else{
										
										var interestecCount=0;
										if(result.trainingCampVOList[i].trainingCampVOList !=null && result.trainingCampVOList[i].trainingCampVOList.length>0)
										{
											for(var j in result.trainingCampVOList[i].trainingCampVOList){
												if(result.trainingCampVOList[i].trainingCampVOList[j].status == "Interested" || result.trainingCampVOList[i].trainingCampVOList[j].status == "Confirmed"){
													interestecCount = interestecCount + result.trainingCampVOList[i].trainingCampVOList[j].count;
												}
											}
										}
										if(result.trainingCampVOList[i].trainingCampVOList !=null && result.trainingCampVOList[i].trainingCampVOList.length>0)
										{
											for(var j in result.trainingCampVOList[i].trainingCampVOList){
												
												if(result.trainingCampVOList[i].trainingCampVOList[j].status == "Interested"){
													str+='<td style="text-align:center;" >'+interestecCount+'</td>';
												}
												else if(result.trainingCampVOList[i].trainingCampVOList[j].status != "Confirmed" && 
													 result.trainingCampVOList[i].trainingCampVOList[j].status != "Switch-Off"){
													str+='<td style="text-align:center;" >'+result.trainingCampVOList[i].trainingCampVOList[j].count+'</td>';
												}
											}
										}
									}
									
									
									str+='</tr>';
								}
							}
							else{
								str+='<tr>Data Not Available</tr>';
							}
								
							str+='</tbody>';
						str+='</table>';
					if(agentType == "Invitation"){
						$(".memberAvailabilityDivCls").html(str);	
					}
					 else if(agentType == "Confirmation"){
						$(".confirmationMemberAvailabilityDivCls").html(str);	
					}
					
				}else{
					if(agentType == "Invitation"){
						$(".memberAvailabilityDivCls").html('<div class="text-center"><b> Schedule Data Not Available...</b></div>');
					}
					else if(agentType == "Confirmation"){
						$(".confirmationMemberAvailabilityDivCls").html('<div class="text-center"><b> Confirmation Data Not Available...</b></div>');	
					}
					//highchart and circle filling
					
					$("#donutchartForStatus").html("<div style='margin-top:100px;text-align:center;'>Status Data Not Available.</div>");
								
					str1+='<div id="myStathalf"  class="text-center" data-info="0" data-dimension="150px" data-percent="0" data-fgcolor="blue" data-bgcolor="#cccccc" data-type="half" >';
						str1+='</div>';
					
					$("#circleForConfirmed").html(str1);
					$('#myStathalf').circliful();
				}
	}
	
	
	//balu chart
var trainingColorArr = [];
var statusArr = ['Invited','Pending','Not Now','Interested','Not Interested','Call Back - Busy','Call Back - Confirm Later','Wrong Mobile No','Invalid Mobile No','Confirmed'];  
function setcolorsForTrainingchart()
	{
		
		trainingColorArr = new Array();
		var colorStatic = new Array('#EE82EE', '#FF0000', '#D2B48C', '#40B5BF' , '#FA8072','#DDA0DD','#BC8F8F','#A9A9A9','#FDF5E6','#00FF00');
		
		var colorCount = 0;
		
		for(var i in statusArr)
		{
		 
				var obj = {
				 status : statusArr[i],
				 color : colorStatic[colorCount]
				}
				trainingColorArr.push(obj)
			  
				if(colorCount == (colorStatic.length)-1)
				colorCount = 0;
				 colorCount++;
		}
		
		return trainingColorArr;
	}
function getColorCodeByType(status)
	{

		if(trainingColorArr != null && trainingColorArr.length > 0)
		{
			for(var i in trainingColorArr)
			{
				if(trainingColorArr[i].status == status)
					return trainingColorArr[i].color;
			}
		}
	}
function buildingHighchartForStatus(result){
	var statusArray=[];
	var colorsArr = [];
	if(result !=null && result.length>0){
		for(var i in result){
			var data=new Array();
			var statusName = result[i].status;
			var count      = result[i].count;
			data.push(statusName,count);
			statusArray.push(data);
			colorsArr.push(getColorCodeByType(result[i].status));
		}
	}
	
	Highcharts.setOptions({
        colors: colorsArr
    });
    $('#donutchartForStatus').highcharts({
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
                innerSize: 80,
                depth: 10,
				dataLabels: {
                    enabled: false,
				}
            }, 
        },
		
		series: [{
			name : 'Count',
            data:statusArray
        }]
    });
}

function buildingMembersFilledInCalenderBatch(result){
	
	$("#circleForConfirmed").html("");
	var str='';
	if(result !=null){
		
		var statusPercentage=0;
		for(var i in result){
			statusPercentage=statusPercentage+result[i].count;
		}
		confirmedCount=0;
		for(var i in result){
			if(result[i].status == "Interested"){
				confirmedCount = result[i].count;
			}
		}
		//percentage Calculation
		var resultPerc = 0;
		if(confirmedCount !=null && confirmedCount !=0){
			resultPerc = parseInt((confirmedCount) * 100)/ parseInt(statusPercentage);
		}
		
		//assigning percentage to Circle
		for(var i in result){
			
			if(result[i].status == "Confirmed"){
				str+='<div id="myStathalf"  class="text-center" data-info="'+result[i].count+'" data-dimension="150px" data-percent="'+resultPerc+'" data-fgcolor="#00FF00" data-bgcolor="#cccccc" data-type="half" >';
				str+='</div>';
			}else{
				str+='<div id="myStathalf"  class="text-center" data-info="0" data-dimension="150px" data-percent="0" data-fgcolor="blue" data-bgcolor="#cccccc" data-type="half" >';
				str+='</div>';
			}
			
			$("#circleForConfirmed").html(str);
			$('#myStathalf').circliful();
		}
	}
	
}
	 //totalProgramsCountId totalCampsCountId 
	 //batchCountOfProgramId batchCountOfCampId
	
	function getTrainingProgramMembersBatchCount(){
		
		var fromDate='';
		var toDate='';
		var dateStr=$("#reportrange").val();
		var dateArr=[];
		if(dateStr !=null && dateStr.length>0){
			dateArr=dateStr.split("to");
			fromDate= dateArr[0];
			toDate=  dateArr[1];
		}
		
		var jsObj={
			fromdate:fromDate,
			toDate :toDate
		/* 	fromdate:"09/01/2015",
			toDate:"09/30/2015" */
		}
		$.ajax({
				type:'POST',
				 url: 'getTrainingProgramMembersBatchCountAction.action',
				 data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					var str='';
					if(result !=null){
						$("#totalProgramsCountId").html(result.programCount);
						$("#totalCampsCountId").html(result.campCount);
						
						if(result.trainingCampVOList !=null){
							buildingProgramMembersBatchCount(result);
						}
						else{
							$("#batchCountOfProgramId").html("Programs Data  Not Available.")
						}
						if(result.trainingCampScheduleVOList !=null){
							buildingCampMembersBatchCount(result.trainingCampScheduleVOList);
						}
						else{
							$("#batchCountOfCampId").html("Centers Data Not Available");	
						}
						
					}
						
				});
		
	}
	function buildingProgramMembersBatchCount(result){
		
		var str='';
		if(result.trainingCampVOList !=null){
		str+='<table class="table table-hover table-bordered">';
										str+='<thead>';
											str+='<th>PROGRAM NAME</th>';
											str+='<th>MEMBERS</th>';
											str+='<th>BATCHES</th>';
										str+='</thead>';
										str+='<tbody>';
										for(var i in result.trainingCampVOList){
											str+='<tr>';
												str+='<td style="text-align:center;"  id="'+result.trainingCampVOList[i].id+'">'+result.trainingCampVOList[i].name+'</td>';
												str+='<td style="text-align:center;" >'+result.trainingCampVOList[i].totalCount+'</td>';
												str+='<td style="text-align:center;" >'+result.trainingCampVOList[i].count+'</td>';
											str+='</tr>';
										}	
									str+='</tbody>';
						str+='</table>';
			$("#batchCountOfProgramId").html(str);
			//scroll
			$(".count-hover-scroll").mCustomScrollbar({
				setHeight:140,
				theme:"minimal-dark"
			});
		}
	else{
		$("#batchCountOfProgramId").html("Program Data Not Available.");
	}
		
		
	}
	
	function buildingCampMembersBatchCount(result){
		var str='';
		if(result !=null){
				str+='<table class="table table-hover table-bordered m_0">';
					str+='<thead>';
						str+='<th>PROGRAM NAME</th>';
						str+='<th>MEMBERS</th>';
						str+='<th>BATCHES</th>';
					str+='</thead>';
					str+='<tbody>';
						for(var i in result){
							str+='<tr>';
								str+='<td style="text-align:center;"  id="'+result[i].id+'">'+result[i].name+'</td>';
								str+='<td style="text-align:center;" >'+result[i].totalCount+'</td>';
								str+='<td style="text-align:center;" >'+result[i].count+'</td>';
							str+='</tr>';
						}
					str+='</tbody>';
				str+='</table>';
				
				$("#batchCountOfCampId").html(str);
				
				//scroll
				$(".count-hover-scroll1").mCustomScrollbar({
				setHeight:190,
					theme:"minimal-dark"
				});
		}
		else{
			$("#batchCountOfCampId").html("Camp Data Not Availale");
		}
		
	}
	
	
	function getScheduleAndConfirmationCallsOfCallerToAgent(){
		
		$("#callPurposeCountDivId").html("");
		
		var fromDate='';
		var toDate='';
		var dateStr=$("#reportrange").val();
		var dateArr=[];
		if(dateStr !=null && dateStr.length>0){
			dateArr=dateStr.split("to");
			fromDate= dateArr[0];
			toDate=  dateArr[1];
		}
		
		var jsObj={
				fromdate:fromDate,
				toDate :toDate
				/* fromdate:"09/01/2015",
				toDate :"09/11/2015" */
		}
		$.ajax({
				type:'POST',
				 url: 'getScheduleAndConfirmationCallsOfCallerToAgentAction.action',
				 data : {task:JSON.stringify(jsObj)} ,
				}).done(function(results){
					var str='';
					if(results.trainingCampScheduleVOList !=null && results.trainingCampScheduleVOList.length>0){
						var result = results.trainingCampScheduleVOList;
						str+='<table class="table table-bordered m_0">';
							str+='<tr>';
								str+='<td style="text-align:center;"  class="pad_5">';
									str+='<h4 class="display-style m_0"> Calls Assigned <br/> to Caller </h4>';
									str+='<span class="pull-right"><h2 class="m_0">'+result[0].totalAssignedCount+'</h2></span>';
								str+='</td>';
								str+='<td style="text-align:center;"  class="pad_5 text-yellow">';
									str+='<p class="m_0 font-10"> Caller <br/>Dialled</p>';
									str+='<h4 class="m_0 text-yellow">'+result[0].totalDialedCallsCount+'</h4>';
								str+='</td>';
							str+='</tr>';
							for(var i in result){
								str+='<tr>';
									str+='<td style="text-align:center;"  class="pad_5" id="'+result[i].id+'">'+result[i].name+'<span class="pull-right">'+result[i].count+'</span>';
									str+='</td>';
									str+='<td style="text-align:center;"  class="pad_5">';
										str+='<h4 class="m_0 text-yellow">'+result[i].dialedCallsCount+'</h4>';
									str+='</td>';
								str+='</tr>';
							}
						str+='</table>';
						
						$("#callPurposeCountDivId").html(str);
					}
					else{
						$("#callPurposeCountDivId").html("Some problem occured while processing data.Please contact Admin.")
					}
				});
		
	}
	
	//status Wise Count showing   
	function buildingStatusWiseCountForUpper(result){
		var str='';
		if(result !=null){
			str+='<table class="table table-bordered m_0">';
			for(var i in result){
				if(result[i].status == "Interested" || result[i].status == "Confirmed" || result[i].status == "Invited" ){
					str+='<tr>';
						str+='<td style="text-align:center;" >';
						if(result[i].status == "Interested")
						{
							str+='<h4 class="m_bottom0 interested-text" id='+result[i].statusId+'>'+result[i].count+' - '+result[i].status+'</h4>';
						}
						else if(result[i].status == "Confirmed"){
							str+='<h4 class="m_bottom0 confirmed-text" id='+result[i].statusId+'>'+result[i].count+' - '+result[i].status+'</h4>';
						}
						else if(result[i].status == "Invited"){
							str+='<h4 class="m_bottom0 invited-text" id='+result[i].statusId+'>'+result[i].count+' - '+result[i].status+'</h4>';
						}
						else if(result[i].status == "Pending"){
							str+='<h4 class="m_bottom0 pending-text" id='+result[i].statusId+'>'+result[i].count+' - '+result[i].status+'</h4>';
						}
						str+='</td>';
					str+='</tr>';
				}
					
			}
			str+='</table>';
			$("#statusWiseCountArraId").html(str);
		}
	}
	
	$(document).on("click",".searchTypeCls li",function(){
		 var text=$(this).text();
		 
		 $("#"+divId+"").html("");
		 
		 var searchType="";
		 var divId='';
		 if(text == "UPCOMING SCHEDULE"){
		 searchType = "notStarted";
		 divId ='scheduled';
		 }
		 else if(text == "RUNNING SCHEDULE"){
		 searchType = "running";
		 divId ='running';
		 }else if(text == "COMPLETED SCHEDULE"){
		 searchType = "completed";
		 divId ='completed';
		 }else if(text == "CANCELLED SCHEDULE"){
		 searchType = "cancelled";
		 divId ='cancelled';
		 }
		 
		 getCampusWiseBatchWiseMembersDetails(searchType,divId);
		 
	});
 
	$(document).on("click",".ranges li",function(){
		if($(this).text() == "Custom"){
			return;
		}
	
		getCallerWiseCallsDetails("Invitation");
		getTrainingProgramMembersBatchCount();
		getScheduleAndConfirmationCallsOfCallerToAgent();
		getCampusWiseDateWiseInterestedMembersDetails('interested');
		getCampusWiseBatchWiseMembersDetails('notStarted','scheduled');
		getCallsDetailsOfCallCenterAdmin();
		getUpComingBatchDetails();
	});	
	$(document).on("click",".newsSubmitBtn",function(){
		getCallerWiseCallsDetails("Invitation");
		getTrainingProgramMembersBatchCount();
		getScheduleAndConfirmationCallsOfCallerToAgent();
		getCampusWiseDateWiseInterestedMembersDetails('interested');
		getCampusWiseBatchWiseMembersDetails('notStarted','scheduled');
		getCallsDetailsOfCallCenterAdmin();
		getUpComingBatchDetails();
	});
	
	 function getCallsDetailsOfCallCenterAdmin(){
		
		$("#adminAssignedTdId").html("");
		
		$("#dataLoadingImgForAdminAssignedTdId").show();
		
		var fromDate='';
		var toDate='';
		var dateStr=$("#reportrange").val();
		var dateArr=[];
		if(dateStr !=null && dateStr.length>0){
			dateArr=dateStr.split("to");
			fromDate= dateArr[0];
			toDate=  dateArr[1];
		}
		
		

		var jsObj={
			fromDate:fromDate,
			toDate : toDate
		}
		$.ajax({
			type:'POST',
			 url: 'getCallsDetailsOfCallCenterAdminAction.action',
			 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				
				$("#dataLoadingImgForAdminAssignedTdId").hide();
				
				var str='';
				if(result !=null){
					str+='<table class="table table-bordered m_0 table-condensed">';
							str+='<tr style="font-size:12px;">';
								str+='<td></td>';
								str+='<td>Assigned</td>';
								str+='<td class="text-yellow">Dialled</td>';
								str+='<td class="text-yellow">Not Dialled</td>';
								/* str+='<td class="interested-text">Interested</td>';
								str+='<td >Not Interested</td>';
								str+='<td class="text-info">Busy</td>';
								str+='<td class="text-danger">Confirm Later</td>';
								str+='<td class="">Accepted</td>'; */
									
									 if(result[0].trainingCampVOList != null && result[0].trainingCampVOList.length>0){
										
										for(var j in result[0].trainingCampVOList){	
											str+='<td class="" id="'+result[0].trainingCampVOList[j].statusId+'">'+result[0].trainingCampVOList[j].status+'</td>';
										}
									}
						
							str+='</tr>';
							for(var i in result){

							var interestedCount =0;
								if(result[i].name == "Scheduled"){
									if(result[i].trainingCampVOList != null && result[i].trainingCampVOList.length>0){										
										for(var j in result[i].trainingCampVOList){											
											if( result[i].trainingCampVOList[j].statusId ==4 || result[i].trainingCampVOList[j].statusId == 10){	
												interestedCount = interestedCount + parseInt(result[i].trainingCampVOList[j].count);
											}
										}										
									}									
								}
								
								str+='<tr>';
								/* if(result[i].name == "Assigned"){	
									str+='<td>Assigned to Agents</td>';
									str+='<td>'+result[i].assignedCallsCount+'</td>';
									str+='<td>'+result[i].dialedCallsCount+'</td>';
									str+='<td>'+result[i].pendingCallsCount+'</td>';
									
									if(result[i].trainingCampVOList != null && result[i].trainingCampVOList.length>0){
										
										for(var j in result[i].trainingCampVOList){
											
											if(result[i].trainingCampVOList[j].statusId ==4 || result[i].trainingCampVOList[j].statusId ==5 || result[i].trainingCampVOList[j].statusId ==6 || result[i].trainingCampVOList[j].statusId ==7){	
												str+='<td>'+result[i].trainingCampVOList[j].count+'</td>';
											}
										}
										
									}
									
									
								} */
								if(result[i].name == "Assigned"){	
									$("#assignedToAgentsId").html(result[i].assignedCallsCount);
								}else if(result[i].name == "Scheduled"){
									str+='<td>Schedule Confirmation</td>';
									str+='<td>'+result[i].assignedCallsCount+'</td>';
									str+='<td>'+result[i].dialedCallsCount+'</td>';
									str+='<td>'+result[i].pendingCallsCount+'</td>';
									
									if(result[i].trainingCampVOList != null && result[i].trainingCampVOList.length>0){
										
										for(var j in result[i].trainingCampVOList){
											
											 if(result[i].trainingCampVOList[j].statusId ==4){	
												str+='<td>'+interestedCount+'</td>';
											}
											else if(result[i].trainingCampVOList[j].statusId == 10)
											{
												str+='<td> - </td>';
											}
											else{	
												str+='<td>'+result[i].trainingCampVOList[j].count+'</td>';
											}
											
										}
										
									}
									
								}
								else if(result[i].name == "Confirmed"){
									str+='<td>Batch Confirmation</td>';
									str+='<td>'+result[i].assignedCallsCount+'</td>';
									str+='<td>'+result[i].dialedCallsCount+'</td>';
									str+='<td>'+result[i].pendingCallsCount+'</td>';
									
									if(result[i].trainingCampVOList != null && result[i].trainingCampVOList.length>0){
										
										for(var j in result[i].trainingCampVOList){
											
											/* if(result[i].trainingCampVOList[j].statusId ==6 || result[i].trainingCampVOList[j].statusId ==4 || result[i].trainingCampVOList[j].statusId ==7 || result[i].trainingCampVOList[j].statusId ==5 || result[i].trainingCampVOList[j].statusId == 10){
												if(result[i].trainingCampVOList[j].statusId ==4){
													str+='<td>-</td>';
												}
												else{
													str+='<td>'+result[i].trainingCampVOList[j].count+'</td>';
												}
												
											} */
											
											if(result[i].trainingCampVOList[j].statusId ==4){
												str+='<td>-</td>';
											}
											else{
													str+='<td>'+result[i].trainingCampVOList[j].count+'</td>';
												}
											
										}
										
									}
									
								}
								str+='</tr>';
								
							}
							
					str+='</table>';
					
					
					$("#adminAssignedTdId").html(str);
					
				}
				else {
					$("#adminAssignedTdId").html("Problem occured For this Module.Please contact admin.");
				} 
			});
	}
	
	function getUpComingBatchDetails(){
		
		var fromDate='';
		var toDate='';
		var dateStr=$("#reportrange").val();
		var dateArr=[];
		if(dateStr !=null && dateStr.length>0){
			dateArr=dateStr.split("to");
			fromDate= dateArr[0];
			toDate=  dateArr[1];
		}
		

		var jsObj={
			fromDate:fromDate,
			toDate : toDate
		}
		$.ajax({
			type:'POST',
			 url: 'getUpComingBatchDetailsAction.action',
			 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				if(result !=null){
					$("#upcmngSchCountId").html(result.upcomingscheduleCnt);
					$("#upAlctId").html(result.upcomingAllocatedAgnt);
					$("#upNtAlctId").html(result.upNotAllocated);
					
					$("#btchConfId").html(result.batchConfirmCnt);
					$("#btchAlcId").html(result.btchAllocatedCnt);
					$("#btchNtalctedId").html(result.btchNotAllocated);
				}
			});
		
	}
	
$("#mainheading").html("TRAINING PROGRAM");
function getScheduleAvailableCallsCount()
{
 $("#errorMsgDivId").html("");
var campId =$("#campId").val();
var programId =$("#programId").val();
var scheduleId = $("#scheduleId").val();
var locationTypeRadio = $("input[type='radio'][name='locationTypeRadio']:checked").val();
$("#scheduleProcessImg").show();
	var jsObj={
		campId:campId,
		programId:programId,
		scheduleId:scheduleId,
		type:locationTypeRadio,
		task : ""
	}
	
	$.ajax({
		type:'POST',
		url :'getScheduleAvailableCallsCountLocationWiseInfoAction.action',
		data:{task:JSON.stringify(jsObj)},
	}).done(function(result){
		$("#scheduleProcessImg").hide();
		$("#agentSuccessMsgDiv").html("");
		$(".successDivCls").html("");
		buildScheduleMembers(result);
		$("#assignSchedulebtn").prop('disabled', false);
	});
}
var index = 0;
function buildScheduleMembers(result)
{
var total = 0;	
var str = '';
if(result != null && result.length > 0)
{
for(var i in result)
{
	total += result[i].count;
index ++;
	/*str+='<ul class="list-unstyled" style="background: rgb(255, 255, 255) none repeat scroll 0% 0%; border: 1px solid rgb(153, 153, 153); padding: 5px;">';
	str+='<li>'+result[i].name+'  - '+result[i].count+'  <input class="pull-right districtCheck" type="checkbox" value="'+result[i].id+'"> ';
	str+='<ul>';
	for(var j in result[i].subList)
	{
	str+='<li>'+result[i].subList[j].name+'  -'+result[i].subList[j].count+' <input class="pull-right constituencyCheck" type="checkbox" value="'+result[i].subList[j].id+'"> ';
	}
	str+='<ul>';
	for(var k in result[i].subList[j].subList)
	{
	str+='<li>'+result[i].subList[j].subList[k].name+'  -'+result[i].subList[j].subList[k].count+' <input class="pull-right mandalCheck" type="checkbox" value="'+result[i].subList[j].subList[k].id+'"> </li>';
	}
	str+='</ul>';
	str+='</li>';
	str+='</ul>';
	str+='</li>';
	str+='</ul>';
	}*/
	/*Schedule */
	
	str+='<div class="panel panel-default">';
	str+='<div class="panel-heading" role="tab"  style="padding:5px" id="distheading'+index+'">';
	/*District*/
	str+='	<h4 class="panel-title">';
	str+='<a class="accordion-toggle collapsed" role="button" data-toggle="collapse" data-parent="#distaccordion" href="#distcollapse'+index+'" aria-expanded="true" aria-controls="distcollapse'+index+'">'+result[i].name+'  - '+result[i].count+'  </a>';
	str+='<input class="pull-right districtCheck" type="checkbox" value="'+result[i].id+'">';
	str+='</h4>';
	str+='</div>';
	str+='<div id="distcollapse'+index+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="distheading'+index+'">';
	/*District body*/
	str+=' <div class="panel-body "  style="padding:5px 5px 5px 8px">';
	
	/*Const*/
	str+=' <div class="panel-group constaccor" id="accordionInner" role="tablist" aria-multiselectable="true">';
    for(var j in result[i].subList)
	{ 
	index ++;	
	str+=' <div class="panel panel-default border_0 padding_left30">';
    str+='<div class="panel-heading " role="tab" id="consheading'+index+'">';
    str+='<h4 class="panel-title">';
        str+=' <a class="accordion-toggle collapsed" role="button" data-toggle="collapse" data-parent="#accordionInner" href="#conscollapse'+index+'" aria-expanded="true" aria-controls="conscollapse'+index+'">'+result[i].subList[j].name.toLowerCase()+'  -'+result[i].subList[j].count+'</a>';
		str+='<input class="pull-right constituencyCheck subdist'+result[i].id+' parentConst'+result[i].subList[j].id+'" type="checkbox" value="'+result[i].subList[j].id+'"> ';
	
    str+='</h4>';
    str+='</div>';
	/*Mandal*/
	
    str+='<div id="conscollapse'+index+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="consheading'+index+'">';
    str+='<div class="panel-body border_0" style="padding:5px 0px 5px 8px">';
	str+='<ul>';
	for(var k in result[i].subList[j].subList)   
	{
	str+='<li>'+result[i].subList[j].subList[k].name.toLowerCase()+'  -'+result[i].subList[j].subList[k].count+' <input class="pull-right mandalCheck subdist'+result[i].id+' subconst'+result[i].subList[j].id+'" type="checkbox" value="'+result[i].subList[j].subList[k].id+'" id="consti'+result[i].subList[j].id+'"> </li>';
	}
	for(var l in result[i].subList[j].scheduleStatusList){
		str+='<li>'+result[i].subList[j].scheduleStatusList[l].name.toLowerCase()+'  -'+result[i].subList[j].scheduleStatusList[l].count+' <input class="pull-right municipalityCheck subdist'+result[i].id+' subconst'+result[i].subList[j].id+'" type="checkbox" value="'+result[i].subList[j].scheduleStatusList[l].id+'" id="consti'+result[i].subList[j].id+'"> </li>';
	}
	str+='</ul>';
	str+='</div>';
    str+='</div>';
	
    str+='</div>';
	}
	str+='</div>';
	
	str+='</div>';	
	str+='</div>';
	str+='</div>';
	$(".scheduleMembersDiv").html(str);
}
$("#avaliableCallsCount").html("<i>Available Calls - "+total+"</i>");
}	
else
{
$("#avaliableCallsCount").html("<i>Available Calls - "+total+"</i>");

$(".scheduleMembersDiv").html("<font color='red'>No data Available</font>");
}	


$(".constituencyCheck").click(function() {

	   if($(this).is(':checked'))
		{
		var constituencyID = $(this).val();
		//alert($(this).closest(".distaccordion").find(".districtCheck").html())
			$(this).closest(".distaccordion").find(".districtCheck").prop('checked', false);//district
			$(".subconst"+constituencyID).prop('checked', false); // mandal
		}
 });
 
$(".districtCheck").click(function(){
if($(this).is(':checked'))
	{
	var districtID = $(this).val();
	$(".subdist"+districtID).each(function(){
							if($(this).is(':checked'))
							{
								$(".subdist"+districtID).prop('checked', false);
								//$(this).prop('checked', false);
							}
					}) 	
		  }
});


$(".mandalCheck").click(function(){

if($(this).is(':checked'))
	{
	var constituencyID = $(this).attr("id").replace ( /[^\d.]/g, '' );
	
	$(".parentConst"+constituencyID).each(function(){
							if($(this).is(':checked'))
							{
								$(".parentConst"+constituencyID).prop('checked', false);
								
							}
					}) 	
	
	}
	
});
$(".municipalityCheck").click(function(){

if($(this).is(':checked'))
	{
	var constituencyID = $(this).attr("id").replace ( /[^\d.]/g, '' );
	//var mandalId   =  $(this).attr("id").replace ( /[^\d.]/g, '' );
	$(".parentConst"+constituencyID).each(function(){
							if($(this).is(':checked'))
							{
								$(".parentConst"+constituencyID).prop('checked', false);
								
							}
					}) 	
	}
	
});

}

function getAgentsByCampCallerAdminId(type)
{
if(type == 'batch')
{
$("#batchAgentId  option").remove();
$("#batchAgentId").append('<option value="0">Select Caller</option>');
}
else
{
$("#agentId1  option").remove();
$("#agentId1").append('<option value="0">Select Caller</option>');
}
	$.ajax({
		type : "POST",
		url  : "getAgentsByCampCallerAdminIdAction.action"
		
	}).done(function(result){
		if(result != null && result.length > 0)
		{
			for(var i in result)
			{
				if(type == 'batch')
					{
				  $("#batchAgentId").append("<option value='"+result[i].id+"'>"+result[i].name+"</option>");
				  }
				  else
				  {
				 $("#agentId1").append("<option value='"+result[i].id+"'>"+result[i].name+"</option>");
				  }
			}
			
		}
		
	});
}


function assignSchedule()
{
$("#callCenterErrorDiv").html("");	
$("#errorMsgDivId").html("");
$("#agentSuccessMsgDiv").html("");
var districtIds = new Array();
var parliamentIds = new Array();
var constiIds = new Array();
var mandalIds = new Array();
var municipalitys  = new Array();
var locationTypeRadio = $("input[type='radio'][name='locationTypeRadio']:checked").val();
if(locationTypeRadio == "District")
{
$(".districtCheck").each(function(){
if($(this).is(':checked'))
districtIds.push($(this).val());
});
}
if(locationTypeRadio == "Parliament")
{
$(".districtCheck").each(function(){
if($(this).is(':checked'))
parliamentIds.push($(this).val());
});
}
$(".constituencyCheck").each(function()
{
if($(this).is(':checked'))
	constiIds.push($(this).val());		
});
$(".mandalCheck").each(function()
{
if($(this).is(':checked'))
	mandalIds.push($(this).val());		
});
$(".municipalityCheck").each(function()
{
if($(this).is(':checked'))
	municipalitys.push($(this).val());		
});


var scheduleId = $('#scheduleId').val();
var callerId  = $('#agentId1').val();
var campId = $("#campId").val();
var programId = $("#programId").val();

if(campId == null || campId == 0)
{
	$("#errorMsgDivId").html("<div class='errorCls'>Please Select Camp.</div>");
	return;
}
if(programId == null || programId == 0)
{
	$("#errorMsgDivId").html("<div class='errorCls'>Please Select Program.</div>");
	return;
}
if(scheduleId == null || scheduleId == 0)
{
	$("#errorMsgDivId").html("<div class='errorCls'>Please Select Schedule.</div>");
	return;
}

if(callerId == null || callerId == 0)
{
  $("#callCenterErrorDiv").html("Please Select Agent.").css("color:red");
  return;
}
if(locationTypeRadio == "District" && districtIds.length == 0 && constiIds.length == 0)
{
	if(mandalIds.length == 0 && municipalitys.length == 0)
	{
	  $("#callCenterErrorDiv").html("Please Select At Least One Location.").css("color:red");;
      return;
	}
}

if(locationTypeRadio == "Parliament" && parliamentIds.length == 0 && constiIds.length == 0)
{
	if(mandalIds.length == 0 && municipalitys.length == 0)
	{
	  $("#callCenterErrorDiv").html("Please Select At Least One Location.").css("color:red");;
      return;
	}
}

$("#processingImg").show();

var callPurposeId = 1;
$("#assignSchedulebtn").prop('disabled', true);

var jObj={
		districtIds:districtIds,
		constiIds:constiIds,
		mandalIds:mandalIds,
		municipalitys : municipalitys,
		parliamentIds:parliamentIds,
		membersCount:0,
		scheduleId:scheduleId,
		callerId : callerId,
		callPurposeId : callPurposeId,
		task:""
		};
		$.ajax({
			  type:'POST',
			  url: 'saveAllDetailsAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jObj)},
			  }).done(function(result){ 	
			  $("#processingImg").hide();
			  $("#agentId1").val(0);	
              $("#callerOverViewDiv").html("");			  
			  if(result.message == "SUCCESS")
			  {
				 $("#agentSuccessMsgDiv").html("<div class='successDivCls'>Assign to Agent Successfully</div>");
				 
				getScheduleAvailableCallsCount();
				//window.location.reload();				
			  }  
			  else
			  {
				 $("#agentSuccessMsgDiv").html("Error Occured! Try Again...").css("color:red;"); 
				 $("#assignSchedulebtn").prop('disabled', false);
			  }
			  //buildScheduleCallMemberDetailsCount(result,jObj);
		   });	
}
function getCallerOverView(type)
{
if(type == "batch")
{
var agentId = $("#batchAgentId").val();
if(agentId == 0)
return;
$("#batchErrorDiv").html("");
$("#agentProcessingImg").show();
}
else{
var agentId = $("#agentId1").val();
if(agentId == 0)
return;
$("#callCenterErrorDiv").html("");
}

var jObj={
		callerId:agentId,
		task:""
		};
		$.ajax({
			  type:'POST',
			  url: 'callerWiseOverViewAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jObj)},
			  }).done(function(result){ 
			  if(type == "batch")
			  {
			    $("#batchcallerOverViewDiv").html('');
				$("#agentProcessingImg").hide();
				buildCallerOverView(result,'batchcallerOverViewDiv');
			}
			else
			{
			 $("#callerOverViewDiv").html('');
			
			 buildCallerOverView(result,'callerOverViewDiv');
			}
			
		   });
}
function buildCallerOverView(resultList,divId)
{

if(resultList.trainingCampVOList == null || resultList.trainingCampVOList.length == 0)
return;

var result = resultList.trainingCampVOList[0].trainingCampVOList;

var str ='';
str+='<table class="table table-condensed"  style="font-size:11px;">';
str+='<tr class="custom-info">';
str+='<td>&nbsp;</td>';
str+='<td>ASSIGNED</td>';
str+='<td >COMPLETED</td>';
str+='<td>PENDING</td>';
str+='</tr>';
console.log(result[0].allocatedCalls)
str+='<tr class="custom-info">';
str+='<td><small>Scheduled Confirmation </small></td>';
if(result[0].allocatedCalls !=null)
str+='<td>'+result[0].allocatedCalls+'</td>';
else
str+='<td>0</td>';
if(result[0].completedCalls !=null)
str+='<td>'+result[0].completedCalls+'</td>';
else
str+='<td>0</td>';
if(result[0].pendingCalls !=null)
str+='<td>'+result[0].pendingCalls+'</td>';
else
str+='<td>0</td>';
str+='</tr>';

str+='<tr class="custom-info">';
str+='<td><small>Batch Confirmation</small></td>';
if(result[1].allocatedCalls !=null)
str+='<td>'+result[1].allocatedCalls+'</td>';
else
str+='<td>0</td>';
if(result[1].completedCalls !=null)
str+='<td>'+result[1].completedCalls+'</td>';
else
str+='<td>0</td>';
if(result[1].pendingCalls !=null)
str+='<td>'+result[1].pendingCalls+'</td>';
else
str+='<td>0</td>';
str+='</tr>	';

str+='</table>';
$("#"+divId).html(str);
}

   function getBatchesForSchedule()
   {
	   $("#batchErrorDiv").html("");
   var scheduleId = $("#batchScheduleId").val();
   var jObj={
		scheduleId:scheduleId,
		task:""
		};
		$.ajax({
			  type:'POST',
			  url: 'getBatchesByScheduleIdAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jObj)},
			  }).done(function(result){ 			  
				buildBatches(result);
		   });	
   }
    function buildBatches(result)
   {
   var str = '';
     $('#batchId').find('option:not(:first)').remove();
    for(var i in result)
	{
	if(result[i].id == batchId)
	$("#batchId").append('<option value='+result[i].id+' selected>'+result[i].name+'</option>');
	else
	$("#batchId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	}
   }
   
function getCallerOverViewForAdmin()
{
var campId =$("#batchCampId").val();
var programId =$("#batchProgramId").val();
var scheduleId = $("#batchScheduleId").val();
var batchId =$("#batchId").val();
$("#AdminCallersOverview").html('');

if(batchId > 0)
{
	$("#trainingBatchProcessImg").show();
  var jObj={
  campId:campId,
  programId:programId,
  scheduleId:scheduleId,
  batchId:batchId,
		task:""
		};
		$.ajax({
			  type:'POST',
			  url: 'AdminCallersWiseOverViewAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jObj)},
			  }).done(function(result){ 
			  $("#trainingBatchProcessImg").hide();
			  if(result != null){
				buildAdminCallerOverView(result);
				$("#assignBatchId").prop('disabled', false);
			  }
			  else
			  {
				$("#AdminCallersOverview").html('No data available');
			  }
		   });
}
  
}
function buildAdminCallerOverView(resultList)
{
var str=' ';
var result = resultList.trainingCampVOList;
var intrestdcount = 0;
for(var k in result)
{
	if(result[k].interestedCount > 0)
	{
		intrestdcount = intrestdcount+result[k].interestedCount;
	}

}
str=' <div style="text-align:center;font-weight:bold;margin-top:5px;margin-bottom:5px;"> Total interested count : '+intrestdcount+' </div> ';
for(var i in result)
{
	if(result[i].interestedCount > 0)
	{
			var result1 = result[i].trainingCampVOList;
			if(result[i].interestedCount == null)
			result[i].interestedCount =0;
			str+='<table class="table table-condensed"  style="font-size:11px;  margin-bottom: 5px;">';
			str+='<tr class="custom-info">';
			str+='<td colspan="4" style="background:#99cccc;">'+result[i].name+' <small class="pull-right" >Selected Batch Interested Members - '+result[i].interestedCount+'&nbsp;';

				str+='<input type="checkbox" class="callerscheck" value="'+result[i].id+'"/> </small></td>';
			str+='</tr>';
			str+='<tr class="custom-info">';
			str+='<td>&nbsp;</td>';
			str+='<td>ASSIGNED</td>';
			str+='<td >COMPLETED</td>';
			str+='<td>PENDING</td>';
			str+='</tr>';
			str+='<tr class="custom-info">';
			str+='<td><small>Scheduled Confirmation </small></td>';
			if(result1[0].allocatedCalls !=null)
			str+='<td>'+result1[0].allocatedCalls+'</td>';
			else
			str+='<td>0</td>';
			if(result1[0].completedCalls !=null)
			str+='<td>'+result1[0].completedCalls+'</td>';
			else
			str+='<td>0</td>';
			if(result1[0].pendingCalls !=null)
			str+='<td>'+result1[0].pendingCalls+'</td>';
			else
			str+='<td>0</td>';
			str+='</tr>';
			str+='<tr class="custom-info">';
			str+='<td><small>Batch Confirmation</small></td>';
			if(result1[1].allocatedCalls !=null)
			str+='<td>'+result1[1].allocatedCalls+'</td>';
			else
			str+='<td>0</td>';
			if(result1[1].completedCalls !=null)
			str+='<td>'+result1[1].completedCalls+'</td>';
			else
			str+='<td>0</td>';
			if(result1[1].pendingCalls !=null)
			str+='<td>'+result1[1].pendingCalls+'</td>';
			else
			str+='<td>0</td>';
			str+='</tr>	';

			str+='</table>	';
	}

}
$("#AdminCallersOverview").html(str);
					
}
function assignBatch()
{
	$("#batchSuccessMsgDiv").html("");
$("#batchErrorDiv").html("");
var userIds = new Array();
$(".callerscheck").each(function(){
if($(this).is(':checked'))
	userIds.push($(this).val());		
});
var scheduleId = $('#batchScheduleId').val();
var callerId  = $('#batchAgentId').val();
var batchId = $("#batchId").val();
var batchCampId = $("#batchCampId").val();
var batchProgramId = $("#batchProgramId").val();


if(batchCampId == null || batchCampId == 0)
{
  $("#batchErrorDiv").html("<div class='errorCls'>Please Select Camp</div>");
  return;
}

if(batchProgramId == null || batchProgramId == 0)
{
  $("#batchErrorDiv").html("<div class='errorCls'>Please Select Program</div>");
  return;
}

if(scheduleId == null || scheduleId == 0)
{
  $("#batchErrorDiv").html("<div class='errorCls'>Please Select Schedule</div>");
  return;
}

if(batchId == null || batchId == 0)
{
  $("#batchErrorDiv").html("<div class='errorCls'>Please Select Batch</div>");
  return;
}
if(callerId == null || callerId == 0)
{
  $("#batchErrorDiv").html("<div class='errorCls'>Please Select Agent</div>");
  return;
}
if(userIds == null || userIds.length == 0)
{
  $("#batchErrorDiv").html("<div class='errorCls'>Atleast One User Required</div>");
  return;
}
$("#batchProcessingImg").show();
$("#assignBatchId").prop('disabled', true);
var callPurposeId = 2;
var jObj={
		membersCount:0,
		scheduleId:scheduleId,
		callerId : callerId,
		callPurposeId : callPurposeId,
		batchId:batchId,
		userIds:userIds,
		task:""
		};
		$.ajax({
			  type:'POST',
			  url: 'saveAllDetailsAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jObj)},
			  }).done(function(result){ 			  
			  $("#batchProcessingImg").hide();	
			  $("#batchAgentId").val(0);
			  $("#batchcallerOverViewDiv").html("");
			 if(result.message == "SUCCESS")
			  {
				 $("#batchSuccessMsgDiv").html("<div class='successDivCls'>Assign Members to Batch Successfully</div>");
				 //getCallerOverView('batch');
				 getCallerOverViewForAdmin();
				 window.location.reload();
			  }  
			  else
			  {
				 $("#batchSuccessMsgDiv").html("Error Occured! Try Again...").css("color:red;"); 
				 $("#assignBatchId").prop('disabled', false);
			  }
			//buildScheduleCallMemberDetailsCount(result,jObj);
		   });	
}

function clearAssignAgent()
{
	$("#campId").val(0);
	$("#programId").find("option").remove();
	$("#programId").append("<option value='0'>Select Program</option>");
	$("#scheduleId").find("option").remove();
	$("#scheduleId").append("<option value='0'>Select Schedule</option>");
	$("#agentId").val(0);
	$("#callerOverViewDiv").html("");
	$("#distaccordion").html("");
	$("#avaliableCallsCount").html("");
	$("#callCenterErrorDiv").html("");
	$("#errorMsgDivId").html("");
	$("#agentSuccessMsgDiv").html("");
}

function clearBatchPopupFields()
{
	$("#batchErrorDiv").html("");
	$("#batchSuccessMsgDiv").html("");
	$("#batchCampId").val(0);
	$("#batchProgramId").find("option").remove();
	$("#batchProgramId").append("<option value='0'>Select Program</option>");
	$("#batchScheduleId").find("option").remove();
	$("#batchScheduleId").append("<option value='0'>Select Schedule</option>");
	$("#batchId").find("option").remove();
	$("#batchId").append("<option value='0'>Select Batch</option>");
	$("#batchAgentId").val(0);
	$("#AdminCallersOverview").html("");
	$("#batchcallerOverViewDiv").html("");
}
function clearErrMsg()
{
	var batchId = $("#batchId").val();
	if(batchId == null || batchId == 0)
		return;
	else
	 $("#batchErrorDiv").html("");
}
 function getPrograms(type)
   {
   var jObj={
		task:"programs"
		};
		$.ajax({
			  type:'POST',
			  url: 'getAllProgramsAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jObj)},
			  }).done(function(result){ 			  
				buildPrograms(result,type);
		   });	
   }
   function buildPrograms(result,type)
   {
   var str = '';
   if(type == "schedule")
  $('#programId').find('option:not(:first)').remove();
  else
  $('#batchProgramId').find('option:not(:first)').remove();
    for(var i in result)
	{
	 if(type == "schedule")
	$("#programId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	else
	$("#batchProgramId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	}
   
   }
   
     function getCampsForProgram(type)
   {
   if(type == "schedule")
   var programId = $("#programId").val();
   else
    var programId = $("#batchProgramId").val();
   var jObj={
		programId:programId,
		task:"programs"
		};
		$.ajax({
			  type:'POST',
			  url: 'getCampsByProgramIdAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jObj)},
			  }).done(function(result){ 			  
				buildCamps(result,type);
		   });	
   }
   function buildCamps(result,type)
   {
   var str = '';
   if(type == "schedule")
   $('#campId').find('option:not(:first)').remove();
   else
   $('#batchCampId').find('option:not(:first)').remove();
    for(var i in result)
	{
	 if(type == "schedule")
	$("#campId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	else
	$("#batchCampId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	}
   
   }
   
   function getSchedulesForCamp(type)
   {
	 if(type == "schedule")
   var campId = $("#campId").val();
   else
    var campId = $("#batchCampId").val();
   var jObj={
		campId:campId,
		task:"programs"
		};
		$.ajax({
			  type:'POST',
			  url: 'getSchedulesByCampIdAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jObj)},
			  }).done(function(result){ 			  
				buildSchedules(result,type);
		   });	
   }
   function buildSchedules(result,type)
   {
   var str = '';
   if(type == "schedule")
     $('#scheduleId').find('option:not(:first)').remove();
	 else
	  $('#batchScheduleId').find('option:not(:first)').remove();
    for(var i in result)
	{
	  if(type == "schedule")
	$("#scheduleId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	else
	$("#batchScheduleId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	}
   }
   
</script>
<script>
//getAllCamps();

getAgentsByCampCallerAdminId('batch');
getAgentsByCampCallerAdminId('schedule');
</script>
</body>
</html>