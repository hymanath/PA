<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Core DashBoard</title>
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
<link href="dist/scroll/jquery.mCustomScrollbar.css" type="text/css" rel="stylesheet"/>
<link href="dist/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="dist/sliderbar/bootstrap-slider.css">
<link href="newCoreDashBoard/Plugins/RangeSlider/iThing.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/RangeSlider/jquery-ui-1.8.10.custom.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/>
<style type="text/css">
.eventsheader , #statewiseoverviewPanel,#locationsPopup
{
	display:none;
}

</style>
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
            	<div class="col-md-5 col-xs-12 col-sm-5">
                	<ul class="nav navbar-nav navbar-left headerProfileName">
                        <li class="dropdown profileDropDown toggleViewIcon">
                          <a style="cursor:pointer;"><span id="mainHeadinId""> </span> 
                          	<span class="caretBackground"><!--KALA VENKATA RAO-->
                            	<span class="fa fa-sort-desc" aria-hidden="true" style="color: rgb(255, 255, 255); margin-left: -1px;"></span>
                            </span>
                          </a>
						 <div class="dropdown-menu settingsDropDownOptionsView" style="z-index: 999;">
								<div id= "userLevelDetailsDiv"></div>
							</div>
                        </li>
                    </ul>
                </div>
				<div class="col-md-7 col-xs-12 col-sm-7 pull-right" style="margin-top: -10px;">
					<ul class="list-inline profileSelection">
						<li>
							<!-- Dont Add New Classes and new functionalities for this class a click has been already initialised please use that-->
							<label style="color:green;" class="radio-inline">
							  <input type="radio" attr_type="default" class="globalDateChange" name="optradio" checked>Default</input>
							 </label>
							<label style="color:green;" class="radio-inline">
							  <input type="radio" attr_type="currentMonth" class="globalDateChange" name="optradio">Current Month</input>
							 </label>
							<label style="color:green;"  class="radio-inline">
							  <input type="radio" attr_type="lastMonth"  class="globalDateChange" name="optradio">Last Month</input>
							</label>
						</li> 
						<li class="active" >
                        	<a  style="cursor:pointer;text-decoration:none;" attr_state_id="1" class="stateCls">AP</a>
                        </li>
						<!--<li>
                        	<a  style="cursor:pointer;text-decoration:none;" attr_state_id="36" class="stateCls">TS</a>
							<a>TS</a>
                        </li> -->
                    </ul>
                </div>
              
            </div>
        </div>
    </div>
</header>
<!-- Error Msg For Committee  Start--->
	<div class="modal fade alerttop" id="committeeErrMsg" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true" style="z-index:9999">
		<div class="modal-dialog modal-sm">
			 <div class="modal-content modal-sm alertstyle">
				 <div class="modal-body ">
				  <h5 class="text-center">Please Select Atleast One Committee</h5>
				  </div>
				 <div class="modal-footer pad-0 bt">
						<div class="row">
							<div class="mtop-5">
								 <button type="button" class="btn btn-primary btn-xs mleft_190 pull-left" data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
			</div>
		</div>
	</div>
	<!-- Error Msg For Committee End --->
<div class="container m_top20">
		
		<div class="row">
			<!-- Committees Start-->
		<div class="col-md-12 col-xs-12 col-sm-12 committeesBlock">
        	<div class="panel panel-default panelNewCustom panel1">
            	<div class="panel-heading">
                	<div class="row">
						<div class="col-md-7 col-sm-8 col-xs-8">
							<h4 class="panel-title text-capital">
								<img src="newCoreDashBoard/img/committees.png" class="iconClass"/>
								committees - <small class="text-muted committeesDate">OVERALL (TILL NOW)</small>
							</h4>
						</div>
						<div class="col-md-5 col-sm-4 col-xs-4">
							<span class="basicCommitteesBlockDiv pull-right">
								<i class="fa fa-gears"  data-toggle="tooltip" data-placement="top" title="Settings"></i>
							</span>
							<span class="notesIcon pull-right">
								<i class="glyphicon glyphicon-list-alt"  data-toggle="tooltip" data-placement="top" title="Notes" onClick="displayDashboardComments(1);"></i>
							</span>
							<span class="iconExpand pull-right" style="display:none">
								<i class="glyphicon glyphicon-fullscreen" data-toggle="tooltip" data-placement="top" title="Expand"></i>
							</span>
							<span class="cadreSettings pull-right refreshCadreCls" onClick="getCommitteesBasicCountReport();"><i class="glyphicon glyphicon-refresh" data-toggle="tooltip" data-placement="top" title="" data-original-title="Refresh"></i></i></span>
							<span><select id="tdpCommitteeYearId" style="width: 98px;display:inline-block;padding:2px 6px;height:25px;margin-top: -3px;"></select></span>
							<span class="input-group pull-right dateRangePickerCls m_XsTop10 hide">
								<input type="text" id="dateRangeId"	 class="form-control" style="width:180px"/>
								<span class="input-group-addon">
									<i class="glyphicon glyphicon-calendar"></i>
								</span>
							</span>						
						</div>
					</div>
					
					<!-- committees tabs for tdpcommittee ids -->	
					<div class="basicCommitteesBlockDropDown documentCloseClass" style="z-index:999;margin-top: -3px;" >
						<div class="row">
							<div class="col-md-6 col-xs-12 col-sm-6 pad_right0 m_top20">
							  <ul class="nav nav-tabs navTabsSettings" role="tablist">
								<li role="presentation" class="text-capital"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">District Level</a></li>
								<li role="presentation"   class="text-capital"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Mandal/town/division level</a></li>
								<li role="presentation"  class="text-capital active"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">village/ward level</a></li>
							  </ul>
							</div>
							<div class="col-md-6 col-xs-12 col-sm-6 pad_left0 pad_right4">
							  <div class="tab-content navTabsSettingsContent">
								<div role="tabpanel" class="tab-pane " id="home">
									<h4 class="text-capital pad_5" style="color:#99A0A5;">Select Committees</h4>
									<hr style ="margin-bottom:0px;" />
									<div class="basicCommitteeDetailsDiv">
									<ul class="settingsUl">
									<c:forEach items="${userDataVO.subList}" var="basicCommittee">
									   <c:if test="${basicCommittee.id == 1}">
										<li>
											<label class="checkbox-inline">
												<input type="checkbox"  class="districtCommitteecheckBoxClass" value="${basicCommittee.id}">
												<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">${basicCommittee.name}</h5></div>
										   </label>
										</li>	
									 </c:if>										
									</c:forEach>
									   <li>
											<label class="checkbox-inline">
											  <input type="checkbox"  id="checkAllAffliatedDistrictlevelId">
											  <div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;font-weight:bold;">Affiliated committees</h5></div>
										   </label>
										</li> 
										<c:forEach items="${userDataVO.subList}" var="basicCommittee">
											<c:if test="${basicCommittee.id != 1}">
											<li>
											  <label class="checkbox-inline">
												 <input type="checkbox"  class="districtCommitteecheckBoxClass districtCommitteeAffliatedcheckBoxClass" value="${basicCommittee.id}">
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
											 <input type="checkbox"  class="checkedBasicComm mandalCommitteecheckBoxClass" value="${basicCommittee.id}">
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
														 <input type="checkbox"  class="mandalCommitteecheckBoxClass mandalCommitteeAffliatedcheckBoxClass" value="${basicCommittee.id}">
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
								<div role="tabpanel" class="tab-pane active" id="messages">
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
					<div class="notesDropDown notesArrow documentCloseClass">
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
                        </ul>-->
                        <hr/>  
                          <div id="id1" style="color:red;"></div>						
                        <label>Create Notes</label>
                        <textarea class="form-control notesArea"></textarea>
                        <button class="btn btn-default btnCustomCreate btn-sm" onClick="savingDashboardComment(1);">create</button>
                    </div>  
                </div>
                <div class="panel-body">  
				
              		<div class="row">
					<div class="col-xs-12 col-sm-4 col-md-2 pull-right showDatePicker" style="display:none;">
						
					</div>
					<div class="col-md-6 col-xs-12 col-sm-12 col-md-offset-0 basicCommitteesBlock">
						<div id="basicCommitteeCountsDiv"></div>
					</div>
                        <div class="col-md-6 col-xs-12 col-sm-12 col-md-offset-0 userTypeCommitteesBlock">
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
                        	<div class="panel panel-default m_top10">
                            	<div class="panel-body ">
									<div id="levelWiseBasicCommittees"></div>
                                </div>
                            </div>
                        </div>
                       
                        <div class="col-md-12 col-xs-12 col-sm-12 col-md-offset-0 m_top20 moreBlocksDistrictlevel" style="display:none">
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
                                        <div class="col-xs-12 col-sm-12 col-md-12">
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
                                                    <div class="col-md-8 col-xs-12 col-sm-12" style="border-right:1px solid #ddd;">
														<div id="topPoorPerformanceDiv"></div>
                                                    </div>
                                                    <div class="col-md-4 col-xs-12 col-sm-8 col-sm-offset-2 col-md-offset-0">
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
		<!-- Committees End-->
		
			
		</div>	
</div>
<input type="hidden" id="alertTypeHiddenId"></input> 
<input type="hidden" id="alertEditionTypeHiddenId"></input>  
<input type="hidden" id="hiddenActivityId"></input>  
<!--model start -->

<input type="hidden" id="cmtId" attr_cmt_id="editTextId'+i+'" value=""></input>
<input type="hidden" id="cmtTrngId" attr_cmt_id="editTextTrngId'+i+'" value=""></input>
<input type="hidden" id="cmtDebateId" attr_cmt_id="editTextDebateId'+i+'" value=""></input>
<input type="hidden" id="cmtNewsId" attr_cmt_id="editTextNewsId'+i+'" value=""></input>
<input type="hidden" id="cmtMeetingId" attr_cmt_id="editTextmettingId'+i+'" value=""></input>
<input type="hidden" id="cmtEventsId" attr_cmt_id="editTextEventsId'+i+'" value=""></input>
<input type="hidden" id="cmtAttendanceId" attr_cmt_id="editTextAttendanceId'+i+'" value=""></input>
<input type="hidden" id="hiddenActivityScopeId"></input>
<input type="hidden" id="hiddenActivityLevelId"></input>

<!--attendance-->
<input type="hidden" id="officeHidId" attr_office_hid_id="" value=""></input>
<input type="hidden" id="deptHidId" attr_dept_hid_id="" value=""></input>
<input type="hidden" id="cadreHidId" attr_cadre_hid_id="" value=""></input>  
<button  style="display:none" class="userStructureClass" attr_activityMemberId="1" attr_userTypeId="3" attr_userAccessLevelId="3" attr_userAccessLevelValuesString="11,12,15" > ActivityMember </button>
<!--global element for state level training program-->
<input type = "hidden" id="hideProgramId" attr_prorgam_id_arr="" value=""></input>
<!--<script src="newCoreDashBoard/js/angular.js" type="text/javascript"></script>-->
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/RangeSlider/jquery-ui.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/RangeSlider/jQDateRangeSlider-withRuler-min.js" type="text/javascript"></script>
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
<script src="newCoreDashBoard/js/tdpCommittees.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/statusColorCodesForNews.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mCustomScrollbar.js"></script>
<script src="dist/scroll/jquery.mousewheel.js"></script>
<script src="dist/sliderbar/bootstrap-slider.js" type="text/javascript"></script>
<script type="text/javascript" src="js/simplePagination/simplePagination.js" ></script>
<script type="text/javascript">

var globallocationScope;
var globallocationValue;
var globallocationName;
var globalActivityScope;
var globalImages;
 
	//url Based Conditions
	  var windowUrl = window.location.href;
	  var ignoreLoginsUrl = windowUrl.indexOf("/partyAndLeaderActivitiesAndPerformanceTracking");
	  var ignoreLoginsUrl_1 = windowUrl.indexOf("/partyLeadersDashboardAction");
	  var ignoreLoginUrl = windowUrl.indexOf("/dailyMonthlyPartyActivities");
		
  
  
	$(document).on('click','#alertSettingsId',function(){
		$(".specialAlertDropDown").toggle();            
	});      
	//getting Dynamic Browser URL
	var windowUrl = window.location.href;
	var wurl = windowUrl.substr(0,(windowUrl.indexOf("/updateToursDetailsAction")));
	wurl = wurl.replace("/PartyAnalyst","");
	$(document).on("click","#demoBtn",function(){
		$("#attendanceModal").modal('show');
	});
	
	//find device ipad & Iphone & Android
	/* var ua = navigator.userAgent,
	browser = {
		iPad: /iPad/.test(ua),
		iPhone: /iPhone/.test(ua),
		Android5: /Android 5/.test(ua)
	};
	if(browser.iPad) { alert('IPAD') }	
	if(browser.Android5) { alert('ANDROID') }	 */
	
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
	  
	  var loggedInUserId = '${sessionScope.USER.registrationID}';
	   
	  
	  if(loggedInUserId == null || loggedInUserId == '')
		  loggedInUserId = 1;
		  
	  var loggedInUserActivityMemberId = '${requestScope.userDataVO.activityMemberId}';
	  var loggedInUserTypeId = '${requestScope.userDataVO.userTypeId}'; 
	  var loggedInUserAccessLevelId  = '${requestScope.userDataVO.userAccessLevelId}';
	  var globalDeptIdsArr = ${requestScope.userDataVO.deptIdList};         
	  var loggedInUserAccessLevelValues = getLoggedInUserAccessLevelValues();
	  
	  var globalActivityMemberId = loggedInUserActivityMemberId;
	  var globalUserTypeId = loggedInUserTypeId;
	  var globalUserAccessLevelId = loggedInUserAccessLevelId;
	  var globalUserAccessLevelValues = loggedInUserAccessLevelValues;
	  
	  var globalState = 'AP';
	  
	   var globalBasicCommitteeIdsArray = []; //basicCommitteeIdsArray
	  getCheckedBasicCommitteeIds();
	  
	getLoggedInUserStructure();

	onLoadCalls()
	
	function onLoadCalls(){
		$("#mainHeadinId").html("KALA VENKATA RAO");
		//news please dont remove
		$("#currentViewing").html(" TODAY ( "+moment().format('DD-MM-YYYY')+" )");
		var URLArr = windowUrl.split('/');
		var finalURL = URLArr[parseInt(URLArr.length) - 1].replace('.action','');
		$(".dateRangePickerCls").toggleClass("hide");
	     $(".profileSelection").hide();
		 getCadreEnrolmentYears();
		  setTimeout(function(){
			   passRequiredUrl(finalURL);
			  $(".moreBlocksDetailAndComp").show();
			  $(".comparisionBlock ").trigger("click");
		  },1000)
	  
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
		getAllItsSubUserTypeIdsByParentUserTypeIdForCadreRegistration(globalUserTypeId);     
	});
	
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


var loginUserId ='${sessionScope.USER.registrationID}';

function manageHeader(){
	if(loginUserId == null || loginUserId =='' || loginUserId.length ==0){

		$(".navbarHeader").show();  
		$(".eventsheader").hide();
	}else {

		$(".eventsheader").show();
		$(".navbarHeader").hide();
	}
}
manageHeader();
</script> 
</body>
</html>