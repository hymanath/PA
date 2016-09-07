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
<link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/Plugins/Slick/slick.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/Slick/slick-theme.css" type="text/css" rel="stylesheet"/>
<link href="dist/DateRange/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/Rating/bootstrap-rating.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/GridStack/gridstack.css" type="text/css" rel="stylesheet"/>
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
                            	<span class="fa fa-sort-desc" aria-hidden="true" style="color: rgb(255, 255, 255); margin-left: -1px;"></span>
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
        	<div class="panel panel-default panelNewCustom panel1">
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
								<i class="glyphicon glyphicon-list-alt"  data-toggle="tooltip" data-placement="top" title="Notes" onClick="displayDashboardComments(1);"></i>
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
					
					<!-- committees tabs for tdpcommittee ids -->	
					<div class="basicCommitteesBlockDropDown" style="z-index:999;margin-top: -3px;" >
						<div class="row">
							<div class="col-md-6 col-xs-12 col-sm-6 pad_right0 m_top20">
							  <ul class="nav nav-tabs navTabsSettings" role="tablist">
								<li role="presentation" class="active text-capital"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">District Level</a></li>
								<li role="presentation" class="text-capital"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Mandal/town/division level</a></li>
								<li role="presentation" class="text-capital"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">village/ward level</a></li>
							  </ul>
							</div>
							<div class="col-md-6 col-xs-12 col-sm-6 pad_left0 pad_right4">
							  <div class="tab-content navTabsSettingsContent">
								<div role="tabpanel" class="tab-pane active" id="home">
									<h4 class="text-capital pad_5" style="color:#99A0A5;">Select Committees</h4>
									<hr style ="margin-bottom:0px;" />
									<div class="basicCommitteeDetailsDiv">
									<ul class="settingsUl">
									<c:forEach items="${userDataVO.subList}" var="basicCommittee">
									   <c:if test="${basicCommittee.id == 1}">
										<li>
											<label class="checkbox-inline">
												<input type="checkbox"  class="districtCommitteecheckBoxClass" value="${basicCommittee.id}" checked>
												<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">${basicCommittee.name}</h5></div>
										   </label>
										</li>	
                                     </c:if>										
									</c:forEach>
									   <li>
										    <label class="checkbox-inline">
											  <input type="checkbox"  id="checkAllAffliatedDistrictlevelId"  checked>
											  <div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;font-weight:bold;">Affiliated committees</h5></div>
										   </label>
										</li> 
										<c:forEach items="${userDataVO.subList}" var="basicCommittee">
											<c:if test="${basicCommittee.id != 1}">
											<li>
											  <label class="checkbox-inline">
												 <input type="checkbox"  class="districtCommitteecheckBoxClass districtCommitteeAffliatedcheckBoxClass" value="${basicCommittee.id}" checked>
												 <div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">${basicCommittee.name}</h5></div>
											   </label>
											</li>   
											</c:if>
									  </c:forEach>
										
									</ul>
									</div>
								</div>
								<div role="tabpanel" class="tab-pane" id="profile">
									<h4 class="text-capital pad_5" style="color:#99A0A5;">Select Committees</h4>
									<hr style ="margin-bottom:0px;" />
									<div class="basicCommitteeDetailsDiv">
									<ul class="settingsUl">
									<c:forEach items="${userDataVO.subList}" var="basicCommittee">
									   <c:if test="${basicCommittee.id == 1}">
										 <li>
										   <label class="checkbox-inline">
											 <input type="checkbox"  class="checkedBasicComm mandalCommitteecheckBoxClass" value="${basicCommittee.id}" checked>
											 <div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">${basicCommittee.name}</h5></div>
										   </label>
										 </li>
									   </c:if>
									</c:forEach>
									    <li>
										    <label class="checkbox-inline">
											  <input type="checkbox"  id="checkAllAffliatedMandallevelId"  >
											  <div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;font-weight:bold;">Affiliated committees</h5></div>
										   </label>
										</li>  
									<c:forEach items="${userDataVO.subList}" var="basicCommittee">
									        <c:if test="${basicCommittee.id != 1}">
											 <c:choose>
											   <c:when test="${basicCommittee.id == 10 || basicCommittee.id == 16}">
											     <li>
											      <label class="checkbox-inline">
												     <input type="checkbox"  class="mandalCommitteecheckBoxClass mandalCommitteeAffliatedcheckBoxClass" value="${basicCommittee.id}">
													 <div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">${basicCommittee.name}</h5></div>
												  </label>
												 </li> 
											   </c:when>
												<c:otherwise>
													<li>
													  <label class="checkbox-inline">
													     <input type="checkbox"  class="mandalCommitteecheckBoxClass mandalCommitteeAffliatedcheckBoxClass" value="${basicCommittee.id}" checked>
													     <div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">${basicCommittee.name}</h5></div>
													   </label>
													 </li> 
												</c:otherwise>
										   </c:choose>
										  </c:if>
									</c:forEach>
									
									</ul>
									</div>
								</div>
								<div role="tabpanel" class="tab-pane" id="messages">
									<h4 class="text-capital pad_5" style="color:#99A0A5;">Select Committees</h4>
									<hr style ="margin-bottom:0px;" />
									<div class="basicCommitteeDetailsDiv">
									<ul class="settingsUl">
									<c:forEach items="${userDataVO.subList}" var="basicCommittee">
										   <c:if test="${basicCommittee.id == 1}">
											 <li>
											   <label class="checkbox-inline">
												 <input type="checkbox"  class="villageCommitteecheckBoxClass" value="${basicCommittee.id}" checked>
												 <div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">${basicCommittee.name}</h5></div>
											   </label>
											 </li>
										   </c:if>
									</c:forEach>
									     <li>
										    <label class="checkbox-inline">
											  <input type="checkbox"  id="checkAllAffliatedVillagelevelId"  >
											  <div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;font-weight:bold;">Affiliated committees</h5></div>
										   </label>
										</li>  
									
									<c:forEach items="${userDataVO.subList}" var="basicCommittee">
										 	<c:if test="${basicCommittee.id != 1}">
											<li>
											  <label class="checkbox-inline">
												 <input type="checkbox"  class="villageCommitteecheckBoxClass villageCommitteeAffliatedcheckBoxClass" value="${basicCommittee.id}">
												 <div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">${basicCommittee.name}</h5></div>
											   </label>
											</li>   
											</c:if>
									</c:forEach>
									</ul>
									</div>
								</div>
							  </div>
							  <button type="button" class="btn btn-success basicCommittessDiv">Get Details</button>
							</div>
						
						</div>
						
						
					</div>
					<div class="notesDropDown">
                    	<h4 class="text-capital">notes
                        	<span class="pull-right">
                            	<i class="glyphicon glyphicon-list-alt"></i>
                            </span>
                        </h4>
						<div id="notesId"></div>
                    	<!--<ul class="notesUl m_top20">
                        	<li>
                            	<!--<span class="notesText">notes on committees notes on committee notes on committee notes on committee notes on committee </span>- <span class="text-muted"><i>20-July 2016</i></span>
                            	<i class="glyphicon glyphicon-trash pull-right hoverBlock deleteNotes"></i>
                                <i class="glyphicon glyphicon-edit pull-right hoverBlock editNotes"></i>
                            </li>
                        </ul>
                        <hr/>        
                        <label>Create Notes</label>
                        <textarea class="form-control notesArea"></textarea>
                        <button class="btn btn-default btnCustomCreate btn-sm" onClick="savingDashboardComment();">create</button>-->
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
										<li class="liCls1 active addactive" attr_value="strong"><i class="fa fa-arrow-up"></i>&nbsp;top 5 strong</li>
										<li class="liCls1 removeactive" attr_value="poor"><i class="fa fa-arrow-down"></i>&nbsp;last 5 poor</li>
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
                                <div class="panel-body verticalScrollBar">
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
                                                <div id="directChildActivityMemberDiv" class="table-responsive"></div>
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
			<div class="panel panel-default panelNewCustom panel1">
				<div class="panel-heading">
					<h4 class="panel-title">
						<img src="newCoreDashBoard/img/training.png" class="iconClass"/>
						training
						<span class="settingsIcon pull-right">
                        	<i class="fa fa-gears"  data-toggle="tooltip" data-placement="top" title="Settings"></i>
                        </span>
						<span class="notesIconTraining pull-right">
							<i class="glyphicon glyphicon-list-alt"  data-toggle="tooltip" data-placement="top" title="Notes" onClick="displayDashboardCommentsForTraining(2);"></i>
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
						<div class="notesDropDown" >
							<h4 class="text-capital">notes
								<span class="pull-right">
									<i class="glyphicon glyphicon-list-alt"></i>
								</span>
							</h4>
							<div id="notesTrainingId"></div>
							<!--<ul class="notesUl m_top20">
								<li>
									<span class="notesText">notes on committees notes on committee notes on committee notes on committee notes on committee </span>- <span class="text-muted"><i>20-July 2016</i></span>
									<i class="glyphicon glyphicon-trash pull-right hoverBlock deleteNotes"></i>
									<i class="glyphicon glyphicon-edit pull-right hoverBlock editNotes"></i>
								</li>
							</ul>
							<hr/>        
							<label>Create Notes</label>
							<textarea class="form-control notesArea"></textarea>
							<button class="btn btn-default btnCustomCreate btn-sm">create</button>-->
						</div>  
					</h4>
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
										<li class="liCls active" attr_value="strong"><i class="fa fa-arrow-up"></i>&nbsp;top 5 strong</li>
										<li class="liCls" attr_value="poor"><i class="fa fa-arrow-down"></i>&nbsp;last 5 poor</li>
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
                <div class="panel panel-default panelNewCustom panel2">
                    <div class="panel-heading">
						<div class="row">
							<div class="col-md-6 col-sm-8 col-xs-7">
								<h4 class="panel-title text-capital">
									<img class="iconClass" src="newCoreDashBoard/img/debates.png">
									debates
									
								</h4>
							</div>
							<div class="col-md-6 col-sm-4 col-xs-5">
								<span class="debatesIconExpand pull-right">
									<i class="glyphicon glyphicon-fullscreen"></i>
								</span>
								<span class="input-group pull-right dateRangePickerClsForDebates hide" style="width:200px;">
									<input type="text" id="dateRangeIdForDebates" style="width:180px" class="form-control" />
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-calendar"></i>
									</span>
								</span>
							</div>
						</div>
                       
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12 col-xs-12 col-sm-12 debatesBlock">
                                <div class="row">
									<div id="partyWiseTotalDebateDetails"></div>
									<!--<div ng-app="partyWiseTotalApp">
										<div ng-controller="partyWiseTotalCont">
											<div class="col-md-12 col-xs-12 col-sm-12 col-md-offset-0 m_top10" ng-repeat="item in partyWiseTotal">
												<h4 class="text-capital"><img src="newCoreDashBoard/img/{{item.name}}.png" alt="Icon" class="debatesPartyIcon"/>{{item.name}}</h4>
												<table class="table tableTraining bg_ED m_top10">
												  <tbody>
													<tr>
														<td>
															<p class="text-capital">total debates</p>
															<h4>{{item.debateCount}}</h4>
														</td>
														<td>
															<p class="text-capital">total spokes persons</p>
															<h4>{{item.candidateCount}}</h4>
														</td>
														<td>
															<p class="text-capital">performance</p>
															<input class="performanceRating" value="{{item.scalePerc}}" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly>
															<span class="label label-default">{{item.scalePerc}}</span>
														</td>
													</tr>
												 </tbody>
												</table>
											</div>
										</div>
									</div>-->
                                 </div>
                            </div>
                            <div class="col-md-6 col-xs-12 col-sm-12 debatesHiddenBlock">
                                <div class="row">
                                	<div class="col-md-6 col-xs-12 col-sm-12">
                                		<h4 class="text-capital"><span class="headingColor">Spokespersons</span></h4>
                                	</div>
                                    <div class="col-md-6 col-xs-12 col-sm-12 ">
                                        <ul class="activeUlCls list-inline debateSpokesCls">
                                            <li id="debateTopId" class="active"><i class="fa fa-arrow-up"></i>&nbsp;top 5 strong</li>
                                            <li id="debateLowId"><i class="fa fa-arrow-down"></i>&nbsp;last 5 poor</li>
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
								<div class="panel-group" id="debatesCollapse" role="tablist" aria-multiselectable="true">
									<div class="panel panel-default panelNew">
										<div class="panel-heading" role="tab" id="headingOne">
											<a role="button" class="collapseDebatesIcon" data-toggle="collapse" data-parent="#debatesCollapse" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
												<h4><span class="headingColor text-capitalize">Scale based performance cohort</span></h4>
											</a>
										</div>
										<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
											<div class="panel-body">
												<div class="row">
													<div id="scaleBasedPerformanceCohort"></div>
												</div>
											</div>
										</div>
									</div>
								  <div class="panel panel-default panelNew">
									<div class="panel-heading" role="tab" id="headingTwo">
										<a class="collapsed collapseDebatesIcon" role="button" data-toggle="collapse" data-parent="#debatesCollapse" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
										   <h4 class="panel-title"><span class="headingColor text-capitalize">role based performance cohort</span></h4>
										</a>
									</div>
									<div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
									  <div class="panel-body">
										<div class="row">
												<div id="roleBasedPerformanceCohort"></div>
										</div>
									  </div>
									</div>
								  </div>
								  <div class="panel panel-default panelNew">
									<div class="panel-heading" role="tab" id="headingFive">
										<a class="collapsed collapseDebatesIcon" role="button" data-toggle="collapse" data-parent="#debatesCollapse" href="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
										   <h4 class="panel-title"><span class="headingColor text-capitalize">Top 5 Performance</span></h4>
										</a>
									</div>
									<div id="collapseFive" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFive">
									  <div class="panel-body">
										<div class="row">
											<div class="table-responsive" id="candidateRolesPerformanceNewId"></div>
										</div>
									  </div>
									</div>
								  </div>
								  <!-- TOP 5 BLock Start -->
							<!--<div class="col-md-12 col-xs-12 col-sm-12 m_top20">-->
                                <!--<div class="panel panel-default panelNew">
                                    <div class="panel-heading">
										<div class="row">
											<div class="col-md-6 col-xs-12 col-sm-6">
												<h4><span class="headingColor text-capitalize">Top 5 Performers</span></h4>
											</div>
											<div class="col-md-6 col-xs-12 col-sm-6">
												<ul class="activeUlCls list-inline pull-right candidateRolesCls">
													<li value="1">Critic</li>
													<li value="2">Bully</li>
												</ul>
											</div>
										</div>
                                        
                                    </div>
									
                                    <div class="panel-body">
                                        <div class="row">
											<div class="col-md-12 col-xs-12 col-sm-12">
												<div class="table-responsive" id="candidateRolesPerformanceId"													
												</div>
											</div>
                                        </div>
                                    </div>
                                </div>-->
                           <!-- </div> -->
                            <!-- TOP 5 BLock End -->
								  
								  <div class="panel panel-default panelNew">
									<div class="panel-heading" role="tab" id="headingThree">
										<a class="collapsed collapseDebatesIcon" role="button" data-toggle="collapse" data-parent="#debatesCollapse" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
										   <h4><span class="headingColor text-capitalize">candidate overall performance cohort</span></h4>
										</a>
									</div>
									<div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
									  <div class="panel-body">
										<div class="row">
											<div class="col-md-12 col-xs-12 col-sm-12">
												<div id="candidateOverAllPerformanceCohort"></div>
											</div>
										</div>
									  </div>
									</div>
								  </div>
								  <div class="panel panel-default panelNew">
									<div class="panel-heading" role="tab" id="headingFour">
										<a class="collapsed collapseDebatesIcon" role="button" data-toggle="collapse" data-parent="#debatesCollapse" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
										   <h4><span class="headingColor text-capitalize">channel vs parties</span></h4>
										</a>
									</div>
									<div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
									  <div class="panel-body">
										<div class="row">
											<div class="col-md-12 col-xs-12 col-sm-12">
												<div id="channelAndPartyWiseDetails"></div>
											</div>
										</div>
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
			<!--  NEWS BLOCK START-->
			<div class="col-md-6 col-xs-12 col-sm-12 newsBlock">
				<div class="panel panel-default panelNewCustom panel2">
					<div class="panel-heading">
						<div class="row">
							<div class="col-md-6 col-sm-8 col-xs-7">
								<h4 class="panel-title text-capital">
									<img src="newCoreDashBoard/img/news.png" class="iconClass"/>
									news
								</h4>
							</div>
							<div class="col-md-6 col-sm-4 col-xs-5">
								<span class="newsIconExpand pull-right">
									<i class="glyphicon glyphicon-fullscreen"></i>
								</span>
								<span class="input-group pull-right dateRangePickerClsForNews hide" style="width:200px;">
									<input type="text" id="dateRangeIdForNews" style="width:180px" class="form-control" />
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-calendar"></i>
									</span>
								</span>
							</div>
						</div>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-12 col-xs-12 col-sm-12 newsBlock">
								<h4 class="text-capital"><span class="headingColor"><img src="newCoreDashBoard/img/TDP.png" alt="tdp icon" class="newsIcon"/>Telugu Desam Party</span></h4>
								<div class="row">
									<div class="col-md-6 col-xs-12 col-sm-12 m_top20 ">
										<table class="table table-condensed tableNews bg_ED">
											<tr>
												<td>
													<p class="text-capital">Main Edition</p>
													<p id="tdpMainTotal">0</p>
												</td>
												<td>
													<p class="text-capital text-muted">Positive</p>
													<span id="tdpMainPositive">0</span><small id="tdpMainPositivePercent" class="text-success">0%</small>
												</td>
												<td>
													<p class="text-capital text-muted">Negative</p>
													<span id="tdpMainNegative">0</span><small id="tdpMainNegativePercent" class="text-success">0%</small>
												</td>
											</tr>
										</table>
									</div>
									<div class="col-md-6 col-xs-12 col-sm-12 m_top20 ">
										<table class="table table-condensed tableNews bg_ED">
											<tr>
												<td>
													<p class="text-capital">Dist edition</p>
													<p id="tdpDistTotal">0</p>
												</td>
												<td>
													<p class="text-capital text-muted">Positive</p>
													<span id="tdpDistPositive">0</span><small class="text-success" id="tdpDistPositivePercent">0%</small>
												</td>
												<td>
													<p class="text-capital text-muted">Negative</p>
													<span id="tdpDistNegative">0</span><small class="text-success" id="tdpDistNegativePercent">0%</small>
												</td>
											</tr>
										</table>
									</div>
								</div>
								<h4 class="text-capital m_top20"><span class="headingColor">Opposition Parties</span></h4>
								<div class="row">
									<div class="col-md-6 col-xs-12 col-sm-12 m_top20 ">
										<table class="table table-condensed tableNews bg_ED">
											<tr>
												<td>
													<p class="text-capital">Main Edition</p>
													<p id="oppMainTotal">0</p>
												</td>
												<td>
													<p class="text-capital text-muted">Positive</p>
													<span id="oppPositiveTotal">0</span><small class="text-success" id="oppPositiveTotalPercent">0%</small>
												</td>
												<td>
													<p class="text-capital text-muted">Negative</p>
													<span id="oppNegativeTotal">0</span><small class="text-success" id="oppNegativeTotalPercent">0%</small>
												</td>
											</tr>
										</table>
										<table class="table table-condensed tableNews m_top10">
											<tr>
												<td>
													<img src="newCoreDashBoard/img/YSRC.png" alt="cong logo" class="newsIcon"/><span id="ysrcMainTotal">0</span>
												</td>
												<td>
													<span id="ysrcMainPositive">0</span><small class="text-success" id="ysrcMainPositivePercent">0%</small>
												</td>
												<td>
													<span id="ysrcMainNegative">0</span><small class="text-success" id="ysrcMainNegativePercent">0%</small>
												</td>
											</tr>
											<tr>
												<td>
													<img src="newCoreDashBoard/img/INC.png" alt="cong logo" class="newsIcon"/><span id="incMainTotal">0</span>
												</td>
												<td>
													<span id="incMainPositive">0</span><small class="text-success" id="incMainPositivePercent">0%</small>
												</td>
												<td>
													<span id="incMainNegative">0</span><small class="text-success" id="incMainNegativePercent">0%</small>
												</td>
											</tr>
											<tr>
												<td>
													<img src="newCoreDashBoard/img/BJP.png" alt="cong logo" class="newsIcon"/><span id="bjpMainTotal">0</span>
												</td>
												<td>
													<span id="bjpMainPositive">0</span><small class="text-success" id="bjpMainPositivePercent">0%</small>
												</td>
												<td>
													<span id="bjpMainNegative">0</span><small class="text-success" id="bjpMainNegativePercent">0%</small>
												</td>
											</tr>
										</table>
									</div>
									<div class="col-md-6 col-xs-12 col-sm-12 m_top20 ">
										<table class="table table-condensed tableNews bg_ED">
											<tr>
												<td>
													<p class="text-capital">Dist Edition</p>
													<p id="oppDistTotal">0</p>
												</td>
												<td>
													<p class="text-capital text-muted">Positive</p>
													<span id="oppDistPositive">0</span><small class="text-success" id="oppDistPositivePercent">0%</small>
												</td>
												<td>
													<p class="text-capital text-muted">Negative</p>
													<span id="oppDistNegative">0</span><small class="text-success" id="oppDistNegativePercent">0%</small>
												</td>
											</tr>
										</table>
										<table class="table table-condensed tableNews m_top10">
											<tr>
												<td>
													<img src="newCoreDashBoard/img/YSRC.png" alt="cong logo" class="newsIcon"/><span id="ysrcDistTotal">0</span>
												</td>
												<td>
													<span id="ysrcDistPositive">0</span><small class="text-success" id="ysrcDistPositivePercent">0%</small>
												</td>
												<td>
													<span id="ysrcDistNegative">0</span><small class="text-success" id="ysrcDistNegativePercent">0%</small>
												</td>
											</tr>
											<tr>
												<td>
													<img src="newCoreDashBoard/img/INC.png" alt="cong logo" class="newsIcon"/><span id="incDistTotal">0</span>
												</td>
												<td>
													<span id="incDistPositive">0</span><small class="text-success" id="incDistPositivePercent">0%</small>
												</td>
												<td>
													<span id="incDistNegative">0</span><small class="text-success" id="incDistNegativePercent">0%</small>
												</td>
											</tr>
											<tr>
												<td>
													<img src="newCoreDashBoard/img/BJP.png" alt="cong logo" class="newsIcon"/><span id="bjpDistTotal">0</span>
												</td>
												<td>
													<span id="bjpDistPositive">0</span><small class="text-success" id="bjpDistPositivePercent">0%</small>
												</td>
												<td>
													<span id="bjpDistNegative">0</span><small class="text-success" id="bjpDistNegativePercent">0%</small>
												</td>
											</tr>
										</table>
									</div>
								</div>
								<h4 class="text-capital m_top20"><span class="headingColor"><img src="newCoreDashBoard/img/GOVT.png" style="width:25px;" alt="tdp icon" class="newsIcon"/>Government</span></h4>
								<div class="row">
									<div class="col-md-6 col-xs-12 col-sm-12 m_top20 ">
										<table class="table table-condensed tableNews bg_ED">
											<tr>
												<td>
													<p class="text-capital">Main Edition</p>
													<p id="govtMainTotal">0</p>
												</td>
												<td>
													<p class="text-capital text-muted">Positive</p>
													<span id="govtMainPositive">0</span><small class="text-success" id="govtMainPositivePercent">0%</small>
												</td>
												<td>
													<p class="text-capital text-muted">Negative</p>
													<span id="govtMainNegative">0</span><small class="text-success" id="govtMainNegativePercent">0%</small>
												</td>
											</tr>
										</table>
									</div>
									<div class="col-md-6 col-xs-12 col-sm-12 m_top20 ">
										<table class="table table-condensed tableNews bg_ED">
											<tr>
												<td>
													<p class="text-capital">Dist Edition</p>
													<p id="govtDistTotal">0</p>
												</td>
												<td>
													<p class="text-capital text-muted">positive</p>
													<span id="govtDistPositive">0</span><small class="text-success" id="govtDistPositivePercent">0%</small>
												</td>
												<td>
													<p class="text-capital text-muted">negative</p>
													<span id="govtDistNegative">0</span><small class="text-success" id="govtDistNegativePercent">0%</small>
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
											<li class="active"><i class="fa fa-arrow-up"></i>&nbsp;top 5 strong</li>
											<li><i class="fa fa-arrow-down"></i>&nbsp;last 5 poor</li>
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
	<!-- MEETING BLOCK START  -->
	<div class="row">
        <div class="col-md-6 col-xs-12 col-sm-8 col-sm-offset-2 col-md-offset-0 meetingsBlock">
        	<div class="panel panel-default panelNewCustom">
            	<div class="panel-heading">
					<div class="row">
						<div class="col-md-6 col-sm-8 col-xs-7">
							<h4 class="panel-title text-capital">
								<img src="newCoreDashBoard/img/debates.png" class="iconClass"/>
								meetings
							</h4>
						</div>
						<div class="col-md-6 col-sm-4 col-xs-5">
							<span class="meetingsIconExpand pull-right">
								<i class="glyphicon glyphicon-fullscreen"></i>
							</span>
							<span class="input-group pull-right dateRangePickerClsForMeetings hide" style="width:200px;">
								<input type="text" id="dateRangeIdForMeetings" style="width:180px" class="form-control" />
								<span class="input-group-addon">
									<i class="glyphicon glyphicon-calendar"></i>
								</span>
							</span>
						</div>
					</div>
                </div>
                <div class="panel-body">
                	<div class="row">
                    	<div class="col-md-12 col-xs-12 col-sm-12 meetingsBlock">
                        	<div class="panelBlock">
                            	<h4><span class="headingColor text-capitalize">committee meetings</span></h4>
								 <div id="meetingBasicCountDivId"></div>
                            </div>
					        <!--<div class="panelBlock m_top10">
                            	<h4><span class="headingColor text-capitalize">state level meetings</span></h4>
                                <div class="row scrollDiv">
                                	<div class="col-md-12 col-xs-12 col-sm-10 col-sm-offset-1 col-md-offset-0 m_top10">
                                        <h4 class="text-capitalize m_top10">general body meetings</h4>
                                        <table class="table tableTraining bg_ED">
                                            <tbody><tr>
                                                <td>
                                                    <p class="text-muted text-capitalize">total</p>
                                                    <h4>10000</h4>
                                                </td>
                                                <td>
                                                    <p class="text-muted text-capitalize">yes</p>
                                                    <h4>10000 <span class="font-10 text-success">50%</span></h4>
                                                </td>
                                                <td>
                                                    <p class="text-muted text-capitalize">no</p>
                                                    <h4>10000 <span class="font-10 text-success">50%</span></h4>
                                                </td>
                                                <td>
                                                    <p class="text-muted text-capitalize">may be</p>
                                                    <h4>10000 <span class="font-10 text-success">50%</span></h4>
                                                </td>
                                            </tr>
                                        </tbody></table>
                                        <hr class="m_0">
                                    </div>
                                    <div class="col-md-12 col-xs-12 col-sm-10 col-sm-offset-1 col-md-offset-0 m_top10">
                                        <h4 class="text-capitalize m_top10">coordinate meetings</h4>
                                        <table class="table tableTraining bg_ED">
                                            <tbody><tr>
                                                <td>
                                                    <p class="text-muted text-capitalize">total</p>
                                                    <h4>10000</h4>
                                                </td>
                                                <td>
                                                    <p class="text-muted text-capitalize">yes</p>
                                                    <h4>10000 <span class="font-10 text-success">50%</span></h4>
                                                </td>
                                                <td>
                                                    <p class="text-muted text-capitalize">no</p>
                                                    <h4>10000 <span class="font-10 text-success">50%</span></h4>
                                                </td>
                                                <td>
                                                    <p class="text-muted text-capitalize">may be</p>
                                                    <h4>10000 <span class="font-10 text-success">50%</span></h4>
                                                </td>
                                            </tr>
                                        </tbody></table>
                                        <hr class="m_0">
                                    </div>
                                </div>
                            </div>-->
                        </div>
                        <div class="col-md-6 col-xs-12 col-sm-12 meetingsHiddenBlock">
                        	<div class="row">
                            	<div class="col-md-6 col-md-offset-6 col-xs-12 col-sm-6 col-sm-offset-6">
                               	<ul class="activeUlCls list-inline ">
										<li class="meetingLiCls active" attr_value="strong">top 5 strong</li>
										<li class="meetingLiCls" attr_value="poor">last 5 poor</li>
									</ul>
                                </div>
								<div id="userTypeWiseTopFiveStrongAndPoorMeetingMemsDivId"></div>
                            </div>
				        </div>
						<div class="col-xs-12 col-sm-12 col-md-12 meetingsHiddenBlock">
                        			<i data-placement="top" data-toggle="tooltip" class="glyphicon glyphicon-option-horizontal pull-right moreMeetingsBlocksIcon" title="Click here for more"></i>
                        </div>	
                        <div class="col-md-12 col-xs-12 col-sm-10 col-sm-offset-1 col-md-offset-0 moreMeetingsBlocks">
                        	<ul class="list-inline pull-right activeUlCls">
                            	<li class="active">Detailed</li>
                                <li>Comparison</li>
                                <li><i class="fa fa-gears"></i></li>
                            </ul>
                        </div>
                        <div class="col-md-12 col-xs-12 col-sm-10 col-sm-offset-1 col-md-offset-0 moreMeetingsBlocks">
                        	<div class="panel panel-default">
                            	<div class="panel-body">
								<div id="meetingLevelHIghChartsDivId" ></div>
                                	<!--<ul class="meetingsAttendanceUl">
                                    	<li>
                                        	<div id="meetingsState" class="chartLi"></div>
   											<ul id="meetings1" class="customLegend"></ul>
                                        </li>
                                        <li>
                                        	<div id="meetingsDistrict" class="chartLi"></div>
   											<ul id="meetings2" class="customLegend"></ul>
                                        </li>
                                        <li>
                                        	<div id="meetingsMandal" class="chartLi"></div>
   											<ul id="meetings3" class="customLegend"></ul>
                                        </li>
                                        <li>
                                        	<div id="meetingsVil" class="chartLi"></div>
   											<ul id="meetings4" class="customLegend"></ul>
                                        </li>
                                    </ul>-->
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12 col-xs-12 col-sm-10 col-sm-offset-1 col-md-offset-0 m_top20 moreMeetingsBlocks">
                        	<div class="panel panel-default panelNew">
                                <div class="panel-heading">
                                    <div class="row">
                                        <div class="col-md-8 col-xs-12 col-sm-12">
                                            <span class="headingColor text-capitalize">meetings performance cohort</span>
                                        </div>
                                        <div class="col-md-4 col-xs-12 col-sm-12">
                                            <ul class="activeUlCls list-inline">
                                                <li class="active">All</li>
                                                <li>May be!</li>
                                                <li>Yes</li>
                                                <li>No</li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel-body">
                                	<div>
                                    	<h4>General Body Meetings</h4>
                                    	<ul class="graphStructure">
                                        	<li>
                                            	<span attr_percent="33"></span>
                                                <p class="name">koushik</p>
                                            </li>
                                            <li>
                                            	
                                            	<span attr_percent="50"></span>
                                                <span attr_percent="20"></span>
                                                <span attr_percent="30"></span>
                                                <p class="name">Kandi</p>
                                            </li>
                                            <li>
                                            	
                                            	<span attr_percent="50"></span>
                                                <span attr_percent="10"></span>
                                                <span attr_percent="12"></span>
                                                <p class="name">Kumar</p>
                                            </li>
                                            <li>
                                            	
                                            	<span attr_percent="50"></span>
                                                <span attr_percent="50"></span>
                                                <p class="name">ITG</p>
                                            </li>
                                            <li>
                                            	
                                            	<span attr_percent="50"></span>
                                                <span attr_percent="50"></span>
                                                <p class="name">koushik</p>
                                            </li>
                                        </ul>
                                    </div>
                                    <hr/>
                                    <div class="m_top20">
                                    	<h4>Coordinate Meetings</h4>
                                    	<ul class="graphStructure">
                                        	<li>
                                            	<span attr_percent="33"></span>
                                                <p class="name">koushik</p>
                                            </li>
                                            <li>
                                            	
                                            	<span attr_percent="33"></span>
                                                <span attr_percent="33"></span>
                                                <span attr_percent="33"></span>
                                                <p class="name">koushik</p>
                                            </li>
                                            <li>
                                            	
                                            	<span attr_percent="33"></span>
                                                <span attr_percent="33"></span>
                                                <span attr_percent="33"></span>
                                                <p class="name">koushik</p>
                                            </li>
                                            <li>
                                            	
                                            	<span attr_percent="50"></span>
                                                <span attr_percent="50"></span>
                                                <p class="name">koushik</p>
                                            </li>
                                            <li>
                                            	
                                            	<span attr_percent="50"></span>
                                                <span attr_percent="50"></span>
                                                <p class="name">koushik</p>
                                            </li>
                                        </ul>
                                    </div>
                                    
                                </div>
                            </div>
                        </div>
                         <div class="col-md-12 col-xs-12 col-sm-12 m_top20 moreMeetingsBlocks">
                            <p><i>Selected:</i> <b>Main Committee</b></p>
                            <div class="panel panel-default panelNew">
                                <div class="panel-heading">
                                    <div class="row">
                                        <div class="col-xs-12 col-sm-7 col-md-8">
                                            <ul class="comparisonSelect">
                                                <li class="active">General Secretary<span class="closeIconComparison"></span></li>
                                                <li>Secretary<span class="closeIconComparison"></span></li>
                                                <li>M.P<span class="closeIconComparison"></span></li>
                                                <li>District Incharge<span class="closeIconComparison"></span></li>
                                                <li>Constituency<span class="closeIconComparison"></span></li>
                                            </ul>
                                        </div>
                                        <div class="col-xs-12 col-sm-5 col-md-4">
                                            <ul class="list-inline">
                                                <li>
                                                    <div class="dropdown">
                                                      <button class="btn btn-default dropdown-toggle dateFinalDiv" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                                        This Week <span class="text-muted">Sunday - Today</span>
                                                        <span class="caret"></span>
                                                      </button>
                                                      <ul class="dropdown-menu calenarUl" aria-labelledby="dropdownMenu1">
                                                        <li><a href="#">LifeTime</a></li>
                                                        <li role="separator" class="divider"></li>
                                                        <li><a href="#">This Week <span class="text-muted">Sunday - Today</span></a></li>
                                                        <li><a href="#">Last Week <span class="text-muted lastWeekDate"></span></a></li>
                                                        <li><a href="#">Last 7 Days <span class="text-muted last7Days"></span></a></li>
                                                        <li><a href="#">First 7 Days <span class="text-muted first7Days"></span></a></li>
                                                        <li role="separator" class="divider"></li>
                                                        <li><a href="#">This Month <span class="text-muted thisMonth"></span></a></li>
                                                        <li><a href="#">Last Month <span class="text-muted lastMonth"></span></a></li>
                                                        <li><a href="#">Last 28 Days <span class="text-muted last28Days"></span></a></li>
                                                        <li><a href="#">Last 30 Days <span class="text-muted last30Days"></span></a></li>
                                                        <li><a href="#">First 28 Days<span class="text-muted first28Days"></span></a></li>
                                                        <li role="separator" class="divider"></li>
                                                        <li><a href="#">This Quarter <span class="text-muted thisQuarter"></span></a></li>
                                                        <li><a href="#">Last Quarter <span class="text-muted lastQuarter"></span></a></li>
                                                        <li><a href="#">Last 90 Days <span class="text-muted Last90Days"></span></a></li>

                                                        <li><a href="#">First 90 Days <span class="text-muted first90Days"></span></a></li>
                                                        <li role="separator" class="divider"></li>
                                                        <li><a href="#">This Year<span class="text-muted thisYear"></span></a></li>
                                                        <li><a href="#">Last Year <span class="text-muted lastYear"></span></a></li>
                                                        <li><a href="#">Last 365 Days <span class="text-muted last365Days"></span></a></li>
                                                        <li><a href="#">First 365 Days <span class="text-muted first365Days"></span></a></li>
                                                        <li role="separator" class="divider"></li>
                                                        <li><a href="#">Custom Range <span class="customRange"></span></a></li>
                                                      </ul>
                                                    </div>
                                                </li>
                                                <li>
                                                    <i class="glyphicon glyphicon-calendar calendarIcon"></i>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12 col-xs-12 col-sm-12">
                                            <ul class="list-inline slickPanelSliderMeetings">
                                                <li>
                                                    <div class="panel panel-default panelSlick">
                                                        <div class="panel-heading">
                                                            <h4 class="panel-title">B Jaya Nageshwara Reddy</h4>
                                                            <span class="count">01</span>
                                                        </div>
                                                        <div class="panel-body">
                                                            <h4 class="text-capital">General Secretary</h4>
                                                            <table class="table table-condensed">
                                                                <thead>
                                                                    <th>Total</th>
                                                                    <th>Started</th>
                                                                    <th>Completed</th>
                                                                    <th>%</th>
                                                                </thead>
                                                                <tr>
                                                                    <td>100</td>
                                                                    <td>100</td>
                                                                    <td>100</td>
                                                                    <td>100%</td>
                                                                </tr>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </li>
                                                <li>
                                                    <div class="panel panel-default panelSlick">
                                                        <div class="panel-heading">
                                                            <h4 class="panel-title">B Jaya Nageshwara Reddy</h4>
                                                            <span class="count">01</span>
                                                        </div>
                                                        <div class="panel-body">
                                                            <h4 class="text-capital">General Secretary</h4>
                                                            <table class="table table-condensed">
                                                                <thead>
                                                                    <th>Total</th>
                                                                    <th>Started</th>
                                                                    <th>Completed</th>
                                                                    <th>%</th>
                                                                </thead>
                                                                <tr>
                                                                    <td>100</td>
                                                                    <td>100</td>
                                                                    <td>100</td>
                                                                    <td>100%</td>
                                                                </tr>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </li>
                                                <li>
                                                    <div class="panel panel-default panelSlick">
                                                        <div class="panel-heading">
                                                            <h4 class="panel-title">B Jaya Nageshwara Reddy</h4>
                                                            <span class="count">01</span>
                                                        </div>
                                                        <div class="panel-body">
                                                            <h4 class="text-capital">General Secretary</h4>
                                                            <table class="table table-condensed">
                                                                <thead>
                                                                    <th>Total</th>
                                                                    <th>Started</th>
                                                                    <th>Completed</th>
                                                                    <th>%</th>
                                                                </thead>
                                                                <tr>
                                                                    <td>100</td>
                                                                    <td>100</td>
                                                                    <td>100</td>
                                                                    <td>100%</td>
                                                                </tr>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </li>
                                                <li>
                                                    <div class="panel panel-default panelSlick">
                                                        <div class="panel-heading">
                                                            <h4 class="panel-title">B Jaya Nageshwara Reddy</h4>
                                                            <span class="count">01</span>
                                                        </div>
                                                        <div class="panel-body">
                                                            <h4 class="text-capital">General Secretary</h4>
                                                            <table class="table table-condensed">
                                                                <thead>
                                                                    <th>Total</th>
                                                                    <th>Started</th>
                                                                    <th>Completed</th>
                                                                    <th>%</th>
                                                                </thead>
                                                                <tr>
                                                                    <td>100</td>
                                                                    <td>100</td>
                                                                    <td>100</td>
                                                                    <td>100%</td>
                                                                </tr>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </li>
                                                <li>
                                                    <div class="panel panel-default panelSlick">
                                                        <div class="panel-heading">
                                                            <h4 class="panel-title">B Jaya Nageshwara Reddy</h4>
                                                            <span class="count">01</span>
                                                        </div>
                                                        <div class="panel-body">
                                                            <h4 class="text-capital">General Secretary</h4>
                                                            <table class="table table-condensed">
                                                                <thead>
                                                                    <th>Total</th>
                                                                    <th>Started</th>
                                                                    <th>Completed</th>
                                                                    <th>%</th>
                                                                </thead>
                                                                <tr>
                                                                    <td>100</td>
                                                                    <td>100</td>
                                                                    <td>100</td>
                                                                    <td>100%</td>
                                                                </tr>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </li>
                                                <li>
                                                    <div class="panel panel-default panelSlick">
                                                        <div class="panel-heading">
                                                            <h4 class="panel-title">B Jaya Nageshwara Reddy</h4>
                                                            <span class="count">01</span>
                                                        </div>
                                                        <div class="panel-body">
                                                            <h4 class="text-capital">General Secretary</h4>
                                                            <table class="table table-condensed">
                                                                <thead>
                                                                    <th>Total</th>
                                                                    <th>Started</th>
                                                                    <th>Completed</th>
                                                                    <th>%</th>
                                                                </thead>
                                                                <tr>
                                                                    <td>100</td>
                                                                    <td>100</td>
                                                                    <td>100</td>
                                                                    <td>100%</td>
                                                                </tr>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </li>
                                            </ul>
                                        </div>
                                        <div class="col-md-12 col-xs-12 col-sm-12">
                                            <div class="bg_ED pad_15 arrow_top">
                                                <h4><span  class="text-capital">b.jayanageshwar reddy</span> - <span class="text-capitalize">General Secretary</span></h4>
                                                <table class="table table-condensed tableHoverLevels m_top20">
                                                    <thead class="bg_D8 text-capital">
                                                        <th>% Rank</th>
                                                        <th>Secretary name</th>
                                                        <th>total</th>
                                                        <th>yet to start</th>
                                                        <th>started</th>
                                                        <th>completed</th>
                                                        <th>%</th>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td>
                                                                <span class="tableCount">01</span>
                                                            </td>
                                                            <td>Parvin Raj</td>
                                                            <td>1000</td>
                                                            <td>600</td>
                                                            <td>400</td>
                                                            <td>400</td>
                                                            <td>10%</td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="tableCount">02</span>
                                                            </td>
                                                            <td>Parvin Raj</td>
                                                            <td>1000</td>
                                                            <td>600</td>
                                                            <td>400</td>
                                                            <td>400</td>
                                                            <td>10%</td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="tableCount">03</span>
                                                            </td>
                                                            <td>Parvin Raj</td>
                                                            <td>1000</td>
                                                            <td>600</td>
                                                            <td>400</td>
                                                            <td>400</td>
                                                            <td>10%</td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                                <span class="headingColor text-capitalize"><i><b>back to </b>general secretary view</i></span>
                                                <span class="text-capitalize">B.T.Naidu - <i>Secretary</i></span>
                                                <table class="table table-condensed tableLevels m_top20">
                                                    <thead class="bg_D8 text-capital">
                                                        <th>% Rank</th>
                                                        <th>constituency</th>
                                                        <th>mla name</th>
                                                        <th>total</th>
                                                        <th>yet to start</th>
                                                        <th>started</th>
                                                        <th>completed</th>
                                                        <th>%</th>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td>
                                                                <span class="tableCount">01</span>
                                                            </td>
                                                            <td>madakisara</td>
                                                            <td>K Eeranna</td>
                                                            <td>1000</td>
                                                            <td>600</td>
                                                            <td>400</td>
                                                            <td>400</td>
                                                            <td>10%</td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="tableCount">02</span>
                                                            </td>
                                                            <td>madakisara</td>
                                                            <td>K Eeranna</td>
                                                            <td>1000</td>
                                                            <td>600</td>
                                                            <td>400</td>
                                                            <td>400</td>
                                                            <td>10%</td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="tableCount">03</span>
                                                            </td>
                                                            <td>madakisara</td>
                                                            <td>K Eeranna</td>
                                                            <td>1000</td>
                                                            <td>600</td>
                                                            <td>400</td>
                                                            <td>400</td>
                                                            <td>10%</td>
                                                        </tr>
                                                    </tbody>
                                                </table>                      
                                                <div class="row">
                                                     <div class="col-md-6 col-xs-12 col-sm-10 col-sm-offset-1 col-md-offset-0">
                                                        <b><span class="color_333 pad_5 bg_CC text-capital"><span class="text-danger">poor</span> meetings conducted locations</span></b>
                                                        <div class="row m_top20">
                                                            <div class="col-md-6 col-xs-12 col-sm-8 col-sm-offset-2 col-md-offset-0">
                                                                <p class="text-capital">districts</p>
                                                                <table class="table tableCumulative">
                                                                    <tr>
                                                                        <td><span class="count" style="background-color:rgba(237, 29, 38,1)">1</span></td>
                                                                        <td>kurnool</td>
                                                                        <td>
                                                                            <div class="progress progressCustom">
                                                                              <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                                                                                <span class="sr-only">60% Complete</span>
                                                                              </div>
                                                                            </div>
                                                                        </td>
                                                                        <td class="text-danger">50</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><span class="count" style="background-color:rgba(237, 29, 38,.8)">1</span></td>
                                                                        <td>chittoor</td>
                                                                        <td>
                                                                            <div class="progress progressCustom">
                                                                              <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                                                                                <span class="sr-only">60% Complete</span>
                                                                              </div>
                                                                            </div>
                                                                        </td>
                                                                        <td class="text-danger">50</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><span class="count" style="background-color:rgba(237, 29, 38,.6)">1</span></td>
                                                                        <td>kadapa</td>
                                                                        <td>
                                                                            <div class="progress progressCustom">
                                                                              <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                                                                                <span class="sr-only">60% Complete</span>
                                                                              </div>
                                                                            </div>
                                                                        </td>
                                                                        <td class="text-danger">50</td>
                                                                    </tr>
                                                                </table>
                                                            </div>
                                                            <div class="col-md-6 col-xs-12 col-sm-8 col-sm-offset-2 col-md-offset-0 m_top20">
                                                                <p class="text-capital">constituencies</p>
                                                                <table class="table tableCumulative">
                                                                    <tr>
                                                                        <td><span class="count" style="background-color:rgba(237, 29, 38,1)">1</span></td>
                                                                        <td>ichapuram</td>
                                                                        <td>
                                                                            <div class="progress progressCustom">
                                                                              <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                                                                                <span class="sr-only">60% Complete</span>
                                                                              </div>
                                                                            </div>
                                                                        </td>
                                                                        <td class="text-danger">50</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><span class="count" style="background-color:rgba(237, 29, 38,.8)">1</span></td>
                                                                        <td>salur</td>
                                                                        <td>
                                                                            <div class="progress progressCustom">
                                                                              <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                                                                                <span class="sr-only">60% Complete</span>
                                                                              </div>
                                                                            </div>
                                                                        </td>
                                                                        <td class="text-danger">50</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><span class="count" style="background-color:rgba(237, 29, 38,.6)">1</span></td>
                                                                        <td>srikakulam</td>
                                                                        <td>
                                                                            <div class="progress progressCustom">
                                                                              <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                                                                                <span class="sr-only">60% Complete</span>
                                                                              </div>
                                                                            </div>
                                                                        </td>
                                                                        <td class="text-danger">50</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><span class="count" style="background-color:rgba(237, 29, 38,.4)">1</span></td>
                                                                        <td>madugula</td>
                                                                        <td>
                                                                            <div class="progress progressCustom">
                                                                              <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                                                                                <span class="sr-only">60% Complete</span>
                                                                              </div>
                                                                            </div>
                                                                        </td>
                                                                        <td class="text-danger">50</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><span class="count" style="background-color:rgba(237, 29, 38,.2)">1</span></td>
                                                                        <td>tuni</td>
                                                                        <td>
                                                                            <div class="progress progressCustom">
                                                                              <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                                                                                <span class="sr-only">60% Complete</span>
                                                                              </div>
                                                                            </div>
                                                                        </td>
                                                                        <td class="text-danger">50</td>
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
                </div>
            </div>
        </div>			
	 </div>
     <!--End -->
	</div>
</div>
<input type="hidden" id="cmtId" attr_cmt_id="editTextId'+i+'" value=""></input>
<input type="hidden" id="cmtTrngId" attr_cmt_id="editTextTrngId'+i+'" value=""></input>
<button  style="display:none" class="userStructureClass" attr_activityMemberId="1" attr_userTypeId="3" attr_userAccessLevelId="3" attr_userAccessLevelValuesString="11,12,15" > ActivityMember </button>
<!--<script src="newCoreDashBoard/js/angular.js" type="text/javascript"></script>-->
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Slick/slick.js" type="text/javascript"></script>
<script src="dist/DateRange/moment.js" type="text/javascript"></script>
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Rating/bootstrap-rating.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mousewheel.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/debates.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/newCoreDashboard.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/newsCoreDashBoard.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/coreDashboardTrainingProgram.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/tdpCommittees.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/partyMeeting.js" type="text/javascript"></script>

<script type="text/javascript">

	/* //Angular Start

		var obj = angular.module("partyWiseTotalApp",[]);
		//var dataA = [];
		obj.controller('partyWiseTotalCont', function($scope,$http) {
			var jsObj= {
					startDate: " ",
					endDate: " ",
					state: "AP"
				}

			$http({
				type : 'GET',
				url : 'getPartyWiseTotalDebateDetailsAction.action?task={startDate:" ",endDate: " ",state: "AP"}',
				//url : 'getPartyWiseTotalDebateDetailsAction.action?task='+jsObj+'',
				dataType : 'application/json',
				data : { },

			}).success(function(data) {
				$scope.partyWiseTotal = data
			});
		});
	//Angular ENd */
	
	//settings scroll
	$(".basicCommitteeDetailsDiv").mCustomScrollbar({setHeight:'300px'})
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
		//Meeting
		getPartyMeetingBasicCountDetails();
		
		//news please dont remove
		//getNewsBasicCounts();
		//getUserTypeWiseNewsCounts();
		getDetailedPartyMainEditionsOverview();
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
