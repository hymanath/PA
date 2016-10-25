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
                          <a style="cursor:pointer;">KALA VENKATA RAO 
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
				<div class="col-md-4 col-xs-12 col-sm-3 pull-right" style="margin-top: -10px;">
					<ul class="list-inline profileSelection">
					<!--	<li>
							<img src="today" id="todayRadio" class="datesClass" attr_type="today" title="today"/>
						 </li>
						<li>
							<img src="lastMonth" id="lastMonthRadio" class="datesClass" checked="true" attr_type="lastMonth"/>
						 </li>
						 -->
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
			
			<!--  NEWS BLOCK START-->
			<div class="col-md-6 col-xs-12 col-sm-12 newsBlock">
				<div class="panel panel-default panelNewCustom panel2">
					<div class="panel-heading">
						<div class="row">
							<div class="col-md-9 col-sm-9 col-xs-7 newsHead">
								<h4 class="panel-title text-capital">
									<img src="newCoreDashBoard/img/news.png" class="iconClass"/>
									news - <small class="text-muted" id="currentViewing"></small>
								</h4>
								
							</div>
							<div class="col-md-3 col-sm-3 col-xs-6 newsHead1">
								<span class="settingsIcon settingsIconNews pull-right">
									<i class="fa fa-gears"  data-toggle="tooltip" data-placement="top" title="Settings"></i>
								</span>
								<span class="notesIconNews pull-right">
									<i class="glyphicon glyphicon-list-alt"  data-toggle="tooltip" data-placement="top" title="Notes" onClick="displayDashboardCommentsForNews(5);"></i>
								</span>
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
						
						<div class="notesDropDown notesArrow documentCloseClass" >
							<h4 class="text-capital">notes
								<span class="pull-right">
									<i class="glyphicon glyphicon-list-alt"></i>
								</span>
							</h4>
							<div id="notesNewsId"></div>
							<hr/>
							<div id="newsUpId" style="color:red;"></div>
							<label>Create Notes</label>
							<textarea class="form-control notesAreaNews"></textarea>
							<button class="btn btn-default btnCustomCreateNews btn-sm "  onClick="savingDashboardCommentForNews(5);">create</button>
						</div>
						<div class="newsBlockDropDown documentCloseClass" style="z-index:999;margin-top: -3px;width:450px;" >
							<i class="glyphicon glyphicon-remove newsSetClose pull-right"></i>
							<div class="row">
								<div class="col-md-4 col-xs-12 col-sm-6 pad_right0 m_top20">
								  <ul class="nav nav-tabs navTabsSettings" role="tablist">
									<li role="presentation" class="active text-capital"><a href="#Editions" aria-controls="Editions" role="tab" data-toggle="tab">Editions</a></li>
									<li role="presentation" class="text-capital"><a href="#impactScope" aria-controls="impactScope" role="tab" data-toggle="tab">Impact Scope</a></li>
								  </ul>
								</div>
								<div class="col-md-8 col-xs-12 col-sm-6 pad_left0 pad_right4">
								  <div class="tab-content navTabsSettingsContent">
									<div role="tabpanel" class="tab-pane active" id="Editions">
										<h4 class="text-capital pad_5" style="color:#99A0A5;">Select Publication</h4>
										<hr style ="margin-bottom:0px;" />
										<div class="">
											<ul class="settingsUl" id="newsPapersUlId"></ul>
										</div>
									</div>
									<div role="tabpanel" class="tab-pane" id="impactScope">
										<h4 class="text-capital pad_5" style="color:#99A0A5;">Select Impact</h4>
										<hr style ="margin-bottom:0px;" />
										<div class="">
											<ul class="settingsUl">
												<li>
													<label class="checkbox-inline">
														<input type="checkbox" value="0" id="impactSelectAllId" checked>
														<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">Select All</h5></div>
													</label>
												</li>
												<li>
													<label class="checkbox-inline">
														<input type="checkbox" value="1" class="impactCheckCls" checked>
														<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">State</h5></div>
													</label>
												</li>
												<li>												
													<label class="checkbox-inline">
														<input type="checkbox" value="2" class="impactCheckCls" checked>
														<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">District</h5></div>
													</label>	
												</li>	
												<li>
													<label class="checkbox-inline">
														<input type="checkbox" value="3" class="impactCheckCls" checked>
														<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">Constituency</h5></div>
													</label>
												</li>
												<li>
													<label class="checkbox-inline">
													<input type="checkbox" value="4" class="impactCheckCls" checked>
														<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">Parliament</h5></div>
													</label>
												</li>	
												<li>
													<label class="checkbox-inline">
														<input type="checkbox" value="5" class="impactCheckCls" checked>
														<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">Mandal</h5></div>
													</label>
												</li>
												<li>
													<label class="checkbox-inline">
														<input type="checkbox" value="6" class="impactCheckCls" checked>
														<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">Panchayat</h5></div>
													</label>
												</li>
												<li>
													<label class="checkbox-inline">
														<input type="checkbox" value="8" class="impactCheckCls" checked>
														<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">Municipality/Corporation</h5></div>
													</label>	
												</li>	
											</ul>
										</div>
									</div>
								  </div>
								  
								</div>
								<div class="col-md-8 col-md-offset-4 col-xs-12 col-sm-9 col-sm-offset-3">
									<button type="button" class="btn btn-success filtersSubmitDivId">Get Details</button>
								</div>
							</div>
							
							
						</div>
						
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-12 col-xs-12 col-sm-12">
								<h5 class="updatedDate pull-right" style="top:-8px;position:relative;right:5px;float:right;font-weight:bold" id="lastUpdatedId"></h5>
							</div>
							<div class="col-md-12 col-xs-12 col-sm-12 newsBlock">
								<div id="newsBlockMainId"></div>
								<!--<h4 class="text-capital"><span class="headingColor" style="margin-right:5px"><img src="newCoreDashBoard/img/TDP.png" alt="tdp icon" class="newsIcon"/>Telugu Desam Party</span><div class="spinner" id="spinnerStatic" style="margin-top: 0px; margin-bottom: 0px;width:20px;height:20px;display:none"><div class="dot1"></div><div class="dot2"></div></div></h4>
								<div class="row">
									<div class="col-md-6 col-xs-12 col-sm-12 m_top10 ">
										<table class="table table-condensed tableNews bg_ED">
											<tr>
												<td>
													<p class="text-capital responsiveFont">Main Edition</p>
													<p id="tdpMainTotal">0</p>
												</td>
												<td>
													<p class="text-capital text-muted responsiveFont">Positive</p>
													<span id="tdpMainPositive">0</span><small id="tdpMainPositivePercent" class="text-success"> 0%</small>
												</td>
												<td>
													<p class="text-capital text-muted responsiveFont">Negative</p>
													<span id="tdpMainNegative">0</span><small id="tdpMainNegativePercent" class="text-danger"> 0%</small>
												</td>
											</tr>
										</table>
									</div>
									<div class="col-md-6 col-xs-12 col-sm-12 m_top10 pad_left0">
										<table class="table table-condensed tableNews bg_ED">
											<tr>
												<td>
													<p class="text-capital">Dist edition</p>
													<p id="tdpDistTotal">0</p>
												</td>
												<td>
													<p class="text-capital text-muted">Positive</p>
													<span id="tdpDistPositive">0</span><small class="text-success" id="tdpDistPositivePercent"> 0%</small>
												</td>
												<td>
													<p class="text-capital text-muted">Negative</p>
													<span id="tdpDistNegative">0</span><small class="text-danger" id="tdpDistNegativePercent"> 0%</small>
												</td>
											</tr>
										</table>
									</div>
								</div>
								<h4 class="text-capital m_top10"><span class="headingColor"><img src="newCoreDashBoard/img/opp.png" style="width:25px;" alt="tdp icon" class="debatesPartyIcon"/>Opposition Parties</span></h4>
								<div class="row">
									<div class="col-md-6 col-xs-12 col-sm-12 m_top10 ">
										<table class="table table-condensed tableNews ">
											<tr class="bg_ED">
												<td>
													<p class="text-capital">Main Edition</p>
													<p id="oppMainTotal">0</p>
												</td>
												<td>
													<p class="text-capital text-muted">Positive</p>
													<span id="oppPositiveTotal">0</span><small class="text-success" id="oppPositiveTotalPercent"> 0%</small>
												</td>
												<td>
													<p class="text-capital text-muted">Negative</p>
													<span id="oppNegativeTotal">0</span><small class="text-danger" id="oppNegativeTotalPercent"> 0%</small>
												</td>
											</tr>
											<tr>
												<td>
													<img src="newCoreDashBoard/img/YSRC.png" alt="cong logo" class="debatesPartyIcon"/><span id="ysrcMainTotal">0</span>
												</td>
												<td>
													<span id="ysrcMainPositive">0</span><small class="text-success" id="ysrcMainPositivePercent"> 0%</small>
												</td>
												<td>
													<span id="ysrcMainNegative">0</span><small class="text-danger" id="ysrcMainNegativePercent"> 0%</small>
												</td>
											</tr>
											<tr>
												<td>
													<img src="newCoreDashBoard/img/INC.png" alt="cong logo" class="debatesPartyIcon"/><span id="incMainTotal">0</span>
												</td>
												<td>
													<span id="incMainPositive">0</span><small class="text-success" id="incMainPositivePercent"> 0%</small>
												</td>
												<td>
													<span id="incMainNegative">0</span><small class="text-danger" id="incMainNegativePercent"> 0%</small>
												</td>
											</tr>
											<tr>
												<td>
													<img src="newCoreDashBoard/img/BJP.png" alt="cong logo" class="debatesPartyIcon"/><span id="bjpMainTotal">0</span>
												</td>
												<td>
													<span id="bjpMainPositive">0</span><small class="text-success" id="bjpMainPositivePercent"> 0%</small>
												</td>
												<td>
													<span id="bjpMainNegative">0</span><small class="text-danger" id="bjpMainNegativePercent"> 0%</small>
												</td>
											</tr>
										</table>
									</div>
									<div class="col-md-6 col-xs-12 col-sm-12 m_top10 pad_left0">
										<table class="table table-condensed tableNews ">
											<tr class="bg_ED">
												<td>
													<p class="text-capital">Dist Edition</p>
													<p id="oppDistTotal">0</p>
												</td>
												<td>
													<p class="text-capital text-muted">Positive</p>
													<span id="oppDistPositive">0</span><small class="text-success" id="oppDistPositivePercent"> 0%</small>
												</td>
												<td>
													<p class="text-capital text-muted">Negative</p>
													<span id="oppDistNegative">0</span><small class="text-danger" id="oppDistNegativePercent"> 0%</small>
												</td>
											</tr>
											<tr>
												<td>
													<img src="newCoreDashBoard/img/YSRC.png" alt="cong logo" class="debatesPartyIcon"/><span id="ysrcDistTotal">0</span>
												</td>
												<td>
													<span id="ysrcDistPositive">0</span><small class="text-success" id="ysrcDistPositivePercent"> 0%</small>
												</td>
												<td>
													<span id="ysrcDistNegative">0</span><small class="text-danger" id="ysrcDistNegativePercent"> 0%</small>
												</td>
											</tr>
											<tr>
												<td>
													<img src="newCoreDashBoard/img/INC.png" alt="cong logo" class="debatesPartyIcon"/><span id="incDistTotal">0</span>
												</td>
												<td>
													<span id="incDistPositive">0</span><small class="text-success" id="incDistPositivePercent"> 0%</small>
												</td>
												<td>
													<span id="incDistNegative">0</span><small class="text-danger" id="incDistNegativePercent"> 0%</small>
												</td>
											</tr>
											<tr>
												<td>
													<img src="newCoreDashBoard/img/BJP.png" alt="cong logo" class="debatesPartyIcon"/><span id="bjpDistTotal">0</span>
												</td>
												<td>
													<span id="bjpDistPositive">0</span><small class="text-success" id="bjpDistPositivePercent"> 0%</small>
												</td>
												<td>
													<span id="bjpDistNegative">0</span><small class="text-danger" id="bjpDistNegativePercent"> 0%</small>
												</td>
											</tr>
										</table>
									</div>
								</div>
								<h4 class="text-capital m_top10"><span class="headingColor"><img src="newCoreDashBoard/img/GOVT.png" style="width:25px;" alt="government icon" class="newsIcon"/>Government</span></h4>
								<div class="row">
									<div class="col-md-6 col-xs-12 col-sm-12 m_top10 ">
										<table class="table table-condensed tableNews bg_ED">
											<tr>
												<td>
													<p class="text-capital">Main Edition</p>
													<p id="govtMainTotal">0</p>
												</td>
												<td>
													<p class="text-capital text-muted">Positive</p>
													<span id="govtMainPositive">0</span><small class="text-success" id="govtMainPositivePercent"> 0%</small>
												</td>
												<td>
													<p class="text-capital text-muted">Negative</p>
													<span id="govtMainNegative">0</span><small class="text-danger" id="govtMainNegativePercent"> 0%</small>
												</td>
											</tr>
										</table>
									</div>
									<div class="col-md-6 col-xs-12 col-sm-12 m_top10 pad_left0">
										<table class="table table-condensed tableNews bg_ED">
											<tr>
												<td>
													<p class="text-capital">Dist Edition</p>
													<p id="govtDistTotal">0</p>
												</td>
												<td>
													<p class="text-capital text-muted">positive</p>
													<span id="govtDistPositive">0</span><small class="text-success" id="govtDistPositivePercent"> 0%</small>
												</td>
												<td>
													<p class="text-capital text-muted">negative</p>
													<span id="govtDistNegative">0</span><small class="text-danger" id="govtDistNegativePercent"> 0%</small>
												</td>
											</tr>
										</table>
									</div>
								</div>-->
								<div class="editionWiseBlock" style="display:none;">
								<h4 class="text-capital m_top10"><span class="headingColor" style="margin-right:5px">Edition Wise Unique Counts</span></h4>
								<div class="row" id="PaperWiseBlockInDivId"></div>
								<div class="row">
									<div class="col-md-6 col-xs-12 col-sm-12 m_top10 ">
										<table class="table table-condensed tableNews " id="mainPaperDivId">
										</table>
									</div>
									<div class="col-md-6 col-xs-12 col-sm-12 m_top10 pad_left0">
										<table class="table table-condensed tableNews " id="distPaperDivId">
										</table>
									</div>
								</div>
							
							</div>
							</div>
							<div class="col-md-6 col-xs-12 col-sm-12 newsHiddenBlock">
								<div class="row">
									<div class="col-md-8 col-md-offset-4 col-xs-12 col-sm-6 col-sm-offset-6">
										<ul class="activeUlCls list-inline pull-right">
												<li class="active newsliCls" attr_value="1"><i class="fa fa-arrow-up"></i>&nbsp;top 5 Positive</li>
												<li class="newsliCls" attr_value="2"> <i class="fa fa-arrow-down"></i>&nbsp;Top 5 Negative</li>
											</ul>
									</div>
									 <div id="userTypeWiseNewsForTopFiveStrongAndPoorDiv"></div>
									
									
								</div>
								
							</div>
							<div class="col-xs-12 col-sm-12 col-md-12">
								<i data-placement="top" data-toggle="tooltip" class="glyphicon glyphicon-option-horizontal pull-right morenewsBlocksIcon" title="Click here for more"></i>
							</div>	
							<div class="col-md-12 col-xs-12 col-sm-12 newsHiddenMoreBlock">
								<div class="row">
									<div class="col-md-12 col-xs-12 col-sm-12">
										<ul class="newsComparisonUl list-inline" style="margin-right: 12px !important;">
											<li class="text-capital newsComparisonHeading">Comparison</li>
											<li class="text-capital a viewsLiClass" id="comparisonPartyLiId" attr_div_id="comparisonPartyMainDivId">Party</li><!--Teja-->
											<li class="text-capital a viewsLiClass" id="comparisonGovernmentLiId" attr_div_id="comparisonGovernamentMainDivId">Government</li>
										</ul>
										
										<ul class="newsComparisonUl list-inline" style="margin-right: 12px !important;">
											<li class="text-capital newsComparisonHeading">Detailed</li>
											<li class="active text-capital a viewsLiClass" id="detailedPartyLiId" attr_div_id="detailedPartyMainDivId">Party</li>
											<li class="text-capital a viewsLiClass" id="detailedGovernmentLiId" attr_div_id="detailedGovernamentMainDivId">Government</li>
										</ul>
										
									</div>
									<div id="detailedPartyMainDivId" class="mainBuildingDivClass">
										<!-- News 1 Block-->
										<!--<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
											<div class="panel panel-default panelNew mainEditionCls">
												<div class="panel-heading">
													<h4 class="panel-title"><span class="headingColor">Main Edition Parties Overview</span></h4>
												</div>
												<div class="panel-body" >
													<div id="mainEditiongraphId"></div>
												</div>
											</div>
										</div>-->
										<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
											<div class="panel panel-default panelNew mainEditionCls">
												<div class="panel-heading">
													<h4 class="panel-title"><span class="headingColor">District Edition Parties Overview</span></h4>
												</div>
												<div class="panel-body verticalScrollBar" >
													<div id="districtWiseNewsReport"></div>
												</div>
											</div>
										</div>
										<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
											<div class="panel panel-default panelNew">
												<div class="panel-heading">
													<h4 class="panel-title"><span class="headingColor">News Type Analysis</span></h4>
												</div>
												<div class="panel-body">
												<div id ="newsTypeAnalysisDiv"></div>
												</div>
											</div>
										</div>
										<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
										<div class="panel panel-default panelNew">
											<div class="panel-heading">
												<div class="row">
													<div class="col-md-8 col-xs-7 col-sm-7">
														<h4 class="panel-title"><span class="headingColor">Party Vs Publication</span></h4>
													</div>
													<div class="col-md-4 col-xs-5 col-sm-5">
														<div class="pull-right">
															<ul class="list-inline  activeUlCls">
																<li class="active partyDistrictWiseDiv" attr_search_type="party">Parties district wise</li>
																<li class="partyDistrictWiseDiv" attr_search_type="publication">Publication Wise</li>
															</ul>
														</div>
													</div>
												</div>
												
											</div>
											<div class="panel-body">
												<div class="row">
													<div id="partyWiseDetailsDiv"></div>
													<!--<div id="publicationWiseDetailsDiv"></div>-->
												</div>
											</div>
										</div>
									</div>
									
									<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
										<div class="panel panel-default panelNew">
											<div class="panel-heading">
												<div class="row">
													<div class="col-md-8 col-xs-7 col-sm-7">
														<h4 class="panel-title"><span class="headingColor">Category Activities</span></h4>
													</div>
													<div class="col-md-4 col-xs-5 col-sm-5">
														<div class="pull-right">
															<ul class="list-inline  activeUlCls">
																<li class="active categoryDistrictWiseDiv" attr_search_type="location">location wise</li>
																<li class="categoryDistrictWiseDiv" attr_search_type="publication">Publication Wise</li>
															</ul>
														</div>
													</div>
												</div>
												
											</div>
											<div class="panel-body">
												<div class="row">
													<div id="categoryActiviesDetailsDiv"></div>
													<!--<div id="publicationWiseDetailsDiv"></div>-->
												</div>
											</div>
										</div>
									</div>
									
									</div>
									<div id="detailedGovernamentMainDivId" class="mainBuildingDivClass" style="display:none;">
										<!--2nd block-->
										<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
											<div class="panel panel-default panelNew">
												<div class="panel-heading">
													<h4 class="panel-title"><span class="headingColor">Departments Wise Districts Overview</span></h4>
												</div>
												<div class="panel-body">
												<div id="districtWiseNewsReportGovtDetailed"></div>
												</div>
											</div>
										</div>
										<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
											<div class="panel panel-default panelNew">
												<div class="panel-heading">
													<h4 class="panel-title"><span class="headingColor">top trending tracked issues</span></h4>
												</div>
												<div class="panel-body">
													<div id="topTrendingTracked"></div>
												</div>
												
											</div>
										</div>
										<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
											<div class="panel panel-default panelNew">
												<div class="panel-heading">
													<h4 class="panel-title"><span class="headingColor">Problems Detailed Overview</span></h4>
												</div>
												<div class="panel-body">
												<div class="row">
													<div class="col-md-12 col-xs-12 col-sm-12">
														<h4>STATE WISE</h4>
														<div id="stateWiseArticleRelatedToProblem"></div>
													</div>
												</div>
												<!--<div id="problemsDetailedOverview1"></div>-->
												<div class="row">
													<div class="col-md-12 col-xs-12 col-sm-12">
														<h4>DISTRICT WISE</h4>
														<div id="districtWiseArticleRelatedToProblem"></div>
													</div>
												</div>
												<div id="problemsDetailedOverview"></div>
												</div>
												
											</div>
										</div>
										<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
											<div class="panel panel-default panelNew">
												<div class="panel-heading">
													<h4 class="panel-title"><span class="headingColor">Analysis on Problem</span></h4>
												</div>
												<div class="panel-body">
												<div id="overAllAnalysisDetailsBlock"></div>
												<div id="problemsDetailedOverviewSubLevel" style="display:none"></div>
												</div>
												
											</div>
										</div>
										<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
										<div class="panel panel-default panelNew">
											<div class="panel-heading">
												<div class="row">
													<div class="col-md-8 col-xs-7 col-sm-7">
														<h4 class="panel-title"><span class="headingColor">Category Activities</span></h4>
													</div>
													<div class="col-md-4 col-xs-5 col-sm-5">
														<div class="pull-right">
															<ul class="list-inline  activeUlCls">
																<li class="active govtcategoryDistrictWiseDiv" attr_search_type="location">location wise</li>
																<li class="govtcategoryDistrictWiseDiv" attr_search_type="publication">Publication Wise</li>
															</ul>
														</div>
													</div>
												</div>
												
											</div>
											<div class="panel-body">
												<div class="row">
													<div id="govtCategoryActiviesDetailsDiv"></div>
													<!--<div id="publicationWiseDetailsDiv"></div>-->
												</div>
											</div>
										</div>
									</div>
									</div>
									
									 <div id="comparisonPartyMainDivId" class="mainBuildingDivClass" style="display:none;">
										
										<div class="col-md-12 col-xs-12 col-sm-12 childCls" >
											<div class="panel panel-default panelNew">
												<!--<div class="panel-heading">
													<div class="row">
														<div id="userTypeStrId"></div>
													 </div>
												</div>-->
												<div class="panel-heading">
													<div class="row">
														<div class="col-xs-12 col-sm-8 col-md-12">
														   <div id="userTypeStrId"></div>
														</div>   
													</div>
												</div>
												<div class="panel-body">
													<div class="row">
														<div class="col-md-5 col-xs-12 col-sm-6 pull-right">
															<ul class="list-inline listIcon">
																<li>
																	<span class="tdpColor"></span>
																	<img src="newCoreDashBoard/img/TDP.png" style="width:25px;" alt="tdp icon"/>
																	TDP
																</li>
																<li>
																	<span class="ysrcColor"></span>
																	<img src="newCoreDashBoard/img/YSRC.png" style="width:25px;" alt="tdp icon"/> 
																	YSRC
																</li>
																<li>
																	<span class="incColor"></span>
																	<img src="newCoreDashBoard/img/INC.png" style="width:25px;" alt="tdp icon"/> 
																	INC
																</li>
																<li>
																	<span class="bjpColor"></span>
																	<img src="newCoreDashBoard/img/BJP.png" style="width:25px;" alt="tdp icon"/> 
																	BJP
																</li>
															</ul>
														</div>
														<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
															<div id="partyWiseComparisionBlock"></div>
														</div>
														<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
															<div class="bg_ED pad_15 newsOnLoadShow" style="display:none">
																<div id="partyComparisionSubLevelMemberDetailsDiv" class="row"></div>
																<div class="panel panel-default panelNew m_top20">
																	<div class="panel-heading">
																		<h4 class="panel-title">NEWS TYPE ANALYSIS</h4>
																		<p><span id="selectedMemberNameNews"></span> - <span id="selectedUserTypeNews"></span></p>
																	</div>
																	<div class="panel-body">
																		<div id="PartyComparisionNewsTypeAnalysisDiv" class="row"></div>
																		<div id="ComparisionPartyDistrictWiseNewsReport" class="row"></div>
																	</div>
																</div>
																<div class="panel panel-default panelNew m_top20">
																	<div class="panel-heading">
																		<div class="row">
																			<div class="col-md-6 col-sm-6 col-xs-12">
																				<h4 class="panel-title">PARTY Vs PUBLICATION</h4>
																				<p><span id="selectedMemberNameNewsParty"></span> - <span id="selectedUserTypeNewsParty"></span></p>
																			</div>
																			<div class="col-md-6 col-sm-6 col-xs-12">
																				<div id="partiesWiseSelection"></div>
																			</div>
																		</div>
																	</div>
																	<div class="panel-body">
																		<div id="partyAndPublication" class="row"></div>
																		<div id="CompaisionPartyAndPublicationWiseDetailsDiv" class="row"></div>
																		<div id="partyComparisonPartyWisePoorL" class="row"></div>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
											
										</div>
									</div>
									
									
									<div id="comparisonGovernamentMainDivId" class="mainBuildingDivClass" style="display:none;">
									<div class="col-md-12 col-xs-12 col-sm-12">
										<input type="radio" class="radioTypeCls" name="comparisonGovtRadioBtn" value="Cumulative" checked="checked" /> Cumulative
										<input type="radio" class="radioTypeCls" name="comparisonGovtRadioBtn" value="Detailed"/> Detailed
									</div>
										<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
											<div id="comparisonGovtMinistriesInfo"></div>
										</div>
										<div class="col-md-12 col-xs-12 col-sm-12 m_top20 moreDetailsBlockNews" style="display:none">
											<div class="bg_ED pad_15">
												<div class="row">
													<div id="ministerSubLevelDetailsDiv"></div>
													<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
														<div class="panel panel-default panelNew">
															<div class="panel-heading">
																<h4 class="panel-title"><span class="headingColor text-capitalize">Departments Wise Districts Overview</span></h4>
															</div>
															<div class="panel-body">
																<div id="comparisonGovtDeptDOverview"></div>
															</div>
														</div>
													</div>
													<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
														<div class="panel panel-default panelNew">
															<div class="panel-heading">
																<h4 class="panel-title"><span class="headingColor text-capitalize">top trending tracked issues</span></h4>
															</div>
															<div class="panel-body">
																<div id="comparisonGovtTopTrend"></div>
															</div>
															
														</div>
													</div>
													<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
														<div class="panel panel-default panelNew">
															<div class="panel-heading">
																<h4 class="panel-title"><span class="headingColor text-capitalize">Problems Detailed Overview</span></h4>
															</div>
															<div class="panel-body">
															<div class="row">
																<div class="col-md-12 col-xs-12 col-sm-12">
																	<h4>STATE WISE</h4>
																	<div id="compareStateWiseArticleRelatedToProblem"></div>
																</div>
															</div>
															<div class="row">
																<div class="col-md-12 col-xs-12 col-sm-12">
																	<h4>DISTRICT WISE</h4>
																	<div id="compareDistrictWiseArticleRelatedToProblem"></div>
																	<!--<div id="comparisonGovtProblemsStateLevelOverview"></div>-->
																	<div id="comparisonGovtProblemsDistrictLevelOverview"></div>
																	
																</div>
															</div>
															</div>
														</div>
													</div>
													<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
														<div class="panel panel-default panelNew">
															<div class="panel-heading">
																<h4 class="panel-title"><span class="headingColor">Analysis on Problem</span></h4>
															</div>
															<div class="panel-body">
															<div id="comparisonGovtProblemsOverAllAnalysis"></div>
															<div id="comparisonGovtProblemsOverAllSubLevelAnalysis" style="display:none;"></div>
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
			<!-- NEWS BLOCK END-->
			<!-- DEBATES PROGRAM BLOCK -->
			<div class="col-md-6 col-xs-12 col-sm-12 debatesBlock">
				<div class="panel panel-default panelNewCustom panel2">
					<div class="panel-heading">
						<div class="row">
							<div class="col-md-9 col-sm-9 col-xs-7 debatesHead">
								<h4 class="panel-title text-capital">
									<img src="newCoreDashBoard/img/debates.png" class="iconClass"/>
									debates - <small class="text-muted debatesDate">today</small>
								</h4>
							</div>
							<div class="col-md-3 col-sm-3 col-xs-5 debatesHead1">
								<span class="settingsIcon pull-right">
								   <i class="fa fa-gears"  data-toggle="tooltip" data-placement="top" title="Settings"></i>
								 </span>
								<span class="notesIconDebates pull-right">
									<i class="glyphicon glyphicon-list-alt"  data-toggle="tooltip" data-placement="top" title="Notes" onClick="displayDashboardCommentsForDebates(3);"></i>
								</span>
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
					   <div class="notesDropDown notesArrow" >
							<h4 class="text-capital">notes
								<span class="pull-right">
									<i class="glyphicon glyphicon-list-alt"></i>
								</span>
							</h4>
							<div id="notesDebatesId"></div>
							<hr/>
							<div id="debateUpId" style="color:red;"></div>
							<label>Create Notes</label>
							<textarea class="form-control notesAreaDebates"></textarea>
							<button class="btn btn-default btnCustomCreateDebates btn-sm "  onClick="savingDashboardCommentFordebates(3);">create</button>
						</div> 	
					
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-12 col-xs-12 col-sm-12">
								<h5 id="lastUpdatedDebateId" style="top:-8px;position:relative;right:5px;float:right;font-weight:bold" class="updatedDate pull-right"></h5>
							</div>
							<div class="col-md-12 col-xs-12 col-sm-12 debatesBlock">
								
								<div class="row">
									<div id="partyWiseTotalDebateDetails"></div>
								</div>
							</div>
							<div class="col-md-6 col-xs-12 col-sm-12 debatesHiddenBlock">
								<div class="row">
									<div class="col-md-6 col-xs-12 col-sm-6 m_top10">
										<h4 class="text-capital"><span class="headingColor ">Spokespersons</span></h4>
									</div>
									<div class="col-md-6 col-xs-12 col-sm-6 m_top10">
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
										<div class="panel-heading" role="tab" id="collapseOneId" style="cursor:pointer">
											<!--<a role="button" class="collapseDebatesIcon" data-toggle="collapse" data-parent="#debatesCollapse" href="#oneId1" aria-expanded="true" aria-controls="oneId1">-->
												<h4><span class="headingColor text-capitalize responsiveWidth" style="display:block;width:330px;">Character based performance cohort</span><span class="pull-right arrowChange"><i class="glyphicon glyphicon-minus"></i></span></h4>
											<!--</a>-->
										</div>
										<!--<div id="oneId1" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="oneId">-->
											<div class="panel-body collapse in" id="collapseOneBodyId">
												<div class="row">
													<div id="scaleBasedPerformanceCohort"></div>
												</div>
											</div>
										<!--</div>-->
									</div>
								  <div class="panel panel-default panelNew">
									<div class="panel-heading" role="tab" id="headingTwo">
										<a class="collapsed collapseDebatesIcon" role="button" data-toggle="collapse" data-parent="#debatesCollapse" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
										   <h4 class="panel-title"><span class="headingColor responsiveWidth text-capitalize" style="display:block;width:330px;">role based performance cohort</span></h4>
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
										   <h4 class="panel-title"><span class="headingColor responsiveWidth text-capitalize" style="display:block;width:330px;">Top 5 Performers</span></h4>
										</a>
									</div>
									<div id="collapseFive" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFive">
									  <div class="panel-body">
										<div class="row">
											<div id="candidateRolesBuildId"></div>
										</div>
										<div class="row m_top20">
											<div class="col-md-12 col-xs-12 col-sm-12" id="candidateRolesPerformanceNewId"></div>
										</div>
									  </div>
									</div>
								  </div>
								  <div class="panel panel-default panelNew">
									<div class="panel-heading" role="tab" id="headingThree">
										<a class="collapsed collapseDebatesIcon" role="button" data-toggle="collapse" data-parent="#debatesCollapse" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
										   <h4><span class="headingColor responsiveWidth text-capitalize" style="display:block;width:330px;">candidate overall performance cohort</span></h4>
										</a>
									</div>
									<div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
									  <div class="panel-body">
										<div class="row">
											<div class="col-md-12 col-xs-12 col-sm-12">
												<div id="candidateOverAllPerformanceCohort" style="overflow:auto;"></div>
											</div>
										</div>
									  </div>
									</div>
								  </div>
								  <div class="panel panel-default panelNew">
									<div class="panel-heading" role="tab" id="headingFour">
										<a class="collapsed collapseDebatesIcon" role="button" data-toggle="collapse" data-parent="#debatesCollapse" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
										   <h4><span class="headingColor responsiveWidth text-capitalize" style="display:block;width:330px;">channel vs parties</span></h4>
										</a>
									</div>
									<div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
									  <div class="panel-body">
										<div class="row">
											<div class="col-md-12 col-xs-12 col-sm-12">
												<div id="channelAndPartyWiseDetails" style="overflow:auto;"></div>
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
		</div>	
		<div class="row">
			<!--Meetings Start -->
			<div class="col-md-6 col-xs-12 col-sm-12 meetingsBlock">
				<div class="panel panel-default panelNewCustom">
					<div class="panel-heading">
						<div class="row">
							<div class="col-md-9 col-sm-9 col-xs-7 meetingHead">
								<h4 class="panel-title text-capital">
									<img src="newCoreDashBoard/img/meetings.png" class="iconClass"/>
									meetings - <small class="text-muted" id="dateMeetingHeadingId"> this month</small>
								</h4>
							</div>
							<div class="col-md-3 col-sm-3 col-xs-5 meetingHead1">
								<!--<span class="settingsIcon pull-right">
									<i class="fa fa-gears"  data-toggle="tooltip" data-placement="top" title="Settings"></i>
							    </span>-->
								<span class="notesIconMeeting pull-right">
									<i class="glyphicon glyphicon-list-alt"  data-toggle="tooltip" data-placement="top" title="Notes" onClick="displayDashboardCommentsForMeetings(2);"></i>
								</span>
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
						<div class="notesDropDown notesArrow" >
							<h4 class="text-capital">notes
								<span class="pull-right">
									<i class="glyphicon glyphicon-list-alt"></i>
								</span>
							</h4>
							<div id="notesMeetingId"></div>
						<hr/>
						<div id="meetingsUpId" style="color:red;"></div>
                        <label>Create Notes</label>
                        <textarea class="form-control notesAreaMeetings"></textarea>
                        <button class="btn btn-default btnCustomCreateMeetings btn-sm "  onClick="savingDashboardCommentForMeetings(2);">create</button>
						</div>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-12 col-xs-12 col-sm-12">
								<h6 id="lastMeetingUpdatedIdTimeId" style="top:-8px;position:relative;right:5px;float:right;font-weight:bold"></h6>
							</div>
							<div class="col-md-12 col-xs-12 col-sm-12 meetingsBlock meetingNB">
								<div class="panelBlock">
									<h4><span class="headingColor text-capital">committee meetings</span><span class="committeeMeetingsSettings" style="background-color:#fff;margin-left:5px;"><i class="fa fa-gears"></i></span><span style="color:red;font-size:15px;" id="committeeMeetingErrorId"></span></h4>
									<div class="settingsDropDown notesArrow" style="left:0px;">
										<ul class="list-inline">
											<li><label><input type="checkbox" class="selectAll"/>&nbsp&nbspSelect All</label></li>
											<!--<li><label><input type="checkbox" class="unSelectAll"/>UnSelect All</label></li>-->
										</ul>
										<div id="committeeTypeDivId"></div>
										<button type="button" attr_main_type_meeting_id="1" class="btn btn-success meetingGetDtlsBtncls">Get Details</button>
									</div>
									 <div class="row">
										<div id="meetingBasicCountDivId"></div>
									 </div>
									 
									 
								  <div class="panelBlock m_top20" >
									<h4>
										<span class="headingColor text-capitalize">state level meetings</span>
										<span class="stateLevelMeetingSeeting" style="background-color:#fff;margin-left:5px;"><i class="fa fa-gears"></i></span>
										<span class="stateLevelMeetingsExpand" id="stateLevelMeetingsExpandId" attr_main_type_meeting_id="2" style="background-color:#fff;margin-left:5px;"><i class="glyphicon glyphicon-fullscreen"></i></span><span style="color:red;font-size:15px;" id="stateLevelMeetingErrorId"></span>
									</h4>
								    <div class="settingsStateLevelMeetingDropDown " style="left:0px;">
										<ul class="list-inline">
											<li><label><input type="checkbox" class="selectAllStateLevelMeeting"/>&nbsp&nbspSelect All</label></li>
										</ul>
										<div id="stateLevelMeetingDivId"></div>
										<button type="button" attr_main_type_meeting_id="2"  class="btn btn-success stateLevelMeetingBtnCls">Get Details</button>
									</div>
									 <div class="row">
										<div id="stateLevelMeetingBasicCnt"></div>
									 </div>
								  </div>
								  
								  <div class="panelBlock m_top20">
									<h4>
										<span class="headingColor text-capitalize">special meetings</span>
										<span class="specialMeetingSeeting" style="background-color:#fff;margin-left:5px;"><i class="fa fa-gears"></i></span>
										<span style="background-color:#fff;margin-left:5px;" attr_main_type_meeting_id="3" class="specialMeetings" id="specialMeetingsExpandId"><i class="glyphicon glyphicon-fullscreen"></i></span><span style="color:red;font-size:15px;" id="specialMeetingErrorId"></span>
								    </h4>
								 	<div class="specialMeetingDropDown " style="left:0px;">
										<ul class="list-inline">
											<li><label><input type="checkbox" class="selectAllSpecialMeeting"/>&nbsp&nbspSelect All</label></li>
										</ul>
										<div id="specialMeetingDivId"></div>
										<button type="button" attr_main_type_meeting_id="3"  class="btn btn-success specialMeetingBtncls">Get Details</button>
									</div>
									 <div class="row">
										<div id="specialMeetingBasicCnt"></div>
									 </div>
								   </div>
								</div>
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
							<div class="col-md-6 col-xs-12 col-sm-12 stateLevelMeetingBlock">
								<div id="stateLevelMeetingBlockId"></div>
							</div>
							<div class="col-md-6 col-xs-12 col-sm-12 stateGeneralMeetBlock">
								
							</div>
							<div class="col-xs-12 col-sm-12 col-md-12 meetingsHiddenBlock showMoreBlockCls ">
										<i data-placement="top" data-toggle="tooltip" class="glyphicon glyphicon-option-horizontal pull-right moreMeetingsBlocksIcon" title="Click here for more"></i>
							</div>	
							<div class="col-md-12 col-xs-12 col-sm-10 col-sm-offset-1 col-md-offset-0 moreMeetingsBlocks1" style="display:none;">
								<ul class="list-inline pull-right activeUlCls">
									<li class="active meetingDetailedBlock">Detailed</li>
									<li class="meetingComparisionBlock">Comparison</li>
									
								</ul>
							</div>
							<div id="meetingLevelHIghChartsDivId" class="moreMeetingsBlocksDetailed"></div>
							<div class="col-md-12 col-xs-12 col-sm-12 m_top20 moreMeetingsBlocksDetailed" style="display:none">
								<div class="panel panel-default panelNew">
									<div class="panel-heading">
										<div class="row">
											<div class="col-md-8 col-xs-12 col-sm-6">
												<span class="headingColor text-capitalize">meetings performance cohort</span>
											</div>
										</div>
									</div>
									<div class="panel-body">
										<div id="userAccessLevelLocationDivId"></div>
										<div id="districtWisePartyMeetingTypeDivId"></div>
									</div>
								</div>
							</div>
							 <div class="col-md-12 col-xs-12 col-sm-12 m_top20 moreMeetingsBlocksComparision" style="display:none;">
								<div class="panel panel-default panelNew">
									<div class="panel-heading">
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-12">
											  <div id="childUserTypeDetailsDivIdForMeeting"></div>
											</div>
										</div>
									</div>
									<div class="panel-body">
										<div class="row"> 
											<div class="col-md-12 col-xs-12 col-sm-12">
												 <div id="childActivityMemberDivIdForMeeting"> </div>
											</div>
											<div class="col-md-12 col-xs-12 col-sm-12">
												<div class="bg_ED pad_15 m_top20">
												   <div id="directChildActivityMeetingMemberDiv"></div>
												   <div class="row">
														 <div class="col-md-6 col-xs-12 col-sm-10 col-sm-offset-1 col-md-offset-0">
														   <!-- <b><span class="color_333 pad_5 bg_CC text-capital"><span class="text-danger">poor</span> meetings conducted locations</span></b>-->
															<div class="row m_top20">
															 <div id="topPoorLocationsMeetingDiv"></div>
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
			<!--Meetings End-->
			<!-- Training Start -->
		<div class="col-md-6 col-xs-12 col-sm-12 trainingsBlock">
			<div class="panel panel-default panelNewCustom panel1">
				<div class="panel-heading">
					<div class="row">
						<div class="col-md-8 col-sm-8 col-xs-7">
							<h4 class="panel-title text-capital">
								<img src="newCoreDashBoard/img/training.png" class="iconClass"/>
								training - <small class="text-muted trainingDate">up to date</small>
							</h4>
						</div>
						<div class="col-md-4 col-sm-4 col-xs-5">
							<!--<span class="settingsIcon pull-right">
								<i class="fa fa-gears"  data-toggle="tooltip" data-placement="top" title="Settings"></i>
							</span>-->
							<span class="notesIconTraining pull-right">
								<i class="glyphicon glyphicon-list-alt"  data-toggle="tooltip" data-placement="top" title="Notes" onClick="displayDashboardCommentsForTraining(4);"></i>
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
					
						
					
					<div class="notesDropDown notesArrow documentCloseClass" >
						<h4 class="text-capital">notes
							<span class="pull-right">
								<i class="glyphicon glyphicon-list-alt"></i>
							</span>
						</h4>
						<div id="notesTrainingId"></div>
						  <hr/>
						<div id="id2" style="color:red;"></div>
						<label>Create Notes</label>
						<textarea class="form-control notesAreaTraining"></textarea>
						<button class="btn btn-default btnCustomCreateTraining btn-sm " id=buttonId" onClick="savingDashboardCommentForTraing(4);">create</button>
					</div>  
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-12 col-xs-12 col-sm-12"><h6 id="lastUpdatedTimeTrainingCampId" style="top:-8px;position:relative;right:5px;float:right;font-weight:bold"></h6></div>
						<div class="col-md-12 col-xs-12 col-sm-12 trainingsBlock trainingsBlockExpand">
							<div class="row">
							 <div id="programsDtlsCntTableId"></div>
								<div class="col-md-12 col-xs-12 col-sm-12">
									 <div id="villageWardTblId"></div>
								</div>
								<div class="col-md-12 col-xs-12 col-sm-12">
									 <div id="mdlTwnDvsnTabId"></div>
								</div>
								<div id="stateLevelCampId"></div>
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
									<div class="col-md-12 col-xs-12 col-sm-12">
										<h4><span id="clickInfoId" class="text-capital headingColor pad_custom" style="font-size:18px;display:none;"></span> </h4>  
									</div>
									<div class="col-md-6 col-md-offset-6 col-xs-12 col-sm-6 col-sm-offset-6">
										<ul class="activeUlCls list-inline hideCls">
											<li class="liCls active" attr_value="strong"><i class="fa fa-arrow-up"></i>&nbsp;top 5 strong</li>
											<li class="liCls" attr_value="poor"><i class="fa fa-arrow-down"></i>&nbsp;last 5 poor</li>
										</ul>
									</div> 
								</div>
								<div id="userTypeWiseTrainingProgramTopFiveStrongAndPoorMemsDivId" class="m_top20"></div>     
							</div>
							<div class="col-xs-12 col-sm-12 col-md-12">
									<i data-placement="top" data-toggle="tooltip" id="switchButtonId" class="glyphicon glyphicon-option-horizontal pull-right moreTrainingBlocksIcon" title="Click here for more" style="cursor:pointer;"></i>  
							</div>
							<div class="col-xs-12 col-sm-12 col-md-12 moreTrainingBlocks trainingDetailedBlock hdCls">
									<ul class="list-inline pull-right activeUlCls">
										<li id="detailedId" class="trainingDetailed">Detailed</li>
										<li class="trainingComparison">Comparison</li>
										<!--<li class="basicCommitteesBlockDiv"><i class="fa fa-gears"></i></li>-->
									</ul>
							</div> 
							<div class="col-md-12 col-xs-12 col-sm-12 col-md-offset-0 moreTrainingBlocks trainingDetailedBlock hdCls">
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
							<div class="col-md-12 col-xs-12 col-sm-12 col-md-offset-0 moreTrainingBlocks trainingDetailedBlock hdCls">
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
												<div id="districtWiseProgramsCntDivId"></div>
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
											<div class="col-xs-12 col-sm-7 col-md-12">
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
			<!-- Training End-->
		</div>
		<div class="row">
		<!--Events Start -->
			<div class="col-md-6 col-xs-12 col-sm-12 eventsBlock">
				<div class="panel panel-default panelNewCustom">
					<div class="panel-heading">
						<div class="row">
							<div class="col-md-8 col-sm-8 col-xs-7">
								<h4 class="panel-title text-capital">
									<img src="newCoreDashBoard/img/events.png" class="iconClass"/>
									events and activities <small class="text-muted"><span id="dateEventsHeadingId">  </span></small>
								</h4>
							</div>
							<div class="col-md-4 col-sm-4 col-xs-5">
								<span class="notesIconEvents pull-right">
									<i class="glyphicon glyphicon-list-alt"  data-toggle="tooltip" data-placement="top" title="Notes" onClick="displayDashboardCommentsForEvents(6);"></i>
								</span>
								<span class="eventsIconExpand pull-right">
									<i class="glyphicon glyphicon-fullscreen"></i>
								</span>
								<!--<span class="input-group pull-right dateRangePickerClsForEvents hide" style="width:200px;">
									<input type="text" id="dateRangeIdForEvents" style="width:180px" class="form-control" />
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-calendar"></i>
									</span>
								</span>-->
							</div>
						</div>
						<div class="notesDropDown notesArrow" >
							<h4 class="text-capital">notes
								<span class="pull-right">
									<i class="glyphicon glyphicon-list-alt"></i>
								</span>
							</h4>
							<div id="notesEventsId"></div>
							<hr/>
							<div id="eventsUpId" style="color:red;"></div>
							<label>Create Notes</label>
							<textarea class="form-control notesAreaEvents"></textarea>
							<button class="btn btn-default btnCustomCreateEvents btn-sm "  onClick="savingDashboardCommentForEvents(6);">create</button>
						</div>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-12 col-xs-12 col-sm-12 eventsBlock">
								<h6 id="lastUpdatedIdEvents" class="updatedDate"></h6>
								<h4><span class="headingColor text-capital">events</span>
								<span id="eventIds" class="eventsListExpandIcon eventCls" style="background-color:#fff;font-size:10px;margin-left:5px;"><i class="glyphicon glyphicon-fullscreen"></i></span></h4>
								<div id="mainEventsList" class="m_top20"></div>
							</div>
							<div class="col-md-6 col-xs-12 col-sm-12 eventsHiddenBlock">
								<h4><span class="headingColor text-capital">events </span></h4>
								<div class="col-md-6 col-md-offset-6 col-xs-12 col-sm-6 col-sm-offset-6">
										<ul class="activeUlCls list-inline hideCls">
											<li class="eventStrngPrCls active" attr_value="strong"><i class="fa fa-arrow-up"></i>&nbsp;top 5 strong</li>
											<li class="eventStrngPrCls" attr_value="poor"><i class="fa fa-arrow-down"></i>&nbsp;last 5 poor</li>
										</ul>
								</div> 
								<div id="UserTypeWiseEventMemberDtslDivId" class="m_top20"></div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-12 eventsHiddenBlock" style="display: none;">
								<i class="glyphicon glyphicon-option-horizontal pull-right moreEventsBlocksIcon" data-toggle="tooltip" data-placement="top" ></i>
							</div>
							<div class="col-md-12 col-xs-12 col-sm-12 moreEventsBlocks">
								<ul class="list-inline pull-right activeUlCls" style="margin-right: 12px !important;display:none">
									<li class="text-capital detailedEvent">Detailed</li>
									<li class="text-capital comparisonEvent">Comparison</li>
								</ul>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-12 moreEventsBlocks detailedBlockEvents m_top10">
								<div class="panel panel-default panelNew">
									<div class="panel-body">
										<div id="eventsGraphBlock"></div>
									</div>
								</div>
								<div class="panel panel-default panelNew m_top20">
									<div class="panel-heading">
										<h4 class="panel-title">
											<span class="headingColor text-capitalize">Mahanadu - Event Cohort</span>
										</h4>
									</div>
									<div class="panel-body">
										<div id="eventsDistWiseCohort"></div>
									</div>
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-12 comparisonBlockEvents m_top10" style="display:none">
								<div class="panel panel-default panelNew">
									<div class="panel-heading">
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-12">
											  <div id="allItsSubUserTypeIdsByParentUserTypeDivIdForEvent"></div>
											</div>
										</div>
									</div>
									<div class="panel-body">
										<div class="row">
											<div class="col-md-12 col-xs-12 col-sm-12">
												 <div id="childEvnetMemberDivId"> </div>
											</div>
											<div class="col-md-12 col-xs-12 col-sm-12">
												<div class="bg_ED pad_15 m_top20">
												   <div id="directChildMemberForEventDivId"></div>
												   <div class="row">
														 <div class="col-md-12 col-xs-12 col-sm-10 col-sm-offset-1 col-md-offset-0">
															<div class="row m_top20">
															 <div id="topPoorLocationsEventDivId"></div>
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
	
		<!--Events End -->
		<!-- Committees Start-->
		<div class="col-md-6 col-xs-12 col-sm-12 committeesBlock">
        	<div class="panel panel-default panelNewCustom panel1">
            	<div class="panel-heading">
                	<div class="row">
						<div class="col-md-8 col-sm-8 col-xs-8">
							<h4 class="panel-title text-capital">
								<img src="newCoreDashBoard/img/committees.png" class="iconClass"/>
								committees - <small class="text-muted committeesDate">up to date</small>
							</h4>
						</div>
						<div class="col-md-4 col-sm-4 col-xs-4">
							<span class="basicCommitteesBlockDiv pull-right">
								<i class="fa fa-gears"  data-toggle="tooltip" data-placement="top" title="Settings"></i>
							</span>
							<span class="notesIcon pull-right">
								<i class="glyphicon glyphicon-list-alt"  data-toggle="tooltip" data-placement="top" title="Notes" onClick="displayDashboardComments(1);"></i>
							</span>
							<span class="iconExpand pull-right">
								<i class="glyphicon glyphicon-fullscreen" data-toggle="tooltip" data-placement="top" title="Expand"></i>
							</span>
							<span class="input-group pull-right dateRangePickerCls m_XsTop10 hide">
								<input type="text" id="dateRangeId"	 class="form-control" />
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
								<li role="presentation" class="active text-capital"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">District Level</a></li>
								<li role="presentation"   class="text-capital"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Mandal/town/division level</a></li>
								<li role="presentation"  class="text-capital"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">village/ward level</a></li>
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
	<div class="row">
		<!-- Attendance Start-->
		<div class="col-md-6 col-xs-12 col-sm-12 attendanceBlock">
            <div class="panel panel-default panelNewCustom">
                <div class="panel-heading">
					<div class="row">
						<div class="col-md-9 col-sm-9 col-xs-7">
							<h4 class="panel-title text-capital">
								<img src="newCoreDashBoard/img/attendance.png" class="iconClass"/>
								emp attendance - <small id="attendanceId" class="text-muted"></small>
							</h4>
						</div>
						<div class="col-md-3 col-sm-3 col-xs-5">
							<span class="attendanceSetIcon pull-right">
								<i class="fa fa-gears"  data-toggle="tooltip" data-placement="top" title="Settings"></i>
							</span>
							<span class="notesIconattendance pull-right">
								<i class="glyphicon glyphicon-list-alt"  data-toggle="tooltip" data-placement="top" title="Notes" onClick="displayDashboardCommentsForAttendance(7);"></i>
							</span>
							<span class="attendaceIconExpand pull-right mainExpandCls">
								<i class="glyphicon glyphicon-fullscreen"></i>
							</span>
							<span class="input-group pull-right dateRangePickerClsForAttendance hide" style="width:110px;">
								<input type="text" id="dateRangeIdForAttendance" style="width:90px" class="form-control" />
								<span class="input-group-addon">
									<i class="glyphicon glyphicon-calendar"></i>
								</span>
							</span>
						</div>
					</div>
					<div class="notesDropDown notesArrow">
							<h4 class="text-capital">notes
								<span class="pull-right">
									<i class="glyphicon glyphicon-list-alt"></i>
								</span>
							</h4>
							<div id="notesAttendanceId"></div>
							<hr/>
							<div id="attendanceId" style="color:red;"></div>
							<label>Create Notes</label>
							<textarea class="form-control notesAreaAttendance"></textarea>
							<button class="btn btn-default btnCustomCreateAttendance btn-sm "  onClick="savingDashboardCommentForAttendance(7);">create</button>
						</div>
                </div>
                <div class="panel-body">   
                    <div class="row">
						<!--<div class="col-md-12 col-xs-12 col-sm-12">
							<h5 id="lastUpdatedIdAtt" style="top:-8px;position:relative;right:5px;float:right;font-weight:bold" class="updatedDate pull-right">Last updated : - </h5>
						</div>-->  
                        <div class="col-md-12 col-xs-12 col-sm-12 attendanceBlock">
							<div id="officeAttendanceTdlsId">
							</div>
							<div id="officeAttendanceTdlsDeptWiseId"> 
							</div>
                            <!-- <h4 class="m_top20"><span class="headingColor text-capital">month wise total employees</span></h4>
                            <div id="attendance" style="height:150px;"></div>-->
                        </div>
                        <div class="col-md-6 col-xs-12 col-sm-12 attendanceBlockMore">
							<div id="deptWiseAttendanceDtlsId">
							</div>
                        </div>
						<div class="col-xs-12 col-sm-12 col-md-12">
                        	<i id="expandForMoreId" class="glyphicon glyphicon-option-horizontal pull-right moreAttBlocksIcon" data-toggle="tooltip" data-placement="top" title="Click here for more"></i>
                        </div>
                        <div class="col-md-12 col-xs-12 col-sm-12 moreAttBlocks">
                        	<div class="panel panel-default panelNew">
                            	<div class="panel-heading">
                                	  
									<div class="panel-heading">
										<div class="row">
											<div class="col-md-6 col-xs-12 col-sm-8">
											<h4><span class="headingColor">Hyderabad Party Office</span></h4>
											</div>
											<div class="col-md-6 col-xs-12 col-sm-4">
												<div class="input-group pull-right dateRangePickerClsForAttendance hide" style="width:210px;">
													<input type="text" id="dateRangeIdForAttendance1" style="width:180px" class="form-control" />
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-calendar"></i>
													</span>  
												</div>
											</div>
										</div>
									</div>
                                </div>
                                <div class="panel-body" id="hydDtlsId">
                                	
                                </div>  
                            </div>
                        </div>
						<div class="col-md-12 col-xs-12 col-sm-12 moreAttBlocks" id="hydTopId">
							
						</div> 
						<div class="col-md-12 col-xs-12 col-sm-12 moreAttBlocks">
                        	<div class="panel panel-default panelNew">
                            	<div class="panel-heading">
                                	  
									<div class="panel-heading">
										<div class="row">
											<div class="col-md-6 col-xs-12 col-sm-8">
											<h4><span class="headingColor">Guntur Party Office</span></h4>
											</div>
											<div class="col-md-6 col-xs-12 col-sm-4">
												<div class="input-group pull-right dateRangePickerClsForAttendance hide" style="width:210px;">
													<input type="text" id="dateRangeIdForAttendance2" style="width:180px" class="form-control" />
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-calendar"></i>
													</span>  
												</div>
											</div>
										</div>
									</div>
                                </div>
                                <div class="panel-body" id="gunDtlsId">
                                	
                                </div>  
                            </div>
                        </div>
						<div class="col-md-12 col-xs-12 col-sm-12 moreAttBlocks" id="gunTopId">
							
						</div>
						
						
                    </div>
                </div>
            </div>
       </div>
	 	<!--Cadre Registration -->
		<div class="col-md-6 col-xs-12 col-sm-12 cadreBlock">
        	<div class="panel panel-default panelNewCustom">
            	<div class="panel-heading">
                	<div class="row">
                    	<div class="col-md-8 col-sm-8 col-xs-8">
							<h4 class="panel-title text-capital">
								<img src="newCoreDashBoard/img/cadreRegistration.png" class="iconClass"/>
								cadre registration <small class="text-muted">2016-2018</small>
							</h4>
						</div>
						<div class="col-md-4 col-sm-4 col-xs-4">
							<span class="cadreSettings pull-right">
								<i class="fa fa-gears"  data-toggle="tooltip" data-placement="top" title="Settings"></i>
							</span>
							<span class="cadreNotes pull-right">
								<i class="glyphicon glyphicon-list-alt"  data-toggle="tooltip" data-placement="top" title="Notes" onClick="displayDashboardComments(1);"></i>
							</span>
							 <!--<span class="cadreExpand pull-right">  
								<i class="glyphicon glyphicon-fullscreen" data-toggle="tooltip" data-placement="top" title="Expand"></i>
							</span>-->
							<span class="input-group pull-right  m_XsTop10 hide">
								<input type="text" id="dateRangeId"	 class="form-control" />
								<span class="input-group-addon">
									<i class="glyphicon glyphicon-calendar"></i>
								</span>
							</span>
						</div>
                    </div>
                </div>
                <div class="panel-body">
			    	<div class="row">
						<div class="col-md-12 col-xs-12 col-sm-12">
							<h6 id="lastUpdatedTimeCadreId" style="top:-8px;position:relative;right:5px;float:right;font-weight:bold">Last Updated : </h6>
						</div>
                        <div class="col-md-12 col-xs-12 col-sm-12 cadreBlock">
						<h4 class="text-capital m_top10"><span class="headingColor">Total Registrations</span></h4>
						
						    <div>
								<div id="totalTodayCadreRegistrationBlockDivAPId"></div>
								<div id="totalTodayCadreRegistrationBlockDivTSId"></div>  
							</div>  
							
                            <div class="row">
							
							   <div id="enumeratorsInfoDivId"></div>
							   <div class="col-md-6 col-xs-12 col-sm-12 m_top20">
									<button class="btn btn-success btn-block text-capital " id="cadreModalDivid">kuppam constituency <br/>detailed report</button>
							   </div>
							   <div class="col-md-6 col-xs-12 col-sm-12 m_top20">
									<button class="btn btn-success btn-block text-capital " id="cadreModalTabDivid">Kuppam Constitency Tab User<br/> Detailed Report</button>
							   </div>
                            </div>
                        </div>
						
						<div class="col-md-6 col-xs-12 col-sm-6 moreCadreBlock">
                        	<div class="row">
                            	<div class="col-md-12 col-xs-12 col-sm-12">
									<div class="row">';
										<div class="col-md-12 col-xs-12 col-sm-12">
											
											
										</div>
									</div>
                                <div id="constituenctDetailedReport"></div>	     
                                </div>
                            </div>
					     </div>
					    <div class="col-md-6 col-xs-12 col-sm-6 moreCadreBlock">
                        	<div class="row">
                            	<div class="col-md-12 col-xs-12 col-sm-12">
                                	<ul class="activeUlCls list-inline pull-right">
                                    	<li class="active cadrePositiveNegativeCls" attr_value="positive" ><i class="fa fa-arrow-up"></i>&nbsp;top 5 Positive</li>
										<li class="cadrePositiveNegativeCls" attr_value="negative"><i class="fa fa-arrow-down"></i>&nbsp;top 5 Negative</li>
                                    </ul>
                                </div>
                                <div class="col-md-12 col-xs-12 col-sm-12">
                                	<div id="userTypeWiseTop5PositiveAndNegitiveCadreDivId"></div>
                                </div>
                            </div>
                        </div>
						
						<div class="col-xs-12 col-sm-12 col-md-12">
                        	<i class="glyphicon glyphicon-option-horizontal pull-right moreBlocksCadreIcon" data-toggle="tooltip" data-placement="top" title="Click here for more"></i>
                        </div>
                        <div class="col-md-12 col-xs-12 col-sm-12 moreBlocksCadre">
                        	<ul class="activeUlCls list-inline pull-right" style="margin-right: 12px !important;display:inline-block">
                                <li class="text-capital"><i class="fa fa-gears"></i></li>
                            </ul>
                            <ul class="activeUlCls list-inline pull-right" style="margin-right: 12px !important;display:inline-block">
                                <li class="text-capital">Comparison</li>
                            </ul>
                            
                            <ul class="newsComparisonUl list-inline" style="margin-right: 12px !important;">
                                <li class="text-capital newsComparisonHeading">Detailed</li>
                                <li class="active text-capital a">today</li>
                                <li class="text-capital a">overall</li>
                                <li><i class="glyphicon glyphicon-calendar"></i></li>
                            </ul>
                            
                        </div>
                        <!--<div class="col-md-12 col-xs-12 col-sm-12 moreBlocksCadre m_top10">
                        	<div class="panel panel-default panelNew">
                            	<div class="panel-heading">
                                	<h4 class="panel-title"><span class="headingColor">District Registration Target  wise overview</span></h4>
                                </div>
                                <div class="panel-body">
                                	<div class="row">
                                    	<div class="col-md-6 col-xs-12 col-sm-12">
                                        	<h4 class="bg_ED pad_15 text-capitalize text-center">Andhra Pradesh</h4>
                                            <div class="col-md-4 col-xs-12 col-sm-4 pad_right0" style="background-color:#E1F5F4">
                                            	<div class="pad_5">
                                                	<h5 class="text-capital">total</h5>	
                                                    <h3>40000</h3>
                                                    <hr style="border-color:#CCC;"/>
                                                    <table class="table">
                                                    	<tr style="color:#1770C4">
                                                        	<td>Web</td>
                                                            <td>10000</td>
                                                            <td><small class="text-muted">25%</small></td>
                                                        </tr>
                                                        <tr style="color:#55E1DA">
                                                        	<td>Tab</td>
                                                            <td>10000</td>
                                                            <td><small class="text-muted">25%</small></td>
                                                        </tr>
                                                        <tr style="color:#CDA737">
                                                        	<td>Office</td>
                                                            <td>10000</td>
                                                            <td><small class="text-muted">25%</small></td>
                                                        </tr>
                                                        <tr style="color:#540B88">
                                                        	<td>Online</td>
                                                            <td>10000</td>
                                                            <td><small class="text-muted">25%</small></td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </div>
                                            <div class="col-md-4 col-xs-12 col-sm-4 pad_right0 pad_left0" style="background-color:#E2EBE6">
                                            	<div class="pad_5">
                                                	<h5 class="text-capital">renewal</h5>	
                                                    <h3>40000 <small class="text-muted">55%</small></h3>
                                                    <hr style="border-color:#CCC;"/>
                                                    <table class="table">
                                                    	<tr style="color:#1770C4">
                                                        	<td>Web</td>
                                                            <td>10000</td>
                                                            <td><small class="text-muted">25%</small></td>
                                                        </tr>
                                                        <tr style="color:#55E1DA">
                                                        	<td>Tab</td>
                                                            <td>10000</td>
                                                            <td><small class="text-muted">25%</small></td>
                                                        </tr>
                                                        <tr style="color:#CDA737">
                                                        	<td>Office</td>
                                                            <td>10000</td>
                                                            <td><small class="text-muted">25%</small></td>
                                                        </tr>
                                                        <tr style="color:#540B88">
                                                        	<td>Online</td>
                                                            <td>10000</td>
                                                            <td><small class="text-muted">25%</small></td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </div>
                                            <div class="col-md-4 col-xs-12 col-sm-4 pad_left0" style="background-color:#FBF4E4">
                                            	<div class="pad_5">
                                                	<h5 class="text-capital">new</h5>	
                                                    <h3>40000 <small class="text-muted">55%</small></h3>
                                                    <hr style="border-color:#CCC;"/>
                                                    <table class="table">
                                                    	<tr style="color:#1770C4">
                                                        	<td>Web</td>
                                                            <td>10000</td>
                                                            <td><small class="text-muted">25%</small></td>
                                                        </tr>
                                                        <tr style="color:#55E1DA">
                                                        	<td>Tab</td>
                                                            <td>10000</td>
                                                            <td><small class="text-muted">25%</small></td>
                                                        </tr>
                                                        <tr style="color:#CDA737">
                                                        	<td>Office</td>
                                                            <td>10000</td>
                                                            <td><small class="text-muted">25%</small></td>
                                                        </tr>
                                                        <tr style="color:#540B88">
                                                        	<td>Online</td>
                                                            <td>10000</td>
                                                            <td><small class="text-muted">25%</small></td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6 col-xs-12 col-sm-12">
                                        	<h4 class="bg_ED pad_15 text-capitalize text-center">telangana</h4>
                                            <div class="col-md-4 col-xs-12 col-sm-4 pad_right0" style="background-color:#E1F5F4">
                                            	<div class="pad_5">
                                                	<h5 class="text-capital">total</h5>	
                                                    <h3>40000</h3>
                                                    <hr style="border-color:#CCC;"/>
                                                    <table class="table">
                                                    	<tr style="color:#1770C4">
                                                        	<td>Web</td>
                                                            <td>10000</td>
                                                            <td><small class="text-muted">25%</small></td>
                                                        </tr>
                                                        <tr style="color:#55E1DA">
                                                        	<td>Tab</td>
                                                            <td>10000</td>
                                                            <td><small class="text-muted">25%</small></td>
                                                        </tr>
                                                        <tr style="color:#CDA737">
                                                        	<td>Office</td>
                                                            <td>10000</td>
                                                            <td><small class="text-muted">25%</small></td>
                                                        </tr>
                                                        <tr style="color:#540B88">
                                                        	<td>Online</td>
                                                            <td>10000</td>
                                                            <td><small class="text-muted">25%</small></td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </div>
                                            <div class="col-md-4 col-xs-12 col-sm-4 pad_right0 pad_left0" style="background-color:#E2EBE6">
                                            	<div class="pad_5">
                                                	<h5 class="text-capital">renewal</h5>	
                                                    <h3>40000</h3>
                                                    <hr style="border-color:#CCC;"/>
                                                    <table class="table">
                                                    	<tr style="color:#1770C4">
                                                        	<td>Web</td>
                                                            <td>10000</td>
                                                            <td><small class="text-muted">25%</small></td>
                                                        </tr>
                                                        <tr style="color:#55E1DA">
                                                        	<td>Tab</td>
                                                            <td>10000</td>
                                                            <td><small class="text-muted">25%</small></td>
                                                        </tr>
                                                        <tr style="color:#CDA737">
                                                        	<td>Office</td>
                                                            <td>10000</td>
                                                            <td><small class="text-muted">25%</small></td>
                                                        </tr>
                                                        <tr style="color:#540B88">
                                                        	<td>Online</td>
                                                            <td>10000</td>
                                                            <td><small class="text-muted">25%</small></td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </div>
                                            <div class="col-md-4 col-xs-12 col-sm-4 pad_left0" style="background-color:#FBF4E4">
                                            	<div class="pad_5">
                                                	<h5 class="text-capital">new</h5>	
                                                    <h3>40000</h3>
                                                    <hr style="border-color:#CCC;"/>
                                                    <table class="table">
                                                    	<tr style="color:#1770C4">
                                                        	<td>Web</td>
                                                            <td>10000</td>
                                                            <td><small class="text-muted">25%</small></td>
                                                        </tr>
                                                        <tr style="color:#55E1DA">
                                                        	<td>Tab</td>
                                                            <td>10000</td>
                                                            <td><small class="text-muted">25%</small></td>
                                                        </tr>
                                                        <tr style="color:#CDA737">
                                                        	<td>Office</td>
                                                            <td>10000</td>
                                                            <td><small class="text-muted">25%</small></td>
                                                        </tr>
                                                        <tr style="color:#540B88">
                                                        	<td>Online</td>
                                                            <td>10000</td>
                                                            <td><small class="text-muted">25%</small></td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div> 
                        </div>-->
						<div class="col-md-12 col-xs-12 col-sm-12" style="display:none;">
							<div class="panel panel-default">
								<div class="panel-heading">
									<span class="headingColor">State Wise Overview</span>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-md-6 col-xs-12 col-sm-6">
											<div class="panel panel-default">
												<div class="panel-heading">
													andhra pradesh
												</div>
												<div class="panel-body pad_0">
													<div class="row">
														<div class="col-md-4 col-xs-12 col-sm-4 pad_right0">
															
														</div>
														<div class="col-md-4 col-xs-12 col-sm-4 pad_right0 pad_left0">
															
														</div>
														<div class="col-md-4 col-xs-12 col-sm-4 pad_left0">
															
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
                        <div class="col-md-12 col-xs-12 col-sm-12 moreBlocksCadre">
                        	<div class="panel panel-default panelNew">
                            	<div class="panel-heading">
                                	<div class="row">
                                    	<div class="col-md-6 col-xs-12 col-sm-12">
                                        	<h4><span class="headingColor">District Registrations - Target Vs Archieve</span></h4><br>
                                        	<h2><span class="headingColor" id="apDistrictHeadingId" style="display:none;">Andhra Pradesh</span></h2>
                                        </div>
                                        <div class="col-md-6 col-xs-12 col-sm-12">
                                        	<ul class="activeUlCls list-inline pull-right">
                                                <li class="active districtFilterCls" attr_filter_value="All">All</li>
                                                <li class="districtFilterCls" attr_filter_value="verygood">Very Good</li>
                                                <li class="districtFilterCls" attr_filter_value="good">Good</li>
                                                <li class="districtFilterCls" attr_filter_value="ok">Ok</li>
												<li class="districtFilterCls" attr_filter_value="poor">Poor</li>
                                                <li class="districtFilterCls" attr_filter_value="verypoor">Very Poor</li>
                                            </ul>
                                        </div>
                                    </div>
                                	
                                </div>
                                <div class="panel-body">
                                	<div class="row">
                                    	<div class="col-md-12 col-xs-12 col-sm-12">
                                        	<div id="userTypeWiseHighChartDivId"></div>
                                        </div>
                                    </div>
									<div class="row">
								    	<div class="col-md-12 col-xs-12 col-sm-12">
                                        	<h2><span class="headingColor" id="tsDistrictHeadingId" style="display:none;" >Telangana</span></h2>
                                        	<div id="tsDistrictWiseRegistrationDivId"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
					    <div class="col-md-12 col-xs-12 col-sm-12 moreBlocksCadre">
                        	<div class="panel panel-default panelNew">
                            	<div class="panel-heading">
                                	<div class="row">
                                    	<div class="col-md-6 col-xs-12 col-sm-12">
                                        	<h4><span class="headingColor text-capitalize">contituency wise registrations</span></h4>
                                        </div>
                                        <div class="col-md-6 col-xs-12 col-sm-12" style="position:relative;">
											<!--<span class="pull-right" style="font-size:8px;position:absolute;top:-10px;right:10px;cursor:pointer;">Clear Filter</span>-->
											<ul class="activeUlCls list-inline pull-right" style="margin-right: 12px !important;display:inline-block">
												<li  id="settingsCadre" class="text-capital"><i class="fa fa-gears" title="filter result"></i></li>
											</ul>
                                        	<ul class="activeUlCls list-inline pull-right constituencyUlCls"">
                                            	<li class="active constituencyFilterCls"  id="cnsttncyverlAllCntId" attr_filter_value="All" attr_all_contituency_cnt="0" >All - 0</li>
                                                <li class="constituencyFilterCls" id="cnsttncyveryGoodCntId" attr_very_good_cnt="0" attr_filter_value="verygood">Very Good - 0</li>
                                                <li class="constituencyFilterCls" id="cnsttncyverlGoodCntId" attr_good_cnt="0" attr_filter_value="good">Good - 0</li>
                                                <li class="constituencyFilterCls" id="cnsttncyOkCntId" attr_ok_cnt="0" attr_filter_value="ok">Ok - 0</li>
												<li class="constituencyFilterCls" id="cnsttncyverlPoorCntId" attr_poor_cnt="0" attr_filter_value="poor">poor - 0</li>
                                                <li class="constituencyFilterCls" id="cnsttncyveryPoorCntId" attr_very_poor_cnt="0" attr_filter_value="verypoor">Very Poor - 0</li>
                                            </ul>
                                        </div>
                                    </div>
                                <div class="cadreRDD documentCloseClass" style="z-index:999;margin-top: -3px;width:450px;display:none;" >
										<i class="glyphicon glyphicon-remove newsSetClose pull-right closePopUpCls"></i>
										<div class="row">
										   <div><span style="color:red;margin-left:20px" id="cadreRegSearchErrorId"></span></div>
											<div class="col-md-12 col-xs-12 col-sm-12">
												<div class="pad_15 bg_EE">
													<label class="checkbox-inline">
														<input attr_cadre_search_type="2016Renewal" checked id="2016RenewalCheckBoxId" type="checkbox"/>2016 Renewal
													</label>
													<label class="checkbox-inline">
														<input attr_cadre_search_type="2016New" checked id="2016NewCheckBoxId" type="checkbox"/>2016 New
													</label>
													<label class="checkbox-inline">
														<input attr_cadre_search_type="2014Cadre" checked id="2014CadreCheckBoxId" type="checkbox"/>2014 cadre
													</label>
												</div>
											</div>
											<div class="col-md-6 col-xs-12 col-sm-6">
											   <p>AP DISTRICTS</p>
											   <ul class="list-inline">
											    <li><label><input type="checkbox" class="selectAllApDistrict"/>&nbsp&nbspSelect All</label></li>
										       </ul>
											   <div id="apDistrictId"></div>
											</div>
											<div class="col-md-6 col-xs-12 col-sm-6">
											  <p>TS DISTRICTS</p>
											  <ul class="list-inline">
											    <li><label><input type="checkbox" class="selectAllTsDistrict"/>&nbsp&nbspSelect All</label></li>
										       </ul>
											  <div id="tsDistrictId"></div>
											</div>
											<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
												<input type="button" class="btn btn-success" id="getCadreRegistrationDetailsBtnId" value="GetDetails"/>
											</div>
										</div>
									</div>
                                </div>
                                <div class="panel-body">
                                	<div class="row">
										<div class="col-md-6 col-xs-12 col-sm-6">
											<h4>Andhra Pradesh</h4>
											<div class="row m_top20">
												<div class="col-md-2 col-xs-12 col-sm-2">
													<i class="glyphicon glyphicon-sort-by-attributes decendingApSorting pull-left" attr_filter_value="No" style="transform:rotate(180deg)"></i>
													<i class="glyphicon glyphicon-sort-by-attributes-alt ascendingApSorting pull-left" attr_filter_value="No"></i>
												</div>
												<div class="col-md-4 col-xs-12 col-sm-4">
													<select attr_filter_value="All" class="form-control" id="apConstituencySelectBoxId"></select>
												</div>
											</div>
											<div Id="apscrollBarDivId">
											<div id="apConstituencyRegistrationReportDivId" class="m_top20"></div>
											</div>
										</div>
										<div class="col-md-6 col-xs-12 col-sm-6">
											<h4>Telangana</h4>
											<div class="row m_top20">
												<div class="col-md-2 col-xs-12 col-sm-2">
													<i class="glyphicon glyphicon-sort-by-attributes decendingTsSorting pull-left" attr_filter_value="No" style="transform:rotate(180deg)"></i>
													<i class="glyphicon glyphicon-sort-by-attributes-alt ascendingTsSorting pull-left" attr_filter_value="No"></i>
												</div>
												<div class="col-md-4 col-xs-12 col-sm-4">
													<select attr_filter_value="All" class="form-control" id="tsConstituencySelectBoxId"></select>
												</div>
											</div>
											<div Id="tsscrollBarDivId">
											<div id="tsConstituencyRegistrationReportDivId" class="m_top20"></div>
											</div>
										</div>
                                    </div>
                                </div>
                            </div>
                        </div>                   
						<!--<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
							<div class="panel panel-default panelNew">
								<div class="panel-heading">
									<div id="designationListId"></div>
								</div>
								<div class="panel-body">  
									<div class="row" id="childMembersId">

									</div>
									<div class="bg_ED pad_15">
										<div class="row" id="directChildId"></div>   
										<div class="row" id="enumeratorsId"></div>     
										<div class="row m_top20">
											<div class="col-md-6 col-xs-12 col-sm-6">
												<span class="headingColor">districts wise registrations</span>
												<div id="individualDtlsId"></div>  
											</div>
											<div class="col-md-6 col-xs-12 col-sm-6">
												<span class="headingColor">registered voter id</span>
												<div id=""></div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>-->  
						
                    </div>
                </div>
            </div>
        </div>
		<!--Cadre Registration ENd -->
	 </div>
	</div>
</div> 
<!--model start -->
<!-- Modal -->

<div class="modal fade" id="myModelId" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document" style="width:75%"> 
    <div class="modal-content" style="border-radius:0px">
      <div class="modal-header" style="background-color:#CCC">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Training Program Member Details</h4>
      </div>
       <div class="modal-body">
        <div class="row">
			<div class="col-md-12 col-xs-12 col-sm-12">
			<div class="pull-right" id="positionId">  
				
			</div> 
			</div>
			<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
				<div class="table-responsive" id="memberId">  
				  
				</div>
				<div id="processingImgId" style="display:none;"></div>    
				<div id="processingImgAttendId"></div>    
			</div>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<!--model end -->
<!-- Modal -->
<div class="modal fade" id="cadreModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1">
  <div class="modal-dialog modal-lg" role="document" style="width:85%">
    <div class="modal-content" style="border-radius:0px">
      <div class="modal-header" style="background-color:#CCC">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel1">KUPPAM CONSTITUENCY DETAILED REPORT</h4>
      </div>
      <div class="modal-body">
		<div class="row tabModal" style="display:none;">
			<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
				<div class="row m_top10">
					<div class="col-md-3 col-xs-12 col-sm-3">
						<select class="form-control" id="constituencySeletBoxId">
						  <option value="282">Kuppam</option>
						  <select>
						  <span id="constituencyErrorId" style="color:red"></span>
					</div>
					<div class="col-md-3 col-xs-12 col-sm-3">
						<span class="input-group pull-right">
							<input type="text" id="dateRangeIdForCadre"	 class="form-control" />
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-calendar"></i>
							</span>
						</span>
					</div>
					<div class="col-md-1 col-xs-12 col-sm-3" style="margin-top: -8px;">
						<button class="btn btn-success pull-right m_top10 tabUserWiseDetails" type="submit" style="margin-right: 16px;">Submit</button>
					</div>
				</div>
				<div class="row showTabUserWiseDetails" style="display:none">
				  <div class="col-md-12 col-xs-12 col-sm-12 m_top20 mtop-20">
				     <div id="notReceiveRegistrationFieldStaffDivId"></div>
					</div>
					<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
							<div id="tabUserWiseReportDiv"></div>
					</div>
				</div>
			 </div>
		</div>
        <div class="row webModal">
				<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
					<label class="radio-inline">
						<input type="radio" class="scopeRadioCls" name="scopeType" id="inlineRadio1" value="overall" style="margin-top: 0px;" checked><h5>Over All</h5>
					</label>
					<label class="radio-inline">
						<input type="radio" class="scopeRadioCls" name="scopeType" id="inlineRadio2" value="today" style="margin-top: 0px;"><h5>Today</h5>
					</label>  
				</div>
				<div class="col-md-12 col-xs-12 col-sm-12 m_top10">     
					<label class="radio-inline">
						<input type="radio" class="locationRadioCls" name="selectionType" id="inlineRadio3" value="mandal" style="margin-top: 0px;" checked><h5>Mandal Wise</h5>
					</label>
					<label class="radio-inline">
						<input type="radio" class="locationRadioCls" name="selectionType" id="inlineRadio2" value="panchayat" style="margin-top: 0px;"><h5>Panchayat Wise</h5>
					</label>
					<label class="radio-inline">
						<input type="radio" class="locationRadioCls" name="selectionType" id="inlineRadio1" value="booth" style="margin-top: 0px;" ><h5>Booth Wise</h5>
					</label>
				</div>
		  <div class="col-md-12 col-xs-12 col-sm-12">
			<div id="kupamRegDtlsId"></div>
		  </div>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
<!-- end-->  
<!--attended model-->
<div class="modal fade" id="attendanceModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document" style="width:80%;margin:auto">
    <div class="modal-content">
      <div class="modal-header bg_EE">
    <div class="row">
      <div class="col-md-6 col-xs-12 col-sm-6">
        <h4 class="modal-title text-capitalize" id="diptNameId"></h4>
        <h6 id="officeNameId"></h6>
      </div>
      <div class="col-md-3 col-md-offset-3 col-xs-12 col-sm-6">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <div class="input-group">
          <input type="text" class="form-control" id="attenDatePickerModal"/>
          <span class="input-group-addon">
            <i class="glyphicon glyphicon-calendar"></i>  
          </span>
        </div>
      </div>
    </div>
      </div>
      <div class="modal-body">      
        <div class="row">
      <div class="col-md-6 col-xs-12 col-sm-6" id="totalCountId">
        
      </div>
      <div class="col-md-6 col-xs-12 col-sm-6">
        <div id="attedanceModalId" height="200px"></div>
      </div>
      <div class="col-md-12 col-xs-12 col-sm-12">
        <h4><span class="headingColor text-capitalize">department overview</span></h4>
        <div id="dayWiseOvervwModal" style="height:250px"></div>
      </div>  
	  <div class="col-md-12 col-xs-12 col-sm-12 m_top20">
		<div class="m_top20">
			<h4><span class="headingColor text-capitalize">employee overview</span></h4>
		</div>
		<div class="table-responsive m_top20" id="employeeOverViewId">
		</div>
	  </div>      
    </div>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="attendanceModalEmplo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document" style="width:80%;margin:auto">
    <div class="modal-content">
      <div class="modal-header bg_EE">
		<div class="row">
		  <div class="col-md-6 col-xs-12 col-sm-6">
			<h4 class="modal-title text-capitalize" id="diptNameForEmpId"></h4>
			<h6 id="officeNameForEmpId"></h6>      
		  </div>
		  <div class="col-md-3 col-md-offset-3 col-xs-12 col-sm-6">
			<button type="button" class="close closeButtonCls" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<div class="input-group">
			 <input type="text" class="form-control" id="attenDatePickerModalForEmpId"/>
			  <span class="input-group-addon">
				<i class="glyphicon glyphicon-calendar"></i>      
			  </span> 
			</div>
		  </div>
		</div>
      </div>
      <div class="modal-body">      
        <div class="row">
		  <div class="col-md-6 col-xs-12 col-sm-6">
			<h4><span class="headingColor text-capitalize">overview</span></h4>
			<div id="tableAttendanceId">
			</div>
			
		  </div>
		  <div class="col-md-6 col-xs-12 col-sm-6">
			<div id="attedanceModalForEmpId" height="150px"></div>       
		  </div>
		  <div class="col-md-12 col-xs-12 col-sm-12 m_top20">
				<div class="m_top20">
					<h4><span class="headingColor text-capitalize">employee overview</span></h4>
				</div>
				<div class="m_top20 table-responsive" id="singleEmployeeOverViewId" style="height:200px"> </div>      
			</div> 
		</div>
      </div>
    </div>
  </div>
</div>

<!-- Model for Debate Start-->
		<div class="modal" tabindex="-1" role="dialog" id="debateModelDivId">
		  <div class="modal-dialog modal-lg" style="width:85%">
			<div class="modal-content" style="border-radius:0px">
			  <div class="modal-header" style="background-color:#CCC">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">Debate Details</h4>
			  </div>
			  <div class="modal-body">
				  <div id="debateModelId" class="row"></div>				 
					<!--<center><img id="dataLoadingsImgForDebate" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;margin-top:50px;"/></center>-->
			  </div>
			  <div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			  </div>
			</div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
<!--end-->
<!-- Model for Cadre  Start-->
		<div class="modal" tabindex="-1" role="dialog" id="noOfSamplesModal">
		  <div class="modal-dialog modal-lg" style="width:65%">
			<div class="modal-content" style="border-radius:0px">
			  <div class="modal-header" style="background-color:#CCC">
				<button type="button" class="close closeModal" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="tabUserInfoDetailsHeadingId"></h4>
			  </div>
			  <div class="modal-body">
				<div class="row">
				  <div class="col-md-12 col-xs-12 col-sm-12">
					<div id="noOfSamplesDetailsDiv" ></div>	
				  </div>
				   <div class="col-md-12 col-xs-12 col-sm-12">
					<div id="tabUserInfoDivId" ></div>	
				  </div>
				</div>
			  </div>
			  <div class="modal-footer">
				<button type="button" class="btn btn-default closeModal" data-dismiss="modal">Close</button>
			  </div>
			</div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
<!--end-->

<!-- meeting comment modal start -->
<div class="modal fade" id="meetingCommentModalId" tabindex="-1"  role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document" style="width:80%;margin:auto">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	    <h4  class="modal-title" id="meetingHeadingId"></h4>
		<!--<input type="button" value="view not comment meeting"/>-->
		<div class="row">
		<div class="col-md-12 col-xs-12 col-sm-12 consolidatedCls">
			<div class="col-md-4 col-xs-12 col-sm-4"  style="text-align: center; margin-top: 25px;">
			<label class="radio-inline">
			  <input type="radio" id="ConsolidatedradioId" name="inlineRadioOptions" id="inlineRadio1" value="option1"> Consolidated 
			</label>
			<label  class="radio-inline individualRadioBtnCls">
			  <input type="radio" id="individualradioId" name="inlineRadioOptions" id="inlineRadio2" value="option2"> Individual
			</label>
			</div>
			<div class="col-md-3 col-xs-12 col-sm-6">
			<label>Consolidated Type</label>
			<select  id="commentFilterSelectBoxId" class="form-control col-xs-3">
			  <option value="0">District</option>
			  <option value="1">Constituency</option>
			</select>
			</div>
		</div>
		</div>
		<div class="row m_top10">
			<div class="col-md-12 col-xs-12 col-sm-12 filterCls">
				<div class="col-md-3 col-xs-12 col-sm-6 districtSlctBxCls">
				<label>District</label>
				<select  id="districtSlctBxId" class="form-control col-xs-3">
				 <option value="0">Select District</option>
				</select>
				</div>
				<div class="col-md-3 col-xs-12 col-sm-6 constituencySlctBxCls">
				<label>Constituency</label>
				<select  id="constituencySlctBxId" class="form-control col-xs-3">
				<option value="0">Select Constituency</option>
				</select>
				</div>
				<div class="col-md-3 col-xs-12 col-sm-6 mandalSlctBxCls">
				<label>Mandal/Town/Division</label>
				<select  id="mandalSlctBxId" class="form-control col-xs-3">
				<option value="0">select Mandal/Town/Division</option>
				</select>
				</div>
				<div class="col-md-3 col-xs-12 col-sm-6" style="margin-top: 25px;">
			     <input type="button" id="getDetailsBtnId" class="btn btn-success" value="GetDetails"/>
				</div>
			</div>
	    </div>
		<!--<div class="row m_top10">
			<div class="col-md-12 col-xs-12 col-sm-12">
			 <input type="checkbox" id="getMeetingDetails" class="btn btn-success"/>If u will check this. you will get all (comment / not comment ) meeting details
			</div>
		</div>-->
	  </div>
      <div class="modal-body">
        <div class="row">
			<div class="col-md-12 col-xs-12 col-sm-12">
			<div ><center ><img style="display: none;" src="images/icons/loading.gif" id="meetingCommentProcessingImgId"></center></div>
			<div id="meetingCommentDtlsTblId"></div> 
			</div>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
<!-- end -->
<div class="modal fade" id="meetingCommentDtlsModalId" tabindex="-1"  role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document" style="width:60%;margin:auto">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close modalCloseCls" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	    <h4 class="modal-title" id="meetingHeadingId">Meeting Description</h4> 
      <div class="modal-body">
        <div class="row">
			<div class="col-md-12 col-xs-12 col-sm-12">
			<div ><center ><img style="display: none;" src="images/icons/loading.gif" id="meetingSubLevelRsltProcessingImgId"></center></div>
			<div id="meetingDetailsTblId"></div> 
			</div>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default modalCloseCls" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
</div>
<input type="hidden" id="cmtId" attr_cmt_id="editTextId'+i+'" value=""></input>
<input type="hidden" id="cmtTrngId" attr_cmt_id="editTextTrngId'+i+'" value=""></input>
<input type="hidden" id="cmtDebateId" attr_cmt_id="editTextDebateId'+i+'" value=""></input>
<input type="hidden" id="cmtNewsId" attr_cmt_id="editTextNewsId'+i+'" value=""></input>
<input type="hidden" id="cmtMeetingId" attr_cmt_id="editTextmettingId'+i+'" value=""></input>
<input type="hidden" id="cmtEventsId" attr_cmt_id="editTextEventsId'+i+'" value=""></input>
<input type="hidden" id="cmtAttendanceId" attr_cmt_id="editTextAttendanceId'+i+'" value=""></input>
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
<script src="newCoreDashBoard/js/statusColorCodesForNews.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/eventsActivities.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/attendanceCoreDashBoard.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/cadreRegistration.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mCustomScrollbar.js"></script>
<script src="dist/scroll/jquery.mousewheel.js"></script>
<script type="text/javascript">
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
	  initialiseDatePickerForTrainingProgram();
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
	  
	$(document).ready(function(){
		//Main header remove
		$(".eventsheader").hide();
		$('[data-toggle="tooltip"]').tooltip();
		stateLevelCampDetails();
	});
	getLoggedInUserStructure();
	
	onLoadCalls();
	
	function onLoadCalls(){
		
		getRescentArticleTime();
		
		committeeBasicCall();
		
		//training program call
		/*var idStr = $("#hideProgramId").attr("attr_prorgam_id_arr");
		var programIdArr = [];
		var arr = idStr.split(","); 
		for(var i in arr){
			programIdArr.push(arr[i]);
		} 
		console.log($("#hideProgramId").attr("attr_prorgam_id_arr"));      
		stateLevelCampDetails();
		stateLevelCampDetailsRepresentativeWise(programIdArr);
		getStateLevelCampCount(programIdArr); */   
		getTrainingCampBasicDetailsCntOverview();   
		//Meeting
		getPartyMeetingTypeByPartyMeetingMainType();
		getStateLevelMeetingsByMeetingType();
		getSpecialMeetingsByMeetingType();
		//events
		getEventBasicCntDtls();
		//news please dont remove
		$("#currentViewing").html(" TODAY ( "+moment().format('DD-MM-YYYY')+" )");
		//getNewsBasicCounts();
		commonNewsBasicCalls();
		getAllNewsPapers();
		//getPaperWiseNewsBasicCounts();
		//Debates
		getPartyWiseTotalDebateDetails();
        //cadreRegistration
		cadreRegistrationBasicCall(globalActivityMemberId);        
		getAllItsSubUserTypeIdsByParentUserTypeIdForCadreRegistration(globalUserTypeId);          
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

$(document).on("click",".moreTrainingCampBlocksIcon",function(){
	//$("#switchButtonId").removeClass("showCls");
	//$("#switchButtonId").addClass("hdCls");
	if($("#switchButtonId").hasClass("showCls")){ 
		$(".trainingDetailedBlock").hide();
		$("#switchButtonId").addClass("hdCls");
		$("#switchButtonId").removeClass("showCls");  
	}else{
		$(".trainingDetailedBlock").show();
		$("#switchButtonId").removeClass("hdCls");
		$("#switchButtonId").addClass("showCls");
	}
});    

</script> 
</body>
</html>
