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
<link href="coreApi/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="coreApi/Plugins/DataTable/exportButtons.css" type="text/css" rel="stylesheet"/>
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
.slickApplyPopupDays li.active{
	background: #E29203 none repeat scroll 0 0;
	 border-radius: 5px 5px 0px 0px;
	 margin-left:5px;
	 font-size:11px;
	 border-bottom:0px;
}
.slickApplyPopupDays li.active a{
	background: #E29203 none repeat scroll 0 0;
	 border-radius: 5px 5px 0px 0px;
	 margin-left:5px;
	 font-size:11px;
	 border-bottom:0px;
}

.slickApplyPopupDays li{
	background: #e6e6e6 none repeat scroll 0 0;
	border-radius: 5px 5px 0px 0px;
	margin-left:5px;
	font-size:11px;
	border-bottom:0px;
}
.customModal{
	background-color:#fff;
}

</style>
</head>
<body>  
<header>
	<nav class="navbar navbar-default navbarHeader">
      <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed hidden-xs" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
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
        <div class="collapse navbar-collapse hidden-xs" id="bs-example-navbar-collapse-1">
          <ul class="nav navbar-nav navbar-right">
          	<li class="birthdayHideShowCls"><a href="birthDayAction.action"><i class="glyphicon glyphicon-gift" data-toggle="tooltip" data-placement="bottom" title="Click here Birthday Page"></i><span class="birthdayCount" style="top:3px;left:31px"> <b> ${sessionScope.birthDayCount} </b></span></a></li>
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
<div class="rightNavigationMenu">
	<div class="backgroundBlock hidden-xs"></div>
	<ul class="hidden-xs">
		<li expand-icon="alerts" right-nav="true">Alerts</li>
		<li expand-icon="debates" right-nav="true">debates</li>
		<li expand-icon="news" right-nav="true">News - Print Media</li>
		<li expand-icon="electronic" right-nav="true">News - electronic media</li>
		<li expand-icon="prajaSankaplaYatra" right-nav="true">Praja Sankapla Yatra</li>
		<li expand-icon="pressmeet" right-nav="true">Press Meet</li>
		<li expand-icon="tours" right-nav="true">Tours</li>
		<li expand-icon="meetings" right-nav="true">meetings</li>
		<li expand-icon="cadre" right-nav="true">membership</li>
		<li expand-icon="committees" right-nav="true">committees</li>
		<li expand-icon="boothCommittees" right-nav="true">booth committees</li>
		<li expand-icon="events" right-nav="true">events & activities</li>
		<li expand-icon="attendance" right-nav="true">emp attendance</li>
		<li expand-icon="training" right-nav="true">training</li>
		<li expand-icon="cadreInsurance" right-nav="true">Insurance</li>
	</ul>
	<button class="rightNavigationMenuRes visible-xs">
		<span class="icon-bar"></span>
		<span class="icon-bar"></span>
		<span class="icon-bar"></span>
	</button>
</div>
<div class="scrollTopHtml hide">
	<i class="glyphicon glyphicon-chevron-up"></i>
	Top
</div>
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
		<!-- Alerts Start -->
	 	<div class="col-md-6 col-xs-12 col-sm-12 alertsBlock" expand-block="alerts">
			<div class="panel panel-default panelNewCustom">
				<div class="panel-heading">
					<div class="row">
						<div class="col-md-9 col-sm-9 col-xs-12" expand-block-heading="alerts">
							<h4 class="panel-title text-capital">
								<img src="newCoreDashBoard/img/Alert_icon.png" class="iconClass"/>
								Alerts <small class="text-muted"> - <span id="alertDateHeadingId">  </span></small>
							</h4>
						</div>
						<div class="col-md-3 col-sm-3 col-xs-12" expand-block-heading1="alerts">
							<span class="alertSettingBlock pull-right">
							 <i title="" data-placement="top" data-toggle="tooltip" class="fa fa-gears" data-original-title="Settings"></i>
							</span>
							<span class="alertsIconExpand pull-right" expand-icon="alerts">
								<i class="glyphicon glyphicon-fullscreen"></i>
							</span>
							<span class="alertsIconRefresh pull-right">
								<i class="glyphicon glyphicon-refresh"></i>
							</span>
							<span class="input-group pull-right dateRangePickerClsForAlert hide" expand-block-date="alerts" style="width:200px;">
								<input type="text" id="dateRangeIdForAlert" style="width:180px" class="form-control" />
								<span class="input-group-addon">
									<i class="glyphicon glyphicon-calendar"></i>
								</span>
							</span>  
							
						</div>     
					</div>
					<div class="basicAlertBlockDropDown documentCloseClass" style="z-index:999;margin-top: -3px;" >
						<i class="glyphicon glyphicon-remove basicAlertSetClose pull-right" style="cursor:pointer;"></i>
						<div class="row">
							<div class="col-md-6 col-xs-12 col-sm-6 pad_right0 m_top20">
							  <ul class="nav nav-tabs navTabsSettings" role="tablist">
								<li role="presentation" class="text-capital active"><a href="#impactScope" aria-controls="impactScope" role="tab" data-toggle="tab">Impact Scope </a></li>
								<li role="presentation"   class="text-capital"><a href="#alertStatus" aria-controls="alertStatus" role="tab" data-toggle="tab">Alert Status</a></li>
							  </ul>
								<button type="button" class="btn btn-success getAlertDetailsCls" onClick="getAlertDtlsBasedOnSelection('click');">Get Details</button>
							 
							</div>
							<div class="col-md-6 col-xs-12 col-sm-6 pad_left0 pad_right4">
							  <div class="tab-content navTabsSettingsContent">
								<div role="tabpanel" class="tab-pane active" id="impactScope">
								<hr style ="margin-bottom:0px;" />
									<ul class="settingsUl alertImpactSettingCls">
											<li>
												<label class="checkbox-inline">
												<input type="checkbox" attr_scope_type="All" class="alertImpactCheckCls" id="alertImpactScopeSelectAllId" checked>
													<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">All</h5></div>
												</label>
											</li>
										   	 <li>
												<label class="checkbox-inline">
													<input type="checkbox" attr_scope_type="State" class="alertImpactCheckCls" checked>
													<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">State</h5></div>
												</label>
											</li>
											<li>
												<label class="checkbox-inline">
													<input type="checkbox" attr_scope_type="District" class="alertImpactCheckCls" checked>
													<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">District</h5></div>
												</label>
											</li>
											<li>												
												<label class="checkbox-inline">
													<input type="checkbox" attr_scope_type="Constituency" class="alertImpactCheckCls" checked>
													<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">Constituency</h5></div>
												</label>	
											</li>	
											<li>
												<label class="checkbox-inline">
													<input type="checkbox" attr_scope_type="MuncipalityGMC" class="alertImpactCheckCls" checked>
													<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">CORP-GMC</h5></div>
												</label>
											</li>
											<li>
												<label class="checkbox-inline">
													<input type="checkbox" attr_scope_type="mandalMuncipality" class="alertImpactCheckCls" checked>
													<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">Mandal/MUNICIPALITY</h5></div>
												</label>
											</li>
											<li>
												<label class="checkbox-inline">
												<input type="checkbox" attr_scope_type="VillageWardPanchayat"  class="alertImpactCheckCls" checked>
													<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">Village/ward/PANCHAYAT</h5></div>
												</label>
											</li>	
									</ul>
								</div>
								<div role="tabpanel" class="tab-pane" id="alertStatus">
									<hr style ="margin-bottom:0px;" />
									 <ul class="settingsUl alertStatusSettingUl">
									  <!--<div id="alertStatusliDivId"></div>-->
									  <li>
										<label class="checkbox-inline">
										<input type="checkbox" id="alertStatusSelectAllId" attr_scope_type="All" attr_status_id="0" class="alertStausCls" checked>
										<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">All</h5></div>
										</label>
									  </li>
									    <c:forEach items="${alertOverviewVO.subList}" var="status">
											<li>
											<label class="checkbox-inline">
											<input type="checkbox" attr_status_id="${status.id}"  class="alertStausCls" checked>
												<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">${status.name}</h5></div>
											</label>
										   </li>
									    </c:forEach>
									</ul>
								</div>
								<button type="button" class="btn btn-success getAlertDetailsCls" onClick="getAlertDtlsBasedOnSelection('click');">Get Details</button>
							  </div>
							</div>
						</div>
					 </div>
				</div>
				<div class="panel-body">
				    
					<div class="row">
						<div class="col-md-12 col-xs-12 col-sm-12">
								<h6 id="lastAlertUpdatedTimeId" style="top:-8px;position:relative;right:5px;float:right;font-weight:bold"></h6>
						</div>
						<div class="col-md-12 col-xs-12 col-sm-12 alertsBlock" expand-block-inner="alerts">
							<h4><span class="headingColor text-capital">overall alerts</span></h4>
							<div id="alertOverview" class="m_top10"></div>
							<div id="alertOverviewDetails" class="m_top10"></div>
						</div>
						<div class="col-md-6 col-xs-12 col-sm-12 alertLocationDiv m_top10"  expand-block-right="alerts" style="display:none;">
							<h4><span class="headingColor text-capital">Impact Scope Level</span></h4>
							<div id="locationWiseAlertDivId" class="row"></div>
						</div>    
						</div>
						<div class="col-md-12 col-xs-12 col-sm-12 m_top10 districtAltCtnCls" expand-block-right="alerts" style="display:none;">
							<div class="panel panel-default panelNew">
								<div class="panel-heading panelNew" style="background: rgb(237, 238, 240) none repeat scroll 0% 0% ! important;">
									<div class="row">
										<div class="col-md-6 col-xs-12 col-sm-6 pull-right">
											<ul class="activeUlCls alertFilterCls list-inline pull-right">
											    <li class="" style="background:#ddd;">Detailed</li>
												<li class="active optionsCls" onClick="getAlertDetails('1')" attr_id="1" style="margin-left: -7px;">Overview</li>
												<li class="optionsCls" onClick="getAlertDetails('2')" attr_id="2" style="margin-left: -5px;">Status</li> 
												<li class="optionsCls" onClick="getAlertDetails('3')" attr_id="3" style="margin-left: -6px;">Publication</li>  
												<li class="optionsCls" onClick="getAlertDetails('4')" attr_id="4">Comparison</li>  
												<!--<li  id="alertSettingsId"><i class="fa fa-gears" style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title="" data-original-title="Settings"></i></li>-->
											</ul>
										</div>
										<div class="col-md-3 col-xs-12 col-sm-6 locImptLevelDivCls">
											<label class="checkbox-inline">
											  <input type="checkbox" id="impactAlertsId" value="option2" checked onClick="getAlertDetails('1')" class="checkedAlertsCls">Impact Wise
											</label>
											<label class="checkbox-inline">
											  <input type="checkbox" id="locationAlertsId" value="option1" onClick="getLocationWiseAlertDetails()" class="checkedAlertsCls">Location Wise 
											</label>
										</div>
								   </div>
								</div>
								
								<div class="panel-body alertImpctLevelBlcock">  
								   <div class="row">
									<div class="col-md-12 col-xs-12 col-sm-12 stateImpactLevelBlockCls">
										   <div class="panel panel-default panelNew">
											<div class="panel-heading">
												<h4 class="panel-title"><span class="headingColor" id="stateOverviewHeadingId">state overview - impact alerts</span></h4>
											</div>
											<div class="panel-body">
												<div class="row">
													<div class="col-md-12 col-xs-12 col-sm-12">
														  <!-- Nav tabs -->
														  <ul class="nav nav-tabs navTabsAlerts pull-right" role="tablist">
															<li role="presentation" onClick="stateLevelHighchartBuildingFunction();"  class="active impactLevelCls collapseHIghChartViewCls"><a href="#stateOvervwGraph" aria-controls="stateOvervwGraph" role="tab" data-toggle="tab"><i class="fa fa-line-chart"></i></a></li>
															<li role="presentation" onClick="stateLevelTblBuildingFunction();" class="impactLevelCls collapseTblViewCls"><a href="#stateOvervwTable" aria-controls="stateOvervwTable" role="tab" data-toggle="tab">
																<i class="fa fa-table"></i>
															</a></li>
														  </ul>
													</div>
														<div class="col-md-12 col-xs-12 col-sm-12">
														  <!-- Tab panes -->
														  <div class="tab-content">
															<div role="tabpanel" class="tab-pane active collapseHIghChartViewCls" id="stateOvervwGraph">
																<div id="stateImpactLevelHighChartDivId" style="height:250px;"></div>
															</div>
															<div role="tabpanel" class="tab-pane collapseTblViewCls" id="stateOvervwTable">
																<div id="stateImpactLevelTblDivId"></div>
															</div>
														  </div>
														</div>
													</div>
												</div>
											</div>
									</div>
								     <div class="col-md-12 col-xs-12 col-sm-12 districtImpactLevelBlockCls">
										   <div class="panel panel-default panelNew">
											<div class="panel-heading">
												<h4 class="panel-title"><span class="headingColor" id="districtOverviewHeadingId">District overview - impact alerts</span></h4>
											</div>
											<div class="panel-body">
												<div class="row" >
													<div class="col-md-8 col-xs-12 col-sm-8">
														<ul class="list-inline activeUlCls districtUl">
															<li class="descendingConstituencyCls active" onClick="getSortedDistrictInRequiredFormat('Decending')">
																<i class="glyphicon glyphicon-sort-by-attributes" ></i>
															</li>
															<li class="ascendingConstituencyCls" onClick="getSortedDistrictInRequiredFormat('Ascending')">
																<i class="glyphicon glyphicon-sort-by-attributes-alt" style="transform:rotate(180deg)"></i>
															</li>
															<li class="atozDistrictSortingCls" onClick="getSortedDistrictInRequiredFormat('AlphabeticalAscending')">
																A-Z
															</li>
															<li class="ztoaDistrictSortingCls" onClick="getSortedDistrictInRequiredFormat('AlphabeticalDescending')">
																Z-A
															</li>
															<li>
																<select class="form-control" id="districtSelectBoxId" onChange="getSortedDistrictInRequiredFormat('Search')">
																 <option value="0">Select District </option>
																</select>
															</li>
														</ul>
													</div>
													<div class="col-md-4 col-xs-12 col-sm-4">
														  <!-- Nav tabs -->
														  <ul class="nav nav-tabs navTabsAlerts pull-right" role="tablist">
															<li role="presentation" onClick="districtLevelHighchartBuildingFunction();" class="active impactLevelCls collapseHIghChartViewCls districtCollapseHIghChartViewCls"><a href="#districtOvervwGraph" aria-controls="districtOvervwGraph" role="tab" data-toggle="tab"><i class="fa fa-line-chart"></i></a></li>
															<li role="presentation" onClick="districtLevelTblBuildingFunction();" class="impactLevelCls collapseTblViewCls districtCollapseTblViewCls"><a href="#districtOvervwTable" aria-controls="districtOvervwTable" role="tab" data-toggle="tab">
																<i class="fa fa-table"></i>
															</a></li>
														  </ul>
													</div>
														<div class="col-md-12 col-xs-12 col-sm-12">
														  <!-- Tab panes -->
														  <div class="tab-content">
														 <div role="tabpanel" class="tab-pane active collapseHIghChartViewCls districtCollapseHIghChartViewCls" id="districtOvervwGraph">
																<div id="districtImpactLevelHighChartDivId" style="height:650px;"></div>
														</div>
														<div role="tabpanel" class="tab-pane collapseTblViewCls districtCollapseTblViewCls" id="districtOvervwTable">
															<div id="districtImpactLevelTblDivId"></div>
														</div>
														  </div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-12 col-xs-12 col-sm-12 gmcImpactLevelBlockCls">
										   <div class="panel panel-default panelNew">
											<div class="panel-heading">
												<h4 class="panel-title"><span class="headingColor" id="corpOverviewHeadingId">CORP-GMC overview - impact alerts</span></h4>
											</div>
											<div class="panel-body">
												<div class="row">
													<div class="col-md-12 col-xs-12 col-sm-12">
														  <!-- Nav tabs -->
														  <ul class="nav nav-tabs navTabsAlerts pull-right" role="tablist">
															<li role="presentation" onClick="gmcLevelHighchartBuildingFunction();" class="active impactLevelCls collapseHIghChartViewCls"><a href="#corpGmcOvervwGraph" aria-controls="corpGmcOvervwGraph" role="tab" data-toggle="tab"><i class="fa fa-line-chart"></i></a></li>
															<li role="presentation" onClick="gmcLevelTblBuildingFunction();" class="impactLevelCls collapseTblViewCls"><a href="#corpGmcOvervwTable" aria-controls="corpGmcOvervwTable" role="tab" data-toggle="tab">
																<i class="fa fa-table"></i>
															</a></li>
														  </ul>
													</div>
														<div class="col-md-12 col-xs-12 col-sm-12">
														  <!-- Tab panes -->
														  <div class="tab-content">
														 <div role="tabpanel" class="tab-pane active collapseHIghChartViewCls" id="corpGmcOvervwGraph">
															<div id="gmcImpactLevelHighChartDivId" style="height:250px;"></div>
														</div>
														<div role="tabpanel" class="tab-pane collapseTblViewCls" id="corpGmcOvervwTable">
															 <div id="gmcImpactLevelTblDivId"></div>
														</div>
														  </div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-12 col-xs-12 col-sm-12 constituencyImpactLevelBlockCls">
										   <div class="panel panel-default panelNew">
											<div class="panel-heading">
												<h4 class="panel-title"><span class="headingColor" id="constituencyOverviewHeadingId">Constituency overview - impact alerts</span></h4>
											</div>
											<div class="panel-body">
												<div class="row">
													<div class="col-md-8 col-xs-12 col-sm-8">
														<ul class="list-inline activeUlCls  constituencyUl">
																<li class="descendingConstituencyCls active" onClick="getSortedConstituencyInRequiredFormat('Decending')">
																	<i class="glyphicon glyphicon-sort-by-attributes" ></i>
																</li >
																<li class="ascendingConstituencyCls" onClick="getSortedConstituencyInRequiredFormat('Ascending')">
																	<i class="glyphicon glyphicon-sort-by-attributes-alt" style="transform:rotate(180deg)"></i>
																</li>
																<li class="atozConstituecySortingCls" onClick="getSortedConstituencyInRequiredFormat('AlphabeticalAscending')">
																	A-Z
																</li>
																<li class="ztozConstituecySortingCls" onClick="getSortedConstituencyInRequiredFormat('AlphabeticalDescending')">
																	Z-A
																</li>
																<li class="ascendingSortingByConstituencyIdCls" onClick="getSortedConstituencyInRequiredFormat('locationIdAscendingOrder')">
																	constituency id&nbsp;&nbsp;<i class="fa fa-long-arrow-up"></i>
																</li>
																<li class="ascendingSortingByConstituencyIdCls" onClick="getSortedConstituencyInRequiredFormat('locationIdDescendingOrder')">
																	constituency id&nbsp;&nbsp;<i class="fa fa-long-arrow-down"></i>
																</li>
																<li>
																<select class="form-control" id="constituencySeletBoxId" onChange="getSortedConstituencyInRequiredFormat('Search')">
																    <option value="0">Select Constitency </option>
																</select>
															    </li>
															</ul>
													</div>
													<div class="col-md-4 col-xs-12 col-sm-4">
														  <!-- Nav tabs -->
														  <ul class="nav nav-tabs navTabsAlerts pull-right" role="tablist">
															<li role="presentation" onClick="constituencyLevelHighchartBuildingFunction();" class="active impactLevelCls collapseHIghChartViewCls constituencyHighChartViewCls"><a href="#constituencyOvervwGraph" aria-controls="constituencyOvervwGraph" role="tab" data-toggle="tab"><i class="fa fa-line-chart"></i></a></li>
															<li role="presentation" onClick="constituencyTblBuildingFunction();" class="impactLevelCls collapseTblViewCls constituencyCollapseTblViewCls"><a href="#constituencyOvervwTable" aria-controls="constituencyOvervwTable" role="tab" data-toggle="tab">
																<i class="fa fa-table"></i>
															</a></li>
														  </ul>
													</div>
														<div class="col-md-12 col-xs-12 col-sm-12">
														  <!-- Tab panes -->
													<div class="tab-content">
														 <div role="tabpanel" class="tab-pane active collapseHIghChartViewCls constituencyHighChartViewCls" id="constituencyOvervwGraph">
															<div id="constituencyLevelHighChartDivId" style="height:450px;"></div>
														</div>
														<div role="tabpanel" class="tab-pane collapseTblViewCls constituencyCollapseTblViewCls" id="constituencyOvervwTable">
															 <div id="constituencyLevelTblDivId"></div>
														</div>
													</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									<div class="row m_top20">
										<div class="col-md-12 col-xs-12 col-sm-12">
											<h4 class="panel-title groupAssignCls"><span class="headingColor text-capital">Alert Assigned Group Members - Status</span></h4>
											<div id="groupAssignAlertDlsDivId" class="row m_top20"></div>     
										</div>
									</div>
								</div>
								</div>
							</div>
						</div> 
						<div class="col-md-12 col-xs-12 col-sm-12  alertComparisonblock" expand-block-right="alerts" style="display:none;">
								<div class="panel panel-default panelNew">
									<div class="panel-heading">
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-12">
											 <div id="childUserTypeDetailsDivForAlerts"></div>
											</div>
										</div>
									</div>
									<div class="panel-body alertComBlockCls">
										<div class="row">
											<div class="col-md-12 col-xs-12 col-sm-12">
											 <div id="alertChildActivityMemberDivId"> </div>
											</div>
											<div class="col-md-12 col-xs-12 col-sm-12">
												<div class="bg_ED pad_15 m_top20"> 
												 <div id="userTypeWiseChildDtlsTabForAlertId"></div>
													<div class="row impactLevelWiseComparisonBlockCls">
													<div id="candidateLocationAlertDtlsStatusWiseDivId" class="m_top20"></div>
														
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
	 <!--Alerts End -->
	 <!-- DEBATES PROGRAM BLOCK -->
			<div class="col-md-6 col-xs-12 col-sm-12 debatesBlock" expand-block="debates">
				<div class="panel panel-default panelNewCustom panel2">
					<div class="panel-heading">
						<div class="row">
							<div class="col-md-9 col-sm-9 col-xs-12" expand-block-heading="debates">
								<h4 class="panel-title text-capital">
									<img src="newCoreDashBoard/img/debates.png" class="iconClass"/>
									debates - <small class="text-muted debatesDate">today</small>
									<!--<label class="radio-inline">
								<input  name="stateSelection" value="0" checked type="radio" class="radioStateCls"/>All
							   </label>
							  <label class="radio-inline">
								<input  name="stateSelection" value="1" type="radio" class="radioStateCls"/>AP
							  </label>
							  <label class="radio-inline">
								<input name="stateSelection" value="36" type="radio" class="radioStateCls"/>TS
							 </label>
							 <label class="radio-inline">
								<input  name="stateSelection" value="2" type="radio" class="radioStateCls"/>Others
							 </label>-->
								</h4>
								<div class="col-md-3 col-sm-3 col-xs-12">
								
							</div>
							</div>
							
							<div class="col-md-3 col-sm-3 col-xs-12" expand-block-heading1="debates">
								<span class="debatesSettingsIcon pull-right">
								   <i class="fa fa-gears"  data-toggle="tooltip" data-placement="top" title="Settings"></i>
								 </span>
								<span class="notesIconDebates pull-right">
									<i class="glyphicon glyphicon-list-alt"  data-toggle="tooltip" data-placement="top" title="Notes" onClick="displayDashboardCommentsForDebates(3);"></i>
								</span>
								<span class="debatesIconExpand pull-right" expand-icon="debates">
									<i class="glyphicon glyphicon-fullscreen"></i>
								</span>
								<span class="debatesRefresh pull-right">
								   <i class="glyphicon glyphicon-refresh"  data-toggle="tooltip" data-placement="top" title="Settings"></i>
								</span>
								<span class="input-group pull-right dateRangePickerClsForDebates hide" expand-block-date="debates" style="width:200px;">
									<input type="text" id="dateRangeIdForDebates" style="width:180px" class="form-control" />
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-calendar"></i>
									</span>
								</span>
								<div class="debatesSettingsBody" style="display:none;top:15px;">
									<div class="row">
										<div class="col-sm-12">
										 <i class="glyphicon glyphicon-remove debatesSettingsCloseBody" style="cursor: pointer; position: absolute; right: 6px; z-index: 99; top: -9px;"></i>
											<h4 class="panel-title text-center">Debate Location</h4>
										</div>
										<div class="col-sm-6">
											<!-- Nav tabs -->
											<ul class="nav nav-tabs tabViewSelectionDebates" role="tablist">
												<li class="active" role="presentation"><a href="#debatesTabViewDebate" aria-controls="debatesTabViewDebate" role="tab" data-toggle="tab">Debate</a></li>
												<li role="presentation"><a href="#debatesTabViewParticipant" aria-controls="debatesTabViewParticipant" role="tab" data-toggle="tab">Debate Participant</a></li>
											</ul>
										</div>
										<!-- Tab panes -->
										<div class="col-sm-6">
											<div class="tab-content">
												<div role="tabpanel" class="tab-pane active" id="debatesTabViewDebate">
													<ul class="debatesViewList">
														<li>
															<label class="checkbox-inline">
																<input type="checkbox" class="radioStateCls" name="debatesRadio"  value="1" checked />AP
															</label>
														</li>
														<li>
															<label class="checkbox-inline">
																<input type="checkbox"  class="radioStateCls" name="debatesRadio"  value="36" checked />TS
															</label>
														</li>
														<li>
															<label class="checkbox-inline">
																<input type="checkbox"  class="radioStateCls" name="debatesRadio" value="2" checked />Others
															</label>
														</li>
													</ul>
												</div>
												<div role="tabpanel" class="tab-pane" id="debatesTabViewParticipant">
													<ul class="debatesViewList">
														<li>
															<label class="checkbox-inline">
																<input type="checkbox"  class="radioStateCls1" name="debatesParticipantRadio" value="1" checked />AP
															</label>
														</li>
														<li>
															<label class="checkbox-inline">
																<input type="checkbox"  class="radioStateCls1" name="debatesParticipantRadio"  value="36" checked />TS
															</label>
														</li>
														<!--<li>
															<label class="checkbox-inline">
																<input type="checkbox" name="debatesParticipantRadio" checked/>AP
															</label>
														</li>-->
													</ul>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					   <div class="notesDropDown notesArrow">
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
							<div class="col-md-12 col-xs-12 col-sm-12 debatesBlock" expand-block-inner="debates">
								
								
								<div class="row">
									<div id="partyWiseTotalDebateDetails"></div>
								</div>
							</div>
							<div class="col-md-6 col-xs-12 col-sm-12 debatesHiddenBlock" expand-block-right="debates">
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
						<div class="row debatesMoreHiddenBlock" expand-block-more="debates">
							<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
								<div class="panel-group" id="debatesCollapse" role="tablist" aria-multiselectable="true">
									<div class="panel panel-default panelNew">
										<div class="panel-heading" role="tab" id="collapseOneId" style="cursor:pointer">
											
												<h4><span class="headingColor text-capitalize responsiveWidth" style="display:block;width:330px;">Character based performance cohort</span><span class="pull-right arrowChange"><i class="glyphicon glyphicon-minus"></i></span></h4>
											
										</div>
									
											<div class="panel-body collapse in" id="collapseOneBodyId">
												<div class="row">
													<div id="scaleBasedPerformanceCohort"></div>
												</div>
											</div>
									
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
						</div>
					</div>
				</div>
			</div>
			<!-- DEBATES PROGRAM BLOCK END-->
		</div>
		<div class="row">
	 		<!--  NEWS BLOCK START-->
			<div class="col-md-6 col-xs-12 col-sm-12 newsBlock" expand-block="news">
				<div class="panel panel-default panelNewCustom panel2">
					<div class="panel-heading">
						<div class="row">
							<div class="col-md-9 col-sm-9 col-xs-12" expand-block-heading="news">
								<h4 class="panel-title text-capital" style="width:440px;">    
									<img src="newCoreDashBoard/img/news.png" class="iconClass"/>
									Print Media News - <small class="text-muted" id="currentViewing"></small>  
								</h4>  
								
							</div>
							<div class="col-md-3 col-sm-3 col-xs-12 m_XsTop10" expand-block-heading1="news">
								<span class="settingsIcon settingsIconNews pull-right">
									<i class="fa fa-gears"  data-toggle="tooltip" data-placement="top" title="Settings"></i>
								</span>
								<span class="notesIconNews pull-right">
									<i class="glyphicon glyphicon-list-alt"  data-toggle="tooltip" data-placement="top" title="Notes" onClick="displayDashboardCommentsForNews(5);"></i>
								</span>   
								<span class="newsIconExpand pull-right" expand-icon="news">
									<i class="glyphicon glyphicon-fullscreen"></i>
								</span>
								<span class="newsRefresh pull-right">
									<i class="glyphicon glyphicon-refresh"  data-toggle="tooltip" data-placement="top" title="Settings"></i>
								</span>
								<span class="input-group pull-right dateRangePickerClsForNews hide" expand-block-date="news" style="width:200px;">
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
										<h4 class="text-capital pad_5" style="color:#99A0A5;"> Select Publication </h4>
										<hr style ="margin-bottom:0px;" />
										<div class="">
											<ul class="settingsUl" id="newsPapersUlId"></ul>
										</div>
									</div>
									<div role="tabpanel" class="tab-pane" id="impactScope">
										<h4 class="text-capital pad_5" style="color:#99A0A5;"> Select Impact </h4>
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
							<div class="col-md-12 col-xs-12 col-sm-12 newsBlock" expand-block-inner="news">
								<div id="newsBlockMainId"></div>
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
							<div class="col-md-6 col-xs-12 col-sm-12 newsHiddenBlock" expand-block-right="news">
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
								<i data-placement="top" data-toggle="tooltip" expand-block-right="news" class="glyphicon glyphicon-option-horizontal pull-right morenewsBlocksIcon" title="Click here for more"></i>
							</div>	
							<div class="col-md-12 col-xs-12 col-sm-12 newsHiddenMoreBlock" expand-block-more="news">
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
												<div class="panel-body verticalScrollBarPM">
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
			<!-- Electronic Media Start -->
		<div class="col-md-6 col-xs-12 col-sm-12 electronicMediaBlock" expand-block="electronic">
			<div class="panel panel-default panelNewCustom">
				<div class="panel-heading">
					<div class="row">
						<div class="col-md-9 col-sm-9 col-xs-12" expand-block-heading="electronic">
							<h4 class="panel-title text-capital" style="width: 470px;">
								<img src="newCoreDashBoard/img/electronicMediaIcon.png" class="iconClass">
								electronic media news-<small id="emnHeadDate" class="text-muted">TODAY (28-10-2016)</small>
							</h4>    
						</div>
						<div class="col-md-3 col-sm-3 col-xs-12 m_XsTop10" expand-block-heading1="electronic">
							<span class="emnSetIcon pull-right">
								<i class="fa fa-gears" data-toggle="tooltip" data-placement="top" title="" data-original-title="Settings"></i>
							</span>
							<span class="notesIconEmn pull-right">
								<i class="glyphicon glyphicon-list-alt" data-toggle="tooltip" data-placement="top" title="" onclick="displayDashboardCommentsForAttendance(7);" data-original-title="Notes"></i>
							</span>
							<span class="emnIconExpand pull-right" expand-icon="electronic">
								<i class="glyphicon glyphicon-fullscreen"></i>
							</span>
							<span class="emnRefresh pull-right">
								<i class="glyphicon glyphicon-refresh" data-toggle="tooltip" data-placement="top" title="" data-original-title="Settings"></i>
							</span>
							<span class="input-group pull-right dateRangePickerClsForEmn hide" expand-block-date="electronic" style="width:200px;">
								<input id="dateRangeIdForEmn" style="width:180px" class="form-control" type="text">
								<span class="input-group-addon">
									<i class="glyphicon glyphicon-calendar"></i>
								</span>
							</span>
							
						</div>
					</div>
					<div class="emnBlockDropDown documentCloseClass" style="z-index:999;margin-top: -3px;width:450px;" >
						<i class="glyphicon glyphicon-remove emnSetClose pull-right"></i>
						<div class="row">
							<div class="col-md-4 col-xs-12 col-sm-6 pad_right0 m_top20">
								<ul class="nav nav-tabs navTabsSettings" role="tablist">
									<li role="presentation" class="active text-capital"><a href="#EditionsEmn" aria-controls="EditionsEmn" role="tab" data-toggle="tab">News Channels</a></li>
									<li role="presentation" class="text-capital"><a href="#impactScopeEmn" aria-controls="impactScopeEmn" role="tab" data-toggle="tab">Impact Scope</a></li>
								</ul>
							</div>
							<div class="col-md-8 col-xs-12 col-sm-6 pad_left0 pad_right4">
								<div class="tab-content navTabsSettingsContent">
									<div role="tabpanel" class="tab-pane active" id="EditionsEmn">
										<h4 class="text-capital pad_5" style="color:#99A0A5;">Select Publication</h4>
										<hr style ="margin-bottom:0px;" />
										<div class="">
											<ul class="settingsUlEmn" id="emnNewsChannelsUlId"></ul>
										</div>
									</div>
									<div role="tabpanel" class="tab-pane" id="impactScopeEmn">
										<h4 class="text-capital pad_5" style="color:#99A0A5;">Select Impact</h4>
										<hr style ="margin-bottom:0px;" />
										<div class="">
											<ul class="settingsUlEmn">
												<li>
													<label class="checkbox-inline">
														<input type="checkbox" value="0" id="impactSelectAllIdEmn" checked>
														<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">Select All</h5></div>
													</label>
												</li>
												<li>
													<label class="checkbox-inline">
														<input type="checkbox" value="1" class="impactCheckClsEmn" checked>
														<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">State</h5></div>
													</label>
												</li>
												<li>												
													<label class="checkbox-inline">
														<input type="checkbox" value="2" class="impactCheckClsEmn" checked>
														<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">District</h5></div>
													</label>	
												</li>	
												<li>
													<label class="checkbox-inline">
														<input type="checkbox" value="3" class="impactCheckClsEmn" checked>
														<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">Constituency</h5></div>
													</label>
												</li>
												<li>
													<label class="checkbox-inline">
													<input type="checkbox" value="4" class="impactCheckClsEmn" checked>
														<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">Parliament</h5></div>
													</label>
												</li>	
												<li>
													<label class="checkbox-inline">
														<input type="checkbox" value="5" class="impactCheckClsEmn" checked>
														<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">Mandal</h5></div>
													</label>
												</li>
												<li>
													<label class="checkbox-inline">
														<input type="checkbox" value="6" class="impactCheckClsEmn" checked>
														<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">Panchayat</h5></div>
													</label>
												</li>
												<li>
													<label class="checkbox-inline">
														<input type="checkbox" value="8" class="impactCheckClsEmn" checked>
														<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">Municipality/Corporation</h5></div>
													</label>	
												</li>	
											</ul>
										</div>
									</div>
								</div>
							  
							</div>
							<div class="col-md-8 col-md-offset-4 col-xs-12 col-sm-9 col-sm-offset-3">
								<button type="button" class="btn btn-success filtersSubmitDivIdEmn">Get Details</button>
							</div>
						</div>
					</div>
                </div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-12 col-xs-12 col-sm-12">
							<h6 id="lastUpdatedTimeElecId" style="top:-8px;position:relative;right:5px;float:right;font-weight:bold"></h6>
						</div>
						<div class="col-md-12 col-xs-12 col-sm-12 electronicMediaBlock" expand-block-inner="electronic">
							<div class="row">
								<div class="col-md-12 col-xs-12 col-sm-12">
									<div id="electronicMediaChannelCountId"></div>
								</div>
							</div>
						</div>
						<div class="col-md-6 col-xs-12 col-sm-12 moreBlockEMN" expand-block-right="electronic" style="display:none;">
							<div class="row">
								<div class="col-md-7 col-xs-12 col-sm-7">
									<h4 class="panel-title">
										<span class="headingColor text-capital">media programs on party</span>
									</h4>
								</div>
								<div class="col-md-5 col-xs-12 col-sm-5">
									<ul class="activeUlCls list-inline emnMediaPrograms pull-right">
										<li attr_searchType="top" class="active">Top</li>
										<li attr_searchType="poor">Poor</li>
									</ul>
								</div>
								<!-- <div class="col-md-12 col-xs-12 col-sm-12 m_top10">
									<div class="pull-right">
										<label class="radio-inline"><input class="emnSearchTypeCls" type="radio" name="emnSearchTypeName" value="program" checked />Program Wise</label>
										<label class="radio-inline"><input class="emnSearchTypeCls" type="radio" name="emnSearchTypeName" value="time"/>Time Wise</label>
									</div>
								</div> -->
								<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
									<div id="electronicMediaPrograms"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 moreBlockEMN" expand-block-right="electronic">
							<i data-placement="top" data-toggle="tooltip" class="glyphicon glyphicon-option-horizontal pull-right moreEmnBlocksIcon showHideEmn" title="Click here for more" style="display: inline;"></i>
						</div>
						<div class="col-md-12 col-xs-12 col-sm-12 selectEmnCate" expand-block-more="electronic">
							<ul class="newsComparisonUl list-inline">
								<li class="text-capital newsComparisonHeading">Comparison</li>
								<li class="text-capital a viewsLiClassEmn" id="comparisonPartyLiIdEmn" attr_div_id="comparisonPartyMainDivId">Party</li>
								<li class="text-capital a viewsLiClassEmn" id="comparisonGovernmentLiIdEmn" attr_div_id="comparisonGovernamentMainDivId">Government</li>
							</ul>
							
							<ul class="newsComparisonUl list-inline">
								<li class="text-capital newsComparisonHeading">Detailed</li>
								<li class="text-capital a viewsLiClassEmn" id="detailedPartyLiIdEmn" attr_div_id="detailedPartyMainDivId">Party</li>
								<li class="text-capital a viewsLiClassEmn" id="detailedGovernmentLiIdEmn" attr_div_id="detailedGovernamentMainDivId">Government</li>
							</ul>
						</div>
					</div>
					<div class="row m_top20 detailedPartyEmn newEmnHideCls"  expand-block-more="electronic">
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div class="panel panel-default panelNew">
								<div class="panel-heading">
									<h4><span class="headingColor">State Wise - Programs Overview</span></h4>
								</div>
								<div class="panel-body">
									<div id="stateWiseProgramsOvrViewEMN"></div>
								</div>
							</div>
						</div>
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div class="panel panel-default panelNew">
								<div class="panel-heading">
									<h4><span class="headingColor">District Wise - Programs Overview</span></h4>
								</div>
								<div class="panel-body">
									<div id="districtWiseProgramsOvrViewEMN"></div>
								</div>
							</div>
						</div>
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div class="panel panel-default panelNew">
								<div class="panel-heading">
									<div class="row">
										<div class="col-md-6 col-xs-12 col-sm-6">
											<h4><span class="headingColor text-capitalize">Parties Vs Channels</span></h4>
										</div>
										<div class="col-md-6 col-xs-12 col-sm-6">
											<ul class="list-inline activeUlCls distWiseProgramOvrView pull-right">
												<li class="active" attr_type="party">Parties District Wise</li>
												<li attr_type="channel">Tv Channels Wise</li>
											</ul>
										</div>
									</div>
								</div>
								<div class="panel-body">
									<div id="partiesVsChannelsEMN"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="row m_top20 detailedGovtEmn newEmnHideCls" expand-block-more="electronic">
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div class="panel panel-default panelNew">
								<div class="panel-heading">
									<h4><span class="headingColor">State Wise - Programs Overview</span></h4>
								</div>
								<div class="panel-body">
									<div id="stateWiseProgramsOvrViewGovtEMN"></div>
								</div>
							</div>
						</div>
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div class="panel panel-default panelNew">
								<div class="panel-heading">
									<h4><span class="headingColor">Problems Detailed Overview</span></h4>
								</div>
								<div class="panel-body">
									<div id="problemsDetailedOvrViewEMN"></div>
									<div id="stateWiseEMNRelatedToProblem" class="m_top20"></div>
									<div id="emmDetailedGovtDistDetailedOverview"></div>
									<div id="districtWiseEMNRelatedToProblem"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="row m_top20 compPartyEmn newEmnHideCls" expand-block-more="electronic">
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div class="panel panel-default panelNew">
								<div class="panel-body">
									<div class="row">
										<div class="col-md-12 col-xs-12 col-sm-12">
											<div id="partyRankWiseDetailsOfChannel"></div>
											<div id="partyRankWiseDetailsOfChannelSub" class="m_top20"></div>
											<div id="partyRankWiseCompDistWiseOvervw"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row m_top20 compGovtEmn newEmnHideCls" expand-block-more="electronic" >
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div class="panel panel-default panelNew">
								<div class="panel-body">
									<div class="row">
										<div class="col-md-12 col-xs-12 col-sm-12">
											<div id="govtRankWiseDetailsOfChannel"></div>
											<div id="govtRankWiseDetailsOfChannelSub" class="m_top20"></div>
											<div id="emmCompGovtDistDetailedOverview"></div>
											<div id="districtWiseEMNRelatedToProblemGovt"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	<!-- Electronic Media End -->
	</div>
	<div class="row">
		
	<!-- TOURS START -->
	        <!-- tour New Jsp Code -->
					<div class="col-md-6 col-xs-12 col-sm-12 NewToursBlock" expand-block="tours">
						<div class="panel panel-default panelNewCustom">
							<div class="panel-heading">
								<div class="row">
									<div class="col-md-9 col-sm-9 col-xs-12" expand-block-heading="tours">
										<h4 class="panel-title text-capital">
											<img src="newCoreDashBoard/img/Alert_icon.png" class="iconClass"/>
											TOURS <small class="text-muted"><span id="toursNewHeadingId">  </span></small>
										</h4>
									</div>
									<div class="col-md-3 col-sm-3 col-xs-12" expand-block-heading1="tours">
										<span class="NewTourExpand pull-right" expand-icon="tours">
											<i class="glyphicon glyphicon-fullscreen" style="cursor:pointer;"></i>
										</span>
										<span class="input-group pull-right hideShowNewToursDateRangeCls hide" expand-block-date="tours" style="width:200px;">
												<input type="text" id="tourNewDateRangePickerId" style="width:180px" class="form-control" />
												<span class="input-group-addon">
													<i class="glyphicon glyphicon-calendar"></i>
												</span>
											</span>
										<span class="toursRefresh pull-right">
											<i class="glyphicon glyphicon-refresh" data-toggle="tooltip" data-placement="top" title="" data-original-title="Settings"></i>
										</span>
									</div>     
								</div>
							</div>
							<div class="panel-body">
							<div class="row">
								<div class="col-md-12 col-xs-12 col-sm-12 NewToursBlock" expand-block-inner="tours">
									<div class="row">
										<div id="tourOverviewNewDivId"></div>
									</div>
								</div>
								<div class="col-md-6 col-xs-12 col-sm-12 NewTourExpandCls m_top10"  expand-block-right="tours" style="display:none;" >
									<div class="row">
										 <h4><span class="headingColor text-capital">TOUR COMPLAINCE</span></h4>
											<div class="col-md-6 col-md-offset-6 col-xs-12 col-sm-6 col-sm-offset-6">
													<ul class="activeUlCls tourNewComplainceFilterCls list-inline hideCls">
														<li class="tourComplainceCls active" attr_value="strong"><i class="fa fa-arrow-up"></i>&nbsp;top 5</li>
														<li class="tourComplainceCls " attr_value="poor"><i class="fa fa-arrow-down"></i>&nbsp;last 5</li>
													</ul>
											</div> 
										 <div id="buildgDesignationWiseToursTopFiveComplainceDivId"></div>
									</div>
									
								</div>
								<div class="col-xs-12 col-sm-12 col-md-12 NewToursHiddenBlock"  expand-block-right="tours" style="display: none;">
									<i data-placement="top" data-toggle="tooltip" class="glyphicon glyphicon-option-horizontal pull-right moreNewToursBlocksIcon" title="Click here for more"></i>
								</div>	
								<!--<div class="col-md-12 col-xs-12 col-sm-10 col-sm-offset-1 col-md-offset-0 moreNewToursBlocks" style="display:none;">
									<ul class="list-inline pull-right activeUlCls">
										<li class="active toursNewDetailedBlock">Detailed</li>
									</ul>
								</div>!-->
								<div class="col-md-12 col-xs-12 col-sm-12 moreNewToursBlocksDetailed m_top10"  expand-block-more="tours" style="display:none;">
									<h4 class="panel-title"><span class="headingColor text-capital"> Average Tour Performance - leaders</h4><br><br>	
									<div id="toursPerformanceDivId"></div>
								</div>
										
							</div>
						</div>
						</div>
				 </div>
			<!-- TOURS END -->  
		
			<!--Meetings Start -->
			<div class="col-md-6 col-xs-12 col-sm-12 meetingsBlock" expand-block="meetings">  
				<div class="panel panel-default panelNewCustom">
					<div class="panel-heading">
						<div class="row">
							<div class="col-md-9 col-sm-9 col-xs-12" expand-block-heading="meetings">
								<h4 class="panel-title text-capital">
									<img src="newCoreDashBoard/img/meetings.png" class="iconClass"/>
									meetings - <small class="text-muted" id="dateMeetingHeadingId"> last month</small>
								</h4>
							</div>
							<div class="col-md-3 col-sm-3 col-xs-12" expand-block-heading1="meetings">
								<span class="notesIconMeeting pull-right">
									<i class="glyphicon glyphicon-list-alt"  data-toggle="tooltip" data-placement="top" title="Notes" onClick="displayDashboardCommentsForMeetings(2);"></i>
								</span>
								<span class="meetingsIconExpand pull-right" expand-icon="meetings">
									<i class="glyphicon glyphicon-fullscreen"></i>
								</span>
								<span class="meetingsRefresh pull-right" attr_meeting_type="committeeMeeting" attr_refresh_status="false">
									<i class="glyphicon glyphicon-refresh" data-toggle="tooltip" data-placement="top" title="" data-original-title="Settings"></i>
								</span>
								<span class="input-group pull-right dateRangePickerClsForMeetings hide" expand-block-date="meetings" style="width:200px;">
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
							<div class="col-md-12 col-xs-12 col-sm-12" expand-block-inner="meetings">
								<div class="row">
									<div class="col-sm-6 col-xs-12 col-md-6">
										<h4 class="display:inline-block">
											<span class="headingColor text-capital">committee meetings</span>
										</h4>
									</div>
									<div class="col-sm-6 col-xs-12 col-md-6">
										<div style="position:relative;">
											<span class="committeeMeetingsSettings" style="background-color:#fff;margin-left:5px;"><i class="fa fa-gears"></i></span>
											<span class="mainMeetingsIcon" style="background-color:#fff;margin-left:5px;" expand-icon-inner="meetings" expand_event_name="mainMeetings">
												<i class="glyphicon glyphicon-fullscreen"></i>
											</span>
											<span style="color:red;font-size:15px;" id="committeeMeetingErrorId"></span>
											<div class="settingsDropDown" id="committeeTypeDivId">
												
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div id="meetingBasicCountDivId"></div>
								</div>
								
								<div class="panelBlock m_top20" >
									<h4>
										<span class="headingColor text-capitalize">state level meetings</span>
										<span class="stateLevelMeetingSeeting" style="background-color:#fff;margin-left:5px;"><i class="fa fa-gears"></i></span>
										<!--<span class="stateLevelMeetingsExpand" id="stateLevelMeetingsExpandId" attr_main_type_meeting_id="2" style="background-color:#fff;margin-left:5px;"><i class="glyphicon glyphicon-fullscreen"></i></span>-->
										<span style="color:red;font-size:15px;" id="stateLevelMeetingErrorId"></span>
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
								<div id="customMeetingsDiv"></div>
								
								<div class="panelBlock m_top20">
									<div class="row">
										<div class="col-md-7 col-xs-12 col-sm-7" style="padding-right:0px;">
											<h4>
												<span class="headingColor text-capitalize">special meetings</span>
												<span class="specialMeetingSeeting" style="background-color:#fff;margin-left:5px;"><i class="fa fa-gears"></i></span>
												<!--<span style="background-color:#fff;margin-left:5px;" attr_main_type_meeting_id="3" class="specialMeetings" id="specialMeetingsExpandId"><i class="glyphicon glyphicon-fullscreen"></i></span><span style="color:red;font-size:15px;" id="specialMeetingErrorId"></span>-->
												
												<span style="background-color:#fff;margin-left:5px;"  class="refreshButtonUcon" id="" onclick="refreshMeeting();" title="Click here to refresh"><i class="glyphicon glyphicon-refresh"></i></span><span style="color:red;font-size:15px;" id=""></span>
											</h4>
										</div>
										<div class="col-md-5 col-xs-12 col-sm-5" style="padding-right:0px;">
											<span class="pull-right specialMeetingsCollapseBtn" style="cursor: pointer;padding: 0px 5px;font-size: 15px;border: 1px solid #333;">+</span>
										</div>
										<div class="specialMeetingDropDown " style="left:0px;top:30px;">
											<ul class="list-inline">
												<li><label><input type="checkbox" class="selectAllSpecialMeeting"/>&nbsp&nbspSelect All</label></li>
											</ul>
											<div id="specialMeetingDivId"></div>
											<button type="button" onclick="specialMeetingBtncls()" attr_main_type_meeting_id="3"  class="btn btn-success specialMeetingBtncls">Get Details</button>
										</div>
									</div>
									<div class="row specialMeetingsCollapseBody" style="display:none">
										<div class="col-md-5 col-xs-12 col-sm-5 pull-right" style="cursor:pointer; margin-top: 15px; ">
											<button class="btn btn-default text-capital btn-xs specialMeetingBtnClsNew  defalutSpecialMeetingCls" attr_date="default" attr_startDate="" attr_endDate="">this month</button>
											<button class="btn btn-default btn-primary text-capital btn-xs specialMeetingBtnClsNew specialMeetingsDate lastMonthSpecialCls" attr_date="lastMonth" attr_startDate="" attr_endDate="" >last month</button>
										</div>
										<div id="specialMeetingBasicCnt" class="m_top10"></div>
									</div>
								</div>
								
								<!--<div class="row">
									<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
										<div class="panel-group" id="accordionMultiLocation" role="tablist" aria-multiselectable="true">
											<div class="panel panel-default panelNew">
												<div class="panel-heading pad_10" role="tab" id="headingMultiLocation">
													<h4 class="panel-title" style="display: inline-block">
														<span class="headingColor text-capital">multi Location meetings</span>
														<span style="background-color: #fff;" attr_levelId="0" attr_group_id="1"  attr_sessionId="0" class="multicLocationMeetingCls" expand-icon-inner="meetings" expand_event_name="multiLocation">
															<i class="glyphicon glyphicon-fullscreen"></i>
														</span>
														<span style="background-color:#fff;margin-left:5px;"  class="refreshButtonUcon" id="" onclick="getMultiLocationWiseMeetingGroupsData();" title="Click here to refresh"><i class="glyphicon glyphicon-refresh"></i></span><span style="color:red;font-size:15px;" id=""></span>
														<!--<button class="btn btn-xs btn-mini btn-success getModalImagesCls" attr_Meeting_level_id="3" attr_Meeting_id="513359"> view </button>
													</h4>  
													<a role="button" class="collapseDebatesIcon" data-toggle="collapse" data-parent="#accordionMultiLocation" href="#collapseMultiLocation" aria-expanded="true" aria-controls="collapseMultiLocation">
													</a>
												</div>
												<div id="collapseMultiLocation" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingMultiLocation">
												  <div class="panel-body b_top0 pad_5">
													<div id="MultiLocationWiseMeetingGroupsData"></div>  
												  </div>
												</div>
											</div>
										</div>
									</div>
								</div>-->
								
							</div>
							<div class="col-md-6 col-xs-12 col-sm-12" expand-block-right="meetings" style="display:none">
								<div class="row">
									<div class="col-md-6 col-md-offset-6 col-xs-12 col-sm-6 col-sm-offset-6">
										<ul class="activeUlCls list-inline meetingFilterCls">
											<li class="meetingLiCls active" attr_value="strong">top 5 strong</li>
											<li class="meetingLiCls" attr_value="poor">last 5 poor</li>
										</ul>
									</div>
									<div id="userTypeWiseTopFiveStrongAndPoorMeetingMemsDivId"></div>
									<div id="stateLevelMeetingBlockId"></div>
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-12 meetingsHiddenBlock" expand-block-right="meetings">
								<i data-placement="top" data-toggle="tooltip"  party_meetingId="0" class="glyphicon glyphicon-option-horizontal pull-right moreMeetingsBlocksIcon" title="Click here for more" expand-block-right="meetings"></i>
							</div>	
							<div class="col-md-12 col-xs-12 col-sm-10 col-sm-offset-1 col-md-offset-0 moreMeetingsBlocksList" expand-block-more="meetings" style="display:none;">
								<ul class="list-inline pull-right activeUlCls">
									<li class="active multiMetingDetailedBlock" attr_levelId="0" attr_group_id="1"  attr_sessionId="0" >Detailed</li>
									<li class="multiLocation">Comparison</li>
									<!--<li class="multiLocation">multiLocation</li>-->
								</ul>
							</div>
							
							<div class="col-md-12 col-xs-12 col-sm-10 col-sm-offset-1 col-md-offset-0 detailedMeetngsBlkId" style="display:none" expand-block-more="meetings" >
								<ul class="list-inline pull-right activeUlCls">
									<li class="active attendedMetngs" attr_levelId="0" attr_group_id="1"  attr_sessionId="0" >Attended</li>
									<li class="meetingBased" attr_levelId="0" attr_group_id="1"  attr_sessionId="0">Meetings Based</li>
									<!--<li class="multiLocation">multiLocation</li>-->
								</ul>
							</div>
							<div class="col-md-12 col-xs-12 col-sm-12 multiLocationMeetingsCLs" expand-block-more="meetings" style="display:none">
								<ul class="list-inline pull-right activeUlCls">
									<li class="active multiMetingDetailedBlock">Detailed</li>
									<li class="multiLocation">Comparison</li>
								</ul>
								
							</div>
							<div id="meetingLevelHIghChartsDivId" class="moreMeetingsBlocksDetailed"  expand-block-more="meetings"></div>
							<div class="col-md-12 col-xs-12 col-sm-12 m_top20 meetingPerformancCls moreMeetingsBlocksDetailed"  expand-block-more="meetings" style="display:none">
								<div class="panel panel-default panelNew">
									<div class="panel-heading">
										<div class="row">
											<div class="col-md-8 col-xs-12 col-sm-6">
												<span class="headingColor text-capitalize">meetings performance cohort</span>
											</div>
										</div>
									</div>
									<div class="panel-body">
										<div id="districtWiseSpecialMeetingsGraph"></div>
										<div id="partyMeetingOverviewTabDiv"></div>
										<div id="cmtMemberDtlsTableId"></div>
										<div id="childUserTypeDetailsDivIdForMeetingMultiLocation"></div>
										<div id="childActivityMemberDivIdForMeetingMultiLocation" class="m_top20"></div>
										<div id="directChildActivityMeetingMemberDivMultiLocation" class="m_top20"></div>
										<div id="topPoorLocationsMeetingDiv" class="m_top20"></div>
										<div id="userAccessLevelLocationDivId"></div>
										<div id="districtWisePartyMeetingTypeDivId"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<!--Meetings End-->
		</div>	
		
		<div class="row">
		
			<!-- Booth Committees Start-->
		<div class="col-md-6 col-xs-12 col-sm-12 boothCommitteesBlock" expand-block="boothCommittees">
        	<div class="panel panel-default panelNewCustom panel1">
            	<div class="panel-heading">
                	<div class="row">
						<div class="col-md-7 col-sm-7 col-xs-12">  
							<h4 class="panel-title text-capital">
								<img src="newCoreDashBoard/img/committees.png" class="iconClass"/>
								Booth committees - <small class="text-muted boothCommitteesDate">OVERALL (TILL NOW)</small>
							</h4>
						</div>
						<div class="col-md-5 col-sm-5 col-xs-12">
							<!--<span class="basicBoothCommitteesBlockDiv pull-right">
								<i class="fa fa-gears"  data-toggle="tooltip" data-placement="top" title="Settings"></i>
							</span>
							<span class="notesIcon pull-right">
								<i class="glyphicon glyphicon-list-alt"  data-toggle="tooltip" data-placement="top" title="Notes" onClick="displayDashboardComments(1);"></i>
							</span>-->
							<span class="iconExpand pull-right" expand-icon="boothCommittees">
								<i class="glyphicon glyphicon-fullscreen" data-toggle="tooltip" data-placement="top" title="Expand"></i>
							</span>
							<span class="cadreSettings pull-right refreshCadreCls" onClick="getBoothCommitteesBasicCountReport();"><i class="glyphicon glyphicon-refresh" data-toggle="tooltip" data-placement="top" title="" data-original-title="Refresh"></i></i></span> 
							<span style="display:none;" ><select id="tdpBoothCommitteeYearId" style="width: 98px;display:inline-block;padding:2px 6px;height:25px;margin-top: -3px;"></select></span>
							<span class="input-group pull-right dateRangePickerCls m_XsTop10 hide" expand-block-date="boothCommittees">
								<input type="text" id="dateRangeBoothId"	class="form-control" style="width:180px"/>
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
								<!--<li role="presentation"   class="text-capital"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Mandal/town/division level</a></li>
								<li role="presentation"  class="active text-capital"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">village/ward level</a></li>-->
							  </ul>
							</div>
							<div class="col-md-6 col-xs-12 col-sm-6 pad_left0 pad_right4">
							  <div class="tab-content navTabsSettingsContent">
								<div role="tabpanel" class="tab-pane" id="home">
									<h4 class="text-capital pad_5" style="color:#99A0A5;">Select Committees</h4>
									<hr style ="margin-bottom:0px;" />
									<div class="basicBoothCommitteeDetailsDiv">
									<ul class="settingsUl">
									<c:forEach items="${userDataVO.subList}" var="basicCommittee">
									   <c:if test="${basicCommittee.id == 1}">
										<li>
											<label class="checkbox-inline">
												<input type="checkbox"  class="districtBoothCommitteecheckBoxClass" value="${basicCommittee.id}" checked><!--checked-->
												<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">${basicCommittee.name}</h5></div>
										   </label>
										</li>	
									 </c:if>										
									</c:forEach>
									   <li>
											<label class="checkbox-inline">
											  <input type="checkbox"  id="checkAllBoothAffliatedDistrictlevelId">
											  <div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;font-weight:bold;">Affiliated committees</h5></div>
										   </label><!--checked-->
										</li> 
										<c:forEach items="${userDataVO.subList}" var="basicCommittee">
											<c:if test="${basicCommittee.id != 1}">
											<li>
											  <label class="checkbox-inline">
												 <input type="checkbox"  class="districtBoothCommitteecheckBoxClass districtBoothCommitteeAffliatedcheckBoxClass" value="${basicCommittee.id}"><!--checked-->
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
									<div class="basicBoothCommitteeDetailsDiv">
									<ul class="settingsUl">
									<c:forEach items="${userDataVO.subList}" var="basicCommittee">
									   <c:if test="${basicCommittee.id == 1}">
										 <li>
										   <label class="checkbox-inline">
											 <input type="checkbox"  class="checkedBasicComm mandalBoothCommitteecheckBoxClass" value="${basicCommittee.id}" checked><!--checked-->
											 <div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">${basicCommittee.name}</h5></div>
										   </label>
										 </li>
									   </c:if>
									</c:forEach>
										<li>
											<label class="checkbox-inline">
											  <input type="checkbox"  id="checkAllBoothAffliatedMandallevelId"  >
											  <div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;font-weight:bold;">Affiliated committees</h5></div>
										   </label>
										</li>  
									<c:forEach items="${userDataVO.subList}" var="basicCommittee">
											<c:if test="${basicCommittee.id != 1}">
											 <c:choose>
											   <c:when test="${basicCommittee.id == 10 || basicCommittee.id == 16}">
												 <li>
												  <label class="checkbox-inline">
													 <input type="checkbox"  class="mandalBoothCommitteecheckBoxClass mandalBoothCommitteeAffliatedcheckBoxClass" value="${basicCommittee.id}">
													 <div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">${basicCommittee.name}</h5></div>
												  </label>
												 </li> 
											   </c:when>
												<c:otherwise>
													<li>
													  <label class="checkbox-inline">
														 <input type="checkbox"  class="mandalBoothCommitteecheckBoxClass mandalBoothCommitteeAffliatedcheckBoxClass" value="${basicCommittee.id}" ><!--checked-->
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
								<div role="tabpanel" class="tab-pane active" id="boothMessages">
									<h4 class="text-capital pad_5" style="color:#99A0A5;">Select Committees</h4>
									<hr style ="margin-bottom:0px;" />
									<div class="basicBoothCommitteeDetailsDiv">
									<ul class="settingsUl">
									<c:forEach items="${userDataVO.subList}" var="basicCommittee">
										   <c:if test="${basicCommittee.id == 1}">
											 <li>
											   <label class="checkbox-inline">
												 <input type="checkbox"  class="villageBoothCommitteecheckBoxClass" value="${basicCommittee.id}" checked>
												 <div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">${basicCommittee.name}</h5></div>
											   </label>
											 </li>
										   </c:if>
									</c:forEach>
										 <li>
											<label class="checkbox-inline">
											  <input type="checkbox"  id="checkAllBoothAffliatedVillagelevelId"  >
											  <div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;font-weight:bold;">Affiliated committees</h5></div>
										   </label>
										</li>  
									
									<c:forEach items="${userDataVO.subList}" var="basicCommittee">
											<c:if test="${basicCommittee.id != 1}">
											<li>
											  <label class="checkbox-inline">
												 <input type="checkbox"  class="villageBoothCommitteecheckBoxClass villageBoothCommitteeAffliatedcheckBoxClass" value="${basicCommittee.id}">
												 <div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">${basicCommittee.name}</h5></div>
											   </label>
											</li>   
											</c:if>
									</c:forEach>
									</ul>
									</div>
								</div>
							  </div>
							  <button type="button" class="btn btn-success basicBoothCommittessDiv">Get Details</button>
							</div>
						
						</div>
						
						
					</div>
					<div class="notesDropDown notesArrow documentCloseClass">
                    	<h4 class="text-capital">notes
                        	<span class="pull-right">
                            	<i class="glyphicon glyphicon-list-alt"></i>
                            </span>
                        </h4>
						<div id="boothNotesId"></div>
                    	<!--<ul class="notesUl m_top20">
                        	<li>
                            	<!--<span class="notesText">notes on committees notes on committee notes on committee notes on committee notes on committee </span>- <span class="text-muted"><i>20-July 2016</i></span>
                            	<i class="glyphicon glyphicon-trash pull-right hoverBlock deleteNotes"></i>
                                <i class="glyphicon glyphicon-edit pull-right hoverBlock editNotes"></i>
                            </li>
                        </ul>-->
                        <hr/>  
                          <div id="id7" style="color:red;"></div>						
                        <label>Create Notes</label>
                        <textarea class="form-control notesArea"></textarea>
                        <button class="btn btn-default btnCustomCreate btn-sm" onClick="savingDashboardComment(1);">create</button>
                    </div>  
                </div>
                <div class="panel-body">  
				
              		<div class="row">
					<div class="col-xs-12 col-sm-4 col-md-2 pull-right showDatePicker" style="display:none;">
						
					</div>
					<div class="col-md-12 col-xs-12 col-sm-12 col-md-offset-0 basicCommitteesBlock" expand-block-inner="boothCommittees">
						<div id="boothBasicCommitteeCountsDiv"></div>
						<div id="boothCommitteesAssdDiv"></div>
					</div>
                        <div class="col-md-6 col-xs-12 col-sm-6 col-md-offset-0 userTypeCommitteesBlock committeesHiddenBlock" expand-block-right="boothCommittees">
                        	<div class="row">
                            	<div class="col-md-12 col-xs-12 col-sm-12">
                                	<ul class="boothCommitteeTopPoorLiCls activeUlCls list-inline pull-right">
                                    	<!--<li class="active topFiveStrongResults">Top 5 Strong</li>
										<li class="topFivePoorResults">Top 5 Poor</li>-->
										<li class="boothCommitteeTopPoorCls active addactive" attr_value="strong"><i class="fa fa-arrow-up"></i>&nbsp;top 5 strong</li>
										<li class="boothCommitteeTopPoorCls removeactive" attr_value="poor"><i class="fa fa-arrow-down"></i>&nbsp;last 5 poor</li>
                                    </ul>
                                </div>
                                <div id="boothUserTypeWiseCommitteesForTopFiveStrongAndPoorDiv"></div>
                               <!-- <div id="userTypeWiseCommitteesForTopFivePoorDiv" style ="display:none"></div>-->
						    </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-12" expand-block-right="boothCommittees">
                        	<i class="glyphicon glyphicon-option-horizontal pull-right moreBlocksIcons" expand-block-right="boothCommittees" data-toggle="tooltip" data-placement="top" title="Click here for more" style="cursor:pointer;display:none"></i>
                        </div>
                        <div class="col-md-12 col-xs-12 col-sm-12 col-md-offset-0 moreBoothBlocksDetailAndComp" style="display:none;" expand-block-more="boothCommittees">
                        	
							<ul class="list-inline pull-right activeUlCls">
                            	<li class="active detailedBlocks">Detailed</li>
                                <li class="comparisionBlocks">Comparison</li>
                            </ul>
					    </div>
                       <!-- <div class="col-md-12 col-xs-12 col-sm-12 col-md-offset-0 moreboothBlocks" expand-block-more="boothCommittees">
                        	<div class="panel panel-default m_top10">
                            	<div class="panel-body ">
									<div id="boothLevelWiseBasicCommittees"></div>
                                </div>
                            </div>
                        </div> -->
                       
                        <div class="col-md-12 col-xs-12 col-sm-12 col-md-offset-0 m_top20 moreBlocksDistrictlevels" style="display:none" expand-block-more="boothCommittees">
                        	<div class="panel panel-default panelNew">
                                <div class="panel-heading">
                                    <div class="row">
                                        <div class="col-md-8 col-xs-12 col-sm-12">
                                            <span class="headingColor text-capitalize">all committees performance cohort</span>
                                        </div>
                                        
                                    </div>
                                </div>
                                <div class="panel-body verticalScrollBar">
									<div id="boothDistrictWiseCommitteesReport" ></div>
								</div>
                            </div>
						</div>
                        <div class="col-md-12 col-xs-12 col-sm-12 m_top20 moreBoothBlocks1" style="display:none;"  expand-block-more="boothCommittees">
                            <!--<p><i>Selected:</i> <b>Main Committee</b></p>-->
                            <div class="panel panel-default panelNew">
                                <div class="panel-heading">
                                    <div class="row">
                                        <div class="col-xs-12 col-sm-12 col-md-12">
                                           <div id="boothChildUserTypeDetailsDiv"></div>
                                        </div>
                                       
                                    </div>
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12 col-xs-12 col-sm-12">
                                           <div id="boothSelectedUserTypeDetailsDiv"></div>
                                        </div>
                                        <div class="col-md-12 col-xs-12 col-sm-12" style="diaplay:none;">
                                            <div class="bg_ED pad_15 m_top20 showChildBlockAndTopPoorBlockForBoothC">
                                                <div id="boothDirectChildActivityMemberDiv" class="table-responsive"></div><!--sruj-->
                                                <div class="row m_top20">
                                                    <div class="col-md-8 col-xs-12 col-sm-12" style="border-right:1px solid #ddd;">
														<div id="boothTopPoorPerformanceDiv" style="display:none;"></div>
                                                    </div>
                                                    <div class="col-md-4 col-xs-12 col-sm-8 col-sm-offset-2 col-md-offset-0">
                                                        <div id="boothTopPoorLocationsDiv"></div>
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
		<!-- Booth Committees End-->
			<!-- Committees Start-->
		<div class="col-md-6 col-xs-12 col-sm-12 committeesBlock" expand-block="committees">
        	<div class="panel panel-default panelNewCustom panel1">
            	<div class="panel-heading">
                	<div class="row">
						<div class="col-md-7 col-sm-7 col-xs-12">
							<h4 class="panel-title text-capital">
								<img src="newCoreDashBoard/img/committees.png" class="iconClass"/>
								committees - <small class="text-muted committeesDate">OVERALL (TILL NOW)</small>
							</h4>
						</div>
						<div class="col-md-5 col-sm-5 col-xs-12">
							<span class="basicCommitteesBlockDiv pull-right">
								<i class="fa fa-gears"  data-toggle="tooltip" data-placement="top" title="Settings"></i>
							</span>
							<span class="notesIcon pull-right">
								<i class="glyphicon glyphicon-list-alt"  data-toggle="tooltip" data-placement="top" title="Notes" onClick="displayDashboardComments(1);"></i>
							</span>
							<span class="iconExpand pull-right" expand-icon="committees">
								<i class="glyphicon glyphicon-fullscreen" data-toggle="tooltip" data-placement="top" title="Expand"></i>
							</span>
							<span class="cadreSettings pull-right refreshCadreCls" onClick="getCommitteesBasicCountReport();"><i class="glyphicon glyphicon-refresh" data-toggle="tooltip" data-placement="top" title="" data-original-title="Refresh"></i></i></span>
							<span><select id="tdpCommitteeYearId" style="width: 98px;display:inline-block;padding:2px 6px;height:25px;margin-top: -3px;"></select></span>
							<span class="input-group pull-right dateRangePickerCls m_XsTop10 hide" expand-block-date="committees">
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
								<li role="presentation" class="text-capital"><a href="#homeComm" aria-controls="home" role="tab" data-toggle="tab">District Level</a></li>
								<li role="presentation"  class="text-capital"><a href="#profileComm" aria-controls="profile" role="tab" data-toggle="tab">Mandal/town/division level</a></li>
								<li role="presentation"  class="active text-capital"><a href="#messagesComm" aria-controls="messages" role="tab" data-toggle="tab">village/ward level</a></li>
							  </ul>
							</div>
							<div class="col-md-6 col-xs-12 col-sm-6 pad_left0 pad_right4">
							  <div class="tab-content navTabsSettingsContent">
								<div role="tabpanel" class="tab-pane" id="homeComm">
									<h4 class="text-capital pad_5" style="color:#99A0A5;">Select Committees</h4>
									<hr style ="margin-bottom:0px;" />
									<div class="basicCommitteeDetailsDiv">
									<ul class="settingsUl">
									<c:forEach items="${userDataVO.subList}" var="basicCommittee">
									   <c:if test="${basicCommittee.id == 1}">
										<li>
											<label class="checkbox-inline">
												<input type="checkbox"  class="districtCommitteecheckBoxClass" value="${basicCommittee.id}"><!--checked-->
												<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">${basicCommittee.name}</h5></div>
										   </label>
										</li>	
									 </c:if>										
									</c:forEach>
									   <li>
											<label class="checkbox-inline">
											  <input type="checkbox"  id="checkAllAffliatedDistrictlevelId">
											  <div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;font-weight:bold;">Affiliated committees</h5></div>
										   </label><!--checked-->
										</li> 
										<c:forEach items="${userDataVO.subList}" var="basicCommittee">
											<c:if test="${basicCommittee.id != 1}">
											<li>
											  <label class="checkbox-inline">
												 <input type="checkbox"  class="districtCommitteecheckBoxClass districtCommitteeAffliatedcheckBoxClass" value="${basicCommittee.id}"><!--checked-->
												 <div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">${basicCommittee.name}</h5></div>
											   </label>
											</li>   
											</c:if>
									  </c:forEach>
										
									</ul>
									</div>
								</div>
								<div role="tabpanel" class="tab-pane" id="profileComm">
									<h4 class="text-capital pad_5" style="color:#99A0A5;">Select Committees</h4>
									<hr style ="margin-bottom:0px;" />
									<div class="basicCommitteeDetailsDiv">
									<ul class="settingsUl">
									<c:forEach items="${userDataVO.subList}" var="basicCommittee">
									   <c:if test="${basicCommittee.id == 1}">
										 <li>
										   <label class="checkbox-inline">
											 <input type="checkbox"  class="checkedBasicComm mandalCommitteecheckBoxClass" value="${basicCommittee.id}" checked><!--checked-->
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
														 <input type="checkbox"  class="mandalCommitteecheckBoxClass mandalCommitteeAffliatedcheckBoxClass" value="${basicCommittee.id}" ><!--checked-->
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
								<div role="tabpanel" class="tab-pane active" id="messagesComm">
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
											  <input type="checkbox"  id="checkAllAffliatedVillagelevelId">
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
					<div class="col-md-12 col-xs-12 col-sm-12 col-md-offset-0 basicCommitteesBlock" expand-block-inner="committees">
						<div id="basicCommitteeCountsDiv"></div>
					</div>
                        <div class="col-md-6 col-xs-12 col-sm-6 col-md-offset-0 userTypeCommitteesBlock committeesHiddenBlock" expand-block-right="committees">
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
                        <div class="col-xs-12 col-sm-12 col-md-12" expand-block-right="committees">
                        	<i class="glyphicon glyphicon-option-horizontal pull-right moreBlocksIcon" expand-block-right="committees" data-toggle="tooltip" data-placement="top" title="Click here for more"></i>
                        </div>
                        <div class="col-md-12 col-xs-12 col-sm-12 col-md-offset-0 moreBlocksDetailAndComp" style="display:none;" expand-block-more="committees">
                        	
							<ul class="list-inline pull-right activeUlCls">
                            	<li class="active detailedBlock">Detailed</li>
                                <li class="comparisionBlock">Comparison</li>
                            </ul>
					    </div>
                        <div class="col-md-12 col-xs-12 col-sm-12 col-md-offset-0 moreBlocks" expand-block-more="committees">
                        	<div class="panel panel-default m_top10">
                            	<div class="panel-body ">
									<div id="levelWiseBasicCommittees"></div>
                                </div>
                            </div>
                        </div>
                       
                        <div class="col-md-12 col-xs-12 col-sm-12 col-md-offset-0 m_top20 moreBlocksDistrictlevel" style="display:none" expand-block-more="committees">
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
							<div class="panel panel-default panelNew">
                                <div class="panel-heading">
                                    <div class="row">
                                        <div class="col-md-8 col-xs-12 col-sm-12">
                                            <span class="headingColor text-capitalize">Committee Detailed Report</span>
                                        </div>
                                        
                                    </div>
                                </div>
                                <div class="panel-body">
									<div id="commiteeWiseDetailedReportId"></div>
								</div>
                            </div>
                        </div>
                        <div class="col-md-12 col-xs-12 col-sm-12 m_top20 moreBlocks1" style="display:none;"  expand-block-more="committees">
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
		<!--Events Start -->
			<div class="col-md-6 col-xs-12 col-sm-12 eventsBlock" expand-block="events">
				<div class="panel panel-default panelNewCustom">
					<div class="panel-heading">
						<div class="row">
							<div class="col-md-9 col-sm-9 col-xs-12" expand-block-heading="events">
								<h4 class="panel-title text-capital">
									<img src="newCoreDashBoard/img/events.png" class="iconClass"/>
									events and activities <small class="text-muted"><span id="dateEventsHeadingId">  - OVERALL (TILL NOW) </span></small>
								</h4>
							</div>
							<div class="col-md-3 col-sm-3 col-xs-12" expand-block-heading1="events">
								<span class="notesIconEvents pull-right">
									<i class="glyphicon glyphicon-list-alt"  data-toggle="tooltip" data-placement="top" title="Notes" onClick="displayDashboardCommentsForEvents(6);"></i>
								</span>
								<span class="eventsIconExpand pull-right" expand-icon="events">
									<i class="glyphicon glyphicon-fullscreen"></i>
								</span>
							<span class="cadreSettings pull-right refreshCadreCls" onClick="refreshEventsActivities();"><i class="glyphicon glyphicon-refresh" data-toggle="tooltip" data-placement="top" title="" data-original-title="Refresh"></i></i></span>
								<span class="input-group pull-right dateRangePickerClsForEvents hide" expand-block-date="events" style="width:200px;">
									<input type="text" id="dateRangeIdForEvents" style="width:180px" class="form-control" />
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
							<div class="col-md-12 col-xs-12 col-sm-12 eventsBlock" expand-block-inner="events">
								<h6 id="lastUpdatedIdEvents" class="updatedDate"></h6>
								<h4><span class="headingColor text-capital">events</span>
								<span id="eventIds" class="eventsListExpandIcon eventCls" expand-icon-inner="events" expand_event_name="events" attr_event_name="Events" style="background-color:#fff;font-size:10px;margin-left:5px;"><i class="glyphicon glyphicon-fullscreen"></i></span><span style="background-color:#fff;margin-left:5px;" class="refreshButtonUcon" id="" onclick="getEventBasicCntDtls();" title="Click here to refresh"><i class="glyphicon glyphicon-refresh"></i></span></h4>
								<div id="mainEventsList" class="m_top20"></div>
								<h4 style="margin-top:20px !important;"><span class="headingColor text-capital">activities</span> <span attr_activity_name="activities" expand-icon-inner="events" expand_event_name="overallActivity" attr_search_type="activities" class="activitesExpandIcon activityCls overAllActivityCls"><i class="glyphicon glyphicon-fullscreen"></i></span> <span style="background-color:#fff;margin-left:5px;" class="refreshButtonUcon" id="" onclick="getActivitiesDetails();" title="Click here to refresh"><i class="glyphicon glyphicon-refresh"></i></span>
									<span class="pull-right activitiesCollapseBtn" style="cursor: pointer;padding: 0px 5px;font-size: 15px;border: 1px solid #333;">+</span>
								</h4>
									<div id="activityEventsListNew" class="activitiesCollapseBody" style="display:none"></div>
				
								<!--<div style="border : 1px solid #333; padding : 5px">-->
									<!--<h4 style="margin-top:30px;"><span class="headingColor text-capital">activities</span></h4>-->
									<!--<div id="activityEventsListNew" class="m_top20"></div>-->
								<!--</div>-->
							</div>
							<!--Acivites Block
							
							
							<div class="col-md-12 col-xs-12 col-sm-12 eventsBlock m_top20">
							<div style="border : 1px solid #333; padding : 5px">
								<h4><span class="headingColor text-capital">activities</span></h4>
								<div id="activityEventsListNew" class="m_top20"></div>
							<!--</div>
							</div>-->
							<div class="col-md-6 col-xs-12 col-sm-12 eventsHiddenBlock" expand-block-right="events">
								<h4><span class="headingColor eventAndActivityCls text-capital">events</span></h4>
								<div class="col-md-6 col-md-offset-6 col-xs-12 col-sm-6 col-sm-offset-6 m_top10">
										<ul class="activeUlCls list-inline hideCls">
											<li class="eventStrngPrCls active" attr_value="strong"><i class="fa fa-arrow-up"></i>&nbsp;top 5 </li>
											<li class="eventStrngPrCls" attr_value="poor"><i class="fa fa-arrow-down"></i>&nbsp;poor 5</li>
										</ul>
								</div> 
								<div id="UserTypeWiseEventMemberDtslDivId" class="m_top20"></div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-12 eventsHiddenBlock" expand-block-right="events" style="display: none;">
								<i class="glyphicon glyphicon-option-horizontal pull-right moreEventsBlocksIcon" data-toggle="tooltip" data-placement="top" ></i>
							</div>
							<div class="col-md-11 col-xs-12 col-sm-11 moreEventsBlocks" id="eventsCmpBlckDivId" expand-block-more="events">
								
								<ul class="list-inline pull-right activeUlCls" style="margin-right: 12px !important;">
									<li class="text-capital activeLICls" attr_typeId="1">Detailed </li>
									<li class="text-capital activeLICls" attr_typeId="2">Comparison</li>
								</ul>
							</div>
							<div class="col-md-1 col-xs-12 col-sm-1 moreEventsBlocks" expand-block-more="events">
							
								<ul class="list-inline pull-right activeUlCls" style="margin-right: 12px !important;">
									<li class="text-capital settingsIconAct">
										<i class="fa fa-gears"  data-toggle="tooltip" data-placement="top" title="Settings"></i>
									</li>
								</ul>
								<div class="actBlockDropDown notesArrow documentCloseClass" style="z-index:999;top: 10px;width:450px;" >
									<i class="glyphicon glyphicon-remove actSetClose pull-right"></i>
									<div class="row">
										<div class="col-md-4 col-xs-12 col-sm-6 pad_right0 m_top20">
										  <ul class="nav nav-tabs navTabsSettings" role="tablist">
											<li role="presentation" class="active text-capital"><a href="#eventsSettings" aria-controls="eventsSettings" role="tab" data-toggle="tab">Events</a></li>
											<li role="presentation" class="text-capital"><a href="#actSettings" aria-controls="actSettings" role="tab" data-toggle="tab">Activities</a></li>
										  </ul>
										</div>
										<div class="col-md-8 col-xs-12 col-sm-6 pad_left0 pad_right4">
										  <div class="tab-content navTabsSettingsContent">
											<div role="tabpanel" class="tab-pane active" id="eventsSettings">
												<h4 class="text-capital pad_5" style="color:#99A0A5;"> EVENTS DETAILS  </h4>
												<hr style ="margin-bottom:0px;margin-top:0px;" />
												<div class="">
													<ul class="evntsSettingsUl">
														<!--<li>
															<label class="checkbox-inline">
																<input type="checkbox" value="0" checked>
																<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">Select All</h5></div>
															</label>
														</li>
														<li>
															<label class="checkbox-inline">
																<input type="checkbox" value="0" checked>
																<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">Select All</h5></div>
															</label>
														</li>
														<li>
															<label class="checkbox-inline">
																<input type="checkbox" value="0" checked>
																<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">Select All</h5></div>
															</label>
														</li>-->
													</ul>
												</div>
											</div>
											<div role="tabpanel" class="tab-pane" id="actSettings">
												<h4 class="text-capital pad_5" style="color:#99A0A5;"> ACTIVITIES DETAILS  </h4>
												<hr style ="margin-bottom:0px;" />
												<div class="">
													<ul class="activitySettingsUl">
														<!-- <li>
															<label class="checkbox-inline">
																<input type="checkbox" value="0" checked>
																<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">Select All</h5></div>
															</label>
														</li>
														<li>
															<label class="checkbox-inline">
																<input type="checkbox" value="0" checked>
																<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">Select All</h5></div>
															</label>
														</li>
														<li>
															<label class="checkbox-inline">
																<input type="checkbox" value="0" checked>
																<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">Select All</h5></div>
															</label>
														</li> -->
													</ul>
												</div>
											</div>
										  </div>
										  
										</div>
										<!--<div class="col-md-8 col-md-offset-4 col-xs-12 col-sm-9 col-sm-offset-3">
											<button type="button" class="btn btn-success">Get Details</button>
										</div>-->
									</div>
									
									
								</div>
							</div>
							<!--<div class="col-md-12 col-xs-12 col-sm-12 moreActivitiesBlocks" style="display:none;" id="activitesCmpBlockDivId">
								<ul class="list-inline pull-right activeUlCls" style="margin-right: 12px !important;display:none">
									<li class="text-capital detailedEvent">Detailed</li>
									<li class="text-capital comparisonActivity">Comparison</li>
								</ul>
							</div> -->
							<div class="col-xs-12 col-sm-12 col-md-12 moreEventsBlocks detailedBlockEvents m_top10" expand-block-more="events">
								<div class="panel panel-default panelNew">
									<div class="panel-body">
										<div id="eventsGraphBlock"></div>
										<div id="eventsGraphBlock1"></div>
									</div>
								</div>
								<div class="panel panel-default panelNew m_top20">
									<div class="panel-heading">
										<h4 class="panel-title">
											<span class="headingColor text-capitalize activitiesH4"> Cohort</span>
										</h4>
									</div>
									<div class="panel-body">
										<div id="eventsDistWiseCohort"></div>
										 <div id="eventsDistWiseCohort1"></div>
										<div id="eventsDistWiseCohort2"></div>
									</div>
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-12 comparisonBlockEvents m_top10" expand-block-more="events" style="display:none" id="evntCmpBLockId">
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
							<div class="col-xs-12 col-sm-12 col-md-12  comparisonBlockActivities m_top10" expand-block-more="events" style="display:none" id="activtyBlckDivId">
								<div class="panel panel-default panelNew">
									<div class="panel-heading">
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-12">
											  <div id="allItsSubUserTypeIdsByParentUserTypeDivIdForActivity"></div>
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
												   <div id="directChildMemberForActivityDivId"></div>
												   <div class="row">
														 <div class="col-md-12 col-xs-12 col-sm-10 col-sm-offset-1 col-md-offset-0">
															<div class="row m_top20">
															 <div id="topPoorLocationsActivityDivId"></div>
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
		<!-- Attendance Start-->
		<div class="col-md-6 col-xs-12 col-sm-12 attendanceBlock" expand-block="attendance">
            <div class="panel panel-default panelNewCustom">
                <div class="panel-heading">
					<div class="row">
						<div class="col-md-9 col-sm-9 col-xs-12"  expand-block-heading="attendance">
							<h4 class="panel-title text-capital">
								<img src="newCoreDashBoard/img/attendance.png" class="iconClass"/>
								emp attendance - <small id="attendanceId" class="text-muted"></small>
							</h4>
						</div>
						<div class="col-md-3 col-sm-3 col-xs-12" expand-block-heading1="attendance">
							<span class="attendanceSetIcon pull-right">
								<i class="fa fa-gears"  data-toggle="tooltip" data-placement="top" title="Settings"></i>
							</span>
							<span class="notesIconattendance pull-right">
								<i class="glyphicon glyphicon-list-alt"  data-toggle="tooltip" data-placement="top" title="Notes" onClick="displayDashboardCommentsForAttendance(7);"></i>
							</span>
							<span class="attendaceIconExpand pull-right mainExpandCls" expand-icon="attendance">
								<i class="glyphicon glyphicon-fullscreen"></i>
							</span>
							<span class="input-group pull-right dateRangePickerClsForAttendance hide" expand-block-date="attendance" style="width:110px;">
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
                        <div class="col-md-12 col-xs-12 col-sm-12 attendanceBlock" expand-block-inner="attendance">
							<div id="officeAttendanceTdlsId">
							</div>
							<div id="officeAttendanceTdlsDeptWiseId"> 
							</div>
                            <!-- <h4 class="m_top20"><span class="headingColor text-capital">month wise total employees</span></h4>
                            <div id="attendance" style="height:150px;"></div>-->
                        </div>
                        <div class="col-md-6 col-xs-12 col-sm-12 attendanceBlockMore" expand-block-right="attendance">
							<div id="deptWiseAttendanceDtlsId">
							</div>
                        </div>
						<div class="col-xs-12 col-sm-12 col-md-12">
                        	<i id="expandForMoreId" class="glyphicon glyphicon-option-horizontal pull-right moreAttBlocksIcon"  expand-block-right="attendance" data-toggle="tooltip" data-placement="top" title="Click here for more"></i>
                        </div>
                        <div class="col-md-12 col-xs-12 col-sm-12 moreAttBlocks" expand-block-more="attendance">
                        	<div class="panel panel-default panelNew">
                            	<div class="panel-heading">
                                	  
									<div class="panel-heading">
										<div class="row">
											<div class="col-md-6 col-xs-12 col-sm-8">
											<h4><span class="headingColor">Hyderabad Party Office</span></h4>
											</div>
											<div class="col-md-6 col-xs-12 col-sm-4">
												<div class="input-group pull-right dateRangePickerClsForAttendance hide" expand-block-date="attendance" style="width:210px;">
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
						<div class="col-md-12 col-xs-12 col-sm-12 moreAttBlocks" id="hydTopId"  expand-block-more="attendance">
							
						</div> 
						<div class="col-md-12 col-xs-12 col-sm-12 moreAttBlocks"  expand-block-more="attendance">
                        	<div class="panel panel-default panelNew">
                            	<div class="panel-heading">
                                	  
									<div class="panel-heading">
										<div class="row">
											<div class="col-md-6 col-xs-12 col-sm-8">
											<h4><span class="headingColor">Guntur Party Office</span></h4>
											</div>
											<div class="col-md-6 col-xs-12 col-sm-4">
												<div class="input-group pull-right dateRangePickerClsForAttendance hide" expand-block-date="attendance" style="width:210px;">
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
						<div class="col-md-12 col-xs-12 col-sm-12 moreAttBlocks" id="gunTopId"  expand-block-more="attendance">
							
						</div>
						
						
                    </div>
                </div>
            </div>
       </div>
		<!-- Attendance End-->
	</div>
	<div class="row">
			<!-- Training Start -->
		<div class="col-md-6 col-xs-12 col-sm-12 trainingsBlock" expand-block="training">
			<div class="panel panel-default panelNewCustom panel1">
				<div class="panel-heading">
					<div class="row">
						<div class="col-md-7 col-sm-9 col-xs-12" expand-block-heading="training">
							<h4 class="panel-title text-capital">
								<img src="newCoreDashBoard/img/training.png" class="iconClass"/>
								training  <small class="text-muted trainingDate">- OVERALL (TILL NOW)</small>
							</h4>
						</div>
						<div class="col-md-5 col-sm-3 col-xs-12" expand-block-heading1="training">
							<!--<span class="settingsIcon pull-right">
								<i class="fa fa-gears"  data-toggle="tooltip" data-placement="top" title="Settings"></i>
							</span>-->
							<span class="notesIconTraining pull-right">
								<i class="glyphicon glyphicon-list-alt"  data-toggle="tooltip" data-placement="top" title="Notes" onClick="displayDashboardCommentsForTraining(4);"></i>
							</span>
							<span class="trainingIconExpand pull-right" expand-icon="training">
								<i class="glyphicon glyphicon-fullscreen"></i>
							</span>
							<span><select id="tdpTriningYearId" style="width: 98px;display:inline-block;padding:2px 6px;height:25px;margin-top: -3px;">
							    <option value="4" selected="selected">2016-2018</option>
                                <option value="3">2014-2016</option>
							</select></span>
							<span class="input-group pull-right dateRangePickerClsForTraining hide" expand-block-date="training" style="display:none;">
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
						<button class="btn btn-default btnCustomCreateTraining btn-sm " id="buttonId" onClick="savingDashboardCommentForTraing(4);">create</button>
					</div>  
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-12 col-xs-12 col-sm-12" style="display:none;"><h6 id="lastUpdatedTimeTrainingCampId" style="top:-8px;position:relative;right:5px;float:right;font-weight:bold"></h6></div>
						<div class="col-md-12 col-xs-12 col-sm-12 trainingsBlock trainingsBlockExpand" expand-block-inner="training">
							<div class="row">
							<!-- <div id="programsDtlsCntTableId" class="m_top10"></div>
								<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
									 <div id="villageWardTblId"></div>
								</div>
								<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
									 <div id="mdlTwnDvsnTabId"></div>
								</div> -->
								<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
									 <div id="programsDtlsCntTableId"></div>
								</div>
								<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
									 <div id="specialProgramLeaderId"></div>
								</div>
								<div id="stateLevelCampId" class="m_top10"></div>
								<!--<div class="col-md-12 col-xs-12 col-sm-12 col-md-offset-0 m_top10">
									 <div id="districtTblId"></div>
								</div>
								<div class="col-md-12 col-xs-12 col-sm-12 col-md-offset-0 m_top10">
									 <div id="stateTblDivId"></div>
									<!--<hr class="m_0"/>"/>
								</div>-->
								<div id="campWiseTrainingId" class="m_top10"></div>
							</div>
						</div>
							<div class="col-md-6 col-xs-12 col-sm-12 col-md-offset-0 trainingsHiddenBlock" expand-block-right="training">
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
									<i data-placement="top" data-toggle="tooltip"  expand-block-right="training" id="switchButtonId" class="glyphicon glyphicon-option-horizontal pull-right moreTrainingBlocksIcon" title="Click here for more" style="cursor:pointer;"></i>  
							</div>
							<div class="col-xs-12 col-sm-12 col-md-12 moreTrainingBlocks trainingDetailedBlock hdCls"  expand-block-more="training">
									<ul class="list-inline pull-right activeUlCls">
										<li id="detailedId" class="trainingDetailed">Detailed</li>
										<li class="trainingComparison">Comparison</li>
										<!--<li class="basicCommitteesBlockDiv"><i class="fa fa-gears"></i></li>-->
									</ul>
							</div> 
							<div class="col-md-12 col-xs-12 col-sm-12 col-md-offset-0 moreTrainingBlocks trainingDetailedBlock hdCls"  expand-block-more="training">
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
							<div class="col-md-12 col-xs-12 col-sm-12 col-md-offset-0 moreTrainingBlocks trainingDetailedBlock hdCls"  expand-block-more="training">
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
						
							<div class="col-md-12 col-xs-12 col-sm-12 moreTrainingBlocks trainingComparisonBlock"  expand-block-more="training">
								<div class="panel panel-default panelNew">
									<div class="panel-heading">
										<div class="row">
											<div class="col-xs-12 col-sm-7 col-md-12">
											 <div id="childUserTypeDetailsDivForTrainingProgram"></div>
											</div>
										</div>
									</div>
									<div class="panel-body">
										<div class="row">
											<div class="col-md-12 col-xs-12 col-sm-12">
											 <div id="trainingChildActivityMemberDivId"> </div>
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
			
			<!-- Cadre Insurance Start-->
			<div class="col-md-6 col-xs-12 col-sm-6 cadreInsuranceBlock" expand-block="cadreInsurance">
				<div class="panel panel-default panelNewCustom">
					<div class="panel-heading">
						<div class="row">
							<div class="col-md-8 col-sm-8 col-xs-8">
								<h4 class="panel-title text-capital">
									<img src="newCoreDashBoard/img/cadreInsurance.png" class="iconClass"/>
									cadre insurance <small class="text-muted"></small>
								</h4>
							</div>
							<div class="col-md-4 col-sm-4 col-xs-4">
								<span class="notesIcon pull-right">
									<i class="glyphicon glyphicon-refresh insuranceRefresh" data-toggle="tooltip" data-placement="top" title="Refresh"></i>
								</span>
								<span class="iconExpand pull-right" expand-icon="cadreInsurance">
									<i class="glyphicon glyphicon-fullscreen" data-toggle="tooltip" data-placement="top" title="Expand"></i>
								</span>
								<span class="input-group pull-right dateRangePickerCls m_XsTop10 hide" style="width:210px" expand-block-date="cadreInsurance">
									<!--<input type="text" id="dateRangeIdCadreInsurance" style="width:190px" class="form-control" />
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-calendar"></i>
									</span>-->
									<select class="yearWiseDtsCls" style="height: 30px;">
										<option value="0" selected>All Time Cadre</option>
										<option value="4">2016-2018</option>
										<option value="3">2014-2016</option>
										<option value="2">2012-2014</option>
									</select>
								</span>
							</div>
						</div>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-12 col-xs-12 col-sm-12" expand-block-inner="cadreInsurance">
								<ul class="activeUlCls cadreInsuranceStateCls list-inline">
								  <li class="active" attr_id="0"> ALL </li>
								  <li  attr_id="1">AP</li>
								  <li  attr_id="2">TS</li>
								</ul>
							
								<ul class="activeUlCls list-inline cadreInsuranceCDate m_top20">
									<li>Today</li>
									<li>Week</li>
									<li>Months</li>
									<li>3 Months</li>
									<li>6 Months</li>
									<li>9 Months</li>
									<li class="active">All Time</li>
								</ul>
								<div id="insuraceCompanyAndTypeOfIssueWiseComplaintsDetails" class="m_top20"></div>
								
							</div>
							<div class="col-md-6 col-xs-12 col-sm-6" expand-block-right="cadreInsurance" style="display:none">
								<ul class="list-inline activeUlCls pull-right">
									<li class="liclsChange active addActiveCls" attr_value="strong"><i class="fa fa-arrow-up"></i>&nbsp;5 strong</li>
									<li class="liclsChange" attr_value="poor"><i class="fa fa-arrow-down"></i>&nbsp;5 poor</li>
								</ul>
								<div id="userTypeWiseTotalCadreInsuranceComplainctCnt"></div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-12">
								<i data-placement="top" data-toggle="tooltip" expand-block-right="cadreInsurance" class="glyphicon glyphicon-option-horizontal pull-right moreCadreInsuranceIcon" title="Click here for more" style="display:none"></i>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-12">
								<ul class="list-inline activeUlCls cadreInsuranceLi pull-right" expand-block-more="cadreInsurance" style="display:none">
									<li class="active" attr_type="Detailed" >Detailed</li>
									<li attr_type="Comparison" >Comparison</li>
								</ul>
							</div>
							<div class="col-md-12 col-xs-12 col-sm-12 m_top20 cadreInsuranceDetailedblock" expand-block-more="cadreInsurance" style="display:none">
								<div class="panel panel-default panelNew">
									<div class="panel-heading">
										<h4><span class="headingColor text-capital">insurance company wise - overview & status details</span></h4>
									</div>
									<div class="panel-body">
										<div id="insuranceCompanyWiseOverviewAndStatusDetails"></div>
									</div>
								</div>
								<div class="panel panel-default panelNew" id="apDistrictBlockId">
									<div class="panel-heading">
										<div class="row">
											<div class="col-md-6 col-xs-12 col-sm-6">
												<label class="radio-inline">
													<input checked type="radio" attr_status="category" class="cadeInsuranceCatAndStsCls" name="cadeInsuranceCat" value="category"/>Show Category Wise (Death & Hospital)
												</label>
												<label class="radio-inline">          
													<input type="radio" attr_status="categoryStatus" class="cadeInsuranceCatAndStsCls" name="cadeInsuranceCat" value="status"/>Show Status Wise
												</label> 
											</div>
											<div class="col-md-6 col-xs-12 col-sm-6">
												<div class="col-md-4 col-xs-12 col-sm-4">
													<select class="form-control" id="apCategoryId">
														<option value=" ">ALL</option>
														<option value="Death">Death</option>
														<option value="Hospitalization">Hospitalization</option>
													</select>
												</div> 
												<div class="col-md-4 col-xs-12 col-sm-4">
													<select class="form-control" id="apStatusId">
														<option value=" ">ALL</option>   
														<option value="INTIMATIONS">INTIMATIONS</option>
														<option value="FORWARDED">FORWARDED</option>
														<option value="SETTLED">SETTLED</option>
														<option value="REJECTED">REJECTED</option>
													</select>
												</div>
												<div class="col-md-2 col-xs-12 col-sm-2">
													<input type="submit" value="SUBMIT" onclick="getInsuranceData();" class="btn-primary btn-sm btn"/>
												</div> 
											</div>
										</div>
										     
									</div>
									<div class="panel-heading">
										<div class="row">
											<div class="col-md-6 col-xs-12 col-sm-6">
												<h4 class="m_top10"><span class="headingColor text-capital" id="distGraphHeadingId">andhra pradesh district wise - categories overview</span></h4>         
											</div>
											<!--<div class="col-md-2 col-xs-12 col-sm-2">
												<select class="form-control" id="apCategoryId">
													<option value=" ">ALL</option>
													<option value="Death">Death</option>
													<option value="Hospitalization">Hospitalization</option>
												</select>
											</div> 
											<div class="col-md-2 col-xs-12 col-sm-2">
												<select class="form-control" id="apStatusId">
													<option value=" ">ALL</option>   
													<option value="INTIMATIONS">INTIMATIONS</option>
													<option value="FORWARDED">FORWARDED</option>
													<option value="SETTLED">SETTLED</option>
													<option value="REJECTED">REJECTED</option>
												</select>
											</div>
											<div class="col-md-2 col-xs-12 col-sm-2">
												<input type="submit" value="SUBMIT" onclick="getInsuranceData();" class="btn-primary btn-sm btn"/>
											</div> -->   
										</div>
									</div>
									<div class="panel-body">
										<div class="cadreInsuranceCategory">
											<ul class="list-inline activeUlCls  constituencyUl">
												<li class="active" onclick="getDistrictWiseThenCategoryWiseInsuranceMemberCount('insuredMember','desc',0);">
													<i class="glyphicon glyphicon-sort-by-attributes"></i>
												</li>
												<li onclick="getDistrictWiseThenCategoryWiseInsuranceMemberCount('insuredMember','asc',0);">
													<i class="glyphicon glyphicon-sort-by-attributes-alt" style="transform:rotate(180deg)"></i>
												</li>
												<li onclick="getDistrictWiseThenCategoryWiseInsuranceMemberCount('name','asc',0);">   
													A-Z
												</li>
												<li onclick="getDistrictWiseThenCategoryWiseInsuranceMemberCount('name','desc',0);">
													Z-A
												</li>
												<li onclick="getDistrictWiseThenCategoryWiseInsuranceMemberCount('constituencyNo','desc',0);" class="showHideConstNoCls">
													constituency id&nbsp;&nbsp;<i class="fa fa-long-arrow-up"></i>
												</li>
												<li onclick="getDistrictWiseThenCategoryWiseInsuranceMemberCount('constituencyNo','asc',0);" class="showHideConstNoCls">   
													constituency id&nbsp;&nbsp;<i class="fa fa-long-arrow-down"></i>
												</li>
												<li>
													<select class="form-control" id="locationListForCategoryId">
													</select>  
												</li>
											</ul>
											<div id="districtWiseThenCategoryWiseInsuranceMemberCount"></div>
										</div>
										<div class="cadreInsuranceCategoryStatus">
											<ul class="list-inline activeUlCls  constituencyUl">
												<li class="active" onclick="getDistrictWiseThenStatusWiseInsuranceMemberCount('insuredMember','desc',0);">
													<i class="glyphicon glyphicon-sort-by-attributes"></i>
												</li>
												<li onclick="getDistrictWiseThenStatusWiseInsuranceMemberCount('insuredMember','asc',0);">
													<i class="glyphicon glyphicon-sort-by-attributes-alt" style="transform:rotate(180deg)"></i>
												</li>
												<li onclick="getDistrictWiseThenStatusWiseInsuranceMemberCount('name','asc',0);">  
													A-Z
												</li>
												<li onclick="getDistrictWiseThenStatusWiseInsuranceMemberCount('name','desc',0);">
													Z-A
												</li>
												<li onclick="getDistrictWiseThenStatusWiseInsuranceMemberCount('constituencyNo','desc',0);" class="showHideConstNoCls">
													constituency id&nbsp;&nbsp;<i class="fa fa-long-arrow-up"></i>
												</li>
												<li onclick="getDistrictWiseThenStatusWiseInsuranceMemberCount('constituencyNo','asc',0);" class="showHideConstNoCls">   
													constituency id&nbsp;&nbsp;<i class="fa fa-long-arrow-down"></i>
												</li>
												<li>
													<select class="form-control" id="locationListForStatusId">
													</select>
												</li>
											</ul>
											<div id="districtWiseThenStatusWiseInsuranceMemberCount"></div>  
										</div>  
									</div>
								</div>
								<div class="panel panel-default panelNew" id="apConstBlockId">
									<div class="panel-heading">     
										<div class="row">
											<div class="col-md-12 col-xs-12 col-sm-12">  
												<h4 class="m_top10"><span class="headingColor text-capital" id="constGraphHeadingId">andhra pradesh constituency wise - categories overview</span></h4>            
											</div>
											
										</div>
									</div>
									<div class="panel-body">
										<div class="cadreInsuranceCategory">  
											<ul class="list-inline activeUlCls  constituencyUl">
												<li class="active" onclick="getConstituencyWiseThenCategoryWiseInsuranceMemberCount('insuredMember','desc',0);">
													<i class="glyphicon glyphicon-sort-by-attributes"></i>
												</li>
												<li onclick="getConstituencyWiseThenCategoryWiseInsuranceMemberCount('insuredMember','asc',0);">   
													<i class="glyphicon glyphicon-sort-by-attributes-alt" style="transform:rotate(180deg)"></i>
												</li>
												<li onclick="getConstituencyWiseThenCategoryWiseInsuranceMemberCount('name','asc',0);">   
													A-Z
												</li>
												<li onclick="getConstituencyWiseThenCategoryWiseInsuranceMemberCount('name','desc',0);">
													Z-A  
												</li>
												<li onclick="getConstituencyWiseThenCategoryWiseInsuranceMemberCount('constituencyNo','desc',0);">
													constituency id&nbsp;&nbsp;<i class="fa fa-long-arrow-up"></i>
												</li>
												<li onclick="getConstituencyWiseThenCategoryWiseInsuranceMemberCount('constituencyNo','asc',0);">
													constituency id&nbsp;&nbsp;<i class="fa fa-long-arrow-down"></i>
												</li>  
												<li>
													<select class="form-control" id="constListForCategoryId">
													</select>
												</li>
											</ul>
											<div id="constituencyWiseThenCategoryWiseInsuranceMemberCount"></div>
										</div>
										<div class="cadreInsuranceCategoryStatus">
											<ul class="list-inline activeUlCls  constituencyUl">
												<li class="active" onclick="getConstituencyWiseThenStatusWiseInsuranceMemberCount('insuredMember','desc',0);">
													<i class="glyphicon glyphicon-sort-by-attributes"></i>
												</li>
												<li onclick="getConstituencyWiseThenStatusWiseInsuranceMemberCount('insuredMember','asc',0);">
													<i class="glyphicon glyphicon-sort-by-attributes-alt" style="transform:rotate(180deg)"></i>
												</li>
												<li onclick="getConstituencyWiseThenStatusWiseInsuranceMemberCount('name','asc',0);">
													A-Z
												</li>
												<li onclick="getConstituencyWiseThenStatusWiseInsuranceMemberCount('name','desc',0);">
													Z-A
												</li>            
												<li onclick="getConstituencyWiseThenStatusWiseInsuranceMemberCount('constituencyNo','desc',0);">
													constituency id&nbsp;&nbsp;<i class="fa fa-long-arrow-up"></i>
												</li>
												<li onclick="getConstituencyWiseThenStatusWiseInsuranceMemberCount('constituencyNo','asc',0);">
													constituency id&nbsp;&nbsp;<i class="fa fa-long-arrow-down"></i>
												</li>  
												<li>
													<select class="form-control" id="constListForStatusId">
													</select>
												</li>  
											</ul>
											<div id="constituencyWiseThenStatusWiseInsuranceMemberCount"></div>
										</div>
									</div>
								</div>
								<div class="panel panel-default panelNew" id="tsDistrictBlockId">
									<div class="panel-heading">
										<div class="row">
											<div class="col-md-6 col-xs-12 col-sm-6">
												<label class="radio-inline">
													<input checked type="radio" attr_status="category" value="category" class="cadeInsuranceCatTs" name="cadeInsuranceCatTs"/>Show Category Wise (Death & Hospital)
												</label>
												<label class="radio-inline">
													<input type="radio" attr_status="categoryStatus" value="categoryStatus" class="cadeInsuranceCatTs" name="cadeInsuranceCatTs"/>Show Status Wise
												</label>
											</div>
											<div class="col-md-6 col-xs-12 col-sm-6">
												<div class="row">
													<div class="col-md-4 col-xs-12 col-sm-4">
														<select class="form-control" id="tsCategoryId">
															<option value=" ">ALL</option>
															<option value="Death">Death</option>
															<option value="Hospitalization">Hospitalization</option>
														</select>
													</div> 
													<div class="col-md-4 col-xs-12 col-sm-4">
														<select class="form-control" id="tsStatusId">
															<option value=" ">ALL</option>
															<option value="INTIMATIONS">INTIMATIONS</option>
															<option value="FORWARDED">FORWARDED</option>
															<option value="SETTLED">SETTLED</option>
															<option value="REJECTED">REJECTED</option>
														</select>
													</div>
													<div class="col-md-2 col-xs-12 col-sm-2">  
														<input type="submit" value="SUBMIT" onclick="getInsuranceDataForTs();" class="btn-primary btn-sm btn"/>
													</div> 
												</div>
											</div>
										</div>
										  
									</div>
									<div class="panel-heading">
										<div class="row">
											<div class="col-md-6 col-xs-12 col-sm-6">
												<h4 class="m_top10"><span class="headingColor text-capital">telangana district wise - categories overview</span></h4>
											</div>
											
										</div>      
									</div>
									<div class="panel-body">
										<div class="cadreInsuranceCategoryTs">
											<ul class="list-inline activeUlCls  constituencyUl">
												<li class="active" onclick="getLocationWiseThenCategoryWiseInsuranceMemberCountForTS('district','insuredMember','desc',0);">
													<i class="glyphicon glyphicon-sort-by-attributes"></i>
												</li>
												<li onclick="getLocationWiseThenCategoryWiseInsuranceMemberCountForTS('district','insuredMember','asc',0);">    
													<i class="glyphicon glyphicon-sort-by-attributes-alt" style="transform:rotate(180deg)"></i>
												</li>
												<li onclick="getLocationWiseThenCategoryWiseInsuranceMemberCountForTS('district','name','asc',0);">
													A-Z
												</li>
												<li onclick="getLocationWiseThenCategoryWiseInsuranceMemberCountForTS('district','name','desc',0);">
													Z-A
												</li>
												
												<li>    
													<select class="form-control" id="locationIdForCategoryDist"></select>
												</li>
											</ul>
											<div id="locationWiseThenCategoryWiseInsuranceMemberCountForTS"></div>
										</div>
										<div class="cadreInsuranceCategoryStatusTs">
											<ul class="list-inline activeUlCls  constituencyUl">
												<li class="active" onclick="getLocationWiseThenStatusWiseInsuranceMemberCountForTS('district','insuredMember','desc',0);">
													<i class="glyphicon glyphicon-sort-by-attributes"></i>
												</li>
												<li onclick="getLocationWiseThenStatusWiseInsuranceMemberCountForTS('district','insuredMember','asc',0)">
													<i class="glyphicon glyphicon-sort-by-attributes-alt" style="transform:rotate(180deg)"></i>
												</li>
												<li onclick="getLocationWiseThenStatusWiseInsuranceMemberCountForTS('district','insuredMember','asc',0);">  
													A-Z
												</li>
												<li onclick="getLocationWiseThenStatusWiseInsuranceMemberCountForTS('district','insuredMember','desc',0);">
													Z-A
												</li>
												
												<li>
													<select class="form-control" id="locationIdForStatusDist"></select>
												</li>
											</ul>
											<div id="locationWiseThenStatusWiseInsuranceMemberCountForTS"></div>
										</div>
									</div>
								</div>
								<div class="panel panel-default panelNew" id="tsConstBlockId">
									<div class="panel-heading">
										<div class="row">
											<div class="col-md-12 col-xs-12 col-sm-12">  
												<h4 class="m_top10"><span class="headingColor text-capital">telangana constituency wise - categories overview</span></h4>
											</div>
										</div>
									</div>
									<div class="panel-body">
										<div class="cadreInsuranceCategoryTs">
											<ul class="list-inline activeUlCls  constituencyUl">
												<li class="active" onclick="getLocationWiseThenCategoryWiseInsuranceMemberCountForTS('constituency','insuredMember','desc',0);">
													<i class="glyphicon glyphicon-sort-by-attributes"></i>
												</li>
												<li onclick="getLocationWiseThenCategoryWiseInsuranceMemberCountForTS('constituency','insuredMember','asc',0);">          
													<i class="glyphicon glyphicon-sort-by-attributes-alt" style="transform:rotate(180deg)"></i>
												</li>
												<li onclick="getLocationWiseThenCategoryWiseInsuranceMemberCountForTS('constituency','name','asc',0);">  
													A-Z
												</li>
												<li onclick="getLocationWiseThenCategoryWiseInsuranceMemberCountForTS('constituency','name','desc',0);">
													Z-A
												</li>
												<li onclick="getLocationWiseThenCategoryWiseInsuranceMemberCountForTS('constituency','constituencyNo','desc',0);">
													constituency id&nbsp;&nbsp;<i class="fa fa-long-arrow-up"></i>
												</li>
												<li onclick="getLocationWiseThenCategoryWiseInsuranceMemberCountForTS('constituency','constituencyNo','asc',0);">
													constituency id&nbsp;&nbsp;<i class="fa fa-long-arrow-down"></i>
												</li>  
												<li>
													<select class="form-control" id="locationIdForCategoryCons"></select>
												</li>
											</ul>
											<div id="locationWiseThenCategoryWiseInsuranceMemberCountForTSCons"></div>
										</div>
										<div class="cadreInsuranceCategoryStatusTs">
											<ul class="list-inline activeUlCls  constituencyUl">
												<li class="active" onclick="getLocationWiseThenStatusWiseInsuranceMemberCountForTS('constituency','insuredMember','desc',0)">
													<i class="glyphicon glyphicon-sort-by-attributes"></i>
												</li>
												<li onclick="getLocationWiseThenStatusWiseInsuranceMemberCountForTS('constituency','insuredMember','asc',0)">
													<i class="glyphicon glyphicon-sort-by-attributes-alt" style="transform:rotate(180deg)"></i>
												</li>
												<li onclick="">
													A-Z
												</li>
												<li onclick="">
													Z-A
												</li>
												<li onclick="">
													constituency id&nbsp;&nbsp;<i class="fa fa-long-arrow-up"></i>
												</li>
												<li onclick="">
													constituency id&nbsp;&nbsp;<i class="fa fa-long-arrow-down"></i>
												</li>
												<li>
													<select class="form-control" id="locationIdForStatusCons"></select>
												</li>  
											</ul>
											<div id="locationWiseThenStatusWiseInsuranceMemberCountForTSCons"></div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12 col-xs-12 col-sm-12  cadreInsuranceComparisonblock" expand-block-more="cadreInsurance" style="display:none">
								<div class="panel panel-default panelNew">
									<div class="panel-heading">
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-12">
												<div id="childUserTypeDetailsDivForCadreInsurance"></div>
											</div>
										</div>
									</div>
									<div class="panel-body">
										<div class="row">
											<div class="col-md-12 col-xs-12 col-sm-12">
												<div class="bg_ED pad_15 m_top20"> 
													<div id="cadreInsuranceChildActivityMemberDivId"> </div>
													<div id="userTypeWiseChildDtlsTabForCadreInsuranceDivId"></div>
													<h4 class="text-capital" style="background-color:#ddd;padding:6px;display:none;" id="DeathHeadingId"></h4>
													<div id="candidateWiseCadreInsuranceDeathDtlsDivId" class="m_top20"></div>
													<h4 class="text-capital" style="background-color:#ddd;padding:6px;display:none;" id="HospitalizationHeadingId"></h4>
													<div id="candidateWiseCadreInsuranceHospDtlsDivId" class="m_top20"></div>
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
			<!-- Cadre Insurance End-->
				<!--Cadre Registration -->
		<div class="col-md-6 col-xs-12 col-sm-12 cadreBlock" expand-block="cadre">  
        	<div class="panel panel-default panelNewCustom">
            	<div class="panel-heading">
                	<div class="row">
                    	<div class="col-md-9 col-sm-9 col-xs-12" expand-block-heading="cadre">
							<h4 class="panel-title text-capital">
								<img src="newCoreDashBoard/img/cadreRegistration.png" class="iconClass"/>
								cadre registration <small class="text-muted"> - OVERALL (TILL NOW)</small>
							</h4>
						</div>
						<div class="col-md-3 col-sm-3 col-xs-12" expand-block-heading1="cadre">
							   
							<span class="cadreSettings pull-right" id="cadreSettingsId">
								<i class="fa fa-gears" data-toggle="tooltip" data-placement="top" title="Settings"></i>
							</span>
							<span class="cadreNotes pull-right">  
								<i class="glyphicon glyphicon-list-alt"  data-toggle="tooltip" data-placement="top" title="Notes" onClick="displayDashboardComments(1);"></i>
							</span>
							<span class="cadreSettings pull-right refreshCadreCls" onClick="refreshCadre();">
								<i class="glyphicon glyphicon-refresh" data-toggle="tooltip" data-placement="top" title="" data-original-title="Refresh"></i>
							</span> 
							 <span class="cadreExpand pull-right" expand-icon="cadre">  
								<i class="glyphicon glyphicon-fullscreen" data-toggle="tooltip" data-placement="top" title="Expand"></i>
							</span> 
							<span class="input-group pull-right dateRangeClsForCadre  m_XsTop10 hide" expand-block-date="cadre" style="width:210px">
								<input type="text" id="dateRangeIdForCadre" style="width:190px"	 class="form-control" />
								<span class="input-group-addon">
									<i class="glyphicon glyphicon-calendar"></i>
								</span>
							</span>
						</div>
                    </div>      
                </div>       
				<div class="specialCadreDropDown" style="right:13px;top:30px;">
					<ul class="list-inline">
						<li><h4>Sorting Type</h4></li><span class="glyphicon glyphicon-remove-circle pull-right settingCloseCls" style="cursor:pointer;"></span><br> 
						<li><label><input type="checkbox" class="selectOneSpecialCadre" id="targetWiseId" attr_sort_type="TargetWise" checked/>&nbsp&nbsp2016 Target Achieved</label></li><br>
						<li><label><input type="checkbox" class="selectOneSpecialCadre" id="2016CadreWiseId" attr_sort_type="2016CadreWise" />&nbsp&nbsp2016 Registrations Count</label></li>
					</ul>        
					<button type="button" class="btn btn-success specialCadreBtncls">Get Details</button>   
					<span id="checkErrId" style="color:red;"></span>  
				</div>    
                <div class="panel-body">
			    	<div class="row">
						  
						<div class="col-md-12 col-xs-12 col-sm-12">
							   
							<h6 id="lastUpdatedTimeCadreId" style="top:-8px;position:relative;right:5px;float:right;font-weight:bold">Last Updated : </h6>
						</div>
						
                        <div class="col-md-12 col-xs-12 col-sm-12 cadreBlock" expand-block-inner="cadre">
						    <div class="row">
							 <div class="col-md-12 col-xs-12 col-sm-12  showCadrecls" style="display:none;">
								<h4 class="text-capital m_top10"><span class="headingColor">OVERALL REGISTRATIONS</span></h4>
								<div class="row">
									<div class="col-md-6 col-xs-12 col-sm-12">
										<div class="bg_ED pad_5 m_top10">
											<div class="row">
												<div class="col-md-8 col-xs-12 col-sm-4">
													<h5 class="text-capital" >TOTAL-<span id="totalperc"></span></h5>  
													<h4  data-toggle="tooltip" class ="cadreCount" id="totalRegId" attr_total="0" attr_total_per="0">0</h4>
												</div>
												<div class="col-md-2 col-xs-12 col-sm-2">
													<i style="cursor: pointer; font-size: 16px; margin-top: 10px;" class="glyphicon glyphicon-info-sign compCls" attr_state_id="0" attr_option="total" data-toggle="tooltip" data-placement="top" title="" data-original-title="Today And Yesterday Comparison"></i>
												</div>
											</div> 
										</div> 
									</div>
									<div class="col-md-6 col-xs-12 col-sm-12 m_XsTop10">
										<div class="bg_ED pad_5 m_top10">
											<h5 class="text-capital">TODAY-<span id="todayperc1"></span></h5><h4  class ="cadreCount" id="totalTodayId" attr_today="0" attr_today_per ="0">0</h4>
										</div>
									</div>
								</div>
							   </div>
							</div>
						    <div class="row">
								<div class="col-md-6 col-xs-12 col-sm-6" id="totalTodayCadreRegistrationBlockDivAPId"></div>
								<div class="col-md-6 col-xs-12 col-sm-6" id="totalTodayCadreRegistrationBlockDivTSId"></div>  
							</div>  
							
                            <div class="row">  
							
							   <!--<div id="enumeratorsInfoDivId"></div>
							   <div id="enumeratorsInfoDivTSId"></div>      -->
							   <!-- 
							   <div class="col-md-6 col-xs-12 col-sm-12 m_top20">
									<button class="btn btn-success btn-block text-capital " id="cadreModalDivid">kuppam constituency <br/>detailed report</button>
							   </div>
							   <div class="col-md-6 col-xs-12 col-sm-12 m_top20">
									<button class="btn btn-success btn-block text-capital " id="cadreModalTabDivid">Kuppam Constitency Tab User<br/> Detailed Report</button>
							   </div>
							    -->
                            </div>
                        </div>
						
						<div class="col-md-6 col-xs-12 col-sm-12 moreCadreBlock" expand-block-right="cadre">
                        	<div class="row">
                            	<div class="col-md-12 col-xs-12 col-sm-12">
									<div id="constituenctDetailedReport"></div>	     
                                </div>
                            </div>
					     </div>
					    <div class="col-md-6 col-xs-12 col-sm-12 moreCadreBlock" expand-block-right="cadre">
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
                        	<i class="glyphicon glyphicon-option-horizontal pull-right moreBlocksCadreIcon"  expand-block-right="cadre" data-toggle="tooltip" data-placement="top" title="Click here for more"></i>
                        </div>
                        <div class="col-md-12 col-xs-12 col-sm-12 moreBlocksCadre" expand-block-more="cadre">   
                        	<ul class="activeUlCls list-inline pull-right" style="margin-right: 12px !important;display:inline-block">
                                <li class="text-capital"><i class="fa fa-gears"></i></li>
                            </ul>    
                            <ul class="activeUlCls list-inline pull-right" style="margin-right: 12px !important;display:inline-block">
                                <li class="text-capital cadreDetailsCls">Detailed</li>
								<li class="text-capital compareBlockSwitchCls">Comparison</li>  
                            </ul>
                            
                        </div> 
						<div class="col-md-12 col-xs-12 col-sm-12 moreBlocksCadre detailsCls m_top20" expand-block-more="cadre">        
							<div class="panel panel-default panelNew">
								<div class="panel-heading">
									<span class="headingColor">State Wise Overview</span>
									<a href="javascript:void(0);" class="btn btn-success btn-sm  pull-right" id="statewiseoverview" data-hidden="true"> Show </a>
								</div>
								<div class="panel-body" id="statewiseoverviewPanel">
									<div class="row" id="sourceTypeId">
										
									</div>
									<div class="row" id="sourceTypeTsId">  
										
									</div>    
								</div>
							</div>
						</div>
                        <div class="col-md-12 col-xs-12 col-sm-12 moreBlocksCadre detailsCls" expand-block-more="cadre">
                        	<div class="panel panel-default panelNew">
                            	<div class="panel-heading">
                                	<div class="row">
                                    	<div class="col-md-5 col-xs-12 col-sm-12">
                                        	<h4><span class="headingColor">District Registrations - Target Vs Achieved</span></h4><br>
                                        	<h2><span class="headingColor" id="apDistrictHeadingId" style="display:none;">Andhra Pradesh</span></h2>
                                        </div>
                                        <div class="col-md-7 col-xs-12 col-sm-12 m_XsTop10">
                                        	<ul class="activeUlCls districtFilterUlCls list-inline pull-right">
                                                <li class="active districtFilterCls" id="dstrctOverlAllCntId" attr_over_all_cnt="0"  attr_filter_value="All">All - 0</li>
                                                <li class="districtFilterCls"  id="dstrctyveryGoodCntId" attr_very_good_cnt="0" attr_filter_value="verygood" data-toggle="tooltip" data-placement="top" title="More than 100% of target">Very Good - 0</li>
                                                <li class="districtFilterCls"  id="dstrctGoodCntId" attr_good_cnt="0" attr_filter_value="good" data-toggle="tooltip" data-placement="top" title="91%-100% of target">Good - 0</li>
                                                <li class="districtFilterCls"   id="dstrctOkCntId" attr_ok_cnt="0" attr_filter_value="ok" data-toggle="tooltip" data-placement="top" title="81%-90% of target">Ok - 0</li>
												<li class="districtFilterCls"   id="dstrctPoorCntId" attr_poor_cnt="0"  attr_filter_value="poor" data-toggle="tooltip" data-placement="top" title="61%-80% of target">Poor - 0</li>
                                                <li class="districtFilterCls"  id="dstrctVeryPoorCntId" attr_very_poor_cnt="0" attr_filter_value="verypoor" data-toggle="tooltip" data-placement="top" title="<=60% of target">Very Poor - 0</li>
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
					    <div class="col-md-12 col-xs-12 col-sm-12 moreBlocksCadre detailsCls" expand-block-more="cadre">         
                        	<div class="panel panel-default panelNew">
                            	<div class="panel-heading">
                                	<div class="row">
                                    	<div class="col-md-6 col-xs-12 col-sm-12">
                                        	<h4><span class="headingColor text-capitalize">contituency wise registrations</span></h4>
                                        </div>
                                        <div class="col-md-6 col-xs-12 col-sm-12" style="position:relative;">
								        </div>
                                    </div>
                                </div>
                                <div class="panel-body">
                                	<div class="row">
										<div class="col-md-6 col-xs-12 col-sm-6 settingsBlockCadre">  
											<h4>Andhra Pradesh</h4>
											<ul class="activeUlCls list-inline pull-right m_top20" style="margin-right: 12px !important;display:inline-block">
												<li id="settingsCadre" class="text-capital"><i class="fa fa-gears" title="filter result"></i></li>
											</ul>
                                        	<ul class="activeUlCls list-inline  apConstituencyUlCls m_top20">
												<li class="active constituencyFilterCls"  id="cnsttncyverlAllCntId" attr_filter_value="All">All - 0</li>
												<li class="constituencyFilterCls" id="cnsttncyveryGoodCntId" attr_filter_value="verygood" data-toggle="tooltip" data-placement="top" title="More than 100% of target">Very Good - 0</li>
												<li class="constituencyFilterCls" id="cnsttncyverlGoodCntId"  attr_filter_value="good" data-toggle="tooltip" data-placement="top" title="91%-100% of target">Good - 0</li>
												<li class="constituencyFilterCls" id="cnsttncyOkCntId"  attr_filter_value="ok" data-toggle="tooltip" data-placement="top" title="81%-90% of target">Ok - 0</li>
												<li class="constituencyFilterCls" id="cnsttncyverlPoorCntId"  attr_filter_value="poor" data-toggle="tooltip" data-placement="top" title="61%-80% of target">poor - 0</li>
												<li class="constituencyFilterCls" id="cnsttncyveryPoorCntId"  attr_filter_value="verypoor" data-toggle="tooltip" data-placement="top" title="<=60% of target">Very Poor - 0</li>      
											</ul>  
											<div class="cadreRDD documentCloseClass" style="z-index:999;margin-top: -3px;width:200px;display:none;" >
												<i class="glyphicon glyphicon-remove newsSetClose pull-right closePopUpCls"></i>
												<div class="row">
													<div><span style="color:red;margin-left:20px" id="cadreRegSearchErrorId"></span></div>
													<div class="col-md-12 col-xs-12 col-sm-12">
														<div class="pad_5 bg_EE">
															<label>
																<input attr_cadre_search_type="2016Renewal" checked id="2016RenewalCheckBoxId" type="checkbox"/>&nbsp;&nbsp;2016 Renewal
															</label><br/>
															<label>
																<input attr_cadre_search_type="2016New" checked id="2016NewCheckBoxId" type="checkbox"/>&nbsp;&nbsp;2016 New
															</label><br/>
															<label>
																<input attr_cadre_search_type="2014Cadre" attr_2014_active="Yes" id="2014CadreCheckBoxId" type="checkbox"/>&nbsp;&nbsp;2014 cadre
															</label>
														</div>
													</div>
													<div class="col-md-12 col-xs-12 col-sm-12">
														<p>AP DISTRICTS</p>
														<label><input type="checkbox" class="selectAllApDistrict"/>&nbsp&nbspSelect All</label>
														<div id="apDistrictId"></div>
													</div>
													<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
														<input type="button" class="btn btn-success" id="getCadreRegistrationDetailsBtnId" value="GetDetails"/>
													</div>
												</div>
											</div>
											<div class="row m_top20">
												<div class="col-md-2 col-xs-12 col-sm-2">
													<i class="glyphicon glyphicon-sort-by-attributes decendingApSorting pull-left" attr_filter_value="No" style="transform:rotate(180deg)"></i>
													<i class="glyphicon glyphicon-sort-by-attributes-alt ascendingApSorting pull-left" attr_filter_value="No"></i>
												</div>
												<div class="col-md-4 col-xs-12 col-sm-4">
													<select attr_filter_value="All" class="form-control" id="apConstituencySelectBoxId"></select>
												</div>
												<!--<div class="col-md-4 col-xs-12 col-sm-4">
												<label class="checkbox-inline">
													<input id="kuppamConstituencyCheckBoxId" checked type="checkbox">Exclude Kuppam 
												</label>
												</div>-->  
											</div>
											<div Id="apscrollBarDivId">
											<div id="apConstituencyRegistrationReportDivId" class="m_top20"></div>
											</div>
										</div>
										<div class="col-md-6 col-xs-12 col-sm-6 settingsBlockCadre">
											<h4>Telangana</h4>
											<ul class="activeUlCls list-inline pull-right m_top20" style="margin-right: 12px !important;display:inline-block">
												<li  id="settingsCadre" class="text-capital"><i class="fa fa-gears" title="filter result"></i></li>
											</ul>
                                        	<ul class="activeUlCls list-inline tsConstituencyUlCls m_top20">
                                            <li class="active tsConstituencyFilterCls"  id="tsCnsttncyverlAllCntId" attr_filter_value="All"  >All - 0</li>
                                                <li class="tsConstituencyFilterCls" id="tsCnsttncyveryGoodCntId"  attr_filter_value="verygood" data-toggle="tooltip" data-placement="top" title="More than 100% of target">Very Good - 0</li>
                                                <li class="tsConstituencyFilterCls" id="tsCnsttncyverlGoodCntId"  attr_filter_value="good" data-toggle="tooltip" data-placement="top" title="91%-100% of target">Good - 0</li>
                                                <li class="tsConstituencyFilterCls" id="tsCnsttncyOkCntId"  attr_filter_value="ok" data-toggle="tooltip" data-placement="top" title="81%-90% of target">Ok - 0</li>
												<li class="tsConstituencyFilterCls" id="tsCnsttncyverlPoorCntId"  attr_filter_value="poor" data-toggle="tooltip" data-placement="top" title="61%-80% of target">poor - 0</li>
                                                <li class="tsConstituencyFilterCls" id="tsCnsttncyveryPoorCntId"  attr_filter_value="verypoor" data-toggle="tooltip" data-placement="top" title="<=60% of target">Very Poor - 0</li>
                                            </ul>
											<div class="cadreRDD documentCloseClass" style="z-index:999;margin-top: -3px;width:200px;display:none;" >
												<i class="glyphicon glyphicon-remove newsSetClose pull-right closePopUpCls"></i>
												<div class="row">
												   <div><span style="color:red;margin-left:20px" id="cadreTsRegSearchErrorId"></span></div>
													<div class="col-md-12 col-xs-12 col-sm-12">
														<div class="pad_5 bg_EE">
															<label>
																<input attr_cadre_search_type="2016Renewal" checked id="2016TsRenewalCheckBoxId" type="checkbox"/>&nbsp;&nbsp;2016 Renewal
															</label><br/>
															<label>
																<input attr_cadre_search_type="2016New" checked id="2016TsNewCheckBoxId" type="checkbox"/>&nbsp;&nbsp;2016 New
															</label><br/>
															<label>
																<input attr_cadre_search_type="2014Cadre" attr_2014_active="Yes" id="2014TsCadreCheckBoxId" type="checkbox"/>&nbsp;&nbsp;2014 cadre
															</label>
														</div>
													</div>
													<div class="col-md-12 col-xs-12 col-sm-12">
													  <p>TS DISTRICTS</p>
													  <ul class="list-inline">
														<li><label><input type="checkbox" class="selectAllTsDistrict"/>&nbsp&nbspSelect All</label></li>
													   </ul>
													  <div id="tsDistrictId"></div>
													</div>
													<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
														<input type="button" class="btn btn-success" id="getTsCadreRegistrationDetailsBtnId" value="GetDetails"/>
													</div>
												</div>
											</div>
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
						<div class="col-md-12 col-xs-12 col-sm-12 m_top20 moreBlocksCadre compareCls" expand-block-more="cadre">                
							<div class="panel panel-default panelNew">   <!--swadhin-->
								<div class="panel-heading">
									<div id="designationListId"></div>
								</div>
								<div class="panel-body">  
									<div class="row" id="childMembersId">

									</div>
									<div class="bg_ED pad_15">
										<div class="row" id="directChildId"></div>   
										<!--<div class="row" id="enumeratorsId"></div> -->    
										<div class="row m_top20">
											<div class="col-md-6 col-xs-12 col-sm-6">
												<span class="headingColor1" id="districtWiseRportHeadingId" style="display:none;">DISTRICT WISE REGISTRATIONS</span>
												<div id="individualDtlsId"></div>   
											</div>
											<!--<div class="col-md-6 col-xs-12 col-sm-6">
												<span class="headingColor" style="display:none;">REGISTERED VOTER ID</span>  
												<div id="voterDtlsId"></div>  
											   </div>-->
										</div>
										<div class="row m_top20">
										  <div class="col-md-7 col-xs-12 col-sm-5">
											<h4><span class="headingColor1 hideConReport" id="constituencyReportHeadingId">CONSTITUENCY WISE REGISTRATIONS</span></h4>
										  </div>
										  <div class="col-md-5 col-xs-12 col-sm-7">
											<ul class="activeUlCls list-inline usrWseCnsttuncyUlCls pull-right">
                                                <li class="active consFilterCls" id="usrWseCnsttuncyAllCntId" attr_filter_value="All">All-0</li>
                                                <li class="consFilterCls" id="usrWseCnsttuncyVryGdCntId" attr_filter_value="verygood" data-toggle="tooltip" data-placement="top" title="More than 100% of target">Very Good-0</li>
                                                <li class="consFilterCls" id="usrWseCnsttuncyGdCntId" attr_filter_value="good" data-toggle="tooltip" data-placement="top" title="91%-100% of target">Good-0</li>
                                                <li class="consFilterCls" id="usrWseCnsttuncyOkCntId" attr_filter_value="ok" data-toggle="tooltip" data-placement="top" title="81%-90% of target">Ok-0</li>
												<li class="consFilterCls" id="usrWseCnsttuncyPoorCntId" attr_filter_value="poor" data-toggle="tooltip" data-placement="top" title="61%-80% of target">Poor-0</li>
                                                <li class="consFilterCls" id="usrWseCnsttuncyVryPoorCntId" attr_filter_value="verypoor" data-toggle="tooltip" data-placement="top" title="<=60% of target">Very Poor-0</li>
                                            </ul>
										  </div>
										  <div class="col-md-12 col-xs-12 col-sm-12 m_top20">
											<div id="individualDtls"></div>        
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
		<!--Cadre Registration ENd -->
		<div class="col-md-6 col-xs-12 col-sm-12 prajaSankaplaYatraBlock" expand-block="prajaSankaplaYatra">
				
				<div class="panel panel-default panelNewCustom panel2">
					<div class="panel-heading">
						<div class="row">
							<div class="col-md-9 col-sm-9 col-xs-12" expand-block-heading="prajaSankaplaYatra">
								<h4 class="panel-title text-capital" style="width:440px;">    
									<img src="newCoreDashBoard/img/news.png" class="iconClass"/>
									Praja Sankalpam Yatra News 
								</h4>  
								
							</div>
							<div class="col-sm-3">
								<span class="pull-right" expand-icon="prajaSankaplaYatra">
									<i class="glyphicon glyphicon-fullscreen" data-toggle="tooltip" data-placement="top" title="Expand"></i>
								</span>
							</div>
						</div>
					</div>
					<div class="panel-body">
						<div class="row">
							<span class="input-group pull-right dateRangePickerCls">
								<input type="text" id="dateRangePrajaSankalpaId" class="form-control" style="width:180px"/>
								<span class="input-group-addon">
									<i class="glyphicon glyphicon-calendar"></i>
								</span>
							</span>	
						</div>
						<div class="row">
							<div class="col-sm-12">
								<h4 class="text-capital"><span class="headingColor" style="margin-right:5px">Print Media</span></h4>
								<div class="row m_top10">
									<div class="col-sm-6">
										<h5>PRAJA SANKALPA YATRA</h5>
									</div>
									<div class="col-sm-6">
										<h5 style="font-size: 13px; text-align: center;">COUNTER ON PRAJA SANKALPA YATRA</h5>
									</div>
								</div>
								<div id="printMediaOverAllBlockId" class=""></div>
								<div id="printMediaPartyBlockId" class="m_top10"></div>
								<div id="printMediaPublicationBlockId" class="m_top10"></div>
							</div>
							<div class="col-sm-12 m_top10">
								<h4 class="text-capital"><span class="headingColor" style="margin-right:5px">Electronic Media</span></h4>
								<div class="row m_top10">
									<div class="col-sm-6">
										<h5>PRAJA SANKALPA YATRA</h5>
									</div>
									<div class="col-sm-6">
										<h5 style="font-size: 13px; text-align: center;">COUNTER ON PRAJA SANKALPA YATRA</h5>
									</div>
								</div>
								<div id="electronicMediaOverAllBlockId" class=""></div>
								<div id="electronicMediaPartyBlockId" class="m_top10"></div>
								<div id="electronicMediaPublicationBlockId" class="m_top10"></div>
							</div>
							
						</div>
					</div>
				</div>
			</div>
			<!-- NEWS BLOCK END-->
			<!--Press Meet block Start-->
			<div class="col-md-6 col-xs-12 col-sm-12 pressmeetBlock" expand-block="pressmeet">
			   <div class="panel panel-default panelNewCustom ">
				  <div class="panel-heading">
					 <div class="row">
						<div class="col-md-9 col-sm-9 col-xs-12" expand-block-heading="pressmeet">
						   <h4 class="panel-title text-capital" style="width:440px;">    
							  <img src="newCoreDashBoard/img/Press_Meet_icon.png" class="iconClass"/>
							  PRESS MEET 
						   </h4>
						</div>
						<div class="col-md-3 col-sm-3 col-xs-12" expand-block-heading1="pressmeet">
						   <span class="pressmeetIconExpand pull-right" expand-icon="pressmeet">
						   <i class="glyphicon glyphicon-fullscreen"></i>
						   </span>
						</div>
					 </div>
				  </div>
				  <div class="panel-body">
					<div class="row">
						<span class="input-group pull-right dateRangePickerCls">
							<input type="text" id="dateRangePressmeetId" class="form-control" style="width:180px"/>
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-calendar"></i>
							</span>
						</span>	
					</div>	
					 <div class="row">
						<div class="col-md-12 col-xs-12 col-sm-12 pressmeetBlock" expand-block-inner="pressmeet">
						   <div class="row">
							  <div id="partyWisePressMeetDetails"></div>
						   </div>
						</div>
						<div class="col-md-6 col-xs-12 col-sm-12 pressmeetHiddenBlock" expand-block-right="pressmeet">
						   <div class="row">
							  <div class="col-md-6 col-xs-12 col-sm-6 m_top10">
								 <h4 class="text-capital"><span class="headingColor ">Spokespersons</span></h4>
							  </div>
							  <div class="col-md-6 col-md-offset-6 col-xs-12 col-sm-6 col-sm-offset-6 m_top10">
								 <ul class="activeUlCls list-inline pressmeetSpokesCls">
									<li id="pressmeetTopId" class="active"><i class="fa fa-arrow-up"></i>&nbsp;Top </li>
									<li id="pressmeetLowId"><i class="fa fa-arrow-down"></i>&nbsp;Poor</li>
								 </ul>
							  </div>
							  <div id="spokesPersonWisepressmeetDetailsId" class="col-md-12 col-xs-12 col-sm-12">
							  </div>
						   </div>                  
						</div>
						<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
							<div class="panel-group" id="pressmeetCollapse" role="tablist" aria-multiselectable="true">
								<div class="panel panel-default panelNew">
									<div class="panel-heading" role="tab" id="headingone">
										<a class="collapsed collapsepressmeetIcon" role="button" data-toggle="collapse" data-parent="#pressmeetCollapse" href="#collapseone" aria-expanded="true" aria-controls="collapseone">
											<h4 class="panel-title"><span class="headingColor responsiveWidth text-capitalize" style="display:block;width:330px;">scale based performance cohort</span></h4>
										</a>
									</div>
									<div id="collapseone" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingone">
										<div class="panel-body">
										   <div class="row">
											  <div id="scaleBasedPerformanceCohortId">
											  </div>
										   </div>
										</div>
									</div>
								</div>
								<div class="panel panel-default panelNew">
									<div class="panel-heading" role="tab" id="headingtwo">
										<a class="collapsed collapsepressmeetIcon" role="button" data-toggle="collapse" data-parent="#pressmeetCollapse" href="#collapsetwo" aria-expanded="true" aria-controls="collapsetwo">
											<h4 class="panel-title"><span class="headingColor responsiveWidth text-capitalize" style="display:block;width:330px;">candidate overall performance cohort</span></h4>
										</a>
									</div>
									<div id="collapsetwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingtwo">
										<div class="panel-body">
										   <div class="row">
											  <div id="candidateOverAllPerformanceCohortId" style="overflow:auto;">
											  </div>
										   </div>
										</div>
									</div>
								</div>
							  <div class="panel panel-default panelNew">
									<div class="panel-heading" role="tab" id="headingsthree">
										<a class="collapsed collapsepressmeetIcon" role="button" data-toggle="collapse" data-parent="#pressmeetCollapse" href="#collapsethree" aria-expanded="false" aria-controls="collapsethree">
										   <h4 class="panel-title"><span class="headingColor responsiveWidth text-capitalize" style="display:block;width:330px;">Top 5 Performers</span></h4>
										</a>
									</div>
									<div id="collapsethree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingsthree">
									  <div class="panel-body">
										<div class="row">
										
										  <div class="col-md-12 col-xs-12 col-sm-12">
										  <ul class=" activeUlCls edtionTypescls list-inline pull-right">
										   <li class="active" id="0"> Both Edition </li>
										   <li id="1" > Main Edition </li>
										  <li id="2"> District Edtion</li>
										 
										  </ul>
										  </div>
											<div class="col-md-12 col-xs-12 col-sm-12 m_top20" id="candidatePressMeetPerformanceId"></div>
										</div>
									  </div>
									</div>
								  </div> 
								<div class="panel panel-default panelNew">
									<div class="panel-heading" role="tab" id="headingsFour">
										<a class="collapsed collapsepressmeetIcon" role="button" data-toggle="collapse" data-parent="#pressmeetCollapse" href="#collapsesFour" aria-expanded="false" aria-controls="collapsesFour">
										   <h4><span class="headingColor responsiveWidth text-capitalize" style="display:block;width:330px;">Publication VS Party</span></h4>
										</a>
									</div>
									<div id="collapsesFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingsFour">
									  <div class="panel-body">
										<div class="row">
											<div class="col-md-12 col-xs-12 col-sm-12">
											 <ul class=" activeUlCls publicationAndEdtionTypescls list-inline pull-right">
											   <li class="active" id="0"> Both Edition </li>
											   <li id="1" > Main Edition </li>
											  <li id="2"> District Edtion</li>
											</ul>
											 </div>
											<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
												<div id="publicationAndPartyWiseDetailsId" style="overflow:auto;"></div>
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
<input type="hidden" id="alertTypeHiddenId"></input> 
<input type="hidden" id="alertEditionTypeHiddenId"></input>  
<input type="hidden" id="hiddenActivityId"></input>  
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
<!-- Insurance Modal -->

<div class="modal fade" id="insuranceModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document" style="width:95%"> 
		<div class="modal-content" style="border-radius:0px">
			<div class="modal-header" style="background-color:#CCC">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="insuranceHeadingId">Insurance Details</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-12 col-xs-12 col-sm-12">
						<div id="lagDaysInsuranceComplaintsCounts"></div>
						<div id="insuraceStatusWiseComplaintsDetails" class="m_top20"></div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<!--Insurance model end -->

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
				<h4 class="modal-title" id="modalLabelNameId">Debate Details</h4>
			  </div>
			  <div class="modal-body">
				  <div  class="row">
					<div class="col-md-12 col-xs-12 col-sm-12">
						<div class=" debateModelCls"></div>
					</div>
				  </div>				 
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
		
		<!--<div class="row m_top10">
			<div class="col-md-12 col-xs-12 col-sm-12">
			 <input type="checkbox" id="getMeetingDetails" class="btn btn-success"/>If u will check this. you will get all (comment / not comment ) meeting details
			</div>
		</div>-->
	  </div>
      <div class="modal-body">
	  <div class="row consolidatedCls well well-xs" style="margin-top: -15px;">
		
			<div class="col-md-3 col-xs-12 col-sm-4"  style="text-align: center; margin-top: 25px;">
			<label class="radio-inline">
			  <input type="radio" id="ConsolidatedradioId" name="inlineRadioOptions" id="inlineRadio1" value="option1"> Consolidated 
			</label>
			<label  class="radio-inline individualRadioBtnCls">
			  <input type="radio" id="individualradioId" name="inlineRadioOptions" id="inlineRadio2" value="option2"> Individual
			</label>
			</div>
			<div class="col-md-2 col-xs-12 col-sm-6">
			<label>Consolidated Type</label>
			<select  id="commentFilterSelectBoxId" class="form-control col-xs-3">
			  <option value="0">District</option>
			  <option value="1">Constituency</option>
			</select>
			</div>
			<div class="col-md-2 col-xs-12 col-sm-6 districtSlctBxCls">
				<label>District</label>
				<select  id="districtSlctBxId" class="form-control col-xs-3">
				 <option value="0">Select District</option>
				</select>
				</div>
				<div class="col-md-2 col-xs-12 col-sm-6 constituencySlctBxCls">
				<label>Constituency</label>
				<select  id="constituencySlctBxId" class="form-control col-xs-3">
				<option value="0">Select Constituency</option>
				</select>
				</div>
				<div class="col-md-2 col-xs-12 col-sm-6 mandalSlctBxCls">
				<label>Mandal/Town/Division</label>
				<select  id="mandalSlctBxId" class="form-control col-xs-3">
				<option value="0">Select Mandal/Town/Division</option>
				</select>
				</div>
				<div class="col-md-2 col-xs-12 col-sm-6" style="margin-top: 25px;">
			     <input type="button" id="getDetailsBtnId" class="btn btn-success" value="GetDetails"/>
				</div>
		
		</div>
		<!--<div class="row m_top10">
			<div class="col-md-12 col-xs-12 col-sm-12 filterCls">
				
			</div>
	    </div>-->
        <div class="row">
			<div class="col-md-12 col-xs-12 col-sm-12">
			<div ><center ><img style="display: none;" src="images/icons/loading.gif" id="meetingCommentProcessingImgId"></center></div>
			<div id="meetingSummaryDtlsTblId"></div> 
			<!--<div id="meetingDesignSummaryDtlsTblId"></div> -->
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

<div class="modal fade" id="locationWiseCadreReportModalId" tabindex="-1"  role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-lg" role="document" style="margin:auto">
    <div class="modal-content">
      <div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>

	    <button class="btn btn-success btn-xs pull-right" id="cadreExcelExpBtnId" attr_tab_user_type="Tour"  style="display:none;margin-right:20px;">Export To Excel</button>
	    <h4 class="modal-title text-capital"  id="locationWiseCadreReportHeadingId"></h4>
	  <div class="modal-body">
	    <div class="row">
			<div class="col-md-12 col-xs-12 col-sm-12">
			<div ><center ><img style="display: none;" src="images/icons/loading.gif" id="locationWiseProcessImgReport"></center></div>
			
			<div class="col-md-12 col-xs-12 col-sm-12">
				<div id="locationWiseCadreReportDivId"> </div>    
			</div>      
			</div>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
</div>
<!-- Modal For Alert Start -->
<div class="modal fade" id="alertModalId" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document" style="width:90%"> 
    <div class="modal-content" style="border-radius:0px">
      <div class="modal-header" style="background-color:#CCC">
        <button type="button" class="close alertModalCloseCls" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
       <div class="row">
			<div class="col-md-5 col-xs-12 col-sm-12">
				<h4 class="text-capital" id="alertModalHeadingId"></h4>
			</div>
			<div class="col-md-7 col-xs-12 col-sm-12" id="commitLvlId" style="display:none;">        
				<label class="radio-inline">
					<input class="commitLvlCls" type="radio" name="commitLvlName" id="commitLvlId1" value="All" checked> All
				</label>
				<label class="radio-inline">
					<input class="commitLvlCls" type="radio" name="commitLvlName" id="commitLvlId6" value="Central"> Central
				</label>   
				<label class="radio-inline">
					<input class="commitLvlCls" type="radio" name="commitLvlName" id="commitLvlId2" value="State"> State
				</label>    
				<label class="radio-inline">       
					<input class="commitLvlCls" type="radio" name="commitLvlName" id="commitLvlId3" value="District"> District
				</label>
				<label class="radio-inline">
					<input class="commitLvlCls" type="radio" name="commitLvlName" id="commitLvlId4" value="Mandal"> Mandal/Municipality
				</label>
				<label class="radio-inline">
					<input class="commitLvlCls" type="radio" name="commitLvlName" id="commitLvlId5" value="Village"> Village/Ward
				</label>
			</div>
		</div>
      </div>
       <div class="modal-body">
        <div class="row">
			<div class="col-md-12 col-xs-12 col-sm-12">
			<div ><center ><img style="display: none;" src="images/icons/loading.gif" id="alertProcessingImgId"></center></div>
			<div id="alertDetailsDivId"></div> 
			</div>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default alertModalCloseCls" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
<!-- End -->
<div class="modal fade" tabindex="-1" id="tourDocumentId" role="dialog">
	<div class="modal-dialog" style="width:95%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="alertCntTitId">Tour Document</h4>  
			</div>
			<div class="modal-body" id="tourDocumentBodyId">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div><!--  /.modal-content -->  
	</div><!--  /.modal-dialog -->
</div><!--  /.modal -->
<div class="modal fade" tabindex="-1" id="tourNewDocumentId" role="dialog" style="z-index:99999;">
	<div class="modal-dialog" style="width:70%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close closeShowPdfCls" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">Tour Document</h4>  
			</div>
			<div class="modal-body" id="tourNewDocumentBodyId">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default closeShowPdfCls" data-dismiss="modal">Close</button>
			</div>
		</div><!--  /.modal-content -->  
	</div><!--  /.modal-dialog -->
</div><!--  /.modal -->
<div class="modal fade" tabindex="-1" id="meetingMemDetailsId" role="dialog">
 <div class="modal-dialog" role="document" style="width:85%;">   
    <div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close closeBtnCls" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h4 class="modal-title" id="">Member Details</h4>      
		</div>
		<div class="modal-body" id="">   
      
          <div id="meetingMemDetailsBodyId"></div>
       
		</div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default closeBtnCls" data-dismiss="modal">Close</button>
      </div>
    </div><!--   /.modal-content -->  
  </div><!--  /.modal-dialog -->
</div><!--   /.modal -->
<!-- Model for Debate Start-->           
		<div class="modal" tabindex="-1" role="dialog" id="cdrModelDivId">
		  <div class="modal-dialog modal-lg">       
			<div class="modal-content" style="border-radius:0px">
			  <div class="modal-header" style="background-color:#999999">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="tourDocHeadingId">Cadre Registration Comparison Details</h4>  
			  </div>
			  <div class="modal-body">   
				<div class="row">
					<div class="col-md-12 col-xs-12 col-sm-12"> 
						<div id="cdrModelId"></div>
					</div>
					<div class="col-md-12 col-xs-12 col-sm-12"> 
						<div id="alertDestId" ></div>
					</div>
					<div class="col-md-12 col-xs-12 col-sm-12 m_top10"> 
						<div id="sourceHeadingId"></div>
					</div>
					<div class="col-md-12 col-xs-12 col-sm-12"> 
						<div id="headingNameId" ></div>
					</div>
					<div class="col-md-12 col-xs-12 col-sm-12 m_top10"> 
						<div id="alertDocHeadingId"></div>
					</div>
					<div class="col-md-12 col-xs-12 col-sm-12"> 
						<div id="alertDocId" ></div>
					</div>
					<div class="col-md-12 col-xs-12 col-sm-12 m_top10"> 
						<div id="alertAttachTitId"></div>    
					</div> 
					<div class="col-md-12 col-xs-12 col-sm-12"> 
						<div id="alertAttachImgId"></div>  
					</div>
					<div class="col-md-12 col-xs-12 col-sm-12 m_top10"> 
						<div id="alertGroupAttachTitId"></div>    
					</div> 
					<div class="col-md-12 col-xs-12 col-sm-12"> 
						<div id="alertGroupAttachImgId"></div>  
					</div>
					<div class="col-md-12 col-xs-12 col-sm-12 m_top10"> 
						<div id="alertInvolvedCandidates"></div>        
					</div>
					<div class="col-md-12 col-xs-12 col-sm-12 m_top10"> 
						<div id="alertAssignedCandidates"></div>  
					</div>
					<div class="col-md-12 col-xs-12 col-sm-12 m_top10"> 
						<div id="alertStatusDiv" ></div>    
					</div>
					<div  class="col-md-12 col-xs-12 col-sm-12"> 
						<div id="alertCommentsDiv"></div>  
					</div> 
					<div  class="col-md-12 col-xs-12 col-sm-12 m_top10"> 
						<div id="alertVerificationDiv"></div>    
					</div>
					<div  class="col-md-12 col-xs-12 col-sm-12"> 
						<div id="alertVerificationDtlsDiv"></div>  
					</div>
				</div>
			  </div>
			  <div class="modal-footer">     
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			  </div>
			</div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
<!--end-->
<!-- Tour Document Modal -->
<div class="modal" tabindex="-1" role="dialog" id="tourDocumentDivModalId">
		  <div class="modal-dialog modal-lg">       
			<div class="modal-content" style="border-radius:0px">
			  <div class="modal-header" style="background-color:#CCC">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="tourDcHeadingId"></h4>  
			  </div>
			  <div class="modal-body">   
				<div class="row">
					<div class="col-md-12 col-xs-12 col-sm-12"> 
						<div id="tourDocumentDivId"></div>
					</div>
				</div>
			  </div>
			  <div class="modal-footer">     
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			  </div>
			</div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<div class="modal" tabindex="-1" role="dialog" id="tourIndividualPerformanceDivId" style="z-index:9999;">
		  <div class="modal-dialog modal-lg" style="width:85%">       
			<div class="modal-content" style="border-radius:0px">
			  <div class="modal-header" style="background-color:#CCC">
				<button type="button" class="close tourIndividualCls" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<span id="nameOfMemberHeadingId"></span>
			  </div>
			  <div class="modal-body"> 
				<div class="row" style="background: rgb(204, 204, 204) none repeat scroll 0% 0%; padding: 0px 0px 20px; border-radius: 6px; margin: 10px 0px 0px;">
					<div class="col-md-2 col-xs-12 col-sm-4"> 
						<select class="pull-right form-control" id="dateRangeSliderYear" style="margin-top: 46px;">
							<option value="0">Select Year</option>
							<option value="2016">2016</option>
							<option value="2017">2017</option>
							<option value="2018">2018</option>
							<option value="2019">2019</option>
							<option value="2020">2020</option>
							<option value="2021">2021</option>
							<option value="2022">2022</option>
							<option value="2023">2023</option>
							<option value="2024">2024</option>
							<option value="2025">2025</option>
						</select>
					</div>
					<div class="col-md-9 col-xs-12 col-sm-12" style="margin-left: -20px;"> 
						<div id="tourSlider" style="margin-top:7px"></div>
					</div>
					<div class="col-md-1 col-xs-12 col-sm-4 pull-right">
						<button class="btn btn-success pull-right" id="subMitBtn" type="button" style="margin-top: 46px;">SUBMIT</button>
					</div>
				
				</div>
				<!---<div class="row">
					<div class="col-md-12 col-xs-12 col-sm-12"> 
						<div id="tourSlider" style="margin-top:7px"></div>
					</div>
				</div>--->
				<div class="row">
					<div class="col-md-12 col-xs-12 col-sm-12"> 
						<div id="tourIndividualDetailsBlock" class="m_top20"></div>
						<div id="monthWiseComplainceDivId" class="row m_top20"></div>
						<div id="tourIndividualDetailsTableBlock" class="m_top20"></div>
					</div>
				</div>
			  </div>
			  <div class="modal-footer">     
				<button type="button" class="btn btn-default tourIndividualCls" class="close" data-dismiss="modal">Close</button>
			  </div>
			</div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="myModalShowNew">
	<div class="modal-dialog modal-lg" role="document" style="width:90%">
		<div class="modal-content">
			<div id="myModalShowNewId"></div>
		</div>
	</div>  
</div>      
<!-- Tour Details Modal -->
<div class="modal" tabindex="-1" role="dialog" id="tourDetailsModalId">
		  <div class="modal-dialog modal-lg" style="width:90%;">       
			<div class="modal-content" style="border-radius:0px">
			  <div class="modal-header" style="background-color:#CCC">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<!--<h4 class="modal-title" id="tourDetailsHeadingId"></h4>--> 
				<div class="row">
					<div class="col-md-9 col-xs-12 col-sm-9">
						<h4 class="modal-title text-capital" id="tourLeadrDtlsHeadingId">Leaders Detailed Report</h4>
					</div>
					
				</div>
			  </div>
			  <div class="modal-body">
					<div class="col-md-3 col-xs-12 col-sm-6 designationSelectBoxCls pull-right">
						<label class="text-capital">Select Designation
						<select  class="form-control" id="tourDesignationSelectBoxId"></select></label>	
					</div>			  
				<div class="row">
					<div class="col-md-12 col-xs-12 col-sm-12">
                       <div id="tourDetailsDivId"></div>
					</div>
				</div>
			  </div>
			  <div class="modal-footer">     
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			  </div>
			</div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="myModelActivityId" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document" style="width:90%"> 
    <div class="modal-content" style="border-radius:0px">
      <div class="modal-header" style="background-color:#CCC">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title text-capital" id="myModelActivityhead"> <small class="" id="smallHeadngId" ></small></h4>
			<div class="row m_top10">
			<div style="background-color:#E9E9E9;">
				<div class="row">
						<div class="col-md-2 col-xs-12 col-sm-3" style="margin-left: 15px;margin-bottom: 25px;">
							<label>District</label>
							<select class="form-control districtCls" id="districtId" >
								<select>
						</div>
						<div class="col-md-2 col-xs-12 col-sm-3" style="display:none;" id="constncyDivId">
							<label>Constituency</label>
							<select class="form-control constituencyCls" id="constituencyId" >
								<select>
						</div>
						<div class="col-md-2 col-xs-12 col-sm-3" style="display:none;" id="mandalDivId">
							<label>Mandal</label>
							<select class="form-control mandalsCls" id="mandalId" >
								<select>
						</div>
						<div class="col-md-2 col-xs-12 col-sm-3" style="display:none;" id="villgWardDivId">
							<label>Village</label>
							<select class="form-control villgWardCls" id="villgWardId" >
								<select>
						</div>
						<div class="col-md-2 col-xs-12 col-sm-3">
							<button type="button" class="btn btn-success m_top20 submitCls">SUBMIT</button>
						</div>
				</div>
			</div>
			</div>
		</div>
       <div class="modal-body">
			
        <div class="row">
			<div class="col-md-12 col-xs-12 col-sm-12">
				<div class="col-md-8 col-xs-12 col-sm-6">
					<h4><span id="locatnNamId" class="text-capital"></span></h4>
				</div>
				<div class="col-md-4 col-xs-12 col-sm-6">
					<!--<label class="radio-inline ">
					<input type="radio" name="radioBtn" class="radioBtnCls" value="All"/>All
					</label>-->
					<label class="radio-inline ">
						<input type="radio" name="radioBtn" class="radioBtnCls" value="Conducted" checked />Conducted
					</label>
					<label class="radio-inline ">
						<input type="radio" name="radioBtn" class="radioBtnCls" value="NotConducted"/>Not Conducted
					</label>
					<!--<label class="radio-inline ">
						<input type="radio" name="radioBtn" class="radioBtnCls" value="notupdated"/>Not Updated
					</label>-->
				</div>
				
					
			</div>
			<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
				<div class="table-responsive" id="activityId">  
				  
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

<!-------start popup build------------>
<div class="modal fade" id="myModalImageId" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="z-index:9999">
  <div class="modal-dialog modal-lg" role="document"  id="slick-modal" style="width:90%;z-index:9999" >
    <div class="modal-content customModal">
      <div class="modal-header">
        <button type="button" class="close imageCloseBtnCls" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabelId" style="text-transform: uppercase;"></h4>
      </div>
      <div class="modal-body" style="padding:0px 15px;">
       <div id="buildPoupupImage"></div>
      </div>
    </div>
  </div>
</div>
<!--------End Popup build----------->

<!-- Modal for popUp Images -->
<div class="modal fade" id="imagesModalDivId" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-lg" role="document"  id="slick-modal" style="width:90%">
    <div class="modal-content customModal">
      <div class="modal-header">
        <button type="button" class="close imagesModalClose" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel"></h4>
      </div>
      <div class="modal-body" style="padding:0px 15px;">
       <div id="buildImagesId"></div>
      </div>
    </div>
  </div>
</div>
<!-- Modal For UpdationDetails -->
<div class="modal fade" id="commentsModalId" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document" style="width:80%">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="mdlHeadingId" style="text-transform: uppercase;"></h4>
      </div>
      <div class="modal-body">
	  <div class="row">
	  <div class="col-md-3 col-xs-12 col-sm-6">
	  <label>District</label>
	  <select class="form-control selectCls" id="distId" attr_value="district" onchange="getConstituenciesForDistricts(this.value);" name=""    data-placeholder ="ALL" >	  
	    </select>
		</div>
		<div class="col-md-3 col-xs-12 col-sm-6">
		<label>Constituency</label>
		<select class="form-control selectCls" id="constId" attr_value="constituency" name="" onchange="onchangeFunction();" data-placeholder ="ALL">
		</select>
		</div>
		<div class="col-md-3 col-xs-12 col-sm-6">
		<label>Status</label>
		<select class="form-control" id="thirdPartyStatusId" name="" data-placeholder ="ALL" onchange="onchangeFunction();">
			   <option value="ALL">ALL</option>
			   <option value="Yes">Yes</option>
			   <option value="No">No</option>
		</select>
		</div>
		<div class="col-md-2 col-xs-12 col-sm-6">
             <button class="btn btn-success"  id="levelTypeId" onclick="getupdationDetailsConflicts();" style="margin-top: 19px;"> Get Details </button>
           </div>
		</div>
        <div id="commentsBlock" class="m_top10"></div>		
        <!--<div id="commentsDivId"></div>-->
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>
<div class="modal fade" id="customModalId" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document" style="width:60%">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="customHeadingId"></h4>
      </div>
      <div class="modal-body">
        <div id="detailedReportsDivId"></div>
        <!--<div id="commentsDivId"></div>-->
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>
<input type="hidden" id="cmtId" attr_cmt_id="editTextId'+i+'" value=""></input>
<input type="hidden" id="cmtTrngId" attr_cmt_id="editTextTrngId'+i+'" value=""></input>
<input type="hidden" id="cmtDebateId" attr_cmt_id="editTextDebateId'+i+'" value=""></input>
<input type="hidden" id="cmtNewsId" attr_cmt_id="editTextNewsId'+i+'" value=""></input>
<input type="hidden" id="cmtMeetingId" attr_cmt_id="editTextmettingId'+i+'" value=""></input>
<input type="hidden" id="cmtEventsId" attr_cmt_id="editTextEventsId'+i+'" value=""></input>
<input type="hidden" id="cmtAttendanceId" attr_cmt_id="editTextAttendanceId'+i+'" value=""></input>
<input type="hidden" id="hiddenActivityScopeId"></input>
<input type="hidden" id="hiddenActivityLevelId"></input>
<input type="hidden" id="hiddenLevelTypeId" attr_level_type="impactScopeWise"></input>

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
<script src="coreApi/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="coreApi/Plugins/DataTable/exportButtons.js" type="text/javascript"></script>
<script src="coreApi/Plugins/DataTable/jsZip.js" type="text/javascript"></script>
<script src="coreApi/Plugins/DataTable/pdf.js" type="text/javascript"></script>
<script src="coreApi/Plugins/DataTable/v5font.js" type="text/javascript"></script>
<script src="coreApi/Plugins/DataTable/htmlButtons.js" type="text/javascript"></script>
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
<script src="newCoreDashBoard/js/boothCommittees.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/statusColorCodesForNews.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/eventsActivities.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/attendanceCoreDashBoard.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/cadreRegistration.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/electronicMedia.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/cadreInsurance.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/prajaSankaplaYatra.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/partyMeeting1.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/partyMeetingExt.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mCustomScrollbar.js"></script>
<script src="dist/scroll/jquery.mousewheel.js"></script>
<script src="newCoreDashBoard/js/tours.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/alerts.js" type="text/javascript"></script>
<script src="dist/sliderbar/bootstrap-slider.js" type="text/javascript"></script>
<script src="js/Activities/activityDashboard.js" type="text/javascript"></script>
<script type="text/javascript" src="js/simplePagination/simplePagination.js" ></script>
<script src="newCoreDashBoard/js/pressmeet.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).on("click",".rightNavigationMenuRes",function(){
	$(this).toggleClass("active");
	$(".rightNavigationMenu ul,.backgroundBlock").toggleClass("hidden-xs");
});
//$(document).on("click","[expand-icon]",function(){
$("[expand-icon]").click(function(){
	var $this = $(this);
	var blockName = $this.attr("expand-icon");
	var iconClass = $("[expand-icon="+blockName+"]").find("i").attr("class");
	var rightNav = $this.attr("right-nav");
	$(".rightNavigationMenu ul,.backgroundBlock").addClass("hidden-xs");
	$(".rightNavigationMenuRes").removeClass("active");
	if(iconClass == 'glyphicon glyphicon-fullscreen' || rightNav == true)
	{
		closeBlocks();
		$("[right-nav]").removeClass("active");
		$("[expand-icon="+blockName+"]").addClass("active");
		$("[expand-icon="+blockName+"]").find("i").removeClass("glyphicon-fullscreen").addClass("glyphicon-resize-small");
		$("[expand-block="+blockName+"]").removeClass("col-md-6").addClass("col-md-12").css("transition"," ease-in-out, width 0.7s ease-in-out");
		$("[expand-block-inner="+blockName+"]").addClass("col-md-6").removeClass("col-md-12").css("transition"," ease-in-out, width 0.7s ease-in-out");
		$("[expand-block-right="+blockName+"]").show();
		$("[expand-block-date="+blockName+"]").removeClass("hide");
		$("[expand-icon-inner="+blockName+"],[expand-icon-inner]").find("i").addClass("glyphicon-fullscreen").removeClass("glyphicon-resize-small");
		datePickerAlignment(blockName);
		if($this.attr("expand-icon-inner") == blockName)
		{
			$this.find("i").removeClass("glyphicon-fullscreen").addClass("glyphicon-resize-small");
		}
		setTimeout(function(){
		$('html,body').animate({
			scrollTop: $("[expand-block="+blockName+"]").offset().top},
		'slow');
		},1000);
		if(blockName == 'alerts')
		{
			defaultAlertCalls();
		}else if(blockName == 'debates')
		{
			globalDebateCalls();
		}else if(blockName == 'news')
		{
			commonNewsBasicCalls();
		}else if(blockName == 'electronic')
		{
			globalElectronicMediaCalls('');
		}else if(blockName == 'tours')
		{
			globalToursCalls('');
		}else if(blockName == 'meetings')
		{
			$(".moreMeetingsBlocksIcon").attr("expand_event_name",'mainMeetings')
			$(".stateLevelMeetingBlock").show();
			$(".showMoreBlockCls").show();
			$(".dateRangePickerClsForMeetings").removeClass("hide");
			$(".meetingFilterCls li").removeClass("active");
		    $(".meetingFilterCls li:first-child").addClass("active");
			//$(".meetingsIconExpand").find("i").addClass("glyphicon-resize-small").removeClass("glyphicon-fullscreen");
			$(".meetingsRefresh").attr("attr_refresh_status","true");
			$(".meetingsRefresh").attr("attr_meeting_type","committeeMeeting");
			getUserTypeWiseMeetingCounductedNotCounductedMayBeDetailsCnt();
		}else if(blockName == 'cadre')
		{
			getUserTypeWiseTotalCadreRegistrationCount();
		}else if(blockName == 'committees')
		{
			getUserTypeWiseCommitteesCompletedCounts1();
		}else if(blockName == 'boothCommittees')
		{
			getUserTypeWiseCommitteesCompletedCounts2();
			$(".moreBlocksIcons").show();
		}else if(blockName == 'events')
		{
			$("#eventsCmpBlckDivId").find("ul li").attr("attr_type","events");
			var eventIdsString="7,30";
			
			$(".moreEventsBlocksIcon").attr("attr_type","event");
			$(".moreEventsBlocksIcon").attr("attr_event_idsString",eventIdsString);
			$(".detailedEvent").attr("attr_type","event");
			$(".detailedEvent").attr("attr_event_idsString",eventIdsString);
			$(".comparisonEvent").attr("attr_type","event");
			$(".comparisonEvent").attr("attr_event_idsString",eventIdsString);
			
			getUserTypeWiseTotalInviteeAndInviteeAttendedCnt(eventIdsString);
		}else if(blockName == 'attendance')
		{
			getAttendanceOverViewForPartyOfficeDeptWise();
		}else if(blockName == 'training')
		{
			getUserTypeWiseTotalEligibleAndAttendedCnt();
		}else if(blockName == 'cadreInsurance')
		{
			getUserTypeWiseTotalCadreInsuranceComplainctCnt("All Time",4);
		}
	}else if(iconClass == 'glyphicon glyphicon-resize-small')
	{
		closeBlocks();
		$("[expand-icon="+blockName+"]").removeClass("active");
		$("[expand-icon-inner="+blockName+"],[expand-icon-inner]").find("i").addClass("glyphicon-fullscreen").removeClass("glyphicon-resize-small");
	}/* else if(iconClass == 'prajaSankalpa')
	{
		$("[right-nav]").removeClass("active");
		$("[expand-icon="+blockName+"]").addClass("active");
		$("[expand-block-right="+blockName+"]").show();
		$('html, body').animate({
			scrollTop: $('.prajaSankaplaYatraBlock').offset().top - 20
		}, 'slow');
	} */
	
});
$(document).on("click","[expand-icon-inner]",function(){
	var $this = $(this);
	var blockName = $this.attr("expand-icon-inner");
	var iconClass = $this.find("i").attr("class");
	var rightNav = $this.attr("right-nav");
	
	//alert($this.find("i").attr("class"));
	if($this.find("i").hasClass("glyphicon-fullscreen"))
	{
		$("[expand-icon-inner").find("i").addClass("glyphicon-fullscreen").removeClass("glyphicon-resize-small");
		closeBlocks();
		setTimeout(function(){
			$('html,body').animate({
				scrollTop: $("[expand-block="+blockName+"]").offset().top},
			'slow');
		},1000);
		$("[right-nav]").removeClass("active");
		$("[expand-icon="+blockName+"]").addClass("active");
		$("[expand-icon="+blockName+"]").find("i").removeClass("glyphicon-fullscreen").addClass("glyphicon-resize-small");
		$("[expand-block="+blockName+"]").removeClass("col-md-6").addClass("col-md-12").css("transition"," ease-in-out, width 0.7s ease-in-out");;
		$("[expand-block-inner="+blockName+"]").addClass("col-md-6").removeClass("col-md-12").css("transition"," ease-in-out, width 0.7s ease-in-out");;
		$("[expand-block-right="+blockName+"]").show();
		$("[expand-block-date="+blockName+"]").removeClass("hide");
		datePickerAlignment(blockName);
		$this.find("i").removeClass("glyphicon-fullscreen").addClass("glyphicon-resize-small");
		var expandName = $this.attr("expand_event_name");
		if(blockName == 'events')
		{
			if(expandName == "events")
			{
				$("#eventsCmpBlckDivId").find("ul li").attr("attr_type","events");	
				$("#eventsCmpBlckDivId").find("ul li:nth-child(2)").removeClass("active")	
				$(".moreEventsBlocksIcon").removeClass("acitivitiesMoreExpand");
				$("#eventsDistWiseCohort1,#eventsGraphBlock1").html(' ');
				$(".activitiesH4").html("Cohort")
				var eventIdsString = $(this).attr("attr_event_idsString");
				var eventName = $(this).attr("attr_event_name");
				$(".eventAndActivityCls").html(eventName);
				$(".moreEventsBlocksIcon").attr("attr_type","event");
				$(".moreEventsBlocksIcon").attr("attr_event_idsString",eventIdsString);
				$(".detailedEvent").attr("attr_type","event");
				$(".detailedEvent").attr("attr_event_idsString",eventIdsString);
				$(".comparisonEvent").attr("attr_type","event");
				$(".comparisonEvent").attr("attr_event_idsString",eventIdsString);
				$(".eventsHiddenBlock,.moreEventsBlocksIcon").show();
				
				$("#eventsDistWiseCohort1,#eventsGraphBlock1").html(' ');
				$(".activitiesH4").html("Cohort")
				var eventIdsString = $(this).attr("attr_event_idsString");
				var eventName = $(this).attr("attr_event_name");
				$(".eventAndActivityCls").html(eventName);
				$(".detailedEvent").attr("attr_type","event");
				$(".detailedEvent").attr("attr_event_idsString",eventIdsString);
				$(".comparisonEvent").attr("attr_type","event");
				$(".comparisonEvent").attr("attr_event_idsString",eventIdsString);
				getUserTypeWiseTotalInviteeAndInviteeAttendedCnt(eventIdsString);	
				//getUserTypeWiseTotalInviteeAndInviteeAttendedCnt(eventIdsString);
			}else if(expandName == "activities")
			{
				var searchType = $(this).attr("attr_search_type");
				$("#eventsCmpBlckDivId").find("ul li").attr("attr_type","activities");
				$("#eventsCmpBlckDivId ul li:nth-child(2)").attr("attr_search_type",searchType);
				$("#eventsCmpBlckDivId ul li:nth-child(2)").attr("attr_event_idsstring",activityId);
				var activityLevelIds=[];
				var activityId = $(this).attr("attr_id");
				var activityName = $(this).attr("attr_activity_name");
				var activityLevelId = $(this).attr("attr_level_id");
				 if(activityLevelId != null && activityLevelId > 0){
					activityLevelIds.push(activityLevelId); 
				 }
				 getUserTypeActivityConductedCnt(activityId,activityLevelIds);
				 activityName = activityName.replace("'","");
				 activityName = activityName.replace("\'","");
				 $(".eventAndActivityCls").html(activityName);
				$(".moreEventsBlocksIcon").addClass("acitivitiesMoreExpand");
				$(".eventsHiddenBlock,.moreEventsBlocksIcon").show();
				var eventIdsString = $(this).attr("attr_event_idsString");
				if($(".detailedBlockEvents").is(":visible"))
				{
					var activityId = $("#hiddenActivityId").val();
					stateWiseCohort(activityId);
					districtWiseCohort(activityId);
				}
				if($(".comparisonBlockActivities").is(":visible")){
					$('#eventsCmpBlckDivId ul li').trigger('click');
				}
				
					$(".acitivitiesMoreExpand").removeAttr("attr_type");
					$(".acitivitiesMoreExpand").removeAttr("attr_event_idsString");
					$(".acitivitiesMoreExpand").attr("attr_type","activity");
					$(".acitivitiesMoreExpand").attr("attr_event_idsString",activityId);
			}else if(expandName == "overallActivity")
			{
				var activityLevelIds=[];
				var activityId = $(this).attr("attr_id");
				var activityName = $(this).attr("attr_activity_name");
				var activityLevelId = $(this).attr("attr_level_id");
				 if(activityLevelId != null && activityLevelId > 0){
					activityLevelIds.push(activityLevelId); 
				 }
				 getUserTypeActivityConductedCnt(activityId,activityLevelIds);
				 activityName = activityName.replace("'","");
				 activityName = activityName.replace("\'","");
				 $(".eventAndActivityCls").html(activityName);
			}
			
		}else if(blockName == 'training'){
			
			stateLevel = $this.attr("state-level");
			if(stateLevel == 'true')
			{
				var val = $(this).attr("attr_location");
				$("#clickInfoId").html(val); 
				$("#switchButtonId").removeClass("moreTrainingBlocksIcon");
				$("#switchButtonId").addClass("moreTrainingCampBlocksIcon");   
				$("#detailedId").removeClass("trainingDetailed");
				$("#detailedId").addClass("trainingCampDetailed");
				$("#clickInfoId").show();
				$(".trainingComparison").hide();
				$(".trainingCampDetailed").attr("attr_program_id",$(this).attr("attr_program_id"));  
				var programId = [];
				programId.push($(this).attr("attr_program_id"));
				getStateLevelCampCount(programId);         
				stateLevelCampMembersDistWise(programId); 
				stateLevelCampDetailsRepresentativeWise(programId);
			}else{
				getUserTypeWiseTotalEligibleAndAttendedCnt();
			}
		}else if(blockName == 'meetings'){
			//var memberType = $(".meetingLiCls").attr("attr_value");
			
			$(".moreMeetingsBlocksIcon").attr("expand_event_name",expandName)
			if(expandName == "statelevelmeeting")
			{
				console.log("State Level Meetings");
			}else if(expandName == "mainMeetings")
			{
				var attrMainTypeMeetingId = $(this).attr("attr_main_type_meeting_id");
				var meetingTypeId = $(this).attr("attr_meeting_type_id");
				$(".showMoreBlockCls,.showMoreBlockCls,.moreMeetingsBlocksIcon,.stateLevelMeetingBlock").show();
				$(".showMoreBlockCls").attr("attr_main_type_meeting_id",attrMainTypeMeetingId);
				$(".showMoreBlockCls").attr("attr_meeting_type_id",meetingTypeId);
				$(".showMoreBlockCls").attr("attr_selected_type","stateLevel");
				$(".meetingsRefresh").attr("attr_refresh_status","true");
				$(".meetingsRefresh").attr("attr_meeting_type","committeeMeeting");
				getUserTypeWiseMeetingCounductedNotCounductedMayBeDetailsCnt();
			}else if(expandName == "multiLocation")
			{
				var locLevelId = $(this).attr('attr_levelId'); 
				var partyMeetingGroupId = $(this).attr('attr_group_id');
				var sessionId= $(this).attr('attr_sessionId');
				$(".moreMeetingsBlocksIcon").attr('attr_levelId',locLevelId).attr('attr_group_id',partyMeetingGroupId).attr('attr_sessionId',sessionId); 
				getCmmttsAndPblcRprsnttvMembersInvitedAndAttendedToSeeionWiseMultiLocationMeetingDtls(locLevelId,partyMeetingGroupId);
				setTimeout(function(){
					$('html,body').animate({
						scrollTop: $(".meetingsBlock").offset().top},
					'slow');
				},500);
			}else if(expandName == "specialMeetings")
			{
				$("#meetingLevelHIghChartsDivId,#userAccessLevelLocationDivId").html(' ');
				var partyMeetingId = $(this).attr("party_meetingId");
				var partyMeetingTypeId = $(this).attr("party_meeting_type_id");
				var partyMeetingMainTypeId = 3;
				var partyMeetingIdArr = [];
				partyMeetingIdArr.push(partyMeetingId);
				$(".showMoreBlockCls").attr("attr_selected_type","sessionMeeting");
				$(".meetingsRefresh").attr("attr_refresh_status","true");
				$(".meetingsRefresh").attr("attr_main_type_meeting_id",partyMeetingMainTypeId);
				$(".meetingsRefresh").attr("attr_partymeetingtypeidsstring",partyMeetingTypeId);
				$(".meetingsRefresh").attr("attr_special_meeting_id",partyMeetingId);
				$(".moreMeetingsBlocksIcon").attr("party_meeting_id",partyMeetingId);
				$(".moreMeetingsBlocksIcon").attr("party_meeting_type_id",partyMeetingTypeId);
				$(".meetingsRefresh").attr("attr_meeting_type","specailSessionTypeMeeting");
				$(".stateLevelMeetingBlock").show();
				$(".showMoreBlockCls").show();
				
				setSessioncolorsForStatus();
				getCommitteesAndPublicRepresentativeMembersInvitedAndAttendedToSeeionWiseMeetingDtls(partyMeetingMainTypeId,partyMeetingTypeId,partyMeetingIdArr);
			}
		}
	}else{
		closeBlocks();
		$("[expand-icon-inner="+blockName+"]").find("i").addClass("glyphicon-fullscreen").removeClass("glyphicon-resize-small");
	}
});
function datePickerAlignment(blockName)
{
	$("[expand-block-heading]").addClass("col-md-9 col-sm-9").removeClass("col-md-8 col-sm-8");
	$("[expand-block-heading1]").addClass("col-md-3 col-sm-3").removeClass("col-md-4 col-sm-4");
	$("[expand-block-heading="+blockName+"]").removeClass("col-md-9 col-sm-9").addClass("col-md-8 col-sm-8");
	$("[expand-block-heading1="+blockName+"]").removeClass("col-md-3 col-sm-3").addClass("col-md-4 col-sm-4");
}
function closeBlocks()
{
	$("[expand-icon]").find("i").addClass("glyphicon-fullscreen").removeClass("glyphicon-resize-small");
	$("[expand-block]").addClass("col-md-6").removeClass("col-md-12").css("transition"," ease-in-out, width 0.7s ease-in-out");
	$("[expand-block-inner]").removeClass("col-md-6").addClass("col-md-12");
	$("[expand-block-right],[expand-block-more]").hide();
	$("[expand-block-date]").addClass("hide");
}
//find device ipad & Iphone & Android
	var ua = navigator.userAgent,
	browser = {
		iPad: /iPad/.test(ua),
		iPhone: /iPhone/.test(ua),
		Android5: /Android 5/.test(ua)
	};
	if(browser.iPad) { minimise(".rightNavigationMenu li",5) }
	else if(browser.Android5) { alert('ANDROID') }else{
		minimise(".rightNavigationMenu li",3)
	}
	

function minimise(Id,count)
{
	var id = Id;
	var minimized_elements = $(id);
	minimized_elements.each(function(){    
		var t = $(this).text();        
		if(t.length < count) return;

		$(this).html(
			'<span class="less">'+t.slice(0,count)+'..</span>'+
			'<span style="display:none;" class="more text-capitalize">'+t+'</span>'
		);

	}); 
	$(document).on("mouseover",id,function(){
		$(this).find('span').hide();
		$(this).find('span.more').show();
	});
	$(document).on("mouseout",id,function(){
		$(this).find('span.less').show();
		$(this).find('span.more').hide();
	});	
}
/* GLobal Navbar Date Change Start*/
$(document).on("click",".globalDateChange",function(){
	var type = $(this).attr("attr_type");
	 var URLArray = windowUrl.split('/');
	 var fnlURL = URLArray[parseInt(URLArray.length) - 1].replace('.action','');
	 if(fnlURL =="partyAndLeaderActivitiesAndPerformanceTracking"){
	 	  globalPrintMediaCalls(type);
		  globalToursCalls(type);
		  globalMeetingsCalls(type);  
	  }else if(fnlURL =="partyLeadersDashboardAction"){
		  globalPrintMediaCalls(type);
		  globalToursCalls(type);
		  globalMeetingsCalls(type);  
	  }else{
		globalAlertsCalls(type);
		globalDebateCalls(type);
		globalPrintMediaCalls(type);
		globalElectronicMediaCalls(type);
		globalToursCalls(type);
		globalMeetingsCalls(type);  
	  }
});
/* GLobal Navbar Date Change End*/
/*Scroll Top Start*/
$(window).scroll(function(){
	var windowScrollTop = $(window).scrollTop();
	if (windowScrollTop>200) {
		$(".scrollTopHtml").removeClass("hide");
	} else {
		$(".scrollTopHtml").addClass("hide");
	}
});
$(document).on("click",".scrollTopHtml",function(){
	setTimeout(function(){
		$('html,body').animate({
			scrollTop: $(".navbarHeader").offset().top
		},'slow');
	},100);
});
/*Scroll Top Start*/
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
	
	
	//settings scroll
	$(".basicCommitteeDetailsDiv").mCustomScrollbar({setHeight:'300px'})
	$(".basicBoothCommitteeDetailsDiv").mCustomScrollbar({setHeight:'300px'})
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
	  var globalselectedUserType='STATE';
	  var globalState = 'AP';
	  
	  //This enrollementYearIds is used in alert block
	  var enrollementYearIds = getTdpCadreEnrollementYearIds();
	  var globalEnrollementyearIdArr = enrollementYearIds;
	  
	  
	   var globalBasicCommitteeIdsArray = []; //basicCommitteeIdsArray
	  getCheckedBasicCommitteeIds();
	  
	 
	$(document).ready(function(){
		//Main header remove
		//$(".eventsheader").hide();
		$('[data-toggle="tooltip"]').tooltip();
		//stateLevelCampDetails();
		getTrainingCampBasicDetailsCntOverviewTrainingCampCenterWise();
		getCadreEnrolmentYears();
		getBoothCadreEnrolmentYears();
	});
	getLoggedInUserStructure();
	
	onLoadCalls()
	function onLoadCalls(){
		getUserTypeWiseBoothCommitteesInchargeDetails();
		
		onloadPrajaSankaplaYatraCalls();
		//Preemeet
		preemeeetOnloadCalls();
		//news please dont remove
		$("#currentViewing").html(" TODAY ( "+moment().format('DD-MM-YYYY')+" )");
		var URLArr = windowUrl.split('/');
		//console.log(URLArr[parseInt(URLArr.length) - 1].replace('.action',''));
	 var finalURL = URLArr[parseInt(URLArr.length) - 1].replace('.action','');
		
	  if(finalURL =="dailyMonthlyPartyActivities")
	  {
		//ALL BLOCKS
		$("#mainHeadinId").html("KALA VENKATA RAO");
		getRescentArticleTime();		
		//committeeBasicCall();
		
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
				
		//getTrainingCampProgramOverviewDtls();   
		//Meeting
		getTrainingCampBasicDetailsCntOverview(); 
        getTrainingCampBasicDetailsCntOverviewTrainingCampCenterWise();
		getPartyMeetingTypeByPartyMeetingMainType();
		getStateLevelMeetingsByMeetingType();
		getSpecialMeetingsByMeetingType();
		getMultiLocationWiseMeetingGroupsData();
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
		//getAllItsSubUserTypeIdsByParentUserTypeIdForCadreRegistration(globalUserTypeId); 
        /* Tours Default Call */
       // getToursBasicOverviewCountDetails();     
		//getDesigWiseMemberDtls();  
		/*New Tours implementation Default Call */
		getToursBasicOverviewDtls();
     	/*Electronic Media Calls*/
		getMediaProgramsOnParty(globalUserAccessLevelId,globalUserAccessLevelValues);		
		getAllTvChannels();
		getRescentNewsBulletinTime();
		/* Alert Default Call */
		//getAlertOverviewDetails();  
		getAlertDtlsBasedOnSelection('default');
		/* Insurance Calls */
		globalInsuranceCalls();
		/* Activities Default Call */
		getActivitiesDetails();
		getAttendanceOverViewForPartyOffice();
		getAttendanceOverViewForPartyOfficeWise();
		getSettingActivities();
		//getSettingEvents();
		//onloadPrajaSankaplaYatraCalls();
		var datStr = changeDateFormat($("#dateRangeIdForAttendance").val());
		$("#attendanceId").html('TODAY ('+datStr+')');
		
	  }
	  else  if(finalURL =="partyAndLeaderActivitiesAndPerformanceTracking")
	  {
	    $("#mainHeadinId").html("KALA VENKATA RAO");
		$(".alertsBlock,.debatesBlock,.electronicMediaBlock,.cadreBlock,.committeesBlock,.eventsBlock,.attendanceBlock,.trainingsBlock,.rightNavigationMenu").remove();
		$(".newsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".newsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".newsHiddenBlock,.morenewsBlocksIcon,.editionWiseBlock").show();
		$(".dateRangePickerClsForNews").toggleClass("hide");
		$(".newsHead").toggleClass('col-md-9 col-sm-9').toggleClass('col-md-8 col-sm-8');
		$(".newsHead1").toggleClass('col-md-3 col-sm-3').toggleClass('col-md-4 col-sm-4');
		$(".specialMeetingSeeting").closest(".panelBlock").hide();
		getRescentArticleTime();
		getToursBasicOverviewDtls();
		getPartyMeetingTypeByPartyMeetingMainType();
		getStateLevelMeetingsByMeetingType();
		//getNewsBasicCounts();
		commonNewsBasicCalls();
		getAllNewsPapers();
		
		getPartySpecialMeetingsMainTypeOverview(0);
	  }else  if(finalURL =="partyLeadersDashboardAction")//finalURL =="coreDashboardAction1"
	  {
		$(".debatesBlock,.electronicMediaBlock,.cadreBlock,.eventsBlock,.attendanceBlock,.trainingsBlock,.alertsBlock,.rightNavigationMenu").remove();
		$(".newsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".newsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".newsHiddenBlock,.morenewsBlocksIcon,.editionWiseBlock").show();
		$(".dateRangePickerClsForNews").toggleClass("hide");
		$(".newsHead").toggleClass('col-md-9 col-sm-9').toggleClass('col-md-8 col-sm-8');
		$(".newsHead1").toggleClass('col-md-3 col-sm-3').toggleClass('col-md-4 col-sm-4');
		$(".specialMeetingSeeting").closest(".panelBlock").hide();
		getRescentArticleTime();
		getToursBasicOverviewDtls();
		getPartyMeetingTypeByPartyMeetingMainType();
		getStateLevelMeetingsByMeetingType();
		//getNewsBasicCounts();
		commonNewsBasicCalls();
		getAllNewsPapers();
		//getAlertOverviewDetails();
		//getAlertDtlsBasedOnSelection('default');
	  }else if(finalURL == "govtPartyCoreDashboardAction")
	  {
		  $(".debatesBlock,.cadreBlock,.eventsBlock,.attendanceBlock,.trainingsBlock,.alertsBlock,.boothCommitteesBlock,.NewToursBlock,.meetingsBlock,.committeesBlock,.cadreInsuranceBlock,.rightNavigationMenu,.navbarProfile,.birthdayHideShowCls").remove();
		$("#currentViewing").html(" TODAY ( "+moment().format('DD-MM-YYYY')+" )");
		globalUserAccessLevelId = 2;
		globalUserAccessLevelValues.push(1);
		globalActivityMemberId=44;
		globalUserTypeId="2";//ara
		commonNewsBasicCalls();
		getAllNewsPapers();
		getMediaProgramsOnParty(globalUserAccessLevelId,globalUserAccessLevelValues);		
		getAllTvChannels();
		getRescentNewsBulletinTime();
	  }
	  else{
		   $("#mainHeadinId").html("KALA VENKATA RAO");
			//ALL BLOCKS
			getRescentArticleTime();		
			//committeeBasicCall();
			
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
            getTrainingCampBasicDetailsCntOverviewTrainingCampCenterWise();			
			//getTrainingCampProgramOverviewDtls();   
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
			//getAllItsSubUserTypeIdsByParentUserTypeIdForCadreRegistration(globalUserTypeId); 
			/* Tours Default Call */
		   // getToursBasicOverviewCountDetails();     
			//getDesigWiseMemberDtls();  
			/*New Tours implementation Default Call */
			getToursBasicOverviewDtls();
			/*Electronic Media Calls*/
			getMediaProgramsOnParty(globalUserAccessLevelId,globalUserAccessLevelValues);		
			getAllTvChannels();
			getRescentNewsBulletinTime();
			/* Alert Default Call */
			//getAlertOverviewDetails();
			getAlertDtlsBasedOnSelection('default');
			/* Activities Default Call */
			getActivitiesDetails();
			getAttendanceOverViewForPartyOffice();
			getAttendanceOverViewForPartyOfficeWise();
			getSettingActivities();
			//getSettingEvents();
			//onloadPrajaSankaplaYatraCalls();
			var datStr = changeDateFormat($("#dateRangeIdForAttendance").val());
			$("#attendanceId").html('TODAY ('+datStr+')');
	  }
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
		globalselectedUserType = selectedUserType;
		onLoadCalls();
		defaultCommitteeCalls();
		boothDefaultCommitteeCalls();
		getAllItsSubUserTypeIdsByParentUserTypeIdForCadreRegistration(globalUserTypeId);     
		defaultAlertCalls();
		//if any member is selected then hide the ts block
		$("#tsConstBlockId").hide();
		$("#tsDistrictBlockId").hide();
		$(".tsBlockCls").hide();    
		$(".apBlockCls").show();   
		
		
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
    function getTdpCadreEnrollementYearIds(){
		var enrollementIdValues = [];
		<c:forEach items="${alertOverviewVO.enrollementIdList}" var="enrollement">
		  enrollementIdValues.push( ${enrollement.id} );        
	   </c:forEach>
	   return enrollementIdValues;
	}
$(document).on("click",".moreTrainingCampBlocksIcon",function(){
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

function refreshMeeting(){
	getSpecialMeetingsByMeetingType();
}
function refreshMeetingStatus(){
	getinsertDataInToPartyMeetingStatus();
}
</script> 
<script>
  var tableToExcel = (function() {
  var uri = 'data:application/vnd.ms-excel;base64,'
    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--><meta http-equiv="content-type" content="text/plain; charset=UTF-8"/></head><body><table>{table}</table></body></html>'
    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
  return function(table, name) {
    if (!table.nodeType) table = document.getElementById(table)
    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
    window.location.href = uri + base64(format(template, ctx))
  }
})()
</script>
</body>
</html>