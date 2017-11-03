
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Training Center</title>
<link href="training/dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
<link href="training/dist/DateRange/daterangepicker.css" rel="stylesheet" type="text/css">
<link href="training/dist/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/DataTable_NewVersion/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="dist/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css">
<style type="text/css">
#dataTablePagination_length , #dataTablePagination_filter
{
	display:none;
}
#dataTablePagination thead
{
	display:none;
}
.panel-group .panel
{
	margin-top:0px !important;
}
.panel .panel-default
{
	border-width:1px !important
}
footer{background-color:#5c2d25;color:#ccc;padding:30px}
header.eventsheader { 
 background:url("dist/img/header-footer.png") no-repeat scroll center bottom / 100% auto #fed501;
 background-origin: border-box;
 background-repeat: no-repeat;
 height: 71px; 
} 
.summaryCls{color:#252D47;text-decoration: underline;}
.summaryCls:hover{text-decoration: underline;}

#speakersTab tr.odd td.sorting_1,#districtTableId tr.odd td.sorting_1{
    background-color: #d3d3d3 !important;
}
#speakersTab tr.even td.sorting_1 , #districtTableId tr.even td.sorting_1{
    background-color: #fafafa !important;
}
#speakersTab tr.odd,#districtTableId tr.odd {
    background-color: #f3f3f3 !important;
}
#speakersTab th {
    background-color: #DDDDDD !important;
	color:#666666;
}
.table-scroll-1{max-height:400px;overflow-y:auto;z-index:999999}
.panelCollapseIcon.collapsed::before
{  
content:"\002b";
}
.panelCollapseIcon::before
{
	
		font-family:'Glyphicons Halflings';
		content:"\2212";
		float: right;
		font-size:10px;
		font-weight:400 !important;
		margin-left:-4px;
		color:@white_color;
		border:1px solid @white_color;
		padding:2px 4px;
		border:1px solid #000;
		
	
}
.panelCollapseIcon.collapsed::before
{
	content:"\002b";
}
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
					    <!-- <li><a href="dashBoardAction.action"><span>DASHBOARD</span></a> </li>
						    <c:if test="${fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_SUPER_ADMIN')}">
								<li><a tabindex="-1" href="callCenterTrainingAdmin.action"> CALLERS ADMIN DASHBOARD </a></li>
								<li><a tabindex="-1" href="callCenterTrainingAgentDashBoard.action"> CALLERS DASHBOARD </a></li>
								<li><a tabindex="-1" href="trainingCampMainDashboard.action"> TRAINING CAMP FEEDBACK </a></li>
						    </c:if>
							<c:if test="${fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_CALLER_ADMIN')}">
								<li><a tabindex="-1" href="callCenterTrainingAdmin.action"> CALLERS ADMIN DASHBOARD </a></li>
						    </c:if>
							<c:if test="${fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_CALLER')}">
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
<main>
	<div class="container">
    	<div class="row">
        	<div class="col-md-12">
            	<div class="panel panel-default">
                	<div class="panel-heading bg_c">
                    	<h4 class="panel-title" style="font-weight:bold;">
                        	TRAINING CENTER MAIN DASHBOARD
							  <!--<span class="" style="font-size:15px">Select Enrollment</span>-->
									<select id="trainingEnrlmntYrId" style="width: 118px;display:inline-block;padding:2px 6px;height:25px;margin-top: -3px;" id="" title="Select Enrollment"
									 class="pull-right">
										<option value="3">2014-2016</option> 
										<option value="4" selected="selected" >2016-2018</option> 
									</select>
							<span class="pull-right col-md-3" style="margin-top: -8px;">
								<div class="input-group" >
									<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
									<input class="form-control" id="selectDate">
								</div>
							</span>
                        </h4>
                    </div>
                    <div class="panel-body">
						<section>
                        	<div class="row">
                            	<div class="col-md-12">
                                	<table class="table table-bordered">
                                    	<tr>
                                        	<td style="vertical-align:middle">
                                            	<ul class="list-inline m_0">
                                                	<li>Total</li>
                                                    <li><h1 class="m_0" id="totalTariningPrograms">0</h1></li>
													
                                                </ul>
                                                <p class="m_0">Training Programs</p>
                                            </td>
                                            <td style="vertical-align:middle">
                                            	<ul class="list-inline m_0">
                                                	<li>Total</li>
                                                    <li><h1 class="m_0" id="totalTrainingCenters">0</h1></li>
													<li id="totalTraingCentersIconId"  data-toggle="tooltip" title="Training Center Informtion" style="display:none;cursor:pointer;"><span class="glyphicon glyphicon-info-sign"></span></li>
                                                </ul>
                                                <p class="m_0">Training Center</p>
                                            </td>
                                            <td style="vertical-align:middle;padding:0px;">
												<table class="font-13 m_0 table-bordered table table-condensed text-center">
													<tr>
														<td></td>
														<td>Confirmed</td>
														<td>Invitees Attended</td>
														<td>Non-Invitees Attended</td>
													</tr>
													<tr class="text-danger">
														<td >UPCOMING - </td>
														<td id="upcomingMembers" style="font-weight:bold;">0</td>
														<td id="upcomingMembersAte" style="font-weight:bold;">0</td>
														<td id="upcomingMembersNonAte" style="font-weight:bold;">0</td>
													</tr>
													<tr class="text-yellow">
														<td >RUNNING - </td>
														<td id="runningMembers" style="font-weight:bold;">0</td>
														<td id="runningMembersAte" style="font-weight:bold;">0</td>
														<td id="runningMembersNonAte" style="font-weight:bold;">0</td>
													</tr>
													<tr class="text-success">
														<td >COMPLETED - </td>
														<td id="completedMembers" style="font-weight:bold;">0</td>
														<td id="completedMembersAte" style="font-weight:bold;">0</td>
														<td id="completedMembersNonAte" style="font-weight:bold;">0</td>
													</tr>
													<tr>
														<td >TOTAL  </td>
														<td id="totalMembers" style="font-weight:bold;">0</td>
														<td id="totalMembersIn" style="font-weight:bold;">0</td>
														<td id="totalMembersNonIn" style="font-weight:bold;">0</td>
													</tr>
												</table>
                                            </td>
                                            <td style="vertical-align:middle">
												<table class="table-condensed">
													<tr>
														<td>
															<div id="donutchart"  style="height:200px;width:350px"></div>
														</td>
														<!--<td>
															<h4 class="m_0">TOTAL BATCHES - <span id="totalTrainingBatches" style="font-weight:bold;">0</span></h4>
															<ul class="m_0 pad_0 font-12">
																<li class="text-danger" style="padding:2px; font-weight:bold;">UPCOMING BATCHES - <span id="upcomingBatches">0</span></li>
																<li class="text-yellow" style="padding:2px; font-weight:bold;">RUNNING BATCHES - <span id="runningBatches">0</span> </li>
																<li class="text-success" style="padding:2px; font-weight:bold;color : 21B4FF">COMPLETED BATCHES - <span id="completedBatches">0</span></li>
															</ul>
														</td>-->
													</tr>
												</table>
                                            	
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </section>						
						<section id="sectorRunningSpecialTrainingProgramsDayWiseAtendence">
                            <div class="row">
                            	<div class="col-md-12">
									<div class="panel panel-default panel-black" id="heading8">
										<div class="panel-heading bg_d">
											
											<div class="row">
												<div class="col-sm-12">
													<a class="panelCollapseIcon" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse8" aria-expanded="true" aria-controls="collapse8">
														<h4 class="panel-title text-bold"> <span style="color:green;font-weight:bold;"> SPECIAL TRAININGS </span>- BATCHES ATTENDENCE &nbsp;&nbsp;&nbsp;&nbsp;<span style="font-weight:bold;"> IA-Invitee Attended, NIA-Non Invitee Attended</span></h4>
													</a>
												</div>
											</div>
										</div>
										<div id="collapse8" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading8">
											<div class="panel-body pad_0" id="runningSpecialTrainingProgramsDayWiseAttendence">
										</div>
										</div>
									</div>
								</div>
                            </div>
                        </section>
						
						<section id="sectorRunningTrainingProgramsDayWiseAtendence" >
                            <div class="row">
                            	<div class="col-md-12">
								<div class="panel panel-default panel-black" id="heading9">
									<div class="panel-heading bg_d">
										
										<div class="row">
											<div class="col-sm-12">
												<a class="panelCollapseIcon collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse9" aria-expanded="true" aria-controls="collapse9">
													<h4 class="panel-title text-bold">REGULAR TRAININGS - CURRENTLY RUNNING BATCHES ATTENDENCE &nbsp;&nbsp;&nbsp;&nbsp;<span style="font-weight:bold;"> IA-Invitee Attended, NIA-Non Invitee Attended</span></h4>
												</a>
											</div>
										</div>
									</div>
									<div id="collapse9" class="panel-collapse collapse " role="tabpanel" aria-labelledby="heading9">
										<div class="panel-body pad_0" id="runningTrainingProgramsDayWiseAttendence">
										</div>
									</div>
								</div>
					            </div>
                            </div>
                        </section>
						
						 <section>
                            <div class="row">
                            	<div class="col-md-12">
								<div class="panel panel-default panel-black" id="heading10">
									<div class="panel-heading bg_d">
										<div class="row">
											<div class="col-sm-12">
												<a class="panelCollapseIcon collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse10" aria-expanded="true" aria-controls="collapse10">
													<h4 class="panel-title text-bold">CURRENTLY RUNNING TRAINING PROGRAMS</h4>
												</a>
											</div>
										</div>
									</div>
									<div id="collapse10" class="panel-collapse collapse " role="tabpanel" aria-labelledby="heading10">
										<div class="panel-body pad_0" id="runningTrainingPrograms">
                                        </div>
									</div>
								</div>
                                </div>
                            </div>
                        </section>
						
						<section>
                            <div class="row">
                            	<div class="col-md-12">
									<div class="panel panel-default panel-black" id="heading7">
										<div class="panel-heading bg_d">
											
											<div class="row">
												<div class="col-sm-12">
													<a class="panelCollapseIcon collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse7" aria-expanded="true" aria-controls="collapse7">
														<h4 class="panel-title text-capital">ALL TRAINING PROGRAM WISE DETAILS</h4>
													</a>
												</div>
											</div>
										</div>
										<div id="collapse7" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading7">
											<div class="panel-body">
												<div class="table-responsive table-scroll" id="allProgramWiseDetailsDIv"> </div>
											</div>
										</div>
									</div>
				                </div>
                            </div>
                        </section>
						
						<section>
                            <div class="row">
							
                            	<div class="col-md-12">
								
								<div class="panel panel-default panel-black" id="heading11">
									<div id="feedBackCountMainDivId">
										<div class="panel-heading bg_d">
											
											<div class="row">
												<div class="col-sm-12">
													<a class="panelCollapseIcon collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse11" aria-expanded="true" aria-controls="collapse11">
														<h4 class="panel-title text-capital">TRAINED CADRE AND FEEDBACK DETAILS</h4>
													</a>
												</div>
											</div>
										</div>
										<div id="collapse11" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading11">
											<div class="panel-body pad_0">
												<img id="feefbackLoadingImgId" style="width: 45px; height: 45px; margin-left: 45%; display: none;" src="images/ajaxImg2.gif">
												<div style="" id="feedBackCountId"></div>
											</div>
										</div>
									</div>
								</div>
									<!-- ara-->
									<div class="panel panel-default" id="headingTwo">
											<div class="panel-heading bg_d">
												
												<div class="row">
													<div class="col-sm-12">
														<a class="panelCollapseIcon collapseActiveDistCls collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
															
															<h4 class="panel-title text-capital">SPEAKERS ATTENDANCE <span class="col-md-offset-3" id="spekrsTotalDiv"></span><img id="refrshButtonId" alt="refresh icon" class="tiled-icon" style="margin-left:-265px;max-width: 128px; max-height: 22px;" title="Click Here To get Updated Speaker Details" src="images/refresh.png"></h4>
														</a>
													</div>
												</div>
											</div>
											<div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
												<div class="panel-body">
													<div class="row">
														<div class="col-sm-6 col-">
															<input type="radio" checked name="filterRadio" value="today" class="filterRadio"/>
															<label>&nbsp;Today</label>
															<input type="radio" name="filterRadio" value="fifteen" class="filterRadio"/>
															<label>&nbsp;15 Days</label>
															<input type="radio" name="filterRadio" value="thirty" class="filterRadio"/>
															<label>&nbsp;30 Days</label>
															<input type="radio" name="filterRadio" value="all" class="filterRadio"/><label>&nbsp;All</label>
														</div>
														<div class="col-sm-6">
															<input type="radio" checked name="filtersRadio" value="individual" class="filtersRadio"/><label>&nbsp;Individual</label>
															<input type="radio" name="filtersRadio" value="count" class="filtersRadio"/><label>&nbsp;Count </label>
															<input type="radio" name="filtersRadio" value="consolidated" class="filtersRadio"/><label>&nbsp;Consolidated</label>
														</div>
													</div>
													
													<div><img id="speakersAttendenceImg" style="width: 45px; height: 45px; margin-left: 45%; display: none;" src="images/ajaxImg2.gif"></div>
													<div id="speakersAttendence" class="table-responsive"></div>
												</div>
											</div>
										</div>
                                </div>
                            </div>
                        </section>
						<div class="pull-right font-13"><span style="color:red">*</span>
							<span style="font-weight:bold;">C-Confirmed, IA-Invitee Attended, NIA-Non Invitee Attended</span>
						</div>
                    	
						<div class="pull-right font-13"><span style="color:red">*</span>
							<span style="font-weight:bold;">C-Confirmed, IA-Invitee Attended, NIA-Non Invitee Attended</span>
						</div>
                        <section>
                            <div class="row">
                            	<div class="col-md-12">
									<div class="panel panel-default panel-black" id="headingThree">
										<div class="panel-heading bg_d">
											
											<div class="row">
												<div class="col-sm-12">
													<a class="panelCollapseIcon collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="true" aria-controls="collapseThree">
														<h4 class="panel-title text-capital">UPCOMING TRAINING PROGRAM DETAILS</h4>
													</a>
												</div>
											</div>
										</div>
										<div id="collapseThree" class="panel-collapse collapse " role="tabpanel" aria-labelledby="headingThree">
											<div class="panel-body">
												<div class="" id="upComingTrainingPrograms">
											</div>
											</div>
										</div>
									</div>
					            </div>
                            </div>
                        </section>
						<div class="pull-right font-13"><span style="color:red">*</span>
							<span style="font-weight:bold;">C-Confirmed, IA-Invitee Attended, NIA-Non Invitee Attended</span>
						</div>
                       
						<div class="pull-right font-13"><span style="color:red">*</span>
							<span style="font-weight:bold;">C-Confirmed, IA-Invitee Attended, NIA-Non Invitee Attended</span>
						</div>
                        <section>
                            <div class="row">
                            	<div class="col-md-12">
									<div class="panel panel-default" id="headingFour">
										<div class="panel-heading bg_d">
											
											<div class="row">
												<div class="col-sm-12">
													<a class="panelCollapseIcon collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="true" aria-controls="collapseFour">
														<h4 class="panel-title text-capital">COMPLETED TRAINING PROGRAMS</h4>
													</a>
												</div>
											</div>
										</div>
										<div id="collapseFour" class="panel-collapse collapse " role="tabpanel" aria-labelledby="headingFour">
											<div class="panel-body">
												 <div class="" id="completedTrainingPrograms"></div>
                                        	</div>
										</div>
									</div>
                                	
                                </div>
                            </div>
                        </section>
						
                        <section>
                        	<div class="row">
                            	<div class="col-md-12">
									<div class="panel panel-default" id="headingFive">
										<div class="panel-heading bg_d">
											<div class="row">
												<div class="col-sm-12">
													<a class="panelCollapseIcon collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFive" aria-expanded="true" aria-controls="collapseFive">
														<h4 class="panel-title text-capital">PARTY DESIGNATION WISE MEMBERS PARTICIPATED - COMPLETED TRAINING PROGRAMS</h4>
													</a>
												</div>
											</div>
										</div>
										<div id="collapseFive" class="panel-collapse collapse " role="tabpanel" aria-labelledby="headingFive">
											<div class="panel-body">
												<img id="partyDesgWiseImg" src="images/ajaxImg2.gif" style="width:45px;height:45px;margin-left:45%">
												<div id="partyDesgWisedivId"></div>
											
											</div>
										</div>
									</div>
								</div>
                            </div>
                        </section>
						
                        <section>
                        	<div class="row">
								<div class="col-md-12">
								<div class="panel panel-default" id="heading6">
										<div class="panel-heading bg_d">
											<div class="row">
												<div class="col-sm-12">
													<a class="panelCollapseIcon collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse6" aria-expanded="true" aria-controls="collapse6">
														<h4 class="panel-title">
															<b><span id="headingDiv">DISTRICT </span>WISE TOTAL MEMBERS PARTICIPATED</b>
															<div class="pull-right">
																<input type="radio" name="distConst" class="constDistRadio" checked value="dist"><label>District</label>
																<input type="radio" name="distConst" class="constDistRadio" value="const"><label>Constituency</label>
															</div>
														</h4>
													</a>
												</div>
											</div>
										</div>
										<div id="collapse6" class="panel-collapse collapse " role="tabpanel" aria-labelledby="heading6">
											<div class="panel-body">
												<div class="row">
												<div class="col-md-12" id="distDivId">
													<div class="panel panel-default">
														<!--<div class="panel-heading bg_d">
															<h4 class="panel-title" style="font-weight:bold;">DISTRICTS WISE TOTAL MEMBERS PARTICIPATED</h4>
														</div>-->
														<div class="panel-body pad_0">
														  <img id="distWiseImg" src="images/ajaxImg2.gif" style="width:45px;height:45px;margin-left:45%">
														  <div id="distWiseDivId"></div>
														</div>
													</div>
													
												</div>
												<div class="col-md-12" id="constDivId" style="display:none;">
													<div class="panel panel-default">
														<!--<div class="panel-heading bg_d">
															<h4 class="panel-title" style="font-weight:bold;">CONSTITUENCY WISE TOTAL MEMBERS PARTICIPATED</h4>
														</div>-->
														
														<div class="panel-body pad_0">
														  <img id="constWiseImg" src="images/ajaxImg2.gif" style="width:45px;height:45px;margin-left:45%">
														  <div id="constWiseDivId"></div>
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
            </div>
        </div>
    </div>
<!-- feedback count Div-->

</main>
<footer>
		<p class="text-center">All &copy; 2015. Telugu Desam Party</p>
</footer>

<div id="resultModalId" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
  <div class="modal-dialog modal-lg">
    <div class="modal-content" >
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel"> Speaker Attendance Details </h4>
      </div>
	  <div class="modal-body">
        <div id="resultInnerDIvId">
			<img id="modalLoadingImg" style="width: 45px; height: 45px; margin-left: 45%; display: none;" src="images/ajaxImg2.gif">
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <!-- <button type="button" class="btn btn-primary">Save changes</button>-->
      </div>
    </div>
  </div>
</div>

 <!-- model -->
<div class="modal fade" id="myModalForCadreId">
  <div class="modal-dialog" style="width:60%">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="cadreTitleId"></h4>
      </div>
      <div class="modal-body">
        <div class="row">
			<div class="col-md-12">
				<div style="background: rgba(0, 0, 0, 0.1) none repeat scroll 0% 0%; border: medium none transparent; margin-bottom: 2px;" class="well well-sm">
				<center><img id="dataLoadingsImgForCadrePopUpId" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;margin-top:50px;"/></center>
				<div id="popupForCadreDetailsId"></div>	
				</div>
			</div>
		</div>
      </div>
      <div class="modal-footer">
       <button type="button" class="btn btn-default btn-success btn-sm" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="totalCampDetailsModalId" role="dialog">
    <div class="modal-dialog" style="width:90%;">
		<div class="modal-content">
			<div class="modal-header">
			  <button type="button" class="close" data-dismiss="modal">&times;</button>
			  <h3 class="modal-title">Training  Center Details</h3>
			</div>
			<div class="modal-body">
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="table" id="taingingCampsDivId"></div>
					</div>
				 </div>
			</div>
			<div class="modal-footer">
			  <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
        </div>
      
    </div>
  </div>
<div class="modal fade" id="totalprogramDetailsModalId" role="dialog">
	<div class="modal-dialog" style="width:80%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h3 class="modal-title">Training  Programs</h3>
			</div>
			<div class="modal-body">
				<div class="table text-center" id="taingingProgrmsDivId"></div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<script src="training/dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="training/dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/DataTable_NewVersion/dataTable.js" type="text/javascript"></script>
<script type="text/javascript" src="training/dist/scroll/jquery.mCustomScrollbar.js"></script>
<script type="text/javascript" src="training/dist/scroll/jquery.mousewheel.js"></script>
<script src="training/dist/DateRange/moment.js" type="text/javascript"></script>
<script src="training/dist/DateRange/daterangepicker.js" type="text/javascript"></script>
<script src="training/dist/HighCharts/highcharts.js" type="text/javascript"></script>
<!-- scrollator -->
	<script type="text/javascript" src="js/scrollator/fm.scrollator.jquery.js"></script>
	<script type="text/javascript" src="dist/scroll/jquery.mCustomScrollbar.js"></script>
	<script type="text/javascript" src="dist/scroll/jquery.mousewheel.js"></script>
<script type="text/javascript">
var fromTypeGlob = "onLoad" ;
onLoadCalls(fromTypeGlob);
function onLoadCalls(fromTypeGlob){

	getTrainingCenterDetailsBasedOnDates(fromTypeGlob);
	getAttendedCountForBatchesByLocation(fromTypeGlob);
	getInvitedAttendedCadreCountByBatchIds(fromTypeGlob);
	getDayWiseCountsForRunningBatches(fromTypeGlob);
	getAttendenceForTrainers("today");
	getFeedBackCountsOfTraining(fromTypeGlob);
	getAllTrainingCampDetails(fromTypeGlob);
}

$(document).on('click', '.applyBtn', function(){
	fromTypeGlob="onClick";
	onLoadCalls(fromTypeGlob);
});

$(document).on("click",".ranges li",function(){
	fromTypeGlob="onClick";
	onLoadCalls(fromTypeGlob);
});

$(document).on('change', '#trainingEnrlmntYrId', function(){
	fromTypeGlob="change";
	onLoadCalls(fromTypeGlob);
});

$(function () {
	var cb = function(start, end, label) {
	//console.log(start.toISOString(), end.toISOString(), label);
	$('#selectDate span').html(start.format('D MMMM, YYYY')- + ' - ' + end.format('D MMMM, YYYY'));
	//alert("Callback has fired: [" + start.format('MMMM D, YYYY') + " to " + end.format('MMMM D, YYYY') + ", label = " + label + "]");
  }
  var optionSet1 = {
	startDate: moment().startOf('month'),
	endDate: moment().endOf('month'),
	showDropdowns: true,
	showWeekNumbers: true,
	timePicker: false,
	timePickerIncrement: 1,
	timePicker12Hour: true,
	ranges: {
	   //'Today': [moment(), moment()],
	   //'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	   //'Last 7 Days': [moment().subtract(6, 'days'), moment()],
	   'Last 30 Days': [ moment().subtract(30, 'days'),moment()],
	   'Lat 60 Days': [moment().subtract(60, 'days'),moment()],
	   'Last 180 Days': [moment().subtract(6, 'months'),moment()],
	   'Last 365 Days': [moment().subtract(1, 'year'),moment()],
	   'This Month': [moment().startOf('month'), moment().endOf('month')],
	   //'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
	},
	opens: 'left',
	buttonClasses: ['btn btn-default'],
	applyClass: 'btn-small btn-primary',
	cancelClass: 'btn-small',
	format: 'DD/MM/YYYY',
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
  $('#selectDate span').html(moment().subtract(29, 'days').format('D MMMM, YYYY') + ' - ' + moment().format('D MMMM, YYYY'));

  $('#selectDate').daterangepicker(optionSet1, cb);

  $('#selectDate').on('show.daterangepicker', function() { console.log("show event fired"); });
  $('#selectDate').on('hide.daterangepicker', function() { console.log("hide event fired"); });
  /*$('#selectDate').on('apply.daterangepicker', function(ev, picker) { 
	 console.log("apply event fired, start/end dates are " 
	  + picker.startDate.format('D MMMM, YYYY') 
	  + " to " 
	  + picker.endDate.format('D MMMM , YYYY')
	);  
  });*/
  $('#selectDate').on('cancel.daterangepicker', function(ev, picker) { console.log("cancel event fired"); });
	/*fromTypeGlob="onLoad";
	getTrainingCenterDetailsBasedOnDates("onLoad");
	getAttendedCountForBatchesByLocation("onLoad");
	getInvitedAttendedCadreCountByBatchIds("onLoad");
	getDayWiseCountsForRunningBatches("onLoad");
	getAttendenceForTrainers("today");
	//$(".ranges ul li:nth-child(5)").trigger("click");
	getFeedBackCountsOfTraining("onLoad");
	getAllTrainingCampDetails("onLoad");*/	
});

 $("#mainheading").html(" TRAINING CENTER DASHBOARD ");
 
function getTrainingCenterDetailsBasedOnDates(fromType){
	$("#allProgramWiseDetailsDIv").html('<img id="" style="width: 45px; height: 45px; margin-left: 45%;" src="images/ajaxImg2.gif">');
	$("#donutchart").html('<img id="" style="width: 45px; height: 45px; margin-left: 45%;" src="images/ajaxImg2.gif">');
	var dates;
	if(fromType=="onLoad" || fromType=="change"){
		dates="";
	}else{
		dates=$("#selectDate").val();
	}
	var enrollmentYrIds =[];
	enrollmentYrIds.push($("#trainingEnrlmntYrId").val());
	if(fromType=="change"){
		enrollmentYrIds.length = 0;
		enrollmentYrIds.push($("#trainingEnrlmntYrId").val());
	}
	var programIds =[];
	if(enrollmentYrIds == 4){
		programIds.push(8,9);
	}else{
		programIds.push(1,6,7,5);
	}
	var jsObj=
	{	
		selectedDate : dates,
		enrollmentYearIdsList :enrollmentYrIds,
		programIdsList : programIds
	}
    $.ajax({
          type:'GET',
          url: 'getTrainingCenterDetailsBasedOnDatesAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
		$("#totalTariningPrograms").html("0");
		$("#totalTrainingCenters").html("0");
		//$("#totalMembers").html("0");
		//$("#upcomingMembers").html("0");
		//$("#runningMembers").html("0");
		//$("#completedMembers").html("0");
		$("#totalTrainingBatches").html("0");
		$("#upcomingBatches").html("0");
		$("#runningBatches").html("0");
		$("#completedBatches").html("0");
	   if(result!=null && result.completed!=null && result.completed.programDetails!=null && result.completed.programDetails.length>0){
		  var str='';
			str+='<table class="table table-bordered m_0">';
			str+='<thead class="bg_d">';
			str+='<th>TRAINING PROGRAM NAME</th>';
			str+='<th>TRAINING CENTER</th>';
			str+='<th>TRAINING SCHEDULE</th>';
			str+='<th>BATCH NAME</th>';
			str+='<th>TRAINING ON</th>';
			str+='<th>MEMBERS ACCEPTED</th>';
			str+='</thead>';
			str+='<tbody>';	
			
			var obj={};
			
			//for programwise batch count
			for(var i in result.completed.programDetails){
				var temp=0;;
				var myResult = result.completed.programDetails;
				if(result.completed.completedBatchIds!=null){
					for(var j in myResult[i].campDetails){
						for(var k in myResult[i].campDetails[j].scheduleDetails){
							var schedule = myResult[i].campDetails[j].scheduleDetails[k];
							for(var m in schedule.batchDetails){
								temp=temp+1;
							}
						}
					}
				}
				obj[""+myResult[i].programId+""]=temp;
			}
			
			for(var i in result.completed.programDetails){
				var myResult = result.completed.programDetails;
				if(result.completed.completedBatchIds!=null){
					str+="<tr>";
					str+="<td rowspan="+obj[myResult[i].programId]+" class='summaryCls' attr_prog_id='"+myResult[i].programId+"' attr_batch_id='0' attr_from='c' attr_camp_id='0' style='cursor:pointer' title='Click Here TO View Program Summary'>"+myResult[i].programName+"</td>";
					for(var j in myResult[i].campDetails){
						var campspan=myResult[i].campDetails[j].cmpBatchCount;
						str+="<td rowspan="+campspan+" class='summaryCls' attr_prog_id='"+myResult[i].programId+"' attr_camp_id='"+myResult[i].campDetails[j].campId+"' attr_from='c' attr_batch_id='0' style='cursor:pointer' title='Click Here View Camp Summary'>"+myResult[i].campDetails[j].campName+"</td>";
							for(var k in myResult[i].campDetails[j].scheduleDetails){
								
								var schedule = myResult[i].campDetails[j].scheduleDetails[k];
								if(schedule.scheduleCode != null){
								str+="<td rowspan="+schedule.batchDetails.length+">"+schedule.scheduleCode+"</td>";
									for(var m in schedule.batchDetails){
										str+="<td class='summaryCls' title='Click Here to View Batch Summary' style='cursor:pointer' attr_batch_id='"+schedule.batchDetails[m].batchId+"' attr_prog_id='"+myResult[i].programId+"' attr_from='c' attr_camp_id='"+myResult[i].campDetails[j].campId+"'>"+schedule.batchDetails[m].batchName+"</td>";
										str+='<td>'+schedule.batchDetails[m].batchDates+'</td>';
										
										if(myResult[i].campDetails[j].campId != null && parseInt(myResult[i].campDetails[j].campId)==7){
											var count = parseInt(schedule.batchDetails[m].completedMemberCount)+parseInt(schedule.batchDetails[m].completedAttendenceNonIn)
											str+='<td><b>'+count+'</b> IA</td>';
										}
										else{
											str+='<td><b>'+schedule.batchDetails[m].completedMemberAttendeeCount+'</b> C - <b>'+schedule.batchDetails[m].completedMemberCount+'</b> IA - <b>'+schedule.batchDetails[m].completedAttendenceNonIn+'</b> NIA</td>';
										}
										
										str+="</tr>";
									}
									}
								str+="</tr>";
								
							}
						str+="</tr>";
					}
				}
			}
			str+='</tbody>';
			str+='</table>';
			
			$("#completedTrainingPrograms").html(str);
		}else{
			$("#completedTrainingPrograms").html("<h4 style='font-weight:bold;margin-left:10px;'>No Completed Training Programs Are Available</h4>");
		}
		
		if(result!=null && result.running!=null && result.running.programDetails!=null && result.running.programDetails.length>0){
						
			var str='';
			str+='<table class="table table-bordered m_0">';
			str+='<thead class="bg_d">';
			str+='<th>TRAINING PROGRAM NAME</th>';
			str+='<th>TRAINING CENTER</th>';
			str+='<th>TRAINING SCHEDULE</th>';
			str+='<th>BATCH NAME</th>';
			str+='<th>TRAINING ON</th>';
			str+='<th>MEMBERS ACCEPTED</th>';
			str+='</thead>';
			str+='<tbody>';	
			
			var obj={};
			//for programwise batch count
			for(var i in result.running.programDetails){
				var temp=0;;
				var myResult = result.running.programDetails;
				if(result.running.runningBatchIds!=null){
					for(var j in myResult[i].campDetails){
						for(var k in myResult[i].campDetails[j].scheduleDetails){
							var schedule = myResult[i].campDetails[j].scheduleDetails[k];
							for(var m in schedule.batchDetails){
								temp=temp+1;
							}
						}
					}
				}
				obj[""+myResult[i].programId+""]=temp;
			}
			
			for(var i in result.running.programDetails){
				var myResult = result.running.programDetails;
				str+="<tr>";
					str+="<td rowspan="+obj[myResult[i].programId]+" class='summaryCls' attr_prog_id='"+myResult[i].programId+"' attr_batch_id='0' attr_from='r' attr_camp_id='0' style='cursor:pointer' title='Click Here TO View Program Summary'>"+myResult[i].programName+"</td>";
					for(var j in myResult[i].campDetails){
						var campspan=myResult[i].campDetails[j].cmpBatchCount;
						str+="<td rowspan="+campspan+" class='summaryCls' attr_prog_id='"+myResult[i].programId+"' attr_camp_id='"+myResult[i].campDetails[j].campId+"' attr_from='r' attr_batch_id='0' style='cursor:pointer' title='Click Here View Camp Summary'>"+myResult[i].campDetails[j].campName+"</td>";
							for(var k in myResult[i].campDetails[j].scheduleDetails){
								var schedule = myResult[i].campDetails[j].scheduleDetails[k];
								str+="<td rowspan="+schedule.batchDetails.length+">"+schedule.scheduleCode+"</td>";
									for(var m in schedule.batchDetails){
										str+="<td class='summaryCls' title='Click Here to View Batch Summary' style='cursor:pointer' attr_batch_id='"+schedule.batchDetails[m].batchId+"' attr_prog_id='"+myResult[i].programId+"' attr_from='r' attr_camp_id='"+myResult[i].campDetails[j].campId+"'>"+schedule.batchDetails[m].batchName+"</td>";
										str+='<td>'+schedule.batchDetails[m].batchDates+'</td>';
										if(schedule.batchDetails[m].runningMemberCount!=null){
											
											if(myResult[i].campDetails[j].campId == 7){
												var totalCount = parseInt(schedule.batchDetails[m].runningAttendenceMemberCount)+parseInt(schedule.batchDetails[m].runningAttendenceNonIn);
												str+='<td><b>'+totalCount+'</b> IA</td>';
											}else{
												str+='<td><b>'+schedule.batchDetails[m].runningMemberCount+'</b> C - <b>'+schedule.batchDetails[m].runningAttendenceMemberCount+'</b> IA - <b>'+schedule.batchDetails[m].runningAttendenceNonIn+'</b> NIA</td>';
											}
										}else{
											str+='<td>0</td>';
										}
										
										str+="</tr>";
									}
								str+="</tr>";
							}
						str+="</tr>";
					}
				
			}
			str+='</tbody>';
			str+='</table>';
			
			$("#runningTrainingPrograms").html(str);
		}else{
			$("#runningTrainingPrograms").html("<h4 style='font-weight:bold;margin-left:10px;'>No Running Training Programs Are Available</h4>");
		}
		
		/*//day wise attendence for currently running batches
		if(result!=null && result.completed!=null && result.completed.simpleVoList!=null && result.completed.simpleVoList.length>0){
			var str='';
			str+='<table class="table table-bordered">';
			str+='<thead class="bg_d">';
			str+='<tr>';
			str+='<th>Center</th>';
			str+='<th>Batch</th>';
			str+='<th>Day 1 Count</th>';
			str+='<th>Day 2 Count</th>';
			str+='<th>Day 3 Count</th>';
			str+='<th>1 Day Attended Cadres</th>';
			str+='<th>2 Days Attended Cadres</th>';
			str+='<th>3 Days Attended Cadres</th>';
			str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
			for(var i in result.completed.simpleVoList){
				str+='<tr>';
				str+='<td>'+result.completed.simpleVoList[i].centerName+'</td>';
				str+='<td>'+result.completed.simpleVoList[i].batchName+'</td>';
				for(var j in result.completed.simpleVoList[i].simpleVOList1){
					if(result.completed.simpleVoList[i].simpleVOList1[j].total!=null){
						str+='<td>'+result.completed.simpleVoList[i].simpleVOList1[j].total+'</td>'
					}else{
						str+='<td>0</td>'
					}
				}
				str+='<td>'+result.completed.simpleVoList[i].day1Count+'</td>';
				str+='<td>'+result.completed.simpleVoList[i].day2Count+'</td>';
				str+='<td>'+result.completed.simpleVoList[i].day3Count+'</td>';
				str+='</tr>';
			}
			str+='</tbody>';
			str+='</table>';
			$("#runningTrainingProgramsDayWiseAttendence").html(str);
		}else{
			$("#runningTrainingProgramsDayWiseAttendence").html("<h4 style='font-weight:bold;margin-left:10px;'>No Data Available</h4>");
		}*/
		
		if(result!=null && result.upcoming!=null && result.upcoming.programDetails!=null && result.upcoming.programDetails.length>0){
			var str='';
			str+='<table class="table table-bordered m_0">';
			str+='<thead class="bg_d">';
			str+='<th>TRAINING PROGRAM NAME</th>';
			str+='<th>TRAINING CENTER</th>';
			str+='<th>TRAINING SCHEDULE</th>';
			str+='<th>BATCH NAME</th>';
			str+='<th>TRAINING ON</th>';
			str+='<th>MEMBERS ACCEPTED</th>';
			str+='</thead>';
			str+='<tbody>';	

			var obj={};
			//for programwise batch count
			for(var i in result.upcoming.programDetails){
				var temp=0;;
				var myResult = result.upcoming.programDetails;
				if(result.upcoming.upComingBatchIds!=null){
					for(var j in myResult[i].campDetails){
						for(var k in myResult[i].campDetails[j].scheduleDetails){
							var schedule = myResult[i].campDetails[j].scheduleDetails[k];
							for(var m in schedule.batchDetails){
								temp=temp+1;
							}
						}
					}
				}
				obj[""+myResult[i].programId+""]=temp;
			}
			
			for(var i in result.upcoming.programDetails){
				var myResult = result.upcoming.programDetails;
				if(result.upcoming.upComingBatchIds!=null){
					str+="<tr>";
					str+="<td rowspan="+obj[myResult[i].programId]+" class='summaryCls' attr_prog_id='"+myResult[i].programId+"' attr_batch_id='0' attr_from='u' attr_camp_id='0' style='cursor:pointer' title='Click Here TO View Program Summary'>"+myResult[i].programName+"</td>";
					for(var j in myResult[i].campDetails){
						var campspan=myResult[i].campDetails[j].cmpBatchCount;
						str+="<td rowspan="+campspan+" class='summaryCls' attr_prog_id='"+myResult[i].programId+"' attr_camp_id='"+myResult[i].campDetails[j].campId+"' attr_from='u' attr_batch_id='0' style='cursor:pointer' title='Click Here View Camp Summary'>"+myResult[i].campDetails[j].campName+"</td>";
							for(var k in myResult[i].campDetails[j].scheduleDetails){
								var schedule = myResult[i].campDetails[j].scheduleDetails[k];
								str+="<td rowspan="+schedule.batchDetails.length+">"+schedule.scheduleCode+"</td>";
									for(var m in schedule.batchDetails){
										str+="<td class='summaryCls' title='Click Here to View Batch Summary' style='cursor:pointer' attr_batch_id='"+schedule.batchDetails[m].batchId+"' attr_prog_id='"+myResult[i].programId+"' attr_from='u' attr_camp_id='"+myResult[i].campDetails[j].campId+"'>"+schedule.batchDetails[m].batchName+"</td>";
										str+='<td>'+schedule.batchDetails[m].batchDates+'</td>';
										if(schedule.batchDetails[m].upCommingMemberCount!=null){
											str+='<td><b>'+schedule.batchDetails[m].upCommingMemberCount+'</b> C</td>';
										}else{
											str+='<td>0</td>';
										}
										
										str+="</tr>";
									}
								str+="</tr>";
							}
						str+="</tr>";
					}
				}
				
				
			}
			str+='</tbody>';
			str+='</table>';
			
			$("#upComingTrainingPrograms").html(str);
		}else{
			$("#upComingTrainingPrograms").html("<h4 style='font-weight:bold;margin-left:10px;'>No Upcomming Training Programs Are Available</h4>");
		}
		
		
		if(result!=null && result.completed!=null && result.completed.programWiseDetails!=null && result.completed.programWiseDetails.length>0){
			var totalMembersGlob=0,upcomingBatchesGlob=0,compltedBatchesGlob=0,runningBatchesGlob=0,upcomingMemGlob=0,upcomingMemGlob1=0,upcomingMemGlob2=0,runningMemGlob=0,runningMemGlob1=0,runningMemGlob2=0,compltedMemGlob=0,compltedMemGlob1=0,compltedMemGlob2=0,totalTrainingBatchesGlob=0;
			var str='';
			
			str+='<table class="table table-bordered m_0">';
			str+='<thead class="bg_d">';
			str+='<th>Training Program Name</th>';
			str+='<th>Training Center Name</th>';
			str+='<th>Total<br/> Batches</th>';
			str+='<th class="text-danger">Upcoming<br/> Batches</th>';
			str+='<th class="text-warning">Running<br/> Batches</th>';
			str+='<th class="text-success">Completed <br/>Batches</th>';
			str+='<th class="text-muted">Total <br/>Members</th>';
			str+='</thead>';
			str+='<tbody>';
			$("#totalTariningPrograms").html(result.completed.programWiseDetails.length);
			$("#totalTrainingCenters").html(result.completed.totalTrainingCenters);
			for(var i in result.completed.programWiseDetails){
				str+='<tr>';
				str+='<td class="summaryCls" title="Click Here To View Program Summary" style="Cursor:Pointer" attr_prog_id="'+result.completed.programWiseDetails[i].programId+'" attr_from="all" attr_batch_id="0" attr_camp_id="0" rowspan='+result.completed.programWiseDetails[i].campDetails.length+'>'+result.completed.programWiseDetails[i].programName+'</td>';
				if(result.completed.programWiseDetails[i].campDetails!=null && result.completed.programWiseDetails[i].campDetails.length>0){
					for(var j in result.completed.programWiseDetails[i].campDetails){
						str+='<td class="summaryCls" title="Click Here TO View Camp Summary" style="cursor:pointer" attr_prog_id="'+result.completed.programWiseDetails[i].programId+'" attr_from="all" attr_batch_id="0" attr_camp_id="'+result.completed.programWiseDetails[i].campDetails[j].campId+'">'+result.completed.programWiseDetails[i].campDetails[j].campName+'</td>';
						
						if(result.completed.programWiseDetails[i].campDetails[j].scheduleDetails!=null && result.completed.programWiseDetails[i].campDetails[j].scheduleDetails.length>0){
							var TotalBatches=0,upcomingDetails=0,upcomingMem=0,upcomingMem1=0,upcomingMem2=0,completedDetails=0,completedMem=0,completedMem1=0,completedMem2=0,runningDetails=0,runningMem=0,runningMem1=0,runningMem2=0,TotalMembers=0,TotalMembers1=0,TotalMembers2=0;
							for(var k in result.completed.programWiseDetails[i].campDetails[j].scheduleDetails){
								
								TotalBatches=TotalBatches+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].upcomingDetails.length+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].runningDetails.length+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].completedDetails.length;
								
								//totalTrainingBatchesGlob=totalTrainingBatchesGlob+TotalBatches;
								totalTrainingBatchesGlob=totalTrainingBatchesGlob+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].upcomingDetails.length+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].runningDetails.length+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].completedDetails.length;
								
								if(result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].upcomingDetails!=null && result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].upcomingDetails.length>0){
									var temp=0;
									var temp1=0;
									var temp2=0;
									for(var l in result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].upcomingDetails){
										temp=temp+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].upcomingDetails[l].memberCount;
										temp1=temp1+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].upcomingDetails[l].memberCountAttendee;
										temp2=temp2+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].upcomingDetails[l].memberCountNonIn;
										
									}
									TotalMembers=TotalMembers+temp;
									TotalMembers1=TotalMembers1+temp1;
									TotalMembers2=TotalMembers2+temp2;
									upcomingMemGlob=upcomingMemGlob+temp;
									upcomingMemGlob1=upcomingMemGlob1+temp1;
									upcomingMemGlob2=upcomingMemGlob2+temp2;
									upcomingMem=upcomingMem+temp;
									upcomingMem1=upcomingMem1+temp1;
									upcomingMem2=upcomingMem2+temp2;
									upcomingBatchesGlob=upcomingBatchesGlob+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].upcomingDetails.length;
									
									upcomingDetails=upcomingDetails+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].upcomingDetails.length;
									
								}
								
								if(result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].runningDetails!=null && result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].runningDetails.length>0){
									var temp=0;
									var temp1=0;
									var temp2=0;
									for(var l in result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].runningDetails){
										temp=temp+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].runningDetails[l].memberCount;
										temp1=temp1+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].runningDetails[l].memberCountAttendee;
										temp2=temp2+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].runningDetails[l].memberCountNonIn;
									}
									TotalMembers=TotalMembers+temp;
									TotalMembers1=TotalMembers1+temp1;
									TotalMembers2=TotalMembers2+temp2;
									runningMemGlob=runningMemGlob+temp;
									runningMemGlob1=runningMemGlob1+temp1;
									runningMemGlob2=runningMemGlob2+temp2;
									runningMem=runningMem+temp;
									runningMem1=runningMem1+temp1;
									runningMem2=runningMem2+temp2;
									runningBatchesGlob=runningBatchesGlob+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].runningDetails.length;
									runningDetails=runningDetails+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].runningDetails.length
									
								}
								
								if(result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].completedDetails!=null && result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].completedDetails.length>0){
									var temp=0;
									var temp1=0;
									var temp2=0;
									for(var l in result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].completedDetails){
										temp=temp+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].completedDetails[l].memberCount;
										temp1=temp1+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].completedDetails[l].memberCountAttendee;
										temp2=temp2+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].completedDetails[l].memberCountNonIn;
									}
									TotalMembers=TotalMembers+temp1;
									TotalMembers1=TotalMembers1+temp;
									TotalMembers2=TotalMembers2+temp2;
									compltedMemGlob=compltedMemGlob+temp;
									compltedMemGlob1=compltedMemGlob1+temp1;
									compltedMemGlob2=compltedMemGlob2+temp2;
									completedMem=completedMem+temp;
									completedMem1=completedMem1+temp1;
									completedMem2=completedMem2+temp2;
									compltedBatchesGlob=compltedBatchesGlob+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].completedDetails.length;
									completedDetails=completedDetails+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].completedDetails.length;
									
								}
								totalMembersGlob=totalMembersGlob+TotalMembers;
								
							}
							
							str+='<td>'+TotalBatches+'</td>';
							str+='<td class="text-danger"><b>'+upcomingDetails+'</b><br>(<b>'+upcomingMem+'</b> C - <b>'+upcomingMem1+'</b> IA - <b>'+upcomingMem2+'</b> NIA)</td>';
							str+='<td class="text-warning"><b>'+runningDetails+'</b><br>(<b>'+runningMem+'</b> C - <b>'+runningMem1+'</b> IA - <b>'+runningMem2+'</b> NIA)</td>';
							str+='<td class="text-success"><b>'+completedDetails+'</b><br>(<b>'+completedMem1+'</b> C - <b>'+completedMem+'</b> IA - <b>'+completedMem2+'</b> NIA)</td>';
							
							if(result.completed.programWiseDetails[i].campDetails[j].campId == 7 ){
								var count = parseInt(TotalMembers1)+parseInt(TotalMembers2);
								str+='<td class="text-muted"><b>'+count+'</b> IA</td>';
							}else {
								str+='<td class="text-muted"><b>'+TotalMembers+'</b> C - <b>'+TotalMembers1+'</b> IA - <b>'+TotalMembers2+'</b> NIA</td>';
							}
						}	
						str+='</tr>';
					}
				}
				str+='</tr>';
			}
			str+='</tbody>';
			str+='</table>';
			//$("#totalMembers").html(upcomingMemGlob+runningMemGlob+compltedMemGlob1);
			//$("#totalMembersIn").html(upcomingMemGlob1+runningMemGlob1+compltedMemGlob);
			//$("#totalMembersNonIn").html(upcomingMemGlob2+runningMemGlob2+compltedMemGlob2);
			//$("#upcomingMembers").html(upcomingMemGlob+" ("+upcomingMemGlob1+" - Members)"+"");
			//$("#runningMembers").html(runningMemGlob+" ("+runningMemGlob1+" - Members)"+"");
			//$("#completedMembers").html(compltedMemGlob1+" ("+compltedMemGlob+" - Members)"+"");
			//$("#upcomingMembers").html(upcomingMemGlob);
			//$("#upcomingMembersAte").html(upcomingMemGlob1);
			//$("#upcomingMembersNonAte").html(upcomingMemGlob2);
			//$("#runningMembers").html(runningMemGlob);
			//$("#runningMembersAte").html(runningMemGlob1);
			//$("#runningMembersNonAte").html(runningMemGlob2);
			//$("#completedMembers").html(compltedMemGlob1);
			//$("#completedMembersAte").html(compltedMemGlob);
			//$("#completedMembersNonAte").html(compltedMemGlob2);
			$("#totalTrainingBatches").html(totalTrainingBatchesGlob);
			$("#upcomingBatches").html(upcomingBatchesGlob);
			$("#runningBatches").html(runningBatchesGlob);
			$("#completedBatches").html(compltedBatchesGlob);
			$("#allProgramWiseDetailsDIv").html(str);
			
			Highcharts.setOptions({
				colors: ['#40b5bf', '#999967', '#089bf8', '#ac69ae' , '#cccccc']
			});
			chart = Highcharts.chart('donutchart', {
				chart: {
					type: 'pie',
					backgroundColor: 'transparent',
					options3d: {
						enabled: false,
						alpha: 30
					}
				},
				 title:{
					 text:'TOTAL BATCHES'
					 },
				legend: {
						enabled: true,
						align: 'right',
						verticalAlign: 'center',
						y:60,
						floating: false,
						backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
						borderColor: '#fff',
						useHTML:true,
						labelFormatter: function () {
							return '<span title="' + this.name + '">' + this.name + ' - ' + this.y + '</span>';
						},
						borderWidth: 0,
						shadow: false
					},
				plotOptions: {
					pie: {
						center: [50, 50],
						innerSize: 60,
						depth: 40,
						dataLabels: {
							enabled: false,
						},
						showInLegend: true
					}, 
				},
				
				series: [{
					name: ' ',
					data: [
						['Upcoming Batches', upcomingBatchesGlob],
						['Running Batches', runningBatchesGlob],
						['Completed Batches', compltedBatchesGlob],
					]
				}]
			});
			chart.setTitle({text: "Total Batches"});//+compltedBatchesGlob+upcomingBatchesGlob+runningBatchesGlob});
		}else{
			$("#allProgramWiseDetailsDIv").html("No Data Found");
		}
   });
}

	function getAttendedCountForBatchesByLocation(fromType){
		
		$('#partyDesgWisedivId').html('');
		
		var dates;
		if(fromType=="onLoad" || fromType=="change"){
			dates="";
		}else{
			dates=$("#selectDate").val();
		}
		var enrollmentYrIds =[];
		enrollmentYrIds.push($("#trainingEnrlmntYrId").val());
	    if(fromType == "change"){
			enrollmentYrIds.length = 0;
		    enrollmentYrIds.push($("#trainingEnrlmntYrId").val());
	     }
		 var programIds =[];
	    if(enrollmentYrIds == 4){
		  programIds.push(8,9);
	     }else{
			programIds.push(1,6,7,5);
			}
		var jObj={
			selectedDate : dates,
			enrollmentYearIdsList :enrollmentYrIds,
			programIdsList : programIds
	     }
		$.ajax({
		   type:'POST',
		   url :'getAttendedCountForBatchesByLocationAction.action',
		   data: {task:JSON.stringify(jObj)},
		}).done(function(result){
			  $('#partyDesgWiseImg').hide();
			  if(result!=null && result.length>0){
				  var str='';
				  str+='<table class="table table-bordered m_0">';
				  for(var i in result){
				    str+='<tr>';
                      str+='<td>'+result[i].name.toUpperCase()+'</td>';
                      str+='<td>'+result[i].districtid+' IA</td>';
					  str+='<td>'+result[i].actualCount+' NIA</td>';
                      str+='</tr>';
				  }
				 str+='</table>';
				 $('#partyDesgWisedivId').html(str);
			  }else{
				   $('#partyDesgWisedivId').html('NO DATA AVAILABLE');
			  }
	    });
	}
	
	function getDayWiseCountsForRunningBatches(fromType){
		$("#sectorRunningTrainingProgramsDayWiseAtendence").show();
		$("#runningSpecialTrainingProgramsDayWiseAttendence").html('<img id="" style="width: 45px; height: 45px; margin-left: 45%;" src="images/ajaxImg2.gif">');
		$("#runningTrainingProgramsDayWiseAttendence").html('<img id="" style="width: 45px; height: 45px; margin-left: 45%;" src="images/ajaxImg2.gif">');
		var dates;
		if(fromType=="onLoad" || fromType=="change"){
			dates="";
		}else{
			dates=$("#selectDate").val();
		}
		var enrollmentYrIds =[];
		enrollmentYrIds.push($("#trainingEnrlmntYrId").val());
	    if(fromType == "change"){
			enrollmentYrIds.length = 0;
		    enrollmentYrIds.push($("#trainingEnrlmntYrId").val());
	     }
		 var programIds =[];
		if(enrollmentYrIds == 4){
			programIds.push(8,9);
		}else{
			programIds.push(1,6,7,5);
		}
		var jObj={
			selectedDate : dates,
			enrollmentYearIdsList :enrollmentYrIds,
			programIdsList : programIds
	     }
		
		$.ajax({
		   type:'POST',
		   url :'getDayWiseCountsForRunningBatchesAction.action',
		   data: {task:JSON.stringify(jObj)},
		}).done(function(result){
			//day wise attendence for currently running batches
			buildSpecialTrainings(result);
			if(result!=null && result.length>0 && result[0].simpleVOList1 != null && result[0].simpleVOList1.length>0){				
						var str='';
						str+='<table class="table table-bordered m_0 text-center">';
						str+='<thead class="bg_d">';
						str+='<tr>';
						str+='<th>Program</th>';
						str+='<th>Center</th>';
						str+='<th>Batch</th>';
						str+='<th>Total</th>';
						str+='<th>Day 1 Count</th>';
						str+='<th>Day 2 Count</th>';				
						str+='<th>Day 3 Count</th>';
						
						str+='<th>1 Day Attended Members</th>';
						str+='<th>2 Days Attended Members</th>';
						str+='<th>3 Days Attended Members</th>';
						
						str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
						
						var myResult = result[0].simpleVOList1;
						for(var i in myResult){
							str+='<tr>';
							str+='<td  rowspan="'+myResult[i].totalBatchesCount+'" > '+myResult[i].progName+' </td>';							
							for(var j in myResult[i].campDetails){
								str+='<td  rowspan="'+myResult[i].campDetails[j].totalBatchesCount+'" > '+myResult[i].campDetails[j].campName+' </td>';
								for(var k in myResult[i].campDetails[j].batchDetails){
									str+='<td>'+myResult[i].campDetails[j].batchDetails[k].batchName+'</td>';
									
									var totalCount =0;
									var totalIACount =0;
									var totalNIACount =0;
									totalIACount = parseInt(myResult[i].campDetails[j].batchDetails[k].oneDayInvitedAttendedCount)+parseInt(myResult[i].campDetails[j].batchDetails[k].twoDaysInvitedAttendedCount)+parseInt(myResult[i].campDetails[j].batchDetails[k].threeDaysInvitedAttendedCount);
									totalNIACount = parseInt(myResult[i].campDetails[j].batchDetails[k].oneDayNonInvitedAttendedCount)+parseInt(myResult[i].campDetails[j].batchDetails[k].twoDaysNonInvitedAttendedCount)+parseInt(myResult[i].campDetails[j].batchDetails[k].threeDaysNonInvitedAttendedCount);
									totalCount = parseInt(totalIACount)+parseInt(totalNIACount);
									
									
									str+='<td>' +totalIACount+'- IA <br/>'+totalNIACount+'- NIA</td>';
									if(myResult[i].campDetails[j].batchDetails[k].day1IACount!=null){
									 str+='<td>'+myResult[i].campDetails[j].batchDetails[k].day1IACount+'- IA <br/>'+myResult[i].campDetails[j].batchDetails[k].day1NIACount+'- NIA</td>';
									}else{
										str+='<td>0</td>';
									}					
									if(myResult[i].campDetails[j].batchDetails[k].day2IACount!=null){
									 str+='<td>'+myResult[i].campDetails[j].batchDetails[k].day2IACount+'- IA <br/>'+myResult[i].campDetails[j].batchDetails[k].day2NIACount+'- NIA</td>';
									}else{
										str+='<td>0</td>';
									}
									
									if(myResult[i].campDetails[j].batchDetails[k].day3IACount!=null){
									 str+='<td>'+myResult[i].campDetails[j].batchDetails[k].day3IACount+'- IA <br/>'+myResult[i].campDetails[j].batchDetails[k].day3NIACount+'- NIA</td>';
									}else{
										str+='<td>0</td>';
									}
									
									
									str+='<td>';
									if(parseInt(myResult[i].campDetails[j].batchDetails[k].oneDayInvitedAttendedCount)>0)
										str+='<a attr_batchId='+myResult[i].campDetails[j].batchDetails[k].batchId+' attr_dataType="oneDay" attr_type="Invitee" style="cursor:pointer;font-weight:bold;" class="cadreDetailsCls" title="To click on invitee Details"> <u>'+myResult[i].campDetails[j].batchDetails[k].oneDayInvitedAttendedCount+'</u></a> - IA<br/>';
									else 
										str+='0 - IA <br/> ';
									
									if(parseInt(myResult[i].campDetails[j].batchDetails[k].oneDayNonInvitedAttendedCount)>0)
										str+='<a attr_batchId='+myResult[i].campDetails[j].batchDetails[k].batchId+' attr_dataType="oneDay" attr_type="nonInvitee" style="cursor:pointer" class="cadreDetailsCls" title="To click on nonInvitee Details"><u>'+myResult[i].campDetails[j].batchDetails[k].oneDayNonInvitedAttendedCount+'</u></a> - NIA';
									else 
										str+='0 - NIA';							
									str+='</td>';
									
									
									str+='<td>';
									if(parseInt(myResult[i].campDetails[j].batchDetails[k].twoDaysInvitedAttendedCount)>0)
										str+='<a attr_batchId='+myResult[i].campDetails[j].batchDetails[k].batchId+' attr_dataType="twoDay" attr_type="Invitee" style="cursor:pointer" class="cadreDetailsCls"><u>'+myResult[i].campDetails[j].batchDetails[k].twoDaysInvitedAttendedCount+'</u></a> - IA<br/>';
									else 
										str+='0 - IA <br/>';
									
									
									if(parseInt(myResult[i].campDetails[j].batchDetails[k].twoDaysNonInvitedAttendedCount)>0)
										str+='<a attr_batchId='+myResult[i].campDetails[j].batchDetails[k].batchId+' attr_dataType="twoDay" attr_type="nonInvitee" style="cursor:pointer" class="cadreDetailsCls"><u>'+myResult[i].campDetails[j].batchDetails[k].twoDaysNonInvitedAttendedCount+'</u></a> - NIA';
									else 
										str+='0 - NIA';	
									str+='</td>';
									
									str+='<td>';
									if(parseInt(myResult[i].campDetails[j].batchDetails[k].threeDaysInvitedAttendedCount)>0)
										str+='<a attr_batchId='+myResult[i].campDetails[j].batchDetails[k].batchId+' attr_dataType="twoDay" attr_type="Invitee" style="cursor:pointer" class="cadreDetailsCls"><u>'+myResult[i].campDetails[j].batchDetails[k].threeDaysInvitedAttendedCount+'</u></a> - IA<br/>';
									else 
										str+='0 - IA <br/>';
									
									
									if(parseInt(myResult[i].campDetails[j].batchDetails[k].threeDaysNonInvitedAttendedCount)>0)
										str+='<a attr_batchId='+myResult[i].campDetails[j].batchDetails[k].batchId+' attr_dataType="twoDay" attr_type="nonInvitee" style="cursor:pointer" class="cadreDetailsCls"><u>'+myResult[i].campDetails[j].batchDetails[k].threeDaysNonInvitedAttendedCount+'</u></a> - NIA';
									else 
										str+='0 - NIA';	
									str+='</td>';
									
									//if(k<myResult[i].campDetails[j].batchDetails.lenght-1){
										str+='</tr>';
										str+='<tr>';
									//}
								}
								if(j<myResult[i].campDetails.lenght-1){
									str+='</tr>';
									str+='<tr>';
								}
							}
							str+='</tr>';
						}
						
						str+='</tbody>';
						str+='</table>';
						$("#runningTrainingProgramsDayWiseAttendence").html(str);
			}else{
				$("#sectorRunningTrainingProgramsDayWiseAtendence").hide();
				$("#runningTrainingProgramsDayWiseAttendence").html("<h4 style='font-weight:bold;margin-left:10px;'>No Data Available</h4>");
			}
		});
	}
	
	function buildSpecialTrainings(result){
			//day wise attendence for currently running batches
			if(result!=null && result.length>0 && result[0].simpleVOList2 != null && result[0].simpleVOList2.length>0 ){
			
						var str='';
						str+='<table class="table table-bordered m_0 text-center">';
						str+='<thead class="bg_d">';
						str+='<tr>';
						str+='<th>Program</th>';
						str+='<th>Center</th>';
						str+='<th>Batch</th>';
						str+='<th>Total</th>';
						str+='<th>Day 1 Count</th>';
						str+='<th>Day 2 Count</th>';				
						str+='<th>Day 3 Count</th>';
						
						str+='<th>1 Day Attended Members</th>';
						str+='<th>2 Days Attended Members</th>';
						str+='<th>3 Days Attended Members</th>';
						
						str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
						
						var myResult = result[0].simpleVOList2;
						for(var i in myResult){
								str+='<tr>';
								str+='<td  rowspan="'+myResult[i].totalBatchesCount+'" > '+myResult[i].progName+' </td>';							
								for(var j in myResult[i].campDetails){
									str+='<td  rowspan="'+myResult[i].campDetails[j].totalBatchesCount+'" > '+myResult[i].campDetails[j].campName+' </td>';
									for(var k in myResult[i].campDetails[j].batchDetails){
										str+='<td>'+myResult[i].campDetails[j].batchDetails[k].batchName+'</td>';
										
										var totalCount =0;
										var totalIACount =0;
										var totalNIACount =0;
										
										
										totalIACount = parseInt(myResult[i].campDetails[j].batchDetails[k].oneDayInvitedAttendedCount)+parseInt(myResult[i].campDetails[j].batchDetails[k].twoDaysInvitedAttendedCount)+parseInt(myResult[i].campDetails[j].batchDetails[k].threeDaysInvitedAttendedCount);
										totalNIACount = parseInt(myResult[i].campDetails[j].batchDetails[k].oneDayNonInvitedAttendedCount)+parseInt(myResult[i].campDetails[j].batchDetails[k].twoDaysNonInvitedAttendedCount)+parseInt(myResult[i].campDetails[j].batchDetails[k].threeDaysNonInvitedAttendedCount);
										totalCount = parseInt(totalIACount)+parseInt(totalNIACount);
										
										var day1Count = parseInt(myResult[i].campDetails[j].batchDetails[k].day1IACount)+parseInt(myResult[i].campDetails[j].batchDetails[k].day1NIACount);
										var day2Count = parseInt(myResult[i].campDetails[j].batchDetails[k].day2IACount)+parseInt(myResult[i].campDetails[j].batchDetails[k].day2NIACount);
										var day3Count = parseInt(myResult[i].campDetails[j].batchDetails[k].day3IACount)+parseInt(myResult[i].campDetails[j].batchDetails[k].day3NIACount);
										
										
										var oneDayAttendedCount = parseInt(myResult[i].campDetails[j].batchDetails[k].oneDayInvitedAttendedCount)+parseInt(myResult[i].campDetails[j].batchDetails[k].oneDayNonInvitedAttendedCount);
										var twoDaysAttendedCount = parseInt(myResult[i].campDetails[j].batchDetails[k].twoDaysInvitedAttendedCount)+parseInt(myResult[i].campDetails[j].batchDetails[k].twoDaysNonInvitedAttendedCount);
										var threeDaysAttendedCount = parseInt(myResult[i].campDetails[j].batchDetails[k].threeDaysInvitedAttendedCount)+parseInt(myResult[i].campDetails[j].batchDetails[k].threeDaysNonInvitedAttendedCount);
										
										
										
										str+='<td>' +totalCount+'- IA <br/></td>';
										if(day1Count!=null){
										 str+='<td>'+day1Count+'- IA <br/></td>';
										}else{
											str+='<td>0</td>';
										}					
										if(day2Count!=null){
										 str+='<td>'+day2Count+'- IA <br/></td>';
										}else{
											str+='<td>0</td>';
										}	
										
										if(day3Count!=null){
										 str+='<td>'+day3Count+'- IA <br/></td>';
										}else{
											str+='<td>0</td>';
										}	
										
										
										if(oneDayAttendedCount!=null && oneDayAttendedCount >0 ){
										 str+='<td> <a attr_batchId='+myResult[i].campDetails[j].batchDetails[k].batchId+' attr_dataType="oneDay" attr_type="Invitee" style="cursor:pointer" class="cadreDetailsCls"><u>'+oneDayAttendedCount+'- IA </u><br/></td>';
										}else{
											str+='<td>0 - IA </td>';
										}	
										
										if(twoDaysAttendedCount!=null && twoDaysAttendedCount >0 ){
										 str+='<td><a attr_batchId='+myResult[i].campDetails[j].batchDetails[k].batchId+' attr_dataType="twoDay" attr_type="Invitee" style="cursor:pointer" class="cadreDetailsCls"><u>'+twoDaysAttendedCount+'- IA </u><br/></td>';
										}else{
											str+='<td>0 - IA </td>';
										}	
										
										if(threeDaysAttendedCount!=null && threeDaysAttendedCount >0 ){
										 str+='<td><a attr_batchId='+myResult[i].campDetails[j].batchDetails[k].batchId+' attr_dataType="threeDay" attr_type="Invitee" style="cursor:pointer" class="cadreDetailsCls"><u>'+threeDaysAttendedCount+'- IA </u><br/></td>';
										}else{
											str+='<td>0 - IA </td>';
										}	
										
										//if(k<myResult[i].campDetails[j].batchDetails.lenght-1){
											str+='</tr>';
											str+='<tr>';
										//}
									}
									if(j<myResult[i].campDetails.lenght-1){
										str+='</tr>';
										str+='<tr>';
									}
								}
								str+='</tr>';
							
							str+='</tbody>';
							str+='</table>';
							$("#runningSpecialTrainingProgramsDayWiseAttendence").html(str);
						}
			}else{
				$("#sectorRunningSpecialTrainingProgramsDayWiseAtendence").hide();
				$("#runningSpecialTrainingProgramsDayWiseAttendence").html("<h4 style='font-weight:bold;margin-left:10px;'>No Data Available</h4>");
			}
	}
	function getRunningDayWiseCountsForRunningBatches(fromType){
		$("#sectorRunningTrainingProgramsDayWiseAtendence").show();
		$("#runningSpecialTrainingProgramsDayWiseAttendence").html('<img id="" style="width: 45px; height: 45px; margin-left: 45%;" src="images/ajaxImg2.gif">');
		$("#runningTrainingProgramsDayWiseAttendence").html('<img id="" style="width: 45px; height: 45px; margin-left: 45%;" src="images/ajaxImg2.gif">');
		var dates;
		if(fromType=="onLoad" || fromType=="change"){
			dates="";
		}else{
			dates=$("#selectDate").val();
		}
		var enrollmentYrIds =[];
		enrollmentYrIds.push($("#trainingEnrlmntYrId").val());
	    if(fromType == "change"){
			enrollmentYrIds.length = 0;
		    enrollmentYrIds.push($("#trainingEnrlmntYrId").val());
	     }
		 var programIds =[];
		if(enrollmentYrIds == 4){
			programIds.push(8,9);
		}else{
			programIds.push(1,6,7,5);
		}
		var jObj={
			selectedDate : dates,
			enrollmentYearIdsList :enrollmentYrIds,
			programIdsList : programIds
	     }
		
		$.ajax({
		   type:'POST',
		   url :'getDayWiseCountsForRunningBatchesAction.action',
		   data: {task:JSON.stringify(jObj)},
		}).done(function(result){
			//day wise attendence for currently running batches
			if(result!=null && result.length>0){
				buildSpecialTrainings(result);
				var campId = 0;
				var lenght =0;
				for(var i in result){
					if(result[i].campId != 7){
						campId = result[i].campId;
						lenght=parseInt(lenght)+1;
					}
				}				
				
				if(campId != null && parseInt(campId)>0){
						var str='';
						str+='<table class="table table-bordered m_0 text-center">';
						str+='<thead class="bg_d">';
						str+='<tr>';
						str+='<th rowspan="'+lenght+'">Program</th>';
						str+='<th>Center</th>';
						str+='<th>Batch</th>';
						str+='<th>Total</th>';
						str+='<th>Day 1 Count</th>';
						str+='<th>Day 2 Count</th>';				
						str+='<th>Day 3 Count</th>';
						
						str+='<th>1 Day Attended Members</th>';
						str+='<th>2 Days Attended Members</th>';
						str+='<th>3 Days Attended Members</th>';
						
						str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
						var isBuild=false;
						for(var i in result){
							if(result[i].campId != 7){
									str+='<tr>';
									if(!isBuild){
										isBuild=true;
										str+='<td  rowspan="'+lenght+'" > Leadership Skills - 2016 - 18 </td>';
									}
									str+='<td>'+result[i].name+'</td>';
									str+='<td>'+result[i].batchName+'</td>';
									var totalCount =0;
									var totalIACount =0;
									var totalNIACount =0;
									totalIACount = parseInt(result[i].oneDayInvitedAttendedCount)+parseInt(result[i].twoDaysInvitedAttendedCount)+parseInt(result[i].threeDaysInvitedAttendedCount);
									totalNIACount = parseInt(result[i].oneDayNonInvitedAttendedCount)+parseInt(result[i].twoDaysNonInvitedAttendedCount)+parseInt(result[i].threeDaysNonInvitedAttendedCount);
									totalCount = parseInt(totalIACount)+parseInt(totalNIACount);
									
									
									str+='<td>' +totalIACount+'- IA <br/>'+totalNIACount+'- NIA</td>';
									if(result[i].day1IACount!=null){
									 str+='<td>'+result[i].day1IACount+'- IA <br/>'+result[i].day1NIACount+'- NIA</td>';
									}else{
										str+='<td>0</td>';
									}					
									if(result[i].day2IACount!=null){
									 str+='<td>'+result[i].day2IACount+'- IA <br/>'+result[i].day2NIACount+'- NIA</td>';
									}else{
										str+='<td>0</td>';
									}
									
									if(result[i].day3IACount!=null){
									 str+='<td>'+result[i].day3IACount+'- IA <br/>'+result[i].day3NIACount+'- NIA</td>';
									}else{
										str+='<td>0</td>';
									}
									
									
									str+='<td>';
									if(parseInt(result[i].oneDayInvitedAttendedCount)>0)
										str+='<a attr_batchId='+result[i].batchId+' attr_dataType="oneDay" attr_type="Invitee" style="cursor:pointer;font-weight:bold;" class="cadreDetailsCls" title="To click on invitee Details"> <u>'+result[i].oneDayInvitedAttendedCount+'</u></a> - IA<br/>';
									else 
										str+='0 - IA <br/> ';
									
									if(parseInt(result[i].oneDayNonInvitedAttendedCount)>0)
										str+='<a attr_batchId='+result[i].batchId+' attr_dataType="oneDay" attr_type="nonInvitee" style="cursor:pointer" class="cadreDetailsCls" title="To click on nonInvitee Details"><u>'+result[i].oneDayNonInvitedAttendedCount+'</u></a> - NIA';
									else 
										str+='0 - NIA';							
									str+='</td>';
									
									
									str+='<td>';
									if(parseInt(result[i].twoDaysInvitedAttendedCount)>0)
										str+='<a attr_batchId='+result[i].batchId+' attr_dataType="twoDay" attr_type="Invitee" style="cursor:pointer" class="cadreDetailsCls"><u>'+result[i].twoDaysInvitedAttendedCount+'</u></a> - IA<br/>';
									else 
										str+='0 - IA <br/>';
									
									
									if(parseInt(result[i].twoDaysNonInvitedAttendedCount)>0)
										str+='<a attr_batchId='+result[i].batchId+' attr_dataType="twoDay" attr_type="nonInvitee" style="cursor:pointer" class="cadreDetailsCls"><u>'+result[i].twoDaysNonInvitedAttendedCount+'</u></a> - NIA';
									else 
										str+='0 - NIA';							
									str+='</td>';
									
									
									str+='<td>';
									if(parseInt(result[i].threeDaysInvitedAttendedCount)>0)
										str+='<a attr_batchId='+result[i].batchId+' attr_dataType="threeDay" attr_type="Invitee" style="cursor:pointer" class="cadreDetailsCls"><u>'+result[i].threeDaysInvitedAttendedCount+'</u></a> - IA<br/>';
									else 
										str+='0 - IA <br/> ';
								
									
									if(parseInt(result[i].threeDaysNonInvitedAttendedCount)>0)
										str+='<a attr_batchId='+result[i].batchId+' attr_dataType="threeDay" attr_type="nonInvitee" style="cursor:pointer" class="cadreDetailsCls"><u>'+result[i].threeDaysNonInvitedAttendedCount+'</u></a> - NIA';
									else 
										str+='0 - NIA';							
									str+='</td>';
									
									str+='</tr>';
							}				
						}
						
						str+='</tbody>';
						str+='</table>';
						$("#runningTrainingProgramsDayWiseAttendence").html(str);
				}else{
					$("#sectorRunningTrainingProgramsDayWiseAtendence").hide();
				}		
			}else{
				$("#sectorRunningTrainingProgramsDayWiseAtendence").hide();
				$("#runningTrainingProgramsDayWiseAttendence").html("<h4 style='font-weight:bold;margin-left:10px;'>No Data Available</h4>");
			}
		});
	}
	
	function buildRunningSpecialTrainings(result){
		if(result!=null && result.length>0){
				var lenght =0;
				for(var i in result){
					if(result[i].campId == 7){
						lenght=parseInt(lenght)+1;
					}
				}		
				
				var str='';
				str+='<table class="table table-bordered m_0 text-center">';
				str+='<thead class="bg_d">';
				str+='<tr>';
				str+='<th>Training Program </th>';
				str+='<th>Center</th>';
				str+='<th>Batch</th>';
				str+='<th>Total</th>';
				str+='<th>Day 1 Count</th>';
				str+='<th>Day 2 Count</th>';				
				str+='<th>Day 3 Count</th>';
				
				str+='<th>1 Day Attended Members</th>';
				str+='<th>2 Days Attended Members</th>';
				str+='<th>3 Days Attended Members</th>';
				
				str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
				var isBuild = false;
				for(var i in result){
					if(parseInt(result[i].campId) == 7){
							str+='<tr>';
							if(!isBuild){
								isBuild=true;
								str+='<td  rowspan="'+lenght+'" > TNSF Leadership Skills  </td>';
								str+='<td   rowspan="'+lenght+'"  >'+result[i].name+'</td>';
							}
							
							
							str+='<td>'+result[i].batchName+'</td>';
							var totalCount =0;
							var totalIACount =0;
							var totalNIACount =0;
							
							
							totalIACount = parseInt(result[i].oneDayInvitedAttendedCount)+parseInt(result[i].twoDaysInvitedAttendedCount)+parseInt(result[i].threeDaysInvitedAttendedCount);
							totalNIACount = parseInt(result[i].oneDayNonInvitedAttendedCount)+parseInt(result[i].twoDaysNonInvitedAttendedCount)+parseInt(result[i].threeDaysNonInvitedAttendedCount);
							totalCount = parseInt(totalIACount)+parseInt(totalNIACount);
							
							var day1Count = parseInt(result[i].day1IACount)+parseInt(result[i].day1NIACount);
							var day2Count = parseInt(result[i].day2IACount)+parseInt(result[i].day2NIACount);
							var day3Count = parseInt(result[i].day3IACount)+parseInt(result[i].day3NIACount);
							
							
							var oneDayAttendedCount = parseInt(result[i].oneDayInvitedAttendedCount)+parseInt(result[i].oneDayNonInvitedAttendedCount);
							var twoDaysAttendedCount = parseInt(result[i].twoDaysInvitedAttendedCount)+parseInt(result[i].twoDaysNonInvitedAttendedCount);
							var threeDaysAttendedCount = parseInt(result[i].threeDaysInvitedAttendedCount)+parseInt(result[i].threeDaysNonInvitedAttendedCount);
							
							
							
							str+='<td>' +totalCount+'- IA <br/></td>';
							if(day1Count!=null){
							 str+='<td>'+day1Count+'- IA <br/></td>';
							}else{
								str+='<td>0</td>';
							}					
							if(day2Count!=null){
							 str+='<td>'+day2Count+'- IA <br/></td>';
							}else{
								str+='<td>0</td>';
							}	
							
							if(day3Count!=null){
							 str+='<td>'+day3Count+'- IA <br/></td>';
							}else{
								str+='<td>0</td>';
							}	
							
							
							if(oneDayAttendedCount!=null && oneDayAttendedCount >0 ){
							 str+='<td> <a attr_batchId='+result[i].batchId+' attr_dataType="oneDay" attr_type="Invitee" style="cursor:pointer" class="cadreDetailsCls"><u>'+oneDayAttendedCount+'- IA </u><br/></td>';
							}else{
								str+='<td>0 - IA </td>';
							}	
							
							if(twoDaysAttendedCount!=null && twoDaysAttendedCount >0 ){
							 str+='<td><a attr_batchId='+result[i].batchId+' attr_dataType="twoDay" attr_type="Invitee" style="cursor:pointer" class="cadreDetailsCls"><u>'+twoDaysAttendedCount+'- IA </u><br/></td>';
							}else{
								str+='<td>0 - IA </td>';
							}	
							
							if(threeDaysAttendedCount!=null && threeDaysAttendedCount >0 ){
							 str+='<td><a attr_batchId='+result[i].batchId+' attr_dataType="threeDay" attr_type="Invitee" style="cursor:pointer" class="cadreDetailsCls"><u>'+threeDaysAttendedCount+'- IA </u><br/></td>';
							}else{
								str+='<td>0 - IA </td>';
							}	
							
							
							/*
							str+='<td>' +totalIACount+'- IA <br/>'+totalNIACount+'- NIA</td>';
							if(result[i].day1IACount!=null){
							 str+='<td>'+result[i].day1IACount+'- IA <br/>'+result[i].day1NIACount+'- NIA</td>';
							}else{
								str+='<td>0</td>';
							}					
							if(result[i].day2IACount!=null){
							 str+='<td>'+result[i].day2IACount+'- IA <br/>'+result[i].day2NIACount+'- NIA</td>';
							}else{
								str+='<td>0</td>';
							}
							
							if(result[i].day3IACount!=null){
							 str+='<td>'+result[i].day3IACount+'- IA <br/>'+result[i].day3NIACount+'- NIA</td>';
							}else{
								str+='<td>0</td>';
							}
							
							
							str+='<td>';
							if(parseInt(result[i].oneDayInvitedAttendedCount)>0)
								str+='<a attr_batchId='+result[i].batchId+' attr_dataType="oneDay" attr_type="Invitee" style="cursor:pointer;font-weight:bold;" class="cadreDetailsCls" title="To click on invitee Details"> <u>'+result[i].oneDayInvitedAttendedCount+'</u></a> - IA<br/>';
							else 
								str+='0 - IA <br/> ';
							
							if(parseInt(result[i].oneDayNonInvitedAttendedCount)>0)
								str+='<a attr_batchId='+result[i].batchId+' attr_dataType="oneDay" attr_type="nonInvitee" style="cursor:pointer" class="cadreDetailsCls" title="To click on nonInvitee Details"><u>'+result[i].oneDayNonInvitedAttendedCount+'</u></a> - NIA';
							else 
								str+='0 - NIA';							
							str+='</td>';
							
							
							str+='<td>';
							if(parseInt(result[i].twoDaysInvitedAttendedCount)>0)
								str+='<a attr_batchId='+result[i].batchId+' attr_dataType="twoDay" attr_type="Invitee" style="cursor:pointer" class="cadreDetailsCls"><u>'+result[i].twoDaysInvitedAttendedCount+'</u></a> - IA<br/>';
							else 
								str+='0 - IA <br/>';
							
							if(parseInt(result[i].twoDaysNonInvitedAttendedCount)>0)
								str+='<a attr_batchId='+result[i].batchId+' attr_dataType="twoDay" attr_type="nonInvitee" style="cursor:pointer" class="cadreDetailsCls"><u>'+result[i].twoDaysNonInvitedAttendedCount+'</u></a> - NIA';
							else 
								str+='0 - NIA';							
							str+='</td>';
							
							
							str+='<td>';
							if(parseInt(result[i].threeDaysInvitedAttendedCount)>0)
								str+='<a attr_batchId='+result[i].batchId+' attr_dataType="threeDay" attr_type="Invitee" style="cursor:pointer" class="cadreDetailsCls"><u>'+result[i].threeDaysInvitedAttendedCount+'</u></a> - IA<br/>';
							else 
								str+='0 - IA <br/> ';
							
							if(parseInt(result[i].threeDaysNonInvitedAttendedCount)>0)
								str+='<a attr_batchId='+result[i].batchId+' attr_dataType="threeDay" attr_type="nonInvitee" style="cursor:pointer" class="cadreDetailsCls"><u>'+result[i].threeDaysNonInvitedAttendedCount+'</u></a> - NIA';
							else 
								str+='0 - NIA';							
							str+='</td>';
							*/
							str+='</tr>';
					}				
				}
				str+='</tbody>';
				str+='</table>';
				$("#runningSpecialTrainingProgramsDayWiseAttendence").html(str);
		}
	}
	function getAttendenceForTrainers(type){
		var typeStr = $("input[name='filterRadio']:checked").val();
		var searchType = $("input[name='filtersRadio']:checked").val();	
        var enrollmentYrIds =[];
        enrollmentYrIds.push($("#trainingEnrlmntYrId").val());
		if(type == "change"){
			enrollmentYrIds.length = 0;
		    enrollmentYrIds.push($("#trainingEnrlmntYrId").val());
	     }
        var programIds =[];
		if(enrollmentYrIds == 4){
			programIds.push(8,5,9);
		}else{
			programIds.push(1,6,7,5);
		}		
		$("#spekrsTotalDiv").html("");
		var jObj={
			type:typeStr,
			searchType:searchType,
			enrollmentYearIdsList :enrollmentYrIds,
			programIdsList : programIds
		}
		$("#speakersAttendenceImg").show();
		$.ajax({
		   type:'POST',
		   url :'getAttendenceForTrainersAction.action',
		   data: {task:JSON.stringify(jObj)},
		}).done(function(result){
			if(result!=null && result.length>0){
				var str='';
				str+='<table class="table table-bordered" id="speakerTab" >';
				if(searchType == 'count')
				{					
					str+='<thead>';
					str+='<th class="text-center" style="background-color:#dddddd;" >Program</th>';
					str+='<th class="text-center" style="background-color:#dddddd;" >Camp</th>';
					str+='<th class="text-center" style="background-color:#dddddd;" >Invitees Count</th>';
					str+='<th class="text-center" style="background-color:#dddddd;" >Invitees Attended Count</th>';
					str+='<th class="text-center" style="background-color:#dddddd;" >Non-Invitees Attended Count</th>';
					str+='</thead>';				
					str+='<tbody>';
					for(var i in result){
						str+='<tr>';
						str+='<td>'+result[i].progName+'</td>';
						str+='<td>'+result[i].centerName+'</td>';
						str+='<td>'+result[i].inviteeCount+'</td>';
						str+='<td>'+result[i].inviteeAttendedCount+'</td>';
						str+='<td>'+result[i].nonInviteeAttendedCount+'</td>';
						str+='</tr>';
					}
					str+='</tbody>';
					str+='</table>';
					$("#speakersAttendenceImg").hide();
					$("#speakersAttendence").html(str);
					$("#spekrsTotalDiv").html("  ( <b> TOTAL : "+result[0].total+" Members </b> ) ");
				}
				else if(searchType =='individual')
				{	
					str+='<thead>';
					str+='<th class="text-center" style="background-color:#dddddd;" >  </th>';
					str+='<th class="text-center" style="background-color:#dddddd;" > SPEAKER </th>';
					str+='<th class="text-center" style="background-color:#dddddd;" > DESIGNATION </th>';
					if(type == 'today1'){
						str+='<th class="text-center" style="background-color:#dddddd;" >PROGRAM</th>';
						str+='<th class="text-center" style="background-color:#dddddd;" >TRAINING CAMP</th>';					
						str+='<th class="text-center" style="background-color:#dddddd;" >BATCH NAME</th>';
						str+='<th class="text-center" style="background-color:#dddddd;" >  DATE </th>';
					}
					//str+='<th class="text-center"> STATUS </th>';
					str+='</thead>';				
					str+='<tbody>';
					for(var i in result){
						str+='<tr>';
						str+='<td><a href="cadreDetailsAction.action?cadreId='+result[i].id+'" target="_blank" ><img src="https://www.mytdp.com/images/cadre_images/'+result[i].imageStr+'" style="width: 50px;"/></a></td>';
						str+='<td> <b> NAME: </b> <a href="cadreDetailsAction.action?cadreId='+result[i].id+'" target="_blank" >'+result[i].name+' </a><br> ';
						
						if(result[i].status != null)
							str+=' <b>   </b>'+result[i].status+'';
						str+='</td>';
						if(result[i].partyBenefitStr != null)
							str+='<td style="text-align:center;">'+result[i].partyBenefitStr+'</td>';
						else
							str+='<td style="text-align:center;"> - </td>';
						if(type == 'today1'){
							str+='<td>'+result[i].progName+'</td>';
							str+='<td>'+result[i].campName+'</td>';
							if(type == 'today')
								str+='<td>'+result[i].batchName+'</td>';
							if(result[i].dateString != null)
								str+='<td style="text-align:center;"> '+result[i].dateString+' </td>';
							else
								str+='<td style="text-align:center;"> - </td>';
						}
						//if(result[i].inviteeAttendedCount != null && result[i].inviteeAttendedCount>0)
						//	str+='<td style="text-align:center;"> ATTENDED </td>';
						//else
						//	str+='<td style="text-align:center;"> ABSENT </td>';
							
						str+='</tr>';
					}
					str+='</tbody>';
					str+='</table>';
					$("#speakersAttendenceImg").hide();
					$("#speakersAttendence").html(str);
					$('#speakerTab').dataTable();
					//$('#speakersTab').removeClass('dataTable');
				}
				else if(searchType =='consolidated')
				{	
					str+='<thead>';
					str+='<th class="text-center" style="background-color:#dddddd;" >  </th>';
					str+='<th class="text-center" style="background-color:#dddddd;" > SPEAKER </th>';
					str+='<th class="text-center" style="background-color:#dddddd;" > DESIGNATION </th>';
					str+='<th class="text-center" style="background-color:#dddddd;" > ATTENDED COUNT </th>';
					str+='</thead>';				
					str+='<tbody>';
					for(var i in result){
						str+='<tr>';						
						str+='<td><a href="cadreDetailsAction.action?cadreId='+result[i].id+'" target="_blank" ><img src="https://www.mytdp.com/images/cadre_images/'+result[i].imageStr+'" style="width: 50px;"/></a></td>';
						str+='<td> <b> NAME: </b> <a href="cadreDetailsAction.action?cadreId='+result[i].id+'" target="_blank" >'+result[i].name+' </a><br>';
						
						if(result[i].status != null)
							str+=' <b>   </b>'+result[i].status+'';
						str+='</td>';
						if(result[i].partyBenefitStr != null)
							str+='<td style="text-align:center;">'+result[i].partyBenefitStr+'</td>';
						else
							str+='<td style="text-align:center;"> - </td>';
						if(result[i].inviteeAttendedCount != null && result[i].inviteeAttendedCount>0)
							str+='<td style="text-align:center; cursor:pointer;" attr_cadre_id="'+result[i].id+'" class="callFunctionCls"> '+result[i].inviteeAttendedCount+' </td>';
						else
							str+='<td style="text-align:center;"> 0 </td>';
							
						str+='</tr>';
					}
					str+='</tbody>';
					str+='</table>';
					$("#speakersAttendenceImg").hide();
					$("#speakersAttendence").html(str);
					$('#speakerTab').dataTable();
					
					//$('#speakersTab').removeClass('dataTable');
				}
			}else{
				$("#speakersAttendenceImg").hide();
				$("#speakersAttendence").html("No Data Available...");
					$("#spekrsTotalDiv").html("");
			}
		});
	}
	
	function getInvitedAttendedCadreCountByBatchIds(fromType){
		
		$('#distWiseDivId').html('');
		$('#constWiseDivId').html('');
		
		var dates;
		if(fromType=="onLoad" || fromType=="change"){
			dates="";
		}else{
			dates=$("#selectDate").val();
		}
		var enrollmentYrIds =[];
		enrollmentYrIds.push($("#trainingEnrlmntYrId").val());
	    if(fromType == "change"){
			enrollmentYrIds.length = 0;
		    enrollmentYrIds.push($("#trainingEnrlmntYrId").val());
	     }
		 var programIds =[];
		if(enrollmentYrIds == 4){
			programIds.push(8,9);
		}else{
			programIds.push(1,6,7,5);
		}
		var jObj={
		  selectedDate : dates,
		  enrollmentYearIdsList :enrollmentYrIds,
		  programIdsList : programIds
	     }
		$.ajax({
		   type:'POST',
		   url :'getInvitedAttendedCadreCountByBatchIdsAction.action',
		    data: {task:JSON.stringify(jObj)},
		}).done(function(result){
		       $('#distWiseImg').hide(); 
			   $('#constWiseImg').hide(); 
			   
		 	   if(result!=null && result.simpleVOList1!=null && result.simpleVOList1.length>0){
				   buildDistData(result.simpleVOList1);
			   }else{
				   $('#distWiseDivId').html('<p style="text-align:center;font-weight:bold;">NO DATA AVAILABLE</p>');
			   }
			   
			   if(result!=null && result.simpleVOList2!=null && result.simpleVOList2.length>0){
				  buildConstData(result.simpleVOList2);
			   }else{
				  $('#constWiseDivId').html('<p style="text-align:center;font-weight:bold;">NO DATA AVAILABLE</p>');
			   }
	    });
	}
	function buildDistData(results){
		
		var str='';
		str+='<table class="table table-bordered m_0" id="distTable">';
			str+='<thead class="bg_d">';
			str+='<th>DISTRICTS NAME</th>';
			str+='<th>INVITED</th>';
			str+='<th>ATTENDED</th>';
		    str+='<th>INVITEES ATTENDED</th>';
			str+='<th>INVITEES ATTENDED PERCENTAGE</th>';
			str+='<th>NON INVITEES ATTENDED</th>';
			str+='</thead>';
			str+='<tbody>';
		for(var i in results){
			str+='<tr>';
				str+='<td>'+results[i].name+'</td>';
				
				if(results[i].total==0){
					str+='<td> - </td>';
				}else{
					str+='<td>'+results[i].total+'</td>';
				}
				
				if(results[i].count==0){
					str+='<td> - </td>';
				}else{
                   str+='<td>'+results[i].count+'</td>';
				}
				if(results[i].locValue==0){
					str+='<td> - </td>';
				}else{
                   str+='<td>'+results[i].locValue+'</td>';
				}
				if(results[i].percentage==null){
					str+='<td>0.00</td>';
				}else{
					str+='<td>'+results[i].percentage+'</td>';
				}
				if(results[i].totalCount==0){
					str+='<td> - </td>';
				}else{
                   str+='<td>'+results[i].totalCount+'</td>';
				}
			str+='</tr>';
		}
		str+='</tbody>';
		str+='</table>';
		$('#distWiseDivId').html(str);
		$("#distTable").dataTable({"aaSorting": [[ 1, "desc" ]],
		   "aLengthMenu": [[10, 20, 40,90,-1], [10, 20, 40,90,"All"]]});
		//$("#distTable").removeClass('dataTable');
	}
	function buildConstData(results){
		
		var str='';
		str+='<table class="table table-bordered m_0" id="constTable">';
			str+='<thead class="bg_d">';
			str+='<th>CONSTITUENCY NAME</th>';
			str+='<th>INVITED</th>';
			str+='<th>ATTENDED</th>';
			str+='<th>INVITEES ATTENDED</th>';
			str+='<th>INVITEES ATTENDED PERCENTAGE</th>';
			str+='<th>NON INVITEES ATTENDED</th>';
			str+='</thead>';
			str+='<tbody>';
		for(var i in results){
			str+='<tr>';
				str+='<td>'+results[i].name+'</td>';
				
				if(results[i].total==0){
					str+='<td> - </td>';
				}else{
					str+='<td>'+results[i].total+'</td>';
				}
				
				if(results[i].count==0){
					str+='<td> - </td>';
				}else{
					str+='<td>'+results[i].count+'</td>';
				}
				if(results[i].locValue==0){
					str+='<td> - </td>';
				}else{
					str+='<td>'+results[i].locValue+'</td>';
				}
				if(results[i].percentage==null){
					str+='<td>0.00</td>';
				}else{
					str+='<td>'+results[i].percentage+'</td>';
				}
				
				if(results[i].totalCount==0){
					str+='<td> - </td>';
				}else{
					str+='<td>'+results[i].totalCount+'</td>';
				}
				
			str+='</tr>';
		}
		str+='</tbody>';
		str+='</table>';
		$('#constWiseDivId').html(str);
		
		$("#constTable").dataTable({"aaSorting": [[ 1, "desc" ]],
		   "aLengthMenu": [[10, 20, 40,90,-1], [10, 20, 40,90,"All"]]});
		$("#constTable").removeClass("dataTable");
	}
	/*
	$(document).on('click', '.applyBtn', function(){
		fromTypeGlob="onClick";
		getTrainingCenterDetailsBasedOnDates("onClick");
		getAttendedCountForBatchesByLocation("onClick");
		getInvitedAttendedCadreCountByBatchIds("onClick");
		getDayWiseCountsForRunningBatches("onClick");
		getFeedBackCountsOfTraining("onClick");
	});
	
	$(document).on("click",".ranges li",function(){
		fromTypeGlob="onClick";
		getTrainingCenterDetailsBasedOnDates("onClick");
		getAttendedCountForBatchesByLocation("onClick");
		getInvitedAttendedCadreCountByBatchIds("onClick");
		getDayWiseCountsForRunningBatches("onClick");
		getFeedBackCountsOfTraining("onClick");
	});
	*/
	$(document).on("click",".summaryCls",function(){
		var progId = $(this).attr("attr_prog_id");
		var campId = $(this).attr("attr_camp_id");
		var batchId = $(this).attr("attr_batch_id");
		var dates = $("#selectDate").val();
		var callFrom = $(this).attr("attr_from");
		var enrollmentYrId = $("#trainingEnrlmntYrId").val();
		if(fromTypeGlob=="onClick"){
			dates = $("#selectDate").val();
		}else{
			dates="";
		}
		var win = window.open('trainingProgramMainDashBoardAction.action?pd='+progId+'&cd='+campId+'&bd='+batchId+'&dts='+dates+'&cf='+callFrom+'&eyi='+enrollmentYrId+'', '_blank');
	});
	
	$(".constDistRadio").click(function(){
		if($(this).val()=="const"){
			$("#constDivId").show();
			$("#headingDiv").html("CONSTITUENCY ");
			$("#distDivId").hide();
		}else if($(this).val()=="dist"){
			$("#constDivId").hide();
			$("#headingDiv").html("DISTRICT ");
			$("#distDivId").show();
		}
	});
	
	$(".filterRadio").click(function(){
		$("#speakersAttendence").html("");
		getAttendenceForTrainers($(this).val());
	});
	$(".filtersRadio").click(function(){
		$("#speakersAttendence").html("");
		getAttendenceForTrainers($(this).val());
	});
	
	$(document).on("click",".callFunctionCls",function(){
		getProgramCampBatchDetailsForAMemberBasedOnCadreId($(this).attr("attr_cadre_id"));
	});
	
	function getProgramCampBatchDetailsForAMemberBasedOnCadreId(cadreId){
		$("#resultModalId").modal("show");
		$("#resultInnerDIvId").html('');
		$("#modalLoadingImg").show();
		var type = $( "input:radio[name=filterRadio]:checked" ).val();
		//var cadreId = 7072180;
		var cadreId = cadreId;
		var jObj={
				cadreId : cadreId,
				type:type
			}
		$.ajax({
		   type:'POST',
		   url :'getProgramCampBatchDetailsForAMemberBasedOnCadreIdAction.action',
		   data: {task:JSON.stringify(jObj)},
		}).done(function(result){
			if(result != null && result.length > 0){
				var str='';
				str+='<table class="table table-bordered" id="tableId">';
				str+='<tr>';
				str+='<td>Program Name</td>';
				str+='<td>Camp Name</td>';				
				//str+='<td>Batch Name</td>';
				str+='<td>Date</td>';
				str+='<td>Status</td>';
				str+='</tr>';
				for(var i in result){
					str+='<tr>';		
					str+='<td>'+result[i].progName+'</td>';	
					if(result[i].campId == 1)
						str+='<td> SVV </td>';		
					else if(result[i].campId == 2)
						str+='<td> EWK </td>';
					else if(result[i].campId == 3)
						str+='<td> GPN </td>';
					else if(result[i].campId == 4)
						str+='<td> AKKC </td>';					
					//str+='<td>'+result[i].batchName+'</td>';
					str+='<td>'+result[i].dateString+'</td>';
					str+='<td>'+result[i].status+'</td>';
					str+='</tr>';
				}
				str+='</table>';
				$("#modalLoadingImg").hide();
				$("#resultInnerDIvId").html(str);
			}else{
				$("#modalLoadingImg").hide();
				$("#resultInnerDIvId").html("No Date Found...");
			}
		});
	}
	function getFeedBackCountsOfTraining(fromType){
			$("#feefbackLoadingImgId").show();
			$("#viewDetailsBtnId").hide();
			
			var dates;
			if(fromType=="onLoad" || fromType=="change"){
				dates="";
			}else{
				dates=$("#selectDate").val();
			}
			var enrollmentYrIds =[];
			enrollmentYrIds.push($("#trainingEnrlmntYrId").val());
	     if(fromType=="change"){
			 enrollmentYrIds.length = 0;
		    enrollmentYrIds.push($("#trainingEnrlmntYrId").val());
	     }
		 var programIds =[];
			if(enrollmentYrIds==4){
				programIds.push(8,9);
			}else{
				programIds.push(1,6,7,5);
			}
		var jsObj={
			dates : dates,
			enrollmentYearIdsList :enrollmentYrIds,
			programIdsList : programIds
		}
		$.ajax({
		   type:'POST',
		   url :'getFeedBackCountsOfTrainingAction.action',
		   data: {task:JSON.stringify(jsObj)},
		}).done(function(result){
			var str='';
			$("#feefbackLoadingImgId").hide();
			if(result !=null && result.length>0)
			{
			
			$("#viewDetailsBtnId").show();
		
			 str+='<table class="table table-bordered m_0">';
				str+='<thead>';
						str+='<th>Training Program Name</th>';
						str+='<th>Training Center Name</th>';
						str+='<th>FeedBack Provided</th>';
						str+='<th>Feedback Documents</th>';
				str+='</thead>';
				
				str+='<tbody>';
					var campLength=0;
				for(var i in result){
					if(result[i].trainingCampVOList !=null){
						campLength = parseInt(campLength)+parseInt(result[i].trainingCampVOList.length);
					}					
					for(var j in result[i].trainingCampVOList){
						 if(j==0){
							 str+='<td rowspan='+campLength+' id="'+result[i].trainingCampVOList[j].programId+'">'+result[i].trainingCampVOList[j].programName+'</td>';	
						 }
						
						  str+='<td id="'+result[i].trainingCampVOList[j].campId+'">'+result[i].trainingCampVOList[j].campName+'</td>';	
						  if(result[i].trainingCampVOList[j].assignedCount==null){
							  result[i].trainingCampVOList[j].assignedCount=0;
						  }
						  str+='<td id="'+result[i].trainingCampVOList[j].campId+'">'+result[i].trainingCampVOList[j].assignedCount+'</td>';	
						  if(result[i].trainingCampVOList[j].filledCount==null){
							  result[i].trainingCampVOList[j].filledCount=0;
						  }
						  str+='<td id="'+result[i].trainingCampVOList[j].campId+'">'+result[i].trainingCampVOList[j].filledCount+'</td>';	
						  
						  str+='</tr>';
					}
				}
				str+='<tr>';
					str+='<td colspan="2" class="text-center"><b>TOTAL</b></td>';
					str+='<td><b>'+result[0].availableMembersCount+'</b></td>';
					str+='<td><b>'+result[0].totalProgrammesCount+'</b></td>';
				str+='</tr>';			
					
				str+='</tbody>';				
			 str+='</table>';
			
			$("#feedBackCountId").html(str);
			//$("#feedBackCountMainDivId").show();
			}
			else{
				$("#feedBackCountId").html("Feedback Data Not Available.");
			}
			
		});
		
	}
	
	$("#viewDetailsBtnId").click(function(){
		var dates='';
		if(fromTypeGlob=="onLoad"){
			dates='';
		}else if(fromTypeGlob=="onClick"){
			dates = $("#selectDate").val();
		}
		
		window.open('feedbackOverViewDetailsAction.action?dates='+dates+'', '_blank');
	});
	
	$(document).on("click",".cadreDetailsCls",function(){ 
		var batchId = $(this).attr("attr_batchId");
		var type=$(this).attr("attr_type");
		var dataType=$(this).attr("attr_dataType");
		getDaysAttendedCadreDetails(batchId,dataType,type);
		$("#myModalForCadreId").modal('show');
	});
	
	function getDaysAttendedCadreDetails(batchId,dataType,type){
		
		$("#cadreTitleId").html('');
		$("#popupForCadreDetailsId").html('');
		
		$("#dataLoadingsImgForCadrePopUpId").show();
		var enrollmentYrIds =[];
			enrollmentYrIds.push($("#trainingEnrlmntYrId").val());
		 var programIds =[];
			if(enrollmentYrIds==4){
				programIds.push(8,9);
			}else{
				programIds.push(1,6,7,5);
			}
		var jsObj={
			batchId : batchId,
			dataType:dataType,
			type:type,
			enrollmentYrIds :enrollmentYrIds,
			programIdsList : programIds	
		}
		$.ajax({
		   type:'POST',
		   url :'getDaysAttendedCadreDetailsAction.action',
		   data: {task:JSON.stringify(jsObj)},
		}).done(function(result){
			$("#dataLoadingsImgForCadrePopUpId").hide();
			$("#cadreTitleId").html("<div style='color:green;'>"+type.toUpperCase()+" Cadre Details</div>");
			if(result !=null && result.length>0){
				buildDaysAttendedCadreDetails(result);
			}else{
				$("#popupForCadreDetailsId").html("No Data Available.");
			}
			
		});
	}
	function buildDaysAttendedCadreDetails(result){
		var str='';
		str+='<table class="table" id="dataTablePagination">';
			str+='<thead>';
				str+='<th></th>';
			str+='</thead>';
			for(var i in result){
				str+='<tr>';
					str+='<td>';
						str+='<div class="media scrollDivConstituencycls" style="border-bottom: 1px solid rgb(51, 51, 51);" attr_cadre_id='+result[i].cadreId+'>';
							str+='<span href="#" class="media-left">';
							str+='<img style="width: 64px; height: 64px;" src="'+result[i].imageStr+'" />';
							str+='</span>';
							str+='<div class="media-body">';
							str+='<h5 class="media-heading"> <span style="font-weight:bold;"> Name:</span> '+result[i].name+' ; ';				
							str+=' <span style="font-weight:bold;"> Relative Name: </span>'+result[i].status+' </h5>';
							str+='<ul class="list-inline">';
							str+='<li>Age:'+result[i].age+';</li>';
							str+='<li>Mobile No: '+result[i].mobileNo+'</li>';
							str+='<li>Caste: '+result[i].caste+'</li>';
							str+='<li>MemberShip No: <a href="cadreDetailsAction.action?cadreId='+result[i].id+'" target="_blank" >'+result[i].membershipNo+'</a></li>';
							str+='</ul>';
							str+='</div>';
						str+='</div>';
					str+='</td>';
				str+='</tr>';
			}
		str+='</table>';
		$("#popupForCadreDetailsId").html(str);
		$("#dataTablePagination").dataTable();
		//$(".scrollDivConstituencycls").mCustomScrollbar({max-height:400px;overflow-y:auto;z-index:999999;});
	}
	
	/* $(document).on("click",".cadreCls",function(){
		var cadreId = $(this).attr("attr_cadre_id");
		window.open("https://mytdp.com/cadreDetailsAction.action?cadreId="+cadreId,"_blank")
	}); */

	
	function getAllTrainingCampDetails(fromType){
			$("#runningMembers").html('<img id="" style="width: 45px; height: 45px; margin-left: 45%;" src="images/ajaxImg2.gif">');
			
		  var enrollmentYrIds =[];
		  enrollmentYrIds.push($("#trainingEnrlmntYrId").val());
		  if(fromType == "change"){
			  enrollmentYrIds.length = 0;
		    enrollmentYrIds.push($("#trainingEnrlmntYrId").val());
	     }
		  var programIds =[];
			if(enrollmentYrIds == 4){
				programIds.push(8,9);
			}else{
				programIds.push(1,6,7,5);
			}
		var jsObj={
			enrollmentYearIdsList :enrollmentYrIds,
			programIdsList : programIds,
			task : "totalTrainingCampDetails"
		}
		$.ajax({
		   type:'POST',
		   url :'getAllTrainingCampDetailsAjaxAction.action',
		   data: {task:JSON.stringify(jsObj)},
		}).done(function(result){
			if(result != null && result.length >0)
			{
				for(var i in result)
				{
					//console.log(result[i].confirmedCount);
					if(i==0){
						$('#upcomingMembers').html(result[i].confirmedCount);
						$('#upcomingMembersAte').html(0);
						$('#upcomingMembersNonAte').html(0);						
					}else if(i==1){
						$('#runningMembers').html(result[i].confirmedCount);
						$('#runningMembersAte').html(result[i].inviteeAttendedCount);
						$('#runningMembersNonAte').html(result[i].nonInviteeAttendedCount);						
					}else if(i==2){
						$('#completedMembers').html(result[i].confirmedCount);
						$('#completedMembersAte').html(result[i].inviteeAttendedCount);
						$('#completedMembersNonAte').html(result[i].nonInviteeAttendedCount);						
					}else if(i==3){
						$('#totalMembers').html(result[i].confirmedCount);
						$('#totalMembersIn').html(result[i].inviteeAttendedCount);
						$('#totalMembersNonIn').html(result[i].nonInviteeAttendedCount);						
					}
						
				}					
			}
		});
	}
	//getAllTrainingCampDetails();
$(document).on("click","#refrshButtonId",function(){
	updateTrainingCampSpeakersDetails();
});
 function updateTrainingCampSpeakersDetails(){
	 var jsObj={
			task : "getUpdateTrainingCampSpeakersDetails"
		}
		$.ajax({
		   type:'POST',
		   url :'getUpdateTrainingCampSpeakersDetails.action',
		   data: {task:JSON.stringify(jsObj)},
		}).done(function(result){	
		});
	}
/*
$(document).on('change', '#trainingEnrlmntYrId', function(){
		fromTypeGlob="change";
		getTrainingCenterDetailsBasedOnDates("change");
		getAttendedCountForBatchesByLocation("change");
		getInvitedAttendedCadreCountByBatchIds("change");
		getDayWiseCountsForRunningBatches("change");
		getFeedBackCountsOfTraining("change");
		getAllTrainingCampDetails("change");
		getAttendenceForTrainers("change");
	});
	*/
$("#trainingEnrlmntYrId").tooltip();

$(document).on("click","#totalTraingCentersIconId",function(){
	$("#totalCampDetailsModalId").modal("show");
	$("#taingingCampsDivId").html('');
	getTrainingCampDetailsByCampIds();
});
$(document).on("click","#totalTraingProgramsIconId",function(){
	$("#totalprogramDetailsModalId").modal("show");
	 getTrainingProgramDetailsByProgramIds();
});


function getTrainingCampDetailsByCampIds(){
	
	var traingCampIds=[1,2,3,4,7];
	var jsObj={
		traingCampIds:traingCampIds
    }
    $.ajax({
       type:'POST',
       url :'getTrainingCampDetailsByCampIdsAction.action',
       data: {task:JSON.stringify(jsObj)},
    }).done(function(result){
		buildtrainCampDetails(result);   
    });

}	

function getTrainingProgramDetailsByProgramIds(){
	$("#taingingProgrmsDivId").html('');
var traingProgramIds=[8,9];
  var jsObj={
  traingProgramIds:traingProgramIds
    }
    $.ajax({
       type:'POST',
       url :'getTrainingProgramDetailsByProgramIdsAction.action',
       data: {task:JSON.stringify(jsObj)},
    }).done(function(result){
		buildProgramDeatails(result);
		
    });

}	
function buildProgramDeatails(result){
	var str='';
		str+='<table id="trainingProgramTableId" class="table table-responsive text-center">';
			str+='<thead>';
				str+='<tr>';
					str+='<th class="text-center"> PROGRAM NAME</th>';
				str+='</tr>';
		str+='</thead>';
		 str+='<tbody>';
			 for(var i in result){
				 str+='<tr>';
				str+="<td id="+result[i].id+">"+result[i].name+"</td>";	
				str+='</tr>';
			}
		str+='</tbody>';
	 str+='</table>';
	 $("#taingingProgrmsDivId").html(str);
}

function buildtrainCampDetails(result){
	var str='';
	 str+='<table id="trainingCenterTableId" class="table table-bordered table-responsive text-center">';
		 str+='<thead>';
			str+='<tr style="background-color:#CCCCCC;">';
				str+='<th class="text-center"> CAMP NAME</th>';
				str+='<th class="text-center" > LOCATION</th>';
				str+='<th class="text-center" > COVERED DISTRICTS</th>';
			str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
		for( var i in result){
			str+='<tr>';
				str+='<td id="'+result[i].id+'">'+result[i].name+'</td>';
				str+='<td>'+result[i].locationName+'</td>';
				str+='<td>'+result[i].districtName+'</td>';
			str+='</tr>';
		}
		str+='</tbody>';
	 str+='</table>';
	$("#taingingCampsDivId").html(str);
	
}	

</script>
</body>
</html>	
