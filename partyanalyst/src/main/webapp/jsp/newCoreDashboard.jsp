<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Core DashBoard</title>
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/css/custom.css" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/css/responsive.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/Plugins/Slick/slick.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/Slick/slick-theme.css" type="text/css" rel="stylesheet"/>
<link href="dist/DateRange/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/Rating/star-rating.css" type="text/css" rel="stylesheet"/>
<link href="dist/scroll/jquery.mCustomScrollbar.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<header>
	<nav class="navbar navbar-default navbarHeader">
      <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">
          	<span class="logo"></span>
            <span class="logoText">TDP PARTY</span>
          </a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse " id="bs-example-navbar-collapse-1">
          <ul class="nav navbar-nav navbar-right">
          	<li><a href="birthDayAction.action"><i class="glyphicon glyphicon-gift" data-toggle="tooltip" data-placement="bottom" title="Click here Birthday Page"></i><span class="birthdayCount" style="top:3px;left:31px"> <b> ${sessionScope.birthDayCount} </b></span></a></li>
            <li><a href="#"><i class="glyphicon glyphicon-bell"></i></a></li>
            <li class="dropdown profileDropDown">
              <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><img src="dist/coreDashboard/img/logo.png" class="profileImage"/> <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="#">My Profile</a></li>
                <li><a href="#">Change Password</a></li>
                <li role="separator" class="divider"></li>
                <li><a href="#">Sign Out</a></li>
              </ul>
            </li>
          </ul>
        </div><!-- /.navbar-collapse -->
      </div><!-- /.container-fluid -->
    </nav>
    <div class="navbar navbar-default navbarProfile">
    	<div class="container">
        	<div class="row">
            	<div class="col-md-7 col-xs-12 col-sm-7">
                	<ul class="nav navbar-nav navbar-left headerProfileName">
                        <li class="dropdown profileDropDown toggleViewIcon">
                          <a style="cursor:pointer;">ASHOK DAKAVARAM 
                          	<span class="caretBackground">
                            	<span class="caret" style="margin-top: 9px;"></span>
                            </span>
                          </a>
						 <div class="dropdown-menu settingsDropDownOptionsView" style="z-index: 999;">
								<div id= "userLevelDetailsDiv"></div>
							</div>
                        </li>
                    </ul>
                </div>
				<div class="col-md-2 col-xs-12 col-sm-3 pull-right"  style="margin-top: -10px;">
                	<ul class="list-inline profileSelection">
                    	<li class="active" >
                        	<a  style="cursor:pointer;text-decoration:none;" attr_state_id="1" class="stateCls">AP</a>
                        </li>
                        <li>
                        	<a  style="cursor:pointer;text-decoration:none;" attr_state_id="36" class="stateCls">TS</a>
                        </li>
                    </ul>
                </div>
              
            </div>
        </div>
    </div>
</header>
<div class="container m_top20">
	<div class="row">
    	<div class="col-md-6 col-xs-12 col-sm-12 committeesBlock">
        	<div class="panel panel-default panelNewCustom">
            	<div class="panel-heading">
                	<div class="row">
						<div class="col-md-6 col-sm-8 col-xs-7">
							<h4 class="panel-title text-capital">
								<img src="newCoreDashBoard/img/committees.png" class="iconClass"/>
								committees
							</h4>
						</div>
						<div class="col-md-6 col-sm-4 col-xs-5">
							<span class="basicCommitteesBlockDiv pull-right">
								<i class="fa fa-gears"  data-toggle="tooltip" data-placement="top" title="Settings"></i>
							</span>
							<span class="notesIcon pull-right">
								<i class="glyphicon glyphicon-list-alt"  data-toggle="tooltip" data-placement="top" title="Notes"></i>
							</span>
							<span class="iconExpand pull-right">
								<i class="glyphicon glyphicon-fullscreen" data-toggle="tooltip" data-placement="top" title="Expand"></i>
							</span>
							
							<span class="input-group pull-right dateRangePickerCls hide">
								<input type="text" id="dateRangeId"	 class="form-control" />
								<span class="input-group-addon">
									<i class="glyphicon glyphicon-calendar"></i>
								</span>
							</span>
						</div>
					</div>
					
							
					<div class="basicCommitteesBlockDropDown" style="z-index:999;margin-top: -3px;" >
						<h4 class="text-capital" style="color:#99A0A5;">Select Committees</h4>
						<hr style ="margin-bottom:0px;" />
						<div id="basicCommitteeDetailsDiv" style="height:300px;overflow-y:scroll;">
						
						<c:forEach items="${userDataVO.subList}" var="basicCommittee">
							 <div class="col-xs-12 col-sm-12 col-md-12">
								<label class="checkbox-inline m_top10">
								 <c:choose>
								   <c:when test="${basicCommittee.id == 1}">
									   <input type="checkbox"  class="checkedBasicComm" value="${basicCommittee.id}" checked>
								   </c:when>
									<c:otherwise>
									   <input type="checkbox"  class="checkedBasicComm" value="${basicCommittee.id}">
									</c:otherwise>
							   </c:choose>
									<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">${basicCommittee.name}</h5></div>
							   </label>
							   <hr style ="margin-bottom:0px;" />
						  </div>      
						</c:forEach>
						
						
						</div>
						
						<button type="button" class="btn btn-success basicCommittessDiv">Get Details</button>
					</div>
					<div class="notesDropDown">
                    	<h4 class="text-capital">notes
                        	<span class="pull-right">
                            	<i class="glyphicon glyphicon-list-alt"></i>
                            </span>
                        </h4>
                    	<ul class="notesUl m_top20">
                        	<li>
                            	<span class="notesText">notes on committees notes on committee notes on committee notes on committee notes on committee </span>- <span class="text-muted"><i>20-July 2016</i></span>
                            	<i class="glyphicon glyphicon-trash pull-right hoverBlock deleteNotes"></i>
                                <i class="glyphicon glyphicon-edit pull-right hoverBlock editNotes"></i>
                            </li>
                        </ul>
                        <hr/>
                        <label>Create Notes</label>
                        <textarea class="form-control notesArea"></textarea>
                        <button class="btn btn-default btnCustomCreate btn-sm">create</button>
                    </div>
                </div>
                <div class="panel-body">
				
              		<div class="row">
					<div class="col-xs-12 col-sm-4 col-md-2 pull-right showDatePicker" style="display:none;">
						
					</div>
					<div class="col-md-12 col-xs-12 col-sm-12 col-md-offset-0 basicCommitteesBlock">
						<div id="basicCommitteeCountsDiv"></div>
					</div>
                        <div class="col-md-12 col-xs-12 col-sm-12 col-md-offset-0 userTypeCommitteesBlock committeesHiddenBlock">
                        	<div class="row">
                            	<div class="col-md-12 col-xs-12 col-sm-12">
                                	<ul class="activeUlCls list-inline pull-right">
                                    	<!--<li class="active topFiveStrongResults">Top 5 Strong</li>
										<li class="topFivePoorResults">Top 5 Poor</li>-->
										<li class="liCls1 active addactive" attr_value="strong">top 5 strong</li>
										<li class="liCls1 removeactive" attr_value="poor">last 5 poor</li>
                                    </ul>
                                </div>
                                <div id="userTypeWiseCommitteesForTopFiveStrongAndPoorDiv"></div>
                               <!-- <div id="userTypeWiseCommitteesForTopFivePoorDiv" style ="display:none"></div>-->
						    </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-12">
                        	<i class="glyphicon glyphicon-option-horizontal pull-right moreBlocksIcon" data-toggle="tooltip" data-placement="top" title="Click here for more"></i>
                        </div>
                        <div class="col-md-12 col-xs-12 col-sm-12 col-md-offset-0 moreBlocksDetailAndComp" style="display:none;">
                        	
							<ul class="list-inline pull-right activeUlCls">
                            	<li class="active detailedBlock">Detailed</li>
                                <li class="comparisionBlock">Comparison</li>
                            </ul>
					    </div>
                        <div class="col-md-12 col-xs-12 col-sm-12 col-md-offset-0 moreBlocks">
                        	<div class="panel panel-default">
                            	<div class="panel-body ">
								<div id="levelWiseBasicCommittees"></div>
                                
                                </div>
                            </div>
                        </div>
                       
                        <div class="col-md-12 col-xs-12 col-sm-12 col-md-offset-0 m_top20 moreBlocks">
                        	<div class="panel panel-default panelNew">
                                <div class="panel-heading">
                                    <div class="row">
                                        <div class="col-md-8 col-xs-12 col-sm-12">
                                            <span class="headingColor text-capitalize">all committees performance cohort</span>
                                        </div>
                                        
                                    </div>
                                </div>
                                <div class="panel-body">
									<div id="districtWiseCommitteesReport" ></div>
								</div>
                            </div>
                        </div>
                        <div class="col-md-12 col-xs-12 col-sm-12 m_top20 moreBlocks1" style="display:none;" >
                            <!--<p><i>Selected:</i> <b>Main Committee</b></p>-->
                            <div class="panel panel-default panelNew">
                                <div class="panel-heading">
                                    <div class="row">
                                        <div class="col-xs-12 col-sm-8 col-md-10">
                                           <div id="childUserTypeDetailsDiv"></div>
                                        </div>
                                       
                                    </div>
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12 col-xs-12 col-sm-12">
                                           <div id="SelectedUserTypeDetailsDiv"></div>
                                        </div>
                                        <div class="col-md-12 col-xs-12 col-sm-12">
                                            <div class="bg_ED pad_15 m_top20 showChildBlockAndTopPoorBlock">
                                                <div id="directChildActivityMemberDiv"></div>
                                                <div class="row m_top20">
                                                    <div class="col-md-8 col-xs-12 col-sm-12 col-md-offset-0" style="border-right:1px solid #ddd;">
													 <div id="topPoorPerformanceDiv"></div>
                                                       
                                                    </div>
                                                    <div class="col-md-4 col-xs-12 col-sm-12 col-md-offset-0">
                                                        <div id="topPoorLocationsDiv"></div>
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
        <!-- TRAINING PROGRAM BLOCK -->
        <div class="col-md-6 col-xs-12 col-sm-12 trainingsBlock">
			<div class="panel panel-default panelNewCustom">
				<div class="panel-heading">
					<div class="row">
						<div class="col-md-6 col-sm-8 col-xs-7">
							<h4 class="panel-title text-capital">
								<img src="newCoreDashBoard/img/training.png" class="iconClass"/>
								training
							</h4>
						</div>
						<div class="col-md-6 col-sm-4 col-xs-5">
							<span class="settingsIcon pull-right">
								<i class="fa fa-gears"  data-toggle="tooltip" data-placement="top" title="Settings"></i>
							</span>
							<span class="trainingIconExpand pull-right">
								<i class="glyphicon glyphicon-fullscreen"></i>
							</span>
							<span class="input-group pull-right dateRangePickerClsForTraining hide">
									<input type="text" id="dateRangeIdForTrainingCamp"	 class="form-control" />
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-calendar"></i>
									</span>
							</span>
						</div>
					</div>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-12 col-xs-12 col-sm-12 trainingsBlock">
							<div class="row">
							 <div id="programsDtlsCntTableId"></div>
								<div class="col-md-12 col-xs-12 col-sm-12 col-md-offset-0 m_top20">
									 <div id="villageWardTblId"></div>
								</div>
								<div class="col-md-12 col-xs-12 col-sm-12 col-md-offset-0 m_top10">
									 <div id="mdlTwnDvsnTabId"></div>
								</div>
								<!--<div class="col-md-12 col-xs-12 col-sm-12 col-md-offset-0 m_top10">
									 <div id="districtTblId"></div>
								</div>
								<div class="col-md-12 col-xs-12 col-sm-12 col-md-offset-0 m_top10">
									 <div id="stateTblDivId"></div>
									<!--<hr class="m_0"/>"/>
								</div>-->
							</div>
						</div>
							<div class="col-md-6 col-xs-12 col-sm-12 col-md-offset-0 trainingsHiddenBlock">
								<div class="row">
								  <div class="col-md-6 col-md-offset-6 col-xs-12 col-sm-6 col-sm-offset-6">
									<ul class="activeUlCls list-inline ">
										<li class="liCls active" attr_value="strong">top 5 strong</li>
										<li class="liCls" attr_value="poor">last 5 poor</li>
									</ul>
								  </div>
								</div>
								<div id="userTypeWiseTrainingProgramTopFiveStrongAndPoorMemsDivId"></div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-12">
									<i data-placement="top" data-toggle="tooltip" class="glyphicon glyphicon-option-horizontal pull-right moreTrainingBlocksIcon" title="Click here for more"></i>
						    </div>
							<div class="col-xs-12 col-sm-12 col-md-12 moreTrainingBlocks">
									<ul class="list-inline pull-right activeUlCls">
										<li class="trainingDetailed">Detailed</li>
										<li class="trainingComparison">Comparison</li>
										<!--<li class="basicCommitteesBlockDiv"><i class="fa fa-gears"></i></li>-->
									</ul>
							</div>
						
							<div class="col-md-12 col-xs-12 col-sm-12 col-md-offset-0 moreTrainingBlocks trainingDetailedBlock">
								<div class="panel panel-default">
									<div class="panel-body">
										<h4 class="text-capitalize text-muted">training programs</h4>
										<br>
										<div id="programsDivId"></div>
										<!--<ul class="trainingsUl">
											<li>
												<div id="leadershipSkills" class="chartLi trainingGraphWidth"></div>
											</li>
											<li>
												<div id="officialSpokespersons" class="chartLi trainingGraphWidth"></div>
											</li>
										</ul>-->
									</div>
								</div>
							</div>
							<div class="col-md-12 col-xs-12 col-sm-12 col-md-offset-0 moreTrainingBlocks trainingDetailedBlock">
								<div class="panel panel-default panelNew">
									<div class="panel-heading">
										<div class="row">
											<div class="col-md-8 col-xs-12 col-sm-6">
												<span class="headingColor text-capitalize">training program cohort</span>
											</div>
											<!--<div class="col-md-4 col-xs-12 col-sm-6">
												<ul class="activeUlCls list-inline">
													<li class="active trainingProgramCohortLiCls" attr_li_value="all">All</li>
													<li class="trainingProgramCohortLiCls" attr_li_value="attended">Attended</li>
													<li class="trainingProgramCohortLiCls" attr_li_value="notAttended">Yet to train</li>
												</ul>
											</div>-->
										</div>
									</div>
									<div class="panel-body">
										<div class="row">
											<div class="col-md-12 col-xs-12 col-sm-12">
												<div id="districtWiseProgramCntDivId"></div>
											</div>
											<!--<div class="col-md-12 col-xs-12 col-sm-12">
												<h4 class="text-capitalize">Leadership SKills</h4>
												<div id="leadershipSkillsDis" class="chartLi"></div>
											</div>
											<div class="col-md-12 col-xs-12 col-sm-12">
												<h4 class="text-capitalize">official Spokes Person</h4>
												<div id="officialSpokesPerDis" class="chartLi"></div>
											</div>-->
										</div>
										
									</div>
								</div>
							</div>
						
							<div class="col-md-12 col-xs-12 col-sm-12 moreTrainingBlocks trainingComparisonBlock">
								<div class="panel panel-default panelNew">
									<div class="panel-heading">
										<div class="row">
											<div class="col-xs-12 col-sm-7 col-md-8">
											 <div id="childUserTypeDetailsDivForTrainingProgram"></div>
											</div>
											<div class="col-xs-12 col-sm-5 col-md-4">
												<!--<ul class="list-inline">
													<li>
														<div class="dropdown">
														  <button class="btn btn-default dropdown-toggle dateFinalDiv" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
							                          	  <span class="changeDate">
															This Week <span class="text-muted">Sunday - Today</span>
								                           </span>
															<span class="caret"></span>
														  </button>
														  <ul class="dropdown-menu calenarUl" aria-labelledby="dropdownMenu1">
															<li>LifeTime</li>
															<li role="separator" class="divider"></li>
															<li class="thisWeek">This Week <span class="text-muted">Sunday - Today</span></li>
															<li>Last Week <span class="text-muted lastWeekDate"></span></li>
															<li>Last 7 Days <span class="text-muted last7Days"></span></li>
															<li role="separator" class="divider"></li>
															<li>This Month <span class="text-muted thisMonth"></span></li>
															<li>Last Month <span class="text-muted lastMonth"></span></li>
															<li>Last 28 Days <span class="text-muted last28Days"></span></li>
															<li>Last 30 Days <span class="text-muted last30Days"></span></li>
															<li>First 28 Days<span class="text-muted first28Days"></span></li>
															<li role="separator" class="divider"></li>
															<li>This Quarter <span class="text-muted thisQuarter"></span></li>
															<li>Last Quarter <span class="text-muted lastQuarter"></span></li>
															<li>Last 90 Days <span class="text-muted Last90Days"></span></li>
															<li role="separator" class="divider"></li>
															<li>This Year<span class="text-muted thisYear"></span></li>
															<li>Last Year <span class="text-muted lastYear"></span></li>
															<li>Last 365 Days <span class="text-muted last365Days"></span></li>
															<li role="separator" class="divider"></li>
															<li>Custom Range <span class="customRange"></span></li>
														  </ul>
														</div>
													</li>
													<li>
														<i class="glyphicon glyphicon-calendar calendarIcon"></i>
													</li>
												</ul>-->
											</div>
										</div>
									</div>
									<div class="panel-body">
										<div class="row">
											<div class="col-md-12 col-xs-12 col-sm-12">
											 <div id="childActivityMemberDivId"> </div>
											</div>
											<div class="col-md-12 col-xs-12 col-sm-12">
												<div class="bg_ED pad_15 m_top20">
												 <div id="userTypeWiseChildDtlsTabId"></div>
													<div class="row">
														<div class="col-md-10 col-xs-12 col-sm-12 col-md-offset-0 m_top10">
															<!--<b><span class="color_333 pad_5 bg_CC text-capital"><span class="text-danger">poor</span> training completed locations</span></b>-->
															<div class="row m_top20">
															  <div id="poorPerformancTrainingPrograLocationsDivId"></div>
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
        <div class="row">
            <!-- DEBATES PROGRAM BLOCK -->
            <div class="col-md-6 col-xs-12 col-sm-8 col-sm-offset-2 col-md-offset-0 debatesBlock">
                <div class="panel panel-default panelNewCustom">
                    <div class="panel-heading">
                        <h4 class="panel-title">debates
                            <span class="debatesIconExpand pull-right">
                                <i class="glyphicon glyphicon-fullscreen"></i>
                            </span>
                        </h4>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12 col-xs-12 col-sm-12 debatesBlock">
                                <div class="row">
									<div id="partyWiseTotalDebateDetails"></div>
                                 </div>
                            </div>
                            <div class="col-md-6 col-xs-12 col-sm-12 debatesHiddenBlock">
                                <div class="row">
                                	<div class="col-md-12 col-xs-12 col-sm-12">
                                		<h4 class="text-capital"><span class="headingColor">Spokespersons</span></h4>
                                	</div>
                                    <div class="col-md-6 col-md-offset-6 col-xs-12 col-sm-6 col-sm-offset-6">
                                        <ul class="activeUlCls list-inline debateSpokesCls">
                                            <li id="debateTopId">top 5 strong</li>
                                            <li id="debateLowId">last 5 poor</li>
                                        </ul>
                                    </div>
									<div id="SpokesPersonWiseDebate"></div>
                                    
                                    <div class="col-xs-12 col-sm-12 col-md-12">
                                        <i data-placement="top" data-toggle="tooltip" class="glyphicon glyphicon-option-horizontal pull-right moreDebatesBlocksIcon" title="Click here for more"></i>
                                    </div>	
                                </div>
                            </div>
                        </div>
                        <div class="row debatesMoreHiddenBlock">
                            <div class="col-md-12 col-xs-12 col-sm-12 m_top20">
                                <div class="panel panel-default panelNew">
                                    <div class="panel-heading">
                                        <h4><span class="headingColor text-capitalize">scale based performance cohort</span></h4>
                                    </div>
                                    <div class="panel-body">
                                        <div class="row">
											<div id="scaleBasedPerformanceCohort"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12 col-xs-12 col-sm-12 m_top20">
                                <div class="panel panel-default panelNew">
                                    <div class="panel-heading">
                                        <h4><span class="headingColor text-capitalize">role based performance cohort</span></h4>
                                    </div>
                                    <div class="panel-body">
                                        <div class="row">
											<div id="roleBasedPerformanceCohort"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- TOP 5 BLock Start -->
							<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
                                <div class="panel panel-default panelNew">
                                    <div class="panel-heading">
										<div class="row">
											<div class="col-md-6 col-xs-12 col-sm-6">
												<h4><span class="headingColor text-capitalize">top 5</span></h4>
											</div>
											<div class="col-md-6 col-xs-12 col-sm-6">
												<ul class="activeUlCls list-inline pull-right">
													<li>Bully</li>
													<li>Critic</li>
												</ul>
											</div>
										</div>
                                        
                                    </div>
                                    <div class="panel-body">
                                        <div class="row">
											<div class="col-md-12 col-xs-12 col-sm-12">
												<div class="table-responsive">
													<table class="table tableTopDebates">
														<tr>
															<td class="text-capital">Raja Reddy</td>
															<td class="text-capital">
																<p>PARTY</p>
																<p><img src="newCoreDashBoard/img/tdpIcon.png" class="debatesPartyIcon"/>TDP</p>
															</td>
															<td class="text-capital">
																<p>debates</p>
																<p class="text-muted">150</p>
															</td>
															<td class="text-capital">
																<p>performance</p>
																<i class="glyphicon glyphicon-star"></i>
																<i class="glyphicon glyphicon-star"></i>
																<i class="glyphicon glyphicon-star"></i>
																<i class="glyphicon glyphicon-star"></i>
																<i class="glyphicon glyphicon-star"></i>
															</td>
														</tr>
													</table>
												</div>
											</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- TOP 5 BLock End -->
                            <div class="col-md-12 col-xs-12 col-sm-12 m_top20">
                                <div class="panel panel-default panelNew">
                                    <div class="panel-heading">
                                        <h4><span class="headingColor text-capitalize">candidate overall performance cohort</span></h4>
                                    </div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-md-12 col-xs-12 col-sm-12">
												<div id="candidateOverAllPerformanceCohort"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12 col-xs-12 col-sm-12 m_top20">
                                <div class="panel panel-default panelNew">
                                    <div class="panel-heading">
                                        <h4><span class="headingColor text-capitalize">channel vs parties</span></h4>
                                    </div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-md-12 col-xs-12 col-sm-12">
												<div id="channelAndPartyWiseDetails"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                          <!--  <div class="col-md-12 col-xs-12 col-sm-12 m_top20">
                                <div class="panel panel-default panelNew">
                                    <div class="panel-heading">
                                        <h4><span class="headingColor">SMS Poll</span></h4>
                                    </div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-md-12 col-xs-12 col-sm-12">
                                                <table class="table tableDebatesVs m_top10">
                                                  <tbody>
                                                    <tr>
                                                        <td class="b_right1">
                                                            <img src="newCoreDashBoard/img/ntvLogo.png" class="channelLogo" alt="Ntv Logo"/>NTV
                                                        </td>
                                                        <td>
                                                            <p class="text-capital">total questions</p>
                                                            <h4>100</h4>
                                                        </td>
                                                        <td>
                                                            <p class="text-capital">yes %</p>
                                                            <h4>100</h4>
                                                        </td>
                                                        <td>
                                                            <p class="text-capital">no % </p>
                                                            <h4>100</h4>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="b_right1">
                                                            <img src="newCoreDashBoard/img/studioNLogo.png" class="channelLogo" alt="Ntv Logo"/>Studio - N
                                                        </td>
                                                        <td>
                                                            <p class="text-capital">total questions</p>
                                                            <h4>100</h4>
                                                        </td>
                                                        <td>
                                                            <p class="text-capital">yes %</p>
                                                            <h4>100</h4>
                                                        </td>
                                                        <td>
                                                            <p class="text-capital">no % </p>
                                                            <h4>100</h4>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="b_right1">
                                                            <img src="newCoreDashBoard/img/tv5Logo.png" class="channelLogo" alt="Ntv Logo"/>tv5
                                                        </td>
                                                        <td>
                                                            <p class="text-capital">total questions</p>
                                                            <h4>100</h4>
                                                        </td>
                                                        <td>
                                                            <p class="text-capital">yes %</p>
                                                            <h4>100</h4>
                                                        </td>
                                                        <td>
                                                            <p class="text-capital">no % </p>
                                                            <h4>100</h4>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="b_right1">
                                                            <img src="newCoreDashBoard/img/tv9Logo.png" class="channelLogo" alt="Ntv Logo"/>tv9
                                                        </td>
                                                        <td>
                                                            <p class="text-capital">total questions</p>
                                                            <h4>100</h4>
                                                        </td>
                                                        <td>
                                                            <p class="text-capital">yes %</p>
                                                            <h4>100</h4>
                                                        </td>
                                                        <td>
                                                            <p class="text-capital">no % </p>
                                                            <h4>100</h4>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="b_right1">
                                                            <img src="newCoreDashBoard/img/ntvLogo.png" class="channelLogo" alt="Ntv Logo"/>NTV
                                                        </td>
                                                        <td>
                                                            <p class="text-capital">total questions</p>
                                                            <h4>100</h4>
                                                        </td>
                                                        <td>
                                                            <p class="text-capital">yes %</p>
                                                            <h4>100</h4>
                                                        </td>
                                                        <td>
                                                            <p class="text-capital">no % </p>
                                                            <h4>100</h4>
                                                        </td>
                                                    </tr>
                                                 </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>-->
                        </div>
                    </div>
                </div>
            </div>
            <!-- DEBATES PROGRAM BLOCK END-->
			<!-- NEWS BLOCK START-->
			<div class="col-md-6 col-xs-12 col-sm-12 newsBlock">
				<div class="panel panel-default panelNewCustom">
					<div class="panel-heading">
						<h4 class="panel-title">news
							<span class="newsIconExpand pull-right">
								<i class="glyphicon glyphicon-fullscreen"></i>
							</span>
						</h4>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-12 col-xs-12 col-sm-12 newsBlock">
								<h4 class="text-capital"><span class="headingColor"><img src="dist/img/tdpIcon.png" alt="tdp icon" class="newsIcon"/>telugu desam party</span></h4>
								<div class="row">
									<div class="col-md-6 col-xs-12 col-sm-12 m_top20 ">
										<table class="table table-condensed tableNews bg_ED">
											<tr>
												<td>
													<p class="text-capital">main edition</p>
													<p>1000</p>
												</td>
												<td>
													<p class="text-capital text-muted">positive</p>
													<p>1000<small class="text-success">40%</small></p>
												</td>
												<td>
													<p class="text-capital text-muted">negative</p>
													<p>1000<small class="text-success">40%</small></p>
												</td>
											</tr>
										</table>
									</div>
									<div class="col-md-6 col-xs-12 col-sm-12 m_top20 ">
										<table class="table table-condensed tableNews bg_ED">
											<tr>
												<td>
													<p class="text-capital">main edition</p>
													<p>1000</p>
												</td>
												<td>
													<p class="text-capital text-muted">positive</p>
													<p>1000<small class="text-success">40%</small></p>
												</td>
												<td>
													<p class="text-capital text-muted">negative</p>
													<p>1000<small class="text-success">40%</small></p>
												</td>
											</tr>
										</table>
									</div>
								</div>
								<h4 class="text-capital m_top20"><span class="headingColor">opposition parties</span></h4>
								<div class="row">
									<div class="col-md-6 col-xs-12 col-sm-12 m_top20 ">
										<table class="table table-condensed tableNews bg_ED">
											<tr>
												<td>
													<p class="text-capital">main edition</p>
													<p>1000</p>
												</td>
												<td>
													<p class="text-capital text-muted">positive</p>
													<p>1000<small class="text-success">40%</small></p>
												</td>
												<td>
													<p class="text-capital text-muted">negative</p>
													<p>1000<small class="text-success">40%</small></p>
												</td>
											</tr>
										</table>
										<table class="table table-condensed tableNews m_top10">
											<tr>
												<td>
													<p><img src="dist/img/congLogo.png" alt="cong logo" class="newsIcon"/>250</p>
												</td>
												<td>
													<p>1000<small class="text-success">40%</small></p>
												</td>
												<td>
													<p>1000<small class="text-success">40%</small></p>
												</td>
											</tr>
											<tr>
												<td>
													<p><img src="dist/img/congLogo.png" alt="cong logo" class="newsIcon"/>250</p>
												</td>
												<td>
													<p>1000<small class="text-success">40%</small></p>
												</td>
												<td>
													<p>1000<small class="text-success">40%</small></p>
												</td>
											</tr>
											<tr>
												<td>
													<p><img src="dist/img/congLogo.png" alt="cong logo" class="newsIcon"/>250</p>
												</td>
												<td>
													<p>1000<small class="text-success">40%</small></p>
												</td>
												<td>
													<p>1000<small class="text-success">40%</small></p>
												</td>
											</tr>
										</table>
									</div>
									<div class="col-md-6 col-xs-12 col-sm-12 m_top20 ">
										<table class="table table-condensed tableNews bg_ED">
											<tr>
												<td>
													<p class="text-capital">main edition</p>
													<p>1000</p>
												</td>
												<td>
													<p class="text-capital text-muted">positive</p>
													<p>1000<small class="text-success">40%</small></p>
												</td>
												<td>
													<p class="text-capital text-muted">negative</p>
													<p>1000<small class="text-success">40%</small></p>
												</td>
											</tr>
										</table>
										<table class="table table-condensed tableNews m_top10">
											<tr>
												<td>
													<p><img src="dist/img/congLogo.png" alt="cong logo" class="newsIcon"/>250</p>
												</td>
												<td>
													<p>1000<small class="text-success">40%</small></p>
												</td>
												<td>
													<p>1000<small class="text-success">40%</small></p>
												</td>
											</tr>
											<tr>
												<td>
													<p><img src="dist/img/congLogo.png" alt="cong logo" class="newsIcon"/>250</p>
												</td>
												<td>
													<p>1000<small class="text-success">40%</small></p>
												</td>
												<td>
													<p>1000<small class="text-success">40%</small></p>
												</td>
											</tr>
											<tr>
												<td>
													<p><img src="dist/img/congLogo.png" alt="cong logo" class="newsIcon"/>250</p>
												</td>
												<td>
													<p>1000<small class="text-success">40%</small></p>
												</td>
												<td>
													<p>1000<small class="text-success">40%</small></p>
												</td>
											</tr>
										</table>
									</div>
								</div>
								<h4 class="text-capital m_top20"><span class="headingColor">government</span></h4>
								<div class="row">
									<div class="col-md-6 col-xs-12 col-sm-12 m_top20 ">
										<table class="table table-condensed tableNews bg_ED">
											<tr>
												<td>
													<p class="text-capital">main edition</p>
													<p>1000</p>
												</td>
												<td>
													<p class="text-capital text-muted">positive</p>
													<p>1000<small class="text-success">40%</small></p>
												</td>
												<td>
													<p class="text-capital text-muted">negative</p>
													<p>1000<small class="text-success">40%</small></p>
												</td>
											</tr>
										</table>
									</div>
									<div class="col-md-6 col-xs-12 col-sm-12 m_top20 ">
										<table class="table table-condensed tableNews bg_ED">
											<tr>
												<td>
													<p class="text-capital">main edition</p>
													<p>1000</p>
												</td>
												<td>
													<p class="text-capital text-muted">positive</p>
													<p>1000<small class="text-success">40%</small></p>
												</td>
												<td>
													<p class="text-capital text-muted">negative</p>
													<p>1000<small class="text-success">40%</small></p>
												</td>
											</tr>
										</table>
									</div>
								</div>
							</div>
							<div class="col-md-6 col-xs-12 col-sm-12 newsHiddenBlock">
								<div class="row">
									<div class="col-md-6 col-md-offset-6 col-xs-12 col-sm-6 col-sm-offset-6">
										<ul class="activeUlCls list-inline">
											<li>top 5 strong</li>
											<li>last 5 poor</li>
										</ul>
									</div>
									<div class="col-md-12 col-xs-12 col-sm-12">
										<h4 class="text-capital">general secretary</h4>
										<div id="newsSec" style="width:100%;height:150px;"></div>
									</div>
									<div class="col-md-12 col-xs-12 col-sm-12">
										<h4 class="text-capital">secretary</h4>
										<div id="newsGenSec" style="width:100%;height:150px;"></div>
									</div>
									<div class="col-md-12 col-xs-12 col-sm-12">
										<h4 class="text-capital">member of parliament</h4>
										<div id="newsDistInch" style="width:100%;height:150px;"></div>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-12">
										<i data-placement="top" data-toggle="tooltip" class="glyphicon glyphicon-option-horizontal pull-right morenewsBlocksIcon" title="Click here for more"></i>
									</div>	
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- NEWS BLOCK END-->
        </div>
	</div>
</div>
<button  style="display:none" class="userStructureClass" attr_activityMemberId="1" attr_userTypeId="3" attr_userAccessLevelId="3" attr_userAccessLevelValuesString="11,12,15" > ActivityMember </button>
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/newCoreDashboard.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/coreDashboardTrainingProgram.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/debates.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/KChart.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/tdpCommittees.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Slick/slick.js" type="text/javascript"></script>
<script src="dist/DateRange/moment.js" type="text/javascript"></script>
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Rating/star-rating.min.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mousewheel.js" type="text/javascript"></script>
<script type="text/javascript">
	DatePickerDropdown();
	function DatePickerDropdown()
	{
		$(".thisWeek").find(".text-muted").html("("+moment().subtract(7, 'days').startOf('week').format("dddd") + "- today)")
		$(".last7Days").html("("+moment().subtract(7, 'days').format("DD/MM/YY") + "-"+moment().format("DD/MM/YY")+")");
		$(".lastWeekDate").html("("+moment().subtract(7, 'days').startOf('week').format("DD/MM/YY") + "-"+moment().subtract(7, 'days').endOf('week').format("DD/MM/YY")+")");
		$(".thisMonth").html("("+moment().startOf('month').format("DD/MM/YY")+'-'+moment().endOf('month').format("DD/MM/YYYY")+")");
		$(".lastMonth").html("("+moment().subtract(1,'month').startOf('month').format("DD/MM/YYYY")+'-'+moment().subtract(1,'month').endOf('month').format("DD/MM/YYYY")+")");
		$(".last28Days").html("("+moment().subtract(28,'days').format("DD/MM/YY")+'-'+moment().format("DD/MM/YY")+")");
		$(".last30Days").html("("+moment().subtract(30,'days').format("DD/MM/YY")+'-'+moment().format("DD/MM/YY")+")");
		$(".first28Days").html("("+moment().startOf('month').format("DD/MM/YYYY")+'-'+moment().startOf('month').add(28,'days').format("DD/MM/YY")+")");
		$(".thisQuarter").html("("+moment().startOf('quarter').format('MMMM')+'-'+moment().endOf('quarter').format('MMMM')+")");
		$(".lastQuarter").html("("+moment().subtract(1,'quarter').startOf('quarter').format('MMMM')+'-'+moment().subtract(1,'quarter').endOf('quarter').format('MMMM')+")");
		$(".Last90Days").html("("+moment().subtract(90,'days').format('DD/MM/YY')+'-'+moment().format('DD/MM/YY')+")");
		$(".lastYear").html("("+moment().subtract(1,'year').startOf('year').format('YYYY')+'-'+moment().subtract(1,'year').endOf('year').format('YYYY')+")");
		$(".thisYear").html("("+moment().startOf('year').format('YYYY')+'-'+moment().endOf('year').format('YYYY')+")");
		$(".last365Days").html("("+moment().subtract(365,'days').format('DD/MM/YY')+'-'+moment().format('DD/MM/YY')+")");
		$(".calendarIcon").daterangepicker({opens:'left',parentEl:$(this).closest(".row")})
		$(".calenarUl li:last-child").click(function(){
		  $(this).closest(".list-inline").find(".calendarIcon").trigger("click");
		  $(this).closest(".dropdown").removeClass("open")
		});
		$(document).on("click",".calenarUl li",function(){
		  $(".calenarUl li").removeClass("active")
		  $(this).addClass("active")
		  var kk = $(this).html();
		  $(document).find(".changeDate").html(kk);
		});
	}
	
/* New Design */
      initialiseDatePicker();
	  initialiseDatePickerForTrainingProgram();
	  var loggedInUserId = '${sessionScope.USER.registrationID}';
	  var loggedInUserActivityMemberId = '${requestScope.userDataVO.activityMemberId}';
	  var loggedInUserTypeId = '${requestScope.userDataVO.userTypeId}'; 
	  var loggedInUserAccessLevelId  = '${requestScope.userDataVO.userAccessLevelId}';
	  var loggedInUserAccessLevelValues = getLoggedInUserAccessLevelValues();
	  
	  var globalActivityMemberId = loggedInUserActivityMemberId;
	  var globalUserTypeId = loggedInUserTypeId;
	  var globalUserAccessLevelId = loggedInUserAccessLevelId;
	  var globalUserAccessLevelValues = loggedInUserAccessLevelValues;
	  
	  var globalState = 'AP';
	  
	   var globalBasicCommitteeIdsArray = []; //basicCommitteeIdsArray
	  getCheckedBasicCommitteeIds(); 
	  
	  
	$(document).ready(function(){
		//Main header remove
		$(".eventsheader").hide();
		$('[data-toggle="tooltip"]').tooltip();
	});
	getLoggedInUserStructure();
	onLoadCalls();
	function onLoadCalls(){
		getCommitteesBasicCountReport();
		//training program call
		getTrainingCampBasicDetailsCntOverview();
	}
	
	
	$(document).on("click",".userStructureClass",function(){
		
		$("#directChildActivityMemberDiv").html('');
		$("#topPoorPerformanceDiv").html('');
		$("#topPoorLocationsDiv").html(''); 
		
        var  clickedActivityMemberId = $(this).attr("attr_activityMemberId");
		var  clickedUserTypeId = $(this).attr("attr_userTypeId");
		var  clickedUserAccessLevelId =  $(this).attr("attr_userAccessLevelId");
		var  clickedUserAccessLevelValuesString = $(this).attr("attr_userAccessLevelValuesString");
		var  selectedMemberName = $(this).attr("attr_selectedmembername");
		var  selectedUserType = $(this).attr("attr_selectedusertype");
		var  clickedChildActivityMemberId = $(this).attr("attr_selectedusertype");
		
		var  clickedUserAccessLevelValuesArray = [];
		if($.trim(clickedUserAccessLevelValuesString).length > 0){
			clickedUserAccessLevelValuesArray = clickedUserAccessLevelValuesString.split(",");
		}
		//MAKE SELECTED MEMBER DATA AS GLOBAL.
	    globalActivityMemberId = clickedActivityMemberId;
	    globalUserTypeId = clickedUserTypeId;
	    globalUserAccessLevelId = clickedUserAccessLevelId;
	    globalUserAccessLevelValues = clickedUserAccessLevelValuesArray;
		
		onLoadCalls();
		defaultCommitteeCalls();
		
	});
	
	function defaultCommitteeCalls(){
		onLoadCalls();
		
		if($(".iconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".liCls1").each(function(){
				var topvalue = $(this).attr("attr_value");
				if(topvalue == "poor"){
					$(".removeactive").removeClass("active");
					
				}else{
					
					$(".addactive").addClass("active");
				}
			});
			getUserTypeWiseCommitteesCompletedCounts1();
		}
		if($(".moreBlocksIcon").hasClass("unExpandBlock")){
			
			if($(".detailedBlock").hasClass("active")){
				getLevelWiseBasicCommitteesCountReport();
				var tdpCommitteeLevelIdsClickedArray = [];
				tdpCommitteeLevelIdsClickedArray.push(6);
				tdpCommitteeLevelIdsClickedArray.push(8);
				getcommitteesPerformanceCohort(tdpCommitteeLevelIdsClickedArray);
			}
			if($(".comparisionBlock").hasClass("active")){
				getChildUserTypesByItsParentUserType();
			}
		
		}
		
		
		
	}
	
	$(document).on("click",".hideDropDownView",function(){
		$(".profileDropDown").removeClass("dropDownView");
	});
	
	function getLoggedInUserAccessLevelValues(){
		var loggedInUserAccessLevelValues = [];
		<c:forEach items="${userDataVO.userAccessLevelValuesList}" var="userAccessLevelValue">
		  loggedInUserAccessLevelValues.push( ${userAccessLevelValue} );        
	   </c:forEach>
	   return loggedInUserAccessLevelValues;
	}
	

  </script>	
</body>
</html>
